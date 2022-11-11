fun main() {
    fun part1(input: List<String>): Int {

        // Extension functions to check for each of the criteria
        fun String.hasUnwantedStrings() = this.zipWithNext().any { it.first == 'a' && it.second == 'b' } ||
                this.zipWithNext().any { it.first == 'c' && it.second == 'd' } ||
                this.zipWithNext().any { it.first == 'p' && it.second == 'q' } ||
                this.zipWithNext().any { it.first == 'x' && it.second == 'y' }

        fun String.hasVowels()      = this.count { it in "aeiou" } >= 3
        fun String.hasLetterTwice() = this.zipWithNext().any { it.first == it.second }

        // Checks string eligibility for nice list
        fun isNice(str: String): Boolean {
            return !str.hasUnwantedStrings() && str.hasVowels() && str.hasLetterTwice()
        }

        var totalNiceList = 0

        for(string in input) {
            when(isNice(string)) {
                true -> totalNiceList++
                false -> continue
            }
        }

        return totalNiceList
    }

    // Part 1
    val input = readInput("input")
    println(part1(input))

}
