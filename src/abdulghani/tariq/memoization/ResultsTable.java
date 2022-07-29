package abdulghani.tariq.memoization;

import abdulghani.tariq.recognizers.Recognizer;
import abdulghani.tariq.utils.Tuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static abdulghani.tariq.utils.Tuple.tuple;

/**
 * singleton Table to be used within the whole scope of parsing
 */
public class ResultsTable {
    private static ResultsTable resultsTable = null;
    private ResultsTable(){ }

    /**
     * Gets instance of ResultTable
     * @return ResultsTable
     */
    public static  ResultsTable getInstance(){
        if (resultsTable == null) {
            resultsTable = new ResultsTable();
        }
        return resultsTable;
    }

    private Map<Tuple<Integer, String>, Result> table = new HashMap<>();

    /**
     * Adds index and recognizer pair to results
     * it makes sure the data store is immutable any
     * references to it has a fresh copy
     * @param i index of input
     * @param r recognizer
     * @param indices  list of indices where recognizer succeeded
     */
    public void add(int index, Recognizer r, List<Integer> indices){
        System.out.println("add:" +" "+ index + " "+r +" " + indices);
        Tuple<Integer, String> indexRecognizerPair = tuple(index, r.toString());
        table.put(indexRecognizerPair, new Result(r.toString(), new ArrayList<>(indices)));
    }

    /**
     * Gets the result in table by index and recognizer pairs
     * @param i index of input
     * @param r recognizer
     * @return Result
     */
    public  Result get(int i, Recognizer r){
        Tuple<Integer, String> indexRecognizerPair = tuple(i, r.toString());
        Result result = table.get(indexRecognizerPair);
        if (result != null){
            return  new Result(result.recognizer, new ArrayList<>(result.getIndices()));
        }else return null;
    }

}
