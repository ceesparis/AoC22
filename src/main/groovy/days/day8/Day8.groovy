String filePath = "/Users/cees/dev/repos/AoC23/src/main/resources/inputs/day8.txt"
File file = new File(filePath);

List<List<Integer>> grid = []

file.eachLine { line ->
    List<Integer> row = line.split("");
    row.stream().map(element -> element.toInteger())
    grid.add(row)
    println row
}

Closure performMove = { boolean addition, int number ->
    return addition ? (number += 1) : (number -= 1)
}



Closure inTheWay = {boolean addition, int y, int x,  String horOrDir ->
    int placeToCheck;
    if (horOrDir == "x") placeToCheck = grid[y][performMove(addition, x)]
    else placeToCheck = grid[performMove(addition, y)][x]
    if (placeToCheck >= grid[y][x]) return true
    else return false
}

Closure isVisibleFromDirection = { int y, int x, boolean condition, boolean addition, String horOrDir ->
    int count
    if(horOrDir == "x") count = x
    else count = y
    while (condition) {
        performMove(addition, count)
        if(inTheWay(addition, grid[y][x], horOrDir)) return false
    }
}

int interiorCount = 0

for(int i = 1; i < grid.size()-2; i ++) {
    for(int j = 1; j < grid[0].size()-2; j++) {
        if(isVisibleFromOutside(i, j, )) interiorCount++
    }
}

println interiorCount + grid.size() * 4 -4


