// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.autoImport;

import java.util.List;
import java.util.Collections;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.util.Processor;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.workspace.OCResolveRootAndConfiguration;

public class OCDefaultAutoImportHelper implements OCAutoImportHelper
{
    @Override
    public boolean supports(@NotNull final OCResolveRootAndConfiguration ocResolveRootAndConfiguration) {
        try {
            if (ocResolveRootAndConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rootAndConfiguration", "com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper", "supports"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return true;
    }
    
    @Override
    public boolean processPathSpecificationToInclude(@NotNull final Project p0, @Nullable final VirtualFile p1, @NotNull final VirtualFile p2, @NotNull final OCResolveRootAndConfiguration p3, @NotNull final Processor<ImportSpecification> p4) {
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
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processPathSpecificationToInclude"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_3        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "fileToImport"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processPathSpecificationToInclude"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload           4
        //    90: ifnonnull       133
        //    93: new             Ljava/lang/IllegalArgumentException;
        //    96: dup            
        //    97: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    99: ldc             3
        //   101: anewarray       Ljava/lang/Object;
        //   104: dup            
        //   105: ldc             0
        //   107: ldc             "rootAndConfiguration"
        //   109: aastore        
        //   110: dup            
        //   111: ldc             1
        //   113: ldc             "com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "processPathSpecificationToInclude"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload           5
        //   135: ifnonnull       178
        //   138: new             Ljava/lang/IllegalArgumentException;
        //   141: dup            
        //   142: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   144: ldc             3
        //   146: anewarray       Ljava/lang/Object;
        //   149: dup            
        //   150: ldc             0
        //   152: ldc             "processor"
        //   154: aastore        
        //   155: dup            
        //   156: ldc             1
        //   158: ldc             "com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper"
        //   160: aastore        
        //   161: dup            
        //   162: ldc             2
        //   164: ldc             "processPathSpecificationToInclude"
        //   166: aastore        
        //   167: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   170: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   173: athrow         
        //   174: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: aload_3        
        //   179: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getName:()Ljava/lang/String;
        //   182: astore          6
        //   184: aload_3        
        //   185: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getPath:()Ljava/lang/String;
        //   188: astore          7
        //   190: aload_2        
        //   191: ifnull          205
        //   194: aload_2        
        //   195: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getParent:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   198: goto            206
        //   201: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   204: athrow         
        //   205: aconst_null    
        //   206: astore          8
        //   208: aload           8
        //   210: ifnull          267
        //   213: aload           8
        //   215: aload_3        
        //   216: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getParent:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   219: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   222: ifeq            267
        //   225: goto            232
        //   228: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   231: athrow         
        //   232: aload           5
        //   234: new             Lcom/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification;
        //   237: dup            
        //   238: aload           6
        //   240: getstatic       com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification$Kind.PROJECT_HEADER:Lcom/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification$Kind;
        //   243: invokespecial   com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification.<init>:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification$Kind;)V
        //   246: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   251: ifne            267
        //   254: goto            261
        //   257: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   260: athrow         
        //   261: iconst_0       
        //   262: ireturn        
        //   263: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   266: athrow         
        //   267: iconst_0       
        //   268: istore          9
        //   270: aload           4
        //   272: invokevirtual   com/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration.getLibraryHeadersRoots:()Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeaderRoots;
        //   275: invokevirtual   com/jetbrains/cidr/lang/workspace/headerRoots/HeaderRoots.getRoots:()Ljava/util/List;
        //   278: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   283: astore          10
        //   285: aload           10
        //   287: invokeinterface java/util/Iterator.hasNext:()Z
        //   292: ifeq            675
        //   295: aload           10
        //   297: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   302: checkcast       Lcom/intellij/psi/PsiFileSystemItem;
        //   305: astore          11
        //   307: aload           11
        //   309: invokeinterface com/intellij/psi/PsiFileSystemItem.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   314: astore          12
        //   316: getstatic       com/jetbrains/cidr/lang/OCLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   319: aload           12
        //   321: ifnull          332
        //   324: iconst_1       
        //   325: goto            333
        //   328: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   331: athrow         
        //   332: iconst_0       
        //   333: new             Ljava/lang/StringBuilder;
        //   336: dup            
        //   337: invokespecial   java/lang/StringBuilder.<init>:()V
        //   340: aload           11
        //   342: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   345: ldc             ": physical="
        //   347: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   350: aload           11
        //   352: invokeinterface com/intellij/psi/PsiFileSystemItem.isPhysical:()Z
        //   357: invokevirtual   java/lang/StringBuilder.append:(Z)Ljava/lang/StringBuilder;
        //   360: ldc             ", valid="
        //   362: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   365: aload           11
        //   367: invokeinterface com/intellij/psi/PsiFileSystemItem.isValid:()Z
        //   372: invokevirtual   java/lang/StringBuilder.append:(Z)Ljava/lang/StringBuilder;
        //   375: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   378: invokevirtual   com/intellij/openapi/diagnostic/Logger.assertTrue:(ZLjava/lang/Object;)Z
        //   381: pop            
        //   382: aload           12
        //   384: aload_3        
        //   385: iconst_1       
        //   386: invokestatic    com/intellij/openapi/vfs/VfsUtilCore.isAncestor:(Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/vfs/VirtualFile;Z)Z
        //   389: ifne            399
        //   392: goto            285
        //   395: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   398: athrow         
        //   399: iconst_1       
        //   400: istore          9
        //   402: aload           11
        //   404: instanceof      Lcom/jetbrains/cidr/lang/workspace/headerRoots/IncludedHeadersRoot;
        //   407: ifeq            544
        //   410: aload           11
        //   412: checkcast       Lcom/jetbrains/cidr/lang/workspace/headerRoots/IncludedHeadersRoot;
        //   415: astore          13
        //   417: aload           13
        //   419: invokevirtual   com/jetbrains/cidr/lang/workspace/headerRoots/IncludedHeadersRoot.isRecursive:()Z
        //   422: ifeq            432
        //   425: aload           6
        //   427: astore          7
        //   429: goto            491
        //   432: aload_3        
        //   433: aload           12
        //   435: invokestatic    com/intellij/openapi/vfs/VfsUtilCore.getRelativePath:(Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/vfs/VirtualFile;)Ljava/lang/String;
        //   438: astore          14
        //   440: aload           14
        //   442: ifnonnull       487
        //   445: getstatic       com/jetbrains/cidr/lang/OCLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   448: new             Ljava/lang/StringBuilder;
        //   451: dup            
        //   452: invokespecial   java/lang/StringBuilder.<init>:()V
        //   455: ldc             "Cannot calculate relative path: \n\tbase: "
        //   457: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   460: aload           12
        //   462: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   465: ldc             "\n\tchild: "
        //   467: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   470: aload_3        
        //   471: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   474: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   477: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/String;)V
        //   480: goto            491
        //   483: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   486: athrow         
        //   487: aload           14
        //   489: astore          7
        //   491: new             Lcom/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification;
        //   494: dup            
        //   495: aload           7
        //   497: aload           13
        //   499: invokevirtual   com/jetbrains/cidr/lang/workspace/headerRoots/IncludedHeadersRoot.isUserHeaders:()Z
        //   502: ifeq            515
        //   505: getstatic       com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification$Kind.USER_HEADER_SEARCH_PATH:Lcom/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification$Kind;
        //   508: goto            518
        //   511: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   514: athrow         
        //   515: getstatic       com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification$Kind.SYSTEM_HEADER_SEARCH_PATH:Lcom/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification$Kind;
        //   518: invokespecial   com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification.<init>:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification$Kind;)V
        //   521: astore          14
        //   523: aload           5
        //   525: aload           14
        //   527: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   532: ifne            541
        //   535: iconst_0       
        //   536: ireturn        
        //   537: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   540: athrow         
        //   541: goto            672
        //   544: aload           11
        //   546: instanceof      Lcom/jetbrains/cidr/lang/workspace/headerRoots/FrameworksSearchRoot;
        //   549: ifeq            672
        //   552: aload           11
        //   554: checkcast       Lcom/jetbrains/cidr/lang/workspace/headerRoots/FrameworksSearchRoot;
        //   557: astore          13
        //   559: new             Lcom/intellij/openapi/util/Ref;
        //   562: dup            
        //   563: invokespecial   com/intellij/openapi/util/Ref.<init>:()V
        //   566: astore          14
        //   568: aload           13
        //   570: new             Lcom/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper$1;
        //   573: dup            
        //   574: aload_0        
        //   575: aload_3        
        //   576: aload           14
        //   578: invokespecial   com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper$1.<init>:(Lcom/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/util/Ref;)V
        //   581: invokevirtual   com/jetbrains/cidr/lang/workspace/headerRoots/FrameworksSearchRoot.processChildren:(Lcom/intellij/psi/search/PsiElementProcessor;)Z
        //   584: pop            
        //   585: aload           14
        //   587: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   590: checkcast       Lcom/jetbrains/cidr/lang/workspace/headerRoots/AppleFramework;
        //   593: astore          15
        //   595: aload           15
        //   597: ifnull          672
        //   600: aload           15
        //   602: invokevirtual   com/jetbrains/cidr/lang/workspace/headerRoots/AppleFramework.getMainFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   605: astore          16
        //   607: aload           16
        //   609: ifnonnull       615
        //   612: aload_3        
        //   613: astore          16
        //   615: aload           5
        //   617: new             Lcom/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification;
        //   620: dup            
        //   621: new             Ljava/lang/StringBuilder;
        //   624: dup            
        //   625: invokespecial   java/lang/StringBuilder.<init>:()V
        //   628: aload           15
        //   630: invokevirtual   com/jetbrains/cidr/lang/workspace/headerRoots/AppleFramework.getName:()Ljava/lang/String;
        //   633: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   636: ldc             "/"
        //   638: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   641: aload           16
        //   643: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getName:()Ljava/lang/String;
        //   646: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   649: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   652: getstatic       com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification$Kind.SYSTEM_HEADER_SEARCH_PATH:Lcom/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification$Kind;
        //   655: invokespecial   com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification.<init>:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification$Kind;)V
        //   658: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   663: ifne            672
        //   666: iconst_0       
        //   667: ireturn        
        //   668: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   671: athrow         
        //   672: goto            285
        //   675: iload           9
        //   677: ifne            746
        //   680: aload           8
        //   682: ifnull          746
        //   685: goto            692
        //   688: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   691: athrow         
        //   692: aload           8
        //   694: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getPath:()Ljava/lang/String;
        //   697: aload           7
        //   699: bipush          47
        //   701: invokestatic    com/intellij/openapi/util/io/FileUtil.getRelativePath:(Ljava/lang/String;Ljava/lang/String;C)Ljava/lang/String;
        //   704: astore          10
        //   706: aload           10
        //   708: ifnull          746
        //   711: aload           5
        //   713: new             Lcom/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification;
        //   716: dup            
        //   717: aload           10
        //   719: getstatic       com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification$Kind.USER_HEADER_SEARCH_PATH:Lcom/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification$Kind;
        //   722: invokespecial   com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification.<init>:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification$Kind;)V
        //   725: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   730: ifne            746
        //   733: goto            740
        //   736: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   739: athrow         
        //   740: iconst_0       
        //   741: ireturn        
        //   742: invokestatic    com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   745: athrow         
        //   746: iconst_1       
        //   747: ireturn        
        //    Signature:
        //  (Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration;Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     129    129    133    Ljava/lang/IllegalArgumentException;
        //  133    174    174    178    Ljava/lang/IllegalArgumentException;
        //  190    201    201    205    Ljava/lang/IllegalArgumentException;
        //  208    225    228    232    Ljava/lang/IllegalArgumentException;
        //  213    254    257    261    Ljava/lang/IllegalArgumentException;
        //  232    263    263    267    Ljava/lang/IllegalArgumentException;
        //  316    328    328    332    Ljava/lang/IllegalArgumentException;
        //  333    395    395    399    Ljava/lang/IllegalArgumentException;
        //  440    483    483    487    Ljava/lang/IllegalArgumentException;
        //  491    511    511    515    Ljava/lang/IllegalArgumentException;
        //  523    537    537    541    Ljava/lang/IllegalArgumentException;
        //  615    668    668    672    Ljava/lang/IllegalArgumentException;
        //  675    685    688    692    Ljava/lang/IllegalArgumentException;
        //  706    733    736    740    Ljava/lang/IllegalArgumentException;
        //  711    742    742    746    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0232:
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
    public void addHeaderSearchPath(@NotNull final Project project, @Nullable final VirtualFile virtualFile, @NotNull final VirtualFile virtualFile2) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper", "addHeaderSearchPath"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileToImport", "com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper", "addHeaderSearchPath"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    @NotNull
    @Override
    public Iterable<IntentionAction> getAddHeaderSearchPathFixes(@NotNull final Project project, @NotNull final VirtualFile virtualFile, @NotNull final String s) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper", "getAddHeaderSearchPathFixes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetFile", "com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper", "getAddHeaderSearchPathFixes"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileNameToImport", "com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper", "getAddHeaderSearchPathFixes"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        List<IntentionAction> emptyList;
        try {
            emptyList = Collections.emptyList();
            if (emptyList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper", "getAddHeaderSearchPathFixes"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return emptyList;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
