package io.github.acdcjunior.prp.scala

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class PrpScalaApplication

object PrpScalaApplication extends App {

  SpringApplication.run(classOf[PrpScalaApplication], args: _*)

}