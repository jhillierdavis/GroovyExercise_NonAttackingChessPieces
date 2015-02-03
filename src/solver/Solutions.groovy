package solver

import domain.Board
import java.util.concurrent.ConcurrentHashMap

class Solutions {
    private Set set = []
    // private Set set = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
    int permutations

    int size()  {
        return this.set.size()
    }
    
    void add(final Board board)  {
        String boardAsString = board.toString()
        // if (!this.set.contains(boardAsString))   {
            this.set.add(boardAsString)
        // }
    }
}

