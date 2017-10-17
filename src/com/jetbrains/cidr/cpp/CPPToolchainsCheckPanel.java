// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.awt.Cursor;
import javax.swing.JTextPane;
import com.intellij.util.PairFunction;
import com.intellij.openapi.ui.Messages;
import java.awt.Graphics;
import com.intellij.openapi.util.Disposer;
import java.util.LinkedHashSet;
import java.util.Set;
import com.intellij.util.ui.Animator;
import com.intellij.icons.AllIcons;
import javax.swing.JComponent;
import com.intellij.util.ui.FilePathSplittingPolicy;
import javax.swing.Icon;
import com.intellij.ui.SimpleTextAttributes;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Container;
import com.intellij.util.ui.AbstractLayoutManager;
import java.awt.Dimension;
import java.awt.Component;
import com.intellij.ui.components.labels.LinkLabel;
import com.intellij.ui.SimpleColoredComponent;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.CMakeRunner;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import com.intellij.execution.process.ProcessOutput;
import com.jetbrains.cidr.cpp.cmake.model.CMakeGenerator;
import com.intellij.util.concurrency.AppExecutorUtil;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.cpp.cmake.model.CMakeVariable;
import com.jetbrains.cidr.cpp.cmake.model.CMakeCacheFile;
import java.util.regex.Pattern;
import com.intellij.util.ExceptionUtil;
import com.jetbrains.cidr.cpp.cmake.CMakeException;
import java.io.File;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.cpp.cmake.CMakeEnvironment;
import java.util.Collections;
import com.intellij.openapi.util.Ref;
import java.util.concurrent.TimeoutException;
import com.jetbrains.cidr.ToolVersion;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import com.jetbrains.cidr.cpp.toolchains.GDB;
import com.jetbrains.cidr.cpp.toolchains.MSVC;
import com.jetbrains.cidr.cpp.toolchains.CMake;
import java.util.Iterator;
import com.intellij.util.ui.UIUtil;
import javax.swing.Box;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.GridBag;
import java.util.ArrayList;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.Disposable;
import com.intellij.util.Alarm;
import java.util.List;
import javax.swing.JPanel;

public class CPPToolchainsCheckPanel extends JPanel
{
    private static final String NOT_FOUND = "not found";
    private static final String NOT_SELECTED = "not selected";
    private final boolean isWindows;
    private final List<CheckLabel> myAllLabels;
    private final List<CheckLabel> myCMakeLabels;
    private final List<CheckLabel> myCMakeSubLabels;
    private final List<CheckLabel> myGDBLabels;
    private final CheckLabel myEnvironmentLabel;
    private final CheckLabel myCMakeLabel;
    private final CheckLabel myMakeLabel;
    private final CheckLabel myCCompilerLabel;
    private final CheckLabel myCXXCompilerLabel;
    private final CheckLabel myGDBLabel;
    private final ProgressIcon myProgressIcon;
    private final Alarm myAlarm;
    
    public CPPToolchainsCheckPanel(@NotNull final Disposable disposable, final boolean isWindows) {
        if (disposable == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "disposable", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel", "<init>"));
        }
        super(new GridBagLayout());
        this.myAllLabels = new ArrayList<CheckLabel>();
        this.myCMakeLabels = new ArrayList<CheckLabel>();
        this.myCMakeSubLabels = new ArrayList<CheckLabel>();
        this.myGDBLabels = new ArrayList<CheckLabel>();
        this.isWindows = isWindows;
        this.myProgressIcon = new ProgressIcon(disposable);
        this.myAlarm = new Alarm(Alarm.ThreadToUse.POOLED_THREAD, disposable);
        final GridBag setDefaultWeightX = new GridBag().setDefaultFill(2).setDefaultWeightX(1.0);
        Label_0205: {
            try {
                if (isWindows) {
                    this.myEnvironmentLabel = new CheckLabel("Environment", false, false, setDefaultWeightX, this);
                    this.myAllLabels.add(this.myEnvironmentLabel);
                    this.myCMakeLabels.add(this.myEnvironmentLabel);
                    break Label_0205;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            this.myEnvironmentLabel = null;
        }
        this.myCMakeLabel = new CheckLabel(CPPBundle.message("cmake", new Object[0]), false, true, setDefaultWeightX, this);
        this.myMakeLabel = new CheckLabel("make", true, false, true, setDefaultWeightX, this);
        this.myCCompilerLabel = new CheckLabel("C Compiler", true, false, true, setDefaultWeightX, this);
        this.myCXXCompilerLabel = new CheckLabel("C++ Compiler", true, false, true, setDefaultWeightX, this);
        this.myGDBLabel = new CheckLabel(CPPBundle.message("gdb", new Object[0]), false, false, setDefaultWeightX, this);
        ContainerUtil.addAll((Collection)this.myAllLabels, (Object[])new CheckLabel[] { this.myCMakeLabel, this.myMakeLabel, this.myCCompilerLabel, this.myCXXCompilerLabel, this.myGDBLabel });
        ContainerUtil.addAll((Collection)this.myCMakeLabels, (Object[])new CheckLabel[] { this.myCMakeLabel, this.myMakeLabel, this.myCCompilerLabel, this.myCXXCompilerLabel });
        ContainerUtil.addAll((Collection)this.myCMakeSubLabels, (Object[])new CheckLabel[] { this.myMakeLabel, this.myCCompilerLabel, this.myCXXCompilerLabel });
        ContainerUtil.addAll((Collection)this.myGDBLabels, (Object[])new CheckLabel[] { this.myGDBLabel });
        this.add(Box.createVerticalGlue(), setDefaultWeightX.nextLine().weighty(1.0));
        this.a(this.myAllLabels);
    }
    
    public List<CheckLabel> getAllLabels() {
        return this.myAllLabels;
    }
    
    public void scheduleCheck(@NotNull final CPPToolchainsCheckSettings cppToolchainsCheckSettings, @NotNull final UpdateType updateType) {
        try {
            if (cppToolchainsCheckSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel", "scheduleCheck"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (updateType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "updateType", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel", "scheduleCheck"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        this.myAlarm.cancelAllRequests();
        final IllegalArgumentException ex3;
        final IllegalArgumentException ex5;
        this.myAlarm.addRequest(() -> {
            try {
                if (cppToolchainsCheckSettings == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel", "lambda$scheduleCheck$0"));
                    throw ex3;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            try {
                if (updateType == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "updateType", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel", "lambda$scheduleCheck$0"));
                    throw ex5;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
            this.runCheck(cppToolchainsCheckSettings, updateType);
        }, 300);
    }
    
    public void runCheck(@NotNull final CPPToolchainsCheckSettings p0, @NotNull final UpdateType p1) {
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
        //    18: ldc             "settings"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "runCheck"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "updateType"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "runCheck"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_0        
        //    89: aload_0        
        //    90: aload_2        
        //    91: invokespecial   com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.a:(Lcom/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$UpdateType;)Ljava/util/List;
        //    94: invokespecial   com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.a:(Ljava/util/List;)V
        //    97: aload_0        
        //    98: getfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.isWindows:Z
        //   101: ifeq            310
        //   104: aload_1        
        //   105: getfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckSettings.environment:Lcom/jetbrains/cidr/cpp/CPPToolchains$WinEnvironment;
        //   108: ifnull          132
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   117: athrow         
        //   118: aload_1        
        //   119: getfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckSettings.toolSetHome:Ljava/io/File;
        //   122: ifnonnull       153
        //   125: goto            132
        //   128: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   131: athrow         
        //   132: aload_0        
        //   133: getfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.myEnvironmentLabel:Lcom/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel;
        //   136: ldc             "not selected"
        //   138: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel.error:(Ljava/lang/String;)V
        //   141: aload_1        
        //   142: iconst_1       
        //   143: putfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckSettings.invalidToolSet:Z
        //   146: goto            310
        //   149: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   152: athrow         
        //   153: aload_1        
        //   154: aload_1        
        //   155: getfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckSettings.environment:Lcom/jetbrains/cidr/cpp/CPPToolchains$WinEnvironment;
        //   158: aload_1        
        //   159: getfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckSettings.toolSetHome:Ljava/io/File;
        //   162: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchains$WinEnvironment.create:(Ljava/io/File;)Lcom/jetbrains/cidr/toolchains/CidrToolSet;
        //   165: putfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckSettings.toolSet:Lcom/jetbrains/cidr/toolchains/CidrToolSet;
        //   168: aload_1        
        //   169: getfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckSettings.toolSet:Lcom/jetbrains/cidr/toolchains/CidrToolSet;
        //   172: invokevirtual   com/jetbrains/cidr/toolchains/CidrToolSet.readVersion:()Ljava/lang/String;
        //   175: astore_3       
        //   176: aconst_null    
        //   177: astore          4
        //   179: aload_3        
        //   180: ifnull          193
        //   183: aload_1        
        //   184: getfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckSettings.toolSet:Lcom/jetbrains/cidr/toolchains/CidrToolSet;
        //   187: aload_3        
        //   188: invokevirtual   com/jetbrains/cidr/toolchains/CidrToolSet.checkVersion:(Ljava/lang/String;)Ljava/lang/String;
        //   191: astore          4
        //   193: aload_3        
        //   194: ifnonnull       246
        //   197: aload_0        
        //   198: getfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.myEnvironmentLabel:Lcom/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel;
        //   201: new             Ljava/lang/StringBuilder;
        //   204: dup            
        //   205: invokespecial   java/lang/StringBuilder.<init>:()V
        //   208: aload_1        
        //   209: getfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckSettings.toolSet:Lcom/jetbrains/cidr/toolchains/CidrToolSet;
        //   212: invokevirtual   com/jetbrains/cidr/toolchains/CidrToolSet.getName:()Ljava/lang/String;
        //   215: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   218: ldc             " "
        //   220: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   223: ldc             "not found"
        //   225: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   228: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   231: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel.error:(Ljava/lang/String;)V
        //   234: aload_1        
        //   235: iconst_1       
        //   236: putfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckSettings.invalidToolSet:Z
        //   239: goto            310
        //   242: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   245: athrow         
        //   246: aload           4
        //   248: ifnonnull       273
        //   251: aload_0        
        //   252: getfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.myEnvironmentLabel:Lcom/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel;
        //   255: aload_1        
        //   256: getfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckSettings.toolSet:Lcom/jetbrains/cidr/toolchains/CidrToolSet;
        //   259: invokevirtual   com/jetbrains/cidr/toolchains/CidrToolSet.getName:()Ljava/lang/String;
        //   262: aload_3        
        //   263: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel.ok:(Ljava/lang/String;Ljava/lang/String;)V
        //   266: goto            310
        //   269: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   272: athrow         
        //   273: aload_0        
        //   274: getfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.myEnvironmentLabel:Lcom/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel;
        //   277: new             Ljava/lang/StringBuilder;
        //   280: dup            
        //   281: invokespecial   java/lang/StringBuilder.<init>:()V
        //   284: aload_1        
        //   285: getfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckSettings.toolSet:Lcom/jetbrains/cidr/toolchains/CidrToolSet;
        //   288: invokevirtual   com/jetbrains/cidr/toolchains/CidrToolSet.getName:()Ljava/lang/String;
        //   291: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   294: ldc             "; "
        //   296: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   299: aload           4
        //   301: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   304: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   307: invokevirtual   com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel.warning:(Ljava/lang/String;)V
        //   310: aload_2        
        //   311: getstatic       com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$UpdateType.All:Lcom/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$UpdateType;
        //   314: if_acmpeq       331
        //   317: aload_2        
        //   318: getstatic       com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$UpdateType.CMake:Lcom/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$UpdateType;
        //   321: if_acmpne       343
        //   324: goto            331
        //   327: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   330: athrow         
        //   331: aload_0        
        //   332: aload_1        
        //   333: invokespecial   com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.a:(Lcom/jetbrains/cidr/cpp/CPPToolchainsCheckSettings;)V
        //   336: goto            343
        //   339: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   342: athrow         
        //   343: aload_2        
        //   344: getstatic       com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$UpdateType.All:Lcom/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$UpdateType;
        //   347: if_acmpeq       364
        //   350: aload_2        
        //   351: getstatic       com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$UpdateType.GDB:Lcom/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$UpdateType;
        //   354: if_acmpne       376
        //   357: goto            364
        //   360: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   363: athrow         
        //   364: aload_0        
        //   365: aload_1        
        //   366: invokespecial   com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Lcom/jetbrains/cidr/cpp/CPPToolchainsCheckSettings;)V
        //   369: goto            376
        //   372: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   375: athrow         
        //   376: aload_0        
        //   377: invokespecial   com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.a:()V
        //   380: aload_2        
        //   381: getstatic       com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$UpdateType.GDB:Lcom/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$UpdateType;
        //   384: if_acmpeq       411
        //   387: aload_0        
        //   388: aload_0        
        //   389: getfield        com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.myCMakeSubLabels:Ljava/util/List;
        //   392: invokespecial   com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.a:(Ljava/util/List;)V
        //   395: aload_0        
        //   396: aload_1        
        //   397: invokespecial   com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.c:(Lcom/jetbrains/cidr/cpp/CPPToolchainsCheckSettings;)V
        //   400: aload_0        
        //   401: invokespecial   com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.a:()V
        //   404: goto            411
        //   407: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   410: athrow         
        //   411: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     111    114    118    Ljava/lang/IllegalArgumentException;
        //  104    125    128    132    Ljava/lang/IllegalArgumentException;
        //  118    149    149    153    Ljava/lang/IllegalArgumentException;
        //  193    242    242    246    Ljava/lang/IllegalArgumentException;
        //  246    269    269    273    Ljava/lang/IllegalArgumentException;
        //  310    324    327    331    Ljava/lang/IllegalArgumentException;
        //  317    336    339    343    Ljava/lang/IllegalArgumentException;
        //  343    357    360    364    Ljava/lang/IllegalArgumentException;
        //  350    369    372    376    Ljava/lang/IllegalArgumentException;
        //  376    404    407    411    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0118:
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
    
    private List<CheckLabel> a(@NotNull final UpdateType updateType) {
        try {
            if (updateType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "updateType", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel", "getLabelList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            switch (updateType) {
                case CMake: {
                    return this.myCMakeLabels;
                }
                case GDB: {
                    break;
                }
                default: {
                    return this.myAllLabels;
                }
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return this.myGDBLabels;
    }
    
    private void a(final List<CheckLabel> list) {
        final Iterator<CheckLabel> iterator;
        UIUtil.invokeAndWaitIfNeeded(() -> {
            list.iterator();
            while (iterator.hasNext()) {
                iterator.next().clear();
            }
            this.revalidate();
            this.repaint();
        });
    }
    
    private void a() {
        final Iterator<CheckLabel> iterator;
        UIUtil.invokeAndWaitIfNeeded(() -> {
            this.myAllLabels.iterator();
            while (iterator.hasNext()) {
                iterator.next().update();
            }
            this.revalidate();
            this.repaint();
        });
    }
    
    private void a(final CPPToolchainsCheckSettings cppToolchainsCheckSettings) {
        try {
            if (cppToolchainsCheckSettings.invalidToolSet) {
                this.myCMakeLabel.disable();
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        CMake bundledCMake;
        if (cppToolchainsCheckSettings.useBundledCMake) {
            bundledCMake = CMake.getBundledCMake(cppToolchainsCheckSettings.toolSet);
        }
        else {
            CMake cMake = null;
            Label_0067: {
                try {
                    if (cppToolchainsCheckSettings.cmakeFile == null) {
                        cMake = null;
                        break Label_0067;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                cMake = new CMake(cppToolchainsCheckSettings.cmakeFile, cppToolchainsCheckSettings.toolSet);
            }
            bundledCMake = cMake;
        }
        String version = null;
        Label_0084: {
            try {
                if (bundledCMake == null) {
                    version = null;
                    break Label_0084;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            version = bundledCMake.readVersion();
        }
        final String s = version;
        try {
            if (s == null) {
                this.myCMakeLabel.error("not found");
                return;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final String checkVersion = CMake.checkVersion(s);
        Label_0140: {
            try {
                if (checkVersion == null) {
                    this.myCMakeLabel.ok(s);
                    break Label_0140;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
            this.myCMakeLabel.warning(checkVersion);
        }
        cppToolchainsCheckSettings.cmake = bundledCMake;
    }
    
    private void b(final CPPToolchainsCheckSettings cppToolchainsCheckSettings) {
        boolean b2 = false;
        Label_0033: {
            Label_0024: {
                try {
                    if (cppToolchainsCheckSettings.useBundledLLDB) {
                        break Label_0024;
                    }
                    final CPPToolchainsCheckSettings cppToolchainsCheckSettings2 = cppToolchainsCheckSettings;
                    final CidrToolSet set = cppToolchainsCheckSettings2.toolSet;
                    final boolean b = MSVC.isDebugSupportDisabled(set);
                    if (b) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final CPPToolchainsCheckSettings cppToolchainsCheckSettings2 = cppToolchainsCheckSettings;
                    final CidrToolSet set = cppToolchainsCheckSettings2.toolSet;
                    final boolean b = MSVC.isDebugSupportDisabled(set);
                    if (b) {
                        b2 = true;
                        break Label_0033;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            b2 = false;
        }
        final boolean b3 = b2;
        Label_0051: {
            CheckLabel myGDBLabel;
            try {
                myGDBLabel = this.myGDBLabel;
                if (!b3) {
                    final boolean visible = true;
                    break Label_0051;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            final boolean visible = false;
            try {
                myGDBLabel.setVisible(visible);
                if (b3) {
                    return;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        try {
            if (cppToolchainsCheckSettings.invalidToolSet) {
                this.myGDBLabel.disable();
                return;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        final CidrToolSet toolSet = cppToolchainsCheckSettings.toolSet;
        Label_0105: {
            try {
                if (toolSet == null) {
                    break Label_0105;
                }
                final CidrToolSet set2 = toolSet;
                final boolean b4 = set2.forceToolSetGDB();
                if (b4) {
                    break Label_0105;
                }
                break Label_0105;
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
            try {
                final CidrToolSet set2 = toolSet;
                final boolean b4 = set2.forceToolSetGDB();
                if (b4) {
                    this.a(GDB.readVersion(toolSet.getGDBPath(), toolSet));
                    return;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw b(ex7);
            }
        }
        if (cppToolchainsCheckSettings.useBundledGDB) {
            final String bundledVersion = GDB.readBundledVersion(toolSet);
            try {
                if (bundledVersion == null) {
                    this.myGDBLabel.error("unknown");
                    return;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw b(ex8);
            }
            this.myGDBLabel.ok(bundledVersion);
        }
        else {
            try {
                if (cppToolchainsCheckSettings.gdbFile == null) {
                    this.myGDBLabel.error("not found");
                    return;
                }
            }
            catch (IllegalArgumentException ex9) {
                throw b(ex9);
            }
            this.a(GDB.readVersion(cppToolchainsCheckSettings.gdbFile, toolSet));
        }
    }
    
    private void a(final ToolVersion toolVersion) {
        Label_0018: {
            try {
                if (toolVersion == null) {
                    break Label_0018;
                }
                final ToolVersion toolVersion2 = toolVersion;
                final boolean b = toolVersion2.isUnknown();
                if (b) {
                    break Label_0018;
                }
                break Label_0018;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final ToolVersion toolVersion2 = toolVersion;
                final boolean b = toolVersion2.isUnknown();
                if (b) {
                    this.myGDBLabel.error("not found");
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        final String checkVersion = GDB.checkVersion(toolVersion);
        try {
            if (checkVersion == null) {
                this.myGDBLabel.ok(toolVersion.toString());
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        this.myGDBLabel.warning(checkVersion);
    }
    
    private void c(final CPPToolchainsCheckSettings cppToolchainsCheckSettings) {
        Label_0021: {
            try {
                if (cppToolchainsCheckSettings.invalidToolSet) {
                    break Label_0021;
                }
                final CPPToolchainsCheckSettings cppToolchainsCheckSettings2 = cppToolchainsCheckSettings;
                final CMake cMake = cppToolchainsCheckSettings2.cmake;
                if (cMake == null) {
                    break Label_0021;
                }
                break Label_0021;
            }
            catch (TimeoutException ex) {
                throw b(ex);
            }
            try {
                final CPPToolchainsCheckSettings cppToolchainsCheckSettings2 = cppToolchainsCheckSettings;
                final CMake cMake = cppToolchainsCheckSettings2.cmake;
                if (cMake == null) {
                    this.b();
                    return;
                }
            }
            catch (TimeoutException ex2) {
                throw b(ex2);
            }
        }
        final Ref ref = new Ref();
        final StringBuilder sb = new StringBuilder();
        try {
            final CMakeEnvironment cMakeEnvironment = new CMakeEnvironment(cppToolchainsCheckSettings.toolSet, cppToolchainsCheckSettings.cmake, Collections.emptyList());
            ref.set((Object)FileUtil.createTempDirectory("cmake_check_environment", (String)null));
            final File tempDirectory = FileUtil.createTempDirectory((File)ref.get(), "_build", (String)null);
            FileUtil.writeToFile(new File((File)ref.get(), "CMakeLists.txt"), "cmake_minimum_required(VERSION 2.8.4)\nproject(cmake_test_run)\n\nadd_executable(cmake_test_run main.cpp)");
            FileUtil.createIfDoesntExist(new File((File)ref.get(), "main.cpp"));
            final CMakeCacheFile a = this.a((Ref<File>)ref, cMakeEnvironment, tempDirectory, sb);
            try {
                if (CPPLog.LOG.isDebugEnabled()) {
                    CPPLog.LOG.debug("CMake.checkTools() first run\n" + (Object)sb);
                }
            }
            catch (TimeoutException ex3) {
                throw b(ex3);
            }
            Label_0478: {
                Label_0440: {
                    Label_0413: {
                        Label_0262: {
                            Label_0235: {
                                try {
                                    if (cppToolchainsCheckSettings.toolSet != null) {
                                        if (cppToolchainsCheckSettings.toolSet.isMinGW()) {
                                            break Label_0235;
                                        }
                                    }
                                }
                                catch (TimeoutException ex4) {
                                    throw b(ex4);
                                }
                                return;
                                try {
                                    if (!this.myMakeLabel.isOkState()) {
                                        break Label_0262;
                                    }
                                    final CPPToolchainsCheckPanel cppToolchainsCheckPanel = this;
                                    final CheckLabel checkLabel = cppToolchainsCheckPanel.myCCompilerLabel;
                                    final boolean b = checkLabel.isOkState();
                                    if (b) {
                                        break Label_0262;
                                    }
                                    break Label_0262;
                                }
                                catch (TimeoutException ex5) {
                                    throw b(ex5);
                                }
                            }
                            try {
                                final CPPToolchainsCheckPanel cppToolchainsCheckPanel = this;
                                final CheckLabel checkLabel = cppToolchainsCheckPanel.myCCompilerLabel;
                                final boolean b = checkLabel.isOkState();
                                if (b) {
                                    if (this.myCXXCompilerLabel.isOkState()) {
                                        break Label_0413;
                                    }
                                }
                            }
                            catch (TimeoutException ex6) {
                                throw b(ex6);
                            }
                        }
                        final CMakeVariable variable = a.getVariable("CMAKE_SH");
                        try {
                            if (variable == null || variable.isNotFound()) {
                                break Label_0413;
                            }
                        }
                        catch (TimeoutException ex7) {
                            throw b(ex7);
                        }
                        final String a2 = a(variable, cMakeEnvironment);
                        Label_0381: {
                            try {
                                if (a2 == null) {
                                    break Label_0413;
                                }
                                final CPPToolchainsCheckPanel cppToolchainsCheckPanel2 = this;
                                final CheckLabel checkLabel2 = cppToolchainsCheckPanel2.myEnvironmentLabel;
                                final StringBuilder sb2 = new StringBuilder();
                                final String s = "For MinGW make to work correctly ";
                                final StringBuilder sb3 = sb2.append(s);
                                final String s2 = a2;
                                final StringBuilder sb4 = sb3.append(s2);
                                final String s3 = " must NOT be in your PATH";
                                final StringBuilder sb5 = sb4.append(s3);
                                final String s4 = sb5.toString();
                                checkLabel2.warning(s4);
                                final CPPToolchainsCheckPanel cppToolchainsCheckPanel3 = this;
                                final Ref<File> ref2 = (Ref<File>)ref;
                                final CMakeEnvironment cMakeEnvironment2 = cMakeEnvironment;
                                final File file = tempDirectory;
                                final StringBuilder sb6 = sb;
                                cppToolchainsCheckPanel3.a(ref2, cMakeEnvironment2, file, sb6);
                                final Logger logger = CPPLog.LOG;
                                final boolean b2 = logger.isDebugEnabled();
                                if (b2) {
                                    break Label_0381;
                                }
                                break Label_0413;
                            }
                            catch (TimeoutException ex8) {
                                throw b(ex8);
                            }
                            try {
                                final CPPToolchainsCheckPanel cppToolchainsCheckPanel2 = this;
                                final CheckLabel checkLabel2 = cppToolchainsCheckPanel2.myEnvironmentLabel;
                                final StringBuilder sb2 = new StringBuilder();
                                final String s = "For MinGW make to work correctly ";
                                final StringBuilder sb3 = sb2.append(s);
                                final String s2 = a2;
                                final StringBuilder sb4 = sb3.append(s2);
                                final String s3 = " must NOT be in your PATH";
                                final StringBuilder sb5 = sb4.append(s3);
                                final String s4 = sb5.toString();
                                checkLabel2.warning(s4);
                                final CPPToolchainsCheckPanel cppToolchainsCheckPanel3 = this;
                                final Ref<File> ref2 = (Ref<File>)ref;
                                final CMakeEnvironment cMakeEnvironment2 = cMakeEnvironment;
                                final File file = tempDirectory;
                                final StringBuilder sb6 = sb;
                                cppToolchainsCheckPanel3.a(ref2, cMakeEnvironment2, file, sb6);
                                final Logger logger = CPPLog.LOG;
                                final boolean b2 = logger.isDebugEnabled();
                                if (b2) {
                                    CPPLog.LOG.debug("CMake.checkTools() second run\n" + (Object)sb);
                                }
                            }
                            catch (TimeoutException ex9) {
                                throw b(ex9);
                            }
                        }
                        try {
                            if (cppToolchainsCheckSettings.toolSet.isMinGW64()) {
                                break Label_0478;
                            }
                            final CPPToolchainsCheckPanel cppToolchainsCheckPanel4 = this;
                            final CheckLabel checkLabel3 = cppToolchainsCheckPanel4.myEnvironmentLabel;
                            final boolean b3 = checkLabel3.isOkState();
                            if (b3) {
                                break Label_0440;
                            }
                            break Label_0478;
                        }
                        catch (TimeoutException ex10) {
                            throw b(ex10);
                        }
                    }
                    try {
                        final CPPToolchainsCheckPanel cppToolchainsCheckPanel4 = this;
                        final CheckLabel checkLabel3 = cppToolchainsCheckPanel4.myEnvironmentLabel;
                        final boolean b3 = checkLabel3.isOkState();
                        if (!b3) {
                            break Label_0478;
                        }
                        if (new File(cppToolchainsCheckSettings.toolSet.getHome(), "bin/zlib1.dll").exists()) {
                            break Label_0478;
                        }
                    }
                    catch (TimeoutException ex11) {
                        throw b(ex11);
                    }
                }
                this.myEnvironmentLabel.error("MinGW package mingw32-libz (zlib1.dll) not found");
                try {}
                catch (TimeoutException ex15) {}
            }
        }
        catch (TimeoutException ex16) {
            this.myCMakeLabel.error("test run timed out");
            this.b();
            if (!ref.isNull()) {
                FileUtil.delete((File)ref.get());
            }
        }
        catch (Exception ex12) {
            Label_0574: {
                try {
                    if (ExceptionUtil.causedBy((Throwable)ex12, (Class)CMakeException.class)) {
                        CPPLog.LOG.info((Throwable)ex12);
                        break Label_0574;
                    }
                }
                catch (TimeoutException ex13) {
                    throw b(ex13);
                }
                CPPLog.LOG.error((Throwable)ex12);
            }
            CPPLog.LOG.info("CMake execute result\n" + (Object)sb);
            this.myCMakeLabel.error("test run finished with");
            this.myCMakeLabel.link("errors", new Runnable() {
                String error;
                
                @Override
                public void run() {
                    if (this.error == null) {
                        final StringBuilder sb = new StringBuilder();
                        final String message = ExceptionUtil.getMessage((Throwable)ex12);
                        if (message != null) {
                            sb.append(message).append("\n\n");
                        }
                        if (Pattern.compile(".+directory \\W/cygdrive/.+ does not exist.*", 32).matcher(sb).matches()) {
                            sb.append("Selected CMake might be incompatible with Cygwin environment.\nIn order to run on Cygwin, CMake needs to be specially compiled.\n\n");
                        }
                        if (!(ex12 instanceof CMakeException)) {
                            sb.append(ExceptionUtil.getThrowableText((Throwable)ex12)).append("\n\n");
                        }
                        sb.append((CharSequence)sb);
                        this.error = sb.toString();
                    }
                    OutputMessages.showMessageDialog(this.error);
                }
            });
            this.b();
            if (!ref.isNull()) {
                FileUtil.delete((File)ref.get());
            }
        }
        finally {
            try {
                if (!ref.isNull()) {
                    FileUtil.delete((File)ref.get());
                }
            }
            catch (TimeoutException ex14) {
                throw b(ex14);
            }
        }
    }
    
    private CMakeCacheFile a(final Ref<File> ref, final CMakeEnvironment cMakeEnvironment, final File file, final StringBuilder sb) throws Exception {
        final Future<ProcessOutput> submit = AppExecutorUtil.getAppExecutorService().submit(() -> CMakeGenerator.runCMakeGenerator(this.createCMakeRunner(cMakeEnvironment), (File)ref.get(), file));
        try {
            a(submit.get(60L, TimeUnit.SECONDS), sb);
        }
        catch (Exception ex) {
            submit.cancel(true);
            throw ex;
        }
        final CMakeCacheFile cMakeCacheFile = new CMakeCacheFile(new File(file, "CMakeCache.txt"), "UTF-8");
        a(this.myMakeLabel, cMakeCacheFile.getVariable("CMAKE_MAKE_PROGRAM"), cMakeEnvironment);
        a(this.myCCompilerLabel, cMakeCacheFile.getVariable("CMAKE_C_COMPILER"), cMakeEnvironment);
        a(this.myCXXCompilerLabel, cMakeCacheFile.getVariable("CMAKE_CXX_COMPILER"), cMakeEnvironment);
        return cMakeCacheFile;
    }
    
    protected CMakeRunner createCMakeRunner(final CMakeEnvironment cMakeEnvironment) {
        return new CMakeRunner(cMakeEnvironment);
    }
    
    private static void a(final ProcessOutput processOutput, final StringBuilder sb) {
        sb.setLength(0);
        final String stdout = processOutput.getStdout();
        try {
            if (stdout.length() > 0) {
                sb.append("Output:\n").append(stdout);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final String stderr = processOutput.getStderr();
        Label_0061: {
            try {
                if (stderr.length() <= 0) {
                    return;
                }
                final String s = stdout;
                final int n = s.length();
                if (n > 0) {
                    break Label_0061;
                }
                break Label_0061;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final String s = stdout;
                final int n = s.length();
                if (n > 0) {
                    sb.append("\n");
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        sb.append("Errors:\n").append(stderr);
    }
    
    private void b() {
        this.myMakeLabel.disable();
        this.myCCompilerLabel.disable();
        this.myCXXCompilerLabel.disable();
    }
    
    private static void a(@NotNull final CheckLabel checkLabel, @Nullable final CMakeVariable cMakeVariable, @NotNull final CMakeEnvironment cMakeEnvironment) {
        try {
            if (checkLabel == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "label", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel", "checkCMakeVariable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (cMakeEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel", "checkCMakeVariable"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final String a = a(cMakeVariable, cMakeEnvironment);
        try {
            if (a == null) {
                checkLabel.error("not found");
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        checkLabel.ok(a);
    }
    
    @Nullable
    private static String a(@Nullable final CMakeVariable p0, @NotNull final CMakeEnvironment p1) {
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
        //    18: ldc             "environment"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "variableToPath"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aconst_null    
        //    45: astore_2       
        //    46: aload_0        
        //    47: ifnull          250
        //    50: aload_0        
        //    51: invokevirtual   com/jetbrains/cidr/cpp/cmake/model/CMakeVariable.getValue:()Ljava/lang/String;
        //    54: astore_2       
        //    55: aload_2        
        //    56: ifnull          250
        //    59: aload_2        
        //    60: invokestatic    com/intellij/openapi/util/io/FileUtil.toSystemIndependentName:(Ljava/lang/String;)Ljava/lang/String;
        //    63: ldc             "/"
        //    65: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //    68: ifne            192
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    77: athrow         
        //    78: new             Lcom/intellij/execution/configurations/GeneralCommandLine;
        //    81: dup            
        //    82: invokespecial   com/intellij/execution/configurations/GeneralCommandLine.<init>:()V
        //    85: astore          4
        //    87: aload_1        
        //    88: aload           4
        //    90: getstatic       com/jetbrains/cidr/lang/toolchains/CidrToolEnvironment$PrepareFor.BUILD:Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment$PrepareFor;
        //    93: invokevirtual   com/jetbrains/cidr/cpp/cmake/CMakeEnvironment.prepare:(Lcom/intellij/execution/configurations/GeneralCommandLine;Lcom/jetbrains/cidr/lang/toolchains/CidrToolEnvironment$PrepareFor;)V
        //    96: goto            103
        //    99: astore          5
        //   101: aconst_null    
        //   102: areturn        
        //   103: aload           4
        //   105: invokevirtual   com/intellij/execution/configurations/GeneralCommandLine.getEffectiveEnvironment:()Ljava/util/Map;
        //   108: ldc             "PATH"
        //   110: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   115: checkcast       Ljava/lang/String;
        //   118: astore          5
        //   120: aload_2        
        //   121: aload           5
        //   123: aconst_null    
        //   124: invokestatic    com/intellij/execution/configurations/PathEnvironmentVariableUtil.findInPath:(Ljava/lang/String;Ljava/lang/String;Ljava/io/FileFilter;)Ljava/io/File;
        //   127: astore_3       
        //   128: aload_3        
        //   129: ifnonnull       189
        //   132: getstatic       com/intellij/openapi/util/SystemInfo.isWindows:Z
        //   135: ifeq            189
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   144: athrow         
        //   145: aload_2        
        //   146: ldc             ".exe"
        //   148: invokestatic    com/intellij/openapi/util/text/StringUtil.endsWithIgnoreCase:(Ljava/lang/String;Ljava/lang/String;)Z
        //   151: ifne            189
        //   154: goto            161
        //   157: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   160: athrow         
        //   161: new             Ljava/lang/StringBuilder;
        //   164: dup            
        //   165: invokespecial   java/lang/StringBuilder.<init>:()V
        //   168: aload_2        
        //   169: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   172: ldc             ".exe"
        //   174: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   177: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   180: astore_2       
        //   181: aload_2        
        //   182: aload           5
        //   184: aconst_null    
        //   185: invokestatic    com/intellij/execution/configurations/PathEnvironmentVariableUtil.findInPath:(Ljava/lang/String;Ljava/lang/String;Ljava/io/FileFilter;)Ljava/io/File;
        //   188: astore_3       
        //   189: goto            205
        //   192: new             Ljava/io/File;
        //   195: dup            
        //   196: aload_1        
        //   197: aload_2        
        //   198: invokevirtual   com/jetbrains/cidr/cpp/cmake/CMakeEnvironment.toLocalPath:(Ljava/lang/String;)Ljava/lang/String;
        //   201: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   204: astore_3       
        //   205: aload_3        
        //   206: ifnull          248
        //   209: aload_3        
        //   210: invokevirtual   java/io/File.exists:()Z
        //   213: ifeq            248
        //   216: goto            223
        //   219: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   222: athrow         
        //   223: aload_3        
        //   224: invokevirtual   java/io/File.canExecute:()Z
        //   227: ifeq            248
        //   230: goto            237
        //   233: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   236: athrow         
        //   237: aload_3        
        //   238: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   241: goto            249
        //   244: invokestatic    com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   247: athrow         
        //   248: aconst_null    
        //   249: astore_2       
        //   250: aload_2        
        //   251: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                       
        //  -----  -----  -----  -----  -------------------------------------------
        //  55     71     74     78     Lcom/intellij/execution/ExecutionException;
        //  0      40     40     44     Lcom/intellij/execution/ExecutionException;
        //  87     96     99     103    Lcom/intellij/execution/ExecutionException;
        //  128    138    141    145    Lcom/intellij/execution/ExecutionException;
        //  132    154    157    161    Lcom/intellij/execution/ExecutionException;
        //  205    216    219    223    Lcom/intellij/execution/ExecutionException;
        //  209    230    233    237    Lcom/intellij/execution/ExecutionException;
        //  223    244    244    248    Lcom/intellij/execution/ExecutionException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0223:
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
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    public enum UpdateType
    {
        All, 
        CMake, 
        GDB;
    }
    
    enum State
    {
        OK, 
        Error, 
        Warning;
    }
    
    public class CheckLabel
    {
        private final SimpleColoredComponent myLabel;
        private final String myName;
        private final boolean myPath;
        private String myMessage;
        private String myVersion;
        private State myState;
        private boolean myDoUpdate;
        private LinkLabel myLink;
        private String myLinkText;
        private Runnable myLinkRunnable;
        private final JPanel myWrapper;
        private boolean myVisible;
        
        public CheckLabel(@NotNull final CPPToolchainsCheckPanel cppToolchainsCheckPanel, final String s, final boolean b, @NotNull final boolean b2, @NotNull final GridBag gridBag, final JPanel panel) {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel", "<init>"));
            }
            if (gridBag == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "gbc", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel", "<init>"));
            }
            if (panel == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "panel", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel", "<init>"));
            }
            this(cppToolchainsCheckPanel, s, b, b2, false, gridBag, panel);
        }
        
        public CheckLabel(final String myName, final boolean b, final boolean b2, @NotNull final boolean myPath, @NotNull final GridBag gridBag, final JPanel panel) {
            if (myName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel", "<init>"));
            }
            if (gridBag == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "gbc", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel", "<init>"));
            }
            if (panel == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "panel", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel", "<init>"));
            }
            this.myLabel = new SimpleColoredComponent();
            this.myState = State.Error;
            this.myVisible = true;
            this.myName = myName;
            this.myPath = myPath;
            if (b) {
                this.myLabel.getIpad().left = 20;
            }
            try {
                (this.myWrapper = new JPanel()).add((Component)this.myLabel);
                if (b2) {
                    (this.myLink = LinkLabel.create((String)null, () -> this.myLinkRunnable.run())).setVisible(false);
                    this.myWrapper.add((Component)this.myLink);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0323: {
                try {
                    if (this.myPath) {
                        this.myWrapper.setMinimumSize(new Dimension(100, this.myLabel.getPreferredSize().height));
                        this.myWrapper.setLayout((LayoutManager)new AbstractLayoutManager() {
                            public Dimension preferredLayoutSize(final Container container) {
                                return new Dimension(100, CheckLabel.this.myLabel.getPreferredSize().height);
                            }
                            
                            public void layoutContainer(final Container container) {
                                final Rectangle bounds = container.getBounds();
                                final Insets insets = container.getInsets();
                                CheckLabel.this.myLabel.setBounds(insets.left, insets.top, bounds.width - insets.left - insets.right, bounds.height - insets.top - insets.bottom);
                            }
                        });
                        break Label_0323;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                this.myWrapper.setLayout(new FlowLayout(0, 0, 0));
            }
            panel.add(this.myWrapper, gridBag.nextLine().fillCellHorizontally());
            panel.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(final ComponentEvent componentEvent) {
                    CheckLabel.this.update(true);
                }
            });
        }
        
        public boolean isOkState() {
            try {
                if (this.myState == State.OK) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return false;
        }
        
        public String getTestName() {
            return this.myLabel.toString();
        }
        
        public void disable() {
            this.message(null, null, State.Error);
        }
        
        public void error(@Nullable final String s) {
            this.message(s, null, State.Error);
        }
        
        public void error(@Nullable final String s, @Nullable final String s2) {
            this.message(s, s2, State.Error);
        }
        
        public void ok(@Nullable final String s) {
            this.message(s, null, State.OK);
        }
        
        public void ok(@Nullable final String s, @Nullable final String s2) {
            this.message(s, s2, State.OK);
        }
        
        public void warning(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel", "warning"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.message(s, null, State.Warning);
        }
        
        public void message(@Nullable final String myMessage, @Nullable final String myVersion, final State myState) {
            this.myMessage = myMessage;
            this.myVersion = myVersion;
            this.myState = myState;
            this.myDoUpdate = true;
        }
        
        public void link(@NotNull final String myLinkText, @NotNull final Runnable myLinkRunnable) {
            try {
                if (myLinkText == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel", "link"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (myLinkRunnable == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runnable", "com/jetbrains/cidr/cpp/CPPToolchainsCheckPanel$CheckLabel", "link"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            this.myLinkText = myLinkText;
            this.myLinkRunnable = myLinkRunnable;
        }
        
        public void clear() {
            try {
                if (this.myLink != null) {
                    this.myLink.setVisible(false);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.myLabel.clear();
            this.myLabel.append(this.myName, SimpleTextAttributes.GRAYED_ATTRIBUTES);
            this.myLabel.setIcon((Icon)CPPToolchainsCheckPanel.this.myProgressIcon);
            CPPToolchainsCheckPanel.this.myProgressIcon.addComponent(this.myLabel);
            this.myLinkText = null;
            this.myLinkRunnable = null;
            this.myDoUpdate = false;
        }
        
        public void update() {
            this.update(false);
        }
        
        public void update(final boolean b) {
            Label_0023: {
                try {
                    if (b) {
                        break Label_0023;
                    }
                    final CheckLabel checkLabel = this;
                    final boolean b2 = checkLabel.myDoUpdate;
                    if (!b2) {
                        return;
                    }
                    break Label_0023;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final CheckLabel checkLabel = this;
                    final boolean b2 = checkLabel.myDoUpdate;
                    if (!b2) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    this.myDoUpdate = false;
                    this.myWrapper.setVisible(this.myVisible);
                    if (!this.myVisible) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            Label_0358: {
                Label_0142: {
                    Label_0103: {
                        Label_0072: {
                            try {
                                if (this.myLinkText == null) {
                                    break Label_0103;
                                }
                                final CheckLabel checkLabel2 = this;
                                final LinkLabel linkLabel = checkLabel2.myLink;
                                if (linkLabel != null) {
                                    break Label_0072;
                                }
                                break Label_0103;
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                            try {
                                final CheckLabel checkLabel2 = this;
                                final LinkLabel linkLabel = checkLabel2.myLink;
                                if (linkLabel != null) {
                                    this.myLink.setText(this.myLinkText);
                                    this.myLink.setVisible(true);
                                    this.myLinkText = null;
                                }
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                        }
                        try {
                            this.myLabel.clear();
                            this.myLabel.setIcon(this.a());
                            if (this.myMessage != null) {
                                break Label_0142;
                            }
                            final CheckLabel checkLabel3 = this;
                            final String s = checkLabel3.myVersion;
                            if (s == null) {
                                break Label_0142;
                            }
                            break Label_0142;
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                    }
                    try {
                        final CheckLabel checkLabel3 = this;
                        final String s = checkLabel3.myVersion;
                        if (s == null) {
                            this.myLabel.append(this.myName, SimpleTextAttributes.GRAYED_ATTRIBUTES);
                            break Label_0358;
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                }
                this.myLabel.append(this.myName).append(":");
                if (this.myMessage != null) {
                    this.myLabel.append(" ");
                    String s2 = this.myMessage;
                    if (this.myPath) {
                        s2 = FilePathSplittingPolicy.SPLIT_BY_LETTER.getOptimalTextForComponent(this.myLabel.toString(), new File(s2), (JComponent)this.myLabel, this.myLabel.getWidth() - (this.myLabel.getIpad().left + this.myLabel.getIpad().right + this.myLabel.getInsets().left + this.myLabel.getInsets().right) - this.myLabel.getIconTextGap() - this.myLabel.getIcon().getIconWidth());
                    }
                    this.myLabel.append(s2);
                }
                try {
                    if (this.myVersion != null) {
                        this.myLabel.append(" (").append(this.myVersion).append(")");
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
            }
            CPPToolchainsCheckPanel.this.myProgressIcon.removeComponent(this.myLabel);
        }
        
        private Icon a() {
            try {
                switch (this.myState) {
                    case OK: {
                        return AllIcons.Actions.Checked;
                    }
                    case Error: {
                        break;
                    }
                    case Warning: {
                        return AllIcons.General.BalloonWarning;
                    }
                    default: {
                        return null;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return AllIcons.General.Error;
        }
        
        public void setVisible(final boolean myVisible) {
            this.myVisible = myVisible;
            this.myDoUpdate = true;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class ProgressIcon extends Animator implements Icon
    {
        private static final Icon[] ICONS;
        private final Set<SimpleColoredComponent> myComponents;
        private int myIndex;
        
        public ProgressIcon(final Disposable disposable) {
            super((String)null, ProgressIcon.ICONS.length, 800, true);
            this.myComponents = new LinkedHashSet<SimpleColoredComponent>();
            Disposer.register(disposable, (Disposable)this);
        }
        
        public void addComponent(final SimpleColoredComponent simpleColoredComponent) {
            this.myComponents.add(simpleColoredComponent);
            if (this.myComponents.size() == 1) {
                this.resume();
            }
        }
        
        public void removeComponent(final SimpleColoredComponent simpleColoredComponent) {
            this.myComponents.remove(simpleColoredComponent);
            if (this.myComponents.isEmpty()) {
                this.suspend();
            }
        }
        
        public void paintNow(final int n, final int n2, final int n3) {
            this.myIndex = ((n < 0) ? 0 : ((n >= ProgressIcon.ICONS.length) ? (ProgressIcon.ICONS.length - 1) : n));
            final int iconWidth = this.getIconWidth();
            final int iconHeight = this.getIconHeight();
            for (final SimpleColoredComponent simpleColoredComponent : this.myComponents) {
                simpleColoredComponent.paintImmediately(simpleColoredComponent.getIpad().left, 0, iconWidth, iconHeight);
            }
        }
        
        public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
            ProgressIcon.ICONS[this.myIndex].paintIcon(component, graphics, n, n2);
        }
        
        public int getIconWidth() {
            return AllIcons.Process.Step_passive.getIconWidth();
        }
        
        public int getIconHeight() {
            return AllIcons.Process.Step_passive.getIconHeight();
        }
        
        static {
            ICONS = new Icon[] { AllIcons.Process.Step_1, AllIcons.Process.Step_2, AllIcons.Process.Step_3, AllIcons.Process.Step_4, AllIcons.Process.Step_5, AllIcons.Process.Step_6, AllIcons.Process.Step_7, AllIcons.Process.Step_8, AllIcons.Process.Step_9, AllIcons.Process.Step_10, AllIcons.Process.Step_11, AllIcons.Process.Step_12 };
        }
    }
    
    private static class OutputMessages extends Messages
    {
        public static void showMessageDialog(final String s) {
            new Messages.TwoStepConfirmationDialog(s, "CMake Errors", new String[] { "Close" }, null, false, 0, -1, null, null) {
                protected JComponent createNorthPanel() {
                    return null;
                }
                
                protected JComponent createCenterPanel() {
                    final JComponent doCreateCenterPanel = this.doCreateCenterPanel();
                    final JTextPane textPane = (JTextPane)UIUtil.findComponentOfType(doCreateCenterPanel, (Class)JTextPane.class);
                    if (textPane != null) {
                        textPane.setBackground(UIUtil.getTextFieldBackground());
                        textPane.setBorder(UIUtil.getTextFieldBorder());
                        textPane.setCursor(Cursor.getPredefinedCursor(2));
                    }
                    return doCreateCenterPanel;
                }
            }.show();
        }
    }
}
