// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr;

import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.List;
import com.intellij.openapi.keymap.impl.BundledKeymapProvider;

public class CidrKeymapProvider implements BundledKeymapProvider
{
    @NotNull
    @Override
    public List<String> getKeymapFileNames() {
        List<String> list;
        try {
            list = Arrays.asList("Xcode.xml", "ReSharper.xml", "ReSharper OSX.xml");
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/CidrKeymapProvider", "getKeymapFileNames"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return list;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
