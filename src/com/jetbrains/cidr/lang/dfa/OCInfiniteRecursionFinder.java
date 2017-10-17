// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import java.util.Iterator;
import com.jetbrains.cidr.lang.util.OCElementsRange;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCCallable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import java.util.List;

public class OCInfiniteRecursionFinder extends OCDataFlowAlgorithm
{
    private boolean myExitOrDeadEndReached;
    private List<PsiElement> myRecursiveCalls;
    
    protected OCInfiniteRecursionFinder(@NotNull final OCControlFlowGraph ocControlFlowGraph) {
        if (ocControlFlowGraph == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cfg", "com/jetbrains/cidr/lang/dfa/OCInfiniteRecursionFinder", "<init>"));
        }
        super(ocControlFlowGraph);
    }
    
    @Override
    public void process() {
        this.myExitOrDeadEndReached = true;
        final PsiElement codeFragment = this.myCfg.getCodeFragment();
        try {
            if (!(codeFragment instanceof OCCallable)) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCSymbol symbol = ((OCCallable<OCSymbol>)codeFragment).getSymbol();
        try {
            if (symbol == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            this.myRecursiveCalls = new ArrayList<PsiElement>();
            codeFragment.accept((PsiElementVisitor)new OCRecursiveVisitor() {
                @Override
                public void visitCallExpression(final OCCallExpression ocCallExpression) {
                    super.visitCallExpression(ocCallExpression);
                    final OCExpression functionReferenceExpression = ocCallExpression.getFunctionReferenceExpression();
                    if (symbol instanceof OCFunctionSymbol) {
                        OCSymbol ocSymbol = null;
                        if (functionReferenceExpression instanceof OCReferenceExpression) {
                            ocSymbol = ((OCReferenceExpression)functionReferenceExpression).resolveToSymbol();
                        }
                        else if (functionReferenceExpression instanceof OCQualifiedExpression) {
                            final OCQualifiedExpression ocQualifiedExpression = (OCQualifiedExpression)functionReferenceExpression;
                            if (ocQualifiedExpression.getQualifier() instanceof OCReferenceExpression && ((OCReferenceExpression)ocQualifiedExpression.getQualifier()).isCppThis()) {
                                ocSymbol = ocQualifiedExpression.resolveToSymbol();
                            }
                        }
                        if (symbol.equals(ocSymbol) || (ocSymbol != null && symbol.equals(ocSymbol.getAssociatedSymbol()))) {
                            OCInfiniteRecursionFinder.this.myRecursiveCalls.add(ocCallExpression);
                        }
                    }
                }
                
                @Override
                public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
                    super.visitSendMessageExpression(ocSendMessageExpression);
                    final OCExpression receiverExpression = ocSendMessageExpression.getReceiverExpression();
                    if (symbol instanceof OCMethodSymbol && ocSendMessageExpression.getMessageSelector().equals(symbol.getName()) && (((OCMethodSymbol)symbol).isStatic() || (receiverExpression instanceof OCReferenceExpression && ((OCReferenceExpression)receiverExpression).getSelfSuperToken() == OCElementTypes.SelfSuperToken.SELF))) {
                        final OCMethodSymbol knownResponder = ocSendMessageExpression.getProbableResponders().getKnownResponder();
                        if (symbol.equals(knownResponder) || (knownResponder != null && symbol.equals(knownResponder.getAssociatedSymbol()))) {
                            OCInfiniteRecursionFinder.this.myRecursiveCalls.add(ocSendMessageExpression);
                        }
                    }
                }
                
                @Override
                public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
                    super.visitQualifiedExpression(ocQualifiedExpression);
                    final OCExpression qualifier = ocQualifiedExpression.getQualifier();
                    if (symbol instanceof OCMethodSymbol) {
                        final String symbolName = ocQualifiedExpression.getSymbolName();
                        final PsiReference reference = ocQualifiedExpression.getReference();
                        if ((symbolName.equals(symbol.getName()) || OCNameSuggester.getObjCSetterFromGetter(symbolName).equals(symbol.getName())) && (((OCMethodSymbol)symbol).isStatic() || (qualifier instanceof OCReferenceExpression && ((OCReferenceExpression)qualifier).getSelfSuperToken() == OCElementTypes.SelfSuperToken.SELF)) && reference != null && !ocQualifiedExpression.processTargets(symbolName, (Processor<OCSymbol>)(ocSymbol2 -> !ocSymbol.equals(ocSymbol2) || !reference.isReferenceTo(psiElement)), true, null, false, false, null)) {
                            OCInfiniteRecursionFinder.this.myRecursiveCalls.add(ocQualifiedExpression);
                        }
                    }
                }
                
                @Override
                public void visitBlockExpression(final OCBlockExpression ocBlockExpression) {
                }
            });
            if (this.myRecursiveCalls.isEmpty()) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        this.myExitOrDeadEndReached = false;
        this.clearProcessedNodes();
        this.traverse(this.myCfg.getStartNode(), null, null, true);
    }
    
    @NotNull
    public List<PsiElement> getRecursiveCalls() {
        List<PsiElement> myRecursiveCalls;
        try {
            myRecursiveCalls = this.myRecursiveCalls;
            if (myRecursiveCalls == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCInfiniteRecursionFinder", "getRecursiveCalls"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myRecursiveCalls;
    }
    
    public boolean isExitOrDeadEndReached() {
        return this.myExitOrDeadEndReached;
    }
    
    @Override
    protected boolean processNode(@NotNull final OCNode ocNode, @Nullable final OCSymbol ocSymbol, final boolean b, @Nullable final OCInstruction ocInstruction, @Nullable final OCInstruction ocInstruction2) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCInfiniteRecursionFinder", "processNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCElementsRange range = ocNode.getRange();
        if (range != null) {
            for (final PsiElement psiElement : this.myRecursiveCalls) {
                try {
                    if (range.getTextRange().contains(psiElement.getTextRange())) {
                        return false;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
        }
        try {
            if (this.myCfg.getExitNodes().contains(ocNode)) {
                this.myExitOrDeadEndReached = true;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return true;
    }
    
    @Override
    protected void processDeadEnd(@NotNull final OCNode ocNode) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCInfiniteRecursionFinder", "processDeadEnd"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.myExitOrDeadEndReached = true;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
