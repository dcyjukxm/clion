// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.dfa.OCInstruction;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.dfa.OCControlFlowGraph;
import com.jetbrains.cidr.lang.dfa.OCNotUsedValueChecker;

static final class OCDFAUtils$1 extends OCNotUsedValueChecker {
    @Override
    protected boolean isStartInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1", "isStartInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        final PsiElement rValue = ocInstruction.getRValue();
        Label_0071: {
            try {
                if (!(rValue instanceof OCReferenceExpression)) {
                    return false;
                }
                final OCNotUsedValueChecker ocNotUsedValueChecker = this;
                final PsiElement psiElement = rValue;
                final boolean b = ocNotUsedValueChecker.a(psiElement);
                if (b) {
                    break Label_0071;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
            try {
                final OCNotUsedValueChecker ocNotUsedValueChecker = this;
                final PsiElement psiElement = rValue;
                final boolean b = ocNotUsedValueChecker.a(psiElement);
                if (b) {
                    return super.isStartInstruction(ocInstruction);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw d(ex3);
            }
        }
        return false;
    }
    
    @Override
    protected boolean isEndInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1", "isEndInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return true;
    }
    
    @Override
    protected PsiElement getElementFromInstruction(@NotNull final OCInstruction ocInstruction) {
        try {
            if (ocInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "instruction", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1", "getElementFromInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return ocInstruction.getRValue();
    }
    
    private boolean a(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //     6: astore_2       
        //     7: aload_2        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCastExpression;
        //    11: ifeq            20
        //    14: iconst_1       
        //    15: ireturn        
        //    16: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    19: athrow         
        //    20: aload_2        
        //    21: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //    24: ifeq            53
        //    27: aload_2        
        //    28: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //    31: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getSourceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    36: aload_1        
        //    37: if_acmpne       53
        //    40: goto            47
        //    43: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    46: athrow         
        //    47: iconst_1       
        //    48: ireturn        
        //    49: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    52: athrow         
        //    53: aload_2        
        //    54: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //    57: ifeq            86
        //    60: aload_2        
        //    61: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //    64: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getCondition:()Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //    69: aload_1        
        //    70: if_acmpne       86
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    79: athrow         
        //    80: iconst_1       
        //    81: ireturn        
        //    82: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: aload_2        
        //    87: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSwitchStatement;
        //    90: ifeq            119
        //    93: aload_2        
        //    94: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSwitchStatement;
        //    97: invokeinterface com/jetbrains/cidr/lang/psi/OCSwitchStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //   102: aload_1        
        //   103: if_acmpne       119
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: iconst_1       
        //   114: ireturn        
        //   115: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: aload_2        
        //   120: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //   123: ifne            157
        //   126: aload_2        
        //   127: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   130: ifeq            167
        //   133: goto            140
        //   136: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   139: athrow         
        //   140: aload_2        
        //   141: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   144: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.getInstanceofPair:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$InstanceOf;
        //   147: ifnull          167
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: aload_0        
        //   158: aload_2        
        //   159: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.a:(Lcom/intellij/psi/PsiElement;)Z
        //   162: ireturn        
        //   163: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: aload_2        
        //   168: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   171: ifne            202
        //   174: aload_2        
        //   175: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   178: ifne            202
        //   181: goto            188
        //   184: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: aload_2        
        //   189: instanceof      Lcom/jetbrains/cidr/lang/psi/OCArraySelectionExpression;
        //   192: ifeq            208
        //   195: goto            202
        //   198: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   201: athrow         
        //   202: iconst_1       
        //   203: ireturn        
        //   204: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: aload_2        
        //   209: instanceof      Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //   212: ifeq            258
        //   215: aload_2        
        //   216: checkcast       Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //   219: invokeinterface com/jetbrains/cidr/lang/psi/OCUnaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   224: astore_3       
        //   225: aload_3        
        //   226: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   229: if_acmpne       238
        //   232: iconst_1       
        //   233: ireturn        
        //   234: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   237: athrow         
        //   238: aload_3        
        //   239: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXCL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   242: if_acmpne       255
        //   245: aload_0        
        //   246: aload_2        
        //   247: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.a:(Lcom/intellij/psi/PsiElement;)Z
        //   250: ireturn        
        //   251: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   254: athrow         
        //   255: goto            334
        //   258: aload_2        
        //   259: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //   262: ifeq            334
        //   265: aload_2        
        //   266: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //   269: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   274: astore_3       
        //   275: aload_3        
        //   276: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   279: if_acmpeq       324
        //   282: aload_3        
        //   283: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EXCLEQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   286: if_acmpeq       324
        //   289: goto            296
        //   292: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   295: athrow         
        //   296: aload_3        
        //   297: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ANDAND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   300: if_acmpeq       324
        //   303: goto            310
        //   306: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   309: athrow         
        //   310: aload_3        
        //   311: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OROR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   314: if_acmpne       334
        //   317: goto            324
        //   320: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   323: athrow         
        //   324: aload_0        
        //   325: aload_2        
        //   326: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.a:(Lcom/intellij/psi/PsiElement;)Z
        //   329: ireturn        
        //   330: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$1.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   333: athrow         
        //   334: iconst_0       
        //   335: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      16     16     20     Ljava/lang/IllegalArgumentException;
        //  20     40     43     47     Ljava/lang/IllegalArgumentException;
        //  27     49     49     53     Ljava/lang/IllegalArgumentException;
        //  53     73     76     80     Ljava/lang/IllegalArgumentException;
        //  60     82     82     86     Ljava/lang/IllegalArgumentException;
        //  86     106    109    113    Ljava/lang/IllegalArgumentException;
        //  93     115    115    119    Ljava/lang/IllegalArgumentException;
        //  119    133    136    140    Ljava/lang/IllegalArgumentException;
        //  126    150    153    157    Ljava/lang/IllegalArgumentException;
        //  140    163    163    167    Ljava/lang/IllegalArgumentException;
        //  167    181    184    188    Ljava/lang/IllegalArgumentException;
        //  174    195    198    202    Ljava/lang/IllegalArgumentException;
        //  188    204    204    208    Ljava/lang/IllegalArgumentException;
        //  225    234    234    238    Ljava/lang/IllegalArgumentException;
        //  238    251    251    255    Ljava/lang/IllegalArgumentException;
        //  275    289    292    296    Ljava/lang/IllegalArgumentException;
        //  282    303    306    310    Ljava/lang/IllegalArgumentException;
        //  296    317    320    324    Ljava/lang/IllegalArgumentException;
        //  310    330    330    334    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0140:
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
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
}