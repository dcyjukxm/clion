// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import java.util.function.Consumer;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.execution.Location;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.execution.testing.CidrTestScopeElement;
import com.jetbrains.cidr.execution.testing.CidrScopeInfo;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import org.jetbrains.annotations.Contract;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.ConfigurationType;
import com.jetbrains.cidr.execution.testing.CidrTestRunConfigurationProducer;
import com.jetbrains.cidr.execution.CidrRunConfiguration;
import com.jetbrains.cidr.execution.CidrBuildTarget;
import com.jetbrains.cidr.execution.CidrBuildConfiguration;

public abstract class CidrCatchTestRunConfigurationProducer<BC extends CidrBuildConfiguration, TARGET extends CidrBuildTarget<BC>, CONFIGURATION extends CidrRunConfiguration<BC, TARGET>> extends CidrTestRunConfigurationProducer<BC, TARGET, CONFIGURATION, CidrCatchTestObject>
{
    static final String CATCH_NAME_PREFIX = "____C_A_T_C_H____T_E_S_T____";
    static final String TEST_METHOD_NAME = "test";
    
    protected CidrCatchTestRunConfigurationProducer(final ConfigurationType configurationType) {
        super(configurationType, CidrCatchTestFramework.class);
    }
    
    @Contract(pure = true)
    @NotNull
    @Override
    protected PsiElement getElement(@NotNull final CidrCatchTestObject cidrCatchTestObject) {
        try {
            if (cidrCatchTestObject == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testObj", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer", "getElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Object o = null;
        Label_0072: {
            try {
                if (cidrCatchTestObject.getKind() == CidrCatchTestObject.KIND.TEST) {
                    final Object o2;
                    o = (o2 = cidrCatchTestObject.getTestInfo().getDeclarator());
                    break Label_0072;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            Object o2;
            o = (o2 = cidrCatchTestObject.getFile());
            try {
                if (o2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer", "getElement"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return (PsiElement)o;
    }
    
    @Nullable
    @Override
    protected abstract TARGET getTargetFromResolveConfiguration(@NotNull final OCResolveConfiguration p0);
    
    @Nullable
    @Override
    protected CidrScopeInfo determineScope(@NotNull final List<CidrCatchTestObject> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testObj", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer", "determineScope"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.determineScope(list, ",");
    }
    
    @Override
    protected boolean isTestTarget(@NotNull final TARGET target) {
        try {
            if (target == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer", "isTestTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return target.isExecutable();
    }
    
    @NotNull
    @Override
    protected CidrTestScopeElement createScopeElement(@NotNull final CidrScopeInfo cidrScopeInfo) {
        try {
            if (cidrScopeInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer", "createScopeElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        CidrCatchTestScopeElement cidrCatchTestScopeElement;
        try {
            cidrCatchTestScopeElement = new CidrCatchTestScopeElement(cidrScopeInfo.getSuite(), cidrScopeInfo.getTest());
            if (cidrCatchTestScopeElement == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer", "createScopeElement"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return cidrCatchTestScopeElement;
    }
    
    @Nullable
    @Override
    protected CidrScopeInfo determineScope(@NotNull final CidrCatchTestObject cidrCatchTestObject) {
        try {
            if (cidrCatchTestObject == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "object", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer", "determineScope"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0071: {
            try {
                if (cidrCatchTestObject.getKind() != CidrCatchTestObject.KIND.FILE) {
                    return cidrCatchTestObject.getScope();
                }
                final CidrCatchTestObject cidrCatchTestObject2 = cidrCatchTestObject;
                final OCFile ocFile = cidrCatchTestObject2.getFile();
                final boolean b = CidrCatchTestUtil.fileHasTest(ocFile);
                if (!b) {
                    break Label_0071;
                }
                return cidrCatchTestObject.getScope();
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final CidrCatchTestObject cidrCatchTestObject2 = cidrCatchTestObject;
                final OCFile ocFile = cidrCatchTestObject2.getFile();
                final boolean b = CidrCatchTestUtil.fileHasTest(ocFile);
                if (!b) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return cidrCatchTestObject.getScope();
    }
    
    @Nullable
    @Override
    protected CidrCatchTestObject findTestObject(@NotNull final Location location) {
        try {
            if (location == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer", "findTestObject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (location instanceof CidrCatchTestLocation) {
                return new CidrCatchTestObject(((CidrCatchTestLocation)location).getTestInfo());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return findTestObject(location.getPsiElement(), true);
    }
    
    @Nullable
    static CidrCatchTestObject findTestObject(@NotNull PsiElement parent, final boolean b) {
        try {
            if (parent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer", "findTestObject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        while (true) {
            try {
                if (parent == null || a(parent, b)) {
                    break;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            parent = parent.getParent();
        }
        Label_0152: {
            try {
                if (parent == null) {
                    return null;
                }
                if (!(parent instanceof OCFile)) {
                    break Label_0152;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            final VirtualFile virtualFile = ((OCFile)parent).getVirtualFile();
            try {
                if (virtualFile == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            return new CidrCatchTestObject("[#" + virtualFile.getNameWithoutExtension() + "]", (OCFile)parent);
        }
        if (parent instanceof OCMacroCall) {
            final PsiElement firstExpansionLeaf = ((OCMacroCall)parent).getFirstExpansionLeaf();
            final PsiElement lastExpansionLeaf = ((OCMacroCall)parent).getLastExpansionLeaf();
            PsiElement nextLeaf = firstExpansionLeaf;
            while (true) {
                try {
                    if (nextLeaf == lastExpansionLeaf || nextLeaf == null) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
                final CidrCatchTestObject testObject = findTestObject(nextLeaf, false);
                try {
                    if (testObject != null) {
                        return testObject;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
                nextLeaf = PsiTreeUtil.nextLeaf(nextLeaf);
            }
        }
        final Ref ref = new Ref();
        final String a = a(parent);
        final Consumer<CidrCatchTestCache> consumer = cidrCatchTestCache -> ref.set((Object)new CidrCatchTestObject(cidrCatchTestCache));
        try {
            if (a == null) {
                parent.accept((PsiElementVisitor)CidrCatchTestUtil.getTestSymbolExtractorVisitor(consumer));
                return (CidrCatchTestObject)ref.get();
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        parent.getContainingFile().accept((PsiElementVisitor)CidrCatchTestUtil.getTestSymbolExtractorLocalVisitor(a, consumer));
        return (CidrCatchTestObject)ref.get();
    }
    
    private static boolean a(@NotNull final PsiElement p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isTestCandidate"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: iload_1        
        //    45: ifeq            62
        //    48: aload_0        
        //    49: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    52: ifne            142
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aload_0        
        //    63: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //    66: ifne            90
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload_0        
        //    77: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil.isTestMacroCandidate:(Lcom/intellij/psi/PsiElement;)Z
        //    80: ifeq            128
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: aload_0        
        //    91: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    96: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    99: ifne            142
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: aload_0        
        //   110: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   115: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
        //   118: ifne            142
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   127: athrow         
        //   128: aload_0        
        //   129: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer.a:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   132: ifnull          150
        //   135: goto            142
        //   138: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   141: athrow         
        //   142: iconst_1       
        //   143: goto            151
        //   146: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: iconst_0       
        //   151: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     55     58     62     Ljava/lang/IllegalArgumentException;
        //  48     69     72     76     Ljava/lang/IllegalArgumentException;
        //  62     83     86     90     Ljava/lang/IllegalArgumentException;
        //  76     102    105    109    Ljava/lang/IllegalArgumentException;
        //  90     121    124    128    Ljava/lang/IllegalArgumentException;
        //  109    135    138    142    Ljava/lang/IllegalArgumentException;
        //  128    146    146    150    Ljava/lang/IllegalArgumentException;
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
    private static String a(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer", "getTestBodyOwnerNameIfCan"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        if (psiElement instanceof OCFunctionDefinition) {
            final OCDeclarator declarator = ((OCFunctionDefinition)psiElement).getDeclarator();
            if (declarator != null) {
                String s = declarator.getName();
                Label_0136: {
                    try {
                        if (declarator.getNamespaceQualifier() == null || declarator.getNamespaceQualifier().isEmpty()) {
                            break Label_0136;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    s = declarator.getNamespaceQualifier().getName() + "::" + s;
                    try {
                        if (s.startsWith("____C_A_T_C_H____T_E_S_T____")) {
                            return s;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
            }
        }
        return null;
    }
    
    @Override
    protected String formatTestName(final CidrScopeInfo cidrScopeInfo) {
        return cidrScopeInfo.getTest();
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
