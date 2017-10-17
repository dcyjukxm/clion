// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.headerRoots;

import com.intellij.openapi.application.ApplicationManager;
import icons.CidrLangIcons;
import javax.swing.Icon;
import java.util.Iterator;
import java.util.Collections;
import com.intellij.psi.impl.SyntheticFileSystemItem;
import java.util.Set;
import com.intellij.psi.search.PsiElementProcessor;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapFileSymbol;
import com.intellij.util.containers.ContainerUtil;
import java.util.List;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapResolveUtil;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.openapi.project.Project;
import java.util.Comparator;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.util.Condition;
import org.jetbrains.annotations.NotNull;

public abstract class AppleFramework extends HeadersSearchRoot
{
    public static boolean SORTED_ITERATION;
    public static final String HEADERS_DIR_NAME = "Headers";
    public static final String PRIVATE_HEADERS_DIR_NAME = "PrivateHeaders";
    public static final String MODULES_DIR_NAME = "Modules";
    public static final String FRAMEWORKS_DIR_NAME = "Frameworks";
    public static final String[] HEADERS_DIR_NAMES;
    @NotNull
    private final String myName;
    static final Condition<VirtualFile> FRAMEWORK_CONDITION;
    private static final Comparator<VirtualFile> FILE_NAME_COMPARATOR;
    
    protected AppleFramework(@NotNull final Project project, @NotNull final String myName) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/headerRoots/AppleFramework", "<init>"));
        }
        if (myName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/workspace/headerRoots/AppleFramework", "<init>"));
        }
        super(project);
        this.myName = myName;
    }
    
    @NotNull
    @Override
    public String getName() {
        String myName;
        try {
            myName = this.myName;
            if (myName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/AppleFramework", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myName;
    }
    
    @Nullable
    public abstract PsiFileSystemItem getParentSdkOrFrameworkItem();
    
    public boolean isLibrary() {
        return false;
    }
    
    @Nullable
    public VirtualFile getMainFile() {
        final ModuleMapModuleSymbol mainFrameworkModule = this.getMainFrameworkModule();
        try {
            if (mainFrameworkModule != null) {
                return ModuleMapResolveUtil.getUmbrellaHeader(mainFrameworkModule);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @NotNull
    public List<VirtualFile> getPublicHeadersToInclude() {
        final ModuleMapModuleSymbol mainFrameworkModule = this.getMainFrameworkModule();
        List maybeSingletonList = null;
        Label_0059: {
            List<VirtualFile> list = null;
            Label_0024: {
                try {
                    if (mainFrameworkModule == null) {
                        break Label_0059;
                    }
                    final ModuleMapModuleSymbol moduleMapModuleSymbol = mainFrameworkModule;
                    list = ModuleMapResolveUtil.getIncludeHeaders(moduleMapModuleSymbol);
                    if (list == null) {
                        break Label_0024;
                    }
                    return list;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final ModuleMapModuleSymbol moduleMapModuleSymbol = mainFrameworkModule;
                    list = ModuleMapResolveUtil.getIncludeHeaders(moduleMapModuleSymbol);
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/AppleFramework", "getPublicHeadersToInclude"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return list;
            try {
                maybeSingletonList = ContainerUtil.createMaybeSingletonList((Object)this.getMainFile());
                if (maybeSingletonList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/AppleFramework", "getPublicHeadersToInclude"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return (List<VirtualFile>)maybeSingletonList;
    }
    
    @Nullable
    protected ModuleMapModuleSymbol getMainFrameworkModule() {
        final ModuleMapFileSymbol moduleMap = this.getModuleMap();
        try {
            if (moduleMap != null) {
                return moduleMap.findModule(this.getName());
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public abstract boolean containsHeader(@Nullable final VirtualFile p0);
    
    public boolean process(@NotNull final PsiElementProcessor<PsiFileSystemItem> processor, @NotNull final Set<String> set) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/workspace/headerRoots/AppleFramework", "process"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processed", "com/jetbrains/cidr/lang/workspace/headerRoots/AppleFramework", "process"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (!set.add(this.getName())) {
                return true;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (processor instanceof HeadersSearchRootProcessor) {
                return this.processChildren((PsiElementProcessor)processor);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return SyntheticFileSystemItem.processFileSystemItem(processor, (PsiFileSystemItem)this);
    }
    
    public static boolean processFrameworksUnder(@NotNull final PsiFileSystemItem psiFileSystemItem, @Nullable final VirtualFile virtualFile, @NotNull final PsiElementProcessor<PsiFileSystemItem> psiElementProcessor, @NotNull final Set<String> set) {
        try {
            if (psiFileSystemItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parentSdkOrFramework", "com/jetbrains/cidr/lang/workspace/headerRoots/AppleFramework", "processFrameworksUnder"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElementProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/workspace/headerRoots/AppleFramework", "processFrameworksUnder"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processed", "com/jetbrains/cidr/lang/workspace/headerRoots/AppleFramework", "processFrameworksUnder"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (virtualFile == null || !virtualFile.isDirectory()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final List filter = ContainerUtil.filter((Object[])virtualFile.getChildren(), (Condition)AppleFramework.FRAMEWORK_CONDITION);
        try {
            if (AppleFramework.SORTED_ITERATION) {
                Collections.sort((List<Object>)filter, (Comparator<? super Object>)AppleFramework.FILE_NAME_COMPARATOR);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        final Iterator<VirtualFile> iterator = filter.iterator();
        while (iterator.hasNext()) {
            final RealFramework realFramework = new RealFramework(psiFileSystemItem.getProject(), iterator.next(), psiFileSystemItem);
            try {
                if (!realFramework.process(psiElementProcessor, set)) {
                    return false;
                }
                continue;
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return true;
    }
    
    public Icon getIcon(final int n) {
        return CidrLangIcons.Framework;
    }
    
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final AppleFramework appleFramework = this;
                final Class<? extends AppleFramework> clazz = appleFramework.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final AppleFramework appleFramework = this;
                final Class<? extends AppleFramework> clazz = appleFramework.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final AppleFramework appleFramework2 = (AppleFramework)o;
        try {
            if (!this.myName.equals(appleFramework2.myName)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return true;
    }
    
    public int hashCode() {
        return this.myName.hashCode();
    }
    
    static {
        AppleFramework.SORTED_ITERATION = ApplicationManager.getApplication().isUnitTestMode();
        HEADERS_DIR_NAMES = new String[] { "Headers", "PrivateHeaders" };
        FRAMEWORK_CONDITION = (virtualFile -> {
            Label_0026: {
                try {
                    if (!virtualFile.isDirectory()) {
                        return false;
                    }
                    final VirtualFile virtualFile2 = virtualFile;
                    final String s = virtualFile2.getName();
                    final String s2 = ".framework";
                    final boolean b = s.endsWith(s2);
                    if (b) {
                        break Label_0026;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final VirtualFile virtualFile2 = virtualFile;
                    final String s = virtualFile2.getName();
                    final String s2 = ".framework";
                    final boolean b = s.endsWith(s2);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return false;
        });
        FILE_NAME_COMPARATOR = ((virtualFile, virtualFile2) -> virtualFile.getName().compareTo(virtualFile2.getName()));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
