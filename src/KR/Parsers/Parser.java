package KR.Parsers;
import KR.Main;
import KR.Nodes.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private INode root;
    public double comp(double t){
        return root.compute(t);
    }
    public void parse(String eq) throws Exception {
        root = parseExpr(eq, new Position());
    }
    private INode parseExpr(String eq, Position p) throws Exception {
        INode a = parseTerm(eq, p);
        if (p.pos >= eq.length()){
            return a;
        }
        if (eq.charAt(p.pos) == '+' || eq.charAt(p.pos) == '-'){
            char oper = eq.charAt(p.pos);
            p.pos++;
            INode b = parseExpr(eq, p);
            return new OperationNode(oper, a, b);
        }
//        throw new Exception();
        return a;
    }

    private INode parseTerm(String eq, Position p) throws Exception {
        INode a = parseMult(eq, p);
        if (p.pos >= eq.length()){
            return a;
        }
        if (eq.charAt(p.pos) == '*' || eq.charAt(p.pos) == '/'){
            char oper = eq.charAt(p.pos);
            p.pos++;
            INode b = parseTerm(eq, p);
            return new OperationNode(oper, a, b);
        }
//        throw new Exception();
        return a;
    }

    private INode parseMult(String eq, Position p) throws Exception {

        INode a = null;
        if (p.pos < eq.length() && eq.charAt(p.pos) == '(')
            a = parseGr(eq, p);
        if (p.pos < eq.length() && eq.charAt(p.pos) >= 48 && eq.charAt(p.pos) <= 57) {
            a = parseNum(eq, p);
        }
        if (p.pos < eq.length() && eq.charAt(p.pos) >= 'a' && eq.charAt(p.pos) <= 'z'){
            a = parseFunction(eq, p);
        }
        return a;

    }

    private INode parseGr(String eq, Position p) throws Exception {
        p.pos++;
        INode a = parseExpr(eq, p);
//        p.pos++;
        if (p.pos < eq.length() && eq.charAt(p.pos) == ')')
            p.pos++;
        else throw new Exception("No \")\"");
        return a;

    }
    private NumNode parseNum(String eq, Position p){//возвращать double?
        String e ="";
        while (p.pos < eq.length() && eq.charAt(p.pos) >= '0' && eq.charAt(p.pos) <= '9' || eq.charAt(p.pos) == '.'){
            e += Character.toString(eq.charAt(p.pos));
                p.pos++;
        }
        return new NumNode(Double.parseDouble(e));
    }
    private INode parseFunction(String eq, Position p) throws Exception {
        String e ="";
        while (p.pos < eq.length() && eq.charAt(p.pos) >= 'a' && eq.charAt(p.pos) <= 'z'){
            e += Character.toString(eq.charAt(p.pos));
            p.pos++;
        }
        if (eq.charAt(p.pos) != '(') {
            return new VarNode();
        }
        if (eq.charAt(p.pos) == 'e') {
            return new NumNode(Math.E);
        }
//        INode a = parseGr(eq, p);
        List<INode> aa = parseArgs(eq, p);
        INode a = aa.get(0);
//        System.out.println(e);
        if (e.equals("pow")) {
            //INode b = parseArgs(eq, p);
            return new DegreeNode(aa);
        }
        if (e.equals("sin")) {
            return new SinNode(a);
        }
        if (e.equals("cos")) {
            return new CosNode(a);
        }
        if (e.equals("tg")) {
            return new TgNode(a);
        }
        if (e.equals("ctg")) {
            return new CtgNode(a);
        }

        return a;
    }
    private List<INode> parseArgs(String eq, Position p) throws Exception {
//        System.out.println(eq.charAt(p.pos));
        p.pos++;
        INode a = parseExpr(eq, p);
        List<INode> aa = new ArrayList<>();
        aa.add(a);
        while (eq.charAt(p.pos) == ',') {
//            System.out.println(eq.charAt(p.pos));
            p.pos++;
            INode b = parseExpr(eq, p);

            aa.add(b);
//            System.out.println(eq.charAt(p.pos));
        }
        if (p.pos < eq.length() && eq.charAt(p.pos) == ')')
            p.pos++;
//        System.out.println(eq.charAt(p.pos));
        return aa;
    }

}
