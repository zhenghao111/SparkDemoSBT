package com.zhenghao.sql

import org.apache.spark.sql.SparkSession

object RDDtoDFExample1 {

  case class Person(name: String, age: Long)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName("Spark SQL Example")
      .getOrCreate()

    import spark.implicits._

    // RDD -> DF
    // 方法一，用case class反射推断Schema

    val sc = spark.sparkContext
    val textRDD = sc.textFile("src/main/resources/people.txt")
    val peopleRDD = textRDD.map(_.split(","))
                           .map(line => Person(line(0), line(1).trim().toInt))
    val peopleDF = peopleRDD.toDF()
    peopleDF.show()

    // 注册临时表
    peopleDF.createOrReplaceTempView("people")
    val teenagersDF = spark.sql("select * from people where age between 13 and 19")
    teenagersDF.map(teenager => "Name:" + teenager(0)).show()
    teenagersDF.map(teenager => "Name:" + teenager.getAs[Int]("name")).show()

    // DF -> Array(Map(name, age),...)
    implicit val mapEncoder = org.apache.spark.sql.Encoders.kryo[Map[String, Any]]
    teenagersDF.map(teenager => teenager.getValuesMap[Any](List("name", "age"))).collect()




    spark.stop()
  }

}
