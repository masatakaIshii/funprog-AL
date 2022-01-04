package fr.esgi.al.funprog.action

import fr.esgi.al.funprog.model.{Direction, Point}

case class MoveAction
(
  point: Point,
  direction: Direction
)