package KR.Nodes;

public class TgNode implements INode{
    private INode child;
    @Override
    public double compute(double t) {
        return Math.tan(child.compute(t));
    }

    public TgNode(INode a) {
        child = a;
    }
}
