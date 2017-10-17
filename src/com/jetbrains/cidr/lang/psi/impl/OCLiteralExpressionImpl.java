// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCKeywordElementType;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCRealType;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCIntType;
import java.util.Collections;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceService;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import java.util.ArrayList;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.util.OCLiteral;
import com.jetbrains.cidr.lang.util.OCCharLiteral;
import com.jetbrains.cidr.lang.util.OCStringLiteral;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.ContributedReferenceHost;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;

public class OCLiteralExpressionImpl extends OCExpressionWithReferenceBase implements OCLiteralExpression, ContributedReferenceHost
{
    private static final Logger LOG;
    
    @NotNull
    private String a(@NotNull final LiteralPresentation literalPresentation) {
        try {
            if (literalPresentation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "presentation", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl", "getLiteralText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCLiteral a = a(this.getNode().getFirstChildNode().getElementType(), this.a());
        if (a instanceof OCStringLiteral) {
            final OCStringLiteral ocStringLiteral = (OCStringLiteral)a;
            String contents = null;
            Label_0104: {
                OCStringLiteral ocStringLiteral2;
                try {
                    ocStringLiteral2 = ocStringLiteral;
                    if (literalPresentation == LiteralPresentation.TEXT_ESCAPED) {
                        final boolean b = true;
                        break Label_0104;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final boolean b = false;
                try {
                    contents = ocStringLiteral2.getContents(b);
                    if (contents == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl", "getLiteralText"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return contents;
        }
        if (a instanceof OCCharLiteral) {
            final OCCharLiteral ocCharLiteral = (OCCharLiteral)a;
            String contents2 = null;
            Label_0179: {
                OCCharLiteral ocCharLiteral2;
                try {
                    ocCharLiteral2 = ocCharLiteral;
                    if (literalPresentation == LiteralPresentation.TEXT_ESCAPED) {
                        final boolean b2 = true;
                        break Label_0179;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                final boolean b2 = false;
                try {
                    contents2 = ocCharLiteral2.getContents(b2);
                    if (contents2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl", "getLiteralText"));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            return contents2;
        }
        String rawLiteralText;
        try {
            rawLiteralText = this.getRawLiteralText();
            if (rawLiteralText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl", "getLiteralText"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return rawLiteralText;
    }
    
    @Nullable
    private static OCLiteral a(@NotNull final IElementType p0, @NotNull final List<String> p1) {
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
        //    18: ldc             "tt"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getLiteral"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "childTextElements"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getLiteral"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    92: if_acmpeq       112
        //    95: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ALL_STRINGS:Lcom/intellij/psi/tree/TokenSet;
        //    98: aload_0        
        //    99: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   102: ifeq            218
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: aload_1        
        //   113: invokeinterface java/util/List.isEmpty:()Z
        //   118: ifeq            136
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   127: athrow         
        //   128: getstatic       com/jetbrains/cidr/lang/util/OCStringLiteral.BAD_LITERAL:Lcom/jetbrains/cidr/lang/util/OCStringLiteral;
        //   131: areturn        
        //   132: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   135: athrow         
        //   136: aload_1        
        //   137: invokeinterface java/util/List.size:()I
        //   142: iconst_1       
        //   143: if_icmpne       164
        //   146: aload_1        
        //   147: iconst_0       
        //   148: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   153: checkcast       Ljava/lang/String;
        //   156: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.parseStringLiteral:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/util/OCStringLiteral;
        //   159: areturn        
        //   160: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: aload_1        
        //   165: invokeinterface java/util/List.size:()I
        //   170: istore_2       
        //   171: iload_2        
        //   172: anewarray       Lcom/jetbrains/cidr/lang/util/OCStringLiteral;
        //   175: astore_3       
        //   176: iconst_0       
        //   177: istore          4
        //   179: iload           4
        //   181: iload_2        
        //   182: if_icmpge       213
        //   185: aload_3        
        //   186: iload           4
        //   188: aload_1        
        //   189: iload           4
        //   191: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   196: checkcast       Ljava/lang/String;
        //   199: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.parseStringLiteral:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/util/OCStringLiteral;
        //   202: aastore        
        //   203: iinc            4, 1
        //   206: goto            179
        //   209: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   212: athrow         
        //   213: aload_3        
        //   214: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.concatStrings:([Lcom/jetbrains/cidr/lang/util/OCStringLiteral;)Lcom/jetbrains/cidr/lang/util/OCStringLiteral;
        //   217: areturn        
        //   218: aload_1        
        //   219: ldc             ""
        //   221: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/util/Collection;Ljava/lang/String;)Ljava/lang/String;
        //   224: astore_2       
        //   225: aload_0        
        //   226: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CHARACTER_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   229: if_acmpne       241
        //   232: aload_2        
        //   233: invokestatic    com/jetbrains/cidr/lang/util/OCStringLiteralUtil.parseCharLiteral:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/util/OCCharLiteral;
        //   236: areturn        
        //   237: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   240: athrow         
        //   241: aload_0        
        //   242: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.INTEGER_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   245: if_acmpne       261
        //   248: new             Lcom/jetbrains/cidr/lang/util/OCIntegerLiteral;
        //   251: dup            
        //   252: aload_2        
        //   253: invokespecial   com/jetbrains/cidr/lang/util/OCIntegerLiteral.<init>:(Ljava/lang/String;)V
        //   256: areturn        
        //   257: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   260: athrow         
        //   261: aload_0        
        //   262: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FLOAT_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   265: if_acmpne       281
        //   268: new             Lcom/jetbrains/cidr/lang/util/OCFloatLiteral;
        //   271: dup            
        //   272: aload_2        
        //   273: invokespecial   com/jetbrains/cidr/lang/util/OCFloatLiteral.<init>:(Ljava/lang/String;)V
        //   276: areturn        
        //   277: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   280: athrow         
        //   281: aconst_null    
        //   282: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/tree/IElementType;Ljava/util/List<Ljava/lang/String;>;)Lcom/jetbrains/cidr/lang/util/OCLiteral;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     105    108    112    Ljava/lang/IllegalArgumentException;
        //  95     121    124    128    Ljava/lang/IllegalArgumentException;
        //  112    132    132    136    Ljava/lang/IllegalArgumentException;
        //  136    160    160    164    Ljava/lang/IllegalArgumentException;
        //  179    209    209    213    Ljava/lang/IllegalArgumentException;
        //  225    237    237    241    Ljava/lang/IllegalArgumentException;
        //  241    257    257    261    Ljava/lang/IllegalArgumentException;
        //  261    277    277    281    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0112:
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
    
    public OCLiteralExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitLiteralExpression(this);
    }
    
    @NotNull
    @Override
    public String getUnescapedLiteralText() {
        String a;
        try {
            a = this.a(LiteralPresentation.TEXT_UNESCAPED);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl", "getUnescapedLiteralText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a;
    }
    
    @NotNull
    @Override
    public String getEscapedLiteralText() {
        String a;
        try {
            a = this.a(LiteralPresentation.TEXT_ESCAPED);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl", "getEscapedLiteralText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a;
    }
    
    @NotNull
    @Override
    public String getRawLiteralText() {
        String join;
        try {
            join = StringUtil.join((Collection)this.a(), "");
            if (join == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl", "getRawLiteralText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return join;
    }
    
    @NotNull
    public List<ASTNode> findStringLiteralNodes() {
        ASTNode astNode = this.getNode().getFirstChildNode();
        final ArrayList<ASTNode> list = new ArrayList<ASTNode>();
        while (true) {
            Label_0044: {
                try {
                    if (astNode == null) {
                        break;
                    }
                    final TokenSet set = OCTokenTypes.ALL_STRINGS;
                    final ASTNode astNode2 = astNode;
                    final IElementType elementType = astNode2.getElementType();
                    final boolean b = set.contains(elementType);
                    if (b) {
                        break Label_0044;
                    }
                    break Label_0044;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final TokenSet set = OCTokenTypes.ALL_STRINGS;
                    final ASTNode astNode2 = astNode;
                    final IElementType elementType = astNode2.getElementType();
                    final boolean b = set.contains(elementType);
                    if (b) {
                        list.add(astNode);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            astNode = astNode.getTreeNext();
        }
        ArrayList<ASTNode> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl", "findStringLiteralNodes"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return list2;
    }
    
    @NotNull
    private List<String> a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.getNode:()Lcom/intellij/lang/ASTNode;
        //     4: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //     9: astore_1       
        //    10: aload_1        
        //    11: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    16: astore_2       
        //    17: aload_2        
        //    18: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    21: if_acmpeq       41
        //    24: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ALL_STRINGS:Lcom/intellij/psi/tree/TokenSet;
        //    27: aload_2        
        //    28: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    31: ifeq            99
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: aload_0        
        //    42: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.findStringLiteralNodes:()Ljava/util/List;
        //    45: invokedynamic   fun:()Lcom/intellij/util/Function;
        //    50: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //    53: dup            
        //    54: ifnonnull       98
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: new             Ljava/lang/IllegalStateException;
        //    67: dup            
        //    68: ldc             "@NotNull method %s.%s must not return null"
        //    70: ldc             2
        //    72: anewarray       Ljava/lang/Object;
        //    75: dup            
        //    76: ldc             0
        //    78: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl"
        //    80: aastore        
        //    81: dup            
        //    82: ldc             1
        //    84: ldc             "getRawLiteralTextElements"
        //    86: aastore        
        //    87: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    90: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    93: athrow         
        //    94: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    97: athrow         
        //    98: areturn        
        //    99: aload_1        
        //   100: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //   105: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   108: dup            
        //   109: ifnonnull       146
        //   112: new             Ljava/lang/IllegalStateException;
        //   115: dup            
        //   116: ldc             "@NotNull method %s.%s must not return null"
        //   118: ldc             2
        //   120: anewarray       Ljava/lang/Object;
        //   123: dup            
        //   124: ldc             0
        //   126: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl"
        //   128: aastore        
        //   129: dup            
        //   130: ldc             1
        //   132: ldc             "getRawLiteralTextElements"
        //   134: aastore        
        //   135: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   138: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   141: athrow         
        //   142: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: areturn        
        //    Signature:
        //  ()Ljava/util/List<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  17     34     37     41     Ljava/lang/IllegalArgumentException;
        //  24     57     60     64     Ljava/lang/IllegalArgumentException;
        //  41     94     94     98     Ljava/lang/IllegalArgumentException;
        //  99     142    142    146    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0041:
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
    public PsiReference[] getReferences() {
        final PsiReferenceService service = PsiReferenceService.getService();
        PsiReference[] array = null;
        if (service != null) {
            array = service.getContributedReferences((PsiElement)this);
        }
        if (array == null) {
            array = PsiReference.EMPTY_ARRAY;
            Logger log = null;
            boolean b = false;
            Label_0041: {
                try {
                    log = OCLiteralExpressionImpl.LOG;
                    if (array.length == 0) {
                        b = true;
                        break Label_0041;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                b = false;
            }
            log.assertTrue(b);
        }
        PsiReference[] array2;
        try {
            array2 = array;
            if (array2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl", "getReferences"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return array2;
    }
    
    @Nullable
    public PsiReference createReference() {
        final PsiReference[] references = this.getReferences();
        try {
            if (references.length == 1) {
                return references[0];
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @NotNull
    public OCType getType(@NotNull final OCResolveContext p0) {
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
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.getNode:()Lcom/intellij/lang/ASTNode;
        //    48: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //    53: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    58: astore_2       
        //    59: aload_0        
        //    60: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.getRawLiteralText:()Ljava/lang/String;
        //    63: astore_3       
        //    64: aload_2        
        //    65: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.INTEGER_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    68: if_acmpne       312
        //    71: ldc             "0"
        //    73: aload_3        
        //    74: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    77: ifne            103
        //    80: goto            87
        //    83: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    86: athrow         
        //    87: ldc             "1"
        //    89: aload_3        
        //    90: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    93: ifeq            312
        //    96: goto            103
        //    99: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: aload_0        
        //   104: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRangeInMacroCall:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/parser/OCMacroRange;
        //   107: astore          4
        //   109: aload           4
        //   111: ifnull          312
        //   114: aload           4
        //   116: invokevirtual   com/jetbrains/cidr/lang/parser/OCMacroRange.getMacroCall:()Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   119: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getText:()Ljava/lang/String;
        //   124: astore          5
        //   126: aload           5
        //   128: ldc             "TRUE"
        //   130: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   133: ifne            187
        //   136: aload           5
        //   138: ldc             "FALSE"
        //   140: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   143: ifne            187
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: aload           5
        //   155: ldc             "YES"
        //   157: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   160: ifne            187
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   169: athrow         
        //   170: aload           5
        //   172: ldc             "NO"
        //   174: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   177: ifeq            236
        //   180: goto            187
        //   183: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   186: athrow         
        //   187: getstatic       com/jetbrains/cidr/lang/types/OCIntType.BOOL:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   190: dup            
        //   191: ifnonnull       235
        //   194: goto            201
        //   197: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   200: athrow         
        //   201: new             Ljava/lang/IllegalStateException;
        //   204: dup            
        //   205: ldc             "@NotNull method %s.%s must not return null"
        //   207: ldc             2
        //   209: anewarray       Ljava/lang/Object;
        //   212: dup            
        //   213: ldc             0
        //   215: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl"
        //   217: aastore        
        //   218: dup            
        //   219: ldc             1
        //   221: ldc             "getType"
        //   223: aastore        
        //   224: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   227: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   230: athrow         
        //   231: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   234: athrow         
        //   235: areturn        
        //   236: aload           5
        //   238: ldc             "true"
        //   240: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   243: ifne            263
        //   246: aload           5
        //   248: ldc             "false"
        //   250: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   253: ifeq            312
        //   256: goto            263
        //   259: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   262: athrow         
        //   263: getstatic       com/jetbrains/cidr/lang/types/OCIntType.BOOL_NATIVE:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   266: dup            
        //   267: ifnonnull       311
        //   270: goto            277
        //   273: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: new             Ljava/lang/IllegalStateException;
        //   280: dup            
        //   281: ldc             "@NotNull method %s.%s must not return null"
        //   283: ldc             2
        //   285: anewarray       Ljava/lang/Object;
        //   288: dup            
        //   289: ldc             0
        //   291: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl"
        //   293: aastore        
        //   294: dup            
        //   295: ldc             1
        //   297: ldc             "getType"
        //   299: aastore        
        //   300: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   303: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   306: athrow         
        //   307: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   310: athrow         
        //   311: areturn        
        //   312: aload_2        
        //   313: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.NS_ARRAY_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   316: if_acmpne       373
        //   319: ldc             "NSArray"
        //   321: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.fromText:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   324: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   327: dup            
        //   328: ifnonnull       372
        //   331: goto            338
        //   334: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   337: athrow         
        //   338: new             Ljava/lang/IllegalStateException;
        //   341: dup            
        //   342: ldc             "@NotNull method %s.%s must not return null"
        //   344: ldc             2
        //   346: anewarray       Ljava/lang/Object;
        //   349: dup            
        //   350: ldc             0
        //   352: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl"
        //   354: aastore        
        //   355: dup            
        //   356: ldc             1
        //   358: ldc             "getType"
        //   360: aastore        
        //   361: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   364: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   367: athrow         
        //   368: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   371: athrow         
        //   372: areturn        
        //   373: aload_2        
        //   374: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.NS_DICTIONARY_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   377: if_acmpne       434
        //   380: ldc             "NSDictionary"
        //   382: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.fromText:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   385: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   388: dup            
        //   389: ifnonnull       433
        //   392: goto            399
        //   395: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   398: athrow         
        //   399: new             Ljava/lang/IllegalStateException;
        //   402: dup            
        //   403: ldc             "@NotNull method %s.%s must not return null"
        //   405: ldc             2
        //   407: anewarray       Ljava/lang/Object;
        //   410: dup            
        //   411: ldc             0
        //   413: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl"
        //   415: aastore        
        //   416: dup            
        //   417: ldc             1
        //   419: ldc             "getType"
        //   421: aastore        
        //   422: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   425: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   428: athrow         
        //   429: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   432: athrow         
        //   433: areturn        
        //   434: aload_0        
        //   435: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.isNSNumberLiteral:()Z
        //   438: ifeq            495
        //   441: ldc             "NSNumber"
        //   443: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.fromText:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   446: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   449: dup            
        //   450: ifnonnull       494
        //   453: goto            460
        //   456: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   459: athrow         
        //   460: new             Ljava/lang/IllegalStateException;
        //   463: dup            
        //   464: ldc             "@NotNull method %s.%s must not return null"
        //   466: ldc             2
        //   468: anewarray       Ljava/lang/Object;
        //   471: dup            
        //   472: ldc             0
        //   474: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl"
        //   476: aastore        
        //   477: dup            
        //   478: ldc             1
        //   480: ldc             "getType"
        //   482: aastore        
        //   483: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   486: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   489: athrow         
        //   490: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   493: athrow         
        //   494: areturn        
        //   495: aload_0        
        //   496: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.isNSStringLiteral:()Z
        //   499: ifeq            556
        //   502: ldc             "NSString"
        //   504: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.fromText:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   507: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   510: dup            
        //   511: ifnonnull       555
        //   514: goto            521
        //   517: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   520: athrow         
        //   521: new             Ljava/lang/IllegalStateException;
        //   524: dup            
        //   525: ldc             "@NotNull method %s.%s must not return null"
        //   527: ldc             2
        //   529: anewarray       Ljava/lang/Object;
        //   532: dup            
        //   533: ldc             0
        //   535: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl"
        //   537: aastore        
        //   538: dup            
        //   539: ldc             1
        //   541: ldc             "getType"
        //   543: aastore        
        //   544: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   547: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   550: athrow         
        //   551: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   554: athrow         
        //   555: areturn        
        //   556: aload_2        
        //   557: aload_0        
        //   558: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   561: aload_2        
        //   562: aload_0        
        //   563: invokespecial   com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:()Ljava/util/List;
        //   566: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Lcom/intellij/psi/tree/IElementType;Ljava/util/List;)Lcom/jetbrains/cidr/lang/util/OCLiteral;
        //   569: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Lcom/intellij/psi/tree/IElementType;Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/util/OCLiteral;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   572: dup            
        //   573: ifnonnull       610
        //   576: new             Ljava/lang/IllegalStateException;
        //   579: dup            
        //   580: ldc             "@NotNull method %s.%s must not return null"
        //   582: ldc             2
        //   584: anewarray       Ljava/lang/Object;
        //   587: dup            
        //   588: ldc             0
        //   590: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl"
        //   592: aastore        
        //   593: dup            
        //   594: ldc             1
        //   596: ldc             "getType"
        //   598: aastore        
        //   599: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   602: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   605: athrow         
        //   606: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   609: athrow         
        //   610: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  64     80     83     87     Ljava/lang/IllegalArgumentException;
        //  71     96     99     103    Ljava/lang/IllegalArgumentException;
        //  126    146    149    153    Ljava/lang/IllegalArgumentException;
        //  136    163    166    170    Ljava/lang/IllegalArgumentException;
        //  153    180    183    187    Ljava/lang/IllegalArgumentException;
        //  170    194    197    201    Ljava/lang/IllegalArgumentException;
        //  187    231    231    235    Ljava/lang/IllegalArgumentException;
        //  236    256    259    263    Ljava/lang/IllegalArgumentException;
        //  246    270    273    277    Ljava/lang/IllegalArgumentException;
        //  263    307    307    311    Ljava/lang/IllegalArgumentException;
        //  312    331    334    338    Ljava/lang/IllegalArgumentException;
        //  319    368    368    372    Ljava/lang/IllegalArgumentException;
        //  373    392    395    399    Ljava/lang/IllegalArgumentException;
        //  380    429    429    433    Ljava/lang/IllegalArgumentException;
        //  434    453    456    460    Ljava/lang/IllegalArgumentException;
        //  441    490    490    494    Ljava/lang/IllegalArgumentException;
        //  495    514    517    521    Ljava/lang/IllegalArgumentException;
        //  502    551    551    555    Ljava/lang/IllegalArgumentException;
        //  556    606    606    610    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0153:
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
    
    public static OCType getLiteralType(final IElementType elementType, final String s, @NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl", "getLiteralType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a(elementType, psiFile, a(elementType, Collections.singletonList(s)));
    }
    
    private static OCType a(final IElementType elementType, @Nullable final PsiFile psiFile, @Nullable final OCLiteral ocLiteral) {
        Label_0029: {
            Label_0021: {
                try {
                    if (elementType == OCTokenTypes.TRUE_CPP_KEYWORD) {
                        break Label_0021;
                    }
                    final IElementType elementType2 = elementType;
                    final OCKeywordElementType ocKeywordElementType = OCTokenTypes.FALSE_CPP_KEYWORD;
                    if (elementType2 == ocKeywordElementType) {
                        break Label_0021;
                    }
                    break Label_0029;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final IElementType elementType2 = elementType;
                    final OCKeywordElementType ocKeywordElementType = OCTokenTypes.FALSE_CPP_KEYWORD;
                    if (elementType2 == ocKeywordElementType) {
                        return OCIntType.BOOL_NATIVE;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            try {
                if (elementType == OCTokenTypes.NULL_CPP_KEYWORD) {
                    return OCPointerType.NULLPTR_T;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        try {
            if (ocLiteral != null) {
                return ocLiteral.getType(psiFile, OCCodeInsightUtil.isInPlainOldC((PsiElement)psiFile));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (elementType == OCTokenTypes.WRONG_INTEGER_LITERAL) {
                return OCIntType.INT;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (elementType == OCTokenTypes.WRONG_FLOAT_LITERAL) {
                return OCRealType.FLOAT;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (elementType == OCTokenTypes.TEMPLATE_START_MARK) {
                return OCUnknownType.INSTANCE;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        Label_0138: {
            Label_0130: {
                try {
                    if (elementType == OCTokenTypes.FAKE_FALSE) {
                        break Label_0130;
                    }
                    final IElementType elementType3 = elementType;
                    final OCElementType ocElementType = OCTokenTypes.FAKE_TRUE;
                    if (elementType3 == ocElementType) {
                        break Label_0130;
                    }
                    break Label_0138;
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
                try {
                    final IElementType elementType3 = elementType;
                    final OCElementType ocElementType = OCTokenTypes.FAKE_TRUE;
                    if (elementType3 == ocElementType) {
                        return OCIntType.INT;
                    }
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
            }
            try {
                if (elementType == OCTokenTypes.__NULL_KEYWORD) {
                    return OCPointerType.to(OCVoidType.instance());
                }
            }
            catch (IllegalArgumentException ex10) {
                throw a(ex10);
            }
        }
        return OCUnknownType.INSTANCE;
    }
    
    @Override
    public boolean isNSNumberLiteral() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.getNode:()Lcom/intellij/lang/ASTNode;
        //     4: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //     9: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    14: astore_1       
        //    15: aload_1        
        //    16: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    19: if_acmpne       82
        //    22: aload_0        
        //    23: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.getNode:()Lcom/intellij/lang/ASTNode;
        //    26: invokeinterface com/intellij/lang/ASTNode.getLastChildNode:()Lcom/intellij/lang/ASTNode;
        //    31: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    36: astore_2       
        //    37: aload_2        
        //    38: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.INTEGER_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    41: if_acmpeq       72
        //    44: aload_2        
        //    45: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FLOAT_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    48: if_acmpeq       72
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: aload_2        
        //    59: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CHARACTER_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    62: if_acmpne       80
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    71: athrow         
        //    72: iconst_1       
        //    73: goto            81
        //    76: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    79: athrow         
        //    80: iconst_0       
        //    81: ireturn        
        //    82: iconst_0       
        //    83: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  37     51     54     58     Ljava/lang/IllegalArgumentException;
        //  44     65     68     72     Ljava/lang/IllegalArgumentException;
        //  58     76     76     80     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0058:
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
    
    @Override
    public boolean isNSStringLiteral() {
        if (this.getNode().getFirstChildNode().getElementType() == OCTokenTypes.AT) {
            final IElementType elementType = this.getNode().getLastChildNode().getElementType();
            try {
                if (elementType == OCTokenTypes.STRING_LITERAL) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return false;
        }
        return false;
    }
    
    @Override
    public boolean isStringLiteral() {
        try {
            if (!this.findStringLiteralNodes().isEmpty()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    static {
        LOG = Logger.getInstance("#com.jetbrains.cidr.lang.psi.impl.OCLiteralExpressionImpl");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum LiteralPresentation
    {
        TEXT_ESCAPED, 
        TEXT_UNESCAPED;
    }
}
