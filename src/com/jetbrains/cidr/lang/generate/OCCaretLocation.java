// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import com.intellij.openapi.project.Project;
import com.intellij.util.ObjectUtils;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;

public class OCCaretLocation
{
    @NotNull
    private final PsiFile myFile;
    @Nullable
    private final PsiElement myElement;
    private final int myOffsetInElement;
    
    @NotNull
    public static OCCaretLocation byEditor(@NotNull final PsiFile psiFile, @Nullable final Editor editor) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/OCCaretLocation", "byEditor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        PsiElement element = null;
        Label_0061: {
            try {
                if (editor == null) {
                    element = null;
                    break Label_0061;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            element = OCGenerateUtil.getElementAt(editor, psiFile);
        }
        final PsiElement psiElement = element;
        int max = 0;
        Label_0099: {
            try {
                if (editor == null) {
                    max = -1;
                    break Label_0099;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            max = Math.max(0, editor.getCaretModel().getOffset() - psiElement.getTextRange().getStartOffset());
        }
        final int n = max;
        OCCaretLocation ocCaretLocation;
        try {
            ocCaretLocation = new OCCaretLocation(psiFile, psiElement, n);
            if (ocCaretLocation == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCCaretLocation", "byEditor"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return ocCaretLocation;
    }
    
    @NotNull
    public static OCCaretLocation byOffset(@NotNull final PsiFile psiFile, final int n) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/OCCaretLocation", "byOffset"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement psiElement = (PsiElement)ObjectUtils.notNull((Object)psiFile.findElementAt(n), (Object)psiFile);
        OCCaretLocation ocCaretLocation;
        try {
            ocCaretLocation = new OCCaretLocation(psiFile, psiElement, Math.min(psiElement.getTextRange().getEndOffset(), Math.max(0, n - psiElement.getTextRange().getStartOffset())));
            if (ocCaretLocation == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCCaretLocation", "byOffset"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ocCaretLocation;
    }
    
    public static OCCaretLocation byFile(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/OCCaretLocation", "byFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new OCCaretLocation(psiFile, null, -1);
    }
    
    private OCCaretLocation(@NotNull final PsiFile myFile, @Nullable final PsiElement myElement, final int myOffsetInElement) {
        if (myFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/OCCaretLocation", "<init>"));
        }
        this.myFile = myFile;
        this.myElement = myElement;
        this.myOffsetInElement = myOffsetInElement;
    }
    
    @Nullable
    public PsiElement getElement() {
        return this.myElement;
    }
    
    @Nullable
    public Integer getOffsetInFile() {
        try {
            if (this.myElement == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myElement.getTextRange().getStartOffset() + this.myOffsetInElement;
    }
    
    @NotNull
    public PsiFile getFile() {
        PsiFile myFile;
        try {
            myFile = this.myFile;
            if (myFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCCaretLocation", "getFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myFile;
    }
    
    @NotNull
    public Project getProject() {
        Project project;
        try {
            project = this.myFile.getProject();
            if (project == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCCaretLocation", "getProject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return project;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
