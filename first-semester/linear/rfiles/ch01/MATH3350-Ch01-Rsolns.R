##
## Chapter 1 R Solutions
##
install.packages("pracma")

library(pracma) # Load the pracma library

##############
## Section 1.2

## 57
# Points: (1,4), (2,7), (3,14)
# Model: f(x) = a*x^2 + b*x + c

# Equations:
#   (1,4)  ->  a +  b + c =  4
#   (2,7)  -> 4a + 2b + c =  7
#   (3,14) -> 9a + 3b + c = 14

# Augmented matrix:
# byrow tells r that information is row-by-row

A = matrix(c(1,1,1,4, 4,2,1,7, 9,3,1,14), nrow=3, byrow=T) 
rref(A) # row-reduced echelon form
# [,1] [,2] [,3] [,4]
# [1,]    1    0    0    2
# [2,]    0    1    0   -3
# [3,]    0    0    1    5
# Model: f(x) = 2x^2 - 3x + 5

## 59
# Points: (20,288), (40,364), (60,360)
# Model: f(x) = a*x^2 + b*x + c

# Equations:
#   (20,288)  ->  400a + 20b + c = 288
#   (40,364)  -> 1600a + 40b + c = 364
#   (60,360) ->  3600a + 60b + c = 360

# Augmented matrix:
A = matrix(c(400,20,1,288, 1600,40,1,364, 3600,60,1,360), nrow=3, byrow=T)
rref(A)
# [,1] [,2] [,3]  [,4]
# [1,]    1    0    0  -0.1
# [2,]    0    1    0   9.8
# [3,]    0    0    1 132.0
# Model: E(x) = -0.1x^2 + 9.8x + 132

## 61
# Augmented matrix:
A = matrix(c(2,7,-3,0, -3,0,5,1, -2,6,-5,4), nrow=3, byrow=T)
rref(A)
#      [,1] [,2] [,3]       [,4]
# [1,]    1    0    0 -0.8674033
# [2,]    0    1    0  0.1104972
# [3,]    0    0    1 -0.3204420
# Soln: x1 = -0.8674033
#       x2 =  0.1104972
#       x3 = -0.3204420

## 63
# Augmented matrix:
A = matrix(c(5,-2,0,3,9, 7,1,6,2,-2, 2,0,-3,5,4), nrow=3, byrow=T)
rref(A)
#      [,1] [,2] [,3] [,4]       [,5]
# [1,]    1    0    0    1  0.7777778
# [2,]    0    1    0    1 -2.5555556
# [3,]    0    0    1   -1 -0.8148148
# Soln: x1 =  0.7777778 - t   
#       x2 = -2.5555556 - t
#       x3 = -0.8148148 + t
#       x4 = t  (t any real number)

## 65
# Augmented matrix:
A = matrix(c(9,-2,0,-4,6, 0,7,-1,-1,3, 8,12,-6,5,-8), nrow=3, byrow=T)
rref(A)
#      [,1] [,2] [,3]       [,4]      [,5]
# [1,]    1    0    0 -0.5590551 0.9133858
# [2,]    0    1    0 -0.5157480 1.1102362
# [3,]    0    0    1 -2.6102362 4.7716535
# Soln: x1 = 0.9133858 + 0.5590551t   (t any real number)
#       x2 = 1.1102362 + 0.5157480t
#       x3 = 4.7716535 + 2.6102362t
#       x4 = t  (t any real number)

## 67
# Augmented matrix:
A = matrix(c(6,5,1,0,-3,0, 3,-2,-1,8,12,0, -7,1,3,0,11,0, 13,2,0,-2,-7,0), nrow=4, byrow=T)
rref(A)
#      [,1] [,2] [,3] [,4]        [,5] [,6]
# [1,]    1    0    0    0 -0.07944732    0
# [2,]    0    1    0    0 -1.28670121    0
# [3,]    0    0    1    0  3.91018998    0
# [4,]    0    0    0    1  1.69689119    0
# Soln: x1 =  0.07944732t   
#       x2 =  1.28670121t
#       x3 = -3.91018998t
#       x4 = -1.69689119t
#       x5 = t  (t any real number)


##############
## Section 1.4

## 21
# System written to be diagonally dominant:
#   2*x1 -   x2 =  1
#     x1 - 2*x2 = -1
# Solving for variables:
#   x1 = (1 + x2)/2
#   x2 = (-1-x1)/(-2)

# Start by initializing variables for Jacobi iteration
x1old <- 0   # Set initial guess for x1=0
x2old <- 0   # Set initial guess for x2=0

for(n in 1:4) {
  x1new <- (1 + x2old)/2  # Compute new value for x1
  x2new <- (-1-x1old)/(-2)  # Compute new value for x2
  x1old <- x1new # Transfer new to old to compute next x1
  x2old <- x2new # Transfer new to old to compute next x2
}
print(c(x1new, x2new))  # Print out approximate solution
# [1] 0.9375 0.9375

## 23
# System written to be diagonally dominant:
#   5*x1 +    x2 - 2*x3 =  8
#   2*x1 - 10*x2 + 3*x3 = -1
#     x1 -  2*x2 + 5*x3 = -1
# Solving for variables:
#   x1 = (8 - x2 + 2*x3)/5
#   x2 = (-1 - 2*x1 - 3*x3)/(-10)
#   x3 = (-1 - x1 + 2*x2)/5

# Start by initializing variables for Jacobi iteration
x1old <- 0   # Set initial guess for x1=0
x2old <- 0   # Set initial guess for x2=0
x3old <- 0   # Set initial guess for x2=0

for(n in 1:4) {
  x1new <- (8 - x2old + 2*x3old)/5  # Compute new value for x1
  x2new <- (-1 - 2*x1old - 3*x3old)/(-10)  # Compute new value for x2
  x3new <- (-1 - x1old + 2*x2old)/5  # Compute new value for x3
  x1old <- x1new # Transfer new to old to compute next x1
  x2old <- x2new # Transfer new to old to compute next x2
  x3old <- x3new # Transfer new to old to compute next x3
}
print(c(x1new, x2new, x3new))  # Print out approximate solution
# [1] 1.4064  0.2604 -0.3648

## 25
# System written to be diagonally dominant:
#   2*x1 -   x2 =  1
#     x1 - 2*x2 = -1
# Solving for variables:
#   x1 = (1 + x2)/2
#   x2 = (-1-x1)/(-2)

# Start by initializing variables for Gauss-Seidel iteration
x2 <- 0   # Set initial guess for x2=0

for(n in 1:4) {
  x1 <- (1 + x2)/2  # Compute new value for x1
  x2 <- (-1-x1)/(-2)  # Compute new value for x2
}
print(c(x1, x2))  # Print out approximate solution
# [1] 0.9921875 0.9960938

## 27
# System written to be diagonally dominant:
#   5*x1 +    x2 - 2*x3 =  8
#   2*x1 - 10*x2 + 3*x3 = -1
#     x1 -  2*x2 + 5*x3 = -1
# Solving for variables:
#   x1 = (8 - x2 + 2*x3)/5
#   x2 = (-1 - 2*x1 - 3*x3)/(-10)
#   x3 = (-1 - x1 + 2*x2)/5

# Start by initializing variables for Gauss-Seidel iteration
x2 <- 0   # Set initial guess for x2=0
x3 <- 0   # Set initial guess for x2=0

for(n in 1:4) {
  x1 <- (8 - x2 + 2*x3)/5  # Compute new value for x1
  x2 <- (-1 - 2*x1 - 3*x3)/(-10)  # Compute new value for x2
  x3 <- (-1 - x1 + 2*x2)/5  # Compute new value for x3
}
print(c(x1, x2, x3))  # Print out approximate solution
# [1] 1.3972770  0.2678625 -0.3723104