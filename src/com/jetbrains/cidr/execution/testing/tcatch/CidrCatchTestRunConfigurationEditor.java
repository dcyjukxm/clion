// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.application.Application;
import com.intellij.psi.search.SearchScope;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressManager;
import java.util.TreeSet;
import com.jetbrains.cidr.CidrBundle;
import com.intellij.util.containers.ContainerUtil;
import java.util.List;
import com.intellij.openapi.util.Computable;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.Set;
import com.intellij.openapi.util.Pair;
import com.intellij.codeInsight.completion.PrefixMatcher;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import javax.swing.Icon;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.ui.OCFieldAdapter;
import com.intellij.openapi.util.Condition;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import com.jetbrains.cidr.execution.testing.CidrTestRunConfigurationEditorWithLWValidation;
import com.jetbrains.cidr.execution.CidrBuildConfigurationHelper;
import com.jetbrains.cidr.execution.CidrBuildTarget;
import com.jetbrains.cidr.execution.CidrBuildConfiguration;
import com.jetbrains.cidr.execution.CidrRunConfiguration;

public class CidrCatchTestRunConfigurationEditor<CONFIGURATION extends CidrRunConfiguration, BC extends CidrBuildConfiguration, TARGET extends CidrBuildTarget<BC>, BCH extends CidrBuildConfigurationHelper<BC, TARGET>> extends CidrTestRunConfigurationEditorWithLWValidation<String, CONFIGURATION, BC, TARGET, BCH>
{
    private final ArrayList<CidrCatchTestCache> myTestInfo;
    private static int INIT;
    private static int WORKING;
    private static int READY;
    private final AtomicInteger myTestInfoState;
    
    public CidrCatchTestRunConfigurationEditor(@NotNull final Project project, @NotNull final BCH bch) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor", "<init>"));
        }
        if (bch == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configHelper", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor", "<init>"));
        }
        super(project, bch);
        this.myTestInfo = new ArrayList<CidrCatchTestCache>();
        this.myTestInfoState = new AtomicInteger(CidrCatchTestRunConfigurationEditor.INIT);
    }
    
    @NotNull
    @Override
    protected Condition<String> createSuiteCompletionCondition() {
        Condition condition;
        try {
            condition = (s -> true);
            if (condition == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor", "createSuiteCompletionCondition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a((Throwable)ex);
        }
        return (Condition<String>)condition;
    }
    
    @NotNull
    @Override
    protected OCFieldAdapter<String> createSuiteAdapter() {
        OCFieldAdapter<String> ocFieldAdapter;
        try {
            ocFieldAdapter = new OCFieldAdapter<String>() {
                @NotNull
                @Contract(pure = true)
                @Override
                public String getName(final String s) {
                    String s2 = null;
                    Label_0014: {
                        try {
                            if (s == null) {
                                final String s3;
                                s2 = (s3 = "non-tagged");
                                break Label_0014;
                            }
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        s2 = s;
                        String s3 = s;
                        try {
                            if (s3 == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1", "getName"));
                            }
                        }
                        catch (IllegalStateException ex2) {
                            throw a(ex2);
                        }
                    }
                    return s2;
                }
                
                @Contract(value = "_ -> null", pure = true)
                @Override
                public Icon getIcon(final String s) {
                    return null;
                }
                
                @Contract(value = "_ -> null", pure = true)
                @Override
                public String getTypeText(final String s) {
                    return null;
                }
                
                @NotNull
                @Contract(pure = true)
                @Override
                public String getReadableName(final String s) {
                    String name;
                    try {
                        name = this.getName(s);
                        if (name == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1", "getReadableName"));
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    return name;
                }
                
                @NotNull
                @Override
                public Collection<String> collectValuesFromProject(@NotNull final Project p0, @Nullable final Condition<String> p1) {
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
                    //    24: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1"
                    //    26: aastore        
                    //    27: dup            
                    //    28: ldc             2
                    //    30: ldc             "collectValuesFromProject"
                    //    32: aastore        
                    //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                    //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                    //    39: athrow         
                    //    40: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                    //    43: athrow         
                    //    44: aload_0        
                    //    45: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1.this$0:Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor;
                    //    48: aload_0        
                    //    49: getfield        com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1.this$0:Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor;
                    //    52: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor.access$000:(Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor;)Lcom/intellij/openapi/project/Project;
                    //    55: aload_2        
                    //    56: invokedynamic   accept:(Lcom/intellij/openapi/util/Condition;)Ljava/util/function/Consumer;
                    //    61: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor.access$100:(Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor;Lcom/intellij/openapi/project/Project;Ljava/util/function/Consumer;)Ljava/util/Set;
                    //    64: dup            
                    //    65: ifnonnull       102
                    //    68: new             Ljava/lang/IllegalStateException;
                    //    71: dup            
                    //    72: ldc             "@NotNull method %s.%s must not return null"
                    //    74: ldc             2
                    //    76: anewarray       Ljava/lang/Object;
                    //    79: dup            
                    //    80: ldc             0
                    //    82: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1"
                    //    84: aastore        
                    //    85: dup            
                    //    86: ldc             1
                    //    88: ldc             "collectValuesFromProject"
                    //    90: aastore        
                    //    91: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                    //    94: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
                    //    97: athrow         
                    //    98: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                    //   101: athrow         
                    //   102: areturn        
                    //    Signature:
                    //  (Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/util/Condition<Ljava/lang/String;>;)Ljava/util/Collection<Ljava/lang/String;>;
                    //    Exceptions:
                    //  Try           Handler
                    //  Start  End    Start  End    Type                             
                    //  -----  -----  -----  -----  ---------------------------------
                    //  0      40     40     44     Ljava/lang/IllegalStateException;
                    //  44     98     98     102    Ljava/lang/IllegalStateException;
                    // 
                    // The error that occurred was:
                    // 
                    // java.lang.IllegalStateException: Could not infer any expression.
                    //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
                    //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
                    //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
                    //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:494)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
                
                @Contract(pure = true)
                @NotNull
                @Override
                public PrefixMatcher createPrefixMatcher(@NotNull final String s) {
                    try {
                        if (s == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1", "createPrefixMatcher"));
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    TagPrefixMatcher tagPrefixMatcher;
                    try {
                        tagPrefixMatcher = new TagPrefixMatcher(s);
                        if (tagPrefixMatcher == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1", "createPrefixMatcher"));
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    return tagPrefixMatcher;
                }
                
                @NotNull
                @Override
                public String getPrefix(@NotNull final String s, final int n) {
                    try {
                        if (s == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1", "getPrefix"));
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    final int n2 = Math.max(s.lastIndexOf(93, n - 1), s.lastIndexOf(32, n - 1)) + 1;
                    final int n3 = s.lastIndexOf(10, n - 1) + 1;
                    String substring;
                    try {
                        substring = s.substring(Math.max(n2, n3), n);
                        if (substring == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$1", "getPrefix"));
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    return substring;
                }
                
                private static IllegalStateException a(final IllegalStateException ex) {
                    return ex;
                }
            };
            if (ocFieldAdapter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor", "createSuiteAdapter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a((Throwable)ex);
        }
        return ocFieldAdapter;
    }
    
    @Override
    public boolean isValidSuiteName() {
        final String testSuiteText = this.getTestSuiteText();
        try {
            if (testSuiteText == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a((Throwable)ex);
        }
        Label_0038: {
            try {
                if ("non-tagged".equals(testSuiteText)) {
                    break Label_0038;
                }
                final String s = testSuiteText;
                final boolean b = CidrCatchTestUtil.isValidStringOfTags(s);
                if (b) {
                    break Label_0038;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a((Throwable)ex2);
            }
            try {
                final String s = testSuiteText;
                final boolean b = CidrCatchTestUtil.isValidStringOfTags(s);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a((Throwable)ex3);
            }
        }
        return false;
    }
    
    @Override
    protected boolean isChecking() {
        try {
            if (this.myTestInfoState.get() != CidrCatchTestRunConfigurationEditor.READY) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a((Throwable)ex);
        }
        return false;
    }
    
    @NotNull
    private Set<String> b(@NotNull final Project project, @Nullable final Consumer<Pair<CidrCatchTestCache, Set<String>>> consumer) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor", "collectStringsFromProject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a((Throwable)ex);
        }
        Set<Object> set;
        try {
            set = OCFieldAdapter.collectAllValues(project, Collections.emptySet(), (com.intellij.openapi.util.Computable<Set<Object>>)(() -> {
                try {
                    if (project == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor", "lambda$collectStringsFromProject$2"));
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                Label_0067: {
                    try {
                        if (project.isDisposed()) {
                            break Label_0067;
                        }
                        final CidrCatchTestRunConfigurationEditor cidrCatchTestRunConfigurationEditor = this;
                        final boolean b = cidrCatchTestRunConfigurationEditor.isDisposed();
                        if (b) {
                            break Label_0067;
                        }
                        break Label_0067;
                    }
                    catch (Throwable t2) {
                        throw a(t2);
                    }
                    try {
                        final CidrCatchTestRunConfigurationEditor cidrCatchTestRunConfigurationEditor = this;
                        final boolean b = cidrCatchTestRunConfigurationEditor.isDisposed();
                        if (b) {
                            return Collections.emptySet();
                        }
                    }
                    catch (Throwable t3) {
                        throw a(t3);
                    }
                }
                final TreeSet set = new TreeSet();
                final Object o;
                final Consumer<CidrCatchTestCache> consumer2 = cidrCatchTestCache -> {
                    try {
                        ProgressManager.checkCanceled();
                        if (this.myTestInfoState.get() == CidrCatchTestRunConfigurationEditor.WORKING) {
                            this.myTestInfo.add(cidrCatchTestCache);
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a((Throwable)ex);
                    }
                    try {
                        if (consumer != null) {
                            consumer.accept(Pair.create((Object)cidrCatchTestCache, o));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a((Throwable)ex2);
                    }
                    return;
                };
                try {
                    if (this.myTestInfoState.get() == CidrCatchTestRunConfigurationEditor.READY) {
                        this.myTestInfo.forEach(consumer2);
                        return set;
                    }
                }
                catch (Throwable t4) {
                    throw a(t4);
                }
                Label_0151: {
                    try {
                        if (!ApplicationManager.getApplication().isDispatchThread()) {
                            break Label_0151;
                        }
                        final Application application = ApplicationManager.getApplication();
                        final boolean b2 = application.isUnitTestMode();
                        if (b2) {
                            break Label_0151;
                        }
                        return set;
                    }
                    catch (Throwable t5) {
                        throw a(t5);
                    }
                    try {
                        final Application application = ApplicationManager.getApplication();
                        final boolean b2 = application.isUnitTestMode();
                        if (!b2) {
                            return set;
                        }
                        if (!this.myTestInfoState.compareAndSet(CidrCatchTestRunConfigurationEditor.INIT, CidrCatchTestRunConfigurationEditor.WORKING)) {
                            return set;
                        }
                    }
                    catch (Throwable t6) {
                        throw a(t6);
                    }
                }
                try {
                    CidrCatchTestUtil.consumeCatchTestSymbols(project, OCSearchScope.getExplicitlySpecifiedProjectSourceFiles(project), null, consumer2);
                    this.myTestInfoState.set(CidrCatchTestRunConfigurationEditor.READY);
                    this.scheduleSuiteAndTestsUpdate();
                }
                catch (Throwable t7) {
                    this.myTestInfo.clear();
                    this.myTestInfoState.set(CidrCatchTestRunConfigurationEditor.INIT);
                    throw t7;
                }
                return set;
            }));
            if (set == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor", "collectStringsFromProject"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a((Throwable)ex2);
        }
        return (Set<String>)set;
    }
    
    @NotNull
    public Set<String> collectSuiteTests() {
        final String testSuiteText = this.getTestSuiteText();
        Label_0058: {
            Set<String> set = null;
            Label_0023: {
                try {
                    if (testSuiteText != null) {
                        break Label_0058;
                    }
                    set = Collections.emptySet();
                    if (set == null) {
                        break Label_0023;
                    }
                    return set;
                }
                catch (IllegalArgumentException ex) {
                    throw a((Throwable)ex);
                }
                try {
                    set = Collections.emptySet();
                    if (set == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor", "collectSuiteTests"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a((Throwable)ex2);
                }
            }
            return set;
        }
        CidrCatchTestUtil.splitTags(testSuiteText);
        Set<String> b;
        try {
            final Collection<?> collection;
            b = this.b(this.myProject, pair -> {
                try {
                    if (CidrCatchTestUtil.splitTags(((CidrCatchTestCache)pair.first).getTags()).containsAll(collection)) {
                        ((Set)pair.second).add(((CidrCatchTestCache)pair.first).getNotNullTestName());
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a((Throwable)ex3);
                }
                return;
            });
            if (b == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor", "collectSuiteTests"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a((Throwable)ex4);
        }
        return b;
    }
    
    @NotNull
    @Override
    protected List<TARGET> getTargets() {
        List filter;
        try {
            filter = ContainerUtil.filter((Collection)this.myConfigHelper.getTargets(), cidrBuildTarget -> cidrBuildTarget.isExecutable());
            if (filter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor", "getTargets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a((Throwable)ex);
        }
        return (List<TARGET>)filter;
    }
    
    @Override
    protected String getSuitePlaceholder() {
        return CidrBundle.message("catch.allTestTags", new Object[0]);
    }
    
    @Override
    protected String getSuitesTipMessage() {
        return CidrBundle.message("catch.testSuitesTip", new Object[0]);
    }
    
    @Override
    protected String getTestFieldLabelName() {
        return CidrBundle.message("test.configuration.test", new Object[0]);
    }
    
    @Override
    protected String getSuiteFieldLabelName() {
        return CidrBundle.message("catch.configuration.tags", new Object[0]);
    }
    
    @Override
    protected String getAllTestsMessage() {
        try {
            if ("non-tagged".equals(this.getTestSuiteText())) {
                return CidrBundle.message("catch.selectTestForNonTagged", new Object[0]);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a((Throwable)ex);
        }
        return CidrBundle.message("catch.allTestForTags", new Object[0]);
    }
    
    static {
        CidrCatchTestRunConfigurationEditor.INIT = 0;
        CidrCatchTestRunConfigurationEditor.WORKING = 1;
        CidrCatchTestRunConfigurationEditor.READY = 2;
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
    
    private static class TagPrefixMatcher extends PrefixMatcher
    {
        public TagPrefixMatcher(final String s) {
            super(s);
        }
        
        public boolean prefixMatches(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$TagPrefixMatcher", "prefixMatches"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return StringUtil.startsWith((CharSequence)s, (CharSequence)this.getPrefix());
        }
        
        @NotNull
        public PrefixMatcher cloneWithPrefix(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$TagPrefixMatcher", "cloneWithPrefix"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            TagPrefixMatcher tagPrefixMatcher;
            try {
                tagPrefixMatcher = new TagPrefixMatcher(s);
                if (tagPrefixMatcher == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRunConfigurationEditor$TagPrefixMatcher", "cloneWithPrefix"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return tagPrefixMatcher;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
