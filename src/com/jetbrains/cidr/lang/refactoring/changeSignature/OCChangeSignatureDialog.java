// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import java.awt.Container;
import javax.swing.SwingUtilities;
import com.intellij.util.Alarm;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.ui.table.JBTable;
import javax.swing.Action;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.intellij.refactoring.changeSignature.ParameterTableModelBase;
import java.awt.event.MouseEvent;
import com.intellij.util.ui.table.JBTableRow;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.IJSwingUtilities;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.UIUtil;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.openapi.ui.VerticalFlowLayout;
import java.util.ArrayList;
import com.intellij.ui.EditorTextField;
import java.util.List;
import com.intellij.util.ui.table.JBTableRowEditor;
import javax.swing.JTable;
import com.jetbrains.cidr.lang.editor.colors.OCHighlightingKeys;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import java.awt.Font;
import java.awt.Toolkit;
import com.intellij.openapi.editor.colors.EditorFontType;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import java.util.Iterator;
import com.intellij.openapi.application.WriteAction;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.util.ArrayUtil;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.intellij.refactoring.ui.ComboBoxVisibilityPanel;
import com.intellij.refactoring.ui.VisibilityPanelBase;
import org.jetbrains.annotations.Nullable;
import com.intellij.refactoring.changeSignature.MethodDescriptor;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.refactoring.changeSignature.CallerChooserBase;
import java.util.Set;
import com.intellij.util.Consumer;
import com.intellij.ui.treeStructure.Tree;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.PsiCodeFragment;
import com.intellij.refactoring.BaseRefactoringProcessor;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.OCFileType;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import com.intellij.util.ui.JBUI;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import javax.swing.JPanel;
import com.jetbrains.cidr.lang.ui.OCTypeReferenceEditor;
import com.intellij.refactoring.changeSignature.ParameterTableModelItemBase;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.refactoring.changeSignature.ChangeSignatureDialogBase;

public class OCChangeSignatureDialog extends ChangeSignatureDialogBase<OCParameterInfo, OCCallable, Object, OCMethodDescriptor, ParameterTableModelItemBase<OCParameterInfo>, OCParameterTableModel>
{
    private DialogChangeSignatureHandlerImpl myHandler;
    private OCTypeReferenceEditor myContainerClassField;
    private JPanel myContainerPanel;
    private boolean myChangeParentClassPossible;
    private String myHelpId;
    private static final Key<Integer> DECLARATION_PLACE_KEY;
    
    public OCChangeSignatureDialog(final Project project, final OCMethodDescriptor ocMethodDescriptor, final PsiElement psiElement) {
        super(project, ocMethodDescriptor, false, psiElement);
        this.initHandler();
        this.myNameField.setText(ocMethodDescriptor.getName());
    }
    
    protected void initHandler() {
        try {
            if (this.myHandler == null) {
                this.myHandler = new DialogChangeSignatureHandlerImpl();
            }
        }
        catch (UnsupportedOperationException ex) {
            throw c(ex);
        }
    }
    
    public void setHelpId(final String myHelpId) {
        this.myHelpId = myHelpId;
    }
    
    @Override
    protected String getHelpId() {
        try {
            if (this.myHelpId == null) {
                return "procedures.refactoring.changeMethodSignature";
            }
        }
        catch (UnsupportedOperationException ex) {
            throw c(ex);
        }
        return this.myHelpId;
    }
    
    @Override
    protected JComponent createNorthPanel() {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, 17, 2, (Insets)JBUI.emptyInsets(), 0, 0);
        final OCClassDeclaration containerClass = ((OCMethodDescriptor)this.myMethod).getContainerClass();
        OCClassSymbol symbol = null;
        Label_0067: {
            try {
                if (containerClass != null) {
                    symbol = containerClass.getSymbol();
                    break Label_0067;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw c(ex);
            }
            symbol = null;
        }
        (this.myContainerClassField = OCTypeReferenceEditor.create(symbol, (Condition<OCSymbol>)(ocSymbol -> ocSymbol instanceof OCImplementationSymbol), (PsiElement)((OCMethodDescriptor)this.myMethod).getMethod(), true, this.myProject)).addDocumentListener((DocumentListener)this.mySignatureUpdater);
        this.myContainerClassField.getEditorTextField().setPreferredWidth(200);
        this.myContainerPanel = new JPanel(new BorderLayout(0, 2));
        final JLabel label = new JLabel();
        label.setText("Containing Class:");
        label.setLabelFor((Component)this.myContainerClassField);
        panel.add(super.createNorthPanel(), gridBagConstraints);
        this.myContainerPanel.add(label, "North");
        this.myContainerPanel.add((Component)this.myContainerClassField, "South");
        final OCSymbol methodSymbol = this.myHandler.getMethodDescriptor().getMethodSymbol();
        Label_0233: {
            try {
                if (!(methodSymbol instanceof OCFunctionSymbol)) {
                    break Label_0233;
                }
                final OCSymbol ocSymbol = methodSymbol;
                final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                final boolean b = ocFunctionSymbol.isCppOperator();
                if (b) {
                    break Label_0233;
                }
                break Label_0233;
            }
            catch (UnsupportedOperationException ex2) {
                throw c(ex2);
            }
            try {
                final OCSymbol ocSymbol = methodSymbol;
                final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                final boolean b = ocFunctionSymbol.isCppOperator();
                if (b) {
                    this.myNameField.setEnabled(false);
                }
            }
            catch (UnsupportedOperationException ex3) {
                throw c(ex3);
            }
        }
        gridBagConstraints.insets.left = 8;
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridx;
        panel.add(this.myContainerPanel, gridBagConstraints);
        this.getRefactorAction().setEnabled(false);
        this.validateButtons();
        return panel;
    }
    
    @Override
    protected JPanel createParametersPanel(final boolean b) {
        final JPanel parametersPanel = super.createParametersPanel(b);
        this.myPropagateParamChangesButton.setVisible(false);
        return parametersPanel;
    }
    
    @Override
    protected LanguageFileType getFileType() {
        return OCFileType.INSTANCE;
    }
    
    @Override
    public JComponent getPreferredFocusedComponent() {
        Label_0027: {
            try {
                if (!this.getMethodName().isEmpty()) {
                    return super.getPreferredFocusedComponent();
                }
                final OCChangeSignatureDialog ocChangeSignatureDialog = this;
                final DialogChangeSignatureHandlerImpl dialogChangeSignatureHandlerImpl = ocChangeSignatureDialog.myHandler;
                final boolean b = dialogChangeSignatureHandlerImpl.isMethod();
                if (!b) {
                    break Label_0027;
                }
                return super.getPreferredFocusedComponent();
            }
            catch (UnsupportedOperationException ex) {
                throw c(ex);
            }
            try {
                final OCChangeSignatureDialog ocChangeSignatureDialog = this;
                final DialogChangeSignatureHandlerImpl dialogChangeSignatureHandlerImpl = ocChangeSignatureDialog.myHandler;
                final boolean b = dialogChangeSignatureHandlerImpl.isMethod();
                if (!b) {
                    return (JComponent)this.myNameField;
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw c(ex2);
            }
        }
        return super.getPreferredFocusedComponent();
    }
    
    @Override
    protected OCParameterTableModel createParametersInfoModel(final OCMethodDescriptor ocMethodDescriptor) {
        return new OCParameterTableModel(this.myDefaultValueContext, ((OCMethodDescriptor)this.myMethod).getMethod() instanceof OCMethod);
    }
    
    @Override
    protected BaseRefactoringProcessor createRefactoringProcessor() {
        return this.myHandler.createRefactoringProcessor();
    }
    
    @Override
    protected PsiCodeFragment createReturnTypeCodeFragment() {
        return (PsiCodeFragment)OCElementFactory.getTypeCodeFragmentInWriteAction(((OCMethodDescriptor)this.myMethod).getReturnTypeText(this.myDefaultValueContext), this.myProject, this.myDefaultValueContext);
    }
    
    @Override
    protected CallerChooserBase<OCCallable> createCallerChooser(final String s, final Tree tree, final Consumer<Set<OCCallable>> consumer) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected void updateSignatureAlarmFired() {
        super.updateSignatureAlarmFired();
        this.validateButtons();
    }
    
    @Override
    protected String getMethodName() {
        try {
            if (this.myHandler.isMethod()) {
                return this.myHandler.calculateMethodName();
            }
        }
        catch (UnsupportedOperationException ex) {
            throw c(ex);
        }
        return super.getMethodName();
    }
    
    @Override
    protected void canRun() throws ConfigurationException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myNameField:Lcom/intellij/ui/EditorTextField;
        //     4: ifnonnull       12
        //     7: return         
        //     8: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    11: athrow         
        //    12: aload_0        
        //    13: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myVisibilityPanel:Lcom/intellij/refactoring/ui/VisibilityPanelBase;
        //    16: instanceof      Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCTargetSymbolPanel;
        //    19: ifeq            86
        //    22: aload_0        
        //    23: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myVisibilityPanel:Lcom/intellij/refactoring/ui/VisibilityPanelBase;
        //    26: invokevirtual   com/intellij/refactoring/ui/VisibilityPanelBase.isVisible:()Z
        //    29: ifeq            86
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    38: athrow         
        //    39: aload_0        
        //    40: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myVisibilityPanel:Lcom/intellij/refactoring/ui/VisibilityPanelBase;
        //    43: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCTargetSymbolPanel;
        //    46: astore_1       
        //    47: aload_1        
        //    48: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCTargetSymbolPanel.isOnlyNewCategoryAllowed:()Z
        //    51: ifeq            86
        //    54: aload_1        
        //    55: aload_0        
        //    56: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //    59: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.getGeneratedInfo:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo;
        //    62: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCTargetSymbolPanel.createNewCategory:(Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo;)Z
        //    65: ifne            86
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    74: athrow         
        //    75: aload_0        
        //    76: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.doCancelAction:()V
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    85: athrow         
        //    86: aload_0        
        //    87: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.initHandler:()V
        //    90: aload_0        
        //    91: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //    94: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.getCallableKind:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //    97: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind.toString:()Ljava/lang/String;
        //   100: invokestatic    com/intellij/openapi/util/text/StringUtil.decapitalize:(Ljava/lang/String;)Ljava/lang/String;
        //   103: astore_1       
        //   104: aload_0        
        //   105: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myNamePanel:Ljavax/swing/JPanel;
        //   108: invokevirtual   javax/swing/JPanel.isVisible:()Z
        //   111: ifeq            190
        //   114: aload_0        
        //   115: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myNameField:Lcom/intellij/ui/EditorTextField;
        //   118: invokevirtual   com/intellij/ui/EditorTextField.isEnabled:()Z
        //   121: ifeq            190
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   130: athrow         
        //   131: aload_0        
        //   132: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myNameField:Lcom/intellij/ui/EditorTextField;
        //   135: invokevirtual   com/intellij/ui/EditorTextField.getText:()Ljava/lang/String;
        //   138: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   141: invokestatic    com/intellij/openapi/util/text/StringUtil.isJavaIdentifier:(Ljava/lang/String;)Z
        //   144: ifne            190
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   153: athrow         
        //   154: new             Lcom/intellij/openapi/options/ConfigurationException;
        //   157: dup            
        //   158: new             Ljava/lang/StringBuilder;
        //   161: dup            
        //   162: invokespecial   java/lang/StringBuilder.<init>:()V
        //   165: ldc             "Name of "
        //   167: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: aload_1        
        //   171: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   174: ldc             " is invalid"
        //   176: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   179: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   182: invokespecial   com/intellij/openapi/options/ConfigurationException.<init>:(Ljava/lang/String;)V
        //   185: athrow         
        //   186: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   189: athrow         
        //   190: aload_0        
        //   191: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //   194: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.isFunction:()Z
        //   197: ifne            217
        //   200: aload_0        
        //   201: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //   204: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.isMethod:()Z
        //   207: ifeq            293
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   216: athrow         
        //   217: aload_0        
        //   218: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myMethod:Lcom/intellij/refactoring/changeSignature/MethodDescriptor;
        //   221: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //   224: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.canChangeReturnType:()Lcom/intellij/refactoring/changeSignature/MethodDescriptor$ReadWriteOption;
        //   227: getstatic       com/intellij/refactoring/changeSignature/MethodDescriptor$ReadWriteOption.ReadWrite:Lcom/intellij/refactoring/changeSignature/MethodDescriptor$ReadWriteOption;
        //   230: if_acmpne       293
        //   233: goto            240
        //   236: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   239: athrow         
        //   240: aload_0        
        //   241: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myReturnTypeCodeFragment:Lcom/intellij/psi/PsiCodeFragment;
        //   244: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getType:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   247: ifnonnull       293
        //   250: goto            257
        //   253: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   256: athrow         
        //   257: new             Lcom/intellij/openapi/options/ConfigurationException;
        //   260: dup            
        //   261: new             Ljava/lang/StringBuilder;
        //   264: dup            
        //   265: invokespecial   java/lang/StringBuilder.<init>:()V
        //   268: ldc             "Return type of "
        //   270: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   273: aload_1        
        //   274: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   277: ldc             " is invalid"
        //   279: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   282: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   285: invokespecial   com/intellij/openapi/options/ConfigurationException.<init>:(Ljava/lang/String;)V
        //   288: athrow         
        //   289: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   292: athrow         
        //   293: aload_0        
        //   294: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //   297: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.isMethod:()Z
        //   300: ifeq            354
        //   303: aload_0        
        //   304: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myParametersTableModel:Lcom/intellij/refactoring/changeSignature/ParameterTableModelBase;
        //   307: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterTableModel;
        //   310: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterTableModel.getRowCount:()I
        //   313: ifne            354
        //   316: goto            323
        //   319: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   322: athrow         
        //   323: new             Lcom/intellij/openapi/options/ConfigurationException;
        //   326: dup            
        //   327: new             Ljava/lang/StringBuilder;
        //   330: dup            
        //   331: invokespecial   java/lang/StringBuilder.<init>:()V
        //   334: ldc             "No selector parts for "
        //   336: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   339: aload_1        
        //   340: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   343: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   346: invokespecial   com/intellij/openapi/options/ConfigurationException.<init>:(Ljava/lang/String;)V
        //   349: athrow         
        //   350: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   353: athrow         
        //   354: aload_0        
        //   355: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myContainerPanel:Ljavax/swing/JPanel;
        //   358: invokevirtual   javax/swing/JPanel.isVisible:()Z
        //   361: ifeq            489
        //   364: aload_0        
        //   365: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myContainerClassField:Lcom/jetbrains/cidr/lang/ui/OCTypeReferenceEditor;
        //   368: aload_0        
        //   369: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myProject:Lcom/intellij/openapi/project/Project;
        //   372: invokevirtual   com/jetbrains/cidr/lang/ui/OCTypeReferenceEditor.getClassDeclaration:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   375: astore_2       
        //   376: aload_2        
        //   377: ifnonnull       422
        //   380: new             Lcom/intellij/openapi/options/ConfigurationException;
        //   383: dup            
        //   384: new             Ljava/lang/StringBuilder;
        //   387: dup            
        //   388: invokespecial   java/lang/StringBuilder.<init>:()V
        //   391: ldc             "Containing class \""
        //   393: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   396: aload_0        
        //   397: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myContainerClassField:Lcom/jetbrains/cidr/lang/ui/OCTypeReferenceEditor;
        //   400: invokevirtual   com/jetbrains/cidr/lang/ui/OCTypeReferenceEditor.getText:()Ljava/lang/String;
        //   403: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   406: ldc             "\" is invalid"
        //   408: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   411: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   414: invokespecial   com/intellij/openapi/options/ConfigurationException.<init>:(Ljava/lang/String;)V
        //   417: athrow         
        //   418: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   421: athrow         
        //   422: aload_2        
        //   423: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   428: astore_3       
        //   429: aload_3        
        //   430: ifnull          489
        //   433: aload_2        
        //   434: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   437: ifne            489
        //   440: goto            447
        //   443: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   446: athrow         
        //   447: new             Lcom/intellij/openapi/options/ConfigurationException;
        //   450: dup            
        //   451: new             Ljava/lang/StringBuilder;
        //   454: dup            
        //   455: invokespecial   java/lang/StringBuilder.<init>:()V
        //   458: ldc             "Containing class \""
        //   460: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   463: aload_0        
        //   464: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myContainerClassField:Lcom/jetbrains/cidr/lang/ui/OCTypeReferenceEditor;
        //   467: invokevirtual   com/jetbrains/cidr/lang/ui/OCTypeReferenceEditor.getText:()Ljava/lang/String;
        //   470: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   473: ldc             "\" is outside of the project"
        //   475: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   478: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   481: invokespecial   com/intellij/openapi/options/ConfigurationException.<init>:(Ljava/lang/String;)V
        //   484: athrow         
        //   485: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   488: athrow         
        //   489: aload_0        
        //   490: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myParametersTableModel:Lcom/intellij/refactoring/changeSignature/ParameterTableModelBase;
        //   493: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterTableModel;
        //   496: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterTableModel.getItems:()Ljava/util/List;
        //   499: astore_2       
        //   500: iconst_0       
        //   501: istore_3       
        //   502: aload_2        
        //   503: invokeinterface java/util/List.size:()I
        //   508: iconst_1       
        //   509: if_icmpne       591
        //   512: aload_0        
        //   513: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //   516: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.isMethod:()Z
        //   519: ifeq            591
        //   522: goto            529
        //   525: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   528: athrow         
        //   529: aload_2        
        //   530: iconst_0       
        //   531: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   536: checkcast       Lcom/intellij/refactoring/changeSignature/ParameterTableModelItemBase;
        //   539: astore          4
        //   541: aload           4
        //   543: getfield        com/intellij/refactoring/changeSignature/ParameterTableModelItemBase.typeCodeFragment:Lcom/intellij/psi/PsiCodeFragment;
        //   546: invokeinterface com/intellij/psi/PsiCodeFragment.getText:()Ljava/lang/String;
        //   551: invokevirtual   java/lang/String.isEmpty:()Z
        //   554: ifeq            589
        //   557: aload           4
        //   559: getfield        com/intellij/refactoring/changeSignature/ParameterTableModelItemBase.parameter:Ljava/lang/Object;
        //   562: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   565: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getName:()Ljava/lang/String;
        //   568: invokevirtual   java/lang/String.isEmpty:()Z
        //   571: ifeq            589
        //   574: goto            581
        //   577: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   580: athrow         
        //   581: iconst_1       
        //   582: goto            590
        //   585: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   588: athrow         
        //   589: iconst_0       
        //   590: istore_3       
        //   591: iconst_1       
        //   592: istore          4
        //   594: aload_2        
        //   595: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   600: astore          5
        //   602: aload           5
        //   604: invokeinterface java/util/Iterator.hasNext:()Z
        //   609: ifeq            930
        //   612: aload           5
        //   614: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   619: checkcast       Lcom/intellij/refactoring/changeSignature/ParameterTableModelItemBase;
        //   622: astore          6
        //   624: aload           6
        //   626: getfield        com/intellij/refactoring/changeSignature/ParameterTableModelItemBase.typeCodeFragment:Lcom/intellij/psi/PsiCodeFragment;
        //   629: invokeinterface com/intellij/psi/PsiCodeFragment.getText:()Ljava/lang/String;
        //   634: ldc             "..."
        //   636: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   639: istore          7
        //   641: aload           6
        //   643: getfield        com/intellij/refactoring/changeSignature/ParameterTableModelItemBase.parameter:Ljava/lang/Object;
        //   646: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   649: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getSelector:()Ljava/lang/String;
        //   652: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   655: astore          8
        //   657: aload           6
        //   659: getfield        com/intellij/refactoring/changeSignature/ParameterTableModelItemBase.parameter:Ljava/lang/Object;
        //   662: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   665: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getName:()Ljava/lang/String;
        //   668: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   671: astore          9
        //   673: aload_0        
        //   674: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //   677: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.isMethod:()Z
        //   680: ifeq            765
        //   683: aload           8
        //   685: invokestatic    com/intellij/openapi/util/text/StringUtil.isJavaIdentifier:(Ljava/lang/String;)Z
        //   688: ifne            765
        //   691: goto            698
        //   694: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   697: athrow         
        //   698: iload           4
        //   700: ifne            725
        //   703: goto            710
        //   706: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   709: athrow         
        //   710: aload           8
        //   712: invokevirtual   java/lang/String.isEmpty:()Z
        //   715: ifne            765
        //   718: goto            725
        //   721: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   724: athrow         
        //   725: new             Lcom/intellij/openapi/options/ConfigurationException;
        //   728: dup            
        //   729: new             Ljava/lang/StringBuilder;
        //   732: dup            
        //   733: invokespecial   java/lang/StringBuilder.<init>:()V
        //   736: ldc             "Selector part '"
        //   738: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   741: aload           8
        //   743: invokestatic    com/intellij/openapi/util/text/StringUtil.escapeXml:(Ljava/lang/String;)Ljava/lang/String;
        //   746: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   749: ldc             "' is invalid"
        //   751: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   754: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   757: invokespecial   com/intellij/openapi/options/ConfigurationException.<init>:(Ljava/lang/String;)V
        //   760: athrow         
        //   761: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   764: athrow         
        //   765: iload_3        
        //   766: ifne            850
        //   769: aload           6
        //   771: getfield        com/intellij/refactoring/changeSignature/ParameterTableModelItemBase.typeCodeFragment:Lcom/intellij/psi/PsiCodeFragment;
        //   774: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getType:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   777: ifnonnull       850
        //   780: goto            787
        //   783: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   786: athrow         
        //   787: iload           7
        //   789: ifne            850
        //   792: goto            799
        //   795: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   798: athrow         
        //   799: new             Lcom/intellij/openapi/options/ConfigurationException;
        //   802: dup            
        //   803: new             Ljava/lang/StringBuilder;
        //   806: dup            
        //   807: invokespecial   java/lang/StringBuilder.<init>:()V
        //   810: ldc             "Parameter type '"
        //   812: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   815: aload           6
        //   817: getfield        com/intellij/refactoring/changeSignature/ParameterTableModelItemBase.typeCodeFragment:Lcom/intellij/psi/PsiCodeFragment;
        //   820: invokeinterface com/intellij/psi/PsiCodeFragment.getText:()Ljava/lang/String;
        //   825: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   828: invokestatic    com/intellij/openapi/util/text/StringUtil.escapeXml:(Ljava/lang/String;)Ljava/lang/String;
        //   831: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   834: ldc             "' is invalid"
        //   836: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   839: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   842: invokespecial   com/intellij/openapi/options/ConfigurationException.<init>:(Ljava/lang/String;)V
        //   845: athrow         
        //   846: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   849: athrow         
        //   850: iload_3        
        //   851: ifne            924
        //   854: aload           9
        //   856: invokestatic    com/intellij/openapi/util/text/StringUtil.isJavaIdentifier:(Ljava/lang/String;)Z
        //   859: ifne            924
        //   862: goto            869
        //   865: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   868: athrow         
        //   869: aload           9
        //   871: invokevirtual   java/lang/String.isEmpty:()Z
        //   874: ifne            924
        //   877: goto            884
        //   880: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   883: athrow         
        //   884: new             Lcom/intellij/openapi/options/ConfigurationException;
        //   887: dup            
        //   888: new             Ljava/lang/StringBuilder;
        //   891: dup            
        //   892: invokespecial   java/lang/StringBuilder.<init>:()V
        //   895: ldc             "Parameter name '"
        //   897: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   900: aload           9
        //   902: invokestatic    com/intellij/openapi/util/text/StringUtil.escapeXml:(Ljava/lang/String;)Ljava/lang/String;
        //   905: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   908: ldc             "' is invalid"
        //   910: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   913: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   916: invokespecial   com/intellij/openapi/options/ConfigurationException.<init>:(Ljava/lang/String;)V
        //   919: athrow         
        //   920: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   923: athrow         
        //   924: iconst_0       
        //   925: istore          4
        //   927: goto            602
        //   930: return         
        //    Exceptions:
        //  throws com.intellij.openapi.options.ConfigurationException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                 
        //  -----  -----  -----  -----  -----------------------------------------------------
        //  0      8      8      12     Lcom/intellij/openapi/options/ConfigurationException;
        //  12     32     35     39     Lcom/intellij/openapi/options/ConfigurationException;
        //  47     68     71     75     Lcom/intellij/openapi/options/ConfigurationException;
        //  54     79     82     86     Lcom/intellij/openapi/options/ConfigurationException;
        //  104    124    127    131    Lcom/intellij/openapi/options/ConfigurationException;
        //  114    147    150    154    Lcom/intellij/openapi/options/ConfigurationException;
        //  131    186    186    190    Lcom/intellij/openapi/options/ConfigurationException;
        //  190    210    213    217    Lcom/intellij/openapi/options/ConfigurationException;
        //  200    233    236    240    Lcom/intellij/openapi/options/ConfigurationException;
        //  217    250    253    257    Lcom/intellij/openapi/options/ConfigurationException;
        //  240    289    289    293    Lcom/intellij/openapi/options/ConfigurationException;
        //  293    316    319    323    Lcom/intellij/openapi/options/ConfigurationException;
        //  303    350    350    354    Lcom/intellij/openapi/options/ConfigurationException;
        //  376    418    418    422    Lcom/intellij/openapi/options/ConfigurationException;
        //  429    440    443    447    Lcom/intellij/openapi/options/ConfigurationException;
        //  433    485    485    489    Lcom/intellij/openapi/options/ConfigurationException;
        //  502    522    525    529    Lcom/intellij/openapi/options/ConfigurationException;
        //  541    574    577    581    Lcom/intellij/openapi/options/ConfigurationException;
        //  557    585    585    589    Lcom/intellij/openapi/options/ConfigurationException;
        //  673    691    694    698    Lcom/intellij/openapi/options/ConfigurationException;
        //  683    703    706    710    Lcom/intellij/openapi/options/ConfigurationException;
        //  698    718    721    725    Lcom/intellij/openapi/options/ConfigurationException;
        //  710    761    761    765    Lcom/intellij/openapi/options/ConfigurationException;
        //  765    780    783    787    Lcom/intellij/openapi/options/ConfigurationException;
        //  769    792    795    799    Lcom/intellij/openapi/options/ConfigurationException;
        //  787    846    846    850    Lcom/intellij/openapi/options/ConfigurationException;
        //  850    862    865    869    Lcom/intellij/openapi/options/ConfigurationException;
        //  854    877    880    884    Lcom/intellij/openapi/options/ConfigurationException;
        //  869    920    920    924    Lcom/intellij/openapi/options/ConfigurationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0131:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    protected void doAction() {
        Label_0032: {
            try {
                if (!this.isPreviewUsages()) {
                    break Label_0032;
                }
                final OCChangeSignatureDialog ocChangeSignatureDialog = this;
                final MethodDescriptor<ParamInfo, Visibility> methodDescriptor = ocChangeSignatureDialog.myMethod;
                final OCMethodDescriptor ocMethodDescriptor = (OCMethodDescriptor)methodDescriptor;
                final OCCallable ocCallable = ocMethodDescriptor.getMethod();
                final boolean b = ocCallable.isPhysical();
                if (b) {
                    break Label_0032;
                }
                return;
            }
            catch (UnsupportedOperationException ex) {
                throw c(ex);
            }
            try {
                final OCChangeSignatureDialog ocChangeSignatureDialog = this;
                final MethodDescriptor<ParamInfo, Visibility> methodDescriptor = ocChangeSignatureDialog.myMethod;
                final OCMethodDescriptor ocMethodDescriptor = (OCMethodDescriptor)methodDescriptor;
                final OCCallable ocCallable = ocMethodDescriptor.getMethod();
                final boolean b = ocCallable.isPhysical();
                if (b) {
                    super.doAction();
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw c(ex2);
            }
        }
    }
    
    @Nullable
    @Override
    protected String validateAndCommitData() {
        if (this.myContainerPanel.isVisible()) {
            final OCSymbol classDeclaration = this.myContainerClassField.getClassDeclaration(this.myProject);
            try {
                if (classDeclaration != null) {
                    this.myHandler.getGeneratedInfo().setMethodParent(classDeclaration);
                }
            }
            catch (UnsupportedOperationException ex) {
                throw c(ex);
            }
        }
        return null;
    }
    
    private static String a(final String s) {
        try {
            if (s.indexOf(58) == -1) {
                return s;
            }
        }
        catch (UnsupportedOperationException ex) {
            throw c(ex);
        }
        return s.substring(0, s.indexOf(58));
    }
    
    @Override
    protected VisibilityPanelBase<Object> createVisibilityControl() {
        this.initHandler();
        if (((OCMethodDescriptor)this.myMethod).isChangeCallableKindPossible()) {
            final ComboBoxVisibilityPanel<Object> comboBoxVisibilityPanel = new ComboBoxVisibilityPanel<Object>("Callable Type:", new OCCallableKind[] { OCCallableKind.METHOD, OCCallableKind.FUNCTION, OCCallableKind.BLOCK });
            ComboBoxVisibilityPanel<Object> comboBoxVisibilityPanel2 = null;
            boolean enabled = false;
            Label_0091: {
                try {
                    comboBoxVisibilityPanel.addListener(new ChangeListener() {
                        @Override
                        public void stateChanged(final ChangeEvent changeEvent) {
                            OCChangeSignatureDialog.this.a((OCCallableKind)((ChangeSignatureDialogBase<ParamInfo, Method, Object, Descriptor, ParameterTableModelItem, ParameterTableModel>)OCChangeSignatureDialog.this).getVisibility());
                        }
                    });
                    comboBoxVisibilityPanel2 = comboBoxVisibilityPanel;
                    if (((OCMethodDescriptor)this.myMethod).getMethod().getBody() != null) {
                        enabled = true;
                        break Label_0091;
                    }
                }
                catch (UnsupportedOperationException ex) {
                    throw c(ex);
                }
                enabled = false;
            }
            comboBoxVisibilityPanel2.setEnabled(enabled);
            return comboBoxVisibilityPanel;
        }
        Label_0254: {
            try {
                this.a((OCCallableKind)((OCMethodDescriptor)this.myMethod).getVisibility());
                if (!(((OCMethodDescriptor)this.myMethod).getMethod() instanceof OCFunctionDefinition) || ((OCFunctionDefinition)((OCMethodDescriptor)this.myMethod).getMethod()).getNamespaceQualifier() != null) {
                    break Label_0254;
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw c(ex2);
            }
            final Object[] array = { "above", "below" };
            final ComboBoxVisibilityPanel comboBoxVisibilityPanel3 = new ComboBoxVisibilityPanel<Object>("Declaration place:", array);
            comboBoxVisibilityPanel3.addListener(new ChangeListener() {
                @Override
                public void stateChanged(final ChangeEvent changeEvent) {
                    OCChangeSignatureDialog.this.myHandler.getGeneratedInfo().setTargetSymbolsMode("below".equals(comboBoxVisibilityPanel3.getVisibility()) ? OCTargetSymbolPanel.TargetSymbolsMode.INTERFACE : OCTargetSymbolPanel.TargetSymbolsMode.IMPLEMENTATION);
                    OCChangeSignatureDialog.this.myProject.putUserData(OCChangeSignatureDialog.DECLARATION_PLACE_KEY, (Object)ArrayUtil.find(array, comboBoxVisibilityPanel3.getVisibility()));
                }
            });
            comboBoxVisibilityPanel3.setVisible(false);
            Integer value = (Integer)this.myProject.getUserData((Key)OCChangeSignatureDialog.DECLARATION_PLACE_KEY);
            Label_0242: {
                try {
                    if (value != null) {
                        if (value != -1) {
                            break Label_0242;
                        }
                    }
                }
                catch (UnsupportedOperationException ex3) {
                    throw c(ex3);
                }
                value = 1;
            }
            comboBoxVisibilityPanel3.setVisibility(array[value]);
            return (VisibilityPanelBase<Object>)comboBoxVisibilityPanel3;
        }
        final OCTargetSymbolPanel ocTargetSymbolPanel = new OCTargetSymbolPanel();
        ocTargetSymbolPanel.setVisible(false);
        return ocTargetSymbolPanel;
    }
    
    private void a(final OCCallableKind p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myNameField:Lcom/intellij/ui/EditorTextField;
        //     4: invokevirtual   com/intellij/ui/EditorTextField.getText:()Ljava/lang/String;
        //     7: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.a:(Ljava/lang/String;)Ljava/lang/String;
        //    10: astore_2       
        //    11: aload_0        
        //    12: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //    15: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.getCallableKind:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //    18: getstatic       com/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind.METHOD:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //    21: if_acmpne       58
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //    28: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.getParameters:()Ljava/util/List;
        //    31: astore_3       
        //    32: aload_3        
        //    33: invokeinterface java/util/List.isEmpty:()Z
        //    38: ifne            58
        //    41: aload_3        
        //    42: iconst_0       
        //    43: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    48: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //    51: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getSelector:()Ljava/lang/String;
        //    54: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.a:(Ljava/lang/String;)Ljava/lang/String;
        //    57: astore_2       
        //    58: aload_0        
        //    59: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //    62: aload_1        
        //    63: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.setCallableKindToModel:(Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;)V
        //    66: aload_0        
        //    67: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myChangeParentClassPossible:Z
        //    70: ifne            110
        //    73: aload_0        
        //    74: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //    77: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.isMethod:()Z
        //    80: ifeq            118
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    89: athrow         
        //    90: aload_0        
        //    91: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //    94: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.getOriginalCallableKind:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //    97: getstatic       com/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind.METHOD:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //   100: if_acmpeq       118
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   109: athrow         
        //   110: iconst_1       
        //   111: goto            119
        //   114: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   117: athrow         
        //   118: iconst_0       
        //   119: istore_3       
        //   120: aload_0        
        //   121: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myContainerPanel:Ljavax/swing/JPanel;
        //   124: iload_3        
        //   125: invokevirtual   javax/swing/JPanel.setVisible:(Z)V
        //   128: aload_0        
        //   129: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //   132: aload_0        
        //   133: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //   136: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.isFunction:()Z
        //   139: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.setNameVisible:(Z)V
        //   142: aload_0        
        //   143: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myNameField:Lcom/intellij/ui/EditorTextField;
        //   146: aload_2        
        //   147: invokevirtual   com/intellij/ui/EditorTextField.setText:(Ljava/lang/String;)V
        //   150: aload_0        
        //   151: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myParametersTableModel:Lcom/intellij/refactoring/changeSignature/ParameterTableModelBase;
        //   154: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterTableModel;
        //   157: aload_0        
        //   158: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myProject:Lcom/intellij/openapi/project/Project;
        //   161: aload_0        
        //   162: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //   165: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.isMethod:()Z
        //   168: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterTableModel.updateColumns:(Lcom/intellij/openapi/project/Project;Z)V
        //   171: aload_0        
        //   172: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.getTableComponent:()Lcom/intellij/ui/table/JBTable;
        //   175: ifnull          192
        //   178: aload_0        
        //   179: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.getTableComponent:()Lcom/intellij/ui/table/JBTable;
        //   182: invokevirtual   com/intellij/ui/table/JBTable.repaint:()V
        //   185: goto            192
        //   188: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   191: athrow         
        //   192: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  58     83     86     90     Ljava/lang/UnsupportedOperationException;
        //  73     103    106    110    Ljava/lang/UnsupportedOperationException;
        //  90     114    114    118    Ljava/lang/UnsupportedOperationException;
        //  120    185    188    192    Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0090:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public OCChangeSignatureHandlerImpl getHandler() {
        this.initHandler();
        return this.myHandler;
    }
    
    @Override
    protected boolean hasPreviewButton() {
        try {
            if (!this.myHandler.isMethodGenerated()) {
                return true;
            }
        }
        catch (UnsupportedOperationException ex) {
            throw c(ex);
        }
        return false;
    }
    
    @Override
    protected String calculateSignature() {
        this.initHandler();
        final String calculateSignature = this.myHandler.calculateSignature(null, true);
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(this.getProject()).getCustomSettings((Class)OCCodeStyleSettings.class);
        boolean method_PARAMETERS_ALIGN_MULTILINE = false;
        boolean method_PARAMETERS_ALIGN_BY_COLONS = false;
        boolean function_PARAMETERS_ALIGN_MULTILINE = false;
        if (ocCodeStyleSettings != null) {
            method_PARAMETERS_ALIGN_MULTILINE = ocCodeStyleSettings.METHOD_PARAMETERS_ALIGN_MULTILINE;
            method_PARAMETERS_ALIGN_BY_COLONS = ocCodeStyleSettings.METHOD_PARAMETERS_ALIGN_BY_COLONS;
            function_PARAMETERS_ALIGN_MULTILINE = ocCodeStyleSettings.FUNCTION_PARAMETERS_ALIGN_MULTILINE;
            ocCodeStyleSettings.METHOD_PARAMETERS_ALIGN_MULTILINE = true;
            ocCodeStyleSettings.METHOD_PARAMETERS_ALIGN_BY_COLONS = true;
            ocCodeStyleSettings.FUNCTION_PARAMETERS_ALIGN_MULTILINE = true;
        }
        final String s = (String)WriteAction.compute(() -> {
            switch (this.myHandler.getCallableKind()) {
                case METHOD: {
                    final String text = OCElementFactory.methodFromSignature(calculateSignature, (PsiElement)((OCMethodDescriptor)this.myMethod).getMethod(), false, true).getText();
                    return text.substring(0, text.length() - 1);
                }
                case FUNCTION: {
                    try {
                        if (((OCMethodDescriptor)this.myMethod).isConstructor()) {
                            return OCElementFactory.constructorFromText(calculateSignature, (PsiElement)((OCMethodDescriptor)this.myMethod).getMethod(), true).getText();
                        }
                    }
                    catch (RuntimeException ex) {
                        throw c(ex);
                    }
                    return OCElementFactory.topLevelDeclarationFromText(calculateSignature, (PsiElement)((OCMethodDescriptor)this.myMethod).getMethod(), true).getText();
                }
                default: {
                    return OCElementFactory.expressionFromText(calculateSignature, (PsiElement)((OCMethodDescriptor)this.myMethod).getMethod(), true).getText();
                }
            }
        });
        try {
            if (ocCodeStyleSettings != null) {
                ocCodeStyleSettings.METHOD_PARAMETERS_ALIGN_MULTILINE = method_PARAMETERS_ALIGN_MULTILINE;
                ocCodeStyleSettings.METHOD_PARAMETERS_ALIGN_BY_COLONS = method_PARAMETERS_ALIGN_BY_COLONS;
                ocCodeStyleSettings.FUNCTION_PARAMETERS_ALIGN_MULTILINE = function_PARAMETERS_ALIGN_MULTILINE;
            }
        }
        catch (UnsupportedOperationException ex) {
            throw c(ex);
        }
        return s;
    }
    
    @Override
    protected boolean isListTableViewSupported() {
        return true;
    }
    
    private int b() {
        int max = 0;
        for (final ParameterTableModelItemBase parameterTableModelItemBase : ((OCParameterTableModel)this.myParametersTableModel).getItems()) {
            String text = null;
            Label_0061: {
                try {
                    if (parameterTableModelItemBase.typeCodeFragment == null) {
                        text = null;
                        break Label_0061;
                    }
                }
                catch (UnsupportedOperationException ex) {
                    throw c(ex);
                }
                text = parameterTableModelItemBase.typeCodeFragment.getText();
            }
            final String s = text;
            int n = 0;
            int length = 0;
            Label_0082: {
                try {
                    n = max;
                    if (s == null) {
                        length = 0;
                        break Label_0082;
                    }
                }
                catch (UnsupportedOperationException ex2) {
                    throw c(ex2);
                }
                length = s.length();
            }
            max = Math.max(n, length);
        }
        return max;
    }
    
    private int a() {
        final Font font = EditorColorsManager.getInstance().getGlobalScheme().getFont(EditorFontType.PLAIN);
        return (this.b() + 1) * Toolkit.getDefaultToolkit().getFontMetrics(new Font(font.getFontName(), font.getStyle(), 12)).stringWidth("W");
    }
    
    private int c() {
        int max = 0;
        final Iterator<ParameterTableModelItemBase> iterator = (Iterator<ParameterTableModelItemBase>)((OCParameterTableModel)this.myParametersTableModel).getItems().iterator();
        while (iterator.hasNext()) {
            final String selector = ((OCParameterInfo)iterator.next().parameter).getSelector();
            int n = 0;
            int length = 0;
            Label_0068: {
                try {
                    n = max;
                    if (selector == null) {
                        length = 0;
                        break Label_0068;
                    }
                }
                catch (UnsupportedOperationException ex) {
                    throw c(ex);
                }
                length = selector.length();
            }
            max = Math.max(n, length);
        }
        return max;
    }
    
    protected static SimpleTextAttributes getSelectorTextAttributes(final EditorColorsScheme editorColorsScheme) {
        return SimpleTextAttributes.fromTextAttributes(editorColorsScheme.getAttributes(OCHighlightingKeys.MESSAGE_ARGUMENT));
    }
    
    protected static SimpleTextAttributes getTypeTextAttributes(final EditorColorsScheme editorColorsScheme) {
        return SimpleTextAttributes.fromTextAttributes(editorColorsScheme.getAttributes(OCHighlightingKeys.CLASS_REFERENCE));
    }
    
    @Override
    protected JComponent getRowPresentation(final ParameterTableModelItemBase<OCParameterInfo> p0, final boolean p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/intellij/util/ui/UIUtil.getTableSelectionBackground:()Ljava/awt/Color;
        //     3: astore          4
        //     5: invokestatic    com/intellij/util/ui/UIUtil.getTableSelectionForeground:()Ljava/awt/Color;
        //     8: astore          5
        //    10: iload_2        
        //    11: ifeq            45
        //    14: iload_3        
        //    15: ifeq            45
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    24: athrow         
        //    25: new             Lcom/intellij/ui/SimpleTextAttributes;
        //    28: dup            
        //    29: aload           4
        //    31: aload           5
        //    33: aconst_null    
        //    34: iconst_0       
        //    35: invokespecial   com/intellij/ui/SimpleTextAttributes.<init>:(Ljava/awt/Color;Ljava/awt/Color;Ljava/awt/Color;I)V
        //    38: goto            48
        //    41: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    44: athrow         
        //    45: getstatic       com/intellij/ui/SimpleTextAttributes.REGULAR_ATTRIBUTES:Lcom/intellij/ui/SimpleTextAttributes;
        //    48: astore          6
        //    50: new             Lcom/intellij/ui/SimpleColoredComponent;
        //    53: dup            
        //    54: invokespecial   com/intellij/ui/SimpleColoredComponent.<init>:()V
        //    57: astore          7
        //    59: invokestatic    com/intellij/openapi/editor/colors/EditorColorsManager.getInstance:()Lcom/intellij/openapi/editor/colors/EditorColorsManager;
        //    62: invokevirtual   com/intellij/openapi/editor/colors/EditorColorsManager.getGlobalScheme:()Lcom/intellij/openapi/editor/colors/EditorColorsScheme;
        //    65: astore          8
        //    67: aload           8
        //    69: getstatic       com/intellij/openapi/editor/colors/EditorFontType.PLAIN:Lcom/intellij/openapi/editor/colors/EditorFontType;
        //    72: invokeinterface com/intellij/openapi/editor/colors/EditorColorsScheme.getFont:(Lcom/intellij/openapi/editor/colors/EditorFontType;)Ljava/awt/Font;
        //    77: astore          9
        //    79: new             Ljava/awt/Font;
        //    82: dup            
        //    83: aload           9
        //    85: invokevirtual   java/awt/Font.getFontName:()Ljava/lang/String;
        //    88: aload           9
        //    90: invokevirtual   java/awt/Font.getStyle:()I
        //    93: bipush          12
        //    95: invokespecial   java/awt/Font.<init>:(Ljava/lang/String;II)V
        //    98: astore          9
        //   100: aload           7
        //   102: aload           9
        //   104: invokevirtual   com/intellij/ui/SimpleColoredComponent.setFont:(Ljava/awt/Font;)V
        //   107: aload_1        
        //   108: getfield        com/intellij/refactoring/changeSignature/ParameterTableModelItemBase.typeCodeFragment:Lcom/intellij/psi/PsiCodeFragment;
        //   111: invokeinterface com/intellij/psi/PsiCodeFragment.getText:()Ljava/lang/String;
        //   116: astore          10
        //   118: aload_1        
        //   119: getfield        com/intellij/refactoring/changeSignature/ParameterTableModelItemBase.parameter:Ljava/lang/Object;
        //   122: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   125: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getSelector:()Ljava/lang/String;
        //   128: astore          11
        //   130: aload_1        
        //   131: getfield        com/intellij/refactoring/changeSignature/ParameterTableModelItemBase.parameter:Ljava/lang/Object;
        //   134: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   137: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getName:()Ljava/lang/String;
        //   140: astore          12
        //   142: aload_0        
        //   143: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //   146: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.isMethod:()Z
        //   149: ifeq            245
        //   152: aload           11
        //   154: invokestatic    com/intellij/openapi/util/text/StringUtil.isEmpty:(Ljava/lang/String;)Z
        //   157: ifne            245
        //   160: goto            167
        //   163: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   166: athrow         
        //   167: aload           7
        //   169: aload           11
        //   171: iload_2        
        //   172: ifeq            191
        //   175: goto            182
        //   178: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   181: athrow         
        //   182: aload           6
        //   184: goto            196
        //   187: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   190: athrow         
        //   191: aload           8
        //   193: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.getSelectorTextAttributes:(Lcom/intellij/openapi/editor/colors/EditorColorsScheme;)Lcom/intellij/ui/SimpleTextAttributes;
        //   196: invokevirtual   com/intellij/ui/SimpleColoredComponent.append:(Ljava/lang/String;Lcom/intellij/ui/SimpleTextAttributes;)V
        //   199: aload           12
        //   201: invokestatic    com/intellij/openapi/util/text/StringUtil.isEmpty:(Ljava/lang/String;)Z
        //   204: ifne            223
        //   207: aload           7
        //   209: ldc             ":"
        //   211: aload           6
        //   213: invokevirtual   com/intellij/ui/SimpleColoredComponent.append:(Ljava/lang/String;Lcom/intellij/ui/SimpleTextAttributes;)V
        //   216: goto            223
        //   219: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   222: athrow         
        //   223: aload           7
        //   225: bipush          32
        //   227: aload_0        
        //   228: invokespecial   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:()I
        //   231: aload           11
        //   233: invokevirtual   java/lang/String.length:()I
        //   236: isub           
        //   237: invokestatic    com/intellij/openapi/util/text/StringUtil.repeatSymbol:(CI)Ljava/lang/String;
        //   240: aload           6
        //   242: invokevirtual   com/intellij/ui/SimpleColoredComponent.append:(Ljava/lang/String;Lcom/intellij/ui/SimpleTextAttributes;)V
        //   245: aload_0        
        //   246: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //   249: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.isMethod:()Z
        //   252: ifeq            286
        //   255: aload           10
        //   257: invokestatic    com/intellij/openapi/util/text/StringUtil.isEmpty:(Ljava/lang/String;)Z
        //   260: ifne            286
        //   263: goto            270
        //   266: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   269: athrow         
        //   270: aload           7
        //   272: ldc             "("
        //   274: aload           6
        //   276: invokevirtual   com/intellij/ui/SimpleColoredComponent.append:(Ljava/lang/String;Lcom/intellij/ui/SimpleTextAttributes;)V
        //   279: goto            286
        //   282: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   285: athrow         
        //   286: aload           7
        //   288: aload           10
        //   290: iload_2        
        //   291: ifeq            314
        //   294: iload_3        
        //   295: ifeq            314
        //   298: goto            305
        //   301: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   304: athrow         
        //   305: aload           6
        //   307: goto            319
        //   310: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   313: athrow         
        //   314: aload           8
        //   316: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.getTypeTextAttributes:(Lcom/intellij/openapi/editor/colors/EditorColorsScheme;)Lcom/intellij/ui/SimpleTextAttributes;
        //   319: invokevirtual   com/intellij/ui/SimpleColoredComponent.append:(Ljava/lang/String;Lcom/intellij/ui/SimpleTextAttributes;)V
        //   322: aload_0        
        //   323: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.myHandler:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl;
        //   326: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog$DialogChangeSignatureHandlerImpl.isMethod:()Z
        //   329: ifeq            363
        //   332: aload           10
        //   334: invokestatic    com/intellij/openapi/util/text/StringUtil.isEmpty:(Ljava/lang/String;)Z
        //   337: ifne            363
        //   340: goto            347
        //   343: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   346: athrow         
        //   347: aload           7
        //   349: ldc             ")"
        //   351: aload           6
        //   353: invokevirtual   com/intellij/ui/SimpleColoredComponent.append:(Ljava/lang/String;Lcom/intellij/ui/SimpleTextAttributes;)V
        //   356: goto            363
        //   359: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   362: athrow         
        //   363: aload           7
        //   365: bipush          32
        //   367: aload_0        
        //   368: invokespecial   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.b:()I
        //   371: aload           10
        //   373: invokevirtual   java/lang/String.length:()I
        //   376: isub           
        //   377: iconst_1       
        //   378: iadd           
        //   379: invokestatic    com/intellij/openapi/util/text/StringUtil.repeatSymbol:(CI)Ljava/lang/String;
        //   382: aload           6
        //   384: invokevirtual   com/intellij/ui/SimpleColoredComponent.append:(Ljava/lang/String;Lcom/intellij/ui/SimpleTextAttributes;)V
        //   387: aload           7
        //   389: aload           12
        //   391: aload           6
        //   393: invokevirtual   com/intellij/ui/SimpleColoredComponent.append:(Ljava/lang/String;Lcom/intellij/ui/SimpleTextAttributes;)V
        //   396: aload           7
        //   398: iload_2        
        //   399: ifeq            422
        //   402: iload_3        
        //   403: ifeq            422
        //   406: goto            413
        //   409: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   412: athrow         
        //   413: aload           4
        //   415: goto            425
        //   418: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureDialog.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   421: athrow         
        //   422: invokestatic    com/intellij/util/ui/UIUtil.getTableBackground:()Ljava/awt/Color;
        //   425: invokevirtual   com/intellij/ui/SimpleColoredComponent.setBackground:(Ljava/awt/Color;)V
        //   428: aload           7
        //   430: areturn        
        //    Signature:
        //  (Lcom/intellij/refactoring/changeSignature/ParameterTableModelItemBase<Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;>;ZZ)Ljavax/swing/JComponent;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  10     18     21     25     Ljava/lang/UnsupportedOperationException;
        //  14     41     41     45     Ljava/lang/UnsupportedOperationException;
        //  142    160    163    167    Ljava/lang/UnsupportedOperationException;
        //  152    175    178    182    Ljava/lang/UnsupportedOperationException;
        //  167    187    187    191    Ljava/lang/UnsupportedOperationException;
        //  196    216    219    223    Ljava/lang/UnsupportedOperationException;
        //  245    263    266    270    Ljava/lang/UnsupportedOperationException;
        //  255    279    282    286    Ljava/lang/UnsupportedOperationException;
        //  286    298    301    305    Ljava/lang/UnsupportedOperationException;
        //  294    310    310    314    Ljava/lang/UnsupportedOperationException;
        //  319    340    343    347    Ljava/lang/UnsupportedOperationException;
        //  332    356    359    363    Ljava/lang/UnsupportedOperationException;
        //  363    406    409    413    Ljava/lang/UnsupportedOperationException;
        //  402    418    418    422    Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0167:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    protected JBTableRowEditor getTableEditor(final JTable table, final ParameterTableModelItemBase<OCParameterInfo> parameterTableModelItemBase) {
        return new JBTableRowEditor() {
            private List<EditorTextField> myEditors = new ArrayList<EditorTextField>();
            
            @Override
            public void prepareEditor(final JTable table, final int n) {
                this.setLayout(new BorderLayout());
                final String[] array = { "West", "Center", "East" };
                int n2 = 0;
                for (final ColumnInfo columnInfo : ((OCParameterTableModel)OCChangeSignatureDialog.this.myParametersTableModel).getColumnInfos()) {
                    final JPanel panel = new JPanel((LayoutManager)new VerticalFlowLayout(0, 4, 2, true, false));
                    EditorTextField editorTextField;
                    if (OCParameterTableModel.isTypeColumn(columnInfo)) {
                        editorTextField = new EditorTextField(PsiDocumentManager.getInstance(OCChangeSignatureDialog.this.getProject()).getDocument((PsiFile)parameterTableModelItemBase.typeCodeFragment), OCChangeSignatureDialog.this.getProject(), (FileType)OCChangeSignatureDialog.this.getFileType());
                    }
                    else {
                        editorTextField = new EditorTextField((String)columnInfo.valueOf((Object)parameterTableModelItemBase), OCChangeSignatureDialog.this.getProject(), (FileType)OCChangeSignatureDialog.this.getFileType());
                    }
                    editorTextField.addDocumentListener((DocumentListener)new DocumentListener() {
                        public void documentChanged(final DocumentEvent documentEvent) {
                            JBTableRowEditor.this.fireDocumentChanged(documentEvent, n2);
                        }
                    });
                    final JBLabel jbLabel = new JBLabel(columnInfo.getName(), UIUtil.ComponentStyle.SMALL);
                    panel.add((Component)jbLabel);
                    panel.add((Component)editorTextField);
                    IJSwingUtilities.adjustComponentsOnMac((JLabel)jbLabel, (JComponent)editorTextField);
                    editorTextField.setPreferredWidth(table.getWidth() / ((OCParameterTableModel)OCChangeSignatureDialog.this.myParametersTableModel).getColumnCount());
                    this.add(panel, array[n2]);
                    this.myEditors.add(editorTextField);
                    ++n2;
                }
            }
            
            @Override
            public JBTableRow getValue() {
                return new JBTableRow() {
                    @Override
                    public Object getValueAt(final int n) {
                        if (OCParameterTableModel.isTypeColumn(((OCParameterTableModel)OCChangeSignatureDialog.this.myParametersTableModel).getColumnInfos()[n])) {
                            return parameterTableModelItemBase.typeCodeFragment;
                        }
                        return JBTableRowEditor.this.myEditors.get(n).getText();
                    }
                };
            }
            
            private int a(final int n) {
                final Font font = EditorColorsManager.getInstance().getGlobalScheme().getFont(EditorFontType.PLAIN);
                final Font font2 = new Font(font.getFontName(), font.getStyle(), 12);
                final int n2 = (OCChangeSignatureDialog.this.c() + 1) * Toolkit.getDefaultToolkit().getFontMetrics(font2).stringWidth("W");
                final int n3 = (OCChangeSignatureDialog.this.b() + 1) * Toolkit.getDefaultToolkit().getFontMetrics(font2).stringWidth("W") + n2;
                if (OCChangeSignatureDialog.this.myHandler.isMethod()) {
                    return (n <= n2) ? 0 : ((n <= n3) ? 1 : 2);
                }
                return (n > n3) ? 1 : 0;
            }
            
            @Override
            public JComponent getPreferredFocusedComponent() {
                final MouseEvent mouseEvent = this.getMouseEvent();
                if (mouseEvent == null) {
                    return this.myEditors.get(0).getFocusTarget();
                }
                return this.myEditors.get(this.a((int)mouseEvent.getPoint().getX())).getFocusTarget();
            }
            
            @Override
            public JComponent[] getFocusableComponents() {
                final JComponent[] array = new JComponent[this.myEditors.size()];
                for (int i = 0; i < this.myEditors.size(); ++i) {
                    array[i] = this.myEditors.get(i).getFocusTarget();
                }
                return array;
            }
        };
    }
    
    static {
        DECLARATION_PLACE_KEY = Key.create("DECLARATION_PLACE_KEY");
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
    
    private class DialogChangeSignatureHandlerImpl extends OCChangeSignatureHandlerImpl
    {
        public DialogChangeSignatureHandlerImpl() {
            super((OCParameterTableModel)OCChangeSignatureDialog.this.myParametersTableModel, (OCMethodDescriptor)OCChangeSignatureDialog.this.myMethod, OCChangeSignatureDialog.this.myDefaultValueContext);
        }
        
        @Override
        public void setTitle(final String s) {
            super.setTitle(s);
            OCChangeSignatureDialog.this.setTitle(s);
        }
        
        @Override
        public void setHelpId(final String s) {
            super.setHelpId(s);
            OCChangeSignatureDialog.this.setHelpId(s);
        }
        
        public void setCallableKindToModel(final OCCallableKind callableKind) {
            super.setCallableKind(callableKind);
        }
        
        @Override
        public void setCallableKind(final OCCallableKind ocCallableKind) {
            super.setCallableKind(ocCallableKind);
            OCChangeSignatureDialog.this.myVisibilityPanel.setVisibility(ocCallableKind);
        }
        
        @Override
        public void setChangeParentClassPossible(final boolean visible) {
            OCChangeSignatureDialog.this.myChangeParentClassPossible = visible;
            OCChangeSignatureDialog.this.myContainerPanel.setVisible(visible);
        }
        
        @Override
        public void setNameVisible(final boolean visible) {
            OCChangeSignatureDialog.this.myNamePanel.setVisible(visible);
        }
        
        @Override
        public void setName(final String name) {
            super.setName(name);
            OCChangeSignatureDialog.this.myNameField.setText(OCChangeSignatureDialog.this.myHandler.isMethod() ? this.calculateMethodName() : name);
            PsiDocumentManager.getInstance(OCChangeSignatureDialog.this.myProject).commitAllDocuments();
            OCChangeSignatureDialog.this.updateSignatureAlarmFired();
        }
        
        @Override
        public void setReturnType(final OCType returnType) {
            super.setReturnType(returnType);
            if (OCChangeSignatureDialog.this.myReturnTypeField != null) {
                OCChangeSignatureDialog.this.myReturnTypeField.setText(this.myReturnTypeText);
            }
        }
        
        @Override
        public void setParentClass(final OCSymbol ocSymbol, final boolean b, final List<? extends OCClassSymbol> list) {
            super.setParentClass(ocSymbol, b, list);
            OCChangeSignatureDialog.this.myContainerClassField.setText((ocSymbol != null) ? ocSymbol.getName() : "");
        }
        
        @Override
        OCType getReturnType() {
            final OCType type = OCElementUtil.getType((PsiElement)OCChangeSignatureDialog.this.myReturnTypeCodeFragment);
            return (type != null) ? type : this.myReturnType;
        }
        
        @Override
        String getReturnTypeText() {
            return (OCChangeSignatureDialog.this.myReturnTypeCodeFragment != null) ? OCChangeSignatureDialog.this.myReturnTypeCodeFragment.getText().trim() : "";
        }
        
        @Override
        String getMethodName() {
            return OCChangeSignatureDialog.this.getMethodName();
        }
        
        @Nullable
        @Override
        OCClassDeclaration getContainerClass() {
            if (this.myCallableKind == OCCallableKind.METHOD) {
                return OCElementUtil.resolveClassDeclaration(OCChangeSignatureDialog.this.myContainerClassField.getClassDeclaration(OCChangeSignatureDialog.this.myProject));
            }
            return null;
        }
        
        @Override
        public void setRefactorButtonText(final String s) {
            OCChangeSignatureDialog.this.getRefactorAction().putValue("Name", s);
        }
        
        @Override
        public void invoke() {
            final List items = this.myParametersTableModel.getItems();
            if (OCChangeSignatureDialog.this.myHandler.getGeneratedInfo().getMethodParent() instanceof OCClassSymbol) {
                OCChangeSignatureDialog.this.myVisibilityPanel.setVisible(!OCChangeSignatureDialog.this.myChangeParentClassPossible);
                ((OCTargetSymbolPanel)OCChangeSignatureDialog.this.myVisibilityPanel).fillData(OCChangeSignatureDialog.this.myHandler.getGeneratedInfo());
                OCChangeSignatureDialog.this.myContainerClassField.setText(OCChangeSignatureDialog.this.myHandler.getGeneratedInfo().getMethodParent().getName());
            }
            else if (this.myMethod.getMethod() instanceof OCFunctionDefinition && ((OCFunctionDefinition)this.myMethod.getMethod()).getNamespaceQualifier() == null && this.myGeneratedInfo.getMethodReference() != null) {
                OCChangeSignatureDialog.this.myVisibilityPanel.setVisible(true);
            }
            if (this.myMethod.isConstructor()) {
                if (OCChangeSignatureDialog.this.myReturnTypeField != null) {
                    OCChangeSignatureDialog.this.myReturnTypeField.setEnabled(false);
                }
                OCChangeSignatureDialog.this.myNameField.setEnabled(false);
                OCChangeSignatureDialog.this.myVisibilityPanel.setVisible(false);
            }
            if (OCChangeSignatureDialog.this.myHandler.isMethod() && !items.isEmpty()) {
                final JBTable access$3800 = OCChangeSignatureDialog.this.getTableComponent();
                if (items.size() > 0 && ((OCParameterInfo)items.get(0).parameter).getSelector().isEmpty()) {
                    SwingUtilities.invokeLater(() -> new Alarm(Alarm.ThreadToUse.SWING_THREAD).addRequest(() -> ((JTable)access$3800).editCellAt(0, 0), 300));
                }
                final Container parent = ((Component)access$3800).getParent();
                if (parent == null) {
                    ((Component)access$3800).repaint();
                }
                else {
                    parent.repaint();
                }
            }
            OCChangeSignatureDialog.this.show();
        }
    }
}
