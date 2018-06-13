package com.zhenghao.sql

import org.apache.spark.sql.{Row, SparkSession}

object RDDtoDFExample2 {

  case class Person(name: String, age: Long)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName("Spark SQL Example")
      .getOrCreate()

    // RDD -> DF
    // 方法二，用用编程方法指定Schema
    import org.apache.spark.sql.types._

    val peopleRDD = spark.sparkContext.textFile("src/main/resources/people.txt")

    // schema
    val schemaString = "name age"
    val fields = schemaString.split(" ")
        .map(fieldName => StructField(fieldName, StringType, nullable = true))
    val schema = StructType(fields)

    // RDD -> Row
    val rowRDD = peopleRDD.map(_.split(","))
        .map(attributes => Row(attributes(0), attributes(1).trim()))

    // 把schema应用到RDD
    val peopleDF = spark.createDataFrame(rowRDD, schema)

    peopleDF.createOrReplaceTempView("people")
    spark.sql("select * from people").show()

    spark.stop()
  }

}
