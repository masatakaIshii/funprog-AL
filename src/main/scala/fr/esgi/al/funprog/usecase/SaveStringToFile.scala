package fr.esgi.al.funprog.usecase

import better.files.File

class SaveStringToFile {
  def execute(value: String, filename: String): String = {
    val file = File(filename)
    file.createIfNotExists(createParents = true).overwrite("").append(value)

    filename
  }
}

object SaveStringToFile {
  def apply(): SaveStringToFile = new SaveStringToFile()
}
