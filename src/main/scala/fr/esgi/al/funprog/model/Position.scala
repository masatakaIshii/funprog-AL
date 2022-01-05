package fr.esgi.al.funprog.model

import play.api.libs.json.{JsValue, Json, Writes}

case class Position(point: Point, direction: Direction)

object Position {
  implicit object WritesPosition extends Writes[Position] {
    def writes(o: Position): JsValue = Json.writes[Position].writes(o)
  }
}