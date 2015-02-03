package solver

import domain.*;
// import groovy.transform.TailRecursive // ? Groovy 2.3+

class Solver {
    private problemName
    private columns, rows
    
    Solver(final int rows, final int columns)  {
        this.columns = columns
        this.rows = rows
    }

    Solutions solve(final String problemName, final List pieceList)  {           
        Solutions solutions = new Solutions()
        findSolutions(new Board(rows, columns), pieceList, solutions)
        println "Problem: ${problemName} permutations: ${solutions.permutations}"
        return solutions
    }
       
    // @TailRecursive
    private void findSolutions(final Board board, final List pieceList, final Solutions solutions) {
        if (pieceList.isEmpty())    {
            solutions.add(board)
            return
        }
        
        ChessPiece currentPiece = pieceList.head()
        
        
        // Adding remaining pieces & determine non-attacking boards
        Set viableBoards = []
        for (int r=0; r < rows; r++)    {
            for (int c=0; c < columns; c++) {
                if (!board.isAvailable(r, c))    {
                    continue;
                }
                
                Board clone = new Board(board)
                clone.add(r, c, currentPiece)
                if (clone.hasAttackedPieces())    {
                    continue;
                }
                
                solutions.permutations++
                viableBoards.add(clone)                
            }
        }
        
        // Recurse for each non-attacking board 
        List remainingPieceList = pieceList.tail()
        viableBoards.each{ b->
            findSolutions(b, remainingPieceList, solutions)
        }
    }
}

