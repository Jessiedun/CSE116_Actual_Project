package GUI

import javafx.scene.canvas.GraphicsContext
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.Includes._
import scalafx.animation.AnimationTimer
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
import scalafx.scene.paint.{LinearGradient, Stops,Color}
import scalafx.scene.text.Text
import scalafx.scene.{Group, shape}


object GUI extends JFXApp{
  stage = new JFXApp.PrimaryStage {
    //    g.fillRect(0, 0, 300, 300) //black rectangle
    var canvas = new Canvas(600, 600)
    title = "Retro Quiz"

    scene = new Scene(600, 600) {
      


      var g: GraphicsContext = canvas.getGraphicsContext2D
          var image = new Image("file:images/mario.png")
      var player = new Player(new Image("file:images/thanos.png"), new util(275, 550))
      var right = false
      var left = false
      var up = false
      var down = false
      val playerLogin: TextInputDialog = new TextInputDialog(defaultValue = "") {
        initOwner(stage)
        title = "Enter Your Username"
        headerText = "Enter Your Username"
        contentText = "Please Enter Your Name Here"
      }

      scene

      val result: Option[String] = playerLogin.showAndWait()
      result match {
        case Some(name) => println("Your name: " + name)
        case None => println("Dialog was canceled")
      }
      //      }
      var timer = AnimationTimer(t =>{

        fill = LightBlue
        //      content = new Rectangle {
        //        x = 12.5
        //        y = 20
        //        width = 50
        //        height = 50

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


     })
      timer.start

      content = List(canvas, leftRectangle, rightRectangle)
    }

  }

}


