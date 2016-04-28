package builders.dynamicBuilder

import collection.mutable
import scala.language.dynamics

/**
  * Created by Greg on 27/04/16.
  */

class Contact extends Dynamic {
  private val regex = "(with|and)(.*)".r
  private val values: mutable.Map[String, String] = mutable.Map.empty[String, String]

  def selectDynamic(name: String): String = {
    values.get(name) match {
      case Some(value) => value
      case None => throw new NoSuchMethodException
    }
  }

  def applyDynamic(name: String)(value: String): Contact = {
    try {
      val regex(_, property) = name
      val lowerCasedProp = property.substring(0,1).toLowerCase + property.substring(1)
      values(lowerCasedProp) = value
      this
    } catch {
      case e: MatchError => throw new NoSuchMethodException("Use with[PropertyName] to set a property on the client.")
    }
  }

  def apply(prop: String) = values.get(prop).get
}

object Contact extends Dynamic {
  def applyDynamic(name: String)(value: String): Contact = {
    val newContact = new Contact()
    newContact.applyDynamic(name)(value)
  }
}