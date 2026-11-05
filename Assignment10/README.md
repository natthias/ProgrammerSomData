When generating code with the schortcut operator using the ternary if statement the code quality is worse as more GOTO statements are generated and there are more bytecode instructions


Byte code instructions with ternary statement
val it: Machine.instr list =
  [LDARGS 1; CALL (1, "L1"); STOP; Label "L1"; INCSP 1; GETBP; CSTI 1; ADD;
   CSTI 1889; STI; INCSP -1; GOTO "L3"; Label "L2"; GETBP; CSTI 1; ADD; GETBP;
   CSTI 1; ADD; LDI; CSTI 1; ADD; STI; INCSP -1; GETBP; CSTI 1; ADD; LDI;
   CSTI 4; MOD; IFNZRO "L6"; CSTI 0; GOTO "L4"; Label "L6"; GETBP; CSTI 1; ADD;
   LDI; CSTI 100; MOD; IFZERO "L5"; GETBP; CSTI 1; ADD; LDI; CSTI 400; MOD;
   NOT; GOTO "L4"; Label "L5"; CSTI 1; Label "L4"; IFZERO "L3"; GETBP; CSTI 1;
   ADD; LDI; PRINTI; INCSP -1; Label "L3"; GETBP; CSTI 1; ADD; LDI; GETBP; LDI;
   LT; IFNZRO "L2"; RET 1]

Byte code instructions without
val it: Machine.instr list =
  [LDARGS 1; CALL (1, "L1"); STOP; Label "L1"; INCSP 1; GETBP; CSTI 1; ADD;
   CSTI 1889; STI; INCSP -1; GOTO "L3"; Label "L2"; GETBP; CSTI 1; ADD; GETBP;
   CSTI 1; ADD; LDI; CSTI 1; ADD; STI; INCSP -1; GETBP; CSTI 1; ADD; LDI;
   CSTI 4; MOD; IFNZRO "L3"; GETBP; CSTI 1; ADD; LDI; CSTI 100; MOD;
   IFNZRO "L4"; GETBP; CSTI 1; ADD; LDI; CSTI 400; MOD; IFNZRO "L3";
   Label "L4"; GETBP; CSTI 1; ADD; LDI; PRINTI; INCSP -1; Label "L3"; GETBP;
   CSTI 1; ADD; LDI; GETBP; LDI; LT; IFNZRO "L2"; RET 1]