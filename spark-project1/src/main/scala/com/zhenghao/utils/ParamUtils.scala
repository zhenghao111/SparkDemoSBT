package com.zhenghao.utils

import com.alibaba.fastjson.JSONObject


object ParamUtils {

  def getTaskIdFromArgs(args: Array[String]): Double = {
    try {
      if (args != null && args.length > 0) args(0).toDouble
    }
    catch {
      case _: Exception => println("")
    }
    0

  }

  def getParam(jsonObject: JSONObject, field: String): String = {
    val jsonArray = jsonObject.getJSONArray(field)
    if (jsonArray != null && jsonArray.size()>0)
      jsonArray.getString(0)
    ""
  }


}
