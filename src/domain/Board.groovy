package domain

import static domain.ChessPiece.*
import groovy.transform.EqualsAndHashCode

/**
 * Class rempresentating an abitary size board, as specified (e.g.  8x8 squares for a Chess size board).
 * Notes that all squares are considered equal (i.e. they do not have colour as in a real Chess board). 
 */

@EqualsAndHashCode(includeFields=true)
class Board {
    // Internal constants
    private static final char EMPTY = '.', ATTACKED = '*', NEWROW = '|'
    
    // State
    private int columns, rows
    private char[][] squares // [rows][columns]    
    private boolean hasAttackedPieces = false;
    
    Board(final int rows, final int columns)  {
        this.rows = rows
        this.columns = columns
        this.squares = new char[rows][columns]
        (0..< rows).each{r->
            (0..< columns).each{c->
                this.squares[r][c] = EMPTY
            }
        }
    }
    
    // Copy constructor
    Board(final Board other)  {
        // copy state
        this(other.rows, other.columns)
        
        // Deep copy
        (0..< other.rows).each{r->
            (0..< other.columns).each{c->
                this.squares[r][c] = other.squares[r][c]
            }
        }
        this.hasAttackedPieces = other.hasAttackedPieces
    }
    
    boolean isOccupied(final int row, final int column) {
        return !(this.squares[row][column] == EMPTY || this.squares[row][column] == ATTACKED)
    }
    
    boolean isAttacked(final int row, final int column) {
        this.squares[row][column] == ATTACKED
    }
    
    boolean isAvailable(final int row, final int column)   {
        return !(this.isAttacked(row, column) || this.isOccupied(row, column));
    }
    
    boolean hasAttackedPieces()    {
        return this.hasAttackedPieces
    }
 
    boolean add(final int row, final int column, final ChessPiece piece)    {
        
        
        switch(piece)   {
        case KING:
            this.markAttackedSquaresForKing(row, column)                
            break
                
        case QUEEN:
        case ROOK:            
        case BISHOP:            
            if (piece.isStraightLineAttacker)   {
                this.markAttackedRow(column)                
                this.markAttackedColumn(row)
            }
            if (piece.isDiagonalLineAttacker)   {
                this.markAttackedDiagonals(row, column)
            }
            break            
        
        case KNIGHT:
            this.markAttackedSquaresForKnight(row, column)   
            break            
                
        default:
            throw new RuntimeException("Unknown ChessPiece: ${piece}")
        }
        if (this.isOccupied(row, column))   {
            this.hasAttackedPieces = true
        }
        this.squares[row][column] = piece.label
    }
    
    private void markAttackedSquaresForKing(final int row, final int column)  {
        int maxRowRange = row + 1
        int minRowRange = row - 1
        int maxColumnRange = column + 1
        int minColumnRange = column - 1
        
        for (int r = minRowRange; r <= maxRowRange; r++) {            
            
            if (r < 0 || r >= this.rows)  {
                continue;
            }
            for (int c = minColumnRange; c <= maxColumnRange; c++) {

                if (c < 0 || c >= this.columns)  {
                    continue;
                }
                if (r == row && c == column)    {
                    continue; // Location of piece
                }
                setAttackedIfAvailable(r, c)
            }
        }        
    }
    
    private void markAttackedSquaresForKnight(final int row, final int column)  {
        def cValues = [column-2, column+2]
        def rValues = [row-1, row+1]
        
        rValues.each{ r -> 
            cValues.each{ c->
                if (r >= 0 && r < this.rows && c >= 0 && c < this.columns)  {
                    setAttackedIfAvailable(r, c)
                }
            }
        }
        
        
        rValues = [row-2, row+2]
        cValues = [column-1, column+1]
        
        rValues.each{ r -> 
            cValues.each{ c->
                if (r >= 0 && r < this.rows && c >= 0 && c < this.columns)  {
                    setAttackedIfAvailable(r, c)
                }
            }
        }
        
    }    
    
    private void setAttackedIfAvailable(final int row, final int column)    {
        if (this.isAvailable(row, column)) {                   
            this.squares[row][column] = ATTACKED
        }     
        if (this.isOccupied(row, column))   {
            this.hasAttackedPieces = true
        }
    }
    
    private void markAttackedDiagonals(final int row, final int column)    {
        (0..< this.rows).each{r -> 
            (0..< this.columns).each{c -> 
                if (Math.abs(row - r) == Math.abs(column - c))  {
                    this.setAttackedIfAvailable(r, c);
                }
            }
        }
    }
    
    private void markAttackedRow(final int column)    {
        (0..< this.rows).each{r ->               
            this.setAttackedIfAvailable(r, column);
        }
    }

    private void markAttackedColumn(final int row)    {
        (0..< this.columns).each{c ->             
            this.setAttackedIfAvailable(row, c);
        }
    }    
    
    String toString()   {
        StringBuffer strbuf = new StringBuffer()
        for (int r=0; r < rows; r++)   {
            for (int c=0; c < columns; c++)   {
                strbuf.append(this.squares[r][c])                
            }
            strbuf.append(NEWROW)
        }
        return strbuf.toString()
    }       
}