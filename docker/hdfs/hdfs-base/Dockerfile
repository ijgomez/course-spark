FROM openjdk:8-jre-alpine

RUN apk -U update && \
    apk add bash curl wget supervisor && \
    apk upgrade && \
    curl http://mirrors.ukfast.co.uk/sites/ftp.apache.org/hadoop/common/hadoop-2.7.7/hadoop-2.7.7.tar.gz | tar xvz && \
    mv hadoop-2.7.7 /var/hadoop && \
    rm -rf /var/hadoop/share/doc && \
    mkdir -p /var/hadoop_data/hdfs/namenode && \
    mkdir -p /var/hadoop_data/hdfs/datanode


