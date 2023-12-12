#include <stdio.h>

int main() {
    int x[4] = {1, 2, 3, 4};
    int *p = x;
    printf("%p\n", p); // address
    p = p + 1;
    printf("%d\n", *p); // value (2)
    printf("%p\n", p); // address + 4
    return 0;
}