package dev.iwanczuk.driver

import spock.lang.Specification
import spock.lang.Unroll

import static dev.iwanczuk.driver.Constants.*

class MDynamicModeTest extends Specification {

    @Unroll
    def "should return the same gear when driver is in #mode and M_DYNAMIC mode and rpm (#rpm) is below #minRpm rpm on first gear"() {
        given:
            def driver = DriverConf.mode(mode).onGear(1)
                .onMDynamicMode()
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
    def "should return the same gear when driver is in #mode and M_DYNAMIC mode and rpm (#rpm) is above #maxRpm rpm on last gear"() {
        given:
            def driver = DriverConf.mode(mode).onGear(8)
                .onMDynamicMode()
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
    def "should return the same gear when driver is in #mode and M_DYNAMIC mode and rpm (#rpm) is equal #minRpm rpm"() {
        given:
            def calculator = DriverConf.mode(mode).onGear(4)
                .onMDynamicMode()
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
    def "should return the same gear when driver is in #mode and M_DYNAMIC mode and rpm (#rpm) is equal #maxRpm rpm"() {
        given:
            def calculator = DriverConf.mode(mode).onGear(4)
                .onMDynamicMode()
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
    def "should return the same gear when driver is in #mode and M_DYNAMIC mode and rpm (#rpm) is between #minRpm and #maxRpm rpm"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onMDynamicMode()
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
    def "should return previous gear when driver is in #mode and M_DYNAMIC mode and rpm (#rpm) is below #minRpm rpm"() {
        given:
            def driver = DriverConf.mode(mode).onGear(2)
                .onMDynamicMode()
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
    def "should return next gear when driver is in #mode and M_DYNAMIC mode and rpm (#rpm) is above #maxRpm rpm"() {
        given:
            def driver = DriverConf.mode(mode).onGear(1)
                .onMDynamicMode()
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

    @Unroll
    def "should return the same gear when driver is in #mode and M_DYNAMIC mode and rpm (#rpm) is below #minRpm rpm while drifting"() {
        given:
            def driver = DriverConf.mode(mode).onGear(2)
                .onMDynamicMode().isDrifting()
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 2
        where:
            mode     | rpm                | minRpm
            ECO      | ECO_BELOW_MIN      | ECO_MIN
            COMFORT  | COMFORT_BELOW_MIN  | COMFORT_MIN
            SPORT    | SPORT_BELOW_MIN    | SPORT_MIN
    }

    @Unroll
    def "should return the same gear when driver is in #mode and M_DYNAMIC mode and rpm (#rpm) is above #maxRpm rpm while drifting"() {
        given:
            def driver = DriverConf.mode(mode).onGear(1)
                .onMDynamicMode().isDrifting()
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 1
        where:
            mode     | rpm                | maxRpm
            ECO      | ECO_ABOVE_MAX      | ECO_MAX
            COMFORT  | COMFORT_ABOVE_MAX  | COMFORT_MAX
            SPORT    | SPORT_ABOVE_MAX    | SPORT_MAX
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
    def "should return two gears down when driver is in #mode, M_DYNAMIC and FIRST_AGGRESSIVE mode, rpm (#rpm) is between #minRpm and #maxRpm rpm and gas position is above double threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onFirstAggressiveMode()
                .onMDynamicMode()
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
    def "should return two gears down when when driver is in #mode, M_DYNAMIC and FIRST_AGGRESSIVE mode, rpm (#rpm) is between #minRpm and #maxDoubleKickdownRpm rpm and gas position is above double threshold"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onFirstAggressiveMode()
                .onMDynamicMode()
                .withGasPosition(ABOVE_DOUBLE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 3
        where:
            mode   | rpm                         | minRpm           | maxDoubleKickdownRpm
            SPORT  | SPORT_BELOW_KICKDOWN * 1.3  | SPORT_MIN * 1.3  | SPORT_DOUBLE_KICKDOWN * 1.3
    }

    @Unroll
    def "should return the same gear when driver is in #mode, M_DYNAMIC and FIRST_AGGRESSIVE mode, rpm (#rpm) is between #minRpm and #maxRpm rpm and gas position is above double threshold while drifting"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onFirstAggressiveMode()
                .onMDynamicMode().isDrifting()
                .withGasPosition(ABOVE_DOUBLE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode   | rpm                   | minRpm           | maxRpm
            SPORT  | SPORT_IN_RANGE * 1.3  | SPORT_MIN * 1.3  | SPORT_MAX * 1.3
    }

    @Unroll
    def "should return the same gear when when driver is in #mode, M_DYNAMIC and FIRST_AGGRESSIVE mode, rpm (#rpm) is between #minRpm and #maxDoubleKickdownRpm rpm and gas position is above double threshold while drifting"() {
        given:
            def driver = DriverConf.mode(mode).onGear(5)
                .onFirstAggressiveMode()
                .onMDynamicMode().isDrifting()
                .withGasPosition(ABOVE_DOUBLE_KICKDOWN)
                .withRpm(rpm).build()
        when:
            def result = driver.calculate()
        then:
            result == 5
        where:
            mode   | rpm                         | minRpm           | maxDoubleKickdownRpm
            SPORT  | SPORT_BELOW_KICKDOWN * 1.3  | SPORT_MIN * 1.3  | SPORT_DOUBLE_KICKDOWN * 1.3
    }

}
