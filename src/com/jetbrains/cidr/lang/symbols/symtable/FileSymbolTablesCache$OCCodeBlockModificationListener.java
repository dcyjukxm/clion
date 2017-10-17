// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.impl.PsiTreeChangeEventImpl;
import com.intellij.openapi.util.Key;
import com.intellij.psi.impl.PsiTreeChangePreprocessor;

private class OCCodeBlockModificationListener implements PsiTreeChangePreprocessor
{
    private final Key<String> FILE_PREPROCESSOR_STAMP;
    
    private OCCodeBlockModificationListener() {
        this.FILE_PREPROCESSOR_STAMP = (Key<String>)Key.create("FILE_PREPROCESSOR_STAMP");
    }
    
    @Override
    public void treeChanged(@NotNull final PsiTreeChangeEventImpl p0) {
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
        //    18: ldc             "event"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "treeChanged"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: iconst_0       
        //    45: istore_2       
        //    46: iconst_0       
        //    47: istore_3       
        //    48: iconst_0       
        //    49: istore          4
        //    51: aload_1        
        //    52: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getFile:()Lcom/intellij/psi/PsiFile;
        //    55: astore          5
        //    57: aload_1        
        //    58: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getChild:()Lcom/intellij/psi/PsiElement;
        //    61: astore          6
        //    63: aload           5
        //    65: ifnonnull       89
        //    68: aload           6
        //    70: ifnull          89
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    79: athrow         
        //    80: aload           6
        //    82: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    87: astore          5
        //    89: aload           5
        //    91: ifnonnull       119
        //    94: aload_1        
        //    95: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getParent:()Lcom/intellij/psi/PsiElement;
        //    98: ifnull          119
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: aload_1        
        //   109: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getParent:()Lcom/intellij/psi/PsiElement;
        //   112: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   117: astore          5
        //   119: aload_1        
        //   120: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getParent:()Lcom/intellij/psi/PsiElement;
        //   123: astore          7
        //   125: aload           5
        //   127: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/SymbolTableProvider.isSourceFile:(Lcom/intellij/psi/PsiFile;)Z
        //   130: ifne            138
        //   133: return         
        //   134: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: getstatic       com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$5.$SwitchMap$com$intellij$psi$impl$PsiTreeChangeEventImpl$PsiEventType:[I
        //   141: aload_1        
        //   142: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getCode:()Lcom/intellij/psi/impl/PsiTreeChangeEventImpl$PsiEventType;
        //   145: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl$PsiEventType.ordinal:()I
        //   148: iaload         
        //   149: tableswitch {
        //                2: 212
        //                3: 245
        //                4: 254
        //                5: 263
        //                6: 272
        //                7: 275
        //                8: 336
        //                9: 364
        //               10: 430
        //               11: 485
        //               12: 485
        //               13: 485
        //          default: 497
        //        }
        //   212: aload_1        
        //   213: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getParent:()Lcom/intellij/psi/PsiElement;
        //   216: instanceof      Lcom/intellij/psi/PsiFile;
        //   219: ifeq            236
        //   222: goto            229
        //   225: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   228: athrow         
        //   229: goto            525
        //   232: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   235: athrow         
        //   236: aload_0        
        //   237: aload           5
        //   239: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Lcom/intellij/psi/PsiFile;)V
        //   242: goto            525
        //   245: aload_0        
        //   246: aload           5
        //   248: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Lcom/intellij/psi/PsiFile;)V
        //   251: goto            525
        //   254: aload_0        
        //   255: aload           5
        //   257: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Lcom/intellij/psi/PsiFile;)V
        //   260: goto            525
        //   263: aload_0        
        //   264: aload           5
        //   266: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Lcom/intellij/psi/PsiFile;)V
        //   269: goto            525
        //   272: goto            525
        //   275: aload_1        
        //   276: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getOffset:()I
        //   279: istore_2       
        //   280: getstatic       com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.$assertionsDisabled:Z
        //   283: ifne            310
        //   286: aload           6
        //   288: ifnonnull       310
        //   291: goto            298
        //   294: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   297: athrow         
        //   298: new             Ljava/lang/AssertionError;
        //   301: dup            
        //   302: invokespecial   java/lang/AssertionError.<init>:()V
        //   305: athrow         
        //   306: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   309: athrow         
        //   310: iload_2        
        //   311: aload           6
        //   313: invokeinterface com/intellij/psi/PsiElement.getTextLength:()I
        //   318: iadd           
        //   319: istore_3       
        //   320: iload_3        
        //   321: iload_2        
        //   322: isub           
        //   323: istore          4
        //   325: aload_0        
        //   326: aload           5
        //   328: aload           7
        //   330: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Lcom/intellij/psi/PsiFile;Lcom/intellij/psi/PsiElement;)V
        //   333: goto            525
        //   336: aload_1        
        //   337: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getOffset:()I
        //   340: istore_2       
        //   341: aload_1        
        //   342: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getOffset:()I
        //   345: istore_3       
        //   346: aload_1        
        //   347: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getOldLength:()I
        //   350: ineg           
        //   351: istore          4
        //   353: aload_0        
        //   354: aload           5
        //   356: aload           7
        //   358: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Lcom/intellij/psi/PsiFile;Lcom/intellij/psi/PsiElement;)V
        //   361: goto            525
        //   364: aload_1        
        //   365: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getOffset:()I
        //   368: istore_2       
        //   369: getstatic       com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.$assertionsDisabled:Z
        //   372: ifne            399
        //   375: aload           6
        //   377: ifnonnull       399
        //   380: goto            387
        //   383: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   386: athrow         
        //   387: new             Ljava/lang/AssertionError;
        //   390: dup            
        //   391: invokespecial   java/lang/AssertionError.<init>:()V
        //   394: athrow         
        //   395: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   398: athrow         
        //   399: iload_2        
        //   400: aload           6
        //   402: invokeinterface com/intellij/psi/PsiElement.getTextLength:()I
        //   407: iadd           
        //   408: istore_3       
        //   409: iload_3        
        //   410: iload_2        
        //   411: isub           
        //   412: aload_1        
        //   413: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getOldLength:()I
        //   416: isub           
        //   417: istore          4
        //   419: aload_0        
        //   420: aload           5
        //   422: aload           7
        //   424: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Lcom/intellij/psi/PsiFile;Lcom/intellij/psi/PsiElement;)V
        //   427: goto            525
        //   430: aload_1        
        //   431: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.isGenericChange:()Z
        //   434: ifne            525
        //   437: aload           6
        //   439: ifnull          474
        //   442: goto            449
        //   445: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   448: athrow         
        //   449: aload_1        
        //   450: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getOffset:()I
        //   453: istore_2       
        //   454: iload_2        
        //   455: aload           6
        //   457: invokeinterface com/intellij/psi/PsiElement.getTextLength:()I
        //   462: iadd           
        //   463: istore_3       
        //   464: iload_3        
        //   465: iload_2        
        //   466: isub           
        //   467: aload_1        
        //   468: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getOldLength:()I
        //   471: isub           
        //   472: istore          4
        //   474: aload_0        
        //   475: aload           5
        //   477: aload           7
        //   479: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Lcom/intellij/psi/PsiFile;Lcom/intellij/psi/PsiElement;)V
        //   482: goto            525
        //   485: aload_0        
        //   486: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.this$0:Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;
        //   489: aload           5
        //   491: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.access$1000:(Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;Lcom/intellij/psi/PsiFile;)V
        //   494: goto            525
        //   497: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.access$1100:()Lcom/intellij/openapi/diagnostic/Logger;
        //   500: new             Ljava/lang/StringBuilder;
        //   503: dup            
        //   504: invokespecial   java/lang/StringBuilder.<init>:()V
        //   507: ldc             "Unknown code:"
        //   509: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   512: aload_1        
        //   513: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.getCode:()Lcom/intellij/psi/impl/PsiTreeChangeEventImpl$PsiEventType;
        //   516: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   519: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   522: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/String;)V
        //   525: iload           4
        //   527: ifeq            550
        //   530: aload_0        
        //   531: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.this$0:Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;
        //   534: aload           5
        //   536: iload_2        
        //   537: iload_3        
        //   538: iload           4
        //   540: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.access$1200:(Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;Lcom/intellij/psi/PsiFile;III)V
        //   543: goto            550
        //   546: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   549: athrow         
        //   550: aload_1        
        //   551: invokevirtual   com/intellij/psi/impl/PsiTreeChangeEventImpl.isGenericChange:()Z
        //   554: ifeq            628
        //   557: aload           5
        //   559: aload_0        
        //   560: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.FILE_PREPROCESSOR_STAMP:Lcom/intellij/openapi/util/Key;
        //   563: invokeinterface com/intellij/psi/PsiFile.getUserData:(Lcom/intellij/openapi/util/Key;)Ljava/lang/Object;
        //   568: checkcast       Ljava/lang/String;
        //   571: astore          8
        //   573: aload           8
        //   575: ifnull          628
        //   578: aload           8
        //   580: aload_0        
        //   581: aload           5
        //   583: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.b:(Lcom/intellij/psi/PsiFile;)Ljava/lang/String;
        //   586: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   589: ifne            616
        //   592: goto            599
        //   595: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   598: athrow         
        //   599: aload_0        
        //   600: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.this$0:Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;
        //   603: aload           5
        //   605: iconst_1       
        //   606: invokevirtual   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.handleFileChange:(Lcom/intellij/psi/PsiFile;Z)V
        //   609: goto            616
        //   612: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   615: athrow         
        //   616: aload           5
        //   618: aload_0        
        //   619: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.FILE_PREPROCESSOR_STAMP:Lcom/intellij/openapi/util/Key;
        //   622: aconst_null    
        //   623: invokeinterface com/intellij/psi/PsiFile.putUserData:(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V
        //   628: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  63     73     76     80     Ljava/lang/IllegalArgumentException;
        //  89     101    104    108    Ljava/lang/IllegalArgumentException;
        //  125    134    134    138    Ljava/lang/IllegalArgumentException;
        //  138    222    225    229    Ljava/lang/IllegalArgumentException;
        //  212    232    232    236    Ljava/lang/IllegalArgumentException;
        //  280    291    294    298    Ljava/lang/IllegalArgumentException;
        //  286    306    306    310    Ljava/lang/IllegalArgumentException;
        //  369    380    383    387    Ljava/lang/IllegalArgumentException;
        //  375    395    395    399    Ljava/lang/IllegalArgumentException;
        //  430    442    445    449    Ljava/lang/IllegalArgumentException;
        //  525    543    546    550    Ljava/lang/IllegalArgumentException;
        //  573    592    595    599    Ljava/lang/IllegalArgumentException;
        //  578    609    612    616    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0212:
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
    
    private void a(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener", "processBeforeChange"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0079: {
            try {
                ApplicationManager.getApplication().assertIsDispatchThread();
                if (psiFile instanceof OCCodeFragment) {
                    return;
                }
                final PsiFile psiFile2 = psiFile;
                final OCCodeBlockModificationListener ocCodeBlockModificationListener = this;
                final Key<String> key = ocCodeBlockModificationListener.FILE_PREPROCESSOR_STAMP;
                final Object o = psiFile2.getUserData((Key)key);
                if (o == null) {
                    break Label_0079;
                }
                return;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final PsiFile psiFile2 = psiFile;
                final OCCodeBlockModificationListener ocCodeBlockModificationListener = this;
                final Key<String> key = ocCodeBlockModificationListener.FILE_PREPROCESSOR_STAMP;
                final Object o = psiFile2.getUserData((Key)key);
                if (o == null) {
                    psiFile.putUserData((Key)this.FILE_PREPROCESSOR_STAMP, (Object)this.b(psiFile));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
    }
    
    private void a(final PsiFile psiFile, final PsiElement psiElement) {
        Label_0030: {
            try {
                ApplicationManager.getApplication().assertIsDispatchThread();
                if (psiFile instanceof OCCodeFragment) {
                    return;
                }
                final OCCodeBlockModificationListener ocCodeBlockModificationListener = this;
                final PsiElement psiElement2 = psiElement;
                final boolean b = ocCodeBlockModificationListener.a(psiElement2);
                if (!b) {
                    break Label_0030;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCCodeBlockModificationListener ocCodeBlockModificationListener = this;
                final PsiElement psiElement2 = psiElement;
                final boolean b = ocCodeBlockModificationListener.a(psiElement2);
                if (!b) {
                    FileSymbolTablesCache.this.handleFileChange(psiFile, false);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    @NotNull
    private String b(final PsiFile psiFile) {
        Label_0055: {
            String s = null;
            Label_0020: {
                try {
                    if (psiFile instanceof OCFile) {
                        break Label_0055;
                    }
                    s = "";
                    if (s == null) {
                        break Label_0020;
                    }
                    return s;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    s = "";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener", "macroStamp"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return s;
        }
        final StringBuilder sb = new StringBuilder();
        String string;
        try {
            this.a((ASTNode)psiFile.getNode(), sb);
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener", "macroStamp"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return string;
    }
    
    private void a(@Nullable final ASTNode astNode, @NotNull final StringBuilder sb) {
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "acc", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener", "processASTNodeForMacros"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (astNode == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final IElementType elementType = astNode.getElementType();
        try {
            if (OCElementTypes.IMPORTANT_DIRECTIVES.contains(elementType)) {
                sb.append(astNode.getText());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        for (ASTNode astNode2 = astNode.getFirstChildNode(); astNode2 != null; astNode2 = astNode2.getTreeNext()) {
            this.a(astNode2, sb);
        }
    }
    
    private boolean a(@Nullable final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/intellij/psi/PsiFileSystemItem;
        //     4: ifeq            13
        //     7: iconst_0       
        //     8: ireturn        
        //     9: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    12: athrow         
        //    13: aload_1        
        //    14: ifnull          33
        //    17: aload_1        
        //    18: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    23: ifnonnull       39
        //    26: goto            33
        //    29: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    32: athrow         
        //    33: iconst_1       
        //    34: ireturn        
        //    35: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: aload_1        
        //    40: astore_2       
        //    41: aload_2        
        //    42: instanceof      Lcom/intellij/psi/PsiFile;
        //    45: ifne            73
        //    48: aload_2        
        //    49: instanceof      Lcom/intellij/psi/PsiDirectory;
        //    52: ifne            73
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aload_2        
        //    63: ifnonnull       79
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: iconst_0       
        //    74: ireturn        
        //    75: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload_2        
        //    80: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLocalBlock;
        //    83: ifeq            106
        //    86: aload_2        
        //    87: instanceof      Lcom/jetbrains/cidr/lang/psi/impl/OCEagerBlockStatementImpl;
        //    90: ifne            106
        //    93: goto            100
        //    96: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    99: athrow         
        //   100: iconst_1       
        //   101: ireturn        
        //   102: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$OCCodeBlockModificationListener.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: aload_2        
        //   107: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   112: astore_2       
        //   113: goto            41
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      9      9      13     Ljava/lang/IllegalArgumentException;
        //  13     26     29     33     Ljava/lang/IllegalArgumentException;
        //  17     35     35     39     Ljava/lang/IllegalArgumentException;
        //  41     55     58     62     Ljava/lang/IllegalArgumentException;
        //  48     66     69     73     Ljava/lang/IllegalArgumentException;
        //  62     75     75     79     Ljava/lang/IllegalArgumentException;
        //  79     93     96     100    Ljava/lang/IllegalArgumentException;
        //  86     102    102    106    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0062:
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
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!FileSymbolTablesCache.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
