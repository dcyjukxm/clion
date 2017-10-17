// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.navigation.ItemPresentation;
import javax.swing.Icon;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NonNls;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public class OCDelegatingSymbol<T extends PsiElement> implements OCSymbol<T>
{
    @NotNull
    private OCSymbol<T> myDelegate;
    
    public OCDelegatingSymbol(@NotNull final OCSymbol<T> myDelegate) {
        if (myDelegate == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "delegate", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "<init>"));
        }
        this.myDelegate = myDelegate;
    }
    
    public OCDelegatingSymbol() {
    }
    
    public static Processor<OCSymbol> getDelegateProcessor(@NotNull final Processor<OCSymbol> processor) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "getDelegateProcessor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (Processor<OCSymbol>)(ocSymbol -> {
            try {
                if (processor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "lambda$getDelegateProcessor$0"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return processor.process((Object)ocSymbol.getDelegate());
        });
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                return false;
            }
            final OCDelegatingSymbol ocDelegatingSymbol = this;
            final Class<? extends OCDelegatingSymbol> clazz = ocDelegatingSymbol.getClass();
            final Object o2 = o;
            final Class<?> clazz2 = o2.getClass();
            if (clazz != clazz2) {
                return false;
            }
            return this.myDelegate.equals(((OCDelegatingSymbol)o).myDelegate);
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            final OCDelegatingSymbol ocDelegatingSymbol = this;
            final Class<? extends OCDelegatingSymbol> clazz = ocDelegatingSymbol.getClass();
            final Object o2 = o;
            final Class<?> clazz2 = o2.getClass();
            if (clazz != clazz2) {
                return false;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return this.myDelegate.equals(((OCDelegatingSymbol)o).myDelegate);
    }
    
    @Override
    public int hashCode() {
        return this.myDelegate.hashCode();
    }
    
    @NotNull
    public OCSymbol<T> getRawDelegate() {
        OCSymbol<T> myDelegate;
        try {
            myDelegate = this.myDelegate;
            if (myDelegate == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "getRawDelegate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myDelegate;
    }
    
    @NotNull
    @Override
    public OCSymbol<?> getDelegate() {
        OCSymbol<?> delegate;
        try {
            delegate = this.myDelegate.getDelegate();
            if (delegate == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "getDelegate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return delegate;
    }
    
    @NotNull
    @Override
    public String getPresentableText() {
        String presentableText;
        try {
            presentableText = this.myDelegate.getPresentableText();
            if (presentableText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "getPresentableText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return presentableText;
    }
    
    @NotNull
    @Override
    public OCType getType() {
        OCType type;
        try {
            type = this.myDelegate.getType();
            if (type == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return type;
    }
    
    @NotNull
    @Override
    public OCType getResolvedType() {
        OCType resolvedType;
        try {
            resolvedType = this.myDelegate.getResolvedType();
            if (resolvedType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return resolvedType;
    }
    
    @Override
    public OCType getResolvedType(final boolean b) {
        return this.myDelegate.getResolvedType(b);
    }
    
    @Override
    public boolean isGlobal() {
        return this.myDelegate.isGlobal();
    }
    
    @Override
    public boolean isTopLevel() {
        return this.myDelegate.isTopLevel();
    }
    
    @Override
    public boolean isCallable() {
        return this.myDelegate.isCallable();
    }
    
    @Override
    public boolean isDefinition() {
        return this.myDelegate.isDefinition();
    }
    
    @Override
    public boolean isPredeclaration() {
        return this.myDelegate.isPredeclaration();
    }
    
    @NotNull
    @Override
    public List<String> getAttributes() {
        List<String> attributes;
        try {
            attributes = this.myDelegate.getAttributes();
            if (attributes == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "getAttributes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return attributes;
    }
    
    @Override
    public void addAttributes(final List<String> list) {
        this.myDelegate.addAttributes(list);
    }
    
    @Override
    public boolean isUnnamed() {
        return this.myDelegate.isUnnamed();
    }
    
    @Override
    public Object getData(@NonNls final String s) {
        return this.myDelegate.getData(s);
    }
    
    @Nullable
    @Override
    public T locateDefinition() {
        return this.myDelegate.locateDefinition();
    }
    
    @Override
    public void updateOffset(final int n, final int n2, final int n3) {
    }
    
    @Override
    public void compact() {
    }
    
    @NotNull
    @Override
    public String getName() {
        String name;
        try {
            name = this.myDelegate.getName();
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return name;
    }
    
    @NotNull
    @Override
    public String getSignature() {
        String signature;
        try {
            signature = this.myDelegate.getSignature();
            if (signature == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "getSignature"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return signature;
    }
    
    @Nullable
    @Override
    public TextRange getScope() {
        return this.myDelegate.getScope();
    }
    
    @NotNull
    @Override
    public String getPresentableName() {
        String presentableName;
        try {
            presentableName = this.myDelegate.getPresentableName();
            if (presentableName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "getPresentableName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return presentableName;
    }
    
    @Nullable
    @Override
    public String getLocationString() {
        return this.myDelegate.getLocationString();
    }
    
    @Override
    public boolean isSynthetic() {
        return this.myDelegate.isSynthetic();
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind kind;
        try {
            kind = this.myDelegate.getKind();
            if (kind == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return kind;
    }
    
    @NotNull
    @Override
    public String getNameWithKindLowercase() {
        String nameWithKindLowercase;
        try {
            nameWithKindLowercase = this.myDelegate.getNameWithKindLowercase();
            if (nameWithKindLowercase == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "getNameWithKindLowercase"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return nameWithKindLowercase;
    }
    
    @NotNull
    @Override
    public String getNameWithKindUppercase() {
        String nameWithKindUppercase;
        try {
            nameWithKindUppercase = this.myDelegate.getNameWithKindUppercase();
            if (nameWithKindUppercase == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "getNameWithKindUppercase"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return nameWithKindUppercase;
    }
    
    @Override
    public String getKindLowercase() {
        return this.myDelegate.getKindLowercase();
    }
    
    @Override
    public String getKindUppercase() {
        return this.myDelegate.getKindUppercase();
    }
    
    @Nullable
    @Override
    public OCSymbol getDefinitionSymbol() {
        return this.myDelegate.getDefinitionSymbol();
    }
    
    @Override
    public boolean processAssociatedSymbols(final Processor<OCSymbol> processor) {
        return this.myDelegate.processAssociatedSymbols((com.intellij.util.Processor<OCSymbol<T>>)processor);
    }
    
    @Nullable
    @Override
    public OCSymbol getAssociatedSymbol() {
        return this.myDelegate.getAssociatedSymbol();
    }
    
    @Override
    public OCSymbol getFirstPredeclaration() {
        return this.myDelegate.getFirstPredeclaration();
    }
    
    @Override
    public boolean processPredeclarations(final Processor<OCSymbol> processor) {
        return this.myDelegate.processPredeclarations((com.intellij.util.Processor<OCSymbol<T>>)processor);
    }
    
    @Override
    public boolean processSameSymbols(final Processor<OCSymbol> processor) {
        return this.myDelegate.processSameSymbols((com.intellij.util.Processor<OCSymbol<T>>)processor);
    }
    
    @Nullable
    @Override
    public PsiFile getContainingPsiFile() {
        return this.myDelegate.getContainingPsiFile();
    }
    
    @Nullable
    @Override
    public OCFile getContainingOCFile() {
        return this.myDelegate.getContainingOCFile();
    }
    
    @Override
    public int getOffset() {
        return this.myDelegate.getOffset();
    }
    
    @Override
    public long getComplexOffset() {
        return this.myDelegate.getComplexOffset();
    }
    
    @Nullable
    @Override
    public Icon getIcon() {
        return this.myDelegate.getIcon();
    }
    
    @Nullable
    @Override
    public Icon getBaseIcon() {
        return this.myDelegate.getBaseIcon();
    }
    
    @Nullable
    @Override
    public Icon computeFullIconNow(@Nullable final T t) {
        return this.myDelegate.computeFullIconNow(t);
    }
    
    @Override
    public boolean isSameSymbol(@Nullable final OCSymbol ocSymbol) {
        return this.myDelegate.isSameSymbol(ocSymbol);
    }
    
    @Override
    public int hashCodeExcludingOffset() {
        return this.myDelegate.hashCodeExcludingOffset();
    }
    
    @Override
    public OCType getEffectiveType() {
        return this.myDelegate.getEffectiveType();
    }
    
    @NotNull
    @Override
    public OCType getEffectiveResolvedType() {
        OCType effectiveResolvedType;
        try {
            effectiveResolvedType = this.myDelegate.getEffectiveResolvedType();
            if (effectiveResolvedType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "getEffectiveResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return effectiveResolvedType;
    }
    
    @Override
    public boolean isDeprecated() {
        return this.myDelegate.isDeprecated();
    }
    
    @Override
    public String getDeprecatedMessage() {
        return this.myDelegate.getDeprecatedMessage();
    }
    
    @Override
    public boolean isUnavailable() {
        return this.myDelegate.isUnavailable();
    }
    
    @Override
    public String getUnavailableMessage() {
        return this.myDelegate.getUnavailableMessage();
    }
    
    @Override
    public boolean isForbiddenByARC(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "isForbiddenByARC"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myDelegate.isForbiddenByARC(psiElement);
    }
    
    @Override
    public boolean isTransparentUnion() {
        return this.myDelegate.isTransparentUnion();
    }
    
    @Override
    public boolean hasAttribute(final String s) {
        return this.myDelegate.hasAttribute(s);
    }
    
    @Nullable
    @Override
    public String getAttributeParameters(final String s) {
        return this.myDelegate.getAttributeParameters(s);
    }
    
    @Nullable
    public PsiElement getTargetElement() {
        return this.myDelegate.getTargetElement();
    }
    
    @Nullable
    public ItemPresentation getPresentation() {
        return this.myDelegate.getPresentation();
    }
    
    public void navigate(final boolean b) {
        this.myDelegate.navigate(b);
    }
    
    public boolean canNavigate() {
        return this.myDelegate.canNavigate();
    }
    
    public boolean canNavigateToSource() {
        return this.myDelegate.canNavigateToSource();
    }
    
    public int compareTo(final OCSymbol ocSymbol) {
        return this.myDelegate.compareTo(ocSymbol);
    }
    
    public void init(@Nullable final Project project, @Nullable final VirtualFile virtualFile) {
    }
    
    @Nullable
    public Project getProject() {
        return this.myDelegate.getProject();
    }
    
    @Nullable
    public VirtualFile getContainingFile() {
        return this.myDelegate.getContainingFile();
    }
    
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/OCDelegatingSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return this.myDelegate.deepEqualStep(comparator, ((OCDelegatingSymbol)o).myDelegate, ((OCDelegatingSymbol)o2).myDelegate);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
