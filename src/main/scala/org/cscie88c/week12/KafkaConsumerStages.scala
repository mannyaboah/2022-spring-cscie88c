package org.cscie88c.week12

import akka.stream.scaladsl._
import akka.{ Done, NotUsed }
import scala.concurrent._
import scala.concurrent.duration._
import cats._
import org.apache.kafka.clients.consumer.ConsumerRecord
import akka.kafka.{ ConsumerSettings }
import akka.kafka.scaladsl._
import akka.kafka.Subscriptions

object KafkaConsumerStages {

  def kafkaSource(settings: ConsumerSettings[String, String], topic: String) = {
    val kafkaSource: Source[ConsumerRecord[String, String], Consumer.Control] =
      Consumer
        .plainSource(settings, Subscriptions.topics(topic))
    kafkaSource
  }

  val recordToStringFlow
      : Flow[ConsumerRecord[String, String], String, NotUsed] =
    Flow[ConsumerRecord[String, String]].map { record =>
      record.value.toString
    }

  val customerTransactionFlow: Flow[String, CustomerTransaction, NotUsed] =
    Flow[String]
      .map(CustomerTransaction(_))
      .collect({
        case Some(transaction) => transaction
      })

  val averageTransactionFlow
      : Flow[CustomerTransaction, AverageTransactionAggregate, NotUsed] =
    Flow[CustomerTransaction]
      .map(AverageTransactionAggregate(_))
      .groupedWithin(1000, 10 seconds)
      .map(Monoid.combineAll(_))

  def printSink[I]: Sink[I, Future[Done]] =
    Sink.foreach(v => println(s"record: $v"))

}
