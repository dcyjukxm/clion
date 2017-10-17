// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.jetbrains.cidr.lang.util.OCElementsRange;
import com.intellij.util.containers.MultiMap;
import com.intellij.psi.PsiElement;
import com.intellij.util.containers.ContainerUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;

public abstract class OCDataFlowAlgorithm
{
    protected OCControlFlowGraph myCfg;
    protected boolean[] myProcessedNodes;
    
    protected OCDataFlowAlgorithm(final OCControlFlowGraph myCfg) {
        this.myCfg = myCfg;
        this.myProcessedNodes = new boolean[this.myCfg.getNumOfNodes()];
    }
    
    public abstract void process();
    
    protected void traverse(@NotNull final OCNode ocNode, @Nullable final OCSymbol ocSymbol, @Nullable final OCInstruction ocInstruction, final boolean b) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "traverse"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.isNodeProcessed(ocNode, ocSymbol)) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            this.markNodeAsProcessed(ocNode, ocSymbol);
            if (!this.processNode(ocNode, ocSymbol, b, null, ocInstruction)) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final List<OCNode> jumpTargets = this.getJumpTargets(ocNode, b);
        if (jumpTargets != null) {
            final Iterator<OCNode> iterator = jumpTargets.iterator();
            while (iterator.hasNext()) {
                this.traverse(iterator.next(), ocSymbol, ocInstruction, b);
            }
        }
        else {
            try {
                if (!this.myCfg.getExitNodes().contains(ocNode)) {
                    this.processDeadEnd(ocNode);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        this.nodeProcessed(ocNode);
    }
    
    protected void traverse(@NotNull final OCInstruction ocInstruction, @Nullable final OCSymbol ocSymbol, final boolean b) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "traverse"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCNode node = ocInstruction.getNode();
        try {
            if (!this.processNode(node, ocSymbol, b, ocInstruction, null)) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final List<OCNode> jumpTargets = this.getJumpTargets(node, b);
        if (jumpTargets != null) {
            final Iterator<OCNode> iterator = jumpTargets.iterator();
            while (iterator.hasNext()) {
                this.traverse(iterator.next(), ocSymbol, ocInstruction, b);
            }
        }
    }
    
    protected void traverseFromStart(@Nullable final OCSymbol ocSymbol) {
        this.traverse(this.myCfg.getStartNode(), ocSymbol, null, true);
    }
    
    @NotNull
    protected Collection<OCInstruction> getStartInstructions() {
        final ArrayList<OCInstruction> list = new ArrayList<OCInstruction>();
        for (final OCInstruction ocInstruction : this.myCfg.getAllInstructions()) {
            try {
                if (!this.isStartInstruction(ocInstruction)) {
                    continue;
                }
                list.add(ocInstruction);
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        ArrayList<OCInstruction> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "getStartInstructions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return list2;
    }
    
    protected boolean acceptsInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "acceptsInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocInstruction.getKind() != OCInstruction.InstructionKind.KILL) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    protected boolean isStartInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "isStartInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    protected boolean isEndInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "isEndInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    protected boolean processInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "processInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.acceptsInstruction(ocInstruction);
    }
    
    protected boolean processNode(@NotNull final OCNode ocNode, @Nullable final OCSymbol ocSymbol, final boolean b, @Nullable final OCInstruction ocInstruction, @Nullable final OCInstruction ocInstruction2) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "processNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<OCInstruction> instructions = ocNode.getInstructions();
        boolean b2 = false;
        Label_0086: {
            Label_0072: {
                try {
                    if (instructions == null) {
                        return true;
                    }
                    final OCSymbol ocSymbol2 = ocSymbol;
                    if (ocSymbol2 == null) {
                        return true;
                    }
                    break Label_0072;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCSymbol ocSymbol2 = ocSymbol;
                    if (ocSymbol2 == null) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    if (ocInstruction == null) {
                        b2 = true;
                        break Label_0086;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            b2 = false;
        }
        boolean b3 = b2;
        List<OCInstruction> reverse = null;
        Label_0106: {
            try {
                if (b) {
                    reverse = instructions;
                    break Label_0106;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            reverse = (List<OCInstruction>)ContainerUtil.reverse((List)instructions);
        }
        for (final OCInstruction ocInstruction3 : reverse) {
            Label_0197: {
                Label_0170: {
                    try {
                        if (ocInstruction3.getSymbolOffset() != ocSymbol.getComplexOffset()) {
                            break Label_0197;
                        }
                        if (ocInstruction != ocInstruction3) {
                            break Label_0170;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                    b3 = true;
                    break Label_0197;
                    try {
                        if (!b3) {
                            break Label_0197;
                        }
                        final OCDataFlowAlgorithm ocDataFlowAlgorithm = this;
                        final OCInstruction ocInstruction4 = ocInstruction3;
                        final boolean b4 = ocDataFlowAlgorithm.processInstruction(ocInstruction4);
                        if (!b4) {
                            return false;
                        }
                        break Label_0197;
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                }
                try {
                    final OCDataFlowAlgorithm ocDataFlowAlgorithm = this;
                    final OCInstruction ocInstruction4 = ocInstruction3;
                    final boolean b4 = ocDataFlowAlgorithm.processInstruction(ocInstruction4);
                    if (!b4) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
                try {
                    if (ocInstruction3 == ocInstruction2) {
                        break;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
            }
        }
        return true;
    }
    
    protected boolean isProcessed(@NotNull final OCInstruction ocInstruction, final boolean b) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "isProcessed"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCNode node = ocInstruction.getNode();
        final List<OCInstruction> instructions = node.getInstructions();
        try {
            if (instructions == null) {
                return this.isNodeProcessed(node, ocInstruction.getSymbol());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        boolean b2 = false;
        List<OCInstruction> reverse = null;
        Label_0095: {
            try {
                if (b) {
                    reverse = (List<OCInstruction>)ContainerUtil.reverse((List)instructions);
                    break Label_0095;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            reverse = instructions;
        }
        for (final OCInstruction ocInstruction2 : reverse) {
            Label_0183: {
                Label_0156: {
                    try {
                        if (ocInstruction2.getSymbolOffset() != ocInstruction.getSymbolOffset()) {
                            continue;
                        }
                        if (ocInstruction2 != ocInstruction) {
                            break Label_0156;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    b2 = true;
                    continue;
                    try {
                        if (!b2) {
                            continue;
                        }
                        final OCDataFlowAlgorithm ocDataFlowAlgorithm = this;
                        final OCInstruction ocInstruction3 = ocInstruction2;
                        final boolean b3 = ocDataFlowAlgorithm.isStartInstruction(ocInstruction3);
                        if (b3) {
                            return true;
                        }
                        break Label_0183;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                try {
                    final OCDataFlowAlgorithm ocDataFlowAlgorithm = this;
                    final OCInstruction ocInstruction3 = ocInstruction2;
                    final boolean b3 = ocDataFlowAlgorithm.isStartInstruction(ocInstruction3);
                    if (b3) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                try {
                    if (!this.acceptsInstruction(ocInstruction2)) {
                        return false;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
        }
        return this.isNodeProcessed(node, ocInstruction.getSymbol());
    }
    
    @NotNull
    protected List<PsiElement> getReachableElements(final boolean b, @NotNull final OCSymbol ocSymbol, final boolean b2) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "getReachableElements"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ArrayList<PsiElement> list = new ArrayList<PsiElement>();
        final MultiMap<OCInstruction.InstructionKind, OCInstruction> instructions = this.myCfg.getInstructions(ocSymbol);
        Label_0116: {
            ArrayList<PsiElement> list2 = null;
            Label_0081: {
                try {
                    if (instructions != null) {
                        break Label_0116;
                    }
                    list2 = list;
                    if (list2 == null) {
                        break Label_0081;
                    }
                    return list2;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    list2 = list;
                    if (list2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "getReachableElements"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return list2;
        }
        for (final OCInstruction ocInstruction : instructions.values()) {
            try {
                if (!this.isEndInstruction(ocInstruction) || !(b2 ^ this.isProcessed(ocInstruction, b))) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            final PsiElement elementFromInstruction = this.getElementFromInstruction(ocInstruction);
            try {
                if (elementFromInstruction == null) {
                    continue;
                }
                list.add(elementFromInstruction);
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        ArrayList<PsiElement> list3;
        try {
            list3 = list;
            if (list3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "getReachableElements"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return list3;
    }
    
    @Nullable
    protected PsiElement getElementFromInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "getElementFromInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocInstruction.getRValue();
    }
    
    @Nullable
    protected List<OCNode> getJumpTargets(@NotNull final OCNode ocNode, final boolean b) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "getJumpTargets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (b) {
                return ocNode.getJumpTargets();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ocNode.getJumpSources();
    }
    
    protected void nodeProcessed(@NotNull final OCNode ocNode) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "nodeProcessed"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    protected void processDeadEnd(@NotNull final OCNode ocNode) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "processDeadEnd"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    protected boolean isNodeProcessed(@NotNull final OCNode ocNode, @Nullable final OCSymbol ocSymbol) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "isNodeProcessed"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myProcessedNodes[ocNode.getIndex()];
    }
    
    protected void markNodeAsProcessed(@NotNull final OCNode ocNode, @Nullable final OCSymbol ocSymbol) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "markNodeAsProcessed"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myProcessedNodes[ocNode.getIndex()] = true;
    }
    
    protected void clearProcessedNodes() {
        int i = 0;
        try {
            while (i < this.myCfg.getNumOfNodes()) {
                this.myProcessedNodes[i] = false;
                ++i;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    protected static List<OCElementsRange> getRanges(@NotNull final List<OCNode> list, final boolean b) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nodes", "com/jetbrains/cidr/lang/dfa/OCDataFlowAlgorithm", "getRanges"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List mapNotNull = ContainerUtil.mapNotNull((Collection)list, ocNode -> ocNode.getRange());
        try {
            if (b) {
                return OCElementsRange.mergeRanges(mapNotNull);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (List<OCElementsRange>)mapNotNull;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
