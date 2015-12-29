package com.olvind
package requiresjs

import ammonite.ops._

object Run extends App {
  def indented(n: Int)(s: String) =
    println((" " * n) + s)

  def print(indent: Int)(r: Required): Unit = {
    r match {
      case Multiple(name, path, cs) =>
        println("-" * 20)
        indented(indent)(name.value)
        cs foreach print(indent + 2)
        println("-" * 20)
      case Single(compName, c) =>
        indented(indent)(compName.value)
        c.propsOpt.fold(())(_ foreach {case (name, tpe) => indented(indent + 2)(name + " -> " + tpe)})
    }
  }
  val ctx = new ScanCtx
  val result = Require(VarName("mui"), home / "pr" / "material-ui" / "lib", ctx)
  println(flattenScan(result).keySet)
}