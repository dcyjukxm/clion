// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.DeepEqual;

public class OCCppReferenceType extends OCType
{
    private OCType myRefType;
    private boolean myRvalueRef;
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)o;
        final OCCppReferenceType ocCppReferenceType2 = (OCCppReferenceType)o2;
        try {
            if (ocCppReferenceType.myRvalueRef != ocCppReferenceType2.myRvalueRef) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!comparator.equalObjects(ocCppReferenceType.myRefType, (DeepEqual.Equality<Object>)ocCppReferenceType2.myRefType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return true;
    }
    
    public OCCppReferenceType() {
    }
    
    public static OCCppReferenceType to(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ref", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "to"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return to(ocType, false, false, false);
    }
    
    public static OCCppReferenceType rvalue(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ref", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "rvalue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return to(ocType, true, false, false);
    }
    
    public static OCCppReferenceType to(@NotNull OCType refType, boolean rvalueRef, boolean const1, boolean volatile1) {
        try {
            if (refType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ref", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "to"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0089: {
            Label_0083: {
                try {
                    if (!(refType instanceof OCCppReferenceType)) {
                        return new OCCppReferenceType(refType, rvalueRef, const1, volatile1);
                    }
                    if (!rvalueRef) {
                        break Label_0083;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                const1 = refType.isConst();
                volatile1 = refType.isVolatile();
                rvalueRef = ((OCCppReferenceType)refType).isRvalueRef();
                break Label_0089;
            }
            const1 = (rvalueRef = (volatile1 = false));
        }
        refType = ((OCCppReferenceType)refType).getRefType();
        return new OCCppReferenceType(refType, rvalueRef, const1, volatile1);
    }
    
    private OCCppReferenceType(final OCType myRefType, final boolean myRvalueRef, final boolean b, final boolean b2) {
        super(b, b2);
        this.myRefType = myRefType;
        this.myRvalueRef = myRvalueRef;
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitCppReferenceType(this);
    }
    
    public boolean isReferenceToConst() {
        Label_0027: {
            try {
                if (this.myRefType.isConst()) {
                    break Label_0027;
                }
                final OCCppReferenceType ocCppReferenceType = this;
                final OCType ocType = ocCppReferenceType.myRefType;
                final boolean b = ocType instanceof OCArrayType;
                if (b) {
                    break Label_0027;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCCppReferenceType ocCppReferenceType = this;
                final OCType ocType = ocCppReferenceType.myRefType;
                final boolean b = ocType instanceof OCArrayType;
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public boolean isRvalueRef() {
        return this.myRvalueRef;
    }
    
    @NotNull
    public OCType getRefType() {
        OCType myRefType;
        try {
            myRefType = this.myRefType;
            if (myRefType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "getRefType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myRefType;
    }
    
    @NotNull
    public OCType getRefType(final PsiElement psiElement) {
        OCType ocType = null;
        Label_0045: {
            try {
                if (this.myRefType.isBetterAliasName(this, this.myAliasName, this.myRefType.getAliasName(), psiElement)) {
                    final OCType ocType2;
                    ocType = (ocType2 = this.myRefType.cloneWithAliasName(this.myAliasName));
                    break Label_0045;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            OCType ocType2;
            ocType = (ocType2 = this.myRefType);
            try {
                if (ocType2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "getRefType"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return ocType;
    }
    
    @NotNull
    @Override
    public OCType getTerminalType() {
        OCType terminalType;
        try {
            terminalType = this.myRefType.getTerminalType();
            if (terminalType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "getTerminalType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return terminalType;
    }
    
    @Override
    public int pointersDepth() {
        return this.myRefType.pointersDepth();
    }
    
    @Override
    public boolean isMagicInside(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "isMagicInside"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myRefType.isMagicInside(ocResolveContext);
    }
    
    @Override
    public boolean isSubclassOfMagic(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "isSubclassOfMagic"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myRefType.isSubclassOfMagic(ocResolveContext);
    }
    
    @Override
    public boolean isUnknown() {
        return this.myRefType.isUnknown();
    }
    
    @Override
    public boolean isUnresolved(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "isUnresolved"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myRefType.isUnresolved(ocResolveContext);
    }
    
    @NotNull
    @Override
    public OCType getGuessedUnmagicType() {
        OCCppReferenceType to;
        try {
            to = to(this.myRefType.getGuessedUnmagicType());
            if (to == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "getGuessedUnmagicType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return to;
    }
    
    @Override
    public int hashCode() {
        return this.baseHashCode() * 31 + this.myRefType.hashCode();
    }
    
    @Override
    public boolean isPointerCompatible(final PsiElement psiElement, final boolean b) {
        return true;
    }
    
    @NotNull
    @Override
    protected OCType doGetLeastCommonType(final OCType ocType, final PsiElement psiElement) {
        OCCppReferenceType to = null;
        Label_0163: {
            OCType ocType2 = null;
            Label_0128: {
                Label_0109: {
                    OCCppReferenceType ocCppReferenceType = null;
                    Label_0074: {
                        Label_0053: {
                            OCUnknownType ocUnknownType = null;
                            Label_0018: {
                                try {
                                    if (ocType != null) {
                                        break Label_0053;
                                    }
                                    ocUnknownType = OCUnknownType.INSTANCE;
                                    if (ocUnknownType == null) {
                                        break Label_0018;
                                    }
                                    return ocUnknownType;
                                }
                                catch (IllegalArgumentException ex) {
                                    throw a(ex);
                                }
                                try {
                                    ocUnknownType = OCUnknownType.INSTANCE;
                                    if (ocUnknownType == null) {
                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "doGetLeastCommonType"));
                                    }
                                }
                                catch (IllegalArgumentException ex2) {
                                    throw a(ex2);
                                }
                            }
                            return ocUnknownType;
                            try {
                                if (!this.equals(ocType, psiElement)) {
                                    break Label_0109;
                                }
                                ocCppReferenceType = this;
                                if (ocCppReferenceType == null) {
                                    break Label_0074;
                                }
                                return ocCppReferenceType;
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                        }
                        try {
                            ocCppReferenceType = this;
                            if (ocCppReferenceType == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "doGetLeastCommonType"));
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    return ocCppReferenceType;
                    try {
                        if (!(ocType instanceof OCMagicType)) {
                            break Label_0163;
                        }
                        ocType2 = ocType;
                        if (ocType2 == null) {
                            break Label_0128;
                        }
                        return ocType2;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                try {
                    ocType2 = ocType;
                    if (ocType2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "doGetLeastCommonType"));
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            return ocType2;
            try {
                to = to(this.myRefType.getLeastCommonType(ocType, psiElement));
                if (to == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "doGetLeastCommonType"));
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return to;
    }
    
    @Override
    public boolean isScalar() {
        return this.myRefType.isScalar();
    }
    
    @Override
    public boolean isCppStructType() {
        return this.myRefType.isCppStructType();
    }
    
    @Override
    public boolean isInstanceable() {
        return true;
    }
    
    @Override
    public boolean isIntegerCompatible(final PsiElement psiElement, final boolean b) {
        return this.myRefType.isIntegerCompatible(psiElement, b);
    }
    
    @Override
    public boolean isPointerToObject() {
        return this.myRefType.isPointerToObject();
    }
    
    @Override
    public boolean isPointerToCppStructType() {
        return this.myRefType.isPointerToCppStructType();
    }
    
    @Override
    public boolean isPointerToID(final boolean b) {
        return this.myRefType.isPointerToID(b);
    }
    
    @Override
    public boolean isPointerToChar() {
        return this.myRefType.isPointerToChar();
    }
    
    @Override
    public boolean isCString() {
        return this.myRefType.isCString();
    }
    
    @Override
    public boolean isPointerToObjectCompatible() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/OCCppReferenceType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     4: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //     7: ifne            44
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/lang/types/OCCppReferenceType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    14: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:()Z
        //    17: ifne            44
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/types/OCCppReferenceType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: aload_0        
        //    28: getfield        com/jetbrains/cidr/lang/types/OCCppReferenceType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    31: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //    34: ifeq            52
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/lang/types/OCCppReferenceType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: iconst_1       
        //    45: goto            53
        //    48: invokestatic    com/jetbrains/cidr/lang/types/OCCppReferenceType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: iconst_0       
        //    53: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      20     23     27     Ljava/lang/IllegalArgumentException;
        //  10     37     40     44     Ljava/lang/IllegalArgumentException;
        //  27     48     48     52     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
    public boolean isPointerToPointerToObjectCompatible() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/OCCppReferenceType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     4: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //     7: ifne            50
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/lang/types/OCCppReferenceType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    14: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    17: ifeq            58
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/types/OCCppReferenceType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: aload_0        
        //    28: getfield        com/jetbrains/cidr/lang/types/OCCppReferenceType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    31: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    34: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    37: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToPointerToObjectCompatible:()Z
        //    40: ifeq            58
        //    43: goto            50
        //    46: invokestatic    com/jetbrains/cidr/lang/types/OCCppReferenceType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: iconst_1       
        //    51: goto            59
        //    54: invokestatic    com/jetbrains/cidr/lang/types/OCCppReferenceType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: iconst_0       
        //    59: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      20     23     27     Ljava/lang/IllegalArgumentException;
        //  10     43     46     50     Ljava/lang/IllegalArgumentException;
        //  27     54     54     58     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
    public boolean isPointer() {
        return this.myRefType.isPointer();
    }
    
    @Override
    public boolean isClassType(final boolean b) {
        return this.myRefType.isClassType(b);
    }
    
    @NotNull
    @Override
    public String getDefaultValue(final PsiElement psiElement) {
        String s;
        try {
            s = "<#initializer#>";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCCppReferenceType", "getDefaultValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    public int getSizeInBytes(@Nullable final PsiFile psiFile, @Nullable final OCInclusionContext ocInclusionContext) {
        return this.myRefType.getSizeInBytes(psiFile, ocInclusionContext);
    }
    
    @Override
    public String getFormatString() {
        return this.myRefType.getFormatString();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
