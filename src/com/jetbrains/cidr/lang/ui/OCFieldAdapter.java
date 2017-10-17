// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.progress.util.ProgressIndicatorUtils;
import com.intellij.openapi.progress.EmptyProgressIndicator;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.util.Function;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.util.Computable;
import com.intellij.codeInsight.completion.PrefixMatcher;
import java.util.Collection;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;

public interface OCFieldAdapter<SUPPLEMENTED>
{
    @NotNull
    String getName(final SUPPLEMENTED p0);
    
    @Nullable
    Icon getIcon(final SUPPLEMENTED p0);
    
    @Nullable
    String getTypeText(final SUPPLEMENTED p0);
    
    @NotNull
    String getReadableName(final SUPPLEMENTED p0);
    
    @NotNull
    Collection<SUPPLEMENTED> collectValuesFromProject(@NotNull final Project p0, @Nullable final Condition<SUPPLEMENTED> p1);
    
    @NotNull
    PrefixMatcher createPrefixMatcher(@NotNull final String p0);
    
    String getPrefix(@NotNull final String p0, final int p1);
    
    @NotNull
    default <SUPPLEMENTED_COLLECTION extends Collection> SUPPLEMENTED_COLLECTION collectAllValues(@NotNull final Project project, @NotNull final SUPPLEMENTED_COLLECTION supplemented_COLLECTION, @NotNull final Computable<SUPPLEMENTED_COLLECTION> computable) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/ui/OCFieldAdapter", "collectAllValues"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw a(ex);
        }
        try {
            if (supplemented_COLLECTION == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "empty", "com/jetbrains/cidr/lang/ui/OCFieldAdapter", "collectAllValues"));
            }
        }
        catch (ProcessCanceledException ex2) {
            throw a(ex2);
        }
        try {
            if (computable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "computable", "com/jetbrains/cidr/lang/ui/OCFieldAdapter", "collectAllValues"));
            }
        }
        catch (ProcessCanceledException ex3) {
            throw a(ex3);
        }
        Collection collection2 = null;
        Label_0188: {
            Collection collection = null;
            Label_0153: {
                try {
                    if (!project.isDisposed()) {
                        break Label_0188;
                    }
                    collection = supplemented_COLLECTION;
                    if (collection == null) {
                        break Label_0153;
                    }
                    return (SUPPLEMENTED_COLLECTION)collection;
                }
                catch (ProcessCanceledException ex4) {
                    throw a(ex4);
                }
                try {
                    collection = supplemented_COLLECTION;
                    if (collection == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCFieldAdapter", "collectAllValues"));
                    }
                }
                catch (ProcessCanceledException ex5) {
                    throw a(ex5);
                }
            }
            return (SUPPLEMENTED_COLLECTION)collection;
            try {
                collection2 = computeWithWriteActionPriority((com.intellij.util.Function<ProgressIndicator, SUPPLEMENTED_COLLECTION>)(progressIndicator -> {
                    try {
                        if (computable == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "computable", "com/jetbrains/cidr/lang/ui/OCFieldAdapter", "lambda$collectAllValues$1"));
                        }
                    }
                    catch (ProcessCanceledException ex) {
                        throw a(ex);
                    }
                    Collection collection = null;
                    while (collection == null) {
                        try {
                            collection = (Collection)ProgressManager.getInstance().runProcess(() -> {
                                try {
                                    if (computable == null) {
                                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "computable", "com/jetbrains/cidr/lang/ui/OCFieldAdapter", "lambda$null$0"));
                                    }
                                }
                                catch (ProcessCanceledException ex) {
                                    throw a(ex);
                                }
                                return (Collection)ApplicationManager.getApplication().runReadAction(computable);
                            }, progressIndicator);
                        }
                        catch (ProcessCanceledException ex2) {}
                    }
                    return collection;
                }));
                if (collection2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCFieldAdapter", "collectAllValues"));
                }
            }
            catch (ProcessCanceledException ex6) {
                throw a(ex6);
            }
        }
        return (SUPPLEMENTED_COLLECTION)collection2;
    }
    
    default <T> T computeWithWriteActionPriority(@NotNull final Function<ProgressIndicator, T> function) {
        try {
            if (function == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "f", "com/jetbrains/cidr/lang/ui/OCFieldAdapter", "computeWithWriteActionPriority"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw a(ex);
        }
        final Disposable disposable = Disposer.newDisposable();
        final EmptyProgressIndicator emptyProgressIndicator = new EmptyProgressIndicator();
        ProgressIndicatorUtils.forceWriteActionPriority((ProgressIndicator)emptyProgressIndicator, disposable);
        try {
            return (T)function.fun((Object)emptyProgressIndicator);
        }
        finally {
            Disposer.dispose(disposable);
        }
    }
    
    default ProcessCanceledException a(final ProcessCanceledException ex) {
        return ex;
    }
}
