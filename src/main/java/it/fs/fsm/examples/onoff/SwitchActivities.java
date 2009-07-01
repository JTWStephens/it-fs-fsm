/*
Copyright 2008-2009 Fabiano Sarracco

This file is part of it-fs-fsm.

it-fs-fsm is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

it-fs-fsm is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with it-fs-fsm.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.fs.fsm.examples.onoff;

/**
 * This class contains all the activities to be executed
 * whithin the 'switch' finite state machine.
 *
 * @author Fabiano Sarracco
 */
public class SwitchActivities {

    /** Name of the activity which prints "Turning lights on!" on <code>stdout</code>.*/
    public static final String PRINT_TURN_ON = "printTurnOn";
    /** Name of the activity which prints "Turning lights off!" on <code>stdout</code>.*/
    public static final String PRINT_TURN_OFF = "printTurnOff";
    /** Name of the activity which prints "Lights are now off!" on <code>stdout</code>.*/
    public static final String LIGHTS_NOW_OFF = "lightsNowOff";
    /** Name of the activity which prints "Lights are now on." on <code>stdout</code>.*/
    public static final String LIGHTS_NOW_ON = "lightsNowOn";
    /** Name of the activity which prints "Lights are no more off!" on <code>stdout</code>.*/
    public static final String LIGHTS_NO_MORE_OFF = "lightsNoMoreOff";
    /** Name of the activity which prints "Lights are no more on." on <code>stdout</code>.*/
    public static final String LIGHTS_NO_MORE_ON = "lightsNoMoreOn";

    /**
     * Prints "Turning lights on!" on <code>stdout</code>.
     */
    public void printTurnOn() {
        System.out.println("Turning lights on!");
    }

    /**
     * Prints "Lights are now off!" on <code>stdout</code>.
     */
    public void lightsNowOff() {
        System.out.println("Lights are now off!");
    }

    /**
     * Prints "Lights are no more off!" on <code>stdout</code>.
     */
    public void lightsNoMoreOff() {
        System.out.println("Lights are no more off!");
    }

    /**
     * Prints "Turning lights off!" on <code>stdout</code>.
     */
    public void printTurnOff() {
        System.out.println("Turning lights off!");
    }

    /**
     * Prints "Lights are now on." on <code>stdout</code>.
     */
    public void lightsNowOn() {
        System.out.println("Lights are now on.");
    }

    /**
     * Prints "Lights are no more on." on <code>stdout</code>.
     */
    public void lightsNoMoreOn() {
        System.out.println("Lights are no more on.");
    }
}
