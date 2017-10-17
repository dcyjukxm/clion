// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.execution.configuration.EnvironmentVariablesComponent;
import org.jdom.Element;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.Executor;
import java.util.Map;
import com.intellij.execution.ExternalizablePath;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import java.util.LinkedHashMap;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.CommonProgramRunConfigurationParameters;
import com.jetbrains.cidr.lang.workspace.OCRunConfigurationWithResolveConfiguration;
import com.intellij.execution.configurations.LocatableConfigurationBase;

public abstract class CidrRunConfiguration<BC extends CidrBuildConfiguration, TARGET extends CidrBuildTarget<BC>> extends LocatableConfigurationBase implements CidrRunProfile, OCRunConfigurationWithResolveConfiguration, CommonProgramRunConfigurationParameters
{
    @Nullable
    private BuildTargetAndConfigurationData myTargetAndConfigurationData;
    @Nullable
    private String myProgramParameters;
    @Nullable
    private String myWorkingDirectory;
    @NotNull
    private LinkedHashMap<String, String> myEnvs;
    private static final boolean DEFAULT_PASS_PARENT_ENVS = true;
    private boolean myPassParentEnvs;
    
    public CidrRunConfiguration(final Project project, final ConfigurationFactory configurationFactory, final String s) {
        super(project, configurationFactory, s);
        this.myEnvs = (LinkedHashMap<String, String>)ContainerUtil.newLinkedHashMap();
        this.myPassParentEnvs = true;
    }
    
    public String suggestedName() {
        return this.suggestNameForTarget();
    }
    
    @Nullable
    public String suggestNameForTarget() {
        final BuildTargetAndConfigurationData myTargetAndConfigurationData = this.myTargetAndConfigurationData;
        Label_0023: {
            try {
                if (myTargetAndConfigurationData == null) {
                    return null;
                }
                final BuildTargetAndConfigurationData buildTargetAndConfigurationData = myTargetAndConfigurationData;
                final BuildTargetData buildTargetData = buildTargetAndConfigurationData.target;
                if (buildTargetData != null) {
                    break Label_0023;
                }
                return null;
            }
            catch (InvalidDataException ex) {
                throw b((RuntimeException)ex);
            }
            try {
                final BuildTargetAndConfigurationData buildTargetAndConfigurationData = myTargetAndConfigurationData;
                final BuildTargetData buildTargetData = buildTargetAndConfigurationData.target;
                if (buildTargetData != null) {
                    return myTargetAndConfigurationData.target.targetName;
                }
            }
            catch (InvalidDataException ex2) {
                throw b((RuntimeException)ex2);
            }
        }
        return null;
    }
    
    public void setTargetAndConfigurationData(@Nullable final BuildTargetAndConfigurationData myTargetAndConfigurationData) {
        this.myTargetAndConfigurationData = myTargetAndConfigurationData;
    }
    
    @Nullable
    public BuildTargetAndConfigurationData getTargetAndConfigurationData() {
        return this.myTargetAndConfigurationData;
    }
    
    public void setProgramParameters(@Nullable final String s) {
        String myProgramParameters = null;
        Label_0017: {
            try {
                if (StringUtil.isEmptyOrSpaces(s)) {
                    myProgramParameters = null;
                    break Label_0017;
                }
            }
            catch (InvalidDataException ex) {
                throw b((RuntimeException)ex);
            }
            myProgramParameters = s;
        }
        this.myProgramParameters = myProgramParameters;
    }
    
    @Nullable
    public String getProgramParameters() {
        return this.myProgramParameters;
    }
    
    public void setWorkingDirectory(@Nullable final String s) {
        this.myWorkingDirectory = ExternalizablePath.urlValue(s);
    }
    
    @Nullable
    public String getWorkingDirectory() {
        return ExternalizablePath.localPathValue(this.myWorkingDirectory);
    }
    
    public void setEnvs(@NotNull final Map<String, String> map) {
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "envs", "com/jetbrains/cidr/execution/CidrRunConfiguration", "setEnvs"));
            }
        }
        catch (InvalidDataException ex) {
            throw b((RuntimeException)ex);
        }
        this.myEnvs = new LinkedHashMap<String, String>(map);
    }
    
    @NotNull
    public Map<String, String> getEnvs() {
        LinkedHashMap<String, String> myEnvs;
        try {
            myEnvs = this.myEnvs;
            if (myEnvs == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrRunConfiguration", "getEnvs"));
            }
        }
        catch (InvalidDataException ex) {
            throw b((RuntimeException)ex);
        }
        return myEnvs;
    }
    
    public void setPassParentEnvs(final boolean myPassParentEnvs) {
        this.myPassParentEnvs = myPassParentEnvs;
    }
    
    public boolean isPassParentEnvs() {
        return this.myPassParentEnvs;
    }
    
    @NotNull
    protected BuildTargetAndConfigurationData createBuildTargetAndConfigurationData(@Nullable final String s, @Nullable final String s2, @Nullable final String s3) {
        BuildTargetAndConfigurationData buildTargetAndConfigurationData;
        try {
            buildTargetAndConfigurationData = new BuildTargetAndConfigurationData(s, s2, s3);
            if (buildTargetAndConfigurationData == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrRunConfiguration", "createBuildTargetAndConfigurationData"));
            }
        }
        catch (InvalidDataException ex) {
            throw b((RuntimeException)ex);
        }
        return buildTargetAndConfigurationData;
    }
    
    @Nullable
    public abstract CidrCommandLineState getState(@NotNull final Executor p0, @NotNull final ExecutionEnvironment p1) throws ExecutionException;
    
    public void readExternal(final Element element) throws InvalidDataException {
        super.readExternal(element);
        this.myProgramParameters = element.getAttributeValue("PROGRAM_PARAMS");
        this.myWorkingDirectory = element.getAttributeValue("WORKING_DIR");
        EnvironmentVariablesComponent.readExternal(element, this.myEnvs = a());
        this.myPassParentEnvs = a(element.getAttributeValue("PASS_PARENT_ENVS_2"), true);
        this.myTargetAndConfigurationData = this.createBuildTargetAndConfigurationData(element.getAttributeValue("PROJECT_NAME"), element.getAttributeValue("TARGET_NAME"), element.getAttributeValue("CONFIG_NAME"));
    }
    
    @NotNull
    private static LinkedHashMap<String, String> a() {
        LinkedHashMap linkedHashMap;
        try {
            linkedHashMap = new LinkedHashMap<String, String>();
            if (linkedHashMap == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrRunConfiguration", "createEnvMap"));
            }
        }
        catch (InvalidDataException ex) {
            throw b((RuntimeException)ex);
        }
        return (LinkedHashMap<String, String>)linkedHashMap;
    }
    
    @NotNull
    private static Boolean a(@Nullable final String s, final boolean b) {
        Boolean value = null;
        Label_0022: {
            try {
                if (StringUtil.isEmptyOrSpaces(s)) {
                    final boolean booleanValue = b;
                    break Label_0022;
                }
            }
            catch (InvalidDataException ex) {
                throw b((RuntimeException)ex);
            }
            final boolean booleanValue = Boolean.valueOf(s);
            try {
                value = booleanValue;
                if (value == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrRunConfiguration", "getBoolean"));
                }
            }
            catch (InvalidDataException ex2) {
                throw b((RuntimeException)ex2);
            }
        }
        return value;
    }
    
    public void writeExternal(final Element element) throws WriteExternalException {
        try {
            super.writeExternal(element);
            if (!StringUtil.isEmptyOrSpaces(this.myProgramParameters)) {
                element.setAttribute("PROGRAM_PARAMS", this.myProgramParameters);
            }
        }
        catch (WriteExternalException ex) {
            throw b((RuntimeException)ex);
        }
        try {
            if (!StringUtil.isEmptyOrSpaces(this.myWorkingDirectory)) {
                element.setAttribute("WORKING_DIR", this.myWorkingDirectory);
            }
        }
        catch (WriteExternalException ex2) {
            throw b((RuntimeException)ex2);
        }
        EnvironmentVariablesComponent.writeExternal(element, this.myEnvs);
        element.setAttribute("PASS_PARENT_ENVS_2", String.valueOf(this.myPassParentEnvs));
        if (this.myTargetAndConfigurationData != null) {
            final BuildTargetData target = this.myTargetAndConfigurationData.target;
            try {
                if (target != null) {
                    element.setAttribute("PROJECT_NAME", target.projectName);
                    element.setAttribute("TARGET_NAME", target.targetName);
                }
            }
            catch (WriteExternalException ex3) {
                throw b((RuntimeException)ex3);
            }
            try {
                if (this.myTargetAndConfigurationData.configurationName != null) {
                    element.setAttribute("CONFIG_NAME", this.myTargetAndConfigurationData.configurationName);
                }
            }
            catch (WriteExternalException ex4) {
                throw b((RuntimeException)ex4);
            }
        }
    }
    
    public RunConfiguration clone() {
        final CidrRunConfiguration cidrRunConfiguration = (CidrRunConfiguration)super.clone();
        cidrRunConfiguration.myProgramParameters = this.myProgramParameters;
        cidrRunConfiguration.myWorkingDirectory = this.myWorkingDirectory;
        cidrRunConfiguration.myEnvs = new LinkedHashMap<String, String>(this.myEnvs);
        cidrRunConfiguration.myPassParentEnvs = this.myPassParentEnvs;
        cidrRunConfiguration.myTargetAndConfigurationData = this.myTargetAndConfigurationData;
        return (RunConfiguration)cidrRunConfiguration;
    }
    
    public void setupDefaultTargetAndExecutable() {
        final CidrBuildConfigurationHelper<BC, CidrBuildTarget> helper = this.getHelper();
        CidrBuildTarget cidrBuildTarget = null;
        BuildTargetAndConfigurationData targetAndConfigurationData = this.getTargetAndConfigurationData();
        Label_0039: {
            try {
                if (targetAndConfigurationData == null || targetAndConfigurationData.target == null) {
                    break Label_0039;
                }
            }
            catch (InvalidDataException ex) {
                throw b((RuntimeException)ex);
            }
            cidrBuildTarget = helper.findRunTarget(targetAndConfigurationData.target);
        }
        if (cidrBuildTarget == null) {
            cidrBuildTarget = helper.getDefaultTarget();
            targetAndConfigurationData = null;
        }
        String s = null;
        String projectName = null;
        Label_0124: {
            Label_0101: {
                Label_0080: {
                    try {
                        if (targetAndConfigurationData == null || targetAndConfigurationData.configurationName == null) {
                            break Label_0080;
                        }
                    }
                    catch (InvalidDataException ex2) {
                        throw b((RuntimeException)ex2);
                    }
                    s = targetAndConfigurationData.configurationName;
                    break Label_0101;
                }
                final CidrBuildConfiguration defaultConfiguration = helper.getDefaultConfiguration(cidrBuildTarget);
                if (defaultConfiguration != null) {
                    s = defaultConfiguration.getName();
                }
                try {
                    if (cidrBuildTarget == null) {
                        projectName = null;
                        break Label_0124;
                    }
                }
                catch (InvalidDataException ex3) {
                    throw b((RuntimeException)ex3);
                }
            }
            projectName = cidrBuildTarget.getProjectName();
        }
        this.setTargetAndConfigurationData(new BuildTargetAndConfigurationData(projectName, (cidrBuildTarget == null) ? null : cidrBuildTarget.getName(), s));
    }
    
    @NotNull
    public abstract CidrBuildConfigurationHelper<BC, TARGET> getHelper();
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
