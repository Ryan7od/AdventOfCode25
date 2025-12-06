package org.example

import java.io.File

fun main() {
    d5p2()
}

fun d5p2() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d5.txt").bufferedReader()
    val inputLines =
        reader.useLines { lines ->
            lines.toList()
        }

    var counter = 0L
    val freshRanges = mutableListOf<Pair<Long, Long>>()

    // Ranges
    for (i in inputLines.indices) {
        val line = inputLines[i]
        if (line == "") {
            break
        }

        val split = line.split('-').map { it.toLong() }
        val range = Pair(split[0], split[1])

        var foundOverlap = false
        // If overlapping range
        for (j in freshRanges.indices) {
            val pair = freshRanges[j]

            if (range.first >= pair.first && range.first <= pair.second) {
                foundOverlap = true
                if (range.second >= pair.first && range.second <= pair.second) {
                } else {
                    // Last pair
                    if (j + 1 >= freshRanges.size) {
                        freshRanges[j] = Pair(pair.first, split[1])
                    } else {
                        // Find what pair second is in / before
                        var secondIndex = -1
                        var before = false
                        for (k in j + 1..<freshRanges.size) {
                            val pair2 = freshRanges[k]
                            if (range.second >= pair2.first && range.second <= pair2.second) {
                                secondIndex = k
                                break
                            }
                            if (range.second < pair2.first) {
                                secondIndex = k - 1
                                before = true
                                break
                            }
                        }

                        // Insert new range and delete old
                        val firstVal = pair.first
                        val secondVal = if (before) range.second else freshRanges[secondIndex].second

                        freshRanges.subList(j, secondIndex + 1).clear()
                        freshRanges.add(j, Pair(firstVal, secondVal))
                        break
                    }
                }
            } else if (range.second >= pair.first && range.second <= pair.second) {
                foundOverlap = true

                freshRanges[j] = Pair(range.first, pair.second)
            }
        }

        // Otherwise add new range
        if (!foundOverlap) {
            val index = freshRanges.binarySearch(range, compareBy { it.first })
            val insertIndex = if (index >= 0) index else -index - 1
            freshRanges.add(insertIndex, range)
        }
    }

    for (range in freshRanges) {
        counter += range.second - range.first + 1
    }

    println(counter)
}

fun d5p1() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d5.txt").bufferedReader()
    val inputLines =
        reader.useLines { lines ->
            lines.toList()
        }

    var counter = 0L
    var splitI = -1
    val freshRanges = mutableListOf<Pair<Long, Long>>()

    // Ranges
    for (i in inputLines.indices) {
        val line = inputLines[i]
        if (line == "") {
            splitI = i
            break
        }

        val split = line.split('-').map { it.toLong() }
        val range = Pair(split[0], split[1])

        var foundOverlap = false
        // If overlapping range
        for (j in freshRanges.indices) {
            val pair = freshRanges[j]

            if (range.first >= pair.first && range.first <= pair.second) {
                foundOverlap = true
                if (range.second >= pair.first && range.second <= pair.second) {
                } else {
                    // Last pair
                    if (j + 1 >= freshRanges.size) {
                        freshRanges[j] = Pair(pair.first, split[1])
                    } else {
                        // Find what pair second is in / before
                        var secondIndex = -1
                        var before = false
                        for (k in j + 1..<freshRanges.size) {
                            val pair2 = freshRanges[k]
                            if (range.second >= pair2.first && range.second <= pair2.second) {
                                secondIndex = k
                                break
                            }
                            if (range.second < pair2.first) {
                                secondIndex = k - 1
                                before = true
                                break
                            }
                        }

                        // Insert new range and delete old
                        val firstVal = pair.first
                        val secondVal = if (before) range.second else freshRanges[secondIndex].second

                        freshRanges.subList(j, secondIndex + 1).clear()
                        freshRanges.add(j, Pair(firstVal, secondVal))
                        break
                    }
                }
            } else if (range.second >= pair.first && range.second <= pair.second) {
                foundOverlap = true

                freshRanges[j] = Pair(range.first, pair.second)
            }
        }

        // Otherwise add new range
        if (!foundOverlap) {
            val index = freshRanges.binarySearch(range, compareBy { it.first })
            val insertIndex = if (index >= 0) index else -index - 1
            freshRanges.add(insertIndex, range)
        }
    }

    for (i in splitI + 1..<inputLines.size) {
        for (j in freshRanges.indices) {
            val id = inputLines[i].toLong()
            val range = freshRanges[j]
            if (id >= range.first && id <= range.second) {
                counter++
                break
            }
        }
    }

    println(counter)
}
