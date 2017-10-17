// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.util.NotNullFunction;
import com.jetbrains.cidr.lang.psi.OCIfStatement;

public class OCIfRespondsToSurrounder extends OCStatementSurrounder<OCIfStatement>
{
    private static final NotNullFunction<PsiElement, TextRange> RANGER;
    
    @Override
    protected String getStatementTemplate(final Project project, final PsiElement psiElement) {
        return "if ([x respondsToSelector:@selector(selector)]) {\n}";
    }
    
    @Override
    public TextRange surroundElements(@NotNull final Project p0, @NotNull final Editor p1, @NotNull final PsiElement[] p2) throws IncorrectOperationException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "surroundElements"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "editor"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "surroundElements"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "elements"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "surroundElements"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   131: athrow         
        //   132: aload_3        
        //   133: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder.a:([Lcom/intellij/psi/PsiElement;)Ljava/util/List;
        //   136: astore          4
        //   138: aload           4
        //   140: invokeinterface java/util/List.size:()I
        //   145: iconst_1       
        //   146: if_icmpeq       184
        //   149: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   152: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   157: ifeq            199
        //   160: goto            167
        //   163: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   166: athrow         
        //   167: aload           4
        //   169: invokeinterface java/util/List.isEmpty:()Z
        //   174: ifne            199
        //   177: goto            184
        //   180: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   183: athrow         
        //   184: aload_0        
        //   185: aload_1        
        //   186: aload_3        
        //   187: iconst_0       
        //   188: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   191: invokevirtual   com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder.doSurroundElements:(Lcom/intellij/openapi/project/Project;[Lcom/intellij/psi/PsiElement;Ljava/lang/Object;)Lcom/intellij/openapi/util/TextRange;
        //   194: areturn        
        //   195: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   198: athrow         
        //   199: aload           4
        //   201: invokeinterface java/util/List.isEmpty:()Z
        //   206: ifne            268
        //   209: aload_2        
        //   210: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   215: aload_3        
        //   216: aload_3        
        //   217: arraylength    
        //   218: iconst_1       
        //   219: isub           
        //   220: aaload         
        //   221: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   226: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //   231: aload_2        
        //   232: aload           4
        //   234: new             Lcom/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder$1;
        //   237: dup            
        //   238: aload_0        
        //   239: aload_1        
        //   240: aload_3        
        //   241: aload           4
        //   243: invokespecial   com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder$1.<init>:(Lcom/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder;Lcom/intellij/openapi/project/Project;[Lcom/intellij/psi/PsiElement;Ljava/util/List;)V
        //   246: new             Lcom/jetbrains/cidr/lang/refactoring/OCExpressionTrimRenderer$RenderFunction;
        //   249: dup            
        //   250: invokespecial   com/jetbrains/cidr/lang/refactoring/OCExpressionTrimRenderer$RenderFunction.<init>:()V
        //   253: ldc             "Expressions"
        //   255: getstatic       com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder.RANGER:Lcom/intellij/util/NotNullFunction;
        //   258: invokestatic    com/intellij/refactoring/IntroduceTargetChooser.showChooser:(Lcom/intellij/openapi/editor/Editor;Ljava/util/List;Lcom/intellij/openapi/util/Pass;Lcom/intellij/util/Function;Ljava/lang/String;Lcom/intellij/util/NotNullFunction;)V
        //   261: goto            280
        //   264: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   267: athrow         
        //   268: aload_1        
        //   269: aload_2        
        //   270: ldc             "No send message expressions in the selected code"
        //   272: aload_0        
        //   273: invokevirtual   com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder.getTemplateDescription:()Ljava/lang/String;
        //   276: aconst_null    
        //   277: invokestatic    com/intellij/refactoring/util/CommonRefactoringUtil.showErrorHint:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   280: aload_3        
        //   281: iconst_0       
        //   282: aaload         
        //   283: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   288: areturn        
        //    Exceptions:
        //  throws com.intellij.util.IncorrectOperationException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  88     128    128    132    Lcom/intellij/util/IncorrectOperationException;
        //  138    160    163    167    Lcom/intellij/util/IncorrectOperationException;
        //  149    177    180    184    Lcom/intellij/util/IncorrectOperationException;
        //  167    195    195    199    Lcom/intellij/util/IncorrectOperationException;
        //  199    264    264    268    Lcom/intellij/util/IncorrectOperationException;
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
    protected void insertOldElements(final PsiElement[] array, final PsiElement psiElement, final OCIfStatement ocIfStatement, @NotNull final Object o) {
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chosenElement", "com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder", "insertOldElements"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        OCSendMessageExpression ocSendMessageExpression = null;
        Label_0085: {
            try {
                if (o instanceof OCSendMessageExpression) {
                    ocSendMessageExpression = (OCSendMessageExpression)o;
                    break Label_0085;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw c(ex2);
            }
            ocSendMessageExpression = a(array).get((int)o);
        }
        final OCSendMessageExpression ocSendMessageExpression2 = ocSendMessageExpression;
        final OCSendMessageExpression ocSendMessageExpression3 = (OCSendMessageExpression)ocIfStatement.getCondition().getExpression();
        final OCReferenceExpression ocReferenceExpression = (OCReferenceExpression)ocSendMessageExpression3.getReceiverExpression();
        final OCExpression receiverExpression = ocSendMessageExpression2.getReceiverExpression();
        Label_0304: {
            try {
                ((OCSelectorExpression)ocSendMessageExpression3.getArgumentExpressions().get(0)).getReference().handleElementRename(ocSendMessageExpression2.getMessageSelector());
                if (receiverExpression instanceof OCReferenceExpression) {
                    ocReferenceExpression.getReferenceElement().setNameOfIdentifier(((OCReferenceExpression)receiverExpression).getReferenceElement().getName());
                    break Label_0304;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw c(ex3);
            }
            if (receiverExpression != null) {
                final Collection<String> suggestForExpression = OCNameSuggester.suggestForExpression(OCSymbolKind.LOCAL_VARIABLE, receiverExpression);
                String s = null;
                Label_0250: {
                    try {
                        if (suggestForExpression.isEmpty()) {
                            s = "x";
                            break Label_0250;
                        }
                    }
                    catch (IncorrectOperationException ex4) {
                        throw c(ex4);
                    }
                    s = suggestForExpression.iterator().next();
                }
                final String nameOfIdentifier = s;
                ocReferenceExpression.getReferenceElement().setNameOfIdentifier(nameOfIdentifier);
                OCChangeUtil.addBefore(psiElement, OCElementFactory.declarationStatement(nameOfIdentifier, receiverExpression.getType(), receiverExpression, (PsiElement)ocIfStatement), (PsiElement)ocIfStatement);
                OCChangeUtil.replaceHandlingMacros((PsiElement)receiverExpression, (PsiElement)OCElementFactory.expressionFromText(nameOfIdentifier, (PsiElement)ocIfStatement));
            }
        }
        super.insertOldElements(array, psiElement, ocIfStatement, o);
    }
    
    private static List<OCSendMessageExpression> a(final PsiElement[] array) {
        final ArrayList<OCSendMessageExpression> list = new ArrayList<OCSendMessageExpression>();
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i].accept((PsiElementVisitor)new OCRecursiveVisitor() {
                @Override
                public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
                    final OCMethodSymbol knownResponder = ocSendMessageExpression.getProbableResponders().getKnownResponder();
                    if (knownResponder == null || knownResponder.isOptional() || !((OCSymbolWithParent<T, OCClassSymbol>)knownResponder).getParent().getName().equals("NSObject") || ((OCSymbolWithParent<T, OCClassSymbol>)knownResponder).getParent().getCategoryName() != null) {
                        list.add(ocSendMessageExpression);
                    }
                    super.visitSendMessageExpression(ocSendMessageExpression);
                }
            });
        }
        return list;
    }
    
    @Override
    protected PsiElement getInsertionPlace(final OCIfStatement ocIfStatement) {
        return (PsiElement)ocIfStatement.getThenBranch();
    }
    
    @Override
    protected TextRange getSelectionRange(final OCIfStatement ocIfStatement) {
        final int endOffset = ocIfStatement.getTextRange().getEndOffset();
        return new TextRange(endOffset, endOffset);
    }
    
    public String getTemplateDescription() {
        return "if respondsToSelector";
    }
    
    @Override
    protected OCLanguageKind getLanguageKind() {
        return OCLanguageKind.OBJ_C;
    }
    
    static {
        RANGER = (psiElement -> OCElementUtil.getRangeWithMacros(psiElement));
    }
    
    private static IncorrectOperationException c(final IncorrectOperationException ex) {
        return ex;
    }
}
