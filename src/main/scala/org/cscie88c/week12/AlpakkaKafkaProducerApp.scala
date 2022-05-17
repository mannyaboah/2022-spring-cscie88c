package org.cscie88c.week12

import akka.stream.scaladsl._
import akka.{ Done }
import scala.concurrent._
import org.cscie88c.utils.AkkaStreamUtils
import akka.kafka.{ ProducerSettings }
import akka.kafka.scaladsl._
import com.typesafe.scalalogging.{ LazyLogging }
import scala.util.{ Failure, Success }
import KafkaProducerStages._

// run with: sbt "runMain org.cscie88c.week12.AlpakkaKafkaProducerApp"
object AlpakkaKafkaProducerApp extends LazyLogging{
  
  import AkkaStreamUtils.defaultActorSystem._

  val MyTopic = "orders"
  val KafkaConfPath = "org.cscie88c.akka.kafka.producer"

  // main entry point
  def main(args: Array[String]): Unit = {
    logger.info("[XXXX]: Start of AlpakkaApp")
    // read configuration and define serializers and deserializers
    val appSettings: ProducerSettings[String, String] = producerSettings(
      KafkaConfPath
    )
    logger.info(s"[settings]: $appSettings")
    runPipeline(appSettings) // run the data processing pipeline
    logger.info("[XXXX]: Stopped AlpakkaApp")
  }

  // run the pipeline on the default actor system
  def runPipeline(settings: ProducerSettings[String, String]): Unit = {
    import AkkaStreamUtils.defaultActorSystem._

    val pipeline: RunnableGraph[Future[Done]] =
      producerPipeline(settings, MyTopic)

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
  def producerPipeline(
      settings: ProducerSettings[String, String],
      topic: String
    ): RunnableGraph[Future[Done]] = {
    val kafkProducerPipeline: RunnableGraph[Future[Done]] =
      transactionsSource // create the source
        .map(_.toString) // transform to string
        .via(producerRecordFlow(topic)) // transform to producer record
        .toMat(Producer.plainSink(settings))(Keep.right) // connect to the sink created from factory method
    kafkProducerPipeline
  }

}
