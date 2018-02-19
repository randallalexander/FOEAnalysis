package net.randallalexander.foe.config

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._

final case class Arc(contribBoost:Double)

object Arc {
  /*
  Not sure why wartremover is complaining here...this is exactly how
  it should look.  All attempts to appears it has been futile.  Need
  to circle back.
   */

  @SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
  implicit val arcDecoder: Decoder[Arc] = deriveDecoder[Arc]
  @SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
  implicit val arcEncoder: Encoder[Arc] = deriveEncoder[Arc]
}