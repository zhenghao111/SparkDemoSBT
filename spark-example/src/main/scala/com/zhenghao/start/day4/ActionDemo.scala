package com.zhenghao.start.day4

import org.apache.spark.{SparkConf, SparkContext}

object ActionDemo {

  def main(args: Array[String]): Unit = {
    val inputFile = "input/README.md"

    val conf = new SparkConf().setAppName("WordCount1").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile(inputFile)

    val lineLengths = textFile.map(line => line.length)
    val totalLength = lineLengths.reduce((a, b) => a+b)
    print(totalLength)
  }

}
