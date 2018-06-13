package com.zhenghao.streaming

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}


object NetworkWordCount {

  def main(args: Array[String]): Unit = {

//    val conf = new SparkConf().setMaster("spark://node:7077").setAppName("NetworkWordCount")
    val spark = SparkSession
      .builder
      .appName("NetworkWordCount")
      .getOrCreate()

    val ssc = new StreamingContext(spark.sparkContext, Seconds(20))

    // lines DStream
    val lines = ssc.socketTextStream("192.168.8.113", 9999)

    // DStream是streaming计算下的RDD，操作和RDD同名
    val words = lines.flatMap(line => line.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_ + _)

    // print不会触发真正的调度执行
    wordCounts.print()

    //正式启动j计算
    ssc.start()
    ssc.awaitTermination()
  }

}
