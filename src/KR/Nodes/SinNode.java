package KR.Nodes;

public class SinNode implements INode{
    private INode child;
    @Override
    public double compute(double t) {
        return Math.sin(child.compute(t));
    }

    public SinNode(INode a) {
        child = a;
    }
}
