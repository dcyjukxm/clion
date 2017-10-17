// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.Condition;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;

public class OCSynthesizeSymbol extends OCMemberSymbolImpl
{
    private boolean mySynthesize;
    private boolean myStatic;
    private String myIvarName;
    
    public OCSynthesizeSymbol() {
    }
    
    public OCSynthesizeSymbol(final Project project, final VirtualFile virtualFile, final long n, final String s, @NotNull final List<String> list, final OCClassSymbol ocClassSymbol, final boolean mySynthesize, final boolean myStatic, final String myIvarName) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/objc/OCSynthesizeSymbol", "<init>"));
        }
        super(project, virtualFile, n, s, list, ocClassSymbol);
        this.mySynthesize = mySynthesize;
        this.myStatic = myStatic;
        this.myIvarName = myIvarName;
    }
    
    public boolean isSynthesize() {
        return this.mySynthesize;
    }
    
    @Override
    public boolean isStatic() {
        return this.myStatic;
    }
    
    public String getIvarName() {
        try {
            if (this.myIvarName != null) {
                return this.myIvarName;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return this.myName;
    }
    
    public boolean hasIvar() {
        try {
            if (this.myIvarName != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return false;
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind synthesize;
        try {
            synthesize = OCSymbolKind.SYNTHESIZE;
            if (synthesize == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCSynthesizeSymbol", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return synthesize;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/objc/OCSynthesizeSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/objc/OCSynthesizeSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/objc/OCSynthesizeSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        final OCSynthesizeSymbol ocSynthesizeSymbol = (OCSynthesizeSymbol)o;
        final OCSynthesizeSymbol ocSynthesizeSymbol2 = (OCSynthesizeSymbol)o2;
        try {
            if (ocSynthesizeSymbol.mySynthesize != ocSynthesizeSymbol2.mySynthesize) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw c(ex5);
        }
        try {
            if (ocSynthesizeSymbol.myStatic != ocSynthesizeSymbol2.myStatic) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw c(ex6);
        }
        try {
            if (!Comparing.equal(ocSynthesizeSymbol.myIvarName, ocSynthesizeSymbol2.myIvarName)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw c(ex7);
        }
        return true;
    }
    
    @Nullable
    public OCPropertySymbol getAssociatedProperty() {
        final OCInterfaceSymbol interface1 = this.getParent().getInterface();
        try {
            if (interface1 == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final String name = this.getName();
        final CommonProcessors.FindFirstProcessor<OCPropertySymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCPropertySymbol>() {
            protected boolean accept(final OCPropertySymbol ocPropertySymbol) {
                return Comparing.equal(name, ocPropertySymbol.getName());
            }
        };
        try {
            interface1.processMembers(name, (Class<? extends OCMemberSymbol>)OCPropertySymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor);
            if (!findFirstProcessor.isFound()) {
                interface1.processMembers("", name, (Class<? extends OCMemberSymbol>)OCPropertySymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor, true);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return (OCPropertySymbol)findFirstProcessor.getFoundValue();
    }
    
    @Nullable
    public OCInstanceVariableSymbol getIvarSymbol() {
        final OCClassSymbol parent = this.getParent();
        final CommonProcessors.FindFirstProcessor<OCInstanceVariableSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCInstanceVariableSymbol>() {
            protected boolean accept(final OCInstanceVariableSymbol ocInstanceVariableSymbol) {
                return Comparing.equal(OCSynthesizeSymbol.this.getIvarName(), ocInstanceVariableSymbol.getName()) && !ocInstanceVariableSymbol.isClang4ImplicitIvar();
            }
        };
        final OCCommonProcessors.OrderedProcessor orderedProcessor = new OCCommonProcessors.OrderedProcessor<Object>((com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor, true, (com.intellij.openapi.util.Condition<? super OCMemberSymbol>[])new Condition[] { ocInstanceVariableSymbol -> {
                try {
                    if (ocInstanceVariableSymbol.getGeneratedFromProperty() == null) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                return false;
            }, Conditions.alwaysTrue() });
        final OCInterfaceSymbol interface1 = parent.getInterface();
        try {
            if (interface1 != null) {
                interface1.processMembersInAllCategories(this.getIvarName(), (Class<? extends OCMemberSymbol>)OCInstanceVariableSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)orderedProcessor);
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        parent.processMembers(this.getIvarName(), (Class<? extends OCMemberSymbol>)OCInstanceVariableSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)orderedProcessor);
        orderedProcessor.finish();
        return (OCInstanceVariableSymbol)findFirstProcessor.getFoundValue();
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
