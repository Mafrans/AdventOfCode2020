package me.mafrans.adventofcode

/*
*   Day 5 Part 2, Version 2
*   ~ 30% faster than version 1
* */
fun main() {
    val input = Util.readResourceLines("day5.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    // A hashset containing all ids included in the input
    val ids = HashSet<Int>()

    // Generate all ids available in the input, and add them to the hashset
    for (line in input)  {
        // Add the id hash to the ´ids´ list
        ids += line
            .replace('F', '0')
            .replace('L', '0')
            .replace('B', '1')
            .replace('R', '1')
            .toInt(2)
    }

    // Loop over every possible row, ignoring the frontmost row and backmost row
    outer@for (row in 1..126) {
        // Loop over every possible seat
        for (seat in 1..7) {
            // Calculate the seat id
            val id = row * 8 + seat

            // If the id is not in our input but the adjacent ids are, we've found the right seat
            if (!ids.contains(id) && ids.contains(id + 1) && ids.contains(id - 1)) {
                println(id)
                break@outer
            }
        }
    }

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}