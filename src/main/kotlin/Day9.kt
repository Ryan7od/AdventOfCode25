package org.example

import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Polygon
import java.io.File
import kotlin.math.abs

fun main() {
    d9p2()
}

fun d9p2() {
    // read input
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d9.txt").bufferedReader()
    val points =
        reader.useLines { lines ->
            lines.toList().map { line ->
                val (x, y) = line.split(",")
                intArrayOf(x.toInt(), y.toInt())
            }
        }

    // build polygon
    val geomFactory = GeometryFactory()
    val coords =
        Array(points.size + 1) { i ->
            if (i == points.size) {
                val p0 = points[0]
                Coordinate(p0[0].toDouble(), p0[1].toDouble())
            } else {
                val p = points[i]
                Coordinate(p[0].toDouble(), p[1].toDouble())
            }
        }
    val polygon: Polygon = geomFactory.createPolygon(coords)

    // scan rectangles
    var best = 0L
    val n = points.size
    for (i in 0 until n) {
        val p1 = points[i]
        val x1 = p1[0]
        val y1 = p1[1]
        for (j in i + 1 until n) {
            val p2 = points[j]
            val x2 = p2[0]
            val y2 = p2[1]

            val width = abs(x1 - x2) + 1L
            val height = abs(y1 - y2) + 1L
            val area = width * height
            if (area <= best) continue

            val minX = minOf(x1, x2).toDouble()
            val maxX = maxOf(x1, x2).toDouble()
            val minY = minOf(y1, y2).toDouble()
            val maxY = maxOf(y1, y2).toDouble()

            // rectangle geometry
            val rectCoords =
                arrayOf(
                    Coordinate(minX, minY),
                    Coordinate(maxX, minY),
                    Coordinate(maxX, maxY),
                    Coordinate(minX, maxY),
                    Coordinate(minX, minY),
                )
            val rect = geomFactory.createPolygon(rectCoords)

            // inside check
            if (polygon.covers(rect)) {
                best = area
            }
        }
    }

    println(best)
}

fun d9p1() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d9.txt").bufferedReader()
    val inputLines =
        reader.useLines { lines ->
            lines.toList().map { it.split(",").map { it.toInt() }.toIntArray() }
        }

    // Precompute sizes
    val sizes =
        Array(inputLines.size) { Array(inputLines.size) { Long.MIN_VALUE } }

    for (i in inputLines.indices) {
        for (j in i + 1..<inputLines.size) {
            sizes[i][j] = (abs(inputLines[i][0] - inputLines[j][0]) + 1).toLong() * (abs(inputLines[i][1] - inputLines[j][1]) + 1).toLong()
        }
    }

    val result =
        sizes
            .flatten()
            .maxBy { it }

    println(result)
}
