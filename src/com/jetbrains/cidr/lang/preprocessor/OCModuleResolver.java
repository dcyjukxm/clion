// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Processor;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.extensions.ExtensionPointName;

public abstract class OCModuleResolver
{
    private static final ExtensionPointName<OCModuleResolver> EP_NAME;
    
    public static boolean processModuleImports(@NotNull final OCInclusionContext ocInclusionContext, @NotNull final List<String> list, @NotNull final Processor<VirtualFile> processor) {
        try {
            if (ocInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/preprocessor/OCModuleResolver", "processModuleImports"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "moduleNameParts", "com/jetbrains/cidr/lang/preprocessor/OCModuleResolver", "processModuleImports"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerProcessor", "com/jetbrains/cidr/lang/preprocessor/OCModuleResolver", "processModuleImports"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        boolean b = true;
        final OCModuleResolver[] array = (OCModuleResolver[])OCModuleResolver.EP_NAME.getExtensions();
        for (int length = array.length, i = 0; i < length; ++i) {
            b &= array[i].processImports(ocInclusionContext, list, processor);
        }
        return b;
    }
    
    @Nullable
    public static String getGuessedPath(@NotNull final List<String> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "moduleNameParts", "com/jetbrains/cidr/lang/preprocessor/OCModuleResolver", "getGuessedPath"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list.isEmpty()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        if (list.size() == 1) {
            final String s = list.get(0);
            return s + "/" + s + ".h";
        }
        final StringBuilder sb = new StringBuilder();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final String s2 = iterator.next();
            try {
                sb.append(s2);
                if (!iterator.hasNext()) {
                    continue;
                }
                sb.append("/");
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        sb.append(".h");
        return sb.toString();
    }
    
    protected abstract boolean processImports(@NotNull final OCInclusionContext p0, @NotNull final List<String> p1, @NotNull final Processor<VirtualFile> p2);
    
    static {
        EP_NAME = ExtensionPointName.create("cidr.lang.moduleResolver");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
