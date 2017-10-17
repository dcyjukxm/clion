// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.icons.AllIcons;
import javax.swing.JComponent;
import java.io.File;
import com.intellij.util.ui.FilePathSplittingPolicy;
import javax.swing.Icon;
import com.intellij.ui.SimpleTextAttributes;
import org.jetbrains.annotations.Nullable;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Container;
import com.intellij.util.ui.AbstractLayoutManager;
import java.awt.Dimension;
import java.awt.Component;
import com.intellij.util.ui.GridBag;
import org.jetbrains.annotations.NotNull;
import javax.swing.JPanel;
import com.intellij.ui.components.labels.LinkLabel;
import com.intellij.ui.SimpleColoredComponent;

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
        this.myLabel.setIcon((Icon)CPPToolchainsCheckPanel.access$100(CPPToolchainsCheckPanel.this));
        CPPToolchainsCheckPanel.access$100(CPPToolchainsCheckPanel.this).addComponent(this.myLabel);
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
        CPPToolchainsCheckPanel.access$100(CPPToolchainsCheckPanel.this).removeComponent(this.myLabel);
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
