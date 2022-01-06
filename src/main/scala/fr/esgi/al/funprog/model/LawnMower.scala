package fr.esgi.al.funprog.model

import fr.esgi.al.funprog.parser.WritesCsv
import fr.esgi.al.funprog.parser.csv.{CsvObject, CsvValue}
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

  implicit object WritesCsvLawnMower extends WritesCsv[LawnMower] {
    override def writes(value: LawnMower): CsvValue = CsvObject(Map(
      "start" -> WritesCsv.of[Position].writes(value.start),
      "end" -> WritesCsv.of[Position].writes(value.end),
      "instructions" -> {
        val listInstructions = value.instructions.map(WritesCsv.of[Instruction].writes)
        WritesCsv.of[List[CsvValue]].writes(listInstructions)
      }
    ))
  }
}
