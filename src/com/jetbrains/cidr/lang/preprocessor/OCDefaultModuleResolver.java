// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.jetbrains.cidr.modulemap.resolve.ModuleMapResolveUtil;
import com.jetbrains.cidr.lang.workspace.headerRoots.AppleFramework;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.lang.workspace.OCResolveRootAndConfiguration;
import com.intellij.openapi.project.Project;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.OCIncludeHelpers;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapWalker;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Processor;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class OCDefaultModuleResolver extends OCModuleResolver
{
    @Override
    protected boolean processImports(@NotNull final OCInclusionContext ocInclusionContext, @NotNull final List<String> list, @NotNull final Processor<VirtualFile> processor) {
        try {
            if (ocInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/preprocessor/OCDefaultModuleResolver", "processImports"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "moduleNameParts", "com/jetbrains/cidr/lang/preprocessor/OCDefaultModuleResolver", "processImports"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerProcessor", "com/jetbrains/cidr/lang/preprocessor/OCDefaultModuleResolver", "processImports"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (list.isEmpty()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        if (ocInclusionContext.hasRootFile()) {
            final String s = list.get(0);
            final Project project = ocInclusionContext.getProject();
            final VirtualFile virtualFile = ocInclusionContext.getRootFile().getVirtualFile();
            if (virtualFile != null) {
                final OCResolveRootAndConfiguration resolveRootAndActiveConfiguration = OCInclusionContextUtil.getResolveRootAndActiveConfiguration(virtualFile, project);
                final OCResolveConfiguration configuration = resolveRootAndActiveConfiguration.getConfiguration();
                if (configuration != null) {
                    new ModuleMapWalker(true, (Processor<ModuleMapModuleSymbol>)(moduleMapModuleSymbol -> {
                        try {
                            if (processor == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerProcessor", "com/jetbrains/cidr/lang/preprocessor/OCDefaultModuleResolver", "lambda$processImports$0"));
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw b(ex);
                        }
                        try {
                            if (moduleMapModuleSymbol.isInferred()) {
                                return true;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        return ContainerUtil.process((List)ModuleMapResolveUtil.getIncludeHeaders(moduleMapModuleSymbol), processor);
                    })).process(StringUtil.join((Collection)list, "."), configuration);
                }
                final AppleFramework framework = OCIncludeHelpers.getFramework(resolveRootAndActiveConfiguration, s);
                Label_0276: {
                    try {
                        if (framework == null) {
                            return false;
                        }
                        final List<String> list2 = list;
                        final int n = list2.size();
                        final boolean b = true;
                        if (n == (b ? 1 : 0)) {
                            break Label_0276;
                        }
                        return false;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                    try {
                        final List<String> list2 = list;
                        final int n = list2.size();
                        final boolean b = true;
                        if (n == (b ? 1 : 0)) {
                            ContainerUtil.process((List)framework.getPublicHeadersToInclude(), (Processor)processor);
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                }
            }
        }
        return false;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
