package io.github.acdcjunior.prp.scala

import org.junit._
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(classOf[SpringRunner])
@SpringBootTest
class PrpScalaApplication$Test {

  @Autowired
  private var context: WebApplicationContext = _

  private var mvc: MockMvc = _

  @Before def setUp() {
    this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build()
  }

  @Test
  def test() = assert(true)

  @Test
  def testHome() {
    this.mvc.perform(get("/")).andExpect(status.isNotFound)
  }

}