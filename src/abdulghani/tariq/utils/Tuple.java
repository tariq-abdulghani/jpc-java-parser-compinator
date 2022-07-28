package abdulghani.tariq.utils;

public class Tuple<X,Y>{

    X x;
    Y y;

    private Tuple(X x, Y y){
        this.x = x;
        this.y = y;
    }

    public static <A, B>  Tuple<A, B> tuple(A x, B y){
        return  new Tuple<>(x,y);
    }

    public X getX(){
        return x;
    }

    public Y getY(){
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple<?, ?> tuple = (Tuple<?, ?>) o;

        if (x != null ? !x.equals(tuple.x) : tuple.x != null) return false;
        return y != null ? y.equals(tuple.y) : tuple.y == null;
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }
}
