package com.zhenghao.spark.day3

import org.apache.spark.sql.SparkSession

// IDEA向Spark standalone集群提交运行
// 模块配置-打包，勾选编译时打包，META-INF放在scala下，和包同级
// 创建空的，依赖要不要添加进去，服务器是有的
// 代码里设置jar包、master
// 注意：本地的scala、spark版本要和服务器的一致
// deployMode默认是client，driver运行在本机，结果都返回本机，
// -Dspark.submit.deployMode=cluster

object SimpleAppToSpark {

  def main(args: Array[String]): Unit = {
    // 1. 文件先放在各个节点可以访问的文件系统上，集群本地、HDFS、网络文件系统
    // 2. Driver程序中读取本地文件，分发到各个节点
    val inputFile = "/Users/zhenghao/Documents/Workspace/IDEA/SparkDemoSBT/data/README.md" // 文件是？？
    val spark = SparkSession
      .builder
      .appName("Simple Application To Spark")
      .master("spark://node:7077")
      .config("spark.jars", "/Users/zhenghao/Documents/Workspace/IDEA/SparkDemoSBT/out/artifacts/spark_example/spark-example.jar")
      .getOrCreate()
//    spark.sparkContext.getConf.setJars(List("/Users/zhenghao/Documents/Workspace/IDEA/SparkDemoSBT/out/artifacts/spark_example/spark-example.jar"))


    val textFile = spark.read.textFile(inputFile).cache()

    val numAs = textFile.filter(line => line.contains("a")).count()
    val numBs = textFile.filter(line => line.contains("b")).count()
    println(f"Lines with a: $numAs, Lines with b: $numBs")

//    print("submit to spark from IDEA")
    spark.stop()
  }

}
