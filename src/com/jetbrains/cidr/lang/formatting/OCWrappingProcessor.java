// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.formatting.WrapType;
import com.intellij.psi.formatter.WrappingUtil;
import com.intellij.formatting.alignment.AlignmentInColumnsHelper;
import org.jetbrains.annotations.Nullable;
import com.intellij.formatting.Alignment;
import com.intellij.openapi.util.Computable;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import com.intellij.formatting.Wrap;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.formatting.alignment.AlignmentInColumnsConfig;
import com.intellij.openapi.util.Key;

class OCWrappingProcessor extends OCFormatProcessor
{
    protected static final Key<Boolean> IS_TABLE;
    private static final AlignmentInColumnsConfig VARS_ALIGNMENT_CONFIG;
    public static final OCElementType CHAINED_CALL_PSEUDOTYPE;
    public static final OCElementType BINARY_EXPRESSION_PSEUDOTYPE;
    private static final String KEY_METHOD_ARGUMENTS = "method arguments";
    private static final String KEY_METHOD_FIRST_ARGUMENT = "first method argument";
    private static final String KEY_METHOD_ARGUMENT_VALUES = "method argument values";
    private static final String KEY_METHOD_CHAIN = "method argument alignment";
    private static final String KEY_CALL_ARGUMENT_COLUMNS = "call argument columns";
    private static final String KEY_PARAMETER_COLUMNS = "parameter columns";
    private static final String KEY_ASSIGNMENT_WRAP = "assignment wrap";
    private static final String KEY_ASSIGNMENT_ALIGNMENT = "assignment alignment";
    private static final String KEY_GROUP_ASSIGNMENT = "group assignment";
    private static final String KEY_GROUP_VARIABLE_NAME = "group variable name";
    private static final String KEY_GROUP_INIT_LIST = "group initialization list";
    private static final String KEY_GROUP_DICTIONARY_PAIRS = "group dictionary pairs";
    private static final String KEY_TERNARY_ALIGNMENT = "ternary alignment";
    private static final String KEY_TERNARY_OPERATOR_ALIGNMENT = "ternary operator alignment";
    private static final String KEY_TERNARY_WRAP = "ternary wrap";
    private static final String KEY_TERNARY_OPERATOR_WRAP = "ternary operator wrap";
    private static final String KEY_TEMPLATE_DECLARATION_WRAP = "template declaration wrap";
    private static final String KEY_NEVER_WRAP = "Never wrap";
    private static final String KEY_ALWAYS_WRAP = "Always wrap";
    private static final String KEY_IF_LONG_WRAP = "If long wrap";
    private static final String KEY_SHIFT_OPERATION = "Shift operations";
    private static final TokenSet COMMENTS_OR_EXPRESSION;
    private static final TokenSet TYPE_OR_EXPRESSION;
    private static final TokenSet LAMBDA_LIST_CAPTURE;
    private static final TokenSet MACRO_ARG_OR_EXPRESSION;
    private final OCLocalFormatterData myData;
    private ASTNode childNode;
    private IElementType childType;
    
    OCWrappingProcessor(final CommonCodeStyleSettings commonCodeStyleSettings, final OCCodeStyleSettings ocCodeStyleSettings, final ASTNode astNode, final OCLocalFormatterData myData) {
        super(commonCodeStyleSettings, ocCodeStyleSettings, astNode);
        this.myData = myData;
    }
    
    public Wrap getTemplateWrap(final IElementType elementType) {
        OCLocalFormatterData ocLocalFormatterData = null;
        Label_0025: {
            try {
                if (elementType == null) {
                    ocLocalFormatterData = this.myData;
                    break Label_0025;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            ocLocalFormatterData = this.myData.getAncestor(elementType);
        }
        final OCLocalFormatterData ocLocalFormatterData2 = ocLocalFormatterData;
        if (ocLocalFormatterData2 != null) {
            final OCFormatterInfo value = ocLocalFormatterData2.get("template declaration wrap");
            try {
                if (value != null) {
                    return value.wrap;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return null;
    }
    
    public OCFormatterInfo calculate(final ASTNode childNode, final IElementType childType) {
        this.childNode = childNode;
        this.childType = childType;
        return this.k();
    }
    
    private boolean a(@NotNull final IElementType p0, final boolean p1) {
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
        //    18: ldc             "elementType"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "shouldAlignAsElementOrComma"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: iload_2        
        //    45: ifeq            78
        //    48: aload_0        
        //    49: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //    52: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    55: if_acmpne       78
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_0        
        //    66: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //    69: aload_1        
        //    70: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isPrecededBy:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Z
        //    73: ireturn        
        //    74: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    77: athrow         
        //    78: aload_0        
        //    79: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //    82: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    85: if_acmpeq       103
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //    92: aload_1        
        //    93: if_acmpne       142
        //    96: goto            103
        //    99: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: iload_2        
        //   104: ifeq            134
        //   107: goto            114
        //   110: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   113: athrow         
        //   114: aload_0        
        //   115: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //   118: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   121: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isPrecededBy:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Z
        //   124: ifne            142
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: iconst_1       
        //   135: goto            143
        //   138: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   141: athrow         
        //   142: iconst_0       
        //   143: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     58     61     65     Ljava/lang/IllegalArgumentException;
        //  48     74     74     78     Ljava/lang/IllegalArgumentException;
        //  78     96     99     103    Ljava/lang/IllegalArgumentException;
        //  88     107    110    114    Ljava/lang/IllegalArgumentException;
        //  103    127    130    134    Ljava/lang/IllegalArgumentException;
        //  114    138    138    142    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0103:
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
    
    private boolean a(@NotNull final TokenSet p0, final boolean p1) {
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
        //    18: ldc             "elementTypes"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "shouldAlignAsElementOrComma"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: iload_2        
        //    45: ifeq            78
        //    48: aload_0        
        //    49: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //    52: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    55: if_acmpne       78
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_0        
        //    66: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //    69: aload_1        
        //    70: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isPrecededBy:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/TokenSet;)Z
        //    73: ireturn        
        //    74: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    77: athrow         
        //    78: aload_1        
        //    79: aload_0        
        //    80: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //    83: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    86: ifeq            113
        //    89: aload_0        
        //    90: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //    93: aload_1        
        //    94: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isPrecededBy:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/TokenSet;)Z
        //    97: ifeq            113
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: iconst_0       
        //   108: ireturn        
        //   109: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: aload_0        
        //   114: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //   117: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   120: if_acmpeq       141
        //   123: aload_1        
        //   124: aload_0        
        //   125: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //   128: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   131: ifeq            180
        //   134: goto            141
        //   137: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   140: athrow         
        //   141: iload_2        
        //   142: ifeq            172
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   151: athrow         
        //   152: aload_0        
        //   153: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //   156: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   159: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isPrecededBy:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Z
        //   162: ifne            180
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: iconst_1       
        //   173: goto            181
        //   176: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   179: athrow         
        //   180: iconst_0       
        //   181: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     58     61     65     Ljava/lang/IllegalArgumentException;
        //  48     74     74     78     Ljava/lang/IllegalArgumentException;
        //  78     100    103    107    Ljava/lang/IllegalArgumentException;
        //  89     109    109    113    Ljava/lang/IllegalArgumentException;
        //  113    134    137    141    Ljava/lang/IllegalArgumentException;
        //  123    145    148    152    Ljava/lang/IllegalArgumentException;
        //  141    165    168    172    Ljava/lang/IllegalArgumentException;
        //  152    176    176    180    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0141:
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
    private static Pair<Object, ASTNode> a(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor", "getAssignGroupKey"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        int n = -1;
        ASTNode astNode2 = astNode;
        for (ASTNode treeParent = astNode; treeParent != null; treeParent = treeParent.getTreeParent()) {
            final IElementType elementType2;
            final IElementType elementType = elementType2 = OCElementUtil.getElementType(treeParent);
            Label_0083: {
                try {
                    if (a(elementType)) {
                        break;
                    }
                    final IElementType elementType3 = elementType2;
                    final OCElementType ocElementType = OCElementTypes.ASSIGNMENT_EXPRESSION;
                    if (elementType3 != ocElementType) {
                        break Label_0083;
                    }
                    break Label_0083;
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                try {
                    final IElementType elementType3 = elementType2;
                    final OCElementType ocElementType = OCElementTypes.ASSIGNMENT_EXPRESSION;
                    if (elementType3 != ocElementType) {
                        if (elementType2 != OCElementTypes.DECLARATOR) {
                            continue;
                        }
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
            }
            astNode2 = treeParent;
            ++n;
        }
        if (n < 0) {
            n = 0;
        }
        Pair pair;
        try {
            pair = new Pair((Object)new Pair((Object)"group assignment", (Object)n), (Object)astNode2);
            if (pair == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor", "getAssignGroupKey"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return (Pair<Object, ASTNode>)pair;
    }
    
    @NotNull
    private static String b(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor", "getInitListGroupKey"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String string = "";
        int n = 0;
        ASTNode treeParent = astNode;
        while (true) {
            try {
                if (treeParent == null || OCElementUtil.getElementType(treeParent.getTreeParent()) != OCElementTypes.COMPOUND_INITIALIZER) {
                    break;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            int n2 = 0;
            for (ASTNode previousNonWhitespaceOrCommentSibling = treeParent; previousNonWhitespaceOrCommentSibling != null; previousNonWhitespaceOrCommentSibling = OCFormatterUtil.getPreviousNonWhitespaceOrCommentSibling(previousNonWhitespaceOrCommentSibling)) {
                ++n2;
            }
            if (n > 0) {
                string = new StringBuffer(string.length() + 32).append('/').append(n).append(string).toString();
            }
            n = n2;
            treeParent = treeParent.getTreeParent();
        }
        String string2;
        try {
            string2 = "group initialization list" + string;
            if (string2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor", "getInitListGroupKey"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return string2;
    }
    
    @NotNull
    private OCFormatterInfo k() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //     4: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.DO_NOT_ADD_BREAKS:Z
        //     7: ifeq            60
        //    10: aload_0        
        //    11: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getJoinFormatting:()Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //    14: dup            
        //    15: ifnonnull       59
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    24: athrow         
        //    25: new             Ljava/lang/IllegalStateException;
        //    28: dup            
        //    29: ldc             "@NotNull method %s.%s must not return null"
        //    31: ldc             2
        //    33: anewarray       Ljava/lang/Object;
        //    36: dup            
        //    37: ldc             0
        //    39: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //    41: aastore        
        //    42: dup            
        //    43: ldc             1
        //    45: ldc             "doCalculate"
        //    47: aastore        
        //    48: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    51: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    54: athrow         
        //    55: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    58: athrow         
        //    59: areturn        
        //    60: aload_0        
        //    61: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //    64: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD_SELECTOR_PART:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    67: if_acmpne       137
        //    70: aload_0        
        //    71: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD_SELECTOR_PART:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    74: aload_0        
        //    75: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //    78: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.METHOD_PARAMETERS_WRAP:I
        //    81: aload_0        
        //    82: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //    85: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.METHOD_PARAMETERS_ALIGN_MULTILINE:Z
        //    88: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapAndAlign:(Ljava/lang/Object;IZ)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //    91: dup            
        //    92: ifnonnull       136
        //    95: goto            102
        //    98: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: new             Ljava/lang/IllegalStateException;
        //   105: dup            
        //   106: ldc             "@NotNull method %s.%s must not return null"
        //   108: ldc             2
        //   110: anewarray       Ljava/lang/Object;
        //   113: dup            
        //   114: ldc             0
        //   116: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //   118: aastore        
        //   119: dup            
        //   120: ldc             1
        //   122: ldc             "doCalculate"
        //   124: aastore        
        //   125: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   128: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   131: athrow         
        //   132: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   135: athrow         
        //   136: areturn        
        //   137: aload_0        
        //   138: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   141: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   144: if_acmpne       315
        //   147: aload_0        
        //   148: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   151: aload_0        
        //   152: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   155: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_PARAMETERS_COMMA_ON_NEXT_LINE:Z
        //   158: invokespecial   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.a:(Lcom/intellij/psi/tree/IElementType;Z)Z
        //   161: ifeq            238
        //   164: goto            171
        //   167: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   170: athrow         
        //   171: aload_0        
        //   172: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   175: aload_0        
        //   176: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   179: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_PARAMETERS_WRAP:I
        //   182: aload_0        
        //   183: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   186: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_PARAMETERS_ALIGN_MULTILINE:Z
        //   189: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapAndAlign:(Ljava/lang/Object;IZ)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //   192: dup            
        //   193: ifnonnull       237
        //   196: goto            203
        //   199: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: new             Ljava/lang/IllegalStateException;
        //   206: dup            
        //   207: ldc             "@NotNull method %s.%s must not return null"
        //   209: ldc             2
        //   211: anewarray       Ljava/lang/Object;
        //   214: dup            
        //   215: ldc             0
        //   217: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //   219: aastore        
        //   220: dup            
        //   221: ldc             1
        //   223: ldc             "doCalculate"
        //   225: aastore        
        //   226: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   229: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   232: athrow         
        //   233: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   236: athrow         
        //   237: areturn        
        //   238: aload_0        
        //   239: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //   242: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   245: if_acmpeq       265
        //   248: aload_0        
        //   249: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //   252: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   255: if_acmpne       315
        //   258: goto            265
        //   261: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   264: athrow         
        //   265: aload_0        
        //   266: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getJoinFormatting:()Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //   269: dup            
        //   270: ifnonnull       314
        //   273: goto            280
        //   276: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   279: athrow         
        //   280: new             Ljava/lang/IllegalStateException;
        //   283: dup            
        //   284: ldc             "@NotNull method %s.%s must not return null"
        //   286: ldc             2
        //   288: anewarray       Ljava/lang/Object;
        //   291: dup            
        //   292: ldc             0
        //   294: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //   296: aastore        
        //   297: dup            
        //   298: ldc             1
        //   300: ldc             "doCalculate"
        //   302: aastore        
        //   303: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   306: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   309: athrow         
        //   310: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   313: athrow         
        //   314: areturn        
        //   315: aload_0        
        //   316: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   319: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   322: if_acmpeq       342
        //   325: aload_0        
        //   326: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   329: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   332: if_acmpne       513
        //   335: goto            342
        //   338: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   341: athrow         
        //   342: aload_0        
        //   343: getstatic       com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.MACRO_ARG_OR_EXPRESSION:Lcom/intellij/psi/tree/TokenSet;
        //   346: aload_0        
        //   347: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   350: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_COMMA_ON_NEXT_LINE:Z
        //   353: invokespecial   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.a:(Lcom/intellij/psi/tree/TokenSet;Z)Z
        //   356: ifeq            433
        //   359: goto            366
        //   362: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   365: athrow         
        //   366: aload_0        
        //   367: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   370: aload_0        
        //   371: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   374: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_WRAP:I
        //   377: aload_0        
        //   378: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   381: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_ALIGN_MULTILINE:Z
        //   384: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapAndAlign:(Ljava/lang/Object;IZ)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //   387: dup            
        //   388: ifnonnull       432
        //   391: goto            398
        //   394: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   397: athrow         
        //   398: new             Ljava/lang/IllegalStateException;
        //   401: dup            
        //   402: ldc             "@NotNull method %s.%s must not return null"
        //   404: ldc             2
        //   406: anewarray       Ljava/lang/Object;
        //   409: dup            
        //   410: ldc             0
        //   412: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //   414: aastore        
        //   415: dup            
        //   416: ldc             1
        //   418: ldc             "doCalculate"
        //   420: aastore        
        //   421: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   424: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   427: athrow         
        //   428: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   431: athrow         
        //   432: areturn        
        //   433: aload_0        
        //   434: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //   437: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   440: if_acmpeq       463
        //   443: getstatic       com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.MACRO_ARG_OR_EXPRESSION:Lcom/intellij/psi/tree/TokenSet;
        //   446: aload_0        
        //   447: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //   450: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   453: ifeq            513
        //   456: goto            463
        //   459: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   462: athrow         
        //   463: aload_0        
        //   464: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getJoinFormatting:()Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //   467: dup            
        //   468: ifnonnull       512
        //   471: goto            478
        //   474: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   477: athrow         
        //   478: new             Ljava/lang/IllegalStateException;
        //   481: dup            
        //   482: ldc             "@NotNull method %s.%s must not return null"
        //   484: ldc             2
        //   486: anewarray       Ljava/lang/Object;
        //   489: dup            
        //   490: ldc             0
        //   492: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //   494: aastore        
        //   495: dup            
        //   496: ldc             1
        //   498: ldc             "doCalculate"
        //   500: aastore        
        //   501: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   504: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   507: athrow         
        //   508: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   511: athrow         
        //   512: areturn        
        //   513: aload_0        
        //   514: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   517: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   520: if_acmpne       691
        //   523: aload_0        
        //   524: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TYPE_PARAMETER_DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   527: aload_0        
        //   528: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   531: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_PARAMETERS_COMMA_ON_NEXT_LINE:Z
        //   534: invokespecial   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.a:(Lcom/intellij/psi/tree/IElementType;Z)Z
        //   537: ifeq            614
        //   540: goto            547
        //   543: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   546: athrow         
        //   547: aload_0        
        //   548: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   551: aload_0        
        //   552: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   555: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_PARAMETERS_WRAP:I
        //   558: aload_0        
        //   559: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   562: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_PARAMETERS_ALIGN_MULTILINE:Z
        //   565: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapAndAlign:(Ljava/lang/Object;IZ)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //   568: dup            
        //   569: ifnonnull       613
        //   572: goto            579
        //   575: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   578: athrow         
        //   579: new             Ljava/lang/IllegalStateException;
        //   582: dup            
        //   583: ldc             "@NotNull method %s.%s must not return null"
        //   585: ldc             2
        //   587: anewarray       Ljava/lang/Object;
        //   590: dup            
        //   591: ldc             0
        //   593: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //   595: aastore        
        //   596: dup            
        //   597: ldc             1
        //   599: ldc             "doCalculate"
        //   601: aastore        
        //   602: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   605: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   608: athrow         
        //   609: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   612: athrow         
        //   613: areturn        
        //   614: aload_0        
        //   615: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //   618: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   621: if_acmpeq       641
        //   624: aload_0        
        //   625: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //   628: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TYPE_PARAMETER_DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   631: if_acmpne       691
        //   634: goto            641
        //   637: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   640: athrow         
        //   641: aload_0        
        //   642: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getJoinFormatting:()Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //   645: dup            
        //   646: ifnonnull       690
        //   649: goto            656
        //   652: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   655: athrow         
        //   656: new             Ljava/lang/IllegalStateException;
        //   659: dup            
        //   660: ldc             "@NotNull method %s.%s must not return null"
        //   662: ldc             2
        //   664: anewarray       Ljava/lang/Object;
        //   667: dup            
        //   668: ldc             0
        //   670: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //   672: aastore        
        //   673: dup            
        //   674: ldc             1
        //   676: ldc             "doCalculate"
        //   678: aastore        
        //   679: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   682: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   685: athrow         
        //   686: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   689: athrow         
        //   690: areturn        
        //   691: aload_0        
        //   692: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   695: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TEMPLATE_ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   698: if_acmpne       872
        //   701: aload_0        
        //   702: getstatic       com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.TYPE_OR_EXPRESSION:Lcom/intellij/psi/tree/TokenSet;
        //   705: aload_0        
        //   706: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   709: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_CALL_ARGUMENTS_COMMA_ON_NEXT_LINE:Z
        //   712: invokespecial   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.a:(Lcom/intellij/psi/tree/TokenSet;Z)Z
        //   715: ifeq            792
        //   718: goto            725
        //   721: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   724: athrow         
        //   725: aload_0        
        //   726: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TEMPLATE_ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   729: aload_0        
        //   730: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   733: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_CALL_ARGUMENTS_WRAP:I
        //   736: aload_0        
        //   737: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   740: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_CALL_ARGUMENTS_ALIGN_MULTILINE:Z
        //   743: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapAndAlign:(Ljava/lang/Object;IZ)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //   746: dup            
        //   747: ifnonnull       791
        //   750: goto            757
        //   753: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   756: athrow         
        //   757: new             Ljava/lang/IllegalStateException;
        //   760: dup            
        //   761: ldc             "@NotNull method %s.%s must not return null"
        //   763: ldc             2
        //   765: anewarray       Ljava/lang/Object;
        //   768: dup            
        //   769: ldc             0
        //   771: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //   773: aastore        
        //   774: dup            
        //   775: ldc             1
        //   777: ldc             "doCalculate"
        //   779: aastore        
        //   780: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   783: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   786: athrow         
        //   787: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   790: athrow         
        //   791: areturn        
        //   792: aload_0        
        //   793: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //   796: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   799: if_acmpeq       822
        //   802: getstatic       com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.TYPE_OR_EXPRESSION:Lcom/intellij/psi/tree/TokenSet;
        //   805: aload_0        
        //   806: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //   809: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   812: ifeq            872
        //   815: goto            822
        //   818: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   821: athrow         
        //   822: aload_0        
        //   823: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getJoinFormatting:()Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //   826: dup            
        //   827: ifnonnull       871
        //   830: goto            837
        //   833: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   836: athrow         
        //   837: new             Ljava/lang/IllegalStateException;
        //   840: dup            
        //   841: ldc             "@NotNull method %s.%s must not return null"
        //   843: ldc             2
        //   845: anewarray       Ljava/lang/Object;
        //   848: dup            
        //   849: ldc             0
        //   851: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //   853: aastore        
        //   854: dup            
        //   855: ldc             1
        //   857: ldc             "doCalculate"
        //   859: aastore        
        //   860: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   863: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   866: athrow         
        //   867: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   870: athrow         
        //   871: areturn        
        //   872: aload_0        
        //   873: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   876: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_LAMBDA_INTRODUCER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   879: if_acmpne       1053
        //   882: aload_0        
        //   883: getstatic       com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.LAMBDA_LIST_CAPTURE:Lcom/intellij/psi/tree/TokenSet;
        //   886: aload_0        
        //   887: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   890: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.LAMBDA_CAPTURE_LIST_COMMA_ON_NEXT_LINE:Z
        //   893: invokespecial   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.a:(Lcom/intellij/psi/tree/TokenSet;Z)Z
        //   896: ifeq            973
        //   899: goto            906
        //   902: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   905: athrow         
        //   906: aload_0        
        //   907: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_LAMBDA_INTRODUCER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   910: aload_0        
        //   911: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   914: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.LAMBDA_CAPTURE_LIST_WRAP:I
        //   917: aload_0        
        //   918: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   921: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.LAMBDA_CAPTURE_LIST_ALIGN_MULTILINE:Z
        //   924: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapAndAlign:(Ljava/lang/Object;IZ)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //   927: dup            
        //   928: ifnonnull       972
        //   931: goto            938
        //   934: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   937: athrow         
        //   938: new             Ljava/lang/IllegalStateException;
        //   941: dup            
        //   942: ldc             "@NotNull method %s.%s must not return null"
        //   944: ldc             2
        //   946: anewarray       Ljava/lang/Object;
        //   949: dup            
        //   950: ldc             0
        //   952: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //   954: aastore        
        //   955: dup            
        //   956: ldc             1
        //   958: ldc             "doCalculate"
        //   960: aastore        
        //   961: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   964: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   967: athrow         
        //   968: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   971: athrow         
        //   972: areturn        
        //   973: aload_0        
        //   974: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //   977: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   980: if_acmpeq       1003
        //   983: getstatic       com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.LAMBDA_LIST_CAPTURE:Lcom/intellij/psi/tree/TokenSet;
        //   986: aload_0        
        //   987: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //   990: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   993: ifeq            1053
        //   996: goto            1003
        //   999: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1002: athrow         
        //  1003: aload_0        
        //  1004: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getJoinFormatting:()Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1007: dup            
        //  1008: ifnonnull       1052
        //  1011: goto            1018
        //  1014: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1017: athrow         
        //  1018: new             Ljava/lang/IllegalStateException;
        //  1021: dup            
        //  1022: ldc             "@NotNull method %s.%s must not return null"
        //  1024: ldc             2
        //  1026: anewarray       Ljava/lang/Object;
        //  1029: dup            
        //  1030: ldc             0
        //  1032: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  1034: aastore        
        //  1035: dup            
        //  1036: ldc             1
        //  1038: ldc             "doCalculate"
        //  1040: aastore        
        //  1041: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1044: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1047: athrow         
        //  1048: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1051: athrow         
        //  1052: areturn        
        //  1053: aload_0        
        //  1054: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1057: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_CONSTRUCTOR_INITIALIZATION_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1060: if_acmpeq       1097
        //  1063: aload_0        
        //  1064: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1067: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1070: if_acmpne       1691
        //  1073: goto            1080
        //  1076: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1079: athrow         
        //  1080: aload_0        
        //  1081: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  1084: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1087: if_acmpne       1691
        //  1090: goto            1097
        //  1093: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1096: athrow         
        //  1097: aload_0        
        //  1098: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1101: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1104: if_acmpne       1125
        //  1107: goto            1114
        //  1110: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1113: athrow         
        //  1114: aload_0        
        //  1115: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  1118: goto            1134
        //  1121: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1124: athrow         
        //  1125: aload_0        
        //  1126: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  1129: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.getParent:()Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  1134: astore_1       
        //  1135: aload_1        
        //  1136: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_CONSTRUCTOR_INITIALIZATION_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1139: aload_0        
        //  1140: aload_1        
        //  1141: invokedynamic   compute:(Lcom/jetbrains/cidr/lang/formatting/OCWrappingProcessor;Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;)Lcom/intellij/openapi/util/Computable;
        //  1146: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.get:(Ljava/lang/Object;Lcom/intellij/openapi/util/Computable;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1151: astore_2       
        //  1152: aload_0        
        //  1153: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  1156: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1159: if_acmpne       1336
        //  1162: aload_0        
        //  1163: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  1166: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_NEW_LINE_BEFORE_COLON:I
        //  1169: tableswitch {
        //                0: 1200
        //                1: 1250
        //                2: 1293
        //          default: 1293
        //        }
        //  1196: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1199: athrow         
        //  1200: aload_1        
        //  1201: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getJoinFormatting:(Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1204: dup            
        //  1205: ifnonnull       1249
        //  1208: goto            1215
        //  1211: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1214: athrow         
        //  1215: new             Ljava/lang/IllegalStateException;
        //  1218: dup            
        //  1219: ldc             "@NotNull method %s.%s must not return null"
        //  1221: ldc             2
        //  1223: anewarray       Ljava/lang/Object;
        //  1226: dup            
        //  1227: ldc             0
        //  1229: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  1231: aastore        
        //  1232: dup            
        //  1233: ldc             1
        //  1235: ldc             "doCalculate"
        //  1237: aastore        
        //  1238: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1241: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1244: athrow         
        //  1245: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1248: athrow         
        //  1249: areturn        
        //  1250: aload_1        
        //  1251: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getNewLineFormatting:(Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1254: dup            
        //  1255: ifnonnull       1292
        //  1258: new             Ljava/lang/IllegalStateException;
        //  1261: dup            
        //  1262: ldc             "@NotNull method %s.%s must not return null"
        //  1264: ldc             2
        //  1266: anewarray       Ljava/lang/Object;
        //  1269: dup            
        //  1270: ldc             0
        //  1272: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  1274: aastore        
        //  1275: dup            
        //  1276: ldc             1
        //  1278: ldc             "doCalculate"
        //  1280: aastore        
        //  1281: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1284: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1287: athrow         
        //  1288: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1291: athrow         
        //  1292: areturn        
        //  1293: aload_1        
        //  1294: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getIfLongFormatting:(Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1297: dup            
        //  1298: ifnonnull       1335
        //  1301: new             Ljava/lang/IllegalStateException;
        //  1304: dup            
        //  1305: ldc             "@NotNull method %s.%s must not return null"
        //  1307: ldc             2
        //  1309: anewarray       Ljava/lang/Object;
        //  1312: dup            
        //  1313: ldc             0
        //  1315: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  1317: aastore        
        //  1318: dup            
        //  1319: ldc             1
        //  1321: ldc             "doCalculate"
        //  1323: aastore        
        //  1324: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1327: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1330: athrow         
        //  1331: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1334: athrow         
        //  1335: areturn        
        //  1336: aload_0        
        //  1337: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_CONSTRUCTOR_FIELD_INITIALIZER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1340: aload_0        
        //  1341: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  1344: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_COMMA_ON_NEXT_LINE:Z
        //  1347: invokespecial   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.a:(Lcom/intellij/psi/tree/IElementType;Z)Z
        //  1350: ifeq            1614
        //  1353: aload_0        
        //  1354: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //  1357: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isFirstEssentialChild:(Lcom/intellij/lang/ASTNode;)Z
        //  1360: ifeq            1574
        //  1363: goto            1370
        //  1366: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1369: athrow         
        //  1370: aload_0        
        //  1371: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  1374: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_NEW_LINE_AFTER_COLON:I
        //  1377: tableswitch {
        //                0: 1408
        //                1: 1468
        //                2: 1521
        //          default: 1521
        //        }
        //  1404: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1407: athrow         
        //  1408: aload_1        
        //  1409: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getJoinFormatting:(Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1412: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //  1415: aload_2        
        //  1416: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.alignment:Lcom/intellij/formatting/Alignment;
        //  1419: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1422: dup            
        //  1423: ifnonnull       1467
        //  1426: goto            1433
        //  1429: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1432: athrow         
        //  1433: new             Ljava/lang/IllegalStateException;
        //  1436: dup            
        //  1437: ldc             "@NotNull method %s.%s must not return null"
        //  1439: ldc             2
        //  1441: anewarray       Ljava/lang/Object;
        //  1444: dup            
        //  1445: ldc             0
        //  1447: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  1449: aastore        
        //  1450: dup            
        //  1451: ldc             1
        //  1453: ldc             "doCalculate"
        //  1455: aastore        
        //  1456: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1459: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1462: athrow         
        //  1463: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1466: athrow         
        //  1467: areturn        
        //  1468: aload_1        
        //  1469: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getNewLineFormatting:(Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1472: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //  1475: aload_2        
        //  1476: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.alignment:Lcom/intellij/formatting/Alignment;
        //  1479: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1482: dup            
        //  1483: ifnonnull       1520
        //  1486: new             Ljava/lang/IllegalStateException;
        //  1489: dup            
        //  1490: ldc             "@NotNull method %s.%s must not return null"
        //  1492: ldc             2
        //  1494: anewarray       Ljava/lang/Object;
        //  1497: dup            
        //  1498: ldc             0
        //  1500: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  1502: aastore        
        //  1503: dup            
        //  1504: ldc             1
        //  1506: ldc             "doCalculate"
        //  1508: aastore        
        //  1509: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1512: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1515: athrow         
        //  1516: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1519: athrow         
        //  1520: areturn        
        //  1521: aload_1        
        //  1522: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getIfLongFormatting:(Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1525: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //  1528: aload_2        
        //  1529: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.alignment:Lcom/intellij/formatting/Alignment;
        //  1532: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1535: dup            
        //  1536: ifnonnull       1573
        //  1539: new             Ljava/lang/IllegalStateException;
        //  1542: dup            
        //  1543: ldc             "@NotNull method %s.%s must not return null"
        //  1545: ldc             2
        //  1547: anewarray       Ljava/lang/Object;
        //  1550: dup            
        //  1551: ldc             0
        //  1553: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  1555: aastore        
        //  1556: dup            
        //  1557: ldc             1
        //  1559: ldc             "doCalculate"
        //  1561: aastore        
        //  1562: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1565: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1568: athrow         
        //  1569: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1572: athrow         
        //  1573: areturn        
        //  1574: aload_2        
        //  1575: dup            
        //  1576: ifnonnull       1613
        //  1579: new             Ljava/lang/IllegalStateException;
        //  1582: dup            
        //  1583: ldc             "@NotNull method %s.%s must not return null"
        //  1585: ldc             2
        //  1587: anewarray       Ljava/lang/Object;
        //  1590: dup            
        //  1591: ldc             0
        //  1593: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  1595: aastore        
        //  1596: dup            
        //  1597: ldc             1
        //  1599: ldc             "doCalculate"
        //  1601: aastore        
        //  1602: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1605: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1608: athrow         
        //  1609: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1612: athrow         
        //  1613: areturn        
        //  1614: aload_0        
        //  1615: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  1618: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1621: if_acmpeq       1641
        //  1624: aload_0        
        //  1625: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  1628: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_CONSTRUCTOR_FIELD_INITIALIZER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1631: if_acmpne       1691
        //  1634: goto            1641
        //  1637: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1640: athrow         
        //  1641: aload_1        
        //  1642: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getJoinFormatting:(Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1645: dup            
        //  1646: ifnonnull       1690
        //  1649: goto            1656
        //  1652: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1655: athrow         
        //  1656: new             Ljava/lang/IllegalStateException;
        //  1659: dup            
        //  1660: ldc             "@NotNull method %s.%s must not return null"
        //  1662: ldc             2
        //  1664: anewarray       Ljava/lang/Object;
        //  1667: dup            
        //  1668: ldc             0
        //  1670: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  1672: aastore        
        //  1673: dup            
        //  1674: ldc             1
        //  1676: ldc             "doCalculate"
        //  1678: aastore        
        //  1679: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1682: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1685: athrow         
        //  1686: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1689: athrow         
        //  1690: areturn        
        //  1691: aload_0        
        //  1692: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1695: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_BASE_CLAUSE_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1698: if_acmpne       2273
        //  1701: aload_0        
        //  1702: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  1705: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_BASE_CLAUSE_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1708: aload_0        
        //  1709: invokedynamic   compute:(Lcom/jetbrains/cidr/lang/formatting/OCWrappingProcessor;)Lcom/intellij/openapi/util/Computable;
        //  1714: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.get:(Ljava/lang/Object;Lcom/intellij/openapi/util/Computable;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1719: astore_1       
        //  1720: aload_0        
        //  1721: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  1724: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1727: if_acmpne       1910
        //  1730: aload_0        
        //  1731: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  1734: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SUPERCLASS_LIST_BEFORE_COLON:I
        //  1737: tableswitch {
        //                0: 1768
        //                1: 1818
        //                2: 1864
        //          default: 1864
        //        }
        //  1764: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1767: athrow         
        //  1768: aload_0        
        //  1769: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getJoinFormatting:()Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1772: dup            
        //  1773: ifnonnull       1817
        //  1776: goto            1783
        //  1779: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1782: athrow         
        //  1783: new             Ljava/lang/IllegalStateException;
        //  1786: dup            
        //  1787: ldc             "@NotNull method %s.%s must not return null"
        //  1789: ldc             2
        //  1791: anewarray       Ljava/lang/Object;
        //  1794: dup            
        //  1795: ldc             0
        //  1797: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  1799: aastore        
        //  1800: dup            
        //  1801: ldc             1
        //  1803: ldc             "doCalculate"
        //  1805: aastore        
        //  1806: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1809: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1812: athrow         
        //  1813: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1816: athrow         
        //  1817: areturn        
        //  1818: aload_0        
        //  1819: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  1822: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getNewLineFormatting:(Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1825: dup            
        //  1826: ifnonnull       1863
        //  1829: new             Ljava/lang/IllegalStateException;
        //  1832: dup            
        //  1833: ldc             "@NotNull method %s.%s must not return null"
        //  1835: ldc             2
        //  1837: anewarray       Ljava/lang/Object;
        //  1840: dup            
        //  1841: ldc             0
        //  1843: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  1845: aastore        
        //  1846: dup            
        //  1847: ldc             1
        //  1849: ldc             "doCalculate"
        //  1851: aastore        
        //  1852: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1855: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1858: athrow         
        //  1859: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1862: athrow         
        //  1863: areturn        
        //  1864: aload_0        
        //  1865: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  1868: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getIfLongFormatting:(Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1871: dup            
        //  1872: ifnonnull       1909
        //  1875: new             Ljava/lang/IllegalStateException;
        //  1878: dup            
        //  1879: ldc             "@NotNull method %s.%s must not return null"
        //  1881: ldc             2
        //  1883: anewarray       Ljava/lang/Object;
        //  1886: dup            
        //  1887: ldc             0
        //  1889: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  1891: aastore        
        //  1892: dup            
        //  1893: ldc             1
        //  1895: ldc             "doCalculate"
        //  1897: aastore        
        //  1898: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1901: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1904: athrow         
        //  1905: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1908: athrow         
        //  1909: areturn        
        //  1910: aload_0        
        //  1911: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_BASE_CLAUSE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1914: aload_0        
        //  1915: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  1918: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SUPERCLASS_LIST_COMMA_ON_NEXT_LINE:Z
        //  1921: invokespecial   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.a:(Lcom/intellij/psi/tree/IElementType;Z)Z
        //  1924: ifeq            2196
        //  1927: aload_0        
        //  1928: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //  1931: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1934: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isPrecededBy:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Z
        //  1937: ifeq            2156
        //  1940: goto            1947
        //  1943: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1946: athrow         
        //  1947: aload_0        
        //  1948: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  1951: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SUPERCLASS_LIST_AFTER_COLON:I
        //  1954: tableswitch {
        //                0: 1984
        //                1: 2044
        //                2: 2100
        //          default: 2100
        //        }
        //  1980: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1983: athrow         
        //  1984: aload_0        
        //  1985: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getJoinFormatting:()Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1988: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //  1991: aload_1        
        //  1992: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.alignment:Lcom/intellij/formatting/Alignment;
        //  1995: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  1998: dup            
        //  1999: ifnonnull       2043
        //  2002: goto            2009
        //  2005: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2008: athrow         
        //  2009: new             Ljava/lang/IllegalStateException;
        //  2012: dup            
        //  2013: ldc             "@NotNull method %s.%s must not return null"
        //  2015: ldc             2
        //  2017: anewarray       Ljava/lang/Object;
        //  2020: dup            
        //  2021: ldc             0
        //  2023: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  2025: aastore        
        //  2026: dup            
        //  2027: ldc             1
        //  2029: ldc             "doCalculate"
        //  2031: aastore        
        //  2032: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  2035: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  2038: athrow         
        //  2039: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2042: athrow         
        //  2043: areturn        
        //  2044: aload_0        
        //  2045: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  2048: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getNewLineFormatting:(Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2051: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //  2054: aload_1        
        //  2055: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.alignment:Lcom/intellij/formatting/Alignment;
        //  2058: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2061: dup            
        //  2062: ifnonnull       2099
        //  2065: new             Ljava/lang/IllegalStateException;
        //  2068: dup            
        //  2069: ldc             "@NotNull method %s.%s must not return null"
        //  2071: ldc             2
        //  2073: anewarray       Ljava/lang/Object;
        //  2076: dup            
        //  2077: ldc             0
        //  2079: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  2081: aastore        
        //  2082: dup            
        //  2083: ldc             1
        //  2085: ldc             "doCalculate"
        //  2087: aastore        
        //  2088: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  2091: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  2094: athrow         
        //  2095: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2098: athrow         
        //  2099: areturn        
        //  2100: aload_0        
        //  2101: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  2104: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getIfLongFormatting:(Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2107: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //  2110: aload_1        
        //  2111: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.alignment:Lcom/intellij/formatting/Alignment;
        //  2114: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2117: dup            
        //  2118: ifnonnull       2155
        //  2121: new             Ljava/lang/IllegalStateException;
        //  2124: dup            
        //  2125: ldc             "@NotNull method %s.%s must not return null"
        //  2127: ldc             2
        //  2129: anewarray       Ljava/lang/Object;
        //  2132: dup            
        //  2133: ldc             0
        //  2135: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  2137: aastore        
        //  2138: dup            
        //  2139: ldc             1
        //  2141: ldc             "doCalculate"
        //  2143: aastore        
        //  2144: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  2147: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  2150: athrow         
        //  2151: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2154: athrow         
        //  2155: areturn        
        //  2156: aload_1        
        //  2157: dup            
        //  2158: ifnonnull       2195
        //  2161: new             Ljava/lang/IllegalStateException;
        //  2164: dup            
        //  2165: ldc             "@NotNull method %s.%s must not return null"
        //  2167: ldc             2
        //  2169: anewarray       Ljava/lang/Object;
        //  2172: dup            
        //  2173: ldc             0
        //  2175: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  2177: aastore        
        //  2178: dup            
        //  2179: ldc             1
        //  2181: ldc             "doCalculate"
        //  2183: aastore        
        //  2184: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  2187: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  2190: athrow         
        //  2191: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2194: athrow         
        //  2195: areturn        
        //  2196: aload_0        
        //  2197: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  2200: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2203: if_acmpeq       2223
        //  2206: aload_0        
        //  2207: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  2210: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_BASE_CLAUSE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2213: if_acmpne       2273
        //  2216: goto            2223
        //  2219: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2222: athrow         
        //  2223: aload_0        
        //  2224: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getJoinFormatting:()Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2227: dup            
        //  2228: ifnonnull       2272
        //  2231: goto            2238
        //  2234: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2237: athrow         
        //  2238: new             Ljava/lang/IllegalStateException;
        //  2241: dup            
        //  2242: ldc             "@NotNull method %s.%s must not return null"
        //  2244: ldc             2
        //  2246: anewarray       Ljava/lang/Object;
        //  2249: dup            
        //  2250: ldc             0
        //  2252: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  2254: aastore        
        //  2255: dup            
        //  2256: ldc             1
        //  2258: ldc             "doCalculate"
        //  2260: aastore        
        //  2261: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  2264: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  2267: athrow         
        //  2268: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2271: athrow         
        //  2272: areturn        
        //  2273: aload_0        
        //  2274: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2277: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD_SELECTOR_PART:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2280: if_acmpne       2371
        //  2283: aload_0        
        //  2284: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //  2287: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2290: invokestatic    com/intellij/psi/formatter/FormatterUtil.isFollowedBy:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Z
        //  2293: ifeq            2371
        //  2296: goto            2303
        //  2299: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2302: athrow         
        //  2303: aload_0        
        //  2304: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  2307: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.getParent:()Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  2312: ldc             "parameter columns"
        //  2314: aload_0        
        //  2315: invokedynamic   compute:(Lcom/jetbrains/cidr/lang/formatting/OCWrappingProcessor;)Lcom/intellij/openapi/util/Computable;
        //  2320: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.get:(Ljava/lang/Object;Lcom/intellij/openapi/util/Computable;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2325: dup            
        //  2326: ifnonnull       2370
        //  2329: goto            2336
        //  2332: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2335: athrow         
        //  2336: new             Ljava/lang/IllegalStateException;
        //  2339: dup            
        //  2340: ldc             "@NotNull method %s.%s must not return null"
        //  2342: ldc             2
        //  2344: anewarray       Ljava/lang/Object;
        //  2347: dup            
        //  2348: ldc             0
        //  2350: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  2352: aastore        
        //  2353: dup            
        //  2354: ldc             1
        //  2356: ldc             "doCalculate"
        //  2358: aastore        
        //  2359: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  2362: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  2365: athrow         
        //  2366: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2369: athrow         
        //  2370: areturn        
        //  2371: aload_0        
        //  2372: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  2375: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TEMPLATE_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //  2378: if_acmpne       2473
        //  2381: aload_0        
        //  2382: ldc             "template declaration wrap"
        //  2384: aload_0        
        //  2385: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2388: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2391: if_acmpne       2415
        //  2394: goto            2401
        //  2397: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2400: athrow         
        //  2401: aload_0        
        //  2402: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2405: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_DECLARATION_STRUCT_WRAP:I
        //  2408: goto            2422
        //  2411: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2414: athrow         
        //  2415: aload_0        
        //  2416: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2419: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_DECLARATION_FUNCTION_WRAP:I
        //  2422: iconst_0       
        //  2423: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapOnly:(Ljava/lang/Object;IZ)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2426: pop            
        //  2427: aload_0        
        //  2428: ldc             "template declaration wrap"
        //  2430: iconst_1       
        //  2431: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.alignOnly:(Ljava/lang/Object;Z)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2434: dup            
        //  2435: ifnonnull       2472
        //  2438: new             Ljava/lang/IllegalStateException;
        //  2441: dup            
        //  2442: ldc             "@NotNull method %s.%s must not return null"
        //  2444: ldc             2
        //  2446: anewarray       Ljava/lang/Object;
        //  2449: dup            
        //  2450: ldc             0
        //  2452: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  2454: aastore        
        //  2455: dup            
        //  2456: ldc             1
        //  2458: ldc             "doCalculate"
        //  2460: aastore        
        //  2461: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  2464: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  2467: athrow         
        //  2468: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2471: athrow         
        //  2472: areturn        
        //  2473: aload_0        
        //  2474: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  2477: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MESSAGE_ARGUMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2480: if_acmpne       2763
        //  2483: aload_0        
        //  2484: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  2487: ldc             "first method argument"
        //  2489: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.get:(Ljava/lang/Object;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2494: astore_1       
        //  2495: aload_1        
        //  2496: ifnonnull       2607
        //  2499: aload_0        
        //  2500: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  2503: astore_2       
        //  2504: aload_2        
        //  2505: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.getParent:()Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  2510: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.getType:()Lcom/intellij/psi/tree/IElementType;
        //  2515: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MESSAGE_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2518: if_acmpne       2531
        //  2521: aload_2        
        //  2522: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.getParent:()Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  2527: astore_2       
        //  2528: goto            2504
        //  2531: aload_2        
        //  2532: ldc             "method argument alignment"
        //  2534: aload_0        
        //  2535: invokedynamic   compute:(Lcom/jetbrains/cidr/lang/formatting/OCWrappingProcessor;)Lcom/intellij/openapi/util/Computable;
        //  2540: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.get:(Ljava/lang/Object;Lcom/intellij/openapi/util/Computable;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2545: astore_3       
        //  2546: aload_0        
        //  2547: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  2550: ldc             "first method argument"
        //  2552: aload_3        
        //  2553: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //  2556: aload_0        
        //  2557: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  2560: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.ALIGN_MULTILINE_CHAINED_METHODS:Z
        //  2563: ifeq            2577
        //  2566: aload_3        
        //  2567: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.alignment:Lcom/intellij/formatting/Alignment;
        //  2570: goto            2598
        //  2573: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2576: athrow         
        //  2577: aload_0        
        //  2578: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2581: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.METHOD_CALL_ARGUMENTS_ALIGN_MULTILINE:Z
        //  2584: ifeq            2597
        //  2587: invokestatic    com/intellij/formatting/Alignment.createAlignment:()Lcom/intellij/formatting/Alignment;
        //  2590: goto            2598
        //  2593: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2596: athrow         
        //  2597: aconst_null    
        //  2598: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2601: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.put:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2606: astore_1       
        //  2607: aload_0        
        //  2608: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //  2611: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MESSAGE_ARGUMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2614: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isPrecededBy:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Z
        //  2617: ifne            2628
        //  2620: iconst_1       
        //  2621: goto            2629
        //  2624: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2627: athrow         
        //  2628: iconst_0       
        //  2629: istore_2       
        //  2630: iload_2        
        //  2631: ifeq            2704
        //  2634: aload_0        
        //  2635: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //  2638: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MESSAGE_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2641: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isPrecededBy:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Z
        //  2644: istore_3       
        //  2645: iload_3        
        //  2646: ifeq            2657
        //  2649: aload_1        
        //  2650: goto            2665
        //  2653: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2656: athrow         
        //  2657: aconst_null    
        //  2658: aload_1        
        //  2659: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.alignment:Lcom/intellij/formatting/Alignment;
        //  2662: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2665: dup            
        //  2666: ifnonnull       2703
        //  2669: new             Ljava/lang/IllegalStateException;
        //  2672: dup            
        //  2673: ldc             "@NotNull method %s.%s must not return null"
        //  2675: ldc             2
        //  2677: anewarray       Ljava/lang/Object;
        //  2680: dup            
        //  2681: ldc             0
        //  2683: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  2685: aastore        
        //  2686: dup            
        //  2687: ldc             1
        //  2689: ldc             "doCalculate"
        //  2691: aastore        
        //  2692: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  2695: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  2698: athrow         
        //  2699: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2702: athrow         
        //  2703: areturn        
        //  2704: aload_1        
        //  2705: astore_3       
        //  2706: aload_0        
        //  2707: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  2710: ldc             "method arguments"
        //  2712: aload_0        
        //  2713: aload_3        
        //  2714: invokedynamic   compute:(Lcom/jetbrains/cidr/lang/formatting/OCWrappingProcessor;Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;)Lcom/intellij/openapi/util/Computable;
        //  2719: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.get:(Ljava/lang/Object;Lcom/intellij/openapi/util/Computable;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2724: dup            
        //  2725: ifnonnull       2762
        //  2728: new             Ljava/lang/IllegalStateException;
        //  2731: dup            
        //  2732: ldc             "@NotNull method %s.%s must not return null"
        //  2734: ldc             2
        //  2736: anewarray       Ljava/lang/Object;
        //  2739: dup            
        //  2740: ldc             0
        //  2742: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  2744: aastore        
        //  2745: dup            
        //  2746: ldc             1
        //  2748: ldc             "doCalculate"
        //  2750: aastore        
        //  2751: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  2754: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  2757: athrow         
        //  2758: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2761: athrow         
        //  2762: areturn        
        //  2763: aload_0        
        //  2764: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  2767: getstatic       com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.CHAINED_CALL_PSEUDOTYPE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2770: if_acmpne       2841
        //  2773: aload_0        
        //  2774: aload_0        
        //  2775: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  2778: aload_0        
        //  2779: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  2782: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.METHOD_CALL_CHAIN_WRAP:I
        //  2785: aload_0        
        //  2786: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  2789: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.ALIGN_MULTILINE_CHAINED_METHODS:Z
        //  2792: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapAndAlign:(Ljava/lang/Object;IZ)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2795: dup            
        //  2796: ifnonnull       2840
        //  2799: goto            2806
        //  2802: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2805: athrow         
        //  2806: new             Ljava/lang/IllegalStateException;
        //  2809: dup            
        //  2810: ldc             "@NotNull method %s.%s must not return null"
        //  2812: ldc             2
        //  2814: anewarray       Ljava/lang/Object;
        //  2817: dup            
        //  2818: ldc             0
        //  2820: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  2822: aastore        
        //  2823: dup            
        //  2824: ldc             1
        //  2826: ldc             "doCalculate"
        //  2828: aastore        
        //  2829: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  2832: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  2835: athrow         
        //  2836: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2839: athrow         
        //  2840: areturn        
        //  2841: aload_0        
        //  2842: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  2845: getstatic       com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.BINARY_EXPRESSION_PSEUDOTYPE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2848: if_acmpne       2997
        //  2851: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SHIFT_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //  2854: aload_0        
        //  2855: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //  2858: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //  2863: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //  2866: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2871: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  2874: ifeq            2943
        //  2877: goto            2884
        //  2880: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2883: athrow         
        //  2884: aload_0        
        //  2885: ldc             "Shift operations"
        //  2887: aload_0        
        //  2888: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2891: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SHIFT_OPERATION_WRAP:I
        //  2894: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapOnly:(Ljava/lang/Object;I)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2897: dup            
        //  2898: ifnonnull       2942
        //  2901: goto            2908
        //  2904: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2907: athrow         
        //  2908: new             Ljava/lang/IllegalStateException;
        //  2911: dup            
        //  2912: ldc             "@NotNull method %s.%s must not return null"
        //  2914: ldc             2
        //  2916: anewarray       Ljava/lang/Object;
        //  2919: dup            
        //  2920: ldc             0
        //  2922: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  2924: aastore        
        //  2925: dup            
        //  2926: ldc             1
        //  2928: ldc             "doCalculate"
        //  2930: aastore        
        //  2931: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  2934: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  2937: athrow         
        //  2938: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2941: athrow         
        //  2942: areturn        
        //  2943: aload_0        
        //  2944: aload_0        
        //  2945: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  2948: aload_0        
        //  2949: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  2952: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.BINARY_OPERATION_WRAP:I
        //  2955: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapOnly:(Ljava/lang/Object;I)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  2958: dup            
        //  2959: ifnonnull       2996
        //  2962: new             Ljava/lang/IllegalStateException;
        //  2965: dup            
        //  2966: ldc             "@NotNull method %s.%s must not return null"
        //  2968: ldc             2
        //  2970: anewarray       Ljava/lang/Object;
        //  2973: dup            
        //  2974: ldc             0
        //  2976: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  2978: aastore        
        //  2979: dup            
        //  2980: ldc             1
        //  2982: ldc             "doCalculate"
        //  2984: aastore        
        //  2985: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  2988: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  2991: athrow         
        //  2992: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2995: athrow         
        //  2996: areturn        
        //  2997: aload_0        
        //  2998: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3001: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.BINARY_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3004: if_acmpne       3248
        //  3007: aload_0        
        //  3008: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //  3011: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //  3016: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //  3021: astore_1       
        //  3022: aload_1        
        //  3023: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //  3026: ifeq            3045
        //  3029: aload_1        
        //  3030: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //  3033: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3038: goto            3046
        //  3041: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3044: athrow         
        //  3045: aconst_null    
        //  3046: astore_2       
        //  3047: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SHIFT_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //  3050: aload_2        
        //  3051: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  3054: ifeq            3194
        //  3057: aload_2        
        //  3058: aload_0        
        //  3059: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  3062: if_acmpeq       3093
        //  3065: goto            3072
        //  3068: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3071: athrow         
        //  3072: aload_0        
        //  3073: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  3076: ldc             "Shift operations"
        //  3078: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.get:(Ljava/lang/Object;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  3083: ifnull          3152
        //  3086: goto            3093
        //  3089: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3092: athrow         
        //  3093: aload_0        
        //  3094: ldc             "Shift operations"
        //  3096: aload_0        
        //  3097: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3100: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SHIFT_OPERATION_ALIGN_MULTILINE:Z
        //  3103: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.alignOnly:(Ljava/lang/Object;Z)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  3106: dup            
        //  3107: ifnonnull       3151
        //  3110: goto            3117
        //  3113: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3116: athrow         
        //  3117: new             Ljava/lang/IllegalStateException;
        //  3120: dup            
        //  3121: ldc             "@NotNull method %s.%s must not return null"
        //  3123: ldc             2
        //  3125: anewarray       Ljava/lang/Object;
        //  3128: dup            
        //  3129: ldc             0
        //  3131: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  3133: aastore        
        //  3134: dup            
        //  3135: ldc             1
        //  3137: ldc             "doCalculate"
        //  3139: aastore        
        //  3140: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  3143: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  3146: athrow         
        //  3147: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3150: athrow         
        //  3151: areturn        
        //  3152: getstatic       com/jetbrains/cidr/lang/formatting/OCFormatterInfo.EMPTY:Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  3155: dup            
        //  3156: ifnonnull       3193
        //  3159: new             Ljava/lang/IllegalStateException;
        //  3162: dup            
        //  3163: ldc             "@NotNull method %s.%s must not return null"
        //  3165: ldc             2
        //  3167: anewarray       Ljava/lang/Object;
        //  3170: dup            
        //  3171: ldc             0
        //  3173: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  3175: aastore        
        //  3176: dup            
        //  3177: ldc             1
        //  3179: ldc             "doCalculate"
        //  3181: aastore        
        //  3182: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  3185: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  3188: athrow         
        //  3189: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3192: athrow         
        //  3193: areturn        
        //  3194: aload_0        
        //  3195: aload_0        
        //  3196: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3199: aload_0        
        //  3200: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  3203: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.ALIGN_MULTILINE_BINARY_OPERATION:Z
        //  3206: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.alignOnly:(Ljava/lang/Object;Z)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  3209: dup            
        //  3210: ifnonnull       3247
        //  3213: new             Ljava/lang/IllegalStateException;
        //  3216: dup            
        //  3217: ldc             "@NotNull method %s.%s must not return null"
        //  3219: ldc             2
        //  3221: anewarray       Ljava/lang/Object;
        //  3224: dup            
        //  3225: ldc             0
        //  3227: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  3229: aastore        
        //  3230: dup            
        //  3231: ldc             1
        //  3233: ldc             "doCalculate"
        //  3235: aastore        
        //  3236: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  3239: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  3242: athrow         
        //  3243: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3246: athrow         
        //  3247: areturn        
        //  3248: aload_0        
        //  3249: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //  3252: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isMessageArgumentValue:(Lcom/intellij/lang/ASTNode;)Z
        //  3255: ifeq            3723
        //  3258: iconst_m1      
        //  3259: istore_1       
        //  3260: iconst_0       
        //  3261: istore_2       
        //  3262: aload_0        
        //  3263: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //  3266: astore_3       
        //  3267: aload_3        
        //  3268: ifnull          3370
        //  3271: aload_3        
        //  3272: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //  3277: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MESSAGE_ARGUMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3280: if_acmpne       3360
        //  3283: goto            3290
        //  3286: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3289: athrow         
        //  3290: aload_3        
        //  3291: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isVarArgArgument:(Lcom/intellij/lang/ASTNode;)Z
        //  3294: ifeq            3314
        //  3297: goto            3304
        //  3300: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3303: athrow         
        //  3304: iinc            1, 1
        //  3307: goto            3360
        //  3310: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3313: athrow         
        //  3314: aload_3        
        //  3315: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_SELECTOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3318: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //  3323: astore          4
        //  3325: aload           4
        //  3327: ifnull          3370
        //  3330: aload           4
        //  3332: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //  3337: invokestatic    com/intellij/openapi/util/text/StringUtil.notNullize:(Ljava/lang/String;)Ljava/lang/String;
        //  3340: ldc             "ObjectsAndKeys"
        //  3342: invokestatic    com/intellij/openapi/util/text/StringUtil.containsIgnoreCase:(Ljava/lang/String;Ljava/lang/String;)Z
        //  3345: ifeq            3370
        //  3348: goto            3355
        //  3351: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3354: athrow         
        //  3355: iconst_1       
        //  3356: istore_2       
        //  3357: goto            3370
        //  3360: aload_3        
        //  3361: invokeinterface com/intellij/lang/ASTNode.getTreePrev:()Lcom/intellij/lang/ASTNode;
        //  3366: astore_3       
        //  3367: goto            3267
        //  3370: aload_0        
        //  3371: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  3374: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MESSAGE_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3377: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.getAncestor:(Lcom/intellij/psi/tree/IElementType;)Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  3382: astore_3       
        //  3383: iload_1        
        //  3384: iconst_m1      
        //  3385: if_icmpeq       3570
        //  3388: aload_3        
        //  3389: ldc             "method argument values"
        //  3391: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.get:(Ljava/lang/Object;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  3396: astore          4
        //  3398: aload           4
        //  3400: ifnull          3567
        //  3403: aload_0        
        //  3404: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3407: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.METHOD_CALL_ARGUMENTS_SPECIAL_DICTIONARY_PAIRS_TREATMENT:Z
        //  3410: ifeq            3526
        //  3413: goto            3420
        //  3416: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3419: athrow         
        //  3420: iload_2        
        //  3421: ifeq            3526
        //  3424: goto            3431
        //  3427: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3430: athrow         
        //  3431: iload_1        
        //  3432: iconst_2       
        //  3433: irem           
        //  3434: ifne            3526
        //  3437: goto            3444
        //  3440: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3443: athrow         
        //  3444: aload_0        
        //  3445: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3448: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.ALIGN_DICTIONARY_PAIR_VALUES:Z
        //  3451: ifeq            3479
        //  3454: goto            3461
        //  3457: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3460: athrow         
        //  3461: aload_0        
        //  3462: ldc             "group dictionary pairs"
        //  3464: aload_3        
        //  3465: aload_0        
        //  3466: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //  3469: invokespecial   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;Lcom/intellij/lang/ASTNode;)Lcom/intellij/formatting/Alignment;
        //  3472: goto            3484
        //  3475: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3478: athrow         
        //  3479: aload           4
        //  3481: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.alignment:Lcom/intellij/formatting/Alignment;
        //  3484: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  3487: dup            
        //  3488: ifnonnull       3525
        //  3491: new             Ljava/lang/IllegalStateException;
        //  3494: dup            
        //  3495: ldc             "@NotNull method %s.%s must not return null"
        //  3497: ldc             2
        //  3499: anewarray       Ljava/lang/Object;
        //  3502: dup            
        //  3503: ldc             0
        //  3505: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  3507: aastore        
        //  3508: dup            
        //  3509: ldc             1
        //  3511: ldc             "doCalculate"
        //  3513: aastore        
        //  3514: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  3517: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  3520: athrow         
        //  3521: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3524: athrow         
        //  3525: areturn        
        //  3526: aload           4
        //  3528: dup            
        //  3529: ifnonnull       3566
        //  3532: new             Ljava/lang/IllegalStateException;
        //  3535: dup            
        //  3536: ldc             "@NotNull method %s.%s must not return null"
        //  3538: ldc             2
        //  3540: anewarray       Ljava/lang/Object;
        //  3543: dup            
        //  3544: ldc             0
        //  3546: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  3548: aastore        
        //  3549: dup            
        //  3550: ldc             1
        //  3552: ldc             "doCalculate"
        //  3554: aastore        
        //  3555: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  3558: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  3561: athrow         
        //  3562: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3565: athrow         
        //  3566: areturn        
        //  3567: goto            3681
        //  3570: aload_0        
        //  3571: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //  3574: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isFollowedByVarArg:(Lcom/intellij/lang/ASTNode;)Z
        //  3577: ifeq            3681
        //  3580: aload_3        
        //  3581: ldc             "method arguments"
        //  3583: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.get:(Ljava/lang/Object;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  3588: astore          4
        //  3590: aload           4
        //  3592: ifnull          3607
        //  3595: aload           4
        //  3597: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //  3600: goto            3618
        //  3603: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3606: athrow         
        //  3607: aload_0        
        //  3608: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3611: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.METHOD_CALL_ARGUMENTS_WRAP:I
        //  3614: iconst_1       
        //  3615: invokestatic    com/intellij/formatting/Wrap.createWrap:(IZ)Lcom/intellij/formatting/Wrap;
        //  3618: invokestatic    com/intellij/formatting/Alignment.createAlignment:()Lcom/intellij/formatting/Alignment;
        //  3621: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  3624: astore          5
        //  3626: aload_3        
        //  3627: ldc             "method argument values"
        //  3629: aload           5
        //  3631: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.put:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  3636: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.alignment:Lcom/intellij/formatting/Alignment;
        //  3639: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  3642: dup            
        //  3643: ifnonnull       3680
        //  3646: new             Ljava/lang/IllegalStateException;
        //  3649: dup            
        //  3650: ldc             "@NotNull method %s.%s must not return null"
        //  3652: ldc             2
        //  3654: anewarray       Ljava/lang/Object;
        //  3657: dup            
        //  3658: ldc             0
        //  3660: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  3662: aastore        
        //  3663: dup            
        //  3664: ldc             1
        //  3666: ldc             "doCalculate"
        //  3668: aastore        
        //  3669: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  3672: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  3675: athrow         
        //  3676: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3679: athrow         
        //  3680: areturn        
        //  3681: getstatic       com/jetbrains/cidr/lang/formatting/OCFormatterInfo.EMPTY:Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  3684: dup            
        //  3685: ifnonnull       3722
        //  3688: new             Ljava/lang/IllegalStateException;
        //  3691: dup            
        //  3692: ldc             "@NotNull method %s.%s must not return null"
        //  3694: ldc             2
        //  3696: anewarray       Ljava/lang/Object;
        //  3699: dup            
        //  3700: ldc             0
        //  3702: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  3704: aastore        
        //  3705: dup            
        //  3706: ldc             1
        //  3708: ldc             "doCalculate"
        //  3710: aastore        
        //  3711: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  3714: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  3717: athrow         
        //  3718: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3721: athrow         
        //  3722: areturn        
        //  3723: aload_0        
        //  3724: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3727: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_SELECTOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3730: if_acmpne       3824
        //  3733: aload_0        
        //  3734: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //  3737: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3740: invokestatic    com/intellij/psi/formatter/FormatterUtil.isFollowedBy:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Z
        //  3743: ifeq            3824
        //  3746: goto            3753
        //  3749: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3752: athrow         
        //  3753: aload_0        
        //  3754: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  3757: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MESSAGE_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3760: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.getAncestor:(Lcom/intellij/psi/tree/IElementType;)Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  3765: ldc             "call argument columns"
        //  3767: aload_0        
        //  3768: invokedynamic   compute:(Lcom/jetbrains/cidr/lang/formatting/OCWrappingProcessor;)Lcom/intellij/openapi/util/Computable;
        //  3773: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.get:(Ljava/lang/Object;Lcom/intellij/openapi/util/Computable;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  3778: dup            
        //  3779: ifnonnull       3823
        //  3782: goto            3789
        //  3785: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3788: athrow         
        //  3789: new             Ljava/lang/IllegalStateException;
        //  3792: dup            
        //  3793: ldc             "@NotNull method %s.%s must not return null"
        //  3795: ldc             2
        //  3797: anewarray       Ljava/lang/Object;
        //  3800: dup            
        //  3801: ldc             0
        //  3803: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  3805: aastore        
        //  3806: dup            
        //  3807: ldc             1
        //  3809: ldc             "doCalculate"
        //  3811: aastore        
        //  3812: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  3815: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  3818: athrow         
        //  3819: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3822: athrow         
        //  3823: areturn        
        //  3824: aload_0        
        //  3825: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3828: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3831: if_acmpeq       3868
        //  3834: aload_0        
        //  3835: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3838: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3841: if_acmpeq       3868
        //  3844: goto            3851
        //  3847: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3850: athrow         
        //  3851: aload_0        
        //  3852: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3855: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3858: if_acmpne       3986
        //  3861: goto            3868
        //  3864: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3867: athrow         
        //  3868: aload_0        
        //  3869: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  3872: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3875: if_acmpeq       3902
        //  3878: goto            3885
        //  3881: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3884: athrow         
        //  3885: aload_0        
        //  3886: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  3889: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3892: if_acmpne       3986
        //  3895: goto            3902
        //  3898: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3901: athrow         
        //  3902: aload_0        
        //  3903: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3906: aload_0        
        //  3907: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3910: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3913: if_acmpne       3937
        //  3916: goto            3923
        //  3919: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3922: athrow         
        //  3923: aload_0        
        //  3924: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3927: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_PARAMETERS_ALIGN_MULTILINE_PARS:Z
        //  3930: goto            3944
        //  3933: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3936: athrow         
        //  3937: aload_0        
        //  3938: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3941: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_ALIGN_MULTILINE_PARS:Z
        //  3944: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.alignOnly:(Ljava/lang/Object;Z)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  3947: dup            
        //  3948: ifnonnull       3985
        //  3951: new             Ljava/lang/IllegalStateException;
        //  3954: dup            
        //  3955: ldc             "@NotNull method %s.%s must not return null"
        //  3957: ldc             2
        //  3959: anewarray       Ljava/lang/Object;
        //  3962: dup            
        //  3963: ldc             0
        //  3965: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  3967: aastore        
        //  3968: dup            
        //  3969: ldc             1
        //  3971: ldc             "doCalculate"
        //  3973: aastore        
        //  3974: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  3977: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  3980: athrow         
        //  3981: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3984: athrow         
        //  3985: areturn        
        //  3986: aload_0        
        //  3987: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3990: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_LAMBDA_INTRODUCER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3993: if_acmpne       4090
        //  3996: aload_0        
        //  3997: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  4000: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4003: if_acmpeq       4030
        //  4006: goto            4013
        //  4009: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4012: athrow         
        //  4013: aload_0        
        //  4014: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  4017: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4020: if_acmpne       4090
        //  4023: goto            4030
        //  4026: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4029: athrow         
        //  4030: aload_0        
        //  4031: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4034: aload_0        
        //  4035: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  4038: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.LAMBDA_CAPTURE_LIST_ALIGN_MULTILINE_BRACKET:Z
        //  4041: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.alignOnly:(Ljava/lang/Object;Z)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  4044: dup            
        //  4045: ifnonnull       4089
        //  4048: goto            4055
        //  4051: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4054: athrow         
        //  4055: new             Ljava/lang/IllegalStateException;
        //  4058: dup            
        //  4059: ldc             "@NotNull method %s.%s must not return null"
        //  4061: ldc             2
        //  4063: anewarray       Ljava/lang/Object;
        //  4066: dup            
        //  4067: ldc             0
        //  4069: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  4071: aastore        
        //  4072: dup            
        //  4073: ldc             1
        //  4075: ldc             "doCalculate"
        //  4077: aastore        
        //  4078: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  4081: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  4084: athrow         
        //  4085: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4088: athrow         
        //  4089: areturn        
        //  4090: aload_0        
        //  4091: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4094: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4097: if_acmpeq       4117
        //  4100: aload_0        
        //  4101: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4104: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TEMPLATE_ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4107: if_acmpne       4235
        //  4110: goto            4117
        //  4113: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4116: athrow         
        //  4117: aload_0        
        //  4118: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  4121: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4124: if_acmpeq       4151
        //  4127: goto            4134
        //  4130: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4133: athrow         
        //  4134: aload_0        
        //  4135: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  4138: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4141: if_acmpne       4235
        //  4144: goto            4151
        //  4147: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4150: athrow         
        //  4151: aload_0        
        //  4152: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4155: aload_0        
        //  4156: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4159: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4162: if_acmpne       4186
        //  4165: goto            4172
        //  4168: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4171: athrow         
        //  4172: aload_0        
        //  4173: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  4176: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_PARAMETERS_ALIGN_MULTILINE_PARS:Z
        //  4179: goto            4193
        //  4182: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4185: athrow         
        //  4186: aload_0        
        //  4187: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  4190: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_CALL_ARGUMENTS_ALIGN_MULTILINE_PARS:Z
        //  4193: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.alignOnly:(Ljava/lang/Object;Z)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  4196: dup            
        //  4197: ifnonnull       4234
        //  4200: new             Ljava/lang/IllegalStateException;
        //  4203: dup            
        //  4204: ldc             "@NotNull method %s.%s must not return null"
        //  4206: ldc             2
        //  4208: anewarray       Ljava/lang/Object;
        //  4211: dup            
        //  4212: ldc             0
        //  4214: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  4216: aastore        
        //  4217: dup            
        //  4218: ldc             1
        //  4220: ldc             "doCalculate"
        //  4222: aastore        
        //  4223: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  4226: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  4229: athrow         
        //  4230: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4233: athrow         
        //  4234: areturn        
        //  4235: aload_0        
        //  4236: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4239: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FOR_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4242: if_acmpne       4380
        //  4245: aload_0        
        //  4246: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //  4249: ldc             Lcom/jetbrains/cidr/lang/psi/OCForStatement;.class
        //  4251: invokeinterface com/intellij/lang/ASTNode.getPsi:(Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //  4256: checkcast       Lcom/jetbrains/cidr/lang/psi/OCForStatement;
        //  4259: astore_1       
        //  4260: aload_1        
        //  4261: ifnull          4380
        //  4264: aload_0        
        //  4265: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //  4268: iconst_3       
        //  4269: anewarray       Lcom/intellij/psi/PsiElement;
        //  4272: dup            
        //  4273: iconst_0       
        //  4274: aload_1        
        //  4275: invokeinterface com/jetbrains/cidr/lang/psi/OCForStatement.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //  4280: aastore        
        //  4281: dup            
        //  4282: iconst_1       
        //  4283: aload_1        
        //  4284: invokeinterface com/jetbrains/cidr/lang/psi/OCForStatement.getCondition:()Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //  4289: aastore        
        //  4290: dup            
        //  4291: iconst_2       
        //  4292: aload_1        
        //  4293: invokeinterface com/jetbrains/cidr/lang/psi/OCForStatement.getIncrement:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //  4298: aastore        
        //  4299: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isOneOf:(Lcom/intellij/lang/ASTNode;[Lcom/intellij/psi/PsiElement;)Z
        //  4302: ifeq            4380
        //  4305: goto            4312
        //  4308: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4311: athrow         
        //  4312: aload_0        
        //  4313: aload_0        
        //  4314: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4317: aload_0        
        //  4318: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  4321: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.FOR_STATEMENT_WRAP:I
        //  4324: aload_0        
        //  4325: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  4328: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.ALIGN_MULTILINE_FOR:Z
        //  4331: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapAndAlign:(Ljava/lang/Object;IZ)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  4334: dup            
        //  4335: ifnonnull       4379
        //  4338: goto            4345
        //  4341: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4344: athrow         
        //  4345: new             Ljava/lang/IllegalStateException;
        //  4348: dup            
        //  4349: ldc             "@NotNull method %s.%s must not return null"
        //  4351: ldc             2
        //  4353: anewarray       Ljava/lang/Object;
        //  4356: dup            
        //  4357: ldc             0
        //  4359: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  4361: aastore        
        //  4362: dup            
        //  4363: ldc             1
        //  4365: ldc             "doCalculate"
        //  4367: aastore        
        //  4368: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  4371: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  4374: athrow         
        //  4375: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4378: athrow         
        //  4379: areturn        
        //  4380: aload_0        
        //  4381: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  4384: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ASSIGNMENT_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4387: if_acmpne       4476
        //  4390: aload_0        
        //  4391: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  4394: ldc             "assignment alignment"
        //  4396: aload_0        
        //  4397: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  4400: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.getParent:()Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  4405: ldc             "assignment alignment"
        //  4407: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.get:(Ljava/lang/Object;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  4412: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.put:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  4417: astore_1       
        //  4418: aload_1        
        //  4419: ifnonnull       4436
        //  4422: aload_0        
        //  4423: ldc             "assignment alignment"
        //  4425: aload_0        
        //  4426: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  4429: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.ALIGN_MULTILINE_ASSIGNMENT:Z
        //  4432: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.alignOnly:(Ljava/lang/Object;Z)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  4435: astore_1       
        //  4436: aload_1        
        //  4437: dup            
        //  4438: ifnonnull       4475
        //  4441: new             Ljava/lang/IllegalStateException;
        //  4444: dup            
        //  4445: ldc             "@NotNull method %s.%s must not return null"
        //  4447: ldc             2
        //  4449: anewarray       Ljava/lang/Object;
        //  4452: dup            
        //  4453: ldc             0
        //  4455: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  4457: aastore        
        //  4458: dup            
        //  4459: ldc             1
        //  4461: ldc             "doCalculate"
        //  4463: aastore        
        //  4464: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  4467: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  4470: athrow         
        //  4471: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4474: athrow         
        //  4475: areturn        
        //  4476: aload_0        
        //  4477: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4480: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ASSIGNMENT_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4483: if_acmpne       4705
        //  4486: aload_0        
        //  4487: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  4490: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4493: if_acmpne       4511
        //  4496: goto            4503
        //  4499: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4502: athrow         
        //  4503: iconst_1       
        //  4504: goto            4512
        //  4507: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4510: athrow         
        //  4511: iconst_0       
        //  4512: istore_1       
        //  4513: aconst_null    
        //  4514: astore_2       
        //  4515: iload_1        
        //  4516: ifeq            4543
        //  4519: aload_0        
        //  4520: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //  4523: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.a:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //  4526: astore_3       
        //  4527: aload_0        
        //  4528: aload_3        
        //  4529: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //  4532: aload_3        
        //  4533: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //  4536: checkcast       Lcom/intellij/lang/ASTNode;
        //  4539: invokespecial   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.a:(Ljava/lang/Object;Lcom/intellij/lang/ASTNode;)Lcom/intellij/formatting/Alignment;
        //  4542: astore_2       
        //  4543: aload_0        
        //  4544: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  4547: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.PLACE_ASSIGNMENT_SIGN_ON_NEXT_LINE:Z
        //  4550: iload_1        
        //  4551: if_icmpne       4662
        //  4554: aload_0        
        //  4555: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  4558: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.getParent:()Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  4563: ldc             "assignment wrap"
        //  4565: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.get:(Ljava/lang/Object;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  4570: astore_3       
        //  4571: aload_3        
        //  4572: ifnonnull       4602
        //  4575: aload_0        
        //  4576: ldc             "assignment wrap"
        //  4578: aload_0        
        //  4579: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  4582: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.ASSIGNMENT_WRAP:I
        //  4585: aload_0        
        //  4586: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  4589: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.PLACE_ASSIGNMENT_SIGN_ON_NEXT_LINE:Z
        //  4592: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapOnly:(Ljava/lang/Object;IZ)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  4595: goto            4614
        //  4598: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4601: athrow         
        //  4602: aload_0        
        //  4603: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  4606: ldc             "assignment wrap"
        //  4608: aload_3        
        //  4609: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.put:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  4614: astore_3       
        //  4615: aload_3        
        //  4616: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //  4619: aload_2        
        //  4620: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  4623: dup            
        //  4624: ifnonnull       4661
        //  4627: new             Ljava/lang/IllegalStateException;
        //  4630: dup            
        //  4631: ldc             "@NotNull method %s.%s must not return null"
        //  4633: ldc             2
        //  4635: anewarray       Ljava/lang/Object;
        //  4638: dup            
        //  4639: ldc             0
        //  4641: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  4643: aastore        
        //  4644: dup            
        //  4645: ldc             1
        //  4647: ldc             "doCalculate"
        //  4649: aastore        
        //  4650: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  4653: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  4656: athrow         
        //  4657: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4660: athrow         
        //  4661: areturn        
        //  4662: aload_2        
        //  4663: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  4666: dup            
        //  4667: ifnonnull       4704
        //  4670: new             Ljava/lang/IllegalStateException;
        //  4673: dup            
        //  4674: ldc             "@NotNull method %s.%s must not return null"
        //  4676: ldc             2
        //  4678: anewarray       Ljava/lang/Object;
        //  4681: dup            
        //  4682: ldc             0
        //  4684: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  4686: aastore        
        //  4687: dup            
        //  4688: ldc             1
        //  4690: ldc             "doCalculate"
        //  4692: aastore        
        //  4693: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  4696: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  4699: athrow         
        //  4700: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4703: athrow         
        //  4704: areturn        
        //  4705: aload_0        
        //  4706: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4709: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4712: if_acmpne       4810
        //  4715: aload_0        
        //  4716: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  4719: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4722: if_acmpne       4810
        //  4725: goto            4732
        //  4728: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4731: athrow         
        //  4732: aload_0        
        //  4733: aload_0        
        //  4734: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  4737: aload_0        
        //  4738: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  4741: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.ASSIGNMENT_WRAP:I
        //  4744: iconst_1       
        //  4745: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapOnly:(Ljava/lang/Object;IZ)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  4748: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //  4751: aload_0        
        //  4752: ldc             "group variable name"
        //  4754: aload_0        
        //  4755: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //  4758: invokespecial   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.a:(Ljava/lang/Object;Lcom/intellij/lang/ASTNode;)Lcom/intellij/formatting/Alignment;
        //  4761: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  4764: dup            
        //  4765: ifnonnull       4809
        //  4768: goto            4775
        //  4771: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4774: athrow         
        //  4775: new             Ljava/lang/IllegalStateException;
        //  4778: dup            
        //  4779: ldc             "@NotNull method %s.%s must not return null"
        //  4781: ldc             2
        //  4783: anewarray       Ljava/lang/Object;
        //  4786: dup            
        //  4787: ldc             0
        //  4789: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  4791: aastore        
        //  4792: dup            
        //  4793: ldc             1
        //  4795: ldc             "doCalculate"
        //  4797: aastore        
        //  4798: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  4801: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  4804: athrow         
        //  4805: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4808: athrow         
        //  4809: areturn        
        //  4810: aload_0        
        //  4811: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.parentType:Lcom/intellij/psi/tree/IElementType;
        //  4814: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4817: if_acmpne       5058
        //  4820: aload_0        
        //  4821: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4824: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4827: if_acmpeq       4854
        //  4830: goto            4837
        //  4833: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4836: athrow         
        //  4837: aload_0        
        //  4838: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4841: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ASSIGNMENT_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4844: if_acmpne       5058
        //  4847: goto            4854
        //  4850: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4853: athrow         
        //  4854: aload_0        
        //  4855: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //  4858: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4861: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.isOrFollows:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Z
        //  4864: ifeq            5058
        //  4867: goto            4874
        //  4870: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4873: athrow         
        //  4874: aload_0        
        //  4875: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  4878: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4881: if_acmpne       4899
        //  4884: goto            4891
        //  4887: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4890: athrow         
        //  4891: iconst_1       
        //  4892: goto            4900
        //  4895: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4898: athrow         
        //  4899: iconst_0       
        //  4900: istore_1       
        //  4901: aconst_null    
        //  4902: astore_2       
        //  4903: iload_1        
        //  4904: ifeq            4931
        //  4907: aload_0        
        //  4908: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //  4911: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.a:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //  4914: astore_3       
        //  4915: aload_0        
        //  4916: aload_3        
        //  4917: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //  4920: aload_3        
        //  4921: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //  4924: checkcast       Lcom/intellij/lang/ASTNode;
        //  4927: invokespecial   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.a:(Ljava/lang/Object;Lcom/intellij/lang/ASTNode;)Lcom/intellij/formatting/Alignment;
        //  4930: astore_2       
        //  4931: aload_0        
        //  4932: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  4935: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.PLACE_ASSIGNMENT_SIGN_ON_NEXT_LINE:Z
        //  4938: iload_1        
        //  4939: if_icmpne       5015
        //  4942: aload_0        
        //  4943: ldc             "assignment wrap"
        //  4945: aload_0        
        //  4946: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  4949: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.ASSIGNMENT_WRAP:I
        //  4952: aload_0        
        //  4953: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  4956: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.PLACE_ASSIGNMENT_SIGN_ON_NEXT_LINE:Z
        //  4959: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapOnly:(Ljava/lang/Object;IZ)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  4962: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //  4965: aload_2        
        //  4966: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  4969: dup            
        //  4970: ifnonnull       5014
        //  4973: goto            4980
        //  4976: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4979: athrow         
        //  4980: new             Ljava/lang/IllegalStateException;
        //  4983: dup            
        //  4984: ldc             "@NotNull method %s.%s must not return null"
        //  4986: ldc             2
        //  4988: anewarray       Ljava/lang/Object;
        //  4991: dup            
        //  4992: ldc             0
        //  4994: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  4996: aastore        
        //  4997: dup            
        //  4998: ldc             1
        //  5000: ldc             "doCalculate"
        //  5002: aastore        
        //  5003: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  5006: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  5009: athrow         
        //  5010: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5013: athrow         
        //  5014: areturn        
        //  5015: aload_2        
        //  5016: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  5019: dup            
        //  5020: ifnonnull       5057
        //  5023: new             Ljava/lang/IllegalStateException;
        //  5026: dup            
        //  5027: ldc             "@NotNull method %s.%s must not return null"
        //  5029: ldc             2
        //  5031: anewarray       Ljava/lang/Object;
        //  5034: dup            
        //  5035: ldc             0
        //  5037: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  5039: aastore        
        //  5040: dup            
        //  5041: ldc             1
        //  5043: ldc             "doCalculate"
        //  5045: aastore        
        //  5046: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  5049: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  5052: athrow         
        //  5053: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5056: athrow         
        //  5057: areturn        
        //  5058: aload_0        
        //  5059: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  5062: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CONDITIONAL_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5065: if_acmpne       5353
        //  5068: aload_0        
        //  5069: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  5072: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.IN_LINE_SHORT_TERNARY_OPERATOR:Z
        //  5075: ifeq            5124
        //  5078: goto            5085
        //  5081: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5084: athrow         
        //  5085: aload_0        
        //  5086: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //  5089: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.QUEST:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  5092: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //  5097: invokestatic    com/intellij/psi/formatter/FormatterUtil.getNextNonWhitespaceSibling:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //  5100: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //  5103: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  5106: if_acmpne       5124
        //  5109: goto            5116
        //  5112: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5115: athrow         
        //  5116: iconst_1       
        //  5117: goto            5125
        //  5120: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5123: athrow         
        //  5124: iconst_0       
        //  5125: istore_1       
        //  5126: aload_0        
        //  5127: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  5130: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.QUEST:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  5133: if_acmpeq       5164
        //  5136: aload_0        
        //  5137: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  5140: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  5143: if_acmpne       5172
        //  5146: goto            5153
        //  5149: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5152: athrow         
        //  5153: iload_1        
        //  5154: ifne            5172
        //  5157: goto            5164
        //  5160: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5163: athrow         
        //  5164: iconst_1       
        //  5165: goto            5173
        //  5168: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5171: athrow         
        //  5172: iconst_0       
        //  5173: istore_2       
        //  5174: iload_2        
        //  5175: aload_0        
        //  5176: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  5179: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.TERNARY_OPERATION_SIGNS_ON_NEXT_LINE:Z
        //  5182: if_icmpne       5221
        //  5185: iload_1        
        //  5186: ifeq            5213
        //  5189: goto            5196
        //  5192: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5195: athrow         
        //  5196: aload_0        
        //  5197: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  5200: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  5203: if_acmpeq       5221
        //  5206: goto            5213
        //  5209: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5212: athrow         
        //  5213: iconst_1       
        //  5214: goto            5222
        //  5217: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5220: athrow         
        //  5221: iconst_0       
        //  5222: istore_3       
        //  5223: iload_3        
        //  5224: ifeq            5267
        //  5227: aload_0        
        //  5228: iload_2        
        //  5229: ifeq            5248
        //  5232: goto            5239
        //  5235: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5238: athrow         
        //  5239: ldc             "ternary operator wrap"
        //  5241: goto            5250
        //  5244: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5247: athrow         
        //  5248: ldc             "ternary wrap"
        //  5250: aload_0        
        //  5251: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  5254: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.TERNARY_OPERATION_WRAP:I
        //  5257: iload_2        
        //  5258: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapOnly:(Ljava/lang/Object;IZ)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  5261: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //  5264: goto            5268
        //  5267: aconst_null    
        //  5268: aload_0        
        //  5269: iload_2        
        //  5270: ifeq            5282
        //  5273: ldc             "ternary operator alignment"
        //  5275: goto            5284
        //  5278: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5281: athrow         
        //  5282: ldc             "ternary alignment"
        //  5284: aload_0        
        //  5285: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  5288: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.ALIGN_MULTILINE_TERNARY_OPERATION:Z
        //  5291: iload_2        
        //  5292: ifeq            5304
        //  5295: ldc             "ternary alignment"
        //  5297: goto            5305
        //  5300: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5303: athrow         
        //  5304: aconst_null    
        //  5305: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.alignOnly:(Ljava/lang/Object;ZLjava/lang/Object;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  5308: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.alignment:Lcom/intellij/formatting/Alignment;
        //  5311: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  5314: dup            
        //  5315: ifnonnull       5352
        //  5318: new             Ljava/lang/IllegalStateException;
        //  5321: dup            
        //  5322: ldc             "@NotNull method %s.%s must not return null"
        //  5324: ldc             2
        //  5326: anewarray       Ljava/lang/Object;
        //  5329: dup            
        //  5330: ldc             0
        //  5332: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  5334: aastore        
        //  5335: dup            
        //  5336: ldc             1
        //  5338: ldc             "doCalculate"
        //  5340: aastore        
        //  5341: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  5344: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  5347: athrow         
        //  5348: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5351: athrow         
        //  5352: areturn        
        //  5353: aload_0        
        //  5354: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  5357: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isCollectionOrStructureInitializer:(Lcom/intellij/psi/tree/IElementType;)Z
        //  5360: ifeq            5655
        //  5363: aload_0        
        //  5364: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  5367: aload_0        
        //  5368: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  5371: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.ARRAY_INITIALIZER_COMMA_ON_NEXT_LINE:Z
        //  5374: invokespecial   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.a:(Lcom/intellij/psi/tree/TokenSet;Z)Z
        //  5377: ifeq            5655
        //  5380: goto            5387
        //  5383: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5386: athrow         
        //  5387: aload_0        
        //  5388: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  5391: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.ALIGN_INIT_LIST_IN_COLUMNS:Z
        //  5394: ifeq            5486
        //  5397: goto            5404
        //  5400: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5403: athrow         
        //  5404: aload_0        
        //  5405: invokespecial   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.e:()Z
        //  5408: ifeq            5486
        //  5411: goto            5418
        //  5414: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5417: athrow         
        //  5418: aload_0        
        //  5419: invokespecial   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.g:()Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  5422: aload_0        
        //  5423: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //  5426: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Lcom/intellij/lang/ASTNode;)Ljava/lang/String;
        //  5429: aload_0        
        //  5430: invokedynamic   compute:(Lcom/jetbrains/cidr/lang/formatting/OCWrappingProcessor;)Lcom/intellij/openapi/util/Computable;
        //  5435: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.get:(Ljava/lang/Object;Lcom/intellij/openapi/util/Computable;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  5440: dup            
        //  5441: ifnonnull       5485
        //  5444: goto            5451
        //  5447: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5450: athrow         
        //  5451: new             Ljava/lang/IllegalStateException;
        //  5454: dup            
        //  5455: ldc             "@NotNull method %s.%s must not return null"
        //  5457: ldc             2
        //  5459: anewarray       Ljava/lang/Object;
        //  5462: dup            
        //  5463: ldc             0
        //  5465: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  5467: aastore        
        //  5468: dup            
        //  5469: ldc             1
        //  5471: ldc             "doCalculate"
        //  5473: aastore        
        //  5474: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  5477: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  5480: athrow         
        //  5481: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5484: athrow         
        //  5485: areturn        
        //  5486: aload_0        
        //  5487: aload_0        
        //  5488: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  5491: aload_0        
        //  5492: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  5495: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.ARRAY_INITIALIZER_WRAP:I
        //  5498: aload_0        
        //  5499: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  5502: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.ALIGN_MULTILINE_ARRAY_INITIALIZER_EXPRESSION:Z
        //  5505: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapAndAlign:(Ljava/lang/Object;IZ)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  5508: astore_1       
        //  5509: aload_0        
        //  5510: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  5513: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.NS_DICTIONARY_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5516: if_acmpne       5562
        //  5519: aload_0        
        //  5520: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //  5523: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  5526: aload_0        
        //  5527: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  5530: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  5533: if_acmpne       5553
        //  5536: goto            5543
        //  5539: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5542: athrow         
        //  5543: getstatic       com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.COMMENTS_OR_EXPRESSION:Lcom/intellij/psi/tree/TokenSet;
        //  5546: goto            5556
        //  5549: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5552: athrow         
        //  5553: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMENTS:Lcom/intellij/psi/tree/TokenSet;
        //  5556: invokestatic    com/intellij/psi/formatter/FormatterUtil.isFollowedBy:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;Lcom/intellij/psi/tree/TokenSet;)Z
        //  5559: ifeq            5609
        //  5562: aload_1        
        //  5563: dup            
        //  5564: ifnonnull       5608
        //  5567: goto            5574
        //  5570: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5573: athrow         
        //  5574: new             Ljava/lang/IllegalStateException;
        //  5577: dup            
        //  5578: ldc             "@NotNull method %s.%s must not return null"
        //  5580: ldc             2
        //  5582: anewarray       Ljava/lang/Object;
        //  5585: dup            
        //  5586: ldc             0
        //  5588: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  5590: aastore        
        //  5591: dup            
        //  5592: ldc             1
        //  5594: ldc             "doCalculate"
        //  5596: aastore        
        //  5597: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  5600: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  5603: athrow         
        //  5604: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5607: athrow         
        //  5608: areturn        
        //  5609: aload_1        
        //  5610: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.alignment:Lcom/intellij/formatting/Alignment;
        //  5613: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  5616: dup            
        //  5617: ifnonnull       5654
        //  5620: new             Ljava/lang/IllegalStateException;
        //  5623: dup            
        //  5624: ldc             "@NotNull method %s.%s must not return null"
        //  5626: ldc             2
        //  5628: anewarray       Ljava/lang/Object;
        //  5631: dup            
        //  5632: ldc             0
        //  5634: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  5636: aastore        
        //  5637: dup            
        //  5638: ldc             1
        //  5640: ldc             "doCalculate"
        //  5642: aastore        
        //  5643: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  5646: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  5649: athrow         
        //  5650: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5653: athrow         
        //  5654: areturn        
        //  5655: aload_0        
        //  5656: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  5659: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.NS_DICTIONARY_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5662: if_acmpne       5745
        //  5665: aload_0        
        //  5666: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  5669: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  5672: if_acmpne       5745
        //  5675: goto            5682
        //  5678: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5681: athrow         
        //  5682: aload_0        
        //  5683: ldc             "group dictionary pairs"
        //  5685: aload_0        
        //  5686: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.myData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //  5689: aload_0        
        //  5690: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childNode:Lcom/intellij/lang/ASTNode;
        //  5693: invokespecial   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.a:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;Lcom/intellij/lang/ASTNode;)Lcom/intellij/formatting/Alignment;
        //  5696: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  5699: dup            
        //  5700: ifnonnull       5744
        //  5703: goto            5710
        //  5706: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5709: athrow         
        //  5710: new             Ljava/lang/IllegalStateException;
        //  5713: dup            
        //  5714: ldc             "@NotNull method %s.%s must not return null"
        //  5716: ldc             2
        //  5718: anewarray       Ljava/lang/Object;
        //  5721: dup            
        //  5722: ldc             0
        //  5724: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  5726: aastore        
        //  5727: dup            
        //  5728: ldc             1
        //  5730: ldc             "doCalculate"
        //  5732: aastore        
        //  5733: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  5736: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  5739: athrow         
        //  5740: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5743: athrow         
        //  5744: areturn        
        //  5745: aload_0        
        //  5746: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  5749: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ENUM:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5752: if_acmpne       5858
        //  5755: aload_0        
        //  5756: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5759: aload_0        
        //  5760: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  5763: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.ENUM_CONSTANTS_COMMA_ON_NEXT_LINE:Z
        //  5766: invokespecial   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.a:(Lcom/intellij/psi/tree/IElementType;Z)Z
        //  5769: ifeq            5858
        //  5772: goto            5779
        //  5775: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5778: athrow         
        //  5779: aload_0        
        //  5780: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  5783: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.ENUM_CONSTANTS_WRAP:I
        //  5786: istore_1       
        //  5787: aload_0        
        //  5788: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  5791: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.KEEP_STRUCTURES_IN_ONE_LINE:Z
        //  5794: ifeq            5811
        //  5797: iload_1        
        //  5798: iconst_2       
        //  5799: if_icmpne       5811
        //  5802: goto            5809
        //  5805: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5808: athrow         
        //  5809: iconst_4       
        //  5810: istore_1       
        //  5811: aload_0        
        //  5812: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ENUM:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5815: iload_1        
        //  5816: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapOnly:(Ljava/lang/Object;I)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  5819: dup            
        //  5820: ifnonnull       5857
        //  5823: new             Ljava/lang/IllegalStateException;
        //  5826: dup            
        //  5827: ldc             "@NotNull method %s.%s must not return null"
        //  5829: ldc             2
        //  5831: anewarray       Ljava/lang/Object;
        //  5834: dup            
        //  5835: ldc             0
        //  5837: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  5839: aastore        
        //  5840: dup            
        //  5841: ldc             1
        //  5843: ldc             "doCalculate"
        //  5845: aastore        
        //  5846: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  5849: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  5852: athrow         
        //  5853: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5856: athrow         
        //  5857: areturn        
        //  5858: aload_0        
        //  5859: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  5862: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROPERTY:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5865: if_acmpne       5964
        //  5868: aload_0        
        //  5869: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  5872: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5875: if_acmpeq       5902
        //  5878: goto            5885
        //  5881: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5884: athrow         
        //  5885: aload_0        
        //  5886: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.childType:Lcom/intellij/psi/tree/IElementType;
        //  5889: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5892: if_acmpne       5964
        //  5895: goto            5902
        //  5898: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5901: athrow         
        //  5902: aload_0        
        //  5903: aload_0        
        //  5904: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  5907: aload_0        
        //  5908: getfield        com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  5911: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.WRAP_PROPERTY_DECLARATION:I
        //  5914: iconst_1       
        //  5915: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapOnly:(Ljava/lang/Object;IZ)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  5918: dup            
        //  5919: ifnonnull       5963
        //  5922: goto            5929
        //  5925: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5928: athrow         
        //  5929: new             Ljava/lang/IllegalStateException;
        //  5932: dup            
        //  5933: ldc             "@NotNull method %s.%s must not return null"
        //  5935: ldc             2
        //  5937: anewarray       Ljava/lang/Object;
        //  5940: dup            
        //  5941: ldc             0
        //  5943: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  5945: aastore        
        //  5946: dup            
        //  5947: ldc             1
        //  5949: ldc             "doCalculate"
        //  5951: aastore        
        //  5952: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  5955: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  5958: athrow         
        //  5959: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5962: athrow         
        //  5963: areturn        
        //  5964: aconst_null    
        //  5965: aconst_null    
        //  5966: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //  5969: dup            
        //  5970: ifnonnull       6007
        //  5973: new             Ljava/lang/IllegalStateException;
        //  5976: dup            
        //  5977: ldc             "@NotNull method %s.%s must not return null"
        //  5979: ldc             2
        //  5981: anewarray       Ljava/lang/Object;
        //  5984: dup            
        //  5985: ldc             0
        //  5987: ldc             "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor"
        //  5989: aastore        
        //  5990: dup            
        //  5991: ldc             1
        //  5993: ldc             "doCalculate"
        //  5995: aastore        
        //  5996: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  5999: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  6002: athrow         
        //  6003: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  6006: athrow         
        //  6007: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      18     21     25     Ljava/lang/IllegalArgumentException;
        //  10     55     55     59     Ljava/lang/IllegalArgumentException;
        //  60     95     98     102    Ljava/lang/IllegalArgumentException;
        //  70     132    132    136    Ljava/lang/IllegalArgumentException;
        //  137    164    167    171    Ljava/lang/IllegalArgumentException;
        //  147    196    199    203    Ljava/lang/IllegalArgumentException;
        //  171    233    233    237    Ljava/lang/IllegalArgumentException;
        //  238    258    261    265    Ljava/lang/IllegalArgumentException;
        //  248    273    276    280    Ljava/lang/IllegalArgumentException;
        //  265    310    310    314    Ljava/lang/IllegalArgumentException;
        //  315    335    338    342    Ljava/lang/IllegalArgumentException;
        //  325    359    362    366    Ljava/lang/IllegalArgumentException;
        //  342    391    394    398    Ljava/lang/IllegalArgumentException;
        //  366    428    428    432    Ljava/lang/IllegalArgumentException;
        //  433    456    459    463    Ljava/lang/IllegalArgumentException;
        //  443    471    474    478    Ljava/lang/IllegalArgumentException;
        //  463    508    508    512    Ljava/lang/IllegalArgumentException;
        //  513    540    543    547    Ljava/lang/IllegalArgumentException;
        //  523    572    575    579    Ljava/lang/IllegalArgumentException;
        //  547    609    609    613    Ljava/lang/IllegalArgumentException;
        //  614    634    637    641    Ljava/lang/IllegalArgumentException;
        //  624    649    652    656    Ljava/lang/IllegalArgumentException;
        //  641    686    686    690    Ljava/lang/IllegalArgumentException;
        //  691    718    721    725    Ljava/lang/IllegalArgumentException;
        //  701    750    753    757    Ljava/lang/IllegalArgumentException;
        //  725    787    787    791    Ljava/lang/IllegalArgumentException;
        //  792    815    818    822    Ljava/lang/IllegalArgumentException;
        //  802    830    833    837    Ljava/lang/IllegalArgumentException;
        //  822    867    867    871    Ljava/lang/IllegalArgumentException;
        //  872    899    902    906    Ljava/lang/IllegalArgumentException;
        //  882    931    934    938    Ljava/lang/IllegalArgumentException;
        //  906    968    968    972    Ljava/lang/IllegalArgumentException;
        //  973    996    999    1003   Ljava/lang/IllegalArgumentException;
        //  983    1011   1014   1018   Ljava/lang/IllegalArgumentException;
        //  1003   1048   1048   1052   Ljava/lang/IllegalArgumentException;
        //  1053   1073   1076   1080   Ljava/lang/IllegalArgumentException;
        //  1063   1090   1093   1097   Ljava/lang/IllegalArgumentException;
        //  1080   1107   1110   1114   Ljava/lang/IllegalArgumentException;
        //  1097   1121   1121   1125   Ljava/lang/IllegalArgumentException;
        //  1152   1196   1196   1200   Ljava/lang/IllegalArgumentException;
        //  1162   1208   1211   1215   Ljava/lang/IllegalArgumentException;
        //  1200   1245   1245   1249   Ljava/lang/IllegalArgumentException;
        //  1250   1288   1288   1292   Ljava/lang/IllegalArgumentException;
        //  1293   1331   1331   1335   Ljava/lang/IllegalArgumentException;
        //  1336   1363   1366   1370   Ljava/lang/IllegalArgumentException;
        //  1353   1404   1404   1408   Ljava/lang/IllegalArgumentException;
        //  1370   1426   1429   1433   Ljava/lang/IllegalArgumentException;
        //  1408   1463   1463   1467   Ljava/lang/IllegalArgumentException;
        //  1468   1516   1516   1520   Ljava/lang/IllegalArgumentException;
        //  1521   1569   1569   1573   Ljava/lang/IllegalArgumentException;
        //  1574   1609   1609   1613   Ljava/lang/IllegalArgumentException;
        //  1614   1634   1637   1641   Ljava/lang/IllegalArgumentException;
        //  1624   1649   1652   1656   Ljava/lang/IllegalArgumentException;
        //  1641   1686   1686   1690   Ljava/lang/IllegalArgumentException;
        //  1720   1764   1764   1768   Ljava/lang/IllegalArgumentException;
        //  1730   1776   1779   1783   Ljava/lang/IllegalArgumentException;
        //  1768   1813   1813   1817   Ljava/lang/IllegalArgumentException;
        //  1818   1859   1859   1863   Ljava/lang/IllegalArgumentException;
        //  1864   1905   1905   1909   Ljava/lang/IllegalArgumentException;
        //  1910   1940   1943   1947   Ljava/lang/IllegalArgumentException;
        //  1927   1980   1980   1984   Ljava/lang/IllegalArgumentException;
        //  1947   2002   2005   2009   Ljava/lang/IllegalArgumentException;
        //  1984   2039   2039   2043   Ljava/lang/IllegalArgumentException;
        //  2044   2095   2095   2099   Ljava/lang/IllegalArgumentException;
        //  2100   2151   2151   2155   Ljava/lang/IllegalArgumentException;
        //  2156   2191   2191   2195   Ljava/lang/IllegalArgumentException;
        //  2196   2216   2219   2223   Ljava/lang/IllegalArgumentException;
        //  2206   2231   2234   2238   Ljava/lang/IllegalArgumentException;
        //  2223   2268   2268   2272   Ljava/lang/IllegalArgumentException;
        //  2273   2296   2299   2303   Ljava/lang/IllegalArgumentException;
        //  2283   2329   2332   2336   Ljava/lang/IllegalArgumentException;
        //  2303   2366   2366   2370   Ljava/lang/IllegalArgumentException;
        //  2371   2394   2397   2401   Ljava/lang/IllegalArgumentException;
        //  2381   2411   2411   2415   Ljava/lang/IllegalArgumentException;
        //  2422   2468   2468   2472   Ljava/lang/IllegalArgumentException;
        //  2546   2573   2573   2577   Ljava/lang/IllegalArgumentException;
        //  2577   2593   2593   2597   Ljava/lang/IllegalArgumentException;
        //  2607   2624   2624   2628   Ljava/lang/IllegalArgumentException;
        //  2645   2653   2653   2657   Ljava/lang/IllegalArgumentException;
        //  2665   2699   2699   2703   Ljava/lang/IllegalArgumentException;
        //  2706   2758   2758   2762   Ljava/lang/IllegalArgumentException;
        //  2763   2799   2802   2806   Ljava/lang/IllegalArgumentException;
        //  2773   2836   2836   2840   Ljava/lang/IllegalArgumentException;
        //  2841   2877   2880   2884   Ljava/lang/IllegalArgumentException;
        //  2851   2901   2904   2908   Ljava/lang/IllegalArgumentException;
        //  2884   2938   2938   2942   Ljava/lang/IllegalArgumentException;
        //  2943   2992   2992   2996   Ljava/lang/IllegalArgumentException;
        //  3022   3041   3041   3045   Ljava/lang/IllegalArgumentException;
        //  3047   3065   3068   3072   Ljava/lang/IllegalArgumentException;
        //  3057   3086   3089   3093   Ljava/lang/IllegalArgumentException;
        //  3072   3110   3113   3117   Ljava/lang/IllegalArgumentException;
        //  3093   3147   3147   3151   Ljava/lang/IllegalArgumentException;
        //  3152   3189   3189   3193   Ljava/lang/IllegalArgumentException;
        //  3194   3243   3243   3247   Ljava/lang/IllegalArgumentException;
        //  3267   3283   3286   3290   Ljava/lang/IllegalArgumentException;
        //  3271   3297   3300   3304   Ljava/lang/IllegalArgumentException;
        //  3290   3310   3310   3314   Ljava/lang/IllegalArgumentException;
        //  3325   3348   3351   3355   Ljava/lang/IllegalArgumentException;
        //  3398   3413   3416   3420   Ljava/lang/IllegalArgumentException;
        //  3403   3424   3427   3431   Ljava/lang/IllegalArgumentException;
        //  3420   3437   3440   3444   Ljava/lang/IllegalArgumentException;
        //  3431   3454   3457   3461   Ljava/lang/IllegalArgumentException;
        //  3444   3475   3475   3479   Ljava/lang/IllegalArgumentException;
        //  3484   3521   3521   3525   Ljava/lang/IllegalArgumentException;
        //  3526   3562   3562   3566   Ljava/lang/IllegalArgumentException;
        //  3590   3603   3603   3607   Ljava/lang/IllegalArgumentException;
        //  3626   3676   3676   3680   Ljava/lang/IllegalArgumentException;
        //  3681   3718   3718   3722   Ljava/lang/IllegalArgumentException;
        //  3723   3746   3749   3753   Ljava/lang/IllegalArgumentException;
        //  3733   3782   3785   3789   Ljava/lang/IllegalArgumentException;
        //  3753   3819   3819   3823   Ljava/lang/IllegalArgumentException;
        //  3824   3844   3847   3851   Ljava/lang/IllegalArgumentException;
        //  3834   3861   3864   3868   Ljava/lang/IllegalArgumentException;
        //  3851   3878   3881   3885   Ljava/lang/IllegalArgumentException;
        //  3868   3895   3898   3902   Ljava/lang/IllegalArgumentException;
        //  3885   3916   3919   3923   Ljava/lang/IllegalArgumentException;
        //  3902   3933   3933   3937   Ljava/lang/IllegalArgumentException;
        //  3944   3981   3981   3985   Ljava/lang/IllegalArgumentException;
        //  3986   4006   4009   4013   Ljava/lang/IllegalArgumentException;
        //  3996   4023   4026   4030   Ljava/lang/IllegalArgumentException;
        //  4013   4048   4051   4055   Ljava/lang/IllegalArgumentException;
        //  4030   4085   4085   4089   Ljava/lang/IllegalArgumentException;
        //  4090   4110   4113   4117   Ljava/lang/IllegalArgumentException;
        //  4100   4127   4130   4134   Ljava/lang/IllegalArgumentException;
        //  4117   4144   4147   4151   Ljava/lang/IllegalArgumentException;
        //  4134   4165   4168   4172   Ljava/lang/IllegalArgumentException;
        //  4151   4182   4182   4186   Ljava/lang/IllegalArgumentException;
        //  4193   4230   4230   4234   Ljava/lang/IllegalArgumentException;
        //  4260   4305   4308   4312   Ljava/lang/IllegalArgumentException;
        //  4264   4338   4341   4345   Ljava/lang/IllegalArgumentException;
        //  4312   4375   4375   4379   Ljava/lang/IllegalArgumentException;
        //  4436   4471   4471   4475   Ljava/lang/IllegalArgumentException;
        //  4476   4496   4499   4503   Ljava/lang/IllegalArgumentException;
        //  4486   4507   4507   4511   Ljava/lang/IllegalArgumentException;
        //  4571   4598   4598   4602   Ljava/lang/IllegalArgumentException;
        //  4615   4657   4657   4661   Ljava/lang/IllegalArgumentException;
        //  4662   4700   4700   4704   Ljava/lang/IllegalArgumentException;
        //  4705   4725   4728   4732   Ljava/lang/IllegalArgumentException;
        //  4715   4768   4771   4775   Ljava/lang/IllegalArgumentException;
        //  4732   4805   4805   4809   Ljava/lang/IllegalArgumentException;
        //  4810   4830   4833   4837   Ljava/lang/IllegalArgumentException;
        //  4820   4847   4850   4854   Ljava/lang/IllegalArgumentException;
        //  4837   4867   4870   4874   Ljava/lang/IllegalArgumentException;
        //  4854   4884   4887   4891   Ljava/lang/IllegalArgumentException;
        //  4874   4895   4895   4899   Ljava/lang/IllegalArgumentException;
        //  4931   4973   4976   4980   Ljava/lang/IllegalArgumentException;
        //  4942   5010   5010   5014   Ljava/lang/IllegalArgumentException;
        //  5015   5053   5053   5057   Ljava/lang/IllegalArgumentException;
        //  5058   5078   5081   5085   Ljava/lang/IllegalArgumentException;
        //  5068   5109   5112   5116   Ljava/lang/IllegalArgumentException;
        //  5085   5120   5120   5124   Ljava/lang/IllegalArgumentException;
        //  5126   5146   5149   5153   Ljava/lang/IllegalArgumentException;
        //  5136   5157   5160   5164   Ljava/lang/IllegalArgumentException;
        //  5153   5168   5168   5172   Ljava/lang/IllegalArgumentException;
        //  5174   5189   5192   5196   Ljava/lang/IllegalArgumentException;
        //  5185   5206   5209   5213   Ljava/lang/IllegalArgumentException;
        //  5196   5217   5217   5221   Ljava/lang/IllegalArgumentException;
        //  5223   5232   5235   5239   Ljava/lang/IllegalArgumentException;
        //  5227   5244   5244   5248   Ljava/lang/IllegalArgumentException;
        //  5268   5278   5278   5282   Ljava/lang/IllegalArgumentException;
        //  5284   5300   5300   5304   Ljava/lang/IllegalArgumentException;
        //  5305   5348   5348   5352   Ljava/lang/IllegalArgumentException;
        //  5353   5380   5383   5387   Ljava/lang/IllegalArgumentException;
        //  5363   5397   5400   5404   Ljava/lang/IllegalArgumentException;
        //  5387   5411   5414   5418   Ljava/lang/IllegalArgumentException;
        //  5404   5444   5447   5451   Ljava/lang/IllegalArgumentException;
        //  5418   5481   5481   5485   Ljava/lang/IllegalArgumentException;
        //  5509   5536   5539   5543   Ljava/lang/IllegalArgumentException;
        //  5519   5549   5549   5553   Ljava/lang/IllegalArgumentException;
        //  5556   5567   5570   5574   Ljava/lang/IllegalArgumentException;
        //  5562   5604   5604   5608   Ljava/lang/IllegalArgumentException;
        //  5609   5650   5650   5654   Ljava/lang/IllegalArgumentException;
        //  5655   5675   5678   5682   Ljava/lang/IllegalArgumentException;
        //  5665   5703   5706   5710   Ljava/lang/IllegalArgumentException;
        //  5682   5740   5740   5744   Ljava/lang/IllegalArgumentException;
        //  5745   5772   5775   5779   Ljava/lang/IllegalArgumentException;
        //  5787   5802   5805   5809   Ljava/lang/IllegalArgumentException;
        //  5811   5853   5853   5857   Ljava/lang/IllegalArgumentException;
        //  5858   5878   5881   5885   Ljava/lang/IllegalArgumentException;
        //  5868   5895   5898   5902   Ljava/lang/IllegalArgumentException;
        //  5885   5922   5925   5929   Ljava/lang/IllegalArgumentException;
        //  5902   5959   5959   5963   Ljava/lang/IllegalArgumentException;
        //  5964   6003   6003   6007   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0171:
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
    
    public static OCFormatterInfo getJoinFormatting(final OCLocalFormatterData ocLocalFormatterData) {
        return ocLocalFormatterData.get("Never wrap", (Computable<OCFormatterInfo>)(() -> OCFormatterInfo.createInfo(Wrap.createWrap(0, true), null)));
    }
    
    public OCFormatterInfo getJoinFormatting() {
        return getJoinFormatting(this.myData);
    }
    
    public static OCFormatterInfo getNewLineFormatting(final OCLocalFormatterData ocLocalFormatterData) {
        return ocLocalFormatterData.get("Always wrap", (Computable<OCFormatterInfo>)(() -> OCFormatterInfo.createInfo(Wrap.createWrap(2, true), null)));
    }
    
    public static OCFormatterInfo getIfLongFormatting(final OCLocalFormatterData ocLocalFormatterData) {
        return ocLocalFormatterData.get("If long wrap", (Computable<OCFormatterInfo>)(() -> OCFormatterInfo.createInfo(Wrap.createWrap(5, true), null)));
    }
    
    private boolean e() {
        ASTNode astNode = null;
        for (ASTNode astNode2 = this.thisNode; OCElementUtil.getElementType(astNode2) == OCElementTypes.COMPOUND_INITIALIZER; astNode2 = astNode2.getTreeParent()) {
            astNode = astNode2;
        }
        try {
            if (astNode == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Boolean value = (Boolean)astNode.getUserData((Key)OCWrappingProcessor.IS_TABLE);
        if (value == null) {
            value = c(astNode);
            astNode.putUserData((Key)OCWrappingProcessor.IS_TABLE, (Object)value);
        }
        return value;
    }
    
    private static boolean c(final ASTNode p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //     6: astore_1       
        //     7: aload_1        
        //     8: ifnull          72
        //    11: aload_1        
        //    12: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    17: astore_2       
        //    18: aload_1        
        //    19: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.isInessential:(Lcom/intellij/lang/ASTNode;)Z
        //    22: ifne            62
        //    25: aload_2        
        //    26: instanceof      Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    29: ifne            62
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: aload_2        
        //    40: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.COMPOUND_INITIALIZER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    43: if_acmpne       60
        //    46: goto            53
        //    49: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    52: athrow         
        //    53: goto            62
        //    56: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: iconst_0       
        //    61: ireturn        
        //    62: aload_1        
        //    63: invokeinterface com/intellij/lang/ASTNode.getTreeNext:()Lcom/intellij/lang/ASTNode;
        //    68: astore_1       
        //    69: goto            7
        //    72: iconst_1       
        //    73: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  18     32     35     39     Ljava/lang/IllegalArgumentException;
        //  25     46     49     53     Ljava/lang/IllegalArgumentException;
        //  39     56     56     60     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0039:
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
    OCFormatterInfo wrapOnly(final Object o, final int n) {
        OCFormatterInfo wrapOnly;
        try {
            wrapOnly = this.wrapOnly(o, n, false);
            if (wrapOnly == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor", "wrapOnly"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return wrapOnly;
    }
    
    @NotNull
    OCFormatterInfo wrapOnly(final Object o, final int n, final boolean b) {
        OCFormatterInfo wrapAndAlign;
        try {
            wrapAndAlign = this.wrapAndAlign(o, true, n, b, null, false, null);
            if (wrapAndAlign == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor", "wrapOnly"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return wrapAndAlign;
    }
    
    @NotNull
    OCFormatterInfo alignOnly(final Object o, final boolean b) {
        OCFormatterInfo alignOnly;
        try {
            alignOnly = this.alignOnly(o, b, null);
            if (alignOnly == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor", "alignOnly"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return alignOnly;
    }
    
    @NotNull
    OCFormatterInfo alignOnly(final Object o, final boolean b, final Object o2) {
        Alignment alignment = null;
        if (o2 != null) {
            final OCFormatterInfo value = this.myData.get(o2);
            if (value != null) {
                alignment = value.alignment;
            }
        }
        OCFormatterInfo wrapAndAlign;
        try {
            wrapAndAlign = this.wrapAndAlign(o, false, -1, false, null, b, alignment);
            if (wrapAndAlign == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor", "alignOnly"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return wrapAndAlign;
    }
    
    @NotNull
    OCFormatterInfo wrapAndAlign(final Object o, final int n, final boolean b) {
        OCFormatterInfo wrapAndAlign;
        try {
            wrapAndAlign = this.wrapAndAlign(o, n, false, b);
            if (wrapAndAlign == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor", "wrapAndAlign"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return wrapAndAlign;
    }
    
    @NotNull
    OCFormatterInfo wrapAndAlign(final Object o, final int n, final boolean b, final boolean b2) {
        OCFormatterInfo wrapAndAlign;
        try {
            wrapAndAlign = this.wrapAndAlign(o, true, n, b, null, b2, null);
            if (wrapAndAlign == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor", "wrapAndAlign"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return wrapAndAlign;
    }
    
    @NotNull
    OCFormatterInfo wrapAndAlign(final Object o, final boolean b, final int n, final boolean b2, @Nullable final Wrap wrap, final boolean b3, @Nullable final Alignment alignment) {
        OCFormatterInfo value;
        try {
            value = this.myData.get(o, (Computable<OCFormatterInfo>)(() -> {
                Wrap childWrap = null;
                Label_0021: {
                    try {
                        if (b) {
                            childWrap = Wrap.createChildWrap(wrap, WrappingUtil.getWrapType(n), b2);
                            break Label_0021;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    childWrap = null;
                }
                final Wrap wrap2 = childWrap;
                Alignment alignment2 = null;
                Alignment alignment4 = null;
                Label_0058: {
                    Label_0043: {
                        try {
                            if (!b3) {
                                return OCFormatterInfo.createInfo(wrap2, alignment2);
                            }
                            final Alignment alignment3 = alignment;
                            if (alignment3 != null) {
                                break Label_0043;
                            }
                            break Label_0043;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        try {
                            final Alignment alignment3 = alignment;
                            if (alignment3 != null) {
                                alignment4 = Alignment.createChildAlignment(alignment);
                                break Label_0058;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                    }
                    alignment4 = Alignment.createAlignment();
                }
                alignment2 = alignment4;
                return OCFormatterInfo.createInfo(wrap2, alignment2);
            }));
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor", "wrapAndAlign"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return value;
    }
    
    private static boolean a(@Nullable final IElementType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.isBlock:(Lcom/intellij/psi/tree/IElementType;)Z
        //     4: ifne            35
        //     7: aload_0        
        //     8: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.isVariablesListOrStructure:(Lcom/intellij/psi/tree/IElementType;)Z
        //    11: ifne            35
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.isGlobalDeclarationScope:(Lcom/intellij/psi/tree/IElementType;)Z
        //    25: ifeq            43
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: iconst_1       
        //    36: goto            44
        //    39: invokestatic    com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: iconst_0       
        //    44: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     39     39     43     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    private Alignment a(@NotNull final Object o, @Nullable final ASTNode astNode) {
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor", "varsGroupAlignment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (!this.settings.ALIGN_GROUP_FIELD_DECLARATIONS) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return this.a(this.l(), o, astNode, OCWrappingProcessor.VARS_ALIGNMENT_CONFIG);
    }
    
    @Nullable
    private Alignment a(@NotNull final Object o, @Nullable final OCLocalFormatterData ocLocalFormatterData, @Nullable final ASTNode astNode) {
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor", "pairsGroupAlignment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (!this.ocSettings.ALIGN_DICTIONARY_PAIR_VALUES) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return this.a(ocLocalFormatterData, o, astNode, null);
    }
    
    @Nullable
    private Alignment a(@Nullable final OCLocalFormatterData ocLocalFormatterData, @NotNull final Object o, @Nullable final ASTNode astNode, @Nullable final AlignmentInColumnsConfig alignmentInColumnsConfig) {
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor", "groupAlignment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocLocalFormatterData == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final Computable computable = () -> OCFormatterInfo.createInfo(Alignment.createAlignment(true));
        Label_0092: {
            try {
                if (alignmentInColumnsConfig == null) {
                    return ocLocalFormatterData.get(o, (Computable<OCFormatterInfo>)computable).alignment;
                }
                final AlignmentInColumnsHelper alignmentInColumnsHelper = AlignmentInColumnsHelper.INSTANCE;
                final ASTNode astNode2 = astNode;
                final AlignmentInColumnsConfig alignmentInColumnsConfig2 = alignmentInColumnsConfig;
                final OCWrappingProcessor ocWrappingProcessor = this;
                final CommonCodeStyleSettings commonCodeStyleSettings = ocWrappingProcessor.settings;
                final int n = commonCodeStyleSettings.KEEP_BLANK_LINES_IN_DECLARATIONS;
                final boolean b = alignmentInColumnsHelper.useDifferentVarDeclarationAlignment(astNode2, alignmentInColumnsConfig2, n);
                if (b) {
                    break Label_0092;
                }
                return ocLocalFormatterData.get(o, (Computable<OCFormatterInfo>)computable).alignment;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final AlignmentInColumnsHelper alignmentInColumnsHelper = AlignmentInColumnsHelper.INSTANCE;
                final ASTNode astNode2 = astNode;
                final AlignmentInColumnsConfig alignmentInColumnsConfig2 = alignmentInColumnsConfig;
                final OCWrappingProcessor ocWrappingProcessor = this;
                final CommonCodeStyleSettings commonCodeStyleSettings = ocWrappingProcessor.settings;
                final int n = commonCodeStyleSettings.KEEP_BLANK_LINES_IN_DECLARATIONS;
                final boolean b = alignmentInColumnsHelper.useDifferentVarDeclarationAlignment(astNode2, alignmentInColumnsConfig2, n);
                if (b) {
                    ocLocalFormatterData.put(o, (OCFormatterInfo)computable.compute());
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return ocLocalFormatterData.get(o, (Computable<OCFormatterInfo>)computable).alignment;
    }
    
    @NotNull
    private OCLocalFormatterData l() {
        OCLocalFormatterData ocLocalFormatterData = this.myData;
        while (true) {
            try {
                if (a(ocLocalFormatterData.getType()) || ocLocalFormatterData.isNull()) {
                    break;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            ocLocalFormatterData = ocLocalFormatterData.getParent();
        }
        OCLocalFormatterData ocLocalFormatterData2;
        try {
            ocLocalFormatterData2 = ocLocalFormatterData;
            if (ocLocalFormatterData2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor", "findVarGroupParentData"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return ocLocalFormatterData2;
    }
    
    @NotNull
    private OCLocalFormatterData g() {
        OCLocalFormatterData ocLocalFormatterData = this.myData;
        OCLocalFormatterData ocLocalFormatterData2 = null;
        while (true) {
            Label_0069: {
                Label_0034: {
                    try {
                        if (ocLocalFormatterData.getParent().getType() == OCElementTypes.COMPOUND_INITIALIZER) {
                            break Label_0069;
                        }
                        ocLocalFormatterData2 = ocLocalFormatterData;
                        if (ocLocalFormatterData2 == null) {
                            break Label_0034;
                        }
                        break;
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    try {
                        ocLocalFormatterData2 = ocLocalFormatterData;
                        if (ocLocalFormatterData2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCWrappingProcessor", "findTopInitListData"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                }
                break;
            }
            ocLocalFormatterData = ocLocalFormatterData.getParent();
        }
        return ocLocalFormatterData2;
    }
    
    static {
        IS_TABLE = new Key("IS_TABLE");
        VARS_ALIGNMENT_CONFIG = new AlignmentInColumnsConfig(TokenSet.create(new IElementType[] { OCTokenTypes.IDENTIFIER }), TokenSet.orSet(new TokenSet[] { OCTokenTypes.WHITESPACES, TokenSet.create(new IElementType[] { OCTokenTypes.COMMA, OCElementTypes.MACRO_CALL }) }), TokenSet.orSet(new TokenSet[] { OCTokenTypes.COMMENTS, OCTokenTypes.TYPE_MODIFIERS }), TokenSet.EMPTY, TokenSet.create(new IElementType[] { OCElementTypes.ASSIGNMENT_EXPRESSION, OCElementTypes.DECLARATOR }));
        CHAINED_CALL_PSEUDOTYPE = new OCElementType("CHAINED_CALL_PSEUDOTYPE");
        BINARY_EXPRESSION_PSEUDOTYPE = new OCElementType("BINARY_EXPRESSION_PSEUDOTYPE");
        COMMENTS_OR_EXPRESSION = TokenSet.orSet(new TokenSet[] { OCTokenTypes.COMMENTS, OCElementTypes.EXPRESSIONS });
        TYPE_OR_EXPRESSION = TokenSet.orSet(new TokenSet[] { TokenSet.create(new IElementType[] { OCElementTypes.TYPE_ELEMENT }), OCElementTypes.EXPRESSIONS });
        LAMBDA_LIST_CAPTURE = TokenSet.orSet(new TokenSet[] { OCTokenTypes.TYPE_MODIFIERS, TokenSet.create(new IElementType[] { OCTokenTypes.EQ, OCTokenTypes.THIS_CPP_KEYWORD, OCTokenTypes.ELLIPSIS, OCElementTypes.REFERENCE_ELEMENT }) });
        MACRO_ARG_OR_EXPRESSION = TokenSet.orSet(new TokenSet[] { TokenSet.create(new IElementType[] { OCElementTypes.MACRO_ARGUMENT }), OCElementTypes.EXPRESSIONS });
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
