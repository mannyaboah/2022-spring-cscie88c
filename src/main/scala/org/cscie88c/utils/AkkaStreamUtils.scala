package org.cscie88c.utils

import akka.stream._
import akka.stream.scaladsl._
import akka.{ Done, NotUsed }
import akka.actor.ActorSystem
import scala.concurrent._
import scala.concurrent.duration._
import com.typesafe.config.Config
import akka.kafka.{ProducerSettings, ConsumerSettings}
import org.apache.kafka.common.serialization.{StringSerializer, ByteArrayDeserializer, StringDeserializer}

object AkkaStreamUtils {
  def actorSystemInstance(
      name: String
    ): (ActorSystem, ActorMaterializer, ExecutionContext) = {
    val akkaSystem = ActorSystem(s"$name-Actor-System")
    val akkaMaterializer = ActorMaterializer()(akkaSystem)
    val akkaStreamsEC = akkaSystem.dispatcher
    (akkaSystem, akkaMaterializer, akkaStreamsEC)
  }

  def getAkkaSettings(actorSystem: ActorSystem, settingsPath: String): Config =
    actorSystem.settings.config.getConfig(settingsPath)

  object defaultActorSystem {

    implicit val (
      akkaStreams1System,
      akkaStreams1Materializer,
      akkaStreams1EC
    ) = actorSystemInstance("Default-Actor-System")

    def shutdown() =
      akkaStreams1System.terminate()

    def producerSettings(settingsPath: String): ProducerSettings[String,String] = {
      val akkaSettings = getAkkaSettings(akkaStreams1System, settingsPath)
      ProducerSettings(akkaSettings,new StringSerializer, new StringSerializer)
    }

    def consumerSettings(settingsPath: String): ConsumerSettings[String,String] = {
      val akkaSettings = getAkkaSettings(akkaStreams1System, settingsPath)
      ConsumerSettings(akkaSettings,new StringDeserializer, new StringDeserializer)
    }

  }

}
