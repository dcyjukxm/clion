// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.openapi.util.Ref;
import com.intellij.util.FilteringProcessor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Pair;
import java.util.Stack;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceLikeSymbol;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

private static class InnerProcessor implements Processor<OCSymbol>
{
    private String myMemberName;
    private final boolean myTypesOnly;
    private final boolean myGoTransitive;
    private final Set<String> myAlreadyProcessed;
    private final OCTypeSubstitution mySubstitution;
    private Set<OCNamespaceLikeSymbol> myProcessed;
    private Stack<Pair<OCNamespaceLikeSymbol, Set<String>>> myWorkset;
    private Processor<OCSymbol> myProcessor;
    private Processor<OCSymbol> myFilteringProcessor;
    private final Condition<OCSymbol> myCondition;
    @NotNull
    private final OCResolveContext myContext;
    
    public InnerProcessor(final String myMemberName, final boolean myTypesOnly, final boolean myGoTransitive, final Set<String> myAlreadyProcessed, final OCTypeSubstitution mySubstitution, final Set<OCNamespaceLikeSymbol> myProcessed, final Stack<Pair<OCNamespaceLikeSymbol, Set<String>>> myWorkset, final Processor<OCSymbol> myProcessor, final Condition<OCSymbol> myCondition, @NotNull final OCResolveContext myContext) {
        if (myContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor", "<init>"));
        }
        this.myMemberName = myMemberName;
        this.myTypesOnly = myTypesOnly;
        this.myGoTransitive = myGoTransitive;
        this.myAlreadyProcessed = myAlreadyProcessed;
        this.mySubstitution = mySubstitution;
        this.myProcessed = myProcessed;
        this.myWorkset = myWorkset;
        this.myProcessor = myProcessor;
        this.myFilteringProcessor = (Processor<OCSymbol>)new FilteringProcessor((Condition)myCondition, (Processor)myProcessor);
        this.myCondition = myCondition;
        this.myContext = myContext;
    }
    
    public boolean process(final OCSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //     4: ifeq            204
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myProcessed:Ljava/util/Set;
        //    11: aload_1        
        //    12: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //    17: ifne            204
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: aload_0        
        //    28: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myProcessed:Ljava/util/Set;
        //    31: aload_1        
        //    32: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol;
        //    35: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //    40: pop            
        //    41: aload_1        
        //    42: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    45: ifeq            93
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: aload_0        
        //    56: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.mySubstitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //    59: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.ID:Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
        //    62: if_acmpeq       93
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    71: athrow         
        //    72: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    75: dup            
        //    76: aload_1        
        //    77: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    80: aload_0        
        //    81: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.mySubstitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //    84: iconst_0       
        //    85: aload_0        
        //    86: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    89: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //    92: astore_1       
        //    93: aload_1        
        //    94: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //    97: astore_2       
        //    98: new             Lcom/intellij/openapi/util/Ref;
        //   101: dup            
        //   102: iconst_0       
        //   103: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   106: invokespecial   com/intellij/openapi/util/Ref.<init>:(Ljava/lang/Object;)V
        //   109: astore_3       
        //   110: new             Lcom/intellij/util/FilteringProcessor;
        //   113: dup            
        //   114: getstatic       com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.WITHOUT_QUALIFIER:Lcom/intellij/openapi/util/Condition;
        //   117: aload_0        
        //   118: aload_3        
        //   119: invokedynamic   process:(Lcom/jetbrains/cidr/lang/types/OCStructType$InnerProcessor;Lcom/intellij/openapi/util/Ref;)Lcom/intellij/util/Processor;
        //   124: invokespecial   com/intellij/util/FilteringProcessor.<init>:(Lcom/intellij/openapi/util/Condition;Lcom/intellij/util/Processor;)V
        //   127: astore          4
        //   129: aload_2        
        //   130: aload_0        
        //   131: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myMemberName:Ljava/lang/String;
        //   134: aload           4
        //   136: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.processMembers:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
        //   139: ifne            148
        //   142: iconst_0       
        //   143: ireturn        
        //   144: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: aload_3        
        //   149: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   152: checkcast       Ljava/lang/Boolean;
        //   155: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   158: ifeq            175
        //   161: aload_0        
        //   162: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myGoTransitive:Z
        //   165: ifeq            202
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: aload_0        
        //   176: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myWorkset:Ljava/util/Stack;
        //   179: new             Lcom/intellij/openapi/util/Pair;
        //   182: dup            
        //   183: aload_2        
        //   184: aload_0        
        //   185: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myAlreadyProcessed:Ljava/util/Set;
        //   188: invokespecial   com/intellij/openapi/util/Pair.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   191: invokevirtual   java/util/Stack.push:(Ljava/lang/Object;)Ljava/lang/Object;
        //   194: pop            
        //   195: goto            202
        //   198: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   201: athrow         
        //   202: iconst_1       
        //   203: ireturn        
        //   204: aload_1        
        //   205: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   208: ifeq            486
        //   211: aload_1        
        //   212: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   217: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isTypedefOrAlias:()Z
        //   220: ifeq            486
        //   223: goto            230
        //   226: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   229: athrow         
        //   230: aload_1        
        //   231: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   236: astore_2       
        //   237: aload_2        
        //   238: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   241: ifeq            331
        //   244: aload_0        
        //   245: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myFilteringProcessor:Lcom/intellij/util/Processor;
        //   248: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
        //   251: dup            
        //   252: aconst_null    
        //   253: aconst_null    
        //   254: lconst_0       
        //   255: aload_0        
        //   256: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myMemberName:Ljava/lang/String;
        //   259: aconst_null    
        //   260: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   263: aconst_null    
        //   264: iconst_0       
        //   265: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;Lcom/intellij/openapi/util/TextRange;Z)V
        //   268: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   273: ifne            289
        //   276: goto            283
        //   279: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   282: athrow         
        //   283: iconst_0       
        //   284: ireturn        
        //   285: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   288: athrow         
        //   289: aload_0        
        //   290: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myFilteringProcessor:Lcom/intellij/util/Processor;
        //   293: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   296: dup            
        //   297: aconst_null    
        //   298: aconst_null    
        //   299: iconst_0       
        //   300: aconst_null    
        //   301: aload_0        
        //   302: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myMemberName:Ljava/lang/String;
        //   305: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   308: new             Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   311: dup            
        //   312: aload_0        
        //   313: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myMemberName:Ljava/lang/String;
        //   316: invokespecial   com/jetbrains/cidr/lang/types/OCMagicType.<init>:(Ljava/lang/String;)V
        //   319: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_VALUE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   322: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;ILcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Ljava/lang/String;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //   325: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   330: ireturn        
        //   331: aload_2        
        //   332: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   335: ifeq            437
        //   338: aload_0        
        //   339: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.mySubstitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   342: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.ID:Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
        //   345: if_acmpeq       425
        //   348: goto            355
        //   351: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   354: athrow         
        //   355: aload_2        
        //   356: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   359: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
        //   362: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   367: astore_3       
        //   368: aload_3        
        //   369: invokeinterface java/util/Iterator.hasNext:()Z
        //   374: ifeq            422
        //   377: aload_3        
        //   378: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   383: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   386: astore          4
        //   388: aload_0        
        //   389: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   392: dup            
        //   393: aload           4
        //   395: aload_0        
        //   396: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.mySubstitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   399: iconst_0       
        //   400: aload_0        
        //   401: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   404: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   407: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.process:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   410: ifne            419
        //   413: iconst_0       
        //   414: ireturn        
        //   415: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   418: athrow         
        //   419: goto            368
        //   422: goto            483
        //   425: aload_2        
        //   426: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   429: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
        //   432: aload_0        
        //   433: invokestatic    com/intellij/util/containers/ContainerUtil.process:(Ljava/util/List;Lcom/intellij/util/Processor;)Z
        //   436: ireturn        
        //   437: aload_2        
        //   438: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   441: ifeq            483
        //   444: aload_0        
        //   445: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   448: aload_2        
        //   449: checkcast       Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   452: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   455: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.substitute:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   458: aload_2        
        //   459: checkcast       Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   462: aload_0        
        //   463: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   466: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getReference:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   469: iconst_1       
        //   470: iconst_1       
        //   471: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.resolveToSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;ZZ)Ljava/util/List;
        //   474: aload_0        
        //   475: invokestatic    com/intellij/util/containers/ContainerUtil.process:(Ljava/util/List;Lcom/intellij/util/Processor;)Z
        //   478: ireturn        
        //   479: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   482: athrow         
        //   483: goto            671
        //   486: aload_1        
        //   487: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   490: ifeq            601
        //   493: aload_1        
        //   494: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
        //   497: ifeq            518
        //   500: goto            507
        //   503: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   506: athrow         
        //   507: aload_1        
        //   508: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
        //   511: goto            519
        //   514: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   517: athrow         
        //   518: aconst_null    
        //   519: astore_2       
        //   520: aload_0        
        //   521: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myFilteringProcessor:Lcom/intellij/util/Processor;
        //   524: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
        //   527: dup            
        //   528: aconst_null    
        //   529: aconst_null    
        //   530: lconst_0       
        //   531: aload_0        
        //   532: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myMemberName:Ljava/lang/String;
        //   535: aload_2        
        //   536: aconst_null    
        //   537: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   540: aconst_null    
        //   541: iconst_0       
        //   542: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;Lcom/intellij/openapi/util/TextRange;Z)V
        //   545: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   550: ifne            559
        //   553: iconst_0       
        //   554: ireturn        
        //   555: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   558: athrow         
        //   559: aload_0        
        //   560: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myFilteringProcessor:Lcom/intellij/util/Processor;
        //   563: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   566: dup            
        //   567: aconst_null    
        //   568: aconst_null    
        //   569: iconst_0       
        //   570: aconst_null    
        //   571: aload_0        
        //   572: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myMemberName:Ljava/lang/String;
        //   575: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   578: new             Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   581: dup            
        //   582: aload_0        
        //   583: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myMemberName:Ljava/lang/String;
        //   586: invokespecial   com/jetbrains/cidr/lang/types/OCMagicType.<init>:(Ljava/lang/String;)V
        //   589: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_VALUE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   592: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;ILcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Ljava/lang/String;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //   595: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   600: ireturn        
        //   601: aload_1        
        //   602: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
        //   605: ifeq            636
        //   608: aload_0        
        //   609: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   612: aload_1        
        //   613: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
        //   616: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol.getSymbolReference:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   619: iconst_1       
        //   620: aload_0        
        //   621: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myTypesOnly:Z
        //   624: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.resolveToSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;ZZ)Ljava/util/List;
        //   627: aload_0        
        //   628: invokestatic    com/intellij/util/containers/ContainerUtil.process:(Ljava/util/List;Lcom/intellij/util/Processor;)Z
        //   631: ireturn        
        //   632: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   635: athrow         
        //   636: aload_1        
        //   637: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceAliasSymbol;
        //   640: ifeq            671
        //   643: aload_0        
        //   644: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   647: aload_1        
        //   648: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceAliasSymbol;
        //   651: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceAliasSymbol.getNamespaceReference:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   654: iconst_1       
        //   655: aload_0        
        //   656: getfield        com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.myTypesOnly:Z
        //   659: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.resolveToSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;ZZ)Ljava/util/List;
        //   662: aload_0        
        //   663: invokestatic    com/intellij/util/containers/ContainerUtil.process:(Ljava/util/List;Lcom/intellij/util/Processor;)Z
        //   666: ireturn        
        //   667: invokestatic    com/jetbrains/cidr/lang/types/OCStructType$InnerProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   670: athrow         
        //   671: iconst_1       
        //   672: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      20     23     27     Ljava/lang/IllegalArgumentException;
        //  7      48     51     55     Ljava/lang/IllegalArgumentException;
        //  27     65     68     72     Ljava/lang/IllegalArgumentException;
        //  129    144    144    148    Ljava/lang/IllegalArgumentException;
        //  148    168    171    175    Ljava/lang/IllegalArgumentException;
        //  161    195    198    202    Ljava/lang/IllegalArgumentException;
        //  204    223    226    230    Ljava/lang/IllegalArgumentException;
        //  237    276    279    283    Ljava/lang/IllegalArgumentException;
        //  244    285    285    289    Ljava/lang/IllegalArgumentException;
        //  331    348    351    355    Ljava/lang/IllegalArgumentException;
        //  388    415    415    419    Ljava/lang/IllegalArgumentException;
        //  437    479    479    483    Ljava/lang/IllegalArgumentException;
        //  486    500    503    507    Ljava/lang/IllegalArgumentException;
        //  493    514    514    518    Ljava/lang/IllegalArgumentException;
        //  520    555    555    559    Ljava/lang/IllegalArgumentException;
        //  601    632    632    636    Ljava/lang/IllegalArgumentException;
        //  636    667    667    671    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
