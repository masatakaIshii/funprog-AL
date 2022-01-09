package fr.esgi.al.funprog.usecase

import fr.esgi.al.funprog.model.{LawnMower, Point}
import play.api.libs.json.{JsArray, JsObject, Json}

class CreateJsonFromResults {
  def execute(lawnMowers: List[LawnMower],limits: Point): String = {
    val jsonLimit = Point.WritesPoint.writes(limits)
    val jsonLawnMowers = JsArray(lawnMowers.map(lawnMower => LawnMower.WritesLawnMower.writes(lawnMower)))

    val jsonResults = JsObject(Map(("limits",jsonLimit),("lawnMowers", jsonLawnMowers)))
    Json prettyPrint jsonResults
  }

}

object CreateJsonFromResults {
  def apply(): CreateJsonFromResults = new CreateJsonFromResults()
}
