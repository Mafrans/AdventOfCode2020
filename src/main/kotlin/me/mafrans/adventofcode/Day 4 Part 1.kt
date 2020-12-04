package me.mafrans.adventofcode

fun main() {
    val input = Util.readResource("day4.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    /*
    *   Split the input string by empty lines
    *   ```
    *   firstEntry
    *                           ← Split by this line
    *   secondEntry
    *   ```
    * */
    val lines = input.split("[\r\n]{3}".toRegex())

    // Required fields, does not include "cid" as it's optional
    val fields = arrayOf(
        "byr", "iyr",
        "eyr", "hgt",
        "hcl", "ecl",
        "pid",
    )

    // The amount of valid passports
    var count = 0;

    // Loop over passports
    for(line in lines) {
        /*
        *   Split each passport by the valid fields, then get the first three letters
        *   filters out any empty passports
        *
        *   "" is necessary, as a char can be appended to a string, but not another char
        * */
        val lineFields = line
            .split(" ", "\n")
            .filter { f -> f.isNotEmpty() }
            .map { f ->
                f.substring(0..1)
            }

        // If all fields are valid, increase the count
        if (fields.all { f -> lineFields.contains(f) }) {
            count++
        }
    }

    println(count)

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}