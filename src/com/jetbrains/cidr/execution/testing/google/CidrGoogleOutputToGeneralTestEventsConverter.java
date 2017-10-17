// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Matcher;
import org.jetbrains.annotations.NotNull;
import java.text.ParseException;
import jetbrains.buildServer.messages.serviceMessages.ServiceMessageVisitor;
import com.intellij.openapi.util.Key;
import com.jetbrains.cidr.execution.testing.CidrTestEventProcessor;
import com.intellij.execution.testframework.TestConsoleProperties;
import org.jetbrains.annotations.Nullable;
import java.util.regex.Pattern;
import com.jetbrains.cidr.execution.testing.CidrOutputToGeneralTestEventsConverter;

class CidrGoogleOutputToGeneralTestEventsConverter extends CidrOutputToGeneralTestEventsConverter
{
    private static final Pattern TEST_PATTERN;
    private static final Pattern SUITE_PATTERN;
    private static final Pattern NAME_PATTERN;
    private static final Pattern INSTANTIATION_PATTERN;
    private static final Pattern GLOBAL_ENVIRONMENT_PATTERN;
    private static final Pattern STARTING_PATTERN;
    private static final String FILTER_NOTE_PREFIX = "Note: Google Test filter = ";
    private static final String ZERO_TESTS_FINISHED_PREFIX = "[==========] 0 tests from 0 test cases ran.";
    private boolean myTornDown;
    @Nullable
    private String myPotentiallyFinishedInstantiation;
    @Nullable
    private String myOpenType;
    
    public CidrGoogleOutputToGeneralTestEventsConverter(final String s, final TestConsoleProperties testConsoleProperties) {
        super(s, testConsoleProperties);
        this.myTornDown = false;
        this.myPotentiallyFinishedInstantiation = null;
        this.myOpenType = null;
        this.myEventProcessor = new CidrTestEventProcessor("gtest");
    }
    
    @Override
    protected boolean processServiceMessages(final String p0, final Key p1, final ServiceMessageVisitor p2) throws ParseException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_2        
        //     2: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.logOutput:(Ljava/lang/String;Lcom/intellij/openapi/util/Key;)V
        //     5: aload_0        
        //     6: aload_2        
        //     7: aload_3        
        //     8: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.attachTestFramework:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;)V
        //    11: aload_2        
        //    12: getstatic       com/intellij/execution/process/ProcessOutputTypes.STDOUT:Lcom/intellij/openapi/util/Key;
        //    15: if_acmpne       752
        //    18: aload_0        
        //    19: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myTornDown:Z
        //    22: ifne            68
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    31: athrow         
        //    32: getstatic       com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.STARTING_PATTERN:Ljava/util/regex/Pattern;
        //    35: aload_1        
        //    36: invokevirtual   java/util/regex/Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //    39: invokevirtual   java/util/regex/Matcher.find:()Z
        //    42: ifne            68
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    51: athrow         
        //    52: aload_1        
        //    53: ldc             "Note: Google Test filter = "
        //    55: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    58: ifeq            74
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    67: athrow         
        //    68: iconst_1       
        //    69: ireturn        
        //    70: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    73: athrow         
        //    74: aload_1        
        //    75: ldc             "[==========] 0 tests from 0 test cases ran."
        //    77: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    80: ifeq            94
        //    83: aload_0        
        //    84: iconst_1       
        //    85: putfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myTornDown:Z
        //    88: iconst_1       
        //    89: ireturn        
        //    90: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    93: athrow         
        //    94: getstatic       com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.GLOBAL_ENVIRONMENT_PATTERN:Ljava/util/regex/Pattern;
        //    97: aload_1        
        //    98: invokevirtual   java/util/regex/Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //   101: dup            
        //   102: astore          4
        //   104: invokevirtual   java/util/regex/Matcher.find:()Z
        //   107: ifeq            148
        //   110: aload_0        
        //   111: aload           4
        //   113: iconst_1       
        //   114: invokevirtual   java/util/regex/Matcher.group:(I)Ljava/lang/String;
        //   117: ldc             "tear-down"
        //   119: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   122: putfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myTornDown:Z
        //   125: aload_0        
        //   126: aload_2        
        //   127: aload_3        
        //   128: aconst_null    
        //   129: aconst_null    
        //   130: aconst_null    
        //   131: invokespecial   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.a:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   134: aload_0        
        //   135: aload_2        
        //   136: aload_3        
        //   137: aconst_null    
        //   138: aconst_null    
        //   139: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.reopenSuiteIfAppropriate:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;Ljava/lang/String;Ljava/lang/String;)V
        //   142: iconst_1       
        //   143: ireturn        
        //   144: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   147: athrow         
        //   148: getstatic       com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.TEST_PATTERN:Ljava/util/regex/Pattern;
        //   151: aload_1        
        //   152: invokevirtual   java/util/regex/Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //   155: dup            
        //   156: astore          4
        //   158: invokevirtual   java/util/regex/Matcher.matches:()Z
        //   161: ifeq            431
        //   164: aload           4
        //   166: iconst_1       
        //   167: invokevirtual   java/util/regex/Matcher.group:(I)Ljava/lang/String;
        //   170: astore          5
        //   172: aload           4
        //   174: iconst_4       
        //   175: invokevirtual   java/util/regex/Matcher.group:(I)Ljava/lang/String;
        //   178: invokestatic    com/intellij/openapi/util/text/StringUtil.notNullize:(Ljava/lang/String;)Ljava/lang/String;
        //   181: astore          6
        //   183: aload           4
        //   185: iconst_2       
        //   186: invokevirtual   java/util/regex/Matcher.group:(I)Ljava/lang/String;
        //   189: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.a:(Ljava/lang/String;)Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;
        //   192: astore          7
        //   194: aload           7
        //   196: ifnonnull       205
        //   199: iconst_0       
        //   200: ireturn        
        //   201: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   204: athrow         
        //   205: aload           7
        //   207: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$000:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Z
        //   210: ifeq            225
        //   213: aload           7
        //   215: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$100:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Ljava/lang/String;
        //   218: goto            230
        //   221: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   224: athrow         
        //   225: aload           7
        //   227: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$200:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Ljava/lang/String;
        //   230: astore          8
        //   232: ldc             "RUN"
        //   234: aload           5
        //   236: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   239: ifeq            350
        //   242: aload_0        
        //   243: aload           8
        //   245: putfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myRunningTest:Ljava/lang/String;
        //   248: aload           7
        //   250: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.instantiationLocation:()Ljava/lang/String;
        //   253: astore          9
        //   255: aload           7
        //   257: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$000:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Z
        //   260: ifeq            293
        //   263: new             Ljava/lang/StringBuilder;
        //   266: dup            
        //   267: invokespecial   java/lang/StringBuilder.<init>:()V
        //   270: aload           9
        //   272: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   275: ldc             "."
        //   277: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   280: aload           7
        //   282: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$200:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Ljava/lang/String;
        //   285: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   288: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   291: astore          9
        //   293: aload_0        
        //   294: aload_2        
        //   295: aload_3        
        //   296: aload           7
        //   298: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$000:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Z
        //   301: ifeq            316
        //   304: aload           7
        //   306: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$200:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Ljava/lang/String;
        //   309: goto            317
        //   312: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   315: athrow         
        //   316: aconst_null    
        //   317: aload           9
        //   319: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.reopenSuiteIfAppropriate:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;Ljava/lang/String;Ljava/lang/String;)V
        //   322: aload_0        
        //   323: aconst_null    
        //   324: putfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myPotentiallyFinishedSuite:Ljava/lang/String;
        //   327: aload_0        
        //   328: aload_2        
        //   329: aload_3        
        //   330: aload_0        
        //   331: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myEventProcessor:Lcom/jetbrains/cidr/execution/testing/CidrTestEventProcessor;
        //   334: aload           8
        //   336: aload           7
        //   338: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.testLocation:()Ljava/lang/String;
        //   341: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestEventProcessor.testStarted:(Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //   344: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.doProcessMessages:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;Ljava/util/List;)V
        //   347: goto            429
        //   350: ldc             "FAILED"
        //   352: aload           5
        //   354: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   357: istore          9
        //   359: aload_0        
        //   360: aload_2        
        //   361: aload_3        
        //   362: aload           8
        //   364: iload           9
        //   366: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.processCollectedAssertionOutput:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;Ljava/lang/String;Z)V
        //   369: aload_0        
        //   370: aload_2        
        //   371: aload_3        
        //   372: aload_0        
        //   373: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myEventProcessor:Lcom/jetbrains/cidr/execution/testing/CidrTestEventProcessor;
        //   376: aload           8
        //   378: aload           6
        //   380: iload           9
        //   382: ifne            393
        //   385: iconst_1       
        //   386: goto            394
        //   389: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   392: athrow         
        //   393: iconst_0       
        //   394: invokevirtual   com/jetbrains/cidr/execution/testing/CidrTestEventProcessor.testFinished:(Ljava/lang/String;Ljava/lang/String;Z)Ljava/util/List;
        //   397: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.doProcessMessages:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;Ljava/util/List;)V
        //   400: aload           7
        //   402: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$000:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Z
        //   405: ifeq            424
        //   408: aload_0        
        //   409: aload           7
        //   411: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$200:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Ljava/lang/String;
        //   414: putfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myPotentiallyFinishedSuite:Ljava/lang/String;
        //   417: goto            424
        //   420: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   423: athrow         
        //   424: aload_0        
        //   425: aconst_null    
        //   426: putfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myRunningTest:Ljava/lang/String;
        //   429: iconst_1       
        //   430: ireturn        
        //   431: getstatic       com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.SUITE_PATTERN:Ljava/util/regex/Pattern;
        //   434: aload_1        
        //   435: invokevirtual   java/util/regex/Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //   438: dup            
        //   439: astore          4
        //   441: invokevirtual   java/util/regex/Matcher.matches:()Z
        //   444: ifeq            679
        //   447: aload           4
        //   449: iconst_2       
        //   450: invokevirtual   java/util/regex/Matcher.group:(I)Ljava/lang/String;
        //   453: invokestatic    com/intellij/openapi/util/text/StringUtil.isNotEmpty:(Ljava/lang/String;)Z
        //   456: istore          5
        //   458: aload           4
        //   460: iconst_1       
        //   461: invokevirtual   java/util/regex/Matcher.group:(I)Ljava/lang/String;
        //   464: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.a:(Ljava/lang/String;)Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;
        //   467: astore          6
        //   469: aload           6
        //   471: ifnonnull       480
        //   474: iconst_0       
        //   475: ireturn        
        //   476: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   479: athrow         
        //   480: iload           5
        //   482: ifeq            565
        //   485: aload_0        
        //   486: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myOpenType:Ljava/lang/String;
        //   489: ifnull          521
        //   492: goto            499
        //   495: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   498: athrow         
        //   499: aload_0        
        //   500: aload_2        
        //   501: aload_3        
        //   502: aload_0        
        //   503: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myOpenType:Ljava/lang/String;
        //   506: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.suiteFinished:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;Ljava/lang/String;)V
        //   509: aload_0        
        //   510: aconst_null    
        //   511: putfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myOpenType:Ljava/lang/String;
        //   514: goto            521
        //   517: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   520: athrow         
        //   521: aload_0        
        //   522: aload_2        
        //   523: aload_3        
        //   524: aconst_null    
        //   525: aconst_null    
        //   526: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.reopenSuiteIfAppropriate:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;Ljava/lang/String;Ljava/lang/String;)V
        //   529: aload           6
        //   531: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$300:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Ljava/lang/String;
        //   534: ifnull          553
        //   537: aload_0        
        //   538: aload           6
        //   540: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$300:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Ljava/lang/String;
        //   543: putfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myPotentiallyFinishedInstantiation:Ljava/lang/String;
        //   546: goto            553
        //   549: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   552: athrow         
        //   553: aload_0        
        //   554: aload           6
        //   556: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$400:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Ljava/lang/String;
        //   559: putfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myPotentiallyFinishedSuite:Ljava/lang/String;
        //   562: goto            677
        //   565: aload_0        
        //   566: aload_2        
        //   567: aload_3        
        //   568: aload           6
        //   570: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$300:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Ljava/lang/String;
        //   573: aload           6
        //   575: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.instantiationLocation:()Ljava/lang/String;
        //   578: aload           6
        //   580: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$400:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Ljava/lang/String;
        //   583: invokespecial   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.a:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   586: aload           6
        //   588: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$500:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Z
        //   591: ifeq            677
        //   594: aload_0        
        //   595: new             Ljava/lang/StringBuilder;
        //   598: dup            
        //   599: invokespecial   java/lang/StringBuilder.<init>:()V
        //   602: aload           6
        //   604: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$100:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Ljava/lang/String;
        //   607: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   610: ldc             " - "
        //   612: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   615: aload           6
        //   617: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$600:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Ljava/lang/String;
        //   620: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   623: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   626: putfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myOpenType:Ljava/lang/String;
        //   629: aload_0        
        //   630: aload_2        
        //   631: aload_3        
        //   632: aload_0        
        //   633: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myOpenType:Ljava/lang/String;
        //   636: new             Ljava/lang/StringBuilder;
        //   639: dup            
        //   640: invokespecial   java/lang/StringBuilder.<init>:()V
        //   643: aload           6
        //   645: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.instantiationLocation:()Ljava/lang/String;
        //   648: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   651: ldc             "="
        //   653: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   656: aload           6
        //   658: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo.access$100:(Lcom/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter$TestInfo;)Ljava/lang/String;
        //   661: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   664: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   667: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.suiteStarted:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;Ljava/lang/String;Ljava/lang/String;)V
        //   670: goto            677
        //   673: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   676: athrow         
        //   677: iconst_1       
        //   678: ireturn        
        //   679: aload_0        
        //   680: aload_2        
        //   681: aload_3        
        //   682: aload_1        
        //   683: getstatic       com/jetbrains/cidr/execution/testing/google/CidrGoogleTestConsoleProperties.FAILURE_PATTERN:Ljava/util/regex/Pattern;
        //   686: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.processAssertion:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;Ljava/lang/String;Ljava/util/regex/Pattern;)Ljava/lang/Boolean;
        //   689: astore          5
        //   691: aload           5
        //   693: ifnull          706
        //   696: aload           5
        //   698: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   701: ireturn        
        //   702: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   705: athrow         
        //   706: aload_0        
        //   707: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myPotentiallyFinishedSuite:Ljava/lang/String;
        //   710: ifnull          749
        //   713: aload_0        
        //   714: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myRunningTest:Ljava/lang/String;
        //   717: ifnonnull       749
        //   720: goto            727
        //   723: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   726: athrow         
        //   727: ldc             "\n"
        //   729: aload_1        
        //   730: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   733: ifeq            749
        //   736: goto            743
        //   739: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   742: athrow         
        //   743: iconst_1       
        //   744: ireturn        
        //   745: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   748: athrow         
        //   749: goto            759
        //   752: aload_0        
        //   753: aload_1        
        //   754: aload_2        
        //   755: aload_3        
        //   756: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.checkForTermination:(Ljava/lang/String;Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;)V
        //   759: iconst_0       
        //   760: ireturn        
        //    Exceptions:
        //  throws java.text.ParseException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  0      25     28     32     Ljava/text/ParseException;
        //  18     45     48     52     Ljava/text/ParseException;
        //  32     61     64     68     Ljava/text/ParseException;
        //  52     70     70     74     Ljava/text/ParseException;
        //  74     90     90     94     Ljava/text/ParseException;
        //  104    144    144    148    Ljava/text/ParseException;
        //  194    201    201    205    Ljava/text/ParseException;
        //  205    221    221    225    Ljava/text/ParseException;
        //  293    312    312    316    Ljava/text/ParseException;
        //  359    389    389    393    Ljava/text/ParseException;
        //  394    417    420    424    Ljava/text/ParseException;
        //  469    476    476    480    Ljava/text/ParseException;
        //  480    492    495    499    Ljava/text/ParseException;
        //  485    514    517    521    Ljava/text/ParseException;
        //  521    546    549    553    Ljava/text/ParseException;
        //  565    670    673    677    Ljava/text/ParseException;
        //  691    702    702    706    Ljava/text/ParseException;
        //  706    720    723    727    Ljava/text/ParseException;
        //  713    736    739    743    Ljava/text/ParseException;
        //  727    745    745    749    Ljava/text/ParseException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0032:
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
    protected void processCollectedAssertionOutput(final Key key, final ServiceMessageVisitor serviceMessageVisitor, @NotNull final String s, final boolean b) throws ParseException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter", "processCollectedAssertionOutput"));
            }
        }
        catch (ParseException ex) {
            throw d(ex);
        }
        if (this.myAssertionOutput.length() > 0) {
            final String string = this.myAssertionOutput.toString();
            Label_0100: {
                try {
                    if (b) {
                        this.fireOnErrorMsg("", string, false);
                        break Label_0100;
                    }
                }
                catch (ParseException ex2) {
                    throw d(ex2);
                }
                this.doProcessMessages(key, serviceMessageVisitor, this.myEventProcessor.testErrOut(s, string));
            }
            this.myAssertionOutput.setLength(0);
        }
    }
    
    @Nullable
    private static TestInfo a(final String s) {
        final TestInfo testInfo = new TestInfo();
        Matcher matcher = CidrGoogleOutputToGeneralTestEventsConverter.NAME_PATTERN.matcher(s);
        if (!matcher.matches()) {
            boolean matches = false;
            matcher = CidrGoogleOutputToGeneralTestEventsConverter.INSTANTIATION_PATTERN.matcher(s);
            if (matcher.find()) {
                testInfo.instantiation = matcher.group(1);
                matcher = CidrGoogleOutputToGeneralTestEventsConverter.NAME_PATTERN.matcher(matcher.group(2));
                matches = matcher.matches();
            }
            try {
                if (!matches) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
        }
        Label_0177: {
            TestInfo testInfo5 = null;
            boolean b2 = false;
            Label_0173: {
                Label_0156: {
                    TestInfo testInfo3 = null;
                    boolean b = false;
                    Label_0149: {
                        Label_0140: {
                            try {
                                testInfo.suite = matcher.group(1);
                                testInfo.name = matcher.group(5);
                                testInfo.param = matcher.group(7);
                                if (testInfo.param != null) {
                                    break Label_0156;
                                }
                                final TestInfo testInfo2 = testInfo;
                                final Matcher matcher2 = matcher;
                                final int n = 3;
                                final String s2 = matcher2.group(n);
                                testInfo2.param = s2;
                                testInfo3 = testInfo;
                                final TestInfo testInfo4 = testInfo;
                                final String s3 = testInfo4.param;
                                if (s3 != null) {
                                    break Label_0140;
                                }
                                break Label_0140;
                            }
                            catch (IllegalArgumentException ex2) {
                                throw d(ex2);
                            }
                            try {
                                final TestInfo testInfo2 = testInfo;
                                final Matcher matcher2 = matcher;
                                final int n = 3;
                                final String s2 = matcher2.group(n);
                                testInfo2.param = s2;
                                testInfo3 = testInfo;
                                final TestInfo testInfo4 = testInfo;
                                final String s3 = testInfo4.param;
                                if (s3 != null) {
                                    b = true;
                                    break Label_0149;
                                }
                            }
                            catch (IllegalArgumentException ex3) {
                                throw d(ex3);
                            }
                        }
                        b = false;
                    }
                    testInfo3.isTyped = b;
                    break Label_0177;
                    try {
                        testInfo5 = testInfo;
                        if (testInfo.instantiation != null) {
                            b2 = true;
                            break Label_0173;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw d(ex4);
                    }
                }
                b2 = false;
            }
            testInfo5.isValueParameterized = b2;
        }
        testInfo.type = matcher.group(10);
        return testInfo;
    }
    
    private void a(final Key p0, final ServiceMessageVisitor p1, @Nullable final String p2, final String p3, @Nullable final String p4) throws ParseException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           5
        //     2: ifnull          24
        //     5: aload           5
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myPotentiallyFinishedSuite:Ljava/lang/String;
        //    11: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    14: ifeq            32
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    23: athrow         
        //    24: iconst_1       
        //    25: goto            33
        //    28: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    31: athrow         
        //    32: iconst_0       
        //    33: istore          6
        //    35: aload_3        
        //    36: ifnull          77
        //    39: aload_3        
        //    40: aload_0        
        //    41: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myPotentiallyFinishedInstantiation:Ljava/lang/String;
        //    44: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    47: ifeq            69
        //    50: goto            57
        //    53: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    56: athrow         
        //    57: iload           6
        //    59: ifne            77
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    68: athrow         
        //    69: iconst_1       
        //    70: goto            78
        //    73: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    76: athrow         
        //    77: iconst_0       
        //    78: istore          7
        //    80: aload_0        
        //    81: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myPotentiallyFinishedInstantiation:Ljava/lang/String;
        //    84: ifnull          127
        //    87: iload           7
        //    89: ifne            110
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    98: athrow         
        //    99: aload_3        
        //   100: ifnonnull       127
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   109: athrow         
        //   110: aload_0        
        //   111: aload_1        
        //   112: aload_2        
        //   113: aload_0        
        //   114: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myPotentiallyFinishedInstantiation:Ljava/lang/String;
        //   117: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.suiteFinished:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;Ljava/lang/String;)V
        //   120: goto            127
        //   123: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   126: athrow         
        //   127: aload           5
        //   129: ifnull          149
        //   132: aload_0        
        //   133: aload_1        
        //   134: aload_2        
        //   135: aload           5
        //   137: aload           5
        //   139: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.reopenSuiteIfAppropriate:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;Ljava/lang/String;Ljava/lang/String;)V
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   148: athrow         
        //   149: iload           7
        //   151: ifeq            170
        //   154: aload_0        
        //   155: aload_1        
        //   156: aload_2        
        //   157: aload_3        
        //   158: aload           4
        //   160: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.suiteStarted:(Lcom/intellij/openapi/util/Key;Ljetbrains/buildServer/messages/serviceMessages/ServiceMessageVisitor;Ljava/lang/String;Ljava/lang/String;)V
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.d:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   169: athrow         
        //   170: aload_0        
        //   171: aconst_null    
        //   172: putfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter.myPotentiallyFinishedInstantiation:Ljava/lang/String;
        //   175: return         
        //    Exceptions:
        //  throws java.text.ParseException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  0      17     20     24     Ljava/text/ParseException;
        //  5      28     28     32     Ljava/text/ParseException;
        //  35     50     53     57     Ljava/text/ParseException;
        //  39     62     65     69     Ljava/text/ParseException;
        //  57     73     73     77     Ljava/text/ParseException;
        //  80     92     95     99     Ljava/text/ParseException;
        //  87     103    106    110    Ljava/text/ParseException;
        //  99     120    123    127    Ljava/text/ParseException;
        //  127    142    145    149    Ljava/text/ParseException;
        //  149    163    166    170    Ljava/text/ParseException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0057:
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
    protected void suiteStarted(final Key key, final ServiceMessageVisitor serviceMessageVisitor, @NotNull final String s, @NotNull final String s2) throws ParseException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter", "suiteStarted"));
            }
        }
        catch (ParseException ex) {
            throw d(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter", "suiteStarted"));
            }
        }
        catch (ParseException ex2) {
            throw d(ex2);
        }
        this.doProcessMessages(key, serviceMessageVisitor, this.myEventProcessor.suiteStarted(s, s2));
    }
    
    @Override
    protected void suiteFinished(final Key key, final ServiceMessageVisitor serviceMessageVisitor, @NotNull final String s) throws ParseException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/testing/google/CidrGoogleOutputToGeneralTestEventsConverter", "suiteFinished"));
            }
        }
        catch (ParseException ex) {
            throw d(ex);
        }
        this.doProcessMessages(key, serviceMessageVisitor, this.myEventProcessor.suiteFinished(s));
    }
    
    static {
        TEST_PATTERN = Pattern.compile("\\[\\s*(OK|RUN|FAILED)\\s*]\\s*(.*?)(\\s+\\((\\d+) ms\\))?\n");
        SUITE_PATTERN = Pattern.compile("\\[----------] \\d+ tests? from (.*?)(\\s+\\(\\d+ ms total\\))?\n");
        NAME_PATTERN = Pattern.compile("(\\w+)(/(\\d+))?(\\.(\\w+))?(/(\\d+))?((, where TypeParam = (.*))|(, where GetParam\\(\\) = .*))?");
        INSTANTIATION_PATTERN = Pattern.compile("(\\w+)/(.*)");
        GLOBAL_ENVIRONMENT_PATTERN = Pattern.compile("\\[----------] Global test environment (set-up|tear-down)");
        STARTING_PATTERN = Pattern.compile("\\[==========] Running \\d+ tests? from \\d+ test cases?\\.");
    }
    
    private static Exception d(final Exception ex) {
        return ex;
    }
    
    static class TestInfo
    {
        private String instantiation;
        private String suite;
        private String name;
        private String param;
        private String type;
        private boolean isTyped;
        private boolean isValueParameterized;
        
        public String instantiationLocation() {
            String s = this.suite;
            if (this.instantiation != null) {
                s = this.instantiation + "/" + s;
            }
            return s;
        }
        
        public String testLocation() {
            String s = this.instantiationLocation() + "." + this.name;
            if (this.param != null) {
                s = s + "=" + this.param;
            }
            if (this.isTyped) {
                s += "?typed";
            }
            return s;
        }
    }
}
