#include <stdio.h>

int main() {
    char x = 'A'; // this would make a movb instruction in assembly.
    printf("This is a character: %c\n", x);
    /**
     * had we done printf("This is a character: %d\n", x);
     * this would treat x as an integer and would print the ASCII value of 'A', which is 65.
     * Types don't exist in assembly!!
    */

    return 0;
}