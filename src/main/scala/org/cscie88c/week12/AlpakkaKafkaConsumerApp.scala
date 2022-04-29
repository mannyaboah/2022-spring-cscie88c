package org.cscie88c.week12

import akka.stream.scaladsl._
import akka.{ Done }
import scala.concurrent._
import org.cscie88c.utils.AkkaStreamUtils
import akka.kafka.{ ConsumerSettings }
import com.typesafe.scalalogging.{ LazyLogging }
import scala.util.{ Failure, Success }
import KafkaConsumerStages._

// run with: sbt "runMain org.cscie88c.week12.AlpakkaKafkaConsumerApp"
object AlpakkaKafkaConsumerApp extends LazyLogging {
  
  import AkkaStreamUtils.defaultActorSystem._

  val MyTopic = "orders"
  val KafkaConfPath = "com.example.akka.kafka.consumer"

  // main entry point
  def main(args: Array[String]): Unit = {
    logger.info("[XXXX]: Start of AlpakkaApp")
    // read configuration and define serializers and deserializers
    val appSettings: ConsumerSettings[String, String] = consumerSettings(
      KafkaConfPath
    )
    logger.info(s"[settings]: $appSettings")
    runPipeline(appSettings) // run the data processing pipeline
    logger.info("[XXXX]: Stopped AlpakkaApp")
  }

  // run the pipeline on the default actor system
  def runPipeline(settings: ConsumerSettings[String, String]): Unit = {
    import AkkaStreamUtils.defaultActorSystem._

    val pipeline: RunnableGraph[Future[Done]] =
      consumerPipeline(settings, MyTopic)

    pipeline.run().onComplete {
      case Success(_) =>
        logger.info("[XXXX]: Done")
        shutdown()
      case Failure(ex) =>
        logger.error(s"[XXXX]: Failed: ${ex.getMessage}")
        shutdown()
    }
  }

  // kafka data processing pipeline
  def consumerPipeline(settings: ConsumerSettings[String, String], topic: String): RunnableGraph[Future[Done]] = {
    kafkaSource(settings, topic) // create the source using factory method
      .via(recordToStringFlow) // transform ConsumerRecord to string
      .via(customerTransactionFlow) // transform to transaction data type
      .via(averageTransactionFlow) // average aggregate
      .toMat(printSink)(Keep.right) // connect to the sink
  }

}
