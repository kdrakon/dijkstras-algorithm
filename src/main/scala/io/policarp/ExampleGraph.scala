package io.policarp

import io.policarp.dijkstra.{Edge, Node}
import io.policarp.dijkstra.Dijkstra.Graph

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportAll}
import js.JSConverters._

@JSExport
object ExampleGraph {

  @JSExport
  lazy val graph : Graph = List(
    Edge(Node("teu"), Node("tyo"), 71),
    Edge(Node("tyo"), Node("vln"), 84),
    Edge(Node("vln"), Node("trn"), 79),
    Edge(Node("xiy"), Node("ztm"), 65),
    Edge(Node("syd"), Node("vno"), 2000),
    Edge(Node("vno"), Node("zrh"), 33),
    Edge(Node("zrh"), Node("stl"), 780),
    Edge(Node("stl"), Node("sin"), 400),
    Edge(Node("sin"), Node("ber"), 94),
    Edge(Node("yul"), Node("vno"), 257),
    Edge(Node("ztm"), Node("mnl"), 70),
    Edge(Node("hhn"), Node("yfc"), 71),
    Edge(Node("trn"), Node("nas"), 66),
    Edge(Node("cfn"), Node("teu"), 78),
    Edge(Node("hhn"), Node("vln"), 501),
    Edge(Node("ztm"), Node("hhn"), 760),
    Edge(Node("yyz"), Node("ber"), 73),
    Edge(Node("yyz"), Node("hhn"), 150),
    Edge(Node("ber"), Node("hhn"), 77),
    Edge(Node("cfn"), Node("tyo"), 242),
    Edge(Node("teu"), Node("trn"), 357),
    Edge(Node("mnl"), Node("led"), 137),
    Edge(Node("okj"), Node("ber"), 622),
    Edge(Node("okj"), Node("wns"), 48),
    Edge(Node("kul"), Node("zrh"), 1232),
    Edge(Node("okj"), Node("sel"), 1232),
    Edge(Node("ceb"), Node("hhn"), 2300),
    Edge(Node("lhr"), Node("maa"), 84),
    Edge(Node("maa"), Node("yul"), 72),
    Edge(Node("ztm"), Node("lhr"), 170),
    Edge(Node("yul"), Node("ztm"), 312),
    Edge(Node("mnl"), Node("lhr"), 65),
    Edge(Node("yul"), Node("ceb"), 69),
    Edge(Node("ztm"), Node("ceb"), 381),
    Edge(Node("ceb"), Node("syd"), 82),
    Edge(Node("hhn"), Node("syd"), 2102),
    Edge(Node("nas"), Node("xiy"), 69),
    Edge(Node("yfc"), Node("sel"), 79),
    Edge(Node("sel"), Node("cfn"), 73),
    Edge(Node("hhn"), Node("xiy"), 732),
    Edge(Node("wns"), Node("mex"), 48),
    Edge(Node("mex"), Node("kul"), 48),
    Edge(Node("nas"), Node("led"), 701),
    Edge(Node("led"), Node("tlv"), 20),
    Edge(Node("tlv"), Node("vie"), 923),
    Edge(Node("vie"), Node("maa"), 3022)
  )

  @JSExport
  lazy val graphJS = graph.toJSArray
}
