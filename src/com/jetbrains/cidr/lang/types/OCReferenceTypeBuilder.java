// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import java.util.Collections;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import org.jetbrains.annotations.Nullable;

public class OCReferenceTypeBuilder
{
    private boolean myConst;
    private boolean myVolatile;
    @Nullable
    private OCNullability myNullability;
    @NotNull
    private final OCSymbolReference myReference;
    @NotNull
    private List<String> myProtocolNames;
    @NotNull
    private OCTypeSubstitution mySubstitution;
    @Nullable
    private ARCAttribute myARCAttribute;
    private boolean myFunctionParameterType;
    private boolean myIsKindof;
    
    public OCReferenceTypeBuilder(final String s) {
        this(OCQualifiedName.interned(s));
    }
    
    public OCReferenceTypeBuilder(@NotNull final OCQualifiedName ocQualifiedName) {
        if (ocQualifiedName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "<init>"));
        }
        this(ocQualifiedName, null, false, false);
    }
    
    public OCReferenceTypeBuilder(@NotNull final OCQualifiedName ocQualifiedName, @Nullable final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final boolean b, final boolean b2) {
        if (ocQualifiedName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "<init>"));
        }
        this(a(ocQualifiedName, ocSymbolWithQualifiedName, b, b2));
    }
    
    public OCReferenceTypeBuilder(@NotNull final OCQualifiedName ocQualifiedName, @Nullable final PsiElement psiElement, @Nullable final OCSymbolWithQualifiedName ocSymbolWithQualifiedName) {
        if (ocQualifiedName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "<init>"));
        }
        this(a(ocQualifiedName, psiElement, ocSymbolWithQualifiedName));
    }
    
    public OCReferenceTypeBuilder(@NotNull final OCQualifiedName ocQualifiedName, @Nullable final PsiElement psiElement) {
        if (ocQualifiedName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "<init>"));
        }
        this(a(ocQualifiedName, psiElement, null));
    }
    
    public OCReferenceTypeBuilder(@NotNull final OCSymbolReference myReference) {
        if (myReference == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "<init>"));
        }
        this.myConst = false;
        this.myVolatile = false;
        this.myProtocolNames = Collections.emptyList();
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myARCAttribute = null;
        this.myFunctionParameterType = false;
        this.myIsKindof = false;
        this.myReference = myReference;
    }
    
    @NotNull
    private static OCSymbolReference a(@NotNull final OCQualifiedName ocQualifiedName, @Nullable final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final boolean b, final boolean b2) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "makeGlobalReference"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCSymbolReference.GlobalReference globalReference = null;
        Label_0154: {
            OCSymbolReference.TemplateParamsReference templateParamsReference = null;
            Label_0119: {
                Label_0099: {
                    OCSymbolReference.BaseClauseReference baseClauseReference = null;
                    Label_0064: {
                        try {
                            if (!b) {
                                break Label_0099;
                            }
                            final OCQualifiedName ocQualifiedName2 = ocQualifiedName;
                            final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName;
                            baseClauseReference = OCSymbolReference.getBaseClauseReference(ocQualifiedName2, ocSymbolWithQualifiedName2);
                            if (baseClauseReference == null) {
                                break Label_0064;
                            }
                            return baseClauseReference;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final OCQualifiedName ocQualifiedName2 = ocQualifiedName;
                            final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName;
                            baseClauseReference = OCSymbolReference.getBaseClauseReference(ocQualifiedName2, ocSymbolWithQualifiedName2);
                            if (baseClauseReference == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "makeGlobalReference"));
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    return baseClauseReference;
                    try {
                        if (!b2) {
                            break Label_0154;
                        }
                        final OCQualifiedName ocQualifiedName3 = ocQualifiedName;
                        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName3 = ocSymbolWithQualifiedName;
                        templateParamsReference = OCSymbolReference.getTemplateParamsReference(ocQualifiedName3, ocSymbolWithQualifiedName3);
                        if (templateParamsReference == null) {
                            break Label_0119;
                        }
                        return templateParamsReference;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                try {
                    final OCQualifiedName ocQualifiedName3 = ocQualifiedName;
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName3 = ocSymbolWithQualifiedName;
                    templateParamsReference = OCSymbolReference.getTemplateParamsReference(ocQualifiedName3, ocSymbolWithQualifiedName3);
                    if (templateParamsReference == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "makeGlobalReference"));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            return templateParamsReference;
            try {
                globalReference = OCSymbolReference.getGlobalReference(ocQualifiedName, ocSymbolWithQualifiedName);
                if (globalReference == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "makeGlobalReference"));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return globalReference;
    }
    
    @NotNull
    private static OCSymbolReference a(@NotNull final OCQualifiedName p0, @Nullable final PsiElement p1, @Nullable final OCSymbolWithQualifiedName p2) {
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
        //    18: ldc             "name"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "tryMakeLocalReference"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: iconst_0       
        //    45: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    48: astore_3       
        //    49: aload_1        
        //    50: ifnull          161
        //    53: aload_1        
        //    54: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    59: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    62: astore          4
        //    64: aload           4
        //    66: aload_0        
        //    67: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCLocalTypes.canBeLocalType:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;)Ljava/lang/Boolean;
        //    70: astore_3       
        //    71: aload_3        
        //    72: ifnull          104
        //    75: aload_3        
        //    76: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    79: ifne            161
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: aload           4
        //    91: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCLocalTypes.hasLocalUsingStatement:(Lcom/jetbrains/cidr/lang/psi/OCFile;)Z
        //    94: ifeq            161
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: aload_1        
        //   105: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //   107: invokestatic    com/intellij/psi/util/PsiTreeUtil.getTopmostParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   110: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   113: astore          5
        //   115: aload           5
        //   117: ifnull          156
        //   120: aload_3        
        //   121: ifnull          148
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aload           5
        //   133: ldc             Lcom/jetbrains/cidr/lang/psi/OCCppUsingStatement;.class
        //   135: invokestatic    com/intellij/psi/util/PsiTreeUtil.findChildOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   138: ifnull          156
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: iconst_1       
        //   149: goto            157
        //   152: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   155: athrow         
        //   156: iconst_0       
        //   157: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   160: astore_3       
        //   161: aload_3        
        //   162: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   165: ifeq            219
        //   168: aload_0        
        //   169: aload_1        
        //   170: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.getLocalReference:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference;
        //   173: dup            
        //   174: ifnonnull       218
        //   177: goto            184
        //   180: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   183: athrow         
        //   184: new             Ljava/lang/IllegalStateException;
        //   187: dup            
        //   188: ldc             "@NotNull method %s.%s must not return null"
        //   190: ldc             2
        //   192: anewarray       Ljava/lang/Object;
        //   195: dup            
        //   196: ldc             0
        //   198: ldc             "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder"
        //   200: aastore        
        //   201: dup            
        //   202: ldc             1
        //   204: ldc             "tryMakeLocalReference"
        //   206: aastore        
        //   207: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   210: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   213: athrow         
        //   214: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   217: athrow         
        //   218: areturn        
        //   219: aload_2        
        //   220: ifnonnull       228
        //   223: aload_1        
        //   224: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.getGlobalContextFromLocal:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   227: astore_2       
        //   228: aload_0        
        //   229: aload_2        
        //   230: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.getGlobalReference:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference;
        //   233: dup            
        //   234: ifnonnull       271
        //   237: new             Ljava/lang/IllegalStateException;
        //   240: dup            
        //   241: ldc             "@NotNull method %s.%s must not return null"
        //   243: ldc             2
        //   245: anewarray       Ljava/lang/Object;
        //   248: dup            
        //   249: ldc             0
        //   251: ldc             "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder"
        //   253: aastore        
        //   254: dup            
        //   255: ldc             1
        //   257: ldc             "tryMakeLocalReference"
        //   259: aastore        
        //   260: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   263: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   266: athrow         
        //   267: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   270: athrow         
        //   271: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  71     82     85     89     Ljava/lang/IllegalArgumentException;
        //  75     97     100    104    Ljava/lang/IllegalArgumentException;
        //  115    124    127    131    Ljava/lang/IllegalArgumentException;
        //  120    141    144    148    Ljava/lang/IllegalArgumentException;
        //  131    152    152    156    Ljava/lang/IllegalArgumentException;
        //  161    177    180    184    Ljava/lang/IllegalArgumentException;
        //  168    214    214    218    Ljava/lang/IllegalArgumentException;
        //  228    267    267    271    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0131:
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
    public OCReferenceTypeBuilder setSingleProtocolName(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "protocolName", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "setSingleProtocolName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCReferenceTypeBuilder setProtocolNames;
        try {
            setProtocolNames = this.setProtocolNames(Collections.singletonList(s));
            if (setProtocolNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "setSingleProtocolName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return setProtocolNames;
    }
    
    @NotNull
    public OCReferenceTypeBuilder setProtocolNames(@NotNull final List<String> myProtocolNames) {
        try {
            if (myProtocolNames == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "protocolNames", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "setProtocolNames"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            this.myProtocolNames = myProtocolNames;
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "setProtocolNames"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this;
    }
    
    @NotNull
    public OCReferenceTypeBuilder setSubstitution(@NotNull final OCTypeSubstitution mySubstitution) {
        try {
            if (mySubstitution == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitution", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "setSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            this.mySubstitution = mySubstitution;
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "setSubstitution"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this;
    }
    
    @NotNull
    public OCReferenceTypeBuilder setARCAttribute(@Nullable final ARCAttribute myARCAttribute) {
        try {
            this.myARCAttribute = myARCAttribute;
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "setARCAttribute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this;
    }
    
    @NotNull
    public OCReferenceTypeBuilder setProtocolSubstitutionARCFromType(@NotNull final OCReferenceType ocReferenceType) {
        try {
            if (ocReferenceType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fromType", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "setProtocolSubstitutionARCFromType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            this.setProtocolNames(ocReferenceType.getProtocolNames());
            this.setSubstitution(ocReferenceType.getSubstitution());
            this.setARCAttribute(ocReferenceType.getARCAttribute());
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "setProtocolSubstitutionARCFromType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this;
    }
    
    @NotNull
    public OCReferenceTypeBuilder setConstVolatile(final boolean myConst, final boolean myVolatile) {
        try {
            this.myConst = myConst;
            this.myVolatile = myVolatile;
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "setConstVolatile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this;
    }
    
    @NotNull
    public OCReferenceTypeBuilder setConstVolatile(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fromType", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "setConstVolatile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCReferenceTypeBuilder setConstVolatile;
        try {
            setConstVolatile = this.setConstVolatile(ocType.isConst(), ocType.isVolatile());
            if (setConstVolatile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "setConstVolatile"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return setConstVolatile;
    }
    
    @NotNull
    public OCReferenceTypeBuilder setNullability(@Nullable final OCNullability myNullability) {
        try {
            this.myNullability = myNullability;
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "setNullability"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this;
    }
    
    @NotNull
    public OCReferenceTypeBuilder setFunctionParameterType(final boolean myFunctionParameterType) {
        try {
            this.myFunctionParameterType = myFunctionParameterType;
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "setFunctionParameterType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this;
    }
    
    public void setIsKindof(final boolean myIsKindof) {
        this.myIsKindof = myIsKindof;
    }
    
    @NotNull
    public OCReferenceType build() {
        OCReferenceType ocReferenceType;
        try {
            ocReferenceType = new OCReferenceType(this.myConst, this.myVolatile, this.myReference, this.myProtocolNames, this.mySubstitution, this.myARCAttribute, this.myNullability, this.myFunctionParameterType, this.myIsKindof);
            if (ocReferenceType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder", "build"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocReferenceType;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
