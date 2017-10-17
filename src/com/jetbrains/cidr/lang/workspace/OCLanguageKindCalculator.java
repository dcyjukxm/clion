// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace;

import com.jetbrains.cidr.lang.preprocessor.OCImmutableInclusionContext;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.CLanguageKind;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import java.util.Iterator;
import com.jetbrains.cidr.lang.OCLanguageKindProvider;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.OCLanguageKind;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.Key;

public class OCLanguageKindCalculator
{
    public static final Key<Boolean> FORCE_FILE_TYPE_IN_TESTS;
    
    @NotNull
    public static OCLanguageKind calculateLanguageKindFast(@NotNull final PsiFile p0) {
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
        //    18: ldc             "file"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "calculateLanguageKindFast"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //    50: astore_2       
        //    51: aload_0        
        //    52: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.getVirtualFile:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/vfs/VirtualFile;
        //    55: astore_3       
        //    56: aload_3        
        //    57: ifnull          223
        //    60: aload_2        
        //    61: aload_3        
        //    62: invokestatic    com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator.tryFileTypeAndExtension:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //    65: astore_1       
        //    66: aload_1        
        //    67: ifnull          117
        //    70: aload_1        
        //    71: dup            
        //    72: ifnonnull       116
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    81: athrow         
        //    82: new             Ljava/lang/IllegalStateException;
        //    85: dup            
        //    86: ldc             "@NotNull method %s.%s must not return null"
        //    88: ldc             2
        //    90: anewarray       Ljava/lang/Object;
        //    93: dup            
        //    94: ldc             0
        //    96: ldc             "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator"
        //    98: aastore        
        //    99: dup            
        //   100: ldc             1
        //   102: ldc             "calculateLanguageKindFast"
        //   104: aastore        
        //   105: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   108: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   111: athrow         
        //   112: invokestatic    com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: areturn        
        //   117: aload_0        
        //   118: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   121: ifeq            289
        //   124: aload_0        
        //   125: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.isNeedToFindRoot:(Lcom/intellij/psi/PsiFile;)Z
        //   128: ifeq            289
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: aload_0        
        //   139: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   142: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getAssociatedFileWithSameName:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   147: astore          4
        //   149: aload           4
        //   151: ifnull          220
        //   154: aload           4
        //   156: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.isNeedToFindRoot:(Lcom/intellij/psi/PsiFile;)Z
        //   159: ifne            220
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aload           4
        //   171: invokestatic    com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator.calculateLanguageKindFast:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   174: dup            
        //   175: ifnonnull       219
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: new             Ljava/lang/IllegalStateException;
        //   188: dup            
        //   189: ldc             "@NotNull method %s.%s must not return null"
        //   191: ldc             2
        //   193: anewarray       Ljava/lang/Object;
        //   196: dup            
        //   197: ldc             0
        //   199: ldc             "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator"
        //   201: aastore        
        //   202: dup            
        //   203: ldc             1
        //   205: ldc             "calculateLanguageKindFast"
        //   207: aastore        
        //   208: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   211: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   214: athrow         
        //   215: invokestatic    com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   218: athrow         
        //   219: areturn        
        //   220: goto            289
        //   223: aload_0        
        //   224: invokeinterface com/intellij/psi/PsiFile.getName:()Ljava/lang/String;
        //   229: astore          4
        //   231: aload_2        
        //   232: aload           4
        //   234: invokestatic    com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator.tryFileExtension:(Lcom/intellij/openapi/project/Project;Ljava/lang/String;)Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   237: astore_1       
        //   238: aload_1        
        //   239: ifnull          289
        //   242: aload_1        
        //   243: dup            
        //   244: ifnonnull       288
        //   247: goto            254
        //   250: invokestatic    com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   253: athrow         
        //   254: new             Ljava/lang/IllegalStateException;
        //   257: dup            
        //   258: ldc             "@NotNull method %s.%s must not return null"
        //   260: ldc             2
        //   262: anewarray       Ljava/lang/Object;
        //   265: dup            
        //   266: ldc             0
        //   268: ldc             "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator"
        //   270: aastore        
        //   271: dup            
        //   272: ldc             1
        //   274: ldc             "calculateLanguageKindFast"
        //   276: aastore        
        //   277: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   280: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   283: athrow         
        //   284: invokestatic    com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   287: athrow         
        //   288: areturn        
        //   289: aload_2        
        //   290: invokestatic    com/jetbrains/cidr/lang/CLanguageKind.maxLanguage:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/CLanguageKind;
        //   293: dup            
        //   294: ifnonnull       331
        //   297: new             Ljava/lang/IllegalStateException;
        //   300: dup            
        //   301: ldc             "@NotNull method %s.%s must not return null"
        //   303: ldc             2
        //   305: anewarray       Ljava/lang/Object;
        //   308: dup            
        //   309: ldc             0
        //   311: ldc             "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator"
        //   313: aastore        
        //   314: dup            
        //   315: ldc             1
        //   317: ldc             "calculateLanguageKindFast"
        //   319: aastore        
        //   320: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   323: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   326: athrow         
        //   327: invokestatic    com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   330: athrow         
        //   331: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  66     75     78     82     Ljava/lang/IllegalArgumentException;
        //  70     112    112    116    Ljava/lang/IllegalArgumentException;
        //  117    131    134    138    Ljava/lang/IllegalArgumentException;
        //  149    162    165    169    Ljava/lang/IllegalArgumentException;
        //  154    178    181    185    Ljava/lang/IllegalArgumentException;
        //  169    215    215    219    Ljava/lang/IllegalArgumentException;
        //  238    247    250    254    Ljava/lang/IllegalArgumentException;
        //  242    284    284    288    Ljava/lang/IllegalArgumentException;
        //  289    327    327    331    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0169:
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
    public static OCLanguageKind tryFileTypeAndExtension(@Nullable final Project project, @Nullable final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCLanguageKind a = a(project, virtualFile);
        try {
            if (a != null) {
                return a;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCLanguageKind tryFileExtension = tryFileExtension(project, virtualFile.getName());
        try {
            if (tryFileExtension != null) {
                return tryFileExtension;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        if (virtualFile.getUserData((Key)OCLanguageKindCalculator.FORCE_FILE_TYPE_IN_TESTS) == Boolean.TRUE) {
            final String extension = virtualFile.getExtension();
            for (final OCLanguageKind ocLanguageKind : OCLanguageKindProvider.getAllLanguageKinds()) {
                try {
                    if (ocLanguageKind.getDefaultSourceExtension().equalsIgnoreCase(extension)) {
                        return ocLanguageKind;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
        }
        return null;
    }
    
    @Nullable
    private static OCLanguageKind a(@Nullable final Project project, @Nullable final VirtualFile virtualFile) {
        Label_0015: {
            try {
                if (project == null) {
                    break Label_0015;
                }
                final VirtualFile virtualFile2 = virtualFile;
                if (virtualFile2 == null) {
                    break Label_0015;
                }
                break Label_0015;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final VirtualFile virtualFile2 = virtualFile;
                if (virtualFile2 == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final OCLanguageKindCalculatorHelper[] a = a();
        for (int length = a.length, i = 0; i < length; ++i) {
            final OCLanguageKind specifiedLanguage = a[i].getSpecifiedLanguage(project, virtualFile);
            try {
                if (specifiedLanguage != null) {
                    return specifiedLanguage;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return null;
    }
    
    @Nullable
    public static OCLanguageKind tryFileExtension(@Nullable final Project project, @Nullable final String s) {
        Label_0015: {
            try {
                if (project == null) {
                    break Label_0015;
                }
                final String s2 = s;
                if (s2 == null) {
                    break Label_0015;
                }
                break Label_0015;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final String s2 = s;
                if (s2 == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final OCLanguageKindCalculatorHelper[] a = a();
        for (int length = a.length, i = 0; i < length; ++i) {
            final OCLanguageKind languageByExtension = a[i].getLanguageByExtension(project, s);
            try {
                if (languageByExtension != null) {
                    return languageByExtension;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return null;
    }
    
    @NotNull
    private static OCLanguageKindCalculatorHelper[] a() {
        OCLanguageKindCalculatorHelper[] array;
        try {
            array = (OCLanguageKindCalculatorHelper[])Extensions.getExtensions((ExtensionPointName)OCLanguageKindCalculatorHelper.EP_NAME);
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator", "getHelpers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return array;
    }
    
    @NotNull
    public static OCLanguageKind calculateLanguageKind(@NotNull final OCFile ocFile) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator", "calculateLanguageKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0129: {
            if (ocFile instanceof OCCodeFragment) {
                final PsiElement context = ocFile.getContext();
                if (context != null) {
                    final PsiFile containingFile = context.getContainingFile();
                    OCLanguageKind ocLanguageKind = null;
                    Label_0094: {
                        try {
                            if (!(containingFile instanceof OCFile)) {
                                break Label_0129;
                            }
                            final OCFile ocFile2 = (OCFile)containingFile;
                            final OCFile ocFile3 = ocFile2;
                            ocLanguageKind = calculateLanguageKind(ocFile3);
                            if (ocLanguageKind == null) {
                                break Label_0094;
                            }
                            return ocLanguageKind;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final OCFile ocFile2 = (OCFile)containingFile;
                            final OCFile ocFile3 = ocFile2;
                            ocLanguageKind = calculateLanguageKind(ocFile3);
                            if (ocLanguageKind == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator", "calculateLanguageKind"));
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    return ocLanguageKind;
                }
            }
        }
        final Project project = ocFile.getProject();
        final VirtualFile virtualFile = OCInclusionContextUtil.getVirtualFile((PsiFile)ocFile);
        if (virtualFile == null) {
            final OCLanguageKind tryFileExtension = tryFileExtension(project, ocFile.getName());
            CLanguageKind maxLanguage = null;
            Label_0212: {
                OCLanguageKind ocLanguageKind2 = null;
                Label_0177: {
                    try {
                        if (tryFileExtension == null) {
                            break Label_0212;
                        }
                        ocLanguageKind2 = tryFileExtension;
                        if (ocLanguageKind2 == null) {
                            break Label_0177;
                        }
                        return ocLanguageKind2;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        ocLanguageKind2 = tryFileExtension;
                        if (ocLanguageKind2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator", "calculateLanguageKind"));
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                return ocLanguageKind2;
                try {
                    maxLanguage = CLanguageKind.maxLanguage(project);
                    if (maxLanguage == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator", "calculateLanguageKind"));
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            return maxLanguage;
        }
        OCLanguageKind a;
        try {
            a = a(OCInclusionContextUtil.getActiveConfiguration((PsiElement)ocFile), virtualFile, project);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator", "calculateLanguageKind"));
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        return a;
    }
    
    @NotNull
    private static OCLanguageKind a(@Nullable final OCResolveConfiguration ocResolveConfiguration, @NotNull final VirtualFile virtualFile, @NotNull final Project project) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator", "calculateLanguageKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator", "calculateLanguageKind"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0151: {
            if (ocResolveConfiguration != null) {
                final OCLanguageKind declaredLanguageKind = ocResolveConfiguration.getDeclaredLanguageKind(virtualFile);
                OCLanguageKind ocLanguageKind = null;
                Label_0116: {
                    try {
                        if (declaredLanguageKind == null) {
                            break Label_0151;
                        }
                        ocLanguageKind = declaredLanguageKind;
                        if (ocLanguageKind == null) {
                            break Label_0116;
                        }
                        return ocLanguageKind;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        ocLanguageKind = declaredLanguageKind;
                        if (ocLanguageKind == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator", "calculateLanguageKind"));
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                return ocLanguageKind;
            }
        }
        final OCLanguageKind tryFileTypeAndExtension = tryFileTypeAndExtension(project, virtualFile);
        Label_0208: {
            OCLanguageKind ocLanguageKind2 = null;
            Label_0173: {
                try {
                    if (tryFileTypeAndExtension == null) {
                        break Label_0208;
                    }
                    ocLanguageKind2 = tryFileTypeAndExtension;
                    if (ocLanguageKind2 == null) {
                        break Label_0173;
                    }
                    return ocLanguageKind2;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    ocLanguageKind2 = tryFileTypeAndExtension;
                    if (ocLanguageKind2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator", "calculateLanguageKind"));
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            return ocLanguageKind2;
        }
        OCLanguageKind ocLanguageKind4 = null;
        Label_0398: {
            Label_0377: {
                if (OCInclusionContextUtil.isNeedToFindRoot(virtualFile, project)) {
                    final OCResolveRootAndConfiguration resolveRootAndActiveConfiguration = OCInclusionContextUtil.getResolveRootAndActiveConfiguration(virtualFile, project);
                    final VirtualFile rootFile = resolveRootAndActiveConfiguration.getRootFile();
                    final OCResolveConfiguration configuration = resolveRootAndActiveConfiguration.getConfiguration();
                    Label_0254: {
                        try {
                            if (rootFile == null) {
                                break Label_0377;
                            }
                            final OCResolveConfiguration ocResolveConfiguration2 = configuration;
                            if (ocResolveConfiguration2 != null) {
                                break Label_0254;
                            }
                            break Label_0377;
                        }
                        catch (IllegalArgumentException ex7) {
                            throw a(ex7);
                        }
                        try {
                            final OCResolveConfiguration ocResolveConfiguration2 = configuration;
                            if (ocResolveConfiguration2 == null) {
                                break Label_0377;
                            }
                            if (rootFile.equals(virtualFile)) {
                                break Label_0377;
                            }
                        }
                        catch (IllegalArgumentException ex8) {
                            throw a(ex8);
                        }
                    }
                    final OCLanguageKind tryLanguageKindFromPchRoot = tryLanguageKindFromPchRoot(configuration, virtualFile, rootFile);
                    OCLanguageKind a = null;
                    Label_0330: {
                        OCLanguageKind ocLanguageKind3 = null;
                        Label_0295: {
                            try {
                                if (tryLanguageKindFromPchRoot == null) {
                                    break Label_0330;
                                }
                                ocLanguageKind3 = tryLanguageKindFromPchRoot;
                                if (ocLanguageKind3 == null) {
                                    break Label_0295;
                                }
                                return ocLanguageKind3;
                            }
                            catch (IllegalArgumentException ex9) {
                                throw a(ex9);
                            }
                            try {
                                ocLanguageKind3 = tryLanguageKindFromPchRoot;
                                if (ocLanguageKind3 == null) {
                                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator", "calculateLanguageKind"));
                                }
                            }
                            catch (IllegalArgumentException ex10) {
                                throw a(ex10);
                            }
                        }
                        return ocLanguageKind3;
                        try {
                            a = a(configuration, rootFile, project);
                            if (a == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator", "calculateLanguageKind"));
                            }
                        }
                        catch (IllegalArgumentException ex11) {
                            throw a(ex11);
                        }
                    }
                    return a;
                }
                try {
                    if (ocResolveConfiguration != null) {
                        final OCLanguageKind ocLanguageKind5;
                        ocLanguageKind4 = (ocLanguageKind5 = ocResolveConfiguration.getMaximumLanguageKind());
                        break Label_0398;
                    }
                }
                catch (IllegalArgumentException ex12) {
                    throw a(ex12);
                }
            }
            OCLanguageKind ocLanguageKind5;
            ocLanguageKind4 = (ocLanguageKind5 = CLanguageKind.maxLanguage(project));
            try {
                if (ocLanguageKind5 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator", "calculateLanguageKind"));
                }
            }
            catch (IllegalArgumentException ex13) {
                throw a(ex13);
            }
        }
        return ocLanguageKind4;
    }
    
    @Nullable
    public static OCLanguageKind tryLanguageKindFromPchRoot(@NotNull final OCResolveConfiguration ocResolveConfiguration, @NotNull final VirtualFile virtualFile, @NotNull final VirtualFile virtualFile2) {
        try {
            if (ocResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config", "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator", "tryLanguageKindFromPchRoot"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "header", "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator", "tryLanguageKindFromPchRoot"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (virtualFile2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pchCandidate", "com/jetbrains/cidr/lang/workspace/OCLanguageKindCalculator", "tryLanguageKindFromPchRoot"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final OCImmutableInclusionContext tryFindInCachedPCHPrecompiledContexts = OCInclusionContext.tryFindInCachedPCHPrecompiledContexts(ocResolveConfiguration, virtualFile, virtualFile2);
        try {
            if (tryFindInCachedPCHPrecompiledContexts != null) {
                return tryFindInCachedPCHPrecompiledContexts.getLanguageKind();
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return null;
    }
    
    static {
        FORCE_FILE_TYPE_IN_TESTS = Key.create("FORCE_FILE_TYPE_IN_TESTS");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
