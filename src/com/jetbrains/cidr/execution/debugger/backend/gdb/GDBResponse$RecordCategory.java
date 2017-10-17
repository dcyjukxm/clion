// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public interface RecordCategory
{
    @NotNull
    String getPrefix();
    
    @NotNull
    default <T extends RecordCategory> T forPrefix(@NotNull final T[] array, @NotNull final String s) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "values", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$RecordCategory", "forPrefix"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$RecordCategory", "forPrefix"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        for (final RecordCategory recordCategory : array) {
            Label_0170: {
                RecordCategory recordCategory2 = null;
                Label_0135: {
                    try {
                        if (!recordCategory.getPrefix().equals(s)) {
                            break Label_0170;
                        }
                        recordCategory2 = recordCategory;
                        if (recordCategory2 == null) {
                            break Label_0135;
                        }
                        return (T)recordCategory2;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        recordCategory2 = recordCategory;
                        if (recordCategory2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$RecordCategory", "forPrefix"));
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                return (T)recordCategory2;
            }
        }
        throw new IllegalArgumentException("Unknown RecordCategory prefix, expected one of " + Arrays.toString(array));
    }
    
    default IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
