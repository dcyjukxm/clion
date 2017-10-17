// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.intellij.ui.SpeedSearchComparator;
import com.intellij.util.containers.Convertor;
import com.intellij.ui.TreeSpeedSearch;
import javax.swing.tree.TreePath;
import com.intellij.ui.treeStructure.Tree;
import javax.swing.JTree;
import com.intellij.util.ui.tree.TreeUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.codeInsight.generation.MemberChooserObject;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.codeInsight.generation.ClassMember;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import javax.swing.JComponent;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.settings.OCOption;
import java.util.List;
import com.intellij.ide.util.MemberChooser;

public class OCMemberChooser extends MemberChooser<OCMemberChooserObject>
{
    private List<OCOption> myOptions;
    
    public OCMemberChooser(final OCMemberChooserObject[] array, final boolean b, final boolean b2, final List<Pair<OCOption, Object>> list, @Nullable final JComponent component, @NotNull final Project project) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/OCMemberChooser", "<init>"));
        }
        super((ClassMember[])array, b, b2, project, component, a(list));
        this.myOptions = (List<OCOption>)ContainerUtil.map((Collection)list, pair -> (OCOption)pair.getFirst());
    }
    
    @Override
    protected boolean isContainerNode(final MemberChooserObject memberChooserObject) {
        final OCSymbol symbol = ((OCMemberChooserObject)memberChooserObject).getSymbol();
        Label_0029: {
            try {
                if (symbol instanceof OCClassSymbol) {
                    break Label_0029;
                }
                final OCSymbol ocSymbol = symbol;
                final boolean b = ocSymbol instanceof OCStructSymbol;
                if (b) {
                    break Label_0029;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCSymbol ocSymbol = symbol;
                final boolean b = ocSymbol instanceof OCStructSymbol;
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Override
    protected boolean supportsNestedContainers() {
        return true;
    }
    
    @Override
    protected void defaultExpandTree() {
        TreeUtil.expand((JTree)this.myTree, 2);
        int n = 0;
        while (true) {
            Label_0050: {
                try {
                    if (n >= this.myTree.getRowCount()) {
                        break;
                    }
                    final OCMemberChooser ocMemberChooser = this;
                    final Tree tree = ocMemberChooser.myTree;
                    final int n2 = n;
                    final TreePath treePath = tree.getPathForRow(n2);
                    final Object o = treePath.getLastPathComponent();
                    final boolean b = o instanceof MemberNode;
                    if (b) {
                        return;
                    }
                    break Label_0050;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final OCMemberChooser ocMemberChooser = this;
                    final Tree tree = ocMemberChooser.myTree;
                    final int n2 = n;
                    final TreePath treePath = tree.getPathForRow(n2);
                    final Object o = treePath.getLastPathComponent();
                    final boolean b = o instanceof MemberNode;
                    if (b) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            ++n;
        }
        super.defaultExpandTree();
    }
    
    @Override
    protected void installSpeedSearch() {
        new TreeSpeedSearch(this.myTree, (Convertor<TreePath, String>)(treePath -> {
            final Object lastPathComponent = treePath.getLastPathComponent();
            ElementNode elementNode = null;
            Label_0024: {
                try {
                    if (lastPathComponent instanceof ElementNode) {
                        elementNode = (ElementNode)lastPathComponent;
                        break Label_0024;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                elementNode = null;
            }
            final ElementNode elementNode2 = elementNode;
            try {
                if (elementNode2 == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            String s = elementNode2.getDelegate().getText();
            if (s != null) {
                final int index = s.indexOf(40);
                if (index >= 0) {
                    s = s.substring(0, index);
                }
            }
            return s;
        }), true).setComparator(new SpeedSearchComparator(false));
    }
    
    private static JComponent[] a(final List<Pair<OCOption, Object>> list) {
        final JComponent[] array = new JComponent[list.size()];
        for (int i = 0; i < array.length; ++i) {
            final OCOption ocOption = (OCOption)list.get(i).getFirst();
            ocOption.selectValue(array[i] = ocOption.createComponent(), list.get(i).getSecond());
        }
        return array;
    }
    
    public Map<OCOption, Object> getOptionSelections() {
        final HashMap<OCOption<Object, JComponent>, Object> hashMap = (HashMap<OCOption<Object, JComponent>, Object>)new HashMap<OCOption, Object>();
        for (int i = 0; i < this.myOptionControls.length; ++i) {
            final OCOption<Object, JComponent> ocOption = this.myOptions.get(i);
            hashMap.put(ocOption, ocOption.getSelectedValue(this.myOptionControls[i]));
        }
        return (Map<OCOption, Object>)hashMap;
    }
    
    public <T, Comp extends JComponent> void setOptionSelection(final OCOption<T, Comp> ocOption, final T t) {
        final int index = this.myOptions.indexOf(ocOption);
        try {
            if (index != -1) {
                ocOption.selectValue((Comp)this.myOptionControls[index], t);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    public <T, Comp extends JComponent> Comp getOptionComponent(final OCOption<T, Comp> ocOption) {
        return (Comp)this.myOptionControls[this.myOptions.indexOf(ocOption)];
    }
    
    @Nullable
    public List<OCMemberChooserObject> getChosenElements() {
        return new ArrayList<OCMemberChooserObject>((Collection<? extends OCMemberChooserObject>)this.mySelectedElements);
    }
    
    @Override
    public void selectElements(final ClassMember[] array) {
        Label_0023: {
            try {
                if (array.length != 0) {
                    break Label_0023;
                }
                final OCMemberChooser ocMemberChooser = this;
                final ClassMember[] array2 = ocMemberChooser.myElements;
                final OCMemberChooserObject[] array3 = (OCMemberChooserObject[])array2;
                final int n = array3.length;
                if (n > 0) {
                    break Label_0023;
                }
                break Label_0023;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCMemberChooser ocMemberChooser = this;
                final ClassMember[] array2 = ocMemberChooser.myElements;
                final OCMemberChooserObject[] array3 = (OCMemberChooserObject[])array2;
                final int n = array3.length;
                if (n > 0) {
                    super.selectElements(new ClassMember[] { ((OCMemberChooserObject[])this.myElements)[0] });
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        super.selectElements(array);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
