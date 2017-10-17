// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCExpression;

protected static class SwitchInfo
{
    OCNode myNode;
    OCExpression myExpression;
    List<OCExpression> myCaseExpressions;
    boolean myHasDefault;
    
    public SwitchInfo(@Nullable final OCDeclarationOrExpression ocDeclarationOrExpression, @NotNull final OCNode myNode) {
        if (myNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$SwitchInfo", "<init>"));
        }
        this.myCaseExpressions = new ArrayList<OCExpression>();
        this.myExpression = ((ocDeclarationOrExpression != null) ? ocDeclarationOrExpression.getExpression() : null);
        this.myNode = myNode;
    }
    
    @NotNull
    public OCNode getNode() {
        OCNode myNode;
        try {
            myNode = this.myNode;
            if (myNode == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$SwitchInfo", "getNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myNode;
    }
    
    @Nullable
    public OCExpression getExpression() {
        return this.myExpression;
    }
    
    public boolean hasDefault() {
        return this.myHasDefault;
    }
    
    public void setHasDefault(final boolean myHasDefault) {
        this.myHasDefault = myHasDefault;
    }
    
    public void addCaseExpression(@Nullable final OCExpression ocExpression) {
        try {
            if (ocExpression != null) {
                this.myCaseExpressions.add(ocExpression);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    public List<OCExpression> getCaseExpressions() {
        return this.myCaseExpressions;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
