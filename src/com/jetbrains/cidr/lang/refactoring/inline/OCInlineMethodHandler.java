// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.inline;

import com.intellij.psi.PsiReference;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.util.containers.Stack;
import java.util.Set;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.psi.PsiNamedElement;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.SmartPsiElementPointer;
import java.util.Map;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCPropertyAttribute;
import com.intellij.psi.xml.XmlElement;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.jetbrains.cidr.lang.dfa.OCUnreachableCodeFinder;
import com.jetbrains.cidr.lang.util.OCElementsRange;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import com.jetbrains.cidr.lang.dfa.OCInstruction;
import com.jetbrains.cidr.lang.dfa.OCNode;
import com.jetbrains.cidr.lang.dfa.OCControlFlowGraph;
import com.jetbrains.cidr.lang.dfa.OCDataFlowAlgorithm;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.dfa.OCDataFlowAnalyzer;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.search.OCSearchUtil;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.psi.OCInterface;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCCallableKind;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.search.searches.ReferencesSearch;
import java.util.ArrayList;
import java.util.List;
import com.intellij.psi.search.SearchScope;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCCallable;

public class OCInlineMethodHandler extends OCInlineActionHandlerBase<OCCallable>
{
    @Override
    protected String getElementKind(final OCCallable ocCallable) {
        return ocCallable.getKind().toStringLowercase();
    }
    
    public boolean canInlineElement(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //     4: ifne            40
        //     7: aload_1        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    11: ifeq            48
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    20: athrow         
        //    21: aload_1        
        //    22: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    27: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    30: ifeq            48
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    39: athrow         
        //    40: iconst_1       
        //    41: goto            49
        //    44: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    47: athrow         
        //    48: iconst_0       
        //    49: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      14     17     21     Ljava/lang/IllegalStateException;
        //  7      33     36     40     Ljava/lang/IllegalStateException;
        //  21     44     44     48     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    protected OCCallable getElementToInline(final PsiElement psiElement) {
        OCCallable ocCallable = null;
        Label_0027: {
            try {
                if (psiElement instanceof OCCallable) {
                    ocCallable = (OCCallable)psiElement;
                    break Label_0027;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            ocCallable = (OCCallable)psiElement.getParent();
        }
        final OCCallable ocCallable2 = ocCallable;
        if (((OCCallable<OCSymbol>)ocCallable2).getBody() == null) {
            final OCSymbol symbol = ((OCCallable<OCSymbol>)ocCallable2).getSymbol();
            try {
                if (symbol == null || !symbol.isPredeclaration()) {
                    return ocCallable2;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            final OCSymbol definitionSymbol = symbol.getDefinitionSymbol();
            PsiElement locateDefinition = null;
            Label_0095: {
                try {
                    if (definitionSymbol != null) {
                        locateDefinition = definitionSymbol.locateDefinition();
                        break Label_0095;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                locateDefinition = null;
            }
            final PsiElement psiElement2 = locateDefinition;
            try {
                if (psiElement2 instanceof OCMethod) {
                    return (OCCallable)psiElement2;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            try {
                if (psiElement2 instanceof OCDeclarator) {
                    return (OCCallable)psiElement2.getParent();
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
        }
        return ocCallable2;
    }
    
    @Override
    protected List<PsiElement> findUsages(final OCCallable ocCallable, final SearchScope searchScope) {
        if (ocCallable instanceof OCMethod) {
            final ArrayList<PsiElement> list = new ArrayList<PsiElement>();
            ReferencesSearch.search((PsiElement)ocCallable, searchScope, false).forEach(psiReference -> {
                list.add(psiReference.getElement());
                return true;
            });
            return list;
        }
        return super.findUsages(ocCallable, searchScope);
    }
    
    @Override
    protected String checkValidness(final OCCallable ocCallable, final List<PsiElement> list, final PsiElement psiElement, final String s, final Editor editor, final Ref<PsiElement> ref, final List<String> list2, final boolean b) {
        try {
            if (ocCallable.getKind() == OCCallableKind.BLOCK) {
                return "Cannot inline blocks";
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0173: {
            Label_0152: {
                Label_0106: {
                    try {
                        if (ocCallable.getBody() != null) {
                            break Label_0152;
                        }
                        if (!(ocCallable instanceof OCMethod)) {
                            break Label_0106;
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getParentOfType((PsiElement)ocCallable, (Class)OCClassDeclaration.class);
                    try {
                        if (ocClassDeclaration instanceof OCProtocol) {
                            return "Cannot inline a protocol method";
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                    try {
                        if (ocClassDeclaration instanceof OCInterface) {
                            return "Interface " + s + " was not implemented";
                        }
                    }
                    catch (IllegalStateException ex4) {
                        throw a(ex4);
                    }
                    try {
                        if (ocCallable.getBody() == null) {
                            return "Cannot inline a " + ocCallable.getKind().toStringLowercase() + " with an empty body";
                        }
                    }
                    catch (IllegalStateException ex5) {
                        throw a(ex5);
                    }
                }
                try {
                    if (!list.isEmpty()) {
                        break Label_0173;
                    }
                    final boolean b2 = b;
                    if (!b2) {
                        break Label_0173;
                    }
                    break Label_0173;
                }
                catch (IllegalStateException ex6) {
                    throw a(ex6);
                }
            }
            try {
                final boolean b2 = b;
                if (!b2) {
                    return "There are no calls of " + s + " in the project";
                }
            }
            catch (IllegalStateException ex7) {
                throw a(ex7);
            }
        }
        checkMethodsHierarchy(ocCallable, s, list2);
        return a(ocCallable);
    }
    
    static void checkMethodsHierarchy(final OCCallable ocCallable, final String s, final List<String> list) {
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        Label_0092: {
            List<String> list2 = null;
            StringBuilder sb3 = null;
            String s5 = null;
            Label_0077: {
                Label_0066: {
                    try {
                        OCSearchUtil.processMembersHierarchy((OCSymbolWithParent)ocCallable.getSymbol(), (com.intellij.util.Processor<? super OCSymbolWithParent>)findFirstProcessor, false, true, false);
                        if (!findFirstProcessor.isFound()) {
                            break Label_0092;
                        }
                        list2 = list;
                        final StringBuilder sb = new StringBuilder();
                        final String s2 = s;
                        final String s3 = StringUtil.capitalize(s2);
                        final StringBuilder sb2 = sb.append(s3);
                        final String s4 = " has inheritor ";
                        sb3 = sb2.append(s4);
                        final OCCallable<OCSymbol> ocCallable2 = (OCCallable<OCSymbol>)ocCallable;
                        final boolean b = ocCallable2 instanceof OCMethod;
                        if (b) {
                            break Label_0066;
                        }
                        break Label_0066;
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    try {
                        list2 = list;
                        final StringBuilder sb = new StringBuilder();
                        final String s2 = s;
                        final String s3 = StringUtil.capitalize(s2);
                        final StringBuilder sb2 = sb.append(s3);
                        final String s4 = " has inheritor ";
                        sb3 = sb2.append(s4);
                        final OCCallable<OCSymbol> ocCallable2 = (OCCallable<OCSymbol>)ocCallable;
                        final boolean b = ocCallable2 instanceof OCMethod;
                        if (b) {
                            s5 = "methods";
                            break Label_0077;
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                }
                s5 = "functions";
            }
            list2.add(sb3.append(s5).toString());
            return;
            try {
                OCSearchUtil.processMembersHierarchy((OCSymbolWithParent)ocCallable.getSymbol(), (com.intellij.util.Processor<? super OCSymbolWithParent>)findFirstProcessor, true, false, false);
                if (findFirstProcessor.isFound()) {
                    list.add(StringUtil.capitalize(s) + " inherits a method from " + ((OCSymbolWithParent)findFirstProcessor.getFoundValue()).getParent().getNameWithKindLowercase());
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
    }
    
    @Nullable
    private static String a(final OCCallable ocCallable) {
        final Ref ref = new Ref();
        final OCDataFlowAnalyzer ocDataFlowAnalyzer = new OCDataFlowAnalyzer((PsiElement)ocCallable, null);
        Label_0059: {
            try {
                ocDataFlowAnalyzer.buildControlFlowGraph();
                if (!ocDataFlowAnalyzer.getUnreachableCodeFinder().isDeadEndReached()) {
                    break Label_0059;
                }
                final OCDataFlowAnalyzer ocDataFlowAnalyzer2 = ocDataFlowAnalyzer;
                final OCUnreachableCodeFinder ocUnreachableCodeFinder = ocDataFlowAnalyzer2.getUnreachableCodeFinder();
                final boolean b = ocUnreachableCodeFinder.isExitNodeReached();
                if (b) {
                    return "Cannot inline methods with return statements interrupting the execution flow";
                }
                break Label_0059;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCDataFlowAnalyzer ocDataFlowAnalyzer2 = ocDataFlowAnalyzer;
                final OCUnreachableCodeFinder ocUnreachableCodeFinder = ocDataFlowAnalyzer2.getUnreachableCodeFinder();
                final boolean b = ocUnreachableCodeFinder.isExitNodeReached();
                if (b) {
                    return "Cannot inline methods with return statements interrupting the execution flow";
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        new OCDataFlowAlgorithm(ocDataFlowAnalyzer.getGraph()) {
            @Override
            public void process() {
                final Iterator<OCNode> iterator = this.myCfg.getExitNodes().iterator();
                while (iterator.hasNext()) {
                    final OCNode nodeAfterReturn = iterator.next().getNodeAfterReturn();
                    try {
                        if (nodeAfterReturn == null) {
                            continue;
                        }
                        this.traverse(nodeAfterReturn, null, null, true);
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                }
            }
            
            @Override
            protected boolean processNode(@NotNull final OCNode ocNode, final OCSymbol ocSymbol, final boolean b, @Nullable final OCInstruction ocInstruction, final OCInstruction ocInstruction2) {
                try {
                    if (ocNode == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler$1", "processNode"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                final OCElementsRange range = ocNode.getRange();
                Label_0070: {
                    try {
                        if (range == null) {
                            return true;
                        }
                        final OCElementsRange ocElementsRange = range;
                        final boolean b2 = ocElementsRange.isEmpty();
                        if (!b2) {
                            break Label_0070;
                        }
                        return true;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final OCElementsRange ocElementsRange = range;
                        final boolean b2 = ocElementsRange.isEmpty();
                        if (!b2) {
                            ref.set((Object)"Cannot inline methods with return statements interrupting the execution flow");
                            return false;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                return true;
            }
            
            private static IllegalArgumentException b(final IllegalArgumentException ex) {
                return ex;
            }
        }.process();
        return (String)ref.get();
    }
    
    @Override
    protected String checkUsageValid(final PsiElement psiElement, final OCCallable ocCallable) {
        try {
            if (psiElement instanceof OCSelectorExpression) {
                return "Cannot inline method reference in '@selector' expression";
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement instanceof XmlElement) {
                return "Cannot inline method reference in XML file";
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (psiElement instanceof OCPropertyAttribute) {
                return "Cannot inline method reference in property attribute";
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        Label_0080: {
            try {
                if (!(psiElement instanceof OCReferenceElement)) {
                    break Label_0080;
                }
                final PsiElement psiElement2 = psiElement;
                final PsiElement psiElement3 = psiElement2.getParent();
                final PsiElement psiElement4 = psiElement3.getParent();
                final boolean b = psiElement4 instanceof OCCallExpression;
                if (!b) {
                    return "Cannot inline function reference in non-call expression";
                }
                break Label_0080;
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final PsiElement psiElement3 = psiElement2.getParent();
                final PsiElement psiElement4 = psiElement3.getParent();
                final boolean b = psiElement4 instanceof OCCallExpression;
                if (!b) {
                    return "Cannot inline function reference in non-call expression";
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
            try {
                if ("Swift".equals(psiElement.getLanguage().getDisplayName())) {
                    return "Cannot inline usage in Swift code";
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
        }
        try {
            if (PsiTreeUtil.isAncestor((PsiElement)ocCallable, psiElement, true)) {
                return "Cannot inline recursive " + ocCallable.getKind().toStringLowercase() + " call";
            }
        }
        catch (IllegalStateException ex7) {
            throw a(ex7);
        }
        return null;
    }
    
    @Override
    protected void sortUsages(final List<PsiElement> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokedynamic   compare:()Ljava/util/Comparator;
        //     6: invokestatic    java/util/Collections.sort:(Ljava/util/List;Ljava/util/Comparator;)V
        //     9: return         
        //    Signature:
        //  (Ljava/util/List<Lcom/intellij/psi/PsiElement;>;)V
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0043_1:
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
    protected void inlineUsage(final PsiElement p0, final OCCallable p1, final PsiElement p2, final Project p3, final Map<SmartPsiElementPointer, Pair<OCSymbol, OCVisibility>> p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //     6: astore          6
        //     8: aload_2        
        //     9: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    14: invokeinterface com/intellij/psi/PsiFile.copy:()Lcom/intellij/psi/PsiElement;
        //    19: checkcast       Lcom/intellij/psi/PsiFile;
        //    22: astore          7
        //    24: aload           7
        //    26: aload           6
        //    28: aload_2        
        //    29: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    32: iconst_1       
        //    33: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.findElementAtRange:(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/util/TextRange;Ljava/lang/Class;Z)Lcom/intellij/psi/PsiElement;
        //    36: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //    39: astore          8
        //    41: aload           8
        //    43: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getParameters:()Ljava/util/List;
        //    48: astore          9
        //    50: new             Lcom/intellij/util/containers/hash/HashMap;
        //    53: dup            
        //    54: aload           9
        //    56: invokeinterface java/util/List.size:()I
        //    61: invokespecial   com/intellij/util/containers/hash/HashMap.<init>:(I)V
        //    64: astore          10
        //    66: aload           9
        //    68: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    73: astore          11
        //    75: aload           11
        //    77: invokeinterface java/util/Iterator.hasNext:()Z
        //    82: ifeq            115
        //    85: aload           11
        //    87: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    92: checkcast       Lcom/intellij/psi/PsiNamedElement;
        //    95: astore          12
        //    97: aload           10
        //    99: aload           12
        //   101: aload           12
        //   103: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Lcom/intellij/psi/PsiNamedElement;)Ljava/util/List;
        //   106: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   111: pop            
        //   112: goto            75
        //   115: aload           8
        //   117: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   122: new             Lcom/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler$2;
        //   125: dup            
        //   126: aload_0        
        //   127: aload           10
        //   129: invokespecial   com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler$2.<init>:(Lcom/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler;Ljava/util/Map;)V
        //   132: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //   137: aload_2        
        //   138: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   141: ifeq            188
        //   144: aload_1        
        //   145: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   148: ifeq            188
        //   151: goto            158
        //   154: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   157: athrow         
        //   158: aload_1        
        //   159: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   162: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifyingTokenKind:()Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   167: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   170: if_acmpne       188
        //   173: goto            180
        //   176: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   179: athrow         
        //   180: iconst_1       
        //   181: goto            189
        //   184: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   187: athrow         
        //   188: iconst_0       
        //   189: istore          11
        //   191: aload           8
        //   193: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   198: iload           11
        //   200: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCBindUtil.insertRedundantQualifiers:(Lcom/intellij/psi/PsiElement;Z)Lcom/intellij/psi/PsiElement;
        //   203: pop            
        //   204: aload           8
        //   206: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   211: iconst_0       
        //   212: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCBindUtil.encodeContextInfo:(Lcom/intellij/psi/PsiElement;Z)V
        //   215: aload_1        
        //   216: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   219: astore          12
        //   221: aload           12
        //   223: ldc             Lcom/jetbrains/cidr/lang/psi/OCStatement;.class
        //   225: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   228: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   231: astore          13
        //   233: aload           13
        //   235: ifnull          250
        //   238: aload           12
        //   240: ifnonnull       255
        //   243: goto            250
        //   246: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   249: athrow         
        //   250: return         
        //   251: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   254: athrow         
        //   255: aload           12
        //   257: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   262: aload           13
        //   264: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getTextOffset:()I
        //   269: ineg           
        //   270: invokevirtual   com/intellij/openapi/util/TextRange.shiftRight:(I)Lcom/intellij/openapi/util/TextRange;
        //   273: astore          14
        //   275: aload           13
        //   277: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.ensureParentIsBlockStatement:(Lcom/jetbrains/cidr/lang/psi/OCStatement;)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   280: astore          13
        //   282: aload           13
        //   284: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   289: aload           14
        //   291: aload           13
        //   293: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getTextOffset:()I
        //   298: invokevirtual   com/intellij/openapi/util/TextRange.shiftRight:(I)Lcom/intellij/openapi/util/TextRange;
        //   301: aload           12
        //   303: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   306: iconst_1       
        //   307: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.findElementAtRange:(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/util/TextRange;Ljava/lang/Class;Z)Lcom/intellij/psi/PsiElement;
        //   310: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   313: astore          12
        //   315: aload           13
        //   317: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getParent:()Lcom/intellij/psi/PsiElement;
        //   322: astore          15
        //   324: aload           12
        //   326: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.b:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/util/List;
        //   329: astore          16
        //   331: aload           12
        //   333: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   336: astore          17
        //   338: aload           17
        //   340: ifnull          366
        //   343: aload           17
        //   345: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.hasSideEffects:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Z
        //   348: ifeq            366
        //   351: goto            358
        //   354: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   357: athrow         
        //   358: iconst_1       
        //   359: goto            367
        //   362: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   365: athrow         
        //   366: iconst_0       
        //   367: istore          18
        //   369: new             Ljava/util/ArrayList;
        //   372: dup            
        //   373: invokespecial   java/util/ArrayList.<init>:()V
        //   376: astore          19
        //   378: aload           8
        //   380: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   385: astore          20
        //   387: getstatic       com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.$assertionsDisabled:Z
        //   390: ifne            417
        //   393: aload           20
        //   395: ifnonnull       417
        //   398: goto            405
        //   401: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   404: athrow         
        //   405: new             Ljava/lang/AssertionError;
        //   408: dup            
        //   409: invokespecial   java/lang/AssertionError.<init>:()V
        //   412: athrow         
        //   413: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   416: athrow         
        //   417: getstatic       com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.$assertionsDisabled:Z
        //   420: ifne            447
        //   423: aload           9
        //   425: ifnonnull       447
        //   428: goto            435
        //   431: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   434: athrow         
        //   435: new             Ljava/lang/AssertionError;
        //   438: dup            
        //   439: invokespecial   java/lang/AssertionError.<init>:()V
        //   442: athrow         
        //   443: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   446: athrow         
        //   447: getstatic       com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.$assertionsDisabled:Z
        //   450: ifne            489
        //   453: aload           9
        //   455: invokeinterface java/util/List.size:()I
        //   460: aload           16
        //   462: invokeinterface java/util/List.size:()I
        //   467: if_icmpge       489
        //   470: goto            477
        //   473: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   476: athrow         
        //   477: new             Ljava/lang/AssertionError;
        //   480: dup            
        //   481: invokespecial   java/lang/AssertionError.<init>:()V
        //   484: athrow         
        //   485: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   488: athrow         
        //   489: aload_2        
        //   490: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   493: ifeq            555
        //   496: aload_2        
        //   497: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   500: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.isInstanceMethod:()Z
        //   505: ifne            555
        //   508: goto            515
        //   511: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   514: athrow         
        //   515: aload           17
        //   517: ifnull          555
        //   520: goto            527
        //   523: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   526: athrow         
        //   527: ldc             "class"
        //   529: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   532: aload_1        
        //   533: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.sendMessageExpression:(Ljava/util/List;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   536: astore          21
        //   538: aload           21
        //   540: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   545: aload           17
        //   547: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   550: pop            
        //   551: aload           21
        //   553: astore          17
        //   555: iconst_0       
        //   556: istore          21
        //   558: iload           21
        //   560: aload           16
        //   562: invokeinterface java/util/List.size:()I
        //   567: if_icmpge       710
        //   570: aload           9
        //   572: iload           21
        //   574: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   579: checkcast       Lcom/intellij/psi/PsiNamedElement;
        //   582: astore          22
        //   584: aload           16
        //   586: iload           21
        //   588: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   593: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   596: astore          23
        //   598: aload           22
        //   600: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   603: ifeq            623
        //   606: aload           22
        //   608: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   611: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   616: goto            633
        //   619: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   622: athrow         
        //   623: aload           22
        //   625: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //   628: invokeinterface com/jetbrains/cidr/lang/psi/OCMethodSelectorPart.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   633: astore          24
        //   635: aload           22
        //   637: invokeinterface com/intellij/psi/PsiNamedElement.getName:()Ljava/lang/String;
        //   642: aload           24
        //   644: aload           23
        //   646: aload           15
        //   648: aload           13
        //   650: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   653: astore          25
        //   655: aload           23
        //   657: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.hasSideEffects:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Z
        //   660: ifne            680
        //   663: aload           19
        //   665: aload           25
        //   667: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   672: pop            
        //   673: goto            680
        //   676: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   679: athrow         
        //   680: aload           22
        //   682: aload           25
        //   684: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getName:()Ljava/lang/String;
        //   689: aload           10
        //   691: aload           22
        //   693: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   698: checkcast       Ljava/util/List;
        //   701: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Lcom/intellij/psi/PsiNamedElement;Ljava/lang/String;Ljava/util/List;)V
        //   704: iinc            21, 1
        //   707: goto            558
        //   710: aload           17
        //   712: ifnull          743
        //   715: ldc             "receiver"
        //   717: aload           17
        //   719: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   724: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getGuessedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   727: aload           17
        //   729: aload           15
        //   731: aload           13
        //   733: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   736: goto            744
        //   739: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   742: athrow         
        //   743: aconst_null    
        //   744: astore          21
        //   746: aload_2        
        //   747: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   752: aload           15
        //   754: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   759: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   762: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   765: ifne            791
        //   768: ldc             "result"
        //   770: aload_2        
        //   771: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   776: aconst_null    
        //   777: aload           15
        //   779: aload           13
        //   781: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   784: goto            792
        //   787: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   790: athrow         
        //   791: aconst_null    
        //   792: astore          22
        //   794: aload           22
        //   796: ifnull          813
        //   799: aload           22
        //   801: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getName:()Ljava/lang/String;
        //   806: goto            815
        //   809: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   812: athrow         
        //   813: ldc             "nullResult"
        //   815: astore          23
        //   817: new             Ljava/util/ArrayList;
        //   820: dup            
        //   821: invokespecial   java/util/ArrayList.<init>:()V
        //   824: astore          24
        //   826: new             Ljava/util/HashSet;
        //   829: dup            
        //   830: invokespecial   java/util/HashSet.<init>:()V
        //   833: astore          25
        //   835: aload           20
        //   837: aload           24
        //   839: aload           25
        //   841: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;Ljava/util/List;Ljava/util/Set;)V
        //   844: aload           24
        //   846: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   851: astore          26
        //   853: aload           26
        //   855: invokeinterface java/util/Iterator.hasNext:()Z
        //   860: ifeq            1136
        //   863: aload           26
        //   865: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   870: checkcast       Lcom/intellij/psi/PsiElement;
        //   873: astore          27
        //   875: aload           27
        //   877: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReturnStatement;
        //   880: ifeq            1001
        //   883: aload           25
        //   885: aload           27
        //   887: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   892: ifne            1001
        //   895: goto            902
        //   898: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   901: athrow         
        //   902: aload           27
        //   904: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReturnStatement;
        //   907: invokeinterface com/jetbrains/cidr/lang/psi/OCReturnStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   912: astore          28
        //   914: aload           28
        //   916: ifnull          985
        //   919: new             Ljava/lang/StringBuilder;
        //   922: dup            
        //   923: invokespecial   java/lang/StringBuilder.<init>:()V
        //   926: aload           23
        //   928: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   931: ldc             "=b;"
        //   933: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   936: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   939: aload           27
        //   941: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.statementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   944: astore          29
        //   946: aload           29
        //   948: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   951: invokeinterface com/jetbrains/cidr/lang/psi/OCExpressionStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   956: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   959: astore          30
        //   961: aload           30
        //   963: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getSourceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   968: aload           28
        //   970: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   973: pop            
        //   974: aload           27
        //   976: aload           29
        //   978: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   981: pop            
        //   982: goto            998
        //   985: aload           27
        //   987: ldc             ";"
        //   989: aload           27
        //   991: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.statementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   994: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   997: pop            
        //   998: goto            1133
        //  1001: aload           27
        //  1003: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //  1006: ifeq            1068
        //  1009: aload           27
        //  1011: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //  1014: astore          28
        //  1016: aload           28
        //  1018: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.isSelfSuperOrThis:()Z
        //  1023: ifeq            1065
        //  1026: aload           21
        //  1028: ifnull          1065
        //  1031: goto            1038
        //  1034: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1037: athrow         
        //  1038: aload           28
        //  1040: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getReferenceElement:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //  1045: aload           21
        //  1047: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getName:()Ljava/lang/String;
        //  1052: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.setNameOfIdentifier:(Ljava/lang/String;)Lcom/intellij/psi/PsiElement;
        //  1057: pop            
        //  1058: goto            1065
        //  1061: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1064: athrow         
        //  1065: goto            1133
        //  1068: aload           27
        //  1070: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //  1073: ifeq            1133
        //  1076: aload           27
        //  1078: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //  1081: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1086: astore          28
        //  1088: aload           28
        //  1090: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //  1093: ifeq            1133
        //  1096: aload           27
        //  1098: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //  1101: aload           28
        //  1103: aload           15
        //  1105: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.suggestUniqueName:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //  1108: aload           10
        //  1110: aload           27
        //  1112: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //  1115: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1120: checkcast       Ljava/util/List;
        //  1123: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Lcom/intellij/psi/PsiNamedElement;Ljava/lang/String;Ljava/util/List;)V
        //  1126: goto            1133
        //  1129: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1132: athrow         
        //  1133: goto            853
        //  1136: aload           7
        //  1138: aload           20
        //  1140: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //  1145: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //  1148: iconst_1       
        //  1149: iadd           
        //  1150: aload           20
        //  1152: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //  1157: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //  1160: iconst_1       
        //  1161: isub           
        //  1162: iconst_1       
        //  1163: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.findStatementsAtRange:(Lcom/intellij/psi/PsiFile;IIZ)[Lcom/intellij/psi/PsiElement;
        //  1166: astore          26
        //  1168: getstatic       com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.$assertionsDisabled:Z
        //  1171: ifne            1198
        //  1174: aload           26
        //  1176: ifnonnull       1198
        //  1179: goto            1186
        //  1182: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1185: athrow         
        //  1186: new             Ljava/lang/AssertionError;
        //  1189: dup            
        //  1190: invokespecial   java/lang/AssertionError.<init>:()V
        //  1193: athrow         
        //  1194: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1197: athrow         
        //  1198: aload           26
        //  1200: astore          27
        //  1202: aload           27
        //  1204: arraylength    
        //  1205: istore          28
        //  1207: iconst_0       
        //  1208: istore          29
        //  1210: iload           29
        //  1212: iload           28
        //  1214: if_icmpge       1240
        //  1217: aload           27
        //  1219: iload           29
        //  1221: aaload         
        //  1222: astore          30
        //  1224: aload           15
        //  1226: aload           30
        //  1228: aload           13
        //  1230: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //  1233: pop            
        //  1234: iinc            29, 1
        //  1237: goto            1210
        //  1240: new             Lcom/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler;
        //  1243: dup            
        //  1244: invokespecial   com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.<init>:()V
        //  1247: astore          27
        //  1249: aload           12
        //  1251: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //  1254: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //  1259: astore          28
        //  1261: aload           12
        //  1263: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //  1266: ifeq            1296
        //  1269: aload           28
        //  1271: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //  1274: ifeq            1296
        //  1277: goto            1284
        //  1280: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1283: athrow         
        //  1284: aload           22
        //  1286: ifnull          1330
        //  1289: goto            1296
        //  1292: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1295: athrow         
        //  1296: aload_2        
        //  1297: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //  1300: ifeq            1345
        //  1303: goto            1310
        //  1306: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1309: athrow         
        //  1310: aload           12
        //  1312: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //  1317: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //  1320: ifeq            1345
        //  1323: goto            1330
        //  1326: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1329: athrow         
        //  1330: aload           28
        //  1332: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //  1335: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //  1338: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //  1343: astore          28
        //  1345: aload           28
        //  1347: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //  1350: ifeq            1365
        //  1353: aload           28
        //  1355: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //  1358: goto            1415
        //  1361: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1364: athrow         
        //  1365: aload           23
        //  1367: aload           15
        //  1369: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //  1372: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //  1375: astore          29
        //  1377: aload           12
        //  1379: aload           29
        //  1381: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //  1384: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //  1387: astore          29
        //  1389: aload           22
        //  1391: ifnull          1415
        //  1394: aload           27
        //  1396: aload           22
        //  1398: aload           29
        //  1400: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getReferenceElement:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //  1405: invokevirtual   com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.invokeSilently:(Lcom/intellij/psi/PsiNamedElement;Lcom/intellij/psi/PsiElement;)V
        //  1408: goto            1415
        //  1411: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1414: athrow         
        //  1415: aload           21
        //  1417: ifnull          1448
        //  1420: iload           18
        //  1422: ifne            1448
        //  1425: goto            1432
        //  1428: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1431: athrow         
        //  1432: aload           27
        //  1434: aload           21
        //  1436: aload           21
        //  1438: invokevirtual   com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.invokeSilently:(Lcom/intellij/psi/PsiNamedElement;Lcom/intellij/psi/PsiElement;)V
        //  1441: goto            1448
        //  1444: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1447: athrow         
        //  1448: aload           19
        //  1450: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  1455: astore          29
        //  1457: aload           29
        //  1459: invokeinterface java/util/Iterator.hasNext:()Z
        //  1464: ifeq            1491
        //  1467: aload           29
        //  1469: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1474: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //  1477: astore          30
        //  1479: aload           27
        //  1481: aload           30
        //  1483: aload           30
        //  1485: invokevirtual   com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.invokeSilently:(Lcom/intellij/psi/PsiNamedElement;Lcom/intellij/psi/PsiElement;)V
        //  1488: goto            1457
        //  1491: aload           15
        //  1493: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.fixAllSymbolsRecursively:(Lcom/intellij/psi/PsiElement;)V
        //  1496: aload           15
        //  1498: aconst_null    
        //  1499: aload           5
        //  1501: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCBindUtil.decodeContextInfo:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/util/Map;)V
        //  1504: aload           15
        //  1506: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCBindUtil.removeRedundantQualifiers:(Lcom/intellij/psi/PsiElement;)V
        //  1509: return         
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/psi/OCCallable;Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/project/Project;Ljava/util/Map<Lcom/intellij/psi/SmartPsiElementPointer;Lcom/intellij/openapi/util/Pair<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;>;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  115    151    154    158    Ljava/lang/IllegalStateException;
        //  144    173    176    180    Ljava/lang/IllegalStateException;
        //  158    184    184    188    Ljava/lang/IllegalStateException;
        //  233    243    246    250    Ljava/lang/IllegalStateException;
        //  238    251    251    255    Ljava/lang/IllegalStateException;
        //  338    351    354    358    Ljava/lang/IllegalStateException;
        //  343    362    362    366    Ljava/lang/IllegalStateException;
        //  387    398    401    405    Ljava/lang/IllegalStateException;
        //  393    413    413    417    Ljava/lang/IllegalStateException;
        //  417    428    431    435    Ljava/lang/IllegalStateException;
        //  423    443    443    447    Ljava/lang/IllegalStateException;
        //  447    470    473    477    Ljava/lang/IllegalStateException;
        //  453    485    485    489    Ljava/lang/IllegalStateException;
        //  489    508    511    515    Ljava/lang/IllegalStateException;
        //  496    520    523    527    Ljava/lang/IllegalStateException;
        //  598    619    619    623    Ljava/lang/IllegalStateException;
        //  655    673    676    680    Ljava/lang/IllegalStateException;
        //  710    739    739    743    Ljava/lang/IllegalStateException;
        //  746    787    787    791    Ljava/lang/IllegalStateException;
        //  794    809    809    813    Ljava/lang/IllegalStateException;
        //  875    895    898    902    Ljava/lang/IllegalStateException;
        //  1016   1031   1034   1038   Ljava/lang/IllegalStateException;
        //  1026   1058   1061   1065   Ljava/lang/IllegalStateException;
        //  1088   1126   1129   1133   Ljava/lang/IllegalStateException;
        //  1168   1179   1182   1186   Ljava/lang/IllegalStateException;
        //  1174   1194   1194   1198   Ljava/lang/IllegalStateException;
        //  1261   1277   1280   1284   Ljava/lang/IllegalStateException;
        //  1269   1289   1292   1296   Ljava/lang/IllegalStateException;
        //  1284   1303   1306   1310   Ljava/lang/IllegalStateException;
        //  1296   1323   1326   1330   Ljava/lang/IllegalStateException;
        //  1345   1361   1361   1365   Ljava/lang/IllegalStateException;
        //  1389   1408   1411   1415   Ljava/lang/IllegalStateException;
        //  1415   1425   1428   1432   Ljava/lang/IllegalStateException;
        //  1420   1441   1444   1448   Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0158:
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
    
    private static List<PsiElement> a(final PsiNamedElement psiNamedElement) {
        return (List<PsiElement>)ContainerUtil.mapNotNull(ReferencesSearch.search((PsiElement)psiNamedElement, psiNamedElement.getUseScope()).findAll(), psiReference -> psiReference.getElement());
    }
    
    private static void a(final OCBlockStatement ocBlockStatement, final List<PsiElement> list, final Set<PsiElement> set) {
        ocBlockStatement.accept((PsiElementVisitor)new OCVisitor() {
            final /* synthetic */ Stack val$blocksStack = new Stack();
            
            public void visitElement(final PsiElement psiElement) {
                psiElement.acceptChildren((PsiElementVisitor)this);
                list.add(psiElement);
                if (!this.val$blocksStack.isEmpty()) {
                    set.add(psiElement);
                }
            }
            
            @Override
            public void visitBlockExpression(final OCBlockExpression ocBlockExpression) {
                this.val$blocksStack.push((Object)ocBlockExpression);
                ocBlockExpression.acceptChildren((PsiElementVisitor)this);
                this.val$blocksStack.pop();
            }
        });
    }
    
    @Nullable
    private static OCExpression a(final PsiElement psiElement) {
        OCExpression ocExpression = (OCExpression)PsiTreeUtil.getParentOfType(psiElement, (Class)OCExpression.class, false);
        if (ocExpression instanceof OCReferenceExpression) {
            ocExpression = (OCExpression)PsiTreeUtil.getParentOfType((PsiElement)ocExpression, (Class)OCCallExpression.class);
        }
        return ocExpression;
    }
    
    private static void a(final PsiNamedElement psiNamedElement, final String s, final List<PsiElement> list) {
        try {
            if (s.equals(psiNamedElement.getName())) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        for (final PsiElement psiElement : list) {
            Label_0068: {
                try {
                    if (!(psiElement instanceof OCReferenceElement)) {
                        continue;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = OCElementUtil.isPartOfMacroSubstitution(psiElement2);
                    if (!b) {
                        break Label_0068;
                    }
                    continue;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = OCElementUtil.isPartOfMacroSubstitution(psiElement2);
                    if (b) {
                        continue;
                    }
                    ((OCReferenceElement)psiElement).setNameOfIdentifier(s);
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
        }
        psiNamedElement.setName(s);
    }
    
    private static OCDeclarator a(final String s, final OCType ocType, @Nullable final OCExpression ocExpression, final PsiElement psiElement, final PsiElement psiElement2) {
        return OCChangeUtil.addBefore(psiElement, OCElementFactory.declarationStatement(OCNameSuggester.suggestUniqueName(OCSymbolKind.LOCAL_VARIABLE, s, psiElement), ocType, ocExpression, psiElement), psiElement2).getDeclaration().getDeclarators().get(0);
    }
    
    @Nullable
    private static OCExpression a(final OCExpression ocExpression) {
        try {
            if (ocExpression instanceof OCSendMessageExpression) {
                return ((OCSendMessageExpression)ocExpression).getReceiverExpression();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocExpression instanceof OCQualifiedExpression) {
                return ((OCQualifiedExpression)ocExpression).getQualifier();
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    private static List<OCExpression> b(final OCExpression ocExpression) {
        try {
            if (ocExpression instanceof OCCallExpression) {
                return ((OCCallExpression)ocExpression).getArguments();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocExpression instanceof OCSendMessageExpression) {
                return ((OCSendMessageExpression)ocExpression).getArgumentExpressions();
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        if (ocExpression instanceof OCQualifiedExpression) {
            final OCExpression topmostParenthesized = OCParenthesesUtils.topmostParenthesized(ocExpression);
            final PsiElement parent = topmostParenthesized.getParent();
            Label_0088: {
                try {
                    if (!(parent instanceof OCAssignmentExpression)) {
                        return Collections.emptyList();
                    }
                    final PsiElement psiElement = parent;
                    final OCAssignmentExpression ocAssignmentExpression = (OCAssignmentExpression)psiElement;
                    final OCExpression ocExpression2 = ocAssignmentExpression.getReceiverExpression();
                    final OCExpression ocExpression3 = topmostParenthesized;
                    if (ocExpression2 == ocExpression3) {
                        break Label_0088;
                    }
                    return Collections.emptyList();
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                try {
                    final PsiElement psiElement = parent;
                    final OCAssignmentExpression ocAssignmentExpression = (OCAssignmentExpression)psiElement;
                    final OCExpression ocExpression2 = ocAssignmentExpression.getReceiverExpression();
                    final OCExpression ocExpression3 = topmostParenthesized;
                    if (ocExpression2 == ocExpression3) {
                        return Collections.singletonList(((OCAssignmentExpression)parent).getSourceExpression());
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            return Collections.emptyList();
        }
        try {
            assert false : ocExpression.getClass();
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        return null;
    }
    
    @Override
    protected void deleteElement(final OCCallable ocCallable, final PsiElement psiElement) {
        final Iterator<OCCallable> iterator = b(ocCallable).iterator();
        while (iterator.hasNext()) {
            OCChangeUtil.delete((PsiElement)iterator.next());
        }
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.inlineMethod";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/inline/OCInlineMethodHandler", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected List<OCCallable> getElementsToWrite(final OCCallable ocCallable) {
        return b(ocCallable);
    }
    
    private static List<OCCallable> b(final OCCallable ocCallable) {
        final ArrayList<OCCallable<OCSymbol>> list = (ArrayList<OCCallable<OCSymbol>>)new ArrayList<OCCallable>();
        final OCSymbol symbol = ocCallable.getSymbol();
        try {
            if (symbol != null) {
                symbol.processSameSymbols(ocSymbol -> {
                    PsiElement locateDefinition = null;
                    Label_0018: {
                        try {
                            if (ocSymbol != null) {
                                locateDefinition = ocSymbol.locateDefinition();
                                break Label_0018;
                            }
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        locateDefinition = null;
                    }
                    final PsiElement psiElement = locateDefinition;
                    try {
                        if (psiElement instanceof OCCallable) {
                            list.add(psiElement);
                            return true;
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    Label_0067: {
                        try {
                            if (psiElement == null) {
                                return true;
                            }
                            final PsiElement psiElement2 = psiElement;
                            final PsiElement psiElement3 = psiElement2.getParent();
                            final boolean b = psiElement3 instanceof OCCallable;
                            if (b) {
                                break Label_0067;
                            }
                            return true;
                        }
                        catch (IllegalStateException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final PsiElement psiElement2 = psiElement;
                            final PsiElement psiElement3 = psiElement2.getParent();
                            final boolean b = psiElement3 instanceof OCCallable;
                            if (b) {
                                list.add(psiElement.getParent());
                            }
                        }
                        catch (IllegalStateException ex4) {
                            throw a(ex4);
                        }
                    }
                    return true;
                });
                return (List<OCCallable>)list;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        list.add(ocCallable);
        return (List<OCCallable>)list;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCInlineMethodHandler.class.desiredAssertionStatus()) {
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
