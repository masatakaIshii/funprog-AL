package fr.esgi.al.funprog.usecase

import fr.esgi.al.funprog.action.Action
import fr.esgi.al.funprog.model._

import scala.annotation.tailrec

class StartGivenLawnMower {
  def execute(limit: Point, occupiedPoints: List[Point], givenLawnMower: LawnMower): LawnMower = {
    val newEnd = getNewEnd(givenLawnMower.instructions, givenLawnMower.end, occupiedPoints, limit)
    givenLawnMower.copy(end = newEnd)
  }

  @tailrec
  private def getNewEnd(instructions: List[Instruction], end: (Point, Direction), occupiedPoints: List[Point], limit: Point): (Point, Direction) = instructions match {
    case head :: tail =>
      val updatedEnd = updateEndByInstruction(end, head, occupiedPoints, limit)
      if (occupiedPoints.contains(updatedEnd._1)) {
        getNewEnd(tail, end, occupiedPoints, limit)
      } else {
        getNewEnd(tail, updatedEnd, occupiedPoints, limit)
      }
    case Nil => end
  }

  private def updateEndByInstruction(end: (Point, Direction), instruction: Instruction, occupiedPoints: List[Point], limit: Point): (Point, Direction) = instruction match {
    case Instruction.A =>
      val newPoint = Action.of[(Point, Direction), Point].run(end)
      if (occupiedPoints.contains(newPoint) || isPointNotCorrect(newPoint, limit)) {
        end
      } else {
        (newPoint, end._2)
      }
    case Instruction.D | Instruction.G =>
      val newDirection = Action.of[(Instruction, Direction), Direction].run((instruction, end._2))
      (end._1, newDirection)
    case _ => end
  }

  private def isPointNotCorrect(pointToCheck: Point, limit: Point): Boolean = {
    pointToCheck.x > limit.x || pointToCheck.y > limit.y || pointToCheck.x < 0 || pointToCheck.y < 0
  }
}

object StartGivenLawnMower {
  def apply(): StartGivenLawnMower = new StartGivenLawnMower()
}