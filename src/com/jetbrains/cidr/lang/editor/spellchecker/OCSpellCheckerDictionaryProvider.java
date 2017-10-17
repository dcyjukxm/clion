// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.spellchecker;

import com.intellij.spellchecker.BundledDictionaryProvider;

public class OCSpellCheckerDictionaryProvider implements BundledDictionaryProvider
{
    @Override
    public String[] getBundledDictionaries() {
        return new String[] { "objc.dic" };
    }
}
