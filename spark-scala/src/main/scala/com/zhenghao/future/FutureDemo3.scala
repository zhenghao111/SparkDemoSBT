package com.zhenghao.future

import java.time.LocalTime
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent._

object FutureDemo3 {

  def main(args: Array[String]): Unit = {

    val f1 = Future {
        Thread.sleep(10)
        42
      }

//      val result = Await.result(f1, 10.seconds)
//      println(result)

    Await.ready(f1, 10.seconds)
    val Some(t) = f1.value
    println(t)

  }

}
