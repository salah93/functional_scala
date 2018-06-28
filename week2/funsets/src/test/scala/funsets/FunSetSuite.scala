package funsets

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import FunSets._

import math.abs
/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }



  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  test("test singletontake") {
    val x = 1
    val one = singletonSet(x)
    assert(one(1) === true)
    assert(one(2) === false)
    assert(one(3) === false)
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
    val s7 = singletonSet(7)
    val s1000 = singletonSet(1000)
    val even: Set = (x: Int) => x % 2 === 0
    val odd: Set = (x: Int) => !even(x)
    val factors_3: Set = (x: Int) => x % 3 === 0
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains elements in both sets") {
    new TestSets {
      val s = intersect(odd, factors_3)
      assert(contains(s, 3), "Intersect 3")
      assert(contains(s, 9), "Intersect 9")
      assert(!contains(s, 5), "Intersect 5")
    }
  }

  test("difference") {
    new TestSets {
      val s = diff(factors_3, even)
      assert(contains(s, 3), "Diff 3")
      assert(contains(s, 9), "Diff 9")
      assert(!contains(s, 6), "Diff 6")
    }
  }

  test("filter") {
    new TestSets {
      val s = filter(even, (x => x % 4 != 0))
      assert(contains(s, 6), "filter 6")
      assert(contains(s, 10), "filter 10")
      assert(!contains(s, 8), "filter 8")
    }
  }

  test("forall") {
    new TestSets {
      val e = forall(even, (x => (x + 2) % 2 === 0))
      assert(e === true, "forall even")
      val o = forall(odd, (x => (x + 2) % 2 === 0))
      assert(o === false, "forall odd")
    }
  }

  test("exists") {
    new TestSets {
      val e = exists(even, (x => x === 800))
      assert(e === true, "exists 800")
      val o = exists(odd, (x => x % 2 === 0))
      assert(o === false, "exists even in odd")
    }
  }

  test("map") {
    new TestSets {
      val s = map(even, (x => x + 1))
      assert(contains(s, 1), "map even to odd 1")
      assert(contains(s, 3), "map even to odd 3")
      assert(!contains(s, 8), "map even to odd 8")
      assert(forall(s, x => (abs(x) % 2 == 1)), "forall map even to odd")

      // {1,3,4,5,7,1000}
      // {0,2,3,4,6,999}
      val set = union(union(union(union(union(union(s1, s2), s3), s4), s5), s7), s1000)
      val map_set = map(set, (x => x - 1))
      assert(contains(map_set, 0), "contains 0")
      assert(contains(map_set, 2), "contains 2")
      assert(contains(map_set, 3), "contains 3")
      assert(contains(map_set, 4), "contains 4")
      assert(contains(map_set, 6), "contains 6")
      assert(contains(map_set, 999), "contains 999")
      val map_set_even = map(set, (x => x * 2))
      assert(forall(map_set_even, x => (x % 2 == 0)), "forall map double all numbers")
    }
  }

}
