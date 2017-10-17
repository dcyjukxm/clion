// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterTypeSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceAliasSymbol;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.symtable.OCFileSymbols;
import com.jetbrains.cidr.lang.symbols.cpp.OCUsingSymbol;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Set;
import com.intellij.util.Processor;

public static class UsingAndTypedefSymbolsResolver implements Processor<OCSymbol>
{
    private final Set<OCSymbol> myProcessedSymbols;
    private final ArrayList<OCSymbol> myAnswer;
    @NotNull
    private OCResolveContext myMemoization;
    private final boolean myProcessTypdefs;
    private final boolean myProcessTypeParameters;
    private final boolean myOnlyTypes;
    private List<OCTypeArgument> myArguments;
    
    public UsingAndTypedefSymbolsResolver(final boolean myProcessTypdefs, final boolean myProcessTypeParameters, final boolean myOnlyTypes, @Nullable final List<OCTypeArgument> myArguments, @NotNull final OCResolveContext myMemoization) {
        if (myMemoization == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$UsingAndTypedefSymbolsResolver", "<init>"));
        }
        this.myProcessedSymbols = (Set<OCSymbol>)OCTypeUtils.newSymbolWithSubstitutionSet();
        this.myAnswer = new ArrayList<OCSymbol>();
        this.myProcessTypdefs = myProcessTypdefs;
        this.myProcessTypeParameters = myProcessTypeParameters;
        this.myOnlyTypes = myOnlyTypes;
        this.myMemoization = myMemoization;
        this.myArguments = myArguments;
    }
    
    public boolean process(final OCSymbol ocSymbol) {
        if (this.myProcessedSymbols.add(ocSymbol)) {
            final OCFile ocFile = (OCFile)this.myMemoization.getFile();
            try {
                if (ocFile == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            if (ocSymbol instanceof OCUsingSymbol) {
                OCFileSymbols.markImportNeeded(ocFile, ocSymbol);
                OCSymbolReference ocSymbolReference = ((OCUsingSymbol)ocSymbol).getSymbolReference();
                if (this.myArguments != null) {
                    ocSymbolReference = ocSymbolReference.applyTypeArguments(this.myArguments);
                }
                final OCResolveContext substitute = this.myMemoization.substitute(((OCUsingSymbol)ocSymbol).getSubstitution());
                ContainerUtil.process((Iterable)ocSymbolReference.resolveTemplateSpecialization(substitute, substitute.doResolveToSymbols(ocSymbolReference, false, this.myOnlyTypes)), (Processor)this);
            }
            else {
                Label_0308: {
                    Label_0223: {
                        Label_0168: {
                            Label_0145: {
                                try {
                                    if (!ocSymbol.getKind().isTypedefOrAlias()) {
                                        break Label_0168;
                                    }
                                    final UsingAndTypedefSymbolsResolver usingAndTypedefSymbolsResolver = this;
                                    final boolean b = usingAndTypedefSymbolsResolver.myProcessTypdefs;
                                    if (b) {
                                        break Label_0145;
                                    }
                                    break Label_0168;
                                }
                                catch (IllegalArgumentException ex2) {
                                    throw a(ex2);
                                }
                                try {
                                    final UsingAndTypedefSymbolsResolver usingAndTypedefSymbolsResolver = this;
                                    final boolean b = usingAndTypedefSymbolsResolver.myProcessTypdefs;
                                    if (b) {
                                        OCFileSymbols.markImportNeeded(ocFile, ocSymbol);
                                        this.processType(ocSymbol, ocSymbol.getType());
                                        return true;
                                    }
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw a(ex3);
                                }
                            }
                            try {
                                if (!(ocSymbol instanceof OCNamespaceAliasSymbol) || !this.myProcessTypdefs) {
                                    break Label_0223;
                                }
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                        }
                        OCFileSymbols.markImportNeeded(ocFile, ocSymbol);
                        ContainerUtil.process((List)this.myMemoization.doResolveToSymbols(((OCNamespaceAliasSymbol)ocSymbol).getNamespaceReference(), false, this.myOnlyTypes), (Processor)this);
                        return true;
                        try {
                            if (!(ocSymbol instanceof OCTypeParameterSymbol) || !this.myProcessTypeParameters) {
                                break Label_0308;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                    }
                    final OCTypeArgument substitution = this.myMemoization.getSubstitution().getSubstitutionFor((OCTypeParameterSymbol)ocSymbol);
                    Label_0280: {
                        try {
                            if (!(ocSymbol instanceof OCTypeParameterTypeSymbol)) {
                                break Label_0280;
                            }
                            final OCTypeArgument ocTypeArgument = substitution;
                            final boolean b2 = ocTypeArgument instanceof OCType;
                            if (b2) {
                                break Label_0280;
                            }
                            break Label_0280;
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                        try {
                            final OCTypeArgument ocTypeArgument = substitution;
                            final boolean b2 = ocTypeArgument instanceof OCType;
                            if (b2) {
                                this.processType(ocSymbol, (OCType)substitution);
                                return true;
                            }
                        }
                        catch (IllegalArgumentException ex7) {
                            throw a(ex7);
                        }
                    }
                    this.myAnswer.add(ocSymbol);
                    return true;
                }
                this.myAnswer.add(ocSymbol);
            }
        }
        return true;
    }
    
    protected void processType(final OCSymbol ocSymbol, final OCType ocType) {
        try {
            if (ocType instanceof OCStructType) {
                this.myAnswer.addAll(((OCStructType)ocType).getStructs());
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (ocType instanceof OCReferenceType) {
            final OCSymbolReference reference = ((OCReferenceType)ocType).getReference(this.myMemoization);
            final OCResolveContext myMemoization = this.myMemoization;
            try {
                if (ocSymbol instanceof OCSymbolWithSubstitution) {
                    this.myMemoization = this.myMemoization.substitute(((OCSymbolWithSubstitution)ocSymbol).getSubstitution());
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final Collection<OCSymbol> resolveTemplateSpecialization = reference.resolveTemplateSpecialization(this.myMemoization, this.myMemoization.doResolveToSymbols(reference, false, this.myOnlyTypes));
            final List<OCTypeArgument> myArguments = this.myArguments;
            Label_0145: {
                try {
                    if (this.myArguments != null) {
                        break Label_0145;
                    }
                    final OCSymbolReference ocSymbolReference = reference;
                    final OCQualifiedName ocQualifiedName = ocSymbolReference.getQualifiedName();
                    final boolean b = ocQualifiedName instanceof OCQualifiedNameWithArguments;
                    if (b) {
                        break Label_0145;
                    }
                    break Label_0145;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCSymbolReference ocSymbolReference = reference;
                    final OCQualifiedName ocQualifiedName = ocSymbolReference.getQualifiedName();
                    final boolean b = ocQualifiedName instanceof OCQualifiedNameWithArguments;
                    if (b) {
                        this.myArguments = ((OCQualifiedNameWithArguments)reference.getQualifiedName()).getArguments();
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            ContainerUtil.process((Iterable)resolveTemplateSpecialization, (Processor)this);
            this.myArguments = myArguments;
            this.myMemoization = myMemoization;
        }
        else if (ocType instanceof OCAutoType) {
            final OCExpressionSymbol expressionSymbol = ((OCAutoType)ocType).getExpressionSymbol();
            try {
                if (expressionSymbol != null) {
                    this.processType(expressionSymbol, expressionSymbol.getResolvedType(this.myMemoization.substitute(((OCAutoType)ocType).getSubstitution())));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        else {
            try {
                if (ocType instanceof OCMagicType) {
                    this.myAnswer.add(new OCTypeParameterTypeSymbol(null, null, 0L, ocSymbol.getName(), null, Collections.emptyList(), null, false));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
    }
    
    @NotNull
    public List<OCSymbol> getAnswer() {
        ArrayList<OCSymbol> myAnswer = null;
        Label_0059: {
            List<OCSymbol> list = null;
            Label_0024: {
                try {
                    if (this.myAnswer.size() != 0) {
                        break Label_0059;
                    }
                    list = Collections.emptyList();
                    if (list == null) {
                        break Label_0024;
                    }
                    return (List<OCSymbol>)list;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    list = Collections.emptyList();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$UsingAndTypedefSymbolsResolver", "getAnswer"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return (List<OCSymbol>)list;
            try {
                this.myAnswer.trimToSize();
                myAnswer = this.myAnswer;
                if (myAnswer == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$UsingAndTypedefSymbolsResolver", "getAnswer"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return myAnswer;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
