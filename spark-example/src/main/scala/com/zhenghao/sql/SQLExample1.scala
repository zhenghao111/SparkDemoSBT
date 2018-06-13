package com.zhenghao.sql

import org.apache.spark.sql.SparkSession

object SQLExample1 {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("spark://node:7077")
      .appName("Spark SQL Example")
      .getOrCreate()

    // 创建一个DataFrame
    val df = spark.read.json("./data/people.json")

    // 打印DataFrame
    df.show()

    import spark.implicits._

    df.printSchema()
    df.select("name").show()
    df.select($"name", $"age" + 1).show()
    df.filter($"age" > 21).show()
    df.groupBy("age").count().show()

    spark.stop()
  }

}
