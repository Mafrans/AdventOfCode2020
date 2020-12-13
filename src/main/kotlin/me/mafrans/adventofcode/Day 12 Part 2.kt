package me.mafrans.adventofcode

import kotlin.math.abs


fun main() {

    val input = Util.readResourceLines("day12.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    var ship = Vector2D(0, 0)
    var waypoint = Vector2D(10, -1)

    fun moveWaypoint(direction: Char, amount: Int) {
        when (direction) {
            'E' -> waypoint += Vector2D(amount, 0)
            'N' -> waypoint += Vector2D(0, -amount)
            'W' -> waypoint += Vector2D(-amount, 0)
            'S' -> waypoint += Vector2D(0, amount)
        }
    }

    fun moveShip(amount: Int) {
        ship += waypoint * amount
    }

    fun rotateClockwise(amount: Int) {
        waypoint = when ((360 + amount) % 360) {
            90 -> waypoint.swap() * Vector2D(-1, 1)
            180 -> waypoint * -1
            270 -> waypoint.swap() * Vector2D(1, -1)
            else -> waypoint
        }
    }

    for (instruction in input) {
        val operation = instruction[0]
        val amount = instruction.substring(1).toInt()

        when (operation) {
            'F' -> moveShip(amount)
            'L' -> rotateClockwise(-amount)
            'R' -> rotateClockwise(amount)
            else -> moveWaypoint(operation, amount)
        }
    }

    println(ship)
    println(abs(ship.x) + abs(ship.y));

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}