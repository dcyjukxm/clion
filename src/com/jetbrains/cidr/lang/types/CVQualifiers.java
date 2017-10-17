// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.types.visitors.OCTypeNameVisitor;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Contract;

public enum CVQualifiers
{
    EMPTY(false, false), 
    CONST(true, false), 
    VOLATILE(false, true), 
    CONST_VOLATILE(true, true);
    
    private final boolean myConst;
    private final boolean myVolatile;
    
    private CVQualifiers(final boolean myConst, final boolean myVolatile) {
        this.myConst = myConst;
        this.myVolatile = myVolatile;
    }
    
    @Contract(pure = true)
    @NotNull
    public static CVQualifiers get(final boolean b, final boolean b2) {
        CVQualifiers cvQualifiers3 = null;
        Label_0084: {
            Label_0067: {
                CVQualifiers cvQualifiers = null;
                Label_0028: {
                    Label_0015: {
                        try {
                            if (!b) {
                                break Label_0067;
                            }
                            final boolean b3 = b2;
                            if (b3) {
                                break Label_0015;
                            }
                            break Label_0015;
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        try {
                            final boolean b3 = b2;
                            if (b3) {
                                final CVQualifiers cvQualifiers2;
                                cvQualifiers = (cvQualifiers2 = CVQualifiers.CONST_VOLATILE);
                                break Label_0028;
                            }
                        }
                        catch (IllegalStateException ex2) {
                            throw a(ex2);
                        }
                    }
                    CVQualifiers cvQualifiers2;
                    cvQualifiers = (cvQualifiers2 = CVQualifiers.CONST);
                    try {
                        if (cvQualifiers2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/CVQualifiers", "get"));
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                return cvQualifiers;
                try {
                    if (b2) {
                        final CVQualifiers cvQualifiers4;
                        cvQualifiers3 = (cvQualifiers4 = CVQualifiers.VOLATILE);
                        break Label_0084;
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            CVQualifiers cvQualifiers4;
            cvQualifiers3 = (cvQualifiers4 = CVQualifiers.EMPTY);
            try {
                if (cvQualifiers4 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/CVQualifiers", "get"));
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
        }
        return cvQualifiers3;
    }
    
    public boolean isConst() {
        return this.myConst;
    }
    
    public boolean isVolatile() {
        return this.myVolatile;
    }
    
    @NotNull
    public CVQualifiers or(@NotNull final CVQualifiers cvQualifiers) {
        try {
            if (cvQualifiers == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modifiers", "com/jetbrains/cidr/lang/types/CVQualifiers", "or"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        CVQualifiers value = null;
        Label_0104: {
            boolean b2 = false;
            Label_0095: {
                Label_0074: {
                    Label_0065: {
                        try {
                            if (this.myConst) {
                                break Label_0065;
                            }
                            final CVQualifiers cvQualifiers2 = cvQualifiers;
                            final boolean b = cvQualifiers2.isConst();
                            if (b) {
                                break Label_0065;
                            }
                            break Label_0065;
                        }
                        catch (IllegalStateException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final CVQualifiers cvQualifiers2 = cvQualifiers;
                            final boolean b = cvQualifiers2.isConst();
                            if (b) {
                                b2 = true;
                                break Label_0074;
                            }
                        }
                        catch (IllegalStateException ex3) {
                            throw a(ex3);
                        }
                    }
                    b2 = false;
                    try {
                        if (this.myVolatile) {
                            break Label_0095;
                        }
                        final CVQualifiers cvQualifiers3 = cvQualifiers;
                        final boolean b3 = cvQualifiers3.isVolatile();
                        if (b3) {
                            break Label_0095;
                        }
                        break Label_0095;
                    }
                    catch (IllegalStateException ex4) {
                        throw a(ex4);
                    }
                }
                try {
                    final CVQualifiers cvQualifiers3 = cvQualifiers;
                    final boolean b3 = cvQualifiers3.isVolatile();
                    if (b3) {
                        final boolean b4 = true;
                        break Label_0104;
                    }
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
            }
            final boolean b4 = false;
            try {
                value = get(b2, b4);
                if (value == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/CVQualifiers", "or"));
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
        }
        return value;
    }
    
    public void appendCVQualifiers(@NotNull final StringBuilder sb) {
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buffer", "com/jetbrains/cidr/lang/types/CVQualifiers", "appendCVQualifiers"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (this.isConst()) {
                sb.append(" const");
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (this.isVolatile()) {
                sb.append(" volatile");
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
    }
    
    public boolean isSuperset(@NotNull final CVQualifiers p0) {
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
        //    18: ldc             "other"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/CVQualifiers"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isSuperset"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/CVQualifiers.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/lang/types/CVQualifiers.isConst:()Z
        //    48: ifne            65
        //    51: aload_1        
        //    52: invokevirtual   com/jetbrains/cidr/lang/types/CVQualifiers.isConst:()Z
        //    55: ifne            101
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/types/CVQualifiers.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    64: athrow         
        //    65: aload_0        
        //    66: invokevirtual   com/jetbrains/cidr/lang/types/CVQualifiers.isVolatile:()Z
        //    69: ifne            93
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/types/CVQualifiers.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    78: athrow         
        //    79: aload_1        
        //    80: invokevirtual   com/jetbrains/cidr/lang/types/CVQualifiers.isVolatile:()Z
        //    83: ifne            101
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/types/CVQualifiers.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    92: athrow         
        //    93: iconst_1       
        //    94: goto            102
        //    97: invokestatic    com/jetbrains/cidr/lang/types/CVQualifiers.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   100: athrow         
        //   101: iconst_0       
        //   102: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     58     61     65     Ljava/lang/IllegalStateException;
        //  51     72     75     79     Ljava/lang/IllegalStateException;
        //  65     86     89     93     Ljava/lang/IllegalStateException;
        //  79     97     97     101    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    public static String appendCVQualifiers(@NotNull String s, @NotNull final OCType ocType, @Nullable final Project project) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeString", "com/jetbrains/cidr/lang/types/CVQualifiers", "appendCVQualifiers"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/types/CVQualifiers", "appendCVQualifiers"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        if (ocType.isConst()) {
            s = OCTypeNameVisitor.addTypeQualifier(s, ocType, project, "const");
        }
        if (ocType.isVolatile()) {
            s = OCTypeNameVisitor.addTypeQualifier(s, ocType, project, "volatile");
        }
        String s2;
        try {
            s2 = s;
            if (s2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/CVQualifiers", "appendCVQualifiers"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return s2;
    }
    
    @Override
    public String toString() {
        StringBuilder append = null;
        Label_0025: {
            StringBuilder sb;
            try {
                sb = new StringBuilder();
                if (this.isConst()) {
                    final String s = "c";
                    break Label_0025;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            final String s = "";
            try {
                append = sb.append(s);
                if (this.isVolatile()) {
                    final String s2 = "v";
                    return append.append(s2).toString();
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        final String s2 = "";
        return append.append(s2).toString();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
