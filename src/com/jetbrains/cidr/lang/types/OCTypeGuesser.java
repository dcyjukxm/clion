// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.intellij.psi.codeStyle.NameUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import java.util.Iterator;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTable;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.Set;

public class OCTypeGuesser
{
    private static Set<String> KNOWN_MUTABLE_PREFIXES;
    
    @Nullable
    public static OCType getMethodGuessedReturnType(@NotNull final OCMethodSymbol p0, @NotNull final OCObjectTypeContext p1, @Nullable final OCSendMessageExpression p2, @NotNull final PsiElement p3) {
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
        //    18: ldc             "method"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/OCTypeGuesser"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getMethodGuessedReturnType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "receiverContext"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/types/OCTypeGuesser"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getMethodGuessedReturnType"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "context"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/types/OCTypeGuesser"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "getMethodGuessedReturnType"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_0        
        //   133: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getName:()Ljava/lang/String;
        //   138: astore          4
        //   140: aload_1        
        //   141: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getOriginalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   144: astore          5
        //   146: aload           5
        //   148: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:()Z
        //   151: ifne            169
        //   154: aload           5
        //   156: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   159: ifeq            176
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aload           5
        //   171: astore          6
        //   173: goto            185
        //   176: aload_1        
        //   177: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   180: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   183: astore          6
        //   185: ldc             "id"
        //   187: aload_0        
        //   188: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   193: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //   196: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   199: istore          7
        //   201: aload           6
        //   203: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   206: ifeq            389
        //   209: aload           4
        //   211: ldc             "mutableCopy"
        //   213: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.startsWithWord:(Ljava/lang/String;Ljava/lang/String;)Z
        //   216: ifeq            389
        //   219: goto            226
        //   222: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   225: athrow         
        //   226: aload_1        
        //   227: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   230: aload_3        
        //   231: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getSimpleName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   234: astore          8
        //   236: aload           8
        //   238: invokevirtual   java/lang/String.length:()I
        //   241: iconst_2       
        //   242: if_icmple       389
        //   245: ldc             "NSMutableCopying"
        //   247: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROTOCOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   250: aload_0        
        //   251: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getProject:()Lcom/intellij/openapi/project/Project;
        //   256: aload_0        
        //   257: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   262: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolImpl.findSymbolDefinition:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   265: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;
        //   268: astore          9
        //   270: aload           8
        //   272: iconst_0       
        //   273: iconst_2       
        //   274: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   277: astore          10
        //   279: aload           9
        //   281: ifnull          389
        //   284: aload           6
        //   286: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   289: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   292: aload           9
        //   294: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.implementsProtocol:(Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;)Z
        //   297: ifeq            389
        //   300: goto            307
        //   303: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   306: athrow         
        //   307: getstatic       com/jetbrains/cidr/lang/types/OCTypeGuesser.KNOWN_MUTABLE_PREFIXES:Ljava/util/Set;
        //   310: aload           10
        //   312: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   317: ifeq            389
        //   320: goto            327
        //   323: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   326: athrow         
        //   327: new             Ljava/lang/StringBuilder;
        //   330: dup            
        //   331: invokespecial   java/lang/StringBuilder.<init>:()V
        //   334: aload           10
        //   336: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   339: ldc             "Mutable"
        //   341: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   344: aload           8
        //   346: iconst_2       
        //   347: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   350: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   353: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   356: aload_3        
        //   357: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   362: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.resolvedFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   365: astore          11
        //   367: aload           11
        //   369: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   372: ifeq            387
        //   375: aload           11
        //   377: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   380: goto            388
        //   383: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   386: athrow         
        //   387: aconst_null    
        //   388: areturn        
        //   389: iload           7
        //   391: ifeq            694
        //   394: aload           6
        //   396: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   399: ifeq            464
        //   402: goto            409
        //   405: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   408: athrow         
        //   409: aload_0        
        //   410: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isConstructorMethod:()Z
        //   415: ifne            457
        //   418: goto            425
        //   421: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   424: athrow         
        //   425: aload_0        
        //   426: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isClassConstructorMethod:()Z
        //   431: ifne            457
        //   434: goto            441
        //   437: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   440: athrow         
        //   441: aload_0        
        //   442: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isFactoryMethod:()Z
        //   447: ifeq            464
        //   450: goto            457
        //   453: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   456: athrow         
        //   457: aload           6
        //   459: areturn        
        //   460: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   463: athrow         
        //   464: aload           4
        //   466: ldc             "copy"
        //   468: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.startsWithWord:(Ljava/lang/String;Ljava/lang/String;)Z
        //   471: ifne            491
        //   474: aload           4
        //   476: ldc             "Copy"
        //   478: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   481: ifeq            498
        //   484: goto            491
        //   487: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   490: athrow         
        //   491: aload           6
        //   493: areturn        
        //   494: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   497: athrow         
        //   498: aload           4
        //   500: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.isOfficialNamingConvention:(Ljava/lang/String;)Z
        //   503: ifne            540
        //   506: ldc             "sharedInstance"
        //   508: aload           4
        //   510: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   513: ifne            540
        //   516: goto            523
        //   519: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   522: athrow         
        //   523: ldc             "instance"
        //   525: aload           4
        //   527: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   530: ifeq            566
        //   533: goto            540
        //   536: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   539: athrow         
        //   540: aload           5
        //   542: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:()Z
        //   545: ifeq            563
        //   548: goto            555
        //   551: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   554: athrow         
        //   555: aconst_null    
        //   556: goto            565
        //   559: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   562: athrow         
        //   563: aload           6
        //   565: areturn        
        //   566: aload_0        
        //   567: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getName:()Ljava/lang/String;
        //   572: invokestatic    com/jetbrains/cidr/lang/resolve/OCSelectorAdHocResolver.isPerformSelectorMethod:(Ljava/lang/String;)Z
        //   575: ifeq            694
        //   578: aload_2        
        //   579: ifnull          694
        //   582: goto            589
        //   585: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   588: athrow         
        //   589: aload_2        
        //   590: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArgumentExpressions:()Ljava/util/List;
        //   595: astore          8
        //   597: aload           8
        //   599: invokeinterface java/util/List.isEmpty:()Z
        //   604: ifeq            615
        //   607: aconst_null    
        //   608: goto            626
        //   611: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   614: athrow         
        //   615: aload           8
        //   617: iconst_0       
        //   618: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   623: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   626: astore          9
        //   628: aload           9
        //   630: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSelectorExpression;
        //   633: ifeq            694
        //   636: aload           9
        //   638: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getReference:()Lcom/intellij/psi/PsiReference;
        //   643: checkcast       Lcom/jetbrains/cidr/lang/psi/OCPolyVariantReference;
        //   646: invokeinterface com/jetbrains/cidr/lang/psi/OCPolyVariantReference.resolveToSymbols:()Ljava/util/List;
        //   651: astore          10
        //   653: aload           10
        //   655: invokeinterface java/util/List.size:()I
        //   660: iconst_1       
        //   661: if_icmpne       694
        //   664: aload           10
        //   666: iconst_0       
        //   667: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   672: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   675: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   680: aload_3        
        //   681: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   686: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   689: areturn        
        //   690: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   693: athrow         
        //   694: aconst_null    
        //   695: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  146    162    165    169    Ljava/lang/IllegalArgumentException;
        //  201    219    222    226    Ljava/lang/IllegalArgumentException;
        //  279    300    303    307    Ljava/lang/IllegalArgumentException;
        //  284    320    323    327    Ljava/lang/IllegalArgumentException;
        //  367    383    383    387    Ljava/lang/IllegalArgumentException;
        //  389    402    405    409    Ljava/lang/IllegalArgumentException;
        //  394    418    421    425    Ljava/lang/IllegalArgumentException;
        //  409    434    437    441    Ljava/lang/IllegalArgumentException;
        //  425    450    453    457    Ljava/lang/IllegalArgumentException;
        //  441    460    460    464    Ljava/lang/IllegalArgumentException;
        //  464    484    487    491    Ljava/lang/IllegalArgumentException;
        //  474    494    494    498    Ljava/lang/IllegalArgumentException;
        //  498    516    519    523    Ljava/lang/IllegalArgumentException;
        //  506    533    536    540    Ljava/lang/IllegalArgumentException;
        //  523    548    551    555    Ljava/lang/IllegalArgumentException;
        //  540    559    559    563    Ljava/lang/IllegalArgumentException;
        //  566    582    585    589    Ljava/lang/IllegalArgumentException;
        //  597    611    611    615    Ljava/lang/IllegalArgumentException;
        //  653    690    690    694    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0409:
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
    
    public static boolean isNewInstanceCreation(final OCExpression ocExpression) {
        if (ocExpression instanceof OCSendMessageExpression) {
            final String messageSelector = ((OCSendMessageExpression)ocExpression).getMessageSelector();
            try {
                if (a(messageSelector)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (isOfficialNamingConvention(messageSelector)) {
                    return isNewInstanceCreation(((OCSendMessageExpression)ocExpression).getReceiverExpression());
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public static boolean isInitializerName(final String s) {
        return OCElementUtil.startsWithWord(s, "init");
    }
    
    private static boolean a(final String s) {
        Label_0025: {
            try {
                if (OCElementUtil.startsWithWord(s, "alloc")) {
                    break Label_0025;
                }
                final String s2 = s;
                final String s3 = "new";
                final boolean b = OCElementUtil.startsWithWord(s2, s3);
                if (b) {
                    break Label_0025;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final String s2 = s;
                final String s3 = "new";
                final boolean b = OCElementUtil.startsWithWord(s2, s3);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public static boolean isOfficialNamingConvention(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/String;)Z
        //     4: ifne            71
        //     7: aload_0        
        //     8: ldc             "init"
        //    10: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.startsWithWord:(Ljava/lang/String;Ljava/lang/String;)Z
        //    13: ifne            71
        //    16: goto            23
        //    19: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    22: athrow         
        //    23: aload_0        
        //    24: ldc             "autorelease"
        //    26: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.startsWithWord:(Ljava/lang/String;Ljava/lang/String;)Z
        //    29: ifne            71
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: aload_0        
        //    40: ldc             "retain"
        //    42: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.startsWithWord:(Ljava/lang/String;Ljava/lang/String;)Z
        //    45: ifne            71
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: aload_0        
        //    56: ldc             "self"
        //    58: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.startsWithWord:(Ljava/lang/String;Ljava/lang/String;)Z
        //    61: ifeq            79
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: iconst_1       
        //    72: goto            80
        //    75: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: iconst_0       
        //    80: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      16     19     23     Ljava/lang/IllegalArgumentException;
        //  7      32     35     39     Ljava/lang/IllegalArgumentException;
        //  23     48     51     55     Ljava/lang/IllegalArgumentException;
        //  39     64     67     71     Ljava/lang/IllegalArgumentException;
        //  55     75     75     79     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
    private static List<OCSymbol> a(final OCSymbol ocSymbol, final OCFile ocFile) {
        try {
            if (ocFile == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<FileSymbolTable> allTablesForFile = FileSymbolTablesCache.getInstance(ocFile.getProject()).allTablesForFile(ocFile);
        final ArrayList<OCSymbol> list = new ArrayList<OCSymbol>();
        for (final FileSymbolTable fileSymbolTable : allTablesForFile) {
            final boolean[] array = { false };
            try {
                fileSymbolTable.processFile((Processor<OCSymbol>)new Processor<OCSymbol>() {
                    public boolean process(final OCSymbol ocSymbol2) {
                        if (ocSymbol2 == ocSymbol) {
                            array[0] = true;
                            return false;
                        }
                        if (ocSymbol2.getKind() == OCSymbolKind.ENUM_CONST) {
                            list.add(ocSymbol2);
                        }
                        else {
                            list.clear();
                        }
                        if (ocSymbol2.getKind() == OCSymbolKind.NAMESPACE) {
                            final CommonProcessors.CollectProcessor collectProcessor = new CommonProcessors.CollectProcessor();
                            ((OCNamespaceSymbol)ocSymbol2).processMembers(null, (Processor<OCSymbol>)collectProcessor);
                            final List list = (List)collectProcessor.getResults();
                            Collections.sort((List<Object>)list, (ocSymbol, ocSymbol3) -> OCSymbolOffsetUtil.compare(ocSymbol.getComplexOffset(), ocSymbol3.getComplexOffset()));
                            if (!ContainerUtil.process(list, (Processor)this)) {
                                return false;
                            }
                        }
                        return true;
                    }
                });
                if (!array[0]) {
                    continue;
                }
                final ArrayList<OCSymbol> list2 = list;
                final boolean b = list2.isEmpty();
                if (!b) {
                    return list;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final ArrayList<OCSymbol> list2 = list;
                final boolean b = list2.isEmpty();
                if (!b) {
                    return list;
                }
                continue;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return null;
    }
    
    public static void processGuessedEnumConsts(final OCIntType ocIntType, final Processor<OCSymbol> processor, final PsiElement psiElement) {
        final String aliasName = ocIntType.getAliasName();
        if (aliasName != null) {
            final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
            OCSymbolReference.getLocalReference(OCQualifiedName.parse(aliasName), psiElement).processPossibleSymbols((Processor<OCSymbol>)findFirstProcessor, psiElement.getContainingFile());
            final OCSymbol ocSymbol = (OCSymbol)findFirstProcessor.getFoundValue();
            try {
                if (!(ocSymbol instanceof OCDeclaratorSymbol) || ocSymbol.getKind() != OCSymbolKind.TYPEDEF) {
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final List<OCSymbol> a = a(ocSymbol, ocSymbol.getContainingOCFile());
            if (a != null) {
                final Iterator<OCSymbol<?>> iterator = a.iterator();
                while (iterator.hasNext()) {
                    processor.process((Object)iterator.next());
                }
                return;
            }
            final String unpluralize = StringUtil.unpluralize(aliasName);
            String s = null;
            Label_0164: {
                try {
                    if (unpluralize != null) {
                        s = unpluralize;
                        break Label_0164;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                s = aliasName;
            }
            OCResolveUtil.processGlobalSymbols(null, psiElement, (Processor<OCSymbol>)(p3 -> {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_3        
                //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
                //     4: ifeq            91
                //     7: aload_3        
                //     8: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //    13: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM_CONST:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //    16: if_acmpne       91
                //    19: goto            26
                //    22: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    25: athrow         
                //    26: aload_3        
                //    27: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
                //    32: astore          4
                //    34: aload           4
                //    36: aload_0        
                //    37: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/String;[Ljava/lang/String;)Z
                //    40: ifeq            91
                //    43: aload_3        
                //    44: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
                //    49: aload_1        
                //    50: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
                //    55: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
                //    58: ifeq            91
                //    61: goto            68
                //    64: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    67: athrow         
                //    68: aload_2        
                //    69: aload_3        
                //    70: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
                //    75: ifne            91
                //    78: goto            85
                //    81: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    84: athrow         
                //    85: iconst_0       
                //    86: ireturn        
                //    87: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    90: athrow         
                //    91: iconst_1       
                //    92: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      19     22     26     Ljava/lang/IllegalArgumentException;
                //  34     61     64     68     Ljava/lang/IllegalArgumentException;
                //  43     78     81     85     Ljava/lang/IllegalArgumentException;
                //  68     87     87     91     Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0068:
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
            }));
        }
    }
    
    private static boolean a(final String p0, final String[] p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/intellij/psi/codeStyle/NameUtil.nameToWords:(Ljava/lang/String;)[Ljava/lang/String;
        //     4: astore_2       
        //     5: iconst_0       
        //     6: istore_3       
        //     7: ldc             "k"
        //     9: aload_2        
        //    10: iconst_0       
        //    11: aaload         
        //    12: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    15: istore          4
        //    17: iload           4
        //    19: ifeq            106
        //    22: iconst_1       
        //    23: istore_3       
        //    24: aload_1        
        //    25: astore          5
        //    27: aload           5
        //    29: arraylength    
        //    30: istore          6
        //    32: iconst_0       
        //    33: istore          7
        //    35: iload           7
        //    37: iload           6
        //    39: if_icmpge       104
        //    42: aload           5
        //    44: iload           7
        //    46: aaload         
        //    47: astore          8
        //    49: iload_3        
        //    50: aload_2        
        //    51: arraylength    
        //    52: if_icmplt       61
        //    55: iconst_0       
        //    56: ireturn        
        //    57: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    60: athrow         
        //    61: ldc             "ID"
        //    63: aload           8
        //    65: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    68: ifne            95
        //    71: aload_2        
        //    72: iload_3        
        //    73: aaload         
        //    74: aload           8
        //    76: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    79: ifne            95
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: iconst_0       
        //    90: ireturn        
        //    91: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    94: athrow         
        //    95: iinc            3, 1
        //    98: iinc            7, 1
        //   101: goto            35
        //   104: iconst_1       
        //   105: ireturn        
        //   106: aload_1        
        //   107: astore          5
        //   109: aload           5
        //   111: arraylength    
        //   112: istore          6
        //   114: iconst_0       
        //   115: istore          7
        //   117: iload           7
        //   119: iload           6
        //   121: if_icmpge       191
        //   124: aload           5
        //   126: iload           7
        //   128: aaload         
        //   129: astore          8
        //   131: iload_3        
        //   132: aload_2        
        //   133: arraylength    
        //   134: if_icmpge       182
        //   137: aload_2        
        //   138: iload_3        
        //   139: aaload         
        //   140: aload           8
        //   142: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   145: ifne            182
        //   148: goto            155
        //   151: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   154: athrow         
        //   155: ldc             "ID"
        //   157: aload           8
        //   159: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   162: ifne            182
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: iinc            3, 1
        //   175: goto            131
        //   178: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: iinc            3, 1
        //   185: iinc            7, 1
        //   188: goto            117
        //   191: iload_3        
        //   192: aload_2        
        //   193: arraylength    
        //   194: if_icmpgt       205
        //   197: iconst_1       
        //   198: goto            206
        //   201: invokestatic    com/jetbrains/cidr/lang/types/OCTypeGuesser.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   204: athrow         
        //   205: iconst_0       
        //   206: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  49     57     57     61     Ljava/lang/IllegalArgumentException;
        //  61     82     85     89     Ljava/lang/IllegalArgumentException;
        //  71     91     91     95     Ljava/lang/IllegalArgumentException;
        //  131    148    151    155    Ljava/lang/IllegalArgumentException;
        //  137    165    168    172    Ljava/lang/IllegalArgumentException;
        //  155    178    178    182    Ljava/lang/IllegalArgumentException;
        //  191    201    201    205    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0155:
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
    
    static {
        OCTypeGuesser.KNOWN_MUTABLE_PREFIXES = (Set<String>)ContainerUtil.set((Object[])new String[] { "NS", "CN", "UI" });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
