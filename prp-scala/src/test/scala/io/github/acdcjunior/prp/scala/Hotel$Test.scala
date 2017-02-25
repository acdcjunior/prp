package io.github.acdcjunior.prp.scala

import org.junit.Assert._
import org.junit.Test

class Hotel$Test {

  @Test def name() {
    val hotel = new Hotel
    hotel.setName("aew")
    assertEquals("aew", hotel.getName)
    assertEquals("aew", hotel.name)
    hotel.go()
    assertEquals("ok", hotel.zip)
  }

}