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
  private def getNewEnd(instructions: List[Instruction], end: Position, limit: Point): Position = instructions match {
    case head :: tail =>
      val updatedEnd = updateEndByInstruction(end, head, limit)
      getNewEnd(tail, updatedEnd, limit)
    case Nil => end
  }

  private def updateEndByInstruction(end: Position, instruction: Instruction, limit: Point): Position = instruction match {
    case Instruction.A =>
      val moveActionResult = Action.of[MoveAction].run(MoveAction(end.point, end.direction, limit))
      Position(moveActionResult.point, end.direction)
    case Instruction.D | Instruction.G =>
      val newDirection = getNewDirectionByInstruction(end.direction, instruction)
      Position(end.point, newDirection)
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