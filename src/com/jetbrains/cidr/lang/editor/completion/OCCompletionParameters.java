// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.completion.CompletionParameters;

public class OCCompletionParameters
{
    @NotNull
    private final CompletionParameters myParameters;
    @NotNull
    private final PsiElement myPosition;
    
    public OCCompletionParameters(@NotNull final CompletionParameters myParameters, @NotNull final PsiElement myPosition) {
        if (myParameters == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCCompletionParameters", "<init>"));
        }
        if (myPosition == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "position", "com/jetbrains/cidr/lang/editor/completion/OCCompletionParameters", "<init>"));
        }
        this.myParameters = myParameters;
        this.myPosition = myPosition;
    }
    
    @NotNull
    public CompletionType getCompletionType() {
        CompletionType completionType;
        try {
            completionType = this.a().getCompletionType();
            if (completionType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCCompletionParameters", "getCompletionType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return completionType;
    }
    
    @NotNull
    private CompletionParameters a() {
        CompletionParameters myParameters;
        try {
            myParameters = this.myParameters;
            if (myParameters == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCCompletionParameters", "getParameters"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myParameters;
    }
    
    @NotNull
    public PsiElement getPosition() {
        PsiElement myPosition;
        try {
            myPosition = this.myPosition;
            if (myPosition == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCCompletionParameters", "getPosition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myPosition;
    }
    
    @NotNull
    public PsiFile getOriginalFile() {
        PsiFile originalFile;
        try {
            originalFile = this.a().getOriginalFile();
            if (originalFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCCompletionParameters", "getOriginalFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return originalFile;
    }
    
    public int getOffset() {
        return this.a().getOffset();
    }
    
    public int getInvocationCount() {
        return this.a().getInvocationCount();
    }
    
    @NotNull
    public PsiElement getRealPosition() {
        PsiElement position;
        try {
            position = this.a().getPosition();
            if (position == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCCompletionParameters", "getRealPosition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return position;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
