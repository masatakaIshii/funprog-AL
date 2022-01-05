package fr.esgi.al.funprog.usecase

import fr.esgi.al.funprog.action.{Action, MoveAction, TurnLeft, TurnRight}
import fr.esgi.al.funprog.model._

import scala.annotation.tailrec

class StartGivenLawnMower {
  def execute(limit: Point, givenLawnMower: LawnMower): LawnMower = {
    val newEnd = getNewEnd(givenLawnMower.instructions, givenLawnMower.end, limit)
    givenLawnMower.copy(end = newEnd)
  }

  @tailrec
  private def getNewEnd(instructions: List[Instruction], end: (Point, Direction), limit: Point): (Point, Direction) = instructions match {
    case head :: tail =>
      val updatedEnd = updateEndByInstruction(end, head, limit)
      getNewEnd(tail, updatedEnd, limit)
    case Nil => end
  }

  private def updateEndByInstruction(end: (Point, Direction), instruction: Instruction, limit: Point): (Point, Direction) = instruction match {
    case Instruction.A =>
      val moveActionResult = Action.of[MoveAction].run(MoveAction(end._1, end._2, limit))
      (moveActionResult.point, end._2)
    case Instruction.D | Instruction.G =>
      val newDirection = getNewDirectionByInstruction(end._2, instruction)
      (end._1, newDirection)
    case _ => end
  }

  private def getNewDirectionByInstruction(oldDirection: Direction, instruction: Instruction): Direction = instruction match {
    case Instruction.D => Action.of[TurnRight].run(TurnRight(oldDirection)).direction
    case Instruction.G => Action.of[TurnLeft].run(TurnLeft(oldDirection)).direction
    case _ => oldDirection
  }
}

object StartGivenLawnMower {
  def apply(): StartGivenLawnMower = new StartGivenLawnMower()
}