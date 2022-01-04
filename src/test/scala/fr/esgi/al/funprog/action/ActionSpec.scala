package fr.esgi.al.funprog.action

import fr.esgi.al.funprog.model.{Direction, Point}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class ActionSpec extends AnyFunSpec {
  describe("ActionMove") {
    val actionMove = Action.ActionMove
    describe("when move action have point is (x=1, y=1)") {
      val point = Point(x = 1, y = 1)
      describe("and direction is N") {
        val direction = Direction.N
        it("should return move action with point (x=1, y=2) and direction N") {
          val moveAction = MoveAction(point, direction);
          val expectedMoveAction = MoveAction(
            Point(x = 1, y = 2),
            direction
          )
          actionMove.run(moveAction) shouldBe expectedMoveAction
        }
      }
      describe("and direction is S") {
        val direction = Direction.S
        it("should return move action with point (x=1, y=0) and direction N") {
          val moveAction = MoveAction(point, direction);
          val expectedMoveAction = MoveAction(
            Point(x = 1, y = 0),
            direction
          )
          actionMove.run(moveAction) shouldBe expectedMoveAction
        }
      }
      describe("and direction is E") {
        val direction = Direction.E
        it("should return move action with point (x=2, y=1) and direction E") {
          val moveAction = MoveAction(point, direction);
          val expectedMoveAction = MoveAction(
            Point(x = 2, y = 1),
            direction
          )
          actionMove.run(moveAction) shouldBe expectedMoveAction
        }
      }
      describe("and direction is W") {
        val direction = Direction.W
        it("should return move action with point (x=0, y=1) and direction W") {
          val moveAction = MoveAction(point, direction);
          val expectedMoveAction = MoveAction(
            Point(x = 0, y = 1),
            direction
          )
          actionMove.run(moveAction) shouldBe expectedMoveAction
        }
      }
    }
  }
  describe("ActionTurnRight") {
    val actionTurnRight = Action.ActionTurnRight
    describe("when direction is N") {
      val direction = Direction.N
      it("should return turn right with direction E") {
        val expectedData = TurnRight(Direction.E)
        actionTurnRight.run(TurnRight(direction)) shouldBe expectedData
      }
    }
    describe("when direction is E") {
      val direction = Direction.E
      it("should return turn right with direction S") {
        val expectedData = TurnRight(Direction.S)
        actionTurnRight.run(TurnRight(direction)) shouldBe expectedData
      }
    }
    describe("when direction is S") {
      val direction = Direction.S
      it("should return turn right with direction W") {
        val expectedData = TurnRight(Direction.W)
        actionTurnRight.run(TurnRight(direction)) shouldBe expectedData
      }
    }
    describe("when direction is W") {
      val direction = Direction.W
      it("should return turn right with direction N") {
        val expectedData = TurnRight(Direction.N)
        actionTurnRight.run(TurnRight(direction)) shouldBe expectedData
      }
    }
  }
  describe("ActionTurnRight") {
    val actionTurnLeft = Action.ActionTurnLeft
    describe("when direction is N") {
      val direction = Direction.N
      it("should return turn left with direction W") {
        val expectedData = TurnLeft(Direction.W)
        actionTurnLeft.run(TurnLeft(direction)) shouldBe expectedData
      }
    }
    describe("when direction is E") {
      val direction = Direction.E
      it("should return turn left with direction N") {
        val expectedData = TurnLeft(Direction.N)
        actionTurnLeft.run(TurnLeft(direction)) shouldBe expectedData
      }
    }
    describe("when direction is S") {
      val direction = Direction.S
      it("should return turn left with direction E") {
        val expectedData = TurnLeft(Direction.E)
        actionTurnLeft.run(TurnLeft(direction)) shouldBe expectedData
      }
    }
    describe("when direction is W") {
      val direction = Direction.W
      it("should return direction S") {
        val expectedData = TurnLeft(Direction.S)
        actionTurnLeft.run(TurnLeft(direction)) shouldBe expectedData
      }
    }
  }
}
