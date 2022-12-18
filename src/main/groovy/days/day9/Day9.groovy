String filePath = "/Users/cees/dev/repos/AoC23/src/main/resources/inputs/day9test.txt"
File file = new File(filePath);

List<Tuple> instructions = []

file.eachLine { line ->
    List<String> inputLine = line.split(" ")
    instructions.add(new Tuple(inputLine[0], inputLine[1].toInteger()))
}

List<Integer> coordinatesHead = [0, 0]
List<Integer> coordinatesTail = [0, 0]

def moveHeadOneStep(String direction, List<Integer> coordinatesHead) {
    if (direction == "U") {
        coordinatesHead[0] = coordinatesHead[0] - 1
    }
    if (direction == "D") {
        coordinatesHead[0] = coordinatesHead[0] + 1
    }
    if (direction == "L") {
        coordinatesHead[1] = coordinatesHead[1] - 1
    }
    if (direction == "R") {
        coordinatesHead[1] = coordinatesHead[1] + 1
    }
}

def moveRightDirection(List<Integer> cH, List<Integer> cT) {
    if(cH[0] > cT[0]) {
        cT[0] = cT[0] + 1
    }
    else {
        cT[0] = cT[0] - 1
    }
}

def moveTailOneStep(List<Integer> cH, List<Integer> cT) {
    // one or zero steps need to happen
    if ((Math.abs(cH[0]-cT[0]) <= 1) && (Math.abs(cH[1]-cT[1]) <= 1)) return

    if ((cH[0] != cT[0]) && (cH[1] != cT[1])) {
        if(cH[0] > cT[0]) {
            cT[0] = cT[0] + 1
        }
        else {
            cT[0] = cT[0] - 1
        }

        if(cH[1] > cT[1]) {
            cT[1] = cT[1] + 1
        }
        else {
            cT[1] = cT[1] - 1
        }
    }

    // move on y axis
    else if (Math.abs(cH[0]-cT[0]) == 2) {
        if(cH[0] > cT[0]) {
            cT[0] = cT[0] + 1
        }
        else {
            cT[0] = cT[0] - 1
        }
    }
    // move on x axis
    else if (Math.abs(cH[1]-cT[1]) == 2) {
        if(cH[1] > cT[1]) {
            cT[1] = cT[1] + 1
        }
        else {
            cT[1] = cT[1] - 1
        }
    }
}

def moveStepsInstruction(String direction, Integer steps,
                         List<Integer> coordinatesHead, List<Integer> coordinatesTail) {
    if (steps == 0) return
    moveHeadOneStep(direction, coordinatesHead)
    println "head " + coordinatesHead
    moveTailOneStep(coordinatesHead, coordinatesTail)
    println "tail" + coordinatesTail
    steps--
    moveStepsInstruction(direction, steps, coordinatesHead, coordinatesTail)
}


for(Tuple instruction in instructions) {
    moveStepsInstruction(instruction.get(0), instruction.get(1), coordinatesHead, coordinatesTail)
}