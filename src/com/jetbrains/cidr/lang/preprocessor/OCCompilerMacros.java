// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.PredefinedVariables;

public class OCCompilerMacros
{
    public static final String __STDC_VERSION__ = "__STDC_VERSION__";
    public static final String __CPLUSPLUS = "__cplusplus";
    static final String PREDEFINED_MACROS;
    public static final String NS_ENUM_MACRO = "#define NS_ENUM(_type, _name) __attribute__((NS_ENUM_MACRO)) enum _name : _type _name; enum __attribute__((NS_ENUM)) _name : _type\n";
    public static final String NS_OPTIONS_MACRO = "#define NS_OPTIONS(_type, _name) __attribute__((NS_OPTIONS_MACRO)) enum _name : _type _name; enum __attribute__((NS_OPTIONS)) _name : _type\n";
    static final String OVERRIDDEN_MACROS = "\n#define __OSX_AVAILABLE_BUT_DEPRECATED(_macIntro, _macDep, _iosIntro, _iosDep) __CIDR_OSX_AVAILABLE_BUT_DEPRECATED_IMPL(_macIntro, _macDep, _iosIntro, _iosDep)\n#define __OSX_AVAILABLE_BUT_DEPRECATED_MSG(_osxIntro, _osxDep, _iosIntro, _iosDep, _msg) __CIDR_OSX_AVAILABLE_BUT_DEPRECATED_IMPL(_macIntro, _macDep, _iosIntro, _iosDep)\n#define __OSX_AVAILABLE_STARTING(mac, ios) __CIDR_OSX_AVAILABLE_STARTING_IMPL(mac, ios)\n#define CF_DEPRECATED(_macIntro, _macDep, _iosIntro, _iosDep) __CIDR_OSX_DEPRECATED(_macIntro, _macDep, _iosIntro, _iosDep)\n#define CF_DEPRECATED_MAC(_macIntro, _macDep) __CIDR_OSX_DEPRECATED(_macIntro, _macDep, NA, NA)\n#define CF_DEPRECATED_IOS(_iosIntro, _iosDep) __CIDR_OSX_DEPRECATED(NA, NA, _iosIntro, _iosDep)\n#define CF_AVAILABLE(mac, ios) __CIDR_OSX_AVAILABLE(mac, ios)\n#define CF_AVAILABLE_MAC(mac) __CIDR_OSX_AVAILABLE(mac, NA)\n#define CF_AVAILABLE_IOS(mac) __CIDR_OSX_AVAILABLE(NA, mac)\n#define CF_CLASS_AVAILABLE(mac, ios) __CIDR_OSX_AVAILABLE(mac, ios)\n#define NS_AVAILABLE(mac, ios) __CIDR_OSX_AVAILABLE(mac, ios)\n#define NS_AVAILABLE_MAC(mac) __CIDR_OSX_AVAILABLE(mac, NA)\n#define NS_AVAILABLE_IOS(mac) __CIDR_OSX_AVAILABLE(NA, mac)\n#define NS_CLASS_AVAILABLE(mac, ios) __CIDR_OSX_AVAILABLE(mac, ios)\n#define NS_ENUM(_type, _name) __attribute__((NS_ENUM_MACRO)) enum _name : _type _name; enum __attribute__((NS_ENUM)) _name : _type\n\n#define NS_OPTIONS(_type, _name) __attribute__((NS_OPTIONS_MACRO)) enum _name : _type _name; enum __attribute__((NS_OPTIONS)) _name : _type\n";
    
    @NotNull
    private static String a() {
        String join;
        try {
            join = StringUtil.join((Collection)PredefinedVariables.getIDEVariables(), s -> "#define __" + s + "__ " + PredefinedVariables.getVersionNumber() + "L", "\n");
            if (join == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCCompilerMacros", "getIDEMacros"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return join;
    }
    
    static {
        PREDEFINED_MACROS = "\n" + a() + "\n#define __PRETTY_FUNCTION__ \"_function_name_\"\n#define __FUNCTION__ \"_function_name_\"\n#define __FUNCDNAME__ \"_function_decorated_name_\"\n#define __FUNCSIG__ \"_function__signature_\"\n#define __func__ \"_function_name_\"\n#define __DATE__ \"_date_\"\n#define __TIME__ \"_time_\"\n#define __TIMESTAMP__ \"_timestamp_\"\n#define __FILE__ \"_file_name_\"\n#define __BASE_FILE__ \"_base_file_name_\"\n#define __LINE__ 0\n#define __COUNTER__ 0\n#define __INCLUDE_LEVEL__ 0\n#define __is_base_of(_Bp, _Dp) is_class<_Bp>::value && is_class<_Dp>::value && is_convertible<_Dp, _Bp>::value\n#define __CIDR_OSX_AVAILABLE_BUT_DEPRECATED_IMPL(_macIntro, _macDep, _iosIntro, _iosDep) __attribute__((deprecated)) __attribute__((" + "__min_macos_version_" + "##_macIntro)) __attribute__((" + "__min_ios_version_" + "##_iosIntro))\n#define __CIDR_OSX_AVAILABLE_STARTING_IMPL(mac, ios) __attribute__((" + "__min_macos_version_" + "##mac)) __attribute__((" + "__min_ios_version_" + "##ios))\n#define __CIDR_OSX_DEPRECATED_IMPL(_macIntro, _macDep, _iosIntro, _iosDep) __CIDR_OSX_AVAILABLE_BUT_DEPRECATED_IMPL(_macIntro, _macDep, _iosIntro, _iosDep)\n#define __CIDR_OSX_AVAILABLE_IMPL(mac, ios) __CIDR_OSX_AVAILABLE_STARTING_IMPL(mac, ios)\n#define __CIDR_OSX_DEPRECATED(_macIntro, _macDep, _iosIntro, _iosDep) __CIDR_OSX_DEPRECATED_IMPL(__MAC_##_macIntro, __MAC_##_macDep, __IPHONE_##_iosIntro, __IPHONE_##_iosDep)\n#define __CIDR_OSX_AVAILABLE(mac, ios) __CIDR_OSX_AVAILABLE_IMPL(__MAC_##mac, __IPHONE_##ios)\n";
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
