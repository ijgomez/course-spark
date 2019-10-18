#!/bin/sh

DIRNAME=`dirname "$0"`

COURSE_WORKSPACE="$DIRNAME/workspace"

# Read an user running configuration file
if [ "x$RUN_CONF" = "x" ]; then
    RUN_CONF="$DIRNAME/spark-course.conf"
fi
if [ -r "$RUN_CONF" ]; then
    . "$RUN_CONF"
fi

#set COURSE_SCRIPTS=%DIRNAME%\scripts\win
#set M2_HOME=%MAVEN_HOME%
#set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%HADOOP_HOME%\bin;%SPARK_HOME%\bin;%COURSE_SCRIPTS%;%PATH%

java -version

cd "$UserDir"

#cd $COURSE_WORKSPACE