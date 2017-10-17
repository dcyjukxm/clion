// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import java.util.Collections;
import java.util.Collection;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import gnu.trove.THashSet;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.intellij.util.containers.HashMap;
import com.intellij.util.Function;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.ArrayList;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.Pair;
import java.util.Map;
import com.intellij.openapi.util.Key;
import com.jetbrains.cidr.lang.symbols.ProjectAndVirtualFileOwner;
import java.io.Serializable;

public class FileSymbolTable implements Serializable, ProjectAndVirtualFileOwner
{
    private static final Key<Map<Class, Pair<Integer, Integer>>> DEBUG_STATS_KEY;
    private static final Logger LOG;
    private final ArrayList<OCSymbol> myContents;
    private ContextSignature mySignature;
    private volatile int myPackStamp;
    private transient VirtualFile myFile;
    private transient Project myProject;
    private transient volatile int myUsingCount;
    
    public FileSymbolTable() {
        this.myContents = new ArrayList<OCSymbol>();
    }
    
    public FileSymbolTable(@NotNull final VirtualFile myFile, @NotNull final Project myProject, @NotNull final ContextSignature mySignature) {
        if (myFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "<init>"));
        }
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "<init>"));
        }
        if (mySignature == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "signature", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "<init>"));
        }
        this.myContents = new ArrayList<OCSymbol>();
        this.myFile = myFile;
        this.mySignature = mySignature;
        this.myProject = myProject;
    }
    
    public void compact() {
        this.myContents.trimToSize();
    }
    
    @NotNull
    public ContextSignature getSignature() {
        ContextSignature mySignature;
        try {
            mySignature = this.mySignature;
            if (mySignature == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "getSignature"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return mySignature;
    }
    
    @Override
    public VirtualFile getContainingFile() {
        return this.myFile;
    }
    
    @NotNull
    public List<OCSymbol> getContents() {
        ArrayList<OCSymbol> myContents;
        try {
            myContents = this.myContents;
            if (myContents == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "getContents"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myContents;
    }
    
    @NotNull
    @Override
    public Project getProject() {
        Project myProject;
        try {
            myProject = this.myProject;
            if (myProject == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "getProject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myProject;
    }
    
    public void internSymbols(final Function<OCSymbol, OCSymbol> function) {
        for (int i = 0; i < this.myContents.size(); ++i) {
            final OCSymbol ocSymbol = this.myContents.get(i);
            final OCSymbol ocSymbol2 = (OCSymbol)function.fun((Object)ocSymbol);
            try {
                this.a(ocSymbol, false);
                if (ocSymbol != ocSymbol2) {
                    this.a(ocSymbol, true);
                    this.myContents.set(i, ocSymbol2);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
    }
    
    private void a(final OCSymbol ocSymbol, final boolean b) {
        try {
            if (!FileSymbolTable.LOG.isDebugEnabled()) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Object o = this.myProject.getUserData((Key)FileSymbolTable.DEBUG_STATS_KEY);
        if (o == null) {
            o = new HashMap();
            this.myProject.putUserData((Key)FileSymbolTable.DEBUG_STATS_KEY, o);
        }
        synchronized (o) {
            Pair pair = ((Map<Class<? extends OCNamespaceSymbol>, Pair>)o).get(((OCNamespaceSymbol)ocSymbol).getClass());
            if (pair == null) {
                pair = new Pair((Object)0, (Object)0);
            }
            Pair pair2;
            if (b) {
                pair2 = new Pair((Object)((int)pair.first + 1), pair.second);
            }
            else {
                pair2 = new Pair(pair.first, (Object)((int)pair.second + 2));
            }
            try {
                ((Map<Class<? extends OCNamespaceSymbol>, Pair>)o).put(((OCNamespaceSymbol)ocSymbol).getClass(), pair2);
                if (ocSymbol instanceof OCNamespaceSymbol) {
                    ((OCNamespaceSymbol)ocSymbol).processMembers(null, (Processor<OCSymbol>)(ocSymbol -> {
                        this.a(ocSymbol, false);
                        this.a(ocSymbol, true);
                        return true;
                    }));
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (ocSymbol instanceof OCClassSymbol) {
                    ((OCClassSymbol)ocSymbol).processMembers((String)null, (Class<? extends OCMemberSymbol>)OCMemberSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)(ocMemberSymbol -> {
                        this.a(ocMemberSymbol, false);
                        this.a(ocMemberSymbol, true);
                        return true;
                    }));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
    }
    
    public static void reportStats(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "reportStats"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Map map = (Map)project.getUserData((Key)FileSymbolTable.DEBUG_STATS_KEY);
        try {
            if (map == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        project.putUserData((Key)FileSymbolTable.DEBUG_STATS_KEY, (Object)null);
        for (final Map.Entry<Class, V> entry : map.entrySet()) {
            FileSymbolTable.LOG.debug(entry.getKey().getSimpleName() + ": interned=" + ((Pair)entry.getValue()).first + " count=" + ((Pair)entry.getValue()).second);
        }
    }
    
    public void updateOffsets(final int n, final int n2, final int n3, final THashSet<OCSymbol> set) {
        for (final OCSymbol ocSymbol : this.myContents) {
            try {
                if (!set.add((Object)ocSymbol)) {
                    continue;
                }
                ocSymbol.updateOffset(n, n2, n3);
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
    }
    
    @Override
    public void init(@Nullable final Project myProject, @Nullable final VirtualFile myFile) {
        this.myFile = myFile;
        this.myProject = myProject;
    }
    
    @Nullable
    public static FileSymbolTable forFile(@NotNull PsiFile originalFile, @NotNull final OCInclusionContext ocInclusionContext) {
        try {
            if (originalFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "forFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "forFile"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Project project = originalFile.getProject();
        OCSymbolTablesBuildingActivity.getInstance(project).assertParsingAndSymbolBuildingAllowed();
        originalFile = originalFile.getOriginalFile();
        return FileSymbolTablesCache.getInstance(project).forFile(originalFile, ocInclusionContext);
    }
    
    @Nullable
    public <T extends OCSymbol> T findSymbol(@NotNull final PsiElement psiElement, @NotNull final Class<T> clazz) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "findSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolClass", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "findSymbol"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.findSymbol(psiElement, null, clazz);
    }
    
    @Nullable
    public <T extends OCSymbol> T findSymbol(@NotNull final PsiElement psiElement, @Nullable final String s, @NotNull final Class<T> clazz) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "findSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolClass", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "findSymbol"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        String symbolName = null;
        Label_0104: {
            try {
                if (s != null) {
                    symbolName = s;
                    break Label_0104;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            symbolName = OCElementUtil.getSymbolName(psiElement);
        }
        final String s2 = symbolName;
        try {
            if (psiElement instanceof OCElement) {
                final long complexOffset = ((OCElement)psiElement).getComplexOffset();
                return this.a(s2, complexOffset, clazz);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final long complexOffset = psiElement.getTextOffset();
        return this.a(s2, complexOffset, clazz);
    }
    
    @Nullable
    public <T extends OCSymbol> T findSymbol(@Nullable final String s, @NotNull final Class<T> clazz) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolClass", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "findSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.a(s, -1L, clazz);
    }
    
    @Nullable
    private <T extends OCSymbol> T a(@Nullable final String s, final long n, @NotNull final Class<T> clazz) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolClass", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "findSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCSymbol[] array = { null };
        final OCSymbol[] array2 = { null };
        final Processor<OCSymbol> processor = (Processor<OCSymbol>)new Processor<OCSymbol>() {
            public boolean process(OCSymbol delegate) {
                delegate = delegate.getDelegate();
                if ((n == -1L || delegate.getOffset() == OCSymbolOffsetUtil.getTextOffset(n)) && (s == null || Comparing.equal(s, delegate.getName())) && clazz.isAssignableFrom(((OCClassSymbol)delegate).getClass())) {
                    if (n == -1L || delegate.getComplexOffset() == n) {
                        array[0] = delegate;
                        return false;
                    }
                    array2[0] = delegate;
                }
                if (delegate instanceof OCNamespaceSymbol) {
                    return ((OCNamespaceSymbol)delegate).processMembers(null, (Processor<OCSymbol>)this);
                }
                return !(delegate instanceof OCClassSymbol) || ((OCClassSymbol)delegate).processMembers(s, (Processor<OCMemberSymbol>)(ocMemberSymbol -> processor.process((Object)ocMemberSymbol)));
            }
        };
        try {
            ContainerUtil.process((List)this.myContents, (Processor)processor);
            if (array[0] != null) {
                return (T)array[0];
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (T)array2[0];
    }
    
    public boolean processFile(final Processor<OCSymbol> processor) {
        for (final OCSymbol ocSymbol : this.myContents) {
            try {
                ProgressManager.checkCanceled();
                if (!processor.process((Object)ocSymbol.getDelegate())) {
                    return false;
                }
                continue;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return true;
    }
    
    public boolean processFile(final Processor<OCSymbol> processor, final int n, final int n2) {
        return OCResolveUtil.processSymbolsFromList((Processor<OCSymbol>)(ocSymbol -> processor.process((Object)ocSymbol.getDelegate())), this.myContents, n, n2);
    }
    
    public boolean processIncludes(final Processor<OCIncludeSymbol> processor) {
        for (final OCIncludeSymbol next : this.myContents) {
            if (next instanceof OCIncludeSymbol) {
                final OCIncludeSymbol ocIncludeSymbol = next;
                try {
                    if (!processor.process((Object)ocIncludeSymbol)) {
                        return false;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
        }
        return true;
    }
    
    public boolean processSymbols(@NotNull final Processor<OCSymbol> processor, @Nullable final String s, @Nullable final Map<OCSymbol, VirtualFile> map, final OCInclusionContext ocInclusionContext) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "processSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.processSymbols(processor, s, (Set<FileSymbolTable>)new THashSet(), map, null, ocInclusionContext, null);
    }
    
    public boolean shallowProcessSymbols(@NotNull final Processor<OCSymbol> processor) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "shallowProcessSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (final OCSymbol next : this.myContents) {
            try {
                if (!(next instanceof OCSymbol)) {
                    continue;
                }
                final Processor<OCSymbol> processor2 = processor;
                final OCSymbol ocSymbol = next;
                final OCSymbol ocSymbol2 = ocSymbol;
                final OCSymbol ocSymbol3 = ocSymbol2.getDelegate();
                final boolean b = processor2.process((Object)ocSymbol3);
                if (!b) {
                    return false;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final Processor<OCSymbol> processor2 = processor;
                final OCSymbol ocSymbol = next;
                final OCSymbol ocSymbol2 = ocSymbol;
                final OCSymbol ocSymbol3 = ocSymbol2.getDelegate();
                final boolean b = processor2.process((Object)ocSymbol3);
                if (!b) {
                    return false;
                }
                continue;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public boolean processSymbols(@NotNull final Processor<OCSymbol> processor, @Nullable final String s, @NotNull final Set<FileSymbolTable> set, @Nullable final Map<OCSymbol, VirtualFile> map, @Nullable final VirtualFile virtualFile, @NotNull final OCInclusionContext ocInclusionContext, @Nullable final VirtualFile virtualFile2) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "processSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processedTables", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "processSymbols"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "processSymbols"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (this.isEmpty()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!set.add(this)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return this.processFile((Processor<OCSymbol>)new FileSymbolTable.1Builder(ocInclusionContext.getNameScope(), ocInclusionContext, FileSymbolTablesCache.getInstance(this.myProject), set, virtualFile2, virtualFile, processor, s, map));
    }
    
    public boolean isEmpty() {
        return this.myContents.isEmpty();
    }
    
    public void append(final OCSymbol ocSymbol) {
        this.myContents.add(ocSymbol);
    }
    
    public void appendAll(@NotNull final List<OCSymbol> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbols", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable", "appendAll"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myContents.addAll(list);
    }
    
    public void sortSymbols() {
        a(this.myContents);
    }
    
    private static void a(@Nullable final List<OCSymbol> list) {
        if (list != null) {
            Collections.sort((List<Object>)list, (ocSymbol, ocSymbol2) -> OCSymbolOffsetUtil.compare(ocSymbol.getComplexOffset(), ocSymbol2.getComplexOffset()));
            for (final OCSymbol ocSymbol3 : list) {
                try {
                    if (!(ocSymbol3 instanceof OCNamespaceSymbol)) {
                        continue;
                    }
                    a(((OCNamespaceSymbol)ocSymbol3).getMembersList());
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
        }
    }
    
    public int incUsageCount() {
        return ++this.myUsingCount;
    }
    
    public int getUsageCount() {
        return this.myUsingCount;
    }
    
    public void resetUsageCount() {
        this.myUsingCount = 1;
    }
    
    public int getPackStamp() {
        return this.myPackStamp;
    }
    
    public void setPackStamp(final int myPackStamp) {
        this.myPackStamp = myPackStamp;
    }
    
    static {
        DEBUG_STATS_KEY = Key.create("FileSymbolTable.STATS_KEY");
        LOG = Logger.getInstance((Class)FileSymbolTable.class);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
