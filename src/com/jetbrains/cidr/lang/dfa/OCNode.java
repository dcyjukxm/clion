// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.containers.MultiMap;
import java.util.List;
import com.jetbrains.cidr.lang.util.OCElementsRange;

public class OCNode
{
    private OCControlFlowGraph myGraph;
    private int myIndex;
    private int myId;
    private OCElementsRange myRange;
    private boolean myFake;
    private List<OCNode> myJumpTargets;
    private List<OCNode> myJumpSources;
    private List<OCNode> myFakeJumpTargets;
    private OCNode myNodeAfterReturn;
    private List<OCInstruction> myInstructions;
    private MultiMap<OCInstruction.InstructionKind, OCInstruction> myInstructionsByType;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCNode(@NotNull final OCControlFlowGraph myGraph, final int myIndex, final int myId, final boolean myFake) {
        if (myGraph == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "graph", "com/jetbrains/cidr/lang/dfa/OCNode", "<init>"));
        }
        this.myRange = null;
        this.myGraph = myGraph;
        this.myIndex = myIndex;
        this.myId = myId;
        this.myFake = myFake;
    }
    
    @NotNull
    public OCControlFlowGraph getGraph() {
        OCControlFlowGraph myGraph;
        try {
            myGraph = this.myGraph;
            if (myGraph == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCNode", "getGraph"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myGraph;
    }
    
    public int getIndex() {
        return this.myIndex;
    }
    
    public int getId() {
        return this.myId;
    }
    
    public boolean isEmpty() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_0        
        //     2: getfield        com/jetbrains/cidr/lang/dfa/OCNode.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //     5: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getLastAddedNode:()Lcom/jetbrains/cidr/lang/dfa/OCNode;
        //     8: if_acmpne       61
        //    11: aload_0        
        //    12: getfield        com/jetbrains/cidr/lang/dfa/OCNode.myRange:Lcom/jetbrains/cidr/lang/util/OCElementsRange;
        //    15: ifnonnull       61
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/dfa/OCNode.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    24: athrow         
        //    25: aload_0        
        //    26: getfield        com/jetbrains/cidr/lang/dfa/OCNode.myJumpTargets:Ljava/util/List;
        //    29: ifnonnull       61
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/dfa/OCNode.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: aload_0        
        //    40: getfield        com/jetbrains/cidr/lang/dfa/OCNode.myInstructions:Ljava/util/List;
        //    43: ifnonnull       61
        //    46: goto            53
        //    49: invokestatic    com/jetbrains/cidr/lang/dfa/OCNode.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    52: athrow         
        //    53: iconst_1       
        //    54: goto            62
        //    57: invokestatic    com/jetbrains/cidr/lang/dfa/OCNode.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    60: athrow         
        //    61: iconst_0       
        //    62: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      18     21     25     Ljava/lang/IllegalArgumentException;
        //  11     32     35     39     Ljava/lang/IllegalArgumentException;
        //  25     46     49     53     Ljava/lang/IllegalArgumentException;
        //  39     57     57     61     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0025:
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
    
    public boolean isFake() {
        return this.myFake;
    }
    
    public void addBranch(@NotNull final OCNode ocNode) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/lang/dfa/OCNode", "addBranch"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.addBranch(ocNode, false);
    }
    
    public void addBranch(@NotNull final OCNode ocNode, final boolean b) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/lang/dfa/OCNode", "addBranch"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0124: {
            Label_0080: {
                Label_0068: {
                    try {
                        if (OCNode.$assertionsDisabled) {
                            break Label_0080;
                        }
                        final OCNode ocNode2 = this;
                        final OCControlFlowGraph ocControlFlowGraph = ocNode2.myGraph;
                        final OCNode ocNode3 = ocNode;
                        final OCControlFlowGraph ocControlFlowGraph2 = ocNode3.myGraph;
                        if (ocControlFlowGraph != ocControlFlowGraph2) {
                            break Label_0068;
                        }
                        break Label_0080;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCNode ocNode2 = this;
                        final OCControlFlowGraph ocControlFlowGraph = ocNode2.myGraph;
                        final OCNode ocNode3 = ocNode;
                        final OCControlFlowGraph ocControlFlowGraph2 = ocNode3.myGraph;
                        if (ocControlFlowGraph != ocControlFlowGraph2) {
                            throw new AssertionError();
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    if (this.myJumpTargets == null) {
                        this.myJumpTargets = new ArrayList<OCNode>(2);
                        break Label_0124;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            try {
                if (this.myJumpTargets.contains(ocNode)) {
                    return;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                if (ocNode.myJumpSources == null) {
                    ocNode.myJumpSources = new ArrayList<OCNode>(2);
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        Label_0190: {
            try {
                this.myJumpTargets.add(ocNode);
                ocNode.myJumpSources.add(this);
                if (!b) {
                    return;
                }
                final OCNode ocNode4 = this;
                final List<OCNode> list = ocNode4.myFakeJumpTargets;
                if (list == null) {
                    break Label_0190;
                }
                break Label_0190;
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
            try {
                final OCNode ocNode4 = this;
                final List<OCNode> list = ocNode4.myFakeJumpTargets;
                if (list == null) {
                    this.myFakeJumpTargets = new ArrayList<OCNode>(2);
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        this.myFakeJumpTargets.add(ocNode);
    }
    
    public void enlarge(@NotNull final PsiElement psiElement, @NotNull final PsiElement psiElement2) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/dfa/OCNode", "enlarge"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/dfa/OCNode", "enlarge"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        TextRange textRange = null;
        Label_0115: {
            try {
                if (psiElement instanceof OCMacroForeignLeafElement) {
                    textRange = ((OCMacroForeignLeafElement)psiElement).getRealTextRange();
                    break Label_0115;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            textRange = psiElement.getTextRange();
        }
        final TextRange textRange2 = textRange;
        Label_0202: {
            try {
                if (this.myRange == null) {
                    this.myRange = new OCElementsRange(psiElement, psiElement) {
                        @Override
                        public TextRange getTextRange() {
                            return new TextRange(this.getStartOffset(), this.getEndOffset());
                        }
                    };
                    break Label_0202;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                if (this.myRange.getStartOffset() > textRange2.getStartOffset()) {
                    this.myRange.setFirstElement(psiElement);
                    break Label_0202;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                if (this.myRange.getEndOffset() < textRange2.getEndOffset()) {
                    this.myRange.setLastElement(psiElement);
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            try {
                if (this.myRange.getTextRange().contains(psiElement2.getTextRange())) {
                    this.myRange.setContainsCompositeElement(true);
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
    }
    
    public void addInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCNode", "addInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myInstructions == null) {
                this.myInstructions = new ArrayList<OCInstruction>();
                this.myInstructionsByType = (MultiMap<OCInstruction.InstructionKind, OCInstruction>)new MultiMap();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.myInstructions.add(ocInstruction);
        this.myInstructionsByType.putValue((Object)ocInstruction.getKind(), (Object)ocInstruction);
    }
    
    @Nullable
    public List<OCInstruction> getInstructions() {
        return this.myInstructions;
    }
    
    @Nullable
    public List<OCNode> getJumpTargets() {
        return this.myJumpTargets;
    }
    
    @Nullable
    public List<OCNode> getJumpSources() {
        return this.myJumpSources;
    }
    
    @Nullable
    public List<OCNode> getFakeJumpTargets() {
        return this.myFakeJumpTargets;
    }
    
    @Nullable
    public OCElementsRange getRange() {
        return this.myRange;
    }
    
    public int getEndOffset() {
        try {
            if (this.myRange != null) {
                return this.myRange.getEndOffset();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return -1;
    }
    
    @Nullable
    public OCNode getNodeAfterReturn() {
        return this.myNodeAfterReturn;
    }
    
    public void setNodeAfterReturn(@NotNull final OCNode myNodeAfterReturn) {
        try {
            if (myNodeAfterReturn == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCNode", "setNodeAfterReturn"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myNodeAfterReturn = myNodeAfterReturn;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCNode.class.desiredAssertionStatus()) {
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
