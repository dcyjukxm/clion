// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.project.Project;
import java.util.ArrayList;
import gnu.trove.THashSet;
import gnu.trove.THashMap;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import java.util.Map;

public class OCContextChangeBuilderImpl implements OCContextChange, OCContextChangeBuilder
{
    private final int myOffset;
    @NotNull
    private final Map<String, OCMacroSymbol> myDefinitions;
    @NotNull
    private final Set<String> myUndefs;
    @NotNull
    private final List<OCSymbol> mySymbols;
    @NotNull
    private final Set<VirtualFile> myProcessedFiles;
    
    public OCContextChangeBuilderImpl(final int myOffset) {
        this.myDefinitions = (Map<String, OCMacroSymbol>)new THashMap();
        this.myUndefs = (Set<String>)new THashSet();
        this.mySymbols = new ArrayList<OCSymbol>();
        this.myProcessedFiles = (Set<VirtualFile>)new THashSet();
        this.myOffset = myOffset;
    }
    
    @Override
    public void define(@NotNull final String s, @NotNull final OCMacroSymbol ocMacroSymbol) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/preprocessor/OCContextChangeBuilderImpl", "define"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocMacroSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "macro", "com/jetbrains/cidr/lang/preprocessor/OCContextChangeBuilderImpl", "define"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.myUndefs.remove(s);
        this.myDefinitions.put(s, ocMacroSymbol);
    }
    
    @Override
    public void undef(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/preprocessor/OCContextChangeBuilderImpl", "undef"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myDefinitions.remove(s);
        this.myUndefs.add(s);
    }
    
    @Override
    public void addSymbol(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/preprocessor/OCContextChangeBuilderImpl", "addSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.mySymbols.add(ocSymbol);
    }
    
    @Override
    public void addProcessedFile(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/preprocessor/OCContextChangeBuilderImpl", "addProcessedFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myProcessedFiles.add(virtualFile);
    }
    
    @Override
    public int getOffset() {
        return this.myOffset;
    }
    
    @Override
    public void apply(@NotNull final Project project, @NotNull final OCInclusionContext ocInclusionContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/preprocessor/OCContextChangeBuilderImpl", "apply"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/preprocessor/OCContextChangeBuilderImpl", "apply"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Iterator<OCMacroSymbol> iterator = this.myDefinitions.values().iterator();
        while (iterator.hasNext()) {
            ocInclusionContext.define(iterator.next());
        }
        final Iterator<String> iterator2 = this.myUndefs.iterator();
        while (iterator2.hasNext()) {
            ocInclusionContext.undef(iterator2.next());
        }
        ocInclusionContext.preprocessFile(null, null, null, 1, -1, -1, this.mySymbols, null);
        final Iterator<VirtualFile> iterator3 = this.myProcessedFiles.iterator();
        while (iterator3.hasNext()) {
            ocInclusionContext.addProcessedFile(iterator3.next());
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final OCContextChangeBuilderImpl ocContextChangeBuilderImpl = this;
                final Class<? extends OCContextChangeBuilderImpl> clazz = ocContextChangeBuilderImpl.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCContextChangeBuilderImpl ocContextChangeBuilderImpl = this;
                final Class<? extends OCContextChangeBuilderImpl> clazz = ocContextChangeBuilderImpl.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final OCContextChangeBuilderImpl ocContextChangeBuilderImpl2 = (OCContextChangeBuilderImpl)o;
        try {
            if (this.myOffset != ocContextChangeBuilderImpl2.myOffset) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!this.myDefinitions.equals(ocContextChangeBuilderImpl2.myDefinitions)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!this.myProcessedFiles.equals(ocContextChangeBuilderImpl2.myProcessedFiles)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (!this.mySymbols.equals(ocContextChangeBuilderImpl2.mySymbols)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        try {
            if (!this.myUndefs.equals(ocContextChangeBuilderImpl2.myUndefs)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return 31 * (31 * (31 * (31 * this.myOffset + this.myDefinitions.hashCode()) + this.myUndefs.hashCode()) + this.mySymbols.hashCode()) + this.myProcessedFiles.hashCode();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
