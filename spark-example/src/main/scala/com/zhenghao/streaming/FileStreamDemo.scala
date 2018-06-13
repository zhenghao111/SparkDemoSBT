package com.zhenghao.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object FileStreamDemo {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("FileStreamDemo")

    val ssc = new StreamingContext(conf, Seconds(20))

    val file = ssc.fileStream("file:///Users/zhenghao/Documents/Workspace/IDEA/SparkDemoSBT/input")

    file.print()

    ssc.start()
    ssc.awaitTermination()
  }

}
