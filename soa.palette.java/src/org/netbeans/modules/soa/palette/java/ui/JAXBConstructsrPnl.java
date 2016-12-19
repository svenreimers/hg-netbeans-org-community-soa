/*
 * OTDMarshalerPnl.java
 *
 * Created on December 10, 2007, 2:48 PM
 */

package org.netbeans.modules.soa.palette.java.ui;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.TypeElement;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.api.java.source.ClassIndex.NameKind;
import org.netbeans.api.java.source.ClassIndex.SearchScope;
import org.netbeans.api.java.source.ClasspathInfo;
import org.netbeans.api.java.source.ElementHandle;
import org.netbeans.api.java.source.ui.TypeElementFinder;
import org.netbeans.api.project.Project;
import org.netbeans.modules.soa.palette.java.util.JavaSourceUtil;
import org.netbeans.modules.soa.palette.java.util.ProjectHelper;
import org.netbeans.modules.soa.palette.java.util.OTDImportConstants;
import org.netbeans.spi.java.classpath.support.ClassPathSupport;
import org.openide.DialogDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.util.NbBundle;

/**
 *
 * @author  gpatil
 */
public class JAXBConstructsrPnl extends JPanel implements DocumentListener {
    private static final ClassPath EMPTY_PATH = ClassPathSupport.createClassPath(new URL[0]);
    private static File LAST_BROWSED_DIRS = null;
    
    private File fileSelected = null;
    private Project prj;
    private FileObject fo;
    private JTextComponent doc;
    private List<String> methodNames;
    private DialogDescriptor dd;

    public JAXBConstructsrPnl(Project project, JTextComponent doc) {
        this.prj = project;
        this.doc = doc;
        initComponents();
        custInit();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jlGenerateOTDCode = new javax.swing.JLabel();
        jlOtdClass = new javax.swing.JLabel();
        jlMarshalTo = new javax.swing.JLabel();
        jlMethodName = new javax.swing.JLabel();
        txtMethodName = new javax.swing.JTextField();
        txtClassName = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        txtSampleFilePath = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jlGenerateOTDCode.setText(org.openide.util.NbBundle.getMessage(JAXBConstructsrPnl.class, "LBL_GenJAXBConstructsCode")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jlGenerateOTDCode, gridBagConstraints);

        jlOtdClass.setLabelFor(txtClassName);
        org.openide.awt.Mnemonics.setLocalizedText(jlOtdClass, org.openide.util.NbBundle.getMessage(JAXBConstructsrPnl.class, "LBL_JAXBClass")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jlOtdClass, gridBagConstraints);

        jlMarshalTo.setLabelFor(txtSampleFilePath);
        org.openide.awt.Mnemonics.setLocalizedText(jlMarshalTo, org.openide.util.NbBundle.getMessage(JAXBConstructsrPnl.class, "LBL_JAXBSampleFile")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jlMarshalTo, gridBagConstraints);

        jlMethodName.setLabelFor(txtMethodName);
        org.openide.awt.Mnemonics.setLocalizedText(jlMethodName, org.openide.util.NbBundle.getMessage(JAXBConstructsrPnl.class, "LBL_MethodName")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jlMethodName, gridBagConstraints);

        txtMethodName.setPreferredSize(new java.awt.Dimension(200, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtMethodName, gridBagConstraints);
        txtMethodName.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JAXBConstructsrPnl.class, "ASCD_MethodName")); // NOI18N

        txtClassName.setPreferredSize(new java.awt.Dimension(200, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtClassName, gridBagConstraints);
        txtClassName.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JAXBConstructsrPnl.class, "ACSD_JAXBClass")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnFind, org.openide.util.NbBundle.getMessage(JAXBConstructsrPnl.class, "LBL_BTN_FindClass")); // NOI18N
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findJAXBClass(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(btnFind, gridBagConstraints);
        btnFind.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JAXBConstructsrPnl.class, "ACSD_FindJAXBClass")); // NOI18N

        txtSampleFilePath.setPreferredSize(new java.awt.Dimension(300, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(txtSampleFilePath, gridBagConstraints);
        txtSampleFilePath.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JAXBConstructsrPnl.class, "ACSD_SampleXMLFile")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnBrowse, org.openide.util.NbBundle.getMessage(JAXBConstructsrPnl.class, "LBL_Browse")); // NOI18N
        btnBrowse.setActionCommand("Browse"); // NOI18N
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectSampleXML(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(btnBrowse, gridBagConstraints);
        btnBrowse.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JAXBConstructsrPnl.class, "ACSD_BrowseSampleXml")); // NOI18N

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setMaximumSize(new java.awt.Dimension(0, 14));
        lblError.setMinimumSize(new java.awt.Dimension(0, 14));
        lblError.setPreferredSize(new java.awt.Dimension(300, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblError, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void findJAXBClass(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findJAXBClass
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ClasspathInfo cpInfo = ClasspathInfo.create(
                    EMPTY_PATH, // boot classpath
                    ClassPath.getClassPath(fo, ClassPath.COMPILE), // classpath from dependent projects and libraries
                    ClassPath.getClassPath(fo, ClassPath.SOURCE)); // source classpath
                
                final ElementHandle<TypeElement> handle = TypeElementFinder.find(cpInfo, new TypeElementFinder.Customizer() {
                    
                    public Set<ElementHandle<TypeElement>> query(ClasspathInfo classpathInfo, String textForQuery, NameKind nameKind, Set<SearchScope> searchScopes) {
                        return classpathInfo.getClassIndex().getDeclaredTypes(textForQuery, nameKind, searchScopes);
                    }

                    public boolean accept(ElementHandle<TypeElement> typeHandle) {
                        return true;
                    }
                });
                
                if (handle != null) {
                    txtClassName.setText(handle.getQualifiedName());
                    //setSelectedItem(combo, handle.getQualifiedName());
                }
            }
        });
    }//GEN-LAST:event_findJAXBClass

    private void selectSampleXML(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectSampleXML
        this.selectFileFromFileSystem();
    }//GEN-LAST:event_selectSampleXML
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnFind;
    private javax.swing.JLabel jlGenerateOTDCode;
    private javax.swing.JLabel jlMarshalTo;
    private javax.swing.JLabel jlMethodName;
    private javax.swing.JLabel jlOtdClass;
    private javax.swing.JLabel lblError;
    private javax.swing.JTextField txtClassName;
    private javax.swing.JTextField txtMethodName;
    private javax.swing.JTextField txtSampleFilePath;
    // End of variables declaration//GEN-END:variables
 
    private void custInit(){
        this.lblError.getAccessibleContext().setAccessibleDescription(getMsg("ACSN_ErrorMessage"));//NOI18N
        //Mnemonics.setLocalizedText(rImportOnce, getMsg("LBL_OnAdd")); // NOI18N
        
        this.fo = ProjectHelper.getFileObject(doc);
        this.methodNames = JavaSourceUtil.getMethodNames(doc);
        String methName = JavaSourceUtil.recommendConstructsMethodName(methodNames,true);
        this.txtMethodName.setText(methName);
        this.txtMethodName.getDocument().addDocumentListener(this);
        this.txtClassName.getDocument().addDocumentListener(this);
        this.txtSampleFilePath.getDocument().addDocumentListener(this);
        
    }
    private String getMsg(String key){
        return NbBundle.getMessage(getClass(), key);
    }
    
    public void setDD(DialogDescriptor dd){
        this.dd = dd;
        validateFields();
    }
    
    private static synchronized File getLastBrowsedDir(){
        return LAST_BROWSED_DIRS;
    }

    private static synchronized void setLastBrowsedDir(File dir){
        LAST_BROWSED_DIRS = dir;
    }
    
    protected File selectFileFromFileSystem(){
        File file = null;
        File lastBrowsed = getLastBrowsedDir();
        JFileChooser jfc = new JFileChooser();
        if (lastBrowsed != null){
            jfc.setCurrentDirectory(lastBrowsed);
        }

        jfc.setMultiSelectionEnabled(false);
        jfc.setFileSelectionMode( JFileChooser.FILES_ONLY  );
        int iRt = jfc.showOpenDialog(this);
        if ( iRt == JFileChooser.APPROVE_OPTION ) {
            file = jfc.getSelectedFile();
            this.fileSelected = file;
            if (this.fileSelected != null){
                this.txtSampleFilePath.setText(this.fileSelected.getAbsolutePath());
            }
        }
        
        File currDir = jfc.getCurrentDirectory();
        if (currDir != null){
            setLastBrowsedDir(currDir);
        }

        return file;
    }
    
    public Map<String, Object> getData(){
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(OTDImportConstants.MAP_KEY_SAMPLE_XML, this.fileSelected);
        data.put(OTDImportConstants.MAP_KEY_QUAL_CLASS_NAME, this.txtClassName.getText());
        data.put(OTDImportConstants.MAP_KEY_METHOD_NAME, this.txtMethodName.getText());
        data.put(OTDImportConstants.MAP_KEY_CLASSLOADER, ProjectHelper.getClassLoader(prj));
        return data;
    }
    
    private void validateFields(){
        String txt = this.txtMethodName.getText();
        String errorMsg = "" ; //NOI18N
        boolean error = false;
        //Method Name
        if (this.methodNames.contains(txt)){
            errorMsg = NbBundle.getMessage(this.getClass(), 
                    "LBL_METHOD_EXISTS", txt);//NOI18N
            error = true;    
        }
        
        if (!error){
            if ((txt == null) || (txt.trim().length() == 0)){
                errorMsg = NbBundle.getMessage(this.getClass(), 
                        "LBL_ENTER_METHOD");//NOI18N
                error = true;                    
            }
        }

        // Class name
        if (!error){
            txt = this.txtClassName.getText();
            if ((txt == null) || (txt.trim().length() == 0)){
                errorMsg = NbBundle.getMessage(this.getClass(), 
                        "LBL_ENTER_CLASS_NAME");//NOI18N
                error = true;                    
            }
        }
/*        
        if (!error){
            txt = this.txtSampleFilePath.getText();
            if ((txt == null) || (txt.trim().length() == 0)){
                errorMsg = NbBundle.getMessage(this.getClass(), 
                        "LBL_SELECT_SAMPLE_FILE");//NOI18N
                error = true;                    
            }
        }
*/        
        this.lblError.setText(errorMsg);
        if (error){
            this.dd.setValid(false);
        }else{
            this.dd.setValid(true);
        }
    }
    // DocumentListener interface methods -begin
    private void validateDocListenerObjects(DocumentEvent evt){
        if (this.txtMethodName.getDocument() == evt.getDocument()) {
            validateFields();
        } else if (this.txtClassName.getDocument() == evt.getDocument()) {
            validateFields();
        } else if (this.txtSampleFilePath.getDocument() == evt.getDocument()) {
            validateFields();
        }        
    }
    
    public void insertUpdate(DocumentEvent evt) {
        validateDocListenerObjects(evt);
    }

    public void removeUpdate(DocumentEvent evt) {
        validateDocListenerObjects(evt);
    }

    public void changedUpdate(DocumentEvent evt) {
        validateDocListenerObjects(evt);    
    }
    // DocumentListener interface methods -begin        
    //
}