// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import com.intellij.util.containers.Stack;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.expression.OCLambdaExpressionSymbol;

public static class DeclarationContext<T>
{
    private OCSymbolKind declaratorType;
    private Object parent;
    private int lambdaParameterIndex;
    private OCLambdaExpressionSymbol lambda;
    protected OCSymbolWithQualifiedName myParentSymbol;
    private OCVisibility visibility;
    protected boolean isDeclarationWithoutDeclarators;
    protected boolean isDeclarationWithoutType;
    private boolean isBaseClause;
    private boolean isTemplateValueParameter;
    private boolean isLambdaInitCapture;
    private List<String> attributes;
    private PsiElement localContext;
    protected T myKRParamterList;
    private ASTNode forCollection;
    private Stack<List<OCSymbolReference>> referencesInDeclaration;
    private List<T> myTemplateParameters;
    private boolean myIsTemplateSymbol;
    private boolean myInsideTemplateParams;
    private boolean myAssumeNonNull;
    private int myModifiers;
    
    public DeclarationContext(final OCSymbolKind declaratorType, final Object parent, final OCSymbolWithQualifiedName myParentSymbol, final OCVisibility visibility, final PsiElement localContext, final boolean myAssumeNonNull) {
        this.lambdaParameterIndex = -1;
        this.referencesInDeclaration = (Stack<List<OCSymbolReference>>)new Stack();
        this.myTemplateParameters = new ArrayList<T>();
        this.myModifiers = 0;
        this.declaratorType = declaratorType;
        this.parent = parent;
        this.myParentSymbol = myParentSymbol;
        this.visibility = visibility;
        this.localContext = localContext;
        this.myAssumeNonNull = myAssumeNonNull;
    }
    
    public DeclarationContext(final OCSymbolKind declaratorType) {
        this.lambdaParameterIndex = -1;
        this.referencesInDeclaration = (Stack<List<OCSymbolReference>>)new Stack();
        this.myTemplateParameters = new ArrayList<T>();
        this.myModifiers = 0;
        this.declaratorType = declaratorType;
    }
    
    public DeclarationContext() {
        this.lambdaParameterIndex = -1;
        this.referencesInDeclaration = (Stack<List<OCSymbolReference>>)new Stack();
        this.myTemplateParameters = new ArrayList<T>();
        this.myModifiers = 0;
    }
    
    public OCSymbolKind getDeclaratorType() {
        return this.declaratorType;
    }
    
    public void setDeclaratorType(final OCSymbolKind declaratorType) {
        this.declaratorType = declaratorType;
    }
    
    public Object getParent() {
        return this.parent;
    }
    
    public OCSymbolWithQualifiedName getParentSymbol() {
        return this.myParentSymbol;
    }
    
    public OCVisibility getVisibility() {
        try {
            if (this.isFriend()) {
                return OCVisibility.NULL;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.visibility;
    }
    
    public boolean isFriend() {
        return this.hasModifier(OCSymbolAttribute.FRIEND);
    }
    
    public boolean isVirtual() {
        return this.hasModifier(OCSymbolAttribute.VIRTUAL);
    }
    
    public boolean isConstexpr() {
        return this.hasModifier(OCSymbolAttribute.CONSTEXPR);
    }
    
    public boolean isBaseClause() {
        return this.isBaseClause;
    }
    
    public void setBaseClause(final boolean isBaseClause) {
        this.isBaseClause = isBaseClause;
    }
    
    public boolean isTemplateValueParameter() {
        return this.isTemplateValueParameter;
    }
    
    public void setTemplateValueParameter(final boolean isTemplateValueParameter) {
        this.isTemplateValueParameter = isTemplateValueParameter;
    }
    
    void addModifier(@NotNull final OCSymbolAttribute ocSymbolAttribute) {
        try {
            if (ocSymbolAttribute == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modifier", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext", "addModifier"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myModifiers |= ocSymbolAttribute.getMask();
    }
    
    int getModifiers() {
        return this.myModifiers;
    }
    
    public boolean hasModifier(@NotNull final OCSymbolAttribute ocSymbolAttribute) {
        try {
            if (ocSymbolAttribute == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modifier", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext", "hasModifier"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if ((this.myModifiers & ocSymbolAttribute.getMask()) != 0x0) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    public List<String> getAttributes() {
        return this.attributes;
    }
    
    public void addAttributes(final List<String> attributes) {
        try {
            if (this.attributes == null) {
                this.attributes = attributes;
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.attributes.addAll(attributes);
    }
    
    public PsiElement getLocalContext() {
        return this.localContext;
    }
    
    public void setLocalContext(@Nullable final PsiElement localContext) {
        this.localContext = localContext;
    }
    
    public List<T> getTemplateParameters() {
        return this.myTemplateParameters;
    }
    
    public void setDeclarationWithoutDeclarators(final boolean isDeclarationWithoutDeclarators) {
        this.isDeclarationWithoutDeclarators = isDeclarationWithoutDeclarators;
    }
    
    public void setDeclarationWithoutType(final boolean isDeclarationWithoutType) {
        this.isDeclarationWithoutType = isDeclarationWithoutType;
    }
    
    public ASTNode getForCollection() {
        return this.forCollection;
    }
    
    public void setForCollection(final ASTNode forCollection) {
        this.forCollection = forCollection;
    }
    
    @NotNull
    public List<OCSymbolReference> getReferencesInDeclaration() {
        List list;
        try {
            list = (List)this.referencesInDeclaration.peek();
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext", "getReferencesInDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<OCSymbolReference>)list;
    }
    
    public void addSymbolReference(@NotNull final OCSymbolReference ocSymbolReference) {
        try {
            if (ocSymbolReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext", "addSymbolReference"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!this.referencesInDeclaration.isEmpty()) {
                ((List)this.referencesInDeclaration.peek()).add(ocSymbolReference);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    public void pushDeclaration() {
        this.referencesInDeclaration.push((Object)new ArrayList());
    }
    
    public void popDeclaration() {
        this.referencesInDeclaration.pop();
    }
    
    public boolean isTemplateSymbol() {
        return this.myIsTemplateSymbol;
    }
    
    public void setTemplateSymbol(final boolean myIsTemplateSymbol) {
        this.myIsTemplateSymbol = myIsTemplateSymbol;
    }
    
    public boolean isInsideTemplateParams() {
        return this.myInsideTemplateParams;
    }
    
    public void setInsideTemplateParams(final boolean myInsideTemplateParams) {
        this.myInsideTemplateParams = myInsideTemplateParams;
    }
    
    public boolean isAssumeNonNull() {
        return this.myAssumeNonNull;
    }
    
    public void setAssumeNonNull(final boolean myAssumeNonNull) {
        this.myAssumeNonNull = myAssumeNonNull;
    }
    
    @Nullable
    public OCLambdaExpressionSymbol getLambda() {
        return this.lambda;
    }
    
    public void setLambda(final OCLambdaExpressionSymbol lambda) {
        this.lambda = lambda;
    }
    
    public int getLambdaParameterIndex() {
        return this.lambdaParameterIndex;
    }
    
    public void setLambdaParameterIndex(final int lambdaParameterIndex) {
        this.lambdaParameterIndex = lambdaParameterIndex;
    }
    
    public boolean isLambdaInitCapture() {
        return this.isLambdaInitCapture;
    }
    
    public void setLambdaInitCapture(final boolean isLambdaInitCapture) {
        this.isLambdaInitCapture = isLambdaInitCapture;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
