// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCElement;
import java.lang.reflect.Constructor;

public class OCPsiElementType extends OCElementType
{
    private final Constructor<? extends OCElement> myCons;
    
    public OCPsiElementType(@NotNull @NonNls final String s, @NotNull final Class<? extends OCElement> clazz) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "debugName", "com/jetbrains/cidr/lang/parser/OCPsiElementType", "<init>"));
        }
        if (clazz == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "klass", "com/jetbrains/cidr/lang/parser/OCPsiElementType", "<init>"));
        }
        super(s, null);
        try {
            this.myCons = clazz.getConstructor(ASTNode.class);
        }
        catch (NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public PsiElement createPsi(final ASTNode astNode) {
        try {
            return (PsiElement)this.myCons.newInstance(astNode);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
