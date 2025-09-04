#!/bin/bash
#exec java -jar /app/app.jar
java -jar /app/app.jar & pid="$!"
handle_sigterm() {
  kill $pid
  wait $pid
}
trap handle_sigterm SIGTERM
wait