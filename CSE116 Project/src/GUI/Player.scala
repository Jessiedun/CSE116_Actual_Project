package GUI

import scalafx.scene.image.Image
import scalafx.scene.canvas.GraphicsContext


class Player(var img: Image, var initPos: util){

  def moveUp(): Unit ={
    if(initPos.y - 5 >= 0) initPos -= new util(0,5)
  }
  def moveDown(): Unit ={
    if(initPos.y + 5 <= 550) initPos += new util(0,5)
  }
  def moveLeft(): Unit ={
    if(initPos.x - 5 >= 0) initPos -= new util(5,0)
  }
  def moveRight(): Unit ={
    if(initPos.x + 5 >= 550) initPos -= new util(5,0)
  }
  def display(g: GraphicsContext): Unit ={
    g.drawImage(img, initPos.x, initPos.y, 50,50)
  }
}
