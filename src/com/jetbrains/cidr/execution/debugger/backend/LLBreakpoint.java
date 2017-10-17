// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public class LLBreakpoint extends LLCodepoint
{
    @NotNull
    private final String myFile;
    private final int myLine;
    private final boolean myPending;
    @Nullable
    private String myCondition;
    @Nullable
    private String myConditionError;
    
    public LLBreakpoint(final int n, @NotNull final String myFile, final int myLine, final boolean myPending, @Nullable final String myCondition, @Nullable final String myConditionError) {
        if (myFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/execution/debugger/backend/LLBreakpoint", "<init>"));
        }
        super(n);
        this.myFile = myFile;
        this.myLine = myLine;
        this.myPending = myPending;
        this.myCondition = myCondition;
        this.myConditionError = myConditionError;
    }
    
    @NotNull
    public String getFile() {
        String myFile;
        try {
            myFile = this.myFile;
            if (myFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLBreakpoint", "getFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myFile;
    }
    
    public int getLine() {
        return this.myLine;
    }
    
    @Nullable
    public String getCondition() {
        return this.myCondition;
    }
    
    @Nullable
    public String getConditionError() {
        return this.myConditionError;
    }
    
    @Override
    public String toString() {
        String s = "Breakpoint-" + this.getId() + "@" + this.myFile + ":" + this.myLine;
        if (this.myPending) {
            s += "(pending)";
        }
        if (this.myCondition != null) {
            s = s + ":condition:" + this.myCondition;
        }
        if (this.myConditionError != null) {
            s = s + ":condition-error:" + this.myConditionError;
        }
        return s;
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final LLBreakpoint llBreakpoint = this;
                final Class<? extends LLBreakpoint> clazz = llBreakpoint.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final LLBreakpoint llBreakpoint = this;
                final Class<? extends LLBreakpoint> clazz = llBreakpoint.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (!super.equals(o)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        final LLBreakpoint llBreakpoint2 = (LLBreakpoint)o;
        try {
            if (this.myLine != llBreakpoint2.myLine) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (this.myPending != llBreakpoint2.myPending) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        Label_0188: {
            Label_0175: {
                Label_0168: {
                    Label_0140: {
                        Label_0127: {
                            Label_0120: {
                                try {
                                    if (this.myCondition == null) {
                                        break Label_0127;
                                    }
                                    final LLBreakpoint llBreakpoint3 = this;
                                    final String s = llBreakpoint3.myCondition;
                                    final LLBreakpoint llBreakpoint4 = llBreakpoint2;
                                    final String s2 = llBreakpoint4.myCondition;
                                    final boolean b = s.equals(s2);
                                    if (!b) {
                                        break Label_0120;
                                    }
                                    break Label_0140;
                                }
                                catch (IllegalArgumentException ex7) {
                                    throw a(ex7);
                                }
                                try {
                                    final LLBreakpoint llBreakpoint3 = this;
                                    final String s = llBreakpoint3.myCondition;
                                    final LLBreakpoint llBreakpoint4 = llBreakpoint2;
                                    final String s2 = llBreakpoint4.myCondition;
                                    final boolean b = s.equals(s2);
                                    if (!b) {
                                        return false;
                                    }
                                    break Label_0140;
                                }
                                catch (IllegalArgumentException ex8) {
                                    throw a(ex8);
                                }
                            }
                            try {
                                if (llBreakpoint2.myCondition != null) {
                                    return false;
                                }
                            }
                            catch (IllegalArgumentException ex9) {
                                throw a(ex9);
                            }
                        }
                        try {
                            if (this.myConditionError == null) {
                                break Label_0175;
                            }
                            final LLBreakpoint llBreakpoint5 = this;
                            final String s3 = llBreakpoint5.myConditionError;
                            final LLBreakpoint llBreakpoint6 = llBreakpoint2;
                            final String s4 = llBreakpoint6.myConditionError;
                            final boolean b2 = s3.equals(s4);
                            if (!b2) {
                                break Label_0168;
                            }
                            break Label_0188;
                        }
                        catch (IllegalArgumentException ex10) {
                            throw a(ex10);
                        }
                    }
                    try {
                        final LLBreakpoint llBreakpoint5 = this;
                        final String s3 = llBreakpoint5.myConditionError;
                        final LLBreakpoint llBreakpoint6 = llBreakpoint2;
                        final String s4 = llBreakpoint6.myConditionError;
                        final boolean b2 = s3.equals(s4);
                        if (!b2) {
                            return false;
                        }
                        break Label_0188;
                    }
                    catch (IllegalArgumentException ex11) {
                        throw a(ex11);
                    }
                }
                try {
                    if (llBreakpoint2.myConditionError != null) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex12) {
                    throw a(ex12);
                }
            }
            try {
                if (!this.myFile.equals(llBreakpoint2.myFile)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex13) {
                throw a(ex13);
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * super.hashCode() + this.myFile.hashCode()) + this.myLine;
        int n2 = 0;
        int n3 = 0;
        Label_0048: {
            try {
                n2 = 31 * n;
                if (this.myPending) {
                    n3 = 1;
                    break Label_0048;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            n3 = 0;
        }
        final int n4 = n2 + n3;
        int n5 = 0;
        int hashCode = 0;
        Label_0076: {
            try {
                n5 = 31 * n4;
                if (this.myCondition != null) {
                    hashCode = this.myCondition.hashCode();
                    break Label_0076;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            hashCode = 0;
        }
        final int n6 = n5 + hashCode;
        int n7;
        try {
            n7 = 31 * n6;
            if (this.myConditionError != null) {
                final int hashCode2 = this.myConditionError.hashCode();
                return n7 + hashCode2;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final int hashCode2 = 0;
        return n7 + hashCode2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
