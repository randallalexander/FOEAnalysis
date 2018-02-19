package net.randallalexander.foe.console.lock

import com.typesafe.config.ConfigFactory
import io.circe.config.syntax._
import net.randallalexander.foe.config.Arc

object Driver extends App {
  private val mainConfig = ConfigFactory.load()
  private val arcEith = mainConfig.as[Arc]("foe.gb.arc")
  arcEith match {
    case Left(error) =>
      println(s"Config load error: ${error.getMessage}")
    case Right(arc) =>
      def arcRound = Utils.multiply(1d + arc.contribBoost)_

      Console
        .parser.parse(args,Config.consoleDefaultConfig) match {
        case Some(config) =>

          val rewardAmount = arcRound(config.reward.forgePoints)
          val blueprintsRewardOpt = config.reward.bluePrints.map(arcRound)
          val lockAmount = LockCalculator.calculateLock(config.greatBuilding, config.nearestFP, config.contributedSoFar)
          val netFP = rewardAmount - lockAmount

          println(s"FP Lock Amount:       ${lockAmount}")
          println(s"FP Reward Amount:     ${rewardAmount}")
          println(s"Net FP Reward Amount: ${netFP}")
          println(s"-------------------------------------")
          println(s"FP Snipe:             ${netFP > 0}")
          println(s"BP Snipe:             ${netFP >= 0}")
          println(s"-------------------------------------")
          println(s"BP Reward:            ${blueprintsRewardOpt.map(_.toString).getOrElse("Unknown")}")
            ()
        case None =>
          ()
      }
  }
}