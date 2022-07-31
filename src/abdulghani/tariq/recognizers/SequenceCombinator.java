package abdulghani.tariq.recognizers;

import abdulghani.tariq.memoization.MemoizedRecognizer;

import java.util.ArrayList;
import java.util.List;

public class SequenceCombinator implements Recognizer{

    private final ArrayList<Recognizer> recognizers = new ArrayList<>();

    private String name;
    private SequenceCombinator(){
    }

    static  public SequenceCombinator startWith(Recognizer r){
        SequenceCombinator builder =  new SequenceCombinator();
        builder.recognizers.add(r);
        return builder;
    }

    static  public SequenceCombinator self(String name){
        SequenceCombinator builder =  new SequenceCombinator();
        builder.name = name;
        builder.recognizers.add(builder);
        return builder;
    }

    static  public SequenceCombinator memorisedSelf(String name){
        SequenceCombinator builder =  new SequenceCombinator();
        builder.name = name;
        builder.recognizers.add(new MemoizedRecognizer(builder));
        return builder;
    }

    public SequenceCombinator then(Recognizer r){
        recognizers.add(r);
        return  this;
    }

    public OrElseCombinator or(Recognizer r){
        return  OrElseCombinator.of(this).or(r);
    }

//    @Override
//    public  List<Integer> apply(String[] input, int i){
//
//        Recognizer firstRecognizer = recognizersQueue.poll();
//        List<Integer> indices = firstRecognizer.apply(input, i);
//        while (!recognizersQueue.isEmpty()){
//            Recognizer recognizer = recognizersQueue.poll();
//            List<Integer> recogniserIndices = new ArrayList<>();
//            for (Integer index: indices
//                 ) {
//                recogniserIndices.addAll(recognizer.apply(input, index));
//            }
//            indices.clear();
//            indices.addAll(recogniserIndices);
//        }
//        return indices;
//    }

    @Override
    public  List<Integer> apply(String[] input, int i){
        Recognizer topRecognizer = recognizers.get(0);
        List<Integer> indicesTemp = topRecognizer.apply(input, i);

        for (int j = 1; j < recognizers.size()-1 ; j++) {
            Recognizer nextRecognizer = recognizers.get(j);
            List<Integer> nextIndices = new ArrayList<>();
            for (Integer index: indicesTemp
            ) {
                nextIndices.addAll(nextRecognizer.apply(input, index));
            }
            indicesTemp.clear();
            indicesTemp.addAll(nextIndices);
        }
        return indicesTemp;

    }

    @Override
    public String toString() {
        return "Sequence " + (name==null? recognizers : name) ;
    }
}
