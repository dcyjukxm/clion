// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.util.StringBuilderSpinAllocator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class BuildTargetAndConfigurationData
{
    @Nullable
    public final BuildTargetData target;
    @Nullable
    public final String configurationName;
    
    public BuildTargetAndConfigurationData(@Nullable final BuildTargetData target, @Nullable final String configurationName) {
        this.target = target;
        this.configurationName = configurationName;
    }
    
    public BuildTargetAndConfigurationData(@Nullable final String s, @Nullable final String s2, @Nullable final String s3) {
        this((s == null || s2 == null) ? null : new BuildTargetData(s, s2), s3);
    }
    
    public BuildTargetAndConfigurationData(@Nullable final CidrBuildTarget cidrBuildTarget, @Nullable final String s) {
        this((cidrBuildTarget == null) ? null : cidrBuildTarget.getProjectName(), (cidrBuildTarget == null) ? null : cidrBuildTarget.getName(), s);
    }
    
    public BuildTargetAndConfigurationData(@Nullable final CidrBuildTarget cidrBuildTarget, @Nullable final CidrBuildConfiguration cidrBuildConfiguration) {
        this(cidrBuildTarget, (cidrBuildConfiguration == null) ? null : cidrBuildConfiguration.getName());
    }
    
    public BuildTargetAndConfigurationData() {
        this(null, null, null);
    }
    
    @Override
    public String toString() {
        return this.getDisplayString();
    }
    
    @NotNull
    public String getDisplayString() {
        String formatDisplayString = null;
        Label_0044: {
            String projectName = null;
            Label_0022: {
                try {
                    if (this.target == null) {
                        projectName = null;
                        break Label_0022;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                projectName = this.target.projectName;
                try {
                    if (this.target == null) {
                        final String targetName = null;
                        break Label_0044;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            final String targetName = this.target.targetName;
            try {
                formatDisplayString = formatDisplayString(projectName, targetName, this.configurationName);
                if (formatDisplayString == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/BuildTargetAndConfigurationData", "getDisplayString"));
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return formatDisplayString;
    }
    
    @Contract("null, _ -> false")
    public static boolean checkData(@Nullable final BuildTargetAndConfigurationData buildTargetAndConfigurationData, @Nullable final BuildConfigurationProblems buildConfigurationProblems) {
        return checkData(buildTargetAndConfigurationData, buildConfigurationProblems, false);
    }
    
    @Contract("null, _, _ -> false")
    public static boolean checkData(@Nullable final BuildTargetAndConfigurationData p0, @Nullable final BuildConfigurationProblems p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          15
        //     4: aload_0        
        //     5: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.target:Lcom/jetbrains/cidr/execution/BuildTargetData;
        //     8: goto            16
        //    11: invokestatic    com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    14: athrow         
        //    15: aconst_null    
        //    16: astore_3       
        //    17: aload_0        
        //    18: ifnull          32
        //    21: aload_0        
        //    22: getfield        com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.configurationName:Ljava/lang/String;
        //    25: goto            33
        //    28: invokestatic    com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    31: athrow         
        //    32: aconst_null    
        //    33: astore          4
        //    35: aload_1        
        //    36: ifnull          59
        //    39: aload_1        
        //    40: ldc             "build.configuration.invalid"
        //    42: iconst_0       
        //    43: anewarray       Ljava/lang/Object;
        //    46: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    49: putfield        com/jetbrains/cidr/execution/BuildConfigurationProblems.title:Ljava/lang/String;
        //    52: goto            59
        //    55: invokestatic    com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    58: athrow         
        //    59: aload_3        
        //    60: ifnonnull       74
        //    63: iload_2        
        //    64: ifeq            86
        //    67: goto            74
        //    70: invokestatic    com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    73: athrow         
        //    74: aload           4
        //    76: ifnonnull       202
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    85: athrow         
        //    86: aload_1        
        //    87: ifnull          200
        //    90: goto            97
        //    93: invokestatic    com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    96: athrow         
        //    97: aload_3        
        //    98: ifnonnull       157
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   107: athrow         
        //   108: iload_2        
        //   109: ifne            157
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   118: athrow         
        //   119: aload_1        
        //   120: getfield        com/jetbrains/cidr/execution/BuildConfigurationProblems.problems:Ljava/util/List;
        //   123: ldc             "build.configuration.parameterNotSelected"
        //   125: iconst_1       
        //   126: anewarray       Ljava/lang/Object;
        //   129: dup            
        //   130: iconst_0       
        //   131: ldc             "build.configuration.target"
        //   133: iconst_0       
        //   134: anewarray       Ljava/lang/Object;
        //   137: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   140: aastore        
        //   141: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   144: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   149: pop            
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   156: athrow         
        //   157: aload           4
        //   159: ifnonnull       200
        //   162: aload_1        
        //   163: getfield        com/jetbrains/cidr/execution/BuildConfigurationProblems.problems:Ljava/util/List;
        //   166: ldc             "build.configuration.parameterNotSelected"
        //   168: iconst_1       
        //   169: anewarray       Ljava/lang/Object;
        //   172: dup            
        //   173: iconst_0       
        //   174: ldc             "build.configuration.configuration"
        //   176: iconst_0       
        //   177: anewarray       Ljava/lang/Object;
        //   180: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   183: aastore        
        //   184: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   187: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   192: pop            
        //   193: goto            200
        //   196: invokestatic    com/jetbrains/cidr/execution/BuildTargetAndConfigurationData.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   199: athrow         
        //   200: iconst_0       
        //   201: ireturn        
        //   202: iconst_1       
        //   203: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      11     11     15     Ljava/lang/IllegalStateException;
        //  17     28     28     32     Ljava/lang/IllegalStateException;
        //  35     52     55     59     Ljava/lang/IllegalStateException;
        //  59     67     70     74     Ljava/lang/IllegalStateException;
        //  63     79     82     86     Ljava/lang/IllegalStateException;
        //  74     90     93     97     Ljava/lang/IllegalStateException;
        //  86     101    104    108    Ljava/lang/IllegalStateException;
        //  97     112    115    119    Ljava/lang/IllegalStateException;
        //  108    150    153    157    Ljava/lang/IllegalStateException;
        //  157    193    196    200    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0074:
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
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final BuildTargetAndConfigurationData buildTargetAndConfigurationData = this;
                final Class<? extends BuildTargetAndConfigurationData> clazz = buildTargetAndConfigurationData.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final BuildTargetAndConfigurationData buildTargetAndConfigurationData = this;
                final Class<? extends BuildTargetAndConfigurationData> clazz = buildTargetAndConfigurationData.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        final BuildTargetAndConfigurationData buildTargetAndConfigurationData2 = (BuildTargetAndConfigurationData)o;
        Label_0127: {
            Label_0120: {
                Label_0092: {
                    Label_0079: {
                        Label_0072: {
                            try {
                                if (this.target == null) {
                                    break Label_0079;
                                }
                                final BuildTargetAndConfigurationData buildTargetAndConfigurationData3 = this;
                                final BuildTargetData buildTargetData = buildTargetAndConfigurationData3.target;
                                final BuildTargetAndConfigurationData buildTargetAndConfigurationData4 = buildTargetAndConfigurationData2;
                                final BuildTargetData buildTargetData2 = buildTargetAndConfigurationData4.target;
                                final boolean b = buildTargetData.equals(buildTargetData2);
                                if (!b) {
                                    break Label_0072;
                                }
                                break Label_0092;
                            }
                            catch (IllegalStateException ex4) {
                                throw a(ex4);
                            }
                            try {
                                final BuildTargetAndConfigurationData buildTargetAndConfigurationData3 = this;
                                final BuildTargetData buildTargetData = buildTargetAndConfigurationData3.target;
                                final BuildTargetAndConfigurationData buildTargetAndConfigurationData4 = buildTargetAndConfigurationData2;
                                final BuildTargetData buildTargetData2 = buildTargetAndConfigurationData4.target;
                                final boolean b = buildTargetData.equals(buildTargetData2);
                                if (!b) {
                                    return false;
                                }
                                break Label_0092;
                            }
                            catch (IllegalStateException ex5) {
                                throw a(ex5);
                            }
                        }
                        try {
                            if (buildTargetAndConfigurationData2.target != null) {
                                return false;
                            }
                        }
                        catch (IllegalStateException ex6) {
                            throw a(ex6);
                        }
                    }
                    try {
                        if (this.configurationName == null) {
                            break Label_0127;
                        }
                        final BuildTargetAndConfigurationData buildTargetAndConfigurationData5 = this;
                        final String s = buildTargetAndConfigurationData5.configurationName;
                        final BuildTargetAndConfigurationData buildTargetAndConfigurationData6 = buildTargetAndConfigurationData2;
                        final String s2 = buildTargetAndConfigurationData6.configurationName;
                        final boolean b2 = s.equals(s2);
                        if (!b2) {
                            break Label_0120;
                        }
                        return true;
                    }
                    catch (IllegalStateException ex7) {
                        throw a(ex7);
                    }
                }
                try {
                    final BuildTargetAndConfigurationData buildTargetAndConfigurationData5 = this;
                    final String s = buildTargetAndConfigurationData5.configurationName;
                    final BuildTargetAndConfigurationData buildTargetAndConfigurationData6 = buildTargetAndConfigurationData2;
                    final String s2 = buildTargetAndConfigurationData6.configurationName;
                    final boolean b2 = s.equals(s2);
                    if (!b2) {
                        return false;
                    }
                    return true;
                }
                catch (IllegalStateException ex8) {
                    throw a(ex8);
                }
            }
            try {
                if (buildTargetAndConfigurationData2.configurationName != null) {
                    return false;
                }
            }
            catch (IllegalStateException ex9) {
                throw a(ex9);
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        Label_0022: {
            try {
                if (this.target != null) {
                    hashCode = this.target.hashCode();
                    break Label_0022;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            hashCode = 0;
        }
        final int n = hashCode;
        int n2;
        try {
            n2 = 31 * n;
            if (this.configurationName != null) {
                final int hashCode2 = this.configurationName.hashCode();
                return n2 + hashCode2;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final int hashCode2 = 0;
        return n2 + hashCode2;
    }
    
    @NotNull
    public static String formatDisplayString(@Nullable final String s, @Nullable final String s2, @Nullable final String s3) {
        final StringBuilder alloc = StringBuilderSpinAllocator.alloc();
        try {
            final String string = alloc.append(formatDisplayString(s, s2)).append(" - ").append(s3).toString();
            String s4;
            try {
                StringBuilderSpinAllocator.dispose(alloc);
                s4 = string;
                if (s4 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/BuildTargetAndConfigurationData", "formatDisplayString"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return s4;
        }
        finally {
            StringBuilderSpinAllocator.dispose(alloc);
        }
    }
    
    @NotNull
    public static String formatDisplayString(@Nullable final String s, @Nullable final String s2) {
        final StringBuilder alloc = StringBuilderSpinAllocator.alloc();
        try {
            final String string = alloc.append(s2).append(" (").append(s).append(")").toString();
            String s3;
            try {
                StringBuilderSpinAllocator.dispose(alloc);
                s3 = string;
                if (s3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/BuildTargetAndConfigurationData", "formatDisplayString"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return s3;
        }
        finally {
            StringBuilderSpinAllocator.dispose(alloc);
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
