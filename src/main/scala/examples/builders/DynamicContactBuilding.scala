package examples.builders

import builders._

/**
  * @author : Léonard Julémont and Grégory Vander Schueren
  */

object DynamicContactBuilding extends App {
  /*
  This dynamic builder uses the dynamic member lookup feature of Scala
  to build a Contact object (note that the setters always return the instance
  being built for easy chaining). By mixing the scala Dynamic trait we can
  catches all the messages that the target object does not have and access
  instance members that are not statically defined on the class. This builder
  allows to create a Contact object that basically acts like a dictionary: we
  can add to it any member we desire at runtime and retrieve that value later
  (under the hood the Contact is backed by a dictionary):
  */

  val greg = Contact withName "Greg" andDog "Chucky" andAge "25"
  println(s"${greg.name} has dog ${greg.dog} and age ${greg.age}")

  // We can really use whatever name pleases us:
  val leo  = Contact withXYZ "Léonard" andAge "21" andCat "Flappy"

  // We provide 2 alternates notation: withProperty & andProperty that are equivalent.
  val jon  = Contact withName "Jony" andWhatever "Whatever"
}
