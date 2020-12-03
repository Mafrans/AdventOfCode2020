package me.mafrans.adventofcode

fun main() {
    val input = Util.readResourceLines("day3.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    // Amount of trees
    var count = 0;

    // X-coordinate, from left to right
    var x = 0

    // Y-coordinate, from top to bottom
    var y = 0

    // Loop and add 1 to `y` until it reaches the maximum value
    while(y < input.size) {
        // If the character at position (x, y) is a tree, increase the count
        if(input[y][x] == '#')
            count++;

        // Add 3 to the x coordinate, looping from the beginning if it reaches the max length
        x = (x + 3) % input[0].length;
        y += 1;
    }

    println(count);

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}