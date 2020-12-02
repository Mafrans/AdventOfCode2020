package me.mafrans.adventofcode

fun main() {
    val input = Util.readResourceLines("day2.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    var count = 0;
    for(line in input) {
        /*
        *   Each line is formatted as `<min>-<max> <letter>: <body>`
        *   Use split() to get the relevant data
        **/
        val data = line.split(" ")

        /*
        *   Array of positions to look for
        *   data[0][0] grabs the character at index 0 of the string at index 0 of the data array
        *   data[0][2] grabs the character at index 2 of the string at index 0 of the data array
        *
        *     0 1 2
        *     ↓ ↓ ↓
        *   " 2 - 8 "
        **/
        val positions = arrayOf(data[0][0], data[0][2]);

        /*
        *   Single char to look for in `body`
        *   data[1][0] grabs the character at index 0 of the string at index 1 of the data array.
        *
        *     0 1
        *     ↓ ↓
        *   " a : "
        **/
        val letter = data[1][0];

        // Text to find occurrences in
        val body = data[2]

        /*
        *   Amount of correct positions, if exactly 1, then increase the password count by 1
        **/
        val amount = positions.count { position -> body[position.toInt() - 1] == letter }
        if(amount == 1) count++
    }

    println(count)

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}