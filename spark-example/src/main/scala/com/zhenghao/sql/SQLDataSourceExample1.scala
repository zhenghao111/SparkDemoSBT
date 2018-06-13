package com.zhenghao.sql

import org.apache.spark.sql.SparkSession

object SQLDataSourceExample1 {

  case class Person(name: String, age: Long)


  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
//      .master("local[2]")
      .appName("Spark SQL data sources example")
      .getOrCreate()

    // load save
//    val df = spark.read.load("src/main/resources/users.parquet")
//    df.show()
//    df.select("name", "favorite_color").write.save("src/main/resources/namesAndFavColors.parquet")

    // 手动指定选项
//    val df = spark.read.format("json").load("src/main/resources/people.json")
//    df.select("name", "age").write.format("parquet").save("namesAndAges.parquet")

//    val peopleDFCsv = spark.read.format("csv")
//      .option("sep", ";")
//      .option("inferSchema", "true")
//      .option("header", "true")
//      .load("src/main/resources/people.csv")
//    peopleDFCsv.show()

    // 直接在文件上运行SQL
//    val result = spark.sql("select * from parquet.`src/main/resources/users.parquet`")
//    result.show()

    //持久表
//    val df = spark.read.format("json").load("src/main/resources/people.json")
//    df.write.option("path", "src/main/resources/table").saveAsTable("t")

    // Bucket分桶、排序、分区
//    val df = spark.read.format("json").load("src/main/resources/people.json")
//    df.write.bucketBy(42, "name").sortBy("age").saveAsTable("people_bucketed")
//    val df = spark.read.load("src/main/resources/users.parquet")
//    df.write.partitionBy("favorite_color").format("parquet").save("namesPartByColor.parquet")
    // schema merging
//    val df1 = spark.sparkContext.makeRDD(1 to 5).map(i => (i, i * i)).toDF("value", "square")
//    df1.write.parquet("src/main/resources/schemamegre/key=1")
//    val df2 = spark.sparkContext.makeRDD(6 to 10).map(i => (i, i * i * i)).toDF("value", "cube")
//    df2.write.parquet("src/main/resources/schemamegre/key=2")
//
//    val mergeDF = spark.read.option("mergeSchema", "true").option("basePath", "src/main/resources/schemamegre").parquet("src/main/resources/schemamegre")
//    mergeDF.printSchema()
//    root
//    |-- value: integer (nullable = true)
//    |-- square: integer (nullable = true)
//    |-- cube: integer (nullable = true)
//    |-- key: integer (nullable = true)

    // JSON
    import spark.implicits._
    val jsonDF = spark.read.json("./data/people.json")
    jsonDF.write.parquet("./data/jsonToParquet.parquet")

    // 从字符串的JSON对象创建DF
//    val otherPeopleDataset = spark.createDataset(
//      """{"name":"Yin","address":{"city":"Columbus","state":"Ohio"}}""" :: Nil)
//    val otherPeople = spark.read.json(otherPeopleDataset)
//    otherPeople.show()




    spark.stop()
  }

}
