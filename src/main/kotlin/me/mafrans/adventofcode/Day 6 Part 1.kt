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
    val groups = input.split("\r\n".repeat(2))

    // The sum of all question answers
    var sum = 0

    // Iterate over each group
    for (group in groups) {
        // Combine all answers and group them by the amount of times each answer appears
        val set = HashSet<Char>();
        val questionAmount = group.replace("\n", "").replace("\r", "").count { c ->
            val doesContain = set.contains(c)
            set.add(c)
            !doesContain
        }

        // Increase the sum by the amount of answer
        sum += questionAmount
    }

    println(sum)

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}