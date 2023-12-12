#
## R Demo: Chapter 10
#

# Load the "pracma" library
library("pracma")

## Weight Dot Products

# One example of an inner product is a weighted dot product.  For instance,
# we can define
#
#   <u, v> = 2u1*v1 + 5u2*v2 + 4u3*v3 + u4*v4
#
# This inner product has weights (2, 5, 4, 1).  We define the weighted dot 
# product as a function ("mip" = "my inner product"):

mip <- function(u,v,weight) {
  return(dot(weight*u,v))
}

# Let's try this out
w = c(2, 5, 4, 1)  # the weights
x = c(-3, 3, -1, 2)
y = c(2, 0, 7, 5)

mip(x, y, w)

## Gram-Schmidt Process

# Let's use the above weighted dot product to find an orthogonal basis
# for the subspace with basis

u1 = c(3, 0, 2, -1)
u2 = c(-2, 5, 3, 9)
u3 = c(-4, 3, 2, 7)

# Start with v1 = u1:
v1 = u1

# Next we have v2 = u2 - proj_v1(u2) = u2 - (<v1, u2>/<v1, v1>)v1
v2 = u2 - (mip(v1, u2, w)/mip(v1, v1, w))*v1

# Finally we have v3 = u3 - proj_v1(u3) - proj_v2(u3):
v3 = u3 - (mip(v1, u3, w)/mip(v1, v1, w))*v1 - (mip(v2, u3, w)/mip(v2, v2, w))*v2

# The end result:
print(v1)
print(v2)
print(v3)

# Let's check that the new vectors are orthogonal:
mip(v1, v2, w)
mip(v1, v3, w)
mip(v2, v3, w)

# We can also use rref to compare the subspace spanned by {u1,u2,u3}
# with the subspace spanned by {v1,v2,v3}
A1 <- matrix(c(u1,u2,u3), nrow=3, byrow=TRUE) # sample matrix
rref(A1)

A2 <- matrix(c(v1,v2,v3), nrow=3, byrow=TRUE) # sample matrix
rref(A2)
