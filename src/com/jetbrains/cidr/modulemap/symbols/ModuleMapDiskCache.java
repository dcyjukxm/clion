// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import com.intellij.openapi.components.ServiceManager;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.psi.search.PsiElementProcessor;
import kotlin.jvm.internal.Ref$LongRef;
import java.io.Closeable;
import com.intellij.openapi.util.io.StreamUtil;
import java.io.IOException;
import com.jetbrains.cidr.modulemap.ModuleMapLog;
import java.io.InputStream;
import java.io.DataInputStream;
import com.intellij.openapi.vfs.VirtualFile;
import java.io.OutputStream;
import java.io.DataOutputStream;
import com.jetbrains.cidr.lang.workspace.headerRoots.RealFramework;
import com.jetbrains.cidr.lang.workspace.headerRoots.IncludedHeadersRoot;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import com.intellij.openapi.vfs.newvfs.FileAttribute;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001a\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bJ\"\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapDiskCache;", "", "()V", "mySerializer", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSerializer;", "cache", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapFileSymbol;", "root", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot;", "moduleMap", "calculateHashCode", "", "deserializeModuleMap", "inputStream", "Ljava/io/InputStream;", "getModuleMap", "serializeModuleMap", "", "outputStream", "Ljava/io/OutputStream;", "Companion", "cidr-lang" })
public final class ModuleMapDiskCache
{
    private final ModuleMapSerializer mySerializer;
    private static final int SERIALIZATION_VERSION = 102;
    private static final String TABLES_KEY_PREFIX = "clang_module_map_attribute";
    private static final FileAttribute ourFileCacheAttribute;
    public static final Companion Companion;
    
    @Nullable
    public final ModuleMapFileSymbol cache(@NotNull final HeadersSearchRoot headersSearchRoot, @Nullable final ModuleMapFileSymbol moduleMapFileSymbol) {
        Intrinsics.checkParameterIsNotNull((Object)headersSearchRoot, "root");
        synchronized (this) {
            if (!(headersSearchRoot instanceof IncludedHeadersRoot) && !(headersSearchRoot instanceof RealFramework)) {
                return null;
            }
            final VirtualFile virtualFile = headersSearchRoot.getVirtualFile();
            if (!virtualFile.isValid()) {
                return null;
            }
            final DataOutputStream dataOutputStream = ModuleMapDiskCache.Companion.a().writeAttribute(virtualFile);
            boolean b = false;
            try {
                final DataOutputStream dataOutputStream2 = dataOutputStream;
                Intrinsics.checkExpressionValueIsNotNull((Object)dataOutputStream2, "it");
                this.a(headersSearchRoot, dataOutputStream2, moduleMapFileSymbol);
                try {
                    final DataOutputStream dataOutputStream3 = dataOutputStream;
                    if (dataOutputStream3 != null) {
                        dataOutputStream3.close();
                    }
                }
                catch (Exception ex) {
                    throw b(ex);
                }
            }
            catch (Exception ex3) {
                b = true;
                try {
                    try {
                        final DataOutputStream dataOutputStream4 = dataOutputStream;
                        if (dataOutputStream4 != null) {
                            dataOutputStream4.close();
                        }
                    }
                    catch (Exception ex2) {
                        throw b(ex2);
                    }
                }
                catch (Exception ex6) {}
                throw ex3;
            }
            finally {
                Label_0179: {
                    try {
                        if (b) {
                            return moduleMapFileSymbol;
                        }
                        final DataOutputStream dataOutputStream5 = dataOutputStream;
                        if (dataOutputStream5 != null) {
                            break Label_0179;
                        }
                        return moduleMapFileSymbol;
                    }
                    catch (Exception ex4) {
                        throw b(ex4);
                    }
                    try {
                        final DataOutputStream dataOutputStream5 = dataOutputStream;
                        if (dataOutputStream5 != null) {
                            dataOutputStream5.close();
                        }
                    }
                    catch (Exception ex5) {
                        throw b(ex5);
                    }
                }
            }
            return moduleMapFileSymbol;
        }
    }
    
    @Nullable
    public final ModuleMapFileSymbol getModuleMap(@NotNull final HeadersSearchRoot headersSearchRoot) {
        Intrinsics.checkParameterIsNotNull((Object)headersSearchRoot, "root");
        synchronized (this) {
            if (!(headersSearchRoot instanceof IncludedHeadersRoot) && !(headersSearchRoot instanceof RealFramework)) {
                return null;
            }
            final VirtualFile virtualFile = headersSearchRoot.getVirtualFile();
            if (!virtualFile.isValid()) {
                return null;
            }
            final DataInputStream attribute = ModuleMapDiskCache.Companion.a().readAttribute(virtualFile);
            if (attribute != null) {
                final DataInputStream dataInputStream = attribute;
                boolean b = false;
                try {
                    final DataInputStream dataInputStream2 = dataInputStream;
                    Intrinsics.checkExpressionValueIsNotNull((Object)dataInputStream2, "it");
                    final ModuleMapFileSymbol a = this.a(headersSearchRoot, dataInputStream2);
                    try {
                        final DataInputStream dataInputStream3 = dataInputStream;
                        if (dataInputStream3 != null) {
                            dataInputStream3.close();
                        }
                    }
                    catch (Exception ex) {
                        throw b(ex);
                    }
                    return a;
                }
                catch (Exception ex3) {
                    b = true;
                    try {
                        try {
                            final DataInputStream dataInputStream4 = dataInputStream;
                            if (dataInputStream4 != null) {
                                dataInputStream4.close();
                            }
                        }
                        catch (Exception ex2) {
                            throw b(ex2);
                        }
                    }
                    catch (Exception ex6) {}
                    throw ex3;
                }
                finally {
                    Label_0185: {
                        try {
                            if (b) {
                                return null;
                            }
                            final DataInputStream dataInputStream5 = dataInputStream;
                            if (dataInputStream5 != null) {
                                break Label_0185;
                            }
                            return null;
                        }
                        catch (Exception ex4) {
                            throw b(ex4);
                        }
                        try {
                            final DataInputStream dataInputStream5 = dataInputStream;
                            if (dataInputStream5 != null) {
                                dataInputStream5.close();
                            }
                        }
                        catch (Exception ex5) {
                            throw b(ex5);
                        }
                    }
                }
            }
            return null;
        }
    }
    
    private final boolean a(final HeadersSearchRoot headersSearchRoot, final OutputStream outputStream, final ModuleMapFileSymbol moduleMapFileSymbol) {
        try {
            if (ModuleMapLog.LOG.isTraceEnabled()) {
                ModuleMapLog.LOG.trace("Serialize Module Map for " + headersSearchRoot);
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        final DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        try {
            ModuleMapSerializer mySerializer;
            DataOutputStream dataOutputStream2;
            try {
                dataOutputStream.writeLong(this.a(headersSearchRoot));
                mySerializer = this.mySerializer;
                dataOutputStream2 = dataOutputStream;
                final ModuleMapFileSymbol empty = moduleMapFileSymbol;
                if (moduleMapFileSymbol != null) {
                    return mySerializer.writeModuleMap(dataOutputStream2, empty);
                }
            }
            catch (IOException ex2) {
                throw b(ex2);
            }
            final ModuleMapFileSymbol empty = ModuleMapFileSymbol.Companion.getEMPTY();
            return mySerializer.writeModuleMap(dataOutputStream2, empty);
        }
        catch (IOException ex3) {
            ModuleMapLog.LOG.warn("Failed to serialize Module Map for " + headersSearchRoot, (Throwable)ex3);
            return false;
        }
        finally {
            StreamUtil.closeStream((Closeable)dataOutputStream);
        }
    }
    
    private final ModuleMapFileSymbol a(final HeadersSearchRoot headersSearchRoot, final InputStream inputStream) {
        try {
            if (ModuleMapLog.LOG.isTraceEnabled()) {
                ModuleMapLog.LOG.trace("Deserialize Module Map for " + headersSearchRoot);
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        try {
            if (dataInputStream.available() == 0) {
                return null;
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        try {
            if (dataInputStream.readLong() != this.a(headersSearchRoot)) {
                return null;
            }
            return this.mySerializer.readModuleMap(dataInputStream);
        }
        catch (IOException ex3) {
            ModuleMapLog.LOG.warn("Failed to deserialize Module Map for " + headersSearchRoot, (Throwable)ex3);
            return null;
        }
        finally {
            StreamUtil.closeStream((Closeable)dataInputStream);
        }
    }
    
    private final long a(final HeadersSearchRoot headersSearchRoot) {
        if (ModuleMapLog.LOG.isTraceEnabled()) {
            ModuleMapLog.LOG.trace("Calculate Hash Code for " + headersSearchRoot);
        }
        final Ref$LongRef ref$LongRef = new Ref$LongRef();
        ref$LongRef.element = 0L;
        headersSearchRoot.processChildren((PsiElementProcessor)new ModuleMapDiskCache$calculateHashCode.ModuleMapDiskCache$calculateHashCode$1(ref$LongRef));
        return ref$LongRef.element;
    }
    
    public ModuleMapDiskCache() {
        this.mySerializer = new ModuleMapSerializer();
    }
    
    static {
        Companion = new Companion(null);
        ourFileCacheAttribute = new FileAttribute("clang_module_map_attribute", 102, false);
    }
    
    @NotNull
    public static final /* synthetic */ FileAttribute access$getOurFileCacheAttribute$cp() {
        return ModuleMapDiskCache.ourFileCacheAttribute;
    }
    
    @JvmStatic
    @NotNull
    public static final ModuleMapDiskCache getInstance() {
        return ModuleMapDiskCache.Companion.getInstance();
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapDiskCache$Companion;", "", "()V", "SERIALIZATION_VERSION", "", "TABLES_KEY_PREFIX", "", "ourFileCacheAttribute", "Lcom/intellij/openapi/vfs/newvfs/FileAttribute;", "getOurFileCacheAttribute", "()Lcom/intellij/openapi/vfs/newvfs/FileAttribute;", "getInstance", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapDiskCache;", "cidr-lang" })
    public static final class Companion
    {
        @JvmStatic
        @NotNull
        public final ModuleMapDiskCache getInstance() {
            final Object service = ServiceManager.getService((Class)ModuleMapDiskCache.class);
            Intrinsics.checkExpressionValueIsNotNull(service, "ServiceManager.getServic\u2026MapDiskCache::class.java)");
            return (ModuleMapDiskCache)service;
        }
        
        private final FileAttribute a() {
            return ModuleMapDiskCache.access$getOurFileCacheAttribute$cp();
        }
    }
}
