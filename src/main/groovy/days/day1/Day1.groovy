package days.day1


String filePath = "/Users/cees/dev/repos/AoC23/src/main/resources/inputs/day1.txt"
File file = new File(filePath);


current = 0
List<Integer> snackLog = []
file.eachLine{ line ->
    if (line == "") {
        snackLog.add(current as Integer)
        current = 0;
    } else {
        current += line as Integer
    }
}

snackLog = snackLog.sort().reverse();
println snackLog.get(0);
println snackLog.get(0) + snackLog.get(1) + snackLog.get(2);