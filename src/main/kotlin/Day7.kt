package org.example

import java.io.File

fun main() {
    d7p2()
}

fun d7p2() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d7.txt").bufferedReader()
    val inputLines =
        reader.useLines { lines ->
            lines.toList().map { it.toCharArray() }
        }

    val dp = Array(inputLines.size) { LongArray(inputLines[0].size) { 0 } }

    for (i in inputLines[1].indices) {
        if (inputLines[0][i] == 'S') dp[1][i] = 1
    }

    for (j in 2..<inputLines.size) {
        val line = inputLines[j]
        for (i in line.indices) {
            if (dp[j - 1][i] != 0L) {
                val num = dp[j - 1][i]
                if (inputLines[j][i] == '^') {
                    // Assume data structured so no index out of array
                    dp[j][i - 1] += num
                    dp[j][i + 1] += num
                } else {
                    dp[j][i] += num
                }
            }
        }
    }

    println(dp[inputLines.size - 1].sum())
}

fun d7p1() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d7.txt").bufferedReader()
    val inputLines =
        reader.useLines { lines ->
            lines.toList().map { it.toCharArray() }
        }

    var counter = 0L

    for (i in inputLines[1].indices) {
        if (inputLines[0][i] == 'S') inputLines[1][i] = '|'
    }
    for (j in 2..<inputLines.size) {
        val line = inputLines[j]
        for (i in line.indices) {
            if (inputLines[j - 1][i] == '|') {
                if (inputLines[j][i] == '^') {
                    counter++
                    // Assume data structured so no index out of array
                    if (inputLines[j][i - 1] == '.') inputLines[j][i - 1] = '|'
                    if (inputLines[j][i + 1] == '.') inputLines[j][i + 1] = '|'
                } else {
                    if (inputLines[j][i] == '.') inputLines[j][i] = '|'
                }
            }
        }
    }

//    println(inputLines.joinToString("\n") { it.joinToString("") })
    println(counter)
}
