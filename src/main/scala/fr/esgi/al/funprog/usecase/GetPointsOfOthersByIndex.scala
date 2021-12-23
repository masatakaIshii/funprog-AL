package fr.esgi.al.funprog.usecase

import fr.esgi.al.funprog.model.{LawnMower, Point}

import scala.annotation.tailrec

class GetPointsOfOthersByIndex {
  def execute(concernedIndex: Int, listLawnMower: List[LawnMower]): List[Point] = {
    if (concernedIndex < 0 || listLawnMower.size <= concernedIndex) {
      List()
    } else {
      getWithoutIndex(0, concernedIndex, listLawnMower, List())
        .map(lawnMower => lawnMower.end._1)
        .reverse
    }
  }

  @tailrec
  private def getWithoutIndex(pos: Int, index: Int, list: List[LawnMower], result: List[LawnMower]): List[LawnMower] = list match {
    case head :: tail => if (pos == index) {
      getWithoutIndex(pos + 1, index, tail, result)
    } else {
      getWithoutIndex(pos + 1, index, tail, head :: result)
    }
    case Nil => result
  }
}

object GetPointsOfOthersByIndex {
  def apply(): GetPointsOfOthersByIndex = new GetPointsOfOthersByIndex()
}