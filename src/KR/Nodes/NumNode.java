package KR.Nodes;

public class NumNode implements INode {
    private double value;

    public NumNode(double value) {
        this.value = value;
    }

    @Override
    public double compute(double t) {
        return value;
    }
}
