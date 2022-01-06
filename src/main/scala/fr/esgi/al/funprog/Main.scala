package fr.esgi.al.funprog

import fr.esgi.al.funprog.usecase.{CreateJsonFromResults, CreateStringCsvFromResults, ReadFileInstructions, SaveStringToFile, SaveToJsonFile, StartGivenLawnMower}


object Main extends App {
  println("Ici le programme principal")
  val test = ReadFileInstructions().execute("tondeuse.txt")
  val funProg =FunProg.apply(startGivenLawnMower = StartGivenLawnMower())
  val uesh = funProg.start(test._1, test._2)

  println(uesh)
  val json = CreateJsonFromResults().execute(uesh, test._1)
  println(json)

  val filePath = SaveToJsonFile.apply().execute(json,"result.json")

  println("Result is save at the path : " + filePath)


  val csv = CreateStringCsvFromResults().execute(uesh)

  val csvFilePath = SaveStringToFile().execute(csv, "resultCsv.csv")

  println("Result csvFile is save at the path : " + csvFilePath)
}
