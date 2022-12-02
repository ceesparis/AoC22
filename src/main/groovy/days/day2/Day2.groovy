
String filePath = "/Users/cees/dev/repos/AoC23/src/main/resources/inputs/day2.txt"
File file = new File(filePath);


List<String> circList = ["A", "B", "C"]
List<String> codeCircList = ["X", "Y", "Z"]
determineScorePartOneCircList = { String left, String right ->
    Integer leftIndex = circList.findIndexOf {it == left}
    Integer rightIndex = codeCircList.findIndexOf {it == right}
    if ((leftIndex + 1) % 3 ==  rightIndex) return 6 + rightIndex + 1
    if ((rightIndex + 1) % 3 == leftIndex) return rightIndex  + 1
    return 3 + rightIndex + 1
}

score = 0
file.eachLine( line -> {
    List lineList =  line.split(" ")
    score += determineScorePartOneCircList(lineList[0], lineList[1])
})

println score

determineScorePartTwoCircList = { String left, String right ->
    if (right == "X") return ((circList.findIndexOf {it == left} + 2) % 3) + 1
    if (right == "Y") return circList.findIndexOf {it == left} + 1 + 3
    if (right == "Z") return ((circList.findIndexOf {it == left} + 1) % 3) + 1 + 6
}

score = 0
file.eachLine( line -> {
    List lineList =  line.split(" ")
    score += determineScorePartTwoCircList(lineList[0], lineList[1])
})

println score
