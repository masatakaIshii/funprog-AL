package fr.esgi.al.funprog

import fr.esgi.al.funprog.model.{LawnMower, Point}
import fr.esgi.al.funprog.usecase.StartGivenLawnMower

import scala.annotation.tailrec


class FunProg(startGivenLawnMower: StartGivenLawnMower) {
  def start(limit: Point, listLawnMower: List[LawnMower]): List[LawnMower] = {
    startLoop(limit, listLawnMower, List()).reverse
  }

  @tailrec
  private def startLoop(limit: Point, list: List[LawnMower], result: List[LawnMower]): List[LawnMower] = list match {
    case Nil => result
    case head :: tail =>
      val finishedLawnMower = startGivenLawnMower.execute(limit, head)
      startLoop(limit, tail, finishedLawnMower :: result)
  }
}

object FunProg {
  def apply(startGivenLawnMower: StartGivenLawnMower): FunProg = new FunProg(startGivenLawnMower)
}