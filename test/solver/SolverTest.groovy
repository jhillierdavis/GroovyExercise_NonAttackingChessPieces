package solver

import spock.lang.Ignore
import static domain.ChessPiece.*

/**
 * Unit tests for Solver
 */

/*
Problem: 3x3 board, KKR permutations: 49
Problem: 3x3 board, KKK permutations: 89
Problem: 4x4 board, Qx4 permutations: 296
Problem: 5x5 board, Qx5 permutations: 4697
Problem: 6x6 board, Qx6 permutations: 63068
Problem: 4x4 board, RRKKKK permutations: 1592
Problem: 5x6 board, QBRNKK permutations: 67502
Problem: 5x8 board, QBRNKK permutations: 1781180
Problem: 6x9 board, QBRNKK permutations: 45297894
 */


class SolverTest extends spock.lang.Specification {
    
    def "check quick (< 1 min) solutions"()    {        
        given:
        Solver solver = new Solver(rows, columns)
        String problemName = "${rows}x${columns} board, ${name}"
        Solutions solutions = solver.solve(problemName, pieceList)
        
        expect:
        solutions.size() == numberOfSolutions
        // println "DEBUG: ${problemName} numberOfSolutions=${numberOfSolutions}"
        

        where:
        rows | columns | name     | pieceList                                     | numberOfSolutions
        3    | 3       | "QQQ"    | [QUEEN, QUEEN, QUEEN]                         | 0        
	3    | 3       | "KKR"    | [KING, KING, ROOK]                            | 4
        3    | 3       | "RRR"    | [ROOK, ROOK, ROOK]                            | 6        
        3    | 3       | "KKK"    | [KING, KING, KING]                            | 8
        3    | 3       | "BBB"    | [BISHOP, BISHOP, BISHOP]                      | 26
        3    | 3       | "NNN"    | [KNIGHT, KNIGHT, KNIGHT]                      | 36
        4    | 4       | "QQQQ"   | [QUEEN, QUEEN, QUEEN, QUEEN]                  | 2 
        5    | 5       | "QQQQQ"  | [QUEEN, QUEEN, QUEEN, QUEEN, QUEEN]           | 10
        6    | 6       | "QQQQQQ" | [QUEEN, QUEEN, QUEEN, QUEEN, QUEEN, QUEEN]    | 4
        4    | 4       | "RRKKKK" | [ROOK, ROOK, KNIGHT, KNIGHT, KNIGHT, KNIGHT]  | 8
        5    | 6       | "QBRNKK" | [QUEEN, BISHOP, ROOK, KNIGHT, KING, KING]     | 16048
    }
    
          
    // Currently takes ~ 1min
    @Ignore
    def "7x7 board, Qx7"() {
        when:
        def pieceList = [QUEEN, QUEEN, QUEEN, QUEEN, QUEEN, QUEEN, QUEEN]
        Solver solver = new Solver(7, 7)
        Solutions solutions = solver.solve("7x7 board, Qx7", pieceList)
        
        then:
        // println "DEBUG: Solutions: ${solutions.set}"
        solutions.size() == 40        
    }
    
    // Currently takes ~ 2200s (~ 40mins)
    @Ignore
    def "8x8 board, Qx8"() {
        when:
        def pieceList = [QUEEN, QUEEN, QUEEN, QUEEN, QUEEN, QUEEN, QUEEN, QUEEN]
        Solver solver = new Solver(8, 8)
        Solutions solutions = solver.solve("8x8 board, Qx8", pieceList)
        
        then:
        // println "DEBUG: Solutions: ${solutions.set}"
        solutions.size() == 92        
    }
 
    // Takes ~ 1min    
    @Ignore
    def "5x8 board, QBRNKK"() {
        when:
        def pieceList = [QUEEN, BISHOP, ROOK, KNIGHT, KING, KING]
        Solver solver = new Solver(5, 8)
        Solutions solutions = solver.solve("5x8 board, QBRNKK", pieceList)
        
        then:
        solutions.size() == 677040     
    }    

    // Takes ~ 509s (~10mins)
    @Ignore
    def "6x8 board, QBRNKK"() {
        when:
        def pieceList = [QUEEN, BISHOP, ROOK, KNIGHT, KING, KING]
        Solver solver = new Solver(6, 8)
        Solutions solutions = solver.solve("6x8 board, QBRNKK", pieceList)
        
        then:
        solutions.size() == 5549700    
    }    

    // Takes ~ 2000s (~33mins)
    @Ignore
    def "6x9 board, QBRNKK"() {
        when:
        def pieceList = [QUEEN, BISHOP, ROOK, KNIGHT, KING, KING]
        Solver solver = new Solver(6, 9)
        Solutions solutions = solver.solve("6x9 board, QBRNKK", pieceList)
        
        then:
        solutions.size() == 20136752   
    }    
    
}