package me.mafrans.adventofcode

fun main() {
    /*
    *   Describes an amount of copies of a single bag type
    * */
    class Bag(val name: String, val amount: Int)




    val input = Util.readResourceLines("day7.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    // All bags in the input
    val bags: HashMap<String, Array<Bag?>> = HashMap()

    // Populate `bags` with all bags and their children
    for (line in input) {
        /*
        *   A bag string is formatted as: `<name> bag|bags contain <children: [<amount> <prefix> <color> bag|bags]>`.
        *   Therefore, one can find data by splitting by " bags contain " and ", ", where:
        *   [0]: The name of the bag
        *   [>0]: A list of children, formatted as `<amount> <prefix> <color> bag|bags`
        * */
        val data = line.split(" bags contain ", ", ")

        /*
        *   Set the key <bag name> in `bags` to an array containing all the child bags of that bag
        *   The child bags are formatted as `<amount> <prefix> <color> bag|bags` and can therefore be parsed by
        *   splitting the child string and using `Bag(name = "${childData[1]} ${childData[2]}", amount = childData[0])`
        * */
        bags[data[0]] = data.subList(1, data.size).map { childString ->
            val childData = childString.split(" ")
            if (childString == "no other bags.")
                null
            else
                Bag(
                    name = "${childData[1]} ${childData[2]}",
                    amount = childData[0].toInt()
                )
        }.toTypedArray()
    }

    // The amount of bags inside the first bag, includes the first bag
    var amount = 0

    // A recursive function that takes a bag and counts how many children it has, then does the same for each child
    fun recurse(bag: Bag, multiplier: Int) {
        val children = bags[bag.name]
        for (child in children!!) {
            /*
            *   If the child is not null, run this function on it, otherwise do nothing
            *   Each time you recurse, multiply the multiplier by the bag amount, do this to keep track of how many
            *   new bags are added.
            * */
            child?.let { recurse(it, bag.amount * multiplier) }
        }
        amount += bag.amount * multiplier
    }

    recurse(Bag("shiny gold", 1), 1)

    // Since the amount includes the first bag but we only want the bags it actually contains, subtract 1
    println(amount - 1)

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}

