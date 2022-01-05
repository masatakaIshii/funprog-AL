package fr.esgi.al.funprog.action

import fr.esgi.al.funprog.model.{Direction, Point}

trait Action[A] {
  def run(data: A): A
}

object Action {
  implicit object ActionMove extends Action[MoveAction] {
    override def run(data: MoveAction): MoveAction = data.direction match {
      case Direction.N => buildNewDataIfLimitYGreaterThanPointY(data)(Point(data.point.x, data.point.y + 1))
      case Direction.S => buildNewDataIfPointYMoreThanZero(data)(Point(data.point.x, data.point.y - 1))
      case Direction.E => buildNewDataIfLimitXGreaterThanPointX(data)(Point(data.point.x + 1, data.point.y))
      case Direction.W => buildNewDataIfPointXMoreThanZero(data)(Point(data.point.x - 1, data.point.y))
      case _ => data
    }
    private def buildNewDataIfLimitYGreaterThanPointY =
      buildNewDataIfValid((data: MoveAction) => data.limit.y > data.point.y) _

    private def buildNewDataIfPointYMoreThanZero =
      buildNewDataIfValid((data: MoveAction) => data.point.y > 0) _

    private def buildNewDataIfLimitXGreaterThanPointX =
      buildNewDataIfValid((data: MoveAction) => data.limit.x > data.point.x) _

    private def buildNewDataIfPointXMoreThanZero =
      buildNewDataIfValid((data: MoveAction) => data.point.x > 0) _

    private def buildNewDataIfValid
    (isValid: MoveAction => Boolean)
    (data: MoveAction)
    (newPoint: Point)
    : MoveAction =
      if (isValid(data)) {
        data.copy(point = newPoint)
      } else {
        data
      }
  }

  implicit object ActionTurnRight extends Action[TurnRight] {
    override def run(data: TurnRight): TurnRight = data.direction match {
      case Direction.N => TurnRight(Direction.E)
      case Direction.E => TurnRight(Direction.S)
      case Direction.S => TurnRight(Direction.W)
      case Direction.W => TurnRight(Direction.N)
      case _ => data
    }
  }

  implicit object ActionTurnLeft extends Action[TurnLeft] {
    override def run(data: TurnLeft): TurnLeft = data.direction match {
      case Direction.N => TurnLeft(Direction.W)
      case Direction.E => TurnLeft(Direction.N)
      case Direction.S => TurnLeft(Direction.E)
      case Direction.W => TurnLeft(Direction.S)
      case _ => data
    }
  }

  def of[A](implicit action: Action[A]): Action[A] = action

}