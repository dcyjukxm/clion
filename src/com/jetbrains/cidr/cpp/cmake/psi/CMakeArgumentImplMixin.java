// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.cpp.cmake.editor.CMakeArgumentPresentation;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.cpp.cmake.resolve.CMakeRenameUtils;
import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.cpp.cmake.completion.CMakeCompletionUtils;
import javax.swing.Icon;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.resolve.CMakeCommandReference;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiPolyVariantReference;
import com.jetbrains.cidr.cpp.cmake.interpreter.CMakeInterpreter;
import com.jetbrains.cidr.cpp.cmake.interpreter.CMakeScope;
import java.util.List;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public abstract class CMakeArgumentImplMixin extends CMakeElementBase implements CMakeArgument
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public CMakeArgumentImplMixin(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public PsiElement getLiteralNotNull() {
        final CMakeLiteral cMakeLiteral = this.getCMakeLiteral();
        CMakeLiteral cMakeLiteral3 = null;
        Label_0034: {
            Label_0022: {
                try {
                    if (CMakeArgumentImplMixin.$assertionsDisabled) {
                        break Label_0034;
                    }
                    final CMakeLiteral cMakeLiteral2 = cMakeLiteral;
                    if (cMakeLiteral2 == null) {
                        break Label_0022;
                    }
                    break Label_0034;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final CMakeLiteral cMakeLiteral2 = cMakeLiteral;
                    if (cMakeLiteral2 == null) {
                        throw new AssertionError();
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            try {
                cMakeLiteral3 = cMakeLiteral;
                if (cMakeLiteral3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin", "getLiteralNotNull"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return (PsiElement)cMakeLiteral3;
    }
    
    @Override
    public boolean isBracketArgument() {
        try {
            if (this.getFirstChild().getNode().getElementType() == CMakeTokenTypes.BRACKET_ARG_START) {
                return true;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public boolean isQuotedArgument() {
        try {
            if (this.getFirstChild().getNode().getElementType() == CMakeTokenTypes.QUOTE) {
                return true;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public void expandIntoArgumentList(@NotNull final List<String> list, @NotNull final CMakeScope cMakeScope) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin", "expandIntoArgumentList"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (cMakeScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin", "expandIntoArgumentList"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final String text = this.getLiteralNotNull().getText();
        try {
            if (this.isBracketArgument()) {
                list.add(text);
                return;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final boolean quotedArgument = this.isQuotedArgument();
        final String eval = cMakeScope.eval(text);
        Label_0150: {
            try {
                if (eval == null) {
                    return;
                }
                final boolean b = quotedArgument;
                if (b) {
                    break Label_0150;
                }
                break Label_0150;
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
            try {
                final boolean b = quotedArgument;
                if (b) {
                    list.add(eval);
                    return;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
        }
        CMakeInterpreter.expandListArgument(eval, list);
    }
    
    @Nullable
    @Override
    public PsiPolyVariantReference getReference() {
        Label_0021: {
            try {
                if (this.isCommandDefinitionName()) {
                    break Label_0021;
                }
                final CMakeArgumentImplMixin cMakeArgumentImplMixin = this;
                final boolean b = cMakeArgumentImplMixin.isEndCommandDefinitionName();
                if (b) {
                    break Label_0021;
                }
                return null;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final CMakeArgumentImplMixin cMakeArgumentImplMixin = this;
                final boolean b = cMakeArgumentImplMixin.isEndCommandDefinitionName();
                if (b) {
                    return (PsiPolyVariantReference)new CMakeCommandReference((PsiElement)this, new TextRange(0, this.getTextRange().getLength()));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    @Nullable
    public Icon getIcon(final int n) {
        Label_0051: {
            Label_0030: {
                Label_0021: {
                    try {
                        if (this.isFunctionName()) {
                            break Label_0021;
                        }
                        final CMakeArgumentImplMixin cMakeArgumentImplMixin = this;
                        final boolean b = cMakeArgumentImplMixin.isEndFunctionName();
                        if (b) {
                            break Label_0021;
                        }
                        break Label_0030;
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    try {
                        final CMakeArgumentImplMixin cMakeArgumentImplMixin = this;
                        final boolean b = cMakeArgumentImplMixin.isEndFunctionName();
                        if (b) {
                            return CMakeCompletionUtils.getRoutineIcon(true);
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                }
                try {
                    if (this.isMacroName()) {
                        break Label_0051;
                    }
                    final CMakeArgumentImplMixin cMakeArgumentImplMixin2 = this;
                    final boolean b2 = cMakeArgumentImplMixin2.isEndMacroName();
                    if (b2) {
                        break Label_0051;
                    }
                    return super.getIcon(n);
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            try {
                final CMakeArgumentImplMixin cMakeArgumentImplMixin2 = this;
                final boolean b2 = cMakeArgumentImplMixin2.isEndMacroName();
                if (b2) {
                    return CMakeCompletionUtils.getRoutineIcon(false);
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return super.getIcon(n);
    }
    
    @NotNull
    public String getName() {
        String value;
        try {
            value = this.getValue();
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return value;
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        CMakeRenameUtils.renameArgument(this.getProject(), s, this.getNode());
        return (PsiElement)this;
    }
    
    @NotNull
    @Override
    public String getValue() {
        final CMakeLiteral cMakeLiteral = this.getCMakeLiteral();
        String valueFromCMakeLiteralNoEval = null;
        Label_0057: {
            String s = null;
            Label_0022: {
                try {
                    if (cMakeLiteral != null) {
                        break Label_0057;
                    }
                    s = "";
                    if (s == null) {
                        break Label_0022;
                    }
                    return s;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    s = "";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin", "getValue"));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return s;
            try {
                valueFromCMakeLiteralNoEval = CMakeArgumentManipulator.getValueFromCMakeLiteralNoEval(cMakeLiteral.getText());
                if (valueFromCMakeLiteralNoEval == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin", "getValue"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return valueFromCMakeLiteralNoEval;
    }
    
    @NotNull
    @Override
    public TextRange getContentsRange() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.getLiteralNotNull:()Lcom/intellij/psi/PsiElement;
        //     4: astore_1       
        //     5: aload_1        
        //     6: invokeinterface com/intellij/psi/PsiElement.getStartOffsetInParent:()I
        //    11: istore_2       
        //    12: new             Lcom/intellij/openapi/util/TextRange;
        //    15: dup            
        //    16: iload_2        
        //    17: iload_2        
        //    18: aload_1        
        //    19: invokeinterface com/intellij/psi/PsiElement.getTextLength:()I
        //    24: iadd           
        //    25: invokespecial   com/intellij/openapi/util/TextRange.<init>:(II)V
        //    28: astore_3       
        //    29: aload_0        
        //    30: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.isBracketArgument:()Z
        //    33: ifeq            127
        //    36: aload_1        
        //    37: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //    42: astore          4
        //    44: aload           4
        //    46: ldc             "\""
        //    48: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    51: ifeq            127
        //    54: aload           4
        //    56: ldc             "\""
        //    58: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //    61: ifeq            127
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    70: athrow         
        //    71: aload_3        
        //    72: iconst_1       
        //    73: invokevirtual   com/intellij/openapi/util/TextRange.shiftRight:(I)Lcom/intellij/openapi/util/TextRange;
        //    76: bipush          -2
        //    78: invokevirtual   com/intellij/openapi/util/TextRange.grown:(I)Lcom/intellij/openapi/util/TextRange;
        //    81: dup            
        //    82: ifnonnull       126
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    91: athrow         
        //    92: new             Ljava/lang/IllegalStateException;
        //    95: dup            
        //    96: ldc             "@NotNull method %s.%s must not return null"
        //    98: ldc             2
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "getContentsRange"
        //   114: aastore        
        //   115: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   118: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   121: athrow         
        //   122: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   125: athrow         
        //   126: areturn        
        //   127: aload_3        
        //   128: dup            
        //   129: ifnonnull       166
        //   132: new             Ljava/lang/IllegalStateException;
        //   135: dup            
        //   136: ldc             "@NotNull method %s.%s must not return null"
        //   138: ldc             2
        //   140: anewarray       Ljava/lang/Object;
        //   143: dup            
        //   144: ldc             0
        //   146: ldc             "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin"
        //   148: aastore        
        //   149: dup            
        //   150: ldc             1
        //   152: ldc             "getContentsRange"
        //   154: aastore        
        //   155: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   158: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   161: athrow         
        //   162: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   165: athrow         
        //   166: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  44     64     67     71     Lcom/intellij/util/IncorrectOperationException;
        //  54     85     88     92     Lcom/intellij/util/IncorrectOperationException;
        //  71     122    122    126    Lcom/intellij/util/IncorrectOperationException;
        //  127    162    162    166    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0071:
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
    
    @Override
    public boolean isFunctionName() {
        final CMakeCommandArguments parentCommandArguments = this.getParentCommandArguments();
        Label_0031: {
            try {
                if (!parentCommandArguments.isFunctionDeclarationParameters()) {
                    return false;
                }
                final CMakeCommandArguments cMakeCommandArguments = parentCommandArguments;
                final CMakeArgument cMakeArgument = cMakeCommandArguments.getFirstArgument();
                final CMakeArgumentImplMixin cMakeArgumentImplMixin = this;
                if (cMakeArgument == cMakeArgumentImplMixin) {
                    break Label_0031;
                }
                return false;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final CMakeCommandArguments cMakeCommandArguments = parentCommandArguments;
                final CMakeArgument cMakeArgument = cMakeCommandArguments.getFirstArgument();
                final CMakeArgumentImplMixin cMakeArgumentImplMixin = this;
                if (cMakeArgument == cMakeArgumentImplMixin) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean isEndFunctionName() {
        final CMakeCommandArguments parentCommandArguments = this.getParentCommandArguments();
        Label_0031: {
            try {
                if (!parentCommandArguments.isEndFunctionParameters()) {
                    return false;
                }
                final CMakeCommandArguments cMakeCommandArguments = parentCommandArguments;
                final CMakeArgument cMakeArgument = cMakeCommandArguments.getFirstArgument();
                final CMakeArgumentImplMixin cMakeArgumentImplMixin = this;
                if (cMakeArgument == cMakeArgumentImplMixin) {
                    break Label_0031;
                }
                return false;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final CMakeCommandArguments cMakeCommandArguments = parentCommandArguments;
                final CMakeArgument cMakeArgument = cMakeCommandArguments.getFirstArgument();
                final CMakeArgumentImplMixin cMakeArgumentImplMixin = this;
                if (cMakeArgument == cMakeArgumentImplMixin) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean isMacroName() {
        final CMakeCommandArguments parentCommandArguments = this.getParentCommandArguments();
        Label_0031: {
            try {
                if (!parentCommandArguments.isMacroDeclarationParameters()) {
                    return false;
                }
                final CMakeCommandArguments cMakeCommandArguments = parentCommandArguments;
                final CMakeArgument cMakeArgument = cMakeCommandArguments.getFirstArgument();
                final CMakeArgumentImplMixin cMakeArgumentImplMixin = this;
                if (cMakeArgument == cMakeArgumentImplMixin) {
                    break Label_0031;
                }
                return false;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final CMakeCommandArguments cMakeCommandArguments = parentCommandArguments;
                final CMakeArgument cMakeArgument = cMakeCommandArguments.getFirstArgument();
                final CMakeArgumentImplMixin cMakeArgumentImplMixin = this;
                if (cMakeArgument == cMakeArgumentImplMixin) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean isEndMacroName() {
        final CMakeCommandArguments parentCommandArguments = this.getParentCommandArguments();
        Label_0031: {
            try {
                if (!parentCommandArguments.isEndMacroParameters()) {
                    return false;
                }
                final CMakeCommandArguments cMakeCommandArguments = parentCommandArguments;
                final CMakeArgument cMakeArgument = cMakeCommandArguments.getFirstArgument();
                final CMakeArgumentImplMixin cMakeArgumentImplMixin = this;
                if (cMakeArgument == cMakeArgumentImplMixin) {
                    break Label_0031;
                }
                return false;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final CMakeCommandArguments cMakeCommandArguments = parentCommandArguments;
                final CMakeArgument cMakeArgument = cMakeCommandArguments.getFirstArgument();
                final CMakeArgumentImplMixin cMakeArgumentImplMixin = this;
                if (cMakeArgument == cMakeArgumentImplMixin) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean isCommandDefinitionName() {
        Label_0021: {
            try {
                if (this.isFunctionName()) {
                    break Label_0021;
                }
                final CMakeArgumentImplMixin cMakeArgumentImplMixin = this;
                final boolean b = cMakeArgumentImplMixin.isMacroName();
                if (b) {
                    break Label_0021;
                }
                return false;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final CMakeArgumentImplMixin cMakeArgumentImplMixin = this;
                final boolean b = cMakeArgumentImplMixin.isMacroName();
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean isEndCommandDefinitionName() {
        return this.isEndCommandDefinitionName(null);
    }
    
    @Override
    public boolean isEndCommandDefinitionName(@Nullable final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.isEndFunctionName:()Z
        //     4: ifne            21
        //     7: aload_0        
        //     8: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.isEndMacroName:()Z
        //    11: ifeq            123
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    20: athrow         
        //    21: aload_0        
        //    22: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.getParentCommandArguments:()Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeCommandArguments;
        //    25: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeCommandArguments.getParent:()Lcom/intellij/psi/PsiElement;
        //    30: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    35: checkcast       Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeRoutine;
        //    38: astore_2       
        //    39: aload_2        
        //    40: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeRoutine.getFirstArgument:()Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;
        //    45: astore_3       
        //    46: aload_1        
        //    47: ifnull          90
        //    50: aload_0        
        //    51: aload_3        
        //    52: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReferenceHelper.commandNamesEqualByText:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Z
        //    55: ifne            80
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    64: athrow         
        //    65: aload_3        
        //    66: aload_1        
        //    67: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReferenceHelper.commandNamesEqualByText:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Z
        //    70: ifeq            88
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    79: athrow         
        //    80: iconst_1       
        //    81: goto            89
        //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: iconst_0       
        //    89: ireturn        
        //    90: aload_0        
        //    91: aload_3        
        //    92: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReferenceHelper.commandNamesEqualByText:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Z
        //    95: ifne            113
        //    98: aload_0        
        //    99: aload_3        
        //   100: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.a:(Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;)Z
        //   103: ifeq            121
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   112: athrow         
        //   113: iconst_1       
        //   114: goto            122
        //   117: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   120: athrow         
        //   121: iconst_0       
        //   122: ireturn        
        //   123: iconst_0       
        //   124: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      14     17     21     Lcom/intellij/util/IncorrectOperationException;
        //  46     58     61     65     Lcom/intellij/util/IncorrectOperationException;
        //  50     73     76     80     Lcom/intellij/util/IncorrectOperationException;
        //  65     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  90     106    109    113    Lcom/intellij/util/IncorrectOperationException;
        //  98     117    117    121    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    
    private boolean a(@Nullable final CMakeArgument p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          73
        //     4: aload_0        
        //     5: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.getText:()Ljava/lang/String;
        //     8: ldc             "IntellijIdeaRulezzz"
        //    10: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //    13: ifeq            73
        //    16: goto            23
        //    19: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    22: athrow         
        //    23: aload_1        
        //    24: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeArgument.getText:()Ljava/lang/String;
        //    29: getstatic       java/util/Locale.US:Ljava/util/Locale;
        //    32: invokevirtual   java/lang/String.toLowerCase:(Ljava/util/Locale;)Ljava/lang/String;
        //    35: aload_0        
        //    36: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.getText:()Ljava/lang/String;
        //    39: ldc             "IntellijIdeaRulezzz"
        //    41: ldc             ""
        //    43: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //    46: getstatic       java/util/Locale.US:Ljava/util/Locale;
        //    49: invokevirtual   java/lang/String.toLowerCase:(Ljava/util/Locale;)Ljava/lang/String;
        //    52: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    55: ifeq            73
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    64: athrow         
        //    65: iconst_1       
        //    66: goto            74
        //    69: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    72: athrow         
        //    73: iconst_0       
        //    74: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      16     19     23     Lcom/intellij/util/IncorrectOperationException;
        //  4      58     61     65     Lcom/intellij/util/IncorrectOperationException;
        //  23     69     69     73     Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
    @Override
    public CMakeArgument getCommandDefinitionName() {
        try {
            if (this.isCommandDefinitionName()) {
                return this;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (!this.isEndCommandDefinitionName() || !this.getParentCommandArguments().isEndRoutineParameters()) {
                return null;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final CMakeArgument firstArgument = ((CMakeRoutine)this.getParentCommandArguments().getParent().getParent()).getFirstArgument();
        try {
            if (firstArgument != null) {
                return firstArgument;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    @Nullable
    @Override
    public CMakeArgument getNextArgument() {
        return (CMakeArgument)PsiTreeUtil.getNextSiblingOfType((PsiElement)this, (Class)CMakeArgument.class);
    }
    
    @Nullable
    @Override
    public CMakeArgument getPreviousArgument() {
        return (CMakeArgument)PsiTreeUtil.getPrevSiblingOfType((PsiElement)this, (Class)CMakeArgument.class);
    }
    
    @NotNull
    @Override
    public CMakeCommandArguments getParentCommandArguments() {
        final CMakeCommandArguments cMakeCommandArguments = (CMakeCommandArguments)PsiTreeUtil.getParentOfType((PsiElement)this, (Class)CMakeCommandArguments.class, true);
        CMakeCommandArguments cMakeCommandArguments3 = null;
        Label_0040: {
            Label_0028: {
                try {
                    if (CMakeArgumentImplMixin.$assertionsDisabled) {
                        break Label_0040;
                    }
                    final CMakeCommandArguments cMakeCommandArguments2 = cMakeCommandArguments;
                    if (cMakeCommandArguments2 == null) {
                        break Label_0028;
                    }
                    break Label_0040;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final CMakeCommandArguments cMakeCommandArguments2 = cMakeCommandArguments;
                    if (cMakeCommandArguments2 == null) {
                        throw new AssertionError();
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            try {
                cMakeCommandArguments3 = cMakeCommandArguments;
                if (cMakeCommandArguments3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin", "getParentCommandArguments"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return cMakeCommandArguments3;
    }
    
    @NotNull
    public ItemPresentation getPresentation() {
        CMakeArgumentPresentation cMakeArgumentPresentation;
        try {
            cMakeArgumentPresentation = new CMakeArgumentPresentation(this);
            if (cMakeArgumentPresentation == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentImplMixin", "getPresentation"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return (ItemPresentation)cMakeArgumentPresentation;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!CMakeArgumentImplMixin.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
