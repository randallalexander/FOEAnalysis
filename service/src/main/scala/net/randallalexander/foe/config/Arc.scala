package net.randallalexander.foe.config

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._

case class Arc(contribBoost:Double)

object Arc {
  implicit val arcDecoder: Decoder[Arc] = deriveDecoder[Arc]
  implicit val arcEncoder: Encoder[Arc] = deriveEncoder[Arc]
}