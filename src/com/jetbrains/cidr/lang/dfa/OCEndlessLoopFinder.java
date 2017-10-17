// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.jetbrains.cidr.lang.util.OCElementsRange;
import java.util.Iterator;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Stack;

class OCEndlessLoopFinder extends OCDataFlowAlgorithm
{
    private Stack<OCNode> myTopologicalOrder;
    private List<OCNode> myComponent;
    private List<OCNode> myEndlessLoops;
    private List<OCNode> myUnreachedNodes;
    private boolean myForwardMode;
    
    public OCEndlessLoopFinder(@NotNull final OCControlFlowGraph ocControlFlowGraph) {
        if (ocControlFlowGraph == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cfg", "com/jetbrains/cidr/lang/dfa/OCEndlessLoopFinder", "<init>"));
        }
        super(ocControlFlowGraph);
        this.myTopologicalOrder = new Stack<OCNode>();
        this.myComponent = new ArrayList<OCNode>();
        this.myEndlessLoops = new ArrayList<OCNode>();
        this.myUnreachedNodes = new ArrayList<OCNode>();
    }
    
    @Override
    public void process() {
        this.myEndlessLoops.clear();
        this.myTopologicalOrder.clear();
        this.myUnreachedNodes.clear();
        this.clearProcessedNodes();
        this.myForwardMode = true;
        this.traverse(this.myCfg.getStartNode(), null, null, true);
        for (final OCNode ocNode : this.myCfg.getNodes()) {
            try {
                if (this.isNodeProcessed(ocNode, null)) {
                    continue;
                }
                this.myUnreachedNodes.add(ocNode);
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
        }
        this.myForwardMode = false;
        this.clearProcessedNodes();
        while (!this.myTopologicalOrder.isEmpty()) {
            final OCNode ocNode2 = this.myTopologicalOrder.pop();
            try {
                if (this.isNodeProcessed(ocNode2, null)) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                this.myComponent.clear();
                this.traverse(ocNode2, null, null, false);
                if (!this.a(this.myComponent)) {
                    continue;
                }
                this.myEndlessLoops.addAll(this.myComponent);
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
    }
    
    private boolean a(@NotNull final List<OCNode> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "component", "com/jetbrains/cidr/lang/dfa/OCEndlessLoopFinder", "isEndlessLoop"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        boolean b = false;
        for (final OCNode ocNode : list) {
            try {
                if (this.myUnreachedNodes.contains(ocNode)) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            if (ocNode.getJumpTargets() != null) {
                b = true;
                for (final OCNode ocNode2 : ocNode.getJumpTargets()) {
                    try {
                        if (this.myUnreachedNodes.contains(ocNode2)) {
                            continue;
                        }
                        final OCEndlessLoopFinder ocEndlessLoopFinder = this;
                        final OCNode ocNode3 = ocNode2;
                        final OCSymbol ocSymbol = null;
                        final boolean b2 = ocEndlessLoopFinder.isNodeProcessed(ocNode3, ocSymbol);
                        if (!b2) {
                            return false;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    try {
                        final OCEndlessLoopFinder ocEndlessLoopFinder = this;
                        final OCNode ocNode3 = ocNode2;
                        final OCSymbol ocSymbol = null;
                        final boolean b2 = ocEndlessLoopFinder.isNodeProcessed(ocNode3, ocSymbol);
                        if (!b2) {
                            return false;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                }
            }
        }
        return b;
    }
    
    @Override
    protected void nodeProcessed(@NotNull final OCNode ocNode) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCEndlessLoopFinder", "nodeProcessed"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (this.myForwardMode) {
                this.myTopologicalOrder.push(ocNode);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        this.myComponent.add(ocNode);
    }
    
    @NotNull
    public List<OCElementsRange> getEndlessLoops() {
        List<OCElementsRange> ranges;
        try {
            ranges = OCDataFlowAlgorithm.getRanges(this.myEndlessLoops, true);
            if (ranges == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCEndlessLoopFinder", "getEndlessLoops"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return ranges;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
