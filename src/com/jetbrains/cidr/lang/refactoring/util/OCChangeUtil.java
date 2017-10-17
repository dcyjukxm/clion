// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.util;

import java.util.Map;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.intellij.openapi.util.TextRange;
import java.util.List;
import com.jetbrains.cidr.lang.generate.OCCaretLocation;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.util.Collection;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.lang.injection.InjectedLanguageManager;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCStatement;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Ref;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;

public class OCChangeUtil
{
    public static <T extends PsiElement> T addBefore(final PsiElement psiElement, final T t, @Nullable PsiElement a) {
        a = a(psiElement, t, a);
        return (T)addHandlingMacros(psiElement, t, a, true);
    }
    
    public static <T extends PsiElement> T addAfter(final PsiElement psiElement, final T t, @Nullable PsiElement a) {
        a = a(psiElement, t, a);
        IElementType elementType = OCElementUtil.getElementType(a);
        PsiElement psiElement2 = null;
        Label_0064: {
            Label_0040: {
                try {
                    if (a == null || elementType != OCElementTypes.OBJC_KEYWORD) {
                        break Label_0040;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                elementType = OCElementUtil.getObjCKeywordElementType(a.getNode());
                try {
                    psiElement2 = a;
                    if (elementType == OCTokenTypes.END_KEYWORD) {
                        break Label_0064;
                    }
                    final OCElementType ocElementType = (OCElementType)elementType;
                    final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.RBRACE;
                    if (ocElementType == ocPunctuatorElementType) {
                        break Label_0064;
                    }
                    break Label_0064;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            try {
                final OCElementType ocElementType = (OCElementType)elementType;
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.RBRACE;
                if (ocElementType == ocPunctuatorElementType) {
                    final boolean b = true;
                    return (T)addHandlingMacros(psiElement, t, psiElement2, b);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final boolean b = false;
        return (T)addHandlingMacros(psiElement, t, psiElement2, b);
    }
    
    public static <T extends PsiElement> T add(final PsiElement psiElement, final T t) {
        return addBefore(psiElement, t, null);
    }
    
    @Nullable
    public static PsiElement getRealAnchorForInsertion(final PsiElement psiElement, final PsiElement psiElement2) {
        return a(psiElement, null, psiElement2);
    }
    
    @Nullable
    private static PsiElement a(@Nullable final PsiElement p0, @Nullable final PsiElement p1, @Nullable final PsiElement p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       10
        //     4: aconst_null    
        //     5: areturn        
        //     6: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //     9: athrow         
        //    10: aload_2        
        //    11: ifnull          41
        //    14: aload_2        
        //    15: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    20: aload_0        
        //    21: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    26: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    29: ifne            41
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: aconst_null    
        //    40: astore_2       
        //    41: aload_1        
        //    42: ifnull          66
        //    45: aload_1        
        //    46: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    51: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    54: ifne            66
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: aconst_null    
        //    65: astore_1       
        //    66: aload_1        
        //    67: ifnull          185
        //    70: aload_0        
        //    71: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    74: ifne            98
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: aload_0        
        //    85: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    88: ifeq            185
        //    91: goto            98
        //    94: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    97: athrow         
        //    98: aload_1        
        //    99: invokestatic    com/jetbrains/cidr/lang/util/OCDeclarationKind.getDeclarationKind:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/util/OCDeclarationKind;
        //   102: astore_3       
        //   103: aload_3        
        //   104: ifnull          182
        //   107: aload_3        
        //   108: aload_0        
        //   109: invokevirtual   com/jetbrains/cidr/lang/util/OCDeclarationKind.getChildrenStartOffset:(Lcom/intellij/psi/PsiElement;)I
        //   112: istore          4
        //   114: aload_3        
        //   115: aload_0        
        //   116: invokevirtual   com/jetbrains/cidr/lang/util/OCDeclarationKind.getChildrenEndOffset:(Lcom/intellij/psi/PsiElement;)I
        //   119: istore          5
        //   121: iload           4
        //   123: iconst_m1      
        //   124: if_icmpeq       182
        //   127: iload           5
        //   129: iconst_m1      
        //   130: if_icmpeq       182
        //   133: goto            140
        //   136: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   139: athrow         
        //   140: aload_2        
        //   141: ifnull          169
        //   144: goto            151
        //   147: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   150: athrow         
        //   151: aload_2        
        //   152: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   157: iload           5
        //   159: if_icmplt       182
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aload_0        
        //   170: iload           5
        //   172: iconst_1       
        //   173: iadd           
        //   174: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Lcom/intellij/psi/PsiElement;I)Lcom/intellij/psi/PsiElement;
        //   177: areturn        
        //   178: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: goto            347
        //   185: aload_0        
        //   186: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   189: ifeq            347
        //   192: aload_0        
        //   193: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   196: invokeinterface com/jetbrains/cidr/lang/psi/OCStruct.getName:()Ljava/lang/String;
        //   201: astore_3       
        //   202: aload_1        
        //   203: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   206: ifeq            347
        //   209: aload_1        
        //   210: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   213: astore          4
        //   215: aload           4
        //   217: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getName:()Ljava/lang/String;
        //   222: aload_3        
        //   223: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //   226: ifeq            347
        //   229: aload_0        
        //   230: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   233: invokeinterface com/jetbrains/cidr/lang/psi/OCStruct.getMembers:()Ljava/util/List;
        //   238: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   243: astore          5
        //   245: aload           5
        //   247: invokeinterface java/util/Iterator.hasNext:()Z
        //   252: ifeq            347
        //   255: aload           5
        //   257: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   262: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   265: astore          6
        //   267: aload           4
        //   269: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getParameters:()Ljava/util/List;
        //   274: astore          7
        //   276: aload           7
        //   278: ifnull          298
        //   281: aload           7
        //   283: invokeinterface java/util/List.size:()I
        //   288: ifne            305
        //   291: goto            298
        //   294: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   297: athrow         
        //   298: aload           6
        //   300: areturn        
        //   301: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   304: athrow         
        //   305: aload           6
        //   307: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   310: ifne            320
        //   313: aload           6
        //   315: areturn        
        //   316: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   319: athrow         
        //   320: aload           6
        //   322: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   325: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getName:()Ljava/lang/String;
        //   330: aload_3        
        //   331: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //   334: ifne            344
        //   337: aload           6
        //   339: areturn        
        //   340: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   343: athrow         
        //   344: goto            245
        //   347: aload_2        
        //   348: ifnull          407
        //   351: aload_2        
        //   352: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   357: aload_2        
        //   358: invokeinterface com/intellij/psi/PsiElement.getTextLength:()I
        //   363: ifeq            381
        //   366: goto            373
        //   369: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   372: athrow         
        //   373: iconst_1       
        //   374: goto            382
        //   377: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   380: athrow         
        //   381: iconst_0       
        //   382: iadd           
        //   383: istore_3       
        //   384: iload_3        
        //   385: aload_0        
        //   386: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   391: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   394: if_icmpge       407
        //   397: aload_0        
        //   398: iload_3        
        //   399: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Lcom/intellij/psi/PsiElement;I)Lcom/intellij/psi/PsiElement;
        //   402: areturn        
        //   403: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   406: athrow         
        //   407: aload_0        
        //   408: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //   411: ifeq            472
        //   414: aload_0        
        //   415: checkcast       Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //   418: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterList.getParameterDeclarations:()Ljava/util/List;
        //   423: invokeinterface java/util/List.isEmpty:()Z
        //   428: ifne            465
        //   431: goto            438
        //   434: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   437: athrow         
        //   438: aload_0        
        //   439: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   442: aload_0        
        //   443: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   446: aload_0        
        //   447: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //   452: invokeinterface com/intellij/psi/PsiElement.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   457: pop            
        //   458: goto            465
        //   461: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   464: athrow         
        //   465: aload_0        
        //   466: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //   471: areturn        
        //   472: aload_0        
        //   473: instanceof      Lcom/jetbrains/cidr/lang/psi/OCPropertyAttributesList;
        //   476: ifeq            537
        //   479: aload_0        
        //   480: checkcast       Lcom/jetbrains/cidr/lang/psi/OCPropertyAttributesList;
        //   483: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttributesList.getAttributes:()Ljava/util/List;
        //   488: invokeinterface java/util/List.isEmpty:()Z
        //   493: ifne            530
        //   496: goto            503
        //   499: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   502: athrow         
        //   503: aload_0        
        //   504: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   507: aload_0        
        //   508: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   511: aload_0        
        //   512: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //   517: invokeinterface com/intellij/psi/PsiElement.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   522: pop            
        //   523: goto            530
        //   526: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   529: athrow         
        //   530: aload_0        
        //   531: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //   536: areturn        
        //   537: aload_0        
        //   538: instanceof      Lcom/jetbrains/cidr/lang/psi/OCEnum;
        //   541: ifeq            602
        //   544: aload_0        
        //   545: checkcast       Lcom/jetbrains/cidr/lang/psi/OCEnum;
        //   548: invokeinterface com/jetbrains/cidr/lang/psi/OCEnum.getFields:()Ljava/util/List;
        //   553: invokeinterface java/util/List.isEmpty:()Z
        //   558: ifne            595
        //   561: goto            568
        //   564: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   567: athrow         
        //   568: aload_0        
        //   569: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   572: aload_0        
        //   573: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   576: aload_0        
        //   577: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //   582: invokeinterface com/intellij/psi/PsiElement.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   587: pop            
        //   588: goto            595
        //   591: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   594: athrow         
        //   595: aload_0        
        //   596: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //   601: areturn        
        //   602: aload_0        
        //   603: instanceof      Lcom/jetbrains/cidr/lang/psi/OCConstructorInitializationList;
        //   606: ifeq            656
        //   609: aload_0        
        //   610: checkcast       Lcom/jetbrains/cidr/lang/psi/OCConstructorInitializationList;
        //   613: invokeinterface com/jetbrains/cidr/lang/psi/OCConstructorInitializationList.getInitializers:()Ljava/util/List;
        //   618: invokeinterface java/util/List.size:()I
        //   623: ifeq            654
        //   626: goto            633
        //   629: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   632: athrow         
        //   633: aload_0        
        //   634: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   637: aload_1        
        //   638: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   641: invokeinterface com/intellij/psi/PsiElement.add:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   646: pop            
        //   647: goto            654
        //   650: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   653: athrow         
        //   654: aconst_null    
        //   655: areturn        
        //   656: aload_0        
        //   657: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   660: ifne            691
        //   663: aload_0        
        //   664: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   667: ifne            691
        //   670: goto            677
        //   673: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   676: athrow         
        //   677: aload_0        
        //   678: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
        //   681: ifeq            703
        //   684: goto            691
        //   687: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   690: athrow         
        //   691: aload_0        
        //   692: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   695: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/psi/PsiElement;
        //   698: areturn        
        //   699: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   702: athrow         
        //   703: aload_0        
        //   704: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProtocolList;
        //   707: ifeq            813
        //   710: aload_0        
        //   711: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   714: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/psi/PsiElement;
        //   717: astore_3       
        //   718: aload_3        
        //   719: ifnonnull       762
        //   722: aload_0        
        //   723: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   726: aload_0        
        //   727: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   730: invokeinterface com/intellij/psi/PsiElement.add:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   735: astore          4
        //   737: aload           4
        //   739: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   744: iconst_1       
        //   745: invokestatic    com/intellij/psi/impl/source/codeStyle/CodeEditUtil.markToReformatBefore:(Lcom/intellij/lang/ASTNode;Z)V
        //   748: aload_0        
        //   749: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.GT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   752: aload_0        
        //   753: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   756: invokeinterface com/intellij/psi/PsiElement.add:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   761: areturn        
        //   762: aload_0        
        //   763: checkcast       Lcom/jetbrains/cidr/lang/psi/OCProtocolList;
        //   766: invokeinterface com/jetbrains/cidr/lang/psi/OCProtocolList.getProtocols:()Ljava/util/List;
        //   771: invokeinterface java/util/List.isEmpty:()Z
        //   776: ifne            806
        //   779: aload_0        
        //   780: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   783: aload_0        
        //   784: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   787: aload_0        
        //   788: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //   793: invokeinterface com/intellij/psi/PsiElement.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   798: pop            
        //   799: goto            806
        //   802: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   805: athrow         
        //   806: aload_0        
        //   807: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //   812: areturn        
        //   813: aload_0        
        //   814: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppBaseClauseList;
        //   817: ifeq            881
        //   820: aload_0        
        //   821: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCppBaseClauseList;
        //   824: invokeinterface com/jetbrains/cidr/lang/psi/OCCppBaseClauseList.getBaseClauses:()Ljava/util/List;
        //   829: invokeinterface java/util/List.isEmpty:()Z
        //   834: ifeq            865
        //   837: goto            844
        //   840: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   843: athrow         
        //   844: aload_0        
        //   845: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   848: aload_0        
        //   849: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   852: invokeinterface com/intellij/psi/PsiElement.add:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   857: pop            
        //   858: goto            879
        //   861: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   864: athrow         
        //   865: aload_0        
        //   866: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   869: aload_0        
        //   870: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   873: invokeinterface com/intellij/psi/PsiElement.add:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   878: pop            
        //   879: aconst_null    
        //   880: areturn        
        //   881: aload_0        
        //   882: instanceof      Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;
        //   885: ifeq            1014
        //   888: aload_0        
        //   889: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   892: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/psi/PsiElement;
        //   895: astore_3       
        //   896: aload_3        
        //   897: ifnonnull       1012
        //   900: aload_1        
        //   901: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.instanceVariableList:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;
        //   904: invokeinterface com/jetbrains/cidr/lang/psi/OCInstanceVariablesList.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   909: astore          4
        //   911: aconst_null    
        //   912: astore          5
        //   914: aload           4
        //   916: ifnull          1009
        //   919: aload           4
        //   921: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   926: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   931: astore          6
        //   933: aload_0        
        //   934: aload           4
        //   936: invokeinterface com/intellij/psi/PsiElement.add:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   941: astore          7
        //   943: aload           6
        //   945: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   948: if_acmpne       955
        //   951: aload           7
        //   953: astore          5
        //   955: aload           7
        //   957: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   962: aload           6
        //   964: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   967: if_acmpeq       985
        //   970: aload           6
        //   972: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   975: if_acmpne       993
        //   978: goto            985
        //   981: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   984: athrow         
        //   985: iconst_1       
        //   986: goto            994
        //   989: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   992: athrow         
        //   993: iconst_0       
        //   994: invokestatic    com/intellij/psi/impl/source/codeStyle/CodeEditUtil.markToReformatBefore:(Lcom/intellij/lang/ASTNode;Z)V
        //   997: aload           4
        //   999: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //  1004: astore          4
        //  1006: goto            914
        //  1009: aload           5
        //  1011: areturn        
        //  1012: aload_3        
        //  1013: areturn        
        //  1014: aload_0        
        //  1015: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //  1018: ifeq            1115
        //  1021: aload_1        
        //  1022: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //  1025: ifeq            1115
        //  1028: goto            1035
        //  1031: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1034: athrow         
        //  1035: aload_0        
        //  1036: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //  1039: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getParameters:()Ljava/util/List;
        //  1044: astore_3       
        //  1045: aload_3        
        //  1046: invokeinterface java/util/List.isEmpty:()Z
        //  1051: ifne            1115
        //  1054: aload_3        
        //  1055: aload_3        
        //  1056: invokeinterface java/util/List.size:()I
        //  1061: iconst_1       
        //  1062: isub           
        //  1063: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //  1068: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //  1071: astore          4
        //  1073: aload           4
        //  1075: invokeinterface com/jetbrains/cidr/lang/psi/OCMethodSelectorPart.getParameter:()Lcom/intellij/psi/PsiElement;
        //  1080: ifnonnull       1095
        //  1083: aload           4
        //  1085: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //  1088: goto            1108
        //  1091: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1094: athrow         
        //  1095: aload_0        
        //  1096: aload_1        
        //  1097: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.spaceFromText:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //  1100: aload           4
        //  1102: invokeinterface com/intellij/psi/PsiElement.addAfter:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //  1107: pop            
        //  1108: aload_0        
        //  1109: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //  1114: areturn        
        //  1115: aload_2        
        //  1116: ifnull          1132
        //  1119: aload_0        
        //  1120: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //  1125: goto            1133
        //  1128: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1131: athrow         
        //  1132: aconst_null    
        //  1133: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      6      6      10     Ljava/lang/IllegalArgumentException;
        //  10     32     35     39     Ljava/lang/IllegalArgumentException;
        //  41     57     60     64     Ljava/lang/IllegalArgumentException;
        //  66     77     80     84     Ljava/lang/IllegalArgumentException;
        //  70     91     94     98     Ljava/lang/IllegalArgumentException;
        //  121    133    136    140    Ljava/lang/IllegalArgumentException;
        //  127    144    147    151    Ljava/lang/IllegalArgumentException;
        //  140    162    165    169    Ljava/lang/IllegalArgumentException;
        //  151    178    178    182    Ljava/lang/IllegalArgumentException;
        //  276    291    294    298    Ljava/lang/IllegalArgumentException;
        //  281    301    301    305    Ljava/lang/IllegalArgumentException;
        //  305    316    316    320    Ljava/lang/IllegalArgumentException;
        //  320    340    340    344    Ljava/lang/IllegalArgumentException;
        //  347    366    369    373    Ljava/lang/IllegalArgumentException;
        //  351    377    377    381    Ljava/lang/IllegalArgumentException;
        //  384    403    403    407    Ljava/lang/IllegalArgumentException;
        //  407    431    434    438    Ljava/lang/IllegalArgumentException;
        //  414    458    461    465    Ljava/lang/IllegalArgumentException;
        //  472    496    499    503    Ljava/lang/IllegalArgumentException;
        //  479    523    526    530    Ljava/lang/IllegalArgumentException;
        //  537    561    564    568    Ljava/lang/IllegalArgumentException;
        //  544    588    591    595    Ljava/lang/IllegalArgumentException;
        //  602    626    629    633    Ljava/lang/IllegalArgumentException;
        //  609    647    650    654    Ljava/lang/IllegalArgumentException;
        //  656    670    673    677    Ljava/lang/IllegalArgumentException;
        //  663    684    687    691    Ljava/lang/IllegalArgumentException;
        //  677    699    699    703    Ljava/lang/IllegalArgumentException;
        //  762    799    802    806    Ljava/lang/IllegalArgumentException;
        //  813    837    840    844    Ljava/lang/IllegalArgumentException;
        //  820    861    861    865    Ljava/lang/IllegalArgumentException;
        //  955    978    981    985    Ljava/lang/IllegalArgumentException;
        //  970    989    989    993    Ljava/lang/IllegalArgumentException;
        //  1014   1028   1031   1035   Ljava/lang/IllegalArgumentException;
        //  1073   1091   1091   1095   Ljava/lang/IllegalArgumentException;
        //  1115   1128   1128   1132   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0140:
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
    
    public static void delete(@Nullable final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          20
        //     4: aload_0        
        //     5: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //    10: ifne            25
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    19: athrow         
        //    20: return         
        //    21: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    24: athrow         
        //    25: aload_0        
        //    26: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    31: astore_1       
        //    32: aload_1        
        //    33: ifnonnull       41
        //    36: return         
        //    37: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: new             Lcom/intellij/openapi/util/Ref;
        //    44: dup            
        //    45: getstatic       com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil$ParentCleanup.LEAVE:Lcom/jetbrains/cidr/lang/refactoring/util/OCChangeUtil$ParentCleanup;
        //    48: invokespecial   com/intellij/openapi/util/Ref.<init>:(Ljava/lang/Object;)V
        //    51: astore_2       
        //    52: aload_0        
        //    53: aload_1        
        //    54: aload_2        
        //    55: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/util/Ref;)Z
        //    58: ifeq            80
        //    61: aload_1        
        //    62: aload_0        
        //    63: aload_2        
        //    64: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //    67: checkcast       Lcom/jetbrains/cidr/lang/refactoring/util/OCChangeUtil$ParentCleanup;
        //    70: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/refactoring/util/OCChangeUtil$ParentCleanup;)V
        //    73: goto            582
        //    76: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    79: athrow         
        //    80: aload_0        
        //    81: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    84: ifeq            136
        //    87: aload_1        
        //    88: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    91: ifeq            136
        //    94: goto            101
        //    97: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   100: athrow         
        //   101: aload_1        
        //   102: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   107: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   110: ifeq            136
        //   113: goto            120
        //   116: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   119: athrow         
        //   120: aload_1        
        //   121: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   126: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   129: goto            582
        //   132: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   135: athrow         
        //   136: aload_1        
        //   137: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   140: ifeq            158
        //   143: aload_0        
        //   144: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Lcom/intellij/psi/PsiElement;)V
        //   147: aload_0        
        //   148: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.b:(Lcom/intellij/psi/PsiElement;)V
        //   151: goto            582
        //   154: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   157: athrow         
        //   158: aload_1        
        //   159: instanceof      Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;
        //   162: ifeq            208
        //   165: aload_0        
        //   166: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Lcom/intellij/psi/PsiElement;)V
        //   169: aload_0        
        //   170: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.b:(Lcom/intellij/psi/PsiElement;)V
        //   173: aload_1        
        //   174: checkcast       Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;
        //   177: invokeinterface com/jetbrains/cidr/lang/psi/OCInstanceVariablesList.getDeclarations:()Ljava/util/List;
        //   182: invokeinterface java/util/List.isEmpty:()Z
        //   187: ifeq            582
        //   190: goto            197
        //   193: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: aload_1        
        //   198: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.clear:(Lcom/intellij/psi/PsiElement;)V
        //   201: goto            582
        //   204: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: aload_1        
        //   209: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   212: ifeq            254
        //   215: aload_0        
        //   216: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   219: ifeq            254
        //   222: goto            229
        //   225: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   228: athrow         
        //   229: aload_0        
        //   230: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.b:(Lcom/intellij/psi/PsiElement;)V
        //   233: aload_1        
        //   234: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   237: aload_1        
        //   238: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   241: invokeinterface com/intellij/psi/PsiElement.add:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   246: pop            
        //   247: goto            582
        //   250: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   253: athrow         
        //   254: aload_1        
        //   255: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   258: ifeq            311
        //   261: aload_1        
        //   262: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   265: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getInstanceVariableRef:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   270: ifnonnull       311
        //   273: goto            280
        //   276: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   279: athrow         
        //   280: aload_1        
        //   281: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   284: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getPropertyRef:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   289: aload_0        
        //   290: if_acmpne       311
        //   293: goto            300
        //   296: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   299: athrow         
        //   300: aload_1        
        //   301: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   304: goto            582
        //   307: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   310: athrow         
        //   311: aload_1        
        //   312: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   315: ifeq            371
        //   318: aload_1        
        //   319: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   322: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getInstanceVariableRef:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   327: aload_0        
        //   328: if_acmpne       371
        //   331: goto            338
        //   334: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   337: athrow         
        //   338: aload_1        
        //   339: aload_1        
        //   340: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   343: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getPropertyRef:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   348: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   353: aload_1        
        //   354: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //   359: invokeinterface com/intellij/psi/PsiElement.deleteChildRange:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)V
        //   364: goto            582
        //   367: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   370: athrow         
        //   371: aload_1        
        //   372: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   375: ifeq            488
        //   378: aload_1        
        //   379: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   382: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   387: aload_0        
        //   388: if_acmpne       488
        //   391: goto            398
        //   394: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   397: athrow         
        //   398: aload_0        
        //   399: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   404: invokeinterface com/intellij/lang/ASTNode.getTreePrev:()Lcom/intellij/lang/ASTNode;
        //   409: iconst_2       
        //   410: anewarray       Lcom/intellij/psi/tree/TokenSet;
        //   413: dup            
        //   414: iconst_0       
        //   415: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   418: aastore        
        //   419: dup            
        //   420: iconst_1       
        //   421: iconst_1       
        //   422: anewarray       Lcom/intellij/psi/tree/IElementType;
        //   425: dup            
        //   426: iconst_0       
        //   427: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   430: aastore        
        //   431: invokestatic    com/intellij/psi/tree/TokenSet.create:([Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/psi/tree/TokenSet;
        //   434: aastore        
        //   435: invokestatic    com/intellij/psi/tree/TokenSet.orSet:([Lcom/intellij/psi/tree/TokenSet;)Lcom/intellij/psi/tree/TokenSet;
        //   438: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.skipElementsBack:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/TokenSet;)Lcom/intellij/lang/ASTNode;
        //   441: astore_3       
        //   442: aload_3        
        //   443: ifnull          481
        //   446: aload_3        
        //   447: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   452: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   455: if_acmpne       481
        //   458: goto            465
        //   461: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   464: athrow         
        //   465: aload_3        
        //   466: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   471: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.b:(Lcom/intellij/psi/PsiElement;)V
        //   474: goto            481
        //   477: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   480: athrow         
        //   481: aload_0        
        //   482: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.b:(Lcom/intellij/psi/PsiElement;)V
        //   485: goto            582
        //   488: aload_0        
        //   489: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTemplateParameterList;
        //   492: ifeq            582
        //   495: aload_0        
        //   496: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   501: invokeinterface com/intellij/lang/ASTNode.getTreePrev:()Lcom/intellij/lang/ASTNode;
        //   506: iconst_2       
        //   507: anewarray       Lcom/intellij/psi/tree/TokenSet;
        //   510: dup            
        //   511: iconst_0       
        //   512: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   515: aastore        
        //   516: dup            
        //   517: iconst_1       
        //   518: iconst_1       
        //   519: anewarray       Lcom/intellij/psi/tree/IElementType;
        //   522: dup            
        //   523: iconst_0       
        //   524: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   527: aastore        
        //   528: invokestatic    com/intellij/psi/tree/TokenSet.create:([Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/psi/tree/TokenSet;
        //   531: aastore        
        //   532: invokestatic    com/intellij/psi/tree/TokenSet.orSet:([Lcom/intellij/psi/tree/TokenSet;)Lcom/intellij/psi/tree/TokenSet;
        //   535: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.skipElementsBack:(Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/tree/TokenSet;)Lcom/intellij/lang/ASTNode;
        //   538: astore_3       
        //   539: aload_3        
        //   540: ifnull          578
        //   543: aload_3        
        //   544: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   549: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TEMPLATE_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   552: if_acmpne       578
        //   555: goto            562
        //   558: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   561: athrow         
        //   562: aload_3        
        //   563: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   568: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.b:(Lcom/intellij/psi/PsiElement;)V
        //   571: goto            578
        //   574: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   577: athrow         
        //   578: aload_0        
        //   579: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.b:(Lcom/intellij/psi/PsiElement;)V
        //   582: aload_0        
        //   583: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   586: ifeq            644
        //   589: aload_0        
        //   590: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   595: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   598: ifne            644
        //   601: goto            608
        //   604: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   607: athrow         
        //   608: aload_0        
        //   609: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.insideConditionalHeader:(Lcom/intellij/psi/PsiElement;)Z
        //   612: ifne            644
        //   615: goto            622
        //   618: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   621: athrow         
        //   622: aload_0        
        //   623: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.insideLoopHeader:(Lcom/intellij/psi/PsiElement;)Z
        //   626: ifne            644
        //   629: goto            636
        //   632: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   635: athrow         
        //   636: iconst_1       
        //   637: goto            645
        //   640: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   643: athrow         
        //   644: iconst_0       
        //   645: istore_3       
        //   646: aload_0        
        //   647: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.b:(Lcom/intellij/psi/PsiElement;)V
        //   650: iload_3        
        //   651: ifeq            675
        //   654: aload_1        
        //   655: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   658: aload_1        
        //   659: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   662: invokeinterface com/intellij/psi/PsiElement.add:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   667: pop            
        //   668: goto            675
        //   671: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   674: athrow         
        //   675: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      13     16     20     Ljava/lang/IllegalArgumentException;
        //  4      21     21     25     Ljava/lang/IllegalArgumentException;
        //  32     37     37     41     Ljava/lang/IllegalArgumentException;
        //  52     76     76     80     Ljava/lang/IllegalArgumentException;
        //  80     94     97     101    Ljava/lang/IllegalArgumentException;
        //  87     113    116    120    Ljava/lang/IllegalArgumentException;
        //  101    132    132    136    Ljava/lang/IllegalArgumentException;
        //  136    154    154    158    Ljava/lang/IllegalArgumentException;
        //  158    190    193    197    Ljava/lang/IllegalArgumentException;
        //  165    204    204    208    Ljava/lang/IllegalArgumentException;
        //  208    222    225    229    Ljava/lang/IllegalArgumentException;
        //  215    250    250    254    Ljava/lang/IllegalArgumentException;
        //  254    273    276    280    Ljava/lang/IllegalArgumentException;
        //  261    293    296    300    Ljava/lang/IllegalArgumentException;
        //  280    307    307    311    Ljava/lang/IllegalArgumentException;
        //  311    331    334    338    Ljava/lang/IllegalArgumentException;
        //  318    367    367    371    Ljava/lang/IllegalArgumentException;
        //  371    391    394    398    Ljava/lang/IllegalArgumentException;
        //  442    458    461    465    Ljava/lang/IllegalArgumentException;
        //  446    474    477    481    Ljava/lang/IllegalArgumentException;
        //  539    555    558    562    Ljava/lang/IllegalArgumentException;
        //  543    571    574    578    Ljava/lang/IllegalArgumentException;
        //  582    601    604    608    Ljava/lang/IllegalArgumentException;
        //  589    615    618    622    Ljava/lang/IllegalArgumentException;
        //  608    629    632    636    Ljava/lang/IllegalArgumentException;
        //  622    640    640    644    Ljava/lang/IllegalArgumentException;
        //  646    668    671    675    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0101:
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
    
    private static boolean a(@NotNull final PsiElement p0, @NotNull final PsiElement p1, @NotNull final Ref<ParentCleanup> p2) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isDeletionWithParentCleanup"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "parent"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isDeletionWithParentCleanup"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "parentCleanup"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "isDeletionWithParentCleanup"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_1        
        //   133: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassPredeclarationList;
        //   136: ifne            265
        //   139: aload_1        
        //   140: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   143: ifeq            167
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: aload_0        
        //   154: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   157: ifne            265
        //   160: goto            167
        //   163: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: aload_1        
        //   168: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
        //   171: ifne            265
        //   174: goto            181
        //   177: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   180: athrow         
        //   181: aload_1        
        //   182: instanceof      Lcom/jetbrains/cidr/lang/psi/OCPropertyAttributesList;
        //   185: ifne            265
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: aload_1        
        //   196: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //   199: ifeq            223
        //   202: goto            209
        //   205: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   208: athrow         
        //   209: aload_0        
        //   210: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   213: ifne            265
        //   216: goto            223
        //   219: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   222: athrow         
        //   223: aload_1        
        //   224: instanceof      Lcom/jetbrains/cidr/lang/psi/OCEnum;
        //   227: ifne            265
        //   230: goto            237
        //   233: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   236: athrow         
        //   237: aload_1        
        //   238: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizePropertiesList;
        //   241: ifne            265
        //   244: goto            251
        //   247: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: aload_1        
        //   252: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTemplateParameterList;
        //   255: ifeq            273
        //   258: goto            265
        //   261: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   264: athrow         
        //   265: iconst_1       
        //   266: goto            274
        //   269: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   272: athrow         
        //   273: iconst_0       
        //   274: istore_3       
        //   275: iload_3        
        //   276: ifeq            292
        //   279: aload_2        
        //   280: getstatic       com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil$ParentCleanup.DELETE:Lcom/jetbrains/cidr/lang/refactoring/util/OCChangeUtil$ParentCleanup;
        //   283: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   286: iconst_1       
        //   287: ireturn        
        //   288: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   291: athrow         
        //   292: aload_1        
        //   293: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //   296: ifne            341
        //   299: aload_1        
        //   300: instanceof      Lcom/jetbrains/cidr/lang/psi/OCArgumentList;
        //   303: ifne            341
        //   306: goto            313
        //   309: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   312: athrow         
        //   313: aload_1        
        //   314: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   317: ifne            341
        //   320: goto            327
        //   323: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   326: athrow         
        //   327: aload_1        
        //   328: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTemplateArgumentList;
        //   331: ifeq            349
        //   334: goto            341
        //   337: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   340: athrow         
        //   341: iconst_1       
        //   342: goto            350
        //   345: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   348: athrow         
        //   349: iconst_0       
        //   350: istore          4
        //   352: iload           4
        //   354: ifeq            370
        //   357: aload_2        
        //   358: getstatic       com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil$ParentCleanup.LEAVE:Lcom/jetbrains/cidr/lang/refactoring/util/OCChangeUtil$ParentCleanup;
        //   361: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   364: iconst_1       
        //   365: ireturn        
        //   366: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   369: athrow         
        //   370: aload_1        
        //   371: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProtocolList;
        //   374: ifne            391
        //   377: aload_1        
        //   378: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppBaseClauseList;
        //   381: ifeq            399
        //   384: goto            391
        //   387: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   390: athrow         
        //   391: iconst_1       
        //   392: goto            400
        //   395: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   398: athrow         
        //   399: iconst_0       
        //   400: istore          5
        //   402: iload           5
        //   404: ifeq            420
        //   407: aload_2        
        //   408: getstatic       com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil$ParentCleanup.CLEAR:Lcom/jetbrains/cidr/lang/refactoring/util/OCChangeUtil$ParentCleanup;
        //   411: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   414: iconst_1       
        //   415: ireturn        
        //   416: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   419: athrow         
        //   420: iconst_0       
        //   421: ireturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/util/Ref<Lcom/jetbrains/cidr/lang/refactoring/util/OCChangeUtil$ParentCleanup;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    146    149    153    Ljava/lang/IllegalArgumentException;
        //  139    160    163    167    Ljava/lang/IllegalArgumentException;
        //  153    174    177    181    Ljava/lang/IllegalArgumentException;
        //  167    188    191    195    Ljava/lang/IllegalArgumentException;
        //  181    202    205    209    Ljava/lang/IllegalArgumentException;
        //  195    216    219    223    Ljava/lang/IllegalArgumentException;
        //  209    230    233    237    Ljava/lang/IllegalArgumentException;
        //  223    244    247    251    Ljava/lang/IllegalArgumentException;
        //  237    258    261    265    Ljava/lang/IllegalArgumentException;
        //  251    269    269    273    Ljava/lang/IllegalArgumentException;
        //  275    288    288    292    Ljava/lang/IllegalArgumentException;
        //  292    306    309    313    Ljava/lang/IllegalArgumentException;
        //  299    320    323    327    Ljava/lang/IllegalArgumentException;
        //  313    334    337    341    Ljava/lang/IllegalArgumentException;
        //  327    345    345    349    Ljava/lang/IllegalArgumentException;
        //  352    366    366    370    Ljava/lang/IllegalArgumentException;
        //  370    384    387    391    Ljava/lang/IllegalArgumentException;
        //  377    395    395    399    Ljava/lang/IllegalArgumentException;
        //  402    416    416    420    Ljava/lang/IllegalArgumentException;
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
    
    private static void a(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //     6: astore_1       
        //     7: aload_0        
        //     8: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //    13: astore_2       
        //    14: aload_1        
        //    15: ifnull          83
        //    18: aload_1        
        //    19: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //    22: ifne            49
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    31: athrow         
        //    32: aload_1        
        //    33: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    36: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    39: if_acmpne       54
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: return         
        //    50: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    53: athrow         
        //    54: aload_1        
        //    55: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    60: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isVisibilityKeyword:(Lcom/intellij/lang/ASTNode;)Z
        //    63: ifeq            73
        //    66: goto            83
        //    69: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: aload_1        
        //    74: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //    79: astore_1       
        //    80: goto            14
        //    83: aload_2        
        //    84: ifnull          135
        //    87: aload_2        
        //    88: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //    91: ifeq            106
        //    94: goto            101
        //    97: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   100: athrow         
        //   101: return         
        //   102: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: aload_2        
        //   107: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   112: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isVisibilityKeyword:(Lcom/intellij/lang/ASTNode;)Z
        //   115: ifeq            125
        //   118: goto            135
        //   121: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: aload_2        
        //   126: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   131: astore_2       
        //   132: goto            83
        //   135: aload_1        
        //   136: ifnull          210
        //   139: aload_1        
        //   140: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   145: astore_2       
        //   146: aload_2        
        //   147: ifnull          174
        //   150: aload_2        
        //   151: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isElementEmpty:(Lcom/intellij/psi/PsiElement;)Z
        //   154: ifeq            174
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: aload_2        
        //   165: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   170: astore_2       
        //   171: goto            146
        //   174: aload_1        
        //   175: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.b:(Lcom/intellij/psi/PsiElement;)V
        //   178: aload_2        
        //   179: ifnull          210
        //   182: aload_2        
        //   183: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   186: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   189: if_acmpne       210
        //   192: goto            199
        //   195: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   198: athrow         
        //   199: aload_2        
        //   200: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.b:(Lcom/intellij/psi/PsiElement;)V
        //   203: goto            210
        //   206: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   209: athrow         
        //   210: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  14     25     28     32     Ljava/lang/IllegalArgumentException;
        //  18     42     45     49     Ljava/lang/IllegalArgumentException;
        //  32     50     50     54     Ljava/lang/IllegalArgumentException;
        //  54     69     69     73     Ljava/lang/IllegalArgumentException;
        //  83     94     97     101    Ljava/lang/IllegalArgumentException;
        //  87     102    102    106    Ljava/lang/IllegalArgumentException;
        //  106    121    121    125    Ljava/lang/IllegalArgumentException;
        //  146    157    160    164    Ljava/lang/IllegalArgumentException;
        //  174    192    195    199    Ljava/lang/IllegalArgumentException;
        //  182    203    206    210    Ljava/lang/IllegalArgumentException;
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
    
    @Nullable
    private static PsiElement a(final PsiElement psiElement, final IElementType elementType) {
        final ASTNode childByType = psiElement.getNode().findChildByType(elementType);
        try {
            if (childByType != null) {
                return childByType.getPsi();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Nullable
    private static PsiElement a(final PsiElement psiElement, final int n) {
        PsiElement psiElement2 = null;
        for (final PsiElement psiElement3 : OCElementUtil.getAllChildren(psiElement)) {
            try {
                if (psiElement3.getTextRange().getEndOffset() < n || OCElementUtil.isElementEmpty(psiElement3)) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0061: {
                break Label_0061;
                continue;
            }
            psiElement2 = psiElement3;
            break;
        }
        while (true) {
            Label_0102: {
                Label_0109: {
                    Label_0088: {
                        try {
                            if (psiElement2 != null) {
                                break Label_0109;
                            }
                            final PsiElement psiElement4 = psiElement;
                            final boolean b = psiElement4 instanceof OCStructLike;
                            if (!b) {
                                break Label_0088;
                            }
                            break Label_0102;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final PsiElement psiElement4 = psiElement;
                            final boolean b = psiElement4 instanceof OCStructLike;
                            if (!b) {
                                if (!(psiElement instanceof OCClassDeclaration)) {
                                    break Label_0109;
                                }
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    break Label_0102;
                    while (true) {
                        try {
                            if (psiElement2 == null || !(psiElement2.getPrevSibling() instanceof OCMacroCall)) {
                                break;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                        psiElement2 = psiElement2.getPrevSibling();
                    }
                }
                return psiElement2;
            }
            psiElement2 = psiElement.getLastChild();
            continue;
        }
    }
    
    public static boolean canBeReplacedToBlockStatement(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          18
        //     4: aload_0        
        //     5: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //     8: ifne            24
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    17: athrow         
        //    18: iconst_0       
        //    19: ireturn        
        //    20: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_0        
        //    25: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    30: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCallArgument;
        //    33: ifne            99
        //    36: aload_0        
        //    37: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    42: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //    45: ifne            99
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: aload_0        
        //    56: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    61: instanceof      Lcom/jetbrains/cidr/lang/psi/OCForStatement;
        //    64: ifeq            105
        //    67: goto            74
        //    70: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    73: athrow         
        //    74: aload_0        
        //    75: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    80: checkcast       Lcom/jetbrains/cidr/lang/psi/OCForStatement;
        //    83: invokeinterface com/jetbrains/cidr/lang/psi/OCForStatement.getBody:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    88: aload_0        
        //    89: if_acmpeq       105
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: iconst_0       
        //   100: ireturn        
        //   101: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: iconst_1       
        //   106: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      11     14     18     Ljava/lang/IllegalArgumentException;
        //  4      20     20     24     Ljava/lang/IllegalArgumentException;
        //  24     48     51     55     Ljava/lang/IllegalArgumentException;
        //  36     67     70     74     Ljava/lang/IllegalArgumentException;
        //  55     92     95     99     Ljava/lang/IllegalArgumentException;
        //  74     101    101    105    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0055:
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
    
    public static OCStatement ensureParentIsBlockStatement(final OCStatement ocStatement) {
        if (!(ocStatement.getParent() instanceof OCBlockStatement)) {
            final OCBlockStatement ocBlockStatement = (OCBlockStatement)OCElementFactory.statementFromText("{\n}", (PsiElement)ocStatement);
            final PsiElement copy = ocStatement.copy();
            final OCBlockStatement ocBlockStatement2 = (OCBlockStatement)replaceHandlingMacros((PsiElement)ocStatement, (PsiElement)ocBlockStatement);
            return (OCStatement)ocBlockStatement2.addBefore(copy, ocBlockStatement2.getClosingBrace());
        }
        return ocStatement;
    }
    
    @Nullable
    public static PsiElement getAppropriateParent(final OCSymbolKind ocSymbolKind, final PsiElement psiElement) {
        try {
            if (ocSymbolKind.isFunction()) {
                return PsiTreeUtil.getParentOfType(psiElement, new Class[] { OCFile.class, OCCppNamespace.class, OCStructLike.class });
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocSymbolKind == OCSymbolKind.LOCAL_VARIABLE) {
                return PsiTreeUtil.getParentOfType(psiElement, (Class)OCBlockStatement.class);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocSymbolKind == OCSymbolKind.METHOD) {
                return PsiTreeUtil.getParentOfType(psiElement, (Class)OCClassDeclaration.class);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return PsiTreeUtil.getParentOfType(psiElement, (Class)OCFile.class);
    }
    
    public static boolean changeText(@NotNull final Project project, final PsiFile psiFile, final int n, final int n2, final String s, final boolean b) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil", "changeText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return changeText(project, psiFile, n, n2, s, b, true);
    }
    
    public static boolean changeText(@NotNull final Project project, final PsiFile psiFile, final int n, final int n2, final String s, final boolean b, final boolean b2) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil", "changeText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Document document = PsiDocumentManager.getInstance(project).getDocument(psiFile);
        Label_0081: {
            try {
                if (document == null) {
                    return false;
                }
                final PsiFile psiFile2 = psiFile;
                final boolean b3 = psiFile2.isValid();
                if (!b3) {
                    return false;
                }
                break Label_0081;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final PsiFile psiFile2 = psiFile;
                final boolean b3 = psiFile2.isValid();
                if (!b3) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (b) {
                    PsiDocumentManager.getInstance(project).doPostponedOperationsAndUnblockDocument(document);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        document.replaceString(n, n + n2, (CharSequence)s);
        if (b2) {
            PsiDocumentManager.getInstance(project).commitDocument(document);
            int n3 = n + s.length();
            try {
                if (n3 < psiFile.getTextLength()) {
                    ++n3;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            reformatTextIfNotInjected(psiFile, n, n3);
        }
        return true;
    }
    
    public static void reformatTextIfNotInjected(final PsiFile psiFile, final int n, final int n2) {
        try {
            if (!InjectedLanguageManager.getInstance(psiFile.getProject()).isInjectedFragment(psiFile)) {
                CodeStyleManager.getInstance(psiFile.getProject()).reformatRange((PsiElement)psiFile, n, n2, true);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    public static OCCaretLocation removeFunctions(@NotNull final Collection<OCFunctionSymbol> p0, @NotNull final OCCaretLocation p1) {
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
        //    18: ldc             "functions"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "removeFunctions"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "location"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "removeFunctions"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokeinterface java/util/Collection.isEmpty:()Z
        //    94: ifeq            103
        //    97: aload_1        
        //    98: areturn        
        //    99: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: new             Ljava/util/HashMap;
        //   106: dup            
        //   107: invokespecial   java/util/HashMap.<init>:()V
        //   110: astore_2       
        //   111: aload_0        
        //   112: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   117: astore_3       
        //   118: aload_3        
        //   119: invokeinterface java/util/Iterator.hasNext:()Z
        //   124: ifeq            310
        //   127: aload_3        
        //   128: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   133: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   136: astore          4
        //   138: aload           4
        //   140: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.locateFunctionDefinition:()Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   143: astore          5
        //   145: aload           5
        //   147: ifnull          307
        //   150: aload           5
        //   152: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   157: ifnull          307
        //   160: goto            167
        //   163: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: aload_2        
        //   168: aload           5
        //   170: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   175: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   180: checkcast       Ljava/util/List;
        //   183: astore          6
        //   185: aload           6
        //   187: ifnonnull       215
        //   190: new             Ljava/util/ArrayList;
        //   193: dup            
        //   194: invokespecial   java/util/ArrayList.<init>:()V
        //   197: astore          6
        //   199: aload_2        
        //   200: aload           5
        //   202: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   207: aload           6
        //   209: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   214: pop            
        //   215: aload           5
        //   217: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getRangeWithMacros:()Lcom/intellij/openapi/util/TextRange;
        //   222: astore          7
        //   224: aload           5
        //   226: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   231: astore          8
        //   233: aload           8
        //   235: ifnonnull       245
        //   238: goto            118
        //   241: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   244: athrow         
        //   245: aload           8
        //   247: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   252: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   255: aload           8
        //   257: invokevirtual   com/intellij/psi/PsiDocumentManager.getDocument:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/editor/Document;
        //   260: astore          9
        //   262: aload           9
        //   264: ifnonnull       274
        //   267: goto            118
        //   270: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   273: athrow         
        //   274: aload           6
        //   276: aload           7
        //   278: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   281: aload           9
        //   283: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //   288: aload           7
        //   290: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   293: ldc             "\n"
        //   295: invokestatic    com/intellij/util/text/CharArrayUtil.shiftForward:(Ljava/lang/CharSequence;ILjava/lang/String;)I
        //   298: invokestatic    com/intellij/openapi/util/TextRange.create:(II)Lcom/intellij/openapi/util/TextRange;
        //   301: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   306: pop            
        //   307: goto            118
        //   310: aload_1        
        //   311: invokestatic    com/intellij/openapi/util/Ref.create:(Ljava/lang/Object;)Lcom/intellij/openapi/util/Ref;
        //   314: astore_3       
        //   315: aload_1        
        //   316: invokevirtual   com/jetbrains/cidr/lang/generate/OCCaretLocation.getOffsetInFile:()Ljava/lang/Integer;
        //   319: ifnonnull       330
        //   322: iconst_m1      
        //   323: goto            337
        //   326: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   329: athrow         
        //   330: aload_1        
        //   331: invokevirtual   com/jetbrains/cidr/lang/generate/OCCaretLocation.getOffsetInFile:()Ljava/lang/Integer;
        //   334: invokevirtual   java/lang/Integer.intValue:()I
        //   337: istore          4
        //   339: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   342: aload_2        
        //   343: aload_1        
        //   344: iload           4
        //   346: aload_3        
        //   347: invokedynamic   run:(Ljava/util/Map;Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;ILcom/intellij/openapi/util/Ref;)Ljava/lang/Runnable;
        //   352: invokeinterface com/intellij/openapi/application/Application.runWriteAction:(Ljava/lang/Runnable;)V
        //   357: aload_3        
        //   358: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   361: checkcast       Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;
        //   364: areturn        
        //    Signature:
        //  (Ljava/util/Collection<Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;>;Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;)Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     99     99     103    Ljava/lang/IllegalArgumentException;
        //  145    160    163    167    Ljava/lang/IllegalArgumentException;
        //  233    241    241    245    Ljava/lang/IllegalArgumentException;
        //  262    270    270    274    Ljava/lang/IllegalArgumentException;
        //  315    326    326    330    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0163_1:
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
    private static List<TextRange> a(@NotNull final List<TextRange> p0, @NotNull final Document p1) {
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
        //    18: ldc             "ranges"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "mergeRanges"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "mergeRanges"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: new             Ljava/util/ArrayList;
        //    91: dup            
        //    92: invokespecial   java/util/ArrayList.<init>:()V
        //    95: astore_2       
        //    96: invokestatic    com/intellij/openapi/util/Ref.create:()Lcom/intellij/openapi/util/Ref;
        //    99: astore_3       
        //   100: aload_0        
        //   101: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //   106: invokedynamic   compare:()Ljava/util/Comparator;
        //   111: invokeinterface java/util/stream/Stream.sorted:(Ljava/util/Comparator;)Ljava/util/stream/Stream;
        //   116: aload_3        
        //   117: aload_1        
        //   118: aload_2        
        //   119: invokedynamic   accept:(Lcom/intellij/openapi/util/Ref;Lcom/intellij/openapi/editor/Document;Ljava/util/ArrayList;)Ljava/util/function/Consumer;
        //   124: invokeinterface java/util/stream/Stream.forEach:(Ljava/util/function/Consumer;)V
        //   129: aload_3        
        //   130: invokevirtual   com/intellij/openapi/util/Ref.isNull:()Z
        //   133: ifne            152
        //   136: aload_2        
        //   137: aload_3        
        //   138: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   141: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   144: pop            
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   151: athrow         
        //   152: aload_2        
        //   153: dup            
        //   154: ifnonnull       191
        //   157: new             Ljava/lang/IllegalStateException;
        //   160: dup            
        //   161: ldc             "@NotNull method %s.%s must not return null"
        //   163: ldc             2
        //   165: anewarray       Ljava/lang/Object;
        //   168: dup            
        //   169: ldc             0
        //   171: ldc             "com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil"
        //   173: aastore        
        //   174: dup            
        //   175: ldc             1
        //   177: ldc             "mergeRanges"
        //   179: aastore        
        //   180: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   183: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   186: athrow         
        //   187: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   190: athrow         
        //   191: areturn        
        //    Signature:
        //  (Ljava/util/List<Lcom/intellij/openapi/util/TextRange;>;Lcom/intellij/openapi/editor/Document;)Ljava/util/List<Lcom/intellij/openapi/util/TextRange;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  100    145    148    152    Ljava/lang/IllegalArgumentException;
        //  152    187    187    191    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0072_2:
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
    
    private static void a(@NotNull final PsiElement psiElement, @NotNull final PsiElement psiElement2, final ParentCleanup parentCleanup) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil", "deleteChildFromList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child", "com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil", "deleteChildFromList"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final ASTNode skipElementsBack = TreeUtil.skipElementsBack(psiElement2.getNode().getTreePrev(), OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET);
        Label_0240: {
            Label_0129: {
                try {
                    if (skipElementsBack == null) {
                        break Label_0129;
                    }
                    final ASTNode astNode = skipElementsBack;
                    final IElementType elementType = astNode.getElementType();
                    final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.COMMA;
                    if (elementType == ocPunctuatorElementType) {
                        break Label_0129;
                    }
                    break Label_0129;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final ASTNode astNode = skipElementsBack;
                    final IElementType elementType = astNode.getElementType();
                    final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.COMMA;
                    if (elementType == ocPunctuatorElementType) {
                        CodeEditUtil.removeChild(psiElement.getNode(), skipElementsBack);
                        break Label_0240;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            final ASTNode skipElements = TreeUtil.skipElements(psiElement2.getNode().getTreeNext(), OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET);
            Label_0208: {
                Label_0190: {
                    try {
                        if (skipElements == null) {
                            break Label_0208;
                        }
                        final ASTNode astNode2 = skipElements;
                        final IElementType elementType2 = astNode2.getElementType();
                        final OCPunctuatorElementType ocPunctuatorElementType2 = OCTokenTypes.COMMA;
                        if (elementType2 == ocPunctuatorElementType2) {
                            break Label_0190;
                        }
                        break Label_0208;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    try {
                        final ASTNode astNode2 = skipElements;
                        final IElementType elementType2 = astNode2.getElementType();
                        final OCPunctuatorElementType ocPunctuatorElementType2 = OCTokenTypes.COMMA;
                        if (elementType2 == ocPunctuatorElementType2) {
                            CodeEditUtil.removeChild(psiElement.getNode(), skipElements);
                            break Label_0240;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
                try {
                    if (parentCleanup == ParentCleanup.DELETE) {
                        delete(psiElement);
                        return;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            try {
                if (parentCleanup == ParentCleanup.CLEAR) {
                    clear(psiElement);
                    return;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        CodeEditUtil.removeChild(psiElement.getNode(), psiElement2.getNode());
    }
    
    public static void clear(final PsiElement psiElement) {
        psiElement.deleteChildRange(psiElement.getFirstChild(), psiElement.getLastChild());
    }
    
    public static void deleteUnsafe(final PsiElement psiElement) {
        b(psiElement);
    }
    
    private static void b(final PsiElement psiElement) {
        PsiElement psiElement2 = OCElementUtil.getPrevSiblingOrParentSibling(psiElement);
        while (psiElement2 instanceof OCMacroCall) {
            final PsiElement psiElement3 = psiElement2;
            psiElement2 = psiElement2.getPrevSibling();
            boolean b = false;
            Label_0039: {
                try {
                    if (psiElement3.getTextLength() > 0) {
                        b = true;
                        break Label_0039;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                b = false;
            }
            final boolean b2 = b;
            try {
                psiElement3.delete();
                if (b2) {
                    break;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        CodeEditUtil.removeChild(psiElement.getParent().getNode(), psiElement.getNode());
    }
    
    public static PsiElement addHandlingMacros(@NotNull final PsiElement psiElement, @NotNull final PsiElement psiElement2, @Nullable final PsiElement psiElement3) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil", "addHandlingMacros"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child", "com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil", "addHandlingMacros"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return addHandlingMacros(psiElement, psiElement2, psiElement3, true);
    }
    
    public static PsiElement addHandlingMacros(@NotNull final PsiElement psiElement, @NotNull PsiElement psiElement2, @Nullable final PsiElement psiElement3, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil", "addHandlingMacros"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child", "com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil", "addHandlingMacros"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        PsiElement psiElement4 = OCElementUtil.getPrevSiblingOrParentSibling(psiElement2);
        if (psiElement3 == null) {
            psiElement2 = psiElement.add(psiElement2);
        }
        else if (b) {
            psiElement2 = psiElement.addBefore(psiElement2, psiElement3);
        }
        else {
            psiElement2 = psiElement.addAfter(psiElement2, psiElement3);
        }
        PsiElement psiElement5 = psiElement2;
        while (psiElement4 instanceof OCMacroCall) {
            final PsiElement psiElement6 = psiElement4;
            psiElement4 = psiElement4.getPrevSibling();
            boolean b2 = false;
            Label_0177: {
                try {
                    if (psiElement6.getTextLength() > 0) {
                        b2 = true;
                        break Label_0177;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                b2 = false;
            }
            final boolean b3 = b2;
            CodeEditUtil.addChild(psiElement.getNode(), psiElement6.getNode(), psiElement5.getNode());
            psiElement5 = psiElement6;
            try {
                if (b3) {
                    break;
                }
                continue;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return psiElement2;
    }
    
    public static PsiElement replaceHandlingMacros(final PsiElement psiElement, final PsiElement psiElement2) {
        Label_0021: {
            try {
                if (psiElement == null) {
                    return psiElement;
                }
                final PsiElement psiElement3 = psiElement2;
                if (psiElement3 == null) {
                    return psiElement;
                }
                break Label_0021;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final PsiElement psiElement3 = psiElement2;
                if (psiElement3 == null) {
                    return psiElement;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        PsiElement psiElement4 = OCElementUtil.getPrevSiblingOrParentSibling(psiElement);
        PsiElement psiElement5 = OCElementUtil.getPrevSiblingOrParentSibling(psiElement2);
        PsiElement addBefore = psiElement;
        while (psiElement5 instanceof OCMacroCall) {
            final PsiElement psiElement6 = psiElement5;
            psiElement5 = psiElement5.getPrevSibling();
            boolean b = false;
            Label_0070: {
                try {
                    if (psiElement6.getTextLength() > 0) {
                        b = true;
                        break Label_0070;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                b = false;
            }
            final boolean b2 = b;
            addBefore = psiElement.getParent().addBefore(psiElement6, addBefore);
            try {
                if (b2) {
                    break;
                }
                continue;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        while (psiElement4 instanceof OCMacroCall) {
            final PsiElement psiElement7 = psiElement4;
            psiElement4 = psiElement4.getPrevSibling();
            boolean b3 = false;
            Label_0140: {
                try {
                    if (psiElement7.getTextLength() > 0) {
                        b3 = true;
                        break Label_0140;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                b3 = false;
            }
            final boolean b4 = b3;
            try {
                CodeEditUtil.removeChild(psiElement7.getParent().getNode(), psiElement7.getNode());
                if (b4) {
                    break;
                }
                continue;
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return psiElement.replace(psiElement2);
    }
    
    public static void safeDeleteReference(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //     4: ifeq            14
        //     7: aload_0        
        //     8: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    13: astore_0       
        //    14: aload_0        
        //    15: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //    18: ifeq            176
        //    21: aload_0        
        //    22: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //    25: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArgumentExpressions:()Ljava/util/List;
        //    30: astore_1       
        //    31: aload_0        
        //    32: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //    35: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getProbableResponders:()Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders;
        //    40: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getKnownResponder:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    43: astore_2       
        //    44: aload_0        
        //    45: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    48: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    51: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    56: astore_3       
        //    57: aload_1        
        //    58: invokeinterface java/util/List.size:()I
        //    63: iconst_1       
        //    64: if_icmpne       173
        //    67: aload_2        
        //    68: ifnull          110
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    77: athrow         
        //    78: aload_2        
        //    79: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isSynthetic:()Z
        //    84: ifeq            173
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: aload_2        
        //    95: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   100: ifnonnull       173
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: aload_1        
        //   111: iconst_0       
        //   112: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   117: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   120: astore          4
        //   122: aload_3        
        //   123: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   126: ifeq            173
        //   129: aload           4
        //   131: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.hasSideEffects:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Z
        //   134: ifne            158
        //   137: goto            144
        //   140: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   143: athrow         
        //   144: aload_3        
        //   145: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   148: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.safeDeleteStatement:(Lcom/jetbrains/cidr/lang/psi/OCStatement;)V
        //   151: goto            173
        //   154: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   157: athrow         
        //   158: aload_3        
        //   159: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   162: invokeinterface com/jetbrains/cidr/lang/psi/OCExpressionStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   167: aload           4
        //   169: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   172: pop            
        //   173: goto            297
        //   176: aload_0        
        //   177: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   180: ifne            197
        //   183: aload_0        
        //   184: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   187: ifeq            297
        //   190: goto            197
        //   193: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: aload_0        
        //   198: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   201: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   204: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   209: astore_1       
        //   210: aload_1        
        //   211: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   214: ifne            222
        //   217: return         
        //   218: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: aload_1        
        //   223: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   226: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   229: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   234: astore_2       
        //   235: aload_1        
        //   236: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   239: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getSourceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   244: astore_3       
        //   245: aload_2        
        //   246: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   249: ifeq            291
        //   252: aload_3        
        //   253: ifnull          277
        //   256: goto            263
        //   259: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   262: athrow         
        //   263: aload_3        
        //   264: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.hasSideEffects:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Z
        //   267: ifne            291
        //   270: goto            277
        //   273: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: aload_2        
        //   278: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   281: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.safeDeleteStatement:(Lcom/jetbrains/cidr/lang/psi/OCStatement;)V
        //   284: goto            297
        //   287: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   290: athrow         
        //   291: aload_1        
        //   292: aload_3        
        //   293: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   296: pop            
        //   297: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  57     71     74     78     Ljava/lang/IllegalArgumentException;
        //  67     87     90     94     Ljava/lang/IllegalArgumentException;
        //  78     103    106    110    Ljava/lang/IllegalArgumentException;
        //  122    137    140    144    Ljava/lang/IllegalArgumentException;
        //  129    154    154    158    Ljava/lang/IllegalArgumentException;
        //  176    190    193    197    Ljava/lang/IllegalArgumentException;
        //  210    218    218    222    Ljava/lang/IllegalArgumentException;
        //  245    256    259    263    Ljava/lang/IllegalArgumentException;
        //  252    270    273    277    Ljava/lang/IllegalArgumentException;
        //  263    287    287    291    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0078:
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
    
    public static void safeDeleteStatement(final OCStatement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getParent:()Lcom/intellij/psi/PsiElement;
        //     6: astore_1       
        //     7: aload_0        
        //     8: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Lcom/jetbrains/cidr/lang/psi/OCStatement;)Z
        //    11: ifeq            45
        //    14: aload_1        
        //    15: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    20: aload_1        
        //    21: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //    24: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getElseKeyword:()Lcom/intellij/lang/ASTNode;
        //    29: aload_0        
        //    30: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getNode:()Lcom/intellij/lang/ASTNode;
        //    35: invokestatic    com/intellij/psi/impl/source/codeStyle/CodeEditUtil.removeChildren:(Lcom/intellij/lang/ASTNode;Lcom/intellij/lang/ASTNode;Lcom/intellij/lang/ASTNode;)V
        //    38: goto            126
        //    41: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload_1        
        //    46: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //    49: ifeq            115
        //    52: aload_1        
        //    53: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //    56: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.getStatements:()Ljava/util/List;
        //    61: invokeinterface java/util/List.size:()I
        //    66: iconst_1       
        //    67: if_icmpne       108
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: aload_1        
        //    78: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    81: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Lcom/jetbrains/cidr/lang/psi/OCStatement;)Z
        //    84: ifeq            108
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: aload_1        
        //    95: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    98: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.safeDeleteStatement:(Lcom/jetbrains/cidr/lang/psi/OCStatement;)V
        //   101: goto            126
        //   104: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: aload_0        
        //   109: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   112: goto            126
        //   115: aload_0        
        //   116: ldc             ";"
        //   118: aload_0        
        //   119: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.statementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   122: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   125: pop            
        //   126: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      41     41     45     Ljava/lang/IllegalArgumentException;
        //  45     70     73     77     Ljava/lang/IllegalArgumentException;
        //  52     87     90     94     Ljava/lang/IllegalArgumentException;
        //  77     104    104    108    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0077:
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
    
    private static boolean a(final OCStatement ocStatement) {
        Label_0037: {
            try {
                if (!(ocStatement.getParent() instanceof OCIfStatement)) {
                    return false;
                }
                final OCStatement ocStatement2 = ocStatement;
                final PsiElement psiElement = ocStatement2.getParent();
                final OCIfStatement ocIfStatement = (OCIfStatement)psiElement;
                final OCStatement ocStatement3 = ocIfStatement.getElseBranch();
                final OCStatement ocStatement4 = ocStatement;
                if (ocStatement3 == ocStatement4) {
                    break Label_0037;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCStatement ocStatement2 = ocStatement;
                final PsiElement psiElement = ocStatement2.getParent();
                final OCIfStatement ocIfStatement = (OCIfStatement)psiElement;
                final OCStatement ocStatement3 = ocIfStatement.getElseBranch();
                final OCStatement ocStatement4 = ocStatement;
                if (ocStatement3 == ocStatement4) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    enum ParentCleanup
    {
        LEAVE, 
        DELETE, 
        CLEAR;
    }
}
