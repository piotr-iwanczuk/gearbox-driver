package dev.iwanczuk.driver

import spock.lang.Specification
import spock.lang.Unroll

import static dev.iwanczuk.driver.Constants.*

class TrailerTest extends Specification {

    @Unroll
    def "should return the same gear when driver is in #mode mode goes FLAT with trailer and rpm (#rpm) is above #trailerRpm rpm"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .withTrailer()
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode     | rpm                     | trailerRpm
            ECO      | ECO_ABOVE_TRAILER       | ECO_TRAILER
            COMFORT  | COMFORT_ABOVE_TRAILER   | COMFORT_TRAILER
            SPORT    | SPORT_ABOVE_TRAILER     | SPORT_TRAILER
    }

    @Unroll
    def "should return the same gear when driver is in #mode mode goes FLAT with trailer and rpm (#rpm) is below #trailerRpm rpm"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .withTrailer()
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode     | rpm                     | trailerRpm
            ECO      | ECO_BELOW_TRAILER       | ECO_TRAILER
            COMFORT  | COMFORT_BELOW_TRAILER   | COMFORT_TRAILER
            SPORT    | SPORT_BELOW_TRAILER     | SPORT_TRAILER
    }

    @Unroll
    def "should return the same gear when driver is in #mode mode goes DOWNHILL with trailer and rpm (#rpm) is above #trailerRpm rpm"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .goesDownhill().withTrailer()
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode     | rpm                     | trailerRpm
            ECO      | ECO_ABOVE_TRAILER       | ECO_TRAILER
            COMFORT  | COMFORT_ABOVE_TRAILER   | COMFORT_TRAILER
            SPORT    | SPORT_ABOVE_TRAILER     | SPORT_TRAILER
    }

    @Unroll
    def "should return previous gear when driver is in #mode mode goes DOWNHILL with trailer and rpm (#rpm) is below #trailerRpm rpm"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .goesDownhill().withTrailer()
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 4
        where:
            mode     | rpm                     | trailerRpm
            ECO      | ECO_BELOW_TRAILER       | ECO_TRAILER
            COMFORT  | COMFORT_BELOW_TRAILER   | COMFORT_TRAILER
            SPORT    | SPORT_BELOW_TRAILER     | SPORT_TRAILER
    }

}
