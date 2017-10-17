// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;

public enum Delimiters
{
    ANGLE_BRACKETS {
        @NotNull
        @Override
        public String getBeforeText() {
            String s;
            try {
                s = "<";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/OCIncludeDirective$Delimiters$1", "getBeforeText"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return s;
        }
        
        @NotNull
        @Override
        public String getAfterText() {
            String s;
            try {
                s = ">";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/OCIncludeDirective$Delimiters$1", "getAfterText"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return s;
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }, 
    QUOTES {
        @NotNull
        @Override
        public String getBeforeText() {
            String s;
            try {
                s = "\"";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/OCIncludeDirective$Delimiters$2", "getBeforeText"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return s;
        }
        
        @NotNull
        @Override
        public String getAfterText() {
            String s;
            try {
                s = "\"";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/OCIncludeDirective$Delimiters$2", "getAfterText"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return s;
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }, 
    NONE {
        @NotNull
        @Override
        public String getBeforeText() {
            String s;
            try {
                s = "";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/OCIncludeDirective$Delimiters$3", "getBeforeText"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return s;
        }
        
        @NotNull
        @Override
        public String getAfterText() {
            String s;
            try {
                s = "";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/OCIncludeDirective$Delimiters$3", "getAfterText"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return s;
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    };
    
    @NotNull
    public abstract String getBeforeText();
    
    @NotNull
    public abstract String getAfterText();
}
