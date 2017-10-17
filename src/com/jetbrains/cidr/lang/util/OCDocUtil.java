// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.types.OCExpressionTypeArgument;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterValueSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.search.OCSearchUtil;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Consumer;
import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.types.CVQualifiers;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.OCQualifiedNameWithArguments;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import java.util.Collections;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.types.OCStructType;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.List;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.visitors.OCTypeParameterResolveVisitor;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.intellij.util.Function;
import com.jetbrains.cidr.lang.symbols.OCSymbolAttribute;
import java.util.EnumSet;

public class OCDocUtil
{
    private static EnumSet<OCSymbolAttribute> POST_FUNC_ATTRIBUTES;
    private static EnumSet<OCSymbolAttribute> SKIP_DECL_ATTRIBUTES;
    private static final Function<OCSymbolWithQualifiedName, String> TO_NAME;
    
    @NotNull
    public static String parameterSignature(@NotNull final String s, @Nullable final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/util/OCDocUtil", "parameterSignature"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String parameterSignature;
        try {
            parameterSignature = parameterSignature(s, s2, null);
            if (parameterSignature == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCDocUtil", "parameterSignature"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return parameterSignature;
    }
    
    @NotNull
    public static String parameterSignature(@NotNull final String s, @Nullable final String s2, @Nullable final String s3) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/util/OCDocUtil", "parameterSignature"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final StringBuilder sb = new StringBuilder();
        boolean b2 = false;
        Label_0081: {
            Label_0072: {
                try {
                    if (s2 == null) {
                        break Label_0072;
                    }
                    final String s4 = "<unnamed>";
                    final String s5 = s2;
                    final boolean b = s4.equals(s5);
                    if (!b) {
                        break Label_0072;
                    }
                    break Label_0072;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final String s4 = "<unnamed>";
                    final String s5 = s2;
                    final boolean b = s4.equals(s5);
                    if (!b) {
                        b2 = true;
                        break Label_0081;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            b2 = false;
        }
        final boolean b3 = b2;
        try {
            sb.append(s);
            if (b3) {
                sb.append(delimiter(s));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        Label_0126: {
            StringBuilder sb2;
            try {
                sb2 = sb;
                if (b3) {
                    final String s6 = s2;
                    break Label_0126;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            final String s6 = "";
            try {
                sb2.append(s6);
                if (s3 != null) {
                    sb.append(" = ").append(s3);
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        String string;
        try {
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCDocUtil", "parameterSignature"));
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        return string;
    }
    
    public static String delimiter(final String s) {
        return delimiter(s, " ");
    }
    
    public static String delimiter(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ldc             "*"
        //     3: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //     6: ifne            47
        //     9: aload_0        
        //    10: ldc             "&"
        //    12: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //    15: ifne            47
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/util/OCDocUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    24: athrow         
        //    25: aload_0        
        //    26: ldc             "&amp;"
        //    28: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //    31: ifne            47
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/util/OCDocUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: aload_1        
        //    42: areturn        
        //    43: invokestatic    com/jetbrains/cidr/lang/util/OCDocUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    46: athrow         
        //    47: ldc             ""
        //    49: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      18     21     25     Ljava/lang/IllegalArgumentException;
        //  9      34     37     41     Ljava/lang/IllegalArgumentException;
        //  25     43     43     47     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0025:
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
    
    public static void extractModifiers(@NotNull final OCDeclaratorSymbol ocDeclaratorSymbol, @NotNull final StringBuilder sb) {
        try {
            if (ocDeclaratorSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/util/OCDocUtil", "extractModifiers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sb", "com/jetbrains/cidr/lang/util/OCDocUtil", "extractModifiers"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        for (final OCSymbolAttribute ocSymbolAttribute : OCSymbolAttribute.values()) {
            Label_0137: {
                try {
                    if (!ocDeclaratorSymbol.hasAttribute(ocSymbolAttribute)) {
                        break Label_0137;
                    }
                    final EnumSet<OCSymbolAttribute> set = OCDocUtil.SKIP_DECL_ATTRIBUTES;
                    final OCSymbolAttribute ocSymbolAttribute2 = ocSymbolAttribute;
                    final boolean b = set.contains(ocSymbolAttribute2);
                    if (!b) {
                        break Label_0137;
                    }
                    break Label_0137;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final EnumSet<OCSymbolAttribute> set = OCDocUtil.SKIP_DECL_ATTRIBUTES;
                    final OCSymbolAttribute ocSymbolAttribute2 = ocSymbolAttribute;
                    final boolean b = set.contains(ocSymbolAttribute2);
                    if (!b) {
                        sb.append(ocSymbolAttribute).append(" ");
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
        }
    }
    
    public static void extractModifiers(@NotNull final OCFunctionSymbol ocFunctionSymbol, @NotNull final StringBuilder sb) {
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/util/OCDocUtil", "extractModifiers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sb", "com/jetbrains/cidr/lang/util/OCDocUtil", "extractModifiers"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        for (final OCSymbolAttribute ocSymbolAttribute : OCSymbolAttribute.values()) {
            Label_0137: {
                try {
                    if (OCDocUtil.POST_FUNC_ATTRIBUTES.contains(ocSymbolAttribute)) {
                        break Label_0137;
                    }
                    final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                    final OCSymbolAttribute ocSymbolAttribute2 = ocSymbolAttribute;
                    final boolean b = ocFunctionSymbol2.hasAttribute(ocSymbolAttribute2);
                    if (b) {
                        break Label_0137;
                    }
                    break Label_0137;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                    final OCSymbolAttribute ocSymbolAttribute2 = ocSymbolAttribute;
                    final boolean b = ocFunctionSymbol2.hasAttribute(ocSymbolAttribute2);
                    if (b) {
                        sb.append(ocSymbolAttribute).append(" ");
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
        }
    }
    
    public static void extractModifiers(@NotNull final OCStructSymbol ocStructSymbol, @NotNull final StringBuilder sb) {
        try {
            if (ocStructSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/util/OCDocUtil", "extractModifiers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sb", "com/jetbrains/cidr/lang/util/OCDocUtil", "extractModifiers"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        for (final OCSymbolAttribute ocSymbolAttribute : OCSymbolAttribute.values()) {
            try {
                if (ocStructSymbol.hasAttribute(ocSymbolAttribute)) {
                    sb.append(ocSymbolAttribute).append(" ");
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        try {
            if (ocStructSymbol.isTemplateSymbol()) {
                sb.append(OCTokenTypes.TEMPLATE_CPP_KEYWORD.getName()).append(" ");
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
    }
    
    public static void extractFuncPostModifiers(@NotNull final OCFunctionSymbol p0, @NotNull final StringBuilder p1) {
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
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCDocUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "extractFuncPostModifiers"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCDocUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "sb"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/util/OCDocUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "extractFuncPostModifiers"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/util/OCDocUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isConst:()Z
        //    92: ifeq            118
        //    95: aload_1        
        //    96: ldc             " "
        //    98: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   101: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONST_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   104: invokevirtual   com/jetbrains/cidr/lang/parser/OCElementType.getName:()Ljava/lang/String;
        //   107: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   110: pop            
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/lang/util/OCDocUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   117: athrow         
        //   118: getstatic       com/jetbrains/cidr/lang/util/OCDocUtil.POST_FUNC_ATTRIBUTES:Ljava/util/EnumSet;
        //   121: invokevirtual   java/util/EnumSet.iterator:()Ljava/util/Iterator;
        //   124: astore_2       
        //   125: aload_2        
        //   126: invokeinterface java/util/Iterator.hasNext:()Z
        //   131: ifeq            208
        //   134: aload_2        
        //   135: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   140: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //   143: astore_3       
        //   144: aload_0        
        //   145: aload_3        
        //   146: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.hasAttribute:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;)Z
        //   149: ifeq            205
        //   152: aload_3        
        //   153: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.DELETE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //   156: if_acmpeq       180
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/util/OCDocUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: aload_3        
        //   167: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolAttribute.DEFAULT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolAttribute;
        //   170: if_acmpne       194
        //   173: goto            180
        //   176: invokestatic    com/jetbrains/cidr/lang/util/OCDocUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   179: athrow         
        //   180: aload_1        
        //   181: ldc             " ="
        //   183: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   186: pop            
        //   187: goto            194
        //   190: invokestatic    com/jetbrains/cidr/lang/util/OCDocUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   193: athrow         
        //   194: aload_1        
        //   195: ldc             " "
        //   197: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   200: aload_3        
        //   201: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   204: pop            
        //   205: goto            125
        //   208: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     111    114    118    Ljava/lang/IllegalArgumentException;
        //  144    159    162    166    Ljava/lang/IllegalArgumentException;
        //  152    173    176    180    Ljava/lang/IllegalArgumentException;
        //  166    187    190    194    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0166:
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
    public static String getParametersSignature(final OCFunctionSymbol ocFunctionSymbol, final boolean b) {
        final StringBuilder sb = new StringBuilder("(");
        final List<OCSymbolWithQualifiedName> parents = getParents(ocFunctionSymbol);
        final OCFile containingOCFile = ocFunctionSymbol.getContainingOCFile();
        int n = 1;
        for (final OCDeclaratorSymbol ocDeclaratorSymbol : ocFunctionSymbol.getParameterSymbols()) {
            try {
                if (n == 0) {
                    sb.append(", ");
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            String s;
            if (b) {
                s = getCanonicalName(ocDeclaratorSymbol.getType().accept((OCTypeVisitor<OCType>)new OCTypeParameterResolveVisitor((PsiFile)ocDeclaratorSymbol.getContainingOCFile())), parents, containingOCFile);
            }
            else {
                s = ocDeclaratorSymbol.getType().getCanonicalName();
            }
            try {
                sb.append(parameterSignature(s, ocDeclaratorSymbol.getName()));
                if (ocDeclaratorSymbol.hasInitializer()) {
                    sb.append(" = ").append(ocDeclaratorSymbol.getInitializer().getSignature());
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            n = 0;
        }
        String string;
        try {
            string = sb.append(')').toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCDocUtil", "getParametersSignature"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return string;
    }
    
    @NotNull
    public static String getCanonicalName(@NotNull final OCType ocType, @NotNull final List<OCSymbolWithQualifiedName> list, @Nullable final OCFile ocFile) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/util/OCDocUtil", "getCanonicalName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "contextNamespace", "com/jetbrains/cidr/lang/util/OCDocUtil", "getCanonicalName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCType terminalType = ocType.getTerminalType();
        final OCType resolve = terminalType.resolve((PsiFile)ocFile);
        if (resolve instanceof OCStructType) {
            final OCStructSymbol symbol = ((OCStructType)resolve).getSymbol();
            final List<OCSymbolWithQualifiedName> a = a(symbol, OCSymbolKind.NAMESPACE, OCSymbolKind.STRUCT, OCSymbolKind.ENUM, OCSymbolKind.UNION);
            if (!a.isEmpty()) {
                for (final OCSymbolWithQualifiedName ocSymbolWithQualifiedName : list) {
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = a.get(0);
                    try {
                        if (!ocSymbolWithQualifiedName2.equals(ocSymbolWithQualifiedName)) {
                            continue;
                        }
                        a.remove(0);
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
            }
            final StringBuilder sb = new StringBuilder();
            CVQualifiers cvQualifiers = null;
            Label_0283: {
                Label_0265: {
                    try {
                        if (ocType instanceof OCCppReferenceType) {
                            break Label_0265;
                        }
                        final OCType ocType2 = ocType;
                        final boolean b = ocType2 instanceof OCPointerType;
                        if (b) {
                            break Label_0265;
                        }
                        break Label_0265;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final OCType ocType2 = ocType;
                        final boolean b = ocType2 instanceof OCPointerType;
                        if (b) {
                            cvQualifiers = ocType.getTerminalType().getCVQualifiers();
                            break Label_0283;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                cvQualifiers = ocType.getCVQualifiers();
            }
            final CVQualifiers cvQualifiers2 = cvQualifiers;
            try {
                if (cvQualifiers2.isConst()) {
                    sb.append(OCTokenTypes.CONST_KEYWORD.getName()).append(" ");
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            try {
                if (cvQualifiers2.isVolatile()) {
                    sb.append(OCTokenTypes.VOLATILE_KEYWORD.getName()).append(" ");
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
            sb.append(StringUtil.join((Collection)a, ocSymbolWithQualifiedName -> b((OCSymbol)ocSymbolWithQualifiedName), "::"));
            List<OCTypeArgument> list2 = Collections.emptyList();
            if (terminalType instanceof OCReferenceType) {
                final OCQualifiedName qualifiedName = ((OCReferenceType)terminalType).getReference((PsiFile)ocFile).getQualifiedName();
                if (qualifiedName instanceof OCQualifiedNameWithArguments) {
                    list2 = ((OCQualifiedNameWithArguments)qualifiedName).getArguments();
                }
            }
            else {
                list2 = symbol.getTemplateArguments(new OCResolveContext());
            }
            try {
                wrapTemplateArgs(list2, list, ocFile, sb);
                if (ocType instanceof OCCppReferenceType) {
                    sb.append(" &amp;");
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
            try {
                if (ocType instanceof OCPointerType) {
                    sb.append(" *");
                }
            }
            catch (IllegalArgumentException ex9) {
                throw a(ex9);
            }
            String string;
            try {
                string = sb.toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCDocUtil", "getCanonicalName"));
                }
            }
            catch (IllegalArgumentException ex10) {
                throw a(ex10);
            }
            return string;
        }
        String escapeXml;
        try {
            escapeXml = StringUtil.escapeXml(ocType.getCanonicalName());
            if (escapeXml == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCDocUtil", "getCanonicalName"));
            }
        }
        catch (IllegalArgumentException ex11) {
            throw a(ex11);
        }
        return escapeXml;
    }
    
    @NotNull
    public static String getNamespace(@NotNull final OCSymbolWithQualifiedName<?> ocSymbolWithQualifiedName) {
        try {
            if (ocSymbolWithQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/util/OCDocUtil", "getNamespace"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String join;
        try {
            join = StringUtil.join((Collection)getParents(ocSymbolWithQualifiedName), (Function)OCDocUtil.TO_NAME, "::");
            if (join == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCDocUtil", "getNamespace"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return join;
    }
    
    @NotNull
    public static String getCanonicalPrefix(@NotNull final OCSymbolWithQualifiedName<?> ocSymbolWithQualifiedName) {
        try {
            if (ocSymbolWithQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/util/OCDocUtil", "getCanonicalPrefix"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<OCSymbolWithQualifiedName> b = b(ocSymbolWithQualifiedName);
        final List<Object> list = b.stream().filter(ocSymbolWithQualifiedName2 -> ocSymbolWithQualifiedName2.getKind().isStructLike()).collect((Collector<? super Object, ?, List<Object>>)Collectors.toList());
        final boolean b2;
        final List<? super Object> list2 = b.stream().filter(ocSymbolWithQualifiedName3 -> {
            try {
                if (ocSymbolWithQualifiedName3.getKind() == OCSymbolKind.NAMESPACE) {
                    return b2;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return b2;
        }).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList());
        if (!list.isEmpty()) {
            final String canonicalName = getCanonicalName(list.get(list.size() - 1).getType(), (List<OCSymbolWithQualifiedName>)list2, ocSymbolWithQualifiedName.getContainingOCFile());
            String replaceAnonymous;
            try {
                replaceAnonymous = replaceAnonymous(canonicalName);
                if (replaceAnonymous == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCDocUtil", "getCanonicalPrefix"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            return replaceAnonymous;
        }
        String s;
        try {
            s = "";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCDocUtil", "getCanonicalPrefix"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return s;
    }
    
    @NotNull
    public static String replaceAnonymous(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "canonicalName", "com/jetbrains/cidr/lang/util/OCDocUtil", "replaceAnonymous"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String replace;
        try {
            replace = s.replace("anonymous ", "<i>(anonymous)</i>");
            if (replace == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCDocUtil", "replaceAnonymous"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return replace;
    }
    
    @NotNull
    public static List<OCSymbolWithQualifiedName> getParents(@NotNull final OCSymbolWithQualifiedName<?> ocSymbolWithQualifiedName) {
        try {
            if (ocSymbolWithQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/util/OCDocUtil", "getParents"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<OCSymbolWithQualifiedName> a = a(ocSymbolWithQualifiedName, OCSymbolKind.NAMESPACE);
        List<OCSymbolWithQualifiedName> list;
        try {
            a.remove(ocSymbolWithQualifiedName);
            list = a;
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCDocUtil", "getParents"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return list;
    }
    
    @NotNull
    private static List<OCSymbolWithQualifiedName> a(@NotNull final OCSymbolWithQualifiedName<?> ocSymbolWithQualifiedName, @NotNull final OCSymbolKind... array) {
        try {
            if (ocSymbolWithQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/util/OCDocUtil", "getQualifiedSymbolChain"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filter", "com/jetbrains/cidr/lang/util/OCDocUtil", "getQualifiedSymbolChain"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final ArrayList list = new ArrayList<OCSymbolWithQualifiedName>();
        ArrayList list2;
        try {
            ocSymbolWithQualifiedName.getResolvedQualifiedName(true, new OCResolveContext((PsiElement)ocSymbolWithQualifiedName.getContainingOCFile()), true, true, true, true, false, (com.intellij.util.Consumer<OCSymbolWithQualifiedName<?>>)(ocSymbolWithQualifiedName -> {
                try {
                    if (array == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filter", "com/jetbrains/cidr/lang/util/OCDocUtil", "lambda$getQualifiedSymbolChain$3"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                for (final OCSymbolKind ocSymbolKind : array) {
                    try {
                        if (ocSymbolWithQualifiedName.getKind() == ocSymbolKind) {
                            list.add(ocSymbolWithQualifiedName);
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
            }));
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCDocUtil", "getQualifiedSymbolChain"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return (List<OCSymbolWithQualifiedName>)list2;
    }
    
    @NotNull
    private static List<OCSymbolWithQualifiedName> b(@NotNull final OCSymbolWithQualifiedName<?> ocSymbolWithQualifiedName) {
        try {
            if (ocSymbolWithQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/util/OCDocUtil", "getQualifiedSymbolChain"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ArrayList list = new ArrayList<OCSymbolWithQualifiedName>();
        ArrayList list2;
        try {
            ocSymbolWithQualifiedName.getResolvedQualifiedName(true, new OCResolveContext((PsiElement)ocSymbolWithQualifiedName.getContainingOCFile()), true, true, true, true, false, (com.intellij.util.Consumer<OCSymbolWithQualifiedName<?>>)list::add);
            list.remove(ocSymbolWithQualifiedName);
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCDocUtil", "getQualifiedSymbolChain"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (List<OCSymbolWithQualifiedName>)list2;
    }
    
    @NotNull
    private static String b(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/util/OCDocUtil", "wrapSymbolName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0106: {
            String s2 = null;
            Label_0071: {
                try {
                    if (ocSymbol instanceof OCStructSymbol) {
                        break Label_0106;
                    }
                    final OCStructSymbol ocStructSymbol = (OCStructSymbol)ocSymbol;
                    final String s = ocStructSymbol.getPresentableName();
                    s2 = StringUtil.escapeXml(s);
                    if (s2 == null) {
                        break Label_0071;
                    }
                    return s2;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCStructSymbol ocStructSymbol = (OCStructSymbol)ocSymbol;
                    final String s = ocStructSymbol.getPresentableName();
                    s2 = StringUtil.escapeXml(s);
                    if (s2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCDocUtil", "wrapSymbolName"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return s2;
        }
        final StringBuilder sb = new StringBuilder();
        String string;
        try {
            a((OCStructSymbol)ocSymbol, sb);
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCDocUtil", "wrapSymbolName"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return string;
    }
    
    private static void a(@NotNull final OCStructSymbol ocStructSymbol, @NotNull final StringBuilder sb) {
        try {
            if (ocStructSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/util/OCDocUtil", "wrapStruct"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sb", "com/jetbrains/cidr/lang/util/OCDocUtil", "wrapStruct"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final String name = ocStructSymbol.getName();
        boolean b = false;
        Label_0111: {
            try {
                if (!"<unnamed>".equals(name)) {
                    b = true;
                    break Label_0111;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            b = false;
        }
        final boolean b2 = b;
        final String a = a((OCSymbol)ocStructSymbol);
        Label_0134: {
            try {
                if (!b2) {
                    break Label_0134;
                }
                final String s = a;
                if (s != null) {
                    break Label_0134;
                }
                break Label_0134;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final String s = a;
                if (s != null) {
                    sb.append("<a href=\"").append(a).append("\">").append(StringUtil.escapeXml(name)).append("</a>");
                    return;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        sb.append(StringUtil.escapeXml(ocStructSymbol.getPresentableName()));
    }
    
    public static String getLink(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/util/OCDocUtil", "getLink"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String presentableName = ocSymbol.getPresentableName();
        boolean b = false;
        Label_0069: {
            try {
                if (!"<unnamed>".equals(presentableName)) {
                    b = true;
                    break Label_0069;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            b = false;
        }
        final boolean b2 = b;
        final String a = a(ocSymbol);
        Label_0090: {
            try {
                if (!b2) {
                    return StringUtil.escapeXml(ocSymbol.getPresentableName());
                }
                final String s = a;
                if (s != null) {
                    break Label_0090;
                }
                return StringUtil.escapeXml(ocSymbol.getPresentableName());
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final String s = a;
                if (s != null) {
                    return "<a href=\"" + a + "\">" + StringUtil.escapeXml(presentableName) + "</a>";
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return StringUtil.escapeXml(ocSymbol.getPresentableName());
    }
    
    public static void wrapTemplateArgs(@NotNull final List<OCTypeArgument> list, @NotNull final List<OCSymbolWithQualifiedName> list2, @Nullable final OCFile ocFile, @NotNull final StringBuilder sb) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "templateArguments", "com/jetbrains/cidr/lang/util/OCDocUtil", "wrapTemplateArgs"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "contextNamespace", "com/jetbrains/cidr/lang/util/OCDocUtil", "wrapTemplateArgs"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "answer", "com/jetbrains/cidr/lang/util/OCDocUtil", "wrapTemplateArgs"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final String join = StringUtil.join((Collection)list, ocTypeArgument -> {
            try {
                if (list2 == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "contextNamespace", "com/jetbrains/cidr/lang/util/OCDocUtil", "lambda$wrapTemplateArgs$4"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (ocTypeArgument instanceof OCType) {
                    return getCanonicalName((OCType)ocTypeArgument, list2, ocFile);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (ocTypeArgument instanceof OCExpressionTypeArgument) {
                    return ((OCExpressionTypeArgument)ocTypeArgument).getSymbol().getName();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            return "";
        }, ", ");
        try {
            if (!join.isEmpty()) {
                sb.append(StringUtil.escapeXml("<")).append(join).append(StringUtil.escapeXml(">"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
    }
    
    public static void wrapTemplateParams(@NotNull final List<OCTypeParameterSymbol> list, @NotNull final List<OCSymbolWithQualifiedName> list2, @Nullable final OCFile ocFile, @NotNull final StringBuilder sb) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "templateParams", "com/jetbrains/cidr/lang/util/OCDocUtil", "wrapTemplateParams"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "contextNamespace", "com/jetbrains/cidr/lang/util/OCDocUtil", "wrapTemplateParams"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "answer", "com/jetbrains/cidr/lang/util/OCDocUtil", "wrapTemplateParams"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        sb.append(StringUtil.escapeXml("template<")).append(StringUtil.join((Collection)list, ocTypeParameterSymbol -> {
            try {
                if (list2 == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "contextNamespace", "com/jetbrains/cidr/lang/util/OCDocUtil", "lambda$wrapTemplateParams$5"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final StringBuilder sb = new StringBuilder();
            if (ocTypeParameterSymbol instanceof OCTypeParameterValueSymbol) {
                final String name = ((OCTypeParameterValueSymbol)ocTypeParameterSymbol).getType().getName();
                sb.append(name).append(delimiter(name));
            }
            try {
                if (ocTypeParameterSymbol.isVariadic()) {
                    sb.append("...");
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            sb.append(ocTypeParameterSymbol.getName());
            final OCType defaultValue = ocTypeParameterSymbol.getDefaultValue();
            Label_0177: {
                Label_0155: {
                    try {
                        if (defaultValue == null) {
                            return sb.toString();
                        }
                        final StringBuilder sb2 = sb;
                        final String s = " = ";
                        sb2.append(s);
                        final OCType ocType = defaultValue;
                        final boolean b = ocType instanceof OCType;
                        if (b) {
                            break Label_0155;
                        }
                        break Label_0177;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final StringBuilder sb2 = sb;
                        final String s = " = ";
                        sb2.append(s);
                        final OCType ocType = defaultValue;
                        final boolean b = ocType instanceof OCType;
                        if (b) {
                            sb.append(getCanonicalName(defaultValue, list2, ocFile));
                            return sb.toString();
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                try {
                    if (defaultValue instanceof OCExpressionTypeArgument) {
                        sb.append(((OCExpressionTypeArgument)defaultValue).getSymbol().getName());
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            return sb.toString();
        }, ", ")).append(StringUtil.escapeXml(">"));
    }
    
    @Nullable
    private static String a(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/util/OCDocUtil", "buildLinkSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final StringBuilder sb = new StringBuilder("psi_element://");
        final VirtualFile containingFile = ocSymbol.getContainingFile();
        try {
            if (containingFile != null) {
                sb.append(containingFile.getPath()).append('#').append(ocSymbol.getOffset());
                return sb.toString();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @NotNull
    public static List<OCSymbolWithParent> getSuperSymbols(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCDocUtil", "getSuperSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ArrayList list = new ArrayList<OCSymbolWithParent>();
        if (psiElement instanceof OCSymbolDeclarator) {
            final OCSymbolWithParent symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
            try {
                if (symbol instanceof OCSymbolWithParent) {
                    OCSearchUtil.processMemberAncestors(symbol, (Processor<? super OCSymbolWithParent>)(ocSymbolWithParent -> {
                        list.add(ocSymbolWithParent);
                        return true;
                    }), true);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        ArrayList list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCDocUtil", "getSuperSymbols"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return (List<OCSymbolWithParent>)list2;
    }
    
    static {
        OCDocUtil.POST_FUNC_ATTRIBUTES = EnumSet.of(OCSymbolAttribute.CONST, OCSymbolAttribute.FINAL, OCSymbolAttribute.OVERRIDE, OCSymbolAttribute.DELETE, OCSymbolAttribute.DEFAULT);
        OCDocUtil.SKIP_DECL_ATTRIBUTES = EnumSet.of(OCSymbolAttribute.CONST, OCSymbolAttribute.CONSTEXPR);
        TO_NAME = (ocSymbolWithQualifiedName -> StringUtil.escapeXml(ocSymbolWithQualifiedName.getPresentableName()));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
