// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import java.util.Collection;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.BuilderDriverBase;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import java.util.ArrayList;
import com.intellij.psi.tree.IElementType;
import java.util.List;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.OCLanguageKind;

public class OCTypeBuilder
{
    private final OCLanguageKind myFileKind;
    private final Project myProject;
    private OCType myCurrentType;
    private int longKeywords;
    private List<IElementType> myRefTokens;
    private List<IElementType> myFunctionRefTokens;
    private ArrayList<String> myProtocolList;
    private boolean myIsTypedef;
    private boolean myWasLT;
    private boolean myWasConst;
    private boolean myWasNonArrayExpression;
    private boolean myIsInsideBrackets;
    private boolean myPassByReference;
    private boolean myConst;
    private boolean mySign;
    private boolean myUnsign;
    private boolean myWasComplex;
    private boolean myVariadic;
    private OCQualifiedName myPointerQualifier;
    private int myParLevel;
    private BuilderDriverBase.DeclarationContext myLocalContext;
    private OCQualifiedName myNamespaceQualifier;
    private String myName;
    private List<OCTypeArgument> myTypeArguments;
    private int[] myArrayLengths;
    private int myLastIntLiteral;
    private ARCAttribute myARCAttribute;
    private boolean myWasAuto;
    private boolean myIsKindof;
    private OCNullability myNullability;
    private boolean myAssumeNonNullOn;
    
    public OCTypeBuilder(final OCLanguageKind myFileKind, final Project myProject, @NotNull final BuilderDriverBase.DeclarationContext myLocalContext) {
        if (myLocalContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCTypeBuilder", "<init>"));
        }
        this.longKeywords = 0;
        this.myRefTokens = new ArrayList<IElementType>();
        this.myFunctionRefTokens = new ArrayList<IElementType>();
        this.myProtocolList = null;
        this.myIsTypedef = false;
        this.myWasLT = false;
        this.myWasConst = false;
        this.myWasNonArrayExpression = false;
        this.myIsInsideBrackets = false;
        this.myPassByReference = false;
        this.mySign = false;
        this.myUnsign = false;
        this.myParLevel = 0;
        this.myArrayLengths = ArrayUtil.EMPTY_INT_ARRAY;
        this.myLastIntLiteral = -1;
        this.myWasAuto = false;
        this.myIsKindof = false;
        this.myNullability = null;
        this.myFileKind = myFileKind;
        this.myProject = myProject;
        this.myLocalContext = myLocalContext;
        this.myAssumeNonNullOn = myLocalContext.isAssumeNonNull();
        if (myLocalContext.isLambdaInitCapture()) {
            this.myWasAuto = true;
            this.myCurrentType = new OCAutoType();
        }
    }
    
    public void setLocalContext(@NotNull final BuilderDriverBase.DeclarationContext myLocalContext) {
        try {
            if (myLocalContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "localContext", "com/jetbrains/cidr/lang/types/OCTypeBuilder", "setLocalContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myLocalContext = myLocalContext;
    }
    
    public void setPointerQualifier(final OCQualifiedName myPointerQualifier) {
        this.myPointerQualifier = myPointerQualifier;
    }
    
    public void learn(final IElementType elementType) {
        this.learn(elementType, null);
    }
    
    public void learnNamespaceQualifier(final OCQualifiedName myNamespaceQualifier) {
        this.myNamespaceQualifier = myNamespaceQualifier;
    }
    
    public void learn(final IElementType p0, @Nullable final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.isInsideParentheses:()Z
        //     4: ifeq            18
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myFunctionRefTokens:Ljava/util/List;
        //    11: goto            22
        //    14: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    17: athrow         
        //    18: aload_0        
        //    19: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myRefTokens:Ljava/util/List;
        //    22: astore_3       
        //    23: aload_1        
        //    24: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    27: if_acmpne       52
        //    30: aload_3        
        //    31: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    34: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    39: pop            
        //    40: aload_0        
        //    41: iconst_0       
        //    42: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myConst:Z
        //    45: goto            462
        //    48: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: aload_1        
        //    53: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.XOR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    56: if_acmpne       81
        //    59: aload_3        
        //    60: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.XOR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    63: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    68: pop            
        //    69: aload_0        
        //    70: iconst_0       
        //    71: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myConst:Z
        //    74: goto            462
        //    77: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: aload_1        
        //    82: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    85: if_acmpne       110
        //    88: aload_3        
        //    89: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    92: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    97: pop            
        //    98: aload_0        
        //    99: iconst_0       
        //   100: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myConst:Z
        //   103: goto            462
        //   106: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: aload_1        
        //   111: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   114: if_acmpne       139
        //   117: aload_3        
        //   118: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   121: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   126: pop            
        //   127: aload_0        
        //   128: iconst_0       
        //   129: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myConst:Z
        //   132: goto            462
        //   135: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: aload_1        
        //   140: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONST_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   143: if_acmpne       173
        //   146: aload_3        
        //   147: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONST_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   150: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   155: pop            
        //   156: aload_0        
        //   157: iconst_1       
        //   158: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myConst:Z
        //   161: aload_0        
        //   162: iconst_1       
        //   163: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myWasConst:Z
        //   166: goto            462
        //   169: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: aload_1        
        //   174: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.VOLATILE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   177: if_acmpne       197
        //   180: aload_3        
        //   181: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.VOLATILE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   184: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   189: pop            
        //   190: goto            462
        //   193: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: aload_1        
        //   198: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   201: if_acmpne       221
        //   204: aload_0        
        //   205: iconst_m1      
        //   206: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLastIntLiteral:I
        //   209: aload_0        
        //   210: iconst_1       
        //   211: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myIsInsideBrackets:Z
        //   214: goto            462
        //   217: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   220: athrow         
        //   221: aload_1        
        //   222: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   225: if_acmpne       288
        //   228: aload_0        
        //   229: aload_0        
        //   230: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myArrayLengths:[I
        //   233: aload_0        
        //   234: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLastIntLiteral:I
        //   237: invokestatic    com/intellij/util/ArrayUtil.append:([II)[I
        //   240: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myArrayLengths:[I
        //   243: aload_3        
        //   244: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   247: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   252: pop            
        //   253: aload_0        
        //   254: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLastIntLiteral:I
        //   257: iconst_m1      
        //   258: if_icmpeq       280
        //   261: goto            268
        //   264: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   267: athrow         
        //   268: aload_0        
        //   269: iconst_1       
        //   270: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myConst:Z
        //   273: goto            280
        //   276: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   279: athrow         
        //   280: aload_0        
        //   281: iconst_0       
        //   282: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myIsInsideBrackets:Z
        //   285: goto            462
        //   288: aload_1        
        //   289: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   292: if_acmpne       312
        //   295: aload_0        
        //   296: dup            
        //   297: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myParLevel:I
        //   300: iconst_1       
        //   301: iadd           
        //   302: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myParLevel:I
        //   305: goto            462
        //   308: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   311: athrow         
        //   312: aload_1        
        //   313: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   316: if_acmpne       336
        //   319: aload_0        
        //   320: dup            
        //   321: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myParLevel:I
        //   324: iconst_1       
        //   325: isub           
        //   326: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myParLevel:I
        //   329: goto            462
        //   332: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   335: athrow         
        //   336: aload_1        
        //   337: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ELLIPSIS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   340: if_acmpne       412
        //   343: aload_0        
        //   344: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   347: ifnonnull       371
        //   350: goto            357
        //   353: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   356: athrow         
        //   357: aload_0        
        //   358: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myName:Ljava/lang/String;
        //   361: ifnull          390
        //   364: goto            371
        //   367: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   370: athrow         
        //   371: aload_0        
        //   372: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myFileKind:Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   375: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isCpp:()Z
        //   380: ifne            404
        //   383: goto            390
        //   386: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   389: athrow         
        //   390: aload_0        
        //   391: invokestatic    com/jetbrains/cidr/lang/types/OCEllipsisType.instance:()Lcom/jetbrains/cidr/lang/types/OCEllipsisType;
        //   394: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   397: goto            462
        //   400: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   403: athrow         
        //   404: aload_0        
        //   405: iconst_1       
        //   406: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myVariadic:Z
        //   409: goto            462
        //   412: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.NULLABILITY_KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //   415: aload_1        
        //   416: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   419: ifeq            437
        //   422: aload_3        
        //   423: aload_1        
        //   424: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   429: pop            
        //   430: goto            462
        //   433: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   436: athrow         
        //   437: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IN_SELECTOR_NULLABILITY_KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //   440: aload_1        
        //   441: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   444: ifeq            462
        //   447: aload_0        
        //   448: aload_1        
        //   449: invokestatic    com/jetbrains/cidr/lang/types/OCNullability.parseFrom:(Lcom/intellij/psi/tree/IElementType;)Lcom/jetbrains/cidr/lang/types/OCNullability;
        //   452: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myNullability:Lcom/jetbrains/cidr/lang/types/OCNullability;
        //   455: goto            462
        //   458: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   461: athrow         
        //   462: aload_0        
        //   463: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.isInsideParentheses:()Z
        //   466: ifeq            474
        //   469: return         
        //   470: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   473: athrow         
        //   474: aload_1        
        //   475: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SIGNED_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   478: if_acmpne       632
        //   481: aload_0        
        //   482: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.mySign:Z
        //   485: ifeq            508
        //   488: goto            495
        //   491: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   494: athrow         
        //   495: aload_0        
        //   496: ldc             "duplicate 'signed'"
        //   498: invokespecial   com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/String;)V
        //   501: goto            508
        //   504: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   507: athrow         
        //   508: aload_0        
        //   509: iconst_1       
        //   510: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.mySign:Z
        //   513: aload_0        
        //   514: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myUnsign:Z
        //   517: ifeq            533
        //   520: aload_0        
        //   521: ldc             "'signed' and 'unsigned' specified together"
        //   523: invokespecial   com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/String;)V
        //   526: goto            533
        //   529: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   532: athrow         
        //   533: aload_0        
        //   534: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   537: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   540: if_acmpeq       560
        //   543: aload_0        
        //   544: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   547: getstatic       com/jetbrains/cidr/lang/types/OCIntType.UCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   550: if_acmpne       574
        //   553: goto            560
        //   556: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   559: athrow         
        //   560: aload_0        
        //   561: getstatic       com/jetbrains/cidr/lang/types/OCIntType.SCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   564: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   567: goto            632
        //   570: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   573: athrow         
        //   574: aload_0        
        //   575: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   578: ifnull          595
        //   581: aload_0        
        //   582: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myWasAuto:Z
        //   585: ifeq            609
        //   588: goto            595
        //   591: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   594: athrow         
        //   595: aload_0        
        //   596: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   599: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   602: goto            632
        //   605: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   608: athrow         
        //   609: aload_0        
        //   610: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   613: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   616: ifne            632
        //   619: aload_0        
        //   620: ldc             "signed is only applicable to integer types!"
        //   622: invokespecial   com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/String;)V
        //   625: goto            632
        //   628: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   631: athrow         
        //   632: aload_1        
        //   633: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.UNSIGNED_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   636: if_acmpne       903
        //   639: aload_0        
        //   640: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myUnsign:Z
        //   643: ifeq            666
        //   646: goto            653
        //   649: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   652: athrow         
        //   653: aload_0        
        //   654: ldc             "duplicate 'unsigned'"
        //   656: invokespecial   com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/String;)V
        //   659: goto            666
        //   662: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   665: athrow         
        //   666: aload_0        
        //   667: iconst_1       
        //   668: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myUnsign:Z
        //   671: aload_0        
        //   672: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.mySign:Z
        //   675: ifeq            691
        //   678: aload_0        
        //   679: ldc             "'signed' and 'unsigned' specified together"
        //   681: invokespecial   com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/String;)V
        //   684: goto            691
        //   687: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   690: athrow         
        //   691: aload_0        
        //   692: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   695: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   698: if_acmpeq       718
        //   701: aload_0        
        //   702: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   705: getstatic       com/jetbrains/cidr/lang/types/OCIntType.SCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   708: if_acmpne       732
        //   711: goto            718
        //   714: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   717: athrow         
        //   718: aload_0        
        //   719: getstatic       com/jetbrains/cidr/lang/types/OCIntType.UCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   722: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   725: goto            2089
        //   728: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   731: athrow         
        //   732: aload_0        
        //   733: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   736: getstatic       com/jetbrains/cidr/lang/types/OCIntType.SHORT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   739: if_acmpne       756
        //   742: aload_0        
        //   743: getstatic       com/jetbrains/cidr/lang/types/OCIntType.USHORT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   746: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   749: goto            2089
        //   752: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   755: athrow         
        //   756: aload_0        
        //   757: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   760: ifnull          794
        //   763: aload_0        
        //   764: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   767: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   770: if_acmpeq       794
        //   773: goto            780
        //   776: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   779: athrow         
        //   780: aload_0        
        //   781: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myWasAuto:Z
        //   784: ifeq            808
        //   787: goto            794
        //   790: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   793: athrow         
        //   794: aload_0        
        //   795: getstatic       com/jetbrains/cidr/lang/types/OCIntType.UINT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   798: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   801: goto            2089
        //   804: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   807: athrow         
        //   808: aload_0        
        //   809: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   812: getstatic       com/jetbrains/cidr/lang/types/OCIntType.LONG:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   815: if_acmpne       832
        //   818: aload_0        
        //   819: getstatic       com/jetbrains/cidr/lang/types/OCIntType.ULONG:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   822: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   825: goto            2089
        //   828: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   831: athrow         
        //   832: aload_0        
        //   833: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   836: getstatic       com/jetbrains/cidr/lang/types/OCIntType.LONGLONG:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   839: if_acmpne       856
        //   842: aload_0        
        //   843: getstatic       com/jetbrains/cidr/lang/types/OCIntType.ULONGLONG:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   846: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   849: goto            2089
        //   852: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   855: athrow         
        //   856: aload_0        
        //   857: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   860: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT128:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   863: if_acmpne       880
        //   866: aload_0        
        //   867: getstatic       com/jetbrains/cidr/lang/types/OCIntType.UINT128:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   870: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   873: goto            2089
        //   876: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   879: athrow         
        //   880: aload_0        
        //   881: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   884: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   887: ifne            2089
        //   890: aload_0        
        //   891: ldc             "unsigned is only applicable to integer types!"
        //   893: invokespecial   com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/String;)V
        //   896: goto            2089
        //   899: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   902: athrow         
        //   903: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AUTO_KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //   906: aload_1        
        //   907: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   910: ifeq            1057
        //   913: aload_0        
        //   914: iconst_1       
        //   915: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myWasAuto:Z
        //   918: aload_0        
        //   919: new             Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   922: dup            
        //   923: invokespecial   com/jetbrains/cidr/lang/types/OCAutoType.<init>:()V
        //   926: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   929: aload_0        
        //   930: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLocalContext:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   933: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParent:()Ljava/lang/Object;
        //   936: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   939: ifeq            1020
        //   942: goto            949
        //   945: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   948: athrow         
        //   949: aload_0        
        //   950: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLocalContext:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   953: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParent:()Ljava/lang/Object;
        //   956: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   959: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getParent:()Lcom/intellij/psi/PsiElement;
        //   964: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;
        //   967: ifeq            1020
        //   970: goto            977
        //   973: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   976: athrow         
        //   977: aload_0        
        //   978: new             Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   981: dup            
        //   982: aload_0        
        //   983: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLocalContext:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   986: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParent:()Ljava/lang/Object;
        //   989: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   992: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getParent:()Lcom/intellij/psi/PsiElement;
        //   997: checkcast       Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;
        //  1000: aload_0        
        //  1001: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLocalContext:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //  1004: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getLambdaParameterIndex:()I
        //  1007: invokespecial   com/jetbrains/cidr/lang/types/OCAutoType.<init>:(Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;I)V
        //  1010: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1013: goto            2089
        //  1016: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1019: athrow         
        //  1020: aload_0        
        //  1021: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLocalContext:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //  1024: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getLambdaParameterIndex:()I
        //  1027: iconst_m1      
        //  1028: if_icmpeq       2089
        //  1031: aload_0        
        //  1032: new             Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //  1035: dup            
        //  1036: aconst_null    
        //  1037: aload_0        
        //  1038: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLocalContext:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //  1041: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getLambdaParameterIndex:()I
        //  1044: invokespecial   com/jetbrains/cidr/lang/types/OCAutoType.<init>:(Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;I)V
        //  1047: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1050: goto            2089
        //  1053: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1056: athrow         
        //  1057: aload_1        
        //  1058: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.INT_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1061: if_acmpne       1126
        //  1064: aload_0        
        //  1065: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1068: ifnull          1109
        //  1071: goto            1078
        //  1074: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1077: athrow         
        //  1078: aload_0        
        //  1079: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1082: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1085: if_acmpeq       1109
        //  1088: goto            1095
        //  1091: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1094: athrow         
        //  1095: aload_0        
        //  1096: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myWasAuto:Z
        //  1099: ifeq            1126
        //  1102: goto            1109
        //  1105: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1108: athrow         
        //  1109: aload_0        
        //  1110: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1113: getstatic       com/jetbrains/cidr/lang/types/OCIntType.UINT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1116: invokespecial   com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Lcom/jetbrains/cidr/lang/types/OCIntType;Lcom/jetbrains/cidr/lang/types/OCIntType;)V
        //  1119: goto            2089
        //  1122: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1125: athrow         
        //  1126: aload_1        
        //  1127: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CHAR_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1130: if_acmpne       1170
        //  1133: aload_0        
        //  1134: aload_0        
        //  1135: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.mySign:Z
        //  1138: ifeq            1158
        //  1141: goto            1148
        //  1144: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1147: athrow         
        //  1148: getstatic       com/jetbrains/cidr/lang/types/OCIntType.SCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1151: goto            1161
        //  1154: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1157: athrow         
        //  1158: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1161: getstatic       com/jetbrains/cidr/lang/types/OCIntType.UCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1164: invokespecial   com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Lcom/jetbrains/cidr/lang/types/OCIntType;Lcom/jetbrains/cidr/lang/types/OCIntType;)V
        //  1167: goto            2089
        //  1170: aload_1        
        //  1171: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SHORT_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1174: if_acmpne       1194
        //  1177: aload_0        
        //  1178: getstatic       com/jetbrains/cidr/lang/types/OCIntType.SHORT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1181: getstatic       com/jetbrains/cidr/lang/types/OCIntType.USHORT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1184: invokespecial   com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Lcom/jetbrains/cidr/lang/types/OCIntType;Lcom/jetbrains/cidr/lang/types/OCIntType;)V
        //  1187: goto            2089
        //  1190: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1193: athrow         
        //  1194: aload_1        
        //  1195: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LONG_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1198: if_acmpne       1339
        //  1201: aload_0        
        //  1202: dup            
        //  1203: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.longKeywords:I
        //  1206: iconst_1       
        //  1207: iadd           
        //  1208: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.longKeywords:I
        //  1211: aload_0        
        //  1212: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1215: getstatic       com/jetbrains/cidr/lang/types/OCRealType.DOUBLE:Lcom/jetbrains/cidr/lang/types/OCRealType;
        //  1218: if_acmpne       1242
        //  1221: goto            1228
        //  1224: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1227: athrow         
        //  1228: aload_0        
        //  1229: getstatic       com/jetbrains/cidr/lang/types/OCRealType.LONG_DOUBLE:Lcom/jetbrains/cidr/lang/types/OCRealType;
        //  1232: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1235: goto            2089
        //  1238: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1241: athrow         
        //  1242: aload_0        
        //  1243: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1246: ifnull          1297
        //  1249: aload_0        
        //  1250: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1253: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1256: if_acmpeq       1297
        //  1259: goto            1266
        //  1262: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1265: athrow         
        //  1266: aload_0        
        //  1267: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1270: getstatic       com/jetbrains/cidr/lang/types/OCIntType.UINT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1273: if_acmpeq       1297
        //  1276: goto            1283
        //  1279: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1282: athrow         
        //  1283: aload_0        
        //  1284: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myWasAuto:Z
        //  1287: ifeq            1314
        //  1290: goto            1297
        //  1293: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1296: athrow         
        //  1297: aload_0        
        //  1298: getstatic       com/jetbrains/cidr/lang/types/OCIntType.LONG:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1301: getstatic       com/jetbrains/cidr/lang/types/OCIntType.ULONG:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1304: invokespecial   com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Lcom/jetbrains/cidr/lang/types/OCIntType;Lcom/jetbrains/cidr/lang/types/OCIntType;)V
        //  1307: goto            2089
        //  1310: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1313: athrow         
        //  1314: aload_0        
        //  1315: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.longKeywords:I
        //  1318: iconst_2       
        //  1319: if_icmpne       2089
        //  1322: aload_0        
        //  1323: getstatic       com/jetbrains/cidr/lang/types/OCIntType.LONGLONG:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1326: getstatic       com/jetbrains/cidr/lang/types/OCIntType.ULONGLONG:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1329: invokespecial   com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Lcom/jetbrains/cidr/lang/types/OCIntType;Lcom/jetbrains/cidr/lang/types/OCIntType;)V
        //  1332: goto            2089
        //  1335: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1338: athrow         
        //  1339: aload_1        
        //  1340: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.VOID_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1343: if_acmpne       1360
        //  1346: aload_0        
        //  1347: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //  1350: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1353: goto            2089
        //  1356: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1359: athrow         
        //  1360: aload_1        
        //  1361: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes._BOOL_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1364: if_acmpne       1381
        //  1367: aload_0        
        //  1368: getstatic       com/jetbrains/cidr/lang/types/OCIntType.BOOL_NATIVE:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1371: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1374: goto            2089
        //  1377: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1380: athrow         
        //  1381: aload_1        
        //  1382: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.FLOAT_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1385: if_acmpne       1422
        //  1388: aload_0        
        //  1389: aload_0        
        //  1390: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myWasComplex:Z
        //  1393: ifeq            1413
        //  1396: goto            1403
        //  1399: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1402: athrow         
        //  1403: getstatic       com/jetbrains/cidr/lang/types/OCRealType.COMPLEX_FLOAT:Lcom/jetbrains/cidr/lang/types/OCRealType;
        //  1406: goto            1416
        //  1409: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1412: athrow         
        //  1413: getstatic       com/jetbrains/cidr/lang/types/OCRealType.FLOAT:Lcom/jetbrains/cidr/lang/types/OCRealType;
        //  1416: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1419: goto            2089
        //  1422: aload_1        
        //  1423: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOUBLE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1426: if_acmpne       1504
        //  1429: aload_0        
        //  1430: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myWasComplex:Z
        //  1433: ifeq            1477
        //  1436: goto            1443
        //  1439: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1442: athrow         
        //  1443: aload_0        
        //  1444: aload_0        
        //  1445: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.longKeywords:I
        //  1448: ifle            1468
        //  1451: goto            1458
        //  1454: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1457: athrow         
        //  1458: getstatic       com/jetbrains/cidr/lang/types/OCRealType.COMPLEX_LONG_DOUBLE:Lcom/jetbrains/cidr/lang/types/OCRealType;
        //  1461: goto            1471
        //  1464: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1467: athrow         
        //  1468: getstatic       com/jetbrains/cidr/lang/types/OCRealType.COMPLEX_DOUBLE:Lcom/jetbrains/cidr/lang/types/OCRealType;
        //  1471: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1474: goto            2089
        //  1477: aload_0        
        //  1478: aload_0        
        //  1479: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.longKeywords:I
        //  1482: ifle            1495
        //  1485: getstatic       com/jetbrains/cidr/lang/types/OCRealType.LONG_DOUBLE:Lcom/jetbrains/cidr/lang/types/OCRealType;
        //  1488: goto            1498
        //  1491: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1494: athrow         
        //  1495: getstatic       com/jetbrains/cidr/lang/types/OCRealType.DOUBLE:Lcom/jetbrains/cidr/lang/types/OCRealType;
        //  1498: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1501: goto            2089
        //  1504: aload_1        
        //  1505: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes._COMPLEX_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1508: if_acmpne       1557
        //  1511: aload_0        
        //  1512: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1515: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
        //  1518: ifeq            1549
        //  1521: goto            1528
        //  1524: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1527: athrow         
        //  1528: aload_0        
        //  1529: aload_0        
        //  1530: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1533: checkcast       Lcom/jetbrains/cidr/lang/types/OCRealType;
        //  1536: invokevirtual   com/jetbrains/cidr/lang/types/OCRealType.cloneWithComplexModifier:()Lcom/jetbrains/cidr/lang/types/OCRealType;
        //  1539: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1542: goto            1549
        //  1545: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1548: athrow         
        //  1549: aload_0        
        //  1550: iconst_1       
        //  1551: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myWasComplex:Z
        //  1554: goto            2089
        //  1557: aload_1        
        //  1558: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1561: if_acmpne       1576
        //  1564: aload_0        
        //  1565: iconst_1       
        //  1566: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myWasLT:Z
        //  1569: goto            2089
        //  1572: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1575: athrow         
        //  1576: aload_1        
        //  1577: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1580: if_acmpne       1590
        //  1583: goto            2089
        //  1586: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1589: athrow         
        //  1590: aload_1        
        //  1591: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BOOL_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //  1594: if_acmpne       1611
        //  1597: aload_0        
        //  1598: getstatic       com/jetbrains/cidr/lang/types/OCIntType.BOOL_NATIVE:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1601: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1604: goto            2089
        //  1607: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1610: athrow         
        //  1611: aload_1        
        //  1612: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WCHAR_T_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //  1615: if_acmpne       1632
        //  1618: aload_0        
        //  1619: getstatic       com/jetbrains/cidr/lang/types/OCIntType.WCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1622: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1625: goto            2089
        //  1628: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1631: athrow         
        //  1632: aload_1        
        //  1633: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CHAR16_T_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //  1636: if_acmpne       1653
        //  1639: aload_0        
        //  1640: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR16:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1643: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1646: goto            2089
        //  1649: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1652: athrow         
        //  1653: aload_1        
        //  1654: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CHAR32_T_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //  1657: if_acmpne       1674
        //  1660: aload_0        
        //  1661: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR32:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1664: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1667: goto            2089
        //  1670: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1673: athrow         
        //  1674: aload_1        
        //  1675: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.INT128_T_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1678: if_acmpne       1698
        //  1681: aload_0        
        //  1682: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT128:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1685: getstatic       com/jetbrains/cidr/lang/types/OCIntType.UINT128:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1688: invokespecial   com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Lcom/jetbrains/cidr/lang/types/OCIntType;Lcom/jetbrains/cidr/lang/types/OCIntType;)V
        //  1691: goto            2089
        //  1694: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1697: athrow         
        //  1698: aload_1        
        //  1699: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.UINT128_T_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1702: if_acmpne       1719
        //  1705: aload_0        
        //  1706: getstatic       com/jetbrains/cidr/lang/types/OCIntType.UINT128:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1709: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1712: goto            2089
        //  1715: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1718: athrow         
        //  1719: aload_1        
        //  1720: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1723: if_acmpeq       1740
        //  1726: aload_1        
        //  1727: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EMPTY_NAME:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1730: if_acmpne       1926
        //  1733: goto            1740
        //  1736: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1739: athrow         
        //  1740: aload_1        
        //  1741: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EMPTY_NAME:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1744: if_acmpne       1784
        //  1747: goto            1754
        //  1750: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1753: athrow         
        //  1754: aload_0        
        //  1755: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myWasAuto:Z
        //  1758: ifeq            1784
        //  1761: goto            1768
        //  1764: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1767: athrow         
        //  1768: aload_0        
        //  1769: new             Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //  1772: dup            
        //  1773: invokespecial   com/jetbrains/cidr/lang/types/OCAutoType.<init>:()V
        //  1776: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1779: return         
        //  1780: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1783: athrow         
        //  1784: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_TYPE_QUALIFIERS:Lgnu/trove/THashSet;
        //  1787: aload_2        
        //  1788: invokevirtual   gnu/trove/THashSet.contains:(Ljava/lang/Object;)Z
        //  1791: ifne            1918
        //  1794: aload_0        
        //  1795: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myProtocolList:Ljava/util/ArrayList;
        //  1798: ifnull          1845
        //  1801: goto            1808
        //  1804: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1807: athrow         
        //  1808: aload_0        
        //  1809: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myWasLT:Z
        //  1812: ifeq            1845
        //  1815: goto            1822
        //  1818: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1821: athrow         
        //  1822: aload_0        
        //  1823: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myProtocolList:Ljava/util/ArrayList;
        //  1826: aload_2        
        //  1827: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  1830: pop            
        //  1831: aload_0        
        //  1832: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myProtocolList:Ljava/util/ArrayList;
        //  1835: invokevirtual   java/util/ArrayList.trimToSize:()V
        //  1838: goto            2089
        //  1841: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1844: athrow         
        //  1845: aload_1        
        //  1846: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EMPTY_NAME:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1849: if_acmpne       1883
        //  1852: aload_0        
        //  1853: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myFileKind:Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //  1856: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isCpp:()Z
        //  1861: ifeq            1883
        //  1864: goto            1871
        //  1867: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1870: athrow         
        //  1871: aload_0        
        //  1872: getstatic       com/jetbrains/cidr/lang/types/OCIntType.LONG:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1875: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1878: return         
        //  1879: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1882: athrow         
        //  1883: aload_0        
        //  1884: new             Ljava/util/ArrayList;
        //  1887: dup            
        //  1888: invokespecial   java/util/ArrayList.<init>:()V
        //  1891: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myProtocolList:Ljava/util/ArrayList;
        //  1894: aload_0        
        //  1895: aload_1        
        //  1896: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EMPTY_NAME:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1899: if_acmpne       1911
        //  1902: ldc             "id"
        //  1904: goto            1912
        //  1907: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1910: athrow         
        //  1911: aload_2        
        //  1912: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myName:Ljava/lang/String;
        //  1915: goto            2089
        //  1918: aload_0        
        //  1919: iconst_1       
        //  1920: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myPassByReference:Z
        //  1923: goto            2089
        //  1926: aload_1        
        //  1927: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TYPEDEF_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1930: if_acmpne       1950
        //  1933: aload_0        
        //  1934: iconst_1       
        //  1935: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myIsTypedef:Z
        //  1938: aload_0        
        //  1939: iconst_0       
        //  1940: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myAssumeNonNullOn:Z
        //  1943: goto            2089
        //  1946: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1949: athrow         
        //  1950: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.EXPRESSIONS:Lcom/intellij/psi/tree/TokenSet;
        //  1953: aload_1        
        //  1954: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //  1957: ifeq            1986
        //  1960: aload_0        
        //  1961: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myIsInsideBrackets:Z
        //  1964: ifne            2089
        //  1967: goto            1974
        //  1970: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1973: athrow         
        //  1974: aload_0        
        //  1975: iconst_1       
        //  1976: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myWasNonArrayExpression:Z
        //  1979: goto            2089
        //  1982: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1985: athrow         
        //  1986: aload_1        
        //  1987: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRONG_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1990: if_acmpne       2007
        //  1993: aload_0        
        //  1994: getstatic       com/jetbrains/cidr/lang/types/ARCAttribute.STRONG:Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //  1997: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myARCAttribute:Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //  2000: goto            2089
        //  2003: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2006: athrow         
        //  2007: aload_1        
        //  2008: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WEAK_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2011: if_acmpne       2028
        //  2014: aload_0        
        //  2015: getstatic       com/jetbrains/cidr/lang/types/ARCAttribute.WEAK:Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //  2018: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myARCAttribute:Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //  2021: goto            2089
        //  2024: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2027: athrow         
        //  2028: aload_1        
        //  2029: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.UNSAFE_UNRETAINED_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2032: if_acmpne       2049
        //  2035: aload_0        
        //  2036: getstatic       com/jetbrains/cidr/lang/types/ARCAttribute.UNSAFE_UNRETAINED:Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //  2039: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myARCAttribute:Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //  2042: goto            2089
        //  2045: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2048: athrow         
        //  2049: aload_1        
        //  2050: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AUTORELEASING_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2053: if_acmpne       2070
        //  2056: aload_0        
        //  2057: getstatic       com/jetbrains/cidr/lang/types/ARCAttribute.AUTO_RELEASING:Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //  2060: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myARCAttribute:Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //  2063: goto            2089
        //  2066: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2069: athrow         
        //  2070: aload_1        
        //  2071: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KINDOF_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2074: if_acmpne       2089
        //  2077: aload_0        
        //  2078: iconst_1       
        //  2079: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myIsKindof:Z
        //  2082: goto            2089
        //  2085: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  2088: athrow         
        //  2089: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     14     18     Ljava/lang/IllegalArgumentException;
        //  23     48     48     52     Ljava/lang/IllegalArgumentException;
        //  52     77     77     81     Ljava/lang/IllegalArgumentException;
        //  81     106    106    110    Ljava/lang/IllegalArgumentException;
        //  110    135    135    139    Ljava/lang/IllegalArgumentException;
        //  139    169    169    173    Ljava/lang/IllegalArgumentException;
        //  173    193    193    197    Ljava/lang/IllegalArgumentException;
        //  197    217    217    221    Ljava/lang/IllegalArgumentException;
        //  221    261    264    268    Ljava/lang/IllegalArgumentException;
        //  228    273    276    280    Ljava/lang/IllegalArgumentException;
        //  288    308    308    312    Ljava/lang/IllegalArgumentException;
        //  312    332    332    336    Ljava/lang/IllegalArgumentException;
        //  336    350    353    357    Ljava/lang/IllegalArgumentException;
        //  343    364    367    371    Ljava/lang/IllegalArgumentException;
        //  357    383    386    390    Ljava/lang/IllegalArgumentException;
        //  371    400    400    404    Ljava/lang/IllegalArgumentException;
        //  412    433    433    437    Ljava/lang/IllegalArgumentException;
        //  437    455    458    462    Ljava/lang/IllegalArgumentException;
        //  462    470    470    474    Ljava/lang/IllegalArgumentException;
        //  474    488    491    495    Ljava/lang/IllegalArgumentException;
        //  481    501    504    508    Ljava/lang/IllegalArgumentException;
        //  508    526    529    533    Ljava/lang/IllegalArgumentException;
        //  533    553    556    560    Ljava/lang/IllegalArgumentException;
        //  543    570    570    574    Ljava/lang/IllegalArgumentException;
        //  574    588    591    595    Ljava/lang/IllegalArgumentException;
        //  581    605    605    609    Ljava/lang/IllegalArgumentException;
        //  609    625    628    632    Ljava/lang/IllegalArgumentException;
        //  632    646    649    653    Ljava/lang/IllegalArgumentException;
        //  639    659    662    666    Ljava/lang/IllegalArgumentException;
        //  666    684    687    691    Ljava/lang/IllegalArgumentException;
        //  691    711    714    718    Ljava/lang/IllegalArgumentException;
        //  701    728    728    732    Ljava/lang/IllegalArgumentException;
        //  732    752    752    756    Ljava/lang/IllegalArgumentException;
        //  756    773    776    780    Ljava/lang/IllegalArgumentException;
        //  763    787    790    794    Ljava/lang/IllegalArgumentException;
        //  780    804    804    808    Ljava/lang/IllegalArgumentException;
        //  808    828    828    832    Ljava/lang/IllegalArgumentException;
        //  832    852    852    856    Ljava/lang/IllegalArgumentException;
        //  856    876    876    880    Ljava/lang/IllegalArgumentException;
        //  880    899    899    903    Ljava/lang/IllegalArgumentException;
        //  903    942    945    949    Ljava/lang/IllegalArgumentException;
        //  913    970    973    977    Ljava/lang/IllegalArgumentException;
        //  949    1016   1016   1020   Ljava/lang/IllegalArgumentException;
        //  1020   1053   1053   1057   Ljava/lang/IllegalArgumentException;
        //  1057   1071   1074   1078   Ljava/lang/IllegalArgumentException;
        //  1064   1088   1091   1095   Ljava/lang/IllegalArgumentException;
        //  1078   1102   1105   1109   Ljava/lang/IllegalArgumentException;
        //  1095   1122   1122   1126   Ljava/lang/IllegalArgumentException;
        //  1126   1141   1144   1148   Ljava/lang/IllegalArgumentException;
        //  1133   1154   1154   1158   Ljava/lang/IllegalArgumentException;
        //  1170   1190   1190   1194   Ljava/lang/IllegalArgumentException;
        //  1194   1221   1224   1228   Ljava/lang/IllegalArgumentException;
        //  1201   1238   1238   1242   Ljava/lang/IllegalArgumentException;
        //  1242   1259   1262   1266   Ljava/lang/IllegalArgumentException;
        //  1249   1276   1279   1283   Ljava/lang/IllegalArgumentException;
        //  1266   1290   1293   1297   Ljava/lang/IllegalArgumentException;
        //  1283   1310   1310   1314   Ljava/lang/IllegalArgumentException;
        //  1314   1335   1335   1339   Ljava/lang/IllegalArgumentException;
        //  1339   1356   1356   1360   Ljava/lang/IllegalArgumentException;
        //  1360   1377   1377   1381   Ljava/lang/IllegalArgumentException;
        //  1381   1396   1399   1403   Ljava/lang/IllegalArgumentException;
        //  1388   1409   1409   1413   Ljava/lang/IllegalArgumentException;
        //  1422   1436   1439   1443   Ljava/lang/IllegalArgumentException;
        //  1429   1451   1454   1458   Ljava/lang/IllegalArgumentException;
        //  1443   1464   1464   1468   Ljava/lang/IllegalArgumentException;
        //  1477   1491   1491   1495   Ljava/lang/IllegalArgumentException;
        //  1504   1521   1524   1528   Ljava/lang/IllegalArgumentException;
        //  1511   1542   1545   1549   Ljava/lang/IllegalArgumentException;
        //  1557   1572   1572   1576   Ljava/lang/IllegalArgumentException;
        //  1576   1586   1586   1590   Ljava/lang/IllegalArgumentException;
        //  1590   1607   1607   1611   Ljava/lang/IllegalArgumentException;
        //  1611   1628   1628   1632   Ljava/lang/IllegalArgumentException;
        //  1632   1649   1649   1653   Ljava/lang/IllegalArgumentException;
        //  1653   1670   1670   1674   Ljava/lang/IllegalArgumentException;
        //  1674   1694   1694   1698   Ljava/lang/IllegalArgumentException;
        //  1698   1715   1715   1719   Ljava/lang/IllegalArgumentException;
        //  1719   1733   1736   1740   Ljava/lang/IllegalArgumentException;
        //  1726   1747   1750   1754   Ljava/lang/IllegalArgumentException;
        //  1740   1761   1764   1768   Ljava/lang/IllegalArgumentException;
        //  1754   1780   1780   1784   Ljava/lang/IllegalArgumentException;
        //  1784   1801   1804   1808   Ljava/lang/IllegalArgumentException;
        //  1794   1815   1818   1822   Ljava/lang/IllegalArgumentException;
        //  1808   1841   1841   1845   Ljava/lang/IllegalArgumentException;
        //  1845   1864   1867   1871   Ljava/lang/IllegalArgumentException;
        //  1852   1879   1879   1883   Ljava/lang/IllegalArgumentException;
        //  1883   1907   1907   1911   Ljava/lang/IllegalArgumentException;
        //  1926   1946   1946   1950   Ljava/lang/IllegalArgumentException;
        //  1950   1967   1970   1974   Ljava/lang/IllegalArgumentException;
        //  1960   1982   1982   1986   Ljava/lang/IllegalArgumentException;
        //  1986   2003   2003   2007   Ljava/lang/IllegalArgumentException;
        //  2007   2024   2024   2028   Ljava/lang/IllegalArgumentException;
        //  2028   2045   2045   2049   Ljava/lang/IllegalArgumentException;
        //  2049   2066   2066   2070   Ljava/lang/IllegalArgumentException;
        //  2070   2082   2085   2089   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0357:
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
    
    public void learn(final int myLastIntLiteral) {
        try {
            if (this.myIsInsideBrackets) {
                this.myLastIntLiteral = myLastIntLiteral;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    private void a(final OCIntType ocIntType, final OCIntType myCurrentType) {
        Label_0081: {
            Label_0061: {
                Label_0031: {
                    Label_0021: {
                        try {
                            if (this.myCurrentType == null) {
                                break Label_0021;
                            }
                            final OCTypeBuilder ocTypeBuilder = this;
                            final boolean b = ocTypeBuilder.myWasAuto;
                            if (b) {
                                break Label_0021;
                            }
                            break Label_0031;
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            final OCTypeBuilder ocTypeBuilder = this;
                            final boolean b = ocTypeBuilder.myWasAuto;
                            if (b) {
                                this.myCurrentType = ocIntType;
                                return;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                    try {
                        if (!(this.myCurrentType instanceof OCIntType)) {
                            break Label_0081;
                        }
                        final OCTypeBuilder ocTypeBuilder2 = this;
                        final OCType ocType = ocTypeBuilder2.myCurrentType;
                        final OCIntType ocIntType2 = (OCIntType)ocType;
                        final boolean b2 = ocIntType2.isSigned();
                        if (b2) {
                            break Label_0061;
                        }
                        break Label_0061;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    final OCTypeBuilder ocTypeBuilder2 = this;
                    final OCType ocType = ocTypeBuilder2.myCurrentType;
                    final OCIntType ocIntType2 = (OCIntType)ocType;
                    final boolean b2 = ocIntType2.isSigned();
                    if (b2) {
                        this.myCurrentType = ocIntType;
                        return;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            this.myCurrentType = myCurrentType;
            return;
        }
        this.a("Illegal combination of type specifiers");
    }
    
    public void updateArrayLength(final int n) {
        Label_0026: {
            try {
                if (this.myArrayLengths.length < 1) {
                    return;
                }
                final OCTypeBuilder ocTypeBuilder = this;
                final int[] array = ocTypeBuilder.myArrayLengths;
                final int n2 = 0;
                final int n3 = array[n2];
                final int n4 = -1;
                if (n3 == n4) {
                    break Label_0026;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCTypeBuilder ocTypeBuilder = this;
                final int[] array = ocTypeBuilder.myArrayLengths;
                final int n2 = 0;
                final int n3 = array[n2];
                final int n4 = -1;
                if (n3 == n4) {
                    this.myArrayLengths[0] = n;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    private void a(final String s) {
    }
    
    public OCType getResult() {
        return this.getResult(false);
    }
    
    public OCType getResult(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myName:Ljava/lang/String;
        //     4: ifnull          65
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myTypeArguments:Ljava/util/List;
        //    11: ifnonnull       36
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myNamespaceQualifier:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    25: aload_0        
        //    26: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myName:Ljava/lang/String;
        //    29: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.interned:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    32: astore_2       
        //    33: goto            56
        //    36: new             Lcom/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments;
        //    39: dup            
        //    40: aload_0        
        //    41: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myNamespaceQualifier:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myName:Ljava/lang/String;
        //    48: aload_0        
        //    49: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myTypeArguments:Ljava/util/List;
        //    52: invokespecial   com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/lang/String;Ljava/util/List;)V
        //    55: astore_2       
        //    56: aload_0        
        //    57: aload_0        
        //    58: aload_2        
        //    59: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeBuilder.createReferenceType:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;)Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //    62: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    65: aload_0        
        //    66: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myRefTokens:Ljava/util/List;
        //    69: astore_2       
        //    70: iload_1        
        //    71: ifne            154
        //    74: aload_0        
        //    75: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myRefTokens:Ljava/util/List;
        //    78: aload_0        
        //    79: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myFunctionRefTokens:Ljava/util/List;
        //    82: invokestatic    com/intellij/util/containers/ContainerUtil.concat:(Ljava/util/List;Ljava/util/List;)Ljava/util/List;
        //    85: astore_2       
        //    86: aload_0        
        //    87: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myFunctionRefTokens:Ljava/util/List;
        //    90: invokeinterface java/util/List.isEmpty:()Z
        //    95: ifne            154
        //    98: aload_0        
        //    99: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myFunctionRefTokens:Ljava/util/List;
        //   102: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONST_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   105: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   110: ifne            154
        //   113: goto            120
        //   116: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   119: athrow         
        //   120: aload_0        
        //   121: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myFunctionRefTokens:Ljava/util/List;
        //   124: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   127: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   132: ifne            154
        //   135: goto            142
        //   138: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   141: athrow         
        //   142: aload_0        
        //   143: iconst_0       
        //   144: putfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myConst:Z
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload_0        
        //   155: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myCurrentType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   158: astore_3       
        //   159: aload_3        
        //   160: ifnonnull       207
        //   163: aload_0        
        //   164: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myFileKind:Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   167: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isObjC:()Z
        //   172: ifeq            203
        //   175: goto            182
        //   178: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: aload_0        
        //   183: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myProject:Lcom/intellij/openapi/project/Project;
        //   186: invokestatic    com/jetbrains/cidr/lang/types/OCIdType.pointerToID:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   189: aload_0        
        //   190: invokespecial   com/jetbrains/cidr/lang/types/OCTypeBuilder.a:()Lcom/jetbrains/cidr/lang/types/OCNullability;
        //   193: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.cloneWithNullability:(Lcom/jetbrains/cidr/lang/types/OCNullability;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   196: goto            206
        //   199: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   206: astore_3       
        //   207: aload_0        
        //   208: aload_3        
        //   209: aload_2        
        //   210: aload_0        
        //   211: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myArrayLengths:[I
        //   214: invokespecial   com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;[I)Lcom/jetbrains/cidr/lang/types/OCType;
        //   217: astore_3       
        //   218: aload_0        
        //   219: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myVariadic:Z
        //   222: ifeq            234
        //   225: new             Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   228: dup            
        //   229: aload_3        
        //   230: invokespecial   com/jetbrains/cidr/lang/types/OCVariadicType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   233: astore_3       
        //   234: aload_3        
        //   235: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  86     113    116    120    Ljava/lang/IllegalArgumentException;
        //  98     135    138    142    Ljava/lang/IllegalArgumentException;
        //  120    147    150    154    Ljava/lang/IllegalArgumentException;
        //  159    175    178    182    Ljava/lang/IllegalArgumentException;
        //  163    199    199    203    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0120:
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
    
    protected OCReferenceType createReferenceType(final OCQualifiedName p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLocalContext:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //     4: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getLocalContext:()Lcom/intellij/psi/PsiElement;
        //     7: astore_2       
        //     8: aload_0        
        //     9: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myProtocolList:Ljava/util/ArrayList;
        //    12: astore_3       
        //    13: aload_3        
        //    14: ifnonnull       25
        //    17: new             Ljava/util/ArrayList;
        //    20: dup            
        //    21: invokespecial   java/util/ArrayList.<init>:()V
        //    24: astore_3       
        //    25: aload_2        
        //    26: ifnull          50
        //    29: new             Lcom/jetbrains/cidr/lang/types/OCReferenceTypeBuilder;
        //    32: dup            
        //    33: aload_1        
        //    34: aload_2        
        //    35: aload_0        
        //    36: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLocalContext:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //    39: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParentSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    42: invokespecial   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)V
        //    45: astore          4
        //    47: goto            177
        //    50: aload_0        
        //    51: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLocalContext:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //    54: ifnull          71
        //    57: aload_0        
        //    58: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLocalContext:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //    61: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.getParentSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    64: goto            72
        //    67: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: aconst_null    
        //    72: astore          5
        //    74: aload_0        
        //    75: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLocalContext:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //    78: ifnull          106
        //    81: aload_0        
        //    82: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLocalContext:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //    85: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.isBaseClause:()Z
        //    88: ifeq            106
        //    91: goto            98
        //    94: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    97: athrow         
        //    98: iconst_1       
        //    99: goto            107
        //   102: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: iconst_0       
        //   107: istore          6
        //   109: aload_0        
        //   110: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLocalContext:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   113: ifnull          158
        //   116: aload_0        
        //   117: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLocalContext:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   120: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.isInsideTemplateParams:()Z
        //   123: ifne            150
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload_0        
        //   134: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLocalContext:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   137: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.isTemplateValueParameter:()Z
        //   140: ifeq            158
        //   143: goto            150
        //   146: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: iconst_1       
        //   151: goto            159
        //   154: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   157: athrow         
        //   158: iconst_0       
        //   159: istore          7
        //   161: new             Lcom/jetbrains/cidr/lang/types/OCReferenceTypeBuilder;
        //   164: dup            
        //   165: aload_1        
        //   166: aload           5
        //   168: iload           6
        //   170: iload           7
        //   172: invokespecial   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;ZZ)V
        //   175: astore          4
        //   177: aload           4
        //   179: aload_3        
        //   180: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.setProtocolNames:(Ljava/util/List;)Lcom/jetbrains/cidr/lang/types/OCReferenceTypeBuilder;
        //   183: pop            
        //   184: aload           4
        //   186: aload_0        
        //   187: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myARCAttribute:Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //   190: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.setARCAttribute:(Lcom/jetbrains/cidr/lang/types/ARCAttribute;)Lcom/jetbrains/cidr/lang/types/OCReferenceTypeBuilder;
        //   193: pop            
        //   194: aload           4
        //   196: aload_0        
        //   197: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myIsKindof:Z
        //   200: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.setIsKindof:(Z)V
        //   203: aload_0        
        //   204: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myNullability:Lcom/jetbrains/cidr/lang/types/OCNullability;
        //   207: ifnull          227
        //   210: aload           4
        //   212: aload_0        
        //   213: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myNullability:Lcom/jetbrains/cidr/lang/types/OCNullability;
        //   216: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.setNullability:(Lcom/jetbrains/cidr/lang/types/OCNullability;)Lcom/jetbrains/cidr/lang/types/OCReferenceTypeBuilder;
        //   219: pop            
        //   220: goto            250
        //   223: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   226: athrow         
        //   227: aload_0        
        //   228: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myAssumeNonNullOn:Z
        //   231: ifeq            250
        //   234: aload           4
        //   236: getstatic       com/jetbrains/cidr/lang/types/OCNullability.NONNULL:Lcom/jetbrains/cidr/lang/types/OCNullability;
        //   239: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.setNullability:(Lcom/jetbrains/cidr/lang/types/OCNullability;)Lcom/jetbrains/cidr/lang/types/OCReferenceTypeBuilder;
        //   242: pop            
        //   243: goto            250
        //   246: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   249: athrow         
        //   250: aload           4
        //   252: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceTypeBuilder.build:()Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   255: astore          5
        //   257: aload_2        
        //   258: ifnonnull       280
        //   261: aload_0        
        //   262: getfield        com/jetbrains/cidr/lang/types/OCTypeBuilder.myLocalContext:Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext;
        //   265: aload           5
        //   267: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getReference:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   270: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext.addSymbolReference:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;)V
        //   273: goto            280
        //   276: invokestatic    com/jetbrains/cidr/lang/types/OCTypeBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   279: athrow         
        //   280: aload           5
        //   282: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  50     67     67     71     Ljava/lang/IllegalArgumentException;
        //  74     91     94     98     Ljava/lang/IllegalArgumentException;
        //  81     102    102    106    Ljava/lang/IllegalArgumentException;
        //  109    126    129    133    Ljava/lang/IllegalArgumentException;
        //  116    143    146    150    Ljava/lang/IllegalArgumentException;
        //  133    154    154    158    Ljava/lang/IllegalArgumentException;
        //  177    223    223    227    Ljava/lang/IllegalArgumentException;
        //  227    243    246    250    Ljava/lang/IllegalArgumentException;
        //  257    273    276    280    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0133:
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
    
    public OCType createFunction(final OCType ocType, final List<OCType> list, final List<String> list2, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        if (list != null) {
            final OCFunctionType ocFunctionType = new OCFunctionType(ocType, list, list2, b2, b3, b4, b5);
            try {
                if (b) {
                    return this.a(ocFunctionType, this.myFunctionRefTokens, null);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return ocFunctionType;
        }
        return ocType;
    }
    
    public void learnTypeArguments(final List<OCTypeArgument> myTypeArguments) {
        this.myTypeArguments = myTypeArguments;
    }
    
    private OCType a(OCType ocType, final List<IElementType> list, @Nullable final int[] array) {
        int i = 0;
        int n = 0;
        Label_0019: {
            try {
                if (array != null) {
                    n = array.length - 1;
                    break Label_0019;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            n = -1;
        }
        int n2 = n;
        while (i < list.size()) {
            final IElementType elementType = list.get(i++);
            Label_0291: {
                if (elementType == OCTokenTypes.CONST_KEYWORD) {
                    ocType = ocType.cloneWithCVQualifiers(CVQualifiers.get(true, ocType.isVolatile()), this.myProject);
                }
                else if (elementType == OCTokenTypes.VOLATILE_KEYWORD) {
                    ocType = ocType.cloneWithCVQualifiers(CVQualifiers.get(ocType.isConst(), true), this.myProject);
                }
                else {
                    Label_0156: {
                        Label_0143: {
                            try {
                                if (elementType != OCTokenTypes.LBRACKET) {
                                    break Label_0156;
                                }
                                if (n2 < 0) {
                                    break Label_0143;
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                            ocType = OCArrayType.to(ocType, array[n2--], this.myARCAttribute);
                            break Label_0291;
                        }
                        ocType = OCArrayType.to(ocType, -1, this.myARCAttribute);
                        break Label_0291;
                    }
                    if (elementType == OCTokenTypes.XOR) {
                        ocType = OCBlockPointerType.blockPtr(ocType, this.myARCAttribute, this.a(), false, false);
                    }
                    else if (elementType == OCTokenTypes.AND) {
                        ocType = OCCppReferenceType.to(ocType);
                    }
                    else if (elementType == OCTokenTypes.ANDAND) {
                        ocType = OCCppReferenceType.rvalue(ocType);
                    }
                    else {
                        Label_0270: {
                            OCType ocType2 = null;
                            ARCAttribute arcAttribute = null;
                            OCReferenceType referenceType = null;
                            Label_0257: {
                                Label_0241: {
                                    try {
                                        if (elementType != OCTokenTypes.MUL) {
                                            break Label_0270;
                                        }
                                        ocType2 = ocType;
                                        final OCTypeBuilder ocTypeBuilder = this;
                                        arcAttribute = ocTypeBuilder.myARCAttribute;
                                        final OCTypeBuilder ocTypeBuilder2 = this;
                                        final OCQualifiedName ocQualifiedName = ocTypeBuilder2.myPointerQualifier;
                                        if (ocQualifiedName != null) {
                                            break Label_0241;
                                        }
                                        break Label_0241;
                                    }
                                    catch (IllegalArgumentException ex3) {
                                        throw a(ex3);
                                    }
                                    try {
                                        ocType2 = ocType;
                                        final OCTypeBuilder ocTypeBuilder = this;
                                        arcAttribute = ocTypeBuilder.myARCAttribute;
                                        final OCTypeBuilder ocTypeBuilder2 = this;
                                        final OCQualifiedName ocQualifiedName = ocTypeBuilder2.myPointerQualifier;
                                        if (ocQualifiedName != null) {
                                            referenceType = this.createReferenceType(this.myPointerQualifier);
                                            break Label_0257;
                                        }
                                    }
                                    catch (IllegalArgumentException ex4) {
                                        throw a(ex4);
                                    }
                                }
                                referenceType = null;
                            }
                            ocType = OCPointerType.to(ocType2, arcAttribute, referenceType, this.a(), false, false);
                            break Label_0291;
                        }
                        if (OCTokenTypes.NULLABILITY_KEYWORDS.contains(elementType)) {
                            ocType = ocType.cloneWithNullability(OCNullability.parseFrom(elementType));
                        }
                    }
                }
            }
            this.myARCAttribute = null;
        }
        return ocType;
    }
    
    public OCTypeBuilder copy() {
        final OCTypeBuilder ocTypeBuilder = new OCTypeBuilder(this.myFileKind, this.myProject, this.myLocalContext);
        ocTypeBuilder.myCurrentType = this.myCurrentType;
        ocTypeBuilder.myName = this.myName;
        ocTypeBuilder.myProtocolList = this.myProtocolList;
        ocTypeBuilder.myRefTokens = new ArrayList<IElementType>(this.myRefTokens);
        ocTypeBuilder.myIsTypedef = this.myIsTypedef;
        ocTypeBuilder.myLocalContext = this.myLocalContext;
        ocTypeBuilder.myPointerQualifier = this.myPointerQualifier;
        ocTypeBuilder.myConst = this.myConst;
        ocTypeBuilder.mySign = this.mySign;
        ocTypeBuilder.myUnsign = this.myUnsign;
        ocTypeBuilder.myWasConst = this.myWasConst;
        ocTypeBuilder.myWasAuto = this.myWasAuto;
        ocTypeBuilder.myWasComplex = this.myWasComplex;
        ocTypeBuilder.myArrayLengths = this.myArrayLengths;
        ocTypeBuilder.myNamespaceQualifier = this.myNamespaceQualifier;
        ocTypeBuilder.myTypeArguments = this.myTypeArguments;
        ocTypeBuilder.myARCAttribute = this.myARCAttribute;
        ocTypeBuilder.myIsKindof = this.myIsKindof;
        ocTypeBuilder.myNullability = this.myNullability;
        ocTypeBuilder.myAssumeNonNullOn = this.myAssumeNonNullOn;
        return ocTypeBuilder;
    }
    
    public boolean isTypedef() {
        return this.myIsTypedef;
    }
    
    public boolean isPassByReference() {
        return this.myPassByReference;
    }
    
    public void learnBaseType(final OCType myCurrentType) {
        this.myCurrentType = myCurrentType;
    }
    
    public int[] getArrayLengths() {
        return this.myArrayLengths;
    }
    
    public boolean isConst() {
        return this.myConst;
    }
    
    public boolean wasAuto() {
        return this.myWasAuto;
    }
    
    public boolean eraseWasConst() {
        final boolean myWasConst = this.myWasConst;
        this.myWasConst = false;
        return myWasConst;
    }
    
    public boolean wasNonArrayExpression() {
        return this.myWasNonArrayExpression;
    }
    
    public boolean isInsideParentheses() {
        try {
            if (this.myParLevel > 0) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Nullable
    private OCNullability a() {
        try {
            if (this.myNullability != null) {
                return this.myNullability;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myAssumeNonNullOn) {
                return OCNullability.NONNULL;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
