package org.cscie88c.week10

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.when
import org.cscie88c.config.{ ConfigUtils }
import org.cscie88c.utils.{ SparkUtils }
import pureconfig.generic.auto._

// run with: sbt "runMain org.cscie88c.week10.SparkSQLApplication"
object SparkSQLApplication {

  val SPARK_DS_CONF_PATH = "org.cscie88c.spark-ds-application"

  def main(args: Array[String]): Unit = {
    implicit val conf: SparkDSConfig = readConfig()
    val spark = SparkUtils.sparkSession(conf.name, conf.masterUrl)
    val transactionDF = loadData(spark)
    val augmentedTransactionsDF = addCategoryColumn(transactionDF)

    augmentedTransactionsDF.createOrReplaceTempView("transactions")
    val sparkSQL =
      "Select category, SUM(transactionAmount) as total from transactions group by category"
    val totalsByCategoryDF = spark.sql(sparkSQL)
    printTransactionTotalsByCategory(totalsByCategoryDF)
    spark.stop()
  }

  def readConfig(): SparkDSConfig =
    ConfigUtils.loadAppConfig[SparkDSConfig](SPARK_DS_CONF_PATH)

  def loadData(spark: SparkSession)(implicit conf: SparkDSConfig): DataFrame = {
    import spark.implicits._
    spark
      .sparkContext
      .textFile(conf.transactionFile)
      .map(CustomerTransaction(_))
      .collect {
        case Some(ct) => ct
      }
      .toDF()
  }

  def addCategoryColumn(raw: DataFrame): DataFrame =
    raw.withColumn(
      "category",
      when(raw("transactionAmount") > 80, "High").otherwise("Low")
    )

  def printTransactionTotalsByCategory(df: DataFrame): Unit =
    df.show()

}
