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
      val moveActionResult = Action.of[MoveAction].run(MoveAction(end._1, end._2))
      if (isPointNotCorrect(moveActionResult.point, limit)) {
        end
      } else {
        (moveActionResult.point, end._2)
      }
    case Instruction.D =>
      (end._1, Action.of[TurnRight].run(TurnRight(end._2)).direction)
    case Instruction.G =>
      (end._1, Action.of[TurnLeft].run(TurnLeft(end._2)).direction)
    case _ => end
  }

  private def isPointNotCorrect(pointToCheck: Point, limit: Point): Boolean = {
    pointToCheck.x > limit.x || pointToCheck.y > limit.y || pointToCheck.x < 0 || pointToCheck.y < 0
  }
}

object StartGivenLawnMower {
  def apply(): StartGivenLawnMower = new StartGivenLawnMower()
}