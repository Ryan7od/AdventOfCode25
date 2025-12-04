package org.example

import java.io.File
import kotlin.math.abs
import kotlin.math.pow

fun main() {
    d3p2()
}


fun d3p2() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d3.txt").bufferedReader()
    val inputLines = reader.useLines { lines ->
        lines.toList().map { it.map{ digit -> digit.digitToInt() }.toTypedArray() }
    }

    var counter = 0L

    inputLines.forEach {
        var indexLast = -1
        for (i in 11 downTo 0) {
            val searchSlice = it.slice(indexLast+1..<it.size-i)
            val maxAtStage = searchSlice.max()
            indexLast = searchSlice.indexOf(maxAtStage) + (indexLast + 1)
            counter += maxAtStage*(10.0.pow(i).toLong())
        }
    }

    println(counter)
}

fun d3p1() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d3.txt").bufferedReader()
    val inputLines = reader.useLines { lines ->
        lines.toList().map { it.map{ digit -> digit.digitToInt() }.toTypedArray() }
    }

    var counter = 0L

    inputLines.forEach {
        val maxTen = it.slice(0..<it.size-1).max()
        val indexMaxTen = it.indexOf(maxTen)
        val maxOne = it.slice(indexMaxTen+1..<it.size).max()

        counter += (maxTen*10 + maxOne)
    }

    println(counter)
}
