#include <stdio.h>

int main() {
    char *pointer = "SomethingFun"; // stored as a string in the code section
    printf("%s\n", pointer);

    char pointerTwo[] = "SomethingFun";
    printf("%s\n", pointerTwo); // stored code section but a copy moved onto the stack so we can modify it!!
}