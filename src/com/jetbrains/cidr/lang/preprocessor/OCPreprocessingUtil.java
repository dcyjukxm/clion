// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

public class OCPreprocessingUtil
{
    public static String preprocessedFileText(final OCPreprocessingLexer ocPreprocessingLexer, final String s) {
        return ocPreprocessingLexer.preprocess(s, OCPreprocessingLexer.PreprocessingMode.FILE);
    }
}
