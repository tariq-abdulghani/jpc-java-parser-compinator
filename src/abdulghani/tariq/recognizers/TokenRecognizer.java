package abdulghani.tariq.recognizers;

import java.util.ArrayList;
import java.util.List;

public class TokenRecognizer implements Recognizer {
    final private  String text;
    final private String name;

    public TokenRecognizer(String name, String text){
        this.name = name;
        this.text = text;
    }

    public List<Integer> apply(String[] input, int i){
        List<Integer> indices = new ArrayList<Integer>();
        if(i > input.length) {
            System.out.println("index  is out of bound " + i );
            return indices;
        }
        if(matches(input, i)) indices.add(i+1);
        return indices;
    }

    public boolean matches(String[] input, int i){
        return input[i] == text;
    }


    @Override
    public String toString() {
        return name;
    }
}
