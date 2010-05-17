/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
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
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
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
package org.netbeans.modules.edm.model.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.netbeans.modules.edm.editor.utils.TagParserUtility;
import org.netbeans.modules.edm.model.GUIInfo;
import org.netbeans.modules.edm.model.SQLCaseOperator;
import org.netbeans.modules.edm.model.SQLConstants;
import org.netbeans.modules.edm.model.SQLInputObject;
import org.netbeans.modules.edm.model.SQLObject;
import org.netbeans.modules.edm.model.SQLWhen;
import org.netbeans.modules.edm.editor.utils.GeneratorUtil;
import org.netbeans.modules.edm.model.visitors.SQLVisitor;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.netbeans.modules.edm.model.EDMException;
import java.util.logging.Logger;
import org.openide.util.NbBundle;


/**
 * Case operator used when we want to do a join and lookup
 * 
 * @author Sudhi Seshachala
 * @author Jonathan Giron
 * @version $Revision$
 */
public class SQLCaseOperatorImpl extends SQLConnectableObjectImpl implements SQLCaseOperator {

    private static final String DEFAULT = "default"; // NOI18N
    private static transient final Logger mLogger = Logger.getLogger(SQLCaseOperatorImpl.class.getName());
    
    /**
     * List of when for this case expression
     */
    protected List whenList = new ArrayList();

    private GUIInfo guiInfo = new GUIInfo();

    /**
     * Constructor
     */
    public SQLCaseOperatorImpl() {
        super();
        this.type = SQLConstants.CASE;
        
        String dispName = "default";
        SQLInputObject input = new SQLInputObjectImpl(DEFAULT, dispName, null);
        this.inputMap.put(DEFAULT, input);
    }

    public void addInput(String argName, SQLObject newInput) throws EDMException {
        super.addInput(argName, newInput);
    }


    public boolean addSQLWhen(SQLWhen when) throws EDMException {
        if (when == null) {
            throw new EDMException(NbBundle.getMessage(SQLCaseOperatorImpl.class, "ERROR_null_Case"));
        }
        when.setParentObject(this);
        this.whenList.add(when);

        return true;
    }


    public int compareTo(Object refObj) {
        SQLCaseOperator refCase = (SQLCaseOperator) refObj;

        if (refCase != null) {
            return this.id.compareTo(refCase.getId());
        }
        return -1;
    }

    /**
     * Creates a new SQLWhen object and returns it.
     * 
     * @return SQLWhen as the newly created object.
     * @throws EDMException if any input params are passed in as null.
     */
    public SQLWhen createSQLWhen() throws EDMException {
        SQLWhen when = new SQLWhenImpl(generateNewWhenName());
        // no need to generate id for when as it is not referenced anywhere
        return when;
    }

    /**
     * Compares two objects for equivalence.
     * 
     * @param refObj is the object to be compared against this.
     * @return boolean true if equal, false if not equal.
     */
    public boolean equals(Object refObj) {
        if (refObj == null) {
            return false;
        }

        if (!(refObj instanceof SQLCaseOperator)) {
            return false;
        }

        SQLCaseOperator caseStatement = (SQLCaseOperator) refObj;

        if ((caseStatement.getId() == null && this.id != null) || (caseStatement.getId() != null && this.id == null)) {
            return false;
        }

        if (caseStatement.getId() != null && this.id != null && !this.id.equals(caseStatement.getId())) {
            return false;
        }

        return true;
    }

    public String generateNewWhenName() {
        String whenName = "when";
        String newName = whenName;
        int count = 0;
        while (isWhenExist(newName)) {
            newName = whenName + "_" + count++;
        }

        return newName;
    }

    /**
     * Gets list of child sql objects.
     * 
     * @return List of child SQLObjects
     */
    public List getChildSQLObjects() {
        return getWhenList();
    }

    /**
     * Gets GUI-related attributes for this instance in the form of a GuiInfo instance.
     * 
     * @return associated GuiInfo instance
     * @see GUIInfo
     */
    public GUIInfo getGUIInfo() {
        return this.guiInfo;
    }

    public int getJdbcType() {
        // Use VARCHAR as the "widest" possible default value for JDBC type
        return Types.VARCHAR;
    }

    /**
     * Gets a specific SQLWhen by name.
     * 
     * @param whenName of the SQLWhen object to return.
     * @return SQLWhen instance with the given name.
     */
    public SQLWhen getWhen(String whenName) {
        if (this.whenList == null) {
            return (null);
        }

        for (int i = 0; i < whenList.size(); i++) {
            SQLWhen when = (SQLWhen) whenList.get(i);
            if (when.getDisplayName().equals(whenName)) {
                return when;
            }
        }

        return null;
    }

    /**
     * getWhenCount returns the size of the when list.
     * 
     * @return int the size of the list.
     */
    public int getWhenCount() {
        return this.whenList.size();
    }

    public List getWhenList() {
        return this.whenList;
    }

    /**
     * Gets the hashCode for this object.
     * 
     * @return int hash code value.
     */
    public int hashCode() {
        return super.hashCode();
    }

    public boolean isInputValid(String argName, SQLObject input) {
        if (input == null) {
            return false;
        }

        switch (input.getObjectType()) {
            case SQLConstants.VISIBLE_LITERAL:
            case SQLConstants.LITERAL:
            case SQLConstants.SOURCE_COLUMN:
            case SQLConstants.GENERIC_OPERATOR:
            case SQLConstants.CUSTOM_OPERATOR:            	
            case SQLConstants.CAST_OPERATOR:
            case SQLConstants.DATE_DIFF_OPERATOR:
            case SQLConstants.DATE_ADD_OPERATOR:
                return DEFAULT.equals(argName);

            default:
                return false;
        }
    }

    /**
     * Parses the XML element data and populates a new SQLCase object.
     * 
     * @param xmlElement which represents SQLCase
     * @throws EDMException when the xmlElement is null.
     */
    public void parseXML(Element xmlElement) throws EDMException {
        super.parseXML(xmlElement);

        NodeList list = xmlElement.getChildNodes();
        TagParserUtility.parseInputChildNodes(this, list);
        parseSQLWhens(list);

        // There may be multiple guiInfo descendants - we're only interested in the
        // instance
        // associated with this case operator.
        NodeList guiInfoList = xmlElement.getElementsByTagName(GUIInfo.TAG_GUIINFO);
        if (guiInfoList != null && guiInfoList.getLength() != 0) {
            for (int i = 0; i < guiInfoList.getLength(); i++) {
                Element elem = (Element) guiInfoList.item(i);
                if (elem.getParentNode() == xmlElement) {
                    guiInfo = new GUIInfo(elem);
                    break;
                }
            }
        }
    }

    /**
     * Removes an SQLWhen instance from the when list.
     * 
     * @param when SQLWhen instance to be removed.
     * @return boolean true when removed.
     * @throws EDMException when the input is null.
     */
    public boolean removeSQLWhen(SQLWhen when) throws EDMException {
        if (when == null) {
            throw new EDMException(NbBundle.getMessage(SQLCaseOperatorImpl.class, "ERROR_Cannot_remove"));
        }
        this.whenList.remove(when);

        return true;
    }

    /**
     * Second parse. Called if not found in first pass.
     * 
     * @param element to be parsed
     * @exception EDMException thrown while secondparsing
     */
    public void secondPassParse(Element element) throws EDMException {
        TagParserUtility.parseInputTag(this, element);
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        try {
            return GeneratorUtil.getInstance().getEvaluatedString(this);
        } catch (EDMException ignore) {
            return "Unknown";
        }
    }

    /**
     * Generates this SQLCase object as an XML String and returns it
     * 
     * @param prefix is the prefix to use for the XML string.
     * @return String in XML of the object.
     * @throws EDMException if error occurs during generation
     */
    public String toXMLString(String prefix) throws EDMException {
        StringBuilder buffer = new StringBuilder();

        buffer.append(prefix).append(getHeader());
        buffer.append(toXMLAttributeTags(prefix));
        buffer.append(TagParserUtility.toXMLInputTag(prefix + "\t", this.inputMap));
        buffer.append(TagParserUtility.toXMLInputTag(prefix + "\t", this.whenList));
        buffer.append(this.guiInfo.toXMLString(prefix + "\t"));
        buffer.append(prefix + super.getFooter());

        return buffer.toString();
    }

    public void visit(SQLVisitor visitor) {
        visitor.visit(this);
    }

    private boolean isWhenExist(String whenName) {
        Iterator it = this.whenList.iterator();
        while (it.hasNext()) {
            SQLWhen when = (SQLWhen) it.next();
            if (when.getDisplayName().equals(whenName)) {
                return true;
            }
        }

        return false;
    }

    private void parseSQLWhens(NodeList list) throws EDMException {
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeType() == Node.ELEMENT_NODE && list.item(i).getNodeName().equals(SQLObject.TAG_SQLOBJECT)) {
                Element elem = (Element) list.item(i);
                String objType = elem.getAttribute(SQLObject.OBJECT_TYPE);
                int obType = TagParserUtility.getIntType(objType);
                if (obType == SQLConstants.WHEN) {
                    SQLWhen obj = new SQLWhenImpl();
                    obj.setParentObject(this);
                    obj.parseXML(elem);
                    this.addSQLWhen(obj);
                }
            }
        }
    }
}
