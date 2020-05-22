package dev.iwanczuk.driver

import dev.iwanczuk.driver.exception.GearException
import spock.lang.Specification
import spock.lang.Unroll

class GearTest extends Specification {

    @Unroll
    def "should fail when gear is less than 0"() {
        when:
            Gear.of(-1)
        then:
            thrown GearException
    }

    @Unroll
    def "should return true when greater gear is greater than lower gear "() {
        when:
            def greaterGear = Gear.of(5)
            def lowerGear = Gear.of(4)
        then:
            greaterGear.isGreaterThan(lowerGear)
    }

    @Unroll
    def "should return false when greater gear is less than lower gear "() {
        when:
            def greaterGear = Gear.of(5)
            def lowerGear = Gear.of(4)
        then:
            lowerGear.isGreaterThan(greaterGear) == false
    }

    //todo: dopisać testy

}
