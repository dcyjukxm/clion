// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy;

import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.ide.hierarchy.HierarchyTreeStructure;
import com.intellij.ide.util.treeView.AlphaComparator;
import com.intellij.ide.util.treeView.NodeDescriptor;
import java.util.Comparator;
import com.intellij.ide.hierarchy.HierarchyNodeDescriptor;
import javax.swing.JPanel;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import javax.swing.JTree;
import java.util.Map;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.ide.hierarchy.TypeHierarchyBrowserBase;

public class OCTypeHierarchyBrowser extends TypeHierarchyBrowserBase
{
    private static final Logger LOG;
    private boolean myIsImplementation;
    
    public OCTypeHierarchyBrowser(final Project project, final OCElement ocElement) {
        super(project, (PsiElement)ocElement);
    }
    
    public boolean isImplementation() {
        return this.myIsImplementation;
    }
    
    @Override
    protected boolean isInterface(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProtocol;
        //     4: ifne            35
        //     7: aload_1        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //    11: ifne            35
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyBrowser.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_1        
        //    22: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    25: ifeq            43
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyBrowser.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: iconst_1       
        //    36: goto            44
        //    39: invokestatic    com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyBrowser.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: iconst_0       
        //    44: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     39     39     43     Ljava/lang/IllegalArgumentException;
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
    
    @Override
    protected void setHierarchyBase(@NotNull final PsiElement hierarchyBase) {
        try {
            if (hierarchyBase == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyBrowser", "setHierarchyBase"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        boolean myIsImplementation = false;
        Label_0085: {
            Label_0076: {
                try {
                    super.setHierarchyBase(hierarchyBase);
                    if (!(hierarchyBase instanceof OCFile)) {
                        break Label_0076;
                    }
                    final PsiElement psiElement = hierarchyBase;
                    final OCFile ocFile = (OCFile)psiElement;
                    final boolean b = ocFile.isHeader();
                    if (!b) {
                        break Label_0076;
                    }
                    break Label_0076;
                }
                catch (IllegalArgumentException ex2) {
                    throw d(ex2);
                }
                try {
                    final PsiElement psiElement = hierarchyBase;
                    final OCFile ocFile = (OCFile)psiElement;
                    final boolean b = ocFile.isHeader();
                    if (!b) {
                        myIsImplementation = true;
                        break Label_0085;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw d(ex3);
                }
            }
            myIsImplementation = false;
        }
        this.myIsImplementation = myIsImplementation;
    }
    
    @Override
    protected String getContentDisplayName(@NotNull String s, @NotNull final PsiElement psiElement) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeName", "com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyBrowser", "getContentDisplayName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyBrowser", "getContentDisplayName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        Label_0115: {
            try {
                if (!(psiElement instanceof PsiFile)) {
                    return super.getContentDisplayName(s, psiElement);
                }
                if (s != OCTypeHierarchyBrowser.SUPERTYPES_HIERARCHY_TYPE) {
                    break Label_0115;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw d(ex3);
            }
            s = "Files included from {0}";
            return super.getContentDisplayName(s, psiElement);
        }
        s = "Files including {0}";
        return super.getContentDisplayName(s, psiElement);
    }
    
    @Override
    protected void createTrees(@NotNull final Map<String, JTree> map) {
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "trees", "com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyBrowser", "createTrees"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        this.createTreeAndSetupCommonActions(map, (ActionGroup)new DefaultActionGroup());
    }
    
    @Override
    protected JPanel createLegendPanel() {
        return null;
    }
    
    @Override
    protected PsiElement getElementFromDescriptor(@NotNull final HierarchyNodeDescriptor hierarchyNodeDescriptor) {
        try {
            if (hierarchyNodeDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyBrowser", "getElementFromDescriptor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (!(hierarchyNodeDescriptor instanceof OCClassHierarchyNodeDescriptor)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return ((OCClassHierarchyNodeDescriptor)hierarchyNodeDescriptor).getType();
    }
    
    @Override
    protected boolean isApplicableElement(@NotNull final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyBrowser"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isApplicableElement"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyBrowser.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    48: ifne            79
        //    51: aload_1        
        //    52: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //    55: ifne            79
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyBrowser.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_1        
        //    66: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    69: ifeq            87
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyBrowser.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: iconst_1       
        //    80: goto            88
        //    83: invokestatic    com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyBrowser.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    86: athrow         
        //    87: iconst_0       
        //    88: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     58     61     65     Ljava/lang/IllegalArgumentException;
        //  51     72     75     79     Ljava/lang/IllegalArgumentException;
        //  65     83     83     87     Ljava/lang/IllegalArgumentException;
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
    
    @Override
    protected Comparator<NodeDescriptor> getComparator() {
        return (Comparator<NodeDescriptor>)AlphaComparator.INSTANCE;
    }
    
    @Override
    protected HierarchyTreeStructure createHierarchyTreeStructure(@NotNull final String s, @NotNull final PsiElement psiElement) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeName", "com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyBrowser", "createHierarchyTreeStructure"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyBrowser", "createHierarchyTreeStructure"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        Label_0326: {
            Label_0290: {
                Label_0266: {
                    Label_0236: {
                        Label_0216: {
                            Label_0192: {
                                Label_0132: {
                                    Label_0112: {
                                        try {
                                            if (!(psiElement instanceof OCClassDeclaration)) {
                                                break Label_0192;
                                            }
                                            final String s2 = OCTypeHierarchyBrowser.SUPERTYPES_HIERARCHY_TYPE;
                                            final String s3 = s;
                                            final boolean b = s2.equals(s3);
                                            if (b) {
                                                break Label_0112;
                                            }
                                            break Label_0132;
                                        }
                                        catch (IllegalArgumentException ex3) {
                                            throw d(ex3);
                                        }
                                        try {
                                            final String s2 = OCTypeHierarchyBrowser.SUPERTYPES_HIERARCHY_TYPE;
                                            final String s3 = s;
                                            final boolean b = s2.equals(s3);
                                            if (b) {
                                                return new OCSuperTypesHierarchyTreeStructure(this.myProject, (OCClassDeclaration)psiElement);
                                            }
                                        }
                                        catch (IllegalArgumentException ex4) {
                                            throw d(ex4);
                                        }
                                    }
                                    try {
                                        if (OCTypeHierarchyBrowser.SUBTYPES_HIERARCHY_TYPE.equals(s)) {
                                            return new OCSubTypesHierarchyTreeStructure(this.myProject, (OCClassDeclaration)psiElement);
                                        }
                                    }
                                    catch (IllegalArgumentException ex5) {
                                        throw d(ex5);
                                    }
                                }
                                try {
                                    if (OCTypeHierarchyBrowser.TYPE_HIERARCHY_TYPE.equals(s)) {
                                        return new OCTypeHierarchyTreeStructure(this.myProject, (OCClassDeclaration)psiElement);
                                    }
                                    break Label_0326;
                                }
                                catch (IllegalArgumentException ex6) {
                                    throw d(ex6);
                                }
                                try {
                                    if (!(psiElement instanceof OCStruct)) {
                                        break Label_0266;
                                    }
                                    final String s4 = OCTypeHierarchyBrowser.SUPERTYPES_HIERARCHY_TYPE;
                                    final String s5 = s;
                                    final boolean b2 = s4.equals(s5);
                                    if (b2) {
                                        break Label_0216;
                                    }
                                    break Label_0236;
                                }
                                catch (IllegalArgumentException ex7) {
                                    throw d(ex7);
                                }
                            }
                            try {
                                final String s4 = OCTypeHierarchyBrowser.SUPERTYPES_HIERARCHY_TYPE;
                                final String s5 = s;
                                final boolean b2 = s4.equals(s5);
                                if (b2) {
                                    return new OCStructSuperTypesHierarchyTreeStructure(this.myProject, (OCStruct)psiElement);
                                }
                            }
                            catch (IllegalArgumentException ex8) {
                                throw d(ex8);
                            }
                        }
                        try {
                            if (OCTypeHierarchyBrowser.SUBTYPES_HIERARCHY_TYPE.equals(s)) {
                                return new OCStructSubTypesHierarchyTreeStructure(this.myProject, (OCStruct)psiElement);
                            }
                            break Label_0326;
                        }
                        catch (IllegalArgumentException ex9) {
                            throw d(ex9);
                        }
                    }
                    try {
                        if (!(psiElement instanceof OCFile)) {
                            break Label_0326;
                        }
                        final String s6 = OCTypeHierarchyBrowser.SUPERTYPES_HIERARCHY_TYPE;
                        final String s7 = s;
                        final boolean b3 = s6.equals(s7);
                        if (b3) {
                            break Label_0290;
                        }
                        return new OCImportSuperHierarchyTreeStructure(this.myProject, (OCFile)psiElement);
                    }
                    catch (IllegalArgumentException ex10) {
                        throw d(ex10);
                    }
                }
                try {
                    final String s6 = OCTypeHierarchyBrowser.SUPERTYPES_HIERARCHY_TYPE;
                    final String s7 = s;
                    final boolean b3 = s6.equals(s7);
                    if (b3) {
                        return new OCImportSubHierarchyTreeStructure(this.myProject, (OCFile)psiElement);
                    }
                }
                catch (IllegalArgumentException ex11) {
                    throw d(ex11);
                }
            }
            return new OCImportSuperHierarchyTreeStructure(this.myProject, (OCFile)psiElement);
        }
        OCTypeHierarchyBrowser.LOG.error("unexpected type: " + s);
        return null;
    }
    
    @Override
    protected boolean canBeDeleted(final PsiElement psiElement) {
        return psiElement instanceof OCClassDeclaration;
    }
    
    @Override
    protected String getQualifiedName(final PsiElement psiElement) {
        try {
            if (psiElement instanceof OCClassDeclaration) {
                return ((OCClassDeclaration)psiElement).getCanonicalName();
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        if (psiElement instanceof OCSymbolDeclarator) {
            final OCSymbolWithQualifiedName symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
            if (symbol instanceof OCSymbolWithQualifiedName) {
                final OCQualifiedName resolvedQualifiedName = symbol.getResolvedQualifiedName();
                try {
                    if (resolvedQualifiedName != null) {
                        return resolvedQualifiedName.toString();
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw d(ex2);
                }
                return symbol.getQualifiedName().toString();
            }
        }
        return "";
    }
    
    static {
        LOG = Logger.getInstance("#com.intellij.lang.javascript.hierarchy.type.jsclass.JSTypeHierarchyBrowser");
    }
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
}
