// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.workspace.OCLanguageKindCalculator;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.editor.colors.OCFileHighlighter;
import com.intellij.util.containers.FactoryMap;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;

class OCLanguage$1 extends SyntaxHighlighterFactory {
    private FactoryMap<HighlighterKey, OCFileHighlighter> myHighlighters = new FactoryMap<HighlighterKey, OCFileHighlighter>() {
        @Nullable
        protected OCFileHighlighter create(final HighlighterKey highlighterKey) {
            return new OCFileHighlighter(highlighterKey.myLanguageKind, highlighterKey.mySupportNullability, highlighterKey.myAllowGccAutoType, highlighterKey.myAllowAvailabilityExpression);
        }
    };
    
    @NotNull
    public SyntaxHighlighter getSyntaxHighlighter(final Project project, final VirtualFile virtualFile) {
        OCLanguageKind ocLanguageKind = CLanguageKind.maxLanguage(project);
        SyntaxHighlighter syntaxHighlighter = null;
        Label_0078: {
            Label_0023: {
                try {
                    if (virtualFile == null) {
                        break Label_0078;
                    }
                    final VirtualFile virtualFile2 = virtualFile;
                    final boolean b = virtualFile2.isValid();
                    if (b) {
                        break Label_0023;
                    }
                    break Label_0078;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final VirtualFile virtualFile2 = virtualFile;
                    final boolean b = virtualFile2.isValid();
                    if (!b) {
                        break Label_0078;
                    }
                    if (project == null) {
                        break Label_0078;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            final PsiFile file = PsiManager.getInstance(project).findFile(virtualFile);
            Object o = null;
            Label_0065: {
                try {
                    if (file instanceof OCFile) {
                        o = file;
                        break Label_0065;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                o = null;
            }
            final Object o2 = o;
            if (o2 != null) {
                ocLanguageKind = OCLanguageKindCalculator.calculateLanguageKindFast((PsiFile)o2);
            }
            try {
                syntaxHighlighter = (SyntaxHighlighter)this.myHighlighters.get((Object)new HighlighterKey(ocLanguageKind, true, true, true));
                if (syntaxHighlighter == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/OCLanguage$1", "getSyntaxHighlighter"));
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        return syntaxHighlighter;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    class HighlighterKey
    {
        private OCLanguageKind myLanguageKind;
        private boolean mySupportNullability;
        private boolean myAllowGccAutoType;
        private boolean myAllowAvailabilityExpression;
        
        public HighlighterKey(final OCLanguageKind myLanguageKind, final boolean mySupportNullability, final boolean myAllowGccAutoType, final boolean myAllowAvailabilityExpression) {
            this.myLanguageKind = myLanguageKind;
            this.mySupportNullability = mySupportNullability;
            this.myAllowGccAutoType = myAllowGccAutoType;
            this.myAllowAvailabilityExpression = myAllowAvailabilityExpression;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final HighlighterKey highlighterKey = (HighlighterKey)o;
            return this.mySupportNullability == highlighterKey.mySupportNullability && this.myAllowGccAutoType == highlighterKey.myAllowGccAutoType && this.myAllowAvailabilityExpression == highlighterKey.myAllowAvailabilityExpression && this.myLanguageKind.equals(highlighterKey.myLanguageKind);
        }
        
        @Override
        public int hashCode() {
            return 31 * this.myLanguageKind.hashCode() + (this.mySupportNullability ? 1 : 0);
        }
    }
}