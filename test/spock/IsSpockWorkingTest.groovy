package spock

/**
 * Simple UnitTest to confirm Spock test framework is configured & functioning correctly.
 *
 *See https://code.google.com/p/spock/wiki/HelloSpock
 *
 * Spock blocks:
 * 
given: preconditions, data fixtures
setup: alias for given (JUnit syntax)
when: actions that trigger some outcome
then: make assertions about the outcome
expect: shorthand for when & then
where: applies varied inputs
and: subdivides other blocks
cleanup: post-conditions, housekeeping

 */

 
class IsSpockWorkingTest extends spock.lang.Specification {
	    
    def "length of Spock's and his friends' names"() {
        expect:
        name.size() == length

        where:
        name     | length
	"Spock"  | 5
	"Kirk"   | 4
	"Scotty" | 6
    }	
}