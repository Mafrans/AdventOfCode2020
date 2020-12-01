package me.mafrans.adventofcode

fun main() {
    val input = Util.readResourceLines("day1.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    // Sort the input, so that it becomes more predictable
    val sorted = input.map { a -> a.toInt() }.sorted()

    // Iterate through the sorted input backwards, beginning with larger numbers
    outer@ for (i in sorted.size - 1 downTo 0) {
        val a = sorted[i]
        // Iterate through the input again, but beginning with smaller numbers
        for (b in sorted) {
            /*
            *   Since the input is sorted and iterated in a way where larger numbers are paired
            *   with smaller ones, we can be certain that when a + b > 2020 the entire inner-loop
            *   can be stopped as the sum will only increase if it continues.
            **/
            if (a + b > 2020)
                break

            if (a + b == 2020) {
                println("$a + $b == 2020 : ${a * b}")
                break@outer
            }
        }
    }

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}