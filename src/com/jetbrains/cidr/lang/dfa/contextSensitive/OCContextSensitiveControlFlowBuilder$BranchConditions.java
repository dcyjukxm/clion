// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import org.jetbrains.annotations.NotNull;

static class BranchConditions
{
    OCConditionsList myTrueConditions;
    OCConditionsList myFalseConditions;
    
    BranchConditions(@NotNull final OCConditionsList myTrueConditions, @NotNull final OCConditionsList myFalseConditions) {
        if (myTrueConditions == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "trueConditions", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$BranchConditions", "<init>"));
        }
        if (myFalseConditions == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "falseConditions", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$BranchConditions", "<init>"));
        }
        this.myTrueConditions = myTrueConditions;
        this.myFalseConditions = myFalseConditions;
    }
    
    @NotNull
    OCConditionsList getTrueConditions() {
        OCConditionsList myTrueConditions;
        try {
            myTrueConditions = this.myTrueConditions;
            if (myTrueConditions == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$BranchConditions", "getTrueConditions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myTrueConditions;
    }
    
    @NotNull
    OCConditionsList getFalseConditions() {
        OCConditionsList myFalseConditions;
        try {
            myFalseConditions = this.myFalseConditions;
            if (myFalseConditions == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$BranchConditions", "getFalseConditions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myFalseConditions;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
