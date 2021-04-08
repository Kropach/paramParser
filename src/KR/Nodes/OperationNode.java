package KR.Nodes;

public class OperationNode implements INode{
    INode left, right;
    char oper;

    public OperationNode(char oper, INode left, INode right ) {
        this.oper = oper;
        this.left = left;
        this.right = right;
    }

    @Override
    public double compute(double t) {
        double a = left.compute(t);
        double b = right.compute(t);
        if (oper == '+')
            return a+b;
        if (oper == '-')
            return a-b;
        if (oper == '*')
            return a*b;
        if (oper == '/')
            return a/b;
        return 0;
    }
}
