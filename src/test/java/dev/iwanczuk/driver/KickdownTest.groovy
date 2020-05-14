package dev.iwanczuk.driver

import spock.lang.Specification
import spock.lang.Unroll

import static dev.iwanczuk.driver.Constants.*

class KickdownTest extends Specification {

    @Unroll
    def "should return the same gear when driver is in #mode mode, rpm (#rpm) is between #minRpm and #maxRpm rpm and gas position is below threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .withGasPosition(BELOW_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode     | rpm               | minRpm       | maxRpm
            COMFORT  | COMFORT_IN_RANGE  | COMFORT_MIN  | COMFORT_MAX
            SPORT    | SPORT_IN_RANGE    | SPORT_MIN    | SPORT_MAX
    }

    @Unroll
    def "should return the same gear when driver is in #mode mode, rpm (#rpm) is between #minRpm and #maxRpm rpm and gas position is equal threshold"() {
        given:
        def driver = DriverConf.mode(mode).onGear(5)
                .withGasPosition(EQUAL_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode     | rpm               | minRpm       | maxRpm
            COMFORT  | COMFORT_IN_RANGE  | COMFORT_MIN  | COMFORT_MAX
            SPORT    | SPORT_IN_RANGE    | SPORT_MIN    | SPORT_MAX
    }

    @Unroll
    def "should return previous gear when driver is in #mode mode, rpm (#rpm) is between #minRpm and #maxRpm rpm and gas position is above threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .withGasPosition(ABOVE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 4
        where:
            mode     | rpm               | minRpm       | maxRpm
            COMFORT  | COMFORT_IN_RANGE  | COMFORT_MIN  | COMFORT_MAX
            SPORT    | SPORT_IN_RANGE    | SPORT_MIN    | SPORT_MAX
    }

    @Unroll
    def "should return previous gear when driver is in #mode mode, rpm (#rpm) is between #minRpm and #maxKickdownRpm rpm and gas position is above threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .withGasPosition(ABOVE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 4
        where:
            mode     | rpm                     | minRpm       | maxKickdownRpm
            COMFORT  | COMFORT_BELOW_KICKDOWN  | COMFORT_MIN  | COMFORT_KICKDOWN
            SPORT    | SPORT_BELOW_KICKDOWN    | SPORT_MIN    | SPORT_KICKDOWN
    }

    @Unroll
    def "should return the same gear when driver is in #mode mode, rpm (#rpm) is equal #maxKickdownRpm rpm and gas position is above threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .withGasPosition(ABOVE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode     | rpm               | maxKickdownRpm
            COMFORT  | COMFORT_KICKDOWN  | COMFORT_KICKDOWN
            SPORT    | SPORT_KICKDOWN    | SPORT_KICKDOWN
    }

    @Unroll
    def "should return the same gear when driver is in #mode mode, rpm (#rpm) is above #maxKickdownRpm rpm and gas position is above threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .withGasPosition(ABOVE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode     | rpm                     | maxKickdownRpm
            COMFORT  | COMFORT_ABOVE_KICKDOWN  | COMFORT_KICKDOWN
            SPORT    | SPORT_ABOVE_KICKDOWN    | SPORT_KICKDOWN
    }

    @Unroll
    def "should return previous gear when driver is in #mode mode, rpm (#rpm) is between #minRpm and #maxRpm rpm and gas position is equal double threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .withGasPosition(EQUAL_DOUBLE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 4
        where:
            mode   | rpm             | minRpm     | maxRpm
            SPORT  | SPORT_IN_RANGE  | SPORT_MIN  | SPORT_MAX
    }

    @Unroll
    def "should return two gears down when driver is in #mode mode, rpm (#rpm) is between #minRpm and #maxRpm rpm and gas position is above double threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .withGasPosition(ABOVE_DOUBLE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 3
        where:
            mode   | rpm             | minRpm     | maxRpm
            SPORT  | SPORT_IN_RANGE  | SPORT_MIN  | SPORT_MAX
    }

    @Unroll
    def "should return two gears down when driver is in #mode mode, rpm (#rpm) is between #minRpm and #maxDoubleKickdownRpm rpm and gas position is above double threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .withGasPosition(ABOVE_DOUBLE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 3
        where:
            mode   | rpm                   | minRpm     | maxDoubleKickdownRpm
            SPORT  | SPORT_BELOW_KICKDOWN  | SPORT_MIN  | SPORT_DOUBLE_KICKDOWN
    }

    @Unroll
    def "should return the same when driver is in #mode mode, rpm (#rpm) is equal #maxDoubleKickdownRpm rpm and gas position is above double threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .withGasPosition(ABOVE_DOUBLE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode   | rpm                   | maxDoubleKickdownRpm
            SPORT  | SPORT_DOUBLE_KICKDOWN  | SPORT_DOUBLE_KICKDOWN
    }

    @Unroll
    def "should return the same when driver is in #mode mode, rpm (#rpm) is above #maxDoubleKickdownRpm rpm and gas position is above double threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .withGasPosition(ABOVE_DOUBLE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode   | rpm                          | maxDoubleKickdownRpm
            SPORT  | SPORT_ABOVE_DOUBLE_KICKDOWN  | SPORT_DOUBLE_KICKDOWN
    }

}
