// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

public enum Language implements ProtocolMessageEnum
{
    LanguageUnknown(0, 0), 
    LanguageC89(1, 1), 
    LanguageC(2, 2), 
    LanguageAda83(3, 3), 
    LanguageC_plus_plus(4, 4), 
    LanguageCobol74(5, 5), 
    LanguageCobol85(6, 6), 
    LanguageFortran77(7, 7), 
    LanguageFortran90(8, 8), 
    LanguagePascal83(9, 9), 
    LanguageModula2(10, 10), 
    LanguageJava(11, 11), 
    LanguageC99(12, 12), 
    LanguageAda95(13, 13), 
    LanguageFortran95(14, 14), 
    LanguagePLI(15, 15), 
    LanguageObjC(16, 16), 
    LanguageObjC_plus_plus(17, 17), 
    LanguageUPC(18, 18), 
    LanguageD(19, 19), 
    LanguagePython(20, 20), 
    LanguageOpenCL(21, 21), 
    LanguageGo(22, 22), 
    LanguageModula3(23, 23), 
    LanguageHaskell(24, 24), 
    LanguageC_plus_plus_03(25, 25), 
    LanguageC_plus_plus_11(26, 26), 
    LanguageOCaml(27, 27), 
    LanguageRust(28, 28), 
    LanguageC11(29, 29), 
    LanguageSwift(30, 30), 
    LanguageJulia(31, 31), 
    LanguageDylan(32, 32), 
    LanguageC_plus_plus_14(33, 33), 
    LanguageFortran03(34, 34), 
    LanguageFortran08(35, 35), 
    LanguageMipsAssembler(36, 36), 
    LanguageExtRenderScript(37, 37), 
    UnsupportedLanguage(38, 38);
    
    public static final int LanguageUnknown_VALUE = 0;
    public static final int LanguageC89_VALUE = 1;
    public static final int LanguageC_VALUE = 2;
    public static final int LanguageAda83_VALUE = 3;
    public static final int LanguageC_plus_plus_VALUE = 4;
    public static final int LanguageCobol74_VALUE = 5;
    public static final int LanguageCobol85_VALUE = 6;
    public static final int LanguageFortran77_VALUE = 7;
    public static final int LanguageFortran90_VALUE = 8;
    public static final int LanguagePascal83_VALUE = 9;
    public static final int LanguageModula2_VALUE = 10;
    public static final int LanguageJava_VALUE = 11;
    public static final int LanguageC99_VALUE = 12;
    public static final int LanguageAda95_VALUE = 13;
    public static final int LanguageFortran95_VALUE = 14;
    public static final int LanguagePLI_VALUE = 15;
    public static final int LanguageObjC_VALUE = 16;
    public static final int LanguageObjC_plus_plus_VALUE = 17;
    public static final int LanguageUPC_VALUE = 18;
    public static final int LanguageD_VALUE = 19;
    public static final int LanguagePython_VALUE = 20;
    public static final int LanguageOpenCL_VALUE = 21;
    public static final int LanguageGo_VALUE = 22;
    public static final int LanguageModula3_VALUE = 23;
    public static final int LanguageHaskell_VALUE = 24;
    public static final int LanguageC_plus_plus_03_VALUE = 25;
    public static final int LanguageC_plus_plus_11_VALUE = 26;
    public static final int LanguageOCaml_VALUE = 27;
    public static final int LanguageRust_VALUE = 28;
    public static final int LanguageC11_VALUE = 29;
    public static final int LanguageSwift_VALUE = 30;
    public static final int LanguageJulia_VALUE = 31;
    public static final int LanguageDylan_VALUE = 32;
    public static final int LanguageC_plus_plus_14_VALUE = 33;
    public static final int LanguageFortran03_VALUE = 34;
    public static final int LanguageFortran08_VALUE = 35;
    public static final int LanguageMipsAssembler_VALUE = 36;
    public static final int LanguageExtRenderScript_VALUE = 37;
    public static final int UnsupportedLanguage_VALUE = 38;
    private static Internal.EnumLiteMap<Language> internalValueMap;
    private static final Language[] VALUES;
    private final int index;
    private final int value;
    
    public final int getNumber() {
        return this.value;
    }
    
    public static Language valueOf(final int n) {
        try {
            switch (n) {
                case 0: {
                    return Language.LanguageUnknown;
                }
                case 1: {
                    break;
                }
                case 2: {
                    return Language.LanguageC;
                }
                case 3: {
                    return Language.LanguageAda83;
                }
                case 4: {
                    return Language.LanguageC_plus_plus;
                }
                case 5: {
                    return Language.LanguageCobol74;
                }
                case 6: {
                    return Language.LanguageCobol85;
                }
                case 7: {
                    return Language.LanguageFortran77;
                }
                case 8: {
                    return Language.LanguageFortran90;
                }
                case 9: {
                    return Language.LanguagePascal83;
                }
                case 10: {
                    return Language.LanguageModula2;
                }
                case 11: {
                    return Language.LanguageJava;
                }
                case 12: {
                    return Language.LanguageC99;
                }
                case 13: {
                    return Language.LanguageAda95;
                }
                case 14: {
                    return Language.LanguageFortran95;
                }
                case 15: {
                    return Language.LanguagePLI;
                }
                case 16: {
                    return Language.LanguageObjC;
                }
                case 17: {
                    return Language.LanguageObjC_plus_plus;
                }
                case 18: {
                    return Language.LanguageUPC;
                }
                case 19: {
                    return Language.LanguageD;
                }
                case 20: {
                    return Language.LanguagePython;
                }
                case 21: {
                    return Language.LanguageOpenCL;
                }
                case 22: {
                    return Language.LanguageGo;
                }
                case 23: {
                    return Language.LanguageModula3;
                }
                case 24: {
                    return Language.LanguageHaskell;
                }
                case 25: {
                    return Language.LanguageC_plus_plus_03;
                }
                case 26: {
                    return Language.LanguageC_plus_plus_11;
                }
                case 27: {
                    return Language.LanguageOCaml;
                }
                case 28: {
                    return Language.LanguageRust;
                }
                case 29: {
                    return Language.LanguageC11;
                }
                case 30: {
                    return Language.LanguageSwift;
                }
                case 31: {
                    return Language.LanguageJulia;
                }
                case 32: {
                    return Language.LanguageDylan;
                }
                case 33: {
                    return Language.LanguageC_plus_plus_14;
                }
                case 34: {
                    return Language.LanguageFortran03;
                }
                case 35: {
                    return Language.LanguageFortran08;
                }
                case 36: {
                    return Language.LanguageMipsAssembler;
                }
                case 37: {
                    return Language.LanguageExtRenderScript;
                }
                case 38: {
                    return Language.UnsupportedLanguage;
                }
                default: {
                    return null;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return Language.LanguageC89;
    }
    
    public static Internal.EnumLiteMap<Language> internalGetValueMap() {
        return Language.internalValueMap;
    }
    
    public final Descriptors.EnumValueDescriptor getValueDescriptor() {
        return getDescriptor().getValues().get(this.index);
    }
    
    public final Descriptors.EnumDescriptor getDescriptorForType() {
        return getDescriptor();
    }
    
    public static final Descriptors.EnumDescriptor getDescriptor() {
        return Model.getDescriptor().getEnumTypes().get(4);
    }
    
    public static Language valueOf(final Descriptors.EnumValueDescriptor enumValueDescriptor) {
        try {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return Language.VALUES[enumValueDescriptor.getIndex()];
    }
    
    private Language(final int index, final int value) {
        this.index = index;
        this.value = value;
    }
    
    static {
        Language.internalValueMap = (Internal.EnumLiteMap<Language>)new Internal.EnumLiteMap<Language>() {
            public Language findValueByNumber(final int n) {
                return Language.valueOf(n);
            }
        };
        VALUES = values();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
