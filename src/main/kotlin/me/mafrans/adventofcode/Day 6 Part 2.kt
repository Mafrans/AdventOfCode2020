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

    // The sum of all question answers
    var sum = 0

    // Iterate over each group
    for (group in groups) {
        /*
        *   Remove any line break characters before grouping answers together
        *   This is done to avoid getting blank groups filled with line breaks
        * */
        val strippedGroup = group.replace("\n", "").replace("\r", "")

        // Group together every answer by how many times it appears
        val questions = strippedGroup.groupBy { e -> e }

        // How many members are included in the group
        val groupSize = group.lines().size;

        /*
        *   If the amount of answers for a question is equal to the amount of
        *   members in the group, increase the sum by it
        * */
        sum += questions.values.filter { q -> q.size == groupSize }.size
    }

    println(sum)

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}