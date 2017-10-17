// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.resolve.references.OCResourceReference;
import com.jetbrains.cidr.lang.resolve.references.OCStringResourceReference;
import java.util.Collection;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.jetbrains.cidr.lang.formatting.OCFormatterUtil;
import com.jetbrains.cidr.lang.resolve.references.OCResourceCompletionProvider;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.resolve.references.OCResourceReferenceContributor;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.psi.OCLocalizedString;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.folding.FoldingDescriptor;
import java.util.List;
import com.intellij.psi.tree.IElementType;
import java.util.Set;
import com.intellij.lang.folding.CustomFoldingBuilder;

public class OCFoldingBuilder extends CustomFoldingBuilder
{
    private static Set<? extends IElementType> INCLUDE_ELEMENT_TYPES;
    
    protected void buildLanguageFoldRegions(@NotNull final List<FoldingDescriptor> list, @NotNull final PsiElement psiElement, @NotNull final Document document, final boolean b) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptors", "com/jetbrains/cidr/lang/editor/OCFoldingBuilder", "buildLanguageFoldRegions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "root", "com/jetbrains/cidr/lang/editor/OCFoldingBuilder", "buildLanguageFoldRegions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (document == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "document", "com/jetbrains/cidr/lang/editor/OCFoldingBuilder", "buildLanguageFoldRegions"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        if (!DumbService.isDumb(psiElement.getProject())) {
            final ASTNode node = psiElement.getNode();
            try {
                if (node != null) {
                    a(node, document, list);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        else {
            OCLog.LOG.error("Folding builder was called in dumb mode!");
        }
    }
    
    @NotNull
    private static Pair<ASTNode, Integer> c(@Nullable final ASTNode astNode) {
        Pair create;
        try {
            create = Pair.create((Object)astNode, (Object)1);
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCFoldingBuilder", "nodeStart"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (Pair<ASTNode, Integer>)create;
    }
    
    @NotNull
    private static Pair<ASTNode, Integer> b(@Nullable final ASTNode astNode) {
        Pair create;
        try {
            create = Pair.create((Object)astNode, (Object)(-1));
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCFoldingBuilder", "nodeEnd"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (Pair<ASTNode, Integer>)create;
    }
    
    @NotNull
    private static ASTNode a(@NotNull final ASTNode p0, @NotNull final Document p1, @NotNull final List<FoldingDescriptor> p2) {
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
        //    18: ldc             "node"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "appendDescriptors"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "document"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "appendDescriptors"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "descriptors"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "appendDescriptors"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_0        
        //   133: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   138: astore_3       
        //   139: aload_3        
        //   140: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   143: if_acmpeq       160
        //   146: aload_3        
        //   147: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   150: if_acmpne       193
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   159: athrow         
        //   160: aload_0        
        //   161: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.BLOCK_STATEMENTS:Lcom/intellij/psi/tree/TokenSet;
        //   164: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/TokenSet;)Lcom/intellij/lang/ASTNode;
        //   169: astore          4
        //   171: aload_1        
        //   172: aload_0        
        //   173: aload_2        
        //   174: aload           4
        //   176: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.c:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //   179: aload           4
        //   181: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.b:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //   184: getstatic       com/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition.NOT_EMPTY_SEVERAL_LINES:Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;
        //   187: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/lang/ASTNode;Ljava/util/List;Lcom/intellij/openapi/util/Pair;Lcom/intellij/openapi/util/Pair;Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;)V
        //   190: goto            1098
        //   193: aload_3        
        //   194: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   197: if_acmpne       245
        //   200: aload_0        
        //   201: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.getPreviousNonWhitespaceOrCommentSibling:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   204: astore          4
        //   206: aload           4
        //   208: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //   211: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TEMPLATE_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   214: if_acmpne       242
        //   217: aload_1        
        //   218: aload_0        
        //   219: aload_2        
        //   220: aload           4
        //   222: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.c:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //   225: aload_0        
        //   226: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.b:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //   229: getstatic       com/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition.LONGER_THAN_5CHARS_OR_NOT_EMPTY_SEVERAL_LINES:Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;
        //   232: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/lang/ASTNode;Ljava/util/List;Lcom/intellij/openapi/util/Pair;Lcom/intellij/openapi/util/Pair;Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;)V
        //   235: goto            242
        //   238: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   241: athrow         
        //   242: goto            1098
        //   245: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.STRUCTURE_TYPES:Lcom/intellij/psi/tree/TokenSet;
        //   248: aload_3        
        //   249: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   252: ifne            269
        //   255: aload_3        
        //   256: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   259: if_acmpne       313
        //   262: goto            269
        //   265: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   268: athrow         
        //   269: aload_0        
        //   270: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   273: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   278: astore          4
        //   280: aload_0        
        //   281: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   284: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   289: astore          5
        //   291: aload_1        
        //   292: aload_0        
        //   293: aload_2        
        //   294: aload           4
        //   296: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.c:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //   299: aload           5
        //   301: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.b:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //   304: getstatic       com/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition.LONGER_THAN_5CHARS_OR_NOT_EMPTY_SEVERAL_LINES:Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;
        //   307: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/lang/ASTNode;Ljava/util/List;Lcom/intellij/openapi/util/Pair;Lcom/intellij/openapi/util/Pair;Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;)V
        //   310: goto            1098
        //   313: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CLASSES:Lcom/intellij/psi/tree/TokenSet;
        //   316: aload_3        
        //   317: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   320: ifeq            509
        //   323: aload_1        
        //   324: aload_0        
        //   325: invokeinterface com/intellij/lang/ASTNode.getStartOffset:()I
        //   330: invokeinterface com/intellij/openapi/editor/Document.getLineNumber:(I)I
        //   335: aload_1        
        //   336: aload_0        
        //   337: invokeinterface com/intellij/lang/ASTNode.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   342: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   345: invokeinterface com/intellij/openapi/editor/Document.getLineNumber:(I)I
        //   350: if_icmpeq       1098
        //   353: goto            360
        //   356: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   359: athrow         
        //   360: aload_3        
        //   361: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.INTERFACE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   364: if_acmpne       449
        //   367: goto            374
        //   370: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   373: athrow         
        //   374: aload_0        
        //   375: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.INSTANCE_VARIABLES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   378: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   383: astore          5
        //   385: aload           5
        //   387: ifnull          439
        //   390: aload           5
        //   392: invokeinterface com/intellij/lang/ASTNode.getTreePrev:()Lcom/intellij/lang/ASTNode;
        //   397: ifnull          439
        //   400: goto            407
        //   403: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   406: athrow         
        //   407: aload           5
        //   409: invokeinterface com/intellij/lang/ASTNode.getTreePrev:()Lcom/intellij/lang/ASTNode;
        //   414: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.d:(Lcom/intellij/lang/ASTNode;)Z
        //   417: ifeq            439
        //   420: goto            427
        //   423: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   426: athrow         
        //   427: aload           5
        //   429: invokeinterface com/intellij/lang/ASTNode.getTreePrev:()Lcom/intellij/lang/ASTNode;
        //   434: astore          5
        //   436: goto            385
        //   439: aload           5
        //   441: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.c:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //   444: astore          4
        //   446: goto            467
        //   449: aload_0        
        //   450: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   453: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   458: astore          5
        //   460: aload           5
        //   462: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.b:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //   465: astore          4
        //   467: aload_0        
        //   468: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.END_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   471: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.findObjCKeyword:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   474: astore          5
        //   476: aload           5
        //   478: ifnull          490
        //   481: aload           5
        //   483: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   488: astore          5
        //   490: aload_1        
        //   491: aload_0        
        //   492: aload_2        
        //   493: aload           4
        //   495: aload           5
        //   497: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.c:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //   500: getstatic       com/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition.LONGER_THAN_5CHARS_OR_NOT_EMPTY_SEVERAL_LINES:Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;
        //   503: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/lang/ASTNode;Ljava/util/List;Lcom/intellij/openapi/util/Pair;Lcom/intellij/openapi/util/Pair;Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;)V
        //   506: goto            1098
        //   509: aload_3        
        //   510: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.INSTANCE_VARIABLES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   513: if_acmpeq       558
        //   516: aload_3        
        //   517: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TEMPLATE_ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   520: if_acmpeq       558
        //   523: goto            530
        //   526: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   529: athrow         
        //   530: aload_3        
        //   531: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BLOCK_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   534: if_acmpeq       558
        //   537: goto            544
        //   540: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   543: athrow         
        //   544: aload_3        
        //   545: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RAW_STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   548: if_acmpne       582
        //   551: goto            558
        //   554: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   557: athrow         
        //   558: aload_1        
        //   559: aload_0        
        //   560: aload_2        
        //   561: aload_0        
        //   562: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.c:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //   565: aload_0        
        //   566: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.b:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //   569: getstatic       com/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition.NOT_EMPTY_SEVERAL_LINES:Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;
        //   572: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/lang/ASTNode;Ljava/util/List;Lcom/intellij/openapi/util/Pair;Lcom/intellij/openapi/util/Pair;Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;)V
        //   575: goto            1098
        //   578: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   581: athrow         
        //   582: aload_3        
        //   583: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   586: if_acmpne       613
        //   589: aload_1        
        //   590: aload_0        
        //   591: aload_2        
        //   592: aload_0        
        //   593: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.c:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //   596: aload_0        
        //   597: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.b:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //   600: getstatic       com/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition.ALWAYS:Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;
        //   603: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/lang/ASTNode;Ljava/util/List;Lcom/intellij/openapi/util/Pair;Lcom/intellij/openapi/util/Pair;Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;)V
        //   606: goto            1098
        //   609: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   612: athrow         
        //   613: getstatic       com/jetbrains/cidr/lang/editor/OCFoldingBuilder.INCLUDE_ELEMENT_TYPES:Ljava/util/Set;
        //   616: aload_3        
        //   617: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   622: ifeq            709
        //   625: aload_0        
        //   626: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //   631: astore          4
        //   633: aload           4
        //   635: ifnull          706
        //   638: aload_1        
        //   639: aload_0        
        //   640: aload_2        
        //   641: getstatic       com/jetbrains/cidr/lang/editor/OCFoldingBuilder.INCLUDE_ELEMENT_TYPES:Ljava/util/Set;
        //   644: aload           4
        //   646: bipush          -2
        //   648: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   651: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   654: getstatic       com/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition.LONGER_THAN_5CHARS_OR_NOT_EMPTY_SEVERAL_LINES:Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;
        //   657: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/lang/ASTNode;Ljava/util/List;Ljava/util/Collection;Lcom/intellij/openapi/util/Pair;Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;)Lcom/intellij/lang/ASTNode;
        //   660: dup            
        //   661: ifnonnull       705
        //   664: goto            671
        //   667: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   670: athrow         
        //   671: new             Ljava/lang/IllegalStateException;
        //   674: dup            
        //   675: ldc             "@NotNull method %s.%s must not return null"
        //   677: ldc             2
        //   679: anewarray       Ljava/lang/Object;
        //   682: dup            
        //   683: ldc             0
        //   685: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //   687: aastore        
        //   688: dup            
        //   689: ldc             1
        //   691: ldc             "appendDescriptors"
        //   693: aastore        
        //   694: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   697: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   700: athrow         
        //   701: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   704: athrow         
        //   705: areturn        
        //   706: goto            1098
        //   709: aload_3        
        //   710: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SYNTHESIZED_PROPERTIES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   713: if_acmpne       803
        //   716: aload_0        
        //   717: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //   722: astore          4
        //   724: aload           4
        //   726: ifnull          800
        //   729: aload_1        
        //   730: aload_0        
        //   731: aload_2        
        //   732: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SYNTHESIZED_PROPERTIES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   735: invokestatic    java/util/Collections.singleton:(Ljava/lang/Object;)Ljava/util/Set;
        //   738: aload           4
        //   740: bipush          -2
        //   742: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   745: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   748: getstatic       com/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition.LONGER_THAN_5CHARS_OR_NOT_EMPTY_SEVERAL_LINES:Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;
        //   751: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/lang/ASTNode;Ljava/util/List;Ljava/util/Collection;Lcom/intellij/openapi/util/Pair;Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;)Lcom/intellij/lang/ASTNode;
        //   754: dup            
        //   755: ifnonnull       799
        //   758: goto            765
        //   761: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   764: athrow         
        //   765: new             Ljava/lang/IllegalStateException;
        //   768: dup            
        //   769: ldc             "@NotNull method %s.%s must not return null"
        //   771: ldc             2
        //   773: anewarray       Ljava/lang/Object;
        //   776: dup            
        //   777: ldc             0
        //   779: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //   781: aastore        
        //   782: dup            
        //   783: ldc             1
        //   785: ldc             "appendDescriptors"
        //   787: aastore        
        //   788: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   791: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   794: athrow         
        //   795: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   798: athrow         
        //   799: areturn        
        //   800: goto            1098
        //   803: aload_3        
        //   804: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EOL_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   807: if_acmpne       899
        //   810: aload_0        
        //   811: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   816: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   821: instanceof      Lcom/intellij/psi/tree/IFileElementType;
        //   824: ifeq            1098
        //   827: goto            834
        //   830: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   833: athrow         
        //   834: aload_1        
        //   835: aload_0        
        //   836: aload_2        
        //   837: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EOL_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   840: invokestatic    java/util/Collections.singleton:(Ljava/lang/Object;)Ljava/util/Set;
        //   843: aload_0        
        //   844: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.c:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //   847: getstatic       com/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition.LONGER_THAN_5CHARS_OR_NOT_EMPTY_SEVERAL_LINES:Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;
        //   850: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/lang/ASTNode;Ljava/util/List;Ljava/util/Collection;Lcom/intellij/openapi/util/Pair;Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;)Lcom/intellij/lang/ASTNode;
        //   853: dup            
        //   854: ifnonnull       898
        //   857: goto            864
        //   860: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   863: athrow         
        //   864: new             Ljava/lang/IllegalStateException;
        //   867: dup            
        //   868: ldc             "@NotNull method %s.%s must not return null"
        //   870: ldc             2
        //   872: anewarray       Ljava/lang/Object;
        //   875: dup            
        //   876: ldc             0
        //   878: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //   880: aastore        
        //   881: dup            
        //   882: ldc             1
        //   884: ldc             "appendDescriptors"
        //   886: aastore        
        //   887: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   890: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   893: athrow         
        //   894: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   897: athrow         
        //   898: areturn        
        //   899: aload_3        
        //   900: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TEMPLATE_START_MARK:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   903: if_acmpne       997
        //   906: aload_0        
        //   907: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   912: astore          4
        //   914: aload           4
        //   916: invokeinterface com/intellij/lang/ASTNode.getTextLength:()I
        //   921: ifle            994
        //   924: aload           4
        //   926: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TEMPLATE_STOP_MARK:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   929: aload_0        
        //   930: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   935: astore          5
        //   937: aload           5
        //   939: ifnull          994
        //   942: aload_2        
        //   943: new             Lcom/intellij/lang/folding/FoldingDescriptor;
        //   946: dup            
        //   947: aload           4
        //   949: aload_0        
        //   950: invokeinterface com/intellij/lang/ASTNode.getStartOffset:()I
        //   955: aload           5
        //   957: invokeinterface com/intellij/lang/ASTNode.getStartOffset:()I
        //   962: aload           5
        //   964: invokeinterface com/intellij/lang/ASTNode.getTextLength:()I
        //   969: iadd           
        //   970: invokestatic    com/intellij/openapi/util/TextRange.create:(II)Lcom/intellij/openapi/util/TextRange;
        //   973: aconst_null    
        //   974: invokestatic    java/util/Collections.emptySet:()Ljava/util/Set;
        //   977: iconst_1       
        //   978: invokespecial   com/intellij/lang/folding/FoldingDescriptor.<init>:(Lcom/intellij/lang/ASTNode;Lcom/intellij/openapi/util/TextRange;Lcom/intellij/openapi/editor/FoldingGroup;Ljava/util/Set;Z)V
        //   981: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   986: pop            
        //   987: goto            994
        //   990: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   993: athrow         
        //   994: goto            1098
        //   997: aload_3        
        //   998: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MESSAGE_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1001: if_acmpeq       1018
        //  1004: aload_3        
        //  1005: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1008: if_acmpne       1032
        //  1011: goto            1018
        //  1014: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1017: athrow         
        //  1018: aload_0        
        //  1019: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.e:(Lcom/intellij/lang/ASTNode;)Lcom/jetbrains/cidr/lang/psi/OCLocalizedString;
        //  1022: ifnonnull       1074
        //  1025: goto            1032
        //  1028: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1031: athrow         
        //  1032: aload_3        
        //  1033: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.NS_ARRAY_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1036: if_acmpeq       1074
        //  1039: goto            1046
        //  1042: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1045: athrow         
        //  1046: aload_3        
        //  1047: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.NS_DICTIONARY_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1050: if_acmpeq       1074
        //  1053: goto            1060
        //  1056: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1059: athrow         
        //  1060: aload_3        
        //  1061: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.BLOCK_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1064: if_acmpne       1098
        //  1067: goto            1074
        //  1070: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1073: athrow         
        //  1074: aload_1        
        //  1075: aload_0        
        //  1076: aload_2        
        //  1077: aload_0        
        //  1078: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.c:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //  1081: aload_0        
        //  1082: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.b:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //  1085: getstatic       com/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition.LONGER_THAN_5CHARS_OR_NOT_EMPTY_SEVERAL_LINES:Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;
        //  1088: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/lang/ASTNode;Ljava/util/List;Lcom/intellij/openapi/util/Pair;Lcom/intellij/openapi/util/Pair;Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;)V
        //  1091: goto            1098
        //  1094: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1097: athrow         
        //  1098: aload_0        
        //  1099: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //  1104: astore          4
        //  1106: aload           4
        //  1108: ifnull          1128
        //  1111: aload           4
        //  1113: aload_1        
        //  1114: aload_2        
        //  1115: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Lcom/intellij/lang/ASTNode;Lcom/intellij/openapi/editor/Document;Ljava/util/List;)Lcom/intellij/lang/ASTNode;
        //  1118: invokeinterface com/intellij/lang/ASTNode.getTreeNext:()Lcom/intellij/lang/ASTNode;
        //  1123: astore          4
        //  1125: goto            1106
        //  1128: aload_0        
        //  1129: dup            
        //  1130: ifnonnull       1167
        //  1133: new             Ljava/lang/IllegalStateException;
        //  1136: dup            
        //  1137: ldc             "@NotNull method %s.%s must not return null"
        //  1139: ldc             2
        //  1141: anewarray       Ljava/lang/Object;
        //  1144: dup            
        //  1145: ldc             0
        //  1147: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //  1149: aastore        
        //  1150: dup            
        //  1151: ldc             1
        //  1153: ldc             "appendDescriptors"
        //  1155: aastore        
        //  1156: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1159: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1162: athrow         
        //  1163: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1166: athrow         
        //  1167: areturn        
        //    Signature:
        //  (Lcom/intellij/lang/ASTNode;Lcom/intellij/openapi/editor/Document;Ljava/util/List<Lcom/intellij/lang/folding/FoldingDescriptor;>;)Lcom/intellij/lang/ASTNode;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  139    153    156    160    Ljava/lang/IllegalArgumentException;
        //  206    235    238    242    Ljava/lang/IllegalArgumentException;
        //  245    262    265    269    Ljava/lang/IllegalArgumentException;
        //  313    353    356    360    Ljava/lang/IllegalArgumentException;
        //  323    367    370    374    Ljava/lang/IllegalArgumentException;
        //  385    400    403    407    Ljava/lang/IllegalArgumentException;
        //  390    420    423    427    Ljava/lang/IllegalArgumentException;
        //  509    523    526    530    Ljava/lang/IllegalArgumentException;
        //  516    537    540    544    Ljava/lang/IllegalArgumentException;
        //  530    551    554    558    Ljava/lang/IllegalArgumentException;
        //  544    578    578    582    Ljava/lang/IllegalArgumentException;
        //  582    609    609    613    Ljava/lang/IllegalArgumentException;
        //  633    664    667    671    Ljava/lang/IllegalArgumentException;
        //  638    701    701    705    Ljava/lang/IllegalArgumentException;
        //  724    758    761    765    Ljava/lang/IllegalArgumentException;
        //  729    795    795    799    Ljava/lang/IllegalArgumentException;
        //  803    827    830    834    Ljava/lang/IllegalArgumentException;
        //  810    857    860    864    Ljava/lang/IllegalArgumentException;
        //  834    894    894    898    Ljava/lang/IllegalArgumentException;
        //  937    987    990    994    Ljava/lang/IllegalArgumentException;
        //  997    1011   1014   1018   Ljava/lang/IllegalArgumentException;
        //  1004   1025   1028   1032   Ljava/lang/IllegalArgumentException;
        //  1018   1039   1042   1046   Ljava/lang/IllegalArgumentException;
        //  1032   1053   1056   1060   Ljava/lang/IllegalArgumentException;
        //  1046   1067   1070   1074   Ljava/lang/IllegalArgumentException;
        //  1060   1091   1094   1098   Ljava/lang/IllegalArgumentException;
        //  1128   1163   1163   1167   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0530:
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
    
    private static boolean d(@NotNull final ASTNode p0) {
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
        //    18: ldc             "node"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isUnimportantNode"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMENTS:Lcom/intellij/psi/tree/TokenSet;
        //    47: aload_0        
        //    48: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    53: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    56: ifne            89
        //    59: aload_0        
        //    60: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //    63: ifne            89
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: aload_0        
        //    74: invokeinterface com/intellij/lang/ASTNode.getTextLength:()I
        //    79: ifne            97
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: iconst_1       
        //    90: goto            98
        //    93: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    96: athrow         
        //    97: iconst_0       
        //    98: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     66     69     73     Ljava/lang/IllegalArgumentException;
        //  59     82     85     89     Ljava/lang/IllegalArgumentException;
        //  73     93     93     97     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0073:
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
    private static OCLocalizedString e(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/editor/OCFoldingBuilder", "getLocalizedString"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Ref create = Ref.create((Object)null);
        OCResourceReferenceContributor.processReferenceProviders((Processor<OCResourceCompletionProvider>)(ocResourceCompletionProvider -> {
            try {
                if (astNode == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/editor/OCFoldingBuilder", "lambda$getLocalizedString$0"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final OCResourceReference referenceByCall = ocResourceCompletionProvider.getReferenceByCall(astNode.getPsi(), null);
            if (referenceByCall instanceof OCStringResourceReference) {
                final OCStringResourceReference ocStringResourceReference = (OCStringResourceReference)referenceByCall;
                try {
                    if (!ocStringResourceReference.areAllLanguagesLocalized()) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                create.set((Object)ocStringResourceReference.resolve());
            }
            return true;
        }));
        return (OCLocalizedString)create.get();
    }
    
    private static void a(@NotNull final Document p0, @NotNull final ASTNode p1, @NotNull final List<FoldingDescriptor> p2, @NotNull final Pair<ASTNode, Integer> p3, @NotNull final Pair<ASTNode, Integer> p4, @NotNull final FoldingCondition p5) {
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
        //    18: ldc             "document"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "registerRange"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "node"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "registerRange"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "descriptors"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "registerRange"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_3        
        //   133: ifnonnull       176
        //   136: new             Ljava/lang/IllegalArgumentException;
        //   139: dup            
        //   140: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   142: ldc             3
        //   144: anewarray       Ljava/lang/Object;
        //   147: dup            
        //   148: ldc             0
        //   150: ldc             "start"
        //   152: aastore        
        //   153: dup            
        //   154: ldc             1
        //   156: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //   158: aastore        
        //   159: dup            
        //   160: ldc             2
        //   162: ldc             "registerRange"
        //   164: aastore        
        //   165: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   168: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   171: athrow         
        //   172: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: aload           4
        //   178: ifnonnull       221
        //   181: new             Ljava/lang/IllegalArgumentException;
        //   184: dup            
        //   185: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   187: ldc             3
        //   189: anewarray       Ljava/lang/Object;
        //   192: dup            
        //   193: ldc             0
        //   195: ldc             "end"
        //   197: aastore        
        //   198: dup            
        //   199: ldc             1
        //   201: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //   203: aastore        
        //   204: dup            
        //   205: ldc             2
        //   207: ldc             "registerRange"
        //   209: aastore        
        //   210: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   213: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   216: athrow         
        //   217: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   220: athrow         
        //   221: aload           5
        //   223: ifnonnull       266
        //   226: new             Ljava/lang/IllegalArgumentException;
        //   229: dup            
        //   230: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   232: ldc             3
        //   234: anewarray       Ljava/lang/Object;
        //   237: dup            
        //   238: ldc             0
        //   240: ldc             "foldingCondition"
        //   242: aastore        
        //   243: dup            
        //   244: ldc             1
        //   246: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //   248: aastore        
        //   249: dup            
        //   250: ldc             2
        //   252: ldc             "registerRange"
        //   254: aastore        
        //   255: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   258: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   261: athrow         
        //   262: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   265: athrow         
        //   266: aload_3        
        //   267: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Lcom/intellij/openapi/util/Pair;)I
        //   270: istore          6
        //   272: aload           4
        //   274: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Lcom/intellij/openapi/util/Pair;)I
        //   277: istore          7
        //   279: iload           7
        //   281: iload           6
        //   283: isub           
        //   284: istore          8
        //   286: aload_0        
        //   287: invokeinterface com/intellij/openapi/editor/Document.getTextLength:()I
        //   292: istore          9
        //   294: iload           6
        //   296: iflt            351
        //   299: iload           6
        //   301: iload           9
        //   303: if_icmpgt       351
        //   306: goto            313
        //   309: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   312: athrow         
        //   313: iload           7
        //   315: iflt            351
        //   318: goto            325
        //   321: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   324: athrow         
        //   325: iload           7
        //   327: iload           9
        //   329: if_icmpgt       351
        //   332: goto            339
        //   335: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   338: athrow         
        //   339: iload           8
        //   341: ifgt            356
        //   344: goto            351
        //   347: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   350: athrow         
        //   351: return         
        //   352: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   355: athrow         
        //   356: iconst_0       
        //   357: istore          10
        //   359: getstatic       com/jetbrains/cidr/lang/editor/OCFoldingBuilder$1.$SwitchMap$com$jetbrains$cidr$lang$editor$OCFoldingBuilder$FoldingCondition:[I
        //   362: aload           5
        //   364: invokevirtual   com/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition.ordinal:()I
        //   367: iaload         
        //   368: tableswitch {
        //                2: 396
        //                3: 420
        //                4: 469
        //          default: 485
        //        }
        //   396: iload           8
        //   398: iconst_5       
        //   399: if_icmple       417
        //   402: goto            409
        //   405: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   408: athrow         
        //   409: iconst_1       
        //   410: goto            418
        //   413: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   416: athrow         
        //   417: iconst_0       
        //   418: istore          10
        //   420: iload           10
        //   422: iload           8
        //   424: iconst_1       
        //   425: if_icmple       462
        //   428: aload_0        
        //   429: iload           6
        //   431: invokeinterface com/intellij/openapi/editor/Document.getLineNumber:(I)I
        //   436: aload_0        
        //   437: iload           7
        //   439: invokeinterface com/intellij/openapi/editor/Document.getLineNumber:(I)I
        //   444: if_icmpeq       462
        //   447: goto            454
        //   450: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   453: athrow         
        //   454: iconst_1       
        //   455: goto            463
        //   458: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   461: athrow         
        //   462: iconst_0       
        //   463: ior            
        //   464: istore          10
        //   466: goto            485
        //   469: iload           8
        //   471: ifle            482
        //   474: iconst_1       
        //   475: goto            483
        //   478: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   481: athrow         
        //   482: iconst_0       
        //   483: istore          10
        //   485: iload           10
        //   487: ifeq            542
        //   490: new             Lcom/intellij/lang/folding/FoldingDescriptor;
        //   493: dup            
        //   494: aload_1        
        //   495: iload           6
        //   497: iload           7
        //   499: invokestatic    com/intellij/openapi/util/TextRange.create:(II)Lcom/intellij/openapi/util/TextRange;
        //   502: invokespecial   com/intellij/lang/folding/FoldingDescriptor.<init>:(Lcom/intellij/lang/ASTNode;Lcom/intellij/openapi/util/TextRange;)V
        //   505: astore          11
        //   507: aload           11
        //   509: aload_1        
        //   510: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   515: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   518: if_acmpne       529
        //   521: iconst_1       
        //   522: goto            530
        //   525: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   528: athrow         
        //   529: iconst_0       
        //   530: invokevirtual   com/intellij/lang/folding/FoldingDescriptor.setCanBeRemovedWhenCollapsed:(Z)V
        //   533: aload_2        
        //   534: aload           11
        //   536: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   541: pop            
        //   542: return         
        //    Signature:
        //  (Lcom/intellij/openapi/editor/Document;Lcom/intellij/lang/ASTNode;Ljava/util/List<Lcom/intellij/lang/folding/FoldingDescriptor;>;Lcom/intellij/openapi/util/Pair<Lcom/intellij/lang/ASTNode;Ljava/lang/Integer;>;Lcom/intellij/openapi/util/Pair<Lcom/intellij/lang/ASTNode;Ljava/lang/Integer;>;Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    172    172    176    Ljava/lang/IllegalArgumentException;
        //  176    217    217    221    Ljava/lang/IllegalArgumentException;
        //  221    262    262    266    Ljava/lang/IllegalArgumentException;
        //  294    306    309    313    Ljava/lang/IllegalArgumentException;
        //  299    318    321    325    Ljava/lang/IllegalArgumentException;
        //  313    332    335    339    Ljava/lang/IllegalArgumentException;
        //  325    344    347    351    Ljava/lang/IllegalArgumentException;
        //  339    352    352    356    Ljava/lang/IllegalArgumentException;
        //  359    402    405    409    Ljava/lang/IllegalArgumentException;
        //  396    413    413    417    Ljava/lang/IllegalArgumentException;
        //  420    447    450    454    Ljava/lang/IllegalArgumentException;
        //  428    458    458    462    Ljava/lang/IllegalArgumentException;
        //  469    478    478    482    Ljava/lang/IllegalArgumentException;
        //  507    525    525    529    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0313:
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
    
    private static int a(@NotNull final Pair<ASTNode, Integer> pair) {
        try {
            if (pair == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "anchorNode", "com/jetbrains/cidr/lang/editor/OCFoldingBuilder", "getOffsetPosition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ASTNode astNode = (ASTNode)pair.first;
        ASTNode astNode2 = null;
        Label_0110: {
            Label_0082: {
                try {
                    if (astNode == null) {
                        return -1;
                    }
                    final Pair<ASTNode, Integer> pair2 = pair;
                    final Object o = pair2.second;
                    final Integer n = (Integer)o;
                    final int n2 = n;
                    if (n2 == 0) {
                        return -1;
                    }
                    break Label_0082;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final Pair<ASTNode, Integer> pair2 = pair;
                    final Object o = pair2.second;
                    final Integer n = (Integer)o;
                    final int n2 = n;
                    if (n2 == 0) {
                        return -1;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    if ((int)pair.second > 0) {
                        astNode2 = OCFormatterUtil.firstLeaf(astNode);
                        break Label_0110;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            astNode2 = OCFormatterUtil.lastLeaf(astNode);
        }
        ASTNode astNode3 = astNode2;
        try {
            if (astNode3 instanceof OCMacroForeignLeafElement) {
                return -1;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        if (astNode3 == null) {
            astNode3 = astNode;
        }
        final TextRange textRange = astNode3.getTextRange();
        try {
            if ((int)pair.second > 0) {
                final int n3 = textRange.getStartOffset() + 1;
                return n3 - (int)pair.second;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        final int n3 = textRange.getEndOffset() - 1;
        return n3 - (int)pair.second;
    }
    
    protected String getLanguagePlaceholderText(@NotNull final ASTNode p0, @NotNull final TextRange p1) {
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
        //    18: ldc             "node"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getLanguagePlaceholderText"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "range"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getLanguagePlaceholderText"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    94: astore_3       
        //    95: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CLASSES:Lcom/intellij/psi/tree/TokenSet;
        //    98: aload_3        
        //    99: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   102: ifne            138
        //   105: getstatic       com/jetbrains/cidr/lang/editor/OCFoldingBuilder.INCLUDE_ELEMENT_TYPES:Ljava/util/Set;
        //   108: aload_3        
        //   109: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   114: ifne            138
        //   117: goto            124
        //   120: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   123: athrow         
        //   124: aload_3        
        //   125: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SYNTHESIZED_PROPERTIES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   128: if_acmpne       145
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: ldc             "..."
        //   140: areturn        
        //   141: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: aload_3        
        //   146: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BLOCK_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   149: if_acmpne       178
        //   152: aload_1        
        //   153: instanceof      Lcom/intellij/psi/PsiDocCommentBase;
        //   156: ifeq            175
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: ldc             "/**...*/"
        //   168: goto            177
        //   171: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: ldc             "/.../"
        //   177: areturn        
        //   178: aload_3        
        //   179: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EOL_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   182: if_acmpne       192
        //   185: ldc             "//..."
        //   187: areturn        
        //   188: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   191: athrow         
        //   192: aload_3        
        //   193: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.NS_ARRAY_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   196: if_acmpne       206
        //   199: ldc             "@[...]"
        //   201: areturn        
        //   202: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   205: athrow         
        //   206: aload_3        
        //   207: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.NS_DICTIONARY_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   210: if_acmpne       220
        //   213: ldc             "@{...}"
        //   215: areturn        
        //   216: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   219: athrow         
        //   220: aload_3        
        //   221: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RAW_STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   224: if_acmpne       234
        //   227: ldc             "\"...\""
        //   229: areturn        
        //   230: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   233: athrow         
        //   234: aload_3        
        //   235: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.BLOCK_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   238: if_acmpne       342
        //   241: aload_1        
        //   242: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   247: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //   250: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockExpression.getReturnTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   255: astore          4
        //   257: aload_1        
        //   258: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   263: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //   266: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockExpression.getParameterList:()Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //   271: astore          5
        //   273: new             Ljava/lang/StringBuilder;
        //   276: dup            
        //   277: invokespecial   java/lang/StringBuilder.<init>:()V
        //   280: ldc             "^"
        //   282: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   285: aload           4
        //   287: ifnull          304
        //   290: aload           4
        //   292: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getTextWithMacros:()Ljava/lang/String;
        //   297: goto            306
        //   300: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   303: athrow         
        //   304: ldc             ""
        //   306: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   309: aload           5
        //   311: ifnull          328
        //   314: aload           5
        //   316: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterList.getTextWithMacros:()Ljava/lang/String;
        //   321: goto            330
        //   324: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   327: athrow         
        //   328: ldc             ""
        //   330: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   333: ldc             "{...}"
        //   335: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   338: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   341: areturn        
        //   342: aload_3        
        //   343: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.LITERAL_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   346: if_acmpeq       363
        //   349: aload_3        
        //   350: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   353: if_acmpne       430
        //   356: goto            363
        //   359: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   362: athrow         
        //   363: aload_2        
        //   364: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   367: aload_1        
        //   368: invokeinterface com/intellij/lang/ASTNode.getStartOffset:()I
        //   373: isub           
        //   374: istore          4
        //   376: aload_1        
        //   377: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //   382: iload           4
        //   384: iload           4
        //   386: aload_2        
        //   387: invokevirtual   com/intellij/openapi/util/TextRange.getLength:()I
        //   390: iadd           
        //   391: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   394: astore          5
        //   396: aload           5
        //   398: ldc             "#>"
        //   400: invokestatic    com/intellij/openapi/util/text/StringUtil.trimEnd:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   403: ldc             "<#"
        //   405: invokestatic    com/intellij/openapi/util/text/StringUtil.trimStart:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   408: astore          6
        //   410: aload           6
        //   412: invokevirtual   java/lang/String.isEmpty:()Z
        //   415: ifeq            427
        //   418: ldc             "{...}"
        //   420: goto            429
        //   423: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   426: athrow         
        //   427: aload           6
        //   429: areturn        
        //   430: aload_3        
        //   431: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MESSAGE_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   434: if_acmpeq       451
        //   437: aload_3        
        //   438: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   441: if_acmpne       500
        //   444: goto            451
        //   447: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   450: athrow         
        //   451: aload_1        
        //   452: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.e:(Lcom/intellij/lang/ASTNode;)Lcom/jetbrains/cidr/lang/psi/OCLocalizedString;
        //   455: astore          4
        //   457: aload           4
        //   459: ifnull          497
        //   462: new             Ljava/lang/StringBuilder;
        //   465: dup            
        //   466: invokespecial   java/lang/StringBuilder.<init>:()V
        //   469: ldc             "@\""
        //   471: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   474: aload           4
        //   476: invokeinterface com/jetbrains/cidr/lang/psi/OCLocalizedString.getValue:()Ljava/lang/String;
        //   481: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   484: ldc             "\""
        //   486: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   489: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   492: areturn        
        //   493: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   496: athrow         
        //   497: goto            544
        //   500: aload_3        
        //   501: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   504: if_acmpne       516
        //   507: aload_1        
        //   508: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Lcom/intellij/lang/ASTNode;)Ljava/lang/String;
        //   511: areturn        
        //   512: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   515: athrow         
        //   516: aload_3        
        //   517: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TEMPLATE_ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   520: if_acmpne       530
        //   523: ldc             "<~>"
        //   525: areturn        
        //   526: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   529: athrow         
        //   530: aload_3        
        //   531: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   534: if_acmpne       544
        //   537: ldc             "..."
        //   539: areturn        
        //   540: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   543: athrow         
        //   544: ldc             "{...}"
        //   546: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  95     117    120    124    Ljava/lang/IllegalArgumentException;
        //  105    131    134    138    Ljava/lang/IllegalArgumentException;
        //  124    141    141    145    Ljava/lang/IllegalArgumentException;
        //  145    159    162    166    Ljava/lang/IllegalArgumentException;
        //  152    171    171    175    Ljava/lang/IllegalArgumentException;
        //  178    188    188    192    Ljava/lang/IllegalArgumentException;
        //  192    202    202    206    Ljava/lang/IllegalArgumentException;
        //  206    216    216    220    Ljava/lang/IllegalArgumentException;
        //  220    230    230    234    Ljava/lang/IllegalArgumentException;
        //  273    300    300    304    Ljava/lang/IllegalArgumentException;
        //  306    324    324    328    Ljava/lang/IllegalArgumentException;
        //  342    356    359    363    Ljava/lang/IllegalArgumentException;
        //  410    423    423    427    Ljava/lang/IllegalArgumentException;
        //  430    444    447    451    Ljava/lang/IllegalArgumentException;
        //  457    493    493    497    Ljava/lang/IllegalArgumentException;
        //  500    512    512    516    Ljava/lang/IllegalArgumentException;
        //  516    526    526    530    Ljava/lang/IllegalArgumentException;
        //  530    540    540    544    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0124:
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
    private static String a(@NotNull final ASTNode p0) {
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
        //    18: ldc             "node"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getTemplateParamsText"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: new             Ljava/lang/StringBuilder;
        //    47: dup            
        //    48: invokespecial   java/lang/StringBuilder.<init>:()V
        //    51: astore_1       
        //    52: aload_0        
        //    53: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.firstLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //    56: astore_2       
        //    57: iconst_0       
        //    58: istore_3       
        //    59: aload_2        
        //    60: ifnull          196
        //    63: aload_2        
        //    64: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    69: astore          4
        //    71: aload_2        
        //    72: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //    75: ifne            187
        //    78: aload_2        
        //    79: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isInessential:(Lcom/intellij/lang/ASTNode;)Z
        //    82: ifne            187
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload           4
        //    94: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TYPENAME_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //    97: if_acmpeq       187
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: aload           4
        //   109: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CLASS_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   112: if_acmpeq       187
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: aload           4
        //   124: instanceof      Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   127: istore          5
        //   129: iload_3        
        //   130: ifne            173
        //   133: iload           5
        //   135: ifne            173
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: aload_1        
        //   146: invokevirtual   java/lang/StringBuilder.length:()I
        //   149: ifeq            173
        //   152: goto            159
        //   155: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   158: athrow         
        //   159: aload_1        
        //   160: bipush          32
        //   162: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   165: pop            
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: iload           5
        //   175: istore_3       
        //   176: aload_1        
        //   177: aload_2        
        //   178: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //   183: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   186: pop            
        //   187: aload_2        
        //   188: aload_0        
        //   189: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.nextLeafInOwner:(Lcom/intellij/lang/ASTNode;Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   192: astore_2       
        //   193: goto            59
        //   196: aload_1        
        //   197: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   200: dup            
        //   201: ifnonnull       238
        //   204: new             Ljava/lang/IllegalStateException;
        //   207: dup            
        //   208: ldc             "@NotNull method %s.%s must not return null"
        //   210: ldc             2
        //   212: anewarray       Ljava/lang/Object;
        //   215: dup            
        //   216: ldc             0
        //   218: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //   220: aastore        
        //   221: dup            
        //   222: ldc             1
        //   224: ldc             "getTemplateParamsText"
        //   226: aastore        
        //   227: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   230: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   233: athrow         
        //   234: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   237: athrow         
        //   238: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  71     85     88     92     Ljava/lang/IllegalArgumentException;
        //  78     100    103    107    Ljava/lang/IllegalArgumentException;
        //  92     115    118    122    Ljava/lang/IllegalArgumentException;
        //  129    138    141    145    Ljava/lang/IllegalArgumentException;
        //  133    152    155    159    Ljava/lang/IllegalArgumentException;
        //  145    166    169    173    Ljava/lang/IllegalArgumentException;
        //  196    234    234    238    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0092:
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
    
    protected boolean isRegionCollapsedByDefault(@NotNull final ASTNode p0) {
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
        //    18: ldc             "node"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isRegionCollapsedByDefault"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: invokestatic    com/jetbrains/cidr/lang/settings/OCCodeFoldingSettings.getInstance:()Lcom/jetbrains/cidr/lang/settings/OCCodeFoldingSettings;
        //    47: astore_2       
        //    48: aload_1        
        //    49: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    54: astore_3       
        //    55: getstatic       com/jetbrains/cidr/lang/editor/OCFoldingBuilder.INCLUDE_ELEMENT_TYPES:Ljava/util/Set;
        //    58: aload_3        
        //    59: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //    64: ifeq            78
        //    67: invokestatic    com/intellij/codeInsight/folding/CodeFoldingSettings.getInstance:()Lcom/intellij/codeInsight/folding/CodeFoldingSettings;
        //    70: getfield        com/intellij/codeInsight/folding/CodeFoldingSettings.COLLAPSE_IMPORTS:Z
        //    73: ireturn        
        //    74: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    77: athrow         
        //    78: aload_3        
        //    79: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    82: if_acmpne       94
        //    85: aload_2        
        //    86: invokevirtual   com/jetbrains/cidr/lang/settings/OCCodeFoldingSettings.isCollapseConditionallyNotCompiled:()Z
        //    89: ireturn        
        //    90: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: aload_3        
        //    95: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BLOCK_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    98: if_acmpeq       115
        //   101: aload_3        
        //   102: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EOL_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   105: if_acmpne       124
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: aload_2        
        //   116: invokevirtual   com/jetbrains/cidr/lang/settings/OCCodeFoldingSettings.isCollapseMultilineComments:()Z
        //   119: ireturn        
        //   120: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   123: athrow         
        //   124: aload_3        
        //   125: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   128: if_acmpeq       145
        //   131: aload_3        
        //   132: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   135: if_acmpne       156
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: invokestatic    com/intellij/codeInsight/folding/CodeFoldingSettings.getInstance:()Lcom/intellij/codeInsight/folding/CodeFoldingSettings;
        //   148: getfield        com/intellij/codeInsight/folding/CodeFoldingSettings.COLLAPSE_METHODS:Z
        //   151: ireturn        
        //   152: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   155: athrow         
        //   156: aload_3        
        //   157: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.INSTANCE_VARIABLES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   160: if_acmpne       172
        //   163: aload_2        
        //   164: invokevirtual   com/jetbrains/cidr/lang/settings/OCCodeFoldingSettings.isCollapseIvars:()Z
        //   167: ireturn        
        //   168: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: aload_3        
        //   173: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SYNTHESIZED_PROPERTIES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   176: if_acmpne       188
        //   179: aload_2        
        //   180: invokevirtual   com/jetbrains/cidr/lang/settings/OCCodeFoldingSettings.isCollapseSynthesizes:()Z
        //   183: ireturn        
        //   184: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: aload_3        
        //   189: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.LITERAL_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   192: if_acmpeq       223
        //   195: aload_3        
        //   196: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MESSAGE_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   199: if_acmpeq       223
        //   202: goto            209
        //   205: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   208: athrow         
        //   209: aload_3        
        //   210: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   213: if_acmpne       232
        //   216: goto            223
        //   219: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   222: athrow         
        //   223: aload_2        
        //   224: invokevirtual   com/jetbrains/cidr/lang/settings/OCCodeFoldingSettings.isCollapseLocalizedStrings:()Z
        //   227: ireturn        
        //   228: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   231: athrow         
        //   232: aload_3        
        //   233: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.BLOCK_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   236: if_acmpne       248
        //   239: aload_2        
        //   240: invokevirtual   com/jetbrains/cidr/lang/settings/OCCodeFoldingSettings.isCollapseBlockExpressions:()Z
        //   243: ireturn        
        //   244: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   247: athrow         
        //   248: aload_3        
        //   249: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   252: if_acmpne       264
        //   255: aload_2        
        //   256: invokevirtual   com/jetbrains/cidr/lang/settings/OCCodeFoldingSettings.isCollapseTemplateParamList:()Z
        //   259: ireturn        
        //   260: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   263: athrow         
        //   264: iconst_0       
        //   265: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  55     74     74     78     Ljava/lang/IllegalArgumentException;
        //  78     90     90     94     Ljava/lang/IllegalArgumentException;
        //  94     108    111    115    Ljava/lang/IllegalArgumentException;
        //  101    120    120    124    Ljava/lang/IllegalArgumentException;
        //  124    138    141    145    Ljava/lang/IllegalArgumentException;
        //  131    152    152    156    Ljava/lang/IllegalArgumentException;
        //  156    168    168    172    Ljava/lang/IllegalArgumentException;
        //  172    184    184    188    Ljava/lang/IllegalArgumentException;
        //  188    202    205    209    Ljava/lang/IllegalArgumentException;
        //  195    216    219    223    Ljava/lang/IllegalArgumentException;
        //  209    228    228    232    Ljava/lang/IllegalArgumentException;
        //  232    244    244    248    Ljava/lang/IllegalArgumentException;
        //  248    260    260    264    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0209:
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
    private static ASTNode a(@NotNull final Document p0, @NotNull final ASTNode p1, @NotNull final List<FoldingDescriptor> p2, @NotNull final Collection<? extends IElementType> p3, @NotNull final Pair<ASTNode, Integer> p4, @NotNull final FoldingCondition p5) {
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
        //    18: ldc             "document"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "collapseConsequentNodesOfSpecifiedType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "node"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "collapseConsequentNodesOfSpecifiedType"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "descriptors"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "collapseConsequentNodesOfSpecifiedType"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_3        
        //   133: ifnonnull       176
        //   136: new             Ljava/lang/IllegalArgumentException;
        //   139: dup            
        //   140: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   142: ldc             3
        //   144: anewarray       Ljava/lang/Object;
        //   147: dup            
        //   148: ldc             0
        //   150: ldc             "elementTypes"
        //   152: aastore        
        //   153: dup            
        //   154: ldc             1
        //   156: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //   158: aastore        
        //   159: dup            
        //   160: ldc             2
        //   162: ldc             "collapseConsequentNodesOfSpecifiedType"
        //   164: aastore        
        //   165: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   168: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   171: athrow         
        //   172: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: aload           4
        //   178: ifnonnull       221
        //   181: new             Ljava/lang/IllegalArgumentException;
        //   184: dup            
        //   185: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   187: ldc             3
        //   189: anewarray       Ljava/lang/Object;
        //   192: dup            
        //   193: ldc             0
        //   195: ldc             "start"
        //   197: aastore        
        //   198: dup            
        //   199: ldc             1
        //   201: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //   203: aastore        
        //   204: dup            
        //   205: ldc             2
        //   207: ldc             "collapseConsequentNodesOfSpecifiedType"
        //   209: aastore        
        //   210: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   213: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   216: athrow         
        //   217: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   220: athrow         
        //   221: aload           5
        //   223: ifnonnull       266
        //   226: new             Ljava/lang/IllegalArgumentException;
        //   229: dup            
        //   230: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   232: ldc             3
        //   234: anewarray       Ljava/lang/Object;
        //   237: dup            
        //   238: ldc             0
        //   240: ldc             "foldingCondition"
        //   242: aastore        
        //   243: dup            
        //   244: ldc             1
        //   246: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //   248: aastore        
        //   249: dup            
        //   250: ldc             2
        //   252: ldc             "collapseConsequentNodesOfSpecifiedType"
        //   254: aastore        
        //   255: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   258: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   261: athrow         
        //   262: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   265: athrow         
        //   266: aload_1        
        //   267: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   272: astore          6
        //   274: aload           6
        //   276: astore          7
        //   278: aload           7
        //   280: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   285: astore          8
        //   287: iconst_0       
        //   288: istore          9
        //   290: aload           6
        //   292: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.isCustomRegionElement:(Lcom/intellij/psi/PsiElement;)Z
        //   295: istore          10
        //   297: aload           6
        //   299: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIncludeDirective;
        //   302: ifeq            328
        //   305: aload           6
        //   307: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIncludeDirective;
        //   310: invokeinterface com/jetbrains/cidr/lang/psi/OCIncludeDirective.isValidDirective:()Z
        //   315: ifne            328
        //   318: goto            325
        //   321: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   324: athrow         
        //   325: iconst_1       
        //   326: istore          9
        //   328: aload           8
        //   330: ifnull          480
        //   333: aload           8
        //   335: ifnull          372
        //   338: goto            345
        //   341: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   344: athrow         
        //   345: aload           8
        //   347: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //   350: ifeq            372
        //   353: goto            360
        //   356: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   359: athrow         
        //   360: aload           8
        //   362: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   367: astore          8
        //   369: goto            333
        //   372: aload           8
        //   374: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIncludeDirective;
        //   377: ifeq            403
        //   380: aload           8
        //   382: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIncludeDirective;
        //   385: invokeinterface com/jetbrains/cidr/lang/psi/OCIncludeDirective.isValidDirective:()Z
        //   390: ifne            403
        //   393: goto            400
        //   396: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   399: athrow         
        //   400: iconst_1       
        //   401: istore          9
        //   403: aload_3        
        //   404: aload           8
        //   406: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   409: invokeinterface java/util/Collection.contains:(Ljava/lang/Object;)Z
        //   414: ifeq            480
        //   417: getstatic       com/jetbrains/cidr/lang/editor/OCFoldingBuilder.$assertionsDisabled:Z
        //   420: ifne            454
        //   423: goto            430
        //   426: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   429: athrow         
        //   430: aload           8
        //   432: ifnonnull       454
        //   435: goto            442
        //   438: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   441: athrow         
        //   442: new             Ljava/lang/AssertionError;
        //   445: dup            
        //   446: invokespecial   java/lang/AssertionError.<init>:()V
        //   449: athrow         
        //   450: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   453: athrow         
        //   454: aload           8
        //   456: astore          7
        //   458: aload           8
        //   460: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   465: astore          8
        //   467: iload           10
        //   469: aload           7
        //   471: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.isCustomRegionElement:(Lcom/intellij/psi/PsiElement;)Z
        //   474: ior            
        //   475: istore          10
        //   477: goto            328
        //   480: aload           7
        //   482: aload           6
        //   484: if_acmpeq       573
        //   487: aload           7
        //   489: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   494: astore          11
        //   496: iload           9
        //   498: ifne            532
        //   501: iload           10
        //   503: ifne            532
        //   506: goto            513
        //   509: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   512: athrow         
        //   513: aload           11
        //   515: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.b:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/Pair;
        //   518: astore          12
        //   520: aload_0        
        //   521: aload_1        
        //   522: aload_2        
        //   523: aload           4
        //   525: aload           12
        //   527: aload           5
        //   529: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/lang/ASTNode;Ljava/util/List;Lcom/intellij/openapi/util/Pair;Lcom/intellij/openapi/util/Pair;Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;)V
        //   532: aload           11
        //   534: dup            
        //   535: ifnonnull       572
        //   538: new             Ljava/lang/IllegalStateException;
        //   541: dup            
        //   542: ldc             "@NotNull method %s.%s must not return null"
        //   544: ldc             2
        //   546: anewarray       Ljava/lang/Object;
        //   549: dup            
        //   550: ldc             0
        //   552: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //   554: aastore        
        //   555: dup            
        //   556: ldc             1
        //   558: ldc             "collapseConsequentNodesOfSpecifiedType"
        //   560: aastore        
        //   561: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   564: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   567: athrow         
        //   568: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   571: athrow         
        //   572: areturn        
        //   573: aload_1        
        //   574: dup            
        //   575: ifnonnull       612
        //   578: new             Ljava/lang/IllegalStateException;
        //   581: dup            
        //   582: ldc             "@NotNull method %s.%s must not return null"
        //   584: ldc             2
        //   586: anewarray       Ljava/lang/Object;
        //   589: dup            
        //   590: ldc             0
        //   592: ldc             "com/jetbrains/cidr/lang/editor/OCFoldingBuilder"
        //   594: aastore        
        //   595: dup            
        //   596: ldc             1
        //   598: ldc             "collapseConsequentNodesOfSpecifiedType"
        //   600: aastore        
        //   601: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   604: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   607: athrow         
        //   608: invokestatic    com/jetbrains/cidr/lang/editor/OCFoldingBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   611: athrow         
        //   612: areturn        
        //    Signature:
        //  (Lcom/intellij/openapi/editor/Document;Lcom/intellij/lang/ASTNode;Ljava/util/List<Lcom/intellij/lang/folding/FoldingDescriptor;>;Ljava/util/Collection<+Lcom/intellij/psi/tree/IElementType;>;Lcom/intellij/openapi/util/Pair<Lcom/intellij/lang/ASTNode;Ljava/lang/Integer;>;Lcom/jetbrains/cidr/lang/editor/OCFoldingBuilder$FoldingCondition;)Lcom/intellij/lang/ASTNode;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    172    172    176    Ljava/lang/IllegalArgumentException;
        //  176    217    217    221    Ljava/lang/IllegalArgumentException;
        //  221    262    262    266    Ljava/lang/IllegalArgumentException;
        //  297    318    321    325    Ljava/lang/IllegalArgumentException;
        //  328    338    341    345    Ljava/lang/IllegalArgumentException;
        //  333    353    356    360    Ljava/lang/IllegalArgumentException;
        //  372    393    396    400    Ljava/lang/IllegalArgumentException;
        //  403    423    426    430    Ljava/lang/IllegalArgumentException;
        //  417    435    438    442    Ljava/lang/IllegalArgumentException;
        //  430    450    450    454    Ljava/lang/IllegalArgumentException;
        //  496    506    509    513    Ljava/lang/IllegalArgumentException;
        //  532    568    568    572    Ljava/lang/IllegalArgumentException;
        //  573    608    608    612    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0333:
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
    
    public boolean isDumbAware() {
        return false;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCFoldingBuilder.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        OCFoldingBuilder.INCLUDE_ELEMENT_TYPES = (Set<? extends IElementType>)ContainerUtil.set((Object[])new OCElementType[] { OCElementTypes.IMPORT_DIRECTIVE, OCElementTypes.IMPORT_MODULE_STATEMENT });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    enum FoldingCondition
    {
        LONGER_THAN_5CHARS_OR_NOT_EMPTY_SEVERAL_LINES, 
        NOT_EMPTY_SEVERAL_LINES, 
        ALWAYS;
    }
}
