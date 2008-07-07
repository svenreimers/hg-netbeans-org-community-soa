/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 * 
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.bpel.debugger.spi.plugin.event;

/**
 * Manager of incoming debugger events for a target
 * {@link org.netbeans.modules.bpel.debugger.spi.plugin.BpelEngine}.
 * Events are always grouped in
 * {@link org.netbeans.modules.bpel.debugger.spi.plugin.event.EventSet}s.
 * {@link org.netbeans.modules.bpel.debugger.spi.plugin.event.EventSet}s
 * generated by the BPEL Debugger Plugin can be read here.
 * There is one instance of EventQueue assigned to a particular
 * {@link org.netbeans.modules.bpel.debugger.spi.plugin.BpelEngine}. It's
 * provided by the BPEL Debugger Plugin and can be obtained by invoking
 * {@link org.netbeans.modules.bpel.debugger.spi.plugin.BpelEngine#getEventQueue}.
 * <br><br>
 * Some events cause the suspension of process instances -
 * those which are issued using
 * {@link org.netbeans.modules.bpel.debugger.spi.plugin.request.EventRequest}s
 * with a
 * {@link org.netbeans.modules.bpel.debugger.spi.plugin.request.EventRequest.SuspendPolicy
 * suspend policy} of
 * {@link org.netbeans.modules.bpel.debugger.spi.plugin.request.EventRequest.SuspendPolicy#SUSPEND_ALL}
 * or
 * {@link org.netbeans.modules.bpel.debugger.spi.plugin.request.EventRequest.SuspendPolicy#SUSPEND_EVENT_PROCESS_INSTANCE}.
 * If these suspensions are not resumed the corresponding process instances
 * will hang. Thus, it is always good policy to
 * {@link org.netbeans.modules.bpel.debugger.spi.plugin.event.EventQueue#remove}
 * every event set from the event queue until an
 * {@link org.netbeans.modules.bpel.debugger.spi.plugin.event.EventSet}
 * containing a
 * {@link org.netbeans.modules.bpel.debugger.spi.plugin.event.BpelEngineDisconnectedEvent}
 * is read.
 *
 * @author Alexander Zgursky
 */
public interface EventQueue {
    
    /**
     * Waits forever for the next available
     * {@link org.netbeans.modules.bpel.debugger.spi.plugin.event.EventSet}.
     * When
     * {@link org.netbeans.modules.bpel.debugger.spi.plugin.event.EventSet}
     * becomes available, removes it from the EventQueue
     * and returns as the result.
     *
     * @return the next
     * {@link org.netbeans.modules.bpel.debugger.spi.plugin.event.EventSet}
     *
     * @throws InterruptedException
     *  if another thread has interrupted this thread
     * @throws org.netbeans.modules.bpel.debugger.spi.plugin.BpelEngineDisconnectedException
     *  if the
     *  {@link org.netbeans.modules.bpel.debugger.spi.plugin.BpelEngine}
     *  has been disconnected. Note this will always
     *  be preceded by a
     *  {@link org.netbeans.modules.bpel.debugger.spi.plugin.event.BpelEngineDisconnectedEvent}.
     */
    EventSet remove() throws InterruptedException;
    
    /**
     * Waits a specified time for the next available
     * {@link org.netbeans.modules.bpel.debugger.spi.plugin.event.EventSet}.
     * When
     * {@link org.netbeans.modules.bpel.debugger.spi.plugin.event.EventSet}
     * becomes available, removes it from the EventQueue
     * and returns as the result.
     *
     * @return the next
     * {@link org.netbeans.modules.bpel.debugger.spi.plugin.event.EventSet},
     * or <code>null</code> if there is a timeout.
     *
     * @throws InterruptedException
     *  if another thread has interrupted this thread
     * @throws org.netbeans.modules.bpel.debugger.spi.plugin.BpelEngineDisconnectedException
     *  if the
     *  {@link org.netbeans.modules.bpel.debugger.spi.plugin.BpelEngine}
     *  has been disconnected. Note this will always
     *  be preceded by a
     *  {@link org.netbeans.modules.bpel.debugger.spi.plugin.event.BpelEngineDisconnectedEvent}.
     * @throws IllegalArgumentException
     *  if the timeout argument contains an illegal value
     */
    EventSet remove(long timeout) throws InterruptedException;
}
