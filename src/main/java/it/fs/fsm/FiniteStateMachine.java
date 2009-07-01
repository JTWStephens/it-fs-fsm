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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for a finite state machine class.
 *
 * @author Fabiano Sarracco
 */
@Retention(value=RetentionPolicy.RUNTIME)
public @interface FiniteStateMachine {

    /** Definition of the states of the fsm. */
    Class<?>[] states() default {};
    /** Definition of the initial state of the fsm. */
    Class<?> initialState();
    /** Definition of the activities of the fsm. */
    Class<?> activities() default Object.class;
    /** Definition of the final state of the fsm */
    Class<?> finalState() default FinalState.class;

}