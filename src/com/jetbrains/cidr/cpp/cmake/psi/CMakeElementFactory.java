// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.PsiFileFactoryImpl;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.SingleRootFileViewProvider;
import com.intellij.lang.Language;
import com.jetbrains.cidr.cpp.cmake.CMakeLanguage;
import com.intellij.testFramework.LightVirtualFile;
import com.jetbrains.cidr.cpp.cmake.CMakeListsFileType;
import com.intellij.psi.PsiFileFactory;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

public class CMakeElementFactory
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    @NotNull
    public static CMakeFile createFile(@NotNull final Project project, @NotNull final CharSequence charSequence) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory", "createFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (charSequence == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory", "createFile"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiFileFactory instance = PsiFileFactory.getInstance(project);
        final LightVirtualFile virtualFile = new LightVirtualFile("dummy." + CMakeListsFileType.INSTANCE.getDefaultExtension(), CMakeListsFileType.INSTANCE, charSequence);
        virtualFile.setLanguage((Language)CMakeLanguage.INSTANCE);
        SingleRootFileViewProvider.doNotCheckFileSizeLimit((VirtualFile)virtualFile);
        final PsiFile trySetupPsiForFile = ((PsiFileFactoryImpl)instance).trySetupPsiForFile(virtualFile, CMakeLanguage.getInstance(), false, true);
        CMakeFile cMakeFile = null;
        Label_0193: {
            Label_0181: {
                try {
                    if (CMakeElementFactory.$assertionsDisabled) {
                        break Label_0193;
                    }
                    final PsiFile psiFile = trySetupPsiForFile;
                    if (psiFile == null) {
                        break Label_0181;
                    }
                    break Label_0193;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final PsiFile psiFile = trySetupPsiForFile;
                    if (psiFile == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            try {
                cMakeFile = (CMakeFile)trySetupPsiForFile;
                if (cMakeFile == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory", "createFile"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return cMakeFile;
    }
    
    @NotNull
    public static CMakeArgument createArgument(@NotNull final Project project, @NotNull final CharSequence charSequence) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory", "createArgument"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (charSequence == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory", "createArgument"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final String cMakeLiteralFromValue = CMakeArgumentManipulator.getCMakeLiteralFromValue(charSequence.toString());
        CMakeArgument argumentRaw;
        try {
            argumentRaw = createArgumentRaw(project, cMakeLiteralFromValue);
            if (argumentRaw == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory", "createArgument"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return argumentRaw;
    }
    
    @NotNull
    public static CMakeArgument createArgumentRaw(@NotNull final Project p0, @NotNull final CharSequence p1) {
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
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "createArgumentRaw"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "text"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "createArgumentRaw"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //    91: aload_1        
        //    92: invokestatic    com/intellij/openapi/util/text/StringUtil.isEmpty:(Ljava/lang/CharSequence;)Z
        //    95: ifne            106
        //    98: iconst_1       
        //    99: goto            107
        //   102: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: iconst_0       
        //   107: ldc             "empty arguments are not allowed"
        //   109: invokevirtual   com/intellij/openapi/diagnostic/Logger.assertTrue:(ZLjava/lang/Object;)Z
        //   112: pop            
        //   113: aload_0        
        //   114: new             Ljava/lang/StringBuilder;
        //   117: dup            
        //   118: invokespecial   java/lang/StringBuilder.<init>:()V
        //   121: ldc             "foo("
        //   123: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   126: aload_1        
        //   127: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   130: ldc             ")"
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   138: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory.createFile:(Lcom/intellij/openapi/project/Project;Ljava/lang/CharSequence;)Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeFile;
        //   141: iconst_4       
        //   142: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/CMakeFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   145: astore_2       
        //   146: aload_2        
        //   147: instanceof      Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;
        //   150: ifne            199
        //   153: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory.$assertionsDisabled:Z
        //   156: ifne            189
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: aload_2        
        //   167: ifnonnull       189
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: new             Ljava/lang/AssertionError;
        //   180: dup            
        //   181: invokespecial   java/lang/AssertionError.<init>:()V
        //   184: athrow         
        //   185: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   188: athrow         
        //   189: aload_2        
        //   190: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   195: astore_2       
        //   196: goto            146
        //   199: aload_2        
        //   200: checkcast       Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;
        //   203: dup            
        //   204: ifnonnull       241
        //   207: new             Ljava/lang/IllegalStateException;
        //   210: dup            
        //   211: ldc             "@NotNull method %s.%s must not return null"
        //   213: ldc             2
        //   215: anewarray       Ljava/lang/Object;
        //   218: dup            
        //   219: ldc             0
        //   221: ldc             "com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory"
        //   223: aastore        
        //   224: dup            
        //   225: ldc             1
        //   227: ldc             "createArgumentRaw"
        //   229: aastore        
        //   230: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   233: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   236: athrow         
        //   237: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   240: athrow         
        //   241: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     102    102    106    Ljava/lang/IllegalArgumentException;
        //  146    159    162    166    Ljava/lang/IllegalArgumentException;
        //  153    170    173    177    Ljava/lang/IllegalArgumentException;
        //  166    185    185    189    Ljava/lang/IllegalArgumentException;
        //  199    237    237    241    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0166:
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
    public static CMakeCommandName createCommandName(@NotNull final Project project, @NotNull final CharSequence charSequence) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory", "createCommandName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (charSequence == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newCommandName", "com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory", "createCommandName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final CMakeFile file = createFile(project, (Object)charSequence + "()\n");
        CMakeCommandName cMakeCommandName;
        try {
            cMakeCommandName = ((CMakeCommand)((PsiFile)file).getFirstChild()).getCMakeCommandName();
            if (cMakeCommandName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory", "createCommandName"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return cMakeCommandName;
    }
    
    @NotNull
    public static PsiElement createWhitespace(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory", "createWhitespace"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement element = createFile(project, " ").findElementAt(0);
        PsiElement psiElement2 = null;
        Label_0084: {
            Label_0072: {
                try {
                    if (CMakeElementFactory.$assertionsDisabled) {
                        break Label_0084;
                    }
                    final PsiElement psiElement = element;
                    if (psiElement == null) {
                        break Label_0072;
                    }
                    break Label_0084;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final PsiElement psiElement = element;
                    if (psiElement == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            try {
                psiElement2 = element;
                if (psiElement2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeElementFactory", "createWhitespace"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return psiElement2;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!CMakeElementFactory.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
