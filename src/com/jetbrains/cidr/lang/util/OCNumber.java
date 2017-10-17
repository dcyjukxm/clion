// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.OCLog;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.text.StringUtil;
import java.math.BigInteger;

public class OCNumber extends BigInteger
{
    public static final byte[] MINUS_ONE;
    public static final OCNumber MAX_UINT32_T;
    public static final OCNumber MAX_UINT64_T;
    public static final OCNumber MAX_UINT128_T;
    private static final OCNumber[] SIZE2MASK;
    private final int mySizeInBytes;
    private final boolean myIsSigned;
    
    public static OCNumber getMask(int min) {
        min = Math.min(min, 16);
        OCNumber ocNumber = OCNumber.SIZE2MASK[min - 1];
        if (ocNumber == null) {
            ocNumber = new OCNumber(StringUtil.repeat("FF", min), 16, min, true);
            OCNumber.SIZE2MASK[min - 1] = ocNumber;
        }
        return ocNumber;
    }
    
    public OCNumber(final byte[] array, final int mySizeInBytes, final boolean myIsSigned) {
        super(array);
        this.mySizeInBytes = mySizeInBytes;
        this.myIsSigned = myIsSigned;
    }
    
    protected OCNumber(final String s, final int n, final int mySizeInBytes, final boolean myIsSigned) {
        super(s, n);
        this.mySizeInBytes = mySizeInBytes;
        this.myIsSigned = myIsSigned;
    }
    
    public int getSizeInBytes() {
        return this.mySizeInBytes;
    }
    
    public boolean isSigned() {
        return this.myIsSigned;
    }
    
    public static OCNumber parse(final String s, final int n, final boolean b) throws NumberFormatException {
        final ParseInfo parse = ParseInfo.parse(s);
        final BigInteger bigInteger = new BigInteger(parse.numbers, parse.radix);
        try {
            if (parse.negative) {
                final BigInteger negate = bigInteger.negate();
                return convert(negate, n, b);
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final BigInteger negate = bigInteger;
        return convert(negate, n, b);
    }
    
    @Override
    public OCNumber negate() {
        return a(super.negate(), this);
    }
    
    public OCNumber inverse() {
        return a(super.xor(getMask(this.mySizeInBytes)), this);
    }
    
    @NotNull
    @Override
    public OCNumber add(@NotNull final BigInteger bigInteger) {
        try {
            if (bigInteger == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/util/OCNumber", "add"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        OCNumber a;
        try {
            a = a(super.add(bigInteger), this.a(bigInteger));
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "add"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return a;
    }
    
    @NotNull
    @Override
    public OCNumber subtract(@NotNull final BigInteger bigInteger) {
        try {
            if (bigInteger == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/util/OCNumber", "subtract"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        OCNumber a;
        try {
            a = a(super.subtract(bigInteger), this.a(bigInteger));
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "subtract"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return a;
    }
    
    @NotNull
    @Override
    public OCNumber multiply(@NotNull final BigInteger bigInteger) {
        try {
            if (bigInteger == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/util/OCNumber", "multiply"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        OCNumber a;
        try {
            a = a(super.multiply(bigInteger), this.a(bigInteger));
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "multiply"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return a;
    }
    
    @NotNull
    @Override
    public OCNumber divide(@NotNull final BigInteger bigInteger) {
        try {
            if (bigInteger == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/util/OCNumber", "divide"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        OCNumber a;
        try {
            a = a(super.divide(bigInteger), this.a(bigInteger));
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "divide"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return a;
    }
    
    @NotNull
    @Override
    public OCNumber mod(@NotNull final BigInteger bigInteger) {
        try {
            if (bigInteger == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/util/OCNumber", "mod"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        OCNumber a;
        try {
            a = a(super.mod(bigInteger), this.a(bigInteger));
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "mod"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return a;
    }
    
    @NotNull
    @Override
    public OCNumber or(@NotNull final BigInteger bigInteger) {
        try {
            if (bigInteger == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/util/OCNumber", "or"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        OCNumber a;
        try {
            a = a(super.or(bigInteger), this.a(bigInteger));
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "or"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return a;
    }
    
    @NotNull
    @Override
    public OCNumber and(@NotNull final BigInteger bigInteger) {
        try {
            if (bigInteger == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/util/OCNumber", "and"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        OCNumber a;
        try {
            a = a(super.and(bigInteger), this.a(bigInteger));
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "and"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return a;
    }
    
    @NotNull
    @Override
    public OCNumber xor(@NotNull final BigInteger bigInteger) {
        try {
            if (bigInteger == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/util/OCNumber", "xor"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        OCNumber a;
        try {
            a = a(super.xor(bigInteger), this.a(bigInteger));
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "xor"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return a;
    }
    
    @NotNull
    @Override
    public OCNumber shiftLeft(final int n) {
        OCNumber a;
        try {
            OCLog.LOG.error("OCNumber.shiftLeft(int arg) is deprecated. Use OCNumber.shiftLeft(@NotNull BigInteger arg) instead");
            a = a(super.shiftLeft(n), this);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "shiftLeft"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return a;
    }
    
    @NotNull
    public OCNumber shiftLeft(@NotNull final BigInteger bigInteger) {
        try {
            if (bigInteger == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/util/OCNumber", "shiftLeft"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        OCNumber a;
        try {
            a = a(super.shiftLeft(bigInteger.intValue()), this.a(bigInteger));
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "shiftLeft"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return a;
    }
    
    @NotNull
    @Override
    public OCNumber shiftRight(final int n) {
        OCNumber a;
        try {
            OCLog.LOG.error("OCNumber.shiftRight(int arg) is deprecated. Use OCNumber.shiftRight(@NotNull BigInteger arg) instead");
            a = a(super.shiftRight(n), this);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "shiftRight"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return a;
    }
    
    @NotNull
    public OCNumber shiftRight(@NotNull final BigInteger bigInteger) {
        try {
            if (bigInteger == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/util/OCNumber", "shiftRight"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        OCNumber a;
        try {
            a = a(super.shiftRight(bigInteger.intValue()), this.a(bigInteger));
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "shiftRight"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return a;
    }
    
    @Override
    public int compareTo(@NotNull final BigInteger bigInteger) {
        try {
            if (bigInteger == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/util/OCNumber", "compareTo"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        if (!this.a(bigInteger).myIsSigned) {
            final int signum = this.signum();
            final int signum2 = bigInteger.signum();
            Label_0247: {
                Label_0225: {
                    Label_0203: {
                        Label_0094: {
                            Label_0084: {
                                try {
                                    if (signum < 0) {
                                        break Label_0094;
                                    }
                                    final int n = signum2;
                                    if (n >= 0) {
                                        break Label_0084;
                                    }
                                    break Label_0094;
                                }
                                catch (NumberFormatException ex2) {
                                    throw b(ex2);
                                }
                                try {
                                    final int n = signum2;
                                    if (n >= 0) {
                                        return super.compareTo(bigInteger);
                                    }
                                }
                                catch (NumberFormatException ex3) {
                                    throw b(ex3);
                                }
                            }
                            try {
                                if (signum > 0 || signum2 > 0) {
                                    break Label_0203;
                                }
                            }
                            catch (NumberFormatException ex4) {
                                throw b(ex4);
                            }
                        }
                        final OCNumber value = valueOf(bigInteger);
                        try {
                            if (value.mySizeInBytes == this.mySizeInBytes) {
                                return -super.compareTo(bigInteger);
                            }
                        }
                        catch (NumberFormatException ex5) {
                            throw b(ex5);
                        }
                        Label_0181: {
                            Label_0166: {
                                try {
                                    if (this.mySizeInBytes <= value.mySizeInBytes) {
                                        break Label_0181;
                                    }
                                    final OCNumber ocNumber = value;
                                    final boolean b = ocNumber.myIsSigned;
                                    if (b) {
                                        break Label_0166;
                                    }
                                    return 1;
                                }
                                catch (NumberFormatException ex6) {
                                    throw b(ex6);
                                }
                                try {
                                    final OCNumber ocNumber = value;
                                    final boolean b = ocNumber.myIsSigned;
                                    if (b) {
                                        return -super.compareTo(bigInteger);
                                    }
                                }
                                catch (NumberFormatException ex7) {
                                    throw b(ex7);
                                }
                            }
                            return 1;
                            try {
                                if (this.myIsSigned) {
                                    return -super.compareTo(bigInteger);
                                }
                            }
                            catch (NumberFormatException ex8) {
                                throw b(ex8);
                            }
                        }
                        return -1;
                        try {
                            if (signum >= 0) {
                                break Label_0225;
                            }
                            final int n4 = signum2;
                            if (n4 >= 0) {
                                return 1;
                            }
                            break Label_0225;
                        }
                        catch (NumberFormatException ex9) {
                            throw b(ex9);
                        }
                    }
                    try {
                        final int n4 = signum2;
                        if (n4 >= 0) {
                            return 1;
                        }
                    }
                    catch (NumberFormatException ex10) {
                        throw b(ex10);
                    }
                    try {
                        if (signum < 0) {
                            break Label_0247;
                        }
                        final int n5 = signum2;
                        if (n5 < 0) {
                            return -1;
                        }
                        break Label_0247;
                    }
                    catch (NumberFormatException ex11) {
                        throw b(ex11);
                    }
                }
                try {
                    final int n5 = signum2;
                    if (n5 < 0) {
                        return -1;
                    }
                }
                catch (NumberFormatException ex12) {
                    throw b(ex12);
                }
                try {
                    assert false;
                }
                catch (NumberFormatException ex13) {
                    throw b(ex13);
                }
            }
        }
        return super.compareTo(bigInteger);
    }
    
    @Override
    public String toString(final int n) {
        return new BigInteger(this.toByteArray()).toString(n);
    }
    
    public String toHexString() {
        StringBuilder append;
        try {
            append = new StringBuilder().append("0x").append(super.and(getMask(this.mySizeInBytes)).toString(16));
            if (this.myIsSigned) {
                final String s = "i";
                return append.append(s).append(this.mySizeInBytes * 8).toString();
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final String s = "u";
        return append.append(s).append(this.mySizeInBytes * 8).toString();
    }
    
    @Contract(pure = true)
    private OCNumber a(final Number n) {
        if (n instanceof OCNumber) {
            final OCNumber ocNumber = (OCNumber)n;
            try {
                if (ocNumber.mySizeInBytes > this.mySizeInBytes) {
                    return ocNumber;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            try {
                if (ocNumber.mySizeInBytes != this.mySizeInBytes) {
                    return this;
                }
                final OCNumber ocNumber2 = ocNumber;
                final boolean b = ocNumber2.myIsSigned;
                if (!b) {
                    return ocNumber;
                }
                return this;
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            try {
                final OCNumber ocNumber2 = ocNumber;
                final boolean b = ocNumber2.myIsSigned;
                if (!b) {
                    return ocNumber;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
        }
        return this;
    }
    
    @NotNull
    private static OCNumber a(@NotNull final BigInteger bigInteger, final OCNumber ocNumber) {
        try {
            if (bigInteger == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/lang/util/OCNumber", "convert"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        OCNumber convert;
        try {
            convert = convert(bigInteger, ocNumber.mySizeInBytes, ocNumber.myIsSigned);
            if (convert == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "convert"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return convert;
    }
    
    @NotNull
    public static OCNumber convert(@NotNull final BigInteger bigInteger, final int n, final boolean b) {
        try {
            if (bigInteger == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/lang/util/OCNumber", "convert"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length > n) {
            final byte[] array = new byte[n];
            System.arraycopy(byteArray, byteArray.length - n, array, 0, n);
            byteArray = array;
        }
        OCNumber ocNumber;
        try {
            ocNumber = new OCNumber(byteArray, n, b);
            if (ocNumber == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "convert"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return ocNumber;
    }
    
    @NotNull
    public static OCNumber valueOf(final long n) {
        OCNumber value;
        try {
            value = valueOf((Object)n);
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "valueOf"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return value;
    }
    
    @NotNull
    public static OCNumber valueOf(@NotNull final Object o) {
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/lang/util/OCNumber", "valueOf"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        Label_0164: {
            OCNumber ocNumber2 = null;
            Label_0129: {
                Label_0101: {
                    OCNumber ocNumber = null;
                    Label_0066: {
                        try {
                            if (!(o instanceof OCNumber)) {
                                break Label_0101;
                            }
                            final Object o2 = o;
                            ocNumber = (OCNumber)o2;
                            if (ocNumber == null) {
                                break Label_0066;
                            }
                            return ocNumber;
                        }
                        catch (NumberFormatException ex2) {
                            throw b(ex2);
                        }
                        try {
                            final Object o2 = o;
                            ocNumber = (OCNumber)o2;
                            if (ocNumber == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "valueOf"));
                            }
                        }
                        catch (NumberFormatException ex3) {
                            throw b(ex3);
                        }
                    }
                    return ocNumber;
                    try {
                        if (!(o instanceof BigInteger)) {
                            break Label_0164;
                        }
                        final Object o3 = o;
                        final BigInteger bigInteger = (BigInteger)o3;
                        final int n = 16;
                        final boolean b = true;
                        ocNumber2 = convert(bigInteger, n, b);
                        if (ocNumber2 == null) {
                            break Label_0129;
                        }
                        return ocNumber2;
                    }
                    catch (NumberFormatException ex4) {
                        throw b(ex4);
                    }
                }
                try {
                    final Object o3 = o;
                    final BigInteger bigInteger = (BigInteger)o3;
                    final int n = 16;
                    final boolean b = true;
                    ocNumber2 = convert(bigInteger, n, b);
                    if (ocNumber2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "valueOf"));
                    }
                }
                catch (NumberFormatException ex5) {
                    throw b(ex5);
                }
            }
            return ocNumber2;
        }
        int n2 = 0;
        long n3 = 0L;
        Label_0292: {
            if (o instanceof Boolean) {
                n2 = 1;
                long n4 = 0L;
                Label_0196: {
                    try {
                        if (o) {
                            n4 = 1L;
                            break Label_0196;
                        }
                    }
                    catch (NumberFormatException ex6) {
                        throw b(ex6);
                    }
                    n4 = 0L;
                }
                n3 = n4;
            }
            else if (o instanceof Byte) {
                n2 = 1;
                n3 = (long)o;
            }
            else if (o instanceof Short) {
                n2 = 2;
                n3 = (long)o;
            }
            else if (o instanceof Integer) {
                n2 = 4;
                n3 = (long)o;
            }
            else {
                try {
                    if (!(o instanceof Long)) {
                        if (!(o instanceof Number)) {
                            break Label_0292;
                        }
                    }
                }
                catch (NumberFormatException ex7) {
                    throw b(ex7);
                }
                n2 = 8;
                n3 = ((Number)o).longValue();
            }
        }
        if (n2 > 0) {
            final byte[] array = new byte[n2];
            int i = 0;
            try {
                while (i < n2) {
                    array[i] = (byte)(n3 >> (n2 - 1 - i << 3));
                    ++i;
                }
            }
            catch (NumberFormatException ex8) {
                throw b(ex8);
            }
            OCNumber ocNumber3;
            try {
                ocNumber3 = new OCNumber(array, n2, true);
                if (ocNumber3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "valueOf"));
                }
            }
            catch (NumberFormatException ex9) {
                throw b(ex9);
            }
            return ocNumber3;
        }
        OCNumber max_UINT128_T;
        try {
            OCLog.LOG.warn("Unknown type in OCNumber.valueOf:" + o.getClass());
            max_UINT128_T = OCNumber.MAX_UINT128_T;
            if (max_UINT128_T == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCNumber", "valueOf"));
            }
        }
        catch (NumberFormatException ex10) {
            throw b(ex10);
        }
        return max_UINT128_T;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCNumber.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        MINUS_ONE = new byte[] { -1 };
        MAX_UINT32_T = new OCNumber(OCNumber.MINUS_ONE, 4, true);
        MAX_UINT64_T = new OCNumber(OCNumber.MINUS_ONE, 8, true);
        MAX_UINT128_T = new OCNumber(OCNumber.MINUS_ONE, 16, true);
        SIZE2MASK = new OCNumber[16];
    }
    
    private static NumberFormatException b(final NumberFormatException ex) {
        return ex;
    }
    
    public static final class ParseInfo
    {
        public int radix;
        public String numbers;
        public int countL;
        public int countU;
        public boolean negative;
        
        public ParseInfo() {
            this.radix = 10;
            this.countL = 0;
            this.countU = 0;
            this.negative = false;
        }
        
        public static ParseInfo parse(String substring) throws NumberFormatException {
            final ParseInfo parseInfo = new ParseInfo();
            int i;
            for (i = substring.length() - 1; i >= 0; --i) {
                final char char1 = substring.charAt(i);
                Label_0097: {
                    Label_0080: {
                        Label_0061: {
                            Label_0044: {
                                try {
                                    if (char1 == 'L') {
                                        break Label_0044;
                                    }
                                    final char c = char1;
                                    final char c2 = 'l';
                                    if (c == c2) {
                                        break Label_0044;
                                    }
                                    break Label_0061;
                                }
                                catch (NumberFormatException ex) {
                                    throw b(ex);
                                }
                                try {
                                    final char c = char1;
                                    final char c2 = 'l';
                                    if (c == c2) {
                                        final ParseInfo parseInfo2 = parseInfo;
                                        ++parseInfo2.countL;
                                        continue;
                                    }
                                }
                                catch (NumberFormatException ex2) {
                                    throw b(ex2);
                                }
                            }
                            try {
                                if (char1 == 'U') {
                                    break Label_0080;
                                }
                                final char c3 = char1;
                                final char c4 = 'u';
                                if (c3 == c4) {
                                    break Label_0080;
                                }
                                break Label_0097;
                            }
                            catch (NumberFormatException ex3) {
                                throw b(ex3);
                            }
                        }
                        try {
                            final char c3 = char1;
                            final char c4 = 'u';
                            if (c3 == c4) {
                                final ParseInfo parseInfo3 = parseInfo;
                                ++parseInfo3.countU;
                                continue;
                            }
                        }
                        catch (NumberFormatException ex4) {
                            throw b(ex4);
                        }
                    }
                    try {
                        if (StringUtil.isHexDigit(char1)) {
                            ++i;
                            break;
                        }
                    }
                    catch (NumberFormatException ex5) {
                        throw b(ex5);
                    }
                }
            }
            substring = substring.substring(0, i);
            int n = 0;
            try {
                if (substring.length() == 0) {
                    throw new NumberFormatException("Zero length string");
                }
            }
            catch (NumberFormatException ex6) {
                throw b(ex6);
            }
            final char char2 = substring.charAt(0);
            Label_0351: {
                Label_0324: {
                    Label_0308: {
                        Label_0281: {
                            Label_0266: {
                                Label_0239: {
                                    Label_0223: {
                                        Label_0196: {
                                            try {
                                                if (char2 == '-') {
                                                    parseInfo.negative = true;
                                                    ++n;
                                                    break Label_0196;
                                                }
                                            }
                                            catch (NumberFormatException ex7) {
                                                throw b(ex7);
                                            }
                                            try {
                                                if (char2 == '+') {
                                                    ++n;
                                                }
                                            }
                                            catch (NumberFormatException ex8) {
                                                throw b(ex8);
                                            }
                                            try {
                                                if (substring.startsWith("0x", n)) {
                                                    break Label_0223;
                                                }
                                                final String s = substring;
                                                final String s2 = "0X";
                                                final int n2 = n;
                                                final boolean b = s.startsWith(s2, n2);
                                                if (b) {
                                                    break Label_0223;
                                                }
                                                break Label_0239;
                                            }
                                            catch (NumberFormatException ex9) {
                                                throw b(ex9);
                                            }
                                        }
                                        try {
                                            final String s = substring;
                                            final String s2 = "0X";
                                            final int n2 = n;
                                            final boolean b = s.startsWith(s2, n2);
                                            if (b) {
                                                n += 2;
                                                parseInfo.radix = 16;
                                                break Label_0324;
                                            }
                                        }
                                        catch (NumberFormatException ex10) {
                                            throw b(ex10);
                                        }
                                    }
                                    try {
                                        if (substring.startsWith("0b", n)) {
                                            break Label_0266;
                                        }
                                        final String s3 = substring;
                                        final String s4 = "0B";
                                        final int n3 = n;
                                        final boolean b2 = s3.startsWith(s4, n3);
                                        if (b2) {
                                            break Label_0266;
                                        }
                                        break Label_0281;
                                    }
                                    catch (NumberFormatException ex11) {
                                        throw b(ex11);
                                    }
                                }
                                try {
                                    final String s3 = substring;
                                    final String s4 = "0B";
                                    final int n3 = n;
                                    final boolean b2 = s3.startsWith(s4, n3);
                                    if (b2) {
                                        n += 2;
                                        parseInfo.radix = 2;
                                        break Label_0324;
                                    }
                                }
                                catch (NumberFormatException ex12) {
                                    throw b(ex12);
                                }
                            }
                            try {
                                if (!substring.startsWith("0", n)) {
                                    break Label_0324;
                                }
                                final String s5 = substring;
                                final int n4 = s5.length();
                                final int n5 = 1;
                                final int n6 = n;
                                final int n7 = n5 + n6;
                                if (n4 > n7) {
                                    break Label_0308;
                                }
                                break Label_0324;
                            }
                            catch (NumberFormatException ex13) {
                                throw b(ex13);
                            }
                        }
                        try {
                            final String s5 = substring;
                            final int n4 = s5.length();
                            final int n5 = 1;
                            final int n6 = n;
                            final int n7 = n5 + n6;
                            if (n4 > n7) {
                                ++n;
                                parseInfo.radix = 8;
                            }
                        }
                        catch (NumberFormatException ex14) {
                            throw b(ex14);
                        }
                    }
                    try {
                        if (substring.startsWith("-", n)) {
                            break Label_0351;
                        }
                        final String s6 = substring;
                        final String s7 = "+";
                        final int n8 = n;
                        final boolean b3 = s6.startsWith(s7, n8);
                        if (b3) {
                            break Label_0351;
                        }
                        break Label_0351;
                    }
                    catch (NumberFormatException ex15) {
                        throw b(ex15);
                    }
                }
                try {
                    final String s6 = substring;
                    final String s7 = "+";
                    final int n8 = n;
                    final boolean b3 = s6.startsWith(s7, n8);
                    if (b3) {
                        throw new NumberFormatException("Sign character in wrong position");
                    }
                }
                catch (NumberFormatException ex16) {
                    throw b(ex16);
                }
            }
            parseInfo.numbers = StringUtil.replace(substring.substring(n), "'", "");
            return parseInfo;
        }
        
        private static NumberFormatException b(final NumberFormatException ex) {
            return ex;
        }
    }
}
