// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import java.util.Iterator;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.refactoring.util.OCBindUtil;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import java.util.List;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureUsageProcessor;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.psi.OCCallable;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeInfo;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public class OCGeneratedMethodUsage extends OCUsageWithContext
{
    public OCGeneratedMethodUsage(@NotNull final PsiElement psiElement) {
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage", "<init>"));
        }
        super(psiElement);
    }
    
    @Nullable
    static OCCallable addMethod(final PsiElement psiElement, final OCChangeInfo ocChangeInfo, @Nullable final PsiElement psiElement2, final boolean b, final boolean b2) {
        Label_0018: {
            try {
                if (psiElement == null) {
                    break Label_0018;
                }
                final PsiElement psiElement3 = psiElement;
                final boolean b3 = OCSearchScope.isInProjectSources(psiElement3);
                if (!b3) {
                    break Label_0018;
                }
                break Label_0018;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final PsiElement psiElement3 = psiElement;
                final boolean b3 = OCSearchScope.isInProjectSources(psiElement3);
                if (!b3) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        final OCCallable generateMethodDefinition = OCChangeSignatureUsageProcessor.generateMethodDefinition(ocChangeInfo, ocChangeInfo.getMethod(), b, true, false);
        Label_0054: {
            try {
                if (generateMethodDefinition == null) {
                    return null;
                }
                final boolean b4 = b2;
                if (b4) {
                    break Label_0054;
                }
                return OCChangeUtil.addAfter(psiElement, generateMethodDefinition, psiElement2);
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final boolean b4 = b2;
                if (b4) {
                    return OCChangeUtil.addBefore(psiElement, generateMethodDefinition, psiElement2);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return OCChangeUtil.addAfter(psiElement, generateMethodDefinition, psiElement2);
    }
    
    @Override
    public boolean processUsage(@NotNull final OCChangeInfo p0, @NotNull final PsiElement p1, @NotNull final Project p2) {
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
        //    18: ldc             "changeInfo"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processUsage"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "element"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processUsage"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "project"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "processUsage"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_1        
        //   133: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getGenerated:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo;
        //   136: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo.getTargetSymbolsMode:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCTargetSymbolPanel$TargetSymbolsMode;
        //   139: astore          4
        //   141: aload_1        
        //   142: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getGenerated:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo;
        //   145: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo.getMethodParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   148: astore          5
        //   150: aconst_null    
        //   151: astore          6
        //   153: aload_1        
        //   154: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeBlock:()Z
        //   157: ifeq            176
        //   160: aload_1        
        //   161: aload_1        
        //   162: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getMethod:()Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   165: iconst_1       
        //   166: iconst_1       
        //   167: iconst_0       
        //   168: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.generateMethodDefinition:(Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;Lcom/jetbrains/cidr/lang/psi/OCCallable;ZZZ)Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   171: astore          6
        //   173: goto            649
        //   176: aload           5
        //   178: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   181: ifeq            504
        //   184: new             Ljava/util/ArrayList;
        //   187: dup            
        //   188: invokespecial   java/util/ArrayList.<init>:()V
        //   191: astore          7
        //   193: aload           5
        //   195: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   198: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getImplementation:()Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //   203: astore          8
        //   205: aload           8
        //   207: ifnull          225
        //   210: aload           8
        //   212: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   215: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclarationBase;
        //   218: goto            226
        //   221: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   224: athrow         
        //   225: aconst_null    
        //   226: astore          9
        //   228: aload           4
        //   230: getstatic       com/jetbrains/cidr/lang/refactoring/changeSignature/OCTargetSymbolPanel$TargetSymbolsMode.INTERFACE:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCTargetSymbolPanel$TargetSymbolsMode;
        //   233: if_acmpne       307
        //   236: aload           5
        //   238: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   241: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getInterfaceOrProtocol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   246: astore          10
        //   248: aload           7
        //   250: aload           10
        //   252: ifnull          272
        //   255: aload           10
        //   257: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   262: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclarationBase;
        //   265: goto            273
        //   268: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   271: athrow         
        //   272: aconst_null    
        //   273: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   278: pop            
        //   279: new             Lcom/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix;
        //   282: dup            
        //   283: aload_2        
        //   284: aload           10
        //   286: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.<init>:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   289: astore          11
        //   291: aload           11
        //   293: aload_3        
        //   294: aload_2        
        //   295: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   300: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.fixFirstItem:(Lcom/intellij/openapi/project/Project;Lcom/intellij/psi/PsiFile;)Z
        //   303: pop            
        //   304: goto            406
        //   307: aload           4
        //   309: getstatic       com/jetbrains/cidr/lang/refactoring/changeSignature/OCTargetSymbolPanel$TargetSymbolsMode.PRIVATE_CATEGORY:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCTargetSymbolPanel$TargetSymbolsMode;
        //   312: if_acmpne       406
        //   315: aload           9
        //   317: ifnull          406
        //   320: goto            327
        //   323: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   326: athrow         
        //   327: aload           9
        //   329: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.getPrivateCategory:(Lcom/jetbrains/cidr/lang/psi/OCClassDeclarationBase;)Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   332: astore          10
        //   334: aload           10
        //   336: ifnonnull       396
        //   339: new             Ljava/lang/StringBuilder;
        //   342: dup            
        //   343: invokespecial   java/lang/StringBuilder.<init>:()V
        //   346: aload           5
        //   348: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   353: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   356: ldc             "()"
        //   358: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   361: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   364: aload           9
        //   366: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.interfaceByName:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCInterface;
        //   369: astore          10
        //   371: aload           7
        //   373: aload           9
        //   375: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclarationBase.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   380: aload           10
        //   382: aload           9
        //   384: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   387: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   392: pop            
        //   393: goto            406
        //   396: aload           7
        //   398: aload           10
        //   400: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   405: pop            
        //   406: aload           7
        //   408: aload           9
        //   410: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   415: pop            
        //   416: aload           7
        //   418: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   423: astore          10
        //   425: aload           10
        //   427: invokeinterface java/util/Iterator.hasNext:()Z
        //   432: ifeq            501
        //   435: aload           10
        //   437: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   442: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclarationBase;
        //   445: astore          11
        //   447: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsLaterMethodDeclaration:()Z
        //   450: ifne            461
        //   453: iconst_1       
        //   454: goto            462
        //   457: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   460: athrow         
        //   461: iconst_0       
        //   462: istore          12
        //   464: aload           11
        //   466: aload_1        
        //   467: aload_2        
        //   468: aload           11
        //   470: instanceof      Lcom/jetbrains/cidr/lang/psi/OCImplementation;
        //   473: iload           12
        //   475: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.addMethod:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;Lcom/intellij/psi/PsiElement;ZZ)Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   478: astore          13
        //   480: aload           13
        //   482: ifnull          494
        //   485: aload           13
        //   487: goto            496
        //   490: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   493: athrow         
        //   494: aload           6
        //   496: astore          6
        //   498: goto            425
        //   501: goto            649
        //   504: iconst_1       
        //   505: istore          8
        //   507: aload           5
        //   509: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   512: ifne            628
        //   515: aload_1        
        //   516: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewCallableKind:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //   519: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind.getSymbolKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   522: astore          9
        //   524: aload           9
        //   526: aload_1        
        //   527: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getContext:()Lcom/intellij/psi/PsiElement;
        //   530: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.getAppropriateParent:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   533: astore          7
        //   535: aload_1        
        //   536: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getMethod:()Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   539: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   542: ifeq            625
        //   545: aload_1        
        //   546: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getMethod:()Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   549: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   552: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDefinition.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //   557: ifnonnull       622
        //   560: goto            567
        //   563: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   566: athrow         
        //   567: aload           4
        //   569: getstatic       com/jetbrains/cidr/lang/refactoring/changeSignature/OCTargetSymbolPanel$TargetSymbolsMode.INTERFACE:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCTargetSymbolPanel$TargetSymbolsMode;
        //   572: if_acmpne       625
        //   575: goto            582
        //   578: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   581: athrow         
        //   582: aload           7
        //   584: ifnull          625
        //   587: goto            594
        //   590: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   593: athrow         
        //   594: iconst_0       
        //   595: istore          8
        //   597: aload           7
        //   599: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   602: ifne            625
        //   605: aload           7
        //   607: aload_1        
        //   608: aload_2        
        //   609: iconst_0       
        //   610: iconst_1       
        //   611: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.addMethod:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;Lcom/intellij/psi/PsiElement;ZZ)Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   614: pop            
        //   615: goto            625
        //   618: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   621: athrow         
        //   622: iconst_0       
        //   623: istore          8
        //   625: goto            637
        //   628: aload           5
        //   630: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   635: astore          7
        //   637: aload           7
        //   639: aload_1        
        //   640: aload_2        
        //   641: iconst_1       
        //   642: iload           8
        //   644: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.addMethod:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;Lcom/intellij/psi/PsiElement;ZZ)Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   647: astore          6
        //   649: aload           6
        //   651: ifnull          1460
        //   654: aload           6
        //   656: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   661: astore          7
        //   663: aload_1        
        //   664: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getGenerated:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo;
        //   667: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo.getMethodStatements:()Ljava/util/List;
        //   670: astore          8
        //   672: aload           7
        //   674: ifnull          1147
        //   677: aload           8
        //   679: ifnull          1147
        //   682: goto            689
        //   685: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   688: athrow         
        //   689: aload_1        
        //   690: aload           8
        //   692: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.a:(Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;Ljava/util/List;)Ljava/util/List;
        //   695: astore          8
        //   697: aload           8
        //   699: invokeinterface java/util/List.size:()I
        //   704: iconst_1       
        //   705: if_icmpne       755
        //   708: aload           8
        //   710: iconst_0       
        //   711: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   716: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   719: ifeq            755
        //   722: goto            729
        //   725: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   728: athrow         
        //   729: aload           7
        //   731: aload           8
        //   733: iconst_0       
        //   734: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   739: checkcast       Lcom/intellij/psi/PsiElement;
        //   742: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.replace:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   747: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   750: astore          7
        //   752: goto            917
        //   755: aload           8
        //   757: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   762: astore          9
        //   764: aload           9
        //   766: invokeinterface java/util/Iterator.hasNext:()Z
        //   771: ifeq            917
        //   774: aload           9
        //   776: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   781: checkcast       Lcom/intellij/psi/PsiElement;
        //   784: astore          10
        //   786: aload           10
        //   788: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   791: ifeq            889
        //   794: aload_1        
        //   795: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewReturnType:()Ljava/lang/String;
        //   798: ldc             "void"
        //   800: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   803: ifeq            851
        //   806: goto            813
        //   809: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   812: athrow         
        //   813: ldc             "x();"
        //   815: aload_1        
        //   816: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getContext:()Lcom/intellij/psi/PsiElement;
        //   819: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.statementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   822: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   825: astore          11
        //   827: aload           11
        //   829: invokeinterface com/jetbrains/cidr/lang/psi/OCExpressionStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   834: aload           10
        //   836: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   839: pop            
        //   840: aload           7
        //   842: aload           11
        //   844: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.add:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   847: pop            
        //   848: goto            914
        //   851: ldc             "return x;"
        //   853: aload_1        
        //   854: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getContext:()Lcom/intellij/psi/PsiElement;
        //   857: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.statementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   860: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReturnStatement;
        //   863: astore          11
        //   865: aload           11
        //   867: invokeinterface com/jetbrains/cidr/lang/psi/OCReturnStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   872: aload           10
        //   874: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   877: pop            
        //   878: aload           7
        //   880: aload           11
        //   882: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.add:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   885: pop            
        //   886: goto            914
        //   889: aload           10
        //   891: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //   896: ifeq            914
        //   899: aload           7
        //   901: aload           10
        //   903: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.add:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   906: pop            
        //   907: goto            914
        //   910: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   913: athrow         
        //   914: goto            764
        //   917: aload           6
        //   919: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   922: ifeq            1123
        //   925: aload           6
        //   927: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   932: astore          9
        //   934: aload           6
        //   936: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   939: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDefinition.getDeclarator:()Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   944: astore          10
        //   946: aload           10
        //   948: ifnull          965
        //   951: aload           10
        //   953: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //   958: goto            966
        //   961: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   964: athrow         
        //   965: aconst_null    
        //   966: astore          11
        //   968: aload           9
        //   970: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   973: ifeq            1123
        //   976: aload           11
        //   978: ifnull          1123
        //   981: goto            988
        //   984: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   987: athrow         
        //   988: aload           11
        //   990: aload           9
        //   992: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   995: iconst_1       
        //   996: invokeinterface com/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier.getPredeclarationInParent:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Z)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1001: ifnonnull       1123
        //  1004: goto            1011
        //  1007: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1010: athrow         
        //  1011: aload           11
        //  1013: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //  1016: dup            
        //  1017: aload           11
        //  1019: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //  1022: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.getAppropriateToAppendSymbol:(Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1025: astore          12
        //  1027: aload           12
        //  1029: ifnull          1123
        //  1032: aload           9
        //  1034: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  1037: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1040: astore          13
        //  1042: new             Lcom/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction;
        //  1045: dup            
        //  1046: aload           13
        //  1048: aload           10
        //  1050: aconst_null    
        //  1051: aload           12
        //  1053: aload           10
        //  1055: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getName:()Ljava/lang/String;
        //  1060: aload           9
        //  1062: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1067: aload_1        
        //  1068: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getGenerated:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo;
        //  1071: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo.isStatic:()Z
        //  1074: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Z)V
        //  1077: astore          14
        //  1079: aload           14
        //  1081: iconst_1       
        //  1082: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.setSilentMode:(Z)V
        //  1085: aload           14
        //  1087: aload_3        
        //  1088: aconst_null    
        //  1089: aload           10
        //  1091: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //  1096: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.isAvailable:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Z
        //  1099: ifeq            1123
        //  1102: aload           14
        //  1104: aload_3        
        //  1105: aconst_null    
        //  1106: aload           10
        //  1108: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //  1113: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.invoke:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)V
        //  1116: goto            1123
        //  1119: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1122: athrow         
        //  1123: aload_1        
        //  1124: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeBlock:()Z
        //  1127: ifne            1147
        //  1130: aload           7
        //  1132: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCBindUtil.decodeContextInfo:(Lcom/intellij/psi/PsiElement;)V
        //  1135: aload           7
        //  1137: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCBindUtil.removeRedundantQualifiers:(Lcom/intellij/psi/PsiElement;)V
        //  1140: goto            1147
        //  1143: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1146: athrow         
        //  1147: aload           6
        //  1149: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.fixAllSymbolsRecursively:(Lcom/intellij/psi/PsiElement;)V
        //  1152: aload           6
        //  1154: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //  1159: astore          9
        //  1161: aload           5
        //  1163: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  1166: ifeq            1410
        //  1169: aload           6
        //  1171: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //  1174: ifeq            1410
        //  1177: goto            1184
        //  1180: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1183: athrow         
        //  1184: aload           6
        //  1186: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1191: astore          10
        //  1193: aload           10
        //  1195: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  1198: ifeq            1407
        //  1201: aload_2        
        //  1202: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //  1205: ifeq            1236
        //  1208: goto            1215
        //  1211: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1214: athrow         
        //  1215: aload_2        
        //  1216: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //  1219: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //  1224: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1229: goto            1237
        //  1232: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1235: athrow         
        //  1236: aconst_null    
        //  1237: astore          11
        //  1239: aload           10
        //  1241: aload_2        
        //  1242: aload           11
        //  1244: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.getVisibility:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //  1247: astore          12
        //  1249: aload           10
        //  1251: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  1254: astore          13
        //  1256: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction;
        //  1259: dup            
        //  1260: aload           13
        //  1262: aload           12
        //  1264: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)V
        //  1267: astore          14
        //  1269: aload           10
        //  1271: aload           12
        //  1273: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.isVisible:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)Z
        //  1276: ifne            1317
        //  1279: aload           14
        //  1281: aload_3        
        //  1282: aconst_null    
        //  1283: aload           9
        //  1285: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.isAvailable:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Z
        //  1288: ifeq            1317
        //  1291: goto            1298
        //  1294: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1297: athrow         
        //  1298: aload           14
        //  1300: aload_3        
        //  1301: aconst_null    
        //  1302: aload           9
        //  1304: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.invoke:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)V
        //  1307: aload           14
        //  1309: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction.getNewDeclaration:()Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //  1312: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //  1315: astore          6
        //  1317: aload           5
        //  1319: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //  1324: astore          15
        //  1326: aload           15
        //  1328: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //  1331: ifeq            1407
        //  1334: aload           13
        //  1336: iconst_0       
        //  1337: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.shouldGenerateDefinitionsFor:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Z)Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION;
        //  1340: getstatic       com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION.NO:Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION;
        //  1343: if_acmpeq       1407
        //  1346: goto            1353
        //  1349: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1352: athrow         
        //  1353: aload_3        
        //  1354: invokestatic    com/intellij/psi/SmartPointerManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/SmartPointerManager;
        //  1357: aload           6
        //  1359: invokevirtual   com/intellij/psi/SmartPointerManager.createSmartPsiElementPointer:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/SmartPsiElementPointer;
        //  1362: astore          16
        //  1364: aload_3        
        //  1365: aload           9
        //  1367: invokestatic    com/jetbrains/cidr/lang/generate/OCCaretLocation.byFile:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;
        //  1370: aload           5
        //  1372: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  1375: aload           13
        //  1377: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //  1380: aload           6
        //  1382: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //  1385: getstatic       com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy.PREFERRED:Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy;
        //  1388: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.getGenerateDefinitionReplacements:(Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy;)Ljava/util/List;
        //  1391: iconst_1       
        //  1392: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil.applyReplacements:(Lcom/intellij/openapi/project/Project;Ljava/util/List;Z)V
        //  1395: aload           16
        //  1397: invokeinterface com/intellij/psi/SmartPsiElementPointer.getElement:()Lcom/intellij/psi/PsiElement;
        //  1402: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //  1405: astore          6
        //  1407: goto            1460
        //  1410: aload_1        
        //  1411: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getGenerated:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo;
        //  1414: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCGeneratedInfo.isSelectMethod:()Z
        //  1417: ifeq            1460
        //  1420: aload_1        
        //  1421: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeBlock:()Z
        //  1424: ifne            1460
        //  1427: goto            1434
        //  1430: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1433: athrow         
        //  1434: aload           9
        //  1436: invokestatic    com/intellij/ide/util/EditorHelper.openInEditor:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/editor/Editor;
        //  1439: astore          10
        //  1441: aload           10
        //  1443: ifnull          1460
        //  1446: aload           10
        //  1448: aload           7
        //  1450: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.selectBody:(Lcom/intellij/openapi/editor/Editor;Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;)V
        //  1453: goto            1460
        //  1456: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedMethodUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1459: athrow         
        //  1460: aload_1        
        //  1461: aload           6
        //  1463: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.setNewMethod:(Lcom/jetbrains/cidr/lang/psi/OCCallable;)V
        //  1466: aload_1        
        //  1467: aload           6
        //  1469: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.addNewMethod:(Lcom/jetbrains/cidr/lang/psi/OCCallable;)V
        //  1472: iconst_1       
        //  1473: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  205    221    221    225    Ljava/lang/IllegalArgumentException;
        //  248    268    268    272    Ljava/lang/IllegalArgumentException;
        //  307    320    323    327    Ljava/lang/IllegalArgumentException;
        //  447    457    457    461    Ljava/lang/IllegalArgumentException;
        //  480    490    490    494    Ljava/lang/IllegalArgumentException;
        //  535    560    563    567    Ljava/lang/IllegalArgumentException;
        //  545    575    578    582    Ljava/lang/IllegalArgumentException;
        //  567    587    590    594    Ljava/lang/IllegalArgumentException;
        //  597    618    618    622    Ljava/lang/IllegalArgumentException;
        //  672    682    685    689    Ljava/lang/IllegalArgumentException;
        //  697    722    725    729    Ljava/lang/IllegalArgumentException;
        //  786    806    809    813    Ljava/lang/IllegalArgumentException;
        //  889    907    910    914    Ljava/lang/IllegalArgumentException;
        //  946    961    961    965    Ljava/lang/IllegalArgumentException;
        //  968    981    984    988    Ljava/lang/IllegalArgumentException;
        //  976    1004   1007   1011   Ljava/lang/IllegalArgumentException;
        //  1079   1116   1119   1123   Ljava/lang/IllegalArgumentException;
        //  1123   1140   1143   1147   Ljava/lang/IllegalArgumentException;
        //  1161   1177   1180   1184   Ljava/lang/IllegalArgumentException;
        //  1193   1208   1211   1215   Ljava/lang/IllegalArgumentException;
        //  1201   1232   1232   1236   Ljava/lang/IllegalArgumentException;
        //  1269   1291   1294   1298   Ljava/lang/IllegalArgumentException;
        //  1326   1346   1349   1353   Ljava/lang/IllegalArgumentException;
        //  1410   1427   1430   1434   Ljava/lang/IllegalArgumentException;
        //  1441   1453   1456   1460   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0567:
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
    
    private static List<PsiElement> a(final OCChangeInfo ocChangeInfo, final List<PsiElement> list) {
        final List<PsiElement> insertRedundantQualifiers = OCBindUtil.insertRedundantQualifiers(ContainerUtil.filter((Collection)list, psiElement -> psiElement.isValid()), false);
        final List filter = ContainerUtil.filter((Collection)insertRedundantQualifiers, psiElement -> psiElement.isPhysical());
        OCBindUtil.encodeContextInfo(filter, null, false);
        final Iterator<PsiElement> iterator = filter.iterator();
        while (iterator.hasNext()) {
            iterator.next().accept((PsiElementVisitor)new OCRecursiveVisitor() {
                @Override
                public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
                    if (ocReferenceExpression.getSelfSuperToken() != null && ocChangeInfo.getSelfParameterName() != null) {
                        final OCReferenceElement referenceElement = ocReferenceExpression.getReferenceElement();
                        if (referenceElement != null) {
                            referenceElement.setNameOfIdentifier(ocChangeInfo.getSelfParameterName());
                        }
                    }
                }
            });
        }
        return insertRedundantQualifiers;
    }
    
    @Override
    public int getUsageRank() {
        return -1;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
