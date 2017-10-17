// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import com.jetbrains.cidr.lang.workspace.OCResolveLanguageAndConfiguration;
import com.intellij.openapi.project.Project;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collection;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.progress.util.ReadTask;

class OCResolveContextPanel$3 extends ReadTask {
    final /* synthetic */ PsiFile val$psiFile;
    final /* synthetic */ VirtualFile val$file;
    final /* synthetic */ ProgressIndicator val$progress;
    final /* synthetic */ ProgressIndicator val$globalProgress;
    final /* synthetic */ OCResolveContextPanel this$0;
    
    @Override
    public void computeInReadAction(@NotNull final ProgressIndicator progressIndicator) {
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel$3", "computeInReadAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0110: {
            Label_0081: {
                try {
                    if (OCResolveContextPanel.access$600(OCResolveContextPanel.this)) {
                        return;
                    }
                    final ReadTask readTask = this;
                    final OCResolveContextPanel ocResolveContextPanel = readTask.this$0;
                    final Project project = OCResolveContextPanel.access$700(ocResolveContextPanel);
                    final boolean b = project.isDisposed();
                    if (b) {
                        return;
                    }
                    break Label_0081;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final ReadTask readTask = this;
                    final OCResolveContextPanel ocResolveContextPanel = readTask.this$0;
                    final Project project = OCResolveContextPanel.access$700(ocResolveContextPanel);
                    final boolean b = project.isDisposed();
                    if (b) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    if (!this.val$psiFile.isValid()) {
                        break Label_0110;
                    }
                    final ReadTask readTask2 = this;
                    final OCResolveContextPanel ocResolveContextPanel2 = readTask2.this$0;
                    final boolean b2 = OCResolveContextPanel.access$800(ocResolveContextPanel2);
                    if (!b2) {
                        break Label_0110;
                    }
                    break Label_0110;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            try {
                final ReadTask readTask2 = this;
                final OCResolveContextPanel ocResolveContextPanel2 = readTask2.this$0;
                final boolean b2 = OCResolveContextPanel.access$800(ocResolveContextPanel2);
                if (!b2) {
                    OCResolveContextPanel.access$900(OCResolveContextPanel.this, this.val$file, null, false, false, null, true);
                    return;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        this.val$progress.checkCanceled();
        OCResolveContextPanel.access$402(OCResolveContextPanel.this, true);
        OCResolveLanguageAndConfiguration orCalculateParsedResolveLanguageAndConfiguration;
        try {
            orCalculateParsedResolveLanguageAndConfiguration = OCInclusionContextUtil.getOrCalculateParsedResolveLanguageAndConfiguration(this.val$psiFile);
        }
        finally {
            OCResolveContextPanel.access$402(OCResolveContextPanel.this, false);
        }
        final OCResolveConfiguration configuration = orCalculateParsedResolveLanguageAndConfiguration.getConfiguration();
        final OCLanguageKind languageKind = orCalculateParsedResolveLanguageAndConfiguration.getLanguageKind();
        this.val$progress.checkCanceled();
        OCResolveContextPanel.access$900(OCResolveContextPanel.this, this.val$file, configuration, false, false, languageKind, true);
        final Collection collection = (Collection)OCResolveContextPanel.access$1000(this.val$psiFile, false, this.val$progress).first;
        OCResolveContextPanel this$0 = null;
        VirtualFile val$file = null;
        OCResolveConfiguration ocResolveConfiguration2 = null;
        boolean b4 = false;
        boolean b5 = false;
        Label_0342: {
            Label_0322: {
                Label_0300: {
                    Label_0278: {
                        try {
                            this.val$progress.checkCanceled();
                            if (collection.size() <= 0) {
                                break Label_0300;
                            }
                            final Collection collection2 = collection;
                            final OCResolveConfiguration ocResolveConfiguration = configuration;
                            final boolean b3 = collection2.contains(ocResolveConfiguration);
                            if (!b3) {
                                break Label_0278;
                            }
                            break Label_0300;
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                        try {
                            final Collection collection2 = collection;
                            final OCResolveConfiguration ocResolveConfiguration = configuration;
                            final boolean b3 = collection2.contains(ocResolveConfiguration);
                            if (!b3) {
                                OCResolveContextPanel.access$1100(this.val$psiFile, (OCResolveConfiguration)ContainerUtil.getFirstItem(collection));
                                return;
                            }
                        }
                        catch (IllegalArgumentException ex7) {
                            throw a(ex7);
                        }
                    }
                    try {
                        this$0 = OCResolveContextPanel.this;
                        val$file = this.val$file;
                        if ((ocResolveConfiguration2 = configuration) != null) {
                            b4 = true;
                            break Label_0322;
                        }
                    }
                    catch (IllegalArgumentException ex8) {
                        throw a(ex8);
                    }
                }
                b4 = false;
                try {
                    if (collection.size() > 1) {
                        b5 = true;
                        break Label_0342;
                    }
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
            }
            b5 = false;
        }
        OCResolveContextPanel.access$900(this$0, val$file, ocResolveConfiguration2, b4, b5, languageKind, false);
    }
    
    @Override
    public void onCanceled(@NotNull final ProgressIndicator progressIndicator) {
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel$3", "onCanceled"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!this.val$globalProgress.isCanceled()) {
                OCResolveContextPanel.access$1200(OCResolveContextPanel.this, this.val$file, this.val$psiFile, this.val$globalProgress);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}