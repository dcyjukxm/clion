// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil;
import com.intellij.psi.impl.source.tree.TreeElement;
import com.intellij.openapi.util.UserDataHolder;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.openapi.util.TextRange;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.Key;

public class OCMultilineNodeFormatter
{
    public static final Key<Pair<Boolean, OCPreprocessorFormatterData.OCIndentInfo>> PREPROCESSOR_INFO;
    public static final Key<Integer> CODE_INDENT;
    public static final Key<Boolean> AT_FIRST_COL_HINT;
    public static final int DIRTY_SPACE_COUNT = 120;
    @NotNull
    private CodeStyleSettings mySettings;
    
    public OCMultilineNodeFormatter(@NotNull final CodeStyleSettings mySettings) {
        if (mySettings == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "<init>"));
        }
        this.mySettings = mySettings;
    }
    
    @NotNull
    public static OCMultilineNodeFormatter create(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "create"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCMultilineNodeFormatter ocMultilineNodeFormatter;
        try {
            ocMultilineNodeFormatter = new OCMultilineNodeFormatter(CodeStyleSettingsManager.getInstance(project).getCurrentSettings());
            if (ocMultilineNodeFormatter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "create"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ocMultilineNodeFormatter;
    }
    
    @NotNull
    public OCCodeStyleSettings getOCSettings() {
        OCCodeStyleSettings ocCodeStyleSettings;
        try {
            ocCodeStyleSettings = (OCCodeStyleSettings)this.mySettings.getCustomSettings((Class)OCCodeStyleSettings.class);
            if (ocCodeStyleSettings == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "getOCSettings"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocCodeStyleSettings;
    }
    
    public TextRange shiftIndentInsideRangeInDocument(@NotNull final Document document, @NotNull final ASTNode astNode, @NotNull final TextRange textRange, final int n) {
        try {
            if (document == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "document", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "shiftIndentInsideRangeInDocument"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "shiftIndentInsideRangeInDocument"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "textRange", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "shiftIndentInsideRangeInDocument"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return this.a(astNode, textRange, n, new DocumentModifier() {
            @Override
            public TextRange change(final boolean b, final String s, final String s2) {
                document.replaceString(textRange.getStartOffset(), textRange.getEndOffset(), (CharSequence)s);
                return new TextRange(textRange.getStartOffset(), textRange.getEndOffset() + s.length() - s2.length());
            }
        });
    }
    
    public TextRange shiftIndentInsideRangeInPsiTree(@NotNull final ASTNode astNode, @NotNull final TextRange textRange, final int n) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "shiftIndentInsideRangeInPsiTree"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "textRange", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "shiftIndentInsideRangeInPsiTree"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.a(astNode, textRange, n, new DocumentModifier() {
            @Override
            public TextRange change(final boolean b, final String s, final String s2) {
                final PsiElement psi = astNode.getPsi();
                final ASTNode treeParent = astNode.getTreeParent();
                if (treeParent != null) {
                    PsiElement psiElement;
                    if (b) {
                        psiElement = OCElementFactory.codeFragment("\n#if 0\n" + s + "\n#endif", psi.getProject(), psi, false, false).getFirstChild().getNextSibling().getNextSibling();
                    }
                    else {
                        psiElement = OCElementFactory.codeFragment(s, psi.getProject(), psi, false, false).getFirstChild();
                    }
                    final ASTNode node = psiElement.getNode();
                    if (node != null) {
                        if (b) {
                            final Pair pair = (Pair)OCMultilineNodeFormatter.PREPROCESSOR_INFO.get((UserDataHolder)astNode);
                            if (pair != null && pair.first != null) {
                                OCMultilineNodeFormatter.AT_FIRST_COL_HINT.set((UserDataHolder)node, pair.first);
                            }
                            final int n = s.length() - node.getTextLength();
                            if (n != 0) {
                                CodeEditUtil.setOldIndentation((TreeElement)node, -n);
                            }
                            else {
                                OCElementFactory.initIndentFromContext(psi, psiElement);
                            }
                        }
                        else {
                            OCElementFactory.initIndentFromContext(psi, psiElement);
                        }
                        CodeEditUtil.replaceChild(treeParent, astNode, node);
                        return new TextRange(textRange.getStartOffset(), textRange.getStartOffset() + node.getTextLength());
                    }
                }
                return textRange;
            }
        });
    }
    
    private TextRange a(@NotNull final ASTNode astNode, @NotNull final TextRange textRange, final int n, @NotNull final DocumentModifier documentModifier) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "shiftIndentInsideRangeEx"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "textRange", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "shiftIndentInsideRangeEx"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (documentModifier == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modifier", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "shiftIndentInsideRangeEx"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final IElementType elementType = OCElementUtil.getElementType(astNode);
        boolean b = false;
        Label_0156: {
            try {
                if (elementType == OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT) {
                    b = true;
                    break Label_0156;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            b = false;
        }
        final boolean b2 = b;
        Label_0286: {
            Label_0178: {
                try {
                    if (elementType == OCTokenTypes.BLOCK_COMMENT) {
                        break Label_0178;
                    }
                    final boolean b3 = b2;
                    if (b3) {
                        break Label_0178;
                    }
                    return textRange;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    final boolean b3 = b2;
                    if (!b3) {
                        return textRange;
                    }
                    if (!b2) {
                        break Label_0286;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            final Pair pair = (Pair)OCMultilineNodeFormatter.PREPROCESSOR_INFO.get((UserDataHolder)astNode);
            final Integer n2 = (Integer)OCMultilineNodeFormatter.CODE_INDENT.get((UserDataHolder)astNode);
            try {
                if (pair == null || n2 == null) {
                    return textRange;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
            final String text = astNode.getText();
            final String directiveIndentCorrection = this.directiveIndentCorrection((Pair<Boolean, OCPreprocessorFormatterData.OCIndentInfo>)pair, n2, n, false, text);
            try {
                if (!directiveIndentCorrection.equals(text)) {
                    return documentModifier.change(true, directiveIndentCorrection, text);
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
            return textRange;
        }
        final String text2 = astNode.getText();
        final String a = this.a(n, text2);
        try {
            if (!a.equals(text2)) {
                return documentModifier.change(false, a, text2);
            }
        }
        catch (IllegalArgumentException ex9) {
            throw a(ex9);
        }
        return textRange;
    }
    
    @NotNull
    private String a(final int n, @NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "leafText", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "getShiftedLeafText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int length = s.length();
        int i = 0;
        int n2 = 1;
        final StringBuilder sb = new StringBuilder();
    Label_0181_Outer:
        while (i < length) {
            char c = ' ';
            final StringBuilder sb2 = new StringBuilder();
            while (i < length) {
                final char c2 = c = s.charAt(i);
                try {
                    if (a(c2)) {
                        ++i;
                        sb2.append(c);
                        continue Label_0181_Outer;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                break;
            }
            while (true) {
                try {
                    if (n2 != 0) {
                        if (sb2.length() <= 0) {
                            break Label_0181;
                        }
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                Label_0142: {
                    break Label_0142;
                    while (i < length) {
                        final char c3 = c = s.charAt(i);
                        try {
                            if (c3 != '\n') {
                                ++i;
                                sb.append(c);
                                continue Label_0181_Outer;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                        break;
                    }
                    if (c != '\n') {
                        continue Label_0181_Outer;
                    }
                    ++i;
                    sb.append(c);
                    n2 = 0;
                    continue Label_0181_Outer;
                }
                final int b = this.b(sb2.toString());
                try {
                    if (b + n > 0) {
                        sb.append(this.getStringIndent(b + n));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                continue;
            }
        }
        String string;
        try {
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "getShiftedLeafText"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return string;
    }
    
    public String directiveIndentCorrection(@NotNull final Pair<Boolean, OCPreprocessorFormatterData.OCIndentInfo> p0, final int p1, final int p2, final boolean p3, @NotNull final String p4) {
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
        //    18: ldc             "info"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "directiveIndentCorrection"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload           5
        //    46: ifnonnull       89
        //    49: new             Ljava/lang/IllegalArgumentException;
        //    52: dup            
        //    53: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    55: ldc             3
        //    57: anewarray       Ljava/lang/Object;
        //    60: dup            
        //    61: ldc             0
        //    63: ldc             "leafText"
        //    65: aastore        
        //    66: dup            
        //    67: ldc             1
        //    69: ldc             "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter"
        //    71: aastore        
        //    72: dup            
        //    73: ldc             2
        //    75: ldc             "directiveIndentCorrection"
        //    77: aastore        
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: aload_0        
        //    90: invokevirtual   com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.getOCSettings:()Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //    93: astore          6
        //    95: aload           5
        //    97: invokestatic    com/intellij/openapi/util/text/StringUtil.splitByLinesKeepSeparators:(Ljava/lang/String;)[Ljava/lang/String;
        //   100: astore          7
        //   102: iinc            3, -120
        //   105: new             Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData;
        //   108: dup            
        //   109: invokespecial   com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.<init>:()V
        //   112: astore          8
        //   114: aload           8
        //   116: aload_1        
        //   117: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   120: checkcast       Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OCIndentInfo;
        //   123: invokevirtual   com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.setIndentAtLevel:(Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OCIndentInfo;)V
        //   126: aload           8
        //   128: dup            
        //   129: getfield        com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.directiveNestLevel:I
        //   132: iconst_1       
        //   133: iadd           
        //   134: putfield        com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.directiveNestLevel:I
        //   137: new             Ljava/lang/StringBuilder;
        //   140: dup            
        //   141: aload           5
        //   143: invokevirtual   java/lang/String.length:()I
        //   146: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   149: astore          9
        //   151: aload           7
        //   153: arraylength    
        //   154: anewarray       Lcom/intellij/openapi/util/Pair;
        //   157: astore          10
        //   159: iconst_0       
        //   160: istore          11
        //   162: iconst_m1      
        //   163: istore          12
        //   165: iconst_m1      
        //   166: istore          13
        //   168: iconst_0       
        //   169: istore          14
        //   171: iconst_1       
        //   172: istore          15
        //   174: iconst_0       
        //   175: istore          16
        //   177: iconst_0       
        //   178: istore          17
        //   180: iconst_0       
        //   181: istore          18
        //   183: iconst_0       
        //   184: istore          19
        //   186: aload           7
        //   188: astore          20
        //   190: aload           20
        //   192: arraylength    
        //   193: istore          21
        //   195: iconst_0       
        //   196: istore          22
        //   198: iload           22
        //   200: iload           21
        //   202: if_icmpge       755
        //   205: aload           20
        //   207: iload           22
        //   209: aaload         
        //   210: astore          23
        //   212: aload           23
        //   214: invokevirtual   java/lang/String.length:()I
        //   217: istore          24
        //   219: iload           19
        //   221: iload           12
        //   223: if_icmplt       361
        //   226: aload           23
        //   228: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.c:(Ljava/lang/String;)Ljava/lang/String;
        //   231: ldc             "#"
        //   233: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   236: ifeq            361
        //   239: goto            246
        //   242: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   245: athrow         
        //   246: invokestatic    com/jetbrains/cidr/lang/lexer/OCLexerWithDirectives.createDefault:()Lcom/jetbrains/cidr/lang/lexer/OCLexerWithDirectives;
        //   249: astore          25
        //   251: aload           25
        //   253: aload           23
        //   255: invokevirtual   com/intellij/lexer/Lexer.start:(Ljava/lang/CharSequence;)V
        //   258: aload           25
        //   260: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   263: astore          26
        //   265: aload           26
        //   267: ifnonnull       277
        //   270: goto            358
        //   273: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: getstatic       com/jetbrains/cidr/lang/formatting/OCFormatterUtil.FORMAT_DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //   280: aload           26
        //   282: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   285: ifeq            350
        //   288: iconst_1       
        //   289: istore          11
        //   291: aload           10
        //   293: iload           18
        //   295: aload           8
        //   297: aload           26
        //   299: aload           6
        //   301: iload           18
        //   303: ifne            323
        //   306: aload_1        
        //   307: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   310: checkcast       Ljava/lang/Boolean;
        //   313: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   316: goto            343
        //   319: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   322: athrow         
        //   323: aload           23
        //   325: iconst_0       
        //   326: invokevirtual   java/lang/String.charAt:(I)C
        //   329: bipush          35
        //   331: if_icmpne       342
        //   334: iconst_1       
        //   335: goto            343
        //   338: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   341: athrow         
        //   342: iconst_0       
        //   343: invokevirtual   com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.getIndents:(Lcom/intellij/psi/tree/IElementType;Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;Z)Lcom/intellij/openapi/util/Pair;
        //   346: aastore        
        //   347: goto            358
        //   350: aload           25
        //   352: invokevirtual   com/intellij/lexer/Lexer.advance:()V
        //   355: goto            258
        //   358: goto            697
        //   361: iload           14
        //   363: ifne            677
        //   366: iload           19
        //   368: iload           13
        //   370: if_icmplt       502
        //   373: goto            380
        //   376: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   379: athrow         
        //   380: iload           15
        //   382: ifeq            476
        //   385: goto            392
        //   388: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   391: athrow         
        //   392: aload           23
        //   394: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/String;)Z
        //   397: ifne            476
        //   400: goto            407
        //   403: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   406: athrow         
        //   407: iload           16
        //   409: ifeq            459
        //   412: goto            419
        //   415: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   418: athrow         
        //   419: aload_0        
        //   420: aload           23
        //   422: invokevirtual   com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.indent:(Ljava/lang/String;)I
        //   425: istore          25
        //   427: iload           25
        //   429: ifne            440
        //   432: iconst_1       
        //   433: goto            441
        //   436: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   439: athrow         
        //   440: iconst_0       
        //   441: istore          17
        //   443: iload_2        
        //   444: iload           25
        //   446: isub           
        //   447: istore_3       
        //   448: aload           9
        //   450: aload           23
        //   452: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   455: pop            
        //   456: goto            470
        //   459: aload           9
        //   461: aload           23
        //   463: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.c:(Ljava/lang/String;)Ljava/lang/String;
        //   466: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   469: pop            
        //   470: iconst_0       
        //   471: istore          15
        //   473: goto            484
        //   476: aload           9
        //   478: aload           23
        //   480: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   483: pop            
        //   484: aload           10
        //   486: iload           18
        //   488: getstatic       com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OffsetType.CODE_RELATIVE_PREV:Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OffsetType;
        //   491: iconst_0       
        //   492: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   495: invokestatic    com/intellij/openapi/util/Pair.pair:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   498: aastore        
        //   499: goto            517
        //   502: aload           10
        //   504: iload           18
        //   506: getstatic       com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OffsetType.CODE_AS_IS:Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OffsetType;
        //   509: iconst_0       
        //   510: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   513: invokestatic    com/intellij/openapi/util/Pair.pair:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   516: aastore        
        //   517: iload           19
        //   519: iload           24
        //   521: iadd           
        //   522: istore          25
        //   524: iload           25
        //   526: iload           12
        //   528: if_icmple       674
        //   531: iload           19
        //   533: iload           12
        //   535: invokestatic    java/lang/Math.max:(II)I
        //   538: istore          26
        //   540: invokestatic    com/jetbrains/cidr/lang/lexer/OCLexerWithDirectives.createDefault:()Lcom/jetbrains/cidr/lang/lexer/OCLexerWithDirectives;
        //   543: astore          27
        //   545: aload           27
        //   547: aload           5
        //   549: iload           26
        //   551: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   554: invokevirtual   com/intellij/lexer/Lexer.start:(Ljava/lang/CharSequence;)V
        //   557: aload           27
        //   559: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   562: astore          28
        //   564: iload           26
        //   566: aload           27
        //   568: invokevirtual   com/intellij/lexer/Lexer.getTokenStart:()I
        //   571: iadd           
        //   572: istore          29
        //   574: aload           28
        //   576: ifnull          674
        //   579: iload           29
        //   581: iload           25
        //   583: if_icmplt       600
        //   586: goto            593
        //   589: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   592: athrow         
        //   593: goto            674
        //   596: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   599: athrow         
        //   600: getstatic       com/jetbrains/cidr/lang/formatting/OCFormatterUtil.FORMAT_PROBLEM_LEAFS_IN_NONCOMPILED:Lcom/intellij/psi/tree/TokenSet;
        //   603: aload           28
        //   605: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   608: ifeq            666
        //   611: iload           26
        //   613: aload           27
        //   615: invokevirtual   com/intellij/lexer/Lexer.getTokenEnd:()I
        //   618: iadd           
        //   619: istore          12
        //   621: aload           28
        //   623: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BLOCK_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   626: if_acmpne       662
        //   629: aload           5
        //   631: iload           19
        //   633: iload           29
        //   635: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   638: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.c:(Ljava/lang/String;)Ljava/lang/String;
        //   641: invokevirtual   java/lang/String.isEmpty:()Z
        //   644: ifeq            662
        //   647: goto            654
        //   650: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   653: athrow         
        //   654: iconst_m1      
        //   655: goto            664
        //   658: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   661: athrow         
        //   662: iload           12
        //   664: istore          13
        //   666: aload           27
        //   668: invokevirtual   com/intellij/lexer/Lexer.advance:()V
        //   671: goto            557
        //   674: goto            697
        //   677: aload           10
        //   679: iload           18
        //   681: getstatic       com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OffsetType.DIRECTIVE_ABSOLUTE:Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OffsetType;
        //   684: aload_0        
        //   685: aload           23
        //   687: invokevirtual   com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.indent:(Ljava/lang/String;)I
        //   690: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   693: invokestatic    com/intellij/openapi/util/Pair.pair:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   696: aastore        
        //   697: iload           18
        //   699: ifne            706
        //   702: iload           11
        //   704: istore          16
        //   706: iload           11
        //   708: ifeq            736
        //   711: aload           23
        //   713: ldc             "\\\n"
        //   715: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   718: ifeq            736
        //   721: goto            728
        //   724: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   727: athrow         
        //   728: iconst_1       
        //   729: goto            737
        //   732: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   735: athrow         
        //   736: iconst_0       
        //   737: istore          14
        //   739: iinc            18, 1
        //   742: iload           19
        //   744: iload           24
        //   746: iadd           
        //   747: istore          19
        //   749: iinc            22, 1
        //   752: goto            198
        //   755: aload_0        
        //   756: iload_3        
        //   757: aload           9
        //   759: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   762: invokespecial   com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(ILjava/lang/String;)Ljava/lang/String;
        //   765: astore          20
        //   767: iload           17
        //   769: ifeq            797
        //   772: new             Ljava/lang/StringBuilder;
        //   775: dup            
        //   776: invokespecial   java/lang/StringBuilder.<init>:()V
        //   779: aload_0        
        //   780: iload_3        
        //   781: invokevirtual   com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.getStringIndent:(I)Ljava/lang/String;
        //   784: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   787: aload           20
        //   789: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   792: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   795: astore          20
        //   797: aload           20
        //   799: invokestatic    com/intellij/openapi/util/text/StringUtil.splitByLinesKeepSeparators:(Ljava/lang/String;)[Ljava/lang/String;
        //   802: astore          21
        //   804: iconst_0       
        //   805: istore          22
        //   807: iload_2        
        //   808: istore          23
        //   810: new             Ljava/lang/StringBuilder;
        //   813: dup            
        //   814: aload           5
        //   816: invokevirtual   java/lang/String.length:()I
        //   819: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   822: astore          24
        //   824: iconst_0       
        //   825: istore          25
        //   827: iload           25
        //   829: aload           7
        //   831: arraylength    
        //   832: if_icmpge       1149
        //   835: getstatic       com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter$3.$SwitchMap$com$jetbrains$cidr$lang$formatting$OCPreprocessorFormatterData$OffsetType:[I
        //   838: aload           10
        //   840: iload           25
        //   842: aaload         
        //   843: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   846: checkcast       Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OffsetType;
        //   849: invokevirtual   com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OffsetType.ordinal:()I
        //   852: iaload         
        //   853: tableswitch {
        //                2: 888
        //                3: 933
        //                4: 971
        //                5: 1132
        //          default: 1132
        //        }
        //   884: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   887: athrow         
        //   888: aload           24
        //   890: aload_0        
        //   891: iload           23
        //   893: aload           10
        //   895: iload           25
        //   897: aaload         
        //   898: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   901: checkcast       Ljava/lang/Integer;
        //   904: invokevirtual   java/lang/Integer.intValue:()I
        //   907: iadd           
        //   908: invokevirtual   com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.getStringIndent:(I)Ljava/lang/String;
        //   911: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   914: aload           7
        //   916: iload           25
        //   918: aaload         
        //   919: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.c:(Ljava/lang/String;)Ljava/lang/String;
        //   922: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   925: pop            
        //   926: goto            1143
        //   929: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   932: athrow         
        //   933: aload           24
        //   935: aload_0        
        //   936: aload           10
        //   938: iload           25
        //   940: aaload         
        //   941: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   944: checkcast       Ljava/lang/Integer;
        //   947: invokevirtual   java/lang/Integer.intValue:()I
        //   950: invokevirtual   com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.getStringIndent:(I)Ljava/lang/String;
        //   953: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   956: aload           7
        //   958: iload           25
        //   960: aaload         
        //   961: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.c:(Ljava/lang/String;)Ljava/lang/String;
        //   964: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   967: pop            
        //   968: goto            1143
        //   971: iload           22
        //   973: aload           21
        //   975: arraylength    
        //   976: if_icmpge       1143
        //   979: iload           22
        //   981: ifne            1015
        //   984: goto            991
        //   987: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   990: athrow         
        //   991: iload           22
        //   993: ifne            1023
        //   996: goto            1003
        //   999: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1002: athrow         
        //  1003: iload           16
        //  1005: ifeq            1023
        //  1008: goto            1015
        //  1011: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1014: athrow         
        //  1015: iconst_1       
        //  1016: goto            1024
        //  1019: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1022: athrow         
        //  1023: iconst_0       
        //  1024: istore          26
        //  1026: aload           21
        //  1028: iload           22
        //  1030: iinc            22, 1
        //  1033: aaload         
        //  1034: astore          27
        //  1036: aload           27
        //  1038: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/String;)Z
        //  1041: istore          28
        //  1043: iload           26
        //  1045: ifeq            1068
        //  1048: iload           28
        //  1050: ifne            1068
        //  1053: goto            1060
        //  1056: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1059: athrow         
        //  1060: aload_0        
        //  1061: aload           27
        //  1063: invokevirtual   com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.indent:(Ljava/lang/String;)I
        //  1066: istore          23
        //  1068: iload           26
        //  1070: ifeq            1121
        //  1073: iload           28
        //  1075: ifeq            1121
        //  1078: goto            1085
        //  1081: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1084: athrow         
        //  1085: iload           4
        //  1087: ifeq            1121
        //  1090: goto            1097
        //  1093: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1096: athrow         
        //  1097: aload           24
        //  1099: aload_0        
        //  1100: iload           23
        //  1102: invokevirtual   com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.getStringIndent:(I)Ljava/lang/String;
        //  1105: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1108: ldc             "\n"
        //  1110: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1113: pop            
        //  1114: goto            1129
        //  1117: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1120: athrow         
        //  1121: aload           24
        //  1123: aload           27
        //  1125: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1128: pop            
        //  1129: goto            1143
        //  1132: aload           24
        //  1134: aload           7
        //  1136: iload           25
        //  1138: aaload         
        //  1139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1142: pop            
        //  1143: iinc            25, 1
        //  1146: goto            827
        //  1149: aload           24
        //  1151: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1154: areturn        
        //    Signature:
        //  (Lcom/intellij/openapi/util/Pair<Ljava/lang/Boolean;Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OCIndentInfo;>;IIZLjava/lang/String;)Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     85     85     89     Ljava/lang/IllegalArgumentException;
        //  219    239    242    246    Ljava/lang/IllegalArgumentException;
        //  265    273    273    277    Ljava/lang/IllegalArgumentException;
        //  291    319    319    323    Ljava/lang/IllegalArgumentException;
        //  323    338    338    342    Ljava/lang/IllegalArgumentException;
        //  361    373    376    380    Ljava/lang/IllegalArgumentException;
        //  366    385    388    392    Ljava/lang/IllegalArgumentException;
        //  380    400    403    407    Ljava/lang/IllegalArgumentException;
        //  392    412    415    419    Ljava/lang/IllegalArgumentException;
        //  427    436    436    440    Ljava/lang/IllegalArgumentException;
        //  574    586    589    593    Ljava/lang/IllegalArgumentException;
        //  579    596    596    600    Ljava/lang/IllegalArgumentException;
        //  621    647    650    654    Ljava/lang/IllegalArgumentException;
        //  629    658    658    662    Ljava/lang/IllegalArgumentException;
        //  706    721    724    728    Ljava/lang/IllegalArgumentException;
        //  711    732    732    736    Ljava/lang/IllegalArgumentException;
        //  827    884    884    888    Ljava/lang/IllegalArgumentException;
        //  835    929    929    933    Ljava/lang/IllegalArgumentException;
        //  971    984    987    991    Ljava/lang/IllegalArgumentException;
        //  979    996    999    1003   Ljava/lang/IllegalArgumentException;
        //  991    1008   1011   1015   Ljava/lang/IllegalArgumentException;
        //  1003   1019   1019   1023   Ljava/lang/IllegalArgumentException;
        //  1043   1053   1056   1060   Ljava/lang/IllegalArgumentException;
        //  1068   1078   1081   1085   Ljava/lang/IllegalArgumentException;
        //  1073   1090   1093   1097   Ljava/lang/IllegalArgumentException;
        //  1085   1117   1117   1121   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0380:
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
    
    private static boolean a(final String s) {
        return StringUtil.trim(s).isEmpty();
    }
    
    @Nullable
    public static String indentAtOffsetInNoncompiledCode(@NotNull final PsiElement psiElement, final String s, final int n) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nonCompiled", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "indentAtOffsetInNoncompiledCode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ASTNode node = psiElement.getNode();
        final OCMultilineNodeFormatter create = create(psiElement.getProject());
        Integer value = (Integer)OCMultilineNodeFormatter.CODE_INDENT.get((UserDataHolder)node);
        if (value == null) {
            final String lineIndent = CodeStyleManager.getInstance(psiElement.getProject()).getLineIndent(psiElement.getContainingFile(), psiElement.getTextOffset());
            if (lineIndent != null) {
                OCMultilineNodeFormatter.CODE_INDENT.set((UserDataHolder)node, (Object)(value = Math.max(0, create.indent(lineIndent))));
            }
            else {
                OCLog.LOG.error("Wrong formatting approach in non-compiled code");
            }
        }
        final Pair pair = (Pair)OCMultilineNodeFormatter.PREPROCESSOR_INFO.get((UserDataHolder)node);
        try {
            if (pair == null || value == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final String directiveIndentCorrection = create.directiveIndentCorrection((Pair<Boolean, OCPreprocessorFormatterData.OCIndentInfo>)pair, value, 120, true, s);
        final int offsetToLineNumber = StringUtil.offsetToLineNumber((CharSequence)s, n - node.getStartOffset());
        final String[] splitByLinesKeepSeparators = StringUtil.splitByLinesKeepSeparators(directiveIndentCorrection);
        try {
            if (offsetToLineNumber < splitByLinesKeepSeparators.length) {
                return getStringIndent(splitByLinesKeepSeparators[offsetToLineNumber]);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    @Nullable
    public static String getIndentAtPosition(@NotNull final HighlighterIterator highlighterIterator) {
        try {
            if (highlighterIterator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "it", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "getIndentAtPosition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            highlighterIterator.retreat();
            if (highlighterIterator.atEnd()) {
                return "";
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        if (highlighterIterator.getTokenType() == TokenType.WHITE_SPACE) {
            final String substring = highlighterIterator.getDocument().getText().substring(highlighterIterator.getStart(), highlighterIterator.getEnd());
            Label_0130: {
                try {
                    if (highlighterIterator.getStart() == 0) {
                        break Label_0130;
                    }
                    final String s = substring;
                    final int n = 10;
                    final int n2 = s.indexOf(n);
                    if (n2 >= 0) {
                        break Label_0130;
                    }
                    return null;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final String s = substring;
                    final int n = 10;
                    final int n2 = s.indexOf(n);
                    if (n2 >= 0) {
                        return (String)splitIndent(substring).second;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
        }
        return null;
    }
    
    @Nullable
    public static String getDirectiveIndentFromAnchor(@NotNull final HighlighterIterator p0, @NotNull final PsiFile p1, final boolean p2) {
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
        //    18: ldc             "it"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getDirectiveIndentFromAnchor"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "file"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getDirectiveIndentFromAnchor"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: iconst_0       
        //    89: istore_3       
        //    90: aload_0        
        //    91: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //    96: ifne            195
        //    99: getstatic       com/jetbrains/cidr/lang/formatting/OCFormatterUtil.FORMAT_DIRECTIVES_INCREASE_INDENT:Lcom/intellij/psi/tree/TokenSet;
        //   102: aload_0        
        //   103: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   108: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   111: ifeq            164
        //   114: goto            121
        //   117: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   120: athrow         
        //   121: iload_3        
        //   122: ifne            139
        //   125: goto            132
        //   128: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: goto            195
        //   135: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IF_DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //   142: aload_0        
        //   143: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   148: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   151: ifeq            186
        //   154: iinc            3, 1
        //   157: goto            186
        //   160: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ENDIF_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   167: aload_0        
        //   168: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   173: if_acmpne       186
        //   176: iinc            3, -1
        //   179: goto            186
        //   182: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   185: athrow         
        //   186: aload_0        
        //   187: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.retreat:()V
        //   192: goto            90
        //   195: aload_0        
        //   196: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //   201: ifne            312
        //   204: iload_2        
        //   205: ifeq            256
        //   208: goto            215
        //   211: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   214: athrow         
        //   215: aload_1        
        //   216: iconst_1       
        //   217: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getGuardIfndef:(Lcom/intellij/psi/PsiFile;Z)Lcom/jetbrains/cidr/lang/psi/OCDirective;
        //   220: astore          4
        //   222: aload           4
        //   224: ifnull          256
        //   227: aload           4
        //   229: invokeinterface com/jetbrains/cidr/lang/psi/OCDirective.getTextOffset:()I
        //   234: aload_0        
        //   235: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getStart:()I
        //   240: if_icmpne       256
        //   243: goto            250
        //   246: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   249: athrow         
        //   250: aconst_null    
        //   251: areturn        
        //   252: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   255: athrow         
        //   256: aload_0        
        //   257: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.getIndentAtPosition:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;)Ljava/lang/String;
        //   260: astore          4
        //   262: aload           4
        //   264: ifnull          312
        //   267: aload_1        
        //   268: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   273: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.create:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter;
        //   276: astore          5
        //   278: aload           5
        //   280: aload           5
        //   282: aload           4
        //   284: invokevirtual   com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.indent:(Ljava/lang/String;)I
        //   287: iload_2        
        //   288: ifeq            306
        //   291: aload           5
        //   293: invokevirtual   com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.getOCSettings:()Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   296: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.INDENT_PREPROCESSOR_DIRECTIVE:I
        //   299: goto            307
        //   302: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   305: athrow         
        //   306: iconst_0       
        //   307: iadd           
        //   308: invokevirtual   com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.getStringIndent:(I)Ljava/lang/String;
        //   311: areturn        
        //   312: aconst_null    
        //   313: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  90     114    117    121    Ljava/lang/IllegalArgumentException;
        //  99     125    128    132    Ljava/lang/IllegalArgumentException;
        //  121    135    135    139    Ljava/lang/IllegalArgumentException;
        //  139    160    160    164    Ljava/lang/IllegalArgumentException;
        //  164    179    182    186    Ljava/lang/IllegalArgumentException;
        //  195    208    211    215    Ljava/lang/IllegalArgumentException;
        //  222    243    246    250    Ljava/lang/IllegalArgumentException;
        //  227    252    252    256    Ljava/lang/IllegalArgumentException;
        //  278    302    302    306    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0121:
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
    
    public int indent(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "line", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "indent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.b(getStringIndent(s));
    }
    
    private int b(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "normalizedTabLength"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return s.replaceAll("\t", StringUtil.repeatSymbol(' ', this.a())).length();
    }
    
    private int a() {
        final CommonCodeStyleSettings.IndentOptions indentOptions = this.mySettings.getCommonSettings((Language)OCLanguage.getInstance()).getIndentOptions();
        try {
            if (indentOptions == null) {
                return 4;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return indentOptions.TAB_SIZE;
    }
    
    @NotNull
    public String getStringIndent(final int n) {
        final String repeatSymbol = StringUtil.repeatSymbol(' ', n);
        final CommonCodeStyleSettings.IndentOptions indentOptions = this.mySettings.getCommonSettings((Language)OCLanguage.getInstance()).getIndentOptions();
        String s = null;
        Label_0062: {
            Label_0039: {
                try {
                    if (indentOptions == null) {
                        break Label_0039;
                    }
                    final CommonCodeStyleSettings.IndentOptions indentOptions2 = indentOptions;
                    final boolean b = indentOptions2.USE_TAB_CHARACTER;
                    if (!b) {
                        break Label_0039;
                    }
                    break Label_0039;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final CommonCodeStyleSettings.IndentOptions indentOptions2 = indentOptions;
                    final boolean b = indentOptions2.USE_TAB_CHARACTER;
                    if (!b) {
                        final String replaceAll;
                        s = (replaceAll = repeatSymbol);
                        break Label_0062;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String replaceAll;
            s = (replaceAll = repeatSymbol.replaceAll(StringUtil.repeatSymbol((char)32, this.a()), "\t"));
            try {
                if (replaceAll == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "getStringIndent"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public static String getStringIndent(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "line", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "getStringIndent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        int i = 0;
        final int length = s.length();
        final StringBuilder sb = new StringBuilder();
        while (i < length) {
            final char char1 = s.charAt(i);
            try {
                if (a(char1)) {
                    ++i;
                    sb.append(char1);
                    continue;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            break;
        }
        String string;
        try {
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "getStringIndent"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return string;
    }
    
    @NotNull
    private static String c(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "string", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "dropStringIndent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        int n = 0;
        String substring = null;
        Label_0082: {
            while (true) {
                Label_0072: {
                    try {
                        if (n >= s.length()) {
                            break Label_0082;
                        }
                        final String s2 = s;
                        final int n2 = n;
                        final char c = s2.charAt(n2);
                        final boolean b = a(c);
                        if (b) {
                            break Label_0072;
                        }
                        break Label_0082;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final String s2 = s;
                        final int n2 = n;
                        final char c = s2.charAt(n2);
                        final boolean b = a(c);
                        if (b) {
                            ++n;
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                break;
            }
            try {
                substring = s.substring(n, s.length());
                if (substring == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "dropStringIndent"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return substring;
    }
    
    private static boolean a(final char c) {
        try {
            if ("\t ".indexOf(c) >= 0) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @NotNull
    public static Pair<String, String> splitIndent(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "whiteSpace", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "splitIndent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int lastIndex = s.lastIndexOf(10);
        Pair create = null;
        Label_0119: {
            Pair pair = null;
            Label_0084: {
                try {
                    if (lastIndex < 0) {
                        break Label_0119;
                    }
                    final String s2 = s;
                    final int n = 0;
                    final int n2 = lastIndex;
                    final int n3 = 1;
                    final int n4 = n2 + n3;
                    final String s3 = s2.substring(n, n4);
                    final String s4 = s;
                    final int n5 = lastIndex;
                    final int n6 = 1;
                    final int n7 = n5 + n6;
                    final String s5 = s4.substring(n7);
                    pair = Pair.create((Object)s3, (Object)s5);
                    if (pair == null) {
                        break Label_0084;
                    }
                    return (Pair<String, String>)pair;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final String s2 = s;
                    final int n = 0;
                    final int n2 = lastIndex;
                    final int n3 = 1;
                    final int n4 = n2 + n3;
                    final String s3 = s2.substring(n, n4);
                    final String s4 = s;
                    final int n5 = lastIndex;
                    final int n6 = 1;
                    final int n7 = n5 + n6;
                    final String s5 = s4.substring(n7);
                    pair = Pair.create((Object)s3, (Object)s5);
                    if (pair == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "splitIndent"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return (Pair<String, String>)pair;
            try {
                create = Pair.create((Object)"", (Object)s);
                if (create == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "splitIndent"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return (Pair<String, String>)create;
    }
    
    public static boolean isMultiline(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter", "isMultiline"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (astNode.getText().trim().indexOf(10) >= 0) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    static {
        PREPROCESSOR_INFO = Key.create("PREPROCESSOR_INFO");
        CODE_INDENT = Key.create("CODE_INDENT");
        AT_FIRST_COL_HINT = Key.create("AT_FIRST_COL_HINT");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private interface DocumentModifier
    {
        TextRange change(final boolean p0, final String p1, final String p2);
    }
}
