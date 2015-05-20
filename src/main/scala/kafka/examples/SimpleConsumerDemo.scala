/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kafka.examples

import kafka.api.FetchRequestBuilder
import kafka.consumer.SimpleConsumer
import kafka.message.ByteBufferMessageSet

object SimpleConsumerDemo {

  private def printMessages(messageSet: ByteBufferMessageSet): Unit = {
    for (messageAndOffset <- messageSet) {
      val payload = messageAndOffset.message.payload
      val bytes = new Array[Byte](payload.limit)
      payload.get(bytes)
      println(new String(bytes, "UTF-8"))
    }
  }

  private def generateData(): Unit = {
    val producer2 = new Producer(KafkaProperties.topic2)
    producer2.start()
    val producer3 = new Producer(KafkaProperties.topic3)
    producer3.start()
    try {
      Thread.sleep(1000)
    }
    catch {
      case e: InterruptedException =>
        e.printStackTrace()
    }
  }

  def main(args: Array[String]) {
    generateData()

    val simpleConsumer = new SimpleConsumer(
      KafkaProperties.kafkaServerURL,
      KafkaProperties.kafkaServerPort,
      KafkaProperties.connectionTimeOut,
      KafkaProperties.kafkaProducerBufferSize,
      KafkaProperties.clientId
    )
    println("Testing single fetch")
    val req1 = new FetchRequestBuilder().clientId(KafkaProperties.clientId).addFetch(KafkaProperties.topic2, 0, 0L, 100).build()
    val fetchResponse1 = simpleConsumer.fetch(req1)
    printMessages(fetchResponse1.messageSet(KafkaProperties.topic2, 0))

    println("Testing single multi-fetch")
    val topicMap = List(
      KafkaProperties.topic2 -> List(0),
      KafkaProperties.topic3 -> List(0)
    )

    val req2 = new FetchRequestBuilder().clientId(KafkaProperties.clientId).addFetch(KafkaProperties.topic2, 0, 0L, 100).addFetch(KafkaProperties.topic3, 0, 0L, 100).build()
    val fetchResponse2 = simpleConsumer.fetch(req2)
    var fetchReq = 0
    topicMap.foreach{ case (topic, offsets) =>
      offsets.foreach{ offset =>
        fetchReq += 1
        println("Response from fetch request no: " + fetchReq)
        printMessages(fetchResponse2.messageSet(topic, offset))
      }
    }
  }
}
