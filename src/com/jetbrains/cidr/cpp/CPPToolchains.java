// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.Version;
import com.intellij.openapi.application.Application;
import com.intellij.util.messages.Topic;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.Pair;
import com.intellij.util.Processor;
import java.util.function.Consumer;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.jetbrains.cidr.cpp.toolchains.GDB;
import com.jetbrains.cidr.cpp.toolchains.CMake;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import com.jetbrains.cidr.cpp.toolchains.Cygwin;
import com.jetbrains.cidr.cpp.toolchains.MinGW;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import com.jetbrains.cidr.cpp.toolchains.MSVC;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.SystemInfo;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.components.RoamingType;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.PersistentStateComponent;

@State(name = "CPPToolchains", storages = { @Storage(value = "other.xml", roamingType = RoamingType.DISABLED) })
public class CPPToolchains implements PersistentStateComponent<Settings>
{
    public static Settings InitialAppState;
    private final boolean myIsWindows;
    private final boolean myIsMac;
    private final boolean myShouldDetectToolchains;
    @NotNull
    private volatile Settings mySettings_DoNotAccess;
    
    public CPPToolchains() {
        this(SystemInfo.isWindows, SystemInfo.isMac, true);
    }
    
    CPPToolchains(final boolean myIsWindows, final boolean myIsMac, final boolean myShouldDetectToolchains) {
        this.myIsWindows = myIsWindows;
        this.myIsMac = myIsMac;
        this.myShouldDetectToolchains = myShouldDetectToolchains;
        if (CPPToolchains.InitialAppState == null) {
            this.mySettings_DoNotAccess = new Settings(myIsWindows, myIsMac);
        }
        else {
            this.mySettings_DoNotAccess = CPPToolchains.InitialAppState;
            CPPToolchains.InitialAppState = null;
        }
    }
    
    @NotNull
    public static CPPToolchains getInstance() {
        CPPToolchains cppToolchains = null;
        Label_0039: {
            try {
                if (ApplicationManager.getApplication().isUnitTestMode()) {
                    final CPPToolchains cppToolchains2;
                    cppToolchains = (cppToolchains2 = new CPPTestToolchains(SystemInfo.isWindows, SystemInfo.isMac));
                    break Label_0039;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            CPPToolchains cppToolchains2;
            cppToolchains = (cppToolchains2 = (CPPToolchains)ServiceManager.getService((Class)CPPToolchains.class));
            try {
                if (cppToolchains2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPToolchains", "getInstance"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return cppToolchains;
    }
    
    protected boolean isWindows() {
        return this.myIsWindows;
    }
    
    @Nullable
    public MSVC getMSVC() {
        try {
            if (!this.myIsWindows) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final Settings state = this.getState();
        try {
            if (state.getWinEnvironment() != WinEnvironment.MSVC) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final String msvcHomePath = state.getMSVCHomePath();
        try {
            if (msvcHomePath != null) {
                final File file;
                final MSVC msvc = new MSVC(file);
                file = new File(msvcHomePath);
                return msvc;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    @Nullable
    public MinGW getMinGW() {
        try {
            if (!this.myIsWindows) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final Settings state = this.getState();
        try {
            if (state.getWinEnvironment() != WinEnvironment.MINGW) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final String minGWHomePath = state.getMinGWHomePath();
        try {
            if (minGWHomePath != null) {
                final File file;
                final MinGW minGW = new MinGW(file);
                file = new File(minGWHomePath);
                return minGW;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    @Nullable
    public Cygwin getCygwin() {
        try {
            if (!this.myIsWindows) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final Settings state = this.getState();
        try {
            if (state.getWinEnvironment() != WinEnvironment.CYGWIN) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final String cygwinHomePath = state.getCygwinHomePath();
        try {
            if (cygwinHomePath != null) {
                final File file;
                final Cygwin cygwin = new Cygwin(file);
                file = new File(cygwinHomePath);
                return cygwin;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    @Nullable
    public CidrToolSet getToolSet() {
        try {
            if (!this.isWindows()) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final MSVC msvc = this.getMSVC();
        Label_0041: {
            try {
                if (msvc == null) {
                    break Label_0041;
                }
                final boolean b = MSVC.isEnabled();
                if (b) {
                    return msvc;
                }
                break Label_0041;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final boolean b = MSVC.isEnabled();
                if (b) {
                    return msvc;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        final Cygwin cygwin = this.getCygwin();
        try {
            if (cygwin != null) {
                return cygwin;
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        final MinGW minGW = this.getMinGW();
        try {
            if (minGW != null) {
                return minGW;
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        return null;
    }
    
    @Nullable
    public CMake getCMake() {
        final Settings state = this.getState();
        try {
            if (state.isUseBundledCMake()) {
                return CMake.getBundledCMake(this.getToolSet());
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final String specifiedCMakeExecutablePath = state.getSpecifiedCMakeExecutablePath();
        try {
            if (specifiedCMakeExecutablePath != null) {
                final File file;
                final CMake cMake = new CMake(file, this.getToolSet());
                file = new File(specifiedCMakeExecutablePath);
                return cMake;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @Nullable
    public GDB getGDB() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains.getState:()Lcom/jetbrains/cidr/cpp/CPPToolchains$Settings;
        //     4: astore_1       
        //     5: aload_0        
        //     6: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains.getToolSet:()Lcom/jetbrains/cidr/toolchains/CidrToolSet;
        //     9: astore_2       
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/cpp/CPPToolchains.myIsWindows:Z
        //    14: ifeq            59
        //    17: aload_2        
        //    18: ifnull          59
        //    21: goto            28
        //    24: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    27: athrow         
        //    28: aload_2        
        //    29: invokevirtual   com/jetbrains/cidr/toolchains/CidrToolSet.forceToolSetGDB:()Z
        //    32: ifeq            59
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    41: athrow         
        //    42: new             Lcom/jetbrains/cidr/cpp/toolchains/GDB;
        //    45: dup            
        //    46: aload_2        
        //    47: invokevirtual   com/jetbrains/cidr/toolchains/CidrToolSet.getGDBPath:()Ljava/io/File;
        //    50: aload_2        
        //    51: invokespecial   com/jetbrains/cidr/cpp/toolchains/GDB.<init>:(Ljava/io/File;Lcom/jetbrains/cidr/toolchains/CidrToolSet;)V
        //    54: areturn        
        //    55: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    58: athrow         
        //    59: aload_0        
        //    60: aload_1        
        //    61: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains.isUseBundledGDB:(Lcom/jetbrains/cidr/cpp/CPPToolchains$Settings;)Z
        //    64: ifeq            76
        //    67: aload_2        
        //    68: invokestatic    com/jetbrains/cidr/cpp/toolchains/GDB.getBundledGDB:(Lcom/jetbrains/cidr/toolchains/CidrToolSet;)Lcom/jetbrains/cidr/cpp/toolchains/GDB;
        //    71: areturn        
        //    72: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    75: athrow         
        //    76: aload_0        
        //    77: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains.getState:()Lcom/jetbrains/cidr/cpp/CPPToolchains$Settings;
        //    80: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains$Settings.getSpecifiedGDBExecutablePath:()Ljava/lang/String;
        //    83: astore_3       
        //    84: aload_3        
        //    85: ifnull          111
        //    88: new             Lcom/jetbrains/cidr/cpp/toolchains/GDB;
        //    91: dup            
        //    92: new             Ljava/io/File;
        //    95: dup            
        //    96: aload_3        
        //    97: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   100: aload_2        
        //   101: invokespecial   com/jetbrains/cidr/cpp/toolchains/GDB.<init>:(Ljava/io/File;Lcom/jetbrains/cidr/toolchains/CidrToolSet;)V
        //   104: goto            112
        //   107: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   110: athrow         
        //   111: aconst_null    
        //   112: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  10     21     24     28     Ljava/lang/IllegalStateException;
        //  17     35     38     42     Ljava/lang/IllegalStateException;
        //  28     55     55     59     Ljava/lang/IllegalStateException;
        //  59     72     72     76     Ljava/lang/IllegalStateException;
        //  84     107    107    111    Ljava/lang/IllegalStateException;
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
    public GDB getBundledGDB() {
        GDB bundledGDB;
        try {
            bundledGDB = GDB.getBundledGDB(this.getToolSet());
            if (bundledGDB == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPToolchains", "getBundledGDB"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return bundledGDB;
    }
    
    protected boolean isUseBundledGDB(@NotNull final Settings settings) {
        try {
            if (settings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/cpp/CPPToolchains", "isUseBundledGDB"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return settings.isUseBundledGDB();
    }
    
    public boolean isUseGDB() {
        try {
            if (!this.isUseLLDB()) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean isUseBundleGDB() {
        return this.isUseBundledGDB(this.getState());
    }
    
    public boolean isUseLLDB() {
        return this.getState().isUseBundledLLDB();
    }
    
    public boolean isLLDBAvailable() {
        return isLLDBAvailableStatic();
    }
    
    public static boolean isLLDBAvailableStatic() {
        return a(SystemInfo.isWindows);
    }
    
    private static boolean a(final boolean b) {
        try {
            if (!b) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean isGDBAvailable() {
        return true;
    }
    
    @NotNull
    public List<String> getDebuggers() {
        List<Object> unmodifiableList;
        try {
            unmodifiableList = (List<Object>)Collections.unmodifiableList((List<? extends String>)this.getState().getDebuggers());
            if (unmodifiableList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPToolchains", "getDebuggers"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (List<String>)unmodifiableList;
    }
    
    public void setDebuggers(@NotNull final List<String> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "debuggers", "com/jetbrains/cidr/cpp/CPPToolchains", "setDebuggers"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.getState().setDebuggers(new ArrayList<String>(list));
    }
    
    private void a(@NotNull final Settings settings) {
        try {
            if (settings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/cpp/CPPToolchains", "detectOnFirstAccess"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        detectOnFirstAccess(settings, 5, this.myIsWindows, this.myIsMac, processor -> MSVC.processVisualStudioInstallations(processor), processor2 -> MinGW.processMinGWInstallations(processor2), processor3 -> Cygwin.processCygwinInstallations(processor3));
    }
    
    static void detectOnFirstAccess(@NotNull final Settings settings, final int detectedVersion, final boolean b, final boolean b2, @NotNull final Consumer<Processor<Pair<File, Boolean>>> consumer, @NotNull final Consumer<Processor<File>> consumer2, @NotNull final Consumer<Processor<Pair<File, Boolean>>> consumer3) {
        try {
            if (settings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/cpp/CPPToolchains", "detectOnFirstAccess"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (consumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "msvcInstallations", "com/jetbrains/cidr/cpp/CPPToolchains", "detectOnFirstAccess"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (consumer2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "mingwInstallations", "com/jetbrains/cidr/cpp/CPPToolchains", "detectOnFirstAccess"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            if (consumer3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cygwinInstallations", "com/jetbrains/cidr/cpp/CPPToolchains", "detectOnFirstAccess"));
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        final int detectedVersion2 = settings.getDetectedVersion();
        try {
            if (detectedVersion2 == detectedVersion) {
                return;
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        settings.setDetectedVersion(detectedVersion);
        settings.b(true);
        try {
            if (b) {
                Label_0347: {
                    Label_0229: {
                        try {
                            if (detectedVersion2 >= 5) {
                                break Label_0347;
                            }
                            final boolean b3 = MSVC.isEnabled();
                            if (b3) {
                                break Label_0229;
                            }
                            break Label_0347;
                        }
                        catch (IllegalStateException ex6) {
                            throw a(ex6);
                        }
                        try {
                            final boolean b3 = MSVC.isEnabled();
                            if (!b3) {
                                break Label_0347;
                            }
                            if (settings.getMSVCHomePath() != null) {
                                break Label_0347;
                            }
                        }
                        catch (IllegalStateException ex7) {
                            throw a(ex7);
                        }
                    }
                    final Ref create = Ref.create();
                    CPPLog.LOG.info("Looking for MSVC...");
                    consumer.accept((Processor<Pair<File, Boolean>>)(pair -> {
                        final Boolean b = (Boolean)pair.second;
                        Label_0044: {
                            Label_0029: {
                                try {
                                    if (create.isNull()) {
                                        break Label_0029;
                                    }
                                    final Boolean b2 = b;
                                    final boolean b3 = b2;
                                    if (b3) {
                                        break Label_0029;
                                    }
                                    break Label_0044;
                                }
                                catch (IllegalStateException ex) {
                                    throw a(ex);
                                }
                                try {
                                    final Boolean b2 = b;
                                    final boolean b3 = b2;
                                    if (b3) {
                                        create.set(pair.first);
                                    }
                                }
                                catch (IllegalStateException ex2) {
                                    throw a(ex2);
                                }
                            }
                            try {
                                if (!b) {
                                    return true;
                                }
                            }
                            catch (IllegalStateException ex3) {
                                throw a(ex3);
                            }
                        }
                        return false;
                    }));
                    if (!create.isNull()) {
                        CPPLog.LOG.info("MSVC found at " + create.get());
                        settings.setMSVCHomePath(((File)create.get()).getPath());
                        final WinEnvironment winEnvironment = settings.getWinEnvironment();
                        try {
                            if (winEnvironment == null) {
                                settings.setWinEnvironment(WinEnvironment.MSVC);
                            }
                        }
                        catch (IllegalStateException ex8) {
                            throw a(ex8);
                        }
                    }
                }
                if (detectedVersion2 < 3) {
                    WinEnvironment winEnvironment2 = settings.getWinEnvironment();
                    String minGWHomePath = settings.getMinGWHomePath();
                    String cygwinHomePath = settings.getCygwinHomePath();
                    if (minGWHomePath == null) {
                        CPPLog.LOG.info("Looking for MinGW...");
                        final File[] array = { null };
                        consumer2.accept((Processor<File>)(file -> {
                            array[0] = file;
                            return false;
                        }));
                        if (array[0] != null) {
                            minGWHomePath = array[0].getPath();
                            CPPLog.LOG.info("MinGW found at " + minGWHomePath);
                        }
                    }
                    if (cygwinHomePath == null) {
                        CPPLog.LOG.info("Looking for Cygwin...");
                        final File[] array2 = { null };
                        consumer3.accept((Processor<Pair<File, Boolean>>)(pair -> {
                            try {
                                array2[0] = (File)pair.first;
                                if (!(boolean)pair.second) {
                                    return true;
                                }
                            }
                            catch (IllegalStateException ex) {
                                throw a(ex);
                            }
                            return false;
                        }));
                        if (array2[0] != null) {
                            cygwinHomePath = array2[0].getPath();
                            CPPLog.LOG.info("Cygwin found at " + cygwinHomePath);
                        }
                    }
                    Label_0556: {
                        Label_0546: {
                            try {
                                if (winEnvironment2 != null) {
                                    break Label_0556;
                                }
                                if (minGWHomePath == null) {
                                    break Label_0546;
                                }
                            }
                            catch (IllegalStateException ex9) {
                                throw a(ex9);
                            }
                            winEnvironment2 = WinEnvironment.MINGW;
                            break Label_0556;
                        }
                        if (cygwinHomePath != null) {
                            winEnvironment2 = WinEnvironment.CYGWIN;
                        }
                    }
                    settings.setWinEnvironment(winEnvironment2);
                    settings.setMinGWHomePath(minGWHomePath);
                    settings.setCygwinHomePath(cygwinHomePath);
                }
            }
            else {
                Label_0594: {
                    try {
                        if (!b2) {
                            return;
                        }
                        final int n = detectedVersion2;
                        final int n2 = 4;
                        if (n < n2) {
                            break Label_0594;
                        }
                        return;
                    }
                    catch (IllegalStateException ex10) {
                        throw a(ex10);
                    }
                    try {
                        final int n = detectedVersion2;
                        final int n2 = 4;
                        if (n < n2) {
                            settings.setUseBundledLLDB(true);
                        }
                    }
                    catch (IllegalStateException ex11) {
                        throw a(ex11);
                    }
                }
            }
        }
        finally {
            settings.b(false);
        }
    }
    
    @NotNull
    public Settings getState() {
        try {
            if (this.myShouldDetectToolchains) {
                this.a(this.mySettings_DoNotAccess);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Settings mySettings_DoNotAccess;
        try {
            mySettings_DoNotAccess = this.mySettings_DoNotAccess;
            if (mySettings_DoNotAccess == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPToolchains", "getState"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return mySettings_DoNotAccess;
    }
    
    public void loadState(@NotNull final Settings mySettings_DoNotAccess) {
        try {
            if (mySettings_DoNotAccess == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/cpp/CPPToolchains", "loadState"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.mySettings_DoNotAccess = mySettings_DoNotAccess;
    }
    
    public static void notifyEnvironmentChanged() {
        final Application application = ApplicationManager.getApplication();
        try {
            if (application != null) {
                ((CPPToolchainsListener)application.getMessageBus().syncPublisher((Topic)CPPToolchainsListener.TOPIC)).environmentChanged();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
    }
    
    @Nullable
    public static String checkVersion(@NotNull final Version p0, @NotNull final Version p1, @NotNull final Version p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "min"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/CPPToolchains"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkVersion"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "max"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/CPPToolchains"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "checkVersion"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "version"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/cpp/CPPToolchains"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "checkVersion"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   131: athrow         
        //   132: aload_0        
        //   133: aload_2        
        //   134: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.a:(Lcom/intellij/openapi/util/Version;Lcom/intellij/openapi/util/Version;)I
        //   137: iflt            155
        //   140: aload_1        
        //   141: aload_2        
        //   142: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.a:(Lcom/intellij/openapi/util/Version;Lcom/intellij/openapi/util/Version;)I
        //   145: ifle            232
        //   148: goto            155
        //   151: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   154: athrow         
        //   155: aload_0        
        //   156: aload_1        
        //   157: invokevirtual   com/intellij/openapi/util/Version.equals:(Ljava/lang/Object;)Z
        //   160: ifeq            204
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   169: athrow         
        //   170: ldc             "settings.select.UnsupportedVersion"
        //   172: iconst_3       
        //   173: anewarray       Ljava/lang/Object;
        //   176: dup            
        //   177: iconst_0       
        //   178: aload_2        
        //   179: aastore        
        //   180: dup            
        //   181: iconst_1       
        //   182: aload_0        
        //   183: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.toWildcardString:(Lcom/intellij/openapi/util/Version;)Ljava/lang/String;
        //   186: aastore        
        //   187: dup            
        //   188: iconst_2       
        //   189: aload_1        
        //   190: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.toWildcardString:(Lcom/intellij/openapi/util/Version;)Ljava/lang/String;
        //   193: aastore        
        //   194: invokestatic    com/jetbrains/cidr/cpp/CPPBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   197: goto            231
        //   200: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   203: athrow         
        //   204: ldc             "settings.select.UnsupportedVersion.range"
        //   206: iconst_3       
        //   207: anewarray       Ljava/lang/Object;
        //   210: dup            
        //   211: iconst_0       
        //   212: aload_2        
        //   213: aastore        
        //   214: dup            
        //   215: iconst_1       
        //   216: aload_0        
        //   217: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.toWildcardString:(Lcom/intellij/openapi/util/Version;)Ljava/lang/String;
        //   220: aastore        
        //   221: dup            
        //   222: iconst_2       
        //   223: aload_1        
        //   224: invokestatic    com/jetbrains/cidr/cpp/CPPToolchains.toWildcardString:(Lcom/intellij/openapi/util/Version;)Ljava/lang/String;
        //   227: aastore        
        //   228: invokestatic    com/jetbrains/cidr/cpp/CPPBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   231: areturn        
        //   232: aconst_null    
        //   233: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     84     84     88     Ljava/lang/IllegalStateException;
        //  88     128    128    132    Ljava/lang/IllegalStateException;
        //  132    148    151    155    Ljava/lang/IllegalStateException;
        //  140    163    166    170    Ljava/lang/IllegalStateException;
        //  155    200    200    204    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0155:
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
    public static String toWildcardString(@NotNull final Version version) {
        try {
            if (version == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "version", "com/jetbrains/cidr/cpp/CPPToolchains", "toWildcardString"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final StringBuilder sb = new StringBuilder();
        String string = null;
        Label_0136: {
            Label_0129: {
                Label_0098: {
                    try {
                        sb.append(version.major);
                        if (version.minor == -1) {
                            break Label_0129;
                        }
                        final StringBuilder sb2 = sb;
                        final String s = ".";
                        final StringBuilder sb3 = sb2.append(s);
                        final Version version2 = version;
                        final int n = version2.minor;
                        sb3.append(n);
                        final Version version3 = version;
                        final int n2 = version3.bugfix;
                        final int n3 = -1;
                        if (n2 != n3) {
                            break Label_0098;
                        }
                        break Label_0098;
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final StringBuilder sb2 = sb;
                        final String s = ".";
                        final StringBuilder sb3 = sb2.append(s);
                        final Version version2 = version;
                        final int n = version2.minor;
                        sb3.append(n);
                        final Version version3 = version;
                        final int n2 = version3.bugfix;
                        final int n3 = -1;
                        if (n2 != n3) {
                            sb.append(".").append(version.bugfix);
                            break Label_0136;
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                sb.append(".x");
                break Label_0136;
            }
            sb.append(".x");
            try {
                string = sb.toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPToolchains", "toWildcardString"));
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        return string;
    }
    
    private static int a(@NotNull final Version version, @NotNull final Version version2) {
        try {
            if (version == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseline", "com/jetbrains/cidr/cpp/CPPToolchains", "compareVersions"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (version2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "version", "com/jetbrains/cidr/cpp/CPPToolchains", "compareVersions"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        Integer value = null;
        Integer value2 = null;
        Label_0135: {
            Label_0112: {
                try {
                    if (version.major == -1) {
                        value = null;
                        break Label_0112;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                value = version.major;
                try {
                    if (version.minor == -1) {
                        value2 = null;
                        break Label_0135;
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            value2 = version.minor;
            try {
                if (version.bugfix == -1) {
                    final Integer value3 = null;
                    return version2.compareTo(value, value2, value3);
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
        }
        final Integer value3 = version.bugfix;
        return version2.compareTo(value, value2, value3);
    }
    
    @Nullable
    public static String preparePath(@Nullable final String s) {
        return StringUtil.nullize(StringUtil.trim(s), true);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    public enum WinEnvironment
    {
        MSVC(CPPBundle.message("msvc", new Object[0])), 
        MINGW(CPPBundle.message("mingw", new Object[0])), 
        CYGWIN(CPPBundle.message("cygwin", new Object[0]));
        
        private String myDisplayName;
        
        private WinEnvironment(final String myDisplayName) {
            this.myDisplayName = myDisplayName;
        }
        
        @NotNull
        public String getDisplayName() {
            String myDisplayName;
            try {
                myDisplayName = this.myDisplayName;
                if (myDisplayName == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPToolchains$WinEnvironment", "getDisplayName"));
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            return myDisplayName;
        }
        
        public CidrToolSet create(@NotNull final File file) {
            try {
                if (file == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "home", "com/jetbrains/cidr/cpp/CPPToolchains$WinEnvironment", "create"));
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            try {
                switch (this) {
                    case MSVC: {
                        return new MSVC(file);
                    }
                    case MINGW: {
                        break;
                    }
                    case CYGWIN: {
                        return new Cygwin(file);
                    }
                    default: {
                        throw new RuntimeException("Add creation support for " + this);
                    }
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            return new MinGW(file);
        }
        
        private static RuntimeException b(final RuntimeException ex) {
            return ex;
        }
    }
    
    public static class Settings
    {
        private int myDetectedVersion;
        private boolean myDoNotNotify;
        private boolean myIsWindows;
        private boolean myIsMac;
        @Nullable
        private WinEnvironment myWinEnvironment;
        @Nullable
        private String myMSVCHomePath;
        @Nullable
        private String myMinGWHomePath;
        @Nullable
        private String myCygwinHomePath;
        private boolean myUseBundledCMake;
        @Nullable
        private String mySpecifiedCMakeExecutablePath;
        private boolean myUseBundledLLDB;
        private boolean myUseBundledGDB;
        @Nullable
        private String mySpecifiedGDBExecutablePath;
        @NotNull
        private List<String> myDebuggers;
        
        public Settings() {
            this(SystemInfo.isWindows, SystemInfo.isMac);
        }
        
        public Settings(final boolean myIsWindows, final boolean myIsMac) {
            this.myUseBundledCMake = true;
            this.myDebuggers = new ArrayList<String>();
            this.myIsMac = myIsMac;
            this.myIsWindows = myIsWindows;
            this.myUseBundledLLDB = this.myIsMac;
            this.myUseBundledGDB = !this.myUseBundledLLDB;
        }
        
        public int getDetectedVersion() {
            return this.myDetectedVersion;
        }
        
        public void setDetectedVersion(final int myDetectedVersion) {
            this.myDetectedVersion = myDetectedVersion;
        }
        
        private void b(final boolean myDoNotNotify) {
            this.myDoNotNotify = myDoNotNotify;
        }
        
        private void a(final boolean b) {
            Label_0018: {
                try {
                    if (!b) {
                        return;
                    }
                    final Settings settings = this;
                    final boolean b2 = settings.myDoNotNotify;
                    if (!b2) {
                        break Label_0018;
                    }
                    return;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final Settings settings = this;
                    final boolean b2 = settings.myDoNotNotify;
                    if (!b2) {
                        CPPToolchains.notifyEnvironmentChanged();
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
        }
        
        @Nullable
        public WinEnvironment getWinEnvironment() {
            return this.myWinEnvironment;
        }
        
        public void setWinEnvironment(@Nullable final WinEnvironment myWinEnvironment) {
            boolean b = false;
            Label_0017: {
                try {
                    if (this.myWinEnvironment != myWinEnvironment) {
                        b = true;
                        break Label_0017;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                b = false;
            }
            final boolean b2 = b;
            this.myWinEnvironment = myWinEnvironment;
            this.a(b2);
        }
        
        @Nullable
        public String getMSVCHomePath() {
            return CPPToolchains.preparePath(this.myMSVCHomePath);
        }
        
        public void setMSVCHomePath(@Nullable String preparePath) {
            preparePath = CPPToolchains.preparePath(preparePath);
            boolean b = false;
            Label_0025: {
                try {
                    if (!FileUtil.pathsEqual(this.myMSVCHomePath, preparePath)) {
                        b = true;
                        break Label_0025;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                b = false;
            }
            final boolean b2 = b;
            this.myMSVCHomePath = preparePath;
            this.a(b2);
        }
        
        @Nullable
        public String getMinGWHomePath() {
            return CPPToolchains.preparePath(this.myMinGWHomePath);
        }
        
        public void setMinGWHomePath(@Nullable String preparePath) {
            preparePath = CPPToolchains.preparePath(preparePath);
            boolean b = false;
            Label_0025: {
                try {
                    if (!FileUtil.pathsEqual(this.myMinGWHomePath, preparePath)) {
                        b = true;
                        break Label_0025;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                b = false;
            }
            final boolean b2 = b;
            this.myMinGWHomePath = preparePath;
            this.a(b2);
        }
        
        @Nullable
        public String getCygwinHomePath() {
            return CPPToolchains.preparePath(this.myCygwinHomePath);
        }
        
        public void setCygwinHomePath(@Nullable String preparePath) {
            preparePath = CPPToolchains.preparePath(preparePath);
            boolean b = false;
            Label_0025: {
                try {
                    if (!FileUtil.pathsEqual(this.myCygwinHomePath, preparePath)) {
                        b = true;
                        break Label_0025;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                b = false;
            }
            final boolean b2 = b;
            this.myCygwinHomePath = preparePath;
            this.a(b2);
        }
        
        public boolean isUseBundledCMake() {
            return this.myUseBundledCMake;
        }
        
        public void setUseBundledCMake(final boolean myUseBundledCMake) {
            boolean b = false;
            Label_0017: {
                try {
                    if (this.myUseBundledCMake != myUseBundledCMake) {
                        b = true;
                        break Label_0017;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                b = false;
            }
            final boolean b2 = b;
            this.myUseBundledCMake = myUseBundledCMake;
            this.a(b2);
        }
        
        @Nullable
        public String getSpecifiedCMakeExecutablePath() {
            return CPPToolchains.preparePath(this.mySpecifiedCMakeExecutablePath);
        }
        
        public void setSpecifiedCMakeExecutablePath(@Nullable String preparePath) {
            preparePath = CPPToolchains.preparePath(preparePath);
            boolean b = false;
            Label_0025: {
                try {
                    if (!FileUtil.pathsEqual(this.mySpecifiedCMakeExecutablePath, preparePath)) {
                        b = true;
                        break Label_0025;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                b = false;
            }
            final boolean b2 = b;
            this.mySpecifiedCMakeExecutablePath = preparePath;
            this.a(b2);
        }
        
        public boolean isUseBundledLLDB() {
            return this.myUseBundledLLDB;
        }
        
        public void setUseBundledLLDB(final boolean myUseBundledLLDB) {
            try {
                if (!a(this.myIsWindows)) {
                    this.myUseBundledLLDB = false;
                    this.myUseBundledGDB = true;
                    return;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                if (this.myUseBundledLLDB = myUseBundledLLDB) {
                    this.myUseBundledGDB = false;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        
        public boolean isUseBundledGDB() {
            return this.myUseBundledGDB;
        }
        
        public void setUseBundledGDB(final boolean myUseBundledGDB) {
            try {
                this.myUseBundledGDB = myUseBundledGDB;
                if (myUseBundledGDB) {
                    this.myUseBundledLLDB = false;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
        
        @Nullable
        public String getSpecifiedGDBExecutablePath() {
            return CPPToolchains.preparePath(this.mySpecifiedGDBExecutablePath);
        }
        
        public void setSpecifiedGDBExecutablePath(@Nullable final String s) {
            this.mySpecifiedGDBExecutablePath = CPPToolchains.preparePath(s);
        }
        
        @NotNull
        public List<String> getDebuggers() {
            List<String> myDebuggers;
            try {
                myDebuggers = this.myDebuggers;
                if (myDebuggers == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPToolchains$Settings", "getDebuggers"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return myDebuggers;
        }
        
        public void setDebuggers(@NotNull final List<String> myDebuggers) {
            try {
                if (myDebuggers == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "debuggers", "com/jetbrains/cidr/cpp/CPPToolchains$Settings", "setDebuggers"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            this.myDebuggers = myDebuggers;
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
}
