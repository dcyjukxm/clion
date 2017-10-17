// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.highlighter.ModuleMapFileHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.lang.Language;

public class ModuleMapLanguage extends Language
{
    public static ModuleMapLanguage INSTANCE;
    
    protected ModuleMapLanguage() {
        super("ModuleMap");
        SyntaxHighlighterFactory.LANGUAGE_FACTORY.addExplicitExtension((Object)this, (Object)new SingleLazyInstanceSyntaxHighlighterFactory() {
            @NotNull
            protected SyntaxHighlighter createHighlighter() {
                ModuleMapFileHighlighter moduleMapFileHighlighter;
                try {
                    moduleMapFileHighlighter = new ModuleMapFileHighlighter();
                    if (moduleMapFileHighlighter == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/ModuleMapLanguage$1", "createHighlighter"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return (SyntaxHighlighter)moduleMapFileHighlighter;
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        });
    }
    
    @Nullable
    public LanguageFileType getAssociatedFileType() {
        return ModuleMapFileType.INSTANCE;
    }
    
    static {
        ModuleMapLanguage.INSTANCE = new ModuleMapLanguage();
    }
}
