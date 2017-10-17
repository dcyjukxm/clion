// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.ARCAttribute;
import com.intellij.openapi.util.text.StringUtil;

public enum PropertyAttribute
{
    NONATOMIC(0), 
    ATOMIC(0), 
    READONLY(1), 
    READWRITE(1), 
    ASSIGN(2), 
    COPY(2), 
    RETAIN(2), 
    WEAK(2), 
    STRONG(2), 
    UNSAFE_UNRETAINED(2), 
    GETTER(3), 
    SETTER(4), 
    NONNULL(5), 
    NULLABLE(5), 
    NULL_RESETTABLE(5), 
    NULL_UNSPECIFIED(5), 
    CLASS(6);
    
    private final int myGroup;
    
    private PropertyAttribute(final int myGroup) {
        this.myGroup = myGroup;
    }
    
    public int getGroup() {
        return this.myGroup;
    }
    
    public String getTokenName() {
        return StringUtil.toLowerCase(this.name());
    }
    
    public ARCAttribute getIvarCompatibleARCAttribute() {
        switch (this) {
            case ASSIGN:
            case UNSAFE_UNRETAINED: {
                return ARCAttribute.UNSAFE_UNRETAINED;
            }
            case WEAK: {
                return ARCAttribute.WEAK;
            }
            case STRONG:
            case COPY:
            case RETAIN: {
                return ARCAttribute.STRONG;
            }
            default: {
                assert false;
                return null;
            }
        }
    }
    
    public int getSemanticsGroup() {
        switch (this) {
            case STRONG:
            case RETAIN: {
                return 0;
            }
            case COPY: {
                return 1;
            }
            default: {
                return 2;
            }
        }
    }
    
    @Nullable
    public PropertySemantics getSemanticsAttribute() {
        switch (this) {
            case ASSIGN:
            case UNSAFE_UNRETAINED: {
                return PropertySemantics.ASSIGN;
            }
            case COPY: {
                return PropertySemantics.COPY;
            }
            case RETAIN: {
                return PropertySemantics.RETAIN;
            }
            case WEAK: {
                return PropertySemantics.WEAK;
            }
            case STRONG: {
                return PropertySemantics.STRONG;
            }
            default: {
                return null;
            }
        }
    }
    
    public static class Group
    {
        public static final int GROUP_ATOMICITY = 0;
        public static final int GROUP_WRITEABILITY = 1;
        public static final int GROUP_ARC = 2;
        public static final int GROUP_GETTER = 3;
        public static final int GROUP_SETTER = 4;
        public static final int GROUP_NULLABILITY = 5;
        public static final int GROUP_STATICNESS = 6;
    }
}
