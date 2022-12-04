
String filePath = "/Users/cees/dev/repos/AoC23/src/main/resources/inputs/day4.txt"
File file = new File(filePath);
List<Tuple<Tuple<Integer>>> cleanList = []

createTuple = { String[] duo ->
    String[] duoList = duo[0].split("-")
    return new Tuple(duoList[0].toInteger(), duoList[1].toInteger())
}

file.eachLine( line -> {
    String[] lineList = line.split(",")
    Tuple combinedTuple = new Tuple(createTuple(lineList[0]), createTuple(lineList[1]));
    cleanList.add(combinedTuple)
})

easyToSpotDuplicatesCount = 0
cleanList.forEach(duo -> {
    Tuple firstPatch = duo.get(0)
    Tuple secondPatch = duo.get(1)
    if (firstPatch.get(0) <= secondPatch.get(0) && firstPatch.get(1) >= secondPatch.get(1)) easyToSpotDuplicatesCount++
    else if (secondPatch.get(0) <= firstPatch.get(0) && secondPatch.get(1) >= firstPatch.get(1)) easyToSpotDuplicatesCount++
})

println easyToSpotDuplicatesCount

hardToSpotOverlapCount = 0
cleanList.forEach( duo -> {
    Tuple firstPatch = duo.get(0)
    Tuple secondPatch = duo.get(1)
    if (!(firstPatch.get(1) < secondPatch.get(0) || (secondPatch.get(1) < firstPatch.get(0)))) hardToSpotOverlapCount++
})
println hardToSpotOverlapCount
