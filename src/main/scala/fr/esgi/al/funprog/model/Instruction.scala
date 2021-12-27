package fr.esgi.al.funprog.model

sealed trait Instruction extends Product with Serializable

object Instruction {
  final case object A extends Instruction

  final case object G extends Instruction

  final case object D extends Instruction

  final case object NotInstruction extends Instruction

  def mapFromString(maybeInstruction: String): Instruction =
    maybeInstruction match {
      case "A" => A
      case "G" => G
      case "D" => D
      case _   => NotInstruction
    }

  def mapFromChar(maybeInstruction: Char): Instruction = maybeInstruction match {
    case 'A'  => A
    case 'G'  => G
    case 'D'  => D
    case _   => NotInstruction
  }

  def mapToString(instruction: Instruction): String = instruction match {
    case A              => "A"
    case G              => "G"
    case D              => "D"
    case NotInstruction => "not instruction"
  }
}
