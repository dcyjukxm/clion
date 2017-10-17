// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.StubElement;

public abstract class OCStubElementType<StubT extends StubElement, PsiT extends PsiElement> extends IStubElementType<StubT, PsiT>
{
    private final String myName;
    
    public OCStubElementType(@NotNull @NonNls final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "debugName", "com/jetbrains/cidr/lang/parser/OCStubElementType", "<init>"));
        }
        super(s, (Language)OCLanguage.getInstance());
        this.myName = "oc." + s;
    }
    
    @NotNull
    public abstract PsiElement createPsi(final ASTNode p0);
    
    @NotNull
    public String getExternalId() {
        String myName;
        try {
            myName = this.myName;
            if (myName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/parser/OCStubElementType", "getExternalId"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myName;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
