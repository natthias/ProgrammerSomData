module Intro2

(* Association lists map object language variables to their values *)

let env = [("a", 3); ("c", 78); ("baf", 666); ("b", 111)];;

let emptyenv = []; (* the empty environment *)

let rec lookup env x =
    match env with 
    | []        -> failwith (x + " not found")
    | (y, v)::r -> if x=y then v else lookup r x;;

let cvalue = lookup env "c";;


(* Object language expressions with variables *)

type expr = 
  | CstI of int
  | Var of string
  | Prim of string * expr * expr
  | If of expr * expr * expr;;

let e1 = CstI 17;;

let e2 = Prim("+", CstI 3, Var "a");;

let e3 = Prim("+", Prim("*", Var "b", CstI 9), Var "a");;

let e4 = Prim("max", CstI 2, CstI 10);;

let e5 = Prim("min", Prim("*",CstI 2, CstI 10), CstI 10);;

let e6 = Prim("==", CstI 2, CstI 2);;

let e7 = If(Var "a", CstI 11, CstI 22)


(* Evaluation within an environment *)

let rec eval e (env : (string * int) list) : int =
    match e with
    | CstI i            -> i
    | Var x             -> lookup env x 
    | If(e1,e2,e3)      -> if eval e1 env <> 0 then eval e2 env else eval e3 env
    | Prim("+", e1, e2) -> eval e1 env + eval e2 env
    | Prim("*", e1, e2) -> eval e1 env * eval e2 env
    | Prim("-", e1, e2) -> eval e1 env - eval e2 env
    | Prim("max", e1, e2) ->
        match eval e1 env, eval e2 env with
        | x,y when x > y -> x
        | x,y -> y
    | Prim("min", e1, e2) ->
        match eval e1 env, eval e2 env with
        | x,y when x < y -> x
        | x,y -> y
    | Prim("==", e1, e2) ->
        match eval e1 env, eval e2 env with
        | x,y when x = y -> 1
        | _ -> 0
    | Prim _            -> failwith "unknown primitive";;

let e1v  = eval e1 env;;
let e2v1 = eval e2 env;;
let e2v2 = eval e2 [("a", 314)];;
let e3v  = eval e3 env;;
let e4v = eval e4 env;;
let e5v  = eval e5 env;;
let e6v = eval e6 env;;
let e7c = eval e7 env;;

let rec eval2 e (env : (string * int) list) : int =
    match e with
    | CstI i            -> i
    | Var x             -> lookup env x
    | Prim(ope, e1, e2) -> 
        let i1 = eval e1 env
        let i2 = eval e2 env
        match ope with
        | "+" -> i1 + i2
        | "-" -> i1 - i2
        | "*" -> i1 * i2
        | "max" -> if i1 > i2 then i1 else i2
        | "min" -> if i1 < i2 then i2 else i1
        | "==" -> if i1 = i2 then 1 else 0
        | _ -> failwith "unknown primitive";;


type aexpr =
    | CstI of int
    | Var of string
    | Add of aexpr * aexpr
    | Sub of aexpr * aexpr
    | Mul of aexpr * aexpr;;

let e21 = Sub(Var("v"),Add(Var("w"),Var("z")))
let e22 = Mul(CstI(2),Sub(Var("v"),Add(Var("w"),Var("z"))))
let e23 = Add(Var("x"),Add(Var("y"),Add(Var("z"),Var("v"))))


let rec fmt (a : aexpr) : string =
    match a with
    | CstI i        -> string i
    | Var v         -> v
    | Add(e1,e2)    -> "(" + fmt e1 + "+" + fmt e2 + ")"
    | Sub(e1,e2)    -> "(" + fmt e1 + "-" + fmt e2 + ")"
    | Mul(e1,e2)    -> "(" + fmt e1 + "*" + fmt e2 + ")"
    
let f1 = fmt e21
let f2 = fmt e22
let f3 = fmt e23

let rec simplify (a : aexpr ) : aexpr =
    match a with
    | Add (CstI 0,a2) -> simplify a2
    | Add (a1,CstI 0) -> simplify a1
    | Sub (a1,CstI 0) -> simplify a1
    | Mul (CstI 1,a2) -> simplify a2
    | Mul (a1,CstI 1) -> simplify a1
    | Mul (CstI 0,a2) -> CstI 0
    | Mul (a1,CstI 0) -> CstI 0
    | Sub (a1,a2) when a1 = a2 -> CstI 0
    | Mul (a1,a2) -> simplify (Mul(simplify a1, simplify a2))
    | Add (a1,a2) -> simplify (Add(simplify a1, simplify a2))
    | Sub (a1,a2) -> simplify (Sub(simplify a1, simplify a2))
    | _ -> a

let e31 = Mul(Add(CstI 1, CstI 0),Add(Var("x"),CstI 0))
let s1 = simplify e31


let rec symDiff (a : aexpr) (x : string) : aexpr =
    match a with
    | CstI i -> CstI 0
    | Var v when v = x -> CstI 1
    | Var _ -> CstI 0
    | Add(e1,e2) -> Add(symDiff e1 x,symDiff e2 x)
    | Sub(e1,e2) -> Sub(symDiff e1 x,symDiff e2 x)
    | Mul(e1,e2) -> Add(Mul((symDiff e1 x), e2 ),Mul(e1,(symDiff e2 x)))