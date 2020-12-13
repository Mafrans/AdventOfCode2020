package me.mafrans.adventofcode

fun main() {

    val input = Util.readResourceLines("day11.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    val directions = arrayOf(
        Vector2D(1, 1),
        Vector2D(1, 0),
        Vector2D(1, -1),

        Vector2D(-1, 1),
        Vector2D(-1, 0),
        Vector2D(-1, -1),

        Vector2D(0, -1),
        Vector2D(0, 1),
    )
    val seats = input.map { it.toCharArray() }.toTypedArray()
    val changes: MutableSet<Triple<Int, Int, Char>> = mutableSetOf(Triple(0, 0, '0'))

    while (changes.size != 0) {
        changes.clear()
        for (x in seats.indices) {
            for (y in seats[x].indices) {
                if(seats[x][y] != '.') {
                    var occupiedSeats = 0
                    for (direction in directions) {
                        var dx = x + direction.x
                        var dy = y + direction.y
                        while (dx in seats.indices && dy in seats[0].indices && seats[dx][dy] == '.') {
                            dx += direction.x
                            dy += direction.y
                        }

                        if (dx in seats.indices && dy in seats[0].indices && seats[dx][dy] == '#')
                            occupiedSeats++
                    }

                    if (seats[x][y] == 'L' && occupiedSeats == 0)
                        changes += Triple(x, y, '#')
                    else if (seats[x][y] == '#' && occupiedSeats >= 5)
                        changes += Triple(x, y, 'L')
                }
            }
        }
        for (change in changes) {
            seats[change.first][change.second] = change.third
        }
    }

    println(seats.sumOf { a -> a.count { b -> b == '#' } })

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}