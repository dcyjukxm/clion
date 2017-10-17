// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$77 extends FieldSerializer<OCIncludeSymbol.IncludePath> {
    protected OCIncludeSymbol.IncludePath create(final Kryo kryo, final Input input, final Class<OCIncludeSymbol.IncludePath> clazz) {
        return new OCIncludeSymbol.IncludePath();
    }
}