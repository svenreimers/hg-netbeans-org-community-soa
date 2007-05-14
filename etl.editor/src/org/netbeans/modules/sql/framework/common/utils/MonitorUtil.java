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
package org.netbeans.modules.sql.framework.common.utils;

import java.io.Serializable;

import org.netbeans.modules.sql.framework.model.TargetTable;


/**
 * @author Ritesh Adval
 * @version :$Revision$
 */
public class MonitorUtil implements Serializable{
    public static final String LOG_DETAILS_TABLE_PREFIX = "DETAILS_";

    public static String getDetailsTableName(String qName) {
        StringBuilder detailsTableNameBuf = new StringBuilder(25);

        detailsTableNameBuf.append(LOG_DETAILS_TABLE_PREFIX);
        detailsTableNameBuf.append(qName.trim());

        return detailsTableNameBuf.toString();
    }

    public static String getDetailsTableName(TargetTable table) {
        StringBuilder detailsTableNameBuf = new StringBuilder(25);

        detailsTableNameBuf.append(LOG_DETAILS_TABLE_PREFIX);
        detailsTableNameBuf.append(table.getAliasName());
        detailsTableNameBuf.append("_");
        detailsTableNameBuf.append(table.getName());

        return detailsTableNameBuf.toString();

    }
}

