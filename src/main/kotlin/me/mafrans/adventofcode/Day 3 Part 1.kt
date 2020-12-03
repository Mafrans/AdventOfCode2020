package me.mafrans.adventofcode

fun main() {
    val input = Util.readResourceLines("day3.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    var count = 0;
    var y = 0;
    var x = 0;

    while (x < input.size && y < input[0].length) {
        if(input[x][y] == '#') {
            count++;
        }

        println()

        x += 3;
        y += 1;
    }

    println(count);

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}