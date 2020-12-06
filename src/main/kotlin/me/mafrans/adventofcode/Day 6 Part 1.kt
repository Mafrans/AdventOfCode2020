package me.mafrans.adventofcode

import kotlin.math.ceil

fun main() {
    val input = Util.readResource("day6.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()


    /*
    *   Split the input string by empty lines
    *   ```
    *   aagfvajji
    *   jdasowyqte
    *                           ← Split by this line
    *   dioewurg
    *   ```
    * */
    val lines = input.split("[\r\n]{3}".toRegex())

    var sum = 0
    for (line in lines) {
        val groups = line.replace("[\r\n]+".toRegex(), "").groupBy { e -> e }
        sum += groups.keys.size
    }

    println(sum)

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}