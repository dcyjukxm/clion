// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import javax.swing.JComponent;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Ref;
import com.intellij.util.messages.Topic;
import com.intellij.openapi.options.SearchableConfigurable;

public class CPPToolchainsConfigurable implements SearchableConfigurable
{
    public static final Topic<EnvironmentChangeListener> TOPIC;
    public static final String DISPLAY_NAME;
    private CPPToolchainsPanel myPanel;
    @Nullable
    private static Ref<CPPToolchains.WinEnvironment> ourCurrentSelectedEnvironment;
    
    @NotNull
    public String getId() {
        String s;
        try {
            s = "CPPToolchains";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPToolchainsConfigurable", "getId"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Nls
    public String getDisplayName() {
        return CPPToolchainsConfigurable.DISPLAY_NAME;
    }
    
    @Nullable
    public String getHelpTopic() {
        return "reference.settings.cl.toolchains";
    }
    
    @Nullable
    public JComponent createComponent() {
        try {
            if (this.myPanel == null) {
                (this.myPanel = new CPPToolchainsPanel(new EnvironmentChangeListener() {
                    @Override
                    public void environmentChanged(@Nullable final CPPToolchains.WinEnvironment winEnvironment) {
                        CPPToolchainsConfigurable.ourCurrentSelectedEnvironment = (Ref<CPPToolchains.WinEnvironment>)Ref.create((Object)winEnvironment);
                        final Application application = ApplicationManager.getApplication();
                        if (application != null) {
                            ((EnvironmentChangeListener)application.getMessageBus().syncPublisher((Topic)CPPToolchainsConfigurable.TOPIC)).environmentChanged(winEnvironment);
                        }
                    }
                })).autoRecheckWithApplication();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return this.myPanel;
    }
    
    public boolean isModified() {
        return this.myPanel.isModified(CPPToolchains.getInstance());
    }
    
    public void apply() throws ConfigurationException {
        this.myPanel.apply(CPPToolchains.getInstance());
    }
    
    @Nullable
    public static CPPToolchains.WinEnvironment getSelectedEnvironment() {
        try {
            if (CPPToolchainsConfigurable.ourCurrentSelectedEnvironment != null) {
                return (CPPToolchains.WinEnvironment)CPPToolchainsConfigurable.ourCurrentSelectedEnvironment.get();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return CPPToolchains.getInstance().getState().getWinEnvironment();
    }
    
    public void reset() {
        this.myPanel.reset(CPPToolchains.getInstance());
    }
    
    public void disposeUIResources() {
        Disposer.dispose((Disposable)this.myPanel);
        this.myPanel = null;
        CPPToolchainsConfigurable.ourCurrentSelectedEnvironment = null;
    }
    
    static {
        TOPIC = Topic.create("Selected environment listeners", (Class)EnvironmentChangeListener.class);
        DISPLAY_NAME = CPPBundle.message("cpp.toolchains", new Object[0]);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    public interface EnvironmentChangeListener
    {
        void environmentChanged(@Nullable final CPPToolchains.WinEnvironment p0);
    }
}
