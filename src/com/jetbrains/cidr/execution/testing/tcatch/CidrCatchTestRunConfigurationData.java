// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import com.jetbrains.cidr.CidrBundle;
import java.util.Set;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.execution.testing.CidrTestRunConfigurationData;

public class CidrCatchTestRunConfigurationData extends CidrTestRunConfigurationData
{
    public static final String FRAMEWORK_NAME;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public CidrCatchTestRunConfigurationData(@NotNull final Project project) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData", "<init>"));
        }
        super(project);
    }
    
    @NotNull
    @Override
    protected String formatTestMethod() {
        String myTestName = null;
        Label_0032: {
            Label_0020: {
                try {
                    if (CidrCatchTestRunConfigurationData.$assertionsDisabled) {
                        break Label_0032;
                    }
                    final CidrCatchTestRunConfigurationData cidrCatchTestRunConfigurationData = this;
                    final String s = cidrCatchTestRunConfigurationData.myTestName;
                    if (s == null) {
                        break Label_0020;
                    }
                    break Label_0032;
                }
                catch (IllegalArgumentException ex) {
                    throw b((Exception)ex);
                }
                try {
                    final CidrCatchTestRunConfigurationData cidrCatchTestRunConfigurationData = this;
                    final String s = cidrCatchTestRunConfigurationData.myTestName;
                    if (s == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b((Exception)ex2);
                }
            }
            try {
                myTestName = this.myTestName;
                if (myTestName == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData", "formatTestMethod"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b((Exception)ex3);
            }
        }
        return myTestName;
    }
    
    @Override
    public void checkData() throws RuntimeConfigurationException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.myTestMode:Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode;
        //     4: getstatic       com/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode.PATTERN:Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode;
        //     7: if_acmpeq       27
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.myProject:Lcom/intellij/openapi/project/Project;
        //    14: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationEditorWithLWValidation.isLWValidationOn:(Lcom/intellij/openapi/project/Project;)Z
        //    17: ifeq            32
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    26: athrow         
        //    27: return         
        //    28: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    31: athrow         
        //    32: aload_0        
        //    33: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.myTestName:Ljava/lang/String;
        //    36: ifnonnull       58
        //    39: aload_0        
        //    40: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //    43: ifnonnull       58
        //    46: goto            53
        //    49: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    52: athrow         
        //    53: return         
        //    54: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    57: athrow         
        //    58: aload_0        
        //    59: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //    62: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil.splitTags:(Ljava/lang/String;)Ljava/util/Set;
        //    65: astore_1       
        //    66: aload_1        
        //    67: invokeinterface java/util/Set.size:()I
        //    72: istore_2       
        //    73: aload_0        
        //    74: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //    77: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil.isValidStringOfTags:(Ljava/lang/String;)Z
        //    80: ifne            120
        //    83: new             Lcom/intellij/execution/configurations/RuntimeConfigurationError;
        //    86: dup            
        //    87: ldc             "catch.notValidTags"
        //    89: iconst_2       
        //    90: anewarray       Ljava/lang/Object;
        //    93: dup            
        //    94: iconst_0       
        //    95: iload_2        
        //    96: iconst_1       
        //    97: iadd           
        //    98: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   101: aastore        
        //   102: dup            
        //   103: iconst_1       
        //   104: aload_0        
        //   105: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //   108: aastore        
        //   109: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   112: invokespecial   com/intellij/execution/configurations/RuntimeConfigurationError.<init>:(Ljava/lang/String;)V
        //   115: athrow         
        //   116: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   119: athrow         
        //   120: iconst_1       
        //   121: newarray        Z
        //   123: dup            
        //   124: iconst_0       
        //   125: iconst_0       
        //   126: bastore        
        //   127: astore_3       
        //   128: aload_0        
        //   129: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.myProject:Lcom/intellij/openapi/project/Project;
        //   132: aload_0        
        //   133: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.myProject:Lcom/intellij/openapi/project/Project;
        //   136: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.getExplicitlySpecifiedProjectSourceFiles:(Lcom/intellij/openapi/project/Project;)Ljava/util/Collection;
        //   139: aconst_null    
        //   140: aload_0        
        //   141: aload_1        
        //   142: aload_3        
        //   143: invokedynamic   accept:(Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData;Ljava/util/Set;[Z)Ljava/util/function/Consumer;
        //   148: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil.consumeCatchTestSymbols:(Lcom/intellij/openapi/project/Project;Ljava/util/Collection;Lcom/intellij/psi/search/SearchScope;Ljava/util/function/Consumer;)V
        //   151: aload_3        
        //   152: iconst_0       
        //   153: baload         
        //   154: ifne            265
        //   157: new             Lcom/intellij/execution/configurations/RuntimeConfigurationError;
        //   160: dup            
        //   161: aload_0        
        //   162: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.myTestName:Ljava/lang/String;
        //   165: ifnonnull       205
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   174: athrow         
        //   175: ldc             "catch.noTestsWithTags"
        //   177: iconst_2       
        //   178: anewarray       Ljava/lang/Object;
        //   181: dup            
        //   182: iconst_0       
        //   183: iload_2        
        //   184: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   187: aastore        
        //   188: dup            
        //   189: iconst_1       
        //   190: aload_0        
        //   191: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //   194: aastore        
        //   195: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   198: goto            261
        //   201: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   204: athrow         
        //   205: aload_0        
        //   206: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //   209: ifnonnull       231
        //   212: ldc             "test.testNotFound"
        //   214: iconst_1       
        //   215: anewarray       Ljava/lang/Object;
        //   218: dup            
        //   219: iconst_0       
        //   220: aload_0        
        //   221: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.myTestName:Ljava/lang/String;
        //   224: aastore        
        //   225: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   228: goto            261
        //   231: ldc             "catch.testNotFound"
        //   233: iconst_3       
        //   234: anewarray       Ljava/lang/Object;
        //   237: dup            
        //   238: iconst_0       
        //   239: aload_0        
        //   240: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.myTestName:Ljava/lang/String;
        //   243: aastore        
        //   244: dup            
        //   245: iconst_1       
        //   246: iload_2        
        //   247: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   250: aastore        
        //   251: dup            
        //   252: iconst_2       
        //   253: aload_0        
        //   254: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //   257: aastore        
        //   258: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   261: invokespecial   com/intellij/execution/configurations/RuntimeConfigurationError.<init>:(Ljava/lang/String;)V
        //   264: athrow         
        //   265: return         
        //    Exceptions:
        //  throws com.intellij.execution.configurations.RuntimeConfigurationException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                 
        //  -----  -----  -----  -----  ---------------------------------------------------------------------
        //  0      20     23     27     Lcom/intellij/execution/configurations/RuntimeConfigurationException;
        //  10     28     28     32     Lcom/intellij/execution/configurations/RuntimeConfigurationException;
        //  32     46     49     53     Lcom/intellij/execution/configurations/RuntimeConfigurationException;
        //  39     54     54     58     Lcom/intellij/execution/configurations/RuntimeConfigurationException;
        //  73     116    116    120    Lcom/intellij/execution/configurations/RuntimeConfigurationException;
        //  128    168    171    175    Lcom/intellij/execution/configurations/RuntimeConfigurationException;
        //  157    201    201    205    Lcom/intellij/execution/configurations/RuntimeConfigurationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0034_1:
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
                if (!CidrCatchTestRunConfigurationData.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b((Exception)ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        FRAMEWORK_NAME = CidrBundle.message("catch.test", new Object[0]);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
