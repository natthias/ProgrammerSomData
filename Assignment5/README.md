6.5 
"let f x = 1 in f f end"
we got an int as the type.


"let f g = g g in f end"
this fails with a type error of circularity, as function f takes a function g, that expects another function. We will end up always returning a function that takes another function and returns a function in a never ending loop.


"let f x = let g y = y in g false end in f 42 end"
we got an bool as the type.


"let f x = let g y = if true then y else x in g false end in f 42 end"
this fails with a type error of bool and int, this function can return a bool and an int at the same time. The if statement is always true, but the type checker will understand the return type as ambiguous, as y is a bool and x is an int.


"let f x = let g y = if true then y else x in g false end in f true end"
we got an bool as the type.


• bool -> bool
"let btb x = if x then x else x in btb end"

• int -> int
"let iti x = x+1 in iti end"

• int -> int -> int
"let f x = let g y = x+y in g end in f end"

• ’a -> ’b -> ’a
"let f x = let g y = x in g end in f end"

• ’a -> ’b -> ’b
"let f x = let g y = y in g end in f end"

• (’a -> ’b) -> (’b -> ’c) -> (’a -> ’c)
"let f x = let g y = let h z = y (x z) in h end in g end in f end "

• ’a -> ’b
" let f x = f x in f end "

• ’a
" let f x = f x in f 1 end "
