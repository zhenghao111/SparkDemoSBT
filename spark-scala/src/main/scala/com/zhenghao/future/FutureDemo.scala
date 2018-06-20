package com.zhenghao.future

import java.time.LocalTime

import scala.concurrent._
import ExecutionContext.Implicits.global

object FutureDemo {

  def main(args: Array[String]): Unit = {
    Future {
      Thread.sleep(10000)
      println(s"in the future ${LocalTime.now}")
    }

    println(s"present ${LocalTime.now}")
  }

}
