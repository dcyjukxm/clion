// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$55 extends FieldSerializer<OCSymbolReference.LambdaLocalReference> {
    protected OCSymbolReference.LambdaLocalReference create(final Kryo kryo, final Input input, final Class<OCSymbolReference.LambdaLocalReference> clazz) {
        return new OCSymbolReference.LambdaLocalReference();
    }
}