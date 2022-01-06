package fr.esgi.al.funprog.parser.csv

sealed trait CsvValue

case class CsvNumber(value: Int) extends CsvValue

case class CsvString(value: String) extends CsvValue

case class CsvArray(value: List[CsvValue]) extends CsvValue

case class CsvObject(private val underlying: Map[String, CsvValue]) extends CsvValue
