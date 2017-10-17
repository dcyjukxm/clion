// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.codeInsight.generation.MemberChooserObject;
import java.util.Map;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.generate.OCMemberChooserObject;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.intellij.util.containers.ContainerUtil;
import java.util.ArrayList;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.generate.OCGenerateUtil;
import java.util.List;
import com.jetbrains.cidr.lang.generate.OCCaretLocation;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateConstructorContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;

public class OCGenerateConstructorHandler extends OCCCppGenerateHandlerBase<OCStructSymbol, OCDeclaratorSymbol, OCGenerateConstructorContext>
{
    @Override
    protected String getActionTitle() {
        return "Generate Constructor";
    }
    
    @Override
    protected String getMembersChooserTitle() {
        return "Select Fields to be Initialized";
    }
    
    @NotNull
    @Override
    protected OCGenerateConstructorContext evaluateActionContext(final OCStructSymbol ocStructSymbol, final PsiElement psiElement) {
        OCGenerateConstructorContext ocGenerateConstructorContext;
        try {
            ocGenerateConstructorContext = new OCGenerateConstructorContext(ocStructSymbol, psiElement);
            if (ocGenerateConstructorContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler", "evaluateActionContext"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocGenerateConstructorContext;
    }
    
    @Override
    protected boolean allowUnions() {
        return true;
    }
    
    @NotNull
    @Override
    protected List<OCGenerateUtil.Replacement> getReplacements(@NotNull final OCCaretLocation p0, @NotNull final OCGenerateConstructorContext p1, @NotNull final List<OCDeclaratorSymbol> p2) {
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
        //    18: ldc             "location"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getReplacements"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
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
        //    62: ldc             "actionContext"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getReplacements"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
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
        //   106: ldc             "fields"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "getReplacements"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   131: athrow         
        //   132: new             Ljava/util/ArrayList;
        //   135: dup            
        //   136: invokespecial   java/util/ArrayList.<init>:()V
        //   139: astore          4
        //   141: aload_2        
        //   142: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateConstructorContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   145: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   148: astore          5
        //   150: aload           5
        //   152: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   155: astore          6
        //   157: aload           6
        //   159: ifnull          177
        //   162: aload           6
        //   164: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   167: ifne            226
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   176: athrow         
        //   177: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   180: dup            
        //   181: ifnonnull       225
        //   184: goto            191
        //   187: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   190: athrow         
        //   191: new             Ljava/lang/IllegalStateException;
        //   194: dup            
        //   195: ldc             "@NotNull method %s.%s must not return null"
        //   197: ldc             2
        //   199: anewarray       Ljava/lang/Object;
        //   202: dup            
        //   203: ldc             0
        //   205: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler"
        //   207: aastore        
        //   208: dup            
        //   209: ldc             1
        //   211: ldc             "getReplacements"
        //   213: aastore        
        //   214: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   217: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   220: athrow         
        //   221: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   224: athrow         
        //   225: areturn        
        //   226: aload           6
        //   228: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   231: astore          7
        //   233: aload_0        
        //   234: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.addParametersForBaseClasses:()Z
        //   237: ifeq            269
        //   240: aload           5
        //   242: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   245: dup            
        //   246: aload           7
        //   248: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   251: aload           4
        //   253: invokedynamic   process:(Ljava/util/List;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;
        //   258: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processBaseClasses:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;)Z
        //   261: pop            
        //   262: goto            269
        //   265: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   268: athrow         
        //   269: aload           4
        //   271: aload_1        
        //   272: invokevirtual   com/jetbrains/cidr/lang/generate/OCCaretLocation.getProject:()Lcom/intellij/openapi/project/Project;
        //   275: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.chooseBaseConstructors:(Ljava/util/List;Lcom/intellij/openapi/project/Project;)Ljava/util/List;
        //   278: astore          8
        //   280: aload           8
        //   282: ifnonnull       334
        //   285: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   288: dup            
        //   289: ifnonnull       333
        //   292: goto            299
        //   295: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   298: athrow         
        //   299: new             Ljava/lang/IllegalStateException;
        //   302: dup            
        //   303: ldc             "@NotNull method %s.%s must not return null"
        //   305: ldc             2
        //   307: anewarray       Ljava/lang/Object;
        //   310: dup            
        //   311: ldc             0
        //   313: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler"
        //   315: aastore        
        //   316: dup            
        //   317: ldc             1
        //   319: ldc             "getReplacements"
        //   321: aastore        
        //   322: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   325: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   328: athrow         
        //   329: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   332: athrow         
        //   333: areturn        
        //   334: new             Ljava/util/ArrayList;
        //   337: dup            
        //   338: invokespecial   java/util/ArrayList.<init>:()V
        //   341: astore          9
        //   343: new             Ljava/util/ArrayList;
        //   346: dup            
        //   347: invokespecial   java/util/ArrayList.<init>:()V
        //   350: astore          10
        //   352: new             Ljava/util/HashSet;
        //   355: dup            
        //   356: invokespecial   java/util/HashSet.<init>:()V
        //   359: astore          11
        //   361: new             Ljava/util/ArrayList;
        //   364: dup            
        //   365: invokespecial   java/util/ArrayList.<init>:()V
        //   368: astore          12
        //   370: aload           8
        //   372: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   377: astore          13
        //   379: aload           13
        //   381: invokeinterface java/util/Iterator.hasNext:()Z
        //   386: ifeq            653
        //   389: aload           13
        //   391: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   396: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   399: astore          14
        //   401: aload           14
        //   403: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   406: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.hasNoParameters:()Z
        //   409: ifeq            419
        //   412: goto            379
        //   415: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   418: athrow         
        //   419: new             Ljava/lang/StringBuilder;
        //   422: dup            
        //   423: aload           14
        //   425: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getName:()Ljava/lang/String;
        //   428: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   431: ldc             "("
        //   433: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   436: astore          15
        //   438: iconst_1       
        //   439: istore          16
        //   441: aload           14
        //   443: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParameterSymbols:()Ljava/util/List;
        //   446: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   451: astore          17
        //   453: aload           17
        //   455: invokeinterface java/util/Iterator.hasNext:()Z
        //   460: ifeq            629
        //   463: aload           17
        //   465: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   470: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   473: astore          18
        //   475: aload           18
        //   477: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   480: astore          19
        //   482: aload           19
        //   484: instanceof      Lcom/jetbrains/cidr/lang/types/OCEllipsisType;
        //   487: ifne            629
        //   490: aload           19
        //   492: instanceof      Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   495: ifeq            512
        //   498: goto            505
        //   501: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   504: athrow         
        //   505: goto            629
        //   508: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   511: athrow         
        //   512: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   515: aload           18
        //   517: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getName:()Ljava/lang/String;
        //   520: aconst_null    
        //   521: aload           11
        //   523: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.suggestUniqueName:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Ljava/lang/String;Lcom/intellij/psi/PsiElement;Ljava/util/Collection;)Ljava/lang/String;
        //   526: astore          20
        //   528: aload           11
        //   530: aload           20
        //   532: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   537: pop            
        //   538: aload           10
        //   540: aload           19
        //   542: aload           7
        //   544: aload           19
        //   546: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   549: ifne            567
        //   552: aload           19
        //   554: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   557: ifeq            575
        //   560: goto            567
        //   563: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   566: athrow         
        //   567: iconst_1       
        //   568: goto            576
        //   571: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   574: athrow         
        //   575: iconst_0       
        //   576: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.getExtractExpressionType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //   579: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   584: pop            
        //   585: aload           9
        //   587: aload           20
        //   589: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   594: pop            
        //   595: iload           16
        //   597: ifne            615
        //   600: aload           15
        //   602: ldc             ","
        //   604: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   607: pop            
        //   608: goto            615
        //   611: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   614: athrow         
        //   615: aload           15
        //   617: aload           20
        //   619: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   622: pop            
        //   623: iconst_0       
        //   624: istore          16
        //   626: goto            453
        //   629: aload           15
        //   631: ldc             ")"
        //   633: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   636: pop            
        //   637: aload           12
        //   639: aload           15
        //   641: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   644: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   649: pop            
        //   650: goto            379
        //   653: aload_3        
        //   654: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   659: astore          13
        //   661: aload           13
        //   663: invokeinterface java/util/Iterator.hasNext:()Z
        //   668: ifeq            821
        //   671: aload           13
        //   673: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   678: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   681: astore          14
        //   683: aload           14
        //   685: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.getCppFieldNameWithoutPrefixAndSuffix:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Ljava/lang/String;
        //   688: astore          15
        //   690: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   693: aload           15
        //   695: aconst_null    
        //   696: aload           11
        //   698: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.suggestUniqueName:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Ljava/lang/String;Lcom/intellij/psi/PsiElement;Ljava/util/Collection;)Ljava/lang/String;
        //   701: astore          15
        //   703: aload           11
        //   705: aload           15
        //   707: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   712: pop            
        //   713: aload           14
        //   715: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   718: astore          16
        //   720: aload           10
        //   722: aload           16
        //   724: aload           7
        //   726: aload           16
        //   728: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   731: ifne            749
        //   734: aload           16
        //   736: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   739: ifeq            757
        //   742: goto            749
        //   745: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   748: athrow         
        //   749: iconst_1       
        //   750: goto            758
        //   753: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   756: athrow         
        //   757: iconst_0       
        //   758: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.getExtractExpressionType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //   761: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   766: pop            
        //   767: aload           9
        //   769: aload           15
        //   771: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   776: pop            
        //   777: aload           12
        //   779: new             Ljava/lang/StringBuilder;
        //   782: dup            
        //   783: invokespecial   java/lang/StringBuilder.<init>:()V
        //   786: aload           14
        //   788: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getName:()Ljava/lang/String;
        //   791: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   794: ldc             "("
        //   796: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   799: aload           15
        //   801: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   804: ldc             ")"
        //   806: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   809: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   812: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   817: pop            
        //   818: goto            661
        //   821: new             Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   824: dup            
        //   825: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   828: aload           10
        //   830: aload           9
        //   832: invokespecial   com/jetbrains/cidr/lang/types/OCFunctionType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;Ljava/util/List;)V
        //   835: astore          13
        //   837: aload           13
        //   839: aload_1        
        //   840: invokevirtual   com/jetbrains/cidr/lang/generate/OCCaretLocation.getFile:()Lcom/intellij/psi/PsiFile;
        //   843: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   846: astore          14
        //   848: new             Lcom/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler$1;
        //   851: dup            
        //   852: aload_0        
        //   853: aload_1        
        //   854: aload           14
        //   856: invokespecial   com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler$1.<init>:(Lcom/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler;Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   859: astore          15
        //   861: aload           5
        //   863: aconst_null    
        //   864: aload           15
        //   866: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processMembers:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
        //   869: pop            
        //   870: aload           15
        //   872: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.isFound:()Z
        //   875: ifeq            1001
        //   878: new             Ljava/lang/StringBuilder;
        //   881: dup            
        //   882: invokespecial   java/lang/StringBuilder.<init>:()V
        //   885: ldc             "Constructor "
        //   887: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   890: aload           15
        //   892: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.getFoundValue:()Ljava/lang/Object;
        //   895: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   898: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getSignatureWithoutParamNames:()Ljava/lang/String;
        //   901: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   904: ldc             " is already defined.\nDo you wish to continue?"
        //   906: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   909: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   912: astore          16
        //   914: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   917: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   922: ifeq            933
        //   925: iconst_1       
        //   926: goto            945
        //   929: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   932: athrow         
        //   933: aload           16
        //   935: aload_0        
        //   936: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.getActionTitle:()Ljava/lang/String;
        //   939: invokestatic    com/intellij/openapi/ui/Messages.getQuestionIcon:()Ljavax/swing/Icon;
        //   942: invokestatic    com/intellij/openapi/ui/Messages.showYesNoDialog:(Ljava/lang/String;Ljava/lang/String;Ljavax/swing/Icon;)I
        //   945: istore          17
        //   947: iload           17
        //   949: ifeq            1001
        //   952: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   955: dup            
        //   956: ifnonnull       1000
        //   959: goto            966
        //   962: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   965: athrow         
        //   966: new             Ljava/lang/IllegalStateException;
        //   969: dup            
        //   970: ldc             "@NotNull method %s.%s must not return null"
        //   972: ldc             2
        //   974: anewarray       Ljava/lang/Object;
        //   977: dup            
        //   978: ldc             0
        //   980: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler"
        //   982: aastore        
        //   983: dup            
        //   984: ldc             1
        //   986: ldc             "getReplacements"
        //   988: aastore        
        //   989: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   992: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   995: athrow         
        //   996: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   999: athrow         
        //  1000: areturn        
        //  1001: new             Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //  1004: dup            
        //  1005: aload           5
        //  1007: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getName:()Ljava/lang/String;
        //  1010: invokespecial   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.<init>:(Ljava/lang/String;)V
        //  1013: iconst_1       
        //  1014: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.setIsCtorOrDtor:(Z)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //  1017: aload           5
        //  1019: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.setContainer:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //  1022: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //  1025: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.setVisibility:(Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //  1028: astore          16
        //  1030: aload           10
        //  1032: aload           9
        //  1034: invokestatic    com/intellij/util/containers/ContainerUtil.zip:(Ljava/lang/Iterable;Ljava/lang/Iterable;)Ljava/lang/Iterable;
        //  1037: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //  1042: astore          17
        //  1044: aload           17
        //  1046: invokeinterface java/util/Iterator.hasNext:()Z
        //  1051: ifeq            1091
        //  1054: aload           17
        //  1056: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1061: checkcast       Lcom/intellij/openapi/util/Pair;
        //  1064: astore          18
        //  1066: aload           16
        //  1068: aload           18
        //  1070: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //  1073: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //  1076: aload           18
        //  1078: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //  1081: checkcast       Ljava/lang/String;
        //  1084: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.addParam:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //  1087: pop            
        //  1088: goto            1044
        //  1091: new             Ljava/lang/StringBuilder;
        //  1094: dup            
        //  1095: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1098: aload           12
        //  1100: invokeinterface java/util/List.isEmpty:()Z
        //  1105: ifeq            1117
        //  1108: ldc             ""
        //  1110: goto            1142
        //  1113: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1116: athrow         
        //  1117: new             Ljava/lang/StringBuilder;
        //  1120: dup            
        //  1121: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1124: ldc             ":"
        //  1126: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1129: aload           12
        //  1131: ldc             ","
        //  1133: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/util/Collection;Ljava/lang/String;)Ljava/lang/String;
        //  1136: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1139: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1142: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1145: ldc             "{}"
        //  1147: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1150: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1153: astore          17
        //  1155: aload_1        
        //  1156: aload           7
        //  1158: aload           5
        //  1160: aload           16
        //  1162: aload_1        
        //  1163: invokevirtual   com/jetbrains/cidr/lang/generate/OCCaretLocation.getProject:()Lcom/intellij/openapi/project/Project;
        //  1166: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.get:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  1169: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //  1172: aload           17
        //  1174: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //  1177: aload_0        
        //  1178: aload_2        
        //  1179: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.getInlinePolicy:(Lcom/jetbrains/cidr/lang/generate/actions/OCCppActionContext;)Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy;
        //  1182: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.getNewFunctionsReplacements:(Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/jetbrains/cidr/lang/psi/OCStructLike;Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy;)Ljava/util/List;
        //  1185: dup            
        //  1186: ifnonnull       1223
        //  1189: new             Ljava/lang/IllegalStateException;
        //  1192: dup            
        //  1193: ldc             "@NotNull method %s.%s must not return null"
        //  1195: ldc             2
        //  1197: anewarray       Ljava/lang/Object;
        //  1200: dup            
        //  1201: ldc             0
        //  1203: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler"
        //  1205: aastore        
        //  1206: dup            
        //  1207: ldc             1
        //  1209: ldc             "getReplacements"
        //  1211: aastore        
        //  1212: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1215: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1218: athrow         
        //  1219: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1222: athrow         
        //  1223: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateConstructorContext;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;>;)Ljava/util/List<Lcom/jetbrains/cidr/lang/generate/OCGenerateUtil$Replacement;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     84     84     88     Ljava/lang/IllegalStateException;
        //  88     128    128    132    Ljava/lang/IllegalStateException;
        //  157    170    173    177    Ljava/lang/IllegalStateException;
        //  162    184    187    191    Ljava/lang/IllegalStateException;
        //  177    221    221    225    Ljava/lang/IllegalStateException;
        //  233    262    265    269    Ljava/lang/IllegalStateException;
        //  280    292    295    299    Ljava/lang/IllegalStateException;
        //  285    329    329    333    Ljava/lang/IllegalStateException;
        //  401    415    415    419    Ljava/lang/IllegalStateException;
        //  482    498    501    505    Ljava/lang/IllegalStateException;
        //  490    508    508    512    Ljava/lang/IllegalStateException;
        //  528    560    563    567    Ljava/lang/IllegalStateException;
        //  552    571    571    575    Ljava/lang/IllegalStateException;
        //  576    608    611    615    Ljava/lang/IllegalStateException;
        //  720    742    745    749    Ljava/lang/IllegalStateException;
        //  734    753    753    757    Ljava/lang/IllegalStateException;
        //  914    929    929    933    Ljava/lang/IllegalStateException;
        //  947    959    962    966    Ljava/lang/IllegalStateException;
        //  952    996    996    1000   Ljava/lang/IllegalStateException;
        //  1091   1113   1113   1117   Ljava/lang/IllegalStateException;
        //  1155   1219   1219   1223   Ljava/lang/IllegalStateException;
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
    
    @Override
    protected boolean allowEmptySelection(final OCGenerateConstructorContext ocGenerateConstructorContext) {
        return true;
    }
    
    @Override
    protected boolean allowMultiSelection(final OCGenerateConstructorContext ocGenerateConstructorContext) {
        try {
            if (((OCActionContext<OCStructSymbol, M>)ocGenerateConstructorContext).getParent().getKind() == OCSymbolKind.STRUCT) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Nullable
    public static List<OCFunctionSymbol> chooseBaseConstructors(final List<Collection<OCFunctionSymbol>> list, final Project project) {
        final ArrayList<OCFunctionSymbol> list2 = new ArrayList<OCFunctionSymbol>();
        for (final Collection<OCFunctionSymbol> collection : list) {
            if (collection.size() > 1) {
                final OCFunctionSymbol a = a(collection, project);
                try {
                    if (a != null) {
                        list2.add(a);
                        continue;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return null;
                continue;
            }
            final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ContainerUtil.getFirstItem((Collection)collection);
            Label_0110: {
                try {
                    if (ocFunctionSymbol == null) {
                        continue;
                    }
                    final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                    final boolean b = ocFunctionSymbol2.canBeCalledWithoutArguments();
                    if (!b) {
                        break Label_0110;
                    }
                    continue;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                    final boolean b = ocFunctionSymbol2.canBeCalledWithoutArguments();
                    if (b) {
                        continue;
                    }
                    list2.add(ocFunctionSymbol);
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
        }
        return list2;
    }
    
    @Nullable
    private static OCFunctionSymbol a(final Collection<OCFunctionSymbol> p0, final Project p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/jetbrains/cidr/lang/generate/OCMemberChooser;
        //     3: dup            
        //     4: aload_0        
        //     5: ldc             Lcom/jetbrains/cidr/lang/generate/OCMemberChooserObject;.class
        //     7: invokedynamic   fun:()Lcom/intellij/util/Function;
        //    12: invokestatic    com/intellij/util/containers/ContainerUtil.map2Array:(Ljava/util/Collection;Ljava/lang/Class;Lcom/intellij/util/Function;)[Ljava/lang/Object;
        //    15: checkcast       [Lcom/jetbrains/cidr/lang/generate/OCMemberChooserObject;
        //    18: iconst_0       
        //    19: iconst_0       
        //    20: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    23: aconst_null    
        //    24: aload_1        
        //    25: invokespecial   com/jetbrains/cidr/lang/generate/OCMemberChooser.<init>:([Lcom/jetbrains/cidr/lang/generate/OCMemberChooserObject;ZZLjava/util/List;Ljavax/swing/JComponent;Lcom/intellij/openapi/project/Project;)V
        //    28: astore_2       
        //    29: aload_2        
        //    30: ldc             "Choose Base Class Constructor"
        //    32: invokevirtual   com/jetbrains/cidr/lang/generate/OCMemberChooser.setTitle:(Ljava/lang/String;)V
        //    35: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //    38: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //    43: ifne            70
        //    46: aload_2        
        //    47: invokevirtual   com/jetbrains/cidr/lang/generate/OCMemberChooser.show:()V
        //    50: aload_2        
        //    51: invokevirtual   com/jetbrains/cidr/lang/generate/OCMemberChooser.getExitCode:()I
        //    54: ifeq            77
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    63: athrow         
        //    64: aconst_null    
        //    65: areturn        
        //    66: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    69: athrow         
        //    70: aload_2        
        //    71: invokevirtual   com/jetbrains/cidr/lang/generate/OCMemberChooser.getDisposable:()Lcom/intellij/openapi/Disposable;
        //    74: invokestatic    com/intellij/openapi/util/Disposer.dispose:(Lcom/intellij/openapi/Disposable;)V
        //    77: aload_2        
        //    78: invokevirtual   com/jetbrains/cidr/lang/generate/OCMemberChooser.getChosenElements:()Ljava/util/List;
        //    81: astore_3       
        //    82: getstatic       com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.$assertionsDisabled:Z
        //    85: ifne            128
        //    88: aload_3        
        //    89: ifnull          116
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    98: athrow         
        //    99: aload_3        
        //   100: invokeinterface java/util/List.size:()I
        //   105: iconst_1       
        //   106: if_icmpeq       128
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   115: athrow         
        //   116: new             Ljava/lang/AssertionError;
        //   119: dup            
        //   120: invokespecial   java/lang/AssertionError.<init>:()V
        //   123: athrow         
        //   124: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateConstructorHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   127: athrow         
        //   128: aload_3        
        //   129: iconst_0       
        //   130: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   135: checkcast       Lcom/jetbrains/cidr/lang/generate/OCMemberChooserObject;
        //   138: invokevirtual   com/jetbrains/cidr/lang/generate/OCMemberChooserObject.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   141: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   144: areturn        
        //    Signature:
        //  (Ljava/util/Collection<Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;>;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  29     57     60     64     Ljava/lang/IllegalStateException;
        //  46     66     66     70     Ljava/lang/IllegalStateException;
        //  82     92     95     99     Ljava/lang/IllegalStateException;
        //  88     109    112    116    Ljava/lang/IllegalStateException;
        //  99     124    124    128    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0099:
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
    
    protected boolean addParametersForBaseClasses() {
        return true;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCGenerateConstructorHandler.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
