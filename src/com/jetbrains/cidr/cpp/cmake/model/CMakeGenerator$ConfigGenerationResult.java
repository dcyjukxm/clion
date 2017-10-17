// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.openapi.diagnostic.Logger;
import java.util.Comparator;
import com.intellij.util.Processor;
import org.jetbrains.annotations.Contract;
import com.intellij.openapi.util.text.StringUtil;
import java.util.regex.Matcher;
import com.jetbrains.cidr.cpp.cmake.CMakeException;
import java.io.IOException;
import com.jetbrains.cidr.cpp.CPPLog;
import org.jdom.JDOMException;
import java.util.Iterator;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import com.jetbrains.cidr.lang.OCLanguageKind;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.File;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

private class ConfigGenerationResult
{
    @NotNull
    final Parameters parameters;
    @Nullable
    final CMakeModel previousModel;
    @NotNull
    String actualConfigName;
    @NotNull
    File cacheIOFile;
    @NotNull
    String cacheIOFileEncoding;
    @Nullable
    CMakeCacheFile loadedCacheFile;
    @Nullable
    CMakeCodeBlocks codeBlocksProject;
    @NotNull
    List<PerConfigurationTargetInfo> targetInfos;
    @NotNull
    List<String> registeredConfigurationTypes;
    private boolean hasGenerationError;
    
    private ConfigGenerationResult(@Nullable final Parameters parameters, final CMakeModel previousModel) {
        if (parameters == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult", "<init>"));
        }
        this.targetInfos = Collections.emptyList();
        this.registeredConfigurationTypes = new ArrayList<String>(CMakeModel.DEFAULT_CONFIGURATION_TYPES);
        this.hasGenerationError = false;
        this.parameters = parameters;
        this.previousModel = previousModel;
    }
    
    private void a(@NotNull final Set<File> set, @NotNull final Set<File> set2, @NotNull final Map<String, OCLanguageKind> map) {
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmakeListsFiles", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult", "doGenerate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (set2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerAndResourceFiles", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult", "doGenerate"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "extensions", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult", "doGenerate"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        this.a(set, set2, map, false);
    }
    
    private void a(@NotNull final Set<File> p0, @NotNull final Set<File> p1, @NotNull final Map<String, OCLanguageKind> p2, final boolean p3) {
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
        //    18: ldc             "cmakeListsFiles"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "doGenerate"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "headerAndResourceFiles"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "doGenerate"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //   106: ldc             "extensions"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "doGenerate"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   131: athrow         
        //   132: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   135: invokestatic    java/lang/System.currentTimeMillis:()J
        //   138: lstore          5
        //   140: iconst_0       
        //   141: istore          7
        //   143: aload_0        
        //   144: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.this$0:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;
        //   147: aload_0        
        //   148: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.parameters:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters;
        //   151: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$100:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters;)Lcom/intellij/execution/process/ProcessOutput;
        //   154: astore          8
        //   156: aload_0        
        //   157: aload           8
        //   159: invokevirtual   com/intellij/execution/process/ProcessOutput.getExitCode:()I
        //   162: ifeq            173
        //   165: iconst_1       
        //   166: goto            174
        //   169: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   172: athrow         
        //   173: iconst_0       
        //   174: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.hasGenerationError:Z
        //   177: aload           8
        //   179: invokevirtual   com/intellij/execution/process/ProcessOutput.getStdout:()Ljava/lang/String;
        //   182: ldc             "You have changed variables that require your cache to be deleted."
        //   184: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   187: istore          7
        //   189: invokestatic    java/lang/System.currentTimeMillis:()J
        //   192: lstore          8
        //   194: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   197: new             Ljava/lang/StringBuilder;
        //   200: dup            
        //   201: invokespecial   java/lang/StringBuilder.<init>:()V
        //   204: ldc             "CMake generation for "
        //   206: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   209: aload_0        
        //   210: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.parameters:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters;
        //   213: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters.configName:Ljava/lang/String;
        //   216: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   219: ldc             " took "
        //   221: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   224: lload           8
        //   226: lload           5
        //   228: lsub           
        //   229: invokestatic    com/intellij/openapi/util/text/StringUtil.formatDuration:(J)Ljava/lang/String;
        //   232: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   235: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   238: invokevirtual   com/intellij/openapi/diagnostic/Logger.info:(Ljava/lang/String;)V
        //   241: goto            413
        //   244: astore          8
        //   246: aload_0        
        //   247: iconst_1       
        //   248: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.hasGenerationError:Z
        //   251: aload           8
        //   253: instanceof      Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$EmulatedCMakeException;
        //   256: ifne            274
        //   259: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   262: aload           8
        //   264: invokevirtual   com/intellij/openapi/diagnostic/Logger.info:(Ljava/lang/Throwable;)V
        //   267: goto            274
        //   270: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   273: athrow         
        //   274: aload_0        
        //   275: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.parameters:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters;
        //   278: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters.listener:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeListener;
        //   281: aload           8
        //   283: aload_0        
        //   284: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.this$0:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;
        //   287: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$200:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;)Ljava/io/File;
        //   290: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$300:(Ljava/io/File;)Ljava/io/File;
        //   293: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeMessage.fromException:(Lcom/jetbrains/cidr/cpp/cmake/CMakeException;Ljava/io/File;)Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage;
        //   296: invokeinterface com/jetbrains/cidr/cpp/cmake/model/CMakeListener.message:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage;)V
        //   301: invokestatic    java/lang/System.currentTimeMillis:()J
        //   304: lstore          8
        //   306: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   309: new             Ljava/lang/StringBuilder;
        //   312: dup            
        //   313: invokespecial   java/lang/StringBuilder.<init>:()V
        //   316: ldc             "CMake generation for "
        //   318: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   321: aload_0        
        //   322: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.parameters:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters;
        //   325: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters.configName:Ljava/lang/String;
        //   328: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   331: ldc             " took "
        //   333: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   336: lload           8
        //   338: lload           5
        //   340: lsub           
        //   341: invokestatic    com/intellij/openapi/util/text/StringUtil.formatDuration:(J)Ljava/lang/String;
        //   344: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   347: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   350: invokevirtual   com/intellij/openapi/diagnostic/Logger.info:(Ljava/lang/String;)V
        //   353: goto            413
        //   356: astore          10
        //   358: invokestatic    java/lang/System.currentTimeMillis:()J
        //   361: lstore          11
        //   363: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   366: new             Ljava/lang/StringBuilder;
        //   369: dup            
        //   370: invokespecial   java/lang/StringBuilder.<init>:()V
        //   373: ldc             "CMake generation for "
        //   375: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   378: aload_0        
        //   379: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.parameters:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters;
        //   382: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters.configName:Ljava/lang/String;
        //   385: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   388: ldc             " took "
        //   390: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   393: lload           11
        //   395: lload           5
        //   397: lsub           
        //   398: invokestatic    com/intellij/openapi/util/text/StringUtil.formatDuration:(J)Ljava/lang/String;
        //   401: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   404: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   407: invokevirtual   com/intellij/openapi/diagnostic/Logger.info:(Ljava/lang/String;)V
        //   410: aload           10
        //   412: athrow         
        //   413: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   416: aload_0        
        //   417: new             Ljava/io/File;
        //   420: dup            
        //   421: aload_0        
        //   422: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.parameters:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters;
        //   425: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters.generationDir:Ljava/io/File;
        //   428: ldc             "CMakeCache.txt"
        //   430: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   433: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.cacheIOFile:Ljava/io/File;
        //   436: aload_0        
        //   437: aload_0        
        //   438: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.this$0:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;
        //   441: aload_0        
        //   442: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.cacheIOFile:Ljava/io/File;
        //   445: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$400:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;Ljava/io/File;)Ljava/lang/String;
        //   448: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.cacheIOFileEncoding:Ljava/lang/String;
        //   451: aload_0        
        //   452: aload_0        
        //   453: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.parameters:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters;
        //   456: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters.configName:Ljava/lang/String;
        //   459: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.actualConfigName:Ljava/lang/String;
        //   462: iconst_0       
        //   463: istore          8
        //   465: aload_0        
        //   466: new             Lcom/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile;
        //   469: dup            
        //   470: aload_0        
        //   471: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.cacheIOFile:Ljava/io/File;
        //   474: aload_0        
        //   475: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.cacheIOFileEncoding:Ljava/lang/String;
        //   478: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   481: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.loadedCacheFile:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile;
        //   484: aload_0        
        //   485: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.loadedCacheFile:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile;
        //   488: ldc             "CMAKE_BUILD_TYPE"
        //   490: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile.getVariableValue:(Ljava/lang/String;)Ljava/lang/String;
        //   493: astore          9
        //   495: aload_0        
        //   496: aload           9
        //   498: ldc             "Default"
        //   500: invokestatic    com/intellij/openapi/util/text/StringUtil.notNullize:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   503: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.actualConfigName:Ljava/lang/String;
        //   506: iload           7
        //   508: ifeq            531
        //   511: aload           9
        //   513: ifnonnull       531
        //   516: goto            523
        //   519: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   522: athrow         
        //   523: iconst_1       
        //   524: goto            532
        //   527: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   530: athrow         
        //   531: iconst_0       
        //   532: istore          8
        //   534: goto            580
        //   537: astore          9
        //   539: aload_0        
        //   540: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.hasGenerationError:Z
        //   543: ifne            580
        //   546: aload_0        
        //   547: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.parameters:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters;
        //   550: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters.listener:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeListener;
        //   553: aload           9
        //   555: aload_0        
        //   556: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.this$0:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;
        //   559: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$200:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;)Ljava/io/File;
        //   562: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$300:(Ljava/io/File;)Ljava/io/File;
        //   565: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeMessage.fromException:(Lcom/jetbrains/cidr/cpp/cmake/CMakeException;Ljava/io/File;)Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage;
        //   568: invokeinterface com/jetbrains/cidr/cpp/cmake/model/CMakeListener.message:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage;)V
        //   573: goto            580
        //   576: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   579: athrow         
        //   580: iload           8
        //   582: ifeq            706
        //   585: iload           4
        //   587: ifne            706
        //   590: goto            597
        //   593: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   596: athrow         
        //   597: aload_0        
        //   598: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.hasGenerationError:Z
        //   601: ifne            706
        //   604: goto            611
        //   607: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   610: athrow         
        //   611: ldc             "cidr.cmake.cacheResetFix"
        //   613: iconst_1       
        //   614: invokestatic    com/intellij/openapi/util/registry/Registry.is:(Ljava/lang/String;Z)Z
        //   617: ifeq            706
        //   620: goto            627
        //   623: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   626: athrow         
        //   627: aload_0        
        //   628: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.parameters:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters;
        //   631: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters.listener:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeListener;
        //   634: new             Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage;
        //   637: dup            
        //   638: getstatic       com/jetbrains/cidr/cpp/cmake/model/CMakeMessage$MessageLevel.WARNING:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage$MessageLevel;
        //   641: aconst_null    
        //   642: aconst_null    
        //   643: new             Ljava/lang/StringBuilder;
        //   646: dup            
        //   647: invokespecial   java/lang/StringBuilder.<init>:()V
        //   650: ldc             "\n"
        //   652: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   655: ldc             "cmake.willBeRegenerated"
        //   657: iconst_0       
        //   658: anewarray       Ljava/lang/Object;
        //   661: invokestatic    com/jetbrains/cidr/cpp/CPPBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   664: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   667: ldc             "\n"
        //   669: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   672: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   675: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeMessage.<init>:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage$MessageLevel;Ljava/io/File;Ljava/lang/Integer;Ljava/lang/String;)V
        //   678: invokeinterface com/jetbrains/cidr/cpp/cmake/model/CMakeListener.message:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage;)V
        //   683: aload_0        
        //   684: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.parameters:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters;
        //   687: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters.generationDir:Ljava/io/File;
        //   690: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.clearGeneratedCMakeFiles:(Ljava/io/File;)V
        //   693: aload_0        
        //   694: aload_1        
        //   695: aload_2        
        //   696: aload_3        
        //   697: iconst_1       
        //   698: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.a:(Ljava/util/Set;Ljava/util/Set;Ljava/util/Map;Z)V
        //   701: return         
        //   702: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   705: athrow         
        //   706: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   709: aload_0        
        //   710: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.loadedCacheFile:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile;
        //   713: ifnull          941
        //   716: aload_0        
        //   717: aload_0        
        //   718: aload_0        
        //   719: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.loadedCacheFile:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile;
        //   722: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.a:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile;)Lcom/jetbrains/cidr/cpp/cmake/model/CMakeCodeBlocks;
        //   725: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.codeBlocksProject:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeCodeBlocks;
        //   728: aload_0        
        //   729: aload_0        
        //   730: aload_0        
        //   731: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.loadedCacheFile:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile;
        //   734: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile;)Ljava/util/List;
        //   737: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.targetInfos:Ljava/util/List;
        //   740: aload_0        
        //   741: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.loadedCacheFile:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile;
        //   744: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile.getConfigurationTypesValue:()Ljava/lang/String;
        //   747: astore          9
        //   749: aload           9
        //   751: ifnull          830
        //   754: aload           9
        //   756: ldc             ";"
        //   758: invokestatic    com/intellij/openapi/util/text/StringUtil.split:(Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //   761: astore          10
        //   763: aload_0        
        //   764: new             Ljava/util/ArrayList;
        //   767: dup            
        //   768: aload           10
        //   770: invokeinterface java/util/List.size:()I
        //   775: invokespecial   java/util/ArrayList.<init>:(I)V
        //   778: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.registeredConfigurationTypes:Ljava/util/List;
        //   781: aload           10
        //   783: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   788: astore          11
        //   790: aload           11
        //   792: invokeinterface java/util/Iterator.hasNext:()Z
        //   797: ifeq            830
        //   800: aload           11
        //   802: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   807: checkcast       Ljava/lang/String;
        //   810: astore          12
        //   812: aload_0        
        //   813: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.registeredConfigurationTypes:Ljava/util/List;
        //   816: aload           12
        //   818: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   821: invokestatic    com/intellij/openapi/util/text/StringUtil.nullize:(Ljava/lang/String;)Ljava/lang/String;
        //   824: invokestatic    com/intellij/util/containers/ContainerUtil.addIfNotNull:(Ljava/util/Collection;Ljava/lang/Object;)V
        //   827: goto            790
        //   830: aload_0        
        //   831: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.targetInfos:Ljava/util/List;
        //   834: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   839: astore          10
        //   841: aload           10
        //   843: invokeinterface java/util/Iterator.hasNext:()Z
        //   848: ifeq            907
        //   851: aload           10
        //   853: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   858: checkcast       Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo;
        //   861: astore          11
        //   863: aload_0        
        //   864: aload           11
        //   866: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.a:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo;)V
        //   869: goto            904
        //   872: astore          12
        //   874: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   877: aload           12
        //   879: invokevirtual   com/intellij/openapi/diagnostic/Logger.info:(Ljava/lang/Throwable;)V
        //   882: aload_0        
        //   883: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.parameters:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters;
        //   886: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters.listener:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeListener;
        //   889: aload           12
        //   891: aload           11
        //   893: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.cmakeListsFile:Ljava/io/File;
        //   896: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeMessage.fromException:(Lcom/jetbrains/cidr/cpp/cmake/CMakeException;Ljava/io/File;)Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage;
        //   899: invokeinterface com/jetbrains/cidr/cpp/cmake/model/CMakeListener.message:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage;)V
        //   904: goto            841
        //   907: aload_1        
        //   908: dup            
        //   909: astore          10
        //   911: monitorenter   
        //   912: aload_0        
        //   913: aload_0        
        //   914: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.codeBlocksProject:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeCodeBlocks;
        //   917: aload_2        
        //   918: aload_1        
        //   919: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.a:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeCodeBlocks;Ljava/util/Set;Ljava/util/Set;)V
        //   922: aload_0        
        //   923: aload_3        
        //   924: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.a:(Ljava/util/Map;)V
        //   927: aload           10
        //   929: monitorexit    
        //   930: goto            941
        //   933: astore          13
        //   935: aload           10
        //   937: monitorexit    
        //   938: aload           13
        //   940: athrow         
        //   941: return         
        //    Signature:
        //  (Ljava/util/Set<Ljava/io/File;>;Ljava/util/Set<Ljava/io/File;>;Ljava/util/Map<Ljava/lang/String;Lcom/jetbrains/cidr/lang/OCLanguageKind;>;Z)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  156    169    169    173    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  88     128    128    132    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  44     84     84     88     Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  0      40     40     44     Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  143    189    244    356    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  143    189    356    413    Any
        //  246    267    270    274    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  244    301    356    413    Any
        //  356    358    356    413    Any
        //  511    527    527    531    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  495    516    519    523    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  465    534    537    580    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  611    702    702    706    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  597    620    623    627    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  585    604    607    611    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  580    590    593    597    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  539    573    576    580    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  863    869    872    904    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  912    930    933    941    Any
        //  933    938    933    941    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0597:
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
    private CMakeFile a(@NotNull final File p0, @NotNull final CMakeListener p1) {
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
        //    18: ldc             "generatedDir"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "loadDependenciesFile"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "listener"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "loadDependenciesFile"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: new             Ljava/io/File;
        //    91: dup            
        //    92: aload_1        
        //    93: ldc             "CMakeFiles/Makefile.cmake"
        //    95: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    98: astore_3       
        //    99: aload_3        
        //   100: invokevirtual   java/io/File.exists:()Z
        //   103: ifne            120
        //   106: aload_0        
        //   107: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.hasGenerationError:Z
        //   110: ifne            195
        //   113: goto            120
        //   116: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   119: athrow         
        //   120: aload_0        
        //   121: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.this$0:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;
        //   124: aload_3        
        //   125: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$500:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;Ljava/io/File;)Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeFile;
        //   128: areturn        
        //   129: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   132: athrow         
        //   133: astore          4
        //   135: new             Ljava/lang/StringBuilder;
        //   138: dup            
        //   139: invokespecial   java/lang/StringBuilder.<init>:()V
        //   142: ldc             "Cannot read CMake dependency information from "
        //   144: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   147: aload_3        
        //   148: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   151: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   154: astore          5
        //   156: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   159: aload           5
        //   161: aload           4
        //   163: invokevirtual   com/intellij/openapi/diagnostic/Logger.warn:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   166: aload_2        
        //   167: new             Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage;
        //   170: dup            
        //   171: getstatic       com/jetbrains/cidr/cpp/cmake/model/CMakeMessage$MessageLevel.ERROR:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage$MessageLevel;
        //   174: aload_0        
        //   175: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.this$0:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;
        //   178: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$200:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;)Ljava/io/File;
        //   181: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$300:(Ljava/io/File;)Ljava/io/File;
        //   184: aconst_null    
        //   185: aload           5
        //   187: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeMessage.<init>:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage$MessageLevel;Ljava/io/File;Ljava/lang/Integer;Ljava/lang/String;)V
        //   190: invokeinterface com/jetbrains/cidr/cpp/cmake/model/CMakeListener.message:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage;)V
        //   195: aconst_null    
        //   196: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  99     113    116    120    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  44     84     84     88     Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  0      40     40     44     Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  120    128    133    195    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  106    129    129    133    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0120:
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
    
    private void a(@Nullable final CMakeCodeBlocks cMakeCodeBlocks, @NotNull final Set<File> set, @NotNull final Set<File> set2) {
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resources", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult", "loadConfigResources"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (set2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmakeFiles", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult", "loadConfigResources"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        if (cMakeCodeBlocks != null) {
            for (final Map.Entry<String, String> entry : cMakeCodeBlocks.getFilePathsWithFolders().entrySet()) {
                final File access$600 = CMakeGenerator.access$600(this.parameters.environment, FileUtil.toCanonicalPath((String)entry.getKey(), true));
                try {
                    if (entry.getValue().startsWith("CMake Files\\")) {
                        set2.add(access$600);
                        continue;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                set.add(access$600);
            }
        }
    }
    
    @Nullable
    private CMakeCodeBlocks a(@NotNull final CMakeCacheFile cMakeCacheFile) {
        try {
            if (cMakeCacheFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cacheFile", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult", "loadCodeBlocksProject"));
            }
        }
        catch (JDOMException ex) {
            throw b((Exception)ex);
        }
        final File file = new File(this.parameters.generationDir, cMakeCacheFile.getProjectNameValueOrDefault() + ".cbp");
        try {
            return CMakeCodeBlocks.load(file);
        }
        catch (JDOMException ex2) {
            final String string = "Cannot read project file " + file;
            CPPLog.LOG.warn(string, (Throwable)ex2);
            this.parameters.listener.message(new CMakeMessage(CMakeMessage.MessageLevel.ERROR, CMakeGenerator.access$300(CMakeGenerator.access$200(CMakeGenerator.this)), null, string));
        }
        catch (IOException ex3) {
            final String string2 = "Cannot read project file " + file;
            CPPLog.LOG.warn(string2, (Throwable)ex3);
            this.parameters.listener.message(new CMakeMessage(CMakeMessage.MessageLevel.ERROR, CMakeGenerator.access$300(CMakeGenerator.access$200(CMakeGenerator.this)), null, string2));
        }
        return null;
    }
    
    private void a(final Map<String, OCLanguageKind> map) {
        final CMakeFile a = this.a(this.parameters.generationDir, this.parameters.listener);
        try {
            if (a == null) {
                return;
            }
        }
        catch (CMakeException ex) {
            throw b(ex);
        }
        final Iterator iterator = CMakeGenerator.access$700(CMakeGenerator.this, a, "CMAKE_MAKEFILE_DEPENDS").iterator();
        while (iterator.hasNext()) {
            final File access$600 = CMakeGenerator.access$600(this.parameters.environment, iterator.next());
            final Matcher matcher = CMakeGenerator.access$800().matcher(FileUtil.toSystemIndependentName(access$600.getPath()));
            try {
                if (!matcher.matches()) {
                    continue;
                }
            }
            catch (CMakeException ex2) {
                throw b(ex2);
            }
            final String group = matcher.group(1);
            final OCLanguageKind a2 = this.a(group);
            try {
                if (a2 == null) {
                    continue;
                }
            }
            catch (CMakeException ex3) {
                throw b(ex3);
            }
            try {
                final Iterator iterator2 = CMakeGenerator.access$700(CMakeGenerator.this, CMakeGenerator.access$500(CMakeGenerator.this, new File(this.parameters.generationDir, access$600.getPath())), "CMAKE_" + group + "_SOURCE_FILE_EXTENSIONS").iterator();
                while (iterator2.hasNext()) {
                    map.put(iterator2.next(), a2);
                }
            }
            catch (CMakeException ex4) {
                CPPLog.LOG.debug((Throwable)ex4);
                this.parameters.listener.message(new CMakeMessage(CMakeMessage.MessageLevel.ERROR, CMakeGenerator.access$300(CMakeGenerator.access$200(CMakeGenerator.this)), null, ex4.getMessage()));
            }
        }
    }
    
    private void a(@NotNull final PerConfigurationTargetInfo p0) throws CMakeException {
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
        //    18: ldc             "targetInfo"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "loadPerConfigurationTargetInfo"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_1        
        //    45: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.generatorType:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGeneratorType;
        //    48: getstatic       com/jetbrains/cidr/cpp/cmake/model/CMakeGeneratorType.MINGW:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGeneratorType;
        //    51: if_acmpne       61
        //    54: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.MINGW_MAKEFILES:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //    57: astore_2       
        //    58: goto            82
        //    61: aload_1        
        //    62: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.generatorType:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGeneratorType;
        //    65: getstatic       com/jetbrains/cidr/cpp/cmake/model/CMakeGeneratorType.NMAKE:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGeneratorType;
        //    68: if_acmpne       78
        //    71: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.NMAKE_MAKEFILES:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //    74: astore_2       
        //    75: goto            82
        //    78: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.GNU_MAKEFILES:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //    81: astore_2       
        //    82: aload_0        
        //    83: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.this$0:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;
        //    86: new             Ljava/io/File;
        //    89: dup            
        //    90: aload_1        
        //    91: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.targetDir:Ljava/io/File;
        //    94: ldc             "DependInfo.cmake"
        //    96: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    99: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$500:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;Ljava/io/File;)Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeFile;
        //   102: astore_3       
        //   103: new             Ljava/util/ArrayList;
        //   106: dup            
        //   107: invokespecial   java/util/ArrayList.<init>:()V
        //   110: astore          4
        //   112: aload_0        
        //   113: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.this$0:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;
        //   116: aload_3        
        //   117: ldc             "CMAKE_DEPENDS_LANGUAGES"
        //   119: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$700:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeFile;Ljava/lang/String;)Ljava/util/List;
        //   122: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   127: astore          5
        //   129: aload           5
        //   131: invokeinterface java/util/Iterator.hasNext:()Z
        //   136: ifeq            189
        //   139: aload           5
        //   141: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   146: checkcast       Ljava/lang/String;
        //   149: astore          6
        //   151: aload_0        
        //   152: aload           6
        //   154: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.a:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   157: astore          7
        //   159: aload           7
        //   161: ifnull          186
        //   164: aload           4
        //   166: aload           6
        //   168: aload           7
        //   170: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   173: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   178: pop            
        //   179: goto            186
        //   182: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   185: athrow         
        //   186: goto            129
        //   189: new             Ljava/io/File;
        //   192: dup            
        //   193: aload_1        
        //   194: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.targetDir:Ljava/io/File;
        //   197: ldc             "flags.make"
        //   199: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   202: astore          5
        //   204: aload           5
        //   206: invokevirtual   java/io/File.exists:()Z
        //   209: ifeq            993
        //   212: new             Lcom/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile;
        //   215: dup            
        //   216: aload           5
        //   218: aload_0        
        //   219: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.this$0:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;
        //   222: aload           5
        //   224: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$400:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;Ljava/io/File;)Ljava/lang/String;
        //   227: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   230: astore          6
        //   232: new             Lgnu/trove/THashMap;
        //   235: dup            
        //   236: aload           4
        //   238: invokeinterface java/util/List.size:()I
        //   243: invokespecial   gnu/trove/THashMap.<init>:(I)V
        //   246: astore          7
        //   248: aload           6
        //   250: ldc             "_AD_HOC_TARGET_FLAGS_"
        //   252: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile.getVariable:(Ljava/lang/String;)Ljava/lang/String;
        //   255: astore          8
        //   257: aload           4
        //   259: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   264: astore          9
        //   266: aload           9
        //   268: invokeinterface java/util/Iterator.hasNext:()Z
        //   273: ifeq            624
        //   276: aload           9
        //   278: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   283: checkcast       Lcom/intellij/openapi/util/Pair;
        //   286: astore          10
        //   288: aload           10
        //   290: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   293: checkcast       Ljava/lang/String;
        //   296: astore          11
        //   298: aload           6
        //   300: new             Ljava/lang/StringBuilder;
        //   303: dup            
        //   304: invokespecial   java/lang/StringBuilder.<init>:()V
        //   307: aload           11
        //   309: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   312: ldc             "_FLAGS"
        //   314: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   317: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   320: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile.getVariable:(Ljava/lang/String;)Ljava/lang/String;
        //   323: invokestatic    com/intellij/openapi/util/text/StringUtil.notNullize:(Ljava/lang/String;)Ljava/lang/String;
        //   326: astore          12
        //   328: aload           6
        //   330: new             Ljava/lang/StringBuilder;
        //   333: dup            
        //   334: invokespecial   java/lang/StringBuilder.<init>:()V
        //   337: aload           11
        //   339: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   342: ldc             "_DEFINES"
        //   344: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   347: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   350: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile.getVariable:(Ljava/lang/String;)Ljava/lang/String;
        //   353: invokestatic    com/intellij/openapi/util/text/StringUtil.notNullize:(Ljava/lang/String;)Ljava/lang/String;
        //   356: astore          13
        //   358: aload           6
        //   360: new             Ljava/lang/StringBuilder;
        //   363: dup            
        //   364: invokespecial   java/lang/StringBuilder.<init>:()V
        //   367: aload           11
        //   369: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   372: ldc             "_INCLUDES"
        //   374: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   377: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   380: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile.getVariable:(Ljava/lang/String;)Ljava/lang/String;
        //   383: invokestatic    com/intellij/openapi/util/text/StringUtil.notNullize:(Ljava/lang/String;)Ljava/lang/String;
        //   386: astore          14
        //   388: aload           14
        //   390: invokestatic    com/intellij/openapi/util/text/StringUtil.isEmptyOrSpaces:(Ljava/lang/String;)Z
        //   393: ifne            423
        //   396: new             Ljava/lang/StringBuilder;
        //   399: dup            
        //   400: invokespecial   java/lang/StringBuilder.<init>:()V
        //   403: aload           14
        //   405: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   408: ldc             " "
        //   410: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   413: aload           12
        //   415: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   418: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   421: astore          12
        //   423: aload           8
        //   425: invokestatic    com/intellij/openapi/util/text/StringUtil.isEmptyOrSpaces:(Ljava/lang/String;)Z
        //   428: ifne            458
        //   431: new             Ljava/lang/StringBuilder;
        //   434: dup            
        //   435: invokespecial   java/lang/StringBuilder.<init>:()V
        //   438: aload           12
        //   440: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   443: ldc             "   "
        //   445: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   448: aload           8
        //   450: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   453: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   456: astore          12
        //   458: aload_0        
        //   459: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.this$0:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;
        //   462: aload_3        
        //   463: new             Ljava/lang/StringBuilder;
        //   466: dup            
        //   467: invokespecial   java/lang/StringBuilder.<init>:()V
        //   470: ldc             "CMAKE_"
        //   472: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   475: aload           11
        //   477: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   480: ldc             "_COMPILER_ID"
        //   482: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   485: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   488: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$700:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeFile;Ljava/lang/String;)Ljava/util/List;
        //   491: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/List;)Ljava/lang/Object;
        //   494: checkcast       Ljava/lang/String;
        //   497: astore          15
        //   499: new             Lcom/jetbrains/cidr/cpp/cmake/model/CMakeCompiler;
        //   502: dup            
        //   503: aload           15
        //   505: invokestatic    com/intellij/openapi/util/text/StringUtil.notNullize:(Ljava/lang/String;)Ljava/lang/String;
        //   508: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeCompiler.<init>:(Ljava/lang/String;)V
        //   511: astore          16
        //   513: aload_0        
        //   514: aload           6
        //   516: new             Ljava/lang/StringBuilder;
        //   519: dup            
        //   520: invokespecial   java/lang/StringBuilder.<init>:()V
        //   523: aload           11
        //   525: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   528: ldc             "_COMPILER"
        //   530: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   533: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   536: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile.getVariable:(Ljava/lang/String;)Ljava/lang/String;
        //   539: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.c:(Ljava/lang/String;)Ljava/io/File;
        //   542: astore          17
        //   544: aload           17
        //   546: ifnonnull       587
        //   549: aload_0        
        //   550: aload_1        
        //   551: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.cacheFile:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile;
        //   554: new             Ljava/lang/StringBuilder;
        //   557: dup            
        //   558: invokespecial   java/lang/StringBuilder.<init>:()V
        //   561: ldc             "CMAKE_"
        //   563: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   566: aload           11
        //   568: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   571: ldc             "_COMPILER"
        //   573: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   576: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   579: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile.getVariableValue:(Ljava/lang/String;)Ljava/lang/String;
        //   582: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.c:(Ljava/lang/String;)Ljava/io/File;
        //   585: astore          17
        //   587: aload           7
        //   589: aload           10
        //   591: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   594: new             Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfigurationSettings;
        //   597: dup            
        //   598: aload           16
        //   600: aload           17
        //   602: aload           12
        //   604: aload_2        
        //   605: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.parseArgs:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/util/List;
        //   608: aload           13
        //   610: aload_2        
        //   611: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.parseArgs:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/util/List;
        //   614: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeConfigurationSettings.<init>:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeCompiler;Ljava/io/File;Ljava/util/List;Ljava/util/List;)V
        //   617: invokevirtual   gnu/trove/THashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   620: pop            
        //   621: goto            266
        //   624: aload           7
        //   626: invokevirtual   gnu/trove/THashMap.compact:()V
        //   629: aload_1        
        //   630: aload           7
        //   632: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.perLanguageSettings:Ljava/util/Map;
        //   635: aconst_null    
        //   636: astore          9
        //   638: aload           4
        //   640: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   645: astore          10
        //   647: aload           10
        //   649: invokeinterface java/util/Iterator.hasNext:()Z
        //   654: ifeq            975
        //   657: aload           10
        //   659: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   664: checkcast       Lcom/intellij/openapi/util/Pair;
        //   667: astore          11
        //   669: aload           11
        //   671: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   674: checkcast       Ljava/lang/String;
        //   677: astore          12
        //   679: aload_0        
        //   680: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.this$0:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;
        //   683: aload_3        
        //   684: new             Ljava/lang/StringBuilder;
        //   687: dup            
        //   688: invokespecial   java/lang/StringBuilder.<init>:()V
        //   691: ldc             "CMAKE_DEPENDS_CHECK_"
        //   693: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   696: aload           12
        //   698: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   701: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   704: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$700:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeFile;Ljava/lang/String;)Ljava/util/List;
        //   707: astore          13
        //   709: aload           13
        //   711: invokeinterface java/util/List.isEmpty:()Z
        //   716: ifeq            726
        //   719: goto            647
        //   722: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   725: athrow         
        //   726: aload           13
        //   728: invokeinterface java/util/List.size:()I
        //   733: istore          14
        //   735: aload           9
        //   737: ifnonnull       759
        //   740: new             Lgnu/trove/THashMap;
        //   743: dup            
        //   744: iload           14
        //   746: iconst_2       
        //   747: idiv           
        //   748: getstatic       com/intellij/openapi/util/io/FileUtil.FILE_HASHING_STRATEGY:Lgnu/trove/TObjectHashingStrategy;
        //   751: invokespecial   gnu/trove/THashMap.<init>:(ILgnu/trove/TObjectHashingStrategy;)V
        //   754: astore          9
        //   756: goto            774
        //   759: aload           9
        //   761: aload           9
        //   763: invokevirtual   gnu/trove/THashMap.size:()I
        //   766: iload           14
        //   768: iconst_2       
        //   769: idiv           
        //   770: iadd           
        //   771: invokevirtual   gnu/trove/THashMap.ensureCapacity:(I)V
        //   774: iconst_0       
        //   775: istore          15
        //   777: iload           15
        //   779: iload           14
        //   781: iconst_1       
        //   782: isub           
        //   783: if_icmpge       972
        //   786: aload_1        
        //   787: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.environment:Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;
        //   790: aload           13
        //   792: iload           15
        //   794: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   799: checkcast       Ljava/lang/String;
        //   802: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$600:(Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;Ljava/lang/String;)Ljava/io/File;
        //   805: astore          16
        //   807: aload_1        
        //   808: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.environment:Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;
        //   811: aload           13
        //   813: iload           15
        //   815: iconst_1       
        //   816: iadd           
        //   817: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   822: checkcast       Ljava/lang/String;
        //   825: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$600:(Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;Ljava/lang/String;)Ljava/io/File;
        //   828: astore          17
        //   830: aconst_null    
        //   831: astore          18
        //   833: aconst_null    
        //   834: astore          19
        //   836: aload_0        
        //   837: aload_1        
        //   838: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.generatedDir:Ljava/io/File;
        //   841: aload           17
        //   843: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.a:(Ljava/io/File;Ljava/io/File;)Ljava/lang/String;
        //   846: astore          20
        //   848: aload           20
        //   850: ifnull          933
        //   853: aload           20
        //   855: invokestatic    com/intellij/openapi/util/io/FileUtil.toSystemIndependentName:(Ljava/lang/String;)Ljava/lang/String;
        //   858: astore          21
        //   860: aload           6
        //   862: new             Ljava/lang/StringBuilder;
        //   865: dup            
        //   866: invokespecial   java/lang/StringBuilder.<init>:()V
        //   869: aload           21
        //   871: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   874: ldc             "_FLAGS"
        //   876: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   879: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   882: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile.getVariable:(Ljava/lang/String;)Ljava/lang/String;
        //   885: astore          22
        //   887: aload           6
        //   889: new             Ljava/lang/StringBuilder;
        //   892: dup            
        //   893: invokespecial   java/lang/StringBuilder.<init>:()V
        //   896: aload           21
        //   898: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   901: ldc             "_DEFINES"
        //   903: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   906: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   909: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeFlagsFile.getVariable:(Ljava/lang/String;)Ljava/lang/String;
        //   912: astore          23
        //   914: aload           22
        //   916: invokestatic    com/intellij/openapi/util/text/StringUtil.notNullize:(Ljava/lang/String;)Ljava/lang/String;
        //   919: aload_2        
        //   920: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.parseArgs:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/util/List;
        //   923: astore          18
        //   925: aload_0        
        //   926: aload           23
        //   928: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/String;)Ljava/util/List;
        //   931: astore          19
        //   933: aload           9
        //   935: aload           16
        //   937: new             Lcom/jetbrains/cidr/cpp/cmake/model/CMakeFileSettings;
        //   940: dup            
        //   941: aload           11
        //   943: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   946: checkcast       Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   949: aload           18
        //   951: invokestatic    com/intellij/util/containers/ContainerUtil.notNullize:(Ljava/util/List;)Ljava/util/List;
        //   954: aload           19
        //   956: invokestatic    com/intellij/util/containers/ContainerUtil.notNullize:(Ljava/util/List;)Ljava/util/List;
        //   959: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeFileSettings.<init>:(Lcom/jetbrains/cidr/lang/OCLanguageKind;Ljava/util/List;Ljava/util/List;)V
        //   962: invokevirtual   gnu/trove/THashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   965: pop            
        //   966: iinc            15, 2
        //   969: goto            777
        //   972: goto            647
        //   975: aload           9
        //   977: ifnull          993
        //   980: aload_1        
        //   981: aload           9
        //   983: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.sources:Ljava/util/Map;
        //   986: goto            993
        //   989: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   992: athrow         
        //   993: aload_0        
        //   994: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.hasGenerationError:Z
        //   997: ifeq            1194
        //  1000: aload_1        
        //  1001: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.sources:Ljava/util/Map;
        //  1004: invokeinterface java/util/Map.isEmpty:()Z
        //  1009: ifeq            1194
        //  1012: goto            1019
        //  1015: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1018: athrow         
        //  1019: aload_1        
        //  1020: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.perLanguageSettings:Ljava/util/Map;
        //  1023: invokeinterface java/util/Map.isEmpty:()Z
        //  1028: ifeq            1194
        //  1031: goto            1038
        //  1034: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1037: athrow         
        //  1038: aload_0        
        //  1039: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.previousModel:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeModel;
        //  1042: ifnull          1194
        //  1045: goto            1052
        //  1048: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1051: athrow         
        //  1052: aload_0        
        //  1053: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.previousModel:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeModel;
        //  1056: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeModel.getTargets:()Ljava/util/List;
        //  1059: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  1064: astore          6
        //  1066: aload           6
        //  1068: invokeinterface java/util/Iterator.hasNext:()Z
        //  1073: ifeq            1191
        //  1076: aload           6
        //  1078: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1083: checkcast       Lcom/jetbrains/cidr/cpp/cmake/model/CMakeTarget;
        //  1086: astore          7
        //  1088: aload           7
        //  1090: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeTarget.getBuildConfigurations:()Ljava/util/List;
        //  1093: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  1098: astore          8
        //  1100: aload           8
        //  1102: invokeinterface java/util/Iterator.hasNext:()Z
        //  1107: ifeq            1188
        //  1110: aload           8
        //  1112: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1117: checkcast       Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration;
        //  1120: astore          9
        //  1122: aload           9
        //  1124: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration.getConfigurationAndTargetGenerationDir:()Ljava/io/File;
        //  1127: aload_1        
        //  1128: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.targetDir:Ljava/io/File;
        //  1131: invokestatic    com/intellij/openapi/util/io/FileUtil.filesEqual:(Ljava/io/File;Ljava/io/File;)Z
        //  1134: ifeq            1185
        //  1137: aload_1        
        //  1138: new             Lgnu/trove/THashMap;
        //  1141: dup            
        //  1142: aload           9
        //  1144: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration.getSourcesMap:()Ljava/util/Map;
        //  1147: invokespecial   gnu/trove/THashMap.<init>:(Ljava/util/Map;)V
        //  1150: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.sources:Ljava/util/Map;
        //  1153: aload_1        
        //  1154: new             Lgnu/trove/THashMap;
        //  1157: dup            
        //  1158: aload           9
        //  1160: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration.getPerLanguageSettingsMap:()Ljava/util/Map;
        //  1163: invokespecial   gnu/trove/THashMap.<init>:(Ljava/util/Map;)V
        //  1166: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.perLanguageSettings:Ljava/util/Map;
        //  1169: aload_1        
        //  1170: aload           9
        //  1172: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration.getProductFile:()Ljava/io/File;
        //  1175: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.productFile:Ljava/io/File;
        //  1178: goto            1185
        //  1181: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1184: athrow         
        //  1185: goto            1100
        //  1188: goto            1066
        //  1191: goto            1199
        //  1194: aload_0        
        //  1195: aload_1        
        //  1196: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo;)V
        //  1199: return         
        //    Exceptions:
        //  throws com.jetbrains.cidr.cpp.cmake.CMakeException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  0      40     40     44     Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  159    179    182    186    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  709    722    722    726    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  975    986    989    993    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  993    1012   1015   1019   Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  1000   1031   1034   1038   Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  1019   1045   1048   1052   Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //  1122   1178   1181   1185   Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_1019:
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
    private OCLanguageKind a(@Nullable final String s) {
        try {
            if (s == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        int n = -1;
        Label_0076: {
            Label_0065: {
                try {
                    switch (s.hashCode()) {
                        case 67: {
                            if (s.equals("C")) {
                                break;
                            }
                            break Label_0076;
                        }
                        case 67203: {
                            break Label_0065;
                        }
                        default: {
                            break Label_0076;
                        }
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                n = 0;
                break Label_0076;
            }
            if (s.equals("CXX")) {
                n = 1;
            }
            try {
                switch (n) {
                    case 0: {
                        return OCLanguageKind.C;
                    }
                    case 1: {
                        break;
                    }
                    default: {
                        return null;
                    }
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return OCLanguageKind.CPP;
    }
    
    @Nullable
    private File c(@Nullable final String s) {
        final String nullize = StringUtil.nullize(s, true);
        try {
            if (nullize != null) {
                return CMakeGenerator.access$600(this.parameters.environment, nullize);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Contract("null -> null")
    private List<String> b(@Nullable final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       10
        //     4: aconst_null    
        //     5: areturn        
        //     6: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //     9: athrow         
        //    10: new             Ljava/util/ArrayList;
        //    13: dup            
        //    14: invokespecial   java/util/ArrayList.<init>:()V
        //    17: astore_2       
        //    18: new             Ljava/lang/StringBuilder;
        //    21: dup            
        //    22: invokespecial   java/lang/StringBuilder.<init>:()V
        //    25: astore_3       
        //    26: aload_3        
        //    27: aload_2        
        //    28: invokedynamic   run:(Ljava/lang/StringBuilder;Ljava/util/List;)Ljava/lang/Runnable;
        //    33: astore          4
        //    35: aload_1        
        //    36: invokevirtual   java/lang/String.length:()I
        //    39: istore          5
        //    41: iconst_0       
        //    42: istore          6
        //    44: iload           6
        //    46: iload           5
        //    48: if_icmpge       158
        //    51: aload_1        
        //    52: iload           6
        //    54: invokevirtual   java/lang/String.charAt:(I)C
        //    57: istore          7
        //    59: iload           7
        //    61: bipush          59
        //    63: if_icmpne       85
        //    66: aload           4
        //    68: invokeinterface java/lang/Runnable.run:()V
        //    73: aload_3        
        //    74: iconst_0       
        //    75: invokevirtual   java/lang/StringBuilder.setLength:(I)V
        //    78: goto            152
        //    81: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    84: athrow         
        //    85: iload           7
        //    87: bipush          92
        //    89: if_icmpne       145
        //    92: iload           6
        //    94: iload           5
        //    96: iconst_1       
        //    97: isub           
        //    98: if_icmpge       145
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   107: athrow         
        //   108: aload_1        
        //   109: iload           6
        //   111: iconst_1       
        //   112: iadd           
        //   113: invokevirtual   java/lang/String.charAt:(I)C
        //   116: bipush          59
        //   118: if_icmpne       145
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   127: athrow         
        //   128: aload_3        
        //   129: bipush          59
        //   131: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   134: pop            
        //   135: iinc            6, 1
        //   138: goto            152
        //   141: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   144: athrow         
        //   145: aload_3        
        //   146: iload           7
        //   148: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   151: pop            
        //   152: iinc            6, 1
        //   155: goto            44
        //   158: aload           4
        //   160: invokeinterface java/lang/Runnable.run:()V
        //   165: aload_2        
        //   166: invokestatic    java/util/Collections.sort:(Ljava/util/List;)V
        //   169: aload_2        
        //   170: areturn        
        //    Signature:
        //  (Ljava/lang/String;)Ljava/util/List<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      6      6      10     Ljava/lang/IllegalArgumentException;
        //  59     81     81     85     Ljava/lang/IllegalArgumentException;
        //  85     101    104    108    Ljava/lang/IllegalArgumentException;
        //  92     121    124    128    Ljava/lang/IllegalArgumentException;
        //  108    141    141    145    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0108:
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
    
    private void b(final PerConfigurationTargetInfo p0) throws CMakeException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/io/File;
        //     3: dup            
        //     4: aload_1        
        //     5: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.targetDir:Ljava/io/File;
        //     8: ldc             "build.make"
        //    10: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    13: astore_2       
        //    14: aload_0        
        //    15: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.this$0:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;
        //    18: aload_2        
        //    19: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$400:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;Ljava/io/File;)Ljava/lang/String;
        //    22: astore_3       
        //    23: new             Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile;
        //    26: dup            
        //    27: aload_2        
        //    28: aload_3        
        //    29: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    32: astore          4
        //    34: aconst_null    
        //    35: astore          5
        //    37: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$900:()Ljava/util/List;
        //    40: ifnull          80
        //    43: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$900:()Ljava/util/List;
        //    46: aload_1        
        //    47: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.targetName:Ljava/lang/String;
        //    50: aload_1        
        //    51: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.configName:Ljava/lang/String;
        //    54: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //    57: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //    62: ifeq            80
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    71: athrow         
        //    72: iconst_1       
        //    73: goto            81
        //    76: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    79: athrow         
        //    80: iconst_0       
        //    81: istore          6
        //    83: aload_1        
        //    84: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.targetDir:Ljava/io/File;
        //    87: invokevirtual   java/io/File.getCanonicalFile:()Ljava/io/File;
        //    90: astore          10
        //    92: aload_1        
        //    93: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.targetDir:Ljava/io/File;
        //    96: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //    99: astore          8
        //   101: aload           10
        //   103: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //   106: astore          9
        //   108: aload_0        
        //   109: aload_1        
        //   110: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.generatedDir:Ljava/io/File;
        //   113: invokevirtual   java/io/File.getCanonicalFile:()Ljava/io/File;
        //   116: aload           10
        //   118: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.a:(Ljava/io/File;Ljava/io/File;)Ljava/lang/String;
        //   121: astore          7
        //   123: goto            138
        //   126: astore          10
        //   128: new             Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        //   131: dup            
        //   132: aload           10
        //   134: invokespecial   com/jetbrains/cidr/cpp/cmake/CMakeException.<init>:(Ljava/lang/Throwable;)V
        //   137: athrow         
        //   138: aload_1        
        //   139: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.generatorType:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGeneratorType;
        //   142: getstatic       com/jetbrains/cidr/cpp/cmake/model/CMakeGeneratorType.NMAKE:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGeneratorType;
        //   145: if_acmpne       156
        //   148: iconst_1       
        //   149: goto            157
        //   152: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   155: athrow         
        //   156: iconst_0       
        //   157: istore          10
        //   159: iload           10
        //   161: invokedynamic   apply:(Z)Ljava/util/function/Function;
        //   166: astore          11
        //   168: iload           10
        //   170: invokedynamic   apply:(Z)Ljava/util/function/Function;
        //   175: astore          12
        //   177: iload           10
        //   179: invokedynamic   apply:(Z)Ljava/util/function/Function;
        //   184: astore          13
        //   186: new             Ljava/util/LinkedHashSet;
        //   189: dup            
        //   190: iconst_5       
        //   191: anewarray       Ljava/lang/String;
        //   194: dup            
        //   195: iconst_0       
        //   196: aload           11
        //   198: aload           13
        //   200: new             Ljava/lang/StringBuilder;
        //   203: dup            
        //   204: invokespecial   java/lang/StringBuilder.<init>:()V
        //   207: aload           7
        //   209: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   212: ldc             "/build"
        //   214: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   217: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   220: invokeinterface java/util/function/Function.apply:(Ljava/lang/Object;)Ljava/lang/Object;
        //   225: invokeinterface java/util/function/Function.apply:(Ljava/lang/Object;)Ljava/lang/Object;
        //   230: checkcast       Ljava/lang/String;
        //   233: aastore        
        //   234: dup            
        //   235: iconst_1       
        //   236: aload           11
        //   238: aload           13
        //   240: new             Ljava/lang/StringBuilder;
        //   243: dup            
        //   244: invokespecial   java/lang/StringBuilder.<init>:()V
        //   247: aload           8
        //   249: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   252: ldc             "/build"
        //   254: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   257: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   260: invokeinterface java/util/function/Function.apply:(Ljava/lang/Object;)Ljava/lang/Object;
        //   265: invokeinterface java/util/function/Function.apply:(Ljava/lang/Object;)Ljava/lang/Object;
        //   270: checkcast       Ljava/lang/String;
        //   273: aastore        
        //   274: dup            
        //   275: iconst_2       
        //   276: aload           11
        //   278: aload           13
        //   280: new             Ljava/lang/StringBuilder;
        //   283: dup            
        //   284: invokespecial   java/lang/StringBuilder.<init>:()V
        //   287: aload           9
        //   289: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   292: ldc             "/build"
        //   294: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   297: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   300: invokeinterface java/util/function/Function.apply:(Ljava/lang/Object;)Ljava/lang/Object;
        //   305: invokeinterface java/util/function/Function.apply:(Ljava/lang/Object;)Ljava/lang/Object;
        //   310: checkcast       Ljava/lang/String;
        //   313: aastore        
        //   314: dup            
        //   315: iconst_3       
        //   316: aload           11
        //   318: aload_1        
        //   319: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.environment:Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;
        //   322: new             Ljava/lang/StringBuilder;
        //   325: dup            
        //   326: invokespecial   java/lang/StringBuilder.<init>:()V
        //   329: aload           8
        //   331: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   334: ldc             "/build"
        //   336: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   339: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   342: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$1000:(Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;Ljava/lang/String;)Ljava/lang/String;
        //   345: invokeinterface java/util/function/Function.apply:(Ljava/lang/Object;)Ljava/lang/Object;
        //   350: checkcast       Ljava/lang/String;
        //   353: aastore        
        //   354: dup            
        //   355: iconst_4       
        //   356: aload           11
        //   358: aload_1        
        //   359: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.environment:Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;
        //   362: new             Ljava/lang/StringBuilder;
        //   365: dup            
        //   366: invokespecial   java/lang/StringBuilder.<init>:()V
        //   369: aload           9
        //   371: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   374: ldc             "/build"
        //   376: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   379: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   382: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$1000:(Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;Ljava/lang/String;)Ljava/lang/String;
        //   385: invokeinterface java/util/function/Function.apply:(Ljava/lang/Object;)Ljava/lang/Object;
        //   390: checkcast       Ljava/lang/String;
        //   393: aastore        
        //   394: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //   397: invokespecial   java/util/LinkedHashSet.<init>:(Ljava/util/Collection;)V
        //   400: astore          14
        //   402: iload           6
        //   404: ifne            460
        //   407: aload           14
        //   409: invokevirtual   java/util/LinkedHashSet.iterator:()Ljava/util/Iterator;
        //   412: astore          15
        //   414: aload           15
        //   416: invokeinterface java/util/Iterator.hasNext:()Z
        //   421: ifeq            460
        //   424: aload           15
        //   426: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   431: checkcast       Ljava/lang/String;
        //   434: astore          16
        //   436: aload           4
        //   438: aload           16
        //   440: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile.getRuleDependency:(Ljava/lang/String;)Ljava/lang/String;
        //   443: astore          5
        //   445: aload           5
        //   447: ifnull          457
        //   450: goto            460
        //   453: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   456: athrow         
        //   457: goto            414
        //   460: aload           5
        //   462: ifnull          499
        //   465: aload           12
        //   467: aload           5
        //   469: invokeinterface java/util/function/Function.apply:(Ljava/lang/Object;)Ljava/lang/Object;
        //   474: checkcast       Ljava/lang/String;
        //   477: astore          5
        //   479: aload_1        
        //   480: aload_1        
        //   481: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.environment:Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;
        //   484: aload_1        
        //   485: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.generatedDir:Ljava/io/File;
        //   488: aload           5
        //   490: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$1100:(Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;Ljava/io/File;Ljava/lang/String;)Ljava/io/File;
        //   493: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.productFile:Ljava/io/File;
        //   496: goto            631
        //   499: getstatic       com/jetbrains/cidr/cpp/CPPLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   502: new             Ljava/lang/StringBuilder;
        //   505: dup            
        //   506: invokespecial   java/lang/StringBuilder.<init>:()V
        //   509: ldc             "Unable to determine product file path:\nGenerated dir: "
        //   511: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   514: aload_1        
        //   515: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.generatedDir:Ljava/io/File;
        //   518: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   521: ldc             "\nTarget dir: "
        //   523: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   526: aload_1        
        //   527: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.targetDir:Ljava/io/File;
        //   530: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   533: ldc             "\nTried keys: \n"
        //   535: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   538: aload           14
        //   540: ldc             "\n"
        //   542: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/util/Collection;Ljava/lang/String;)Ljava/lang/String;
        //   545: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   548: ldc             "\nEncoding: "
        //   550: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   553: aload_3        
        //   554: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   557: ldc             "\nMakefile keys: \n"
        //   559: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   562: aload           4
        //   564: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile.getRules:()Ljava/lang/Iterable;
        //   567: ldc             "\n"
        //   569: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/lang/Iterable;Ljava/lang/String;)Ljava/lang/String;
        //   572: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   575: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   578: invokevirtual   com/intellij/openapi/diagnostic/Logger.debug:(Ljava/lang/String;)V
        //   581: aload_0        
        //   582: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.parameters:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters;
        //   585: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters.listener:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeListener;
        //   588: new             Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage;
        //   591: dup            
        //   592: getstatic       com/jetbrains/cidr/cpp/cmake/model/CMakeMessage$MessageLevel.WARNING:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage$MessageLevel;
        //   595: aload_1        
        //   596: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.cmakeListsFile:Ljava/io/File;
        //   599: aconst_null    
        //   600: ldc             "cmake.unknownTargetProductPath"
        //   602: iconst_2       
        //   603: anewarray       Ljava/lang/Object;
        //   606: dup            
        //   607: iconst_0       
        //   608: aload_1        
        //   609: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.targetName:Ljava/lang/String;
        //   612: aastore        
        //   613: dup            
        //   614: iconst_1       
        //   615: aload_1        
        //   616: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.configName:Ljava/lang/String;
        //   619: aastore        
        //   620: invokestatic    com/jetbrains/cidr/cpp/CPPBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   623: invokespecial   com/jetbrains/cidr/cpp/cmake/model/CMakeMessage.<init>:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage$MessageLevel;Ljava/io/File;Ljava/lang/Integer;Ljava/lang/String;)V
        //   626: invokeinterface com/jetbrains/cidr/cpp/cmake/model/CMakeListener.message:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage;)V
        //   631: aload_1        
        //   632: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.generatorType:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGeneratorType;
        //   635: getstatic       com/jetbrains/cidr/cpp/cmake/model/CMakeGeneratorType.NMAKE:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGeneratorType;
        //   638: if_acmpne       768
        //   641: aload_1        
        //   642: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.productFile:Ljava/io/File;
        //   645: ifnull          843
        //   648: goto            655
        //   651: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   654: athrow         
        //   655: aload_1        
        //   656: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.productFile:Ljava/io/File;
        //   659: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   662: astore          15
        //   664: aload           4
        //   666: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile.getRules:()Ljava/lang/Iterable;
        //   669: aload           15
        //   671: invokedynamic   value:(Ljava/lang/String;)Lcom/intellij/openapi/util/Condition;
        //   676: invokestatic    com/intellij/util/containers/ContainerUtil.find:(Ljava/lang/Iterable;Lcom/intellij/openapi/util/Condition;)Ljava/lang/Object;
        //   679: checkcast       Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Rule;
        //   682: astore          16
        //   684: aload           16
        //   686: ifnull          765
        //   689: aload           16
        //   691: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Rule.getRecipe:()Ljava/lang/String;
        //   694: ldc             "@<<"
        //   696: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //   699: iconst_3       
        //   700: iadd           
        //   701: istore          17
        //   703: aload           16
        //   705: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Rule.getRecipe:()Ljava/lang/String;
        //   708: ldc             "<<"
        //   710: iload           17
        //   712: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;I)I
        //   715: istore          18
        //   717: iload           17
        //   719: iconst_m1      
        //   720: if_icmpeq       765
        //   723: iload           18
        //   725: iconst_m1      
        //   726: if_icmpeq       765
        //   729: goto            736
        //   732: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   735: athrow         
        //   736: aload_1        
        //   737: aload           16
        //   739: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Rule.getRecipe:()Ljava/lang/String;
        //   742: iload           17
        //   744: iload           18
        //   746: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   749: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.MSVC_RESPONSE_FILE:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   752: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.parseArgs:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/util/List;
        //   755: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.linkerFlags:Ljava/util/List;
        //   758: goto            765
        //   761: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   764: athrow         
        //   765: goto            843
        //   768: new             Ljava/io/File;
        //   771: dup            
        //   772: aload_1        
        //   773: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.targetDir:Ljava/io/File;
        //   776: aload_1        
        //   777: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.generatorType:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGeneratorType;
        //   780: getstatic       com/jetbrains/cidr/cpp/cmake/model/CMakeGeneratorType.MINGW:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGeneratorType;
        //   783: if_acmpne       795
        //   786: ldc             "linklibs.rsp"
        //   788: goto            797
        //   791: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   794: athrow         
        //   795: ldc             "link.txt"
        //   797: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   800: astore          15
        //   802: aload           15
        //   804: invokevirtual   java/io/File.exists:()Z
        //   807: ifeq            843
        //   810: aload_1        
        //   811: aload           15
        //   813: aload_0        
        //   814: getfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.this$0:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;
        //   817: aload           15
        //   819: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator.access$400:(Lcom/jetbrains/cidr/cpp/cmake/model/CMakeGenerator;Ljava/io/File;)Ljava/lang/String;
        //   822: invokestatic    com/intellij/openapi/util/io/FileUtilRt.loadFile:(Ljava/io/File;Ljava/lang/String;)Ljava/lang/String;
        //   825: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.GCC_RESPONSE_FILE:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //   828: invokestatic    com/jetbrains/cidr/lang/toolchains/CidrSwitchBuilder.parseArgs:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/util/List;
        //   831: putfield        com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo.linkerFlags:Ljava/util/List;
        //   834: goto            843
        //   837: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   840: athrow         
        //   841: astore          16
        //   843: return         
        //    Exceptions:
        //  throws com.jetbrains.cidr.cpp.cmake.CMakeException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  43     76     76     80     Ljava/io/IOException;
        //  37     65     68     72     Ljava/io/IOException;
        //  83     123    126    138    Ljava/io/IOException;
        //  768    791    791    795    Ljava/io/IOException;
        //  723    758    761    765    Ljava/io/IOException;
        //  717    729    732    736    Ljava/io/IOException;
        //  631    648    651    655    Ljava/io/IOException;
        //  445    453    453    457    Ljava/io/IOException;
        //  138    152    152    156    Ljava/io/IOException;
        //  810    834    841    843    Ljava/io/IOException;
        //  802    837    837    841    Lcom/jetbrains/cidr/cpp/cmake/CMakeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.generateNameForVariable(NameVariables.java:264)
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.assignNamesToVariables(NameVariables.java:198)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:276)
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
    
    @NotNull
    private List<PerConfigurationTargetInfo> b(@NotNull final CMakeCacheFile cMakeCacheFile) {
        try {
            if (cMakeCacheFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cache", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult", "loadTargetInfos"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final Map access$1200 = CMakeGenerator.access$1200();
        this.a(this.parameters.generationDir, this.parameters.listener, (Processor<File>)(file -> {
            try {
                if (cMakeCacheFile == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cache", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult", "lambda$loadTargetInfos$5"));
                }
            }
            catch (IOException ex) {
                throw b(ex);
            }
            File file2 = file.getParentFile();
            while (true) {
                try {
                    if (file2 == null || FileUtil.namesEqual(file2.getName(), "CMakeFiles")) {
                        break;
                    }
                }
                catch (IOException ex2) {
                    throw b(ex2);
                }
                file2 = file2.getParentFile();
            }
            Logger log = null;
            boolean b = false;
            Label_0102: {
                try {
                    log = CPPLog.LOG;
                    if (file2 != null) {
                        b = true;
                        break Label_0102;
                    }
                }
                catch (IOException ex3) {
                    throw b(ex3);
                }
                b = false;
            }
            log.assertTrue(b, (Object)("Unexpected path to targetDir: " + file));
            final File parentFile = file2.getParentFile();
            CPPLog.LOG.assertTrue(new File(parentFile, "Makefile").exists(), (Object)("No Makefile in working dir: " + parentFile));
            File file3 = CMakeGenerator.access$300(CMakeGenerator.access$200(CMakeGenerator.this));
            try {
                for (final String s : FileUtil.loadLines(new File(parentFile, "cmake_install.cmake"))) {
                    if (s.startsWith("# Install script for directory:")) {
                        file3 = CMakeGenerator.access$300(CMakeGenerator.access$600(this.parameters.environment, StringUtil.trimStart(s, "# Install script for directory:").trim()));
                    }
                }
            }
            catch (IOException ex4) {
                CPPLog.LOG.error("Cannot read cmake directory from " + parentFile, (Throwable)ex4);
            }
            final String relativePath = FileUtil.getRelativePath(file2, file);
            Logger log2 = null;
            boolean b2 = false;
            Label_0328: {
                try {
                    log2 = CPPLog.LOG;
                    if (relativePath != null) {
                        b2 = true;
                        break Label_0328;
                    }
                }
                catch (IOException ex5) {
                    throw b(ex5);
                }
                b2 = false;
            }
            log2.assertTrue(b2);
            final File file4 = new File(relativePath);
            final PerConfigurationTargetInfo perConfigurationTargetInfo = new PerConfigurationTargetInfo(this.parameters.environment, file3, this.parameters.generationDir, cMakeCacheFile, cMakeCacheFile.getGeneratorType(), file, FileUtil.toSystemIndependentName(new File(file4.getParentFile(), FileUtil.getNameWithoutExtension(file4)).getPath()), this.actualConfigName, parentFile);
            access$1200.put(perConfigurationTargetInfo.targetName, perConfigurationTargetInfo);
            return true;
        }));
        final ArrayList list = new ArrayList<PerConfigurationTargetInfo>((Collection<? extends PerConfigurationTargetInfo>)access$1200.values());
        ArrayList<Object> list2;
        try {
            list.sort((Comparator<? super Object>)new PerTargetInfoComparator());
            list2 = (ArrayList<Object>)list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult", "loadTargetInfos"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return (List<PerConfigurationTargetInfo>)list2;
    }
    
    private boolean a(@NotNull final File file, @NotNull final CMakeListener cMakeListener, @NotNull final Processor<File> processor) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configGenerationDir", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult", "processTargetDirectories"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (cMakeListener == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "listener", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult", "processTargetDirectories"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$ConfigGenerationResult", "processTargetDirectories"));
            }
        }
        catch (IOException ex3) {
            throw b(ex3);
        }
        final File file2 = new File(file, "CMakeFiles/TargetDirectories.txt");
        try {
            if (!file2.exists()) {
                if (this.hasGenerationError) {
                    return true;
                }
            }
        }
        catch (IOException ex4) {
            throw b(ex4);
        }
        try {
            final Iterator iterator = FileUtil.loadLines(file2, CMakeGenerator.access$400(CMakeGenerator.this, file2)).iterator();
            while (iterator.hasNext()) {
                final File access$600 = CMakeGenerator.access$600(this.parameters.environment, iterator.next());
                try {
                    if (!access$600.exists()) {
                        continue;
                    }
                }
                catch (IOException ex5) {
                    throw b(ex5);
                }
                if (!processor.process((Object)access$600)) {
                    return false;
                }
            }
        }
        catch (IOException ex6) {
            cMakeListener.message(CMakeMessage.fromException(CMakeException.cannotReadFile(file2, ex6), null));
        }
        return true;
    }
    
    @Nullable
    private String a(final File file, final File file2) {
        final String relativePath = FileUtil.getRelativePath(file, file2);
        try {
            if (relativePath == null) {
                CPPLog.LOG.error("Cannot convert to relative path:\n\tbase=" + file + "\n\tpath=" + file2 + "\n\nIMPORTANT! If you see this problem, we kindly ask you to submit an issue at our tracker\n(https://youtrack.jetbrains.com/issues/CPP) and provide the details, ideally with a sample project.\nIt will help us to track down and fix this problem.\n\n");
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return relativePath;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
