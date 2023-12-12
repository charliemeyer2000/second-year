# Notes from Linear Algebra (Code/R Related)

## Lecture 3

```r
library(pracma)

# takes matrix A and returns the reduced row echelon form of A
A = matrix(c(1, 1, 1, 4,  4, 2, 1, 7,  9, 3, 1, 14), nrow=3, byrow=T)
rref(A)
```