// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.jetbrains.cidr.lang.util.OCStringLiteralUtil;
import java.util.ArrayList;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.Pair;
import java.util.Iterator;
import com.intellij.util.containers.hash.HashMap;
import java.util.Collections;
import java.util.Map;
import com.intellij.psi.TokenType;
import com.intellij.lang.TokenWrapper;
import com.jetbrains.cidr.lang.parser.OCPragmaOnceContentElementType;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.lexer.LexerUtil;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.psi.tree.IElementType;
import java.util.regex.Matcher;
import java.util.List;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.lexer.Lexer;
import com.jetbrains.cidr.lang.lexer.OCLexer;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.intellij.psi.tree.TokenSet;
import com.intellij.openapi.util.Condition;
import com.intellij.util.Function;
import gnu.trove.TObjectIntHashMap;
import java.util.Stack;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.psi.OCFile;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.regex.Pattern;
import com.intellij.openapi.util.Key;
import com.intellij.lexer.LookAheadLexer;

public class OCPreprocessingLexer extends LookAheadLexer
{
    private static final String VARARG_MACRO_PARAMS_SEPARATOR = ",";
    private static final int MACRO_SUBSTITUTIONS_LIMIT_PER_FILE = 200000;
    public static final Key<Boolean> DONT_SUBSTITUTE_IN_DEFINES;
    private static final String LINE_MACRO = "__LINE__";
    private static final String LINE_MACRO_STUB_START = "__CIDR_LINE_MACRO_STUB_";
    private static final Pattern LINE_MACRO_STUB_PATTERN;
    @NotNull
    private final OCInclusionContext myState;
    @Nullable
    private OCContextChangeSet myChangeSet;
    private final boolean myEvalDefinedFunction;
    @Nullable
    private final OCFile myFile;
    private VirtualFile myCurrentFile;
    private PsiFile myIncludeAnchor;
    private int myMacroLevel;
    private Ref<Integer> myTotalMacroSubstitutionsCnt;
    private boolean myAtDefineDirective;
    private final boolean myAddTokensFromDirective;
    private Stack<Boolean> myBraceStack;
    private CurrentState myAtExternDeclaration;
    private boolean myDontSubstituteInDefines;
    private TObjectIntHashMap<PsiFile> myProcessingFiles;
    private int myIncludeLevel;
    private boolean myInsideIfCondition;
    @NotNull
    private final Ref<Long> myLineCount;
    @NotNull
    private final IncludePreprocessingMode myIncludePreprocessingMode;
    private final Function<CharSequence, Boolean> IF_EVAL;
    private final Function<CharSequence, Boolean> IFDEF_EVAL;
    private final Function<CharSequence, Boolean> IFNDEF_EVAL;
    private static final Condition<Boolean> ID;
    private static final TokenSet SKIP_TOKENS;
    
    public OCPreprocessingLexer(@NotNull final OCInclusionContext ocInclusionContext, @Nullable final OCFile ocFile) {
        if (ocInclusionContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "initialSubstitutions", "com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer", "<init>"));
        }
        this(ocInclusionContext, ocFile, false, 0, ocInclusionContext.getInclusionLevel(), (Ref<Integer>)Ref.create((Object)0), IncludePreprocessingMode.USE_SYMBOL_TABLE, (Ref<Long>)Ref.create((Object)1L), false, false);
    }
    
    public OCPreprocessingLexer(@NotNull final OCInclusionContext ocInclusionContext, @Nullable final OCFile ocFile, @NotNull final IncludePreprocessingMode includePreprocessingMode) {
        if (ocInclusionContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "initialSubstitutions", "com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer", "<init>"));
        }
        if (includePreprocessingMode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "includePreprocessingMode", "com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer", "<init>"));
        }
        this(ocInclusionContext, ocFile, false, 0, ocInclusionContext.getInclusionLevel(), (Ref<Integer>)Ref.create((Object)0), includePreprocessingMode, (Ref<Long>)Ref.create((Object)1L), false, false);
    }
    
    public OCPreprocessingLexer(@NotNull final OCInclusionContext ocInclusionContext, @Nullable final OCFile ocFile, final boolean b) {
        if (ocInclusionContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "initialSubstitutions", "com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer", "<init>"));
        }
        this(ocInclusionContext, ocFile, false, 0, ocInclusionContext.getInclusionLevel(), (Ref<Integer>)Ref.create((Object)0), b ? IncludePreprocessingMode.FORCE_PREPROCESSING : IncludePreprocessingMode.USE_SYMBOL_TABLE, (Ref<Long>)Ref.create((Object)1L), false, false);
    }
    
    public OCPreprocessingLexer(@NotNull final OCInclusionContext ocInclusionContext, @Nullable final OCFile ocFile, final int n) {
        if (ocInclusionContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "initialSubstitutions", "com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer", "<init>"));
        }
        this(ocInclusionContext, ocFile, false, 0, ocInclusionContext.getInclusionLevel(), (Ref<Integer>)Ref.create((Object)0), IncludePreprocessingMode.USE_SYMBOL_TABLE, (Ref<Long>)Ref.create((Object)new Long(n)), false, false);
    }
    
    private OCPreprocessingLexer(@NotNull final OCInclusionContext myState, @Nullable final OCFile ocFile, final boolean myEvalDefinedFunction, final int myMacroLevel, final int myIncludeLevel, final Ref<Integer> myTotalMacroSubstitutionsCnt, @NotNull final IncludePreprocessingMode myIncludePreprocessingMode, @NotNull final Ref<Long> myLineCount, final boolean myAtDefineDirective, final boolean myAddTokensFromDirective) {
        if (myState == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "initialSubstitutions", "com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer", "<init>"));
        }
        if (myIncludePreprocessingMode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "includePreprocessingMode", "com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer", "<init>"));
        }
        if (myLineCount == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lineCount", "com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer", "<init>"));
        }
        super((Lexer)new OCLexer(myState.getLanguageKind(), false, OCCompilerFeatures.supportsNullability(myState), OCCompilerFeatures.supportsGccAutoType(myState), OCCompilerFeatures.supportsAvailableExpression(myState)));
        this.myProcessingFiles = (TObjectIntHashMap<PsiFile>)new TObjectIntHashMap();
        this.IF_EVAL = (Function<CharSequence, Boolean>)(charSequence -> this.c(charSequence));
        this.IFDEF_EVAL = (Function<CharSequence, Boolean>)new Function<CharSequence, Boolean>() {
            public Boolean fun(final CharSequence charSequence) {
                final List wordsIn = StringUtil.getWordsIn(charSequence.toString());
                return OCPreprocessingLexer.this.myState.isDefined(wordsIn.isEmpty() ? charSequence.toString().trim() : wordsIn.get(0));
            }
        };
        this.IFNDEF_EVAL = (Function<CharSequence, Boolean>)new Function<CharSequence, Boolean>() {
            public Boolean fun(final CharSequence charSequence) {
                final List wordsIn = StringUtil.getWordsIn(charSequence.toString());
                return !OCPreprocessingLexer.this.myState.isDefined(wordsIn.isEmpty() ? charSequence.toString().trim() : wordsIn.get(0));
            }
        };
        this.myState = myState;
        this.myFile = ocFile;
        this.myIncludeAnchor = (PsiFile)ocFile;
        this.myEvalDefinedFunction = myEvalDefinedFunction;
        this.myMacroLevel = myMacroLevel;
        this.myIncludeLevel = myIncludeLevel;
        this.myTotalMacroSubstitutionsCnt = myTotalMacroSubstitutionsCnt;
        this.myIncludePreprocessingMode = myIncludePreprocessingMode;
        this.myLineCount = myLineCount;
        this.myAtDefineDirective = myAtDefineDirective;
        this.myAddTokensFromDirective = myAddTokensFromDirective;
        this.myBraceStack = new Stack<Boolean>();
        this.myAtExternDeclaration = CurrentState.NONE;
        this.myInsideIfCondition = false;
        boolean myDontSubstituteInDefines = false;
        Label_0316: {
            if (ocFile != null) {
                try {
                    if (ocFile.getProject().getUserData((Key)OCPreprocessingLexer.DONT_SUBSTITUTE_IN_DEFINES) == Boolean.TRUE) {
                        myDontSubstituteInDefines = true;
                        break Label_0316;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
            myDontSubstituteInDefines = false;
        }
        this.myDontSubstituteInDefines = myDontSubstituteInDefines;
    }
    
    protected void lookAhead(final Lexer p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //     4: astore_2       
        //     5: aload_2        
        //     6: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //     9: if_acmpeq       61
        //    12: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //    15: aload_2        
        //    16: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    19: ifne            61
        //    22: goto            29
        //    25: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    28: athrow         
        //    29: aload_2        
        //    30: instanceof      Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    33: ifeq            333
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    42: athrow         
        //    43: aload_2        
        //    44: aload_1        
        //    45: invokestatic    com/intellij/lexer/LexerUtil.getTokenText:(Lcom/intellij/lexer/Lexer;)Ljava/lang/CharSequence;
        //    48: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isAlternativeCppPunctuator:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;)Z
        //    51: ifeq            333
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    60: athrow         
        //    61: aload_1        
        //    62: invokestatic    com/intellij/lexer/LexerUtil.getTokenText:(Lcom/intellij/lexer/Lexer;)Ljava/lang/CharSequence;
        //    65: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //    70: astore_3       
        //    71: aload_0        
        //    72: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myEvalDefinedFunction:Z
        //    75: ifeq            106
        //    78: ldc             "defined"
        //    80: aload_3        
        //    81: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    84: ifeq            106
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    93: athrow         
        //    94: aload_0        
        //    95: aload_1        
        //    96: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.g:(Lcom/intellij/lexer/Lexer;)V
        //    99: goto            330
        //   102: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   105: athrow         
        //   106: aload_0        
        //   107: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myEvalDefinedFunction:Z
        //   110: ifeq            142
        //   113: ldc             "__has_include"
        //   115: aload_3        
        //   116: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   119: ifeq            142
        //   122: goto            129
        //   125: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   128: athrow         
        //   129: aload_0        
        //   130: aload_1        
        //   131: iconst_0       
        //   132: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.c:(Lcom/intellij/lexer/Lexer;Z)V
        //   135: goto            330
        //   138: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   141: athrow         
        //   142: aload_0        
        //   143: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myEvalDefinedFunction:Z
        //   146: ifeq            178
        //   149: ldc             "__has_include_next"
        //   151: aload_3        
        //   152: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   155: ifeq            178
        //   158: goto            165
        //   161: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   164: athrow         
        //   165: aload_0        
        //   166: aload_1        
        //   167: iconst_1       
        //   168: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.c:(Lcom/intellij/lexer/Lexer;Z)V
        //   171: goto            330
        //   174: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   177: athrow         
        //   178: aload_0        
        //   179: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myInsideIfCondition:Z
        //   182: ifne            217
        //   185: aload_0        
        //   186: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myEvalDefinedFunction:Z
        //   189: ifeq            217
        //   192: goto            199
        //   195: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   198: athrow         
        //   199: aload_0        
        //   200: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   203: aload_3        
        //   204: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.isDefined:(Ljava/lang/String;)Z
        //   209: pop            
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   216: athrow         
        //   217: aload_0        
        //   218: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   221: aload_3        
        //   222: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.getDefinition:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   227: astore          4
        //   229: aload           4
        //   231: ifnonnull       261
        //   234: aload_3        
        //   235: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.c:(Ljava/lang/String;)Ljava/lang/String;
        //   238: astore          5
        //   240: aload           5
        //   242: ifnull          261
        //   245: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   248: dup            
        //   249: aconst_null    
        //   250: aconst_null    
        //   251: iconst_m1      
        //   252: aload_3        
        //   253: aconst_null    
        //   254: aload           5
        //   256: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;ILjava/lang/String;Lcom/jetbrains/cidr/lang/util/OCImmutableList;Ljava/lang/String;)V
        //   259: astore          4
        //   261: aload_0        
        //   262: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAtDefineDirective:Z
        //   265: ifne            296
        //   268: aload           4
        //   270: ifnull          296
        //   273: goto            280
        //   276: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   279: athrow         
        //   280: aload_0        
        //   281: aload_1        
        //   282: aload           4
        //   284: iconst_1       
        //   285: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/lexer/Lexer;Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;Z)Z
        //   288: pop            
        //   289: goto            330
        //   292: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   295: athrow         
        //   296: aload_0        
        //   297: aload_2        
        //   298: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.addToken:(Lcom/intellij/psi/tree/IElementType;)V
        //   301: aload_1        
        //   302: invokevirtual   com/intellij/lexer/Lexer.advance:()V
        //   305: aload_0        
        //   306: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myDontSubstituteInDefines:Z
        //   309: ifne            324
        //   312: aload_0        
        //   313: iconst_0       
        //   314: putfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAtDefineDirective:Z
        //   317: goto            324
        //   320: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   323: athrow         
        //   324: aload_0        
        //   325: aload_2        
        //   326: aload_3        
        //   327: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/String;)V
        //   330: goto            584
        //   333: aload_2        
        //   334: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEFINE_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   337: if_acmpne       352
        //   340: aload_0        
        //   341: aload_1        
        //   342: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.b:(Lcom/intellij/lexer/Lexer;)V
        //   345: goto            584
        //   348: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   351: athrow         
        //   352: aload_2        
        //   353: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.UNDEF_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   356: if_acmpne       371
        //   359: aload_0        
        //   360: aload_1        
        //   361: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/lexer/Lexer;)V
        //   364: goto            584
        //   367: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   370: athrow         
        //   371: aload_2        
        //   372: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.INCLUDE_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   375: if_acmpne       392
        //   378: aload_0        
        //   379: aload_1        
        //   380: iconst_0       
        //   381: iconst_0       
        //   382: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.b:(Lcom/intellij/lexer/Lexer;ZZ)V
        //   385: goto            584
        //   388: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   391: athrow         
        //   392: aload_2        
        //   393: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IMPORT_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   396: if_acmpne       413
        //   399: aload_0        
        //   400: aload_1        
        //   401: iconst_1       
        //   402: iconst_0       
        //   403: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.b:(Lcom/intellij/lexer/Lexer;ZZ)V
        //   406: goto            584
        //   409: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   412: athrow         
        //   413: aload_2        
        //   414: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.INCLUDE_NEXT_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   417: if_acmpne       434
        //   420: aload_0        
        //   421: aload_1        
        //   422: iconst_0       
        //   423: iconst_1       
        //   424: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.b:(Lcom/intellij/lexer/Lexer;ZZ)V
        //   427: goto            584
        //   430: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   433: athrow         
        //   434: aload_2        
        //   435: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IF_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   438: if_acmpne       457
        //   441: aload_0        
        //   442: aload_1        
        //   443: aload_0        
        //   444: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.IF_EVAL:Lcom/intellij/util/Function;
        //   447: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/lexer/Lexer;Lcom/intellij/util/Function;)V
        //   450: goto            584
        //   453: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   456: athrow         
        //   457: aload_2        
        //   458: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IFDEF_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   461: if_acmpne       480
        //   464: aload_0        
        //   465: aload_1        
        //   466: aload_0        
        //   467: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.IFDEF_EVAL:Lcom/intellij/util/Function;
        //   470: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/lexer/Lexer;Lcom/intellij/util/Function;)V
        //   473: goto            584
        //   476: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   479: athrow         
        //   480: aload_2        
        //   481: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IFNDEF_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   484: if_acmpne       503
        //   487: aload_0        
        //   488: aload_1        
        //   489: aload_0        
        //   490: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.IFNDEF_EVAL:Lcom/intellij/util/Function;
        //   493: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/lexer/Lexer;Lcom/intellij/util/Function;)V
        //   496: goto            584
        //   499: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   502: athrow         
        //   503: aload_2        
        //   504: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PRAGMA_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   507: if_acmpne       524
        //   510: aload_0        
        //   511: aload_1        
        //   512: bipush          12
        //   514: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/lexer/Lexer;I)V
        //   517: goto            584
        //   520: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   523: athrow         
        //   524: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //   527: aload_2        
        //   528: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   531: ifeq            548
        //   534: aload_0        
        //   535: aload_1        
        //   536: bipush          8
        //   538: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/lexer/Lexer;I)V
        //   541: goto            584
        //   544: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   547: athrow         
        //   548: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   551: aload_2        
        //   552: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   555: ifeq            573
        //   558: aload_0        
        //   559: aload_1        
        //   560: invokestatic    com/intellij/lexer/LexerUtil.getTokenText:(Lcom/intellij/lexer/Lexer;)Ljava/lang/CharSequence;
        //   563: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/CharSequence;)V
        //   566: goto            573
        //   569: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   572: athrow         
        //   573: aload_0        
        //   574: aload_2        
        //   575: aconst_null    
        //   576: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/String;)V
        //   579: aload_0        
        //   580: aload_1        
        //   581: invokespecial   com/intellij/lexer/LookAheadLexer.lookAhead:(Lcom/intellij/lexer/Lexer;)V
        //   584: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  5      22     25     29     Ljava/lang/IllegalArgumentException;
        //  12     36     39     43     Ljava/lang/IllegalArgumentException;
        //  29     54     57     61     Ljava/lang/IllegalArgumentException;
        //  71     87     90     94     Ljava/lang/IllegalArgumentException;
        //  78     102    102    106    Ljava/lang/IllegalArgumentException;
        //  106    122    125    129    Ljava/lang/IllegalArgumentException;
        //  113    138    138    142    Ljava/lang/IllegalArgumentException;
        //  142    158    161    165    Ljava/lang/IllegalArgumentException;
        //  149    174    174    178    Ljava/lang/IllegalArgumentException;
        //  178    192    195    199    Ljava/lang/IllegalArgumentException;
        //  185    210    213    217    Ljava/lang/IllegalArgumentException;
        //  261    273    276    280    Ljava/lang/IllegalArgumentException;
        //  268    292    292    296    Ljava/lang/IllegalArgumentException;
        //  296    317    320    324    Ljava/lang/IllegalArgumentException;
        //  333    348    348    352    Ljava/lang/IllegalArgumentException;
        //  352    367    367    371    Ljava/lang/IllegalArgumentException;
        //  371    388    388    392    Ljava/lang/IllegalArgumentException;
        //  392    409    409    413    Ljava/lang/IllegalArgumentException;
        //  413    430    430    434    Ljava/lang/IllegalArgumentException;
        //  434    453    453    457    Ljava/lang/IllegalArgumentException;
        //  457    476    476    480    Ljava/lang/IllegalArgumentException;
        //  480    499    499    503    Ljava/lang/IllegalArgumentException;
        //  503    520    520    524    Ljava/lang/IllegalArgumentException;
        //  524    544    544    548    Ljava/lang/IllegalArgumentException;
        //  548    566    569    573    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0029:
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
    private static String c(final String s) {
        if (s.length() > "__CIDR_LINE_MACRO_STUB_".length()) {
            final Matcher matcher = OCPreprocessingLexer.LINE_MACRO_STUB_PATTERN.matcher(s);
            try {
                if (matcher.find()) {
                    return matcher.group(1);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    private void a(@NotNull final CharSequence charSequence) {
        try {
            if (charSequence == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tokenText", "com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer", "adjustLineCount"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int countChars = StringUtil.countChars(charSequence, '\n');
        try {
            if (countChars > 0) {
                this.myLineCount.set((Object)((long)this.myLineCount.get() + countChars));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    private void a(final IElementType p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXTERN_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //     4: if_acmpne       21
        //     7: aload_0        
        //     8: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState.AFTER_EXTERN:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //    11: putfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAtExternDeclaration:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //    14: goto            485
        //    17: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    20: athrow         
        //    21: aload_1        
        //    22: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    25: if_acmpne       59
        //    28: aload_0        
        //    29: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAtExternDeclaration:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //    32: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState.AFTER_EXTERN:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //    35: if_acmpne       59
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    44: athrow         
        //    45: aload_0        
        //    46: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState.AFTER_EXTERN_C:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //    49: putfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAtExternDeclaration:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //    52: goto            485
        //    55: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    58: athrow         
        //    59: aload_1        
        //    60: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    63: if_acmpne       80
        //    66: aload_0        
        //    67: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState.AFTER_AT:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //    70: putfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAtExternDeclaration:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //    73: goto            485
        //    76: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    79: athrow         
        //    80: aload_1        
        //    81: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IMPORT_MODULE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    84: if_acmpne       118
        //    87: aload_0        
        //    88: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAtExternDeclaration:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //    91: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState.AFTER_AT:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //    94: if_acmpne       118
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   103: athrow         
        //   104: aload_0        
        //   105: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState.AFTER_MODULE_IMPORT:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //   108: putfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAtExternDeclaration:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //   111: goto            485
        //   114: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   117: athrow         
        //   118: aload_0        
        //   119: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAtExternDeclaration:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //   122: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState.AFTER_MODULE_IMPORT:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //   125: if_acmpne       190
        //   128: aload_1        
        //   129: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   132: if_acmpeq       485
        //   135: goto            142
        //   138: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   141: athrow         
        //   142: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   145: aload_1        
        //   146: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   149: ifne            485
        //   152: goto            159
        //   155: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   158: athrow         
        //   159: aload_1        
        //   160: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //   163: ifne            485
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   172: athrow         
        //   173: aload_0        
        //   174: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState.NONE:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //   177: putfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAtExternDeclaration:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //   180: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   183: goto            485
        //   186: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   189: athrow         
        //   190: aload_0        
        //   191: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAtExternDeclaration:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //   194: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState.AFTER_PROTOCOL:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //   197: if_acmpne       299
        //   200: aload_1        
        //   201: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   204: if_acmpeq       231
        //   207: goto            214
        //   210: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   213: athrow         
        //   214: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   217: aload_1        
        //   218: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   221: ifeq            236
        //   224: goto            231
        //   227: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   230: athrow         
        //   231: return         
        //   232: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   235: athrow         
        //   236: aload_1        
        //   237: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   240: if_acmpeq       257
        //   243: aload_1        
        //   244: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   247: if_acmpne       289
        //   250: goto            257
        //   253: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   256: athrow         
        //   257: aload_0        
        //   258: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myBraceStack:Ljava/util/Stack;
        //   261: invokevirtual   java/util/Stack.empty:()Z
        //   264: ifne            289
        //   267: goto            274
        //   270: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   273: athrow         
        //   274: aload_0        
        //   275: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myBraceStack:Ljava/util/Stack;
        //   278: invokevirtual   java/util/Stack.pop:()Ljava/lang/Object;
        //   281: pop            
        //   282: goto            289
        //   285: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   288: athrow         
        //   289: aload_0        
        //   290: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState.NONE:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //   293: putfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAtExternDeclaration:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //   296: goto            485
        //   299: aload_1        
        //   300: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   303: if_acmpne       346
        //   306: aload_0        
        //   307: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myBraceStack:Ljava/util/Stack;
        //   310: aload_0        
        //   311: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAtExternDeclaration:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //   314: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState.AFTER_EXTERN_C:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //   317: if_acmpne       335
        //   320: goto            327
        //   323: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   326: athrow         
        //   327: iconst_1       
        //   328: goto            336
        //   331: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   334: athrow         
        //   335: iconst_0       
        //   336: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   339: invokevirtual   java/util/Stack.push:(Ljava/lang/Object;)Ljava/lang/Object;
        //   342: pop            
        //   343: goto            461
        //   346: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OBJC_CLASS_KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //   349: aload_1        
        //   350: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   353: ifeq            408
        //   356: aload_1        
        //   357: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CLASS_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   360: if_acmpeq       408
        //   363: goto            370
        //   366: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   369: athrow         
        //   370: aload_0        
        //   371: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myBraceStack:Ljava/util/Stack;
        //   374: iconst_0       
        //   375: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   378: invokevirtual   java/util/Stack.push:(Ljava/lang/Object;)Ljava/lang/Object;
        //   381: pop            
        //   382: aload_1        
        //   383: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PROTOCOL_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   386: if_acmpne       461
        //   389: goto            396
        //   392: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   395: athrow         
        //   396: aload_0        
        //   397: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState.AFTER_PROTOCOL:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //   400: putfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAtExternDeclaration:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //   403: return         
        //   404: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   407: athrow         
        //   408: aload_1        
        //   409: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   412: if_acmpeq       429
        //   415: aload_1        
        //   416: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.END_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   419: if_acmpne       461
        //   422: goto            429
        //   425: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   428: athrow         
        //   429: aload_0        
        //   430: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myBraceStack:Ljava/util/Stack;
        //   433: invokevirtual   java/util/Stack.empty:()Z
        //   436: ifne            461
        //   439: goto            446
        //   442: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   445: athrow         
        //   446: aload_0        
        //   447: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myBraceStack:Ljava/util/Stack;
        //   450: invokevirtual   java/util/Stack.pop:()Ljava/lang/Object;
        //   453: pop            
        //   454: goto            461
        //   457: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   460: athrow         
        //   461: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   464: aload_1        
        //   465: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   468: ifne            485
        //   471: aload_0        
        //   472: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState.NONE:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //   475: putfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAtExternDeclaration:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$CurrentState;
        //   478: goto            485
        //   481: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   484: athrow         
        //   485: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     17     21     Ljava/lang/IllegalArgumentException;
        //  21     38     41     45     Ljava/lang/IllegalArgumentException;
        //  28     55     55     59     Ljava/lang/IllegalArgumentException;
        //  59     76     76     80     Ljava/lang/IllegalArgumentException;
        //  80     97     100    104    Ljava/lang/IllegalArgumentException;
        //  87     114    114    118    Ljava/lang/IllegalArgumentException;
        //  118    135    138    142    Ljava/lang/IllegalArgumentException;
        //  128    152    155    159    Ljava/lang/IllegalArgumentException;
        //  142    166    169    173    Ljava/lang/IllegalArgumentException;
        //  159    186    186    190    Ljava/lang/IllegalArgumentException;
        //  190    207    210    214    Ljava/lang/IllegalArgumentException;
        //  200    224    227    231    Ljava/lang/IllegalArgumentException;
        //  214    232    232    236    Ljava/lang/IllegalArgumentException;
        //  236    250    253    257    Ljava/lang/IllegalArgumentException;
        //  243    267    270    274    Ljava/lang/IllegalArgumentException;
        //  257    282    285    289    Ljava/lang/IllegalArgumentException;
        //  299    320    323    327    Ljava/lang/IllegalArgumentException;
        //  306    331    331    335    Ljava/lang/IllegalArgumentException;
        //  346    363    366    370    Ljava/lang/IllegalArgumentException;
        //  356    389    392    396    Ljava/lang/IllegalArgumentException;
        //  370    404    404    408    Ljava/lang/IllegalArgumentException;
        //  408    422    425    429    Ljava/lang/IllegalArgumentException;
        //  415    439    442    446    Ljava/lang/IllegalArgumentException;
        //  429    454    457    461    Ljava/lang/IllegalArgumentException;
        //  461    478    481    485    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0142:
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
    
    private void a(@Nullable final VirtualFile virtualFile) {
        Object file = null;
        Label_0051: {
            Label_0023: {
                try {
                    if (virtualFile == null) {
                        return;
                    }
                    final VirtualFile virtualFile2 = virtualFile;
                    final boolean b = virtualFile2.isValid();
                    if (!b) {
                        return;
                    }
                    break Label_0023;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final VirtualFile virtualFile2 = virtualFile;
                    final boolean b = virtualFile2.isValid();
                    if (!b) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    if (this.myFile == null) {
                        file = null;
                        break Label_0051;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            file = this.myFile.getManager().findFile(virtualFile);
        }
        final OCFile ocFile = (OCFile)file;
        if (ocFile instanceof OCFile) {
            final OCFile ocFile2 = ocFile;
            ocFile2.markIncludedFrom(this.myFile);
            this.myState.preprocessInclude((PsiFile)ocFile2, true, null, this.myIncludeLevel);
        }
    }
    
    public void setChangeSet(@Nullable final OCContextChangeSet myChangeSet) {
        this.myChangeSet = myChangeSet;
    }
    
    private void a(final Lexer lexer, final int n) {
        ProgressManager.checkCanceled();
        this.advanceLexer(lexer);
        if (e(lexer)) {
            this.a(lexer, LexerUtil.getTokenText(lexer), false, false, n, false, null);
        }
        this.addToken(lexer.getTokenStart(), OCTokenTypes.END_OF_DIRECTIVE_CONTENT);
    }
    
    private void c(final Lexer p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAddTokensFromDirective:Z
        //     4: ifeq            19
        //     7: aload_0        
        //     8: aload_1        
        //     9: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.advanceLexer:(Lcom/intellij/lexer/Lexer;)V
        //    12: goto            24
        //    15: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    18: athrow         
        //    19: aload_0        
        //    20: aload_1        
        //    21: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.f:(Lcom/intellij/lexer/Lexer;)V
        //    24: aload_0        
        //    25: aload_1        
        //    26: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.h:(Lcom/intellij/lexer/Lexer;)V
        //    29: iconst_0       
        //    30: istore_3       
        //    31: aload_1        
        //    32: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    35: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    38: if_acmpne       61
        //    41: aload_0        
        //    42: aload_1        
        //    43: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.f:(Lcom/intellij/lexer/Lexer;)V
        //    46: aload_0        
        //    47: aload_1        
        //    48: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.h:(Lcom/intellij/lexer/Lexer;)V
        //    51: iinc            3, 1
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    60: athrow         
        //    61: iconst_0       
        //    62: istore          4
        //    64: aload_1        
        //    65: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    68: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    71: if_acmpne       87
        //    74: aload_0        
        //    75: aload_1        
        //    76: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.f:(Lcom/intellij/lexer/Lexer;)V
        //    79: aload_0        
        //    80: aload_1        
        //    81: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.h:(Lcom/intellij/lexer/Lexer;)V
        //    84: iconst_1       
        //    85: istore          4
        //    87: aload_1        
        //    88: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    91: astore          5
        //    93: new             Ljava/lang/StringBuilder;
        //    96: dup            
        //    97: invokespecial   java/lang/StringBuilder.<init>:()V
        //   100: astore          6
        //   102: iload           4
        //   104: ifeq            122
        //   107: aload           6
        //   109: ldc             "<"
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: pop            
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   121: athrow         
        //   122: aload           5
        //   124: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   127: if_acmpeq       271
        //   130: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //   133: aload           5
        //   135: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   138: ifne            271
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   147: athrow         
        //   148: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LITERALS:Lcom/intellij/psi/tree/TokenSet;
        //   151: aload           5
        //   153: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   156: ifne            271
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   165: athrow         
        //   166: aload           5
        //   168: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   171: if_acmpeq       271
        //   174: goto            181
        //   177: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   180: athrow         
        //   181: aload           5
        //   183: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DIV:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   186: if_acmpeq       271
        //   189: goto            196
        //   192: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   195: athrow         
        //   196: aload           5
        //   198: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EOL_ESCAPE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   201: if_acmpeq       271
        //   204: goto            211
        //   207: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   210: athrow         
        //   211: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITESPACES:Lcom/intellij/psi/tree/TokenSet;
        //   214: aload           5
        //   216: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   219: ifne            271
        //   222: goto            229
        //   225: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   228: athrow         
        //   229: aload           5
        //   231: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   234: if_acmpeq       271
        //   237: goto            244
        //   240: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   243: athrow         
        //   244: aload           5
        //   246: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   249: if_acmpne       362
        //   252: goto            259
        //   255: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   258: athrow         
        //   259: iload_3        
        //   260: iconst_1       
        //   261: if_icmple       362
        //   264: goto            271
        //   267: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   270: athrow         
        //   271: aload           5
        //   273: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   276: if_acmpne       296
        //   279: goto            286
        //   282: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   285: athrow         
        //   286: iinc            3, 1
        //   289: goto            314
        //   292: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   295: athrow         
        //   296: aload           5
        //   298: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   301: if_acmpne       314
        //   304: iinc            3, -1
        //   307: goto            314
        //   310: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   313: athrow         
        //   314: aload           6
        //   316: aload_1        
        //   317: invokestatic    com/intellij/lexer/LexerUtil.getTokenText:(Lcom/intellij/lexer/Lexer;)Ljava/lang/CharSequence;
        //   320: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //   325: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   328: pop            
        //   329: aload_0        
        //   330: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAddTokensFromDirective:Z
        //   333: ifeq            348
        //   336: aload_0        
        //   337: aload_1        
        //   338: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.advanceLexer:(Lcom/intellij/lexer/Lexer;)V
        //   341: goto            353
        //   344: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   347: athrow         
        //   348: aload_0        
        //   349: aload_1        
        //   350: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.f:(Lcom/intellij/lexer/Lexer;)V
        //   353: aload_1        
        //   354: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   357: astore          5
        //   359: goto            122
        //   362: iload           4
        //   364: ifeq            382
        //   367: aload           6
        //   369: ldc             ">"
        //   371: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   374: pop            
        //   375: goto            382
        //   378: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   381: athrow         
        //   382: aload_0        
        //   383: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAddTokensFromDirective:Z
        //   386: ifne            516
        //   389: aload_0        
        //   390: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludeAnchor:Lcom/intellij/psi/PsiFile;
        //   393: ifnull          509
        //   396: goto            403
        //   399: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   402: athrow         
        //   403: aload_0        
        //   404: aload           6
        //   406: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$PreprocessingMode.DIRECTIVE_CONTENTS:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$PreprocessingMode;
        //   409: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.preprocess:(Ljava/lang/CharSequence;Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$PreprocessingMode;)Ljava/lang/String;
        //   412: astore          7
        //   414: aload           7
        //   416: iconst_1       
        //   417: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.extractPath:(Ljava/lang/CharSequence;Z)Lcom/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol$IncludePath;
        //   420: astore          8
        //   422: iload_2        
        //   423: ifeq            448
        //   426: aload_0        
        //   427: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   430: aload           8
        //   432: aload_0        
        //   433: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludeAnchor:Lcom/intellij/psi/PsiFile;
        //   436: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.resolveNextPath:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol$IncludePath;Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   441: goto            463
        //   444: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   447: athrow         
        //   448: aload_0        
        //   449: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   452: aload           8
        //   454: aload_0        
        //   455: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludeAnchor:Lcom/intellij/psi/PsiFile;
        //   458: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.resolvePath:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol$IncludePath;Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   463: astore          9
        //   465: aload           9
        //   467: ifnull          499
        //   470: aload           9
        //   472: invokevirtual   com/intellij/openapi/vfs/VirtualFile.exists:()Z
        //   475: ifeq            499
        //   478: goto            485
        //   481: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   484: athrow         
        //   485: aload_0        
        //   486: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FAKE_TRUE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   489: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.addToken:(Lcom/intellij/psi/tree/IElementType;)V
        //   492: goto            506
        //   495: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   498: athrow         
        //   499: aload_0        
        //   500: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FAKE_FALSE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   503: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.addToken:(Lcom/intellij/psi/tree/IElementType;)V
        //   506: goto            516
        //   509: aload_0        
        //   510: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FAKE_FALSE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   513: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.addToken:(Lcom/intellij/psi/tree/IElementType;)V
        //   516: aload_0        
        //   517: aload_1        
        //   518: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.h:(Lcom/intellij/lexer/Lexer;)V
        //   521: iload           4
        //   523: ifeq            555
        //   526: aload_1        
        //   527: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   530: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   533: if_acmpne       555
        //   536: goto            543
        //   539: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   542: athrow         
        //   543: aload_0        
        //   544: aload_1        
        //   545: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.f:(Lcom/intellij/lexer/Lexer;)V
        //   548: goto            555
        //   551: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   554: athrow         
        //   555: aload_0        
        //   556: aload_1        
        //   557: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.h:(Lcom/intellij/lexer/Lexer;)V
        //   560: iload_3        
        //   561: ifle            593
        //   564: aload_1        
        //   565: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   568: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   571: if_acmpne       593
        //   574: goto            581
        //   577: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   580: athrow         
        //   581: aload_0        
        //   582: aload_1        
        //   583: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.f:(Lcom/intellij/lexer/Lexer;)V
        //   586: goto            593
        //   589: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   592: athrow         
        //   593: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      15     15     19     Ljava/lang/IllegalArgumentException;
        //  31     54     57     61     Ljava/lang/IllegalArgumentException;
        //  102    115    118    122    Ljava/lang/IllegalArgumentException;
        //  122    141    144    148    Ljava/lang/IllegalArgumentException;
        //  130    159    162    166    Ljava/lang/IllegalArgumentException;
        //  148    174    177    181    Ljava/lang/IllegalArgumentException;
        //  166    189    192    196    Ljava/lang/IllegalArgumentException;
        //  181    204    207    211    Ljava/lang/IllegalArgumentException;
        //  196    222    225    229    Ljava/lang/IllegalArgumentException;
        //  211    237    240    244    Ljava/lang/IllegalArgumentException;
        //  229    252    255    259    Ljava/lang/IllegalArgumentException;
        //  244    264    267    271    Ljava/lang/IllegalArgumentException;
        //  259    279    282    286    Ljava/lang/IllegalArgumentException;
        //  271    292    292    296    Ljava/lang/IllegalArgumentException;
        //  296    307    310    314    Ljava/lang/IllegalArgumentException;
        //  314    344    344    348    Ljava/lang/IllegalArgumentException;
        //  362    375    378    382    Ljava/lang/IllegalArgumentException;
        //  382    396    399    403    Ljava/lang/IllegalArgumentException;
        //  422    444    444    448    Ljava/lang/IllegalArgumentException;
        //  465    478    481    485    Ljava/lang/IllegalArgumentException;
        //  470    495    495    499    Ljava/lang/IllegalArgumentException;
        //  516    536    539    543    Ljava/lang/IllegalArgumentException;
        //  526    548    551    555    Ljava/lang/IllegalArgumentException;
        //  555    574    577    581    Ljava/lang/IllegalArgumentException;
        //  564    586    589    593    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0148:
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
    
    private void g(final Lexer lexer) {
        Label_0024: {
            try {
                if (this.myAddTokensFromDirective) {
                    this.advanceLexer(lexer);
                    break Label_0024;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.f(lexer);
        }
        this.h(lexer);
        boolean b = false;
        if (lexer.getTokenType() == OCTokenTypes.LPAR) {
            this.advanceLexer(lexer);
            this.h(lexer);
            b = true;
        }
        final IElementType tokenType = lexer.getTokenType();
        Label_0178: {
            Label_0152: {
                try {
                    if (tokenType != OCTokenTypes.IDENTIFIER) {
                        if (!OCTokenTypes.KEYWORDS.contains(tokenType)) {
                            break Label_0152;
                        }
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final String string = LexerUtil.getTokenText(lexer).toString();
                try {
                    if (this.myAddTokensFromDirective) {
                        this.advanceLexer(lexer);
                        break Label_0152;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                int tokenStart = 0;
                OCElementType ocElementType = null;
                Label_0149: {
                    try {
                        this.f(lexer);
                        tokenStart = lexer.getTokenStart();
                        if (this.myState.isDefined(string)) {
                            ocElementType = OCTokenTypes.FAKE_TRUE;
                            break Label_0149;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    ocElementType = OCTokenTypes.FAKE_FALSE;
                }
                this.addToken(tokenStart, (IElementType)ocElementType);
                try {
                    this.h(lexer);
                    if (!b) {
                        return;
                    }
                    final Lexer lexer2 = lexer;
                    final IElementType elementType = lexer2.getTokenType();
                    final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.RPAR;
                    if (elementType == ocPunctuatorElementType) {
                        break Label_0178;
                    }
                    return;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            try {
                final Lexer lexer2 = lexer;
                final IElementType elementType = lexer2.getTokenType();
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.RPAR;
                if (elementType == ocPunctuatorElementType) {
                    this.advanceLexer(lexer);
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
    }
    
    private void h(final Lexer lexer) {
        try {
            while (OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(lexer.getTokenType())) {
                this.f(lexer);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    private void a(final Lexer lexer, final Function<CharSequence, Boolean> function) {
        this.advanceLexer(lexer);
        if (e(lexer)) {
            final CharSequence tokenText = LexerUtil.getTokenText(lexer);
            final boolean booleanValue = (boolean)function.fun((Object)tokenText);
            boolean b = false;
            Label_0064: {
                Label_0055: {
                    try {
                        if (function == this.IFDEF_EVAL) {
                            break Label_0055;
                        }
                        final Function<CharSequence, Boolean> function2 = function;
                        final OCPreprocessingLexer ocPreprocessingLexer = this;
                        final Function<CharSequence, Boolean> function3 = ocPreprocessingLexer.IFNDEF_EVAL;
                        if (function2 == function3) {
                            break Label_0055;
                        }
                        break Label_0055;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final Function<CharSequence, Boolean> function2 = function;
                        final OCPreprocessingLexer ocPreprocessingLexer = this;
                        final Function<CharSequence, Boolean> function3 = ocPreprocessingLexer.IFNDEF_EVAL;
                        if (function2 == function3) {
                            b = true;
                            break Label_0064;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                b = false;
            }
            this.a(lexer, tokenText, b, this.myInsideIfCondition = true, 8, true, null);
            this.myInsideIfCondition = false;
            Label_0209: {
                if (booleanValue) {
                    this.j(lexer);
                    final IElementType tokenType = lexer.getTokenType();
                    Label_0129: {
                        try {
                            if (tokenType == OCTokenTypes.ELSE_DIRECTIVE) {
                                break Label_0129;
                            }
                            final OCElementType ocElementType = (OCElementType)tokenType;
                            final OCElementType ocElementType2 = OCTokenTypes.ELIF_DIRECTIVE;
                            if (ocElementType == ocElementType2) {
                                break Label_0129;
                            }
                            break Label_0129;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final OCElementType ocElementType = (OCElementType)tokenType;
                            final OCElementType ocElementType2 = OCTokenTypes.ELIF_DIRECTIVE;
                            if (ocElementType == ocElementType2) {
                                this.c(lexer);
                                this.b(lexer, false);
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                }
                else {
                    this.b(lexer, true);
                    final IElementType tokenType2 = lexer.getTokenType();
                    try {
                        if (tokenType2 == OCTokenTypes.ELSE_DIRECTIVE) {
                            this.c(lexer);
                            this.j(lexer);
                            break Label_0209;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    try {
                        if (tokenType2 == OCTokenTypes.ELIF_DIRECTIVE) {
                            this.a(lexer, this.IF_EVAL);
                            return;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
                try {
                    if (lexer.getTokenType() == OCTokenTypes.ENDIF_DIRECTIVE) {
                        this.a(lexer, 8);
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
        }
        else {
            this.addToken(lexer.getTokenStart(), OCTokenTypes.END_OF_DIRECTIVE_CONTENT);
        }
    }
    
    private static boolean e(final Lexer p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //     4: astore_1       
        //     5: aload_1        
        //     6: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DIRECTIVE_CONTENT:Lcom/intellij/psi/tree/IElementType;
        //     9: if_acmpeq       40
        //    12: aload_1        
        //    13: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PRAGMA_DIRECTIVE_CONTENT:Lcom/intellij/psi/tree/IElementType;
        //    16: if_acmpeq       40
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    25: athrow         
        //    26: aload_1        
        //    27: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.INCLUDE_DIRECTIVE_CONTENT:Lcom/intellij/psi/tree/IElementType;
        //    30: if_acmpne       48
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    39: athrow         
        //    40: iconst_1       
        //    41: goto            49
        //    44: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    47: athrow         
        //    48: iconst_0       
        //    49: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  5      19     22     26     Ljava/lang/IllegalArgumentException;
        //  12     33     36     40     Ljava/lang/IllegalArgumentException;
        //  26     44     44     48     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
    
    private void c(final Lexer lexer) {
        final IElementType tokenType = lexer.getTokenType();
        this.advanceLexer(lexer);
        if (e(lexer)) {
            final CharSequence tokenText = LexerUtil.getTokenText(lexer);
            try {
                if (tokenType == OCTokenTypes.ELIF_DIRECTIVE) {
                    this.a(lexer, tokenText, false, this.myInsideIfCondition = true, 8, true, null);
                    this.myInsideIfCondition = false;
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.a(tokenText);
            this.advanceLexer(lexer);
        }
        else {
            this.addToken(lexer.getTokenStart(), OCTokenTypes.END_OF_DIRECTIVE_CONTENT);
        }
    }
    
    private void j(final Lexer lexer) {
        while (true) {
            final IElementType tokenType = lexer.getTokenType();
            Label_0026: {
                try {
                    if (tokenType == null) {
                        break;
                    }
                    final TokenSet set = OCTokenTypes.END_IF_DIRECTIVES;
                    final IElementType elementType = tokenType;
                    final boolean b = set.contains(elementType);
                    if (b) {
                        break Label_0026;
                    }
                    break Label_0026;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final TokenSet set = OCTokenTypes.END_IF_DIRECTIVES;
                    final IElementType elementType = tokenType;
                    final boolean b = set.contains(elementType);
                    if (b) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            this.lookAhead(lexer);
        }
    }
    
    private void b(final Lexer p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: istore_3       
        //     2: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITESPACES:Lcom/intellij/psi/tree/TokenSet;
        //     5: aload_1        
        //     6: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //     9: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    12: ifeq            38
        //    15: aload_0        
        //    16: aload_1        
        //    17: invokestatic    com/intellij/lexer/LexerUtil.getTokenText:(Lcom/intellij/lexer/Lexer;)Ljava/lang/CharSequence;
        //    20: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/CharSequence;)V
        //    23: aload_0        
        //    24: aload_1        
        //    25: getstatic       com/intellij/psi/TokenType.WHITE_SPACE:Lcom/intellij/psi/tree/IElementType;
        //    28: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.advanceAs:(Lcom/intellij/lexer/Lexer;Lcom/intellij/psi/tree/IElementType;)V
        //    31: goto            2
        //    34: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    37: athrow         
        //    38: aconst_null    
        //    39: astore          4
        //    41: aload_1        
        //    42: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    45: astore          5
        //    47: aload           5
        //    49: ifnonnull       59
        //    52: goto            234
        //    55: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    58: athrow         
        //    59: aload           5
        //    61: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IF_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    64: if_acmpeq       97
        //    67: aload           5
        //    69: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IFDEF_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    72: if_acmpeq       97
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    81: athrow         
        //    82: aload           5
        //    84: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IFNDEF_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    87: if_acmpne       107
        //    90: goto            97
        //    93: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    96: athrow         
        //    97: iinc            3, 1
        //   100: goto            195
        //   103: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   106: athrow         
        //   107: iload_2        
        //   108: ifeq            141
        //   111: aload           5
        //   113: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ELIF_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   116: if_acmpeq       156
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   125: athrow         
        //   126: aload           5
        //   128: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ELSE_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   131: if_acmpeq       156
        //   134: goto            141
        //   137: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   140: athrow         
        //   141: aload           5
        //   143: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ENDIF_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   146: if_acmpne       195
        //   149: goto            156
        //   152: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   155: athrow         
        //   156: iinc            3, -1
        //   159: iload_3        
        //   160: ifne            177
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   169: athrow         
        //   170: goto            234
        //   173: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   176: athrow         
        //   177: aload           5
        //   179: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ENDIF_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   182: if_acmpeq       195
        //   185: iinc            3, 1
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   194: athrow         
        //   195: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITESPACES:Lcom/intellij/psi/tree/TokenSet;
        //   198: aload           5
        //   200: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   203: ifeq            221
        //   206: aload_0        
        //   207: aload_1        
        //   208: invokestatic    com/intellij/lexer/LexerUtil.getTokenText:(Lcom/intellij/lexer/Lexer;)Ljava/lang/CharSequence;
        //   211: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/CharSequence;)V
        //   214: goto            227
        //   217: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   220: athrow         
        //   221: aload_1        
        //   222: invokevirtual   com/intellij/lexer/Lexer.getCurrentPosition:()Lcom/intellij/lexer/LexerPosition;
        //   225: astore          4
        //   227: aload_1        
        //   228: invokevirtual   com/intellij/lexer/Lexer.advance:()V
        //   231: goto            41
        //   234: aload           4
        //   236: ifnull          260
        //   239: aload_1        
        //   240: aload           4
        //   242: invokevirtual   com/intellij/lexer/Lexer.restore:(Lcom/intellij/lexer/LexerPosition;)V
        //   245: aload_0        
        //   246: aload_1        
        //   247: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   250: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.advanceAs:(Lcom/intellij/lexer/Lexer;Lcom/intellij/psi/tree/IElementType;)V
        //   253: goto            260
        //   256: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   259: athrow         
        //   260: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITESPACES:Lcom/intellij/psi/tree/TokenSet;
        //   263: aload_1        
        //   264: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   267: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   270: ifeq            288
        //   273: aload_0        
        //   274: aload_1        
        //   275: getstatic       com/intellij/psi/TokenType.WHITE_SPACE:Lcom/intellij/psi/tree/IElementType;
        //   278: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.advanceAs:(Lcom/intellij/lexer/Lexer;Lcom/intellij/psi/tree/IElementType;)V
        //   281: goto            260
        //   284: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   287: athrow         
        //   288: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  2      34     34     38     Ljava/lang/IllegalArgumentException;
        //  47     55     55     59     Ljava/lang/IllegalArgumentException;
        //  59     75     78     82     Ljava/lang/IllegalArgumentException;
        //  67     90     93     97     Ljava/lang/IllegalArgumentException;
        //  82     103    103    107    Ljava/lang/IllegalArgumentException;
        //  107    119    122    126    Ljava/lang/IllegalArgumentException;
        //  111    134    137    141    Ljava/lang/IllegalArgumentException;
        //  126    149    152    156    Ljava/lang/IllegalArgumentException;
        //  141    163    166    170    Ljava/lang/IllegalArgumentException;
        //  156    173    173    177    Ljava/lang/IllegalArgumentException;
        //  177    188    191    195    Ljava/lang/IllegalArgumentException;
        //  195    217    217    221    Ljava/lang/IllegalArgumentException;
        //  234    253    256    260    Ljava/lang/IllegalArgumentException;
        //  260    284    284    288    Ljava/lang/IllegalArgumentException;
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
    
    private boolean c(final CharSequence p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer;
        //     3: dup            
        //     4: aload_0        
        //     5: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //     8: aload_0        
        //     9: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myFile:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    12: iconst_1       
        //    13: iconst_0       
        //    14: iconst_0       
        //    15: aload_0        
        //    16: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myTotalMacroSubstitutionsCnt:Lcom/intellij/openapi/util/Ref;
        //    19: aload_0        
        //    20: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludePreprocessingMode:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$IncludePreprocessingMode;
        //    23: aload_0        
        //    24: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:()Lcom/intellij/openapi/util/Ref;
        //    27: iconst_0       
        //    28: iconst_0       
        //    29: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.<init>:(Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;Lcom/jetbrains/cidr/lang/psi/OCFile;ZIILcom/intellij/openapi/util/Ref;Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$IncludePreprocessingMode;Lcom/intellij/openapi/util/Ref;ZZ)V
        //    32: astore_2       
        //    33: aload_2        
        //    34: aload_0        
        //    35: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludeAnchor:Lcom/intellij/psi/PsiFile;
        //    38: putfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludeAnchor:Lcom/intellij/psi/PsiFile;
        //    41: new             Ljava/lang/StringBuilder;
        //    44: dup            
        //    45: invokespecial   java/lang/StringBuilder.<init>:()V
        //    48: astore_3       
        //    49: aload_2        
        //    50: aload_1        
        //    51: iconst_0       
        //    52: aload_1        
        //    53: invokeinterface java/lang/CharSequence.length:()I
        //    58: bipush          8
        //    60: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.start:(Ljava/lang/CharSequence;III)V
        //    63: aload_2        
        //    64: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    67: astore          4
        //    69: aload           4
        //    71: ifnonnull       81
        //    74: goto            368
        //    77: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    80: athrow         
        //    81: aload           4
        //    83: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FAKE_TRUE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    86: if_acmpeq       104
        //    89: aload           4
        //    91: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TRUE_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //    94: if_acmpne       118
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   103: athrow         
        //   104: aload_3        
        //   105: ldc             "1"
        //   107: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   110: pop            
        //   111: goto            361
        //   114: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   117: athrow         
        //   118: aload           4
        //   120: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FAKE_FALSE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   123: if_acmpne       140
        //   126: aload_3        
        //   127: ldc             "0"
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   132: pop            
        //   133: goto            361
        //   136: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   139: athrow         
        //   140: aload           4
        //   142: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BLOCK_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   145: if_acmpne       155
        //   148: goto            361
        //   151: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   154: athrow         
        //   155: aload           4
        //   157: instanceof      Lcom/intellij/lang/TokenWrapper;
        //   160: ifeq            312
        //   163: aload           4
        //   165: instanceof      Lcom/intellij/lang/ForeignLeafType;
        //   168: ifeq            361
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   177: athrow         
        //   178: aload           4
        //   180: checkcast       Lcom/intellij/lang/ForeignLeafType;
        //   183: astore          5
        //   185: aload           5
        //   187: invokevirtual   com/intellij/lang/ForeignLeafType.getDelegate:()Lcom/intellij/psi/tree/IElementType;
        //   190: astore          6
        //   192: aload           6
        //   194: instanceof      Lcom/intellij/lang/TokenWrapper;
        //   197: ifne            309
        //   200: aload           6
        //   202: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FAKE_TRUE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   205: if_acmpeq       230
        //   208: goto            215
        //   211: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   214: athrow         
        //   215: aload           6
        //   217: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TRUE_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   220: if_acmpne       244
        //   223: goto            230
        //   226: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   229: athrow         
        //   230: aload_3        
        //   231: ldc             "1"
        //   233: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   236: pop            
        //   237: goto            309
        //   240: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   243: athrow         
        //   244: aload           6
        //   246: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FAKE_FALSE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   249: if_acmpeq       285
        //   252: aload           6
        //   254: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   257: if_acmpeq       285
        //   260: goto            267
        //   263: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   266: athrow         
        //   267: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //   270: aload           6
        //   272: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   275: ifeq            299
        //   278: goto            285
        //   281: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   284: athrow         
        //   285: aload_3        
        //   286: ldc             "0"
        //   288: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   291: pop            
        //   292: goto            309
        //   295: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   298: athrow         
        //   299: aload_3        
        //   300: aload           5
        //   302: invokevirtual   com/intellij/lang/ForeignLeafType.getValue:()Ljava/lang/String;
        //   305: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   308: pop            
        //   309: goto            361
        //   312: aload           4
        //   314: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   317: if_acmpeq       338
        //   320: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //   323: aload           4
        //   325: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   328: ifeq            352
        //   331: goto            338
        //   334: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   337: athrow         
        //   338: aload_3        
        //   339: ldc             "0"
        //   341: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   344: pop            
        //   345: goto            361
        //   348: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   351: athrow         
        //   352: aload_3        
        //   353: aload_2        
        //   354: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.getTokenText:()Ljava/lang/String;
        //   357: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   360: pop            
        //   361: aload_2        
        //   362: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.advance:()V
        //   365: goto            63
        //   368: aload_3        
        //   369: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   372: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   375: astore          5
        //   377: aload           5
        //   379: invokevirtual   java/lang/String.isEmpty:()Z
        //   382: ifeq            387
        //   385: iconst_0       
        //   386: ireturn        
        //   387: aload_0        
        //   388: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   391: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   396: astore          6
        //   398: aload           5
        //   400: aload           6
        //   402: aconst_null    
        //   403: iconst_0       
        //   404: iconst_0       
        //   405: getstatic       com/jetbrains/cidr/lang/OCLanguageKind.C:Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   408: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionCodeFragment:(Ljava/lang/String;Lcom/intellij/openapi/project/Project;Lcom/intellij/psi/PsiElement;ZZLcom/jetbrains/cidr/lang/OCLanguageKind;)Lcom/jetbrains/cidr/lang/psi/OCCodeFragment;
        //   411: ldc             Lcom/jetbrains/cidr/lang/psi/OCExpression;.class
        //   413: invokestatic    com/intellij/psi/util/PsiTreeUtil.getChildOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   416: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   419: astore          4
        //   421: aload           4
        //   423: ifnonnull       428
        //   426: iconst_0       
        //   427: ireturn        
        //   428: goto            435
        //   431: astore          5
        //   433: iconst_0       
        //   434: ireturn        
        //   435: aload           4
        //   437: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluate:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/lang/Number;
        //   440: astore          5
        //   442: aload           5
        //   444: ifnull          470
        //   447: aload           5
        //   449: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //   452: ifeq            470
        //   455: goto            462
        //   458: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   461: athrow         
        //   462: iconst_1       
        //   463: goto            471
        //   466: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   469: athrow         
        //   470: iconst_0       
        //   471: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  320    348    348    352    Ljava/lang/Throwable;
        //  312    331    334    338    Ljava/lang/Throwable;
        //  267    295    295    299    Ljava/lang/Throwable;
        //  252    278    281    285    Ljava/lang/Throwable;
        //  244    260    263    267    Ljava/lang/Throwable;
        //  215    240    240    244    Ljava/lang/Throwable;
        //  200    223    226    230    Ljava/lang/Throwable;
        //  192    208    211    215    Ljava/lang/Throwable;
        //  155    171    174    178    Ljava/lang/Throwable;
        //  140    151    151    155    Ljava/lang/Throwable;
        //  118    136    136    140    Ljava/lang/Throwable;
        //  89     114    114    118    Ljava/lang/Throwable;
        //  81     97     100    104    Ljava/lang/Throwable;
        //  69     77     77     81     Ljava/lang/Throwable;
        //  368    386    431    435    Ljava/lang/Throwable;
        //  387    427    431    435    Ljava/lang/Throwable;
        //  442    455    458    462    Ljava/lang/Throwable;
        //  447    466    466    470    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0215:
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
    private Ref<Long> a() {
        Ref ref;
        try {
            ref = new Ref(this.myLineCount.get());
            if (ref == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer", "getLineCountCopy"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (Ref<Long>)ref;
    }
    
    private void b(final Lexer p0, final boolean p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //     3: aload_0        
        //     4: aload_1        
        //     5: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.advanceLexer:(Lcom/intellij/lexer/Lexer;)V
        //     8: aload_1        
        //     9: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.e:(Lcom/intellij/lexer/Lexer;)Z
        //    12: ifeq            826
        //    15: aload_1        
        //    16: invokestatic    com/intellij/lexer/LexerUtil.getTokenText:(Lcom/intellij/lexer/Lexer;)Ljava/lang/CharSequence;
        //    19: astore          4
        //    21: aload_0        
        //    22: aload_1        
        //    23: aload           4
        //    25: iconst_0       
        //    26: iconst_0       
        //    27: bipush          10
        //    29: iconst_0       
        //    30: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.HEADER_PATH_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    33: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/lexer/Lexer;Ljava/lang/CharSequence;ZZIZLcom/intellij/psi/tree/IElementType;)Z
        //    36: istore          5
        //    38: aload_0        
        //    39: aload           4
        //    41: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //    46: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$PreprocessingMode.DIRECTIVE_CONTENTS:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$PreprocessingMode;
        //    49: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.preprocess:(Ljava/lang/CharSequence;Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$PreprocessingMode;)Ljava/lang/String;
        //    52: astore          6
        //    54: aload           6
        //    56: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.extractFirstPathLiteralText:(Ljava/lang/CharSequence;)Ljava/lang/String;
        //    59: astore          7
        //    61: iload           5
        //    63: ifne            109
        //    66: aload           7
        //    68: ifnull          109
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    77: athrow         
        //    78: aload_0        
        //    79: aload_1        
        //    80: invokevirtual   com/intellij/lexer/Lexer.getTokenStart:()I
        //    83: new             Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //    86: dup            
        //    87: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.HEADER_PATH_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    90: aload           7
        //    92: aconst_null    
        //    93: iconst_m1      
        //    94: aconst_null    
        //    95: iconst_0       
        //    96: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType.<init>:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;Ljava/lang/String;ILcom/intellij/openapi/util/TextRange;I)V
        //    99: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.addToken:(ILcom/intellij/psi/tree/IElementType;)V
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   108: athrow         
        //   109: aload_0        
        //   110: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludeAnchor:Lcom/intellij/psi/PsiFile;
        //   113: ifnull          826
        //   116: aload           7
        //   118: iconst_1       
        //   119: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.extractPath:(Ljava/lang/CharSequence;Z)Lcom/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol$IncludePath;
        //   122: astore          8
        //   124: iload_3        
        //   125: ifeq            150
        //   128: aload_0        
        //   129: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   132: aload           8
        //   134: aload_0        
        //   135: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludeAnchor:Lcom/intellij/psi/PsiFile;
        //   138: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.resolveNextPath:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol$IncludePath;Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   143: goto            165
        //   146: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   149: athrow         
        //   150: aload_0        
        //   151: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   154: aload           8
        //   156: aload_0        
        //   157: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludeAnchor:Lcom/intellij/psi/PsiFile;
        //   160: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.resolvePath:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol$IncludePath;Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   165: astore          9
        //   167: aload           9
        //   169: ifnull          826
        //   172: aload           9
        //   174: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isValid:()Z
        //   177: ifeq            826
        //   180: goto            187
        //   183: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   186: athrow         
        //   187: aload_0        
        //   188: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myFile:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   191: ifnonnull       209
        //   194: goto            201
        //   197: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   200: athrow         
        //   201: aconst_null    
        //   202: goto            223
        //   205: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   208: athrow         
        //   209: aload_0        
        //   210: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myFile:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   213: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getManager:()Lcom/intellij/psi/PsiManager;
        //   218: aload           9
        //   220: invokevirtual   com/intellij/psi/PsiManager.findFile:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/psi/PsiFile;
        //   223: astore          10
        //   225: aload           10
        //   227: ifnull          757
        //   230: aload_0        
        //   231: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludePreprocessingMode:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$IncludePreprocessingMode;
        //   234: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$IncludePreprocessingMode.FORCE_PREPROCESSING:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$IncludePreprocessingMode;
        //   237: if_acmpeq       282
        //   240: goto            247
        //   243: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   246: athrow         
        //   247: aload           10
        //   249: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/SymbolTableProvider.isSourceFile:(Lcom/intellij/psi/PsiFile;)Z
        //   252: ifeq            282
        //   255: goto            262
        //   258: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   261: athrow         
        //   262: aload_0        
        //   263: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myBraceStack:Ljava/util/Stack;
        //   266: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.ID:Lcom/intellij/openapi/util/Condition;
        //   269: invokestatic    com/intellij/util/containers/ContainerUtil.and:(Ljava/lang/Iterable;Lcom/intellij/openapi/util/Condition;)Z
        //   272: ifne            757
        //   275: goto            282
        //   278: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   281: athrow         
        //   282: aload           10
        //   284: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isCodeInsightAvailable:(Lcom/intellij/psi/PsiFile;)Z
        //   287: ifne            356
        //   290: goto            297
        //   293: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   296: athrow         
        //   297: aload_0        
        //   298: aload_1        
        //   299: invokevirtual   com/intellij/lexer/Lexer.getTokenStart:()I
        //   302: new             Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //   305: dup            
        //   306: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.HEADER_TOO_LONG_INLINED_PATH_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   309: new             Ljava/lang/StringBuilder;
        //   312: dup            
        //   313: invokespecial   java/lang/StringBuilder.<init>:()V
        //   316: aload           7
        //   318: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   321: ldc             ":"
        //   323: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   326: aload           10
        //   328: invokeinterface com/intellij/psi/PsiFile.getTextLength:()I
        //   333: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   336: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   339: aconst_null    
        //   340: iconst_m1      
        //   341: aconst_null    
        //   342: iconst_0       
        //   343: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType.<init>:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;Ljava/lang/String;ILcom/intellij/openapi/util/TextRange;I)V
        //   346: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.addToken:(ILcom/intellij/psi/tree/IElementType;)V
        //   349: goto            826
        //   352: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   355: athrow         
        //   356: aload_0        
        //   357: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludeLevel:I
        //   360: aload_0        
        //   361: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   364: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   369: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.getMaxInclusionLevel:(Lcom/intellij/openapi/project/Project;)I
        //   372: if_icmpge       826
        //   375: aload_0        
        //   376: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   379: aload           9
        //   381: iload_2        
        //   382: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.reserveInclude:(Lcom/intellij/openapi/vfs/VirtualFile;Z)Z
        //   387: ifeq            826
        //   390: goto            397
        //   393: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   396: athrow         
        //   397: aload_0        
        //   398: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myProcessingFiles:Lgnu/trove/TObjectIntHashMap;
        //   401: aload           10
        //   403: invokevirtual   gnu/trove/TObjectIntHashMap.get:(Ljava/lang/Object;)I
        //   406: bipush          10
        //   408: if_icmpge       826
        //   411: goto            418
        //   414: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   417: athrow         
        //   418: aload_0        
        //   419: aload_1        
        //   420: invokevirtual   com/intellij/lexer/Lexer.getTokenStart:()I
        //   423: new             Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //   426: dup            
        //   427: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.HEADER_INLINED_PATH_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   430: aload           7
        //   432: aconst_null    
        //   433: iconst_m1      
        //   434: aconst_null    
        //   435: iconst_0       
        //   436: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType.<init>:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;Ljava/lang/String;ILcom/intellij/openapi/util/TextRange;I)V
        //   439: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.addToken:(ILcom/intellij/psi/tree/IElementType;)V
        //   442: aload_0        
        //   443: aload_1        
        //   444: invokevirtual   com/intellij/lexer/Lexer.getTokenStart:()I
        //   447: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.END_OF_DIRECTIVE_CONTENT:Lcom/intellij/psi/tree/IElementType;
        //   450: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.addToken:(ILcom/intellij/psi/tree/IElementType;)V
        //   453: aload_0        
        //   454: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myProcessingFiles:Lgnu/trove/TObjectIntHashMap;
        //   457: aload           10
        //   459: aload_0        
        //   460: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myProcessingFiles:Lgnu/trove/TObjectIntHashMap;
        //   463: aload           10
        //   465: invokevirtual   gnu/trove/TObjectIntHashMap.get:(Ljava/lang/Object;)I
        //   468: iconst_1       
        //   469: iadd           
        //   470: invokevirtual   gnu/trove/TObjectIntHashMap.put:(Ljava/lang/Object;I)I
        //   473: pop            
        //   474: new             Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer;
        //   477: dup            
        //   478: aload_0        
        //   479: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   482: aload_0        
        //   483: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myFile:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   486: aload_0        
        //   487: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myEvalDefinedFunction:Z
        //   490: aload_0        
        //   491: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myMacroLevel:I
        //   494: aload_0        
        //   495: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludeLevel:I
        //   498: iconst_1       
        //   499: iadd           
        //   500: aload_0        
        //   501: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myTotalMacroSubstitutionsCnt:Lcom/intellij/openapi/util/Ref;
        //   504: aload_0        
        //   505: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludePreprocessingMode:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$IncludePreprocessingMode;
        //   508: lconst_1       
        //   509: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   512: invokestatic    com/intellij/openapi/util/Ref.create:(Ljava/lang/Object;)Lcom/intellij/openapi/util/Ref;
        //   515: iconst_0       
        //   516: iconst_0       
        //   517: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.<init>:(Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;Lcom/jetbrains/cidr/lang/psi/OCFile;ZIILcom/intellij/openapi/util/Ref;Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$IncludePreprocessingMode;Lcom/intellij/openapi/util/Ref;ZZ)V
        //   520: astore          11
        //   522: aload           11
        //   524: aload           9
        //   526: putfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myCurrentFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   529: aload           11
        //   531: aload           10
        //   533: putfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludeAnchor:Lcom/intellij/psi/PsiFile;
        //   536: aload           11
        //   538: aload_0        
        //   539: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myProcessingFiles:Lgnu/trove/TObjectIntHashMap;
        //   542: putfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myProcessingFiles:Lgnu/trove/TObjectIntHashMap;
        //   545: aload           11
        //   547: aload_0        
        //   548: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myBraceStack:Ljava/util/Stack;
        //   551: putfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myBraceStack:Ljava/util/Stack;
        //   554: aload           11
        //   556: aload_0        
        //   557: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myInsideIfCondition:Z
        //   560: putfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myInsideIfCondition:Z
        //   563: aload           11
        //   565: aload           10
        //   567: invokeinterface com/intellij/psi/PsiFile.getText:()Ljava/lang/String;
        //   572: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.start:(Ljava/lang/CharSequence;)V
        //   575: iconst_0       
        //   576: istore          12
        //   578: iconst_0       
        //   579: istore          13
        //   581: aload           11
        //   583: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   586: astore          14
        //   588: aload           14
        //   590: ifnonnull       600
        //   593: goto            756
        //   596: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   599: athrow         
        //   600: aload           14
        //   602: instanceof      Lcom/intellij/lang/ForeignLeafType;
        //   605: ifeq            659
        //   608: aload           14
        //   610: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //   613: ifeq            640
        //   616: goto            623
        //   619: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   622: athrow         
        //   623: aload           14
        //   625: checkcast       Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //   628: iload           12
        //   630: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType.plungeIntoSubstitution:(I)V
        //   633: goto            640
        //   636: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   639: athrow         
        //   640: aload_0        
        //   641: aload_1        
        //   642: invokevirtual   com/intellij/lexer/Lexer.getTokenStart:()I
        //   645: aload           14
        //   647: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.addToken:(ILcom/intellij/psi/tree/IElementType;)V
        //   650: iinc            12, 1
        //   653: iconst_0       
        //   654: istore          13
        //   656: goto            748
        //   659: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   662: aload           14
        //   664: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   667: ifne            711
        //   670: aload           11
        //   672: invokestatic    com/intellij/lexer/LexerUtil.getTokenText:(Lcom/intellij/lexer/Lexer;)Ljava/lang/CharSequence;
        //   675: astore          15
        //   677: aload_0        
        //   678: aload_1        
        //   679: invokevirtual   com/intellij/lexer/Lexer.getTokenStart:()I
        //   682: new             Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //   685: dup            
        //   686: aload           14
        //   688: aload           15
        //   690: aload           7
        //   692: iconst_m1      
        //   693: aconst_null    
        //   694: iload           12
        //   696: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType.<init>:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;Ljava/lang/String;ILcom/intellij/openapi/util/TextRange;I)V
        //   699: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.addToken:(ILcom/intellij/psi/tree/IElementType;)V
        //   702: iinc            12, 1
        //   705: iconst_0       
        //   706: istore          13
        //   708: goto            748
        //   711: iload           13
        //   713: ifne            748
        //   716: aload_0        
        //   717: aload_1        
        //   718: invokevirtual   com/intellij/lexer/Lexer.getTokenStart:()I
        //   721: new             Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //   724: dup            
        //   725: getstatic       com/intellij/psi/TokenType.WHITE_SPACE:Lcom/intellij/psi/tree/IElementType;
        //   728: ldc             " "
        //   730: aload           7
        //   732: iconst_m1      
        //   733: aconst_null    
        //   734: iload           12
        //   736: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType.<init>:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;Ljava/lang/String;ILcom/intellij/openapi/util/TextRange;I)V
        //   739: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.addToken:(ILcom/intellij/psi/tree/IElementType;)V
        //   742: iinc            12, 1
        //   745: iconst_1       
        //   746: istore          13
        //   748: aload           11
        //   750: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.advance:()V
        //   753: goto            581
        //   756: return         
        //   757: aload           10
        //   759: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   762: ifeq            826
        //   765: aload_0        
        //   766: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludePreprocessingMode:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$IncludePreprocessingMode;
        //   769: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$IncludePreprocessingMode.USE_SYMBOL_TABLE:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$IncludePreprocessingMode;
        //   772: if_acmpne       826
        //   775: goto            782
        //   778: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   781: athrow         
        //   782: aload           10
        //   784: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   787: astore          11
        //   789: aload           11
        //   791: aload_0        
        //   792: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myFile:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   795: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.markIncludedFrom:(Lcom/jetbrains/cidr/lang/psi/OCFile;)V
        //   800: aload_0        
        //   801: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   804: aload           11
        //   806: iload_2        
        //   807: aconst_null    
        //   808: aload_0        
        //   809: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludeLevel:I
        //   812: aload_1        
        //   813: invokevirtual   com/intellij/lexer/Lexer.getTokenStart:()I
        //   816: aload_0        
        //   817: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myChangeSet:Lcom/jetbrains/cidr/lang/preprocessor/OCContextChangeSet;
        //   820: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.preprocessInclude:(Lcom/intellij/psi/PsiFile;ZLcom/intellij/openapi/vfs/VirtualFile;IILcom/jetbrains/cidr/lang/preprocessor/OCContextChangeSet;)Z
        //   825: pop            
        //   826: aload_0        
        //   827: aload_1        
        //   828: invokevirtual   com/intellij/lexer/Lexer.getTokenStart:()I
        //   831: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.END_OF_DIRECTIVE_CONTENT:Lcom/intellij/psi/tree/IElementType;
        //   834: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.addToken:(ILcom/intellij/psi/tree/IElementType;)V
        //   837: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  61     71     74     78     Ljava/lang/IllegalArgumentException;
        //  66     102    105    109    Ljava/lang/IllegalArgumentException;
        //  124    146    146    150    Ljava/lang/IllegalArgumentException;
        //  167    180    183    187    Ljava/lang/IllegalArgumentException;
        //  172    194    197    201    Ljava/lang/IllegalArgumentException;
        //  187    205    205    209    Ljava/lang/IllegalArgumentException;
        //  225    240    243    247    Ljava/lang/IllegalArgumentException;
        //  230    255    258    262    Ljava/lang/IllegalArgumentException;
        //  247    275    278    282    Ljava/lang/IllegalArgumentException;
        //  262    290    293    297    Ljava/lang/IllegalArgumentException;
        //  282    352    352    356    Ljava/lang/IllegalArgumentException;
        //  356    390    393    397    Ljava/lang/IllegalArgumentException;
        //  375    411    414    418    Ljava/lang/IllegalArgumentException;
        //  588    596    596    600    Ljava/lang/IllegalArgumentException;
        //  600    616    619    623    Ljava/lang/IllegalArgumentException;
        //  608    633    636    640    Ljava/lang/IllegalArgumentException;
        //  757    775    778    782    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0187:
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
    private List<String> d(final Lexer p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/ArrayList;
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore_2       
        //     8: aload_1        
        //     9: invokevirtual   com/intellij/lexer/Lexer.getCurrentPosition:()Lcom/intellij/lexer/LexerPosition;
        //    12: astore_3       
        //    13: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //    16: aload_1        
        //    17: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    20: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    23: ifeq            37
        //    26: aload_1        
        //    27: invokevirtual   com/intellij/lexer/Lexer.advance:()V
        //    30: goto            13
        //    33: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    36: athrow         
        //    37: iconst_0       
        //    38: istore          4
        //    40: iconst_0       
        //    41: istore          5
        //    43: aload_1        
        //    44: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    47: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    50: if_acmpne       541
        //    53: aload_1        
        //    54: aload_3        
        //    55: invokevirtual   com/intellij/lexer/Lexer.restore:(Lcom/intellij/lexer/LexerPosition;)V
        //    58: aload_0        
        //    59: aload_1        
        //    60: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.i:(Lcom/intellij/lexer/Lexer;)V
        //    63: aload_0        
        //    64: aload_1        
        //    65: iconst_0       
        //    66: iconst_0       
        //    67: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/lexer/Lexer;ZZ)Lcom/jetbrains/cidr/lang/preprocessor/OCMacroReferenceTokenType;
        //    70: pop            
        //    71: new             Ljava/lang/StringBuilder;
        //    74: dup            
        //    75: invokespecial   java/lang/StringBuilder.<init>:()V
        //    78: astore          6
        //    80: iconst_m1      
        //    81: istore          7
        //    83: aconst_null    
        //    84: astore          8
        //    86: iconst_0       
        //    87: istore          9
        //    89: iconst_0       
        //    90: istore          10
        //    92: aload_1        
        //    93: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    96: astore          11
        //    98: aload           11
        //   100: ifnonnull       141
        //   103: aload           8
        //   105: ifnull          519
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   114: athrow         
        //   115: aload_1        
        //   116: aload           8
        //   118: invokevirtual   com/intellij/lexer/Lexer.restore:(Lcom/intellij/lexer/LexerPosition;)V
        //   121: aload_0        
        //   122: iload           9
        //   124: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.resetCacheSize:(I)V
        //   127: aload           6
        //   129: iload           10
        //   131: invokevirtual   java/lang/StringBuilder.setLength:(I)V
        //   134: goto            519
        //   137: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   140: athrow         
        //   141: aload           11
        //   143: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TEMPLATE_START_MARK:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   146: if_acmpne       159
        //   149: iinc            5, 1
        //   152: goto            159
        //   155: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   158: athrow         
        //   159: aload           11
        //   161: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TEMPLATE_STOP_MARK:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   164: if_acmpne       177
        //   167: iinc            5, -1
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   176: athrow         
        //   177: aload           11
        //   179: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   182: if_acmpne       216
        //   185: iload           4
        //   187: ifne            216
        //   190: goto            197
        //   193: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   196: athrow         
        //   197: iload           5
        //   199: ifne            216
        //   202: goto            209
        //   205: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   208: athrow         
        //   209: goto            519
        //   212: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   215: athrow         
        //   216: aload           11
        //   218: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   221: if_acmpne       280
        //   224: iload           4
        //   226: ifne            280
        //   229: goto            236
        //   232: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   235: athrow         
        //   236: iload           5
        //   238: ifne            280
        //   241: goto            248
        //   244: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   247: athrow         
        //   248: aload_2        
        //   249: aload           6
        //   251: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   254: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   259: pop            
        //   260: aload           6
        //   262: iconst_0       
        //   263: invokevirtual   java/lang/StringBuilder.setLength:(I)V
        //   266: iconst_m1      
        //   267: istore          7
        //   269: aload_0        
        //   270: aload_1        
        //   271: iconst_0       
        //   272: iconst_0       
        //   273: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/lexer/Lexer;ZZ)Lcom/jetbrains/cidr/lang/preprocessor/OCMacroReferenceTokenType;
        //   276: pop            
        //   277: goto            516
        //   280: aload           11
        //   282: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   285: if_acmpne       310
        //   288: iload           5
        //   290: ifne            310
        //   293: goto            300
        //   296: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   299: athrow         
        //   300: iinc            4, 1
        //   303: goto            409
        //   306: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   309: athrow         
        //   310: aload           11
        //   312: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   315: if_acmpne       340
        //   318: iload           5
        //   320: ifne            340
        //   323: goto            330
        //   326: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   329: athrow         
        //   330: iinc            4, -1
        //   333: goto            409
        //   336: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   339: athrow         
        //   340: aload           8
        //   342: ifnonnull       409
        //   345: aload           11
        //   347: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   350: if_acmpeq       390
        //   353: goto            360
        //   356: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   359: athrow         
        //   360: aload           11
        //   362: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   365: if_acmpeq       390
        //   368: goto            375
        //   371: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   374: athrow         
        //   375: aload           11
        //   377: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   380: if_acmpne       409
        //   383: goto            390
        //   386: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   389: athrow         
        //   390: aload_0        
        //   391: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.getCacheSize:()I
        //   394: istore          9
        //   396: aload_1        
        //   397: invokevirtual   com/intellij/lexer/Lexer.getCurrentPosition:()Lcom/intellij/lexer/LexerPosition;
        //   400: astore          8
        //   402: aload           6
        //   404: invokevirtual   java/lang/StringBuilder.length:()I
        //   407: istore          10
        //   409: iload           7
        //   411: iconst_m1      
        //   412: if_icmpne       421
        //   415: aload_1        
        //   416: invokevirtual   com/intellij/lexer/Lexer.getTokenStart:()I
        //   419: istore          7
        //   421: aload           11
        //   423: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EOL_ESCAPE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   426: if_acmpeq       508
        //   429: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMENTS:Lcom/intellij/psi/tree/TokenSet;
        //   432: aload           11
        //   434: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   437: ifeq            454
        //   440: goto            447
        //   443: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   446: athrow         
        //   447: ldc             " "
        //   449: astore          12
        //   451: goto            500
        //   454: aload_1        
        //   455: invokestatic    com/intellij/lexer/LexerUtil.getTokenText:(Lcom/intellij/lexer/Lexer;)Ljava/lang/CharSequence;
        //   458: astore          12
        //   460: ldc             "__LINE__"
        //   462: aload           12
        //   464: invokestatic    com/intellij/openapi/util/text/StringUtil.equals:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
        //   467: ifeq            500
        //   470: new             Ljava/lang/StringBuilder;
        //   473: dup            
        //   474: invokespecial   java/lang/StringBuilder.<init>:()V
        //   477: ldc             "__CIDR_LINE_MACRO_STUB_"
        //   479: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   482: aload_0        
        //   483: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myLineCount:Lcom/intellij/openapi/util/Ref;
        //   486: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   489: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   492: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   495: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   498: astore          12
        //   500: aload           6
        //   502: aload           12
        //   504: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;
        //   507: pop            
        //   508: aload_0        
        //   509: aload_1        
        //   510: iconst_1       
        //   511: iconst_0       
        //   512: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/lexer/Lexer;ZZ)Lcom/jetbrains/cidr/lang/preprocessor/OCMacroReferenceTokenType;
        //   515: pop            
        //   516: goto            92
        //   519: aload_2        
        //   520: aload           6
        //   522: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   525: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   530: pop            
        //   531: aload_0        
        //   532: aload_1        
        //   533: iconst_0       
        //   534: iconst_0       
        //   535: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/lexer/Lexer;ZZ)Lcom/jetbrains/cidr/lang/preprocessor/OCMacroReferenceTokenType;
        //   538: pop            
        //   539: aload_2        
        //   540: areturn        
        //   541: aload_1        
        //   542: aload_3        
        //   543: invokevirtual   com/intellij/lexer/Lexer.restore:(Lcom/intellij/lexer/LexerPosition;)V
        //   546: aconst_null    
        //   547: areturn        
        //    Signature:
        //  (Lcom/intellij/lexer/Lexer;)Ljava/util/List<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  13     33     33     37     Ljava/lang/IllegalArgumentException;
        //  98     108    111    115    Ljava/lang/IllegalArgumentException;
        //  103    137    137    141    Ljava/lang/IllegalArgumentException;
        //  141    152    155    159    Ljava/lang/IllegalArgumentException;
        //  159    170    173    177    Ljava/lang/IllegalArgumentException;
        //  177    190    193    197    Ljava/lang/IllegalArgumentException;
        //  185    202    205    209    Ljava/lang/IllegalArgumentException;
        //  197    212    212    216    Ljava/lang/IllegalArgumentException;
        //  216    229    232    236    Ljava/lang/IllegalArgumentException;
        //  224    241    244    248    Ljava/lang/IllegalArgumentException;
        //  280    293    296    300    Ljava/lang/IllegalArgumentException;
        //  288    306    306    310    Ljava/lang/IllegalArgumentException;
        //  310    323    326    330    Ljava/lang/IllegalArgumentException;
        //  318    336    336    340    Ljava/lang/IllegalArgumentException;
        //  340    353    356    360    Ljava/lang/IllegalArgumentException;
        //  345    368    371    375    Ljava/lang/IllegalArgumentException;
        //  360    383    386    390    Ljava/lang/IllegalArgumentException;
        //  421    440    443    447    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0197:
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
    
    private void i(@NotNull final Lexer lexer) {
        try {
            if (lexer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseLexer", "com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer", "skipWhitespacesAndCommentByAdvance"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            while (OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(lexer.getTokenType())) {
                this.a(lexer, false, false);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    @Nullable
    private OCMacroReferenceTokenType a(final Lexer p0, final boolean p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          4
        //     3: aload_1        
        //     4: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //     7: astore          5
        //     9: aload           5
        //    11: ifnull          168
        //    14: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //    17: aload           5
        //    19: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    22: ifeq            68
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    31: athrow         
        //    32: iload_3        
        //    33: ifne            58
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    42: athrow         
        //    43: aload_0        
        //    44: aload_1        
        //    45: invokestatic    com/intellij/lexer/LexerUtil.getTokenText:(Lcom/intellij/lexer/Lexer;)Ljava/lang/CharSequence;
        //    48: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/CharSequence;)V
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    57: athrow         
        //    58: aload_0        
        //    59: aload_1        
        //    60: aload           5
        //    62: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.advanceAs:(Lcom/intellij/lexer/Lexer;Lcom/intellij/psi/tree/IElementType;)V
        //    65: goto            168
        //    68: aload_1        
        //    69: invokestatic    com/intellij/lexer/LexerUtil.getTokenText:(Lcom/intellij/lexer/Lexer;)Ljava/lang/CharSequence;
        //    72: astore          6
        //    74: iload_3        
        //    75: ifeq            138
        //    78: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //    81: aload           5
        //    83: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    86: ifne            128
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    95: athrow         
        //    96: aload           5
        //    98: instanceof      Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   101: ifeq            138
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   110: athrow         
        //   111: aload           5
        //   113: aload           6
        //   115: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isAlternativeCppPunctuator:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;)Z
        //   118: ifeq            138
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   127: athrow         
        //   128: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   131: goto            140
        //   134: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   137: athrow         
        //   138: aload           5
        //   140: astore          7
        //   142: new             Lcom/jetbrains/cidr/lang/preprocessor/OCMacroReferenceTokenType;
        //   145: dup            
        //   146: aload           7
        //   148: aload           6
        //   150: iload_2        
        //   151: aload_0        
        //   152: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myMacroLevel:I
        //   155: iload_3        
        //   156: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCMacroReferenceTokenType.<init>:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;ZIZ)V
        //   159: astore          4
        //   161: aload_0        
        //   162: aload_1        
        //   163: aload           4
        //   165: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.advanceAs:(Lcom/intellij/lexer/Lexer;Lcom/intellij/psi/tree/IElementType;)V
        //   168: aload           4
        //   170: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  9      25     28     32     Ljava/lang/IllegalArgumentException;
        //  14     36     39     43     Ljava/lang/IllegalArgumentException;
        //  32     51     54     58     Ljava/lang/IllegalArgumentException;
        //  74     89     92     96     Ljava/lang/IllegalArgumentException;
        //  78     104    107    111    Ljava/lang/IllegalArgumentException;
        //  96     121    124    128    Ljava/lang/IllegalArgumentException;
        //  111    134    134    138    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0032:
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
    
    private void f(final Lexer lexer) {
        this.advanceAs(lexer, (IElementType)OCTokenTypes.BLOCK_COMMENT);
    }
    
    private void a(final Lexer lexer) {
        this.advanceLexer(lexer);
        if (e(lexer)) {
            final CharSequence tokenText = LexerUtil.getTokenText(lexer);
            this.a(lexer, tokenText, true, false, 8, true, null);
            final OCLexer ocLexer = new OCLexer(OCLanguageKind.CPP, false, false, false, false);
            ocLexer.start(tokenText);
            try {
                while (OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(ocLexer.getTokenType())) {
                    ocLexer.advance();
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (ocLexer.getTokenType() == OCTokenTypes.IDENTIFIER) {
                    this.myState.undef(LexerUtil.getTokenText((Lexer)ocLexer).toString());
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        else {
            this.addToken(lexer.getTokenStart(), OCTokenTypes.END_OF_DIRECTIVE_CONTENT);
        }
    }
    
    private void b(final Lexer lexer) {
        final int tokenStart = lexer.getTokenStart();
        this.advanceLexer(lexer);
        if (e(lexer)) {
            final CharSequence tokenText = LexerUtil.getTokenText(lexer);
            this.a(lexer, tokenText, true, false, 8, true, null);
            final OCMacroSymbol fromDirectiveContent = OCMacroSymbol.parseFromDirectiveContent(tokenText, this.myFile, tokenStart);
            try {
                if (fromDirectiveContent != null) {
                    this.myState.define(fromDirectiveContent);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        else {
            this.addToken(lexer.getTokenStart(), OCTokenTypes.END_OF_DIRECTIVE_CONTENT);
        }
    }
    
    private boolean a(@NotNull final Lexer lexer, @NotNull final CharSequence charSequence, final boolean b, final boolean b2, final int n, final boolean b3, @Nullable final IElementType elementType) {
        try {
            if (lexer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseLexer", "com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer", "addTokensFromDirective"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (charSequence == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "content", "com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer", "addTokensFromDirective"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCPreprocessingLexer ocPreprocessingLexer = new OCPreprocessingLexer(this.myState, this.myFile, b2, 0, 0, this.myTotalMacroSubstitutionsCnt, this.myIncludePreprocessingMode, this.myLineCount, b, true);
        ocPreprocessingLexer.myInsideIfCondition = this.myInsideIfCondition;
        ocPreprocessingLexer.start(charSequence, 0, charSequence.length(), n);
        final int tokenStart = lexer.getTokenStart();
        boolean b4 = false;
        while (ocPreprocessingLexer.getTokenType() != null) {
            final IElementType tokenType = ocPreprocessingLexer.getTokenType();
            boolean b5 = false;
            boolean b6 = false;
            Label_0189: {
                try {
                    b5 = b4;
                    if (elementType == tokenType) {
                        b6 = true;
                        break Label_0189;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                b6 = false;
            }
            b4 = (b5 | b6);
            IElementType elementType2 = null;
            Label_0341: {
                OCElementType identifier = null;
                Label_0339: {
                    Label_0316: {
                        VirtualFile virtualFile2 = null;
                        Label_0232: {
                            Label_0214: {
                                try {
                                    if (tokenType != OCTokenTypes.PRAGMA_ONCE_LITERAL) {
                                        break Label_0316;
                                    }
                                    final OCPreprocessingLexer ocPreprocessingLexer2 = this;
                                    final VirtualFile virtualFile = ocPreprocessingLexer2.myCurrentFile;
                                    if (virtualFile == null) {
                                        break Label_0214;
                                    }
                                    break Label_0214;
                                }
                                catch (IllegalArgumentException ex4) {
                                    throw a(ex4);
                                }
                                try {
                                    final OCPreprocessingLexer ocPreprocessingLexer2 = this;
                                    final VirtualFile virtualFile = ocPreprocessingLexer2.myCurrentFile;
                                    if (virtualFile == null) {
                                        virtualFile2 = OCInclusionContextUtil.getVirtualFile((PsiFile)this.myFile);
                                        break Label_0232;
                                    }
                                }
                                catch (IllegalArgumentException ex5) {
                                    throw a(ex5);
                                }
                            }
                            virtualFile2 = this.myCurrentFile;
                        }
                        VirtualFile virtualFile3 = virtualFile2;
                        Label_0302: {
                            Label_0299: {
                                try {
                                    if (virtualFile3 == null || !virtualFile3.isValid()) {
                                        break Label_0299;
                                    }
                                }
                                catch (IllegalArgumentException ex6) {
                                    throw a(ex6);
                                }
                                final String pragmaOnceId = OCInclusionContextUtil.pragmaOnceId(virtualFile3);
                                try {
                                    if (!this.myState.isDefined(pragmaOnceId)) {
                                        this.myState.define(OCMacroSymbol.inclusionGuard(pragmaOnceId));
                                    }
                                }
                                catch (IllegalArgumentException ex7) {
                                    throw a(ex7);
                                }
                                break Label_0302;
                            }
                            virtualFile3 = null;
                        }
                        elementType2 = new OCPragmaOnceContentElementType(virtualFile3);
                        break Label_0341;
                        try {
                            if (OCTokenTypes.KEYWORDS.contains(tokenType)) {
                                identifier = OCTokenTypes.IDENTIFIER;
                                break Label_0339;
                            }
                        }
                        catch (IllegalArgumentException ex8) {
                            throw a(ex8);
                        }
                    }
                    identifier = (OCElementType)tokenType;
                }
                elementType2 = identifier;
            }
            this.addToken(tokenStart + ocPreprocessingLexer.getTokenEnd(), elementType2);
            ocPreprocessingLexer.advance();
        }
        try {
            if (b3) {
                this.addToken(lexer.getTokenEnd(), OCTokenTypes.END_OF_DIRECTIVE_CONTENT);
            }
        }
        catch (IllegalArgumentException ex9) {
            throw a(ex9);
        }
        lexer.advance();
        return b4;
    }
    
    @NotNull
    public OCInclusionContext getContext() {
        OCInclusionContext myState;
        try {
            myState = this.myState;
            if (myState == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer", "getContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myState;
    }
    
    @Nullable
    public OCFile getFile() {
        return this.myFile;
    }
    
    private boolean a(final Lexer p0, final OCMacroSymbol p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //     3: aload_0        
        //     4: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myTotalMacroSubstitutionsCnt:Lcom/intellij/openapi/util/Ref;
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myTotalMacroSubstitutionsCnt:Lcom/intellij/openapi/util/Ref;
        //    11: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //    14: checkcast       Ljava/lang/Integer;
        //    17: invokevirtual   java/lang/Integer.intValue:()I
        //    20: iconst_1       
        //    21: iadd           
        //    22: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    25: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //    28: aload_2        
        //    29: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.getName:()Ljava/lang/String;
        //    32: astore          4
        //    34: aload_1        
        //    35: invokevirtual   com/intellij/lexer/Lexer.getCurrentPosition:()Lcom/intellij/lexer/LexerPosition;
        //    38: astore          5
        //    40: iload_3        
        //    41: ifeq            55
        //    44: aload_1        
        //    45: invokevirtual   com/intellij/lexer/Lexer.advance:()V
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    54: athrow         
        //    55: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //    58: aload_1        
        //    59: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    62: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    65: ifeq            79
        //    68: aload_1        
        //    69: invokevirtual   com/intellij/lexer/Lexer.advance:()V
        //    72: goto            55
        //    75: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    78: athrow         
        //    79: aload_1        
        //    80: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    83: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    86: if_acmpne       97
        //    89: iconst_1       
        //    90: goto            98
        //    93: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    96: athrow         
        //    97: iconst_0       
        //    98: istore          6
        //   100: aload_1        
        //   101: aload           5
        //   103: invokevirtual   com/intellij/lexer/Lexer.restore:(Lcom/intellij/lexer/LexerPosition;)V
        //   106: aload_2        
        //   107: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.hasParameterList:()Z
        //   110: ifeq            150
        //   113: iload           6
        //   115: ifne            150
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   124: athrow         
        //   125: iload_3        
        //   126: ifeq            148
        //   129: goto            136
        //   132: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   135: athrow         
        //   136: aload_0        
        //   137: aload_1        
        //   138: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.advanceLexer:(Lcom/intellij/lexer/Lexer;)V
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   147: athrow         
        //   148: iconst_0       
        //   149: ireturn        
        //   150: iload_3        
        //   151: ifeq            169
        //   154: aload_0        
        //   155: aload_1        
        //   156: iconst_0       
        //   157: iconst_1       
        //   158: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/lexer/Lexer;ZZ)Lcom/jetbrains/cidr/lang/preprocessor/OCMacroReferenceTokenType;
        //   161: pop            
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   168: athrow         
        //   169: aload_2        
        //   170: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.getParameterNames:()Lcom/jetbrains/cidr/lang/util/OCImmutableList;
        //   173: astore          7
        //   175: aload_2        
        //   176: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.hasParameterList:()Z
        //   179: ifeq            192
        //   182: aload_0        
        //   183: aload_1        
        //   184: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.d:(Lcom/intellij/lexer/Lexer;)Ljava/util/List;
        //   187: astore          8
        //   189: goto            195
        //   192: aconst_null    
        //   193: astore          8
        //   195: aload_0        
        //   196: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myTotalMacroSubstitutionsCnt:Lcom/intellij/openapi/util/Ref;
        //   199: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   202: checkcast       Ljava/lang/Integer;
        //   205: invokevirtual   java/lang/Integer.intValue:()I
        //   208: ldc             200000
        //   210: if_icmple       219
        //   213: iconst_1       
        //   214: ireturn        
        //   215: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   218: athrow         
        //   219: aload_0        
        //   220: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myAddTokensFromDirective:Z
        //   223: ifeq            246
        //   226: aload_0        
        //   227: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myInsideIfCondition:Z
        //   230: ifne            246
        //   233: goto            240
        //   236: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   239: athrow         
        //   240: iconst_1       
        //   241: ireturn        
        //   242: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   245: athrow         
        //   246: aload_0        
        //   247: aload_2        
        //   248: aload           7
        //   250: aload           7
        //   252: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/util/List;)Ljava/util/Map;
        //   255: aload           7
        //   257: aload           8
        //   259: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/util/List;Ljava/util/List;)Ljava/util/Map;
        //   262: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;Ljava/util/List;Ljava/util/Map;Ljava/util/Map;)Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$SubstitutionResult;
        //   265: astore          9
        //   267: aconst_null    
        //   268: astore          10
        //   270: aconst_null    
        //   271: astore          11
        //   273: iconst_0       
        //   274: istore          12
        //   276: aload_0        
        //   277: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   280: aload           4
        //   282: getstatic       com/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext$SignaturePart.NO:Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext$SignaturePart;
        //   285: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.getDefinition:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/preprocessor/OCImmutableInclusionContext$SignaturePart;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   290: astore          13
        //   292: aload           13
        //   294: ifnull          315
        //   297: aload_0        
        //   298: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   301: aload           4
        //   303: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.undef:(Ljava/lang/String;)V
        //   308: goto            315
        //   311: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   314: athrow         
        //   315: new             Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer;
        //   318: dup            
        //   319: aload_0        
        //   320: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   323: aload_0        
        //   324: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myFile:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   327: aload_0        
        //   328: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myEvalDefinedFunction:Z
        //   331: aload_0        
        //   332: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myMacroLevel:I
        //   335: iconst_1       
        //   336: iadd           
        //   337: aload_0        
        //   338: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludeLevel:I
        //   341: aload_0        
        //   342: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myTotalMacroSubstitutionsCnt:Lcom/intellij/openapi/util/Ref;
        //   345: aload_0        
        //   346: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myIncludePreprocessingMode:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$IncludePreprocessingMode;
        //   349: aload_0        
        //   350: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:()Lcom/intellij/openapi/util/Ref;
        //   353: iconst_0       
        //   354: iconst_0       
        //   355: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.<init>:(Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;Lcom/jetbrains/cidr/lang/psi/OCFile;ZIILcom/intellij/openapi/util/Ref;Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$IncludePreprocessingMode;Lcom/intellij/openapi/util/Ref;ZZ)V
        //   358: astore          14
        //   360: aload           14
        //   362: aload_0        
        //   363: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myInsideIfCondition:Z
        //   366: putfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myInsideIfCondition:Z
        //   369: aload           9
        //   371: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$SubstitutionResult.getString:()Ljava/lang/String;
        //   374: astore          15
        //   376: aload           14
        //   378: aload           15
        //   380: iconst_0       
        //   381: aload           15
        //   383: invokevirtual   java/lang/String.length:()I
        //   386: bipush          8
        //   388: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.start:(Ljava/lang/CharSequence;III)V
        //   391: aload           9
        //   393: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$SubstitutionResult.getSubstitutions:()Ljava/util/List;
        //   396: astore          16
        //   398: iconst_0       
        //   399: istore          17
        //   401: iconst_0       
        //   402: istore          18
        //   404: aload           14
        //   406: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   409: astore          19
        //   411: aload           19
        //   413: ifnonnull       423
        //   416: goto            804
        //   419: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   422: athrow         
        //   423: iload           17
        //   425: aload           16
        //   427: invokeinterface java/util/List.size:()I
        //   432: if_icmpge       454
        //   435: aload           16
        //   437: iload           17
        //   439: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   444: checkcast       Lcom/intellij/openapi/util/Pair;
        //   447: goto            455
        //   450: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   453: athrow         
        //   454: aconst_null    
        //   455: astore          20
        //   457: aload           20
        //   459: ifnull          477
        //   462: aload           20
        //   464: invokevirtual   com/intellij/openapi/util/Pair.getSecond:()Ljava/lang/Object;
        //   467: checkcast       Lcom/intellij/openapi/util/TextRange;
        //   470: goto            478
        //   473: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   476: athrow         
        //   477: aconst_null    
        //   478: astore          21
        //   480: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   483: aload           19
        //   485: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   488: ifeq            508
        //   491: aload_0        
        //   492: aload_1        
        //   493: invokevirtual   com/intellij/lexer/Lexer.getTokenStart:()I
        //   496: aload           19
        //   498: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.addToken:(ILcom/intellij/psi/tree/IElementType;)V
        //   501: goto            761
        //   504: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   507: athrow         
        //   508: aload           19
        //   510: instanceof      Lcom/intellij/lang/ForeignLeafType;
        //   513: ifeq            607
        //   516: aload           19
        //   518: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //   521: ifeq            548
        //   524: goto            531
        //   527: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   530: athrow         
        //   531: aload           19
        //   533: checkcast       Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //   536: iload           18
        //   538: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType.plungeIntoSubstitution:(I)V
        //   541: goto            548
        //   544: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   547: athrow         
        //   548: aload_0        
        //   549: aload           19
        //   551: checkcast       Lcom/intellij/lang/ForeignLeafType;
        //   554: invokevirtual   com/intellij/lang/ForeignLeafType.getDelegate:()Lcom/intellij/psi/tree/IElementType;
        //   557: aload           19
        //   559: checkcast       Lcom/intellij/lang/ForeignLeafType;
        //   562: invokevirtual   com/intellij/lang/ForeignLeafType.getValue:()Ljava/lang/String;
        //   565: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/String;)V
        //   568: aload_0        
        //   569: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.getCacheSize:()I
        //   572: istore          12
        //   574: aload           19
        //   576: checkcast       Lcom/intellij/lang/ForeignLeafType;
        //   579: invokevirtual   com/intellij/lang/ForeignLeafType.getValue:()Ljava/lang/String;
        //   582: astore          11
        //   584: aload           19
        //   586: checkcast       Lcom/intellij/lang/ForeignLeafType;
        //   589: astore          10
        //   591: aload_0        
        //   592: aload_1        
        //   593: invokevirtual   com/intellij/lexer/Lexer.getTokenStart:()I
        //   596: aload           19
        //   598: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.addToken:(ILcom/intellij/psi/tree/IElementType;)V
        //   601: iinc            18, 1
        //   604: goto            761
        //   607: aload           14
        //   609: invokestatic    com/intellij/lexer/LexerUtil.getTokenText:(Lcom/intellij/lexer/Lexer;)Ljava/lang/CharSequence;
        //   612: astore          22
        //   614: iconst_m1      
        //   615: istore          23
        //   617: aconst_null    
        //   618: astore          24
        //   620: aload           21
        //   622: ifnull          704
        //   625: aload           14
        //   627: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.getTokenStart:()I
        //   630: aload           21
        //   632: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   635: if_icmplt       704
        //   638: goto            645
        //   641: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   644: athrow         
        //   645: aload           20
        //   647: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //   650: ifnull          673
        //   653: goto            660
        //   656: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   659: athrow         
        //   660: aload           20
        //   662: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //   665: checkcast       Ljava/lang/Integer;
        //   668: invokevirtual   java/lang/Integer.intValue:()I
        //   671: istore          23
        //   673: new             Lcom/intellij/openapi/util/TextRange;
        //   676: dup            
        //   677: aload           14
        //   679: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.getTokenStart:()I
        //   682: aload           21
        //   684: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   687: isub           
        //   688: aload           14
        //   690: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.getTokenEnd:()I
        //   693: aload           21
        //   695: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   698: isub           
        //   699: invokespecial   com/intellij/openapi/util/TextRange.<init>:(II)V
        //   702: astore          24
        //   704: aload           22
        //   706: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //   711: astore          11
        //   713: aload_0        
        //   714: aload           19
        //   716: aload           11
        //   718: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/String;)V
        //   721: aload_0        
        //   722: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.getCacheSize:()I
        //   725: istore          12
        //   727: new             Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //   730: dup            
        //   731: aload           19
        //   733: aload           22
        //   735: aload           4
        //   737: iload           23
        //   739: aload           24
        //   741: iload           18
        //   743: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType.<init>:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;Ljava/lang/String;ILcom/intellij/openapi/util/TextRange;I)V
        //   746: astore          10
        //   748: aload_0        
        //   749: aload_1        
        //   750: invokevirtual   com/intellij/lexer/Lexer.getTokenStart:()I
        //   753: aload           10
        //   755: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.addToken:(ILcom/intellij/psi/tree/IElementType;)V
        //   758: iinc            18, 1
        //   761: aload           14
        //   763: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.advance:()V
        //   766: aload           21
        //   768: ifnull          801
        //   771: aload           14
        //   773: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.getTokenStart:()I
        //   776: aload           21
        //   778: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   781: if_icmplt       801
        //   784: goto            791
        //   787: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   790: athrow         
        //   791: iinc            17, 1
        //   794: goto            801
        //   797: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   800: athrow         
        //   801: goto            404
        //   804: aload           13
        //   806: ifnull          855
        //   809: aload_0        
        //   810: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   813: aload           13
        //   815: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.define:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;)V
        //   820: goto            855
        //   823: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   826: athrow         
        //   827: astore          25
        //   829: aload           13
        //   831: ifnull          852
        //   834: aload_0        
        //   835: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   838: aload           13
        //   840: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.define:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;)V
        //   845: goto            852
        //   848: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   851: athrow         
        //   852: aload           25
        //   854: athrow         
        //   855: aload           11
        //   857: ifnull          1025
        //   860: aload_0        
        //   861: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   864: aload           11
        //   866: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.getDefinition:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   871: astore          14
        //   873: aload           14
        //   875: ifnull          1025
        //   878: aload           14
        //   880: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.hasParameterList:()Z
        //   883: ifeq            1025
        //   886: goto            893
        //   889: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   892: athrow         
        //   893: aload           14
        //   895: aload_2        
        //   896: if_acmpeq       1025
        //   899: goto            906
        //   902: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   905: athrow         
        //   906: aload_0        
        //   907: aload_1        
        //   908: aload           14
        //   910: iconst_0       
        //   911: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/lexer/Lexer;Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;Z)Z
        //   914: ifeq            1025
        //   917: goto            924
        //   920: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   923: athrow         
        //   924: new             Lcom/jetbrains/cidr/lang/preprocessor/OCMacroReferenceTokenType;
        //   927: dup            
        //   928: aload           10
        //   930: invokevirtual   com/intellij/lang/ForeignLeafType.getDelegate:()Lcom/intellij/psi/tree/IElementType;
        //   933: aload           11
        //   935: iconst_0       
        //   936: aload_0        
        //   937: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myMacroLevel:I
        //   940: iconst_1       
        //   941: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCMacroReferenceTokenType.<init>:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;ZIZ)V
        //   944: astore          15
        //   946: aload           10
        //   948: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //   951: ifeq            997
        //   954: aload           10
        //   956: checkcast       Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //   959: astore          17
        //   961: new             Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //   964: dup            
        //   965: aload           15
        //   967: aload           11
        //   969: aload           17
        //   971: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType.getMacroName:()Ljava/lang/String;
        //   974: aload           10
        //   976: invokevirtual   com/intellij/lang/ForeignLeafType.getIndex:()S
        //   979: aload           17
        //   981: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType.getRangeInMacroArgument:()Lcom/intellij/openapi/util/TextRange;
        //   984: aload           17
        //   986: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType.getOffsetInTopSubstitution:()I
        //   989: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType.<init>:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;Ljava/lang/String;ILcom/intellij/openapi/util/TextRange;I)V
        //   992: astore          16
        //   994: goto            1016
        //   997: new             Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //  1000: dup            
        //  1001: aload           15
        //  1003: aload           11
        //  1005: aconst_null    
        //  1006: iconst_0       
        //  1007: getstatic       com/intellij/openapi/util/TextRange.EMPTY_RANGE:Lcom/intellij/openapi/util/TextRange;
        //  1010: iconst_0       
        //  1011: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType.<init>:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;Ljava/lang/String;ILcom/intellij/openapi/util/TextRange;I)V
        //  1014: astore          16
        //  1016: aload_0        
        //  1017: iload           12
        //  1019: aload           16
        //  1021: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.replaceCachedType:(ILcom/intellij/psi/tree/IElementType;)Lcom/intellij/psi/tree/IElementType;
        //  1024: pop            
        //  1025: iconst_1       
        //  1026: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  771    794    797    801    Ljava/lang/IllegalArgumentException;
        //  761    784    787    791    Ljava/lang/IllegalArgumentException;
        //  625    653    656    660    Ljava/lang/IllegalArgumentException;
        //  620    638    641    645    Ljava/lang/IllegalArgumentException;
        //  516    541    544    548    Ljava/lang/IllegalArgumentException;
        //  508    524    527    531    Ljava/lang/IllegalArgumentException;
        //  480    504    504    508    Ljava/lang/IllegalArgumentException;
        //  457    473    473    477    Ljava/lang/IllegalArgumentException;
        //  423    450    450    454    Ljava/lang/IllegalArgumentException;
        //  411    419    419    423    Ljava/lang/IllegalArgumentException;
        //  292    308    311    315    Ljava/lang/IllegalArgumentException;
        //  226    242    242    246    Ljava/lang/IllegalArgumentException;
        //  219    233    236    240    Ljava/lang/IllegalArgumentException;
        //  195    215    215    219    Ljava/lang/IllegalArgumentException;
        //  150    162    165    169    Ljava/lang/IllegalArgumentException;
        //  125    141    144    148    Ljava/lang/IllegalArgumentException;
        //  113    129    132    136    Ljava/lang/IllegalArgumentException;
        //  100    118    121    125    Ljava/lang/IllegalArgumentException;
        //  79     93     93     97     Ljava/lang/IllegalArgumentException;
        //  55     75     75     79     Ljava/lang/IllegalArgumentException;
        //  40     48     51     55     Ljava/lang/IllegalArgumentException;
        //  315    804    827    855    Any
        //  804    823    823    827    Ljava/lang/IllegalArgumentException;
        //  827    829    827    855    Any
        //  829    845    848    852    Ljava/lang/IllegalArgumentException;
        //  873    886    889    893    Ljava/lang/IllegalArgumentException;
        //  878    899    902    906    Ljava/lang/IllegalArgumentException;
        //  893    917    920    924    Ljava/lang/IllegalArgumentException;
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
    
    String preprocess(@NotNull final CharSequence p0, @NotNull final PreprocessingMode p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "text"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "preprocess"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
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
        //    62: ldc             "mode"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "preprocess"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    87: athrow         
        //    88: new             Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer;
        //    91: dup            
        //    92: aload_0        
        //    93: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myState:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //    96: aload_0        
        //    97: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myFile:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   100: iconst_0       
        //   101: iconst_0       
        //   102: iconst_0       
        //   103: aload_0        
        //   104: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myTotalMacroSubstitutionsCnt:Lcom/intellij/openapi/util/Ref;
        //   107: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$IncludePreprocessingMode.FORCE_PREPROCESSING:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$IncludePreprocessingMode;
        //   110: aload_0        
        //   111: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:()Lcom/intellij/openapi/util/Ref;
        //   114: iconst_0       
        //   115: iconst_0       
        //   116: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.<init>:(Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;Lcom/jetbrains/cidr/lang/psi/OCFile;ZIILcom/intellij/openapi/util/Ref;Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$IncludePreprocessingMode;Lcom/intellij/openapi/util/Ref;ZZ)V
        //   119: astore_3       
        //   120: aload_3        
        //   121: aload_1        
        //   122: iconst_0       
        //   123: aload_1        
        //   124: invokeinterface java/lang/CharSequence.length:()I
        //   129: aload_2        
        //   130: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$PreprocessingMode.FILE:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$PreprocessingMode;
        //   133: if_acmpne       144
        //   136: iconst_0       
        //   137: goto            147
        //   140: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   143: athrow         
        //   144: sipush          1024
        //   147: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.start:(Ljava/lang/CharSequence;III)V
        //   150: new             Ljava/lang/StringBuilder;
        //   153: dup            
        //   154: invokespecial   java/lang/StringBuilder.<init>:()V
        //   157: astore          4
        //   159: aload_2        
        //   160: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$PreprocessingMode.FIRST_MACRO_ARG:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$PreprocessingMode;
        //   163: if_acmpeq       180
        //   166: aload_2        
        //   167: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$PreprocessingMode.OTHER_MACRO_ARGS:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$PreprocessingMode;
        //   170: if_acmpne       240
        //   173: goto            180
        //   176: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   179: athrow         
        //   180: iconst_0       
        //   181: istore          5
        //   183: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITESPACES:Lcom/intellij/psi/tree/TokenSet;
        //   186: aload_3        
        //   187: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   190: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   193: ifeq            206
        //   196: iconst_1       
        //   197: istore          5
        //   199: aload_3        
        //   200: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.advance:()V
        //   203: goto            183
        //   206: iload           5
        //   208: ifeq            240
        //   211: aload_2        
        //   212: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$PreprocessingMode.OTHER_MACRO_ARGS:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$PreprocessingMode;
        //   215: if_acmpne       240
        //   218: goto            225
        //   221: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   224: athrow         
        //   225: aload           4
        //   227: ldc             " "
        //   229: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   232: pop            
        //   233: goto            240
        //   236: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   239: athrow         
        //   240: iconst_0       
        //   241: istore          5
        //   243: iconst_0       
        //   244: istore          6
        //   246: iconst_0       
        //   247: istore          7
        //   249: iconst_0       
        //   250: istore          8
        //   252: aload_3        
        //   253: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   256: astore          9
        //   258: aload           9
        //   260: ifnonnull       270
        //   263: goto            548
        //   266: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   269: athrow         
        //   270: aload           9
        //   272: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //   275: ifeq            291
        //   278: aload           9
        //   280: checkcast       Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType;
        //   283: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType.getDelegate:()Lcom/intellij/psi/tree/IElementType;
        //   286: astore          9
        //   288: goto            270
        //   291: aload           9
        //   293: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEFINE_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   296: if_acmpne       312
        //   299: iconst_1       
        //   300: istore          8
        //   302: iload           6
        //   304: istore          7
        //   306: iinc            6, 1
        //   309: goto            541
        //   312: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //   315: aload           9
        //   317: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   320: ifeq            333
        //   323: iinc            6, 1
        //   326: goto            541
        //   329: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   332: athrow         
        //   333: aload           9
        //   335: invokestatic    com/jetbrains/cidr/lang/parser/OCParsing.isEndOfDirective:(Lcom/intellij/psi/tree/IElementType;)Z
        //   338: ifeq            364
        //   341: iinc            6, -1
        //   344: iload           6
        //   346: iload           7
        //   348: if_icmpne       541
        //   351: goto            358
        //   354: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   357: athrow         
        //   358: iconst_0       
        //   359: istore          8
        //   361: goto            541
        //   364: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.END_IF_DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //   367: aload           9
        //   369: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   372: ifeq            385
        //   375: iinc            6, -1
        //   378: goto            541
        //   381: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   384: athrow         
        //   385: aload           9
        //   387: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroReferenceTokenType;
        //   390: ifeq            457
        //   393: aload           9
        //   395: astore          10
        //   397: aload           10
        //   399: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroReferenceTokenType;
        //   402: ifeq            418
        //   405: aload           10
        //   407: checkcast       Lcom/jetbrains/cidr/lang/preprocessor/OCMacroReferenceTokenType;
        //   410: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCMacroReferenceTokenType.getDelegate:()Lcom/intellij/psi/tree/IElementType;
        //   413: astore          10
        //   415: goto            397
        //   418: aload           10
        //   420: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   423: if_acmpne       436
        //   426: iinc            5, 1
        //   429: goto            454
        //   432: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   435: athrow         
        //   436: aload           10
        //   438: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   441: if_acmpne       454
        //   444: iinc            5, -1
        //   447: goto            454
        //   450: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   453: athrow         
        //   454: goto            541
        //   457: iload           5
        //   459: ifne            541
        //   462: iload           6
        //   464: ifne            541
        //   467: goto            474
        //   470: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   473: athrow         
        //   474: iload           8
        //   476: ifne            541
        //   479: goto            486
        //   482: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   485: athrow         
        //   486: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.SKIP_TOKENS:Lcom/intellij/psi/tree/TokenSet;
        //   489: aload           9
        //   491: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   494: ifne            541
        //   497: goto            504
        //   500: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   503: athrow         
        //   504: aload_3        
        //   505: aload_2        
        //   506: getstatic       com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$PreprocessingMode.DIRECTIVE_CONTENTS:Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$PreprocessingMode;
        //   509: if_acmpeq       527
        //   512: goto            519
        //   515: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   518: athrow         
        //   519: iconst_1       
        //   520: goto            528
        //   523: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   526: athrow         
        //   527: iconst_0       
        //   528: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Lcom/intellij/lexer/Lexer;Z)Ljava/lang/String;
        //   531: astore          10
        //   533: aload           4
        //   535: aload           10
        //   537: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   540: pop            
        //   541: aload_3        
        //   542: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.advance:()V
        //   545: goto            252
        //   548: aload           4
        //   550: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   553: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  120    140    140    144    Ljava/lang/IllegalArgumentException;
        //  159    173    176    180    Ljava/lang/IllegalArgumentException;
        //  206    218    221    225    Ljava/lang/IllegalArgumentException;
        //  211    233    236    240    Ljava/lang/IllegalArgumentException;
        //  258    266    266    270    Ljava/lang/IllegalArgumentException;
        //  312    329    329    333    Ljava/lang/IllegalArgumentException;
        //  333    351    354    358    Ljava/lang/IllegalArgumentException;
        //  364    381    381    385    Ljava/lang/IllegalArgumentException;
        //  418    432    432    436    Ljava/lang/IllegalArgumentException;
        //  436    447    450    454    Ljava/lang/IllegalArgumentException;
        //  457    467    470    474    Ljava/lang/IllegalArgumentException;
        //  462    479    482    486    Ljava/lang/IllegalArgumentException;
        //  474    497    500    504    Ljava/lang/IllegalArgumentException;
        //  486    512    515    519    Ljava/lang/IllegalArgumentException;
        //  504    523    523    527    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0474:
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
    
    private static String a(final Lexer lexer, final boolean b) {
        final IElementType tokenType = lexer.getTokenType();
        try {
            if (tokenType instanceof TokenWrapper) {
                return ((TokenWrapper)tokenType).getValue();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0052: {
            try {
                if (!b) {
                    break Label_0052;
                }
                final TokenSet set = OCTokenTypes.COMMENTS;
                final TokenWrapper tokenWrapper = (TokenWrapper)tokenType;
                final boolean b2 = set.contains((IElementType)tokenWrapper);
                if (b2) {
                    return "";
                }
                break Label_0052;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final TokenSet set = OCTokenTypes.COMMENTS;
                final TokenWrapper tokenWrapper = (TokenWrapper)tokenType;
                final boolean b2 = set.contains((IElementType)tokenWrapper);
                if (b2) {
                    return "";
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final String string = lexer.getBufferSequence().subSequence(lexer.getTokenStart(), lexer.getTokenEnd()).toString();
        try {
            if (tokenType != TokenType.WHITE_SPACE) {
                return string;
            }
            final String s = string;
            final boolean b3 = StringUtil.isEmpty(s);
            if (b3) {
                return " ";
            }
            return string;
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            final String s = string;
            final boolean b3 = StringUtil.isEmpty(s);
            if (b3) {
                return " ";
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return string;
    }
    
    private SubstitutionResult a(final OCMacroSymbol p0, final List<String> p1, final Map<String, Integer> p2, final Map<String, String> p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/jetbrains/cidr/lang/lexer/OCLexer.createRawLexerForPreprocessor:()Lcom/intellij/lexer/Lexer;
        //     3: astore          5
        //     5: ldc             "__LINE__"
        //     7: aload_1        
        //     8: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.getName:()Ljava/lang/String;
        //    11: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    14: ifeq            32
        //    17: aload_0        
        //    18: getfield        com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.myLineCount:Lcom/intellij/openapi/util/Ref;
        //    21: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //    24: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    27: astore          6
        //    29: goto            38
        //    32: aload_1        
        //    33: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.getSubstitution:()Ljava/lang/String;
        //    36: astore          6
        //    38: aload           5
        //    40: aload           6
        //    42: iconst_0       
        //    43: aload           6
        //    45: invokevirtual   java/lang/String.length:()I
        //    48: bipush          8
        //    50: invokevirtual   com/intellij/lexer/Lexer.start:(Ljava/lang/CharSequence;III)V
        //    53: new             Lgnu/trove/THashMap;
        //    56: dup            
        //    57: aload_2        
        //    58: invokeinterface java/util/List.size:()I
        //    63: invokespecial   gnu/trove/THashMap.<init>:(I)V
        //    66: astore          7
        //    68: new             Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$SubstitutionResult;
        //    71: dup            
        //    72: aconst_null    
        //    73: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$SubstitutionResult.<init>:(Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$1;)V
        //    76: astore          8
        //    78: iconst_0       
        //    79: istore          9
        //    81: iconst_0       
        //    82: istore          10
        //    84: aload           5
        //    86: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    89: ifnull          502
        //    92: aload           5
        //    94: invokestatic    com/intellij/lexer/LexerUtil.getTokenText:(Lcom/intellij/lexer/Lexer;)Ljava/lang/CharSequence;
        //    97: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //   102: astore          11
        //   104: aload           5
        //   106: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   109: astore          12
        //   111: aload           12
        //   113: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.HASH:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   116: if_acmpne       135
        //   119: iconst_1       
        //   120: istore          9
        //   122: aload           8
        //   124: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$SubstitutionResult.onHashOperator:()V
        //   127: aload           5
        //   129: invokevirtual   com/intellij/lexer/Lexer.advance:()V
        //   132: goto            499
        //   135: aload           12
        //   137: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.HASHHASH:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   140: if_acmpne       154
        //   143: iconst_1       
        //   144: istore          10
        //   146: aload           5
        //   148: invokevirtual   com/intellij/lexer/Lexer.advance:()V
        //   151: goto            499
        //   154: aload           12
        //   156: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.UNKNOWN_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   159: if_acmpeq       212
        //   162: aload           12
        //   164: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   167: if_acmpeq       212
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   176: athrow         
        //   177: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //   180: aload           12
        //   182: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   185: ifne            212
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   194: athrow         
        //   195: aload           12
        //   197: aload           11
        //   199: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isAlternativeCppPunctuator:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;)Z
        //   202: ifeq            392
        //   205: goto            212
        //   208: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   211: athrow         
        //   212: aload           5
        //   214: invokevirtual   com/intellij/lexer/Lexer.advance:()V
        //   217: iconst_0       
        //   218: istore          13
        //   220: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   223: aload           5
        //   225: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   228: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   231: ifeq            245
        //   234: aload           5
        //   236: invokevirtual   com/intellij/lexer/Lexer.advance:()V
        //   239: iconst_1       
        //   240: istore          13
        //   242: goto            220
        //   245: iload           10
        //   247: ifne            276
        //   250: aload           5
        //   252: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   255: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.HASHHASH:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   258: if_acmpeq       276
        //   261: goto            268
        //   264: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   267: athrow         
        //   268: iconst_1       
        //   269: goto            277
        //   272: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   275: athrow         
        //   276: iconst_0       
        //   277: istore          14
        //   279: aload           12
        //   281: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.UNKNOWN_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   284: if_acmpeq       299
        //   287: iload           9
        //   289: ifeq            307
        //   292: goto            299
        //   295: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   298: athrow         
        //   299: iconst_1       
        //   300: goto            308
        //   303: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   306: athrow         
        //   307: iconst_0       
        //   308: istore          15
        //   310: aload           12
        //   312: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.UNKNOWN_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   315: if_acmpne       331
        //   318: aload           11
        //   320: iconst_1       
        //   321: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   324: goto            333
        //   327: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   330: athrow         
        //   331: aload           11
        //   333: astore          16
        //   335: aload_0        
        //   336: aload           4
        //   338: aload_3        
        //   339: aload           8
        //   341: iload           15
        //   343: iload           14
        //   345: aload           16
        //   347: aload           7
        //   349: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/util/Map;Ljava/util/Map;Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$SubstitutionResult;ZZLjava/lang/String;Ljava/util/Map;)V
        //   352: iload           13
        //   354: ifeq            389
        //   357: aload           5
        //   359: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   362: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.HASHHASH:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   365: if_acmpeq       389
        //   368: goto            375
        //   371: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   374: athrow         
        //   375: aload           8
        //   377: ldc             " "
        //   379: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$SubstitutionResult.access$200:(Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$SubstitutionResult;Ljava/lang/String;)V
        //   382: goto            389
        //   385: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   388: athrow         
        //   389: goto            499
        //   392: iconst_0       
        //   393: istore          13
        //   395: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   398: aload           5
        //   400: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   403: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   406: ifeq            420
        //   409: aload           5
        //   411: invokevirtual   com/intellij/lexer/Lexer.advance:()V
        //   414: iconst_1       
        //   415: istore          13
        //   417: goto            395
        //   420: iload           13
        //   422: ifeq            481
        //   425: iload           9
        //   427: ifne            499
        //   430: goto            437
        //   433: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   436: athrow         
        //   437: iload           10
        //   439: ifne            499
        //   442: goto            449
        //   445: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   448: athrow         
        //   449: aload           5
        //   451: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   454: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.HASHHASH:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   457: if_acmpeq       499
        //   460: goto            467
        //   463: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   466: athrow         
        //   467: aload           8
        //   469: ldc             " "
        //   471: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$SubstitutionResult.access$200:(Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$SubstitutionResult;Ljava/lang/String;)V
        //   474: goto            499
        //   477: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   480: athrow         
        //   481: aload           5
        //   483: invokevirtual   com/intellij/lexer/Lexer.advance:()V
        //   486: aload           8
        //   488: aload           11
        //   490: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$SubstitutionResult.access$200:(Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$SubstitutionResult;Ljava/lang/String;)V
        //   493: iconst_0       
        //   494: istore          10
        //   496: iconst_0       
        //   497: istore          9
        //   499: goto            84
        //   502: aload           8
        //   504: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;Ljava/util/List<Ljava/lang/String;>;Ljava/util/Map<Ljava/lang/String;Ljava/lang/Integer;>;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)Lcom/jetbrains/cidr/lang/preprocessor/OCPreprocessingLexer$SubstitutionResult;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  154    170    173    177    Ljava/lang/IllegalArgumentException;
        //  162    188    191    195    Ljava/lang/IllegalArgumentException;
        //  177    205    208    212    Ljava/lang/IllegalArgumentException;
        //  245    261    264    268    Ljava/lang/IllegalArgumentException;
        //  250    272    272    276    Ljava/lang/IllegalArgumentException;
        //  279    292    295    299    Ljava/lang/IllegalArgumentException;
        //  287    303    303    307    Ljava/lang/IllegalArgumentException;
        //  310    327    327    331    Ljava/lang/IllegalArgumentException;
        //  335    368    371    375    Ljava/lang/IllegalArgumentException;
        //  357    382    385    389    Ljava/lang/IllegalArgumentException;
        //  420    430    433    437    Ljava/lang/IllegalArgumentException;
        //  425    442    445    449    Ljava/lang/IllegalArgumentException;
        //  437    460    463    467    Ljava/lang/IllegalArgumentException;
        //  449    477    477    481    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0177:
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
    
    private void a(final Map<String, String> map, final Map<String, Integer> map2, final SubstitutionResult substitutionResult, final boolean b, final boolean b2, final String s, final Map<String, String> map3) {
        String s2 = map.get(s);
        String string;
        Integer n;
        if (s2 == null) {
            string = s + "...";
            s2 = map.get(string);
            n = map2.get(string);
        }
        else {
            string = null;
            n = map2.get(s);
        }
        Label_0433: {
            String trimLeading = null;
            Label_0424: {
                Label_0401: {
                    Label_0259: {
                        Label_0218: {
                            Label_0188: {
                                Label_0147: {
                                    try {
                                        if (s2 == null) {
                                            break Label_0433;
                                        }
                                        if (!b) {
                                            break Label_0147;
                                        }
                                    }
                                    catch (IllegalArgumentException ex) {
                                        throw a(ex);
                                    }
                                    final String a = a(b(s2));
                                    try {
                                        if (n != null) {
                                            substitutionResult.a(n, a.length());
                                        }
                                    }
                                    catch (IllegalArgumentException ex2) {
                                        throw a(ex2);
                                    }
                                    substitutionResult.a(a);
                                    return;
                                    try {
                                        if (!b2) {
                                            break Label_0218;
                                        }
                                        if (!map3.containsKey(s2)) {
                                            break Label_0188;
                                        }
                                    }
                                    catch (IllegalArgumentException ex3) {
                                        throw a(ex3);
                                    }
                                }
                                trimLeading = map3.get(s2);
                                break Label_0259;
                            }
                            final String preprocess = this.preprocess(s2, PreprocessingMode.FIRST_MACRO_ARG);
                            map3.put(s2, preprocess);
                            trimLeading = preprocess;
                            break Label_0259;
                        }
                        trimLeading = StringUtil.trimLeading(b(s2));
                        Label_0248: {
                            try {
                                if (string == null) {
                                    break Label_0259;
                                }
                                final String s3 = trimLeading;
                                final boolean b3 = s3.isEmpty();
                                if (b3) {
                                    break Label_0248;
                                }
                                break Label_0259;
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                            try {
                                final String s3 = trimLeading;
                                final boolean b3 = s3.isEmpty();
                                if (b3) {
                                    substitutionResult.removeLastComma();
                                }
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                        }
                        try {
                            if (n == null) {
                                break Label_0424;
                            }
                            if (string == null) {
                                break Label_0401;
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                    }
                    int intValue = n;
                    int n2 = 0;
                    while (true) {
                        final String s4 = map.get(string + n2);
                        try {
                            if (s4 == null) {
                                break;
                            }
                        }
                        catch (IllegalArgumentException ex7) {
                            throw a(ex7);
                        }
                        try {
                            if (!s4.equals(",")) {
                                substitutionResult.a(intValue++, s4.length());
                            }
                        }
                        catch (IllegalArgumentException ex8) {
                            throw a(ex8);
                        }
                        PreprocessingMode preprocessingMode = null;
                        Label_0378: {
                            try {
                                if (n2 == 0) {
                                    preprocessingMode = PreprocessingMode.FIRST_MACRO_ARG;
                                    break Label_0378;
                                }
                            }
                            catch (IllegalArgumentException ex9) {
                                throw a(ex9);
                            }
                            preprocessingMode = PreprocessingMode.OTHER_MACRO_ARGS;
                        }
                        substitutionResult.a(this.preprocess(s4, preprocessingMode));
                        ++n2;
                    }
                    return;
                }
                substitutionResult.a(n, trimLeading.length());
                substitutionResult.a(trimLeading);
                return;
            }
            substitutionResult.a(trimLeading);
            return;
        }
        substitutionResult.a(s);
    }
    
    private static String b(final String s) {
        try {
            if (c(s) != null) {
                return "__LINE__";
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return s;
    }
    
    private static String a(final String s) {
        final Lexer rawLexerForPreprocessor = OCLexer.createRawLexerForPreprocessor();
        rawLexerForPreprocessor.start((CharSequence)s);
        try {
            while (OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(rawLexerForPreprocessor.getTokenType())) {
                rawLexerForPreprocessor.advance();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (rawLexerForPreprocessor.getTokenType() == null) {
                return "\"\"";
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("\"");
        int n = 0;
        while (true) {
            Label_0082: {
                try {
                    if (rawLexerForPreprocessor.getTokenType() == null) {
                        break;
                    }
                    final int n2 = n;
                    if (n2 != 0) {
                        break Label_0082;
                    }
                    break Label_0082;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final int n2 = n;
                    if (n2 != 0) {
                        sb.append(" ");
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            final IElementType tokenType = rawLexerForPreprocessor.getTokenType();
            String s2 = rawLexerForPreprocessor.getTokenText();
            Label_0138: {
                try {
                    if (tokenType != OCTokenTypes.STRING_LITERAL) {
                        if (tokenType != OCTokenTypes.CHARACTER_LITERAL) {
                            break Label_0138;
                        }
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                s2 = StringUtil.escapeStringCharacters(s2);
            }
            sb.append(s2);
            rawLexerForPreprocessor.advance();
            n = 0;
            while (OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(rawLexerForPreprocessor.getTokenType())) {
                rawLexerForPreprocessor.advance();
                n = 1;
            }
        }
        sb.append("\"");
        return sb.toString();
    }
    
    private static Map<String, Integer> a(final List<String> list) {
        Label_0020: {
            try {
                if (list == null) {
                    break Label_0020;
                }
                final List<String> list2 = list;
                final int n = list2.size();
                if (n == 0) {
                    break Label_0020;
                }
                break Label_0020;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final List<String> list2 = list;
                final int n = list2.size();
                if (n == 0) {
                    return Collections.emptyMap();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final HashMap hashMap = new HashMap();
        int n2 = 0;
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            hashMap.put((Object)iterator.next(), (Object)(n2++));
        }
        return (Map<String, Integer>)hashMap;
    }
    
    private static Map<String, String> a(final List<String> list, final List<String> list2) {
        Label_0020: {
            try {
                if (list2 == null) {
                    break Label_0020;
                }
                final List<String> list3 = list2;
                final int n = list3.size();
                if (n == 0) {
                    break Label_0020;
                }
                break Label_0020;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final List<String> list3 = list2;
                final int n = list3.size();
                if (n == 0) {
                    return Collections.emptyMap();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final HashMap hashMap = new HashMap();
        int n2 = 0;
        for (final String s : list) {
            if (s.endsWith("...")) {
                final StringBuilder sb = new StringBuilder();
                int n3 = 0;
                for (int i = n2; i < list2.size(); ++i) {
                    String trimTrailing = list2.get(i);
                    boolean b = false;
                    Label_0132: {
                        try {
                            if (i == n2) {
                                b = true;
                                break Label_0132;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        b = false;
                    }
                    final boolean b2 = b;
                    try {
                        if (!b2) {
                            sb.append(",");
                            ((Map<String, String>)hashMap).put(s + n3++, ",");
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    if (i == list2.size() - 1) {
                        trimTrailing = StringUtil.trimTrailing(trimTrailing);
                    }
                    sb.append(trimTrailing);
                    ((Map<String, String>)hashMap).put(s + n3++, trimTrailing);
                }
                ((Map<String, String>)hashMap).put(s, sb.toString());
                break;
            }
            HashMap hashMap2 = null;
            String s2 = null;
            String trimTrailing2 = null;
            Label_0304: {
                try {
                    hashMap2 = hashMap;
                    s2 = s;
                    if (n2 >= list2.size()) {
                        trimTrailing2 = "";
                        break Label_0304;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                trimTrailing2 = StringUtil.trimTrailing((String)list2.get(n2));
            }
            ((Map<String, String>)hashMap2).put(s2, trimTrailing2);
            ++n2;
        }
        return (Map<String, String>)hashMap;
    }
    
    static {
        DONT_SUBSTITUTE_IN_DEFINES = Key.create("DONT_SUBSTITUTE_IN_DEFINES");
        LINE_MACRO_STUB_PATTERN = Pattern.compile("^\\s*__CIDR_LINE_MACRO_STUB_(\\d+)\\s*$");
        ID = (b -> b);
        SKIP_TOKENS = TokenSet.create(new IElementType[] { OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT, OCTokenTypes.EOL_ESCAPE });
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
    
    public enum IncludePreprocessingMode
    {
        USE_SYMBOL_TABLE, 
        FORCE_PREPROCESSING, 
        SKIP;
    }
    
    private enum CurrentState
    {
        NONE, 
        AFTER_EXTERN, 
        AFTER_EXTERN_C, 
        AFTER_PROTOCOL, 
        AFTER_AT, 
        AFTER_MODULE_IMPORT;
    }
    
    enum PreprocessingMode
    {
        FILE, 
        DIRECTIVE_CONTENTS, 
        FIRST_MACRO_ARG, 
        OTHER_MACRO_ARGS;
    }
    
    private static class SubstitutionResult
    {
        private StringBuilder myString;
        private List<Pair<Integer, TextRange>> mySubstitutions;
        
        private SubstitutionResult() {
            this.myString = new StringBuilder();
            this.mySubstitutions = new ArrayList<Pair<Integer, TextRange>>();
        }
        
        public String getString() {
            return this.myString.toString();
        }
        
        private void a(final String s) {
            this.myString.append(s);
        }
        
        private void a(final int n, final int n2) {
            this.mySubstitutions.add((Pair<Integer, TextRange>)new Pair((Object)n, (Object)new TextRange(this.myString.length(), this.myString.length() + n2)));
        }
        
        public List<Pair<Integer, TextRange>> getSubstitutions() {
            return this.mySubstitutions;
        }
        
        public void removeLastComma() {
            int length;
            for (length = this.myString.length() - 1; length >= 0 && this.myString.charAt(length) == ' '; --length) {}
            if (length >= 0 && this.myString.charAt(length) == ',') {
                this.myString.setLength(length);
            }
        }
        
        public void onHashOperator() {
            int length;
            int n;
            for (n = (length = this.myString.length()); length > 0 && Character.isJavaIdentifierPart(this.myString.charAt(length - 1)); --length) {}
            if (length != n && OCStringLiteralUtil.isStringLiteralPrefix(this.myString.substring(length, n))) {
                this.myString.append(" ");
            }
        }
    }
}
