package com.zhenghao.sql

import org.apache.spark.sql.SparkSession

object DatasetExample1 {

  case class Person(name: String, age: Long)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName("Spark SQL Example")
      .getOrCreate()


    import spark.implicits._

    // Seq -> DS
    val caseClassDS = Seq(Person("zhenghao", 27)).toDS()
    caseClassDS.show()

    val primitveDS = Seq(1, 2, 3).toDS()
    // DS -> Array
    primitveDS.map(_ + 1).collect()

    // DF -> DS
    val peopleDS = spark.read.json("src/main/resources/people.json").as[Person]
    peopleDS.show()

    spark.stop()
  }

}
