package dev.iwanczuk.driver

import spock.lang.Specification
import spock.lang.Unroll

import static dev.iwanczuk.driver.Constants.*

class BaseDriverTest extends Specification {

    @Unroll
    def "should return the same gear when driver is in #mode mode and rpm (#rpm) is below #minRpm rpm on first gear"() {
        given:
            def driver = DriverConf.mode(mode).onGear(1)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 1
        where:
            mode     | rpm                | minRpm
            ECO      | ECO_BELOW_MIN      | ECO_MIN
            COMFORT  | COMFORT_BELOW_MIN  | COMFORT_MIN
            SPORT    | SPORT_BELOW_MIN    | SPORT_MIN
    }

    @Unroll
    def "should return the same gear when driver is in #mode mode and rpm (#rpm) is above #maxRpm rpm on last gear"() {
        given:
            def driver = DriverConf.mode(mode).onGear(8)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 8
        where:
            mode     | rpm                | maxRpm
            ECO      | ECO_ABOVE_MAX      | ECO_MAX
            COMFORT  | COMFORT_ABOVE_MAX  | COMFORT_MAX
            SPORT    | SPORT_ABOVE_MAX    | SPORT_MAX
    }

    @Unroll
    def "should return the same gear when driver is in #mode mode and rpm (#rpm) is equal #minRpm rpm"() {
        given:
            def calculator = DriverConf.mode(mode).onGear(4)
                .withRpm(rpm).build()
        when:
            def result = calculator.calculate()
        then:
            result == 4
        where:
            mode     | rpm          | minRpm
            ECO      | ECO_MIN      | ECO_MIN
            COMFORT  | COMFORT_MIN  | COMFORT_MIN
            SPORT    | SPORT_MIN    | SPORT_MIN
    }

    @Unroll
    def "should return the same gear when driver is in #mode mode and rpm (#rpm) is equal #maxRpm rpm"() {
        given:
            def calculator = DriverConf.mode(mode).onGear(4)
                .withRpm(rpm).build()
        when:
            def result = calculator.calculate()
        then:
            result == 4
        where:
            mode     | rpm          | maxRpm
            ECO      | ECO_MAX      | ECO_MAX
            COMFORT  | COMFORT_MAX  | COMFORT_MAX
            SPORT    | SPORT_MAX    | SPORT_MAX
    }

    @Unroll
    def "should return the same gear when driver is in #mode mode and rpm (#rpm) is between #minRpm and #maxRpm rpm"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode     | rpm               | minRpm       | maxRpm
            ECO      | ECO_IN_RANGE      | ECO_MIN      | ECO_MAX
            COMFORT  | COMFORT_IN_RANGE  | COMFORT_MIN  | COMFORT_MAX
            SPORT    | SPORT_IN_RANGE    | SPORT_MIN    | SPORT_MAX
    }

    @Unroll
    def "should return previous gear when driver is in #mode mode and rpm (#rpm) is below #minRpm rpm"() {
        given:
            def driver = DriverConf.mode(mode).onGear(2)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 1
        where:
            mode     | rpm                | minRpm
            ECO      | ECO_BELOW_MIN      | ECO_MIN
            COMFORT  | COMFORT_BELOW_MIN  | COMFORT_MIN
            SPORT    | SPORT_BELOW_MIN    | SPORT_MIN
    }

    @Unroll
    def "should return next gear when driver is in #mode mode and rpm (#rpm) is above #maxRpm rpm"() {
        given:
            def driver = DriverConf.mode(mode).onGear(1)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 2
        where:
            mode     | rpm                | maxRpm
            ECO      | ECO_ABOVE_MAX      | ECO_MAX
            COMFORT  | COMFORT_ABOVE_MAX  | COMFORT_MAX
            SPORT    | SPORT_ABOVE_MAX    | SPORT_MAX
    }

}
