// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import javax.swing.Icon;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.util.text.StringUtil;
import java.util.Iterator;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.symbols.symtable.OCNamesInternary;
import java.util.List;
import java.util.Collections;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.util.OCImmutableList;
import com.intellij.openapi.util.Iconable;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;

public class OCMacroSymbol extends OCSymbolImpl<OCDefineDirective> implements Iconable
{
    public static final String VA_ARGS_PARAM_NAME = "__VA_ARGS__...";
    private OCImmutableList<String> myParameterNames;
    private String mySubstitution;
    private boolean myHasParameterList;
    
    public OCMacroSymbol() {
    }
    
    public OCMacroSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final int n, final String s, @Nullable final OCImmutableList<String> list, @NotNull final String mySubstitution) {
        if (mySubstitution == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitution", "com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol", "<init>"));
        }
        super(project, virtualFile, n, s, Collections.emptyList());
        boolean myHasParameterList;
        if (list != null) {
            myHasParameterList = true;
        }
        else {
            myHasParameterList = false;
        }
        OCImmutableList<String> emptyList = null;
        Label_0088: {
            try {
                this.myHasParameterList = myHasParameterList;
                if (this.myHasParameterList) {
                    emptyList = list;
                    break Label_0088;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            emptyList = OCImmutableList.emptyList();
        }
        this.myParameterNames = emptyList;
        this.mySubstitution = mySubstitution;
    }
    
    public static OCMacroSymbol inclusionGuard(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol", "inclusionGuard"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return new OCMacroSymbol(null, null, 0, OCNamesInternary.intern(s), null, "");
    }
    
    @NotNull
    public OCImmutableList<String> getParameterNames() {
        OCImmutableList<String> myParameterNames;
        try {
            myParameterNames = this.myParameterNames;
            if (myParameterNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol", "getParameterNames"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myParameterNames;
    }
    
    @NotNull
    public List<String> getUserFriendlyParameterNames() {
        List map;
        try {
            map = ContainerUtil.map((Collection)this.myParameterNames, s -> {
                try {
                    if ("__VA_ARGS__...".equals(s)) {
                        return "...";
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                return s;
            });
            if (map == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol", "getUserFriendlyParameterNames"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<String>)map;
    }
    
    public boolean isGlobal() {
        return true;
    }
    
    @NotNull
    public String getSubstitution() {
        String mySubstitution;
        try {
            mySubstitution = this.mySubstitution;
            if (mySubstitution == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol", "getSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return mySubstitution;
    }
    
    public int getSubstitutionHash() {
        return this.mySubstitution.hashCode();
    }
    
    public boolean hasParameterList() {
        return this.myHasParameterList;
    }
    
    public String getParametersSignature() {
        try {
            if (!this.myHasParameterList) {
                return "";
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append('(');
        int n = 1;
        for (final String s : this.myParameterNames) {
            try {
                if (n == 0) {
                    sb.append(", ");
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            sb.append(s);
            n = 0;
        }
        sb.append(')');
        return sb.toString();
    }
    
    public boolean isVararg() {
        Label_0044: {
            try {
                if (this.myParameterNames.size() <= 0) {
                    return false;
                }
                final OCMacroSymbol ocMacroSymbol = this;
                final OCImmutableList<String> list = ocMacroSymbol.myParameterNames;
                final OCMacroSymbol ocMacroSymbol2 = this;
                final OCImmutableList<String> list2 = ocMacroSymbol2.myParameterNames;
                final int n = list2.size();
                final int n2 = 1;
                final int n3 = n - n2;
                final String s = list.get(n3);
                final String s2 = s;
                final String s3 = "...";
                final boolean b = s2.endsWith(s3);
                if (b) {
                    break Label_0044;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCMacroSymbol ocMacroSymbol = this;
                final OCImmutableList<String> list = ocMacroSymbol.myParameterNames;
                final OCMacroSymbol ocMacroSymbol2 = this;
                final OCImmutableList<String> list2 = ocMacroSymbol2.myParameterNames;
                final int n = list2.size();
                final int n2 = 1;
                final int n3 = n - n2;
                final String s = list.get(n3);
                final String s2 = s;
                final String s3 = "...";
                final boolean b = s2.endsWith(s3);
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
    
    public String getPresentableSignature() {
        return this.getName() + this.getParametersSignature();
    }
    
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        try {
            sb.append("#define ").append(this.myName);
            if (this.myHasParameterList) {
                sb.append('(');
                StringUtil.join((Collection)this.myParameterNames, ", ", sb);
                sb.append(')');
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        sb.append(' ').append(this.mySubstitution);
        return sb.toString();
    }
    
    @Nullable
    public static OCMacroSymbol parseFromDirectiveContent(final CharSequence p0, final OCFile p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/jetbrains/cidr/lang/lexer/OCLexer;
        //     3: dup            
        //     4: getstatic       com/jetbrains/cidr/lang/OCLanguageKind.C:Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //     7: iconst_0       
        //     8: iconst_0       
        //     9: iconst_0       
        //    10: iconst_0       
        //    11: invokespecial   com/jetbrains/cidr/lang/lexer/OCLexer.<init>:(Lcom/jetbrains/cidr/lang/OCLanguageKind;ZZZZ)V
        //    14: astore_3       
        //    15: aload_3        
        //    16: aload_0        
        //    17: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.start:(Ljava/lang/CharSequence;)V
        //    20: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //    23: aload_3        
        //    24: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    27: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    30: ifeq            44
        //    33: aload_3        
        //    34: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.advance:()V
        //    37: goto            20
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_3        
        //    45: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    48: astore          4
        //    50: aload           4
        //    52: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    55: if_acmpeq       76
        //    58: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //    61: aload           4
        //    63: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    66: ifeq            554
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload_3        
        //    77: invokestatic    com/intellij/lexer/LexerUtil.getTokenText:(Lcom/intellij/lexer/Lexer;)Ljava/lang/CharSequence;
        //    80: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //    85: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCNamesInternary.intern:(Ljava/lang/String;)Ljava/lang/String;
        //    88: astore          5
        //    90: aload_3        
        //    91: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.advance:()V
        //    94: aload_3        
        //    95: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    98: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   101: if_acmpne       354
        //   104: new             Ljava/util/ArrayList;
        //   107: dup            
        //   108: invokespecial   java/util/ArrayList.<init>:()V
        //   111: astore          6
        //   113: aload_3        
        //   114: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.advance:()V
        //   117: aload_3        
        //   118: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   121: astore          7
        //   123: aload           7
        //   125: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   128: if_acmpeq       204
        //   131: aload           7
        //   133: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   136: if_acmpeq       204
        //   139: goto            146
        //   142: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //   149: aload           7
        //   151: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   154: ifne            204
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   167: aload           7
        //   169: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   172: ifne            204
        //   175: goto            182
        //   178: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: aload           7
        //   184: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ELLIPSIS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   187: if_acmpeq       204
        //   190: goto            197
        //   193: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: goto            333
        //   200: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   203: athrow         
        //   204: aload           7
        //   206: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   209: if_acmpeq       230
        //   212: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //   215: aload           7
        //   217: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   220: ifeq            297
        //   223: goto            230
        //   226: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   229: athrow         
        //   230: aload_3        
        //   231: invokestatic    com/intellij/lexer/LexerUtil.getTokenText:(Lcom/intellij/lexer/Lexer;)Ljava/lang/CharSequence;
        //   234: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //   239: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCNamesInternary.intern:(Ljava/lang/String;)Ljava/lang/String;
        //   242: astore          8
        //   244: aload_3        
        //   245: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.advance:()V
        //   248: aload_3        
        //   249: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   252: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ELLIPSIS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   255: if_acmpne       284
        //   258: aload_3        
        //   259: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.advance:()V
        //   262: new             Ljava/lang/StringBuilder;
        //   265: dup            
        //   266: invokespecial   java/lang/StringBuilder.<init>:()V
        //   269: aload           8
        //   271: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   274: ldc             "..."
        //   276: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   279: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   282: astore          8
        //   284: aload           6
        //   286: aload           8
        //   288: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   293: pop            
        //   294: goto            330
        //   297: aload           7
        //   299: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ELLIPSIS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   302: if_acmpne       326
        //   305: aload_3        
        //   306: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.advance:()V
        //   309: aload           6
        //   311: ldc             "__VA_ARGS__..."
        //   313: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   318: pop            
        //   319: goto            330
        //   322: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   325: athrow         
        //   326: aload_3        
        //   327: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.advance:()V
        //   330: goto            117
        //   333: aload_3        
        //   334: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   337: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   340: if_acmpne       357
        //   343: aload_3        
        //   344: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.advance:()V
        //   347: goto            357
        //   350: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   353: athrow         
        //   354: aconst_null    
        //   355: astore          6
        //   357: aload_3        
        //   358: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   361: getstatic       com/intellij/psi/TokenType.WHITE_SPACE:Lcom/intellij/psi/tree/IElementType;
        //   364: if_acmpne       378
        //   367: aload_3        
        //   368: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.advance:()V
        //   371: goto            378
        //   374: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   377: athrow         
        //   378: new             Ljava/lang/StringBuilder;
        //   381: dup            
        //   382: invokespecial   java/lang/StringBuilder.<init>:()V
        //   385: astore          7
        //   387: aload_3        
        //   388: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   391: astore          8
        //   393: aload           8
        //   395: ifnonnull       405
        //   398: goto            484
        //   401: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   404: athrow         
        //   405: aload_3        
        //   406: invokestatic    com/intellij/lexer/LexerUtil.getTokenText:(Lcom/intellij/lexer/Lexer;)Ljava/lang/CharSequence;
        //   409: astore          9
        //   411: aload           8
        //   413: getstatic       com/intellij/psi/TokenType.WHITE_SPACE:Lcom/intellij/psi/tree/IElementType;
        //   416: if_acmpne       446
        //   419: aload           7
        //   421: aload           9
        //   423: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //   428: bipush          92
        //   430: bipush          32
        //   432: invokevirtual   java/lang/String.replace:(CC)Ljava/lang/String;
        //   435: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   438: pop            
        //   439: goto            477
        //   442: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   445: athrow         
        //   446: aload           8
        //   448: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EOL_ESCAPE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   451: if_acmpne       469
        //   454: aload           7
        //   456: bipush          32
        //   458: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   461: pop            
        //   462: goto            477
        //   465: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   468: athrow         
        //   469: aload           7
        //   471: aload           9
        //   473: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;
        //   476: pop            
        //   477: aload_3        
        //   478: invokevirtual   com/jetbrains/cidr/lang/lexer/OCLexer.advance:()V
        //   481: goto            387
        //   484: aload           6
        //   486: ifnonnull       497
        //   489: aconst_null    
        //   490: goto            506
        //   493: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   496: athrow         
        //   497: new             Lcom/jetbrains/cidr/lang/util/OCImmutableList;
        //   500: dup            
        //   501: aload           6
        //   503: invokespecial   com/jetbrains/cidr/lang/util/OCImmutableList.<init>:(Ljava/util/Collection;)V
        //   506: astore          8
        //   508: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   511: dup            
        //   512: aload_1        
        //   513: ifnull          529
        //   516: aload_1        
        //   517: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   522: goto            530
        //   525: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   528: athrow         
        //   529: aconst_null    
        //   530: aload_1        
        //   531: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil.getVirtualFile:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   534: iload_2        
        //   535: aload           5
        //   537: aload           8
        //   539: aload           7
        //   541: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   544: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   547: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCNamesInternary.intern:(Ljava/lang/String;)Ljava/lang/String;
        //   550: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;ILjava/lang/String;Lcom/jetbrains/cidr/lang/util/OCImmutableList;Ljava/lang/String;)V
        //   553: areturn        
        //   554: aconst_null    
        //   555: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  20     40     40     44     Ljava/lang/IllegalArgumentException;
        //  50     69     72     76     Ljava/lang/IllegalArgumentException;
        //  123    139    142    146    Ljava/lang/IllegalArgumentException;
        //  131    157    160    164    Ljava/lang/IllegalArgumentException;
        //  146    175    178    182    Ljava/lang/IllegalArgumentException;
        //  164    190    193    197    Ljava/lang/IllegalArgumentException;
        //  182    200    200    204    Ljava/lang/IllegalArgumentException;
        //  204    223    226    230    Ljava/lang/IllegalArgumentException;
        //  297    322    322    326    Ljava/lang/IllegalArgumentException;
        //  333    350    350    354    Ljava/lang/IllegalArgumentException;
        //  357    371    374    378    Ljava/lang/IllegalArgumentException;
        //  393    401    401    405    Ljava/lang/IllegalArgumentException;
        //  411    442    442    446    Ljava/lang/IllegalArgumentException;
        //  446    465    465    469    Ljava/lang/IllegalArgumentException;
        //  484    493    493    497    Ljava/lang/IllegalArgumentException;
        //  508    525    525    529    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0146:
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
    
    public Icon getIcon(final int n) {
        return this.getIcon();
    }
    
    @NotNull
    public OCSymbolKind getKind() {
        OCSymbolKind macro;
        try {
            macro = OCSymbolKind.MACRO;
            if (macro == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return macro;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final OCMacroSymbol ocMacroSymbol = (OCMacroSymbol)o;
        final OCMacroSymbol ocMacroSymbol2 = (OCMacroSymbol)o2;
        try {
            if (ocMacroSymbol.myHasParameterList != ocMacroSymbol2.myHasParameterList) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!Comparing.equal(ocMacroSymbol.mySubstitution, ocMacroSymbol2.mySubstitution)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        try {
            if (!Comparing.equal((Object)ocMacroSymbol.myParameterNames, (Object)ocMacroSymbol2.myParameterNames)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        return true;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
