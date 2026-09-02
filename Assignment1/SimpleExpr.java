// File Intro/SimpleExpr.java
// Java representation of expressions as in lecture 1
// sestoft@itu.dk * 2010-08-29

import java.util.Map;
import java.util.HashMap;

abstract class Expr {
    abstract public int eval(Map<String, Integer> env);

    abstract public Expr simplify(Map<String, Expr> env);

    abstract public String fmt();

    abstract public String fmt2(Map<String, Integer> env);
}

class CstI extends Expr {
    protected final int i;

    public CstI(int i) {
        this.i = i;
    }

    public int eval(Map<String, Integer> env) {
        return i;
    }

    public Expr simplify(Map<String, Expr> env) {
        return new CstI(i);
    }

    public String fmt() {
        return "" + i;
    }

    public String fmt2(Map<String, Integer> env) {
        return "" + i;
    }
}

class Var extends Expr {
    protected final String name;

    public Var(String name) {
        this.name = name;
    }

    public int eval(Map<String, Integer> env) {
        return env.get(name);
    }

    public Expr simplify(Map<String, Expr> env) {
        return new Var(name);
    }

    public String fmt() {
        return name;
    }

    public String fmt2(Map<String, Integer> env) {
        return "" + env.get(name);
    }

}

class Prim extends Expr {
    protected final String oper;
    protected final Expr e1, e2;

    public Prim(String oper, Expr e1, Expr e2) {
        this.oper = oper;
        this.e1 = e1;
        this.e2 = e2;
    }

    public int eval(Map<String, Integer> env) {
        if (oper.equals("+"))
            return e1.eval(env) + e2.eval(env);
        else if (oper.equals("*"))
            return e1.eval(env) * e2.eval(env);
        else if (oper.equals("-"))
            return e1.eval(env) - e2.eval(env);
        else if (oper.equals("max"))
            return Math.max(e1.eval(env), e2.eval(env));
        else if (oper.equals("min"))
            return Math.min(e1.eval(env), e2.eval(env));
        else if (oper.equals("=="))
            if (e1.eval(env) == e2.eval(env)) {
                return 1;
            } else {
                return 0;
            }
        else
            throw new RuntimeException("unknown primitive");
    }

    public Expr simplify(Map<String, Expr> env) {
        if (oper.equals("+")) {
            Expr s1 = e1.simplify(env);
            Expr s2 = e2.simplify(env);
            if (s1 instanceof CstI c && c.i == 0) {
                return e2.simplify(env);
            } else if (s2 instanceof CstI c && c.i == 0) {
                return e1.simplify(env);
            } else {
                return new Prim("+", e1.simplify(env), e2.simplify(env));
            }
        } else if (oper.equals("*")) {
            Expr s1 = e1.simplify(env);
            Expr s2 = e2.simplify(env);
            if (s1 instanceof CstI c1 && c1.i == 0 || s2 instanceof CstI c2 && c2.i == 0) {
                return new CstI(0);
            } else if (s1 instanceof CstI c && c.i == 1) {
                return e2.simplify(env);
            } else if (s2 instanceof CstI c && c.i == 1) {
                return e1.simplify(env);
            } else {
                return new Prim("*", e1.simplify(env), e2.simplify(env));
            }
        } else if (oper.equals("-"))

        {
            Expr s1 = e1.simplify(env);
            Expr s2 = e2.simplify(env);
            if (s1.fmt().equals(s2.fmt())) {
                return new CstI(0);
            } else if (s2 instanceof CstI c2 && c2.i == 0) {
                return e1.simplify(env);
            } else {
                return new Prim("-", e1.simplify(env), e2.simplify(env));
            }
        } else
            throw new RuntimeException("unknown primitive");
    }

    public String fmt() {
        if (oper == "max" || oper == "min") {
            return oper + "(" + e1.fmt() + "," + e2.fmt() + ")";
        } else {
            return "(" + e1.fmt() + oper + e2.fmt() + ")";
        }
    }

    public String fmt2(Map<String, Integer> env) {
        if (oper == "max" || oper == "min") {
            return oper + "(" + e1.fmt2(env) + "," + e2.fmt2(env) + ")";
        } else {
            return "(" + e1.fmt2(env) + oper + e2.fmt2(env) + ")";
        }
    }

}

public class SimpleExpr {
    public static void main(String[] args) {
        Expr e1 = new CstI(17);
        Expr e2 = new Prim("+", new CstI(3), new Var("a"));
        Expr e3 = new Prim("+", new Prim("*", new Var("b"), new CstI(9)), new CstI(10));
        Expr e4 = new Prim("min", new Prim("*", new CstI(0), new CstI(9)), new CstI(10));
        Expr e5 = new Prim("max", new Prim("*", new CstI(0), new CstI(9)), new CstI(10));
        Expr e6 = new Prim("==", new Prim("*", new CstI(1), new CstI(9)), new CstI(9));

        Expr e7 = new Prim("*", new Prim("+", new CstI(1), new CstI(0)), new Prim("+", new Var("x"), new CstI(0)));

        Map<String, Integer> env0 = new HashMap<String, Integer>();
        Map<String, Expr> env1 = new HashMap<String, Expr>();
        env0.put("a", 3);
        env0.put("c", 78);
        env0.put("baf", 666);
        env0.put("b", 111);

        System.out.println("Env: " + env0.toString());

        Expr e8 = e7.simplify(env1);

        System.out.println(e1.fmt() + " = " + e1.fmt2(env0) + " = " + e1.eval(env0));
        System.out.println(e2.fmt() + " = " + e2.fmt2(env0) + " = " + e2.eval(env0));
        System.out.println(e3.fmt() + " = " + e3.fmt2(env0) + " = " + e3.eval(env0));
        System.out.println(e4.fmt() + " = " + e4.fmt2(env0) + " = " + e4.eval(env0));
        System.out.println(e5.fmt() + " = " + e5.fmt2(env0) + " = " + e5.eval(env0));
        System.out.println(e6.fmt() + " = " + e6.fmt2(env0) + " = " + e6.eval(env0));
        System.out.println(e7.fmt() + " " + e8.fmt());
    }
}
