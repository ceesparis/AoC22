

String filePath = "/Users/cees/dev/repos/AoC23/src/main/resources/inputs/day3.txt"
File file = new File(filePath);

// part one
total = 0
List<String> priorities = []
List alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".split("")
List numberList = []
List listLinesForPartTwo = []

for(int i = 1; i < 53; i++) {
    numberList.add(i)
}

file.eachLine( line -> {
    listLinesForPartTwo.add(line)
    Set compOne = line.substring(0, (line.length()/2).intValue()).split("")
    Set compTwo = line.substring((line.length()/2).intValue()).split("")
    compOne.retainAll(compTwo)
    priorities.add(compOne[0])
})

priorities.forEach(letter -> {
    total += alphabet.indexOf(letter)+1
})

println priorities.sort()
println total

getBadge = { Set firstBag, Set secondBag, Set thirdBag ->
    firstBag.retainAll(secondBag)
    firstBag.retainAll(thirdBag)
    return firstBag[0]
}

List badges = []
getBadge = { Set firstBag, Set secondBag, Set thirdBag ->
    firstBag.retainAll(secondBag)
    firstBag.retainAll(thirdBag)
    return firstBag[0]
}

for(int i = 0; i < listLinesForPartTwo.size(); i += 3){
    Set firstBag = listLinesForPartTwo.get(i).split("")
    Set secondBag = listLinesForPartTwo.get(i+1).split("")
    Set thirdBag = listLinesForPartTwo.get(i+2).split("")
    badges.add(getBadge(firstBag, secondBag, thirdBag))
}

total = 0
badges.forEach( letter -> {
    total += alphabet.indexOf(letter) + 1
})

println total



