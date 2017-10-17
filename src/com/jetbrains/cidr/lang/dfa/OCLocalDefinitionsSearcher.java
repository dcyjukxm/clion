// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import java.util.Iterator;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.util.containers.MultiMap;

public class OCLocalDefinitionsSearcher extends OCSingleSymbolAlgorithm
{
    private MultiMap<OCInstruction.InstructionKind, OCInstruction> myInstructions;
    private PsiElement myStartElement;
    private boolean myStopOnWrite;
    private boolean myProcessStartInstruction;
    
    public OCLocalDefinitionsSearcher(@NotNull final OCControlFlowGraph ocControlFlowGraph, @NotNull final OCSymbol ocSymbol, @NotNull final PsiElement myStartElement, final boolean b, final boolean myStopOnWrite, final boolean myProcessStartInstruction) {
        if (ocControlFlowGraph == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cfg", "com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher", "<init>"));
        }
        if (ocSymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher", "<init>"));
        }
        if (myStartElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "startElement", "com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher", "<init>"));
        }
        super(ocControlFlowGraph, b, ocSymbol);
        this.myInstructions = (MultiMap<OCInstruction.InstructionKind, OCInstruction>)new MultiMap();
        this.myStartElement = myStartElement;
        this.myStopOnWrite = myStopOnWrite;
        this.myProcessStartInstruction = myProcessStartInstruction;
    }
    
    @Override
    protected boolean isStartInstruction(@NotNull final OCInstruction p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isStartInstruction"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.myStartElement:Lcom/intellij/psi/PsiElement;
        //    48: aload_1        
        //    49: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getRValue:()Lcom/intellij/psi/PsiElement;
        //    52: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isEqualWithMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Z
        //    55: ifne            79
        //    58: aload_0        
        //    59: getfield        com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.myStartElement:Lcom/intellij/psi/PsiElement;
        //    62: aload_1        
        //    63: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getLValue:()Lcom/intellij/psi/PsiElement;
        //    66: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isEqualWithMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Z
        //    69: ifeq            121
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload_1        
        //    80: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getKind:()Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //    83: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.DECLARATOR:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //    86: if_acmpeq       121
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: aload_1        
        //    97: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getKind:()Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   100: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.KILL:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   103: if_acmpeq       121
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: iconst_1       
        //   114: goto            122
        //   117: invokestatic    com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   120: athrow         
        //   121: iconst_0       
        //   122: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     72     75     79     Ljava/lang/IllegalArgumentException;
        //  58     89     92     96     Ljava/lang/IllegalArgumentException;
        //  79     106    109    113    Ljava/lang/IllegalArgumentException;
        //  96     117    117    121    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0079:
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
    public void process() {
        this.clearProcessedNodes();
        final Collection<OCInstruction> startInstructions = this.getStartInstructions();
        try {
            if (startInstructions.isEmpty()) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        OCInstruction ocInstruction = startInstructions.iterator().next();
        for (final OCInstruction ocInstruction2 : startInstructions) {
            if (ocInstruction2.getKind() == OCInstruction.InstructionKind.WRITE) {
                ocInstruction = ocInstruction2;
            }
        }
        Label_0109: {
            try {
                if (!this.myProcessStartInstruction) {
                    break Label_0109;
                }
                final OCLocalDefinitionsSearcher ocLocalDefinitionsSearcher = this;
                final OCInstruction ocInstruction3 = ocInstruction;
                final boolean b = ocLocalDefinitionsSearcher.processInstruction(ocInstruction3);
                if (!b) {
                    return;
                }
                break Label_0109;
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            try {
                final OCLocalDefinitionsSearcher ocLocalDefinitionsSearcher = this;
                final OCInstruction ocInstruction3 = ocInstruction;
                final boolean b = ocLocalDefinitionsSearcher.processInstruction(ocInstruction3);
                if (!b) {
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        this.traverse(ocInstruction, this.mySymbol, this.myIsForward);
    }
    
    public void processFromStart() {
        this.traverseFromStart(this.mySymbol);
    }
    
    @Override
    protected boolean processInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher", "processInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final OCInstruction.InstructionKind kind = ocInstruction.getKind();
        Label_0079: {
            try {
                this.myInstructions.putValue((Object)kind, (Object)ocInstruction);
                if (!this.myStopOnWrite) {
                    break Label_0079;
                }
                final OCInstruction.InstructionKind instructionKind = kind;
                final OCInstruction.InstructionKind instructionKind2 = OCInstruction.InstructionKind.WRITE;
                if (instructionKind != instructionKind2) {
                    break Label_0079;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            try {
                final OCInstruction.InstructionKind instructionKind = kind;
                final OCInstruction.InstructionKind instructionKind2 = OCInstruction.InstructionKind.WRITE;
                if (instructionKind != instructionKind2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return false;
    }
    
    public Collection<OCInstruction> getInstructionsOfKind(@NotNull final OCInstruction.InstructionKind instructionKind) {
        try {
            if (instructionKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher", "getInstructionsOfKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return (Collection<OCInstruction>)this.myInstructions.get((Object)instructionKind);
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
