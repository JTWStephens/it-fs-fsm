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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

/**
 * This class contains factory methods to generate
 * a new finite state machine.
 *
 * @author Fabiano Sarracco
 */
public class FSMBuilder {

    /**
     * Empty constructor.
     */
    private FSMBuilder() {
    }

    public static <T> T buildFSM(Class<T> fsmClass) {
        return buildFSM(fsmClass, extractStateClasses(fsmClass),
                extractInitialState(fsmClass),
                extractFinalState(fsmClass),
                extractActivitiesClass(fsmClass));
    }

    private static List<Class<?>> extractStateClasses(Class<?> fsmClass) {
        FiniteStateMachine fsm = fsmClass.getAnnotation(FiniteStateMachine.class);
        return Arrays.asList(fsm.states());
    }

    private static Class<?> extractActivitiesClass(Class<?> fsmClass) {
        FiniteStateMachine fsm = fsmClass.getAnnotation(FiniteStateMachine.class);
        return fsm.activities();
    }

    private static Class<?> extractInitialState(Class<?> fsmClass) {
        FiniteStateMachine fsm = fsmClass.getAnnotation(FiniteStateMachine.class);
        return fsm.initialState();
    }

    private static Class<?> extractFinalState(Class<?> fsmClass) {
        FiniteStateMachine fsm = fsmClass.getAnnotation(FiniteStateMachine.class);
        return fsm.finalState();
    }

    private static <T> T buildFSM(Class<T> fsmClass, List<Class<?>> stateClasses,
                                  Class<?> initialStateClass, Class<?> finalStateClass,
                                  Class<?> activitiesClass) {
        return buildFSM(fsmClass, new FSMInvocationHandler(stateClasses,
                initialStateClass, finalStateClass, activitiesClass));
    }

    @SuppressWarnings("unchecked")
    static <T> T buildFSM(Class<T> fsmClass, InvocationHandler handler) {
        // Suppressed unchecked warning
        // The proxy will be of class T as the classToken is passed as one of the
        // interfaces to be implemented by the proxy.
        return (T) Proxy.newProxyInstance(fsmClass.getClassLoader(),
                new Class<?>[]{fsmClass, FSMControl.class}, handler);
    }
}