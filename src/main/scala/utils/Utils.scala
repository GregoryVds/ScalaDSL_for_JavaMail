package utils

import javax.mail.Address
import javax.mail.internet.{InternetAddress, AddressException}
import scala.collection.mutable.ListBuffer

object Utils {


  /** Create an array of InternetAddress from a sequence of strings
     *
     * @param strings the Seq of addresses expressed with strings
     * @return the array of InternetAddress corresponding to the input 'strings'
     */
  def seqToAddresses(strings: Seq[String]) : Array[Address] = {
    val addresses : ListBuffer[Address] = ListBuffer()
    strings.foreach(address => {
      try{
        addresses += new InternetAddress(address, true)
      } catch {
        case exc : AddressException => println(s"Error : The address '$address' is not a valid email address")
      }
    })
    addresses.toArray
  }

}
