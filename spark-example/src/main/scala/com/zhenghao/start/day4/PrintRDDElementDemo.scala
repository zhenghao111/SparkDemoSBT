package com.zhenghao.start.day4

import org.apache.spark.{SparkConf, SparkContext}

object PrintRDDElementDemo {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("PrintRDDElementDemo").setMaster("local[3]")
    val sc = new SparkContext(conf)

    val data = Array(1,2,3,4,5,6)
    val rdd = sc.parallelize(data, 3)

    // 不在驱动程序上，集群下不会显示
    rdd.foreach(println)

    // 在驱动程序上，可能会耗尽驱动程序内存
    rdd.collect().foreach(println)

    rdd.take(4).foreach(println)
  }

}
