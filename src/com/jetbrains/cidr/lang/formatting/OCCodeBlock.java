// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.formatting.Spacing;
import java.util.Iterator;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.formatting.ChildAttributes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.formatting.ASTBlock;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.psi.impl.source.tree.LeafElement;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.util.containers.HashSet;
import com.jetbrains.cidr.lang.parser.OCParsing;
import com.intellij.util.SmartList;
import com.intellij.formatting.Block;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.formatting.Alignment;
import com.intellij.formatting.Wrap;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.formatting.FormattingMode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.TokenSet;
import com.intellij.openapi.util.TextRange;
import com.intellij.formatting.Indent;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.formatter.common.AbstractBlock;

public class OCCodeBlock extends AbstractBlock implements OCIndentChanger
{
    @NotNull
    protected final CommonCodeStyleSettings mySettings;
    @NotNull
    protected final OCCodeStyleSettings myOCSettings;
    @NotNull
    protected final Project myProject;
    @NotNull
    private final OCGlobalFormatterData myGlobalFormatterData;
    @NotNull
    private final OCLocalFormatterData myLocalFormatterData;
    protected Indent myIndent;
    protected Indent myBracesIndent;
    protected Indent myChildIndent;
    protected Indent myChildIndentEx;
    private final boolean myInDirective;
    private final boolean myIsRecursive;
    protected TextRange myTextRange;
    private static final TokenSet COMMA_OR_EXPRESSION;
    
    public OCCodeBlock(@NotNull final PsiFile psiFile, @NotNull final CodeStyleSettings codeStyleSettings, @NotNull final FormattingMode formattingMode) {
        if (psiFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/formatting/OCCodeBlock", "<init>"));
        }
        if (codeStyleSettings == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/formatting/OCCodeBlock", "<init>"));
        }
        if (formattingMode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "mode", "com/jetbrains/cidr/lang/formatting/OCCodeBlock", "<init>"));
        }
        this(null, codeStyleSettings.getCommonSettings((Language)OCLanguage.getInstance()), (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class), psiFile.getProject(), (ASTNode)psiFile.getNode(), new OCGlobalFormatterData(psiFile, formattingMode), Indent.getAbsoluteNoneIndent(), Indent.getNoneIndent(), null, null);
    }
    
    public OCCodeBlock(@Nullable final OCCodeBlock p0, @NotNull final CommonCodeStyleSettings p1, @NotNull final OCCodeStyleSettings p2, @NotNull final Project p3, @NotNull final ASTNode p4, @NotNull final OCGlobalFormatterData p5, @Nullable final Indent p6, @Nullable final Indent p7, @Nullable final Wrap p8, @Nullable final Alignment p9) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       40
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "settings"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "<init>"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: aload_3        
        //    41: ifnonnull       80
        //    44: new             Ljava/lang/IllegalArgumentException;
        //    47: dup            
        //    48: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    50: ldc             3
        //    52: anewarray       Ljava/lang/Object;
        //    55: dup            
        //    56: ldc             0
        //    58: ldc             "ocSettings"
        //    60: aastore        
        //    61: dup            
        //    62: ldc             1
        //    64: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //    66: aastore        
        //    67: dup            
        //    68: ldc             2
        //    70: ldc             "<init>"
        //    72: aastore        
        //    73: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    76: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    79: athrow         
        //    80: aload           4
        //    82: ifnonnull       121
        //    85: new             Ljava/lang/IllegalArgumentException;
        //    88: dup            
        //    89: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    91: ldc             3
        //    93: anewarray       Ljava/lang/Object;
        //    96: dup            
        //    97: ldc             0
        //    99: ldc             "project"
        //   101: aastore        
        //   102: dup            
        //   103: ldc             1
        //   105: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //   107: aastore        
        //   108: dup            
        //   109: ldc             2
        //   111: ldc             "<init>"
        //   113: aastore        
        //   114: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   117: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   120: athrow         
        //   121: aload           5
        //   123: ifnonnull       162
        //   126: new             Ljava/lang/IllegalArgumentException;
        //   129: dup            
        //   130: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   132: ldc             3
        //   134: anewarray       Ljava/lang/Object;
        //   137: dup            
        //   138: ldc             0
        //   140: ldc             "node"
        //   142: aastore        
        //   143: dup            
        //   144: ldc             1
        //   146: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //   148: aastore        
        //   149: dup            
        //   150: ldc             2
        //   152: ldc             "<init>"
        //   154: aastore        
        //   155: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   158: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   161: athrow         
        //   162: aload           6
        //   164: ifnonnull       203
        //   167: new             Ljava/lang/IllegalArgumentException;
        //   170: dup            
        //   171: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   173: ldc             3
        //   175: anewarray       Ljava/lang/Object;
        //   178: dup            
        //   179: ldc             0
        //   181: ldc             "globalFormatterData"
        //   183: aastore        
        //   184: dup            
        //   185: ldc             1
        //   187: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //   189: aastore        
        //   190: dup            
        //   191: ldc             2
        //   193: ldc             "<init>"
        //   195: aastore        
        //   196: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   199: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   202: athrow         
        //   203: aload_0        
        //   204: aload           5
        //   206: aload           9
        //   208: aload           10
        //   210: invokespecial   com/intellij/psi/formatter/common/AbstractBlock.<init>:(Lcom/intellij/lang/ASTNode;Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)V
        //   213: aload_0        
        //   214: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //   217: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   222: astore          11
        //   224: aload_0        
        //   225: aload_1        
        //   226: ifnull          243
        //   229: aload_1        
        //   230: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myInDirective:Z
        //   233: ifne            261
        //   236: goto            243
        //   239: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   242: athrow         
        //   243: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //   246: aload           11
        //   248: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   251: ifeq            269
        //   254: goto            261
        //   257: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   260: athrow         
        //   261: iconst_1       
        //   262: goto            270
        //   265: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   268: athrow         
        //   269: iconst_0       
        //   270: putfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myInDirective:Z
        //   273: aload_0        
        //   274: aconst_null    
        //   275: putfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myTextRange:Lcom/intellij/openapi/util/TextRange;
        //   278: aload_0        
        //   279: aload_2        
        //   280: putfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   283: aload_0        
        //   284: aload_3        
        //   285: putfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myOCSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   288: aload_0        
        //   289: aload           4
        //   291: putfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myProject:Lcom/intellij/openapi/project/Project;
        //   294: aload_0        
        //   295: aload           7
        //   297: putfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myIndent:Lcom/intellij/formatting/Indent;
        //   300: aload_0        
        //   301: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   304: putfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myBracesIndent:Lcom/intellij/formatting/Indent;
        //   307: aload_0        
        //   308: aload_0        
        //   309: aload           8
        //   311: dup_x1         
        //   312: putfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myChildIndent:Lcom/intellij/formatting/Indent;
        //   315: putfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myChildIndentEx:Lcom/intellij/formatting/Indent;
        //   318: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CLASSES:Lcom/intellij/psi/tree/TokenSet;
        //   321: aload           11
        //   323: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   326: ifeq            350
        //   329: aload_0        
        //   330: aload_0        
        //   331: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myOCSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   334: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.INDENT_VISIBILITY_KEYWORDS:I
        //   337: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.getSpaceIndentEnforcedToChildren:(I)Lcom/intellij/formatting/Indent;
        //   340: putfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myChildIndentEx:Lcom/intellij/formatting/Indent;
        //   343: goto            350
        //   346: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   349: athrow         
        //   350: aload_0        
        //   351: aload           6
        //   353: putfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myGlobalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCGlobalFormatterData;
        //   356: aload_0        
        //   357: aload_1        
        //   358: ifnonnull       369
        //   361: aconst_null    
        //   362: goto            373
        //   365: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   368: athrow         
        //   369: aload_1        
        //   370: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myLocalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //   373: aload           11
        //   375: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createData:(Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;Lcom/intellij/psi/tree/IElementType;)Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //   378: putfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myLocalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //   381: aload_0        
        //   382: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //   385: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   390: aload           11
        //   392: iconst_0       
        //   393: new             Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock$1;
        //   396: dup            
        //   397: aload_0        
        //   398: aload           11
        //   400: invokespecial   com/jetbrains/cidr/lang/formatting/OCCodeBlock$1.<init>:(Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;Lcom/intellij/psi/tree/IElementType;)V
        //   403: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.processBraced:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;ZLcom/jetbrains/cidr/lang/formatting/OCFormatterUtil$LeftBracesProcessor;)Ljava/lang/Object;
        //   406: pop            
        //   407: aload_0        
        //   408: aload_0        
        //   409: invokespecial   com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:()Z
        //   412: putfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myIsRecursive:Z
        //   415: aload_0        
        //   416: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myIsRecursive:Z
        //   419: ifeq            434
        //   422: aload_0        
        //   423: invokevirtual   com/jetbrains/cidr/lang/formatting/OCCodeBlock.getSubBlocks:()Ljava/util/List;
        //   426: pop            
        //   427: goto            434
        //   430: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   433: athrow         
        //   434: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  224    236    239    243    Ljava/lang/IllegalArgumentException;
        //  229    254    257    261    Ljava/lang/IllegalArgumentException;
        //  243    265    265    269    Ljava/lang/IllegalArgumentException;
        //  270    343    346    350    Ljava/lang/IllegalArgumentException;
        //  350    365    365    369    Ljava/lang/IllegalArgumentException;
        //  373    427    430    434    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0243:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    @Override
    public TextRange getTextRange() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myTextRange:Lcom/intellij/openapi/util/TextRange;
        //     4: ifnonnull       74
        //     7: aload_0        
        //     8: aload_0        
        //     9: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //    12: instanceof      Lcom/intellij/psi/impl/source/tree/LeafElement;
        //    15: ifne            56
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    24: athrow         
        //    25: aload_0        
        //    26: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //    29: instanceof      Lcom/intellij/lang/FileASTNode;
        //    32: ifne            56
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: aload_0        
        //    43: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myIsRecursive:Z
        //    46: ifne            67
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: aload_0        
        //    57: invokespecial   com/intellij/psi/formatter/common/AbstractBlock.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    60: goto            71
        //    63: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: aload_0        
        //    68: invokestatic    com/jetbrains/cidr/lang/formatting/OCSimpleBlock.getRangeFromSubBlocks:(Lcom/intellij/formatting/Block;)Lcom/intellij/openapi/util/TextRange;
        //    71: putfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myTextRange:Lcom/intellij/openapi/util/TextRange;
        //    74: aload_0        
        //    75: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myTextRange:Lcom/intellij/openapi/util/TextRange;
        //    78: dup            
        //    79: ifnonnull       116
        //    82: new             Ljava/lang/IllegalStateException;
        //    85: dup            
        //    86: ldc             "@NotNull method %s.%s must not return null"
        //    88: ldc             2
        //    90: anewarray       Ljava/lang/Object;
        //    93: dup            
        //    94: ldc             0
        //    96: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //    98: aastore        
        //    99: dup            
        //   100: ldc             1
        //   102: ldc             "getTextRange"
        //   104: aastore        
        //   105: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   108: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   111: athrow         
        //   112: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      18     21     25     Ljava/lang/IllegalArgumentException;
        //  7      35     38     42     Ljava/lang/IllegalArgumentException;
        //  25     49     52     56     Ljava/lang/IllegalArgumentException;
        //  42     63     63     67     Ljava/lang/IllegalArgumentException;
        //  74     112    112    116    Ljava/lang/IllegalArgumentException;
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
    
    @Override
    public String toString() {
        return OCSimpleBlock.getTextFromRange(this.getNode(), this.getTextRange());
    }
    
    @Override
    protected List<Block> buildChildren() {
        try {
            if (this.isLeaf()) {
                return OCCodeBlock.EMPTY;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCWrappingProcessor ocWrappingProcessor = new OCWrappingProcessor(this.mySettings, this.myOCSettings, this.myNode, this.myLocalFormatterData);
        final SmartList list = new SmartList();
        this.b((List<Block>)list, ocWrappingProcessor);
        ASTNode astNode = this.myNode.getFirstChildNode();
        while (true) {
            Label_0095: {
                Label_0088: {
                    try {
                        if (astNode == null) {
                            break;
                        }
                        final OCCodeBlock ocCodeBlock = this;
                        final OCGlobalFormatterData ocGlobalFormatterData = ocCodeBlock.myGlobalFormatterData;
                        final HashSet<ASTNode> set = ocGlobalFormatterData.skipNodeSet;
                        final ASTNode astNode2 = astNode;
                        final boolean b = set.contains((Object)astNode2);
                        if (b) {
                            break Label_0088;
                        }
                        break Label_0095;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final OCCodeBlock ocCodeBlock = this;
                        final OCGlobalFormatterData ocGlobalFormatterData = ocCodeBlock.myGlobalFormatterData;
                        final HashSet<ASTNode> set = ocGlobalFormatterData.skipNodeSet;
                        final ASTNode astNode2 = astNode;
                        final boolean b = set.contains((Object)astNode2);
                        if (b) {
                            break;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                try {
                    if (a(astNode)) {
                        this.a((List<Block>)list, astNode, ocWrappingProcessor);
                        this.b((List<Block>)list, ocWrappingProcessor);
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            Label_0146: {
                try {
                    if (!OCParsing.isEOFError(astNode)) {
                        break Label_0146;
                    }
                    final OCCodeBlock ocCodeBlock2 = this;
                    final OCGlobalFormatterData ocGlobalFormatterData2 = ocCodeBlock2.myGlobalFormatterData;
                    final boolean b2 = ocGlobalFormatterData2.isEOF;
                    if (!b2) {
                        break Label_0146;
                    }
                    break Label_0146;
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
                try {
                    final OCCodeBlock ocCodeBlock2 = this;
                    final OCGlobalFormatterData ocGlobalFormatterData2 = ocCodeBlock2.myGlobalFormatterData;
                    final boolean b2 = ocGlobalFormatterData2.isEOF;
                    if (!b2) {
                        this.myGlobalFormatterData.isEOF = true;
                        this.b((List<Block>)list, astNode, ocWrappingProcessor);
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
            }
            astNode = astNode.getTreeNext();
        }
        return this.a((List<Block>)list, ocWrappingProcessor);
    }
    
    @Contract(value = "null -> true", pure = true)
    private static boolean a(final ASTNode astNode) {
        Label_0021: {
            try {
                if (astNode instanceof PsiWhiteSpace) {
                    return false;
                }
                final ASTNode astNode2 = astNode;
                final boolean b = astNode2 instanceof OCMacroForeignLeafElement;
                if (!b) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final ASTNode astNode2 = astNode;
                final boolean b = astNode2 instanceof OCMacroForeignLeafElement;
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
    
    private void b(@NotNull final List<Block> list, @NotNull final ASTNode astNode, @NotNull final OCWrappingProcessor ocWrappingProcessor) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerBlocks", "com/jetbrains/cidr/lang/formatting/OCCodeBlock", "collectTailComments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "eofError", "com/jetbrains/cidr/lang/formatting/OCCodeBlock", "collectTailComments"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocWrappingProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerWrappingCalculator", "com/jetbrains/cidr/lang/formatting/OCCodeBlock", "collectTailComments"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        ASTNode astNode2;
        for (astNode2 = astNode.getTreeParent(); astNode2.getTreeParent().getTreeParent() != null; astNode2 = astNode2.getTreeParent()) {}
        ASTNode astNode3 = astNode2.getTreeNext();
        while (true) {
            Label_0209: {
                try {
                    if (astNode3 == null) {
                        break;
                    }
                    final OCCodeBlock ocCodeBlock = this;
                    final OCGlobalFormatterData ocGlobalFormatterData = ocCodeBlock.myGlobalFormatterData;
                    final HashSet<ASTNode> set = ocGlobalFormatterData.skipNodeSet;
                    final ASTNode astNode4 = astNode3;
                    set.add((Object)astNode4);
                    final ASTNode astNode5 = astNode3;
                    final boolean b = a(astNode5);
                    if (b) {
                        break Label_0209;
                    }
                    break Label_0209;
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                try {
                    final OCCodeBlock ocCodeBlock = this;
                    final OCGlobalFormatterData ocGlobalFormatterData = ocCodeBlock.myGlobalFormatterData;
                    final HashSet<ASTNode> set = ocGlobalFormatterData.skipNodeSet;
                    final ASTNode astNode4 = astNode3;
                    set.add((Object)astNode4);
                    final ASTNode astNode5 = astNode3;
                    final boolean b = a(astNode5);
                    if (b) {
                        this.a(list, astNode3, ocWrappingProcessor);
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
            }
            astNode3 = astNode3.getTreeNext();
        }
    }
    
    private void b(final List<Block> list, final OCWrappingProcessor ocWrappingProcessor) {
        this.myGlobalFormatterData.movedBlockMap.processForKey((Object)this.myNode, ocCodeBlock -> {
            list.add(this.a(ocWrappingProcessor, ocCodeBlock));
            return true;
        });
        this.myGlobalFormatterData.movedBlockMap.removeAllValues((Object)this.myNode);
    }
    
    private Block a(final OCWrappingProcessor p0, final OCCodeBlock p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: invokevirtual   com/jetbrains/cidr/lang/formatting/OCCodeBlock.getNode:()Lcom/intellij/lang/ASTNode;
        //     4: astore_3       
        //     5: aload_3        
        //     6: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.lastLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //     9: astore          4
        //    11: aload           4
        //    13: ifnonnull       19
        //    16: aload_3        
        //    17: astore          4
        //    19: aload_3        
        //    20: astore          5
        //    22: aload_3        
        //    23: astore          6
        //    25: aload_3        
        //    26: astore          7
        //    28: aload_0        
        //    29: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //    32: invokeinterface com/intellij/lang/ASTNode.getLastChildNode:()Lcom/intellij/lang/ASTNode;
        //    37: astore          8
        //    39: aload_0        
        //    40: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //    43: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //    48: astore          9
        //    50: aload           4
        //    52: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.nextLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //    55: astore          10
        //    57: aload           10
        //    59: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //    62: ifeq            226
        //    65: iconst_0       
        //    66: istore          11
        //    68: aload           10
        //    70: astore          12
        //    72: aload           12
        //    74: ifnull          190
        //    77: aload           12
        //    79: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    84: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    87: if_acmpne       116
        //    90: goto            97
        //    93: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    96: athrow         
        //    97: aload           12
        //    99: aload_3        
        //   100: if_acmpeq       116
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: iconst_1       
        //   111: istore          11
        //   113: goto            190
        //   116: aload           12
        //   118: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   123: aload_0        
        //   124: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //   127: if_acmpne       178
        //   130: aload           5
        //   132: aload_3        
        //   133: if_acmpeq       171
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   142: athrow         
        //   143: aload           8
        //   145: aload           12
        //   147: if_acmpne       178
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: aload           5
        //   159: aload           9
        //   161: if_acmpeq       178
        //   164: goto            171
        //   167: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   170: athrow         
        //   171: aload           12
        //   173: astore          5
        //   175: goto            190
        //   178: aload           12
        //   180: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   185: astore          12
        //   187: goto            72
        //   190: iload           11
        //   192: ifne            216
        //   195: aload           6
        //   197: aload_3        
        //   198: if_acmpne       212
        //   201: goto            208
        //   204: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: aload           10
        //   210: astore          6
        //   212: aload           10
        //   214: astore          7
        //   216: aload           10
        //   218: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.nextLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   221: astore          10
        //   223: goto            57
        //   226: aload           5
        //   228: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   233: astore          10
        //   235: aload_1        
        //   236: aload           5
        //   238: aload           10
        //   240: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.calculate:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //   243: astore          11
        //   245: aload_0        
        //   246: aload_0        
        //   247: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //   250: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   255: aload           5
        //   257: aload           10
        //   259: invokevirtual   com/jetbrains/cidr/lang/formatting/OCCodeBlock.calcChildIndent:(Lcom/intellij/psi/tree/IElementType;Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/formatting/Indent;
        //   262: astore          12
        //   264: new             Lcom/jetbrains/cidr/lang/formatting/OCMacroBlock;
        //   267: dup            
        //   268: aload           11
        //   270: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //   273: aload           11
        //   275: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.alignment:Lcom/intellij/formatting/Alignment;
        //   278: aload           12
        //   280: aload_0        
        //   281: new             Lcom/intellij/util/SmartList;
        //   284: dup            
        //   285: aload_2        
        //   286: invokespecial   com/intellij/util/SmartList.<init>:(Ljava/lang/Object;)V
        //   289: aload_3        
        //   290: aload           6
        //   292: aload           7
        //   294: aload           5
        //   296: invokespecial   com/jetbrains/cidr/lang/formatting/OCMacroBlock.<init>:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;Lcom/intellij/formatting/Indent;Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;Ljava/util/List;Lcom/intellij/lang/ASTNode;Lcom/intellij/lang/ASTNode;Lcom/intellij/lang/ASTNode;Lcom/intellij/lang/ASTNode;)V
        //   299: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  72     90     93     97     Ljava/lang/IllegalArgumentException;
        //  77     103    106    110    Ljava/lang/IllegalArgumentException;
        //  116    136    139    143    Ljava/lang/IllegalArgumentException;
        //  130    150    153    157    Ljava/lang/IllegalArgumentException;
        //  143    164    167    171    Ljava/lang/IllegalArgumentException;
        //  190    201    204    208    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0143:
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
    private ASTNode b(@NotNull final ASTNode p0) {
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
        //    18: ldc             "macroCall"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getMacroOwner"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.lastLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //    48: astore_2       
        //    49: aload_2        
        //    50: ifnonnull       55
        //    53: aload_1        
        //    54: astore_2       
        //    55: aconst_null    
        //    56: astore_3       
        //    57: aconst_null    
        //    58: astore          4
        //    60: aload_2        
        //    61: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.nextLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //    64: astore          5
        //    66: aload           5
        //    68: ifnull          250
        //    71: aload           5
        //    73: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //    76: ifeq            250
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: iconst_0       
        //    87: istore          6
        //    89: aload           5
        //    91: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    96: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    99: if_acmpne       217
        //   102: aload           5
        //   104: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   109: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //   112: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_REF:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   115: if_acmpne       217
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: getstatic       com/jetbrains/cidr/lang/formatting/OCCodeBlock.$assertionsDisabled:Z
        //   128: ifne            167
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: aload           5
        //   140: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   145: ifnonnull       167
        //   148: goto            155
        //   151: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   154: athrow         
        //   155: new             Ljava/lang/AssertionError;
        //   158: dup            
        //   159: invokespecial   java/lang/AssertionError.<init>:()V
        //   162: athrow         
        //   163: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: aload           5
        //   169: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   174: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   179: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.lastLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   182: astore          5
        //   184: getstatic       com/jetbrains/cidr/lang/formatting/OCCodeBlock.$assertionsDisabled:Z
        //   187: ifne            214
        //   190: aload           5
        //   192: ifnonnull       214
        //   195: goto            202
        //   198: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   201: athrow         
        //   202: new             Ljava/lang/AssertionError;
        //   205: dup            
        //   206: invokespecial   java/lang/AssertionError.<init>:()V
        //   209: athrow         
        //   210: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   213: athrow         
        //   214: iconst_1       
        //   215: istore          6
        //   217: iload           6
        //   219: ifne            240
        //   222: aload_3        
        //   223: ifnonnull       236
        //   226: goto            233
        //   229: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   232: athrow         
        //   233: aload           5
        //   235: astore_3       
        //   236: aload           5
        //   238: astore          4
        //   240: aload           5
        //   242: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.nextLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   245: astore          5
        //   247: goto            66
        //   250: aload_3        
        //   251: ifnonnull       304
        //   254: aload_0        
        //   255: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //   258: dup            
        //   259: ifnonnull       303
        //   262: goto            269
        //   265: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   268: athrow         
        //   269: new             Ljava/lang/IllegalStateException;
        //   272: dup            
        //   273: ldc             "@NotNull method %s.%s must not return null"
        //   275: ldc             2
        //   277: anewarray       Ljava/lang/Object;
        //   280: dup            
        //   281: ldc             0
        //   283: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //   285: aastore        
        //   286: dup            
        //   287: ldc             1
        //   289: ldc             "getMacroOwner"
        //   291: aastore        
        //   292: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   295: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   298: athrow         
        //   299: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   302: athrow         
        //   303: areturn        
        //   304: aload_0        
        //   305: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //   308: aload_3        
        //   309: aload           4
        //   311: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.getBestCommonOwner:(Lcom/intellij/lang/ASTNode;Lcom/intellij/lang/ASTNode;Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   314: astore          6
        //   316: aload_3        
        //   317: aload           4
        //   319: if_acmpeq       407
        //   322: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //   325: aload           6
        //   327: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   332: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   335: ifne            407
        //   338: goto            345
        //   341: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   344: athrow         
        //   345: aload           6
        //   347: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.firstLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   350: aload_3        
        //   351: if_acmpne       407
        //   354: goto            361
        //   357: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   360: athrow         
        //   361: aload           6
        //   363: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.lastLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   366: aload           4
        //   368: if_acmpne       407
        //   371: goto            378
        //   374: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   377: athrow         
        //   378: aload           6
        //   380: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   385: ifnull          407
        //   388: goto            395
        //   391: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   394: athrow         
        //   395: aload           6
        //   397: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   402: astore          6
        //   404: goto            316
        //   407: aload           6
        //   409: dup            
        //   410: ifnonnull       447
        //   413: new             Ljava/lang/IllegalStateException;
        //   416: dup            
        //   417: ldc             "@NotNull method %s.%s must not return null"
        //   419: ldc             2
        //   421: anewarray       Ljava/lang/Object;
        //   424: dup            
        //   425: ldc             0
        //   427: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //   429: aastore        
        //   430: dup            
        //   431: ldc             1
        //   433: ldc             "getMacroOwner"
        //   435: aastore        
        //   436: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   439: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   442: athrow         
        //   443: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   446: athrow         
        //   447: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  66     79     82     86     Ljava/lang/IllegalArgumentException;
        //  89     118    121    125    Ljava/lang/IllegalArgumentException;
        //  102    131    134    138    Ljava/lang/IllegalArgumentException;
        //  125    148    151    155    Ljava/lang/IllegalArgumentException;
        //  138    163    163    167    Ljava/lang/IllegalArgumentException;
        //  184    195    198    202    Ljava/lang/IllegalArgumentException;
        //  190    210    210    214    Ljava/lang/IllegalArgumentException;
        //  217    226    229    233    Ljava/lang/IllegalArgumentException;
        //  250    262    265    269    Ljava/lang/IllegalArgumentException;
        //  254    299    299    303    Ljava/lang/IllegalArgumentException;
        //  316    338    341    345    Ljava/lang/IllegalArgumentException;
        //  322    354    357    361    Ljava/lang/IllegalArgumentException;
        //  345    371    374    378    Ljava/lang/IllegalArgumentException;
        //  361    388    391    395    Ljava/lang/IllegalArgumentException;
        //  407    443    443    447    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0125:
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
    
    private void a(@NotNull final List<Block> p0, @NotNull final ASTNode p1, @NotNull final OCWrappingProcessor p2) {
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
        //    18: ldc             "ownerBlocks"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "extendBlockCollectionByNode"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "node"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "extendBlockCollectionByNode"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "ownerWrappingCalculator"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "extendBlockCollectionByNode"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_0        
        //   133: aload_2        
        //   134: aload_3        
        //   135: invokevirtual   com/jetbrains/cidr/lang/formatting/OCCodeBlock.createChildBlock:(Lcom/intellij/lang/ASTNode;Lcom/jetbrains/cidr/lang/formatting/OCWrappingProcessor;)Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;
        //   138: astore          4
        //   140: aload           4
        //   142: astore          5
        //   144: aload_2        
        //   145: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   150: astore          6
        //   152: aload_2        
        //   153: invokeinterface com/intellij/lang/ASTNode.getTextLength:()I
        //   158: ifeq            277
        //   161: aload           6
        //   163: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   166: if_acmpne       223
        //   169: goto            176
        //   172: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: aload_0        
        //   177: aload_2        
        //   178: invokespecial   com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   181: astore          7
        //   183: aload           7
        //   185: aload_0        
        //   186: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //   189: if_acmpeq       211
        //   192: aload_0        
        //   193: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myGlobalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCGlobalFormatterData;
        //   196: getfield        com/jetbrains/cidr/lang/formatting/OCGlobalFormatterData.movedBlockMap:Lcom/intellij/util/containers/MostlySingularMultiMap;
        //   199: aload           7
        //   201: aload           4
        //   203: invokevirtual   com/intellij/util/containers/MostlySingularMultiMap.add:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   206: return         
        //   207: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   210: athrow         
        //   211: aload_0        
        //   212: aload_3        
        //   213: aload           4
        //   215: invokespecial   com/jetbrains/cidr/lang/formatting/OCCodeBlock.a:(Lcom/jetbrains/cidr/lang/formatting/OCWrappingProcessor;Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;)Lcom/intellij/formatting/Block;
        //   218: astore          5
        //   220: goto            277
        //   223: getstatic       com/jetbrains/cidr/lang/formatting/OCFormatterUtil.FORMAT_DIRECTIVES_AND_NON_COMPILED:Lcom/intellij/psi/tree/TokenSet;
        //   226: aload           6
        //   228: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   231: ifeq            277
        //   234: aload_0        
        //   235: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myLocalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //   238: aload_2        
        //   239: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.get:(Ljava/lang/Object;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //   244: astore          7
        //   246: aload           7
        //   248: ifnull          277
        //   251: new             Lcom/jetbrains/cidr/lang/formatting/OCSimpleBlock;
        //   254: dup            
        //   255: iconst_2       
        //   256: iconst_1       
        //   257: invokestatic    com/intellij/formatting/Wrap.createWrap:(IZ)Lcom/intellij/formatting/Wrap;
        //   260: aconst_null    
        //   261: aload           7
        //   263: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.indent:Lcom/intellij/formatting/Indent;
        //   266: aload_0        
        //   267: aload           4
        //   269: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   272: invokespecial   com/jetbrains/cidr/lang/formatting/OCSimpleBlock.<init>:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;Lcom/intellij/formatting/Indent;Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;Ljava/util/List;)V
        //   275: astore          5
        //   277: getstatic       com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer.CALL_CHAIN_TRANSFORMER:Lcom/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer;
        //   280: aload_3        
        //   281: aload_0        
        //   282: aload_1        
        //   283: aload           4
        //   285: aload_2        
        //   286: invokevirtual   com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer.isTransformed:(Lcom/jetbrains/cidr/lang/formatting/OCWrappingProcessor;Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;Ljava/util/List;Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;Lcom/intellij/lang/ASTNode;)Z
        //   289: ifne            428
        //   292: getstatic       com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer.BINARY_CHAIN_TRANSFORMER:Lcom/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer;
        //   295: aload_3        
        //   296: aload_0        
        //   297: aload_1        
        //   298: aload           4
        //   300: aload_2        
        //   301: invokevirtual   com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer.isTransformed:(Lcom/jetbrains/cidr/lang/formatting/OCWrappingProcessor;Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;Ljava/util/List;Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;Lcom/intellij/lang/ASTNode;)Z
        //   304: ifne            428
        //   307: goto            314
        //   310: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   313: athrow         
        //   314: aload           6
        //   316: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   319: if_acmpne       364
        //   322: goto            329
        //   325: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   328: athrow         
        //   329: iconst_1       
        //   330: aload           4
        //   332: invokevirtual   com/jetbrains/cidr/lang/formatting/OCCodeBlock.getSubBlocks:()Ljava/util/List;
        //   335: aload_3        
        //   336: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getOCSettings:()Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   339: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_CALL_ARGUMENTS_WRAP:I
        //   342: aload_3        
        //   343: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getSettings:()Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   346: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.KEEP_LINE_BREAKS:Z
        //   349: invokedynamic   value:()Lcom/intellij/openapi/util/Condition;
        //   354: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.applyIndentCorrection:(ZLjava/util/List;IZLcom/intellij/openapi/util/Condition;)V
        //   357: goto            401
        //   360: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   363: athrow         
        //   364: aload           6
        //   366: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MESSAGE_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   369: if_acmpne       401
        //   372: iconst_0       
        //   373: aload           4
        //   375: invokevirtual   com/jetbrains/cidr/lang/formatting/OCCodeBlock.getSubBlocks:()Ljava/util/List;
        //   378: iconst_1       
        //   379: aload_3        
        //   380: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getSettings:()Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   383: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.KEEP_LINE_BREAKS:Z
        //   386: invokedynamic   value:()Lcom/intellij/openapi/util/Condition;
        //   391: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.applyIndentCorrection:(ZLjava/util/List;IZLcom/intellij/openapi/util/Condition;)V
        //   394: goto            401
        //   397: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   400: athrow         
        //   401: aload           4
        //   403: invokevirtual   com/jetbrains/cidr/lang/formatting/OCCodeBlock.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   406: invokevirtual   com/intellij/openapi/util/TextRange.isEmpty:()Z
        //   409: ifne            428
        //   412: aload_1        
        //   413: aload           5
        //   415: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   420: pop            
        //   421: goto            428
        //   424: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   427: athrow         
        //   428: return         
        //    Signature:
        //  (Ljava/util/List<Lcom/intellij/formatting/Block;>;Lcom/intellij/lang/ASTNode;Lcom/jetbrains/cidr/lang/formatting/OCWrappingProcessor;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  152    169    172    176    Ljava/lang/IllegalArgumentException;
        //  183    207    207    211    Ljava/lang/IllegalArgumentException;
        //  277    307    310    314    Ljava/lang/IllegalArgumentException;
        //  292    322    325    329    Ljava/lang/IllegalArgumentException;
        //  314    360    360    364    Ljava/lang/IllegalArgumentException;
        //  364    394    397    401    Ljava/lang/IllegalArgumentException;
        //  401    421    424    428    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0314:
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
        //     1: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myGlobalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCGlobalFormatterData;
        //     4: getfield        com/jetbrains/cidr/lang/formatting/OCGlobalFormatterData.movedBlockMap:Lcom/intellij/util/containers/MostlySingularMultiMap;
        //     7: invokevirtual   com/intellij/util/containers/MostlySingularMultiMap.isEmpty:()Z
        //    10: ifeq            52
        //    13: aload_0        
        //    14: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //    17: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    22: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    25: if_acmpeq       52
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_0        
        //    36: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //    39: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.d:(Lcom/intellij/lang/ASTNode;)Z
        //    42: ifeq            60
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: iconst_1       
        //    53: goto            61
        //    56: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: iconst_0       
        //    61: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      28     31     35     Ljava/lang/IllegalArgumentException;
        //  13     45     48     52     Ljava/lang/IllegalArgumentException;
        //  35     56     56     60     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0035:
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
    
    private static boolean d(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCCodeBlock", "hasProblemTailNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final ASTNode lastChildNode = astNode.getLastChildNode();
        Label_0078: {
            Label_0069: {
                try {
                    if (lastChildNode == null) {
                        break Label_0069;
                    }
                    final ASTNode astNode2 = lastChildNode;
                    final boolean b = astNode2 instanceof LeafElement;
                    if (b) {
                        break Label_0069;
                    }
                    break Label_0078;
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                try {
                    final ASTNode astNode2 = lastChildNode;
                    final boolean b = astNode2 instanceof LeafElement;
                    if (b) {
                        return lastChildNode instanceof OCMacroForeignLeafElement;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
            }
            try {
                if (lastChildNode.getTextRange().isEmpty()) {
                    return true;
                }
                final ASTNode astNode3 = lastChildNode;
                final IElementType elementType = astNode3.getElementType();
                final OCElementType ocElementType = OCElementTypes.MACRO_CALL;
                if (elementType == ocElementType) {
                    return true;
                }
                return d(lastChildNode);
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        try {
            final ASTNode astNode3 = lastChildNode;
            final IElementType elementType = astNode3.getElementType();
            final OCElementType ocElementType = OCElementTypes.MACRO_CALL;
            if (elementType == ocElementType) {
                return true;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        return d(lastChildNode);
    }
    
    @NotNull
    private List<Block> a(@NotNull final List<Block> p0, @NotNull final OCWrappingProcessor p1) {
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
        //    18: ldc             "ownerBlocks"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "postProcessBlockCollection"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "wrappingCalculator"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "postProcessBlockCollection"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //    92: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    97: astore_3       
        //    98: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.STRUCTURE_TYPES:Lcom/intellij/psi/tree/TokenSet;
        //   101: aload_3        
        //   102: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   105: istore          4
        //   107: aload_3        
        //   108: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   111: if_acmpeq       128
        //   114: aload_3        
        //   115: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   118: if_acmpne       136
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   127: athrow         
        //   128: iconst_1       
        //   129: goto            137
        //   132: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   135: athrow         
        //   136: iconst_0       
        //   137: istore          5
        //   139: iconst_0       
        //   140: istore          6
        //   142: new             Lcom/intellij/util/SmartList;
        //   145: dup            
        //   146: invokespecial   com/intellij/util/SmartList.<init>:()V
        //   149: astore          7
        //   151: aload           7
        //   153: astore          8
        //   155: aconst_null    
        //   156: astore          9
        //   158: aload_1        
        //   159: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   164: astore          10
        //   166: aload           10
        //   168: invokeinterface java/util/Iterator.hasNext:()Z
        //   173: ifeq            1009
        //   176: aload           10
        //   178: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   183: checkcast       Lcom/intellij/formatting/Block;
        //   186: astore          11
        //   188: aload           11
        //   190: invokestatic    com/jetbrains/cidr/lang/formatting/OCSimpleBlock.extractFirstNode:(Lcom/intellij/formatting/Block;)Lcom/intellij/lang/ASTNode;
        //   193: astore          12
        //   195: aload           12
        //   197: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //   200: astore          13
        //   202: aload           13
        //   204: ifnonnull       224
        //   207: aload           8
        //   209: aload           11
        //   211: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   216: pop            
        //   217: goto            166
        //   220: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   223: athrow         
        //   224: iconst_0       
        //   225: istore          14
        //   227: aload           8
        //   229: aload           7
        //   231: if_acmpne       261
        //   234: aload           13
        //   236: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TEMPLATE_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   239: if_acmpne       261
        //   242: goto            249
        //   245: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: new             Lcom/intellij/util/SmartList;
        //   252: dup            
        //   253: invokespecial   com/intellij/util/SmartList.<init>:()V
        //   256: astore          8
        //   258: goto            848
        //   261: aload           8
        //   263: aload           7
        //   265: if_acmpne       399
        //   268: iload           4
        //   270: ifeq            399
        //   273: goto            280
        //   276: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   279: athrow         
        //   280: aload           13
        //   282: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   285: if_acmpeq       316
        //   288: goto            295
        //   291: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   294: athrow         
        //   295: aload           12
        //   297: aload_0        
        //   298: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //   301: invokeinterface com/intellij/lang/ASTNode.getLastChildNode:()Lcom/intellij/lang/ASTNode;
        //   306: if_acmpne       399
        //   309: goto            316
        //   312: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   315: athrow         
        //   316: aload           13
        //   318: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   321: if_acmpeq       344
        //   324: goto            331
        //   327: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   330: athrow         
        //   331: aload           8
        //   333: aload           11
        //   335: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   340: pop            
        //   341: iconst_1       
        //   342: istore          14
        //   344: aload           8
        //   346: invokeinterface java/util/List.isEmpty:()Z
        //   351: ifne            848
        //   354: new             Lcom/intellij/util/SmartList;
        //   357: dup            
        //   358: invokespecial   com/intellij/util/SmartList.<init>:()V
        //   361: astore          7
        //   363: aload           7
        //   365: new             Lcom/jetbrains/cidr/lang/formatting/OCSimpleBlock;
        //   368: dup            
        //   369: aload_2        
        //   370: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   373: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getTemplateWrap:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/formatting/Wrap;
        //   376: aconst_null    
        //   377: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   380: aload_0        
        //   381: aload           8
        //   383: invokespecial   com/jetbrains/cidr/lang/formatting/OCSimpleBlock.<init>:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;Lcom/intellij/formatting/Indent;Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;Ljava/util/List;)V
        //   386: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   391: pop            
        //   392: aload           7
        //   394: astore          8
        //   396: goto            848
        //   399: aload           8
        //   401: aload           7
        //   403: if_acmpeq       549
        //   406: iload           6
        //   408: ifeq            549
        //   411: goto            418
        //   414: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   417: athrow         
        //   418: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.BLOCK_STATEMENTS:Lcom/intellij/psi/tree/TokenSet;
        //   421: aload           13
        //   423: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   426: ifne            451
        //   429: goto            436
        //   432: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   435: athrow         
        //   436: aload           13
        //   438: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   441: if_acmpne       549
        //   444: goto            451
        //   447: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   450: athrow         
        //   451: iconst_0       
        //   452: istore          6
        //   454: aload           13
        //   456: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   459: if_acmpne       475
        //   462: aload           8
        //   464: aload           11
        //   466: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   471: pop            
        //   472: iconst_1       
        //   473: istore          14
        //   475: aload           9
        //   477: ifnonnull       489
        //   480: aload           7
        //   482: goto            496
        //   485: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   488: athrow         
        //   489: new             Lcom/intellij/util/SmartList;
        //   492: dup            
        //   493: invokespecial   com/intellij/util/SmartList.<init>:()V
        //   496: astore          15
        //   498: aload           8
        //   500: invokeinterface java/util/List.isEmpty:()Z
        //   505: ifne            542
        //   508: aload           15
        //   510: new             Lcom/jetbrains/cidr/lang/formatting/OCSimpleBlock;
        //   513: dup            
        //   514: aload_2        
        //   515: aconst_null    
        //   516: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getTemplateWrap:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/formatting/Wrap;
        //   519: aconst_null    
        //   520: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   523: aload_0        
        //   524: aload           8
        //   526: invokespecial   com/jetbrains/cidr/lang/formatting/OCSimpleBlock.<init>:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;Lcom/intellij/formatting/Indent;Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;Ljava/util/List;)V
        //   529: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   534: pop            
        //   535: goto            542
        //   538: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   541: athrow         
        //   542: aload           15
        //   544: astore          8
        //   546: goto            848
        //   549: iload           5
        //   551: ifeq            595
        //   554: aload           8
        //   556: aload           7
        //   558: if_acmpne       595
        //   561: goto            568
        //   564: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   567: athrow         
        //   568: aload           13
        //   570: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   573: if_acmpne       595
        //   576: goto            583
        //   579: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   582: athrow         
        //   583: new             Lcom/intellij/util/SmartList;
        //   586: dup            
        //   587: invokespecial   com/intellij/util/SmartList.<init>:()V
        //   590: astore          8
        //   592: goto            848
        //   595: iload           5
        //   597: ifeq            848
        //   600: aload           8
        //   602: aload           7
        //   604: if_acmpeq       848
        //   607: goto            614
        //   610: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   613: athrow         
        //   614: aload           13
        //   616: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   619: if_acmpne       848
        //   622: goto            629
        //   625: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   628: athrow         
        //   629: iconst_1       
        //   630: istore          14
        //   632: aload           11
        //   634: invokeinterface com/intellij/formatting/Block.getSubBlocks:()Ljava/util/List;
        //   639: astore          15
        //   641: new             Lcom/intellij/util/SmartList;
        //   644: dup            
        //   645: invokespecial   com/intellij/util/SmartList.<init>:()V
        //   648: astore          16
        //   650: aload           15
        //   652: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   657: astore          17
        //   659: aload           17
        //   661: invokeinterface java/util/Iterator.hasNext:()Z
        //   666: ifeq            794
        //   669: aload           17
        //   671: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   676: checkcast       Lcom/intellij/formatting/Block;
        //   679: astore          18
        //   681: aload           18
        //   683: invokestatic    com/jetbrains/cidr/lang/formatting/OCSimpleBlock.extractFirstNode:(Lcom/intellij/formatting/Block;)Lcom/intellij/lang/ASTNode;
        //   686: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //   689: astore          19
        //   691: aload           16
        //   693: invokeinterface java/util/List.isEmpty:()Z
        //   698: ifeq            781
        //   701: aload           19
        //   703: ifnull          764
        //   706: goto            713
        //   709: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   712: athrow         
        //   713: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TYPE_MODIFIERS:Lcom/intellij/psi/tree/TokenSet;
        //   716: aload           19
        //   718: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   721: ifne            764
        //   724: goto            731
        //   727: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   730: athrow         
        //   731: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //   734: aload           19
        //   736: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   739: ifeq            781
        //   742: goto            749
        //   745: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   748: athrow         
        //   749: aload           19
        //   751: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OPERATOR_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   754: if_acmpeq       781
        //   757: goto            764
        //   760: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   763: athrow         
        //   764: aload           8
        //   766: aload           18
        //   768: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   773: pop            
        //   774: goto            791
        //   777: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   780: athrow         
        //   781: aload           16
        //   783: aload           18
        //   785: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   790: pop            
        //   791: goto            659
        //   794: aload           16
        //   796: invokeinterface java/util/List.isEmpty:()Z
        //   801: ifne            848
        //   804: aload           8
        //   806: new             Lcom/jetbrains/cidr/lang/formatting/OCSimpleBlock;
        //   809: dup            
        //   810: aload_2        
        //   811: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   814: aload_0        
        //   815: invokespecial   com/jetbrains/cidr/lang/formatting/OCCodeBlock.a:()I
        //   818: iconst_1       
        //   819: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.wrapOnly:(Ljava/lang/Object;IZ)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //   822: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //   825: aconst_null    
        //   826: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   829: aload_0        
        //   830: aload           16
        //   832: invokespecial   com/jetbrains/cidr/lang/formatting/OCSimpleBlock.<init>:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;Lcom/intellij/formatting/Indent;Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;Ljava/util/List;)V
        //   835: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   840: pop            
        //   841: goto            848
        //   844: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   847: athrow         
        //   848: iload           14
        //   850: ifne            870
        //   853: aload           8
        //   855: aload           11
        //   857: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   862: pop            
        //   863: goto            870
        //   866: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   869: athrow         
        //   870: aload           8
        //   872: aload           7
        //   874: if_acmpeq       1006
        //   877: aload           13
        //   879: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   882: if_acmpne       1006
        //   885: goto            892
        //   888: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   891: athrow         
        //   892: aload           8
        //   894: invokeinterface java/util/List.isEmpty:()Z
        //   899: ifne            943
        //   902: goto            909
        //   905: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   908: athrow         
        //   909: aload           7
        //   911: new             Lcom/jetbrains/cidr/lang/formatting/OCSimpleBlock;
        //   914: dup            
        //   915: aload_2        
        //   916: aconst_null    
        //   917: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getTemplateWrap:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/formatting/Wrap;
        //   920: aconst_null    
        //   921: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   924: aload_0        
        //   925: aload           8
        //   927: invokespecial   com/jetbrains/cidr/lang/formatting/OCSimpleBlock.<init>:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;Lcom/intellij/formatting/Indent;Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;Ljava/util/List;)V
        //   930: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   935: pop            
        //   936: goto            943
        //   939: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   942: athrow         
        //   943: aload           7
        //   945: astore          8
        //   947: iload           5
        //   949: ifeq            982
        //   952: new             Lcom/intellij/util/SmartList;
        //   955: dup            
        //   956: invokespecial   com/intellij/util/SmartList.<init>:()V
        //   959: astore          8
        //   961: iconst_1       
        //   962: istore          6
        //   964: aload_0        
        //   965: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myOCSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   968: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_DECLARATION_FUNCTION_BODY_INDENT:Z
        //   971: ifeq            1006
        //   974: invokestatic    com/intellij/formatting/Indent.getNormalIndent:()Lcom/intellij/formatting/Indent;
        //   977: astore          9
        //   979: goto            1006
        //   982: aload_0        
        //   983: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myOCSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   986: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.TEMPLATE_DECLARATION_STRUCT_BODY_INDENT:Z
        //   989: ifeq            1006
        //   992: new             Lcom/intellij/util/SmartList;
        //   995: dup            
        //   996: invokespecial   com/intellij/util/SmartList.<init>:()V
        //   999: astore          8
        //  1001: invokestatic    com/intellij/formatting/Indent.getNormalIndent:()Lcom/intellij/formatting/Indent;
        //  1004: astore          9
        //  1006: goto            166
        //  1009: aload           8
        //  1011: invokeinterface java/util/List.isEmpty:()Z
        //  1016: ifne            1140
        //  1019: aload           8
        //  1021: aload           7
        //  1023: if_acmpeq       1140
        //  1026: goto            1033
        //  1029: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1032: athrow         
        //  1033: iload           6
        //  1035: ifeq            1096
        //  1038: goto            1045
        //  1041: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1044: athrow         
        //  1045: aload           7
        //  1047: new             Lcom/jetbrains/cidr/lang/formatting/OCSimpleBlock;
        //  1050: dup            
        //  1051: aload_2        
        //  1052: aconst_null    
        //  1053: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getTemplateWrap:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/formatting/Wrap;
        //  1056: aconst_null    
        //  1057: aload           9
        //  1059: ifnull          1078
        //  1062: goto            1069
        //  1065: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1068: athrow         
        //  1069: aload           9
        //  1071: goto            1081
        //  1074: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1077: athrow         
        //  1078: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //  1081: aload_0        
        //  1082: aload           8
        //  1084: invokespecial   com/jetbrains/cidr/lang/formatting/OCSimpleBlock.<init>:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;Lcom/intellij/formatting/Indent;Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;Ljava/util/List;)V
        //  1087: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //  1092: pop            
        //  1093: goto            1140
        //  1096: aload           9
        //  1098: ifnull          1130
        //  1101: aload           7
        //  1103: new             Lcom/jetbrains/cidr/lang/formatting/OCSimpleBlock;
        //  1106: dup            
        //  1107: aconst_null    
        //  1108: aconst_null    
        //  1109: aload           9
        //  1111: aload_0        
        //  1112: aload           8
        //  1114: invokespecial   com/jetbrains/cidr/lang/formatting/OCSimpleBlock.<init>:(Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;Lcom/intellij/formatting/Indent;Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;Ljava/util/List;)V
        //  1117: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //  1122: pop            
        //  1123: goto            1140
        //  1126: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1129: athrow         
        //  1130: aload           7
        //  1132: aload           8
        //  1134: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //  1139: pop            
        //  1140: aload           7
        //  1142: dup            
        //  1143: ifnonnull       1180
        //  1146: new             Ljava/lang/IllegalStateException;
        //  1149: dup            
        //  1150: ldc             "@NotNull method %s.%s must not return null"
        //  1152: ldc             2
        //  1154: anewarray       Ljava/lang/Object;
        //  1157: dup            
        //  1158: ldc             0
        //  1160: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //  1162: aastore        
        //  1163: dup            
        //  1164: ldc             1
        //  1166: ldc             "postProcessBlockCollection"
        //  1168: aastore        
        //  1169: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1172: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1175: athrow         
        //  1176: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1179: athrow         
        //  1180: areturn        
        //    Signature:
        //  (Ljava/util/List<Lcom/intellij/formatting/Block;>;Lcom/jetbrains/cidr/lang/formatting/OCWrappingProcessor;)Ljava/util/List<Lcom/intellij/formatting/Block;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  107    121    124    128    Ljava/lang/IllegalArgumentException;
        //  114    132    132    136    Ljava/lang/IllegalArgumentException;
        //  202    220    220    224    Ljava/lang/IllegalArgumentException;
        //  227    242    245    249    Ljava/lang/IllegalArgumentException;
        //  261    273    276    280    Ljava/lang/IllegalArgumentException;
        //  268    288    291    295    Ljava/lang/IllegalArgumentException;
        //  280    309    312    316    Ljava/lang/IllegalArgumentException;
        //  295    324    327    331    Ljava/lang/IllegalArgumentException;
        //  399    411    414    418    Ljava/lang/IllegalArgumentException;
        //  406    429    432    436    Ljava/lang/IllegalArgumentException;
        //  418    444    447    451    Ljava/lang/IllegalArgumentException;
        //  475    485    485    489    Ljava/lang/IllegalArgumentException;
        //  498    535    538    542    Ljava/lang/IllegalArgumentException;
        //  549    561    564    568    Ljava/lang/IllegalArgumentException;
        //  554    576    579    583    Ljava/lang/IllegalArgumentException;
        //  595    607    610    614    Ljava/lang/IllegalArgumentException;
        //  600    622    625    629    Ljava/lang/IllegalArgumentException;
        //  691    706    709    713    Ljava/lang/IllegalArgumentException;
        //  701    724    727    731    Ljava/lang/IllegalArgumentException;
        //  713    742    745    749    Ljava/lang/IllegalArgumentException;
        //  731    757    760    764    Ljava/lang/IllegalArgumentException;
        //  749    777    777    781    Ljava/lang/IllegalArgumentException;
        //  794    841    844    848    Ljava/lang/IllegalArgumentException;
        //  848    863    866    870    Ljava/lang/IllegalArgumentException;
        //  870    885    888    892    Ljava/lang/IllegalArgumentException;
        //  877    902    905    909    Ljava/lang/IllegalArgumentException;
        //  892    936    939    943    Ljava/lang/IllegalArgumentException;
        //  1009   1026   1029   1033   Ljava/lang/IllegalArgumentException;
        //  1019   1038   1041   1045   Ljava/lang/IllegalArgumentException;
        //  1033   1062   1065   1069   Ljava/lang/IllegalArgumentException;
        //  1045   1074   1074   1078   Ljava/lang/IllegalArgumentException;
        //  1096   1126   1126   1130   Ljava/lang/IllegalArgumentException;
        //  1140   1176   1176   1180   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0280:
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
    
    private int a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //     4: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //     9: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //    12: astore_1       
        //    13: aload_1        
        //    14: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OC_FILE:Lcom/intellij/psi/tree/IFileElementType;
        //    17: if_acmpeq       48
        //    20: aload_1        
        //    21: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_EXTERN_BLOCK:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    24: if_acmpeq       48
        //    27: goto            34
        //    30: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    33: athrow         
        //    34: aload_1        
        //    35: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_NAMESPACE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    38: if_acmpne       62
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    47: athrow         
        //    48: aload_0        
        //    49: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myOCSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //    52: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_TOP_AFTER_RETURN_TYPE_WRAP:I
        //    55: goto            69
        //    58: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aload_0        
        //    63: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myOCSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //    66: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.FUNCTION_NON_TOP_AFTER_RETURN_TYPE_WRAP:I
        //    69: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  13     27     30     34     Ljava/lang/IllegalArgumentException;
        //  20     41     44     48     Ljava/lang/IllegalArgumentException;
        //  34     58     58     62     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0034:
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
    
    public boolean isInDirective() {
        return this.myInDirective;
    }
    
    @Nullable
    public static ASTNode getBlockNode(@Nullable final Block block) {
        try {
            if (block instanceof ASTBlock) {
                return ((ASTBlock)block).getNode();
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Nullable
    public static IElementType getBlockType(@Nullable final Block block) {
        return OCElementUtil.getElementType(getBlockNode(block));
    }
    
    protected OCCodeBlock createChildBlock(final ASTNode p0, final OCWrappingProcessor p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //     6: astore_3       
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myInDirective:Z
        //    11: ifne            28
        //    14: aload_3        
        //    15: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    18: if_acmpne       76
        //    21: goto            28
        //    24: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    27: athrow         
        //    28: aload_2        
        //    29: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getJoinFormatting:()Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //    32: astore          4
        //    34: new             Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;
        //    37: dup            
        //    38: aload_0        
        //    39: aload_0        
        //    40: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //    43: aload_0        
        //    44: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myOCSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //    47: aload_0        
        //    48: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myProject:Lcom/intellij/openapi/project/Project;
        //    51: aload_1        
        //    52: aload_0        
        //    53: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myGlobalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCGlobalFormatterData;
        //    56: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //    59: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //    62: aload           4
        //    64: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //    67: aload           4
        //    69: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.alignment:Lcom/intellij/formatting/Alignment;
        //    72: invokespecial   com/jetbrains/cidr/lang/formatting/OCCodeBlock.<init>:(Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;Lcom/intellij/openapi/project/Project;Lcom/intellij/lang/ASTNode;Lcom/jetbrains/cidr/lang/formatting/OCGlobalFormatterData;Lcom/intellij/formatting/Indent;Lcom/intellij/formatting/Indent;Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)V
        //    75: areturn        
        //    76: aload_2        
        //    77: aload_1        
        //    78: aload_3        
        //    79: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.calculate:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //    82: astore          4
        //    84: aload_0        
        //    85: invokevirtual   com/jetbrains/cidr/lang/formatting/OCCodeBlock.getSettings:()Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //    88: aload_0        
        //    89: invokevirtual   com/jetbrains/cidr/lang/formatting/OCCodeBlock.getOCSettings:()Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //    92: aload_1        
        //    93: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isNestedInlineBlock:(Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;Lcom/intellij/lang/ASTNode;)Z
        //    96: ifeq            109
        //    99: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   102: goto            124
        //   105: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: aload_0        
        //   110: aload_0        
        //   111: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //   114: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   119: aload_1        
        //   120: aload_3        
        //   121: invokevirtual   com/jetbrains/cidr/lang/formatting/OCCodeBlock.calcChildIndent:(Lcom/intellij/psi/tree/IElementType;Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/formatting/Indent;
        //   124: astore          5
        //   126: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.STATEMENTS:Lcom/intellij/psi/tree/TokenSet;
        //   129: aload_3        
        //   130: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   133: ifne            164
        //   136: aload_3        
        //   137: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isVariablesListOrStructure:(Lcom/intellij/psi/tree/IElementType;)Z
        //   140: ifne            164
        //   143: goto            150
        //   146: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: aload_1        
        //   151: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isNamespaceWithKeyword:(Lcom/intellij/lang/ASTNode;)Z
        //   154: ifeq            216
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: new             Lcom/jetbrains/cidr/lang/formatting/OCNormalIndentBlock;
        //   167: dup            
        //   168: aload_0        
        //   169: aload_0        
        //   170: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   173: aload_0        
        //   174: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myOCSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   177: aload_0        
        //   178: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myProject:Lcom/intellij/openapi/project/Project;
        //   181: aload_1        
        //   182: aload_0        
        //   183: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myGlobalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCGlobalFormatterData;
        //   186: aload           5
        //   188: getstatic       com/intellij/formatting/Indent$Type.NORMAL:Lcom/intellij/formatting/Indent$Type;
        //   191: iconst_0       
        //   192: iconst_1       
        //   193: invokestatic    com/intellij/formatting/Indent.getIndent:(Lcom/intellij/formatting/Indent$Type;ZZ)Lcom/intellij/formatting/Indent;
        //   196: aload           4
        //   198: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //   201: aload           4
        //   203: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.alignment:Lcom/intellij/formatting/Alignment;
        //   206: invokespecial   com/jetbrains/cidr/lang/formatting/OCNormalIndentBlock.<init>:(Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;Lcom/intellij/openapi/project/Project;Lcom/intellij/lang/ASTNode;Lcom/jetbrains/cidr/lang/formatting/OCGlobalFormatterData;Lcom/intellij/formatting/Indent;Lcom/intellij/formatting/Indent;Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)V
        //   209: goto            256
        //   212: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: new             Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;
        //   219: dup            
        //   220: aload_0        
        //   221: aload_0        
        //   222: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   225: aload_0        
        //   226: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myOCSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   229: aload_0        
        //   230: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myProject:Lcom/intellij/openapi/project/Project;
        //   233: aload_1        
        //   234: aload_0        
        //   235: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myGlobalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCGlobalFormatterData;
        //   238: aload           5
        //   240: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   243: aload           4
        //   245: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.wrap:Lcom/intellij/formatting/Wrap;
        //   248: aload           4
        //   250: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo.alignment:Lcom/intellij/formatting/Alignment;
        //   253: invokespecial   com/jetbrains/cidr/lang/formatting/OCCodeBlock.<init>:(Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;Lcom/intellij/openapi/project/Project;Lcom/intellij/lang/ASTNode;Lcom/jetbrains/cidr/lang/formatting/OCGlobalFormatterData;Lcom/intellij/formatting/Indent;Lcom/intellij/formatting/Indent;Lcom/intellij/formatting/Wrap;Lcom/intellij/formatting/Alignment;)V
        //   256: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      21     24     28     Ljava/lang/IllegalArgumentException;
        //  84     105    105    109    Ljava/lang/IllegalArgumentException;
        //  126    143    146    150    Ljava/lang/IllegalArgumentException;
        //  136    157    160    164    Ljava/lang/IllegalArgumentException;
        //  150    212    212    216    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0150:
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
    protected Indent calcChildIndentAhead(@NotNull final IElementType p0, @NotNull final ASTNode p1, @NotNull final IElementType p2) {
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
        //    18: ldc             "thisType"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "calcChildIndentAhead"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "child"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "calcChildIndentAhead"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "childType"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "calcChildIndentAhead"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_0        
        //   133: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.mySettings:Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
        //   136: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.KEEP_FIRST_COLUMN_COMMENT:Z
        //   139: ifeq            203
        //   142: aload_3        
        //   143: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BLOCK_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   146: if_acmpeq       170
        //   149: goto            156
        //   152: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   155: athrow         
        //   156: aload_3        
        //   157: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EOL_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   160: if_acmpne       203
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   169: athrow         
        //   170: invokestatic    com/intellij/psi/impl/source/codeStyle/IndentHelper.getInstance:()Lcom/intellij/psi/impl/source/codeStyle/IndentHelper;
        //   173: aload_0        
        //   174: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myProject:Lcom/intellij/openapi/project/Project;
        //   177: getstatic       com/jetbrains/cidr/lang/OCFileType.INSTANCE:Lcom/jetbrains/cidr/lang/OCFileType;
        //   180: aload_2        
        //   181: iconst_1       
        //   182: invokevirtual   com/intellij/psi/impl/source/codeStyle/IndentHelper.getIndent:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/fileTypes/FileType;Lcom/intellij/lang/ASTNode;Z)I
        //   185: ifne            203
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: invokestatic    com/intellij/formatting/Indent.getAbsoluteNoneIndent:()Lcom/intellij/formatting/Indent;
        //   198: areturn        
        //   199: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: aload_3        
        //   204: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   207: if_acmpne       439
        //   210: aload_2        
        //   211: invokestatic    com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.isMultiline:(Lcom/intellij/lang/ASTNode;)Z
        //   214: ifeq            418
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   223: athrow         
        //   224: aconst_null    
        //   225: astore          4
        //   227: aload_0        
        //   228: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myGlobalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCGlobalFormatterData;
        //   231: getfield        com/jetbrains/cidr/lang/formatting/OCGlobalFormatterData.preprocessorData:Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData;
        //   234: getfield        com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.directiveNestLevel:I
        //   237: ifle            284
        //   240: aload_0        
        //   241: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myGlobalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCGlobalFormatterData;
        //   244: getfield        com/jetbrains/cidr/lang/formatting/OCGlobalFormatterData.preprocessorData:Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData;
        //   247: dup            
        //   248: getfield        com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.directiveNestLevel:I
        //   251: iconst_1       
        //   252: isub           
        //   253: putfield        com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.directiveNestLevel:I
        //   256: aload_0        
        //   257: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myGlobalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCGlobalFormatterData;
        //   260: getfield        com/jetbrains/cidr/lang/formatting/OCGlobalFormatterData.preprocessorData:Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData;
        //   263: invokevirtual   com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.getIndentAtLevel:()Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OCIndentInfo;
        //   266: astore          4
        //   268: aload_0        
        //   269: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myGlobalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCGlobalFormatterData;
        //   272: getfield        com/jetbrains/cidr/lang/formatting/OCGlobalFormatterData.preprocessorData:Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData;
        //   275: dup            
        //   276: getfield        com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.directiveNestLevel:I
        //   279: iconst_1       
        //   280: iadd           
        //   281: putfield        com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.directiveNestLevel:I
        //   284: iconst_1       
        //   285: istore          5
        //   287: getstatic       com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.AT_FIRST_COL_HINT:Lcom/intellij/openapi/util/Key;
        //   290: aload_2        
        //   291: invokevirtual   com/intellij/openapi/util/Key.get:(Lcom/intellij/openapi/util/UserDataHolder;)Ljava/lang/Object;
        //   294: checkcast       Ljava/lang/Boolean;
        //   297: astore          6
        //   299: aload           6
        //   301: ifnull          322
        //   304: aload           6
        //   306: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   309: istore          5
        //   311: getstatic       com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.AT_FIRST_COL_HINT:Lcom/intellij/openapi/util/Key;
        //   314: aload_2        
        //   315: aconst_null    
        //   316: invokevirtual   com/intellij/openapi/util/Key.set:(Lcom/intellij/openapi/util/UserDataHolder;Ljava/lang/Object;)V
        //   319: goto            355
        //   322: aload_2        
        //   323: invokeinterface com/intellij/lang/ASTNode.getTreePrev:()Lcom/intellij/lang/ASTNode;
        //   328: astore          7
        //   330: aload           7
        //   332: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //   335: getstatic       com/intellij/psi/TokenType.WHITE_SPACE:Lcom/intellij/psi/tree/IElementType;
        //   338: if_acmpne       355
        //   341: aload           7
        //   343: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //   348: ldc             "\n"
        //   350: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   353: istore          5
        //   355: getstatic       com/jetbrains/cidr/lang/formatting/OCMultilineNodeFormatter.PREPROCESSOR_INFO:Lcom/intellij/openapi/util/Key;
        //   358: aload_2        
        //   359: iload           5
        //   361: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   364: aload           4
        //   366: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   369: invokevirtual   com/intellij/openapi/util/Key.set:(Lcom/intellij/openapi/util/UserDataHolder;Ljava/lang/Object;)V
        //   372: aload_0        
        //   373: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myGlobalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCGlobalFormatterData;
        //   376: getfield        com/jetbrains/cidr/lang/formatting/OCGlobalFormatterData.formattingMode:Lcom/intellij/formatting/FormattingMode;
        //   379: getstatic       com/intellij/formatting/FormattingMode.ADJUST_INDENT:Lcom/intellij/formatting/FormattingMode;
        //   382: if_acmpne       394
        //   385: aload_0        
        //   386: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myChildIndent:Lcom/intellij/formatting/Indent;
        //   389: areturn        
        //   390: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   393: athrow         
        //   394: aload_0        
        //   395: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myLocalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //   398: aload_2        
        //   399: aload_0        
        //   400: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myChildIndent:Lcom/intellij/formatting/Indent;
        //   403: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Indent;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //   406: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.put:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //   411: pop            
        //   412: bipush          120
        //   414: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.getSpaceIndentEnforcedToChildren:(I)Lcom/intellij/formatting/Indent;
        //   417: areturn        
        //   418: aload_2        
        //   419: invokeinterface com/intellij/lang/ASTNode.getChars:()Ljava/lang/CharSequence;
        //   424: iconst_0       
        //   425: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   430: bipush          35
        //   432: if_icmpne       439
        //   435: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.HASH:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   438: astore_3       
        //   439: getstatic       com/jetbrains/cidr/lang/formatting/OCFormatterUtil.FORMAT_DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //   442: aload_3        
        //   443: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   446: ifeq            671
        //   449: aload_2        
        //   450: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   455: aload_0        
        //   456: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myGlobalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCGlobalFormatterData;
        //   459: getfield        com/jetbrains/cidr/lang/formatting/OCGlobalFormatterData.guardIfndef:Lcom/jetbrains/cidr/lang/psi/OCDirective;
        //   462: if_acmpeq       671
        //   465: goto            472
        //   468: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   471: athrow         
        //   472: aload_3        
        //   473: astore          4
        //   475: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //   478: aload_3        
        //   479: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   482: ifeq            516
        //   485: aload_2        
        //   486: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   491: astore          5
        //   493: aload           5
        //   495: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDirective;
        //   498: ifeq            516
        //   501: aload           5
        //   503: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDirective;
        //   506: invokeinterface com/jetbrains/cidr/lang/psi/OCDirective.getHeaderToken:()Lcom/intellij/psi/PsiElement;
        //   511: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   514: astore          4
        //   516: aload_0        
        //   517: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myGlobalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCGlobalFormatterData;
        //   520: getfield        com/jetbrains/cidr/lang/formatting/OCGlobalFormatterData.preprocessorData:Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData;
        //   523: aload           4
        //   525: aload_0        
        //   526: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myOCSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   529: invokestatic    com/intellij/psi/impl/source/codeStyle/IndentHelper.getInstance:()Lcom/intellij/psi/impl/source/codeStyle/IndentHelper;
        //   532: aload_0        
        //   533: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myProject:Lcom/intellij/openapi/project/Project;
        //   536: getstatic       com/jetbrains/cidr/lang/OCFileType.INSTANCE:Lcom/jetbrains/cidr/lang/OCFileType;
        //   539: aload_2        
        //   540: iconst_1       
        //   541: invokevirtual   com/intellij/psi/impl/source/codeStyle/IndentHelper.getIndent:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/fileTypes/FileType;Lcom/intellij/lang/ASTNode;Z)I
        //   544: ifne            555
        //   547: iconst_1       
        //   548: goto            556
        //   551: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   554: athrow         
        //   555: iconst_0       
        //   556: invokevirtual   com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.getIndents:(Lcom/intellij/psi/tree/IElementType;Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;Z)Lcom/intellij/openapi/util/Pair;
        //   559: astore          5
        //   561: aload           5
        //   563: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   566: getstatic       com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OffsetType.DIRECTIVE_ABSOLUTE:Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OffsetType;
        //   569: if_acmpne       601
        //   572: aload           5
        //   574: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   577: checkcast       Ljava/lang/Integer;
        //   580: invokevirtual   java/lang/Integer.intValue:()I
        //   583: ifne            601
        //   586: goto            593
        //   589: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   592: athrow         
        //   593: iconst_1       
        //   594: goto            602
        //   597: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   600: athrow         
        //   601: iconst_0       
        //   602: istore          6
        //   604: iload           6
        //   606: ifeq            617
        //   609: invokestatic    com/intellij/formatting/Indent.getAbsoluteNoneIndent:()Lcom/intellij/formatting/Indent;
        //   612: areturn        
        //   613: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   616: athrow         
        //   617: aload_0        
        //   618: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myLocalFormatterData:Lcom/jetbrains/cidr/lang/formatting/OCLocalFormatterData;
        //   621: aload_2        
        //   622: aload           5
        //   624: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   627: getstatic       com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OffsetType.DIRECTIVE_ABSOLUTE:Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OffsetType;
        //   630: if_acmpne       643
        //   633: invokestatic    com/intellij/formatting/Indent.getAbsoluteNoneIndent:()Lcom/intellij/formatting/Indent;
        //   636: goto            647
        //   639: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   642: athrow         
        //   643: aload_0        
        //   644: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myChildIndent:Lcom/intellij/formatting/Indent;
        //   647: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo.createInfo:(Lcom/intellij/formatting/Indent;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //   650: invokeinterface com/jetbrains/cidr/lang/formatting/OCLocalFormatterData.put:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;)Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo;
        //   655: pop            
        //   656: aload           5
        //   658: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   661: checkcast       Ljava/lang/Integer;
        //   664: invokevirtual   java/lang/Integer.intValue:()I
        //   667: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.getSpaceIndentEnforcedToChildren:(I)Lcom/intellij/formatting/Indent;
        //   670: areturn        
        //   671: aload_3        
        //   672: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   675: if_acmpne       715
        //   678: aload_1        
        //   679: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_BASE_CLAUSE_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   682: if_acmpne       700
        //   685: goto            692
        //   688: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   691: athrow         
        //   692: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   695: areturn        
        //   696: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   699: athrow         
        //   700: aload_1        
        //   701: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   704: if_acmpne       715
        //   707: invokestatic    com/intellij/formatting/Indent.getContinuationIndent:()Lcom/intellij/formatting/Indent;
        //   710: areturn        
        //   711: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   714: athrow         
        //   715: aload_2        
        //   716: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isOCVisibilityKeyword:(Lcom/intellij/lang/ASTNode;)Z
        //   719: ifne            750
        //   722: aload_2        
        //   723: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isCPPVisibilityKeyword:(Lcom/intellij/lang/ASTNode;)Z
        //   726: ifeq            759
        //   729: goto            736
        //   732: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   735: athrow         
        //   736: aload_1        
        //   737: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isStructure:(Lcom/intellij/psi/tree/IElementType;)Z
        //   740: ifeq            759
        //   743: goto            750
        //   746: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   749: athrow         
        //   750: aload_0        
        //   751: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myChildIndentEx:Lcom/intellij/formatting/Indent;
        //   754: areturn        
        //   755: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   758: athrow         
        //   759: aload_3        
        //   760: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   763: if_acmpeq       822
        //   766: aload_3        
        //   767: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   770: if_acmpeq       822
        //   773: goto            780
        //   776: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   779: athrow         
        //   780: aload_1        
        //   781: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_LAMBDA_INTRODUCER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   784: if_acmpne       831
        //   787: goto            794
        //   790: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   793: athrow         
        //   794: aload_3        
        //   795: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   798: if_acmpeq       822
        //   801: goto            808
        //   804: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   807: athrow         
        //   808: aload_3        
        //   809: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   812: if_acmpne       831
        //   815: goto            822
        //   818: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   821: athrow         
        //   822: aload_0        
        //   823: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myBracesIndent:Lcom/intellij/formatting/Indent;
        //   826: areturn        
        //   827: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   830: athrow         
        //   831: aconst_null    
        //   832: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    149    152    156    Ljava/lang/IllegalArgumentException;
        //  142    163    166    170    Ljava/lang/IllegalArgumentException;
        //  156    188    191    195    Ljava/lang/IllegalArgumentException;
        //  170    199    199    203    Ljava/lang/IllegalArgumentException;
        //  203    217    220    224    Ljava/lang/IllegalArgumentException;
        //  355    390    390    394    Ljava/lang/IllegalArgumentException;
        //  439    465    468    472    Ljava/lang/IllegalArgumentException;
        //  516    551    551    555    Ljava/lang/IllegalArgumentException;
        //  561    586    589    593    Ljava/lang/IllegalArgumentException;
        //  572    597    597    601    Ljava/lang/IllegalArgumentException;
        //  604    613    613    617    Ljava/lang/IllegalArgumentException;
        //  617    639    639    643    Ljava/lang/IllegalArgumentException;
        //  671    685    688    692    Ljava/lang/IllegalArgumentException;
        //  678    696    696    700    Ljava/lang/IllegalArgumentException;
        //  700    711    711    715    Ljava/lang/IllegalArgumentException;
        //  715    729    732    736    Ljava/lang/IllegalArgumentException;
        //  722    743    746    750    Ljava/lang/IllegalArgumentException;
        //  736    755    755    759    Ljava/lang/IllegalArgumentException;
        //  759    773    776    780    Ljava/lang/IllegalArgumentException;
        //  766    787    790    794    Ljava/lang/IllegalArgumentException;
        //  780    801    804    808    Ljava/lang/IllegalArgumentException;
        //  794    815    818    822    Ljava/lang/IllegalArgumentException;
        //  808    827    827    831    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0156:
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
    protected Indent calcChildIndent(@NotNull final IElementType p0, @NotNull final ASTNode p1, @NotNull final IElementType p2) {
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
        //    18: ldc             "thisType"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "calcChildIndent"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "child"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "calcChildIndent"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "childType"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/formatting/OCCodeBlock"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "calcChildIndent"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_0        
        //   133: aload_1        
        //   134: aload_2        
        //   135: aload_3        
        //   136: invokevirtual   com/jetbrains/cidr/lang/formatting/OCCodeBlock.calcChildIndentAhead:(Lcom/intellij/psi/tree/IElementType;Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/formatting/Indent;
        //   139: astore          4
        //   141: aload           4
        //   143: ifnull          153
        //   146: aload           4
        //   148: areturn        
        //   149: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.NS_COLLECTION_LITERALS:Lcom/intellij/psi/tree/TokenSet;
        //   156: aload_1        
        //   157: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   160: ifeq            185
        //   163: aload_3        
        //   164: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   167: if_acmpne       185
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   180: areturn        
        //   181: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: aload_3        
        //   186: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   189: if_acmpeq       206
        //   192: aload_3        
        //   193: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   196: if_acmpne       214
        //   199: goto            206
        //   202: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   205: athrow         
        //   206: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   209: areturn        
        //   210: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   213: athrow         
        //   214: aload_1        
        //   215: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   218: if_acmpeq       235
        //   221: aload_1        
        //   222: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TEMPLATE_ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   225: if_acmpne       285
        //   228: goto            235
        //   231: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   234: athrow         
        //   235: aload_3        
        //   236: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   239: if_acmpeq       277
        //   242: goto            249
        //   245: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: aload_3        
        //   250: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   253: if_acmpeq       277
        //   256: goto            263
        //   259: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   262: athrow         
        //   263: aload_3        
        //   264: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GTGT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   267: if_acmpne       285
        //   270: goto            277
        //   273: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   280: areturn        
        //   281: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   284: athrow         
        //   285: aload_3        
        //   286: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   289: if_acmpne       309
        //   292: aload_2        
        //   293: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   296: invokestatic    com/intellij/psi/formatter/FormatterUtil.isFollowedBy:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Z
        //   299: ifeq            323
        //   302: goto            309
        //   305: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   308: athrow         
        //   309: aload_3        
        //   310: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   313: if_acmpne       331
        //   316: goto            323
        //   319: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   322: athrow         
        //   323: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   326: areturn        
        //   327: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   330: athrow         
        //   331: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CLASSES:Lcom/intellij/psi/tree/TokenSet;
        //   334: aload_1        
        //   335: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   338: ifeq            434
        //   341: aload_2        
        //   342: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isClassMember:(Lcom/intellij/lang/ASTNode;)Z
        //   345: ifne            386
        //   348: goto            355
        //   351: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   354: athrow         
        //   355: aload_3        
        //   356: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.INSTANCE_VARIABLES_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   359: if_acmpne       434
        //   362: goto            369
        //   365: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   368: athrow         
        //   369: aload_0        
        //   370: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myOCSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   373: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.INDENT_INTERFACE_MEMBERS_EXCEPT_IVARS_BLOCK:Z
        //   376: ifne            434
        //   379: goto            386
        //   382: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   385: athrow         
        //   386: aload_1        
        //   387: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.IMPLEMENTATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   390: if_acmpne       414
        //   393: goto            400
        //   396: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   399: athrow         
        //   400: aload_0        
        //   401: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myOCSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   404: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.INDENT_IMPLEMENTATION_MEMBERS:I
        //   407: goto            421
        //   410: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   413: athrow         
        //   414: aload_0        
        //   415: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myOCSettings:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   418: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.INDENT_INTERFACE_MEMBERS:I
        //   421: istore          5
        //   423: getstatic       com/intellij/formatting/Indent$Type.SPACES:Lcom/intellij/formatting/Indent$Type;
        //   426: iload           5
        //   428: iconst_0       
        //   429: iconst_1       
        //   430: invokestatic    com/intellij/formatting/Indent.getIndent:(Lcom/intellij/formatting/Indent$Type;IZZ)Lcom/intellij/formatting/Indent;
        //   433: areturn        
        //   434: aload_1        
        //   435: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.BLOCK_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   438: if_acmpne       449
        //   441: invokestatic    com/intellij/formatting/Indent.getNoneIndent:()Lcom/intellij/formatting/Indent;
        //   444: areturn        
        //   445: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   448: athrow         
        //   449: aload_3        
        //   450: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   453: if_acmpeq       526
        //   456: aload_3        
        //   457: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   460: if_acmpeq       526
        //   463: goto            470
        //   466: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   469: athrow         
        //   470: aload_1        
        //   471: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   474: if_acmpeq       526
        //   477: goto            484
        //   480: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   483: athrow         
        //   484: aload_1        
        //   485: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   488: if_acmpeq       526
        //   491: goto            498
        //   494: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   497: athrow         
        //   498: aload_3        
        //   499: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   502: if_acmpeq       526
        //   505: goto            512
        //   508: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   511: athrow         
        //   512: aload_3        
        //   513: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TEMPLATE_ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   516: if_acmpne       534
        //   519: goto            526
        //   522: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   525: athrow         
        //   526: iconst_1       
        //   527: goto            535
        //   530: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   533: athrow         
        //   534: iconst_0       
        //   535: istore          5
        //   537: aload_1        
        //   538: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.BINARY_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   541: if_acmpeq       586
        //   544: aload_1        
        //   545: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSION_STATEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   548: if_acmpeq       586
        //   551: goto            558
        //   554: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   557: athrow         
        //   558: aload_1        
        //   559: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CALL_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   562: if_acmpeq       586
        //   565: goto            572
        //   568: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   571: athrow         
        //   572: aload_1        
        //   573: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.QUALIFIED_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   576: if_acmpne       594
        //   579: goto            586
        //   582: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   585: athrow         
        //   586: iconst_1       
        //   587: goto            595
        //   590: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   593: athrow         
        //   594: iconst_0       
        //   595: istore          6
        //   597: iload           5
        //   599: ifne            614
        //   602: iload           6
        //   604: ifeq            622
        //   607: goto            614
        //   610: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   613: athrow         
        //   614: invokestatic    com/intellij/formatting/Indent.getContinuationWithoutFirstIndent:()Lcom/intellij/formatting/Indent;
        //   617: areturn        
        //   618: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   621: athrow         
        //   622: aload_0        
        //   623: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myNode:Lcom/intellij/lang/ASTNode;
        //   626: aload_1        
        //   627: aload_2        
        //   628: aload_3        
        //   629: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.a:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Z
        //   632: ifeq            643
        //   635: invokestatic    com/intellij/formatting/Indent.getContinuationIndent:()Lcom/intellij/formatting/Indent;
        //   638: areturn        
        //   639: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   642: athrow         
        //   643: aload_0        
        //   644: getfield        com/jetbrains/cidr/lang/formatting/OCCodeBlock.myChildIndent:Lcom/intellij/formatting/Indent;
        //   647: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  141    149    149    153    Ljava/lang/IllegalArgumentException;
        //  153    170    173    177    Ljava/lang/IllegalArgumentException;
        //  163    181    181    185    Ljava/lang/IllegalArgumentException;
        //  185    199    202    206    Ljava/lang/IllegalArgumentException;
        //  192    210    210    214    Ljava/lang/IllegalArgumentException;
        //  214    228    231    235    Ljava/lang/IllegalArgumentException;
        //  221    242    245    249    Ljava/lang/IllegalArgumentException;
        //  235    256    259    263    Ljava/lang/IllegalArgumentException;
        //  249    270    273    277    Ljava/lang/IllegalArgumentException;
        //  263    281    281    285    Ljava/lang/IllegalArgumentException;
        //  285    302    305    309    Ljava/lang/IllegalArgumentException;
        //  292    316    319    323    Ljava/lang/IllegalArgumentException;
        //  309    327    327    331    Ljava/lang/IllegalArgumentException;
        //  331    348    351    355    Ljava/lang/IllegalArgumentException;
        //  341    362    365    369    Ljava/lang/IllegalArgumentException;
        //  355    379    382    386    Ljava/lang/IllegalArgumentException;
        //  369    393    396    400    Ljava/lang/IllegalArgumentException;
        //  386    410    410    414    Ljava/lang/IllegalArgumentException;
        //  434    445    445    449    Ljava/lang/IllegalArgumentException;
        //  449    463    466    470    Ljava/lang/IllegalArgumentException;
        //  456    477    480    484    Ljava/lang/IllegalArgumentException;
        //  470    491    494    498    Ljava/lang/IllegalArgumentException;
        //  484    505    508    512    Ljava/lang/IllegalArgumentException;
        //  498    519    522    526    Ljava/lang/IllegalArgumentException;
        //  512    530    530    534    Ljava/lang/IllegalArgumentException;
        //  537    551    554    558    Ljava/lang/IllegalArgumentException;
        //  544    565    568    572    Ljava/lang/IllegalArgumentException;
        //  558    579    582    586    Ljava/lang/IllegalArgumentException;
        //  572    590    590    594    Ljava/lang/IllegalArgumentException;
        //  597    607    610    614    Ljava/lang/IllegalArgumentException;
        //  602    618    618    622    Ljava/lang/IllegalArgumentException;
        //  622    639    639    643    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0235:
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
    @Override
    public ChildAttributes getChildAttributes(final int n) {
        Label_0055: {
            ChildAttributes childAttributes = null;
            Label_0020: {
                try {
                    if (n != 0) {
                        break Label_0055;
                    }
                    final OCCodeBlock ocCodeBlock = this;
                    final int n2 = n;
                    childAttributes = ocCodeBlock.getChildAttributes(n2);
                    if (childAttributes == null) {
                        break Label_0020;
                    }
                    return childAttributes;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final OCCodeBlock ocCodeBlock = this;
                    final int n2 = n;
                    childAttributes = ocCodeBlock.getChildAttributes(n2);
                    if (childAttributes == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCCodeBlock", "getChildAttributes"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            return childAttributes;
        }
        Block block = null;
        for (final Block block2 : ContainerUtil.iterateBackward((List)this.getSubBlocks())) {
            if (!(OCElementUtil.getElementType(OCSimpleBlock.extractLastNode(block2)) instanceof OCPunctuatorElementType)) {
                block = block2;
                break;
            }
        }
        if (block == null) {
            block = this.getSubBlocks().get(n - 1);
        }
        Indent indent = block.getIndent();
        final IElementType elementType = OCElementUtil.getElementType(OCSimpleBlock.extractLastNode(block));
        if (OCFormatterUtil.isAnyLBrace(elementType)) {
            indent = Indent.getContinuationIndent();
        }
        else if (elementType == OCTokenTypes.LT) {
            final IElementType elementType2 = OCElementUtil.getElementType(this.getNode());
            Label_0208: {
                try {
                    if (elementType2 != OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST) {
                        if (elementType2 != OCElementTypes.TEMPLATE_ARGUMENT_LIST) {
                            break Label_0208;
                        }
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                indent = Indent.getContinuationIndent();
            }
        }
        else if (elementType == OCElementTypes.METHOD_SELECTOR_PART) {
            indent = Indent.getContinuationIndent();
        }
        ChildAttributes childAttributes2;
        try {
            childAttributes2 = new ChildAttributes(indent, block.getAlignment());
            if (childAttributes2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCCodeBlock", "getChildAttributes"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return childAttributes2;
    }
    
    public Spacing getSpacing(@Nullable final Block block, @NotNull final Block block2) {
        try {
            if (block2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child2", "com/jetbrains/cidr/lang/formatting/OCCodeBlock", "getSpacing"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return OCSpacingProcessor.getSpacing(this, block, block2);
    }
    
    @Override
    public Indent getIndent() {
        return this.myIndent;
    }
    
    @Override
    public void putIndent(final Indent myIndent) {
        this.myIndent = myIndent;
    }
    
    @Override
    protected Indent getChildIndent() {
        return this.myChildIndent;
    }
    
    public boolean isLeaf() {
        return this.myNode instanceof LeafElement;
    }
    
    @NotNull
    OCLocalFormatterData getLocalFormatterData() {
        OCLocalFormatterData myLocalFormatterData;
        try {
            myLocalFormatterData = this.myLocalFormatterData;
            if (myLocalFormatterData == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCCodeBlock", "getLocalFormatterData"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myLocalFormatterData;
    }
    
    @NotNull
    public CommonCodeStyleSettings getSettings() {
        CommonCodeStyleSettings mySettings;
        try {
            mySettings = this.mySettings;
            if (mySettings == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCCodeBlock", "getSettings"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return mySettings;
    }
    
    @NotNull
    public OCCodeStyleSettings getOCSettings() {
        OCCodeStyleSettings myOCSettings;
        try {
            myOCSettings = this.myOCSettings;
            if (myOCSettings == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCCodeBlock", "getOCSettings"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myOCSettings;
    }
    
    private static boolean a(final ASTNode p0, final IElementType p1, final ASTNode p2, final IElementType p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //     4: if_acmpne       13
        //     7: iconst_1       
        //     8: ireturn        
        //     9: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    12: athrow         
        //    13: aload_1        
        //    14: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TEMPLATE_ARGUMENT_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    17: if_acmpne       26
        //    20: iconst_1       
        //    21: ireturn        
        //    22: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload_1        
        //    27: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_CONSTRUCTOR_INITIALIZATION_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    30: if_acmpne       39
        //    33: iconst_1       
        //    34: ireturn        
        //    35: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: aload_1        
        //    40: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_BASE_CLAUSE_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    43: if_acmpne       52
        //    46: iconst_1       
        //    47: ireturn        
        //    48: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: aload_1        
        //    53: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_LAMBDA_INTRODUCER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    56: if_acmpne       65
        //    59: iconst_1       
        //    60: ireturn        
        //    61: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_1        
        //    66: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_ARGUMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    69: if_acmpne       78
        //    72: iconst_1       
        //    73: ireturn        
        //    74: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    77: athrow         
        //    78: aload_3        
        //    79: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.COMPOUND_INITIALIZER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    82: if_acmpne       91
        //    85: iconst_1       
        //    86: ireturn        
        //    87: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //    94: aload_1        
        //    95: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    98: ifeq            154
        //   101: aload_2        
        //   102: invokeinterface com/intellij/lang/ASTNode.getTreePrev:()Lcom/intellij/lang/ASTNode;
        //   107: ifnull          154
        //   110: goto            117
        //   113: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   116: athrow         
        //   117: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.BLOCK_STATEMENTS:Lcom/intellij/psi/tree/TokenSet;
        //   120: aload_3        
        //   121: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   124: ifeq            148
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: aload_1        
        //   135: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.CPP_LAMBDA_EXPRESSION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   138: if_acmpeq       154
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: iconst_1       
        //   149: ireturn        
        //   150: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload_1        
        //   155: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.SUPER_CLASS_REF:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   158: if_acmpeq       175
        //   161: aload_1        
        //   162: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PROTOCOL_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   165: if_acmpne       181
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: iconst_1       
        //   176: ireturn        
        //   177: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   180: athrow         
        //   181: aload_1        
        //   182: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isCollectionOrStructureInitializer:(Lcom/intellij/psi/tree/IElementType;)Z
        //   185: ifeq            194
        //   188: iconst_1       
        //   189: ireturn        
        //   190: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   193: athrow         
        //   194: aload_0        
        //   195: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
        //   200: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //   203: astore          4
        //   205: aload           4
        //   207: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   210: if_acmpne       273
        //   213: aload_1        
        //   214: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DECLARATOR:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   217: if_acmpne       273
        //   220: goto            227
        //   223: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   226: athrow         
        //   227: aload_0        
        //   228: invokeinterface com/intellij/lang/ASTNode.getTreePrev:()Lcom/intellij/lang/ASTNode;
        //   233: ifnull          273
        //   236: goto            243
        //   239: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   242: athrow         
        //   243: aload_0        
        //   244: invokeinterface com/intellij/lang/ASTNode.getTreePrev:()Lcom/intellij/lang/ASTNode;
        //   249: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //   254: invokevirtual   java/lang/String.isEmpty:()Z
        //   257: ifne            273
        //   260: goto            267
        //   263: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   266: athrow         
        //   267: iconst_1       
        //   268: ireturn        
        //   269: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   272: athrow         
        //   273: aload_2        
        //   274: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isMessageArgumentValue:(Lcom/intellij/lang/ASTNode;)Z
        //   277: ifeq            286
        //   280: iconst_1       
        //   281: ireturn        
        //   282: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   285: athrow         
        //   286: aload_3        
        //   287: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD_SELECTOR_PART:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   290: if_acmpne       316
        //   293: aload_2        
        //   294: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD_SELECTOR_PART:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   297: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterUtil.isPrecededBy:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/IElementType;)Z
        //   300: ifeq            316
        //   303: goto            310
        //   306: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   309: athrow         
        //   310: iconst_1       
        //   311: ireturn        
        //   312: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   315: athrow         
        //   316: aload_1        
        //   317: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.METHOD_SELECTOR_PART:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   320: if_acmpne       345
        //   323: aload_2        
        //   324: invokeinterface com/intellij/lang/ASTNode.getTreePrev:()Lcom/intellij/lang/ASTNode;
        //   329: ifnull          345
        //   332: goto            339
        //   335: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   338: athrow         
        //   339: iconst_1       
        //   340: ireturn        
        //   341: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   344: athrow         
        //   345: iconst_0       
        //   346: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      9      9      13     Ljava/lang/IllegalArgumentException;
        //  13     22     22     26     Ljava/lang/IllegalArgumentException;
        //  26     35     35     39     Ljava/lang/IllegalArgumentException;
        //  39     48     48     52     Ljava/lang/IllegalArgumentException;
        //  52     61     61     65     Ljava/lang/IllegalArgumentException;
        //  65     74     74     78     Ljava/lang/IllegalArgumentException;
        //  78     87     87     91     Ljava/lang/IllegalArgumentException;
        //  91     110    113    117    Ljava/lang/IllegalArgumentException;
        //  101    127    130    134    Ljava/lang/IllegalArgumentException;
        //  117    141    144    148    Ljava/lang/IllegalArgumentException;
        //  134    150    150    154    Ljava/lang/IllegalArgumentException;
        //  154    168    171    175    Ljava/lang/IllegalArgumentException;
        //  161    177    177    181    Ljava/lang/IllegalArgumentException;
        //  181    190    190    194    Ljava/lang/IllegalArgumentException;
        //  205    220    223    227    Ljava/lang/IllegalArgumentException;
        //  213    236    239    243    Ljava/lang/IllegalArgumentException;
        //  227    260    263    267    Ljava/lang/IllegalArgumentException;
        //  243    269    269    273    Ljava/lang/IllegalArgumentException;
        //  273    282    282    286    Ljava/lang/IllegalArgumentException;
        //  286    303    306    310    Ljava/lang/IllegalArgumentException;
        //  293    312    312    316    Ljava/lang/IllegalArgumentException;
        //  316    332    335    339    Ljava/lang/IllegalArgumentException;
        //  323    341    341    345    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0117:
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
    
    protected static Indent getDoubleIndent(final CommonCodeStyleSettings commonCodeStyleSettings) {
        return Indent.getSpaceIndent(commonCodeStyleSettings.getIndentOptions().INDENT_SIZE * 2);
    }
    
    protected static Indent getSpaceIndentEnforcedToChildren(final int n) {
        return Indent.getIndent(Indent.Type.SPACES, n, false, true);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCCodeBlock.class.desiredAssertionStatus()) {
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
        COMMA_OR_EXPRESSION = TokenSet.orSet(new TokenSet[] { TokenSet.create(new IElementType[] { OCTokenTypes.COMMA }), OCElementTypes.EXPRESSIONS });
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
