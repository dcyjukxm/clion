// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.dfa.OCNode;
import com.jetbrains.sourceglider.atttributes.Attribute;
import com.jetbrains.sourceglider.relations.RelationSignature;
import java.util.List;

static class Condition
{
    List<BranchInfo> myBranches;
    RelationSignature myRelation;
    Attribute[] myAttributes;
    OCNode myFakeNode;
    
    Condition(@Nullable final BranchInfo branchInfo, @Nullable final OCNode myFakeNode, @NotNull final RelationSignature myRelation, @NotNull final Attribute[] myAttributes) {
        if (myRelation == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "relation", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList$Condition", "<init>"));
        }
        if (myAttributes == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList$Condition", "<init>"));
        }
        this.myBranches = new ArrayList<BranchInfo>();
        if (branchInfo != null) {
            this.myBranches.add(branchInfo);
        }
        this.myRelation = myRelation;
        this.myAttributes = myAttributes;
        this.myFakeNode = myFakeNode;
    }
    
    @NotNull
    List<BranchInfo> getBranches() {
        List<BranchInfo> myBranches;
        try {
            myBranches = this.myBranches;
            if (myBranches == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList$Condition", "getBranches"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myBranches;
    }
    
    @NotNull
    RelationSignature getRelation() {
        RelationSignature myRelation;
        try {
            myRelation = this.myRelation;
            if (myRelation == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList$Condition", "getRelation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myRelation;
    }
    
    @NotNull
    Attribute[] getAttributes() {
        Attribute[] myAttributes;
        try {
            myAttributes = this.myAttributes;
            if (myAttributes == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList$Condition", "getAttributes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myAttributes;
    }
    
    @Nullable
    OCNode getFakeNode() {
        return this.myFakeNode;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
