package com.zhenghao.start.day4

import org.apache.spark.{SparkConf, SparkContext}

object KVPairRDDDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("KVPairRDDDemo").setMaster("local[3]")
    val sc = new SparkContext(conf)

    val inputFile = "input/README.md"
    val lines = sc.textFile(inputFile, 3)

    val pairs = lines.map(line => (line,1))
    val counts = pairs.reduceByKey((a, b) => a + b)
    counts.collect()
    counts.sortByKey()
    counts.foreach(println)
  }

}
