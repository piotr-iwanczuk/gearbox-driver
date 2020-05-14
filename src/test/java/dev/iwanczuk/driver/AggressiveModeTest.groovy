package dev.iwanczuk.driver

import spock.lang.Specification
import spock.lang.Unroll

import static dev.iwanczuk.driver.Constants.*

class AggressiveModeTest extends Specification {

    @Unroll
    def "should return the same gear when driver is in #mode and FIRST_AGGRESSIVE mode and rpm (#rpm) is below #minRpm rpm on first gear"() {
        given:
            def driver = DriverConf.mode(mode).onGear(1)
                .onFirstAggressiveMode()
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 1
        where:
            mode     | rpm                      | minRpm
            ECO      | ECO_BELOW_MIN * 1.3      | ECO_MIN * 1.3
            COMFORT  | COMFORT_BELOW_MIN * 1.3  | COMFORT_MIN * 1.3
            SPORT    | SPORT_BELOW_MIN * 1.3    | SPORT_MIN * 1.3
    }

    @Unroll
    def "should return the same gear when driver is in #mode and FIRST_AGGRESSIVE mode and rpm (#rpm) is above #maxRpm rpm on last gear"() {
        given:
            def driver = DriverConf.mode(mode).onGear(8)
                .onFirstAggressiveMode()
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 8
        where:
            mode     | rpm                      | maxRpm
            ECO      | ECO_ABOVE_MAX * 1.3      | ECO_MAX * 1.3
            COMFORT  | COMFORT_ABOVE_MAX * 1.3  | COMFORT_MAX * 1.3
            SPORT    | SPORT_ABOVE_MAX * 1.3    | SPORT_MAX * 1.3
    }

    @Unroll
    def "should return the same gear when driver is in #mode and FIRST_AGGRESSIVE mode and rpm (#rpm) is equal #minRpm rpm"() {
        given:
            def calculator = DriverConf.mode(mode).onGear(4)
                .onFirstAggressiveMode()
                .withRpm(rpm).build()
        when:
            def result = calculator.calculate()
        then:
            result == 4
        where:
            mode     | rpm                | minRpm
            ECO      | ECO_MIN * 1.3      | ECO_MIN * 1.3
            COMFORT  | COMFORT_MIN * 1.3  | COMFORT_MIN * 1.3
            SPORT    | SPORT_MIN * 1.3    | SPORT_MIN * 1.3
    }

    @Unroll
    def "should return the same gear when driver is in #mode and FIRST_AGGRESSIVE mode and rpm (#rpm) is equal #maxRpm rpm rpm"() {
        given:
            def calculator = DriverConf.mode(mode).onGear(4)
                .onFirstAggressiveMode()
                .withRpm(rpm).build()
        when:
            def result = calculator.calculate()
        then:
            result == 4
        where:
            mode     | rpm                | maxRpm
            ECO      | ECO_MAX * 1.3      | ECO_MAX * 1.3
            COMFORT  | COMFORT_MAX * 1.3  | COMFORT_MAX * 1.3
            SPORT    | SPORT_MAX * 1.3    | SPORT_MAX * 1.3
    }

    @Unroll
    def "should return the same gear when driver is in #mode and FIRST_AGGRESSIVE mode and rpm (#rpm) is between #minRpm and #maxRpm rpm"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onFirstAggressiveMode()
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode     | rpm                     | minRpm             | maxRpm
            ECO      | ECO_IN_RANGE * 1.3      | ECO_MIN * 1.3      | ECO_MAX * 1.3
            COMFORT  | COMFORT_IN_RANGE * 1.3  | COMFORT_MIN * 1.3  | COMFORT_MAX * 1.3
            SPORT    | SPORT_IN_RANGE * 1.3    | SPORT_MIN * 1.3    | SPORT_MAX * 1.3
    }

    @Unroll
    def "should return previous gear when driver is in #mode and FIRST_AGGRESSIVE mode and rpm (#rpm) is below #minRpm rpm"() {
        given:
            def driver = DriverConf.mode(mode).onGear(2)
                .onFirstAggressiveMode()
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 1
        where:
            mode     | rpm                      | minRpm
            ECO      | ECO_BELOW_MIN * 1.3      | ECO_MIN * 1.3
            COMFORT  | COMFORT_BELOW_MIN * 1.3  | COMFORT_MIN * 1.3
            SPORT    | SPORT_BELOW_MIN * 1.3    | SPORT_MIN * 1.3
    }

    @Unroll
    def "should return next gear when driver is in #mode and FIRST_AGGRESSIVE mode and rpm (#rpm) is above #maxRpm rpm"() {
        given:
            def driver = DriverConf.mode(mode).onGear(1)
                .onFirstAggressiveMode()
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 2
        where:
            mode     | rpm                      | maxRpm
            ECO      | ECO_ABOVE_MAX * 1.3      | ECO_MAX * 1.3
            COMFORT  | COMFORT_ABOVE_MAX * 1.3  | COMFORT_MAX * 1.3
            SPORT    | SPORT_ABOVE_MAX * 1.3    | SPORT_MAX * 1.3
    }

    @Unroll
    def "should return the same gear when driver is in #mode and FIRST_AGGRESSIVE mode, rpm (#rpm) is between #minRpm and #maxRpm rpm and gas position is below threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onFirstAggressiveMode()
                .withGasPosition(BELOW_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode     | rpm                     | minRpm             | maxRpm
            COMFORT  | COMFORT_IN_RANGE * 1.3  | COMFORT_MIN * 1.3  | COMFORT_MAX * 1.3
            SPORT    | SPORT_IN_RANGE * 1.3    | SPORT_MIN * 1.3    | SPORT_MAX * 1.3
    }

    @Unroll
    def "should return the same gear when driver is in #mode and FIRST_AGGRESSIVE mode, rpm (#rpm) is between #minRpm and #maxRpm rpm and gas position is equal threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onFirstAggressiveMode()
                .withGasPosition(EQUAL_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode     | rpm                     | minRpm             | maxRpm
            COMFORT  | COMFORT_IN_RANGE * 1.3  | COMFORT_MIN * 1.3  | COMFORT_MAX * 1.3
            SPORT    | SPORT_IN_RANGE * 1.3    | SPORT_MIN * 1.3    | SPORT_MAX * 1.3
    }

    @Unroll
    def "should return previous gear when driver is in #mode and FIRST_AGGRESSIVE mode, rpm (#rpm) is between #minRpm and #maxRpm rpm and gas position is above threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onFirstAggressiveMode()
                .withGasPosition(ABOVE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 4
        where:
            mode     | rpm                     | minRpm             | maxRpm
            COMFORT  | COMFORT_IN_RANGE * 1.3  | COMFORT_MIN * 1.3  | COMFORT_MAX * 1.3
            SPORT    | SPORT_IN_RANGE * 1.3    | SPORT_MIN * 1.3    | SPORT_MAX * 1.3
    }

    @Unroll
    def "should return previous gear when driver is in #mode and FIRST_AGGRESSIVE mode, rpm (#rpm) is between #minRpm and #maxKickdownRpm rpm and gas position is above threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onFirstAggressiveMode()
                .withGasPosition(ABOVE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 4
        where:
            mode     | rpm                           | minRpm             | maxKickdownRpm
            COMFORT  | COMFORT_BELOW_KICKDOWN * 1.3  | COMFORT_MIN * 1.3  | COMFORT_KICKDOWN * 1.3
            SPORT    | SPORT_BELOW_KICKDOWN * 1.3    | SPORT_MIN * 1.3    | SPORT_KICKDOWN * 1.3
    }

    @Unroll
    def "should return the same gear when driver is in #mode and FIRST_AGGRESSIVE mode, rpm (#rpm) is equal #maxKickdownRpm rpm and gas position is above threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onFirstAggressiveMode()
                .withGasPosition(ABOVE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode     | rpm                     | maxKickdownRpm
            COMFORT  | COMFORT_KICKDOWN * 1.3  | COMFORT_KICKDOWN * 1.3
            SPORT    | SPORT_KICKDOWN * 1.3    | SPORT_KICKDOWN * 1.3
    }

    @Unroll
    def "should return the same gear when driver is in #mode and FIRST_AGGRESSIVE mode, rpm (#rpm) is above #maxKickdownRpm rpm and gas position is above threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onFirstAggressiveMode()
                .withGasPosition(ABOVE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode     | rpm                           | maxKickdownRpm
            COMFORT  | COMFORT_ABOVE_KICKDOWN * 1.3  | COMFORT_KICKDOWN * 1.3
            SPORT    | SPORT_ABOVE_KICKDOWN * 1.3    | SPORT_KICKDOWN * 1.3
    }

    @Unroll
    def "should return previous gear when driver is in #mode and FIRST_AGGRESSIVE mode, rpm (#rpm) is between #minRpm and #maxRpm rpm and gas position is equal double threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onFirstAggressiveMode()
                .withGasPosition(EQUAL_DOUBLE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 4
        where:
            mode   | rpm                   | minRpm           | maxRpm
            SPORT  | SPORT_IN_RANGE * 1.3  | SPORT_MIN * 1.3  | SPORT_MAX * 1.3
    }

    @Unroll
    def "should return two gears down when driver is in #mode and FIRST_AGGRESSIVE mode, rpm (#rpm) is between #minRpm and #maxRpm rpm and gas position is above double threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onFirstAggressiveMode()
                .withGasPosition(ABOVE_DOUBLE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 3
        where:
            mode   | rpm                   | minRpm           | maxRpm
            SPORT  | SPORT_IN_RANGE * 1.3  | SPORT_MIN * 1.3  | SPORT_MAX * 1.3
    }

    @Unroll
    def "should return two gears down when driver is in #mode and FIRST_AGGRESSIVE mode, rpm (#rpm) is between #minRpm and #maxDoubleKickdownRpm rpm and gas position is above double threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onFirstAggressiveMode()
                .withGasPosition(ABOVE_DOUBLE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 3
        where:
            mode   | rpm                         | minRpm           | maxDoubleKickdownRpm
            SPORT  | SPORT_BELOW_DOUBLE_KICKDOWN * 1.3  | SPORT_MIN * 1.3  | SPORT_DOUBLE_KICKDOWN * 1.3
    }

    @Unroll
    def "should return the same when driver is in #mode and FIRST_AGGRESSIVE mode, rpm (#rpm) is equal #maxDoubleKickdownRpm rpm and gas position is above double threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onFirstAggressiveMode()
                .withGasPosition(ABOVE_DOUBLE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode   | rpm                          | maxDoubleKickdownRpm
            SPORT  | SPORT_DOUBLE_KICKDOWN * 1.3  | SPORT_DOUBLE_KICKDOWN * 1.3
    }

    @Unroll
    def "should return the same when driver is in #mode and FIRST_AGGRESSIVE mode, rpm (#rpm) is above #maxDoubleKickdownRpm rpm and gas position is above double threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onFirstAggressiveMode()
                .withGasPosition(ABOVE_DOUBLE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode   | rpm                                | maxDoubleKickdownRpm
            SPORT  | SPORT_ABOVE_DOUBLE_KICKDOWN * 1.3  | SPORT_DOUBLE_KICKDOWN * 1.3
    }

}
