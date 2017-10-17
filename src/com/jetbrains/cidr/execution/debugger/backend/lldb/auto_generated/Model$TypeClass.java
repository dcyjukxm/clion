// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

public enum TypeClass implements ProtocolMessageEnum
{
    TypeClassArray(0, 0), 
    TypeClassBlockPointer(1, 1), 
    TypeClassBuiltin(2, 2), 
    TypeClassClass(3, 3), 
    TypeClassComplexFloat(4, 4), 
    TypeClassComplexInteger(5, 5), 
    TypeClassEnumeration(6, 6), 
    TypeClassFunction(7, 7), 
    TypeClassMemberPointer(8, 8), 
    TypeClassObjCObject(9, 9), 
    TypeClassObjCInterface(10, 10), 
    TypeClassObjCObjectPointer(11, 11), 
    TypeClassPointer(12, 12), 
    TypeClassReference(13, 13), 
    TypeClassStruct(14, 14), 
    TypeClassTypedef(15, 15), 
    TypeClassUnion(16, 16), 
    TypeClassVector(17, 17), 
    TypeClassOther(18, 18), 
    TypeClassAny(19, 19);
    
    public static final int TypeClassArray_VALUE = 0;
    public static final int TypeClassBlockPointer_VALUE = 1;
    public static final int TypeClassBuiltin_VALUE = 2;
    public static final int TypeClassClass_VALUE = 3;
    public static final int TypeClassComplexFloat_VALUE = 4;
    public static final int TypeClassComplexInteger_VALUE = 5;
    public static final int TypeClassEnumeration_VALUE = 6;
    public static final int TypeClassFunction_VALUE = 7;
    public static final int TypeClassMemberPointer_VALUE = 8;
    public static final int TypeClassObjCObject_VALUE = 9;
    public static final int TypeClassObjCInterface_VALUE = 10;
    public static final int TypeClassObjCObjectPointer_VALUE = 11;
    public static final int TypeClassPointer_VALUE = 12;
    public static final int TypeClassReference_VALUE = 13;
    public static final int TypeClassStruct_VALUE = 14;
    public static final int TypeClassTypedef_VALUE = 15;
    public static final int TypeClassUnion_VALUE = 16;
    public static final int TypeClassVector_VALUE = 17;
    public static final int TypeClassOther_VALUE = 18;
    public static final int TypeClassAny_VALUE = 19;
    private static Internal.EnumLiteMap<TypeClass> internalValueMap;
    private static final TypeClass[] VALUES;
    private final int index;
    private final int value;
    
    public final int getNumber() {
        return this.value;
    }
    
    public static TypeClass valueOf(final int n) {
        try {
            switch (n) {
                case 0: {
                    return TypeClass.TypeClassArray;
                }
                case 1: {
                    break;
                }
                case 2: {
                    return TypeClass.TypeClassBuiltin;
                }
                case 3: {
                    return TypeClass.TypeClassClass;
                }
                case 4: {
                    return TypeClass.TypeClassComplexFloat;
                }
                case 5: {
                    return TypeClass.TypeClassComplexInteger;
                }
                case 6: {
                    return TypeClass.TypeClassEnumeration;
                }
                case 7: {
                    return TypeClass.TypeClassFunction;
                }
                case 8: {
                    return TypeClass.TypeClassMemberPointer;
                }
                case 9: {
                    return TypeClass.TypeClassObjCObject;
                }
                case 10: {
                    return TypeClass.TypeClassObjCInterface;
                }
                case 11: {
                    return TypeClass.TypeClassObjCObjectPointer;
                }
                case 12: {
                    return TypeClass.TypeClassPointer;
                }
                case 13: {
                    return TypeClass.TypeClassReference;
                }
                case 14: {
                    return TypeClass.TypeClassStruct;
                }
                case 15: {
                    return TypeClass.TypeClassTypedef;
                }
                case 16: {
                    return TypeClass.TypeClassUnion;
                }
                case 17: {
                    return TypeClass.TypeClassVector;
                }
                case 18: {
                    return TypeClass.TypeClassOther;
                }
                case 19: {
                    return TypeClass.TypeClassAny;
                }
                default: {
                    return null;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return TypeClass.TypeClassBlockPointer;
    }
    
    public static Internal.EnumLiteMap<TypeClass> internalGetValueMap() {
        return TypeClass.internalValueMap;
    }
    
    public final Descriptors.EnumValueDescriptor getValueDescriptor() {
        return getDescriptor().getValues().get(this.index);
    }
    
    public final Descriptors.EnumDescriptor getDescriptorForType() {
        return getDescriptor();
    }
    
    public static final Descriptors.EnumDescriptor getDescriptor() {
        return Model.getDescriptor().getEnumTypes().get(3);
    }
    
    public static TypeClass valueOf(final Descriptors.EnumValueDescriptor enumValueDescriptor) {
        try {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return TypeClass.VALUES[enumValueDescriptor.getIndex()];
    }
    
    private TypeClass(final int index, final int value) {
        this.index = index;
        this.value = value;
    }
    
    static {
        TypeClass.internalValueMap = (Internal.EnumLiteMap<TypeClass>)new Internal.EnumLiteMap<TypeClass>() {
            public TypeClass findValueByNumber(final int n) {
                return TypeClass.valueOf(n);
            }
        };
        VALUES = values();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
