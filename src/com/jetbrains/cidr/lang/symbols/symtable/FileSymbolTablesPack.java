// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.preprocessor.OCImportGraph;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import gnu.trove.THashSet;
import gnu.trove.THashMap;
import com.intellij.openapi.progress.ProgressManager;
import gnu.trove.TObjectHashingStrategy;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;
import com.intellij.util.Processor;
import java.util.Collection;
import java.util.Iterator;
import java.util.Collections;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.Nullable;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Function;

public class FileSymbolTablesPack implements Function<OCSymbol, OCSymbol>
{
    @NotNull
    private final ArrayList<FileSymbolTable> myTables;
    @Nullable
    private transient Map<OCSymbol, OCSymbol> myInternary;
    @Nullable
    private transient DeepEqual.Resolver myEqualityResolver;
    private transient volatile boolean myIsChanged;
    
    public FileSymbolTablesPack() {
        this.myTables = new ArrayList<FileSymbolTable>();
    }
    
    public boolean isEmpty() {
        return this.myTables.isEmpty();
    }
    
    public List<FileSymbolTable> tablesView() {
        return Collections.unmodifiableList((List<? extends FileSymbolTable>)this.myTables);
    }
    
    public void compactSynchronized() {
        synchronized (this.getTablesAccessLock()) {
            this.myTables.trimToSize();
            final Iterator<FileSymbolTable> iterator = this.myTables.iterator();
            while (iterator.hasNext()) {
                iterator.next().compact();
            }
            this.myInternary = null;
            this.myEqualityResolver = null;
        }
    }
    
    public void addCompactSynchronized(@NotNull final FileSymbolTable fileSymbolTable) {
        try {
            if (fileSymbolTable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "table", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesPack", "addCompactSynchronized"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        synchronized (this.getTablesAccessLock()) {
            try {
                fileSymbolTable.internSymbols((Function<OCSymbol, OCSymbol>)this);
                this.b(fileSymbolTable);
                fileSymbolTable.incUsageCount();
                c(fileSymbolTable);
            }
            finally {
                this.myEqualityResolver = null;
            }
        }
    }
    
    private void b(@NotNull final FileSymbolTable fileSymbolTable) {
        try {
            if (fileSymbolTable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "table", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesPack", "addTableInternal"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myIsChanged = true;
        this.myTables.add(fileSymbolTable);
        fileSymbolTable.setPackStamp(this.a());
    }
    
    public void removeUnusedTables() {
        synchronized (this.getTablesAccessLock()) {
            boolean b = false;
            final Iterator<FileSymbolTable> iterator = this.myTables.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getUsageCount() == 0) {
                    b = true;
                    break;
                }
            }
            if (b) {
                this.myIsChanged = true;
                final ArrayList<FileSymbolTable> list = new ArrayList<FileSymbolTable>(this.myTables);
                this.myTables.clear();
                for (final FileSymbolTable fileSymbolTable : list) {
                    try {
                        if (fileSymbolTable.getUsageCount() > 0) {
                            this.b(fileSymbolTable);
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    a(fileSymbolTable);
                }
            }
        }
    }
    
    public boolean isChanged() {
        return this.myIsChanged;
    }
    
    private int a() {
        return this.myTables.size();
    }
    
    private static void c(@NotNull final FileSymbolTable fileSymbolTable) {
        try {
            if (fileSymbolTable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "table", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesPack", "addToImportGraph"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        fileSymbolTable.processIncludes((Processor<OCIncludeSymbol>)(ocIncludeSymbol -> {
            try {
                if (fileSymbolTable == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "table", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesPack", "lambda$addToImportGraph$0"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final VirtualFile targetFile = ocIncludeSymbol.getTargetFile();
            Label_0067: {
                try {
                    if (targetFile == null) {
                        return true;
                    }
                    final VirtualFile virtualFile = targetFile;
                    final boolean b = virtualFile.isValid();
                    if (b) {
                        break Label_0067;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final VirtualFile virtualFile = targetFile;
                    final boolean b = virtualFile.isValid();
                    if (b) {
                        OCImportGraph.addHeaderIncluder(fileSymbolTable.getProject(), targetFile, fileSymbolTable.getContainingFile());
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return true;
        }));
    }
    
    private static void a(@NotNull final FileSymbolTable fileSymbolTable) {
        try {
            if (fileSymbolTable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "table", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesPack", "removeFromImportGraph"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        fileSymbolTable.processIncludes((Processor<OCIncludeSymbol>)(ocIncludeSymbol -> {
            try {
                if (fileSymbolTable == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "table", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesPack", "lambda$removeFromImportGraph$1"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final VirtualFile targetFile = ocIncludeSymbol.getTargetFile();
            try {
                if (targetFile != null) {
                    OCImportGraph.removeHeaderIncluder(fileSymbolTable.getProject(), targetFile, fileSymbolTable.getContainingFile());
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return true;
        }));
    }
    
    public void onRemove() {
        synchronized (this.myTables) {
            final Iterator<FileSymbolTable> iterator = this.myTables.iterator();
            while (iterator.hasNext()) {
                a(iterator.next());
            }
        }
    }
    
    public boolean isEmptySynchronized() {
        synchronized (this.getTablesAccessLock()) {
            return this.myTables.isEmpty();
        }
    }
    
    @NotNull
    public ArrayList<FileSymbolTable> getTablesSynchronized() {
        synchronized (this.getTablesAccessLock()) {
            final ArrayList list = new ArrayList<FileSymbolTable>(this.myTables);
            // monitorexit(this.getTablesAccessLock())
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesPack", "getTablesSynchronized"));
            }
            return (ArrayList<FileSymbolTable>)list;
        }
    }
    
    public int getTablesCountSynchronized() {
        synchronized (this.getTablesAccessLock()) {
            return this.myTables.size();
        }
    }
    
    @NotNull
    public Object getTablesAccessLock() {
        ArrayList<FileSymbolTable> myTables;
        try {
            myTables = this.myTables;
            if (myTables == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesPack", "getTablesAccessLock"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myTables;
    }
    
    public OCSymbol fun(final OCSymbol ocSymbol) {
        try {
            if (this.myEqualityResolver == null) {
                this.myEqualityResolver = DeepEqual.newResolver();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myInternary == null) {
                this.myInternary = (Map<OCSymbol, OCSymbol>)new THashMap((TObjectHashingStrategy)new TObjectHashingStrategy<OCSymbol>() {
                    public int computeHashCode(final OCSymbol ocSymbol) {
                        return ocSymbol.hashCodeExcludingOffset();
                    }
                    
                    public boolean equals(final OCSymbol ocSymbol, final OCSymbol ocSymbol2) {
                        ProgressManager.checkCanceled();
                        return FileSymbolTablesPack.this.myEqualityResolver.equalObjects(ocSymbol, (DeepEqual.Equality<Object>)ocSymbol2);
                    }
                });
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCSymbol ocSymbol2 = this.myInternary.get(ocSymbol);
        try {
            if (ocSymbol2 != null) {
                return ocSymbol2;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        ocSymbol.compact();
        this.myInternary.put(ocSymbol, ocSymbol);
        return ocSymbol;
    }
    
    public void updateOffsetsSynchronized(final int n, final int n2, final int n3) {
        final THashSet set = new THashSet((TObjectHashingStrategy)new TObjectHashingStrategy<OCSymbol>() {
            public int computeHashCode(final OCSymbol ocSymbol) {
                return System.identityHashCode(ocSymbol);
            }
            
            public boolean equals(final OCSymbol ocSymbol, final OCSymbol ocSymbol2) {
                return ocSymbol == ocSymbol2;
            }
        });
        synchronized (this.getTablesAccessLock()) {
            final Iterator<FileSymbolTable> iterator = this.myTables.iterator();
            while (iterator.hasNext()) {
                iterator.next().updateOffsets(n, n2, n3, (THashSet<OCSymbol>)set);
            }
        }
    }
    
    @Nullable
    public FileSymbolTable findConformingTable(@NotNull final OCInclusionContext ocInclusionContext, final int n, @Nullable final Ref<Integer> ref) {
        try {
            if (ocInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesPack", "findConformingTable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        synchronized (this.getTablesAccessLock()) {
            final int a = this.a();
            try {
                if (ref != null) {
                    ref.set((Object)a);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            if (a > n) {
                int i = 0;
                while (i < this.myTables.size()) {
                    final FileSymbolTable fileSymbolTable = this.myTables.get(i);
                    Label_0140: {
                        Label_0263: {
                            try {
                                if (fileSymbolTable.getPackStamp() <= n || !ocInclusionContext.checkConformanceAndFillSignatures(fileSymbolTable)) {
                                    break Label_0263;
                                }
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                            break Label_0140;
                        }
                        ++i;
                        continue;
                    }
                    final int incUsageCount = fileSymbolTable.incUsageCount();
                    if (incUsageCount < 0) {
                        final Iterator<FileSymbolTable> iterator = this.myTables.iterator();
                        while (iterator.hasNext()) {
                            iterator.next().resetUsageCount();
                        }
                    }
                    else {
                        int n2 = i - 1;
                        while (true) {
                            Label_0234: {
                                try {
                                    if (n2 < 0) {
                                        return fileSymbolTable;
                                    }
                                    final FileSymbolTablesPack fileSymbolTablesPack = this;
                                    final ArrayList<FileSymbolTable> list = fileSymbolTablesPack.myTables;
                                    final int n3 = n2;
                                    final FileSymbolTable fileSymbolTable2 = list.get(n3);
                                    final FileSymbolTable fileSymbolTable3 = fileSymbolTable2;
                                    final int n4 = fileSymbolTable3.getUsageCount();
                                    final int n5 = 2;
                                    final int n6 = n4 + n5;
                                    final int n7 = incUsageCount;
                                    if (n6 < n7) {
                                        break Label_0234;
                                    }
                                    return fileSymbolTable;
                                }
                                catch (IllegalArgumentException ex4) {
                                    throw a(ex4);
                                }
                                try {
                                    final FileSymbolTablesPack fileSymbolTablesPack = this;
                                    final ArrayList<FileSymbolTable> list = fileSymbolTablesPack.myTables;
                                    final int n3 = n2;
                                    final FileSymbolTable fileSymbolTable2 = list.get(n3);
                                    final FileSymbolTable fileSymbolTable3 = fileSymbolTable2;
                                    final int n4 = fileSymbolTable3.getUsageCount();
                                    final int n5 = 2;
                                    final int n6 = n4 + n5;
                                    final int n7 = incUsageCount;
                                    if (n6 < n7) {
                                        Collections.swap(this.myTables, n2, n2 + 1);
                                        --n2;
                                        continue;
                                    }
                                }
                                catch (IllegalArgumentException ex5) {
                                    throw a(ex5);
                                }
                            }
                            break;
                        }
                    }
                    return fileSymbolTable;
                }
            }
            return null;
        }
    }
    
    public boolean hasUsedTables() {
        synchronized (this.getTablesAccessLock()) {
            final Iterator<FileSymbolTable> iterator = this.myTables.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getUsageCount() > 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    static class FileSymbolsPackSerializer extends FieldSerializer<FileSymbolTablesPack>
    {
        public FileSymbolsPackSerializer(final Kryo kryo) {
            super(kryo, (Class)FileSymbolTablesPack.class);
        }
        
        protected FileSymbolTablesPack create(final Kryo kryo, final Input input, final Class<FileSymbolTablesPack> clazz) {
            return new FileSymbolTablesPack();
        }
        
        public FileSymbolTablesPack read(final Kryo kryo, final Input input, final Class<FileSymbolTablesPack> clazz) {
            final FileSymbolTablesPack fileSymbolTablesPack = (FileSymbolTablesPack)super.read(kryo, input, (Class)clazz);
            synchronized (fileSymbolTablesPack.getTablesAccessLock()) {
                final Iterator iterator = fileSymbolTablesPack.myTables.iterator();
                while (iterator.hasNext()) {
                    c(iterator.next());
                }
            }
            return fileSymbolTablesPack;
        }
    }
}
