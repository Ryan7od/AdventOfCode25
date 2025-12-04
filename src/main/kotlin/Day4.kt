package org.example

import java.io.File

fun main() {
    d4p2()
}

fun d4p2() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d4.txt").bufferedReader()
    val inputLines =
        reader.useLines { lines ->
            lines.toList().map { it.toMutableList() }
        }

    var counter = 0L
    val width = inputLines[0].size
    val height = inputLines.size
    val clear = { char: Char -> char == '.' }
    var indeciesToRemove: MutableList<Pair<Int, Int>>

    do {
        indeciesToRemove = mutableListOf()

        // Inside
        for (i in 1..<height - 1) {
            for (j in 1..<width - 1) {
                if (!clear(inputLines[i][j]) &&
                    listOf(
                        inputLines[i - 1][j - 1],
                        inputLines[i - 1][j],
                        inputLines[i - 1][j + 1],
                        inputLines[i][j + 1],
                        inputLines[i + 1][j + 1],
                        inputLines[i + 1][j],
                        inputLines[i + 1][j - 1],
                        inputLines[i][j - 1],
                    ).count { !clear(it) } < 4
                ) {
                    indeciesToRemove.add(Pair(i, j))
                }
            }
        }

        // Outside
        for (j in 1..<width - 1) {
            if (!clear(inputLines[0][j]) &&
                listOf(
                    inputLines[0][j - 1],
                    inputLines[0][j + 1],
                    inputLines[1][j - 1],
                    inputLines[1][j + 1],
                    inputLines[1][j],
                ).count { !clear(it) } < 4
            ) {
                indeciesToRemove.add(Pair(0, j))
            }

            if (!clear(inputLines[height - 1][j]) &&
                listOf(
                    inputLines[height - 1][j - 1],
                    inputLines[height - 1][j + 1],
                    inputLines[height - 2][j - 1],
                    inputLines[height - 2][j + 1],
                    inputLines[height - 2][j],
                ).count { !clear(it) } < 4
            ) {
                indeciesToRemove.add(Pair(height - 1, j))
            }
        }

        for (i in 1..<height - 1) {
            if (!clear(inputLines[i][0]) &&
                listOf(
                    inputLines[i - 1][0],
                    inputLines[i + 1][0],
                    inputLines[i - 1][1],
                    inputLines[i + 1][1],
                    inputLines[i][1],
                ).count { !clear(it) } < 4
            ) {
                indeciesToRemove.add(Pair(i, 0))
            }

            if (!clear(inputLines[i][width - 1]) &&
                listOf(
                    inputLines[i - 1][width - 1],
                    inputLines[i + 1][width - 1],
                    inputLines[i - 1][width - 2],
                    inputLines[i + 1][width - 2],
                    inputLines[i][width - 2],
                ).count { !clear(it) } < 4
            ) {
                indeciesToRemove.add(Pair(i, width - 1))
            }
        }

        listOf(
            Pair(0, 0),
            Pair(0, width - 1),
            Pair(height - 1, width - 1),
            Pair(height - 1, 0),
        ).forEach { if (inputLines[it.first][it.second] == '@') indeciesToRemove.add(it) }

        indeciesToRemove.forEach { inputLines[it.first][it.second] = '.' }
        counter += indeciesToRemove.size
    } while (indeciesToRemove.isNotEmpty())

    println(counter)
}

fun d4p1() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d4.txt").bufferedReader()
    val inputLines =
        reader.useLines { lines ->
            lines.toList().map { it.toList() }
        }

    var counter = 0L
    val width = inputLines[0].size
    val height = inputLines.size
    val clear = { char: Char -> char == '.' }

    // Inside
    for (i in 1..<height - 1) {
        for (j in 1..<width - 1) {
            if (!clear(inputLines[i][j]) &&
                listOf(
                    inputLines[i - 1][j - 1],
                    inputLines[i - 1][j],
                    inputLines[i - 1][j + 1],
                    inputLines[i][j + 1],
                    inputLines[i + 1][j + 1],
                    inputLines[i + 1][j],
                    inputLines[i + 1][j - 1],
                    inputLines[i][j - 1],
                ).count { !clear(it) } < 4
            ) {
                counter++
            }
        }
    }

    // Outside
    for (j in 1..<width - 1) {
        if (!clear(inputLines[0][j]) &&
            listOf(
                inputLines[0][j - 1],
                inputLines[0][j + 1],
                inputLines[1][j - 1],
                inputLines[1][j + 1],
                inputLines[1][j],
            ).count { !clear(it) } < 4
        ) {
            counter++
        }

        if (!clear(inputLines[height - 1][j]) &&
            listOf(
                inputLines[height - 1][j - 1],
                inputLines[height - 1][j + 1],
                inputLines[height - 2][j - 1],
                inputLines[height - 2][j + 1],
                inputLines[height - 2][j],
            ).count { !clear(it) } < 4
        ) {
            counter++
        }
    }

    for (i in 1..<height - 1) {
        if (!clear(inputLines[i][0]) &&
            listOf(
                inputLines[i - 1][0],
                inputLines[i + 1][0],
                inputLines[i - 1][1],
                inputLines[i + 1][1],
                inputLines[i][1],
            ).count { !clear(it) } < 4
        ) {
            counter++
        }

        if (!clear(inputLines[i][width - 1]) &&
            listOf(
                inputLines[i - 1][width - 1],
                inputLines[i + 1][width - 1],
                inputLines[i - 1][width - 2],
                inputLines[i + 1][width - 2],
                inputLines[i][width - 2],
            ).count { !clear(it) } < 4
        ) {
            counter++
        }
    }

    counter +=
        listOf(
            inputLines[0][0],
            inputLines[0][width - 1],
            inputLines[height - 1][width - 1],
            inputLines[height - 1][0],
        ).count { it == '@' }

    println(counter)
}
