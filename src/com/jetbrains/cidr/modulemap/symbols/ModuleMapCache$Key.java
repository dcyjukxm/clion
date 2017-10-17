// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import kotlin.TypeCastException;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import com.jetbrains.cidr.lang.workspace.headerRoots.IncludedHeadersRoot;
import com.jetbrains.cidr.lang.workspace.headerRoots.RealFramework;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB%\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\f\u001a\u00020\rH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapCache$Key;", "", "framework", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/RealFramework;", "includedHeaders", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/IncludedHeadersRoot;", "searchRoot", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot;", "(Lcom/jetbrains/cidr/lang/workspace/headerRoots/RealFramework;Lcom/jetbrains/cidr/lang/workspace/headerRoots/IncludedHeadersRoot;Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot;)V", "equals", "", "other", "hashCode", "", "Companion", "cidr-lang" })
public static final class Key
{
    private final RealFramework framework;
    private final IncludedHeadersRoot includedHeaders;
    private final HeadersSearchRoot searchRoot;
    public static final Companion Companion;
    
    @Override
    public boolean equals(@Nullable final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (TypeCastException ex) {
            throw b(ex);
        }
        Label_0031: {
            try {
                if (o != null) {
                    final Class<?> class1 = o.getClass();
                    break Label_0031;
                }
            }
            catch (TypeCastException ex2) {
                throw b(ex2);
            }
            final Class<?> class1 = null;
            try {
                if (Intrinsics.areEqual((Object)class1, (Object)this.getClass()) ^ true) {
                    return false;
                }
            }
            catch (TypeCastException ex3) {
                throw b(ex3);
            }
        }
        try {
            if (o == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.jetbrains.cidr.modulemap.symbols.ModuleMapCache.Key");
            }
        }
        catch (TypeCastException ex4) {
            throw b(ex4);
        }
        HeadersSearchRoot headersSearchRoot = null;
        Label_0098: {
            try {
                final Key key = (Key)o;
                final RealFramework framework = this.framework;
                if (framework != null) {
                    headersSearchRoot = framework;
                    break Label_0098;
                }
            }
            catch (TypeCastException ex5) {
                throw b(ex5);
            }
            headersSearchRoot = this.includedHeaders;
        }
        final HeadersSearchRoot headersSearchRoot2 = headersSearchRoot;
        HeadersSearchRoot headersSearchRoot3 = null;
        Label_0131: {
            try {
                final RealFramework framework2 = ((Key)o).framework;
                if (framework2 != null) {
                    headersSearchRoot3 = framework2;
                    break Label_0131;
                }
            }
            catch (TypeCastException ex6) {
                throw b(ex6);
            }
            headersSearchRoot3 = ((Key)o).includedHeaders;
        }
        final HeadersSearchRoot headersSearchRoot4 = headersSearchRoot3;
        Label_0200: {
            Class<? extends HeadersSearchRoot> class2 = null;
            Label_0183: {
                Label_0166: {
                    Label_0147: {
                        try {
                            if (headersSearchRoot2 != null) {
                                break Label_0166;
                            }
                            final HeadersSearchRoot headersSearchRoot5 = headersSearchRoot4;
                            if (headersSearchRoot5 == null) {
                                break Label_0147;
                            }
                            break Label_0166;
                        }
                        catch (TypeCastException ex7) {
                            throw b(ex7);
                        }
                        try {
                            final HeadersSearchRoot headersSearchRoot5 = headersSearchRoot4;
                            if (headersSearchRoot5 == null) {
                                return Objects.equals(this.searchRoot, ((Key)o).searchRoot);
                            }
                        }
                        catch (TypeCastException ex8) {
                            throw b(ex8);
                        }
                    }
                    try {
                        final HeadersSearchRoot headersSearchRoot6 = headersSearchRoot2;
                        if (headersSearchRoot6 != null) {
                            class2 = headersSearchRoot6.getClass();
                            break Label_0183;
                        }
                    }
                    catch (TypeCastException ex9) {
                        throw b(ex9);
                    }
                }
                class2 = null;
                try {
                    final HeadersSearchRoot headersSearchRoot7 = headersSearchRoot4;
                    if (headersSearchRoot7 != null) {
                        final Class<? extends HeadersSearchRoot> class3 = headersSearchRoot7.getClass();
                        break Label_0200;
                    }
                }
                catch (TypeCastException ex10) {
                    throw b(ex10);
                }
            }
            final Class<? extends HeadersSearchRoot> class3 = null;
            try {
                if (Intrinsics.areEqual((Object)class2, (Object)class3) ^ true) {
                    return false;
                }
            }
            catch (TypeCastException ex11) {
                throw b(ex11);
            }
        }
        HeadersSearchRoot headersSearchRoot8;
        try {
            headersSearchRoot8 = headersSearchRoot2;
            if (headersSearchRoot8 == null) {
                Intrinsics.throwNpe();
            }
        }
        catch (TypeCastException ex12) {
            throw b(ex12);
        }
        VirtualFile virtualFile;
        HeadersSearchRoot headersSearchRoot9;
        try {
            virtualFile = headersSearchRoot8.getVirtualFile();
            headersSearchRoot9 = headersSearchRoot4;
            if (headersSearchRoot9 == null) {
                Intrinsics.throwNpe();
            }
        }
        catch (TypeCastException ex13) {
            throw b(ex13);
        }
        return Objects.equals(virtualFile, headersSearchRoot9.getVirtualFile());
    }
    
    @Override
    public int hashCode() {
        try {
            if (this.searchRoot != null) {
                return Objects.hashCode(this.searchRoot);
            }
        }
        catch (TypeCastException ex) {
            throw b(ex);
        }
        HeadersSearchRoot headersSearchRoot = null;
        Label_0045: {
            try {
                final RealFramework framework = this.framework;
                if (framework != null) {
                    final RealFramework realFramework;
                    headersSearchRoot = (realFramework = framework);
                    break Label_0045;
                }
            }
            catch (TypeCastException ex2) {
                throw b(ex2);
            }
            RealFramework realFramework;
            headersSearchRoot = (realFramework = (RealFramework)this.includedHeaders);
            try {
                if (realFramework == null) {
                    Intrinsics.throwNpe();
                }
            }
            catch (TypeCastException ex3) {
                throw b(ex3);
            }
        }
        return Objects.hashCode(headersSearchRoot.getVirtualFile());
    }
    
    private Key(final RealFramework framework, final IncludedHeadersRoot includedHeaders, final HeadersSearchRoot searchRoot) {
        this.framework = framework;
        this.includedHeaders = includedHeaders;
        this.searchRoot = searchRoot;
    }
    
    static {
        Companion = new Companion(null);
    }
    
    @JvmStatic
    @NotNull
    public static final Key create(@NotNull final RealFramework realFramework) {
        Intrinsics.checkParameterIsNotNull((Object)realFramework, "root");
        return Key.Companion.create(realFramework);
    }
    
    @JvmStatic
    @NotNull
    public static final Key create(@NotNull final IncludedHeadersRoot includedHeadersRoot) {
        Intrinsics.checkParameterIsNotNull((Object)includedHeadersRoot, "root");
        return Key.Companion.create(includedHeadersRoot);
    }
    
    @JvmStatic
    @NotNull
    public static final Key create(@NotNull final HeadersSearchRoot headersSearchRoot) {
        Intrinsics.checkParameterIsNotNull((Object)headersSearchRoot, "root");
        return Key.Companion.create(headersSearchRoot);
    }
    
    private static TypeCastException b(final TypeCastException ex) {
        return ex;
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0007H\u0007J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\bH\u0007¨\u0006\t" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapCache$Key$Companion;", "", "()V", "create", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapCache$Key;", "root", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot;", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/IncludedHeadersRoot;", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/RealFramework;", "cidr-lang" })
    public static final class Companion
    {
        @JvmStatic
        @NotNull
        public final Key create(@NotNull final RealFramework realFramework) {
            Intrinsics.checkParameterIsNotNull((Object)realFramework, "root");
            return new Key(realFramework, null, null, null);
        }
        
        @JvmStatic
        @NotNull
        public final Key create(@NotNull final IncludedHeadersRoot includedHeadersRoot) {
            Intrinsics.checkParameterIsNotNull((Object)includedHeadersRoot, "root");
            return new Key(null, includedHeadersRoot, null, null);
        }
        
        @JvmStatic
        @NotNull
        public final Key create(@NotNull final HeadersSearchRoot headersSearchRoot) {
            Intrinsics.checkParameterIsNotNull((Object)headersSearchRoot, "root");
            if (headersSearchRoot instanceof RealFramework) {
                return this.create((RealFramework)headersSearchRoot);
            }
            if (headersSearchRoot instanceof IncludedHeadersRoot) {
                return this.create((IncludedHeadersRoot)headersSearchRoot);
            }
            return new Key(null, null, headersSearchRoot, null);
        }
    }
}
