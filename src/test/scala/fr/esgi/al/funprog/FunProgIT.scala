package fr.esgi.al.funprog

import fr.esgi.al.funprog.model._
import fr.esgi.al.funprog.usecase.StartGivenLawnMower
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class FunProgIT extends AnyFunSpec {
  val funProg: FunProg = FunProg(StartGivenLawnMower())

  describe("given limit, inputs and output provided by statement") {
    val limit = Point(x = 5, y = 5)
    val firstLawnMower = LawnMower(
      start = Position(
        point = Point(x = 1, y = 2),
        direction = Direction.N
      ),
      instructions = List(Instruction.G, Instruction.A, Instruction.G, Instruction.A, Instruction.G, Instruction.A, Instruction.G, Instruction.A, Instruction.A),
      end = Position(
        point = Point(x = 1, y = 2),
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
        point = Point(x = 3, y = 3),
        direction = Direction.E
      )
    )
    val inputList = List(firstLawnMower, secondLawnMower)

    val expectFirstLawnMower = firstLawnMower.copy(end = Position(
      point = Point(x = 1, y = 3),
      direction = Direction.N
    ))
    val expectSecondLawnMower = secondLawnMower.copy(end = Position(
      point = Point(x = 5, y = 1),
      direction = Direction.E
    ))
    val outputList = List(expectFirstLawnMower, expectSecondLawnMower)
    it("should return expected result") {
      funProg.start(limit, inputList) shouldBe outputList
    }
  }
  describe("given limit (x=1, y=1)") {
    val limit = Point(x = 1, y = 1)
    describe("first lawn mower") {
      val firstLawnMower = LawnMower(
        start = Position(
          point = Point(x = 0, y = 0),
          direction = Direction.N
        ),
        instructions = List(Instruction.A, Instruction.D, Instruction.A, Instruction.A, Instruction.A),
        end = Position(
          point = Point(x = 0, y = 0),
          direction = Direction.N
        )
      )
      describe("second lawn mower") {
        val secondLawnMower = LawnMower(
          start = Position(
            point = Point(x = 1, y = 1),
            direction = Direction.N
          ),
          instructions = List(Instruction.G, Instruction.A, Instruction.A),
          end = Position(
            point = Point(x = 1, y = 1),
            direction = Direction.N
          )
        )
        it("should return expected result") {
          val inputList = List(firstLawnMower, secondLawnMower)

          val expectedFirstLawnMower = firstLawnMower.copy(end = Position(point = Point(x = 1, y = 1), direction = Direction.E))
          val expectedSecondLawnMower = secondLawnMower.copy(end = Position(point = Point(x = 0, y = 1), direction = Direction.W))
          val outputList = List(expectedFirstLawnMower, expectedSecondLawnMower)

          funProg.start(limit, inputList) shouldBe outputList
        }
      }
    }
  }
}
