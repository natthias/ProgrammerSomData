(* sum n + (n − 1) + · · · + 2 + 1 *)
let sum n = if n = 0 then 0 else n + sum (n - 1) in sum 1000 end

(* 3^8 *)
let threePowN n = if n = 0 then 1 else 3 * threePowN (n - 1) in threePowN 8 end

(* 3^0+3^1+...+3^11*)
let threePowN n = if n = 0 then 1 else 3 * threePowN (n - 1) in
  let mSumThreePowM m = (threePowN m) + if m = 0 then 0 else mSumThreePowM (m - 1) in
    mSumThreePowM 11
  end
end

(* 1^8+2^8+...+10^8 *)
let nPowEight n = n * n * n * n * n * n * n * n in
  let mSumMPowEight m = if m = 0 then 0 else (nPowEight m) + mSumMPowEight (m - 1) in
    mSumMPowEight 10
  end
end
