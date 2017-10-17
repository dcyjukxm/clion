// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.lexer.OCHighlightingLexer;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import java.util.Arrays;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCRealType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.CTypeId;
import com.jetbrains.cidr.lang.types.OCIntType;
import java.util.Iterator;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Contract;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.containers.ContainerUtil;
import java.util.Set;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.Map;
import java.util.regex.Pattern;

public class OCFormatSpecifiersUtil
{
    public static final String POINTER_TYPE_REQUIRED = "<pointer type required>";
    private static final Pattern FORMAT_ATTRIBUTE_PATTERN;
    private static final Map<String, FormatSpecifiersInfo> PREDEFINED_FORMAT_FUNCTIONS;
    
    @NotNull
    private static Pair<OCTypeWrapper, Set<String>> a(@NotNull final OCType ocType, final String... array) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil", "p2s"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Pair pair;
        try {
            pair = Pair.pair((Object)new OCTypeWrapper(ocType), (Object)ContainerUtil.newHashSet((Object[])array));
            if (pair == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil", "p2s"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (Pair<OCTypeWrapper, Set<String>>)pair;
    }
    
    @NotNull
    private static Pair<OCTypeWrapper, Set<String>> a(@NotNull final String s, final boolean b, final String... array) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeName", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil", "p2s"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Pair pair;
        try {
            pair = Pair.pair((Object)new OCTypeWrapper(s, b), (Object)ContainerUtil.newHashSet((Object[])array));
            if (pair == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil", "p2s"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (Pair<OCTypeWrapper, Set<String>>)pair;
    }
    
    @Nullable
    public static Pair<FormatSpecifiersInfo, List<SpecifierUsage>> getFormatSpecifiersInfo(@NotNull final OCSymbol<?> p0, @NotNull final List<OCExpression> p1) {
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
        //    18: ldc             "callable"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getFormatSpecifiersInfo"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "arguments"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getFormatSpecifiersInfo"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aconst_null    
        //    89: astore_2       
        //    90: aload_0        
        //    91: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getAttributes:()Ljava/util/List;
        //    96: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   101: astore_3       
        //   102: aload_3        
        //   103: invokeinterface java/util/Iterator.hasNext:()Z
        //   108: ifeq            197
        //   111: aload_3        
        //   112: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   117: checkcast       Ljava/lang/String;
        //   120: astore          4
        //   122: getstatic       com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.FORMAT_ATTRIBUTE_PATTERN:Ljava/util/regex/Pattern;
        //   125: aload           4
        //   127: invokevirtual   java/util/regex/Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //   130: astore          5
        //   132: aload           5
        //   134: invokevirtual   java/util/regex/Matcher.matches:()Z
        //   137: ifeq            194
        //   140: aload           5
        //   142: iconst_1       
        //   143: invokevirtual   java/util/regex/Matcher.group:(I)Ljava/lang/String;
        //   146: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.getFormatTypeFromName:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
        //   149: astore          6
        //   151: aload           6
        //   153: ifnonnull       162
        //   156: aconst_null    
        //   157: areturn        
        //   158: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   161: athrow         
        //   162: new             Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo;
        //   165: dup            
        //   166: aload           6
        //   168: aload           5
        //   170: iconst_2       
        //   171: invokevirtual   java/util/regex/Matcher.group:(I)Ljava/lang/String;
        //   174: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   177: iconst_1       
        //   178: isub           
        //   179: aload           5
        //   181: iconst_3       
        //   182: invokevirtual   java/util/regex/Matcher.group:(I)Ljava/lang/String;
        //   185: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   188: iconst_1       
        //   189: isub           
        //   190: invokespecial   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.<init>:(Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;II)V
        //   193: astore_2       
        //   194: goto            102
        //   197: aload_2        
        //   198: ifnonnull       219
        //   201: getstatic       com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.PREDEFINED_FORMAT_FUNCTIONS:Ljava/util/Map;
        //   204: aload_0        
        //   205: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   210: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   215: checkcast       Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo;
        //   218: astore_2       
        //   219: aload_2        
        //   220: ifnonnull       229
        //   223: aconst_null    
        //   224: areturn        
        //   225: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   228: athrow         
        //   229: aload_2        
        //   230: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.formatStringIndex:I
        //   233: iflt            274
        //   236: aload_2        
        //   237: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.formatStringIndex:I
        //   240: aload_2        
        //   241: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.argumentsIndex:I
        //   244: if_icmpge       274
        //   247: goto            254
        //   250: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   253: athrow         
        //   254: aload_2        
        //   255: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.formatStringIndex:I
        //   258: aload_1        
        //   259: invokeinterface java/util/List.size:()I
        //   264: if_icmplt       280
        //   267: goto            274
        //   270: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   273: athrow         
        //   274: aconst_null    
        //   275: areturn        
        //   276: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   279: athrow         
        //   280: aload_1        
        //   281: aload_2        
        //   282: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.formatStringIndex:I
        //   285: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   290: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   293: astore_3       
        //   294: aload_2        
        //   295: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.formatType:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
        //   298: aload_3        
        //   299: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.collectFormatSpecifiers:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/util/List;
        //   302: astore          4
        //   304: aload           4
        //   306: ifnonnull       315
        //   309: aconst_null    
        //   310: areturn        
        //   311: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   314: athrow         
        //   315: aload_2        
        //   316: aload           4
        //   318: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   321: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/symbols/OCSymbol<*>;Ljava/util/List<Lcom/jetbrains/cidr/lang/psi/OCExpression;>;)Lcom/intellij/openapi/util/Pair<Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo;Ljava/util/List<Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage;>;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  151    158    158    162    Ljava/lang/IllegalArgumentException;
        //  219    225    225    229    Ljava/lang/IllegalArgumentException;
        //  229    247    250    254    Ljava/lang/IllegalArgumentException;
        //  236    267    270    274    Ljava/lang/IllegalArgumentException;
        //  254    276    276    280    Ljava/lang/IllegalArgumentException;
        //  304    311    311    315    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0254:
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
    
    @Nullable
    public static OCType getFormatArgumentType(@NotNull final OCSymbol p0, final int p1, @NotNull final List<OCExpression> p2) {
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
        //    18: ldc             "callable"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getFormatArgumentType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "arguments"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getFormatArgumentType"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: aload_2        
        //    90: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.getFormatSpecifiersInfo:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/util/List;)Lcom/intellij/openapi/util/Pair;
        //    93: astore_3       
        //    94: aload_3        
        //    95: ifnull          190
        //    98: aload_3        
        //    99: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   102: checkcast       Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo;
        //   105: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.argumentsIndex:I
        //   108: iflt            190
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   117: athrow         
        //   118: iload_1        
        //   119: aload_3        
        //   120: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   123: checkcast       Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo;
        //   126: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.argumentsIndex:I
        //   129: if_icmplt       190
        //   132: goto            139
        //   135: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: iload_1        
        //   140: aload_2        
        //   141: invokeinterface java/util/List.size:()I
        //   146: if_icmpge       190
        //   149: goto            156
        //   152: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   155: athrow         
        //   156: iload_1        
        //   157: aload_3        
        //   158: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   161: checkcast       Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo;
        //   164: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.argumentsIndex:I
        //   167: aload_3        
        //   168: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   171: checkcast       Ljava/util/List;
        //   174: invokeinterface java/util/List.size:()I
        //   179: iadd           
        //   180: if_icmplt       196
        //   183: goto            190
        //   186: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   189: athrow         
        //   190: aconst_null    
        //   191: areturn        
        //   192: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   195: athrow         
        //   196: iload_1        
        //   197: aload_3        
        //   198: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   201: checkcast       Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo;
        //   204: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.argumentsIndex:I
        //   207: isub           
        //   208: istore          4
        //   210: iconst_0       
        //   211: istore          5
        //   213: aload_3        
        //   214: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   217: checkcast       Ljava/util/List;
        //   220: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   225: astore          6
        //   227: aload           6
        //   229: invokeinterface java/util/Iterator.hasNext:()Z
        //   234: ifeq            313
        //   237: aload           6
        //   239: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   244: checkcast       Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage;
        //   247: astore          7
        //   249: aload           7
        //   251: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage.getType:()Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   254: getstatic       com/jetbrains/cidr/lang/editor/colors/OCHighlightingKeys.OC_VALID_STRING_ESCAPE:Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   257: if_acmpne       267
        //   260: goto            227
        //   263: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   266: athrow         
        //   267: iload           5
        //   269: iload           4
        //   271: if_icmpne       307
        //   274: aload_3        
        //   275: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   278: checkcast       Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo;
        //   281: getfield        com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo.formatType:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
        //   284: aload           7
        //   286: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage.getName:()Ljava/lang/String;
        //   289: aload_2        
        //   290: iload_1        
        //   291: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   296: checkcast       Lcom/intellij/psi/PsiElement;
        //   299: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.resolveType:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   302: areturn        
        //   303: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   306: athrow         
        //   307: iinc            5, 1
        //   310: goto            227
        //   313: aconst_null    
        //   314: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/symbols/OCSymbol;ILjava/util/List<Lcom/jetbrains/cidr/lang/psi/OCExpression;>;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  94     111    114    118    Ljava/lang/IllegalArgumentException;
        //  98     132    135    139    Ljava/lang/IllegalArgumentException;
        //  118    149    152    156    Ljava/lang/IllegalArgumentException;
        //  139    183    186    190    Ljava/lang/IllegalArgumentException;
        //  156    192    192    196    Ljava/lang/IllegalArgumentException;
        //  249    263    263    267    Ljava/lang/IllegalArgumentException;
        //  267    303    303    307    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0118:
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
        FORMAT_ATTRIBUTE_PATTERN = Pattern.compile("_{0,2}format_{0,2}#_{0,2}([^_\\s]*)_{0,2}\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)");
        PREDEFINED_FORMAT_FUNCTIONS = ContainerUtil.newHashMap(Pair.pair((Object)"printf", (Object)new FormatSpecifiersInfo(FormatType.PRINTF, 0, 1)), new Pair[] { Pair.pair((Object)"wprintf", (Object)new FormatSpecifiersInfo(FormatType.PRINTF, 0, 1)), Pair.pair((Object)"fprintf", (Object)new FormatSpecifiersInfo(FormatType.PRINTF, 1, 2)), Pair.pair((Object)"fwprintf", (Object)new FormatSpecifiersInfo(FormatType.PRINTF, 1, 2)), Pair.pair((Object)"sprintf", (Object)new FormatSpecifiersInfo(FormatType.PRINTF, 1, 2)), Pair.pair((Object)"swprintf", (Object)new FormatSpecifiersInfo(FormatType.PRINTF, 1, 2)), Pair.pair((Object)"snprintf", (Object)new FormatSpecifiersInfo(FormatType.PRINTF, 2, 3)), Pair.pair((Object)"asprintf", (Object)new FormatSpecifiersInfo(FormatType.PRINTF, 1, 2)), Pair.pair((Object)"scanf", (Object)new FormatSpecifiersInfo(FormatType.SCANF, 0, 1)), Pair.pair((Object)"fscanf", (Object)new FormatSpecifiersInfo(FormatType.SCANF, 1, 2)), Pair.pair((Object)"sscanf", (Object)new FormatSpecifiersInfo(FormatType.SCANF, 1, 2)), Pair.pair((Object)"strftime", (Object)new FormatSpecifiersInfo(FormatType.STRFTIME, 2, 3)), Pair.pair((Object)"wcsftime", (Object)new FormatSpecifiersInfo(FormatType.STRFTIME, 2, 3)), Pair.pair((Object)"put_time", (Object)new FormatSpecifiersInfo(FormatType.STRFTIME, 1, 0)) });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum FormatType
    {
        PRINTF((Collection<Pair<OCTypeWrapper, Set<String>>>)Arrays.asList(a(OCPointerType.to(OCIntType.CHAR), new String[] { "%s", "%hhn" }), a(OCIntType.CHAR, new String[] { "%hhd", "%hhi" }), a(OCIntType.UCHAR, new String[] { "%c", "%hhu", "%hho", "%hhx", "%hhX" }), a(OCPointerType.to(OCIntType.WCHAR), new String[] { "%ls" }), a(OCIntType.WCHAR, new String[] { "%lc" }), a(OCPointerType.to(OCIntType.SHORT), new String[] { "%hn" }), a(OCIntType.SHORT, new String[] { "%hd", "%hi" }), a(OCIntType.USHORT, new String[] { "%hu", "%ho", "%hx", "%hX" }), a(OCPointerType.to(OCIntType.INT), new String[] { "%n" }), a(OCIntType.INT, new String[] { "*", "%d", "%i" }), a(OCIntType.UINT, new String[] { "%u", "%o", "%x", "%X" }), a(OCPointerType.to(OCIntType.LONG), new String[] { "%ln" }), a(OCIntType.LONG, new String[] { "%ld", "%li" }), a(OCIntType.ULONG, new String[] { "%lu", "%lo", "%lx", "%lX" }), a(OCPointerType.to(OCIntType.LONGLONG), new String[] { "%lln" }), a(OCIntType.LONGLONG, new String[] { "%lld", "%lli" }), a(OCIntType.ULONGLONG, new String[] { "%llu", "%llo", "%llx", "%llX" }), a(OCRealType.DOUBLE, new String[] { "%a", "%A", "%e", "%E", "%f", "%F", "%g", "%G", "%la", "%lA", "%le", "%lE", "%lf", "%lF", "%lg", "%lG" }), a(OCRealType.LONG_DOUBLE, new String[] { "%La", "%LA", "%Le", "%LE", "%Lf", "%LF", "%Lg", "%LG" }), a(OCPointerType.to(OCVoidType.instance()), new String[] { "%p" }), a("intmax_t", true, new String[] { "%jn" }), a("intmax_t", false, new String[] { "%jd", "%ji", "%ju", "%jo", "%jx", "%jX" }), a(OCPointerType.to(OCIntType.SIZE_T), new String[] { "%zn" }), a(OCIntType.SSIZE_T, new String[] { "%zd", "%zi" }), a(OCIntType.SIZE_T, new String[] { "%zu", "%zo", "%zx", "%zX" }), a(OCPointerType.to(OCIntType.PTRDIFF_T), new String[] { "%tn" }), a(OCIntType.PTRDIFF_T, new String[] { "%td", "%ti", "%tu", "%to", "%tx", "%tX" }), a("<errno message, no arg>", false, new String[] { "%m" })), true, new String[] { "printf" }), 
        NSSTRING((Collection<Pair<OCTypeWrapper, Set<String>>>)Arrays.asList(a(OCPointerType.to(OCIntType.CHAR), new String[] { "%s", "%hhn" }), a(OCIntType.CHAR, new String[] { "%hhd", "%hhD", "%hhi" }), a(OCIntType.UCHAR, new String[] { "%c", "%hhu", "%hhU", "%hho", "%hhO", "%hhx", "%hhX" }), a(OCPointerType.to(OCIntType.WCHAR), new String[] { "%ls", "%S" }), a(OCIntType.WCHAR, new String[] { "%lc", "%C" }), a(OCPointerType.to(OCIntType.SHORT), new String[] { "%hn" }), a(OCIntType.SHORT, new String[] { "%hd", "%hD", "%hi" }), a(OCIntType.USHORT, new String[] { "%hu", "%hU", "%ho", "%hO", "%hx", "%hX" }), a(OCPointerType.to(OCIntType.INT), new String[] { "%n" }), a(OCIntType.INT, new String[] { "*", "%d", "%D", "%i" }), a(OCIntType.UINT, new String[] { "%u", "%U", "%o", "%O", "%x", "%X" }), a(OCPointerType.to(OCIntType.LONG), new String[] { "%ln" }), a(OCIntType.LONG, new String[] { "%ld", "%lD", "%li" }), a(OCIntType.ULONG, new String[] { "%lu", "%lU", "%lo", "%lO", "%lx", "%lX" }), a(OCPointerType.to(OCIntType.LONGLONG), new String[] { "%lln", "%qn" }), a(OCIntType.LONGLONG, new String[] { "%lld", "%llD", "%lli", "%qd", "%qD", "%qi" }), a(OCIntType.ULONGLONG, new String[] { "%llu", "%llU", "%llo", "%llO", "%llx", "%llX", "%qu", "%qU", "%qo", "%qO", "%qx", "%qX" }), a(OCRealType.DOUBLE, new String[] { "%a", "%A", "%e", "%E", "%f", "%F", "%g", "%G", "%la", "%lA", "%le", "%lE", "%lf", "%lF", "%lg", "%lG" }), a(OCRealType.LONG_DOUBLE, new String[] { "%La", "%LA", "%Le", "%LE", "%Lf", "%LF", "%Lg", "%LG" }), a(OCPointerType.to(OCVoidType.instance()), new String[] { "%p" }), a("intmax_t", true, new String[] { "%jn" }), a("intmax_t", false, new String[] { "%jd", "%jD", "%ji", "%ju", "%jU", "%jo", "%jO", "%jx", "%jX" }), a(OCPointerType.to(OCIntType.SIZE_T), new String[] { "%zn" }), a(OCIntType.SSIZE_T, new String[] { "%zd", "%zD", "%zi" }), a(OCIntType.SIZE_T, new String[] { "%zu", "%zU", "%zo", "%zO", "%zx", "%zX" }), a(OCPointerType.to(OCIntType.PTRDIFF_T), new String[] { "%tn" }), a(OCIntType.PTRDIFF_T, new String[] { "%td", "%tD", "%ti", "%tu", "%tU", "%to", "%tO", "%tx", "%tX" }), a("<errno message, no arg>", false, new String[] { "%m" }), a("NSObject", true, new String[] { "%@" })), true, new String[] { "NSString" }), 
        SCANF((Collection<Pair<OCTypeWrapper, Set<String>>>)Arrays.asList(a("<skip input>", false, new String[] { "*" }), a(OCPointerType.to(OCIntType.CHAR), new String[] { "%c", "%hhd", "%hhi", "%hhu", "%hho", "%hhx", "%hhX", "%hhn", "%s", "%[" }), a(OCPointerType.to(OCIntType.WCHAR), new String[] { "%lc", "%ls", "%l[" }), a(OCPointerType.to(OCIntType.SHORT), new String[] { "%hd", "%hi", "%hu", "%ho", "%hx", "%hX", "%hn" }), a(OCPointerType.to(OCIntType.INT), new String[] { "%d", "%i", "%u", "%o", "%x", "%X", "%n" }), a(OCPointerType.to(OCIntType.LONG), new String[] { "%ld", "%li", "%lu", "%lo", "%lx", "%lX", "%ln" }), a(OCPointerType.to(OCIntType.LONGLONG), new String[] { "%lld", "%lli", "%llu", "%llo", "%llx", "%llX", "%lln" }), a(OCPointerType.to(OCIntType.SIZE_T), new String[] { "%zd", "%zi", "%zu", "%zU", "%zo", "%zO", "%zx", "%zX", "%zn" }), a(OCPointerType.to(OCIntType.PTRDIFF_T), new String[] { "%td", "%ti", "%tu", "%tU", "%to", "%tO", "%tx", "%tX", "%tn" }), a(OCPointerType.to(OCRealType.FLOAT), new String[] { "%a", "%A", "%e", "%E", "%f", "%F", "%g", "%G" }), a(OCPointerType.to(OCRealType.DOUBLE), new String[] { "%la", "%lA", "%le", "%lE", "%lf", "%lF", "%lg", "%lG" }), a(OCPointerType.to(OCRealType.LONG_DOUBLE), new String[] { "%La", "%LA", "%Le", "%LE", "%Lf", "%LF", "%Lg", "%LG" }), a(OCPointerType.to(OCPointerType.to(OCVoidType.instance())), new String[] { "%p" }), a("intmax_t", true, new String[] { "%jd", "%ji", "%ju", "%jU", "%jo", "%jO", "%jx", "%jX", "%jn" })), true, new String[] { "scanf" }), 
        STRFTIME((Collection<Pair<OCTypeWrapper, Set<String>>>)Arrays.asList(a("tm", true, new String[] { "%n", "%t", "%Y", "%y", "%C", "%G", "%g", "%b", "%h", "%B", "%m", "%U", "%W", "%V", "%j", "%d", "%e", "%a", "%A", "%w", "%u", "%H", "%I", "%M", "%S", "%c", "%x", "%X", "%D", "%F", "%r", "%R", "%T", "%p", "%Z", "%z" })), false, new String[] { "strftime" });
        
        @Nullable
        private Collection<Pair<OCTypeWrapper, Set<String>>> myType2spec;
        @NotNull
        private String[] mySuffixes;
        private boolean myNeedArgumentCheck;
        
        private FormatType(@NotNull final Collection<Pair<OCTypeWrapper, Set<String>>> myType2spec, final boolean myNeedArgumentCheck, final String[] mySuffixes) {
            if (mySuffixes == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "suffixes", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType", "<init>"));
            }
            super(s, n);
            this.myType2spec = myType2spec;
            this.myNeedArgumentCheck = myNeedArgumentCheck;
            this.mySuffixes = mySuffixes;
        }
        
        @Nullable
        @Contract("null->null")
        static FormatType getFormatTypeFromName(@Nullable final String s) {
            try {
                if (s == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            for (final FormatType formatType : values()) {
                for (final String s2 : formatType.mySuffixes) {
                    try {
                        if (s.endsWith(s2)) {
                            return formatType;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
            }
            return null;
        }
        
        @Nullable
        public OCType resolveType(@NotNull final String s, @Nullable final PsiElement psiElement) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "spec", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType", "resolveType"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (this.myType2spec == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            for (final Pair<OCTypeWrapper, Set<String>> pair : this.myType2spec) {
                try {
                    if (((Set)pair.second).contains(s)) {
                        return ((OCTypeWrapper)pair.first).getTypeFromContext(psiElement);
                    }
                    continue;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return null;
        }
        
        @Nullable
        public String getSpecifierForType(@NotNull final OCType p0, @Nullable final PsiElement p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: ifnonnull       44
            //     4: new             Ljava/lang/IllegalArgumentException;
            //     7: dup            
            //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    10: ldc             3
            //    12: anewarray       Ljava/lang/Object;
            //    15: dup            
            //    16: ldc             0
            //    18: ldc             "type"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "getSpecifierForType"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aload_0        
            //    45: getstatic       com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.SCANF:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
            //    48: if_acmpne       137
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCString:()Z
            //    55: ifne            137
            //    58: goto            65
            //    61: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    64: athrow         
            //    65: aload_1        
            //    66: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
            //    69: ifeq            99
            //    72: goto            79
            //    75: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    78: athrow         
            //    79: aload_1        
            //    80: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
            //    83: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //    86: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCString:()Z
            //    89: ifne            137
            //    92: goto            99
            //    95: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    98: athrow         
            //    99: aload_1        
            //   100: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
            //   103: ifeq            134
            //   106: goto            113
            //   109: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   112: athrow         
            //   113: getstatic       com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.PRINTF:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
            //   116: aload_1        
            //   117: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
            //   120: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   123: aload_2        
            //   124: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.getSpecifierForType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
            //   127: goto            136
            //   130: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   133: athrow         
            //   134: ldc             "<pointer type required>"
            //   136: areturn        
            //   137: getstatic       com/jetbrains/cidr/lang/types/OCIntType.SSIZE_T:Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   140: aload_1        
            //   141: aload_2        
            //   142: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Lcom/jetbrains/cidr/lang/types/OCIntType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
            //   145: ifeq            159
            //   148: getstatic       com/jetbrains/cidr/lang/types/OCIntType.SSIZE_T:Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   151: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getFormatString:()Ljava/lang/String;
            //   154: areturn        
            //   155: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   158: athrow         
            //   159: getstatic       com/jetbrains/cidr/lang/types/OCIntType.SIZE_T:Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   162: aload_1        
            //   163: aload_2        
            //   164: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Lcom/jetbrains/cidr/lang/types/OCIntType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
            //   167: ifeq            181
            //   170: getstatic       com/jetbrains/cidr/lang/types/OCIntType.SIZE_T:Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   173: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getFormatString:()Ljava/lang/String;
            //   176: areturn        
            //   177: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   180: athrow         
            //   181: getstatic       com/jetbrains/cidr/lang/types/OCIntType.PTRDIFF_T:Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   184: aload_1        
            //   185: aload_2        
            //   186: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Lcom/jetbrains/cidr/lang/types/OCIntType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
            //   189: ifeq            203
            //   192: getstatic       com/jetbrains/cidr/lang/types/OCIntType.PTRDIFF_T:Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   195: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getFormatString:()Ljava/lang/String;
            //   198: areturn        
            //   199: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   202: athrow         
            //   203: aload_1        
            //   204: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getFormatString:()Ljava/lang/String;
            //   207: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  44     58     61     65     Ljava/lang/IllegalArgumentException;
            //  51     72     75     79     Ljava/lang/IllegalArgumentException;
            //  65     92     95     99     Ljava/lang/IllegalArgumentException;
            //  79     106    109    113    Ljava/lang/IllegalArgumentException;
            //  99     130    130    134    Ljava/lang/IllegalArgumentException;
            //  137    155    155    159    Ljava/lang/IllegalArgumentException;
            //  159    177    177    181    Ljava/lang/IllegalArgumentException;
            //  181    199    199    203    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        private static boolean a(@NotNull final OCIntType p0, @NotNull final OCType p1, @Nullable final PsiElement p2) {
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
            //    18: ldc             "compilerType"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "equalsByAlias"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aload_1        
            //    45: ifnonnull       88
            //    48: new             Ljava/lang/IllegalArgumentException;
            //    51: dup            
            //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    54: ldc             3
            //    56: anewarray       Ljava/lang/Object;
            //    59: dup            
            //    60: ldc             0
            //    62: ldc             "type"
            //    64: aastore        
            //    65: dup            
            //    66: ldc             1
            //    68: ldc             "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType"
            //    70: aastore        
            //    71: dup            
            //    72: ldc             2
            //    74: ldc             "equalsByAlias"
            //    76: aastore        
            //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    83: athrow         
            //    84: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    87: athrow         
            //    88: aload_1        
            //    89: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getAliasName:()Ljava/lang/String;
            //    92: astore_3       
            //    93: aload_3        
            //    94: ifnull          201
            //    97: aload_1        
            //    98: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   101: ifeq            201
            //   104: goto            111
            //   107: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   110: athrow         
            //   111: aload_3        
            //   112: aload_0        
            //   113: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getAliasName:()Ljava/lang/String;
            //   116: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //   119: ifne            147
            //   122: goto            129
            //   125: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   128: athrow         
            //   129: aload_3        
            //   130: aload_0        
            //   131: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getText:()Ljava/lang/String;
            //   134: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //   137: ifeq            201
            //   140: goto            147
            //   143: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   146: athrow         
            //   147: aload_1        
            //   148: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   151: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.isSigned:()Z
            //   154: aload_0        
            //   155: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.isSigned:()Z
            //   158: if_icmpne       201
            //   161: goto            168
            //   164: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   167: athrow         
            //   168: aload_1        
            //   169: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   172: aload_2        
            //   173: aconst_null    
            //   174: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getBits:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;)I
            //   177: aload_0        
            //   178: aload_2        
            //   179: aconst_null    
            //   180: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getBits:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;)I
            //   183: if_icmpne       201
            //   186: goto            193
            //   189: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   192: athrow         
            //   193: iconst_1       
            //   194: goto            202
            //   197: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   200: athrow         
            //   201: iconst_0       
            //   202: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  44     84     84     88     Ljava/lang/IllegalArgumentException;
            //  93     104    107    111    Ljava/lang/IllegalArgumentException;
            //  97     122    125    129    Ljava/lang/IllegalArgumentException;
            //  111    140    143    147    Ljava/lang/IllegalArgumentException;
            //  129    161    164    168    Ljava/lang/IllegalArgumentException;
            //  147    186    189    193    Ljava/lang/IllegalArgumentException;
            //  168    197    197    201    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0111:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        @Contract(pure = true)
        public boolean needArgumentsCheck() {
            return this.myNeedArgumentCheck;
        }
        
        private boolean a(final char p0, final char p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: getstatic       com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$1.$SwitchMap$com$jetbrains$cidr$lang$util$OCFormatSpecifiersUtil$FormatType:[I
            //     3: aload_0        
            //     4: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.ordinal:()I
            //     7: iaload         
            //     8: tableswitch {
            //                2: 40
            //                3: 40
            //                4: 40
            //                5: 119
            //          default: 138
            //        }
            //    40: iload_2        
            //    41: bipush          37
            //    43: if_icmpeq       67
            //    46: goto            53
            //    49: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    52: athrow         
            //    53: iload_2        
            //    54: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.b:(C)Z
            //    57: ifeq            93
            //    60: goto            67
            //    63: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    66: athrow         
            //    67: iload_1        
            //    68: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.b:(C)Z
            //    71: ifeq            93
            //    74: goto            81
            //    77: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    80: athrow         
            //    81: iload_1        
            //    82: iload_2        
            //    83: if_icmpne       109
            //    86: goto            93
            //    89: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    92: athrow         
            //    93: ldc             "0123456789.*"
            //    95: iload_1        
            //    96: invokevirtual   java/lang/String.indexOf:(I)I
            //    99: iflt            117
            //   102: goto            109
            //   105: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   108: athrow         
            //   109: iconst_1       
            //   110: goto            118
            //   113: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   116: athrow         
            //   117: iconst_0       
            //   118: ireturn        
            //   119: ldc             "OE"
            //   121: iload_1        
            //   122: invokevirtual   java/lang/String.indexOf:(I)I
            //   125: iflt            136
            //   128: iconst_1       
            //   129: goto            137
            //   132: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   135: athrow         
            //   136: iconst_0       
            //   137: ireturn        
            //   138: iconst_0       
            //   139: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      46     49     53     Ljava/lang/IllegalArgumentException;
            //  40     60     63     67     Ljava/lang/IllegalArgumentException;
            //  53     74     77     81     Ljava/lang/IllegalArgumentException;
            //  67     86     89     93     Ljava/lang/IllegalArgumentException;
            //  81     102    105    109    Ljava/lang/IllegalArgumentException;
            //  93     113    113    117    Ljava/lang/IllegalArgumentException;
            //  119    132    132    136    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0040:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        @Contract(pure = true)
        private static boolean b(final char c) {
            try {
                if (" +-#0".indexOf(c) >= 0) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return false;
        }
        
        private boolean a(final char p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: iload_1        
            //     1: invokestatic    java/lang/Character.isLetter:(C)Z
            //     4: ifeq            13
            //     7: iconst_1       
            //     8: ireturn        
            //     9: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    12: athrow         
            //    13: getstatic       com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$1.$SwitchMap$com$jetbrains$cidr$lang$util$OCFormatSpecifiersUtil$FormatType:[I
            //    16: aload_0        
            //    17: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.ordinal:()I
            //    20: iaload         
            //    21: lookupswitch {
            //                1: 48
            //                2: 71
            //          default: 87
            //        }
            //    48: iload_1        
            //    49: bipush          64
            //    51: if_icmpne       69
            //    54: goto            61
            //    57: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    60: athrow         
            //    61: iconst_1       
            //    62: goto            70
            //    65: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    68: athrow         
            //    69: iconst_0       
            //    70: ireturn        
            //    71: iload_1        
            //    72: bipush          91
            //    74: if_icmpne       85
            //    77: iconst_1       
            //    78: goto            86
            //    81: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    84: athrow         
            //    85: iconst_0       
            //    86: ireturn        
            //    87: iconst_0       
            //    88: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      9      9      13     Ljava/lang/IllegalArgumentException;
            //  13     54     57     61     Ljava/lang/IllegalArgumentException;
            //  48     65     65     69     Ljava/lang/IllegalArgumentException;
            //  71     81     81     85     Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        @Nullable
        public List<SpecifierUsage> collectFormatSpecifiers(@Nullable final OCExpression p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParenthesesAndCasts:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
            //     4: astore_1       
            //     5: aload_1        
            //     6: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
            //     9: ifne            18
            //    12: aconst_null    
            //    13: areturn        
            //    14: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    17: athrow         
            //    18: new             Ljava/util/ArrayList;
            //    21: dup            
            //    22: invokespecial   java/util/ArrayList.<init>:()V
            //    25: astore_2       
            //    26: new             Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator;
            //    29: dup            
            //    30: aload_1        
            //    31: invokespecial   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator.<init>:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)V
            //    34: astore_3       
            //    35: iconst_0       
            //    36: istore          4
            //    38: iconst_0       
            //    39: istore          5
            //    41: iload           4
            //    43: ifeq            52
            //    46: iconst_0       
            //    47: istore          4
            //    49: goto            58
            //    52: aload_3        
            //    53: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator.getNextChar:()C
            //    56: istore          5
            //    58: iload           5
            //    60: ifne            70
            //    63: goto            664
            //    66: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    69: athrow         
            //    70: iload           5
            //    72: bipush          37
            //    74: if_icmpne       41
            //    77: new             Ljava/lang/StringBuilder;
            //    80: dup            
            //    81: invokespecial   java/lang/StringBuilder.<init>:()V
            //    84: astore          6
            //    86: iconst_0       
            //    87: istore          7
            //    89: iconst_0       
            //    90: istore          8
            //    92: iconst_0       
            //    93: istore          9
            //    95: iconst_0       
            //    96: istore          10
            //    98: iconst_0       
            //    99: istore          11
            //   101: aload_3        
            //   102: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator.getPos:()I
            //   105: istore          12
            //   107: iload           12
            //   109: iconst_1       
            //   110: isub           
            //   111: istore          13
            //   113: iload           5
            //   115: bipush          37
            //   117: if_icmpeq       149
            //   120: aload_0        
            //   121: iload           5
            //   123: invokespecial   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(C)Z
            //   126: ifne            149
            //   129: aload_0        
            //   130: iload           5
            //   132: aload_3        
            //   133: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator.getPrevChar:()C
            //   136: invokespecial   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(CC)Z
            //   139: ifeq            337
            //   142: goto            149
            //   145: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   148: athrow         
            //   149: iload           5
            //   151: bipush          37
            //   153: if_icmpne       198
            //   156: goto            163
            //   159: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   162: athrow         
            //   163: iload           7
            //   165: ifne            337
            //   168: goto            175
            //   171: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   174: athrow         
            //   175: iinc            10, 1
            //   178: iload           10
            //   180: iconst_2       
            //   181: if_icmpne       201
            //   184: goto            191
            //   187: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   190: athrow         
            //   191: goto            337
            //   194: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   197: athrow         
            //   198: iconst_1       
            //   199: istore          7
            //   201: iload           8
            //   203: ifne            226
            //   206: aload_0        
            //   207: iload           5
            //   209: aload_3        
            //   210: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator.getPrevChar:()C
            //   213: invokespecial   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(CC)Z
            //   216: ifne            276
            //   219: goto            226
            //   222: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   225: athrow         
            //   226: iload           13
            //   228: iconst_1       
            //   229: iadd           
            //   230: aload_3        
            //   231: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator.getPos:()I
            //   234: if_icmpeq       252
            //   237: goto            244
            //   240: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   243: athrow         
            //   244: iconst_1       
            //   245: goto            253
            //   248: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   251: athrow         
            //   252: iconst_0       
            //   253: istore          9
            //   255: aload           6
            //   257: iload           5
            //   259: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
            //   262: pop            
            //   263: iload           5
            //   265: bipush          37
            //   267: if_icmpeq       314
            //   270: iconst_1       
            //   271: istore          8
            //   273: goto            314
            //   276: iload           5
            //   278: bipush          42
            //   280: if_icmpne       314
            //   283: aload_2        
            //   284: new             Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage;
            //   287: dup            
            //   288: ldc             "*"
            //   290: aload_3        
            //   291: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator.getPos:()I
            //   294: iconst_1       
            //   295: getstatic       com/jetbrains/cidr/lang/editor/colors/OCHighlightingKeys.OC_FORMAT_STRING_TOKEN:Lcom/intellij/openapi/editor/colors/TextAttributesKey;
            //   298: invokespecial   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage.<init>:(Ljava/lang/String;IILcom/intellij/openapi/editor/colors/TextAttributesKey;)V
            //   301: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //   306: pop            
            //   307: goto            314
            //   310: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   313: athrow         
            //   314: iinc            11, 1
            //   317: aload_3        
            //   318: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator.getPos:()I
            //   321: istore          13
            //   323: aload_3        
            //   324: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator.getNextChar:()C
            //   327: istore          5
            //   329: iload           5
            //   331: ifne            113
            //   334: goto            337
            //   337: iload           10
            //   339: iconst_2       
            //   340: if_icmplt       407
            //   343: aload_2        
            //   344: new             Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage;
            //   347: dup            
            //   348: aload           6
            //   350: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   353: iload           9
            //   355: ifeq            373
            //   358: goto            365
            //   361: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   364: athrow         
            //   365: iconst_m1      
            //   366: goto            375
            //   369: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   372: athrow         
            //   373: iload           12
            //   375: iload           10
            //   377: getstatic       com/jetbrains/cidr/lang/editor/colors/OCHighlightingKeys.OC_VALID_STRING_ESCAPE:Lcom/intellij/openapi/editor/colors/TextAttributesKey;
            //   380: invokespecial   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage.<init>:(Ljava/lang/String;IILcom/intellij/openapi/editor/colors/TextAttributesKey;)V
            //   383: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //   388: pop            
            //   389: aload_3        
            //   390: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator.getNextChar:()C
            //   393: istore          5
            //   395: iload           5
            //   397: ifne            658
            //   400: goto            664
            //   403: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   406: athrow         
            //   407: aload           6
            //   409: invokevirtual   java/lang/StringBuilder.length:()I
            //   412: ifle            658
            //   415: aload           6
            //   417: invokevirtual   java/lang/StringBuilder.length:()I
            //   420: istore          14
            //   422: iload           11
            //   424: istore          15
            //   426: iconst_0       
            //   427: istore          16
            //   429: iload           14
            //   431: iconst_1       
            //   432: if_icmple       490
            //   435: aload_0        
            //   436: aload           6
            //   438: iconst_0       
            //   439: iload           14
            //   441: invokevirtual   java/lang/StringBuilder.substring:(II)Ljava/lang/String;
            //   444: aload_1        
            //   445: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.resolveType:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType;
            //   448: ifnull          466
            //   451: goto            458
            //   454: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   457: athrow         
            //   458: iconst_1       
            //   459: goto            467
            //   462: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   465: athrow         
            //   466: iconst_0       
            //   467: istore          16
            //   469: iload           16
            //   471: ifeq            481
            //   474: goto            490
            //   477: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   480: athrow         
            //   481: iinc            14, -1
            //   484: iinc            11, -1
            //   487: goto            429
            //   490: aload           6
            //   492: iconst_0       
            //   493: iload           14
            //   495: invokevirtual   java/lang/StringBuilder.substring:(II)Ljava/lang/String;
            //   498: astore          17
            //   500: aload           17
            //   502: ldc             "["
            //   504: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
            //   507: ifeq            611
            //   510: iload           5
            //   512: ifeq            599
            //   515: goto            522
            //   518: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   521: athrow         
            //   522: iload           5
            //   524: bipush          93
            //   526: if_icmpne       544
            //   529: goto            536
            //   532: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   535: athrow         
            //   536: iconst_1       
            //   537: goto            545
            //   540: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   543: athrow         
            //   544: iconst_0       
            //   545: istore          16
            //   547: iload           16
            //   549: ifeq            559
            //   552: goto            599
            //   555: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   558: athrow         
            //   559: iinc            15, 1
            //   562: aload_3        
            //   563: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator.getPos:()I
            //   566: istore          13
            //   568: aload_3        
            //   569: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator.getNextChar:()C
            //   572: istore          5
            //   574: iload           13
            //   576: iconst_1       
            //   577: iadd           
            //   578: aload_3        
            //   579: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator.getPos:()I
            //   582: if_icmpeq       593
            //   585: iconst_1       
            //   586: goto            594
            //   589: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   592: athrow         
            //   593: iconst_0       
            //   594: istore          9
            //   596: goto            510
            //   599: iload           5
            //   601: ifeq            611
            //   604: iinc            15, 1
            //   607: iload           15
            //   609: istore          11
            //   611: aload_2        
            //   612: new             Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage;
            //   615: dup            
            //   616: aload           17
            //   618: iload           9
            //   620: ifeq            631
            //   623: iconst_m1      
            //   624: goto            633
            //   627: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   630: athrow         
            //   631: iload           12
            //   633: iload           11
            //   635: iload           16
            //   637: ifeq            646
            //   640: getstatic       com/jetbrains/cidr/lang/editor/colors/OCHighlightingKeys.OC_FORMAT_STRING_TOKEN:Lcom/intellij/openapi/editor/colors/TextAttributesKey;
            //   643: goto            649
            //   646: getstatic       com/jetbrains/cidr/lang/editor/colors/OCHighlightingKeys.OC_INVALID_STRING_ESCAPE:Lcom/intellij/openapi/editor/colors/TextAttributesKey;
            //   649: invokespecial   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage.<init>:(Ljava/lang/String;IILcom/intellij/openapi/editor/colors/TextAttributesKey;)V
            //   652: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //   657: pop            
            //   658: iconst_1       
            //   659: istore          4
            //   661: goto            41
            //   664: aload_3        
            //   665: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator.wasEmpty:()Z
            //   668: ifeq            679
            //   671: aconst_null    
            //   672: goto            680
            //   675: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   678: athrow         
            //   679: aload_2        
            //   680: areturn        
            //    Signature:
            //  (Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/util/List<Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage;>;
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  5      14     14     18     Ljava/lang/IllegalArgumentException;
            //  58     66     66     70     Ljava/lang/IllegalArgumentException;
            //  120    142    145    149    Ljava/lang/IllegalArgumentException;
            //  129    156    159    163    Ljava/lang/IllegalArgumentException;
            //  149    168    171    175    Ljava/lang/IllegalArgumentException;
            //  163    184    187    191    Ljava/lang/IllegalArgumentException;
            //  175    194    194    198    Ljava/lang/IllegalArgumentException;
            //  201    219    222    226    Ljava/lang/IllegalArgumentException;
            //  206    237    240    244    Ljava/lang/IllegalArgumentException;
            //  226    248    248    252    Ljava/lang/IllegalArgumentException;
            //  276    307    310    314    Ljava/lang/IllegalArgumentException;
            //  337    358    361    365    Ljava/lang/IllegalArgumentException;
            //  343    369    369    373    Ljava/lang/IllegalArgumentException;
            //  395    403    403    407    Ljava/lang/IllegalArgumentException;
            //  429    451    454    458    Ljava/lang/IllegalArgumentException;
            //  435    462    462    466    Ljava/lang/IllegalArgumentException;
            //  469    477    477    481    Ljava/lang/IllegalArgumentException;
            //  500    515    518    522    Ljava/lang/IllegalArgumentException;
            //  510    529    532    536    Ljava/lang/IllegalArgumentException;
            //  522    540    540    544    Ljava/lang/IllegalArgumentException;
            //  547    555    555    559    Ljava/lang/IllegalArgumentException;
            //  574    589    589    593    Ljava/lang/IllegalArgumentException;
            //  611    627    627    631    Ljava/lang/IllegalArgumentException;
            //  664    675    675    679    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0149:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        public boolean isCompatible(@NotNull final OCType p0, @NotNull final OCType p1, @NotNull final String p2, @NotNull final String p3, @Nullable final PsiElement p4) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: ifnonnull       44
            //     4: new             Ljava/lang/IllegalArgumentException;
            //     7: dup            
            //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    10: ldc             3
            //    12: anewarray       Ljava/lang/Object;
            //    15: dup            
            //    16: ldc             0
            //    18: ldc             "requiredType"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "isCompatible"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aload_2        
            //    45: ifnonnull       88
            //    48: new             Ljava/lang/IllegalArgumentException;
            //    51: dup            
            //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    54: ldc             3
            //    56: anewarray       Ljava/lang/Object;
            //    59: dup            
            //    60: ldc             0
            //    62: ldc             "actualType"
            //    64: aastore        
            //    65: dup            
            //    66: ldc             1
            //    68: ldc             "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType"
            //    70: aastore        
            //    71: dup            
            //    72: ldc             2
            //    74: ldc             "isCompatible"
            //    76: aastore        
            //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    83: athrow         
            //    84: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    87: athrow         
            //    88: aload_3        
            //    89: ifnonnull       132
            //    92: new             Ljava/lang/IllegalArgumentException;
            //    95: dup            
            //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    98: ldc             3
            //   100: anewarray       Ljava/lang/Object;
            //   103: dup            
            //   104: ldc             0
            //   106: ldc             "requiredSpecifierName"
            //   108: aastore        
            //   109: dup            
            //   110: ldc             1
            //   112: ldc             "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType"
            //   114: aastore        
            //   115: dup            
            //   116: ldc             2
            //   118: ldc             "isCompatible"
            //   120: aastore        
            //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //   127: athrow         
            //   128: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   131: athrow         
            //   132: aload           4
            //   134: ifnonnull       177
            //   137: new             Ljava/lang/IllegalArgumentException;
            //   140: dup            
            //   141: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //   143: ldc             3
            //   145: anewarray       Ljava/lang/Object;
            //   148: dup            
            //   149: ldc             0
            //   151: ldc             "actualSpecifierName"
            //   153: aastore        
            //   154: dup            
            //   155: ldc             1
            //   157: ldc             "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType"
            //   159: aastore        
            //   160: dup            
            //   161: ldc             2
            //   163: ldc             "isCompatible"
            //   165: aastore        
            //   166: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   169: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //   172: athrow         
            //   173: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   176: athrow         
            //   177: aload_3        
            //   178: aload           4
            //   180: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //   183: ifne            203
            //   186: aload_1        
            //   187: aload_2        
            //   188: aload           5
            //   190: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Z
            //   193: ifeq            209
            //   196: goto            203
            //   199: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   202: athrow         
            //   203: iconst_1       
            //   204: ireturn        
            //   205: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   208: athrow         
            //   209: aload_0        
            //   210: getstatic       com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.PRINTF:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
            //   213: if_acmpeq       230
            //   216: aload_0        
            //   217: getstatic       com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.NSSTRING:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
            //   220: if_acmpne       579
            //   223: goto            230
            //   226: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   229: athrow         
            //   230: aload_3        
            //   231: ldc             "%@"
            //   233: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //   236: ifeq            283
            //   239: goto            246
            //   242: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   245: athrow         
            //   246: aload_2        
            //   247: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointer:()Z
            //   250: ifeq            283
            //   253: goto            260
            //   256: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   259: athrow         
            //   260: aload_2        
            //   261: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
            //   264: invokestatic    com/jetbrains/cidr/lang/types/OCTollFreeBridges.getNSCounterpart:(Ljava/lang/String;)Ljava/lang/String;
            //   267: ifnull          283
            //   270: goto            277
            //   273: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   276: athrow         
            //   277: iconst_1       
            //   278: ireturn        
            //   279: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   282: athrow         
            //   283: aload_3        
            //   284: ldc             "n"
            //   286: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
            //   289: ifeq            340
            //   292: aload_2        
            //   293: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointer:()Z
            //   296: ifeq            340
            //   299: goto            306
            //   302: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   305: athrow         
            //   306: aload_2        
            //   307: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
            //   310: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   313: astore          6
            //   315: aload           6
            //   317: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
            //   320: ifeq            329
            //   323: iconst_0       
            //   324: ireturn        
            //   325: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   328: athrow         
            //   329: aload_1        
            //   330: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
            //   333: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   336: astore_1       
            //   337: goto            363
            //   340: aload_2        
            //   341: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   344: ifeq            353
            //   347: aload_2        
            //   348: astore          6
            //   350: goto            363
            //   353: aload_0        
            //   354: aload           4
            //   356: aload           5
            //   358: invokevirtual   com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.resolveType:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType;
            //   361: astore          6
            //   363: aload           6
            //   365: ifnull          576
            //   368: aload_1        
            //   369: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   372: ifeq            497
            //   375: goto            382
            //   378: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   381: athrow         
            //   382: aload           6
            //   384: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   387: ifeq            497
            //   390: goto            397
            //   393: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   396: athrow         
            //   397: aload_1        
            //   398: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   401: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
            //   404: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Lcom/jetbrains/cidr/lang/types/CTypeId;)I
            //   407: istore          7
            //   409: aload           6
            //   411: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   414: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
            //   417: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Lcom/jetbrains/cidr/lang/types/CTypeId;)I
            //   420: istore          8
            //   422: iload           7
            //   424: ifle            463
            //   427: iload           8
            //   429: ifle            463
            //   432: goto            439
            //   435: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   438: athrow         
            //   439: iload           7
            //   441: iload           8
            //   443: if_icmpne       461
            //   446: goto            453
            //   449: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   452: athrow         
            //   453: iconst_1       
            //   454: goto            462
            //   457: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   460: athrow         
            //   461: iconst_0       
            //   462: ireturn        
            //   463: aload_1        
            //   464: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   467: aload           5
            //   469: aconst_null    
            //   470: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getBits:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;)I
            //   473: aload           6
            //   475: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   478: aload           5
            //   480: aconst_null    
            //   481: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getBits:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;)I
            //   484: if_icmpne       495
            //   487: iconst_1       
            //   488: goto            496
            //   491: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   494: athrow         
            //   495: iconst_0       
            //   496: ireturn        
            //   497: aload_1        
            //   498: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
            //   501: ifeq            567
            //   504: aload           6
            //   506: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
            //   509: ifeq            567
            //   512: goto            519
            //   515: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   518: athrow         
            //   519: aload_1        
            //   520: checkcast       Lcom/jetbrains/cidr/lang/types/OCRealType;
            //   523: invokevirtual   com/jetbrains/cidr/lang/types/OCRealType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
            //   526: astore          7
            //   528: aload           6
            //   530: checkcast       Lcom/jetbrains/cidr/lang/types/OCRealType;
            //   533: invokevirtual   com/jetbrains/cidr/lang/types/OCRealType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
            //   536: astore          8
            //   538: getstatic       com/jetbrains/cidr/lang/types/CTypeId.LONG_DOUBLE:Lcom/jetbrains/cidr/lang/types/CTypeId;
            //   541: aload           7
            //   543: invokevirtual   com/jetbrains/cidr/lang/types/CTypeId.equals:(Ljava/lang/Object;)Z
            //   546: getstatic       com/jetbrains/cidr/lang/types/CTypeId.LONG_DOUBLE:Lcom/jetbrains/cidr/lang/types/CTypeId;
            //   549: aload           8
            //   551: invokevirtual   com/jetbrains/cidr/lang/types/CTypeId.equals:(Ljava/lang/Object;)Z
            //   554: if_icmpne       565
            //   557: iconst_1       
            //   558: goto            566
            //   561: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   564: athrow         
            //   565: iconst_0       
            //   566: ireturn        
            //   567: aload_1        
            //   568: aload           6
            //   570: aload           5
            //   572: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Z
            //   575: ireturn        
            //   576: goto            705
            //   579: aload_0        
            //   580: getstatic       com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.SCANF:Lcom/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType;
            //   583: if_acmpne       705
            //   586: aload_1        
            //   587: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
            //   590: ifeq            705
            //   593: goto            600
            //   596: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   599: athrow         
            //   600: aload_2        
            //   601: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
            //   604: ifeq            705
            //   607: goto            614
            //   610: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   613: athrow         
            //   614: aload_2        
            //   615: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
            //   618: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   621: astore          6
            //   623: aload           6
            //   625: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
            //   628: ifeq            637
            //   631: iconst_0       
            //   632: ireturn        
            //   633: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   636: athrow         
            //   637: aload_1        
            //   638: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
            //   641: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   644: astore          7
            //   646: aload           7
            //   648: instanceof      Lcom/jetbrains/cidr/lang/types/OCNumericType;
            //   651: ifeq            705
            //   654: aload           6
            //   656: instanceof      Lcom/jetbrains/cidr/lang/types/OCNumericType;
            //   659: ifeq            705
            //   662: goto            669
            //   665: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   668: athrow         
            //   669: aload           7
            //   671: checkcast       Lcom/jetbrains/cidr/lang/types/OCNumericType;
            //   674: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
            //   677: aload           6
            //   679: checkcast       Lcom/jetbrains/cidr/lang/types/OCNumericType;
            //   682: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
            //   685: if_acmpne       703
            //   688: goto            695
            //   691: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   694: athrow         
            //   695: iconst_1       
            //   696: goto            704
            //   699: invokestatic    com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   702: athrow         
            //   703: iconst_0       
            //   704: ireturn        
            //   705: iconst_0       
            //   706: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  44     84     84     88     Ljava/lang/IllegalArgumentException;
            //  88     128    128    132    Ljava/lang/IllegalArgumentException;
            //  132    173    173    177    Ljava/lang/IllegalArgumentException;
            //  177    196    199    203    Ljava/lang/IllegalArgumentException;
            //  186    205    205    209    Ljava/lang/IllegalArgumentException;
            //  209    223    226    230    Ljava/lang/IllegalArgumentException;
            //  216    239    242    246    Ljava/lang/IllegalArgumentException;
            //  230    253    256    260    Ljava/lang/IllegalArgumentException;
            //  246    270    273    277    Ljava/lang/IllegalArgumentException;
            //  260    279    279    283    Ljava/lang/IllegalArgumentException;
            //  283    299    302    306    Ljava/lang/IllegalArgumentException;
            //  315    325    325    329    Ljava/lang/IllegalArgumentException;
            //  363    375    378    382    Ljava/lang/IllegalArgumentException;
            //  368    390    393    397    Ljava/lang/IllegalArgumentException;
            //  422    432    435    439    Ljava/lang/IllegalArgumentException;
            //  427    446    449    453    Ljava/lang/IllegalArgumentException;
            //  439    457    457    461    Ljava/lang/IllegalArgumentException;
            //  463    491    491    495    Ljava/lang/IllegalArgumentException;
            //  497    512    515    519    Ljava/lang/IllegalArgumentException;
            //  538    561    561    565    Ljava/lang/IllegalArgumentException;
            //  579    593    596    600    Ljava/lang/IllegalArgumentException;
            //  586    607    610    614    Ljava/lang/IllegalArgumentException;
            //  623    633    633    637    Ljava/lang/IllegalArgumentException;
            //  646    662    665    669    Ljava/lang/IllegalArgumentException;
            //  654    688    691    695    Ljava/lang/IllegalArgumentException;
            //  669    699    699    703    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0230:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        private static int a(final CTypeId cTypeId) {
            try {
                switch (cTypeId) {
                    case BOOL:
                    case SIGNED_CHAR:
                    case CHAR:
                    case CHAR16_T:
                    case CHAR32_T:
                    case WCHAR_T:
                    case SHORT:
                    case INT: {
                        return 1;
                    }
                    case LONG: {
                        break;
                    }
                    case LONG_LONG: {
                        return 3;
                    }
                    default: {
                        return 0;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return 2;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class FormatSpecifiersInfo
    {
        public FormatType formatType;
        public int formatStringIndex;
        public int argumentsIndex;
        
        public FormatSpecifiersInfo(@NotNull final FormatType formatType, final int n, final int n2) {
            if (formatType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "formatType", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo", "<init>"));
            }
            this.formatType = formatType;
            int formatStringIndex;
            if (n < 0) {
                formatStringIndex = -1;
            }
            else {
                formatStringIndex = n;
            }
            int argumentsIndex = 0;
            Label_0076: {
                try {
                    this.formatStringIndex = formatStringIndex;
                    if (n2 < 0) {
                        argumentsIndex = -1;
                        break Label_0076;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                argumentsIndex = n2;
            }
            this.argumentsIndex = argumentsIndex;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class SpecifierUsage
    {
        @NotNull
        private String name;
        private int lengthWithFormat;
        private int offset;
        @NotNull
        private TextAttributesKey specType;
        
        public SpecifierUsage(@NotNull final String name, final int offset, final int lengthWithFormat, @NotNull final TextAttributesKey specType) {
            if (name == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage", "<init>"));
            }
            if (specType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "specType", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage", "<init>"));
            }
            this.name = name;
            this.lengthWithFormat = lengthWithFormat;
            this.offset = offset;
            this.specType = specType;
        }
        
        @NotNull
        public String getName() {
            String name;
            try {
                name = this.name;
                if (name == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage", "getName"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return name;
        }
        
        @NotNull
        public TextAttributesKey getType() {
            TextAttributesKey specType;
            try {
                specType = this.specType;
                if (specType == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage", "getType"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return specType;
        }
        
        @Nullable
        public TextRange getRange() {
            try {
                if (this.offset < 0) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return new TextRange(this.offset, this.offset + this.lengthWithFormat);
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class OCTypeWrapper
    {
        final String myTypeName;
        final boolean myTypeNameIsPointer;
        final OCType myType;
        
        public OCTypeWrapper(@NotNull final OCType myType) {
            if (myType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$OCTypeWrapper", "<init>"));
            }
            this.myType = myType;
            this.myTypeName = null;
            this.myTypeNameIsPointer = false;
        }
        
        public OCTypeWrapper(@NotNull final String myTypeName, final boolean myTypeNameIsPointer) {
            if (myTypeName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeName", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$OCTypeWrapper", "<init>"));
            }
            this.myType = null;
            this.myTypeName = myTypeName;
            this.myTypeNameIsPointer = myTypeNameIsPointer;
        }
        
        public OCType getTypeFromContext(@Nullable final PsiElement psiElement) {
            try {
                if (this.myType != null) {
                    return this.myType;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            String myTypeName = null;
            PsiFile containingFile = null;
            Label_0038: {
                try {
                    myTypeName = this.myTypeName;
                    if (psiElement == null) {
                        containingFile = null;
                        break Label_0038;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                containingFile = psiElement.getContainingFile();
            }
            final OCType resolvedFromText = OCReferenceType.resolvedFromText(myTypeName, containingFile);
            try {
                if (this.myTypeNameIsPointer) {
                    return OCPointerType.to(resolvedFromText);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            return resolvedFromText;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class StrFragmentIterator
    {
        public static final char END_MARKER = '\0';
        private ASTNode child;
        private int pos;
        private int endPos;
        private boolean BOF;
        private boolean hasStringLiteral;
        private char cur;
        private char prev;
        private char prevPrev;
        private final OCHighlightingLexer lexer;
        
        public StrFragmentIterator(@NotNull final OCExpression ocExpression) {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$StrFragmentIterator", "<init>"));
            }
            this.pos = -1;
            this.endPos = -1;
            this.BOF = true;
            this.hasStringLiteral = false;
            this.cur = '\0';
            this.prev = '\0';
            this.prevPrev = '\0';
            this.lexer = new OCHighlightingLexer(OCLanguageKind.OBJ_CPP, false, false, false);
            this.child = ocExpression.getNode().getFirstChildNode();
        }
        
        public boolean wasEmpty() {
            try {
                if (!this.hasStringLiteral) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return false;
        }
        
        private boolean a() {
            Label_0121: {
                boolean hasStringLiteral = false;
                boolean b3 = false;
                Label_0117: {
                    Label_0096: {
                        while (true) {
                            Label_0021: {
                                try {
                                    if (this.child == null) {
                                        break Label_0121;
                                    }
                                    final StrFragmentIterator strFragmentIterator = this;
                                    final boolean b = strFragmentIterator.BOF;
                                    if (b) {
                                        break Label_0021;
                                    }
                                    break Label_0021;
                                }
                                catch (IllegalArgumentException ex) {
                                    throw a(ex);
                                }
                                try {
                                    final StrFragmentIterator strFragmentIterator = this;
                                    final boolean b = strFragmentIterator.BOF;
                                    if (b) {
                                        this.BOF = false;
                                        break Label_0046;
                                    }
                                }
                                catch (IllegalArgumentException ex2) {
                                    throw a(ex2);
                                }
                            }
                            this.child = this.child.getTreeNext();
                            Label_0076: {
                                try {
                                    if (this.child == null) {
                                        break Label_0096;
                                    }
                                    final TokenSet set = OCTokenTypes.ALL_STRINGS;
                                    final StrFragmentIterator strFragmentIterator2 = this;
                                    final ASTNode astNode = strFragmentIterator2.child;
                                    final IElementType elementType = OCElementUtil.getElementType(astNode);
                                    final boolean b2 = set.contains(elementType);
                                    if (!b2) {
                                        break Label_0076;
                                    }
                                    break Label_0096;
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw a(ex3);
                                }
                                try {
                                    final TokenSet set = OCTokenTypes.ALL_STRINGS;
                                    final StrFragmentIterator strFragmentIterator2 = this;
                                    final ASTNode astNode = strFragmentIterator2.child;
                                    final IElementType elementType = OCElementUtil.getElementType(astNode);
                                    final boolean b2 = set.contains(elementType);
                                    if (!b2) {
                                        this.child = this.child.getTreeNext();
                                        continue;
                                    }
                                }
                                catch (IllegalArgumentException ex4) {
                                    throw a(ex4);
                                }
                            }
                            break;
                        }
                        try {
                            hasStringLiteral = this.hasStringLiteral;
                            if (this.child != null) {
                                b3 = true;
                                break Label_0117;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                    }
                    b3 = false;
                }
                this.hasStringLiteral = (hasStringLiteral | b3);
                try {
                    if (this.child != null) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            return false;
        }
        
        private void b() {
            try {
                this.lexer.start((CharSequence)this.child.getText());
                if (OCTokenTypes.RAW_STRING_LITERALS.contains(OCElementUtil.getElementType(this.child))) {
                    this.lexer.advance();
                    this.lexer.advance();
                    this.lexer.advance();
                    this.pos = this.lexer.getTokenStart();
                    this.endPos = this.lexer.getTokenEnd();
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (OCHighlightingLexer.PREFIX_TYPE == this.lexer.getTokenType()) {
                    this.lexer.advance();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            this.pos = this.lexer.getTokenStart() + 1;
            this.endPos = this.lexer.getBufferEnd() - 1;
        }
        
        public int getPos() {
            Label_0024: {
                try {
                    if (this.child == null) {
                        break Label_0024;
                    }
                    final StrFragmentIterator strFragmentIterator = this;
                    final ASTNode astNode = strFragmentIterator.child;
                    final boolean b = astNode instanceof OCMacroForeignLeafElement;
                    if (b) {
                        break Label_0024;
                    }
                    return this.child.getStartOffset() + this.pos;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final StrFragmentIterator strFragmentIterator = this;
                    final ASTNode astNode = strFragmentIterator.child;
                    final boolean b = astNode instanceof OCMacroForeignLeafElement;
                    if (b) {
                        return -1;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return this.child.getStartOffset() + this.pos;
        }
        
        public char getPrevChar() {
            return this.prev;
        }
        
        public char getPrevPrevChar() {
            return this.prevPrev;
        }
        
        public char getNextChar() {
            while (true) {
                while (true) {
                    try {
                        this.prevPrev = this.prev;
                        this.prev = this.cur;
                        if (this.pos == -1) {
                            break Label_0044;
                        }
                        final StrFragmentIterator strFragmentIterator = this;
                        final int n = strFragmentIterator.pos;
                        final int n2 = 1;
                        final int n3 = n + n2;
                        final StrFragmentIterator strFragmentIterator2 = this;
                        final int n4 = strFragmentIterator2.endPos;
                        if (n3 >= n4) {
                            break Label_0044;
                        }
                        return this.cur = this.lexer.getBufferSequence().charAt(++this.pos);
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final StrFragmentIterator strFragmentIterator = this;
                        final int n = strFragmentIterator.pos;
                        final int n2 = 1;
                        final int n3 = n + n2;
                        final StrFragmentIterator strFragmentIterator2 = this;
                        final int n4 = strFragmentIterator2.endPos;
                        if (n3 < n4) {
                            return this.cur = this.lexer.getBufferSequence().charAt(++this.pos);
                        }
                        if (!this.a()) {
                            return '\0';
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    this.b();
                    if (this.pos < this.endPos) {
                        return this.cur = this.lexer.getBufferSequence().charAt(this.pos);
                    }
                    continue;
                }
            }
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
