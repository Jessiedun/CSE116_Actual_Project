package Games.Physics

import Games.objects.Objects

object Physics {
  def computePotentialLocation(obj: Objects, deltaTime: Double): PhysicsVector ={
    var updatedX = obj.location.x + deltaTime * obj.velocity.x
    var updatedY = obj.location.y + deltaTime * obj.velocity.y
    var updatedZ = 0.0.max(obj.location.z + deltaTime * obj.velocity.z)
    new PhysicsVector(updatedX, updatedY, updatedZ)
  }
  def computePotentialVelocity(obj: Objects, world: World, deltaTime: Double): Unit ={
    var newVelocity = obj.velocity.z - world.gravity * deltaTime
    if(newVelocity < 0.0){
      obj.velocity.z = 0.0
    }
    else{
      obj.velocity.z =newVelocity
    }
  }

  def world(newWorld: World, deltaTime: Double): Unit = {
    for (obj <- newWorld.thing) {
      var place = computePotentialLocation(obj, deltaTime)
      computePotentialVelocity(obj, newWorld, deltaTime)
      obj.location.z = place.z

    }

  }
}
