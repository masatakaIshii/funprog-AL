package fr.esgi.al.funprog.usecase

import fr.esgi.al.funprog.model.{Direction, LawnMower, Point}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class GetPointsOfOthersSpec extends AnyFunSpec {
  val getPointsOfOthers: GetPointsOfOthers = GetPointsOfOthers()

  describe("when list of lawnMower contain zero element") {
    it("should return empty list") {
      val lawnMower = LawnMower(
        start = (Point(1, 2), Direction("N")),
        instructions = List("D"),
        end = (Point(1, 2), Direction("N"))
      )
      getPointsOfOthers.execute(lawnMower, List()) shouldBe List()
    }
  }

  describe("when list of lawnMower contain one element") {
    val listLawnmower = List(LawnMower(
      start = (Point(1, 2), Direction("N")),
      instructions = List("D"),
      end = (Point(1, 2), Direction("N"))
    ))
    describe("when lawnMower not contain is list") {
      val lawnMower = LawnMower(
        start = (Point(1, 3), Direction("N")),
        instructions = List("D"),
        end = (Point(1, 3), Direction("N"))
      )
      it("should return param list") {
        getPointsOfOthers.execute(lawnMower, listLawnmower) shouldBe List(Point(1, 2))
      }
    }
  }

  describe("when list of lawnMower contain 3 elements") {
    val listLawnmower = List(
      LawnMower(
        start = (Point(1, 2), Direction("N")),
        instructions = List("D"),
        end = (Point(1, 5), Direction("N"))
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
    describe("when lawnMower is second element of list") {
      val second = LawnMower(
        start = (Point(3, 4), Direction("N")),
        instructions = List("D"),
        end = (Point(3, 4), Direction("N"))
      )
      it("should return list with 2 points elements without second lawnMower point") {
        val expectedList = List(
          Point(1, 5),
          Point(5, 6)
        )
        getPointsOfOthers.execute(second, listLawnmower) shouldBe expectedList
      }
    }
    describe("when lawnMower is first element of list") {
      val first = LawnMower(
        start = (Point(1, 2), Direction("N")),
        instructions = List("D"),
        end = (Point(1, 5), Direction("N"))
      )
      it("should return list with 2 points element without first lawnMower point") {
        val expectedList = List(
          Point(3, 4),
          Point(5, 6)
        )
        getPointsOfOthers.execute(first, listLawnmower) shouldBe expectedList
      }
    }
  }
}
