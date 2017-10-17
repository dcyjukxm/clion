// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import java.util.Collection;
import java.util.HashSet;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collections;
import com.jetbrains.sourceglider.atttributes.Attribute;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.sourceglider.relations.RelationSignature;
import com.jetbrains.cidr.lang.dfa.OCNode;
import org.jetbrains.annotations.Nullable;
import java.util.Set;
import java.util.List;

static class Conditions
{
    private List<Condition> myConditions;
    private Set<Integer> mySkipEvaluationIndices;
    private boolean myUnconditionalBranch;
    
    Conditions(@Nullable final BranchInfo branchInfo, @Nullable final OCNode ocNode, @NotNull final RelationSignature relationSignature, @NotNull final Attribute... array) {
        if (relationSignature == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "relation", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList$Conditions", "<init>"));
        }
        if (array == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList$Conditions", "<init>"));
        }
        this.myConditions = Collections.singletonList(new Condition(branchInfo, ocNode, relationSignature, array));
        this.myUnconditionalBranch = (array.length == 0);
    }
    
    Conditions(@NotNull final Conditions conditions, @NotNull final Conditions conditions2) {
        if (conditions == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "conds1", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList$Conditions", "<init>"));
        }
        if (conditions2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "conds2", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList$Conditions", "<init>"));
        }
        this.myConditions = (List<Condition>)ContainerUtil.concat((List)conditions.myConditions, (List)conditions2.myConditions);
        if (conditions.mySkipEvaluationIndices == null) {
            this.mySkipEvaluationIndices = (Set<Integer>)OCConditionsList.access$000(conditions2.mySkipEvaluationIndices, conditions.getConditions().size());
        }
        else {
            try {
                if (conditions2.mySkipEvaluationIndices == null) {
                    this.mySkipEvaluationIndices = conditions.mySkipEvaluationIndices;
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            (this.mySkipEvaluationIndices = new HashSet<Integer>()).addAll(conditions.mySkipEvaluationIndices);
            this.mySkipEvaluationIndices.addAll(OCConditionsList.access$000(conditions2.mySkipEvaluationIndices, conditions.getConditions().size()));
        }
    }
    
    Conditions(@NotNull final Conditions conditions, final boolean b, @NotNull final BranchInfo branchInfo) {
        if (conditions == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "conds", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList$Conditions", "<init>"));
        }
        if (branchInfo == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "branch", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList$Conditions", "<init>"));
        }
        this.myConditions = conditions.myConditions;
        this.myUnconditionalBranch = conditions.myUnconditionalBranch;
        this.mySkipEvaluationIndices = (b ? new HashSet<Integer>(Collections.singleton(this.myConditions.size())) : null);
        final Condition condition = (Condition)ContainerUtil.getLastItem((List)this.myConditions);
        try {
            if (condition != null) {
                condition.myBranches.add(branchInfo);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    @NotNull
    List<Condition> getConditions() {
        List<Condition> myConditions;
        try {
            myConditions = this.myConditions;
            if (myConditions == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList$Conditions", "getConditions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myConditions;
    }
    
    public Set<Integer> getSkipEvaluationIndices() {
        return this.mySkipEvaluationIndices;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
