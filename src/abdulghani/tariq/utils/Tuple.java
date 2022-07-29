package abdulghani.tariq.utils;

import java.util.Objects;

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

        if (!Objects.equals(x, tuple.x)) return false;
        return Objects.equals(y, tuple.y);
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}
