package fr.esgi.al.funprog.model

sealed trait Direction extends Product with Serializable

object Direction {
  final case object N extends Direction

  final case object S extends Direction

  final case object E extends Direction

  final case object W extends Direction

  final case object NotDirection extends Direction

  def mapFromString(maybeDirection: String): Direction = maybeDirection match {
    case "N" => N
    case "S" => S
    case "E" => E
    case "W" => W
    case _ => NotDirection
  }

  def mapToString(direction: Direction): String = direction match {
    case N => "N"
    case S => "S"
    case E => "E"
    case W => "W"
    case _ => "not direction"
  }
}
