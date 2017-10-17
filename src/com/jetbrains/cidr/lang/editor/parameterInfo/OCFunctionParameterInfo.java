// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.parameterInfo;

import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.lang.OCLog;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCFunctionType;

public class OCFunctionParameterInfo
{
    @NotNull
    private final OCFunctionType myType;
    @Nullable
    private final List<String> myDefaultParameterValues;
    
    public OCFunctionParameterInfo(@NotNull final OCFunctionType myType, @Nullable final List<String> myDefaultParameterValues) {
        if (myType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionParameterInfo", "<init>"));
        }
        this.myType = myType;
        final Logger log = OCLog.LOG;
        while (true) {
            if (myDefaultParameterValues != null) {
                boolean b = false;
                Label_0086: {
                    try {
                        if (this.myType.getParameterTypes().size() == myDefaultParameterValues.size()) {
                            b = true;
                            break Label_0086;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    b = false;
                }
                log.assertTrue(b);
                this.myDefaultParameterValues = myDefaultParameterValues;
                return;
            }
            continue;
        }
    }
    
    @NotNull
    public OCFunctionType getType() {
        OCFunctionType myType;
        try {
            myType = this.myType;
            if (myType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionParameterInfo", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myType;
    }
    
    @Nullable
    public List<String> getDefaultParameterValues() {
        return this.myDefaultParameterValues;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
