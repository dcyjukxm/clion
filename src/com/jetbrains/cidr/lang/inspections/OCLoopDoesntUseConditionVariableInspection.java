// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import java.util.Set;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.text.StringUtil;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.dfa.OCDataFlowAnalyzer;
import com.jetbrains.cidr.lang.dfa.OCControlFlowBuilder;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.impl.OCMacroReferenceElementImpl;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import java.util.Iterator;
import com.intellij.codeInspection.GlobalInspectionContext;
import com.intellij.codeInspection.ProblemDescriptionsProcessor;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.ProblemHighlightType;
import java.util.Collection;
import com.intellij.util.PairProcessor;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import java.util.HashSet;
import com.intellij.openapi.util.Ref;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCDoWhileStatement;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCWhileStatement;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCLoopStatement;
import com.jetbrains.cidr.lang.psi.OCForStatement;
import com.jetbrains.cidr.lang.dfa.OCControlFlowGraph;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.util.containers.hash.HashMap;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;

public class OCLoopDoesntUseConditionVariableInspection extends OCInspections.GeneralCpp
{
    @Nls
    @NotNull
    @Override
    public String getDisplayName() {
        String s;
        try {
            s = "Loop condition isn't updated inside the loop";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection", "getDisplayName"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        return s;
    }
    
    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder problemsHolder, final boolean b) {
        try {
            if (problemsHolder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "holder", "com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection", "buildVisitor"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        ForLoopThatDoesntUseLoopVariableVisitor forLoopThatDoesntUseLoopVariableVisitor;
        try {
            forLoopThatDoesntUseLoopVariableVisitor = new ForLoopThatDoesntUseLoopVariableVisitor(problemsHolder);
            if (forLoopThatDoesntUseLoopVariableVisitor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection", "buildVisitor"));
            }
        }
        catch (IllegalStateException ex2) {
            throw d(ex2);
        }
        return forLoopThatDoesntUseLoopVariableVisitor;
    }
    
    private static IllegalStateException d(final IllegalStateException ex) {
        return ex;
    }
    
    private class ForLoopThatDoesntUseLoopVariableVisitor extends OCVisitor
    {
        private final ProblemsHolder myHolder;
        private final HashMap<OCCallable, OCControlFlowGraph> myGraphs;
        
        public ForLoopThatDoesntUseLoopVariableVisitor(final ProblemsHolder myHolder) {
            this.myHolder = myHolder;
            this.myGraphs = (HashMap<OCCallable, OCControlFlowGraph>)new HashMap();
        }
        
        @Override
        public void visitForStatement(@NotNull final OCForStatement ocForStatement) {
            try {
                if (ocForStatement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "statement", "com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor", "visitForStatement"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.a(ocForStatement, ocForStatement.getCondition(), ocForStatement.getBody(), ocForStatement.getIncrement());
        }
        
        @Override
        public void visitWhileStatement(final OCWhileStatement ocWhileStatement) {
            this.a(ocWhileStatement, ocWhileStatement.getCondition(), ocWhileStatement.getBody(), null);
        }
        
        @Override
        public void visitDoWhileStatement(final OCDoWhileStatement ocDoWhileStatement) {
            this.a(ocDoWhileStatement, ocDoWhileStatement.getCondition(), ocDoWhileStatement.getBody(), null);
        }
        
        private void a(@NotNull final OCLoopStatement ocLoopStatement, @Nullable final OCElement ocElement, @Nullable final OCStatement ocStatement, @Nullable final OCStatement ocStatement2) {
            try {
                if (ocLoopStatement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "loop", "com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor", "processLoop"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (ocElement == null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final Ref ref = new Ref((Object)false);
            final HashSet<OCSymbolDeclarator> set = new HashSet<OCSymbolDeclarator>();
            try {
                if (OCCodeInsightUtil.hasSideEffects(ocElement)) {
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            final OCCallable a = this.a((PsiElement)ocLoopStatement);
            try {
                if (a == null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            final OCControlFlowGraph a2 = this.a(a);
            Label_0162: {
                try {
                    this.a(ocElement, (PairProcessor<OCSymbolDeclarator, OCElement>)((p2, p3) -> {
                        // 
                        // This method could not be decompiled.
                        // 
                        // Original Bytecode:
                        // 
                        //     0: aload_2        
                        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
                        //     6: astore          4
                        //     8: aload           4
                        //    10: ifnull          66
                        //    13: aload           4
                        //    15: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                        //    20: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isLocal:()Z
                        //    23: ifeq            66
                        //    26: goto            33
                        //    29: invokestatic    com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                        //    32: athrow         
                        //    33: aload           4
                        //    35: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
                        //    38: ifeq            80
                        //    41: goto            48
                        //    44: invokestatic    com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                        //    47: athrow         
                        //    48: aload           4
                        //    50: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
                        //    53: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isBlockModifiable:()Z
                        //    56: ifeq            80
                        //    59: goto            66
                        //    62: invokestatic    com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                        //    65: athrow         
                        //    66: aload_0        
                        //    67: iconst_1       
                        //    68: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
                        //    71: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
                        //    74: iconst_0       
                        //    75: ireturn        
                        //    76: invokestatic    com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                        //    79: athrow         
                        //    80: aload_1        
                        //    81: aload_2        
                        //    82: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
                        //    87: pop            
                        //    88: iconst_1       
                        //    89: ireturn        
                        //    Exceptions:
                        //  Try           Handler
                        //  Start  End    Start  End    Type                                
                        //  -----  -----  -----  -----  ------------------------------------
                        //  8      26     29     33     Ljava/lang/IllegalArgumentException;
                        //  13     41     44     48     Ljava/lang/IllegalArgumentException;
                        //  33     59     62     66     Ljava/lang/IllegalArgumentException;
                        //  48     76     76     80     Ljava/lang/IllegalArgumentException;
                        // 
                        // The error that occurred was:
                        // 
                        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0033:
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
                        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
                    }));
                    if (ref.get()) {
                        return;
                    }
                    final HashSet<OCSymbolDeclarator> set2 = set;
                    final boolean b = set2.isEmpty();
                    if (b) {
                        return;
                    }
                    break Label_0162;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    final HashSet<OCSymbolDeclarator> set2 = set;
                    final boolean b = set2.isEmpty();
                    if (b) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            for (final OCSymbolDeclarator ocSymbolDeclarator : set) {
                try {
                    if (this.a(ocSymbolDeclarator, a2, ocStatement, ocStatement2)) {
                        ref.set((Object)true);
                        break;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            try {
                if (!(boolean)ref.get()) {
                    OCLoopDoesntUseConditionVariableInspection.this.registerProblem(this.myHolder, null, null, this.myHolder.isOnTheFly(), (PsiElement)ocElement, this.a((Collection<OCSymbolDeclarator>)set), "warn_variables_not_in_loop_body", ProblemHighlightType.GENERIC_ERROR_OR_WARNING, new IntentionAction[0]);
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        
        private void a(@NotNull final OCElement ocElement, @NotNull final PairProcessor<OCSymbolDeclarator, OCElement> pairProcessor) {
            try {
                if (ocElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "condition", "com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor", "processAllUsedSymbol"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (pairProcessor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor", "processAllUsedSymbol"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            ocElement.accept((PsiElementVisitor)new OCRecursiveVisitor() {
                public boolean myStopped = false;
                
                @Override
                public void visitReferenceElement(final OCReferenceElement ocReferenceElement) {
                    this.a(ocReferenceElement);
                    super.visitReferenceElement(ocReferenceElement);
                }
                
                @Override
                public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
                    this.a(ocQualifiedExpression);
                    super.visitQualifiedExpression(ocQualifiedExpression);
                }
                
                @Override
                public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
                    this.a(ocSendMessageExpression);
                    super.visitSendMessageExpression(ocSendMessageExpression);
                }
                
                @Override
                public void visitExpression(final OCExpression ocExpression) {
                    this.a(ocExpression);
                    super.visitExpression(ocExpression);
                }
                
                private void a(final OCElement ocElement) {
                    if (!this.myStopped) {
                        final PsiReference reference = ocElement.getReference();
                        if (reference != null) {
                            final PsiElement resolve = reference.resolve();
                            if (resolve instanceof OCSymbolDeclarator && !(resolve instanceof OCDefineDirective) && !(resolve instanceof OCMacroReferenceElementImpl) && !pairProcessor.process((Object)resolve, (Object)ocElement)) {
                                this.myStopped = true;
                            }
                        }
                    }
                }
            });
        }
        
        private boolean a(@NotNull final OCSymbolDeclarator p0, @NotNull final OCControlFlowGraph p1, @Nullable final OCStatement p2, @Nullable final OCStatement p3) {
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
            //    18: ldc             "declarator"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "declaratorIsUsed"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
            //    62: ldc             "graph"
            //    64: aastore        
            //    65: dup            
            //    66: ldc             1
            //    68: ldc             "com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor"
            //    70: aastore        
            //    71: dup            
            //    72: ldc             2
            //    74: ldc             "declaratorIsUsed"
            //    76: aastore        
            //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    83: athrow         
            //    84: invokestatic    com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    87: athrow         
            //    88: aload_1        
            //    89: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    94: astore          5
            //    96: aload           5
            //    98: ifnonnull       107
            //   101: iconst_1       
            //   102: ireturn        
            //   103: invokestatic    com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   106: athrow         
            //   107: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   110: dup            
            //   111: aload_1        
            //   112: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
            //   115: astore          6
            //   117: aload           5
            //   119: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   124: aload           6
            //   126: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
            //   129: astore          7
            //   131: aload           7
            //   133: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVolatile:()Z
            //   136: ifne            183
            //   139: aload           7
            //   141: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
            //   144: ifne            183
            //   147: goto            154
            //   150: invokestatic    com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   153: athrow         
            //   154: aload           7
            //   156: iconst_2       
            //   157: anewarray       Ljava/lang/Class;
            //   160: dup            
            //   161: iconst_0       
            //   162: ldc             Lcom/jetbrains/cidr/lang/types/OCStructType;.class
            //   164: aastore        
            //   165: dup            
            //   166: iconst_1       
            //   167: ldc             Lcom/jetbrains/cidr/lang/types/OCMagicType;.class
            //   169: aastore        
            //   170: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.isInstanceOfType:(Lcom/jetbrains/cidr/lang/types/OCType;[Ljava/lang/Class;)Z
            //   173: ifeq            189
            //   176: goto            183
            //   179: invokestatic    com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   182: athrow         
            //   183: iconst_1       
            //   184: ireturn        
            //   185: invokestatic    com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   188: athrow         
            //   189: aload_2        
            //   190: aload           5
            //   192: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getInstructions:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/intellij/util/containers/MultiMap;
            //   195: astore          8
            //   197: aload           8
            //   199: ifnonnull       208
            //   202: iconst_0       
            //   203: ireturn        
            //   204: invokestatic    com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   207: athrow         
            //   208: aload           8
            //   210: invokevirtual   com/intellij/util/containers/MultiMap.values:()Ljava/util/Collection;
            //   213: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
            //   218: astore          9
            //   220: aload           9
            //   222: invokeinterface java/util/Iterator.hasNext:()Z
            //   227: ifeq            388
            //   230: aload           9
            //   232: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //   237: checkcast       Lcom/jetbrains/cidr/lang/dfa/OCInstruction;
            //   240: astore          10
            //   242: getstatic       com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$1.$SwitchMap$com$jetbrains$cidr$lang$dfa$OCInstruction$InstructionKind:[I
            //   245: aload           10
            //   247: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getKind:()Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
            //   250: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.ordinal:()I
            //   253: iaload         
            //   254: tableswitch {
            //                2: 288
            //                3: 294
            //                4: 296
            //                5: 317
            //                6: 340
            //          default: 385
            //        }
            //   288: iconst_1       
            //   289: ireturn        
            //   290: invokestatic    com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   293: athrow         
            //   294: iconst_1       
            //   295: ireturn        
            //   296: aload_0        
            //   297: aload_3        
            //   298: aload           4
            //   300: aload           10
            //   302: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getLValue:()Lcom/intellij/psi/PsiElement;
            //   305: invokespecial   com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Lcom/jetbrains/cidr/lang/psi/OCStatement;Lcom/jetbrains/cidr/lang/psi/OCStatement;Lcom/intellij/psi/PsiElement;)Z
            //   308: ifeq            385
            //   311: iconst_1       
            //   312: ireturn        
            //   313: invokestatic    com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   316: athrow         
            //   317: aload           7
            //   319: iconst_1       
            //   320: anewarray       Ljava/lang/Class;
            //   323: dup            
            //   324: iconst_0       
            //   325: ldc             Lcom/jetbrains/cidr/lang/types/OCPointerType;.class
            //   327: aastore        
            //   328: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.isInstanceOfType:(Lcom/jetbrains/cidr/lang/types/OCType;[Ljava/lang/Class;)Z
            //   331: ifeq            385
            //   334: iconst_1       
            //   335: ireturn        
            //   336: invokestatic    com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   339: athrow         
            //   340: aload           7
            //   342: iconst_1       
            //   343: anewarray       Ljava/lang/Class;
            //   346: dup            
            //   347: iconst_0       
            //   348: ldc             Lcom/jetbrains/cidr/lang/types/OCPointerType;.class
            //   350: aastore        
            //   351: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.isInstanceOfType:(Lcom/jetbrains/cidr/lang/types/OCType;[Ljava/lang/Class;)Z
            //   354: ifeq            385
            //   357: aload_0        
            //   358: aload_3        
            //   359: aload           4
            //   361: aload           10
            //   363: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getRValue:()Lcom/intellij/psi/PsiElement;
            //   366: invokespecial   com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Lcom/jetbrains/cidr/lang/psi/OCStatement;Lcom/jetbrains/cidr/lang/psi/OCStatement;Lcom/intellij/psi/PsiElement;)Z
            //   369: ifeq            385
            //   372: goto            379
            //   375: invokestatic    com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   378: athrow         
            //   379: iconst_1       
            //   380: ireturn        
            //   381: invokestatic    com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   384: athrow         
            //   385: goto            220
            //   388: iconst_0       
            //   389: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  44     84     84     88     Ljava/lang/IllegalArgumentException;
            //  96     103    103    107    Ljava/lang/IllegalArgumentException;
            //  131    147    150    154    Ljava/lang/IllegalArgumentException;
            //  139    176    179    183    Ljava/lang/IllegalArgumentException;
            //  154    185    185    189    Ljava/lang/IllegalArgumentException;
            //  197    204    204    208    Ljava/lang/IllegalArgumentException;
            //  242    290    290    294    Ljava/lang/IllegalArgumentException;
            //  296    313    313    317    Ljava/lang/IllegalArgumentException;
            //  317    336    336    340    Ljava/lang/IllegalArgumentException;
            //  340    372    375    379    Ljava/lang/IllegalArgumentException;
            //  357    381    381    385    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0154:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        private boolean a(@Nullable final OCStatement ocStatement, @Nullable final OCStatement ocStatement2, @Nullable final PsiElement psiElement) {
            try {
                if (psiElement == null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0035: {
                try {
                    if (PsiTreeUtil.isAncestor((PsiElement)ocStatement, psiElement, false)) {
                        break Label_0035;
                    }
                    final OCStatement ocStatement3 = ocStatement2;
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = false;
                    final boolean b2 = PsiTreeUtil.isAncestor((PsiElement)ocStatement3, psiElement2, b);
                    if (b2) {
                        break Label_0035;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCStatement ocStatement3 = ocStatement2;
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = false;
                    final boolean b2 = PsiTreeUtil.isAncestor((PsiElement)ocStatement3, psiElement2, b);
                    if (b2) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return false;
        }
        
        @NotNull
        private OCControlFlowGraph a(@NotNull final OCCallable ocCallable) {
            try {
                if (ocCallable == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callable", "com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor", "getCFG"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0112: {
                OCControlFlowGraph ocControlFlowGraph = null;
                Label_0077: {
                    try {
                        if (!this.myGraphs.containsKey((Object)ocCallable)) {
                            break Label_0112;
                        }
                        final ForLoopThatDoesntUseLoopVariableVisitor forLoopThatDoesntUseLoopVariableVisitor = this;
                        final HashMap<OCCallable, OCControlFlowGraph> hashMap = forLoopThatDoesntUseLoopVariableVisitor.myGraphs;
                        final PsiElement psiElement = (PsiElement)ocCallable;
                        final Object o = hashMap.get((Object)psiElement);
                        ocControlFlowGraph = (OCControlFlowGraph)o;
                        if (ocControlFlowGraph == null) {
                            break Label_0077;
                        }
                        return ocControlFlowGraph;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final ForLoopThatDoesntUseLoopVariableVisitor forLoopThatDoesntUseLoopVariableVisitor = this;
                        final HashMap<OCCallable, OCControlFlowGraph> hashMap = forLoopThatDoesntUseLoopVariableVisitor.myGraphs;
                        final PsiElement psiElement = (PsiElement)ocCallable;
                        final Object o = hashMap.get((Object)psiElement);
                        ocControlFlowGraph = (OCControlFlowGraph)o;
                        if (ocControlFlowGraph == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor", "getCFG"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return ocControlFlowGraph;
            }
            final OCControlFlowGraph ocControlFlowGraph2 = new OCControlFlowGraph((PsiElement)ocCallable, null);
            final OCControlFlowBuilder ocControlFlowBuilder = new OCControlFlowBuilder(null, ocControlFlowGraph2, null);
            OCControlFlowGraph ocControlFlowGraph3;
            try {
                ocControlFlowBuilder.processFirstCodeFragment((PsiElement)ocCallable);
                this.myGraphs.put((Object)ocCallable, (Object)ocControlFlowGraph2);
                ocControlFlowGraph3 = ocControlFlowGraph2;
                if (ocControlFlowGraph3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor", "getCFG"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            return ocControlFlowGraph3;
        }
        
        @Nullable
        private OCCallable a(@NotNull final PsiElement psiElement) {
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor", "getTopmostCallable"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            OCCallable ocCallable;
            for (ocCallable = (OCCallable)PsiTreeUtil.getParentOfType(psiElement, (Class)OCCallable.class); ocCallable instanceof OCLambdaExpression; ocCallable = (OCCallable)PsiTreeUtil.getParentOfType((PsiElement)ocCallable, (Class)OCCallable.class)) {}
            return ocCallable;
        }
        
        @NotNull
        private String a(@NotNull final Collection<OCSymbolDeclarator> collection) {
            try {
                if (collection == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarators", "com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor", "getMessage"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final boolean b;
            final List<? super Object> list = collection.stream().map(ocSymbolDeclarator -> ocSymbolDeclarator.getSymbol()).filter(ocSymbol -> {
                try {
                    if (ocSymbol != null) {
                        return b;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                return b;
            }).map(ocSymbol2 -> ocSymbol2.getName()).sorted().collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList());
            final StringJoiner stringJoiner = new StringJoiner(", ");
            if (list.size() == collection.size()) {
                final Iterator<String> iterator = list.iterator();
                while (iterator.hasNext()) {
                    stringJoiner.add("'" + iterator.next() + "'");
                }
            }
            String string = null;
            Label_0244: {
                StringBuilder append;
                try {
                    append = new StringBuilder().append(StringUtil.pluralize("Variable", collection.size())).append(" ").append(stringJoiner.toString()).append(" used in loop condition ");
                    if (collection.size() == 1) {
                        final String s = "is";
                        break Label_0244;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                final String s = "are";
                try {
                    string = append.append(s).append(" not updated in the loop").toString();
                    if (string == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCLoopDoesntUseConditionVariableInspection$ForLoopThatDoesntUseLoopVariableVisitor", "getMessage"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return string;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
