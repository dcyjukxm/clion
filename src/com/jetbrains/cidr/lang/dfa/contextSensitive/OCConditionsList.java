// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import java.util.HashSet;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Contract;
import java.util.Set;
import java.util.Collections;
import com.jetbrains.sourceglider.atttributes.Attribute;
import com.jetbrains.sourceglider.relations.RelationSignature;
import com.jetbrains.cidr.lang.dfa.OCNode;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
import java.util.List;

class OCConditionsList
{
    private List<Conditions> myList;
    
    OCConditionsList(@NotNull final List<Conditions> myList) {
        if (myList == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "list", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList", "<init>"));
        }
        this.myList = myList;
    }
    
    @NotNull
    List<Conditions> getConditions() {
        List<Conditions> myList;
        try {
            myList = this.myList;
            if (myList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList", "getConditions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myList;
    }
    
    public boolean isUnconditional() {
        for (final Conditions conditions : this.myList) {
            try {
                if (conditions.myUnconditionalBranch) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return false;
    }
    
    @NotNull
    public OCConditionsList merge(@NotNull final OCConditionsList p0) {
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
        //    18: ldc             "conds"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "merge"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList.isUnconditional:()Z
        //    48: ifeq            112
        //    51: aload_1        
        //    52: invokevirtual   com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList.isUnconditional:()Z
        //    55: ifeq            112
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_0        
        //    66: dup            
        //    67: ifnonnull       111
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: new             Ljava/lang/IllegalStateException;
        //    80: dup            
        //    81: ldc             "@NotNull method %s.%s must not return null"
        //    83: ldc             2
        //    85: anewarray       Ljava/lang/Object;
        //    88: dup            
        //    89: ldc             0
        //    91: ldc             "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList"
        //    93: aastore        
        //    94: dup            
        //    95: ldc             1
        //    97: ldc             "merge"
        //    99: aastore        
        //   100: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   103: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   106: athrow         
        //   107: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: areturn        
        //   112: new             Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList;
        //   115: dup            
        //   116: aload_0        
        //   117: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList.myList:Ljava/util/List;
        //   120: aload_1        
        //   121: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList.myList:Ljava/util/List;
        //   124: invokestatic    com/intellij/util/containers/ContainerUtil.concat:(Ljava/util/List;Ljava/util/List;)Ljava/util/List;
        //   127: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList.<init>:(Ljava/util/List;)V
        //   130: dup            
        //   131: ifnonnull       168
        //   134: new             Ljava/lang/IllegalStateException;
        //   137: dup            
        //   138: ldc             "@NotNull method %s.%s must not return null"
        //   140: ldc             2
        //   142: anewarray       Ljava/lang/Object;
        //   145: dup            
        //   146: ldc             0
        //   148: ldc             "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList"
        //   150: aastore        
        //   151: dup            
        //   152: ldc             1
        //   154: ldc             "merge"
        //   156: aastore        
        //   157: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   160: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   163: athrow         
        //   164: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     58     61     65     Ljava/lang/IllegalArgumentException;
        //  51     70     73     77     Ljava/lang/IllegalArgumentException;
        //  65     107    107    111    Ljava/lang/IllegalArgumentException;
        //  112    164    164    168    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    
    @NotNull
    public OCConditionsList skipEvaluation(@NotNull final BranchInfo branchInfo) {
        try {
            if (branchInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "branch", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList", "skipEvaluation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCConditionsList list;
        try {
            list = new OCConditionsList(ContainerUtil.map((Collection)this.myList, conditions -> {
                try {
                    if (branchInfo == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "branch", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList", "lambda$skipEvaluation$0"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return new Conditions(conditions, true, branchInfo);
            }));
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList", "skipEvaluation"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return list;
    }
    
    @NotNull
    public OCConditionsList combine(@NotNull final OCConditionsList list, @NotNull final BranchInfo branchInfo) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "conds", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList", "combine"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (branchInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "branch", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList", "combine"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final ArrayList<Conditions> list2 = new ArrayList<Conditions>();
        for (final Conditions conditions : this.myList) {
            for (final Conditions conditions2 : list.myList) {
                try {
                    if (conditions.myUnconditionalBranch) {
                        list2.add(conditions2);
                        continue;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    if (conditions2.myUnconditionalBranch) {
                        list2.add(conditions);
                        continue;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                list2.add(new Conditions(conditions, conditions2));
                final Condition condition = (Condition)ContainerUtil.getLastItem((List)conditions2.getConditions());
                try {
                    if (condition == null) {
                        continue;
                    }
                    condition.myBranches.add(branchInfo);
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
        }
        OCConditionsList list3;
        try {
            list3 = new OCConditionsList(list2);
            if (list3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList", "combine"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return list3;
    }
    
    public void clearBranches() {
        final Iterator<Conditions> iterator = this.myList.iterator();
        while (iterator.hasNext()) {
            final Iterator<Condition> iterator2 = iterator.next().getConditions().iterator();
            while (iterator2.hasNext()) {
                iterator2.next().myBranches.clear();
            }
        }
    }
    
    @NotNull
    public static OCConditionsList singleton(@Nullable final BranchInfo branchInfo, @Nullable final OCNode ocNode, @NotNull final RelationSignature relationSignature, @NotNull final Attribute... array) {
        try {
            if (relationSignature == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "relation", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList", "singleton"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList", "singleton"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        OCConditionsList list;
        try {
            list = new OCConditionsList(Collections.singletonList(new Conditions(branchInfo, ocNode, relationSignature, array)));
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCConditionsList", "singleton"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return list;
    }
    
    @Nullable
    @Contract("null, _ -> null")
    private static Set<Integer> a(@Nullable final Set<Integer> set, final int n) {
        try {
            if (set == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (Set<Integer>)ContainerUtil.map2Set((Collection)set, n2 -> n2 + n);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    static class BranchInfo
    {
        PsiElement myCondition;
        boolean myBranch;
        boolean myCanBeShortCutted;
        
        BranchInfo(@Nullable final PsiElement myCondition, final boolean myBranch, final boolean myCanBeShortCutted) {
            this.myCondition = myCondition;
            this.myBranch = myBranch;
            this.myCanBeShortCutted = myCanBeShortCutted;
        }
        
        @Nullable
        PsiElement getCondition() {
            return this.myCondition;
        }
        
        boolean isTrueBranch() {
            return this.myBranch;
        }
        
        boolean isCanBeShortCutted() {
            return this.myCanBeShortCutted;
        }
    }
    
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
                this.mySkipEvaluationIndices = a(conditions2.mySkipEvaluationIndices, conditions.getConditions().size());
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
                this.mySkipEvaluationIndices.addAll(a(conditions2.mySkipEvaluationIndices, conditions.getConditions().size()));
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
}
