package fr.esgi.al.funprog.action

import fr.esgi.al.funprog.model.{Direction, Instruction, Point}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class ActionSpec extends AnyFunSpec {
  describe("ActionMove") {
    val actionMove = Action.ActionMove
    describe("when point is (x=1, y=1)") {
      val point = Point(x = 1, y = 1)
      describe("and direction is N") {
        val direction = Direction.N
        it("should return pair point (x=1, y=2) and direction N") {
          actionMove.run((point, direction)) shouldBe Point(x = 1, y = 2)
        }
      }
      describe("and direction is S") {
        val direction = Direction.S
        it("should return pair point (x=1, y=0) and direction N") {
          actionMove.run((point, direction)) shouldBe Point(x = 1, y = 0)
        }
      }
      describe("and direction is E") {
        val direction = Direction.E
        it("should return pair point (x=2, y=1) and direction E") {
          actionMove.run((point, direction)) shouldBe Point(x = 2, y = 1)
        }
      }
      describe("and direction is W") {
        val direction = Direction.W
        it("should return pair point (x=0, y=1) and direction W") {
          actionMove.run((point, direction)) shouldBe Point(x = 0, y = 1)
        }
      }
    }
  }

  describe("ActionTurn") {
    val actionTurn = Action.ActionTurn
    describe("when instruction is D") {
      val instruction = Instruction.D
      describe("when direction is N") {
        val direction = Direction.N
        it("should return direction E") {
          actionTurn.run((instruction, direction)) shouldBe Direction.E
        }
      }
      describe("when direction is E") {
        val direction = Direction.E
        it("should return direction S") {
          actionTurn.run((instruction, direction)) shouldBe Direction.S
        }
      }
      describe("when direction is S") {
        val direction = Direction.S
        it("should return direction W") {
          actionTurn.run((instruction, direction)) shouldBe Direction.W
        }
      }
      describe("when direction is W") {
        val direction = Direction.W
        it("should return direction N") {
          actionTurn.run((instruction, direction)) shouldBe Direction.N
        }
      }
    }
    describe("when instruction is G") {
      val instruction = Instruction.G
      describe("when direction is N") {
        val direction = Direction.N
        it("should return direction W") {
          actionTurn.run((instruction, direction)) shouldBe Direction.W
        }
      }
      describe("when direction is E") {
        val direction = Direction.E
        it("should return direction N") {
          actionTurn.run((instruction, direction)) shouldBe Direction.N
        }
      }
      describe("when direction is S") {
        val direction = Direction.S
        it("should return direction E") {
          actionTurn.run((instruction, direction)) shouldBe Direction.E
        }
      }
      describe("when direction is W") {
        val direction = Direction.W
        it("should return direction S") {
          actionTurn.run((instruction, direction)) shouldBe Direction.S
        }
      }
    }
  }
}
