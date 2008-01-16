/*
 *
 *          Copyright (c) 2005, SeeBeyond Technology Corporation,
 *          All Rights Reserved
 *
 *          This program, and all the routines referenced herein,
 *          are the proprietary properties and trade secrets of
 *          SEEBEYOND TECHNOLOGY CORPORATION.
 *
 *          Except as provided for by license agreement, this
 *          program shall not be duplicated, used, or disclosed
 *          without  written consent signed by an officer of
 *          SEEBEYOND TECHNOLOGY CORPORATION.
 *
 */
package org.netbeans.modules.mashup.db.ui.model;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openide.util.NbBundle;

/**
 * Concrete class to expose fixedwidth-specific flatfile table properties. TODO Extend
 * this class to expose setters for read-write property sheets
 * (MutableFixedWidthFlatfileBeanInfo?)
 * 
 * @author Jonathan Giron
 * @version $Revision$
 */
public class FixedWidthFlatfileBeanInfo extends FlatfileTableBeanInfo {

    private static BeanDescriptor beanDescriptor = null;

    private static PropertyDescriptor[] properties = null;

    /**
     * Gets the bean's <code>BeanDescriptor</code>s.
     * 
     * @return BeanDescriptor describing the editable properties of this bean. May return
     *         null if the information should be obtained by automatic analysis.
     */
    public BeanDescriptor getBeanDescriptor() {
        if (beanDescriptor == null) {
            beanDescriptor = new BeanDescriptor(FixedWidthFlatfile.class);
        }

        return beanDescriptor;
    }

    /**
     * Gets the bean's <code>PropertyDescriptor</code>s.
     * 
     * @return An array of PropertyDescriptors describing the editable properties
     *         supported by this bean. May return null if the information should be
     *         obtained by automatic analysis.
     *         <p>
     *         If a property is indexed, then its entry in the result array will belong to
     *         the IndexedPropertyDescriptor subclass of PropertyDescriptor. A client of
     *         getPropertyDescriptors can use "instanceof" to check if a given
     *         PropertyDescriptor is an IndexedPropertyDescriptor.
     */
    public PropertyDescriptor[] getPropertyDescriptors() {
        if (properties == null) {
            List myProps = new ArrayList(Arrays.asList(super.getPropertyDescriptors()));

            try {
                // This is a derived property and should not be writable
                PropertyDescriptor pd = new PropertyDescriptor("recordLength", FixedWidthFlatfile.class, "getRecordLength", null); // NOI18N
                String label = NbBundle.getMessage(FixedWidthFlatfileBeanInfo.class, "LBL_record_length"); // NOI18N
                pd.setDisplayName(label);
                myProps.add(pd);
            } catch (IntrospectionException ignore) {
            }

            try {
                PropertyDescriptor pd = new PropertyDescriptor("headerBytesOffset", FixedWidthFlatfile.class, "getHeaderBytesOffset", null); // NOI18N
                String label = NbBundle.getMessage(FixedWidthFlatfileBeanInfo.class, "LBL_header_offset"); // NOI18N
                pd.setDisplayName(label);
                myProps.add(pd);
            } catch (IntrospectionException ignore) {
            }

            properties = (PropertyDescriptor[]) myProps.toArray(new PropertyDescriptor[myProps.size()]);
        }

        return properties;
    }
}