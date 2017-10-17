// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import java.util.Iterator;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import java.util.List;
import com.intellij.openapi.application.WriteAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewStringsFileAction;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.Ref;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.intentions.OCLocalizeStringIntentionAction;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.FileModificationService;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.psi.OCStringsFile;
import com.intellij.util.CommonProcessors;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.resolve.references.OCStringResourceReference;

public class OCProvideStringLocalizationsIntentionAction extends OCQuickFix
{
    private OCStringResourceReference myReference;
    
    public OCProvideStringLocalizationsIntentionAction(final OCStringResourceReference myReference) {
        this.myReference = myReference;
    }
    
    @Override
    protected String getTextInternal() {
        return "Provide localizations for \"" + this.myReference.getCanonicalText() + "\"";
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Provide localizations";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCProvideStringLocalizationsIntentionAction", "getFamilyName"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @Override
    public boolean isAvailable() {
        Label_0030: {
            try {
                if (!OCSearchScope.isInProjectSources(this.myReference.getElement())) {
                    return false;
                }
                final OCProvideStringLocalizationsIntentionAction ocProvideStringLocalizationsIntentionAction = this;
                final OCStringResourceReference ocStringResourceReference = ocProvideStringLocalizationsIntentionAction.myReference;
                final String s = ocStringResourceReference.getTableFileName();
                if (s != null) {
                    break Label_0030;
                }
                return false;
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            try {
                final OCProvideStringLocalizationsIntentionAction ocProvideStringLocalizationsIntentionAction = this;
                final OCStringResourceReference ocStringResourceReference = ocProvideStringLocalizationsIntentionAction.myReference;
                final String s = ocStringResourceReference.getTableFileName();
                if (s != null) {
                    return true;
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Override
    protected void invoke(final PsiFile psiFile) {
        try {
            if (this.checkFilesForWrite()) {
                this.invokeBool(psiFile);
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
    }
    
    public boolean checkFilesForWrite() {
        final CommonProcessors.CollectProcessor<OCStringsFile> collectProcessor = new CommonProcessors.CollectProcessor<OCStringsFile>() {
            final /* synthetic */ String val$key = OCProvideStringLocalizationsIntentionAction.this.myReference.getCanonicalText();
            
            protected boolean accept(final OCStringsFile ocStringsFile) {
                return ocStringsFile.findStringPair(this.val$key) == null;
            }
        };
        this.myReference.processStringFiles((Processor<OCStringsFile>)collectProcessor);
        return FileModificationService.getInstance().preparePsiElementsForWrite((PsiElement[])collectProcessor.getResults().toArray(new PsiElement[collectProcessor.getResults().size()]));
    }
    
    public boolean invokeBool(final PsiFile psiFile) {
        final String canonicalText = this.myReference.getCanonicalText();
        final String string = "\n\"" + OCLocalizeStringIntentionAction.getKeyString(canonicalText) + "\" = \"" + canonicalText + "\";";
        final ArrayList list = new ArrayList();
        final Ref create = Ref.create((Object)false);
        Label_0171: {
            try {
                this.myReference.processStringFiles((Processor<OCStringsFile>)(ocStringsFile -> {
                    try {
                        if (ocStringsFile.findStringPair(canonicalText) == null) {
                            list.add(ocStringsFile);
                        }
                    }
                    catch (RuntimeException ex) {
                        throw b(ex);
                    }
                    create.set((Object)true);
                    return true;
                }));
                if ((boolean)create.get() || ApplicationManager.getApplication().isUnitTestMode()) {
                    break Label_0171;
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            final String nameWithoutExtension = FileUtil.getNameWithoutExtension(this.myReference.getTableFileName());
            final OCNewStringsFileAction ocNewStringsFileAction = new OCNewStringsFileAction(nameWithoutExtension, string) {
                @Override
                protected boolean isNameFieldEnabled() {
                    return false;
                }
                
                @Override
                protected void openCreatedFiles(@NotNull final PsiFile[] array) {
                    try {
                        if (array == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "createdElements", "com/jetbrains/cidr/lang/quickfixes/OCProvideStringLocalizationsIntentionAction$2", "openCreatedFiles"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            };
            try {
                if (ocNewStringsFileAction.performAction(psiFile.getProject(), psiFile.getParent(), psiFile, nameWithoutExtension) != null) {
                    return true;
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            return false;
        }
        WriteAction.run(() -> {
            for (final OCStringsFile ocStringsFile : list) {
                OCChangeUtil.changeText(ocStringsFile.getProject(), (PsiFile)ocStringsFile, ocStringsFile.getTextLength(), 0, string, false);
            }
        });
        return true;
    }
    
    @Override
    public boolean startInWriteAction() {
        return false;
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
