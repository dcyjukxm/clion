// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.intellij.openapi.vfs.CharsetToolkit;
import java.nio.ByteBuffer;
import org.jetbrains.annotations.NotNull;
import java.nio.charset.Charset;

public enum OCEncoding
{
    NONE(Charset.forName("US-ASCII")), 
    WCHAR(CharsetToolkit.UTF_16LE_CHARSET), 
    UTF8(CharsetToolkit.UTF8_CHARSET), 
    UTF16(CharsetToolkit.UTF_16LE_CHARSET), 
    UTF32(CharsetToolkit.UTF_32LE_CHARSET);
    
    @NotNull
    private final Charset charset;
    
    private OCEncoding(final Charset charset) {
        if (charset == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "charset", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding", "<init>"));
        }
        super(s, n);
        this.charset = charset;
    }
    
    public byte[] getBytes(final String s) {
        return s.getBytes(this.charset);
    }
    
    public String getString(final byte[] array) {
        return this.charset.decode(ByteBuffer.wrap(array)).toString();
    }
}
