// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.intellij.psi.PsiElement;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public class OCSingleSymbolAlgorithm extends OCDataFlowAlgorithm
{
    protected boolean myIsForward;
    protected OCSymbol mySymbol;
    
    protected OCSingleSymbolAlgorithm(@NotNull final OCControlFlowGraph ocControlFlowGraph, final boolean myIsForward, @NotNull final OCSymbol mySymbol) {
        if (ocControlFlowGraph == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cfg", "com/jetbrains/cidr/lang/dfa/OCSingleSymbolAlgorithm", "<init>"));
        }
        if (mySymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCSingleSymbolAlgorithm", "<init>"));
        }
        super(ocControlFlowGraph);
        this.myIsForward = myIsForward;
        this.mySymbol = mySymbol;
    }
    
    @Override
    public void process() {
        this.clearProcessedNodes();
        final Iterator<OCInstruction> iterator = this.getStartInstructions().iterator();
        while (iterator.hasNext()) {
            this.traverse(iterator.next(), this.mySymbol, this.myIsForward);
        }
    }
    
    @NotNull
    @Override
    protected Collection<OCInstruction> getStartInstructions() {
        final ArrayList<OCInstruction> list = new ArrayList<OCInstruction>();
        for (final OCInstruction ocInstruction : this.myCfg.getAllInstructions()) {
            Label_0072: {
                try {
                    if (ocInstruction.getSymbolOffset() != this.mySymbol.getComplexOffset()) {
                        continue;
                    }
                    final OCSingleSymbolAlgorithm ocSingleSymbolAlgorithm = this;
                    final OCInstruction ocInstruction2 = ocInstruction;
                    final boolean b = ocSingleSymbolAlgorithm.isStartInstruction(ocInstruction2);
                    if (b) {
                        break Label_0072;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final OCSingleSymbolAlgorithm ocSingleSymbolAlgorithm = this;
                    final OCInstruction ocInstruction2 = ocInstruction;
                    final boolean b = ocSingleSymbolAlgorithm.isStartInstruction(ocInstruction2);
                    if (!b) {
                        continue;
                    }
                    list.add(ocInstruction);
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
        }
        ArrayList<OCInstruction> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCSingleSymbolAlgorithm", "getStartInstructions"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return list2;
    }
    
    @NotNull
    protected List<PsiElement> getReachableElements() {
        List<PsiElement> reachableElements;
        try {
            reachableElements = this.getReachableElements(this.myIsForward, this.mySymbol, false);
            if (reachableElements == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCSingleSymbolAlgorithm", "getReachableElements"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return reachableElements;
    }
    
    @NotNull
    protected List<PsiElement> getNonReachableElements() {
        List<PsiElement> reachableElements;
        try {
            reachableElements = this.getReachableElements(this.myIsForward, this.mySymbol, true);
            if (reachableElements == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCSingleSymbolAlgorithm", "getNonReachableElements"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return reachableElements;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
