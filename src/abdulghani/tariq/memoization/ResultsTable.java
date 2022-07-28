package abdulghani.tariq.memoization;

import abdulghani.tariq.recognizers.Recognizer;
import abdulghani.tariq.utils.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static abdulghani.tariq.utils.Tuple.tuple;

public class ResultsTable {

    private Map<Tuple<Integer, String>, Result> table = new HashMap<>();

    public void add(int index, Recognizer r, List<Integer> indices){
        Tuple<Integer, String> indexRecognizerPair = tuple(index, r.toString());
        table.put(indexRecognizerPair, new Result(r.toString(), indices));
    }

    public  Result get(int i, Recognizer r){
        Tuple<Integer, String> indexRecognizerPair = tuple(i, r.toString());
       return table.get(indexRecognizerPair);
    }
}
