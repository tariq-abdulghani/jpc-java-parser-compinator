package abdulghani.tariq.recognizers;

import java.util.ArrayList;
import java.util.List;

public class OrElseCombinator implements Recognizer{

    private  List<Recognizer> recognizers = new ArrayList<>();

    private  OrElseCombinator(){}

    public static OrElseCombinator of(Recognizer r){
        OrElseCombinator recognizer =  new OrElseCombinator();
        return  recognizer.or(r);
    }

    public  OrElseCombinator or(Recognizer r){
        recognizers.add(r);
        return this;
    }

    @Override
    public List<Integer> apply(String[] input, int i) {
        List<Integer> indices = new ArrayList<>();
        for (Recognizer r: recognizers
             ) {
            indices.addAll(r.apply(input, i));
        }
        return indices;
    }

    @Override
    public String toString() {
        return "Union" + recognizers ;
    }
}
