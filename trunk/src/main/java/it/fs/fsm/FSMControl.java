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
package it.fs.fsm;

import java.util.Collection;

/**
 * Interface to be implemented by (the proxy of) a finite state machine.
 * 
 * @author Fabiano Sarracco
 */
public interface FSMControl {
    /** Returns a string representing the current state of the machine. */
    public String getCurrentState();
    /** Returns a collection of all the states. */
    public Collection<Object> getStates();
    /** Returns the class which manages all the activities of the machine. */
    public Object getActivities();
}
