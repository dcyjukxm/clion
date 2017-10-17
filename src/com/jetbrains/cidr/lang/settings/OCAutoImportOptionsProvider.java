// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.intellij.openapi.options.ConfigurationException;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import com.intellij.ui.IdeBorderFactory;
import com.jetbrains.cidr.lang.OCLanguage;
import java.awt.BorderLayout;
import com.intellij.util.PlatformUtils;
import java.awt.Component;
import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.openapi.application.ApplicationBundle;
import com.intellij.util.ui.GridBag;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import com.intellij.application.options.editor.AutoImportOptionsProvider;

public class OCAutoImportOptionsProvider implements AutoImportOptionsProvider
{
    private JPanel myHeaderImportStyleComponent;
    private JCheckBox myShowImportPopupBox;
    private JCheckBox myAllowImportInCompletionBox;
    private OCEnumGroupOption<OCCodeStyleSettings.HeaderImportStyle> myHeaderImportStyleOption;
    
    public JComponent createComponent() {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBag setDefaultAnchor = new GridBag().setDefaultAnchor(17);
        this.myHeaderImportStyleOption = new OCEnumGroupOption<OCCodeStyleSettings.HeaderImportStyle>("Auto import", OCEnumGroupOption.Presentation.Section, OCCodeStyleSettings.HeaderImportStyle.values(), new String[] { "Predeclare in .h/Import in .m", "Import in .h" });
        this.myShowImportPopupBox = new JCheckBox(ApplicationBundle.message("checkbox.show.import.popup", new Object[0]));
        this.myAllowImportInCompletionBox = new JCheckBox(OCBundle.message("checkbox.allow.import.in.completion", new Object[0]));
        this.myHeaderImportStyleComponent = this.myHeaderImportStyleOption.createComponent(false);
        panel.add(this.myShowImportPopupBox, setDefaultAnchor.nextLine());
        panel.add(this.myAllowImportInCompletionBox, setDefaultAnchor.nextLine());
        if (PlatformUtils.isAppCode()) {
            panel.add(this.myHeaderImportStyleComponent, setDefaultAnchor.nextLine());
        }
        final JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(panel, "West");
        panel2.setBorder((Border)IdeBorderFactory.createTitledBorder(OCLanguage.getInstance().getDisplayName(), true));
        return panel2;
    }
    
    public boolean isModified() {
        final OCCodeInsightSettings instance = OCCodeInsightSettings.getInstance();
        return a(this.myHeaderImportStyleOption, this.myHeaderImportStyleComponent, instance.HEADER_IMPORT_STYLE) | a(this.myShowImportPopupBox, instance.SHOW_IMPORT_POPUP) | a(this.myAllowImportInCompletionBox, instance.ALLOW_IMPORT_IN_COMPLETION);
    }
    
    private static <T, Comp extends JComponent> boolean a(final OCOption<T, Comp> ocOption, final Comp comp, final T t) {
        return !ocOption.getSelectedValue(comp).equals(t);
    }
    
    private static boolean a(final JToggleButton toggleButton, final boolean b) {
        return toggleButton.isSelected() != b;
    }
    
    public void apply() throws ConfigurationException {
        final OCCodeInsightSettings instance = OCCodeInsightSettings.getInstance();
        instance.HEADER_IMPORT_STYLE = this.myHeaderImportStyleOption.getSelectedValue(this.myHeaderImportStyleComponent);
        instance.SHOW_IMPORT_POPUP = this.myShowImportPopupBox.isSelected();
        instance.ALLOW_IMPORT_IN_COMPLETION = this.myAllowImportInCompletionBox.isSelected();
    }
    
    public void reset() {
        final OCCodeInsightSettings instance = OCCodeInsightSettings.getInstance();
        this.myShowImportPopupBox.setSelected(instance.SHOW_IMPORT_POPUP);
        this.myAllowImportInCompletionBox.setSelected(instance.ALLOW_IMPORT_IN_COMPLETION);
        this.myHeaderImportStyleOption.selectValue(this.myHeaderImportStyleComponent, instance.HEADER_IMPORT_STYLE);
    }
}
