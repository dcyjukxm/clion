// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import com.esotericsoftware.kryo.io.Output;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import com.esotericsoftware.kryo.io.Input;
import org.jetbrains.annotations.NotNull;
import com.esotericsoftware.kryo.Kryo;
import kotlin.Metadata;
import com.intellij.openapi.vfs.newvfs.impl.VirtualDirectoryImpl;
import com.esotericsoftware.kryo.Serializer;

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
