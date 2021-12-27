package fr.esgi.al.funprog.usecase

import fr.esgi.al.funprog.model.{LawnMower, Point}
import play.api.libs.json.{JsArray, JsObject, JsValue}

class CreateJsonFromResults {
  def execute(lawnMowers: List[LawnMower],limits: Point): JsValue = {
    val jsonLimit = Point.WritesPoint.writes(limits)
    val jsonLawnMowers = JsArray(lawnMowers.map(lawnMower => LawnMower.WritesLawnMower.writes(lawnMower)))

    JsObject(Map(("limits",jsonLimit),("lawnMowers", jsonLawnMowers)))
  }

}

object CreateJsonFromResults {
  def apply(): CreateJsonFromResults = new CreateJsonFromResults()
}
