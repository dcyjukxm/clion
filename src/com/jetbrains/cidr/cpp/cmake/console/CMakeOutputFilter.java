// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.console;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.execution.filters.OpenFileHyperlinkInfo;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.execution.filters.HyperlinkInfo;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.util.SmartList;
import com.jetbrains.cidr.cpp.CPPLog;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.jetbrains.cidr.lang.toolchains.DefaultCidrToolEnvironment;
import java.util.regex.Matcher;
import java.util.List;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.jetbrains.cidr.cpp.cmake.model.CMakeMessage;
import java.util.ArrayList;
import java.io.File;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import java.util.regex.Pattern;
import com.jetbrains.cidr.execution.DimmableFilter;
import com.intellij.openapi.project.DumbAware;
import com.intellij.execution.filters.Filter;

public class CMakeOutputFilter implements Filter, ErrorMatcher, DumbAware, DimmableFilter
{
    private static final String CMAKE_ERROR = "CMake Error";
    private static final String CMAKE_WARNING = "CMake Warning";
    private static final Pattern MESSAGE_PATTERN;
    private static final Pattern LOCATION_PATTERN;
    @Nullable
    private final Project myProject;
    @NotNull
    private final CidrToolEnvironment myEnvironment;
    @Nullable
    private final File myBaseDir;
    @NotNull
    private final ArrayList<CMakeMessage> myCollectedMessages;
    @NotNull
    private final EditorColorsScheme myColorsScheme;
    private boolean myDimHighlighting;
    private volatile boolean isFinished;
    private final StringBuilder myBuffer;
    private boolean isParseError;
    private boolean isGeneralError;
    private List<CharSequence> parseErrorLines;
    private Matcher currentMatcher;
    
    public CMakeOutputFilter(@Nullable final Project myProject, @Nullable final CidrToolEnvironment cidrToolEnvironment, @Nullable final File myBaseDir) {
        this.myCollectedMessages = new ArrayList<CMakeMessage>();
        this.myBuffer = new StringBuilder();
        this.isParseError = false;
        this.isGeneralError = false;
        this.parseErrorLines = new ArrayList<CharSequence>();
        this.currentMatcher = null;
        this.myProject = myProject;
        this.myEnvironment = ((cidrToolEnvironment == null) ? new DefaultCidrToolEnvironment() : cidrToolEnvironment);
        this.myBaseDir = myBaseDir;
        this.myColorsScheme = EditorColorsManager.getInstance().getGlobalScheme();
    }
    
    public void dimHighlighting() {
        this.myDimHighlighting = true;
    }
    
    @Nullable
    public Filter.Result applyFilter(final String s, final int n) {
        Logger log = null;
        boolean b = false;
        Label_0019: {
            try {
                log = CPPLog.LOG;
                if (!this.isFinished) {
                    b = true;
                    break Label_0019;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            b = false;
        }
        log.assertTrue(b, (Object)"finish has been already called");
        final int n2 = n - s.length();
        final SmartList list = new SmartList();
        Label_0102: {
            try {
                if (!this.a(s, n2, (List<Filter.ResultItem>)list) || this.myDimHighlighting) {
                    break Label_0102;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            ((List<CMakeOutputFilter.CMakeOutputFilter$1>)list).add(new Filter.ResultItem(n2, n, null, this.myColorsScheme.getAttributes(ConsoleViewContentType.ERROR_OUTPUT_KEY), null) {
                public int getHighlighterLayer() {
                    return 5000;
                }
            });
            try {
                if (((List)list).isEmpty()) {
                    return null;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
        }
        return new Filter.Result((List)list);
    }
    
    private boolean a(final CharSequence p0, final int p1, final List<Filter.ResultItem> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/intellij/openapi/util/text/StringUtil.trimTrailing:(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;
        //     4: astore_1       
        //     5: aload_0        
        //     6: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.currentMatcher:Ljava/util/regex/Matcher;
        //     9: ifnull          163
        //    12: aload_1        
        //    13: invokeinterface java/lang/CharSequence.length:()I
        //    18: ifne            44
        //    21: goto            28
        //    24: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //    27: athrow         
        //    28: aload_0        
        //    29: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.myBuffer:Ljava/lang/StringBuilder;
        //    32: bipush          10
        //    34: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    37: pop            
        //    38: iconst_1       
        //    39: ireturn        
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ldc             "  "
        //    47: invokestatic    com/intellij/openapi/util/text/StringUtil.startsWith:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
        //    50: ifeq            135
        //    53: aload_0        
        //    54: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.myBuffer:Ljava/lang/StringBuilder;
        //    57: invokevirtual   java/lang/StringBuilder.length:()I
        //    60: istore          4
        //    62: iload           4
        //    64: ifle            108
        //    67: aload_0        
        //    68: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.myBuffer:Ljava/lang/StringBuilder;
        //    71: iload           4
        //    73: iconst_1       
        //    74: isub           
        //    75: invokevirtual   java/lang/StringBuilder.charAt:(I)C
        //    78: invokestatic    java/lang/Character.isWhitespace:(C)Z
        //    81: ifne            108
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //    90: athrow         
        //    91: aload_0        
        //    92: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.myBuffer:Ljava/lang/StringBuilder;
        //    95: bipush          32
        //    97: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   100: pop            
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   107: athrow         
        //   108: aload_0        
        //   109: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.myBuffer:Ljava/lang/StringBuilder;
        //   112: aload_1        
        //   113: ldc             "  "
        //   115: invokevirtual   java/lang/String.length:()I
        //   118: aload_1        
        //   119: invokeinterface java/lang/CharSequence.length:()I
        //   124: invokeinterface java/lang/CharSequence.subSequence:(II)Ljava/lang/CharSequence;
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;
        //   132: pop            
        //   133: iconst_1       
        //   134: ireturn        
        //   135: aload_0        
        //   136: aload_0        
        //   137: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.currentMatcher:Ljava/util/regex/Matcher;
        //   140: aload_0        
        //   141: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.myBuffer:Ljava/lang/StringBuilder;
        //   144: invokespecial   com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.a:(Ljava/util/regex/Matcher;Ljava/lang/StringBuilder;)V
        //   147: aload_0        
        //   148: aconst_null    
        //   149: putfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.currentMatcher:Ljava/util/regex/Matcher;
        //   152: aload_0        
        //   153: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.myBuffer:Ljava/lang/StringBuilder;
        //   156: iconst_0       
        //   157: invokevirtual   java/lang/StringBuilder.setLength:(I)V
        //   160: goto            229
        //   163: aload_0        
        //   164: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.isParseError:Z
        //   167: ifeq            229
        //   170: aload_0        
        //   171: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.parseErrorLines:Ljava/util/List;
        //   174: invokeinterface java/util/List.size:()I
        //   179: iconst_2       
        //   180: if_icmpge       207
        //   183: goto            190
        //   186: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   189: athrow         
        //   190: aload_0        
        //   191: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.parseErrorLines:Ljava/util/List;
        //   194: aload_1        
        //   195: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   200: pop            
        //   201: iconst_1       
        //   202: ireturn        
        //   203: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   206: athrow         
        //   207: aload_0        
        //   208: aload_0        
        //   209: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.parseErrorLines:Ljava/util/List;
        //   212: invokespecial   com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.a:(Ljava/util/List;)V
        //   215: aload_0        
        //   216: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.parseErrorLines:Ljava/util/List;
        //   219: invokeinterface java/util/List.clear:()V
        //   224: aload_0        
        //   225: iconst_0       
        //   226: putfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.isParseError:Z
        //   229: aload_1        
        //   230: ldc             "CMake Error"
        //   232: invokestatic    com/intellij/openapi/util/text/StringUtil.startsWith:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
        //   235: istore          4
        //   237: iload           4
        //   239: ifne            258
        //   242: aload_1        
        //   243: ldc             "CMake Warning"
        //   245: invokestatic    com/intellij/openapi/util/text/StringUtil.startsWith:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
        //   248: ifeq            272
        //   251: goto            258
        //   254: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   257: athrow         
        //   258: getstatic       com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.MESSAGE_PATTERN:Ljava/util/regex/Pattern;
        //   261: aload_1        
        //   262: invokevirtual   java/util/regex/Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //   265: goto            273
        //   268: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   271: athrow         
        //   272: aconst_null    
        //   273: astore          5
        //   275: aload           5
        //   277: ifnull          345
        //   280: aload           5
        //   282: invokevirtual   java/util/regex/Matcher.matches:()Z
        //   285: ifeq            345
        //   288: goto            295
        //   291: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   294: athrow         
        //   295: aload_0        
        //   296: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.isGeneralError:Z
        //   299: ifeq            324
        //   302: goto            309
        //   305: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   308: athrow         
        //   309: aload_0        
        //   310: aload_0        
        //   311: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.myBuffer:Ljava/lang/StringBuilder;
        //   314: invokespecial   com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.a:(Ljava/lang/StringBuilder;)V
        //   317: goto            324
        //   320: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   323: athrow         
        //   324: aload_0        
        //   325: iconst_0       
        //   326: putfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.isGeneralError:Z
        //   329: aload_0        
        //   330: aload           5
        //   332: putfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.currentMatcher:Ljava/util/regex/Matcher;
        //   335: aload_0        
        //   336: aload           5
        //   338: iload_2        
        //   339: aload_3        
        //   340: invokespecial   com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.a:(Ljava/util/regex/Matcher;ILjava/util/List;)V
        //   343: iconst_1       
        //   344: ireturn        
        //   345: iload           4
        //   347: ifeq            458
        //   350: aload_1        
        //   351: ldc             "CMake Error:"
        //   353: invokestatic    com/intellij/openapi/util/text/StringUtil.startsWith:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
        //   356: ifeq            458
        //   359: goto            366
        //   362: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   365: athrow         
        //   366: aload_0        
        //   367: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.isGeneralError:Z
        //   370: ifeq            395
        //   373: goto            380
        //   376: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   379: athrow         
        //   380: aload_0        
        //   381: aload_0        
        //   382: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.myBuffer:Ljava/lang/StringBuilder;
        //   385: invokespecial   com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.a:(Ljava/lang/StringBuilder;)V
        //   388: goto            395
        //   391: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   394: athrow         
        //   395: aload_0        
        //   396: iconst_0       
        //   397: putfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.isGeneralError:Z
        //   400: aload_1        
        //   401: ldc             "CMake Error: Error in cmake code at"
        //   403: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   406: ifeq            421
        //   409: aload_0        
        //   410: iconst_1       
        //   411: putfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.isParseError:Z
        //   414: goto            456
        //   417: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   420: athrow         
        //   421: aload_0        
        //   422: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.myBuffer:Ljava/lang/StringBuilder;
        //   425: aload_1        
        //   426: ldc             "CMake Error:"
        //   428: invokevirtual   java/lang/String.length:()I
        //   431: aload_1        
        //   432: invokeinterface java/lang/CharSequence.length:()I
        //   437: invokeinterface java/lang/CharSequence.subSequence:(II)Ljava/lang/CharSequence;
        //   442: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;
        //   445: ldc             "\n"
        //   447: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   450: pop            
        //   451: aload_0        
        //   452: iconst_1       
        //   453: putfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.isGeneralError:Z
        //   456: iconst_1       
        //   457: ireturn        
        //   458: aload_0        
        //   459: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.isGeneralError:Z
        //   462: ifeq            530
        //   465: aload_1        
        //   466: ldc             "-- "
        //   468: invokestatic    com/intellij/openapi/util/text/StringUtil.startsWith:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
        //   471: ifne            517
        //   474: goto            481
        //   477: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   480: athrow         
        //   481: aload_1        
        //   482: ldc             ""
        //   484: invokestatic    com/intellij/openapi/util/text/StringUtil.equalsTrimWhitespaces:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
        //   487: ifne            517
        //   490: goto            497
        //   493: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   496: athrow         
        //   497: aload_0        
        //   498: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.myBuffer:Ljava/lang/StringBuilder;
        //   501: aload_1        
        //   502: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;
        //   505: ldc             "\n"
        //   507: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   510: pop            
        //   511: iconst_1       
        //   512: ireturn        
        //   513: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   516: athrow         
        //   517: aload_0        
        //   518: aload_0        
        //   519: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.myBuffer:Ljava/lang/StringBuilder;
        //   522: invokespecial   com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.a:(Ljava/lang/StringBuilder;)V
        //   525: aload_0        
        //   526: iconst_0       
        //   527: putfield        com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter.isGeneralError:Z
        //   530: iconst_0       
        //   531: ireturn        
        //    Signature:
        //  (Ljava/lang/CharSequence;ILjava/util/List<Lcom/intellij/execution/filters/Filter$ResultItem;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  5      21     24     28     Ljava/lang/NumberFormatException;
        //  12     40     40     44     Ljava/lang/NumberFormatException;
        //  62     84     87     91     Ljava/lang/NumberFormatException;
        //  67     101    104    108    Ljava/lang/NumberFormatException;
        //  163    183    186    190    Ljava/lang/NumberFormatException;
        //  170    203    203    207    Ljava/lang/NumberFormatException;
        //  237    251    254    258    Ljava/lang/NumberFormatException;
        //  242    268    268    272    Ljava/lang/NumberFormatException;
        //  275    288    291    295    Ljava/lang/NumberFormatException;
        //  280    302    305    309    Ljava/lang/NumberFormatException;
        //  295    317    320    324    Ljava/lang/NumberFormatException;
        //  345    359    362    366    Ljava/lang/NumberFormatException;
        //  350    373    376    380    Ljava/lang/NumberFormatException;
        //  366    388    391    395    Ljava/lang/NumberFormatException;
        //  395    417    417    421    Ljava/lang/NumberFormatException;
        //  458    474    477    481    Ljava/lang/NumberFormatException;
        //  465    490    493    497    Ljava/lang/NumberFormatException;
        //  481    513    513    517    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0295:
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
    
    public boolean isPotentialMatch(@NotNull final CharSequence charSequence) {
        try {
            if (charSequence == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "multilineFragment", "com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter", "isPotentialMatch"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        Label_0069: {
            try {
                if (StringUtil.contains(charSequence, (CharSequence)"CMake Error")) {
                    break Label_0069;
                }
                final CharSequence charSequence2 = charSequence;
                final String s = "CMake Warning";
                final boolean b = StringUtil.contains(charSequence2, (CharSequence)s);
                if (b) {
                    break Label_0069;
                }
                return false;
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            try {
                final CharSequence charSequence2 = charSequence;
                final String s = "CMake Warning";
                final boolean b = StringUtil.contains(charSequence2, (CharSequence)s);
                if (b) {
                    return true;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    @Nullable
    public CMakeConsoleMessageType match(@NotNull CharSequence trimTrailing) {
        try {
            if (trimTrailing == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "line", "com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter", "match"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        trimTrailing = StringUtil.trimTrailing(trimTrailing);
        final Matcher matcher = CMakeOutputFilter.MESSAGE_PATTERN.matcher(trimTrailing);
        Label_0098: {
            Label_0084: {
                try {
                    if (!matcher.matches()) {
                        break Label_0098;
                    }
                    final Matcher matcher2 = matcher;
                    final int n = 1;
                    final String s = matcher2.group(n);
                    final String s2 = "Error";
                    final boolean b = s.equals(s2);
                    if (b) {
                        break Label_0084;
                    }
                    return CMakeConsoleMessageType.WARNING;
                }
                catch (NumberFormatException ex2) {
                    throw b(ex2);
                }
                try {
                    final Matcher matcher2 = matcher;
                    final int n = 1;
                    final String s = matcher2.group(n);
                    final String s2 = "Error";
                    final boolean b = s.equals(s2);
                    if (b) {
                        return CMakeConsoleMessageType.ERROR;
                    }
                }
                catch (NumberFormatException ex3) {
                    throw b(ex3);
                }
            }
            return CMakeConsoleMessageType.WARNING;
            try {
                if (StringUtil.startsWith(trimTrailing, (CharSequence)"CMake Error:")) {
                    return CMakeConsoleMessageType.ERROR;
                }
            }
            catch (NumberFormatException ex4) {
                throw b(ex4);
            }
        }
        return null;
    }
    
    private void a(@NotNull final Matcher matcher, final int n, @NotNull final List<Filter.ResultItem> list) {
        try {
            if (matcher == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "m", "com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter", "addErrorHyperlink"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "results", "com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter", "addErrorHyperlink"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        final Pair<File, Integer> a = this.a(matcher.group(3), matcher.group(4));
        if (a.first != null) {
            final HyperlinkInfo hyperlinkInfo = this.createHyperlinkInfo((File)a.first, (Integer)a.second);
            try {
                if (hyperlinkInfo != null) {
                    list.add(new Filter.ResultItem(n + matcher.start(3), n + Math.max(matcher.end(3), matcher.end(4)), hyperlinkInfo, this.myDimHighlighting));
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
        }
    }
    
    @Nullable
    protected HyperlinkInfo createHyperlinkInfo(@NotNull final File file, @Nullable final Integer n) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter", "createHyperlinkInfo"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final VirtualFile fileByIoFile = VfsUtil.findFileByIoFile(file, false);
        HyperlinkInfo hyperlinkInfo = null;
        Project myProject = null;
        VirtualFile virtualFile2 = null;
        int n2 = 0;
        Label_0103: {
            Label_0076: {
                Label_0068: {
                    try {
                        if (this.myProject == null) {
                            break Label_0068;
                        }
                        final VirtualFile virtualFile = fileByIoFile;
                        if (virtualFile == null) {
                            break Label_0068;
                        }
                        break Label_0076;
                    }
                    catch (NumberFormatException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final VirtualFile virtualFile = fileByIoFile;
                        if (virtualFile == null) {
                            hyperlinkInfo = null;
                            return hyperlinkInfo;
                        }
                    }
                    catch (NumberFormatException ex3) {
                        throw b(ex3);
                    }
                }
                try {
                    final OpenFileHyperlinkInfo openFileHyperlinkInfo;
                    hyperlinkInfo = (HyperlinkInfo)openFileHyperlinkInfo;
                    myProject = this.myProject;
                    virtualFile2 = fileByIoFile;
                    if (n == null) {
                        n2 = 0;
                        break Label_0103;
                    }
                }
                catch (NumberFormatException ex4) {
                    throw b(ex4);
                }
            }
            n2 = n - 1;
        }
        final OpenFileHyperlinkInfo openFileHyperlinkInfo = new OpenFileHyperlinkInfo(myProject, virtualFile2, n2, 0);
        return hyperlinkInfo;
    }
    
    @NotNull
    public List<CMakeMessage> finishAndGetMessages() {
        Label_0019: {
            Logger log;
            try {
                log = CPPLog.LOG;
                if (!this.isFinished) {
                    final boolean b = true;
                    break Label_0019;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            final boolean b = false;
            try {
                log.assertTrue(b, (Object)"finishAndGetMessages() has been already called.\nThis might happen when something tries to write to CMake console, after generation/build has finished");
                this.isFinished = true;
                if (this.currentMatcher != null) {
                    this.a(this.currentMatcher, this.myBuffer);
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
        }
        try {
            if (this.isParseError) {
                this.a(this.parseErrorLines);
            }
        }
        catch (NumberFormatException ex3) {
            throw b(ex3);
        }
        try {
            if (this.isGeneralError) {
                this.a(this.myBuffer);
            }
        }
        catch (NumberFormatException ex4) {
            throw b(ex4);
        }
        List<Object> unmodifiableList;
        try {
            unmodifiableList = (List<Object>)Collections.unmodifiableList((List<? extends CMakeMessage>)this.myCollectedMessages);
            if (unmodifiableList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter", "finishAndGetMessages"));
            }
        }
        catch (NumberFormatException ex5) {
            throw b(ex5);
        }
        return (List<CMakeMessage>)unmodifiableList;
    }
    
    private void a(final StringBuilder sb) {
        this.myCollectedMessages.add(this.a(CMakeMessage.MessageLevel.ERROR, null, null, sb));
    }
    
    private void a(final List<CharSequence> list) {
        final Matcher matcher = CMakeOutputFilter.LOCATION_PATTERN.matcher(list.get(0));
        String group = null;
        String group2 = null;
        final StringBuilder sb = new StringBuilder();
        if (matcher.matches()) {
            group = matcher.group(1);
            group2 = matcher.group(2);
        }
        else {
            sb.append(list.get(0)).append(' ');
        }
        sb.append(list.get(1));
        this.myCollectedMessages.add(this.a(CMakeMessage.MessageLevel.FATAL_ERROR, group, group2, sb));
    }
    
    private void a(@NotNull final Matcher matcher, @NotNull final StringBuilder sb) {
        try {
            if (matcher == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "matcher", "com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter", "flushMessage"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter", "flushMessage"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        final String group = matcher.group(1);
        final String group2 = matcher.group(2);
        final String group3 = matcher.group(3);
        final String group4 = matcher.group(4);
        CMakeMessage.MessageLevel error;
        if (group.equals("Error")) {
            error = CMakeMessage.MessageLevel.ERROR;
        }
        else {
            CMakeMessage.MessageLevel messageLevel = null;
            Label_0150: {
                try {
                    if (group2 == null) {
                        messageLevel = CMakeMessage.MessageLevel.WARNING;
                        break Label_0150;
                    }
                }
                catch (NumberFormatException ex3) {
                    throw b(ex3);
                }
                messageLevel = CMakeMessage.MessageLevel.AUTHOR_WARNING;
            }
            error = messageLevel;
        }
        this.myCollectedMessages.add(this.a(error, group3, group4, sb));
    }
    
    private CMakeMessage a(@NotNull final CMakeMessage.MessageLevel messageLevel, @Nullable final String s, @Nullable final String s2, @NotNull final StringBuilder sb) {
        try {
            if (messageLevel == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "level", "com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter", "createMessage"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter", "createMessage"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        final Pair<File, Integer> a = this.a(s, s2);
        final CMakeMessage cMakeMessage = new CMakeMessage(messageLevel, (File)a.first, (Integer)a.second, sb.toString().trim());
        sb.setLength(0);
        return cMakeMessage;
    }
    
    @NotNull
    private Pair<File, Integer> a(@Nullable final String s, @Nullable final String s2) {
        Object o = null;
        try {
            Object value = null;
            Label_0021: {
                try {
                    if (s2 == null) {
                        value = null;
                        break Label_0021;
                    }
                }
                catch (NumberFormatException ex) {
                    throw b(ex);
                }
                value = Integer.parseInt(s2);
            }
            o = value;
        }
        catch (NumberFormatException ex5) {}
        Pair create = null;
        Label_0072: {
            Label_0045: {
                try {
                    if (s == null) {
                        break Label_0045;
                    }
                    final CMakeOutputFilter cMakeOutputFilter = this;
                    final File file = cMakeOutputFilter.myBaseDir;
                    if (file == null) {
                        break Label_0045;
                    }
                    break Label_0045;
                }
                catch (NumberFormatException ex2) {
                    throw b(ex2);
                }
                try {
                    final CMakeOutputFilter cMakeOutputFilter = this;
                    final File file = cMakeOutputFilter.myBaseDir;
                    if (file == null) {
                        final File file2 = null;
                        break Label_0072;
                    }
                }
                catch (NumberFormatException ex3) {
                    throw b(ex3);
                }
            }
            final File file2 = new File(this.myEnvironment.toLocalPath(this.myBaseDir, s));
            try {
                create = Pair.create((Object)file2, o);
                if (create == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/console/CMakeOutputFilter", "toFileAndLine"));
                }
            }
            catch (NumberFormatException ex4) {
                throw b(ex4);
            }
        }
        return (Pair<File, Integer>)create;
    }
    
    static {
        MESSAGE_PATTERN = Pattern.compile("CMake ((?:Error|Warning)( \\(dev\\))?)(?: (?:in|at) (.+?)(?::(\\d+)( \\(.+\\))?)?)?:");
        LOCATION_PATTERN = Pattern.compile("(.+?):(\\d+):?");
    }
    
    private static NumberFormatException b(final NumberFormatException ex) {
        return ex;
    }
}
