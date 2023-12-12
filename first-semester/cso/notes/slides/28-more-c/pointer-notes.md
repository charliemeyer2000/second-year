# Lecture 28 Notes - C

## Pointer Rules

1. If we have `type` + `*` + `variable name;`, this is a declaration. For example, `int *p`. This reserves a memory location on the stack to store an address.
1. If we have `*` and a variable name on the left side of `=` it means _go to the address stored in p and **update** the value_. 
1. If we have `*` and a variable name on the right side of `=` or no `=` means: _go to the address stored in p and **retrieve** the value_. 
1. `&` followed by a variable name means **get the address of the variable**.

