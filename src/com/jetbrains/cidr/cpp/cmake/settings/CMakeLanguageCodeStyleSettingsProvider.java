// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.settings;

import com.jetbrains.cidr.cpp.CPPBundle;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.application.options.SmartIndentOptionsEditor;
import com.intellij.application.options.IndentOptionsEditor;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.CMakeLanguage;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;

public class CMakeLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider
{
    @NotNull
    public Language getLanguage() {
        CMakeLanguage instance;
        try {
            instance = CMakeLanguage.INSTANCE;
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider", "getLanguage"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return instance;
    }
    
    @Nullable
    public CommonCodeStyleSettings getDefaultCommonSettings() {
        final CommonCodeStyleSettings commonCodeStyleSettings = new CommonCodeStyleSettings(this.getLanguage());
        commonCodeStyleSettings.initIndentOptions();
        return commonCodeStyleSettings;
    }
    
    @NotNull
    public String getCodeSample(@NotNull final LanguageCodeStyleSettingsProvider.SettingsType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "settingsType"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getCodeSample"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: getstatic       com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider$1.$SwitchMap$com$intellij$psi$codeStyle$LanguageCodeStyleSettingsProvider$SettingsType:[I
        //    47: aload_1        
        //    48: invokevirtual   com/intellij/psi/codeStyle/LanguageCodeStyleSettingsProvider$SettingsType.ordinal:()I
        //    51: iaload         
        //    52: tableswitch {
        //                2: 84
        //                3: 139
        //                4: 187
        //                5: 235
        //          default: 283
        //        }
        //    84: aload_0        
        //    85: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    88: ldc             "formatter/Spaces.cmake"
        //    90: invokestatic    com/jetbrains/cidr/lang/settings/OCCustomOption$ResourceReader.readExampleString:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/String;
        //    93: dup            
        //    94: ifnonnull       138
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   103: athrow         
        //   104: new             Ljava/lang/IllegalStateException;
        //   107: dup            
        //   108: ldc             "@NotNull method %s.%s must not return null"
        //   110: ldc             2
        //   112: anewarray       Ljava/lang/Object;
        //   115: dup            
        //   116: ldc             0
        //   118: ldc             "com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider"
        //   120: aastore        
        //   121: dup            
        //   122: ldc             1
        //   124: ldc             "getCodeSample"
        //   126: aastore        
        //   127: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   130: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   133: athrow         
        //   134: invokestatic    com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   137: athrow         
        //   138: areturn        
        //   139: aload_0        
        //   140: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   143: ldc             "formatter/Indent.cmake"
        //   145: invokestatic    com/jetbrains/cidr/lang/settings/OCCustomOption$ResourceReader.readExampleString:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/String;
        //   148: dup            
        //   149: ifnonnull       186
        //   152: new             Ljava/lang/IllegalStateException;
        //   155: dup            
        //   156: ldc             "@NotNull method %s.%s must not return null"
        //   158: ldc             2
        //   160: anewarray       Ljava/lang/Object;
        //   163: dup            
        //   164: ldc             0
        //   166: ldc             "com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider"
        //   168: aastore        
        //   169: dup            
        //   170: ldc             1
        //   172: ldc             "getCodeSample"
        //   174: aastore        
        //   175: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   178: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   181: athrow         
        //   182: invokestatic    com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   185: athrow         
        //   186: areturn        
        //   187: aload_0        
        //   188: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   191: ldc             "formatter/Wrapping.cmake"
        //   193: invokestatic    com/jetbrains/cidr/lang/settings/OCCustomOption$ResourceReader.readExampleString:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/String;
        //   196: dup            
        //   197: ifnonnull       234
        //   200: new             Ljava/lang/IllegalStateException;
        //   203: dup            
        //   204: ldc             "@NotNull method %s.%s must not return null"
        //   206: ldc             2
        //   208: anewarray       Ljava/lang/Object;
        //   211: dup            
        //   212: ldc             0
        //   214: ldc             "com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider"
        //   216: aastore        
        //   217: dup            
        //   218: ldc             1
        //   220: ldc             "getCodeSample"
        //   222: aastore        
        //   223: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   226: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   229: athrow         
        //   230: invokestatic    com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   233: athrow         
        //   234: areturn        
        //   235: aload_0        
        //   236: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   239: ldc             "formatter/Blank_lines.cmake"
        //   241: invokestatic    com/jetbrains/cidr/lang/settings/OCCustomOption$ResourceReader.readExampleString:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/String;
        //   244: dup            
        //   245: ifnonnull       282
        //   248: new             Ljava/lang/IllegalStateException;
        //   251: dup            
        //   252: ldc             "@NotNull method %s.%s must not return null"
        //   254: ldc             2
        //   256: anewarray       Ljava/lang/Object;
        //   259: dup            
        //   260: ldc             0
        //   262: ldc             "com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider"
        //   264: aastore        
        //   265: dup            
        //   266: ldc             1
        //   268: ldc             "getCodeSample"
        //   270: aastore        
        //   271: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   274: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   277: athrow         
        //   278: invokestatic    com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   281: athrow         
        //   282: areturn        
        //   283: ldc             ""
        //   285: dup            
        //   286: ifnonnull       323
        //   289: new             Ljava/lang/IllegalStateException;
        //   292: dup            
        //   293: ldc             "@NotNull method %s.%s must not return null"
        //   295: ldc             2
        //   297: anewarray       Ljava/lang/Object;
        //   300: dup            
        //   301: ldc             0
        //   303: ldc             "com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider"
        //   305: aastore        
        //   306: dup            
        //   307: ldc             1
        //   309: ldc             "getCodeSample"
        //   311: aastore        
        //   312: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   315: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   318: athrow         
        //   319: invokestatic    com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   322: athrow         
        //   323: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     97     100    104    Ljava/lang/IllegalStateException;
        //  84     134    134    138    Ljava/lang/IllegalStateException;
        //  139    182    182    186    Ljava/lang/IllegalStateException;
        //  187    230    230    234    Ljava/lang/IllegalStateException;
        //  235    278    278    282    Ljava/lang/IllegalStateException;
        //  283    319    319    323    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0084:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Nullable
    public IndentOptionsEditor getIndentOptionsEditor() {
        return (IndentOptionsEditor)new SmartIndentOptionsEditor();
    }
    
    public void customizeSettings(@NotNull final CodeStyleSettingsCustomizable codeStyleSettingsCustomizable, @NotNull final LanguageCodeStyleSettingsProvider.SettingsType settingsType) {
        try {
            if (codeStyleSettingsCustomizable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider", "customizeSettings"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (settingsType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settingsType", "com/jetbrains/cidr/cpp/cmake/settings/CMakeLanguageCodeStyleSettingsProvider", "customizeSettings"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (settingsType == LanguageCodeStyleSettingsProvider.SettingsType.INDENT_SETTINGS) {
                codeStyleSettingsCustomizable.showStandardOptions(new String[] { "INDENT_SIZE" });
                return;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            if (settingsType == LanguageCodeStyleSettingsProvider.SettingsType.SPACING_SETTINGS) {
                codeStyleSettingsCustomizable.showStandardOptions(new String[] { "SPACE_BEFORE_METHOD_CALL_PARENTHESES", "SPACE_WITHIN_METHOD_CALL_PARENTHESES", "SPACE_BEFORE_METHOD_PARENTHESES", "SPACE_WITHIN_METHOD_PARENTHESES", "SPACE_BEFORE_IF_PARENTHESES", "SPACE_WITHIN_IF_PARENTHESES", "SPACE_BEFORE_WHILE_PARENTHESES", "SPACE_WITHIN_WHILE_PARENTHESES", "SPACE_BEFORE_FOR_PARENTHESES", "SPACE_WITHIN_FOR_PARENTHESES" });
                codeStyleSettingsCustomizable.renameStandardOption("SPACE_BEFORE_METHOD_CALL_PARENTHESES", CPPBundle.message("cmake.settings.formatter.spaceBeforeMethodCallParentheses", new Object[0]));
                codeStyleSettingsCustomizable.renameStandardOption("SPACE_BEFORE_METHOD_PARENTHESES", CPPBundle.message("cmake.settings.formatter.spaceBeforeMethodParentheses", new Object[0]));
                codeStyleSettingsCustomizable.renameStandardOption("SPACE_WITHIN_METHOD_CALL_PARENTHESES", CPPBundle.message("cmake.settings.formatter.spaceWithinMethodCallParentheses", new Object[0]));
                codeStyleSettingsCustomizable.renameStandardOption("SPACE_WITHIN_METHOD_PARENTHESES", CPPBundle.message("cmake.settings.formatter.spaceWithinMethodParentheses", new Object[0]));
                return;
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        try {
            if (settingsType == LanguageCodeStyleSettingsProvider.SettingsType.WRAPPING_AND_BRACES_SETTINGS) {
                codeStyleSettingsCustomizable.showStandardOptions(new String[] { "ALIGN_MULTILINE_PARAMETERS_IN_CALLS" });
                codeStyleSettingsCustomizable.renameStandardOption(CodeStyleSettingsCustomizable.WRAPPING_METHOD_ARGUMENTS_WRAPPING, CPPBundle.message("cmake.settings.formatter.commandCallArguments", new Object[0]));
                return;
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        try {
            if (settingsType == LanguageCodeStyleSettingsProvider.SettingsType.BLANK_LINES_SETTINGS) {
                codeStyleSettingsCustomizable.showStandardOptions(new String[] { "KEEP_BLANK_LINES_IN_CODE" });
            }
        }
        catch (IllegalStateException ex6) {
            throw a(ex6);
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
