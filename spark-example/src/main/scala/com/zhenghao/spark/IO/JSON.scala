package com.zhenghao.spark.IO

import org.apache.spark.sql.SparkSession

case class Person(name: String, salary: Double)

object JSON extends App {

  val inputFile = "./data/employees.json"

  val spark = SparkSession
    .builder
    .appName("Simple Application To Spark")
    //    .master("spark://node:7077")
    .master("local[10]")
    .config("spark.jars", "/Users/zhenghao/Documents/Workspace/IDEA/SparkDemoSBT/out/artifacts/spark_project1/spark-project1.jar")
    .getOrCreate()

  // [name, salary]
  val jsons = spark.read.json(inputFile)
  jsons.rdd.foreach(println)

  // 文本读取，使用第三方json库再解析成case class
  val jsons2 = spark.read.textFile(inputFile)
  val result = jsons2.rdd.flatMap(record => {
    try {
      Some()
    } catch {
      case e: Exception => None
    }
  }
  )


}
