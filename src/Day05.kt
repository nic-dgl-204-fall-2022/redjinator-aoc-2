fun main() {
    fun part1(input: List<String>): Int {

        // Extension functions to check for each of the criteria
        fun String.hasUnwantedStrings() = this.windowed(2).any { it.first() == 'a' && it.last() == 'b' } ||
                this.zipWithNext().any { it.first == 'c' && it.second == 'd' } ||
                this.zipWithNext().any { it.first == 'p' && it.second == 'q' } ||
                this.zipWithNext().any { it.first == 'x' && it.second == 'y' }

        fun String.hasVowels()      = this.count { it in "aeiou" } >= 3
        fun String.hasLetterTwice() = this.zipWithNext().any { it.first == it.second }


        // Checks string eligibility for nice list
        fun isNice(str: String) = !str.hasUnwantedStrings() && str.hasVowels() && str.hasLetterTwice()

        var totalNiceList = 0

        for(string in input) {
            if (isNice(string)) totalNiceList++
        }

        return input.filter { isNice(it) }.count()

//        return totalNiceList
    }





    fun part2(input: List<String>): Int {

        // Criteria for nice list
        val pattern = Regex("(..).*\\1") 
        fun String.equalEnds() = this.windowed(3).any { it.first() == it.last() }

        // Nice list check
        fun isNice(str: String) = pattern.containsMatchIn(str) && str.equalEnds()

        var totalNiceList   = 0

        for(string in input) {
            when(isNice(string)) {
                true        -> totalNiceList++
                false       -> continue
            }
        }

        return totalNiceList
    }

    val input = readInput("input")

    println(part1(input))
    println(part2(input))
}
