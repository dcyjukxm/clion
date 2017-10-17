// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import com.intellij.openapi.util.WriteExternalException;
import com.intellij.openapi.util.InvalidDataException;
import org.jdom.Element;
import com.intellij.openapi.util.text.StringUtil;
import java.lang.reflect.Modifier;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.JDOMExternalizable;

public abstract class CidrTestRunConfigurationData implements JDOMExternalizable, Cloneable
{
    @Nullable
    public String myTestSuite;
    @Nullable
    public String myTestName;
    @Nullable
    protected String myTestPattern;
    @NotNull
    protected CidrScopeInfo.Mode myTestMode;
    @NotNull
    protected Project myProject;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public abstract void checkData() throws RuntimeConfigurationException;
    
    public CidrTestRunConfigurationData(@NotNull final Project myProject) {
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData", "<init>"));
        }
        this.myTestMode = CidrScopeInfo.Mode.SUITE_TEST;
        this.myProject = myProject;
        if (!CidrTestRunConfigurationData.$assertionsDisabled) {
            try {
                if (isPotentiallyThisCapturing(this.getClass())) {
                    throw new AssertionError((Object)"TestData must not be initialized from an inner/anonymous (non-static) class. Otherwise it will leak in clone() and cause unexpected behavior");
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
        }
    }
    
    static boolean isPotentiallyThisCapturing(final Class<?> clazz) {
        Label_0024: {
            try {
                if (clazz.getEnclosingClass() == null) {
                    return false;
                }
                final Class clazz2 = clazz;
                final int n = clazz2.getModifiers();
                final boolean b = Modifier.isStatic(n);
                if (!b) {
                    break Label_0024;
                }
                return false;
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            try {
                final Class clazz2 = clazz;
                final int n = clazz2.getModifiers();
                final boolean b = Modifier.isStatic(n);
                if (!b) {
                    return true;
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    public String getTestSuite() {
        return this.myTestSuite;
    }
    
    public void setTestSuite(@Nullable final String s) {
        this.myTestSuite = StringUtil.nullize(s, true);
    }
    
    @Nullable
    public String getTestName() {
        return this.myTestName;
    }
    
    public void setTestName(@Nullable final String s) {
        this.myTestName = StringUtil.nullize(s, true);
    }
    
    @Nullable
    public String getTestPattern() {
        return this.myTestPattern;
    }
    
    public void setTestPattern(@Nullable final String myTestPattern) {
        this.myTestPattern = myTestPattern;
    }
    
    @NotNull
    public CidrScopeInfo.Mode getTestMode() {
        CidrScopeInfo.Mode myTestMode;
        try {
            myTestMode = this.myTestMode;
            if (myTestMode == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData", "getTestMode"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return myTestMode;
    }
    
    public void setTestMode(@NotNull final CidrScopeInfo.Mode myTestMode) {
        try {
            if (myTestMode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "mode", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData", "setTestMode"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        this.myTestMode = myTestMode;
    }
    
    @NotNull
    public String suggestedName(@Nullable final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //     4: ifnull          71
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.myTestName:Ljava/lang/String;
        //    11: ifnull          71
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    20: athrow         
        //    21: aload_0        
        //    22: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.formatTestMethod:()Ljava/lang/String;
        //    25: dup            
        //    26: ifnonnull       70
        //    29: goto            36
        //    32: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    35: athrow         
        //    36: new             Ljava/lang/IllegalStateException;
        //    39: dup            
        //    40: ldc             "@NotNull method %s.%s must not return null"
        //    42: ldc             2
        //    44: anewarray       Ljava/lang/Object;
        //    47: dup            
        //    48: ldc             0
        //    50: ldc             "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData"
        //    52: aastore        
        //    53: dup            
        //    54: ldc             1
        //    56: ldc             "suggestedName"
        //    58: aastore        
        //    59: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    62: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    65: athrow         
        //    66: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    69: athrow         
        //    70: areturn        
        //    71: aload_0        
        //    72: getfield        com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.myTestMode:Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode;
        //    75: getstatic       com/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode.PATTERN:Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode;
        //    78: if_acmpne       129
        //    81: ldc             "Pattern"
        //    83: dup            
        //    84: ifnonnull       128
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    93: athrow         
        //    94: new             Ljava/lang/IllegalStateException;
        //    97: dup            
        //    98: ldc             "@NotNull method %s.%s must not return null"
        //   100: ldc             2
        //   102: anewarray       Ljava/lang/Object;
        //   105: dup            
        //   106: ldc             0
        //   108: ldc             "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData"
        //   110: aastore        
        //   111: dup            
        //   112: ldc             1
        //   114: ldc             "suggestedName"
        //   116: aastore        
        //   117: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   120: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   123: athrow         
        //   124: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   127: athrow         
        //   128: areturn        
        //   129: aload_0        
        //   130: getfield        com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //   133: ifnull          147
        //   136: aload_0        
        //   137: getfield        com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //   140: goto            148
        //   143: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   146: athrow         
        //   147: aload_1        
        //   148: astore_2       
        //   149: aload_2        
        //   150: ifnonnull       169
        //   153: ldc             "test.defaultName.allTests"
        //   155: iconst_0       
        //   156: anewarray       Ljava/lang/Object;
        //   159: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   162: goto            182
        //   165: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   168: athrow         
        //   169: ldc             "test.defaultName.allTestsIn"
        //   171: iconst_1       
        //   172: anewarray       Ljava/lang/Object;
        //   175: dup            
        //   176: iconst_0       
        //   177: aload_2        
        //   178: aastore        
        //   179: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   182: dup            
        //   183: ifnonnull       220
        //   186: new             Ljava/lang/IllegalStateException;
        //   189: dup            
        //   190: ldc             "@NotNull method %s.%s must not return null"
        //   192: ldc             2
        //   194: anewarray       Ljava/lang/Object;
        //   197: dup            
        //   198: ldc             0
        //   200: ldc             "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData"
        //   202: aastore        
        //   203: dup            
        //   204: ldc             1
        //   206: ldc             "suggestedName"
        //   208: aastore        
        //   209: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   212: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   215: athrow         
        //   216: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   219: athrow         
        //   220: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      14     17     21     Ljava/lang/RuntimeException;
        //  7      29     32     36     Ljava/lang/RuntimeException;
        //  21     66     66     70     Ljava/lang/RuntimeException;
        //  71     87     90     94     Ljava/lang/RuntimeException;
        //  81     124    124    128    Ljava/lang/RuntimeException;
        //  129    143    143    147    Ljava/lang/RuntimeException;
        //  149    165    165    169    Ljava/lang/RuntimeException;
        //  182    216    216    220    Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    protected abstract String formatTestMethod();
    
    public void readExternal(final Element element) throws InvalidDataException {
        this.setTestSuite(element.getAttributeValue("TEST_CLASS"));
        this.setTestName(element.getAttributeValue("TEST_METHOD"));
        this.setTestPattern(element.getAttributeValue("TEST_PATTERN"));
        final String attributeValue = element.getAttributeValue("TEST_MODE");
        CidrScopeInfo.Mode testMode = null;
        Label_0071: {
            Label_0058: {
                try {
                    if (attributeValue == null) {
                        break Label_0058;
                    }
                    final String s = attributeValue;
                    final String s2 = "SUITE_TEST";
                    final boolean b = s.equals(s2);
                    if (b) {
                        break Label_0058;
                    }
                    break Label_0058;
                }
                catch (InvalidDataException ex) {
                    throw b((RuntimeException)ex);
                }
                try {
                    final String s = attributeValue;
                    final String s2 = "SUITE_TEST";
                    final boolean b = s.equals(s2);
                    if (b) {
                        testMode = CidrScopeInfo.Mode.SUITE_TEST;
                        break Label_0071;
                    }
                }
                catch (InvalidDataException ex2) {
                    throw b((RuntimeException)ex2);
                }
            }
            testMode = CidrScopeInfo.Mode.PATTERN;
        }
        this.setTestMode(testMode);
    }
    
    public void writeExternal(final Element p0) throws WriteExternalException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData$1.$SwitchMap$com$jetbrains$cidr$execution$testing$CidrScopeInfo$Mode:[I
        //     3: aload_0        
        //     4: getfield        com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.myTestMode:Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode;
        //     7: invokevirtual   com/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode.ordinal:()I
        //    10: iaload         
        //    11: lookupswitch {
        //                1: 36
        //                2: 93
        //          default: 118
        //        }
        //    36: aload_0        
        //    37: getfield        com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //    40: ifnull          118
        //    43: goto            50
        //    46: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    49: athrow         
        //    50: aload_1        
        //    51: ldc             "TEST_CLASS"
        //    53: aload_0        
        //    54: getfield        com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //    57: invokevirtual   org/jdom/Element.setAttribute:(Ljava/lang/String;Ljava/lang/String;)Lorg/jdom/Element;
        //    60: pop            
        //    61: aload_0        
        //    62: getfield        com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.myTestName:Ljava/lang/String;
        //    65: ifnull          118
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    74: athrow         
        //    75: aload_1        
        //    76: ldc             "TEST_METHOD"
        //    78: aload_0        
        //    79: getfield        com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.myTestName:Ljava/lang/String;
        //    82: invokevirtual   org/jdom/Element.setAttribute:(Ljava/lang/String;Ljava/lang/String;)Lorg/jdom/Element;
        //    85: pop            
        //    86: goto            118
        //    89: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    92: athrow         
        //    93: aload_0        
        //    94: getfield        com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.myTestPattern:Ljava/lang/String;
        //    97: ifnull          118
        //   100: aload_1        
        //   101: ldc             "TEST_PATTERN"
        //   103: aload_0        
        //   104: getfield        com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.myTestPattern:Ljava/lang/String;
        //   107: invokevirtual   org/jdom/Element.setAttribute:(Ljava/lang/String;Ljava/lang/String;)Lorg/jdom/Element;
        //   110: pop            
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   117: athrow         
        //   118: aload_1        
        //   119: ldc             "TEST_MODE"
        //   121: aload_0        
        //   122: getfield        com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.myTestMode:Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode;
        //   125: invokevirtual   com/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode.name:()Ljava/lang/String;
        //   128: invokevirtual   org/jdom/Element.setAttribute:(Ljava/lang/String;Ljava/lang/String;)Lorg/jdom/Element;
        //   131: pop            
        //   132: return         
        //    Exceptions:
        //  throws com.intellij.openapi.util.WriteExternalException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                              
        //  -----  -----  -----  -----  --------------------------------------------------
        //  0      43     46     50     Lcom/intellij/openapi/util/WriteExternalException;
        //  36     68     71     75     Lcom/intellij/openapi/util/WriteExternalException;
        //  50     89     89     93     Lcom/intellij/openapi/util/WriteExternalException;
        //  93     111    114    118    Lcom/intellij/openapi/util/WriteExternalException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0036:
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
    
    public CidrTestRunConfigurationData clone() {
        try {
            final CidrTestRunConfigurationData cidrTestRunConfigurationData = (CidrTestRunConfigurationData)super.clone();
            cidrTestRunConfigurationData.myTestSuite = this.myTestSuite;
            cidrTestRunConfigurationData.myTestName = this.myTestName;
            cidrTestRunConfigurationData.myTestPattern = this.myTestPattern;
            cidrTestRunConfigurationData.myTestMode = this.myTestMode;
            cidrTestRunConfigurationData.myProject = this.myProject;
            return cidrTestRunConfigurationData;
        }
        catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public boolean equalTo(final CidrScopeInfo p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.getTestSuite:()Ljava/lang/String;
        //     4: aload_1        
        //     5: invokevirtual   com/jetbrains/cidr/execution/testing/CidrScopeInfo.getSuite:()Ljava/lang/String;
        //     8: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //    11: ifeq            85
        //    14: aload_0        
        //    15: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.getTestName:()Ljava/lang/String;
        //    18: aload_1        
        //    19: invokevirtual   com/jetbrains/cidr/execution/testing/CidrScopeInfo.getTest:()Ljava/lang/String;
        //    22: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //    25: ifeq            85
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    34: athrow         
        //    35: aload_0        
        //    36: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.getTestPattern:()Ljava/lang/String;
        //    39: aload_1        
        //    40: invokevirtual   com/jetbrains/cidr/execution/testing/CidrScopeInfo.getPattern:()Ljava/lang/String;
        //    43: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //    46: ifeq            85
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    55: athrow         
        //    56: aload_0        
        //    57: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.getTestMode:()Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode;
        //    60: aload_1        
        //    61: invokevirtual   com/jetbrains/cidr/execution/testing/CidrScopeInfo.getMode:()Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode;
        //    64: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    67: ifeq            85
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    76: athrow         
        //    77: iconst_1       
        //    78: goto            86
        //    81: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationData.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    84: athrow         
        //    85: iconst_0       
        //    86: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      28     31     35     Ljava/lang/RuntimeException;
        //  14     49     52     56     Ljava/lang/RuntimeException;
        //  35     70     73     77     Ljava/lang/RuntimeException;
        //  56     81     81     85     Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0035:
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
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!CidrTestRunConfigurationData.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
