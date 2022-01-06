package fr.esgi.al.funprog.parser

import fr.esgi.al.funprog.parser.csv.{CsvArray, CsvNumber, CsvString, CsvValue}

trait WritesCsv[A] {
  def writes(value: A): CsvValue
}

object WritesCsv {

  implicit def of[A](implicit w: WritesCsv[A]): WritesCsv[A] = w

  implicit object WritesCsvInt extends WritesCsv[Int] {
    override def writes(value: Int): CsvValue = CsvNumber(value)
  }

  implicit object WriteCsvString extends WritesCsv[String] {
    override def writes(value: String): CsvValue = CsvString(value)
  }

  implicit object WriteCsvArray extends WritesCsv[List[CsvValue]] {
    override def writes(value: List[CsvValue]): CsvValue = CsvArray(value)
  }
}
