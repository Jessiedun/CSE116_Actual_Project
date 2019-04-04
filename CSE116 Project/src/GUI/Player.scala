package GUI

import scalafx.scene.image.Image
import scalafx.scene.canvas.GraphicsContext


class Player(var img: Image, var initPos: util){

  def moveUp(): Unit ={
                 // Boundaries
    if(initPos.y - 0 >= 0) initPos -= new util(0,5)
  }
  def moveDown(): Unit ={
    if(initPos.y + 0 <= 200) initPos += new util(0,5)
  }
  def moveLeft(): Unit ={
    if(initPos.x - 0 >= 0) initPos -= new util(5,0)
  }
  def moveRight(): Unit ={
    if(initPos.x + 0 <= 650) initPos += new util(5,0)
  }
  def display(g: GraphicsContext): Unit ={
    g.drawImage(img, initPos.x, initPos.y, 60, 60)
                                  // how big picture is
  }
}
