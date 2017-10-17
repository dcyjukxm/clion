// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.psi.OCExternalReference;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.codeInspection.ProblemHighlightType;
import com.jetbrains.cidr.lang.quickfixes.OCSendMessageToObjectIntentionAction;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.daemon.clang.OCClangMessageFinder;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.psi.util.PsiModificationTracker;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.intellij.lang.annotation.Annotation;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.quickfixes.OCAddInitializerIntentionAction;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.daemon.OCNullAnnotatorSink;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.psi.util.CachedValue;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.daemon.OCAnnotatorSink;
import com.intellij.psi.PsiReference;
import java.util.Map;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.TextRange;
import java.util.List;
import com.intellij.psi.PsiElement;

public class OCDataFlowAnalyzer
{
    private PsiElement[] myCodeFragments;
    private OCControlFlowGraph myGraph;
    private OCControlFlowBuilder myGraphBuilder;
    private OCUnreachableCodeFinder myUnreachableCodeFinder;
    private OCInfiniteRecursionFinder myInfiniteRecursionFinder;
    private OCEndlessLoopFinder myEndlessLoopFinder;
    private OCDataFlowAnalyzer myParentAnalyzer;
    private List<OCDataFlowAnalyzer> myChildAnalyzers;
    private TextRange mySelection;
    private List<OCSymbol> myInputVariables;
    private List<OCSymbol> myOutputVariables;
    private List<OCSymbol> myWrittenVariables;
    private List<OCSymbol> myEscapedDeclarators;
    private Map<OCSymbol, List<PsiReference>> myVariableUsages;
    @NotNull
    private OCAnnotatorSink mySink;
    private static final Key<CachedValue<Map<OCStructSymbol, Boolean>>> TRIVIAL_DTOR_CACHE;
    
    public OCDataFlowAnalyzer(@NotNull final PsiElement psiElement, @NotNull final OCAnnotatorSink ocAnnotatorSink, @Nullable final OCDataFlowAnalyzer ocDataFlowAnalyzer) {
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "codeFragment", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "<init>"));
        }
        if (ocAnnotatorSink == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "annotator", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "<init>"));
        }
        this(new PsiElement[] { psiElement }, ocAnnotatorSink, ocDataFlowAnalyzer, (ocDataFlowAnalyzer != null) ? ocDataFlowAnalyzer.mySelection : null);
        if (ocDataFlowAnalyzer != null) {
            ocDataFlowAnalyzer.myChildAnalyzers.add(this);
        }
    }
    
    public OCDataFlowAnalyzer(@NotNull final PsiElement[] array, @Nullable final TextRange textRange) {
        if (array == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "codeFragments", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "<init>"));
        }
        this(array, OCNullAnnotatorSink.INSTANCE, null, textRange);
    }
    
    public OCDataFlowAnalyzer(@NotNull final PsiElement psiElement, @Nullable final TextRange textRange) {
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "codeFragment", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "<init>"));
        }
        this(new PsiElement[] { psiElement }, OCNullAnnotatorSink.INSTANCE, null, textRange);
    }
    
    private OCDataFlowAnalyzer(@NotNull final PsiElement[] myCodeFragments, @NotNull final OCAnnotatorSink mySink, @Nullable final OCDataFlowAnalyzer myParentAnalyzer, @Nullable final TextRange mySelection) {
        if (myCodeFragments == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "codeFragments", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "<init>"));
        }
        if (mySink == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sink", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "<init>"));
        }
        this.myChildAnalyzers = new ArrayList<OCDataFlowAnalyzer>();
        this.mySink = mySink;
        this.myCodeFragments = myCodeFragments;
        this.mySelection = mySelection;
        this.myParentAnalyzer = myParentAnalyzer;
        this.myInputVariables = new ArrayList<OCSymbol>();
        this.myOutputVariables = new ArrayList<OCSymbol>();
        this.myWrittenVariables = new ArrayList<OCSymbol>();
        this.myEscapedDeclarators = new ArrayList<OCSymbol>();
        this.myVariableUsages = new HashMap<OCSymbol, List<PsiReference>>();
    }
    
    @NotNull
    public OCAnnotatorSink getSink() {
        OCAnnotatorSink mySink;
        try {
            mySink = this.mySink;
            if (mySink == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "getSink"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return mySink;
    }
    
    @NotNull
    public OCControlFlowGraph getGraph() {
        OCControlFlowGraph myGraph;
        try {
            myGraph = this.myGraph;
            if (myGraph == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "getGraph"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myGraph;
    }
    
    @Nullable
    public OCControlFlowGraph findGraph(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "findGraph"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!this.myGraph.getCodeFragment().getTextRange().contains(psiElement.getTextOffset())) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Iterator<OCDataFlowAnalyzer> iterator = this.myChildAnalyzers.iterator();
        while (iterator.hasNext()) {
            final OCControlFlowGraph graph = iterator.next().findGraph(psiElement);
            try {
                if (graph != null) {
                    return graph;
                }
                continue;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return this.myGraph;
    }
    
    @NotNull
    public OCUnreachableCodeFinder getUnreachableCodeFinder() {
        OCUnreachableCodeFinder myUnreachableCodeFinder;
        try {
            myUnreachableCodeFinder = this.myUnreachableCodeFinder;
            if (myUnreachableCodeFinder == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "getUnreachableCodeFinder"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myUnreachableCodeFinder;
    }
    
    @NotNull
    public List<OCSymbol> getInputVariables() {
        List<OCSymbol> myInputVariables;
        try {
            myInputVariables = this.myInputVariables;
            if (myInputVariables == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "getInputVariables"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myInputVariables;
    }
    
    @NotNull
    public List<OCSymbol> getOutputVariables() {
        List<OCSymbol> myOutputVariables;
        try {
            myOutputVariables = this.myOutputVariables;
            if (myOutputVariables == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "getOutputVariables"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myOutputVariables;
    }
    
    @NotNull
    public List<OCSymbol> getWrittenVariables() {
        List<OCSymbol> myWrittenVariables;
        try {
            myWrittenVariables = this.myWrittenVariables;
            if (myWrittenVariables == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "getWrittenVariables"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myWrittenVariables;
    }
    
    @NotNull
    public List<OCSymbol> getEscapedDeclarators() {
        List<OCSymbol> myEscapedDeclarators;
        try {
            myEscapedDeclarators = this.myEscapedDeclarators;
            if (myEscapedDeclarators == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "getEscapedDeclarators"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myEscapedDeclarators;
    }
    
    public void buildControlFlowGraph() {
        PsiElement psiElement = null;
        OCControlFlowGraph graph = null;
        Label_0033: {
            try {
                psiElement = this.myCodeFragments[0];
                if (this.myParentAnalyzer != null) {
                    graph = this.myParentAnalyzer.getGraph();
                    break Label_0033;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            graph = null;
        }
        this.myGraph = new OCControlFlowGraph(psiElement, graph);
        this.myGraphBuilder = new OCControlFlowBuilder(this, this.myGraph, this.mySelection);
        int n = 1;
        for (final PsiElement psiElement2 : this.myCodeFragments) {
            Label_0113: {
                try {
                    if (n != 0) {
                        this.myGraphBuilder.processFirstCodeFragment(psiElement2);
                        break Label_0113;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                this.myGraphBuilder.processNextCodeFragment(psiElement2);
            }
            n = 0;
        }
        this.myUnreachableCodeFinder = new OCUnreachableCodeFinder(this.myGraph);
        this.myInfiniteRecursionFinder = new OCInfiniteRecursionFinder(this.myGraph);
        this.myEndlessLoopFinder = new OCEndlessLoopFinder(this.myGraph);
        this.myUnreachableCodeFinder.process();
        this.myInfiniteRecursionFinder.process();
        this.myEndlessLoopFinder.process();
    }
    
    public void analyze() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myCodeFragments:[Lcom/intellij/psi/PsiElement;
        //     4: iconst_0       
        //     5: aaload         
        //     6: astore_1       
        //     7: aload_1        
        //     8: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //    13: astore_2       
        //    14: aload_1        
        //    15: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //    18: ifeq            32
        //    21: aload_1        
        //    22: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //    25: goto            33
        //    28: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    31: athrow         
        //    32: aconst_null    
        //    33: astore_3       
        //    34: aconst_null    
        //    35: astore          4
        //    37: aload_3        
        //    38: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    41: ifeq            58
        //    44: aload_3        
        //    45: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    48: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getSelectors:()Ljava/util/List;
        //    53: astore          4
        //    55: goto            79
        //    58: aload_3        
        //    59: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //    62: ifeq            79
        //    65: aload_3        
        //    66: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //    69: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDefinition.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //    74: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //    77: astore          4
        //    79: aload_3        
        //    80: ifnull          528
        //    83: aload_3        
        //    84: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    89: aload_3        
        //    90: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    95: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    98: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   101: ifne            528
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: aload_0        
        //   112: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myUnreachableCodeFinder:Lcom/jetbrains/cidr/lang/dfa/OCUnreachableCodeFinder;
        //   115: invokevirtual   com/jetbrains/cidr/lang/dfa/OCUnreachableCodeFinder.isDeadEndReached:()Z
        //   118: ifeq            528
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   127: athrow         
        //   128: aload_3        
        //   129: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   134: astore          5
        //   136: iconst_0       
        //   137: istore          6
        //   139: aload           5
        //   141: ifnonnull       149
        //   144: return         
        //   145: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   148: athrow         
        //   149: aload_3        
        //   150: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   155: astore          7
        //   157: aload_3        
        //   158: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   163: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   168: ifeq            220
        //   171: aload_3        
        //   172: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   175: ifeq            220
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: aload           7
        //   187: ifnull          220
        //   190: goto            197
        //   193: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: aload           7
        //   199: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   204: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isConstructorOrDestructor:()Z
        //   207: ifeq            220
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   216: athrow         
        //   217: iconst_1       
        //   218: istore          6
        //   220: aload           7
        //   222: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   225: ifeq            251
        //   228: aload           7
        //   230: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   233: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isMainFunction:()Z
        //   236: ifeq            251
        //   239: goto            246
        //   242: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   245: athrow         
        //   246: return         
        //   247: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: iload           6
        //   253: ifne            528
        //   256: aconst_null    
        //   257: astore          8
        //   259: new             Ljava/lang/StringBuilder;
        //   262: dup            
        //   263: invokespecial   java/lang/StringBuilder.<init>:()V
        //   266: ldc             "Control reaches end of non-void "
        //   268: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   271: aload_3        
        //   272: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getKind:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //   277: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind.toStringLowercase:()Ljava/lang/String;
        //   280: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   283: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   286: astore          9
        //   288: aload_3        
        //   289: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //   292: ifeq            320
        //   295: aload_0        
        //   296: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   299: aload           5
        //   301: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.getClosingBrace:()Lcom/intellij/psi/PsiElement;
        //   306: ldc             "err_maybe_falloff_nonvoid_block"
        //   308: aload           9
        //   310: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   315: astore          8
        //   317: goto            422
        //   320: getstatic       com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer$1.$SwitchMap$com$jetbrains$cidr$lang$workspace$compiler$OCCompilerFeatures$DiagnosticLevel:[I
        //   323: aload           5
        //   325: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   330: getstatic       com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Diagnostic.MISSING_RETURN_FROM_NON_VOID:Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Diagnostic;
        //   333: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.getDiagnosticLevel:(Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Diagnostic;)Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$DiagnosticLevel;
        //   336: invokevirtual   com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$DiagnosticLevel.ordinal:()I
        //   339: iaload         
        //   340: lookupswitch {
        //                1: 368
        //                2: 395
        //          default: 422
        //        }
        //   368: aload_0        
        //   369: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   372: aload           5
        //   374: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.getClosingBrace:()Lcom/intellij/psi/PsiElement;
        //   379: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$MissingReturn;.class
        //   381: ldc             "warn_falloff_nonvoid_function"
        //   383: aload           9
        //   385: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.addErrorAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   390: astore          8
        //   392: goto            422
        //   395: aload_0        
        //   396: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   399: aload           5
        //   401: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.getClosingBrace:()Lcom/intellij/psi/PsiElement;
        //   406: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$MissingReturn;.class
        //   408: ldc             "warn_falloff_nonvoid_function"
        //   410: aload           9
        //   412: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   417: astore          8
        //   419: goto            422
        //   422: aload           8
        //   424: ifnull          528
        //   427: aload_3        
        //   428: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   433: aload           5
        //   435: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   440: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   443: astore          10
        //   445: aload_0        
        //   446: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   449: aload           8
        //   451: new             Lcom/jetbrains/cidr/lang/quickfixes/OCAddElementIntentionAction;
        //   454: dup            
        //   455: ldc             "Add return statement"
        //   457: aload           5
        //   459: new             Ljava/lang/StringBuilder;
        //   462: dup            
        //   463: invokespecial   java/lang/StringBuilder.<init>:()V
        //   466: ldc             "return "
        //   468: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   471: aload           10
        //   473: aload_3        
        //   474: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getDefaultValue:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   477: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   480: ldc             ";"
        //   482: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   485: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   488: aload_1        
        //   489: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.statementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   492: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCAddElementIntentionAction.<init>:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)V
        //   495: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   500: aload_0        
        //   501: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   504: aload           8
        //   506: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;
        //   509: dup            
        //   510: aload_3        
        //   511: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   516: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   519: iconst_1       
        //   520: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;Z)V
        //   523: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   528: aload_0        
        //   529: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myEndlessLoopFinder:Lcom/jetbrains/cidr/lang/dfa/OCEndlessLoopFinder;
        //   532: invokevirtual   com/jetbrains/cidr/lang/dfa/OCEndlessLoopFinder.getEndlessLoops:()Ljava/util/List;
        //   535: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   540: astore          5
        //   542: aload           5
        //   544: invokeinterface java/util/Iterator.hasNext:()Z
        //   549: ifeq            597
        //   552: aload           5
        //   554: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   559: checkcast       Lcom/jetbrains/cidr/lang/util/OCElementsRange;
        //   562: astore          6
        //   564: aload_0        
        //   565: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   568: aload_1        
        //   569: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   574: aload           6
        //   576: invokevirtual   com/jetbrains/cidr/lang/util/OCElementsRange.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   579: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$EndlessLoop;.class
        //   581: ldc             "warn_suggest_noreturn_function"
        //   583: ldc             "Endless loop"
        //   585: getstatic       com/intellij/codeInspection/ProblemHighlightType.GENERIC_ERROR_OR_WARNING:Lcom/intellij/codeInspection/ProblemHighlightType;
        //   588: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/util/TextRange;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //   593: pop            
        //   594: goto            542
        //   597: aload           4
        //   599: ifnull          699
        //   602: aload_0        
        //   603: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myInfiniteRecursionFinder:Lcom/jetbrains/cidr/lang/dfa/OCInfiniteRecursionFinder;
        //   606: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInfiniteRecursionFinder.isExitOrDeadEndReached:()Z
        //   609: ifne            699
        //   612: goto            619
        //   615: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   618: athrow         
        //   619: new             Ljava/util/ArrayList;
        //   622: dup            
        //   623: aload           4
        //   625: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
        //   628: astore          5
        //   630: aload           5
        //   632: aload_0        
        //   633: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myInfiniteRecursionFinder:Lcom/jetbrains/cidr/lang/dfa/OCInfiniteRecursionFinder;
        //   636: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInfiniteRecursionFinder.getRecursiveCalls:()Ljava/util/List;
        //   639: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   644: pop            
        //   645: aload_0        
        //   646: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   649: aload           5
        //   651: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$InfiniteRecursion;.class
        //   653: ldc             "CIDR"
        //   655: new             Ljava/lang/StringBuilder;
        //   658: dup            
        //   659: invokespecial   java/lang/StringBuilder.<init>:()V
        //   662: aload_3        
        //   663: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getKind:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //   668: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   671: ldc             " '"
        //   673: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   676: aload_3        
        //   677: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getName:()Ljava/lang/String;
        //   682: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   685: ldc             "' recurses infinitely"
        //   687: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   690: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   693: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.addWarningAnnotations:(Ljava/util/List;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //   698: pop            
        //   699: aload_1        
        //   700: ifnull          730
        //   703: aload_1        
        //   704: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   709: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcDisabled:(Lcom/intellij/psi/PsiFile;)Z
        //   712: ifne            730
        //   715: goto            722
        //   718: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   721: athrow         
        //   722: iconst_1       
        //   723: goto            731
        //   726: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   729: athrow         
        //   730: iconst_0       
        //   731: istore          5
        //   733: aload_0        
        //   734: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //   737: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getLocalSymbols:()Ljava/util/Set;
        //   740: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   745: astore          6
        //   747: aload           6
        //   749: invokeinterface java/util/Iterator.hasNext:()Z
        //   754: ifeq            842
        //   757: aload           6
        //   759: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   764: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   767: astore          7
        //   769: aload           7
        //   771: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isUnnamed:()Z
        //   776: ifeq            786
        //   779: goto            747
        //   782: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   785: athrow         
        //   786: aload_0        
        //   787: aload           7
        //   789: aload_2        
        //   790: invokespecial   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.b:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)V
        //   793: aload_0        
        //   794: aload           7
        //   796: invokespecial   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   799: aload_0        
        //   800: aload           7
        //   802: iload           5
        //   804: invokespecial   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Z)V
        //   807: iload           5
        //   809: ifne            839
        //   812: aload_0        
        //   813: aload           7
        //   815: new             Lcom/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker;
        //   818: dup            
        //   819: aload_0        
        //   820: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //   823: aload           7
        //   825: invokespecial   com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.<init>:(Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   828: iconst_1       
        //   829: invokevirtual   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.analyzeNotReleased:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker;Z)V
        //   832: goto            839
        //   835: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   838: athrow         
        //   839: goto            747
        //   842: aload_0        
        //   843: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //   846: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getClosureSymbols:()Ljava/util/Set;
        //   849: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   854: astore          6
        //   856: aload           6
        //   858: invokeinterface java/util/Iterator.hasNext:()Z
        //   863: ifeq            912
        //   866: aload           6
        //   868: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   873: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   876: astore          7
        //   878: aload           7
        //   880: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isUnnamed:()Z
        //   885: ifeq            895
        //   888: goto            856
        //   891: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   894: athrow         
        //   895: aload_0        
        //   896: aload           7
        //   898: iload           5
        //   900: invokespecial   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Z)V
        //   903: aload_0        
        //   904: aload           7
        //   906: invokespecial   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   909: goto            856
        //   912: iload           5
        //   914: ifne            1108
        //   917: aload_1        
        //   918: ifnull          1108
        //   921: goto            928
        //   924: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   927: athrow         
        //   928: aload_1        
        //   929: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.getUnreleasedObjects:(Lcom/intellij/psi/PsiElement;)Ljava/util/List;
        //   932: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   937: astore          6
        //   939: aload           6
        //   941: invokeinterface java/util/Iterator.hasNext:()Z
        //   946: ifeq            1108
        //   949: aload           6
        //   951: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   956: checkcast       Lcom/intellij/openapi/util/Pair;
        //   959: astore          7
        //   961: aload_0        
        //   962: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   965: aload           7
        //   967: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //   970: checkcast       Lcom/intellij/psi/PsiElement;
        //   973: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NotReleasedValue;.class
        //   975: ldc             "CIDR"
        //   977: ldc             "Retained value may have not been released"
        //   979: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   984: astore          8
        //   986: aload           7
        //   988: invokevirtual   com/intellij/openapi/util/Pair.getSecond:()Ljava/lang/Object;
        //   991: checkcast       Lcom/intellij/psi/PsiElement;
        //   994: astore          9
        //   996: aload_0        
        //   997: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //  1000: aload           8
        //  1002: new             Lcom/jetbrains/cidr/lang/quickfixes/OCSendMessageToObjectIntentionAction;
        //  1005: dup            
        //  1006: aload           9
        //  1008: ldc             "autorelease"
        //  1010: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCSendMessageToObjectIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //  1013: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //  1018: aload           9
        //  1020: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //  1025: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReturnStatement;
        //  1028: ifeq            1105
        //  1031: aload           9
        //  1033: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //  1035: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //  1038: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //  1041: astore          10
        //  1043: aload           10
        //  1045: ifnull          1062
        //  1048: aload           10
        //  1050: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1055: goto            1063
        //  1058: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1061: athrow         
        //  1062: aconst_null    
        //  1063: astore          11
        //  1065: aload           11
        //  1067: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //  1070: ifeq            1105
        //  1073: aload_0        
        //  1074: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //  1077: aload           8
        //  1079: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction;
        //  1082: dup            
        //  1083: aload           11
        //  1085: ldc             "ns_returns_retained"
        //  1087: ldc             "NS_RETURNS_RETAINED"
        //  1089: iconst_1       
        //  1090: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Ljava/lang/String;Z)V
        //  1093: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //  1098: goto            1105
        //  1101: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1104: athrow         
        //  1105: goto            939
        //  1108: aload_0        
        //  1109: invokespecial   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:()V
        //  1112: aload_0        
        //  1113: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myChildAnalyzers:Ljava/util/List;
        //  1116: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  1121: astore          6
        //  1123: aload           6
        //  1125: invokeinterface java/util/Iterator.hasNext:()Z
        //  1130: ifeq            1300
        //  1133: aload           6
        //  1135: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1140: checkcast       Lcom/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer;
        //  1143: astore          7
        //  1145: aload           7
        //  1147: invokevirtual   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.analyze:()V
        //  1150: aload_0        
        //  1151: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myInputVariables:Ljava/util/List;
        //  1154: aload           7
        //  1156: invokevirtual   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.getInputVariables:()Ljava/util/List;
        //  1159: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //  1164: pop            
        //  1165: aload_0        
        //  1166: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myOutputVariables:Ljava/util/List;
        //  1169: aload           7
        //  1171: invokevirtual   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.getOutputVariables:()Ljava/util/List;
        //  1174: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //  1179: pop            
        //  1180: aload_0        
        //  1181: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myEscapedDeclarators:Ljava/util/List;
        //  1184: aload           7
        //  1186: invokevirtual   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.getEscapedDeclarators:()Ljava/util/List;
        //  1189: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //  1194: pop            
        //  1195: aload_0        
        //  1196: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myWrittenVariables:Ljava/util/List;
        //  1199: aload           7
        //  1201: invokevirtual   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.getWrittenVariables:()Ljava/util/List;
        //  1204: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //  1209: pop            
        //  1210: aload           7
        //  1212: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myVariableUsages:Ljava/util/Map;
        //  1215: invokeinterface java/util/Map.keySet:()Ljava/util/Set;
        //  1220: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //  1225: astore          8
        //  1227: aload           8
        //  1229: invokeinterface java/util/Iterator.hasNext:()Z
        //  1234: ifeq            1297
        //  1237: aload           8
        //  1239: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1244: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1247: astore          9
        //  1249: aload_0        
        //  1250: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myVariableUsages:Ljava/util/Map;
        //  1253: aload           9
        //  1255: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //  1260: ifne            1294
        //  1263: aload_0        
        //  1264: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myVariableUsages:Ljava/util/Map;
        //  1267: aload           9
        //  1269: aload           7
        //  1271: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myVariableUsages:Ljava/util/Map;
        //  1274: aload           9
        //  1276: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1281: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1286: pop            
        //  1287: goto            1294
        //  1290: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1293: athrow         
        //  1294: goto            1227
        //  1297: goto            1123
        //  1300: aload_0        
        //  1301: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySelection:Lcom/intellij/openapi/util/TextRange;
        //  1304: ifnull          1350
        //  1307: invokedynamic   compare:()Ljava/util/Comparator;
        //  1312: astore          6
        //  1314: aload_0        
        //  1315: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myInputVariables:Ljava/util/List;
        //  1318: aload           6
        //  1320: invokestatic    java/util/Collections.sort:(Ljava/util/List;Ljava/util/Comparator;)V
        //  1323: aload_0        
        //  1324: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myOutputVariables:Ljava/util/List;
        //  1327: aload           6
        //  1329: invokestatic    java/util/Collections.sort:(Ljava/util/List;Ljava/util/Comparator;)V
        //  1332: aload_0        
        //  1333: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myWrittenVariables:Ljava/util/List;
        //  1336: aload           6
        //  1338: invokestatic    java/util/Collections.sort:(Ljava/util/List;Ljava/util/Comparator;)V
        //  1341: aload_0        
        //  1342: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myEscapedDeclarators:Ljava/util/List;
        //  1345: aload           6
        //  1347: invokestatic    java/util/Collections.sort:(Ljava/util/List;Ljava/util/Comparator;)V
        //  1350: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  14     28     28     32     Ljava/lang/IllegalArgumentException;
        //  79     104    107    111    Ljava/lang/IllegalArgumentException;
        //  83     121    124    128    Ljava/lang/IllegalArgumentException;
        //  139    145    145    149    Ljava/lang/IllegalArgumentException;
        //  157    178    181    185    Ljava/lang/IllegalArgumentException;
        //  171    190    193    197    Ljava/lang/IllegalArgumentException;
        //  185    210    213    217    Ljava/lang/IllegalArgumentException;
        //  220    239    242    246    Ljava/lang/IllegalArgumentException;
        //  228    247    247    251    Ljava/lang/IllegalArgumentException;
        //  597    612    615    619    Ljava/lang/IllegalArgumentException;
        //  699    715    718    722    Ljava/lang/IllegalArgumentException;
        //  703    726    726    730    Ljava/lang/IllegalArgumentException;
        //  769    782    782    786    Ljava/lang/IllegalArgumentException;
        //  786    832    835    839    Ljava/lang/IllegalArgumentException;
        //  878    891    891    895    Ljava/lang/IllegalArgumentException;
        //  912    921    924    928    Ljava/lang/IllegalArgumentException;
        //  1043   1058   1058   1062   Ljava/lang/IllegalArgumentException;
        //  1065   1098   1101   1105   Ljava/lang/IllegalArgumentException;
        //  1249   1287   1290   1294   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0185:
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
    
    private void a(@NotNull final OCSymbol ocSymbol, final boolean b) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "analyzeNotInitialized"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocSymbol.getKind() != OCSymbolKind.LOCAL_VARIABLE) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCType resolvedType = ocSymbol.getResolvedType();
        if (resolvedType instanceof OCStructType) {
            final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
            for (final OCStructSymbol ocStructSymbol : ((OCStructType)resolvedType).getStructs()) {
                try {
                    if (!ocStructSymbol.processConstructors((Processor<? super OCFunctionSymbol>)findFirstProcessor)) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    if (((OCStructType)resolvedType).isEmpty()) {
                        return;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            try {
                if (findFirstProcessor.isFound()) {
                    return;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        Label_0220: {
            Label_0191: {
                try {
                    if (!b) {
                        break Label_0191;
                    }
                    final OCStructType ocStructType = (OCStructType)resolvedType;
                    final boolean b2 = ocStructType.isPointerToObjectCompatible();
                    if (b2) {
                        return;
                    }
                    break Label_0191;
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                try {
                    final OCStructType ocStructType = (OCStructType)resolvedType;
                    final boolean b2 = ocStructType.isPointerToObjectCompatible();
                    if (b2) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
                try {
                    if (!(ocSymbol instanceof OCDeclaratorSymbol)) {
                        break Label_0220;
                    }
                    final OCDeclaratorSymbol ocDeclaratorSymbol = (OCDeclaratorSymbol)ocSymbol;
                    final OCDeclaratorSymbol ocDeclaratorSymbol2 = ocDeclaratorSymbol;
                    final boolean b3 = ocDeclaratorSymbol2.isFriendOrStatic();
                    if (b3) {
                        return;
                    }
                    break Label_0220;
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
            }
            try {
                final OCDeclaratorSymbol ocDeclaratorSymbol = (OCDeclaratorSymbol)ocSymbol;
                final OCDeclaratorSymbol ocDeclaratorSymbol2 = ocDeclaratorSymbol;
                final boolean b3 = ocDeclaratorSymbol2.isFriendOrStatic();
                if (b3) {
                    return;
                }
            }
            catch (IllegalArgumentException ex9) {
                throw a(ex9);
            }
        }
        final OCNotInitializedVarChecker ocNotInitializedVarChecker = new OCNotInitializedVarChecker(this.myGraph, ocSymbol);
        ocNotInitializedVarChecker.process();
        for (final PsiElement psiElement : ocNotInitializedVarChecker.getNotInitializedReads()) {
            if (!a(ocSymbol, psiElement)) {
                try {
                    if (a(psiElement)) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw a(ex10);
                }
                final Annotation a = this.a(psiElement, ocSymbol, OCInspections.NotInitializedVariable.class, "warn_uninit_var", ocSymbol.getNameWithKindUppercase() + " might not have been initialized");
                OCInstruction ocInstruction = this.myGraph.getDeclaratorInstruction(ocSymbol);
                if (ocInstruction == null) {
                    ocInstruction = this.myGraph.getClosureVariableDeclaratorGraph(ocSymbol).getDeclaratorInstruction(ocSymbol);
                }
                this.mySink.registerQuickFix(a, (IntentionAction)new OCAddInitializerIntentionAction((OCDeclarator)ocInstruction.getRValue(), ocSymbol));
            }
        }
    }
    
    private static boolean a(@NotNull final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isStaticallyEvaluated"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    50: astore_1       
        //    51: aload_1        
        //    52: ifnull          171
        //    55: aload_1        
        //    56: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSizeofExpression;
        //    59: ifne            97
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_1        
        //    70: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppTypeidExpression;
        //    73: ifne            97
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    82: athrow         
        //    83: aload_1        
        //    84: instanceof      Lcom/jetbrains/cidr/lang/psi/OCGenericSelectionExpression;
        //    87: ifeq            103
        //    90: goto            97
        //    93: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    96: athrow         
        //    97: iconst_1       
        //    98: ireturn        
        //    99: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: aload_1        
        //   104: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   107: ifeq            161
        //   110: aload_1        
        //   111: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   116: aconst_null    
        //   117: invokeinterface com/intellij/lang/ASTNode.getChildren:(Lcom/intellij/psi/tree/TokenSet;)[Lcom/intellij/lang/ASTNode;
        //   122: invokestatic    java/util/Arrays.stream:([Ljava/lang/Object;)Ljava/util/stream/Stream;
        //   125: invokedynamic   apply:()Ljava/util/function/Function;
        //   130: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
        //   135: invokedynamic   test:()Ljava/util/function/Predicate;
        //   140: invokeinterface java/util/stream/Stream.anyMatch:(Ljava/util/function/Predicate;)Z
        //   145: ifeq            161
        //   148: goto            155
        //   151: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   154: athrow         
        //   155: iconst_1       
        //   156: ireturn        
        //   157: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   160: athrow         
        //   161: aload_1        
        //   162: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   167: astore_1       
        //   168: goto            51
        //   171: iconst_0       
        //   172: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  51     62     65     69     Ljava/lang/IllegalArgumentException;
        //  55     76     79     83     Ljava/lang/IllegalArgumentException;
        //  69     90     93     97     Ljava/lang/IllegalArgumentException;
        //  83     99     99     103    Ljava/lang/IllegalArgumentException;
        //  103    148    151    155    Ljava/lang/IllegalArgumentException;
        //  110    157    157    161    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
    
    private static boolean a(@NotNull final OCSymbol p0, @NotNull final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isComplicatedCppInstruction"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "read"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isComplicatedCppInstruction"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    94: astore_2       
        //    95: aload_2        
        //    96: ifnull          242
        //    99: aload_2        
        //   100: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   105: ifeq            242
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: aload_0        
        //   116: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   121: astore_3       
        //   122: aload_3        
        //   123: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   126: ifne            163
        //   129: aload_3        
        //   130: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   133: ifeq            169
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   142: athrow         
        //   143: aload_3        
        //   144: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   147: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   150: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.hasMemberFunctions:()Z
        //   153: ifeq            169
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: iconst_1       
        //   164: ireturn        
        //   165: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aload_1        
        //   170: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   175: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   178: ifeq            242
        //   181: aload_1        
        //   182: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   187: astore          4
        //   189: aload           4
        //   191: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   194: ifeq            242
        //   197: aload           4
        //   199: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   202: astore          5
        //   204: aload           5
        //   206: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   211: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   214: ifeq            223
        //   217: iconst_1       
        //   218: ireturn        
        //   219: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   222: athrow         
        //   223: aload           4
        //   225: iconst_1       
        //   226: anewarray       Ljava/lang/Class;
        //   229: dup            
        //   230: iconst_0       
        //   231: ldc             Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;.class
        //   233: aastore        
        //   234: invokestatic    com/intellij/psi/util/PsiTreeUtil.skipParentsOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   237: astore          4
        //   239: goto            189
        //   242: iconst_0       
        //   243: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  95     108    111    115    Ljava/lang/IllegalArgumentException;
        //  122    136    139    143    Ljava/lang/IllegalArgumentException;
        //  129    156    159    163    Ljava/lang/IllegalArgumentException;
        //  143    165    165    169    Ljava/lang/IllegalArgumentException;
        //  204    219    219    223    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0143:
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
    
    private void b(@NotNull final OCSymbol p0, @Nullable final PsiElement p1) {
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
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "analyzeNotUsed"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: aload_2        
        //    46: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.d:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)Z
        //    49: ifne            79
        //    52: aload_1        
        //    53: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getProject:()Lcom/intellij/openapi/project/Project;
        //    58: getstatic       com/jetbrains/cidr/lang/psi/OCFile.DFA_UNUSED_CHECKS:Lcom/intellij/openapi/util/Key;
        //    61: invokeinterface com/intellij/openapi/project/Project.getUserData:(Lcom/intellij/openapi/util/Key;)Ljava/lang/Object;
        //    66: getstatic       com/jetbrains/cidr/lang/psi/OCFile$UnusedChecksMode.DISABLED:Lcom/jetbrains/cidr/lang/psi/OCFile$UnusedChecksMode;
        //    69: if_acmpne       84
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: return         
        //    80: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: new             Lcom/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker;
        //    87: dup            
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //    92: aload_1        
        //    93: invokespecial   com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.<init>:(Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //    96: astore_3       
        //    97: aload_3        
        //    98: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.process:()V
        //   101: aload_3        
        //   102: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.getNotUsedWrites:()Ljava/util/List;
        //   105: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   110: astore          4
        //   112: aload           4
        //   114: invokeinterface java/util/Iterator.hasNext:()Z
        //   119: ifeq            354
        //   122: aload           4
        //   124: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   129: checkcast       Lcom/intellij/psi/PsiElement;
        //   132: astore          5
        //   134: aload           5
        //   136: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   141: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isPartOfMacroSubstitution:(Lcom/intellij/psi/PsiElement;)Z
        //   144: ifeq            154
        //   147: goto            112
        //   150: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload           5
        //   156: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   161: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   164: ifeq            237
        //   167: aload_3        
        //   168: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.isSymbolUsed:()Z
        //   171: ifne            188
        //   174: goto            181
        //   177: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   180: athrow         
        //   181: goto            112
        //   184: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: aload           5
        //   190: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   195: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   198: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   203: astore          5
        //   205: aload           5
        //   207: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTextWithMacros:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   210: astore          6
        //   212: ldc             "0"
        //   214: aload           6
        //   216: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   219: ifne            112
        //   222: aload           6
        //   224: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isLikeNull:(Ljava/lang/String;)Z
        //   227: ifeq            237
        //   230: goto            112
        //   233: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   236: athrow         
        //   237: aload           5
        //   239: ifnull          351
        //   242: aload_0        
        //   243: aload           5
        //   245: aload_1        
        //   246: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$UnusedValue;.class
        //   248: ldc             "CIDR"
        //   250: ldc             "The value is never used"
        //   252: getstatic       com/intellij/codeInspection/ProblemHighlightType.LIKE_UNUSED_SYMBOL:Lcom/intellij/codeInspection/ProblemHighlightType;
        //   255: invokespecial   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //   258: astore          6
        //   260: aload           5
        //   262: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //   267: astore          7
        //   269: aload           7
        //   271: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   274: ifeq            307
        //   277: aload_0        
        //   278: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   281: aload           6
        //   283: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveInitializerIntentionAction;
        //   286: dup            
        //   287: aload           7
        //   289: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   292: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveInitializerIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/psi/OCDeclarator;)V
        //   295: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   300: goto            351
        //   303: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   306: athrow         
        //   307: aload_0        
        //   308: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   311: aload           6
        //   313: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction;
        //   316: dup            
        //   317: ldc             "statement"
        //   319: aload           7
        //   321: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.<init>:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)V
        //   324: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   329: aload_0        
        //   330: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   333: aload           6
        //   335: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction;
        //   338: dup            
        //   339: ldc             "statement"
        //   341: aload           7
        //   343: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction.<init>:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)V
        //   346: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   351: goto            112
        //   354: aload_3        
        //   355: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.isSymbolUsed:()Z
        //   358: ifne            608
        //   361: aload_0        
        //   362: aload_1        
        //   363: aload_2        
        //   364: invokespecial   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.e:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)Z
        //   367: ifeq            608
        //   370: goto            377
        //   373: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   376: athrow         
        //   377: aload_3        
        //   378: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.isSymbolAssigned:()Z
        //   381: ifeq            400
        //   384: goto            391
        //   387: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   390: athrow         
        //   391: ldc             " is only assigned but never accessed"
        //   393: goto            402
        //   396: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   399: athrow         
        //   400: ldc             " is never used"
        //   402: astore          4
        //   404: aload_0        
        //   405: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //   408: aload_1        
        //   409: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getDeclaratorInstruction:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/dfa/OCInstruction;
        //   412: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getRValue:()Lcom/intellij/psi/PsiElement;
        //   415: astore          5
        //   417: aload           5
        //   419: ldc             Lcom/jetbrains/cidr/lang/psi/OCTypeElement;.class
        //   421: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   424: ifnull          432
        //   427: return         
        //   428: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   431: athrow         
        //   432: aload           5
        //   434: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   437: ifeq            452
        //   440: aload           5
        //   442: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   445: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   450: astore          5
        //   452: aload_1        
        //   453: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   458: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   461: if_acmpne       475
        //   464: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$UnusedParameter;.class
        //   466: astore          6
        //   468: ldc             "warn_unused_parameter"
        //   470: astore          7
        //   472: goto            506
        //   475: aload_1        
        //   476: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   481: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.LABEL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   484: if_acmpne       498
        //   487: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$UnusedLocalVariable;.class
        //   489: astore          6
        //   491: ldc             "warn_unused_label"
        //   493: astore          7
        //   495: goto            506
        //   498: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$UnusedLocalVariable;.class
        //   500: astore          6
        //   502: ldc             "warn_unused_variable"
        //   504: astore          7
        //   506: aload_0        
        //   507: aload           5
        //   509: aload_1        
        //   510: aload           6
        //   512: aload           7
        //   514: new             Ljava/lang/StringBuilder;
        //   517: dup            
        //   518: invokespecial   java/lang/StringBuilder.<init>:()V
        //   521: aload_1        
        //   522: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   527: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   530: aload           4
        //   532: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   535: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   538: getstatic       com/intellij/codeInspection/ProblemHighlightType.LIKE_UNUSED_SYMBOL:Lcom/intellij/codeInspection/ProblemHighlightType;
        //   541: invokespecial   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //   544: astore          8
        //   546: aload_0        
        //   547: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   550: aload           8
        //   552: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction;
        //   555: dup            
        //   556: aload_1        
        //   557: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   560: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   565: aload_0        
        //   566: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   569: aload           8
        //   571: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction;
        //   574: dup            
        //   575: aload_1        
        //   576: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   579: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   584: aload_0        
        //   585: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   588: aload           8
        //   590: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction$SuppressFix;
        //   593: dup            
        //   594: aload_1        
        //   595: ldc             "unused"
        //   597: ldc             "__unused"
        //   599: iconst_1       
        //   600: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction$SuppressFix.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Ljava/lang/String;Z)V
        //   603: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   608: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     72     75     79     Ljava/lang/IllegalArgumentException;
        //  52     80     80     84     Ljava/lang/IllegalArgumentException;
        //  134    150    150    154    Ljava/lang/IllegalArgumentException;
        //  154    174    177    181    Ljava/lang/IllegalArgumentException;
        //  167    184    184    188    Ljava/lang/IllegalArgumentException;
        //  222    233    233    237    Ljava/lang/IllegalArgumentException;
        //  269    303    303    307    Ljava/lang/IllegalArgumentException;
        //  354    370    373    377    Ljava/lang/IllegalArgumentException;
        //  361    384    387    391    Ljava/lang/IllegalArgumentException;
        //  377    396    396    400    Ljava/lang/IllegalArgumentException;
        //  417    428    428    432    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0377:
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
    
    private static boolean d(@NotNull final OCSymbol ocSymbol, @Nullable final PsiElement psiElement) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "hasReferenceType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocSymbol.getType().resolve(new OCResolveContext(psiElement)) instanceof OCCppReferenceType;
    }
    
    private boolean e(@NotNull final OCSymbol ocSymbol, @Nullable final PsiElement psiElement) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "enableUnusedCheck"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCType resolve = ocSymbol.getType().resolve(new OCResolveContext(psiElement));
        Label_0089: {
            try {
                if (c(ocSymbol)) {
                    return false;
                }
                final OCType ocType = resolve;
                final boolean b = a(ocType);
                if (b) {
                    return false;
                }
                break Label_0089;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCType ocType = resolve;
                final boolean b = a(ocType);
                if (b) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                switch (ocSymbol.getKind()) {
                    case PARAMETER: {
                        return this.b(ocSymbol);
                    }
                    case LOCAL_VARIABLE: {
                        break;
                    }
                    case CATCH_EXCEPTION_VARIABLE: {
                        return false;
                    }
                    default: {
                        return true;
                    }
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return c(ocSymbol, psiElement);
    }
    
    private static boolean c(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "specifiedByUsedOrUnusedAttribute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0073: {
            try {
                if (ocSymbol.hasAttribute("unused")) {
                    break Label_0073;
                }
                final OCSymbol ocSymbol2 = ocSymbol;
                final String s = "used";
                final boolean b = ocSymbol2.hasAttribute(s);
                if (b) {
                    break Label_0073;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCSymbol ocSymbol2 = ocSymbol;
                final String s = "used";
                final boolean b = ocSymbol2.hasAttribute(s);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    private static boolean a(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "specifiedByUsedOrUnusedAttribute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocType instanceof OCStructType) {
                return c(((OCStructType)ocType).getSymbol());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    private boolean b(@NotNull final OCSymbol p0) {
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
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "enableUnusedCheckForParameter"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myCodeFragments:[Lcom/intellij/psi/PsiElement;
        //    48: iconst_0       
        //    49: aaload         
        //    50: astore_2       
        //    51: aload_2        
        //    52: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //    55: ifeq            69
        //    58: aload_2        
        //    59: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //    62: goto            70
        //    65: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aconst_null    
        //    70: astore_3       
        //    71: aload_3        
        //    72: ifnull          88
        //    75: aload_3        
        //    76: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    81: goto            89
        //    84: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aconst_null    
        //    89: astore          4
        //    91: aload           4
        //    93: ifnull          155
        //    96: aload           4
        //    98: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   103: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.BLOCK:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   106: if_acmpeq       155
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: aload           4
        //   118: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   123: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.LAMBDA:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   126: if_acmpeq       155
        //   129: goto            136
        //   132: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   135: athrow         
        //   136: aload           4
        //   138: ldc             "ibaction"
        //   140: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.hasAttribute:(Ljava/lang/String;)Z
        //   145: ifeq            161
        //   148: goto            155
        //   151: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   154: athrow         
        //   155: iconst_0       
        //   156: ireturn        
        //   157: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   160: athrow         
        //   161: aload           4
        //   163: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   166: ifeq            197
        //   169: aload           4
        //   171: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   174: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isMainFunction:()Z
        //   177: ifne            195
        //   180: goto            187
        //   183: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   186: athrow         
        //   187: iconst_1       
        //   188: goto            196
        //   191: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: iconst_0       
        //   196: ireturn        
        //   197: aload           4
        //   199: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   202: ifeq            352
        //   205: aload           4
        //   207: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   210: astore          5
        //   212: aload           5
        //   214: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   219: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   222: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   227: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.IMPLEMENTATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   230: if_acmpeq       239
        //   233: iconst_0       
        //   234: ireturn        
        //   235: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   238: athrow         
        //   239: aload           5
        //   241: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch.getParameters:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;)Lcom/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters;
        //   244: astore          6
        //   246: aload           6
        //   248: iconst_1       
        //   249: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.setAncestors:(Z)V
        //   252: aload           6
        //   254: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch.search:(Lcom/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters;)Lcom/intellij/util/Query;
        //   257: invokeinterface com/intellij/util/Query.findFirst:()Ljava/lang/Object;
        //   262: ifnull          271
        //   265: iconst_0       
        //   266: ireturn        
        //   267: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   270: athrow         
        //   271: aload           5
        //   273: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   278: astore          7
        //   280: aload           7
        //   282: ifnull          289
        //   285: aload           7
        //   287: astore          5
        //   289: aload_1        
        //   290: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getProject:()Lcom/intellij/openapi/project/Project;
        //   295: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.getProjectSourcesScope:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/search/GlobalSearchScope;
        //   298: astore          8
        //   300: aload           5
        //   302: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   307: astore          9
        //   309: aload           9
        //   311: ifnonnull       320
        //   314: iconst_0       
        //   315: ireturn        
        //   316: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   319: athrow         
        //   320: new             Lcom/intellij/psi/search/searches/ReferencesSearch$SearchParameters;
        //   323: dup            
        //   324: aload           9
        //   326: aload           8
        //   328: iconst_0       
        //   329: invokespecial   com/intellij/psi/search/searches/ReferencesSearch$SearchParameters.<init>:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/search/SearchScope;Z)V
        //   332: astore          10
        //   334: new             Lcom/jetbrains/cidr/lang/search/OCMethodReferencesSearch;
        //   337: dup            
        //   338: invokespecial   com/jetbrains/cidr/lang/search/OCMethodReferencesSearch.<init>:()V
        //   341: aload           10
        //   343: invokedynamic   process:()Lcom/intellij/util/Processor;
        //   348: invokevirtual   com/jetbrains/cidr/lang/search/OCMethodReferencesSearch.execute:(Lcom/intellij/psi/search/searches/ReferencesSearch$SearchParameters;Lcom/intellij/util/Processor;)Z
        //   351: ireturn        
        //   352: iconst_0       
        //   353: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  51     65     65     69     Ljava/lang/IllegalArgumentException;
        //  71     84     84     88     Ljava/lang/IllegalArgumentException;
        //  91     109    112    116    Ljava/lang/IllegalArgumentException;
        //  96     129    132    136    Ljava/lang/IllegalArgumentException;
        //  116    148    151    155    Ljava/lang/IllegalArgumentException;
        //  136    157    157    161    Ljava/lang/IllegalArgumentException;
        //  161    180    183    187    Ljava/lang/IllegalArgumentException;
        //  169    191    191    195    Ljava/lang/IllegalArgumentException;
        //  212    235    235    239    Ljava/lang/IllegalArgumentException;
        //  246    267    267    271    Ljava/lang/IllegalArgumentException;
        //  309    316    316    320    Ljava/lang/IllegalArgumentException;
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
    
    private static boolean c(@NotNull final OCSymbol ocSymbol, @Nullable final PsiElement psiElement) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "enableUnusedCheckForLocalVariable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!(ocSymbol instanceof OCDeclaratorSymbol)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCType resolve = ((OCDeclaratorSymbol)ocSymbol).getType().resolve(new OCResolveContext(psiElement));
        if (resolve instanceof OCStructType) {
            return a(((OCStructType)resolve).getSymbol(), psiElement);
        }
        try {
            if (!(resolve instanceof OCMagicType)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return false;
    }
    
    private static boolean a(@NotNull final OCStructSymbol ocStructSymbol, @Nullable final PsiElement psiElement) {
        try {
            if (ocStructSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "hasTrivialDestructor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        PsiFile containingFile = null;
        Label_0062: {
            try {
                if (psiElement != null) {
                    containingFile = psiElement.getContainingFile();
                    break Label_0062;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            containingFile = null;
        }
        final Map<OCStructSymbol, Boolean> a = a(containingFile);
        Label_0089: {
            try {
                if (a == null) {
                    break Label_0089;
                }
                final Map<OCStructSymbol, Boolean> map = a;
                final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                final boolean b = map.containsKey(ocStructSymbol2);
                if (b) {
                    break Label_0089;
                }
                break Label_0089;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final Map<OCStructSymbol, Boolean> map = a;
                final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                final boolean b = map.containsKey(ocStructSymbol2);
                if (b) {
                    return a.get(ocStructSymbol);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        final Boolean value = ocStructSymbol.hasTrivialDestructor(new OCResolveContext(psiElement));
        try {
            if (a != null) {
                a.put(ocStructSymbol, value);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return value;
    }
    
    private static Map<OCStructSymbol, Boolean> a(@Nullable final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (Map<OCStructSymbol, Boolean>)CachedValuesManager.getManager(psiFile.getProject()).getCachedValue((UserDataHolder)psiFile, (Key)OCDataFlowAnalyzer.TRIVIAL_DTOR_CACHE, () -> new CachedValueProvider.Result((Object)OCTypeUtils.newDeepEqualityMap(), new Object[] { o }), false);
    }
    
    public void analyzeNotReleased(@NotNull final OCSymbol p0, @NotNull final OCNotReleasedVariablesChecker p1, final boolean p2) {
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
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "analyzeNotReleased"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "checker"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "analyzeNotReleased"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: iload_3        
        //    89: ifeq            104
        //    92: aload_2        
        //    93: iconst_1       
        //    94: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.setStartFromReturns:(Z)V
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: aload_1        
        //   105: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   110: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   113: ifeq            147
        //   116: aload_1        
        //   117: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   120: ifeq            152
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: aload_1        
        //   131: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   134: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isFriendOrStatic:()Z
        //   137: ifeq            152
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: return         
        //   148: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   151: athrow         
        //   152: aload_2        
        //   153: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.process:()V
        //   156: new             Ljava/util/HashSet;
        //   159: dup            
        //   160: invokespecial   java/util/HashSet.<init>:()V
        //   163: astore          4
        //   165: aload_2        
        //   166: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.getNotReleasedElements:()Ljava/util/List;
        //   169: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   174: astore          5
        //   176: aload           5
        //   178: invokeinterface java/util/Iterator.hasNext:()Z
        //   183: ifeq            269
        //   186: aload           5
        //   188: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   193: checkcast       Lcom/intellij/psi/PsiElement;
        //   196: astore          6
        //   198: aload           4
        //   200: aload           6
        //   202: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   207: pop            
        //   208: aload_0        
        //   209: aload           6
        //   211: aload_1        
        //   212: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NotReleasedValue;.class
        //   214: ldc             "CIDR"
        //   216: ldc             "Retained value may have not been released"
        //   218: invokespecial   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   221: astore          7
        //   223: aload_0        
        //   224: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   227: aload           7
        //   229: new             Lcom/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction;
        //   232: dup            
        //   233: aload_1        
        //   234: aload           6
        //   236: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)V
        //   239: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   244: aload_0        
        //   245: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   248: aload           7
        //   250: new             Lcom/jetbrains/cidr/lang/quickfixes/OCSendMessageToObjectIntentionAction;
        //   253: dup            
        //   254: aload           6
        //   256: ldc             "autorelease"
        //   258: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCSendMessageToObjectIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   261: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   266: goto            176
        //   269: iload_3        
        //   270: ifeq            463
        //   273: aload_0        
        //   274: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myCodeFragments:[Lcom/intellij/psi/PsiElement;
        //   277: iconst_0       
        //   278: aaload         
        //   279: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   282: ifeq            463
        //   285: goto            292
        //   288: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   291: athrow         
        //   292: aload_0        
        //   293: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.myCodeFragments:[Lcom/intellij/psi/PsiElement;
        //   296: iconst_0       
        //   297: aaload         
        //   298: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   301: astore          5
        //   303: aload           5
        //   305: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isRetainMethod:(Lcom/jetbrains/cidr/lang/psi/OCCallable;)Z
        //   308: ifne            463
        //   311: aload_2        
        //   312: iconst_0       
        //   313: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.setStartFromReturns:(Z)V
        //   316: aload_2        
        //   317: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.process:()V
        //   320: aload_2        
        //   321: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNotReleasedVariablesChecker.getNotReleasedElements:()Ljava/util/List;
        //   324: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   329: astore          6
        //   331: aload           6
        //   333: invokeinterface java/util/Iterator.hasNext:()Z
        //   338: ifeq            463
        //   341: aload           6
        //   343: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   348: checkcast       Lcom/intellij/psi/PsiElement;
        //   351: astore          7
        //   353: aload           4
        //   355: aload           7
        //   357: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   362: ifeq            372
        //   365: goto            331
        //   368: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   371: athrow         
        //   372: aload_0        
        //   373: aload           7
        //   375: aload_1        
        //   376: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NotReleasedValue;.class
        //   378: ldc             "CIDR"
        //   380: ldc             "Retained value may have not been released"
        //   382: invokespecial   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/lang/annotation/Annotation;
        //   385: astore          8
        //   387: aload_0        
        //   388: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   391: aload           8
        //   393: new             Lcom/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction;
        //   396: dup            
        //   397: aload_1        
        //   398: aload           7
        //   400: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)V
        //   403: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   408: aload_0        
        //   409: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   412: aload           8
        //   414: new             Lcom/jetbrains/cidr/lang/quickfixes/OCSendMessageToObjectIntentionAction;
        //   417: dup            
        //   418: aload           7
        //   420: ldc             "autorelease"
        //   422: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCSendMessageToObjectIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   425: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   430: aload_0        
        //   431: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   434: aload           8
        //   436: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction;
        //   439: dup            
        //   440: aload           5
        //   442: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   447: ldc             "ns_returns_retained"
        //   449: ldc             "NS_RETURNS_RETAINED"
        //   451: iconst_1       
        //   452: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Ljava/lang/String;Z)V
        //   455: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.registerQuickFix:(Lcom/intellij/lang/annotation/Annotation;Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   460: goto            331
        //   463: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     97     100    104    Ljava/lang/IllegalArgumentException;
        //  104    123    126    130    Ljava/lang/IllegalArgumentException;
        //  116    140    143    147    Ljava/lang/IllegalArgumentException;
        //  130    148    148    152    Ljava/lang/IllegalArgumentException;
        //  269    285    288    292    Ljava/lang/IllegalArgumentException;
        //  353    368    368    372    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0130:
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
    
    private void a() {
        final OCEscapedValuesChecker ocEscapedValuesChecker = new OCEscapedValuesChecker(this.myGraph);
        ocEscapedValuesChecker.process();
        final Iterator<PsiElement> iterator = ocEscapedValuesChecker.getEscapedVariables().iterator();
        while (iterator.hasNext()) {
            this.mySink.addWarningAnnotation(iterator.next(), OCInspections.LocalValueEscapesScope.class, OCClangMessageFinder.getInstance().getReturnStackAddr(), "Local value may escape the method/function");
        }
        for (final PsiElement psiElement : ocEscapedValuesChecker.getEscapedObjects()) {
            StringBuilder sb = null;
            String s = null;
            Label_0123: {
                try {
                    sb = new StringBuilder();
                    if (psiElement instanceof OCBlockExpression) {
                        s = "Block";
                        break Label_0123;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                s = "Value";
            }
            final Annotation addWarningAnnotation = this.mySink.addWarningAnnotation(psiElement, OCInspections.LocalValueEscapesScope.class, OCClangMessageFinder.getInstance().getReturnTempAddr(), sb.append(s).append(" escapes the local scope").toString());
            try {
                if (!(psiElement instanceof OCBlockExpression)) {
                    continue;
                }
                this.mySink.registerQuickFix(addWarningAnnotation, (IntentionAction)new OCSendMessageToObjectIntentionAction(psiElement, "copy"));
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    private void a(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "analyzeInputOutput"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.mySelection == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCInputOutputVariablesFinder ocInputOutputVariablesFinder = new OCInputOutputVariablesFinder(this.myGraph, ocSymbol, this.mySelection);
        ocInputOutputVariablesFinder.process();
        boolean b = false;
        if (ocInputOutputVariablesFinder.isInputVariable()) {
            this.myInputVariables.add(ocSymbol);
            b = true;
        }
        if (ocInputOutputVariablesFinder.isOutputVariable()) {
            this.myOutputVariables.add(ocSymbol);
            b = true;
        }
        if (ocInputOutputVariablesFinder.isWrittenVariable()) {
            this.myWrittenVariables.add(ocSymbol);
            b = true;
        }
        if (ocInputOutputVariablesFinder.isEscapedDeclarator()) {
            this.myEscapedDeclarators.add(ocSymbol);
            b = true;
        }
        try {
            if (b) {
                this.myVariableUsages.put(ocSymbol, ocInputOutputVariablesFinder.getVariableUsages());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
    }
    
    public List<PsiReference> getVariableUsages(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "getVariableUsages"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myVariableUsages.get(ocSymbol);
    }
    
    public boolean hasCrossSelectionJumps() {
        return this.myGraphBuilder.hasCrossSelectionJumps();
    }
    
    public boolean hasDanglingJumps() {
        return this.myGraphBuilder.hasDanglingJumps();
    }
    
    @Nullable
    private Annotation a(@Nullable final PsiElement p0, @NotNull final OCSymbol p1, @Nullable final Class<? extends OCInspection> p2, @Nullable final String p3, @NotNull final String p4, @Nullable final ProblemHighlightType p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "addWarningAnnotation"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload           5
        //    46: ifnonnull       89
        //    49: new             Ljava/lang/IllegalArgumentException;
        //    52: dup            
        //    53: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    55: ldc             3
        //    57: anewarray       Ljava/lang/Object;
        //    60: dup            
        //    61: ldc             0
        //    63: ldc             "message"
        //    65: aastore        
        //    66: dup            
        //    67: ldc             1
        //    69: ldc             "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer"
        //    71: aastore        
        //    72: dup            
        //    73: ldc             2
        //    75: ldc             "addWarningAnnotation"
        //    77: aastore        
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: aload_1        
        //    90: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRangeInMacroCall:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/parser/OCMacroRange;
        //    93: astore          7
        //    95: aload           7
        //    97: ifnull          175
        //   100: aload           7
        //   102: invokevirtual   com/jetbrains/cidr/lang/parser/OCMacroRange.mapsToArguments:()Z
        //   105: ifne            175
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: aload           7
        //   117: invokevirtual   com/jetbrains/cidr/lang/parser/OCMacroRange.getMacroCall:()Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   120: astore          8
        //   122: aload_2        
        //   123: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getOffset:()I
        //   128: ifeq            169
        //   131: aload           8
        //   133: ifnull          175
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   142: athrow         
        //   143: aload_2        
        //   144: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getOffset:()I
        //   149: aload           8
        //   151: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   156: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   159: if_icmpne       175
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aconst_null    
        //   170: areturn        
        //   171: invokestatic    com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: aload_0        
        //   176: getfield        com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.mySink:Lcom/jetbrains/cidr/lang/daemon/OCAnnotatorSink;
        //   179: aload_1        
        //   180: aload_3        
        //   181: aload           4
        //   183: aload           5
        //   185: aload           6
        //   187: invokeinterface com/jetbrains/cidr/lang/daemon/OCAnnotatorSink.addWarningAnnotation:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //   192: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/Class<+Lcom/jetbrains/cidr/lang/inspections/OCInspection;>;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;)Lcom/intellij/lang/annotation/Annotation;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     85     85     89     Ljava/lang/IllegalArgumentException;
        //  95     108    111    115    Ljava/lang/IllegalArgumentException;
        //  122    136    139    143    Ljava/lang/IllegalArgumentException;
        //  131    162    165    169    Ljava/lang/IllegalArgumentException;
        //  143    171    171    175    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0143:
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
    
    @Nullable
    private Annotation a(@Nullable final PsiElement psiElement, @NotNull final OCSymbol ocSymbol, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "addWarningAnnotation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer", "addWarningAnnotation"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.a(psiElement, ocSymbol, clazz, s, s2, null);
    }
    
    static {
        TRIVIAL_DTOR_CACHE = Key.create("TRIVIAL_DTOR_CACHE_IN_FILE");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
