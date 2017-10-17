// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import java.util.Collections;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;

class OCNotInitializedVarChecker extends OCSingleSymbolAlgorithm
{
    private boolean myWasInitialized;
    
    protected OCNotInitializedVarChecker(@NotNull final OCControlFlowGraph ocControlFlowGraph, @NotNull final OCSymbol ocSymbol) {
        if (ocControlFlowGraph == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cfg", "com/jetbrains/cidr/lang/dfa/OCNotInitializedVarChecker", "<init>"));
        }
        if (ocSymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCNotInitializedVarChecker", "<init>"));
        }
        super(ocControlFlowGraph, true, ocSymbol);
    }
    
    @Override
    public void process() {
        this.clearProcessedNodes();
        final OCControlFlowGraph closureVariableDeclaratorGraph = this.myCfg.getClosureVariableDeclaratorGraph(this.mySymbol);
        Label_0073: {
            Label_0063: {
                try {
                    if (closureVariableDeclaratorGraph == null) {
                        break Label_0073;
                    }
                    final OCNotInitializedVarChecker ocNotInitializedVarChecker = this;
                    final OCControlFlowGraph ocControlFlowGraph = ocNotInitializedVarChecker.myCfg;
                    final OCNotInitializedVarChecker ocNotInitializedVarChecker2 = this;
                    final OCSymbol ocSymbol = ocNotInitializedVarChecker2.mySymbol;
                    final int n = 3;
                    final OCInstruction.InstructionKind[] array = new OCInstruction.InstructionKind[n];
                    final int n2 = 0;
                    final OCInstruction.InstructionKind instructionKind = OCInstruction.InstructionKind.WRITE;
                    array[n2] = instructionKind;
                    final int n3 = 1;
                    final OCInstruction.InstructionKind instructionKind2 = OCInstruction.InstructionKind.WRITE_IN_BLOCK;
                    array[n3] = instructionKind2;
                    final int n4 = 2;
                    final OCInstruction.InstructionKind instructionKind3 = OCInstruction.InstructionKind.REFERENCE;
                    array[n4] = instructionKind3;
                    final boolean b = ocControlFlowGraph.hasInstructionsInParentGraph(ocSymbol, array);
                    if (b) {
                        break Label_0063;
                    }
                    break Label_0073;
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    final OCNotInitializedVarChecker ocNotInitializedVarChecker = this;
                    final OCControlFlowGraph ocControlFlowGraph = ocNotInitializedVarChecker.myCfg;
                    final OCNotInitializedVarChecker ocNotInitializedVarChecker2 = this;
                    final OCSymbol ocSymbol = ocNotInitializedVarChecker2.mySymbol;
                    final int n = 3;
                    final OCInstruction.InstructionKind[] array = new OCInstruction.InstructionKind[n];
                    final int n2 = 0;
                    final OCInstruction.InstructionKind instructionKind = OCInstruction.InstructionKind.WRITE;
                    array[n2] = instructionKind;
                    final int n3 = 1;
                    final OCInstruction.InstructionKind instructionKind2 = OCInstruction.InstructionKind.WRITE_IN_BLOCK;
                    array[n3] = instructionKind2;
                    final int n4 = 2;
                    final OCInstruction.InstructionKind instructionKind3 = OCInstruction.InstructionKind.REFERENCE;
                    array[n4] = instructionKind3;
                    final boolean b = ocControlFlowGraph.hasInstructionsInParentGraph(ocSymbol, array);
                    if (b) {
                        this.myWasInitialized = true;
                        return;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
            }
            try {
                if (closureVariableDeclaratorGraph.getInstructions(this.mySymbol) != null) {
                    this.traverseFromStart(this.mySymbol);
                }
                return;
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        this.traverse(this.myCfg.getDeclaratorInstruction(this.mySymbol), this.mySymbol, true);
    }
    
    @Override
    protected boolean acceptsInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCNotInitializedVarChecker", "acceptsInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            switch (ocInstruction.getKind()) {
                case KILL:
                case WRITE:
                case WRITE_IN_BLOCK:
                case REFERENCE: {
                    return false;
                }
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return true;
    }
    
    @Override
    protected boolean isStartInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCNotInitializedVarChecker", "isStartInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (ocInstruction.getKind() == OCInstruction.InstructionKind.DECLARATOR) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return false;
    }
    
    @Override
    protected boolean isEndInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCNotInitializedVarChecker", "isEndInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        Label_0068: {
            try {
                if (ocInstruction.getKind() != OCInstruction.InstructionKind.READ) {
                    return false;
                }
                final OCInstruction ocInstruction2 = ocInstruction;
                final boolean b = ocInstruction2.isTransparentRead();
                if (!b) {
                    break Label_0068;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            try {
                final OCInstruction ocInstruction2 = ocInstruction;
                final boolean b = ocInstruction2.isTransparentRead();
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return false;
    }
    
    @Nullable
    @Override
    protected List<OCNode> getJumpTargets(@NotNull final OCNode ocNode, final boolean b) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCNotInitializedVarChecker", "getJumpTargets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final List<OCNode> jumpTargets = super.getJumpTargets(ocNode, b);
        final List<OCNode> fakeJumpTargets = ocNode.getFakeJumpTargets();
        try {
            if (jumpTargets == null || fakeJumpTargets == null) {
                return jumpTargets;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        final ArrayList list = new ArrayList<OCNode>(jumpTargets);
        list.removeAll(fakeJumpTargets);
        return (List<OCNode>)list;
    }
    
    @NotNull
    public List<PsiElement> getNotInitializedReads() {
        List<PsiElement> reachableElements = null;
        Label_0056: {
            List<PsiElement> list = null;
            Label_0021: {
                try {
                    if (!this.myWasInitialized) {
                        break Label_0056;
                    }
                    list = Collections.emptyList();
                    if (list == null) {
                        break Label_0021;
                    }
                    return list;
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    list = Collections.emptyList();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCNotInitializedVarChecker", "getNotInitializedReads"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
            }
            return list;
            try {
                reachableElements = this.getReachableElements();
                if (reachableElements == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCNotInitializedVarChecker", "getNotInitializedReads"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return reachableElements;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
