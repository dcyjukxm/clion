// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Comparing;
import org.jetbrains.annotations.NotNull;

public enum OCLanguageStandard
{
    C89(OCLanguageKind.C), 
    C99(OCLanguageKind.C), 
    C11(OCLanguageKind.C), 
    CPP98(OCLanguageKind.CPP), 
    CPP11(OCLanguageKind.CPP), 
    CPP14(OCLanguageKind.CPP), 
    CPP17(OCLanguageKind.CPP);
    
    @NotNull
    private final OCLanguageKind myKind;
    public static final String __STDC_VERSION__VALUE_C89 = "199409L";
    public static final String __STDC_VERSION__VALUE_C99 = "199901L";
    public static final String __STDC_VERSION__VALUE_C11 = "201112L";
    public static final String __CPLUSPLUS_VALUE_CPP98 = "199711L";
    public static final String __CPLUSPLUS_VALUE_CPP11 = "201103L";
    public static final String __CPLUSPLUS_VALUE_CPP14 = "201402L";
    
    private OCLanguageStandard(final OCLanguageKind myKind) {
        if (myKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/OCLanguageStandard", "<init>"));
        }
        super(s, n);
        this.myKind = myKind;
    }
    
    public boolean supports(@NotNull final OCLanguageStandard ocLanguageStandard) {
        try {
            if (ocLanguageStandard == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "standard", "com/jetbrains/cidr/lang/OCLanguageStandard", "supports"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0076: {
            try {
                if (!Comparing.equal((Object)this.myKind, (Object)ocLanguageStandard.myKind)) {
                    return false;
                }
                final OCLanguageStandard ocLanguageStandard2 = this;
                final int n = ocLanguageStandard2.ordinal();
                final OCLanguageStandard ocLanguageStandard3 = ocLanguageStandard;
                final int n2 = ocLanguageStandard3.ordinal();
                if (n >= n2) {
                    break Label_0076;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCLanguageStandard ocLanguageStandard2 = this;
                final int n = ocLanguageStandard2.ordinal();
                final OCLanguageStandard ocLanguageStandard3 = ocLanguageStandard;
                final int n2 = ocLanguageStandard3.ordinal();
                if (n >= n2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    @NotNull
    public static OCLanguageStandard getCStandard(@Nullable final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          127
        //     4: aload_0        
        //     5: ldc             "199901L"
        //     7: invokevirtual   java/lang/String.compareTo:(Ljava/lang/String;)I
        //    10: ifle            69
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/lang/OCLanguageStandard.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    19: athrow         
        //    20: getstatic       com/jetbrains/cidr/lang/OCLanguageStandard.C11:Lcom/jetbrains/cidr/lang/OCLanguageStandard;
        //    23: dup            
        //    24: ifnonnull       68
        //    27: goto            34
        //    30: invokestatic    com/jetbrains/cidr/lang/OCLanguageStandard.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    33: athrow         
        //    34: new             Ljava/lang/IllegalStateException;
        //    37: dup            
        //    38: ldc             "@NotNull method %s.%s must not return null"
        //    40: ldc             2
        //    42: anewarray       Ljava/lang/Object;
        //    45: dup            
        //    46: ldc             0
        //    48: ldc             "com/jetbrains/cidr/lang/OCLanguageStandard"
        //    50: aastore        
        //    51: dup            
        //    52: ldc             1
        //    54: ldc             "getCStandard"
        //    56: aastore        
        //    57: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    60: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    63: athrow         
        //    64: invokestatic    com/jetbrains/cidr/lang/OCLanguageStandard.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: areturn        
        //    69: aload_0        
        //    70: ldc             "199409L"
        //    72: invokevirtual   java/lang/String.compareTo:(Ljava/lang/String;)I
        //    75: ifle            127
        //    78: getstatic       com/jetbrains/cidr/lang/OCLanguageStandard.C99:Lcom/jetbrains/cidr/lang/OCLanguageStandard;
        //    81: dup            
        //    82: ifnonnull       126
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/OCLanguageStandard.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: new             Ljava/lang/IllegalStateException;
        //    95: dup            
        //    96: ldc             "@NotNull method %s.%s must not return null"
        //    98: ldc             2
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "com/jetbrains/cidr/lang/OCLanguageStandard"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "getCStandard"
        //   114: aastore        
        //   115: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   118: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   121: athrow         
        //   122: invokestatic    com/jetbrains/cidr/lang/OCLanguageStandard.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: areturn        
        //   127: getstatic       com/jetbrains/cidr/lang/OCLanguageStandard.C89:Lcom/jetbrains/cidr/lang/OCLanguageStandard;
        //   130: dup            
        //   131: ifnonnull       168
        //   134: new             Ljava/lang/IllegalStateException;
        //   137: dup            
        //   138: ldc             "@NotNull method %s.%s must not return null"
        //   140: ldc             2
        //   142: anewarray       Ljava/lang/Object;
        //   145: dup            
        //   146: ldc             0
        //   148: ldc             "com/jetbrains/cidr/lang/OCLanguageStandard"
        //   150: aastore        
        //   151: dup            
        //   152: ldc             1
        //   154: ldc             "getCStandard"
        //   156: aastore        
        //   157: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   160: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   163: athrow         
        //   164: invokestatic    com/jetbrains/cidr/lang/OCLanguageStandard.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      13     16     20     Ljava/lang/IllegalArgumentException;
        //  4      27     30     34     Ljava/lang/IllegalArgumentException;
        //  20     64     64     68     Ljava/lang/IllegalArgumentException;
        //  69     85     88     92     Ljava/lang/IllegalArgumentException;
        //  78     122    122    126    Ljava/lang/IllegalArgumentException;
        //  127    164    164    168    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
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
    public static OCLanguageStandard getCppStandard(@Nullable final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          185
        //     4: aload_0        
        //     5: ldc             "201402L"
        //     7: invokevirtual   java/lang/String.compareTo:(Ljava/lang/String;)I
        //    10: ifle            69
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/lang/OCLanguageStandard.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    19: athrow         
        //    20: getstatic       com/jetbrains/cidr/lang/OCLanguageStandard.CPP17:Lcom/jetbrains/cidr/lang/OCLanguageStandard;
        //    23: dup            
        //    24: ifnonnull       68
        //    27: goto            34
        //    30: invokestatic    com/jetbrains/cidr/lang/OCLanguageStandard.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    33: athrow         
        //    34: new             Ljava/lang/IllegalStateException;
        //    37: dup            
        //    38: ldc             "@NotNull method %s.%s must not return null"
        //    40: ldc             2
        //    42: anewarray       Ljava/lang/Object;
        //    45: dup            
        //    46: ldc             0
        //    48: ldc             "com/jetbrains/cidr/lang/OCLanguageStandard"
        //    50: aastore        
        //    51: dup            
        //    52: ldc             1
        //    54: ldc             "getCppStandard"
        //    56: aastore        
        //    57: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    60: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    63: athrow         
        //    64: invokestatic    com/jetbrains/cidr/lang/OCLanguageStandard.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: areturn        
        //    69: aload_0        
        //    70: ldc             "201103L"
        //    72: invokevirtual   java/lang/String.compareTo:(Ljava/lang/String;)I
        //    75: ifle            127
        //    78: getstatic       com/jetbrains/cidr/lang/OCLanguageStandard.CPP14:Lcom/jetbrains/cidr/lang/OCLanguageStandard;
        //    81: dup            
        //    82: ifnonnull       126
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/OCLanguageStandard.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: new             Ljava/lang/IllegalStateException;
        //    95: dup            
        //    96: ldc             "@NotNull method %s.%s must not return null"
        //    98: ldc             2
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "com/jetbrains/cidr/lang/OCLanguageStandard"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "getCppStandard"
        //   114: aastore        
        //   115: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   118: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   121: athrow         
        //   122: invokestatic    com/jetbrains/cidr/lang/OCLanguageStandard.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: areturn        
        //   127: aload_0        
        //   128: ldc             "199711L"
        //   130: invokevirtual   java/lang/String.compareTo:(Ljava/lang/String;)I
        //   133: ifle            185
        //   136: getstatic       com/jetbrains/cidr/lang/OCLanguageStandard.CPP11:Lcom/jetbrains/cidr/lang/OCLanguageStandard;
        //   139: dup            
        //   140: ifnonnull       184
        //   143: goto            150
        //   146: invokestatic    com/jetbrains/cidr/lang/OCLanguageStandard.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: new             Ljava/lang/IllegalStateException;
        //   153: dup            
        //   154: ldc             "@NotNull method %s.%s must not return null"
        //   156: ldc             2
        //   158: anewarray       Ljava/lang/Object;
        //   161: dup            
        //   162: ldc             0
        //   164: ldc             "com/jetbrains/cidr/lang/OCLanguageStandard"
        //   166: aastore        
        //   167: dup            
        //   168: ldc             1
        //   170: ldc             "getCppStandard"
        //   172: aastore        
        //   173: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   176: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   179: athrow         
        //   180: invokestatic    com/jetbrains/cidr/lang/OCLanguageStandard.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   183: athrow         
        //   184: areturn        
        //   185: getstatic       com/jetbrains/cidr/lang/OCLanguageStandard.CPP98:Lcom/jetbrains/cidr/lang/OCLanguageStandard;
        //   188: dup            
        //   189: ifnonnull       226
        //   192: new             Ljava/lang/IllegalStateException;
        //   195: dup            
        //   196: ldc             "@NotNull method %s.%s must not return null"
        //   198: ldc             2
        //   200: anewarray       Ljava/lang/Object;
        //   203: dup            
        //   204: ldc             0
        //   206: ldc             "com/jetbrains/cidr/lang/OCLanguageStandard"
        //   208: aastore        
        //   209: dup            
        //   210: ldc             1
        //   212: ldc             "getCppStandard"
        //   214: aastore        
        //   215: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   218: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   221: athrow         
        //   222: invokestatic    com/jetbrains/cidr/lang/OCLanguageStandard.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   225: athrow         
        //   226: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      13     16     20     Ljava/lang/IllegalArgumentException;
        //  4      27     30     34     Ljava/lang/IllegalArgumentException;
        //  20     64     64     68     Ljava/lang/IllegalArgumentException;
        //  69     85     88     92     Ljava/lang/IllegalArgumentException;
        //  78     122    122    126    Ljava/lang/IllegalArgumentException;
        //  127    143    146    150    Ljava/lang/IllegalArgumentException;
        //  136    180    180    184    Ljava/lang/IllegalArgumentException;
        //  185    222    222    226    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
