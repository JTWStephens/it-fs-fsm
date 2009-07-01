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

import it.fs.fsm.FiniteStateMachine;
import it.fs.fsm.Transition;

/**
 * This class describes a finite state machine representation of a switch.
 *
 * @author Fabiano Sarracco
 */
@FiniteStateMachine(states = {On.class, Off.class}, initialState = Off.class, activities = SwitchActivities.class)
public interface Switch {

    /**
     * Returns the transition representing the turning on of the switch.
     *
     * @return the transition representing the turning on of the switch.
     */
    Transition turnOn();

    /**
     * Returns the transition representing the turning off of the switch.
     *
     * @return the transition representing the turning off of the switch.
     */
    Transition turnOff();

    /**
     * Returns the transition representing the end of the switch usage.
     *
     * @return the transition representing the end of the switch usage.
     */
    Transition leave();
}