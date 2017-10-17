// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.headerRoots;

import com.intellij.psi.PsiElement;
import com.intellij.psi.search.PsiElementProcessor;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;
import java.io.File;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapRealFrameworkPathResolver;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapPathResolver;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapSymbolBuilder;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapFileSymbol;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFileSystemItem;

public class RealFramework extends AppleFramework
{
    @Nullable
    private final PsiFileSystemItem myParent;
    @NotNull
    private final VirtualFile myBaseFile;
    
    public RealFramework(@NotNull final Project project, @NotNull final VirtualFile myBaseFile, @Nullable final PsiFileSystemItem myParent) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework", "<init>"));
        }
        if (myBaseFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseFile", "com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework", "<init>"));
        }
        super(project, myBaseFile.getNameWithoutExtension());
        this.myParent = myParent;
        this.myBaseFile = myBaseFile;
    }
    
    @Nullable
    @Override
    public PsiFileSystemItem getParent() {
        return (PsiFileSystemItem)this.myManager.findDirectory(this.myBaseFile);
    }
    
    @NotNull
    @Override
    public ModuleMapFileSymbol getModuleMap() {
        ModuleMapFileSymbol moduleMap;
        try {
            moduleMap = super.getModuleMap();
            if (moduleMap == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework", "getModuleMap"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return moduleMap;
    }
    
    @NotNull
    @Override
    protected ModuleMapFileSymbol buildModuleMap() {
        ModuleMapFileSymbol moduleMapFileSymbol = super.buildModuleMap();
        if (moduleMapFileSymbol == null) {
            moduleMapFileSymbol = new ModuleMapSymbolBuilder(this.myProject, this.createModuleMapPathResolver()).buildLegacy(this.myBaseFile.getNameWithoutExtension());
        }
        ModuleMapFileSymbol moduleMapFileSymbol2;
        try {
            moduleMapFileSymbol2 = moduleMapFileSymbol;
            if (moduleMapFileSymbol2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework", "buildModuleMap"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return moduleMapFileSymbol2;
    }
    
    @NotNull
    @Override
    protected ModuleMapPathResolver createModuleMapPathResolver() {
        ModuleMapRealFrameworkPathResolver moduleMapRealFrameworkPathResolver;
        try {
            moduleMapRealFrameworkPathResolver = new ModuleMapRealFrameworkPathResolver(this.myBaseFile);
            if (moduleMapRealFrameworkPathResolver == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework", "createModuleMapPathResolver"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return moduleMapRealFrameworkPathResolver;
    }
    
    @Nullable
    @Override
    protected File findModuleMapFile() {
        File file = null;
        final File file2 = new File(this.myBaseFile.getPath(), "Modules");
        Label_0086: {
            Label_0051: {
                try {
                    if (!file2.exists() || !file2.isDirectory()) {
                        break Label_0051;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                file = new File(file2, "module.modulemap");
                try {
                    if (file != null) {
                        if (file.exists()) {
                            break Label_0086;
                        }
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            file = new File(this.myBaseFile.getPath(), "module.map");
        }
        if (!file.exists()) {
            file = null;
        }
        return file;
    }
    
    @Nullable
    @Override
    public PsiFileSystemItem getParentSdkOrFrameworkItem() {
        return this.myParent;
    }
    
    @NotNull
    public VirtualFile getVirtualFile() {
        VirtualFile myBaseFile;
        try {
            myBaseFile = this.myBaseFile;
            if (myBaseFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework", "getVirtualFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myBaseFile;
    }
    
    @Override
    public boolean containsHeader(@Nullable final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final ModuleMapModuleSymbol mainFrameworkModule = this.getMainFrameworkModule();
        try {
            if (mainFrameworkModule == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return mainFrameworkModule.getPathResolver().containsHeader(virtualFile);
    }
    
    public boolean processChildren(final PsiElementProcessor<PsiFileSystemItem> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework.myBaseFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //     4: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isValid:()Z
        //     7: ifeq            129
        //    10: aload_1        
        //    11: instanceof      Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRootProcessor;
        //    14: ifeq            48
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_1        
        //    25: checkcast       Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRootProcessor;
        //    28: aload_0        
        //    29: invokevirtual   com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRootProcessor.processFramework:(Lcom/jetbrains/cidr/lang/workspace/headerRoots/RealFramework;)Z
        //    32: ifne            48
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: iconst_0       
        //    43: ireturn        
        //    44: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    47: athrow         
        //    48: getstatic       com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework.HEADERS_DIR_NAMES:[Ljava/lang/String;
        //    51: astore_2       
        //    52: aload_2        
        //    53: arraylength    
        //    54: istore_3       
        //    55: iconst_0       
        //    56: istore          4
        //    58: iload           4
        //    60: iload_3        
        //    61: if_icmpge       99
        //    64: aload_2        
        //    65: iload           4
        //    67: aaload         
        //    68: astore          5
        //    70: aload_0        
        //    71: aload_1        
        //    72: aload_0        
        //    73: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework.myBaseFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //    76: aload           5
        //    78: invokevirtual   com/intellij/openapi/vfs/VirtualFile.findChild:(Ljava/lang/String;)Lcom/intellij/openapi/vfs/VirtualFile;
        //    81: invokevirtual   com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework.processUnder:(Lcom/intellij/psi/search/PsiElementProcessor;Lcom/intellij/openapi/vfs/VirtualFile;)Z
        //    84: ifne            93
        //    87: iconst_0       
        //    88: ireturn        
        //    89: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: iinc            4, 1
        //    96: goto            58
        //    99: aload_0        
        //   100: aload_0        
        //   101: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework.myBaseFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   104: ldc             "Frameworks"
        //   106: invokevirtual   com/intellij/openapi/vfs/VirtualFile.findChild:(Ljava/lang/String;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   109: aload_1        
        //   110: new             Lgnu/trove/THashSet;
        //   113: dup            
        //   114: invokespecial   gnu/trove/THashSet.<init>:()V
        //   117: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework.processFrameworksUnder:(Lcom/intellij/psi/PsiFileSystemItem;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/psi/search/PsiElementProcessor;Ljava/util/Set;)Z
        //   120: ifne            129
        //   123: iconst_0       
        //   124: ireturn        
        //   125: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/RealFramework.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   128: athrow         
        //   129: iconst_1       
        //   130: ireturn        
        //    Signature:
        //  (Lcom/intellij/psi/search/PsiElementProcessor<Lcom/intellij/psi/PsiFileSystemItem;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  10     35     38     42     Ljava/lang/IllegalArgumentException;
        //  24     44     44     48     Ljava/lang/IllegalArgumentException;
        //  70     89     89     93     Ljava/lang/IllegalArgumentException;
        //  99     125    125    129    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final RealFramework realFramework = this;
                final Class<? extends RealFramework> clazz = realFramework.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final RealFramework realFramework = this;
                final Class<? extends RealFramework> clazz = realFramework.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                if (!super.equals(o)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        final RealFramework realFramework2 = (RealFramework)o;
        try {
            if (!this.myBaseFile.equals(realFramework2.myBaseFile)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        Label_0113: {
            Label_0106: {
                try {
                    if (this.myParent == null) {
                        break Label_0113;
                    }
                    final RealFramework realFramework3 = this;
                    final PsiFileSystemItem psiFileSystemItem = realFramework3.myParent;
                    final RealFramework realFramework4 = realFramework2;
                    final PsiFileSystemItem psiFileSystemItem2 = realFramework4.myParent;
                    final boolean b = psiFileSystemItem.equals(psiFileSystemItem2);
                    if (!b) {
                        break Label_0106;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
                try {
                    final RealFramework realFramework3 = this;
                    final PsiFileSystemItem psiFileSystemItem = realFramework3.myParent;
                    final RealFramework realFramework4 = realFramework2;
                    final PsiFileSystemItem psiFileSystemItem2 = realFramework4.myParent;
                    final boolean b = psiFileSystemItem.equals(psiFileSystemItem2);
                    if (!b) {
                        return false;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex7) {
                    throw b(ex7);
                }
            }
            try {
                if (realFramework2.myParent != null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw b(ex8);
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int hashCode = super.hashCode();
        int n;
        try {
            n = 31 * hashCode;
            if (this.myParent != null) {
                final int hashCode2 = this.myParent.hashCode();
                return 31 * (n + hashCode2) + this.myBaseFile.hashCode();
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final int hashCode2 = 0;
        return 31 * (n + hashCode2) + this.myBaseFile.hashCode();
    }
    
    public String toString() {
        return "AppleFramework: [" + this.getName() + ": " + this.myBaseFile.getPath() + "]";
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
