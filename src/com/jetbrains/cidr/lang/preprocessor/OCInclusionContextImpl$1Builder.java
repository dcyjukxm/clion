// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.psi.OCFile;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

class 1Builder implements Processor<OCSymbol>
{
    @NotNull
    private OCParsingNameScope myNameScope;
    @Nullable
    private final OCContextChangeBuilder myContextChangeBuilder;
    private boolean wasTruncated;
    final /* synthetic */ OCFile val$file;
    final /* synthetic */ VirtualFile val$vFile;
    final /* synthetic */ VirtualFile val$breakOn;
    final /* synthetic */ int val$inclusionLevel;
    final /* synthetic */ OCContextChangeSet val$changeSet;
    final /* synthetic */ int val$afterOffset;
    final /* synthetic */ int val$beforeOffset;
    
    public 1Builder(@Nullable final OCParsingNameScope myNameScope, final OCContextChangeBuilder myContextChangeBuilder, final OCFile val$file, final VirtualFile val$vFile, final VirtualFile val$breakOn, final int val$inclusionLevel, final OCContextChangeSet val$changeSet, final OCParsingNameScope val$afterOffset, final OCContextChangeBuilder val$beforeOffset) {
        this.val$file = val$file;
        this.val$vFile = val$vFile;
        this.val$breakOn = val$breakOn;
        this.val$inclusionLevel = val$inclusionLevel;
        this.val$changeSet = val$changeSet;
        this.val$afterOffset = (int)val$afterOffset;
        this.val$beforeOffset = (int)val$beforeOffset;
        this.myNameScope = myNameScope;
        this.myContextChangeBuilder = myContextChangeBuilder;
    }
    
    public boolean wasTruncated() {
        return this.wasTruncated;
    }
    
    @NotNull
    public OCParsingNameScope getNameScope() {
        OCParsingNameScope myNameScope;
        try {
            myNameScope = this.myNameScope;
            if (myNameScope == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder", "getNameScope"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myNameScope;
    }
    
    public boolean process(final OCSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.this$0:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;
        //     4: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.access$000:(Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;)Lcom/intellij/util/Processor;
        //     7: ifnull          31
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.this$0:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;
        //    14: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.access$000:(Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;)Lcom/intellij/util/Processor;
        //    17: aload_1        
        //    18: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //    23: pop            
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    30: athrow         
        //    31: aload_0        
        //    32: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.myContextChangeBuilder:Lcom/jetbrains/cidr/lang/preprocessor/OCContextChangeBuilder;
        //    35: astore_2       
        //    36: aload_1        
        //    37: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol;
        //    40: ifeq            262
        //    43: getstatic       com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.$assertionsDisabled:Z
        //    46: ifne            82
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    55: athrow         
        //    56: aload_0        
        //    57: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$file:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    60: ifnonnull       82
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    69: athrow         
        //    70: new             Ljava/lang/AssertionError;
        //    73: dup            
        //    74: invokespecial   java/lang/AssertionError.<init>:()V
        //    77: athrow         
        //    78: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    81: athrow         
        //    82: getstatic       com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.$assertionsDisabled:Z
        //    85: ifne            114
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$vFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //    92: ifnonnull       114
        //    95: goto            102
        //    98: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   101: athrow         
        //   102: new             Ljava/lang/AssertionError;
        //   105: dup            
        //   106: invokespecial   java/lang/AssertionError.<init>:()V
        //   109: athrow         
        //   110: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   113: athrow         
        //   114: aload_1        
        //   115: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol;
        //   118: astore_3       
        //   119: aload_3        
        //   120: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol.getTargetFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   123: astore          4
        //   125: aload           4
        //   127: ifnull          145
        //   130: aload           4
        //   132: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isValid:()Z
        //   135: ifne            153
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   144: athrow         
        //   145: aconst_null    
        //   146: goto            167
        //   149: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   152: athrow         
        //   153: aload_0        
        //   154: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$file:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   157: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getManager:()Lcom/intellij/psi/PsiManager;
        //   162: aload           4
        //   164: invokevirtual   com/intellij/psi/PsiManager.findFile:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/psi/PsiFile;
        //   167: astore          5
        //   169: aload           5
        //   171: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   174: ifeq            259
        //   177: aload           4
        //   179: aload_0        
        //   180: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$breakOn:Lcom/intellij/openapi/vfs/VirtualFile;
        //   183: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   186: ifne            237
        //   189: goto            196
        //   192: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   195: athrow         
        //   196: aload_0        
        //   197: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.this$0:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;
        //   200: aload           5
        //   202: aload_3        
        //   203: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol.isOnce:()Z
        //   206: aload_0        
        //   207: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$breakOn:Lcom/intellij/openapi/vfs/VirtualFile;
        //   210: aload_0        
        //   211: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$inclusionLevel:I
        //   214: iconst_1       
        //   215: iadd           
        //   216: aload_3        
        //   217: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol.getEndOffset:()I
        //   220: aload_0        
        //   221: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$changeSet:Lcom/jetbrains/cidr/lang/preprocessor/OCContextChangeSet;
        //   224: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.preprocessInclude:(Lcom/intellij/psi/PsiFile;ZLcom/intellij/openapi/vfs/VirtualFile;IILcom/jetbrains/cidr/lang/preprocessor/OCContextChangeSet;)Z
        //   227: ifne            243
        //   230: goto            237
        //   233: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   236: athrow         
        //   237: iconst_0       
        //   238: ireturn        
        //   239: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   242: athrow         
        //   243: aload           5
        //   245: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   250: aload           4
        //   252: aload_0        
        //   253: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$vFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   256: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCImportGraph.addHeaderIncluder:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/vfs/VirtualFile;)V
        //   259: goto            1338
        //   262: aload_1        
        //   263: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   266: ifeq            287
        //   269: aload_0        
        //   270: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.this$0:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;
        //   273: aload_1        
        //   274: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   277: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.define:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;)V
        //   280: goto            1338
        //   283: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   286: athrow         
        //   287: aload_1        
        //   288: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCUndefMacroSymbol;
        //   291: ifeq            314
        //   294: aload_0        
        //   295: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.this$0:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;
        //   298: aload_1        
        //   299: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   304: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.undef:(Ljava/lang/String;)V
        //   307: goto            1338
        //   310: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   313: athrow         
        //   314: aload_1        
        //   315: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   320: astore_3       
        //   321: aload_0        
        //   322: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   325: astore          4
        //   327: aload_1        
        //   328: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   331: ifeq            749
        //   334: aload_1        
        //   335: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   338: astore          5
        //   340: aload           5
        //   342: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.getMembersList:()Ljava/util/List;
        //   345: astore          6
        //   347: aload_2        
        //   348: ifnull          360
        //   351: aload_2        
        //   352: aload_1        
        //   353: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCContextChangeBuilder.addSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   358: aconst_null    
        //   359: astore_2       
        //   360: aload_0        
        //   361: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$afterOffset:I
        //   364: iconst_m1      
        //   365: if_icmpeq       397
        //   368: aload_0        
        //   369: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$afterOffset:I
        //   372: aload_1        
        //   373: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getOffset:()I
        //   378: if_icmple       397
        //   381: goto            388
        //   384: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   387: athrow         
        //   388: aload_0        
        //   389: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   392: astore          7
        //   394: goto            516
        //   397: aload_0        
        //   398: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   401: aload_1        
        //   402: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   407: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineNamespace:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   410: astore          7
        //   412: aload_1        
        //   413: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   416: ifeq            516
        //   419: aload_1        
        //   420: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   423: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getTemplateParameters:()Ljava/util/List;
        //   426: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   431: astore          8
        //   433: aload           8
        //   435: invokeinterface java/util/Iterator.hasNext:()Z
        //   440: ifeq            516
        //   443: aload           8
        //   445: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   450: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   453: astore          9
        //   455: aload           9
        //   457: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
        //   460: ifeq            484
        //   463: aload           7
        //   465: aload           9
        //   467: invokeinterface com/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol.getName:()Ljava/lang/String;
        //   472: iconst_1       
        //   473: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineType:(Ljava/lang/String;Z)Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //   476: pop            
        //   477: goto            513
        //   480: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   483: athrow         
        //   484: aload           9
        //   486: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol;
        //   489: ifeq            513
        //   492: aload           7
        //   494: aload           9
        //   496: invokeinterface com/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol.getName:()Ljava/lang/String;
        //   501: iconst_0       
        //   502: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineValue:(Ljava/lang/String;Z)Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //   505: pop            
        //   506: goto            513
        //   509: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   512: athrow         
        //   513: goto            433
        //   516: new             Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder;
        //   519: dup            
        //   520: aload_0        
        //   521: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.this$0:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;
        //   524: aload           7
        //   526: aconst_null    
        //   527: aload_0        
        //   528: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$file:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   531: aload_0        
        //   532: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$vFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   535: aload_0        
        //   536: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$breakOn:Lcom/intellij/openapi/vfs/VirtualFile;
        //   539: aload_0        
        //   540: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$inclusionLevel:I
        //   543: aload_0        
        //   544: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$changeSet:Lcom/jetbrains/cidr/lang/preprocessor/OCContextChangeSet;
        //   547: aload_0        
        //   548: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$afterOffset:I
        //   551: aload_0        
        //   552: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$beforeOffset:I
        //   555: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.<init>:(Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;Lcom/jetbrains/cidr/lang/preprocessor/OCContextChangeBuilder;Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/vfs/VirtualFile;ILcom/jetbrains/cidr/lang/preprocessor/OCContextChangeSet;II)V
        //   558: astore          8
        //   560: aload           5
        //   562: aload           8
        //   564: invokedynamic   process:(Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder;)Lcom/intellij/util/Processor;
        //   569: aload_0        
        //   570: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$afterOffset:I
        //   573: aload_0        
        //   574: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$beforeOffset:I
        //   577: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.processMembersAndUsings:(Lcom/intellij/util/Processor;II)Z
        //   580: pop            
        //   581: aload           6
        //   583: ifnull          601
        //   586: aload           6
        //   588: invokestatic    com/intellij/util/containers/ContainerUtil.getLastItem:(Ljava/util/List;)Ljava/lang/Object;
        //   591: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   594: goto            602
        //   597: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   600: athrow         
        //   601: aconst_null    
        //   602: astore          9
        //   604: aload_0        
        //   605: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$beforeOffset:I
        //   608: iconst_m1      
        //   609: if_icmpeq       645
        //   612: aload           9
        //   614: ifnull          645
        //   617: goto            624
        //   620: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   623: athrow         
        //   624: aload_0        
        //   625: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.val$beforeOffset:I
        //   628: aload           9
        //   630: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getOffset:()I
        //   635: if_icmple       660
        //   638: goto            645
        //   641: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   644: athrow         
        //   645: aload           8
        //   647: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.wasTruncated:()Z
        //   650: ifeq            675
        //   653: goto            660
        //   656: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   659: athrow         
        //   660: aload_0        
        //   661: iconst_1       
        //   662: putfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.wasTruncated:Z
        //   665: aload           8
        //   667: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.getNameScope:()Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   670: astore          4
        //   672: goto            749
        //   675: aload           8
        //   677: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.getNameScope:()Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   680: astore          10
        //   682: aload           10
        //   684: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.getParent:()Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   687: astore          4
        //   689: getstatic       com/jetbrains/cidr/lang/OCLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   692: aload           4
        //   694: ifnull          705
        //   697: iconst_1       
        //   698: goto            706
        //   701: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   704: athrow         
        //   705: iconst_0       
        //   706: new             Ljava/lang/StringBuilder;
        //   709: dup            
        //   710: invokespecial   java/lang/StringBuilder.<init>:()V
        //   713: ldc             "no parent scope found for '"
        //   715: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   718: aload_1        
        //   719: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   724: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   727: ldc             "' (current scope is '"
        //   729: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   732: aload           10
        //   734: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   737: ldc             "')"
        //   739: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   742: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   745: invokevirtual   com/intellij/openapi/diagnostic/Logger.assertTrue:(ZLjava/lang/Object;)Z
        //   748: pop            
        //   749: aload_1        
        //   750: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
        //   753: ifeq            843
        //   756: aload_2        
        //   757: ifnull          781
        //   760: goto            767
        //   763: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   766: athrow         
        //   767: aload_2        
        //   768: aload_1        
        //   769: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCContextChangeBuilder.addSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   774: goto            781
        //   777: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   780: athrow         
        //   781: aload_1        
        //   782: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   787: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE_USING_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   790: if_acmpne       820
        //   793: aload_0        
        //   794: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   797: aload_1        
        //   798: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
        //   801: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol.getSymbolReference:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   804: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   807: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.flatten:()Ljava/util/List;
        //   810: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineNamespaceUsing:(Ljava/util/List;)V
        //   813: goto            1332
        //   816: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   819: athrow         
        //   820: aload_0        
        //   821: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   824: aload_1        
        //   825: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
        //   828: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol.getSymbolReference:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   831: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   834: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.flatten:()Ljava/util/List;
        //   837: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineSymbolUsing:(Ljava/util/List;)V
        //   840: goto            1332
        //   843: aload_3        
        //   844: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isType:()Z
        //   847: ifeq            1135
        //   850: aload_2        
        //   851: ifnull          875
        //   854: goto            861
        //   857: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   860: athrow         
        //   861: aload_2        
        //   862: aload_1        
        //   863: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCContextChangeBuilder.addSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   868: goto            875
        //   871: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   874: athrow         
        //   875: aload_3        
        //   876: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isClass:()Z
        //   879: ifeq            966
        //   882: aload_3        
        //   883: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROTOCOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   886: if_acmpne       916
        //   889: goto            896
        //   892: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   895: athrow         
        //   896: aload_0        
        //   897: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   900: aload_1        
        //   901: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   906: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineProtocol:(Ljava/lang/String;)V
        //   909: goto            930
        //   912: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   915: athrow         
        //   916: aload_0        
        //   917: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   920: aload_1        
        //   921: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   926: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineInterface:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //   929: pop            
        //   930: aload_1        
        //   931: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   934: ifeq            1093
        //   937: aload_1        
        //   938: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   941: aconst_null    
        //   942: checkcast       Ljava/lang/String;
        //   945: new             Lcom/jetbrains/cidr/lang/util/OCCommonProcessors$SelfAdapterProcessor;
        //   948: dup            
        //   949: aload_0        
        //   950: invokespecial   com/jetbrains/cidr/lang/util/OCCommonProcessors$SelfAdapterProcessor.<init>:(Lcom/intellij/util/Processor;)V
        //   953: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.processMembers:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
        //   958: pop            
        //   959: goto            1093
        //   962: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   965: athrow         
        //   966: aload_3        
        //   967: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isClassOrTypedef:()Z
        //   970: ifne            995
        //   973: aload_0        
        //   974: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.this$0:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;
        //   977: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl.access$100:(Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;)Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   980: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isCpp:()Z
        //   985: ifeq            1093
        //   988: goto            995
        //   991: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   994: athrow         
        //   995: aload_1        
        //   996: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   999: ifeq            1036
        //  1002: goto            1009
        //  1005: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1008: athrow         
        //  1009: aload_1        
        //  1010: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //  1013: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.isTemplateSymbol:()Z
        //  1018: ifeq            1036
        //  1021: goto            1028
        //  1024: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1027: athrow         
        //  1028: iconst_1       
        //  1029: goto            1037
        //  1032: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1035: athrow         
        //  1036: iconst_0       
        //  1037: istore          5
        //  1039: aload_1        
        //  1040: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  1043: ifeq            1071
        //  1046: aload_1        
        //  1047: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  1050: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isFriend:()Z
        //  1053: ifeq            1071
        //  1056: goto            1063
        //  1059: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1062: athrow         
        //  1063: iconst_1       
        //  1064: goto            1072
        //  1067: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1070: athrow         
        //  1071: iconst_0       
        //  1072: istore          6
        //  1074: aload_0        
        //  1075: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //  1078: aload_1        
        //  1079: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //  1084: iload           5
        //  1086: iload           6
        //  1088: iconst_m1      
        //  1089: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineType:(Ljava/lang/String;ZZI)Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //  1092: pop            
        //  1093: aload_3        
        //  1094: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1097: if_acmpne       1332
        //  1100: aload_1        
        //  1101: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  1104: ifeq            1332
        //  1107: goto            1114
        //  1110: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1113: athrow         
        //  1114: aload_1        
        //  1115: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  1118: aload_0        
        //  1119: invokedynamic   process:(Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder;)Lcom/intellij/util/Processor;
        //  1124: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processFields:(Lcom/intellij/util/Processor;)Z
        //  1127: pop            
        //  1128: goto            1332
        //  1131: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1134: athrow         
        //  1135: aload_1        
        //  1136: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //  1139: ifeq            1164
        //  1142: aload_0        
        //  1143: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //  1146: aload_1        
        //  1147: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //  1152: iconst_0       
        //  1153: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineValue:(Ljava/lang/String;Z)Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //  1156: pop            
        //  1157: goto            1332
        //  1160: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1163: athrow         
        //  1164: aload_1        
        //  1165: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  1168: ifeq            1259
        //  1171: aload_1        
        //  1172: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1177: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isConstructorOrDestructor:()Z
        //  1180: ifne            1259
        //  1183: goto            1190
        //  1186: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1189: athrow         
        //  1190: aload_1        
        //  1191: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  1194: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //  1197: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getQualifier:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //  1200: ifnonnull       1259
        //  1203: goto            1210
        //  1206: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1209: athrow         
        //  1210: aload_2        
        //  1211: ifnull          1235
        //  1214: goto            1221
        //  1217: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1220: athrow         
        //  1221: aload_2        
        //  1222: aload_1        
        //  1223: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCContextChangeBuilder.addSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1228: goto            1235
        //  1231: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1234: athrow         
        //  1235: aload_0        
        //  1236: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //  1239: aload_1        
        //  1240: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //  1245: aload_1        
        //  1246: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  1249: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isTemplateSymbol:()Z
        //  1252: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineValue:(Ljava/lang/String;Z)Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //  1255: pop            
        //  1256: goto            1332
        //  1259: aload_1        
        //  1260: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //  1263: ifeq            1332
        //  1266: aload_1        
        //  1267: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //  1270: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //  1273: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getQualifier:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //  1276: ifnonnull       1332
        //  1279: goto            1286
        //  1282: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1285: athrow         
        //  1286: aload_2        
        //  1287: ifnull          1311
        //  1290: goto            1297
        //  1293: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1296: athrow         
        //  1297: aload_2        
        //  1298: aload_1        
        //  1299: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCContextChangeBuilder.addSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1304: goto            1311
        //  1307: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1310: athrow         
        //  1311: aload_0        
        //  1312: getfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //  1315: aload_1        
        //  1316: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //  1321: aload_1        
        //  1322: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //  1325: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isTemplateSymbol:()Z
        //  1328: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineValue:(Ljava/lang/String;Z)Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //  1331: pop            
        //  1332: aload_0        
        //  1333: aload           4
        //  1335: putfield        com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //  1338: iconst_1       
        //  1339: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      24     27     31     Ljava/lang/IllegalStateException;
        //  36     49     52     56     Ljava/lang/IllegalStateException;
        //  43     63     66     70     Ljava/lang/IllegalStateException;
        //  56     78     78     82     Ljava/lang/IllegalStateException;
        //  82     95     98     102    Ljava/lang/IllegalStateException;
        //  88     110    110    114    Ljava/lang/IllegalStateException;
        //  125    138    141    145    Ljava/lang/IllegalStateException;
        //  130    149    149    153    Ljava/lang/IllegalStateException;
        //  169    189    192    196    Ljava/lang/IllegalStateException;
        //  177    230    233    237    Ljava/lang/IllegalStateException;
        //  196    239    239    243    Ljava/lang/IllegalStateException;
        //  262    283    283    287    Ljava/lang/IllegalStateException;
        //  287    310    310    314    Ljava/lang/IllegalStateException;
        //  360    381    384    388    Ljava/lang/IllegalStateException;
        //  455    480    480    484    Ljava/lang/IllegalStateException;
        //  484    506    509    513    Ljava/lang/IllegalStateException;
        //  560    597    597    601    Ljava/lang/IllegalStateException;
        //  604    617    620    624    Ljava/lang/IllegalStateException;
        //  612    638    641    645    Ljava/lang/IllegalStateException;
        //  624    653    656    660    Ljava/lang/IllegalStateException;
        //  689    701    701    705    Ljava/lang/IllegalStateException;
        //  749    760    763    767    Ljava/lang/IllegalStateException;
        //  756    774    777    781    Ljava/lang/IllegalStateException;
        //  781    816    816    820    Ljava/lang/IllegalStateException;
        //  843    854    857    861    Ljava/lang/IllegalStateException;
        //  850    868    871    875    Ljava/lang/IllegalStateException;
        //  875    889    892    896    Ljava/lang/IllegalStateException;
        //  882    912    912    916    Ljava/lang/IllegalStateException;
        //  930    962    962    966    Ljava/lang/IllegalStateException;
        //  966    988    991    995    Ljava/lang/IllegalStateException;
        //  973    1002   1005   1009   Ljava/lang/IllegalStateException;
        //  995    1021   1024   1028   Ljava/lang/IllegalStateException;
        //  1009   1032   1032   1036   Ljava/lang/IllegalStateException;
        //  1039   1056   1059   1063   Ljava/lang/IllegalStateException;
        //  1046   1067   1067   1071   Ljava/lang/IllegalStateException;
        //  1093   1107   1110   1114   Ljava/lang/IllegalStateException;
        //  1100   1131   1131   1135   Ljava/lang/IllegalStateException;
        //  1135   1160   1160   1164   Ljava/lang/IllegalStateException;
        //  1164   1183   1186   1190   Ljava/lang/IllegalStateException;
        //  1171   1203   1206   1210   Ljava/lang/IllegalStateException;
        //  1190   1214   1217   1221   Ljava/lang/IllegalStateException;
        //  1210   1228   1231   1235   Ljava/lang/IllegalStateException;
        //  1259   1279   1282   1286   Ljava/lang/IllegalStateException;
        //  1266   1290   1293   1297   Ljava/lang/IllegalStateException;
        //  1286   1304   1307   1311   Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0056:
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
                if (!OCInclusionContextImpl.class.desiredAssertionStatus()) {
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
