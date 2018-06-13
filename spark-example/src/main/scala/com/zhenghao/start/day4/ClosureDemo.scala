package com.zhenghao.start.day4

import org.apache.spark.{SparkConf, SparkContext}

object ClosureDemo {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("ClosureDemo").setMaster("local[3]")
    val sc = new SparkContext(conf)

    var counter = 0
    val data = Array(1, 2, 3,4)
    val rdd = sc.parallelize(data)
    rdd.foreach(x => counter += x)
    println(counter)



  }

}
