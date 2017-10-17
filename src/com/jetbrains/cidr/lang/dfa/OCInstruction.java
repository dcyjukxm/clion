// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.Nullable;

public class OCInstruction
{
    private final InstructionKind myKind;
    private final OCNode myNode;
    @Nullable
    private OCInstruction myAssociatedInstruction;
    @NotNull
    private final OCSymbol mySymbol;
    private final PsiElement myLValue;
    private final PsiElement myRValue;
    private boolean myTransparentRead;
    
    OCInstruction(@NotNull final InstructionKind instructionKind, @NotNull final OCNode ocNode, @Nullable final PsiElement psiElement, @NotNull final OCSymbol ocSymbol) {
        if (instructionKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/dfa/OCInstruction", "<init>"));
        }
        if (ocNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCInstruction", "<init>"));
        }
        if (ocSymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCInstruction", "<init>"));
        }
        this(instructionKind, ocNode, psiElement, null, ocSymbol);
    }
    
    OCInstruction(@NotNull final InstructionKind myKind, @NotNull final OCNode myNode, @Nullable final PsiElement myLValue, @Nullable final PsiElement myRValue, @NotNull final OCSymbol mySymbol) {
        if (myKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/dfa/OCInstruction", "<init>"));
        }
        if (myNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCInstruction", "<init>"));
        }
        if (mySymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCInstruction", "<init>"));
        }
        this.myKind = myKind;
        this.myNode = myNode;
        this.myLValue = myLValue;
        this.mySymbol = mySymbol;
        this.myRValue = myRValue;
    }
    
    @Nullable
    public PsiElement getLValue() {
        return this.myLValue;
    }
    
    @Nullable
    public PsiElement getRValue() {
        return this.myRValue;
    }
    
    @NotNull
    public InstructionKind getKind() {
        InstructionKind myKind;
        try {
            myKind = this.myKind;
            if (myKind == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCInstruction", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myKind;
    }
    
    @NotNull
    public OCNode getNode() {
        OCNode myNode;
        try {
            myNode = this.myNode;
            if (myNode == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCInstruction", "getNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myNode;
    }
    
    @NotNull
    public OCSymbol getSymbol() {
        OCSymbol mySymbol;
        try {
            mySymbol = this.mySymbol;
            if (mySymbol == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCInstruction", "getSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return mySymbol;
    }
    
    public long getSymbolOffset() {
        final long complexOffset = this.mySymbol.getComplexOffset();
        try {
            if (complexOffset != 0L) {
                return complexOffset;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.mySymbol.hashCode();
    }
    
    public void setAssociatedInstruction(@Nullable final OCInstruction myAssociatedInstruction) {
        this.myAssociatedInstruction = myAssociatedInstruction;
    }
    
    @Nullable
    public OCInstruction getAssociatedInstruction() {
        return this.myAssociatedInstruction;
    }
    
    public boolean isTransparentRead() {
        return this.myTransparentRead;
    }
    
    public void setTransparentRead(final boolean myTransparentRead) {
        this.myTransparentRead = myTransparentRead;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum InstructionKind
    {
        DECLARATOR, 
        READ, 
        WRITE, 
        KILL, 
        REFERENCE, 
        READ_IN_BLOCK, 
        WRITE_IN_BLOCK;
    }
}
