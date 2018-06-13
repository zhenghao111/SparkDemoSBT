package com.zhenghao.sql

import org.apache.spark.sql.{Row, SparkSession}

object SparkHiveExample {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName("Spark Hive Example")
      .config("spark.sql.warehouse.dir", "file:///Users/zhenghao/Documents/Workspace/IDEA/SparkDemoSBT/src/main/resources/spark-warehouse")
      .enableHiveSupport()
      .getOrCreate()

    import spark.implicits._
    import spark.sql

    sql("CREATE TABLE IF NOT EXISTS src (key INT, value STRING) USING hive")
    sql("LOAD DATA LOCAL INPATH 'src/main/resources/kv1.txt' INTO TABLE src")
    sql("SELECT * FROM src").show()

    val sqlDF = sql("SELECT key, value FROM src WHERE key < 10 ORDER BY key")
    val stringsDS = sqlDF.map {
      case Row(key: Int, value: String) => s"Key: $key, Value: $value"
    }
    stringsDS.show()

//    val recordsDF = spark.createDataFrame((1 to 100).map(i => Record(i, s"val_$i")))
//    recordsDF.createOrReplaceTempView("records")
//    sql("SELECT * FROM records r JOIN src s ON r.key = s.key").show()





  }

}
