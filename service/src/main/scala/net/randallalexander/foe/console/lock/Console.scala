package net.randallalexander.foe.console.lock

import net.randallalexander.foe.console.lock.Model.Mode.CalculateLock
import net.randallalexander.foe.console.lock.Model.{GreatBuilding, Mode, Reward}
import scopt.OptionParser

final case class Config(greatBuilding: GreatBuilding, reward:Reward, nearestFP:Int, mode:Mode, contributedSoFar:Option[Int])

object Config {
  def consoleDefaultConfig: Config =Config(GreatBuilding(0,0),Reward(0,None),0,CalculateLock,None)
}

object Console {
  //just gonna ignore this for now
  @SuppressWarnings(Array("org.wartremover.warts.NonUnitStatements"))
  val parser: OptionParser[Config] = new scopt.OptionParser[Config]("Arc") {
    head("FP Return Calculator", "0.1.0")

    opt[Int]('t', "GBTarget")
      .required()
      .validate( fp =>
        if (fp > 0) success
        else failure("GBTarget must be > 0") )
      .action( (fp, conf) =>
        conf.copy(greatBuilding = conf.greatBuilding.copy(total = fp)))
      .text("Total amount of FP required to level the GB.")

    opt[Int]('r', "GBRunningTotal")
      .required()
      .validate( fp =>
        if (fp > 0) success
        else failure("GBContrib must be > 0") )
      .action( (fp, conf) =>
        conf.copy(greatBuilding = conf.greatBuilding.copy(runningTotal = fp)))
      .text("Total amount of FP contributed to the GB.")

    opt[Int]('f', "FPReward")
      .required()
      .validate( fp =>
        if (fp > 0) success
        else failure("FPReward must be > 0") )
      .action( (fp, conf) =>
        conf.copy(reward = conf.reward.copy(forgePoints = fp)))
      .text("Total amount of FP rewarded.")

    opt[Int]('n', "nearestFP")
      .required()
      .validate( fp =>
        if (fp >= 0) success
        else failure("nearestFP must be > 0") )
      .action( (fp, conf) =>
        conf.copy(nearestFP = fp) )
      .text("Nearest contributor.")

    opt[Int]('c', "contributedSoFar")
      .optional()
      .action( (fp, conf) =>
        conf.copy(contributedSoFar = Some(fp)))
      .text("Total FP contributed so far.")

    opt[Int]('b', "BPReward")
      .optional()
      .action( (bp, conf) =>
        conf.copy(reward = conf.reward.copy(bluePrints = Some(bp))))
      .text("Total amount of BP rewarded.")

    cmd("lock")
      .action(
        (_, config) => config.copy(mode = CalculateLock)
      )

    help("help")
      .text("Print usage text.")
  }
}
