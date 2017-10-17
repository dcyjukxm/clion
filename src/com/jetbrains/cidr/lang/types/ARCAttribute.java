// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbolImpl;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.parser.OCElementType;

public enum ARCAttribute
{
    STRONG(OCTokenTypes.STRONG_KEYWORD), 
    WEAK(OCTokenTypes.WEAK_KEYWORD), 
    UNSAFE_UNRETAINED(OCTokenTypes.UNSAFE_UNRETAINED_KEYWORD), 
    AUTO_RELEASING(OCTokenTypes.AUTORELEASING_KEYWORD);
    
    public static ARCAttribute DEFAULT;
    private final OCElementType myTokenType;
    
    private ARCAttribute(final OCElementType myTokenType) {
        this.myTokenType = myTokenType;
    }
    
    public String getTokenName() {
        return this.myTokenType.getName();
    }
    
    public OCPropertySymbol.PropertySemantics getPropertyCompatibleSemantics(final OCType ocType, final PsiElement psiElement) {
        switch (this) {
            case STRONG: {
                return OCPropertySymbolImpl.getDefaultSemanticsForType(ocType, psiElement);
            }
            case WEAK: {
                return OCPropertySymbol.PropertySemantics.WEAK;
            }
            case UNSAFE_UNRETAINED:
            case AUTO_RELEASING: {
                return OCPropertySymbol.PropertySemantics.ASSIGN;
            }
            default: {
                assert false;
                return null;
            }
        }
    }
    
    public boolean isDefault() {
        return this == ARCAttribute.DEFAULT;
    }
    
    static {
        ARCAttribute.DEFAULT = ARCAttribute.STRONG;
    }
}
