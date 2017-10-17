// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.resolve;

import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.cpp.cmake.psi.util.CMakeFileLocationUtil;
import com.jetbrains.cidr.cpp.CPPLog;
import com.jetbrains.cidr.cpp.cmake.completion.CMakeCompletionUtils;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeRoutine;
import com.intellij.psi.search.PsiElementProcessor;
import java.util.Iterator;
import java.util.Collections;
import com.intellij.util.Processor;
import com.intellij.psi.search.FileTypeIndex;
import com.jetbrains.cidr.cpp.cmake.CMakeListsFileType;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.indexing.ID;
import com.intellij.psi.impl.cache.impl.id.IdIndexEntry;
import com.intellij.psi.impl.cache.impl.id.IdIndex;
import com.intellij.util.indexing.FileBasedIndex;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgumentMixin;
import java.util.List;
import java.util.ArrayList;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgument;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeLiteral;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.psi.ResolveResult;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommand;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceBase;

public class CMakeCommandReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference
{
    private static final boolean USE_CACHE = true;
    
    public CMakeCommandReference(final PsiElement psiElement, final TextRange textRange) {
        super(psiElement, textRange);
    }
    
    public PsiElement handleElementRename(final String s) throws IncorrectOperationException {
        try {
            if (this.myElement.getParent() instanceof CMakeCommand) {
                CMakeRenameUtils.renameCommandCall(this.myElement.getProject(), s, this.myElement.getNode());
                return this.myElement;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0072: {
            try {
                if (CMakeCommandReferenceHelper.isCommandDefinition(this.myElement)) {
                    break Label_0072;
                }
                final CMakeCommandReference cMakeCommandReference = this;
                final PsiElement psiElement = cMakeCommandReference.myElement;
                final String s2 = s;
                final boolean b = CMakeCommandReferenceHelper.isEndCommandDefinition(psiElement, s2);
                if (b) {
                    break Label_0072;
                }
                return this.myElement;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final CMakeCommandReference cMakeCommandReference = this;
                final PsiElement psiElement = cMakeCommandReference.myElement;
                final String s2 = s;
                final boolean b = CMakeCommandReferenceHelper.isEndCommandDefinition(psiElement, s2);
                if (b) {
                    CMakeRenameUtils.renameArgument(this.myElement.getProject(), s, this.myElement.getNode());
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return this.myElement;
    }
    
    public boolean isReferenceTo(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_0        
        //     2: getfield        com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference.myElement:Lcom/intellij/psi/PsiElement;
        //     5: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReferenceHelper.commandNamesEqualByText:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Z
        //     8: ifeq            109
        //    11: aload_0        
        //    12: getfield        com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference.myElement:Lcom/intellij/psi/PsiElement;
        //    15: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReferenceHelper.isCommandCall:(Lcom/intellij/psi/PsiElement;)Z
        //    18: ifne            62
        //    21: goto            28
        //    24: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    27: athrow         
        //    28: aload_0        
        //    29: getfield        com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference.myElement:Lcom/intellij/psi/PsiElement;
        //    32: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReferenceHelper.isCommandDefinition:(Lcom/intellij/psi/PsiElement;)Z
        //    35: ifne            62
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    44: athrow         
        //    45: aload_0        
        //    46: getfield        com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference.myElement:Lcom/intellij/psi/PsiElement;
        //    49: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReferenceHelper.isEndCommandDefinition:(Lcom/intellij/psi/PsiElement;)Z
        //    52: ifeq            109
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    61: athrow         
        //    62: aload_1        
        //    63: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReferenceHelper.isCommandCall:(Lcom/intellij/psi/PsiElement;)Z
        //    66: ifeq            82
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    75: athrow         
        //    76: iconst_1       
        //    77: ireturn        
        //    78: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    81: athrow         
        //    82: aload_1        
        //    83: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReferenceHelper.isCommandDefinition:(Lcom/intellij/psi/PsiElement;)Z
        //    86: ifeq            109
        //    89: aload_1        
        //    90: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReferenceHelper.getCMakeArgument:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;
        //    93: astore_2       
        //    94: aload_2        
        //    95: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeArgument.isCommandDefinitionName:()Z
        //   100: ifeq            109
        //   103: iconst_1       
        //   104: ireturn        
        //   105: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   108: athrow         
        //   109: iconst_0       
        //   110: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      21     24     28     Lcom/intellij/util/IncorrectOperationException;
        //  11     38     41     45     Lcom/intellij/util/IncorrectOperationException;
        //  28     55     58     62     Lcom/intellij/util/IncorrectOperationException;
        //  45     69     72     76     Lcom/intellij/util/IncorrectOperationException;
        //  62     78     78     82     Lcom/intellij/util/IncorrectOperationException;
        //  94     105    105    109    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
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
    public ResolveResult[] multiResolve(final boolean incompleteCode) {
        final ResolveCache instance = ResolveCache.getInstance(this.getElement().getProject());
        ResolveResult[] resolveWithCaching;
        try {
            resolveWithCaching = instance.resolveWithCaching(this, new ResolveCache.PolyVariantResolver<CMakeCommandReference>() {
                @NotNull
                @Override
                public ResolveResult[] resolve(@NotNull final CMakeCommandReference cMakeCommandReference, final boolean b) {
                    try {
                        if (cMakeCommandReference == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference$1", "resolve"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    ResolveResult[] access$000;
                    try {
                        access$000 = cMakeCommandReference.a();
                        if (access$000 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference$1", "resolve"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    return access$000;
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            }, false, incompleteCode);
            if (resolveWithCaching == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference", "multiResolve"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return resolveWithCaching;
    }
    
    private ResolveResult[] a() {
        try {
            if (CMakeCommandReferenceHelper.isCommandDefinition(this.myElement)) {
                return new ResolveResult[] { new PsiElementResolveResult(this.myElement) };
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        CMakeArgumentMixin argument = null;
        if (this.myElement instanceof CMakeLiteral) {
            argument = ((CMakeLiteral)this.myElement).getArgument();
        }
        else if (this.myElement instanceof CMakeArgument) {
            argument = (CMakeArgument)this.myElement;
        }
        if (argument != null) {
            final CMakeArgument commandDefinitionName = argument.getCommandDefinitionName();
            try {
                if (commandDefinitionName != null) {
                    return new ResolveResult[] { new PsiElementResolveResult((PsiElement)commandDefinitionName) };
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        final ArrayList list = new ArrayList<ResolveResult>();
        this.a(list, true);
        return list.toArray(new ResolveResult[list.size()]);
    }
    
    private List<ResolveResult> a(@NotNull final List<ResolveResult> list, @NotNull final PsiFile psiFile, @Nullable final Module module, final boolean b) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commands", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference", "fillCommandReferencesFromModule"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "containingFile", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference", "fillCommandReferencesFromModule"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        List<VirtualFile> filter = null;
        Label_0193: {
            Label_0160: {
                try {
                    if (module == null) {
                        return list;
                    }
                    if (!b) {
                        break Label_0160;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                filter = (List<VirtualFile>)ContainerUtil.filter((Collection)new ArrayList(FileBasedIndex.getInstance().getContainingFiles((ID)IdIndex.NAME, (Object)new IdIndexEntry(this.myElement.getText(), false), module.getModuleWithLibrariesScope())), virtualFile -> {
                    try {
                        if (virtualFile.getFileType() == CMakeListsFileType.INSTANCE) {
                            return true;
                        }
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    return false;
                });
                break Label_0193;
            }
            filter = new ArrayList<VirtualFile>();
            FileTypeIndex.processFiles(CMakeListsFileType.INSTANCE, (Processor<VirtualFile>)(virtualFile -> {
                filter.add(virtualFile);
                return true;
            }), module.getModuleWithLibrariesScope());
        }
        Collections.sort((List<Object>)filter, (virtualFile, virtualFile2) -> virtualFile.getPath().compareToIgnoreCase(virtualFile2.getPath()));
        final Iterator<VirtualFile> iterator = filter.iterator();
        while (iterator.hasNext()) {
            final PsiFile file = this.myElement.getManager().findFile((VirtualFile)iterator.next());
            try {
                if (psiFile.equals(file)) {
                    continue;
                }
                this.a(list, file, b);
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return list;
    }
    
    private List<ResolveResult> a(@NotNull final List<ResolveResult> list, @Nullable final PsiFile psiFile, final boolean b) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commands", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference", "fillCommandsFromFile"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        PsiTreeUtil.processElements((PsiElement)psiFile, (PsiElementProcessor)new PsiElementProcessor() {
            final /* synthetic */ CMakeCommandReference this$0;
            
            public boolean execute(@NotNull final PsiElement psiElement) {
                try {
                    if (psiElement == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference$2", "execute"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                if (psiElement instanceof CMakeRoutine) {
                    final CMakeArgument firstArgument = ((CMakeRoutine)psiElement).getFirstArgument();
                    boolean b = false;
                    Label_0076: {
                        try {
                            if (firstArgument != null) {
                                b = true;
                                break Label_0076;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        b = false;
                    }
                    final boolean b2 = b;
                    boolean b4 = false;
                    Label_0108: {
                        Label_0099: {
                            try {
                                if (!b2) {
                                    break Label_0099;
                                }
                                final CMakeArgument cMakeArgument = firstArgument;
                                final boolean b3 = cMakeArgument.isCommandDefinitionName();
                                if (b3) {
                                    break Label_0099;
                                }
                                break Label_0099;
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                            try {
                                final CMakeArgument cMakeArgument = firstArgument;
                                final boolean b3 = cMakeArgument.isCommandDefinitionName();
                                if (b3) {
                                    b4 = true;
                                    break Label_0108;
                                }
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                        }
                        b4 = false;
                    }
                    final boolean b5 = b4;
                    Label_0217: {
                        try {
                            if (!b5) {
                                return true;
                            }
                            if (!b) {
                                break Label_0217;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                        final CMakeLiteral cMakeLiteral = firstArgument.getCMakeLiteral();
                        boolean b7 = false;
                        Label_0183: {
                            Label_0174: {
                                try {
                                    if (cMakeLiteral == null) {
                                        break Label_0174;
                                    }
                                    final CMakeLiteral cMakeLiteral2 = cMakeLiteral;
                                    final String s = cMakeLiteral2.getText();
                                    final PsiElementProcessor psiElementProcessor = (PsiElementProcessor)this;
                                    final CMakeCommandReference cMakeCommandReference = psiElementProcessor.this$0;
                                    final PsiElement psiElement2 = cMakeCommandReference.myElement;
                                    final String s2 = psiElement2.getText();
                                    final boolean b6 = s.equalsIgnoreCase(s2);
                                    if (b6) {
                                        break Label_0174;
                                    }
                                    break Label_0174;
                                }
                                catch (IllegalArgumentException ex6) {
                                    throw a(ex6);
                                }
                                try {
                                    final CMakeLiteral cMakeLiteral2 = cMakeLiteral;
                                    final String s = cMakeLiteral2.getText();
                                    final PsiElementProcessor psiElementProcessor = (PsiElementProcessor)this;
                                    final CMakeCommandReference cMakeCommandReference = psiElementProcessor.this$0;
                                    final PsiElement psiElement2 = cMakeCommandReference.myElement;
                                    final String s2 = psiElement2.getText();
                                    final boolean b6 = s.equalsIgnoreCase(s2);
                                    if (b6) {
                                        b7 = true;
                                        break Label_0183;
                                    }
                                }
                                catch (IllegalArgumentException ex7) {
                                    throw a(ex7);
                                }
                            }
                            b7 = false;
                        }
                        final boolean b8 = b7;
                        try {
                            if (b8) {
                                list.add(new PsiElementResolveResult((PsiElement)firstArgument));
                                return true;
                            }
                        }
                        catch (IllegalArgumentException ex8) {
                            throw a(ex8);
                        }
                        return true;
                    }
                    list.add(new PsiElementResolveResult((PsiElement)firstArgument));
                    return true;
                }
                return true;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        });
        return list;
    }
    
    @Nullable
    public PsiElement resolve() {
        final ResolveResult[] multiResolve = this.multiResolve(false);
        try {
            if (multiResolve.length == 1) {
                return multiResolve[0].getElement();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @NotNull
    public Object[] getVariants() {
        final List<ResolveResult> c = this.c();
        final Object[] b = this.b();
        Object[] a = null;
        Label_0061: {
            Object[] array = null;
            Label_0026: {
                try {
                    if (b == null) {
                        break Label_0061;
                    }
                    array = b;
                    if (array == null) {
                        break Label_0026;
                    }
                    return array;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    array = b;
                    if (array == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference", "getVariants"));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return array;
            try {
                a = this.a(c);
                if (a == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference", "getVariants"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return a;
    }
    
    @NotNull
    private List<ResolveResult> c() {
        final ArrayList list = new ArrayList<ResolveResult>();
        final ResolveCache instance = ResolveCache.getInstance(this.getElement().getProject());
        ArrayList list2;
        try {
            ContainerUtil.addAll((Collection)list, (Object[])instance.resolveWithCaching(this, new ResolveCache.PolyVariantResolver<CMakeCommandReference>() {
                @NotNull
                @Override
                public ResolveResult[] resolve(@NotNull final CMakeCommandReference cMakeCommandReference, final boolean b) {
                    try {
                        if (cMakeCommandReference == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference$3", "resolve"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    final ArrayList list = new ArrayList();
                    ResolveResult[] array;
                    try {
                        CMakeCommandReference.this.a(list, false);
                        array = (ResolveResult[])list.toArray(new ResolveResult[list.size()]);
                        if (array == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference$3", "resolve"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    return array;
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            }, false, false));
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference", "collectCommandsFromProjectOrCache"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return (List<ResolveResult>)list2;
    }
    
    @Nullable
    private Object[] b() {
        final ArrayList<LookupElement> list = new ArrayList<LookupElement>();
        Label_0040: {
            try {
                if (!(this.myElement instanceof CMakeArgument)) {
                    return null;
                }
                final CMakeCommandReference cMakeCommandReference = this;
                final PsiElement psiElement = cMakeCommandReference.myElement;
                final CMakeArgument cMakeArgument = (CMakeArgument)psiElement;
                final boolean b = cMakeArgument.isCommandDefinitionName();
                if (b) {
                    break Label_0040;
                }
                break Label_0040;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final CMakeCommandReference cMakeCommandReference = this;
                final PsiElement psiElement = cMakeCommandReference.myElement;
                final CMakeArgument cMakeArgument = (CMakeArgument)psiElement;
                final boolean b = cMakeArgument.isCommandDefinitionName();
                if (b) {
                    return LookupElement.EMPTY_ARRAY;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        if (((CMakeArgument)this.myElement).isEndCommandDefinitionName()) {
            final CMakeArgument commandDefinitionName = ((CMakeArgument)this.myElement).getCommandDefinitionName();
            try {
                if (commandDefinitionName != null) {
                    list.add(CMakeCompletionUtils.createCommandItem(commandDefinitionName.getText(), this.myElement.getContainingFile().getName(), Collections.emptyList(), true, false));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            return list.toArray();
        }
        return null;
    }
    
    @NotNull
    private Object[] a(final List<ResolveResult> list) {
        final ArrayList<LookupElement> list2 = new ArrayList<LookupElement>();
        final Iterator<ResolveResult> iterator = list.iterator();
        while (iterator.hasNext()) {
            final PsiElement element = iterator.next().getElement();
            CPPLog.LOG.assertTrue(element instanceof CMakeArgument);
            final CMakeArgument cMakeArgument = (CMakeArgument)element;
            final CMakeLiteral cMakeLiteral = cMakeArgument.getCMakeLiteral();
            Logger log = null;
            boolean b = false;
            Label_0089: {
                try {
                    log = CPPLog.LOG;
                    if (cMakeLiteral != null) {
                        b = true;
                        break Label_0089;
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                b = false;
            }
            log.assertTrue(b);
            final String convertCommandToProperCase = CMakeCompletionUtils.convertCommandToProperCase(element.getProject(), cMakeLiteral.getText());
            final List<CMakeArgument> cMakeArgumentList = cMakeArgument.getParentCommandArguments().getCMakeArgumentList();
            final List<CMakeArgument> subList = cMakeArgumentList.subList(1, cMakeArgumentList.size());
            final String locationInFile = CMakeFileLocationUtil.getLocationInFile(element, false);
            boolean b2 = false;
            Label_0170: {
                try {
                    if (!(this.myElement instanceof CMakeArgument)) {
                        b2 = true;
                        break Label_0170;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                b2 = false;
            }
            list2.add(CMakeCompletionUtils.createCommandItem(convertCommandToProperCase, locationInFile, subList, cMakeArgument.isFunctionName(), b2));
        }
        Object[] array;
        try {
            array = list2.toArray();
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference", "getCompletion"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        return array;
    }
    
    private void a(@NotNull final List<ResolveResult> list, final boolean b) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commands", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference", "fillCommandsFromProject"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final PsiFile containingFile = this.myElement.getContainingFile();
        if (containingFile != null) {
            this.a(list, containingFile, b);
            VirtualFile virtualFile = containingFile.getVirtualFile();
            if (virtualFile == null) {
                virtualFile = containingFile.getOriginalFile().getVirtualFile();
            }
            if (virtualFile != null) {
                this.a(list, containingFile, ProjectRootManager.getInstance(this.myElement.getProject()).getFileIndex().getModuleForFile(virtualFile), b);
            }
        }
    }
    
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final CMakeCommandReference cMakeCommandReference = this;
                final Class<? extends CMakeCommandReference> clazz = cMakeCommandReference.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final CMakeCommandReference cMakeCommandReference = this;
                final Class<? extends CMakeCommandReference> clazz = cMakeCommandReference.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        final PsiReferenceBase psiReferenceBase = (PsiReferenceBase)o;
        Label_0127: {
            Label_0120: {
                Label_0092: {
                    Label_0079: {
                        Label_0072: {
                            try {
                                if (this.getElement() == null) {
                                    break Label_0079;
                                }
                                final CMakeCommandReference cMakeCommandReference2 = this;
                                final PsiElement psiElement = cMakeCommandReference2.getElement();
                                final PsiReferenceBase psiReferenceBase2 = psiReferenceBase;
                                final PsiElement psiElement2 = psiReferenceBase2.getElement();
                                final boolean b = psiElement.equals(psiElement2);
                                if (!b) {
                                    break Label_0072;
                                }
                                break Label_0092;
                            }
                            catch (IncorrectOperationException ex4) {
                                throw a(ex4);
                            }
                            try {
                                final CMakeCommandReference cMakeCommandReference2 = this;
                                final PsiElement psiElement = cMakeCommandReference2.getElement();
                                final PsiReferenceBase psiReferenceBase2 = psiReferenceBase;
                                final PsiElement psiElement2 = psiReferenceBase2.getElement();
                                final boolean b = psiElement.equals(psiElement2);
                                if (!b) {
                                    return false;
                                }
                                break Label_0092;
                            }
                            catch (IncorrectOperationException ex5) {
                                throw a(ex5);
                            }
                        }
                        try {
                            if (psiReferenceBase.getElement() != null) {
                                return false;
                            }
                        }
                        catch (IncorrectOperationException ex6) {
                            throw a(ex6);
                        }
                    }
                    try {
                        if (this.getRangeInElement() == null) {
                            break Label_0127;
                        }
                        final CMakeCommandReference cMakeCommandReference3 = this;
                        final TextRange textRange = cMakeCommandReference3.getRangeInElement();
                        final PsiReferenceBase psiReferenceBase3 = psiReferenceBase;
                        final TextRange textRange2 = psiReferenceBase3.getRangeInElement();
                        final boolean b2 = textRange.equals((Object)textRange2);
                        if (!b2) {
                            break Label_0120;
                        }
                        return true;
                    }
                    catch (IncorrectOperationException ex7) {
                        throw a(ex7);
                    }
                }
                try {
                    final CMakeCommandReference cMakeCommandReference3 = this;
                    final TextRange textRange = cMakeCommandReference3.getRangeInElement();
                    final PsiReferenceBase psiReferenceBase3 = psiReferenceBase;
                    final TextRange textRange2 = psiReferenceBase3.getRangeInElement();
                    final boolean b2 = textRange.equals((Object)textRange2);
                    if (!b2) {
                        return false;
                    }
                    return true;
                }
                catch (IncorrectOperationException ex8) {
                    throw a(ex8);
                }
            }
            try {
                if (psiReferenceBase.getRangeInElement() != null) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex9) {
                throw a(ex9);
            }
        }
        return true;
    }
    
    public int hashCode() {
        int hashCode = 0;
        Label_0022: {
            try {
                if (this.getElement() != null) {
                    hashCode = this.getElement().hashCode();
                    break Label_0022;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            hashCode = 0;
        }
        final int n = hashCode;
        int n2;
        try {
            n2 = 31 * n;
            if (this.getRangeInElement() != null) {
                final int hashCode2 = this.getRangeInElement().hashCode();
                return n2 + hashCode2;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final int hashCode2 = 0;
        return n2 + hashCode2;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
