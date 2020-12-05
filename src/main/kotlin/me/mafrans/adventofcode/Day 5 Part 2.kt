package me.mafrans.adventofcode

import kotlin.math.ceil

fun main() {
    val input = Util.readResourceLines("day5.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    val ids = HashSet<Int>()

    for (line in input)  {
        val rowData = line.substring(0, 7)
        val seatData = line.substring(7, 10)

        if (rowData == "FFFFFFF" || rowData == "BBBBBBB")
            continue

        var rowMin = 0
        var rowMax = 127
        for (char in rowData) {
            when(char) {
                'F' -> rowMax -= ceil((rowMax - rowMin) / 2.0).toInt()
                'B' -> rowMin += ceil((rowMax - rowMin) / 2.0).toInt()
            }
        }

        var seatMin = 0
        var seatMax = 7
        for (char in seatData) {
            when(char) {
                'L' -> seatMax -= ceil((seatMax - seatMin) / 2.0).toInt()
                'R' -> seatMin += ceil((seatMax - seatMin) / 2.0).toInt()
            }
        }

        ids += rowMax * 8 + seatMax
    }

    outer@for (row in 1..126) {
        for (seat in 1..7) {
            val id = row * 8 + seat
            if (!ids.contains(id) && ids.contains(id + 1) && ids.contains(id - 1)) {
                println(id)
                break@outer
            }
        }
    }

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}