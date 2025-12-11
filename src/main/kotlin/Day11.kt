package org.example

import java.io.File

fun main() {
    d11p2()
}

fun d11p2() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d11.txt").bufferedReader()
    val mappings = reader.useLines { lines -> lines.toList().map { it.substring(0, 3) to it.substring(5).split(' ') } }.toMap()

    data class Key(
        val node: String,
        val seenDac: Boolean,
        val seenFft: Boolean,
    )

    val dp: MutableMap<Key, Long> = mutableMapOf()

    fun countPaths(
        node: String,
        seenDac: Boolean = false,
        seenFft: Boolean = false,
    ): Long {
        val seenDac = seenDac || node == "dac"
        val seenFft = seenFft || node == "fft"
        val key = Key(node, seenDac, seenFft)

        if (node == "out") {
            if (seenDac && seenFft) return 1L else return 0L
        }
        dp[key]?.let { return it }

        var total = 0L
        for (next in mappings[node].orEmpty()) {
            total += countPaths(next, seenDac, seenFft)
        }
        dp[key] = total
        return total
    }

    println(countPaths("svr"))
}

fun d11p1() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d11.txt").bufferedReader()
    val mappings = reader.useLines { lines -> lines.toList().map { it.substring(0, 3) to it.substring(5).split(' ') } }.toMap()

    val dp: MutableMap<String, Long> = mutableMapOf()

    fun countPaths(node: String): Long {
        if (node == "out") return 1L
        dp[node]?.let { return it }

        var total = 0L
        for (next in mappings[node].orEmpty()) {
            total += countPaths(next)
        }
        dp[node] = total
        return total
    }

    println(countPaths("you"))
}
