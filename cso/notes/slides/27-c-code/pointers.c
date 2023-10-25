int x = 3;

int *p;

p = &x; // address of x, p contains the ADDRESS of x, or p POINTS TO the location in memory where x is stored. 

*p = 4; // dereference p, or get the value stored at the address p points to, and set it to 4.  

int y = *p; // gets the value stored at the address p points to (4) and stores it in y. 

int *q = &y; // q points to the address of y.

*q = *p + 1; // get the value stored at the address p points to (4), add 1 to it, and store it at the address q points to (y). 

q = p; // q now points to the address p points to (x).