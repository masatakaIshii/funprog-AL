package fr.esgi.al.funprog.model

import fr.esgi.al.funprog.parser.WritesCsv
import fr.esgi.al.funprog.parser.csv.{CsvObject, CsvValue}
import play.api.libs.json.{JsValue, Json, Writes}

case class Point(x: Int, y: Int)

object Point {
  implicit object WritesPoint extends Writes[Point] {
    override def writes(o: Point): JsValue = Json.writes[Point].writes(o)
  }

  implicit object WritesCsvPoint extends WritesCsv[Point] {
    override def writes(value: Point): CsvValue = CsvObject(Map(
      "x" -> WritesCsv.of[Int].writes(value.x),
      "y" -> WritesCsv.of[Int].writes(value.y)
    ))
  }
}