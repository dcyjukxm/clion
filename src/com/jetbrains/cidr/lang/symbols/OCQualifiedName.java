// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.symtable.OCNamesInternary;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.io.Serializable;

public class OCQualifiedName implements Serializable, DeepEqual.Equality<OCQualifiedName>
{
    @NotNull
    private static final String GLOBAL_NAME = "";
    @NotNull
    public static final OCQualifiedName GLOBAL;
    @Nullable
    protected OCQualifiedName myQualifier;
    @Nullable
    protected String myName;
    
    protected OCQualifiedName() {
    }
    
    private OCQualifiedName(@Nullable final OCQualifiedName myQualifier, @Nullable final String myName) {
        this.myQualifier = myQualifier;
        this.myName = myName;
    }
    
    public static OCQualifiedName interned(@Nullable final OCQualifiedName ocQualifiedName, @Nullable final String s) {
        final OCQualifiedName with = with(ocQualifiedName, s);
        try {
            if (with == OCQualifiedName.GLOBAL) {
                return with;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCNamesInternary.intern(with);
    }
    
    public static OCQualifiedName with(@Nullable final OCQualifiedName ocQualifiedName, @Nullable final String s) {
        Label_0020: {
            try {
                if (ocQualifiedName != null) {
                    return new OCQualifiedName(ocQualifiedName, s);
                }
                final String s2 = "";
                final String s3 = s;
                final boolean b = s2.equals(s3);
                if (b) {
                    break Label_0020;
                }
                return new OCQualifiedName(ocQualifiedName, s);
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final String s2 = "";
                final String s3 = s;
                final boolean b = s2.equals(s3);
                if (b) {
                    return OCQualifiedName.GLOBAL;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return new OCQualifiedName(ocQualifiedName, s);
    }
    
    public static OCQualifiedName interned(@Nullable final String s) {
        return interned(null, s);
    }
    
    public static OCQualifiedName with(@Nullable final String s) {
        return with(null, s);
    }
    
    @Nullable
    public OCQualifiedName getQualifier() {
        return this.myQualifier;
    }
    
    @Nullable
    public String getName() {
        return this.myName;
    }
    
    public OCQualifiedName dropArguments() {
        return this;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final OCQualifiedName ocQualifiedName, @NotNull final OCQualifiedName ocQualifiedName2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/OCQualifiedName", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/OCQualifiedName", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocQualifiedName2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/OCQualifiedName", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!Comparing.equal(ocQualifiedName.myName, ocQualifiedName2.myName)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!comparator.equalObjects((DeepEqual.Equality<Object>)ocQualifiedName.myQualifier, (DeepEqual.Equality<Object>)ocQualifiedName2.myQualifier)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return true;
    }
    
    @Override
    public final boolean equals(@Nullable final Object o) {
        return DeepEqual.equalObjects(this, o);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        Label_0022: {
            try {
                if (this.myQualifier != null) {
                    hashCode = this.myQualifier.hashCode();
                    break Label_0022;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            hashCode = 0;
        }
        final int n = hashCode;
        int n2;
        try {
            n2 = 31 * n;
            if (this.myName != null) {
                final int hashCode2 = this.myName.hashCode();
                return n2 + hashCode2;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final int hashCode2 = 0;
        return n2 + hashCode2;
    }
    
    public String getSuperQualifier() {
        try {
            if (this.myQualifier == null) {
                return this.myName;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myQualifier.getSuperQualifier();
    }
    
    @NotNull
    public OCQualifiedName dropSuperQualifier() {
        OCQualifiedName with = null;
        Label_0118: {
            OCQualifiedName ocQualifiedName4 = null;
            Label_0083: {
                Label_0054: {
                    OCQualifiedName ocQualifiedName = null;
                    Label_0019: {
                        try {
                            if (this.myQualifier != null) {
                                break Label_0054;
                            }
                            ocQualifiedName = this;
                            if (ocQualifiedName == null) {
                                break Label_0019;
                            }
                            return ocQualifiedName;
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            ocQualifiedName = this;
                            if (ocQualifiedName == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCQualifiedName", "dropSuperQualifier"));
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                    return ocQualifiedName;
                    try {
                        if (this.myQualifier.myQualifier != null) {
                            break Label_0118;
                        }
                        final OCQualifiedName ocQualifiedName2 = null;
                        final OCQualifiedName ocQualifiedName3 = this;
                        final String s = ocQualifiedName3.myName;
                        ocQualifiedName4 = with(ocQualifiedName2, s);
                        if (ocQualifiedName4 == null) {
                            break Label_0083;
                        }
                        return ocQualifiedName4;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    final OCQualifiedName ocQualifiedName2 = null;
                    final OCQualifiedName ocQualifiedName3 = this;
                    final String s = ocQualifiedName3.myName;
                    ocQualifiedName4 = with(ocQualifiedName2, s);
                    if (ocQualifiedName4 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCQualifiedName", "dropSuperQualifier"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return ocQualifiedName4;
            try {
                with = with(this.myQualifier.dropSuperQualifier(), this.myName);
                if (with == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCQualifiedName", "dropSuperQualifier"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return with;
    }
    
    @NotNull
    public OCQualifiedName changeName(@Nullable final String s) {
        OCQualifiedName with = null;
        Label_0058: {
            OCQualifiedName ocQualifiedName = null;
            Label_0023: {
                try {
                    if (!Comparing.equal(this.myName, s)) {
                        break Label_0058;
                    }
                    ocQualifiedName = this;
                    if (ocQualifiedName == null) {
                        break Label_0023;
                    }
                    return ocQualifiedName;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    ocQualifiedName = this;
                    if (ocQualifiedName == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCQualifiedName", "changeName"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return ocQualifiedName;
            try {
                with = with(this.myQualifier, s);
                if (with == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCQualifiedName", "changeName"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return with;
    }
    
    @NotNull
    public OCQualifiedName changeQualifier(@Nullable final OCQualifiedName ocQualifiedName) {
        OCQualifiedName with;
        try {
            with = with(ocQualifiedName, this.myName);
            if (with == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCQualifiedName", "changeQualifier"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return with;
    }
    
    public OCQualifiedName append(final OCQualifiedName ocQualifiedName) {
        final OCQualifiedName with = with(this, ocQualifiedName.getSuperQualifier());
        try {
            if (ocQualifiedName.getQualifier() == null) {
                return with;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return with.append(ocQualifiedName.dropSuperQualifier());
    }
    
    public String getCanonicalName(final boolean b) {
        return this.getCanonicalName(b, true);
    }
    
    public String getCanonicalName(final boolean b, final boolean b2) {
        try {
            if (b) {
                final OCType.Presentation presentation = OCType.Presentation.FULL;
                return this.getCanonicalName(presentation, b2, new OCResolveContext(null), 0);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCType.Presentation presentation = OCType.Presentation.SHORT;
        return this.getCanonicalName(presentation, b2, new OCResolveContext(null), 0);
    }
    
    public String getCanonicalName(@NotNull final OCType.Presentation p0, final boolean p1, @NotNull final OCResolveContext p2, final int p3) {
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
        //    18: ldc             "presentation"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/OCQualifiedName"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getCanonicalName"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_3        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "context"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/symbols/OCQualifiedName"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getCanonicalName"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/symbols/OCQualifiedName.myName:Ljava/lang/String;
        //    92: ifnull          106
        //    95: aload_0        
        //    96: getfield        com/jetbrains/cidr/lang/symbols/OCQualifiedName.myName:Ljava/lang/String;
        //    99: goto            108
        //   102: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: ldc             "<anonymous>"
        //   108: astore          5
        //   110: aload_1        
        //   111: getstatic       com/jetbrains/cidr/lang/types/OCType$Presentation.SHORT:Lcom/jetbrains/cidr/lang/types/OCType$Presentation;
        //   114: if_acmpeq       204
        //   117: aload_0        
        //   118: getfield        com/jetbrains/cidr/lang/symbols/OCQualifiedName.myQualifier:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   121: ifnull          201
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: iload_2        
        //   132: ifne            159
        //   135: goto            142
        //   138: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   141: athrow         
        //   142: aload_0        
        //   143: getfield        com/jetbrains/cidr/lang/symbols/OCQualifiedName.myQualifier:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   146: getstatic       com/jetbrains/cidr/lang/symbols/OCQualifiedName.GLOBAL:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   149: if_acmpeq       201
        //   152: goto            159
        //   155: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   158: athrow         
        //   159: new             Ljava/lang/StringBuilder;
        //   162: dup            
        //   163: invokespecial   java/lang/StringBuilder.<init>:()V
        //   166: aload_0        
        //   167: getfield        com/jetbrains/cidr/lang/symbols/OCQualifiedName.myQualifier:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   170: aload_1        
        //   171: iload_2        
        //   172: aload_3        
        //   173: iload           4
        //   175: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getCanonicalName:(Lcom/jetbrains/cidr/lang/types/OCType$Presentation;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;I)Ljava/lang/String;
        //   178: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   181: ldc             "::"
        //   183: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   186: aload           5
        //   188: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   191: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   194: goto            203
        //   197: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   200: athrow         
        //   201: aload           5
        //   203: areturn        
        //   204: aload           5
        //   206: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     102    102    106    Ljava/lang/IllegalArgumentException;
        //  110    124    127    131    Ljava/lang/IllegalArgumentException;
        //  117    135    138    142    Ljava/lang/IllegalArgumentException;
        //  131    152    155    159    Ljava/lang/IllegalArgumentException;
        //  142    197    197    201    Ljava/lang/IllegalArgumentException;
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
    
    public String getNameWithParent() {
        final String canonicalName = this.getCanonicalName(false);
        Label_0030: {
            try {
                if (this.myQualifier == null) {
                    return canonicalName;
                }
                final OCQualifiedName ocQualifiedName = this;
                final OCQualifiedName ocQualifiedName2 = ocQualifiedName.myQualifier;
                final OCQualifiedName ocQualifiedName3 = OCQualifiedName.GLOBAL;
                if (ocQualifiedName2 != ocQualifiedName3) {
                    break Label_0030;
                }
                return canonicalName;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCQualifiedName ocQualifiedName = this;
                final OCQualifiedName ocQualifiedName2 = ocQualifiedName.myQualifier;
                final OCQualifiedName ocQualifiedName3 = OCQualifiedName.GLOBAL;
                if (ocQualifiedName2 != ocQualifiedName3) {
                    return this.myQualifier.getCanonicalName(false) + "::" + canonicalName;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return canonicalName;
    }
    
    public static OCQualifiedName parse(final String s) {
        OCQualifiedName with = null;
        final String[] split = s.split("::");
        for (int length = split.length, i = 0; i < length; ++i) {
            with = with(with, split[i]);
        }
        return with;
    }
    
    @Override
    public String toString() {
        return this.getCanonicalName(true);
    }
    
    public List<String> flatten() {
        final ArrayList<String> list = new ArrayList<String>();
        for (OCQualifiedName qualifier = this; qualifier != null; qualifier = qualifier.getQualifier()) {
            list.add(qualifier.getName());
        }
        Collections.reverse(list);
        return list;
    }
    
    static {
        GLOBAL = new OCQualifiedName(null, "");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
