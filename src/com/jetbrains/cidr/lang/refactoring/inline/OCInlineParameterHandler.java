// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.inline;

import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureHandler;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.SmartPsiElementPointer;
import java.util.Map;
import com.intellij.openapi.project.Project;
import java.util.Iterator;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.refactoring.move.OCCodeMoveValidator;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.util.Processor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.editor.colors.EditorColors;
import java.util.Collections;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import com.jetbrains.cidr.lang.search.usages.OCReadWriteAccessDetector;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.editor.Editor;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;

public class OCInlineParameterHandler extends OCInlineActionHandlerBase<PsiNamedElement>
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    @Override
    protected String getElementKind(final PsiNamedElement psiNamedElement) {
        return "parameter";
    }
    
    public boolean canInlineElement(final PsiElement psiElement) {
        Label_0027: {
            try {
                if (psiElement instanceof OCDeclarator) {
                    break Label_0027;
                }
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2 instanceof OCMethodSelectorPart;
                if (!b) {
                    return false;
                }
                break Label_0027;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2 instanceof OCMethodSelectorPart;
                if (!b) {
                    return false;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        final OCSymbol symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
        Label_0063: {
            try {
                if (!(symbol instanceof OCDeclaratorSymbol)) {
                    return false;
                }
                final OCSymbol ocSymbol = symbol;
                final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.PARAMETER;
                if (ocSymbolKind == ocSymbolKind2) {
                    break Label_0063;
                }
                return false;
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            try {
                final OCSymbol ocSymbol = symbol;
                final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.PARAMETER;
                if (ocSymbolKind == ocSymbolKind2) {
                    return true;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    private static int a(final PsiNamedElement psiNamedElement, final OCCallable ocCallable) {
        try {
            if (ocCallable instanceof OCMethod) {
                return ((OCMethod)ocCallable).getParameters().indexOf(psiNamedElement);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocCallable instanceof OCFunctionDeclaration) {
                return ((OCFunctionDeclaration)ocCallable).getParameterList().getParameterDeclarations().indexOf(psiNamedElement.getParent());
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (ocCallable instanceof OCBlockExpression) {
                return ((OCBlockExpression)ocCallable).getParameterList().getParameterDeclarations().indexOf(psiNamedElement.getParent());
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            if (ocCallable instanceof OCLambdaExpression) {
                return ((OCLambdaExpression)ocCallable).getParameterList().getParameterDeclarations().indexOf(psiNamedElement.getParent());
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        return -1;
    }
    
    @Override
    protected boolean allowInlineSingleUsage() {
        return false;
    }
    
    @Override
    protected String checkValidness(final PsiNamedElement psiNamedElement, final List<PsiElement> list, final PsiElement psiElement, final String s, final Editor editor, final Ref<PsiElement> ref, final List<String> list2, final boolean b) {
        final String checkValidness = super.checkValidness(psiNamedElement, list, psiElement, s, editor, ref, list2, b);
        try {
            if (checkValidness != null) {
                return checkValidness;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        for (final PsiElement psiElement2 : list) {
            try {
                if (new OCReadWriteAccessDetector().getExpressionAccess(psiElement2) != ReadWriteAccessDetector.Access.Read) {
                    OCInlineActionHandlerBase.highlightUsages(psiNamedElement.getProject(), editor, Collections.singletonList(psiElement2), EditorColors.WRITE_SEARCH_RESULT_ATTRIBUTES);
                    OCInlineActionHandlerBase.showHighlightRemovalStatus(psiNamedElement.getProject());
                    return StringUtil.capitalize(s) + " is accessed for writing";
                }
                continue;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        final OCCallable ocCallable = (OCCallable)PsiTreeUtil.getParentOfType((PsiElement)psiNamedElement, (Class)OCCallable.class);
        try {
            if (ocCallable == null) {
                return "Cannot find the method/function";
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            if (ocCallable instanceof OCBlockExpression) {
                return "Cannot inline the parameters of blocks";
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        try {
            if (ocCallable instanceof OCLambdaExpression) {
                return "Cannot inline the parameters of lambdas";
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        final OCSymbol symbol = ocCallable.getSymbol();
        try {
            if (symbol == null) {
                return "SILENT_EXIT";
            }
        }
        catch (IllegalStateException ex6) {
            throw a(ex6);
        }
        OCInlineMethodHandler.checkMethodsHierarchy(ocCallable, symbol.getNameWithKindLowercase(), list2);
        final String nameWithKindLowercase = symbol.getNameWithKindLowercase();
        final int a = a(psiNamedElement, ocCallable);
        Label_0273: {
            Label_0261: {
                try {
                    if (OCInlineParameterHandler.$assertionsDisabled) {
                        break Label_0273;
                    }
                    final int n = a;
                    if (n < 0) {
                        break Label_0261;
                    }
                    break Label_0273;
                }
                catch (IllegalStateException ex7) {
                    throw a(ex7);
                }
                try {
                    final int n = a;
                    if (n < 0) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalStateException ex8) {
                    throw a(ex8);
                }
            }
            try {
                if (!a(symbol, a, (Processor<OCExpression>)(ocExpression -> {
                    OCReferenceElement referenceElement = null;
                    Label_0024: {
                        try {
                            if (ocExpression instanceof OCReferenceExpression) {
                                referenceElement = ((OCReferenceExpression)ocExpression).getReferenceElement();
                                break Label_0024;
                            }
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        referenceElement = null;
                    }
                    final OCReferenceElement ocReferenceElement = referenceElement;
                    Label_0055: {
                        try {
                            if (ocReferenceElement == null) {
                                break Label_0055;
                            }
                            final PsiNamedElement psiNamedElement2 = psiNamedElement;
                            final OCReferenceElement ocReferenceElement2 = ocReferenceElement;
                            final PsiElement psiElement = ocReferenceElement2.resolve();
                            final boolean b = psiNamedElement2.equals(psiElement);
                            if (b) {
                                return true;
                            }
                            break Label_0055;
                        }
                        catch (IllegalStateException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final PsiNamedElement psiNamedElement2 = psiNamedElement;
                            final OCReferenceElement ocReferenceElement2 = ocReferenceElement;
                            final PsiElement psiElement = ocReferenceElement2.resolve();
                            final boolean b = psiNamedElement2.equals(psiElement);
                            if (b) {
                                return true;
                            }
                        }
                        catch (IllegalStateException ex3) {
                            throw a(ex3);
                        }
                        try {
                            if (ref.isNull()) {
                                ref.set((Object)ocExpression);
                                return true;
                            }
                        }
                        catch (IllegalStateException ex4) {
                            throw a(ex4);
                        }
                    }
                    try {
                        if (!OCElementUtil.areElementsEquivalent((PsiElement)ref.get(), (PsiElement)ocExpression, true)) {
                            return false;
                        }
                    }
                    catch (IllegalStateException ex5) {
                        throw a(ex5);
                    }
                    return true;
                }))) {
                    return "There are several call sites with different parameter initializers";
                }
            }
            catch (IllegalStateException ex9) {
                throw a(ex9);
            }
        }
        try {
            if (ref.isNull()) {
                return "There are no usages of " + nameWithKindLowercase;
            }
        }
        catch (IllegalStateException ex10) {
            throw a(ex10);
        }
        final OCCodeMoveValidator ocCodeMoveValidator = new OCCodeMoveValidator((PsiElement)ocCallable.getBody());
        try {
            ((PsiElement)ref.get()).accept((PsiElementVisitor)ocCodeMoveValidator);
            if (ocCodeMoveValidator.isOutOfScope()) {
                list2.add("Parameter initializer is not available in " + nameWithKindLowercase + ": " + ocCodeMoveValidator.getOutOfScopeMessage() + ".");
            }
        }
        catch (IllegalStateException ex11) {
            throw a(ex11);
        }
        return null;
    }
    
    private static boolean a(final OCSymbol p0, final int p1, final Processor<OCExpression> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/intellij/util/containers/hash/HashSet;
        //     3: dup            
        //     4: invokespecial   com/intellij/util/containers/hash/HashSet.<init>:()V
        //     7: astore_3       
        //     8: aload_0        
        //     9: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //    12: aload_3        
        //    13: iconst_1       
        //    14: iconst_1       
        //    15: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.findAllMemberUsages:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;Ljava/util/Set;ZZ)Z
        //    18: pop            
        //    19: aload_3        
        //    20: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    25: astore          4
        //    27: aload           4
        //    29: invokeinterface java/util/Iterator.hasNext:()Z
        //    34: ifeq            375
        //    37: aload           4
        //    39: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    44: checkcast       Lcom/intellij/usageView/UsageInfo;
        //    47: astore          5
        //    49: aload           5
        //    51: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //    54: astore          6
        //    56: aload           5
        //    58: instanceof      Lcom/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCMethodCallUsage;
        //    61: ifeq            143
        //    64: aload           6
        //    66: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //    69: ifeq            143
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineParameterHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    78: athrow         
        //    79: aload           6
        //    81: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //    84: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArguments:()Ljava/util/List;
        //    89: astore          7
        //    91: aload           7
        //    93: invokeinterface java/util/List.size:()I
        //    98: iload_1        
        //    99: if_icmple       140
        //   102: aload_2        
        //   103: aload           7
        //   105: iload_1        
        //   106: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   111: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMessageArgument;
        //   114: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getArgumentExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   119: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   124: ifne            140
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineParameterHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   133: athrow         
        //   134: iconst_0       
        //   135: ireturn        
        //   136: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineParameterHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   139: athrow         
        //   140: goto            372
        //   143: aload           5
        //   145: instanceof      Lcom/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCMethodDotCallUsage;
        //   148: ifeq            261
        //   151: aload           6
        //   153: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   156: ifeq            261
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineParameterHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   165: athrow         
        //   166: iload_1        
        //   167: ifne            261
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineParameterHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   176: athrow         
        //   177: aload           6
        //   179: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   182: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   185: astore          7
        //   187: aload           7
        //   189: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   194: astore          8
        //   196: aload           8
        //   198: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   201: ifeq            258
        //   204: aload           8
        //   206: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   209: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   214: aload           7
        //   216: if_acmpne       258
        //   219: goto            226
        //   222: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineParameterHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   225: athrow         
        //   226: aload_2        
        //   227: aload           8
        //   229: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   232: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getSourceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   237: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   242: ifne            258
        //   245: goto            252
        //   248: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineParameterHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   251: athrow         
        //   252: iconst_0       
        //   253: ireturn        
        //   254: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineParameterHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   257: athrow         
        //   258: goto            372
        //   261: aload           5
        //   263: instanceof      Lcom/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage;
        //   266: ifeq            372
        //   269: aload           6
        //   271: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   274: ifeq            372
        //   277: goto            284
        //   280: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineParameterHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   283: athrow         
        //   284: aload           6
        //   286: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   291: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   296: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //   299: ifeq            372
        //   302: goto            309
        //   305: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineParameterHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   308: athrow         
        //   309: aload           6
        //   311: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   316: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   321: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //   324: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getArguments:()Ljava/util/List;
        //   329: astore          7
        //   331: aload           7
        //   333: invokeinterface java/util/List.size:()I
        //   338: iload_1        
        //   339: if_icmple       372
        //   342: aload_2        
        //   343: aload           7
        //   345: iload_1        
        //   346: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   351: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   356: ifne            372
        //   359: goto            366
        //   362: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineParameterHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   365: athrow         
        //   366: iconst_0       
        //   367: ireturn        
        //   368: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineParameterHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   371: athrow         
        //   372: goto            27
        //   375: iconst_1       
        //   376: ireturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/symbols/OCSymbol;ILcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/psi/OCExpression;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  56     72     75     79     Ljava/lang/IllegalStateException;
        //  91     127    130    134    Ljava/lang/IllegalStateException;
        //  102    136    136    140    Ljava/lang/IllegalStateException;
        //  143    159    162    166    Ljava/lang/IllegalStateException;
        //  151    170    173    177    Ljava/lang/IllegalStateException;
        //  196    219    222    226    Ljava/lang/IllegalStateException;
        //  204    245    248    252    Ljava/lang/IllegalStateException;
        //  226    254    254    258    Ljava/lang/IllegalStateException;
        //  261    277    280    284    Ljava/lang/IllegalStateException;
        //  269    302    305    309    Ljava/lang/IllegalStateException;
        //  331    359    362    366    Ljava/lang/IllegalStateException;
        //  342    368    368    372    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0226:
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
    protected void inlineUsage(PsiElement replaceExpressionAndAppendParentheses, final PsiNamedElement psiNamedElement, final PsiElement psiElement, final Project project, final Map<SmartPsiElementPointer, Pair<OCSymbol, OCVisibility>> map) {
        Label_0031: {
            try {
                if (!(replaceExpressionAndAppendParentheses instanceof OCReferenceElement)) {
                    return;
                }
                final PsiElement psiElement2 = replaceExpressionAndAppendParentheses;
                final PsiElement psiElement3 = psiElement2.getParent();
                final boolean b = psiElement3 instanceof OCReferenceExpression;
                if (!b) {
                    return;
                }
                break Label_0031;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final PsiElement psiElement2 = replaceExpressionAndAppendParentheses;
                final PsiElement psiElement3 = psiElement2.getParent();
                final boolean b = psiElement3 instanceof OCReferenceExpression;
                if (!b) {
                    return;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        replaceExpressionAndAppendParentheses = OCParenthesesUtils.replaceExpressionAndAppendParentheses((OCExpression)replaceExpressionAndAppendParentheses.getParent(), (OCExpression)psiElement);
        OCImportSymbolFix.fixAllSymbolsRecursively(replaceExpressionAndAppendParentheses);
    }
    
    @Override
    protected void deleteElement(final PsiNamedElement psiNamedElement, final PsiElement psiElement) {
        final OCCallable ocCallable = (OCCallable)PsiTreeUtil.getParentOfType((PsiElement)psiNamedElement, (Class)OCCallable.class);
        final OCChangeSignatureHandler handler = OCChangeSignatureActionHandler.getHandler(ocCallable, true);
        handler.setChangeAncestors(true);
        handler.removeParameter(a(psiNamedElement, ocCallable));
        handler.invokeSynchronously();
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.inlineParameter";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/inline/OCInlineParameterHandler", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCInlineParameterHandler.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
