// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCTestFramework;
import com.intellij.openapi.util.NotNullLazyValue;

class CidrTestRunConfigurationProducer$1 extends NotNullLazyValue<OCTestFramework> {
    @NotNull
    @Contract(pure = true)
    protected OCTestFramework compute() {
        OCTestFramework extension;
        try {
            extension = CidrTestFrameworkBase.findExtension((Class<OCTestFramework>)CidrTestRunConfigurationProducer.access$000(CidrTestRunConfigurationProducer.this));
            if (extension == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer$1", "compute"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return extension;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}