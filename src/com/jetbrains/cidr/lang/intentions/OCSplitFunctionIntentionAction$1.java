// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.intellij.ide.util.EditorHelper;
import com.intellij.util.ObjectUtils;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.intellij.util.text.CharArrayUtil;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import java.util.Collection;
import com.intellij.codeInsight.FileModificationService;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.generate.OCGenerateUtil;
import com.intellij.openapi.editor.RangeMarker;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiDocumentManager;
import java.util.Set;
import com.intellij.openapi.command.WriteCommandAction;

class OCSplitFunctionIntentionAction$1 extends WriteCommandAction {
    final /* synthetic */ Set val$files;
    final /* synthetic */ PsiDocumentManager val$psiDocumentManager;
    final /* synthetic */ Document val$declarationDocument;
    final /* synthetic */ Document val$definitionDocument;
    final /* synthetic */ TextRange val$range;
    final /* synthetic */ RangeMarker val$newLocation;
    final /* synthetic */ String val$definitionSignature;
    final /* synthetic */ String val$bodyText;
    final /* synthetic */ OCGenerateUtil.ReplacePosition val$pos;
    final /* synthetic */ Project val$project;
    
    protected void run(@NotNull final Result result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/intentions/OCSplitFunctionIntentionAction$1", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        try {
            if (!FileModificationService.getInstance().preparePsiElementsForWrite((Collection)this.val$files)) {
                return;
            }
        }
        catch (Throwable t2) {
            throw a(t2);
        }
        try {
            this.val$psiDocumentManager.doPostponedOperationsAndUnblockDocument(this.val$declarationDocument);
            if (this.val$declarationDocument != this.val$definitionDocument) {
                this.val$psiDocumentManager.doPostponedOperationsAndUnblockDocument(this.val$definitionDocument);
            }
        }
        catch (Throwable t3) {
            throw a(t3);
        }
        this.val$declarationDocument.replaceString(this.val$range.getStartOffset(), this.val$range.getEndOffset(), (CharSequence)";");
        final int startOffset = this.val$newLocation.getStartOffset();
        this.val$definitionDocument.deleteString(this.val$newLocation.getStartOffset(), this.val$newLocation.getEndOffset());
        final RangeMarker rangeMarker = this.val$definitionDocument.createRangeMarker(startOffset, startOffset);
        rangeMarker.setGreedyToRight(true);
        this.val$definitionDocument.insertString(startOffset, (CharSequence)this.val$definitionSignature);
        rangeMarker.setGreedyToRight(false);
        final RangeMarker rangeMarker2 = this.val$definitionDocument.createRangeMarker(rangeMarker.getEndOffset(), rangeMarker.getEndOffset());
        rangeMarker2.setGreedyToRight(true);
        this.val$definitionDocument.insertString(rangeMarker2.getStartOffset(), (CharSequence)this.val$bodyText);
        rangeMarker2.setGreedyToRight(false);
        this.val$psiDocumentManager.commitDocument(this.val$definitionDocument);
        OCChangeUtil.reformatTextIfNotInjected(this.val$pos.file, rangeMarker.getStartOffset(), rangeMarker.getEndOffset());
        this.val$psiDocumentManager.commitDocument(this.val$definitionDocument);
        OCChangeUtil.reformatTextIfNotInjected(this.val$pos.file, rangeMarker2.getStartOffset(), rangeMarker2.getStartOffset() + 1);
        CodeStyleManager.getInstance(this.val$project).adjustLineIndent(this.val$pos.file, TextRange.create(rangeMarker2.getStartOffset(), rangeMarker2.getEndOffset()));
        OCChangeUtil.reformatTextIfNotInjected(this.val$pos.file, rangeMarker2.getEndOffset(), CharArrayUtil.shiftForward(this.val$definitionDocument.getCharsSequence(), rangeMarker2.getEndOffset(), " \t\n"));
        this.val$psiDocumentManager.commitDocument(this.val$declarationDocument);
        final OCFunctionDeclaration ocFunctionDeclaration = (OCFunctionDeclaration)PsiTreeUtil.getParentOfType(this.val$pos.file.findElementAt(rangeMarker.getEndOffset()), (Class)OCFunctionDeclaration.class);
        PsiElement nameIdentifier = null;
        Label_0464: {
            try {
                if (ocFunctionDeclaration != null) {
                    nameIdentifier = ocFunctionDeclaration.getNameIdentifier();
                    break Label_0464;
                }
            }
            catch (Throwable t4) {
                throw a(t4);
            }
            nameIdentifier = null;
        }
        EditorHelper.openInEditor((PsiElement)ObjectUtils.notNull((Object)nameIdentifier, (Object)this.val$pos.file));
        rangeMarker.dispose();
        rangeMarker2.dispose();
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}