// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.Lexer;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.lexer.OCLexer;
import java.util.Set;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.openapi.util.SimpleModificationTracker;
import java.util.Collections;
import java.util.Collection;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTable;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.openapi.util.Key;

public interface OCInclusionContext extends OCImmutableInclusionContext
{
    public static final Key<Integer> MAX_INCLUSION_LEVEL_KEY = Key.create("MAX_INCLUSION_LEVE_KEY");
    
    @NotNull
    OCLanguageKind getLanguageKind();
    
    OCInclusionContext setRootFile(@Nullable final PsiFile p0);
    
    void addProcessedFile(@NotNull final VirtualFile p0);
    
    boolean checkConformanceAndFillSignatures(@NotNull final FileSymbolTable p0);
    
    void setProcessingListener(@Nullable final Processor<OCSymbol> p0);
    
    void setResolvePathListener(@Nullable final ResolvePathListener p0);
    
    void setChangeBuilder(@Nullable final OCContextChangeBuilder p0);
    
    void setPrecompiledHeaders(@NotNull final List<OCFile> p0);
    
    @NotNull
    OCParsingNameScope getNameScope();
    
    @NotNull
    OCInclusionContext deriveButDontCopyTypes(final boolean p0);
    
    void define(@NotNull final OCMacroSymbol p0);
    
    void define(final String p0, final String p1);
    
    void undef(final String p0);
    
    boolean isDefined(@Nullable final String p0);
    
    void setSignatureBuilder(@Nullable final SignatureBuilder p0);
    
    @Nullable
    VirtualFile resolveNextPath(@NotNull final OCIncludeSymbol.IncludePath p0, @NotNull final PsiFile p1);
    
    @Nullable
    VirtualFile resolvePath(@NotNull final OCIncludeSymbol.IncludePath p0, @NotNull final PsiFile p1);
    
    boolean reserveInclude(@NotNull final VirtualFile p0, final boolean p1);
    
    void preprocessContextOf(@Nullable final PsiFile p0, @Nullable final VirtualFile p1);
    
    void preprocessInclude(@Nullable final PsiFile p0, final boolean p1);
    
    boolean preprocessInclude(@Nullable final PsiFile p0, final boolean p1, @Nullable final VirtualFile p2, final int p3);
    
    @Contract("_, _, null, _, _, _, _, _ -> !null")
    OCInclusionContext preprocessFile(@Nullable final OCFile p0, @Nullable final VirtualFile p1, @Nullable final VirtualFile p2, final int p3, final int p4, final int p5, @NotNull final List<OCSymbol> p6, @Nullable final OCContextChangeSet p7);
    
    boolean preprocessInclude(@NotNull final PsiFile p0, final boolean p1, @Nullable final VirtualFile p2, final int p3, final int p4, @Nullable final OCContextChangeSet p5);
    
    default int getMaxInclusionLevel(@Nullable final Project project) {
        Integer n = null;
        Label_0024: {
            try {
                if (project != null) {
                    n = (Integer)project.getUserData((Key)OCInclusionContext.MAX_INCLUSION_LEVEL_KEY);
                    break Label_0024;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            n = null;
        }
        final Integer n2 = n;
        try {
            if (n2 != null) {
                return n2;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return OCInclusionContextImpl.DEFAULT_MAX_INCLUSION_LEVEL;
    }
    
    default boolean isPrecompiledHeader(@NotNull final VirtualFile virtualFile, @NotNull final OCResolveConfiguration ocResolveConfiguration) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "isPrecompiledHeader"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "isPrecompiledHeader"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return isPrecompiledHeader(virtualFile, ocResolveConfiguration.getProject());
    }
    
    default boolean isPrecompiledHeader(@NotNull final VirtualFile virtualFile, @NotNull final Project project) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "isPrecompiledHeader"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "isPrecompiledHeader"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return OCInclusionContextImpl.getPCHCache(project).isPCH(virtualFile);
    }
    
    @NotNull
    default Collection<OCResolveConfiguration> getBuildConfigurationByPchFile(@Nullable final VirtualFile virtualFile, @NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "getBuildConfigurationByPchFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Collection<OCResolveConfiguration> configurations = null;
        Label_0097: {
            List<OCResolveConfiguration> list = null;
            Label_0062: {
                try {
                    if (virtualFile != null) {
                        break Label_0097;
                    }
                    list = Collections.emptyList();
                    if (list == null) {
                        break Label_0062;
                    }
                    return list;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    list = Collections.emptyList();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "getBuildConfigurationByPchFile"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return list;
            try {
                configurations = OCInclusionContextImpl.getPCHCache(project).getConfigurations(virtualFile);
                if (configurations == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "getBuildConfigurationByPchFile"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return configurations;
    }
    
    default void onPrecompiledContextChange(@Nullable final OCResolveConfiguration ocResolveConfiguration) {
        try {
            if (ocResolveConfiguration == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Project project = ocResolveConfiguration.getProject();
        try {
            if (project.isDisposed()) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        ((SimpleModificationTracker)OCInclusionContextImpl.PROJECT_MODIFICATION_TRACKER.getValue((UserDataHolder)project)).incModificationCount();
        ocResolveConfiguration.putUserData((Key)OCInclusionContextImpl.INITIAL_PLAIN_AND_PCH_CONTEXTS_KEY, (Object)null);
    }
    
    default void clearSymbolTableConformanceCache(final OCResolveConfiguration ocResolveConfiguration) {
        try {
            if (ocResolveConfiguration == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Project project = ocResolveConfiguration.getProject();
        try {
            if (project.isDisposed()) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        ((SimpleModificationTracker)OCInclusionContextImpl.PROJECT_MODIFICATION_TRACKER.getValue((UserDataHolder)project)).incModificationCount();
        ocResolveConfiguration.putUserData((Key)OCInclusionContextImpl.INCLUDE_RESOLVE_CACHE, (Object)null);
    }
    
    @NotNull
    default OCInclusionContext empty(@NotNull final OCLanguageKind ocLanguageKind, @NotNull final PsiFile rootFile) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "empty"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (rootFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "empty"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        OCInclusionContext setRootFile;
        try {
            setRootFile = new OCInclusionContextImpl(null, rootFile.getProject(), ocLanguageKind).setRootFile(rootFile);
            if (setRootFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "empty"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return setRootFile;
    }
    
    @NotNull
    default OCInclusionContext beforePCHFileContext(@NotNull final OCFile ocFile) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "beforePCHFileContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCResolveConfiguration activeConfiguration = OCInclusionContextUtil.getActiveConfiguration((PsiElement)ocFile);
        OCInclusionContext ocInclusionContext = null;
        Label_0081: {
            try {
                if (activeConfiguration == null) {
                    final OCInclusionContext ocInclusionContext2;
                    ocInclusionContext = (ocInclusionContext2 = OCInclusionContextImpl.emptyWithBuiltinMacros(ocFile.getKind(), (PsiFile)ocFile));
                    break Label_0081;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            OCInclusionContext ocInclusionContext2;
            ocInclusionContext = (ocInclusionContext2 = beforePCHFileContext(activeConfiguration, ocFile.getKind(), (PsiFile)ocFile));
            try {
                if (ocInclusionContext2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "beforePCHFileContext"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return ocInclusionContext;
    }
    
    @NotNull
    default OCInclusionContext beforePCHFileContext(@NotNull final OCResolveConfiguration ocResolveConfiguration, @NotNull final OCLanguageKind ocLanguageKind, @NotNull final PsiFile psiFile) {
        try {
            if (ocResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "beforePCHFileContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "beforePCHFileContext"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "beforePCHFileContext"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        OCInclusionContext derive;
        try {
            derive = OCInclusionContextImpl.initialContextWithoutRoot(ocResolveConfiguration, ocLanguageKind, psiFile).derive(psiFile);
            if (derive == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "beforePCHFileContext"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return derive;
    }
    
    @NotNull
    default OCInclusionContext sourceParsingContext(@NotNull final OCResolveConfiguration ocResolveConfiguration, @NotNull final OCLanguageKind ocLanguageKind, @NotNull final PsiFile psiFile) {
        try {
            if (ocResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "sourceParsingContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "sourceParsingContext"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "sourceParsingContext"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        OCInclusionContext derive;
        try {
            derive = initialPCHContextWithoutRoot(ocResolveConfiguration, ocLanguageKind, psiFile).derive(psiFile);
            if (derive == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "sourceParsingContext"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return derive;
    }
    
    @NotNull
    default OCImmutableInclusionContext initialPCHContextWithoutRoot(@NotNull final OCResolveConfiguration p0, @NotNull final OCLanguageKind p1, @NotNull final PsiFile p2) {
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
        //    18: ldc             "configuration"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "initialPCHContextWithoutRoot"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "kind"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "initialPCHContextWithoutRoot"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "file"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "initialPCHContextWithoutRoot"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_2        
        //   133: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.getVirtualFile:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   136: astore_3       
        //   137: aload_1        
        //   138: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.supportsPrecompiledHeaders:()Z
        //   143: ifeq            172
        //   146: aload_3        
        //   147: ifnull          172
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: aload_3        
        //   158: aload_0        
        //   159: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.isPrecompiledHeader:(Lcom/intellij/openapi/vfs/VirtualFile;Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;)Z
        //   162: ifeq            224
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: aload_0        
        //   173: aload_1        
        //   174: aload_2        
        //   175: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.initialContextWithoutRoot:(Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;
        //   178: dup            
        //   179: ifnonnull       223
        //   182: goto            189
        //   185: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   188: athrow         
        //   189: new             Ljava/lang/IllegalStateException;
        //   192: dup            
        //   193: ldc             "@NotNull method %s.%s must not return null"
        //   195: ldc             2
        //   197: anewarray       Ljava/lang/Object;
        //   200: dup            
        //   201: ldc             0
        //   203: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext"
        //   205: aastore        
        //   206: dup            
        //   207: ldc             1
        //   209: ldc             "initialPCHContextWithoutRoot"
        //   211: aastore        
        //   212: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   215: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   218: athrow         
        //   219: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   222: athrow         
        //   223: areturn        
        //   224: aload_0        
        //   225: invokeinterface com/jetbrains/cidr/lang/workspace/OCResolveConfiguration.getCompilerSettings:()Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerSettings;
        //   230: aload_1        
        //   231: aload_3        
        //   232: invokeinterface com/jetbrains/cidr/lang/workspace/compiler/OCCompilerSettings.getCompilerKey:(Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/intellij/openapi/vfs/VirtualFile;)Ljava/lang/String;
        //   237: astore          4
        //   239: getstatic       com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.INITIAL_PLAIN_AND_PCH_CONTEXTS_KEY:Lcom/intellij/openapi/util/NotNullLazyKey;
        //   242: aload_0        
        //   243: invokevirtual   com/intellij/openapi/util/NotNullLazyKey.getValue:(Lcom/intellij/openapi/util/UserDataHolder;)Ljava/lang/Object;
        //   246: checkcast       Lcom/intellij/openapi/util/Couple;
        //   249: getfield        com/intellij/openapi/util/Couple.second:Ljava/lang/Object;
        //   252: checkcast       Ljava/util/concurrent/ConcurrentHashMap;
        //   255: astore          5
        //   257: aload           5
        //   259: aload           4
        //   261: invokevirtual   java/util/concurrent/ConcurrentHashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   264: checkcast       Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;
        //   267: astore          6
        //   269: aload           6
        //   271: ifnull          322
        //   274: aload           6
        //   276: dup            
        //   277: ifnonnull       321
        //   280: goto            287
        //   283: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   286: athrow         
        //   287: new             Ljava/lang/IllegalStateException;
        //   290: dup            
        //   291: ldc             "@NotNull method %s.%s must not return null"
        //   293: ldc             2
        //   295: anewarray       Ljava/lang/Object;
        //   298: dup            
        //   299: ldc             0
        //   301: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext"
        //   303: aastore        
        //   304: dup            
        //   305: ldc             1
        //   307: ldc             "initialPCHContextWithoutRoot"
        //   309: aastore        
        //   310: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   313: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   316: athrow         
        //   317: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   320: athrow         
        //   321: areturn        
        //   322: aload_0        
        //   323: aload_1        
        //   324: aload_3        
        //   325: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.getPrecompiledHeaders:(Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/intellij/openapi/vfs/VirtualFile;)Ljava/util/List;
        //   328: astore          7
        //   330: aload           7
        //   332: invokeinterface java/util/List.isEmpty:()Z
        //   337: ifeq            392
        //   340: aload_0        
        //   341: aload_1        
        //   342: aload_2        
        //   343: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.initialContextWithoutRoot:(Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;
        //   346: dup            
        //   347: ifnonnull       391
        //   350: goto            357
        //   353: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   356: athrow         
        //   357: new             Ljava/lang/IllegalStateException;
        //   360: dup            
        //   361: ldc             "@NotNull method %s.%s must not return null"
        //   363: ldc             2
        //   365: anewarray       Ljava/lang/Object;
        //   368: dup            
        //   369: ldc             0
        //   371: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext"
        //   373: aastore        
        //   374: dup            
        //   375: ldc             1
        //   377: ldc             "initialPCHContextWithoutRoot"
        //   379: aastore        
        //   380: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   383: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   386: athrow         
        //   387: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   390: athrow         
        //   391: areturn        
        //   392: aload_0        
        //   393: aload_1        
        //   394: aload_2        
        //   395: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.initialContextWithoutRoot:(Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;
        //   398: astore          8
        //   400: aload           8
        //   402: aload_2        
        //   403: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext.derive:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   408: astore          9
        //   410: aload           7
        //   412: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   417: astore          10
        //   419: aload           10
        //   421: invokeinterface java/util/Iterator.hasNext:()Z
        //   426: ifeq            454
        //   429: aload           10
        //   431: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   436: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   439: astore          11
        //   441: aload           9
        //   443: aload           11
        //   445: iconst_1       
        //   446: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.preprocessInclude:(Lcom/intellij/psi/PsiFile;Z)V
        //   451: goto            419
        //   454: aload           9
        //   456: aconst_null    
        //   457: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.setRootFile:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   462: pop            
        //   463: aload           9
        //   465: aload           7
        //   467: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.setPrecompiledHeaders:(Ljava/util/List;)V
        //   472: aload           5
        //   474: aload           4
        //   476: aload           9
        //   478: invokevirtual   java/util/concurrent/ConcurrentHashMap.putIfAbsent:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   481: checkcast       Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext;
        //   484: astore          10
        //   486: aload           10
        //   488: ifnonnull       500
        //   491: aload           9
        //   493: goto            502
        //   496: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   499: athrow         
        //   500: aload           10
        //   502: dup            
        //   503: ifnonnull       540
        //   506: new             Ljava/lang/IllegalStateException;
        //   509: dup            
        //   510: ldc             "@NotNull method %s.%s must not return null"
        //   512: ldc             2
        //   514: anewarray       Ljava/lang/Object;
        //   517: dup            
        //   518: ldc             0
        //   520: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext"
        //   522: aastore        
        //   523: dup            
        //   524: ldc             1
        //   526: ldc             "initialPCHContextWithoutRoot"
        //   528: aastore        
        //   529: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   532: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   535: athrow         
        //   536: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   539: athrow         
        //   540: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  137    150    153    157    Ljava/lang/IllegalArgumentException;
        //  146    165    168    172    Ljava/lang/IllegalArgumentException;
        //  157    182    185    189    Ljava/lang/IllegalArgumentException;
        //  172    219    219    223    Ljava/lang/IllegalArgumentException;
        //  269    280    283    287    Ljava/lang/IllegalArgumentException;
        //  274    317    317    321    Ljava/lang/IllegalArgumentException;
        //  330    350    353    357    Ljava/lang/IllegalArgumentException;
        //  340    387    387    391    Ljava/lang/IllegalArgumentException;
        //  486    496    496    500    Ljava/lang/IllegalArgumentException;
        //  502    536    536    540    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0157:
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
    default OCImmutableInclusionContext tryFindInCachedPCHPrecompiledContexts(@NotNull final OCResolveConfiguration ocResolveConfiguration, @NotNull final VirtualFile virtualFile, @NotNull final VirtualFile virtualFile2) {
        try {
            if (ocResolveConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "tryFindInCachedPCHPrecompiledContexts"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "header", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "tryFindInCachedPCHPrecompiledContexts"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (virtualFile2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pchCandidate", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "tryFindInCachedPCHPrecompiledContexts"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        Label_0162: {
            try {
                if (!isPrecompiledHeader(virtualFile2, ocResolveConfiguration)) {
                    return null;
                }
                final OCResolveConfiguration ocResolveConfiguration2 = ocResolveConfiguration;
                final VirtualFile virtualFile3 = virtualFile2;
                final ProgressIndicator progressIndicator = null;
                final Set<VirtualFile> set = OCImportGraph.getAllRootHeaders(ocResolveConfiguration2, virtualFile3, progressIndicator);
                final VirtualFile virtualFile4 = virtualFile;
                final boolean b = set.contains(virtualFile4);
                if (b) {
                    break Label_0162;
                }
                return null;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final OCResolveConfiguration ocResolveConfiguration2 = ocResolveConfiguration;
                final VirtualFile virtualFile3 = virtualFile2;
                final ProgressIndicator progressIndicator = null;
                final Set<VirtualFile> set = OCImportGraph.getAllRootHeaders(ocResolveConfiguration2, virtualFile3, progressIndicator);
                final VirtualFile virtualFile4 = virtualFile;
                final boolean b = set.contains(virtualFile4);
                if (b) {
                    return OCInclusionContextImpl.findInCachedPCHPrecompiledContexts(ocResolveConfiguration, virtualFile);
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return null;
    }
    
    @Nullable
    default String extractFirstPathLiteralText(@NotNull final CharSequence charSequence) {
        try {
            if (charSequence == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "contentWithoutMacros", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext", "extractFirstPathLiteralText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Lexer rawLexerForPreprocessor = OCLexer.createRawLexerForPreprocessor();
        rawLexerForPreprocessor.start(charSequence, 0, charSequence.length(), 10);
        while (rawLexerForPreprocessor.getState() == 10) {
            final IElementType tokenType = rawLexerForPreprocessor.getTokenType();
            try {
                if (tokenType == null) {
                    break;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (tokenType == OCTokenTypes.HEADER_PATH_LITERAL) {
                    return rawLexerForPreprocessor.getTokenText();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            rawLexerForPreprocessor.advance();
        }
        return null;
    }
    
    @NotNull
    default OCIncludeSymbol.IncludePath extractPath(@Nullable final CharSequence p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          16
        //     4: aload_0        
        //     5: invokeinterface java/lang/CharSequence.length:()I
        //    10: dup            
        //    11: istore_2       
        //    12: iconst_2       
        //    13: if_icmpge       65
        //    16: getstatic       com/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol$IncludePath.EMPTY:Lcom/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol$IncludePath;
        //    19: dup            
        //    20: ifnonnull       64
        //    23: goto            30
        //    26: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    29: athrow         
        //    30: new             Ljava/lang/IllegalStateException;
        //    33: dup            
        //    34: ldc             "@NotNull method %s.%s must not return null"
        //    36: ldc             2
        //    38: anewarray       Ljava/lang/Object;
        //    41: dup            
        //    42: ldc             0
        //    44: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext"
        //    46: aastore        
        //    47: dup            
        //    48: ldc             1
        //    50: ldc             "extractPath"
        //    52: aastore        
        //    53: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    56: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    59: athrow         
        //    60: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: areturn        
        //    65: iconst_0       
        //    66: istore_3       
        //    67: aload_0        
        //    68: iconst_0       
        //    69: invokeinterface java/lang/CharSequence.charAt:(I)C
        //    74: istore          4
        //    76: iload           4
        //    78: bipush          34
        //    80: if_icmpeq       97
        //    83: iload           4
        //    85: bipush          60
        //    87: if_icmpne       99
        //    90: goto            97
        //    93: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    96: athrow         
        //    97: iconst_1       
        //    98: istore_3       
        //    99: aload_0        
        //   100: iload_2        
        //   101: iconst_1       
        //   102: isub           
        //   103: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   108: istore          5
        //   110: iload           5
        //   112: bipush          34
        //   114: if_icmpne       131
        //   117: iload           4
        //   119: bipush          34
        //   121: if_icmpeq       159
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: iload           5
        //   133: bipush          62
        //   135: if_icmpne       169
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: iload           4
        //   147: bipush          60
        //   149: if_icmpne       169
        //   152: goto            159
        //   155: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   158: athrow         
        //   159: iinc            2, -1
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aload_0        
        //   170: iload_3        
        //   171: iload_2        
        //   172: invokeinterface java/lang/CharSequence.subSequence:(II)Ljava/lang/CharSequence;
        //   177: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //   182: astore          6
        //   184: iload_1        
        //   185: ifeq            199
        //   188: aload           6
        //   190: ldc             "\\\n"
        //   192: ldc             ""
        //   194: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   197: astore          6
        //   199: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol$IncludePath;
        //   202: dup            
        //   203: aload           6
        //   205: iload           4
        //   207: bipush          34
        //   209: if_icmpeq       220
        //   212: iconst_1       
        //   213: goto            221
        //   216: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   219: athrow         
        //   220: iconst_0       
        //   221: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol$IncludePath.<init>:(Ljava/lang/String;Z)V
        //   224: dup            
        //   225: ifnonnull       258
        //   228: new             Ljava/lang/IllegalStateException;
        //   231: dup            
        //   232: ldc             "@NotNull method %s.%s must not return null"
        //   234: ldc             2
        //   236: anewarray       Ljava/lang/Object;
        //   239: dup            
        //   240: ldc             0
        //   242: ldc             "com/jetbrains/cidr/lang/preprocessor/OCInclusionContext"
        //   244: aastore        
        //   245: dup            
        //   246: ldc             1
        //   248: ldc             "extractPath"
        //   250: aastore        
        //   251: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   254: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   257: athrow         
        //   258: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  12     23     26     30     Ljava/lang/IllegalArgumentException;
        //  16     60     60     64     Ljava/lang/IllegalArgumentException;
        //  76     90     93     97     Ljava/lang/IllegalArgumentException;
        //  110    124    127    131    Ljava/lang/IllegalArgumentException;
        //  117    138    141    145    Ljava/lang/IllegalArgumentException;
        //  131    152    155    159    Ljava/lang/IllegalArgumentException;
        //  145    162    165    169    Ljava/lang/IllegalArgumentException;
        //  199    216    216    220    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0016:
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
    
    default IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public interface ResolvePathListener
    {
        void resolve(@NotNull final OCIncludeSymbol.IncludePath p0, final boolean p1, @Nullable final VirtualFile p2);
    }
    
    public interface SignatureBuilder
    {
        void setDefined(@NotNull final String p0, final boolean p1);
        
        void setDefinition(@NotNull final String p0, @NotNull final OCMacroSymbol p1);
        
        void enterConformanceCheckMode();
        
        void exitConformanceCheckMode(final boolean p0);
    }
}
