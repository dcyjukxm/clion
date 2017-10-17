// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Pattern;
import com.intellij.util.execution.ParametersListUtil;
import kotlin.text.MatchResult;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.util.SmartList;
import java.util.HashMap;
import java.util.Map;
import com.jetbrains.cidr.lang.OCLanguageStandard;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import java.util.LinkedHashMap;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.TypeCastException;
import java.io.File;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchPath;
import java.util.Iterator;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.Set;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.lang.toolchains.CidrSwitchBuilder;
import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.jetbrains.cidr.lang.toolchains.CidrCompilerSwitches;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.process.ProcessOutput;
import java.util.List;
import com.intellij.util.Function;
import kotlin.collections.CollectionsKt;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 %2\u00020\u0001:\u0002%&B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0002J\u0014\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\tJ \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0017JN\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00072\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\t2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\t2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\tJ\u001e\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u001eH\u0002J\u001c\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0002J\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001a0\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\tJ\n\u0010!\u001a\u0004\u0018\u00010\u0007H\u0016JD\u0010\"\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010#\u001a\u00020$2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u001e2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0003¨\u0006'" }, d2 = { "Lcom/jetbrains/cidr/toolchains/MSVCCompiler;", "Lcom/jetbrains/cidr/toolchains/CidrCompilerBase;", "executable", "Ljava/io/File;", "workingDir", "(Ljava/io/File;Ljava/io/File;)V", "buildMacrosFileTest", "", "defines", "", "collectHeaderPathsAndDefinesListFromCommandLine", "Lcom/jetbrains/cidr/toolchains/MSVCCompiler$HeaderSearchPathsAndDefines;", "args", "collectInfo", "Lcom/jetbrains/cidr/toolchains/CompilerInfo;", "languageKind", "Lcom/jetbrains/cidr/lang/OCLanguageKind;", "switches", "Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches;", "environment", "Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment;", "output", "headersSearchPaths", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchPath;", "includedHeaders", "includesMapping", "Lcom/jetbrains/cidr/toolchains/PrecompiledInclude;", "warnLog", "filterOptions", "skipOptions", "", "getForceIncludeHeaders", "getIncludeMapping", "readVersion", "runCompiler", "runWithSkippedOptions", "", "Companion", "HeaderSearchPathsAndDefines", "cidr-common" })
public final class MSVCCompiler extends CidrCompilerBase
{
    public static final Companion Companion;
    
    @Nullable
    @Override
    public String readVersion() {
        return this.doReadVersion(CollectionsKt.emptyList(), (Function<ProcessOutput, String>)MSVCCompiler$readVersion.MSVCCompiler$readVersion$1.INSTANCE);
    }
    
    @NotNull
    @Override
    public CompilerInfo collectInfo(@NotNull final OCLanguageKind ocLanguageKind, @NotNull final CidrCompilerSwitches cidrCompilerSwitches, @NotNull final CidrToolEnvironment cidrToolEnvironment) throws ExecutionException {
        CidrSwitchBuilder cidrSwitchBuilder = null;
        String s = null;
        Label_0045: {
            try {
                Intrinsics.checkParameterIsNotNull((Object)ocLanguageKind, "languageKind");
                Intrinsics.checkParameterIsNotNull((Object)cidrCompilerSwitches, "switches");
                Intrinsics.checkParameterIsNotNull((Object)cidrToolEnvironment, "environment");
                cidrSwitchBuilder = new CidrSwitchBuilder();
                if (ocLanguageKind.isCpp()) {
                    s = "/TP";
                    break Label_0045;
                }
            }
            catch (ExecutionException ex) {
                throw c((Exception)ex);
            }
            s = "/TC";
        }
        final CidrCompilerSwitches build = cidrSwitchBuilder.addSingleRaw(s).addAll(cidrCompilerSwitches).addSingleRaw("/Be").addSingleRaw("/Bd").addSingleRaw("/EP").build();
        Intrinsics.checkExpressionValueIsNotNull((Object)build, "finalSwitches");
        return this.a(ocLanguageKind, build, cidrToolEnvironment, false, new LinkedHashSet<String>(), new ArrayList<String>());
    }
    
    private final CompilerInfo a(final OCLanguageKind p0, final CidrCompilerSwitches p1, final CidrToolEnvironment p2, final boolean p3, final Set<String> p4, final List<String> p5) throws ExecutionException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_2        
        //     2: aload           5
        //     4: invokespecial   com/jetbrains/cidr/toolchains/MSVCCompiler.a:(Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches;Ljava/util/Set;)Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches;
        //     7: astore          7
        //     9: aconst_null    
        //    10: checkcast       Ljava/io/File;
        //    13: astore          8
        //    15: aconst_null    
        //    16: checkcast       Ljava/io/File;
        //    19: astore          9
        //    21: nop            
        //    22: ldc             "response-file"
        //    24: ldc             ""
        //    26: iconst_1       
        //    27: invokestatic    com/intellij/openapi/util/io/FileUtil.createTempFile:(Ljava/lang/String;Ljava/lang/String;Z)Ljava/io/File;
        //    30: astore          8
        //    32: aload           7
        //    34: getstatic       com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format.MSVC_RESPONSE_FILE:Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;
        //    37: invokevirtual   com/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches.getCommandLineString:(Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches$Format;)Ljava/lang/String;
        //    40: astore          10
        //    42: aload           8
        //    44: aload           10
        //    46: invokestatic    com/intellij/openapi/util/io/FileUtil.writeToFile:(Ljava/io/File;Ljava/lang/String;)V
        //    49: ldc             "compiler-file"
        //    51: ldc             ""
        //    53: iconst_1       
        //    54: invokestatic    com/intellij/openapi/util/io/FileUtil.createTempFile:(Ljava/lang/String;Ljava/lang/String;Z)Ljava/io/File;
        //    57: astore          9
        //    59: new             Lcom/intellij/execution/configurations/GeneralCommandLine;
        //    62: dup            
        //    63: invokespecial   com/intellij/execution/configurations/GeneralCommandLine.<init>:()V
        //    66: astore          11
        //    68: aload_3        
        //    69: aload           11
        //    71: getstatic       com/jetbrains/cidr/lang/toolchains/CidrToolEnvironment$PrepareFor.BUILD:Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment$PrepareFor;
        //    74: invokevirtual   com/jetbrains/cidr/lang/toolchains/CidrToolEnvironment.prepare:(Lcom/intellij/execution/configurations/GeneralCommandLine;Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment$PrepareFor;)V
        //    77: aload           11
        //    79: aload_0        
        //    80: invokevirtual   com/jetbrains/cidr/toolchains/MSVCCompiler.getExecutablePath:()Ljava/lang/String;
        //    83: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.setExePath:(Ljava/lang/String;)V
        //    86: aload           11
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/toolchains/MSVCCompiler.myWorkingDirectory:Ljava/io/File;
        //    92: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.withWorkDirectory:(Ljava/io/File;)Lcom/intellij/execution/configurations/GeneralCommandLine;
        //    95: pop            
        //    96: aload           11
        //    98: iconst_1       
        //    99: anewarray       Ljava/lang/String;
        //   102: dup            
        //   103: iconst_0       
        //   104: new             Ljava/lang/StringBuilder;
        //   107: dup            
        //   108: invokespecial   java/lang/StringBuilder.<init>:()V
        //   111: ldc             "@"
        //   113: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   116: aload_3        
        //   117: aload           8
        //   119: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   122: invokevirtual   com/jetbrains/cidr/lang/toolchains/CidrToolEnvironment.toEnvPath:(Ljava/lang/String;)Ljava/lang/String;
        //   125: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   128: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   131: aastore        
        //   132: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.addParameters:([Ljava/lang/String;)V
        //   135: aload           11
        //   137: iconst_1       
        //   138: anewarray       Ljava/lang/String;
        //   141: dup            
        //   142: iconst_0       
        //   143: aload_3        
        //   144: aload           9
        //   146: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   149: invokevirtual   com/jetbrains/cidr/lang/toolchains/CidrToolEnvironment.toEnvPath:(Ljava/lang/String;)Ljava/lang/String;
        //   152: aastore        
        //   153: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.addParameters:([Ljava/lang/String;)V
        //   156: new             Ljava/lang/StringBuilder;
        //   159: dup            
        //   160: invokespecial   java/lang/StringBuilder.<init>:()V
        //   163: ldc             ""
        //   165: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   168: aload           11
        //   170: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.getCommandLineString:()Ljava/lang/String;
        //   173: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   176: ldc             " | @"
        //   178: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   181: aload           8
        //   183: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   186: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   189: bipush          61
        //   191: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   194: aload           10
        //   196: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   199: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   202: astore          12
        //   204: getstatic       com/jetbrains/cidr/CidrLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   207: new             Ljava/lang/StringBuilder;
        //   210: dup            
        //   211: invokespecial   java/lang/StringBuilder.<init>:()V
        //   214: ldc             "Running 1st pass compiler: "
        //   216: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   219: aload           12
        //   221: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   224: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   227: invokevirtual   com/intellij/openapi/diagnostic/Logger.debug:(Ljava/lang/String;)V
        //   230: getstatic       com/jetbrains/cidr/toolchains/CidrCompilerBase.outCompilerRunner:Lcom/jetbrains/cidr/toolchains/CidrCompilerBase$CompilerRunner;
        //   233: aload           11
        //   235: invokevirtual   com/jetbrains/cidr/toolchains/CidrCompilerBase$CompilerRunner.run:(Lcom/intellij/execution/configurations/GeneralCommandLine;)Lcom/intellij/execution/process/ProcessOutput;
        //   238: astore          13
        //   240: aload           13
        //   242: invokevirtual   com/intellij/execution/process/ProcessOutput.isTimeout:()Z
        //   245: ifeq            267
        //   248: aload           12
        //   250: invokestatic    com/jetbrains/cidr/toolchains/CidrCompilerBase.throwCompilerTimeout:(Ljava/lang/String;)Lcom/intellij/execution/ExecutionException;
        //   253: dup            
        //   254: ldc             "throwCompilerTimeout(userFriendlyCommandLine)"
        //   256: invokestatic    kotlin/jvm/internal/Intrinsics.checkExpressionValueIsNotNull:(Ljava/lang/Object;Ljava/lang/String;)V
        //   259: checkcast       Ljava/lang/Throwable;
        //   262: athrow         
        //   263: invokestatic    com/jetbrains/cidr/toolchains/MSVCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   266: athrow         
        //   267: aload           13
        //   269: invokevirtual   com/intellij/execution/process/ProcessOutput.getExitCode:()I
        //   272: ifeq            463
        //   275: iload           4
        //   277: ifne            446
        //   280: goto            287
        //   283: invokestatic    com/jetbrains/cidr/toolchains/MSVCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   286: athrow         
        //   287: aload           13
        //   289: invokevirtual   com/intellij/execution/process/ProcessOutput.getStderrLines:()Ljava/util/List;
        //   292: dup            
        //   293: ldc             "output.stderrLines"
        //   295: invokestatic    kotlin/jvm/internal/Intrinsics.checkExpressionValueIsNotNull:(Ljava/lang/Object;Ljava/lang/String;)V
        //   298: aload           5
        //   300: aload           6
        //   302: invokestatic    com/jetbrains/cidr/toolchains/MSVCCompilerKt.collectMSVCSkipOptions:(Ljava/util/List;Ljava/util/Set;Ljava/util/List;)Z
        //   305: ifeq            446
        //   308: goto            315
        //   311: invokestatic    com/jetbrains/cidr/toolchains/MSVCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   314: athrow         
        //   315: getstatic       com/jetbrains/cidr/CidrLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   318: invokevirtual   com/intellij/openapi/diagnostic/Logger.isDebugEnabled:()Z
        //   321: ifeq            383
        //   324: goto            331
        //   327: invokestatic    com/jetbrains/cidr/toolchains/MSVCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   330: athrow         
        //   331: getstatic       com/jetbrains/cidr/CidrLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   334: new             Ljava/lang/StringBuilder;
        //   337: dup            
        //   338: invokespecial   java/lang/StringBuilder.<init>:()V
        //   341: ldc             "Re-running compiler with skipped options: "
        //   343: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   346: aload           5
        //   348: checkcast       Ljava/lang/Iterable;
        //   351: ldc             " "
        //   353: checkcast       Ljava/lang/CharSequence;
        //   356: aconst_null    
        //   357: aconst_null    
        //   358: iconst_0       
        //   359: aconst_null    
        //   360: aconst_null    
        //   361: bipush          62
        //   363: aconst_null    
        //   364: invokestatic    kotlin/collections/CollectionsKt.joinToString$default:(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;
        //   367: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   370: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   373: invokevirtual   com/intellij/openapi/diagnostic/Logger.debug:(Ljava/lang/String;)V
        //   376: goto            383
        //   379: invokestatic    com/jetbrains/cidr/toolchains/MSVCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   382: athrow         
        //   383: aload_0        
        //   384: aload_1        
        //   385: aload           7
        //   387: aload_3        
        //   388: iload           4
        //   390: aload           5
        //   392: aload           6
        //   394: invokespecial   com/jetbrains/cidr/toolchains/MSVCCompiler.a:(Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches;Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment;ZLjava/util/Set;Ljava/util/List;)Lcom/jetbrains/cidr/toolchains/CompilerInfo;
        //   397: astore          14
        //   399: aload           8
        //   401: dup            
        //   402: ifnull          420
        //   405: astore          15
        //   407: aload           15
        //   409: astore          16
        //   411: aload           16
        //   413: invokestatic    com/intellij/openapi/util/io/FileUtil.delete:(Ljava/io/File;)Z
        //   416: pop            
        //   417: goto            421
        //   420: pop            
        //   421: aload           9
        //   423: dup            
        //   424: ifnull          442
        //   427: astore          15
        //   429: aload           15
        //   431: astore          16
        //   433: aload           16
        //   435: invokestatic    com/intellij/openapi/util/io/FileUtil.delete:(Ljava/io/File;)Z
        //   438: pop            
        //   439: goto            443
        //   442: pop            
        //   443: aload           14
        //   445: areturn        
        //   446: aload           13
        //   448: aload           12
        //   450: invokestatic    com/jetbrains/cidr/toolchains/CidrCompilerBase.throwCompilerError:(Lcom/intellij/execution/process/ProcessOutput;Ljava/lang/String;)Lcom/intellij/execution/ExecutionException;
        //   453: dup            
        //   454: ldc             "throwCompilerError(outpu\u2026 userFriendlyCommandLine)"
        //   456: invokestatic    kotlin/jvm/internal/Intrinsics.checkExpressionValueIsNotNull:(Ljava/lang/Object;Ljava/lang/String;)V
        //   459: checkcast       Ljava/lang/Throwable;
        //   462: athrow         
        //   463: aload           13
        //   465: aload           12
        //   467: invokestatic    com/jetbrains/cidr/toolchains/CidrCompilerBase.checkCompilerOutput:(Lcom/intellij/execution/process/ProcessOutput;Ljava/lang/String;)V
        //   470: getstatic       com/jetbrains/cidr/CidrLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   473: invokevirtual   com/intellij/openapi/diagnostic/Logger.isDebugEnabled:()Z
        //   476: ifeq            515
        //   479: getstatic       com/jetbrains/cidr/CidrLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   482: new             Ljava/lang/StringBuilder;
        //   485: dup            
        //   486: invokespecial   java/lang/StringBuilder.<init>:()V
        //   489: ldc             "Compiler 1st output: \n"
        //   491: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   494: aload           13
        //   496: invokevirtual   com/intellij/execution/process/ProcessOutput.getStderr:()Ljava/lang/String;
        //   499: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   502: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   505: invokevirtual   com/intellij/openapi/diagnostic/Logger.debug:(Ljava/lang/String;)V
        //   508: goto            515
        //   511: invokestatic    com/jetbrains/cidr/toolchains/MSVCCompiler.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   514: athrow         
        //   515: getstatic       com/jetbrains/cidr/toolchains/MSVCCompiler.Companion:Lcom/jetbrains/cidr/toolchains/MSVCCompiler$Companion;
        //   518: aload           13
        //   520: invokevirtual   com/intellij/execution/process/ProcessOutput.getStderrLines:()Ljava/util/List;
        //   523: dup            
        //   524: ldc             "output.stderrLines"
        //   526: invokestatic    kotlin/jvm/internal/Intrinsics.checkExpressionValueIsNotNull:(Ljava/lang/Object;Ljava/lang/String;)V
        //   529: invokevirtual   com/jetbrains/cidr/toolchains/MSVCCompiler$Companion.parseArguments:(Ljava/util/List;)Ljava/util/List;
        //   532: astore          14
        //   534: aload_0        
        //   535: aload           14
        //   537: invokevirtual   com/jetbrains/cidr/toolchains/MSVCCompiler.collectHeaderPathsAndDefinesListFromCommandLine:(Ljava/util/List;)Lcom/jetbrains/cidr/toolchains/MSVCCompiler$HeaderSearchPathsAndDefines;
        //   540: astore          17
        //   542: aload           17
        //   544: invokevirtual   com/jetbrains/cidr/toolchains/MSVCCompiler$HeaderSearchPathsAndDefines.component1:()Ljava/util/List;
        //   547: astore          15
        //   549: aload           17
        //   551: invokevirtual   com/jetbrains/cidr/toolchains/MSVCCompiler$HeaderSearchPathsAndDefines.component2:()Ljava/util/List;
        //   554: astore          16
        //   556: aconst_null    
        //   557: astore          17
        //   559: aload           9
        //   561: aload_0        
        //   562: aload           16
        //   564: invokespecial   com/jetbrains/cidr/toolchains/MSVCCompiler.a:(Ljava/util/List;)Ljava/lang/String;
        //   567: invokestatic    com/intellij/openapi/util/io/FileUtil.writeToFile:(Ljava/io/File;Ljava/lang/String;)V
        //   570: getstatic       com/jetbrains/cidr/CidrLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   573: ldc             "Running 2st pass compiler"
        //   575: invokevirtual   com/intellij/openapi/diagnostic/Logger.debug:(Ljava/lang/String;)V
        //   578: getstatic       com/jetbrains/cidr/toolchains/CidrCompilerBase.outCompilerRunner:Lcom/jetbrains/cidr/toolchains/CidrCompilerBase$CompilerRunner;
        //   581: aload           11
        //   583: invokevirtual   com/jetbrains/cidr/toolchains/CidrCompilerBase$CompilerRunner.run:(Lcom/intellij/execution/configurations/GeneralCommandLine;)Lcom/intellij/execution/process/ProcessOutput;
        //   586: astore          13
        //   588: aload           13
        //   590: aload           12
        //   592: invokestatic    com/jetbrains/cidr/toolchains/CidrCompilerBase.checkCompilerOutput:(Lcom/intellij/execution/process/ProcessOutput;Ljava/lang/String;)V
        //   595: aload_0        
        //   596: aload           14
        //   598: invokespecial   com/jetbrains/cidr/toolchains/MSVCCompiler.b:(Ljava/util/List;)Ljava/util/List;
        //   601: astore          17
        //   603: aload_0        
        //   604: aload           14
        //   606: invokevirtual   com/jetbrains/cidr/toolchains/MSVCCompiler.getIncludeMapping:(Ljava/util/List;)Ljava/util/List;
        //   609: astore          18
        //   611: aload_0        
        //   612: aload_1        
        //   613: aload           13
        //   615: invokevirtual   com/intellij/execution/process/ProcessOutput.getStdout:()Ljava/lang/String;
        //   618: dup            
        //   619: ldc             "output.stdout"
        //   621: invokestatic    kotlin/jvm/internal/Intrinsics.checkExpressionValueIsNotNull:(Ljava/lang/Object;Ljava/lang/String;)V
        //   624: aload           15
        //   626: aload           17
        //   628: aload           18
        //   630: aload           6
        //   632: invokevirtual   com/jetbrains/cidr/toolchains/MSVCCompiler.collectInfo:(Lcom/jetbrains/cidr/lang/OCLanguageKind;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)Lcom/jetbrains/cidr/toolchains/CompilerInfo;
        //   635: astore          19
        //   637: aload           8
        //   639: dup            
        //   640: ifnull          658
        //   643: astore          20
        //   645: aload           20
        //   647: astore          21
        //   649: aload           21
        //   651: invokestatic    com/intellij/openapi/util/io/FileUtil.delete:(Ljava/io/File;)Z
        //   654: pop            
        //   655: goto            659
        //   658: pop            
        //   659: aload           9
        //   661: dup            
        //   662: ifnull          680
        //   665: astore          20
        //   667: aload           20
        //   669: astore          21
        //   671: aload           21
        //   673: invokestatic    com/intellij/openapi/util/io/FileUtil.delete:(Ljava/io/File;)Z
        //   676: pop            
        //   677: goto            681
        //   680: pop            
        //   681: aload           19
        //   683: areturn        
        //   684: astore          10
        //   686: new             Lcom/intellij/execution/ExecutionException;
        //   689: dup            
        //   690: aload           10
        //   692: checkcast       Ljava/lang/Throwable;
        //   695: invokespecial   com/intellij/execution/ExecutionException.<init>:(Ljava/lang/Throwable;)V
        //   698: checkcast       Ljava/lang/Throwable;
        //   701: athrow         
        //   702: astore          10
        //   704: aload           8
        //   706: dup            
        //   707: ifnull          725
        //   710: astore          11
        //   712: aload           11
        //   714: astore          12
        //   716: aload           12
        //   718: invokestatic    com/intellij/openapi/util/io/FileUtil.delete:(Ljava/io/File;)Z
        //   721: pop            
        //   722: goto            726
        //   725: pop            
        //   726: aload           9
        //   728: dup            
        //   729: ifnull          747
        //   732: astore          11
        //   734: aload           11
        //   736: astore          12
        //   738: aload           12
        //   740: invokestatic    com/intellij/openapi/util/io/FileUtil.delete:(Ljava/io/File;)Z
        //   743: pop            
        //   744: goto            748
        //   747: pop            
        //   748: aload           10
        //   750: athrow         
        //    Exceptions:
        //  throws com.intellij.execution.ExecutionException
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/jetbrains/cidr/lang/toolchains/CidrCompilerSwitches;Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment;ZLjava/util/Set<Ljava/lang/String;>;Ljava/util/List<Ljava/lang/String;>;)Lcom/jetbrains/cidr/toolchains/CompilerInfo;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  315    376    379    383    Ljava/io/IOException;
        //  287    324    327    331    Ljava/io/IOException;
        //  275    308    311    315    Ljava/io/IOException;
        //  267    280    283    287    Ljava/io/IOException;
        //  240    263    263    267    Ljava/io/IOException;
        //  21     399    684    702    Ljava/io/IOException;
        //  463    508    511    515    Ljava/io/IOException;
        //  446    637    684    702    Ljava/io/IOException;
        //  21     399    702    751    Any
        //  446    637    702    751    Any
        //  684    702    702    751    Any
        //  702    704    702    751    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0287:
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
    
    private final CidrCompilerSwitches a(final CidrCompilerSwitches cidrCompilerSwitches, final Set<String> set) {
        final CidrCompilerSwitches filterOptions = cidrCompilerSwitches.filterOptions((BiFunction<String, String, Boolean>)new MSVCCompiler$filterOptions$argumentsFilter.MSVCCompiler$filterOptions$argumentsFilter$1((Set)set));
        Intrinsics.checkExpressionValueIsNotNull((Object)filterOptions, "switches.filterOptions(argumentsFilter)");
        return filterOptions;
    }
    
    private final String a(final List<String> list) {
        final StringBuilder sb = new StringBuilder();
        sb.append(MSVCCompilerKt.access$getDEFINES_START$p());
        for (final String s : new LinkedHashSet<Object>(CollectionsKt.plus((Collection)MSVCCompilerKt.access$getBUILT_IN_MACROS$p(), (Iterable)list))) {
            sb.append("#if (defined " + s + ")\n" + MSVCCompilerKt.access$getDEFINE_PREFIX$p() + "" + s + ' ' + s + "\n#endif\n");
        }
        sb.append(MSVCCompilerKt.access$getDEFINES_END$p());
        final String string = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull((Object)string, "builder.toString()");
        return string;
    }
    
    @NotNull
    public final HeaderSearchPathsAndDefines collectHeaderPathsAndDefinesListFromCommandLine(@NotNull final List<String> list) {
        Intrinsics.checkParameterIsNotNull((Object)list, "args");
        final ArrayList<String> list2 = new ArrayList<String>();
        final ArrayList<HeadersSearchPath> list3 = new ArrayList<HeadersSearchPath>();
        int equal = 0;
        for (final String s : list) {
            if (equal != 0) {
                final String canonicalPath = FileUtil.toCanonicalPath(s);
                File file = null;
                Label_0104: {
                    try {
                        if (FileUtil.isAbsolute(canonicalPath)) {
                            file = new File(canonicalPath);
                            break Label_0104;
                        }
                    }
                    catch (TypeCastException ex) {
                        throw c((Exception)ex);
                    }
                    file = new File(this.myWorkingDirectory, canonicalPath);
                }
                list3.add(new HeadersSearchPath(file, false, false));
                equal = 0;
            }
            else {
                equal = (Intrinsics.areEqual((Object)s, (Object)"-I") ? 1 : 0);
                if (!StringsKt.startsWith$default(s, "-D", false, 2, (Object)null)) {
                    continue;
                }
                final int indexOfAny$default = StringsKt.indexOfAny$default((CharSequence)s, new char[] { '=', '(', ' ' }, 0, false, 6, (Object)null);
                final ArrayList<String> list4 = list2;
                final String s2 = s;
                final int n = 2;
                int length = 0;
                Label_0211: {
                    try {
                        if (indexOfAny$default > -1) {
                            length = indexOfAny$default;
                            break Label_0211;
                        }
                    }
                    catch (TypeCastException ex2) {
                        throw c((Exception)ex2);
                    }
                    length = s.length();
                }
                final int n2 = length;
                final ArrayList<String> list5 = list4;
                String s3;
                try {
                    s3 = s2;
                    if (s3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                }
                catch (TypeCastException ex3) {
                    throw c((Exception)ex3);
                }
                final String substring = s3.substring(n, n2);
                Intrinsics.checkExpressionValueIsNotNull((Object)substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                final String s4 = substring;
                final ArrayList<String> list6 = list5;
                final String s5 = s4;
                final ArrayList<String> list7 = list6;
                String s6;
                try {
                    s6 = s5;
                    if (s6 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                }
                catch (TypeCastException ex4) {
                    throw c((Exception)ex4);
                }
                list7.add(StringsKt.trim((CharSequence)s6).toString());
            }
        }
        return new HeaderSearchPathsAndDefines(list3, list2);
    }
    
    @NotNull
    public final CompilerInfo collectInfo(@NotNull final OCLanguageKind ocLanguageKind, @NotNull final String s, @NotNull final List<? extends HeadersSearchPath> list, @NotNull final List<? extends File> list2, @NotNull final List<PrecompiledInclude> list3, @NotNull final List<String> list4) {
        Intrinsics.checkParameterIsNotNull((Object)ocLanguageKind, "languageKind");
        Intrinsics.checkParameterIsNotNull((Object)s, "output");
        Intrinsics.checkParameterIsNotNull((Object)list, "headersSearchPaths");
        Intrinsics.checkParameterIsNotNull((Object)list2, "includedHeaders");
        Intrinsics.checkParameterIsNotNull((Object)list3, "includesMapping");
        Intrinsics.checkParameterIsNotNull((Object)list4, "warnLog");
        String replace = "";
        final int indexOf$default = StringsKt.indexOf$default((CharSequence)s, MSVCCompilerKt.access$getDEFINES_START$p(), 0, false, 6, (Object)null);
        final int indexOf$default2 = StringsKt.indexOf$default((CharSequence)s, MSVCCompilerKt.access$getDEFINES_END$p(), 0, false, 6, (Object)null);
        Label_0342: {
            try {
                if (indexOf$default <= -1 || indexOf$default2 <= -1) {
                    break Label_0342;
                }
            }
            catch (TypeCastException ex) {
                throw c((Exception)ex);
            }
            final String substring = s.substring(indexOf$default + MSVCCompilerKt.access$getDEFINES_START$p().length(), indexOf$default2);
            Intrinsics.checkExpressionValueIsNotNull((Object)substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            final String s2 = substring;
            final StringBuilder sb = new StringBuilder();
            int n = 0;
            final String s3 = s2;
            int n2 = 0;
            final String s4 = s3;
            for (int i = 0; i < s4.length(); ++i) {
                final char char1 = s4.charAt(i);
                final int n3 = n2++;
                final char c = char1;
                final int n4 = n3;
                Label_0255: {
                    Label_0252: {
                        Label_0249: {
                            Label_0220: {
                                Label_0213: {
                                    try {
                                        if (c != '\n') {
                                            break Label_0252;
                                        }
                                        final int n5 = n;
                                        if (n5 != 0) {
                                            break Label_0213;
                                        }
                                        break Label_0220;
                                    }
                                    catch (TypeCastException ex2) {
                                        throw c((Exception)ex2);
                                    }
                                    try {
                                        final int n5 = n;
                                        if (n5 != 0) {
                                            continue;
                                        }
                                    }
                                    catch (TypeCastException ex3) {
                                        throw c((Exception)ex3);
                                    }
                                }
                                try {
                                    if (n4 != 0) {
                                        if (s2.charAt(n4 - 1) == '\\') {
                                            break Label_0249;
                                        }
                                    }
                                }
                                catch (TypeCastException ex4) {
                                    throw c((Exception)ex4);
                                }
                            }
                            n = 1;
                        }
                        break Label_0255;
                    }
                    n = 0;
                }
                sb.append(c);
            }
            final String string = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull((Object)string, "builder.toString()");
            replace = new Regex("^" + MSVCCompilerKt.access$getDEFINE_PREFIX$p(), RegexOption.MULTILINE).replace((CharSequence)string, "#define ");
        }
        final LinkedHashMap<OCCompilerFeatures.Feature, OCLanguageStandard> linkedHashMap = new LinkedHashMap<OCCompilerFeatures.Feature, OCLanguageStandard>();
        final int mscVersion = MSVCCompiler.Companion.getMSCVersion(replace);
        Label_0457: {
            Label_0445: {
                Cloneable cloneable = null;
                OCCompilerFeatures.Type<OCLanguageStandard> language_STANDARD = null;
                OCLanguageStandard cpp11 = null;
                Label_0438: {
                    Label_0434: {
                        LinkedHashMap<OCCompilerFeatures.Feature, OCLanguageStandard> linkedHashMap2;
                        OCCompilerFeatures.Type<OCLanguageStandard> type;
                        String msvcLangVersion;
                        try {
                            if (!ocLanguageKind.isCpp()) {
                                break Label_0445;
                            }
                            linkedHashMap2 = (LinkedHashMap<OCCompilerFeatures.Feature, OCLanguageStandard>)(cloneable = linkedHashMap);
                            type = (language_STANDARD = OCCompilerFeatures.LANGUAGE_STANDARD);
                            msvcLangVersion = MSVCCompiler.Companion.getMSVCLangVersion(replace);
                            if (msvcLangVersion == null) {
                                break Label_0434;
                            }
                        }
                        catch (TypeCastException ex5) {
                            throw c((Exception)ex5);
                        }
                        final String s5 = msvcLangVersion;
                        final OCCompilerFeatures.Type<OCLanguageStandard> type2 = type;
                        final LinkedHashMap<OCCompilerFeatures.Feature, OCLanguageStandard> linkedHashMap3 = linkedHashMap2;
                        final OCLanguageStandard cppStandard = OCLanguageStandard.getCppStandard(s5);
                        try {
                            cloneable = linkedHashMap3;
                            language_STANDARD = type2;
                            if ((cpp11 = cppStandard) != null) {
                                break Label_0438;
                            }
                        }
                        catch (TypeCastException ex6) {
                            throw c((Exception)ex6);
                        }
                    }
                    cpp11 = OCLanguageStandard.CPP11;
                }
                ((HashMap<OCCompilerFeatures.Type<OCLanguageStandard>, OCLanguageStandard>)cloneable).put(language_STANDARD, cpp11);
                break Label_0457;
            }
            linkedHashMap.put((OCCompilerFeatures.Feature)OCCompilerFeatures.LANGUAGE_STANDARD, OCLanguageStandard.C11);
            try {
                if (mscVersion < 1900) {
                    linkedHashMap.put(OCCompilerFeatures.Feature.CXX_CONSTEXPR, (OCLanguageStandard)false);
                    linkedHashMap.put(OCCompilerFeatures.Feature.CXX_USER_LITERALS, (OCLanguageStandard)false);
                    linkedHashMap.put(OCCompilerFeatures.Feature.CXX_BINARY_LITERALS, (OCLanguageStandard)false);
                    linkedHashMap.put(OCCompilerFeatures.Feature.CXX_GENERIC_LAMBDAS, (OCLanguageStandard)false);
                    linkedHashMap.put(OCCompilerFeatures.Feature.CXX_RETURN_TYPE_DEDUCTION, (OCLanguageStandard)false);
                }
            }
            catch (TypeCastException ex7) {
                throw c((Exception)ex7);
            }
        }
        try {
            if (mscVersion < 1800) {
                linkedHashMap.put(OCCompilerFeatures.Feature.CXX_NONSTATIC_MEMBER_INIT, (OCLanguageStandard)false);
                linkedHashMap.put(OCCompilerFeatures.Feature.CXX_RAW_STRING_LITERALS, (OCLanguageStandard)false);
            }
        }
        catch (TypeCastException ex8) {
            throw c((Exception)ex8);
        }
        linkedHashMap.put(OCCompilerFeatures.Feature.IS_BASE_OF, (OCLanguageStandard)true);
        final boolean is64Bit = MSVCCompiler.Companion.is64Bit(replace);
        HashMap<OCCompilerFeatures.TypeSize, OCLanguageStandard> hashMap = null;
        OCCompilerFeatures.TypeSize size_T = null;
        short n6 = 0;
        Label_0718: {
            try {
                linkedHashMap.put(OCCompilerFeatures.TypeSize.WCHAR_T, (OCLanguageStandard)(short)2);
                linkedHashMap.put(OCCompilerFeatures.TypeSize.INT, (OCLanguageStandard)(short)4);
                linkedHashMap.put(OCCompilerFeatures.TypeSize.LONG, (OCLanguageStandard)(short)4);
                linkedHashMap.put(OCCompilerFeatures.TypeSize.LONG_LONG, (OCLanguageStandard)(short)8);
                linkedHashMap.put(OCCompilerFeatures.TypeSize.FLOAT, (OCLanguageStandard)(short)4);
                linkedHashMap.put(OCCompilerFeatures.TypeSize.DOUBLE, (OCLanguageStandard)(short)8);
                linkedHashMap.put(OCCompilerFeatures.TypeSize.LONG_DOUBLE, (OCLanguageStandard)(short)8);
                hashMap = (HashMap<OCCompilerFeatures.TypeSize, OCLanguageStandard>)linkedHashMap;
                size_T = OCCompilerFeatures.TypeSize.SIZE_T;
                if (is64Bit) {
                    n6 = 8;
                    break Label_0718;
                }
            }
            catch (TypeCastException ex9) {
                throw c((Exception)ex9);
            }
            n6 = 4;
        }
        hashMap.put(size_T, (OCLanguageStandard)n6);
        return new CompilerInfo(replace, (Map<OCCompilerFeatures.Type<?>, ?>)linkedHashMap, list, list2, list3, list4);
    }
    
    private final List<File> b(final List<String> list) {
        final String s = "-FI";
        final LinkedHashSet<File> set = new LinkedHashSet<File>(1);
        for (int i = 0; i < list.size(); ++i) {
            final String s2 = list.get(i);
            try {
                if (!StringsKt.startsWith$default(s2, s, false, 2, (Object)null) || ++i >= list.size()) {
                    continue;
                }
            }
            catch (TypeCastException ex) {
                throw c((Exception)ex);
            }
            final String s3 = list.get(i);
            File file = null;
            Label_0122: {
                try {
                    if (FileUtil.isWindowsAbsolutePath(s3)) {
                        file = new File(s3);
                        break Label_0122;
                    }
                }
                catch (TypeCastException ex2) {
                    throw c((Exception)ex2);
                }
                file = new File(this.myWorkingDirectory, s3);
            }
            set.add(file);
        }
        return (List<File>)new SmartList((Collection)set);
    }
    
    @NotNull
    public final List<PrecompiledInclude> getIncludeMapping(@NotNull final List<String> list) {
        Intrinsics.checkParameterIsNotNull((Object)list, "args");
        final String s = "-Yc";
        final String s2 = "-Yu";
        final String s3 = "-Fp";
        String s4 = null;
        String s5 = null;
        String s6 = null;
        int i = 0;
        while (i < list.size()) {
            final String s7 = list.get(i);
            try {
                if (++i == list.size()) {
                    break;
                }
            }
            catch (TypeCastException ex) {
                throw c((Exception)ex);
            }
            final String s8 = list.get(i);
            final boolean startsWith$default = StringsKt.startsWith$default((CharSequence)s8, '-', false, 2, (Object)null);
            try {
                if (startsWith$default) {
                    continue;
                }
            }
            catch (TypeCastException ex2) {
                throw c((Exception)ex2);
            }
            final String s9 = s7;
            if (Intrinsics.areEqual((Object)s9, (Object)s3)) {
                s6 = s8;
            }
            else if (Intrinsics.areEqual((Object)s9, (Object)s)) {
                s4 = s8;
            }
            else if (Intrinsics.areEqual((Object)s9, (Object)s2)) {
                s5 = s8;
            }
            ++i;
        }
        final SmartList list2 = new SmartList();
        File file = null;
        Label_0236: {
            Label_0207: {
                try {
                    if (s6 == null) {
                        return (List<PrecompiledInclude>)list2;
                    }
                    final String s10 = s6;
                    final boolean b = FileUtil.isAbsolute(s10);
                    if (b) {
                        break Label_0207;
                    }
                    break Label_0207;
                }
                catch (TypeCastException ex3) {
                    throw c((Exception)ex3);
                }
                try {
                    final String s10 = s6;
                    final boolean b = FileUtil.isAbsolute(s10);
                    if (b) {
                        file = new File(s6);
                        break Label_0236;
                    }
                }
                catch (TypeCastException ex4) {
                    throw c((Exception)ex4);
                }
            }
            file = new File(this.myWorkingDirectory, s6);
        }
        final String canonicalPath = FileUtil.toCanonicalPath(file.getPath());
        try {
            if (s4 != null) {
                final SmartList list3 = list2;
                final String s11 = canonicalPath;
                Intrinsics.checkExpressionValueIsNotNull((Object)s11, "canonicalPCHPath");
                list3.add((Object)new PrecompiledInclude(s11, s4, true));
            }
        }
        catch (TypeCastException ex5) {
            throw c((Exception)ex5);
        }
        try {
            if (s5 != null) {
                final SmartList list4 = list2;
                final String s12 = canonicalPath;
                Intrinsics.checkExpressionValueIsNotNull((Object)s12, "canonicalPCHPath");
                list4.add((Object)new PrecompiledInclude(s12, s5, false));
            }
        }
        catch (TypeCastException ex6) {
            throw c((Exception)ex6);
        }
        return (List<PrecompiledInclude>)list2;
    }
    
    public MSVCCompiler(@NotNull final File file, @NotNull final File file2) {
        Intrinsics.checkParameterIsNotNull((Object)file, "executable");
        Intrinsics.checkParameterIsNotNull((Object)file2, "workingDir");
        super(file, file2);
    }
    
    static {
        Companion = new Companion(null);
    }
    
    @JvmStatic
    public static final int getMSCVersion(@NotNull final CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull((Object)charSequence, "defines");
        return MSVCCompiler.Companion.getMSCVersion(charSequence);
    }
    
    @JvmStatic
    @Nullable
    public static final String getMSVCLangVersion(@NotNull final CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull((Object)charSequence, "defines");
        return MSVCCompiler.Companion.getMSVCLangVersion(charSequence);
    }
    
    @JvmStatic
    public static final boolean is64Bit(@NotNull final CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull((Object)charSequence, "defines");
        return MSVCCompiler.Companion.is64Bit(charSequence);
    }
    
    @JvmStatic
    @NotNull
    public static final List<String> parseArguments(@NotNull final List<String> list) {
        Intrinsics.checkParameterIsNotNull((Object)list, "lines");
        return MSVCCompiler.Companion.parseArguments(list);
    }
    
    @JvmStatic
    private static final List<String> a(final String s) {
        return MSVCCompiler.Companion.a(s);
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\fH\u0007J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\u000f\u001a\u00020\bH\u0003¨\u0006\u0010" }, d2 = { "Lcom/jetbrains/cidr/toolchains/MSVCCompiler$Companion;", "", "()V", "getMSCVersion", "", "defines", "", "getMSVCLangVersion", "", "is64Bit", "", "parseArguments", "", "lines", "splitArguments", "line", "cidr-common" })
    public static final class Companion
    {
        @JvmStatic
        public final int getMSCVersion(@NotNull final CharSequence charSequence) {
            Intrinsics.checkParameterIsNotNull((Object)charSequence, "defines");
            final MatchResult find$default = Regex.find$default(new Regex("#define\\s+_MSC_VER\\s+(\\d+)"), charSequence, 0, 2, (Object)null);
            if (find$default != null) {
                final MatchResult matchResult = find$default;
                try {
                    final Integer value = Integer.valueOf(matchResult.getGroupValues().get(1));
                    if (value != null) {
                        return value;
                    }
                }
                catch (TypeCastException ex) {
                    throw b(ex);
                }
            }
            return 0;
        }
        
        @JvmStatic
        @Nullable
        public final String getMSVCLangVersion(@NotNull final CharSequence charSequence) {
            Intrinsics.checkParameterIsNotNull((Object)charSequence, "defines");
            final MatchResult find$default = Regex.find$default(new Regex("#define\\s+_MSVC_LANG\\s+(\\d+L?)"), charSequence, 0, 2, (Object)null);
            return (find$default != null) ? ((String)find$default.getGroupValues().get(1)) : null;
        }
        
        @JvmStatic
        public final boolean is64Bit(@NotNull final CharSequence charSequence) {
            Intrinsics.checkParameterIsNotNull((Object)charSequence, "defines");
            return Regex.find$default(new Regex("#define\\s+_WIN64\\s+1"), charSequence, 0, 2, (Object)null) != null;
        }
        
        @JvmStatic
        @NotNull
        public final List<String> parseArguments(@NotNull final List<String> list) {
            Intrinsics.checkParameterIsNotNull((Object)list, "lines");
            while (true) {
                for (final String next : list) {
                    final String s = next;
                    Label_0107: {
                        Label_0091: {
                            Label_0082: {
                                try {
                                    if (!StringsKt.startsWith$default(s, "`", false, 2, (Object)null)) {
                                        break Label_0082;
                                    }
                                    final String s2 = s;
                                    final String s3 = "'";
                                    final boolean b = false;
                                    final int n = 2;
                                    final Object o = null;
                                    final boolean b2 = StringsKt.endsWith$default(s2, s3, b, n, o);
                                    if (b2) {
                                        break Label_0082;
                                    }
                                    break Label_0082;
                                }
                                catch (TypeCastException ex) {
                                    throw b(ex);
                                }
                                try {
                                    final String s2 = s;
                                    final String s3 = "'";
                                    final boolean b = false;
                                    final int n = 2;
                                    final Object o = null;
                                    final boolean b2 = StringsKt.endsWith$default(s2, s3, b, n, o);
                                    if (b2) {
                                        final boolean b3 = true;
                                        break Label_0091;
                                    }
                                }
                                catch (TypeCastException ex2) {
                                    throw b(ex2);
                                }
                            }
                            final boolean b3 = false;
                            try {
                                if (b3) {
                                    final String s4 = next;
                                    break Label_0107;
                                }
                                continue;
                            }
                            catch (TypeCastException ex3) {
                                throw b(ex3);
                            }
                        }
                        continue;
                        try {
                            final String s4;
                            final String s5 = s4;
                            if (s5 != null) {
                                return this.a(StringsKt.trimEnd(StringsKt.trimStart(s5, new char[] { '`' }), new char[] { '\'' }));
                            }
                        }
                        catch (TypeCastException ex4) {
                            throw b(ex4);
                        }
                    }
                    return (List<String>)CollectionsKt.emptyList();
                }
                final String s4 = null;
                continue;
            }
        }
        
        @JvmStatic
        private final List<String> a(final String s) {
            final ArrayList<String> list = new ArrayList<String>();
            final List parse = ParametersListUtil.parse(s);
            final StringBuilder sb = new StringBuilder();
            for (final String s2 : parse) {
                if (StringsKt.startsWith$default(s2, "-", false, 2, (Object)null)) {
                    final StringBuilder sb2 = sb;
                    Label_0091: {
                        try {
                            if (sb2.length() > 0) {
                                final boolean b = true;
                                break Label_0091;
                            }
                        }
                        catch (TypeCastException ex) {
                            throw b(ex);
                        }
                        final boolean b = false;
                        try {
                            if (b) {
                                list.add(sb.toString());
                                sb.setLength(0);
                            }
                        }
                        catch (TypeCastException ex2) {
                            throw b(ex2);
                        }
                    }
                    Label_0290: {
                        Label_0150: {
                            try {
                                if (StringsKt.startsWith$default(s2, "-Yc", false, 2, (Object)null)) {
                                    break Label_0150;
                                }
                                final String s3 = s2;
                                final String s4 = "-Yu";
                                final boolean b2 = false;
                                final int n = 2;
                                final Object o = null;
                                final boolean b3 = StringsKt.startsWith$default(s3, s4, b2, n, o);
                                if (!b3) {
                                    break Label_0150;
                                }
                                break Label_0150;
                            }
                            catch (TypeCastException ex3) {
                                throw b(ex3);
                            }
                            try {
                                final String s3 = s2;
                                final String s4 = "-Yu";
                                final boolean b2 = false;
                                final int n = 2;
                                final Object o = null;
                                final boolean b3 = StringsKt.startsWith$default(s3, s4, b2, n, o);
                                if (!b3) {
                                    if (!StringsKt.startsWith$default(s2, "-Fp", false, 2, (Object)null)) {
                                        break Label_0290;
                                    }
                                }
                            }
                            catch (TypeCastException ex4) {
                                throw b(ex4);
                            }
                        }
                        final ArrayList<String> list2 = list;
                        final String s5 = s2;
                        final int n2 = 0;
                        final int n3 = 3;
                        final ArrayList<String> list3 = list2;
                        String s6;
                        try {
                            s6 = s5;
                            if (s6 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                        }
                        catch (TypeCastException ex5) {
                            throw b(ex5);
                        }
                        final String substring = s6.substring(n2, n3);
                        Intrinsics.checkExpressionValueIsNotNull((Object)substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                        list3.add(substring);
                        if (s2.length() <= 3) {
                            continue;
                        }
                        final StringBuilder sb3 = sb;
                        final String s7 = s2;
                        final int n4 = 3;
                        final StringBuilder sb4 = sb3;
                        String s8;
                        try {
                            s8 = s7;
                            if (s8 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                        }
                        catch (TypeCastException ex6) {
                            throw b(ex6);
                        }
                        final String substring2 = s8.substring(n4);
                        Intrinsics.checkExpressionValueIsNotNull((Object)substring2, "(this as java.lang.String).substring(startIndex)");
                        sb4.append(substring2);
                        continue;
                    }
                    list.add(s2);
                }
                else {
                    final StringBuilder sb5 = sb;
                    Label_0326: {
                        try {
                            if (sb5.length() > 0) {
                                final boolean b4 = true;
                                break Label_0326;
                            }
                        }
                        catch (TypeCastException ex7) {
                            throw b(ex7);
                        }
                        final boolean b4 = false;
                        try {
                            if (b4) {
                                sb.append(" ");
                            }
                        }
                        catch (TypeCastException ex8) {
                            throw b(ex8);
                        }
                    }
                    sb.append(s2);
                }
            }
            final StringBuilder sb6 = sb;
            Label_0381: {
                try {
                    if (sb6.length() > 0) {
                        final boolean b5 = true;
                        break Label_0381;
                    }
                }
                catch (TypeCastException ex9) {
                    throw b(ex9);
                }
                final boolean b5 = false;
                try {
                    if (b5) {
                        list.add(sb.toString());
                    }
                }
                catch (TypeCastException ex10) {
                    throw b(ex10);
                }
            }
            return list;
        }
        
        private static TypeCastException b(final TypeCastException ex) {
            return ex;
        }
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J)\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0006H\u00d6\u0001R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0014" }, d2 = { "Lcom/jetbrains/cidr/toolchains/MSVCCompiler$HeaderSearchPathsAndDefines;", "", "headersSearchPaths", "", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchPath;", "defines", "", "(Ljava/util/List;Ljava/util/List;)V", "getDefines", "()Ljava/util/List;", "getHeadersSearchPaths", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "cidr-common" })
    public static final class HeaderSearchPathsAndDefines
    {
        @NotNull
        private final List<HeadersSearchPath> headersSearchPaths;
        @NotNull
        private final List<String> defines;
        
        @NotNull
        public final List<HeadersSearchPath> getHeadersSearchPaths() {
            return this.headersSearchPaths;
        }
        
        @NotNull
        public final List<String> getDefines() {
            return this.defines;
        }
        
        public HeaderSearchPathsAndDefines(@NotNull final List<? extends HeadersSearchPath> headersSearchPaths, @NotNull final List<String> defines) {
            Intrinsics.checkParameterIsNotNull((Object)headersSearchPaths, "headersSearchPaths");
            Intrinsics.checkParameterIsNotNull((Object)defines, "defines");
            this.headersSearchPaths = (List<HeadersSearchPath>)headersSearchPaths;
            this.defines = defines;
        }
        
        @NotNull
        public final List<HeadersSearchPath> component1() {
            return this.headersSearchPaths;
        }
        
        @NotNull
        public final List<String> component2() {
            return this.defines;
        }
        
        @NotNull
        public final HeaderSearchPathsAndDefines copy(@NotNull final List<? extends HeadersSearchPath> list, @NotNull final List<String> list2) {
            Intrinsics.checkParameterIsNotNull((Object)list, "headersSearchPaths");
            Intrinsics.checkParameterIsNotNull((Object)list2, "defines");
            return new HeaderSearchPathsAndDefines(list, list2);
        }
        
        @Override
        public String toString() {
            return "HeaderSearchPathsAndDefines(headersSearchPaths=" + this.headersSearchPaths + ", defines=" + this.defines + ")";
        }
        
        @Override
        public int hashCode() {
            final List<HeadersSearchPath> headersSearchPaths = this.headersSearchPaths;
            final int n = ((headersSearchPaths != null) ? headersSearchPaths.hashCode() : 0) * 31;
            final List<String> defines = this.defines;
            return n + ((defines != null) ? defines.hashCode() : 0);
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this != o) {
                if (o instanceof HeaderSearchPathsAndDefines) {
                    final HeaderSearchPathsAndDefines headerSearchPathsAndDefines = (HeaderSearchPathsAndDefines)o;
                    if (Intrinsics.areEqual((Object)this.headersSearchPaths, (Object)headerSearchPathsAndDefines.headersSearchPaths) && Intrinsics.areEqual((Object)this.defines, (Object)headerSearchPathsAndDefines.defines)) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        }
    }
}
