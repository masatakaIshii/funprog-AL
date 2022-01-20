package fr.esgi.al.funprog.usecase

import better.files.File
import fr.esgi.al.funprog.exception.DonneesIncorectesException
import fr.esgi.al.funprog.model.Direction.NotDirection
import fr.esgi.al.funprog.model.Instruction.NotInstruction
import fr.esgi.al.funprog.model.{Direction, Instruction, LawnMower, Point, Position}

import scala.util.{Failure, Success, Try}

class ReadFileInstructions {
  def execute(filename: String): Try[(Point, List[LawnMower])]  = {
    val f = File(filename)
    val listOfInstructions = f.lines.toList
    if (listOfInstructions.length % 2 == 1 && listOfInstructions.length < 3) {
      Failure(DonneesIncorectesException("Number of lines incompatible"))
    } else {
      val arraySizeOption = listOfInstructions.headOption

      val splitArraySize = arraySizeOption.fold(ifEmpty = {
        Array.empty[Int]
      })(value => {
        value.split(' ').map(value => value.toInt)
      })
      if(splitArraySize.length < 2) {
        Failure(DonneesIncorectesException("Can't find array limits"))
      } else {

        val arrayDimension = Point(splitArraySize(0), splitArraySize(1))

        val lowMowersInformation = listOfInstructions.drop(1)
        generateLawnMower(lowMowersInformation, Success(List())) match {
          case Success(result) => Success((arrayDimension, result))
          case Failure(error) => Failure(error)
        }
      }
    }
  }

  def generateLawnMower(listInstruction: List[String], result: Try[List[LawnMower]]): Try[List[LawnMower]] = (result,listInstruction) match {
    case (Failure(exception),_) => Failure(exception)
    case (_, Nil) => result
    case (Success(previousList), first :: second :: rest) =>
      val arrayLowMowerPosition = first.split(' ')
      if (arrayLowMowerPosition.length < 3) {
        Failure(DonneesIncorectesException("Missing position or direction for lawnMower"))
      } else if (arrayLowMowerPosition(0).toInt < 0 || arrayLowMowerPosition(0).toInt > 5
        || arrayLowMowerPosition(1).toInt < 0 || arrayLowMowerPosition(1).toInt > 5) {
        Failure(DonneesIncorectesException("Position out of bound for loawnMower initialisation"))
      } else if (Direction.mapFromString(arrayLowMowerPosition(2)) == NotDirection) {
        Failure(DonneesIncorectesException("There is a direction that doesn't exist"))
      } else {
        val startPoint = Point(arrayLowMowerPosition(0).toInt, arrayLowMowerPosition(1).toInt)
        val direction = Direction.mapFromString(arrayLowMowerPosition(2))

        val listInstructions = second.map(instruction => Instruction.mapFromChar(instruction))
        if(listInstructions.contains(NotInstruction)) {
          Failure(DonneesIncorectesException("There are instruction that doesn't exist"))
        } else {
          val lawnMower = LawnMower(start = Position(startPoint, direction), instructions = listInstructions.toList, end = Position(startPoint, direction))

          generateLawnMower(rest, Success(previousList :+ lawnMower))
        }
      }
    case(Success(_),List(_)) => result

  }

}
object ReadFileInstructions {
  def apply(): ReadFileInstructions = new ReadFileInstructions()

}
