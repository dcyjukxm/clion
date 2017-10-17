// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

public static class IncludePath
{
    private String myPath;
    private boolean myAngleBrackets;
    public static final IncludePath EMPTY;
    
    public IncludePath() {
    }
    
    public IncludePath(final String myPath, final boolean myAngleBrackets) {
        this.myPath = myPath;
        this.myAngleBrackets = myAngleBrackets;
    }
    
    @Override
    public String toString() {
        return this.myAngleBrackets ? ("<" + this.myPath + ">") : ("\"" + this.myPath + "\"");
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IncludePath)) {
            return false;
        }
        final IncludePath includePath = (IncludePath)o;
        if (this.myAngleBrackets != includePath.myAngleBrackets) {
            return false;
        }
        if (this.myPath != null) {
            if (this.myPath.equals(includePath.myPath)) {
                return true;
            }
        }
        else if (includePath.myPath == null) {
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return 31 * ((this.myPath != null) ? this.myPath.hashCode() : 0) + (this.myAngleBrackets ? 1 : 0);
    }
    
    public String getPath() {
        return this.myPath;
    }
    
    public boolean isAngleBrackets() {
        return this.myAngleBrackets;
    }
    
    static {
        EMPTY = new EmptyIncludePath();
    }
    
    private static class EmptyIncludePath extends IncludePath
    {
        private EmptyIncludePath() {
            super("", false);
        }
    }
}
