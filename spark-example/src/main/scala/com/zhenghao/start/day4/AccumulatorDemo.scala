package com.zhenghao.start.day4

import org.apache.spark.{SparkConf, SparkContext}

object AccumulatorDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("AccumulatorDemo").setMaster("local[3]")
    val sc = new SparkContext(conf)

    val accum = sc.longAccumulator("accumulator")
    println(accum)

    val rdd = sc.parallelize(Array(1,2,3,4))
    rdd.foreach(x => accum.add(x))
    println(s"accum=${accum.value}")
  }

}
