package fr.esgi.al.funprog.model

import play.api.libs.json.{JsValue, Json, Writes}


case class LawnMower
(start: (Point, Direction),
 instructions: List[Instruction],
 end: (Point, Direction)
)
object LawnMower {
  implicit object WritesLawnMower extends Writes[LawnMower] {
    def writes(o: LawnMower): JsValue = Json.writes[LawnMower].writes(o)
  }
}
