package GUI

import scalafx.application
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.Includes._
import scalafx.scene.control.TextInputDialog
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.image.Image
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.layout.GridPane
import scalafx.scene.control.Label
import scalafx.scene.input.KeyEvent
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text


object GUI extends JFXApp{
  stage = new JFXApp.PrimaryStage {
    //    g.fillRect(0, 0, 300, 300) //black rectangle
    scene = new Scene(600, 600) {
      title = "Retro Quiz"
      var canvas = new Canvas(600, 600)
      var g = canvas.getGraphicsContext2D
      //    var image = new Image("file:images")
      var player = new Player(new Image("file:images/thanos.png"), new util(275, 550))
      var right = false
      var left = false
      var up = false
      var down = false
      fill = LightGreen
      content = new Rectangle {
        x = 12.5
        y = 20
        width = 50
        height = 50
      }
//      val playerlogin = new TextInputDialog(defaultValue = "") {
//        initOwner(stage)
//        title = "Enter Your Username"
//        headerText = "Enter Your Username"
//        contentText = "Please Enter Your Name Here"
//      }
//      val result = playerlogin.showAndWait()
//      result match {
//        case Some(name) => println("Your name: " + name)
//        case None => println("Dialog was canceled")
//      }

      onKeyPressed = (e:KeyEvent) => {
        if(e.code.toString() == "UP") up = true
        if(e.code.toString() == "DOWN") down = true
        if(e.code.toString() == "LEFT") left = true
        if(e.code.toString() == "RIGHT") right = true
      }
      onKeyReleased = (e:KeyEvent) => {
        if(e.code.toString() == "UP") up = false
        if(e.code.toString() == "DOWN") down = false
        if(e.code.toString() == "LEFT") left = false
        if(e.code.toString() == "RIGHT") right = false
      }

      if(up) player.moveUp()
      if(down) player.moveDown()
      if(left) player.moveLeft()
      if(right) player.moveRight()

      player.display(g)


      content = canvas
    }
  }
}
