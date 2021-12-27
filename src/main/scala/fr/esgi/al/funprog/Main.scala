package fr.esgi.al.funprog

import fr.esgi.al.funprog.usecase.{GetPointsOfOthers, ReadFileInstructions, StartGivenLawnMower}

object Main extends App {
  println("Ici le programme principal")
  val test = ReadFileInstructions().execute("tondeuse.txt")
  val funProg =FunProg.apply(getPointsOfOthers = GetPointsOfOthers(), startGivenLawnMower = StartGivenLawnMower())
  val uesh = funProg.start(test._1, test._2)
  println(uesh)
  // Le code suivant ne compilera pas.
  // var tmp = null;
  // var tmp2 = if (tmp == 1) "yes" else 1

  // println(s"tmp: $tmp, tmp2: $tmp2")
}
