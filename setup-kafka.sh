#!/usr/bin/env bash

KAFKA="kafka_2.11-0.9.0.1"

if [ ! -d "kafka-install/$KAFKA" ]; then
  mkdir -p kafka-install &&
  cd kafka-install &&
  curl -L http://www.us.apache.org/dist/kafka/0.9.0.1/$KAFKA.tgz | tar xvz
fi

