# R Quiz 6 - Charlie Meyer (abs6bd)

library("pracma")

##############
# QUESTION 1 #
##############

trace <- function(A) {
  return(sum(diag(A)))
}

my_inner_product <- function(A, B) {
  return(trace(t(A) %*% B))
}

A <- matrix(c(2, 5,
             -3, 4), nrow = 2, byrow = T)

B <- matrix(c(7, -4,
             6, 11), nrow = 2, byrow = T)

proj_B_A = (my_inner_product(A, B) / my_inner_product(B, B)) * B
print(proj_B_A)

#################################
# CONSOLE OUTPUT FOR QUESTION 1 #
#################################

# > print(proj_B_A)
#           [,1]       [,2]
# [1,] 0.6306306 -0.3603604
# [2,] 0.5405405  0.9909910

##############
# QUESTION 2 #
##############

A1 = matrix(c(7, 12, 
              5, -8), nrow = 2, byrow = T)

A2 = matrix(c(1, 0, 
              13, 5), nrow = 2, byrow = T)

A3 = matrix(c(-6, 14, 
               2, 19), nrow = 2, byrow = T)


v1 = A1

v2 = A2 - (my_inner_product(v1, A2) / my_inner_product(v1, v1)) * v1

v3 = A3 - (my_inner_product(v1, A3) / my_inner_product(v1, v1)) * v1 - (my_inner_product(v2, A3) / my_inner_product(v2, v2)) * v2

print(v1)
print(v2)
print(v3)

#################################
# CONSOLE OUTPUT FOR QUESTION 2 #
#################################

# > print(v1)
#      [,1] [,2]
# [1,]    7   12
# [2,]    5   -8
# > print(v2)
#            [,1]      [,2]
# [1,]  0.2056738 -1.361702
# [2,] 12.4326241  5.907801
# > print(v3)
#           [,1]     [,2]
# [1,] -5.728385 15.51206
# [2,] -5.305452 14.93985


