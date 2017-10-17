// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.impl.OCLazyElementBase;
import java.lang.reflect.Constructor;
import com.intellij.psi.tree.IReparseableElementType;

public abstract class OCReparseablePsiElementType extends IReparseableElementType implements OCRootElementType
{
    private final Constructor<? extends OCLazyElementBase> myCons;
    
    public OCReparseablePsiElementType(@NotNull @NonNls final String s, @NotNull final Class<? extends OCLazyElementBase> clazz) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "debugName", "com/jetbrains/cidr/lang/parser/OCReparseablePsiElementType", "<init>"));
        }
        if (clazz == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "klass", "com/jetbrains/cidr/lang/parser/OCReparseablePsiElementType", "<init>"));
        }
        super(s, (Language)OCLanguage.getInstance());
        try {
            this.myCons = clazz.getConstructor(IElementType.class, CharSequence.class);
        }
        catch (NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Nullable
    public ASTNode createNode(final CharSequence charSequence) {
        try {
            return (ASTNode)this.myCons.newInstance(this, charSequence);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    protected ASTNode doParseContents(@NotNull final ASTNode astNode, @NotNull final PsiElement psiElement) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chameleon", "com/jetbrains/cidr/lang/parser/OCReparseablePsiElementType", "doParseContents"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psi", "com/jetbrains/cidr/lang/parser/OCReparseablePsiElementType", "doParseContents"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        return OCParser.getInstance().parse(this, astNode);
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
