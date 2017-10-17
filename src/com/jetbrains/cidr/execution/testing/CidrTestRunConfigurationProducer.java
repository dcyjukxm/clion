// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import com.intellij.execution.testframework.sm.runner.SMTestProxy;
import com.intellij.execution.PsiLocation;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.util.text.StringUtil;
import java.util.LinkedHashSet;
import com.intellij.util.Function;
import com.intellij.psi.search.GlobalSearchScope;
import javax.swing.tree.TreePath;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.execution.testframework.sm.runner.ui.SMTRunnerTestTreeView;
import com.intellij.execution.Location;
import com.intellij.util.containers.ContainerUtil;
import java.util.Iterator;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.intellij.openapi.progress.ProgressIndicator;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import java.util.Collection;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceManager;
import java.util.Collections;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.execution.CidrBuildConfigurationHelper;
import com.jetbrains.cidr.execution.BuildTargetAndConfigurationData;
import com.jetbrains.cidr.execution.ExecutableData;
import com.jetbrains.cidr.execution.BuildTargetData;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Ref;
import com.intellij.execution.actions.ConfigurationContext;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.openapi.util.NotNullLazyValue;
import com.jetbrains.cidr.lang.OCTestFramework;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.jetbrains.cidr.execution.CidrRunConfiguration;
import com.jetbrains.cidr.execution.CidrBuildTarget;
import com.jetbrains.cidr.execution.CidrBuildConfiguration;

public abstract class CidrTestRunConfigurationProducer<BC extends CidrBuildConfiguration, TARGET extends CidrBuildTarget<BC>, CONFIGURATION extends CidrRunConfiguration<BC, TARGET>, TEST_OBJECT> extends RunConfigurationProducer<CONFIGURATION>
{
    private final Class<? extends OCTestFramework> myFrameworkClass;
    private final NotNullLazyValue<OCTestFramework> myTestFramework;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    protected CidrTestRunConfigurationProducer(@NotNull final ConfigurationType configurationType, @NotNull final Class<? extends OCTestFramework> myFrameworkClass) {
        if (configurationType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurationType", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "<init>"));
        }
        if (myFrameworkClass == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testFrameworkClass", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "<init>"));
        }
        super(configurationType);
        this.myTestFramework = new NotNullLazyValue<OCTestFramework>() {
            @NotNull
            @Contract(pure = true)
            protected OCTestFramework compute() {
                OCTestFramework extension;
                try {
                    extension = CidrTestFrameworkBase.findExtension(CidrTestRunConfigurationProducer.this.myFrameworkClass);
                    if (extension == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer$1", "compute"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return extension;
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        };
        this.myFrameworkClass = myFrameworkClass;
    }
    
    protected boolean setupConfigurationFromContext(@NotNull final CONFIGURATION configuration, @NotNull final ConfigurationContext configurationContext, @NotNull final Ref<PsiElement> ref) {
        try {
            if (configuration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "setupConfigurationFromContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (configurationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "setupConfigurationFromContext"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceElement", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "setupConfigurationFromContext"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final List<TEST_OBJECT> testObjects = this.findTestObjects(configurationContext);
        try {
            if (testObjects.isEmpty()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final CidrScopeInfo determineScope = this.determineScope(testObjects);
        try {
            if (determineScope == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        final TEST_OBJECT value = testObjects.get(0);
        this.setupTestTarget(configuration, this.a(value));
        this.setupConfiguration(configuration, determineScope);
        ref.set((Object)this.getElement(value));
        return true;
    }
    
    public void setupTestTarget(@NotNull final CONFIGURATION configuration, @NotNull final List<TARGET> list) {
        try {
            if (configuration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newConfigurationWithTemplateDefaults", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "setupTestTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetsWithContext", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "setupTestTarget"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (list.isEmpty()) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        CidrBuildTarget<BC> target = null;
        final CidrBuildConfigurationHelper<BC, TARGET> helper = configuration.getHelper();
        BuildTargetAndConfigurationData targetAndConfigurationData = this.extractTargetAndConfigurationFromCurrentSelection(configuration.getProject(), helper, list);
        if (targetAndConfigurationData == null) {
            targetAndConfigurationData = helper.findSimilarValidInTargets(configuration.getTargetAndConfigurationData(), list);
        }
        Label_0171: {
            try {
                if (targetAndConfigurationData == null || targetAndConfigurationData.target == null) {
                    break Label_0171;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            target = this.findTarget((List<CidrBuildTarget<BC>>)list, targetAndConfigurationData.target);
        }
        if (target == null) {
            targetAndConfigurationData = null;
            target = list.get(0);
        }
        Label_0206: {
            try {
                if (CidrTestRunConfigurationProducer.$assertionsDisabled) {
                    break Label_0206;
                }
                final CidrBuildTarget<BC> cidrBuildTarget = target;
                if (cidrBuildTarget == null) {
                    break Label_0206;
                }
                break Label_0206;
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                final CidrBuildTarget<BC> cidrBuildTarget = target;
                if (cidrBuildTarget == null) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        final BuildTargetData buildTargetData = new BuildTargetData(target);
        this.setExecutableData(configuration, new ExecutableData(buildTargetData));
        if (targetAndConfigurationData == null) {
            final CidrBuildConfiguration defaultConfiguration = this.getDefaultConfiguration(configuration, (TARGET)target);
            BuildTargetData buildTargetData2 = null;
            String name = null;
            Label_0281: {
                try {
                    buildTargetData2 = buildTargetData;
                    if (defaultConfiguration == null) {
                        name = null;
                        break Label_0281;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
                name = defaultConfiguration.getName();
            }
            targetAndConfigurationData = new BuildTargetAndConfigurationData(buildTargetData2, name);
        }
        configuration.setTargetAndConfigurationData(targetAndConfigurationData);
    }
    
    @Nullable
    protected abstract BuildTargetAndConfigurationData extractTargetAndConfigurationFromCurrentSelection(@NotNull final Project p0, @NotNull final CidrBuildConfigurationHelper<BC, TARGET> p1, @NotNull final List<TARGET> p2);
    
    protected abstract void setExecutableData(@NotNull final CONFIGURATION p0, @NotNull final ExecutableData p1);
    
    @Nullable
    protected abstract BC getDefaultConfiguration(@NotNull final CONFIGURATION p0, @NotNull final TARGET p1);
    
    @Nullable
    protected abstract TARGET findTarget(@NotNull final List<TARGET> p0, @NotNull final BuildTargetData p1);
    
    @NotNull
    protected abstract PsiElement getElement(@NotNull final TEST_OBJECT p0);
    
    @NotNull
    private List<TARGET> a(@NotNull final TEST_OBJECT test_OBJECT) {
        try {
            if (test_OBJECT == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "object", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "getTargetsForTestObject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiFile containingFile = this.getElement(test_OBJECT).getContainingFile();
        Label_0108: {
            List<TARGET> list = null;
            Label_0073: {
                try {
                    if (containingFile != null) {
                        break Label_0108;
                    }
                    list = Collections.emptyList();
                    if (list == null) {
                        break Label_0073;
                    }
                    return list;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    list = Collections.emptyList();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "getTargetsForTestObject"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return list;
        }
        final ArrayList list2 = new ArrayList<Object>(OCWorkspaceManager.getWorkspace(containingFile.getProject()).getConfigurations());
        list2.retainAll(OCInclusionContextUtil.getAllBuildConfigurationsForFile(containingFile, null));
        final ArrayList list3 = new ArrayList<TARGET>(list2.size());
        final Iterator<OCResolveConfiguration> iterator = (Iterator<OCResolveConfiguration>)list2.iterator();
        while (iterator.hasNext()) {
            final CidrBuildTarget<BC> targetFromResolveConfiguration = this.getTargetFromResolveConfiguration(iterator.next());
            Label_0216: {
                try {
                    if (targetFromResolveConfiguration == null) {
                        continue;
                    }
                    final CidrTestRunConfigurationProducer cidrTestRunConfigurationProducer = this;
                    final CidrBuildTarget<BC> cidrBuildTarget = targetFromResolveConfiguration;
                    final boolean b = cidrTestRunConfigurationProducer.isTestTarget((TARGET)cidrBuildTarget);
                    if (b) {
                        break Label_0216;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    final CidrTestRunConfigurationProducer cidrTestRunConfigurationProducer = this;
                    final CidrBuildTarget<BC> cidrBuildTarget = targetFromResolveConfiguration;
                    final boolean b = cidrTestRunConfigurationProducer.isTestTarget((TARGET)cidrBuildTarget);
                    if (!b) {
                        continue;
                    }
                    list3.add((TARGET)targetFromResolveConfiguration);
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
        }
        List<Object> unmodifiableList;
        try {
            unmodifiableList = (List<Object>)Collections.unmodifiableList((List<? extends TARGET>)list3);
            if (unmodifiableList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "getTargetsForTestObject"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return (List<TARGET>)unmodifiableList;
    }
    
    @Nullable
    protected abstract TARGET getTargetFromResolveConfiguration(@NotNull final OCResolveConfiguration p0);
    
    protected abstract boolean isTestTarget(@NotNull final TARGET p0);
    
    @Nullable
    protected abstract CidrScopeInfo determineScope(@NotNull final List<TEST_OBJECT> p0);
    
    @Nullable
    protected CidrScopeInfo determineScope(@NotNull final List<TEST_OBJECT> list, final String s) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "objects", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "determineScope"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.foldScope(ContainerUtil.mapNotNull((Collection)list, o -> {
            try {
                if (this.containingFileCanUseFramework(this.getElement(o))) {
                    return this.determineScope(o);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return null;
        }), s);
    }
    
    @Nullable
    protected CidrScopeInfo determineScope(@NotNull final ConfigurationContext configurationContext) {
        try {
            if (configurationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "determineScope"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.determineScope(this.findTestObjects(configurationContext));
    }
    
    @Nullable
    protected abstract CidrScopeInfo determineScope(@NotNull final TEST_OBJECT p0);
    
    @NotNull
    protected List<TEST_OBJECT> findTestObjects(@NotNull final ConfigurationContext configurationContext) {
        try {
            if (configurationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "findTestObjects"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        List mapNotNull;
        try {
            mapNotNull = ContainerUtil.mapNotNull((Collection)this.getLocations(configurationContext), location -> {
                try {
                    if (this.containingFileCanUseFramework(location.getPsiElement())) {
                        return this.findTestObject(location);
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return null;
            });
            if (mapNotNull == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "findTestObjects"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (List<TEST_OBJECT>)mapNotNull;
    }
    
    @Nullable
    protected abstract TEST_OBJECT findTestObject(@NotNull final Location p0);
    
    public boolean isConfigurationFromContext(final CONFIGURATION configuration, final ConfigurationContext configurationContext) {
        final CidrTestRunConfigurationData a = this.a(configuration);
        try {
            if (a == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CidrScopeInfo determineScope = this.determineScope(configurationContext);
        try {
            if (determineScope == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a.equalTo(determineScope);
    }
    
    @Contract("null -> null")
    @Nullable
    private CidrTestRunConfigurationData a(final CONFIGURATION configuration) {
        try {
            if (configuration instanceof CidrTestRunConfiguration) {
                return ((CidrTestRunConfiguration)configuration).getTestData();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    protected void setupConfiguration(final CONFIGURATION configuration, final CidrScopeInfo cidrScopeInfo) {
        final CidrTestRunConfigurationData a = this.a(configuration);
        try {
            if (a == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String name = null;
        Label_0074: {
            try {
                a.setTestSuite(cidrScopeInfo.getSuite());
                a.setTestName(cidrScopeInfo.getTest());
                a.setTestPattern(cidrScopeInfo.getPattern());
                a.setTestMode(cidrScopeInfo.getMode());
                if (cidrScopeInfo.getName() == null) {
                    name = a.suggestedName(configuration.suggestNameForTarget());
                    break Label_0074;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            name = cidrScopeInfo.getName();
        }
        configuration.setName(name);
    }
    
    @NotNull
    protected List<Location> getLocations(@NotNull final ConfigurationContext configurationContext) {
        try {
            if (configurationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "getLocations"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final SMTRunnerTestTreeView smtRunnerTestTreeView = (SMTRunnerTestTreeView)SMTRunnerTestTreeView.SM_TEST_RUNNER_VIEW.getData(configurationContext.getDataContext());
        Label_0145: {
            if (smtRunnerTestTreeView != null) {
                final TreePath[] selectionPaths = smtRunnerTestTreeView.getSelectionPaths();
                try {
                    if (selectionPaths == null || selectionPaths.length <= 1) {
                        break Label_0145;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final GlobalSearchScope projectSourcesScope = OCSearchScope.getProjectSourcesScope(configurationContext.getProject());
                List mapNotNull;
                try {
                    mapNotNull = ContainerUtil.mapNotNull((Object[])selectionPaths, treePath -> {
                        try {
                            if (configurationContext == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "lambda$getLocations$2"));
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            if (smtRunnerTestTreeView.isPathSelected(treePath.getParentPath())) {
                                return null;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        final SMTestProxy selectedTest = smtRunnerTestTreeView.getSelectedTest(treePath);
                        try {
                            if (selectedTest == null) {
                                return null;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        return selectedTest.getLocation(configurationContext.getProject(), projectSourcesScope);
                    });
                    if (mapNotNull == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "getLocations"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                return (List<Location>)mapNotNull;
            }
        }
        Label_0226: {
            if (configurationContext.containsMultipleSelection()) {
                final PsiElement[] array = (PsiElement[])LangDataKeys.PSI_ELEMENT_ARRAY.getData(configurationContext.getDataContext());
                List list = null;
                Label_0191: {
                    try {
                        if (array == null) {
                            break Label_0226;
                        }
                        final PsiElement[] array2 = array;
                        final ConfigurationContext configurationContext2 = configurationContext;
                        final Function function = psiElement -> {
                            try {
                                if (configurationContext2 == null) {
                                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "lambda$getLocations$3"));
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            return (Location)new PsiLocation(configurationContext2.getProject(), psiElement);
                        };
                        list = ContainerUtil.mapNotNull((Object[])array2, function);
                        if (list == null) {
                            break Label_0191;
                        }
                        return (List<Location>)list;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final PsiElement[] array2 = array;
                        final ConfigurationContext configurationContext2 = configurationContext;
                        final Function function = psiElement -> {
                            try {
                                if (configurationContext2 == null) {
                                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "lambda$getLocations$3"));
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            return (Location)new PsiLocation(configurationContext2.getProject(), psiElement);
                        };
                        list = ContainerUtil.mapNotNull((Object[])array2, function);
                        if (list == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "getLocations"));
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                return (List<Location>)list;
            }
        }
        final Location location = configurationContext.getLocation();
        List<Location> singletonList = null;
        Label_0284: {
            List<Location> list2 = null;
            Label_0249: {
                try {
                    if (location != null) {
                        break Label_0284;
                    }
                    list2 = Collections.emptyList();
                    if (list2 == null) {
                        break Label_0249;
                    }
                    return list2;
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                try {
                    list2 = Collections.emptyList();
                    if (list2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "getLocations"));
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            return list2;
            try {
                singletonList = Collections.singletonList(location);
                if (singletonList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "getLocations"));
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        return singletonList;
    }
    
    @Nullable
    protected CidrScopeInfo foldScope(@NotNull final List<CidrScopeInfo> list, final String s) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "foldScope"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list.isEmpty()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (list.size() == 1) {
                return list.get(0);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final Ref ref = new Ref();
        final List map = ContainerUtil.map((Collection)list, cidrScopeInfo -> {
            try {
                ref.setIfNull((Object)this.a(cidrScopeInfo));
                if (cidrScopeInfo.getMode() == CidrScopeInfo.Mode.PATTERN) {
                    return cidrScopeInfo.getPattern();
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return this.createScopeElement(cidrScopeInfo).toString();
        });
        try {
            if (map.isEmpty()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        String string = (String)ref.get();
        if (map.size() > 1) {
            string += String.format(" and %d more", map.size() - 1);
        }
        return new CidrScopeInfo(null, null, string, StringUtil.join((Collection)new LinkedHashSet(map), s));
    }
    
    protected abstract CidrTestScopeElement createScopeElement(final CidrScopeInfo p0);
    
    private String a(final CidrScopeInfo cidrScopeInfo) {
        try {
            if (cidrScopeInfo.getName() != null) {
                return cidrScopeInfo.getName();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (cidrScopeInfo.getTest() == null) {
                return cidrScopeInfo.getSuite();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.formatTestName(cidrScopeInfo);
    }
    
    protected abstract String formatTestName(final CidrScopeInfo p0);
    
    @Contract(pure = true)
    protected boolean containingFileCanUseFramework(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationProducer", "containingFileCanUseFramework"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ((OCTestFramework)this.myTestFramework.getValue()).isAvailable(psiElement.getContainingFile());
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!CidrTestRunConfigurationProducer.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
