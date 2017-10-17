// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.util.OCElementsRange;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.containers.MultiMap;

public class OCUnreachableCodeFinder extends OCDataFlowAlgorithm
{
    private boolean myExitNodeReached;
    private boolean myDeadEndReached;
    private MultiMap<OCInstruction.InstructionKind, OCInstruction> myInstructionsByType;
    
    public OCUnreachableCodeFinder(@NotNull final OCControlFlowGraph ocControlFlowGraph) {
        if (ocControlFlowGraph == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cfg", "com/jetbrains/cidr/lang/dfa/OCUnreachableCodeFinder", "<init>"));
        }
        super(ocControlFlowGraph);
    }
    
    @Override
    public void process() {
        this.myDeadEndReached = false;
        this.myInstructionsByType = (MultiMap<OCInstruction.InstructionKind, OCInstruction>)new MultiMap();
        this.clearProcessedNodes();
        this.traverse(this.myCfg.getStartNode(), null, null, true);
    }
    
    @Override
    protected void processDeadEnd(@NotNull final OCNode ocNode) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCUnreachableCodeFinder", "processDeadEnd"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.myDeadEndReached = true;
    }
    
    public boolean isDeadEndReached() {
        return this.myDeadEndReached;
    }
    
    public boolean isExitNodeReached() {
        return this.myExitNodeReached;
    }
    
    @NotNull
    public List<OCElementsRange> getUnreachableRanges(final boolean b) {
        final ArrayList<OCNode> list = new ArrayList<OCNode>();
        for (final OCNode ocNode : this.myCfg.getNodes()) {
            Label_0066: {
                try {
                    if (this.isNodeProcessed(ocNode, null)) {
                        continue;
                    }
                    final OCNode ocNode2 = ocNode;
                    final boolean b2 = ocNode2.isFake();
                    if (!b2) {
                        break Label_0066;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final OCNode ocNode2 = ocNode;
                    final boolean b2 = ocNode2.isFake();
                    if (b2) {
                        continue;
                    }
                    list.add(ocNode);
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
        }
        List<OCElementsRange> ranges;
        try {
            ranges = OCDataFlowAlgorithm.getRanges(list, b);
            if (ranges == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCUnreachableCodeFinder", "getUnreachableRanges"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return ranges;
    }
    
    @Override
    protected void nodeProcessed(@NotNull final OCNode ocNode) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCUnreachableCodeFinder", "nodeProcessed"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (this.myCfg.getExitNodes().contains(ocNode)) {
                this.myExitNodeReached = true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        if (ocNode.getInstructions() != null) {
            for (final OCInstruction ocInstruction : ocNode.getInstructions()) {
                this.myInstructionsByType.putValue((Object)ocInstruction.getKind(), (Object)ocInstruction);
            }
        }
    }
    
    @NotNull
    public Collection<OCInstruction> getReachableInstructions(@NotNull final OCInstruction.InstructionKind instructionKind) {
        try {
            if (instructionKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/dfa/OCUnreachableCodeFinder", "getReachableInstructions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Collection value;
        try {
            value = this.myInstructionsByType.get((Object)instructionKind);
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCUnreachableCodeFinder", "getReachableInstructions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return (Collection<OCInstruction>)value;
    }
    
    public void clearInstructions() {
        this.myInstructionsByType.clear();
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
