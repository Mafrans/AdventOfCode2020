package me.mafrans.adventofcode

fun main() {

    val input = Util.readResourceLines("day11.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    val adjacentPositions = arrayOf(
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
                if (seats[x][y] == 'L' && adjacentPositions.all {
                        !(x + it.x in seats.indices && y + it.y in seats[x].indices) || seats[x + it.x][y + it.y] != '#'
                    }) {
                    changes += Triple(x, y, '#')
                }
                else if (seats[x][y] == '#' && adjacentPositions.count {
                        if (x + it.x in seats.indices && y + it.y in seats[x].indices)
                            seats[x + it.x][y + it.y] == '#'
                        else false
                    } >= 4) {
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