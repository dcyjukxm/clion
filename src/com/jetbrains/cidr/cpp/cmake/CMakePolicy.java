// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import java.text.DecimalFormat;

public class CMakePolicy
{
    public static final int MINIMUM_POLICY_NUMBER = 0;
    public static final int MAXIMUM_POLICY_NUMBER = 67;
    private static final String[] CMAKE_POLICY_NAMES;
    
    public static int knownPoliciesNumber() {
        return 68;
    }
    
    public static String getPolicyName(final int n) {
        return CMakePolicy.CMAKE_POLICY_NAMES[n];
    }
    
    public static int getSupportedPoliciesNumber() {
        return 68;
    }
    
    static {
        CMAKE_POLICY_NAMES = new String[knownPoliciesNumber()];
        final DecimalFormat decimalFormat = new DecimalFormat("0000");
        for (int i = 0; i < CMakePolicy.CMAKE_POLICY_NAMES.length; ++i) {
            CMakePolicy.CMAKE_POLICY_NAMES[i] = "CMP" + decimalFormat.format(i);
        }
    }
}
