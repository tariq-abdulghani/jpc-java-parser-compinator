package abdulghani.tariq.memoization;

import abdulghani.tariq.recognizers.Recognizer;
import abdulghani.tariq.utils.Tuple;

import java.util.HashMap;
import java.util.Map;

import static abdulghani.tariq.utils.Tuple.tuple;

public class RecursionCountTable {
    private Map<Tuple<Integer, String>, Integer> table = new HashMap<>();
    private static RecursionCountTable recursionCountTable = null;

    private RecursionCountTable(){}

    public  static RecursionCountTable getInstance(){
        if(recursionCountTable == null){
            recursionCountTable =  new RecursionCountTable();
        }
        return recursionCountTable;
    }

    public void add(Tuple<Integer, String> rj, int count){
        table.put(rj, count);
    }

    public void add(int index, Recognizer r, int count){
        Tuple<Integer, String> indexRecognizerPair = tuple(index, r.toString());
        table.put(indexRecognizerPair, count);
    }

    public  int get(int i, Recognizer r){
        Tuple<Integer, String> indexRecognizerPair = tuple(i, r.toString());
        Integer count = table.get(indexRecognizerPair);
        return count == null? 0: count;
    }
}
