// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.intellij.openapi.util.TextRange;
import com.intellij.util.containers.MultiMap;
import java.util.Iterator;
import java.util.Collection;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.util.OCElementsRange;
import java.util.Set;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.containers.LinkedMultiMap;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import com.intellij.psi.PsiElement;

public class OCControlFlowGraph
{
    private PsiElement myCodeFragment;
    private List<OCNode> myNodes;
    private OCNode myStartNode;
    private LinkedHashSet<OCNode> myExitNodes;
    private OCControlFlowGraph myParentGraph;
    private int myForbidSplitNodesDepth;
    private int myNextNodeId;
    private LinkedHashMap<OCSymbol, LinkedMultiMap<OCInstruction.InstructionKind, OCInstruction>> myInstructions;
    private LinkedHashMap<PsiElement, OCInstruction> myReadInstructions;
    private LinkedHashSet<OCInstruction> myAllInstructions;
    private LinkedHashMap<OCSymbol, OCInstruction> myDeclarators;
    private LinkedHashMap<OCSymbol, OCControlFlowGraph> myClosureVariables;
    
    public OCControlFlowGraph(@NotNull final PsiElement myCodeFragment, @Nullable final OCControlFlowGraph myParentGraph) {
        if (myCodeFragment == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "codeFragment", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "<init>"));
        }
        this.myNodes = new ArrayList<OCNode>();
        this.myExitNodes = new LinkedHashSet<OCNode>();
        this.myInstructions = new LinkedHashMap<OCSymbol, LinkedMultiMap<OCInstruction.InstructionKind, OCInstruction>>();
        this.myReadInstructions = new LinkedHashMap<PsiElement, OCInstruction>();
        this.myAllInstructions = new LinkedHashSet<OCInstruction>();
        this.myDeclarators = new LinkedHashMap<OCSymbol, OCInstruction>();
        this.myClosureVariables = new LinkedHashMap<OCSymbol, OCControlFlowGraph>();
        this.myCodeFragment = myCodeFragment;
        this.myParentGraph = myParentGraph;
    }
    
    @NotNull
    public List<OCNode> getNodes() {
        List<OCNode> myNodes;
        try {
            myNodes = this.myNodes;
            if (myNodes == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "getNodes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myNodes;
    }
    
    public int getNumOfNodes() {
        return this.myNodes.size();
    }
    
    public int getNextNodeId() {
        return this.myNextNodeId++;
    }
    
    @NotNull
    public OCNode addNode() {
        OCNode addNode;
        try {
            addNode = this.addNode(false);
            if (addNode == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "addNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return addNode;
    }
    
    @NotNull
    public OCNode addNode(final boolean b) {
        OCNode lastAddedNode = null;
        Label_0099: {
            try {
                if (!this.isSplitNodesAllowed()) {
                    if (!b) {
                        break Label_0099;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final OCNode ocNode = new OCNode(this, this.myNodes.size(), this.myNextNodeId++, b);
            OCNode ocNode2;
            try {
                this.myNodes.add(ocNode);
                ocNode2 = ocNode;
                if (ocNode2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "addNode"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return ocNode2;
            try {
                lastAddedNode = this.getLastAddedNode();
                if (lastAddedNode == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "addNode"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return lastAddedNode;
    }
    
    public void forbidSplittingNodes() {
        ++this.myForbidSplitNodesDepth;
    }
    
    public void allowSplittingNodes() {
        --this.myForbidSplitNodesDepth;
    }
    
    public boolean isSplitNodesAllowed() {
        try {
            if (this.myForbidSplitNodesDepth == 0) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public void removeNode(@NotNull final OCNode p0, final boolean p1) {
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
        //    18: ldc             "node"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "removeNode"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.isSplitNodesAllowed:()Z
        //    48: ifne            62
        //    51: iload_2        
        //    52: ifeq            146
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: getstatic       com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.$assertionsDisabled:Z
        //    65: ifne            130
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: aload_0        
        //    76: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getLastAddedNode:()Lcom/jetbrains/cidr/lang/dfa/OCNode;
        //    79: aload_1        
        //    80: if_acmpne       118
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: aload_1        
        //    91: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNode.isEmpty:()Z
        //    94: ifne            130
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: aload_1        
        //   105: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNode.isFake:()Z
        //   108: ifne            130
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   117: athrow         
        //   118: new             Ljava/lang/AssertionError;
        //   121: dup            
        //   122: invokespecial   java/lang/AssertionError.<init>:()V
        //   125: athrow         
        //   126: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: aload_0        
        //   131: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.myNodes:Ljava/util/List;
        //   134: aload_0        
        //   135: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getNumOfNodes:()I
        //   138: iconst_1       
        //   139: isub           
        //   140: invokeinterface java/util/List.remove:(I)Ljava/lang/Object;
        //   145: pop            
        //   146: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     55     58     62     Ljava/lang/IllegalArgumentException;
        //  51     68     71     75     Ljava/lang/IllegalArgumentException;
        //  62     83     86     90     Ljava/lang/IllegalArgumentException;
        //  75     97     100    104    Ljava/lang/IllegalArgumentException;
        //  90     111    114    118    Ljava/lang/IllegalArgumentException;
        //  104    126    126    130    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0062:
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
    
    public void setStartNode(@NotNull final OCNode myStartNode) {
        try {
            if (myStartNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "startNode", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "setStartNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myStartNode = myStartNode;
    }
    
    @NotNull
    public OCNode getStartNode() {
        OCNode myStartNode;
        try {
            myStartNode = this.myStartNode;
            if (myStartNode == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "getStartNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myStartNode;
    }
    
    public void addExitNode(@NotNull final OCNode ocNode) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "addExitNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myExitNodes.add(ocNode);
    }
    
    @NotNull
    public Set<OCNode> getExitNodes() {
        LinkedHashSet<OCNode> myExitNodes;
        try {
            myExitNodes = this.myExitNodes;
            if (myExitNodes == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "getExitNodes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myExitNodes;
    }
    
    @Nullable
    public OCNode getPreviousNonEmptyNode(@NotNull final OCNode ocNode) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "getPreviousNonEmptyNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        int n = ocNode.getIndex() - 1;
        while (true) {
            Label_0081: {
                try {
                    if (n < 0) {
                        break;
                    }
                    final OCControlFlowGraph ocControlFlowGraph = this;
                    final List<OCNode> list = ocControlFlowGraph.myNodes;
                    final int n2 = n;
                    final OCNode ocNode2 = list.get(n2);
                    final OCNode ocNode3 = ocNode2;
                    final OCElementsRange ocElementsRange = ocNode3.getRange();
                    if (ocElementsRange != null) {
                        break Label_0081;
                    }
                    break Label_0081;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCControlFlowGraph ocControlFlowGraph = this;
                    final List<OCNode> list = ocControlFlowGraph.myNodes;
                    final int n2 = n;
                    final OCNode ocNode2 = list.get(n2);
                    final OCNode ocNode3 = ocNode2;
                    final OCElementsRange ocElementsRange = ocNode3.getRange();
                    if (ocElementsRange != null) {
                        return this.myNodes.get(n);
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            --n;
        }
        return null;
    }
    
    @NotNull
    public OCNode getLastAddedNode() {
        OCNode ocNode;
        try {
            ocNode = this.myNodes.get(this.getNumOfNodes() - 1);
            if (ocNode == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "getLastAddedNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocNode;
    }
    
    @Nullable
    public OCInstruction addInstruction(@NotNull final OCInstruction.InstructionKind instructionKind, @Nullable final PsiElement psiElement, @Nullable final OCSymbol ocSymbol) {
        try {
            if (instructionKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "addInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.addInstruction(this.getLastAddedNode(), instructionKind, null, psiElement, ocSymbol);
    }
    
    @Nullable
    public OCInstruction addInstruction(@NotNull final OCInstruction.InstructionKind instructionKind, @Nullable final PsiElement psiElement, @Nullable final PsiElement psiElement2, @Nullable final OCSymbol ocSymbol) {
        try {
            if (instructionKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "addInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.addInstruction(this.getLastAddedNode(), instructionKind, psiElement, psiElement2, ocSymbol);
    }
    
    @Nullable
    public OCInstruction addInstruction(@NotNull final OCNode ocNode, final OCInstruction.InstructionKind instructionKind, @Nullable final PsiElement psiElement, @Nullable final PsiElement psiElement2, @Nullable final OCSymbol ocSymbol) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "addInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocSymbol == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCInstruction associatedInstruction = new OCInstruction(instructionKind, ocNode, psiElement, psiElement2, ocSymbol);
        if (instructionKind == OCInstruction.InstructionKind.WRITE) {
            final OCInstruction associatedInstruction2 = this.myReadInstructions.get(OCParenthesesUtils.diveIntoParenthesesAndCasts(psiElement2));
            Label_0118: {
                try {
                    if (associatedInstruction2 == null) {
                        break Label_0118;
                    }
                    final OCInstruction ocInstruction = associatedInstruction2;
                    final OCInstruction.InstructionKind instructionKind2 = ocInstruction.getKind();
                    final OCInstruction.InstructionKind instructionKind3 = OCInstruction.InstructionKind.READ;
                    if (instructionKind2 == instructionKind3) {
                        break Label_0118;
                    }
                    break Label_0118;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCInstruction ocInstruction = associatedInstruction2;
                    final OCInstruction.InstructionKind instructionKind2 = ocInstruction.getKind();
                    final OCInstruction.InstructionKind instructionKind3 = OCInstruction.InstructionKind.READ;
                    if (instructionKind2 == instructionKind3) {
                        associatedInstruction2.setAssociatedInstruction(associatedInstruction);
                        associatedInstruction.setAssociatedInstruction(associatedInstruction2);
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
        }
        else {
            try {
                if (instructionKind == OCInstruction.InstructionKind.READ) {
                    this.myReadInstructions.put(OCParenthesesUtils.diveIntoParenthesesAndCasts(psiElement2), associatedInstruction);
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        Label_0274: {
            try {
                if (instructionKind == OCInstruction.InstructionKind.DECLARATOR) {
                    this.myDeclarators.put(ocSymbol, associatedInstruction);
                    break Label_0274;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            if (!this.myDeclarators.containsKey(ocSymbol)) {
                OCControlFlowGraph ocControlFlowGraph = this.myParentGraph;
                while (true) {
                    try {
                        if (ocControlFlowGraph == null || ocControlFlowGraph.myDeclarators.containsKey(ocSymbol)) {
                            break;
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                    ocControlFlowGraph = ocControlFlowGraph.myParentGraph;
                }
                try {
                    if (ocControlFlowGraph != null) {
                        this.myClosureVariables.put(ocSymbol, ocControlFlowGraph);
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
            }
        }
        LinkedMultiMap linkedMultiMap = this.myInstructions.get(ocSymbol);
        if (linkedMultiMap == null) {
            linkedMultiMap = new LinkedMultiMap();
            this.myInstructions.put(ocSymbol, (LinkedMultiMap<OCInstruction.InstructionKind, OCInstruction>)linkedMultiMap);
        }
        linkedMultiMap.putValue((Object)instructionKind, (Object)associatedInstruction);
        this.myAllInstructions.add(associatedInstruction);
        ocNode.addInstruction(associatedInstruction);
        return associatedInstruction;
    }
    
    public void addInstructions(@NotNull final OCNode ocNode, @NotNull final OCInstruction.InstructionKind instructionKind, @NotNull final Collection<OCInstruction> collection) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "addInstructions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (instructionKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "addInstructions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instructions", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "addInstructions"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        for (final OCInstruction ocInstruction : collection) {
            final OCInstruction addInstruction = this.addInstruction(ocNode, instructionKind, ocInstruction.getLValue(), ocInstruction.getRValue(), ocInstruction.getSymbol());
            if (addInstruction != null) {
                final OCInstruction associatedInstruction = ocInstruction.getAssociatedInstruction();
                OCInstruction ocInstruction2 = null;
                OCInstruction associatedInstruction2 = null;
                Label_0215: {
                    try {
                        ocInstruction2 = addInstruction;
                        if (associatedInstruction != null) {
                            associatedInstruction2 = associatedInstruction;
                            break Label_0215;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    associatedInstruction2 = ocInstruction;
                }
                ocInstruction2.setAssociatedInstruction(associatedInstruction2);
            }
        }
    }
    
    @Nullable
    public MultiMap<OCInstruction.InstructionKind, OCInstruction> getInstructions(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "getInstructions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (MultiMap<OCInstruction.InstructionKind, OCInstruction>)this.myInstructions.get(ocSymbol);
    }
    
    public Set<OCInstruction> getAllInstructions() {
        return this.myAllInstructions;
    }
    
    public OCInstruction getDeclaratorInstruction(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "getDeclaratorInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myDeclarators.get(ocSymbol);
    }
    
    public Set<OCSymbol> getLocalSymbols() {
        return (Set<OCSymbol>)this.myDeclarators.keySet();
    }
    
    public Set<OCSymbol> getClosureSymbols() {
        return (Set<OCSymbol>)this.myClosureVariables.keySet();
    }
    
    public OCControlFlowGraph getClosureVariableDeclaratorGraph(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "getClosureVariableDeclaratorGraph"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myClosureVariables.get(ocSymbol);
    }
    
    @NotNull
    public PsiElement getCodeFragment() {
        PsiElement myCodeFragment;
        try {
            myCodeFragment = this.myCodeFragment;
            if (myCodeFragment == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "getCodeFragment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myCodeFragment;
    }
    
    @Nullable
    public OCControlFlowGraph getParentGraph() {
        return this.myParentGraph;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("<CFG>\n");
        final String text = this.myCodeFragment.getContainingFile().getText();
        for (final OCNode ocNode : this.myNodes) {
            String s = "node";
            if (ocNode == this.myStartNode) {
                s = "start-node";
            }
            else if (this.myExitNodes.contains(ocNode)) {
                s = "exit-node";
            }
            sb.append("  <").append(s).append(" id=\"").append(ocNode.getIndex()).append("\">\n");
            final OCElementsRange range = ocNode.getRange();
            if (range != null) {
                final TextRange textRange = range.getTextRange();
                sb.append(text.substring(textRange.getStartOffset(), textRange.getEndOffset()).trim()).append("\n");
            }
            if (ocNode.getJumpTargets() != null) {
                final Iterator<OCNode> iterator2 = ocNode.getJumpTargets().iterator();
                while (iterator2.hasNext()) {
                    sb.append("    <target node-id=\"").append(iterator2.next().getIndex()).append("\"/>\n");
                }
            }
            sb.append("  </").append(s).append(">\n");
        }
        sb.append("</CFG>\n");
        return sb.toString();
    }
    
    public boolean hasInstructionsInParentGraph(@NotNull final OCSymbol ocSymbol, final OCInstruction.InstructionKind... array) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCControlFlowGraph", "hasInstructionsInParentGraph"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCControlFlowGraph closureVariableDeclaratorGraph = this.getClosureVariableDeclaratorGraph(ocSymbol);
        try {
            if (closureVariableDeclaratorGraph == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final MultiMap<OCInstruction.InstructionKind, OCInstruction> instructions = closureVariableDeclaratorGraph.getInstructions(ocSymbol);
        try {
            if (instructions == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            final Iterator iterator = instructions.get((Object)array[i]).iterator();
            while (iterator.hasNext()) {
                final OCInstruction associatedInstruction = iterator.next().getAssociatedInstruction();
                try {
                    if (associatedInstruction == null) {
                        return true;
                    }
                    final OCInstruction ocInstruction = associatedInstruction;
                    final OCNode ocNode = ocInstruction.getNode();
                    final OCControlFlowGraph ocControlFlowGraph = ocNode.getGraph();
                    final OCControlFlowGraph ocControlFlowGraph2 = this;
                    if (ocControlFlowGraph != ocControlFlowGraph2) {
                        return true;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    final OCInstruction ocInstruction = associatedInstruction;
                    final OCNode ocNode = ocInstruction.getNode();
                    final OCControlFlowGraph ocControlFlowGraph = ocNode.getGraph();
                    final OCControlFlowGraph ocControlFlowGraph2 = this;
                    if (ocControlFlowGraph != ocControlFlowGraph2) {
                        return true;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
        }
        return false;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCControlFlowGraph.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
