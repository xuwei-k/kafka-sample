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

object KafkaProperties {
  val zkConnect: String = "127.0.0.1:2181"
  val groupId: String = "group1"
  val topic: String = "topic1"
  val kafkaServerURL: String = "localhost"
  val kafkaServerPort: Int = 9092
  val kafkaProducerBufferSize: Int = 64 * 1024
  val connectionTimeOut: Int = 100000
  val reconnectInterval: Int = 10000
  val topic2: String = "topic2"
  val topic3: String = "topic3"
  val clientId: String = "SimpleConsumerDemoClient"
}
