package fr.esgi.al.funprog

import com.typesafe.config.{Config, ConfigFactory}
import fr.esgi.al.funprog.usecase.{CreateJsonFromResults, CreateStringCsvFromResults, ReadFileInstructions, SaveStringToFile, SaveToJsonFile, StartGivenLawnMower}


object Main extends App {
  println("Ici le programme principal")
  val conf: Config = ConfigFactory.load()

  val inputFile = conf.getString("application.input-file")
  val outputJson = conf.getString("application.output-json-file")
  val outputCsv = conf.getString("application.output-csv-file")

  val test = ReadFileInstructions().execute(inputFile)
  val funProg =FunProg.apply(startGivenLawnMower = StartGivenLawnMower())
  val uesh = funProg.start(test._1, test._2)

  println(uesh)
  val json = CreateJsonFromResults().execute(uesh, test._1)
  println(json)

  val filePath = SaveToJsonFile.apply().execute(json,outputJson)

  println("Result is save at the path : " + filePath)

  val csv = CreateStringCsvFromResults().execute(uesh)

  val csvFilePath = SaveStringToFile().execute(csv, outputCsv)

  println("Result csvFile is save at the path : " + csvFilePath)
}
