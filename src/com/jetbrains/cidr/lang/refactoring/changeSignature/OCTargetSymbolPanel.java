// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import javax.swing.JComboBox;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.util.io.FileUtilRt;
import com.jetbrains.cidr.lang.psi.impl.OCFileImpl;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.actions.newFile.OCNewCategoryAction;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.Iterator;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.openapi.util.Trinity;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import javax.swing.JList;
import com.intellij.ui.ListCellRendererWrapper;
import com.intellij.openapi.util.Key;
import com.intellij.refactoring.ui.ComboBoxVisibilityPanel;

public class OCTargetSymbolPanel extends ComboBoxVisibilityPanel<Object>
{
    public static final Key<TargetSymbolsMode> VISIBILITY_KEY;
    private static final String SEPARATOR = "separator";
    
    public OCTargetSymbolPanel() {
        super("Targets:", new Object[0]);
    }
    
    @Override
    protected ListCellRendererWrapper getRenderer() {
        return new ListCellRendererWrapper<Object>() {
            public void customize(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
                if (o instanceof OCSymbol) {
                    this.setText(((OCSymbol)o).getPresentableName());
                    this.setIcon(((OCSymbol)o).getIcon());
                }
                else if (o instanceof Trinity) {
                    final Trinity trinity = (Trinity)o;
                    this.setText(((OCClassSymbol)trinity.getFirst()).getPresentableName() + (String)trinity.getThird());
                    this.setIcon(((OCClassSymbol)trinity.getFirst()).getIcon());
                }
                else if ("separator".equals(o)) {
                    this.setSeparator();
                }
                else if (o != null) {
                    this.setText(o.toString());
                }
            }
        };
    }
    
    public void fillData(final OCGeneratedInfo ocGeneratedInfo) {
        final OCClassSymbol ocClassSymbol = (OCClassSymbol)ocGeneratedInfo.getMethodParent();
        final OCInterfaceSymbol interface1 = ocClassSymbol.getInterface();
        if (OCSearchScope.isInProjectSources(interface1)) {
            ((ComboBoxVisibilityPanel<Trinity>)this).addOption(new Trinity((Object)ocClassSymbol, (Object)TargetSymbolsMode.INTERFACE, (Object)("  [" + a(interface1, "h") + ", " + a(ocClassSymbol.getImplementation(), "m") + "]")));
        }
        if (ocClassSymbol instanceof OCImplementationSymbol && OCSearchScope.isInProjectSources(ocClassSymbol)) {
            final String a = a(ocClassSymbol, "m");
            ((ComboBoxVisibilityPanel<Trinity>)this).addOption(new Trinity((Object)ocClassSymbol, (Object)TargetSymbolsMode.IMPLEMENTATION, (Object)("  [" + a + "]")));
            ((ComboBoxVisibilityPanel<Trinity>)this).addOption(new Trinity((Object)ocClassSymbol, (Object)TargetSymbolsMode.PRIVATE_CATEGORY, (Object)(" + ()  [" + a + "]")));
        }
        if (this.myComboBox.getItemCount() > 0 && (ocGeneratedInfo.getAuxParents().size() > 0 || ocGeneratedInfo.isAllowChangeCategories())) {
            ((ComboBoxVisibilityPanel<String>)this).addOption("separator");
        }
        for (final OCSymbol ocSymbol : ocGeneratedInfo.getAuxParents()) {
            if (OCSearchScope.isInProjectSources(ocSymbol)) {
                ((ComboBoxVisibilityPanel<OCSymbol>)this).addOption(ocSymbol);
            }
        }
        if (ocGeneratedInfo.isAllowChangeCategories()) {
            final OCType resolve = ocClassSymbol.getType().resolve((PsiFile)ocClassSymbol.getContainingOCFile(), true);
            if (resolve instanceof OCObjectType) {
                for (final OCInterfaceSymbol ocInterfaceSymbol : ((OCObjectType)resolve).getCategoryInterfaces()) {
                    if (OCSearchScope.isInProjectSources(ocInterfaceSymbol) && !"".equals(ocInterfaceSymbol.getCategoryName())) {
                        ((ComboBoxVisibilityPanel<OCInterfaceSymbol>)this).addOption(ocInterfaceSymbol);
                    }
                }
            }
            if (this.myComboBox.getItemCount() > 0 && !"separator".equals(this.myComboBox.getItemAt(this.myComboBox.getItemCount() - 1))) {
                ((ComboBoxVisibilityPanel<String>)this).addOption("separator");
            }
            ((ComboBoxVisibilityPanel<String>)this).addOption("New category of " + ocClassSymbol.getName() + "...");
        }
        this.myComboBox.setSelectedIndex(this.a(ocClassSymbol));
        this.addListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent changeEvent) {
                final Object selectedItem = OCTargetSymbolPanel.this.myComboBox.getSelectedItem();
                if (selectedItem instanceof Trinity) {
                    final TargetSymbolsMode targetSymbolsMode = (TargetSymbolsMode)((Trinity)selectedItem).getSecond();
                    ocClassSymbol.getProject().putUserData((Key)OCTargetSymbolPanel.VISIBILITY_KEY, (Object)targetSymbolsMode);
                    ocGeneratedInfo.setTargetSymbolsMode(targetSymbolsMode);
                }
                else if (selectedItem.toString().startsWith("New category")) {
                    OCTargetSymbolPanel.this.createNewCategory(ocGeneratedInfo);
                }
                else if (selectedItem instanceof OCClassSymbol) {
                    ocGeneratedInfo.setMethodParent((OCSymbol)selectedItem);
                    ocGeneratedInfo.setTargetSymbolsMode(TargetSymbolsMode.INTERFACE);
                }
            }
        });
        final Object selectedItem = this.myComboBox.getSelectedItem();
        if (selectedItem instanceof Trinity) {
            ocGeneratedInfo.setTargetSymbolsMode((TargetSymbolsMode)((Trinity)selectedItem).getSecond());
        }
        else if (selectedItem instanceof OCClassSymbol) {
            ocGeneratedInfo.setMethodParent((OCSymbol)selectedItem);
            ocGeneratedInfo.setTargetSymbolsMode(TargetSymbolsMode.INTERFACE);
        }
    }
    
    private int a(final OCClassSymbol ocClassSymbol) {
        int n = 0;
        for (int i = 0; i < this.myComboBox.getItemCount(); ++i) {
            final Object item = this.myComboBox.getItemAt(i);
            if (item instanceof Trinity && ((Trinity)item).getSecond() == TargetSymbolsMode.IMPLEMENTATION) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    public boolean isOnlyNewCategoryAllowed() {
        return this.myComboBox.getItemCount() == 1 && this.myComboBox.getItemAt(0).toString().startsWith("New category");
    }
    
    public boolean createNewCategory(final OCGeneratedInfo ocGeneratedInfo) {
        final OCClassSymbol ocClassSymbol = (OCClassSymbol)ocGeneratedInfo.getMethodParent();
        OCFile containingOCFile;
        if (OCSearchScope.isInProjectSources(ocClassSymbol)) {
            containingOCFile = ocClassSymbol.getContainingOCFile();
        }
        else {
            containingOCFile = (OCFile)ocGeneratedInfo.getMethodReference().getContainingFile();
        }
        if (containingOCFile == null) {
            return false;
        }
        final PsiFile[] performAction = new OCNewCategoryAction() {
            @Override
            protected OCClassSymbol getBaseClass() {
                return ocClassSymbol;
            }
        }.performAction(ocClassSymbol.getProject(), containingOCFile.getParent(), (PsiFile)containingOCFile, null);
        if (!this.isOnlyNewCategoryAllowed()) {
            this.myComboBox.setSelectedIndex(this.a(ocClassSymbol));
        }
        if (performAction != null && performAction.length > 0 && performAction[0] instanceof OCFile) {
            final OCClassSymbol classInFile = OCCodeInsightUtil.getClassInFile((OCFile)performAction[0]);
            if (classInFile != null) {
                ((ComboBoxVisibilityPanel<OCClassSymbol>)this).addOption((this.myComboBox.getItemCount() == 1) ? 0 : (this.myComboBox.getItemCount() - 2), classInFile, classInFile.getPresentableName(), true);
                ocGeneratedInfo.setMethodParent(classInFile);
                ocGeneratedInfo.setTargetSymbolsMode(TargetSymbolsMode.INTERFACE);
                return true;
            }
        }
        return false;
    }
    
    private static String a(@Nullable final OCClassSymbol ocClassSymbol, final String s) {
        final VirtualFile virtualFile = (ocClassSymbol != null) ? ocClassSymbol.getContainingFile() : null;
        if (virtualFile == null || (s.equals("h") && !OCFileImpl.isHeaderFile(virtualFile.getName())) || (s.equals("m") && !OCFileImpl.isSourceCodeFile(virtualFile.getName()))) {
            return "." + s;
        }
        return "." + FileUtilRt.getExtension(virtualFile.getName());
    }
    
    static {
        VISIBILITY_KEY = Key.create("VISIBILITY_KEY");
    }
    
    public enum TargetSymbolsMode
    {
        INTERFACE, 
        PRIVATE_CATEGORY, 
        IMPLEMENTATION;
    }
}
