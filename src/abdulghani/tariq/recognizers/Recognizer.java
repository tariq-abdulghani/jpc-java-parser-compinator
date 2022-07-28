package abdulghani.tariq.recognizers;

import java.util.List;

public interface Recognizer {
    List<Integer> apply(String[] input, int i);
}
