// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import java.io.Serializable;

public class OCReferenceType extends OCType implements Serializable
{
    @NotNull
    private OCSymbolReference myReference;
    @NotNull
    private List<String> myProtocolNames;
    @NotNull
    private OCTypeSubstitution mySubstitution;
    @Nullable
    private ARCAttribute myARCAttribute;
    private boolean myIsKindof;
    private boolean myFunctionParameterType;
    
    public OCReferenceType() {
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myFunctionParameterType = false;
    }
    
    public OCReferenceType(final boolean b, final boolean b2, @NotNull final OCSymbolReference myReference, @NotNull final List<String> myProtocolNames, @NotNull final OCTypeSubstitution mySubstitution, @Nullable final ARCAttribute myARCAttribute, @Nullable final OCNullability ocNullability, final boolean myFunctionParameterType, final boolean myIsKindof) {
        if (myReference == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/types/OCReferenceType", "<init>"));
        }
        if (myProtocolNames == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "protocolNames", "com/jetbrains/cidr/lang/types/OCReferenceType", "<init>"));
        }
        if (mySubstitution == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitution", "com/jetbrains/cidr/lang/types/OCReferenceType", "<init>"));
        }
        super(b, b2, ocNullability);
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myFunctionParameterType = false;
        this.myReference = myReference;
        this.myProtocolNames = myProtocolNames;
        this.mySubstitution = mySubstitution;
        this.myARCAttribute = myARCAttribute;
        this.myFunctionParameterType = myFunctionParameterType;
        this.myIsKindof = myIsKindof;
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitReferenceType(this);
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCReferenceType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCReferenceType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCReferenceType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final OCReferenceType ocReferenceType = (OCReferenceType)o;
        final OCReferenceType ocReferenceType2 = (OCReferenceType)o2;
        try {
            if (ocReferenceType.myARCAttribute != ocReferenceType2.myARCAttribute) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (ocReferenceType.myIsKindof != ocReferenceType2.myIsKindof) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (!ocReferenceType.myProtocolNames.equals(ocReferenceType2.myProtocolNames)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        try {
            if (!comparator.equalObjects((DeepEqual.Equality<Object>)ocReferenceType.myReference, (DeepEqual.Equality<Object>)ocReferenceType2.myReference)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
        try {
            if (!comparator.equalObjects(ocReferenceType.mySubstitution, (DeepEqual.Equality<Object>)ocReferenceType2.mySubstitution)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex9) {
            throw a(ex9);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * this.baseHashCode() + this.myReference.hashCode();
        int n2;
        try {
            n2 = 31 * n;
            if (this.myARCAttribute == null) {
                final int hashCode = 0;
                return 31 * (31 * (31 * (n2 + hashCode) + this.myProtocolNames.hashCode()) + this.mySubstitution.hashCode()) + Boolean.valueOf(this.myIsKindof).hashCode();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int hashCode = this.myARCAttribute.hashCode();
        return 31 * (31 * (31 * (n2 + hashCode) + this.myProtocolNames.hashCode()) + this.mySubstitution.hashCode()) + Boolean.valueOf(this.myIsKindof).hashCode();
    }
    
    @NotNull
    public List<String> getProtocolNames() {
        List<String> myProtocolNames;
        try {
            myProtocolNames = this.myProtocolNames;
            if (myProtocolNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceType", "getProtocolNames"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myProtocolNames;
    }
    
    @Override
    public boolean isUnknown() {
        return true;
    }
    
    @Override
    public boolean isUnresolved(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCReferenceType", "isUnresolved"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return true;
    }
    
    @NotNull
    public OCTypeSubstitution getSubstitution() {
        OCTypeSubstitution mySubstitution;
        try {
            mySubstitution = this.mySubstitution;
            if (mySubstitution == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceType", "getSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return mySubstitution;
    }
    
    @NotNull
    public static OCType resolvedFromText(final String s, @Nullable final PsiFile psiFile) {
        OCType resolve;
        try {
            resolve = fromText(s).resolve(psiFile);
            if (resolve == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceType", "resolvedFromText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return resolve;
    }
    
    @NotNull
    public static OCType resolvedFromText(final String s, @NotNull final String s2, @Nullable final PsiFile psiFile) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "protocolName", "com/jetbrains/cidr/lang/types/OCReferenceType", "resolvedFromText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCType resolve;
        try {
            resolve = fromText(s, s2).resolve(psiFile);
            if (resolve == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceType", "resolvedFromText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return resolve;
    }
    
    @NotNull
    public static OCType resolvedFromText(final String s, final PsiFile psiFile, final boolean b) {
        OCType resolve;
        try {
            resolve = fromText(s).resolve(psiFile, b);
            if (resolve == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceType", "resolvedFromText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return resolve;
    }
    
    @NotNull
    public static OCReferenceType fromText(final String s) {
        OCReferenceType build;
        try {
            build = new OCReferenceTypeBuilder(s).build();
            if (build == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceType", "fromText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return build;
    }
    
    @NotNull
    public static OCReferenceType fromText(final String s, @NotNull final String singleProtocolName) {
        try {
            if (singleProtocolName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "protocolName", "com/jetbrains/cidr/lang/types/OCReferenceType", "fromText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCReferenceType build;
        try {
            build = new OCReferenceTypeBuilder(s).setSingleProtocolName(singleProtocolName).build();
            if (build == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceType", "fromText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return build;
    }
    
    @NotNull
    public static OCReferenceType fromText(final String s, @NotNull final List<String> protocolNames) {
        try {
            if (protocolNames == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "protocolNames", "com/jetbrains/cidr/lang/types/OCReferenceType", "fromText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCReferenceType build;
        try {
            build = new OCReferenceTypeBuilder(s).setProtocolNames(protocolNames).build();
            if (build == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceType", "fromText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return build;
    }
    
    public static OCReferenceType fromLocalText(final OCQualifiedName ocQualifiedName) {
        return new OCReferenceTypeBuilder(ocQualifiedName, null).build();
    }
    
    @NotNull
    public OCSymbolReference getReference() {
        OCSymbolReference myReference;
        try {
            myReference = this.myReference;
            if (myReference == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceType", "getReference"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myReference;
    }
    
    @NotNull
    public OCSymbolReference getReference(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCReferenceType", "getReference"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCSymbolReference reference;
        try {
            reference = this.getReference(ocResolveContext.getFile());
            if (reference == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCReferenceType", "getReference"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return reference;
    }
    
    @NotNull
    public OCSymbolReference getReference(@Nullable final PsiFile p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       54
        //     4: aload_0        
        //     5: getfield        com/jetbrains/cidr/lang/types/OCReferenceType.myReference:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //     8: dup            
        //     9: ifnonnull       53
        //    12: goto            19
        //    15: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    18: athrow         
        //    19: new             Ljava/lang/IllegalStateException;
        //    22: dup            
        //    23: ldc             "@NotNull method %s.%s must not return null"
        //    25: ldc             2
        //    27: anewarray       Ljava/lang/Object;
        //    30: dup            
        //    31: ldc             0
        //    33: ldc             "com/jetbrains/cidr/lang/types/OCReferenceType"
        //    35: aastore        
        //    36: dup            
        //    37: ldc             1
        //    39: ldc             "getReference"
        //    41: aastore        
        //    42: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    45: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    48: athrow         
        //    49: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    52: athrow         
        //    53: areturn        
        //    54: aload_0        
        //    55: getfield        com/jetbrains/cidr/lang/types/OCReferenceType.myReference:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //    58: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    61: astore_2       
        //    62: aload_0        
        //    63: getfield        com/jetbrains/cidr/lang/types/OCReferenceType.myProtocolNames:Ljava/util/List;
        //    66: invokeinterface java/util/List.isEmpty:()Z
        //    71: ifne            192
        //    74: aload_2        
        //    75: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments;
        //    78: ifne            192
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    92: ifeq            192
        //    95: goto            102
        //    98: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: aload_1        
        //   103: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   106: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   111: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isObjC:()Z
        //   116: ifne            192
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: aload_0        
        //   127: getfield        com/jetbrains/cidr/lang/types/OCReferenceType.myReference:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   130: aload_0        
        //   131: getfield        com/jetbrains/cidr/lang/types/OCReferenceType.myProtocolNames:Ljava/util/List;
        //   134: aload_0        
        //   135: invokedynamic   fun:(Lcom/jetbrains/cidr/lang/types/OCReferenceType;)Lcom/intellij/util/Function;
        //   140: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //   143: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.applyTypeArguments:(Ljava/util/List;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   146: dup            
        //   147: ifnonnull       191
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: new             Ljava/lang/IllegalStateException;
        //   160: dup            
        //   161: ldc             "@NotNull method %s.%s must not return null"
        //   163: ldc             2
        //   165: anewarray       Ljava/lang/Object;
        //   168: dup            
        //   169: ldc             0
        //   171: ldc             "com/jetbrains/cidr/lang/types/OCReferenceType"
        //   173: aastore        
        //   174: dup            
        //   175: ldc             1
        //   177: ldc             "getReference"
        //   179: aastore        
        //   180: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   183: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   186: athrow         
        //   187: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   190: athrow         
        //   191: areturn        
        //   192: aload_0        
        //   193: getfield        com/jetbrains/cidr/lang/types/OCReferenceType.myReference:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   196: dup            
        //   197: ifnonnull       234
        //   200: new             Ljava/lang/IllegalStateException;
        //   203: dup            
        //   204: ldc             "@NotNull method %s.%s must not return null"
        //   206: ldc             2
        //   208: anewarray       Ljava/lang/Object;
        //   211: dup            
        //   212: ldc             0
        //   214: ldc             "com/jetbrains/cidr/lang/types/OCReferenceType"
        //   216: aastore        
        //   217: dup            
        //   218: ldc             1
        //   220: ldc             "getReference"
        //   222: aastore        
        //   223: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   226: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   229: athrow         
        //   230: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   233: athrow         
        //   234: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      12     15     19     Ljava/lang/IllegalArgumentException;
        //  4      49     49     53     Ljava/lang/IllegalArgumentException;
        //  62     81     84     88     Ljava/lang/IllegalArgumentException;
        //  74     95     98     102    Ljava/lang/IllegalArgumentException;
        //  88     119    122    126    Ljava/lang/IllegalArgumentException;
        //  102    150    153    157    Ljava/lang/IllegalArgumentException;
        //  126    187    187    191    Ljava/lang/IllegalArgumentException;
        //  192    230    230    234    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0088:
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
    public ARCAttribute getARCAttribute() {
        return this.myARCAttribute;
    }
    
    public boolean isFunctionParameterType() {
        return this.myFunctionParameterType;
    }
    
    public void setFunctionParameterType(final boolean myFunctionParameterType) {
        this.myFunctionParameterType = myFunctionParameterType;
    }
    
    public boolean isKindof() {
        return this.myIsKindof;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
