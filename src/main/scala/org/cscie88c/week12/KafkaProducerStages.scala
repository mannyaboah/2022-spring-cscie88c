package org.cscie88c.week12

import akka.stream.scaladsl._
import akka.{ NotUsed }
import scala.concurrent.duration._
import cats.implicits._
import org.apache.kafka.clients.producer.ProducerRecord
import scala.util.Random

object KafkaProducerStages {
  
  // Sources
  val intSource: Source[Int, NotUsed] =
    Source(1 to 100)
  
  val randomIntsSource: Source[Int, NotUsed] = {
    val rnd = new Random()
    Source
      .fromIterator(() => LazyList.iterate(rnd.nextInt())(_ => rnd.nextInt()).iterator)
      .throttle(1, 1 second)
  }

  val transactionsSource: Source[CustomerTransaction, NotUsed] = {
    Source
      .fromIterator(() => DataGenerator.customerTransactionStream.iterator)
      .throttle(1, 1 second)
  }

  // Flow
  def producerRecordFlow(topic: String): Flow[String, ProducerRecord[String, String], NotUsed] =
    Flow[String].map(new ProducerRecord[String, String](topic, _))

}
