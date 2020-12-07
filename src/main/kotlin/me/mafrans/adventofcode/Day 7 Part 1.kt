package me.mafrans.adventofcode

fun main() {
    /*
    *   Describes a single bag, including a name and a set of children
    * */
    class Bag(val name: String) {
        val content: HashSet<String> = HashSet()
    }



    val input = Util.readResourceLines("day7.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    // Amount of bags that contain "shiny gold"
    var amount = 0

    // All bags in the input
    val all: MutableSet<Bag> = HashSet()

    // The current set of bags that is being searched for
    var current: HashSet<String> = hashSetOf("shiny gold")

    // The next set of bags to search for
    var next: HashSet<String>? = null

    // Populate `all` with all bags
    for (line in input) {
        /*
        *   A bag string is formatted as: `<name> bag|bags contain <children: [<amount> <prefix> <color> bag|bags]>`.
        *   Therefore, one can find data by splitting by " bags contain " and ", ", where:
        *   [0]: The name of the bag
        *   [>0]: A list of children, formatted as `<amount> <prefix> <color> bag|bags`
        * */
        val data = line.split(" bags contain ", ", ")
        val bag = Bag(data[0])

        // Loop over the children parsed in the previous step
        for (childString in data.subList(1, data.size)) {
            /*
            *   A child string is formatted as `<amount> <prefix> <color> bag|bags`
            *   The name can be constructed by splitting by " " and joining [1] and [2] with a space in-between
            * */
            val childData = childString.split(" ")
            bag.content += "${childData[1]} ${childData[2]}";
        }

        // Add the bag to the set containing all bags
        all.add(bag)
    }

    // While next is either null or contains bags
    while(next == null || next.size > 0) {
        // If next is null (i.e the first operation), set it to a new, empty hashset. Otherwise clear it.
        if (next == null)
            next = HashSet()
        else
            next.clear()

        // Loop over all remaining bags
        for (bag in all) {
            // Loop over each child in the bag
            for (child in bag.content) {
                /*
                *   If we're currently searching for that child, and we haven't already
                *   added a reference to it, add it to the next search batch
                * */
                if (current.contains(child) && !next.contains(bag.name)) {
                    next.add(bag.name)
                    amount++
                }
            }
        }

        // Remove any found bags from the remaining bags to search, as they aren't relevant anymore
        all.removeIf { bag -> next.contains(bag.name) }

        // Update the set of bags to search for
        current = next.clone() as HashSet<String>
    }

    println(amount)


    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}