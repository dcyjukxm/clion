// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

private static class SelectorMethod
{
    String myClassName;
    String myMethodSelector;
    int myTargetIndex;
    boolean mySearchInProtocol;
    
    private SelectorMethod(final String myClassName, final String myMethodSelector, final int myTargetIndex, final boolean mySearchInProtocol) {
        this.myClassName = myClassName;
        this.myMethodSelector = myMethodSelector;
        this.myTargetIndex = myTargetIndex;
        this.mySearchInProtocol = mySearchInProtocol;
    }
    
    private SelectorMethod(final String s, final String s2, final int n) {
        this(s, s2, n, false);
    }
}
