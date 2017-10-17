// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import java.util.regex.Matcher;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.visitors.OCTypeCompatibilityVisitor;
import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityAfterResolvingVisitor;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.OCLog;
import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityVisitor;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.types.visitors.OCTypeResolveVisitor;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.types.visitors.OCTypeCloneVisitor;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.visitors.OCTypeNameVisitor;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import java.util.regex.Pattern;
import org.jetbrains.annotations.Nullable;

public abstract class OCType implements OCTypeArgument
{
    private static final int IS_CONST = 1;
    private static final int IS_VOLATILE = 2;
    private static final int IS_NULLABLE = 4;
    private static final int IS_NONNULL = 8;
    private static final int IS_NULL_UNSPECIFIED = 16;
    protected static final int IS_FUNCTION_LVALUE_REF = 32;
    protected static final int IS_FUNCTION_RVALUE_REF = 64;
    @Nullable
    protected String myAliasName;
    @Nullable
    private OCType myGuessedType;
    protected int myTypeAttributes;
    private static Pattern requiresNilAttributePattern;
    
    public OCType() {
    }
    
    public OCType(final boolean b, final boolean b2) {
        boolean b3;
        if (b) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        int n = 0;
        Label_0027: {
            try {
                if (b2) {
                    n = 2;
                    break Label_0027;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            n = 0;
        }
        this.myTypeAttributes = ((b3 ? 1 : 0) | n);
    }
    
    public OCType(final boolean b, final boolean b2, @Nullable final OCNullability ocNullability) {
        this(b, b2);
        this.attachNullability(ocNullability);
    }
    
    public void attachAliasName(@Nullable final String myAliasName) {
        this.myAliasName = myAliasName;
    }
    
    public void attachGuessedType(@Nullable final OCType myGuessedType) {
        this.myGuessedType = myGuessedType;
    }
    
    protected static int clearAttribute(final int n, final int n2) {
        return n & ~n2;
    }
    
    protected static int attachAttribute(final int n, final int n2) {
        return n | n2;
    }
    
    protected boolean checkAttribute(final int n) {
        try {
            if ((this.myTypeAttributes & n) != 0x0) {
                return true;
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public void attachNullability(@Nullable final OCNullability ocNullability) {
        int myTypeAttributes = clearAttribute(clearAttribute(clearAttribute(this.myTypeAttributes, 8), 4), 16);
        if (ocNullability == OCNullability.NONNULL) {
            myTypeAttributes = attachAttribute(myTypeAttributes, 8);
        }
        else if (ocNullability == OCNullability.NULLABLE) {
            myTypeAttributes = attachAttribute(myTypeAttributes, 4);
        }
        else if (ocNullability == OCNullability.UNSPECIFIED) {
            myTypeAttributes = attachAttribute(myTypeAttributes, 16);
        }
        this.myTypeAttributes = myTypeAttributes;
    }
    
    @NotNull
    public String getName() {
        String simpleName;
        try {
            simpleName = this.getSimpleName(null);
            if (simpleName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "getName"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return simpleName;
    }
    
    @NotNull
    public String getName(@Nullable final PsiElement psiElement) {
        String s = null;
        Label_0020: {
            try {
                if (psiElement != null) {
                    final String s2;
                    s = (s2 = this.getBestNameInContext(psiElement));
                    break Label_0020;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            String s2;
            s = (s2 = this.getName());
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "getName"));
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
        }
        return s;
    }
    
    @NotNull
    public String getName(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCType", "getName"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        String bestNameInContext;
        try {
            bestNameInContext = this.getBestNameInContext(ocResolveContext, null, 0);
            if (bestNameInContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "getName"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        return bestNameInContext;
    }
    
    @NotNull
    public String getSimpleName(@Nullable final PsiElement psiElement) {
        String name;
        try {
            name = new OCTypeNameVisitor(Presentation.SHORT, psiElement).getName(this);
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "getSimpleName"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return name;
    }
    
    public String getBestNameInContext(@Nullable final PsiElement psiElement) {
        return this.getBestNameInContext(psiElement, null);
    }
    
    public String getBestNameInContext(@Nullable final PsiElement psiElement, @Nullable final String s) {
        return this.getBestNameInContext(new OCResolveContext(psiElement), s, 0);
    }
    
    public String getBestNameInContext(@NotNull final OCResolveContext ocResolveContext, @Nullable final String s, final int n) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCType", "getBestNameInContext"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                return new OCTypeNameVisitor(Presentation.BEST, true, true, true, ocResolveContext, n).getName(this.resolve(ocResolveContext));
            }
            final OCType ocType = this;
            final String s2 = s;
            final OCResolveContext ocResolveContext2 = ocResolveContext;
            final boolean b = ocType.equalsAfterResolving(s2, ocResolveContext2);
            if (b) {
                return s;
            }
            return new OCTypeNameVisitor(Presentation.BEST, true, true, true, ocResolveContext, n).getName(this.resolve(ocResolveContext));
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        try {
            final OCType ocType = this;
            final String s2 = s;
            final OCResolveContext ocResolveContext2 = ocResolveContext;
            final boolean b = ocType.equalsAfterResolving(s2, ocResolveContext2);
            if (b) {
                return s;
            }
        }
        catch (UnsupportedOperationException ex3) {
            throw b(ex3);
        }
        return new OCTypeNameVisitor(Presentation.BEST, true, true, true, ocResolveContext, n).getName(this.resolve(ocResolveContext));
    }
    
    @NotNull
    public String getCanonicalName() {
        String canonicalName;
        try {
            canonicalName = this.getCanonicalName(null);
            if (canonicalName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "getCanonicalName"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return canonicalName;
    }
    
    @NotNull
    public String getCanonicalName(@Nullable final PsiElement psiElement) {
        String name;
        try {
            name = new OCTypeNameVisitor(Presentation.FULL, false, false, true, psiElement).getName(this);
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "getCanonicalName"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return name;
    }
    
    @Override
    public String getNameForPresentation(final Presentation presentation, @NotNull final OCResolveContext ocResolveContext, final boolean b, final int n) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCType", "getNameForPresentation"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (presentation == Presentation.SHORT) {
                return this.getSimpleName(ocResolveContext.getElement());
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        try {
            if (presentation == Presentation.BEST) {
                return this.getBestNameInContext(ocResolveContext, null, n);
            }
        }
        catch (UnsupportedOperationException ex3) {
            throw b(ex3);
        }
        return new OCTypeNameVisitor(Presentation.FULL, false, false, b, ocResolveContext, n).getName(this);
    }
    
    @Nullable
    public String getAliasName() {
        return this.myAliasName;
    }
    
    public boolean isBetterAliasName(final OCType p0, final String p1, final String p2, final PsiElement p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           4
        //     2: ifnull          90
        //     5: aload_2        
        //     6: ifnull          90
        //     9: goto            16
        //    12: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    15: athrow         
        //    16: aload_3        
        //    17: ifnull          45
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    26: athrow         
        //    27: aload_3        
        //    28: invokestatic    com/jetbrains/cidr/lang/types/OCType.getNameComplexity:(Ljava/lang/String;)I
        //    31: aload_2        
        //    32: invokestatic    com/jetbrains/cidr/lang/types/OCType.getNameComplexity:(Ljava/lang/String;)I
        //    35: if_icmple       90
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    44: athrow         
        //    45: aload_0        
        //    46: astore          5
        //    48: aload           5
        //    50: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    53: ifeq            66
        //    56: aload           5
        //    58: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    61: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    64: astore          5
        //    66: aload_1        
        //    67: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    70: ifeq            81
        //    73: aload_1        
        //    74: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    77: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    80: astore_1       
        //    81: aload           5
        //    83: aload_1        
        //    84: aload           4
        //    86: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Z
        //    89: ireturn        
        //    90: iconst_0       
        //    91: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  0      9      12     16     Ljava/lang/UnsupportedOperationException;
        //  5      20     23     27     Ljava/lang/UnsupportedOperationException;
        //  16     38     41     45     Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0016:
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
    
    protected static int getNameComplexity(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_1       
        //     2: iconst_0       
        //     3: istore_2       
        //     4: iload_2        
        //     5: aload_0        
        //     6: invokevirtual   java/lang/String.length:()I
        //     9: if_icmpge       66
        //    12: aload_0        
        //    13: iload_2        
        //    14: invokevirtual   java/lang/String.charAt:(I)C
        //    17: istore_3       
        //    18: iload_3        
        //    19: bipush          58
        //    21: if_icmpeq       50
        //    24: iload_3        
        //    25: bipush          60
        //    27: if_icmpeq       50
        //    30: goto            37
        //    33: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    36: athrow         
        //    37: iload_3        
        //    38: bipush          62
        //    40: if_icmpne       60
        //    43: goto            50
        //    46: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    49: athrow         
        //    50: iinc            1, 1
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    59: athrow         
        //    60: iinc            2, 1
        //    63: goto            4
        //    66: iload_1        
        //    67: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  18     30     33     37     Ljava/lang/UnsupportedOperationException;
        //  24     43     46     50     Ljava/lang/UnsupportedOperationException;
        //  37     53     56     60     Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0037:
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
    public OCType getGuessedType() {
        OCType ocType = null;
        Label_0019: {
            try {
                if (this.myGuessedType != null) {
                    final OCType myGuessedType;
                    ocType = (myGuessedType = this.myGuessedType);
                    break Label_0019;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            ocType = this;
            OCType myGuessedType = this;
            try {
                if (myGuessedType == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "getGuessedType"));
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
        }
        return ocType;
    }
    
    public boolean hasGuessedType() {
        try {
            if (this.myGuessedType != null) {
                return true;
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public boolean isConst() {
        try {
            if ((this.myTypeAttributes & 0x1) != 0x0) {
                return true;
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public boolean isVolatile() {
        try {
            if ((this.myTypeAttributes & 0x2) != 0x0) {
                return true;
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return false;
    }
    
    @Nullable
    public OCNullability getNullability() {
        try {
            if (this.checkAttribute(4)) {
                return OCNullability.NULLABLE;
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (this.checkAttribute(8)) {
                return OCNullability.NONNULL;
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        try {
            if (this.checkAttribute(16)) {
                return OCNullability.UNSPECIFIED;
            }
        }
        catch (UnsupportedOperationException ex3) {
            throw b(ex3);
        }
        return null;
    }
    
    @NotNull
    public CVQualifiers getCVQualifiers() {
        CVQualifiers value;
        try {
            value = CVQualifiers.get(this.isConst(), this.isVolatile());
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "getCVQualifiers"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return value;
    }
    
    @NotNull
    public OCType getGuessedUnmagicType() {
        try {
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "getGuessedUnmagicType"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return this;
    }
    
    @NotNull
    public OCType cloneWithCVQualifiers(@NotNull final CVQualifiers cvQualifiers, @Nullable final Project project) {
        try {
            if (cvQualifiers == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modifiers", "com/jetbrains/cidr/lang/types/OCType", "cloneWithCVQualifiers"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        if (this.getCVQualifiers() != cvQualifiers) {
            final OCType a = this.a(true, cvQualifiers.isConst(), cvQualifiers.isVolatile());
            if (a.myAliasName != null) {
                final String myAliasName = a.myAliasName;
                String s;
                if (cvQualifiers.isConst()) {
                    s = OCTypeNameVisitor.addTypeQualifier(myAliasName, a, project, "const");
                }
                else {
                    s = OCTypeNameVisitor.removeTypeQualifier(myAliasName, a, project, "const");
                }
                String s2;
                if (cvQualifiers.isVolatile()) {
                    s2 = OCTypeNameVisitor.addTypeQualifier(s, a, project, "volatile");
                }
                else {
                    s2 = OCTypeNameVisitor.removeTypeQualifier(s, a, project, "volatile");
                }
                a.attachAliasName(s2);
            }
            OCType ocType;
            try {
                ocType = a;
                if (ocType == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "cloneWithCVQualifiers"));
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
            return ocType;
        }
        try {
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "cloneWithCVQualifiers"));
            }
        }
        catch (UnsupportedOperationException ex3) {
            throw b(ex3);
        }
        return this;
    }
    
    @NotNull
    public OCType cloneWithoutCVQualifiers(@Nullable final Project project) {
        OCType cloneWithCVQualifiers;
        try {
            cloneWithCVQualifiers = this.cloneWithCVQualifiers(CVQualifiers.EMPTY, project);
            if (cloneWithCVQualifiers == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "cloneWithoutCVQualifiers"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return cloneWithCVQualifiers;
    }
    
    @NotNull
    public OCType cloneWithAddedCVQualifiers(@NotNull final CVQualifiers cvQualifiers, @Nullable final Project project) {
        try {
            if (cvQualifiers == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modifiers", "com/jetbrains/cidr/lang/types/OCType", "cloneWithAddedCVQualifiers"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        OCType cloneWithCVQualifiers;
        try {
            cloneWithCVQualifiers = this.cloneWithCVQualifiers(this.getCVQualifiers().or(cvQualifiers), project);
            if (cloneWithCVQualifiers == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "cloneWithAddedCVQualifiers"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        return cloneWithCVQualifiers;
    }
    
    @NotNull
    public OCType cloneWithoutConstModifier(@Nullable final Project project) {
        OCType cloneWithCVQualifiers;
        try {
            cloneWithCVQualifiers = this.cloneWithCVQualifiers(CVQualifiers.get(false, this.isVolatile()), project);
            if (cloneWithCVQualifiers == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "cloneWithoutConstModifier"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return cloneWithCVQualifiers;
    }
    
    @NotNull
    public OCType cloneWithConstModifier(@Nullable final Project project) {
        OCType cloneWithCVQualifiers;
        try {
            cloneWithCVQualifiers = this.cloneWithCVQualifiers(CVQualifiers.get(true, this.isVolatile()), project);
            if (cloneWithCVQualifiers == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "cloneWithConstModifier"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return cloneWithCVQualifiers;
    }
    
    @NotNull
    public OCType cloneWithAliasName(final String s) {
        if (!Comparing.equal(this.myAliasName, s)) {
            final OCType shallowCopy = this.getShallowCopy();
            OCType ocType;
            try {
                shallowCopy.attachAliasName(s);
                ocType = shallowCopy;
                if (ocType == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "cloneWithAliasName"));
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            return ocType;
        }
        try {
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "cloneWithAliasName"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        return this;
    }
    
    @NotNull
    public OCType cloneWithGuessedType(final OCType ocType) {
        Label_0071: {
            try {
                if (this.myGuessedType == null) {
                    if (ocType == null) {
                        break Label_0071;
                    }
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            final OCType a = this.a(true, null, null);
            OCType ocType2;
            try {
                a.attachGuessedType(ocType);
                ocType2 = a;
                if (ocType2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "cloneWithGuessedType"));
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
            return ocType2;
            try {
                if (this == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "cloneWithGuessedType"));
                }
            }
            catch (UnsupportedOperationException ex3) {
                throw b(ex3);
            }
        }
        return this;
    }
    
    @NotNull
    public OCType cloneWithNullability(@Nullable final OCNullability ocNullability) {
        if (this.getNullability() != ocNullability) {
            final OCType a = this.a(true, null, null);
            OCType ocType;
            try {
                a.attachNullability(ocNullability);
                ocType = a;
                if (ocType == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "cloneWithNullability"));
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            return ocType;
        }
        try {
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "cloneWithNullability"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        return this;
    }
    
    public OCType getShallowCopy() {
        return this.a(false, null, null);
    }
    
    private OCType a(final boolean b, final Boolean b2, final Boolean b3) {
        final OCType transformType = this.transformType(new OCTypeCloneVisitor(true, this, b2, b3));
        try {
            if (!b) {
                transformType.attachAliasName(null);
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return transformType;
    }
    
    @NotNull
    public OCType resolve(@Nullable final PsiFile psiFile) {
        OCType resolve;
        try {
            resolve = this.resolve(psiFile, false);
            if (resolve == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "resolve"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return resolve;
    }
    
    @NotNull
    public OCType resolve(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCType", "resolve"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        OCType transformType;
        try {
            transformType = this.transformType(new OCTypeResolveVisitor(ocResolveContext));
            if (transformType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "resolve"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        return transformType;
    }
    
    @NotNull
    public OCType resolve(@Nullable final PsiFile psiFile, final boolean b) {
        OCType transformType;
        try {
            transformType = this.transformType(new OCTypeResolveVisitor(psiFile, b));
            if (transformType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "resolve"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return transformType;
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + this.getName() + "]";
    }
    
    @NotNull
    public OCType getTerminalType() {
        try {
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "getTerminalType"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return this;
    }
    
    @NotNull
    public OCType getArrayElementType() {
        try {
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "getArrayElementType"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return this;
    }
    
    public int pointersDepth() {
        return 0;
    }
    
    public boolean isVoid() {
        return false;
    }
    
    public boolean isUnknown() {
        return false;
    }
    
    public final boolean isUnresolved(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCType", "isUnresolved"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return this.isUnresolved(new OCResolveContext(psiElement));
    }
    
    public boolean isUnresolved(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCType", "isUnresolved"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public boolean isMagicInside(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCType", "isMagicInside"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public boolean isSubclassOfMagic(@Nullable final PsiElement psiElement) {
        return this.isSubclassOfMagic(new OCResolveContext(psiElement));
    }
    
    public boolean isSubclassOfMagic(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCType", "isSubclassOfMagic"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public boolean isScalar() {
        return false;
    }
    
    public boolean isChar() {
        return false;
    }
    
    public boolean isScalarOrConvertibleToScalar(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isScalar:()Z
        //     4: ifne            70
        //     7: aload_0        
        //     8: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCppStructType:()Z
        //    11: ifeq            78
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    20: athrow         
        //    21: aload_0        
        //    22: aload_1        
        //    23: invokespecial   com/jetbrains/cidr/lang/types/OCType.a:(Lcom/intellij/psi/PsiElement;)Z
        //    26: ifne            70
        //    29: goto            36
        //    32: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    35: athrow         
        //    36: aload_0        
        //    37: aload_1        
        //    38: invokespecial   com/jetbrains/cidr/lang/types/OCType.b:(Lcom/intellij/psi/PsiElement;)Z
        //    41: ifne            70
        //    44: goto            51
        //    47: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    50: athrow         
        //    51: getstatic       com/jetbrains/cidr/lang/types/OCIntType.BOOL_NATIVE:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    54: aload_0        
        //    55: aload_1        
        //    56: iconst_1       
        //    57: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.isConvertibleByOperator:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Z)Z
        //    60: ifeq            78
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    69: athrow         
        //    70: iconst_1       
        //    71: goto            79
        //    74: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    77: athrow         
        //    78: iconst_0       
        //    79: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  0      14     17     21     Ljava/lang/UnsupportedOperationException;
        //  7      29     32     36     Ljava/lang/UnsupportedOperationException;
        //  21     44     47     51     Ljava/lang/UnsupportedOperationException;
        //  36     63     66     70     Ljava/lang/UnsupportedOperationException;
        //  51     74     74     78     Ljava/lang/UnsupportedOperationException;
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
    
    public boolean isInstanceable() {
        return false;
    }
    
    public boolean isPointerCompatible(final PsiElement p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isInPlainOldC:(Lcom/intellij/psi/PsiElement;)Z
        //     4: ifeq            22
        //     7: aload_0        
        //     8: aload_1        
        //     9: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isIntegerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //    12: ifne            62
        //    15: goto            22
        //    18: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    21: athrow         
        //    22: iload_2        
        //    23: ifeq            70
        //    26: goto            33
        //    29: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    32: athrow         
        //    33: aload_0        
        //    34: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCppStructType:()Z
        //    37: ifeq            70
        //    40: goto            47
        //    43: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    46: athrow         
        //    47: aload_0        
        //    48: aload_1        
        //    49: invokespecial   com/jetbrains/cidr/lang/types/OCType.b:(Lcom/intellij/psi/PsiElement;)Z
        //    52: ifeq            70
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    61: athrow         
        //    62: iconst_1       
        //    63: goto            71
        //    66: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    69: athrow         
        //    70: iconst_0       
        //    71: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  0      15     18     22     Ljava/lang/UnsupportedOperationException;
        //  7      26     29     33     Ljava/lang/UnsupportedOperationException;
        //  22     40     43     47     Ljava/lang/UnsupportedOperationException;
        //  33     55     58     62     Ljava/lang/UnsupportedOperationException;
        //  47     66     66     70     Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0022:
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
    
    public boolean isPointerCompatible(final PsiElement psiElement) {
        return this.isPointerCompatible(psiElement, true);
    }
    
    public boolean isPointerToString() {
        return "NSString *".equals(this.getName());
    }
    
    public boolean isPointerToStringCompatible() {
        return this.isPointerToStringCompatible(false);
    }
    
    public boolean isPointerToStringCompatible(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "NSString *"
        //     2: aload_0        
        //     3: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //     6: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //     9: ifne            86
        //    12: ldc             "NSString *"
        //    14: aload_0        
        //    15: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //    18: invokestatic    com/jetbrains/cidr/lang/types/OCTollFreeBridges.getNSCounterpart:(Ljava/lang/String;)Ljava/lang/String;
        //    21: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    24: ifne            86
        //    27: goto            34
        //    30: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    33: athrow         
        //    34: iload_1        
        //    35: ifeq            94
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    44: athrow         
        //    45: ldc             "NSMutableString *"
        //    47: aload_0        
        //    48: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //    51: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    54: ifne            86
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    63: athrow         
        //    64: ldc             "NSMutableString *"
        //    66: aload_0        
        //    67: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //    70: invokestatic    com/jetbrains/cidr/lang/types/OCTollFreeBridges.getNSCounterpart:(Ljava/lang/String;)Ljava/lang/String;
        //    73: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    76: ifeq            94
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    85: athrow         
        //    86: iconst_1       
        //    87: goto            95
        //    90: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    93: athrow         
        //    94: iconst_0       
        //    95: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  0      27     30     34     Ljava/lang/UnsupportedOperationException;
        //  12     38     41     45     Ljava/lang/UnsupportedOperationException;
        //  34     57     60     64     Ljava/lang/UnsupportedOperationException;
        //  45     79     82     86     Ljava/lang/UnsupportedOperationException;
        //  64     90     90     94     Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0034:
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
    
    public boolean isObjCRootType() {
        final String name = this.getName();
        Label_0030: {
            try {
                if ("id".equals(name)) {
                    break Label_0030;
                }
                final String s = "NSObject *";
                final String s2 = name;
                final boolean b = s.equals(s2);
                if (b) {
                    break Label_0030;
                }
                return false;
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            try {
                final String s = "NSObject *";
                final String s2 = name;
                final boolean b = s.equals(s2);
                if (b) {
                    return true;
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    private boolean b(final PsiElement psiElement) {
        return OCPointerType.to(OCVoidType.instance()).isConvertibleByOperator(this, psiElement, false);
    }
    
    private boolean a(final PsiElement psiElement) {
        return OCIntType.INT.isConvertibleByOperator(this, psiElement, false);
    }
    
    public boolean isNumberCompatible(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          45
        //     4: aload_0        
        //     5: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCppStructType:()Z
        //     8: ifeq            45
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    17: athrow         
        //    18: getstatic       com/jetbrains/cidr/lang/types/OCRealType.DOUBLE:Lcom/jetbrains/cidr/lang/types/OCRealType;
        //    21: aload_0        
        //    22: aload_1        
        //    23: iconst_0       
        //    24: invokevirtual   com/jetbrains/cidr/lang/types/OCRealType.isConvertibleByOperator:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Z)Z
        //    27: ifeq            45
        //    30: goto            37
        //    33: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    36: athrow         
        //    37: iconst_1       
        //    38: goto            46
        //    41: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    44: athrow         
        //    45: iconst_0       
        //    46: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  0      11     14     18     Ljava/lang/UnsupportedOperationException;
        //  4      30     33     37     Ljava/lang/UnsupportedOperationException;
        //  18     41     41     45     Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    
    public boolean isIntegerCompatible(final PsiElement p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_2        
        //     1: ifeq            41
        //     4: aload_0        
        //     5: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCppStructType:()Z
        //     8: ifeq            41
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    17: athrow         
        //    18: aload_0        
        //    19: aload_1        
        //    20: invokespecial   com/jetbrains/cidr/lang/types/OCType.a:(Lcom/intellij/psi/PsiElement;)Z
        //    23: ifeq            41
        //    26: goto            33
        //    29: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    32: athrow         
        //    33: iconst_1       
        //    34: goto            42
        //    37: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    40: athrow         
        //    41: iconst_0       
        //    42: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  0      11     14     18     Ljava/lang/UnsupportedOperationException;
        //  4      26     29     33     Ljava/lang/UnsupportedOperationException;
        //  18     37     37     41     Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    
    public boolean isIntegerCompatible(final PsiElement psiElement) {
        return this.isIntegerCompatible(psiElement, true);
    }
    
    public boolean isPointerToObject() {
        return false;
    }
    
    public boolean isPointerToObjectCompatible() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //     4: ifne            35
        //     7: aload_0        
        //     8: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:()Z
        //    11: ifne            35
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    20: athrow         
        //    21: aload_0        
        //    22: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //    25: ifeq            43
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    34: athrow         
        //    35: iconst_1       
        //    36: goto            44
        //    39: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    42: athrow         
        //    43: iconst_0       
        //    44: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  0      14     17     21     Ljava/lang/UnsupportedOperationException;
        //  7      28     31     35     Ljava/lang/UnsupportedOperationException;
        //  21     39     39     43     Ljava/lang/UnsupportedOperationException;
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
    
    public boolean isPointerToPointerToObjectCompatible() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //     4: ifne            41
        //     7: aload_0        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    11: ifeq            49
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    20: athrow         
        //    21: aload_0        
        //    22: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    25: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    28: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToPointerToObjectCompatible:()Z
        //    31: ifeq            49
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    40: athrow         
        //    41: iconst_1       
        //    42: goto            50
        //    45: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    48: athrow         
        //    49: iconst_0       
        //    50: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  0      14     17     21     Ljava/lang/UnsupportedOperationException;
        //  7      34     37     41     Ljava/lang/UnsupportedOperationException;
        //  21     45     45     49     Ljava/lang/UnsupportedOperationException;
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
    
    public boolean isPointerToID(final boolean b) {
        return false;
    }
    
    public boolean isPointerToID() {
        return this.isPointerToID(false);
    }
    
    public boolean isPointerToChar() {
        return false;
    }
    
    public boolean isCString() {
        return false;
    }
    
    public boolean isPointer() {
        return false;
    }
    
    public boolean isPointerToVoid() {
        return false;
    }
    
    public boolean isClassType(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //     4: ifeq            102
        //     7: aload_0        
        //     8: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    11: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    14: astore_2       
        //    15: aload_2        
        //    16: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //    19: astore_3       
        //    20: aload_3        
        //    21: ldc             "struct objc_class"
        //    23: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    26: ifne            45
        //    29: aload_3        
        //    30: ldc             "objc_class"
        //    32: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    35: ifeq            100
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    44: athrow         
        //    45: iload_1        
        //    46: ifeq            92
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    55: athrow         
        //    56: aload_2        
        //    57: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    60: ifeq            100
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    69: athrow         
        //    70: aload_2        
        //    71: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    74: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getAugmentedProtocols:()Ljava/util/List;
        //    77: invokeinterface java/util/List.isEmpty:()Z
        //    82: ifne            100
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    91: athrow         
        //    92: iconst_1       
        //    93: goto            101
        //    96: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    99: athrow         
        //   100: iconst_0       
        //   101: ireturn        
        //   102: iconst_0       
        //   103: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  20     38     41     45     Ljava/lang/UnsupportedOperationException;
        //  29     49     52     56     Ljava/lang/UnsupportedOperationException;
        //  45     63     66     70     Ljava/lang/UnsupportedOperationException;
        //  56     85     88     92     Ljava/lang/UnsupportedOperationException;
        //  70     96     96     100    Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0045:
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
    
    public boolean isClassType() {
        return this.isClassType(false);
    }
    
    public boolean isCppStructType() {
        return false;
    }
    
    public boolean isPointerToCppStructType() {
        return false;
    }
    
    @Override
    public boolean isVariadic() {
        return false;
    }
    
    @NotNull
    public String getDefaultValue(@Nullable final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //     4: ifeq            55
        //     7: ldc             "nil"
        //     9: dup            
        //    10: ifnonnull       54
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    19: athrow         
        //    20: new             Ljava/lang/IllegalStateException;
        //    23: dup            
        //    24: ldc             "@NotNull method %s.%s must not return null"
        //    26: ldc             2
        //    28: anewarray       Ljava/lang/Object;
        //    31: dup            
        //    32: ldc             0
        //    34: ldc             "com/jetbrains/cidr/lang/types/OCType"
        //    36: aastore        
        //    37: dup            
        //    38: ldc             1
        //    40: ldc             "getDefaultValue"
        //    42: aastore        
        //    43: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    46: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    49: athrow         
        //    50: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    53: athrow         
        //    54: areturn        
        //    55: aload_1        
        //    56: ifnull          126
        //    59: aload_1        
        //    60: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    65: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsNullptr:(Lcom/intellij/psi/PsiFile;)Z
        //    68: ifeq            126
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    77: athrow         
        //    78: ldc             "nullptr"
        //    80: dup            
        //    81: ifnonnull       125
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    90: athrow         
        //    91: new             Ljava/lang/IllegalStateException;
        //    94: dup            
        //    95: ldc             "@NotNull method %s.%s must not return null"
        //    97: ldc             2
        //    99: anewarray       Ljava/lang/Object;
        //   102: dup            
        //   103: ldc             0
        //   105: ldc             "com/jetbrains/cidr/lang/types/OCType"
        //   107: aastore        
        //   108: dup            
        //   109: ldc             1
        //   111: ldc             "getDefaultValue"
        //   113: aastore        
        //   114: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   117: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   120: athrow         
        //   121: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   124: athrow         
        //   125: areturn        
        //   126: ldc             "NULL"
        //   128: dup            
        //   129: ifnonnull       166
        //   132: new             Ljava/lang/IllegalStateException;
        //   135: dup            
        //   136: ldc             "@NotNull method %s.%s must not return null"
        //   138: ldc             2
        //   140: anewarray       Ljava/lang/Object;
        //   143: dup            
        //   144: ldc             0
        //   146: ldc             "com/jetbrains/cidr/lang/types/OCType"
        //   148: aastore        
        //   149: dup            
        //   150: ldc             1
        //   152: ldc             "getDefaultValue"
        //   154: aastore        
        //   155: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   158: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   161: athrow         
        //   162: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   165: athrow         
        //   166: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  0      13     16     20     Ljava/lang/UnsupportedOperationException;
        //  7      50     50     54     Ljava/lang/UnsupportedOperationException;
        //  55     71     74     78     Ljava/lang/UnsupportedOperationException;
        //  59     84     87     91     Ljava/lang/UnsupportedOperationException;
        //  78     121    121    125    Ljava/lang/UnsupportedOperationException;
        //  126    162    162    166    Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0078:
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
    
    public int getSizeInBytes(@Nullable final PsiFile psiFile, @Nullable final OCInclusionContext ocInclusionContext) {
        return -1;
    }
    
    public OCType transformType(final OCTypeVisitor<OCType> ocTypeVisitor) {
        OCType ocType = this.accept(ocTypeVisitor);
        try {
            if (ocType == null) {
                return null;
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        if (this.myAliasName != null) {
            ocType = ocType.cloneWithAliasName(this.myAliasName);
        }
        if (this.myGuessedType != null) {
            ocType = ocType.cloneWithGuessedType(this.myGuessedType);
        }
        if (this.getNullability() != null) {
            ocType = ocType.cloneWithNullability(this.getNullability());
        }
        return ocType;
    }
    
    public abstract <T> T accept(final OCTypeVisitor<T> p0);
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCType", "deepEqualStep"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCType", "deepEqualStep"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCType", "deepEqualStep"));
            }
        }
        catch (UnsupportedOperationException ex3) {
            throw b(ex3);
        }
        final OCType ocType = (OCType)o;
        final OCType ocType2 = (OCType)o2;
        try {
            if (ocType.myTypeAttributes != ocType2.myTypeAttributes) {
                return false;
            }
        }
        catch (UnsupportedOperationException ex4) {
            throw b(ex4);
        }
        try {
            if (!Comparing.equal(ocType.myAliasName, ocType2.myAliasName)) {
                return false;
            }
        }
        catch (UnsupportedOperationException ex5) {
            throw b(ex5);
        }
        return comparator.equalObjects(ocType.myGuessedType, (DeepEqual.Equality<Object>)ocType2.myGuessedType);
    }
    
    @Override
    public boolean equals(final Object o) {
        throw new UnsupportedOperationException("Use equals(Object o, @NotNull OCMemoization context) instead");
    }
    
    public boolean equals(final Object o, @Nullable final PsiElement psiElement) {
        return this.equals(o, true, new OCResolveContext(psiElement));
    }
    
    @Override
    public boolean equals(final Object o, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCType", "equals"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return this.equals(o, true, ocResolveContext);
    }
    
    public boolean equals(final Object o, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCType", "equals"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        OCTypeEqualityVisitor ocTypeEqualityVisitor = null;
        OCType ocType = null;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        boolean b5 = false;
        boolean b6 = false;
        boolean b7 = false;
        boolean b9 = false;
        Label_0085: {
            Label_0076: {
                try {
                    if (!(o instanceof OCType)) {
                        return false;
                    }
                    ocTypeEqualityVisitor = new(com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityVisitor.class);
                    final Object o2 = o;
                    ocType = (OCType)o2;
                    b2 = false;
                    b3 = false;
                    b4 = false;
                    b5 = false;
                    b6 = false;
                    b7 = true;
                    final boolean b8 = b;
                    if (!b8) {
                        break Label_0076;
                    }
                    break Label_0076;
                }
                catch (UnsupportedOperationException ex2) {
                    throw b(ex2);
                }
                try {
                    ocTypeEqualityVisitor = new(com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityVisitor.class);
                    final Object o2 = o;
                    ocType = (OCType)o2;
                    b2 = false;
                    b3 = false;
                    b4 = false;
                    b5 = false;
                    b6 = false;
                    b7 = true;
                    final boolean b8 = b;
                    if (!b8) {
                        b9 = true;
                        break Label_0085;
                    }
                }
                catch (UnsupportedOperationException ex3) {
                    throw b(ex3);
                }
            }
            b9 = false;
        }
        new OCTypeEqualityVisitor(ocType, b2, b3, b4, b5, b6, b7, b9, ocResolveContext);
        if (ocTypeEqualityVisitor.equal(this)) {
            return true;
        }
        return false;
    }
    
    protected int baseHashCode() {
        int hashCode = 0;
        Label_0022: {
            try {
                if (this.myAliasName != null) {
                    hashCode = this.myAliasName.hashCode();
                    break Label_0022;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            hashCode = 0;
        }
        final int n = hashCode;
        int n2;
        try {
            n2 = 31 * n;
            if (this.myGuessedType != null) {
                final int hashCode2 = this.myGuessedType.hashCode();
                return 31 * (n2 + hashCode2) + this.myTypeAttributes;
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        final int hashCode2 = 0;
        return 31 * (n2 + hashCode2) + this.myTypeAttributes;
    }
    
    @Override
    public int hashCode() {
        OCLog.LOG.warn("An attempt to get hashCode for OCType base class!");
        return 0;
    }
    
    public boolean equalsAfterResolving(final String s, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCType", "equalsAfterResolving"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        final PsiElement element = ocResolveContext.getElement();
        Label_0078: {
            try {
                if (element == null) {
                    return false;
                }
                final OCType ocType = this;
                final String s2 = s;
                final PsiElement psiElement = element;
                final OCCodeFragment ocCodeFragment = OCElementFactory.typeCodeFragment(s2, psiElement);
                final OCType ocType2 = OCElementUtil.getType((PsiElement)ocCodeFragment);
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = true;
                final boolean b2 = false;
                final boolean b3 = ocType.equalsAfterResolving(ocType2, ocResolveContext2, b, b2);
                if (b3) {
                    break Label_0078;
                }
                return false;
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
            try {
                final OCType ocType = this;
                final String s2 = s;
                final PsiElement psiElement = element;
                final OCCodeFragment ocCodeFragment = OCElementFactory.typeCodeFragment(s2, psiElement);
                final OCType ocType2 = OCElementUtil.getType((PsiElement)ocCodeFragment);
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = true;
                final boolean b2 = false;
                final boolean b3 = ocType.equalsAfterResolving(ocType2, ocResolveContext2, b, b2);
                if (b3) {
                    return true;
                }
            }
            catch (UnsupportedOperationException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    public boolean equalsAfterResolving(OCType refType, @NotNull final OCResolveContext ocResolveContext, final boolean b, final boolean b2) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCType", "equalsAfterResolving"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        OCType refType2 = this;
        try {
            if (refType == null) {
                return false;
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        if (refType2 instanceof OCCppReferenceType) {
            refType2 = ((OCCppReferenceType)refType2).getRefType();
        }
        if (refType instanceof OCCppReferenceType) {
            refType = ((OCCppReferenceType)refType).getRefType();
        }
        return new OCTypeEqualityAfterResolvingVisitor(refType, false, b, true, b2, true, ocResolveContext).equal(refType2);
    }
    
    public boolean equalsAfterResolving(final OCType ocType, @Nullable final PsiElement psiElement) {
        return new OCTypeEqualityAfterResolvingVisitor(ocType, false, new OCResolveContext(psiElement)).equal(this);
    }
    
    public boolean equalsAfterResolving(final OCType ocType, @Nullable final OCResolveContext ocResolveContext) {
        return new OCTypeEqualityAfterResolvingVisitor(ocType, false, ocResolveContext).equal(this);
    }
    
    public boolean equalsWithAliasName(final OCType ocType, @Nullable final PsiElement psiElement) {
        String s = this.getAliasName();
        String s2 = ocType.getAliasName();
        if (s == null) {
            s = this.getCanonicalName(psiElement);
        }
        if (s2 == null) {
            s2 = ocType.getCanonicalName(psiElement);
        }
        Label_0058: {
            try {
                if (!Comparing.equal(s, s2)) {
                    return false;
                }
                final OCType ocType2 = this;
                final OCType ocType3 = ocType;
                final PsiElement psiElement2 = psiElement;
                final boolean b = ocType2.equalsAfterResolving(ocType3, psiElement2);
                if (b) {
                    break Label_0058;
                }
                return false;
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            try {
                final OCType ocType2 = this;
                final OCType ocType3 = ocType;
                final PsiElement psiElement2 = psiElement;
                final boolean b = ocType2.equalsAfterResolving(ocType3, psiElement2);
                if (b) {
                    return true;
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @NotNull
    public OCType getLeastCommonType(final OCType ocType, @Nullable final PsiElement psiElement) {
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
                catch (UnsupportedOperationException ex) {
                    throw b(ex);
                }
                try {
                    ocUnknownType = OCUnknownType.INSTANCE;
                    if (ocUnknownType == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "getLeastCommonType"));
                    }
                }
                catch (UnsupportedOperationException ex2) {
                    throw b(ex2);
                }
            }
            return ocUnknownType;
        }
        final OCType doGetLeastCommonType = this.doGetLeastCommonType(ocType, psiElement);
        OCType ocType2 = null;
        CVQualifiers or = null;
        Project project = null;
        Label_0090: {
            try {
                ocType2 = doGetLeastCommonType;
                or = this.getCVQualifiers().or(ocType.getCVQualifiers());
                if (psiElement != null) {
                    project = psiElement.getProject();
                    break Label_0090;
                }
            }
            catch (UnsupportedOperationException ex3) {
                throw b(ex3);
            }
            project = null;
        }
        OCType ocType3 = ocType2.cloneWithAddedCVQualifiers(or, project);
        OCType ocType5 = null;
        Label_0175: {
            try {
                if (this.myGuessedType == null || ocType.myGuessedType == null) {
                    break Label_0175;
                }
            }
            catch (UnsupportedOperationException ex4) {
                throw b(ex4);
            }
            final OCType doGetLeastCommonType2 = this.myGuessedType.doGetLeastCommonType(ocType.myGuessedType, psiElement);
            Label_0151: {
                try {
                    if (doGetLeastCommonType2.isUnknown()) {
                        break Label_0175;
                    }
                    final OCType ocType4 = ocType3;
                    final boolean b = ocType4.isPointerToID();
                    if (!b) {
                        break Label_0151;
                    }
                    break Label_0151;
                }
                catch (UnsupportedOperationException ex5) {
                    throw b(ex5);
                }
                try {
                    final OCType ocType4 = ocType3;
                    final boolean b = ocType4.isPointerToID();
                    if (!b) {
                        if (doGetLeastCommonType2.isCompatible(ocType3, psiElement)) {
                            break Label_0175;
                        }
                    }
                }
                catch (UnsupportedOperationException ex6) {
                    throw b(ex6);
                }
            }
            ocType3 = ocType3.cloneWithGuessedType(doGetLeastCommonType2);
            try {
                ocType5 = ocType3;
                if (ocType5 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "getLeastCommonType"));
                }
            }
            catch (UnsupportedOperationException ex7) {
                throw b(ex7);
            }
        }
        return ocType5;
    }
    
    @NotNull
    protected OCType doGetLeastCommonType(final OCType ocType, final PsiElement psiElement) {
        OCUnknownType instance;
        try {
            instance = OCUnknownType.INSTANCE;
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "doGetLeastCommonType"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return instance;
    }
    
    @NotNull
    public TypeCheckResult checkCompatible(final OCType ocType, @Nullable final PsiElement psiElement) {
        TypeCheckResult checkCompatible;
        try {
            checkCompatible = this.checkCompatible(ocType, null, psiElement);
            if (checkCompatible == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "checkCompatible"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return checkCompatible;
    }
    
    @NotNull
    public TypeCheckResult checkCompatible(final OCType ocType, @Nullable final OCTypeOwner ocTypeOwner, @Nullable final PsiElement psiElement) {
        TypeCheckResult checkCompatible;
        try {
            checkCompatible = this.checkCompatible(ocType, ocTypeOwner, psiElement, true, true, new OCResolveContext(psiElement));
            if (checkCompatible == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "checkCompatible"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return checkCompatible;
    }
    
    @NotNull
    public TypeCheckResult checkCompatible(final OCType ocType, @Nullable final OCTypeOwner ocTypeOwner, @Nullable final PsiElement psiElement, final boolean b, final boolean b2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/OCType", "checkCompatible"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        TypeCheckResult checkConvertible;
        try {
            checkConvertible = OCTypeCompatibilityVisitor.checkConvertible(this, ocType, ocTypeOwner, psiElement, b, b, b2, ocResolveContext);
            if (checkConvertible == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCType", "checkCompatible"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        return checkConvertible;
    }
    
    public boolean isConvertibleByOperator(OCType refType, @NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCType", "isConvertibleByOperator"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        if (refType instanceof OCCppReferenceType) {
            refType = ((OCCppReferenceType)refType).getRefType();
        }
        if (refType instanceof OCStructType) {
            final TypeCheckResult typeCheckResult = new TypeCheckResult(TypeCheckState.ERROR);
            final OCResolveContext ocResolveContext = new OCResolveContext(psiElement);
            try {
                if (!OCTypeCompatibilityVisitor.checkConversionOperators(this, (OCStructType)refType, null, psiElement, typeCheckResult, b, ocResolveContext).getState().isError(psiElement)) {
                    return true;
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
            return false;
        }
        return false;
    }
    
    public boolean isCompatible(final OCType ocType, @Nullable final PsiElement psiElement) {
        try {
            if (this.checkCompatible(ocType, psiElement).getState() == TypeCheckState.OK) {
                return true;
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public boolean isCompatible(final OCType ocType, @NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCType", "isCompatible"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (this.checkCompatible(ocType, null, psiElement, b, true, new OCResolveContext(psiElement)).getState() == TypeCheckState.OK) {
                return true;
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    public boolean isCompatible(final OCType ocType, @Nullable final OCTypeOwner ocTypeOwner, @NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCType", "isCompatible"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (this.checkCompatible(ocType, ocTypeOwner, psiElement).getState() == TypeCheckState.OK) {
                return true;
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    public boolean isSuperType(final OCType p0, final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //     6: astore_3       
        //     7: aload_0        
        //     8: aload_3        
        //     9: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    12: astore          4
        //    14: aload_1        
        //    15: aload_3        
        //    16: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    19: astore_1       
        //    20: aload           4
        //    22: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //    25: ifeq            42
        //    28: aload_1        
        //    29: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //    32: ifne            71
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    41: athrow         
        //    42: aload           4
        //    44: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCppStructType:()Z
        //    47: ifeq            116
        //    50: goto            57
        //    53: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    56: athrow         
        //    57: aload_1        
        //    58: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCppStructType:()Z
        //    61: ifeq            116
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    70: athrow         
        //    71: aload           4
        //    73: aload_1        
        //    74: aload_2        
        //    75: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //    78: ifeq            114
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //    92: aload_1        
        //    93: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //    96: if_acmpne       114
        //    99: goto            106
        //   102: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   105: athrow         
        //   106: iconst_1       
        //   107: goto            115
        //   110: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   113: athrow         
        //   114: iconst_0       
        //   115: ireturn        
        //   116: aload_1        
        //   117: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   120: ifeq            192
        //   123: aload_0        
        //   124: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   127: ifeq            192
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   136: athrow         
        //   137: aload_0        
        //   138: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   141: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   144: aload_3        
        //   145: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   148: astore          5
        //   150: aload_1        
        //   151: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   154: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   157: aload_3        
        //   158: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   161: astore          6
        //   163: aload           5
        //   165: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   168: ifeq            192
        //   171: aload           6
        //   173: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   176: ifeq            192
        //   179: goto            186
        //   182: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   185: athrow         
        //   186: iconst_1       
        //   187: ireturn        
        //   188: invokestatic    com/jetbrains/cidr/lang/types/OCType.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   191: athrow         
        //   192: aload           4
        //   194: aload_1        
        //   195: aload_3        
        //   196: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equalsAfterResolving:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   199: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  20     35     38     42     Ljava/lang/UnsupportedOperationException;
        //  28     50     53     57     Ljava/lang/UnsupportedOperationException;
        //  42     64     67     71     Ljava/lang/UnsupportedOperationException;
        //  57     81     84     88     Ljava/lang/UnsupportedOperationException;
        //  71     99     102    106    Ljava/lang/UnsupportedOperationException;
        //  88     110    110    114    Ljava/lang/UnsupportedOperationException;
        //  116    130    133    137    Ljava/lang/UnsupportedOperationException;
        //  163    179    182    186    Ljava/lang/UnsupportedOperationException;
        //  171    188    188    192    Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0042:
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
    public String getFormatString() {
        return null;
    }
    
    public static boolean isFunctionRequiringNil(final OCSymbol<?> ocSymbol) {
        final Iterator iterator = ocSymbol.getAttributes().iterator();
        while (iterator.hasNext()) {
            final Matcher matcher = OCType.requiresNilAttributePattern.matcher(iterator.next());
            try {
                if (matcher.matches()) {
                    return true;
                }
                continue;
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
        }
        return false;
    }
    
    static {
        OCType.requiresNilAttributePattern = Pattern.compile("sentinel.*");
    }
    
    private static UnsupportedOperationException b(final UnsupportedOperationException ex) {
        return ex;
    }
    
    public enum Presentation
    {
        FULL, 
        BEST, 
        SHORT;
    }
    
    public enum TypeCheckState implements Comparable<TypeCheckState>
    {
        OK, 
        INFO, 
        WARNING, 
        ERROR_IF_CPP, 
        ERROR;
        
        public boolean isOK() {
            try {
                if (this == TypeCheckState.OK) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return false;
        }
        
        public boolean isWarning(@NotNull final PsiElement p0) {
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
            //    18: ldc             "context"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/types/OCType$TypeCheckState"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "isWarning"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/lang/types/OCType$TypeCheckState.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aload_0        
            //    45: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.WARNING:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
            //    48: if_acmpeq       89
            //    51: aload_0        
            //    52: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
            //    55: if_acmpne       97
            //    58: goto            65
            //    61: invokestatic    com/jetbrains/cidr/lang/types/OCType$TypeCheckState.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    64: athrow         
            //    65: aload_1        
            //    66: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
            //    71: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
            //    74: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
            //    79: ifne            97
            //    82: goto            89
            //    85: invokestatic    com/jetbrains/cidr/lang/types/OCType$TypeCheckState.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    88: athrow         
            //    89: iconst_1       
            //    90: goto            98
            //    93: invokestatic    com/jetbrains/cidr/lang/types/OCType$TypeCheckState.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    96: athrow         
            //    97: iconst_0       
            //    98: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  44     58     61     65     Ljava/lang/IllegalArgumentException;
            //  51     82     85     89     Ljava/lang/IllegalArgumentException;
            //  65     93     93     97     Ljava/lang/IllegalArgumentException;
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        public boolean isError(@Nullable final PsiElement p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
            //     4: if_acmpeq       56
            //     7: aload_1        
            //     8: ifnull          64
            //    11: goto            18
            //    14: invokestatic    com/jetbrains/cidr/lang/types/OCType$TypeCheckState.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    17: athrow         
            //    18: aload_0        
            //    19: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
            //    22: if_acmpne       64
            //    25: goto            32
            //    28: invokestatic    com/jetbrains/cidr/lang/types/OCType$TypeCheckState.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    31: athrow         
            //    32: aload_1        
            //    33: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
            //    38: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
            //    41: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
            //    46: ifeq            64
            //    49: goto            56
            //    52: invokestatic    com/jetbrains/cidr/lang/types/OCType$TypeCheckState.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    55: athrow         
            //    56: iconst_1       
            //    57: goto            65
            //    60: invokestatic    com/jetbrains/cidr/lang/types/OCType$TypeCheckState.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    63: athrow         
            //    64: iconst_0       
            //    65: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      11     14     18     Ljava/lang/IllegalArgumentException;
            //  7      25     28     32     Ljava/lang/IllegalArgumentException;
            //  18     49     52     56     Ljava/lang/IllegalArgumentException;
            //  32     60     60     64     Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class TypeCheckResult
    {
        private TypeCheckState state;
        private String message;
        private Class<? extends OCInspection> inspectionClass;
        private String clangID;
        private IntentionAction[] quickFixes;
        private PsiElement myAnnotationElement;
        private boolean myIsWithConversion;
        private OCType myTypeAfterConversion;
        private OCType myTypeBeforeConversion;
        private OCFunctionSymbol myImplicitConstructor;
        
        @Override
        public String toString() {
            String s = this.state.toString();
            if (!this.state.isOK()) {
                s = s + "(" + this.clangID + ") " + this.message + " [fixes:" + this.quickFixes.length + "]";
            }
            return s;
        }
        
        public TypeCheckResult(final TypeCheckState typeCheckState, final Class<? extends OCInspection> clazz, final String s, final IntentionAction... array) {
            this(typeCheckState, clazz, s, null, (OCType)null, array);
        }
        
        public TypeCheckResult(final TypeCheckState typeCheckState, final String message, final Class<? extends OCInspection> clazz, final String s, final IntentionAction... array) {
            this(typeCheckState, clazz, s, null, (OCType)null, array);
            this.message = message;
        }
        
        public TypeCheckResult(final TypeCheckState typeCheckState, final Class<? extends OCInspection> clazz, final String s, final PsiElement myAnnotationElement, final IntentionAction... array) {
            this(typeCheckState, clazz, s, null, (OCType)null, array);
            this.myAnnotationElement = myAnnotationElement;
        }
        
        public TypeCheckResult(final TypeCheckState state, final Class<? extends OCInspection> inspectionClass, final String clangID, final OCType ocType, final OCType ocType2, final IntentionAction... quickFixes) {
            this.state = state;
            this.inspectionClass = inspectionClass;
            this.clangID = clangID;
            this.quickFixes = quickFixes;
            this.setConversion(ocType, ocType2);
        }
        
        public TypeCheckResult(final TypeCheckState state) {
            this.state = state;
        }
        
        public TypeCheckState getState() {
            return this.state;
        }
        
        public void setState(final TypeCheckState state) {
            this.state = state;
        }
        
        public String getMessage() {
            return this.message;
        }
        
        public Class<? extends OCInspection> getInspectionClass() {
            return this.inspectionClass;
        }
        
        public String getClangID() {
            return this.clangID;
        }
        
        public IntentionAction[] getQuickFixes() {
            return this.quickFixes;
        }
        
        public void setQuickFixes(final IntentionAction[] quickFixes) {
            this.quickFixes = quickFixes;
        }
        
        @Nullable
        public PsiElement getAnnotationElement() {
            return this.myAnnotationElement;
        }
        
        public void setAnnotationElement(final PsiElement myAnnotationElement) {
            this.myAnnotationElement = myAnnotationElement;
        }
        
        public boolean isWithConversion() {
            return this.myIsWithConversion;
        }
        
        public OCType getTypeBeforeConversion() {
            return this.myTypeBeforeConversion;
        }
        
        @Nullable
        public OCType getTypeAfterConversion() {
            return this.myTypeAfterConversion;
        }
        
        public void setConversion(final OCType myTypeBeforeConversion, final OCType myTypeAfterConversion) {
            this.myIsWithConversion = true;
            this.myTypeAfterConversion = myTypeAfterConversion;
            this.myTypeBeforeConversion = myTypeBeforeConversion;
        }
        
        @Nullable
        public OCFunctionSymbol getImplicitConstructor() {
            return this.myImplicitConstructor;
        }
        
        public void setImplicitConstructor(final OCFunctionSymbol myImplicitConstructor) {
            this.myImplicitConstructor = myImplicitConstructor;
        }
        
        public boolean canBeCasted(final OCType ocType, final OCType ocType2, final PsiElement psiElement) {
            return this.state != TypeCheckState.ERROR || (ocType.isPointerCompatible(psiElement) && ocType2.isPointerCompatible(psiElement) && ocType.isPointerToObjectCompatible() == ocType2.isPointerToObjectCompatible()) || (ocType instanceof OCCppReferenceType && ((OCCppReferenceType)ocType).isRvalueRef() && ((OCCppReferenceType)ocType).getRefType().checkCompatible(ocType2, psiElement).getState() == TypeCheckState.OK);
        }
    }
}
