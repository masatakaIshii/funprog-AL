package fr.esgi.al.funprog.usecase

import fr.esgi.al.funprog.model.{LawnMower, Point}

class GetPointsOfOthers {
  def execute(concernedLawnMower: LawnMower, listLawnMower: List[LawnMower]): List[Point] = {
    listLawnMower
      .filter(lawnMower => lawnMower != concernedLawnMower)
      .map(lawnMower => lawnMower.end._1)
  }
}

object GetPointsOfOthers {
  def apply(): GetPointsOfOthers = new GetPointsOfOthers()
}