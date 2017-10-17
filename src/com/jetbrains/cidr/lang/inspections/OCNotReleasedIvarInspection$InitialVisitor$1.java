// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.dfa.OCControlFlowGraph;
import com.jetbrains.cidr.lang.dfa.OCNotReleasedVariablesChecker;

class OCNotReleasedIvarInspection$InitialVisitor$1 extends OCNotReleasedVariablesChecker {
    @Override
    protected void handleAssignedIvar(@NotNull final Pair<OCInstanceVariableSymbol, PsiElement> pair) {
        try {
            if (pair == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pair", "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$InitialVisitor$1", "handleAssignedIvar"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        InitialVisitor.access$200(InitialVisitor.this).myLocalRetainedIvars.add(pair);
    }
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
}