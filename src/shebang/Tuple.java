package shebang;

public class Tuple<T, Y> {
    private T a;
    private Y b;
    public Tuple(T a, Y b){
        this.a = a;
        this.b = b;
    }

    public T getA() {
        return a;
    }

    public Y getB() {
        return b;
    }

    public void setB(Y b) {
        this.b = b;
    }
}
