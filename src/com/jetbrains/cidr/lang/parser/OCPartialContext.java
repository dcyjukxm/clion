// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTable;

class OCPartialContext
{
    private FileSymbolTable mySymbolTable;
    private OCInclusionContext myInclusionContext;
    private int myFinalOffset;
    
    public OCPartialContext(@NotNull final FileSymbolTable mySymbolTable, @NotNull final OCInclusionContext myInclusionContext, final int myFinalOffset) {
        if (mySymbolTable == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolTable", "com/jetbrains/cidr/lang/parser/OCPartialContext", "<init>"));
        }
        if (myInclusionContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "inclusionContext", "com/jetbrains/cidr/lang/parser/OCPartialContext", "<init>"));
        }
        this.mySymbolTable = mySymbolTable;
        this.myInclusionContext = myInclusionContext;
        this.myFinalOffset = myFinalOffset;
    }
    
    @NotNull
    public FileSymbolTable getSymbolTable() {
        FileSymbolTable mySymbolTable;
        try {
            mySymbolTable = this.mySymbolTable;
            if (mySymbolTable == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/parser/OCPartialContext", "getSymbolTable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return mySymbolTable;
    }
    
    @NotNull
    public OCInclusionContext getInclusionContext() {
        OCInclusionContext myInclusionContext;
        try {
            myInclusionContext = this.myInclusionContext;
            if (myInclusionContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/parser/OCPartialContext", "getInclusionContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myInclusionContext;
    }
    
    public int getFinalOffset() {
        return this.myFinalOffset;
    }
    
    public void setFinalOffset(final int myFinalOffset) {
        this.myFinalOffset = myFinalOffset;
    }
    
    public void setInclusionContext(@NotNull final OCInclusionContext myInclusionContext) {
        try {
            if (myInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/parser/OCPartialContext", "setInclusionContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myInclusionContext = myInclusionContext;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
