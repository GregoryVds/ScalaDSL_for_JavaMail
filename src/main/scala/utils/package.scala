import javax.mail.Address
import javax.mail.internet.InternetAddress
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import builders._

/**
  * @author : Léonard Julémont and Grégory Vander Schueren
  */
package object utils extends Types {

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

  implicit def map2Properties(map: mutable.Map[String, String]) : java.util.Properties = {
    val props = new java.util.Properties()
    map foreach { case (key, value) => props put(key, value) }
    props
  }

  /*
   * Custom construct to allow the DSL user to send a formatted message to a
   *   list of contacts. The message is then personnalized for each contact.
   */
  def formatMessage(body: Contact => Unit) = new {
    def to(contacts : List[Contact]) : Unit = {
      contacts.foreach(body)
    }
  }

  implicit class StringForKey(key: String) {
    def := (value : String) : (String, String) = (key,value)
  }

  var defaultProperties : mutable.Map[String, String] = mutable.Map(("mail.smtp.host","localhost"), ("mail.smtp.port","1025"))
}
