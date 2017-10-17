// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.List;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCLabeledStatement;
import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;

public class OCLabelSymbol extends OCSymbolImpl<OCLabeledStatement>
{
    private TextRange myScope;
    
    public OCLabelSymbol() {
    }
    
    public OCLabelSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final int n, @Nullable final String s, final TextRange myScope, final List<String> list) {
        super(project, virtualFile, n, s, list);
        this.myScope = myScope;
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind label;
        try {
            label = OCSymbolKind.LABEL;
            if (label == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCLabelSymbol", "getKind"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return label;
    }
    
    @Override
    public TextRange getScope() {
        return this.myScope;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/cpp/OCLabelSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/cpp/OCLabelSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/cpp/OCLabelSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalStateException ex4) {
            throw b(ex4);
        }
        final OCLabelSymbol ocLabelSymbol = (OCLabelSymbol)o;
        final OCLabelSymbol ocLabelSymbol2 = (OCLabelSymbol)o2;
        try {
            if (!Comparing.equal((Object)ocLabelSymbol.myScope, (Object)ocLabelSymbol2.myScope)) {
                return false;
            }
        }
        catch (IllegalStateException ex5) {
            throw b(ex5);
        }
        return true;
    }
    
    @Override
    public boolean processSameSymbols(final Processor<OCSymbol> processor) {
        return processor.process((Object)this);
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
