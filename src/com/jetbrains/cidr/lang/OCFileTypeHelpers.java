// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.intellij.openapi.util.io.FileUtilRt;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import java.util.Set;

public class OCFileTypeHelpers
{
    public static final Set<String> HEADER_FILE_EXTENSIONS;
    public static final Set<String> SOURCE_FILE_EXTENSIONS;
    
    @NotNull
    public static OCFileTypeHelper[] getHelpers() {
        OCFileTypeHelper[] array;
        try {
            array = (OCFileTypeHelper[])Extensions.getExtensions((ExtensionPointName)OCFileTypeHelper.EP_NAME);
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/OCFileTypeHelpers", "getHelpers"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return array;
    }
    
    public static boolean isHeaderFile(@Nullable final String s) {
        try {
            if (s == null) {
                return false;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (OCFileTypeHelpers.HEADER_FILE_EXTENSIONS.contains(FileUtilRt.getExtension(s))) {
                return true;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (OCFileTypeHelpers.SOURCE_FILE_EXTENSIONS.contains(FileUtilRt.getExtension(s))) {
                return false;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        for (final OCFileTypeHelper ocFileTypeHelper : getHelpers()) {
            try {
                if (ocFileTypeHelper.isHeaderFile(s)) {
                    return true;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    public static boolean isSourceFile(@Nullable final String s) {
        try {
            if (s == null) {
                return false;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (OCFileTypeHelpers.SOURCE_FILE_EXTENSIONS.contains(FileUtilRt.getExtension(s))) {
                return true;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (OCFileTypeHelpers.HEADER_FILE_EXTENSIONS.contains(FileUtilRt.getExtension(s))) {
                return false;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        for (final OCFileTypeHelper ocFileTypeHelper : getHelpers()) {
            try {
                if (ocFileTypeHelper.isHeaderFile(s)) {
                    return false;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            try {
                if (ocFileTypeHelper.isSourceFile(s)) {
                    return true;
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
        }
        return false;
    }
    
    static {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/intellij/util/text/CaseInsensitiveStringHashingStrategy.INSTANCE:Lcom/intellij/util/text/CaseInsensitiveStringHashingStrategy;
        //     3: bipush          8
        //     5: anewarray       Ljava/lang/String;
        //     8: dup            
        //     9: iconst_0       
        //    10: ldc             ""
        //    12: aastore        
        //    13: dup            
        //    14: iconst_1       
        //    15: ldc             "h"
        //    17: aastore        
        //    18: dup            
        //    19: iconst_2       
        //    20: ldc             "hpp"
        //    22: aastore        
        //    23: dup            
        //    24: iconst_3       
        //    25: ldc             "hh"
        //    27: aastore        
        //    28: dup            
        //    29: iconst_4       
        //    30: ldc             "hxx"
        //    32: aastore        
        //    33: dup            
        //    34: iconst_5       
        //    35: ldc             "tcc"
        //    37: aastore        
        //    38: dup            
        //    39: bipush          6
        //    41: ldc             "pch"
        //    43: aastore        
        //    44: dup            
        //    45: bipush          7
        //    47: ldc             "inl"
        //    49: aastore        
        //    50: invokestatic    com/intellij/util/containers/ContainerUtil.newTroveSet:(Lgnu/trove/TObjectHashingStrategy;[Ljava/lang/Object;)Lgnu/trove/THashSet;
        //    53: putstatic       com/jetbrains/cidr/lang/OCFileTypeHelpers.HEADER_FILE_EXTENSIONS:Ljava/util/Set;
        //    56: getstatic       com/intellij/util/text/CaseInsensitiveStringHashingStrategy.INSTANCE:Lcom/intellij/util/text/CaseInsensitiveStringHashingStrategy;
        //    59: iconst_5       
        //    60: anewarray       Ljava/lang/String;
        //    63: dup            
        //    64: iconst_0       
        //    65: ldc             "c"
        //    67: aastore        
        //    68: dup            
        //    69: iconst_1       
        //    70: ldc             "cp"
        //    72: aastore        
        //    73: dup            
        //    74: iconst_2       
        //    75: ldc             "cpp"
        //    77: aastore        
        //    78: dup            
        //    79: iconst_3       
        //    80: ldc             "cc"
        //    82: aastore        
        //    83: dup            
        //    84: iconst_4       
        //    85: ldc             "cxx"
        //    87: aastore        
        //    88: invokestatic    com/intellij/util/containers/ContainerUtil.newTroveSet:(Lgnu/trove/TObjectHashingStrategy;[Ljava/lang/Object;)Lgnu/trove/THashSet;
        //    91: putstatic       com/jetbrains/cidr/lang/OCFileTypeHelpers.SOURCE_FILE_EXTENSIONS:Ljava/util/Set;
        //    94: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //    97: astore_0       
        //    98: aload_0        
        //    99: ifnull          131
        //   102: aload_0        
        //   103: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   108: ifne            131
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/lang/OCFileTypeHelpers.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   117: athrow         
        //   118: invokestatic    com/intellij/util/PlatformUtils.isAppCode:()Z
        //   121: ifeq            160
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/OCFileTypeHelpers.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   130: athrow         
        //   131: getstatic       com/jetbrains/cidr/lang/OCFileTypeHelpers.SOURCE_FILE_EXTENSIONS:Ljava/util/Set;
        //   134: ldc             "m"
        //   136: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   141: pop            
        //   142: getstatic       com/jetbrains/cidr/lang/OCFileTypeHelpers.SOURCE_FILE_EXTENSIONS:Ljava/util/Set;
        //   145: ldc             "mm"
        //   147: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   152: pop            
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/OCFileTypeHelpers.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   159: athrow         
        //   160: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  98     111    114    118    Ljava/lang/IllegalStateException;
        //  102    124    127    131    Ljava/lang/IllegalStateException;
        //  118    153    156    160    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0118:
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
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
