1.1
i. We extended the eval function to handle "max", "min" and "==" operators, "max" and "min" returns respectively the larger or smaller number of the two given and "==" return 1 if the two given numbers are equal and 0 if they are not

ii. We wrote some sample expressions see e4 e5 e6 

iii. We wrote this as a seperate function called eval2 

iv. We did this see function eval

v. We did this see e7 that we evaluate in evc

1.2
i. We have done this see aexpr in Intro1.fs

ii. We did this see e21, e22 and e23

iii. We did this see fmt that handles constant, variables, addition, subtraction and multiplication recursively

iv. We wrote the siplify function and it simplfies (1 + 0) ∗ (x + 0) to x

v. We wrote the function called symDiff and it performs symbolic differentiation with respect to a variable

1.4






















2.1






















2.2
In 2.2, `freevars` was modified to handle receiving let bindings containing a list of variable bindings.

This required extracting all variable bindings in the right-hand side of the let bindings list.
This was done through a recursive `rhsEval` function, which unions the bindings contained in the right-hand side with the rest of the bindings.
Once this functions has finished evaluation, it will return a list of all variables declared in the right-hand side of the let bindings list.

Additionally, it was also required to extract all variables declarations contained in the let bindings list, this was done in `getVars`, which linearly extracted all variables.
With these variables extracted, we could use union and minus to compute the number of free variables.

2.3
In 2.3, `tcomp` was handle let bindings containing lists of bindings.
This was done by creating a *tail recursive*, using **continuations**, `letLoop`.
`letLoop` converts each binding in the list into a `TLet`, where the left-hand side contains the current binding, and the right-hand side contains the next binding.
Finally, once all bindings have been converted to `TLet`, the body of the let binding is evaluated.

This means that the following holds true
```
tcomp (Let([ "x1", Prim("+", CstI 5, CstI 7); "x2", Prim("*", Var "x1", CstI 2) ], Prim("+", Var "x1", Var "x2"))) [] = TLet(
    TPrim("+", TCstI 5, TCstI 7),
    TLet(TPrim("*", TVar 0, TCstI 2), TPrim("+", TVar 1, TVar 0))
```

Which shows that the list of Let bindings is converted into a set of nested TLet bindings.
