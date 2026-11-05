(* File MicroC/ParseAndComp.fs *)

module ParseAndComp

let fromString = Parse.fromString

let fromFile = Parse.fromFile

let compileToFile = Contcomp.compileToFile

let compileToInsts = Contcomp.cProgram

let compile p = compileToFile (fromFile (p+".c")) (p+".out")
