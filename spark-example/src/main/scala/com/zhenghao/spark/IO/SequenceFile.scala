package com.zhenghao.spark.IO
import org.apache.spark.sql.SparkSession
object SequenceFile extends App {

  val spark = SparkSession
    .builder
    .appName("")
    //    .master("spark://node:7077")
    .master("local[10]")
    .getOrCreate()


  val pairRDD = spark.sparkContext.parallelize(List(("a", 1), ("b", 2)))
//  pairRDD.saveAsSequenceFile("./data/sequencefile")

  val pairRDD2 = spark.sparkContext.sequenceFile[String, Int]("./data/sequencefile")
  pairRDD2.foreach(println)

}
