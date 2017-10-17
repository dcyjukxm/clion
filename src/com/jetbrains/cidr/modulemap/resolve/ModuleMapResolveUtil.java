// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.resolve;

import com.jetbrains.cidr.lang.OCFileTypeHelpers;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;

public class ModuleMapResolveUtil
{
    @NotNull
    public static List<VirtualFile> getIncludeHeaders(@NotNull final ModuleMapModuleSymbol moduleMapModuleSymbol) {
        try {
            if (moduleMapModuleSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "module", "com/jetbrains/cidr/modulemap/resolve/ModuleMapResolveUtil", "getIncludeHeaders"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ArrayList arrayList = ContainerUtil.newArrayList();
        final VirtualFile umbrellaHeader = getUmbrellaHeader(moduleMapModuleSymbol);
        List<VirtualFile> emptyList = null;
        Label_0209: {
            List<Object> list2 = null;
            Label_0174: {
                Label_0131: {
                    try {
                        if (umbrellaHeader != null) {
                            ContainerUtil.addAll((Collection)arrayList, (Object[])new VirtualFile[] { umbrellaHeader });
                            break Label_0131;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    final String umbrellaDir = moduleMapModuleSymbol.getUmbrellaDir();
                    if (umbrellaDir != null) {
                        final VirtualFile resolveDir = moduleMapModuleSymbol.getPathResolver().resolveDir(umbrellaDir);
                        try {
                            if (resolveDir != null) {
                                ContainerUtil.addAll((Collection)arrayList, (Iterable)ContainerUtil.filter((Object[])resolveDir.getChildren(), virtualFile -> {
                                    Label_0024: {
                                        try {
                                            if (virtualFile.isDirectory()) {
                                                return false;
                                            }
                                            final VirtualFile virtualFile2 = virtualFile;
                                            final String s = virtualFile2.getName();
                                            final boolean b = OCFileTypeHelpers.isHeaderFile(s);
                                            if (b) {
                                                break Label_0024;
                                            }
                                            return false;
                                        }
                                        catch (IllegalArgumentException ex) {
                                            throw a(ex);
                                        }
                                        try {
                                            final VirtualFile virtualFile2 = virtualFile;
                                            final String s = virtualFile2.getName();
                                            final boolean b = OCFileTypeHelpers.isHeaderFile(s);
                                            if (b) {
                                                return true;
                                            }
                                        }
                                        catch (IllegalArgumentException ex2) {
                                            throw a(ex2);
                                        }
                                    }
                                    return false;
                                }));
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    try {
                        ContainerUtil.addAll((Collection)arrayList, (Iterable)ContainerUtil.mapNotNull((Collection)moduleMapModuleSymbol.getHeaders(), s -> {
                            try {
                                if (moduleMapModuleSymbol == null) {
                                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "module", "com/jetbrains/cidr/modulemap/resolve/ModuleMapResolveUtil", "lambda$getIncludeHeaders$1"));
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            return moduleMapModuleSymbol.getPathResolver().resolveHeader(s);
                        }));
                        if (arrayList.isEmpty()) {
                            break Label_0209;
                        }
                        final ArrayList<? extends T> list = (ArrayList<? extends T>)arrayList;
                        list2 = Collections.unmodifiableList((List<?>)list);
                        if (list2 == null) {
                            break Label_0174;
                        }
                        return (List<VirtualFile>)list2;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                try {
                    final ArrayList<? extends T> list = (ArrayList<? extends T>)arrayList;
                    list2 = Collections.unmodifiableList((List<?>)list);
                    if (list2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/resolve/ModuleMapResolveUtil", "getIncludeHeaders"));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            return (List<VirtualFile>)list2;
            try {
                emptyList = Collections.emptyList();
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/resolve/ModuleMapResolveUtil", "getIncludeHeaders"));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return emptyList;
    }
    
    @Nullable
    public static VirtualFile getUmbrellaHeader(@NotNull final ModuleMapModuleSymbol moduleMapModuleSymbol) {
        try {
            if (moduleMapModuleSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "module", "com/jetbrains/cidr/modulemap/resolve/ModuleMapResolveUtil", "getUmbrellaHeader"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String umbrellaHeader = moduleMapModuleSymbol.getUmbrellaHeader();
        try {
            if (umbrellaHeader != null) {
                return moduleMapModuleSymbol.getPathResolver().resolveHeader(umbrellaHeader);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
