// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.PlatformUtils;
import com.intellij.openapi.options.Configurable;
import java.util.Collection;
import com.intellij.xdebugger.settings.DebuggerSettingsCategory;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.openapi.project.Project;
import com.intellij.xdebugger.XDebuggerManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.settings.XDebuggerSettings;

public class CidrDebuggerSettings extends XDebuggerSettings<CidrDebuggerSettings>
{
    public boolean RENDERERS_ENABLED;
    public boolean VALUES_FILTER_ENABLED;
    public boolean COCOA_RENDERERS_ENABLED;
    public boolean CORE_DATA_RENDERERS_ENABLED;
    public boolean STL_RENDERERS_ENABLED;
    
    @NotNull
    public static CidrDebuggerSettings getInstance() {
        CidrDebuggerSettings cidrDebuggerSettings;
        try {
            cidrDebuggerSettings = (CidrDebuggerSettings)getInstance((Class)CidrDebuggerSettings.class);
            if (cidrDebuggerSettings == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings", "getInstance"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return cidrDebuggerSettings;
    }
    
    public CidrDebuggerSettings() {
        super("ObjectiveC");
        this.RENDERERS_ENABLED = true;
        this.VALUES_FILTER_ENABLED = true;
        this.COCOA_RENDERERS_ENABLED = true;
        this.CORE_DATA_RENDERERS_ENABLED = true;
        this.STL_RENDERERS_ENABLED = true;
    }
    
    public static void updateCurrentDebugSession(final AnActionEvent anActionEvent) {
        final Project eventProject = AnAction.getEventProject(anActionEvent);
        try {
            if (eventProject == null) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final XDebugSession currentSession = XDebuggerManager.getInstance(eventProject).getCurrentSession();
        try {
            if (currentSession == null) {
                return;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        currentSession.rebuildViews();
    }
    
    @NotNull
    public Collection<? extends Configurable> createConfigurables(@NotNull final DebuggerSettingsCategory p0) {
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
        //    18: ldc             "category"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "createConfigurables"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: getstatic       com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings$1.$SwitchMap$com$intellij$xdebugger$settings$DebuggerSettingsCategory:[I
        //    47: aload_1        
        //    48: invokevirtual   com/intellij/xdebugger/settings/DebuggerSettingsCategory.ordinal:()I
        //    51: iaload         
        //    52: lookupswitch {
        //                1: 72
        //          default: 129
        //        }
        //    72: new             Lcom/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable;
        //    75: dup            
        //    76: aload_0        
        //    77: invokespecial   com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.<init>:(Lcom/jetbrains/cidr/execution/debugger/CidrDebuggerSettings;)V
        //    80: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //    83: dup            
        //    84: ifnonnull       128
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    93: athrow         
        //    94: new             Ljava/lang/IllegalStateException;
        //    97: dup            
        //    98: ldc             "@NotNull method %s.%s must not return null"
        //   100: ldc             2
        //   102: anewarray       Ljava/lang/Object;
        //   105: dup            
        //   106: ldc             0
        //   108: ldc             "com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings"
        //   110: aastore        
        //   111: dup            
        //   112: ldc             1
        //   114: ldc             "createConfigurables"
        //   116: aastore        
        //   117: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   120: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   123: athrow         
        //   124: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   127: athrow         
        //   128: areturn        
        //   129: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   132: dup            
        //   133: ifnonnull       170
        //   136: new             Ljava/lang/IllegalStateException;
        //   139: dup            
        //   140: ldc             "@NotNull method %s.%s must not return null"
        //   142: ldc             2
        //   144: anewarray       Ljava/lang/Object;
        //   147: dup            
        //   148: ldc             0
        //   150: ldc             "com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings"
        //   152: aastore        
        //   153: dup            
        //   154: ldc             1
        //   156: ldc             "createConfigurables"
        //   158: aastore        
        //   159: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   162: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   165: athrow         
        //   166: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   169: athrow         
        //   170: areturn        
        //    Signature:
        //  (Lcom/intellij/xdebugger/settings/DebuggerSettingsCategory;)Ljava/util/Collection<+Lcom/intellij/openapi/options/Configurable;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     87     90     94     Ljava/lang/IllegalStateException;
        //  72     124    124    128    Ljava/lang/IllegalStateException;
        //  129    166    166    170    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0072:
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
    
    public boolean isTargetedToProduct(@NotNull final Configurable configurable) {
        try {
            if (configurable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurable", "com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings", "isTargetedToProduct"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0071: {
            try {
                if (!PlatformUtils.isCidr()) {
                    return false;
                }
                final String s = "Objective-C";
                final Configurable configurable2 = configurable;
                final String s2 = configurable2.getDisplayName();
                final boolean b = s.equals(s2);
                if (b) {
                    break Label_0071;
                }
                return false;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final String s = "Objective-C";
                final Configurable configurable2 = configurable;
                final String s2 = configurable2.getDisplayName();
                final boolean b = s.equals(s2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public CidrDebuggerSettings getState() {
        return this;
    }
    
    public void loadState(final CidrDebuggerSettings cidrDebuggerSettings) {
        XmlSerializerUtil.copyBean((Object)cidrDebuggerSettings, (Object)this);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
