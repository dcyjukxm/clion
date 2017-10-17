// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.sourceglider.relations.RelationSignature;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCAddInitializerIntentionAction;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.dfa.OCInstruction;
import com.jetbrains.cidr.lang.dfa.OCLocalDefinitionsSearcher;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.psi.OCLoopStatement;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.jetbrains.cidr.lang.quickfixes.OCChangeElementIntentionAction;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.sourceglider.relations.IRelation;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.jetbrains.cidr.lang.dfa.OCUnreachableCodeFinder;
import com.jetbrains.cidr.lang.dfa.OCControlFlowGraph;
import com.jetbrains.cidr.lang.util.OCElementsRange;
import java.util.ArrayList;
import com.jetbrains.sourceglider.atttributes.Attribute;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.psi.OCElement;
import java.util.Collections;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import com.intellij.codeInspection.GlobalInspectionContext;
import com.intellij.codeInspection.ProblemDescriptionsProcessor;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.ProblemHighlightType;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.openapi.application.Application;
import com.jetbrains.cidr.lang.OCLog;
import com.jetbrains.sourceglider.ui.DummyThreadCallback;
import com.intellij.openapi.progress.util.AbstractProgressIndicatorBase;
import com.intellij.openapi.progress.util.ProgressIndicatorBase;
import com.intellij.concurrency.SensitiveProgressWrapper;
import com.intellij.openapi.progress.ProgressIndicator;
import java.util.Iterator;
import java.util.List;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressManager;
import com.jetbrains.sourceglider.ui.Task;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.dfa.contextSensitive.OCContextSensitiveControlFlowBuilder;
import com.jetbrains.sourceglider.ManagerInstancesProvider;
import com.jetbrains.cidr.lang.dfa.contextSensitive.OCSourceGliderComponent;
import com.jetbrains.sourceglider.ui.ThreadCallback;
import java.util.Set;
import com.intellij.psi.PsiElement;
import java.util.HashSet;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import com.jetbrains.cidr.lang.dfa.contextSensitive.OCDFAUtils;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiFile;
import com.intellij.lang.annotation.AnnotationSession;
import com.jetbrains.sourceglider.scripts.rml.ProfileManager;
import java.util.HashMap;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.codeInspection.LocalInspectionToolSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;
import com.jetbrains.sourceglider.bdd.BDDMemoryOverflowException;
import java.awt.Component;
import com.intellij.codeInspection.InspectionProfileEntry;
import com.intellij.util.ui.CheckBox;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import com.intellij.openapi.ui.VerticalFlowLayout;
import javax.swing.JComponent;
import java.util.Map;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.util.Key;

public class OCDFAInspection extends OCInspections.DataFlowAnalysis
{
    private static final Boolean ENABLE_RML_PROFILING;
    private static final Boolean ENABLE_BDD_PROFILING;
    private static final Boolean ENABLE_RELATIONS_PROFILING;
    public static final int TIME_LIMIT = 5000;
    public static final Key<Long> DFA_TIME_LIMIT;
    private ProblemsHolder myProblemsHolder;
    private Map<Object, Object> myTestOptions;
    public boolean enableConstantCondition;
    public boolean enableIncompatibleCast;
    public boolean enableNullDereference;
    public boolean enableUnrecognizedSelector;
    public boolean enableUnreachableCode;
    
    public OCDFAInspection() {
        this.enableConstantCondition = true;
        this.enableIncompatibleCast = true;
        this.enableNullDereference = true;
        this.enableUnrecognizedSelector = true;
        this.enableUnreachableCode = true;
    }
    
    public OCDFAInspection(final ProblemsHolder myProblemsHolder, final Map<Object, Object> myTestOptions) {
        this.enableConstantCondition = true;
        this.enableIncompatibleCast = true;
        this.enableNullDereference = true;
        this.enableUnrecognizedSelector = true;
        this.enableUnreachableCode = true;
        this.myProblemsHolder = myProblemsHolder;
        this.myTestOptions = myTestOptions;
    }
    
    public JComponent createOptionsPanel() {
        final JPanel panel = new JPanel((LayoutManager)new VerticalFlowLayout());
        panel.add((Component)new CheckBox("Constant conditions", (InspectionProfileEntry)this, "enableConstantCondition"));
        panel.add((Component)new CheckBox("Incompatible casts", (InspectionProfileEntry)this, "enableIncompatibleCast"));
        panel.add((Component)new CheckBox("Null dereferences", (InspectionProfileEntry)this, "enableNullDereference"));
        panel.add((Component)new CheckBox("Unrecognized selectors", (InspectionProfileEntry)this, "enableUnrecognizedSelector"));
        panel.add((Component)new CheckBox("Unreachable code", (InspectionProfileEntry)this, "enableUnreachableCode"));
        return panel;
    }
    
    @Nls
    @NotNull
    @Override
    public String getDisplayName() {
        String s;
        try {
            s = "Context-sensitive analysis";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCDFAInspection", "getDisplayName"));
            }
        }
        catch (BDDMemoryOverflowException ex) {
            throw b(ex);
        }
        return s;
    }
    
    public void inspectionFinished(@NotNull final LocalInspectionToolSession localInspectionToolSession, @NotNull ProblemsHolder myProblemsHolder) {
        try {
            if (localInspectionToolSession == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "session", "com/jetbrains/cidr/lang/inspections/OCDFAInspection", "inspectionFinished"));
            }
        }
        catch (InterruptedException ex) {
            throw b(ex);
        }
        try {
            if (myProblemsHolder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "problemsHolder", "com/jetbrains/cidr/lang/inspections/OCDFAInspection", "inspectionFinished"));
            }
        }
        catch (InterruptedException ex2) {
            throw b(ex2);
        }
        try {
            if (!(localInspectionToolSession.getFile() instanceof OCFile)) {
                return;
            }
        }
        catch (InterruptedException ex3) {
            throw b(ex3);
        }
        final OCFile ocFile = (OCFile)localInspectionToolSession.getFile();
        final HashMap<Class<ProfileManager>, Boolean> hashMap = new HashMap<Class<ProfileManager>, Boolean>();
        try {
            if (this.myTestOptions != null) {
                hashMap.putAll((Map<?, ?>)this.myTestOptions);
            }
        }
        catch (InterruptedException ex4) {
            throw b(ex4);
        }
        try {
            if (OCDFAInspection.ENABLE_RML_PROFILING) {
                hashMap.put(ProfileManager.class, true);
            }
        }
        catch (InterruptedException ex5) {
            throw b(ex5);
        }
        if (this.myProblemsHolder != null) {
            myProblemsHolder = this.myProblemsHolder;
        }
        synchronized (OCDFAInspection.class) {
            try {
                ocFile.pushAnnotationSession(new AnnotationSession((PsiFile)ocFile));
                final Ref create = Ref.create();
                final Ref create2 = Ref.create();
                final Long n = (Long)ocFile.getUserData((Key)OCDFAInspection.DFA_TIME_LIMIT);
                long longValue = 0L;
                Label_0255: {
                    try {
                        if (n != null) {
                            longValue = n;
                            break Label_0255;
                        }
                    }
                    catch (InterruptedException ex6) {
                        throw b(ex6);
                    }
                    longValue = 5000L;
                }
                final OCDFAUtils.WorkingTimeMeasurer workingTimeMeasurer = new OCDFAUtils.WorkingTimeMeasurer(longValue);
                try {
                    a(ocFile, new HashMap<Object, Object>(hashMap), true, workingTimeMeasurer, (Ref<Result>)create);
                    a(ocFile, new HashMap<Object, Object>(hashMap), false, workingTimeMeasurer, (Ref<Result>)create2);
                    try {
                        synchronized (Result.class) {
                        Label_0337:
                            while (true) {
                                Label_0325: {
                                    try {
                                        if (create.isNull()) {
                                            break Label_0325;
                                        }
                                        final Ref<Result> ref = (Ref<Result>)create2;
                                        final boolean b = ref.isNull();
                                        if (b) {
                                            break Label_0325;
                                        }
                                        break Label_0337;
                                    }
                                    catch (InterruptedException ex7) {
                                        throw b(ex7);
                                    }
                                    try {
                                        final Ref<Result> ref = (Ref<Result>)create2;
                                        final boolean b = ref.isNull();
                                        if (b) {
                                            Result.class.wait();
                                            continue;
                                        }
                                    }
                                    catch (InterruptedException ex8) {
                                        throw b(ex8);
                                    }
                                }
                                break;
                            }
                        }
                    }
                    catch (InterruptedException ex19) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                finally {
                    Disposer.dispose((Disposable)workingTimeMeasurer);
                }
                ManagerInstancesProvider managerInstancesProvider;
                OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder;
                if (((Result)create.get()).isFinishedSuccessfully()) {
                    managerInstancesProvider = ((Result)create.get()).getProvider();
                    ocContextSensitiveControlFlowBuilder = ((Result)create.get()).getFlowBuilder();
                }
                else {
                    if (!((Result)create2.get()).isFinishedSuccessfully()) {
                        this.a(myProblemsHolder, ocFile, "File is too complex to perform data-flow analysis");
                        return;
                    }
                    managerInstancesProvider = ((Result)create2.get()).getProvider();
                    ocContextSensitiveControlFlowBuilder = ((Result)create2.get()).getFlowBuilder();
                    this.a(myProblemsHolder, ocFile, "File is too complex to perform context-sensitive data-flow analysis");
                }
                if (this.enableConstantCondition) {
                    final HashSet<PsiElement> set = new HashSet<PsiElement>();
                    a(set, managerInstancesProvider, (PsiFile)ocFile, "AlwaysTrueStatement");
                    a(set, managerInstancesProvider, (PsiFile)ocFile, "AlwaysFalseStatement");
                    a(set, managerInstancesProvider, (PsiFile)ocFile, "AlwaysTrueWhenReachedStatement");
                    a(set, managerInstancesProvider, (PsiFile)ocFile, "AlwaysFalseWhenReachedStatement");
                    final Set<PsiElement> a = a(set);
                    this.a(myProblemsHolder, managerInstancesProvider, (PsiFile)ocFile, a, "AlwaysTrueStatement", "Condition is always true", a(ocFile, true));
                    this.a(myProblemsHolder, managerInstancesProvider, (PsiFile)ocFile, a, "AlwaysFalseStatement", "Condition is always false", a(ocFile, false));
                    this.a(myProblemsHolder, managerInstancesProvider, (PsiFile)ocFile, a, "AlwaysTrueWhenReachedStatement", "Condition is always true when reached", a(ocFile, true));
                    this.a(myProblemsHolder, managerInstancesProvider, (PsiFile)ocFile, a, "AlwaysFalseWhenReachedStatement", "Condition is always false when reached", a(ocFile, false));
                }
                try {
                    if (this.enableIncompatibleCast) {
                        this.a(myProblemsHolder, managerInstancesProvider, (PsiFile)ocFile, null, "IllegalCastExpression", "Casting to probably incompatible type", null);
                    }
                }
                catch (InterruptedException ex9) {
                    throw b(ex9);
                }
                Label_0836: {
                    OCDFAInspection ocdfaInspection2 = null;
                    ProblemsHolder problemsHolder2 = null;
                    ManagerInstancesProvider managerInstancesProvider3 = null;
                    OCFile ocFile4 = null;
                    Set<PsiElement> set3 = null;
                    String s4 = null;
                    StringBuilder sb4 = null;
                    String s6 = null;
                    Label_0822: {
                        Label_0811: {
                            Label_0763: {
                                OCDFAInspection ocdfaInspection = null;
                                ProblemsHolder problemsHolder = null;
                                ManagerInstancesProvider managerInstancesProvider2 = null;
                                OCFile ocFile2 = null;
                                Set<PsiElement> set2 = null;
                                String s = null;
                                StringBuilder sb2 = null;
                                String s3 = null;
                                Label_0749: {
                                    Label_0738: {
                                        try {
                                            if (!this.enableNullDereference) {
                                                break Label_0763;
                                            }
                                            ocdfaInspection = this;
                                            problemsHolder = myProblemsHolder;
                                            managerInstancesProvider2 = managerInstancesProvider;
                                            ocFile2 = ocFile;
                                            set2 = null;
                                            s = "NullDereference";
                                            final StringBuilder sb = new StringBuilder();
                                            final String s2 = "Reference may be ";
                                            sb2 = sb.append(s2);
                                            final OCFile ocFile3 = ocFile;
                                            final OCLanguageKind ocLanguageKind = ocFile3.getKind();
                                            final boolean b2 = ocLanguageKind.isObjC();
                                            if (b2) {
                                                break Label_0738;
                                            }
                                            break Label_0738;
                                        }
                                        catch (InterruptedException ex10) {
                                            throw b(ex10);
                                        }
                                        try {
                                            ocdfaInspection = this;
                                            problemsHolder = myProblemsHolder;
                                            managerInstancesProvider2 = managerInstancesProvider;
                                            ocFile2 = ocFile;
                                            set2 = null;
                                            s = "NullDereference";
                                            final StringBuilder sb = new StringBuilder();
                                            final String s2 = "Reference may be ";
                                            sb2 = sb.append(s2);
                                            final OCFile ocFile3 = ocFile;
                                            final OCLanguageKind ocLanguageKind = ocFile3.getKind();
                                            final boolean b2 = ocLanguageKind.isObjC();
                                            if (b2) {
                                                s3 = "nil";
                                                break Label_0749;
                                            }
                                        }
                                        catch (InterruptedException ex11) {
                                            throw b(ex11);
                                        }
                                    }
                                    s3 = "null";
                                }
                                ocdfaInspection.a(problemsHolder, managerInstancesProvider2, (PsiFile)ocFile2, set2, s, sb2.append(s3).toString(), a(ocContextSensitiveControlFlowBuilder));
                                try {
                                    if (!this.enableNullDereference) {
                                        break Label_0836;
                                    }
                                    ocdfaInspection2 = this;
                                    problemsHolder2 = myProblemsHolder;
                                    managerInstancesProvider3 = managerInstancesProvider;
                                    ocFile4 = ocFile;
                                    set3 = null;
                                    s4 = "NullReceiver";
                                    final StringBuilder sb3 = new StringBuilder();
                                    final String s5 = "Receiver is always ";
                                    sb4 = sb3.append(s5);
                                    final OCFile ocFile5 = ocFile;
                                    final OCLanguageKind ocLanguageKind2 = ocFile5.getKind();
                                    final boolean b3 = ocLanguageKind2.isObjC();
                                    if (b3) {
                                        break Label_0811;
                                    }
                                    break Label_0811;
                                }
                                catch (InterruptedException ex12) {
                                    throw b(ex12);
                                }
                            }
                            try {
                                ocdfaInspection2 = this;
                                problemsHolder2 = myProblemsHolder;
                                managerInstancesProvider3 = managerInstancesProvider;
                                ocFile4 = ocFile;
                                set3 = null;
                                s4 = "NullReceiver";
                                final StringBuilder sb3 = new StringBuilder();
                                final String s5 = "Receiver is always ";
                                sb4 = sb3.append(s5);
                                final OCFile ocFile5 = ocFile;
                                final OCLanguageKind ocLanguageKind2 = ocFile5.getKind();
                                final boolean b3 = ocLanguageKind2.isObjC();
                                if (b3) {
                                    s6 = "nil";
                                    break Label_0822;
                                }
                            }
                            catch (InterruptedException ex13) {
                                throw b(ex13);
                            }
                        }
                        s6 = "null";
                    }
                    ocdfaInspection2.a(problemsHolder2, managerInstancesProvider3, (PsiFile)ocFile4, set3, s4, sb4.append(s6).toString(), a(ocContextSensitiveControlFlowBuilder));
                    try {
                        if (this.enableUnrecognizedSelector) {
                            this.a(myProblemsHolder, managerInstancesProvider, (PsiFile)ocFile, null, "NoResponder", "Statement may fail with \"unrecognized selector\"", null);
                        }
                    }
                    catch (InterruptedException ex14) {
                        throw b(ex14);
                    }
                }
                Label_0919: {
                    Label_0898: {
                        try {
                            if (!this.enableUnreachableCode) {
                                break Label_0919;
                            }
                            final OCFile ocFile6 = ocFile;
                            final Project project = ocFile6.getProject();
                            final Key<Boolean> key = OCFile.DFA_UNREACHABLE_CODE;
                            final Object o = project.getUserData((Key)key);
                            final Boolean b4 = Boolean.FALSE;
                            if (o != b4) {
                                break Label_0898;
                            }
                            break Label_0919;
                        }
                        catch (InterruptedException ex15) {
                            throw b(ex15);
                        }
                        try {
                            final OCFile ocFile6 = ocFile;
                            final Project project = ocFile6.getProject();
                            final Key<Boolean> key = OCFile.DFA_UNREACHABLE_CODE;
                            final Object o = project.getUserData((Key)key);
                            final Boolean b4 = Boolean.FALSE;
                            if (o != b4) {
                                this.a(myProblemsHolder, managerInstancesProvider, (PsiFile)ocFile, ocContextSensitiveControlFlowBuilder, "UnreachableNode", "Unreachable code");
                            }
                        }
                        catch (InterruptedException ex16) {
                            throw b(ex16);
                        }
                    }
                    try {
                        if (OCDFAInspection.ENABLE_BDD_PROFILING) {
                            managerInstancesProvider.getUIInstancesProvider().getConsoleCommandProcessor().run(8, new String[0], null);
                        }
                    }
                    catch (InterruptedException ex17) {
                        throw b(ex17);
                    }
                }
                try {
                    if (OCDFAInspection.ENABLE_RELATIONS_PROFILING) {
                        managerInstancesProvider.getUIInstancesProvider().getConsoleCommandProcessor().run(9, new String[0], null);
                    }
                }
                catch (InterruptedException ex18) {
                    throw b(ex18);
                }
            }
            finally {
                OCSourceGliderComponent.getInstance().clearVisitorRunners();
                ocFile.popAnnotationSession();
            }
        }
    }
    
    private static void a(final OCFile ocFile, final Map<Object, Object> map, final boolean b, final OCDFAUtils.WorkingTimeMeasurer workingTimeMeasurer, final Ref<Result> ref) {
        final OCSourceGliderComponent instance = OCSourceGliderComponent.getInstance();
        int n = 0;
        Label_0018: {
            try {
                if (b) {
                    n = 0;
                    break Label_0018;
                }
            }
            catch (BDDMemoryOverflowException ex) {
                throw b(ex);
            }
            n = 1;
        }
        final int n2 = n;
        final ManagerInstancesProvider provider = instance.getProvider(n2);
        final Ref create = Ref.create();
        final Ref ref2;
        instance.addVisitorRunner(n2, (map2, threadCallback) -> {
            ref2.set((Object)new OCContextSensitiveControlFlowBuilder(provider.getSymbolTable(), (relationSignature, array) -> instance.getVisitor(n2).addTuple(relationSignature, array)));
            ocFile.accept((PsiElementVisitor)new OCRecursiveVisitor() {
                final /* synthetic */ Ref val$flowBuilder;
                final /* synthetic */ Map val$options1;
                final /* synthetic */ ThreadCallback val$threadCallback;
                
                @Override
                public void visitCallable(final OCCallable ocCallable) {
                    ((OCContextSensitiveControlFlowBuilder)this.val$flowBuilder.get()).processCallable(ocCallable, this.val$options1, this.val$threadCallback);
                }
            });
            return;
        });
        provider.init();
        map.put("CONTEXT_SENSITIVITY", b);
        final List<Task> relationBuildPlan = provider.getHypoteticRepository().getRelationBuildPlan("AlwaysTrueStatement", map);
        if (relationBuildPlan != null) {
            for (final Task task : relationBuildPlan) {
                if (task.getName().startsWith("Running")) {
                    final Task task2;
                    boolean a;
                    final ManagerInstancesProvider managerInstancesProvider;
                    final Ref ref3;
                    final Runnable runnable = () -> ProgressManager.getInstance().executeProcessUnderProgress(() -> {
                        try {
                            a = a(ocFile, workingTimeMeasurer, b, task2);
                        }
                        finally {
                            synchronized (Result.class) {
                                ref.set((Object)new Result(managerInstancesProvider, (OCContextSensitiveControlFlowBuilder)ref3.get(), a));
                                Result.class.notifyAll();
                            }
                        }
                    }, a());
                    try {
                        if (b) {
                            ApplicationManager.getApplication().executeOnPooledThread(runnable);
                            return;
                        }
                    }
                    catch (BDDMemoryOverflowException ex2) {
                        throw b(ex2);
                    }
                    runnable.run();
                    return;
                }
                try {
                    if (!a(ocFile, workingTimeMeasurer, b, task)) {
                        ref.set((Object)new Result(provider, (OCContextSensitiveControlFlowBuilder)create.get(), false));
                        return;
                    }
                    continue;
                }
                catch (BDDMemoryOverflowException ex3) {
                    throw b(ex3);
                }
            }
        }
        ref.set((Object)new Result(provider, (OCContextSensitiveControlFlowBuilder)create.get(), false));
    }
    
    private static ProgressIndicator a() {
        final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
        try {
            if (progressIndicator != null) {
                final AbstractProgressIndicatorBase abstractProgressIndicatorBase = new SensitiveProgressWrapper(progressIndicator);
                return (ProgressIndicator)abstractProgressIndicatorBase;
            }
        }
        catch (BDDMemoryOverflowException ex) {
            throw b(ex);
        }
        final AbstractProgressIndicatorBase abstractProgressIndicatorBase = new ProgressIndicatorBase();
        return (ProgressIndicator)abstractProgressIndicatorBase;
    }
    
    private static boolean a(final OCFile ocFile, final OCDFAUtils.WorkingTimeMeasurer workingTimeMeasurer, final boolean b, final Task task) {
        try {
            task.run(new DummyThreadCallback() {
                @Override
                public boolean checkCancelled() {
                    OCDFAUtils.DFAException ex = null;
                    StringBuilder sb2 = null;
                    String s2 = null;
                    Label_0054: {
                        Label_0043: {
                            try {
                                ProgressManager.checkCanceled();
                                if (!workingTimeMeasurer.isTimeOver()) {
                                    return false;
                                }
                                ex = new(com.jetbrains.cidr.lang.dfa.contextSensitive.OCDFAUtils.DFAException.class);
                                final StringBuilder sb = new StringBuilder();
                                final String s = "DFA time is over";
                                sb2 = sb.append(s);
                                final DummyThreadCallback dummyThreadCallback = this;
                                final boolean b = b;
                                if (b) {
                                    break Label_0043;
                                }
                                break Label_0043;
                            }
                            catch (OCDFAUtils.DFAException ex2) {
                                throw b(ex2);
                            }
                            try {
                                ex = new(com.jetbrains.cidr.lang.dfa.contextSensitive.OCDFAUtils.DFAException.class);
                                final StringBuilder sb = new StringBuilder();
                                final String s = "DFA time is over";
                                sb2 = sb.append(s);
                                final DummyThreadCallback dummyThreadCallback = this;
                                final boolean b = b;
                                if (b) {
                                    s2 = " (context-sensitive)";
                                    break Label_0054;
                                }
                            }
                            catch (OCDFAUtils.DFAException ex3) {
                                throw b(ex3);
                            }
                        }
                        s2 = "";
                    }
                    new OCDFAUtils.DFAException(sb2.append(s2).toString());
                    throw ex;
                }
                
                private static OCDFAUtils.DFAException b(final OCDFAUtils.DFAException ex) {
                    return ex;
                }
            });
        }
        catch (BDDMemoryOverflowException ex) {
            OCLog.LOG.warn(ocFile.getName() + ": " + ex.getMessage());
            return false;
        }
        catch (OCDFAUtils.DFAException ex2) {
            final String string = ocFile.getName() + ": " + ex2.getMessage();
            final Application application = ApplicationManager.getApplication();
            Label_0126: {
                try {
                    if (application.isUnitTestMode()) {
                        break Label_0126;
                    }
                    final Application application2 = application;
                    final boolean b2 = application2.isInternal();
                    if (b2) {
                        break Label_0126;
                    }
                    break Label_0126;
                }
                catch (BDDMemoryOverflowException ex3) {
                    throw b(ex3);
                }
                try {
                    final Application application2 = application;
                    final boolean b2 = application2.isInternal();
                    if (b2) {
                        OCLog.LOG.warn(string);
                        return false;
                    }
                }
                catch (BDDMemoryOverflowException ex4) {
                    throw b(ex4);
                }
            }
            OCLog.LOG.info(string);
            return false;
        }
        return true;
    }
    
    private void a(final ProblemsHolder problemsHolder, final OCFile ocFile, final String s) {
        final Ref ref = new Ref();
        try {
            ocFile.accept((PsiElementVisitor)new OCRecursiveVisitor() {
                @Override
                public void visitClassDeclaration(final OCClassDeclaration ocClassDeclaration) {
                    if (ref.isNull()) {
                        ref.set((Object)ocClassDeclaration.getNameIdentifier());
                    }
                }
                
                @Override
                public void visitStruct(final OCStruct ocStruct) {
                    if (ref.isNull()) {
                        ref.set((Object)ocStruct.getNameIdentifier());
                    }
                }
                
                @Override
                public void visitFunctionDefinition(final OCFunctionDefinition ocFunctionDefinition) {
                    if (ref.isNull()) {
                        ref.set((Object)ocFunctionDefinition.getNameIdentifier());
                    }
                }
            });
            if (!ref.isNull()) {
                this.registerProblem(problemsHolder, null, null, problemsHolder.isOnTheFly(), (PsiElement)ref.get(), s, "CIDR", ProblemHighlightType.WEAK_WARNING, new IntentionAction[0]);
            }
        }
        catch (BDDMemoryOverflowException ex) {
            throw b(ex);
        }
    }
    
    private void a(final ProblemsHolder problemsHolder, final ManagerInstancesProvider managerInstancesProvider, final PsiFile psiFile, @Nullable final Set<PsiElement> set, final String s, final String s2, @Nullable final ElementFix elementFix) {
        final HashSet<PsiElement> set2 = new HashSet<PsiElement>();
        try {
            if (!a(set2, managerInstancesProvider, psiFile, s)) {
                return;
            }
        }
        catch (BDDMemoryOverflowException ex) {
            throw b(ex);
        }
        try {
            if (set != null) {
                set2.removeAll(set);
            }
        }
        catch (BDDMemoryOverflowException ex2) {
            throw b(ex2);
        }
        for (final PsiElement psiElement : set2) {
            Object o = null;
            Label_0103: {
                try {
                    if (elementFix != null) {
                        o = elementFix.getFixes(psiElement);
                        break Label_0103;
                    }
                }
                catch (BDDMemoryOverflowException ex3) {
                    throw b(ex3);
                }
                o = Collections.emptyList();
            }
            final Object o2 = o;
            this.registerProblem(problemsHolder, null, null, problemsHolder.isOnTheFly(), psiElement, s2, "CIDR", ProblemHighlightType.GENERIC_ERROR_OR_WARNING, (IntentionAction[])((List)o2).toArray(new IntentionAction[((List)o2).size()]));
        }
    }
    
    private static boolean a(final Set<PsiElement> set, final ManagerInstancesProvider managerInstancesProvider, final PsiFile psiFile, final String s) {
        try {
            if (!managerInstancesProvider.getRelationsRepository().containsRelation(s)) {
                return false;
            }
        }
        catch (BDDMemoryOverflowException ex) {
            throw b(ex);
        }
        for (final Attribute[] array : managerInstancesProvider.getRelationsRepository().getRelation(s).getAttributes(managerInstancesProvider.getSymbolTable())) {
            final int srcPos = array[0].getSrcPos();
            final OCElement ocElement = OCCodeInsightUtil.findElementAtRange(psiFile, srcPos, srcPos + array[0].getSrcLength(), OCElement.class, true);
            try {
                if (ocElement != null) {
                    set.add((PsiElement)ocElement);
                }
            }
            catch (BDDMemoryOverflowException ex2) {
                throw b(ex2);
            }
        }
        return true;
    }
    
    private static Set<PsiElement> a(final Set<PsiElement> set) {
        final HashSet<PsiElement> set2 = new HashSet<PsiElement>();
        final Iterator<PsiElement> iterator = set.iterator();
        while (iterator.hasNext()) {
            for (PsiElement psiElement = iterator.next().getParent(); psiElement != null; psiElement = psiElement.getParent()) {
                set2.add(psiElement);
            }
        }
        return set2;
    }
    
    private void a(final ProblemsHolder problemsHolder, final ManagerInstancesProvider managerInstancesProvider, final PsiFile psiFile, final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder, final String s, final String s2) {
        try {
            if (!managerInstancesProvider.getRelationsRepository().containsRelation(s)) {
                return;
            }
        }
        catch (BDDMemoryOverflowException ex) {
            throw b(ex);
        }
        final IRelation relation = managerInstancesProvider.getRelationsRepository().getRelation(s);
        final ArrayList<OCElementsRange> list = new ArrayList<OCElementsRange>();
        final Iterator<OCControlFlowGraph> iterator = ocContextSensitiveControlFlowBuilder.getAllControlFlowGraphs().iterator();
        while (iterator.hasNext()) {
            final OCUnreachableCodeFinder ocUnreachableCodeFinder = new OCUnreachableCodeFinder(iterator.next());
            ocUnreachableCodeFinder.process();
            list.addAll((Collection<?>)ocUnreachableCodeFinder.getUnreachableRanges(false));
        }
        for (final Attribute[] array : relation.getAttributes(managerInstancesProvider.getSymbolTable())) {
            final int srcPos = array[0].getSrcPos();
            final OCCallable ocCallable = OCCodeInsightUtil.findElementAtRange(psiFile, srcPos, srcPos + array[0].getSrcLength(), OCCallable.class, true);
            if (ocCallable != null) {
                final OCElementsRange range = ocContextSensitiveControlFlowBuilder.getRange(ocCallable, Integer.decode(array[1].getKey()));
                try {
                    if (range != null) {
                        list.add(range);
                    }
                }
                catch (BDDMemoryOverflowException ex2) {
                    throw b(ex2);
                }
            }
        }
        for (final OCElementsRange ocElementsRange : OCElementsRange.mergeRanges(list)) {
            PsiElement psiElement = ocElementsRange.getFirstElement();
            PsiElement psiElement2 = ocElementsRange.getLastElement();
            while (psiElement instanceof OCMacroForeignLeafElement) {
                psiElement = psiElement.getParent();
            }
            while (psiElement2 instanceof OCMacroForeignLeafElement) {
                psiElement2 = psiElement.getParent();
            }
            final OCElementsRange ocElementsRange2 = new OCElementsRange(psiElement, psiElement2);
            final OCElementsRange trim = ocElementsRange2.trim(TokenSet.orSet(new TokenSet[] { OCElementsRange.NON_IMPORTANT_TOKENS, TokenSet.create(new IElementType[] { OCTokenTypes.SEMICOLON }) }));
            Label_0388: {
                try {
                    if (trim == null) {
                        continue;
                    }
                    final OCElementsRange ocElementsRange3 = trim;
                    final boolean b = ocElementsRange3.isEmpty();
                    if (!b) {
                        break Label_0388;
                    }
                    continue;
                }
                catch (BDDMemoryOverflowException ex3) {
                    throw b(ex3);
                }
                try {
                    final OCElementsRange ocElementsRange3 = trim;
                    final boolean b = ocElementsRange3.isEmpty();
                    if (b) {
                        continue;
                    }
                    this.registerProblem(problemsHolder, null, null, problemsHolder.isOnTheFly(), ocElementsRange2, s2, "CIDR", ProblemHighlightType.LIKE_UNUSED_SYMBOL, new IntentionAction[0]);
                }
                catch (BDDMemoryOverflowException ex4) {
                    throw b(ex4);
                }
            }
        }
    }
    
    private static ElementFix a(final OCFile ocFile, final boolean b) {
        return new ElementFix() {
            @Override
            public List<IntentionAction> getFixes(final PsiElement psiElement) {
                final ArrayList<OCChangeElementIntentionAction> list = (ArrayList<OCChangeElementIntentionAction>)new ArrayList<IntentionAction>();
                final String value = OCIntType.getAppropriateBool(ocFile).getValue(b, psiElement);
                list.add((IntentionAction)new OCChangeElementIntentionAction(psiElement, (PsiElement)OCElementFactory.expressionFromText(value, psiElement, false), "Simplify condition to '" + value + "'"));
                final OCElement ocElement = (OCElement)PsiTreeUtil.getParentOfType(psiElement, new Class[] { OCIfStatement.class, OCLoopStatement.class, OCBinaryExpression.class });
                Object o = null;
                if (ocElement instanceof OCStatement) {
                    o = OCSimplifyInspection.simplifyStatementWithConstCondition(b, (OCStatement)ocElement);
                }
                else if (ocElement instanceof OCBinaryExpression) {
                    o = OCSimplifyInspection.simplifyLogicExpression(b, (OCBinaryExpression)ocElement, psiElement);
                }
                if (o != null) {
                    list.add(OCSimplifyInspection.getSimplifyFix((PsiElement)ocElement, (PsiElement)o));
                }
                return (List<IntentionAction>)list;
            }
        };
    }
    
    private static ElementFix a(final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder) {
        return new ElementFix() {
            @Override
            public List<? extends IntentionAction> getFixes(final PsiElement psiElement) {
                final OCSymbol ocSymbol = (psiElement instanceof OCReferenceElement) ? ((OCReferenceElement)psiElement).resolveToSymbol() : null;
                if (ocSymbol != null) {
                    final OCCallable ocCallable = (OCCallable)PsiTreeUtil.getParentOfType(psiElement, (Class)OCCallable.class);
                    final OCControlFlowGraph ocControlFlowGraph = (ocCallable != null) ? ocContextSensitiveControlFlowBuilder.getControlFlowGraph(ocCallable) : null;
                    if (ocControlFlowGraph != null) {
                        final OCLocalDefinitionsSearcher ocLocalDefinitionsSearcher = new OCLocalDefinitionsSearcher(ocControlFlowGraph, ocSymbol, psiElement.getParent(), false, true, true);
                        ocLocalDefinitionsSearcher.process();
                        final Collection<OCInstruction> instructionsOfKind = ocLocalDefinitionsSearcher.getInstructionsOfKind(OCInstruction.InstructionKind.DECLARATOR);
                        if (!instructionsOfKind.isEmpty()) {
                            final OCInstruction ocInstruction = instructionsOfKind.iterator().next();
                            if (ocInstruction.getRValue() instanceof OCDeclarator) {
                                return Collections.singletonList((IntentionAction)new OCAddInitializerIntentionAction((OCDeclarator)ocInstruction.getRValue(), ocSymbol));
                            }
                        }
                    }
                }
                return Collections.emptyList();
            }
        };
    }
    
    static {
        ENABLE_RML_PROFILING = false;
        ENABLE_BDD_PROFILING = false;
        ENABLE_RELATIONS_PROFILING = false;
        DFA_TIME_LIMIT = Key.create("DFA_TIME_LIMIT");
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    private static class Result
    {
        private ManagerInstancesProvider myProvider;
        private OCContextSensitiveControlFlowBuilder myFlowBuilder;
        private boolean myFinishedSuccessfully;
        
        public Result(final ManagerInstancesProvider myProvider, final OCContextSensitiveControlFlowBuilder myFlowBuilder, final boolean myFinishedSuccessfully) {
            this.myProvider = myProvider;
            this.myFlowBuilder = myFlowBuilder;
            this.myFinishedSuccessfully = myFinishedSuccessfully;
        }
        
        public ManagerInstancesProvider getProvider() {
            return this.myProvider;
        }
        
        public OCContextSensitiveControlFlowBuilder getFlowBuilder() {
            return this.myFlowBuilder;
        }
        
        public boolean isFinishedSuccessfully() {
            return this.myFinishedSuccessfully;
        }
    }
    
    private interface ElementFix
    {
        List<? extends IntentionAction> getFixes(final PsiElement p0);
    }
}
