// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class OCPreprocessorFormatterData
{
    public int directiveNestLevel;
    public ArrayList<OCIndentInfo> directiveIndent;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCPreprocessorFormatterData() {
        this.directiveNestLevel = 0;
        this.directiveIndent = new ArrayList<OCIndentInfo>();
    }
    
    @NotNull
    public OCIndentInfo getIndentAtLevel() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.$assertionsDisabled:Z
        //     3: ifne            53
        //     6: aload_0        
        //     7: getfield        com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.directiveNestLevel:I
        //    10: iflt            41
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    19: athrow         
        //    20: aload_0        
        //    21: getfield        com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.directiveNestLevel:I
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.directiveIndent:Ljava/util/ArrayList;
        //    28: invokevirtual   java/util/ArrayList.size:()I
        //    31: if_icmplt       53
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    40: athrow         
        //    41: new             Ljava/lang/AssertionError;
        //    44: dup            
        //    45: invokespecial   java/lang/AssertionError.<init>:()V
        //    48: athrow         
        //    49: invokestatic    com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    52: athrow         
        //    53: aload_0        
        //    54: getfield        com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.directiveIndent:Ljava/util/ArrayList;
        //    57: aload_0        
        //    58: getfield        com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.directiveNestLevel:I
        //    61: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //    64: checkcast       Lcom/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OCIndentInfo;
        //    67: dup            
        //    68: ifnonnull       105
        //    71: new             Ljava/lang/IllegalStateException;
        //    74: dup            
        //    75: ldc             "@NotNull method %s.%s must not return null"
        //    77: ldc             2
        //    79: anewarray       Ljava/lang/Object;
        //    82: dup            
        //    83: ldc             0
        //    85: ldc             "com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData"
        //    87: aastore        
        //    88: dup            
        //    89: ldc             1
        //    91: ldc             "getIndentAtLevel"
        //    93: aastore        
        //    94: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    97: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   100: athrow         
        //   101: invokestatic    com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   104: athrow         
        //   105: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      13     16     20     Ljava/lang/IllegalStateException;
        //  6      34     37     41     Ljava/lang/IllegalStateException;
        //  20     49     49     53     Ljava/lang/IllegalStateException;
        //  53     101    101    105    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
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
    
    public void setIndentAtLevel(final OCIndentInfo ocIndentInfo) {
        Label_0032: {
            Label_0020: {
                try {
                    if (OCPreprocessorFormatterData.$assertionsDisabled) {
                        break Label_0032;
                    }
                    final OCPreprocessorFormatterData ocPreprocessorFormatterData = this;
                    final int n = ocPreprocessorFormatterData.directiveNestLevel;
                    if (n < 0) {
                        break Label_0020;
                    }
                    break Label_0032;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final OCPreprocessorFormatterData ocPreprocessorFormatterData = this;
                    final int n = ocPreprocessorFormatterData.directiveNestLevel;
                    if (n < 0) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            try {
                if (this.directiveNestLevel >= this.directiveIndent.size()) {
                    this.directiveIndent.add(this.directiveNestLevel, ocIndentInfo);
                    return;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        this.directiveIndent.set(this.directiveNestLevel, ocIndentInfo);
    }
    
    public Pair<OffsetType, Integer> getIndents(final IElementType elementType, final OCCodeStyleSettings ocCodeStyleSettings, final boolean b) {
        OffsetType offsetType = null;
        Integer n = 0;
        int n2 = 0;
        Label_0246: {
            Label_0188: {
                OffsetType offsetType2 = null;
                Label_0163: {
                    Label_0150: {
                        Label_0129: {
                            Label_0104: {
                                Label_0085: {
                                    Label_0046: {
                                        try {
                                            if (!OCTokenTypes.END_IF_DIRECTIVES.contains(elementType)) {
                                                break Label_0085;
                                            }
                                            final OCPreprocessorFormatterData ocPreprocessorFormatterData = this;
                                            final int n3 = ocPreprocessorFormatterData.directiveNestLevel;
                                            final int n4 = 1;
                                            final int n5 = n3 - n4;
                                            ocPreprocessorFormatterData.directiveNestLevel = n5;
                                            final OCPreprocessorFormatterData ocPreprocessorFormatterData2 = this;
                                            final int n6 = ocPreprocessorFormatterData2.directiveNestLevel;
                                            if (n6 < 0) {
                                                break Label_0046;
                                            }
                                            break Label_0046;
                                        }
                                        catch (IllegalStateException ex) {
                                            throw a(ex);
                                        }
                                        try {
                                            final OCPreprocessorFormatterData ocPreprocessorFormatterData = this;
                                            final int n3 = ocPreprocessorFormatterData.directiveNestLevel;
                                            final int n4 = 1;
                                            final int n5 = n3 - n4;
                                            ocPreprocessorFormatterData.directiveNestLevel = n5;
                                            final OCPreprocessorFormatterData ocPreprocessorFormatterData2 = this;
                                            final int n6 = ocPreprocessorFormatterData2.directiveNestLevel;
                                            if (n6 < 0) {
                                                this.directiveNestLevel = 0;
                                                break Label_0085;
                                            }
                                        }
                                        catch (IllegalStateException ex2) {
                                            throw a(ex2);
                                        }
                                    }
                                    final OCIndentInfo indentAtLevel = this.getIndentAtLevel();
                                    offsetType = indentAtLevel.baseIndent;
                                    n = indentAtLevel.ifIndent;
                                    n2 = indentAtLevel.ifNestLevel;
                                    try {
                                        if (offsetType != null) {
                                            break Label_0246;
                                        }
                                        final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                                        final boolean b2 = ocCodeStyleSettings2.KEEP_DIRECTIVE_AT_FIRST_COLUMN;
                                        if (b2) {
                                            break Label_0104;
                                        }
                                        break Label_0129;
                                    }
                                    catch (IllegalStateException ex3) {
                                        throw a(ex3);
                                    }
                                }
                                try {
                                    final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                                    final boolean b2 = ocCodeStyleSettings2.KEEP_DIRECTIVE_AT_FIRST_COLUMN;
                                    if (!b2) {
                                        break Label_0129;
                                    }
                                    if (!b) {
                                        break Label_0129;
                                    }
                                }
                                catch (IllegalStateException ex4) {
                                    throw a(ex4);
                                }
                            }
                            offsetType = OffsetType.DIRECTIVE_ABSOLUTE;
                            n2 = this.directiveNestLevel;
                            break Label_0246;
                            try {
                                if (this.directiveNestLevel != 0) {
                                    break Label_0188;
                                }
                                final OCCodeStyleSettings ocCodeStyleSettings3 = ocCodeStyleSettings;
                                final boolean b3 = ocCodeStyleSettings3.INDENT_DIRECTIVE_AS_CODE;
                                if (b3) {
                                    break Label_0150;
                                }
                                break Label_0150;
                            }
                            catch (IllegalStateException ex5) {
                                throw a(ex5);
                            }
                        }
                        try {
                            final OCCodeStyleSettings ocCodeStyleSettings3 = ocCodeStyleSettings;
                            final boolean b3 = ocCodeStyleSettings3.INDENT_DIRECTIVE_AS_CODE;
                            if (b3) {
                                offsetType2 = OffsetType.DIRECTIVE_RELATIVE_CODE;
                                break Label_0163;
                            }
                        }
                        catch (IllegalStateException ex6) {
                            throw a(ex6);
                        }
                    }
                    offsetType2 = OffsetType.DIRECTIVE_ABSOLUTE;
                }
                offsetType = offsetType2;
                n2 = 0;
                n = (this.directiveNestLevel - n2) * ocCodeStyleSettings.INDENT_PREPROCESSOR_DIRECTIVE;
                break Label_0246;
            }
            --this.directiveNestLevel;
            final OCIndentInfo indentAtLevel2 = this.getIndentAtLevel();
            offsetType = indentAtLevel2.baseIndent;
            n2 = indentAtLevel2.ifNestLevel;
            ++this.directiveNestLevel;
            n = indentAtLevel2.ifIndent + ocCodeStyleSettings.INDENT_PREPROCESSOR_DIRECTIVE;
            try {
                if (OCFormatterUtil.FORMAT_DIRECTIVES_INCREASE_INDENT.contains(elementType)) {
                    this.setIndentAtLevel(new OCIndentInfo(offsetType, n, n2));
                    ++this.directiveNestLevel;
                }
            }
            catch (IllegalStateException ex7) {
                throw a(ex7);
            }
        }
        return (Pair<OffsetType, Integer>)Pair.pair((Object)offsetType, (Object)n);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCPreprocessorFormatterData.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    public enum OffsetType
    {
        CODE_RELATIVE_PREV, 
        CODE_AS_IS, 
        DIRECTIVE_RELATIVE_CODE, 
        DIRECTIVE_ABSOLUTE;
    }
    
    public static class OCIndentInfo
    {
        @NotNull
        public final OffsetType baseIndent;
        @NotNull
        public final Integer ifIndent;
        public final int ifNestLevel;
        
        public OCIndentInfo(@NotNull final OffsetType baseIndent, @NotNull final Integer ifIndent, final int ifNestLevel) {
            if (baseIndent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseIndent", "com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OCIndentInfo", "<init>"));
            }
            if (ifIndent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ifIndent", "com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OCIndentInfo", "<init>"));
            }
            this.baseIndent = baseIndent;
            this.ifIndent = ifIndent;
            this.ifNestLevel = ifNestLevel;
        }
    }
}
