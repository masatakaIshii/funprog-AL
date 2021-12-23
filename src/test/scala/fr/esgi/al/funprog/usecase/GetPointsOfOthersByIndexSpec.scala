package fr.esgi.al.funprog.usecase

import fr.esgi.al.funprog.model.{Direction, LawnMower, Point}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class GetPointsOfOthersSpec extends AnyFunSpec {
  val getPointOthersLawnMowers: GetPointsOfOthers = GetPointsOfOthers()

  describe("when list lawnmower contain zero element") {
    describe("when curIndex is 0") {
      it("should return empty list") {
        getPointOthersLawnMowers.execute(0, List()) shouldBe List()
      }
    }

    describe("when curIndex is 1") {
      it("should return empty list") {
        getPointOthersLawnMowers.execute(1, List()) shouldBe List()
      }
    }

    describe("when curIndex is -1") {
      it("should return empty list") {
        getPointOthersLawnMowers.execute(-1, List()) shouldBe List()
      }
    }
  }

  describe("when list lawnmower contain only one element") {
    val listLawnmower = List(LawnMower(
      start = (Point(1, 2), Direction("N")),
      instructions = List("D"),
      end = (Point(1, 2), Direction("N"))
    ))
    describe("when curIndex is 0") {
      it("should return empty list") {
        getPointOthersLawnMowers.execute(0, listLawnmower) shouldBe List()
      }
    }

    describe("when curIndex is -1") {
      it("should return empty list") {
        getPointOthersLawnMowers.execute(-1, listLawnmower) shouldBe List()
      }
    }

    describe("when curIndex is 1") {
      it("should return empty list") {
        getPointOthersLawnMowers.execute(1, listLawnmower) shouldBe List()
      }
    }
  }

  describe("when list lawnmower contain 3 elements") {
    val listLawnmower = List(
      LawnMower(
        start = (Point(1, 2), Direction("N")),
        instructions = List("D"),
        end = (Point(1, 2), Direction("N"))
      ),
      LawnMower(
        start = (Point(3, 4), Direction("N")),
        instructions = List("D"),
        end = (Point(3, 4), Direction("N"))
      ),
      LawnMower(
        start = (Point(5, 6), Direction("N")),
        instructions = List("D"),
        end = (Point(5, 6), Direction("N"))
      ),
    )

    describe("when curIndex is 1") {
      val curIndex = 1
      it("should return list of end point other than lawnmower locate in current index of list") {
        val expectedList = List(
          Point(1, 2),
          Point(5, 6)
        )

        getPointOthersLawnMowers.execute(curIndex, listLawnmower) shouldBe expectedList
      }
    }
  }
}
