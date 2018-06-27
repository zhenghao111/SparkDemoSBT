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
    .getOrCreate()

  // [name, salary]
  val jsons = spark.read.json(inputFile)
  jsons.rdd.foreach(println)
//    [Michael,3000]
//    [Andy,4500]
//    [Justin,3500]
//    [Berta,4000]

  val csv = spark.read.csv("data/people.csv")
  csv.rdd.foreach(println)
//  [name;age;job]
//  [Jorge;30;Developer]
//  [Bob;32;Developer]

  // 文本读取，使用第三方json库再解析成case class SparkContext下面很多读写文件方法
  val jsons2 = spark.sparkContext.textFile(inputFile)
  val result = jsons2.flatMap(record => {
    try {
      //第三方的json库或者csv库
      Some()
    } catch {
      case e: Exception => None
    }
  }
  )


}
