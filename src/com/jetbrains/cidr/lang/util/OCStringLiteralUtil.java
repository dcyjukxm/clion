// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.types.OCIntType;
import com.intellij.openapi.vfs.CharsetToolkit;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import com.jetbrains.cidr.lang.lexer.OCRawStringLexerBase;
import com.jetbrains.cidr.lang.lexer.OCHighlightingLexer;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.Map;
import com.intellij.openapi.diagnostic.Logger;

public class OCStringLiteralUtil
{
    private static final Logger LOG;
    static final Map<String, OCStringPrefix> STR_PREFIX_TYPE;
    static final Map<String, OCCharType> CHAR_PREFIX_TYPE;
    
    public static boolean isStringLiteralPrefix(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil", "isStringLiteralPrefix"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return OCStringLiteralUtil.STR_PREFIX_TYPE.containsKey(s);
    }
    
    @NotNull
    public static OCStringLiteral concatStrings(@NotNull final OCStringLiteral... p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "stringLiterals"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCStringLiteralUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "concatStrings"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //    43: athrow         
        //    44: getstatic       com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.PREFIX_EMPTY:Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix;
        //    47: astore_1       
        //    48: new             Ljava/lang/StringBuilder;
        //    51: dup            
        //    52: invokespecial   java/lang/StringBuilder.<init>:()V
        //    55: astore_2       
        //    56: aload_0        
        //    57: astore_3       
        //    58: aload_3        
        //    59: arraylength    
        //    60: istore          4
        //    62: iconst_0       
        //    63: istore          5
        //    65: iload           5
        //    67: iload           4
        //    69: if_icmpge       358
        //    72: aload_3        
        //    73: iload           5
        //    75: aaload         
        //    76: astore          6
        //    78: aload_1        
        //    79: getfield        com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.encoding:Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding;
        //    82: aload           6
        //    84: getfield        com/jetbrains/cidr/lang/util/OCStringLiteral.prefix:Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix;
        //    87: getfield        com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.encoding:Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding;
        //    90: if_acmpeq       131
        //    93: aload_1        
        //    94: getfield        com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.encoding:Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding;
        //    97: getstatic       com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding.NONE:Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding;
        //   100: if_acmpeq       131
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   109: athrow         
        //   110: aload           6
        //   112: getfield        com/jetbrains/cidr/lang/util/OCStringLiteral.prefix:Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix;
        //   115: getfield        com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.encoding:Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding;
        //   118: getstatic       com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding.NONE:Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding;
        //   121: if_acmpne       274
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   130: athrow         
        //   131: aload_1        
        //   132: getfield        com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.encoding:Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding;
        //   135: getstatic       com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding.NONE:Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding;
        //   138: if_acmpeq       156
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   147: athrow         
        //   148: aload_1        
        //   149: goto            161
        //   152: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   155: athrow         
        //   156: aload           6
        //   158: getfield        com/jetbrains/cidr/lang/util/OCStringLiteral.prefix:Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix;
        //   161: astore          7
        //   163: aload_1        
        //   164: getfield        com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.isRaw:Z
        //   167: ifne            188
        //   170: aload           6
        //   172: getfield        com/jetbrains/cidr/lang/util/OCStringLiteral.prefix:Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix;
        //   175: getfield        com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.isRaw:Z
        //   178: ifeq            196
        //   181: goto            188
        //   184: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   187: athrow         
        //   188: aload           7
        //   190: iconst_1       
        //   191: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.setRaw:(Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix;Z)Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix;
        //   194: astore          7
        //   196: aload_1        
        //   197: getfield        com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.isRaw:Z
        //   200: aload           7
        //   202: getfield        com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.isRaw:Z
        //   205: if_icmpeq       247
        //   208: aload_2        
        //   209: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   212: astore          9
        //   214: new             Ljava/lang/StringBuilder;
        //   217: dup            
        //   218: aload           7
        //   220: getfield        com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.isRaw:Z
        //   223: ifeq            238
        //   226: aload           9
        //   228: invokestatic    com/intellij/openapi/util/text/StringUtil.unescapeStringCharacters:(Ljava/lang/String;)Ljava/lang/String;
        //   231: goto            243
        //   234: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   237: athrow         
        //   238: aload           9
        //   240: invokestatic    com/intellij/openapi/util/text/StringUtil.escapeStringCharacters:(Ljava/lang/String;)Ljava/lang/String;
        //   243: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   246: astore_2       
        //   247: aload           6
        //   249: aload           7
        //   251: getfield        com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.isRaw:Z
        //   254: ifne            265
        //   257: iconst_1       
        //   258: goto            266
        //   261: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   264: athrow         
        //   265: iconst_0       
        //   266: invokevirtual   com/jetbrains/cidr/lang/util/OCStringLiteral.getContents:(Z)Ljava/lang/String;
        //   269: astore          8
        //   271: goto            342
        //   274: aload_1        
        //   275: iconst_0       
        //   276: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.setRaw:(Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix;Z)Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix;
        //   279: astore          7
        //   281: aload           7
        //   283: getfield        com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.encoding:Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding;
        //   286: aload           6
        //   288: getfield        com/jetbrains/cidr/lang/util/OCStringLiteral.prefix:Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix;
        //   291: getfield        com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.encoding:Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding;
        //   294: aload           6
        //   296: iconst_0       
        //   297: invokevirtual   com/jetbrains/cidr/lang/util/OCStringLiteral.getContents:(Z)Ljava/lang/String;
        //   300: invokevirtual   com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding.getBytes:(Ljava/lang/String;)[B
        //   303: invokevirtual   com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding.getString:([B)Ljava/lang/String;
        //   306: astore          9
        //   308: aload_1        
        //   309: getfield        com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.isRaw:Z
        //   312: aload           7
        //   314: getfield        com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix.isRaw:Z
        //   317: if_icmpeq       335
        //   320: new             Ljava/lang/StringBuilder;
        //   323: dup            
        //   324: aload_2        
        //   325: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   328: invokestatic    com/intellij/openapi/util/text/StringUtil.escapeStringCharacters:(Ljava/lang/String;)Ljava/lang/String;
        //   331: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   334: astore_2       
        //   335: aload           9
        //   337: invokestatic    com/intellij/openapi/util/text/StringUtil.escapeStringCharacters:(Ljava/lang/String;)Ljava/lang/String;
        //   340: astore          8
        //   342: aload_2        
        //   343: aload           8
        //   345: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   348: pop            
        //   349: aload           7
        //   351: astore_1       
        //   352: iinc            5, 1
        //   355: goto            65
        //   358: new             Lcom/jetbrains/cidr/lang/util/OCStringLiteral;
        //   361: dup            
        //   362: aload_1        
        //   363: aload_2        
        //   364: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   367: invokespecial   com/jetbrains/cidr/lang/util/OCStringLiteral.<init>:(Lcom/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix;Ljava/lang/String;)V
        //   370: dup            
        //   371: ifnonnull       408
        //   374: new             Ljava/lang/IllegalStateException;
        //   377: dup            
        //   378: ldc             "@NotNull method %s.%s must not return null"
        //   380: ldc             2
        //   382: anewarray       Ljava/lang/Object;
        //   385: dup            
        //   386: ldc             0
        //   388: ldc             "com/jetbrains/cidr/lang/util/OCStringLiteralUtil"
        //   390: aastore        
        //   391: dup            
        //   392: ldc             1
        //   394: ldc             "concatStrings"
        //   396: aastore        
        //   397: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   400: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   403: athrow         
        //   404: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   407: athrow         
        //   408: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/NumberFormatException;
        //  78     103    106    110    Ljava/lang/NumberFormatException;
        //  93     124    127    131    Ljava/lang/NumberFormatException;
        //  110    141    144    148    Ljava/lang/NumberFormatException;
        //  131    152    152    156    Ljava/lang/NumberFormatException;
        //  163    181    184    188    Ljava/lang/NumberFormatException;
        //  214    234    234    238    Ljava/lang/NumberFormatException;
        //  247    261    261    265    Ljava/lang/NumberFormatException;
        //  358    404    404    408    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0110:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @NotNull
    public static OCStringLiteral parseStringLiteral(@Nullable final String s) {
        Label_0053: {
            OCStringLiteral ocStringLiteral = null;
            Label_0018: {
                try {
                    if (s != null) {
                        break Label_0053;
                    }
                    ocStringLiteral = OCStringLiteral.BAD_LITERAL;
                    if (ocStringLiteral == null) {
                        break Label_0018;
                    }
                    return ocStringLiteral;
                }
                catch (NumberFormatException ex) {
                    throw b(ex);
                }
                try {
                    ocStringLiteral = OCStringLiteral.BAD_LITERAL;
                    if (ocStringLiteral == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil", "parseStringLiteral"));
                    }
                }
                catch (NumberFormatException ex2) {
                    throw b(ex2);
                }
            }
            return ocStringLiteral;
        }
        final int length = s.length();
        Label_0111: {
            OCStringLiteral ocStringLiteral2 = null;
            Label_0076: {
                try {
                    if (length != 0) {
                        break Label_0111;
                    }
                    ocStringLiteral2 = OCStringLiteral.BAD_LITERAL;
                    if (ocStringLiteral2 == null) {
                        break Label_0076;
                    }
                    return ocStringLiteral2;
                }
                catch (NumberFormatException ex3) {
                    throw b(ex3);
                }
                try {
                    ocStringLiteral2 = OCStringLiteral.BAD_LITERAL;
                    if (ocStringLiteral2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil", "parseStringLiteral"));
                    }
                }
                catch (NumberFormatException ex4) {
                    throw b(ex4);
                }
            }
            return ocStringLiteral2;
        }
        final int index = s.indexOf(34);
        Label_0183: {
            OCStringLiteral ocStringLiteral3 = null;
            Label_0148: {
                try {
                    if (index != -1) {
                        break Label_0183;
                    }
                    final Logger logger = OCStringLiteralUtil.LOG;
                    final String s2 = "String literal without quote (lexer problem?)";
                    logger.warn(s2);
                    ocStringLiteral3 = OCStringLiteral.BAD_LITERAL;
                    if (ocStringLiteral3 == null) {
                        break Label_0148;
                    }
                    return ocStringLiteral3;
                }
                catch (NumberFormatException ex5) {
                    throw b(ex5);
                }
                try {
                    final Logger logger = OCStringLiteralUtil.LOG;
                    final String s2 = "String literal without quote (lexer problem?)";
                    logger.warn(s2);
                    ocStringLiteral3 = OCStringLiteral.BAD_LITERAL;
                    if (ocStringLiteral3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil", "parseStringLiteral"));
                    }
                }
                catch (NumberFormatException ex6) {
                    throw b(ex6);
                }
            }
            return ocStringLiteral3;
        }
        final OCStringPrefix ocStringPrefix = OCStringLiteralUtil.STR_PREFIX_TYPE.get(s.substring(0, index));
        Label_0260: {
            OCStringLiteral ocStringLiteral4 = null;
            Label_0225: {
                try {
                    if (ocStringPrefix != null) {
                        break Label_0260;
                    }
                    ocStringLiteral4 = OCStringLiteral.BAD_LITERAL;
                    if (ocStringLiteral4 == null) {
                        break Label_0225;
                    }
                    return ocStringLiteral4;
                }
                catch (NumberFormatException ex7) {
                    throw b(ex7);
                }
                try {
                    ocStringLiteral4 = OCStringLiteral.BAD_LITERAL;
                    if (ocStringLiteral4 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil", "parseStringLiteral"));
                    }
                }
                catch (NumberFormatException ex8) {
                    throw b(ex8);
                }
            }
            return ocStringLiteral4;
        }
        String s3;
        if (index == length - 1) {
            s3 = "";
        }
        else {
            s3 = s.substring(index + 1);
            if (ocStringPrefix.isRaw) {
                final OCRawStringLexerBase rawStringLexer = OCHighlightingLexer.createRawStringLexer();
                rawStringLexer.start((CharSequence)s, index, length);
                s3 = rawStringLexer.getRawText();
            }
            else if (s.charAt(length - 1) == '\"') {
                s3 = s3.substring(0, s3.length() - 1);
            }
        }
        OCStringLiteral ocStringLiteral5;
        try {
            ocStringLiteral5 = new OCStringLiteral(ocStringPrefix, s3);
            if (ocStringLiteral5 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil", "parseStringLiteral"));
            }
        }
        catch (NumberFormatException ex9) {
            throw b(ex9);
        }
        return ocStringLiteral5;
    }
    
    @NotNull
    public static OCCharLiteral parseCharLiteral(@Nullable final String s) {
        Label_0053: {
            OCCharLiteral ocCharLiteral = null;
            Label_0018: {
                try {
                    if (s != null) {
                        break Label_0053;
                    }
                    ocCharLiteral = OCCharLiteral.BAD_LITERAL;
                    if (ocCharLiteral == null) {
                        break Label_0018;
                    }
                    return ocCharLiteral;
                }
                catch (NumberFormatException ex) {
                    throw b(ex);
                }
                try {
                    ocCharLiteral = OCCharLiteral.BAD_LITERAL;
                    if (ocCharLiteral == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil", "parseCharLiteral"));
                    }
                }
                catch (NumberFormatException ex2) {
                    throw b(ex2);
                }
            }
            return ocCharLiteral;
        }
        final int length = s.length();
        Label_0111: {
            OCCharLiteral ocCharLiteral2 = null;
            Label_0076: {
                try {
                    if (length != 0) {
                        break Label_0111;
                    }
                    ocCharLiteral2 = OCCharLiteral.BAD_LITERAL;
                    if (ocCharLiteral2 == null) {
                        break Label_0076;
                    }
                    return ocCharLiteral2;
                }
                catch (NumberFormatException ex3) {
                    throw b(ex3);
                }
                try {
                    ocCharLiteral2 = OCCharLiteral.BAD_LITERAL;
                    if (ocCharLiteral2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil", "parseCharLiteral"));
                    }
                }
                catch (NumberFormatException ex4) {
                    throw b(ex4);
                }
            }
            return ocCharLiteral2;
        }
        final int index = s.indexOf(39);
        Label_0183: {
            OCCharLiteral ocCharLiteral3 = null;
            Label_0148: {
                try {
                    if (index != -1) {
                        break Label_0183;
                    }
                    final Logger logger = OCStringLiteralUtil.LOG;
                    final String s2 = "String literal without quote (lexer problem?)";
                    logger.warn(s2);
                    ocCharLiteral3 = OCCharLiteral.BAD_LITERAL;
                    if (ocCharLiteral3 == null) {
                        break Label_0148;
                    }
                    return ocCharLiteral3;
                }
                catch (NumberFormatException ex5) {
                    throw b(ex5);
                }
                try {
                    final Logger logger = OCStringLiteralUtil.LOG;
                    final String s2 = "String literal without quote (lexer problem?)";
                    logger.warn(s2);
                    ocCharLiteral3 = OCCharLiteral.BAD_LITERAL;
                    if (ocCharLiteral3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil", "parseCharLiteral"));
                    }
                }
                catch (NumberFormatException ex6) {
                    throw b(ex6);
                }
            }
            return ocCharLiteral3;
        }
        final String substring = s.substring(0, index);
        OCCharType multichar_LITERAL = OCStringLiteralUtil.CHAR_PREFIX_TYPE.get(substring);
        Label_0260: {
            OCCharLiteral ocCharLiteral4 = null;
            Label_0225: {
                try {
                    if (multichar_LITERAL != null) {
                        break Label_0260;
                    }
                    ocCharLiteral4 = OCCharLiteral.BAD_LITERAL;
                    if (ocCharLiteral4 == null) {
                        break Label_0225;
                    }
                    return ocCharLiteral4;
                }
                catch (NumberFormatException ex7) {
                    throw b(ex7);
                }
                try {
                    ocCharLiteral4 = OCCharLiteral.BAD_LITERAL;
                    if (ocCharLiteral4 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil", "parseCharLiteral"));
                    }
                }
                catch (NumberFormatException ex8) {
                    throw b(ex8);
                }
            }
            return ocCharLiteral4;
        }
        String s3;
        if (index == length - 1) {
            s3 = "";
        }
        else {
            s3 = s.substring(index + 1);
            if (s.charAt(length - 1) == '\'') {
                s3 = s3.substring(0, s3.length() - 1);
            }
        }
        OCCharLiteral ocCharLiteral5 = null;
        Label_0339: {
            try {
                if (!substring.isEmpty() || s3.length() <= 1) {
                    break Label_0339;
                }
            }
            catch (NumberFormatException ex9) {
                throw b(ex9);
            }
            multichar_LITERAL = OCCharType.MULTICHAR_LITERAL;
            try {
                ocCharLiteral5 = new OCCharLiteral(multichar_LITERAL, substring, s3);
                if (ocCharLiteral5 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil", "parseCharLiteral"));
                }
            }
            catch (NumberFormatException ex10) {
                throw b(ex10);
            }
        }
        return ocCharLiteral5;
    }
    
    @NotNull
    public static String unescapeAnsiStringCharacters(@NotNull final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "s"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCStringLiteralUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "unescapeAnsiStringCharacters"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //    43: athrow         
        //    44: new             Ljava/lang/StringBuilder;
        //    47: dup            
        //    48: invokespecial   java/lang/StringBuilder.<init>:()V
        //    51: astore_1       
        //    52: aload_0        
        //    53: invokevirtual   java/lang/String.length:()I
        //    56: istore_2       
        //    57: iconst_0       
        //    58: istore_3       
        //    59: iconst_0       
        //    60: istore          4
        //    62: iconst_0       
        //    63: istore          5
        //    65: iconst_0       
        //    66: istore          6
        //    68: iconst_0       
        //    69: istore          7
        //    71: iconst_0       
        //    72: istore          8
        //    74: iload           8
        //    76: iload_2        
        //    77: if_icmpge       880
        //    80: aload_0        
        //    81: iload           8
        //    83: invokevirtual   java/lang/String.charAt:(I)C
        //    86: istore          9
        //    88: iload           7
        //    90: ifne            123
        //    93: iload           9
        //    95: bipush          92
        //    97: if_icmpne       113
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   106: athrow         
        //   107: iconst_1       
        //   108: istore          7
        //   110: goto            874
        //   113: aload_1        
        //   114: iload           9
        //   116: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   119: pop            
        //   120: goto            874
        //   123: iload           9
        //   125: tableswitch {
        //               68: 502
        //               69: 663
        //               70: 663
        //               71: 663
        //               72: 663
        //               73: 488
        //               74: 663
        //               75: 663
        //               76: 663
        //               77: 663
        //               78: 663
        //               79: 663
        //               80: 663
        //               81: 663
        //               82: 602
        //               83: 602
        //               84: 602
        //               85: 602
        //               86: 602
        //               87: 602
        //               88: 602
        //               89: 602
        //               90: 663
        //               91: 663
        //               92: 663
        //               93: 663
        //               94: 663
        //               95: 663
        //               96: 663
        //               97: 512
        //               98: 663
        //               99: 663
        //              100: 663
        //              101: 663
        //              102: 663
        //              103: 663
        //              104: 663
        //              105: 663
        //              106: 663
        //              107: 663
        //              108: 663
        //              109: 663
        //              110: 663
        //              111: 663
        //              112: 663
        //              113: 663
        //              114: 663
        //              115: 663
        //              116: 663
        //              117: 663
        //              118: 663
        //              119: 647
        //              120: 663
        //              121: 663
        //              122: 663
        //              123: 663
        //              124: 663
        //              125: 663
        //              126: 522
        //              127: 663
        //              128: 663
        //              129: 663
        //              130: 663
        //              131: 532
        //              132: 542
        //              133: 663
        //              134: 663
        //              135: 663
        //              136: 552
        //              137: 663
        //              138: 663
        //              139: 663
        //              140: 663
        //              141: 663
        //              142: 663
        //              143: 663
        //              144: 562
        //              145: 663
        //              146: 663
        //              147: 663
        //              148: 572
        //              149: 663
        //              150: 582
        //              151: 632
        //              152: 592
        //              153: 663
        //              154: 617
        //          default: 663
        //        }
        //   488: aload_1        
        //   489: bipush          39
        //   491: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   494: pop            
        //   495: goto            670
        //   498: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   501: athrow         
        //   502: aload_1        
        //   503: bipush          34
        //   505: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   508: pop            
        //   509: goto            670
        //   512: aload_1        
        //   513: bipush          63
        //   515: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   518: pop            
        //   519: goto            670
        //   522: aload_1        
        //   523: bipush          92
        //   525: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   528: pop            
        //   529: goto            670
        //   532: aload_1        
        //   533: bipush          7
        //   535: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   538: pop            
        //   539: goto            670
        //   542: aload_1        
        //   543: bipush          8
        //   545: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   548: pop            
        //   549: goto            670
        //   552: aload_1        
        //   553: bipush          12
        //   555: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   558: pop            
        //   559: goto            670
        //   562: aload_1        
        //   563: bipush          10
        //   565: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   568: pop            
        //   569: goto            670
        //   572: aload_1        
        //   573: bipush          13
        //   575: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   578: pop            
        //   579: goto            670
        //   582: aload_1        
        //   583: bipush          9
        //   585: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   588: pop            
        //   589: goto            670
        //   592: aload_1        
        //   593: bipush          11
        //   595: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   598: pop            
        //   599: goto            670
        //   602: iconst_3       
        //   603: istore_3       
        //   604: bipush          8
        //   606: istore          4
        //   608: iconst_0       
        //   609: istore          5
        //   611: iconst_1       
        //   612: istore          6
        //   614: goto            670
        //   617: iconst_2       
        //   618: istore_3       
        //   619: bipush          16
        //   621: istore          4
        //   623: iconst_1       
        //   624: istore          5
        //   626: iconst_1       
        //   627: istore          6
        //   629: goto            670
        //   632: iconst_4       
        //   633: istore_3       
        //   634: bipush          16
        //   636: istore          4
        //   638: iconst_1       
        //   639: istore          5
        //   641: iconst_1       
        //   642: istore          6
        //   644: goto            670
        //   647: bipush          8
        //   649: istore_3       
        //   650: bipush          16
        //   652: istore          4
        //   654: iconst_1       
        //   655: istore          5
        //   657: iconst_1       
        //   658: istore          6
        //   660: goto            670
        //   663: aload_1        
        //   664: iload           9
        //   666: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   669: pop            
        //   670: iload           6
        //   672: ifeq            871
        //   675: iconst_0       
        //   676: istore          6
        //   678: new             Ljava/lang/StringBuilder;
        //   681: dup            
        //   682: iload_3        
        //   683: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   686: astore          10
        //   688: iload           8
        //   690: iload           5
        //   692: iadd           
        //   693: istore          11
        //   695: iload           11
        //   697: iload_2        
        //   698: if_icmpge       795
        //   701: iload_3        
        //   702: ifle            795
        //   705: goto            712
        //   708: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   711: athrow         
        //   712: aload_0        
        //   713: iload           11
        //   715: invokevirtual   java/lang/String.charAt:(I)C
        //   718: istore          12
        //   720: iload           4
        //   722: bipush          16
        //   724: if_icmpne       742
        //   727: iload           12
        //   729: invokestatic    com/intellij/openapi/util/text/StringUtil.isHexDigit:(C)Z
        //   732: ifne            778
        //   735: goto            742
        //   738: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   741: athrow         
        //   742: iload           4
        //   744: bipush          8
        //   746: if_icmpne       795
        //   749: goto            756
        //   752: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   755: athrow         
        //   756: iload           12
        //   758: invokestatic    com/intellij/openapi/util/text/StringUtil.isOctalDigit:(C)Z
        //   761: ifne            778
        //   764: goto            771
        //   767: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   770: athrow         
        //   771: goto            795
        //   774: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   777: athrow         
        //   778: aload           10
        //   780: iload           12
        //   782: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   785: pop            
        //   786: iinc            3, -1
        //   789: iinc            11, 1
        //   792: goto            695
        //   795: aload           10
        //   797: invokevirtual   java/lang/StringBuilder.length:()I
        //   800: ifeq            859
        //   803: aload           10
        //   805: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   808: iload           4
        //   810: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;I)J
        //   813: lstore          11
        //   815: iload           8
        //   817: aload           10
        //   819: invokevirtual   java/lang/StringBuilder.length:()I
        //   822: iload           5
        //   824: iadd           
        //   825: iconst_1       
        //   826: isub           
        //   827: iadd           
        //   828: istore          8
        //   830: aload_1        
        //   831: lload           11
        //   833: l2i            
        //   834: i2c            
        //   835: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   838: pop            
        //   839: goto            871
        //   842: astore          11
        //   844: aload_1        
        //   845: bipush          92
        //   847: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   850: iload           9
        //   852: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   855: pop            
        //   856: goto            871
        //   859: aload_1        
        //   860: bipush          92
        //   862: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   865: iload           9
        //   867: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   870: pop            
        //   871: iconst_0       
        //   872: istore          7
        //   874: iinc            8, 1
        //   877: goto            74
        //   880: iload           7
        //   882: ifeq            899
        //   885: aload_1        
        //   886: bipush          92
        //   888: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   891: pop            
        //   892: goto            899
        //   895: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   898: athrow         
        //   899: aload_1        
        //   900: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   903: dup            
        //   904: ifnonnull       941
        //   907: new             Ljava/lang/IllegalStateException;
        //   910: dup            
        //   911: ldc             "@NotNull method %s.%s must not return null"
        //   913: ldc             2
        //   915: anewarray       Ljava/lang/Object;
        //   918: dup            
        //   919: ldc             0
        //   921: ldc             "com/jetbrains/cidr/lang/util/OCStringLiteralUtil"
        //   923: aastore        
        //   924: dup            
        //   925: ldc             1
        //   927: ldc             "unescapeAnsiStringCharacters"
        //   929: aastore        
        //   930: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   933: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   936: athrow         
        //   937: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   940: athrow         
        //   941: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  756    774    774    778    Ljava/lang/NumberFormatException;
        //  742    764    767    771    Ljava/lang/NumberFormatException;
        //  727    749    752    756    Ljava/lang/NumberFormatException;
        //  720    735    738    742    Ljava/lang/NumberFormatException;
        //  695    705    708    712    Ljava/lang/NumberFormatException;
        //  123    498    498    502    Ljava/lang/NumberFormatException;
        //  88     100    103    107    Ljava/lang/NumberFormatException;
        //  0      40     40     44     Ljava/lang/NumberFormatException;
        //  803    839    842    859    Ljava/lang/NumberFormatException;
        //  880    892    895    899    Ljava/lang/NumberFormatException;
        //  899    937    937    941    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0742:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        LOG = Logger.getInstance("#com.jetbrains.cidr.lang.util.OCStringLiteralUtil");
        STR_PREFIX_TYPE = new HashMap<String, OCStringPrefix>(OCStringPrefix.values().length, 1.0f);
        CHAR_PREFIX_TYPE = new HashMap<String, OCCharType>(4, 1.0f);
        for (final OCStringPrefix ocStringPrefix : OCStringPrefix.values()) {
            OCStringLiteralUtil.STR_PREFIX_TYPE.put(ocStringPrefix.prefix, ocStringPrefix);
        }
        OCStringLiteralUtil.CHAR_PREFIX_TYPE.put("", OCCharType.CHAR_LITERAL);
        OCStringLiteralUtil.CHAR_PREFIX_TYPE.put("L", OCCharType.WCHAR_LITERAL);
        OCStringLiteralUtil.CHAR_PREFIX_TYPE.put("u", OCCharType.CHAR16_LITERAL);
        OCStringLiteralUtil.CHAR_PREFIX_TYPE.put("U", OCCharType.CHAR32_LITERAL);
    }
    
    private static NumberFormatException b(final NumberFormatException ex) {
        return ex;
    }
    
    public enum OCEncoding
    {
        NONE(Charset.forName("US-ASCII")), 
        WCHAR(CharsetToolkit.UTF_16LE_CHARSET), 
        UTF8(CharsetToolkit.UTF8_CHARSET), 
        UTF16(CharsetToolkit.UTF_16LE_CHARSET), 
        UTF32(CharsetToolkit.UTF_32LE_CHARSET);
        
        @NotNull
        private final Charset charset;
        
        private OCEncoding(final Charset charset) {
            if (charset == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "charset", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCEncoding", "<init>"));
            }
            super(s, n);
            this.charset = charset;
        }
        
        public byte[] getBytes(final String s) {
            return s.getBytes(this.charset);
        }
        
        public String getString(final byte[] array) {
            return this.charset.decode(ByteBuffer.wrap(array)).toString();
        }
    }
    
    public enum OCStringPrefix
    {
        PREFIX_EMPTY("", false, OCEncoding.NONE, OCIntType.CHAR, OCIntType.CHAR_CONST), 
        PREFIX_R("R", true, OCEncoding.NONE, OCIntType.CHAR, OCIntType.CHAR_CONST), 
        PREFIX_L("L", false, OCEncoding.WCHAR, OCIntType.WCHAR, OCIntType.WCHAR_CONST), 
        PREFIX_LR("LR", true, OCEncoding.WCHAR, OCIntType.WCHAR, OCIntType.WCHAR_CONST), 
        PREFIX_u8("u8", false, OCEncoding.UTF8, OCIntType.CHAR, OCIntType.CHAR_CONST), 
        PREFIX_u8R("u8R", true, OCEncoding.UTF8, OCIntType.CHAR, OCIntType.CHAR_CONST), 
        PREFIX_u("u", false, OCEncoding.UTF16, OCIntType.CHAR16, OCIntType.CHAR16_CONST), 
        PREFIX_uR("uR", true, OCEncoding.UTF16, OCIntType.CHAR16, OCIntType.CHAR16_CONST), 
        PREFIX_U("U", false, OCEncoding.UTF32, OCIntType.CHAR32, OCIntType.CHAR32_CONST), 
        PREFIX_UR("UR", true, OCEncoding.UTF32, OCIntType.CHAR32, OCIntType.CHAR32_CONST);
        
        @NotNull
        public final String prefix;
        public final boolean isRaw;
        @NotNull
        public final OCEncoding encoding;
        @NotNull
        public final OCIntType charTypePlainC;
        @NotNull
        public final OCIntType charTypeCpp;
        
        private OCStringPrefix(@NotNull final String prefix, @NotNull final boolean isRaw, @NotNull final OCEncoding encoding, final OCIntType charTypePlainC, final OCIntType charTypeCpp) {
            if (prefix == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix", "<init>"));
            }
            if (encoding == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "encoding", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix", "<init>"));
            }
            if (charTypePlainC == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "charTypePlainC", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix", "<init>"));
            }
            if (charTypeCpp == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "charTypeCpp", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix", "<init>"));
            }
            super(s, n);
            this.prefix = prefix;
            this.isRaw = isRaw;
            this.encoding = encoding;
            this.charTypePlainC = charTypePlainC;
            this.charTypeCpp = charTypeCpp;
        }
        
        public static OCStringPrefix setRaw(final OCStringPrefix ocStringPrefix, final boolean b) {
            Label_0078: {
                Label_0052: {
                    try {
                        if (!b) {
                            break Label_0078;
                        }
                        final int[] array = OCStringLiteralUtil$1.$SwitchMap$com$jetbrains$cidr$lang$util$OCStringLiteralUtil$OCStringPrefix;
                        final OCStringPrefix ocStringPrefix2 = ocStringPrefix;
                        final int n = ocStringPrefix2.ordinal();
                        final int n2 = array[n];
                        switch (n2) {
                            case 1: {
                                break Label_0052;
                                break Label_0052;
                            }
                            case 2: {
                                return OCStringPrefix.PREFIX_LR;
                            }
                            case 3: {
                                return OCStringPrefix.PREFIX_u8R;
                            }
                            case 4: {
                                return OCStringPrefix.PREFIX_uR;
                            }
                            case 5: {
                                return OCStringPrefix.PREFIX_UR;
                            }
                            default: {
                                return ocStringPrefix;
                            }
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final int[] array = OCStringLiteralUtil$1.$SwitchMap$com$jetbrains$cidr$lang$util$OCStringLiteralUtil$OCStringPrefix;
                        final OCStringPrefix ocStringPrefix2 = ocStringPrefix;
                        final int n = ocStringPrefix2.ordinal();
                        final int n2 = array[n];
                        switch (n2) {
                            case 1: {
                                return OCStringPrefix.PREFIX_R;
                            }
                            case 2: {
                                break;
                            }
                            case 3: {
                                return OCStringPrefix.PREFIX_u8R;
                            }
                            case 4: {
                                return OCStringPrefix.PREFIX_uR;
                            }
                            case 5: {
                                return OCStringPrefix.PREFIX_UR;
                            }
                            default: {
                                return ocStringPrefix;
                            }
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return OCStringPrefix.PREFIX_LR;
                try {
                    switch (ocStringPrefix) {
                        case PREFIX_R: {
                            return OCStringPrefix.PREFIX_EMPTY;
                        }
                        case PREFIX_LR: {
                            break;
                        }
                        case PREFIX_u8R: {
                            return OCStringPrefix.PREFIX_u8;
                        }
                        case PREFIX_uR: {
                            return OCStringPrefix.PREFIX_u;
                        }
                        case PREFIX_UR: {
                            return OCStringPrefix.PREFIX_U;
                        }
                        default: {
                            return ocStringPrefix;
                        }
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return OCStringPrefix.PREFIX_L;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public enum OCCharType
    {
        CHAR_LITERAL(OCIntType.INT, OCIntType.CHAR), 
        MULTICHAR_LITERAL(OCIntType.INT), 
        WCHAR_LITERAL(OCIntType.WCHAR), 
        CHAR16_LITERAL(OCIntType.CHAR16), 
        CHAR32_LITERAL(OCIntType.CHAR32);
        
        @NotNull
        public final OCIntType typePlainC;
        @NotNull
        public final OCIntType typeCpp;
        
        private OCCharType(final OCIntType ocIntType) {
            if (ocIntType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCCharType", "<init>"));
            }
            this(ocIntType, ocIntType);
        }
        
        private OCCharType(final OCIntType typePlainC, final OCIntType typeCpp) {
            if (typePlainC == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typePlainC", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCCharType", "<init>"));
            }
            if (typeCpp == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeCpp", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCCharType", "<init>"));
            }
            super(s, n);
            this.typePlainC = typePlainC;
            this.typeCpp = typeCpp;
        }
    }
}
