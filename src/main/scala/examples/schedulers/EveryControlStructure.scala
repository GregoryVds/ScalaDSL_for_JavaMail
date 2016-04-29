package examples.schedulers

import builders._
import schedulers._
import utils._

/**
  * @author : Léonard Julémont and Grégory Vander Schueren
  */
object EveryControlStructure extends App {
  val Greg = Contact withName "Greg" andEmail "greg@gmail.com" andDog "Chucky" andAge "25"
  val Tom = Contact withName "Tom" andEmail "tom@gmail.com" andHorse "Light" andAge "18"
  val Me  = Contact withName "Léonard" andEmail "leo@gmail.com" andAge "21" andCat "Flappy"


  /*
   * This schedules a task on every day at 8:00. The task is a formatted mail to a list of contacts.
   */
  every(Day){task =>
    task at "8:00" make {
      formatMessage {contact =>
        SimpleMail(defaultProperties) to contact from Me withSubject {
          "LINGI 2132 : Newsletter !"
        } withContent {
          <html>
            <body>
              Hello contact("name") !

              Here is the newsletter of today :)
            </body>
          </html>
        } send()
      } to List(Greg, Tom)
    }
  } done

  /*
   * Here we schedule two tasks on every Mondays with two different times.
   */
  every(Monday){task =>
    task at "9:00" make {
      SimpleMail(defaultProperties) to Greg from Me withSubject "Info for "+Greg("name") withContent "It's the beginning of the week !" send()
    }

    task at "17:00" make {
      SimpleMail(defaultProperties) to Greg from Me withSubject "Info for "+Greg("name") withContent "4 more days until the end of the week !" send()
    }
  } done

  /*
   * We can even schedule a task on every second.
   */
  every(Second) {
    SimpleMail(defaultProperties) to Tom from Me withSubject "Spam" withContent "This email is sent every second !" send()
  } done



  /*****
   *
   * The following examples are created but not lauched.
   *
   * To activate a schedule you must add 'done' at the end (as above).
   */


   // Schedule a task at an exact time later today
   at("17:00"){
     println("This message will be printed at 17:00")
   }

   // Repeated the task only a certain amount of times
   every(Hour){
     println("Printed once during the next 5 hours")
   } repeated(5)

   // We can select the day in a month on which our task must be done
   every(May){task =>
     task at "9:00" make(println("Printed every day of May at 9:00"))
     task the "15" make(println("Printed May 15th at 0:00"))
     task the "2" at "10:30" make(println("Printed May 2nd at 10:30"))
   }

   // We can also execute a task every Nth day of each month
   every(Day){task =>
     task the "1" at "8:30" make(println("It's the first day of the month !"))
     task the "10" make(println("It's the 10th of the month !"))
   }

   /* Nested task :
    *
    * Schedule a task on May 22th that schedules directly a task printing "SPAM"
    *  every second.
    */
    every(May){ day =>
     day the "22" at "20:00" make {
       every(Second){
         println("SPAM")
       } done
     }
   }
}
