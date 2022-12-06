String filePath = "/Users/cees/dev/repos/AoC23/src/main/resources/inputs/day5.txt"
File file = new File(filePath);


fitCratesOnRightStacks = { List<List<String>> horizontalGrid ->
    List<List<String>> grid = []
    int i = horizontalGrid.size()-1
    while (i >= 0) {
        for(int j = 0; j < horizontalGrid[i].size(); j++){
            if(horizontalGrid[i][j].matches("[A-Z]")) {
                List newStack = []
                int k = i
                while (k>=0 && horizontalGrid[k][j] != null && horizontalGrid[k][j].matches("[A-Z]")){
                    newStack.push(horizontalGrid[k][j])
                    k--
                }
                grid.add(newStack)
                if (grid.size() == 9) return grid
            }
        }
        i--
    }
    return grid

}

List<List<String>> horGrid = []
List instructions = []

file.withReader { reader ->
    while ((line = reader.readLine()) != " 1   2   3   4   5   6   7   8   9") {
        List<String>lineList = line.split("")
        horGrid.add(lineList)
    }

    reader.eachLine {line ->
        if(line != ""){
            List lineList = line.split(" ")
            instructions.add([lineList[1] as Integer, lineList[3] as Integer, lineList[5] as Integer])
        }
    }
}

List<List<String>> grid = fitCratesOnRightStacks(horGrid)

executeInstructionPartOne = { int q, int from, int to ->
    while(q > 0){
        String crateToBeMoved = grid[from-1].pop()
        grid[to-1].push(crateToBeMoved)
        q--
    }
}

executeInstructionPartTwo = { int q, int from, int to ->
    if (q == 1) {
        executeInstructionPartOne(q, from, to)
    }
    else {
        List<String> cratesToBeMoved = []
        while(q > 0){
            cratesToBeMoved.add(grid[from-1].pop())
            q--
        }
        cratesToBeMoved = cratesToBeMoved.reverse()
        cratesToBeMoved.forEach(crate -> {
            grid[to-1].push(crate)
        })
    }
}


instructions.forEach(instruction -> {
    executeInstructionPartTwo(instruction[0], instruction[1], instruction[2])
//    executeInstructionPartOne(instruction[0], instruction[1], instruction[2])
})

grid.forEach({ stack ->
    print stack[0]
})







