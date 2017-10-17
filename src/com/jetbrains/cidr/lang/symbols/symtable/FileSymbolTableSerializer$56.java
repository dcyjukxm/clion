// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.types.OCExpressionTypeArgument;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$56 extends FieldSerializer<OCExpressionTypeArgument> {
    protected OCExpressionTypeArgument create(final Kryo kryo, final Input input, final Class<OCExpressionTypeArgument> clazz) {
        return new OCExpressionTypeArgument();
    }
}