// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.settings;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public enum Case
{
    TO_UPPER(0, "To upper") {
        @NotNull
        @Override
        public String apply(@NonNls @NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/settings/Case$1", "apply"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            String upperCase;
            try {
                upperCase = s.toUpperCase();
                if (upperCase == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/Case$1", "apply"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            return upperCase;
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }, 
    TO_LOWER(1, "To lower") {
        @NotNull
        @Override
        public String apply(@NonNls @NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/settings/Case$2", "apply"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            String lowerCase;
            try {
                lowerCase = s.toLowerCase();
                if (lowerCase == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/Case$2", "apply"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            return lowerCase;
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }, 
    DO_NOT_CHANGE(2, "Do not change") {
        @NotNull
        @Override
        public String apply(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/settings/Case$3", "apply"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/Case$3", "apply"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            return s;
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    };
    
    @NotNull
    public static final String[] TOKEN_CASE_NAMES;
    @NotNull
    public static final int[] TOKEN_CASE_VALUES;
    private final int myValue;
    @NotNull
    private final String myUiDescription;
    
    @Nullable
    public static Case resolveByValue(final int n) {
        for (int i = 0; i < values().length; ++i) {
            final Case case1 = values()[i];
            try {
                if (case1.getValue() == n) {
                    return case1;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    private Case(final int myValue, final String myUiDescription) {
        if (myUiDescription == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "uiDescription", "com/jetbrains/cidr/cpp/cmake/settings/Case", "<init>"));
        }
        super(s, n);
        this.myValue = myValue;
        this.myUiDescription = myUiDescription;
    }
    
    @NotNull
    public String getUiDescription() {
        String myUiDescription;
        try {
            myUiDescription = this.myUiDescription;
            if (myUiDescription == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/Case", "getUiDescription"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myUiDescription;
    }
    
    public int getValue() {
        return this.myValue;
    }
    
    @NotNull
    public abstract String apply(@NotNull final String p0);
    
    static {
        TOKEN_CASE_NAMES = new String[values().length];
        TOKEN_CASE_VALUES = new int[values().length];
        int i = 0;
        try {
            while (i < values().length) {
                Case.TOKEN_CASE_NAMES[i] = values()[i].getUiDescription();
                Case.TOKEN_CASE_VALUES[i] = i;
                ++i;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
