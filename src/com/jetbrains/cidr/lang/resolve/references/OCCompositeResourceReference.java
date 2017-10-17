// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import com.intellij.openapi.util.TextRange;
import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiReference;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import com.intellij.util.IncorrectOperationException;
import java.util.Collection;
import java.util.ArrayList;
import com.intellij.codeInsight.lookup.LookupElement;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.intellij.util.Function;

public class OCCompositeResourceReference implements OCResourceReference
{
    private static final Function<OCResourceReference, PsiElement> ELEMENT;
    private static final Function<OCResourceReference, Class> CLASS;
    private final List<OCResourceReference> myReferences;
    
    public OCCompositeResourceReference(final List<OCResourceReference> myReferences) {
        CompositeResourceCompletionProvider.assertSameValue(myReferences, OCCompositeResourceReference.CLASS);
        CompositeResourceCompletionProvider.assertSameValue(myReferences, OCCompositeResourceReference.ELEMENT);
        this.myReferences = myReferences;
    }
    
    @NotNull
    @Override
    public List<LookupElement> getLookupElements(final boolean b) {
        final ArrayList<LookupElement> list = new ArrayList<LookupElement>();
        final Iterator<OCResourceReference> iterator = this.myReferences.iterator();
        while (iterator.hasNext()) {
            list.addAll(iterator.next().getLookupElements(b));
        }
        ArrayList<LookupElement> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCCompositeResourceReference", "getLookupElements"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return list2;
    }
    
    @Nullable
    public PsiElement resolve() {
        final Pair<PsiElement, ? extends PsiReference> a = this.a();
        try {
            if (a == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return (PsiElement)a.first;
    }
    
    private Pair<PsiElement, ? extends PsiReference> a() {
        for (final OCResourceReference ocResourceReference : this.myReferences) {
            final PsiElement resolve = ocResourceReference.resolve();
            try {
                if (resolve != null) {
                    return (Pair<PsiElement, ? extends PsiReference>)Pair.create((Object)resolve, (Object)ocResourceReference);
                }
                continue;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    public PsiElement getElement() {
        return this.getRepresentative().getElement();
    }
    
    public PsiElement handleElementRename(final String s) throws IncorrectOperationException {
        final Pair<PsiElement, ? extends PsiReference> a = this.a();
        try {
            if (a == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return ((PsiReference)a.second).handleElementRename(s);
    }
    
    public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/references/OCCompositeResourceReference", "bindToElement"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return this.getRepresentative().bindToElement(psiElement);
    }
    
    public boolean isReferenceTo(final PsiElement psiElement) {
        for (final OCResourceReference ocResourceReference : this.myReferences) {
            try {
                if (ocResourceReference.isReferenceTo(psiElement)) {
                    return true;
                }
                continue;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
        }
        return false;
    }
    
    public boolean isSoft() {
        for (final OCResourceReference ocResourceReference : this.myReferences) {
            try {
                if (!ocResourceReference.isSoft()) {
                    return false;
                }
                continue;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
        }
        return true;
    }
    
    @NotNull
    public Object[] getVariants() {
        Object[] empty_OBJECT_ARRAY;
        try {
            empty_OBJECT_ARRAY = ArrayUtil.EMPTY_OBJECT_ARRAY;
            if (empty_OBJECT_ARRAY == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCCompositeResourceReference", "getVariants"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return empty_OBJECT_ARRAY;
    }
    
    public TextRange getRangeInElement() {
        return this.getRepresentative().getRangeInElement();
    }
    
    @NotNull
    public String getCanonicalText() {
        final Pair<PsiElement, ? extends PsiReference> a = this.a();
        String s = null;
        Label_0037: {
            try {
                if (a == null) {
                    final String s2;
                    s = (s2 = this.getRepresentative().getCanonicalText());
                    break Label_0037;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            String s2;
            s = (s2 = ((PsiReference)a.second).getCanonicalText());
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCCompositeResourceReference", "getCanonicalText"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return s;
    }
    
    @NotNull
    public PsiReference getRepresentative() {
        PsiReference psiReference;
        try {
            psiReference = (PsiReference)this.myReferences.get(0);
            if (psiReference == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCCompositeResourceReference", "getRepresentative"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return psiReference;
    }
    
    static {
        ELEMENT = (ocResourceReference -> ocResourceReference.getElement());
        CLASS = (ocResourceReference -> ocResourceReference.getClass());
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
