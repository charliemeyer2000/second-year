#
## R Demo: Chapter 1 (part B)
#

##
## Numerical Solutions
##

# Load the "pracma" library
library(pracma)

## Using Jacobi iteration

# An example of a loop (computing factorials here)

fact <- 1
for (n in 1:10) {
  fact <- n*fact
}
print(fact) # This is 10! = 10*9*8*...*2*1

# To use Jacobi iteration to solve the system
#
#    5*x1 -  2*x2 = 13
#      x1 - 10*x2 = -7
#
# Start by solving for x1, x2:
#      
#      x1 = (13 + 2*x2)/5
#      x2 = (-7 - x1)/(-10)

x1curr <- 0   # Set initial guess for x1=0
x2curr <- 0   # Set initial guess for x2=0

for (n in 1:10) {
  x1new <- (13 + 2*x2curr)/5  # Compute new value for x1
  x2new <- (-7 - x1curr)/(-10) # Compute new value for x2
  print(c(x1new, x2new))  # Print out new values
  x1curr <- x1new # Transfer new to current to compute next x1
  x2curr <- x2new # Transfer new to current to compute next x2
}

# A system of 3 equations and 3 variables.

# -2*x1        +    x3 =  5
#   -x1 + 5*x2 -    x3 =  8
#  2*x1 - 6*x2 + 10*x3 = 16

# Solving for x1, x2, x3:
#
#  x1 = (5 - x3)/(-2)
#  x2 = (8 + x1 + x3)/5
#  x3 = (16 - 2*x1 + 6*x2)/10

# Start by initializing variables
x1curr <- 0   # Set initial guess for x1=0
x2curr <- 0   # Set initial guess for x2=0
x3curr <- 0   # Set initial guess for x3=0

for (n in 1:10) {
  x1new <- (5 - x3curr)/(-2)  # Compute new value for x1
  x2new <- (8 + x1curr + x3curr)/5  # Compute new value for x2
  x3new <- (16 - 2*x1curr + 6*x2curr)/10  # Compute new value for x3
  print(c(x1new, x2new, x3new))  # Print out new values
  x1curr <- x1new # Transfer new to current to compute next x1
  x2curr <- x2new # Transfer new to current to compute next x2
  x3curr <- x3new # Transfer new to current to compute next x3
}

## Using Gauss-Seidel iteration

# The only difference between Jacobi and Gauss-Seidel
# is in when the variable values are updated.  The
# code for Gauss-Seidel is actually simpler, because
# the updating happens immediately.

# Below is code for Gauss-Seidel.

x2 <- 0   # Set initial guess for x2=0 
x3 <- 0   # Set initial guess for x3=0 
# (No need to guess for x1.)

# The first 10 iterations

for (n in 1:10) {
  x1 <- (5 - x3)/(-2)  # Compute new value for x1
  x2 <- (8 + x1 + x3)/5  # Compute new value for x2
  x3 <- (16 - 2*x1 + 6*x2)/10  # Compute new value for x3
  print(c(x1, x2, x3))  # Print out new values
}




