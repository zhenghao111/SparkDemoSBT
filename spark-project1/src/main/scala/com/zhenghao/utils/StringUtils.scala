package com.zhenghao.utils

object StringUtils {

  def isEmpty(string: String): Boolean = {
    string match {
      case null => true
      case "" => true
      case _ => false
    }

  }

  def isNotEmpty(string: String): Boolean = {
    string != null && !string.equals("")
  }

  def trimComma(string: String): String = {
    if (string.startsWith(","))
      string.substring(1)
    if (string.endsWith(","))
      string.substring(0, string.length - 1)
    string
  }

  def fulfuill(str:String): String = {
    if (str.length == 2) str else  "0" + str
  }

  def getFieldFromConcatString(str: String, delimiter: String, field: String): String = {

    val fields = str.split(delimiter)

    for (concatField <- fields) {
      val Array(key, value) = concatField.split("=")
      if (key eq field) value
    }
    ""
  }

  def setFieldInConcatString(str: String, delimiter: String, field: String, newValue: String) = {
    val fields = str.split(delimiter)
//    注意for循环 break的写法
//    breakable {
//      for(i <- 0 until fields.length) {
//        val Array(key, value) = fields(i).split("=")
//        if (key.equals(field)) {
//          val newConcatField = key + "=" + newValue
//          fields(i) = newConcatField
//          break()
//        }
//      }
//    }
    val newFields = fields.map(_.split("=")).map{
          case Array(key, value) =>
            if (key.equals(field)) key+ "=" + newValue else key + "=" + value
        }
    newFields.mkString("|")
  }


  def main(args: Array[String]): Unit = {
    var str = "a=1,b=2"
    println(setFieldInConcatString(str, ",", "a", "2"))
  }

}
