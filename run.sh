#!/bin/bash

mvn clean package jacoco:report

docker build -t twitter_extractor:v1 .

docker run -it -v $(pwd):/project twitter_extractor:v1
