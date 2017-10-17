// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

public class MethodInsertHandler implements InsertHandler<LookupElement>
{
    @NotNull
    protected final OCMethodSymbol mySymbol;
    private final PsiElement myContextExpression;
    private final boolean myInsideSendMessage;
    private final String myQualifier;
    
    MethodInsertHandler(@NotNull final OCMethodSymbol mySymbol, final PsiElement myContextExpression, final boolean myInsideSendMessage, final String myQualifier) {
        if (mySymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler", "<init>"));
        }
        this.mySymbol = mySymbol;
        this.myContextExpression = myContextExpression;
        this.myInsideSendMessage = myInsideSendMessage;
        this.myQualifier = myQualifier;
    }
    
    protected String getInsertion(final InsertionContext p0, final LookupElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/lang/StringBuilder;
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuilder.<init>:()V
        //     7: astore_3       
        //     8: aload_1        
        //     9: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getDocument:()Lcom/intellij/openapi/editor/Document;
        //    12: astore          4
        //    14: aload_1        
        //    15: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getFile:()Lcom/intellij/psi/PsiFile;
        //    18: astore          5
        //    20: aload_1        
        //    21: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getEditor:()Lcom/intellij/openapi/editor/Editor;
        //    24: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //    29: astore          6
        //    31: aload_2        
        //    32: invokevirtual   com/intellij/codeInsight/lookup/LookupElement.getLookupString:()Ljava/lang/String;
        //    35: astore          7
        //    37: aload_0        
        //    38: invokespecial   com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:()Z
        //    41: istore          8
        //    43: aload_3        
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.myQualifier:Ljava/lang/String;
        //    48: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    51: pop            
        //    52: aload_0        
        //    53: getfield        com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.myQualifier:Ljava/lang/String;
        //    56: invokevirtual   java/lang/String.isEmpty:()Z
        //    59: ifne            76
        //    62: aload_3        
        //    63: bipush          32
        //    65: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    68: pop            
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aconst_null    
        //    77: astore          9
        //    79: aconst_null    
        //    80: astore          10
        //    82: aload           6
        //    84: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //    89: aload           7
        //    91: invokevirtual   java/lang/String.length:()I
        //    94: isub           
        //    95: istore          11
        //    97: aload_0        
        //    98: getfield        com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.myQualifier:Ljava/lang/String;
        //   101: invokevirtual   java/lang/String.isEmpty:()Z
        //   104: ifeq            236
        //   107: aload_1        
        //   108: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getCompletionChar:()C
        //   111: bipush          9
        //   113: if_icmpne       236
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: aload           6
        //   125: iload           11
        //   127: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //   132: aload           4
        //   134: iload           11
        //   136: iload           11
        //   138: aload           7
        //   140: invokevirtual   java/lang/String.length:()I
        //   143: iadd           
        //   144: ldc             "xxx"
        //   146: invokeinterface com/intellij/openapi/editor/Document.replaceString:(IILjava/lang/CharSequence;)V
        //   151: aload_1        
        //   152: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   155: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   158: invokevirtual   com/intellij/psi/PsiDocumentManager.commitAllDocuments:()V
        //   161: iload           8
        //   163: ifeq            227
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: aload           5
        //   175: iload           11
        //   177: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   182: ldc             Lcom/jetbrains/cidr/lang/psi/OCMethod;.class
        //   184: iconst_1       
        //   185: iconst_1       
        //   186: anewarray       Ljava/lang/Class;
        //   189: dup            
        //   190: iconst_0       
        //   191: ldc             Lcom/jetbrains/cidr/lang/psi/OCImplementation;.class
        //   193: aastore        
        //   194: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Z[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   197: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   200: astore          12
        //   202: aload           12
        //   204: ifnull          221
        //   207: aload           12
        //   209: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   214: goto            222
        //   217: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   220: athrow         
        //   221: aconst_null    
        //   222: astore          10
        //   224: goto            236
        //   227: aload           5
        //   229: iload           11
        //   231: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.getSendMessage:(Lcom/intellij/psi/PsiFile;I)Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   234: astore          9
        //   236: aload           9
        //   238: ifnull          255
        //   241: aload           9
        //   243: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArguments:()Ljava/util/List;
        //   248: goto            256
        //   251: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   254: athrow         
        //   255: aconst_null    
        //   256: astore          12
        //   258: aload_0        
        //   259: getfield        com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.mySymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   262: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getSelectors:()Ljava/util/List;
        //   267: astore          13
        //   269: iconst_0       
        //   270: istore          14
        //   272: aload_0        
        //   273: getfield        com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.mySymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   276: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isVararg:()Z
        //   281: ifeq            300
        //   284: aload           13
        //   286: invokeinterface java/util/List.size:()I
        //   291: iconst_1       
        //   292: isub           
        //   293: goto            302
        //   296: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   299: athrow         
        //   300: ldc             2147483647
        //   302: istore          15
        //   304: iload           8
        //   306: ifne            317
        //   309: iconst_1       
        //   310: goto            318
        //   313: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   316: athrow         
        //   317: iconst_0       
        //   318: istore          16
        //   320: iconst_0       
        //   321: istore          17
        //   323: iload           17
        //   325: aload           13
        //   327: invokeinterface java/util/List.size:()I
        //   332: if_icmpge       552
        //   335: aload           13
        //   337: iload           17
        //   339: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   344: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol;
        //   347: astore          18
        //   349: iload           17
        //   351: ifle            368
        //   354: aload_3        
        //   355: bipush          32
        //   357: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   360: pop            
        //   361: goto            368
        //   364: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   367: athrow         
        //   368: aload_3        
        //   369: aload           18
        //   371: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol.getSelectorName:()Ljava/lang/String;
        //   376: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   379: pop            
        //   380: aload           18
        //   382: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol.getParameter:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   387: astore          19
        //   389: aload           19
        //   391: ifnull          546
        //   394: aload           12
        //   396: ifnull          425
        //   399: goto            406
        //   402: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   405: athrow         
        //   406: iload           14
        //   408: aload           12
        //   410: invokeinterface java/util/List.size:()I
        //   415: if_icmplt       433
        //   418: goto            425
        //   421: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   424: athrow         
        //   425: aconst_null    
        //   426: goto            453
        //   429: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   432: athrow         
        //   433: aload           12
        //   435: iload           14
        //   437: iinc            14, 1
        //   440: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   445: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMessageArgument;
        //   448: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getArgumentExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   453: astore          20
        //   455: aload           20
        //   457: ifnull          479
        //   460: aload_3        
        //   461: aload           20
        //   463: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getTextWithMacros:()Ljava/lang/String;
        //   468: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   471: pop            
        //   472: goto            546
        //   475: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   478: athrow         
        //   479: iload           16
        //   481: ifeq            498
        //   484: aload_3        
        //   485: ldc             "<#"
        //   487: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   490: pop            
        //   491: goto            498
        //   494: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   497: athrow         
        //   498: aload_3        
        //   499: aload           5
        //   501: aload           19
        //   503: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.appendParameterName:(Ljava/lang/StringBuilder;Lcom/intellij/psi/PsiFile;Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)V
        //   506: iload           17
        //   508: iload           15
        //   510: if_icmpne       527
        //   513: aload_3        
        //   514: ldc             ", ..."
        //   516: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   519: pop            
        //   520: goto            527
        //   523: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   526: athrow         
        //   527: iload           16
        //   529: ifeq            546
        //   532: aload_3        
        //   533: ldc             "#>"
        //   535: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   538: pop            
        //   539: goto            546
        //   542: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   545: athrow         
        //   546: iinc            17, 1
        //   549: goto            323
        //   552: aload           9
        //   554: ifnull          617
        //   557: aload           9
        //   559: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getRangeWithMacros:()Lcom/intellij/openapi/util/TextRange;
        //   564: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   567: istore          17
        //   569: aload           9
        //   571: invokestatic    com/intellij/psi/util/PsiTreeUtil.lastChild:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   574: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   579: ldc             "]"
        //   581: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   584: ifeq            597
        //   587: iinc            17, -1
        //   590: goto            597
        //   593: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   596: athrow         
        //   597: aload_1        
        //   598: iload           11
        //   600: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.setTailOffset:(I)V
        //   603: aload           4
        //   605: iload           11
        //   607: iload           17
        //   609: invokeinterface com/intellij/openapi/editor/Document.deleteString:(II)V
        //   614: goto            640
        //   617: aload           10
        //   619: ifnull          640
        //   622: aload           10
        //   624: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getRangeWithMacros:()Lcom/intellij/openapi/util/TextRange;
        //   629: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   632: istore          17
        //   634: aload_1        
        //   635: iload           17
        //   637: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.setTailOffset:(I)V
        //   640: iload           8
        //   642: ifeq            753
        //   645: aload           5
        //   647: iload           11
        //   649: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   654: ldc             Lcom/jetbrains/cidr/lang/psi/OCMethod;.class
        //   656: iconst_1       
        //   657: iconst_1       
        //   658: anewarray       Ljava/lang/Class;
        //   661: dup            
        //   662: iconst_0       
        //   663: ldc             Lcom/jetbrains/cidr/lang/psi/OCImplementation;.class
        //   665: aastore        
        //   666: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Z[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   669: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   672: astore          17
        //   674: aload           17
        //   676: ifnull          753
        //   679: aload           17
        //   681: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getReturnTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   686: ifnonnull       753
        //   689: goto            696
        //   692: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   695: athrow         
        //   696: aload_3        
        //   697: iconst_0       
        //   698: new             Ljava/lang/StringBuilder;
        //   701: dup            
        //   702: invokespecial   java/lang/StringBuilder.<init>:()V
        //   705: ldc             "("
        //   707: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   710: aload_0        
        //   711: getfield        com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.mySymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   714: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getEffectiveType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   719: aload           17
        //   721: aload_0        
        //   722: getfield        com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.mySymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   725: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getReturnTypeText:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;)Ljava/lang/String;
        //   728: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Ljava/lang/String;
        //   731: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   734: ldc             ")"
        //   736: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   739: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   742: invokevirtual   java/lang/StringBuilder.insert:(ILjava/lang/String;)Ljava/lang/StringBuilder;
        //   745: pop            
        //   746: goto            753
        //   749: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   752: athrow         
        //   753: aload_0        
        //   754: invokespecial   com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:()Z
        //   757: ifeq            833
        //   760: aload           10
        //   762: ifnonnull       833
        //   765: goto            772
        //   768: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   771: athrow         
        //   772: aload_1        
        //   773: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   776: invokestatic    com/intellij/psi/codeStyle/CodeStyleSettingsManager.getSettings:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleSettings;
        //   779: ldc             Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;.class
        //   781: invokevirtual   com/intellij/psi/codeStyle/CodeStyleSettings.getCustomSettings:(Ljava/lang/Class;)Lcom/intellij/psi/codeStyle/CustomCodeStyleSettings;
        //   784: checkcast       Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   787: astore          17
        //   789: aload           17
        //   791: ifnull          823
        //   794: aload           17
        //   796: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SEMICOLON_AFTER_METHOD_SIGNATURE:Z
        //   799: ifeq            823
        //   802: goto            809
        //   805: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   808: athrow         
        //   809: aload_3        
        //   810: ldc             ";"
        //   812: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   815: pop            
        //   816: goto            823
        //   819: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   822: athrow         
        //   823: aload_3        
        //   824: ldc             "{ }"
        //   826: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   829: pop            
        //   830: goto            871
        //   833: aload_0        
        //   834: getfield        com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.myQualifier:Ljava/lang/String;
        //   837: invokevirtual   java/lang/String.isEmpty:()Z
        //   840: ifne            871
        //   843: aload_0        
        //   844: getfield        com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.myInsideSendMessage:Z
        //   847: ifne            871
        //   850: goto            857
        //   853: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   856: athrow         
        //   857: aload_3        
        //   858: bipush          93
        //   860: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   863: pop            
        //   864: goto            871
        //   867: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   870: athrow         
        //   871: aload_3        
        //   872: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   875: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  43     69     72     76     Ljava/lang/IllegalArgumentException;
        //  97     116    119    123    Ljava/lang/IllegalArgumentException;
        //  107    166    169    173    Ljava/lang/IllegalArgumentException;
        //  202    217    217    221    Ljava/lang/IllegalArgumentException;
        //  236    251    251    255    Ljava/lang/IllegalArgumentException;
        //  272    296    296    300    Ljava/lang/IllegalArgumentException;
        //  304    313    313    317    Ljava/lang/IllegalArgumentException;
        //  349    361    364    368    Ljava/lang/IllegalArgumentException;
        //  389    399    402    406    Ljava/lang/IllegalArgumentException;
        //  394    418    421    425    Ljava/lang/IllegalArgumentException;
        //  406    429    429    433    Ljava/lang/IllegalArgumentException;
        //  455    475    475    479    Ljava/lang/IllegalArgumentException;
        //  479    491    494    498    Ljava/lang/IllegalArgumentException;
        //  498    520    523    527    Ljava/lang/IllegalArgumentException;
        //  527    539    542    546    Ljava/lang/IllegalArgumentException;
        //  569    590    593    597    Ljava/lang/IllegalArgumentException;
        //  674    689    692    696    Ljava/lang/IllegalArgumentException;
        //  679    746    749    753    Ljava/lang/IllegalArgumentException;
        //  753    765    768    772    Ljava/lang/IllegalArgumentException;
        //  789    802    805    809    Ljava/lang/IllegalArgumentException;
        //  794    816    819    823    Ljava/lang/IllegalArgumentException;
        //  833    850    853    857    Ljava/lang/IllegalArgumentException;
        //  843    864    867    871    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0406:
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
    
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        if (this.a()) {
            final Document document = insertionContext.getDocument();
            final String insertion = this.getInsertion(insertionContext, lookupElement);
            document.replaceString(insertionContext.getStartOffset(), insertionContext.getTailOffset(), (CharSequence)insertion);
            CallableInsertUtils.moveCaretToCallableBody(insertionContext);
            OCChangeUtil.reformatTextIfNotInjected(insertionContext.getFile(), insertionContext.getStartOffset(), insertionContext.getStartOffset() + insertion.length());
        }
        else {
            this.handleCall(insertionContext, lookupElement);
        }
    }
    
    public void handleCall(final InsertionContext p0, final LookupElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getDocument:()Lcom/intellij/openapi/editor/Document;
        //     4: astore_3       
        //     5: aload_1        
        //     6: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getEditor:()Lcom/intellij/openapi/editor/Editor;
        //     9: astore          4
        //    11: aload_0        
        //    12: aload_1        
        //    13: aload_2        
        //    14: invokevirtual   com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.getInsertion:(Lcom/intellij/codeInsight/completion/InsertionContext;Lcom/intellij/codeInsight/lookup/LookupElement;)Ljava/lang/String;
        //    17: astore          5
        //    19: aload_3        
        //    20: aload_1        
        //    21: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getStartOffset:()I
        //    24: aload_1        
        //    25: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getTailOffset:()I
        //    28: aload           5
        //    30: invokeinterface com/intellij/openapi/editor/Document.replaceString:(IILjava/lang/CharSequence;)V
        //    35: aload_1        
        //    36: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getProject:()Lcom/intellij/openapi/project/Project;
        //    39: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //    42: astore          6
        //    44: aload           6
        //    46: aload_3        
        //    47: invokevirtual   com/intellij/psi/PsiDocumentManager.commitDocument:(Lcom/intellij/openapi/editor/Document;)V
        //    50: aload_0        
        //    51: aload_1        
        //    52: aload_2        
        //    53: invokevirtual   com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.postInsertionHook:(Lcom/intellij/codeInsight/completion/InsertionContext;Lcom/intellij/codeInsight/lookup/LookupElement;)V
        //    56: aload_1        
        //    57: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getProject:()Lcom/intellij/openapi/project/Project;
        //    60: invokestatic    com/intellij/codeInsight/folding/CodeFoldingManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/codeInsight/folding/CodeFoldingManager;
        //    63: astore          7
        //    65: aload           7
        //    67: aload           4
        //    69: invokevirtual   com/intellij/codeInsight/folding/CodeFoldingManager.updateFoldRegions:(Lcom/intellij/openapi/editor/Editor;)V
        //    72: aload_3        
        //    73: invokeinterface com/intellij/openapi/editor/Document.getText:()Ljava/lang/String;
        //    78: astore          8
        //    80: aload           8
        //    82: ldc             "<#"
        //    84: aload_1        
        //    85: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getStartOffset:()I
        //    88: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;I)I
        //    91: istore          9
        //    93: aload           8
        //    95: ldc             "#>"
        //    97: iload           9
        //    99: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;I)I
        //   102: istore          10
        //   104: aload           4
        //   106: invokeinterface com/intellij/openapi/editor/Editor.getSelectionModel:()Lcom/intellij/openapi/editor/SelectionModel;
        //   111: astore          11
        //   113: iload           9
        //   115: iflt            166
        //   118: iload           10
        //   120: iflt            166
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: iload           9
        //   132: aload_1        
        //   133: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getTailOffset:()I
        //   136: if_icmpge       166
        //   139: goto            146
        //   142: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: aload           11
        //   148: iload           9
        //   150: iload           10
        //   152: iconst_2       
        //   153: iadd           
        //   154: invokeinterface com/intellij/openapi/editor/SelectionModel.setSelection:(II)V
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: aload_1        
        //   167: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getTailOffset:()I
        //   170: istore          12
        //   172: iconst_0       
        //   173: istore          13
        //   175: iload           12
        //   177: aload_3        
        //   178: invokeinterface com/intellij/openapi/editor/Document.getTextLength:()I
        //   183: if_icmpge       233
        //   186: aload           8
        //   188: iload           12
        //   190: invokevirtual   java/lang/String.charAt:(I)C
        //   193: istore          13
        //   195: iload           13
        //   197: invokestatic    java/lang/Character.isSpaceChar:(C)Z
        //   200: ifeq            233
        //   203: iload           13
        //   205: bipush          10
        //   207: if_icmpne       224
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   216: athrow         
        //   217: goto            233
        //   220: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   223: athrow         
        //   224: iinc            12, 1
        //   227: iconst_0       
        //   228: istore          13
        //   230: goto            175
        //   233: iload           13
        //   235: bipush          93
        //   237: if_icmpne       301
        //   240: aload_1        
        //   241: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getFile:()Lcom/intellij/psi/PsiFile;
        //   244: astore          14
        //   246: aload           14
        //   248: aload_1        
        //   249: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getStartOffset:()I
        //   252: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.getSendMessage:(Lcom/intellij/psi/PsiFile;I)Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   255: astore          15
        //   257: aload           14
        //   259: iload           12
        //   261: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.getSendMessage:(Lcom/intellij/psi/PsiFile;I)Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   264: astore          16
        //   266: aload           15
        //   268: ifnull          301
        //   271: aload           15
        //   273: aload           16
        //   275: if_acmpne       301
        //   278: goto            285
        //   281: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   284: athrow         
        //   285: iinc            12, 1
        //   288: aload_1        
        //   289: iload           12
        //   291: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.setTailOffset:(I)V
        //   294: goto            301
        //   297: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   300: athrow         
        //   301: aload_1        
        //   302: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getFile:()Lcom/intellij/psi/PsiFile;
        //   305: aload_1        
        //   306: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getStartOffset:()I
        //   309: iload           12
        //   311: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.reformatTextIfNotInjected:(Lcom/intellij/psi/PsiFile;II)V
        //   314: iload           9
        //   316: iflt            374
        //   319: iload           10
        //   321: iflt            374
        //   324: goto            331
        //   327: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   330: athrow         
        //   331: aload           11
        //   333: invokeinterface com/intellij/openapi/editor/SelectionModel.hasSelection:()Z
        //   338: ifeq            374
        //   341: goto            348
        //   344: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   347: athrow         
        //   348: aload           4
        //   350: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   355: aload           11
        //   357: invokeinterface com/intellij/openapi/editor/SelectionModel.getSelectionStart:()I
        //   362: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //   367: goto            390
        //   370: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   373: athrow         
        //   374: aload           4
        //   376: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   381: aload_1        
        //   382: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getTailOffset:()I
        //   385: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //   390: aload_1        
        //   391: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getFile:()Lcom/intellij/psi/PsiFile;
        //   394: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   397: aload_0        
        //   398: getfield        com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.mySymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   401: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.isSymbolImported:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   404: ifne            433
        //   407: aload_1        
        //   408: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getEditor:()Lcom/intellij/openapi/editor/Editor;
        //   411: aload_1        
        //   412: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getFile:()Lcom/intellij/psi/PsiFile;
        //   415: aload_1        
        //   416: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getStartOffset:()I
        //   419: aload_0        
        //   420: getfield        com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.mySymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   423: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.fixSymbolAtOffset:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;ILcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   426: goto            433
        //   429: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   432: athrow         
        //   433: aload_1        
        //   434: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getCompletionChar:()C
        //   437: bipush          40
        //   439: if_icmpne       459
        //   442: aload           5
        //   444: ldc             ")"
        //   446: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   449: ifne            475
        //   452: goto            459
        //   455: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   458: athrow         
        //   459: aload_1        
        //   460: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getCompletionChar:()C
        //   463: bipush          93
        //   465: if_icmpne       487
        //   468: goto            475
        //   471: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   474: athrow         
        //   475: aload_1        
        //   476: iconst_0       
        //   477: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.setAddCompletionChar:(Z)V
        //   480: goto            487
        //   483: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   486: athrow         
        //   487: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  113    123    126    130    Ljava/lang/IllegalArgumentException;
        //  118    139    142    146    Ljava/lang/IllegalArgumentException;
        //  130    159    162    166    Ljava/lang/IllegalArgumentException;
        //  195    210    213    217    Ljava/lang/IllegalArgumentException;
        //  203    220    220    224    Ljava/lang/IllegalArgumentException;
        //  266    278    281    285    Ljava/lang/IllegalArgumentException;
        //  271    294    297    301    Ljava/lang/IllegalArgumentException;
        //  301    324    327    331    Ljava/lang/IllegalArgumentException;
        //  319    341    344    348    Ljava/lang/IllegalArgumentException;
        //  331    370    370    374    Ljava/lang/IllegalArgumentException;
        //  390    426    429    433    Ljava/lang/IllegalArgumentException;
        //  433    452    455    459    Ljava/lang/IllegalArgumentException;
        //  442    468    471    475    Ljava/lang/IllegalArgumentException;
        //  459    480    483    487    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0130:
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
    
    protected void postInsertionHook(final InsertionContext p0, final LookupElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getDocument:()Lcom/intellij/openapi/editor/Document;
        //     4: astore_3       
        //     5: aload_1        
        //     6: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getFile:()Lcom/intellij/psi/PsiFile;
        //     9: astore          4
        //    11: getstatic       com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor.CONTEXT:Lcom/intellij/openapi/util/Key;
        //    14: aload_2        
        //    15: invokevirtual   com/intellij/openapi/util/Key.get:(Lcom/intellij/openapi/util/UserDataHolder;)Ljava/lang/Object;
        //    18: checkcast       Lcom/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$Context;
        //    21: astore          5
        //    23: aload           5
        //    25: ifnonnull       36
        //    28: aconst_null    
        //    29: goto            41
        //    32: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    35: athrow         
        //    36: aload           5
        //    38: invokevirtual   com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$Context.getReceiver:()Lcom/intellij/psi/PsiElement;
        //    41: astore          6
        //    43: iconst_m1      
        //    44: istore          7
        //    46: aload_0        
        //    47: getfield        com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.myContextExpression:Lcom/intellij/psi/PsiElement;
        //    50: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    53: ifeq            150
        //    56: aload_0        
        //    57: getfield        com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.mySymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    60: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isGetter:()Z
        //    65: ifeq            96
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: aload_1        
        //    76: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getCompletionChar:()C
        //    79: bipush          93
        //    81: if_icmpeq       96
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: return         
        //    92: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: aload_0        
        //    97: getfield        com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.myContextExpression:Lcom/intellij/psi/PsiElement;
        //   100: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   103: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifyingToken:()Lcom/intellij/lang/ASTNode;
        //   108: invokeinterface com/intellij/lang/ASTNode.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   113: astore          8
        //   115: aload_3        
        //   116: aload           8
        //   118: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   121: aload           8
        //   123: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   126: ldc             " "
        //   128: invokeinterface com/intellij/openapi/editor/Document.replaceString:(IILjava/lang/CharSequence;)V
        //   133: aload_0        
        //   134: getfield        com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.myContextExpression:Lcom/intellij/psi/PsiElement;
        //   137: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   142: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   145: istore          7
        //   147: goto            459
        //   150: aload           6
        //   152: ifnull          459
        //   155: aload           6
        //   157: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getPrevSignificantSibling:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   160: astore          8
        //   162: aload           8
        //   164: ifnull          212
        //   167: aload           8
        //   169: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   174: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   179: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   182: if_acmpne       212
        //   185: goto            192
        //   188: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   191: athrow         
        //   192: aload           8
        //   194: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   199: instanceof      Lcom/jetbrains/cidr/lang/psi/OCNSArrayLiteral;
        //   202: ifeq            459
        //   205: goto            212
        //   208: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   211: athrow         
        //   212: aload           6
        //   214: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   219: astore          9
        //   221: aload_3        
        //   222: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //   227: astore          10
        //   229: aload           10
        //   231: aload_1        
        //   232: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getStartOffset:()I
        //   235: iconst_1       
        //   236: isub           
        //   237: ldc             " \t\n"
        //   239: invokestatic    com/intellij/util/text/CharArrayUtil.shiftBackward:(Ljava/lang/CharSequence;ILjava/lang/String;)I
        //   242: istore          11
        //   244: aload           6
        //   246: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //   249: ifeq            261
        //   252: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //   254: goto            263
        //   257: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   260: athrow         
        //   261: ldc             Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;.class
        //   263: astore          12
        //   265: aload           4
        //   267: iload           11
        //   269: ldc             Lcom/jetbrains/cidr/lang/psi/OCExpression;.class
        //   271: iconst_0       
        //   272: iconst_1       
        //   273: anewarray       Ljava/lang/Class;
        //   276: dup            
        //   277: iconst_0       
        //   278: aload           12
        //   280: aastore        
        //   281: invokestatic    com/intellij/psi/util/PsiTreeUtil.findElementOfClassAtOffsetWithStopSet:(Lcom/intellij/psi/PsiFile;ILjava/lang/Class;Z[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   284: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   287: astore          13
        //   289: aload           9
        //   291: invokevirtual   com/intellij/openapi/util/TextRange.isEmpty:()Z
        //   294: ifne            369
        //   297: aload           13
        //   299: ifnull          369
        //   302: goto            309
        //   305: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   308: athrow         
        //   309: aload           13
        //   311: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   316: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   319: ifeq            369
        //   322: goto            329
        //   325: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   328: athrow         
        //   329: aload           13
        //   331: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   336: invokevirtual   com/intellij/openapi/util/TextRange.getLength:()I
        //   339: aload           9
        //   341: invokevirtual   com/intellij/openapi/util/TextRange.getLength:()I
        //   344: if_icmpge       369
        //   347: goto            354
        //   350: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   353: athrow         
        //   354: aload           13
        //   356: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   361: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   364: astore          13
        //   366: goto            289
        //   369: aload           13
        //   371: ifnonnull       398
        //   374: aload           4
        //   376: iload           11
        //   378: ldc             Lcom/jetbrains/cidr/lang/psi/OCMacroCall;.class
        //   380: iconst_0       
        //   381: iconst_1       
        //   382: anewarray       Ljava/lang/Class;
        //   385: dup            
        //   386: iconst_0       
        //   387: aload           12
        //   389: aastore        
        //   390: invokestatic    com/intellij/psi/util/PsiTreeUtil.findElementOfClassAtOffsetWithStopSet:(Lcom/intellij/psi/PsiFile;ILjava/lang/Class;Z[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   393: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   396: astore          13
        //   398: aload           13
        //   400: ifnull          418
        //   403: aload           13
        //   405: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   410: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   413: istore          7
        //   415: goto            459
        //   418: aload           4
        //   420: iload           11
        //   422: ldc             Lcom/jetbrains/cidr/lang/psi/OCTypeElement;.class
        //   424: iconst_0       
        //   425: iconst_1       
        //   426: anewarray       Ljava/lang/Class;
        //   429: dup            
        //   430: iconst_0       
        //   431: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //   433: aastore        
        //   434: invokestatic    com/intellij/psi/util/PsiTreeUtil.findElementOfClassAtOffsetWithStopSet:(Lcom/intellij/psi/PsiFile;ILjava/lang/Class;Z[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   437: checkcast       Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   440: astore          14
        //   442: aload           14
        //   444: ifnull          459
        //   447: aload           14
        //   449: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   454: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   457: istore          7
        //   459: aload_1        
        //   460: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   463: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   466: astore          8
        //   468: iload           7
        //   470: iflt            509
        //   473: aload_3        
        //   474: iload           7
        //   476: ldc             "["
        //   478: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   483: aload           8
        //   485: aload_3        
        //   486: invokevirtual   com/intellij/psi/PsiDocumentManager.commitDocument:(Lcom/intellij/openapi/editor/Document;)V
        //   489: aload_1        
        //   490: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getFile:()Lcom/intellij/psi/PsiFile;
        //   493: iload           7
        //   495: iload           7
        //   497: iconst_2       
        //   498: iadd           
        //   499: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.reformatTextIfNotInjected:(Lcom/intellij/psi/PsiFile;II)V
        //   502: goto            509
        //   505: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   508: athrow         
        //   509: aload_3        
        //   510: new             Lcom/intellij/openapi/util/TextRange;
        //   513: dup            
        //   514: aload_1        
        //   515: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getStartOffset:()I
        //   518: aload_1        
        //   519: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getTailOffset:()I
        //   522: invokespecial   com/intellij/openapi/util/TextRange.<init>:(II)V
        //   525: invokeinterface com/intellij/openapi/editor/Document.getText:(Lcom/intellij/openapi/util/TextRange;)Ljava/lang/String;
        //   530: astore          9
        //   532: aload           9
        //   534: ldc             "]"
        //   536: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   539: ifne            604
        //   542: aload_1        
        //   543: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getTailOffset:()I
        //   546: istore          10
        //   548: iload           7
        //   550: ifge            570
        //   553: aload           4
        //   555: iload           10
        //   557: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Lcom/intellij/psi/PsiFile;I)Z
        //   560: ifne            604
        //   563: goto            570
        //   566: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   569: athrow         
        //   570: aload_3        
        //   571: iload           10
        //   573: ldc             "]"
        //   575: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   580: aload           8
        //   582: aload_3        
        //   583: invokevirtual   com/intellij/psi/PsiDocumentManager.commitDocument:(Lcom/intellij/openapi/editor/Document;)V
        //   586: aload           4
        //   588: iload           10
        //   590: iload           10
        //   592: iconst_2       
        //   593: iadd           
        //   594: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.reformatTextIfNotInjected:(Lcom/intellij/psi/PsiFile;II)V
        //   597: goto            604
        //   600: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   603: athrow         
        //   604: aload           8
        //   606: aload_3        
        //   607: invokevirtual   com/intellij/psi/PsiDocumentManager.commitDocument:(Lcom/intellij/openapi/editor/Document;)V
        //   610: aload           4
        //   612: aload_1        
        //   613: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getStartOffset:()I
        //   616: ldc             Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;.class
        //   618: iconst_0       
        //   619: iconst_1       
        //   620: anewarray       Ljava/lang/Class;
        //   623: dup            
        //   624: iconst_0       
        //   625: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //   627: aastore        
        //   628: invokestatic    com/intellij/psi/util/PsiTreeUtil.findElementOfClassAtOffsetWithStopSet:(Lcom/intellij/psi/PsiFile;ILjava/lang/Class;Z[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   631: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   634: astore          10
        //   636: aload           10
        //   638: ifnull          766
        //   641: aload_0        
        //   642: getfield        com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.mySymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   645: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   650: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   653: ifeq            766
        //   656: goto            663
        //   659: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   662: athrow         
        //   663: aload           10
        //   665: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   670: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   673: ifeq            766
        //   676: goto            683
        //   679: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   682: athrow         
        //   683: aload           8
        //   685: aload_3        
        //   686: invokevirtual   com/intellij/psi/PsiDocumentManager.commitDocument:(Lcom/intellij/openapi/editor/Document;)V
        //   689: aload           10
        //   691: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   696: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   699: astore          11
        //   701: aload           11
        //   703: ifnull          766
        //   706: aload           11
        //   708: invokeinterface com/jetbrains/cidr/lang/psi/OCExpressionStatement.getNode:()Lcom/intellij/lang/ASTNode;
        //   713: invokeinterface com/intellij/lang/ASTNode.getLastChildNode:()Lcom/intellij/lang/ASTNode;
        //   718: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   723: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   726: if_acmpeq       766
        //   729: goto            736
        //   732: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   735: athrow         
        //   736: aload           10
        //   738: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   743: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   746: istore          12
        //   748: aload_3        
        //   749: iload           12
        //   751: ldc             ";"
        //   753: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   758: aload_1        
        //   759: iload           12
        //   761: iconst_1       
        //   762: iadd           
        //   763: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.setTailOffset:(I)V
        //   766: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  23     32     32     36     Ljava/lang/IllegalArgumentException;
        //  46     68     71     75     Ljava/lang/IllegalArgumentException;
        //  56     84     87     91     Ljava/lang/IllegalArgumentException;
        //  75     92     92     96     Ljava/lang/IllegalArgumentException;
        //  162    185    188    192    Ljava/lang/IllegalArgumentException;
        //  167    205    208    212    Ljava/lang/IllegalArgumentException;
        //  244    257    257    261    Ljava/lang/IllegalArgumentException;
        //  289    302    305    309    Ljava/lang/IllegalArgumentException;
        //  297    322    325    329    Ljava/lang/IllegalArgumentException;
        //  309    347    350    354    Ljava/lang/IllegalArgumentException;
        //  468    502    505    509    Ljava/lang/IllegalArgumentException;
        //  548    563    566    570    Ljava/lang/IllegalArgumentException;
        //  553    597    600    604    Ljava/lang/IllegalArgumentException;
        //  636    656    659    663    Ljava/lang/IllegalArgumentException;
        //  641    676    679    683    Ljava/lang/IllegalArgumentException;
        //  701    729    732    736    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0075:
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
    
    private static boolean a(final PsiFile psiFile, final int n) {
        PsiElement psiElement = psiFile.findElementAt(n);
        while (true) {
            try {
                if (psiElement == null || !OCTokenTypes.WHITESPACES.contains(psiElement.getNode().getElementType())) {
                    break;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            psiElement = psiElement.getNextSibling();
        }
        Label_0077: {
            try {
                if (psiElement == null) {
                    return false;
                }
                final PsiElement psiElement2 = psiElement;
                final ASTNode astNode = psiElement2.getNode();
                final IElementType elementType = astNode.getElementType();
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.RBRACKET;
                if (elementType == ocPunctuatorElementType) {
                    break Label_0077;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final ASTNode astNode = psiElement2.getNode();
                final IElementType elementType = astNode.getElementType();
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.RBRACKET;
                if (elementType == ocPunctuatorElementType) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    private boolean a() {
        try {
            if (this.myContextExpression == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement parent = this.myContextExpression.getParent();
        Label_0049: {
            try {
                if (!(parent instanceof OCMethodSelectorPart)) {
                    return false;
                }
                final PsiElement psiElement = parent;
                final PsiElement psiElement2 = psiElement.getParent();
                final boolean b = psiElement2 instanceof OCMethod;
                if (b) {
                    break Label_0049;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final PsiElement psiElement = parent;
                final PsiElement psiElement2 = psiElement.getParent();
                final boolean b = psiElement2 instanceof OCMethod;
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    protected static OCSendMessageExpression getSendMessage(final PsiFile psiFile, final int n) {
        return (OCSendMessageExpression)PsiTreeUtil.getParentOfType(psiFile.findElementAt(n), (Class)OCSendMessageExpression.class, true, new Class[] { OCCallable.class, OCQualifiedExpression.class });
    }
    
    protected static void appendParameterName(@NotNull final StringBuilder sb, @NotNull final PsiFile psiFile, @NotNull final OCDeclaratorSymbol ocDeclaratorSymbol) {
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "insertion", "com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler", "appendParameterName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler", "appendParameterName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocDeclaratorSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "p", "com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler", "appendParameterName"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            sb.append("(").append(ocDeclaratorSymbol.getType().getBestNameInContext((PsiElement)psiFile, OCElementUtil.getTypeTextWithModifiers(ocDeclaratorSymbol))).append(')');
            if (!ocDeclaratorSymbol.isUnnamed()) {
                sb.append(ocDeclaratorSymbol.getName());
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
