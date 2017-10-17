// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.intellij.psi.PsiNamedElement;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureUsageProcessor;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCParameterInfo;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureHandler;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCEmptyChangeSignatureHandler;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.jetbrains.cidr.lang.search.OCSearchUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import javax.swing.JCheckBox;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;

public class OCParameterInplaceIntroducer extends OCBaseExpressionInplaceIntroducer<PsiNameIdentifierOwner, PsiElement>
{
    private JCheckBox myRefactorSuperCB;
    private JCheckBox myConstCB;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCParameterInplaceIntroducer(final Project project, final Editor editor, final PsiElement psiElement, final List<PsiElement> list, final String s) {
        super(project, editor, psiElement, list, PsiNameIdentifierOwner.class, PsiElement.class, s);
    }
    
    @Override
    public void configurePanel() {
        super.configurePanel();
        final OCCallable a = a(this.getAnchor());
        OCSymbolWithParent symbol = null;
        Label_0030: {
            try {
                if (a != null) {
                    symbol = a.getSymbol();
                    break Label_0030;
                }
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            symbol = null;
        }
        final OCSymbolWithParent ocSymbolWithParent = symbol;
        OCSearchUtil.Ancestor someAncestor = null;
        Label_0053: {
            try {
                if (ocSymbolWithParent instanceof OCSymbolWithParent) {
                    someAncestor = OCSearchUtil.findSomeAncestor(ocSymbolWithParent);
                    break Label_0053;
                }
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
            someAncestor = null;
        }
        final OCSearchUtil.Ancestor ancestor = someAncestor;
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(this.myProject).getCustomSettings((Class)OCCodeStyleSettings.class);
        try {
            if (this.isConstAvailable()) {
                this.myConstCB = (JCheckBox)this.createCheckBox("Declare &const", ocCodeStyleSettings.INTRODUCE_CONST_PARAMS, () -> {
                    ocCodeStyleSettings.INTRODUCE_CONST_PARAMS = this.myConstCB.isSelected();
                    this.g();
                    return;
                });
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        Label_0126: {
            try {
                if (ancestor == null) {
                    return;
                }
                final OCSearchUtil.Ancestor ancestor2 = ancestor;
                final boolean b = ancestor2.isOutOfProject();
                if (!b) {
                    break Label_0126;
                }
                return;
            }
            catch (IllegalStateException ex4) {
                throw b(ex4);
            }
            try {
                final OCSearchUtil.Ancestor ancestor2 = ancestor;
                final boolean b = ancestor2.isOutOfProject();
                if (!b) {
                    this.myRefactorSuperCB = (JCheckBox)this.createCheckBox("Refactor &super " + ocSymbolWithParent.getKind().getNameLowercase(), true, null);
                }
            }
            catch (IllegalStateException ex5) {
                throw b(ex5);
            }
        }
    }
    
    private void g() {
        final PsiNameIdentifierOwner variable = this.getVariable();
        try {
            if (variable instanceof OCDeclarator) {
                this.rebuildDeclarator((OCDeclarator)variable, new Rebuilder() {
                    @Override
                    public OCDeclarator rebuild(final OCDeclarator ocDeclarator) {
                        return ((OCDeclaration)OCChangeUtil.replaceHandlingMacros(ocDeclarator.getParent(), (PsiElement)OCElementFactory.paramDeclarationByNameAndType(ocDeclarator.getName(), OCParameterInplaceIntroducer.this.getType((PsiElement)ocDeclarator.getInitializer(), false, OCParameterInplaceIntroducer.this.myConstCB != null && OCParameterInplaceIntroducer.this.myConstCB.isSelected()), (PsiElement)ocDeclarator))).getDeclarators().get(0);
                    }
                });
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
    }
    
    @Override
    protected OCSymbolKind getDeclaratorKind() {
        return OCSymbolKind.PARAMETER;
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
        final OCCallable a = a(PsiTreeUtil.getContextOfType((PsiElement)this.myExpr, false, new Class[] { OCBlockStatement.class, OCConstructorFieldInitializer.class }));
        try {
            if (a == null) {
                return "Selected expression should be inside a function or method";
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    @Override
    public PsiElement evaluateAnchor() {
        final PsiElement contextOfType = PsiTreeUtil.getContextOfType((PsiElement)this.myExpr, false, new Class[] { OCConstructorFieldInitializer.class });
        try {
            if (contextOfType != null) {
                return contextOfType;
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return super.evaluateAnchor();
    }
    
    @Override
    protected String getVariableTextWithReplacedName(final PsiNameIdentifierOwner psiNameIdentifierOwner, final String s) {
        try {
            if (!(psiNameIdentifierOwner instanceof OCMethodSelectorPart) || ((OCMethod)psiNameIdentifierOwner.getParent()).getParameters().size() <= 1) {
                return super.getVariableTextWithReplacedName(psiNameIdentifierOwner, s);
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        final PsiElement previewElement = this.getPreviewElement(psiNameIdentifierOwner);
        final String text = previewElement.getText();
        final int n = psiNameIdentifierOwner.getTextOffset() - previewElement.getTextRange().getStartOffset();
        final String string = text.substring(0, n) + text.substring(n).replaceFirst(psiNameIdentifierOwner.getName(), s);
        final int n2 = psiNameIdentifierOwner.getTextRange().getStartOffset() - previewElement.getTextRange().getStartOffset();
        return string.substring(0, n2) + string.substring(n2).replaceFirst(((OCMethodSelectorPart)psiNameIdentifierOwner).getSelectorIdentifier().getText(), s);
    }
    
    @Override
    protected String trimTextForPreview(final String s) {
        final int index = s.indexOf(123);
        try {
            if (index != -1) {
                final String substring = s.substring(0, index);
                return super.trimTextForPreview(substring);
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        final String substring = s;
        return super.trimTextForPreview(substring);
    }
    
    @Override
    protected TextRange getMarkupRange(final PsiNameIdentifierOwner psiNameIdentifierOwner) {
        try {
            if (psiNameIdentifierOwner instanceof OCDeclarator) {
                return psiNameIdentifierOwner.getParent().getTextRange();
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        final OCMethodSelectorPart ocMethodSelectorPart = (OCMethodSelectorPart)psiNameIdentifierOwner;
        final OCTypeElement typeElement = ocMethodSelectorPart.getTypeElement();
        final OCMethod ocMethod = (OCMethod)ocMethodSelectorPart.getParent();
        Label_0073: {
            try {
                if (ocMethod.getParameters().size() > 1) {
                    break Label_0073;
                }
                final OCTypeElement ocTypeElement = typeElement;
                if (ocTypeElement == null) {
                    break Label_0073;
                }
                return new TextRange(typeElement.getTextOffset() - 1, ocMethodSelectorPart.getTextRange().getEndOffset());
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
            try {
                final OCTypeElement ocTypeElement = typeElement;
                if (ocTypeElement == null) {
                    return psiNameIdentifierOwner.getTextRange();
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
        }
        return new TextRange(typeElement.getTextOffset() - 1, ocMethodSelectorPart.getTextRange().getEndOffset());
    }
    
    @Override
    protected PsiNameIdentifierOwner getVariable() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   com/jetbrains/cidr/lang/refactoring/introduce/OCBaseExpressionInplaceIntroducer.getVariable:()Lcom/intellij/psi/PsiNameIdentifierOwner;
        //     4: astore_1       
        //     5: aload_1        
        //     6: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //     9: ifeq            31
        //    12: aload_1        
        //    13: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //    16: invokeinterface com/jetbrains/cidr/lang/psi/OCMethodSelectorPart.getParameter:()Lcom/intellij/psi/PsiElement;
        //    21: ifnull          61
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    30: athrow         
        //    31: aload_1        
        //    32: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    35: ifeq            67
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    44: athrow         
        //    45: aload_1        
        //    46: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //    51: ifnonnull       67
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    60: athrow         
        //    61: aconst_null    
        //    62: areturn        
        //    63: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    66: athrow         
        //    67: aload_1        
        //    68: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  5      24     27     31     Ljava/lang/IllegalStateException;
        //  12     38     41     45     Ljava/lang/IllegalStateException;
        //  31     54     57     61     Ljava/lang/IllegalStateException;
        //  45     63     63     67     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0031:
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
        final OCCallable a = a(this.getAnchor());
        Label_0025: {
            try {
                if (OCParameterInplaceIntroducer.$assertionsDisabled) {
                    break Label_0025;
                }
                final OCCallable ocCallable = a;
                if (ocCallable == null) {
                    break Label_0025;
                }
                break Label_0025;
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            try {
                final OCCallable ocCallable = a;
                if (ocCallable == null) {
                    throw new AssertionError();
                }
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
        }
        final OCChangeSignatureHandler handler = OCChangeSignatureActionHandler.getHandler(a, (PsiElement)a, true);
        try {
            if (handler instanceof OCEmptyChangeSignatureHandler) {
                return;
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        this.a(s, handler);
        a(a, handler);
        final List parameters = a.getParameters();
        Label_0098: {
            try {
                if (parameters == null) {
                    return;
                }
                final List list = parameters;
                final boolean b = list.isEmpty();
                if (!b) {
                    break Label_0098;
                }
                return;
            }
            catch (IllegalStateException ex4) {
                throw b(ex4);
            }
            try {
                final List list = parameters;
                final boolean b = list.isEmpty();
                if (!b) {
                    ((OCBaseInplaceIntroducer<PsiNameIdentifierOwner, E>)this).setVariable(parameters.get(parameters.size() - 1));
                }
            }
            catch (IllegalStateException ex5) {
                throw b(ex5);
            }
        }
    }
    
    @Override
    protected void introduceForReal(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.getAnchor:()Lcom/intellij/psi/PsiElement;
        //     4: astore_2       
        //     5: aload_2        
        //     6: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.a:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //     9: astore_3       
        //    10: getstatic       com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.$assertionsDisabled:Z
        //    13: ifne            39
        //    16: aload_3        
        //    17: ifnonnull       39
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    26: athrow         
        //    27: new             Ljava/lang/AssertionError;
        //    30: dup            
        //    31: invokespecial   java/lang/AssertionError.<init>:()V
        //    34: athrow         
        //    35: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    38: athrow         
        //    39: aload_3        
        //    40: aload_3        
        //    41: iconst_1       
        //    42: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureActionHandler.getHandler:(Lcom/jetbrains/cidr/lang/psi/OCCallable;Lcom/intellij/psi/PsiElement;Z)Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler;
        //    45: astore          4
        //    47: aload_0        
        //    48: aload_1        
        //    49: aload           4
        //    51: invokespecial   com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler;)Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //    54: astore          5
        //    56: aload_0        
        //    57: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myRefactorSuperCB:Ljavax/swing/JCheckBox;
        //    60: ifnull          95
        //    63: aload_0        
        //    64: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myRefactorSuperCB:Ljavax/swing/JCheckBox;
        //    67: invokevirtual   javax/swing/JCheckBox.isSelected:()Z
        //    70: ifeq            95
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    79: athrow         
        //    80: aload           4
        //    82: iconst_1       
        //    83: invokeinterface com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler.setChangeAncestors:(Z)V
        //    88: goto            95
        //    91: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    94: athrow         
        //    95: aload_0        
        //    96: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myIsUnresolvedReference:Z
        //    99: ifeq            312
        //   102: aload_0        
        //   103: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   106: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   109: ifeq            312
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   118: athrow         
        //   119: aload_0        
        //   120: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   123: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   128: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   131: ifeq            312
        //   134: goto            141
        //   137: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   140: athrow         
        //   141: aload_0        
        //   142: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   145: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   150: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   155: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   158: ifeq            312
        //   161: goto            168
        //   164: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   167: athrow         
        //   168: aload_0        
        //   169: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myOccurrences:[Lcom/intellij/psi/PsiElement;
        //   172: iconst_0       
        //   173: aaload         
        //   174: aload_0        
        //   175: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   178: if_acmpne       312
        //   181: goto            188
        //   184: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   187: athrow         
        //   188: aload_0        
        //   189: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   192: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   197: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   200: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   205: aload_0        
        //   206: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   209: if_acmpne       312
        //   212: goto            219
        //   215: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   218: athrow         
        //   219: aload_0        
        //   220: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   223: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   228: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   231: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   236: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   239: if_acmpne       312
        //   242: goto            249
        //   245: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   248: athrow         
        //   249: aload_0        
        //   250: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   253: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   258: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   261: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getSourceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   266: astore          6
        //   268: aload           6
        //   270: ifnull          292
        //   273: aload           5
        //   275: aload           6
        //   277: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getTextWithMacros:()Ljava/lang/String;
        //   282: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.setArgumentValue:(Ljava/lang/String;)V
        //   285: goto            292
        //   288: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   291: athrow         
        //   292: aload_0        
        //   293: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   296: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   301: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   306: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   309: goto            393
        //   312: aload_0        
        //   313: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.isCreateFromUsageMode:()Z
        //   316: ifeq            326
        //   319: goto            393
        //   322: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   325: athrow         
        //   326: aload_0        
        //   327: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   330: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseLocalConvertibleHandler.isDeclaratorIdentifier:(Lcom/intellij/psi/PsiElement;)Z
        //   333: ifeq            360
        //   336: aload_0        
        //   337: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   340: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   345: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   348: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   353: goto            367
        //   356: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   359: athrow         
        //   360: aload_0        
        //   361: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //   364: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   367: astore          6
        //   369: aload           6
        //   371: ifnull          393
        //   374: aload           5
        //   376: aload           6
        //   378: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getTextWithMacros:()Ljava/lang/String;
        //   383: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.setArgumentValue:(Ljava/lang/String;)V
        //   386: goto            393
        //   389: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   392: athrow         
        //   393: aload_0        
        //   394: aload_1        
        //   395: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.changeUsages:(Ljava/lang/String;)Z
        //   398: ifeq            462
        //   401: aload_0        
        //   402: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myOccurrences:[Lcom/intellij/psi/PsiElement;
        //   405: astore          6
        //   407: aload           6
        //   409: arraylength    
        //   410: istore          7
        //   412: iconst_0       
        //   413: istore          8
        //   415: iload           8
        //   417: iload           7
        //   419: if_icmpge       462
        //   422: aload           6
        //   424: iload           8
        //   426: aaload         
        //   427: astore          9
        //   429: aload           9
        //   431: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseLocalConvertibleHandler.isDeclaratorIdentifier:(Lcom/intellij/psi/PsiElement;)Z
        //   434: ifne            456
        //   437: aload           9
        //   439: aload_1        
        //   440: aload           9
        //   442: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   445: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   448: pop            
        //   449: goto            456
        //   452: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   455: athrow         
        //   456: iinc            8, 1
        //   459: goto            415
        //   462: aload_0        
        //   463: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myOccurrences:[Lcom/intellij/psi/PsiElement;
        //   466: astore          6
        //   468: aload           6
        //   470: arraylength    
        //   471: istore          7
        //   473: iconst_0       
        //   474: istore          8
        //   476: iload           8
        //   478: iload           7
        //   480: if_icmpge       521
        //   483: aload           6
        //   485: iload           8
        //   487: aaload         
        //   488: astore          9
        //   490: aload           9
        //   492: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseLocalConvertibleHandler.isDeclaratorIdentifier:(Lcom/intellij/psi/PsiElement;)Z
        //   495: ifeq            515
        //   498: aload           9
        //   500: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   505: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   508: goto            515
        //   511: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   514: athrow         
        //   515: iinc            8, 1
        //   518: goto            476
        //   521: aload           4
        //   523: invokeinterface com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler.invokeSynchronously:()V
        //   528: aload_3        
        //   529: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getParameters:()Ljava/util/List;
        //   534: astore          6
        //   536: aload           6
        //   538: ifnull          588
        //   541: aload           6
        //   543: invokeinterface java/util/List.isEmpty:()Z
        //   548: ifne            588
        //   551: goto            558
        //   554: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   557: athrow         
        //   558: aload_0        
        //   559: aload           6
        //   561: aload           6
        //   563: invokeinterface java/util/List.size:()I
        //   568: iconst_1       
        //   569: isub           
        //   570: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   575: checkcast       Lcom/intellij/psi/PsiNameIdentifierOwner;
        //   578: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.setVariable:(Lcom/intellij/psi/PsiNameIdentifierOwner;)V
        //   581: goto            588
        //   584: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   587: athrow         
        //   588: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  10     20     23     27     Ljava/lang/IllegalStateException;
        //  16     35     35     39     Ljava/lang/IllegalStateException;
        //  56     73     76     80     Ljava/lang/IllegalStateException;
        //  63     88     91     95     Ljava/lang/IllegalStateException;
        //  95     112    115    119    Ljava/lang/IllegalStateException;
        //  102    134    137    141    Ljava/lang/IllegalStateException;
        //  119    161    164    168    Ljava/lang/IllegalStateException;
        //  141    181    184    188    Ljava/lang/IllegalStateException;
        //  168    212    215    219    Ljava/lang/IllegalStateException;
        //  188    242    245    249    Ljava/lang/IllegalStateException;
        //  268    285    288    292    Ljava/lang/IllegalStateException;
        //  312    322    322    326    Ljava/lang/IllegalStateException;
        //  326    356    356    360    Ljava/lang/IllegalStateException;
        //  369    386    389    393    Ljava/lang/IllegalStateException;
        //  429    449    452    456    Ljava/lang/IllegalStateException;
        //  490    508    511    515    Ljava/lang/IllegalStateException;
        //  536    551    554    558    Ljava/lang/IllegalStateException;
        //  541    581    584    588    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0119:
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
            s = "refactoring.introduceParameter";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return s;
    }
    
    private OCParameterInfo a(final String p0, final OCChangeSignatureHandler p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.isCreateFromUsageMode:()Z
        //     4: ifne            59
        //     7: aload_0        
        //     8: aload_0        
        //     9: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myExpr:Lcom/intellij/psi/PsiElement;
        //    12: iconst_0       
        //    13: aload_0        
        //    14: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myConstCB:Ljavax/swing/JCheckBox;
        //    17: ifnull          52
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    26: athrow         
        //    27: aload_0        
        //    28: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myConstCB:Ljavax/swing/JCheckBox;
        //    31: invokevirtual   javax/swing/JCheckBox.isSelected:()Z
        //    34: ifeq            52
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: iconst_1       
        //    45: goto            53
        //    48: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    51: athrow         
        //    52: iconst_0       
        //    53: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.getType:(Lcom/intellij/psi/PsiElement;ZZ)Lcom/jetbrains/cidr/lang/types/OCType;
        //    56: goto            63
        //    59: aload_0        
        //    60: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCParameterInplaceIntroducer.myExprType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    63: astore_3       
        //    64: aload_2        
        //    65: aload_1        
        //    66: aload_1        
        //    67: aload_3        
        //    68: iconst_m1      
        //    69: iconst_0       
        //    70: invokeinterface com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler.addParameter:(Ljava/lang/String;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;IZ)Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //    75: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      20     23     27     Ljava/lang/IllegalStateException;
        //  7      37     40     44     Ljava/lang/IllegalStateException;
        //  27     48     48     52     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
    
    private static void a(final OCCallable ocCallable, final OCChangeSignatureHandler ocChangeSignatureHandler) {
        final OCCallable generateMethodDefinition = OCChangeSignatureUsageProcessor.generateMethodDefinition(ocChangeSignatureHandler.getChangeInfo(), ocCallable, true, false, false);
        final OCBlockStatement body = ocCallable.getBody();
        Label_0038: {
            try {
                if (OCParameterInplaceIntroducer.$assertionsDisabled) {
                    break Label_0038;
                }
                final OCBlockStatement ocBlockStatement = body;
                if (ocBlockStatement == null) {
                    break Label_0038;
                }
                break Label_0038;
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            try {
                final OCBlockStatement ocBlockStatement = body;
                if (ocBlockStatement == null) {
                    throw new AssertionError();
                }
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
        }
        ocCallable.getNode().removeRange(ocCallable.getNode().getFirstChildNode(), body.getNode());
        for (final ASTNode astNode : generateMethodDefinition.getNode().getChildren((TokenSet)null)) {
            try {
                if (OCElementTypes.BLOCK_STATEMENTS.contains(astNode.getElementType())) {
                    break;
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
            ocCallable.addBefore(astNode.getPsi(), (PsiElement)body);
        }
    }
    
    @Override
    protected PsiElement getPreviewElement(final PsiNameIdentifierOwner psiNameIdentifierOwner) {
        return (PsiElement)a((PsiElement)psiNameIdentifierOwner);
    }
    
    private static OCCallable a(final PsiElement psiElement) {
        return (OCCallable)PsiTreeUtil.getParentOfType(psiElement, new Class[] { OCMethod.class, OCFunctionDefinition.class });
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCParameterInplaceIntroducer.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
