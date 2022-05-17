package org.cscie88c.week10

import org.apache.spark.sql.{ Dataset }
import org.apache.spark.sql.SparkSession
import org.cscie88c.config.{ ConfigUtils }
import org.cscie88c.utils.{ SparkUtils }
import pureconfig.generic.auto._

// write config case class below
// case class SparkDSConfig()
case class SparkDSConfig(
    name: String,
    masterUrl: String,
    transactionFile: String
  )

// run with: sbt "runMain org.cscie88c.week10.SparkDSApplication"
object SparkDSApplication {

  val SPARK_DS_CONF_PATH = "org.cscie88c.spark-ds-application"

  // application main entry point
  def main(args: Array[String]): Unit = {
    implicit val conf: SparkDSConfig = readConfig()
    val spark = SparkUtils.sparkSession(conf.name, conf.masterUrl)
    val transactionDS = loadData(spark)
    val totalsByCategoryDS = transactionTotalsByCategory(spark, transactionDS)

    printTransactionTotalsByCategory(totalsByCategoryDS)
    spark.stop()
  }

  def readConfig(): SparkDSConfig =
    ConfigUtils.loadAppConfig[SparkDSConfig](SPARK_DS_CONF_PATH)

  def loadData(
      spark: SparkSession
    )(implicit
      conf: SparkDSConfig
    ): Dataset[CustomerTransaction] = {
    import spark.implicits._
    spark
      .sparkContext
      .textFile(conf.transactionFile)
      .map(CustomerTransaction(_))
      .collect {
        case Some(ct) => ct
      }
      .toDS()
  }

  def transactionTotalsByCategory(
      spark: SparkSession,
      transactions: Dataset[CustomerTransaction]
    ): Dataset[(String, Double)] = {
    import spark.implicits._
    transactions
      .groupByKey(_.transactionCategory)
      .mapValues(_.transactionAmount)
      .reduceGroups(_ + _)
  }

  def printTransactionTotalsByCategory(ds: Dataset[(String, Double)]): Unit =
    ds.show()
}
