package fr.esgi.al.funprog

import com.typesafe.config.{Config, ConfigFactory}
import fr.esgi.al.funprog.exception.DonneesIncorectesException
import fr.esgi.al.funprog.model.{LawnMower, Point}
import fr.esgi.al.funprog.usecase.{CreateJsonFromResults, CreateStringCsvFromResults, ReadFileInstructions, SaveStringToFile, SaveToJsonFile, StartGivenLawnMower}

import scala.util.{Failure, Success}


object Main extends App {
  println("Ici le programme principal")
  val conf: Config = ConfigFactory.load()

  val inputFile = conf.getString("application.input-file")
  val outputJson = conf.getString("application.output-json-file")
  val outputCsv = conf.getString("application.output-csv-file")

  val parsedFile = ReadFileInstructions().execute(inputFile)

  parsedFile match {
    case Success(value: (Point, List[LawnMower])) => {
      val funProg =FunProg.apply(startGivenLawnMower = StartGivenLawnMower())
      val uesh = funProg.start(value._1, value._2)

      println(uesh)
      val json = CreateJsonFromResults().execute(uesh, value._1)
      println(json)

      val filePath = SaveToJsonFile.apply().execute(json,outputJson)

      println("Result is save at the path : " + filePath)

      val csv = CreateStringCsvFromResults().execute(uesh)

      val csvFilePath = SaveStringToFile().execute(csv, outputCsv)

      println("Result csvFile is save at the path : " + csvFilePath)
    }
    case Failure(exception: DonneesIncorectesException) => println(exception.message)

    case Failure(_) => println("Unknown error")
  }

}
