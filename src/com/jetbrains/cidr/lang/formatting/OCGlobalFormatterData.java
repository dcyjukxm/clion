// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCDirective;
import com.intellij.formatting.FormattingMode;
import com.intellij.util.containers.HashSet;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.util.containers.MostlySingularMultiMap;

public class OCGlobalFormatterData
{
    @NotNull
    public final MostlySingularMultiMap<ASTNode, OCCodeBlock> movedBlockMap;
    @NotNull
    public final HashSet<ASTNode> skipNodeSet;
    @NotNull
    public final OCPreprocessorFormatterData preprocessorData;
    @NotNull
    public final FormattingMode formattingMode;
    @Nullable
    public final OCDirective guardIfndef;
    public boolean isEOF;
    
    public OCGlobalFormatterData(@NotNull final PsiFile psiFile, @NotNull final FormattingMode formattingMode) {
        if (psiFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/formatting/OCGlobalFormatterData", "<init>"));
        }
        if (formattingMode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "mode", "com/jetbrains/cidr/lang/formatting/OCGlobalFormatterData", "<init>"));
        }
        this.formattingMode = formattingMode;
        this.guardIfndef = OCElementUtil.getGuardIfndef(psiFile, true);
        this.movedBlockMap = (MostlySingularMultiMap<ASTNode, OCCodeBlock>)new MostlySingularMultiMap();
        this.skipNodeSet = (HashSet<ASTNode>)new HashSet();
        this.preprocessorData = new OCPreprocessorFormatterData();
        this.isEOF = false;
    }
}
