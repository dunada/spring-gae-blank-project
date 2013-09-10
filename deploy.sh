#!/bin/bash
#
# Usage:
# ./deploy.sh profile [dev|prod]
#
#
# Description: 
# This program deploys the app into the server. Following those steps:
# 1 - replace local configurations
# 2 - create the temporary structure
# 3 - deploy to GAE
#
BASE_DIR=./
NOW=$(date +"%y%m%d-%H%M%S")
TODAY=$(date)
ENV=$1
TEMP_DIR=../temp/

clear screen

if [ "$1" == "" ] 
then
  echo "Usage: ./deploy.sh profile [dev|prod]"
  exit
fi



#echo "-----------------------------------------------------------------------------------"
echo ">> 2 - Creating Temporary Structure - $TODAY"


mkdir -p $TEMP_DIR/$ENV-$NOW
cp -rf ./war $TEMP_DIR/$ENV-$NOW


cp -rf ./profile/$ENV/war/ $TEMP_DIR/$ENV-$NOW/war/



# echo "-----------------------------------------------------------------------------------"
echo ">> 3 - Deploy APP to GAE  @ $TODAY"
# echo "-----------------------------------------------------------------------------------"

appcfg.sh update $TEMP_DIR/$ENV-$NOW/war/




echo ">> 4 - Done"
echo "-----------------------------------------------------------------------------------"




