package com.zhenghao.start.day3

import org.apache.spark.sql.SparkSession

// IDEA向Spark standalone集群提交运行
// 模块配置-打包，勾选编译时打包，META-INF放在scala下，和包同级
// 创建空的，依赖要不要添加进去，服务器是有的
// 代码里设置jar包、master
// 注意：本地的scala、spark版本要和服务器的一致
//

object SimpleAppToSpark {

  def main(args: Array[String]): Unit = {
    val inputFile = "/home/zhenghao/deployment/data/README.md" // 文件是？？
    val spark = SparkSession
      .builder
      .appName("Simple Application To Spark")
      .master("spark://node:7077")
      .config("spark.jars", "/Users/zhenghao/Documents/Workspace/IDEA/SparkDemoSBT/out/artifacts/spark_example/spark-example.jar")
      .getOrCreate()
//    spark.sparkContext.getConf.setJars(List("/Users/zhenghao/Documents/Workspace/IDEA/SparkDemoSBT/out/artifacts/spark_example/spark-example.jar"))


//    val textFile = spark.read.textFile(inputFile).cache()
//    val numAs = textFile.filter(line => line.contains("a")).count()
//    val numBs = textFile.filter(line => line.contains("b")).count()
//    println(f"Lines with a: $numAs, Lines with b: $numBs")

    print("submit to spark from IDEA")
    spark.stop()
  }

}
