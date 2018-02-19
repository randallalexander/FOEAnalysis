package net.randallalexander.foe.console.lock

object Model {
  case class GreatBuilding(total:Int, runningTotal:Int)
  case class Reward(forgePoints:Int, bluePrints:Option[Int])

  sealed trait Mode
  object Mode {
    case object CalculateLock extends Mode
  }
}
