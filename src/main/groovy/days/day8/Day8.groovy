String filePath = "/Users/cees/dev/repos/AoC23/src/main/resources/inputs/day8.txt"
File file = new File(filePath);

List<List<Integer>> grid = []

file.eachLine { line ->
    List<Integer> row = line.split("");
    row = row.stream().map(element -> element.toInteger())
    grid.add(row)
}

isVisibleFromOutside = { int y, int x -> {
    boolean visibleFromUp = true

    int cursor = y
    while(cursor > 0) {
        cursor--
        if (grid[y][x] <= grid[cursor][x]) visibleFromUp = false
    }

    boolean visibleFromDown = true
    cursor = y
    while(cursor < grid.size()-1) {
        cursor++
        if (grid[y][x] <= grid[cursor][x]) visibleFromDown = false
    }

    boolean visibleFromLeft = true
    cursor = x
    while(cursor > 0) {
        cursor--
        if (grid[y][x] <= grid[y][cursor]) visibleFromLeft = false
    }

    boolean visibleFromRight = true
    cursor = x
    while(cursor < grid[0].size()-1) {
        cursor++
        if (grid[y][x] <= grid[y][cursor]) visibleFromRight = false
    }
    return visibleFromUp || visibleFromDown || visibleFromLeft || visibleFromRight
}}

int interiorCount = 0

for(int i = 1; i < grid.size()-1; i++) {
    for(int j = 1; j < grid[0].size()-1; j++) {
        if(isVisibleFromOutside(i, j)) interiorCount++
    }
}

println interiorCount + grid.size() * 4 -4

determineScenicScore = { int y, int x ->
    int scoreUp = 0

    int cursor = y
    while(cursor > 0) {
        cursor--
        if (grid[y][x] <= grid[cursor][x]){
          scoreUp++
          break
        }
        else scoreUp++
    }

    int scoreDown = 0

    cursor = y
    while(cursor < grid.size()-1) {
        cursor++
        if (grid[y][x] <= grid[cursor][x]){
            scoreDown++
            break
        }
        else scoreDown++
    }

    int scoreLeft = 0

    cursor = x
    while(cursor > 0) {
        cursor--
        if (grid[y][x] <= grid[y][cursor]){
            scoreLeft++
            break
        }
        else scoreLeft++
    }

    int scoreRight = 0
    cursor = x
    while(cursor < grid[0].size()-1) {
        cursor++
        if (grid[y][x] <= grid[y][cursor]){
            scoreRight++
            break
        }
        else scoreRight++
    }
    return scoreUp * scoreDown * scoreLeft * scoreRight
}

int max = 0

for(int i = 0; i < grid.size(); i++) {
    for(int j = 1; j < grid[0].size(); j++) {
        int currentScenicScore = determineScenicScore(i, j)
        if (currentScenicScore > max) max = currentScenicScore
    }
}

println max
