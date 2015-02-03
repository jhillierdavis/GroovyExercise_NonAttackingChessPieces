package domain

import static domain.ChessPiece.*

/*
 * Unit tests for Board
 */

class BoardTest extends spock.lang.Specification {
    
    def "create empty board"()  {
        when:
        int rows = 3
        int columns = 2
        Board board = new Board(rows, columns)
            
        then:
        for (int r=0; r < rows; r++)   {
            for (int c=0; c < columns; c++)   {
                board.isOccupied(r, c) == false
                board.isAttacked(r, c) == false  
                board.isAvailable(r, c) == false
            }
        }
    }
    
    def "add King to board"()  {
        when:
        int rows = 3
        int columns = 3
        Board board = new Board(rows, columns)
        board.add(0, 0, KING)
            
        then:
                // Check occupied square
                board.isOccupied(0, 0) == true
                board.isAttacked(0, 0) == false  
                board.isAvailable(0, 0) == false
        
                // Check attacked square
                board.isOccupied(1, 1) == false
                board.isAttacked(1, 1) == true  
                board.isAvailable(0, 0) == false

                // Check unattacked square
                board.isOccupied(2, 2) == false
                board.isAttacked(2, 2) == false  
                board.isAvailable(2, 2) == true        
    }

    def "add King"()  {
        when:
        Board board = new Board(3, 3)
        board.add(0, 0, KING)
            
        then:
        board.toString() == 'K*.|**.|...|'
    }    
    
    def "add Queen"()  {
        when:
        Board board = new Board(4, 4)
        board.add(1, 1, QUEEN)
            
        then:
        board.toString() == '***.|*Q**|***.|.*.*|'
    }    

    def "add Bishop"()  {
        when:
        Board board = new Board(4, 4)
        board.add(1, 1, BISHOP)
            
        then:
        board.toString() == '*.*.|.B..|*.*.|...*|'
    }    

    def "add Bishop"()  {
        when:
        Board board = new Board(4, 4)
        board.add(1, 1, ROOK)
            
        then:
        board.toString() == '.*..|*R**|.*..|.*..|'
    }    

    def "add Knight"()  {
        when:
        Board board = new Board(5, 5)
        board.add(2, 2, KNIGHT)
            
        then:
        board.toString() == '.*.*.|*...*|..N..|*...*|.*.*.|'
    }    
    
    def "unattacked 4x4 board, QK"()  {
        when:
        Board board = new Board(4, 4)
        board.add(1, 1, QUEEN)
        board.add(3, 0, KING)
            
        then:
        board.toString() == '***.|*Q**|***.|K*.*|'
        board.hasAttackedPieces() == false
    }        

    def "unattacked 3x3 board, KKR"()  {
        when:
        Board board = new Board(3, 3) 
        board.add(0, 0, KING)
        board.add(0, 2, KING)
        board.add(2, 1, ROOK)        
            
        then:
        board.toString() == 'K*K|***|*R*|'
        board.hasAttackedPieces() == false
    }        
    
    
    def "attacked board, 4x4 KQ"()  {
        when:
        Board board = new Board(4, 4)
        board.add(3, 3, KING)
        board.add(1, 1, QUEEN)
            
        then:
        board.toString() == '***.|*Q**|****|.**K|'
        board.hasAttackedPieces() == true
    }        
    
    def "unattacked 3x3 board, KKR"()  {
        when:
        Board board = new Board(3, 3) 
        board.add(0, 0, KING)
        board.add(2, 2, KING)
        board.add(0, 2, ROOK)        
            
        then:
        board.toString() == 'K*R|***|.*K|'
        board.hasAttackedPieces() == true
    }        
    

    def "clone board"()  {
        when:
        Board board = new Board(4, 4)
        board.add(3, 3, KING)
        board.add(1, 1, QUEEN)
        Board clone = new Board(board)
            
        then:
        board.toString() == clone.toString()
        board.hasAttackedPieces() == clone.hasAttackedPieces()
        board == clone
    }        
    
}