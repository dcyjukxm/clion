// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

class OCSmartCompletionContributor$1 extends OCCompletionProvider {
    @Override
    protected void addCompletions(final String p0, @NotNull final OCCompletionParameters p1, final ProcessingContext p2, final CompletionResultSet p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "parameters"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "addCompletions"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: invokevirtual   com/jetbrains/cidr/lang/editor/completion/OCCompletionParameters.getPosition:()Lcom/intellij/psi/PsiElement;
        //    48: astore          5
        //    50: aload           5
        //    52: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    57: astore          6
        //    59: aload           6
        //    61: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    66: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    69: ifne            85
        //    72: aload_2        
        //    73: aload           4
        //    75: aload           6
        //    77: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor.addMethodCompletionsIfAppropriate:(Lcom/jetbrains/cidr/lang/editor/completion/OCCompletionParameters;Lcom/intellij/codeInsight/completion/CompletionResultSet;Lcom/intellij/psi/PsiElement;)V
        //    80: return         
        //    81: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: aload           6
        //    87: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    92: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    95: astore          9
        //    97: aload           9
        //    99: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   104: astore          10
        //   106: aload           10
        //   108: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   111: ifeq            186
        //   114: aload           10
        //   116: invokeinterface com/intellij/psi/PsiElement.getChildren:()[Lcom/intellij/psi/PsiElement;
        //   121: astore          11
        //   123: aload           11
        //   125: arraylength    
        //   126: iconst_2       
        //   127: if_icmpne       176
        //   130: aload           11
        //   132: iconst_0       
        //   133: aaload         
        //   134: aload           9
        //   136: if_acmpne       176
        //   139: goto            146
        //   142: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: aload           11
        //   148: iconst_1       
        //   149: aaload         
        //   150: instanceof      Lcom/intellij/psi/PsiErrorElement;
        //   153: ifeq            176
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: aload           10
        //   165: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   168: astore          8
        //   170: iconst_1       
        //   171: istore          7
        //   173: goto            183
        //   176: aload           9
        //   178: astore          8
        //   180: iconst_0       
        //   181: istore          7
        //   183: goto            193
        //   186: aload           9
        //   188: astore          8
        //   190: iconst_0       
        //   191: istore          7
        //   193: aload           10
        //   195: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //   198: ifeq            249
        //   201: aload           10
        //   203: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   208: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //   211: ifne            241
        //   214: goto            221
        //   217: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   220: athrow         
        //   221: aload           10
        //   223: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   228: instanceof      Lcom/jetbrains/cidr/lang/psi/OCWhileStatement;
        //   231: ifeq            249
        //   234: goto            241
        //   237: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   240: athrow         
        //   241: iconst_1       
        //   242: goto            250
        //   245: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: iconst_0       
        //   250: istore          11
        //   252: aload           8
        //   254: ldc             Lcom/jetbrains/cidr/lang/psi/OCDeclarator;.class
        //   256: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   259: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   262: astore          12
        //   264: aload           12
        //   266: ifnull          283
        //   269: aload           12
        //   271: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   276: goto            284
        //   279: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   282: athrow         
        //   283: aconst_null    
        //   284: astore          13
        //   286: aload           8
        //   288: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   293: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   296: astore          14
        //   298: aload           8
        //   300: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil.getExpectedType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   303: aload           14
        //   305: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   308: astore          15
        //   310: aload           15
        //   312: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   315: if_acmpeq       1285
        //   318: aload           8
        //   320: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   325: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   328: ifeq            447
        //   331: goto            338
        //   334: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   337: athrow         
        //   338: aload           5
        //   340: aload_2        
        //   341: invokevirtual   com/jetbrains/cidr/lang/editor/completion/OCCompletionParameters.getRealPosition:()Lcom/intellij/psi/PsiElement;
        //   344: if_acmpne       447
        //   347: goto            354
        //   350: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   353: athrow         
        //   354: aload           15
        //   356: aload           8
        //   358: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   361: invokestatic    com/intellij/codeInsight/lookup/LookupElementBuilder.create:(Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //   364: astore          16
        //   366: aload           8
        //   368: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   373: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //   378: astore          17
        //   380: aload           17
        //   382: ifnull          422
        //   385: aload           17
        //   387: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   392: ldc             ")"
        //   394: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   397: ifne            422
        //   400: goto            407
        //   403: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   406: athrow         
        //   407: aload           16
        //   409: new             Lcom/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1$1;
        //   412: dup            
        //   413: aload_0        
        //   414: invokespecial   com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1$1.<init>:(Lcom/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1;)V
        //   417: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withInsertHandler:(Lcom/intellij/codeInsight/completion/InsertHandler;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //   420: astore          16
        //   422: aload           4
        //   424: aload           16
        //   426: getstatic       com/jetbrains/cidr/lang/editor/completion/OCCompletionPriority.HIGH_PRIORITY:Lcom/jetbrains/cidr/lang/editor/completion/OCCompletionPriority;
        //   429: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCCompletionPriority.elementWithPriority:(Lcom/intellij/codeInsight/lookup/LookupElement;Lcom/jetbrains/cidr/lang/editor/completion/OCCompletionPriority;)Lcom/intellij/codeInsight/lookup/LookupElement;
        //   432: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
        //   435: aload_1        
        //   436: invokevirtual   java/lang/String.isEmpty:()Z
        //   439: ifeq            447
        //   442: return         
        //   443: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   446: athrow         
        //   447: iload           7
        //   449: ifne            770
        //   452: aload           6
        //   454: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   459: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsObjectLiterals:(Lcom/intellij/psi/PsiFile;)Z
        //   462: istore          16
        //   464: ldc             "NSArray"
        //   466: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.fromText:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   469: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   472: astore          17
        //   474: ldc             "NSDictionary"
        //   476: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.fromText:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   479: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   482: astore          18
        //   484: iload           16
        //   486: ifeq            533
        //   489: aload           15
        //   491: aload           17
        //   493: aload           6
        //   495: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   498: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   501: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.OK:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   504: if_acmpne       533
        //   507: goto            514
        //   510: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   513: athrow         
        //   514: aload           4
        //   516: ldc             "@[ ]"
        //   518: ldc             "@[<caret>]"
        //   520: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor.access$000:(Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElement;
        //   523: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
        //   526: goto            770
        //   529: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   532: athrow         
        //   533: iload           16
        //   535: ifeq            582
        //   538: aload           15
        //   540: aload           18
        //   542: aload           6
        //   544: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   547: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   550: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.OK:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   553: if_acmpne       582
        //   556: goto            563
        //   559: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   562: athrow         
        //   563: aload           4
        //   565: ldc             "@{ }"
        //   567: ldc             "@{<caret>}"
        //   569: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor.access$000:(Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElement;
        //   572: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
        //   575: goto            770
        //   578: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   581: athrow         
        //   582: aload           15
        //   584: getstatic       com/jetbrains/cidr/lang/types/OCIntType.BOOL:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   587: aload           14
        //   589: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equalsWithAliasName:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   592: ifeq            675
        //   595: aload           14
        //   597: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   602: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isObjC:()Z
        //   607: ifeq            648
        //   610: goto            617
        //   613: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   616: athrow         
        //   617: aload           4
        //   619: ldc             "YES"
        //   621: ldc             "YES"
        //   623: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor.access$000:(Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElement;
        //   626: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
        //   629: aload           4
        //   631: ldc             "NO"
        //   633: ldc             "NO"
        //   635: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor.access$000:(Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElement;
        //   638: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
        //   641: goto            770
        //   644: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   647: athrow         
        //   648: aload           4
        //   650: ldc             "TRUE"
        //   652: ldc             "TRUE"
        //   654: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor.access$000:(Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElement;
        //   657: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
        //   660: aload           4
        //   662: ldc             "FALSE"
        //   664: ldc             "FALSE"
        //   666: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor.access$000:(Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElement;
        //   669: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
        //   672: goto            770
        //   675: aload           15
        //   677: aload           8
        //   679: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.isBool:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   682: ifeq            716
        //   685: aload           4
        //   687: ldc             "true"
        //   689: ldc             "true"
        //   691: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor.access$000:(Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElement;
        //   694: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
        //   697: aload           4
        //   699: ldc             "false"
        //   701: ldc             "false"
        //   703: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor.access$000:(Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElement;
        //   706: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
        //   709: goto            770
        //   712: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   715: athrow         
        //   716: aload           15
        //   718: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   721: ifeq            743
        //   724: aload           4
        //   726: ldc             "nil"
        //   728: ldc             "nil"
        //   730: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor.access$000:(Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElement;
        //   733: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
        //   736: goto            770
        //   739: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   742: athrow         
        //   743: aload           15
        //   745: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   748: ifeq            770
        //   751: aload           4
        //   753: ldc             "NULL"
        //   755: ldc             "NULL"
        //   757: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor.access$000:(Ljava/lang/String;Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElement;
        //   760: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
        //   763: goto            770
        //   766: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   769: athrow         
        //   770: new             Lcom/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$CompletionBuilder;
        //   773: dup            
        //   774: aload           4
        //   776: aload           8
        //   778: invokespecial   com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$CompletionBuilder.<init>:(Lcom/intellij/codeInsight/completion/CompletionResultSet;Lcom/intellij/psi/PsiElement;)V
        //   781: astore          16
        //   783: iconst_1       
        //   784: newarray        Z
        //   786: dup            
        //   787: iconst_0       
        //   788: iconst_0       
        //   789: bastore        
        //   790: astore          17
        //   792: iload           7
        //   794: ifne            890
        //   797: aload           15
        //   799: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   802: ifeq            890
        //   805: goto            812
        //   808: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   811: athrow         
        //   812: aload           15
        //   814: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   817: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
        //   820: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   825: astore          18
        //   827: aload           18
        //   829: invokeinterface java/util/Iterator.hasNext:()Z
        //   834: ifeq            887
        //   837: aload           18
        //   839: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   844: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   847: astore          19
        //   849: aload           19
        //   851: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   854: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   857: if_acmpne       884
        //   860: aload           19
        //   862: aload           8
        //   864: aload           16
        //   866: aload           17
        //   868: invokedynamic   process:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$CompletionBuilder;[Z)Lcom/intellij/util/Processor;
        //   873: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processFields:(Lcom/intellij/util/Processor;)Z
        //   876: pop            
        //   877: goto            884
        //   880: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   883: athrow         
        //   884: goto            827
        //   887: goto            1185
        //   890: iload           7
        //   892: ifne            938
        //   895: aload           15
        //   897: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   900: ifeq            938
        //   903: goto            910
        //   906: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   909: athrow         
        //   910: aload           15
        //   912: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   915: aload           8
        //   917: aload           16
        //   919: aload           17
        //   921: invokedynamic   process:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$CompletionBuilder;[Z)Lcom/intellij/util/Processor;
        //   926: aload           8
        //   928: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.processGuessedEnumConsts:(Lcom/jetbrains/cidr/lang/types/OCIntType;Lcom/intellij/util/Processor;Lcom/intellij/psi/PsiElement;)V
        //   931: goto            1185
        //   934: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   937: athrow         
        //   938: iload           7
        //   940: ifne            980
        //   943: aload           15
        //   945: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   948: ifeq            980
        //   951: goto            958
        //   954: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   957: athrow         
        //   958: aload           4
        //   960: aload           15
        //   962: checkcast       Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   965: aload           8
        //   967: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor.createBlockTemplateElement:(Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/codeInsight/lookup/LookupElement;
        //   970: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
        //   973: goto            1185
        //   976: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   979: athrow         
        //   980: iload           7
        //   982: ifne            1019
        //   985: aload           15
        //   987: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //   990: ldc             "SEL"
        //   992: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   995: ifeq            1019
        //   998: goto            1005
        //  1001: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1004: athrow         
        //  1005: aload           4
        //  1007: aload           8
        //  1009: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor.addSelectorCompletions:(Lcom/intellij/codeInsight/completion/CompletionResultSet;Lcom/jetbrains/cidr/lang/psi/OCExpression;)V
        //  1012: goto            1185
        //  1015: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1018: athrow         
        //  1019: aload           15
        //  1021: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //  1024: ifeq            1185
        //  1027: aload           15
        //  1029: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1032: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1035: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //  1038: astore          18
        //  1040: aload           18
        //  1042: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //  1045: astore          19
        //  1047: aload           19
        //  1049: ifnull          1185
        //  1052: aload           19
        //  1054: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol.getName:()Ljava/lang/String;
        //  1059: astore          20
        //  1061: new             Lcom/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1$2;
        //  1064: dup            
        //  1065: aload_0        
        //  1066: aload           15
        //  1068: aload           18
        //  1070: aload           14
        //  1072: iload           7
        //  1074: aload           20
        //  1076: aload           8
        //  1078: aload           4
        //  1080: invokespecial   com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1$2.<init>:(Lcom/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCObjectType;Lcom/jetbrains/cidr/lang/psi/OCFile;ZLjava/lang/String;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/codeInsight/completion/CompletionResultSet;)V
        //  1083: astore          21
        //  1085: new             Lcom/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1$3;
        //  1088: dup            
        //  1089: aload_0        
        //  1090: aload           18
        //  1092: aload           14
        //  1094: aload           15
        //  1096: iload           7
        //  1098: aload           20
        //  1100: aload           8
        //  1102: aload           4
        //  1104: invokespecial   com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1$3.<init>:(Lcom/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1;Lcom/jetbrains/cidr/lang/types/OCObjectType;Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/types/OCType;ZLjava/lang/String;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/codeInsight/completion/CompletionResultSet;)V
        //  1107: astore          22
        //  1109: aload           19
        //  1111: astore          23
        //  1113: aload           23
        //  1115: ifnull          1185
        //  1118: aload           23
        //  1120: aconst_null    
        //  1121: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;.class
        //  1123: aload           21
        //  1125: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol.processMembersInAllCategories:(Ljava/lang/String;Ljava/lang/Class;Lcom/intellij/util/Processor;)Z
        //  1130: pop            
        //  1131: aload           23
        //  1133: aconst_null    
        //  1134: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;.class
        //  1136: aload           22
        //  1138: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol.processMembersInAllCategories:(Ljava/lang/String;Ljava/lang/Class;Lcom/intellij/util/Processor;)Z
        //  1143: pop            
        //  1144: aload           23
        //  1146: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol.getSuperType:()Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //  1151: aload           14
        //  1153: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //  1156: astore          24
        //  1158: aload           24
        //  1160: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //  1163: ifeq            1179
        //  1166: aload           24
        //  1168: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //  1171: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //  1174: astore          23
        //  1176: goto            1182
        //  1179: aconst_null    
        //  1180: astore          23
        //  1182: goto            1113
        //  1185: iload           7
        //  1187: ifne            1285
        //  1190: aload           6
        //  1192: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //  1195: ifeq            1285
        //  1198: goto            1205
        //  1201: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1204: athrow         
        //  1205: aload           6
        //  1207: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //  1210: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReferenceResolver.getQualifiedName:(Lcom/jetbrains/cidr/lang/psi/OCNamespaceQualifierOwner;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //  1213: aconst_null    
        //  1214: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.changeName:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //  1217: astore          18
        //  1219: aload           18
        //  1221: aload           6
        //  1223: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.getLocalReference:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference;
        //  1226: astore          19
        //  1228: new             Lcom/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$TypeMatchingCondition;
        //  1231: dup            
        //  1232: aload           14
        //  1234: aload           6
        //  1236: aload           15
        //  1238: aload           17
        //  1240: iconst_0       
        //  1241: baload         
        //  1242: aload           13
        //  1244: iload           11
        //  1246: invokespecial   com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$TypeMatchingCondition.<init>:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;ZLcom/jetbrains/cidr/lang/symbols/OCSymbol;Z)V
        //  1249: astore          20
        //  1251: new             Lcom/intellij/util/FilteringProcessor;
        //  1254: dup            
        //  1255: aload           20
        //  1257: aload           16
        //  1259: invokespecial   com/intellij/util/FilteringProcessor.<init>:(Lcom/intellij/openapi/util/Condition;Lcom/intellij/util/Processor;)V
        //  1262: astore          21
        //  1264: aload           6
        //  1266: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //  1269: aload           21
        //  1271: iconst_0       
        //  1272: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCThisSelfSuperSymbol.processThisSelfSuperInContext:(Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;Lcom/intellij/util/Processor;Z)V
        //  1275: aload           19
        //  1277: aload           21
        //  1279: aload           14
        //  1281: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.processPossibleSymbols:(Lcom/intellij/util/Processor;Lcom/intellij/psi/PsiFile;)Z
        //  1284: pop            
        //  1285: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  59     81     81     85     Ljava/lang/IllegalArgumentException;
        //  123    139    142    146    Ljava/lang/IllegalArgumentException;
        //  130    156    159    163    Ljava/lang/IllegalArgumentException;
        //  193    214    217    221    Ljava/lang/IllegalArgumentException;
        //  201    234    237    241    Ljava/lang/IllegalArgumentException;
        //  221    245    245    249    Ljava/lang/IllegalArgumentException;
        //  264    279    279    283    Ljava/lang/IllegalArgumentException;
        //  310    331    334    338    Ljava/lang/IllegalArgumentException;
        //  318    347    350    354    Ljava/lang/IllegalArgumentException;
        //  380    400    403    407    Ljava/lang/IllegalArgumentException;
        //  422    443    443    447    Ljava/lang/IllegalArgumentException;
        //  484    507    510    514    Ljava/lang/IllegalArgumentException;
        //  489    529    529    533    Ljava/lang/IllegalArgumentException;
        //  533    556    559    563    Ljava/lang/IllegalArgumentException;
        //  538    578    578    582    Ljava/lang/IllegalArgumentException;
        //  582    610    613    617    Ljava/lang/IllegalArgumentException;
        //  595    644    644    648    Ljava/lang/IllegalArgumentException;
        //  675    712    712    716    Ljava/lang/IllegalArgumentException;
        //  716    739    739    743    Ljava/lang/IllegalArgumentException;
        //  743    763    766    770    Ljava/lang/IllegalArgumentException;
        //  792    805    808    812    Ljava/lang/IllegalArgumentException;
        //  849    877    880    884    Ljava/lang/IllegalArgumentException;
        //  890    903    906    910    Ljava/lang/IllegalArgumentException;
        //  895    934    934    938    Ljava/lang/IllegalArgumentException;
        //  938    951    954    958    Ljava/lang/IllegalArgumentException;
        //  943    976    976    980    Ljava/lang/IllegalArgumentException;
        //  980    998    1001   1005   Ljava/lang/IllegalArgumentException;
        //  985    1015   1015   1019   Ljava/lang/IllegalArgumentException;
        //  1185   1198   1201   1205   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0221:
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}