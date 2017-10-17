// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.jetbrains.cidr.cpp.cmake.psi.util.CMakeFileLocationUtil;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.cpp.CPPLog;
import java.util.List;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.Nullable;
import javax.swing.Icon;
import com.jetbrains.cidr.cpp.cmake.resolve.CMakeCommandReference;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.cpp.cmake.resolve.CMakeRenameUtils;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public class CMakeRoutineCommandImplMixin extends CMakeElementBase implements CMakeRoutineCommandMixin
{
    public CMakeRoutineCommandImplMixin(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/cpp/cmake/psi/CMakeRoutineCommandImplMixin", "<init>"));
        }
        super(astNode);
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/psi/CMakeRoutineCommandImplMixin", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        CMakeRenameUtils.renameCommandCall(this.getProject(), s, this.getFirstChild().getNode());
        return (PsiElement)this;
    }
    
    @NotNull
    public PsiReference getReference() {
        final PsiElement firstChild = this.getFirstChild();
        CMakeCommandReference cMakeCommandReference;
        try {
            cMakeCommandReference = new CMakeCommandReference(firstChild, new TextRange(0, firstChild.getTextRange().getLength()));
            if (cMakeCommandReference == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeRoutineCommandImplMixin", "getReference"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return (PsiReference)cMakeCommandReference;
    }
    
    public String getName() {
        final CMakeArgument firstArgument = this.getFirstArgument();
        try {
            if (firstArgument != null) {
                return firstArgument.getText();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Nullable
    public Icon getIcon(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/CMakeRoutineCommandImplMixin.getFirstArgument:()Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;
        //     4: astore_2       
        //     5: aload_2        
        //     6: ifnull          54
        //     9: aload_2        
        //    10: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeArgument.isFunctionName:()Z
        //    15: ifne            41
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeRoutineCommandImplMixin.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    24: athrow         
        //    25: aload_2        
        //    26: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeArgument.isEndFunctionName:()Z
        //    31: ifeq            49
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeRoutineCommandImplMixin.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    40: athrow         
        //    41: iconst_1       
        //    42: goto            50
        //    45: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeRoutineCommandImplMixin.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    48: athrow         
        //    49: iconst_0       
        //    50: invokestatic    com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionUtils.getRoutineIcon:(Z)Ljavax/swing/Icon;
        //    53: areturn        
        //    54: aconst_null    
        //    55: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  5      18     21     25     Lcom/intellij/util/IncorrectOperationException;
        //  9      34     37     41     Lcom/intellij/util/IncorrectOperationException;
        //  25     45     45     49     Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0025:
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
    public CMakeArgument getFirstArgument() {
        final CMakeCommandArguments cMakeCommandArguments = (CMakeCommandArguments)PsiTreeUtil.findChildOfType((PsiElement)this, (Class)CMakeCommandArguments.class);
        try {
            if (cMakeCommandArguments == null) {
                final List<CMakeArgument> cMakeArgumentList = null;
                return (CMakeArgument)ContainerUtil.getFirstItem((List)cMakeArgumentList);
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final List<CMakeArgument> cMakeArgumentList = cMakeCommandArguments.getCMakeArgumentList();
        return (CMakeArgument)ContainerUtil.getFirstItem((List)cMakeArgumentList);
    }
    
    @NotNull
    @Override
    public CMakeArgument getFirstArgumentNotNull() {
        final CMakeArgument firstArgument = this.getFirstArgument();
        CMakeArgument cMakeArgument = null;
        Label_0021: {
            Logger log;
            try {
                log = CPPLog.LOG;
                if (firstArgument != null) {
                    final boolean b = true;
                    break Label_0021;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            final boolean b = false;
            try {
                log.assertTrue(b);
                cMakeArgument = firstArgument;
                if (cMakeArgument == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeRoutineCommandImplMixin", "getFirstArgumentNotNull"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return cMakeArgument;
    }
    
    public ItemPresentation getPresentation() {
        return (ItemPresentation)new ItemPresentation() {
            @Nullable
            public String getPresentableText() {
                final CMakeArgument firstArgument = CMakeRoutineCommandImplMixin.this.getFirstArgument();
                if (firstArgument instanceof CMakeArgumentImplMixin) {
                    return ((CMakeArgumentImplMixin)firstArgument).getPresentation().getPresentableText();
                }
                return CMakeRoutineCommandImplMixin.this.getText();
            }
            
            @Nullable
            public String getLocationString() {
                return CMakeFileLocationUtil.getLocationInFile((PsiElement)CMakeRoutineCommandImplMixin.this, false);
            }
            
            @Nullable
            public Icon getIcon(final boolean b) {
                return CMakeRoutineCommandImplMixin.this.getIcon(0);
            }
        };
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
