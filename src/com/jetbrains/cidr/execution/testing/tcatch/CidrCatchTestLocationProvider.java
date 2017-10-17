// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import com.intellij.execution.Location;
import java.util.List;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.testframework.sm.runner.SMTestLocator;

public class CidrCatchTestLocationProvider implements SMTestLocator
{
    public static final String PROTOCOL = "catch";
    public static final CidrCatchTestLocationProvider INSTANCE;
    
    @NotNull
    @Override
    public List<Location> getLocation(@NotNull final String p0, @NotNull final String p1, @NotNull final Project p2, @NotNull final GlobalSearchScope p3) {
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
        //    18: ldc             "protocol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getLocation"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "path"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getLocation"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   112: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "getLocation"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload           4
        //   134: ifnonnull       177
        //   137: new             Ljava/lang/IllegalArgumentException;
        //   140: dup            
        //   141: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   143: ldc             3
        //   145: anewarray       Ljava/lang/Object;
        //   148: dup            
        //   149: ldc             0
        //   151: ldc             "scope"
        //   153: aastore        
        //   154: dup            
        //   155: ldc             1
        //   157: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider"
        //   159: aastore        
        //   160: dup            
        //   161: ldc             2
        //   163: ldc             "getLocation"
        //   165: aastore        
        //   166: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   169: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   172: athrow         
        //   173: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: ldc             "catch"
        //   179: aload_1        
        //   180: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   183: ifne            235
        //   186: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   189: dup            
        //   190: ifnonnull       234
        //   193: goto            200
        //   196: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: new             Ljava/lang/IllegalStateException;
        //   203: dup            
        //   204: ldc             "@NotNull method %s.%s must not return null"
        //   206: ldc             2
        //   208: anewarray       Ljava/lang/Object;
        //   211: dup            
        //   212: ldc             0
        //   214: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider"
        //   216: aastore        
        //   217: dup            
        //   218: ldc             1
        //   220: ldc             "getLocation"
        //   222: aastore        
        //   223: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   226: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   229: athrow         
        //   230: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   233: athrow         
        //   234: areturn        
        //   235: aload_2        
        //   236: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil.splitPath:(Ljava/lang/String;)Lcom/intellij/openapi/util/Pair;
        //   239: astore          5
        //   241: aload           5
        //   243: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   246: checkcast       Ljava/lang/String;
        //   249: aload_3        
        //   250: aload           4
        //   252: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil.findCatchTestSymbol:(Ljava/lang/String;Lcom/intellij/openapi/project/Project;Lcom/intellij/psi/search/GlobalSearchScope;)Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCache;
        //   255: astore          6
        //   257: aload           6
        //   259: ifnonnull       311
        //   262: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   265: dup            
        //   266: ifnonnull       310
        //   269: goto            276
        //   272: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   275: athrow         
        //   276: new             Ljava/lang/IllegalStateException;
        //   279: dup            
        //   280: ldc             "@NotNull method %s.%s must not return null"
        //   282: ldc             2
        //   284: anewarray       Ljava/lang/Object;
        //   287: dup            
        //   288: ldc             0
        //   290: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider"
        //   292: aastore        
        //   293: dup            
        //   294: ldc             1
        //   296: ldc             "getLocation"
        //   298: aastore        
        //   299: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   302: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   305: athrow         
        //   306: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   309: athrow         
        //   310: areturn        
        //   311: aload           5
        //   313: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   316: checkcast       Ljava/lang/Integer;
        //   319: invokevirtual   java/lang/Integer.intValue:()I
        //   322: ifle            579
        //   325: aload           6
        //   327: invokevirtual   com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCache.getDeclarator:()Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   330: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   335: astore          7
        //   337: aload           7
        //   339: ifnull          579
        //   342: aload_3        
        //   343: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   346: aload           7
        //   348: invokevirtual   com/intellij/psi/PsiDocumentManager.getDocument:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/editor/Document;
        //   351: astore          8
        //   353: aload           8
        //   355: ifnull          579
        //   358: aload           5
        //   360: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   363: checkcast       Ljava/lang/Integer;
        //   366: invokevirtual   java/lang/Integer.intValue:()I
        //   369: iconst_1       
        //   370: isub           
        //   371: istore          9
        //   373: iload           9
        //   375: aload           8
        //   377: invokeinterface com/intellij/openapi/editor/Document.getLineCount:()I
        //   382: if_icmpge       579
        //   385: aload           8
        //   387: iload           9
        //   389: invokeinterface com/intellij/openapi/editor/Document.getLineStartOffset:(I)I
        //   394: iconst_1       
        //   395: iadd           
        //   396: istore          10
        //   398: aload           8
        //   400: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //   405: astore          11
        //   407: iload           10
        //   409: aload           11
        //   411: invokeinterface java/lang/CharSequence.length:()I
        //   416: if_icmpge       451
        //   419: aload           11
        //   421: iload           10
        //   423: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   428: invokestatic    com/intellij/openapi/util/text/StringUtil.isWhiteSpace:(C)Z
        //   431: ifeq            451
        //   434: goto            441
        //   437: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   440: athrow         
        //   441: iinc            10, 1
        //   444: goto            407
        //   447: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   450: athrow         
        //   451: aload           7
        //   453: iload           10
        //   455: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   460: astore          12
        //   462: aload           12
        //   464: ifnull          579
        //   467: aload           12
        //   469: iconst_0       
        //   470: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationProducer.findTestObject:(Lcom/intellij/psi/PsiElement;Z)Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestObject;
        //   473: astore          13
        //   475: aload           13
        //   477: ifnull          579
        //   480: aload           13
        //   482: invokevirtual   com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestObject.getTestInfo:()Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCache;
        //   485: ifnull          579
        //   488: goto            495
        //   491: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   494: athrow         
        //   495: aload           13
        //   497: invokevirtual   com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestObject.getTestInfo:()Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCache;
        //   500: invokevirtual   com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCache.getNotNullTestName:()Ljava/lang/String;
        //   503: aload           5
        //   505: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   508: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   511: ifeq            579
        //   514: goto            521
        //   517: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   520: athrow         
        //   521: new             Lcom/intellij/execution/PsiLocation;
        //   524: dup            
        //   525: aload           12
        //   527: invokespecial   com/intellij/execution/PsiLocation.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   530: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   533: dup            
        //   534: ifnonnull       578
        //   537: goto            544
        //   540: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   543: athrow         
        //   544: new             Ljava/lang/IllegalStateException;
        //   547: dup            
        //   548: ldc             "@NotNull method %s.%s must not return null"
        //   550: ldc             2
        //   552: anewarray       Ljava/lang/Object;
        //   555: dup            
        //   556: ldc             0
        //   558: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider"
        //   560: aastore        
        //   561: dup            
        //   562: ldc             1
        //   564: ldc             "getLocation"
        //   566: aastore        
        //   567: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   570: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   573: athrow         
        //   574: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   577: athrow         
        //   578: areturn        
        //   579: new             Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocation;
        //   582: dup            
        //   583: aload_3        
        //   584: aload           6
        //   586: invokespecial   com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocation.<init>:(Lcom/intellij/openapi/project/Project;Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCache;)V
        //   589: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   592: dup            
        //   593: ifnonnull       630
        //   596: new             Ljava/lang/IllegalStateException;
        //   599: dup            
        //   600: ldc             "@NotNull method %s.%s must not return null"
        //   602: ldc             2
        //   604: anewarray       Ljava/lang/Object;
        //   607: dup            
        //   608: ldc             0
        //   610: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider"
        //   612: aastore        
        //   613: dup            
        //   614: ldc             1
        //   616: ldc             "getLocation"
        //   618: aastore        
        //   619: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   622: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   625: athrow         
        //   626: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestLocationProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   629: athrow         
        //   630: areturn        
        //    Signature:
        //  (Ljava/lang/String;Ljava/lang/String;Lcom/intellij/openapi/project/Project;Lcom/intellij/psi/search/GlobalSearchScope;)Ljava/util/List<Lcom/intellij/execution/Location;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    173    173    177    Ljava/lang/IllegalArgumentException;
        //  177    193    196    200    Ljava/lang/IllegalArgumentException;
        //  186    230    230    234    Ljava/lang/IllegalArgumentException;
        //  257    269    272    276    Ljava/lang/IllegalArgumentException;
        //  262    306    306    310    Ljava/lang/IllegalArgumentException;
        //  407    434    437    441    Ljava/lang/IllegalArgumentException;
        //  419    447    447    451    Ljava/lang/IllegalArgumentException;
        //  475    488    491    495    Ljava/lang/IllegalArgumentException;
        //  480    514    517    521    Ljava/lang/IllegalArgumentException;
        //  495    537    540    544    Ljava/lang/IllegalArgumentException;
        //  521    574    574    578    Ljava/lang/IllegalArgumentException;
        //  579    626    626    630    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0495:
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
        INSTANCE = new CidrCatchTestLocationProvider();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
