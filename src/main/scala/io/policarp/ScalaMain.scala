package io.policarp

import io.policarp.dijkstra.Node
import io.policarp.dijkstra.Dijkstra.findPath

object ScalaMain extends App {
  val d = findPath(ExampleGraph.graph, Node("yyz"), Node("syd"))
  println(d)
  println(d.map(edge => edge.distance.toChar))
}
