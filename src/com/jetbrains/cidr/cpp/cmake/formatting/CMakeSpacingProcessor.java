// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.formatting;

import com.intellij.formatting.ASTBlock;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.formatting.Spacing;
import org.jetbrains.annotations.Nullable;
import com.intellij.formatting.Block;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeElementTypes;
import com.intellij.formatting.SpacingBuilder;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.tree.TokenSet;

public class CMakeSpacingProcessor
{
    private static final TokenSet ARGUMENT_ELEMENTS;
    private static final TokenSet PARENTHESES;
    @NotNull
    private final CommonCodeStyleSettings mySettings;
    @NotNull
    private final SpacingBuilder mySpacingBuilder;
    
    public CMakeSpacingProcessor(@NotNull final CommonCodeStyleSettings mySettings) {
        if (mySettings == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor", "<init>"));
        }
        this.mySettings = mySettings;
        this.mySpacingBuilder = new SpacingBuilder(mySettings).betweenInside(CMakeElementTypes.C_MAKE_COMMAND_NAME, CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS, CMakeElementTypes.C_MAKE_COMMAND).spaceIf(this.mySettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES).betweenInside(CMakeElementTypes.C_MAKE_IF_COMMAND_CALL, CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS, CMakeElementTypes.C_MAKE_IF_COMMAND).spaceIf(this.mySettings.SPACE_BEFORE_IF_PARENTHESES).betweenInside(CMakeElementTypes.C_MAKE_ELSE_IF_COMMAND_CALL, CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS, CMakeElementTypes.C_MAKE_ELSE_IF_COMMAND).spaceIf(this.mySettings.SPACE_BEFORE_IF_PARENTHESES).betweenInside(CMakeElementTypes.C_MAKE_ELSE_COMMAND_CALL, CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS, CMakeElementTypes.C_MAKE_ELSE_COMMAND).spaceIf(this.mySettings.SPACE_BEFORE_IF_PARENTHESES).betweenInside(CMakeElementTypes.C_MAKE_END_IF_COMMAND_CALL, CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS, CMakeElementTypes.C_MAKE_END_IF_COMMAND).spaceIf(this.mySettings.SPACE_BEFORE_IF_PARENTHESES).betweenInside(CMakeElementTypes.C_MAKE_WHILE_COMMAND_CALL, CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS, CMakeElementTypes.C_MAKE_WHILE_COMMAND).spaceIf(this.mySettings.SPACE_BEFORE_WHILE_PARENTHESES).betweenInside(CMakeElementTypes.C_MAKE_END_WHILE_COMMAND_CALL, CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS, CMakeElementTypes.C_MAKE_END_WHILE_COMMAND).spaceIf(this.mySettings.SPACE_BEFORE_WHILE_PARENTHESES).betweenInside(CMakeElementTypes.C_MAKE_FOREACH_COMMAND_CALL, CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS, CMakeElementTypes.C_MAKE_FOREACH_COMMAND).spaceIf(this.mySettings.SPACE_BEFORE_FOR_PARENTHESES).betweenInside(CMakeElementTypes.C_MAKE_END_FOREACH_COMMAND_CALL, CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS, CMakeElementTypes.C_MAKE_END_FOREACH_COMMAND).spaceIf(this.mySettings.SPACE_BEFORE_FOR_PARENTHESES).betweenInside(CMakeElementTypes.C_MAKE_MACRO_COMMAND_CALL, CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS, CMakeElementTypes.C_MAKE_MACRO_COMMAND).spaceIf(this.mySettings.SPACE_BEFORE_METHOD_PARENTHESES).betweenInside(CMakeElementTypes.C_MAKE_END_MACRO_COMMAND_CALL, CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS, CMakeElementTypes.C_MAKE_END_MACRO_COMMAND).spaceIf(this.mySettings.SPACE_BEFORE_METHOD_PARENTHESES).betweenInside(CMakeElementTypes.C_MAKE_FUNCTION_COMMAND_CALL, CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS, CMakeElementTypes.C_MAKE_FUNCTION_COMMAND).spaceIf(this.mySettings.SPACE_BEFORE_METHOD_PARENTHESES).betweenInside(CMakeElementTypes.C_MAKE_END_FUNCTION_COMMAND_CALL, CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS, CMakeElementTypes.C_MAKE_END_FUNCTION_COMMAND).spaceIf(this.mySettings.SPACE_BEFORE_METHOD_PARENTHESES);
    }
    
    @Nullable
    public Spacing getSpacing(@NotNull final CMakeCodeBlock p0, @Nullable final Block p1, @Nullable final Block p2) {
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
        //    18: ldc             "parentBlock"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getSpacing"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.getBlockType:(Lcom/intellij/formatting/Block;)Lcom/intellij/psi/tree/IElementType;
        //    48: astore          4
        //    50: aload_3        
        //    51: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.getBlockType:(Lcom/intellij/formatting/Block;)Lcom/intellij/psi/tree/IElementType;
        //    54: astore          5
        //    56: aload_2        
        //    57: ifnull          71
        //    60: aload_3        
        //    61: ifnonnull       79
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: invokestatic    com/intellij/formatting/Spacing.getReadOnlySpacing:()Lcom/intellij/formatting/Spacing;
        //    74: areturn        
        //    75: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload_0        
        //    80: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.mySpacingBuilder:Lcom/intellij/formatting/SpacingBuilder;
        //    83: aload_1        
        //    84: aload_2        
        //    85: aload_3        
        //    86: invokevirtual   com/intellij/formatting/SpacingBuilder.getSpacing:(Lcom/intellij/formatting/Block;Lcom/intellij/formatting/Block;Lcom/intellij/formatting/Block;)Lcom/intellij/formatting/Spacing;
        //    89: astore          6
        //    91: aload           6
        //    93: ifnull          103
        //    96: aload           6
        //    98: areturn        
        //    99: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: aload_1        
        //   104: invokevirtual   com/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock.getNode:()Lcom/intellij/lang/ASTNode;
        //   107: astore          7
        //   109: aload           7
        //   111: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   116: astore          8
        //   118: aload           4
        //   120: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.LPAR:Lcom/intellij/psi/tree/IElementType;
        //   123: if_acmpne       167
        //   126: aload           5
        //   128: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_ARGUMENT:Lcom/intellij/psi/tree/IElementType;
        //   131: if_acmpne       167
        //   134: goto            141
        //   137: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   140: athrow         
        //   141: aload           8
        //   143: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS:Lcom/intellij/psi/tree/IElementType;
        //   146: if_acmpne       167
        //   149: goto            156
        //   152: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   155: athrow         
        //   156: aload_0        
        //   157: aload           7
        //   159: invokespecial   com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/formatting/Spacing;
        //   162: areturn        
        //   163: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: aload           4
        //   169: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_ARGUMENT:Lcom/intellij/psi/tree/IElementType;
        //   172: if_acmpne       216
        //   175: aload           5
        //   177: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.RPAR:Lcom/intellij/psi/tree/IElementType;
        //   180: if_acmpne       216
        //   183: goto            190
        //   186: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   189: athrow         
        //   190: aload           8
        //   192: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS:Lcom/intellij/psi/tree/IElementType;
        //   195: if_acmpne       216
        //   198: goto            205
        //   201: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   204: athrow         
        //   205: aload_0        
        //   206: aload           7
        //   208: invokespecial   com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/formatting/Spacing;
        //   211: areturn        
        //   212: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: aload           8
        //   218: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS:Lcom/intellij/psi/tree/IElementType;
        //   221: if_acmpne       528
        //   224: getstatic       com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.ARGUMENT_ELEMENTS:Lcom/intellij/psi/tree/TokenSet;
        //   227: aload           4
        //   229: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   232: ifeq            528
        //   235: goto            242
        //   238: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   241: athrow         
        //   242: getstatic       com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.ARGUMENT_ELEMENTS:Lcom/intellij/psi/tree/TokenSet;
        //   245: aload           5
        //   247: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   250: ifeq            528
        //   253: goto            260
        //   256: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   259: athrow         
        //   260: aload           4
        //   262: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.LPAR:Lcom/intellij/psi/tree/IElementType;
        //   265: if_acmpne       315
        //   268: goto            275
        //   271: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   274: athrow         
        //   275: aload_2        
        //   276: instanceof      Lcom/intellij/formatting/ASTBlock;
        //   279: ifeq            323
        //   282: goto            289
        //   285: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   288: athrow         
        //   289: aload           7
        //   291: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //   296: aload_2        
        //   297: checkcast       Lcom/intellij/formatting/ASTBlock;
        //   300: invokeinterface com/intellij/formatting/ASTBlock.getNode:()Lcom/intellij/lang/ASTNode;
        //   305: if_acmpeq       323
        //   308: goto            315
        //   311: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   314: athrow         
        //   315: iconst_1       
        //   316: goto            324
        //   319: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   322: athrow         
        //   323: iconst_0       
        //   324: istore          9
        //   326: aload           5
        //   328: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.RPAR:Lcom/intellij/psi/tree/IElementType;
        //   331: if_acmpne       374
        //   334: aload_3        
        //   335: instanceof      Lcom/intellij/formatting/ASTBlock;
        //   338: ifeq            382
        //   341: goto            348
        //   344: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   347: athrow         
        //   348: aload           7
        //   350: invokeinterface com/intellij/lang/ASTNode.getLastChildNode:()Lcom/intellij/lang/ASTNode;
        //   355: aload_3        
        //   356: checkcast       Lcom/intellij/formatting/ASTBlock;
        //   359: invokeinterface com/intellij/formatting/ASTBlock.getNode:()Lcom/intellij/lang/ASTNode;
        //   364: if_acmpeq       382
        //   367: goto            374
        //   370: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   373: athrow         
        //   374: iconst_1       
        //   375: goto            383
        //   378: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   381: athrow         
        //   382: iconst_0       
        //   383: istore          10
        //   385: iload           9
        //   387: ifne            402
        //   390: iload           10
        //   392: ifeq            528
        //   395: goto            402
        //   398: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   401: athrow         
        //   402: getstatic       com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.PARENTHESES:Lcom/intellij/psi/tree/TokenSet;
        //   405: aload           4
        //   407: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   410: ifeq            457
        //   413: goto            420
        //   416: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   419: athrow         
        //   420: getstatic       com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.PARENTHESES:Lcom/intellij/psi/tree/TokenSet;
        //   423: aload           5
        //   425: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   428: ifeq            457
        //   431: goto            438
        //   434: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   437: athrow         
        //   438: iconst_0       
        //   439: iconst_0       
        //   440: iconst_0       
        //   441: iconst_1       
        //   442: aload_0        
        //   443: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   446: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.KEEP_BLANK_LINES_IN_CODE:I
        //   449: invokestatic    com/intellij/formatting/Spacing.createSpacing:(IIIZI)Lcom/intellij/formatting/Spacing;
        //   452: areturn        
        //   453: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   456: athrow         
        //   457: iconst_1       
        //   458: istore          11
        //   460: aload           4
        //   462: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.COMMENT:Lcom/intellij/psi/tree/IElementType;
        //   465: if_acmpeq       483
        //   468: aload           5
        //   470: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.COMMENT:Lcom/intellij/psi/tree/IElementType;
        //   473: if_acmpne       511
        //   476: goto            483
        //   479: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   482: athrow         
        //   483: aload_2        
        //   484: checkcast       Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock;
        //   487: aload_3        
        //   488: checkcast       Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock;
        //   491: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeFormattingUtils.textBetweenChildrenContainsLineBreak:(Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock;Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakeCodeBlock;)Z
        //   494: ifne            511
        //   497: goto            504
        //   500: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   503: athrow         
        //   504: aload_2        
        //   505: aload_3        
        //   506: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Lcom/intellij/formatting/Block;Lcom/intellij/formatting/Block;)I
        //   509: istore          11
        //   511: iload           11
        //   513: iload           11
        //   515: iconst_0       
        //   516: iconst_1       
        //   517: aload_0        
        //   518: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   521: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.KEEP_BLANK_LINES_IN_CODE:I
        //   524: invokestatic    com/intellij/formatting/Spacing.createSpacing:(IIIZI)Lcom/intellij/formatting/Spacing;
        //   527: areturn        
        //   528: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.CMAKE_BLOCK_START_TOKENS:Lcom/intellij/psi/tree/TokenSet;
        //   531: aload           4
        //   533: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   536: ifeq            575
        //   539: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.CMAKE_BLOCK_START_TOKENS:Lcom/intellij/psi/tree/TokenSet;
        //   542: aload           5
        //   544: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   547: ifne            638
        //   550: goto            557
        //   553: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   556: athrow         
        //   557: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.CMAKE_BLOCK_END_TOKENS:Lcom/intellij/psi/tree/TokenSet;
        //   560: aload           5
        //   562: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   565: ifne            638
        //   568: goto            575
        //   571: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   574: athrow         
        //   575: aload           4
        //   577: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS:Lcom/intellij/psi/tree/IElementType;
        //   580: if_acmpne       608
        //   583: goto            590
        //   586: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   589: athrow         
        //   590: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.CMAKE_BLOCK_END_TOKENS:Lcom/intellij/psi/tree/TokenSet;
        //   593: aload           5
        //   595: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   598: ifne            638
        //   601: goto            608
        //   604: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   607: athrow         
        //   608: aload           4
        //   610: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_BODY_BLOCK:Lcom/intellij/psi/tree/IElementType;
        //   613: if_acmpeq       638
        //   616: goto            623
        //   619: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   622: athrow         
        //   623: aload           5
        //   625: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_BODY_BLOCK:Lcom/intellij/psi/tree/IElementType;
        //   628: if_acmpne       751
        //   631: goto            638
        //   634: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   637: athrow         
        //   638: aload           4
        //   640: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.COMMENT:Lcom/intellij/psi/tree/IElementType;
        //   643: if_acmpeq       668
        //   646: goto            653
        //   649: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   652: athrow         
        //   653: aload           5
        //   655: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.COMMENT:Lcom/intellij/psi/tree/IElementType;
        //   658: if_acmpne       736
        //   661: goto            668
        //   664: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   667: athrow         
        //   668: aload_2        
        //   669: aload_3        
        //   670: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Lcom/intellij/formatting/Block;Lcom/intellij/formatting/Block;)I
        //   673: istore          9
        //   675: aload           4
        //   677: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.COMMENT:Lcom/intellij/psi/tree/IElementType;
        //   680: if_acmpne       719
        //   683: aload           5
        //   685: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.COMMENT:Lcom/intellij/psi/tree/IElementType;
        //   688: if_acmpne       719
        //   691: goto            698
        //   694: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   697: athrow         
        //   698: iload           9
        //   700: iload           9
        //   702: iconst_1       
        //   703: iconst_1       
        //   704: aload_0        
        //   705: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   708: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.KEEP_BLANK_LINES_IN_CODE:I
        //   711: invokestatic    com/intellij/formatting/Spacing.createSpacing:(IIIZI)Lcom/intellij/formatting/Spacing;
        //   714: areturn        
        //   715: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   718: athrow         
        //   719: iload           9
        //   721: iload           9
        //   723: iconst_0       
        //   724: iconst_1       
        //   725: aload_0        
        //   726: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   729: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.KEEP_BLANK_LINES_IN_CODE:I
        //   732: invokestatic    com/intellij/formatting/Spacing.createSpacing:(IIIZI)Lcom/intellij/formatting/Spacing;
        //   735: areturn        
        //   736: iconst_0       
        //   737: iconst_0       
        //   738: iconst_1       
        //   739: iconst_1       
        //   740: aload_0        
        //   741: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   744: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.KEEP_BLANK_LINES_IN_CODE:I
        //   747: invokestatic    com/intellij/formatting/Spacing.createSpacing:(IIIZI)Lcom/intellij/formatting/Spacing;
        //   750: areturn        
        //   751: aload           4
        //   753: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.LPAR:Lcom/intellij/psi/tree/IElementType;
        //   756: if_acmpne       808
        //   759: aload           5
        //   761: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.RPAR:Lcom/intellij/psi/tree/IElementType;
        //   764: if_acmpne       808
        //   767: goto            774
        //   770: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   773: athrow         
        //   774: aload           8
        //   776: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS:Lcom/intellij/psi/tree/IElementType;
        //   779: if_acmpne       808
        //   782: goto            789
        //   785: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   788: athrow         
        //   789: iconst_0       
        //   790: iconst_0       
        //   791: iconst_0       
        //   792: iconst_1       
        //   793: aload_0        
        //   794: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   797: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.KEEP_BLANK_LINES_IN_CODE:I
        //   800: invokestatic    com/intellij/formatting/Spacing.createSpacing:(IIIZI)Lcom/intellij/formatting/Spacing;
        //   803: areturn        
        //   804: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   807: athrow         
        //   808: aconst_null    
        //   809: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  56     64     67     71     Ljava/lang/IllegalArgumentException;
        //  60     75     75     79     Ljava/lang/IllegalArgumentException;
        //  91     99     99     103    Ljava/lang/IllegalArgumentException;
        //  118    134    137    141    Ljava/lang/IllegalArgumentException;
        //  126    149    152    156    Ljava/lang/IllegalArgumentException;
        //  141    163    163    167    Ljava/lang/IllegalArgumentException;
        //  167    183    186    190    Ljava/lang/IllegalArgumentException;
        //  175    198    201    205    Ljava/lang/IllegalArgumentException;
        //  190    212    212    216    Ljava/lang/IllegalArgumentException;
        //  216    235    238    242    Ljava/lang/IllegalArgumentException;
        //  224    253    256    260    Ljava/lang/IllegalArgumentException;
        //  242    268    271    275    Ljava/lang/IllegalArgumentException;
        //  260    282    285    289    Ljava/lang/IllegalArgumentException;
        //  275    308    311    315    Ljava/lang/IllegalArgumentException;
        //  289    319    319    323    Ljava/lang/IllegalArgumentException;
        //  326    341    344    348    Ljava/lang/IllegalArgumentException;
        //  334    367    370    374    Ljava/lang/IllegalArgumentException;
        //  348    378    378    382    Ljava/lang/IllegalArgumentException;
        //  385    395    398    402    Ljava/lang/IllegalArgumentException;
        //  390    413    416    420    Ljava/lang/IllegalArgumentException;
        //  402    431    434    438    Ljava/lang/IllegalArgumentException;
        //  420    453    453    457    Ljava/lang/IllegalArgumentException;
        //  460    476    479    483    Ljava/lang/IllegalArgumentException;
        //  468    497    500    504    Ljava/lang/IllegalArgumentException;
        //  528    550    553    557    Ljava/lang/IllegalArgumentException;
        //  539    568    571    575    Ljava/lang/IllegalArgumentException;
        //  557    583    586    590    Ljava/lang/IllegalArgumentException;
        //  575    601    604    608    Ljava/lang/IllegalArgumentException;
        //  590    616    619    623    Ljava/lang/IllegalArgumentException;
        //  608    631    634    638    Ljava/lang/IllegalArgumentException;
        //  623    646    649    653    Ljava/lang/IllegalArgumentException;
        //  638    661    664    668    Ljava/lang/IllegalArgumentException;
        //  675    691    694    698    Ljava/lang/IllegalArgumentException;
        //  683    715    715    719    Ljava/lang/IllegalArgumentException;
        //  751    767    770    774    Ljava/lang/IllegalArgumentException;
        //  759    782    785    789    Ljava/lang/IllegalArgumentException;
        //  774    804    804    808    Ljava/lang/IllegalArgumentException;
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
    
    private static int a(@NotNull final Block block, @NotNull final Block block2) {
        try {
            if (block == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child1", "com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor", "getSpacesBetweenChildren"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (block2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child2", "com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor", "getSpacesBetweenChildren"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return block2.getTextRange().getStartOffset() - block.getTextRange().getEndOffset();
    }
    
    @Nullable
    private Spacing a(@NotNull final ASTNode p0) {
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
        //    18: ldc             "parentNode"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getLoopSpacing"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //    50: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    55: astore_2       
        //    56: aload_2        
        //    57: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_WHILE_COMMAND:Lcom/intellij/psi/tree/IElementType;
        //    60: if_acmpeq       77
        //    63: aload_2        
        //    64: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_END_WHILE_COMMAND:Lcom/intellij/psi/tree/IElementType;
        //    67: if_acmpne       93
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: aload_0        
        //    78: aload_0        
        //    79: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //    82: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_WHILE_PARENTHESES:Z
        //    85: invokespecial   com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Z)Lcom/intellij/formatting/Spacing;
        //    88: areturn        
        //    89: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: aload_2        
        //    94: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_FOREACH_COMMAND:Lcom/intellij/psi/tree/IElementType;
        //    97: if_acmpeq       114
        //   100: aload_2        
        //   101: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_END_FOREACH_COMMAND:Lcom/intellij/psi/tree/IElementType;
        //   104: if_acmpne       130
        //   107: goto            114
        //   110: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   113: athrow         
        //   114: aload_0        
        //   115: aload_0        
        //   116: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   119: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_FOR_PARENTHESES:Z
        //   122: invokespecial   com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Z)Lcom/intellij/formatting/Spacing;
        //   125: areturn        
        //   126: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: aload_2        
        //   131: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_IF_COMMAND:Lcom/intellij/psi/tree/IElementType;
        //   134: if_acmpeq       179
        //   137: aload_2        
        //   138: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_END_IF_COMMAND:Lcom/intellij/psi/tree/IElementType;
        //   141: if_acmpeq       179
        //   144: goto            151
        //   147: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   150: athrow         
        //   151: aload_2        
        //   152: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_ELSE_IF_COMMAND:Lcom/intellij/psi/tree/IElementType;
        //   155: if_acmpeq       179
        //   158: goto            165
        //   161: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   164: athrow         
        //   165: aload_2        
        //   166: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_ELSE_COMMAND:Lcom/intellij/psi/tree/IElementType;
        //   169: if_acmpne       195
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   178: athrow         
        //   179: aload_0        
        //   180: aload_0        
        //   181: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   184: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_IF_PARENTHESES:Z
        //   187: invokespecial   com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Z)Lcom/intellij/formatting/Spacing;
        //   190: areturn        
        //   191: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: aload_2        
        //   196: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_COMMAND:Lcom/intellij/psi/tree/IElementType;
        //   199: if_acmpne       218
        //   202: aload_0        
        //   203: aload_0        
        //   204: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   207: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_METHOD_CALL_PARENTHESES:Z
        //   210: invokespecial   com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Z)Lcom/intellij/formatting/Spacing;
        //   213: areturn        
        //   214: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   217: athrow         
        //   218: aload_2        
        //   219: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_MACRO_COMMAND:Lcom/intellij/psi/tree/IElementType;
        //   222: if_acmpeq       267
        //   225: aload_2        
        //   226: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_END_MACRO_COMMAND:Lcom/intellij/psi/tree/IElementType;
        //   229: if_acmpeq       267
        //   232: goto            239
        //   235: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   238: athrow         
        //   239: aload_2        
        //   240: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_FUNCTION_COMMAND:Lcom/intellij/psi/tree/IElementType;
        //   243: if_acmpeq       267
        //   246: goto            253
        //   249: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   252: athrow         
        //   253: aload_2        
        //   254: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_END_FUNCTION_COMMAND:Lcom/intellij/psi/tree/IElementType;
        //   257: if_acmpne       283
        //   260: goto            267
        //   263: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   266: athrow         
        //   267: aload_0        
        //   268: aload_0        
        //   269: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   272: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.SPACE_WITHIN_METHOD_PARENTHESES:Z
        //   275: invokespecial   com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Z)Lcom/intellij/formatting/Spacing;
        //   278: areturn        
        //   279: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   282: athrow         
        //   283: aconst_null    
        //   284: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  56     70     73     77     Ljava/lang/IllegalArgumentException;
        //  63     89     89     93     Ljava/lang/IllegalArgumentException;
        //  93     107    110    114    Ljava/lang/IllegalArgumentException;
        //  100    126    126    130    Ljava/lang/IllegalArgumentException;
        //  130    144    147    151    Ljava/lang/IllegalArgumentException;
        //  137    158    161    165    Ljava/lang/IllegalArgumentException;
        //  151    172    175    179    Ljava/lang/IllegalArgumentException;
        //  165    191    191    195    Ljava/lang/IllegalArgumentException;
        //  195    214    214    218    Ljava/lang/IllegalArgumentException;
        //  218    232    235    239    Ljava/lang/IllegalArgumentException;
        //  225    246    249    253    Ljava/lang/IllegalArgumentException;
        //  239    260    263    267    Ljava/lang/IllegalArgumentException;
        //  253    279    279    283    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0151:
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
    private Spacing a(final boolean b) {
        Spacing spacing2 = null;
        Label_0064: {
            Spacing spacing = null;
            Label_0029: {
                try {
                    if (!b) {
                        break Label_0064;
                    }
                    final int n = 1;
                    final int n2 = 1;
                    final int n3 = 0;
                    final boolean b2 = true;
                    final CMakeSpacingProcessor cMakeSpacingProcessor = this;
                    final CommonCodeStyleSettings commonCodeStyleSettings = cMakeSpacingProcessor.mySettings;
                    final int n4 = commonCodeStyleSettings.KEEP_BLANK_LINES_IN_CODE;
                    spacing = Spacing.createSpacing(n, n2, n3, b2, n4);
                    if (spacing == null) {
                        break Label_0029;
                    }
                    return spacing;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final int n = 1;
                    final int n2 = 1;
                    final int n3 = 0;
                    final boolean b2 = true;
                    final CMakeSpacingProcessor cMakeSpacingProcessor = this;
                    final CommonCodeStyleSettings commonCodeStyleSettings = cMakeSpacingProcessor.mySettings;
                    final int n4 = commonCodeStyleSettings.KEEP_BLANK_LINES_IN_CODE;
                    spacing = Spacing.createSpacing(n, n2, n3, b2, n4);
                    if (spacing == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor", "createSpacing"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return spacing;
            try {
                spacing2 = Spacing.createSpacing(0, 0, 0, true, this.mySettings.KEEP_BLANK_LINES_IN_CODE);
                if (spacing2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/formatting/CMakeSpacingProcessor", "createSpacing"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return spacing2;
    }
    
    @Nullable
    public static IElementType getBlockType(@Nullable final Block block) {
        if (block instanceof ASTBlock) {
            return ((ASTBlock)block).getNode().getElementType();
        }
        return null;
    }
    
    static {
        ARGUMENT_ELEMENTS = TokenSet.create(new IElementType[] { CMakeElementTypes.LPAR, CMakeElementTypes.C_MAKE_ARGUMENT, CMakeElementTypes.RPAR, CMakeElementTypes.COMMENT });
        PARENTHESES = TokenSet.create(new IElementType[] { CMakeElementTypes.LPAR, CMakeElementTypes.RPAR });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
