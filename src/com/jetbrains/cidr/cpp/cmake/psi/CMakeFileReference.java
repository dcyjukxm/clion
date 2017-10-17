// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.intellij.refactoring.rename.FragmentaryPsiReference;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReference;

class CMakeFileReference extends FileReference implements FragmentaryPsiReference
{
    @NotNull
    protected final CMakeFileReferenceSet cmakeFileReferenceSet;
    protected final boolean isExpressionRef;
    
    public CMakeFileReference(@NotNull final CMakeFileReferenceSet set, @NotNull final TextRange range, final int index, @NotNull final String text) {
        if (set == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileReferenceSet", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference", "<init>"));
        }
        if (range == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference", "<init>"));
        }
        if (text == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference", "<init>"));
        }
        super(set, range, index, text);
        this.cmakeFileReferenceSet = set;
        this.isExpressionRef = a(text);
        if (this.isExpressionRef) {
            this.cmakeFileReferenceSet.setFragmentOnlyRename(true);
        }
    }
    
    @NotNull
    @Override
    protected ResolveResult[] innerResolve(final boolean caseSensitive, @NotNull final PsiFile containingFile) {
        try {
            if (containingFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "containingFile", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference", "innerResolve"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ResolveResult[] innerResolve = null;
        Label_0105: {
            ResolveResult[] array = null;
            Label_0070: {
                try {
                    if (!a(this.getText())) {
                        break Label_0105;
                    }
                    final CMakeFileReference cMakeFileReference = this;
                    final PsiFile psiFile = containingFile;
                    array = cMakeFileReference.a(psiFile);
                    if (array == null) {
                        break Label_0070;
                    }
                    return array;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final CMakeFileReference cMakeFileReference = this;
                    final PsiFile psiFile = containingFile;
                    array = cMakeFileReference.a(psiFile);
                    if (array == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference", "innerResolve"));
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            return array;
            try {
                innerResolve = super.innerResolve(caseSensitive, containingFile);
                if (innerResolve == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference", "innerResolve"));
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return innerResolve;
    }
    
    private static boolean a(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference", "isVariableRef"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0069: {
            try {
                if (!s.startsWith("${")) {
                    return false;
                }
                final String s2 = s;
                final String s3 = "}";
                final boolean b = s2.endsWith(s3);
                if (b) {
                    break Label_0069;
                }
                return false;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final String s2 = s;
                final String s3 = "}";
                final boolean b = s2.endsWith(s3);
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    @NotNull
    @Override
    public PsiElement handleElementRename(@NotNull final String newElementName) throws IncorrectOperationException {
        try {
            if (newElementName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newElementName", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference", "handleElementRename"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        PsiElement psiElement = null;
        Label_0067: {
            try {
                if (this.isReadOnlyFragment()) {
                    final PsiElement psiElement2;
                    psiElement = (psiElement2 = this.getElement());
                    break Label_0067;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            PsiElement psiElement2;
            psiElement = (psiElement2 = super.handleElementRename(newElementName));
            try {
                if (psiElement2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference", "handleElementRename"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return psiElement;
    }
    
    @NotNull
    private ResolveResult[] a(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "containingFile", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference", "resolveVariable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        String substring = this.getText().substring(1);
        if (substring.startsWith("{")) {
            final int index = substring.indexOf(125, 1);
            String substring2 = null;
            Label_0090: {
                try {
                    if (index < 1) {
                        substring2 = "";
                        break Label_0090;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                substring2 = substring.substring(1, index);
            }
            substring = substring2;
        }
        ResolveResult[] empty_ARRAY = null;
        Label_0248: {
            if (!substring.isEmpty()) {
                Object o = null;
                ResolveResult[] array = null;
                Label_0213: {
                    Label_0182: {
                        if (substring.equals("CMAKE_CURRENT_LIST_FILE")) {
                            o = psiFile;
                        }
                        else {
                            Label_0149: {
                                try {
                                    if (!substring.equals("CMAKE_CURRENT_LIST_DIR")) {
                                        if (!substring.equals("CMAKE_CURRENT_SOURCE_DIR")) {
                                            break Label_0149;
                                        }
                                    }
                                }
                                catch (IncorrectOperationException ex3) {
                                    throw a(ex3);
                                }
                                o = psiFile.getParent();
                                break Label_0182;
                            }
                            if (substring.equals("PROJECT_SOURCE_DIR")) {
                                final Project project = psiFile.getProject();
                                o = PsiManager.getInstance(project).findDirectory(project.getBaseDir());
                            }
                        }
                        try {
                            if (o == null) {
                                break Label_0248;
                            }
                            final int n = 1;
                            array = new ResolveResult[n];
                            final int n2 = 0;
                            final PsiElement psiElement = (PsiElement)o;
                            final boolean b = false;
                            final PsiElementResolveResult psiElementResolveResult = new PsiElementResolveResult(psiElement, b);
                            array[n2] = (ResolveResult)psiElementResolveResult;
                            if (array == null) {
                                break Label_0213;
                            }
                            return array;
                        }
                        catch (IncorrectOperationException ex4) {
                            throw a(ex4);
                        }
                    }
                    try {
                        final int n = 1;
                        array = new ResolveResult[n];
                        final int n2 = 0;
                        final PsiElement psiElement = (PsiElement)o;
                        final boolean b = false;
                        final PsiElementResolveResult psiElementResolveResult = new PsiElementResolveResult(psiElement, b);
                        array[n2] = (ResolveResult)psiElementResolveResult;
                        if (array == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference", "resolveVariable"));
                        }
                    }
                    catch (IncorrectOperationException ex5) {
                        throw a(ex5);
                    }
                }
                return array;
            }
            try {
                empty_ARRAY = ResolveResult.EMPTY_ARRAY;
                if (empty_ARRAY == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference", "resolveVariable"));
                }
            }
            catch (IncorrectOperationException ex6) {
                throw a(ex6);
            }
        }
        return empty_ARRAY;
    }
    
    @NotNull
    @Override
    public Object[] getVariants() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference.getElement:()Lcom/intellij/psi/PsiElement;
        //     4: instanceof      Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeLiteral;
        //     7: ifeq            97
        //    10: aload_0        
        //    11: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference.getElement:()Lcom/intellij/psi/PsiElement;
        //    14: checkcast       Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeLiteral;
        //    17: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeLiteral.getArgument:()Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;
        //    22: astore_1       
        //    23: aload_1        
        //    24: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeArgument.isCommandDefinitionName:()Z
        //    29: ifne            48
        //    32: aload_1        
        //    33: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeArgument.isEndCommandDefinitionName:()Z
        //    38: ifeq            97
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    47: athrow         
        //    48: getstatic       com/intellij/codeInsight/lookup/LookupElement.EMPTY_ARRAY:[Lcom/intellij/codeInsight/lookup/LookupElement;
        //    51: dup            
        //    52: ifnonnull       96
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    61: athrow         
        //    62: new             Ljava/lang/IllegalStateException;
        //    65: dup            
        //    66: ldc             "@NotNull method %s.%s must not return null"
        //    68: ldc             2
        //    70: anewarray       Ljava/lang/Object;
        //    73: dup            
        //    74: ldc             0
        //    76: ldc             "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference"
        //    78: aastore        
        //    79: dup            
        //    80: ldc             1
        //    82: ldc             "getVariants"
        //    84: aastore        
        //    85: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    88: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    91: athrow         
        //    92: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    95: athrow         
        //    96: areturn        
        //    97: aload_0        
        //    98: invokespecial   com/intellij/psi/impl/source/resolve/reference/impl/providers/FileReference.getVariants:()[Ljava/lang/Object;
        //   101: dup            
        //   102: ifnonnull       139
        //   105: new             Ljava/lang/IllegalStateException;
        //   108: dup            
        //   109: ldc             "@NotNull method %s.%s must not return null"
        //   111: ldc             2
        //   113: anewarray       Ljava/lang/Object;
        //   116: dup            
        //   117: ldc             0
        //   119: ldc             "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference"
        //   121: aastore        
        //   122: dup            
        //   123: ldc             1
        //   125: ldc             "getVariants"
        //   127: aastore        
        //   128: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   131: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   134: athrow         
        //   135: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   138: athrow         
        //   139: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  23     41     44     48     Lcom/intellij/util/IncorrectOperationException;
        //  32     55     58     62     Lcom/intellij/util/IncorrectOperationException;
        //  48     92     92     96     Lcom/intellij/util/IncorrectOperationException;
        //  97     135    135    139    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
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
    
    @Override
    public boolean isReadOnlyFragment() {
        return this.isExpressionRef;
    }
    
    @Override
    public boolean isFragmentOnlyRename() {
        return this.cmakeFileReferenceSet.isFragmentOnlyRename();
    }
    
    @Nullable
    @Override
    protected String encode(@NotNull final String name, @NotNull final PsiElement psiElement) {
        try {
            if (name == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference", "encode"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference", "encode"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return super.encode(name, psiElement);
    }
    
    @NotNull
    @Override
    public String decode(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference", "decode"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        String decode;
        try {
            decode = super.decode(StringUtil.unescapeStringCharacters(s));
            if (decode == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReference", "decode"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return decode;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
