// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.symbols.ProjectAndVirtualFileOwner;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.newvfs.persistent.PersistentFS;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.CustomHeaderProvider;
import com.intellij.openapi.vfs.VirtualFileWithId;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;

public abstract class OCIncludeSerializer<T extends OCIncludeSymbol> extends ProjectAndFileOwnerSerializer<T>
{
    public OCIncludeSerializer(final Class<T> clazz) {
        super(clazz);
    }
    
    @Override
    public abstract T createInstance(final Kryo p0, final Input p1, final Class p2);
    
    @Override
    public void write(final Kryo kryo, final Output output, final T t) {
        super.write(kryo, output, t);
        final VirtualFile targetFile = t.getTargetFile();
        int id;
        String s;
        if (targetFile != null && targetFile.isValid()) {
            if (targetFile instanceof VirtualFileWithId) {
                id = ((VirtualFileWithId)targetFile).getId();
                s = targetFile.getPath();
            }
            else {
                id = -1;
                s = CustomHeaderProvider.provideSerializationPathForFile(targetFile);
                if (s == null) {
                    FileSymbolTableSerializer.this.LOG.error("only LocalVirtualFiles are supported: " + targetFile.getClass() + ": " + targetFile);
                }
            }
        }
        else {
            id = -1;
            s = null;
        }
        output.writeInt(id, true);
        output.writeString(s);
    }
    
    public T read(final Kryo kryo, final Input input, final Class<T> clazz) {
        final OCIncludeSymbol ocIncludeSymbol = (OCIncludeSymbol)super.read(kryo, input, (Class)clazz);
        final int int1 = input.readInt(true);
        final String string = input.readString();
        VirtualFile virtualFile = null;
        if (int1 != -1) {
            virtualFile = PersistentFS.getInstance().findFileById(int1);
        }
        if (string != null && (virtualFile == null || !FileUtil.pathsEqual(string, virtualFile.getPath()))) {
            virtualFile = LocalFileSystem.getInstance().findFileByPath(string);
            if (virtualFile == null) {
                virtualFile = CustomHeaderProvider.getCustomHeaderFile(string, FileSymbolTableSerializer.access$000(FileSymbolTableSerializer.this), FileSymbolTableSerializer.access$100(FileSymbolTableSerializer.this));
            }
        }
        ocIncludeSymbol.updateTargetFile(virtualFile);
        return (T)ocIncludeSymbol;
    }
}
