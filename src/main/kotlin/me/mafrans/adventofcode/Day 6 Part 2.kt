package me.mafrans.adventofcode

fun main() {
    val input = Util.readResource("day6.txt")
    // val input = "abc\n\n\na\nb\nc\n\n\nab\nac\n\n\na\na\na\na\n\n\nb"

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()


    /*
    *   Split the input string by empty lines
    *   ```
    *   aagfvajji
    *   jdasowyqte
    *                           ← Split by this line
    *   dioewurg
    *   ```
    * */
    val groups = input.split("\r\n".repeat(2))

    var sum = 0
    for (group in groups) {
        val strippedGroup = group.replace("\n", "").replace("\r", "")
        val questions = strippedGroup.groupBy { e -> e }
        val groupSize = group.lines().size;
        sum += questions.values.filter { q -> q.size == groupSize }.size
    }

    println(sum)

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}