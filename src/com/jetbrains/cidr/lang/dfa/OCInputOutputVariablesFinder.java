// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.intellij.psi.search.searches.ReferencesSearch;
import java.util.ArrayList;
import com.intellij.psi.PsiReference;
import java.util.List;
import java.util.Iterator;
import com.intellij.util.containers.MultiMap;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.TextRange;

class OCInputOutputVariablesFinder extends OCSingleSymbolAlgorithm
{
    private TextRange mySelection;
    private boolean myInput;
    
    public OCInputOutputVariablesFinder(@NotNull final OCControlFlowGraph ocControlFlowGraph, @NotNull final OCSymbol ocSymbol, @Nullable final TextRange mySelection) {
        if (ocControlFlowGraph == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cfg", "com/jetbrains/cidr/lang/dfa/OCInputOutputVariablesFinder", "<init>"));
        }
        if (ocSymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCInputOutputVariablesFinder", "<init>"));
        }
        super(ocControlFlowGraph, false, ocSymbol);
        this.mySelection = mySelection;
    }
    
    private boolean a(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCInputOutputVariablesFinder", "isSelected"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        PsiElement psiElement = ocInstruction.getRValue();
        if (psiElement == null) {
            psiElement = ocInstruction.getLValue();
        }
        try {
            if (psiElement == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (ocInstruction.getKind() != OCInstruction.InstructionKind.WRITE || !(psiElement instanceof OCExpression)) {
                return this.mySelection.contains(OCElementUtil.getRangeWithMacros(psiElement));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        psiElement = psiElement.getParent();
        return this.mySelection.contains(OCElementUtil.getRangeWithMacros(psiElement));
    }
    
    @Override
    protected boolean isStartInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCInputOutputVariablesFinder", "isStartInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            switch (ocInstruction.getKind()) {
                case READ:
                case READ_IN_BLOCK:
                case REFERENCE: {
                    if (this.a(ocInstruction)) {
                        break;
                    }
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        final OCInstruction declaratorInstruction = this.myCfg.getDeclaratorInstruction(ocInstruction.getSymbol());
        Label_0126: {
            try {
                if (declaratorInstruction == null) {
                    return false;
                }
                final OCInputOutputVariablesFinder ocInputOutputVariablesFinder = this;
                final OCInstruction ocInstruction2 = declaratorInstruction;
                final boolean b = ocInputOutputVariablesFinder.a(ocInstruction2);
                if (!b) {
                    break Label_0126;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
            try {
                final OCInputOutputVariablesFinder ocInputOutputVariablesFinder = this;
                final OCInstruction ocInstruction2 = declaratorInstruction;
                final boolean b = ocInputOutputVariablesFinder.a(ocInstruction2);
                if (!b) {
                    this.myInput = true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
        }
        return false;
    }
    
    @Override
    protected boolean isEndInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/OCInputOutputVariablesFinder", "isEndInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        Label_0102: {
            try {
                if (!this.a(ocInstruction)) {
                    return false;
                }
                final int[] array = OCInputOutputVariablesFinder.OCInputOutputVariablesFinder$1.$SwitchMap$com$jetbrains$cidr$lang$dfa$OCInstruction$InstructionKind;
                final OCInstruction ocInstruction2 = ocInstruction;
                final OCInstruction.InstructionKind instructionKind = ocInstruction2.getKind();
                final int n = instructionKind.ordinal();
                final int n2 = array[n];
                switch (n2) {
                    case 3:
                    case 4:
                    case 5: {
                        return true;
                    }
                    case 6: {
                        break Label_0102;
                    }
                    default: {
                        return false;
                    }
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            try {
                final int[] array = OCInputOutputVariablesFinder.OCInputOutputVariablesFinder$1.$SwitchMap$com$jetbrains$cidr$lang$dfa$OCInstruction$InstructionKind;
                final OCInstruction ocInstruction2 = ocInstruction;
                final OCInstruction.InstructionKind instructionKind = ocInstruction2.getKind();
                final int n = instructionKind.ordinal();
                final int n2 = array[n];
                switch (n2) {
                    case 3:
                    case 4:
                    case 5: {
                        return true;
                    }
                    case 6: {
                        break;
                    }
                    default: {
                        return false;
                    }
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        final PsiElement rValue = ocInstruction.getRValue();
        Label_0136: {
            try {
                if (!(rValue instanceof OCDeclarator)) {
                    return false;
                }
                final OCDeclarator ocDeclarator = (OCDeclarator)rValue;
                final OCDeclarator ocDeclarator2 = ocDeclarator;
                final OCType ocType = ocDeclarator2.getResolvedType();
                final boolean b = ocType instanceof OCStructType;
                if (b) {
                    break Label_0136;
                }
                return false;
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
            try {
                final OCDeclarator ocDeclarator = (OCDeclarator)rValue;
                final OCDeclarator ocDeclarator2 = ocDeclarator;
                final OCType ocType = ocDeclarator2.getResolvedType();
                final boolean b = ocType instanceof OCStructType;
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw c(ex5);
            }
        }
        return false;
    }
    
    public boolean isInputVariable() {
        return this.myInput;
    }
    
    public boolean isOutputVariable() {
        try {
            if (!this.getReachableElements().isEmpty()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return false;
    }
    
    public boolean isWrittenVariable() {
        final MultiMap<OCInstruction.InstructionKind, OCInstruction> instructions = this.myCfg.getInstructions(this.mySymbol);
        try {
            if (instructions == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        for (final OCInstruction ocInstruction : instructions.values()) {
            try {
                if (this.isEndInstruction(ocInstruction)) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    public boolean isEscapedDeclarator() {
        try {
            if (!this.mySelection.contains(this.mySymbol.getOffset())) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final MultiMap<OCInstruction.InstructionKind, OCInstruction> instructions = this.myCfg.getInstructions(this.mySymbol);
        try {
            if (instructions == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        for (final OCInstruction ocInstruction : instructions.values()) {
            try {
                if (!this.a(ocInstruction)) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return false;
    }
    
    @Nullable
    public List<PsiReference> getVariableUsages() {
        final PsiElement locateDefinition = this.mySymbol.locateDefinition();
        try {
            if (locateDefinition == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final ArrayList<PsiReference> list = new ArrayList<PsiReference>();
        for (final PsiReference psiReference : ReferencesSearch.search(locateDefinition, locateDefinition.getUseScope())) {
            try {
                if (!this.mySelection.contains(psiReference.getElement().getTextRange())) {
                    continue;
                }
                list.add(psiReference);
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return list;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
