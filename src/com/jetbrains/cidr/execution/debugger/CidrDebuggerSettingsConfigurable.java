// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.ui.TitledSeparator;
import javax.swing.JLabel;
import com.intellij.util.ui.UIUtil$ComponentStyle;
import com.intellij.ui.components.JBCheckBox;
import javax.swing.AbstractButton;
import java.util.ResourceBundle;
import java.awt.Component;
import java.awt.Dimension;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.Spacer;
import java.awt.LayoutManager;
import com.intellij.uiDesigner.core.GridLayoutManager;
import java.awt.Insets;
import com.intellij.util.PlatformUtils;
import com.intellij.openapi.options.ConfigurationException;
import javax.swing.JComponent;
import org.jetbrains.annotations.Nls;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import com.intellij.ui.components.JBLabel;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.SearchableConfigurable;

public class CidrDebuggerSettingsConfigurable implements SearchableConfigurable, Configurable.NoScroll
{
    private static final boolean PRESENT_AS_OBJC;
    @NotNull
    private final CidrDebuggerSettings mySetting;
    private JPanel myPanel;
    private JCheckBox myEnableRenderersCheckBox;
    private JCheckBox myEnableCocoaRenderersCheckBox;
    private JCheckBox myEnableCoreDataRenderersCheckBox;
    private JCheckBox myEnableGNUSTLRenderersCheckBox;
    private JBLabel myEnableGNUSTLRenderersDescriptionLabel;
    private JCheckBox myHideValuesOutOfScopeCheckBox;
    
    public CidrDebuggerSettingsConfigurable(@NotNull final CidrDebuggerSettings mySetting) {
        if (mySetting == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "setting", "com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable", "<init>"));
        }
        this.mySetting = mySetting;
        this.a();
        final ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                CidrDebuggerSettingsConfigurable.this.b();
            }
        };
        try {
            this.myEnableRenderersCheckBox.addItemListener(itemListener);
            this.myEnableCocoaRenderersCheckBox.addItemListener(itemListener);
            if (CidrDebuggerSettingsConfigurable.PRESENT_AS_OBJC) {
                this.myEnableGNUSTLRenderersCheckBox.setVisible(false);
                this.myEnableGNUSTLRenderersDescriptionLabel.setVisible(false);
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myEnableRenderersCheckBox.setVisible(false);
        this.myEnableCocoaRenderersCheckBox.setVisible(false);
        this.myEnableCoreDataRenderersCheckBox.setVisible(false);
    }
    
    private void b() {
        final boolean selected = this.myEnableRenderersCheckBox.isSelected();
        boolean b2 = false;
        Label_0038: {
            Label_0029: {
                try {
                    if (!selected) {
                        break Label_0029;
                    }
                    final CidrDebuggerSettingsConfigurable cidrDebuggerSettingsConfigurable = this;
                    final JCheckBox checkBox = cidrDebuggerSettingsConfigurable.myEnableCocoaRenderersCheckBox;
                    final boolean b = checkBox.isSelected();
                    if (b) {
                        break Label_0029;
                    }
                    break Label_0029;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final CidrDebuggerSettingsConfigurable cidrDebuggerSettingsConfigurable = this;
                    final JCheckBox checkBox = cidrDebuggerSettingsConfigurable.myEnableCocoaRenderersCheckBox;
                    final boolean b = checkBox.isSelected();
                    if (b) {
                        b2 = true;
                        break Label_0038;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            b2 = false;
        }
        final boolean enabled = b2;
        this.myEnableCocoaRenderersCheckBox.setEnabled(selected);
        this.myEnableCoreDataRenderersCheckBox.setEnabled(enabled);
    }
    
    @NotNull
    public String getId() {
        String s;
        try {
            s = "Debugger.ObjectiveC";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable", "getId"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Nls
    public String getDisplayName() {
        try {
            if (CidrDebuggerSettingsConfigurable.PRESENT_AS_OBJC) {
                return "Objective-C";
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return "C/C++";
    }
    
    public String getHelpTopic() {
        try {
            if (CidrDebuggerSettingsConfigurable.PRESENT_AS_OBJC) {
                return "reference.idesettings.debugger.objectivec";
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return "reference.idesettings.debugger.cpp";
    }
    
    public JComponent createComponent() {
        return this.myPanel;
    }
    
    public boolean isModified() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.mySetting:Lcom/jetbrains/cidr/execution/debugger/CidrDebuggerSettings;
        //     4: getfield        com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings.RENDERERS_ENABLED:Z
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.myEnableRenderersCheckBox:Ljavax/swing/JCheckBox;
        //    11: invokevirtual   javax/swing/JCheckBox.isSelected:()Z
        //    14: if_icmpne       113
        //    17: aload_0        
        //    18: getfield        com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.mySetting:Lcom/jetbrains/cidr/execution/debugger/CidrDebuggerSettings;
        //    21: getfield        com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings.COCOA_RENDERERS_ENABLED:Z
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.myEnableCocoaRenderersCheckBox:Ljavax/swing/JCheckBox;
        //    28: invokevirtual   javax/swing/JCheckBox.isSelected:()Z
        //    31: if_icmpne       113
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: aload_0        
        //    42: getfield        com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.mySetting:Lcom/jetbrains/cidr/execution/debugger/CidrDebuggerSettings;
        //    45: getfield        com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings.CORE_DATA_RENDERERS_ENABLED:Z
        //    48: aload_0        
        //    49: getfield        com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.myEnableCoreDataRenderersCheckBox:Ljavax/swing/JCheckBox;
        //    52: invokevirtual   javax/swing/JCheckBox.isSelected:()Z
        //    55: if_icmpne       113
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_0        
        //    66: getfield        com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.mySetting:Lcom/jetbrains/cidr/execution/debugger/CidrDebuggerSettings;
        //    69: getfield        com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings.STL_RENDERERS_ENABLED:Z
        //    72: aload_0        
        //    73: getfield        com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.myEnableGNUSTLRenderersCheckBox:Ljavax/swing/JCheckBox;
        //    76: invokevirtual   javax/swing/JCheckBox.isSelected:()Z
        //    79: if_icmpne       113
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: aload_0        
        //    90: getfield        com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.mySetting:Lcom/jetbrains/cidr/execution/debugger/CidrDebuggerSettings;
        //    93: getfield        com/jetbrains/cidr/execution/debugger/CidrDebuggerSettings.VALUES_FILTER_ENABLED:Z
        //    96: aload_0        
        //    97: getfield        com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.myHideValuesOutOfScopeCheckBox:Ljavax/swing/JCheckBox;
        //   100: invokevirtual   javax/swing/JCheckBox.isSelected:()Z
        //   103: if_icmpeq       121
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: iconst_1       
        //   114: goto            122
        //   117: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   120: athrow         
        //   121: iconst_0       
        //   122: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      34     37     41     Ljava/lang/IllegalArgumentException;
        //  17     58     61     65     Ljava/lang/IllegalArgumentException;
        //  41     82     85     89     Ljava/lang/IllegalArgumentException;
        //  65     106    109    113    Ljava/lang/IllegalArgumentException;
        //  89     117    117    121    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0041:
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
    
    public void apply() throws ConfigurationException {
        this.mySetting.RENDERERS_ENABLED = this.myEnableRenderersCheckBox.isSelected();
        this.mySetting.COCOA_RENDERERS_ENABLED = this.myEnableCocoaRenderersCheckBox.isSelected();
        this.mySetting.CORE_DATA_RENDERERS_ENABLED = this.myEnableCoreDataRenderersCheckBox.isSelected();
        this.mySetting.STL_RENDERERS_ENABLED = this.myEnableGNUSTLRenderersCheckBox.isSelected();
        this.mySetting.VALUES_FILTER_ENABLED = this.myHideValuesOutOfScopeCheckBox.isSelected();
    }
    
    public void reset() {
        this.myEnableRenderersCheckBox.setSelected(this.mySetting.RENDERERS_ENABLED);
        this.myEnableCocoaRenderersCheckBox.setSelected(this.mySetting.COCOA_RENDERERS_ENABLED);
        this.myEnableCoreDataRenderersCheckBox.setSelected(this.mySetting.CORE_DATA_RENDERERS_ENABLED);
        this.myEnableGNUSTLRenderersCheckBox.setSelected(this.mySetting.STL_RENDERERS_ENABLED);
        this.myHideValuesOutOfScopeCheckBox.setSelected(this.mySetting.VALUES_FILTER_ENABLED);
        this.b();
    }
    
    static {
        boolean present_AS_OBJC = false;
        Label_0028: {
            Label_0019: {
                try {
                    if (PlatformUtils.isAppCode()) {
                        break Label_0019;
                    }
                    final boolean b = PlatformUtils.isRubyMine();
                    if (b) {
                        break Label_0019;
                    }
                    break Label_0019;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final boolean b = PlatformUtils.isRubyMine();
                    if (b) {
                        present_AS_OBJC = true;
                        break Label_0028;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            present_AS_OBJC = false;
        }
        PRESENT_AS_OBJC = present_AS_OBJC;
    }
    
    private /* synthetic */ void a() {
        final JPanel myPanel = new JPanel();
        (this.myPanel = myPanel).setLayout((LayoutManager)new GridLayoutManager(7, 1, new Insets(0, 0, 0, 0), -1, -1, false, false));
        myPanel.add((Component)new Spacer(), new GridConstraints(6, 0, 1, 1, 0, 2, 1, 6, (Dimension)null, (Dimension)null, (Dimension)null));
        final JCheckBox myHideValuesOutOfScopeCheckBox = new JCheckBox();
        this.a(this.myHideValuesOutOfScopeCheckBox = myHideValuesOutOfScopeCheckBox, ResourceBundle.getBundle("CidrDebuggerBundle").getString("debug.settings.enableValuesFilter.checkbox"));
        myPanel.add(myHideValuesOutOfScopeCheckBox, new GridConstraints(4, 0, 1, 1, 8, 0, 3, 0, (Dimension)null, (Dimension)null, (Dimension)null));
        final JPanel panel = new JPanel();
        panel.setLayout((LayoutManager)new GridLayoutManager(6, 1, new Insets(0, 0, 8, 0), -1, -1, false, false));
        myPanel.add(panel, new GridConstraints(0, 0, 4, 1, 0, 3, 3, 3, (Dimension)null, (Dimension)null, (Dimension)null));
        final JCheckBox myEnableRenderersCheckBox = new JCheckBox();
        this.a(this.myEnableRenderersCheckBox = myEnableRenderersCheckBox, ResourceBundle.getBundle("CidrDebuggerBundle").getString("debug.settings.enableValueRenderers.checkbox"));
        panel.add(myEnableRenderersCheckBox, new GridConstraints(1, 0, 1, 1, 8, 0, 3, 0, (Dimension)null, (Dimension)null, (Dimension)null));
        final JCheckBox myEnableCocoaRenderersCheckBox = new JCheckBox();
        this.a(this.myEnableCocoaRenderersCheckBox = myEnableCocoaRenderersCheckBox, ResourceBundle.getBundle("CidrDebuggerBundle").getString("debug.settings.enableCocoaRenderers.checkbox"));
        panel.add(myEnableCocoaRenderersCheckBox, new GridConstraints(2, 0, 1, 1, 9, 0, 0, 0, (Dimension)null, (Dimension)null, (Dimension)null, 2));
        final JCheckBox myEnableGNUSTLRenderersCheckBox = new JCheckBox();
        this.a(this.myEnableGNUSTLRenderersCheckBox = myEnableGNUSTLRenderersCheckBox, ResourceBundle.getBundle("CidrDebuggerBundle").getString("debug.settings.enableGNUSTLRenderers.checkbox"));
        panel.add(myEnableGNUSTLRenderersCheckBox, new GridConstraints(4, 0, 1, 1, 8, 0, 3, 0, (Dimension)null, (Dimension)null, (Dimension)null));
        final JBCheckBox myEnableCoreDataRenderersCheckBox = new JBCheckBox();
        (this.myEnableCoreDataRenderersCheckBox = (JCheckBox)myEnableCoreDataRenderersCheckBox).setText("Core Data renderers");
        panel.add((Component)myEnableCoreDataRenderersCheckBox, new GridConstraints(3, 0, 1, 1, 8, 0, 0, 0, (Dimension)null, (Dimension)null, (Dimension)null, 4));
        final JBLabel myEnableGNUSTLRenderersDescriptionLabel = new JBLabel();
        (this.myEnableGNUSTLRenderersDescriptionLabel = myEnableGNUSTLRenderersDescriptionLabel).setComponentStyle(UIUtil$ComponentStyle.SMALL);
        this.a((JLabel)myEnableGNUSTLRenderersDescriptionLabel, ResourceBundle.getBundle("CidrDebuggerBundle").getString("debug.settings.enableGNUSTLRenderers.checkbox.description"));
        panel.add((Component)myEnableGNUSTLRenderersDescriptionLabel, new GridConstraints(5, 0, 1, 1, 8, 0, 0, 0, (Dimension)null, (Dimension)null, (Dimension)null, 3));
        final TitledSeparator titledSeparator = new TitledSeparator();
        titledSeparator.setText("Variables");
        panel.add((Component)titledSeparator, new GridConstraints(0, 0, 1, 1, 0, 1, 7, 0, (Dimension)null, (Dimension)null, (Dimension)null));
        final JBLabel jbLabel = new JBLabel();
        jbLabel.setComponentStyle(UIUtil$ComponentStyle.SMALL);
        this.a((JLabel)jbLabel, ResourceBundle.getBundle("CidrDebuggerBundle").getString("debug.settings.enableValuesFilter.checkbox.hint"));
        myPanel.add((Component)jbLabel, new GridConstraints(5, 0, 1, 1, 8, 0, 0, 0, (Dimension)null, (Dimension)null, (Dimension)null, 3));
    }
    
    private /* synthetic */ void a(final JLabel p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/lang/StringBuffer;
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuffer.<init>:()V
        //     7: astore_3       
        //     8: iconst_0       
        //     9: istore          4
        //    11: iconst_0       
        //    12: istore          5
        //    14: iconst_m1      
        //    15: istore          6
        //    17: iconst_0       
        //    18: istore          7
        //    20: iload           7
        //    22: aload_2        
        //    23: invokevirtual   java/lang/String.length:()I
        //    26: if_icmpge       130
        //    29: aload_2        
        //    30: iload           7
        //    32: invokevirtual   java/lang/String.charAt:(I)C
        //    35: bipush          38
        //    37: if_icmpne       113
        //    40: goto            47
        //    43: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    46: athrow         
        //    47: iinc            7, 1
        //    50: iload           7
        //    52: aload_2        
        //    53: invokevirtual   java/lang/String.length:()I
        //    56: if_icmpne       73
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    65: athrow         
        //    66: goto            130
        //    69: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: iload           4
        //    75: ifne            113
        //    78: aload_2        
        //    79: iload           7
        //    81: invokevirtual   java/lang/String.charAt:(I)C
        //    84: bipush          38
        //    86: if_icmpeq       113
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: iconst_1       
        //    97: istore          4
        //    99: aload_2        
        //   100: iload           7
        //   102: invokevirtual   java/lang/String.charAt:(I)C
        //   105: istore          5
        //   107: aload_3        
        //   108: invokevirtual   java/lang/StringBuffer.length:()I
        //   111: istore          6
        //   113: aload_3        
        //   114: aload_2        
        //   115: iload           7
        //   117: invokevirtual   java/lang/String.charAt:(I)C
        //   120: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   123: pop            
        //   124: iinc            7, 1
        //   127: goto            20
        //   130: aload_1        
        //   131: aload_3        
        //   132: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   135: invokevirtual   javax/swing/JLabel.setText:(Ljava/lang/String;)V
        //   138: iload           4
        //   140: ifeq            162
        //   143: aload_1        
        //   144: iload           5
        //   146: invokevirtual   javax/swing/JLabel.setDisplayedMnemonic:(C)V
        //   149: aload_1        
        //   150: iload           6
        //   152: invokevirtual   javax/swing/JLabel.setDisplayedMnemonicIndex:(I)V
        //   155: goto            162
        //   158: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   161: athrow         
        //   162: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  20     40     43     47     Ljava/lang/IllegalArgumentException;
        //  29     59     62     66     Ljava/lang/IllegalArgumentException;
        //  47     69     69     73     Ljava/lang/IllegalArgumentException;
        //  73     89     92     96     Ljava/lang/IllegalArgumentException;
        //  130    155    158    162    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0047:
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
    
    private /* synthetic */ void a(final AbstractButton p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/lang/StringBuffer;
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuffer.<init>:()V
        //     7: astore_3       
        //     8: iconst_0       
        //     9: istore          4
        //    11: iconst_0       
        //    12: istore          5
        //    14: iconst_m1      
        //    15: istore          6
        //    17: iconst_0       
        //    18: istore          7
        //    20: iload           7
        //    22: aload_2        
        //    23: invokevirtual   java/lang/String.length:()I
        //    26: if_icmpge       130
        //    29: aload_2        
        //    30: iload           7
        //    32: invokevirtual   java/lang/String.charAt:(I)C
        //    35: bipush          38
        //    37: if_icmpne       113
        //    40: goto            47
        //    43: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    46: athrow         
        //    47: iinc            7, 1
        //    50: iload           7
        //    52: aload_2        
        //    53: invokevirtual   java/lang/String.length:()I
        //    56: if_icmpne       73
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    65: athrow         
        //    66: goto            130
        //    69: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: iload           4
        //    75: ifne            113
        //    78: aload_2        
        //    79: iload           7
        //    81: invokevirtual   java/lang/String.charAt:(I)C
        //    84: bipush          38
        //    86: if_icmpeq       113
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: iconst_1       
        //    97: istore          4
        //    99: aload_2        
        //   100: iload           7
        //   102: invokevirtual   java/lang/String.charAt:(I)C
        //   105: istore          5
        //   107: aload_3        
        //   108: invokevirtual   java/lang/StringBuffer.length:()I
        //   111: istore          6
        //   113: aload_3        
        //   114: aload_2        
        //   115: iload           7
        //   117: invokevirtual   java/lang/String.charAt:(I)C
        //   120: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   123: pop            
        //   124: iinc            7, 1
        //   127: goto            20
        //   130: aload_1        
        //   131: aload_3        
        //   132: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   135: invokevirtual   javax/swing/AbstractButton.setText:(Ljava/lang/String;)V
        //   138: iload           4
        //   140: ifeq            162
        //   143: aload_1        
        //   144: iload           5
        //   146: invokevirtual   javax/swing/AbstractButton.setMnemonic:(C)V
        //   149: aload_1        
        //   150: iload           6
        //   152: invokevirtual   javax/swing/AbstractButton.setDisplayedMnemonicIndex:(I)V
        //   155: goto            162
        //   158: invokestatic    com/jetbrains/cidr/execution/debugger/CidrDebuggerSettingsConfigurable.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   161: athrow         
        //   162: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  20     40     43     47     Ljava/lang/IllegalArgumentException;
        //  29     59     62     66     Ljava/lang/IllegalArgumentException;
        //  47     69     69     73     Ljava/lang/IllegalArgumentException;
        //  73     89     92     96     Ljava/lang/IllegalArgumentException;
        //  130    155    158    162    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0047:
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
