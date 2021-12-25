package fr.esgi.al.funprog.model


case class LawnMower
(start: (Point, Direction),
 instructions: List[Instruction],
 end: (Point, Direction)
)
