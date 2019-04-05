package GUI

import scalafx.scene.image.Image
import scalafx.scene.canvas.GraphicsContext


class Player(var img: Image, var initPos: Vector){

  def moveUp(): Unit ={
                 // Boundaries
    if(initPos.y - 5 >= 0) initPos -= new Vector(0,5)
  }
  def moveDown(): Unit ={
    if(initPos.y + 5 <= 387) initPos += new Vector(0,5)
  }
  def moveLeft(): Unit ={
    if(initPos.x - 5 >= 0) initPos -= new Vector(5,0)
  }
  def moveRight(): Unit ={
    if(initPos.x + 5 <= 1300) initPos += new Vector(5,0)
  }
  def display(g: GraphicsContext): Unit ={
    g.drawImage(img, initPos.x, initPos.y, 140, 140)
                                  // how big picture is
  }
}
