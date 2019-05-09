package Games

import Games.Physics.PhysicsVector
import akka.actor.{Actor, ActorRef, PoisonPill, Props}


class GameActor extends Actor {

  var players: Map[String, ActorRef] = Map()

  val game: Game = new Game()
  var levelNumber = 0
  loadLevel(levelNumber)

  def loadLevel(levelNumber: Int): Unit ={
    val level = Map
    game.loadMap
  }

  override def receive: Receive = {
    case message: AddPlayer => game.addPlayer(message.username)
    case message: RemovePlayer => game.removePlayer(message.username)
    case message: MovePlayer => game.People(message.username).movement(new PhysicsVector(message.x, message.y))
    case message: StopPlayer => game.People(message.username).standStill()

    case UpdateGame =>
      game.update()
    case SendGameState => sender() ! GameState(game.gameState())
  }
}