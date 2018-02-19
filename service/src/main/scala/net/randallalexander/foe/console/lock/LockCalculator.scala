package net.randallalexander.foe.console.lock

import net.randallalexander.foe.console.lock.Model.GreatBuilding

object LockCalculator {

  /*
    Does not take into account that first person in wins
   */
  def calculateLock(gb:GreatBuilding, competitorFP:Int, contributedSoFarOpt:Option[Int]):Int = {
    val buildingTotal:Double = gb.total.toDouble
    val contribTotal:Double = gb.runningTotal.toDouble
    val contributedSoFar:Double = contributedSoFarOpt.getOrElse(0).toDouble

    val gbFPavailable = buildingTotal - contribTotal
    val lockAmount = Utils.round((gbFPavailable.toDouble - (competitorFP.toDouble-contributedSoFar+1d))/2d + (competitorFP.toDouble-contributedSoFar+1d))
    lockAmount
  }
}
