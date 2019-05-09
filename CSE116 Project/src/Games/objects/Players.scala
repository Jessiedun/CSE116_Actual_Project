package Games.objects

import Games.Physics.PhysicsVector

class Players(location: PhysicsVector, gamevelocity: PhysicsVector)extends Objects (location, gamevelocity){
  var V: Double = 10.0
  def movement(directions: PhysicsVector){
    var direction = directions.normal2d()
    this.velocity = new PhysicsVector(direction.x * V, direction.y * V)
  }
  def standStill(): Unit ={
    this.velocity = new PhysicsVector(0, 0)
  }
}
