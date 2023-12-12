# Notes for C

## Types in C

|Type| size (bytes) | 
|-|-|
char | 1
short | 2
long | 4 
int | 8


## Printing Specifiers

|Specifier| Argument|
|-|-|
%s | char * 
%p | any pointer
%d | int/short/char
%u | unsigned int/short/char
%x | unsigned int/short/char
%ld | long
%f | float
%e | double/float
%% | no argument

## Variable Declaration

`int variable;`
* has a 64 bit address and a 32 bit value. 

## Pointer Declaration

`int *pointer;`
* 64 bit address, 64 bit value. 

`int *pointer = &variable;`
* initializes pointer's value as address of variable

## Pointers

1. `int *p` -> type, *, and variable name. Pointer declaration
    * Location on the stack picked by the compiler and uninitialized memory
1. `*p = `
    * Think of this as M[P]
1. `= *p`
    * Go to the address stored in memory and get that value.    
1. `= &p`
    * get the address of the variable 


```c
int x;
x = 3;
int *p;
p = &x;
*p = 4;
int y = *p;
int *q = &y;
*q = *p + 1;
q = p;
```

## Pass by Value C 

```c
void myFunc(int *intPtr) {
    *intPtr = 3;
}

int main() {
    int x = 2;
    myFunc(&x);
    printf("%d", x);
    return 0;
}
```

```c
void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

int main() {
    int a = 2;
    int b = 3;
    swap(&a, &b);
    return 0;
}
```

## Arrays

* to declare an array, `int myArray[size];` When you declare an array, it initializes on the top of the stack `size` locations. 
* To access variables in the array, use `int variable = myArray[0];`
* Initializing values in an array: `int x[4] = {1, 2, 3, 4}`

## Pointer Arithmetic

`int *p;` -> `p = p + 3` (does 3*4, since it's an int)

## Array Syntax and Pointers

`int x[4] = {1, 2, 3, 4};`

`*x = *x + 1`


