// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.intellij.psi.PsiElement;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.intellij.util.containers.MultiMap;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;

public class OCNotUsedValueChecker extends OCSingleSymbolAlgorithm
{
    private boolean[] myReferenceReachables;
    private boolean mySymbolUsed;
    private boolean mySymbolAssigned;
    private boolean myIgnoreKills;
    private boolean myHandleReferenceReachables;
    
    public OCNotUsedValueChecker(@NotNull final OCControlFlowGraph ocControlFlowGraph, @NotNull final OCSymbol ocSymbol) {
        if (ocControlFlowGraph == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cfg", "com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker", "<init>"));
        }
        if (ocSymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker", "<init>"));
        }
        super(ocControlFlowGraph, false, ocSymbol);
        this.myReferenceReachables = new boolean[ocControlFlowGraph.getNumOfNodes()];
    }
    
    @Override
    public void process() {
        this.clearProcessedNodes();
        this.myIgnoreKills = true;
        this.myHandleReferenceReachables = false;
        final MultiMap<OCInstruction.InstructionKind, OCInstruction> instructions = this.myCfg.getInstructions(this.mySymbol);
        try {
            if (instructions == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (!instructions.get((Object)OCInstruction.InstructionKind.READ_IN_BLOCK).isEmpty()) {
                this.mySymbolUsed = true;
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        final Iterator iterator = instructions.get((Object)OCInstruction.InstructionKind.REFERENCE).iterator();
        while (iterator.hasNext()) {
            this.traverse(iterator.next().getNode(), this.mySymbol, null, true);
        }
        this.myIgnoreKills = false;
        System.arraycopy(this.myProcessedNodes, 0, this.myReferenceReachables, 0, this.myCfg.getNumOfNodes());
        super.process();
    }
    
    @Override
    protected boolean acceptsInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker", "acceptsInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (this.myIgnoreKills) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (ocInstruction.getKind() != OCInstruction.InstructionKind.KILL) {
                return true;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        return false;
    }
    
    @Override
    protected boolean isStartInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker", "isStartInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            switch (ocInstruction.getKind()) {
                case READ:
                case READ_IN_BLOCK:
                case REFERENCE: {
                    return this.mySymbolUsed = true;
                }
                case WRITE:
                case WRITE_IN_BLOCK: {
                    break;
                }
                default: {
                    return false;
                }
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        this.mySymbolAssigned = true;
        return false;
    }
    
    @Override
    protected boolean isEndInstruction(@NotNull final OCInstruction p0) {
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
        //    18: ldc             "instruction"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isEndInstruction"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getKind:()Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //    48: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.WRITE:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //    51: if_acmpne       99
        //    54: aload_0        
        //    55: getfield        com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    58: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    61: ifeq            91
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: aload_0        
        //    72: getfield        com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    75: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    78: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isFriendOrStatic:()Z
        //    81: ifne            99
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: iconst_1       
        //    92: goto            100
        //    95: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: iconst_0       
        //   100: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     64     67     71     Ljava/lang/IllegalArgumentException;
        //  54     84     87     91     Ljava/lang/IllegalArgumentException;
        //  71     95     95     99     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0071:
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
    protected boolean isNodeProcessed(@NotNull final OCNode p0, @Nullable final OCSymbol p1) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isNodeProcessed"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.myHandleReferenceReachables:Z
        //    48: ifeq            70
        //    51: aload_0        
        //    52: getfield        com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.myReferenceReachables:[Z
        //    55: aload_1        
        //    56: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNode.getIndex:()I
        //    59: baload         
        //    60: ifne            86
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload_0        
        //    71: aload_1        
        //    72: aload_2        
        //    73: invokespecial   com/jetbrains/cidr/lang/dfa/OCSingleSymbolAlgorithm.isNodeProcessed:(Lcom/jetbrains/cidr/lang/dfa/OCNode;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    76: ifeq            94
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: iconst_1       
        //    87: goto            95
        //    90: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: iconst_0       
        //    95: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     63     66     70     Ljava/lang/IllegalArgumentException;
        //  51     79     82     86     Ljava/lang/IllegalArgumentException;
        //  70     90     90     94     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0070:
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
    
    @NotNull
    public List<PsiElement> getNotUsedWrites() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.myCfg:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //     4: aload_0        
        //     5: getfield        com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //     8: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getInstructions:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/intellij/util/containers/MultiMap;
        //    11: astore_1       
        //    12: aload_1        
        //    13: ifnull          38
        //    16: aload_1        
        //    17: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.READ_IN_BLOCK:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //    20: invokevirtual   com/intellij/util/containers/MultiMap.get:(Ljava/lang/Object;)Ljava/util/Collection;
        //    23: invokeinterface java/util/Collection.isEmpty:()Z
        //    28: ifne            87
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    41: dup            
        //    42: ifnonnull       86
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: new             Ljava/lang/IllegalStateException;
        //    55: dup            
        //    56: ldc             "@NotNull method %s.%s must not return null"
        //    58: ldc             2
        //    60: anewarray       Ljava/lang/Object;
        //    63: dup            
        //    64: ldc             0
        //    66: ldc             "com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker"
        //    68: aastore        
        //    69: dup            
        //    70: ldc             1
        //    72: ldc             "getNotUsedWrites"
        //    74: aastore        
        //    75: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    78: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    81: athrow         
        //    82: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: areturn        
        //    87: aload_0        
        //    88: iconst_1       
        //    89: putfield        com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.myHandleReferenceReachables:Z
        //    92: aload_0        
        //    93: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.getNonReachableElements:()Ljava/util/List;
        //    96: dup            
        //    97: ifnonnull       134
        //   100: new             Ljava/lang/IllegalStateException;
        //   103: dup            
        //   104: ldc             "@NotNull method %s.%s must not return null"
        //   106: ldc             2
        //   108: anewarray       Ljava/lang/Object;
        //   111: dup            
        //   112: ldc             0
        //   114: ldc             "com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker"
        //   116: aastore        
        //   117: dup            
        //   118: ldc             1
        //   120: ldc             "getNotUsedWrites"
        //   122: aastore        
        //   123: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   126: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   129: athrow         
        //   130: invokestatic    com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: areturn        
        //    Signature:
        //  ()Ljava/util/List<Lcom/intellij/psi/PsiElement;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  12     31     34     38     Ljava/lang/IllegalArgumentException;
        //  16     45     48     52     Ljava/lang/IllegalArgumentException;
        //  38     82     82     86     Ljava/lang/IllegalArgumentException;
        //  87     130    130    134    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0038:
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
    
    @Nullable
    @Override
    protected PsiElement getElementFromInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCNotUsedValueChecker", "getElementFromInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return ocInstruction.getLValue();
    }
    
    public boolean isSymbolUsed() {
        return this.mySymbolUsed;
    }
    
    public boolean isSymbolAssigned() {
        return this.mySymbolAssigned;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
