#!/bin/sh

DIRNAME=`dirname "$0"`

COURSE_WORKSPACE="$DIRNAME/workspace"

# Read an user running configuration file
if [ "x$RUN_CONF" = "x" ]; then
    RUN_CONF="$DIRNAME/spark-environment.sh"
fi
if [ -r "$RUN_CONF" ]; then
    . "$RUN_CONF"
fi

export COURSE_SCRIPTS=$DIRNAME/scripts/linux
export M2_HOME=$MAVEN_HOME
export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$HADOOP_HOME/bin:$SPARK_HOME/bin:$COURSE_SCRIPTS:$PATH

java -version

cd $COURSE_WORKSPACE