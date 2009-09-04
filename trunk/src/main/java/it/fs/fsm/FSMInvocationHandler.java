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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * This class manages all the methods' invocations (transitions) of
 * a finite state machine.
 * 
 * @author Fabiano Sarracco
 */
public class FSMInvocationHandler implements InvocationHandler {

    /** The classes representing all the possible states
        of the machine */
    private List<Class<?>> stateClasses;
    /** The class representing the initial state of
        the machine. */
    private Class<?> initialStateClass;
    /** The class representing the final state of the
        machine. */
    private Class<?> finalStateClass;
    /** The class managing all the activities of the
        machine. */
    private Class<?> activitiesClass;

    private Map<Class<?>, Object> stateObjects = new Hashtable<Class<?>, Object>();
    private Object activitiesObject;
    private Object currentState;

    /**
     * Constructor.
     *
     * @param stateClasses The classes representing all the possible states
     *          of the machine.
     * @param initialStateClass The class representing the initial state of
     *          the machine.
     * @param finalStateClass The class representing the final state of the
     *          machine.
     * @param activitiesClass The class managing all the activities of the
     *          machine.
     */
    FSMInvocationHandler(List<Class<?>> stateClasses, Class<?> initialStateClass,
                         Class<?> finalStateClass, Class<?> activitiesClass) {

        this.stateClasses = new ArrayList<Class<?>>(stateClasses);
        this.initialStateClass = initialStateClass;
        this.finalStateClass = finalStateClass;
        this.activitiesClass = activitiesClass;

        for (Class<?> stateClass : stateClasses) {
            initializeStateObject(stateClass);
        }
        setInitialState();
        initializeActivitiesObject();
    }

    private void initializeStateObject(Class<?> stateClass) {
        try {
            stateObjects.put(stateClass, stateClass.newInstance());
        } catch (InstantiationException ex) {
            throw new RuntimeException("State " + stateClass.getName() +
                    " cannot be instanciated: " + ex.getMessage(), ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException("State " + stateClass.getName() +
                    " has no accessible default constructor: " + ex.getMessage(), ex);
        }
    }

    private void initializeActivitiesObject() {
        try {
            activitiesObject = activitiesClass.newInstance();
        } catch (InstantiationException ex) {
            throw new RuntimeException("Activities class " + activitiesClass.getName() +
                    " cannot be instanciated: " + ex.getMessage(), ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException("Activities class " + activitiesClass.getName() +
                    " has no accessible default constructor: " + ex.getMessage(), ex);
        }
    }

    private void setInitialState() {
        currentState = stateObjects.get(initialStateClass);
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        // Metodo per la richiesta dello stato corrente
        if ("getCurrentState".equals(method.getName()) &&
                (method.getParameterTypes().length == 0)) {
            return currentState.getClass().getSimpleName();
        }
        // Metodo per la richiesta di tutti gli stati
        if ("getStates".equals(method.getName()) &&
                (method.getParameterTypes().length == 0)) {
            return this.stateObjects.values();
        }
        // Metodo per la richiesta della classe che gestisce le attivita'
        if ("getActivities".equals(method.getName()) &&
                (method.getParameterTypes().length == 0)) {
            return this.activitiesObject;
        }
        Method stateMethod = lookupCurrentStateMethod(method, args);
        Transition transition = (Transition) stateMethod.invoke(currentState, args);
        if (transition != null) {
            // Eseguo le attivita' di uscita dello stato corrente
            State s = currentState.getClass().getAnnotation(State.class);
            for (String exitAct : s.exit()) {
                executeActivity(exitAct, currentState);
            }
            for (String act : transition.getActivities()) {
                executeActivity(act, currentState);
            }
            Class<?> nextStateClass = transition.getNewState();
            currentState = stateObjects.get(nextStateClass);
            // Lo stato non e' stato annotato nella macchina a stati finiti.
            if (currentState == null) {
                initializeStateObject(nextStateClass);
                currentState = stateObjects.get(nextStateClass);
            }

            for (String entryAct : ((State) currentState.getClass().getAnnotation(State.class)).
                    entry()) {
                executeActivity(entryAct, currentState);
            }
        }
        return transition;
    }

    /**
     * Esegue una attivita' con un nome specifico, ricercandola prima nello stato speficicato
     * e poi (se non trovata) nella classe delle attivita'.
     *
     * @param activityName il nome dell'attivita' da eseguire.
     * @param state lo stato in cui ricercare l'attivita'.
     */
    private void executeActivity(String activityName, Object state) {
        Method activityMethod = null;
        try {
            activityMethod = state.getClass().getMethod(activityName);
            activityMethod.invoke(currentState);
        } catch (Exception ex) {
            // Lo stato corrente non fornice l'attivita' specificata
            // provo a cercare nella classe delle attivita'
            if (this.activitiesObject != null) {
                try {
                    activityMethod = this.activitiesObject.getClass().getMethod(activityName);
                    activityMethod.invoke(this.activitiesObject);
                } catch (NoSuchMethodException nsme) {
                    throw new RuntimeException("Activity '" + activityName + "' not available: " + nsme.
                            getMessage());
                } catch (IllegalAccessException nsme) {
                    throw new RuntimeException("Activity '" + activityName + "' not executable (IllegalAccessException): " + nsme.
                            getMessage());
                } catch (IllegalArgumentException nsme) {
                    throw new RuntimeException("Activity '" + activityName + "' not executable (IllegalArgumentException): " + nsme.
                            getMessage());
                } catch (java.lang.reflect.InvocationTargetException nsme) {
                    throw new RuntimeException("Activity '" + activityName + "' has generated an exception: " + nsme.
                            getCause());
                }
            } else {
                throw new RuntimeException("Activity '" + activityName + "' not available in the state: " + ex.
                        getMessage());
            }

        }
    }

    private Method lookupCurrentStateMethod(Method method, Object[] args) {
        try {
            return currentState.getClass().getMethod(method.getName(),
                    method.getParameterTypes());
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException("State '" + currentState.getClass().getName() + "' doesn't allow '" + method.
                    getName() + "'");
        }
    }
}