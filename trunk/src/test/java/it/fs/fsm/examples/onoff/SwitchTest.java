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

import it.fs.fsm.FSMBuilder;
import it.fs.fsm.FSMControl;
import static junit.framework.Assert.*;

/**
 * Test class for the switch example.
 *
 * @author Fabiano Sarracco
 */
public class SwitchTest {

    public void testSwitch() {
        // We expect to just give the SwitchTest class to a builder
        Switch fsm = FSMBuilder.buildFSM(Switch.class);

        // We expect to get some sort of control to
        // know in which state the FSM is
        FSMControl fsmControl = (FSMControl) fsm;

        // The initial state should be Off
        assertEquals("Off", fsmControl.getCurrentState());

        // If we turn the switch on, the state should be On
        fsm.turnOn();
        assertEquals("On", fsmControl.getCurrentState());

        // If we turn it back off, the state should be Off
        fsm.turnOff();
        assertEquals("Off", fsmControl.getCurrentState());

        // If we turn it off again, it should complain as the
        // switch is already Off. The state should remain Off
        try {
            fsm.turnOff();
            fail("Shouldn't allow to turn off twice");
        } catch (Exception e) {
            // Failed to turn off twice: good
        }
        assertEquals("Off", fsmControl.getCurrentState());

        // Leave
        fsm.leave();
        assertEquals("FinalState", fsmControl.getCurrentState());

    }
}
