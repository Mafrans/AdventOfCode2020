package me.mafrans.adventofcode

fun main() {

    val input = Util.readResourceLines("day10.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    // Sort the input
    val sorted = input.map { it.toInt() }.sorted()
    // Since the last step (the last adapter to your device) isnt included in the input, add it by default
    val differences = arrayOf(0, 0, 1)

    // The adapter used last iteration, 0 by default (the outlet value)
    var lastAdapter = 0

    // Loop over all adapters, in the order of lowest to highest jolts
    for (adapter in sorted) {
        // Increase the difference storage, since it's saved as an array the index has to be subtracted by 1
        differences[adapter - lastAdapter - 1]++

        // Update the last adapter
        lastAdapter = adapter
    }

    println(differences[0] * differences[2])
    println(differences.toList())

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}