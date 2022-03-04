#!/bin/bash

if [[ -n "$(netstat -ano | grep tcp | grep 8080)" ]];
then
  echo "Server is running"
  fuser -k -n tcp 8080
fi