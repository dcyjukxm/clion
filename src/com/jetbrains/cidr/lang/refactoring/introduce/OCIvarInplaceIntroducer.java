// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.intention.IntentionAction;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import javax.swing.JCheckBox;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

public class OCIvarInplaceIntroducer extends OCBaseExpressionInplaceIntroducer<OCDeclarator, PsiElement>
{
    private JCheckBox mySynthesizeCB;
    protected JCheckBox myDeclareInInterfaceCb;
    
    public OCIvarInplaceIntroducer(final Project project, final Editor editor, final PsiElement psiElement, final List<PsiElement> list, final String s) {
        super(project, editor, psiElement, list, OCDeclarator.class, PsiElement.class, s);
    }
    
    @Override
    public void configurePanel() {
        super.configurePanel();
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(this.myProject).getCustomSettings((Class)OCCodeStyleSettings.class);
        try {
            if (this.askToGenerateProperty()) {
                this.mySynthesizeCB = (JCheckBox)this.createCheckBox("Generate &property", ocCodeStyleSettings.INTRODUCE_GENERATE_PROPERTY, () -> ocCodeStyleSettings.INTRODUCE_GENERATE_PROPERTY = this.mySynthesizeCB.isSelected());
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        OCIvarInplaceIntroducer ocIvarInplaceIntroducer = null;
        OCIvarInplaceIntroducer ocIvarInplaceIntroducer2 = null;
        String s = null;
        boolean b2 = false;
        Label_0088: {
            Label_0079: {
                try {
                    if (!OCCompilerFeatures.supportsIvarsInImplementation()) {
                        return;
                    }
                    ocIvarInplaceIntroducer = this;
                    ocIvarInplaceIntroducer2 = this;
                    s = "Declare in the &interface";
                    final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                    final boolean b = ocCodeStyleSettings2.PUT_IVARS_TO_IMPLEMENTATION;
                    if (!b) {
                        break Label_0079;
                    }
                    break Label_0079;
                }
                catch (IllegalStateException ex2) {
                    throw b(ex2);
                }
                try {
                    ocIvarInplaceIntroducer = this;
                    ocIvarInplaceIntroducer2 = this;
                    s = "Declare in the &interface";
                    final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                    final boolean b = ocCodeStyleSettings2.PUT_IVARS_TO_IMPLEMENTATION;
                    if (!b) {
                        b2 = true;
                        break Label_0088;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw b(ex3);
                }
            }
            b2 = false;
        }
        ocIvarInplaceIntroducer.myDeclareInInterfaceCb = (JCheckBox)ocIvarInplaceIntroducer2.createCheckBox(s, b2, null);
    }
    
    protected boolean askToGenerateProperty() {
        try {
            if (!(this.myExpr instanceof OCSynthesizeProperty)) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return false;
    }
    
    @Override
    protected OCSymbolKind getDeclaratorKind() {
        return OCSymbolKind.INSTANCE_VARIABLE;
    }
    
    @Override
    protected String checkExpression(final PsiElement psiElement) {
        final String checkExpression = super.checkExpression(psiElement);
        try {
            if (checkExpression != null) {
                return checkExpression;
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        if (this.myParentSymbol == null) {
            final OCMethod ocMethod = (OCMethod)PsiTreeUtil.getContextOfType((PsiElement)PsiTreeUtil.getContextOfType((PsiElement)this.myExpr, false, new Class[] { OCBlockStatement.class }), false, new Class[] { OCMethod.class });
            Label_0092: {
                try {
                    if (ocMethod == null) {
                        return "Selected expression should be inside an instance method";
                    }
                    final OCMethod ocMethod2 = ocMethod;
                    final boolean b = ocMethod2.isInstanceMethod();
                    if (!b) {
                        return "Selected expression should be inside an instance method";
                    }
                    break Label_0092;
                }
                catch (IllegalStateException ex2) {
                    throw b(ex2);
                }
                try {
                    final OCMethod ocMethod2 = ocMethod;
                    final boolean b = ocMethod2.isInstanceMethod();
                    if (!b) {
                        return "Selected expression should be inside an instance method";
                    }
                }
                catch (IllegalStateException ex3) {
                    throw b(ex3);
                }
            }
            final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getParentOfType((PsiElement)ocMethod, (Class)OCClassDeclaration.class);
            OCSymbol symbol = null;
            Label_0125: {
                try {
                    if (ocClassDeclaration != null) {
                        symbol = ocClassDeclaration.getSymbol();
                        break Label_0125;
                    }
                }
                catch (IllegalStateException ex4) {
                    throw b(ex4);
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
            if (this.myExpr instanceof OCSynthesizeProperty) {
                return this.myExpr.getPrevSibling();
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return super.evaluateAnchor();
    }
    
    @Override
    protected String[] suggestNames(final boolean b, @Nullable final OCDeclarator ocDeclarator) {
        try {
            if (this.myUsageName != null) {
                return new String[] { this.myUsageName };
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        final String[] suggestNames = super.suggestNames(b, ocDeclarator);
        int i = 0;
        try {
            while (i < suggestNames.length) {
                suggestNames[i] = OCNameSuggester.getNonCollidingName(suggestNames[i], OCSymbolKind.PROPERTY, true, this.myProject);
                ++i;
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        return suggestNames;
    }
    
    private OCDeclarator a(final String p0, final PsiElement p1, final OCClassDeclaration p2, final PsiElement p3, final boolean p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          6
        //     3: aload_2        
        //     4: astore          7
        //     6: aload_2        
        //     7: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //    10: ifeq            142
        //    13: aload_2        
        //    14: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getPropertyRef:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    22: astore          8
        //    24: aload           8
        //    26: ifnull          46
        //    29: aload           8
        //    31: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    36: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //    39: goto            47
        //    42: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    45: athrow         
        //    46: aconst_null    
        //    47: astore          9
        //    49: aload           9
        //    51: ifnull          139
        //    54: aload_2        
        //    55: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    60: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //    63: ifeq            139
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    72: athrow         
        //    73: aload           9
        //    75: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    80: astore          10
        //    82: aload           9
        //    84: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.ASSIGN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //    87: aload           10
        //    89: aload_2        
        //    90: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getAttributeOfGroup:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //    95: astore          11
        //    97: aload           11
        //    99: ifnull          139
        //   102: aload           10
        //   104: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   107: ifeq            139
        //   110: goto            117
        //   113: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   116: athrow         
        //   117: aload           11
        //   119: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getIvarCompatibleARCAttribute:()Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //   122: astore          12
        //   124: aload           12
        //   126: invokevirtual   com/jetbrains/cidr/lang/types/ARCAttribute.isDefault:()Z
        //   129: ifne            139
        //   132: aload           12
        //   134: invokevirtual   com/jetbrains/cidr/lang/types/ARCAttribute.getTokenName:()Ljava/lang/String;
        //   137: astore          6
        //   139: goto            165
        //   142: aload_2        
        //   143: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseLocalConvertibleHandler.isDeclaratorIdentifier:(Lcom/intellij/psi/PsiElement;)Z
        //   146: ifeq            165
        //   149: aload_2        
        //   150: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   155: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   158: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   163: astore          7
        //   165: aload           6
        //   167: aload_1        
        //   168: aload_0        
        //   169: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   172: aload_2        
        //   173: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.declaration:(Ljava/lang/String;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   176: astore          8
        //   178: aload_3        
        //   179: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getInstanceVariablesList:()Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;
        //   184: aload           8
        //   186: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.add:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   189: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   192: astore          8
        //   194: aload_0        
        //   195: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.isCreateFromUsageMode:()Z
        //   198: ifne            468
        //   201: aload           7
        //   203: ifnull          468
        //   206: goto            213
        //   209: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   212: athrow         
        //   213: new             Ljava/lang/StringBuilder;
        //   216: dup            
        //   217: invokespecial   java/lang/StringBuilder.<init>:()V
        //   220: aload_1        
        //   221: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   224: ldc             "=a;"
        //   226: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   229: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   232: aload_2        
        //   233: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   238: iconst_1       
        //   239: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.statementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Z)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   242: astore          9
        //   244: aload           9
        //   246: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   249: invokeinterface com/jetbrains/cidr/lang/psi/OCExpressionStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   254: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   257: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getSourceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   262: astore          10
        //   264: iload           5
        //   266: ifeq            286
        //   269: aload           10
        //   271: aload           7
        //   273: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.replace:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   278: pop            
        //   279: goto            294
        //   282: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   285: athrow         
        //   286: aload           10
        //   288: aload           7
        //   290: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   293: pop            
        //   294: aload_0        
        //   295: aload           4
        //   297: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.ensureParentIsBlockStatement:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   300: astore          4
        //   302: aload_2        
        //   303: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   308: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   311: ifne            328
        //   314: aload_2        
        //   315: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseLocalConvertibleHandler.isDeclaratorIdentifier:(Lcom/intellij/psi/PsiElement;)Z
        //   318: ifeq            380
        //   321: goto            328
        //   324: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   327: athrow         
        //   328: aload_2        
        //   329: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //   334: ifeq            352
        //   337: goto            344
        //   340: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   343: athrow         
        //   344: aload_2        
        //   345: goto            354
        //   348: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   351: athrow         
        //   352: aload           4
        //   354: ldc             Lcom/jetbrains/cidr/lang/psi/OCStatement;.class
        //   356: iconst_0       
        //   357: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Z)Lcom/intellij/psi/PsiElement;
        //   360: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   363: astore          11
        //   365: aload           11
        //   367: aload           9
        //   369: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   372: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   375: astore          9
        //   377: goto            399
        //   380: aload           4
        //   382: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   387: aload           9
        //   389: aload           4
        //   391: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   394: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   397: astore          9
        //   399: iload           5
        //   401: ifeq            468
        //   404: aload           9
        //   406: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   409: invokeinterface com/jetbrains/cidr/lang/psi/OCExpressionStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   414: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   417: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   422: astore          11
        //   424: aload_0        
        //   425: aload           11
        //   427: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.addOccurrence:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/editor/RangeMarker;
        //   430: pop            
        //   431: aload_0        
        //   432: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   435: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //   440: ifne            468
        //   443: aload_0        
        //   444: aload           11
        //   446: putfield        com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   449: aload_0        
        //   450: aload_0        
        //   451: aload_0        
        //   452: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   455: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.createMarker:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/editor/RangeMarker;
        //   458: putfield        com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.myExprMarker:Lcom/intellij/openapi/editor/RangeMarker;
        //   461: goto            468
        //   464: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   467: athrow         
        //   468: aload           8
        //   470: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   475: iconst_0       
        //   476: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   481: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   484: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  24     42     42     46     Ljava/lang/IllegalStateException;
        //  49     66     69     73     Ljava/lang/IllegalStateException;
        //  97     110    113    117    Ljava/lang/IllegalStateException;
        //  194    206    209    213    Ljava/lang/IllegalStateException;
        //  264    282    282    286    Ljava/lang/IllegalStateException;
        //  302    321    324    328    Ljava/lang/IllegalStateException;
        //  314    337    340    344    Ljava/lang/IllegalStateException;
        //  328    348    348    352    Ljava/lang/IllegalStateException;
        //  424    461    464    468    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0328:
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
        final PsiElement mainExpression = ((OCBaseInplaceIntroducer<V, PsiElement>)this).getMainExpression();
        final PsiElement anchor = this.getAnchor();
        PsiElement psiElement = PsiTreeUtil.getContextOfType(anchor, false, new Class[] { OCImplementation.class, PsiFile.class });
        if (psiElement instanceof PsiFile) {
            psiElement = psiElement.add((PsiElement)OCElementFactory.interfaceByName("i", anchor));
        }
        ((OCBaseInplaceIntroducer<OCDeclarator, E>)this).setVariable(this.a(s, mainExpression, (OCClassDeclaration)psiElement, anchor, true));
    }
    
    @Override
    protected void introduceForReal(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.getAnchor:()Lcom/intellij/psi/PsiElement;
        //     4: astore_2       
        //     5: aload_2        
        //     6: iconst_0       
        //     7: iconst_1       
        //     8: anewarray       Ljava/lang/Class;
        //    11: dup            
        //    12: iconst_0       
        //    13: ldc             Lcom/jetbrains/cidr/lang/psi/OCImplementation;.class
        //    15: aastore        
        //    16: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;Z[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    19: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    22: astore_3       
        //    23: aload_0        
        //    24: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.myParentSymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    27: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    30: ifeq            45
        //    33: aload_0        
        //    34: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.myParentSymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    37: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    40: astore          4
        //    42: goto            65
        //    45: aload_3        
        //    46: ifnull          62
        //    49: aload_3        
        //    50: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    55: goto            63
        //    58: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    61: athrow         
        //    62: aconst_null    
        //    63: astore          4
        //    65: aload           4
        //    67: ifnonnull       75
        //    70: return         
        //    71: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    74: athrow         
        //    75: aload           4
        //    77: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //    82: astore          5
        //    84: aload           4
        //    86: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getImplementation:()Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //    91: astore          6
        //    93: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsIvarsInImplementation:()Z
        //    96: ifeq            164
        //    99: aload_0        
        //   100: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.myDeclareInInterfaceCb:Ljavax/swing/JCheckBox;
        //   103: invokevirtual   javax/swing/JCheckBox.isSelected:()Z
        //   106: ifne            164
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   115: athrow         
        //   116: aload           6
        //   118: ifnull          164
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   127: athrow         
        //   128: aload_3        
        //   129: ifnull          164
        //   132: goto            139
        //   135: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   138: athrow         
        //   139: aload           6
        //   141: aload_3        
        //   142: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   147: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   150: ifeq            164
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   159: athrow         
        //   160: aload           6
        //   162: astore          5
        //   164: aload           5
        //   166: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.resolveClassDeclaration:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   169: astore          7
        //   171: aload           7
        //   173: ifnull          191
        //   176: aload           7
        //   178: invokestatic    com/intellij/refactoring/util/CommonRefactoringUtil.checkReadOnlyStatus:(Lcom/intellij/psi/PsiElement;)Z
        //   181: ifne            196
        //   184: goto            191
        //   187: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   190: athrow         
        //   191: return         
        //   192: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   195: athrow         
        //   196: aload_0        
        //   197: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.getMainExpression:()Lcom/intellij/psi/PsiElement;
        //   200: astore          8
        //   202: aload_0        
        //   203: aload_1        
        //   204: aload           8
        //   206: aload           7
        //   208: aload_2        
        //   209: iconst_0       
        //   210: invokespecial   com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.a:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;Lcom/intellij/psi/PsiElement;Z)Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   213: astore          9
        //   215: aload           9
        //   217: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   222: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.fixAllSymbolsRecursively:(Lcom/intellij/psi/PsiElement;)V
        //   225: aload_0        
        //   226: aload_1        
        //   227: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.changeUsages:(Ljava/lang/String;)Z
        //   230: ifeq            321
        //   233: aload_0        
        //   234: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.myOccurrences:[Lcom/intellij/psi/PsiElement;
        //   237: astore          10
        //   239: aload           10
        //   241: arraylength    
        //   242: istore          11
        //   244: iconst_0       
        //   245: istore          12
        //   247: iload           12
        //   249: iload           11
        //   251: if_icmpge       321
        //   254: aload           10
        //   256: iload           12
        //   258: aaload         
        //   259: astore          13
        //   261: aload           13
        //   263: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //   268: ifeq            315
        //   271: aload           13
        //   273: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseLocalConvertibleHandler.isDeclaratorIdentifier:(Lcom/intellij/psi/PsiElement;)Z
        //   276: ifeq            303
        //   279: goto            286
        //   282: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   285: athrow         
        //   286: aload           13
        //   288: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   293: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   296: goto            315
        //   299: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   302: athrow         
        //   303: aload           13
        //   305: aload_1        
        //   306: aload           13
        //   308: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   311: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   314: pop            
        //   315: iinc            12, 1
        //   318: goto            247
        //   321: aload_0        
        //   322: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.mySynthesizeCB:Ljavax/swing/JCheckBox;
        //   325: ifnull          479
        //   328: aload_0        
        //   329: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.mySynthesizeCB:Ljavax/swing/JCheckBox;
        //   332: invokevirtual   javax/swing/JCheckBox.isSelected:()Z
        //   335: ifeq            479
        //   338: goto            345
        //   341: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   344: athrow         
        //   345: aload           9
        //   347: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   352: astore          10
        //   354: aload           10
        //   356: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   359: ifeq            479
        //   362: aload_0        
        //   363: new             Lcom/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer$1;
        //   366: dup            
        //   367: aload_0        
        //   368: aload           10
        //   370: invokespecial   com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer$1.<init>:(Lcom/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   373: aload           9
        //   375: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   380: invokespecial   com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.a:(Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/intellij/psi/PsiFile;)V
        //   383: aload           9
        //   385: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   390: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   393: astore          11
        //   395: aload_2        
        //   396: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //   401: ifne            426
        //   404: aload_3        
        //   405: ifnull          423
        //   408: goto            415
        //   411: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   414: athrow         
        //   415: aload_3        
        //   416: goto            425
        //   419: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   422: athrow         
        //   423: aload           7
        //   425: astore_2       
        //   426: aload           11
        //   428: ifnull          479
        //   431: aload           11
        //   433: aload_2        
        //   434: aconst_null    
        //   435: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.isVisible:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   438: ifne            479
        //   441: goto            448
        //   444: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   447: athrow         
        //   448: aload           11
        //   450: aload_2        
        //   451: aconst_null    
        //   452: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.getVisibility:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   455: astore          12
        //   457: aload_0        
        //   458: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction;
        //   461: dup            
        //   462: aload           11
        //   464: aload           12
        //   466: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)V
        //   469: aload           9
        //   471: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   476: invokespecial   com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer.a:(Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/intellij/psi/PsiFile;)V
        //   479: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  45     58     58     62     Ljava/lang/IllegalStateException;
        //  65     71     71     75     Ljava/lang/IllegalStateException;
        //  93     109    112    116    Ljava/lang/IllegalStateException;
        //  99     121    124    128    Ljava/lang/IllegalStateException;
        //  116    132    135    139    Ljava/lang/IllegalStateException;
        //  128    153    156    160    Ljava/lang/IllegalStateException;
        //  171    184    187    191    Ljava/lang/IllegalStateException;
        //  176    192    192    196    Ljava/lang/IllegalStateException;
        //  261    279    282    286    Ljava/lang/IllegalStateException;
        //  271    299    299    303    Ljava/lang/IllegalStateException;
        //  321    338    341    345    Ljava/lang/IllegalStateException;
        //  395    408    411    415    Ljava/lang/IllegalStateException;
        //  404    419    419    423    Ljava/lang/IllegalStateException;
        //  426    441    444    448    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0116:
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
            s = "refactoring.appCodeIntroduceVariable";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCIvarInplaceIntroducer", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return s;
    }
    
    private void a(final IntentionAction intentionAction, final PsiFile psiFile) {
        try {
            if (intentionAction.isAvailable(this.myProject, this.myEditor, psiFile)) {
                PsiDocumentManager.getInstance(this.myProject).doPostponedOperationsAndUnblockDocument(this.myEditor.getDocument());
                intentionAction.invoke(this.myProject, this.myEditor, psiFile);
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
    }
    
    public PsiElement getPreviewElement(final OCDeclarator ocDeclarator) {
        return ocDeclarator.getParent();
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
