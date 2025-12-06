package org.example

import java.io.File
import kotlin.math.min

fun main() {
    d6p2()
}

fun d6p2() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d6.txt").bufferedReader()
    val inputLines =
        reader.useLines { lines ->
            lines.toList()
        }

    var counter = 0L
    val opSize = inputLines.size - 1

    var col = 0
    while (col < inputLines[0].length) {
        // Find end of col
        var endOfCol: Int = inputLines[0].length
        for (i in col..<endOfCol) {
            var allSpace = true
            for (row in 0..<opSize) {
                if (inputLines[row][i] != ' ') {
                    allSpace = false
                    break
                }
            }
            if (allSpace) {
                endOfCol = i
                break
            }
        }

        val op: (Long, Int) -> Long
        var acc: Long

        if (inputLines[opSize].substring(col, min(endOfCol, inputLines[opSize].length)).trim() == "*") {
            op = { a, b -> a * b }
            acc = 1
        } else {
            op = { a, b -> a + b }
            acc = 0
        }

        for (c in col..<endOfCol) {
            var num = ""
            for (row in 0..<opSize) {
                num += inputLines[row][c]
            }

            acc = op(acc, num.trim().toInt())
        }

        counter += acc

        col = endOfCol + 1
    }

    println(counter)
}

fun d6p1() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d6.txt").bufferedReader()
    val inputLines =
        reader.useLines { lines ->
            lines.toList().map {
                it.trim().split("[ \t]+".toRegex()).map { e ->
                    if (e != "*" &&
                        e != "+"
                    ) {
                        e.toInt()
                    } else {
                        if (e == "*") 1 else 0
                    }
                }
            }
        }

    var counter = 0L
    val opSize = inputLines.size - 1

    for (col in inputLines[0].indices) {
        val op: (Long, Int) -> Long
        var acc: Long

        if (inputLines[opSize][col] == 1) {
            op = { a, b -> a * b }
            acc = 1
        } else {
            op = { a, b -> a + b }
            acc = 0
        }

        for (row in 0..<opSize) {
            acc = op(acc, inputLines[row][col])
        }

        counter += acc
    }

    println(counter)
}
