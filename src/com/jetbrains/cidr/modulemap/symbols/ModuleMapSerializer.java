// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import java.io.OutputStream;
import com.esotericsoftware.kryo.io.FastOutput;
import java.io.DataOutputStream;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.io.StreamUtil;
import java.io.Closeable;
import com.esotericsoftware.kryo.io.Input;
import java.io.InputStream;
import com.esotericsoftware.kryo.io.FastInput;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import java.io.DataInputStream;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapPathResolver;
import com.esotericsoftware.kryo.Serializer;
import com.intellij.openapi.vfs.newvfs.impl.VirtualDirectoryImpl;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapRealFrameworkPathResolver;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapDefaultPathResolver;
import com.jetbrains.cidr.modulemap.symbols.impl.ModuleMapModuleSymbolImpl;
import com.jetbrains.cidr.modulemap.symbols.impl.ModuleMapFileSymbolImpl;
import kotlin.jvm.functions.Function0;
import kotlin.Metadata;
import com.jetbrains.cidr.lang.symbols.symtable.OCSerializer;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00182\u00020\u0001:\u0004\u0018\u0019\u001a\u001bB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u0007\u001a\u00020\bH\u0014J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004J\u0018\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J.\u0010\u0011\u001a\u00020\b\"\u0004\b\u0000\u0010\u0012*\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0017H\u0002¨\u0006\u001c" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSerializer;", "Lcom/jetbrains/cidr/lang/symbols/symtable/OCSerializer;", "()V", "readModuleMap", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapFileSymbol;", "dis", "Ljava/io/DataInputStream;", "registerSerializers", "", "writeModuleMap", "", "dos", "Ljava/io/DataOutputStream;", "moduleMap", "writeObject", "object", "", "registerFieldSerializer", "T", "Lcom/esotericsoftware/kryo/Kryo;", "clazz", "Ljava/lang/Class;", "initializer", "Lkotlin/Function0;", "Companion", "InvalidHeaderRootException", "ModuleMapPathResolverRootSerializer", "ModuleMapWrapper", "cidr-lang" })
public final class ModuleMapSerializer extends OCSerializer
{
    private static final int CHUNK_SIZE = 65536;
    public static final Companion Companion;
    
    @Override
    protected void registerSerializers() {
        this.a(this.getKryo(), ModuleMapWrapper.class, (kotlin.jvm.functions.Function0<? extends ModuleMapWrapper>)ModuleMapSerializer$registerSerializers.ModuleMapSerializer$registerSerializers$1.INSTANCE);
        this.a(this.getKryo(), ModuleMapFileSymbolImpl.class, (kotlin.jvm.functions.Function0<? extends ModuleMapFileSymbolImpl>)ModuleMapSerializer$registerSerializers.ModuleMapSerializer$registerSerializers$2.INSTANCE);
        this.a(this.getKryo(), ModuleMapModuleSymbolImpl.class, (kotlin.jvm.functions.Function0<? extends ModuleMapModuleSymbolImpl>)ModuleMapSerializer$registerSerializers.ModuleMapSerializer$registerSerializers$3.INSTANCE);
        this.a(this.getKryo(), ModuleMapDefaultPathResolver.class, (kotlin.jvm.functions.Function0<? extends ModuleMapDefaultPathResolver>)ModuleMapSerializer$registerSerializers.ModuleMapSerializer$registerSerializers$4.INSTANCE);
        this.a(this.getKryo(), ModuleMapRealFrameworkPathResolver.class, (kotlin.jvm.functions.Function0<? extends ModuleMapRealFrameworkPathResolver>)ModuleMapSerializer$registerSerializers.ModuleMapSerializer$registerSerializers$5.INSTANCE);
        this.getKryo().register((Class)VirtualDirectoryImpl.class, (Serializer)new ModuleMapPathResolverRootSerializer());
        this.getKryo().register((Class)ModuleMapFileSymbol.Companion.getEMPTY().getClass(), (Serializer)new SingletonSerializer<ModuleMapFileSymbol>(ModuleMapFileSymbol.Companion.getEMPTY()));
        this.getKryo().register((Class)ModuleMapPathResolver.Companion.getNULL().getClass(), (Serializer)new SingletonSerializer<ModuleMapPathResolver>(ModuleMapPathResolver.Companion.getNULL()));
    }
    
    @Nullable
    public final ModuleMapFileSymbol readModuleMap(@NotNull final DataInputStream dataInputStream) {
        Intrinsics.checkParameterIsNotNull((Object)dataInputStream, "dis");
        final FastInput fastInput = new FastInput((InputStream)dataInputStream, 65536);
        try {
            return ((ModuleMapWrapper)this.getKryo().readObject((Input)fastInput, (Class)ModuleMapWrapper.class)).getModuleMap();
        }
        catch (InvalidHeaderRootException ex2) {
            return null;
        }
        catch (Exception ex) {
            this.LOG.error((Throwable)ex);
            return null;
        }
        finally {
            StreamUtil.closeStream((Closeable)fastInput);
        }
    }
    
    public final boolean writeModuleMap(@NotNull final DataOutputStream dataOutputStream, @NotNull final ModuleMapFileSymbol moduleMapFileSymbol) {
        Intrinsics.checkParameterIsNotNull((Object)dataOutputStream, "dos");
        Intrinsics.checkParameterIsNotNull((Object)moduleMapFileSymbol, "moduleMap");
        return this.a(dataOutputStream, new ModuleMapWrapper(moduleMapFileSymbol));
    }
    
    private final boolean a(final DataOutputStream dataOutputStream, final Object o) {
        final FastOutput fastOutput = new FastOutput((OutputStream)dataOutputStream, 65536);
        try {
            this.getKryo().writeObject((Output)fastOutput, o);
            fastOutput.flush();
            return true;
        }
        catch (Exception ex) {
            this.LOG.error((Throwable)ex);
            return false;
        }
        finally {
            StreamUtil.closeStream((Closeable)fastOutput);
        }
    }
    
    private final <T> void a(@NotNull final Kryo kryo, final Class<T> clazz, final Function0<? extends T> function0) {
        kryo.register((Class)clazz, (Serializer)new ModuleMapSerializer$registerFieldSerializer.ModuleMapSerializer$registerFieldSerializer$1(kryo, (Function0)function0, (Class)clazz, kryo, (Class)clazz));
    }
    
    static {
        Companion = new Companion(null);
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSerializer$Companion;", "", "()V", "CHUNK_SIZE", "", "cidr-lang" })
    public static final class Companion
    {
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSerializer$ModuleMapWrapper;", "", "moduleMap", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapFileSymbol;", "(Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapFileSymbol;)V", "getModuleMap", "()Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapFileSymbol;", "Companion", "cidr-lang" })
    private static final class ModuleMapWrapper
    {
        @NotNull
        private final ModuleMapFileSymbol moduleMap;
        public static final Companion Companion;
        
        @NotNull
        public final ModuleMapFileSymbol getModuleMap() {
            return this.moduleMap;
        }
        
        public ModuleMapWrapper(@NotNull final ModuleMapFileSymbol moduleMap) {
            Intrinsics.checkParameterIsNotNull((Object)moduleMap, "moduleMap");
            this.moduleMap = moduleMap;
        }
        
        static {
            Companion = new Companion(null);
        }
        
        @JvmStatic
        @NotNull
        public static final ModuleMapWrapper createUninitialized() {
            return ModuleMapWrapper.Companion.createUninitialized();
        }
        
        @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSerializer$ModuleMapWrapper$Companion;", "", "()V", "createUninitialized", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSerializer$ModuleMapWrapper;", "cidr-lang" })
        public static final class Companion
        {
            @JvmStatic
            @NotNull
            public final ModuleMapWrapper createUninitialized() {
                return new ModuleMapWrapper(ModuleMapFileSymbol.Companion.getEMPTY());
            }
        }
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSerializer$InvalidHeaderRootException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "cidr-lang" })
    private static final class InvalidHeaderRootException extends Exception
    {
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\nH\u0016J \u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016¨\u0006\u0010" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSerializer$ModuleMapPathResolverRootSerializer;", "Lcom/esotericsoftware/kryo/Serializer;", "Lcom/intellij/openapi/vfs/newvfs/impl/VirtualDirectoryImpl;", "()V", "read", "kryo", "Lcom/esotericsoftware/kryo/Kryo;", "input", "Lcom/esotericsoftware/kryo/io/Input;", "type", "Ljava/lang/Class;", "write", "", "output", "Lcom/esotericsoftware/kryo/io/Output;", "object", "cidr-lang" })
    private static final class ModuleMapPathResolverRootSerializer extends Serializer<VirtualDirectoryImpl>
    {
        @NotNull
        public VirtualDirectoryImpl read(@NotNull final Kryo kryo, @NotNull final Input input, @Nullable final Class<VirtualDirectoryImpl> clazz) {
            Intrinsics.checkParameterIsNotNull((Object)kryo, "kryo");
            Intrinsics.checkParameterIsNotNull((Object)input, "input");
            final VirtualFile fileByUrl = VirtualFileManager.getInstance().findFileByUrl((String)kryo.readObject(input, (Class)String.class));
            if (fileByUrl instanceof VirtualDirectoryImpl && ((VirtualDirectoryImpl)fileByUrl).isValid()) {
                return (VirtualDirectoryImpl)fileByUrl;
            }
            throw new InvalidHeaderRootException();
        }
        
        public void write(@NotNull final Kryo kryo, @NotNull final Output output, @NotNull final VirtualDirectoryImpl virtualDirectoryImpl) {
            Intrinsics.checkParameterIsNotNull((Object)kryo, "kryo");
            Intrinsics.checkParameterIsNotNull((Object)output, "output");
            Intrinsics.checkParameterIsNotNull((Object)virtualDirectoryImpl, "object");
            kryo.writeObject(output, (Object)virtualDirectoryImpl.getUrl());
        }
    }
}
