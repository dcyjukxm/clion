// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.esotericsoftware.kryo.serializers.FieldSerializer;

class FileSymbolTableSerializer$54 extends FieldSerializer<OCSymbolReference.TemplateParamsReference> {
    protected OCSymbolReference.TemplateParamsReference create(final Kryo kryo, final Input input, final Class<OCSymbolReference.TemplateParamsReference> clazz) {
        return new OCSymbolReference.TemplateParamsReference();
    }
}