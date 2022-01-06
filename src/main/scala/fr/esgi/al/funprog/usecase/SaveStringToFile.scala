package fr.esgi.al.funprog.usecase

import better.files.File

class SaveStringToFile {
  def execute(value: String, filename: String): String = {
    val pathFile = "results/" + filename
    val file = File(pathFile)
    file.createIfNotExists(createParents = true).overwrite("").append(value)

    pathFile
  }
}

object SaveStringToFile {
  def apply(): SaveStringToFile = new SaveStringToFile()
}
