// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.openapi.util.text.StringUtil;
import kotlin.text.StringsKt;
import java.util.Arrays;
import com.jetbrains.cidr.lang.toolchains.DefaultCidrToolEnvironment;
import kotlin.TypeCastException;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.util.ExtensionsKt;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kotlin.jvm.JvmStatic;
import com.intellij.util.NullableFunction;
import java.io.File;
import kotlin.jvm.internal.DefaultConstructorMarker;
import java.util.Collections;
import kotlin.collections.CollectionsKt;
import gnu.trove.THashMap;
import kotlin.Triple;
import com.intellij.util.containers.MultiMap;
import kotlin.collections.MapsKt;
import gnu.trove.THashSet;
import java.util.Iterator;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import java.util.Set;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerSettings;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.lang.workspace.OCResolveRootAndConfiguration;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.util.Pair;
import java.util.Map;
import kotlin.Metadata;
import com.jetbrains.cidr.lang.workspace.OCIncludeMap;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB)\b\u0012\u0012 \u0010\u0002\u001a\u001c\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007BI\b\u0016\u0012\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t0\u0003\u0012&\u0010\n\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00050\f0\u000b¢\u0006\u0002\u0010\rJB\u0010\u000f\u001a\u00020\u00102\u0018\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00130\u00122\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t0\u00122\u0006\u0010\u0015\u001a\u00020\u0016J\u001b\u0010\u0017\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001aH\u0096\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016R(\u0010\u0002\u001a\u001c\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e" }, d2 = { "Lcom/jetbrains/cidr/cpp/cmake/workspace/PrecompiledIncludeMap;", "Lcom/jetbrains/cidr/lang/workspace/OCIncludeMap;", "data", "", "Lcom/intellij/openapi/util/Pair;", "", "Lcom/intellij/openapi/vfs/VirtualFile;", "(Ljava/util/Map;)V", "creation", "", "usage", "Lcom/intellij/util/containers/MultiMap;", "Lkotlin/Triple;", "(Ljava/util/Map;Lcom/intellij/util/containers/MultiMap;)V", "precompiledHeaders", "fillPCHCollections", "", "config2pchFiles", "", "", "fileAndLang2pchFiles", "config", "Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration;", "get", "include", "rootAndConfiguration", "Lcom/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration;", "isEmpty", "", "Companion", "clion" })
public final class PrecompiledIncludeMap implements OCIncludeMap
{
    private final Map<Pair<String, VirtualFile>, VirtualFile> data;
    private final Map<String, List<VirtualFile>> precompiledHeaders;
    public static final Companion Companion;
    
    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }
    
    @Nullable
    @Override
    public VirtualFile get(@NotNull final String s, @NotNull final OCResolveRootAndConfiguration ocResolveRootAndConfiguration) {
        Intrinsics.checkParameterIsNotNull((Object)s, "include");
        Intrinsics.checkParameterIsNotNull((Object)ocResolveRootAndConfiguration, "rootAndConfiguration");
        if (this.isEmpty()) {
            return null;
        }
        final OCResolveConfiguration configuration = ocResolveRootAndConfiguration.getConfiguration();
        char[] supportedFileSeparators = null;
        Label_0057: {
            if (configuration != null) {
                final OCCompilerSettings compilerSettings = configuration.getCompilerSettings();
                if (compilerSettings != null) {
                    final CidrToolEnvironment environment = compilerSettings.getEnvironment();
                    if (environment != null) {
                        supportedFileSeparators = environment.getSupportedFileSeparators();
                        break Label_0057;
                    }
                }
            }
            supportedFileSeparators = null;
        }
        final String access$toCanonicalIncludeName = a(s, supportedFileSeparators);
        VirtualFile virtualFile;
        if ((virtualFile = this.data.get(new Pair((Object)access$toCanonicalIncludeName, (Object)ocResolveRootAndConfiguration.getRootFile()))) == null) {
            virtualFile = this.data.get(new Pair((Object)access$toCanonicalIncludeName, (Object)null));
        }
        return virtualFile;
    }
    
    public final void fillPCHCollections(@NotNull final Map<String, Set<VirtualFile>> map, @NotNull final Map<String, List<VirtualFile>> map2, @NotNull final CMakeResolveConfiguration cMakeResolveConfiguration) {
        Intrinsics.checkParameterIsNotNull((Object)map, "config2pchFiles");
        Intrinsics.checkParameterIsNotNull((Object)map2, "fileAndLang2pchFiles");
        Intrinsics.checkParameterIsNotNull((Object)cMakeResolveConfiguration, "config");
        final String uniqueName = cMakeResolveConfiguration.getUniqueName();
        for (final List<VirtualFile> list : this.precompiledHeaders.values()) {
            final String s = uniqueName;
            Intrinsics.checkExpressionValueIsNotNull((Object)s, "configId");
            final THashSet troveSet = ContainerUtil.newTroveSet((Collection)list);
            Intrinsics.checkExpressionValueIsNotNull((Object)troveSet, "ContainerUtil.newTroveSet(pchList)");
            map.put(s, (Set<VirtualFile>)troveSet);
        }
        map2.putAll(this.precompiledHeaders);
    }
    
    private PrecompiledIncludeMap(final Map<Pair<String, VirtualFile>, ? extends VirtualFile> data) {
        this.data = (Map<Pair<String, VirtualFile>, VirtualFile>)data;
        this.precompiledHeaders = (Map<String, List<VirtualFile>>)MapsKt.emptyMap();
    }
    
    public PrecompiledIncludeMap(@NotNull final Map<String, ? extends List<? extends VirtualFile>> map, @NotNull final MultiMap<String, Triple<String, VirtualFile, String>> multiMap) {
        Intrinsics.checkParameterIsNotNull((Object)map, "creation");
        Intrinsics.checkParameterIsNotNull((Object)multiMap, "usage");
        this.data = (Map<Pair<String, VirtualFile>, VirtualFile>)new THashMap();
        this.precompiledHeaders = (Map<String, List<VirtualFile>>)new THashMap();
        for (final Map.Entry<String, ? extends List<? extends VirtualFile>> entry : map.entrySet()) {
            final String s = entry.getKey();
            final List<? extends T> list = (List<? extends T>)entry.getValue();
            if (!list.isEmpty()) {
                for (final Triple triple : multiMap.get((Object)s)) {
                    final String s2 = (String)triple.component1();
                    final VirtualFile virtualFile = (VirtualFile)triple.component2();
                    final String s3 = (String)triple.component3();
                    ((THashMap)this.data).put((Object)new Pair((Object)s2, (Object)virtualFile), CollectionsKt.last((List)list));
                    ((THashMap)this.precompiledHeaders).put((Object)s3, (Object)Collections.unmodifiableList((List<?>)list));
                }
            }
        }
        ((THashMap)this.data).compact();
        ((THashMap)this.precompiledHeaders).compact();
    }
    
    static {
        Companion = new Companion(null);
    }
    
    @NotNull
    public static final /* synthetic */ Map access$getData$p(final PrecompiledIncludeMap precompiledIncludeMap) {
        return precompiledIncludeMap.data;
    }
    
    @JvmStatic
    @NotNull
    public static final Map<String, PrecompiledIncludeMap> convert(@NotNull final Map<String, ? extends Map<Pair<String, File>, ? extends File>> map, @NotNull final NullableFunction<File, VirtualFile> nullableFunction) {
        Intrinsics.checkParameterIsNotNull((Object)map, "config2includeMap");
        Intrinsics.checkParameterIsNotNull((Object)nullableFunction, "unsafeCachedFileMapper");
        return PrecompiledIncludeMap.Companion.convert(map, nullableFunction);
    }
    
    @JvmStatic
    public static final void save(@NotNull final ObjectOutputStream objectOutputStream, @NotNull final Map<String, PrecompiledIncludeMap> map) {
        Intrinsics.checkParameterIsNotNull((Object)objectOutputStream, "out");
        Intrinsics.checkParameterIsNotNull((Object)map, "config2includeMap");
        PrecompiledIncludeMap.Companion.save(objectOutputStream, map);
    }
    
    @JvmStatic
    @NotNull
    public static final Map<String, Map<Pair<String, File>, File>> load(@NotNull final ObjectInputStream objectInputStream) throws IOException {
        Intrinsics.checkParameterIsNotNull((Object)objectInputStream, "input");
        return PrecompiledIncludeMap.Companion.load(objectInputStream);
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JV\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042,\u0010\u0007\u001a(\u0012\u0004\u0012\u00020\u0005\u0012\u001e\u0012\u001c\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\t0\b\u0012\u0004\u0012\u00020\t0\u00040\u00042\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000bH\u0007J@\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\f0\b2\u0014\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\t0\b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000bH\u0002J6\u0010\u000f\u001a(\u0012\u0004\u0012\u00020\u0005\u0012\u001e\u0012\u001c\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\t0\b\u0012\u0004\u0012\u00020\t0\u00040\u00042\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J*\u0010\u0012\u001a\u001c\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\t0\b\u0012\u0004\u0012\u00020\t0\u00042\u0006\u0010\u0010\u001a\u00020\u0011H\u0003J\u001e\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\t0\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0003J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004H\u0007J\u0018\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0006H\u0002¨\u0006\u001a" }, d2 = { "Lcom/jetbrains/cidr/cpp/cmake/workspace/PrecompiledIncludeMap$Companion;", "", "()V", "convert", "", "", "Lcom/jetbrains/cidr/cpp/cmake/workspace/PrecompiledIncludeMap;", "config2includeMap", "Lcom/intellij/openapi/util/Pair;", "Ljava/io/File;", "unsafeCachedFileMapper", "Lcom/intellij/util/NullableFunction;", "Lcom/intellij/openapi/vfs/VirtualFile;", "convertPair", "pair", "load", "input", "Ljava/io/ObjectInputStream;", "loadMap", "loadPair", "save", "", "out", "Ljava/io/ObjectOutputStream;", "saveMap", "includeMap", "clion" })
    public static final class Companion
    {
        @JvmStatic
        @NotNull
        public final Map<String, PrecompiledIncludeMap> convert(@NotNull final Map<String, ? extends Map<Pair<String, File>, ? extends File>> map, @NotNull final NullableFunction<File, VirtualFile> nullableFunction) {
            Intrinsics.checkParameterIsNotNull((Object)map, "config2includeMap");
            Intrinsics.checkParameterIsNotNull((Object)nullableFunction, "unsafeCachedFileMapper");
            final THashMap tHashMap = new THashMap(map.size());
            for (final Map.Entry<String, ? extends Map<Pair<String, File>, ? extends File>> entry : map.entrySet()) {
                final String s = entry.getKey();
                final Map<Pair, Map<Pair, Map<Pair, V>>> map2 = (Map<Pair, Map<Pair, Map<Pair, V>>>)entry.getValue();
                final THashMap tHashMap2 = new THashMap(map2.size());
                for (final Map.Entry<Pair, Map<Pair, Map<Pair, V>>> entry2 : map2.entrySet()) {
                    final Pair pair = entry2.getKey();
                    final File file = (File)entry2.getValue();
                    final Pair<String, VirtualFile> a = this.a((Pair<String, File>)pair, nullableFunction);
                    final VirtualFile virtualFile = (VirtualFile)nullableFunction.fun((Object)file);
                    if (virtualFile != null) {
                        tHashMap2.put((Object)a, (Object)virtualFile);
                    }
                }
                tHashMap2.compact();
                tHashMap.put((Object)s, (Object)new PrecompiledIncludeMap((Map)tHashMap2, null));
            }
            return (Map<String, PrecompiledIncludeMap>)tHashMap;
        }
        
        private final Pair<String, VirtualFile> a(final Pair<String, File> pair, final NullableFunction<File, VirtualFile> nullableFunction) {
            return (Pair<String, VirtualFile>)new Pair((Object)pair.first, (Object)((pair.second == null) ? null : ((VirtualFile)nullableFunction.fun(pair.second))));
        }
        
        @JvmStatic
        public final void save(@NotNull final ObjectOutputStream objectOutputStream, @NotNull final Map<String, PrecompiledIncludeMap> map) {
            Intrinsics.checkParameterIsNotNull((Object)objectOutputStream, "out");
            Intrinsics.checkParameterIsNotNull((Object)map, "config2includeMap");
            objectOutputStream.writeInt(map.size());
            for (final Map.Entry<String, PrecompiledIncludeMap> entry : map.entrySet()) {
                final String s = entry.getKey();
                final PrecompiledIncludeMap precompiledIncludeMap = entry.getValue();
                objectOutputStream.writeUTF(s);
                this.a(objectOutputStream, precompiledIncludeMap);
            }
        }
        
        private final void a(final ObjectOutputStream objectOutputStream, final PrecompiledIncludeMap precompiledIncludeMap) {
            objectOutputStream.writeInt(PrecompiledIncludeMap.access$getData$p(precompiledIncludeMap).size());
            for (final Map.Entry<Pair, V> entry : PrecompiledIncludeMap.access$getData$p(precompiledIncludeMap).entrySet()) {
                final Pair pair = entry.getKey();
                final VirtualFile virtualFile = (VirtualFile)entry.getValue();
                final Pair pair2 = pair;
                final String s = ExtensionsKt.component1((com.intellij.openapi.util.Pair<String, Object>)pair2);
                final VirtualFile virtualFile2 = ExtensionsKt.component2((com.intellij.openapi.util.Pair<Object, VirtualFile>)pair2);
                objectOutputStream.writeUTF(s);
                objectOutputStream.writeBoolean(virtualFile2 != null);
                if (virtualFile2 != null) {
                    objectOutputStream.writeUTF(VfsUtilCore.virtualToIoFile(virtualFile2).getPath());
                }
                objectOutputStream.writeUTF(VfsUtilCore.virtualToIoFile(virtualFile).getPath());
            }
        }
        
        @JvmStatic
        @NotNull
        public final Map<String, Map<Pair<String, File>, File>> load(@NotNull final ObjectInputStream objectInputStream) throws IOException {
            Intrinsics.checkParameterIsNotNull((Object)objectInputStream, "input");
            int int1 = objectInputStream.readInt();
            final THashMap tHashMap = new THashMap(int1);
            while (int1-- > 0) {
                tHashMap.put((Object)objectInputStream.readUTF(), (Object)this.b(objectInputStream));
            }
            return (Map<String, Map<Pair<String, File>, File>>)tHashMap;
        }
        
        private final Map<Pair<String, File>, File> b(final ObjectInputStream objectInputStream) throws IOException {
            int int1 = objectInputStream.readInt();
            final THashMap tHashMap = new THashMap(int1);
            while (int1-- > 0) {
                tHashMap.put((Object)this.a(objectInputStream), (Object)new File(objectInputStream.readUTF()));
            }
            return (Map<Pair<String, File>, File>)tHashMap;
        }
        
        private final Pair<String, File> a(final ObjectInputStream objectInputStream) throws IOException {
            final String utf = objectInputStream.readUTF();
            final boolean boolean1 = objectInputStream.readBoolean();
            try {
                if (boolean1) {
                    final String s;
                    final File file;
                    final Pair pair = new Pair((Object)s, (Object)file);
                    s = utf;
                    file = new File(objectInputStream.readUTF());
                    return (Pair<String, File>)pair;
                }
            }
            catch (IOException ex) {
                throw a(ex);
            }
            final Pair pair = new Pair((Object)utf, (Object)null);
            return (Pair<String, File>)pair;
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
    }
}
