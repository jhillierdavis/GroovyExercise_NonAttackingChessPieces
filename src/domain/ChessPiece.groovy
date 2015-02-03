package domain

/**
 * Basic enumeration for a sub-set of standard Chess pieces.
 */

enum ChessPiece {
    // Gotcha: Use 'as char' to ensure char (since single & double quotes in Groovy provide Strings)
    KING('K' as char), 
    QUEEN('Q' as char, true, true), 
    ROOK('R' as char, true, false), 
    BISHOP('B' as char, false, true), 
    KNIGHT('N' as char)
    
    // State
    private final char label
    private final boolean isStraightLineAttacker, isDiagonalLineAttacker

    ChessPiece(final char label)    {
        this(label, false, false)
    }

    ChessPiece(final char label, final boolean isStraightLineAttacker, final boolean isDiagonalLineAttacker)    {
        this.label = label
        this.isStraightLineAttacker = isStraightLineAttacker
        this.isDiagonalLineAttacker = isDiagonalLineAttacker
    }        
}