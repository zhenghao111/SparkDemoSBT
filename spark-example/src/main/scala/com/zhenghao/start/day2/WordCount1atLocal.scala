package com.zhenghao.start.day2

import org.apache.spark.{SparkConf, SparkContext}

object WordCount1atLocal {
  def main(args: Array[String]): Unit = {
    val inputFile = "input/README.md"

    val conf = new SparkConf().setAppName("WordCount1").setMaster("local[2]")
    val sc = new SparkContext(conf)

    // textFile是RDD
    val textFile = sc.textFile(inputFile)
    val words = textFile.flatMap(line => line.split(" "))
    val wordParis = words.map(word => (word, 1))
    val wordCount = wordParis.reduceByKey((a, b) => a + b)
    wordCount.foreach(println)
  }

}
