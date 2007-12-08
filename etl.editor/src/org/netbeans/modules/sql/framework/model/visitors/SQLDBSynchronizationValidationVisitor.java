/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */
package org.netbeans.modules.sql.framework.model.visitors;

import com.sun.sql.framework.exception.BaseException;
import com.sun.sql.framework.exception.DBSQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.netbeans.modules.sql.framework.model.SQLDBColumn;
import org.netbeans.modules.sql.framework.model.SQLDBModel;
import org.netbeans.modules.sql.framework.model.SQLDBTable;
import org.netbeans.modules.sql.framework.model.SQLDefinition;
import org.netbeans.modules.sql.framework.model.ValidationInfo;
import org.netbeans.modules.sql.framework.model.impl.ValidationInfoImpl;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import java.sql.Connection;
import org.netbeans.modules.sql.framework.model.DBMetaDataFactory;
import org.netbeans.modules.sql.framework.common.utils.DBExplorerConnectionUtil;
import org.netbeans.modules.sql.framework.model.DBConnectionDefinition;
import org.netbeans.modules.sql.framework.model.impl.AbstractDBTable;

/**
 * @author Girish Patil
 * @author Ahimanikya Satapathy
 * @version $Revision$
 */
public class SQLDBSynchronizationValidationVisitor {

    private class Table extends AbstractDBTable {
        public Table(String tname, String tcatalog, String tschema) {
            super(tname, tschema, tcatalog);
        }
    }
    private List<ValidationInfo> validationInfoList = new ArrayList<ValidationInfo>();

    public List<ValidationInfo> getValidationInfoList() {
        return this.validationInfoList;
    }

    public void visit(SQLDBModel etlDBModel) throws BaseException, DBSQLException, Exception {
        DBMetaDataFactory meta = new DBMetaDataFactory();
        Connection conn = null;
        DBConnectionDefinition connDef = null;
        connDef = etlDBModel.getETLDBConnectionDefinition();

        if (connDef != null) {
            conn = DBExplorerConnectionUtil.createConnection(connDef.getConnectionProperties());
            if (conn == null) {
                return;
            }
            meta.connectDB(conn);
            List collabTables = etlDBModel.getTables();

            if ((collabTables != null) && (!collabTables.isEmpty())) {
                Iterator itr = collabTables.iterator();
                SQLDBTable collabTable = null;
                while (itr.hasNext()) {
                    collabTable = (SQLDBTable) itr.next();
                    compareCollabTableWithDatabaseTable(collabTable, meta);
                }
            }
        }
    }

    public void visit(SQLDefinition definition) {
        List collabDatabases = definition.getAllDatabases();
        if ((collabDatabases != null) && (collabDatabases.size() > 0)) {
            SQLDBModel etlDBModel = null;
            Iterator itr = collabDatabases.iterator();
            while (itr.hasNext()) {
                try {
                    etlDBModel = (SQLDBModel) itr.next();
                    visit(etlDBModel);
                } catch (DBSQLException dbex) {
                    ValidationInfo vInfo = new ValidationInfoImpl(etlDBModel, dbex.getMessage(), ValidationInfo.VALIDATION_ERROR);
                    this.validationInfoList.add(vInfo);
                } catch (BaseException ex) {
                    Exceptions.printStackTrace(ex);
                }  catch (Exception ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }

    private void checkForUpdates(SQLDBColumn collabColumn, List newColumns, SQLDBTable table) {
        Iterator itr = newColumns.iterator();
        SQLDBColumn newColumn = null;
        boolean columnMatched = true;

        while (itr.hasNext()) {
            newColumn = (SQLDBColumn) itr.next();
            columnMatched = (compareWith(collabColumn, newColumn) == 0);
            if (columnMatched) {
                break;
            }
        }

        if (!columnMatched) {
            // Column not present or is modified in Database
            String desc = NbBundle.getMessage(this.getClass(), "MSG_column_not_found", collabColumn.getName(), table.getName());
            ValidationInfo vInfo = new ValidationInfoImpl(table, desc, ValidationInfo.VALIDATION_ERROR);
            this.validationInfoList.add(vInfo);
        }
    }

    private void checkForNewColumns(SQLDBColumn newColumn, List newColumns, SQLDBTable table) {
        Iterator itr = newColumns.iterator();
        SQLDBColumn collabColumn = null;
        boolean columnMatched = true;

        while (itr.hasNext()) {
            collabColumn = (SQLDBColumn) itr.next();
            columnMatched = (compareWith(collabColumn, newColumn) == 0);
            if (columnMatched) {
                break;
            }
        }

        if (!columnMatched) {
            // new column found
            String desc = NbBundle.getMessage(this.getClass(), "MSG_column_not_found", collabColumn.getName(), table.getName());
            ValidationInfo vInfo = new ValidationInfoImpl(table, desc, ValidationInfo.VALIDATION_ERROR);
            this.validationInfoList.add(vInfo);
        }
    }

    private int compareWith(SQLDBColumn collabCol, SQLDBColumn newCol) {
        // compare primary keys
        if (collabCol.isPrimaryKey() && !newCol.isPrimaryKey()) {
            return -1;
        } else if (!collabCol.isPrimaryKey() && newCol.isPrimaryKey()) {
            return 1;
        }

        // compare foreign keys
        if (collabCol.isForeignKey() && !newCol.isForeignKey()) {
            return -1;
        } else if (!collabCol.isForeignKey() && newCol.isForeignKey()) {
            return 1;
        }

        // compare type
        if (collabCol.getJdbcType() != newCol.getJdbcType()) {
            return -1;
        }

        // compare scale
        if (collabCol.getScale() != newCol.getScale()) {
            return -1;
        }

        // compare getPrecision
        if (collabCol.getPrecision() != newCol.getPrecision()) {
            return -1;
        }

        // compare getOrdinalPosition
        if (collabCol.getOrdinalPosition() != newCol.getOrdinalPosition()) {
            return -1;
        }

        // compare isNullable
        if (collabCol.isNullable() != newCol.isNullable()) {
            return -1;
        }

        return 0;
    }

    private void compareCollabTableWithDatabaseTable(SQLDBTable collabTable, DBMetaDataFactory meta) throws Exception {
        if (meta.isTableOrViewExist(collabTable.getCatalog(), collabTable.getSchema(), collabTable.getName())) {
            // Get the table from database
            Table newTable = new Table(collabTable.getName(), collabTable.getCatalog(), collabTable.getSchema());
            meta.populateColumns(newTable);

            List collabColumns = collabTable.getColumnList();
            List newColumns = newTable.getColumnList();

            //Check for update and delete
            for (Iterator itr = collabColumns.iterator(); itr.hasNext();) {
                checkForUpdates((SQLDBColumn) itr.next(), newColumns, collabTable);
            }

            // check for new columns
            for (Iterator itr = newColumns.iterator(); itr.hasNext();) {
                checkForNewColumns((SQLDBColumn) itr.next(), collabColumns, collabTable);
            }

            // TODO: XXXXX We also need to check PK, FK, Index modifications XXXXX
        } else {
            String desc = NbBundle.getMessage(this.getClass(), "MSG_table_not_found", collabTable.getName()) + " " + meta.getDBName();
            ValidationInfo vInfo = new ValidationInfoImpl(collabTable, desc, ValidationInfo.VALIDATION_ERROR);
            validationInfoList.add(vInfo);
            return;
        }
    }
}