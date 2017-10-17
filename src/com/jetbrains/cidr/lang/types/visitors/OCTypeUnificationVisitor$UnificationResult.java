// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

public static class UnificationResult
{
    private final int numOfUnified;
    private int numOfNonSpecializedArgs;
    private int numOfConstantValueArgs;
    
    UnificationResult(final int numOfUnified) {
        this.numOfUnified = numOfUnified;
    }
    
    public UnificationResult(final int numOfUnified, final int numOfNonSpecializedArgs, final int numOfConstantValueArgs) {
        this.numOfUnified = numOfUnified;
        this.numOfNonSpecializedArgs = numOfNonSpecializedArgs;
        this.numOfConstantValueArgs = numOfConstantValueArgs;
    }
    
    UnificationResult add(final UnificationResult unificationResult) {
        if (this == OCTypeUnificationVisitor.NOT_UNIFIED || unificationResult == OCTypeUnificationVisitor.NOT_UNIFIED) {
            return OCTypeUnificationVisitor.NOT_UNIFIED;
        }
        return new UnificationResult(this.numOfUnified + unificationResult.numOfUnified, this.numOfNonSpecializedArgs + unificationResult.numOfNonSpecializedArgs, this.numOfConstantValueArgs + unificationResult.numOfConstantValueArgs);
    }
    
    void incNumOfNonSpecializedArgs() {
        ++this.numOfNonSpecializedArgs;
    }
    
    public boolean isUnified() {
        return this.numOfUnified > 0;
    }
    
    boolean isBetter(final UnificationResult unificationResult) {
        return this.numOfUnified > unificationResult.numOfUnified || (this.numOfUnified >= unificationResult.numOfUnified && (this.numOfNonSpecializedArgs < unificationResult.numOfNonSpecializedArgs || (this.numOfNonSpecializedArgs <= unificationResult.numOfNonSpecializedArgs && this.numOfConstantValueArgs > unificationResult.numOfConstantValueArgs)));
    }
    
    @Override
    public String toString() {
        if (this == OCTypeUnificationVisitor.UNIFIED) {
            return "UNIFIED";
        }
        if (this == OCTypeUnificationVisitor.UNIFIED_CONST_VALUE) {
            return "UNIFIED_CONST_VALUE";
        }
        if (this == OCTypeUnificationVisitor.NOT_UNIFIED) {
            return "NOT_UNIFIED";
        }
        if (this == OCTypeUnificationVisitor.UNKNOWN) {
            return "UNKNOWN";
        }
        return "UnificationResult(unified=" + this.numOfUnified + ", non-spec=" + this.numOfNonSpecializedArgs + ", const-value=" + this.numOfConstantValueArgs + ")";
    }
}
