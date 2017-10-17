// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.actions.newFile;

import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.io.FileUtil;
import java.io.File;
import java.util.Iterator;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.cpp.cmake.psi.util.CMakeFileLocationUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.SmartPointerManager;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.intellij.psi.SmartPsiElementPointer;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.editor.markup.MarkupModel;
import com.intellij.openapi.editor.ScrollingModel;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommand;
import com.intellij.openapi.editor.markup.HighlighterTargetArea;
import com.intellij.openapi.editor.colors.EditorColors;
import com.intellij.openapi.editor.ScrollType;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.codeInsight.hint.EditorFragmentComponent;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.cpp.CPPLog;
import com.intellij.openapi.editor.ex.EditorEx;
import java.awt.Dimension;
import com.intellij.openapi.editor.colors.EditorFontType;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.cpp.cmake.CMakeListsFileType;
import com.intellij.openapi.project.Project;
import com.intellij.ui.EditorTextField;

private static class MyEditorTextField extends EditorTextField
{
    private CMakeNewFileTarget myTarget;
    
    public MyEditorTextField(final Project project) {
        super(null, project, CMakeListsFileType.INSTANCE, true, false);
        this.setFont(EditorColorsManager.getInstance().getGlobalScheme().getFont(EditorFontType.PLAIN));
        this.setPreferredSize(new Dimension(500, 130));
        this.setMinimumSize(new Dimension(500, 130));
        this.setFocusable(false);
        this.setFocusTraversalPolicyProvider(false);
    }
    
    @Override
    protected EditorEx createEditor() {
        final CMakeNewFileTarget myTarget = this.myTarget;
        Logger log = null;
        boolean b = false;
        Label_0021: {
            try {
                log = CPPLog.LOG;
                if (myTarget != null) {
                    b = true;
                    break Label_0021;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            b = false;
        }
        log.assertTrue(b);
        this.a(myTarget);
        return a(myTarget, super.createEditor());
    }
    
    private static EditorEx a(@NotNull final CMakeNewFileTarget cMakeNewFileTarget, @NotNull final EditorEx editorEx) {
        try {
            if (cMakeNewFileTarget == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$MyEditorTextField", "configureNewEditor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (editorEx == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$MyEditorTextField", "configureNewEditor"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        editorEx.setBackgroundColor(EditorFragmentComponent.getBackgroundColor((Editor)editorEx));
        editorEx.setVerticalScrollbarVisible(true);
        editorEx.setHorizontalScrollbarVisible(true);
        final IllegalArgumentException ex3;
        final IllegalArgumentException ex5;
        ApplicationManager.getApplication().invokeLater(() -> {
            try {
                if (editorEx == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$MyEditorTextField", "lambda$configureNewEditor$0"));
                    throw ex3;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            try {
                if (cMakeNewFileTarget == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$MyEditorTextField", "lambda$configureNewEditor$0"));
                    throw ex5;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
            try {
                if (!editorEx.isDisposed()) {
                    a((Editor)editorEx, cMakeNewFileTarget);
                }
            }
            catch (IllegalArgumentException ex7) {
                throw b(ex7);
            }
            return;
        });
        return editorEx;
    }
    
    public void setTarget(@NotNull final CMakeNewFileTarget myTarget) {
        try {
            if (myTarget == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$MyEditorTextField", "setTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.a(this.myTarget = myTarget);
        a(this.getEditor(), myTarget);
    }
    
    private static void a(@Nullable final Editor editor, @NotNull final CMakeNewFileTarget cMakeNewFileTarget) {
        try {
            if (cMakeNewFileTarget == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$MyEditorTextField", "updateWithTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (editor == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final CMakeCommand access$400 = cMakeNewFileTarget.b();
        try {
            if (access$400 == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        editor.getCaretModel().moveToOffset(access$400.getTextOffset());
        final ScrollingModel scrollingModel = editor.getScrollingModel();
        scrollingModel.disableAnimation();
        scrollingModel.scrollToCaret(ScrollType.CENTER);
        scrollingModel.enableAnimation();
        final MarkupModel markupModel = editor.getMarkupModel();
        markupModel.removeAllHighlighters();
        final TextRange textRange = access$400.getTextRange();
        markupModel.addRangeHighlighter(textRange.getStartOffset(), textRange.getEndOffset(), 5500, editor.getColorsScheme().getAttributes(EditorColors.SEARCH_RESULT_ATTRIBUTES), HighlighterTargetArea.EXACT_RANGE);
    }
    
    private void a(@NotNull final CMakeNewFileTarget cMakeNewFileTarget) {
        try {
            if (cMakeNewFileTarget == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$MyEditorTextField", "initDocument"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final PsiFile containingFile = cMakeNewFileTarget.myPointer.getContainingFile();
        Logger log = null;
        boolean b = false;
        Label_0070: {
            try {
                log = CPPLog.LOG;
                if (containingFile != null) {
                    b = true;
                    break Label_0070;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            b = false;
        }
        log.assertTrue(b);
        final Document document = PsiDocumentManager.getInstance(containingFile.getProject()).getDocument(containingFile);
        try {
            if (document != this.getDocument()) {
                this.setDocument(document);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
