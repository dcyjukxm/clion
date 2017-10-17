// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import java.util.Iterator;
import java.util.Set;
import java.util.Map;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCLanguageKind;
import gnu.trove.THashMap;
import java.util.List;
import gnu.trove.THashSet;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;

public class OCContextSignatureBuilder implements OCInclusionContext.SignatureBuilder
{
    public static final int START_TEMP_CAPACITY = 512;
    private final THashSet<String> myDefined;
    private final THashSet<String> myNotDefined;
    private final List<String> myPendingDefined;
    private final List<String> myPendingNotDefined;
    private final THashMap<String, Integer> myDefinitions;
    private final List<String> myPendingDefinitions;
    private final List<Integer> myPendingDefinitionHashes;
    @Nullable
    public final OCLanguageKind languageKind;
    private boolean myInConformMode;
    
    public OCContextSignatureBuilder(@Nullable final OCLanguageKind languageKind) {
        this.myDefined = (THashSet<String>)new THashSet();
        this.myNotDefined = (THashSet<String>)new THashSet();
        this.myPendingDefined = new ArrayList<String>(512);
        this.myPendingNotDefined = new ArrayList<String>(512);
        this.myDefinitions = (THashMap<String, Integer>)new THashMap();
        this.myPendingDefinitions = new ArrayList<String>(512);
        this.myPendingDefinitionHashes = new ArrayList<Integer>(512);
        this.languageKind = languageKind;
    }
    
    @Override
    public void setDefined(@NotNull final String s, final boolean b) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/symtable/OCContextSignatureBuilder", "setDefined"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String intern = OCNamesInternary.intern(s);
        Label_0099: {
            Label_0067: {
                try {
                    if (!this.myInConformMode) {
                        break Label_0099;
                    }
                    final boolean b2 = b;
                    if (b2) {
                        break Label_0067;
                    }
                    break Label_0067;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final boolean b2 = b;
                    if (b2) {
                        this.myPendingDefined.add(intern);
                        return;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            this.myPendingNotDefined.add(intern);
            return;
            try {
                if (b) {
                    this.myDefined.add((Object)intern);
                    return;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        this.myNotDefined.add((Object)intern);
    }
    
    @Override
    public void setDefinition(@NotNull final String s, @NotNull final OCMacroSymbol ocMacroSymbol) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/symtable/OCContextSignatureBuilder", "setDefinition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocMacroSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/symbols/symtable/OCContextSignatureBuilder", "setDefinition"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final String intern = OCNamesInternary.intern(s);
        final int substitutionHash = ocMacroSymbol.getSubstitutionHash();
        try {
            if (this.myInConformMode) {
                this.myPendingDefinitions.add(intern);
                this.myPendingDefinitionHashes.add(substitutionHash);
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        this.myDefinitions.put((Object)intern, (Object)substitutionHash);
    }
    
    @Override
    public void enterConformanceCheckMode() {
        this.a();
        this.myInConformMode = true;
    }
    
    @Override
    public void exitConformanceCheckMode(final boolean b) {
        if (b) {
            this.myDefined.addAll((Collection)this.myPendingDefined);
            this.myNotDefined.addAll((Collection)this.myPendingNotDefined);
            int i = 0;
            try {
                while (i < this.myPendingDefinitions.size()) {
                    this.myDefinitions.put((Object)this.myPendingDefinitions.get(i), (Object)this.myPendingDefinitionHashes.get(i));
                    ++i;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        this.a();
        this.myInConformMode = false;
    }
    
    private void a() {
        this.myPendingDefined.clear();
        this.myPendingNotDefined.clear();
        this.myPendingDefinitions.clear();
        this.myPendingDefinitionHashes.clear();
    }
    
    @NotNull
    public ContextSignature build() {
        for (final String s : this.myDefined) {
            try {
                if (this.myDefinitions.containsKey((Object)s)) {
                    continue;
                }
                this.myDefinitions.put((Object)s, (Object)null);
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        ContextSignature contextSignature;
        try {
            this.myDefined.clear();
            contextSignature = new ContextSignature(this.languageKind, (Map<String, Integer>)this.myDefinitions, (Set<String>)this.myNotDefined);
            if (contextSignature == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/OCContextSignatureBuilder", "build"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return contextSignature;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
