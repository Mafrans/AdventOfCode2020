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

    // List of valid eye colors
    val eyeColors = arrayOf(
        "amb",
        "blu",
        "brn",
        "gry",
        "grn",
        "hzl",
        "oth"
    )

    /*
    *   Map of fields, where the field id is mapped to a function deciding if a certain value is valid for that field
    *
    *   By using function lambdas, `(args) -> {}`, we can easily use a function as a value, meaning it is easy to
    *   make a function that takes a value and returns true or false depending on if it's valid or not.
    * */
    val fields = mapOf<String, (value: String) -> Boolean>(
        // Birth year (byr) should be between 1920 and 2002
        "byr" to { value -> value.toIntOrNull() in 1920..2002 },
        // Issued year (iyr) should be between 2010 and 2020
        "iyr" to { value -> value.toIntOrNull() in 2010..2020 },
        // Ending year (eyr) should be between 2020 and 2030
        "eyr" to { value -> value.toIntOrNull() in 2020..2030 },
        // Height (hgt) should be between 150 and 193 centimeters, or between 59 and 76 inches
        "hgt" to { value ->
            when(value.substring(value.length - 2, value.length)) {
                "cm" -> value.substring(0 until value.length - 2).toIntOrNull() in 150..193
                "in" -> value.substring(0 until value.length - 2).toIntOrNull() in 59..76
                else -> false
            }
        },
        // Hair color (hcl) should be a valid hex color code
        "hcl" to { value -> value.matches("#[a-zA-Z0-9]{6}".toRegex()) },
        // Eye color (ecl) should be included in the defined list of eye colors
        "ecl" to { value -> eyeColors.contains(value) },
        // Passport id (pid) should be a number, 9 digits long (including leading zeroes)
        "pid" to { value -> value.length == 9 && value.toIntOrNull() != null },
    )

    // The amount of valid passports
    var count = 0

    // Loop over passports
    for (line in lines) {
        /*
        *   Split each passport by the valid fields, then get the first three letters
        *   filters out any empty or optional fields
        *
        *   "" is necessary, as a char can be appended to a string, but not another char
        * */
        val lineFields = line
            .split("[ \n\r]".toRegex())
            .filter { f -> f.isNotEmpty() && fields.keys.contains(f.substring(0, 3)) }
            .map { f ->
                arrayOf(f.substring(0, 3), f.substring(4, f.length))
            }

        // If all fields are presend and valid, increase the count
        if (fields.keys.all { f -> lineFields.map { a -> a[0] }.contains(f) } && lineFields.all { f -> fields[f[0]] != null && fields[f[0]]!!.invoke(f[1]) }) {
            count++
        }
    }

    println(count)

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}