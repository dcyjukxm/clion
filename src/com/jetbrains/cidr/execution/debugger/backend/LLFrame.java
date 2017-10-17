// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.memory.Address;
import org.jetbrains.annotations.Nullable;

public class LLFrame
{
    public static final String UNKNOWN_FUNCTION;
    private final int myIndex;
    @Nullable
    private final String myFunction;
    @Nullable
    private final String myFile;
    private final int myLine;
    @NotNull
    private final Address myProgramCounter;
    @Nullable
    private final DebuggerDriver.DebuggerLanguage myLanguage;
    private final boolean myOptimized;
    
    public LLFrame(final int n, @Nullable final String s, @Nullable final String s2, final int n2, final long n3, @Nullable final DebuggerDriver.DebuggerLanguage debuggerLanguage, final boolean b) {
        this(n, s, s2, n2, Address.fromUnsignedLong(n3), debuggerLanguage, b);
    }
    
    public LLFrame(final int myIndex, @Nullable final String myFunction, @Nullable final String s, final int myLine, @NotNull final Address myProgramCounter, @Nullable final DebuggerDriver.DebuggerLanguage myLanguage, final boolean myOptimized) {
        if (myProgramCounter == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pc", "com/jetbrains/cidr/execution/debugger/backend/LLFrame", "<init>"));
        }
        this.myIndex = myIndex;
        this.myFunction = myFunction;
        this.myFile = ((s == null) ? null : FileUtil.toSystemIndependentName(s));
        this.myLine = myLine;
        this.myProgramCounter = myProgramCounter;
        this.myLanguage = myLanguage;
        this.myOptimized = myOptimized;
    }
    
    public int getIndex() {
        return this.myIndex;
    }
    
    @NotNull
    public String getFunction() {
        String defaultIfEmpty;
        try {
            defaultIfEmpty = StringUtil.defaultIfEmpty(this.myFunction, LLFrame.UNKNOWN_FUNCTION);
            if (defaultIfEmpty == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLFrame", "getFunction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return defaultIfEmpty;
    }
    
    @Nullable
    public String getFile() {
        return this.myFile;
    }
    
    public int getLine() {
        return this.myLine;
    }
    
    @NotNull
    public Address getProgramCounter() {
        Address myProgramCounter;
        try {
            myProgramCounter = this.myProgramCounter;
            if (myProgramCounter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLFrame", "getProgramCounter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myProgramCounter;
    }
    
    @Nullable
    public DebuggerDriver.DebuggerLanguage getLanguage() {
        return this.myLanguage;
    }
    
    public boolean getOptimized() {
        return this.myOptimized;
    }
    
    public boolean hasDebugInfo() {
        try {
            if (this.myFile != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean hasSymbolInfo() {
        try {
            if (this.myFunction != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public String toString() {
        String s = null;
        Object[] array = null;
        int n = 0;
        String s2 = null;
        Label_0076: {
            try {
                s = "%d: %s %s@%s:%d (%s)%s";
                array = new Object[] { this.myIndex, this.myProgramCounter, this.myFunction, this.myFile, this.myLine, this.myLanguage, null };
                n = 6;
                if (this.myOptimized) {
                    s2 = " [opt]";
                    break Label_0076;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            s2 = "";
        }
        array[n] = s2;
        return String.format(s, array);
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
                final LLFrame llFrame = this;
                final Class<? extends LLFrame> clazz = llFrame.getClass();
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
                final LLFrame llFrame = this;
                final Class<? extends LLFrame> clazz = llFrame.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final LLFrame llFrame2 = (LLFrame)o;
        try {
            if (this.myLine != llFrame2.myLine) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (this.myIndex != llFrame2.myIndex) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        Label_0174: {
            Label_0161: {
                Label_0154: {
                    Label_0126: {
                        Label_0113: {
                            Label_0106: {
                                try {
                                    if (this.myFunction == null) {
                                        break Label_0113;
                                    }
                                    final LLFrame llFrame3 = this;
                                    final String s = llFrame3.myFunction;
                                    final LLFrame llFrame4 = llFrame2;
                                    final String s2 = llFrame4.myFunction;
                                    final boolean b = s.equals(s2);
                                    if (!b) {
                                        break Label_0106;
                                    }
                                    break Label_0126;
                                }
                                catch (IllegalArgumentException ex6) {
                                    throw a(ex6);
                                }
                                try {
                                    final LLFrame llFrame3 = this;
                                    final String s = llFrame3.myFunction;
                                    final LLFrame llFrame4 = llFrame2;
                                    final String s2 = llFrame4.myFunction;
                                    final boolean b = s.equals(s2);
                                    if (!b) {
                                        return false;
                                    }
                                    break Label_0126;
                                }
                                catch (IllegalArgumentException ex7) {
                                    throw a(ex7);
                                }
                            }
                            try {
                                if (llFrame2.myFunction != null) {
                                    return false;
                                }
                            }
                            catch (IllegalArgumentException ex8) {
                                throw a(ex8);
                            }
                        }
                        try {
                            if (this.myFile == null) {
                                break Label_0161;
                            }
                            final LLFrame llFrame5 = this;
                            final String s3 = llFrame5.myFile;
                            final LLFrame llFrame6 = llFrame2;
                            final String s4 = llFrame6.myFile;
                            final boolean b2 = s3.equals(s4);
                            if (!b2) {
                                break Label_0154;
                            }
                            break Label_0174;
                        }
                        catch (IllegalArgumentException ex9) {
                            throw a(ex9);
                        }
                    }
                    try {
                        final LLFrame llFrame5 = this;
                        final String s3 = llFrame5.myFile;
                        final LLFrame llFrame6 = llFrame2;
                        final String s4 = llFrame6.myFile;
                        final boolean b2 = s3.equals(s4);
                        if (!b2) {
                            return false;
                        }
                        break Label_0174;
                    }
                    catch (IllegalArgumentException ex10) {
                        throw a(ex10);
                    }
                }
                try {
                    if (llFrame2.myFile != null) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex11) {
                    throw a(ex11);
                }
            }
            try {
                if (!this.myProgramCounter.equals(llFrame2.myProgramCounter)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex12) {
                throw a(ex12);
            }
        }
        Label_0242: {
            Label_0229: {
                Label_0222: {
                    try {
                        if (this.myLanguage == null) {
                            break Label_0229;
                        }
                        final LLFrame llFrame7 = this;
                        final DebuggerDriver.DebuggerLanguage debuggerLanguage = llFrame7.myLanguage;
                        final LLFrame llFrame8 = llFrame2;
                        final DebuggerDriver.DebuggerLanguage debuggerLanguage2 = llFrame8.myLanguage;
                        final boolean b3 = debuggerLanguage.equals(debuggerLanguage2);
                        if (!b3) {
                            break Label_0222;
                        }
                        break Label_0242;
                    }
                    catch (IllegalArgumentException ex13) {
                        throw a(ex13);
                    }
                    try {
                        final LLFrame llFrame7 = this;
                        final DebuggerDriver.DebuggerLanguage debuggerLanguage = llFrame7.myLanguage;
                        final LLFrame llFrame8 = llFrame2;
                        final DebuggerDriver.DebuggerLanguage debuggerLanguage2 = llFrame8.myLanguage;
                        final boolean b3 = debuggerLanguage.equals(debuggerLanguage2);
                        if (!b3) {
                            return false;
                        }
                        break Label_0242;
                    }
                    catch (IllegalArgumentException ex14) {
                        throw a(ex14);
                    }
                }
                try {
                    if (llFrame2.myLanguage != null) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex15) {
                    throw a(ex15);
                }
            }
            try {
                if (this.myOptimized != llFrame2.myOptimized) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex16) {
                throw a(ex16);
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int myIndex = this.myIndex;
        int n = 0;
        int hashCode = 0;
        Label_0031: {
            try {
                n = 31 * myIndex;
                if (this.myFunction != null) {
                    hashCode = this.myFunction.hashCode();
                    break Label_0031;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            hashCode = 0;
        }
        final int n2 = n + hashCode;
        int n3 = 0;
        int hashCode2 = 0;
        Label_0059: {
            try {
                n3 = 31 * n2;
                if (this.myFile != null) {
                    hashCode2 = this.myFile.hashCode();
                    break Label_0059;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            hashCode2 = 0;
        }
        final int n4 = 31 * (31 * (n3 + hashCode2) + this.myLine) + this.myProgramCounter.hashCode();
        int n5 = 0;
        int hashCode3 = 0;
        Label_0110: {
            try {
                n5 = 31 * n4;
                if (this.myLanguage != null) {
                    hashCode3 = this.myLanguage.hashCode();
                    break Label_0110;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            hashCode3 = 0;
        }
        final int n6 = n5 + hashCode3;
        int n7;
        try {
            n7 = 31 * n6;
            if (this.myOptimized) {
                final int n8 = 1;
                return n7 + n8;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final int n8 = 0;
        return n7 + n8;
    }
    
    static {
        UNKNOWN_FUNCTION = CidrDebuggerBundle.message("debug.frames.unknownFunction", new Object[0]);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
