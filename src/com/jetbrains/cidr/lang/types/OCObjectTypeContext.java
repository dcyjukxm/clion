// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.psi.OCClassDeclarationBase;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.search.OCMemberInheritorsSearch;
import java.util.List;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;

public class OCObjectTypeContext
{
    private StaticMode myStaticMode;
    private OCObjectType myType;
    private OCType myOriginalType;
    
    public OCObjectTypeContext(final StaticMode myStaticMode, final OCObjectType myType, final OCType myOriginalType) {
        this.myStaticMode = myStaticMode;
        this.myType = myType;
        this.myOriginalType = myOriginalType;
    }
    
    public OCObjectTypeContext(final boolean b, final boolean b2, final OCObjectType ocObjectType, final OCType ocType) {
        this(b2 ? StaticMode.NO_MATTER : (b ? StaticMode.STATIC : StaticMode.INSTANCE), ocObjectType, ocType);
    }
    
    public StaticMode getStaticMode() {
        return this.myStaticMode;
    }
    
    public boolean fitsStaticness(final OCMemberSymbol ocMemberSymbol) {
        return this.myStaticMode.fitsStaticness(ocMemberSymbol);
    }
    
    public void setStaticDoesntMatter() {
        this.myStaticMode = StaticMode.NO_MATTER;
    }
    
    public OCObjectType getType() {
        return this.myType;
    }
    
    public OCType getOriginalType() {
        return this.myOriginalType;
    }
    
    @Nullable
    public OCMethodSymbol getKnownResponder(final String s, final boolean b) {
        final CommonProcessors.FindFirstProcessor<OCMethodSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCMethodSymbol>() {
            protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
                return OCObjectTypeContext.this.fitsStaticness(ocMethodSymbol);
            }
        };
        try {
            if (b) {
                this.myType.processMembers(s, OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)findFirstProcessor, true, false);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (!findFirstProcessor.isFound()) {
                this.myType.processMembers(s, OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)findFirstProcessor, false, true);
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return (OCMethodSymbol)findFirstProcessor.getFoundValue();
    }
    
    private static List<OCMethodSymbol> a(final OCObjectTypeContext ocObjectTypeContext, final String s, final Project project) {
        final OCMemberInheritorsSearch.SearchParameters<OCMemberSymbol> parameters = OCMemberInheritorsSearch.getParameters(s, ocObjectTypeContext.getType().getClassSymbol(), project, (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, StaticMode.NO_MATTER);
        parameters.setIncludeSelfImplementation(true);
        parameters.setInterfacesThenImplementations(true);
        parameters.setIncludeFromID(true);
        parameters.setInheritors(true);
        parameters.setAncestors(true);
        final ArrayList list = new ArrayList<Object>(OCMemberInheritorsSearch.search(parameters).findAll());
        Collections.sort((List<E>)list, (ocSymbolWithParent, ocSymbolWithParent2) -> ocSymbolWithParent.getParent().compareTo(ocSymbolWithParent2.getParent()));
        return (List<OCMethodSymbol>)list;
    }
    
    @NotNull
    public OCSendMessageExpression.ProbableResponders getProbableResponders(final String s, final Project project) {
        final OCMethodSymbol knownResponder = this.getKnownResponder(s, true);
        List<OCMethodSymbol> list = null;
        Label_0028: {
            try {
                if (knownResponder == null) {
                    list = a(this, s, project);
                    break Label_0028;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            list = Collections.singletonList(knownResponder);
        }
        final List<OCMethodSymbol> list2 = list;
        final ArrayList<OCMethodSymbol> list3 = new ArrayList<OCMethodSymbol>();
        for (final OCMethodSymbol ocMethodSymbol : list2) {
            try {
                if (!this.fitsStaticness(ocMethodSymbol)) {
                    continue;
                }
                list3.add(ocMethodSymbol);
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        OCSendMessageExpression.ProbableResponders probableResponders;
        try {
            probableResponders = new OCSendMessageExpression.ProbableResponders(list2, list3, knownResponder, this);
            if (probableResponders == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCObjectTypeContext", "getProbableResponders"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return probableResponders;
    }
    
    @Nullable
    public static OCMethodSymbol findSimilarResponder(@Nullable final OCObjectType ocObjectType, final List<String> list, final List<OCType> list2, final PsiFile psiFile) {
        Label_0035: {
            try {
                if (list.size() != 1) {
                    break Label_0035;
                }
                final List<String> list3 = list;
                final int n = 0;
                final String s = list3.get(n);
                final String s2 = s;
                final String s3 = ":";
                final boolean b = s2.endsWith(s3);
                if (!b) {
                    break Label_0035;
                }
                break Label_0035;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final List<String> list3 = list;
                final int n = 0;
                final String s = list3.get(n);
                final String s2 = s;
                final String s3 = ":";
                final boolean b = s2.endsWith(s3);
                if (!b) {
                    return null;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        for (int i = list2.size() - 1; i >= 0; --i) {
            final int n2 = i;
            final StringBuilder sb = new StringBuilder();
            if (i > 0) {
                int j = 0;
                try {
                    while (j < n2) {
                        sb.append(list.get(j));
                        ++j;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            else {
                final String s4 = list.get(0);
                try {
                    if (s4.isEmpty()) {
                        return null;
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                sb.append(s4.substring(0, s4.length() - 1));
            }
            final String string = sb.toString();
            final CommonProcessors.FindFirstProcessor<OCMethodSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCMethodSymbol>() {
                protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
                    if (ocMethodSymbol.isVararg()) {
                        return false;
                    }
                    final List<OCMethodSymbol.SelectorPartSymbol> selectors = ocMethodSymbol.getSelectors();
                    if (selectors.size() < n2) {
                        return false;
                    }
                    for (int i = 0; i < n2; ++i) {
                        final OCType ocType = list2.get(i);
                        final OCDeclaratorSymbol parameter = selectors.get(i).getParameter();
                        if (ocType != null && parameter != null && !parameter.getType().resolve(psiFile).isCompatible(ocType, (PsiElement)psiFile)) {
                            return false;
                        }
                    }
                    return true;
                }
            };
            Label_0226: {
                try {
                    if (ocObjectType != null) {
                        ocObjectType.processMembers(string, OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)findFirstProcessor, true, true);
                        break Label_0226;
                    }
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
                OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(psiFile.getProject(), (Processor<OCSymbol>)new OCCommonProcessors.TypeFilteredProcessor((com.intellij.util.Processor<Object>)findFirstProcessor, OCMethodSymbol.class), string);
                try {
                    if (findFirstProcessor.isFound()) {
                        return (OCMethodSymbol)findFirstProcessor.getFoundValue();
                    }
                }
                catch (IllegalStateException ex6) {
                    throw a(ex6);
                }
            }
        }
        return null;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    public enum StaticMode
    {
        STATIC, 
        INSTANCE, 
        NO_MATTER;
        
        public boolean fitsStaticness(final OCMemberSymbol ocMemberSymbol) {
            if (ocMemberSymbol == null) {
                return false;
            }
            switch (this) {
                case STATIC: {
                    return ocMemberSymbol.isStatic() || ((OCSymbolWithParent<T, OCClassSymbol>)ocMemberSymbol).getParent().getName().equals("NSObject");
                }
                case INSTANCE: {
                    return !ocMemberSymbol.isStatic();
                }
                case NO_MATTER: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
    }
}
