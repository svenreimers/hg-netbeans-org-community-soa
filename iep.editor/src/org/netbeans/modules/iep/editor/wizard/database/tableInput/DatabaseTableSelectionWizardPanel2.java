/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.modules.iep.editor.wizard.database.tableInput;


import java.awt.Component;
import java.sql.Connection;
import java.util.List;
import javax.swing.event.ChangeListener;

import org.netbeans.modules.iep.editor.wizard.database.ColumnInfo;
import org.netbeans.modules.iep.editor.wizard.database.DatabaseTableWizardConstants;
import org.netbeans.modules.iep.editor.wizard.database.TableInfo;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class DatabaseTableSelectionWizardPanel2 implements WizardDescriptor.Panel {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private DatabaseTableSelectionVisualPanel2 component;

    private boolean mAddWhereClausePanel = true;
    
    public DatabaseTableSelectionWizardPanel2() {
    }
    
    public DatabaseTableSelectionWizardPanel2(boolean addWhereClausePanel) {
        this.mAddWhereClausePanel = addWhereClausePanel;
    }
    
    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    public Component getComponent() {
        if (component == null) {
            component = new DatabaseTableSelectionVisualPanel2();
        }
        return component;
    }

    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
    // If you have context help:
    // return new HelpCtx(SampleWizardPanel1.class);
    }

    public boolean isValid() {
        // If it is always OK to press Next or Finish, then:
        return true;
    // If it depends on some condition (form filled out...), then:
    // return someCondition();
    // and when this condition changes (last form field filled in...) then:
    // fireChangeEvent();
    // and uncomment the complicated stuff below.
    }

    public final void addChangeListener(ChangeListener l) {
    }

    public final void removeChangeListener(ChangeListener l) {
    }
    /*
    private final Set<ChangeListener> listeners = new HashSet<ChangeListener>(1); // or can use ChangeSupport in NB 6.0
    public final void addChangeListener(ChangeListener l) {
    synchronized (listeners) {
    listeners.add(l);
    }
    }
    public final void removeChangeListener(ChangeListener l) {
    synchronized (listeners) {
    listeners.remove(l);
    }
    }
    protected final void fireChangeEvent() {
    Iterator<ChangeListener> it;
    synchronized (listeners) {
    it = new HashSet<ChangeListener>(listeners).iterator();
    }
    ChangeEvent ev = new ChangeEvent(this);
    while (it.hasNext()) {
    it.next().stateChanged(ev);
    }
    }
     */

    // You can use a settings object to keep track of state. Normally the
    // settings object will be the WizardDescriptor, so you can use
    // WizardDescriptor.getProperty & putProperty to store information entered
    // by the user.
    public void readSettings(Object settings) {
        WizardDescriptor wiz = (WizardDescriptor) settings;
        Connection connection = (Connection) wiz.getProperty(DatabaseTableWizardConstants.PROP_SELECTED_DB_CONNECTION);
        List<TableInfo> selectedTables = (List<TableInfo>) wiz.getProperty(DatabaseTableWizardConstants.PROP_SELECTED_TABLES);
        if(connection != null && selectedTables != null) {
        	component.setSelectedTables(selectedTables);
        }
    }

    public void storeSettings(Object settings) {
    	WizardDescriptor wiz = (WizardDescriptor) settings;
    	List<ColumnInfo> selectedColumns = component.getSelectedColumns();
     String jndiName = component.getJNDIName();
         
    	wiz.putProperty(DatabaseTableWizardConstants.PROP_SELECTED_COLUMNS, selectedColumns);
        wiz.putProperty(DatabaseTableWizardConstants.PROP_JNDI_NAME, jndiName);
    }
}

