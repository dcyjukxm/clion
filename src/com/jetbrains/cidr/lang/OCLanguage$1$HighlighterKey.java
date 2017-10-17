// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

class HighlighterKey
{
    private OCLanguageKind myLanguageKind;
    private boolean mySupportNullability;
    private boolean myAllowGccAutoType;
    private boolean myAllowAvailabilityExpression;
    
    public HighlighterKey(final OCLanguageKind myLanguageKind, final boolean mySupportNullability, final boolean myAllowGccAutoType, final boolean myAllowAvailabilityExpression) {
        this.myLanguageKind = myLanguageKind;
        this.mySupportNullability = mySupportNullability;
        this.myAllowGccAutoType = myAllowGccAutoType;
        this.myAllowAvailabilityExpression = myAllowAvailabilityExpression;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final HighlighterKey highlighterKey = (HighlighterKey)o;
        return this.mySupportNullability == highlighterKey.mySupportNullability && this.myAllowGccAutoType == highlighterKey.myAllowGccAutoType && this.myAllowAvailabilityExpression == highlighterKey.myAllowAvailabilityExpression && this.myLanguageKind.equals(highlighterKey.myLanguageKind);
    }
    
    @Override
    public int hashCode() {
        return 31 * this.myLanguageKind.hashCode() + (this.mySupportNullability ? 1 : 0);
    }
}
