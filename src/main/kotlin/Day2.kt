package org.example

import java.io.File
import kotlin.math.abs

fun main() {
    d2p2()
}

fun d2p2() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d2.txt").bufferedReader()
    val inputLines = reader.readLine().split(',')

    var count = 0L

    inputLines.forEach {
        val pair = it.split('-')
        val first = pair[0].toLong()
        val second = pair[1].toLong()

        for (i in first..second) {
            val iString = i.toString()
            val n = iString.length

            for (j in 1..n/2) {
                if (n % j != 0) continue

                val list: MutableList<String> = mutableListOf()
                for (k in 0..<n/j) {
                    list += iString.substring(k*j, (k+1)*j)
                }

                val reference = list[0]
                if (list.all {thing -> thing == reference}) {
                    count += i
                    break
                }
            }
        }
    }

    println(count)
}

fun d2p1() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d2.txt").bufferedReader()
    val inputLines = reader.readLine().split(',')

    var count = 0L

    inputLines.forEach {
        val pair = it.split('-')
        val first = pair[0].toLong()
        val second = pair[1].toLong()

        for (i in first..second) {
            val iString = i.toString()
            val n = iString.length
            if (n % 2 == 1) continue

            val firstHalf = iString.substring(0, n / 2)
            val secondHalf = iString.substring(n / 2)

            if (firstHalf == secondHalf) {
                count += i
            }
        }
    }

    println(count)
}
