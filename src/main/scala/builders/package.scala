import builders.functionalBuilder.Reminder

/**
  * @author : Léonard Julémont and Grégory Vander Schueren
  */
package object builders extends Reminder {
  type SimpleMail           = imperativeBuilder.SimpleMail
  final val SimpleMail      = imperativeBuilder.SimpleMail
  type Authentication       = imperativeBuilder.Authentication

  type Contact              = dynamicBuilder.Contact
  final val Contact         = dynamicBuilder.Contact

  type ImplicitMailBuilder  = implicitBuilder.ImplicitMimeMessage
}
