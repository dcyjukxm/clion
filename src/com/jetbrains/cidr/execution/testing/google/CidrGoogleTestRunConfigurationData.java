// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import com.jetbrains.cidr.CidrBundle;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.execution.testing.CidrTestRunConfigurationData;

public class CidrGoogleTestRunConfigurationData extends CidrTestRunConfigurationData
{
    public static final String FRAMEWORK_NAME = "GoogleTest";
    public static final String GOOGLE_TEST;
    
    public CidrGoogleTestRunConfigurationData(@NotNull final Project project) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData", "<init>"));
        }
        super(project);
    }
    
    @NotNull
    @Override
    protected String formatTestMethod() {
        String string;
        try {
            string = this.myTestSuite + "." + this.myTestName;
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData", "formatTestMethod"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b((Exception)ex);
        }
        return string;
    }
    
    @Override
    public void checkData() throws RuntimeConfigurationException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.myTestMode:Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode;
        //     4: getstatic       com/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode.PATTERN:Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo$Mode;
        //     7: if_acmpeq       41
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //    14: ifnull          41
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    23: athrow         
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.myProject:Lcom/intellij/openapi/project/Project;
        //    28: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationEditorWithLWValidation.isLWValidationOn:(Lcom/intellij/openapi/project/Project;)Z
        //    31: ifeq            46
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    40: athrow         
        //    41: return         
        //    42: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    45: athrow         
        //    46: aload_0        
        //    47: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.myProject:Lcom/intellij/openapi/project/Project;
        //    50: aload_0        
        //    51: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //    54: aload_0        
        //    55: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.myTestName:Ljava/lang/String;
        //    58: ifnonnull       69
        //    61: iconst_1       
        //    62: goto            70
        //    65: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    68: athrow         
        //    69: iconst_0       
        //    70: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.findGoogleTestSymbolsForSuiteRandomly:(Lcom/intellij/openapi/project/Project;Ljava/lang/String;Z)Ljava/util/Collection;
        //    73: astore_1       
        //    74: aload_1        
        //    75: invokeinterface java/util/Collection.isEmpty:()Z
        //    80: ifeq            111
        //    83: new             Lcom/intellij/execution/configurations/RuntimeConfigurationError;
        //    86: dup            
        //    87: ldc             "gtest.suiteNotFound"
        //    89: iconst_1       
        //    90: anewarray       Ljava/lang/Object;
        //    93: dup            
        //    94: iconst_0       
        //    95: aload_0        
        //    96: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //    99: aastore        
        //   100: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   103: invokespecial   com/intellij/execution/configurations/RuntimeConfigurationError.<init>:(Ljava/lang/String;)V
        //   106: athrow         
        //   107: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   110: athrow         
        //   111: aload_0        
        //   112: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.myTestName:Ljava/lang/String;
        //   115: ifnonnull       123
        //   118: return         
        //   119: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   122: athrow         
        //   123: new             Ljava/lang/StringBuilder;
        //   126: dup            
        //   127: invokespecial   java/lang/StringBuilder.<init>:()V
        //   130: aload_0        
        //   131: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //   134: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   137: ldc             "_"
        //   139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: aload_0        
        //   143: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.myTestName:Ljava/lang/String;
        //   146: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   149: ldc             "_Test"
        //   151: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   154: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   157: astore_2       
        //   158: new             Ljava/lang/StringBuilder;
        //   161: dup            
        //   162: invokespecial   java/lang/StringBuilder.<init>:()V
        //   165: ldc             "::gtest_case_"
        //   167: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: aload_0        
        //   171: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.myTestSuite:Ljava/lang/String;
        //   174: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   177: ldc             "_::"
        //   179: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   182: aload_0        
        //   183: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.myTestName:Ljava/lang/String;
        //   186: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   189: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   192: astore_3       
        //   193: aload_1        
        //   194: aload_2        
        //   195: aload_3        
        //   196: invokedynamic   value:(Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/openapi/util/Condition;
        //   201: invokestatic    com/intellij/util/containers/ContainerUtil.exists:(Ljava/lang/Iterable;Lcom/intellij/openapi/util/Condition;)Z
        //   204: istore          4
        //   206: iload           4
        //   208: ifne            239
        //   211: new             Lcom/intellij/execution/configurations/RuntimeConfigurationError;
        //   214: dup            
        //   215: ldc             "test.testNotFound"
        //   217: iconst_1       
        //   218: anewarray       Ljava/lang/Object;
        //   221: dup            
        //   222: iconst_0       
        //   223: aload_0        
        //   224: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.formatTestMethod:()Ljava/lang/String;
        //   227: aastore        
        //   228: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   231: invokespecial   com/intellij/execution/configurations/RuntimeConfigurationError.<init>:(Ljava/lang/String;)V
        //   234: athrow         
        //   235: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationData.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   238: athrow         
        //   239: return         
        //    Exceptions:
        //  throws com.intellij.execution.configurations.RuntimeConfigurationException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                 
        //  -----  -----  -----  -----  ---------------------------------------------------------------------
        //  0      17     20     24     Lcom/intellij/execution/configurations/RuntimeConfigurationException;
        //  10     34     37     41     Lcom/intellij/execution/configurations/RuntimeConfigurationException;
        //  24     42     42     46     Lcom/intellij/execution/configurations/RuntimeConfigurationException;
        //  46     65     65     69     Lcom/intellij/execution/configurations/RuntimeConfigurationException;
        //  74     107    107    111    Lcom/intellij/execution/configurations/RuntimeConfigurationException;
        //  111    119    119    123    Lcom/intellij/execution/configurations/RuntimeConfigurationException;
        //  206    235    235    239    Lcom/intellij/execution/configurations/RuntimeConfigurationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
        GOOGLE_TEST = CidrBundle.message("gtest.google.test", new Object[0]);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
