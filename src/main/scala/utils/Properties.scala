package utils

import scala.collection.mutable
import java.util.{Properties => JavaProperties}
import scala.collection.JavaConversions.propertiesAsScalaMap

class Properties extends java.util.Properties {
  val properties = new JavaProperties()

  def add(keyValue : (String, String)) : Properties = {
    properties.setProperty(keyValue._1, keyValue._2)
    this
  }
}

object Properties {
  def add(keyValue : (String, String)) : Properties = new Properties() add(keyValue)
}
