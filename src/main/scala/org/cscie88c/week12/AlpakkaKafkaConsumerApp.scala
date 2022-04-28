package org.cscie88c.week12

// import akka.stream._
import akka.stream.scaladsl._
import akka.{ Done }
// import akka.util.ByteString
// import akka.actor.ActorSystem
import scala.concurrent._
// import scala.concurrent.duration._
import org.cscie88c.utils.AkkaStreamUtils
// import cats.implicits._
import akka.kafka.{ ConsumerSettings }
// import org.apache.kafka.common.serialization.{ StringDeserializer }
// import akka.kafka.scaladsl._
// import scala.util.Random
import com.typesafe.scalalogging.{ LazyLogging }
import scala.util.{ Failure, Success }
import KafkaConsumerStages._

// run with: sbt "runMain org.cscie88c.week12.AlpakkaKafkaConsumerApp"
object AlpakkaKafkaConsumerApp extends LazyLogging {
  
  import AkkaStreamUtils.defaultActorSystem._

  val MyTopic = "orders"
  val KafkaConfPath = "com.example.akka.kafka.consumer"

  def main(args: Array[String]): Unit = {
    logger.info("[XXXX]: Start of AlpakkaApp")
    val appSettings: ConsumerSettings[String, String] = consumerSettings(
      KafkaConfPath
    )
    logger.info(s"[settings]: $appSettings")
    runPipeline(appSettings)
    logger.info("[XXXX]: Stopped AlpakkaApp")
  }

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

  def consumerPipeline(settings: ConsumerSettings[String, String], topic: String): RunnableGraph[Future[Done]] = {
    kafkaSource(settings, topic)
      .via(recordToStringFlow)
      .via(customerTransactionFlow)
      .via(averageTransactionFlow)
      .toMat(printSink)(Keep.right)
  }

}
