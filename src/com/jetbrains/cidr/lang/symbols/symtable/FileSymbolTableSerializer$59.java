// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$59 extends FieldSerializer<OCMagicType> {
    protected OCMagicType create(final Kryo kryo, final Input input, final Class<OCMagicType> clazz) {
        return new OCMagicType();
    }
}