// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.console;

import com.jetbrains.cidr.cpp.cmake.model.CMakeMessage;
import java.util.List;
import com.jetbrains.cidr.cpp.CPPLog;
import com.jetbrains.cidr.execution.DimmableFilter;
import com.jetbrains.cidr.cpp.CPPBundle;
import java.util.EventListener;
import com.intellij.ide.OccurenceNavigator;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.editor.ex.EditorEx;
import org.jetbrains.annotations.Nullable;
import java.util.regex.Matcher;
import java.util.Iterator;
import com.intellij.execution.filters.Filter;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import gnu.trove.TIntArrayList;
import com.intellij.util.EventDispatcher;
import java.util.regex.Pattern;
import com.intellij.execution.impl.ConsoleViewImpl;

public class CMakeConsoleViewImpl extends ConsoleViewImpl
{
    public static final String WARNING_MATCH = "warning";
    public static final String ERROR_MATCH = "error";
    private static final Pattern ERROR_PATTERN;
    private final EventDispatcher<MessageListener> myDispatcher;
    private final TIntArrayList myErrorLines;
    private final TIntArrayList myWarningLines;
    private boolean myNavigatedOnce;
    
    public CMakeConsoleViewImpl(@NotNull final Project project, @NotNull final GlobalSearchScope globalSearchScope, final boolean b, final boolean b2) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl", "<init>"));
        }
        if (globalSearchScope == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "searchScope", "com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl", "<init>"));
        }
        super(project, globalSearchScope, b, b2);
        this.myDispatcher = (EventDispatcher<MessageListener>)EventDispatcher.create((Class)MessageListener.class);
        this.myErrorLines = new TIntArrayList();
        this.myWarningLines = new TIntArrayList();
    }
    
    private boolean b(final CharSequence charSequence) {
        Label_0031: {
            try {
                if (StringUtil.contains(charSequence, (CharSequence)"warning")) {
                    return true;
                }
                final CharSequence charSequence2 = charSequence;
                final String s = "error";
                final boolean b = StringUtil.contains(charSequence2, (CharSequence)s);
                if (b) {
                    return true;
                }
                break Label_0031;
            }
            catch (RuntimeException ex) {
                throw c(ex);
            }
            try {
                final CharSequence charSequence2 = charSequence;
                final String s = "error";
                final boolean b = StringUtil.contains(charSequence2, (CharSequence)s);
                if (b) {
                    return true;
                }
            }
            catch (RuntimeException ex2) {
                throw c(ex2);
            }
        }
        for (final Filter filter : this.myFilters.getFilters()) {
            try {
                if (!(filter instanceof ErrorMatcher)) {
                    continue;
                }
                final Filter filter2 = filter;
                final ErrorMatcher errorMatcher = (ErrorMatcher)filter2;
                final CharSequence charSequence3 = charSequence;
                final boolean b2 = errorMatcher.isPotentialMatch(charSequence3);
                if (b2) {
                    return true;
                }
                continue;
            }
            catch (RuntimeException ex3) {
                throw c(ex3);
            }
            try {
                final Filter filter2 = filter;
                final ErrorMatcher errorMatcher = (ErrorMatcher)filter2;
                final CharSequence charSequence3 = charSequence;
                final boolean b2 = errorMatcher.isPotentialMatch(charSequence3);
                if (b2) {
                    return true;
                }
                continue;
            }
            catch (RuntimeException ex4) {
                throw c(ex4);
            }
        }
        return false;
    }
    
    @Nullable
    private CMakeConsoleMessageType a(final CharSequence charSequence) {
        final Matcher matcher = CMakeConsoleViewImpl.ERROR_PATTERN.matcher(charSequence);
        Label_0052: {
            Label_0038: {
                try {
                    if (!matcher.matches()) {
                        break Label_0052;
                    }
                    final String s = "error";
                    final Matcher matcher2 = matcher;
                    final Matcher matcher3 = matcher;
                    final int n = matcher3.groupCount();
                    final String s2 = matcher2.group(n);
                    final boolean b = s.equals(s2);
                    if (b) {
                        break Label_0038;
                    }
                    return CMakeConsoleMessageType.WARNING;
                }
                catch (RuntimeException ex) {
                    throw c(ex);
                }
                try {
                    final String s = "error";
                    final Matcher matcher2 = matcher;
                    final Matcher matcher3 = matcher;
                    final int n = matcher3.groupCount();
                    final String s2 = matcher2.group(n);
                    final boolean b = s.equals(s2);
                    if (b) {
                        return CMakeConsoleMessageType.ERROR;
                    }
                }
                catch (RuntimeException ex2) {
                    throw c(ex2);
                }
            }
            return CMakeConsoleMessageType.WARNING;
        }
        for (final Filter filter : this.myFilters.getFilters()) {
            if (filter instanceof ErrorMatcher) {
                final CMakeConsoleMessageType match = ((ErrorMatcher)filter).match(charSequence);
                try {
                    if (match != null) {
                        return match;
                    }
                    continue;
                }
                catch (RuntimeException ex3) {
                    throw c(ex3);
                }
            }
        }
        return null;
    }
    
    @NotNull
    @Override
    protected EditorEx doCreateConsoleEditor() {
        final EditorEx doCreateConsoleEditor = super.doCreateConsoleEditor();
        EditorEx editorEx;
        try {
            doCreateConsoleEditor.getDocument().addDocumentListener((DocumentListener)new DocumentListener() {
                private void a(final int n, final CharSequence charSequence) {
                    final CMakeConsoleMessageType access$000 = CMakeConsoleViewImpl.this.a(charSequence);
                    if (access$000 != null) {
                        final boolean b = access$000 == CMakeConsoleMessageType.ERROR;
                        if (b && CMakeConsoleViewImpl.this.myErrorLines.isEmpty()) {
                            CMakeConsoleViewImpl.this.getEditor().getCaretModel().moveToLogicalPosition(new LogicalPosition(n - 1, 0));
                            CMakeConsoleViewImpl.this.getEditor().getScrollingModel().scrollToCaret(ScrollType.CENTER);
                        }
                        (b ? CMakeConsoleViewImpl.this.myErrorLines : CMakeConsoleViewImpl.this.myWarningLines).add(n);
                        CMakeConsoleViewImpl.this.a(b ? CMakeConsoleMessageType.ERROR : CMakeConsoleMessageType.WARNING);
                    }
                }
                
                public void documentChanged(final DocumentEvent documentEvent) {
                    if (!CMakeConsoleViewImpl.this.b(documentEvent.getNewFragment())) {
                        return;
                    }
                    final Document document = documentEvent.getDocument();
                    final int lineNumber = document.getLineNumber(documentEvent.getOffset());
                    for (int lineNumber2 = document.getLineNumber(documentEvent.getOffset() + documentEvent.getNewLength()), i = lineNumber; i <= lineNumber2; ++i) {
                        this.a(i, document.getImmutableCharSequence().subSequence(document.getLineStartOffset(i), document.getLineEndOffset(i)));
                    }
                }
            });
            doCreateConsoleEditor.getSettings().setCaretRowShown(true);
            editorEx = doCreateConsoleEditor;
            if (editorEx == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl", "doCreateConsoleEditor"));
            }
        }
        catch (RuntimeException ex) {
            throw c(ex);
        }
        return editorEx;
    }
    
    @Nullable
    @Override
    protected OccurenceNavigator.OccurenceInfo calcNextOccurrence(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.myErrorLines:Lgnu/trove/TIntArrayList;
        //     4: invokevirtual   gnu/trove/TIntArrayList.isEmpty:()Z
        //     7: ifne            21
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.myErrorLines:Lgnu/trove/TIntArrayList;
        //    14: goto            25
        //    17: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.c:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.myWarningLines:Lgnu/trove/TIntArrayList;
        //    25: astore_2       
        //    26: aload_2        
        //    27: invokevirtual   gnu/trove/TIntArrayList.isEmpty:()Z
        //    30: ifeq            39
        //    33: aconst_null    
        //    34: areturn        
        //    35: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.c:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    38: athrow         
        //    39: iload_1        
        //    40: ifle            51
        //    43: iconst_1       
        //    44: goto            52
        //    47: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.c:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    50: athrow         
        //    51: iconst_0       
        //    52: istore_3       
        //    53: aload_2        
        //    54: aload_0        
        //    55: invokevirtual   com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.getEditor:()Lcom/intellij/openapi/editor/Editor;
        //    58: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //    63: invokeinterface com/intellij/openapi/editor/CaretModel.getLogicalPosition:()Lcom/intellij/openapi/editor/LogicalPosition;
        //    68: getfield        com/intellij/openapi/editor/LogicalPosition.line:I
        //    71: invokevirtual   gnu/trove/TIntArrayList.binarySearch:(I)I
        //    74: istore          4
        //    76: iload_3        
        //    77: ifne            93
        //    80: iload           4
        //    82: iconst_m1      
        //    83: if_icmpeq       137
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.c:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    92: athrow         
        //    93: iload_3        
        //    94: ifeq            143
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.c:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   103: athrow         
        //   104: aload_0        
        //   105: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.myNavigatedOnce:Z
        //   108: ifeq            143
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.c:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   117: athrow         
        //   118: iload           4
        //   120: aload_2        
        //   121: invokevirtual   gnu/trove/TIntArrayList.size:()I
        //   124: iconst_1       
        //   125: iadd           
        //   126: ineg           
        //   127: if_icmpne       143
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.c:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   136: athrow         
        //   137: aconst_null    
        //   138: areturn        
        //   139: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.c:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   142: athrow         
        //   143: iload           4
        //   145: ifge            200
        //   148: iload           4
        //   150: iconst_1       
        //   151: iadd           
        //   152: invokestatic    java/lang/Math.abs:(I)I
        //   155: istore          4
        //   157: iload_3        
        //   158: ifne            171
        //   161: iinc            4, -1
        //   164: goto            218
        //   167: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.c:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   170: athrow         
        //   171: aload_0        
        //   172: getfield        com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.myNavigatedOnce:Z
        //   175: ifne            218
        //   178: iload           4
        //   180: aload_2        
        //   181: invokevirtual   gnu/trove/TIntArrayList.size:()I
        //   184: if_icmpne       218
        //   187: goto            194
        //   190: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.c:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   193: athrow         
        //   194: iconst_0       
        //   195: istore          4
        //   197: goto            218
        //   200: iload           4
        //   202: iload_3        
        //   203: ifeq            214
        //   206: iconst_1       
        //   207: goto            215
        //   210: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.c:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   213: athrow         
        //   214: iconst_m1      
        //   215: iadd           
        //   216: istore          4
        //   218: iload           4
        //   220: iflt            239
        //   223: iload           4
        //   225: aload_2        
        //   226: invokevirtual   gnu/trove/TIntArrayList.size:()I
        //   229: if_icmplt       245
        //   232: goto            239
        //   235: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.c:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   238: athrow         
        //   239: aconst_null    
        //   240: areturn        
        //   241: invokestatic    com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl.c:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   244: athrow         
        //   245: aload_2        
        //   246: iload           4
        //   248: invokevirtual   gnu/trove/TIntArrayList.getQuick:(I)I
        //   251: istore          5
        //   253: new             Lcom/intellij/ide/OccurenceNavigator$OccurenceInfo;
        //   256: dup            
        //   257: new             Lcom/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl$2;
        //   260: dup            
        //   261: aload_0        
        //   262: iload           5
        //   264: invokespecial   com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl$2.<init>:(Lcom/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl;I)V
        //   267: iload           4
        //   269: iconst_1       
        //   270: iadd           
        //   271: aload_2        
        //   272: invokevirtual   gnu/trove/TIntArrayList.size:()I
        //   275: invokespecial   com/intellij/ide/OccurenceNavigator$OccurenceInfo.<init>:(Lcom/intellij/pom/Navigatable;II)V
        //   278: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      17     17     21     Ljava/lang/RuntimeException;
        //  26     35     35     39     Ljava/lang/RuntimeException;
        //  39     47     47     51     Ljava/lang/RuntimeException;
        //  76     86     89     93     Ljava/lang/RuntimeException;
        //  80     97     100    104    Ljava/lang/RuntimeException;
        //  93     111    114    118    Ljava/lang/RuntimeException;
        //  104    130    133    137    Ljava/lang/RuntimeException;
        //  118    139    139    143    Ljava/lang/RuntimeException;
        //  157    167    167    171    Ljava/lang/RuntimeException;
        //  171    187    190    194    Ljava/lang/RuntimeException;
        //  200    210    210    214    Ljava/lang/RuntimeException;
        //  218    232    235    239    Ljava/lang/RuntimeException;
        //  223    241    241    245    Ljava/lang/RuntimeException;
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
    
    private void c() {
        this.getHyperlinks().waitForPendingFilters(1000L);
    }
    
    private void a(@NotNull final CMakeConsoleMessageType cMakeConsoleMessageType) {
        try {
            if (cMakeConsoleMessageType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl", "notifyMessageReported"));
            }
        }
        catch (RuntimeException ex) {
            throw c(ex);
        }
        ((MessageListener)this.myDispatcher.getMulticaster()).messageReported(cMakeConsoleMessageType);
    }
    
    public void addMessageListener(@NotNull final MessageListener messageListener) {
        try {
            if (messageListener == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "listener", "com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl", "addMessageListener"));
            }
        }
        catch (RuntimeException ex) {
            throw c(ex);
        }
        this.myDispatcher.addListener((EventListener)messageListener);
    }
    
    @Override
    public String getNextOccurenceActionName() {
        String s = null;
        Object[] array = null;
        int n = 0;
        String s2 = null;
        Label_0029: {
            try {
                s = "build.nextOccurrence";
                array = new Object[] { null };
                n = 0;
                if (!this.myErrorLines.isEmpty()) {
                    s2 = "build.error";
                    break Label_0029;
                }
            }
            catch (RuntimeException ex) {
                throw c(ex);
            }
            s2 = "build.warning";
        }
        array[n] = CPPBundle.message(s2, new Object[0]);
        return CPPBundle.message(s, array);
    }
    
    @Override
    public String getPreviousOccurenceActionName() {
        String s = null;
        Object[] array = null;
        int n = 0;
        String s2 = null;
        Label_0029: {
            try {
                s = "build.previousOccurrence";
                array = new Object[] { null };
                n = 0;
                if (!this.myErrorLines.isEmpty()) {
                    s2 = "build.error";
                    break Label_0029;
                }
            }
            catch (RuntimeException ex) {
                throw c(ex);
            }
            s2 = "build.warning";
        }
        array[n] = CPPBundle.message(s2, new Object[0]);
        return CPPBundle.message(s, array);
    }
    
    @Override
    public void scrollToEnd() {
        try {
            if (this.myErrorLines.isEmpty()) {
                super.scrollToEnd();
            }
        }
        catch (RuntimeException ex) {
            throw c(ex);
        }
    }
    
    public void dimHighlighting() {
        for (final Filter filter : this.myFilters.getFilters()) {
            try {
                if (filter instanceof DimmableFilter) {
                    ((DimmableFilter)filter).dimHighlighting();
                    continue;
                }
            }
            catch (RuntimeException ex) {
                throw c(ex);
            }
            CPPLog.LOG.warn("Filter doesn't support dimming: " + ((DimmableFilter)filter).getClass().getName());
        }
    }
    
    @NotNull
    public List<CMakeMessage> finishAndGetMessages() {
        this.flushDeferredText();
        this.c();
        for (final Filter filter : this.myFilters.getFilters()) {
            List<CMakeMessage> list = null;
            Label_0065: {
                try {
                    if (!(filter instanceof CMakeOutputFilter)) {
                        continue;
                    }
                    final CMakeOutputFilter cMakeOutputFilter = (CMakeOutputFilter)filter;
                    final CMakeOutputFilter cMakeOutputFilter2 = cMakeOutputFilter;
                    list = cMakeOutputFilter2.finishAndGetMessages();
                    if (list == null) {
                        break Label_0065;
                    }
                    return list;
                }
                catch (RuntimeException ex) {
                    throw c(ex);
                }
                try {
                    final CMakeOutputFilter cMakeOutputFilter = (CMakeOutputFilter)filter;
                    final CMakeOutputFilter cMakeOutputFilter2 = cMakeOutputFilter;
                    list = cMakeOutputFilter2.finishAndGetMessages();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleViewImpl", "finishAndGetMessages"));
                    }
                }
                catch (RuntimeException ex2) {
                    throw c(ex2);
                }
            }
            return list;
        }
        throw new RuntimeException("CMakeOutputFilter not found");
    }
    
    public String toString() {
        return "CMakeConsoleViewImpl{myErrorLines=" + this.myErrorLines + ", myWarningLines=" + this.myWarningLines + '}';
    }
    
    static {
        ERROR_PATTERN = Pattern.compile(".*(?:(?:\\((\\d{1,5})\\))|:(\\d{1,5})):(?:\\d+:)?(?: \\w+)? (warning|error)(?: \\w+)?:.*");
    }
    
    private static RuntimeException c(final RuntimeException ex) {
        return ex;
    }
    
    public interface MessageListener extends EventListener
    {
        void messageReported(@NotNull final CMakeConsoleMessageType p0);
    }
}
