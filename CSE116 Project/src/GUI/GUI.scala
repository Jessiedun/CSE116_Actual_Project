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
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{Stops, LinearGradient}
import scalafx.scene.text.Text



object GUI extends JFXApp{
  stage = new application.JFXApp.PrimaryStage{
    title = "Retro Quiz"
    var picture = new Canvas(200, 200)
    var screen = picture.getGraphicsContext2D
    screen.fillRect(0,0,300,300)
    scene = new Scene(200, 200){
      fill = LightGreen
      content = new Rectangle {
        x = 12.5
        y = 20
        width = 50
        height = 50
      }
      val playerlogin = new TextInputDialog(defaultValue = ""){
        initOwner(stage)
        title = "Enter Your Username"
        headerText = "Enter Your Username"
        contentText = "please enter your name here"
      }
      val result = playerlogin.showAndWait()
      result match{
        case Some(name) => println("Your name: " + name)
        case None       => println("Dialog was canceled")
      }
      content = picture
    }
  }
}