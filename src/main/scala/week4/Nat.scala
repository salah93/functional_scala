package week4

import java.util.NoSuchElementException


abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat = new Succ(this)
  def + (that: Nat): Nat
  def - (that: Nat): Nat
}

object Zero extends Nat {
  def isZero: Boolean = true
  def predecessor: Nat = throw new NoSuchElementException
  def + (that: Nat): Nat = that
  def - (that: Nat): Nat = if (that.isZero) this else throw new NoSuchElementException
}

class Succ(n: Nat) extends Nat {
  def isZero = false
  def predecessor: Nat = n
  def + (that: Nat): Nat = new Succ(n + that)
  def - (that: Nat): Nat = if (that.isZero) this else new Succ(n - that.predecessor)
}