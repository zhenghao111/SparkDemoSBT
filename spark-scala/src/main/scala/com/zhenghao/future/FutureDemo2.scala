package com.zhenghao.future

import java.time.LocalTime

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._

object FutureDemo2 {

  def main(args: Array[String]): Unit = {

    while (true) {
      Future {
        for (i <- 1 to 10) {
          print("A")
          Thread.sleep(10)
        }
      }

      Future {
        for (i <- 1 to 10) {
          print("B")
          Thread.sleep(10)
        }
      }
    }
  }

}
