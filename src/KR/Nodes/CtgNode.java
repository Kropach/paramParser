package KR.Nodes;

public class CtgNode implements INode{
    private INode child;
    @Override
    public double compute(double t) {
        return 1/Math.tan(child.compute(t));
    }

    public CtgNode(INode a) {
        child = a;
    }
}
