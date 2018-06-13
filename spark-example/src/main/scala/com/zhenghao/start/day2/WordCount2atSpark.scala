package com.zhenghao.start.day2

import org.apache.spark.sql.SparkSession

object WordCount2atSpark {

  def main(args: Array[String]): Unit = {
    val inputFile = "./data/README.md"

    val spark = SparkSession.builder()
                            .appName("WordCount2")
//                            .master("local[2]")
                            .getOrCreate()
    // textFile是Dataset，推荐使用Dataset
    val textFile = spark.read.textFile(inputFile)

    val words = textFile.rdd.flatMap(line => line.split(" "))
    val wordParis = words.map(word => (word, 1))
    val wordCount = wordParis.reduceByKey((a, b) => a + b)
    wordCount.foreach(println)

  }

}
