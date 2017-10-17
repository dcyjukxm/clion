// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.toolchains;

import org.jetbrains.annotations.Contract;
import java.io.File;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.ExecutionException;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.GeneralCommandLine;

public abstract class CidrToolEnvironment
{
    public void prepare(@NotNull final GeneralCommandLine generalCommandLine, @NotNull final PrepareFor prepareFor) throws ExecutionException {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/lang/toolchains/CidrToolEnvironment", "prepare"));
            }
        }
        catch (ExecutionException ex) {
            throw a(ex);
        }
        try {
            if (prepareFor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prepareFor", "com/jetbrains/cidr/lang/toolchains/CidrToolEnvironment", "prepare"));
            }
        }
        catch (ExecutionException ex2) {
            throw a(ex2);
        }
    }
    
    @Contract("null -> null")
    public String toLocalPath(@Nullable final String s) {
        return this.toLocalPath(null, s);
    }
    
    @Contract("_, null -> null")
    public abstract String toLocalPath(@Nullable final File p0, @Nullable final String p1);
    
    @Contract("null -> null")
    public abstract String toEnvPath(@Nullable final String p0);
    
    @NotNull
    public abstract char[] getSupportedFileSeparators();
    
    private static ExecutionException a(final ExecutionException ex) {
        return ex;
    }
    
    public enum PrepareFor
    {
        BUILD, 
        RUN;
    }
}
