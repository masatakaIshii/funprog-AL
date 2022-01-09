package fr.esgi.al.funprog

import com.typesafe.config.{Config, ConfigFactory}
import fr.esgi.al.funprog.exception.DonneesIncorectesException
import fr.esgi.al.funprog.model.{LawnMower, Point}
import fr.esgi.al.funprog.usecase.{CreateJsonFromResults, CreateStringCsvFromResults, ReadFileInstructions, SaveStringToFile, StartGivenLawnMower}

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
      val result = funProg.start(value._1, value._2)
      val saveStringToFile = SaveStringToFile()

      val json = CreateJsonFromResults().execute(result, value._1)

      val filePath = saveStringToFile.execute(json,outputJson)

      println("Result is save at the path : " + filePath)

      val csv = CreateStringCsvFromResults().execute(result)

      val csvFilePath = saveStringToFile.execute(csv, outputCsv)

      println("Result csvFile is save at the path : " + csvFilePath)
    }
    case Failure(exception: DonneesIncorectesException) => println(exception.message)

    case Failure(_) => println("Unknown error")
  }

}
