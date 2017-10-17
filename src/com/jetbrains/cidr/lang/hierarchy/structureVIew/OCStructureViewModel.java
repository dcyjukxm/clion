// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.structureVIew;

import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.ide.util.treeView.smartTree.NodeProvider;
import java.util.Collection;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;

public class OCStructureViewModel extends StructureViewModelBase implements StructureViewModel.ElementInfoProvider
{
    private static final Collection<NodeProvider> NODE_PROVIDERS;
    
    public OCStructureViewModel(@NotNull final PsiFile psiFile, @Nullable final Editor editor) {
        if (psiFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiFile", "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewModel", "<init>"));
        }
        super(psiFile, editor, (StructureViewTreeElement)new OCStructureViewElement((PsiElement)psiFile, null));
        this.withSorters(new Sorter[] { OCMarkSorter.INSTANCE, OCKindSorter.INSTANCE, Sorter.ALPHA_SORTER });
        this.withSuitableClasses(new Class[] { OCFunctionDefinition.class, OCMethod.class, OCClassDeclaration.class });
    }
    
    public boolean isAlwaysShowsPlus(final StructureViewTreeElement structureViewTreeElement) {
        return false;
    }
    
    public boolean isAlwaysLeaf(final StructureViewTreeElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/ide/structureView/StructureViewTreeElement.getValue:()Ljava/lang/Object;
        //     6: astore_2       
        //     7: aload_2        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    11: ifne            42
        //    14: aload_2        
        //    15: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //    18: ifne            42
        //    21: goto            28
        //    24: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewModel.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    27: athrow         
        //    28: aload_2        
        //    29: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    32: ifeq            50
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewModel.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: iconst_1       
        //    43: goto            51
        //    46: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewModel.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: iconst_0       
        //    51: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      21     24     28     Ljava/lang/IllegalArgumentException;
        //  14     35     38     42     Ljava/lang/IllegalArgumentException;
        //  28     46     46     50     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
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
    
    public boolean shouldEnterElement(final Object p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCImplementation;
        //     4: ifne            63
        //     7: aload_1        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProtocol;
        //    11: ifne            63
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewModel.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_1        
        //    22: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    25: ifne            63
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewModel.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_1        
        //    36: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
        //    39: ifne            63
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewModel.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_1        
        //    50: instanceof      Lcom/jetbrains/cidr/lang/psi/OCInterface;
        //    53: ifeq            71
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewModel.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: iconst_1       
        //    64: goto            72
        //    67: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewModel.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: iconst_0       
        //    72: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     42     45     49     Ljava/lang/IllegalArgumentException;
        //  35     56     59     63     Ljava/lang/IllegalArgumentException;
        //  49     67     67     71     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    
    @NotNull
    public Collection<NodeProvider> getNodeProviders() {
        Collection<NodeProvider> node_PROVIDERS;
        try {
            node_PROVIDERS = OCStructureViewModel.NODE_PROVIDERS;
            if (node_PROVIDERS == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewModel", "getNodeProviders"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return node_PROVIDERS;
    }
    
    static {
        NODE_PROVIDERS = Collections.singleton(new OCInheritorsAndCategoriesNodeProvider());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
