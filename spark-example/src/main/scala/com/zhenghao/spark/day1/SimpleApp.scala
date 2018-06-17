package com.zhenghao.spark.day1

import org.apache.spark.sql.SparkSession

object SimpleApp {

  def main(args: Array[String]): Unit = {
    // 配置了HADOOP_CONF_DIR，不指定file默认是hdfs，没有配置默认是file
//    val inputFile = "file:/home/zhenghao/deployment/data/README.md"
//    val inputFile = "/home/zhenghao/deployment/data/README.md"
    // 可以用相对路径，方便向集群提交运行时测试
    val inputFile = "./data/README.md"
    val spark = SparkSession
      .builder
      .appName("Simple Application")
//      .master("local[2]")
      .getOrCreate()

    val textFile = spark.read.textFile(inputFile).cache()
    val numAs = textFile.filter(line => line.contains("a")).count()
    val numBs = textFile.filter(line => line.contains("b")).count()
    println(f"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()

  }
}
