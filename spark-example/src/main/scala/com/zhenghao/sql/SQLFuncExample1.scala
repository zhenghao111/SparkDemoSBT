package com.zhenghao.sql

import org.apache.spark.sql.SparkSession

object SQLFuncExample1 {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName("Spark SQL Example")
      .getOrCreate()

    // 创建一个DataFrame
    val df = spark.read.json("src/main/resources/people.json")

    // 注册临时视图
    df.createTempView("people")
    val sqlDF = spark.sql("select * from people")
    sqlDF.show()
    // Error
//    spark.newSession().sql("select * from people").show()

    // 全局临时视图
    df.createGlobalTempView("people")
    spark.sql("select * from global_temp.people").show()
    spark.newSession().sql("select * from global_temp.people").show()

    spark.stop()
  }

}
