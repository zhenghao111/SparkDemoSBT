package com.zhenghao.utils

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

object DateUtils {

  val TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  val DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd")

  def before(time1: String, time2: String): Boolean = {
    val datetime1 = TIME_FORMAT.parse(time1)
    val datetime2 = TIME_FORMAT.parse(time2)

    try {
      if (datetime1.before(datetime2))
        true
    } catch {
      case _: Exception => println("时间解析失败")
    }
    false
  }

  def after(time1: String, time2: String): Boolean = {

    try {
      val datetime1 = TIME_FORMAT.parse(time1)
      val datetime2 = TIME_FORMAT.parse(time2)
      if (datetime1.after(datetime2))
        true
    } catch {
      case _: Exception => println("时间解析失败")
    }
    false
  }

  def minus(time1: String, time2: String): Int = {
    try {
      val datetime1 = TIME_FORMAT.parse(time1)
      val datetime2 = TIME_FORMAT.parse(time2)
      val millisecond = datetime1.getTime() - datetime2.getTime()
      millisecond
    } catch {
      case _: Exception => println("时间解析失败")
    }
    0
  }

  def getDateHour(datetime: String): String = {
    val Array(date, time) = datetime.split(" ")
    val Array(hour, _, _) = time.split(":")
    date + "_" + hour
  }

  def getTodayDate: String = {
    DATE_FORMAT.format(new Date())
  }

  def getYesterdayDate: String = {
    val cal = Calendar.getInstance
    cal.setTime(new Date)
    cal.add(Calendar.DAY_OF_YEAR, -1)
    DATE_FORMAT.format(cal.getTime)

  }

  def formatDate(date: Date): Unit = {
    DATE_FORMAT.format(date)
  }

  def formatTime(data: Date)= {
    TIME_FORMAT.format(data)
  }

}
