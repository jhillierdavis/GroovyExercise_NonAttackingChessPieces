package domain

import spock.lang.FailsWith

/**
 * Unit tests for ChessPiece
 */

class ChessPieceTest extends spock.lang.Specification {
    def "initialisation, KING"()  {
        when:
        def king = ChessPiece.KING
            
        then:
        king.label == 'K'
        king.isStraightLineAttacker == false
        king.isDiagonalLineAttacker == false
    }
    
    def "initialisation, QUEEN"()  {
        when:
        def queen = ChessPiece.QUEEN
            
        then:
        queen.label == 'Q'
        queen.isStraightLineAttacker == true
        queen.isDiagonalLineAttacker == true
    }
        
    // Show-case use of FailsWith
    @FailsWith(groovy.lang.ReadOnlyPropertyException)
    def "label read-only"()  {
        given:
        def king = ChessPiece.KING
        king.label = '?' as char            
    }
}

