String filePath = "/Users/cees/dev/repos/AoC23/src/main/resources/inputs/day9.txt"
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

def moveRightDirection(List<Integer> cH, List<Integer> cT, int axis) {
    if(cH[axis] > cT[axis]) {
        cT[axis] = cT[axis] + 1
    }
    else {
        cT[axis] = cT[axis] - 1
    }
}

def moveTailOneStep(List<Integer> cH, List<Integer> cT) {
    // one or zero steps need to happen
    if ((Math.abs(cH[0]-cT[0]) <= 1) && (Math.abs(cH[1]-cT[1]) <= 1)) return

    if ((cH[0] != cT[0]) && (cH[1] != cT[1])) {
        moveRightDirection(cH, cT, 0)
        moveRightDirection(cH, cT, 1)
    }

    // move on y axis
    else if (Math.abs(cH[0]-cT[0]) == 2) {
        moveRightDirection(cH, cT, 0)
    }
    // move on x axis
    else if (Math.abs(cH[1]-cT[1]) == 2) {
        moveRightDirection(cH, cT, 1)
    }
}

def moveStepsInstruction(String direction, Integer steps,
                         List<Integer> coordinatesHead, List<Integer> coordinatesTail, List<Tuple> tailPlaces) {
    tailPlaces.add(new Tuple(coordinatesTail[0], coordinatesTail[1]))
    if (steps == 0) return
    moveHeadOneStep(direction, coordinatesHead)
    moveTailOneStep(coordinatesHead, coordinatesTail)
    steps--
    moveStepsInstruction(direction, steps, coordinatesHead, coordinatesTail, tailPlaces)
}


List<Tuple> tailPlaces = []
for(Tuple instruction in instructions) {
    moveStepsInstruction(instruction.get(0), instruction.get(1), coordinatesHead, coordinatesTail, tailPlaces)
}

println tailPlaces.unique().size()

List<List<Integer>> snake = []

for(int i = 0; i<10; i++) {
    snake.add([100, 100])
}

def moveStepsInstructionPartTwo(String direction, Integer steps,
                                List<List<Integer>> snake, List<Tuple> tailPlacesPartTwo) {
    if (steps == 0) return

    // move the head one step for each step
    moveHeadOneStep(direction, snake[0])
    // move the other elements of the snake, taking their precursor as relative head
    for(int i = 0; i < snake.size()-1; i++){
        moveTailOneStep(snake[i], snake[i+1])
    }
    tailPlacesPartTwo.add(new Tuple(snake[9][0], snake[9][1]))
    steps--
    moveStepsInstructionPartTwo(direction, steps, snake, tailPlacesPartTwo)
}

List<Tuple> tailPlacesPartTwo = []

for(Tuple instruction in instructions) {
    moveStepsInstructionPartTwo(instruction[0], instruction[1], snake, tailPlacesPartTwo)
}

println tailPlacesPartTwo.unique().size()


//def visualizeTail(List<Tuple> tailplacesPartTwo) {
//    for(int i = 0; i < 200; i++) {
//        for (int j = 0; j < 200; j++) {
//            Tuple currentPosition = new Tuple(i, j)
//            if(currentPosition in tailplacesPartTwo) print "#"
//            else print "."
//        }
//        println ""
//    }
//}
//
//visualizeTail(tailPlacesPartTwo)
