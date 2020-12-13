package me.mafrans.adventofcode

import java.lang.Integer.max
import java.lang.Integer.min
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/*
*   ===================================
*   WARNING!!!
*   THIS IS UNFINISHED AND DOESN'T WORK
*   ===================================
* */


fun main() {

    val input = Util.readResourceLines("day10.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    val adapters = input.map { it.toInt() }.sorted()
    // Since the last step (the last adapter to your device) isnt included in the input, add it by default
    var variants = 1L
    val max = adapters.maxOrNull();

    println(adapters)

    var i = 0
    while (i < adapters.size) {
        val base = adapters[i]
        var amountWithin = 0
        for (j in i+1..min(i + 3, adapters.size-1)) {
            val it = adapters[j]
            if (it - base <= 3) {
                amountWithin++
            }
        }

        variants *= when(amountWithin) {
            3 -> 2
            2 -> 2
            else -> 1
        }

        println("$i : ${Triple(base, amountWithin, variants)}")
        i ++
    }

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}