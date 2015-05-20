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

import kafka.producer.KeyedMessage
import kafka.producer.ProducerConfig
import java.util.Properties

class Producer(val topic: String) extends Thread {

  override def run(): Unit = {
    var messageNo = 1
    while (true) {
      val messageStr = "Message_" + messageNo
      producer.send(new KeyedMessage(topic, messageStr))
      messageNo += 1
    }
  }

  private lazy val producer: kafka.producer.Producer[Int, String] = {
    val props = new Properties
    props.put("serializer.class", "kafka.serializer.StringEncoder")
    props.put("metadata.broker.list", "localhost:9092")
    new kafka.producer.Producer[Int, String](new ProducerConfig(props))
  }
}