package io.policarp.dijkstra

import scala.annotation.tailrec
import scala.scalajs.js.annotation.{JSExport, JSExportAll}

@JSExportAll
case class Node(label: String = "-")

@JSExportAll
case class Edge(left: Node, right: Node, distance: Int = 0) {
  def contains(n: Node) = this.left == n || this.right == n
}

@JSExportAll
case class LabeledDistance(distance: Int, via : Node)

object Dijkstra {

  type Graph = List[Edge]

  val EmptyLabel = LabeledDistance(0, Node())

  @tailrec
  def dijkstra(graph: Graph, source: Node, target: Node, nodeDistanceMap: Map[Node, LabeledDistance] = Map()) : Map[Node, LabeledDistance] = {

    lazy val nodeIsSource = (n : Node) => n == source
    lazy val edgeFilter = (v : Edge) => nodeIsSource(v.left) || nodeIsSource(v.right)
    lazy val computeDistance = (n : Node, d : Int, via : Node) => Map(n -> LabeledDistance(nodeDistanceMap.getOrElse(source, EmptyLabel).distance + d, via))

    lazy val edgesFromSource = graph.filter(edgeFilter)
    lazy val nodesFromSource = edgesFromSource.map(edge => if (nodeIsSource(edge.left)) edge.right else edge.left)

    lazy val newNodeDistances = edgesFromSource
      .flatMap {
        case edge if edge.left == source => computeDistance(edge.right, edge.distance, edge.left)
        case edge if edge.right == source => computeDistance(edge.left, edge.distance, edge.right)
      }
      .map {
        case tuple @ Tuple2(node, newLabel) =>
          nodeDistanceMap.get(node).fold(tuple)(existingLabel => if (existingLabel.distance < newLabel.distance) node -> existingLabel else tuple)
      }

    lazy val newNodeDistanceMap = newNodeDistances.foldLeft(nodeDistanceMap)((distanceMap, nodeLabelTuple) => {
      distanceMap.updated(nodeLabelTuple._1, nodeLabelTuple._2)
    })

    lazy val newSources = newNodeDistances
      .filter {
        nodeLabelTuple => nodesFromSource.contains(nodeLabelTuple._1)
      }.sortBy {
        nodeLabelTuple => nodeLabelTuple._2.distance
      }

    if (source == target) return nodeDistanceMap
    if (newSources.isEmpty) return nodeDistanceMap

    lazy val nextSourceNode = newSources.head._1

    dijkstra(graph.filterNot(edgeFilter), nextSourceNode, target, newNodeDistanceMap)
  }

  def findPath(g : Graph, start : Node, end : Node) : List[Edge] = {

    lazy val labeling = dijkstra(g, start, end)

    def unrollPath(labeling : Map[Node, LabeledDistance], start: Node = end) : List[Node] =
      labeling.get(start).fold(List(start))(label => List(start) ++ unrollPath(labeling, label.via))

    def mapPathToEdges(path : List[Node]) : List[Edge] = {
      if (path.size == 1) return List()
      List(Edge(path.head, path.tail.head)) ++ mapPathToEdges(path.tail)
    }

    mapPathToEdges(unrollPath(labeling).reverse).flatMap {
      pathEdge =>
        g.filter( graphEdge => graphEdge.contains(pathEdge.left) && graphEdge.contains(pathEdge.right) )
    }
  }
}

@JSExport
object DijkstraJS {

  import Dijkstra._
  import scala.scalajs.js
  import js.JSConverters._

  @JSExport
  def dijkstra(g : Graph, start : String, end : String) : js.Dictionary[LabeledDistance] = {
    Dijkstra.dijkstra(g, Node(start), Node(end)).collect {
      case (node, labeledDistance) => node.label -> labeledDistance
    }.toJSDictionary
  }

  @JSExport
  def findPath(g : Graph, start : String, end : String) : js.Array[Edge] = {
    Dijkstra.findPath(g, Node(start), Node(end)).toJSArray
  }
}
