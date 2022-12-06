String filePath = "/Users/cees/dev/repos/AoC23/src/main/resources/inputs/day6.txt"
File file = new File(filePath);

String text = file.text

determineFirstUniqueFragment = { int size ->

    for (int i = 0; i < text.length()-(size+1); i++) {
        String currentFragment = text.substring(i, i+size)
        Set<String> uniqueLettersFragment = new HashSet<String>(Arrays.asList(currentFragment.split("")))
        if(uniqueLettersFragment.size() == size){
            println currentFragment
            println i+size
            break
        }
    }
}

// part one
determineFirstUniqueFragment(4)
// part two
determineFirstUniqueFragment(14)

