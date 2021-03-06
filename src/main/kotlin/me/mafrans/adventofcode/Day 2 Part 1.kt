package me.mafrans.adventofcode

fun main() {
    val input = Util.readResourceLines("day2.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    var count = 0;
    for(line in input) {
        /*
        *   Each line is formatted as `<min>-<max> <letter>: <body>`.
        *   Use split() to get the relevant data
        **/
        val data = line.split(" ")

        // Range that the letter amount should be within, where [0] is the minimum and [1] is the maximum
        val range = data[0].split("-")

        /*
        *   Single char to look for in `body`
        *   data[1][0] grabs the character at index 0 of the string `data[1]`
        **/
        val letter = data[1][0];

        // Text to count occurrences in
        val body = data[2]

        //Amount of occurrences of `letter` in `body`
        val amount = body.toCharArray().count { l -> l == letter };

        if (amount >= range[0].toInt() && amount <= range[1].toInt())
            count++
    }

    println(count)

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}