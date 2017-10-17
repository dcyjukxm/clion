// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import java.util.Set;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureHandler;
import com.intellij.psi.PsiElement;
import java.util.Collection;
import com.intellij.util.containers.hash.HashSet;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import com.jetbrains.cidr.lang.psi.OCMethod;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;

public class OCChangeMethodSignatureIntentionAction extends OCSymbolQuickFix<OCMethodSymbol>
{
    private List<OCType> myNewParamTypes;
    private List<String> myNewSelectors;
    private List<OCExpression> myNewExpressions;
    
    public OCChangeMethodSignatureIntentionAction(final OCMethodSymbol ocMethodSymbol, final List<OCType> myNewParamTypes, final List<String> myNewSelectors, final List<OCExpression> myNewExpressions) {
        super(ocMethodSymbol);
        this.myNewParamTypes = myNewParamTypes;
        this.myNewSelectors = myNewSelectors;
        this.myNewExpressions = myNewExpressions;
    }
    
    @Override
    protected boolean isAvailable(final OCMethodSymbol ocMethodSymbol) {
        return OCSearchScope.isInProjectSources(ocMethodSymbol);
    }
    
    @Override
    protected String getTextInternal(final OCMethodSymbol ocMethodSymbol) {
        return "Add parameters to " + ocMethodSymbol.getNameWithKindLowercase();
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Add parameters";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCChangeMethodSignatureIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected void invoke(final OCMethodSymbol ocMethodSymbol) {
        final PsiElement locateDefinition = ocMethodSymbol.locateDefinition();
        try {
            if (!(locateDefinition instanceof OCMethod)) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCChangeSignatureHandler handler = OCChangeSignatureActionHandler.getHandler((OCCallable)locateDefinition, locateDefinition, true);
        int size = 0;
        Label_0060: {
            try {
                if (OCNameSuggester.isObjCGetter(ocMethodSymbol.getName())) {
                    size = 0;
                    break Label_0060;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            size = ocMethodSymbol.getSelectors().size();
        }
        final int n = size;
        final HashSet set = new HashSet();
        for (int i = 0; i < n; ++i) {
            final OCDeclaratorSymbol parameter = ocMethodSymbol.getSelectors().get(i).getParameter();
            try {
                if (parameter != null) {
                    ((Set<String>)set).add(parameter.getName());
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        for (int j = n; j < this.myNewSelectors.size(); ++j) {
            final String s = this.myNewSelectors.get(j);
            final OCExpression ocExpression = this.myNewExpressions.get(j);
            HashSet set2 = null;
            boolean b = false;
            Label_0201: {
                try {
                    set2 = set;
                    if (j == 0) {
                        b = true;
                        break Label_0201;
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                b = false;
            }
            final String suggestForParameter = OCNameSuggester.suggestForParameter((Collection<String>)set2, b, s, this.myNewParamTypes.get(j), ocMethodSymbol, ocExpression);
            ((Set<String>)set).add(suggestForParameter);
            handler.addParameter(s, suggestForParameter, this.myNewParamTypes.get(j), -1, false);
        }
        handler.invokeSynchronously();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
