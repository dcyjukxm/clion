// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import com.intellij.util.LineSeparator;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.EnvironmentUtil;
import java.util.concurrent.ConcurrentHashMap;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.application.ApplicationActivationListener;
import java.util.concurrent.atomic.AtomicBoolean;
import com.jetbrains.cidr.lang.toolchains.DefaultCidrToolEnvironment;
import java.util.Comparator;
import com.intellij.openapi.util.io.WindowsRegistryUtil;
import com.intellij.openapi.util.Pair;
import com.intellij.util.Processor;
import org.jdom.Element;
import java.util.Iterator;
import com.intellij.openapi.util.Comparing;
import javax.swing.ComboBoxEditor;
import com.intellij.openapi.ui.FixedComboBoxEditor;
import javax.swing.ComboBoxModel;
import com.intellij.ui.CollectionComboBoxModel;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.intellij.util.ui.GridBag;
import javax.swing.JPanel;
import com.intellij.openapi.ui.ComboBox;
import java.util.Arrays;
import com.intellij.openapi.util.text.StringUtil;
import kotlin.Triple;
import java.util.Map;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import java.util.regex.Matcher;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.openapi.progress.ProgressIndicator;
import com.jetbrains.cidr.cpp.CPPLog;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.application.ApplicationManager;
import java.util.Collections;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.util.Version;
import com.jetbrains.cidr.cpp.CPPToolchains;
import java.util.regex.Pattern;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.jetbrains.cidr.cpp.CPPBundle;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import com.jetbrains.cidr.ToolVersion;
import com.jetbrains.cidr.toolchains.CidrToolSet;

public class MSVC extends CidrToolSet
{
    public static final String VS_VERSION_ENV = "VisualStudioVersion";
    public static final ToolVersion MIN_VERSION;
    public static final ToolVersion MAX_VERSION;
    
    public static boolean isEnabled() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //     3: astore_0       
        //     4: ldc             "clion.enable.msvc"
        //     6: invokestatic    com/intellij/openapi/util/registry/Registry.is:(Ljava/lang/String;)Z
        //     9: ifne            39
        //    12: aload_0        
        //    13: ifnull          47
        //    16: goto            23
        //    19: invokestatic    com/jetbrains/cidr/cpp/toolchains/MSVC.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    22: athrow         
        //    23: aload_0        
        //    24: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //    29: ifeq            47
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/cpp/toolchains/MSVC.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    38: athrow         
        //    39: iconst_1       
        //    40: goto            48
        //    43: invokestatic    com/jetbrains/cidr/cpp/toolchains/MSVC.c:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    46: athrow         
        //    47: iconst_0       
        //    48: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                    
        //  -----  -----  -----  -----  --------------------------------------------------------
        //  4      16     19     23     Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  12     32     35     39     Lcom/intellij/openapi/progress/ProcessCanceledException;
        //  23     43     43     47     Lcom/intellij/openapi/progress/ProcessCanceledException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
    
    public MSVC(@NotNull final File file) {
        if (file == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "home", "com/jetbrains/cidr/cpp/toolchains/MSVC", "<init>"));
        }
        super(file);
    }
    
    @NotNull
    @Override
    public String getName() {
        String message;
        try {
            message = CPPBundle.message("msvc", new Object[0]);
            if (message == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC", "getName"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw c((Exception)ex);
        }
        return message;
    }
    
    @Override
    public boolean isMSVC() {
        return true;
    }
    
    @Override
    public String checkVersion(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "version", "com/jetbrains/cidr/cpp/toolchains/MSVC", "checkVersion"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw c((Exception)ex);
        }
        return CPPToolchains.checkVersion(MSVC.MIN_VERSION, MSVC.MAX_VERSION, ToolVersion.parse(s, Pattern.compile("(\\d+\\.\\d+).*")));
    }
    
    @Nullable
    @Override
    public String readVersion() {
        try {
            final GeneralCommandLine generalCommandLine = new GeneralCommandLine(new String[] { "" });
            ProgressIndicator progressIndicator = null;
            Label_0079: {
                try {
                    this.prepareEnvironment(generalCommandLine, CidrToolEnvironment.PrepareFor.BUILD, Collections.emptyList());
                    generalCommandLine.setExePath(a());
                    generalCommandLine.withParameters(new String[] { "/C", "echo", "VisualStudioVersion=%VisualStudioVersion%" });
                    if (ApplicationManager.getApplication() == null) {
                        progressIndicator = null;
                        break Label_0079;
                    }
                }
                catch (ExecutionException ex) {
                    throw c((Exception)ex);
                }
                progressIndicator = ProgressManager.getInstance().getProgressIndicator();
            }
            final ProgressIndicator progressIndicator2 = progressIndicator;
            final CapturingProcessHandler capturingProcessHandler = new CapturingProcessHandler(generalCommandLine);
            ProcessOutput processOutput = null;
            Label_0115: {
                try {
                    if (progressIndicator2 == null) {
                        processOutput = capturingProcessHandler.runProcess(10000);
                        break Label_0115;
                    }
                }
                catch (ExecutionException ex2) {
                    throw c((Exception)ex2);
                }
                processOutput = capturingProcessHandler.runProcessWithProgressIndicator(progressIndicator2, 10000);
            }
            final ProcessOutput processOutput2 = processOutput;
            try {
                if (processOutput2.isCancelled()) {
                    throw new ProcessCanceledException();
                }
            }
            catch (ExecutionException ex3) {
                throw c((Exception)ex3);
            }
            Label_0160: {
                try {
                    if (processOutput2.getExitCode() != 0) {
                        break Label_0160;
                    }
                    final ProcessOutput processOutput3 = processOutput2;
                    final boolean b = processOutput3.isTimeout();
                    if (b) {
                        break Label_0160;
                    }
                    break Label_0160;
                }
                catch (ExecutionException ex4) {
                    throw c((Exception)ex4);
                }
                try {
                    final ProcessOutput processOutput3 = processOutput2;
                    final boolean b = processOutput3.isTimeout();
                    if (b) {
                        throw new ExecutionException("Exit code: " + processOutput2.getExitCode() + "\n" + processOutput2.getStdout() + "\n" + processOutput2.getStderr());
                    }
                }
                catch (ExecutionException ex5) {
                    throw c((Exception)ex5);
                }
            }
            final Matcher matcher = Pattern.compile("VisualStudioVersion=(\\d.+)").matcher(processOutput2.getStdout());
            if (matcher.find()) {
                return matcher.group(1);
            }
            throw new ExecutionException("Cannot detect MSVC version:\n" + processOutput2.getStdout() + "\n" + processOutput2.getStderr());
        }
        catch (ExecutionException ex6) {
            CPPLog.LOG.info("Cannot read MSVC version: " + ex6);
            return null;
        }
    }
    
    @Nullable
    public static String readVersion(@NotNull final File file) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "home", "com/jetbrains/cidr/cpp/toolchains/MSVC", "readVersion"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw c((Exception)ex);
        }
        return new MSVC(file).readVersion();
    }
    
    @Override
    public boolean forceToolSetGDB() {
        return false;
    }
    
    public static boolean isDebugSupportDisabled(@Nullable final CidrToolSet set) {
        return set instanceof MSVC;
    }
    
    @NotNull
    @Override
    public File getGDBPath() {
        File subFile;
        try {
            subFile = this.getSubFile("VC\\bin\\gdb.exe");
            if (subFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC", "getGDBPath"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw c((Exception)ex);
        }
        return subFile;
    }
    
    @Override
    public void prepareEnvironment(@NotNull final GeneralCommandLine generalCommandLine, @NotNull final CidrToolEnvironment.PrepareFor prepareFor, @NotNull final List<Option> list) throws ExecutionException {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/cpp/toolchains/MSVC", "prepareEnvironment"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        try {
            if (prepareFor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prepareFor", "com/jetbrains/cidr/cpp/toolchains/MSVC", "prepareEnvironment"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/cpp/toolchains/MSVC", "prepareEnvironment"));
            }
        }
        catch (ExecutionException ex3) {
            throw c((Exception)ex3);
        }
        if (prepareFor == CidrToolEnvironment.PrepareFor.BUILD) {
            final Triple<Arch, Platform, Version> a = a(list);
            final ArrayList<String> list2 = new ArrayList<String>();
            ArrayList<String> list5 = null;
            Object value3 = null;
            Label_0251: {
                Label_0219: {
                    ArrayList<String> list4 = null;
                    Label_0184: {
                        ArrayList<String> list3;
                        try {
                            list3 = list2;
                            if (a.component1() != null) {
                                final String value = ((Arch)a.component1()).getValue();
                                break Label_0184;
                            }
                        }
                        catch (ExecutionException ex4) {
                            throw c((Exception)ex4);
                        }
                        final String value = "x86";
                        try {
                            list3.add(value);
                            list4 = list2;
                            if (a.component2() != null) {
                                final Object value2 = ((Platform)a.component2()).getValue();
                                break Label_0219;
                            }
                        }
                        catch (ExecutionException ex5) {
                            throw c((Exception)ex5);
                        }
                    }
                    final Object value2 = null;
                    try {
                        ContainerUtil.addIfNotNull((Collection)list4, value2);
                        list5 = list2;
                        if (a.component3() != null) {
                            value3 = ((Version)a.component3()).getValue();
                            break Label_0251;
                        }
                    }
                    catch (ExecutionException ex6) {
                        throw c((Exception)ex6);
                    }
                }
                value3 = null;
            }
            ContainerUtil.addIfNotNull((Collection)list5, value3);
            generalCommandLine.getEnvironment().putAll(EnvCache.getEnvironment(this.b(), list2));
        }
    }
    
    @NotNull
    private String b() throws ExecutionException {
        final String s = "vcvarsall.bat";
        final String homePath = this.getHomePath();
        final File file = new File(homePath, "VC\\" + s);
        Label_0093: {
            String s2 = null;
            Label_0058: {
                try {
                    if (!file.exists()) {
                        break Label_0093;
                    }
                    final File file2 = file;
                    s2 = file2.getAbsolutePath();
                    if (s2 == null) {
                        break Label_0058;
                    }
                    return s2;
                }
                catch (ExecutionException ex) {
                    throw c((Exception)ex);
                }
                try {
                    final File file2 = file;
                    s2 = file2.getAbsolutePath();
                    if (s2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC", "getVarsAllScript"));
                    }
                }
                catch (ExecutionException ex2) {
                    throw c((Exception)ex2);
                }
            }
            return s2;
        }
        final File file3 = new File(homePath, "VC\\Auxiliary\\Build\\" + s);
        String s3 = null;
        Label_0143: {
            try {
                if (!file3.exists()) {
                    throw new ExecutionException("Cannot locate vcvarsall.bat in " + homePath + "\nPlease check if Visual C++ support is installed");
                }
                final File file4 = file3;
                s3 = file4.getAbsolutePath();
                if (s3 == null) {
                    break Label_0143;
                }
                return s3;
            }
            catch (ExecutionException ex3) {
                throw c((Exception)ex3);
            }
            try {
                final File file4 = file3;
                s3 = file4.getAbsolutePath();
                if (s3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC", "getVarsAllScript"));
                }
            }
            catch (ExecutionException ex4) {
                throw c((Exception)ex4);
            }
        }
        return s3;
    }
    
    @NotNull
    private static String a() throws ExecutionException {
        final String getenv = System.getenv("ComSpec");
        try {
            if (StringUtil.isEmptyOrSpaces(getenv)) {
                throw new ExecutionException("Windows 'cmd' not found in 'ComSpec' environment variable.");
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        String s;
        try {
            s = getenv;
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC", "getCommandExe"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        return s;
    }
    
    @NotNull
    public static OptionsConfigurable createOptionsConfigurable() {
        final List<String> list = Arrays.asList("x86", "amd64", "arm", "x86_amd64", "x86_arm", "amd64_x86", "amd64_arm");
        OptionsConfigurable optionsConfigurable;
        try {
            optionsConfigurable = new OptionsConfigurable() {
                private ComboBox<String> myArchComboBox;
                private ComboBox<String> myPlatformField;
                private ComboBox<String> myVersionField;
                
                @Override
                public void createComponents(final JPanel panel, @NotNull final GridBag gridBag) {
                    try {
                        if (gridBag == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "gridBag", "com/jetbrains/cidr/cpp/toolchains/MSVC$1", "createComponents"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    final JPanel panel2 = new JPanel(new GridBagLayout());
                    panel2.add(new Label(CPPBundle.message("msvc.option.arch", new Object[0])));
                    panel.add(panel2, gridBag.next());
                    final GridBag setDefaultAnchor = new GridBag().setDefaultInsets(0, 0, 0, 10).setDefaultAnchor(21);
                    final JPanel panel3 = new JPanel(new GridBagLayout());
                    panel.add(panel3, gridBag.next().coverLine().fillCell());
                    panel3.add((Component)(this.myArchComboBox = this.a(list, "x86")), setDefaultAnchor.next());
                    panel3.add(new Label(CPPBundle.message("msvc.option.platform", new Object[0])), setDefaultAnchor.next());
                    panel3.add((Component)(this.myPlatformField = this.a(Arrays.asList("store", "uwp", "onecore"), "")), setDefaultAnchor.next());
                    panel3.add(new Label(CPPBundle.message("msvc.option.version", new Object[0])), setDefaultAnchor.next());
                    final String toolTipText = "8.1 or Windows 10 SDK version";
                    (this.myVersionField = this.a(Collections.singletonList("8.1"), toolTipText)).setToolTipText(toolTipText);
                    panel3.add((Component)this.myVersionField, setDefaultAnchor.next().fillCell().coverLine().weightx(1.0).insetRight(0));
                }
                
                private ComboBox<String> a(final List<String> list, final String text) {
                    final ComboBox comboBox = new ComboBox((ComboBoxModel)new CollectionComboBoxModel((List)list));
                    final FixedComboBoxEditor editor = new FixedComboBoxEditor();
                    comboBox.setEditor((ComboBoxEditor)editor);
                    editor.getField().getEmptyText().setText(text);
                    comboBox.setEditable(true);
                    return (ComboBox<String>)comboBox;
                }
                
                @Override
                public boolean isModified(@NotNull final List<Option> list) {
                    try {
                        if (list == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentOptions", "com/jetbrains/cidr/cpp/toolchains/MSVC$1", "isModified"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        if (!Comparing.equal((Object)a(list), (Object)this.getFieldValues())) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    return false;
                }
                
                @NotNull
                public Triple<Arch, Platform, Version> getFieldValues() {
                    final String trim = StringUtil.trim(StringUtil.nullize((String)this.myArchComboBox.getSelectedItem(), true));
                    final String trim2 = StringUtil.trim(StringUtil.nullize((String)this.myPlatformField.getSelectedItem(), true));
                    final String trim3 = StringUtil.trim(StringUtil.nullize((String)this.myVersionField.getSelectedItem(), true));
                    Arch arch = null;
                    Label_0078: {
                        try {
                            if (trim == null) {
                                arch = null;
                                break Label_0078;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        arch = new Arch(trim);
                    }
                    final Triple triple = new Triple((Object)arch, (Object)((trim2 == null) ? null : new Platform(trim2)), (Object)((trim3 == null) ? null : new Version(trim3)));
                    if (triple == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC$1", "getFieldValues"));
                    }
                    return (Triple<Arch, Platform, Version>)triple;
                }
                
                @NotNull
                @Override
                public List<Option> apply() {
                    final Triple<Arch, Platform, Version> fieldValues = this.getFieldValues();
                    final ArrayList list = new ArrayList<Option>(3);
                    ArrayList list2;
                    try {
                        ContainerUtil.addIfNotNull((Collection)list, fieldValues.component1());
                        ContainerUtil.addIfNotNull((Collection)list, fieldValues.component2());
                        ContainerUtil.addIfNotNull((Collection)list, fieldValues.component3());
                        list2 = list;
                        if (list2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC$1", "apply"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    return (List<Option>)list2;
                }
                
                @Override
                public void reset(@NotNull final List<Option> list) {
                    try {
                        if (list == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentOptions", "com/jetbrains/cidr/cpp/toolchains/MSVC$1", "reset"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    final Triple access$000 = a(list);
                    ComboBox<String> myVersionField = null;
                    Object value3 = null;
                    Label_0142: {
                        Label_0110: {
                            ComboBox<String> myPlatformField = null;
                            Label_0078: {
                                ComboBox<String> myArchComboBox;
                                try {
                                    myArchComboBox = this.myArchComboBox;
                                    if (access$000.component1() == null) {
                                        final Object value = null;
                                        break Label_0078;
                                    }
                                }
                                catch (IllegalArgumentException ex2) {
                                    throw a(ex2);
                                }
                                final Object value = ((Arch)access$000.component1()).getValue();
                                try {
                                    myArchComboBox.setSelectedItem(value);
                                    myPlatformField = this.myPlatformField;
                                    if (access$000.component2() == null) {
                                        final Object value2 = null;
                                        break Label_0110;
                                    }
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw a(ex3);
                                }
                            }
                            final Object value2 = ((Platform)access$000.component2()).getValue();
                            try {
                                myPlatformField.setSelectedItem(value2);
                                myVersionField = this.myVersionField;
                                if (access$000.component3() == null) {
                                    value3 = null;
                                    break Label_0142;
                                }
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                        }
                        value3 = ((Version)access$000.component3()).getValue();
                    }
                    myVersionField.setSelectedItem(value3);
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            };
            if (optionsConfigurable == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC", "createOptionsConfigurable"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw c((Exception)ex);
        }
        return optionsConfigurable;
    }
    
    @NotNull
    private static Triple<Arch, Platform, Version> a(@NotNull final List<Option> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/cpp/toolchains/MSVC", "getOptions"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw c((Exception)ex);
        }
        Object o = null;
        Object o2 = null;
        Object o3 = null;
        for (final Option option : list) {
            if (option instanceof Arch) {
                o = option;
            }
            if (option instanceof Platform) {
                o2 = option;
            }
            if (option instanceof Version) {
                o3 = option;
            }
        }
        Triple triple;
        try {
            triple = new Triple(o, o2, o3);
            if (triple == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC", "getOptions"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw c((Exception)ex2);
        }
        return (Triple<Arch, Platform, Version>)triple;
    }
    
    @Nullable
    public static Option loadOption(@NotNull final Element element) {
        try {
            if (element == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/toolchains/MSVC", "loadOption"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw c((Exception)ex);
        }
        final String attributeValue = element.getAttributeValue("type");
        final String attributeValue2 = element.getAttributeValue("value");
        Label_0073: {
            try {
                if (attributeValue == null) {
                    break Label_0073;
                }
                final String s = attributeValue2;
                if (s == null) {
                    break Label_0073;
                }
                break Label_0073;
            }
            catch (ProcessCanceledException ex2) {
                throw c((Exception)ex2);
            }
            try {
                final String s = attributeValue2;
                if (s == null) {
                    return null;
                }
            }
            catch (ProcessCanceledException ex3) {
                throw c((Exception)ex3);
            }
        }
        final String s2 = attributeValue;
        int n = -1;
        Label_0173: {
            Label_0161: {
                Label_0146: {
                    try {
                        switch (s2.hashCode()) {
                            case -653292911: {
                                if (s2.equals("msvc.arch")) {
                                    break;
                                }
                                break Label_0173;
                            }
                            case 812748078: {
                                break Label_0146;
                            }
                            case 351608024: {
                                break Label_0161;
                            }
                            default: {
                                break Label_0173;
                            }
                        }
                    }
                    catch (ProcessCanceledException ex4) {
                        throw c((Exception)ex4);
                    }
                    n = 0;
                    break Label_0173;
                }
                if (s2.equals("msvc.platform")) {
                    n = 1;
                }
                break Label_0173;
            }
            if (s2.equals("version")) {
                n = 2;
            }
            try {
                switch (n) {
                    case 0: {
                        return new Arch(attributeValue2);
                    }
                    case 1: {
                        break;
                    }
                    case 2: {
                        return new Version(attributeValue2);
                    }
                    default: {
                        return null;
                    }
                }
            }
            catch (ProcessCanceledException ex5) {
                throw c((Exception)ex5);
            }
        }
        return new Platform(attributeValue2);
    }
    
    public static boolean processVisualStudioInstallations(final Processor<Pair<File, Boolean>> processor) {
        final Processor processor2 = file -> {
            try {
                if (!file.exists()) {
                    return true;
                }
            }
            catch (ProcessCanceledException ex) {
                throw c((Exception)ex);
            }
            return processor.process((Object)Pair.create((Object)file, (Object)new File(file, "VC").exists()));
        };
        final Iterator<File> iterator = a(new File("C:\\Program Files (x86)\\Microsoft Visual Studio"), (s, s3) -> -StringUtil.compareVersionNumbers(s, s3)).iterator();
        while (iterator.hasNext()) {
            for (final File file : a(iterator.next(), (s2, s4) -> s2.compareToIgnoreCase(s4))) {
                try {
                    if (!processor2.process((Object)file)) {
                        return false;
                    }
                    continue;
                }
                catch (ProcessCanceledException ex) {
                    throw c((Exception)ex);
                }
            }
        }
        final String registryValue = WindowsRegistryUtil.readRegistryValue("HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Microsoft\\VisualStudio\\14.0", "InstallDir");
        if (registryValue != null) {
            final String trimEnd = StringUtil.trimEnd(StringUtil.trimEnd(registryValue, "\\"), "Common7\\IDE");
            try {
                if (!processor2.process((Object)new File(trimEnd))) {
                    return false;
                }
            }
            catch (ProcessCanceledException ex2) {
                throw c((Exception)ex2);
            }
        }
        final String registryValue2 = WindowsRegistryUtil.readRegistryValue("HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Microsoft\\VisualStudio\\12.0\\Setup\\VS", "ProductDir");
        try {
            if (registryValue2 == null) {
                return true;
            }
            final Processor processor3 = processor2;
            final String s5 = registryValue2;
            final String s6 = "\\";
            final String s7 = StringUtil.trimEnd(s5, s6);
            final File file2 = new File(s7);
            final boolean b = processor3.process((Object)file2);
            if (!b) {
                return false;
            }
            return true;
        }
        catch (ProcessCanceledException ex3) {
            throw c((Exception)ex3);
        }
        try {
            final Processor processor3 = processor2;
            final String s5 = registryValue2;
            final String s6 = "\\";
            final String s7 = StringUtil.trimEnd(s5, s6);
            final File file2 = new File(s7);
            final boolean b = processor3.process((Object)file2);
            if (!b) {
                return false;
            }
        }
        catch (ProcessCanceledException ex4) {
            throw c((Exception)ex4);
        }
        return true;
    }
    
    @NotNull
    private static List<File> a(@NotNull final File file, final Comparator<String> comparator) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dir", "com/jetbrains/cidr/cpp/toolchains/MSVC", "sortedSubdirs"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw c((Exception)ex);
        }
        final File[] listFiles = file.listFiles();
        if (listFiles != null) {
            final ArrayList list = new ArrayList<File>(Arrays.asList(listFiles));
            ArrayList<Object> list2;
            try {
                list.sort((file2, file3) -> comparator.compare(file2.getName(), file3.getName()));
                list2 = (ArrayList<Object>)list;
                if (list2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC", "sortedSubdirs"));
                }
            }
            catch (ProcessCanceledException ex2) {
                throw c((Exception)ex2);
            }
            return (List<File>)list2;
        }
        List<File> emptyList;
        try {
            emptyList = Collections.emptyList();
            if (emptyList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC", "sortedSubdirs"));
            }
        }
        catch (ProcessCanceledException ex3) {
            throw c((Exception)ex3);
        }
        return emptyList;
    }
    
    @NotNull
    @Override
    public char[] getSupportedFileSeparators() {
        char[] windows_UNIX_PATH_SEPARATORS;
        try {
            windows_UNIX_PATH_SEPARATORS = DefaultCidrToolEnvironment.WINDOWS_UNIX_PATH_SEPARATORS;
            if (windows_UNIX_PATH_SEPARATORS == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC", "getSupportedFileSeparators"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw c((Exception)ex);
        }
        return windows_UNIX_PATH_SEPARATORS;
    }
    
    static {
        MIN_VERSION = new ToolVersion(12, -1, -1);
        MAX_VERSION = new ToolVersion(15, -1, -1);
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
    
    public interface OptionsConfigurable
    {
        void createComponents(final JPanel p0, @NotNull final GridBag p1);
        
        boolean isModified(@NotNull final List<Option> p0);
        
        @NotNull
        List<Option> apply();
        
        void reset(@NotNull final List<Option> p0);
        
        default void disposeUIResources() {
        }
    }
    
    protected abstract static class BasicOption implements Option
    {
        public static final String TYPE_ATTR = "type";
        public static final String VALUE_ATTR = "value";
        @NotNull
        private final String myValue;
        
        public BasicOption(@NotNull final String myValue) {
            if (myValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/cpp/toolchains/MSVC$BasicOption", "<init>"));
            }
            this.myValue = myValue;
        }
        
        @NotNull
        @Override
        public String getValue() {
            String myValue;
            try {
                myValue = this.myValue;
                if (myValue == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC$BasicOption", "getValue"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myValue;
        }
        
        @Override
        public void write(@NotNull final Element element) {
            try {
                if (element == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/toolchains/MSVC$BasicOption", "write"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            element.setAttribute("type", this.getType());
            element.setAttribute("value", this.myValue);
        }
        
        @NotNull
        @Override
        public String getUniqueID() {
            String string;
            try {
                string = this.getType() + "=" + this.myValue;
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC$BasicOption", "getUniqueID"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return string;
        }
        
        @NotNull
        protected abstract String getType();
        
        @Override
        public String toString() {
            return this.getType() + "=" + this.myValue;
        }
        
        @Override
        public boolean equals(final Object o) {
            try {
                if (this == o) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0039: {
                try {
                    if (o == null) {
                        return false;
                    }
                    final BasicOption basicOption = this;
                    final Class<? extends BasicOption> clazz = basicOption.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                    break Label_0039;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final BasicOption basicOption = this;
                    final Class<? extends BasicOption> clazz = basicOption.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            final BasicOption basicOption2 = (BasicOption)o;
            try {
                if (!this.myValue.equals(basicOption2.myValue)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            return this.myValue.hashCode();
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private abstract static class MSVCOption extends BasicOption
    {
        public MSVCOption(@NotNull final String s) {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/cpp/toolchains/MSVC$MSVCOption", "<init>"));
            }
            super(s);
        }
    }
    
    public static class Arch extends MSVCOption
    {
        public static final String TYPE = "msvc.arch";
        public static final String DEFAULT = "x86";
        
        public Arch(@NotNull final String s) {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/cpp/toolchains/MSVC$Arch", "<init>"));
            }
            super(s);
        }
        
        @NotNull
        @Override
        protected String getType() {
            String s;
            try {
                s = "msvc.arch";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC$Arch", "getType"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return s;
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class Platform extends BasicOption
    {
        public static final String TYPE = "msvc.platform";
        
        public Platform(@NotNull final String s) {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/cpp/toolchains/MSVC$Platform", "<init>"));
            }
            super(s);
        }
        
        @NotNull
        @Override
        protected String getType() {
            String s;
            try {
                s = "msvc.platform";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC$Platform", "getType"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return s;
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class Version extends BasicOption
    {
        public static final String TYPE = "version";
        
        public Version(@NotNull final String s) {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/cpp/toolchains/MSVC$Version", "<init>"));
            }
            super(s);
        }
        
        @NotNull
        @Override
        protected String getType() {
            String s;
            try {
                s = "version";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC$Version", "getType"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return s;
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class EnvCache
    {
        private static final Map<List<String>, Map<String, String>> myCache;
        private static final AtomicBoolean myListenerAdded;
        
        public static Map<String, String> getEnvironment(final String s, final List<String> list) throws ExecutionException {
            final Application application = ApplicationManager.getApplication();
            Label_0025: {
                try {
                    if (application == null) {
                        break Label_0025;
                    }
                    final AtomicBoolean atomicBoolean = EnvCache.myListenerAdded;
                    final boolean b = true;
                    final boolean b2 = atomicBoolean.getAndSet(b);
                    if (!b2) {
                        break Label_0025;
                    }
                    break Label_0025;
                }
                catch (Exception ex) {
                    throw b(ex);
                }
                try {
                    final AtomicBoolean atomicBoolean = EnvCache.myListenerAdded;
                    final boolean b = true;
                    final boolean b2 = atomicBoolean.getAndSet(b);
                    if (!b2) {
                        application.getMessageBus().connect().subscribe(ApplicationActivationListener.TOPIC, (Object)new ApplicationActivationListener() {
                            public void applicationDeactivated(final IdeFrame ideFrame) {
                                EnvCache.myCache.clear();
                            }
                        });
                    }
                }
                catch (Exception ex2) {
                    throw b(ex2);
                }
            }
            final List concat = ContainerUtil.concat((List)Collections.singletonList(s), (List)list);
            Map<String, String> batEnvironment = EnvCache.myCache.get(concat);
            if (batEnvironment == null) {
                try {
                    batEnvironment = MyEnvReader.readBatEnvironment(new File(s), list);
                    try {
                        if (application != null) {
                            EnvCache.myCache.put(concat, batEnvironment);
                        }
                    }
                    catch (Exception ex3) {
                        throw b(ex3);
                    }
                }
                catch (Exception ex4) {
                    throw new ExecutionException("Cannot load Visual C++ environment from " + s + ":\n" + ex4.getMessage(), (Throwable)ex4);
                }
            }
            return batEnvironment;
        }
        
        static {
            myCache = new ConcurrentHashMap<List<String>, Map<String, String>>();
            myListenerAdded = new AtomicBoolean(false);
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
        
        private static class MyEnvReader extends EnvironmentUtil.ShellEnvReader
        {
            @NotNull
            public static Map<String, String> readBatEnvironment(@NotNull final File file, final List<String> list) throws Exception {
                try {
                    if (file == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "batToSetEnvironment", "com/jetbrains/cidr/cpp/toolchains/MSVC$EnvCache$MyEnvReader", "readBatEnvironment"));
                    }
                }
                catch (Exception ex) {
                    throw b(ex);
                }
                File tempFile = null;
                File tempFile2 = null;
                try {
                    tempFile = FileUtil.createTempFile("intellij-cmd-env.", ".bat", false);
                    tempFile2 = FileUtil.createTempFile("intellij-cmd-env.", ".tmp", false);
                    final String separatorString = LineSeparator.CRLF.getSeparatorString();
                    FileUtil.writeToFile(tempFile, "call \"" + file.getAbsolutePath() + "\" %*" + separatorString + "set > \"" + tempFile2.getAbsolutePath() + "\"");
                    final Map runProcessAndReadEnvs = runProcessAndReadEnvs(ContainerUtil.concat((List)Collections.singletonList(tempFile.getAbsolutePath()), (List)list), file.getParentFile(), tempFile2, separatorString);
                    Map map;
                    try {
                        map = runProcessAndReadEnvs;
                        if (map == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC$EnvCache$MyEnvReader", "readBatEnvironment"));
                        }
                    }
                    catch (Exception ex2) {
                        throw b(ex2);
                    }
                    return (Map<String, String>)map;
                }
                finally {
                    try {
                        if (tempFile != null) {
                            FileUtil.delete(tempFile);
                        }
                    }
                    catch (Exception ex3) {
                        throw b(ex3);
                    }
                    try {
                        if (tempFile2 != null) {
                            FileUtil.delete(tempFile2);
                        }
                    }
                    catch (Exception ex4) {
                        throw b(ex4);
                    }
                }
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        }
    }
}
