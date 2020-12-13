package me.mafrans.adventofcode

import kotlin.math.abs


fun main() {

    val input = Util.readResourceLines("day12.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    val rotations = arrayOf('E', 'N', 'W', 'S')
    var currentPos = Vector2D(0, 0)
    var currentDir = 'E'

    fun move(direction: Char, amount: Int) {
        when (direction) {
            'E' -> currentPos += Vector2D(amount, 0)
            'N' -> currentPos += Vector2D(0, -amount)
            'W' -> currentPos += Vector2D(-amount, 0)
            'S' -> currentPos += Vector2D(0, amount)
        }
    }

    for (instruction in input) {
        val operation = instruction[0]
        val amount = instruction.substring(1).toInt()

        when (operation) {
            'F' -> move(currentDir, amount)
            'L' -> currentDir = rotations[(4 + rotations.indexOf(currentDir) + amount/90) % 4]
            'R' -> currentDir = rotations[(4 + rotations.indexOf(currentDir) - amount/90) % 4]
            else -> move(operation, amount)
        }
    }

    println(currentPos)
    println(abs(currentPos.x) + abs(currentPos.y));

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}