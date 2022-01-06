package fr.esgi.al.funprog.usecase

import fr.esgi.al.funprog.model.LawnMower
import fr.esgi.al.funprog.parser.WritesCsv
import fr.esgi.al.funprog.parser.csv._
import fr.esgi.al.funprog.usecase.CreateStringCsvFromResults.{PREFIX_END, PREFIX_START}

import scala.annotation.tailrec

class CreateStringCsvFromResults {

  def execute(listLawnMower: List[LawnMower]): String = {
    val listLawnMowerCsv = listLawnMower.map(WritesCsv.of[LawnMower].writes)
    val listKeys = List(
      PREFIX_START + "x",
      PREFIX_START + "y",
      PREFIX_START + "direction",
      PREFIX_END + "x",
      PREFIX_END + "y",
      PREFIX_END + "direction",
      "instructions"
    )
    val columns = listKeys.mkString(";") + System.lineSeparator()
    fromListLawnMowerCsvToString(listKeys, listLawnMowerCsv, 1, "numéro;" + columns)
  }

  @tailrec
  private def fromListLawnMowerCsvToString(listKeys: List[String], value: List[CsvValue], number: Int, result: String): String = value match {
    case head :: tail =>
      val prepareMapString = setupResultMap(listKeys, Map())
      val resultMap = fromLawnMowerCsvToMapStrings(head, prepareMapString)
      val stringValues = getOrderedListValueByKeysStringResult(listKeys, resultMap, List()).mkString(";")
      val stringResult = number.toString + ";" + stringValues
      fromListLawnMowerCsvToString(listKeys, tail, number + 1, result + stringResult + System.lineSeparator())

    case _ => result
  }

  @tailrec
  private def getOrderedListValueByKeysStringResult(keys: List[String], map: Map[String, String], result: List[String]): List[String] = keys match {
    case head :: tail => getOrderedListValueByKeysStringResult(tail, map, map.getOrElse(head, "") :: result)
    case _ => result.reverse
  }

  @tailrec
  private def setupResultMap(listKeys: List[String], result: Map[String, String]): Map[String, String] = listKeys match {
    case head :: tail => setupResultMap(tail, result + (head -> ""))
    case _ => result
  }

  private def fromLawnMowerCsvToMapStrings(value: CsvValue, result: Map[String, String]): Map[String, String] = value match {
    case CsvObject(underlying) =>
      fromLawnMowerPropertiesToMapStrings(underlying, result)
    case _ => result
  }

  private def fromLawnMowerPropertiesToMapStrings(properties: Map[String, CsvValue], result: Map[String, String]): Map[String, String] = {
    val startResults = fromPositionToMapStrings(PREFIX_START, properties.get("start"), result)
    val endResults = fromPositionToMapStrings(PREFIX_END, properties.get("end"), startResults)
    fromPositionToMapStrings("", properties.get("instructions"), endResults)
  }

  private def fromPositionToMapStrings(prefix: String, maybeValue: Option[CsvValue], result: Map[String, String]): Map[String, String] = maybeValue match {
    case Some(value) => fromCsvValueToMapStrings(prefix, value, result)
    case _ => Map()
  }

  private def fromCsvValueToMapStrings(prefix: String, value: CsvValue, result: Map[String, String]): Map[String, String] = value match {
    case CsvObject(underlying) => fromListPairToMapStrings(prefix, underlying.toList, result)
    case CsvArray(value) => fromInstructionsCvsToMapString(value, result)
    case _ => result
  }

  def fromInstructionsCvsToMapString(list: List[CsvValue], result: Map[String, String]): Map[String, String] = {
    val instructions = fromListInstructionsToString(list, "")
    result + ("instructions" -> instructions)
  }

  @tailrec
  private def fromListPairToMapStrings(prefix: String, list: List[(String, CsvValue)], result: Map[String, String]): Map[String, String] = list match {
    case head :: tail =>
      val newResult = fromPairToMapStrings(prefix, head, result)
      fromListPairToMapStrings(prefix, tail, result ++ newResult)
    case _ => result
  }

  private def fromPairToMapStrings(prefix: String, pair: (String, CsvValue), result: Map[String, String]): Map[String, String] = pair match {
    case ("x", csvNumber: CsvNumber) =>
      result + (prefix + "x" -> csvNumber.value.toString)
    case ("y", csvNumber: CsvNumber) => result + (prefix + "y" -> csvNumber.value.toString)
    case ("direction", csvString: CsvString) => result + (prefix + "direction" -> csvString.value)
    case (_, csvObject: CsvObject) =>
      fromCsvValueToMapStrings(prefix, csvObject, result)
    case _ =>
      Map()
  }

  @tailrec
  private def fromListInstructionsToString(listCsvValue: List[CsvValue], result: String): String = listCsvValue match {
    case head :: tail =>
      val newResult = fromInstructionToString(head)
      fromListInstructionsToString(tail, result + newResult)
    case _ => result
  }

  private def fromInstructionToString(value: CsvValue): String = value match {
    case CsvString(value) => value
    case _ =>
      ""
  }
}

object CreateStringCsvFromResults {
  private val PREFIX_START = "début_"
  private val PREFIX_END = "fin_"

  def apply(): CreateStringCsvFromResults = new CreateStringCsvFromResults()
}