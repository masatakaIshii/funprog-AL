package fr.esgi.al.funprog.usecase

import better.files.File
import play.api.libs.json.{JsValue, Json}

class SaveToJsonFile {
  def execute(json: JsValue, filename: String): String = {
    val pathFile = "results/" + filename
    val f = File(pathFile)
    val jsonString = Json prettyPrint json
    f.createIfNotExists().overwrite("").append(jsonString)

    pathFile
  }

}

object SaveToJsonFile {
  def apply(): SaveToJsonFile = new SaveToJsonFile()

}