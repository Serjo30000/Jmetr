#!/bin/sh
cd $(dirname $0)/.. || exit 1
cd dataService
DOCKER_BUILDKIT=1 docker build -f build-elements/Dockerfile -t serv/data_serv .
cd ..
cd apiService
DOCKER_BUILDKIT=1 docker build -f build-elements/Dockerfile -t serv/api_serv .
cd ..