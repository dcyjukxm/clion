// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.types.visitors.OCBooleanTypeVisitor;
import java.util.Collection;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCStructType;
import java.util.Collections;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCFile;
import gnu.trove.THashSet;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import java.util.HashSet;
import java.util.Stack;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterValueSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.Map;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.types.visitors.OCTypeResolveVisitor;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.util.HashMap;
import com.intellij.openapi.util.Pair;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.UserDataHolderBase;

public class OCResolveContext extends UserDataHolderBase
{
    @Nullable
    private PsiElement myElement;
    @NotNull
    private final OCTypeSubstitution mySubstitution;
    @NotNull
    private final Set<Pair<OCSymbolReference, OCTypeSubstitution>> myResolving;
    private final HashMap<OCFunctionSymbol, OCStructSymbol> myOperatorParentsCache;
    private final Set<OCTypeResolveVisitor.TypeKey> myResolvingTypes;
    private final OCExpressionEvaluator.ValueEvaluator myEvaluator;
    private Map<OCType, OCType> myAutoParamsTypeMapping;
    private Map<OCDeclaratorSymbol, OCTypeParameterValueSymbol> myAutoParamsValueMapping;
    @Nullable
    private OCResolveContext myOriginalContext;
    public int myInsideUsingNamespaceCounter;
    private int myDepth;
    private Ref<Integer> myTypeResolveCounter;
    private Stack<Integer> myUsingNamespaceIndex;
    private Stack<OCFunctionSymbol> myOuterFunctions;
    private boolean myWasCollision;
    private Set<OCTypeParameterSymbol> myTypeDependencies;
    private boolean myProcessNonImported;
    private boolean myOriginallyProcessNonImported;
    private boolean myDontExpandVariadics;
    private boolean isVariadicExpansionMode;
    private boolean myDontUseSymbolContextsInDepends;
    private boolean myInSFINAE;
    private boolean myIncompleteMode;
    
    public OCResolveContext() {
        this(null);
    }
    
    private OCResolveContext(@NotNull final OCResolveContext myOriginalContext, @NotNull final OCTypeSubstitution mySubstitution) {
        if (myOriginalContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "origin", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "<init>"));
        }
        if (mySubstitution == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitution", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "<init>"));
        }
        this.myInsideUsingNamespaceCounter = 0;
        this.myTypeResolveCounter = (Ref<Integer>)Ref.create((Object)0);
        this.myUsingNamespaceIndex = new Stack<Integer>();
        this.myOuterFunctions = new Stack<OCFunctionSymbol>();
        this.myOriginalContext = myOriginalContext;
        this.myDepth = myOriginalContext.myDepth + 1;
        this.myElement = myOriginalContext.myElement;
        this.mySubstitution = mySubstitution;
        this.myResolving = myOriginalContext.myResolving;
        this.myResolvingTypes = myOriginalContext.myResolvingTypes;
        this.myOperatorParentsCache = myOriginalContext.myOperatorParentsCache;
        this.myUsingNamespaceIndex = myOriginalContext.myUsingNamespaceIndex;
        this.myTypeResolveCounter = myOriginalContext.myTypeResolveCounter;
        this.myTypeDependencies = new HashSet<OCTypeParameterSymbol>();
        this.myEvaluator = myOriginalContext.myEvaluator;
        this.myIncompleteMode = myOriginalContext.myIncompleteMode;
    }
    
    public OCResolveContext(@Nullable final PsiElement myElement) {
        this.myInsideUsingNamespaceCounter = 0;
        this.myTypeResolveCounter = (Ref<Integer>)Ref.create((Object)0);
        this.myUsingNamespaceIndex = new Stack<Integer>();
        this.myOuterFunctions = new Stack<OCFunctionSymbol>();
        this.myUsingNamespaceIndex.push(Integer.MAX_VALUE);
        this.myElement = myElement;
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myResolving = OCTypeUtils.newReferenceWithSubstitutionSet();
        this.myResolvingTypes = (Set<OCTypeResolveVisitor.TypeKey>)new THashSet();
        this.myOperatorParentsCache = new HashMap<OCFunctionSymbol, OCStructSymbol>();
        this.myTypeDependencies = new HashSet<OCTypeParameterSymbol>();
        this.myWasCollision = false;
        this.myEvaluator = new OCExpressionEvaluator.ValueEvaluator(this);
    }
    
    public String toString() {
        String s;
        if (this.myElement instanceof OCFile) {
            s = "file " + ((OCFile)this.myElement).getName();
        }
        else if (this.myElement != null) {
            s = "element " + this.myElement.getClass().getSimpleName() + ": " + this.myElement.getText();
        }
        else {
            s = "<null>";
        }
        return "OCResolveContext(" + s + ")";
    }
    
    public List<OCSymbol> doResolveToSymbols(final OCSymbolReference ocSymbolReference, final boolean b, final boolean b2) {
        final Pair create = Pair.create((Object)ocSymbolReference, (Object)this.mySubstitution.getMinimalDependentSubstitution(ocSymbolReference, this));
        try {
            if (this.myResolving.contains(create)) {
                this.setWasCollision(true);
                return (List<OCSymbol>)Collections.emptyList();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myResolving.add((Pair<OCSymbolReference, OCTypeSubstitution>)create);
        final List<OCSymbol> resolveToSymbols = ocSymbolReference.resolveToSymbols(this, b, b2, true);
        this.myResolving.remove(create);
        return resolveToSymbols;
    }
    
    public List<OCSymbol> resolveToSymbols(final OCSymbolReference ocSymbolReference) {
        return this.resolveToSymbols(ocSymbolReference, false, false);
    }
    
    public List<OCSymbol> resolveToSymbols(final OCSymbolReference ocSymbolReference, final boolean b, final boolean b2) {
        return this.resolveToSymbols(ocSymbolReference, b, true, false, b2);
    }
    
    public List<OCSymbol> resolveToSymbols(final OCSymbolReference ocSymbolReference, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        return OCSymbolReference.lookupUsingsAndTypedefs(b, b4, this, this.doResolveToSymbols(ocSymbolReference, b3, b4), ocSymbolReference, b2);
    }
    
    public Set<OCTypeResolveVisitor.TypeKey> getResolvingTypes() {
        return this.myResolvingTypes;
    }
    
    @Nullable
    public OCStructSymbol getNonMemberOperatorParent(final OCSymbol ocSymbol) {
        if (ocSymbol instanceof OCFunctionSymbol) {
            final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
            final List<OCDeclaratorSymbol> parameterSymbols = ocFunctionSymbol.getParameterSymbols();
            Label_0043: {
                try {
                    if (!ocFunctionSymbol.isCppNonMemberOperator(this)) {
                        return null;
                    }
                    final OCResolveContext ocResolveContext = this;
                    final HashMap<OCFunctionSymbol, OCStructSymbol> hashMap = ocResolveContext.myOperatorParentsCache;
                    final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                    final boolean b = hashMap.containsKey(ocFunctionSymbol2);
                    if (b) {
                        break Label_0043;
                    }
                    break Label_0043;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCResolveContext ocResolveContext = this;
                    final HashMap<OCFunctionSymbol, OCStructSymbol> hashMap = ocResolveContext.myOperatorParentsCache;
                    final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                    final boolean b = hashMap.containsKey(ocFunctionSymbol2);
                    if (b) {
                        return this.myOperatorParentsCache.get(ocFunctionSymbol);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            OCStructSymbol symbol = null;
            boolean b2 = false;
            this.myOperatorParentsCache.put(ocFunctionSymbol, null);
            int n = 0;
            while (true) {
                OCDeclaratorSymbol ocDeclaratorSymbol = null;
                Label_0148: {
                    Label_0110: {
                        try {
                            if (n >= parameterSymbols.size()) {
                                break;
                            }
                            final OCFunctionSymbol ocFunctionSymbol3 = (OCFunctionSymbol)ocSymbol;
                            final String s = ocFunctionSymbol3.getName();
                            final String s2 = "operator<<";
                            final boolean b3 = s.equals(s2);
                            if (b3) {
                                break Label_0110;
                            }
                            break Label_0110;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final OCFunctionSymbol ocFunctionSymbol3 = (OCFunctionSymbol)ocSymbol;
                            final String s = ocFunctionSymbol3.getName();
                            final String s2 = "operator<<";
                            final boolean b3 = s.equals(s2);
                            if (b3) {
                                ocDeclaratorSymbol = parameterSymbols.get(parameterSymbols.size() - n - 1);
                                break Label_0148;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    ocDeclaratorSymbol = parameterSymbols.get(n);
                }
                final OCType terminalType = ocDeclaratorSymbol.getType().resolve(this).getTerminalType();
                if (terminalType instanceof OCStructType) {
                    symbol = ((OCStructType)terminalType).getSymbol();
                    break;
                }
                if (terminalType instanceof OCUnknownType) {
                    b2 = true;
                }
                ++n;
            }
            Label_0219: {
                try {
                    if (symbol != null) {
                        break Label_0219;
                    }
                    final boolean b4 = b2;
                    if (b4) {
                        break Label_0219;
                    }
                    break Label_0219;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    final boolean b4 = b2;
                    if (b4) {
                        this.myOperatorParentsCache.remove(ocFunctionSymbol);
                        return symbol;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            this.myOperatorParentsCache.put(ocFunctionSymbol, symbol);
            return symbol;
        }
        return null;
    }
    
    @Nullable
    public PsiFile getFile() {
        Label_0026: {
            try {
                if (this.myElement == null) {
                    break Label_0026;
                }
                final OCResolveContext ocResolveContext = this;
                final PsiElement psiElement = ocResolveContext.myElement;
                final boolean b = psiElement.isValid();
                if (!b) {
                    break Label_0026;
                }
                break Label_0026;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCResolveContext ocResolveContext = this;
                final PsiElement psiElement = ocResolveContext.myElement;
                final boolean b = psiElement.isValid();
                if (!b) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        PsiFile psiFile;
        PsiElement context;
        for (psiFile = this.myElement.getContainingFile(); psiFile instanceof OCCodeFragment; psiFile = context.getContainingFile()) {
            context = psiFile.getContext();
            try {
                if (context == null) {
                    return psiFile;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return psiFile;
    }
    
    @Nullable
    public PsiElement getElement() {
        Label_0026: {
            try {
                if (this.myElement == null) {
                    return null;
                }
                final OCResolveContext ocResolveContext = this;
                final PsiElement psiElement = ocResolveContext.myElement;
                final boolean b = psiElement.isValid();
                if (b) {
                    break Label_0026;
                }
                return null;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCResolveContext ocResolveContext = this;
                final PsiElement psiElement = ocResolveContext.myElement;
                final boolean b = psiElement.isValid();
                if (b) {
                    return this.myElement;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    @Nullable
    public Project getProject() {
        try {
            if (this.myElement != null) {
                return this.myElement.getProject();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public void setElement(@Nullable final PsiElement myElement) {
        this.myElement = myElement;
    }
    
    @NotNull
    public OCTypeSubstitution getSubstitution() {
        OCTypeSubstitution mySubstitution;
        try {
            mySubstitution = this.mySubstitution;
            if (mySubstitution == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "getSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return mySubstitution;
    }
    
    @Nullable
    public OCResolveContext getOriginalContext() {
        return this.myOriginalContext;
    }
    
    @NotNull
    public OCResolveContext substitute(@NotNull final OCTypeSubstitution ocTypeSubstitution) {
        try {
            if (ocTypeSubstitution == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitution", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "substitute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCResolveContext substitute;
        try {
            substitute = this.substitute(ocTypeSubstitution, false, false);
            if (substitute == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "substitute"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return substitute;
    }
    
    @NotNull
    public OCResolveContext substitute(@NotNull final OCTypeSubstitution ocTypeSubstitution, final boolean b, final boolean b2) {
        try {
            if (ocTypeSubstitution == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitution", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "substitute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0132: {
            try {
                if (ocTypeSubstitution == OCTypeSubstitution.ID) {
                    if (!b2) {
                        break Label_0132;
                    }
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final OCResolveContext ocResolveContext = new OCResolveContext(this, OCTypeSubstitution.compose(this.mySubstitution, ocTypeSubstitution, b, this));
            OCResolveContext ocResolveContext2;
            try {
                ocResolveContext.setProcessNonImported(this.myProcessNonImported);
                ocResolveContext2 = ocResolveContext;
                if (ocResolveContext2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "substitute"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            return ocResolveContext2;
            try {
                if (this == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "substitute"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return this;
    }
    
    @NotNull
    public OCResolveContext substituteFirst(@NotNull final OCTypeSubstitution ocTypeSubstitution) {
        try {
            if (ocTypeSubstitution == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitution", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "substituteFirst"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (ocTypeSubstitution != OCTypeSubstitution.ID) {
            final OCResolveContext ocResolveContext = new OCResolveContext(this, OCTypeSubstitution.compose(ocTypeSubstitution, this.mySubstitution, false, this));
            OCResolveContext ocResolveContext2;
            try {
                ocResolveContext.setProcessNonImported(this.myProcessNonImported);
                ocResolveContext2 = ocResolveContext;
                if (ocResolveContext2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "substituteFirst"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return ocResolveContext2;
        }
        try {
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "substituteFirst"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return this;
    }
    
    @NotNull
    public OCResolveContext useFor(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "useFor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCTypeSubstitution minimalDependentSubstitution = this.mySubstitution.getMinimalDependentSubstitution(ocSymbol, this);
        OCTypeSubstitution compose = null;
        Label_0084: {
            try {
                if (ocSymbol instanceof OCSymbolWithSubstitution) {
                    compose = OCTypeSubstitution.compose(minimalDependentSubstitution, ((OCSymbolWithSubstitution)ocSymbol).getSubstitution(), false, this);
                    break Label_0084;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            compose = minimalDependentSubstitution;
        }
        final OCResolveContext ocResolveContext = new OCResolveContext(this, compose);
        OCResolveContext ocResolveContext2;
        try {
            ocResolveContext.setProcessNonImported(this.myProcessNonImported);
            ocResolveContext2 = ocResolveContext;
            if (ocResolveContext2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "useFor"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return ocResolveContext2;
    }
    
    @NotNull
    public OCResolveContext useFor(@NotNull final OCTypeArgument ocTypeArgument, @NotNull OCTypeSubstitution minimalDependentSubstitution) {
        try {
            if (ocTypeArgument == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argument", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "useFor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (minimalDependentSubstitution == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitution", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "useFor"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        minimalDependentSubstitution = OCTypeSubstitution.compose(this.mySubstitution, minimalDependentSubstitution, false, this).getMinimalDependentSubstitution(ocTypeArgument, this);
        final OCResolveContext ocResolveContext = new OCResolveContext(this, minimalDependentSubstitution);
        OCResolveContext ocResolveContext2;
        try {
            ocResolveContext.setProcessNonImported(this.myProcessNonImported);
            ocResolveContext2 = ocResolveContext;
            if (ocResolveContext2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "useFor"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return ocResolveContext2;
    }
    
    @NotNull
    public OCResolveContext clearSubstitution() {
        final OCResolveContext ocResolveContext = new OCResolveContext(this, OCTypeSubstitution.ID);
        OCResolveContext ocResolveContext2;
        try {
            ocResolveContext.setProcessNonImported(this.myProcessNonImported);
            ocResolveContext2 = ocResolveContext;
            if (ocResolveContext2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "clearSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocResolveContext2;
    }
    
    public void startResolvingNamespaceUsing(final int n) {
        try {
            ++this.myInsideUsingNamespaceCounter;
            if (n >= 0) {
                this.myUsingNamespaceIndex.push(Math.min(n, this.myUsingNamespaceIndex.peek()));
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myUsingNamespaceIndex.push(this.myUsingNamespaceIndex.peek());
    }
    
    public void stopResolvingNamespaceUsing() {
        --this.myInsideUsingNamespaceCounter;
        this.myUsingNamespaceIndex.pop();
    }
    
    public int getNestingDepth() {
        return this.myDepth;
    }
    
    public int getCurrentUsingIndex() {
        return this.myUsingNamespaceIndex.peek();
    }
    
    public OCExpressionEvaluator.ValueEvaluator getEvaluator() {
        return new OCExpressionEvaluator.ValueEvaluator(this.myEvaluator, this);
    }
    
    public boolean isProcessNonImported() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myProcessNonImported:Z
        //     4: ifne            38
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myOriginalContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    11: ifnull          46
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myOriginalContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    25: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isProcessNonImported:()Z
        //    28: ifeq            46
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: iconst_1       
        //    39: goto            47
        //    42: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: iconst_0       
        //    47: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      31     34     38     Ljava/lang/IllegalArgumentException;
        //  21     42     42     46     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean isInSFINAE() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myInSFINAE:Z
        //     4: ifne            38
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myOriginalContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    11: ifnull          46
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myOriginalContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    25: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isInSFINAE:()Z
        //    28: ifeq            46
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: iconst_1       
        //    39: goto            47
        //    42: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: iconst_0       
        //    47: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      31     34     38     Ljava/lang/IllegalArgumentException;
        //  21     42     42     46     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean isOriginallyProcessNonImported() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myOriginallyProcessNonImported:Z
        //     4: ifne            38
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myOriginalContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    11: ifnull          46
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myOriginalContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    25: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isOriginallyProcessNonImported:()Z
        //    28: ifeq            46
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: iconst_1       
        //    39: goto            47
        //    42: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: iconst_0       
        //    47: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      31     34     38     Ljava/lang/IllegalArgumentException;
        //  21     42     42     46     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean isDontExpandVariadics() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myDontExpandVariadics:Z
        //     4: ifne            38
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myOriginalContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    11: ifnull          46
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myOriginalContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    25: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isDontExpandVariadics:()Z
        //    28: ifeq            46
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: iconst_1       
        //    39: goto            47
        //    42: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: iconst_0       
        //    47: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      31     34     38     Ljava/lang/IllegalArgumentException;
        //  21     42     42     46     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void setDontExpandVariadics(final boolean myDontExpandVariadics) {
        this.myDontExpandVariadics = myDontExpandVariadics;
    }
    
    public boolean isVariadicExpansionMode() {
        return this.isVariadicExpansionMode;
    }
    
    public void setVariadicExpansionMode(final boolean isVariadicExpansionMode) {
        this.isVariadicExpansionMode = isVariadicExpansionMode;
    }
    
    public boolean wasCollision() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myWasCollision:Z
        //     4: ifne            38
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myOriginalContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    11: ifnull          46
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myOriginalContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    25: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.wasCollision:()Z
        //    28: ifeq            46
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: iconst_1       
        //    39: goto            47
        //    42: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: iconst_0       
        //    47: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      31     34     38     Ljava/lang/IllegalArgumentException;
        //  21     42     42     46     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void addTypeDependency(@NotNull final OCTypeParameterSymbol ocTypeParameterSymbol) {
        try {
            if (ocTypeParameterSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dependency", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "addTypeDependency"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myTypeDependencies.add(ocTypeParameterSymbol);
    }
    
    public void addTypeDependencies(@NotNull final Set<OCTypeParameterSymbol> set) {
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeDependencies", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "addTypeDependencies"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myTypeDependencies.addAll(set);
    }
    
    @NotNull
    public Set<OCTypeParameterSymbol> getTypeDependencies() {
        HashSet set;
        try {
            set = new HashSet<OCTypeParameterSymbol>(this.myTypeDependencies);
            if (set == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "getTypeDependencies"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (Set<OCTypeParameterSymbol>)set;
    }
    
    @NotNull
    public Set<OCTypeParameterSymbol> getTypeDependencies(@Nullable final OCType ocType) {
        final HashSet set = new HashSet<OCTypeParameterSymbol>(this.myTypeDependencies);
        try {
            if (ocType != null) {
                ocType.accept((OCTypeVisitor<Object>)new OCBooleanTypeVisitor() {
                    @Override
                    public Boolean visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
                        set.add(ocTypeParameterType.getSymbol());
                        return true;
                    }
                });
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        HashSet set2;
        try {
            set2 = set;
            if (set2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "getTypeDependencies"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (Set<OCTypeParameterSymbol>)set2;
    }
    
    public void setTypeDependencies(@NotNull final Set<OCTypeParameterSymbol> myTypeDependencies) {
        try {
            if (myTypeDependencies == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeDependencies", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "setTypeDependencies"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myTypeDependencies = myTypeDependencies;
    }
    
    public void clearTypeDependencies() {
        this.myTypeDependencies.clear();
    }
    
    public boolean wasDependentType() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myTypeDependencies:Ljava/util/Set;
        //     4: invokeinterface java/util/Set.isEmpty:()Z
        //     9: ifeq            43
        //    12: aload_0        
        //    13: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myOriginalContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    16: ifnull          51
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload_0        
        //    27: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myOriginalContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    30: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.wasDependentType:()Z
        //    33: ifeq            51
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: iconst_1       
        //    44: goto            52
        //    47: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: iconst_0       
        //    52: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      19     22     26     Ljava/lang/IllegalArgumentException;
        //  12     36     39     43     Ljava/lang/IllegalArgumentException;
        //  26     47     47     51     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public int getTypeResolveCounter() {
        return (int)this.myTypeResolveCounter.get();
    }
    
    public void incTypeResolveCounter() {
        this.myTypeResolveCounter.set((Object)((int)this.myTypeResolveCounter.get() + 1));
    }
    
    public void setProcessNonImported(final boolean b) {
        try {
            this.myProcessNonImported = b;
            if (this.myProcessNonImported) {
                this.myOriginallyProcessNonImported = true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myOriginalContext != null) {
                this.myOriginalContext.setProcessNonImported(b);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    public void setInSFINAE(final boolean b) {
        try {
            this.myInSFINAE = b;
            if (this.myOriginalContext != null) {
                this.myOriginalContext.setInSFINAE(b);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    public void setWasCollision(final boolean b) {
        try {
            this.myWasCollision = b;
            if (this.myOriginalContext != null) {
                this.myOriginalContext.setWasCollision(b);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    public void setIncompleteMode(final boolean myIncompleteMode) {
        this.myIncompleteMode = myIncompleteMode;
    }
    
    public boolean isIncompleteMode() {
        return this.myIncompleteMode;
    }
    
    public void setDontUseSymbolContextsInDepends(final boolean myDontUseSymbolContextsInDepends) {
        this.myDontUseSymbolContextsInDepends = myDontUseSymbolContextsInDepends;
    }
    
    public boolean dontUseSymbolContextsInDepends() {
        return this.myDontUseSymbolContextsInDepends;
    }
    
    public static boolean setNonImportedFlag(@NotNull final OCResolveContext ocResolveContext, final boolean processNonImported) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "setNonImportedFlag"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final boolean processNonImported2 = ocResolveContext.isProcessNonImported();
        ocResolveContext.setProcessNonImported(processNonImported);
        return processNonImported2;
    }
    
    public static boolean setInSFINAEFlag(@NotNull final OCResolveContext ocResolveContext, final boolean inSFINAE) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "setInSFINAEFlag"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final boolean inSFINAE2 = ocResolveContext.isInSFINAE();
        ocResolveContext.setInSFINAE(inSFINAE);
        return inSFINAE2;
    }
    
    public OCTypeParameterType getAutoParamTypeMapping(final OCAutoType ocAutoType) {
        try {
            if (this.myAutoParamsTypeMapping != null) {
                return this.myAutoParamsTypeMapping.get(ocAutoType);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public void setAutoParamsTypeMapping(final Map<OCType, OCType> myAutoParamsTypeMapping) {
        this.myAutoParamsTypeMapping = myAutoParamsTypeMapping;
    }
    
    @Nullable
    public OCTypeParameterValueSymbol getAutoParamValueMapping(@NotNull final OCDeclaratorSymbol ocDeclaratorSymbol) {
        try {
            if (ocDeclaratorSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "getAutoParamValueMapping"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myAutoParamsValueMapping != null) {
                return this.myAutoParamsValueMapping.get(ocDeclaratorSymbol);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    public void setAutoParamsValueMapping(final Map<OCDeclaratorSymbol, OCTypeParameterValueSymbol> myAutoParamsValueMapping) {
        this.myAutoParamsValueMapping = myAutoParamsValueMapping;
    }
    
    public boolean isCpp() {
        try {
            if (!OCCodeInsightUtil.isInPlainOldC(this.myElement)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean isObjc() {
        return OCCodeInsightUtil.isInObjC(this.myElement);
    }
    
    public void pushOuterFunction(@NotNull final OCFunctionSymbol ocFunctionSymbol) {
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "functionSymbol", "com/jetbrains/cidr/lang/symbols/OCResolveContext", "pushOuterFunction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myOuterFunctions.push(ocFunctionSymbol);
    }
    
    public void popOuterFunction() {
        this.myOuterFunctions.pop();
    }
    
    @Nullable
    public OCFunctionSymbol peekOuterFunction() {
        try {
            if (this.myOuterFunctions.isEmpty()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myOuterFunctions.peek();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
