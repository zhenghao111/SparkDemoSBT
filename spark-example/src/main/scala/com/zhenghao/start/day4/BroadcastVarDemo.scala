package com.zhenghao.start.day4

import org.apache.spark.{SparkConf, SparkContext}

object BroadcastVarDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("BroadcastVarDemo").setMaster("local[3]")
    val sc = new SparkContext(conf)

    val v = Array(1, 2, 3, 4)
    val broadcastVar = sc.broadcast(v)

    val rdd = sc.parallelize(Array(1, 2, 3, 4, 5))
    val newRDD = rdd.map(x => x + broadcastVar.value(1))

    newRDD.collect()
    newRDD.foreach(println)
  }
}
