// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import com.intellij.openapi.util.Couple;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.ui.OCFieldAdapterForSymbolName;

class CidrGoogleTestRunConfigurationEditor$2 extends OCFieldAdapterForSymbolName {
    @Override
    protected boolean isTopLevelOnly() {
        return false;
    }
    
    @NotNull
    @Override
    public String getReadableName(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor$2", "getReadableName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String readableName = null;
        Label_0116: {
            if (ocSymbol instanceof OCStructSymbol) {
                final Couple<String> googleTestName = CidrGoogleTestUtil.extractGoogleTestName((OCStructSymbol)ocSymbol);
                String s = null;
                Label_0081: {
                    try {
                        if (googleTestName == null) {
                            break Label_0116;
                        }
                        final Couple<String> couple = googleTestName;
                        final Object o = couple.first;
                        s = (String)o;
                        if (s == null) {
                            break Label_0081;
                        }
                        return s;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final Couple<String> couple = googleTestName;
                        final Object o = couple.first;
                        s = (String)o;
                        if (s == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor$2", "getReadableName"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                return s;
            }
            try {
                readableName = super.getReadableName(ocSymbol);
                if (readableName == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor$2", "getReadableName"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return readableName;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}