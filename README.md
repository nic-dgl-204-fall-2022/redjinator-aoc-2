# Redjinator Advent of Code (2 of 3)

[Day 5 - Advent of Code 2015](https://adventofcode.com/2015/day/5)

## Overview of problem
For this problem we have a large list of strings in a text file and we want to evaluate each one to find out how many of them meet the critera to be on Santa's the nice list:
    1. Must contain 3 vowels
    2. Must contain at least one letter that appears twice in a row. (ex: 'xx')
    3. Must NOT contain the strings: "ab", "cd", "pq", "xy". Even if they are part of another requirement.

The text file is 1000 rows in length made up of strings that are 16 characters in length.

Sample of `input.txt`
```
zgsnvdmlfuplrubt
vlhagaovgqjmgvwq
ffumlmqwfcsyqpss
zztdcqzqddaazdjp
eavfzjajkjesnlsb
```

### Obstacles
* Get strings from file and convert to a list.
* Functions to check for each of the 3 criteria required to make the nice list .
* Iterate through the list and evaluate each string
* Keep a count of the total strings that make the nice list.

### First Solution

#### part1() function
As per normal the part1() function will be the intended implementation of our solution which will return an Int variable representing the total number of strings that meet all 3 of our pre-determined critera for the nice list. The list of measurements in the file input.txt is obtained using readInput() found in Utils.kt and passed as an argument to part1().
```
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
    
    val input = readInput("input")
    println(part1(input))
}
```
### Obstacle #1
I used the `readInput()` function from the `Utils.kt` to get the list of strings from file and then pass it as an argument to the function `part1(input)`.

### Obstacle #2
I used String extension functions for each condition/criteria, it was much simpler to write out the required functions due to the ease of referencing the strings using `this` and `it`.

`String.hasUnwantedStrings()` Makes use of the function `this.zipWithNext()` returns a list of pairs which are referenced using `it.first` and `it.second` for comparrison with the unwanted string types. When it is called it uses the ! not operator, so if there are no unwanted strings present the function returns true. 

`String.hasVowels()` Iterates through the string, checking each character against the sample string of vowels provided. If vowels found are greater or equal to 3 it returns true.

`String.hasLetterTwice()` Similiar to `String.hasUnwantedStrings()` the difference being that `it.first` and `it.second` are  checked for equal

### Obstacles #3 & #4
`isNice(str: String): Boolean` function takes a string as argument and checks for the pre-determined criteria using the extension functions returning true if the string made the nice list and false if it does not.

I used a for loop to iterate through the input list and a when condition to increase the `totalNiceList` if a string `isNice()` and finally the part1() will return the total number on the nice list. `return totalNiceList`. 
```
var totalNiceList = 0

for(string in input) {
    when(isNice(string)) {
        true -> totalNiceList++
        false -> continue
    }
}
```

---

## Overview of problem 2
This problem is very similiar to the first in that there are 2 criteria that strings need to meet in order
to make the nice list. The previous 3 criteria are not longer applicable, and the 2 new criteria are as follows:
* contains a pair of any two letters that appear twice in the string but do not overlap. Ex: xyxy, aabcaa.
* contains at least one letter which repeats with exactly one letter in between them. Ex: xyx, abcdc.

### Obstacles
* Functions to check for the 2 new criteria required to make the nice list.

## Second solution
### part2() function

Just as in part1 we will be using the part2() function provided as our means of completing the task.

```
    fun part2(input: List<String>): Int {

        // Criteria for nice list
        val pattern = Regex("(..).*\\1")
        fun String.equalEnds() = this.windowed(3).any { it.first() == it.last() }
        fun String.checkForPairs() = this.windowed(2).count() > this.windowed(2).distinct().count()

        // Nice list check
        fun isNice(str: String) = pattern.containsMatchIn(str) && str.checkForPairs() && str.equalEnds()

        var totalNiceList   = 0

        for(string in input) {
            when(isNice(string)) {
                true        -> totalNiceList++
                false       -> continue
            }
        }

        return totalNiceList
    }
```

## Obstacle 1: Functions to check for the 2 new criteria required to make the nice list.

The first extension function was: 
```fun String.equalEnds() = this.windowed(3).any { it.first() == it.last() }```
This function satisfies the condition "It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe), or even aaa." by iterating through the string in chunks of 3 using `this.windowed(3)` and checking if the first and last character are equal with `.any { it.first() == it.last() }`. The `.any()` function returns true if our predicate (new word I learned :)) matches.

I should mention here that originally I figured I didn't need the to use the `.any()` function since using comparison operators gives us a boolean return anyway but I quickly realized the any() function gives us access to `it` which again makes my life much easier.
I tried to write this without using `.any()` and without using extension functions and I wasn't happy with it. 
```
for(i in 0..str.length - 3) {
    if(str[i] == str[i + 2]) {
        hasRepeatingLetter = true
        break
    }
}
```
It doesn't look as clean enough, at least not in comparison to using an extension function. Plus I need to pass the string too it, with extension functions my string will just check itself.

The second extension function was:
`fun String.checkForPairs() = this.windowed(2).count() > this.windowed(2).distinct().count()`
So this one caused me some trouble at first but was so simple in the end. First we count the amount of pairs a string can make, then we do the same thing but only count distinct or unique pairs so if we have 2 matching pairs the `.distinct()` will not count one of them leaving us a sign that we've detected at least a double pair. 

The rest of the solution follows the same route as Part1.


## Reflection

for reflection: I've learned some new techniques and gotten some practice using 'it'
Actually time in front of computer is hard to measure on this one as it was spread over about 10 micro sessions less than an hour each. I think that's the best estimate I can give for reflection. I referenced the Kotlin docs a lot for this one and my approach to the solutions is a direct reflection of that as these would not have been my approaches prior to this term. I'm really liking extension functions and the convenience they bring. Oh one thing I forgot to mention was my complete struggle with Regex. That one pattern `val pattern = Regex("(..).*\\1")` took a good hour probabley 2 to get right and full disclosure, that was a few days ago and I can't tell you what it's doing now and since I'm so time crunched I'm happy enough with this for the moment. I know I can reproduce if I need too. I intend to grow this skill too as it's apparent regex is basically steroids for parsing data. I get it, I'm just not highly proficient with it yet.

End






Welcome to the Advent of Code[^aoc] Kotlin project created by [redjinator][github] using the [Advent of Code Kotlin Template][template] delivered by JetBrains.

In this repository, redjinator is about to provide solutions for the puzzles using [Kotlin][kotlin] language.

If you're stuck with Kotlin-specific questions or anything related to this template, check out the following resources:

- [Kotlin docs][docs]
- [Kotlin Slack][slack]
- Template [issue tracker][issues]


[^aoc]:
    [Advent of Code][aoc] – An annual event of Christmas-oriented programming challenges started December 2015.
    Every year since then, beginning on the first day of December, a programming puzzle is published every day for twenty-five days.
    You can solve the puzzle and provide an answer using the language of your choice.

[aoc]: https://adventofcode.com
[docs]: https://kotlinlang.org/docs/home.html
[github]: https://github.com/redjinator
[issues]: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template/issues
[kotlin]: https://kotlinlang.org
[slack]: https://surveys.jetbrains.com/s3/kotlin-slack-sign-up
[template]: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template
