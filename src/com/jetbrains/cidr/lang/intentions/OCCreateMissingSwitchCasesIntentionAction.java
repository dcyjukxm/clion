// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import java.util.Set;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.types.OCTypeGuesser;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.types.OCStructType;
import java.util.HashSet;
import java.util.Collections;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.Pair;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.psi.OCNamespaceQualifierOwner;
import com.jetbrains.cidr.lang.refactoring.util.OCBindUtil;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCCaseStatement;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCSwitchStatement;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.codeInsight.intention.IntentionAction;

public class OCCreateMissingSwitchCasesIntentionAction implements IntentionAction
{
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Create missing switch cases";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction", "getText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    public boolean isAvailable(@NotNull final Project p0, final Editor p1, final PsiFile p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_2        
        //    46: aload_3        
        //    47: invokevirtual   com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction.getSwitchStatement:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/psi/OCSwitchStatement;
        //    50: astore          4
        //    52: aload           4
        //    54: ifnull          106
        //    57: aload           4
        //    59: invokeinterface com/jetbrains/cidr/lang/psi/OCSwitchStatement.getBody:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    64: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //    67: ifeq            106
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    76: athrow         
        //    77: aload_0        
        //    78: aload           4
        //    80: invokespecial   com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction.a:(Lcom/jetbrains/cidr/lang/psi/OCSwitchStatement;)Ljava/util/List;
        //    83: invokeinterface java/util/List.isEmpty:()Z
        //    88: ifne            106
        //    91: goto            98
        //    94: invokestatic    com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    97: athrow         
        //    98: iconst_1       
        //    99: goto            107
        //   102: invokestatic    com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   105: athrow         
        //   106: iconst_0       
        //   107: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  52     70     73     77     Lcom/intellij/util/IncorrectOperationException;
        //  57     91     94     98     Lcom/intellij/util/IncorrectOperationException;
        //  77     102    102    106    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0077:
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
    
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCSwitchStatement switchStatement = this.getSwitchStatement(editor, psiFile);
        try {
            if (switchStatement == null || !OCCodeInsightUtil.isValid((PsiElement)switchStatement)) {
                return;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final OCStatement body = switchStatement.getBody();
        try {
            if (body != null) {
                this.invoke(body, this.a(switchStatement));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
    }
    
    protected OCSwitchStatement getSwitchStatement(final Editor editor, final PsiFile psiFile) {
        return OCElementUtil.getAdjacentParentOfType(psiFile.findElementAt(editor.getCaretModel().getOffset()), (Class<? extends OCSwitchStatement>)OCSwitchStatement.class);
    }
    
    protected void invoke(final OCStatement ocStatement, final List<OCDeclaratorSymbol> list) {
        for (final OCDeclaratorSymbol ocDeclaratorSymbol : list) {
            String s = ocDeclaratorSymbol.getName();
            if (ocDeclaratorSymbol.getParent() != null) {
                s = ocDeclaratorSymbol.getParent().getQualifiedName().getNameWithParent() + "::" + s;
            }
            final OCReferenceExpression ocReferenceExpression = (OCReferenceExpression)OCChangeUtil.add((PsiElement)ocStatement, OCElementFactory.statementFromText("case " + s + ":break;", (PsiElement)ocStatement)).getExpression();
            if (ocReferenceExpression != null) {
                final OCReferenceElement referenceElement = ocReferenceExpression.getReferenceElement();
                try {
                    if (referenceElement == null) {
                        continue;
                    }
                    OCBindUtil.setShortestPossibleName(ocDeclaratorSymbol.getResolvedQualifiedName(), referenceElement, ocDeclaratorSymbol);
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
            }
        }
    }
    
    public boolean startInWriteAction() {
        return true;
    }
    
    private List<OCDeclaratorSymbol> a(@NotNull final OCSwitchStatement ocSwitchStatement) {
        try {
            if (ocSwitchStatement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stmt", "com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction", "getMissingCases"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        if (OCCodeInsightUtil.isValid((PsiElement)ocSwitchStatement)) {
            final OCStatement body = ocSwitchStatement.getBody();
            final ArrayList<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
            final ArrayList<OCCaseStatement> list2 = new ArrayList<OCCaseStatement>();
            boolean caseStatements = true;
            if (body != null) {
                caseStatements = findCaseStatements(body, list, list2);
            }
            try {
                if (this.proceedIfDefaultOrUnresolved(caseStatements)) {
                    return getMissingCases(ocSwitchStatement, list, (Ref<Boolean>)Ref.create((Object)false));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return Collections.emptyList();
    }
    
    protected boolean proceedIfDefaultOrUnresolved(final boolean b) {
        return b;
    }
    
    @NotNull
    public static List<OCDeclaratorSymbol> getMissingCases(@NotNull final OCSwitchStatement ocSwitchStatement, final List<Pair<Integer, Integer>> list, final Ref<Boolean> ref) {
        try {
            if (ocSwitchStatement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stmt", "com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction", "getMissingCases"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCDeclarationOrExpression expression = ocSwitchStatement.getExpression();
        Label_0104: {
            List<OCDeclaratorSymbol> list2 = null;
            Label_0069: {
                try {
                    if (expression != null) {
                        break Label_0104;
                    }
                    list2 = Collections.emptyList();
                    if (list2 == null) {
                        break Label_0069;
                    }
                    return list2;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    list2 = Collections.emptyList();
                    if (list2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction", "getMissingCases"));
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            return list2;
        }
        final OCType resolvedType = expression.getResolvedType();
        final ArrayList<Object> list3 = new ArrayList<Object>();
        final Processor processor = ocSymbol -> {
            try {
                if (ocSwitchStatement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stmt", "com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction", "lambda$getMissingCases$0"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            final Integer evaluateEnumConst = OCExpressionEvaluator.evaluateEnumConst(ocSymbol, ocSwitchStatement.getContainingFile());
            boolean b = false;
            ref.set((Object)true);
            if (evaluateEnumConst != null) {
                for (final Pair pair : list) {
                    try {
                        if ((int)pair.getFirst() > evaluateEnumConst || evaluateEnumConst > (int)pair.getSecond()) {
                            continue;
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                    Label_0148: {
                        break Label_0148;
                        continue;
                    }
                    b = true;
                    break;
                }
                Label_0180: {
                    try {
                        if (b) {
                            return true;
                        }
                        final Set<Integer> set2 = (Set<Integer>)set;
                        final Integer n = evaluateEnumConst;
                        final boolean b2 = set2.contains(n);
                        if (!b2) {
                            break Label_0180;
                        }
                        return true;
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final Set<Integer> set2 = (Set<Integer>)set;
                        final Integer n = evaluateEnumConst;
                        final boolean b2 = set2.contains(n);
                        if (!b2) {
                            list3.add(ocSymbol);
                            set.add(evaluateEnumConst);
                        }
                    }
                    catch (IncorrectOperationException ex4) {
                        throw a(ex4);
                    }
                }
            }
            return true;
        };
        if (resolvedType.getTerminalType() instanceof OCStructType) {
            final Iterator<OCDeclaratorSymbol> iterator = ((OCStructType)resolvedType.getTerminalType()).getFields().iterator();
            while (iterator.hasNext()) {
                processor.process((Object)iterator.next());
            }
        }
        else {
            try {
                if (resolvedType.getTerminalType() instanceof OCIntType) {
                    OCTypeGuesser.processGuessedEnumConsts((OCIntType)resolvedType.getTerminalType(), (Processor<OCSymbol>)processor, (PsiElement)ocSwitchStatement);
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        ArrayList<Object> list4;
        try {
            Collections.sort(list3, (ocDeclaratorSymbol, ocDeclaratorSymbol2) -> OCSymbolOffsetUtil.compare(ocDeclaratorSymbol.getComplexOffset(), ocDeclaratorSymbol2.getComplexOffset()));
            list4 = list3;
            if (list4 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction", "getMissingCases"));
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        return (List<OCDeclaratorSymbol>)list4;
    }
    
    public static boolean findCaseStatements(@NotNull final OCStatement ocStatement, final List<Pair<Integer, Integer>> list, final List<OCCaseStatement> list2) {
        try {
            if (ocStatement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "body", "com/jetbrains/cidr/lang/intentions/OCCreateMissingSwitchCasesIntentionAction", "findCaseStatements"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final Ref create = Ref.create((Object)false);
        final Ref create2 = Ref.create((Object)false);
        Label_0113: {
            try {
                ocStatement.accept((PsiElementVisitor)new OCRecursiveVisitor() {
                    @Override
                    public void visitCaseStatement(final OCCaseStatement ocCaseStatement) {
                        super.visitCaseStatement(ocCaseStatement);
                        Pair pair = null;
                        if (ocCaseStatement.getExpression() != null) {
                            final Number evaluate = OCExpressionEvaluator.evaluate(ocCaseStatement.getExpression());
                            if (evaluate != null) {
                                pair = Pair.create((Object)evaluate.intValue(), (Object)evaluate.intValue());
                            }
                            else {
                                create2.set((Object)true);
                            }
                        }
                        else if (ocCaseStatement.getRangeFirst() != null && ocCaseStatement.getRangeSecond() != null) {
                            final Number evaluate2 = OCExpressionEvaluator.evaluate(ocCaseStatement.getRangeFirst());
                            final Number evaluate3 = OCExpressionEvaluator.evaluate(ocCaseStatement.getRangeSecond());
                            if (evaluate2 != null && evaluate3 != null) {
                                pair = Pair.create((Object)evaluate2.intValue(), (Object)evaluate3.intValue());
                            }
                            else {
                                create2.set((Object)true);
                            }
                        }
                        else if (ocCaseStatement.isDefault()) {
                            create.set((Object)true);
                        }
                        if (pair != null) {
                            list.add(pair);
                            list2.add(ocCaseStatement);
                        }
                    }
                    
                    @Override
                    public void visitSwitchStatement(final OCSwitchStatement ocSwitchStatement) {
                    }
                });
                if (create.get()) {
                    break Label_0113;
                }
                final Ref ref = create2;
                final Object o = ref.get();
                final Boolean b = (Boolean)o;
                final boolean b2 = b;
                if (b2) {
                    break Label_0113;
                }
                return false;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final Ref ref = create2;
                final Object o = ref.get();
                final Boolean b = (Boolean)o;
                final boolean b2 = b;
                if (b2) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
