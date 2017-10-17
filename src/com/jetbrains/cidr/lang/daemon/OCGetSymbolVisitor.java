// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCArraySelectionExpression;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCParenthesizedExpression;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

public class OCGetSymbolVisitor extends OCVisitor
{
    private OCSymbol mySymbol;
    private int numOfDereferences;
    private boolean wasUnresolvedSymbol;
    private boolean processCalls;
    @Nullable
    private OCResolveContext myContext;
    
    public OCGetSymbolVisitor() {
    }
    
    public OCGetSymbolVisitor(@NotNull final OCResolveContext myContext) {
        if (myContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor", "<init>"));
        }
        this.myContext = myContext;
    }
    
    public OCGetSymbolVisitor(final boolean processCalls) {
        this.processCalls = processCalls;
    }
    
    @Override
    public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        try {
            ocQualifiedExpression.processTargets(ocQualifiedExpression.getSymbolName(), (Processor<OCSymbol>)findFirstProcessor, false, null, true, false, null);
            this.mySymbol = (OCSymbol)findFirstProcessor.getFoundValue();
            if (this.mySymbol == null) {
                this.wasUnresolvedSymbol = true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    @Override
    public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
        final OCElementTypes.SelfSuperToken selfSuperToken = ocReferenceExpression.getSelfSuperToken();
        Label_0070: {
            Label_0039: {
                OCGetSymbolVisitor ocGetSymbolVisitor = null;
                boolean wasUnresolvedSymbol = false;
                Label_0035: {
                    Label_0026: {
                        try {
                            if (selfSuperToken == null) {
                                break Label_0039;
                            }
                            ocGetSymbolVisitor = this;
                            final OCElementTypes.SelfSuperToken selfSuperToken2 = selfSuperToken;
                            final OCElementTypes.SelfSuperToken selfSuperToken3 = OCElementTypes.SelfSuperToken.SELF;
                            if (selfSuperToken2 == selfSuperToken3) {
                                break Label_0026;
                            }
                            break Label_0026;
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            ocGetSymbolVisitor = this;
                            final OCElementTypes.SelfSuperToken selfSuperToken2 = selfSuperToken;
                            final OCElementTypes.SelfSuperToken selfSuperToken3 = OCElementTypes.SelfSuperToken.SELF;
                            if (selfSuperToken2 == selfSuperToken3) {
                                wasUnresolvedSymbol = true;
                                break Label_0035;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                    wasUnresolvedSymbol = false;
                }
                ocGetSymbolVisitor.wasUnresolvedSymbol = wasUnresolvedSymbol;
                return;
                try {
                    if (this.myContext != null) {
                        final OCSymbol mySymbol = ocReferenceExpression.resolveToSymbol(this.myContext);
                        break Label_0070;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            final OCSymbol mySymbol = ocReferenceExpression.resolveToSymbol();
            try {
                this.mySymbol = mySymbol;
                if (this.mySymbol == null) {
                    this.wasUnresolvedSymbol = true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
    }
    
    @Override
    public void visitCallExpression(final OCCallExpression ocCallExpression) {
        try {
            if (this.processCalls) {
                ocCallExpression.getFunctionReferenceExpression().accept((PsiElementVisitor)this);
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocCallExpression.getResolvedType() instanceof OCCppReferenceType) {
                this.wasUnresolvedSymbol = true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    @Override
    public void visitAssignmentExpression(final OCAssignmentExpression ocAssignmentExpression) {
        try {
            if (ocAssignmentExpression.getOperationSign() == OCTokenTypes.EQ) {
                ocAssignmentExpression.getReceiverExpression().accept((PsiElementVisitor)this);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        if (this.processCalls) {
            final List<OCMethodSymbol> filteredByStaticnessResponders = ocSendMessageExpression.getProbableResponders().getFilteredByStaticnessResponders();
            try {
                if (filteredByStaticnessResponders.size() == 1) {
                    this.mySymbol = filteredByStaticnessResponders.get(0);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        else {
            try {
                if (ocSendMessageExpression.getResolvedType() instanceof OCCppReferenceType) {
                    this.wasUnresolvedSymbol = true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    @Override
    public void visitParenthesizedExpression(final OCParenthesizedExpression ocParenthesizedExpression) {
        final OCExpression operand = ocParenthesizedExpression.getOperand();
        try {
            if (operand != null) {
                operand.accept((PsiElementVisitor)this);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    @Override
    public void visitUnaryExpression(final OCUnaryExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCUnaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //     6: astore_2       
        //     7: aload_1        
        //     8: invokeinterface com/jetbrains/cidr/lang/psi/OCUnaryExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    13: astore_3       
        //    14: aload_2        
        //    15: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    18: if_acmpne       56
        //    21: aload_0        
        //    22: dup            
        //    23: getfield        com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.numOfDereferences:I
        //    26: iconst_1       
        //    27: iadd           
        //    28: putfield        com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.numOfDereferences:I
        //    31: aload_3        
        //    32: ifnull          102
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: aload_3        
        //    43: aload_0        
        //    44: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //    49: goto            102
        //    52: invokestatic    com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: aload_2        
        //    57: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.__IMAG__KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    60: if_acmpeq       77
        //    63: aload_2        
        //    64: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.__REAL__KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    67: if_acmpne       102
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: aload_3        
        //    78: ifnull          102
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_3        
        //    89: aload_0        
        //    90: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //    95: goto            102
        //    98: invokestatic    com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  14     35     38     42     Ljava/lang/IllegalArgumentException;
        //  21     52     52     56     Ljava/lang/IllegalArgumentException;
        //  56     70     73     77     Ljava/lang/IllegalArgumentException;
        //  63     81     84     88     Ljava/lang/IllegalArgumentException;
        //  77     95     98     102    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0077:
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
    public void visitArraySelectionExpression(final OCArraySelectionExpression ocArraySelectionExpression) {
        ++this.numOfDereferences;
        ocArraySelectionExpression.getArrayExpression().accept((PsiElementVisitor)this);
    }
    
    public OCSymbol getSymbol() {
        return this.mySymbol;
    }
    
    public int getNumOfDereferences() {
        return this.numOfDereferences;
    }
    
    public boolean wasUnresolvedSymbol() {
        return this.wasUnresolvedSymbol;
    }
    
    @Nullable
    public static OCSymbol getSymbol(final OCExpression ocExpression) {
        return getSymbol(ocExpression, new OCResolveContext((PsiElement)ocExpression));
    }
    
    @Nullable
    public static OCSymbol getSymbol(final OCExpression ocExpression, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor", "getSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCGetSymbolVisitor ocGetSymbolVisitor = new OCGetSymbolVisitor(ocResolveContext);
        try {
            if (ocExpression != null) {
                ocExpression.accept((PsiElementVisitor)ocGetSymbolVisitor);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ocGetSymbolVisitor.getSymbol();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
