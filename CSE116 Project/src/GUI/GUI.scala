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
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text


object GUI extends JFXApp{
  stage = new JFXApp.PrimaryStage {
                              //pixels of the background image
    scene = new Scene(714, 260) {
      title = "Retro Quiz"
/*
      val leftRectangle= new Rectangle {
        x = 35
        y = 50
        width = 125
        height = 125
        fill = Cyan

      }
      val rightRectangle= new Rectangle{
        x = 315
        y =  50
        width = 125
        height = 125
        fill = LightGreen
      }
*/
      var canvas = new Canvas(714, 260)

      var g: GraphicsContext = canvas.getGraphicsContext2D
      var mario = new Image("file:images/mario.jpg")                      // x,   y
      var player = new Player(new Image("file:images/thanos.png"), new Vector(275, 200))

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
      val result: Option[String] = playerLogin.showAndWait()
      result match {
        case Some(name) => println("Your name: " + name)
        case None => println("Dialog was canceled")
      }

      var timer = AnimationTimer(t =>{

        //change last two numbers according to pixel size of image
        g.drawImage(mario, 0.0, 0.0, 714, 260)

        g.setFill(Cyan)
        g.fillRect(75, 45, 125, 125)
        g.fillRect(475, 45, 125, 125)

        //        g.fillRect(0, 130, 50, 50)
        fill = LightBlue
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

      content = canvas
      //content =  List(canvas, leftRectangle, rightRectangle)
    }
  }
}


