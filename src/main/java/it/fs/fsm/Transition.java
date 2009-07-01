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

import java.util.LinkedList;
import java.util.List;

/**
 * This class defines a transition, that is a series of activities
 * (methods) to be executed and a class representing the next state.
 *
 * @author Fabiano Sarracco
 */
public class Transition {

    /** Activities to be execute on the finite state machine during
     *  the transition. */
    private List<String> activities;
    /** The new state of the machine at the end of the transition. */
    private Class<?> newState;

    /**
     * Constructor with only a next state (without activities).
     * 
     * @param newState the class representing the new state.
     */
    public Transition(Class<?> newState) {
        this.newState = newState;
        this.activities = new LinkedList<String>();
    }

    /**
     * Constructor with the next state and a list of activities.
     * Activities are performed before entering the next state.
     *
     * @param newState the new state.
     * @param activities a list of activities to be performed before
     *          entering the new state.
     */
    public Transition(Class<?> newState, List<String> activities) {
        this.newState = newState;
        this.activities = activities;
    }

    /**
     * Returns the list of activities of the transition.
     *
     * @return a list of activities to be performed before entering
     *          the new state.
     */
    public List<String> getActivities() {
        return activities;
    }

    /**
     * Sets the activities to be executed during the transition.
     *
     * @param activities a list of activities.
     * @return the current transition, in order to allow setters' concatenation.
     */
    public Transition setActivities(List<String> activities) {
        this.activities = activities;
        return this;
    }

    /**
     * Adds a new activity to be executed during the transition, at the end
     * of the current activities list.
     *
     * @param activity an activity (method's name) to be executed
     *          during transition.
     * @return the current transition, in order to allow setters' concatenation.
     */
    public Transition addActivity(String activity) {
        this.activities.add(activity);
        return this;
    }

    /**
     * Returns the class representing the destination state of the transition.
     *
     * @return the classe rapresenting the state of the machine at the end
     *          of the current transition.
     */
    public Class<?> getNewState() {
        return newState;
    }

    /**
     * Sets the class representing the destination state of the transition.
     *
     * @param newState the class representing the new state of the machine
     *          at the end of current transtion.
     * @return the current transition, in order to allow setters' concatenation.
     */
    public Transition setNewState(Class<?> newState) {
        this.newState = newState;
        return this;
    }
}