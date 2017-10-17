// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import java.util.ArrayList;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Pair;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Map;

public abstract class OCMultiSymbolAlgorithm extends OCDataFlowAlgorithm
{
    private Map<OCSymbol, boolean[]> myVisitedNodes;
    private Stack<OCInstruction> myStack;
    private Set<OCInstruction> myGoodReads;
    private Set<OCSymbol> myReferencedSymbols;
    
    protected OCMultiSymbolAlgorithm(final OCControlFlowGraph ocControlFlowGraph) {
        super(ocControlFlowGraph);
        this.myVisitedNodes = new HashMap<OCSymbol, boolean[]>();
        this.myStack = new Stack<OCInstruction>();
        this.myGoodReads = new HashSet<OCInstruction>();
        this.myReferencedSymbols = new HashSet<OCSymbol>();
    }
    
    @Override
    public void process() {
        this.myStack.addAll((Collection<?>)this.getStartInstructions());
        if (this.processClosureSymbols()) {
            for (final OCSymbol ocSymbol : this.myCfg.getClosureSymbols()) {
                try {
                    if (!this.myCfg.hasInstructionsInParentGraph(ocSymbol, OCInstruction.InstructionKind.WRITE, OCInstruction.InstructionKind.WRITE_IN_BLOCK, OCInstruction.InstructionKind.REFERENCE)) {
                        continue;
                    }
                    this.traverseFromStart(ocSymbol);
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
            }
        }
        while (!this.myStack.isEmpty()) {
            final OCInstruction ocInstruction = this.myStack.pop();
            this.traverse(ocInstruction, ocInstruction.getSymbol(), true);
        }
    }
    
    @Override
    protected boolean acceptsInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCMultiSymbolAlgorithm", "acceptsInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (this.myReferencedSymbols.contains(ocInstruction.getSymbol())) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            switch (ocInstruction.getKind()) {
                case KILL: {
                    return false;
                }
                case WRITE: {
                    break;
                }
                default: {
                    return true;
                }
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return this.isStartInstruction(ocInstruction);
    }
    
    @Override
    protected boolean isStartInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCMultiSymbolAlgorithm", "isStartInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            switch (ocInstruction.getKind()) {
                case REFERENCE:
                case WRITE_IN_BLOCK: {
                    return this.treatReferencesAsStartInstructions();
                }
                case WRITE: {
                    break;
                }
                default: {
                    return false;
                }
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final OCInstruction associatedInstruction = ocInstruction.getAssociatedInstruction();
        Label_0120: {
            try {
                if (associatedInstruction == null) {
                    return this.isGoodWrite(ocInstruction.getRValue());
                }
                final OCInstruction ocInstruction2 = associatedInstruction;
                final OCInstruction.InstructionKind instructionKind = ocInstruction2.getKind();
                final OCInstruction.InstructionKind instructionKind2 = OCInstruction.InstructionKind.READ;
                if (instructionKind == instructionKind2) {
                    break Label_0120;
                }
                return this.isGoodWrite(ocInstruction.getRValue());
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final OCInstruction ocInstruction2 = associatedInstruction;
                final OCInstruction.InstructionKind instructionKind = ocInstruction2.getKind();
                final OCInstruction.InstructionKind instructionKind2 = OCInstruction.InstructionKind.READ;
                if (instructionKind == instructionKind2) {
                    return this.isGoodRead(associatedInstruction);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return this.isGoodWrite(ocInstruction.getRValue());
    }
    
    protected boolean isGoodRead(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCMultiSymbolAlgorithm", "isGoodRead"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.myGoodReads.contains(ocInstruction);
    }
    
    @Override
    protected boolean isEndInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCMultiSymbolAlgorithm", "isEndInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocInstruction.getKind() == OCInstruction.InstructionKind.READ) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    protected boolean treatReferencesAsStartInstructions() {
        return true;
    }
    
    @Override
    protected boolean processInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCMultiSymbolAlgorithm", "processInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        if (ocInstruction.getKind() == OCInstruction.InstructionKind.READ) {
            final OCInstruction associatedInstruction = ocInstruction.getAssociatedInstruction();
            Label_0080: {
                try {
                    if (associatedInstruction == null) {
                        return this.acceptsInstruction(ocInstruction);
                    }
                    final OCInstruction ocInstruction2 = associatedInstruction;
                    final OCInstruction.InstructionKind instructionKind = ocInstruction2.getKind();
                    final OCInstruction.InstructionKind instructionKind2 = OCInstruction.InstructionKind.WRITE;
                    if (instructionKind == instructionKind2) {
                        break Label_0080;
                    }
                    return this.acceptsInstruction(ocInstruction);
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                try {
                    final OCInstruction ocInstruction2 = associatedInstruction;
                    final OCInstruction.InstructionKind instructionKind = ocInstruction2.getKind();
                    final OCInstruction.InstructionKind instructionKind2 = OCInstruction.InstructionKind.WRITE;
                    if (instructionKind == instructionKind2) {
                        this.myGoodReads.add(ocInstruction);
                        this.myStack.push(associatedInstruction);
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
            }
        }
        else {
            Label_0137: {
                try {
                    if (ocInstruction.getKind() == OCInstruction.InstructionKind.WRITE_IN_BLOCK) {
                        break Label_0137;
                    }
                    final OCInstruction ocInstruction3 = ocInstruction;
                    final OCInstruction.InstructionKind instructionKind3 = ocInstruction3.getKind();
                    final OCInstruction.InstructionKind instructionKind4 = OCInstruction.InstructionKind.REFERENCE;
                    if (instructionKind3 == instructionKind4) {
                        break Label_0137;
                    }
                    return this.acceptsInstruction(ocInstruction);
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                try {
                    final OCInstruction ocInstruction3 = ocInstruction;
                    final OCInstruction.InstructionKind instructionKind3 = ocInstruction3.getKind();
                    final OCInstruction.InstructionKind instructionKind4 = OCInstruction.InstructionKind.REFERENCE;
                    if (instructionKind3 == instructionKind4) {
                        this.myReferencedSymbols.add(ocInstruction.getSymbol());
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
            }
        }
        return this.acceptsInstruction(ocInstruction);
    }
    
    protected boolean processClosureSymbols() {
        return true;
    }
    
    @Override
    protected boolean isNodeProcessed(@NotNull final OCNode ocNode, @Nullable final OCSymbol ocSymbol) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCMultiSymbolAlgorithm", "isNodeProcessed"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        boolean[] array = null;
        Label_0069: {
            try {
                if (ocSymbol != null) {
                    array = this.myVisitedNodes.get(ocSymbol);
                    break Label_0069;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            array = null;
        }
        final boolean[] array2 = array;
        Label_0090: {
            try {
                if (array2 == null) {
                    return false;
                }
                final boolean[] array3 = array2;
                final OCNode ocNode2 = ocNode;
                final int n = ocNode2.getIndex();
                final boolean b = array3[n];
                if (b) {
                    break Label_0090;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final boolean[] array3 = array2;
                final OCNode ocNode2 = ocNode;
                final int n = ocNode2.getIndex();
                final boolean b = array3[n];
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return false;
    }
    
    @Override
    protected void markNodeAsProcessed(@NotNull final OCNode ocNode, @Nullable final OCSymbol ocSymbol) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCMultiSymbolAlgorithm", "markNodeAsProcessed"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        if (ocSymbol != null) {
            boolean[] array = this.myVisitedNodes.get(ocSymbol);
            if (array == null) {
                array = new boolean[this.myCfg.getNumOfNodes()];
                this.myVisitedNodes.put(ocSymbol, array);
            }
            array[ocNode.getIndex()] = true;
        }
    }
    
    @NotNull
    protected List<Pair<OCSymbol, PsiElement>> getReachableElements(final boolean b) {
        final ArrayList<Pair<OCSymbol, PsiElement>> list = new ArrayList<Pair<OCSymbol, PsiElement>>();
        for (final OCSymbol ocSymbol : this.myCfg.getLocalSymbols()) {
            final Iterator<PsiElement> iterator2 = this.getReachableElements(true, ocSymbol, b).iterator();
            while (iterator2.hasNext()) {
                list.add((Pair<OCSymbol, PsiElement>)Pair.create((Object)ocSymbol, (Object)iterator2.next()));
            }
        }
        if (this.processClosureSymbols()) {
            for (final OCSymbol ocSymbol2 : this.myCfg.getClosureSymbols()) {
                final Iterator<PsiElement> iterator4 = this.getReachableElements(true, ocSymbol2, b).iterator();
                while (iterator4.hasNext()) {
                    list.add((Pair<OCSymbol, PsiElement>)Pair.create((Object)ocSymbol2, (Object)iterator4.next()));
                }
            }
        }
        ArrayList<Pair<OCSymbol, PsiElement>> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCMultiSymbolAlgorithm", "getReachableElements"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return list2;
    }
    
    @NotNull
    protected List<Pair<OCSymbol, PsiElement>> getReachableElements() {
        List<Pair<OCSymbol, PsiElement>> reachableElements;
        try {
            reachableElements = this.getReachableElements(false);
            if (reachableElements == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCMultiSymbolAlgorithm", "getReachableElements"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return reachableElements;
    }
    
    protected abstract boolean isGoodWrite(@Nullable final PsiElement p0);
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
