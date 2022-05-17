package org.cscie88c.week11

import cats.implicits._
import com.typesafe.scalalogging.{ LazyLogging }
import org.apache.spark.sql.{ Dataset }
import org.apache.spark.sql.SparkSession
import org.cscie88c.config.{ ConfigUtils }
import org.cscie88c.utils.SparkUtils
import pureconfig.generic.auto._

case class SparkAverageTransactionAggregateJobConfig(
    name: String,
    masterUrl: String,
    inputPathTransaction: String,
    inputPathResponse: String,
    outputPathTransaction: String,
    outputPathResponseTransaction: String
  )

object SparkAverageTransactionAggregateJob extends LazyLogging {

  def main(args: Array[String]): Unit = {
    logger.info("XXXX: Start of Advanced SparkApp")
    implicit val appSettings =
      ConfigUtils.loadAppConfig[SparkAverageTransactionAggregateJobConfig](
        "org.cscie88c.spark-advanced-application"
      )
    val spark = SparkUtils.sparkSession(appSettings.name, appSettings.masterUrl)
    logger.info(s"settings: $appSettings")
    runJob(spark)
    spark.stop()
    logger.info("XXXX: Stopped Advanced SparkApp")
  }

  def runJob(
      spark: SparkSession
    )(implicit
      conf: SparkAverageTransactionAggregateJobConfig
    ): Unit = {
    val transactionDS: Dataset[RawTransaction] = loadTransactionData(spark)
    val responseDS: Dataset[RawResponse] = loadCampaignResponseData(spark)
    val averageTransactionById: Map[String, AverageTransactionAggregate] =
      aggregateDataWithMonoid(transactionDS)
    val customersInCampaign: Dataset[RawTransaction] =
      joinTransactionAndResponseData(responseDS, transactionDS)
    val averageTransactionForCampaign
        : Map[String, AverageTransactionAggregate] = aggregateDataWithMonoid(
      customersInCampaign
    )
    saveAverageTransactionByCustomerId(
      spark,
      averageTransactionById,
      conf.outputPathTransaction
    )
    saveAverageTransactionByCustomerId(
      spark,
      averageTransactionForCampaign,
      conf.outputPathResponseTransaction
    )
    saveAverageTransactionAsParquet(
      spark,
      averageTransactionById,
      conf.outputPathTransaction
    )
  }

  def loadTransactionData(
      spark: SparkSession
    )(implicit
      conf: SparkAverageTransactionAggregateJobConfig
    ): Dataset[RawTransaction] = {
    import spark.implicits._
    spark
      .read
      .format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(conf.inputPathTransaction)
      .as[RawTransaction]
  }

  def loadCampaignResponseData(
      spark: SparkSession
    )(implicit
      conf: SparkAverageTransactionAggregateJobConfig
    ): Dataset[RawResponse] = {
    import spark.implicits._
    spark
      .read
      .format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(conf.inputPathResponse)
      .as[RawResponse]
  }

  def aggregateDataWithMonoid(
      transactionDS: Dataset[RawTransaction]
    ): Map[String, AverageTransactionAggregate] = {
    import transactionDS.sparkSession.implicits._
    transactionDS
      .map(trans =>
        Map(trans.customer_id -> AverageTransactionAggregate(trans))
      )
      .reduce(_ |+| _)
  }

  def joinTransactionAndResponseData(
      responseDS: Dataset[RawResponse],
      transactionDS: Dataset[RawTransaction]
    ): Dataset[RawTransaction] = {
    import responseDS.sparkSession.implicits._
    transactionDS
      .joinWith(
        responseDS.filter(_.response == 1),
        transactionDS("customer_id") === responseDS("customer_id")
      )
      .map {
        case (rawTran, rawRes) =>
          RawTransaction(
            rawRes.customer_id,
            rawTran.trans_date,
            rawTran.tran_amount
          )
      }
  }

  def saveAverageTransactionByCustomerId(
      spark: SparkSession,
      transactionsById: Map[String, AverageTransactionAggregate],
      path: String
    ): Unit = {
    import spark.implicits._
    transactionsById
      .map(s => WritableRow(s._1, s._2.totalAmount))
      .toList
      .toDF()
      .coalesce(1)
      .write
      .format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .mode("overwrite")
      .save(path)
  }

  def saveAverageTransactionAsParquet(
      spark: SparkSession,
      transactionsById: Map[String, AverageTransactionAggregate],
      path: String
    ): Unit = {
    import spark.implicits._
    transactionsById
      .map(s => WritableRow(s._1, s._2.totalAmount))
      .toList
      .toDF()
      .coalesce(1)
      .write
      .format("parquet")
      .option("header", "true")
      .option("inferSchema", "true")
      .mode("overwrite")
      .save(path)
  }
}
