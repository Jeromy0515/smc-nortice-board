#!/bin/bash

BUILD_PATH=$(ls /home/ec2-user/*.jar)
JAR_NAME=$(basename $BUILD_PATH)
DEPLOY_PATH=/home/ec2-user/

nohup java -jar $DEPLOY_PATH$JAR_NAME > /dev/null 2> /dev/null < /dev/null &