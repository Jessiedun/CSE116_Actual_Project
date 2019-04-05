package tests

import GUI._
import org.scalatest._

class initPosTest extends FunSuite {
  test("test") {

    assert(GUI.initialPosX(275) == 275)
    assert(GUI.initialPosY(200) == 200)
  }
}