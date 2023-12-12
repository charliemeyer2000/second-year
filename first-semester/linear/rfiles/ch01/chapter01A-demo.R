#
## R Demo: Chapter 1 (part A)
#

## Transforming matrices to reduced echelon form

# R can be used to transform a matrix to reduced echelon form.
# Thus given a linear system, we can use R to transform the 
# augmented matrix to reduced echelon form.  From there we
# back substitute by hand (possibly with assistance from R)
# to find the general solution.

# Some functions are built into R, but others are
# defined in "packages" that need to be installed 
# and then called into your R session.

# Below we install and call in the "pracma" 
# package, which has a lot of functions for
# linear algebra.  Here we focus on just one,
# but we will see more later.

# The next command downloads (only needed once) 
# the "pracma" package.  
install.packages("pracma")

# The command below loads the pracma library into 
# your current working session.  You need to run 
# this with each new R session where it is used.
library(pracma)

# Here is a linear system from a class example.
#
#    x1 -   x2 -   x3 +   x4 - 3*x5 = 4
#  2*x1 - 2*x2 - 2*x3 +   x4 - 4*x5 = 5
#    x1        +   x3 +   x4 - 2*x5 = 3
# -2*x1        - 2*x3 + 2*x4        = 2

# Below is the augmented matrix from a class example:

entries <- c(1,-1,-1,1,-3,4,   # This is a list of the entries
             2,-2,-2,1,-4,5,   # in the augmented matrix.  They
             1,0,1,1,-2,3,     # are split across rows to make
             -2,0,-2,2,0,2)    # it easier to read.

m <- matrix(entries,nrow=4,byrow=T) # Define the augmented matrix m

m  # Print out m

# The function "rref" will transform the matrix to
# the reduced row echelon form.  

rref(m)

# There is a bit of work left to find the general solution,
# but at least the reduced echolon form makes the back 
# substitution relatively easy.

# Here's another example from a system in class:
#
#    x1 + 2*x2 - 3*x3 = 11
#  3*x1 + 6*x2 - 8*x3 = 32
# -2*x1 -   x2        = -7

# The corresponding entries:
entries <- c(1,2,-3,11,
             3,6,-8,32,
             -2,-1,0,-7)

m <- matrix(entries,nrow=3,byrow=T) #Define the augmented matrix m

m  # Print out m
rref(m)  # The reduced echelon form

# Although still in augmented matrix form, we can immediately
# see that the system has one solution:
#
#     x1 = 2,  x2 = 3,  x3 = -1

## Special Case: A Square System

# In the case of a system with the same number of equations
# and variables (a "square system") that has a unique solution,
# the R function "solve" will provide the solution.

# We start with a matrix the includes *only* the coefficients
# from the left side of the system:

entries <- c(1,2,-3,
             3,6,-8,
             -2,-1,0)
a <- matrix(entries,nrow=3,byrow=T) #Define the coefficient matrix a
a

# We then define a list of the constant terms from the right side of
# the system:

b <- c(11,32,-7)
b

# Finally, we put those into "solve":
solve(a,b)  # Output given as a list, in order x1, x2, x3.

