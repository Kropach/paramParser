package KR.Nodes;

import java.util.List;

public class DegreeNode implements INode {
    private List<INode> child;
    @Override
    public double compute(double t) {
        return Math.pow(child.get(0).compute(t),child.get(1).compute(1));
    }

    public DegreeNode(List<INode> aa) {
        child = aa;
    }
}
