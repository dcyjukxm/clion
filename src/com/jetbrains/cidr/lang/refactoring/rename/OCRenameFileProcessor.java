// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.rename;

import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCGroupedFileNamings;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.vfs.VirtualFile;
import java.io.File;
import com.jetbrains.cidr.lang.OCLog;
import com.jetbrains.cidr.lang.OCGroupedFileNaming;
import com.intellij.psi.PsiDirectory;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import com.jetbrains.cidr.lang.refactoring.OCRenameProcessorExtension;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;
import com.intellij.openapi.util.Pair;
import java.util.LinkedList;
import java.util.HashMap;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.refactoring.ui.ConflictsDialog;
import com.intellij.usageView.UsageInfo;
import org.jetbrains.annotations.NotNull;
import com.intellij.refactoring.rename.RenameProcessor;
import com.intellij.refactoring.rename.RenameDialog;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import java.util.Iterator;
import java.util.Collection;
import com.intellij.util.containers.MultiMap;
import java.util.Map;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.rename.RenamePsiFileProcessor;

public class OCRenameFileProcessor extends RenamePsiFileProcessor
{
    @Override
    public void prepareRenaming(final PsiElement psiElement, final String s, final Map<PsiElement, String> map) {
        this.gatherRenames(psiElement, s, map);
    }
    
    @Override
    public void findExistingNameConflicts(final PsiElement psiElement, final String s, final MultiMap<PsiElement, String> multiMap) {
        final MultiMap multiMap2 = new MultiMap();
        this.findRenameConflicts(psiElement, s, (MultiMap<PsiElement, Conflict>)multiMap2);
        for (final Map.Entry<K, Collection> entry : multiMap2.entrySet()) {
            final Iterator<Conflict> iterator2 = entry.getValue().iterator();
            while (iterator2.hasNext()) {
                multiMap.putValue((Object)entry.getKey(), (Object)iterator2.next().toString());
            }
        }
        super.findExistingNameConflicts(psiElement, s, multiMap);
    }
    
    @Override
    public RenameDialog createRenameDialog(final Project project, final PsiElement psiElement, final PsiElement psiElement2, final Editor editor) {
        return new PsiFileRenameDialog(project, psiElement, psiElement2, editor) {
            @Override
            protected RenameProcessor createRenameProcessor(final String s) {
                return new RenameProcessor(this.getProject(), this.getPsiElement(), s, this.isSearchInComments(), this.isSearchInNonJavaFiles()) {
                    @NotNull
                    @Override
                    protected ConflictsDialog createConflictsDialog(@NotNull final MultiMap<PsiElement, String> multiMap, final UsageInfo[] array) {
                        try {
                            if (multiMap == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "conflicts", "com/jetbrains/cidr/lang/refactoring/rename/OCRenameFileProcessor$1$1", "createConflictsDialog"));
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        ConflictsDialog conflictsDialog;
                        try {
                            conflictsDialog = new ConflictsDialog(this.myProject, multiMap, null, false, false);
                            if (conflictsDialog == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/rename/OCRenameFileProcessor$1$1", "createConflictsDialog"));
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        return conflictsDialog;
                    }
                    
                    private static IllegalArgumentException a(final IllegalArgumentException ex) {
                        return ex;
                    }
                };
            }
        };
    }
    
    public void gatherRenames(final PsiElement psiElement, final String s, final Map<PsiElement, String> map) {
        try {
            if (!(psiElement instanceof PsiFileSystemItem)) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final HashMap<Object, String> hashMap = (HashMap<Object, String>)new HashMap<PsiElement, String>();
        final LinkedList<Pair> list = new LinkedList<Pair>();
        list.addLast(Pair.create((Object)psiElement, (Object)s));
        while (!list.isEmpty()) {
            final Pair pair = list.removeFirst();
            final PsiFileSystemItem psiFileSystemItem3 = (PsiFileSystemItem)pair.first;
            final String s4 = (String)pair.second;
            hashMap.put(psiFileSystemItem3, s4);
            final ArrayList<PsiFileSystemItem> list2 = new ArrayList<PsiFileSystemItem>();
            list2.add(psiFileSystemItem3);
            final Map<PsiFileSystemItem, String> map2;
            final List<PsiFileSystemItem> list3;
            a(psiFileSystemItem3, s4, (psiFileSystemItem, s2) -> {
                try {
                    if (map2.put(psiFileSystemItem, s2) == null) {
                        list3.add(psiFileSystemItem);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                return;
            });
            for (final PsiFileSystemItem psiFileSystemItem4 : list2) {
                final Map map3;
                final Deque<Pair> deque;
                this.findExtraVariants(psiFileSystemItem4, (String)hashMap.get(psiFileSystemItem4), (psiFileSystemItem2, s3) -> {
                    try {
                        if (map3.get(psiFileSystemItem2) == null) {
                            deque.addLast(Pair.create((Object)psiFileSystemItem2, (Object)s3));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    return;
                });
            }
        }
        map.putAll((Map<? extends PsiElement, ? extends String>)hashMap);
    }
    
    protected void findExtraVariants(@NotNull final PsiFileSystemItem psiFileSystemItem, @NotNull final String s, @NotNull final VariantProcessor variantProcessor) {
        try {
            if (psiFileSystemItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "variant", "com/jetbrains/cidr/lang/refactoring/rename/OCRenameFileProcessor", "findExtraVariants"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newRootName", "com/jetbrains/cidr/lang/refactoring/rename/OCRenameFileProcessor", "findExtraVariants"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (variantProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "localizedProcessor", "com/jetbrains/cidr/lang/refactoring/rename/OCRenameFileProcessor", "findExtraVariants"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final OCRenameProcessorExtension[] array = (OCRenameProcessorExtension[])Extensions.getExtensions((ExtensionPointName)OCRenameProcessorExtension.EP);
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i].findExtraVariants(psiFileSystemItem, s, variantProcessor);
        }
    }
    
    private static void a(final PsiFileSystemItem psiFileSystemItem, final String s, final VariantProcessor variantProcessor) {
        if (psiFileSystemItem instanceof PsiFile) {
            final PsiDirectory parent = ((PsiFile)psiFileSystemItem).getParent();
            final OCGroupedFileNaming a = a((PsiElement)psiFileSystemItem);
            try {
                if (parent == null || a == null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            final String nameWithoutExtension = FileUtil.getNameWithoutExtension(a.getBaseName(s));
            final String baseName = a.getBaseName(psiFileSystemItem.getName());
            final String nameWithoutExtension2 = FileUtil.getNameWithoutExtension(baseName);
            for (final PsiElement psiElement : parent.getChildren()) {
                Label_0199: {
                    if (psiElement instanceof PsiFile) {
                        final PsiFile psiFile = (PsiFile)psiElement;
                        if (!psiFile.equals(psiFileSystemItem)) {
                            final OCGroupedFileNaming a2 = a((PsiElement)psiFile);
                            try {
                                if (a2 == null || !baseName.equals(a2.getBaseName(psiFile.getName()))) {
                                    break Label_0199;
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw b(ex2);
                            }
                            variantProcessor.processVariant((PsiFileSystemItem)psiFile, psiFile.getName().replace(nameWithoutExtension2, nameWithoutExtension));
                        }
                    }
                }
            }
        }
    }
    
    public void findRenameConflicts(final PsiElement psiElement, final String s, final MultiMap<PsiElement, Conflict> multiMap) {
        final HashMap<PsiElement, String> hashMap = new HashMap<PsiElement, String>();
        try {
            this.gatherRenames(psiElement, s, hashMap);
            if (hashMap.size() < 2) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final MultiMap multiMap2 = new MultiMap();
        for (final Map.Entry<PsiElement, String> entry : hashMap.entrySet()) {
            final PsiElement psiElement2 = entry.getKey();
            OCLog.LOG.assertTrue(psiElement2 instanceof PsiFileSystemItem);
            final VirtualFile virtualFile = ((PsiFileSystemItem)psiElement2).getVirtualFile();
            Logger log = null;
            boolean b = false;
            Label_0127: {
                try {
                    log = OCLog.LOG;
                    if (virtualFile != null) {
                        b = true;
                        break Label_0127;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                b = false;
            }
            log.assertTrue(b);
            multiMap2.putValue((Object)new File(virtualFile.getParent().getPath(), entry.getValue()).getPath(), (Object)psiElement2);
        }
        try {
            if (multiMap2.size() == hashMap.size()) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        for (final Map.Entry<K, Collection> entry2 : multiMap2.entrySet()) {
            final Collection<PsiElement> collection = entry2.getValue();
            try {
                if (collection == null || collection.size() <= 1) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            multiMap.putValue((Object)psiElement, (Object)new Conflict(new File((String)entry2.getKey()).getName(), collection));
        }
    }
    
    @Override
    public boolean forcesShowPreview() {
        return false;
    }
    
    @Nullable
    private static OCGroupedFileNaming a(final PsiElement psiElement) {
        try {
            if (!(psiElement instanceof PsiFile)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return OCGroupedFileNamings.getGroupedFileNaming(((PsiFile)psiElement).getVirtualFile());
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class Conflict
    {
        public final String name;
        public final Collection<PsiElement> elements;
        
        public Conflict(final String name, final Collection<PsiElement> elements) {
            this.name = name;
            this.elements = elements;
        }
        
        private static String a(final String s) {
            return StringUtil.wrapWithDoubleQuote(s);
        }
        
        @Override
        public String toString() {
            return "Trying to rename " + StringUtil.join((Collection)ContainerUtil.map((Collection)ContainerUtil.filter((Collection)this.elements, psiElement -> psiElement instanceof PsiFileSystemItem && !((PsiFileSystemItem)psiElement).getName().equals(this.name)), psiElement -> {
                OCLog.LOG.assertTrue(psiElement instanceof PsiFileSystemItem);
                return a(((PsiFileSystemItem)psiElement).getName());
            }), ", ") + " to existing file name " + a(this.name);
        }
    }
    
    @FunctionalInterface
    public interface VariantProcessor
    {
        void processVariant(final PsiFileSystemItem p0, final String p1);
    }
}
