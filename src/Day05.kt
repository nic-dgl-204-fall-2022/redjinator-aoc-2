import java.io.File

fun main() {
    fun part1(input: List<String>): Int {

        // Obstacle #2: Functions to check for each of the 3 criteria
        fun String.hasUnwantedStrings() = this.zipWithNext().any { it.first == 'a' && it.second == 'b' } ||
                this.zipWithNext().any { it.first == 'c' && it.second == 'd' } ||
                this.zipWithNext().any { it.first == 'p' && it.second == 'q' } ||
                this.zipWithNext().any { it.first == 'x' && it.second == 'y' }

        fun String.hasVowels()      = this.count { it in "aeiou" } >= 3
        fun String.hasLetterTwice() = this.zipWithNext().any { it.first == it.second }

        // Checks if the string is nice
        fun isNice(str: String): Boolean {
            return !str.hasUnwantedStrings()
        }


        var totalNiceList = 0


        // Obstacle #3: Iterate through the list checking each string
        for(string in input) {

            // Obstacle #4: Keep a total of strings that make the nice list
            if(isNice(string)) {
                totalNiceList++
            }
        }

        return totalNiceList

    }



    // Obstacle #1: Read input from file
    val input = readInput("input")

    println(part1(input))

}
