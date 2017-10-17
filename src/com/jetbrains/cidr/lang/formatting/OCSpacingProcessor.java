// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.formatting.Block;
import com.intellij.psi.formatter.FormatterUtil;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.psi.OCStatement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.formatting.DependentSpacingRule;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.formatting.Spacing;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import java.util.ArrayList;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;

class OCSpacingProcessor extends OCFormatProcessor
{
    private static final TokenSet KEYWORD_WITH_LIST;
    private final ASTNode nodeLeft;
    private final ASTNode nodeRight;
    private final IElementType typeLeft;
    private final IElementType typeRight;
    private final IElementType lastLeafTypeLeft;
    private final IElementType firstLeafTypeRight;
    private boolean isDependent;
    private TextRange dependentRange;
    private Boolean hasSpace;
    private int minLineFeeds;
    private Integer keepBlankLines;
    private boolean keepLineBreaks;
    private static final ArrayList[] MANDATORY_SPACE_PAIR;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    OCSpacingProcessor(@NotNull final CommonCodeStyleSettings commonCodeStyleSettings, @NotNull final OCCodeStyleSettings ocCodeStyleSettings, @NotNull final ASTNode astNode, @NotNull final ASTNode nodeLeft, @NotNull final ASTNode nodeRight) {
        if (commonCodeStyleSettings == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor", "<init>"));
        }
        if (ocCodeStyleSettings == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ocSettings", "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor", "<init>"));
        }
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor", "<init>"));
        }
        if (nodeLeft == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "childLeft", "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor", "<init>"));
        }
        if (nodeRight == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "childRight", "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor", "<init>"));
        }
        super(commonCodeStyleSettings, ocCodeStyleSettings, astNode);
        this.nodeLeft = nodeLeft;
        this.nodeRight = nodeRight;
        this.typeLeft = this.nodeLeft.getElementType();
        this.typeRight = this.nodeRight.getElementType();
        final ASTNode lastLeaf = TreeUtil.findLastLeaf(this.nodeLeft);
        final LeafElement firstLeaf = TreeUtil.findFirstLeaf(this.nodeRight);
        this.lastLeafTypeLeft = OCElementUtil.getElementType(lastLeaf);
        this.firstLeafTypeRight = OCElementUtil.getElementType((ASTNode)firstLeaf);
        this.keepLineBreaks = commonCodeStyleSettings.KEEP_LINE_BREAKS;
    }
    
    @Nullable
    public Spacing getResult(final boolean b, final boolean b2) {
        try {
            if (this.thisType == OCElementTypes.OBJC_ERROR_KEYWORD) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0085: {
            Label_0055: {
                Label_0049: {
                    try {
                        if (OCTokenTypes.COMMENTS.contains(this.typeLeft)) {
                            break Label_0049;
                        }
                        final TokenSet set = OCTokenTypes.COMMENTS;
                        final OCSpacingProcessor ocSpacingProcessor = this;
                        final IElementType elementType = ocSpacingProcessor.typeRight;
                        final boolean b3 = set.contains(elementType);
                        if (b3) {
                            break Label_0049;
                        }
                        break Label_0055;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final TokenSet set = OCTokenTypes.COMMENTS;
                        final OCSpacingProcessor ocSpacingProcessor = this;
                        final IElementType elementType = ocSpacingProcessor.typeRight;
                        final boolean b3 = set.contains(elementType);
                        if (b3) {
                            return null;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                try {
                    if (this.thisType == OCElementTypes.MACRO_PARAMETER_LIST) {
                        break Label_0085;
                    }
                    final OCSpacingProcessor ocSpacingProcessor2 = this;
                    final ASTNode astNode = ocSpacingProcessor2.nodeLeft;
                    final OCElementType ocElementType = OCElementTypes.MACRO_DEFINITION;
                    final ASTNode astNode2 = TreeUtil.findParent(astNode, ocElementType);
                    if (astNode2 != null) {
                        break Label_0085;
                    }
                    break Label_0085;
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            try {
                final OCSpacingProcessor ocSpacingProcessor2 = this;
                final ASTNode astNode = ocSpacingProcessor2.nodeLeft;
                final OCElementType ocElementType = OCElementTypes.MACRO_DEFINITION;
                final ASTNode astNode2 = TreeUtil.findParent(astNode, ocElementType);
                if (astNode2 != null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        final boolean b4 = this.b() | this.k() | this.f();
        try {
            if (!b4) {
                return null;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        int n = 0;
        Label_0166: {
            try {
                this.d();
                if (b) {
                    n = 1;
                    break Label_0166;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw b(ex7);
            }
            try {
                if (this.hasSpace == null) {
                    n = 0;
                    break Label_0166;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw b(ex8);
            }
            try {
                if (this.hasSpace) {
                    n = 1;
                    break Label_0166;
                }
            }
            catch (IllegalArgumentException ex9) {
                throw b(ex9);
            }
            n = 0;
        }
        final int n2 = n;
        int n3 = 0;
        Label_0202: {
            try {
                if (this.hasSpace == null) {
                    n3 = 1;
                    break Label_0202;
                }
            }
            catch (IllegalArgumentException ex10) {
                throw b(ex10);
            }
            try {
                if (this.hasSpace) {
                    n3 = 1;
                    break Label_0202;
                }
            }
            catch (IllegalArgumentException ex11) {
                throw b(ex11);
            }
            n3 = 0;
        }
        final int n4 = n3;
        int intValue = 0;
        Label_0226: {
            try {
                if (this.keepBlankLines == null) {
                    intValue = -1;
                    break Label_0226;
                }
            }
            catch (IllegalArgumentException ex12) {
                throw b(ex12);
            }
            intValue = this.keepBlankLines;
        }
        final int n5 = intValue;
        try {
            if (b2) {
                this.isDependent = false;
                this.keepLineBreaks = true;
                this.minLineFeeds = 0;
            }
        }
        catch (IllegalArgumentException ex13) {
            throw b(ex13);
        }
        try {
            if (this.ocSettings.DO_NOT_ADD_BREAKS) {
                this.isDependent = false;
                this.minLineFeeds = 0;
            }
        }
        catch (IllegalArgumentException ex14) {
            throw b(ex14);
        }
        int n8 = 0;
        int n9 = 0;
        TextRange dependentRange = null;
        boolean keepLineBreaks = false;
        int n10 = 0;
        Label_0355: {
            Label_0343: {
                Label_0322: {
                    Label_0302: {
                        try {
                            if (!this.isDependent) {
                                return Spacing.createSpacing(n2, n4, this.minLineFeeds, this.keepLineBreaks, n5);
                            }
                            final OCSpacingProcessor ocSpacingProcessor3 = this;
                            final TextRange textRange = ocSpacingProcessor3.dependentRange;
                            if (textRange == null) {
                                break Label_0302;
                            }
                            break Label_0322;
                        }
                        catch (IllegalArgumentException ex15) {
                            throw b(ex15);
                        }
                        try {
                            final OCSpacingProcessor ocSpacingProcessor3 = this;
                            final TextRange textRange = ocSpacingProcessor3.dependentRange;
                            if (textRange == null) {
                                this.dependentRange = this.thisNode.getTextRange();
                            }
                        }
                        catch (IllegalArgumentException ex16) {
                            throw b(ex16);
                        }
                    }
                    try {
                        if (OCSpacingProcessor.$assertionsDisabled) {
                            break Label_0355;
                        }
                        final OCSpacingProcessor ocSpacingProcessor4 = this;
                        final int n6 = ocSpacingProcessor4.minLineFeeds;
                        final int n7 = 1;
                        if (n6 < n7) {
                            break Label_0343;
                        }
                        break Label_0355;
                    }
                    catch (IllegalArgumentException ex17) {
                        throw b(ex17);
                    }
                }
                try {
                    final OCSpacingProcessor ocSpacingProcessor4 = this;
                    final int n6 = ocSpacingProcessor4.minLineFeeds;
                    final int n7 = 1;
                    if (n6 < n7) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex18) {
                    throw b(ex18);
                }
            }
            try {
                n8 = n2;
                n9 = n4;
                dependentRange = this.dependentRange;
                keepLineBreaks = this.keepLineBreaks;
                n10 = n5;
                if (this.minLineFeeds == 1) {
                    final DependentSpacingRule dependentSpacingRule = DependentSpacingRule.DEFAULT;
                    return Spacing.createDependentLFSpacing(n8, n9, dependentRange, keepLineBreaks, n10, dependentSpacingRule);
                }
            }
            catch (IllegalArgumentException ex19) {
                throw b(ex19);
            }
        }
        final DependentSpacingRule dependentSpacingRule = new DependentSpacingRule(DependentSpacingRule.Trigger.HAS_LINE_FEEDS).registerData(DependentSpacingRule.Anchor.MIN_LINE_FEEDS, this.minLineFeeds);
        return Spacing.createDependentLFSpacing(n8, n9, dependentRange, keepLineBreaks, n10, dependentSpacingRule);
    }
    
    private boolean b(final boolean p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_1        
        //     1: ifeq            68
        //     4: aload_0        
        //     5: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //     8: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    11: if_acmpne       32
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: iload_2        
        //    22: ifne            60
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    31: athrow         
        //    32: aload_0        
        //    33: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //    36: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    39: if_acmpne       68
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: iload_2        
        //    50: ifne            68
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: iconst_1       
        //    61: goto            69
        //    64: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: iconst_0       
        //    69: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  4      25     28     32     Ljava/lang/IllegalArgumentException;
        //  21     42     45     49     Ljava/lang/IllegalArgumentException;
        //  32     53     56     60     Ljava/lang/IllegalArgumentException;
        //  49     64     64     68     Ljava/lang/IllegalArgumentException;
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
    
    private boolean l() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_0        
        //     2: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //     5: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //     8: if_acmpne       19
        //    11: iconst_1       
        //    12: goto            20
        //    15: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    18: athrow         
        //    19: iconst_0       
        //    20: aload_0        
        //    21: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //    24: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_PARAMETERS_COMMA_ON_NEXT_LINE:Z
        //    27: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(ZZ)Z
        //    30: ifne            358
        //    33: aload_0        
        //    34: aload_0        
        //    35: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //    38: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    41: if_acmpeq       68
        //    44: goto            51
        //    47: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: aload_0        
        //    52: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //    55: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    58: if_acmpne       76
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: iconst_1       
        //    69: goto            77
        //    72: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: iconst_0       
        //    77: aload_0        
        //    78: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //    81: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_COMMA_ON_NEXT_LINE:Z
        //    84: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(ZZ)Z
        //    87: ifne            358
        //    90: aload_0        
        //    91: aload_0        
        //    92: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //    95: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ENUM:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    98: if_acmpne       116
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: iconst_1       
        //   109: goto            117
        //   112: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: iconst_0       
        //   117: aload_0        
        //   118: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   121: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.ENUM_CONSTANTS_COMMA_ON_NEXT_LINE:Z
        //   124: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(ZZ)Z
        //   127: ifne            358
        //   130: aload_0        
        //   131: aload_0        
        //   132: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   135: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   138: if_acmpne       156
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: iconst_1       
        //   149: goto            157
        //   152: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   155: athrow         
        //   156: iconst_0       
        //   157: aload_0        
        //   158: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   161: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_PARAMETERS_COMMA_ON_NEXT_LINE:Z
        //   164: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(ZZ)Z
        //   167: ifne            358
        //   170: aload_0        
        //   171: aload_0        
        //   172: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   175: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TEMPLATE_ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   178: if_acmpne       196
        //   181: goto            188
        //   184: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: iconst_1       
        //   189: goto            197
        //   192: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   195: athrow         
        //   196: iconst_0       
        //   197: aload_0        
        //   198: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   201: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_CALL_ARGUMENTS_COMMA_ON_NEXT_LINE:Z
        //   204: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(ZZ)Z
        //   207: ifne            358
        //   210: aload_0        
        //   211: aload_0        
        //   212: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   215: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_CONSTRUCTOR_INITIALIZATION_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   218: if_acmpne       236
        //   221: goto            228
        //   224: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   227: athrow         
        //   228: iconst_1       
        //   229: goto            237
        //   232: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   235: athrow         
        //   236: iconst_0       
        //   237: aload_0        
        //   238: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   241: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_COMMA_ON_NEXT_LINE:Z
        //   244: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(ZZ)Z
        //   247: ifne            358
        //   250: aload_0        
        //   251: aload_0        
        //   252: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   255: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_BASE_CLAUSE_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   258: if_acmpne       276
        //   261: goto            268
        //   264: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   267: athrow         
        //   268: iconst_1       
        //   269: goto            277
        //   272: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   275: athrow         
        //   276: iconst_0       
        //   277: aload_0        
        //   278: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   281: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SUPERCLASS_LIST_COMMA_ON_NEXT_LINE:Z
        //   284: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(ZZ)Z
        //   287: ifne            358
        //   290: aload_0        
        //   291: aload_0        
        //   292: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   295: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_LAMBDA_INTRODUCER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   298: if_acmpne       316
        //   301: goto            308
        //   304: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   307: athrow         
        //   308: iconst_1       
        //   309: goto            317
        //   312: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   315: athrow         
        //   316: iconst_0       
        //   317: aload_0        
        //   318: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   321: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.LAMBDA_CAPTURE_LIST_COMMA_ON_NEXT_LINE:Z
        //   324: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(ZZ)Z
        //   327: ifne            358
        //   330: aload_0        
        //   331: aload_0        
        //   332: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   335: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isCollectionOrStructureInitializer:(Lcom/intellij/psi/tree/IElementType;)Z
        //   338: aload_0        
        //   339: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   342: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.ARRAY_INITIALIZER_COMMA_ON_NEXT_LINE:Z
        //   345: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(ZZ)Z
        //   348: ifeq            366
        //   351: goto            358
        //   354: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   357: athrow         
        //   358: iconst_1       
        //   359: goto            367
        //   362: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   365: athrow         
        //   366: iconst_0       
        //   367: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      15     15     19     Ljava/lang/IllegalArgumentException;
        //  20     44     47     51     Ljava/lang/IllegalArgumentException;
        //  33     61     64     68     Ljava/lang/IllegalArgumentException;
        //  51     72     72     76     Ljava/lang/IllegalArgumentException;
        //  77     101    104    108    Ljava/lang/IllegalArgumentException;
        //  90     112    112    116    Ljava/lang/IllegalArgumentException;
        //  117    141    144    148    Ljava/lang/IllegalArgumentException;
        //  130    152    152    156    Ljava/lang/IllegalArgumentException;
        //  157    181    184    188    Ljava/lang/IllegalArgumentException;
        //  170    192    192    196    Ljava/lang/IllegalArgumentException;
        //  197    221    224    228    Ljava/lang/IllegalArgumentException;
        //  210    232    232    236    Ljava/lang/IllegalArgumentException;
        //  237    261    264    268    Ljava/lang/IllegalArgumentException;
        //  250    272    272    276    Ljava/lang/IllegalArgumentException;
        //  277    301    304    308    Ljava/lang/IllegalArgumentException;
        //  290    312    312    316    Ljava/lang/IllegalArgumentException;
        //  317    351    354    358    Ljava/lang/IllegalArgumentException;
        //  330    362    362    366    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0051:
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
    
    private boolean b() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.i:()Z
        //     4: ifeq            16
        //     7: aload_0        
        //     8: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.e:()Z
        //    11: ireturn        
        //    12: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    15: athrow         
        //    16: aload_0        
        //    17: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //    20: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    23: if_acmpeq       43
        //    26: aload_0        
        //    27: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //    30: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EMPTY_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    33: if_acmpne       83
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: aload_0        
        //    44: iconst_0       
        //    45: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //    48: ifeq            81
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: aload_0        
        //    59: iconst_0       
        //    60: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Z)Z
        //    63: ifeq            81
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: iconst_1       
        //    74: goto            82
        //    77: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: iconst_0       
        //    82: ireturn        
        //    83: aload_0        
        //    84: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //    87: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.LABELED_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    90: if_acmpne       119
        //    93: aload_0        
        //    94: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //    97: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   100: if_acmpne       119
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: aload_0        
        //   111: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.c:()Z
        //   114: ireturn        
        //   115: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: aload_0        
        //   120: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.l:()Z
        //   123: ifne            168
        //   126: aload_0        
        //   127: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   130: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_BASE_CLAUSE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   133: if_acmpeq       168
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   142: athrow         
        //   143: aload_0        
        //   144: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   147: aload_0        
        //   148: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   151: aload_0        
        //   152: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //   155: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isNestedBlockOwner:(Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;Lcom/intellij/lang/ASTNode;)Z
        //   158: ifeq            178
        //   161: goto            168
        //   164: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: aload_0        
        //   169: iconst_0       
        //   170: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Z)Z
        //   173: ireturn        
        //   174: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: aload_0        
        //   179: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //   182: aload_0        
        //   183: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   186: iconst_1       
        //   187: new             Lcom/jetbrains/cidr/lang/formatting/OCSpacingProcessor$1;
        //   190: dup            
        //   191: aload_0        
        //   192: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor$1.<init>:(Lcom/jetbrains/cidr/lang/formatting/OCSpacingProcessor;)V
        //   195: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.processBraced:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;ZLcom/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor;)Ljava/lang/Object;
        //   198: checkcast       Ljava/lang/Boolean;
        //   201: astore_1       
        //   202: aload_1        
        //   203: ifnull          215
        //   206: aload_1        
        //   207: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   210: ireturn        
        //   211: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   214: athrow         
        //   215: aload_0        
        //   216: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.j:()Z
        //   219: ifne            246
        //   222: aload_0        
        //   223: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   226: aload_0        
        //   227: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   230: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.END_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   233: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isObjCKeywordWithDog:(Lcom/intellij/psi/tree/IElementType;Lcom/intellij/lang/ASTNode;Lcom/jetbrains/cidr/lang/parser/OCElementType;)Z
        //   236: ifeq            255
        //   239: goto            246
        //   242: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   245: athrow         
        //   246: aload_0        
        //   247: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.e:()Z
        //   250: ireturn        
        //   251: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   254: athrow         
        //   255: aload_0        
        //   256: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   259: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isOCVisibilityKeywordOrCPPVisibilityColon:(Lcom/intellij/lang/ASTNode;)Z
        //   262: ifne            299
        //   265: aload_0        
        //   266: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   269: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isVisibilityKeyword:(Lcom/intellij/lang/ASTNode;)Z
        //   272: ifeq            308
        //   275: goto            282
        //   278: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   281: athrow         
        //   282: aload_0        
        //   283: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   286: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   289: if_acmpeq       308
        //   292: goto            299
        //   295: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   298: athrow         
        //   299: aload_0        
        //   300: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.e:()Z
        //   303: ireturn        
        //   304: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   307: athrow         
        //   308: aload_0        
        //   309: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   312: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROPERTY:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   315: if_acmpeq       369
        //   318: aload_0        
        //   319: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   322: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROPERTY:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   325: if_acmpeq       369
        //   328: goto            335
        //   331: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   334: athrow         
        //   335: aload_0        
        //   336: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   339: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SYNTHESIZED_PROPERTIES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   342: if_acmpeq       369
        //   345: goto            352
        //   348: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   351: athrow         
        //   352: aload_0        
        //   353: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   356: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SYNTHESIZED_PROPERTIES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   359: if_acmpne       378
        //   362: goto            369
        //   365: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   368: athrow         
        //   369: aload_0        
        //   370: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.e:()Z
        //   373: ireturn        
        //   374: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   377: athrow         
        //   378: aload_0        
        //   379: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   382: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROPERTY:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   385: if_acmpeq       405
        //   388: aload_0        
        //   389: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   392: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SYNTHESIZED_PROPERTIES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   395: if_acmpne       436
        //   398: goto            405
        //   401: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   404: athrow         
        //   405: aload_0        
        //   406: aload_0        
        //   407: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   410: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   413: if_acmpeq       431
        //   416: goto            423
        //   419: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   422: athrow         
        //   423: iconst_1       
        //   424: goto            432
        //   427: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   430: athrow         
        //   431: iconst_0       
        //   432: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Z)Z
        //   435: ireturn        
        //   436: aload_0        
        //   437: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   440: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isControlStatement:(Lcom/intellij/psi/tree/IElementType;)Z
        //   443: ifne            529
        //   446: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.STATEMENTS:Lcom/intellij/psi/tree/TokenSet;
        //   449: aload_0        
        //   450: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   453: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   456: ifne            483
        //   459: goto            466
        //   462: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   465: athrow         
        //   466: aload_0        
        //   467: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   470: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isDeclarationOrDefinition:(Lcom/intellij/psi/tree/IElementType;)Z
        //   473: ifeq            1145
        //   476: goto            483
        //   479: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   482: athrow         
        //   483: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.STATEMENTS:Lcom/intellij/psi/tree/TokenSet;
        //   486: aload_0        
        //   487: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   490: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   493: ifne            520
        //   496: goto            503
        //   499: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   502: athrow         
        //   503: aload_0        
        //   504: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   507: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isDeclarationOrDefinition:(Lcom/intellij/psi/tree/IElementType;)Z
        //   510: ifeq            1145
        //   513: goto            520
        //   516: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   519: athrow         
        //   520: aload_0        
        //   521: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.e:()Z
        //   524: ireturn        
        //   525: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   528: athrow         
        //   529: aload_0        
        //   530: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   533: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.IF_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   536: if_acmpne       734
        //   539: aload_0        
        //   540: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //   543: ldc             Lcom/jetbrains/cidr/lang/psi/OCIfStatement;.class
        //   545: invokeinterface com/intellij/lang/ASTNode.getPsi:(Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   550: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //   553: astore_2       
        //   554: aload_2        
        //   555: ifnull          734
        //   558: aload_2        
        //   559: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getThenBranch:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   564: astore_3       
        //   565: aload_2        
        //   566: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getElseBranch:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   571: astore          4
        //   573: aload_0        
        //   574: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   577: iconst_1       
        //   578: anewarray       Lcom/intellij/psi/PsiElement;
        //   581: dup            
        //   582: iconst_0       
        //   583: aload           4
        //   585: aastore        
        //   586: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isOneOf:(Lcom/intellij/lang/ASTNode;[Lcom/intellij/psi/PsiElement;)Z
        //   589: ifeq            635
        //   592: aload_0        
        //   593: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   596: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.IF_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   599: if_acmpne       635
        //   602: goto            609
        //   605: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   608: athrow         
        //   609: aload_0        
        //   610: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   613: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPECIAL_ELSE_IF_TREATMENT:Z
        //   616: ifeq            635
        //   619: goto            626
        //   622: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   625: athrow         
        //   626: aload_0        
        //   627: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.h:()Z
        //   630: ireturn        
        //   631: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   634: athrow         
        //   635: aload_0        
        //   636: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   639: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ELSE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   642: if_acmpne       662
        //   645: aload_0        
        //   646: aload_3        
        //   647: aload_0        
        //   648: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   651: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.ELSE_ON_NEW_LINE:Z
        //   654: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Lcom/jetbrains/cidr/lang/psi/OCStatement;Z)Z
        //   657: ireturn        
        //   658: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   661: athrow         
        //   662: aload_0        
        //   663: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   666: iconst_2       
        //   667: anewarray       Lcom/intellij/psi/PsiElement;
        //   670: dup            
        //   671: iconst_0       
        //   672: aload_3        
        //   673: aastore        
        //   674: dup            
        //   675: iconst_1       
        //   676: aload           4
        //   678: aastore        
        //   679: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isOneOf:(Lcom/intellij/lang/ASTNode;[Lcom/intellij/psi/PsiElement;)Z
        //   682: ifeq            698
        //   685: aload_0        
        //   686: aload_0        
        //   687: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   690: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Lcom/intellij/lang/ASTNode;)Z
        //   693: ireturn        
        //   694: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   697: athrow         
        //   698: aload_0        
        //   699: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   702: iconst_2       
        //   703: anewarray       Lcom/intellij/psi/PsiElement;
        //   706: dup            
        //   707: iconst_0       
        //   708: aload_3        
        //   709: aastore        
        //   710: dup            
        //   711: iconst_1       
        //   712: aload           4
        //   714: aastore        
        //   715: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isNotEmptyAndOneOf:(Lcom/intellij/lang/ASTNode;[Lcom/intellij/psi/PsiElement;)Z
        //   718: ifeq            734
        //   721: aload_0        
        //   722: aload_0        
        //   723: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   726: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Lcom/intellij/lang/ASTNode;)Z
        //   729: ireturn        
        //   730: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   733: athrow         
        //   734: aload_0        
        //   735: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   738: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isLoopStatement:(Lcom/intellij/psi/tree/IElementType;)Z
        //   741: ifeq            876
        //   744: aload_0        
        //   745: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //   748: ldc             Lcom/jetbrains/cidr/lang/psi/OCLoopStatement;.class
        //   750: invokeinterface com/intellij/lang/ASTNode.getPsi:(Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   755: checkcast       Lcom/jetbrains/cidr/lang/psi/OCLoopStatement;
        //   758: astore_2       
        //   759: aload_2        
        //   760: ifnull          876
        //   763: aload_2        
        //   764: invokeinterface com/jetbrains/cidr/lang/psi/OCLoopStatement.getBody:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   769: astore_3       
        //   770: aload_0        
        //   771: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   774: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DO_WHILE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   777: if_acmpne       814
        //   780: aload_0        
        //   781: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   784: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHILE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   787: if_acmpne       814
        //   790: goto            797
        //   793: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   796: athrow         
        //   797: aload_0        
        //   798: aload_3        
        //   799: aload_0        
        //   800: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   803: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.WHILE_ON_NEW_LINE:Z
        //   806: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Lcom/jetbrains/cidr/lang/psi/OCStatement;Z)Z
        //   809: ireturn        
        //   810: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   813: athrow         
        //   814: aload_0        
        //   815: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   818: iconst_1       
        //   819: anewarray       Lcom/intellij/psi/PsiElement;
        //   822: dup            
        //   823: iconst_0       
        //   824: aload_3        
        //   825: aastore        
        //   826: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isOneOf:(Lcom/intellij/lang/ASTNode;[Lcom/intellij/psi/PsiElement;)Z
        //   829: ifeq            845
        //   832: aload_0        
        //   833: aload_0        
        //   834: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   837: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Lcom/intellij/lang/ASTNode;)Z
        //   840: ireturn        
        //   841: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   844: athrow         
        //   845: aload_0        
        //   846: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   849: iconst_1       
        //   850: anewarray       Lcom/intellij/psi/PsiElement;
        //   853: dup            
        //   854: iconst_0       
        //   855: aload_3        
        //   856: aastore        
        //   857: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isNotEmptyAndOneOf:(Lcom/intellij/lang/ASTNode;[Lcom/intellij/psi/PsiElement;)Z
        //   860: ifeq            876
        //   863: aload_0        
        //   864: aload_0        
        //   865: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   868: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Lcom/intellij/lang/ASTNode;)Z
        //   871: ireturn        
        //   872: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   875: athrow         
        //   876: aload_0        
        //   877: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   880: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TRY_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   883: if_acmpne       965
        //   886: aload_0        
        //   887: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   890: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CATCH_SECTION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   893: if_acmpne       929
        //   896: goto            903
        //   899: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   902: athrow         
        //   903: aload_0        
        //   904: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   907: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.CATCH_ON_NEW_LINE:Z
        //   910: ifeq            929
        //   913: goto            920
        //   916: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   919: athrow         
        //   920: aload_0        
        //   921: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.e:()Z
        //   924: ireturn        
        //   925: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   928: athrow         
        //   929: aload_0        
        //   930: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   933: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FINALLY_SECTION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   936: if_acmpne       965
        //   939: aload_0        
        //   940: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   943: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.FINALLY_ON_NEW_LINE:Z
        //   946: ifeq            965
        //   949: goto            956
        //   952: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   955: athrow         
        //   956: aload_0        
        //   957: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.e:()Z
        //   960: ireturn        
        //   961: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   964: athrow         
        //   965: aload_0        
        //   966: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   969: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CASE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   972: if_acmpne       984
        //   975: aload_0        
        //   976: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.c:()Z
        //   979: ireturn        
        //   980: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   983: athrow         
        //   984: aload_0        
        //   985: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   988: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CASE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   991: if_acmpne       1145
        //   994: aload_0        
        //   995: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   998: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1001: if_acmpne       1145
        //  1004: goto            1011
        //  1007: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1010: athrow         
        //  1011: aload_0        
        //  1012: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //  1015: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SWITCH_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1018: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.findParent:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //  1021: astore_2       
        //  1022: aload_0        
        //  1023: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //  1026: astore_3       
        //  1027: aload_0        
        //  1028: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //  1031: iconst_0       
        //  1032: anewarray       Lcom/intellij/psi/tree/IElementType;
        //  1035: invokestatic    com/intellij/psi/formatter/FormatterUtil.getNext:(Lcom/intellij/lang/ASTNode;[Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //  1038: astore          4
        //  1040: aload           4
        //  1042: ifnull          1124
        //  1045: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.STATEMENTS:Lcom/intellij/psi/tree/TokenSet;
        //  1048: aload           4
        //  1050: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //  1055: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1058: ifeq            1124
        //  1061: goto            1068
        //  1064: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1067: athrow         
        //  1068: aload           4
        //  1070: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //  1075: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CASE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1078: if_acmpeq       1124
        //  1081: goto            1088
        //  1084: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1087: athrow         
        //  1088: aload           4
        //  1090: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SWITCH_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1093: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.findParent:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //  1096: aload_2        
        //  1097: if_acmpne       1124
        //  1100: goto            1107
        //  1103: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1106: athrow         
        //  1107: aload           4
        //  1109: astore_3       
        //  1110: aload           4
        //  1112: iconst_0       
        //  1113: anewarray       Lcom/intellij/psi/tree/IElementType;
        //  1116: invokestatic    com/intellij/psi/formatter/FormatterUtil.getNext:(Lcom/intellij/lang/ASTNode;[Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //  1119: astore          4
        //  1121: goto            1040
        //  1124: aload_0        
        //  1125: aload_0        
        //  1126: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //  1129: aload_0        
        //  1130: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //  1133: aload_3        
        //  1134: aload_0        
        //  1135: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  1138: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.KEEP_CASE_EXPRESSIONS_IN_ONE_LINE:Z
        //  1141: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Lcom/intellij/lang/ASTNode;Lcom/intellij/lang/ASTNode;Lcom/intellij/lang/ASTNode;Z)Z
        //  1144: ireturn        
        //  1145: iconst_0       
        //  1146: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      12     12     16     Ljava/lang/IllegalArgumentException;
        //  16     36     39     43     Ljava/lang/IllegalArgumentException;
        //  26     51     54     58     Ljava/lang/IllegalArgumentException;
        //  43     66     69     73     Ljava/lang/IllegalArgumentException;
        //  58     77     77     81     Ljava/lang/IllegalArgumentException;
        //  83     103    106    110    Ljava/lang/IllegalArgumentException;
        //  93     115    115    119    Ljava/lang/IllegalArgumentException;
        //  119    136    139    143    Ljava/lang/IllegalArgumentException;
        //  126    161    164    168    Ljava/lang/IllegalArgumentException;
        //  143    174    174    178    Ljava/lang/IllegalArgumentException;
        //  202    211    211    215    Ljava/lang/IllegalArgumentException;
        //  215    239    242    246    Ljava/lang/IllegalArgumentException;
        //  222    251    251    255    Ljava/lang/IllegalArgumentException;
        //  255    275    278    282    Ljava/lang/IllegalArgumentException;
        //  265    292    295    299    Ljava/lang/IllegalArgumentException;
        //  282    304    304    308    Ljava/lang/IllegalArgumentException;
        //  308    328    331    335    Ljava/lang/IllegalArgumentException;
        //  318    345    348    352    Ljava/lang/IllegalArgumentException;
        //  335    362    365    369    Ljava/lang/IllegalArgumentException;
        //  352    374    374    378    Ljava/lang/IllegalArgumentException;
        //  378    398    401    405    Ljava/lang/IllegalArgumentException;
        //  388    416    419    423    Ljava/lang/IllegalArgumentException;
        //  405    427    427    431    Ljava/lang/IllegalArgumentException;
        //  436    459    462    466    Ljava/lang/IllegalArgumentException;
        //  446    476    479    483    Ljava/lang/IllegalArgumentException;
        //  466    496    499    503    Ljava/lang/IllegalArgumentException;
        //  483    513    516    520    Ljava/lang/IllegalArgumentException;
        //  503    525    525    529    Ljava/lang/IllegalArgumentException;
        //  573    602    605    609    Ljava/lang/IllegalArgumentException;
        //  592    619    622    626    Ljava/lang/IllegalArgumentException;
        //  609    631    631    635    Ljava/lang/IllegalArgumentException;
        //  635    658    658    662    Ljava/lang/IllegalArgumentException;
        //  662    694    694    698    Ljava/lang/IllegalArgumentException;
        //  698    730    730    734    Ljava/lang/IllegalArgumentException;
        //  770    790    793    797    Ljava/lang/IllegalArgumentException;
        //  780    810    810    814    Ljava/lang/IllegalArgumentException;
        //  814    841    841    845    Ljava/lang/IllegalArgumentException;
        //  845    872    872    876    Ljava/lang/IllegalArgumentException;
        //  876    896    899    903    Ljava/lang/IllegalArgumentException;
        //  886    913    916    920    Ljava/lang/IllegalArgumentException;
        //  903    925    925    929    Ljava/lang/IllegalArgumentException;
        //  929    949    952    956    Ljava/lang/IllegalArgumentException;
        //  939    961    961    965    Ljava/lang/IllegalArgumentException;
        //  965    980    980    984    Ljava/lang/IllegalArgumentException;
        //  984    1004   1007   1011   Ljava/lang/IllegalArgumentException;
        //  1040   1061   1064   1068   Ljava/lang/IllegalArgumentException;
        //  1045   1081   1084   1088   Ljava/lang/IllegalArgumentException;
        //  1068   1100   1103   1107   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0043:
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
    
    private boolean a(@Nullable final OCStatement p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_2        
        //     1: ifne            84
        //     4: aload_1        
        //     5: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //     8: ifeq            84
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    17: athrow         
        //    18: aload_1        
        //    19: bipush          10
        //    21: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.textContains:(C)Z
        //    26: ifne            73
        //    29: goto            36
        //    32: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    35: athrow         
        //    36: aconst_null    
        //    37: aload_1        
        //    38: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getNode:()Lcom/intellij/lang/ASTNode;
        //    43: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isInBlockEnclosed:(Lcom/intellij/lang/ASTNode;Lcom/intellij/lang/ASTNode;)Z
        //    46: ifeq            73
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: aload_0        
        //    57: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //    60: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.KEEP_CONTROL_STATEMENT_IN_ONE_LINE:Z
        //    63: ifne            84
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: aload_0        
        //    74: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.h:()Z
        //    77: goto            88
        //    80: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: aload_0        
        //    85: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.e:()Z
        //    88: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      11     14     18     Ljava/lang/IllegalArgumentException;
        //  4      29     32     36     Ljava/lang/IllegalArgumentException;
        //  18     49     52     56     Ljava/lang/IllegalArgumentException;
        //  36     66     69     73     Ljava/lang/IllegalArgumentException;
        //  56     80     80     84     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    
    private boolean i() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.FORMAT_DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //     3: aload_0        
        //     4: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //     7: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    10: ifne            33
        //    13: getstatic       com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.FORMAT_DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //    16: aload_0        
        //    17: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //    20: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    23: ifeq            58
        //    26: goto            33
        //    29: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    32: athrow         
        //    33: aload_0        
        //    34: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //    37: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isInsideDirective:(Lcom/intellij/lang/ASTNode;)Z
        //    40: ifne            58
        //    43: goto            50
        //    46: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: iconst_1       
        //    51: goto            59
        //    54: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: iconst_0       
        //    59: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      26     29     33     Ljava/lang/IllegalArgumentException;
        //  13     43     46     50     Ljava/lang/IllegalArgumentException;
        //  33     54     54     58     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0033:
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
    
    private boolean k() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //     4: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //     7: if_acmpne       16
        //    10: iconst_0       
        //    11: ireturn        
        //    12: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    15: athrow         
        //    16: aload_0        
        //    17: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //    20: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_EXTERN_BLOCK:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    23: if_acmpne       32
        //    26: iconst_0       
        //    27: ireturn        
        //    28: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    31: athrow         
        //    32: aload_0        
        //    33: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //    36: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    39: if_acmpeq       59
        //    42: aload_0        
        //    43: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //    46: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    49: if_acmpne       108
        //    52: goto            59
        //    55: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    58: athrow         
        //    59: aload_0        
        //    60: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.g:()Z
        //    63: ifeq            106
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: aload_0        
        //    74: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //    77: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    80: if_acmpeq       106
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: aload_0        
        //    91: aload_0        
        //    92: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //    95: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.BLANK_LINES_BEFORE_METHOD_BODY:I
        //    98: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(I)Z
        //   101: ireturn        
        //   102: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: iconst_0       
        //   107: ireturn        
        //   108: aload_0        
        //   109: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   112: aload_0        
        //   113: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   116: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.END_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   119: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isObjCKeywordWithDog:(Lcom/intellij/psi/tree/IElementType;Lcom/intellij/lang/ASTNode;Lcom/jetbrains/cidr/lang/parser/OCElementType;)Z
        //   122: ifeq            131
        //   125: iconst_0       
        //   126: ireturn        
        //   127: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aload_0        
        //   132: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   135: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isClassHeader:(Lcom/intellij/lang/ASTNode;)Z
        //   138: ifeq            174
        //   141: aload_0        
        //   142: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   145: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isClassHeader:(Lcom/intellij/lang/ASTNode;)Z
        //   148: ifne            174
        //   151: goto            158
        //   154: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   157: athrow         
        //   158: aload_0        
        //   159: aload_0        
        //   160: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   163: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.BLANK_LINES_AFTER_CLASS_HEADER:I
        //   166: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(I)Z
        //   169: ireturn        
        //   170: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   173: athrow         
        //   174: aload_0        
        //   175: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   178: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isOCVisibilityKeywordOrCPPVisibilityColon:(Lcom/intellij/lang/ASTNode;)Z
        //   181: ifeq            190
        //   184: iconst_0       
        //   185: ireturn        
        //   186: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   189: athrow         
        //   190: iconst_0       
        //   191: istore_1       
        //   192: aload_0        
        //   193: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   196: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.IMPORT_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   199: if_acmpeq       219
        //   202: aload_0        
        //   203: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   206: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.IMPORT_MODULE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   209: if_acmpne       227
        //   212: goto            219
        //   215: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   218: athrow         
        //   219: iconst_1       
        //   220: goto            228
        //   223: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   226: athrow         
        //   227: iconst_0       
        //   228: istore_2       
        //   229: aload_0        
        //   230: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   233: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.IMPORT_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   236: if_acmpeq       256
        //   239: aload_0        
        //   240: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   243: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.IMPORT_MODULE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   246: if_acmpne       264
        //   249: goto            256
        //   252: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   255: athrow         
        //   256: iconst_1       
        //   257: goto            265
        //   260: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   263: athrow         
        //   264: iconst_0       
        //   265: istore_3       
        //   266: iload_2        
        //   267: ifne            281
        //   270: iload_3        
        //   271: ifeq            336
        //   274: goto            281
        //   277: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   280: athrow         
        //   281: iload_2        
        //   282: iload_3        
        //   283: if_icmpeq       336
        //   286: goto            293
        //   289: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   292: athrow         
        //   293: iload_2        
        //   294: ifeq            318
        //   297: goto            304
        //   300: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   303: athrow         
        //   304: aload_0        
        //   305: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   308: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.BLANK_LINES_AFTER_IMPORTS:I
        //   311: goto            325
        //   314: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   317: athrow         
        //   318: aload_0        
        //   319: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   322: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.BLANK_LINES_BEFORE_IMPORTS:I
        //   325: istore          4
        //   327: iload_1        
        //   328: aload_0        
        //   329: iload           4
        //   331: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(I)Z
        //   334: ior            
        //   335: istore_1       
        //   336: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CLASSES:Lcom/intellij/psi/tree/TokenSet;
        //   339: aload_0        
        //   340: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   343: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   346: ifne            437
        //   349: aload_0        
        //   350: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   353: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isCPPClassDeclaration:(Lcom/intellij/lang/ASTNode;)Z
        //   356: ifne            437
        //   359: goto            366
        //   362: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   365: athrow         
        //   366: aload_0        
        //   367: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   370: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isNamespaceWithKeyword:(Lcom/intellij/lang/ASTNode;)Z
        //   373: ifne            437
        //   376: goto            383
        //   379: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   382: athrow         
        //   383: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CLASSES:Lcom/intellij/psi/tree/TokenSet;
        //   386: aload_0        
        //   387: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   390: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   393: ifne            437
        //   396: goto            403
        //   399: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   402: athrow         
        //   403: aload_0        
        //   404: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   407: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isCPPClassDeclaration:(Lcom/intellij/lang/ASTNode;)Z
        //   410: ifne            437
        //   413: goto            420
        //   416: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   419: athrow         
        //   420: aload_0        
        //   421: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   424: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isNamespaceWithKeyword:(Lcom/intellij/lang/ASTNode;)Z
        //   427: ifeq            499
        //   430: goto            437
        //   433: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   436: athrow         
        //   437: iload_1        
        //   438: aload_0        
        //   439: aload_0        
        //   440: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   443: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   446: if_acmpeq       473
        //   449: goto            456
        //   452: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   455: athrow         
        //   456: aload_0        
        //   457: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   460: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   463: if_acmpne       487
        //   466: goto            473
        //   469: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   472: athrow         
        //   473: aload_0        
        //   474: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   477: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.BLANK_LINES_AROUND_NAMESPACE:I
        //   480: goto            494
        //   483: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   486: athrow         
        //   487: aload_0        
        //   488: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   491: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.BLANK_LINES_AROUND_CLASS:I
        //   494: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(I)Z
        //   497: ior            
        //   498: istore_1       
        //   499: aload_0        
        //   500: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   503: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROPERTY:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   506: if_acmpeq       526
        //   509: aload_0        
        //   510: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   513: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROPERTY:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   516: if_acmpne       540
        //   519: goto            526
        //   522: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   525: athrow         
        //   526: iload_1        
        //   527: aload_0        
        //   528: aload_0        
        //   529: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   532: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.BLANK_LINES_AROUND_PROPERTIES_IN_INTERFACE:I
        //   535: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(I)Z
        //   538: ior            
        //   539: istore_1       
        //   540: aload_0        
        //   541: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   544: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SYNTHESIZED_PROPERTIES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   547: if_acmpeq       567
        //   550: aload_0        
        //   551: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   554: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SYNTHESIZED_PROPERTIES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   557: if_acmpne       581
        //   560: goto            567
        //   563: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   566: athrow         
        //   567: iload_1        
        //   568: aload_0        
        //   569: aload_0        
        //   570: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   573: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.BLANK_LINES_AROUND_PROPERTIES_IN_DECLARATION:I
        //   576: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(I)Z
        //   579: ior            
        //   580: istore_1       
        //   581: aload_0        
        //   582: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   585: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROPERTY:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   588: if_acmpeq       639
        //   591: aload_0        
        //   592: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   595: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isFunctionPredeclaration:(Lcom/intellij/lang/ASTNode;)Z
        //   598: ifne            625
        //   601: goto            608
        //   604: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   607: athrow         
        //   608: aload_0        
        //   609: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   612: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isFunctionPredeclaration:(Lcom/intellij/lang/ASTNode;)Z
        //   615: ifeq            639
        //   618: goto            625
        //   621: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   624: athrow         
        //   625: iload_1        
        //   626: aload_0        
        //   627: aload_0        
        //   628: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   631: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.BLANK_LINES_AROUND_METHOD_IN_INTERFACE:I
        //   634: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(I)Z
        //   637: ior            
        //   638: istore_1       
        //   639: aload_0        
        //   640: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   643: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   646: if_acmpeq       666
        //   649: aload_0        
        //   650: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   653: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   656: if_acmpne       680
        //   659: goto            666
        //   662: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   665: athrow         
        //   666: iload_1        
        //   667: aload_0        
        //   668: aload_0        
        //   669: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   672: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.BLANK_LINES_AROUND_METHOD:I
        //   675: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(I)Z
        //   678: ior            
        //   679: istore_1       
        //   680: aload_0        
        //   681: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   684: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   687: if_acmpeq       707
        //   690: aload_0        
        //   691: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   694: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   697: if_acmpne       756
        //   700: goto            707
        //   703: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   706: athrow         
        //   707: aload_0        
        //   708: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   711: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.IMPLEMENTATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   714: if_acmpne       738
        //   717: goto            724
        //   720: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   723: athrow         
        //   724: aload_0        
        //   725: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   728: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.BLANK_LINES_AROUND_METHOD:I
        //   731: goto            745
        //   734: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   737: athrow         
        //   738: aload_0        
        //   739: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   742: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.BLANK_LINES_AROUND_METHOD_IN_INTERFACE:I
        //   745: istore          4
        //   747: iload_1        
        //   748: aload_0        
        //   749: iload           4
        //   751: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(I)Z
        //   754: ior            
        //   755: istore_1       
        //   756: aload_0        
        //   757: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   760: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isVariableDeclaration:(Lcom/intellij/lang/ASTNode;)Z
        //   763: ifne            800
        //   766: aload_0        
        //   767: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   770: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isVariableDeclaration:(Lcom/intellij/lang/ASTNode;)Z
        //   773: ifeq            892
        //   776: goto            783
        //   779: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   782: athrow         
        //   783: aload_0        
        //   784: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   787: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   790: if_acmpeq       892
        //   793: goto            800
        //   796: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   799: athrow         
        //   800: aload_0        
        //   801: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   804: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.INSTANCE_VARIABLES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   807: if_acmpeq       834
        //   810: goto            817
        //   813: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   816: athrow         
        //   817: aload_0        
        //   818: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //   821: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isCPPClass:(Lcom/intellij/lang/ASTNode;)Z
        //   824: ifeq            848
        //   827: goto            834
        //   830: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   833: athrow         
        //   834: iload_1        
        //   835: aload_0        
        //   836: aload_0        
        //   837: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   840: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.BLANK_LINES_AROUND_FIELD_IN_INTERFACE:I
        //   843: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(I)Z
        //   846: ior            
        //   847: istore_1       
        //   848: aload_0        
        //   849: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   852: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isGlobalDeclarationScope:(Lcom/intellij/psi/tree/IElementType;)Z
        //   855: ifne            878
        //   858: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CLASSES:Lcom/intellij/psi/tree/TokenSet;
        //   861: aload_0        
        //   862: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   865: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   868: ifeq            892
        //   871: goto            878
        //   874: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   877: athrow         
        //   878: iload_1        
        //   879: aload_0        
        //   880: aload_0        
        //   881: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   884: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.BLANK_LINES_AROUND_FIELD:I
        //   887: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(I)Z
        //   890: ior            
        //   891: istore_1       
        //   892: iload_1        
        //   893: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      12     12     16     Ljava/lang/IllegalArgumentException;
        //  16     28     28     32     Ljava/lang/IllegalArgumentException;
        //  32     52     55     59     Ljava/lang/IllegalArgumentException;
        //  42     66     69     73     Ljava/lang/IllegalArgumentException;
        //  59     83     86     90     Ljava/lang/IllegalArgumentException;
        //  73     102    102    106    Ljava/lang/IllegalArgumentException;
        //  108    127    127    131    Ljava/lang/IllegalArgumentException;
        //  131    151    154    158    Ljava/lang/IllegalArgumentException;
        //  141    170    170    174    Ljava/lang/IllegalArgumentException;
        //  174    186    186    190    Ljava/lang/IllegalArgumentException;
        //  192    212    215    219    Ljava/lang/IllegalArgumentException;
        //  202    223    223    227    Ljava/lang/IllegalArgumentException;
        //  229    249    252    256    Ljava/lang/IllegalArgumentException;
        //  239    260    260    264    Ljava/lang/IllegalArgumentException;
        //  266    274    277    281    Ljava/lang/IllegalArgumentException;
        //  270    286    289    293    Ljava/lang/IllegalArgumentException;
        //  281    297    300    304    Ljava/lang/IllegalArgumentException;
        //  293    314    314    318    Ljava/lang/IllegalArgumentException;
        //  336    359    362    366    Ljava/lang/IllegalArgumentException;
        //  349    376    379    383    Ljava/lang/IllegalArgumentException;
        //  366    396    399    403    Ljava/lang/IllegalArgumentException;
        //  383    413    416    420    Ljava/lang/IllegalArgumentException;
        //  403    430    433    437    Ljava/lang/IllegalArgumentException;
        //  420    449    452    456    Ljava/lang/IllegalArgumentException;
        //  437    466    469    473    Ljava/lang/IllegalArgumentException;
        //  456    483    483    487    Ljava/lang/IllegalArgumentException;
        //  499    519    522    526    Ljava/lang/IllegalArgumentException;
        //  540    560    563    567    Ljava/lang/IllegalArgumentException;
        //  581    601    604    608    Ljava/lang/IllegalArgumentException;
        //  591    618    621    625    Ljava/lang/IllegalArgumentException;
        //  639    659    662    666    Ljava/lang/IllegalArgumentException;
        //  680    700    703    707    Ljava/lang/IllegalArgumentException;
        //  690    717    720    724    Ljava/lang/IllegalArgumentException;
        //  707    734    734    738    Ljava/lang/IllegalArgumentException;
        //  756    776    779    783    Ljava/lang/IllegalArgumentException;
        //  766    793    796    800    Ljava/lang/IllegalArgumentException;
        //  783    810    813    817    Ljava/lang/IllegalArgumentException;
        //  800    827    830    834    Ljava/lang/IllegalArgumentException;
        //  848    871    874    878    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0059:
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
    
    Boolean needSpaceAroundBinaryOperator(final ASTNode astNode, final IElementType elementType) {
        Label_0091: {
            Label_0072: {
                Label_0051: {
                    Label_0032: {
                        try {
                            if (OCElementUtil.getElementType(astNode.getTreeParent()) != OCElementTypes.BINARY_EXPRESSION) {
                                return null;
                            }
                            final TokenSet set = OCTokenTypes.LOGIC_OPERATIONS;
                            final IElementType elementType2 = elementType;
                            final boolean b = set.contains(elementType2);
                            if (b) {
                                break Label_0032;
                            }
                            break Label_0051;
                        }
                        catch (IllegalArgumentException ex) {
                            throw b(ex);
                        }
                        try {
                            final TokenSet set = OCTokenTypes.LOGIC_OPERATIONS;
                            final IElementType elementType2 = elementType;
                            final boolean b = set.contains(elementType2);
                            if (b) {
                                return this.c(this.settings.SPACE_AROUND_LOGICAL_OPERATORS);
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                    }
                    try {
                        if (elementType == OCTokenTypes.EQEQ) {
                            break Label_0072;
                        }
                        final IElementType elementType3 = elementType;
                        final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.EXCLEQ;
                        if (elementType3 == ocPunctuatorElementType) {
                            break Label_0072;
                        }
                        break Label_0091;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                try {
                    final IElementType elementType3 = elementType;
                    final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.EXCLEQ;
                    if (elementType3 == ocPunctuatorElementType) {
                        return this.c(this.settings.SPACE_AROUND_EQUALITY_OPERATORS);
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            try {
                if (OCTokenTypes.COMPARISON_OPERATIONS.contains(elementType)) {
                    return this.c(this.settings.SPACE_AROUND_RELATIONAL_OPERATORS);
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        try {
            if (OCTokenTypes.SHIFT_OPERATIONS.contains(elementType)) {
                return this.c(this.settings.SPACE_AROUND_SHIFT_OPERATORS);
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        try {
            if (OCTokenTypes.BITLOGIC_OPERATIONS.contains(elementType)) {
                return this.c(this.settings.SPACE_AROUND_BITWISE_OPERATORS);
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        Label_0218: {
            Label_0199: {
                try {
                    if (elementType == OCTokenTypes.PLUS) {
                        break Label_0199;
                    }
                    final IElementType elementType4 = elementType;
                    final OCPunctuatorElementType ocPunctuatorElementType2 = OCTokenTypes.MINUS;
                    if (elementType4 == ocPunctuatorElementType2) {
                        break Label_0199;
                    }
                    break Label_0218;
                }
                catch (IllegalArgumentException ex8) {
                    throw b(ex8);
                }
                try {
                    final IElementType elementType4 = elementType;
                    final OCPunctuatorElementType ocPunctuatorElementType2 = OCTokenTypes.MINUS;
                    if (elementType4 == ocPunctuatorElementType2) {
                        return this.c(this.settings.SPACE_AROUND_ADDITIVE_OPERATORS);
                    }
                }
                catch (IllegalArgumentException ex9) {
                    throw b(ex9);
                }
            }
            try {
                if (OCTokenTypes.ARITHMETIC_OPERATIONS.contains(elementType)) {
                    return this.c(this.settings.SPACE_AROUND_MULTIPLICATIVE_OPERATORS);
                }
            }
            catch (IllegalArgumentException ex10) {
                throw b(ex10);
            }
        }
        Label_0268: {
            try {
                if (elementType == OCTokenTypes.DEREF_MUL) {
                    break Label_0268;
                }
                final IElementType elementType5 = elementType;
                final OCPunctuatorElementType ocPunctuatorElementType3 = OCTokenTypes.DOT_MUL;
                if (elementType5 == ocPunctuatorElementType3) {
                    break Label_0268;
                }
                return null;
            }
            catch (IllegalArgumentException ex11) {
                throw b(ex11);
            }
            try {
                final IElementType elementType5 = elementType;
                final OCPunctuatorElementType ocPunctuatorElementType3 = OCTokenTypes.DOT_MUL;
                if (elementType5 == ocPunctuatorElementType3) {
                    return this.c(this.ocSettings.SPACE_AROUND_PM_OPERATORS);
                }
            }
            catch (IllegalArgumentException ex12) {
                throw b(ex12);
            }
        }
        return null;
    }
    
    private boolean f() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //     4: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.BLOCK_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //     7: if_acmpeq       27
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //    14: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    17: if_acmpne       60
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: aload_0        
        //    28: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //    31: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.XOR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    34: if_acmpne       60
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_0        
        //    46: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //    49: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_AFTER_CUP_IN_BLOCKS:Z
        //    52: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //    55: ireturn        
        //    56: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: aload_0        
        //    61: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //    64: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_BETWEEN_ADJACENT_BRACKETS:Z
        //    67: ifne            132
        //    70: aload_0        
        //    71: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.lastLeafTypeLeft:Lcom/intellij/psi/tree/IElementType;
        //    74: aload_0        
        //    75: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.firstLeafTypeRight:Lcom/intellij/psi/tree/IElementType;
        //    78: if_acmpne       132
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.lastLeafTypeLeft:Lcom/intellij/psi/tree/IElementType;
        //    92: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isAnyLBrace:(Lcom/intellij/psi/tree/IElementType;)Z
        //    95: ifne            122
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: aload_0        
        //   106: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.lastLeafTypeLeft:Lcom/intellij/psi/tree/IElementType;
        //   109: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isAnyRBrace:(Lcom/intellij/psi/tree/IElementType;)Z
        //   112: ifeq            132
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: aload_0        
        //   123: iconst_0       
        //   124: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //   127: ireturn        
        //   128: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_0        
        //   133: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   136: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OPERATOR_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   139: if_acmpne       190
        //   142: aload_0        
        //   143: aload_0        
        //   144: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   147: instanceof      Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   150: ifeq            177
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   159: athrow         
        //   160: aload_0        
        //   161: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   164: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_BETWEEN_OPERATOR_AND_PUNCTUATOR:Z
        //   167: ifeq            185
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: iconst_1       
        //   178: goto            186
        //   181: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: iconst_0       
        //   186: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //   189: ireturn        
        //   190: aload_0        
        //   191: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   194: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   197: if_acmpne       216
        //   200: aload_0        
        //   201: aload_0        
        //   202: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   205: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES:Z
        //   208: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //   211: ireturn        
        //   212: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: aload_0        
        //   217: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   220: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   223: if_acmpne       305
        //   226: aload_0        
        //   227: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   230: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CATCH_SECTION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   233: if_acmpne       293
        //   236: goto            243
        //   239: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   242: athrow         
        //   243: aload_0        
        //   244: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   247: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CATCH_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   250: if_acmpeq       277
        //   253: goto            260
        //   256: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   259: athrow         
        //   260: aload_0        
        //   261: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   264: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.OBJC_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   267: if_acmpne       305
        //   270: goto            277
        //   273: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: aload_0        
        //   278: aload_0        
        //   279: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   282: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_CATCH_PARENTHESES:Z
        //   285: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //   288: ireturn        
        //   289: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   292: athrow         
        //   293: aload_0        
        //   294: aload_0        
        //   295: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   298: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_METHOD_PARENTHESES:Z
        //   301: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //   304: ireturn        
        //   305: aload_0        
        //   306: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   309: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   312: if_acmpne       488
        //   315: aload_0        
        //   316: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   319: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IF_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   322: if_acmpne       348
        //   325: goto            332
        //   328: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   331: athrow         
        //   332: aload_0        
        //   333: aload_0        
        //   334: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   337: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_IF_PARENTHESES:Z
        //   340: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //   343: ireturn        
        //   344: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   347: athrow         
        //   348: aload_0        
        //   349: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   352: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FOR_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   355: if_acmpne       374
        //   358: aload_0        
        //   359: aload_0        
        //   360: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   363: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_FOR_PARENTHESES:Z
        //   366: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //   369: ireturn        
        //   370: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   373: athrow         
        //   374: aload_0        
        //   375: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   378: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHILE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   381: if_acmpne       400
        //   384: aload_0        
        //   385: aload_0        
        //   386: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   389: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_WHILE_PARENTHESES:Z
        //   392: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //   395: ireturn        
        //   396: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   399: athrow         
        //   400: aload_0        
        //   401: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   404: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SWITCH_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   407: if_acmpne       426
        //   410: aload_0        
        //   411: aload_0        
        //   412: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   415: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_SWITCH_PARENTHESES:Z
        //   418: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //   421: ireturn        
        //   422: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   425: athrow         
        //   426: aload_0        
        //   427: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   430: aload_0        
        //   431: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   434: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SYNCHRONIZED_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   437: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isObjCKeywordWithDog:(Lcom/intellij/psi/tree/IElementType;Lcom/intellij/lang/ASTNode;Lcom/jetbrains/cidr/lang/parser/OCElementType;)Z
        //   440: ifeq            459
        //   443: aload_0        
        //   444: aload_0        
        //   445: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   448: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_SYNCHRONIZED_PARENTHESES:Z
        //   451: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //   454: ireturn        
        //   455: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   458: athrow         
        //   459: getstatic       com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.KEYWORD_WITH_LIST:Lcom/intellij/psi/tree/TokenSet;
        //   462: aload_0        
        //   463: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   466: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   469: ifeq            488
        //   472: aload_0        
        //   473: aload_0        
        //   474: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   477: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES:Z
        //   480: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //   483: ireturn        
        //   484: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   487: athrow         
        //   488: aload_0        
        //   489: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   492: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROPERTY_ATTRIBUTES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   495: if_acmpeq       515
        //   498: aload_0        
        //   499: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   502: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROPERTY_ATTRIBUTES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   505: if_acmpne       563
        //   508: goto            515
        //   511: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   514: athrow         
        //   515: aload_0        
        //   516: aload_0        
        //   517: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   520: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_BEFORE_PROPERTY_ATTRIBUTES_PARENTHESES:Z
        //   523: ifne            550
        //   526: goto            533
        //   529: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   532: athrow         
        //   533: aload_0        
        //   534: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   537: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROPERTY_ATTRIBUTES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   540: if_acmpne       558
        //   543: goto            550
        //   546: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   549: athrow         
        //   550: iconst_1       
        //   551: goto            559
        //   554: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   557: athrow         
        //   558: iconst_0       
        //   559: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //   562: ireturn        
        //   563: aload_0        
        //   564: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   567: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   570: if_acmpeq       590
        //   573: aload_0        
        //   574: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   577: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ASSIGNMENT_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   580: if_acmpne       646
        //   583: goto            590
        //   586: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   589: athrow         
        //   590: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ASSIGNMENT_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //   593: aload_0        
        //   594: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   597: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   600: ifne            630
        //   603: goto            610
        //   606: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   609: athrow         
        //   610: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ASSIGNMENT_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //   613: aload_0        
        //   614: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   617: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   620: ifeq            646
        //   623: goto            630
        //   626: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   629: athrow         
        //   630: aload_0        
        //   631: aload_0        
        //   632: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   635: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS:Z
        //   638: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.c:(Z)Z
        //   641: ireturn        
        //   642: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   645: athrow         
        //   646: aload_0        
        //   647: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   650: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SYNTHESIZED_PROPERTY:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   653: if_acmpne       706
        //   656: aload_0        
        //   657: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   660: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   663: if_acmpeq       690
        //   666: goto            673
        //   669: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   672: athrow         
        //   673: aload_0        
        //   674: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   677: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   680: if_acmpne       706
        //   683: goto            690
        //   686: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   689: athrow         
        //   690: aload_0        
        //   691: aload_0        
        //   692: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   695: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS:Z
        //   698: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.c:(Z)Z
        //   701: ireturn        
        //   702: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   705: athrow         
        //   706: aload_0        
        //   707: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   710: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.QUALIFIED_EXPRESSION_ACCESSOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   713: if_acmpeq       733
        //   716: aload_0        
        //   717: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   720: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.QUALIFIED_EXPRESSION_ACCESSOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   723: if_acmpne       749
        //   726: goto            733
        //   729: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   732: athrow         
        //   733: aload_0        
        //   734: aload_0        
        //   735: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   738: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_AROUND_PM_OPERATORS:Z
        //   741: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.c:(Z)Z
        //   744: ireturn        
        //   745: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   748: athrow         
        //   749: aload_0        
        //   750: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   753: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   756: iconst_2       
        //   757: anewarray       Lcom/intellij/psi/tree/IElementType;
        //   760: dup            
        //   761: iconst_0       
        //   762: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_LAMBDA_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   765: aastore        
        //   766: dup            
        //   767: iconst_1       
        //   768: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   771: aastore        
        //   772: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;[Lcom/intellij/psi/tree/IElementType;)Z
        //   775: ifne            814
        //   778: aload_0        
        //   779: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   782: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   785: iconst_2       
        //   786: anewarray       Lcom/intellij/psi/tree/IElementType;
        //   789: dup            
        //   790: iconst_0       
        //   791: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_LAMBDA_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   794: aastore        
        //   795: dup            
        //   796: iconst_1       
        //   797: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   800: aastore        
        //   801: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;[Lcom/intellij/psi/tree/IElementType;)Z
        //   804: ifeq            830
        //   807: goto            814
        //   810: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   813: athrow         
        //   814: aload_0        
        //   815: aload_0        
        //   816: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   819: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_AROUND_LAMBDA_ARROW:Z
        //   822: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.c:(Z)Z
        //   825: ireturn        
        //   826: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   829: athrow         
        //   830: aload_0        
        //   831: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   834: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON2X:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   837: if_acmpeq       857
        //   840: aload_0        
        //   841: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   844: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON2X:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   847: if_acmpne       867
        //   850: goto            857
        //   853: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   856: athrow         
        //   857: aload_0        
        //   858: iconst_0       
        //   859: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.c:(Z)Z
        //   862: ireturn        
        //   863: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   866: athrow         
        //   867: aload_0        
        //   868: aload_0        
        //   869: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   872: aload_0        
        //   873: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   876: invokevirtual   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.needSpaceAroundBinaryOperator:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Ljava/lang/Boolean;
        //   879: astore_1       
        //   880: aload_1        
        //   881: ifnull          893
        //   884: aload_1        
        //   885: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   888: ireturn        
        //   889: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   892: athrow         
        //   893: aload_0        
        //   894: aload_0        
        //   895: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   898: aload_0        
        //   899: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //   902: invokevirtual   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.needSpaceAroundBinaryOperator:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Ljava/lang/Boolean;
        //   905: astore_2       
        //   906: aload_2        
        //   907: ifnull          919
        //   910: aload_2        
        //   911: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   914: ireturn        
        //   915: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   918: athrow         
        //   919: aload_0        
        //   920: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   923: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   926: if_acmpeq       980
        //   929: aload_0        
        //   930: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   933: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   936: if_acmpeq       980
        //   939: goto            946
        //   942: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   945: athrow         
        //   946: aload_0        
        //   947: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   950: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   953: if_acmpeq       980
        //   956: goto            963
        //   959: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   962: athrow         
        //   963: aload_0        
        //   964: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   967: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   970: if_acmpne       1153
        //   973: goto            980
        //   976: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   979: athrow         
        //   980: aload_0        
        //   981: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   984: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   987: if_acmpeq       1153
        //   990: goto            997
        //   993: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   996: athrow         
        //   997: aload_0        
        //   998: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  1001: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1004: if_acmpeq       1153
        //  1007: goto            1014
        //  1010: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1013: athrow         
        //  1014: aload_0        
        //  1015: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  1018: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isRefToken:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1021: dup            
        //  1022: istore_3       
        //  1023: aload_0        
        //  1024: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  1027: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isRefToken:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1030: dup            
        //  1031: istore          4
        //  1033: ior            
        //  1034: ifeq            1153
        //  1037: iload_3        
        //  1038: ifeq            1070
        //  1041: goto            1048
        //  1044: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1047: athrow         
        //  1048: iload           4
        //  1050: ifeq            1070
        //  1053: goto            1060
        //  1056: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1059: athrow         
        //  1060: aload_0        
        //  1061: iconst_0       
        //  1062: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  1065: ireturn        
        //  1066: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1069: athrow         
        //  1070: iload_3        
        //  1071: ifeq            1117
        //  1074: aload_0        
        //  1075: aload_0        
        //  1076: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  1079: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1082: if_acmpne       1106
        //  1085: goto            1092
        //  1088: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1091: athrow         
        //  1092: aload_0        
        //  1093: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  1096: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_AFTER_POINTER_IN_DECLARATION:Z
        //  1099: goto            1113
        //  1102: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1105: athrow         
        //  1106: aload_0        
        //  1107: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  1110: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_AFTER_REFERENCE_IN_DECLARATION:Z
        //  1113: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  1116: ireturn        
        //  1117: aload_0        
        //  1118: aload_0        
        //  1119: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  1122: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1125: if_acmpne       1142
        //  1128: aload_0        
        //  1129: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  1132: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_BEFORE_POINTER_IN_DECLARATION:Z
        //  1135: goto            1149
        //  1138: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1141: athrow         
        //  1142: aload_0        
        //  1143: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  1146: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_BEFORE_REFERENCE_IN_DECLARATION:Z
        //  1149: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  1152: ireturn        
        //  1153: aload_0        
        //  1154: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1157: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.UNARY_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1160: if_acmpne       1276
        //  1163: aload_0        
        //  1164: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  1167: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isRefToken:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1170: dup            
        //  1171: istore_3       
        //  1172: aload_0        
        //  1173: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  1176: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.UNARY_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1179: if_acmpne       1215
        //  1182: aload_0        
        //  1183: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //  1186: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //  1191: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //  1194: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isRefToken:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1197: ifeq            1215
        //  1200: goto            1207
        //  1203: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1206: athrow         
        //  1207: iconst_1       
        //  1208: goto            1216
        //  1211: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1214: athrow         
        //  1215: iconst_0       
        //  1216: dup            
        //  1217: istore          4
        //  1219: ior            
        //  1220: ifeq            1276
        //  1223: iload_3        
        //  1224: ifeq            1256
        //  1227: goto            1234
        //  1230: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1233: athrow         
        //  1234: iload           4
        //  1236: ifeq            1256
        //  1239: goto            1246
        //  1242: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1245: athrow         
        //  1246: aload_0        
        //  1247: iconst_0       
        //  1248: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  1251: ireturn        
        //  1252: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1255: athrow         
        //  1256: iload_3        
        //  1257: ifeq            1276
        //  1260: aload_0        
        //  1261: aload_0        
        //  1262: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  1265: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_AFTER_REFERENCE_IN_RVALUE:Z
        //  1268: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  1271: ireturn        
        //  1272: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1275: athrow         
        //  1276: aload_0        
        //  1277: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1280: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.UNARY_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1283: if_acmpeq       1320
        //  1286: aload_0        
        //  1287: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1290: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PREFIX_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1293: if_acmpeq       1320
        //  1296: goto            1303
        //  1299: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1302: athrow         
        //  1303: aload_0        
        //  1304: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1307: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.POSTFIX_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1310: if_acmpne       1407
        //  1313: goto            1320
        //  1316: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1319: athrow         
        //  1320: aload_0        
        //  1321: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1324: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.POSTFIX_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1327: if_acmpne       1348
        //  1330: goto            1337
        //  1333: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1336: athrow         
        //  1337: aload_0        
        //  1338: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  1341: goto            1352
        //  1344: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1347: athrow         
        //  1348: aload_0        
        //  1349: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  1352: astore_3       
        //  1353: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.UNARY_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //  1356: aload_3        
        //  1357: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1360: ifne            1391
        //  1363: aload_3        
        //  1364: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUSPLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1367: if_acmpeq       1391
        //  1370: goto            1377
        //  1373: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1376: athrow         
        //  1377: aload_3        
        //  1378: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUSMINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1381: if_acmpne       1407
        //  1384: goto            1391
        //  1387: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1390: athrow         
        //  1391: aload_0        
        //  1392: aload_0        
        //  1393: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  1396: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_AROUND_UNARY_OPERATOR:Z
        //  1399: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.c:(Z)Z
        //  1402: ireturn        
        //  1403: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1406: athrow         
        //  1407: aload_0        
        //  1408: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1411: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1414: if_acmpeq       1434
        //  1417: aload_0        
        //  1418: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1421: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1424: if_acmpne       1508
        //  1427: goto            1434
        //  1430: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1433: athrow         
        //  1434: aload_0        
        //  1435: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //  1438: ifnull          1508
        //  1441: goto            1448
        //  1444: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1447: athrow         
        //  1448: aload_0        
        //  1449: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //  1452: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //  1457: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //  1460: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1463: if_acmpeq       1508
        //  1466: goto            1473
        //  1469: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1472: athrow         
        //  1473: aload_0        
        //  1474: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //  1477: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //  1482: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //  1485: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1488: if_acmpne       1508
        //  1491: goto            1498
        //  1494: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1497: athrow         
        //  1498: aload_0        
        //  1499: iconst_1       
        //  1500: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  1503: ireturn        
        //  1504: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1507: athrow         
        //  1508: aload_0        
        //  1509: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //  1512: aload_0        
        //  1513: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  1516: iconst_1       
        //  1517: new             Lcom/jetbrains/cidr/lang/formatting/OCSpacingProcessor$2;
        //  1520: dup            
        //  1521: aload_0        
        //  1522: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor$2.<init>:(Lcom/jetbrains/cidr/lang/formatting/OCSpacingProcessor;)V
        //  1525: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.processBraced:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;ZLcom/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor;)Ljava/lang/Object;
        //  1528: checkcast       Ljava/lang/Boolean;
        //  1531: astore_3       
        //  1532: aload_3        
        //  1533: ifnull          1545
        //  1536: aload_3        
        //  1537: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1540: ireturn        
        //  1541: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1544: athrow         
        //  1545: aload_0        
        //  1546: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1549: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.IF_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1552: if_acmpne       1588
        //  1555: aload_0        
        //  1556: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  1559: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ELSE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1562: if_acmpne       1588
        //  1565: goto            1572
        //  1568: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1571: athrow         
        //  1572: aload_0        
        //  1573: aload_0        
        //  1574: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  1577: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_ELSE_KEYWORD:Z
        //  1580: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  1583: ireturn        
        //  1584: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1587: athrow         
        //  1588: aload_0        
        //  1589: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1592: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DO_WHILE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1595: if_acmpne       1631
        //  1598: aload_0        
        //  1599: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  1602: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHILE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1605: if_acmpne       1631
        //  1608: goto            1615
        //  1611: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1614: athrow         
        //  1615: aload_0        
        //  1616: aload_0        
        //  1617: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  1620: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_WHILE_KEYWORD:Z
        //  1623: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  1626: ireturn        
        //  1627: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1630: athrow         
        //  1631: aload_0        
        //  1632: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  1635: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CATCH_SECTION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1638: if_acmpne       1657
        //  1641: aload_0        
        //  1642: aload_0        
        //  1643: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  1646: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_CATCH_KEYWORD:Z
        //  1649: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  1652: ireturn        
        //  1653: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1656: athrow         
        //  1657: aload_0        
        //  1658: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  1661: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FINALLY_SECTION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1664: if_acmpne       1683
        //  1667: aload_0        
        //  1668: aload_0        
        //  1669: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  1672: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_FINALLY_KEYWORD:Z
        //  1675: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  1678: ireturn        
        //  1679: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1682: athrow         
        //  1683: aload_0        
        //  1684: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  1687: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1690: if_acmpne       1723
        //  1693: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS_WITH_DOGS:Lcom/intellij/psi/tree/TokenSet;
        //  1696: aload_0        
        //  1697: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  1700: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1703: ifeq            1723
        //  1706: goto            1713
        //  1709: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1712: athrow         
        //  1713: aload_0        
        //  1714: iconst_0       
        //  1715: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  1718: ireturn        
        //  1719: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1722: athrow         
        //  1723: aload_0        
        //  1724: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1727: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isCollectionOrStructureInitializer:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1730: ifeq            1923
        //  1733: aload_0        
        //  1734: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.m:()Z
        //  1737: ifne            1761
        //  1740: goto            1747
        //  1743: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1746: athrow         
        //  1747: aload_0        
        //  1748: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.n:()Z
        //  1751: ifeq            1923
        //  1754: goto            1761
        //  1757: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1760: athrow         
        //  1761: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1764: aload_0        
        //  1765: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  1768: if_acmpeq       1795
        //  1771: goto            1778
        //  1774: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1777: athrow         
        //  1778: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1781: aload_0        
        //  1782: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  1785: if_acmpne       1803
        //  1788: goto            1795
        //  1791: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1794: athrow         
        //  1795: iconst_1       
        //  1796: goto            1804
        //  1799: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1802: athrow         
        //  1803: iconst_0       
        //  1804: istore          4
        //  1806: iload           4
        //  1808: ifeq            1825
        //  1811: aload_0        
        //  1812: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  1815: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.ARRAY_INITIALIZER_LBRACE_ON_NEXT_LINE:Z
        //  1818: goto            1832
        //  1821: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1824: athrow         
        //  1825: aload_0        
        //  1826: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  1829: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.ARRAY_INITIALIZER_RBRACE_ON_NEXT_LINE:Z
        //  1832: istore          5
        //  1834: aload_0        
        //  1835: aload_0        
        //  1836: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  1839: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1842: if_acmpne       1862
        //  1845: aload_0        
        //  1846: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  1849: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1852: if_acmpeq       1896
        //  1855: goto            1862
        //  1858: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1861: athrow         
        //  1862: aload_0        
        //  1863: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  1866: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1869: if_acmpne       1910
        //  1872: goto            1879
        //  1875: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1878: athrow         
        //  1879: aload_0        
        //  1880: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  1883: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1886: if_acmpne       1910
        //  1889: goto            1896
        //  1892: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1895: athrow         
        //  1896: aload_0        
        //  1897: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  1900: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_EMPTY_ARRAY_INITIALIZER_BRACES:Z
        //  1903: goto            1917
        //  1906: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1909: athrow         
        //  1910: aload_0        
        //  1911: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  1914: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_ARRAY_INITIALIZER_BRACES:Z
        //  1917: iload           5
        //  1919: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(ZZ)Z
        //  1922: ireturn        
        //  1923: aload_0        
        //  1924: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.n:()Z
        //  1927: ifeq            2104
        //  1930: aload_0        
        //  1931: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1934: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MESSAGE_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1937: if_acmpne       1963
        //  1940: goto            1947
        //  1943: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1946: athrow         
        //  1947: aload_0        
        //  1948: aload_0        
        //  1949: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  1952: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_SEND_MESSAGE_BRACKETS:Z
        //  1955: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  1958: ireturn        
        //  1959: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1962: athrow         
        //  1963: aload_0        
        //  1964: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1967: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARRAY_INDEX_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1970: if_acmpne       1989
        //  1973: aload_0        
        //  1974: aload_0        
        //  1975: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  1978: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_BRACKETS:Z
        //  1981: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  1984: ireturn        
        //  1985: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1988: athrow         
        //  1989: aload_0        
        //  1990: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  1993: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_LAMBDA_INTRODUCER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1996: if_acmpne       2098
        //  1999: aload_0        
        //  2000: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  2003: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2006: if_acmpne       2030
        //  2009: goto            2016
        //  2012: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2015: athrow         
        //  2016: aload_0        
        //  2017: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2020: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.LAMBDA_CAPTURE_LIST_NEW_LINE_AFTER_LBRACKET:Z
        //  2023: goto            2037
        //  2026: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2029: athrow         
        //  2030: aload_0        
        //  2031: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2034: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.LAMBDA_CAPTURE_LIST_NEW_LINE_BEFORE_RBRACKET:Z
        //  2037: istore          4
        //  2039: aload_0        
        //  2040: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  2043: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2046: if_acmpne       2080
        //  2049: aload_0        
        //  2050: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  2053: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2056: if_acmpne       2080
        //  2059: goto            2066
        //  2062: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2065: athrow         
        //  2066: aload_0        
        //  2067: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2070: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_EMPTY_LAMBDA_CAPTURE_LIST_BRACKET:Z
        //  2073: goto            2087
        //  2076: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2079: athrow         
        //  2080: aload_0        
        //  2081: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2084: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_LAMBDA_CAPTURE_LIST_BRACKET:Z
        //  2087: istore          5
        //  2089: aload_0        
        //  2090: iload           5
        //  2092: iload           4
        //  2094: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(ZZ)Z
        //  2097: ireturn        
        //  2098: aload_0        
        //  2099: iconst_0       
        //  2100: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  2103: ireturn        
        //  2104: aload_0        
        //  2105: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  2108: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2111: if_acmpne       2124
        //  2114: aload_0        
        //  2115: iconst_0       
        //  2116: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  2119: ireturn        
        //  2120: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2123: athrow         
        //  2124: aload_0        
        //  2125: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.m:()Z
        //  2128: ifeq            2191
        //  2131: aload_0        
        //  2132: aload_0        
        //  2133: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  2136: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2139: if_acmpne       2180
        //  2142: goto            2149
        //  2145: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2148: athrow         
        //  2149: aload_0        
        //  2150: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  2153: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2156: if_acmpne       2180
        //  2159: goto            2166
        //  2162: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2165: athrow         
        //  2166: aload_0        
        //  2167: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2170: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_EMPTY_BRACES:Z
        //  2173: goto            2187
        //  2176: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2179: athrow         
        //  2180: aload_0        
        //  2181: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  2184: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_BRACES:Z
        //  2187: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  2190: ireturn        
        //  2191: aload_0        
        //  2192: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2195: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CATEGORY_NAME:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2198: if_acmpne       2251
        //  2201: aload_0        
        //  2202: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  2205: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2208: if_acmpeq       2235
        //  2211: goto            2218
        //  2214: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2217: athrow         
        //  2218: aload_0        
        //  2219: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  2222: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2225: if_acmpne       2251
        //  2228: goto            2235
        //  2231: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2234: athrow         
        //  2235: aload_0        
        //  2236: aload_0        
        //  2237: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2240: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_CATEGORY_PARENTHESES:Z
        //  2243: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  2246: ireturn        
        //  2247: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2250: athrow         
        //  2251: aload_0        
        //  2252: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  2255: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2258: if_acmpeq       2278
        //  2261: aload_0        
        //  2262: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  2265: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2268: if_acmpne       2989
        //  2271: goto            2278
        //  2274: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2277: athrow         
        //  2278: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.LIST_OWNER_SET:Lcom/intellij/psi/tree/TokenSet;
        //  2281: aload_0        
        //  2282: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2285: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  2288: ifeq            2358
        //  2291: goto            2298
        //  2294: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2297: athrow         
        //  2298: aload_0        
        //  2299: aload_0        
        //  2300: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  2303: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2306: if_acmpne       2347
        //  2309: goto            2316
        //  2312: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2315: athrow         
        //  2316: aload_0        
        //  2317: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  2320: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2323: if_acmpne       2347
        //  2326: goto            2333
        //  2329: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2332: athrow         
        //  2333: aload_0        
        //  2334: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2337: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_EMPTY_FUNCTION_CALL_PARENTHESES:Z
        //  2340: goto            2354
        //  2343: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2346: athrow         
        //  2347: aload_0        
        //  2348: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2351: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_FUNCTION_CALL_PARENTHESES:Z
        //  2354: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  2357: ireturn        
        //  2358: aload_0        
        //  2359: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2362: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PAREN_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2365: if_acmpne       2422
        //  2368: aload_0        
        //  2369: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  2372: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2375: if_acmpne       2399
        //  2378: goto            2385
        //  2381: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2384: athrow         
        //  2385: aload_0        
        //  2386: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  2389: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.PARENTHESES_EXPRESSION_LPAREN_WRAP:Z
        //  2392: goto            2406
        //  2395: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2398: athrow         
        //  2399: aload_0        
        //  2400: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  2403: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.PARENTHESES_EXPRESSION_RPAREN_WRAP:Z
        //  2406: istore          4
        //  2408: aload_0        
        //  2409: aload_0        
        //  2410: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  2413: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_PARENTHESES:Z
        //  2416: iload           4
        //  2418: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(ZZ)Z
        //  2421: ireturn        
        //  2422: aload_0        
        //  2423: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2426: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2429: if_acmpeq       2449
        //  2432: aload_0        
        //  2433: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2436: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2439: if_acmpne       2587
        //  2442: goto            2449
        //  2445: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2448: athrow         
        //  2449: aload_0        
        //  2450: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2453: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2456: if_acmpeq       2525
        //  2459: goto            2466
        //  2462: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2465: athrow         
        //  2466: aload_0        
        //  2467: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  2470: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2473: if_acmpne       2507
        //  2476: goto            2483
        //  2479: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2482: athrow         
        //  2483: aload_0        
        //  2484: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2487: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_NEW_LINE_AFTER_LPAR:Z
        //  2490: ifeq            2525
        //  2493: goto            2500
        //  2496: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2499: athrow         
        //  2500: goto            2517
        //  2503: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2506: athrow         
        //  2507: aload_0        
        //  2508: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2511: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_NEW_LINE_BEFORE_RPAR:Z
        //  2514: ifeq            2525
        //  2517: iconst_1       
        //  2518: goto            2526
        //  2521: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2524: athrow         
        //  2525: iconst_0       
        //  2526: istore          4
        //  2528: aload_0        
        //  2529: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  2532: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2535: if_acmpne       2569
        //  2538: aload_0        
        //  2539: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  2542: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2545: if_acmpne       2569
        //  2548: goto            2555
        //  2551: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2554: athrow         
        //  2555: aload_0        
        //  2556: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2559: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_EMPTY_FUNCTION_CALL_PARENTHESES:Z
        //  2562: goto            2576
        //  2565: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2568: athrow         
        //  2569: aload_0        
        //  2570: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2573: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_FUNCTION_CALL_PARENTHESES:Z
        //  2576: istore          5
        //  2578: aload_0        
        //  2579: iload           5
        //  2581: iload           4
        //  2583: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(ZZ)Z
        //  2586: ireturn        
        //  2587: aload_0        
        //  2588: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2591: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2594: if_acmpeq       2614
        //  2597: aload_0        
        //  2598: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2601: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2604: if_acmpne       2778
        //  2607: goto            2614
        //  2610: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2613: athrow         
        //  2614: aload_0        
        //  2615: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.parentType:Lcom/intellij/psi/tree/IElementType;
        //  2618: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CATCH_SECTION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2621: if_acmpne       2647
        //  2624: goto            2631
        //  2627: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2630: athrow         
        //  2631: aload_0        
        //  2632: aload_0        
        //  2633: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  2636: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_CATCH_PARENTHESES:Z
        //  2639: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  2642: ireturn        
        //  2643: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2646: athrow         
        //  2647: aload_0        
        //  2648: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2651: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2654: if_acmpeq       2716
        //  2657: aload_0        
        //  2658: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  2661: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2664: if_acmpne       2698
        //  2667: goto            2674
        //  2670: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2673: athrow         
        //  2674: aload_0        
        //  2675: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2678: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_PARAMETERS_NEW_LINE_AFTER_LPAR:Z
        //  2681: ifeq            2716
        //  2684: goto            2691
        //  2687: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2690: athrow         
        //  2691: goto            2708
        //  2694: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2697: athrow         
        //  2698: aload_0        
        //  2699: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2702: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_PARAMETERS_NEW_LINE_BEFORE_RPAR:Z
        //  2705: ifeq            2716
        //  2708: iconst_1       
        //  2709: goto            2717
        //  2712: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2715: athrow         
        //  2716: iconst_0       
        //  2717: istore          4
        //  2719: aload_0        
        //  2720: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  2723: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2726: if_acmpne       2760
        //  2729: aload_0        
        //  2730: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  2733: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2736: if_acmpne       2760
        //  2739: goto            2746
        //  2742: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2745: athrow         
        //  2746: aload_0        
        //  2747: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2750: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_EMPTY_FUNCTION_DECLARATION_PARENTHESES:Z
        //  2753: goto            2767
        //  2756: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2759: athrow         
        //  2760: aload_0        
        //  2761: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2764: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_FUNCTION_DECLARATION_PARENTHESES:Z
        //  2767: istore          5
        //  2769: aload_0        
        //  2770: iload           5
        //  2772: iload           4
        //  2774: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(ZZ)Z
        //  2777: ireturn        
        //  2778: aload_0        
        //  2779: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2782: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.IF_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2785: if_acmpne       2804
        //  2788: aload_0        
        //  2789: aload_0        
        //  2790: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  2793: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_IF_PARENTHESES:Z
        //  2796: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  2799: ireturn        
        //  2800: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2803: athrow         
        //  2804: aload_0        
        //  2805: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2808: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isForOrForEachStatement:(Lcom/intellij/psi/tree/IElementType;)Z
        //  2811: ifeq            2868
        //  2814: aload_0        
        //  2815: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  2818: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2821: if_acmpne       2845
        //  2824: goto            2831
        //  2827: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2830: athrow         
        //  2831: aload_0        
        //  2832: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  2835: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.FOR_STATEMENT_LPAREN_ON_NEXT_LINE:Z
        //  2838: goto            2852
        //  2841: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2844: athrow         
        //  2845: aload_0        
        //  2846: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  2849: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.FOR_STATEMENT_RPAREN_ON_NEXT_LINE:Z
        //  2852: istore          4
        //  2854: aload_0        
        //  2855: aload_0        
        //  2856: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  2859: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_FOR_PARENTHESES:Z
        //  2862: iload           4
        //  2864: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(ZZ)Z
        //  2867: ireturn        
        //  2868: aload_0        
        //  2869: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2872: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.WHILE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2875: if_acmpeq       2895
        //  2878: aload_0        
        //  2879: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2882: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DO_WHILE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2885: if_acmpne       2911
        //  2888: goto            2895
        //  2891: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2894: athrow         
        //  2895: aload_0        
        //  2896: aload_0        
        //  2897: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  2900: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_WHILE_PARENTHESES:Z
        //  2903: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  2906: ireturn        
        //  2907: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2910: athrow         
        //  2911: aload_0        
        //  2912: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2915: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SWITCH_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2918: if_acmpne       2937
        //  2921: aload_0        
        //  2922: aload_0        
        //  2923: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  2926: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_SWITCH_PARENTHESES:Z
        //  2929: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  2932: ireturn        
        //  2933: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2936: athrow         
        //  2937: aload_0        
        //  2938: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2941: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SYNCHRONIZED_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2944: if_acmpne       2963
        //  2947: aload_0        
        //  2948: aload_0        
        //  2949: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  2952: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_SYNCHRONIZED_PARENTHESES:Z
        //  2955: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  2958: ireturn        
        //  2959: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2962: athrow         
        //  2963: aload_0        
        //  2964: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  2967: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROPERTY_ATTRIBUTES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2970: if_acmpne       2989
        //  2973: aload_0        
        //  2974: aload_0        
        //  2975: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  2978: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_PROPERTY_ATTRIBUTES_PARENTHESES:Z
        //  2981: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  2984: ireturn        
        //  2985: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2988: athrow         
        //  2989: aload_0        
        //  2990: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  2993: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2996: if_acmpne       3016
        //  2999: aload_0        
        //  3000: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  3003: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3006: if_acmpeq       3050
        //  3009: goto            3016
        //  3012: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3015: athrow         
        //  3016: aload_0        
        //  3017: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  3020: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3023: if_acmpne       3135
        //  3026: goto            3033
        //  3029: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3032: athrow         
        //  3033: aload_0        
        //  3034: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  3037: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3040: if_acmpne       3135
        //  3043: goto            3050
        //  3046: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3049: athrow         
        //  3050: aload_0        
        //  3051: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3054: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CAST_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3057: if_acmpne       3083
        //  3060: goto            3067
        //  3063: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3066: athrow         
        //  3067: aload_0        
        //  3068: aload_0        
        //  3069: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  3072: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_CAST_PARENTHESES:Z
        //  3075: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  3078: ireturn        
        //  3079: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3082: athrow         
        //  3083: aload_0        
        //  3084: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3087: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3090: if_acmpne       3109
        //  3093: aload_0        
        //  3094: aload_0        
        //  3095: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3098: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_METHOD_RETURN_TYPE_PARENTHESES:Z
        //  3101: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  3104: ireturn        
        //  3105: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3108: athrow         
        //  3109: aload_0        
        //  3110: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3113: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD_SELECTOR_PART:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3116: if_acmpne       3135
        //  3119: aload_0        
        //  3120: aload_0        
        //  3121: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3124: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_METHOD_PARAMETER_TYPE_PARENTHESES:Z
        //  3127: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  3130: ireturn        
        //  3131: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3134: athrow         
        //  3135: aload_0        
        //  3136: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  3139: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3142: if_acmpne       3161
        //  3145: aload_0        
        //  3146: aload_0        
        //  3147: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3150: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_BEFORE_TEMPLATE_DECLARATION_LT:Z
        //  3153: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  3156: ireturn        
        //  3157: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3160: athrow         
        //  3161: aload_0        
        //  3162: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  3165: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3168: if_acmpne       3181
        //  3171: aload_0        
        //  3172: iconst_1       
        //  3173: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  3176: ireturn        
        //  3177: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3180: athrow         
        //  3181: aload_0        
        //  3182: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  3185: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TEMPLATE_ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3188: if_acmpne       3207
        //  3191: aload_0        
        //  3192: aload_0        
        //  3193: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3196: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_BEFORE_TEMPLATE_CALL_LT:Z
        //  3199: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  3202: ireturn        
        //  3203: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3206: athrow         
        //  3207: aload_0        
        //  3208: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  3211: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROTOCOL_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3214: if_acmpeq       3251
        //  3217: aload_0        
        //  3218: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3221: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isProtocolListOrReference:(Lcom/intellij/psi/tree/IElementType;)Z
        //  3224: ifeq            3267
        //  3227: goto            3234
        //  3230: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3233: athrow         
        //  3234: aload_0        
        //  3235: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  3238: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3241: if_acmpne       3267
        //  3244: goto            3251
        //  3247: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3250: athrow         
        //  3251: aload_0        
        //  3252: aload_0        
        //  3253: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3256: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_BEFORE_PROTOCOLS_BRACKETS:Z
        //  3259: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  3262: ireturn        
        //  3263: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3266: athrow         
        //  3267: aload_0        
        //  3268: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  3271: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3274: if_acmpeq       3294
        //  3277: aload_0        
        //  3278: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  3281: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3284: if_acmpne       3717
        //  3287: goto            3294
        //  3290: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3293: athrow         
        //  3294: aload_0        
        //  3295: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3298: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isProtocolListOrReference:(Lcom/intellij/psi/tree/IElementType;)Z
        //  3301: ifeq            3327
        //  3304: goto            3311
        //  3307: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3310: athrow         
        //  3311: aload_0        
        //  3312: aload_0        
        //  3313: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3316: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_PROTOCOLS_BRACKETS:Z
        //  3319: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  3322: ireturn        
        //  3323: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3326: athrow         
        //  3327: aload_0        
        //  3328: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3331: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TEMPLATE_ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3334: if_acmpne       3522
        //  3337: aload_0        
        //  3338: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  3341: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3344: if_acmpne       3368
        //  3347: goto            3354
        //  3350: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3353: athrow         
        //  3354: aload_0        
        //  3355: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3358: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_CALL_ARGUMENTS_NEW_LINE_AFTER_LT:Z
        //  3361: goto            3375
        //  3364: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3367: athrow         
        //  3368: aload_0        
        //  3369: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3372: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_CALL_ARGUMENTS_NEW_LINE_BEFORE_GT:Z
        //  3375: istore          4
        //  3377: aload_0        
        //  3378: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  3381: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3384: if_acmpne       3418
        //  3387: aload_0        
        //  3388: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  3391: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3394: if_acmpne       3418
        //  3397: goto            3404
        //  3400: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3403: athrow         
        //  3404: aload_0        
        //  3405: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3408: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_EMPTY_TEMPLATE_CALL_LTGT:Z
        //  3411: goto            3511
        //  3414: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3417: athrow         
        //  3418: aload_0        
        //  3419: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  3422: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3425: if_acmpne       3442
        //  3428: aload_0        
        //  3429: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3432: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_TEMPLATE_CALL_LTGT:Z
        //  3435: goto            3511
        //  3438: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3441: athrow         
        //  3442: aload_0        
        //  3443: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //  3446: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.lastLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //  3449: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //  3452: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3455: if_acmpne       3504
        //  3458: aload_0        
        //  3459: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3462: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_TEMPLATE_CALL_LTGT:Z
        //  3465: ifne            3492
        //  3468: goto            3475
        //  3471: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3474: athrow         
        //  3475: aload_0        
        //  3476: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3479: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_TEMPLATE_DOUBLE_GT:Z
        //  3482: ifeq            3500
        //  3485: goto            3492
        //  3488: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3491: athrow         
        //  3492: iconst_1       
        //  3493: goto            3511
        //  3496: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3499: athrow         
        //  3500: iconst_0       
        //  3501: goto            3511
        //  3504: aload_0        
        //  3505: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3508: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_TEMPLATE_CALL_LTGT:Z
        //  3511: istore          5
        //  3513: aload_0        
        //  3514: iload           5
        //  3516: iload           4
        //  3518: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(ZZ)Z
        //  3521: ireturn        
        //  3522: aload_0        
        //  3523: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3526: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3529: if_acmpne       3717
        //  3532: aload_0        
        //  3533: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  3536: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3539: if_acmpne       3563
        //  3542: goto            3549
        //  3545: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3548: athrow         
        //  3549: aload_0        
        //  3550: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3553: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_PARAMETERS_NEW_LINE_AFTER_LT:Z
        //  3556: goto            3570
        //  3559: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3562: athrow         
        //  3563: aload_0        
        //  3564: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3567: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_PARAMETERS_NEW_LINE_BEFORE_GT:Z
        //  3570: istore          4
        //  3572: aload_0        
        //  3573: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  3576: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3579: if_acmpne       3613
        //  3582: aload_0        
        //  3583: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  3586: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3589: if_acmpne       3613
        //  3592: goto            3599
        //  3595: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3598: athrow         
        //  3599: aload_0        
        //  3600: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3603: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_EMPTY_TEMPLATE_DECLARATION_LTGT:Z
        //  3606: goto            3706
        //  3609: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3612: athrow         
        //  3613: aload_0        
        //  3614: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  3617: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3620: if_acmpne       3637
        //  3623: aload_0        
        //  3624: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3627: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_TEMPLATE_DECLARATION_LTGT:Z
        //  3630: goto            3706
        //  3633: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3636: athrow         
        //  3637: aload_0        
        //  3638: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //  3641: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.lastLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //  3644: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //  3647: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3650: if_acmpne       3699
        //  3653: aload_0        
        //  3654: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3657: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_TEMPLATE_DECLARATION_LTGT:Z
        //  3660: ifne            3687
        //  3663: goto            3670
        //  3666: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3669: athrow         
        //  3670: aload_0        
        //  3671: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3674: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_TEMPLATE_DOUBLE_GT:Z
        //  3677: ifeq            3695
        //  3680: goto            3687
        //  3683: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3686: athrow         
        //  3687: iconst_1       
        //  3688: goto            3706
        //  3691: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3694: athrow         
        //  3695: iconst_0       
        //  3696: goto            3706
        //  3699: aload_0        
        //  3700: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3703: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_WITHIN_TEMPLATE_DECLARATION_LTGT:Z
        //  3706: istore          5
        //  3708: aload_0        
        //  3709: iload           5
        //  3711: iload           4
        //  3713: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(ZZ)Z
        //  3716: ireturn        
        //  3717: aload_0        
        //  3718: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3721: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CONDITIONAL_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3724: if_acmpne       3881
        //  3727: aload_0        
        //  3728: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  3731: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.QUEST:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3734: if_acmpne       3777
        //  3737: goto            3744
        //  3740: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3743: athrow         
        //  3744: aload_0        
        //  3745: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  3748: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3751: if_acmpne       3777
        //  3754: goto            3761
        //  3757: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3760: athrow         
        //  3761: aload_0        
        //  3762: aload_0        
        //  3763: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3766: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.DISCHARGED_SHORT_TERNARY_OPERATOR:Z
        //  3769: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  3772: ireturn        
        //  3773: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3776: athrow         
        //  3777: aload_0        
        //  3778: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  3781: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.QUEST:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3784: if_acmpne       3803
        //  3787: aload_0        
        //  3788: aload_0        
        //  3789: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  3792: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_AFTER_QUEST:Z
        //  3795: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  3798: ireturn        
        //  3799: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3802: athrow         
        //  3803: aload_0        
        //  3804: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  3807: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.QUEST:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3810: if_acmpne       3829
        //  3813: aload_0        
        //  3814: aload_0        
        //  3815: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  3818: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_QUEST:Z
        //  3821: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  3824: ireturn        
        //  3825: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3828: athrow         
        //  3829: aload_0        
        //  3830: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  3833: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3836: if_acmpne       3855
        //  3839: aload_0        
        //  3840: aload_0        
        //  3841: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  3844: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_AFTER_COLON:Z
        //  3847: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  3850: ireturn        
        //  3851: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3854: athrow         
        //  3855: aload_0        
        //  3856: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  3859: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3862: if_acmpne       3881
        //  3865: aload_0        
        //  3866: aload_0        
        //  3867: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  3870: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_COLON:Z
        //  3873: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  3876: ireturn        
        //  3877: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3880: athrow         
        //  3881: aload_0        
        //  3882: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  3885: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SUPER_CLASS_REF:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3888: if_acmpeq       3908
        //  3891: aload_0        
        //  3892: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  3895: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_BASE_CLAUSE_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3898: if_acmpne       3924
        //  3901: goto            3908
        //  3904: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3907: athrow         
        //  3908: aload_0        
        //  3909: aload_0        
        //  3910: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3913: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_BEFORE_SUPERCLASS_COLON:Z
        //  3916: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  3919: ireturn        
        //  3920: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3923: athrow         
        //  3924: aload_0        
        //  3925: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3928: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SUPER_CLASS_REF:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3931: if_acmpeq       3951
        //  3934: aload_0        
        //  3935: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3938: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_BASE_CLAUSE_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3941: if_acmpne       3984
        //  3944: goto            3951
        //  3947: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3950: athrow         
        //  3951: aload_0        
        //  3952: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  3955: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  3958: if_acmpne       3984
        //  3961: goto            3968
        //  3964: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3967: athrow         
        //  3968: aload_0        
        //  3969: aload_0        
        //  3970: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  3973: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_AFTER_SUPERCLASS_COLON:Z
        //  3976: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  3979: ireturn        
        //  3980: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  3983: athrow         
        //  3984: aload_0        
        //  3985: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  3988: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  3991: if_acmpne       4087
        //  3994: aload_0        
        //  3995: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  3998: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4001: if_acmpne       4044
        //  4004: goto            4011
        //  4007: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4010: athrow         
        //  4011: aload_0        
        //  4012: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  4015: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_CONSTRUCTOR_INITIALIZATION_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4018: if_acmpne       4044
        //  4021: goto            4028
        //  4024: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4027: athrow         
        //  4028: aload_0        
        //  4029: aload_0        
        //  4030: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  4033: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_AFTER_INIT_LIST_COLON:Z
        //  4036: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4039: ireturn        
        //  4040: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4043: athrow         
        //  4044: aload_0        
        //  4045: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  4048: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4051: if_acmpne       4087
        //  4054: aload_0        
        //  4055: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4058: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4061: if_acmpne       4087
        //  4064: goto            4071
        //  4067: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4070: athrow         
        //  4071: aload_0        
        //  4072: aload_0        
        //  4073: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  4076: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_BEFORE_INIT_LIST_COLON:Z
        //  4079: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4082: ireturn        
        //  4083: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4086: athrow         
        //  4087: aload_0        
        //  4088: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4091: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4094: if_acmpeq       4131
        //  4097: aload_0        
        //  4098: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4101: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.REFERENCE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4104: if_acmpeq       4131
        //  4107: goto            4114
        //  4110: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4113: athrow         
        //  4114: aload_0        
        //  4115: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4118: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4121: if_acmpne       4164
        //  4124: goto            4131
        //  4127: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4130: athrow         
        //  4131: aload_0        
        //  4132: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  4135: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.COMPOUND_INITIALIZER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4138: if_acmpne       4164
        //  4141: goto            4148
        //  4144: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4147: athrow         
        //  4148: aload_0        
        //  4149: aload_0        
        //  4150: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  4153: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_BEFORE_INIT_LIST:Z
        //  4156: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4159: ireturn        
        //  4160: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4163: athrow         
        //  4164: aload_0        
        //  4165: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4168: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4171: if_acmpne       4190
        //  4174: aload_0        
        //  4175: aload_0        
        //  4176: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  4179: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_AFTER_COMMA:Z
        //  4182: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4185: ireturn        
        //  4186: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4189: athrow         
        //  4190: aload_0        
        //  4191: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  4194: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4197: if_acmpne       4216
        //  4200: aload_0        
        //  4201: aload_0        
        //  4202: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  4205: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_COMMA:Z
        //  4208: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4211: ireturn        
        //  4212: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4215: athrow         
        //  4216: aload_0        
        //  4217: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4220: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FOR_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4223: if_acmpne       4324
        //  4226: aload_0        
        //  4227: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4230: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4233: if_acmpeq       4260
        //  4236: goto            4243
        //  4239: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4242: athrow         
        //  4243: aload_0        
        //  4244: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  4247: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4250: if_acmpne       4324
        //  4253: goto            4260
        //  4256: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4259: athrow         
        //  4260: aload_0        
        //  4261: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4264: aload_0        
        //  4265: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  4268: if_acmpne       4288
        //  4271: goto            4278
        //  4274: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4277: athrow         
        //  4278: aload_0        
        //  4279: iconst_0       
        //  4280: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4283: ireturn        
        //  4284: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4287: athrow         
        //  4288: aload_0        
        //  4289: aload_0        
        //  4290: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4293: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4296: if_acmpne       4313
        //  4299: aload_0        
        //  4300: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  4303: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_AFTER_SEMICOLON:Z
        //  4306: goto            4320
        //  4309: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4312: athrow         
        //  4313: aload_0        
        //  4314: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  4317: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_BEFORE_SEMICOLON:Z
        //  4320: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4323: ireturn        
        //  4324: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CLASSES:Lcom/intellij/psi/tree/TokenSet;
        //  4327: aload_0        
        //  4328: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4331: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  4334: ifeq            4370
        //  4337: aload_0        
        //  4338: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  4341: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CATEGORY_NAME:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4344: if_acmpne       4370
        //  4347: goto            4354
        //  4350: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4353: athrow         
        //  4354: aload_0        
        //  4355: aload_0        
        //  4356: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  4359: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_BEFORE_CATEGORY_PARENTHESES:Z
        //  4362: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4365: ireturn        
        //  4366: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4369: athrow         
        //  4370: aload_0        
        //  4371: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4374: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MESSAGE_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4377: if_acmpne       4445
        //  4380: aload_0        
        //  4381: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  4384: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MESSAGE_ARGUMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4387: if_acmpne       4445
        //  4390: goto            4397
        //  4393: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4396: athrow         
        //  4397: aload_0        
        //  4398: aload_0        
        //  4399: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4402: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MESSAGE_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4405: if_acmpne       4432
        //  4408: goto            4415
        //  4411: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4414: athrow         
        //  4415: aload_0        
        //  4416: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  4419: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_BEFORE_CHAINED_SEND_MESSAGE:Z
        //  4422: ifeq            4440
        //  4425: goto            4432
        //  4428: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4431: athrow         
        //  4432: iconst_1       
        //  4433: goto            4441
        //  4436: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4439: athrow         
        //  4440: iconst_0       
        //  4441: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4444: ireturn        
        //  4445: aload_0        
        //  4446: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4449: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD_SELECTOR_PART:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4452: if_acmpne       4472
        //  4455: aload_0        
        //  4456: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4459: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4462: if_acmpeq       4489
        //  4465: goto            4472
        //  4468: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4471: athrow         
        //  4472: aload_0        
        //  4473: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4476: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_SELECTOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4479: if_acmpne       4505
        //  4482: goto            4489
        //  4485: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4488: athrow         
        //  4489: aload_0        
        //  4490: aload_0        
        //  4491: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  4494: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_AFTER_COLON_IN_SELECTOR:Z
        //  4497: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4500: ireturn        
        //  4501: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4504: athrow         
        //  4505: aload_0        
        //  4506: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4509: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_SELECTOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4512: if_acmpeq       4532
        //  4515: aload_0        
        //  4516: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4519: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD_SELECTOR_PART:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4522: if_acmpne       4559
        //  4525: goto            4532
        //  4528: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4531: athrow         
        //  4532: aload_0        
        //  4533: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  4536: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4539: if_acmpne       4559
        //  4542: goto            4549
        //  4545: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4548: athrow         
        //  4549: aload_0        
        //  4550: iconst_0       
        //  4551: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4554: ireturn        
        //  4555: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4558: athrow         
        //  4559: aload_0        
        //  4560: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4563: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD_SELECTOR_PART:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4566: if_acmpne       4596
        //  4569: aload_0        
        //  4570: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  4573: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD_SELECTOR_PART:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4576: if_acmpne       4596
        //  4579: goto            4586
        //  4582: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4585: athrow         
        //  4586: aload_0        
        //  4587: iconst_1       
        //  4588: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4591: ireturn        
        //  4592: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4595: athrow         
        //  4596: aload_0        
        //  4597: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  4600: instanceof      Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //  4603: ifeq            4660
        //  4606: aload_0        
        //  4607: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4610: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4613: if_acmpeq       4765
        //  4616: goto            4623
        //  4619: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4622: athrow         
        //  4623: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  4626: aload_0        
        //  4627: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4630: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  4633: ifne            4765
        //  4636: goto            4643
        //  4639: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4642: athrow         
        //  4643: aload_0        
        //  4644: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4647: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.REFERENCE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4650: if_acmpeq       4765
        //  4653: goto            4660
        //  4656: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4659: athrow         
        //  4660: aload_0        
        //  4661: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4664: instanceof      Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //  4667: ifeq            4731
        //  4670: goto            4677
        //  4673: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4676: athrow         
        //  4677: aload_0        
        //  4678: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  4681: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4684: if_acmpeq       4765
        //  4687: goto            4694
        //  4690: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4693: athrow         
        //  4694: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  4697: aload_0        
        //  4698: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  4701: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  4704: ifne            4765
        //  4707: goto            4714
        //  4710: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4713: athrow         
        //  4714: aload_0        
        //  4715: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  4718: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.REFERENCE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4721: if_acmpeq       4765
        //  4724: goto            4731
        //  4727: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4730: athrow         
        //  4731: aload_0        
        //  4732: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4735: instanceof      Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //  4738: ifeq            4775
        //  4741: goto            4748
        //  4744: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4747: athrow         
        //  4748: aload_0        
        //  4749: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  4752: instanceof      Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //  4755: ifeq            4775
        //  4758: goto            4765
        //  4761: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4764: athrow         
        //  4765: aload_0        
        //  4766: iconst_1       
        //  4767: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4770: ireturn        
        //  4771: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4774: athrow         
        //  4775: aload_0        
        //  4776: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4779: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4782: if_acmpne       4835
        //  4785: aload_0        
        //  4786: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4789: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4792: if_acmpeq       4819
        //  4795: goto            4802
        //  4798: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4801: athrow         
        //  4802: aload_0        
        //  4803: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4806: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4809: if_acmpne       4835
        //  4812: goto            4819
        //  4815: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4818: athrow         
        //  4819: aload_0        
        //  4820: aload_0        
        //  4821: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  4824: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_AFTER_VISIBILITY_SIGN_IN_METHOD_DECLARATION:Z
        //  4827: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4830: ireturn        
        //  4831: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4834: athrow         
        //  4835: aload_0        
        //  4836: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4839: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  4842: if_acmpne       4950
        //  4845: aload_0        
        //  4846: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //  4849: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4852: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isPrecededBy:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Z
        //  4855: ifeq            4950
        //  4858: goto            4865
        //  4861: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4864: athrow         
        //  4865: aload_0        
        //  4866: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4869: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CAST_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4872: if_acmpne       4898
        //  4875: goto            4882
        //  4878: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4881: athrow         
        //  4882: aload_0        
        //  4883: aload_0        
        //  4884: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //  4887: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_AFTER_TYPE_CAST:Z
        //  4890: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4893: ireturn        
        //  4894: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4897: athrow         
        //  4898: aload_0        
        //  4899: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4902: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4905: if_acmpne       4924
        //  4908: aload_0        
        //  4909: aload_0        
        //  4910: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  4913: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_AFTER_METHOD_RETURN_TYPE_PARENTHESES:Z
        //  4916: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4919: ireturn        
        //  4920: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4923: athrow         
        //  4924: aload_0        
        //  4925: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  4928: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD_SELECTOR_PART:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4931: if_acmpne       4950
        //  4934: aload_0        
        //  4935: aload_0        
        //  4936: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  4939: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_AFTER_METHOD_PARAMETER_TYPE_PARENTHESES:Z
        //  4942: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  4945: ireturn        
        //  4946: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4949: athrow         
        //  4950: aload_0        
        //  4951: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  4954: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4957: if_acmpne       5015
        //  4960: aload_0        
        //  4961: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  4964: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  4967: if_acmpne       5015
        //  4970: goto            4977
        //  4973: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4976: athrow         
        //  4977: aload_0        
        //  4978: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //  4981: invokeinterface com/intellij/lang/ASTNode.getLastChildNode:()Lcom/intellij/lang/ASTNode;
        //  4986: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isStructTypeDeclaration:(Lcom/intellij/lang/ASTNode;)Z
        //  4989: ifeq            5015
        //  4992: goto            4999
        //  4995: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  4998: athrow         
        //  4999: aload_0        
        //  5000: aload_0        
        //  5001: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  5004: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_AFTER_STRUCTURES_RBRACE:Z
        //  5007: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  5010: ireturn        
        //  5011: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5014: athrow         
        //  5015: aload_0        
        //  5016: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  5019: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5022: if_acmpne       5125
        //  5025: aload_0        
        //  5026: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  5029: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  5032: if_acmpeq       5125
        //  5035: goto            5042
        //  5038: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5041: athrow         
        //  5042: aload_0        
        //  5043: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //  5046: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRefInDeclarator:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //  5049: astore          4
        //  5051: aload           4
        //  5053: ifnull          5097
        //  5056: aload_0        
        //  5057: aload           4
        //  5059: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  5062: if_acmpne       5086
        //  5065: goto            5072
        //  5068: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5071: athrow         
        //  5072: aload_0        
        //  5073: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  5076: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_BEFORE_POINTER_IN_DECLARATION:Z
        //  5079: goto            5093
        //  5082: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5085: athrow         
        //  5086: aload_0        
        //  5087: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  5090: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_BEFORE_REFERENCE_IN_DECLARATION:Z
        //  5093: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  5096: ireturn        
        //  5097: aload_0        
        //  5098: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //  5101: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //  5106: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //  5109: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5112: if_acmpne       5125
        //  5115: aload_0        
        //  5116: iconst_1       
        //  5117: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  5120: ireturn        
        //  5121: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5124: athrow         
        //  5125: aload_0        
        //  5126: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  5129: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.NS_DICTIONARY_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5132: if_acmpne       5194
        //  5135: aload_0        
        //  5136: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  5139: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  5142: if_acmpne       5168
        //  5145: goto            5152
        //  5148: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5151: athrow         
        //  5152: aload_0        
        //  5153: aload_0        
        //  5154: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  5157: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_BEFORE_DICTIONARY_LITERAL_COLON:Z
        //  5160: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  5163: ireturn        
        //  5164: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5167: athrow         
        //  5168: aload_0        
        //  5169: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  5172: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  5175: if_acmpne       5194
        //  5178: aload_0        
        //  5179: aload_0        
        //  5180: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //  5183: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SPACE_AFTER_DICTIONARY_LITERAL_COLON:Z
        //  5186: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  5189: ireturn        
        //  5190: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5193: athrow         
        //  5194: aload_0        
        //  5195: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  5198: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5201: if_acmpne       5248
        //  5204: aload_0        
        //  5205: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //  5208: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON2X:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  5211: if_acmpeq       5238
        //  5214: goto            5221
        //  5217: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5220: athrow         
        //  5221: aload_0        
        //  5222: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  5225: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON2X:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  5228: if_acmpne       5248
        //  5231: goto            5238
        //  5234: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5237: athrow         
        //  5238: aload_0        
        //  5239: iconst_0       
        //  5240: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  5243: ireturn        
        //  5244: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5247: athrow         
        //  5248: aload_0        
        //  5249: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //  5252: ifnull          5365
        //  5255: aload_0        
        //  5256: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //  5259: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //  5264: aload_0        
        //  5265: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //  5268: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //  5273: if_acmpne       5365
        //  5276: goto            5283
        //  5279: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5282: athrow         
        //  5283: aload_0        
        //  5284: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //  5287: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //  5290: iconst_2       
        //  5291: anewarray       Lcom/intellij/psi/tree/IElementType;
        //  5294: dup            
        //  5295: iconst_0       
        //  5296: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5299: aastore        
        //  5300: dup            
        //  5301: iconst_1       
        //  5302: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5305: aastore        
        //  5306: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/TokenSet;[Lcom/intellij/psi/tree/IElementType;)Z
        //  5309: ifne            5355
        //  5312: goto            5319
        //  5315: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5318: athrow         
        //  5319: aload_0        
        //  5320: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //  5323: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //  5326: iconst_2       
        //  5327: anewarray       Lcom/intellij/psi/tree/IElementType;
        //  5330: dup            
        //  5331: iconst_0       
        //  5332: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5335: aastore        
        //  5336: dup            
        //  5337: iconst_1       
        //  5338: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5341: aastore        
        //  5342: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/TokenSet;[Lcom/intellij/psi/tree/IElementType;)Z
        //  5345: ifeq            5365
        //  5348: goto            5355
        //  5351: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5354: athrow         
        //  5355: aload_0        
        //  5356: iconst_1       
        //  5357: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  5360: ireturn        
        //  5361: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5364: athrow         
        //  5365: aload_0        
        //  5366: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //  5369: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isKeyword:(Lcom/intellij/lang/ASTNode;)Z
        //  5372: ifeq            5402
        //  5375: aload_0        
        //  5376: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  5379: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5382: if_acmpne       5402
        //  5385: goto            5392
        //  5388: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5391: athrow         
        //  5392: aload_0        
        //  5393: iconst_1       
        //  5394: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  5397: ireturn        
        //  5398: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5401: athrow         
        //  5402: aload_0        
        //  5403: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  5406: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_EXTERN_BLOCK:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5409: if_acmpne       5422
        //  5412: aload_0        
        //  5413: iconst_1       
        //  5414: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  5417: ireturn        
        //  5418: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5421: athrow         
        //  5422: aload_0        
        //  5423: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  5426: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CLASS_PREDEF_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5429: if_acmpne       5465
        //  5432: aload_0        
        //  5433: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //  5436: aload_0        
        //  5437: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //  5440: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //  5445: if_acmpne       5465
        //  5448: goto            5455
        //  5451: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5454: athrow         
        //  5455: aload_0        
        //  5456: iconst_1       
        //  5457: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  5460: ireturn        
        //  5461: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5464: athrow         
        //  5465: aload_0        
        //  5466: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  5469: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isStructure:(Lcom/intellij/psi/tree/IElementType;)Z
        //  5472: ifne            5495
        //  5475: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CLASSES:Lcom/intellij/psi/tree/TokenSet;
        //  5478: aload_0        
        //  5479: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  5482: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  5485: ifeq            5528
        //  5488: goto            5495
        //  5491: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5494: athrow         
        //  5495: aload_0        
        //  5496: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //  5499: aload_0        
        //  5500: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //  5503: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //  5508: if_acmpne       5528
        //  5511: goto            5518
        //  5514: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5517: athrow         
        //  5518: aload_0        
        //  5519: iconst_1       
        //  5520: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  5523: ireturn        
        //  5524: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5527: athrow         
        //  5528: aload_0        
        //  5529: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  5532: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_BASE_CLAUSE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5535: if_acmpne       5548
        //  5538: aload_0        
        //  5539: iconst_1       
        //  5540: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  5543: ireturn        
        //  5544: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5547: athrow         
        //  5548: aload_0        
        //  5549: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //  5552: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.RETURN_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  5555: if_acmpne       5608
        //  5558: aload_0        
        //  5559: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //  5562: aload_0        
        //  5563: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisNode:Lcom/intellij/lang/ASTNode;
        //  5566: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //  5571: if_acmpne       5608
        //  5574: goto            5581
        //  5577: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5580: athrow         
        //  5581: aload_0        
        //  5582: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //  5585: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  5588: if_acmpeq       5608
        //  5591: goto            5598
        //  5594: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5597: athrow         
        //  5598: aload_0        
        //  5599: iconst_1       
        //  5600: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(Z)Z
        //  5603: ireturn        
        //  5604: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  5607: athrow         
        //  5608: iconst_0       
        //  5609: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      20     23     27     Ljava/lang/IllegalArgumentException;
        //  10     37     40     44     Ljava/lang/IllegalArgumentException;
        //  27     56     56     60     Ljava/lang/IllegalArgumentException;
        //  60     81     84     88     Ljava/lang/IllegalArgumentException;
        //  70     98     101    105    Ljava/lang/IllegalArgumentException;
        //  88     115    118    122    Ljava/lang/IllegalArgumentException;
        //  105    128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    153    156    160    Ljava/lang/IllegalArgumentException;
        //  142    170    173    177    Ljava/lang/IllegalArgumentException;
        //  160    181    181    185    Ljava/lang/IllegalArgumentException;
        //  190    212    212    216    Ljava/lang/IllegalArgumentException;
        //  216    236    239    243    Ljava/lang/IllegalArgumentException;
        //  226    253    256    260    Ljava/lang/IllegalArgumentException;
        //  243    270    273    277    Ljava/lang/IllegalArgumentException;
        //  260    289    289    293    Ljava/lang/IllegalArgumentException;
        //  305    325    328    332    Ljava/lang/IllegalArgumentException;
        //  315    344    344    348    Ljava/lang/IllegalArgumentException;
        //  348    370    370    374    Ljava/lang/IllegalArgumentException;
        //  374    396    396    400    Ljava/lang/IllegalArgumentException;
        //  400    422    422    426    Ljava/lang/IllegalArgumentException;
        //  426    455    455    459    Ljava/lang/IllegalArgumentException;
        //  459    484    484    488    Ljava/lang/IllegalArgumentException;
        //  488    508    511    515    Ljava/lang/IllegalArgumentException;
        //  498    526    529    533    Ljava/lang/IllegalArgumentException;
        //  515    543    546    550    Ljava/lang/IllegalArgumentException;
        //  533    554    554    558    Ljava/lang/IllegalArgumentException;
        //  563    583    586    590    Ljava/lang/IllegalArgumentException;
        //  573    603    606    610    Ljava/lang/IllegalArgumentException;
        //  590    623    626    630    Ljava/lang/IllegalArgumentException;
        //  610    642    642    646    Ljava/lang/IllegalArgumentException;
        //  646    666    669    673    Ljava/lang/IllegalArgumentException;
        //  656    683    686    690    Ljava/lang/IllegalArgumentException;
        //  673    702    702    706    Ljava/lang/IllegalArgumentException;
        //  706    726    729    733    Ljava/lang/IllegalArgumentException;
        //  716    745    745    749    Ljava/lang/IllegalArgumentException;
        //  749    807    810    814    Ljava/lang/IllegalArgumentException;
        //  778    826    826    830    Ljava/lang/IllegalArgumentException;
        //  830    850    853    857    Ljava/lang/IllegalArgumentException;
        //  840    863    863    867    Ljava/lang/IllegalArgumentException;
        //  880    889    889    893    Ljava/lang/IllegalArgumentException;
        //  906    915    915    919    Ljava/lang/IllegalArgumentException;
        //  919    939    942    946    Ljava/lang/IllegalArgumentException;
        //  929    956    959    963    Ljava/lang/IllegalArgumentException;
        //  946    973    976    980    Ljava/lang/IllegalArgumentException;
        //  963    990    993    997    Ljava/lang/IllegalArgumentException;
        //  980    1007   1010   1014   Ljava/lang/IllegalArgumentException;
        //  1033   1041   1044   1048   Ljava/lang/IllegalArgumentException;
        //  1037   1053   1056   1060   Ljava/lang/IllegalArgumentException;
        //  1048   1066   1066   1070   Ljava/lang/IllegalArgumentException;
        //  1070   1085   1088   1092   Ljava/lang/IllegalArgumentException;
        //  1074   1102   1102   1106   Ljava/lang/IllegalArgumentException;
        //  1117   1138   1138   1142   Ljava/lang/IllegalArgumentException;
        //  1172   1200   1203   1207   Ljava/lang/IllegalArgumentException;
        //  1182   1211   1211   1215   Ljava/lang/IllegalArgumentException;
        //  1219   1227   1230   1234   Ljava/lang/IllegalArgumentException;
        //  1223   1239   1242   1246   Ljava/lang/IllegalArgumentException;
        //  1234   1252   1252   1256   Ljava/lang/IllegalArgumentException;
        //  1256   1272   1272   1276   Ljava/lang/IllegalArgumentException;
        //  1276   1296   1299   1303   Ljava/lang/IllegalArgumentException;
        //  1286   1313   1316   1320   Ljava/lang/IllegalArgumentException;
        //  1303   1330   1333   1337   Ljava/lang/IllegalArgumentException;
        //  1320   1344   1344   1348   Ljava/lang/IllegalArgumentException;
        //  1353   1370   1373   1377   Ljava/lang/IllegalArgumentException;
        //  1363   1384   1387   1391   Ljava/lang/IllegalArgumentException;
        //  1377   1403   1403   1407   Ljava/lang/IllegalArgumentException;
        //  1407   1427   1430   1434   Ljava/lang/IllegalArgumentException;
        //  1417   1441   1444   1448   Ljava/lang/IllegalArgumentException;
        //  1434   1466   1469   1473   Ljava/lang/IllegalArgumentException;
        //  1448   1491   1494   1498   Ljava/lang/IllegalArgumentException;
        //  1473   1504   1504   1508   Ljava/lang/IllegalArgumentException;
        //  1532   1541   1541   1545   Ljava/lang/IllegalArgumentException;
        //  1545   1565   1568   1572   Ljava/lang/IllegalArgumentException;
        //  1555   1584   1584   1588   Ljava/lang/IllegalArgumentException;
        //  1588   1608   1611   1615   Ljava/lang/IllegalArgumentException;
        //  1598   1627   1627   1631   Ljava/lang/IllegalArgumentException;
        //  1631   1653   1653   1657   Ljava/lang/IllegalArgumentException;
        //  1657   1679   1679   1683   Ljava/lang/IllegalArgumentException;
        //  1683   1706   1709   1713   Ljava/lang/IllegalArgumentException;
        //  1693   1719   1719   1723   Ljava/lang/IllegalArgumentException;
        //  1723   1740   1743   1747   Ljava/lang/IllegalArgumentException;
        //  1733   1754   1757   1761   Ljava/lang/IllegalArgumentException;
        //  1747   1771   1774   1778   Ljava/lang/IllegalArgumentException;
        //  1761   1788   1791   1795   Ljava/lang/IllegalArgumentException;
        //  1778   1799   1799   1803   Ljava/lang/IllegalArgumentException;
        //  1806   1821   1821   1825   Ljava/lang/IllegalArgumentException;
        //  1834   1855   1858   1862   Ljava/lang/IllegalArgumentException;
        //  1845   1872   1875   1879   Ljava/lang/IllegalArgumentException;
        //  1862   1889   1892   1896   Ljava/lang/IllegalArgumentException;
        //  1879   1906   1906   1910   Ljava/lang/IllegalArgumentException;
        //  1923   1940   1943   1947   Ljava/lang/IllegalArgumentException;
        //  1930   1959   1959   1963   Ljava/lang/IllegalArgumentException;
        //  1963   1985   1985   1989   Ljava/lang/IllegalArgumentException;
        //  1989   2009   2012   2016   Ljava/lang/IllegalArgumentException;
        //  1999   2026   2026   2030   Ljava/lang/IllegalArgumentException;
        //  2039   2059   2062   2066   Ljava/lang/IllegalArgumentException;
        //  2049   2076   2076   2080   Ljava/lang/IllegalArgumentException;
        //  2104   2120   2120   2124   Ljava/lang/IllegalArgumentException;
        //  2124   2142   2145   2149   Ljava/lang/IllegalArgumentException;
        //  2131   2159   2162   2166   Ljava/lang/IllegalArgumentException;
        //  2149   2176   2176   2180   Ljava/lang/IllegalArgumentException;
        //  2191   2211   2214   2218   Ljava/lang/IllegalArgumentException;
        //  2201   2228   2231   2235   Ljava/lang/IllegalArgumentException;
        //  2218   2247   2247   2251   Ljava/lang/IllegalArgumentException;
        //  2251   2271   2274   2278   Ljava/lang/IllegalArgumentException;
        //  2261   2291   2294   2298   Ljava/lang/IllegalArgumentException;
        //  2278   2309   2312   2316   Ljava/lang/IllegalArgumentException;
        //  2298   2326   2329   2333   Ljava/lang/IllegalArgumentException;
        //  2316   2343   2343   2347   Ljava/lang/IllegalArgumentException;
        //  2358   2378   2381   2385   Ljava/lang/IllegalArgumentException;
        //  2368   2395   2395   2399   Ljava/lang/IllegalArgumentException;
        //  2422   2442   2445   2449   Ljava/lang/IllegalArgumentException;
        //  2432   2459   2462   2466   Ljava/lang/IllegalArgumentException;
        //  2449   2476   2479   2483   Ljava/lang/IllegalArgumentException;
        //  2466   2493   2496   2500   Ljava/lang/IllegalArgumentException;
        //  2483   2503   2503   2507   Ljava/lang/IllegalArgumentException;
        //  2507   2521   2521   2525   Ljava/lang/IllegalArgumentException;
        //  2528   2548   2551   2555   Ljava/lang/IllegalArgumentException;
        //  2538   2565   2565   2569   Ljava/lang/IllegalArgumentException;
        //  2587   2607   2610   2614   Ljava/lang/IllegalArgumentException;
        //  2597   2624   2627   2631   Ljava/lang/IllegalArgumentException;
        //  2614   2643   2643   2647   Ljava/lang/IllegalArgumentException;
        //  2647   2667   2670   2674   Ljava/lang/IllegalArgumentException;
        //  2657   2684   2687   2691   Ljava/lang/IllegalArgumentException;
        //  2674   2694   2694   2698   Ljava/lang/IllegalArgumentException;
        //  2698   2712   2712   2716   Ljava/lang/IllegalArgumentException;
        //  2719   2739   2742   2746   Ljava/lang/IllegalArgumentException;
        //  2729   2756   2756   2760   Ljava/lang/IllegalArgumentException;
        //  2778   2800   2800   2804   Ljava/lang/IllegalArgumentException;
        //  2804   2824   2827   2831   Ljava/lang/IllegalArgumentException;
        //  2814   2841   2841   2845   Ljava/lang/IllegalArgumentException;
        //  2868   2888   2891   2895   Ljava/lang/IllegalArgumentException;
        //  2878   2907   2907   2911   Ljava/lang/IllegalArgumentException;
        //  2911   2933   2933   2937   Ljava/lang/IllegalArgumentException;
        //  2937   2959   2959   2963   Ljava/lang/IllegalArgumentException;
        //  2963   2985   2985   2989   Ljava/lang/IllegalArgumentException;
        //  2989   3009   3012   3016   Ljava/lang/IllegalArgumentException;
        //  2999   3026   3029   3033   Ljava/lang/IllegalArgumentException;
        //  3016   3043   3046   3050   Ljava/lang/IllegalArgumentException;
        //  3033   3060   3063   3067   Ljava/lang/IllegalArgumentException;
        //  3050   3079   3079   3083   Ljava/lang/IllegalArgumentException;
        //  3083   3105   3105   3109   Ljava/lang/IllegalArgumentException;
        //  3109   3131   3131   3135   Ljava/lang/IllegalArgumentException;
        //  3135   3157   3157   3161   Ljava/lang/IllegalArgumentException;
        //  3161   3177   3177   3181   Ljava/lang/IllegalArgumentException;
        //  3181   3203   3203   3207   Ljava/lang/IllegalArgumentException;
        //  3207   3227   3230   3234   Ljava/lang/IllegalArgumentException;
        //  3217   3244   3247   3251   Ljava/lang/IllegalArgumentException;
        //  3234   3263   3263   3267   Ljava/lang/IllegalArgumentException;
        //  3267   3287   3290   3294   Ljava/lang/IllegalArgumentException;
        //  3277   3304   3307   3311   Ljava/lang/IllegalArgumentException;
        //  3294   3323   3323   3327   Ljava/lang/IllegalArgumentException;
        //  3327   3347   3350   3354   Ljava/lang/IllegalArgumentException;
        //  3337   3364   3364   3368   Ljava/lang/IllegalArgumentException;
        //  3377   3397   3400   3404   Ljava/lang/IllegalArgumentException;
        //  3387   3414   3414   3418   Ljava/lang/IllegalArgumentException;
        //  3418   3438   3438   3442   Ljava/lang/IllegalArgumentException;
        //  3442   3468   3471   3475   Ljava/lang/IllegalArgumentException;
        //  3458   3485   3488   3492   Ljava/lang/IllegalArgumentException;
        //  3475   3496   3496   3500   Ljava/lang/IllegalArgumentException;
        //  3522   3542   3545   3549   Ljava/lang/IllegalArgumentException;
        //  3532   3559   3559   3563   Ljava/lang/IllegalArgumentException;
        //  3572   3592   3595   3599   Ljava/lang/IllegalArgumentException;
        //  3582   3609   3609   3613   Ljava/lang/IllegalArgumentException;
        //  3613   3633   3633   3637   Ljava/lang/IllegalArgumentException;
        //  3637   3663   3666   3670   Ljava/lang/IllegalArgumentException;
        //  3653   3680   3683   3687   Ljava/lang/IllegalArgumentException;
        //  3670   3691   3691   3695   Ljava/lang/IllegalArgumentException;
        //  3717   3737   3740   3744   Ljava/lang/IllegalArgumentException;
        //  3727   3754   3757   3761   Ljava/lang/IllegalArgumentException;
        //  3744   3773   3773   3777   Ljava/lang/IllegalArgumentException;
        //  3777   3799   3799   3803   Ljava/lang/IllegalArgumentException;
        //  3803   3825   3825   3829   Ljava/lang/IllegalArgumentException;
        //  3829   3851   3851   3855   Ljava/lang/IllegalArgumentException;
        //  3855   3877   3877   3881   Ljava/lang/IllegalArgumentException;
        //  3881   3901   3904   3908   Ljava/lang/IllegalArgumentException;
        //  3891   3920   3920   3924   Ljava/lang/IllegalArgumentException;
        //  3924   3944   3947   3951   Ljava/lang/IllegalArgumentException;
        //  3934   3961   3964   3968   Ljava/lang/IllegalArgumentException;
        //  3951   3980   3980   3984   Ljava/lang/IllegalArgumentException;
        //  3984   4004   4007   4011   Ljava/lang/IllegalArgumentException;
        //  3994   4021   4024   4028   Ljava/lang/IllegalArgumentException;
        //  4011   4040   4040   4044   Ljava/lang/IllegalArgumentException;
        //  4044   4064   4067   4071   Ljava/lang/IllegalArgumentException;
        //  4054   4083   4083   4087   Ljava/lang/IllegalArgumentException;
        //  4087   4107   4110   4114   Ljava/lang/IllegalArgumentException;
        //  4097   4124   4127   4131   Ljava/lang/IllegalArgumentException;
        //  4114   4141   4144   4148   Ljava/lang/IllegalArgumentException;
        //  4131   4160   4160   4164   Ljava/lang/IllegalArgumentException;
        //  4164   4186   4186   4190   Ljava/lang/IllegalArgumentException;
        //  4190   4212   4212   4216   Ljava/lang/IllegalArgumentException;
        //  4216   4236   4239   4243   Ljava/lang/IllegalArgumentException;
        //  4226   4253   4256   4260   Ljava/lang/IllegalArgumentException;
        //  4243   4271   4274   4278   Ljava/lang/IllegalArgumentException;
        //  4260   4284   4284   4288   Ljava/lang/IllegalArgumentException;
        //  4288   4309   4309   4313   Ljava/lang/IllegalArgumentException;
        //  4324   4347   4350   4354   Ljava/lang/IllegalArgumentException;
        //  4337   4366   4366   4370   Ljava/lang/IllegalArgumentException;
        //  4370   4390   4393   4397   Ljava/lang/IllegalArgumentException;
        //  4380   4408   4411   4415   Ljava/lang/IllegalArgumentException;
        //  4397   4425   4428   4432   Ljava/lang/IllegalArgumentException;
        //  4415   4436   4436   4440   Ljava/lang/IllegalArgumentException;
        //  4445   4465   4468   4472   Ljava/lang/IllegalArgumentException;
        //  4455   4482   4485   4489   Ljava/lang/IllegalArgumentException;
        //  4472   4501   4501   4505   Ljava/lang/IllegalArgumentException;
        //  4505   4525   4528   4532   Ljava/lang/IllegalArgumentException;
        //  4515   4542   4545   4549   Ljava/lang/IllegalArgumentException;
        //  4532   4555   4555   4559   Ljava/lang/IllegalArgumentException;
        //  4559   4579   4582   4586   Ljava/lang/IllegalArgumentException;
        //  4569   4592   4592   4596   Ljava/lang/IllegalArgumentException;
        //  4596   4616   4619   4623   Ljava/lang/IllegalArgumentException;
        //  4606   4636   4639   4643   Ljava/lang/IllegalArgumentException;
        //  4623   4653   4656   4660   Ljava/lang/IllegalArgumentException;
        //  4643   4670   4673   4677   Ljava/lang/IllegalArgumentException;
        //  4660   4687   4690   4694   Ljava/lang/IllegalArgumentException;
        //  4677   4707   4710   4714   Ljava/lang/IllegalArgumentException;
        //  4694   4724   4727   4731   Ljava/lang/IllegalArgumentException;
        //  4714   4741   4744   4748   Ljava/lang/IllegalArgumentException;
        //  4731   4758   4761   4765   Ljava/lang/IllegalArgumentException;
        //  4748   4771   4771   4775   Ljava/lang/IllegalArgumentException;
        //  4775   4795   4798   4802   Ljava/lang/IllegalArgumentException;
        //  4785   4812   4815   4819   Ljava/lang/IllegalArgumentException;
        //  4802   4831   4831   4835   Ljava/lang/IllegalArgumentException;
        //  4835   4858   4861   4865   Ljava/lang/IllegalArgumentException;
        //  4845   4875   4878   4882   Ljava/lang/IllegalArgumentException;
        //  4865   4894   4894   4898   Ljava/lang/IllegalArgumentException;
        //  4898   4920   4920   4924   Ljava/lang/IllegalArgumentException;
        //  4924   4946   4946   4950   Ljava/lang/IllegalArgumentException;
        //  4950   4970   4973   4977   Ljava/lang/IllegalArgumentException;
        //  4960   4992   4995   4999   Ljava/lang/IllegalArgumentException;
        //  4977   5011   5011   5015   Ljava/lang/IllegalArgumentException;
        //  5015   5035   5038   5042   Ljava/lang/IllegalArgumentException;
        //  5051   5065   5068   5072   Ljava/lang/IllegalArgumentException;
        //  5056   5082   5082   5086   Ljava/lang/IllegalArgumentException;
        //  5097   5121   5121   5125   Ljava/lang/IllegalArgumentException;
        //  5125   5145   5148   5152   Ljava/lang/IllegalArgumentException;
        //  5135   5164   5164   5168   Ljava/lang/IllegalArgumentException;
        //  5168   5190   5190   5194   Ljava/lang/IllegalArgumentException;
        //  5194   5214   5217   5221   Ljava/lang/IllegalArgumentException;
        //  5204   5231   5234   5238   Ljava/lang/IllegalArgumentException;
        //  5221   5244   5244   5248   Ljava/lang/IllegalArgumentException;
        //  5248   5276   5279   5283   Ljava/lang/IllegalArgumentException;
        //  5255   5312   5315   5319   Ljava/lang/IllegalArgumentException;
        //  5283   5348   5351   5355   Ljava/lang/IllegalArgumentException;
        //  5319   5361   5361   5365   Ljava/lang/IllegalArgumentException;
        //  5365   5385   5388   5392   Ljava/lang/IllegalArgumentException;
        //  5375   5398   5398   5402   Ljava/lang/IllegalArgumentException;
        //  5402   5418   5418   5422   Ljava/lang/IllegalArgumentException;
        //  5422   5448   5451   5455   Ljava/lang/IllegalArgumentException;
        //  5432   5461   5461   5465   Ljava/lang/IllegalArgumentException;
        //  5465   5488   5491   5495   Ljava/lang/IllegalArgumentException;
        //  5475   5511   5514   5518   Ljava/lang/IllegalArgumentException;
        //  5495   5524   5524   5528   Ljava/lang/IllegalArgumentException;
        //  5528   5544   5544   5548   Ljava/lang/IllegalArgumentException;
        //  5548   5574   5577   5581   Ljava/lang/IllegalArgumentException;
        //  5558   5591   5594   5598   Ljava/lang/IllegalArgumentException;
        //  5581   5604   5604   5608   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
    
    @Contract("null, _, _ -> false")
    private static boolean a(@Nullable final ASTNode astNode, @NotNull final IElementType elementType, final IElementType... array) {
        try {
            if (elementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "childType", "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor", "checkContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0071: {
            try {
                if (astNode == null) {
                    return false;
                }
                final ASTNode astNode2 = astNode;
                final IElementType elementType2 = astNode2.getElementType();
                final IElementType elementType3 = elementType;
                if (elementType2 != elementType3) {
                    return false;
                }
                break Label_0071;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final ASTNode astNode2 = astNode;
                final IElementType elementType2 = astNode2.getElementType();
                final IElementType elementType3 = elementType;
                if (elementType2 != elementType3) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        final IElementType elementType4 = OCElementUtil.getElementType(astNode.getTreeParent());
        for (final IElementType elementType5 : array) {
            try {
                if (elementType5 == elementType4) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return false;
    }
    
    @Contract("null, _, _ -> false")
    private static boolean a(@Nullable final ASTNode astNode, @NotNull final TokenSet set, final IElementType... array) {
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "childTypes", "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor", "checkContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0074: {
            try {
                if (astNode == null) {
                    return false;
                }
                final TokenSet set2 = set;
                final ASTNode astNode2 = astNode;
                final IElementType elementType = astNode2.getElementType();
                final boolean b = set2.contains(elementType);
                if (!b) {
                    return false;
                }
                break Label_0074;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final TokenSet set2 = set;
                final ASTNode astNode2 = astNode;
                final IElementType elementType = astNode2.getElementType();
                final boolean b = set2.contains(elementType);
                if (!b) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        final IElementType elementType2 = OCElementUtil.getElementType(astNode.getTreeParent());
        for (final IElementType elementType3 : array) {
            try {
                if (elementType3 == elementType2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return false;
    }
    
    private boolean e() {
        return this.b(0);
    }
    
    private boolean c() {
        return this.a(0);
    }
    
    private boolean b(final boolean b) {
        try {
            if (b) {
                this.hasSpace = true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.minLineFeeds = 0;
        this.keepLineBreaks = false;
        return true;
    }
    
    private boolean h() {
        this.b(true);
        this.keepBlankLines = -1;
        return true;
    }
    
    private boolean a(final int n, final ASTNode astNode, final ASTNode astNode2) {
        Label_0090: {
            try {
                this.keepBlankLines = -1;
                this.keepLineBreaks = false;
                switch (n) {
                    case 1: {
                        this.minLineFeeds = 0;
                        return true;
                    }
                    case 5: {
                        break;
                    }
                    case 2:
                    case 3:
                    case 4: {
                        break Label_0090;
                    }
                    default: {
                        return false;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            this.dependentRange = new TextRange(astNode.getStartOffset(), astNode2.getTextRange().getEndOffset());
            this.isDependent = true;
        }
        this.minLineFeeds = 1;
        return true;
    }
    
    private boolean b(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor", "wrapStatement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final ASTNode previousNonWhitespaceSibling = FormatterUtil.getPreviousNonWhitespaceSibling(astNode);
        try {
            if (OCElementUtil.getElementType(previousNonWhitespaceSibling) == OCTokenTypes.ELSE_KEYWORD) {
                final ASTNode thisNode = previousNonWhitespaceSibling;
                return this.a(astNode, thisNode, astNode, this.settings.KEEP_CONTROL_STATEMENT_IN_ONE_LINE);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final ASTNode thisNode = this.thisNode;
        return this.a(astNode, thisNode, astNode, this.settings.KEEP_CONTROL_STATEMENT_IN_ONE_LINE);
    }
    
    private boolean a(@NotNull final ASTNode astNode, @Nullable ASTNode astNode2, @Nullable ASTNode astNode3, final boolean b) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor", "wrapStatement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final boolean block = OCFormatterUtil.isBlock(astNode.getElementType());
        int minLineFeeds = 0;
        Label_0142: {
            Label_0122: {
                Label_0090: {
                    Label_0072: {
                        try {
                            if (block) {
                                break Label_0122;
                            }
                            final boolean b2 = b;
                            if (b2) {
                                break Label_0072;
                            }
                            break Label_0122;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        try {
                            final boolean b2 = b;
                            if (!b2) {
                                break Label_0122;
                            }
                            this.isDependent = true;
                            if (astNode2 != null) {
                                break Label_0090;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                    }
                    astNode2 = astNode;
                }
                if (astNode3 == null) {
                    astNode3 = astNode;
                }
                this.dependentRange = new TextRange(astNode2.getStartOffset(), astNode3.getTextRange().getEndOffset());
                try {
                    this.keepLineBreaks = false;
                    if (block) {
                        minLineFeeds = 0;
                        break Label_0142;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            minLineFeeds = 1;
        }
        this.minLineFeeds = minLineFeeds;
        return true;
    }
    
    private boolean b(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.g:()Z
        //     4: ifeq            30
        //     7: aload_0        
        //     8: dup            
        //     9: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isDependent:Z
        //    12: aload_0        
        //    13: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //    16: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.KEEP_SIMPLE_METHODS_IN_ONE_LINE:Z
        //    19: ior            
        //    20: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isDependent:Z
        //    23: goto            184
        //    26: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    29: athrow         
        //    30: aload_0        
        //    31: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //    34: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isControlStatementOrSection:(Lcom/intellij/psi/tree/IElementType;)Z
        //    37: ifne            71
        //    40: aload_0        
        //    41: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:()Z
        //    44: ifeq            121
        //    47: goto            54
        //    50: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    53: athrow         
        //    54: aload_0        
        //    55: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.parentType:Lcom/intellij/psi/tree/IElementType;
        //    58: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isControlStatementOrSection:(Lcom/intellij/psi/tree/IElementType;)Z
        //    61: ifeq            121
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: aload_0        
        //    72: dup            
        //    73: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isDependent:Z
        //    76: aload_0        
        //    77: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.parentType:Lcom/intellij/psi/tree/IElementType;
        //    80: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CASE_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    83: if_acmpne       107
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: aload_0        
        //    94: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //    97: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.KEEP_CASE_EXPRESSIONS_IN_ONE_LINE:Z
        //   100: goto            114
        //   103: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: aload_0        
        //   108: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   111: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.KEEP_CONTROL_STATEMENT_IN_ONE_LINE:Z
        //   114: ior            
        //   115: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isDependent:Z
        //   118: goto            184
        //   121: aload_0        
        //   122: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.thisType:Lcom/intellij/psi/tree/IElementType;
        //   125: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isStructure:(Lcom/intellij/psi/tree/IElementType;)Z
        //   128: ifeq            154
        //   131: aload_0        
        //   132: dup            
        //   133: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isDependent:Z
        //   136: aload_0        
        //   137: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   140: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.KEEP_STRUCTURES_IN_ONE_LINE:Z
        //   143: ior            
        //   144: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isDependent:Z
        //   147: goto            184
        //   150: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload_0        
        //   155: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:()Z
        //   158: ifeq            184
        //   161: aload_0        
        //   162: dup            
        //   163: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isDependent:Z
        //   166: aload_0        
        //   167: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   170: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.KEEP_SIMPLE_BLOCKS_IN_ONE_LINE:Z
        //   173: ior            
        //   174: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isDependent:Z
        //   177: goto            184
        //   180: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   183: athrow         
        //   184: aload_0        
        //   185: dup            
        //   186: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isDependent:Z
        //   189: aload_0        
        //   190: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.i:()Z
        //   193: ifne            204
        //   196: iconst_1       
        //   197: goto            205
        //   200: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   203: athrow         
        //   204: iconst_0       
        //   205: iand           
        //   206: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isDependent:Z
        //   209: aload_0        
        //   210: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   213: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //   216: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   219: if_acmpne       311
        //   222: aload_0        
        //   223: invokevirtual   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.getSettings:()Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   226: aload_0        
        //   227: invokevirtual   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.getOCSettings:()Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   230: aload_0        
        //   231: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   234: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isNestedInlineBlock:(Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;Lcom/intellij/lang/ASTNode;)Z
        //   237: ifeq            311
        //   240: goto            247
        //   243: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   246: athrow         
        //   247: aload_0        
        //   248: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   251: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   254: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   259: astore_2       
        //   260: aload_2        
        //   261: ifnull          308
        //   264: aload_0        
        //   265: iconst_1       
        //   266: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isDependent:Z
        //   269: aload_0        
        //   270: new             Lcom/intellij/openapi/util/TextRange;
        //   273: dup            
        //   274: aload_0        
        //   275: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   278: invokeinterface com/intellij/lang/ASTNode.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   283: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   286: aload_2        
        //   287: invokeinterface com/intellij/lang/ASTNode.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   292: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   295: invokespecial   com/intellij/openapi/util/TextRange.<init>:(II)V
        //   298: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.dependentRange:Lcom/intellij/openapi/util/TextRange;
        //   301: goto            308
        //   304: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   307: athrow         
        //   308: goto            482
        //   311: aload_0        
        //   312: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   315: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //   318: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   321: if_acmpne       482
        //   324: aload_0        
        //   325: invokevirtual   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.getSettings:()Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   328: aload_0        
        //   329: invokevirtual   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.getOCSettings:()Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   332: aload_0        
        //   333: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   336: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isNestedInlineBlock:(Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;Lcom/intellij/lang/ASTNode;)Z
        //   339: ifeq            482
        //   342: goto            349
        //   345: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   348: athrow         
        //   349: aload_0        
        //   350: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   353: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   356: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.findChildBackward:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   359: astore_2       
        //   360: aload_0        
        //   361: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   364: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   367: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   372: astore_3       
        //   373: aload_0        
        //   374: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //   377: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   382: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   385: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   390: astore          4
        //   392: aload_2        
        //   393: ifnull          482
        //   396: aload_3        
        //   397: ifnull          482
        //   400: goto            407
        //   403: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   406: athrow         
        //   407: aload           4
        //   409: ifnull          482
        //   412: goto            419
        //   415: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   418: athrow         
        //   419: aload_0        
        //   420: iconst_0       
        //   421: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   424: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.hasSpace:Ljava/lang/Boolean;
        //   427: aload_0        
        //   428: iconst_1       
        //   429: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isDependent:Z
        //   432: aload_0        
        //   433: iconst_0       
        //   434: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.keepLineBreaks:Z
        //   437: aload_0        
        //   438: iconst_0       
        //   439: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   442: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.keepBlankLines:Ljava/lang/Integer;
        //   445: aload_0        
        //   446: new             Lcom/intellij/openapi/util/TextRange;
        //   449: dup            
        //   450: aload           4
        //   452: invokeinterface com/intellij/lang/ASTNode.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   457: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   460: aload_3        
        //   461: invokeinterface com/intellij/lang/ASTNode.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   466: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   469: invokespecial   com/intellij/openapi/util/TextRange.<init>:(II)V
        //   472: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.dependentRange:Lcom/intellij/openapi/util/TextRange;
        //   475: goto            482
        //   478: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   481: athrow         
        //   482: aload_0        
        //   483: iload_1        
        //   484: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.a:(I)Z
        //   487: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      26     26     30     Ljava/lang/IllegalArgumentException;
        //  30     47     50     54     Ljava/lang/IllegalArgumentException;
        //  40     64     67     71     Ljava/lang/IllegalArgumentException;
        //  54     86     89     93     Ljava/lang/IllegalArgumentException;
        //  71     103    103    107    Ljava/lang/IllegalArgumentException;
        //  121    150    150    154    Ljava/lang/IllegalArgumentException;
        //  154    177    180    184    Ljava/lang/IllegalArgumentException;
        //  184    200    200    204    Ljava/lang/IllegalArgumentException;
        //  205    240    243    247    Ljava/lang/IllegalArgumentException;
        //  260    301    304    308    Ljava/lang/IllegalArgumentException;
        //  311    342    345    349    Ljava/lang/IllegalArgumentException;
        //  392    400    403    407    Ljava/lang/IllegalArgumentException;
        //  396    412    415    419    Ljava/lang/IllegalArgumentException;
        //  407    475    478    482    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0054:
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
    
    private boolean a(final int n) {
        this.minLineFeeds = Math.max(this.minLineFeeds, n + 1);
        return true;
    }
    
    private void d() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.keepBlankLines:Ljava/lang/Integer;
        //     4: ifnull          12
        //     7: return         
        //     8: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    11: athrow         
        //    12: aload_0        
        //    13: aload_0        
        //    14: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //    17: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.KEEP_BLANK_LINES_IN_CODE:I
        //    20: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    23: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.keepBlankLines:Ljava/lang/Integer;
        //    26: aload_0        
        //    27: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //    30: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isDeclarationOrDefinition:(Lcom/intellij/psi/tree/IElementType;)Z
        //    33: ifne            53
        //    36: aload_0        
        //    37: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeLeft:Lcom/intellij/psi/tree/IElementType;
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isNamespace:(Lcom/intellij/psi/tree/IElementType;)Z
        //    43: ifeq            108
        //    46: goto            53
        //    49: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    52: athrow         
        //    53: aload_0        
        //    54: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //    57: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isDeclarationOrDefinition:(Lcom/intellij/psi/tree/IElementType;)Z
        //    60: ifne            87
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload_0        
        //    71: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //    74: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.isNamespace:(Lcom/intellij/psi/tree/IElementType;)Z
        //    77: ifeq            108
        //    80: goto            87
        //    83: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    86: athrow         
        //    87: aload_0        
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //    92: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.KEEP_BLANK_LINES_IN_DECLARATIONS:I
        //    95: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    98: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.keepBlankLines:Ljava/lang/Integer;
        //   101: goto            216
        //   104: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: aload_0        
        //   109: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   112: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   115: if_acmpne       139
        //   118: aload_0        
        //   119: aload_0        
        //   120: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.settings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   123: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.KEEP_BLANK_LINES_BEFORE_RBRACE:I
        //   126: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   129: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.keepBlankLines:Ljava/lang/Integer;
        //   132: goto            216
        //   135: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: aload_0        
        //   140: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   143: aload_0        
        //   144: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //   147: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.END_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   150: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isObjCKeywordWithDog:(Lcom/intellij/psi/tree/IElementType;Lcom/intellij/lang/ASTNode;Lcom/jetbrains/cidr/lang/parser/OCElementType;)Z
        //   153: ifeq            177
        //   156: aload_0        
        //   157: aload_0        
        //   158: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.ocSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   161: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.KEEP_BLANK_LINES_BEFORE_END:I
        //   164: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   167: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.keepBlankLines:Ljava/lang/Integer;
        //   170: goto            216
        //   173: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: aload_0        
        //   178: invokespecial   com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.l:()Z
        //   181: ifne            201
        //   184: aload_0        
        //   185: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.typeRight:Lcom/intellij/psi/tree/IElementType;
        //   188: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   191: if_acmpne       216
        //   194: goto            201
        //   197: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   200: athrow         
        //   201: aload_0        
        //   202: iconst_0       
        //   203: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   206: putfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.keepBlankLines:Ljava/lang/Integer;
        //   209: goto            216
        //   212: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      8      8      12     Ljava/lang/IllegalArgumentException;
        //  12     46     49     53     Ljava/lang/IllegalArgumentException;
        //  36     63     66     70     Ljava/lang/IllegalArgumentException;
        //  53     80     83     87     Ljava/lang/IllegalArgumentException;
        //  70     104    104    108    Ljava/lang/IllegalArgumentException;
        //  108    135    135    139    Ljava/lang/IllegalArgumentException;
        //  139    173    173    177    Ljava/lang/IllegalArgumentException;
        //  177    194    197    201    Ljava/lang/IllegalArgumentException;
        //  184    209    212    216    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0053:
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
    
    private boolean a(final boolean b) {
        this.hasSpace = b;
        return true;
    }
    
    private boolean c(boolean o) {
        if (!o) {
            o = this.o();
        }
        this.hasSpace = o;
        return true;
    }
    
    private boolean o() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeLeft:Lcom/intellij/lang/ASTNode;
        //     4: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.findLastLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //     7: astore_1       
        //     8: aload_0        
        //     9: getfield        com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.nodeRight:Lcom/intellij/lang/ASTNode;
        //    12: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.findFirstLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/impl/source/tree/LeafElement;
        //    15: astore_2       
        //    16: aload_1        
        //    17: ifnull          31
        //    20: aload_2        
        //    21: ifnonnull       37
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    30: athrow         
        //    31: iconst_0       
        //    32: ireturn        
        //    33: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    36: athrow         
        //    37: aload_1        
        //    38: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //    43: astore_3       
        //    44: aload_2        
        //    45: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //    50: astore          4
        //    52: aload_3        
        //    53: invokevirtual   java/lang/String.length:()I
        //    56: ifeq            82
        //    59: aload_3        
        //    60: aload_3        
        //    61: invokevirtual   java/lang/String.length:()I
        //    64: iconst_1       
        //    65: isub           
        //    66: invokevirtual   java/lang/String.charAt:(I)C
        //    69: invokestatic    java/lang/Character.isJavaIdentifierPart:(C)Z
        //    72: ifeq            122
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    81: athrow         
        //    82: aload           4
        //    84: invokevirtual   java/lang/String.length:()I
        //    87: ifeq            116
        //    90: goto            97
        //    93: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    96: athrow         
        //    97: aload           4
        //    99: iconst_0       
        //   100: invokevirtual   java/lang/String.charAt:(I)C
        //   103: invokestatic    java/lang/Character.isJavaIdentifierPart:(C)Z
        //   106: ifeq            122
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: iconst_1       
        //   117: ireturn        
        //   118: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: aload_1        
        //   123: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   128: astore          5
        //   130: aload_2        
        //   131: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   136: astore          6
        //   138: getstatic       com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.MANDATORY_SPACE_PAIR:[Ljava/util/ArrayList;
        //   141: astore          7
        //   143: aload           7
        //   145: arraylength    
        //   146: istore          8
        //   148: iconst_0       
        //   149: istore          9
        //   151: iload           9
        //   153: iload           8
        //   155: if_icmpge       204
        //   158: aload           7
        //   160: iload           9
        //   162: aaload         
        //   163: astore          10
        //   165: aload           10
        //   167: aload           5
        //   169: invokevirtual   java/util/ArrayList.contains:(Ljava/lang/Object;)Z
        //   172: ifeq            198
        //   175: aload           10
        //   177: aload           6
        //   179: invokevirtual   java/util/ArrayList.contains:(Ljava/lang/Object;)Z
        //   182: ifeq            198
        //   185: goto            192
        //   188: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   191: athrow         
        //   192: iconst_1       
        //   193: ireturn        
        //   194: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   197: athrow         
        //   198: iinc            9, 1
        //   201: goto            151
        //   204: iconst_0       
        //   205: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  16     24     27     31     Ljava/lang/IllegalArgumentException;
        //  20     33     33     37     Ljava/lang/IllegalArgumentException;
        //  52     75     78     82     Ljava/lang/IllegalArgumentException;
        //  59     90     93     97     Ljava/lang/IllegalArgumentException;
        //  82     109    112    116    Ljava/lang/IllegalArgumentException;
        //  97     118    118    122    Ljava/lang/IllegalArgumentException;
        //  165    185    188    192    Ljava/lang/IllegalArgumentException;
        //  175    194    194    198    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0082:
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
    
    private boolean a(final boolean b, final boolean b2) {
        try {
            this.hasSpace = b;
            if (b2) {
                this.isDependent = true;
                this.minLineFeeds = 1;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return true;
    }
    
    private boolean m() {
        Label_0027: {
            try {
                if (this.typeLeft == OCTokenTypes.LBRACE) {
                    break Label_0027;
                }
                final OCSpacingProcessor ocSpacingProcessor = this;
                final IElementType elementType = ocSpacingProcessor.typeRight;
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.RBRACE;
                if (elementType == ocPunctuatorElementType) {
                    break Label_0027;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCSpacingProcessor ocSpacingProcessor = this;
                final IElementType elementType = ocSpacingProcessor.typeRight;
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.RBRACE;
                if (elementType == ocPunctuatorElementType) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    private boolean n() {
        Label_0027: {
            try {
                if (this.typeLeft == OCTokenTypes.LBRACKET) {
                    break Label_0027;
                }
                final OCSpacingProcessor ocSpacingProcessor = this;
                final IElementType elementType = ocSpacingProcessor.typeRight;
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.RBRACKET;
                if (elementType == ocPunctuatorElementType) {
                    break Label_0027;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCSpacingProcessor ocSpacingProcessor = this;
                final IElementType elementType = ocSpacingProcessor.typeRight;
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.RBRACKET;
                if (elementType == ocPunctuatorElementType) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    private boolean j() {
        Label_0024: {
            try {
                if (!this.m()) {
                    return false;
                }
                final OCSpacingProcessor ocSpacingProcessor = this;
                final IElementType elementType = ocSpacingProcessor.thisType;
                final boolean b = OCFormatterUtil.isCollectionOrStructureInitializer(elementType);
                if (!b) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCSpacingProcessor ocSpacingProcessor = this;
                final IElementType elementType = ocSpacingProcessor.thisType;
                final boolean b = OCFormatterUtil.isCollectionOrStructureInitializer(elementType);
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    private boolean a() {
        Label_0024: {
            try {
                if (!this.j()) {
                    return false;
                }
                final OCSpacingProcessor ocSpacingProcessor = this;
                final ASTNode astNode = ocSpacingProcessor.thisNode;
                final boolean b = OCElementUtil.isMethodOrFunctionBody(astNode);
                if (!b) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCSpacingProcessor ocSpacingProcessor = this;
                final ASTNode astNode = ocSpacingProcessor.thisNode;
                final boolean b = OCElementUtil.isMethodOrFunctionBody(astNode);
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    private boolean g() {
        Label_0024: {
            try {
                if (!this.j()) {
                    return false;
                }
                final OCSpacingProcessor ocSpacingProcessor = this;
                final ASTNode astNode = ocSpacingProcessor.thisNode;
                final boolean b = OCElementUtil.isMethodOrFunctionBody(astNode);
                if (b) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCSpacingProcessor ocSpacingProcessor = this;
                final ASTNode astNode = ocSpacingProcessor.thisNode;
                final boolean b = OCElementUtil.isMethodOrFunctionBody(astNode);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    @Contract("_, null, _ -> !null")
    public static Spacing getSpacing(@NotNull final OCCodeBlock ocCodeBlock, @Nullable final Block block, @NotNull final Block block2) {
        try {
            if (ocCodeBlock == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerBlock", "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor", "getSpacing"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (block2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "childRight", "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor", "getSpacing"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (block == null) {
                return Spacing.createSpacing(0, 0, 0, ocCodeBlock.getSettings().KEEP_LINE_BREAKS, ocCodeBlock.getSettings().KEEP_BLANK_LINES_IN_CODE);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        ASTNode astNode = null;
        boolean b = false;
        boolean inDirective = ocCodeBlock.isInDirective();
        ASTNode astNode2 = null;
        ASTNode astNode3 = null;
        Label_0620: {
            Label_0603: {
                Label_0491: {
                    Label_0335: {
                        Label_0234: {
                            Label_0164: {
                                try {
                                    if (block instanceof OCMacroBlock || block2 instanceof OCMacroBlock) {
                                        break Label_0164;
                                    }
                                }
                                catch (IllegalArgumentException ex4) {
                                    throw b(ex4);
                                }
                                astNode2 = OCSimpleBlock.extractLastNode(block);
                                astNode3 = OCSimpleBlock.extractFirstNode(block2);
                                break Label_0603;
                                try {
                                    if (!(block instanceof OCMacroBlock) || !(block2 instanceof OCMacroBlock)) {
                                        break Label_0234;
                                    }
                                }
                                catch (IllegalArgumentException ex5) {
                                    throw b(ex5);
                                }
                            }
                            final ASTNode lastInjection = ((OCMacroBlock)block).getLastInjection();
                            final ASTNode firstInjection = ((OCMacroBlock)block2).getFirstInjection();
                            astNode = OCFormatterUtil.getBestCommonOwner(ocCodeBlock.getNode(), lastInjection, firstInjection);
                            astNode2 = a(astNode, lastInjection);
                            astNode3 = a(astNode, firstInjection);
                            break Label_0335;
                        }
                        if (block2 instanceof OCMacroBlock) {
                            final Pair<ASTNode, ASTNode> a = a(((OCMacroBlock)block2).getFirstInjection());
                            astNode2 = (ASTNode)a.first;
                            astNode3 = (ASTNode)a.second;
                            if (astNode2 != null) {
                                astNode = astNode2.getTreeParent();
                            }
                        }
                        else {
                            final ASTNode lastInjection2 = ((OCMacroBlock)block).getLastInjection();
                            final ASTNode c = c(OCSimpleBlock.extractFirstNode(block2));
                            astNode = OCFormatterUtil.getBestCommonOwner(ocCodeBlock.getNode(), lastInjection2, c);
                            astNode2 = a(astNode, lastInjection2);
                            astNode3 = a(astNode, c);
                        }
                        try {
                            if (!(block instanceof OCMacroBlock) || OCElementUtil.getElementType(((OCMacroBlock)block).getMacroCall().getLastChildNode()) == OCTokenTypes.RPAR) {
                                break Label_0491;
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw b(ex6);
                        }
                    }
                    ASTNode node = TreeUtil.nextLeaf(((OCMacroBlock)block).getLastInjection());
                    while (true) {
                        Label_0402: {
                            try {
                                if (node == null) {
                                    break;
                                }
                                final ASTNode astNode4 = node;
                                final boolean b2 = astNode4 instanceof PsiWhiteSpace;
                                if (!b2) {
                                    break Label_0402;
                                }
                                break Label_0402;
                            }
                            catch (IllegalArgumentException ex7) {
                                throw b(ex7);
                            }
                            try {
                                final ASTNode astNode4 = node;
                                final boolean b2 = astNode4 instanceof PsiWhiteSpace;
                                if (!b2) {
                                    if (!(node instanceof OCMacroForeignLeafElement)) {
                                        break;
                                    }
                                }
                            }
                            catch (IllegalArgumentException ex8) {
                                throw b(ex8);
                            }
                        }
                        node = TreeUtil.nextLeaf(node);
                    }
                    boolean b4 = false;
                    Label_0461: {
                        Label_0452: {
                            try {
                                if (node == null) {
                                    break Label_0452;
                                }
                                final ASTNode astNode5 = node;
                                final IElementType elementType = astNode5.getElementType();
                                final boolean b3 = elementType instanceof OCPunctuatorElementType;
                                if (!b3) {
                                    break Label_0452;
                                }
                                break Label_0452;
                            }
                            catch (IllegalArgumentException ex9) {
                                throw b(ex9);
                            }
                            try {
                                final ASTNode astNode5 = node;
                                final IElementType elementType = astNode5.getElementType();
                                final boolean b3 = elementType instanceof OCPunctuatorElementType;
                                if (!b3) {
                                    b4 = true;
                                    break Label_0461;
                                }
                            }
                            catch (IllegalArgumentException ex10) {
                                throw b(ex10);
                            }
                        }
                        b4 = false;
                    }
                    b = b4;
                    boolean b5 = false;
                    Label_0489: {
                        try {
                            if (((OCMacroBlock)block).getMacroCall() == ((OCMacroBlock)block).getFirstInjection()) {
                                b5 = true;
                                break Label_0489;
                            }
                        }
                        catch (IllegalArgumentException ex11) {
                            throw b(ex11);
                        }
                        b5 = false;
                    }
                    inDirective = b5;
                    try {
                        if (b || !(block2 instanceof OCMacroBlock)) {
                            break Label_0603;
                        }
                    }
                    catch (IllegalArgumentException ex12) {
                        throw b(ex12);
                    }
                }
                ASTNode node2 = TreeUtil.prevLeaf(((OCMacroBlock)block2).getMacroCall());
                while (true) {
                    Label_0542: {
                        try {
                            if (node2 == null) {
                                break;
                            }
                            final ASTNode astNode6 = node2;
                            final boolean b6 = astNode6 instanceof PsiWhiteSpace;
                            if (!b6) {
                                break Label_0542;
                            }
                            break Label_0542;
                        }
                        catch (IllegalArgumentException ex13) {
                            throw b(ex13);
                        }
                        try {
                            final ASTNode astNode6 = node2;
                            final boolean b6 = astNode6 instanceof PsiWhiteSpace;
                            if (!b6) {
                                if (!(node2 instanceof OCMacroForeignLeafElement)) {
                                    break;
                                }
                            }
                        }
                        catch (IllegalArgumentException ex14) {
                            throw b(ex14);
                        }
                    }
                    node2 = TreeUtil.prevLeaf(node2);
                }
                boolean b8 = false;
                Label_0601: {
                    Label_0592: {
                        try {
                            if (node2 == null) {
                                break Label_0592;
                            }
                            final ASTNode astNode7 = node2;
                            final IElementType elementType2 = astNode7.getElementType();
                            final boolean b7 = elementType2 instanceof OCPunctuatorElementType;
                            if (!b7) {
                                break Label_0592;
                            }
                            break Label_0592;
                        }
                        catch (IllegalArgumentException ex15) {
                            throw b(ex15);
                        }
                        try {
                            final ASTNode astNode7 = node2;
                            final IElementType elementType2 = astNode7.getElementType();
                            final boolean b7 = elementType2 instanceof OCPunctuatorElementType;
                            if (!b7) {
                                b8 = true;
                                break Label_0601;
                            }
                        }
                        catch (IllegalArgumentException ex16) {
                            throw b(ex16);
                        }
                    }
                    b8 = false;
                }
                b = b8;
                try {
                    if (astNode2 == null) {
                        break Label_0620;
                    }
                    final ASTNode astNode8 = astNode3;
                    if (astNode8 == null) {
                        break Label_0620;
                    }
                    break Label_0620;
                }
                catch (IllegalArgumentException ex17) {
                    throw b(ex17);
                }
            }
            try {
                final ASTNode astNode8 = astNode3;
                if (astNode8 == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex18) {
                throw b(ex18);
            }
        }
        if (astNode == null) {
            astNode = ocCodeBlock.getNode();
        }
        return new OCSpacingProcessor(ocCodeBlock.getSettings(), ocCodeBlock.getOCSettings(), astNode, astNode2, astNode3).getResult(b, inDirective);
    }
    
    private static Pair<ASTNode, ASTNode> a(ASTNode treeParent) {
        while (treeParent != null) {
            ASTNode astNode = treeParent.getTreePrev();
            while (true) {
                Label_0029: {
                    try {
                        if (astNode == null) {
                            break;
                        }
                        final ASTNode astNode2 = astNode;
                        final boolean b = astNode2 instanceof PsiWhiteSpace;
                        if (!b) {
                            break Label_0029;
                        }
                        break Label_0029;
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    try {
                        final ASTNode astNode2 = astNode;
                        final boolean b = astNode2 instanceof PsiWhiteSpace;
                        if (!b) {
                            if (astNode.getElementType() != OCElementTypes.MACRO_CALL) {
                                break;
                            }
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                }
                astNode = astNode.getTreePrev();
            }
            try {
                if (astNode != null) {
                    return (Pair<ASTNode, ASTNode>)Pair.create((Object)astNode, (Object)treeParent);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            treeParent = treeParent.getTreeParent();
        }
        return (Pair<ASTNode, ASTNode>)Pair.create((Object)null, (Object)null);
    }
    
    @NotNull
    private static ASTNode c(@NotNull final ASTNode element) {
        try {
            if (element == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor", "getLeftmostRealLeaf"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Object node = TreeUtil.findFirstLeaf(element);
        while (true) {
            try {
                if (!(node instanceof PsiWhiteSpace)) {
                    if (!(node instanceof OCMacroForeignLeafElement)) {
                        break;
                    }
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            node = TreeUtil.nextLeaf((ASTNode)node);
        }
        ASTNode astNode = null;
        Label_0091: {
            try {
                if (node == null) {
                    astNode = element;
                    final ASTNode astNode2 = element;
                    break Label_0091;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            ASTNode astNode2;
            astNode = (astNode2 = (ASTNode)node);
            try {
                if (astNode2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor", "getLeftmostRealLeaf"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return astNode;
    }
    
    @NotNull
    private static ASTNode a(@Nullable final ASTNode p0, @NotNull final ASTNode p1) {
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
        //    18: ldc             "child"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "findParentInOwnerIfExist"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: ifnonnull       95
        //    48: aload_1        
        //    49: dup            
        //    50: ifnonnull       94
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: new             Ljava/lang/IllegalStateException;
        //    63: dup            
        //    64: ldc             "@NotNull method %s.%s must not return null"
        //    66: ldc             2
        //    68: anewarray       Ljava/lang/Object;
        //    71: dup            
        //    72: ldc             0
        //    74: ldc             "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor"
        //    76: aastore        
        //    77: dup            
        //    78: ldc             1
        //    80: ldc             "findParentInOwnerIfExist"
        //    82: aastore        
        //    83: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    86: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    89: athrow         
        //    90: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: areturn        
        //    95: aload_1        
        //    96: astore_2       
        //    97: aload_2        
        //    98: ifnull          175
        //   101: aload_2        
        //   102: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   107: aload_0        
        //   108: if_acmpne       165
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   117: athrow         
        //   118: aload_2        
        //   119: dup            
        //   120: ifnonnull       164
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: new             Ljava/lang/IllegalStateException;
        //   133: dup            
        //   134: ldc             "@NotNull method %s.%s must not return null"
        //   136: ldc             2
        //   138: anewarray       Ljava/lang/Object;
        //   141: dup            
        //   142: ldc             0
        //   144: ldc             "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor"
        //   146: aastore        
        //   147: dup            
        //   148: ldc             1
        //   150: ldc             "findParentInOwnerIfExist"
        //   152: aastore        
        //   153: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   156: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   159: athrow         
        //   160: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: areturn        
        //   165: aload_2        
        //   166: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   171: astore_2       
        //   172: goto            97
        //   175: aload_1        
        //   176: dup            
        //   177: ifnonnull       214
        //   180: new             Ljava/lang/IllegalStateException;
        //   183: dup            
        //   184: ldc             "@NotNull method %s.%s must not return null"
        //   186: ldc             2
        //   188: anewarray       Ljava/lang/Object;
        //   191: dup            
        //   192: ldc             0
        //   194: ldc             "com/jetbrains/cidr/lang/formatting/OCSpacingProcessor"
        //   196: aastore        
        //   197: dup            
        //   198: ldc             1
        //   200: ldc             "findParentInOwnerIfExist"
        //   202: aastore        
        //   203: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   206: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   209: athrow         
        //   210: invokestatic    com/jetbrains/cidr/lang/formatting/OCSpacingProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   213: athrow         
        //   214: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     53     56     60     Ljava/lang/IllegalArgumentException;
        //  48     90     90     94     Ljava/lang/IllegalArgumentException;
        //  97     111    114    118    Ljava/lang/IllegalArgumentException;
        //  101    123    126    130    Ljava/lang/IllegalArgumentException;
        //  118    160    160    164    Ljava/lang/IllegalArgumentException;
        //  175    210    210    214    Ljava/lang/IllegalArgumentException;
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
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCSpacingProcessor.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        KEYWORD_WITH_LIST = TokenSet.create(new IElementType[] { OCElementTypes.OBJC_KEYWORD, OCTokenTypes.NOEXCEPT_KEYWORD, OCTokenTypes.THROW_KEYWORD, OCTokenTypes.TYPEOF_KEYWORD, OCTokenTypes.DECLTYPE_CPP_KEYWORD, OCTokenTypes.TYPEID_CPP_KEYWORD, OCTokenTypes.SIZEOF_KEYWORD, OCTokenTypes.STATIC_ASSERT_KEYWORD, OCTokenTypes.STATIC_ASSERT_C_KEYWORD, OCTokenTypes.ALIGNOF_KEYWORD, OCTokenTypes.ALIGNAS_KEYWORD, OCTokenTypes.ALIGNAS_CPP_KEYWORD, OCTokenTypes.ALIGNOF_CPP_KEYWORD });
        MANDATORY_SPACE_PAIR = new ArrayList[] { ContainerUtil.newArrayList((Object[])new OCPunctuatorElementType[] { OCTokenTypes.PLUS, OCTokenTypes.MINUS, OCTokenTypes.PLUSPLUS, OCTokenTypes.MINUSMINUS }), ContainerUtil.newArrayList((Object[])new OCPunctuatorElementType[] { OCTokenTypes.AND, OCTokenTypes.ANDAND, OCTokenTypes.OR, OCTokenTypes.OROR }), ContainerUtil.newArrayList((Object[])new OCPunctuatorElementType[] { OCTokenTypes.COLON, OCTokenTypes.COLON2X }) };
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
