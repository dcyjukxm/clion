// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.intellij.psi.PsiNameIdentifierOwner;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCPropertyAttributesList;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCProperty;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.intellij.ui.NonFocusableCheckBox;
import java.awt.Component;
import javax.swing.JPanel;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.OCBundle;
import javax.swing.JComponent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbolImpl;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.editor.RangeMarker;
import com.intellij.openapi.util.Computable;
import javax.swing.JCheckBox;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.settings.OCEnumComboOption;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

public class OCPropertyInplaceIntroducer extends OCBaseExpressionInplaceIntroducer<OCDeclarator, PsiElement>
{
    public static final OCEnumComboOption<OCPropertySymbol.PropertySemantics> PROPERTY_SEMANTICS;
    public static final boolean CONVERT_USAGES_DEFAULT = true;
    public static final boolean READONLY_DEFAULT = false;
    private JCheckBox myReadonlyCB;
    private JCheckBox mySynthesizeCB;
    private JCheckBox myGenerateIvarCB;
    private JCheckBox myConvertUsagesCB;
    protected JCheckBox myPutToPrivateCategoryCB;
    protected Computable<OCPropertySymbol.PropertySemantics> mySemantics;
    protected boolean myReadonly;
    protected boolean myConvertUsages;
    protected RangeMarker myExtraOccurrenceMarker;
    
    public OCPropertyInplaceIntroducer(final Project project, final Editor editor, final PsiElement psiElement, final List<PsiElement> list, final String s) {
        super(project, editor, psiElement, list, OCDeclarator.class, PsiElement.class, s);
        this.mySemantics = (Computable<OCPropertySymbol.PropertySemantics>)(() -> null);
        this.myReadonly = false;
        this.myConvertUsages = true;
    }
    
    @Override
    public void configurePanel() {
        super.configurePanel();
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(this.myProject).getCustomSettings((Class)OCCodeStyleSettings.class);
        if (this.myExprType.isPointerToObjectCompatible()) {
            final OCEnumComboOption<OCPropertySymbol.PropertySemantics> property_SEMANTICS = OCPropertyInplaceIntroducer.PROPERTY_SEMANTICS;
            final JPanel component = property_SEMANTICS.createComponent();
            this.mySemantics = (Computable<OCPropertySymbol.PropertySemantics>)(() -> property_SEMANTICS.getSelectedValue(component));
            final Component[] components = component.getComponents();
            for (int length = components.length, i = 0; i < length; ++i) {
                components[i].setFocusable(false);
            }
            property_SEMANTICS.selectValue(component, OCPropertySymbolImpl.getDefaultSemanticsForType(this.myExprType, this.myExpr));
            property_SEMANTICS.addItemListener(component, (ItemListener)new ItemListener() {
                @Override
                public void itemStateChanged(final ItemEvent itemEvent) {
                    OCPropertyInplaceIntroducer.this.a((OCPropertySymbol.PropertySemantics)OCPropertyInplaceIntroducer.this.mySemantics.compute());
                }
            });
            OCBaseInplaceIntroducer.addControl(this.myWholePanel, component);
        }
        if (this.askToConvertUsages()) {
            this.myConvertUsagesCB = (JCheckBox)this.createCheckBox(OCBundle.message("refactoring.convertIvarUsagesToProperties", new Object[0]), true, () -> this.myConvertUsages = this.myConvertUsagesCB.isSelected());
        }
        Label_0275: {
            OCPropertyInplaceIntroducer ocPropertyInplaceIntroducer3 = null;
            OCPropertyInplaceIntroducer ocPropertyInplaceIntroducer4 = null;
            String s2 = null;
            boolean b2 = false;
            Label_0254: {
                Label_0245: {
                    Label_0196: {
                        try {
                            if (this.isCreateFromUsageMode()) {
                                this.myReadonlyCB = (JCheckBox)this.createCheckBox("Make &readonly", false, () -> {
                                    this.myReadonly = this.myReadonlyCB.isSelected();
                                    this.a((OCPropertySymbol.PropertySemantics)this.mySemantics.compute());
                                    return;
                                });
                                break Label_0196;
                            }
                        }
                        catch (IllegalStateException ex) {
                            throw b(ex);
                        }
                        this.myReadonly = false;
                        try {
                            if (!this.askToGenerateSynthesize()) {
                                break Label_0275;
                            }
                            final OCPropertyInplaceIntroducer ocPropertyInplaceIntroducer = this;
                            final OCPropertyInplaceIntroducer ocPropertyInplaceIntroducer2 = this;
                            final String s = "Generate @&synthesize";
                            final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                            final boolean b = ocCodeStyleSettings2.INTRODUCE_GENERATE_SYNTHESIZE;
                            final OCCodeStyleSettings ocCodeStyleSettings3;
                            final Runnable runnable = () -> {
                                ocCodeStyleSettings3.INTRODUCE_GENERATE_SYNTHESIZE = this.mySynthesizeCB.isSelected();
                                this.myGenerateIvarCB.setEnabled(this.mySynthesizeCB.isSelected());
                                return;
                            };
                            final NonFocusableCheckBox nonFocusableCheckBox = ocPropertyInplaceIntroducer2.createCheckBox(s, b, runnable);
                            ocPropertyInplaceIntroducer.mySynthesizeCB = (JCheckBox)nonFocusableCheckBox;
                            ocPropertyInplaceIntroducer3 = this;
                            ocPropertyInplaceIntroducer4 = this;
                            s2 = "Generate &instance variable";
                            final OCCodeStyleSettings ocCodeStyleSettings4 = ocCodeStyleSettings;
                            final OCCodeStyleSettings.RememberedOption rememberedOption = ocCodeStyleSettings4.GENERATE_INSTANCE_VARIABLES_FOR_PROPERTIES;
                            final OCCodeStyleSettings.RememberedOption rememberedOption2 = OCCodeStyleSettings.RememberedOption.YES;
                            if (rememberedOption == rememberedOption2) {
                                break Label_0245;
                            }
                            break Label_0245;
                        }
                        catch (IllegalStateException ex2) {
                            throw b(ex2);
                        }
                    }
                    try {
                        final OCPropertyInplaceIntroducer ocPropertyInplaceIntroducer = this;
                        final OCPropertyInplaceIntroducer ocPropertyInplaceIntroducer2 = this;
                        final String s = "Generate @&synthesize";
                        final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                        final boolean b = ocCodeStyleSettings2.INTRODUCE_GENERATE_SYNTHESIZE;
                        final OCCodeStyleSettings ocCodeStyleSettings3;
                        final Runnable runnable = () -> {
                            ocCodeStyleSettings3.INTRODUCE_GENERATE_SYNTHESIZE = this.mySynthesizeCB.isSelected();
                            this.myGenerateIvarCB.setEnabled(this.mySynthesizeCB.isSelected());
                            return;
                        };
                        final NonFocusableCheckBox nonFocusableCheckBox = ocPropertyInplaceIntroducer2.createCheckBox(s, b, runnable);
                        ocPropertyInplaceIntroducer.mySynthesizeCB = (JCheckBox)nonFocusableCheckBox;
                        ocPropertyInplaceIntroducer3 = this;
                        ocPropertyInplaceIntroducer4 = this;
                        s2 = "Generate &instance variable";
                        final OCCodeStyleSettings ocCodeStyleSettings4 = ocCodeStyleSettings;
                        final OCCodeStyleSettings.RememberedOption rememberedOption = ocCodeStyleSettings4.GENERATE_INSTANCE_VARIABLES_FOR_PROPERTIES;
                        final OCCodeStyleSettings.RememberedOption rememberedOption2 = OCCodeStyleSettings.RememberedOption.YES;
                        if (rememberedOption == rememberedOption2) {
                            b2 = true;
                            break Label_0254;
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw b(ex3);
                    }
                }
                b2 = false;
            }
            ocPropertyInplaceIntroducer3.myGenerateIvarCB = (JCheckBox)ocPropertyInplaceIntroducer4.createCheckBox(s2, b2, null);
            this.myGenerateIvarCB.setEnabled(this.mySynthesizeCB.isSelected());
        }
        if (this.myParentSymbol instanceof OCClassSymbol) {
            final OCImplementationSymbol implementation = ((OCClassSymbol)this.myParentSymbol).getImplementation();
            Label_0328: {
                try {
                    if (implementation == null) {
                        return;
                    }
                    final OCPropertyInplaceIntroducer ocPropertyInplaceIntroducer5 = this;
                    final PsiElement psiElement = ocPropertyInplaceIntroducer5.myExpr;
                    final PsiFile psiFile = psiElement.getContainingFile();
                    final OCImplementationSymbol ocImplementationSymbol = implementation;
                    final OCFile ocFile = ocImplementationSymbol.getContainingOCFile();
                    final boolean b3 = Comparing.equal((Object)psiFile, (Object)ocFile);
                    if (b3) {
                        break Label_0328;
                    }
                    return;
                }
                catch (IllegalStateException ex4) {
                    throw b(ex4);
                }
                try {
                    final OCPropertyInplaceIntroducer ocPropertyInplaceIntroducer5 = this;
                    final PsiElement psiElement = ocPropertyInplaceIntroducer5.myExpr;
                    final PsiFile psiFile = psiElement.getContainingFile();
                    final OCImplementationSymbol ocImplementationSymbol = implementation;
                    final OCFile ocFile = ocImplementationSymbol.getContainingOCFile();
                    final boolean b3 = Comparing.equal((Object)psiFile, (Object)ocFile);
                    if (b3) {
                        this.myPutToPrivateCategoryCB = (JCheckBox)this.createCheckBox("Put to &private category", ocCodeStyleSettings.INTRODUCE_PROP_TO_PRIVATE_CATEGORY, () -> ocCodeStyleSettings.INTRODUCE_PROP_TO_PRIVATE_CATEGORY = this.myPutToPrivateCategoryCB.isSelected());
                    }
                }
                catch (IllegalStateException ex5) {
                    throw b(ex5);
                }
            }
        }
    }
    
    protected boolean askToGenerateSynthesize() {
        Object myVarFile = this.myVarFile;
        Label_0056: {
            try {
                if (myVarFile != null || !(this.myParentSymbol instanceof OCClassSymbol)) {
                    break Label_0056;
                }
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            final OCImplementationSymbol implementation = ((OCClassSymbol)this.myParentSymbol).getImplementation();
            Object containingOCFile = null;
            Label_0055: {
                try {
                    if (implementation == null) {
                        containingOCFile = null;
                        break Label_0055;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw b(ex2);
                }
                containingOCFile = implementation.getContainingOCFile();
            }
            myVarFile = containingOCFile;
            try {
                if (myVarFile == null) {
                    return false;
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
        }
        Label_0095: {
            try {
                if (OCCompilerFeatures.supportsAutosynthesis((PsiFile)myVarFile)) {
                    return false;
                }
                final OCPropertyInplaceIntroducer ocPropertyInplaceIntroducer = this;
                final PsiElement psiElement = ocPropertyInplaceIntroducer.myExpr;
                final PsiElement psiElement2 = psiElement.getParent();
                final boolean b = psiElement2 instanceof OCSynthesizeProperty;
                if (!b) {
                    break Label_0095;
                }
                return false;
            }
            catch (IllegalStateException ex4) {
                throw b(ex4);
            }
            try {
                final OCPropertyInplaceIntroducer ocPropertyInplaceIntroducer = this;
                final PsiElement psiElement = ocPropertyInplaceIntroducer.myExpr;
                final PsiElement psiElement2 = psiElement.getParent();
                final boolean b = psiElement2 instanceof OCSynthesizeProperty;
                if (!b) {
                    return true;
                }
            }
            catch (IllegalStateException ex5) {
                throw b(ex5);
            }
        }
        return false;
    }
    
    protected boolean askToConvertUsages() {
        return false;
    }
    
    public boolean isConvertUsages() {
        return this.myConvertUsages;
    }
    
    @Override
    protected OCSymbolKind getDeclaratorKind() {
        return OCSymbolKind.PROPERTY;
    }
    
    private void a(final OCPropertySymbol.PropertySemantics propertySemantics) {
        new WriteCommandAction(this.myProject, this.myTitle, this.myTitle, new PsiFile[0]) {
            protected void run(@NotNull final Result result) throws Throwable {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer$2", "run"));
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                final OCProperty ocProperty = (OCProperty)PsiTreeUtil.getParentOfType(((OCBaseInplaceIntroducer<PsiElement, E>)OCPropertyInplaceIntroducer.this).getVariable(), (Class)OCProperty.class);
                if (ocProperty != null) {
                    final OCProperty propertyDeclaration = OCElementFactory.propertyDeclaration("pr", OCPropertyInplaceIntroducer.this.myExprType, (PsiElement)ocProperty, propertySemantics, OCPropertyInplaceIntroducer.this.myReadonly);
                    final OCPropertyAttributesList propertyAttributesList = ocProperty.getPropertyAttributesList();
                    try {
                        if (propertyAttributesList != null) {
                            OCChangeUtil.replaceHandlingMacros((PsiElement)propertyAttributesList, (PsiElement)propertyDeclaration.getPropertyAttributesList());
                        }
                    }
                    catch (Throwable t2) {
                        throw a(t2);
                    }
                }
            }
            
            private static Throwable a(final Throwable t) {
                return t;
            }
        }.execute();
    }
    
    @Override
    protected String checkExpression(final PsiElement psiElement) {
        try {
            if (psiElement.getParent() instanceof OCSynthesizeProperty) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        final String checkExpression = super.checkExpression(psiElement);
        try {
            if (checkExpression != null) {
                return checkExpression;
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        if (this.myParentSymbol == null) {
            final OCMethod ocMethod = (OCMethod)PsiTreeUtil.getContextOfType((PsiElement)PsiTreeUtil.getContextOfType((PsiElement)this.myExpr, false, new Class[] { OCBlockStatement.class }), false, new Class[] { OCMethod.class });
            Label_0110: {
                try {
                    if (ocMethod == null) {
                        return "Selected expression should be inside an instance method";
                    }
                    final OCMethod ocMethod2 = ocMethod;
                    final boolean b = ocMethod2.isInstanceMethod();
                    if (!b) {
                        return "Selected expression should be inside an instance method";
                    }
                    break Label_0110;
                }
                catch (IllegalStateException ex3) {
                    throw b(ex3);
                }
                try {
                    final OCMethod ocMethod2 = ocMethod;
                    final boolean b = ocMethod2.isInstanceMethod();
                    if (!b) {
                        return "Selected expression should be inside an instance method";
                    }
                }
                catch (IllegalStateException ex4) {
                    throw b(ex4);
                }
            }
            final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getParentOfType((PsiElement)ocMethod, (Class)OCClassDeclaration.class);
            OCSymbol symbol = null;
            Label_0143: {
                try {
                    if (ocClassDeclaration != null) {
                        symbol = ocClassDeclaration.getSymbol();
                        break Label_0143;
                    }
                }
                catch (IllegalStateException ex5) {
                    throw b(ex5);
                }
                symbol = null;
            }
            this.myParentSymbol = symbol;
        }
        return null;
    }
    
    @Override
    public PsiElement evaluateAnchor() {
        try {
            if (this.myExpr.getParent() instanceof OCSynthesizeProperty) {
                return this.myExpr.getParent().getPrevSibling();
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return super.evaluateAnchor();
    }
    
    private OCDeclarator a(final String p0, final OCClassDeclaration p1, final PsiElement p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_0        
        //     2: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     5: aload_2        
        //     6: aload_0        
        //     7: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.mySemantics:Lcom/intellij/openapi/util/Computable;
        //    10: invokeinterface com/intellij/openapi/util/Computable.compute:()Ljava/lang/Object;
        //    15: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics;
        //    18: aload_0        
        //    19: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myReadonly:Z
        //    22: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.propertyDeclaration:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics;Z)Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //    25: astore          5
        //    27: aload_2        
        //    28: aload           5
        //    30: aload_3        
        //    31: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    34: checkcast       Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //    37: astore          5
        //    39: aload_0        
        //    40: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.getMainExpression:()Lcom/intellij/psi/PsiElement;
        //    43: astore          7
        //    45: aload           7
        //    47: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseLocalConvertibleHandler.isDeclaratorIdentifier:(Lcom/intellij/psi/PsiElement;)Z
        //    50: ifeq            73
        //    53: aload           7
        //    55: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    60: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    63: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    68: astore          6
        //    70: goto            77
        //    73: aload           7
        //    75: astore          6
        //    77: aload_0        
        //    78: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.isCreateFromUsageMode:()Z
        //    81: ifne            370
        //    84: aload           6
        //    86: ifnull          370
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    95: athrow         
        //    96: new             Ljava/lang/StringBuilder;
        //    99: dup            
        //   100: invokespecial   java/lang/StringBuilder.<init>:()V
        //   103: ldc             "self."
        //   105: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   108: aload_1        
        //   109: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   112: ldc             "=a;"
        //   114: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   117: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   120: aload_3        
        //   121: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   126: iconst_1       
        //   127: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.statementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Z)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   130: astore          8
        //   132: aload           8
        //   134: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   137: invokeinterface com/jetbrains/cidr/lang/psi/OCExpressionStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   142: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   145: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getSourceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   150: astore          9
        //   152: iload           4
        //   154: ifeq            174
        //   157: aload           9
        //   159: aload           6
        //   161: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.replace:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   166: pop            
        //   167: goto            182
        //   170: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   173: athrow         
        //   174: aload           9
        //   176: aload           6
        //   178: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   181: pop            
        //   182: aload_0        
        //   183: aload_3        
        //   184: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.ensureParentIsBlockStatement:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   187: astore_3       
        //   188: aload           7
        //   190: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   195: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   198: ifne            216
        //   201: aload           7
        //   203: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseLocalConvertibleHandler.isDeclaratorIdentifier:(Lcom/intellij/psi/PsiElement;)Z
        //   206: ifeq            269
        //   209: goto            216
        //   212: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   215: athrow         
        //   216: aload           7
        //   218: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //   223: ifeq            242
        //   226: goto            233
        //   229: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   232: athrow         
        //   233: aload           7
        //   235: goto            243
        //   238: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   241: athrow         
        //   242: aload_3        
        //   243: ldc             Lcom/jetbrains/cidr/lang/psi/OCStatement;.class
        //   245: iconst_0       
        //   246: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Z)Lcom/intellij/psi/PsiElement;
        //   249: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   252: astore          10
        //   254: aload           10
        //   256: aload           8
        //   258: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   261: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   264: astore          8
        //   266: goto            286
        //   269: aload_3        
        //   270: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   275: aload           8
        //   277: aload_3        
        //   278: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   281: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   284: astore          8
        //   286: aload           8
        //   288: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   291: invokeinterface com/jetbrains/cidr/lang/psi/OCExpressionStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   296: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   299: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   304: astore          10
        //   306: iload           4
        //   308: ifeq            370
        //   311: aload           10
        //   313: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   316: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   321: astore          11
        //   323: aload_0        
        //   324: aload_0        
        //   325: aload           11
        //   327: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.addOccurrence:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/editor/RangeMarker;
        //   330: putfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myExtraOccurrenceMarker:Lcom/intellij/openapi/editor/RangeMarker;
        //   333: aload_0        
        //   334: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   337: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //   342: ifne            370
        //   345: aload_0        
        //   346: aload           11
        //   348: putfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   351: aload_0        
        //   352: aload_0        
        //   353: aload_0        
        //   354: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   357: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.createMarker:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/editor/RangeMarker;
        //   360: putfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myExprMarker:Lcom/intellij/openapi/editor/RangeMarker;
        //   363: goto            370
        //   366: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   369: athrow         
        //   370: aload           5
        //   372: invokeinterface com/jetbrains/cidr/lang/psi/OCProperty.getDeclaration:()Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   377: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   382: iconst_0       
        //   383: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   388: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   391: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  77     89     92     96     Ljava/lang/IllegalStateException;
        //  152    170    170    174    Ljava/lang/IllegalStateException;
        //  188    209    212    216    Ljava/lang/IllegalStateException;
        //  201    226    229    233    Ljava/lang/IllegalStateException;
        //  216    238    238    242    Ljava/lang/IllegalStateException;
        //  323    363    366    370    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0216:
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
    protected void introduceForPreview(final String s) {
        final PsiElement anchor = this.getAnchor();
        PsiElement psiElement = PsiTreeUtil.getContextOfType(anchor, false, new Class[] { OCImplementation.class, PsiFile.class });
        if (psiElement instanceof PsiFile) {
            psiElement = psiElement.add((PsiElement)OCElementFactory.interfaceByName("i", anchor));
        }
        ((OCBaseInplaceIntroducer<OCDeclarator, E>)this).setVariable(this.a(s, (OCClassDeclaration)psiElement, anchor, true));
    }
    
    @Override
    protected void introduceForReal(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.getAnchor:()Lcom/intellij/psi/PsiElement;
        //     4: astore_2       
        //     5: aload_0        
        //     6: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myParentSymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //     9: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    12: ifeq            26
        //    15: aload_0        
        //    16: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myParentSymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    19: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    22: astore_3       
        //    23: goto            66
        //    26: aload_2        
        //    27: iconst_0       
        //    28: iconst_1       
        //    29: anewarray       Ljava/lang/Class;
        //    32: dup            
        //    33: iconst_0       
        //    34: ldc             Lcom/jetbrains/cidr/lang/psi/OCImplementation;.class
        //    36: aastore        
        //    37: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;Z[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    40: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    43: astore          4
        //    45: aload           4
        //    47: ifnull          64
        //    50: aload           4
        //    52: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    57: goto            65
        //    60: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    63: athrow         
        //    64: aconst_null    
        //    65: astore_3       
        //    66: aload_3        
        //    67: ifnull          83
        //    70: aload_3        
        //    71: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //    76: goto            84
        //    79: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    82: athrow         
        //    83: aconst_null    
        //    84: astore          4
        //    86: aload           4
        //    88: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.resolveClassDeclaration:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    91: astore          5
        //    93: aload           5
        //    95: ifnull          113
        //    98: aload           5
        //   100: invokestatic    com/intellij/refactoring/util/CommonRefactoringUtil.checkReadOnlyStatus:(Lcom/intellij/psi/PsiElement;)Z
        //   103: ifne            118
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   112: athrow         
        //   113: return         
        //   114: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   117: athrow         
        //   118: aload_0        
        //   119: aload_1        
        //   120: aload           5
        //   122: aload_2        
        //   123: iconst_0       
        //   124: invokespecial   com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;Lcom/intellij/psi/PsiElement;Z)Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   127: astore          6
        //   129: aload           6
        //   131: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   136: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.fixAllSymbolsRecursively:(Lcom/intellij/psi/PsiElement;)V
        //   139: aload           6
        //   141: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   146: astore          7
        //   148: aload_0        
        //   149: aload_1        
        //   150: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.changeUsages:(Ljava/lang/String;)Z
        //   153: ifeq            357
        //   156: aload_0        
        //   157: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myOccurrences:[Lcom/intellij/psi/PsiElement;
        //   160: astore          8
        //   162: aload           8
        //   164: arraylength    
        //   165: istore          9
        //   167: iconst_0       
        //   168: istore          10
        //   170: iload           10
        //   172: iload           9
        //   174: if_icmpge       357
        //   177: aload           8
        //   179: iload           10
        //   181: aaload         
        //   182: astore          11
        //   184: aload           11
        //   186: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //   191: ifeq            351
        //   194: aload           11
        //   196: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseLocalConvertibleHandler.isDeclaratorIdentifier:(Lcom/intellij/psi/PsiElement;)Z
        //   199: ifeq            226
        //   202: goto            209
        //   205: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   208: athrow         
        //   209: aload           11
        //   211: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   216: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   219: goto            351
        //   222: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   225: athrow         
        //   226: aload           11
        //   228: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   231: ifeq            271
        //   234: aload           11
        //   236: new             Ljava/lang/StringBuilder;
        //   239: dup            
        //   240: invokespecial   java/lang/StringBuilder.<init>:()V
        //   243: ldc             "self."
        //   245: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   248: aload_1        
        //   249: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   252: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   255: aload           11
        //   257: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   260: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   263: pop            
        //   264: goto            351
        //   267: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   270: athrow         
        //   271: aload_0        
        //   272: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.isCreateFromUsageMode:()Z
        //   275: ifne            351
        //   278: aload_0        
        //   279: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myExtraOccurrenceMarker:Lcom/intellij/openapi/editor/RangeMarker;
        //   282: ifnull          351
        //   285: goto            292
        //   288: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   291: athrow         
        //   292: aload           7
        //   294: aload_0        
        //   295: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myExtraOccurrenceMarker:Lcom/intellij/openapi/editor/RangeMarker;
        //   298: invokeinterface com/intellij/openapi/editor/RangeMarker.getStartOffset:()I
        //   303: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   308: astore          12
        //   310: aload           12
        //   312: ifnull          351
        //   315: aload           12
        //   317: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   320: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   323: if_acmpne       351
        //   326: goto            333
        //   329: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   332: athrow         
        //   333: aload           12
        //   335: aload_1        
        //   336: aload_2        
        //   337: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.createIdentifier:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   340: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   343: pop            
        //   344: goto            351
        //   347: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   350: athrow         
        //   351: iinc            10, 1
        //   354: goto            170
        //   357: aload           6
        //   359: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   364: astore          8
        //   366: aload           8
        //   368: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   371: ifeq            710
        //   374: aload_0        
        //   375: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.mySynthesizeCB:Ljavax/swing/JCheckBox;
        //   378: ifnull          485
        //   381: goto            388
        //   384: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   387: athrow         
        //   388: aload_0        
        //   389: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.mySynthesizeCB:Ljavax/swing/JCheckBox;
        //   392: invokevirtual   javax/swing/JCheckBox.isSelected:()Z
        //   395: ifeq            485
        //   398: goto            405
        //   401: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   404: athrow         
        //   405: aload           8
        //   407: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   410: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   415: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   418: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getImplementation:()Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //   423: astore          9
        //   425: new             Lcom/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer$3;
        //   428: dup            
        //   429: aload_0        
        //   430: aload           9
        //   432: aload           8
        //   434: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   437: invokespecial   com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer$3.<init>:(Lcom/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer;Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;)V
        //   440: astore          10
        //   442: aload           10
        //   444: aload_0        
        //   445: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myProject:Lcom/intellij/openapi/project/Project;
        //   448: aload_0        
        //   449: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myEditor:Lcom/intellij/openapi/editor/Editor;
        //   452: aload           7
        //   454: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCSynthesizePropertyQuickFix.isAvailable:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Z
        //   457: ifeq            482
        //   460: aload           10
        //   462: aload_0        
        //   463: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myProject:Lcom/intellij/openapi/project/Project;
        //   466: aload_0        
        //   467: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myEditor:Lcom/intellij/openapi/editor/Editor;
        //   470: aload           7
        //   472: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCSynthesizePropertyQuickFix.invoke:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)V
        //   475: goto            482
        //   478: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   481: athrow         
        //   482: goto            634
        //   485: aload           7
        //   487: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsAutosynthesis:(Lcom/intellij/psi/PsiFile;)Z
        //   490: ifeq            634
        //   493: aload           7
        //   495: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcDisabled:(Lcom/intellij/psi/PsiFile;)Z
        //   498: ifeq            634
        //   501: goto            508
        //   504: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   507: athrow         
        //   508: aload           8
        //   510: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   515: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   518: ifeq            634
        //   521: goto            528
        //   524: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   527: athrow         
        //   528: aload           8
        //   530: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   533: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isRetained:()Z
        //   538: ifeq            634
        //   541: goto            548
        //   544: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   547: athrow         
        //   548: aload           8
        //   550: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   553: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getAssociatedIvar:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   558: astore          9
        //   560: aload           9
        //   562: ifnull          634
        //   565: aload_0        
        //   566: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myEditor:Lcom/intellij/openapi/editor/Editor;
        //   569: ifnull          605
        //   572: goto            579
        //   575: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   578: athrow         
        //   579: aload_0        
        //   580: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myProject:Lcom/intellij/openapi/project/Project;
        //   583: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   586: aload_0        
        //   587: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myEditor:Lcom/intellij/openapi/editor/Editor;
        //   590: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   595: invokevirtual   com/intellij/psi/PsiDocumentManager.doPostponedOperationsAndUnblockDocument:(Lcom/intellij/openapi/editor/Document;)V
        //   598: goto            605
        //   601: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   604: athrow         
        //   605: new             Lcom/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction;
        //   608: dup            
        //   609: aload           9
        //   611: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   614: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.<init>:(Ljava/util/List;)V
        //   617: astore          10
        //   619: aload           10
        //   621: aload_0        
        //   622: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myProject:Lcom/intellij/openapi/project/Project;
        //   625: aload_0        
        //   626: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myEditor:Lcom/intellij/openapi/editor/Editor;
        //   629: aload           7
        //   631: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.invoke:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)V
        //   634: aload_0        
        //   635: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myPutToPrivateCategoryCB:Ljavax/swing/JCheckBox;
        //   638: ifnull          710
        //   641: aload_0        
        //   642: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myPutToPrivateCategoryCB:Ljavax/swing/JCheckBox;
        //   645: invokevirtual   javax/swing/JCheckBox.isSelected:()Z
        //   648: ifeq            710
        //   651: goto            658
        //   654: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   657: athrow         
        //   658: new             Lcom/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer$4;
        //   661: dup            
        //   662: aload_0        
        //   663: aload           8
        //   665: invokespecial   com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer$4.<init>:(Lcom/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   668: astore          9
        //   670: aload           9
        //   672: aload_0        
        //   673: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myProject:Lcom/intellij/openapi/project/Project;
        //   676: aload_0        
        //   677: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myEditor:Lcom/intellij/openapi/editor/Editor;
        //   680: aload           7
        //   682: invokevirtual   com/jetbrains/cidr/lang/intentions/OCMoveToPrivateCategoryIntentionAction.isAvailable:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Z
        //   685: ifeq            710
        //   688: aload           9
        //   690: aload_0        
        //   691: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myProject:Lcom/intellij/openapi/project/Project;
        //   694: aload_0        
        //   695: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.myEditor:Lcom/intellij/openapi/editor/Editor;
        //   698: aload           7
        //   700: invokevirtual   com/jetbrains/cidr/lang/intentions/OCMoveToPrivateCategoryIntentionAction.invoke:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)V
        //   703: goto            710
        //   706: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   709: athrow         
        //   710: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  45     60     60     64     Ljava/lang/IllegalStateException;
        //  66     79     79     83     Ljava/lang/IllegalStateException;
        //  93     106    109    113    Ljava/lang/IllegalStateException;
        //  98     114    114    118    Ljava/lang/IllegalStateException;
        //  184    202    205    209    Ljava/lang/IllegalStateException;
        //  194    222    222    226    Ljava/lang/IllegalStateException;
        //  226    267    267    271    Ljava/lang/IllegalStateException;
        //  271    285    288    292    Ljava/lang/IllegalStateException;
        //  310    326    329    333    Ljava/lang/IllegalStateException;
        //  315    344    347    351    Ljava/lang/IllegalStateException;
        //  366    381    384    388    Ljava/lang/IllegalStateException;
        //  374    398    401    405    Ljava/lang/IllegalStateException;
        //  442    475    478    482    Ljava/lang/IllegalStateException;
        //  485    501    504    508    Ljava/lang/IllegalStateException;
        //  493    521    524    528    Ljava/lang/IllegalStateException;
        //  508    541    544    548    Ljava/lang/IllegalStateException;
        //  560    572    575    579    Ljava/lang/IllegalStateException;
        //  565    598    601    605    Ljava/lang/IllegalStateException;
        //  634    651    654    658    Ljava/lang/IllegalStateException;
        //  670    703    706    710    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0508:
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
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.introduceProperty";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return s;
    }
    
    public PsiElement getPreviewElement(final OCDeclarator ocDeclarator) {
        return ocDeclarator.getParent().getParent();
    }
    
    static {
        PROPERTY_SEMANTICS = new OCEnumComboOption<OCPropertySymbol.PropertySemantics>("Semantics", OCPropertySymbol.PropertySemantics.values(), new String[] { "default", "strong", "weak", "assign", "retain", "copy" });
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
