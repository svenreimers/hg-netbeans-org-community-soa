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
package org.netbeans.modules.sql.framework.ui.model;

import java.awt.Point;

import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEditSupport;

import org.netbeans.modules.model.database.DBTable;
import org.netbeans.modules.sql.framework.model.SQLCanvasObject;
import org.netbeans.modules.sql.framework.model.SQLConnectableObject;
import org.netbeans.modules.sql.framework.model.SQLObject;
import org.netbeans.modules.sql.framework.model.SQLObjectEvent;
import org.netbeans.modules.sql.framework.model.SQLOperator;
import org.netbeans.modules.sql.framework.model.VisibleSQLPredicate;
import org.netbeans.modules.sql.framework.ui.event.SQLDataEvent;
import org.netbeans.modules.sql.framework.ui.event.SQLDataListener;
import org.netbeans.modules.sql.framework.ui.event.SQLLinkEvent;

import com.sun.sql.framework.exception.BaseException;

public interface SQLUIModel {

    public void addJavaOperator(SQLOperator javaOp);

    public void addObject(SQLObject sqlObject) throws BaseException;

    public void addObjectIgnoreUndo(SQLObject sqlObject) throws BaseException;

    /**
     * Adds a SourceTableImpl instance using the given DBTable instance as a template, if
     * it does not already exist.
     * 
     * @param srcTable DBTable to serve as template for the new SourceTableImpl instance.
     * @return new SourceTableImpl instance
     * @throws BaseException if error occurs during creation
     */
    public SQLObject addSourceTable(DBTable srcTable, Point loc) throws BaseException;

    public void addSQLDataListener(SQLDataListener l);

    public void addSQLObject(SQLObject sqlObject) throws BaseException;

    /**
     * Adds a TargetTableImpl instance using the given DBTable instance as a template, if
     * it does not already exist.
     * 
     * @param targetTable DBTable to serve as template for the new TargetTableImpl
     *        instance.
     * @return SourceTableImpl representing the contents of the given template object; may
     *         be a pre-existing object.
     * @throws BaseException if error occurs during creation
     */
    public SQLObject addTargetTable(DBTable targetTable, Point loc) throws BaseException;

    public void clearJavaOperators();

    public void clearListener();

    /**
     * Called when a link is created in collaboration view
     * 
     * @param srcObject object which is source of new link
     * @param srcFieldName -
     * @param destObject object which is destination of new link
     * @param destFieldName -
     * @throws BaseException if error occurs during linking
     */
    public void createLink(SQLCanvasObject srcObject, String srcFieldName, SQLConnectableObject destObject, String destFieldName)
            throws BaseException;

    /**
     * Called when a link is created in collaboration view
     * 
     * @param srcObject object which is source of new link
     * @param srcFieldName -
     * @param destObject object which is destination of new link
     * @param destFieldName -
     * @throws BaseException if error occurs during linking
     */
    public void createLinkIgnoreUndo(SQLCanvasObject srcObject, String srcFieldName, SQLConnectableObject destObject, String destFieldName)
            throws BaseException;

    public SQLCanvasObject createObject(String className) throws BaseException;

    public void createVisiblePredicateRefObj(VisibleSQLPredicate predicate);

    public void fireChildObjectCreatedEvent(final SQLDataEvent evt);

    public void fireChildObjectDeletedEvent(final SQLDataEvent evt);

    public void fireSQLDataCreationEvent(final SQLDataEvent evt) throws BaseException;

    public void fireSQLDataDeletionEvent(final SQLDataEvent evt);

    public void fireSQLDataUpdatedEvent(final SQLDataEvent evt);

    public void fireSQLLinkCreationEvent(final SQLLinkEvent evt);

    public void fireSQLLinkDeletionEvent(final SQLLinkEvent evt);

    public UndoableEditSupport getUndoEditSupport();

    public UndoManager getUndoManager();

    /**
     * Check if a java operator is used in the model.
     * 
     * @return true if a java operator is used.
     */
    public boolean isContainsJavaOperators();

    public boolean isDirty();

    /**
     * called when an sql object is added
     * 
     * @param evt event object
     */
    public void objectAdded(SQLObjectEvent evt);

    /**
     * called when an sql object is removed
     * 
     * @param evt event object
     */
    public void objectRemoved(SQLObjectEvent evt);

    public void removeJavaOperator(SQLOperator javaOp);

    /**
     * Removes a link from backend model object.
     * 
     * @param srcObject object which is source of link removed
     * @param srcFieldName -
     * @param destObject object which is destination of link removed
     * @param destFieldName -
     * @throws BaseException if error occurs during unlinking
     */
    public void removeLink(SQLCanvasObject srcObject, String srcFieldName, SQLConnectableObject destObject, String destFieldName)
            throws BaseException;

    /**
     * Removes a link from backend model object.
     * 
     * @param srcObject object which is source of link removed
     * @param srcFieldName -
     * @param destObject object which is destination of link removed
     * @param destFieldName -
     * @throws BaseException if error occurs during unlinking
     */
    public void removeLinkIgnoreUndo(SQLCanvasObject srcObject, String srcFieldName, SQLConnectableObject destObject, String destFieldName)
            throws BaseException;

    public void removeObject(SQLObject sqlObject) throws BaseException;

    public void removeObjectIgnoreUndo(SQLObject sqlObject) throws BaseException;

    public void removeSQLDataListener(SQLDataListener l);

    public void setDirty(boolean dirty);

}
