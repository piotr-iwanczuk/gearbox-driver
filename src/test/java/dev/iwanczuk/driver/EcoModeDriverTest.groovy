package dev.iwanczuk.driver

import dev.iwanczuk.driver.dto.ExternalSystemsCurrentState
import dev.iwanczuk.driver.dto.GearboxCurrentState
import dev.iwanczuk.driver.gearbox.Mode
import dev.iwanczuk.driver.gearbox.State
import spock.lang.Specification

class EcoModeDriverTest extends Specification {

    def "should upshift correctly" () {
        given: ""
            def driver = setupGearboxDriver(Mode.ECO, State.DRIVE, 1, 2000)
            EcoModeDriver.withGearbox(DriveStateGearbox.onGear(1)).with
        when:
            driver.manageGearbox()
        then:
            driver.getCurrentGear() == 2
    }

    def "should fail on downshifting" () {
        given:
            def driverConfiguration = new DriverConfiguration(Mode.ECO)
            def gearboxState = new GearboxCurrentState(State.DRIVE, 0)
            def externalSystemsState = new ExternalSystemsCurrentState(999)
            def driver = DriverFactory.getDriver(driverConfiguration, gearboxState, externalSystemsState)
        when:
            driver.manageGearbox()
        then:
            driver.getCurrentGear() == 2
    }

    private static def setupGearboxDriver(Mode mode, State state, int gear, double rpm) {
        def driverConfiguration = new DriverConfiguration(mode)
        def gearboxState = new GearboxCurrentState(state, gear)
        def externalSystemsState = new ExternalSystemsCurrentState(rpm)
        return DriverFactory.getDriver(driverConfiguration, gearboxState, externalSystemsState)
    }

    class EcoModeDriver {

        private DriverConfiguration driverConf = new DriverConfiguration(Mode.ECO)
        def gearbox = new GearboxCurrentState(state, gear)
        def externalSystems = new ExternalSystemsCurrentState(rpm)

        static EcoModeDriver withGearbox(GearboxCurrentState gearbox) {
            return new EcoModeDriver()
        }

        static EcoModeDriver withExternalSystems(ExternalSystemsCurrentState externalSystems) {
            return DriverFactory.getDriver(, gearbox, externalSystemsState)
        }

        static get() {

        }
    }

    class DriveStateGearbox {

        static GearboxCurrentState onGear(def gear) {
            return new GearboxCurrentState(State.DRIVE, gear)
        }
    }

}
