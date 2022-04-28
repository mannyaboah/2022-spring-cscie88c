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
  val KafkaConfPath = "com.example.akka.kafka.producer"

  def main(args: Array[String]): Unit = {
    logger.info("[XXXX]: Start of AlpakkaApp")
    val appSettings: ProducerSettings[String, String] = producerSettings(
      KafkaConfPath
    )
    logger.info(s"[settings]: $appSettings")
    runPipeline(appSettings)
    logger.info("[XXXX]: Stopped AlpakkaApp")
  }

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

  def producerPipeline(
      settings: ProducerSettings[String, String],
      topic: String
    ): RunnableGraph[Future[Done]] = {
    val kafkProducerPipeline: RunnableGraph[Future[Done]] =
      transactionsSource
        .map(_.toString)
        .via(producerRecordFlow(topic))
        .toMat(Producer.plainSink(settings))(Keep.right)
    kafkProducerPipeline
  }

}
