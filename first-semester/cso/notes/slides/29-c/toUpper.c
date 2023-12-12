#include <stdio.h>

void _toUpper(char *input);

int main() {
    char input[] = "lowercase";
    _toUpper(input);
    printf("%s\n", input);
}

void _toUpper(char *input) {
    int index = 0;
    while (*(input + index) != '\0') {
        *(input + index) = *(input + index) - 32;
        index++;
    }
}