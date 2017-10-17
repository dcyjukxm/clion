// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import com.intellij.ide.util.gotoByName.GotoFileModel;
import com.intellij.ide.util.gotoByName.ChooseByNameModel;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.search.OCSearchUtil;
import java.util.List;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import javax.swing.tree.DefaultMutableTreeNode;
import com.intellij.ide.util.TreeChooser;
import com.intellij.psi.PsiNamedElement;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.ide.util.AbstractTreeClassChooserDialog;

public class OCFileChooserDialog extends AbstractTreeClassChooserDialog<OCFile>
{
    public OCFileChooserDialog(final String s, final Project project) {
        super(s, project, OCSearchScope.getProjectSourcesScope(project), (Class<PsiNamedElement>)OCFile.class, null, null, null, false, false);
    }
    
    @Nullable
    @Override
    protected OCFile getSelectedFromTreeUserObject(final DefaultMutableTreeNode defaultMutableTreeNode) {
        final Object userObject = defaultMutableTreeNode.getUserObject();
        try {
            if (!(userObject instanceof PsiFileNode)) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        final PsiFile psiFile = (PsiFile)((PsiFileNode)userObject).getValue();
        try {
            if (psiFile instanceof OCFile) {
                return (OCFile)psiFile;
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    @NotNull
    @Override
    protected List<OCFile> getClassesByName(final String s, final boolean b, final String s2, final GlobalSearchScope globalSearchScope) {
        List<OCFile> ocFilesByName;
        try {
            ocFilesByName = OCSearchUtil.getOCFilesByName(this.getProject(), s, globalSearchScope);
            if (ocFilesByName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCFileChooserDialog", "getClassesByName"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return ocFilesByName;
    }
    
    @Override
    protected ChooseByNameModel createChooseByNameModel() {
        return (ChooseByNameModel)new GotoFileModel(this.getProject());
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
