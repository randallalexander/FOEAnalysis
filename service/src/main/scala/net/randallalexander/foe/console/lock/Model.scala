package net.randallalexander.foe.console.lock

object Model {
  final case class GreatBuilding(total:Int, runningTotal:Int)
  final case class Reward(forgePoints:Int, bluePrints:Option[Int])

  sealed trait Mode
  object Mode {
    final case object CalculateLock extends Mode
  }
}
