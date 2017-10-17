// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.openapi.util.SystemInfo;
import java.util.Collection;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.openapi.project.IndexNotReadyException;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.execution.filters.OpenFileHyperlinkInfo;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.execution.filters.HyperlinkInfo;
import java.util.regex.Matcher;
import java.util.List;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.text.ReverseCharSequence;
import com.intellij.openapi.util.text.StringUtil;
import java.io.File;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import java.util.regex.Pattern;
import com.intellij.openapi.project.DumbAware;
import com.intellij.execution.filters.Filter;

public class CidrPathConsoleFilter implements Filter, DumbAware, DimmableFilter
{
    public static final int MAX_TIME_PER_LINE = 250;
    private static final String FILE_CHARS = "-+\\p{LC}\\d~._\\(\\)|";
    private static final String LINE_NUMBER = "\\d{1,5}";
    private static final Pattern ABSOLUTE_WIN_PATH_PREFIX;
    public static final String LINE_PATTERN_TEXT = "(?:\\((\\d{1,5})\\))|:(\\d{1,5})";
    private static final Pattern LINE_NUMBER_PATTERN;
    private static final Pattern REVERSE_FILE_PATH_PATTERN;
    private static final int FILE_GROUP = 0;
    public static final int MSVC_LINE_GROUP = 1;
    public static final int REGULAR_LINE_GROUP = 2;
    public static final int COLUMN_GROUP = 3;
    @NotNull
    protected final Project myProject;
    @Nullable
    private final CidrToolEnvironment myEnvironment;
    @Nullable
    private final File myBaseDir;
    private boolean myDimHighlighting;
    
    public CidrPathConsoleFilter(@NotNull final Project myProject, @Nullable final CidrToolEnvironment myEnvironment, @Nullable final File myBaseDir) {
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/CidrPathConsoleFilter", "<init>"));
        }
        this.myProject = myProject;
        this.myEnvironment = myEnvironment;
        this.myBaseDir = myBaseDir;
    }
    
    public void dimHighlighting() {
        this.myDimHighlighting = true;
    }
    
    @Nullable
    public Filter.Result applyFilter(@Nullable final String s, final int n) {
        try {
            if (s == null) {
                return null;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (!StringUtil.containsAnyChar(s, ":(")) {
                return null;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        final CharSequence b = b(s);
        final Matcher matcher = CidrPathConsoleFilter.LINE_NUMBER_PATTERN.matcher(b);
        Matcher matcher2 = null;
        List<Filter.ResultItem> smartList = null;
        int n2 = 0;
        final int n3 = n - s.length();
        try {
            while (matcher.find(n2)) {
                final int n4 = n2;
                int n5 = 0;
                Label_0091: {
                    try {
                        if (matcher.group(1) != null) {
                            n5 = 1;
                            break Label_0091;
                        }
                    }
                    catch (NumberFormatException ex3) {
                        throw b(ex3);
                    }
                    n5 = 2;
                }
                final int n6 = n5;
                n2 = matcher.start(n6);
                int n7 = matcher.end(n6);
                try {
                    if (n6 == 1) {
                        ++n7;
                    }
                }
                catch (NumberFormatException ex4) {
                    throw b(ex4);
                }
                int max;
                try {
                    max = Math.max(0, Integer.valueOf(matcher.group(n6)) - 1);
                }
                catch (NumberFormatException ex8) {
                    continue;
                }
                int max2 = 0;
                if (matcher.group(3) != null) {
                    try {
                        max2 = Math.max(0, Integer.valueOf(matcher.group(3)) - 1);
                        n2 = matcher.start(3);
                        n7 = matcher.end(3);
                    }
                    catch (NumberFormatException ex9) {
                        continue;
                    }
                }
                if (matcher2 == null) {
                    matcher2 = CidrPathConsoleFilter.REVERSE_FILE_PATH_PATTERN.matcher((CharSequence)new ReverseCharSequence(b));
                }
                matcher2.region(s.length() - matcher.start() - 1, s.length() - n4);
                if (matcher2.find()) {
                    final int n8 = s.length() - matcher2.end(0);
                    final HyperlinkInfo processMatch = this.processMatch(s.substring(n8, s.length() - matcher2.start()), max, max2);
                    Label_0331: {
                        try {
                            if (processMatch == null) {
                                continue;
                            }
                            if (smartList != null) {
                                break Label_0331;
                            }
                        }
                        catch (NumberFormatException ex5) {
                            throw b(ex5);
                        }
                        smartList = (List<Filter.ResultItem>)ContainerUtil.newSmartList();
                    }
                    smartList.add(new Filter.ResultItem(n3 + n8, n3 + n7, processMatch, this.myDimHighlighting));
                }
            }
        }
        catch (ProcessCanceledException ex10) {}
        Label_0392: {
            try {
                if (smartList == null) {
                    break Label_0392;
                }
                final List<Filter.ResultItem> list = smartList;
                final int n9 = list.size();
                if (n9 == 0) {
                    break Label_0392;
                }
                return new Filter.Result((List)smartList);
            }
            catch (NumberFormatException ex6) {
                throw b(ex6);
            }
            try {
                final List<Filter.ResultItem> list = smartList;
                final int n9 = list.size();
                if (n9 == 0) {
                    return null;
                }
            }
            catch (NumberFormatException ex7) {
                throw b(ex7);
            }
        }
        return new Filter.Result((List)smartList);
    }
    
    private static CharSequence b(final String s) {
        return (CharSequence)new StringUtil.BombedCharSequence(s) {
            final /* synthetic */ long val$expirationTime = System.currentTimeMillis() + 250L;
            
            protected void checkCanceled() {
                try {
                    if (System.currentTimeMillis() > this.val$expirationTime) {
                        throw new ProcessCanceledException();
                    }
                }
                catch (ProcessCanceledException ex) {
                    throw a(ex);
                }
            }
            
            private static ProcessCanceledException a(final ProcessCanceledException ex) {
                return ex;
            }
        };
    }
    
    @Nullable
    protected HyperlinkInfo processMatch(@NotNull final String p0, final int p1, final int p2) {
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
        //    18: ldc             "matchedPath"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/CidrPathConsoleFilter"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processMatch"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    43: athrow         
        //    44: getstatic       com/intellij/openapi/util/SystemInfo.isWindows:Z
        //    47: ifeq            67
        //    50: getstatic       com/jetbrains/cidr/execution/CidrPathConsoleFilter.ABSOLUTE_WIN_PATH_PREFIX:Ljava/util/regex/Pattern;
        //    53: aload_1        
        //    54: invokevirtual   java/util/regex/Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //    57: invokevirtual   java/util/regex/Matcher.matches:()Z
        //    60: goto            73
        //    63: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    66: athrow         
        //    67: aload_1        
        //    68: ldc             "/"
        //    70: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    73: istore          4
        //    75: iload           4
        //    77: ifne            117
        //    80: getstatic       com/intellij/openapi/util/SystemInfo.isWindows:Z
        //    83: ifne            117
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    92: athrow         
        //    93: aload_1        
        //    94: ldc             "~"
        //    96: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    99: ifeq            117
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   108: athrow         
        //   109: iconst_1       
        //   110: goto            118
        //   113: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   116: athrow         
        //   117: iconst_0       
        //   118: istore          5
        //   120: iload           5
        //   122: ifne            179
        //   125: aload_1        
        //   126: getstatic       java/io/File.separator:Ljava/lang/String;
        //   129: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   132: ifne            171
        //   135: goto            142
        //   138: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   141: athrow         
        //   142: getstatic       com/intellij/openapi/util/SystemInfo.isWindows:Z
        //   145: ifeq            179
        //   148: goto            155
        //   151: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   154: athrow         
        //   155: aload_1        
        //   156: ldc             "/"
        //   158: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   161: ifeq            179
        //   164: goto            171
        //   167: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   170: athrow         
        //   171: iconst_1       
        //   172: goto            180
        //   175: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   178: athrow         
        //   179: iconst_0       
        //   180: istore          6
        //   182: aload_0        
        //   183: getfield        com/jetbrains/cidr/execution/CidrPathConsoleFilter.myEnvironment:Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment;
        //   186: ifnull          243
        //   189: iload           4
        //   191: ifne            213
        //   194: goto            201
        //   197: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   200: athrow         
        //   201: iload           6
        //   203: ifeq            243
        //   206: goto            213
        //   209: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   212: athrow         
        //   213: aload_0        
        //   214: getfield        com/jetbrains/cidr/execution/CidrPathConsoleFilter.myBaseDir:Ljava/io/File;
        //   217: ifnull          243
        //   220: goto            227
        //   223: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   226: athrow         
        //   227: aload_0        
        //   228: getfield        com/jetbrains/cidr/execution/CidrPathConsoleFilter.myEnvironment:Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment;
        //   231: aload_0        
        //   232: getfield        com/jetbrains/cidr/execution/CidrPathConsoleFilter.myBaseDir:Ljava/io/File;
        //   235: aload_1        
        //   236: invokevirtual   com/jetbrains/cidr/lang/toolchains/CidrToolEnvironment.toLocalPath:(Ljava/io/File;Ljava/lang/String;)Ljava/lang/String;
        //   239: astore_1       
        //   240: iconst_1       
        //   241: istore          4
        //   243: iload           4
        //   245: ifeq            264
        //   248: new             Ljava/io/File;
        //   251: dup            
        //   252: aload_1        
        //   253: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   256: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.a:(Ljava/io/File;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   259: astore          7
        //   261: goto            354
        //   264: iload           5
        //   266: ifeq            298
        //   269: ldc             "user.home"
        //   271: invokestatic    java/lang/System.getProperty:(Ljava/lang/String;)Ljava/lang/String;
        //   274: astore          8
        //   276: new             Ljava/io/File;
        //   279: dup            
        //   280: aload           8
        //   282: aload_1        
        //   283: iconst_1       
        //   284: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   287: invokespecial   java/io/File.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //   290: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.a:(Ljava/io/File;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   293: astore          7
        //   295: goto            354
        //   298: aload_0        
        //   299: getfield        com/jetbrains/cidr/execution/CidrPathConsoleFilter.myBaseDir:Ljava/io/File;
        //   302: ifnonnull       313
        //   305: aconst_null    
        //   306: goto            328
        //   309: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   312: athrow         
        //   313: new             Ljava/io/File;
        //   316: dup            
        //   317: aload_0        
        //   318: getfield        com/jetbrains/cidr/execution/CidrPathConsoleFilter.myBaseDir:Ljava/io/File;
        //   321: aload_1        
        //   322: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   325: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.a:(Ljava/io/File;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   328: astore          7
        //   330: aload           7
        //   332: ifnonnull       354
        //   335: iload           6
        //   337: ifne            354
        //   340: goto            347
        //   343: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   346: athrow         
        //   347: aload_0        
        //   348: aload_1        
        //   349: invokespecial   com/jetbrains/cidr/execution/CidrPathConsoleFilter.a:(Ljava/lang/String;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   352: astore          7
        //   354: aload           7
        //   356: ifnonnull       367
        //   359: aconst_null    
        //   360: goto            375
        //   363: invokestatic    com/jetbrains/cidr/execution/CidrPathConsoleFilter.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   366: athrow         
        //   367: aload_0        
        //   368: aload           7
        //   370: iload_2        
        //   371: iload_3        
        //   372: invokevirtual   com/jetbrains/cidr/execution/CidrPathConsoleFilter.createHyperlinkInfo:(Lcom/intellij/openapi/vfs/VirtualFile;II)Lcom/intellij/execution/filters/HyperlinkInfo;
        //   375: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/NumberFormatException;
        //  44     63     63     67     Ljava/lang/NumberFormatException;
        //  75     86     89     93     Ljava/lang/NumberFormatException;
        //  80     102    105    109    Ljava/lang/NumberFormatException;
        //  93     113    113    117    Ljava/lang/NumberFormatException;
        //  120    135    138    142    Ljava/lang/NumberFormatException;
        //  125    148    151    155    Ljava/lang/NumberFormatException;
        //  142    164    167    171    Ljava/lang/NumberFormatException;
        //  155    175    175    179    Ljava/lang/NumberFormatException;
        //  182    194    197    201    Ljava/lang/NumberFormatException;
        //  189    206    209    213    Ljava/lang/NumberFormatException;
        //  201    220    223    227    Ljava/lang/NumberFormatException;
        //  298    309    309    313    Ljava/lang/NumberFormatException;
        //  330    340    343    347    Ljava/lang/NumberFormatException;
        //  354    363    363    367    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0093:
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
    
    @NotNull
    protected HyperlinkInfo createHyperlinkInfo(@NotNull final VirtualFile virtualFile, final int n, final int n2) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/execution/CidrPathConsoleFilter", "createHyperlinkInfo"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        OpenFileHyperlinkInfo openFileHyperlinkInfo;
        try {
            openFileHyperlinkInfo = new OpenFileHyperlinkInfo(this.myProject, virtualFile, n, n2);
            if (openFileHyperlinkInfo == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrPathConsoleFilter", "createHyperlinkInfo"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return (HyperlinkInfo)openFileHyperlinkInfo;
    }
    
    @Nullable
    private static VirtualFile a(@NotNull final File file) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ioFile", "com/jetbrains/cidr/execution/CidrPathConsoleFilter", "fileByIOFile"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return LocalFileSystem.getInstance().findFileByPath(file.getAbsolutePath());
    }
    
    @Nullable
    private VirtualFile a(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/CidrPathConsoleFilter", "findFirstFileByName"));
            }
        }
        catch (IndexNotReadyException ex) {
            throw b((RuntimeException)ex);
        }
        try {
            return (VirtualFile)ContainerUtil.getFirstItem((Collection)FilenameIndex.getVirtualFilesByName(this.myProject, s, GlobalSearchScope.projectScope(this.myProject)));
        }
        catch (IndexNotReadyException ex2) {
            return null;
        }
    }
    
    static {
        String s = null;
        Label_0033: {
            try {
                ABSOLUTE_WIN_PATH_PREFIX = Pattern.compile("^(/|([A-Za-z]:([/\\\\]))).*");
                LINE_NUMBER_PATTERN = Pattern.compile("[^:](?:(?:\\((\\d{1,5})\\))|:(\\d{1,5})(?::(\\d{1,5}))?(?::?\\b|_))");
                if (SystemInfo.isWindows) {
                    s = "^(([-+\\p{LC}\\d~._\\(\\)|]+([-+\\p{LC}\\d~._\\(\\)|\t ]*([/\\\\])+)?)+)(:[A-Za-z])?";
                    break Label_0033;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            s = "^(([-+\\p{LC}\\d~._\\(\\)|]+([-+\\p{LC}\\d~._\\(\\)|\t ]*/+)?)+)";
        }
        REVERSE_FILE_PATH_PATTERN = Pattern.compile(s);
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
