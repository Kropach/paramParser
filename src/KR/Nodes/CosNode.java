package KR.Nodes;

public class CosNode implements INode {
    private INode child;
    @Override
    public double compute(double t) {
        return Math.cos(child.compute(t));
    }

    public CosNode(INode a) {
        child = a;
    }
}
