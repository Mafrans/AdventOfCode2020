package me.mafrans.adventofcode

fun main() {
    val input = Util.readResourceLines("day3.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    // The slopes which we look for trees in
    val slopes = arrayOf(
        Vector2D(1, 1),
        Vector2D(3, 1),
        Vector2D(5, 1),
        Vector2D(7, 1),
        Vector2D(1, 2)
    )

    // The output hash, has to be a long since the output tends to go well beyond the limits of an int
    var outputHash: Long? = null;

    // Iterate over each slope
    for (slope in slopes) {
        // Amount of trees in slope
        var count = 0;

        // X-coordinate, from left to right
        var x = 0;

        // Y-coordinate, from top to bottom
        var y = 0;

        // Loop until `y` reaches the max value
        while (y < input.size) {
            // If the character at position (x, y) is a tree, increase the count
            if(input[y][x] == '#')
                count++;

            // Add `slope.x` to the x coordinate, looping from the beginning if it reaches the max length
            x = (x + slope.x) % input[0].length

            // Add `slope.y` to the y coordinate
            y += slope.y
        }

        // During the first repetition, set `outputHash` to `count`. Otherwise multiply it by `count`
        if(outputHash == null)
            outputHash = count.toLong();
        else
            outputHash *= count;
    }

    println(outputHash);

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}