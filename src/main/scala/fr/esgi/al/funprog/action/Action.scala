package fr.esgi.al.funprog.action

import fr.esgi.al.funprog.model.{Direction, Instruction, Point}

trait Action[A, B] {
  def run(data: A): B
}

object Action {
  implicit object ActionMove extends Action[(Point, Direction), Point] {
    override def run(data: (Point, Direction)): Point = data match {
      case (point: Point, Direction.N) => Point(point.x, point.y + 1)
      case (point: Point, Direction.S) => Point(point.x, point.y - 1)
      case (point: Point, Direction.E) => Point(point.x + 1, point.y)
      case (point: Point, Direction.W) => Point(point.x - 1, point.y)
      case _ => data._1
    }
  }

  implicit object ActionTurn extends Action[(Instruction, Direction), Direction] {
    override def run(data: (Instruction, Direction)): Direction = data match {
      case (Instruction.D, direction: Direction) => withInstructionD(direction)
      case (Instruction.G, direction: Direction) => withInstructionG(direction)
      case _ => data._2
    }

    private def withInstructionD(direction: Direction): Direction = direction match {
      case Direction.N => Direction.E
      case Direction.E => Direction.S
      case Direction.S => Direction.W
      case Direction.W => Direction.N
      case _ => direction
    }

    private def withInstructionG(direction: Direction): Direction = direction match {
      case Direction.N => Direction.W
      case Direction.E => Direction.N
      case Direction.S => Direction.E
      case Direction.W => Direction.S
      case _ => direction
    }
  }

  def of[A, B](implicit action: Action[A, B]): Action[A, B] = action
}