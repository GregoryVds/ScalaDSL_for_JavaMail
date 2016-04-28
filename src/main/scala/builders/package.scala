import builders.functionalBuilder.Reminder

/**
  * Created by Greg on 28/04/16.
  */
package object builders extends Reminder {
  type SimpleMail           = imperativeBuilder.SimpleMail
  final val SimpleMail      = imperativeBuilder.SimpleMail

  type Contact              = dynamicBuilder.Contact
  final val Contact         = dynamicBuilder.Contact

  type ImplicitMailBuilder  = implicitBuilder.ImplicitMimeMessageBuilder
}