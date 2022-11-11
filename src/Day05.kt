import java.io.File

fun main() {
    fun part1(input: List<String>): Int {

        // Obstacle #2: Functions to check for each of the 3 criteria
        fun String.hasUnwantedStrings() = this.zipWithNext().any { it.first == 'a' && it.second == 'b' } ||
                this.zipWithNext().any { it.first == 'c' && it.second == 'd' } ||
                this.zipWithNext().any { it.first == 'p' && it.second == 'q' } ||
                this.zipWithNext().any { it.first == 'x' && it.second == 'y' }

        return input.size

    }



    // Obstacle #1: Read input from file
    val input = readInput("input")

    println(part1(input))

}
