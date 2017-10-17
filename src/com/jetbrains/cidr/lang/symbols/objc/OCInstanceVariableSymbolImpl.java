// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import java.io.Serializable;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.ARCAttribute;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.OCIcons;
import javax.swing.Icon;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.intellij.util.CommonProcessors;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.types.OCType;

public class OCInstanceVariableSymbolImpl extends OCMemberSymbolImpl implements OCInstanceVariableSymbol
{
    private OCType myType;
    private String myGeneratedFromProperty;
    private OCVisibility myVisibility;
    
    public OCInstanceVariableSymbolImpl() {
    }
    
    public OCInstanceVariableSymbolImpl(final Project project, final VirtualFile virtualFile, final long n, final String s, @NotNull final List<String> list, final OCClassSymbol ocClassSymbol, @NotNull final OCType myType, @NotNull final OCVisibility myVisibility, @Nullable final String myGeneratedFromProperty) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "<init>"));
        }
        if (myType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "<init>"));
        }
        if (myVisibility == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visibility", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "<init>"));
        }
        super(project, virtualFile, n, s, list, ocClassSymbol);
        this.myType = myType;
        this.myVisibility = myVisibility;
        this.myGeneratedFromProperty = myGeneratedFromProperty;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        final OCInstanceVariableSymbolImpl ocInstanceVariableSymbolImpl = (OCInstanceVariableSymbolImpl)o;
        final OCInstanceVariableSymbolImpl ocInstanceVariableSymbolImpl2 = (OCInstanceVariableSymbolImpl)o2;
        try {
            if (ocInstanceVariableSymbolImpl.myVisibility != ocInstanceVariableSymbolImpl2.myVisibility) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw c(ex5);
        }
        try {
            if (!Comparing.equal(ocInstanceVariableSymbolImpl.myGeneratedFromProperty, ocInstanceVariableSymbolImpl.myGeneratedFromProperty)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw c(ex6);
        }
        try {
            if (!comparator.equalObjects(ocInstanceVariableSymbolImpl.myType, (DeepEqual.Equality<Object>)ocInstanceVariableSymbolImpl2.myType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw c(ex7);
        }
        return true;
    }
    
    @NotNull
    @Override
    public OCType getType() {
        if (this.myGeneratedFromProperty != null) {
            final OCInterfaceSymbol interface1 = this.getParent().getInterface();
            Label_0071: {
                OCType ocType = null;
                Label_0036: {
                    try {
                        if (interface1 != null) {
                            break Label_0071;
                        }
                        final OCInstanceVariableSymbolImpl ocInstanceVariableSymbolImpl = this;
                        ocType = ocInstanceVariableSymbolImpl.myType;
                        if (ocType == null) {
                            break Label_0036;
                        }
                        return ocType;
                    }
                    catch (IllegalArgumentException ex) {
                        throw c(ex);
                    }
                    try {
                        final OCInstanceVariableSymbolImpl ocInstanceVariableSymbolImpl = this;
                        ocType = ocInstanceVariableSymbolImpl.myType;
                        if (ocType == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "getType"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw c(ex2);
                    }
                }
                return ocType;
            }
            final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
            final OCType resolvedType = this.getParent().getResolvedType();
            OCType myType = null;
            Label_0228: {
                OCType ocType3 = null;
                Label_0193: {
                    Label_0146: {
                        OCType ocType2 = null;
                        Label_0111: {
                            try {
                                if (resolvedType instanceof OCObjectType) {
                                    break Label_0146;
                                }
                                final OCInstanceVariableSymbolImpl ocInstanceVariableSymbolImpl2 = this;
                                ocType2 = ocInstanceVariableSymbolImpl2.myType;
                                if (ocType2 == null) {
                                    break Label_0111;
                                }
                                return ocType2;
                            }
                            catch (IllegalArgumentException ex3) {
                                throw c(ex3);
                            }
                            try {
                                final OCInstanceVariableSymbolImpl ocInstanceVariableSymbolImpl2 = this;
                                ocType2 = ocInstanceVariableSymbolImpl2.myType;
                                if (ocType2 == null) {
                                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "getType"));
                                }
                            }
                            catch (IllegalArgumentException ex4) {
                                throw c(ex4);
                            }
                        }
                        return ocType2;
                        try {
                            ((OCObjectType)resolvedType).processMembers(this.myGeneratedFromProperty, OCPropertySymbol.class, (com.intellij.util.Processor<? super OCPropertySymbol>)findFirstProcessor, true, false);
                            if (findFirstProcessor.getFoundValue() == null) {
                                break Label_0228;
                            }
                            final CommonProcessors.FindFirstProcessor findFirstProcessor2 = findFirstProcessor;
                            final Object o = findFirstProcessor2.getFoundValue();
                            final OCPropertySymbol ocPropertySymbol = (OCPropertySymbol)o;
                            ocType3 = ocPropertySymbol.getType();
                            if (ocType3 == null) {
                                break Label_0193;
                            }
                            return ocType3;
                        }
                        catch (IllegalArgumentException ex5) {
                            throw c(ex5);
                        }
                    }
                    try {
                        final CommonProcessors.FindFirstProcessor findFirstProcessor2 = findFirstProcessor;
                        final Object o = findFirstProcessor2.getFoundValue();
                        final OCPropertySymbol ocPropertySymbol = (OCPropertySymbol)o;
                        ocType3 = ocPropertySymbol.getType();
                        if (ocType3 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "getType"));
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw c(ex6);
                    }
                }
                return ocType3;
                try {
                    myType = this.myType;
                    if (myType == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "getType"));
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw c(ex7);
                }
            }
            return myType;
        }
        OCType myType2;
        try {
            myType2 = this.myType;
            if (myType2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex8) {
            throw c(ex8);
        }
        return myType2;
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind instance_VARIABLE;
        try {
            instance_VARIABLE = OCSymbolKind.INSTANCE_VARIABLE;
            if (instance_VARIABLE == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return instance_VARIABLE;
    }
    
    @NotNull
    @Override
    public OCVisibility getVisibility() {
        OCVisibility myVisibility;
        try {
            myVisibility = this.myVisibility;
            if (myVisibility == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "getVisibility"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return myVisibility;
    }
    
    @Override
    public void updateVisibility(@NotNull final OCVisibility myVisibility) {
        try {
            if (myVisibility == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visibility", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "updateVisibility"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        this.myVisibility = myVisibility;
    }
    
    @Override
    public Icon getBaseIcon() {
        return OCIcons.getVisibilityIcon(this.getVisibility(), super.getBaseIcon());
    }
    
    @NotNull
    @Override
    public ARCAttribute getARCAttribute(final PsiElement psiElement) {
        final OCType resolvedType = this.getResolvedType();
        if (this.myGeneratedFromProperty != null) {
            final OCPropertySymbol associatedProperty = this.getAssociatedProperty();
            ARCAttribute default1 = null;
            Label_0081: {
                ARCAttribute arcAttribute = null;
                Label_0046: {
                    try {
                        if (associatedProperty == null) {
                            break Label_0081;
                        }
                        final OCPropertySymbol ocPropertySymbol = associatedProperty;
                        final OCPropertySymbol.PropertyAttribute propertyAttribute = OCPropertySymbol.PropertyAttribute.ASSIGN;
                        final OCType ocType = resolvedType;
                        final PsiElement psiElement2 = psiElement;
                        final OCPropertySymbol.PropertyAttribute propertyAttribute2 = ocPropertySymbol.getAttributeOfGroup(propertyAttribute, ocType, psiElement2);
                        arcAttribute = propertyAttribute2.getIvarCompatibleARCAttribute();
                        if (arcAttribute == null) {
                            break Label_0046;
                        }
                        return arcAttribute;
                    }
                    catch (IllegalArgumentException ex) {
                        throw c(ex);
                    }
                    try {
                        final OCPropertySymbol ocPropertySymbol = associatedProperty;
                        final OCPropertySymbol.PropertyAttribute propertyAttribute = OCPropertySymbol.PropertyAttribute.ASSIGN;
                        final OCType ocType = resolvedType;
                        final PsiElement psiElement2 = psiElement;
                        final OCPropertySymbol.PropertyAttribute propertyAttribute2 = ocPropertySymbol.getAttributeOfGroup(propertyAttribute, ocType, psiElement2);
                        arcAttribute = propertyAttribute2.getIvarCompatibleARCAttribute();
                        if (arcAttribute == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "getARCAttribute"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw c(ex2);
                    }
                }
                return arcAttribute;
                try {
                    default1 = ARCAttribute.DEFAULT;
                    if (default1 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "getARCAttribute"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
            }
            return default1;
        }
        ARCAttribute arcAttribute2 = null;
        Label_0145: {
            try {
                if (resolvedType instanceof OCPointerType) {
                    arcAttribute2 = ((OCPointerType)resolvedType).getARCAttribute();
                    break Label_0145;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
            arcAttribute2 = null;
        }
        final ARCAttribute arcAttribute3 = arcAttribute2;
        ARCAttribute arcAttribute4 = null;
        Label_0161: {
            try {
                if (arcAttribute3 != null) {
                    final ARCAttribute default2;
                    arcAttribute4 = (default2 = arcAttribute3);
                    break Label_0161;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw c(ex5);
            }
            ARCAttribute default2;
            arcAttribute4 = (default2 = ARCAttribute.DEFAULT);
            try {
                if (default2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl", "getARCAttribute"));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw c(ex6);
            }
        }
        return arcAttribute4;
    }
    
    @Override
    protected Class<? extends PsiElement> getPsiElementClass() {
        try {
            if (this.myGeneratedFromProperty != null) {
                final Serializable psiElementClass = OCReferenceElement.class;
                return (Class<? extends PsiElement>)psiElementClass;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final Serializable psiElementClass = super.getPsiElementClass();
        return (Class<? extends PsiElement>)psiElementClass;
    }
    
    @Override
    public String getGeneratedFromProperty() {
        return this.myGeneratedFromProperty;
    }
    
    @Override
    public boolean isStatic() {
        return false;
    }
    
    @Override
    public boolean processSynthesizes(final Processor<OCSynthesizeSymbol> processor) {
        final OCImplementationSymbol mainImplementation = this.getParent().getMainImplementation();
        try {
            if (mainImplementation == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return mainImplementation.processMembers(OCSynthesizeSymbol.class, (com.intellij.util.Processor<? super OCSynthesizeSymbol>)(ocSynthesizeSymbol -> {
            Label_0031: {
                try {
                    if (!this.getName().equals(ocSynthesizeSymbol.getIvarName())) {
                        break Label_0031;
                    }
                    final Processor processor2 = processor;
                    final OCSynthesizeSymbol ocSynthesizeSymbol2 = ocSynthesizeSymbol;
                    final boolean b = processor2.process((Object)ocSynthesizeSymbol2);
                    if (b) {
                        break Label_0031;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    final Processor processor2 = processor;
                    final OCSynthesizeSymbol ocSynthesizeSymbol2 = ocSynthesizeSymbol;
                    final boolean b = processor2.process((Object)ocSynthesizeSymbol2);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
            }
            return false;
        }));
    }
    
    @Nullable
    @Override
    public OCPropertySymbol getAssociatedProperty() {
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        try {
            this.processSynthesizes((Processor<OCSynthesizeSymbol>)findFirstProcessor);
            if (findFirstProcessor.isFound()) {
                return ((OCSynthesizeSymbol)findFirstProcessor.getFoundValue()).getAssociatedProperty();
            }
            if (!OCCompilerFeatures.supportsAutosynthesis((PsiFile)this.getContainingOCFile())) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final OCInterfaceSymbol interface1 = this.myParent.getInterface();
        final String clang4PropertyName = OCNameSuggester.getClang4PropertyName(this.myName);
        final CommonProcessors.FindFirstProcessor findFirstProcessor2 = new CommonProcessors.FindFirstProcessor();
        Label_0080: {
            try {
                if (clang4PropertyName == null) {
                    return (OCPropertySymbol)findFirstProcessor2.getFoundValue();
                }
                final OCInterfaceSymbol ocInterfaceSymbol = interface1;
                if (ocInterfaceSymbol != null) {
                    break Label_0080;
                }
                return (OCPropertySymbol)findFirstProcessor2.getFoundValue();
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            try {
                final OCInterfaceSymbol ocInterfaceSymbol = interface1;
                if (ocInterfaceSymbol != null) {
                    interface1.processMembers(clang4PropertyName, (Class<? extends OCMemberSymbol>)OCPropertySymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor2);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return (OCPropertySymbol)findFirstProcessor2.getFoundValue();
    }
    
    @Override
    public boolean isSynthetic() {
        Label_0025: {
            try {
                if (this.myGeneratedFromProperty == null) {
                    return false;
                }
                final OCInstanceVariableSymbolImpl ocInstanceVariableSymbolImpl = this;
                final long n = ocInstanceVariableSymbolImpl.myComplexOffset;
                final long n2 = 4294967296L;
                final long n3 = lcmp(n, n2);
                if (n3 == 0) {
                    break Label_0025;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCInstanceVariableSymbolImpl ocInstanceVariableSymbolImpl = this;
                final long n = ocInstanceVariableSymbolImpl.myComplexOffset;
                final long n2 = 4294967296L;
                final long n3 = lcmp(n, n2);
                if (n3 == 0) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean isClang4ImplicitIvar(@Nullable final PsiFile p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl.myGeneratedFromProperty:Ljava/lang/String;
        //     4: ifnull          47
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl.myComplexOffset:J
        //    11: ldc2_w          4294967296
        //    14: lcmp           
        //    15: ifne            47
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    24: athrow         
        //    25: aload_1        
        //    26: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsAutosynthesis:(Lcom/intellij/psi/PsiFile;)Z
        //    29: ifeq            47
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: iconst_1       
        //    40: goto            48
        //    43: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    46: athrow         
        //    47: iconst_0       
        //    48: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      18     21     25     Ljava/lang/IllegalArgumentException;
        //  7      32     35     39     Ljava/lang/IllegalArgumentException;
        //  25     43     43     47     Ljava/lang/IllegalArgumentException;
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
    
    @Override
    public boolean isClang4ImplicitIvar() {
        return this.isClang4ImplicitIvar((PsiFile)this.getContainingOCFile());
    }
    
    @Override
    public boolean isForbiddenClang4ImplicitIvar() {
        return this.isForbiddenClang4ImplicitIvar((PsiFile)this.getContainingOCFile());
    }
    
    @Override
    public boolean isForbiddenClang4ImplicitIvar(@Nullable final PsiFile psiFile) {
        Label_0055: {
            Label_0031: {
                try {
                    if (this.myGeneratedFromProperty == null) {
                        return false;
                    }
                    final OCInstanceVariableSymbolImpl ocInstanceVariableSymbolImpl = this;
                    final long n = ocInstanceVariableSymbolImpl.myComplexOffset;
                    final long n2 = 4294967296L;
                    final long n3 = lcmp(n, n2);
                    if (n3 != 0) {
                        return false;
                    }
                    break Label_0031;
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    final OCInstanceVariableSymbolImpl ocInstanceVariableSymbolImpl = this;
                    final long n = ocInstanceVariableSymbolImpl.myComplexOffset;
                    final long n2 = 4294967296L;
                    final long n3 = lcmp(n, n2);
                    if (n3 != 0) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
                try {
                    if (psiFile == null) {
                        break Label_0055;
                    }
                    final PsiFile psiFile2 = psiFile;
                    final boolean b = OCCompilerFeatures.supportsAutosynthesis(psiFile2);
                    if (!b) {
                        return true;
                    }
                    break Label_0055;
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
            }
            try {
                final PsiFile psiFile2 = psiFile;
                final boolean b = OCCompilerFeatures.supportsAutosynthesis(psiFile2);
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
            try {
                if (OCNameSuggester.getClang4PropertyName(this.myName) == null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw c(ex5);
            }
        }
        final OCPropertySymbol associatedProperty = this.getAssociatedProperty();
        Label_0097: {
            try {
                if (associatedProperty == null) {
                    return false;
                }
                final OCPropertySymbol ocPropertySymbol = associatedProperty;
                final boolean b2 = true;
                final boolean b3 = ocPropertySymbol.hasAllAccessorsImplemented(b2);
                if (b3) {
                    break Label_0097;
                }
                return false;
            }
            catch (IllegalArgumentException ex6) {
                throw c(ex6);
            }
            try {
                final OCPropertySymbol ocPropertySymbol = associatedProperty;
                final boolean b2 = true;
                final boolean b3 = ocPropertySymbol.hasAllAccessorsImplemented(b2);
                if (b3) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw c(ex7);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
