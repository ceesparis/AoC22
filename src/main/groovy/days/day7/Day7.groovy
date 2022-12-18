
class AocDirectory {

    private String name
    private List subDirectories = []

    List getParentDirectory() {
        return parentDirectory
    }

    void setParentDirectory(List parentDirectory) {
        this.parentDirectory = parentDirectory
    }
    private List parentDirectory = []
    private int totalFileSize = 0;

    boolean getCounted() {
        return counted
    }

    void setCounted(boolean counted) {
        this.counted = counted
    }
    private boolean counted = false;

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    List getSubDirectories() {
        return subDirectories
    }

    void setSubDirectories(List subDirectories) {
        this.subDirectories = subDirectories
    }

    int getTotalFileSize() {
        return totalFileSize
    }

    void setTotalFileSize(int totalFileSize) {
        this.totalFileSize = totalFileSize
    }

}

String filePath = "/Users/cees/dev/repos/AoC23/src/main/resources/inputs/day7test2.txt"
File file = new File(filePath)

List dirRoute = []
List<AocDirectory> directories = []


static def filterInDirectories(String dirName, List<AocDirectory> directories) {
    return directories
            .stream()
            .filter(dir -> dir.getName() == dirName)
            .findFirst().orElse(new AocDirectory())
}

addDataToDirectories = { String line -> {
    String curDirName = dirRoute.get(0)
    List<String> dataList = line.split(" ")
    int fileSize = 0;
    String subDirName = ""
    if (dataList[0].isNumber()) {
        fileSize = dataList[0].toInteger()
    } else {
        subDirName = dataList[1]
    }

    AocDirectory curDir = filterInDirectories(curDirName, directories)
    if(curDir.getName() == null) {
        if(curDirName != "/"){
            curDir.setParentDirectory(List.of(dirRoute.get(1)))
        }
        curDir.setName(curDirName)
        directories.add(curDir)
    }

    if (!subDirName.isEmpty()) {
        AocDirectory subDir = filterInDirectories(subDirName, directories)
        subDir.setName(subDirName)
        List sd = curDir.getSubDirectories()
        sd.add(subDir)
        curDir.setSubDirectories(sd);
    } else {
        curDir.setTotalFileSize(curDir.getTotalFileSize() + fileSize)
    }
    return
    }
}

move = { String line -> {
    String instruction = line.split(" ")[2]
    if(instruction == ".."){
        dirRoute.pop()
    } else {
        dirRoute.push(instruction)
    }

}}

file.eachLine { line ->
    if(line.contains("\$ cd ")){
        move(line);
    }
    else if(!line.contains(" ls")) {
        addDataToDirectories(line)
    }
}

for (AocDirectory dir in directories){
    println(dir.getParentDirectory())
}

List<AocDirectory> lowestDirs = directories.stream().filter(dir -> dir.getSubDirectories().isEmpty())

for(AocDirectory lowDir in lowestDirs) {
    calculateFromBottomUp(directories, lowDir);
}


def boolean calculateFromBottomUp(List<AocDirectory> directories, AocDirectory dir) {
    if(dir.getCounted()) return true
    if (dir.getParentDirectory().isEmpty()) return true
    int lowerLevelSize = dir.getTotalFileSize()
    dir.setCounted(true)
    AocDirectory nextLevelDir = filterInDirectories(dir.getParentDirectory().get(0) as String, directories)
    for(AocDirectory subdir in nextLevelDir.getSubDirectories()){
        AocDirectory actualSubdir = filterInDirectories(subdir.getName(), directories)
        if(actualSubdir.getCounted()) continue
        lowerLevelSize += actualSubdir.getTotalFileSize()
        actualSubdir.setCounted(true)
    }
    nextLevelDir.setTotalFileSize(nextLevelDir.getTotalFileSize()+lowerLevelSize)
    return calculateFromBottomUp(directories, nextLevelDir)
}

sum = 0
for(AocDirectory dir in directories) {
    println dir.getTotalFileSize()
    int dirSize = dir.getTotalFileSize()
    if(dirSize <= 100000) sum+= dirSize
}

println sum















//
//def recursiveAdding(List<AocDirectory> directories, AocDirectory dir, List<AocDirectory> subDirs, int sum) {
//    sum += dir.getTotalFileSize()
//    if (sum > 100000) return 0
//    if (subDirs.isEmpty()) return sum
//    for (int i = 0; i < subDirs.size(); i++) {
//        AocDirectory subDir = subDirs.get(i)
//        AocDirectory actualSubdir = filterInDirectories(subDir.getName(), directories)
//        if (!subDirs.isEmpty()){
//            return recursiveAdding(directories, actualSubdir, subDirs.pop(), sum)
//        } else {
//            return recursiveAdding(directories, actualSubdir, subDir.getSubDirectories(), sum)
//        }
//
//
//    }
//}



