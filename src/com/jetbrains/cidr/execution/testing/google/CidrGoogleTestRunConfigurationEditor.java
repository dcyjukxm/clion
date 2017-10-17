// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import com.intellij.openapi.diagnostic.Logger;
import java.util.Iterator;
import java.util.regex.Pattern;
import com.jetbrains.cidr.execution.testing.CidrTestLog;
import com.jetbrains.cidr.lang.ui.OCFieldAdapter;
import com.jetbrains.cidr.CidrBundle;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.Nullable;
import java.util.Map;
import com.intellij.openapi.util.Couple;
import com.jetbrains.cidr.lang.ui.OCFieldAdapterForSymbolName;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.progress.util.ProgressIndicatorUtils;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.progress.util.ReadTask;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.Condition;
import com.google.common.collect.Maps;
import java.util.TreeMap;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import java.util.concurrent.atomic.AtomicReference;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import java.util.NavigableMap;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.execution.testing.CidrTestRunConfigurationEditorWithLWValidation;
import com.jetbrains.cidr.execution.CidrBuildConfigurationHelper;
import com.jetbrains.cidr.execution.CidrBuildTarget;
import com.jetbrains.cidr.execution.CidrBuildConfiguration;
import com.jetbrains.cidr.execution.CidrRunConfiguration;

public class CidrGoogleTestRunConfigurationEditor<CONFIGURATION extends CidrRunConfiguration, BC extends CidrBuildConfiguration, TARGET extends CidrBuildTarget<BC>, BCH extends CidrBuildConfigurationHelper<BC, TARGET>> extends CidrTestRunConfigurationEditorWithLWValidation<OCSymbol, CONFIGURATION, BC, TARGET, BCH>
{
    private final NavigableMap<String, OCStructSymbol> myTestSymbols;
    private AtomicReference<String> myCollectingSuiteName;
    
    public CidrGoogleTestRunConfigurationEditor(@NotNull final Project project, @NotNull final BCH bch) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor", "<init>"));
        }
        if (bch == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configHelper", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor", "<init>"));
        }
        super(project, bch);
        this.myTestSymbols = (NavigableMap<String, OCStructSymbol>)Maps.synchronizedNavigableMap((NavigableMap)new TreeMap());
        this.myCollectingSuiteName = new AtomicReference<String>();
    }
    
    @NotNull
    @Override
    protected Condition<OCSymbol> createSuiteCompletionCondition() {
        Condition condition;
        try {
            condition = (ocSymbol -> {
                Label_0024: {
                    try {
                        if (!(ocSymbol instanceof OCStructSymbol)) {
                            return false;
                        }
                        final OCStructSymbol ocStructSymbol = (OCStructSymbol)ocSymbol;
                        final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                        final boolean b = CidrGoogleTestUtil.isGoogleTestClass(ocStructSymbol2);
                        if (b) {
                            break Label_0024;
                        }
                        return false;
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    try {
                        final OCStructSymbol ocStructSymbol = (OCStructSymbol)ocSymbol;
                        final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                        final boolean b = CidrGoogleTestUtil.isGoogleTestClass(ocStructSymbol2);
                        if (b) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                }
                return false;
            });
            if (condition == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor", "createSuiteCompletionCondition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (Condition<OCSymbol>)condition;
    }
    
    private boolean a(@NotNull final Project project, @NotNull final String s) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor", "collectTestSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "suiteName", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor", "collectTestSymbols"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (this.c(s) != null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        if (!s.equals(this.myCollectingSuiteName.getAndSet(s))) {
            final Computable computable = () -> {
                try {
                    if (project == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor", "lambda$collectTestSymbols$2"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    if (s == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "suiteName", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor", "lambda$collectTestSymbols$2"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                final Collection<OCStructSymbol> googleTestSymbolsForSuiteSorted = CidrGoogleTestUtil.findGoogleTestSymbolsForSuiteSorted(project, s);
                final Pattern simpleTestPattern = CidrGoogleTestUtil.getSimpleTestPattern(s);
                for (final OCStructSymbol ocStructSymbol : googleTestSymbolsForSuiteSorted) {
                    String s2;
                    if (simpleTestPattern.matcher(ocStructSymbol.getName()).matches()) {
                        s2 = ocStructSymbol.getName();
                    }
                    else {
                        final Couple<String> googleTestName = CidrGoogleTestUtil.extractGoogleTestName(ocStructSymbol);
                        Logger log = null;
                        boolean b = false;
                        Label_0180: {
                            try {
                                log = CidrTestLog.LOG;
                                if (googleTestName != null) {
                                    b = true;
                                    break Label_0180;
                                }
                            }
                            catch (IllegalArgumentException ex3) {
                                throw b(ex3);
                            }
                            b = false;
                        }
                        log.assertTrue(b);
                        s2 = (String)googleTestName.first + "_" + (String)googleTestName.second + "_Test";
                    }
                    this.myTestSymbols.put(s2, ocStructSymbol);
                }
                final IllegalArgumentException ex4;
                return () -> {
                    try {
                        if (s == null) {
                            new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "suiteName", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor", "lambda$null$1"));
                            throw ex4;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                    try {
                        if (s.equals(this.getTestSuiteText())) {
                            this.scheduleSuiteAndTestsUpdate();
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                };
            };
            final Application application = ApplicationManager.getApplication();
            try {
                if (application.isUnitTestMode()) {
                    ((Runnable)application.runReadAction(computable)).run();
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            ProgressIndicatorUtils.scheduleWithWriteActionPriority(new ReadTask() {
                final /* synthetic */ CidrGoogleTestRunConfigurationEditor this$0;
                
                @Override
                public Continuation performInReadAction(@NotNull final ProgressIndicator progressIndicator) throws ProcessCanceledException {
                    try {
                        if (progressIndicator == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor$1", "performInReadAction"));
                        }
                    }
                    catch (ProcessCanceledException ex) {
                        throw a(ex);
                    }
                    return new Continuation((Runnable)computable.compute(), ModalityState.any());
                }
                
                @Override
                public void onCanceled(@NotNull final ProgressIndicator progressIndicator) {
                    try {
                        if (progressIndicator == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor$1", "onCanceled"));
                        }
                    }
                    catch (ProcessCanceledException ex) {
                        throw a(ex);
                    }
                    Label_0077: {
                        try {
                            if (project.isDisposed()) {
                                return;
                            }
                            final ReadTask readTask = this;
                            final CidrGoogleTestRunConfigurationEditor cidrGoogleTestRunConfigurationEditor = readTask.this$0;
                            final ReadTask readTask2 = this;
                            final String s = s;
                            final OCStructSymbol ocStructSymbol = cidrGoogleTestRunConfigurationEditor.c(s);
                            if (ocStructSymbol == null) {
                                break Label_0077;
                            }
                            return;
                        }
                        catch (ProcessCanceledException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final ReadTask readTask = this;
                            final CidrGoogleTestRunConfigurationEditor cidrGoogleTestRunConfigurationEditor = readTask.this$0;
                            final ReadTask readTask2 = this;
                            final String s = s;
                            final OCStructSymbol ocStructSymbol = cidrGoogleTestRunConfigurationEditor.c(s);
                            if (ocStructSymbol == null) {
                                ProgressIndicatorUtils.scheduleWithWriteActionPriority(this);
                            }
                        }
                        catch (ProcessCanceledException ex3) {
                            throw a(ex3);
                        }
                    }
                }
                
                private static ProcessCanceledException a(final ProcessCanceledException ex) {
                    return ex;
                }
            });
        }
        return true;
    }
    
    @NotNull
    @Override
    protected OCFieldAdapterForSymbolName createSuiteAdapter() {
        OCFieldAdapterForSymbolName ocFieldAdapterForSymbolName;
        try {
            ocFieldAdapterForSymbolName = new OCFieldAdapterForSymbolName() {
                @Override
                protected boolean isTopLevelOnly() {
                    return false;
                }
                
                @NotNull
                @Override
                public String getReadableName(@NotNull final OCSymbol ocSymbol) {
                    try {
                        if (ocSymbol == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor$2", "getReadableName"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    String readableName = null;
                    Label_0116: {
                        if (ocSymbol instanceof OCStructSymbol) {
                            final Couple<String> googleTestName = CidrGoogleTestUtil.extractGoogleTestName((OCStructSymbol)ocSymbol);
                            String s = null;
                            Label_0081: {
                                try {
                                    if (googleTestName == null) {
                                        break Label_0116;
                                    }
                                    final Couple<String> couple = googleTestName;
                                    final Object o = couple.first;
                                    s = (String)o;
                                    if (s == null) {
                                        break Label_0081;
                                    }
                                    return s;
                                }
                                catch (IllegalArgumentException ex2) {
                                    throw b(ex2);
                                }
                                try {
                                    final Couple<String> couple = googleTestName;
                                    final Object o = couple.first;
                                    s = (String)o;
                                    if (s == null) {
                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor$2", "getReadableName"));
                                    }
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw b(ex3);
                                }
                            }
                            return s;
                        }
                        try {
                            readableName = super.getReadableName(ocSymbol);
                            if (readableName == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor$2", "getReadableName"));
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw b(ex4);
                        }
                    }
                    return readableName;
                }
                
                private static IllegalArgumentException b(final IllegalArgumentException ex) {
                    return ex;
                }
            };
            if (ocFieldAdapterForSymbolName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor", "createSuiteAdapter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return ocFieldAdapterForSymbolName;
    }
    
    @Nullable
    private OCStructSymbol c(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "suiteName", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor", "getAnySymbolForSuite"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final String string = s + "_";
        final Map.Entry<String, OCStructSymbol> ceilingEntry = this.myTestSymbols.ceilingEntry(string);
        Label_0102: {
            try {
                if (ceilingEntry == null) {
                    return null;
                }
                final Map.Entry<String, OCStructSymbol> entry = ceilingEntry;
                final String s2 = entry.getKey();
                final String s3 = s2;
                final String s4 = string;
                final boolean b = s3.startsWith(s4);
                if (b) {
                    break Label_0102;
                }
                return null;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final Map.Entry<String, OCStructSymbol> entry = ceilingEntry;
                final String s2 = entry.getKey();
                final String s3 = s2;
                final String s4 = string;
                final boolean b = s3.startsWith(s4);
                if (b) {
                    return ceilingEntry.getValue();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return null;
    }
    
    @Override
    public boolean isValidSuiteName() {
        final String testSuiteText = this.getTestSuiteText();
        Label_0034: {
            try {
                if (testSuiteText == null) {
                    return false;
                }
                final CidrGoogleTestRunConfigurationEditor cidrGoogleTestRunConfigurationEditor = this;
                final CidrGoogleTestRunConfigurationEditor cidrGoogleTestRunConfigurationEditor2 = this;
                final Project project = cidrGoogleTestRunConfigurationEditor2.myProject;
                final String s = testSuiteText;
                final boolean b = cidrGoogleTestRunConfigurationEditor.a(project, s);
                if (b) {
                    return false;
                }
                break Label_0034;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final CidrGoogleTestRunConfigurationEditor cidrGoogleTestRunConfigurationEditor = this;
                final CidrGoogleTestRunConfigurationEditor cidrGoogleTestRunConfigurationEditor2 = this;
                final Project project = cidrGoogleTestRunConfigurationEditor2.myProject;
                final String s = testSuiteText;
                final boolean b = cidrGoogleTestRunConfigurationEditor.a(project, s);
                if (b) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        final OCStructSymbol c = this.c(testSuiteText);
        Label_0059: {
            try {
                if (c == null) {
                    return false;
                }
                final OCStructSymbol ocStructSymbol = c;
                final String s2 = testSuiteText;
                final boolean b2 = CidrGoogleTestUtil.googleTestNameMatches(ocStructSymbol, s2);
                if (b2) {
                    break Label_0059;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final OCStructSymbol ocStructSymbol = c;
                final String s2 = testSuiteText;
                final boolean b2 = CidrGoogleTestUtil.googleTestNameMatches(ocStructSymbol, s2);
                if (b2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return false;
    }
    
    @NotNull
    public Set<String> collectSuiteTests() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor.getTestSuiteText:()Ljava/lang/String;
        //     4: astore_1       
        //     5: aload_1        
        //     6: ifnull          28
        //     9: aload_0        
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor.myProject:Lcom/intellij/openapi/project/Project;
        //    14: aload_1        
        //    15: invokespecial   com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor.a:(Lcom/intellij/openapi/project/Project;Ljava/lang/String;)Z
        //    18: ifeq            77
        //    21: goto            28
        //    24: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    27: athrow         
        //    28: invokestatic    java/util/Collections.emptySet:()Ljava/util/Set;
        //    31: dup            
        //    32: ifnonnull       76
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: new             Ljava/lang/IllegalStateException;
        //    45: dup            
        //    46: ldc             "@NotNull method %s.%s must not return null"
        //    48: ldc             2
        //    50: anewarray       Ljava/lang/Object;
        //    53: dup            
        //    54: ldc             0
        //    56: ldc             "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor"
        //    58: aastore        
        //    59: dup            
        //    60: ldc             1
        //    62: ldc             "collectSuiteTests"
        //    64: aastore        
        //    65: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    68: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    71: athrow         
        //    72: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: areturn        
        //    77: new             Ljava/util/LinkedHashSet;
        //    80: dup            
        //    81: invokespecial   java/util/LinkedHashSet.<init>:()V
        //    84: astore_2       
        //    85: new             Ljava/lang/StringBuilder;
        //    88: dup            
        //    89: invokespecial   java/lang/StringBuilder.<init>:()V
        //    92: aload_1        
        //    93: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    96: ldc             "_"
        //    98: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   101: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   104: astore_3       
        //   105: aload_0        
        //   106: getfield        com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor.myTestSymbols:Ljava/util/NavigableMap;
        //   109: aload_3        
        //   110: invokeinterface java/util/NavigableMap.tailMap:(Ljava/lang/Object;)Ljava/util/SortedMap;
        //   115: invokeinterface java/util/SortedMap.entrySet:()Ljava/util/Set;
        //   120: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   125: astore          4
        //   127: aload           4
        //   129: invokeinterface java/util/Iterator.hasNext:()Z
        //   134: ifeq            230
        //   137: aload           4
        //   139: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   144: checkcast       Ljava/util/Map$Entry;
        //   147: astore          5
        //   149: aload           5
        //   151: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   156: checkcast       Ljava/lang/String;
        //   159: aload_3        
        //   160: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   163: ifne            173
        //   166: goto            230
        //   169: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: aload           5
        //   175: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   180: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   183: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.extractGoogleTestName:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Lcom/intellij/openapi/util/Couple;
        //   186: astore          6
        //   188: aload           6
        //   190: ifnull          127
        //   193: aload           6
        //   195: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //   198: checkcast       Ljava/lang/String;
        //   201: aload_1        
        //   202: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   205: ifne            215
        //   208: goto            127
        //   211: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   214: athrow         
        //   215: aload_2        
        //   216: aload           6
        //   218: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   221: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   226: pop            
        //   227: goto            127
        //   230: aload_2        
        //   231: dup            
        //   232: ifnonnull       269
        //   235: new             Ljava/lang/IllegalStateException;
        //   238: dup            
        //   239: ldc             "@NotNull method %s.%s must not return null"
        //   241: ldc             2
        //   243: anewarray       Ljava/lang/Object;
        //   246: dup            
        //   247: ldc             0
        //   249: ldc             "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor"
        //   251: aastore        
        //   252: dup            
        //   253: ldc             1
        //   255: ldc             "collectSuiteTests"
        //   257: aastore        
        //   258: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   261: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   264: athrow         
        //   265: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   268: athrow         
        //   269: areturn        
        //    Signature:
        //  ()Ljava/util/Set<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  5      21     24     28     Ljava/lang/IllegalArgumentException;
        //  9      35     38     42     Ljava/lang/IllegalArgumentException;
        //  28     72     72     76     Ljava/lang/IllegalArgumentException;
        //  149    169    169    173    Ljava/lang/IllegalArgumentException;
        //  193    211    211    215    Ljava/lang/IllegalArgumentException;
        //  230    265    265    269    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
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
    @Override
    protected List<TARGET> getTargets() {
        List filter;
        try {
            filter = ContainerUtil.filter((Collection)this.myConfigHelper.getTargets(), cidrBuildTarget -> cidrBuildTarget.isExecutable());
            if (filter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor", "getTargets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<TARGET>)filter;
    }
    
    @Override
    protected String getSuitePlaceholder() {
        return CidrBundle.message("gtest.allTestSuites", new Object[0]);
    }
    
    @Override
    protected String getSuitesTipMessage() {
        return CidrBundle.message("gtest.testSuitesTip", new Object[0]);
    }
    
    @Override
    protected String getTestFieldLabelName() {
        return CidrBundle.message("test.configuration.test", new Object[0]);
    }
    
    @Override
    protected String getSuiteFieldLabelName() {
        return CidrBundle.message("gtest.configuration.suite", new Object[0]);
    }
    
    @Override
    protected String getAllTestsMessage() {
        return CidrBundle.message("gtest.allTestsInSuite", new Object[0]);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
