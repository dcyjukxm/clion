// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;
import com.jetbrains.cidr.lang.generate.OCGenerateUtil;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.generate.OCCaretLocation;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateGetterSetterContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;

public abstract class OCGenerateCppGetterSetterHandlerBase extends OCCCppGenerateHandlerBase<OCStructSymbol, OCDeclaratorSymbol, OCGenerateGetterSetterContext>
{
    @Override
    protected String getMembersChooserTitle() {
        return "Select Fields to " + this.getActionTitle();
    }
    
    @Override
    protected boolean allowUnions() {
        return true;
    }
    
    @NotNull
    @Override
    protected List<OCGenerateUtil.Replacement> getReplacements(@NotNull final OCCaretLocation p0, @NotNull final OCGenerateGetterSetterContext p1, @NotNull final List<OCDeclaratorSymbol> p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getReplacements"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getReplacements"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   112: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "getReplacements"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_2        
        //   133: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   136: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   139: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   142: astore          4
        //   144: aload           4
        //   146: ifnull          164
        //   149: aload           4
        //   151: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   154: ifne            213
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   167: dup            
        //   168: ifnonnull       212
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: new             Ljava/lang/IllegalStateException;
        //   181: dup            
        //   182: ldc             "@NotNull method %s.%s must not return null"
        //   184: ldc             2
        //   186: anewarray       Ljava/lang/Object;
        //   189: dup            
        //   190: ldc             0
        //   192: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase"
        //   194: aastore        
        //   195: dup            
        //   196: ldc             1
        //   198: ldc             "getReplacements"
        //   200: aastore        
        //   201: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   204: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   207: athrow         
        //   208: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   211: athrow         
        //   212: areturn        
        //   213: aload           4
        //   215: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   218: astore          5
        //   220: new             Ljava/util/ArrayList;
        //   223: dup            
        //   224: invokespecial   java/util/ArrayList.<init>:()V
        //   227: astore          6
        //   229: new             Ljava/util/ArrayList;
        //   232: dup            
        //   233: invokespecial   java/util/ArrayList.<init>:()V
        //   236: astore          7
        //   238: aload_3        
        //   239: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   244: astore          8
        //   246: aload           8
        //   248: invokeinterface java/util/Iterator.hasNext:()Z
        //   253: ifeq            655
        //   256: aload           8
        //   258: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   263: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   266: astore          9
        //   268: aload           9
        //   270: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.getCppFieldNameWithoutPrefixAndSuffix:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Ljava/lang/String;
        //   273: astore          10
        //   275: aload           9
        //   277: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   280: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   283: ifeq            319
        //   286: aload           9
        //   288: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   291: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   294: ifeq            337
        //   297: goto            304
        //   300: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   303: athrow         
        //   304: aload           9
        //   306: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isFriendOrStatic:()Z
        //   309: ifne            337
        //   312: goto            319
        //   315: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   318: athrow         
        //   319: aload           9
        //   321: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   324: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   327: ifeq            345
        //   330: goto            337
        //   333: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   336: athrow         
        //   337: iconst_1       
        //   338: goto            346
        //   341: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   344: athrow         
        //   345: iconst_0       
        //   346: istore          11
        //   348: aload           9
        //   350: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   353: aload           5
        //   355: iload           11
        //   357: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.getExtractExpressionType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //   360: astore          12
        //   362: aload           9
        //   364: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isStatic:()Z
        //   367: istore          13
        //   369: aload_2        
        //   370: aload           9
        //   372: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.needGetter:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Z
        //   375: ifeq            494
        //   378: aload           6
        //   380: new             Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //   383: dup            
        //   384: aload           9
        //   386: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.getCppGetterName:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Ljava/lang/String;
        //   389: invokespecial   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.<init>:(Ljava/lang/String;)V
        //   392: aload           12
        //   394: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.setReturnType:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //   397: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   400: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.setVisibility:(Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //   403: aload_2        
        //   404: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   407: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   410: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.setContainer:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //   413: iload           13
        //   415: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.setIsStatic:(Z)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //   418: iload           13
        //   420: ifne            438
        //   423: goto            430
        //   426: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   429: athrow         
        //   430: iconst_1       
        //   431: goto            439
        //   434: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   437: athrow         
        //   438: iconst_0       
        //   439: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.setIsConst:(Z)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //   442: aload           5
        //   444: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getProject:()Lcom/intellij/openapi/project/Project;
        //   449: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.get:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   452: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   457: pop            
        //   458: aload           7
        //   460: new             Ljava/lang/StringBuilder;
        //   463: dup            
        //   464: invokespecial   java/lang/StringBuilder.<init>:()V
        //   467: ldc             "{\n return "
        //   469: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   472: aload           9
        //   474: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getName:()Ljava/lang/String;
        //   477: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   480: ldc             ";\n}"
        //   482: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   485: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   488: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   493: pop            
        //   494: aload_2        
        //   495: aload           9
        //   497: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.needSetter:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Z
        //   500: ifeq            652
        //   503: aload           6
        //   505: new             Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //   508: dup            
        //   509: aload           9
        //   511: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.getCppSetterName:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Ljava/lang/String;
        //   514: invokespecial   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.<init>:(Ljava/lang/String;)V
        //   517: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PUBLIC:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   520: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.setVisibility:(Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //   523: aload_2        
        //   524: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   527: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   530: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.setContainer:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //   533: iload           13
        //   535: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.setIsStatic:(Z)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //   538: aload           12
        //   540: aload           10
        //   542: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.addParam:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;)Lcom/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder;
        //   545: aload           5
        //   547: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getProject:()Lcom/intellij/openapi/project/Project;
        //   552: invokevirtual   com/jetbrains/cidr/lang/util/OCFakeFunctionSymbolBuilder.get:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   555: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   560: pop            
        //   561: new             Ljava/lang/StringBuilder;
        //   564: dup            
        //   565: ldc             "{\n"
        //   567: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   570: astore          14
        //   572: aload           10
        //   574: aload           9
        //   576: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getName:()Ljava/lang/String;
        //   579: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   582: ifeq            613
        //   585: aload           14
        //   587: aload_2        
        //   588: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   591: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   594: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getName:()Ljava/lang/String;
        //   597: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   600: ldc             "::"
        //   602: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   605: pop            
        //   606: goto            613
        //   609: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   612: athrow         
        //   613: aload           14
        //   615: aload           9
        //   617: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getName:()Ljava/lang/String;
        //   620: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   623: ldc             " = "
        //   625: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   628: aload           10
        //   630: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   633: ldc             ";\n}"
        //   635: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   638: pop            
        //   639: aload           7
        //   641: aload           14
        //   643: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   646: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   651: pop            
        //   652: goto            246
        //   655: aload_1        
        //   656: aload           5
        //   658: aload_2        
        //   659: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //   662: aload           6
        //   664: aload           7
        //   666: aload_0        
        //   667: aload_2        
        //   668: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase.getInlinePolicy:(Lcom/jetbrains/cidr/lang/generate/actions/OCCppActionContext;)Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy;
        //   671: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.getNewFunctionsReplacements:(Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/jetbrains/cidr/lang/psi/OCStructLike;Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$InlinePolicy;)Ljava/util/List;
        //   674: dup            
        //   675: ifnonnull       712
        //   678: new             Ljava/lang/IllegalStateException;
        //   681: dup            
        //   682: ldc             "@NotNull method %s.%s must not return null"
        //   684: ldc             2
        //   686: anewarray       Ljava/lang/Object;
        //   689: dup            
        //   690: ldc             0
        //   692: ldc             "com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase"
        //   694: aastore        
        //   695: dup            
        //   696: ldc             1
        //   698: ldc             "getReplacements"
        //   700: aastore        
        //   701: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   704: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   707: athrow         
        //   708: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateCppGetterSetterHandlerBase.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   711: athrow         
        //   712: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/generate/OCCaretLocation;Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;>;)Ljava/util/List<Lcom/jetbrains/cidr/lang/generate/OCGenerateUtil$Replacement;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  144    157    160    164    Ljava/lang/IllegalArgumentException;
        //  149    171    174    178    Ljava/lang/IllegalArgumentException;
        //  164    208    208    212    Ljava/lang/IllegalArgumentException;
        //  275    297    300    304    Ljava/lang/IllegalArgumentException;
        //  286    312    315    319    Ljava/lang/IllegalArgumentException;
        //  304    330    333    337    Ljava/lang/IllegalArgumentException;
        //  319    341    341    345    Ljava/lang/IllegalArgumentException;
        //  369    423    426    430    Ljava/lang/IllegalArgumentException;
        //  378    434    434    438    Ljava/lang/IllegalArgumentException;
        //  572    606    609    613    Ljava/lang/IllegalArgumentException;
        //  655    708    708    712    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0164:
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
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
