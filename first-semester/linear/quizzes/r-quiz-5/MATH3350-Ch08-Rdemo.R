#
## R Demo: Chapter 8
#

# Load the "pracma" library
library("pracma")

## The dot product

x = c(1,2,4)
y = c(3,0,-2)
dot(x,y)

## The Gram-Schmidt Process

# We put basis vectors into a matrix A
entries = c(1,-1,0,1, 2,-1,1,0, -1,-1,1,3)
A = matrix(entries,nrow=4)
A  # matrix of basis vectors 

# "gramSchmidt" produces a new set of orthonormal 
# basis vectors in the matrix Q:
gramSchmidt(A)


## Diagonalizing Symmetric Matrices

entries = c(0,2,2, 2,0,2, 2,2,0)
A = matrix(entries,nrow=3)
A

D = diag(eigen(A)$values)  # define D, diagonal entries of eigenvalues
D

P = eigen(A)$vectors  # define P, columns of eigenvectors
P  # The columns are automatically orthonormal

P %*% D %*% t(P)  # check result; P orthogonal so P^-1 = t(P)


## Least Squares Regression

# Define vector of x-coords and y-coords
x <- c(11.6,7.0,8.2,17.9,9.9,14.1,4.4,10.9,12.4,17.3,
       9.3,6.7,17.0,14.9,15.1,7.5,6.8,5.0,18.3,15.1)
y <- c(27.2,24.7,26.7,47.9,34.4,39.2,24.9,38.0,35.4,45.2,
       33.4,24.6,40.1,39.5,36.6,26.6,25.0,24.6,45.4,38.1)
       
plot(x,y, cex=.4, pch=16)  # A scatter plot of (x,y) points

# "lm" computes regression line; "lm" = "linear model"
regeq <- lm(y~x)  # Determine the coefficients for reg. eq.
regeq

abline(regeq)  # Add the regression line to the scatter plot

# We can use the method from the book to find the same regression
# coefficients.  We start by creating the matrix A:

A = matrix(NA, nrow=20, ncol=2) # An empty 20-by-2 matrix
A
A[,1] = x  # 1st column are x-coordinates
A[,2] = rep(1,20)  # 2nd column are 1's
A

# Now we compute the coefficients
solve(t(A) %*% A) %*% t(A) %*% y # The coefficients
regeq  # Compare with output from lm

