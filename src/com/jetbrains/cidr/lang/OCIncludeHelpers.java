// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import com.intellij.psi.search.PsiElementProcessor;
import gnu.trove.THashMap;
import com.intellij.openapi.util.io.FileUtil;
import java.util.Map;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceModificationTrackers;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.openapi.util.UserDataHolder;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeaderRoots;
import java.util.HashMap;
import com.intellij.psi.util.CachedValue;
import com.intellij.openapi.util.NotNullLazyKey;
import java.util.Collections;
import com.jetbrains.cidr.lang.autoImport.OCAutoImportHelper;
import com.intellij.util.Processor;
import java.util.Iterator;
import com.intellij.openapi.util.Comparing;
import com.intellij.psi.PsiFile;
import java.util.List;
import java.util.Collection;
import com.intellij.openapi.util.Condition;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.FilteringIterator;
import com.jetbrains.cidr.lang.workspace.headerRoots.AppleFramework;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.workspace.OCResolveRootAndConfiguration;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;

public class OCIncludeHelpers
{
    public static OCIncludeHelper[] getIncludeHelpers() {
        return (OCIncludeHelper[])Extensions.getExtensions((ExtensionPointName)OCIncludeHelper.EP_NAME);
    }
    
    public static ShowInCompletion showInCompletion(@Nullable final PsiFileSystemItem psiFileSystemItem) {
        try {
            if (psiFileSystemItem == null) {
                return ShowInCompletion.DEFAULT;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ShowInCompletion default1 = ShowInCompletion.DEFAULT;
        final OCIncludeHelper[] includeHelpers = getIncludeHelpers();
        for (int length = includeHelpers.length, i = 0; i < length; ++i) {
            final ShowInCompletion showInCompletion = includeHelpers[i].showInCompletion(psiFileSystemItem);
            try {
                if (showInCompletion == ShowInCompletion.DEFAULT) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (showInCompletion == ShowInCompletion.SHOW) {
                    return ShowInCompletion.SHOW;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            default1 = showInCompletion;
        }
        return default1;
    }
    
    @Nullable
    public static VirtualFile resolveIncludedFile(@Nullable final OCResolveRootAndConfiguration ocResolveRootAndConfiguration, @Nullable final VirtualFile virtualFile, @NotNull final OCIncludeSymbol.IncludePath includePath, @NotNull final Project project) {
        try {
            if (includePath == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "include", "com/jetbrains/cidr/lang/OCIncludeHelpers", "resolveIncludedFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/OCIncludeHelpers", "resolveIncludedFile"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Ref ref = new Ref();
        resolveNextIncludedFile(ocResolveRootAndConfiguration, virtualFile, null, includePath, project, (Ref<VirtualFile>)ref);
        return (VirtualFile)ref.get();
    }
    
    public static void resolveNextIncludedFile(@Nullable final OCResolveRootAndConfiguration p0, @Nullable final VirtualFile p1, @Nullable final VirtualFile p2, @NotNull final OCIncludeSymbol.IncludePath p3, @NotNull final Project p4, @NotNull final Ref<VirtualFile> p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "include"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/OCIncludeHelpers"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "resolveNextIncludedFile"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload           4
        //    46: ifnonnull       89
        //    49: new             Ljava/lang/IllegalArgumentException;
        //    52: dup            
        //    53: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    55: ldc             3
        //    57: anewarray       Ljava/lang/Object;
        //    60: dup            
        //    61: ldc             0
        //    63: ldc             "project"
        //    65: aastore        
        //    66: dup            
        //    67: ldc             1
        //    69: ldc             "com/jetbrains/cidr/lang/OCIncludeHelpers"
        //    71: aastore        
        //    72: dup            
        //    73: ldc             2
        //    75: ldc             "resolveNextIncludedFile"
        //    77: aastore        
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: aload           5
        //    91: ifnonnull       134
        //    94: new             Ljava/lang/IllegalArgumentException;
        //    97: dup            
        //    98: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   100: ldc             3
        //   102: anewarray       Ljava/lang/Object;
        //   105: dup            
        //   106: ldc             0
        //   108: ldc             "result"
        //   110: aastore        
        //   111: dup            
        //   112: ldc             1
        //   114: ldc             "com/jetbrains/cidr/lang/OCIncludeHelpers"
        //   116: aastore        
        //   117: dup            
        //   118: ldc             2
        //   120: ldc             "resolveNextIncludedFile"
        //   122: aastore        
        //   123: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   126: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   129: athrow         
        //   130: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: aload           4
        //   136: invokeinterface com/intellij/openapi/project/Project.isDisposed:()Z
        //   141: ifeq            149
        //   144: return         
        //   145: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   148: athrow         
        //   149: aload_1        
        //   150: ifnull          167
        //   153: aload_1        
        //   154: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isValid:()Z
        //   157: ifne            172
        //   160: goto            167
        //   163: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: return         
        //   168: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: getstatic       com/jetbrains/cidr/lang/toolchains/DefaultCidrToolEnvironment.UNIX_PATH_SEPARATORS:[C
        //   175: astore          6
        //   177: aload_0        
        //   178: ifnull          249
        //   181: aload_0        
        //   182: invokevirtual   com/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration.getConfiguration:()Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;
        //   185: astore          7
        //   187: aload           7
        //   189: ifnull          249
        //   192: aload           7
        //   194: invokeinterface com/jetbrains/cidr/lang/workspace/OCResolveConfiguration.getCompilerSettings:()Lcom/jetbrains/cidr/lang/workspace/compiler/OCCompilerSettings;
        //   199: invokeinterface com/jetbrains/cidr/lang/workspace/compiler/OCCompilerSettings.getEnvironment:()Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment;
        //   204: astore          8
        //   206: aload           8
        //   208: invokevirtual   com/jetbrains/cidr/lang/toolchains/CidrToolEnvironment.getSupportedFileSeparators:()[C
        //   211: astore          6
        //   213: aload           7
        //   215: invokeinterface com/jetbrains/cidr/lang/workspace/OCResolveConfiguration.getIncludeMap:()Lcom/jetbrains/cidr/lang/workspace/OCIncludeMap;
        //   220: aload_3        
        //   221: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol$IncludePath.getPath:()Ljava/lang/String;
        //   224: aload_0        
        //   225: invokeinterface com/jetbrains/cidr/lang/workspace/OCIncludeMap.get:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   230: astore          9
        //   232: aload           9
        //   234: ifnull          249
        //   237: aload           5
        //   239: aload           9
        //   241: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   244: return         
        //   245: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: aload_3        
        //   250: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol$IncludePath.getPath:()Ljava/lang/String;
        //   253: astore          7
        //   255: aload           7
        //   257: aload           6
        //   259: iconst_0       
        //   260: iconst_0       
        //   261: invokestatic    kotlin/text/StringsKt.split:(Ljava/lang/CharSequence;[CZI)Ljava/util/List;
        //   264: astore          8
        //   266: aload           8
        //   268: invokeinterface java/util/List.size:()I
        //   273: ifne            281
        //   276: return         
        //   277: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   280: athrow         
        //   281: aload           7
        //   283: aload_0        
        //   284: invokestatic    com/jetbrains/cidr/lang/CustomHeaderProvider.getProviders:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration;)Lcom/jetbrains/cidr/lang/CustomHeaderProvider$Helper;
        //   287: astore          10
        //   289: aload           10
        //   291: getstatic       com/jetbrains/cidr/lang/CustomHeaderProvider$HeaderSearchStage.BEFORE_START:Lcom/jetbrains/cidr/lang/CustomHeaderProvider$HeaderSearchStage;
        //   294: invokevirtual   com/jetbrains/cidr/lang/CustomHeaderProvider$Helper.getCustomHeader:(Lcom/jetbrains/cidr/lang/CustomHeaderProvider$HeaderSearchStage;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   297: astore          9
        //   299: aload           9
        //   301: ifnull          316
        //   304: aload           5
        //   306: aload           9
        //   308: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   311: return         
        //   312: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   315: athrow         
        //   316: aload           8
        //   318: iconst_0       
        //   319: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   324: checkcast       Ljava/lang/String;
        //   327: astore          11
        //   329: aload           11
        //   331: invokevirtual   java/lang/String.isEmpty:()Z
        //   334: ifne            352
        //   337: aload           7
        //   339: invokestatic    com/intellij/openapi/util/io/FileUtil.isWindowsAbsolutePath:(Ljava/lang/String;)Z
        //   342: ifeq            398
        //   345: goto            352
        //   348: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   351: athrow         
        //   352: invokestatic    com/intellij/openapi/vfs/LocalFileSystem.getInstance:()Lcom/intellij/openapi/vfs/LocalFileSystem;
        //   355: aload           7
        //   357: invokevirtual   com/intellij/openapi/vfs/LocalFileSystem.findFileByPath:(Ljava/lang/String;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   360: astore          12
        //   362: aload           5
        //   364: aload           12
        //   366: ifnull          384
        //   369: aload           12
        //   371: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isDirectory:()Z
        //   374: ifeq            392
        //   377: goto            384
        //   380: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   383: athrow         
        //   384: aconst_null    
        //   385: goto            394
        //   388: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   391: athrow         
        //   392: aload           12
        //   394: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   397: return         
        //   398: aload           10
        //   400: getstatic       com/jetbrains/cidr/lang/CustomHeaderProvider$HeaderSearchStage.BEFORE_CURRENT:Lcom/jetbrains/cidr/lang/CustomHeaderProvider$HeaderSearchStage;
        //   403: invokevirtual   com/jetbrains/cidr/lang/CustomHeaderProvider$Helper.getCustomHeader:(Lcom/jetbrains/cidr/lang/CustomHeaderProvider$HeaderSearchStage;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   406: astore          9
        //   408: aload           9
        //   410: ifnull          425
        //   413: aload           5
        //   415: aload           9
        //   417: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   420: return         
        //   421: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   424: athrow         
        //   425: aload_2        
        //   426: ifnonnull       470
        //   429: aload_3        
        //   430: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol$IncludePath.isAngleBrackets:()Z
        //   433: ifne            470
        //   436: goto            443
        //   439: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   442: athrow         
        //   443: aload_1        
        //   444: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getName:()Ljava/lang/String;
        //   447: aload           7
        //   449: invokestatic    com/intellij/openapi/util/io/FileUtil.namesEqual:(Ljava/lang/String;Ljava/lang/String;)Z
        //   452: ifne            470
        //   455: goto            462
        //   458: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   461: athrow         
        //   462: iconst_1       
        //   463: goto            471
        //   466: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   469: athrow         
        //   470: iconst_0       
        //   471: istore          12
        //   473: new             Lcom/intellij/openapi/util/Ref;
        //   476: dup            
        //   477: aload_2        
        //   478: ifnonnull       489
        //   481: aconst_null    
        //   482: goto            493
        //   485: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   488: athrow         
        //   489: aload_2        
        //   490: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getParent:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   493: invokespecial   com/intellij/openapi/util/Ref.<init>:(Ljava/lang/Object;)V
        //   496: astore          13
        //   498: iload           12
        //   500: ifeq            569
        //   503: aload           4
        //   505: invokestatic    com/intellij/psi/PsiManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiManager;
        //   508: aload_1        
        //   509: invokestatic    com/intellij/psi/impl/source/resolve/reference/impl/providers/FileReferenceHelper.getPsiFileSystemItem:(Lcom/intellij/psi/PsiManager;Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/psi/PsiFileSystemItem;
        //   512: astore          14
        //   514: aload           14
        //   516: ifnull          569
        //   519: aload           14
        //   521: invokeinterface com/intellij/psi/PsiFileSystemItem.getParent:()Lcom/intellij/psi/PsiFileSystemItem;
        //   526: ifnull          569
        //   529: goto            536
        //   532: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   535: athrow         
        //   536: aload           14
        //   538: invokeinterface com/intellij/psi/PsiFileSystemItem.getParent:()Lcom/intellij/psi/PsiFileSystemItem;
        //   543: aload           11
        //   545: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Lcom/intellij/psi/PsiFileSystemItem;Ljava/lang/String;)Ljava/util/List;
        //   548: astore          15
        //   550: aload           15
        //   552: aload           8
        //   554: aload           13
        //   556: aload           5
        //   558: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/util/Collection;Ljava/util/List;Lcom/intellij/openapi/util/Ref;Lcom/intellij/openapi/util/Ref;)Z
        //   561: ifeq            569
        //   564: return         
        //   565: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   568: athrow         
        //   569: aload           10
        //   571: getstatic       com/jetbrains/cidr/lang/CustomHeaderProvider$HeaderSearchStage.BEFORE_LIBRARIES:Lcom/jetbrains/cidr/lang/CustomHeaderProvider$HeaderSearchStage;
        //   574: invokevirtual   com/jetbrains/cidr/lang/CustomHeaderProvider$Helper.getCustomHeader:(Lcom/jetbrains/cidr/lang/CustomHeaderProvider$HeaderSearchStage;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   577: astore          9
        //   579: aload           9
        //   581: ifnull          596
        //   584: aload           5
        //   586: aload           9
        //   588: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   591: return         
        //   592: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   595: athrow         
        //   596: aload_0        
        //   597: ifnull          668
        //   600: aload_3        
        //   601: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol$IncludePath.isAngleBrackets:()Z
        //   604: ifne            641
        //   607: goto            614
        //   610: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   613: athrow         
        //   614: aload_0        
        //   615: aload           11
        //   617: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache.getFirstSegmentsInProjectHeader:(Lcom/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration;Ljava/lang/String;)Ljava/util/Collection;
        //   620: astore          14
        //   622: aload           14
        //   624: aload           8
        //   626: aload           13
        //   628: aload           5
        //   630: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/util/Collection;Ljava/util/List;Lcom/intellij/openapi/util/Ref;Lcom/intellij/openapi/util/Ref;)Z
        //   633: ifeq            641
        //   636: return         
        //   637: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   640: athrow         
        //   641: aload_0        
        //   642: aload           11
        //   644: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache.getFirstSegmentsInLibraryRoots:(Lcom/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration;Ljava/lang/String;)Ljava/util/Collection;
        //   647: astore          14
        //   649: aload           14
        //   651: aload           8
        //   653: aload           13
        //   655: aload           5
        //   657: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/util/Collection;Ljava/util/List;Lcom/intellij/openapi/util/Ref;Lcom/intellij/openapi/util/Ref;)Z
        //   660: ifeq            668
        //   663: return         
        //   664: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   667: athrow         
        //   668: aload           10
        //   670: getstatic       com/jetbrains/cidr/lang/CustomHeaderProvider$HeaderSearchStage.BEFORE_FRAMEWORKS:Lcom/jetbrains/cidr/lang/CustomHeaderProvider$HeaderSearchStage;
        //   673: invokevirtual   com/jetbrains/cidr/lang/CustomHeaderProvider$Helper.getCustomHeader:(Lcom/jetbrains/cidr/lang/CustomHeaderProvider$HeaderSearchStage;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   676: astore          9
        //   678: aload           9
        //   680: ifnull          695
        //   683: aload           5
        //   685: aload           9
        //   687: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   690: return         
        //   691: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   694: athrow         
        //   695: aload           4
        //   697: aload_1        
        //   698: aload           11
        //   700: aload           8
        //   702: aload           13
        //   704: aload           5
        //   706: invokedynamic   process:(Ljava/lang/String;Ljava/util/List;Lcom/intellij/openapi/util/Ref;Lcom/intellij/openapi/util/Ref;)Lcom/intellij/util/Processor;
        //   711: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.processContainingFramework:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/util/Processor;)Z
        //   714: ifne            722
        //   717: return         
        //   718: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   721: athrow         
        //   722: aload           10
        //   724: getstatic       com/jetbrains/cidr/lang/CustomHeaderProvider$HeaderSearchStage.AFTER_END:Lcom/jetbrains/cidr/lang/CustomHeaderProvider$HeaderSearchStage;
        //   727: invokevirtual   com/jetbrains/cidr/lang/CustomHeaderProvider$Helper.getCustomHeader:(Lcom/jetbrains/cidr/lang/CustomHeaderProvider$HeaderSearchStage;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   730: astore          9
        //   732: aload           9
        //   734: ifnull          751
        //   737: aload           5
        //   739: aload           9
        //   741: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   744: goto            751
        //   747: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   750: athrow         
        //   751: return         
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/workspace/OCResolveRootAndConfiguration;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol$IncludePath;Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/util/Ref<Lcom/intellij/openapi/vfs/VirtualFile;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     85     85     89     Ljava/lang/IllegalArgumentException;
        //  89     130    130    134    Ljava/lang/IllegalArgumentException;
        //  134    145    145    149    Ljava/lang/IllegalArgumentException;
        //  149    160    163    167    Ljava/lang/IllegalArgumentException;
        //  153    168    168    172    Ljava/lang/IllegalArgumentException;
        //  232    245    245    249    Ljava/lang/IllegalArgumentException;
        //  266    277    277    281    Ljava/lang/IllegalArgumentException;
        //  299    312    312    316    Ljava/lang/IllegalArgumentException;
        //  329    345    348    352    Ljava/lang/IllegalArgumentException;
        //  362    377    380    384    Ljava/lang/IllegalArgumentException;
        //  369    388    388    392    Ljava/lang/IllegalArgumentException;
        //  408    421    421    425    Ljava/lang/IllegalArgumentException;
        //  425    436    439    443    Ljava/lang/IllegalArgumentException;
        //  429    455    458    462    Ljava/lang/IllegalArgumentException;
        //  443    466    466    470    Ljava/lang/IllegalArgumentException;
        //  473    485    485    489    Ljava/lang/IllegalArgumentException;
        //  514    529    532    536    Ljava/lang/IllegalArgumentException;
        //  550    565    565    569    Ljava/lang/IllegalArgumentException;
        //  579    592    592    596    Ljava/lang/IllegalArgumentException;
        //  596    607    610    614    Ljava/lang/IllegalArgumentException;
        //  622    637    637    641    Ljava/lang/IllegalArgumentException;
        //  649    664    664    668    Ljava/lang/IllegalArgumentException;
        //  678    691    691    695    Ljava/lang/IllegalArgumentException;
        //  695    718    718    722    Ljava/lang/IllegalArgumentException;
        //  732    744    747    751    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0443:
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
    public static AppleFramework getFramework(@NotNull final OCResolveRootAndConfiguration ocResolveRootAndConfiguration, @NotNull final String s) {
        try {
            if (ocResolveRootAndConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerResolveCtx", "com/jetbrains/cidr/lang/OCIncludeHelpers", "getFramework"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/OCIncludeHelpers", "getFramework"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (AppleFramework)ContainerUtil.find((Iterable)HeaderRootsSegmentCache.getFirstSegmentsInLibraryRoots(ocResolveRootAndConfiguration, s), (Condition)new FilteringIterator.InstanceOf((Class)AppleFramework.class));
    }
    
    private static boolean a(@NotNull final Collection<PsiFileSystemItem> collection, @NotNull final List<String> list, @NotNull final Ref<VirtualFile> ref, @NotNull final Ref<VirtualFile> ref2) {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "firstSegmentElements", "com/jetbrains/cidr/lang/OCIncludeHelpers", "findHeader"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pathElements", "com/jetbrains/cidr/lang/OCIncludeHelpers", "findHeader"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "afterDirRef", "com/jetbrains/cidr/lang/OCIncludeHelpers", "findHeader"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (ref2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/OCIncludeHelpers", "findHeader"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        for (final PsiFileSystemItem psiFileSystemItem : collection) {
            PsiFile a = null;
            if (list.size() != 1) {
                a = a(psiFileSystemItem, list, 1);
            }
            else if (psiFileSystemItem instanceof PsiFile) {
                a = (PsiFile)psiFileSystemItem;
            }
            if (a != null) {
                final VirtualFile virtualFile = a.getVirtualFile();
                if (virtualFile == null) {
                    continue;
                }
                final VirtualFile virtualFile2 = (VirtualFile)ref.get();
                try {
                    if (virtualFile2 == null) {
                        ref2.set((Object)virtualFile);
                        return true;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    if (!Comparing.equal((Object)virtualFile.getParent(), (Object)virtualFile2)) {
                        continue;
                    }
                    ref.set((Object)null);
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
        }
        return false;
    }
    
    public static boolean processContainingFramework(@NotNull final Project project, @Nullable final VirtualFile virtualFile, @NotNull final Processor<PsiFileSystemItem> processor) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/OCIncludeHelpers", "processContainingFramework"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/OCIncludeHelpers", "processContainingFramework"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (virtualFile == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        for (final OCIncludeHelper ocIncludeHelper : getIncludeHelpers()) {
            try {
                if (!ocIncludeHelper.processContainingFramework(project, virtualFile, processor)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return true;
    }
    
    public static boolean processImportSpecifications(@NotNull final Project project, @NotNull final OCResolveRootAndConfiguration ocResolveRootAndConfiguration, @Nullable final VirtualFile virtualFile, @NotNull final VirtualFile virtualFile2, @NotNull final Processor<OCAutoImportHelper.ImportSpecification> processor) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/OCIncludeHelpers", "processImportSpecifications"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveRootAndConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rootAndConfiguration", "com/jetbrains/cidr/lang/OCIncludeHelpers", "processImportSpecifications"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (virtualFile2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileToImport", "com/jetbrains/cidr/lang/OCIncludeHelpers", "processImportSpecifications"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/OCIncludeHelpers", "processImportSpecifications"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        for (final OCAutoImportHelper ocAutoImportHelper : (OCAutoImportHelper[])Extensions.getExtensions((ExtensionPointName)OCAutoImportHelper.EP_NAME)) {
            Label_0254: {
                try {
                    if (!ocAutoImportHelper.supports(ocResolveRootAndConfiguration)) {
                        break Label_0254;
                    }
                    final OCAutoImportHelper ocAutoImportHelper2 = ocAutoImportHelper;
                    final Project project2 = project;
                    final VirtualFile virtualFile3 = virtualFile;
                    final VirtualFile virtualFile4 = virtualFile2;
                    final OCResolveRootAndConfiguration ocResolveRootAndConfiguration2 = ocResolveRootAndConfiguration;
                    final Processor<OCAutoImportHelper.ImportSpecification> processor2 = processor;
                    final boolean b = ocAutoImportHelper2.processPathSpecificationToInclude(project2, virtualFile3, virtualFile4, ocResolveRootAndConfiguration2, processor2);
                    if (!b) {
                        return false;
                    }
                    break Label_0254;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    final OCAutoImportHelper ocAutoImportHelper2 = ocAutoImportHelper;
                    final Project project2 = project;
                    final VirtualFile virtualFile3 = virtualFile;
                    final VirtualFile virtualFile4 = virtualFile2;
                    final OCResolveRootAndConfiguration ocResolveRootAndConfiguration2 = ocResolveRootAndConfiguration;
                    final Processor<OCAutoImportHelper.ImportSpecification> processor2 = processor;
                    final boolean b = ocAutoImportHelper2.processPathSpecificationToInclude(project2, virtualFile3, virtualFile4, ocResolveRootAndConfiguration2, processor2);
                    if (!b) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
        }
        return true;
    }
    
    @Nullable
    private static PsiFile a(final PsiFileSystemItem psiFileSystemItem, final List<String> list, final int n) {
        Label_0021: {
            try {
                if (psiFileSystemItem == null) {
                    break Label_0021;
                }
                final int n2 = n;
                final List<String> list2 = list;
                final int n3 = list2.size();
                if (n2 == n3) {
                    break Label_0021;
                }
                break Label_0021;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final int n2 = n;
                final List<String> list2 = list;
                final int n3 = list2.size();
                if (n2 == n3) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final String s = list.get(n);
        try {
            if (s.isEmpty()) {
                return a(psiFileSystemItem, list, n + 1);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        for (final PsiFileSystemItem psiFileSystemItem2 : a(psiFileSystemItem, s)) {
            Label_0119: {
                try {
                    if (n != list.size() - 1) {
                        break Label_0119;
                    }
                    final PsiFileSystemItem psiFileSystemItem3 = psiFileSystemItem2;
                    final boolean b = psiFileSystemItem3 instanceof PsiFile;
                    if (b) {
                        break Label_0119;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    final PsiFileSystemItem psiFileSystemItem3 = psiFileSystemItem2;
                    final boolean b = psiFileSystemItem3 instanceof PsiFile;
                    if (b) {
                        return (PsiFile)psiFileSystemItem2;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            final PsiFile a = a(psiFileSystemItem2, list, n + 1);
            try {
                if (a != null) {
                    return a;
                }
                continue;
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return null;
    }
    
    @NotNull
    private static List<PsiFileSystemItem> a(final PsiFileSystemItem p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          20
        //     4: aload_0        
        //     5: invokeinterface com/intellij/psi/PsiFileSystemItem.isValid:()Z
        //    10: ifne            69
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    19: athrow         
        //    20: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    23: dup            
        //    24: ifnonnull       68
        //    27: goto            34
        //    30: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    33: athrow         
        //    34: new             Ljava/lang/IllegalStateException;
        //    37: dup            
        //    38: ldc             "@NotNull method %s.%s must not return null"
        //    40: ldc             2
        //    42: anewarray       Ljava/lang/Object;
        //    45: dup            
        //    46: ldc             0
        //    48: ldc             "com/jetbrains/cidr/lang/OCIncludeHelpers"
        //    50: aastore        
        //    51: dup            
        //    52: ldc             1
        //    54: ldc             "findChild"
        //    56: aastore        
        //    57: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    60: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    63: athrow         
        //    64: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: areturn        
        //    69: ldc             ".."
        //    71: aload_1        
        //    72: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    75: ifeq            318
        //    78: aload_0        
        //    79: invokeinterface com/intellij/psi/PsiFileSystemItem.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //    84: astore_2       
        //    85: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.getIncludeHelpers:()[Lcom/jetbrains/cidr/lang/OCIncludeHelper;
        //    88: astore_3       
        //    89: aload_3        
        //    90: arraylength    
        //    91: istore          4
        //    93: iconst_0       
        //    94: istore          5
        //    96: iload           5
        //    98: iload           4
        //   100: if_icmpge       136
        //   103: aload_3        
        //   104: iload           5
        //   106: aaload         
        //   107: astore          6
        //   109: aload           6
        //   111: aload_0        
        //   112: invokeinterface com/jetbrains/cidr/lang/OCIncludeHelper.findHeadersRoot:(Lcom/intellij/psi/PsiFileSystemItem;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   117: astore          7
        //   119: aload           7
        //   121: ifnull          130
        //   124: aload           7
        //   126: astore_2       
        //   127: goto            136
        //   130: iinc            5, 1
        //   133: goto            96
        //   136: aload_2        
        //   137: ifnonnull       189
        //   140: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   143: dup            
        //   144: ifnonnull       188
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: new             Ljava/lang/IllegalStateException;
        //   157: dup            
        //   158: ldc             "@NotNull method %s.%s must not return null"
        //   160: ldc             2
        //   162: anewarray       Ljava/lang/Object;
        //   165: dup            
        //   166: ldc             0
        //   168: ldc             "com/jetbrains/cidr/lang/OCIncludeHelpers"
        //   170: aastore        
        //   171: dup            
        //   172: ldc             1
        //   174: ldc             "findChild"
        //   176: aastore        
        //   177: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   180: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   183: athrow         
        //   184: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: areturn        
        //   189: aload_2        
        //   190: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getParent:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   193: astore_3       
        //   194: aload_3        
        //   195: ifnonnull       247
        //   198: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   201: dup            
        //   202: ifnonnull       246
        //   205: goto            212
        //   208: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   211: athrow         
        //   212: new             Ljava/lang/IllegalStateException;
        //   215: dup            
        //   216: ldc             "@NotNull method %s.%s must not return null"
        //   218: ldc             2
        //   220: anewarray       Ljava/lang/Object;
        //   223: dup            
        //   224: ldc             0
        //   226: ldc             "com/jetbrains/cidr/lang/OCIncludeHelpers"
        //   228: aastore        
        //   229: dup            
        //   230: ldc             1
        //   232: ldc             "findChild"
        //   234: aastore        
        //   235: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   238: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   241: athrow         
        //   242: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   245: athrow         
        //   246: areturn        
        //   247: aload_0        
        //   248: invokeinterface com/intellij/psi/PsiFileSystemItem.getManager:()Lcom/intellij/psi/PsiManager;
        //   253: aload_3        
        //   254: invokevirtual   com/intellij/psi/PsiManager.findDirectory:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/psi/PsiDirectory;
        //   257: astore          4
        //   259: aload           4
        //   261: ifnull          276
        //   264: aload           4
        //   266: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   269: goto            279
        //   272: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   275: athrow         
        //   276: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   279: dup            
        //   280: ifnonnull       317
        //   283: new             Ljava/lang/IllegalStateException;
        //   286: dup            
        //   287: ldc             "@NotNull method %s.%s must not return null"
        //   289: ldc             2
        //   291: anewarray       Ljava/lang/Object;
        //   294: dup            
        //   295: ldc             0
        //   297: ldc             "com/jetbrains/cidr/lang/OCIncludeHelpers"
        //   299: aastore        
        //   300: dup            
        //   301: ldc             1
        //   303: ldc             "findChild"
        //   305: aastore        
        //   306: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   309: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   312: athrow         
        //   313: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   316: athrow         
        //   317: areturn        
        //   318: ldc             "."
        //   320: aload_1        
        //   321: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   324: ifeq            377
        //   327: aload_0        
        //   328: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   331: dup            
        //   332: ifnonnull       376
        //   335: goto            342
        //   338: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   341: athrow         
        //   342: new             Ljava/lang/IllegalStateException;
        //   345: dup            
        //   346: ldc             "@NotNull method %s.%s must not return null"
        //   348: ldc             2
        //   350: anewarray       Ljava/lang/Object;
        //   353: dup            
        //   354: ldc             0
        //   356: ldc             "com/jetbrains/cidr/lang/OCIncludeHelpers"
        //   358: aastore        
        //   359: dup            
        //   360: ldc             1
        //   362: ldc             "findChild"
        //   364: aastore        
        //   365: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   368: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   371: athrow         
        //   372: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   375: athrow         
        //   376: areturn        
        //   377: aload_0        
        //   378: instanceof      Lcom/intellij/psi/PsiDirectory;
        //   381: ifeq            449
        //   384: aload_0        
        //   385: checkcast       Lcom/intellij/psi/PsiDirectory;
        //   388: aload_1        
        //   389: invokeinterface com/intellij/psi/PsiDirectory.findFile:(Ljava/lang/String;)Lcom/intellij/psi/PsiFile;
        //   394: astore_2       
        //   395: aload_2        
        //   396: ifnull          449
        //   399: aload_2        
        //   400: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   403: dup            
        //   404: ifnonnull       448
        //   407: goto            414
        //   410: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   413: athrow         
        //   414: new             Ljava/lang/IllegalStateException;
        //   417: dup            
        //   418: ldc             "@NotNull method %s.%s must not return null"
        //   420: ldc             2
        //   422: anewarray       Ljava/lang/Object;
        //   425: dup            
        //   426: ldc             0
        //   428: ldc             "com/jetbrains/cidr/lang/OCIncludeHelpers"
        //   430: aastore        
        //   431: dup            
        //   432: ldc             1
        //   434: ldc             "findChild"
        //   436: aastore        
        //   437: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   440: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   443: athrow         
        //   444: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   447: athrow         
        //   448: areturn        
        //   449: new             Lcom/intellij/util/SmartList;
        //   452: dup            
        //   453: invokespecial   com/intellij/util/SmartList.<init>:()V
        //   456: astore_2       
        //   457: aload_0        
        //   458: new             Lcom/jetbrains/cidr/lang/OCIncludeHelpers$1;
        //   461: dup            
        //   462: aload_1        
        //   463: aload_2        
        //   464: invokespecial   com/jetbrains/cidr/lang/OCIncludeHelpers$1.<init>:(Ljava/lang/String;Lcom/intellij/util/SmartList;)V
        //   467: invokeinterface com/intellij/psi/PsiFileSystemItem.processChildren:(Lcom/intellij/psi/search/PsiElementProcessor;)Z
        //   472: pop            
        //   473: aload_2        
        //   474: dup            
        //   475: ifnonnull       512
        //   478: new             Ljava/lang/IllegalStateException;
        //   481: dup            
        //   482: ldc             "@NotNull method %s.%s must not return null"
        //   484: ldc             2
        //   486: anewarray       Ljava/lang/Object;
        //   489: dup            
        //   490: ldc             0
        //   492: ldc             "com/jetbrains/cidr/lang/OCIncludeHelpers"
        //   494: aastore        
        //   495: dup            
        //   496: ldc             1
        //   498: ldc             "findChild"
        //   500: aastore        
        //   501: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   504: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   507: athrow         
        //   508: invokestatic    com/jetbrains/cidr/lang/OCIncludeHelpers.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   511: athrow         
        //   512: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiFileSystemItem;Ljava/lang/String;)Ljava/util/List<Lcom/intellij/psi/PsiFileSystemItem;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      13     16     20     Ljava/lang/IllegalArgumentException;
        //  4      27     30     34     Ljava/lang/IllegalArgumentException;
        //  20     64     64     68     Ljava/lang/IllegalArgumentException;
        //  136    147    150    154    Ljava/lang/IllegalArgumentException;
        //  140    184    184    188    Ljava/lang/IllegalArgumentException;
        //  194    205    208    212    Ljava/lang/IllegalArgumentException;
        //  198    242    242    246    Ljava/lang/IllegalArgumentException;
        //  259    272    272    276    Ljava/lang/IllegalArgumentException;
        //  279    313    313    317    Ljava/lang/IllegalArgumentException;
        //  318    335    338    342    Ljava/lang/IllegalArgumentException;
        //  327    372    372    376    Ljava/lang/IllegalArgumentException;
        //  395    407    410    414    Ljava/lang/IllegalArgumentException;
        //  399    444    444    448    Ljava/lang/IllegalArgumentException;
        //  457    508    508    512    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
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
    
    public enum ShowInCompletion
    {
        SHOW, 
        DON_NOT_SHOW, 
        DEFAULT;
    }
    
    private static class HeaderRootsSegmentCache
    {
        private static final NotNullLazyKey<CachedValue<HeaderRootsSegmentCache>, Project> RESOLVE_INCLUDE_IN_PROJECT_HEADERS_CACHE;
        private static final NotNullLazyKey<CachedValue<HeaderRootsSegmentCache>, Project> RESOLVE_INCLUDE_CACHE;
        @NotNull
        private final HashMap<HeaderRoots, FirstSegmentCache> myCache;
        private final boolean mySupportsRelative;
        
        private HeaderRootsSegmentCache(final boolean mySupportsRelative) {
            this.myCache = new HashMap<HeaderRoots, FirstSegmentCache>();
            this.mySupportsRelative = mySupportsRelative;
        }
        
        @NotNull
        static Collection<PsiFileSystemItem> getFirstSegmentsInLibraryRoots(final OCResolveRootAndConfiguration ocResolveRootAndConfiguration, final String s) {
            final Project project = ocResolveRootAndConfiguration.getProject();
            Label_0058: {
                List<PsiFileSystemItem> list = null;
                Label_0023: {
                    try {
                        if (project != null) {
                            break Label_0058;
                        }
                        list = Collections.emptyList();
                        if (list == null) {
                            break Label_0023;
                        }
                        return list;
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    try {
                        list = Collections.emptyList();
                        if (list == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache", "getFirstSegmentsInLibraryRoots"));
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                }
                return list;
            }
            final FirstSegmentCache a = ((HeaderRootsSegmentCache)((CachedValue)HeaderRootsSegmentCache.RESOLVE_INCLUDE_CACHE.getValue((UserDataHolder)project)).getValue()).a(ocResolveRootAndConfiguration.getLibraryHeadersRoots());
            Collection access$000;
            try {
                access$000 = a.a(s);
                if (access$000 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache", "getFirstSegmentsInLibraryRoots"));
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            return (Collection<PsiFileSystemItem>)access$000;
        }
        
        @NotNull
        static Collection<PsiFileSystemItem> getFirstSegmentsInProjectHeader(@NotNull final OCResolveRootAndConfiguration ocResolveRootAndConfiguration, @NotNull final String s) {
            try {
                if (ocResolveRootAndConfiguration == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerResolveCtx", "com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache", "getFirstSegmentsInProjectHeader"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "firstSegment", "com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache", "getFirstSegmentsInProjectHeader"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            final Project project = ocResolveRootAndConfiguration.getProject();
            Label_0146: {
                List<PsiFileSystemItem> list = null;
                Label_0111: {
                    try {
                        if (project != null) {
                            break Label_0146;
                        }
                        list = Collections.emptyList();
                        if (list == null) {
                            break Label_0111;
                        }
                        return list;
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                    try {
                        list = Collections.emptyList();
                        if (list == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache", "getFirstSegmentsInProjectHeader"));
                        }
                    }
                    catch (IllegalStateException ex4) {
                        throw a(ex4);
                    }
                }
                return list;
            }
            final FirstSegmentCache a = ((HeaderRootsSegmentCache)((CachedValue)HeaderRootsSegmentCache.RESOLVE_INCLUDE_IN_PROJECT_HEADERS_CACHE.getValue((UserDataHolder)project)).getValue()).a(ocResolveRootAndConfiguration.getProjectHeadersRoots());
            Collection access$000;
            try {
                access$000 = a.a(s);
                if (access$000 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache", "getFirstSegmentsInProjectHeader"));
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
            return (Collection<PsiFileSystemItem>)access$000;
        }
        
        private synchronized FirstSegmentCache a(@NotNull final HeaderRoots headerRoots) {
            try {
                if (headerRoots == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "roots", "com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache", "getFirstSegmentCache"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            FirstSegmentCache firstSegmentCache = this.myCache.get(headerRoots);
            if (firstSegmentCache == null) {
                firstSegmentCache = new FirstSegmentCache((List)headerRoots.getRoots(), this.mySupportsRelative);
                this.myCache.put(headerRoots, firstSegmentCache);
            }
            return firstSegmentCache;
        }
        
        private static NotNullLazyKey<CachedValue<HeaderRootsSegmentCache>, Project> a(@NotNull final String s, final boolean b) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/OCIncludeHelpers$HeaderRootsSegmentCache", "createCacheKey"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return (NotNullLazyKey<CachedValue<HeaderRootsSegmentCache>, Project>)NotNullLazyKey.create(s, project -> CachedValuesManager.getManager(project).createCachedValue(() -> {
                final OCWorkspaceModificationTrackers instance = OCWorkspaceModificationTrackers.getInstance(project);
                return new CachedValueProvider.Result((Object)new HeaderRootsSegmentCache(b), new Object[] { instance.getProjectsListTracker(), instance.getProjectFilesListTracker(), instance.getSourceFilesListTracker(), instance.getSelectedResolveConfigurationTracker() });
            }, false));
        }
        
        static {
            RESOLVE_INCLUDE_IN_PROJECT_HEADERS_CACHE = a("RESOLVE_INCLUDE_IN_PROJECT_HEADERS_CACHE", false);
            RESOLVE_INCLUDE_CACHE = a("RESOLVE_INCLUDE_CACHE", true);
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
    
    private static class FirstSegmentCache
    {
        @NotNull
        private final List<HeadersSearchRoot> myRoots;
        private final boolean mySupportsRelative;
        private Map<String, List<PsiFileSystemItem>> myCache;
        
        private FirstSegmentCache(@NotNull final List<HeadersSearchRoot> myRoots, final boolean mySupportsRelative) {
            if (myRoots == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "roots", "com/jetbrains/cidr/lang/OCIncludeHelpers$FirstSegmentCache", "<init>"));
            }
            this.mySupportsRelative = mySupportsRelative;
            this.myRoots = myRoots;
        }
        
        private void a() {
            try {
                if (this.myCache != null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.myCache = (Map<String, List<PsiFileSystemItem>>)new THashMap(FileUtil.PATH_HASHING_STRATEGY);
            for (final PsiFileSystemItem psiFileSystemItem : this.myRoots) {
                try {
                    if (!psiFileSystemItem.isValid()) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                psiFileSystemItem.processChildren((PsiElementProcessor)new PsiElementProcessor<PsiFileSystemItem>() {
                    public boolean execute(@NotNull final PsiFileSystemItem psiFileSystemItem) {
                        try {
                            if (psiFileSystemItem == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/OCIncludeHelpers$FirstSegmentCache$1", "execute"));
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        final String name = psiFileSystemItem.getName();
                        List<PsiFileSystemItem> list = FirstSegmentCache.this.myCache.get(name);
                        if (list == null) {
                            list = new ArrayList<PsiFileSystemItem>();
                            FirstSegmentCache.this.myCache.put(name, list);
                        }
                        list.add(psiFileSystemItem);
                        return true;
                    }
                    
                    private static IllegalArgumentException a(final IllegalArgumentException ex) {
                        return ex;
                    }
                });
            }
        }
        
        private synchronized Collection<PsiFileSystemItem> a(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "segment", "com/jetbrains/cidr/lang/OCIncludeHelpers$FirstSegmentCache", "getFiles"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.a();
            List<PsiFileSystemItem> emptyList = this.myCache.get(s);
            try {
                if (emptyList != null) {
                    return emptyList;
                }
                if (!this.mySupportsRelative) {
                    return (Collection<PsiFileSystemItem>)Collections.emptyList();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            emptyList = new ArrayList<PsiFileSystemItem>();
            for (final PsiFileSystemItem psiFileSystemItem : this.myRoots) {
                try {
                    if (!psiFileSystemItem.isValid()) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                emptyList.addAll(a(psiFileSystemItem, s));
            }
            this.myCache.put(s, emptyList);
            return emptyList;
            emptyList = Collections.emptyList();
            return emptyList;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
