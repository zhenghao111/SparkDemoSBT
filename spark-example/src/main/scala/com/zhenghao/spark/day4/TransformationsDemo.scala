package com.zhenghao.spark.day4

import org.apache.spark.{SparkConf, SparkContext}

object TransformationsDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("TransformationsDemo").setMaster("local[3]")
    val sc = new SparkContext(conf)

    val data = Array(1,2,3,4,5,6,7,8,9,10,4,5,6,7)
    val rdd = sc.parallelize(data, 3)

    val sampleRDD = rdd.sample(true, 0.5, System.currentTimeMillis())
    sampleRDD.collect()
    sampleRDD.foreach(println)

    val distinctRDD = rdd.distinct()
    distinctRDD.collect()
    distinctRDD.foreach(println)

    val kvData = Array(("a", 2), ("a", 4), ("b", 3), ("c", 3), ("c", 4))
    val pairRDD = sc.parallelize(kvData, 3)
    val groupRDD = pairRDD.groupByKey()
    groupRDD.collect()
    groupRDD.foreach(println)
    println(s"${groupRDD.count()}")

  }

}
