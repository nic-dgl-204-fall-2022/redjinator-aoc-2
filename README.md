# Redjinator Advent of Code (2 of 3)

[Day 5 - Advent of Code 2015](https://adventofcode.com/2015/day/5)

## Overview of problem
For this problem we have a large list of strings in a text file and we want to know how many of these strings meet the critera for the Nice List:
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
* Iterate through the list
* Check for the 3 conditions
* Keep a count of the strings that make the nice list.

---

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
