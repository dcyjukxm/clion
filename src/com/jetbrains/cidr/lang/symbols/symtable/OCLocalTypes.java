// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.UserDataHolder;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCTypeParameterDeclaration;
import com.jetbrains.cidr.lang.psi.impl.OCTemplateParameterListImpl;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCCppUsingStatement;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCCallable;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import java.util.HashSet;
import com.intellij.lang.annotation.AnnotationSession;
import com.intellij.openapi.util.NotNullLazyKey;
import java.util.Set;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

public class OCLocalTypes extends OCRecursiveVisitor
{
    private Set<String> myLocalNames;
    private int myLocalScopeDepth;
    private boolean myHasLocalUsing;
    private static NotNullLazyKey<OCLocalTypes, AnnotationSession> CACHE;
    
    private OCLocalTypes() {
        this.myLocalNames = new HashSet<String>();
    }
    
    @Override
    public void visitStructLike(final OCStructLike p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/psi/visitors/OCRecursiveVisitor.visitStructLike:(Lcom/jetbrains/cidr/lang/psi/OCStructLike;)V
        //     5: aload_0        
        //     6: getfield        com/jetbrains/cidr/lang/symbols/symtable/OCLocalTypes.myLocalScopeDepth:I
        //     9: ifle            111
        //    12: aload_1        
        //    13: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.isDeclaration:()Z
        //    18: ifeq            111
        //    21: goto            28
        //    24: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCLocalTypes.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    27: athrow         
        //    28: aload_0        
        //    29: getfield        com/jetbrains/cidr/lang/symbols/symtable/OCLocalTypes.myLocalNames:Ljava/util/Set;
        //    32: new             Ljava/lang/StringBuilder;
        //    35: dup            
        //    36: invokespecial   java/lang/StringBuilder.<init>:()V
        //    39: aload_1        
        //    40: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    45: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //    48: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    51: ldc             " "
        //    53: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    56: aload_1        
        //    57: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getName:()Ljava/lang/String;
        //    62: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    65: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    68: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //    73: pop            
        //    74: aload_1        
        //    75: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isInPlainOldC:(Lcom/intellij/psi/PsiElement;)Z
        //    78: ifne            111
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCLocalTypes.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/symbols/symtable/OCLocalTypes.myLocalNames:Ljava/util/Set;
        //    92: aload_1        
        //    93: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getName:()Ljava/lang/String;
        //    98: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   103: pop            
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCLocalTypes.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      21     24     28     Ljava/lang/IllegalArgumentException;
        //  12     81     84     88     Ljava/lang/IllegalArgumentException;
        //  28     104    107    111    Ljava/lang/IllegalArgumentException;
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
    
    @Override
    public void visitDeclaration(final OCDeclaration ocDeclaration) {
        try {
            super.visitDeclaration(ocDeclaration);
            if (this.myLocalScopeDepth <= 0 || !ocDeclaration.isTypedef()) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Iterator<OCDeclarator> iterator = ocDeclaration.getDeclarators().iterator();
        OCDeclarator ocDeclarator = null;
        Label_0066: {
            try {
                if (iterator.hasNext()) {
                    ocDeclarator = iterator.next();
                    break Label_0066;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            ocDeclarator = null;
        }
        final OCDeclarator ocDeclarator2 = ocDeclarator;
        try {
            if (ocDeclarator2 != null) {
                this.myLocalNames.add(ocDeclarator2.getName());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
    }
    
    private void a(final OCCallable ocCallable) {
        ++this.myLocalScopeDepth;
        this.visitElement((PsiElement)ocCallable);
        --this.myLocalScopeDepth;
    }
    
    @Override
    public void visitUsingStatement(final OCCppUsingStatement ocCppUsingStatement) {
        try {
            if (this.myLocalScopeDepth > 0) {
                this.myHasLocalUsing = true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    @Override
    public void visitMethod(final OCMethod ocMethod) {
        this.a(ocMethod);
    }
    
    @Override
    public void visitFunctionDefinition(final OCFunctionDefinition ocFunctionDefinition) {
        this.a(ocFunctionDefinition);
    }
    
    @Override
    public void visitTemplateParameterList(final OCTemplateParameterListImpl ocTemplateParameterListImpl) {
        final Iterator<OCTypeParameterDeclaration> iterator = ocTemplateParameterListImpl.getTypeParameterDeclarations().iterator();
        while (iterator.hasNext()) {
            this.myLocalNames.add(iterator.next().getName());
        }
    }
    
    @Override
    public void visitBlockExpression(final OCBlockExpression ocBlockExpression) {
        this.a(ocBlockExpression);
    }
    
    @Override
    public void visitLambdaExpression(final OCLambdaExpression ocLambdaExpression) {
        this.a(ocLambdaExpression);
    }
    
    @Nullable
    public static Boolean canBeLocalType(final OCFile ocFile, @NotNull final OCQualifiedName ocQualifiedName) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qn", "com/jetbrains/cidr/lang/symbols/symtable/OCLocalTypes", "canBeLocalType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        synchronized (ocFile.getAnnotationSessionLock()) {
            final AnnotationSession currentAnnotationSession = ocFile.getCurrentAnnotationSession();
            if (currentAnnotationSession != null) {
                final Set<String> myLocalNames = ((OCLocalTypes)OCLocalTypes.CACHE.getValue((UserDataHolder)currentAnnotationSession)).myLocalNames;
                OCQualifiedName qualifier = ocQualifiedName;
                while (true) {
                    Label_0116: {
                        try {
                            if (qualifier == null) {
                                return false;
                            }
                            if (!myLocalNames.contains(qualifier.getName())) {
                                break Label_0116;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        return true;
                    }
                    qualifier = qualifier.getQualifier();
                }
            }
        }
        return null;
    }
    
    public static boolean hasLocalUsingStatement(final OCFile ocFile) {
        synchronized (ocFile.getAnnotationSessionLock()) {
            final AnnotationSession currentAnnotationSession = ocFile.getCurrentAnnotationSession();
            if (currentAnnotationSession != null) {
                return ((OCLocalTypes)OCLocalTypes.CACHE.getValue((UserDataHolder)currentAnnotationSession)).myHasLocalUsing;
            }
        }
        return true;
    }
    
    static {
        OCLocalTypes.CACHE = (NotNullLazyKey<OCLocalTypes, AnnotationSession>)NotNullLazyKey.create("LOCAL_TYPES_DURING_ANNOTATION", annotationSession -> {
            final PsiFile file = annotationSession.getFile();
            final OCLocalTypes ocLocalTypes = new OCLocalTypes();
            file.accept((PsiElementVisitor)ocLocalTypes);
            return ocLocalTypes;
        });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
