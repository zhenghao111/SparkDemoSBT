package com.zhenghao.sql
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SparkSession}

object UntypedUserDefinedAggregate  {

  object MyAverage extends UserDefinedAggregateFunction {
    // 输入参数的类型
    override def inputSchema: StructType = StructType(StructField("inputColumn", LongType) :: Nil)

    // 缓存中的值类型
    override def bufferSchema: StructType = StructType(StructField("sum", LongType) :: StructField("count", LongType) :: Nil)

    // 返回值类型
    override def dataType: DataType = DoubleType

    override def deterministic: Boolean = true

    override def initialize(buffer: MutableAggregationBuffer): Unit = {
      buffer(0) = 0L
      buffer(1) = 0L
    }

    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
      if(!input.isNullAt((0))) {
        buffer(0) = buffer.getLong(0) + input.getLong(0)
        buffer(1) = buffer.getLong(1) + 1
      }
    }

    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
      buffer1(0) = buffer1.getLong(0) + buffer2.getLong(0)
      buffer1(1) =buffer1.getLong(1) + buffer2.getLong(1)
    }

    override def evaluate(buffer: Row): Any = buffer.getLong(0).toDouble / buffer.getLong(1)
  }

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName("Spark SQL user-defined DataFrames aggregation example")
      .getOrCreate()

    // 注册
    spark.udf.register("salaryavg", MyAverage)

    val df = spark.read.json("src/main/resources/employees.json")
    df.createTempView("employee")
    df.show()

    val result = spark.sql("select salaryavg(salary) from employee")
    result.show()


  }

}
