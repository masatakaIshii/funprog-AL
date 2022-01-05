package fr.esgi.al.funprog.usecase

import fr.esgi.al.funprog.model._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class StartGivenLawnMowerSpec extends AnyFunSpec {
  val startGivenLawnMower: StartGivenLawnMower = StartGivenLawnMower()

  describe("when limit is (x=1, y=1)") {
    val limit = Point(x = 1, y = 1)
    describe("when givenLawnMower point is (x=0, y=0), direction=W, instructions =['A', 'A', 'A']") {
      val lawnMower = LawnMower(
        start = Position(
          Point(0, 0),
          Direction.W
        ),
        instructions = List(Instruction.A, Instruction.A, Instruction.A),
        end = Position(
          Point(0, 0),
          Direction.W
        )
      )

      it("should not change point of lawnMower") {
        startGivenLawnMower.execute(limit, lawnMower) shouldBe lawnMower
      }
    }
  }

  describe("when limit is (x=3, y=3)") {
    val limit = Point(x = 3, y = 3)
    describe("when given lawnMower point is (x=1, y=1), instructions=[A]") {
      describe("and direction=N") {
        val lawnMower = LawnMower(
          start = Position(
            Point(x = 1, y = 1),
            Direction.N
          ),
          instructions = List(Instruction.A),
          end = Position(
            Point(x = 1, y = 1),
            Direction.N
          )
        )
        it("should change end point of result lawn mower to (x=1, y=2)") {
          val expectedLawnMower = lawnMower.copy(end = Position(
            Point(x = 1, y = 2),
            Direction.N
          ))
          startGivenLawnMower.execute(limit, lawnMower) shouldBe expectedLawnMower
        }
      }
      describe("and direction=E") {
        val lawnMower = LawnMower(
          start = Position(
            Point(x = 1, y = 1),
            Direction.E
          ),
          instructions = List(Instruction.A),
          end = Position(
            Point(x = 1, y = 1),
            Direction.E
          )
        )
        it("should change end point if result lawn mower to (x=1, y=2") {
          val expectedLawnMower = lawnMower.copy(end = Position(
            Point(x = 2, y = 1),
            Direction.E
          ))
          startGivenLawnMower.execute(limit, lawnMower) shouldBe expectedLawnMower
        }
      }
      describe("and direction=S") {
        val lawnMower = LawnMower(
          start = Position(
            Point(x = 1, y = 1),
            Direction.S
          ),
          instructions = List(Instruction.A),
          end = Position(
            Point(x = 1, y = 1),
            Direction.S
          )
        )
        it("should change end point if result lawn mower to (x=1, y=0") {
          val expectedLawnMower = lawnMower.copy(end = Position(
            Point(x = 1, y = 0),
            Direction.S
          ))
          startGivenLawnMower.execute(limit, lawnMower) shouldBe expectedLawnMower
        }
      }
      describe("and direction=W") {
        val lawnMower = LawnMower(
          start = Position(
            Point(x = 1, y = 1),
            Direction.W
          ),
          instructions = List(Instruction.A),
          end = Position(
            Point(x = 1, y = 1),
            Direction.W
          )
        )
        it("should change end point if result lawn mower to (x=0, y=1") {
          val expectedLawnMower = lawnMower.copy(end = Position(
            Point(x = 0, y = 1),
            Direction.W
          ))
          startGivenLawnMower.execute(limit, lawnMower) shouldBe expectedLawnMower
        }
      }
    }
    describe("when given lawnMover point is (x=1, y=1), direction=N") {
      describe("and instructions=[D]") {
        val lawnMower = LawnMower(
          start = Position(
            Point(x = 1, y = 1),
            Direction.N
          ),
          instructions = List(Instruction.D),
          end = Position(
            Point(x = 1, y = 1),
            Direction.N
          )
        )
        it("should change direction to direction=E") {
          val expectedLawnMower = lawnMower.copy(end = Position(
            Point(x = 1, y = 1),
            Direction.E
          ))
          startGivenLawnMower.execute(limit, lawnMower) shouldBe expectedLawnMower
        }
      }
      describe("and instructions=[G]") {
        val lawnMower = LawnMower(
          start = Position(
            Point(x = 1, y = 1),
            Direction.N
          ),
          instructions = List(Instruction.G),
          end = Position(
            Point(x = 1, y = 1),
            Direction.N
          )
        )
        it("should change direction to direction=W") {
          val expectedLawnMower = lawnMower.copy(end = Position(
            Point(x = 1, y = 1),
            Direction.W
          ))
          startGivenLawnMower.execute(limit, lawnMower) shouldBe expectedLawnMower
        }
      }
    }
  }
}
