// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.spellchecker;

import com.intellij.spellchecker.BundledDictionaryProvider;

public class DxSpellCheckerDictionaryProvider implements BundledDictionaryProvider
{
    @Override
    public String[] getBundledDictionaries() {
        return new String[] { "doxygen.dic" };
    }
}
