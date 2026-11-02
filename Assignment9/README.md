11.1

i)
```f sharp 

    let rec lenc xs cont =
        match xs with
        | [] -> cont 0
        | x::xr -> lenc xr (fun v -> cont(1+v));

```

ii)
the length is double as the first function in the continuation is 2*v where v is the length
the full continuation would be 2*(1+(1+(1+0)))

iii)
the relation is that they are both tail recursive and they don't increase the size of the stack

```f sharp 

    let rec leni xs acc =
        match xs with
        | [] -> acc 
        | x::xr -> leni xr (1 + acc);
    
```

11.2

```f sharp 

    let rec revc xs cont=
      match xs with
      | [] -> cont []
      | x::xr -> revc xr (fun v -> cont(v @ [x]));;
    
```
ii)
the reversed list is duplicated as the first function in the continuation is v@v where v is the reversed list 

iii)

```f sharp 

    let rec revi xs acc =
        match xs with
        | [] -> acc 
        | x::xr -> revi xr (x :: acc);

```


11.3
```f sharp

    let rec prodc xs cont=
      match xs with
      | [] -> cont 1
      | x::xr -> prodc xr (fun v -> cont(x * v));;

```

11.4
```f sharp

let rec prodi xs cont=
  match xs with
  | [] -> cont 1
  | 0::xr -> 0
  | x::xr -> prodi xr (fun v -> cont(x * v));;
```

11.8

i)
values 3 5 7 9.
```f sharp
let ex10 = Every(Write(Prim("+",CstI 1,Prim("*",CstI 2, FromTo(1, 4)))));
```
values 21 22 31 32 41 42.
```f sharp
let ex11 = Every(Write(Prim("+",Prim("*",CstI 10, FromTo(2, 4)),FromTo(1,2))));
```

ii)
```f sharp
let ex12 = Write(Prim("<",CstI 50, Prim("*",CstI 7,FromTo(1,10))));
```

iii)
in the code

iv)
in the code
