// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.types.ARCAttribute;
import com.intellij.openapi.util.text.StringUtil;
import java.util.EnumSet;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithSubstitution;

public interface OCPropertySymbol extends OCMemberSymbol, OCSymbolWithSubstitution
{
    public static final Set<PropertyAttribute> PROPERTY_ATTRIBUTES_WITH_VALUE = EnumSet.of(PropertyAttribute.GETTER, PropertyAttribute.SETTER);
    public static final Set<PropertyAttribute> PROPERTY_ATTRIBUTES_FOR_OBJECT = EnumSet.of(PropertyAttribute.RETAIN, PropertyAttribute.STRONG, PropertyAttribute.COPY);
    public static final Set<PropertyAttribute> PROPERTY_ATTRIBUTES_AFTER_LLVM_30 = EnumSet.of(PropertyAttribute.STRONG, PropertyAttribute.WEAK, PropertyAttribute.UNSAFE_UNRETAINED);
    
    boolean hasAttribute(final PropertyAttribute p0);
    
    String getAttributeValue(final PropertyAttribute p0);
    
    PropertyAttribute getAttributeOfGroup(final PropertyAttribute p0, final OCType p1, final PsiElement p2);
    
    boolean isOptional();
    
    boolean isReadonly();
    
    boolean isRetained();
    
    @NotNull
    String getGetterName();
    
    @NotNull
    String getSetterName();
    
    boolean processAccessorMethods(final Processor<? super OCMethodSymbol> p0, final boolean p1);
    
    boolean processSynthesizes(final Processor<? super OCSynthesizeSymbol> p0);
    
    @Nullable
    OCInstanceVariableSymbol getAssociatedIvar();
    
    boolean hasAllAccessorsImplemented(final boolean p0);
    
    @Nullable
    OCPropertySymbol getAssociatedPropertyInPrivateCategory();
    
    @Nullable
    OCPropertySymbol getAssociatedPropertyInMainInterface();
    
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
}
