// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.psi.impl.OCLazyElementBase;
import com.intellij.openapi.util.Ref;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.tree.ASTStructure;

class OCSymbolTableProvider$1 extends ASTStructure {
    @Override
    public int getChildren(@NotNull final ASTNode astNode, @NotNull final Ref<ASTNode[]> into) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "astNode", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTableProvider$1", "getChildren"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (into == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "into", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTableProvider$1", "getChildren"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (astNode instanceof OCLazyElementBase) {
                return 0;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return super.getChildren(astNode, into);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}