package utils

import java.util.Properties
import javax.mail.Address
import javax.mail.internet.InternetAddress
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Utils {

  /** Create an array of InternetAddress from a sequence of strings
     *
     * @param strings the Seq of addresses expressed with strings
     * @return the array of InternetAddress corresponding to the input 'strings'
     */
  def seqToAddresses(strings: Seq[String]) : Array[Address] = {
    val addresses : ListBuffer[Address] = ListBuffer()
    strings.foreach(address => addresses += new InternetAddress(address))
    addresses.toArray
  }

  implicit def map2Properties(map: mutable.Map[String,String]) : java.util.Properties = {
    val props = new Properties()
    map foreach { case (key, value) => props put(key, value) }
    props
  }
}
