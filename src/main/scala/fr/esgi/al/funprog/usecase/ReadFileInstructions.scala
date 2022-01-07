package fr.esgi.al.funprog.usecase

import better.files.File
import fr.esgi.al.funprog.model.{Direction, Instruction, LawnMower, Point, Position}

import scala.collection.mutable.ListBuffer

class ReadFileInstructions {
  def execute(filename: String): (Point, List[LawnMower])  = {
    val f = File(filename)
    val listOfInstructions = f.lines.toList
    if (listOfInstructions.length % 2 == 1) {
      //throw new Error("Parsing of " + filename + "impossible")
    }

    val arraySizeOption = listOfInstructions.headOption

    val splitArraySize = arraySizeOption.fold(ifEmpty = {
      //throw new Error("Can't find array limits")
      Array.empty[Int]
    })(value => {
      value.split(' ').map(value => value.toInt)
    })

    val arrayDimension = Point(splitArraySize(0), splitArraySize(1))

    val lowMowersInformation = listOfInstructions.drop(1)
    val lawnMowers = new ListBuffer[LawnMower]()

    for (idx <- lowMowersInformation.indices by 2){
      val lowMowerPosition = lowMowersInformation(idx)
      val arrayLowMowerPosition = lowMowerPosition.split(' ')
      val startPoint = Point(arrayLowMowerPosition(0).toInt, arrayLowMowerPosition(1).toInt)
      val direction = Direction.mapFromString(arrayLowMowerPosition(2))

      val lowMowerInstructions = lowMowersInformation(idx + 1)
      val listInstructions = lowMowerInstructions.map(instruction => Instruction.mapFromChar(instruction))

      val lawnMower = LawnMower(start = Position(startPoint,direction), instructions = listInstructions.toList, end = Position(startPoint, direction))
      lawnMowers += lawnMower
    }

    (arrayDimension,lawnMowers.toList)
  }

}
object ReadFileInstructions {
  def apply(): ReadFileInstructions = new ReadFileInstructions()

}
