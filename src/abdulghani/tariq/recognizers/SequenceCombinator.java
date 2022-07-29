package abdulghani.tariq.recognizers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SequenceCombinator implements Recognizer{

    private final Deque<Recognizer> recognizersQueue = new ArrayDeque<>();
    private SequenceCombinator(){
    }

    static  public SequenceCombinator startWith(Recognizer r){
        SequenceCombinator builder =  new SequenceCombinator();
        builder.recognizersQueue.add(r);
        return builder;
    }

    public SequenceCombinator then(Recognizer r){
        recognizersQueue.add(r);
        return  this;
    }

    public OrElseCombinator or(Recognizer r){
        return  OrElseCombinator.of(r);
    }

    public  List<Integer> apply(String[] input, int i){

        Recognizer firstRecognizer = recognizersQueue.poll();
        List<Integer> indices = firstRecognizer.apply(input, i);
        while (!recognizersQueue.isEmpty()){
            Recognizer recognizer = recognizersQueue.poll();
            List<Integer> recogniserIndices = new ArrayList<>();
            for (Integer index: indices
                 ) {
                recogniserIndices.addAll(recognizer.apply(input, index));
            }
            indices.clear();
            indices.addAll(recogniserIndices);
        }
        return indices;
    }

    @Override
    public String toString() {
        return "Sequence" + recognizersQueue ;
    }
}
