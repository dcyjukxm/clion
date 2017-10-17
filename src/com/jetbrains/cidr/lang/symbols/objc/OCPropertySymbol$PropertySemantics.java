// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import org.jetbrains.annotations.Nullable;

public enum PropertySemantics
{
    DEFAULT, 
    STRONG, 
    WEAK, 
    ASSIGN, 
    RETAIN, 
    COPY;
    
    @Nullable
    public PropertyAttribute getPropertyAttribute() {
        switch (this) {
            case DEFAULT: {
                return null;
            }
            case STRONG: {
                return PropertyAttribute.STRONG;
            }
            case WEAK: {
                return PropertyAttribute.WEAK;
            }
            case ASSIGN: {
                return PropertyAttribute.ASSIGN;
            }
            case RETAIN: {
                return PropertyAttribute.RETAIN;
            }
            case COPY: {
                return PropertyAttribute.COPY;
            }
            default: {
                return null;
            }
        }
    }
}
