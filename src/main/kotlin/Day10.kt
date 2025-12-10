package org.example

import com.google.ortools.Loader
import com.google.ortools.sat.CpModel
import com.google.ortools.sat.CpSolver
import com.google.ortools.sat.CpSolverStatus
import com.google.ortools.sat.IntVar
import com.google.ortools.sat.LinearExpr
import java.io.File

fun main() {
    d10p2()
}

data class State(
    val v: ByteArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is State) return false
        return v.contentEquals(other.v)
    }

    override fun hashCode(): Int = v.contentHashCode()
}

fun d10p2() {
    Loader.loadNativeLibraries()

    val reader =
        File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d10.txt").bufferedReader()
    val inputLines = reader.useLines { lines -> lines.toList() }

    var total = 0L

    for (line in inputLines) {
        val targetStr = line.substringBefore('}').substringAfter('{')
        val targetInts = targetStr.split(",").map { it.toInt() }.toIntArray()
        val counters = targetInts.size

        val buttonRegex = "\\(([^)]*)\\)".toRegex()
        val buttonsList = ArrayList<IntArray>()
        for (match in buttonRegex.findAll(line)) {
            val content = match.groupValues[1].trim()
            if (content.isEmpty()) continue
            val idxs = content.split(",").map { it.toInt() }.toIntArray()
            buttonsList.add(idxs)
        }
        if (buttonsList.isEmpty()) {
            if (targetInts.all { it == 0 }) {
                continue
            } else {
                continue
            }
        }

        val buttons = buttonsList.toTypedArray()
        val m = buttons.size
        val n = counters

        val model = CpModel()
        val maxPresses = targetInts.sum().toLong()
        val vars =
            Array(m) { i ->
                model.newIntVar(0, maxPresses, "x_$i")
            }

        for (j in 0 until n) {
            val rowVars = ArrayList<IntVar>()
            for (i in 0 until m) {
                var touches = false
                val cols = buttons[i]
                for (idx in cols) {
                    if (idx == j) {
                        touches = true
                        break
                    }
                }
                if (touches) {
                    rowVars.add(vars[i])
                }
            }
            if (rowVars.isEmpty()) {
                if (targetInts[j] != 0) {
                    throw IllegalStateException()
                }
            } else {
                model.addEquality(LinearExpr.sum(rowVars.toTypedArray()), targetInts[j].toLong())
            }
        }

        model.minimize(LinearExpr.sum(vars))

        val solver = CpSolver()
        val status = solver.solve(model)
        if (status != CpSolverStatus.OPTIMAL && status != CpSolverStatus.FEASIBLE) {
            throw IllegalStateException()
        }

        val presses = vars.sumOf { solver.value(it) }
        total += presses
    }

    println(total)
}

fun d10p1() {
    val reader = File("C:\\Users\\ryan7\\Desktop\\AdventOfCode25\\src\\main\\d10.txt").bufferedReader()
    val inputLines = reader.useLines { lines -> lines.toList() }

    var total = 0L

    for (line in inputLines) {
        val pattern = line.substringBefore(']').substring(1)
        val lights = pattern.length

        var target = 0
        for ((i, c) in pattern.withIndex()) {
            if (c == '#') {
                target = target or (1 shl (lights - i - 1))
            }
        }

        val buttonRegex = "\\(([^)]*)\\)".toRegex()
        val buttonsList = ArrayList<Int>()

        for (match in buttonRegex.findAll(line)) {
            val content = match.groupValues[1]
            var mask = 0
            val parts = content.split(",")
            for (p in parts) {
                val idx = p.toInt()
                mask = mask or (1 shl (lights - idx - 1))
            }
            buttonsList.add(mask)
        }

        val buttons = buttonsList.toIntArray()
        val maxState = 1 shl lights
        val dist = IntArray(maxState) { -1 }
        val q: ArrayDeque<Int> = ArrayDeque()
        dist[0] = 0
        q.add(0)

        var best = -1
        while (q.isNotEmpty()) {
            val s = q.removeFirst()
            if (s == target) {
                best = dist[s]
                break
            }
            val nextDist = dist[s] + 1
            for (mask in buttons) {
                val ns = s xor mask
                if (dist[ns] == -1) {
                    dist[ns] = nextDist
                    q.add(ns)
                }
            }
        }

        if (best != -1) total += best.toLong()
    }

    println(total)
}
