// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr;

import com.intellij.openapi.util.text.StringUtil;
import java.util.Collection;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.ApplicationInfo;
import java.util.Arrays;
import java.util.Locale;
import com.intellij.openapi.application.ApplicationNamesInfo;
import java.util.Collections;
import java.util.List;

public class PredefinedVariables
{
    public static final String JETBRAINS_IDE = "JETBRAINS_IDE";
    
    @NotNull
    public static List<String> getIDEVariables() {
        final ApplicationInfo a = a();
        Label_0059: {
            List<String> list = null;
            Label_0024: {
                try {
                    if (a != null) {
                        break Label_0059;
                    }
                    final String s = "JETBRAINS_IDE";
                    list = Collections.singletonList(s);
                    if (list == null) {
                        break Label_0024;
                    }
                    return list;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final String s = "JETBRAINS_IDE";
                    list = Collections.singletonList(s);
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/PredefinedVariables", "getIDEVariables"));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            return list;
        }
        final String string = ApplicationNamesInfo.getInstance().getProductName().toUpperCase(Locale.US) + "_IDE".replaceAll("\\W", "_");
        List<Object> unmodifiableList;
        try {
            unmodifiableList = Collections.unmodifiableList((List<?>)Arrays.asList("JETBRAINS_IDE", string));
            if (unmodifiableList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/PredefinedVariables", "getIDEVariables"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return (List<String>)unmodifiableList;
    }
    
    @NotNull
    public static String getVersionNumber() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/jetbrains/cidr/PredefinedVariables.a:()Lcom/intellij/openapi/application/ApplicationInfo;
        //     3: astore_0       
        //     4: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //     7: astore_1       
        //     8: aload_0        
        //     9: ifnull          39
        //    12: aload_1        
        //    13: ifnull          39
        //    16: goto            23
        //    19: invokestatic    com/jetbrains/cidr/PredefinedVariables.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    22: athrow         
        //    23: aload_1        
        //    24: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //    29: ifeq            90
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/PredefinedVariables.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    38: athrow         
        //    39: ldc             "2017.1"
        //    41: invokestatic    com/jetbrains/cidr/PredefinedVariables.toVersionNumber:(Ljava/lang/String;)Ljava/lang/String;
        //    44: dup            
        //    45: ifnonnull       89
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/PredefinedVariables.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    54: athrow         
        //    55: new             Ljava/lang/IllegalStateException;
        //    58: dup            
        //    59: ldc             "@NotNull method %s.%s must not return null"
        //    61: ldc             2
        //    63: anewarray       Ljava/lang/Object;
        //    66: dup            
        //    67: ldc             0
        //    69: ldc             "com/jetbrains/cidr/PredefinedVariables"
        //    71: aastore        
        //    72: dup            
        //    73: ldc             1
        //    75: ldc             "getVersionNumber"
        //    77: aastore        
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: invokestatic    com/jetbrains/cidr/PredefinedVariables.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    88: athrow         
        //    89: areturn        
        //    90: aload_0        
        //    91: invokevirtual   com/intellij/openapi/application/ApplicationInfo.getFullVersion:()Ljava/lang/String;
        //    94: invokestatic    com/jetbrains/cidr/PredefinedVariables.toVersionNumber:(Ljava/lang/String;)Ljava/lang/String;
        //    97: dup            
        //    98: ifnonnull       135
        //   101: new             Ljava/lang/IllegalStateException;
        //   104: dup            
        //   105: ldc             "@NotNull method %s.%s must not return null"
        //   107: ldc             2
        //   109: anewarray       Ljava/lang/Object;
        //   112: dup            
        //   113: ldc             0
        //   115: ldc             "com/jetbrains/cidr/PredefinedVariables"
        //   117: aastore        
        //   118: dup            
        //   119: ldc             1
        //   121: ldc             "getVersionNumber"
        //   123: aastore        
        //   124: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   127: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   130: athrow         
        //   131: invokestatic    com/jetbrains/cidr/PredefinedVariables.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   134: athrow         
        //   135: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  8      16     19     23     Ljava/lang/IllegalStateException;
        //  12     32     35     39     Ljava/lang/IllegalStateException;
        //  23     48     51     55     Ljava/lang/IllegalStateException;
        //  39     85     85     89     Ljava/lang/IllegalStateException;
        //  90     131    131    135    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
    private static ApplicationInfo a() {
        final Application application = ApplicationManager.getApplication();
        try {
            if (application == null) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ApplicationInfo.getInstance();
    }
    
    static String toVersionNumber(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "version", "com/jetbrains/cidr/PredefinedVariables", "toVersionNumber"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final ArrayList<String> list = new ArrayList<String>(Arrays.asList(s.split("\\D")));
        try {
            while (list.size() < 3) {
                list.add("00");
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        String string = list.get(0);
        String string2 = list.get(1);
        String string3 = list.get(2);
        if (string.length() < 4) {
            string = StringUtil.repeat("0", 4 - string.length()) + string;
        }
        if (string2.length() < 2) {
            string2 = StringUtil.repeat("0", 2 - string2.length()) + string2;
        }
        if (string3.length() < 2) {
            string3 = StringUtil.repeat("0", 2 - string3.length()) + string3;
        }
        return string + string2 + string3;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
