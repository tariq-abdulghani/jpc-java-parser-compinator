package abdulghani.tariq.recognizers;

import java.util.List;

public interface Recognizer {
    // check memo table if no record for it
    // execute
    List<Integer> apply(String[] input, int i);
    // if (j, r) is not used before update table
    // return result

}
