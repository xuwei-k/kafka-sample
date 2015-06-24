#!/usr/bin/env bash

KAFKA="kafka_2.11-0.8.2.1"

if [ ! -d "kafka-install/$KAFKA" ]; then
  mkdir -p kafka-install &&
  cd kafka-install &&
  curl -L http://www.us.apache.org/dist/kafka/0.8.2.1/$KAFKA.tgz | tar xvz
fi

