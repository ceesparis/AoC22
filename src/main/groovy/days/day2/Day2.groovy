
String filePath = "/Users/cees/dev/repos/AoC23/src/main/resources/inputs/day2.txt"
File file = new File(filePath);

rock = "rock"
paper = "paper"
scissors = "scissors"

def normMap = [A : rock, B : paper, C : scissors ]
def codeMap = [X : rock, Y: paper, Z: scissors]
def scoreMap = [rock: 1, paper: 2, scissors: 3]
def winsFrom = [rock : paper, paper : scissors, scissors : rock]
def losesFrom = [rock : scissors, paper : rock, scissors : paper]


determineScorePartOne = { String left, String right ->
    String leftHand = normMap[left]
    String rightHand = codeMap[right]
    if (leftHand == rightHand) return 3 + scoreMap[rightHand]
    if((leftHand == rock && rightHand == paper) || (leftHand == paper && rightHand == scissors) || (leftHand == scissors && rightHand == rock)) {
        return 6 + scoreMap[rightHand]
    }
    return scoreMap[rightHand]
}

score = 0

file.eachLine(line -> {
    List lineList =  line.split(" ")
    score += determineScorePartOne(lineList[0], lineList[1])
})

println score
score = 0

determineScorePartTwo = { String left, String right ->
    String leftHand = normMap[left]
    if (right == "X") return scoreMap[losesFrom[leftHand]]
    if (right == "Y") return scoreMap[leftHand] + 3
    if (right == "Z") return scoreMap[winsFrom[leftHand]] + 6
}

file.eachLine( line -> {
    List lineList =  line.split(" ")
    score += determineScorePartTwo(lineList[0], lineList[1])
})

println score





