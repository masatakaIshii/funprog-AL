package fr.esgi.al.funprog.usecase

import fr.esgi.al.funprog.model._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class CreateStringCsvFromResultsIT extends AnyFunSpec {
  val createStringCsvFromResults: CreateStringCsvFromResults = CreateStringCsvFromResults()

  describe("given one lawnMower start=(point(x=1,y=2), direction.N), instructions=A, end=(point(x=2, y=1), direction.E)") {
    val lawnMower = LawnMower(
      start = Position(
        point = Point(x = 1, y = 2),
        direction = Direction.E
      ),
      instructions = List(Instruction.A),
      end = Position(
        point = Point(x = 2, y = 2),
        direction = Direction.E
      )
    )
    it("should return expected result") {
      val expectedResult =
        """numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions
          |1;1;2;E;2;2;E;A
          |""".stripMargin
      val result = createStringCsvFromResults.execute(List(lawnMower))
      result shouldBe expectedResult
    }
  }

  describe("given limit, inputs and output provided by statement") {
    val firstLawnMower = LawnMower(
      start = Position(
        point = Point(x = 1, y = 2),
        direction = Direction.N
      ),
      instructions = List(Instruction.G, Instruction.A, Instruction.G, Instruction.A, Instruction.G, Instruction.A, Instruction.G, Instruction.A, Instruction.A),
      end = Position(
        point = Point(x = 1, y = 3),
        direction = Direction.N
      )
    )
    val secondLawnMower = LawnMower(
      start = Position(
        point = Point(x = 3, y = 3),
        direction = Direction.E
      ),
      instructions = List(Instruction.A, Instruction.A, Instruction.D, Instruction.A, Instruction.A, Instruction.D, Instruction.A, Instruction.D, Instruction.D, Instruction.A),
      end = Position(
        point = Point(x = 5, y = 1),
        direction = Direction.E
      )
    )
    val lawnMowerList = List(firstLawnMower, secondLawnMower)
    it("should return expected result") {
      val result =
        """numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions
          |1;1;2;N;1;3;N;GAGAGAGAA
          |2;3;3;E;5;1;E;AADAADADDA
          |""".stripMargin

      createStringCsvFromResults.execute(lawnMowerList) shouldBe result
    }
  }
}
