package org.example

import java.io.File
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    d8p2()
}

fun d8p2() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d8.txt").bufferedReader()
    val inputLines =
        reader.useLines { lines ->
            lines.toList().map { it.split(",").map { it.toInt() }.toIntArray() }
        }

    // Precompute distances with ends
    val distances =
        Array(inputLines.size) { i -> Array(inputLines.size) { j -> Pair(Pair(i, j), Double.MAX_VALUE) } }

    for (i in inputLines.indices) {
        for (j in i + 1..<inputLines.size) {
            distances[i][j] =
                Pair(
                    Pair(i, j),
                    sqrt(
                        (inputLines[i][0] - inputLines[j][0]).toDouble().pow(2) +
                            (inputLines[i][1] - inputLines[j][1]).toDouble().pow(2) +
                            (inputLines[i][2] - inputLines[j][2]).toDouble().pow(2),
                    ),
                )
        }
    }

    // Find 1000 smallest pairs
    val smallest = distances.flatten().sortedBy { it.second }.map { it.first }

    // Create network structure
    val networks: MutableList<MutableSet<Int>> = mutableListOf()

    // Iterate and build networks
    for (pair in smallest) {
        var firstSet: MutableSet<Int>? = null
        var secondSet: MutableSet<Int>? = null
        for (network in networks) {
            for (junctionBox in network) {
                if (junctionBox == pair.first) firstSet = network
                if (junctionBox == pair.second) secondSet = network
            }
        }

        if (firstSet != null) {
            if (secondSet != null) {
                if (firstSet == secondSet) {
                } else {
                    val newNetwork = firstSet.union(secondSet).toMutableSet()
                    networks.remove(firstSet)
                    networks.remove(secondSet)
                    networks.add(newNetwork)
                }
            } else {
                firstSet.add(pair.second)
            }
        } else {
            if (secondSet != null) {
                secondSet.add(pair.first)
            } else {
                networks.add(mutableSetOf(pair.first, pair.second))
            }
        }

        if (networks.size == 1 && networks[0].size == inputLines.size) {
            val result = inputLines[pair.first][0] * inputLines[pair.second][0]
            println(result)
            break
        }
    }
}

fun d8p1() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d8.txt").bufferedReader()
    val inputLines =
        reader.useLines { lines ->
            lines.toList().map { it.split(",").map { it.toInt() }.toIntArray() }
        }

    // Precompute distances with ends
    val distances =
        Array(inputLines.size) { i -> Array(inputLines.size) { j -> Pair(Pair(i, j), Double.MAX_VALUE) } }

    for (i in inputLines.indices) {
        for (j in i + 1..<inputLines.size) {
            distances[i][j] =
                Pair(
                    Pair(i, j),
                    sqrt(
                        (inputLines[i][0] - inputLines[j][0]).toDouble().pow(2) +
                            (inputLines[i][1] - inputLines[j][1]).toDouble().pow(2) +
                            (inputLines[i][2] - inputLines[j][2]).toDouble().pow(2),
                    ),
                )
        }
    }

    // Find 1000 smallest pairs
    val smallest =
        distances
            .flatten()
            .sortedBy { it.second }
            .slice(0..<1000)
            .map { it.first }

    // Create network structure
    val networks: MutableList<MutableSet<Int>> = mutableListOf()

    // Iterate and build networks
    for (pair in smallest) {
        var firstSet: MutableSet<Int>? = null
        var secondSet: MutableSet<Int>? = null
        for (network in networks) {
            for (junctionBox in network) {
                if (junctionBox == pair.first) firstSet = network
                if (junctionBox == pair.second) secondSet = network
            }
        }

        if (firstSet != null) {
            if (secondSet != null) {
                if (firstSet == secondSet) {
                } else {
                    val newNetwork = firstSet.union(secondSet).toMutableSet()
                    networks.remove(firstSet)
                    networks.remove(secondSet)
                    networks.add(newNetwork)
                }
            } else {
                firstSet.add(pair.second)
            }
        } else {
            if (secondSet != null) {
                secondSet.add(pair.first)
            } else {
                networks.add(mutableSetOf(pair.first, pair.second))
            }
        }
    }

    val threeLargest = networks.sortedByDescending { it.size }.slice(0..<3)
    val result = threeLargest.fold(1) { acc, e -> acc * e.size }
    println(result)
}
