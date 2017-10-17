// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.lang.CLanguageKind;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.jetbrains.cidr.lang.psi.OCConfigurationOwner;
import com.intellij.psi.PsiManager;
import java.util.Iterator;
import java.util.Set;
import com.jetbrains.cidr.lang.preprocessor.OCImportGraph;
import com.intellij.util.Consumer;
import com.intellij.util.Producer;
import java.util.Collections;
import com.intellij.util.containers.HashSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.util.containers.MultiMap;
import com.intellij.openapi.progress.ProgressIndicator;

private static class OCSymbolTableBuilder
{
    private final ProgressIndicator myIndicator;
    private final MultiMap<OCBuildFileCategory, OCBuildFileDescriptor> myClusterization;
    
    public OCSymbolTableBuilder(@NotNull final Project project, @NotNull final ProgressIndicator myIndicator, @NotNull final Collection<VirtualFile> collection) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$OCSymbolTableBuilder", "<init>"));
        }
        if (myIndicator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "progressIndicator", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$OCSymbolTableBuilder", "<init>"));
        }
        if (collection == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$OCSymbolTableBuilder", "<init>"));
        }
        this.myClusterization = (MultiMap<OCBuildFileCategory, OCBuildFileDescriptor>)new MultiMap();
        this.myIndicator = myIndicator;
        OCSymbolTablesBuildingActivity.access$000(() -> {
            try {
                if (iterator.hasNext()) {
                    return iterator.next();
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return null;
        }, virtualFile -> {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$OCSymbolTableBuilder", "lambda$new$1"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (!virtualFile.isValid()) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final PsiFile file = PsiManager.getInstance(project).findFile(virtualFile);
            OCLanguageKind ocLanguageKind = null;
            Label_0106: {
                Label_0095: {
                    try {
                        if (!(file instanceof OCConfigurationOwner)) {
                            return;
                        }
                        if (!OCInclusionContextUtil.isNeedToFindRoot(file)) {
                            break Label_0095;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    ocLanguageKind = CLanguageKind.maxLanguage(project);
                    break Label_0106;
                }
                ocLanguageKind = ((OCConfigurationOwner)file).getKind();
            }
            for (final OCResolveConfiguration ocResolveConfiguration : OCInclusionContextUtil.getAllBuildConfigurationsForIndexing(virtualFile, project)) {
                this.myClusterization.putValue((Object)new OCBuildFileCategory(ocResolveConfiguration.getIndexingCluster(), ocLanguageKind), (Object)new OCBuildFileDescriptor(ocResolveConfiguration, virtualFile, ocLanguageKind));
            }
        }, myIndicator);
    }
    
    public void processBuildFiles() {
        final int size = this.myClusterization.values().size();
        final Set keySet = this.myClusterization.keySet();
        final ArrayList list = new ArrayList(keySet.size());
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final ArrayList<a> list2 = new ArrayList<a>();
        final HashSet set = new HashSet();
        final Iterator<OCBuildFileCategory> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            final Collection<Object> unmodifiableCollection = Collections.unmodifiableCollection((Collection<?>)this.myClusterization.get((Object)iterator.next()));
            ((Set)set).addAll(unmodifiableCollection);
            list2.add(() -> {
                while (iterator.hasNext()) {
                    final OCBuildFileDescriptor ocBuildFileDescriptor = iterator.next();
                    synchronized (set) {
                        if (((Set)set).contains(ocBuildFileDescriptor)) {
                            ((Set)set).remove(ocBuildFileDescriptor);
                            return ocBuildFileDescriptor;
                        }
                        continue;
                    }
                }
                return null;
            });
        }
        list.addAll(OCSymbolTablesBuildingActivity.access$600(new TaskProvider<OCBuildFileDescriptor>() {
            final /* synthetic */ a val$remainingProvider = () -> {
                synchronized (set) {
                    if (!((Set)set).isEmpty()) {
                        final OCBuildFileDescriptor ocBuildFileDescriptor = ((Set<OCBuildFileDescriptor>)set).iterator().next();
                        ((Set)set).remove(ocBuildFileDescriptor);
                        return ocBuildFileDescriptor;
                    }
                }
                return null;
            };
            
            @Override
            public Producer<OCBuildFileDescriptor> getItemProvider() {
                return (Producer<OCBuildFileDescriptor>)new PrioritizedBuildFileProvider((Collection)list2, this.val$remainingProvider);
            }
            
            @Override
            public Consumer<OCBuildFileDescriptor> getWorker() {
                return (Consumer<OCBuildFileDescriptor>)(ocBuildFileDescriptor -> {
                    OCImportGraph.buildSymbolAndRootHeaderCache(ocBuildFileDescriptor.myConfiguration, ocBuildFileDescriptor.myFile, ocBuildFileDescriptor.myLanguageKind, OCSymbolTableBuilder.this.myIndicator);
                    OCSymbolTableBuilder.this.myIndicator.setFraction(atomicInteger.incrementAndGet() / n);
                });
            }
        }, this.myIndicator));
        OCSymbolTablesBuildingActivity.access$700(list);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static final class OCBuildFileCategory
    {
        private final Object myConfigurationCluster;
        private final OCLanguageKind myKind;
        
        private OCBuildFileCategory(@Nullable final Object myConfigurationCluster, @Nullable final OCLanguageKind myKind) {
            this.myConfigurationCluster = myConfigurationCluster;
            this.myKind = myKind;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final OCBuildFileCategory ocBuildFileCategory = (OCBuildFileCategory)o;
            if (this.myKind != ocBuildFileCategory.myKind) {
                return false;
            }
            if (this.myConfigurationCluster != null) {
                if (this.myConfigurationCluster.equals(ocBuildFileCategory.myConfigurationCluster)) {
                    return true;
                }
            }
            else if (ocBuildFileCategory.myConfigurationCluster == null) {
                return true;
            }
            return false;
        }
        
        @Override
        public int hashCode() {
            return 31 * ((this.myConfigurationCluster != null) ? this.myConfigurationCluster.hashCode() : 0) + ((this.myKind != null) ? this.myKind.hashCode() : 0);
        }
    }
    
    private static final class OCBuildFileDescriptor
    {
        private final VirtualFile myFile;
        private final OCResolveConfiguration myConfiguration;
        private final OCLanguageKind myLanguageKind;
        
        private OCBuildFileDescriptor(final OCResolveConfiguration myConfiguration, final VirtualFile myFile, final OCLanguageKind myLanguageKind) {
            this.myConfiguration = myConfiguration;
            this.myFile = myFile;
            this.myLanguageKind = myLanguageKind;
        }
    }
    
    private static class PrioritizedBuildFileProvider implements a
    {
        private boolean myUseClusters;
        private Producer<OCBuildFileDescriptor> myCurrentProvider;
        @NotNull
        private final Collection<a> myProviders;
        @NotNull
        private final a myBottomProvider;
        
        private PrioritizedBuildFileProvider(@NotNull final Collection<a> myProviders, @NotNull final a myBottomProvider) {
            if (myProviders == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "providers", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$OCSymbolTableBuilder$PrioritizedBuildFileProvider", "<init>"));
            }
            if (myBottomProvider == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "bottomProvider", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$OCSymbolTableBuilder$PrioritizedBuildFileProvider", "<init>"));
            }
            this.myUseClusters = true;
            this.myProviders = myProviders;
            this.myBottomProvider = myBottomProvider;
        }
        
        @Nullable
        public OCBuildFileDescriptor produce() {
            final OCBuildFileDescriptor a = this.a();
            try {
                if (a != null) {
                    return a;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return (OCBuildFileDescriptor)this.myBottomProvider.produce();
        }
        
        private OCBuildFileDescriptor a() {
            try {
                if (!this.myUseClusters) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            while (true) {
                if (this.myCurrentProvider != null) {
                    final Object myCurrentProvider = this.myCurrentProvider.produce();
                    try {
                        if (myCurrentProvider != null) {
                            return (OCBuildFileDescriptor)myCurrentProvider;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                Object myCurrentProvider;
                synchronized (this.myProviders) {
                    final Iterator<a> iterator = this.myProviders.iterator();
                    if (!iterator.hasNext()) {
                        break;
                    }
                    myCurrentProvider = iterator.next();
                    iterator.remove();
                }
                this.myCurrentProvider = (Producer<OCBuildFileDescriptor>)myCurrentProvider;
            }
            this.myUseClusters = false;
            return null;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    @FunctionalInterface
    private interface a extends Producer<OCBuildFileDescriptor>
    {
    }
}
