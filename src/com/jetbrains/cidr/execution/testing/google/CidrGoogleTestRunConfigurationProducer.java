// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import java.util.Collection;
import com.intellij.openapi.util.Couple;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCMacroCallArgument;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.intellij.execution.Location;
import com.jetbrains.cidr.execution.testing.CidrTestScopeElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.testing.CidrScopeInfo;
import java.util.List;
import org.jetbrains.annotations.Contract;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.ConfigurationType;
import com.jetbrains.cidr.execution.testing.CidrTestRunConfigurationProducer;
import com.jetbrains.cidr.execution.CidrRunConfiguration;
import com.jetbrains.cidr.execution.CidrBuildTarget;
import com.jetbrains.cidr.execution.CidrBuildConfiguration;

public abstract class CidrGoogleTestRunConfigurationProducer<BC extends CidrBuildConfiguration, TARGET extends CidrBuildTarget<BC>, CONFIGURATION extends CidrRunConfiguration<BC, TARGET>> extends CidrTestRunConfigurationProducer<BC, TARGET, CONFIGURATION, CidrGoogleTestObject>
{
    public static final String TEST_METHOD_NAME = "TestBody";
    
    protected CidrGoogleTestRunConfigurationProducer(final ConfigurationType configurationType) {
        super(configurationType, CidrGoogleTestFramework.class);
    }
    
    @Contract(pure = true)
    @NotNull
    @Override
    protected PsiElement getElement(@NotNull final CidrGoogleTestObject cidrGoogleTestObject) {
        try {
            if (cidrGoogleTestObject == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testObj", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer", "getElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        PsiElement element;
        try {
            element = cidrGoogleTestObject.element;
            if (element == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer", "getElement"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return element;
    }
    
    @Nullable
    @Override
    protected CidrScopeInfo determineScope(@NotNull final List<CidrGoogleTestObject> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testObj", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer", "determineScope"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.determineScope(list, ":");
    }
    
    @Override
    protected boolean isTestTarget(@NotNull final TARGET target) {
        try {
            if (target == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer", "isTestTarget"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer", "createScopeElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        CidrGoogleTestScopeElement cidrGoogleTestScopeElement;
        try {
            cidrGoogleTestScopeElement = new CidrGoogleTestScopeElement(cidrScopeInfo.getSuite(), cidrScopeInfo.getTest(), null, null);
            if (cidrGoogleTestScopeElement == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer", "createScopeElement"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return cidrGoogleTestScopeElement;
    }
    
    @Nullable
    @Override
    protected CidrScopeInfo determineScope(@NotNull final CidrGoogleTestObject p0) {
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
        //    18: ldc             "object"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "determineScope"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject.kind:Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject$KIND;
        //    48: getstatic       com/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject$KIND.FILE:Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject$KIND;
        //    51: if_acmpne       146
        //    54: aload_0        
        //    55: aload_1        
        //    56: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer.getElement:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject;)Lcom/intellij/psi/PsiElement;
        //    59: ldc             Lcom/jetbrains/cidr/lang/psi/OCStruct;.class
        //    61: invokestatic    com/intellij/psi/util/PsiTreeUtil.findChildrenOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Ljava/util/Collection;
        //    64: astore_2       
        //    65: aload_2        
        //    66: invokedynamic   fun:()Lcom/intellij/util/Function;
        //    71: invokestatic    com/intellij/util/containers/ContainerUtil.mapNotNull:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //    74: astore_3       
        //    75: aload_3        
        //    76: invokeinterface java/util/List.isEmpty:()Z
        //    81: ifne            144
        //    84: invokedynamic   fun:()Lcom/intellij/util/Function;
        //    89: astore          4
        //    91: new             Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo;
        //    94: dup            
        //    95: aconst_null    
        //    96: aconst_null    
        //    97: ldc             "test.defaultName.allTestsIn"
        //    99: iconst_1       
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: iconst_0       
        //   105: aload_0        
        //   106: aload_1        
        //   107: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer.getElement:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject;)Lcom/intellij/psi/PsiElement;
        //   110: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   113: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getName:()Ljava/lang/String;
        //   118: aastore        
        //   119: invokestatic    com/jetbrains/cidr/CidrBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   122: new             Ljava/util/LinkedHashSet;
        //   125: dup            
        //   126: aload_3        
        //   127: invokespecial   java/util/LinkedHashSet.<init>:(Ljava/util/Collection;)V
        //   130: aload           4
        //   132: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //   135: ldc             ":"
        //   137: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/lang/Iterable;Ljava/lang/String;)Ljava/lang/String;
        //   140: invokespecial   com/jetbrains/cidr/execution/testing/CidrScopeInfo.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   143: areturn        
        //   144: aconst_null    
        //   145: areturn        
        //   146: aload_0        
        //   147: aload_1        
        //   148: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer.getElement:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject;)Lcom/intellij/psi/PsiElement;
        //   151: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   154: invokeinterface com/jetbrains/cidr/lang/psi/OCStruct.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   159: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   162: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.extractGoogleTestName:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Lcom/intellij/openapi/util/Couple;
        //   165: astore_2       
        //   166: aload_2        
        //   167: ifnonnull       254
        //   170: aload_1        
        //   171: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject.param:Ljava/lang/String;
        //   174: ifnonnull       212
        //   177: goto            184
        //   180: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   183: athrow         
        //   184: new             Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo;
        //   187: dup            
        //   188: aload_0        
        //   189: aload_1        
        //   190: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer.getElement:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject;)Lcom/intellij/psi/PsiElement;
        //   193: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   196: invokeinterface com/jetbrains/cidr/lang/psi/OCStruct.getName:()Ljava/lang/String;
        //   201: aconst_null    
        //   202: aconst_null    
        //   203: aconst_null    
        //   204: invokespecial   com/jetbrains/cidr/execution/testing/CidrScopeInfo.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   207: areturn        
        //   208: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   211: athrow         
        //   212: aload_0        
        //   213: aload_1        
        //   214: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer.getElement:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject;)Lcom/intellij/psi/PsiElement;
        //   217: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   220: invokeinterface com/jetbrains/cidr/lang/psi/OCStruct.getName:()Ljava/lang/String;
        //   225: astore_3       
        //   226: new             Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo;
        //   229: dup            
        //   230: aconst_null    
        //   231: aconst_null    
        //   232: aload_3        
        //   233: new             Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleTestScopeElement;
        //   236: dup            
        //   237: aload_3        
        //   238: aconst_null    
        //   239: aconst_null    
        //   240: aload_1        
        //   241: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject.param:Ljava/lang/String;
        //   244: invokespecial   com/jetbrains/cidr/execution/testing/google/CidrGoogleTestScopeElement.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   247: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleTestScopeElement.toString:()Ljava/lang/String;
        //   250: invokespecial   com/jetbrains/cidr/execution/testing/CidrScopeInfo.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   253: areturn        
        //   254: aload_1        
        //   255: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject.param:Ljava/lang/String;
        //   258: ifnonnull       324
        //   261: aload_1        
        //   262: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject.instantiation:Ljava/lang/String;
        //   265: ifnonnull       324
        //   268: goto            275
        //   271: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   274: athrow         
        //   275: new             Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo;
        //   278: dup            
        //   279: aload_2        
        //   280: getfield        com/intellij/openapi/util/Couple.first:Ljava/lang/Object;
        //   283: checkcast       Ljava/lang/String;
        //   286: aload_1        
        //   287: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject.kind:Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject$KIND;
        //   290: getstatic       com/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject$KIND.TEST:Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject$KIND;
        //   293: if_acmpne       317
        //   296: goto            303
        //   299: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   302: athrow         
        //   303: aload_2        
        //   304: getfield        com/intellij/openapi/util/Couple.second:Ljava/lang/Object;
        //   307: checkcast       Ljava/lang/String;
        //   310: goto            318
        //   313: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   316: athrow         
        //   317: aconst_null    
        //   318: aconst_null    
        //   319: aconst_null    
        //   320: invokespecial   com/jetbrains/cidr/execution/testing/CidrScopeInfo.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   323: areturn        
        //   324: aload_1        
        //   325: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject.kind:Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject$KIND;
        //   328: getstatic       com/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject$KIND.TEST:Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject$KIND;
        //   331: if_acmpne       348
        //   334: aload_2        
        //   335: getfield        com/intellij/openapi/util/Couple.second:Ljava/lang/Object;
        //   338: checkcast       Ljava/lang/String;
        //   341: goto            349
        //   344: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   347: athrow         
        //   348: aconst_null    
        //   349: astore_3       
        //   350: aload_2        
        //   351: getfield        com/intellij/openapi/util/Couple.first:Ljava/lang/Object;
        //   354: checkcast       Ljava/lang/String;
        //   357: astore          4
        //   359: aload_1        
        //   360: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject.instantiation:Ljava/lang/String;
        //   363: ifnull          395
        //   366: new             Ljava/lang/StringBuilder;
        //   369: dup            
        //   370: invokespecial   java/lang/StringBuilder.<init>:()V
        //   373: aload_1        
        //   374: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject.instantiation:Ljava/lang/String;
        //   377: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   380: ldc             "-"
        //   382: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   385: aload           4
        //   387: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   390: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   393: astore          4
        //   395: aload_3        
        //   396: ifnull          425
        //   399: new             Ljava/lang/StringBuilder;
        //   402: dup            
        //   403: invokespecial   java/lang/StringBuilder.<init>:()V
        //   406: aload           4
        //   408: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   411: ldc             "."
        //   413: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   416: aload_3        
        //   417: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   420: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   423: astore          4
        //   425: new             Lcom/jetbrains/cidr/execution/testing/CidrScopeInfo;
        //   428: dup            
        //   429: aconst_null    
        //   430: aconst_null    
        //   431: aload           4
        //   433: new             Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleTestScopeElement;
        //   436: dup            
        //   437: aload_2        
        //   438: getfield        com/intellij/openapi/util/Couple.first:Ljava/lang/Object;
        //   441: checkcast       Ljava/lang/String;
        //   444: aload_3        
        //   445: aload_1        
        //   446: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject.instantiation:Ljava/lang/String;
        //   449: aload_1        
        //   450: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestObject.param:Ljava/lang/String;
        //   453: invokespecial   com/jetbrains/cidr/execution/testing/google/CidrGoogleTestScopeElement.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   456: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleTestScopeElement.toString:()Ljava/lang/String;
        //   459: invokespecial   com/jetbrains/cidr/execution/testing/CidrScopeInfo.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   462: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  166    177    180    184    Ljava/lang/IllegalArgumentException;
        //  170    208    208    212    Ljava/lang/IllegalArgumentException;
        //  254    268    271    275    Ljava/lang/IllegalArgumentException;
        //  261    296    299    303    Ljava/lang/IllegalArgumentException;
        //  275    313    313    317    Ljava/lang/IllegalArgumentException;
        //  324    344    344    348    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0275:
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
    @Override
    protected CidrGoogleTestObject findTestObject(@NotNull final Location location) {
        try {
            if (location == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer", "findTestObject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        PsiElement psiElement = location.getPsiElement();
        if (location instanceof CidrGoogleTestLocation) {
            final CidrGoogleTestLocation cidrGoogleTestLocation = (CidrGoogleTestLocation)location;
            if (!(psiElement instanceof OCStruct)) {
                final CidrGoogleTestObject b = b(psiElement);
                if (b != null) {
                    psiElement = this.getElement(b);
                }
            }
            CidrGoogleTestObject cidrGoogleTestObject = null;
            CidrGoogleTestObject.KIND kind = null;
            Label_0124: {
                Label_0111: {
                    try {
                        if (!(psiElement instanceof OCStruct)) {
                            return b(psiElement);
                        }
                        cidrGoogleTestObject = new(com.jetbrains.cidr.execution.testing.google.CidrGoogleTestObject.class);
                        final CidrGoogleTestLocation cidrGoogleTestLocation2 = cidrGoogleTestLocation;
                        final boolean b2 = cidrGoogleTestLocation2.isSuiteOnly();
                        if (b2) {
                            break Label_0111;
                        }
                        break Label_0111;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    try {
                        cidrGoogleTestObject = new(com.jetbrains.cidr.execution.testing.google.CidrGoogleTestObject.class);
                        final CidrGoogleTestLocation cidrGoogleTestLocation2 = cidrGoogleTestLocation;
                        final boolean b2 = cidrGoogleTestLocation2.isSuiteOnly();
                        if (b2) {
                            kind = CidrGoogleTestObject.KIND.SUITE;
                            break Label_0124;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                kind = CidrGoogleTestObject.KIND.TEST;
            }
            new CidrGoogleTestObject(kind, psiElement, cidrGoogleTestLocation.getInstantiation(), cidrGoogleTestLocation.getParam());
            return cidrGoogleTestObject;
        }
        return b(psiElement);
    }
    
    @Nullable
    private static CidrGoogleTestObject b(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationProducer", "findTestObject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final PsiElement nonStrictParentOfType = PsiTreeUtil.getNonStrictParentOfType(psiElement, new Class[] { OCFunctionDefinition.class, OCStruct.class });
        Label_0117: {
            if (nonStrictParentOfType instanceof OCStruct) {
                final OCStructSymbol symbol;
                final OCStructSymbol ocStructSymbol = symbol = ((OCStruct)nonStrictParentOfType).getSymbol();
                Label_0101: {
                    try {
                        if (ocStructSymbol == null) {
                            break Label_0117;
                        }
                        final OCStructSymbol ocStructSymbol2 = symbol;
                        final OCStructSymbol ocStructSymbol3 = ocStructSymbol2;
                        final boolean b = CidrGoogleTestUtil.isGoogleTestClass(ocStructSymbol3);
                        if (b) {
                            break Label_0101;
                        }
                        break Label_0117;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final OCStructSymbol ocStructSymbol2 = symbol;
                        final OCStructSymbol ocStructSymbol3 = ocStructSymbol2;
                        final boolean b = CidrGoogleTestUtil.isGoogleTestClass(ocStructSymbol3);
                        if (b) {
                            return new CidrGoogleTestObject(CidrGoogleTestObject.KIND.TEST, nonStrictParentOfType);
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
            }
        }
        Label_0220: {
            if (nonStrictParentOfType instanceof OCFunctionDefinition) {
                final OCFunctionSymbol symbol2 = ((OCFunctionDefinition)nonStrictParentOfType).getSymbol();
                if (symbol2 != null) {
                    final OCSymbolWithQualifiedName<OCElement> resolvedOwner = symbol2.getResolvedOwner();
                    if (resolvedOwner != null) {
                        final OCSymbol definitionSymbol = resolvedOwner.getDefinitionSymbol();
                        try {
                            if (!(definitionSymbol instanceof OCStructSymbol) || !CidrGoogleTestUtil.isGoogleTestClass((OCStructSymbol)definitionSymbol)) {
                                break Label_0220;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw b(ex4);
                        }
                        final OCStruct ocStruct = definitionSymbol.locateDefinition();
                        try {
                            if (ocStruct == null) {
                                return null;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw b(ex5);
                        }
                        return new CidrGoogleTestObject(CidrGoogleTestObject.KIND.TEST, (PsiElement)ocStruct);
                    }
                }
            }
        }
        final PsiElement nonStrictParentOfType2 = PsiTreeUtil.getNonStrictParentOfType(psiElement, new Class[] { OCMacroCall.class, OCFile.class });
        if (nonStrictParentOfType2 instanceof OCMacroCall) {
            final OCMacroCall googleTestMacros = CidrGoogleTestUtil.findGoogleTestMacros(nonStrictParentOfType2);
            Label_0426: {
                if (googleTestMacros != null) {
                    final List<OCMacroCallArgument> arguments = googleTestMacros.getArguments();
                    if (arguments.size() >= 2) {
                        final OCMacroCallArgument ocMacroCallArgument = arguments.get(0);
                        final OCMacroCallArgument ocMacroCallArgument2 = arguments.get(1);
                        boolean b3 = false;
                        Label_0340: {
                            Label_0331: {
                                try {
                                    if (a(PsiTreeUtil.getParentOfType(psiElement, (Class)OCMacroCallArgument.class))) {
                                        break Label_0331;
                                    }
                                    final PsiElement psiElement2 = psiElement;
                                    final PsiElement psiElement3 = psiElement2.getPrevSibling();
                                    final boolean b2 = a(psiElement3);
                                    if (b2) {
                                        break Label_0331;
                                    }
                                    break Label_0331;
                                }
                                catch (IllegalArgumentException ex6) {
                                    throw b(ex6);
                                }
                                try {
                                    final PsiElement psiElement2 = psiElement;
                                    final PsiElement psiElement3 = psiElement2.getPrevSibling();
                                    final boolean b2 = a(psiElement3);
                                    if (b2) {
                                        b3 = true;
                                        break Label_0340;
                                    }
                                }
                                catch (IllegalArgumentException ex7) {
                                    throw b(ex7);
                                }
                            }
                            b3 = false;
                        }
                        final boolean b4 = b3;
                        final OCStructSymbol googleTestSymbol = CidrGoogleTestUtil.findGoogleTestSymbol(psiElement.getProject(), CidrGoogleTestUtil.extractArgumentValue((PsiElement)ocMacroCallArgument), CidrGoogleTestUtil.extractArgumentValue((PsiElement)ocMacroCallArgument2));
                        if (googleTestSymbol != null) {
                            final OCStruct ocStruct2 = ((OCSymbolBase<OCStruct>)googleTestSymbol).locateDefinition();
                            CidrGoogleTestObject cidrGoogleTestObject2 = null;
                            CidrGoogleTestObject.KIND kind = null;
                            Label_0420: {
                                Label_0407: {
                                    try {
                                        if (ocStruct2 == null) {
                                            break Label_0426;
                                        }
                                        cidrGoogleTestObject2 = new(com.jetbrains.cidr.execution.testing.google.CidrGoogleTestObject.class);
                                        final boolean b5 = b4;
                                        if (b5) {
                                            break Label_0407;
                                        }
                                        break Label_0407;
                                    }
                                    catch (IllegalArgumentException ex8) {
                                        throw b(ex8);
                                    }
                                    try {
                                        cidrGoogleTestObject2 = new(com.jetbrains.cidr.execution.testing.google.CidrGoogleTestObject.class);
                                        final boolean b5 = b4;
                                        if (b5) {
                                            kind = CidrGoogleTestObject.KIND.SUITE;
                                            break Label_0420;
                                        }
                                    }
                                    catch (IllegalArgumentException ex9) {
                                        throw b(ex9);
                                    }
                                }
                                kind = CidrGoogleTestObject.KIND.TEST;
                            }
                            new CidrGoogleTestObject(kind, (PsiElement)ocStruct2);
                            return cidrGoogleTestObject2;
                        }
                    }
                }
            }
            final Couple<String> fullSuiteNameFromMacro = CidrGoogleTestUtil.extractFullSuiteNameFromMacro(nonStrictParentOfType2);
            if (fullSuiteNameFromMacro != null) {
                final Collection<OCStructSymbol> googleTestSymbolsForSuiteRandomly = CidrGoogleTestUtil.findGoogleTestSymbolsForSuiteRandomly(psiElement.getProject(), (String)fullSuiteNameFromMacro.first, true);
                if (googleTestSymbolsForSuiteRandomly.size() != 0) {
                    final OCStruct ocStruct3 = ((OCSymbolBase<OCStruct>)googleTestSymbolsForSuiteRandomly.iterator().next()).locateDefinition();
                    try {
                        if (ocStruct3 == null) {
                            return null;
                        }
                    }
                    catch (IllegalArgumentException ex10) {
                        throw b(ex10);
                    }
                    return new CidrGoogleTestObject(CidrGoogleTestObject.KIND.SUITE, (PsiElement)ocStruct3, (String)fullSuiteNameFromMacro.second, null);
                }
            }
        }
        else {
            try {
                if (nonStrictParentOfType2 instanceof OCFile) {
                    return new CidrGoogleTestObject(CidrGoogleTestObject.KIND.FILE, nonStrictParentOfType2);
                }
            }
            catch (IllegalArgumentException ex11) {
                throw b(ex11);
            }
        }
        return null;
    }
    
    private static boolean a(@Nullable final PsiElement psiElement) {
        final OCMacroCall ocMacroCall = (OCMacroCall)PsiTreeUtil.getParentOfType(psiElement, (Class)OCMacroCall.class);
        if (ocMacroCall != null) {
            final List<OCMacroCallArgument> arguments = ocMacroCall.getArguments();
            Label_0054: {
                try {
                    if (arguments.size() <= 0) {
                        return false;
                    }
                    final List<OCMacroCallArgument> list = arguments;
                    final int n = 0;
                    final OCMacroCallArgument ocMacroCallArgument = list.get(n);
                    final OCMacroCallArgument ocMacroCallArgument2 = ocMacroCallArgument;
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = ocMacroCallArgument2.equals(psiElement2);
                    if (b) {
                        break Label_0054;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final List<OCMacroCallArgument> list = arguments;
                    final int n = 0;
                    final OCMacroCallArgument ocMacroCallArgument = list.get(n);
                    final OCMacroCallArgument ocMacroCallArgument2 = ocMacroCallArgument;
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = ocMacroCallArgument2.equals(psiElement2);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            return false;
        }
        return false;
    }
    
    @Override
    protected String formatTestName(final CidrScopeInfo cidrScopeInfo) {
        return cidrScopeInfo.getSuite() + "." + cidrScopeInfo.getTest();
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
