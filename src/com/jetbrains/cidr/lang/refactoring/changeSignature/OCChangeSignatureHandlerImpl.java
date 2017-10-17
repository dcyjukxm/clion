// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.intellij.refactoring.changeSignature.ParameterTableModelBase;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.intellij.psi.PsiCodeFragment;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Iterator;
import com.intellij.refactoring.changeSignature.ParameterTableModelItemBase;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCType;

class OCChangeSignatureHandlerImpl implements OCChangeSignatureHandler
{
    protected OCParameterTableModel myParametersTableModel;
    protected OCMethodDescriptor myMethod;
    private String myName;
    protected String myReturnTypeText;
    protected OCType myReturnType;
    protected OCCallableKind myCallableKind;
    protected OCChangeInfo myChangeInfo;
    protected boolean myChangeUsages;
    protected boolean myChangeAncestors;
    protected OCGeneratedInfo myGeneratedInfo;
    protected String myTitle;
    protected String myHelpId;
    private PsiElement myContext;
    
    OCChangeSignatureHandlerImpl(final OCParameterTableModel myParametersTableModel, final OCMethodDescriptor myMethod, final PsiElement myContext) {
        this.myChangeUsages = true;
        this.myParametersTableModel = myParametersTableModel;
        this.myMethod = myMethod;
        this.myName = this.myMethod.getName();
        this.myReturnType = this.myMethod.getReturnType();
        this.myReturnTypeText = this.myMethod.getReturnTypeText(myContext);
        this.myCallableKind = this.myMethod.getCallableKind();
        this.myContext = myContext;
        this.myGeneratedInfo = new OCGeneratedInfo(myContext.getProject());
    }
    
    OCChangeSignatureHandlerImpl(final OCMethodDescriptor myMethod, final PsiElement myContext) {
        this.myChangeUsages = true;
        final OCCallable method = myMethod.getMethod();
        this.myMethod = myMethod;
        this.myName = this.myMethod.getName();
        this.myReturnType = this.myMethod.getReturnType();
        this.myReturnTypeText = this.myMethod.getReturnTypeText(myContext);
        this.myCallableKind = this.myMethod.getCallableKind();
        ((ParameterTableModelBase<OCParameterInfo, TableItem>)(this.myParametersTableModel = new OCParameterTableModel(myContext, method instanceof OCMethod))).setParameterInfos(myMethod.getParameters());
        this.myContext = myContext;
        this.myGeneratedInfo = new OCGeneratedInfo(myContext.getProject());
        if (ApplicationManager.getApplication().isUnitTestMode()) {
            final OCParameterInfo a = this.a();
            if (a != null) {
                final String selector = a.getSelector();
                Label_0164: {
                    try {
                        if (selector == null) {
                            return;
                        }
                        final String s = selector;
                        final String s2 = "_";
                        final boolean b = s.startsWith(s2);
                        if (b) {
                            break Label_0164;
                        }
                        return;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final String s = selector;
                        final String s2 = "_";
                        final boolean b = s.startsWith(s2);
                        if (b) {
                            this.renameSelector(0, selector.substring(1));
                            this.myName = this.myName.substring(1);
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
            }
        }
    }
    
    @Override
    public OCParameterInfo addParameter(final String s, final OCType ocType, final int n) {
        return this.addParameter("", s, ocType, n, false);
    }
    
    @Override
    public OCParameterInfo addParameter(final String s, final String s2, final OCType ocType, final int n, final boolean b) {
        return this.insertParameter(s, s2, ocType, -1, n, b);
    }
    
    @Override
    public OCParameterInfo insertParameter(final String s, final String s2, final OCType ocType, final int n, final int n2, final boolean b) {
        return this.insertParameter(s, s2, ocType, null, n, n2, b);
    }
    
    @Override
    public OCParameterInfo insertParameter(String selector, final String s, final OCType ocType, final String s2, final int n, final int n2, final boolean referenceMode) {
        OCParameterInfo ocParameterInfo = null;
        Label_0096: {
            Label_0050: {
                if (this.isMethod()) {
                    final OCParameterInfo a = this.a();
                    try {
                        if (a == null || !a.getName().isEmpty()) {
                            break Label_0050;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    selector = a.getSelector();
                    this.myParametersTableModel.removeRow(0);
                }
                try {
                    if (s2 == null) {
                        ocParameterInfo = new OCParameterInfo(selector, s, ocType, n2, this.myContext);
                        break Label_0096;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            ocParameterInfo = new OCParameterInfo(selector, s, ocType, s2, n2, this.myContext);
        }
        final OCParameterInfo ocParameterInfo2 = ocParameterInfo;
        try {
            ocParameterInfo2.setReferenceMode(referenceMode);
            if (n == -1) {
                this.myParametersTableModel.addLastRow(ocParameterInfo2);
                return ocParameterInfo2;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        this.myParametersTableModel.addRow(ocParameterInfo2, n);
        return ocParameterInfo2;
    }
    
    @Override
    public OCParameterInfo insertParameter(final String s, final OCType ocType, final int n) {
        return this.insertParameter("", s, ocType, n, -1, false);
    }
    
    @Override
    public void removeParameter(final int n) {
        this.a(n, true);
    }
    
    private void a(final int n, final boolean b) {
        Label_0079: {
            if (this.isMethod()) {
                final OCParameterInfo a = this.a();
                Label_0034: {
                    try {
                        if (this.myParametersTableModel.getRowCount() != 1) {
                            break Label_0079;
                        }
                        final OCParameterInfo ocParameterInfo = a;
                        if (ocParameterInfo != null) {
                            break Label_0034;
                        }
                        break Label_0079;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final OCParameterInfo ocParameterInfo = a;
                        if (ocParameterInfo == null) {
                            break Label_0079;
                        }
                        if (a.getSelector().isEmpty()) {
                            break Label_0079;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                final String selector = a.getSelector();
                this.myParametersTableModel.removeRow(n, b);
                this.addParameter(selector, "", null, -1, false);
                return;
            }
        }
        this.myParametersTableModel.removeRow(n, b);
    }
    
    @Override
    public void removeParameter(final String s, final boolean b) {
        int n = 0;
        for (final ParameterTableModelItemBase parameterTableModelItemBase : this.myParametersTableModel.getItems()) {
            try {
                if (((OCParameterInfo)parameterTableModelItemBase.parameter).getName().equals(s)) {
                    this.a(n, b);
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            ++n;
        }
        try {
            assert false : "Could not find parameter: " + s;
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    @Override
    public void exchangeParameters(final int n, final int n2) {
        this.myParametersTableModel.exchangeRows(n, n2);
    }
    
    @Override
    public void renameSelector(final int n, final String selector) {
        ((OCParameterInfo)this.myParametersTableModel.getItems().get(n).parameter).setSelector(selector);
        this.myName = this.calculateMethodName();
    }
    
    @Override
    public void setName(final String myName) {
        this.myName = myName;
        final OCParameterInfo a = this.a();
        Label_0076: {
            Label_0055: {
                Label_0028: {
                    try {
                        if (!this.isMethod()) {
                            break Label_0055;
                        }
                        final OCParameterInfo ocParameterInfo = a;
                        if (ocParameterInfo == null) {
                            break Label_0028;
                        }
                        break Label_0028;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final OCParameterInfo ocParameterInfo = a;
                        if (ocParameterInfo == null) {
                            this.addParameter(myName, "", null, -1, false);
                            return;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                this.renameSelector(0, myName);
                return;
                try {
                    if (a == null) {
                        return;
                    }
                    final OCParameterInfo ocParameterInfo2 = a;
                    final String s = ocParameterInfo2.getSelector();
                    final boolean b = s.isEmpty();
                    if (!b) {
                        break Label_0076;
                    }
                    return;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            try {
                final OCParameterInfo ocParameterInfo2 = a;
                final String s = ocParameterInfo2.getSelector();
                final boolean b = s.isEmpty();
                if (!b) {
                    this.myParametersTableModel.removeRow(0);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
    }
    
    @Override
    public void setReturnType(final OCType myReturnType) {
        this.myReturnType = myReturnType;
        this.myReturnTypeText = myReturnType.getBestNameInContext(this.myContext);
    }
    
    @Override
    public void setParentClass(final OCSymbol ocSymbol, final boolean b, final List<? extends OCClassSymbol> list) {
        this.getGeneratedInfo().setMethodParent(ocSymbol, b, list);
    }
    
    @Override
    public void setChangeParentClassPossible(final boolean b) {
    }
    
    @Override
    public void setNameVisible(final boolean b) {
    }
    
    @Override
    public void setChangeUsages(final boolean myChangeUsages) {
        this.myChangeUsages = myChangeUsages;
    }
    
    @Override
    public void setChangeAncestors(final boolean myChangeAncestors) {
        this.myChangeAncestors = myChangeAncestors;
    }
    
    @Override
    public OCGeneratedInfo getGeneratedInfo() {
        return this.myGeneratedInfo;
    }
    
    @Override
    public void invoke() {
        this.createRefactoringProcessor().run();
    }
    
    @Override
    public void invokeSynchronously() {
        this.createRefactoringProcessor().runSynchronously();
    }
    
    @Override
    public void setTitle(final String myTitle) {
        this.myTitle = myTitle;
    }
    
    @Override
    public void setHelpId(final String myHelpId) {
        this.myHelpId = myHelpId;
    }
    
    @Override
    public String getNewSignature() {
        return this.calculateSignature(null, false);
    }
    
    @Override
    public void setCallableKind(final OCCallableKind p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: putfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myCallableKind:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //     5: aload_0        
        //     6: invokespecial   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //     9: astore_2       
        //    10: aload_1        
        //    11: getstatic       com/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind.METHOD:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //    14: if_acmpne       25
        //    17: iconst_1       
        //    18: goto            26
        //    21: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    24: athrow         
        //    25: iconst_0       
        //    26: istore_3       
        //    27: aload_2        
        //    28: ifnull          71
        //    31: aload_2        
        //    32: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.isSelfParameter:()Z
        //    35: ifeq            71
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: iload_3        
        //    46: ifeq            71
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: aload_0        
        //    57: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myParametersTableModel:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterTableModel;
        //    60: iconst_0       
        //    61: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterTableModel.removeRow:(I)V
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: iload_3        
        //    72: ifeq            113
        //    75: aload_0        
        //    76: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //    79: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getMethod:()Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //    82: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    85: ifeq            113
        //    88: goto            95
        //    91: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    94: athrow         
        //    95: aload_0        
        //    96: aload_0        
        //    97: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //   100: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getName:()Ljava/lang/String;
        //   103: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.setName:(Ljava/lang/String;)V
        //   106: goto            198
        //   109: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: iload_3        
        //   114: ifne            198
        //   117: aload_0        
        //   118: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //   121: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getMethod:()Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   124: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   127: ifeq            198
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   136: athrow         
        //   137: aload_0        
        //   138: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myParametersTableModel:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterTableModel;
        //   141: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterTableModel.getRowCount:()I
        //   144: iconst_1       
        //   145: if_icmpne       198
        //   148: goto            155
        //   151: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   154: athrow         
        //   155: aload_2        
        //   156: ifnull          198
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: aload_2        
        //   167: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getName:()Ljava/lang/String;
        //   170: invokevirtual   java/lang/String.isEmpty:()Z
        //   173: ifeq            198
        //   176: goto            183
        //   179: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   182: athrow         
        //   183: aload_0        
        //   184: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myParametersTableModel:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterTableModel;
        //   187: iconst_0       
        //   188: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterTableModel.removeRow:(I)V
        //   191: goto            198
        //   194: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   197: athrow         
        //   198: iload_3        
        //   199: ifne            272
        //   202: aload_0        
        //   203: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //   206: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getSelfReferences:()Ljava/util/List;
        //   209: invokeinterface java/util/List.isEmpty:()Z
        //   214: ifne            272
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   223: athrow         
        //   224: aload_2        
        //   225: ifnull          249
        //   228: goto            235
        //   231: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   234: athrow         
        //   235: aload_2        
        //   236: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.isSelfParameter:()Z
        //   239: ifne            272
        //   242: goto            249
        //   245: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: aload_0        
        //   250: aload_0        
        //   251: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //   254: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getContainerClass:()Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   257: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getName:()Ljava/lang/String;
        //   262: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.addSelfParameter:(Ljava/lang/String;)V
        //   265: goto            272
        //   268: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   271: athrow         
        //   272: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  10     21     21     25     Ljava/lang/IllegalArgumentException;
        //  27     38     41     45     Ljava/lang/IllegalArgumentException;
        //  31     49     52     56     Ljava/lang/IllegalArgumentException;
        //  45     64     67     71     Ljava/lang/IllegalArgumentException;
        //  71     88     91     95     Ljava/lang/IllegalArgumentException;
        //  75     109    109    113    Ljava/lang/IllegalArgumentException;
        //  113    130    133    137    Ljava/lang/IllegalArgumentException;
        //  117    148    151    155    Ljava/lang/IllegalArgumentException;
        //  137    159    162    166    Ljava/lang/IllegalArgumentException;
        //  155    176    179    183    Ljava/lang/IllegalArgumentException;
        //  166    191    194    198    Ljava/lang/IllegalArgumentException;
        //  198    217    220    224    Ljava/lang/IllegalArgumentException;
        //  202    228    231    235    Ljava/lang/IllegalArgumentException;
        //  224    242    245    249    Ljava/lang/IllegalArgumentException;
        //  235    265    268    272    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0045:
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
    public void addSelfParameter(final String s) {
        final OCParameterInfo ocParameterInfo = new OCParameterInfo("", "_self", OCPointerType.to(OCReferenceType.resolvedFromText(s, this.myMethod.getMethod().getContainingFile())), -1, this.myContext);
        ocParameterInfo.setSelfParameter(true);
        this.myParametersTableModel.addFirstRow(ocParameterInfo);
    }
    
    @Nullable
    private OCParameterInfo a() {
        final int rowCount = this.myParametersTableModel.getRowCount();
        try {
            if (rowCount > 0) {
                return (OCParameterInfo)((ParameterTableModelItemBase)this.myParametersTableModel.getRowValue(0)).parameter;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    public List<OCParameterInfo> getParameters() {
        final ArrayList<Object> list = new ArrayList<Object>(this.myParametersTableModel.getRowCount());
        for (final ParameterTableModelItemBase parameterTableModelItemBase : this.myParametersTableModel.getItems()) {
            ((OCParameterInfo)parameterTableModelItemBase.parameter).setType(OCElementUtil.getType((PsiElement)parameterTableModelItemBase.typeCodeFragment));
            ((OCParameterInfo)parameterTableModelItemBase.parameter).setTypeText(OCElementUtil.getTextWithMacros((PsiElement)parameterTableModelItemBase.typeCodeFragment));
            list.add(parameterTableModelItemBase.parameter);
        }
        return (List<OCParameterInfo>)list;
    }
    
    OCType getReturnType() {
        return this.myReturnType;
    }
    
    String getReturnTypeText() {
        return this.myReturnTypeText;
    }
    
    String getMethodName() {
        final String myName = this.myName;
        try {
            if (this.myMethod.getMethod() instanceof OCBlockExpression) {
                return "block";
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0049: {
            try {
                if (!this.isFunction()) {
                    return myName;
                }
                final String s = myName;
                final int n = 58;
                final int n2 = s.indexOf(n);
                final int n3 = -1;
                if (n2 == n3) {
                    break Label_0049;
                }
                return myName.substring(0, myName.indexOf(58));
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final String s = myName;
                final int n = 58;
                final int n2 = s.indexOf(n);
                final int n3 = -1;
                if (n2 == n3) {
                    return myName;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return myName.substring(0, myName.indexOf(58));
    }
    
    String calculateMethodName() {
        final StringBuilder sb = new StringBuilder();
        final List items = this.myParametersTableModel.getItems();
        for (final ParameterTableModelItemBase parameterTableModelItemBase : items) {
            Label_0093: {
                try {
                    sb.append(((OCParameterInfo)parameterTableModelItemBase.parameter).getSelector());
                    if (items.size() != 1) {
                        break Label_0093;
                    }
                    final ParameterTableModelItemBase parameterTableModelItemBase2 = parameterTableModelItemBase;
                    final P p = parameterTableModelItemBase2.parameter;
                    final OCParameterInfo ocParameterInfo = (OCParameterInfo)p;
                    final String s = ocParameterInfo.getName();
                    final boolean b = s.isEmpty();
                    if (b) {
                        break Label_0093;
                    }
                    break Label_0093;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final ParameterTableModelItemBase parameterTableModelItemBase2 = parameterTableModelItemBase;
                    final P p = parameterTableModelItemBase2.parameter;
                    final OCParameterInfo ocParameterInfo = (OCParameterInfo)p;
                    final String s = ocParameterInfo.getName();
                    final boolean b = s.isEmpty();
                    if (b) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            sb.append(':');
        }
        return sb.toString();
    }
    
    @Nullable
    OCClassDeclaration getContainerClass() {
        try {
            if (this.myCallableKind == OCCallableKind.METHOD) {
                return this.myMethod.getContainerClass();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    OCChangeSignatureProcessor createRefactoringProcessor() {
        return new OCChangeSignatureProcessor(this.myMethod.getMethod().getProject(), this.getCallableKind(), this.myMethod.getMethod(), this.b(), this.myTitle);
    }
    
    @Override
    public OCChangeInfo getChangeInfo() {
        try {
            if (this.myChangeInfo == null) {
                this.b();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myChangeInfo;
    }
    
    private OCChangeInfo b() {
        final List<OCParameterInfo> parameters = this.getParameters();
        return this.myChangeInfo = new OCChangeInfo(this.myContext, this.myMethod, this.getMethodName(), (OCParameterInfo[])parameters.toArray(new OCParameterInfo[parameters.size()]), this.getReturnTypeText(), this.getContainerClass(), this.getCallableKind(), this.calculateSignature(null, false), this.myChangeUsages, this.myChangeAncestors, this.myGeneratedInfo) {
            @Override
            public String getNewInheritedSignature(final OCCallable ocCallable) {
                return OCChangeSignatureHandlerImpl.this.calculateSignature(ocCallable, false);
            }
        };
    }
    
    OCCallableKind getCallableKind() {
        return this.myCallableKind;
    }
    
    @Override
    public List<OCCallable> getNewCallables() {
        try {
            if (this.myChangeInfo != null) {
                final List<OCCallable> list = this.myChangeInfo.getNewMethods();
                return (List<OCCallable>)list;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<OCCallable> list = Collections.emptyList();
        return (List<OCCallable>)list;
    }
    
    String calculateSignature(@Nullable final OCCallable p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuilder.<init>:()V
        //     7: astore_3       
        //     8: aload_0        
        //     9: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myParametersTableModel:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterTableModel;
        //    12: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterTableModel.getItems:()Ljava/util/List;
        //    15: astore          4
        //    17: aload_0        
        //    18: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //    21: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getMethod:()Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //    24: astore          5
        //    26: aload_1        
        //    27: ifnull          38
        //    30: aload_1        
        //    31: goto            42
        //    34: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: aload_0        
        //    39: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myContext:Lcom/intellij/psi/PsiElement;
        //    42: astore          6
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.isMethod:()Z
        //    48: ifeq            367
        //    51: aload           5
        //    53: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    56: ifeq            94
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    65: athrow         
        //    66: aload           5
        //    68: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    71: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.isInstanceMethod:()Z
        //    76: ifeq            94
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: iconst_1       
        //    87: goto            95
        //    90: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: iconst_0       
        //    95: istore          7
        //    97: aload_3        
        //    98: iload           7
        //   100: ifeq            112
        //   103: bipush          45
        //   105: goto            114
        //   108: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: bipush          43
        //   114: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   117: pop            
        //   118: aload_3        
        //   119: bipush          40
        //   121: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   124: pop            
        //   125: aload_3        
        //   126: aload_0        
        //   127: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.getReturnTypeText:()Ljava/lang/String;
        //   130: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   133: pop            
        //   134: aload_3        
        //   135: bipush          41
        //   137: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   140: pop            
        //   141: iconst_1       
        //   142: istore          8
        //   144: aload           4
        //   146: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   151: astore          9
        //   153: aload           9
        //   155: invokeinterface java/util/Iterator.hasNext:()Z
        //   160: ifeq            315
        //   163: aload           9
        //   165: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   170: checkcast       Lcom/intellij/refactoring/changeSignature/ParameterTableModelItemBase;
        //   173: astore          10
        //   175: aload_3        
        //   176: iload           8
        //   178: ifne            192
        //   181: iload_2        
        //   182: ifne            201
        //   185: goto            192
        //   188: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   191: athrow         
        //   192: bipush          32
        //   194: goto            203
        //   197: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   200: athrow         
        //   201: bipush          10
        //   203: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   206: pop            
        //   207: iconst_0       
        //   208: istore          8
        //   210: aload           10
        //   212: getfield        com/intellij/refactoring/changeSignature/ParameterTableModelItemBase.parameter:Ljava/lang/Object;
        //   215: checkcast       Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   218: astore          11
        //   220: aload_3        
        //   221: aload           11
        //   223: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getSelector:()Ljava/lang/String;
        //   226: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   229: pop            
        //   230: aload           11
        //   232: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getName:()Ljava/lang/String;
        //   235: invokevirtual   java/lang/String.isEmpty:()Z
        //   238: ifeq            248
        //   241: goto            315
        //   244: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   247: athrow         
        //   248: aload_3        
        //   249: ldc             ": ("
        //   251: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   254: pop            
        //   255: aload_3        
        //   256: aload           10
        //   258: getfield        com/intellij/refactoring/changeSignature/ParameterTableModelItemBase.typeCodeFragment:Lcom/intellij/psi/PsiCodeFragment;
        //   261: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTextWithMacros:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   264: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   267: pop            
        //   268: aload_3        
        //   269: ldc             ") "
        //   271: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   274: pop            
        //   275: aload_1        
        //   276: ifnull          302
        //   279: aload_3        
        //   280: aload_0        
        //   281: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myChangeInfo:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;
        //   284: iconst_1       
        //   285: aload_1        
        //   286: aload           11
        //   288: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewParameterName:(ZLcom/jetbrains/cidr/lang/psi/OCCallable;Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;)Ljava/lang/String;
        //   291: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   294: pop            
        //   295: goto            312
        //   298: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   301: athrow         
        //   302: aload_3        
        //   303: aload           11
        //   305: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getName:()Ljava/lang/String;
        //   308: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   311: pop            
        //   312: goto            153
        //   315: aload_3        
        //   316: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   319: aload           6
        //   321: iconst_0       
        //   322: iconst_0       
        //   323: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.methodFromSignature:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;ZZ)Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   326: astore          9
        //   328: aload           6
        //   330: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   335: invokestatic    com/intellij/psi/codeStyle/CodeStyleManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleManager;
        //   338: aload           9
        //   340: invokevirtual   com/intellij/psi/codeStyle/CodeStyleManager.reformat:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   343: pop            
        //   344: aload           9
        //   346: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getTextWithMacros:()Ljava/lang/String;
        //   351: astore          10
        //   353: aload           10
        //   355: iconst_0       
        //   356: aload           10
        //   358: invokevirtual   java/lang/String.length:()I
        //   361: iconst_1       
        //   362: isub           
        //   363: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   366: areturn        
        //   367: aload_0        
        //   368: aload_0        
        //   369: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myChangeInfo:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;
        //   372: aload           4
        //   374: aload_1        
        //   375: aload           6
        //   377: aload_1        
        //   378: ifnull          389
        //   381: iconst_1       
        //   382: goto            390
        //   385: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   388: athrow         
        //   389: iconst_0       
        //   390: iload_2        
        //   391: invokespecial   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;Ljava/util/List;Lcom/jetbrains/cidr/lang/psi/OCCallable;Lcom/intellij/psi/PsiElement;ZZ)Ljava/lang/StringBuilder;
        //   394: astore          7
        //   396: aload_0        
        //   397: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.isFunction:()Z
        //   400: ifeq            729
        //   403: aload_0        
        //   404: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.getMethodName:()Ljava/lang/String;
        //   407: astore          8
        //   409: aload           8
        //   411: invokevirtual   java/lang/String.isEmpty:()Z
        //   414: ifeq            421
        //   417: ldc             "unnamed"
        //   419: astore          8
        //   421: aload           8
        //   423: astore          9
        //   425: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   428: astore          10
        //   430: aload           5
        //   432: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   435: ifeq            549
        //   438: aload           5
        //   440: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   443: astore          11
        //   445: aload_0        
        //   446: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //   449: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getMethodSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   452: astore          12
        //   454: aload           11
        //   456: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.isStatic:()Z
        //   461: ifne            497
        //   464: aload           12
        //   466: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   469: ifeq            504
        //   472: goto            479
        //   475: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   478: athrow         
        //   479: aload           12
        //   481: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   484: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.resolveIsStatic:()Z
        //   487: ifeq            504
        //   490: goto            497
        //   493: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   496: athrow         
        //   497: ldc             "static"
        //   499: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   502: astore          10
        //   504: aload           11
        //   506: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //   511: ifnull          549
        //   514: new             Ljava/lang/StringBuilder;
        //   517: dup            
        //   518: invokespecial   java/lang/StringBuilder.<init>:()V
        //   521: aload           11
        //   523: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //   528: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTextWithMacros:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   531: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   534: ldc             "::"
        //   536: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   539: aload           8
        //   541: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   544: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   547: astore          9
        //   549: aload_0        
        //   550: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //   553: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.isConstructor:()Z
        //   556: ifeq            573
        //   559: aload_3        
        //   560: aload           9
        //   562: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   565: pop            
        //   566: goto            597
        //   569: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   572: athrow         
        //   573: aload_3        
        //   574: aload           10
        //   576: aload           9
        //   578: aload_0        
        //   579: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   582: aconst_null    
        //   583: aload           6
        //   585: aload_0        
        //   586: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.getReturnTypeText:()Ljava/lang/String;
        //   589: iconst_0       
        //   590: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.declarationText:(Ljava/util/List;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;Lcom/intellij/psi/PsiElement;Ljava/lang/String;Z)Ljava/lang/String;
        //   593: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   596: pop            
        //   597: aload_3        
        //   598: aload_3        
        //   599: aload           8
        //   601: invokevirtual   java/lang/StringBuilder.lastIndexOf:(Ljava/lang/String;)I
        //   604: aload           8
        //   606: invokevirtual   java/lang/String.length:()I
        //   609: iadd           
        //   610: aload           7
        //   612: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   615: invokevirtual   java/lang/StringBuilder.insert:(ILjava/lang/String;)Ljava/lang/StringBuilder;
        //   618: pop            
        //   619: aload_3        
        //   620: ldc             ";"
        //   622: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   625: pop            
        //   626: aload_0        
        //   627: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.myMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //   630: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.isConstructor:()Z
        //   633: ifeq            652
        //   636: aload_3        
        //   637: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   640: aload           6
        //   642: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.constructorFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   645: goto            661
        //   648: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   651: athrow         
        //   652: aload_3        
        //   653: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   656: aload           6
        //   658: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.topLevelDeclarationFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   661: astore          11
        //   663: aload           6
        //   665: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   670: invokestatic    com/intellij/psi/codeStyle/CodeStyleManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleManager;
        //   673: aload           11
        //   675: invokevirtual   com/intellij/psi/codeStyle/CodeStyleManager.reformat:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   678: pop            
        //   679: aload_3        
        //   680: iconst_0       
        //   681: invokevirtual   java/lang/StringBuilder.setLength:(I)V
        //   684: aload_3        
        //   685: aload           11
        //   687: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTextWithMacros:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   690: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   693: pop            
        //   694: aload_3        
        //   695: aload_3        
        //   696: invokevirtual   java/lang/StringBuilder.length:()I
        //   699: iconst_1       
        //   700: isub           
        //   701: invokevirtual   java/lang/StringBuilder.charAt:(I)C
        //   704: bipush          59
        //   706: if_icmpne       726
        //   709: aload_3        
        //   710: aload_3        
        //   711: invokevirtual   java/lang/StringBuilder.length:()I
        //   714: iconst_1       
        //   715: isub           
        //   716: invokevirtual   java/lang/StringBuilder.setLength:(I)V
        //   719: goto            726
        //   722: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   725: athrow         
        //   726: goto            828
        //   729: aload_0        
        //   730: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.isBlock:()Z
        //   733: ifeq            828
        //   736: aload_3        
        //   737: bipush          94
        //   739: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   742: pop            
        //   743: ldc             "void"
        //   745: aload_0        
        //   746: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.getReturnTypeText:()Ljava/lang/String;
        //   749: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   752: ifne            778
        //   755: goto            762
        //   758: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   761: athrow         
        //   762: aload_3        
        //   763: aload_0        
        //   764: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.getReturnTypeText:()Ljava/lang/String;
        //   767: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   770: pop            
        //   771: goto            778
        //   774: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   777: athrow         
        //   778: aload_3        
        //   779: aload           7
        //   781: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;
        //   784: pop            
        //   785: aload_3        
        //   786: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   789: aload           6
        //   791: iconst_0       
        //   792: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Z)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   795: astore          8
        //   797: aload           6
        //   799: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   804: invokestatic    com/intellij/psi/codeStyle/CodeStyleManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleManager;
        //   807: aload           8
        //   809: invokevirtual   com/intellij/psi/codeStyle/CodeStyleManager.reformat:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   812: pop            
        //   813: aload_3        
        //   814: iconst_0       
        //   815: invokevirtual   java/lang/StringBuilder.setLength:(I)V
        //   818: aload_3        
        //   819: aload           8
        //   821: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTextWithMacros:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   824: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   827: pop            
        //   828: aload           5
        //   830: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   833: ifeq            857
        //   836: aload           5
        //   838: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   841: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   846: aload_3        
        //   847: invokevirtual   com/jetbrains/cidr/lang/types/CVQualifiers.appendCVQualifiers:(Ljava/lang/StringBuilder;)V
        //   850: goto            857
        //   853: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   856: athrow         
        //   857: aload_0        
        //   858: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.isBlock:()Z
        //   861: ifeq            878
        //   864: aload_3        
        //   865: ldc             "{...}"
        //   867: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   870: pop            
        //   871: goto            878
        //   874: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   877: athrow         
        //   878: aload_3        
        //   879: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   882: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  26     34     34     38     Ljava/lang/IllegalArgumentException;
        //  44     59     62     66     Ljava/lang/IllegalArgumentException;
        //  51     79     82     86     Ljava/lang/IllegalArgumentException;
        //  66     90     90     94     Ljava/lang/IllegalArgumentException;
        //  97     108    108    112    Ljava/lang/IllegalArgumentException;
        //  175    185    188    192    Ljava/lang/IllegalArgumentException;
        //  181    197    197    201    Ljava/lang/IllegalArgumentException;
        //  220    244    244    248    Ljava/lang/IllegalArgumentException;
        //  248    298    298    302    Ljava/lang/IllegalArgumentException;
        //  367    385    385    389    Ljava/lang/IllegalArgumentException;
        //  454    472    475    479    Ljava/lang/IllegalArgumentException;
        //  464    490    493    497    Ljava/lang/IllegalArgumentException;
        //  549    569    569    573    Ljava/lang/IllegalArgumentException;
        //  597    648    648    652    Ljava/lang/IllegalArgumentException;
        //  663    719    722    726    Ljava/lang/IllegalArgumentException;
        //  729    755    758    762    Ljava/lang/IllegalArgumentException;
        //  736    771    774    778    Ljava/lang/IllegalArgumentException;
        //  828    850    853    857    Ljava/lang/IllegalArgumentException;
        //  857    871    874    878    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0066:
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
    
    private StringBuilder a(@Nullable final OCChangeInfo ocChangeInfo, @NotNull final List<ParameterTableModelItemBase<OCParameterInfo>> list, @Nullable final OCCallable ocCallable, @NotNull final PsiElement psiElement, final boolean b, final boolean b2) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl", "buildParams"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandlerImpl", "buildParams"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        int n = 1;
        final StringBuilder sb = new StringBuilder();
        Label_0124: {
            try {
                if (list.size() > 0) {
                    break Label_0124;
                }
                final OCChangeSignatureHandlerImpl ocChangeSignatureHandlerImpl = this;
                final boolean b3 = ocChangeSignatureHandlerImpl.isFunction();
                if (b3) {
                    break Label_0124;
                }
                break Label_0124;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final OCChangeSignatureHandlerImpl ocChangeSignatureHandlerImpl = this;
                final boolean b3 = ocChangeSignatureHandlerImpl.isFunction();
                if (b3) {
                    sb.append('(');
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        for (final ParameterTableModelItemBase<OCParameterInfo> parameterTableModelItemBase : list) {
            String s = null;
            Label_0217: {
                Label_0184: {
                    try {
                        if (ocChangeInfo == null) {
                            break Label_0184;
                        }
                        final OCCallable ocCallable2 = ocCallable;
                        if (ocCallable2 != null) {
                            break Label_0184;
                        }
                        break Label_0184;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    try {
                        final OCCallable ocCallable2 = ocCallable;
                        if (ocCallable2 != null) {
                            s = ocChangeInfo.getNewParameterName(b, ocCallable, parameterTableModelItemBase.parameter);
                            break Label_0217;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
                s = parameterTableModelItemBase.parameter.getName();
            }
            final String s2 = s;
            final PsiCodeFragment typeCodeFragment = parameterTableModelItemBase.typeCodeFragment;
            final String textWithMacros = OCElementUtil.getTextWithMacros((PsiElement)typeCodeFragment);
            Label_0347: {
                Label_0302: {
                    StringBuilder sb3 = null;
                    char c = '\0';
                    Label_0298: {
                        Label_0287: {
                            Label_0263: {
                                Label_0256: {
                                    try {
                                        if (!s2.isEmpty()) {
                                            break Label_0263;
                                        }
                                        final String s3 = textWithMacros;
                                        final boolean b4 = s3.isEmpty();
                                        if (b4) {
                                            break Label_0256;
                                        }
                                        break Label_0263;
                                    }
                                    catch (IllegalArgumentException ex7) {
                                        throw a(ex7);
                                    }
                                    try {
                                        final String s3 = textWithMacros;
                                        final boolean b4 = s3.isEmpty();
                                        if (b4) {
                                            continue;
                                        }
                                    }
                                    catch (IllegalArgumentException ex8) {
                                        throw a(ex8);
                                    }
                                }
                                try {
                                    if (n != 0) {
                                        break Label_0302;
                                    }
                                    final StringBuilder sb2 = sb;
                                    final String s4 = ",";
                                    sb3 = sb2.append(s4);
                                    final boolean b5 = b2;
                                    if (b5) {
                                        break Label_0287;
                                    }
                                    break Label_0287;
                                }
                                catch (IllegalArgumentException ex9) {
                                    throw a(ex9);
                                }
                            }
                            try {
                                final StringBuilder sb2 = sb;
                                final String s4 = ",";
                                sb3 = sb2.append(s4);
                                final boolean b5 = b2;
                                if (b5) {
                                    c = '\n';
                                    break Label_0298;
                                }
                            }
                            catch (IllegalArgumentException ex10) {
                                throw a(ex10);
                            }
                        }
                        c = ' ';
                    }
                    sb3.append(c);
                    try {
                        if (textWithMacros.equals("...")) {
                            sb.append("...");
                            break Label_0347;
                        }
                    }
                    catch (IllegalArgumentException ex11) {
                        throw a(ex11);
                    }
                }
                sb.append(OCElementFactory.declarationText(s2, OCElementUtil.getType((PsiElement)typeCodeFragment), textWithMacros, psiElement));
            }
            n = 0;
        }
        Label_0376: {
            try {
                if (list.size() > 0) {
                    break Label_0376;
                }
                final OCChangeSignatureHandlerImpl ocChangeSignatureHandlerImpl2 = this;
                final boolean b6 = ocChangeSignatureHandlerImpl2.isFunction();
                if (b6) {
                    break Label_0376;
                }
                return sb;
            }
            catch (IllegalArgumentException ex12) {
                throw a(ex12);
            }
            try {
                final OCChangeSignatureHandlerImpl ocChangeSignatureHandlerImpl2 = this;
                final boolean b6 = ocChangeSignatureHandlerImpl2.isFunction();
                if (b6) {
                    sb.append(')');
                }
            }
            catch (IllegalArgumentException ex13) {
                throw a(ex13);
            }
        }
        return sb;
    }
    
    boolean isBlock() {
        try {
            if (OCCallableKind.BLOCK == this.getCallableKind()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    boolean isFunction() {
        try {
            if (OCCallableKind.FUNCTION == this.getCallableKind()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    boolean isMethod() {
        try {
            if (OCCallableKind.METHOD == this.getCallableKind()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    boolean isMethodGenerated() {
        return this.myMethod.getMethod().getContainingFile() instanceof OCCodeFragment;
    }
    
    OCCallableKind getOriginalCallableKind() {
        return this.myMethod.getCallableKind();
    }
    
    @Override
    public OCMethodDescriptor getMethodDescriptor() {
        return this.myMethod;
    }
    
    @Override
    public void setRefactorButtonText(final String s) {
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCChangeSignatureHandlerImpl.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
