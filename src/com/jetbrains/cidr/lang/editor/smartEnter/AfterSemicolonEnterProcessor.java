// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.lang.SmartEnterProcessorWithFixers;

public class AfterSemicolonEnterProcessor extends SmartEnterProcessorWithFixers.FixEnterProcessor
{
    @Override
    public boolean doEnter(@NotNull final PsiElement p0, @NotNull final PsiFile p1, @NotNull final Editor p2, final boolean p3) {
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
        //    18: ldc             "atCaret"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "doEnter"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "file"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "doEnter"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "editor"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "doEnter"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_1        
        //   133: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   136: ifne            287
        //   139: aload_1        
        //   140: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
        //   143: ifne            287
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: aload_1        
        //   154: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   157: ifeq            181
        //   160: goto            167
        //   163: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: aload_1        
        //   168: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   171: ifeq            287
        //   174: goto            181
        //   177: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   180: athrow         
        //   181: aload_1        
        //   182: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReturnStatement;
        //   185: ifne            287
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: aload_1        
        //   196: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBreakStatement;
        //   199: ifne            287
        //   202: goto            209
        //   205: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   208: athrow         
        //   209: aload_1        
        //   210: instanceof      Lcom/jetbrains/cidr/lang/psi/OCContinueStatement;
        //   213: ifne            287
        //   216: goto            223
        //   219: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   222: athrow         
        //   223: aload_1        
        //   224: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceAlias;
        //   227: ifne            287
        //   230: goto            237
        //   233: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   236: athrow         
        //   237: aload_1        
        //   238: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppUsingStatement;
        //   241: ifne            287
        //   244: goto            251
        //   247: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: aload_1        
        //   252: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   255: ifeq            517
        //   258: goto            265
        //   261: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   264: athrow         
        //   265: aload_1        
        //   266: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   269: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getContainingClass:()Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   274: instanceof      Lcom/jetbrains/cidr/lang/psi/OCInterface;
        //   277: ifeq            517
        //   280: goto            287
        //   283: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   286: athrow         
        //   287: aload_1        
        //   288: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getErrorElementOffset:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiErrorElement;
        //   291: astore          5
        //   293: aload           5
        //   295: ifnonnull       306
        //   298: iconst_m1      
        //   299: goto            313
        //   302: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   305: athrow         
        //   306: aload           5
        //   308: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   313: istore          6
        //   315: aload_1        
        //   316: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   319: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   322: istore          7
        //   324: aload_3        
        //   325: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   330: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //   335: astore          8
        //   337: iload           6
        //   339: iflt            444
        //   342: iload           6
        //   344: iload           7
        //   346: if_icmpge       444
        //   349: goto            356
        //   352: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   355: athrow         
        //   356: aload           5
        //   358: ldc             "Expecting '{'"
        //   360: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isErrorWithTextAtEnd:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Z
        //   363: ifne            390
        //   366: goto            373
        //   369: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   372: athrow         
        //   373: aload           5
        //   375: ldc             "} expected"
        //   377: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isErrorWithTextAtEnd:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Z
        //   380: ifeq            397
        //   383: goto            390
        //   386: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   389: athrow         
        //   390: iload           4
        //   392: ireturn        
        //   393: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   396: athrow         
        //   397: aload           8
        //   399: iload           6
        //   401: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   406: bipush          32
        //   408: if_icmpne       487
        //   411: aload           8
        //   413: iload           6
        //   415: iconst_1       
        //   416: iadd           
        //   417: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   422: bipush          59
        //   424: if_icmpne       487
        //   427: goto            434
        //   430: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   433: athrow         
        //   434: iinc            6, 1
        //   437: goto            487
        //   440: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   443: athrow         
        //   444: iload           7
        //   446: aload           8
        //   448: invokeinterface java/lang/CharSequence.length:()I
        //   453: if_icmpge       487
        //   456: aload           8
        //   458: iload           7
        //   460: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   465: bipush          59
        //   467: if_icmpne       487
        //   470: goto            477
        //   473: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   476: athrow         
        //   477: iinc            7, 1
        //   480: goto            487
        //   483: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   486: athrow         
        //   487: aload_3        
        //   488: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   493: iload           6
        //   495: iflt            507
        //   498: iload           6
        //   500: goto            509
        //   503: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/AfterSemicolonEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   506: athrow         
        //   507: iload           7
        //   509: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //   514: iload           4
        //   516: ireturn        
        //   517: iconst_0       
        //   518: ireturn        
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
        //  251    280    283    287    Ljava/lang/IllegalArgumentException;
        //  293    302    302    306    Ljava/lang/IllegalArgumentException;
        //  337    349    352    356    Ljava/lang/IllegalArgumentException;
        //  342    366    369    373    Ljava/lang/IllegalArgumentException;
        //  356    383    386    390    Ljava/lang/IllegalArgumentException;
        //  373    393    393    397    Ljava/lang/IllegalArgumentException;
        //  397    427    430    434    Ljava/lang/IllegalArgumentException;
        //  411    440    440    444    Ljava/lang/IllegalArgumentException;
        //  444    470    473    477    Ljava/lang/IllegalArgumentException;
        //  456    480    483    487    Ljava/lang/IllegalArgumentException;
        //  487    503    503    507    Ljava/lang/IllegalArgumentException;
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
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
