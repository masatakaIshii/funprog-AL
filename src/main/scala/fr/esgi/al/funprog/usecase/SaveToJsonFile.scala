package fr.esgi.al.funprog.usecase

import better.files.File
import play.api.libs.json.{JsValue, Json}

class SaveToJsonFile {
  def execute(json: JsValue, filename: String): String = {
    val f = File(filename)
    val jsonString = Json prettyPrint json
    f.createIfNotExists(createParents = true).overwrite("").append(jsonString)

    filename
  }

}

object SaveToJsonFile {
  def apply(): SaveToJsonFile = new SaveToJsonFile()

}