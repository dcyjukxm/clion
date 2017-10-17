// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import java.math.BigInteger;
import java.util.List;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import java.util.regex.Matcher;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.regex.Pattern;

public class LLValueData
{
    private static final Pattern HEX_VALUE_PATTERN;
    private static final String NIL_PATTERN_STRING = "0x0+";
    private static final Pattern NULL_POINTER_PATTERN;
    @NotNull
    private final String myValue;
    @Nullable
    private final String myDescription;
    private final boolean myHasLongerDescription;
    private final boolean myMayHaveChildren;
    private final boolean myIsSynthetic;
    
    public LLValueData(@NotNull final String myValue, @Nullable final String myDescription, final boolean myHasLongerDescription, final boolean myMayHaveChildren, final boolean myIsSynthetic) {
        if (myValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/backend/LLValueData", "<init>"));
        }
        this.myValue = myValue;
        this.myDescription = myDescription;
        this.myHasLongerDescription = myHasLongerDescription;
        this.myMayHaveChildren = myMayHaveChildren;
        this.myIsSynthetic = myIsSynthetic;
    }
    
    @NotNull
    public String getValue() {
        String myValue;
        try {
            myValue = this.myValue;
            if (myValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLValueData", "getValue"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return myValue;
    }
    
    @Nullable
    public String getDescription() {
        return this.myDescription;
    }
    
    public boolean hasLongerDescription() {
        return this.myHasLongerDescription;
    }
    
    @NotNull
    public String getPresentableValue() {
        String s = null;
        Label_0022: {
            try {
                if (this.myDescription != null) {
                    final String s2;
                    s = (s2 = this.myDescription);
                    break Label_0022;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            String s2;
            s = (s2 = this.myValue);
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLValueData", "getPresentableValue"));
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
        }
        return s;
    }
    
    public boolean mayHaveChildren() {
        return this.myMayHaveChildren;
    }
    
    public boolean isSynthetic() {
        return this.myIsSynthetic;
    }
    
    public boolean isPointer() {
        try {
            if (this.getPointerOrNull() != null) {
                return true;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public boolean isValidPointer() {
        final String pointerOrNull = this.getPointerOrNull();
        Label_0029: {
            try {
                if (pointerOrNull == null) {
                    return false;
                }
                final Pattern pattern = LLValueData.NULL_POINTER_PATTERN;
                final String s = pointerOrNull;
                final Matcher matcher = pattern.matcher(s);
                final boolean b = matcher.matches();
                if (!b) {
                    break Label_0029;
                }
                return false;
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            try {
                final Pattern pattern = LLValueData.NULL_POINTER_PATTERN;
                final String s = pointerOrNull;
                final Matcher matcher = pattern.matcher(s);
                final boolean b = matcher.matches();
                if (!b) {
                    return true;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    public boolean isNullPointer() {
        return a(this.getPointerOrNull());
    }
    
    private static boolean a(@Nullable final String s) {
        Label_0024: {
            try {
                if (s == null) {
                    return false;
                }
                final Pattern pattern = LLValueData.NULL_POINTER_PATTERN;
                final String s2 = s;
                final Matcher matcher = pattern.matcher(s2);
                final boolean b = matcher.matches();
                if (b) {
                    break Label_0024;
                }
                return false;
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            try {
                final Pattern pattern = LLValueData.NULL_POINTER_PATTERN;
                final String s2 = s;
                final Matcher matcher = pattern.matcher(s2);
                final boolean b = matcher.matches();
                if (b) {
                    return true;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @NotNull
    public String getPointer() throws DebuggerCommandException {
        final String pointerOrNull = this.getPointerOrNull();
        try {
            if (pointerOrNull == null) {
                throw new DebuggerCommandException(CidrDebuggerBundle.message("debug.command.error.notAPointer", this.myValue));
            }
        }
        catch (DebuggerCommandException ex) {
            throw b(ex);
        }
        String s;
        try {
            s = pointerOrNull;
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLValueData", "getPointer"));
            }
        }
        catch (DebuggerCommandException ex2) {
            throw b(ex2);
        }
        return s;
    }
    
    @Nullable
    public String getPointerOrNull() {
        return getPointer(this.myValue);
    }
    
    @Nullable
    public static String getPointer(@Nullable final String s) {
        try {
            if (s == null) {
                return null;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final List matches = StringUtil.findMatches(s, LLValueData.HEX_VALUE_PATTERN);
        try {
            if (matches.size() == 1) {
                return matches.get(0);
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    @Nullable
    public String getPresentablePointer() {
        String s = this.getPointerOrNull();
        try {
            if (s == null) {
                return null;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (a(s)) {
                return "0x0";
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        int n = 2;
    Label_0066:
        while (true) {
            Label_0056: {
                try {
                    if (n >= s.length()) {
                        break Label_0066;
                    }
                    final String s2 = s;
                    final int n2 = n;
                    final char c = s2.charAt(n2);
                    final char c2 = '0';
                    if (c == c2) {
                        break Label_0056;
                    }
                    break Label_0066;
                }
                catch (NumberFormatException ex3) {
                    throw b(ex3);
                }
                try {
                    final String s2 = s;
                    final int n2 = n;
                    final char c = s2.charAt(n2);
                    final char c2 = '0';
                    if (c == c2) {
                        ++n;
                        continue;
                    }
                }
                catch (NumberFormatException ex4) {
                    throw b(ex4);
                }
            }
            break;
        }
        if (n > 2) {
            s = "0x" + s.substring(n);
        }
        return s;
    }
    
    public boolean isTrue() {
        try {
            if (!this.isFalse()) {
                return true;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public boolean isFalse() {
        for (final String s : new String[] { "0x0+", "0+", "false", "NO", "'\\\\0'", "0 [LUu]?'.*'" }) {
            try {
                if (Pattern.compile(s).matcher(this.myValue).matches()) {
                    return true;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
        }
        return false;
    }
    
    public long intValue() throws DebuggerCommandException {
        try {
            final String pointerOrNull = this.getPointerOrNull();
            if (pointerOrNull != null) {
                return DebuggerDriver.parseAddressSafe(pointerOrNull).unsignedLongValue();
            }
            return new BigInteger(this.myValue).longValue();
        }
        catch (NumberFormatException ex) {
            throw new DebuggerCommandException(CidrDebuggerBundle.message("debug.command.error.cannotReadInteger", this.myValue));
        }
    }
    
    @Override
    public String toString() {
        StringBuilder append = null;
        Label_0025: {
            StringBuilder sb;
            try {
                sb = new StringBuilder();
                if (this.myIsSynthetic) {
                    final String s = "[synthetic]";
                    break Label_0025;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            final String s = "";
            try {
                append = sb.append(s).append(this.myValue).append("(").append(this.myDescription).append(")");
                if (this.myMayHaveChildren) {
                    final String s2 = ",has children";
                    return append.append(s2).toString();
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
        }
        final String s2 = "";
        return append.append(s2).toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final LLValueData llValueData = this;
                final Class<? extends LLValueData> clazz = llValueData.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            try {
                final LLValueData llValueData = this;
                final Class<? extends LLValueData> clazz = llValueData.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
        }
        final LLValueData llValueData2 = (LLValueData)o;
        try {
            if (this.myMayHaveChildren != llValueData2.myMayHaveChildren) {
                return false;
            }
        }
        catch (NumberFormatException ex4) {
            throw b(ex4);
        }
        try {
            if (this.myIsSynthetic != llValueData2.myIsSynthetic) {
                return false;
            }
        }
        catch (NumberFormatException ex5) {
            throw b(ex5);
        }
        try {
            if (!this.myValue.equals(llValueData2.myValue)) {
                return false;
            }
        }
        catch (NumberFormatException ex6) {
            throw b(ex6);
        }
        Label_0133: {
            Label_0126: {
                try {
                    if (this.myDescription == null) {
                        break Label_0133;
                    }
                    final LLValueData llValueData3 = this;
                    final String s = llValueData3.myDescription;
                    final LLValueData llValueData4 = llValueData2;
                    final String s2 = llValueData4.myDescription;
                    final boolean b = s.equals(s2);
                    if (!b) {
                        break Label_0126;
                    }
                    return true;
                }
                catch (NumberFormatException ex7) {
                    throw b(ex7);
                }
                try {
                    final LLValueData llValueData3 = this;
                    final String s = llValueData3.myDescription;
                    final LLValueData llValueData4 = llValueData2;
                    final String s2 = llValueData4.myDescription;
                    final boolean b = s.equals(s2);
                    if (!b) {
                        return false;
                    }
                    return true;
                }
                catch (NumberFormatException ex8) {
                    throw b(ex8);
                }
            }
            try {
                if (llValueData2.myDescription != null) {
                    return false;
                }
            }
            catch (NumberFormatException ex9) {
                throw b(ex9);
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int hashCode = this.myValue.hashCode();
        int n = 0;
        int hashCode2 = 0;
        Label_0034: {
            try {
                n = 31 * hashCode;
                if (this.myDescription != null) {
                    hashCode2 = this.myDescription.hashCode();
                    break Label_0034;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            hashCode2 = 0;
        }
        final int n2 = n + hashCode2;
        int n3 = 0;
        int n4 = 0;
        Label_0056: {
            try {
                n3 = 31 * n2;
                if (this.myMayHaveChildren) {
                    n4 = 1;
                    break Label_0056;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            n4 = 0;
        }
        final int n5 = n3 + n4;
        int n6;
        try {
            n6 = 31 * n5;
            if (this.myIsSynthetic) {
                final int n7 = 1;
                return n6 + n7;
            }
        }
        catch (NumberFormatException ex3) {
            throw b(ex3);
        }
        final int n7 = 0;
        return n6 + n7;
    }
    
    static {
        HEX_VALUE_PATTERN = Pattern.compile("^(0x\\p{XDigit}+)");
        NULL_POINTER_PATTERN = Pattern.compile("0x0+");
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
