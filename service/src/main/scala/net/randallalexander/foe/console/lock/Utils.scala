package net.randallalexander.foe.console.lock

object Utils {
  def multiply(factor:Double)(value:Int):Int = round(value.toDouble * factor)

  def round(double: Double):Int = BigDecimal(double).setScale(0, BigDecimal.RoundingMode.HALF_UP).toInt
}
