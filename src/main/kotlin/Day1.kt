package org.example

import java.io.File
import kotlin.math.abs

fun main() {
    d1p2()
}

fun d1p2() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d1.txt").bufferedReader()
    val inputLines = reader.useLines { lines ->
        lines.toList()
    }

    var counter = 50
    var code = 0

    for(line in inputLines) {
        val direction: Boolean = line.get(0) == 'R'
        val num = line.substring(1).toInt()

        for (i in 1..num) {
            if (direction) counter++ else counter--

            counter %= 100
            if (counter < 0) counter += 100

            if (counter == 0) code++
        }
    }

    println(code)
}

fun d1p1() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d1.txt").bufferedReader()
    val inputLines = reader.useLines { lines ->
        lines.toList()
    }

    var counter = 50
    var code = 0

    for(line in inputLines) {
        val direction: Boolean = line.get(0) == 'R'
        val num = line.substring(1).toInt()

        if (direction) counter += num else counter -= num

        counter = counter % 100
        if (counter < 0) counter += 100

        if (counter == 0) code++
    }

    println(code)
}
