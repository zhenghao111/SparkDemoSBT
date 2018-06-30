name := "SparkDemoSBT"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.2.1",
  "org.apache.spark" % "spark-sql_2.11" % "2.2.1",
  "org.apache.spark" % "spark-streaming_2.11" % "2.2.1",
  "org.apache.spark" % "spark-hive_2.11" % "2.2.1",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "junit" % "junit" % "4.12" % "test",
  "org.apache.hadoop" % "hadoop-client" % "2.7.6",
  "org.apache.spark" %% "spark-streaming-kafka" % "1.6.3",
  "mysql" % "mysql-connector-java" % "5.1.46",
  "org.json" % "json" % "20180130",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.9.6",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.6",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.9.6",
  "com.alibaba" % "fastjson" % "1.2.47"
)