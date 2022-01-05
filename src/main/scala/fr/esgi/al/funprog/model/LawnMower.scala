package fr.esgi.al.funprog.model

import play.api.libs.json.{JsValue, Json, Writes}


case class LawnMower
(start: Position,
 instructions: List[Instruction],
 end: Position
)
object LawnMower {
  implicit object WritesLawnMower extends Writes[LawnMower] {
    def writes(o: LawnMower): JsValue = Json.writes[LawnMower].writes(o)
  }
}
