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

import it.fs.fsm.FinalState;
import it.fs.fsm.State;
import it.fs.fsm.Transition;
import static it.fs.fsm.examples.onoff.SwitchActivities.*;

/**
 * This class represents the 'off' state of the switch.

 * Within this state it is possible to turn on the switch
 * or to leave.
 *
 * @author Fabiano Sarracco
 */
@State(entry={LIGHTS_NOW_OFF}, exit={LIGHTS_NO_MORE_OFF})
public class Off {

    /**
     * Returns the transition representing the turning on of the switch.
     *
     * @return the transition representing the turning on of the switch.
     */
    public Transition turnOn() {
        return new Transition(On.class).addActivity(PRINT_TURN_ON);
    }

    /**
     * Returns the transition representing the end of the switch usage.
     *
     * @return the transition representing the end of the switch usage.
     */
    public Transition leave() {
        return new Transition(FinalState.class);
    }


}