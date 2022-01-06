package fr.esgi.al.funprog.model

import fr.esgi.al.funprog.parser.WritesCsv
import fr.esgi.al.funprog.parser.csv.{CsvObject, CsvValue}
import play.api.libs.json.{JsValue, Json, Writes}

case class Position(point: Point, direction: Direction)

object Position {
  implicit object WritesPosition extends Writes[Position] {
    def writes(o: Position): JsValue = Json.writes[Position].writes(o)
  }

  implicit object WritesCsvPosition extends WritesCsv[Position] {
    override def writes(value: Position): CsvValue = CsvObject(Map(
      "point" -> WritesCsv.of[Point].writes(value.point),
      "direction" -> WritesCsv.of[Direction].writes(value.direction)
    ))
  }
}