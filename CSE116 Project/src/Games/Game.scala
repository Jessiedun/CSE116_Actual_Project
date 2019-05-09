package Games

import Games.Physics.{PhysicsVector, World}
import Games.objects.Players
import play.api.libs.json.{JsValue, Json}

class Game {
  var People: Map[String, Players] = Map()
  var world: World = new World(10)
  var maps: map = new map()
  var lastUpdateTime: Long = System.nanoTime()

  def loadMap(): Unit = {
    People.values.foreach(player => player.location = spawn())
  }

  def update(): Unit = {
    val time: Long = System.nanoTime()
    var deltaTime = (time - this.lastUpdateTime) / 1000000000.0
    Physics.Physics.world(this.world, deltaTime)
    this.lastUpdateTime = time
  }

  def spawn(): PhysicsVector = {
    new PhysicsVector(maps.startingLocation.x + .5, maps.startingLocation.y + .5)
  }

  def addPlayer(username: String): Unit = {
    val player = new Players(spawn(), new PhysicsVector(0, 0))
    People += (username -> player)
    world.thing = player :: world.thing
  }

  def removePlayer(name: String): Unit = {
    People -= (name)
  }

  def gameState(): String = {
    val gameState: Map[String, JsValue] = Map(
      "gridSize" -> Json.toJson(Map("x" -> maps.gridx, "y" -> maps.gridy)),
      "start" -> Json.toJson(Map("x" -> maps.startingLocation.x, "y" -> maps.startingLocation.y)),
      "players" -> Json.toJson(this.People.map({ case (k, v) => Json.toJson(Map(
        "x" -> Json.toJson(v.location.x),
        "y" -> Json.toJson(v.location.y),
        "v_x" -> Json.toJson(v.velocity.x),
        "v_y" -> Json.toJson(v.velocity.y),
        "id" -> Json.toJson(k)))
      }))
    )
    Json.stringify(Json.toJson(gameState))
  }

}