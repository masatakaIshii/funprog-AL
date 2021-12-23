package fr.esgi.al.funprog.model


case class LawnMower(val start: (Point, Direction), val instructions: List[String], val end: (Point, Direction))
