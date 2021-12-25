package fr.esgi.al.funprog

import fr.esgi.al.funprog.model.{LawnMower, Point}
import fr.esgi.al.funprog.usecase.{GetPointsOfOthers, StartGivenLawnMower}

import scala.annotation.tailrec


class FunProg(getPointsOfOthers: GetPointsOfOthers, startGivenLawnMower: StartGivenLawnMower) {
  def start(limit: Point, listLawnMower: List[LawnMower]): List[LawnMower] = {
    startLoop(limit, listLawnMower, List()).reverse
  }

  @tailrec
  private def startLoop(limit: Point, list: List[LawnMower], result: List[LawnMower]): List[LawnMower] = list match {
    case Nil => result
    case head :: tail =>
      val occupiedPoint = getPointsOfOthers.execute(head, tail ++ result)
      val finishedLawnMower = startGivenLawnMower.execute(limit, occupiedPoint, head)
      startLoop(limit, tail, finishedLawnMower :: result)
  }
}

object FunProg {
  def apply(getPointsOfOthers: GetPointsOfOthers, startGivenLawnMower: StartGivenLawnMower): FunProg = new FunProg(
    getPointsOfOthers,
    startGivenLawnMower
  )
}