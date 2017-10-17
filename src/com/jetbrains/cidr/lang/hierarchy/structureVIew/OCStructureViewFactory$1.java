// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.structureVIew;

import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.ide.structureView.StructureView;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.fileEditor.FileEditor;
import org.jetbrains.annotations.NotNull;
import com.intellij.ide.structureView.StructureViewModel;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;

class OCStructureViewFactory$1 extends TreeBasedStructureViewBuilder {
    final /* synthetic */ PsiFile val$psiFile;
    
    @NotNull
    public StructureViewModel createStructureViewModel(@Nullable final Editor editor) {
        OCStructureViewModel ocStructureViewModel;
        try {
            ocStructureViewModel = new OCStructureViewModel(this.val$psiFile, editor);
            if (ocStructureViewModel == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewFactory$1", "createStructureViewModel"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (StructureViewModel)ocStructureViewModel;
    }
    
    public boolean isRootNodeShown() {
        return false;
    }
    
    @NotNull
    public StructureView createStructureView(final FileEditor fileEditor, @NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewFactory$1", "createStructureView"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Editor editor = null;
        Label_0069: {
            try {
                if (fileEditor instanceof TextEditor) {
                    editor = ((TextEditor)fileEditor).getEditor();
                    break Label_0069;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            editor = null;
        }
        final StructureViewModel structureViewModel = this.createStructureViewModel(editor);
        final OCStructureViewComponent ocStructureViewComponent = new OCStructureViewComponent(fileEditor, structureViewModel, project, this.isRootNodeShown());
        OCStructureViewComponent ocStructureViewComponent2;
        try {
            Disposer.register((Disposable)ocStructureViewComponent, (Disposable)new Disposable() {
                public void dispose() {
                    structureViewModel.dispose();
                }
            });
            ocStructureViewComponent2 = ocStructureViewComponent;
            if (ocStructureViewComponent2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewFactory$1", "createStructureView"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return (StructureView)ocStructureViewComponent2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}