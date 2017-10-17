// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import org.jetbrains.annotations.Contract;
import java.util.Iterator;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collections;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public abstract class CidrBuildConfigurationHelper<BC extends CidrBuildConfiguration, TARGET extends CidrBuildTarget<BC>>
{
    @NotNull
    public abstract List<TARGET> getTargets();
    
    @NotNull
    public List<BC> getConfigurations(@Nullable final TARGET target) {
        Object o = null;
        Label_0020: {
            try {
                if (target == null) {
                    final Object o2;
                    o = (o2 = Collections.emptyList());
                    break Label_0020;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            Object o2;
            o = (o2 = target.getBuildConfigurations());
            try {
                if (o2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrBuildConfigurationHelper", "getConfigurations"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return (List<BC>)o;
    }
    
    @Nullable
    public abstract CidrBuildConfiguration getDefaultConfiguration(@Nullable final TARGET p0);
    
    @NotNull
    public abstract List<? extends TARGET> getRunTargets();
    
    @Nullable
    public abstract TARGET findRunTarget(@Nullable final BuildTargetData p0);
    
    @Nullable
    public abstract TARGET findTarget(@Nullable final BuildTargetData p0);
    
    @Nullable
    public abstract BC findConfiguration(@Nullable final TARGET p0, @Nullable final String p1);
    
    @Nullable
    public TARGET getDefaultTarget() {
        return (TARGET)ContainerUtil.getFirstItem((List)this.getTargets());
    }
    
    @Nullable
    public TARGET findFirstSuitableTarget(@Nullable final String s) {
        return findFirstSuitableTarget(this.getTargets(), s);
    }
    
    @Contract("_, null -> null")
    public static <T extends CidrBuildTarget> T findFirstSuitableTarget(@NotNull final List<T> list, @Nullable final String s) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targets", "com/jetbrains/cidr/execution/CidrBuildConfigurationHelper", "findFirstSuitableTarget"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        for (final CidrBuildTarget cidrBuildTarget : list) {
            try {
                if (cidrBuildTarget.getName().equals(s)) {
                    return (T)cidrBuildTarget;
                }
                continue;
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return null;
    }
    
    @Nullable
    public BuildTargetAndConfigurationData findSimilarValidInTargets(@Nullable final BuildTargetAndConfigurationData p0, @NotNull final List<TARGET> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "targetsWithContext"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/CidrBuildConfigurationHelper"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "findSimilarValidInTargets"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/CidrBuildConfigurationHelper.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnull          76
        //    48: aload_1        
        //    49: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.target:Lcom/jetbrains/cidr/execution/BuildTargetData;
        //    52: ifnull          76
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/execution/CidrBuildConfigurationHelper.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    61: athrow         
        //    62: aload_1        
        //    63: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.configurationName:Ljava/lang/String;
        //    66: ifnonnull       82
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/execution/CidrBuildConfigurationHelper.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    75: athrow         
        //    76: aconst_null    
        //    77: areturn        
        //    78: invokestatic    com/jetbrains/cidr/execution/CidrBuildConfigurationHelper.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    81: athrow         
        //    82: aload_0        
        //    83: aload_1        
        //    84: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.target:Lcom/jetbrains/cidr/execution/BuildTargetData;
        //    87: invokevirtual   com/jetbrains/cidr/execution/CidrBuildConfigurationHelper.findTarget:(Lcom/jetbrains/cidr/execution/BuildTargetData;)Lcom/jetbrains/cidr/execution/CidrBuildTarget;
        //    90: astore_3       
        //    91: aload_3        
        //    92: ifnonnull       101
        //    95: aconst_null    
        //    96: areturn        
        //    97: invokestatic    com/jetbrains/cidr/execution/CidrBuildConfigurationHelper.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   100: athrow         
        //   101: aload_0        
        //   102: aload_0        
        //   103: aload_3        
        //   104: aload_1        
        //   105: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.configurationName:Ljava/lang/String;
        //   108: invokevirtual   com/jetbrains/cidr/execution/CidrBuildConfigurationHelper.findConfiguration:(Lcom/jetbrains/cidr/execution/CidrBuildTarget;Ljava/lang/String;)Lcom/jetbrains/cidr/execution/CidrBuildConfiguration;
        //   111: aload_3        
        //   112: aload_2        
        //   113: invokevirtual   com/jetbrains/cidr/execution/CidrBuildConfigurationHelper.findSimilarValidInTargets:(Lcom/jetbrains/cidr/execution/CidrBuildConfiguration;Lcom/jetbrains/cidr/execution/CidrBuildTarget;Ljava/util/List;)Lcom/jetbrains/cidr/execution/BuildTargetAndConfigurationData;
        //   116: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/execution/BuildTargetAndConfigurationData;Ljava/util/List<TTARGET;>;)Lcom/jetbrains/cidr/execution/BuildTargetAndConfigurationData;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     55     58     62     Ljava/lang/IllegalStateException;
        //  48     69     72     76     Ljava/lang/IllegalStateException;
        //  62     78     78     82     Ljava/lang/IllegalStateException;
        //  91     97     97     101    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0062:
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
    
    @Nullable
    public abstract BuildTargetAndConfigurationData findSimilarValidInTargets(@Nullable final BC p0, @Nullable final TARGET p1, @NotNull final List<TARGET> p2);
    
    @Contract("null, _, _ -> null")
    protected static <BC extends CidrBuildConfiguration, TARGET extends CidrBuildTarget<BC>> BuildTargetAndConfigurationData createIfSuitable(@Nullable final BC bc, @Nullable final TARGET target, @NotNull final List<TARGET> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetsWithContext", "com/jetbrains/cidr/execution/CidrBuildConfigurationHelper", "createIfSuitable"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0065: {
            try {
                if (bc == null) {
                    return null;
                }
                final List<TARGET> list2 = list;
                final CidrBuildTarget<BC> cidrBuildTarget = target;
                final boolean b = list2.contains(cidrBuildTarget);
                if (b) {
                    break Label_0065;
                }
                return null;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final List<TARGET> list2 = list;
                final CidrBuildTarget<BC> cidrBuildTarget = target;
                final boolean b = list2.contains(cidrBuildTarget);
                if (b) {
                    return new BuildTargetAndConfigurationData(target, bc);
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return null;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
