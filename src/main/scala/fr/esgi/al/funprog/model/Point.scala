package fr.esgi.al.funprog.model

import play.api.libs.json.{JsValue, Json, Writes}

case class Point(x: Int, y: Int)

object Point {
  implicit object WritesPoint extends Writes[Point] {
    override def writes(o: Point): JsValue = Json.writes[Point].writes(o)
  }
}