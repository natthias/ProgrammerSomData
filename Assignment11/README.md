Hej Jon, her er din README

13.1
I) The result of running the program is 4.
II) The result type is int.
III) Both g and f are called as tail calls when called from f.
  This matches the intuition, as both f and g are the last operation the function has to perform.
IV)
  In main the call to f has been annotated "f:(int -> int) 2:int:int".
  In f the call to f has been annotated "f:(int -> int)_tail (x:int - 1:int):int".
  In f the call to g has been annotated "g:(int -> int)_tail 4:int:int".
V)
  Interpreted: Used: Elapsed 10ms, CPU 10ms
  Compiled: Used 0 cpu milli-seconds
VI)
  With optimizations: Compiled program has 53 bytecode instructions.
  Without optimizations: Compiled program has 57 bytecode instructions.
  That is a difference of 4 bytecodes or ~8%
