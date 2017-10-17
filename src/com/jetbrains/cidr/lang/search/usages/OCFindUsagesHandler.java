// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages;

import com.intellij.util.CommonProcessors;
import com.intellij.find.findUsages.AbstractFindUsagesDialog;
import com.intellij.find.findUsages.FindUsagesOptions;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.actionSystem.DataContext;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.Collection;
import com.intellij.psi.util.PsiUtilCore;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.application.ApplicationManager;
import java.util.Set;
import com.intellij.util.containers.HashSet;
import com.jetbrains.cidr.lang.symbols.objc.OCSynthesizeSymbol;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.Iterator;
import com.jetbrains.cidr.lang.search.OCStructInheritorsSearch;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.search.OCClassInheritorsSearch;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.find.findUsages.FindUsagesHandler;

public class OCFindUsagesHandler extends FindUsagesHandler
{
    private boolean myDeleteMode;
    
    public OCFindUsagesHandler(@NotNull final PsiElement psiElement) {
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler", "<init>"));
        }
        super(psiElement);
    }
    
    public OCFindUsagesHandler(@NotNull final PsiElement psiElement, final boolean myDeleteMode) {
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler", "<init>"));
        }
        super(psiElement);
        this.myDeleteMode = myDeleteMode;
    }
    
    private String a() {
        try {
            if (this.myDeleteMode) {
                return "delete";
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return "find usages of";
    }
    
    @NotNull
    @Override
    public PsiElement[] getPrimaryElements() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.getPsiElement:()Lcom/intellij/psi/PsiElement;
        //     4: astore_1       
        //     5: aload_1        
        //     6: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //     9: ifeq            25
        //    12: aload_1        
        //    13: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    16: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getSameNamedClass:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    21: astore_2       
        //    22: goto            88
        //    25: aload_1        
        //    26: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //    29: ifeq            45
        //    32: aload_1        
        //    33: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //    36: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    41: astore_2       
        //    42: goto            88
        //    45: aload_0        
        //    46: invokespecial   com/intellij/find/findUsages/FindUsagesHandler.getPrimaryElements:()[Lcom/intellij/psi/PsiElement;
        //    49: dup            
        //    50: ifnonnull       87
        //    53: new             Ljava/lang/IllegalStateException;
        //    56: dup            
        //    57: ldc             "@NotNull method %s.%s must not return null"
        //    59: ldc             2
        //    61: anewarray       Ljava/lang/Object;
        //    64: dup            
        //    65: ldc             0
        //    67: ldc             "com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler"
        //    69: aastore        
        //    70: dup            
        //    71: ldc             1
        //    73: ldc             "getPrimaryElements"
        //    75: aastore        
        //    76: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    79: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    82: athrow         
        //    83: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    86: athrow         
        //    87: areturn        
        //    88: new             Lcom/intellij/util/containers/HashSet;
        //    91: dup            
        //    92: invokespecial   com/intellij/util/containers/HashSet.<init>:()V
        //    95: astore_3       
        //    96: new             Lcom/intellij/util/CommonProcessors$CollectProcessor;
        //    99: dup            
        //   100: aload_3        
        //   101: invokespecial   com/intellij/util/CommonProcessors$CollectProcessor.<init>:(Ljava/util/Collection;)V
        //   104: astore          4
        //   106: aload           4
        //   108: invokedynamic   process:(Lcom/intellij/util/CommonProcessors$CollectProcessor;)Lcom/intellij/util/Processor;
        //   113: astore          5
        //   115: aload_2        
        //   116: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //   119: ifeq            555
        //   122: aload_2        
        //   123: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //   126: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.findSomeAncestor:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;)Lcom/jetbrains/cidr/lang/search/OCSearchUtil$Ancestor;
        //   129: astore          6
        //   131: aload           6
        //   133: ifnull          454
        //   136: aload_2        
        //   137: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   142: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //   145: astore          7
        //   147: new             Ljava/lang/StringBuilder;
        //   150: dup            
        //   151: invokespecial   java/lang/StringBuilder.<init>:()V
        //   154: aload_2        
        //   155: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   160: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   163: ldc             " overrides "
        //   165: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   168: aload           7
        //   170: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   173: ldc             " in "
        //   175: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   178: aload           6
        //   180: invokevirtual   com/jetbrains/cidr/lang/search/OCSearchUtil$Ancestor.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //   183: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   188: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   193: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   196: aload           6
        //   198: invokevirtual   com/jetbrains/cidr/lang/search/OCSearchUtil$Ancestor.isOutOfProject:()Z
        //   201: ifeq            213
        //   204: ldc             " which is out of project"
        //   206: goto            215
        //   209: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   212: athrow         
        //   213: ldc             ""
        //   215: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   218: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   221: astore          8
        //   223: aload_0        
        //   224: getfield        com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.myDeleteMode:Z
        //   227: ifeq            333
        //   230: aload           6
        //   232: invokevirtual   com/jetbrains/cidr/lang/search/OCSearchUtil$Ancestor.isOutOfProject:()Z
        //   235: ifeq            333
        //   238: goto            245
        //   241: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   244: athrow         
        //   245: aload_0        
        //   246: invokevirtual   com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.getProject:()Lcom/intellij/openapi/project/Project;
        //   249: new             Ljava/lang/StringBuilder;
        //   252: dup            
        //   253: invokespecial   java/lang/StringBuilder.<init>:()V
        //   256: aload           8
        //   258: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   261: ldc             ". Do you want to proceed the refactoring?"
        //   263: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   266: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   269: ldc             "Warning"
        //   271: invokestatic    com/intellij/openapi/ui/Messages.getQuestionIcon:()Ljavax/swing/Icon;
        //   274: invokestatic    com/intellij/openapi/ui/Messages.showYesNoDialog:(Lcom/intellij/openapi/project/Project;Ljava/lang/String;Ljava/lang/String;Ljavax/swing/Icon;)I
        //   277: istore          9
        //   279: iload           9
        //   281: ifeq            451
        //   284: getstatic       com/intellij/psi/PsiElement.EMPTY_ARRAY:[Lcom/intellij/psi/PsiElement;
        //   287: dup            
        //   288: ifnonnull       332
        //   291: goto            298
        //   294: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   297: athrow         
        //   298: new             Ljava/lang/IllegalStateException;
        //   301: dup            
        //   302: ldc             "@NotNull method %s.%s must not return null"
        //   304: ldc             2
        //   306: anewarray       Ljava/lang/Object;
        //   309: dup            
        //   310: ldc             0
        //   312: ldc             "com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler"
        //   314: aastore        
        //   315: dup            
        //   316: ldc             1
        //   318: ldc             "getPrimaryElements"
        //   320: aastore        
        //   321: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   324: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   327: athrow         
        //   328: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   331: athrow         
        //   332: areturn        
        //   333: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   336: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   341: ifeq            350
        //   344: iconst_0       
        //   345: istore          9
        //   347: goto            402
        //   350: new             Ljava/lang/StringBuilder;
        //   353: dup            
        //   354: invokespecial   java/lang/StringBuilder.<init>:()V
        //   357: aload           8
        //   359: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   362: ldc             "\n\nDo you want to "
        //   364: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   367: aload_0        
        //   368: invokespecial   com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.a:()Ljava/lang/String;
        //   371: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   374: ldc             " the base "
        //   376: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   379: aload           7
        //   381: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   384: ldc             "?"
        //   386: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   389: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   392: ldc             "Method search"
        //   394: invokestatic    com/intellij/openapi/ui/Messages.getQuestionIcon:()Ljavax/swing/Icon;
        //   397: invokestatic    com/intellij/openapi/ui/Messages.showYesNoDialog:(Ljava/lang/String;Ljava/lang/String;Ljavax/swing/Icon;)I
        //   400: istore          9
        //   402: iload           9
        //   404: ifne            443
        //   407: aload_2        
        //   408: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //   411: aload           5
        //   413: aload_0        
        //   414: getfield        com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.myDeleteMode:Z
        //   417: ifne            435
        //   420: goto            427
        //   423: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   426: athrow         
        //   427: iconst_1       
        //   428: goto            436
        //   431: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   434: athrow         
        //   435: iconst_0       
        //   436: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.processMemberAncestors:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;Lcom/intellij/util/Processor;Z)Z
        //   439: pop            
        //   440: goto            451
        //   443: aload_3        
        //   444: aload_1        
        //   445: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   450: pop            
        //   451: goto            462
        //   454: aload_3        
        //   455: aload_1        
        //   456: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   461: pop            
        //   462: aload_0        
        //   463: getfield        com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.myDeleteMode:Z
        //   466: ifeq            552
        //   469: iconst_0       
        //   470: istore          7
        //   472: aload_2        
        //   473: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   476: ifeq            527
        //   479: aload_2        
        //   480: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   483: astore          8
        //   485: aload           8
        //   487: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   492: astore          9
        //   494: aload           9
        //   496: ifnull          524
        //   499: aload           9
        //   501: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   506: ifnull          524
        //   509: goto            516
        //   512: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   515: athrow         
        //   516: iconst_1       
        //   517: goto            525
        //   520: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   523: athrow         
        //   524: iconst_0       
        //   525: istore          7
        //   527: iload           7
        //   529: ifne            552
        //   532: aload_2        
        //   533: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //   536: aload           5
        //   538: iconst_0       
        //   539: iconst_1       
        //   540: iconst_1       
        //   541: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.processMembersHierarchy:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;Lcom/intellij/util/Processor;ZZZ)Z
        //   544: pop            
        //   545: goto            552
        //   548: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   551: athrow         
        //   552: goto            620
        //   555: aload_2        
        //   556: ifnull          620
        //   559: aload_0        
        //   560: getfield        com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.myDeleteMode:Z
        //   563: ifeq            613
        //   566: goto            573
        //   569: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   572: athrow         
        //   573: aload_1        
        //   574: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //   577: ifeq            601
        //   580: goto            587
        //   583: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   586: athrow         
        //   587: aload           4
        //   589: aload_1        
        //   590: invokevirtual   com/intellij/util/CommonProcessors$CollectProcessor.process:(Ljava/lang/Object;)Z
        //   593: pop            
        //   594: goto            620
        //   597: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   600: athrow         
        //   601: aload_2        
        //   602: aload           5
        //   604: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.processSameSymbols:(Lcom/intellij/util/Processor;)Z
        //   609: pop            
        //   610: goto            620
        //   613: aload           4
        //   615: aload_1        
        //   616: invokevirtual   com/intellij/util/CommonProcessors$CollectProcessor.process:(Ljava/lang/Object;)Z
        //   619: pop            
        //   620: aload_2        
        //   621: ifnull          753
        //   624: aload_2        
        //   625: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getDefinitionSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   630: astore_2       
        //   631: aload_0        
        //   632: getfield        com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.myDeleteMode:Z
        //   635: ifeq            686
        //   638: aload_2        
        //   639: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   642: ifeq            686
        //   645: goto            652
        //   648: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   651: athrow         
        //   652: aload_3        
        //   653: invokeinterface java/util/Set.clear:()V
        //   658: aload_2        
        //   659: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   662: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //   667: astore          6
        //   669: aload_2        
        //   670: aload           6
        //   672: aload_3        
        //   673: aload           4
        //   675: invokedynamic   process:(Ljava/lang/String;Ljava/util/Set;Lcom/intellij/util/CommonProcessors$CollectProcessor;)Lcom/intellij/util/Processor;
        //   680: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.processSameSymbols:(Lcom/intellij/util/Processor;)Z
        //   685: pop            
        //   686: aload_3        
        //   687: invokeinterface java/util/Set.isEmpty:()Z
        //   692: ifeq            710
        //   695: aload_3        
        //   696: aload_1        
        //   697: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   702: pop            
        //   703: goto            710
        //   706: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   709: athrow         
        //   710: aload_3        
        //   711: invokestatic    com/intellij/psi/util/PsiUtilCore.toPsiElementArray:(Ljava/util/Collection;)[Lcom/intellij/psi/PsiElement;
        //   714: dup            
        //   715: ifnonnull       752
        //   718: new             Ljava/lang/IllegalStateException;
        //   721: dup            
        //   722: ldc             "@NotNull method %s.%s must not return null"
        //   724: ldc             2
        //   726: anewarray       Ljava/lang/Object;
        //   729: dup            
        //   730: ldc             0
        //   732: ldc             "com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler"
        //   734: aastore        
        //   735: dup            
        //   736: ldc             1
        //   738: ldc             "getPrimaryElements"
        //   740: aastore        
        //   741: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   744: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   747: athrow         
        //   748: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   751: athrow         
        //   752: areturn        
        //   753: aload_0        
        //   754: invokespecial   com/intellij/find/findUsages/FindUsagesHandler.getPrimaryElements:()[Lcom/intellij/psi/PsiElement;
        //   757: dup            
        //   758: ifnonnull       795
        //   761: new             Ljava/lang/IllegalStateException;
        //   764: dup            
        //   765: ldc             "@NotNull method %s.%s must not return null"
        //   767: ldc             2
        //   769: anewarray       Ljava/lang/Object;
        //   772: dup            
        //   773: ldc             0
        //   775: ldc             "com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler"
        //   777: aastore        
        //   778: dup            
        //   779: ldc             1
        //   781: ldc             "getPrimaryElements"
        //   783: aastore        
        //   784: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   787: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   790: athrow         
        //   791: invokestatic    com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   794: athrow         
        //   795: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  45     83     83     87     Ljava/lang/IllegalArgumentException;
        //  147    209    209    213    Ljava/lang/IllegalArgumentException;
        //  223    238    241    245    Ljava/lang/IllegalArgumentException;
        //  279    291    294    298    Ljava/lang/IllegalArgumentException;
        //  284    328    328    332    Ljava/lang/IllegalArgumentException;
        //  402    420    423    427    Ljava/lang/IllegalArgumentException;
        //  407    431    431    435    Ljava/lang/IllegalArgumentException;
        //  494    509    512    516    Ljava/lang/IllegalArgumentException;
        //  499    520    520    524    Ljava/lang/IllegalArgumentException;
        //  527    545    548    552    Ljava/lang/IllegalArgumentException;
        //  555    566    569    573    Ljava/lang/IllegalArgumentException;
        //  559    580    583    587    Ljava/lang/IllegalArgumentException;
        //  573    597    597    601    Ljava/lang/IllegalArgumentException;
        //  631    645    648    652    Ljava/lang/IllegalArgumentException;
        //  686    703    706    710    Ljava/lang/IllegalArgumentException;
        //  710    748    748    752    Ljava/lang/IllegalArgumentException;
        //  753    791    791    795    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0573:
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
    
    void processSecondaryElements(final AssociatedElementProcessor associatedElementProcessor) {
        final PsiElement psiElement = this.getPsiElement();
        try {
            if (!(psiElement instanceof OCSymbolDeclarator)) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCClassSymbol symbol = ((OCSymbolDeclarator<OCClassSymbol>)psiElement).getSymbol();
        if (symbol instanceof OCInstanceVariableSymbol) {
            final OCPropertySymbol associatedProperty = ((OCInstanceVariableSymbol)symbol).getAssociatedProperty();
            Label_0091: {
                Label_0069: {
                    try {
                        if (associatedProperty == null) {
                            return;
                        }
                        final OCInstanceVariableSymbol ocInstanceVariableSymbol = (OCInstanceVariableSymbol)symbol;
                        final OCInstanceVariableSymbol ocInstanceVariableSymbol2 = ocInstanceVariableSymbol;
                        final boolean b = ocInstanceVariableSymbol2.isClang4ImplicitIvar();
                        if (b) {
                            break Label_0069;
                        }
                        break Label_0091;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final OCInstanceVariableSymbol ocInstanceVariableSymbol = (OCInstanceVariableSymbol)symbol;
                        final OCInstanceVariableSymbol ocInstanceVariableSymbol2 = ocInstanceVariableSymbol;
                        final boolean b = ocInstanceVariableSymbol2.isClang4ImplicitIvar();
                        if (b) {
                            associatedElementProcessor.process((Object)new OCSymbolHolderVirtualPsiElement(symbol));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                try {
                    if (associatedElementProcessor.proceedProperty(associatedProperty)) {
                        a((OCSymbol)associatedProperty, (Processor<PsiElement>)associatedElementProcessor);
                        this.a(associatedProperty, (Processor<PsiElement>)associatedElementProcessor);
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
        }
        else if (symbol instanceof OCPropertySymbol) {
            final OCInstanceVariableSymbol associatedIvar = ((OCPropertySymbol)symbol).getAssociatedIvar();
            Label_0188: {
                Label_0165: {
                    try {
                        if (associatedIvar == null) {
                            break Label_0188;
                        }
                        final OCInstanceVariableSymbol ocInstanceVariableSymbol3 = associatedIvar;
                        final boolean b2 = ocInstanceVariableSymbol3.isClang4ImplicitIvar();
                        if (b2) {
                            break Label_0165;
                        }
                        break Label_0188;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                    try {
                        final OCInstanceVariableSymbol ocInstanceVariableSymbol3 = associatedIvar;
                        final boolean b2 = ocInstanceVariableSymbol3.isClang4ImplicitIvar();
                        if (b2) {
                            associatedElementProcessor.process((Object)new OCSymbolHolderVirtualPsiElement(associatedIvar));
                            break Label_0188;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                }
                try {
                    if (associatedElementProcessor.proceedIvar(associatedIvar)) {
                        a(associatedIvar, (Processor<PsiElement>)associatedElementProcessor);
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw b(ex7);
                }
            }
            this.a((OCPropertySymbol)symbol, (Processor<PsiElement>)associatedElementProcessor);
        }
        else {
            Label_0331: {
                Label_0300: {
                    try {
                        if (!(symbol instanceof OCClassSymbol) || !associatedElementProcessor.proceedDerivedClasses()) {
                            break Label_0300;
                        }
                    }
                    catch (IllegalArgumentException ex8) {
                        throw b(ex8);
                    }
                    final Iterator iterator = OCClassInheritorsSearch.search(symbol).findAll().iterator();
                    while (iterator.hasNext()) {
                        a(iterator.next(), (Processor<PsiElement>)associatedElementProcessor);
                    }
                    return;
                    try {
                        if (!(symbol instanceof OCStructSymbol)) {
                            return;
                        }
                        final OCElement ocElement = (OCElement)psiElement;
                        final OCElement ocElement2 = ocElement;
                        final OCFile ocFile = ocElement2.getContainingOCFile();
                        final boolean b3 = ocFile.isCpp();
                        if (b3) {
                            break Label_0331;
                        }
                        return;
                    }
                    catch (IllegalArgumentException ex9) {
                        throw b(ex9);
                    }
                }
                try {
                    final OCElement ocElement = (OCElement)psiElement;
                    final OCElement ocElement2 = ocElement;
                    final OCFile ocFile = ocElement2.getContainingOCFile();
                    final boolean b3 = ocFile.isCpp();
                    if (!b3) {
                        return;
                    }
                    if (!associatedElementProcessor.proceedDerivedClasses()) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw b(ex10);
                }
            }
            final Iterator iterator2 = OCStructInheritorsSearch.search((OCStructSymbol)symbol, psiElement).findAll().iterator();
            while (iterator2.hasNext()) {
                a(iterator2.next(), (Processor<PsiElement>)associatedElementProcessor);
            }
        }
    }
    
    private void a(final OCPropertySymbol ocPropertySymbol, final Processor<PsiElement> processor) {
        try {
            if (this.myDeleteMode) {
                ocPropertySymbol.processSynthesizes((Processor<? super OCSynthesizeSymbol>)(ocSynthesizeSymbol -> {
                    a(ocSynthesizeSymbol, processor);
                    return true;
                }));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    @NotNull
    @Override
    public PsiElement[] getSecondaryElements() {
        final HashSet set = new HashSet();
        PsiElement[] psiElementArray = null;
        Label_0057: {
            try {
                if (this.myDeleteMode) {
                    this.processSecondaryElements(new AssociatedElementProcessor() {
                        @Override
                        public boolean proceedProperty(final OCPropertySymbol ocPropertySymbol) {
                            final String string = "Do you want to " + OCFindUsagesHandler.this.a() + " the " + ocPropertySymbol.getNameWithKindLowercase() + " as well?";
                            return ApplicationManager.getApplication().isUnitTestMode() || Messages.showYesNoDialog(string, "Properties search", Messages.getQuestionIcon()) == 0;
                        }
                        
                        @Override
                        public boolean proceedIvar(final OCInstanceVariableSymbol ocInstanceVariableSymbol) {
                            if (ocInstanceVariableSymbol.getGeneratedFromProperty() != null) {
                                return true;
                            }
                            final String string = "Do you want to " + OCFindUsagesHandler.this.a() + " the " + ocInstanceVariableSymbol.getNameWithKindLowercase() + " as well?";
                            return ApplicationManager.getApplication().isUnitTestMode() || Messages.showYesNoDialog(string, "Instance variables search", Messages.getQuestionIcon()) == 0;
                        }
                        
                        @Override
                        public boolean proceedDerivedClasses() {
                            return false;
                        }
                        
                        public boolean process(final PsiElement psiElement) {
                            ((Set<PsiElement>)set).add(psiElement);
                            return true;
                        }
                    });
                    break Label_0057;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            this.processSecondaryElements(new AssociatedElementProcessor() {
                final /* synthetic */ OCFindUsagesOptions val$options = OCFindUsagesOptions.getInstance(this.getProject());
                
                @Override
                public boolean proceedProperty(final OCPropertySymbol ocPropertySymbol) {
                    return this.val$options.isSearchForProperties;
                }
                
                @Override
                public boolean proceedIvar(final OCInstanceVariableSymbol ocInstanceVariableSymbol) {
                    return this.val$options.isSearchForIvars;
                }
                
                @Override
                public boolean proceedDerivedClasses() {
                    return this.val$options.isSearchForDerivedClasses;
                }
                
                public boolean process(final PsiElement psiElement) {
                    ((Set<PsiElement>)set).add(psiElement);
                    return true;
                }
            });
            try {
                psiElementArray = PsiUtilCore.toPsiElementArray((Collection)set);
                if (psiElementArray == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler", "getSecondaryElements"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return psiElementArray;
    }
    
    private static void a(final OCSymbol ocSymbol, final Processor<PsiElement> processor) {
        try {
            if (ocSymbol == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final PsiElement locateDefinition = ocSymbol.locateDefinition();
        try {
            if (locateDefinition != null) {
                processor.process((Object)locateDefinition);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
    }
    
    @Override
    protected boolean isSearchForTextOccurrencesAvailable(@NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler", "isSearchForTextOccurrencesAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (b) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        if (psiElement instanceof OCSymbolDeclarator) {
            final OCSymbol symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
            try {
                if (symbol == null) {
                    return true;
                }
                final OCSymbol ocSymbol = symbol;
                final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                final boolean b2 = ocSymbolKind.isLocal();
                if (b2) {
                    return false;
                }
                return true;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final OCSymbol ocSymbol = symbol;
                final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                final boolean b2 = ocSymbolKind.isLocal();
                if (b2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return true;
    }
    
    @NotNull
    @Override
    public FindUsagesOptions getFindUsagesOptions(@Nullable final DataContext dataContext) {
        OCFindUsagesOptions instance;
        try {
            instance = OCFindUsagesOptions.getInstance(this.getProject());
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler", "getFindUsagesOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return instance;
    }
    
    @NotNull
    @Override
    public AbstractFindUsagesDialog getFindUsagesDialog(final boolean b, final boolean b2, final boolean b3) {
        OCFindUsagesDialog ocFindUsagesDialog;
        try {
            ocFindUsagesDialog = new OCFindUsagesDialog(this.getPsiElement(), this.getProject(), OCFindUsagesOptions.getInstance(this.getProject()), b2, b3, b, this);
            if (ocFindUsagesDialog == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesHandler", "getFindUsagesDialog"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return ocFindUsagesDialog;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
    interface AssociatedElementProcessor extends Processor<PsiElement>
    {
        boolean proceedProperty(final OCPropertySymbol p0);
        
        boolean proceedIvar(final OCInstanceVariableSymbol p0);
        
        boolean proceedDerivedClasses();
    }
}
