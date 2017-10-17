// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import com.intellij.openapi.util.Pair;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.daemon.OCGetSymbolVisitor;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

private static class CallableVisitor extends OCRecursiveVisitor
{
    private VirtualFile myContainingFile;
    private boolean isDealloc;
    private OCClassSymbol myClass;
    private IvarsInfo myIvarsInfo;
    private OCClassSymbol myOriginalClass;
    
    private CallableVisitor(final VirtualFile myContainingFile, final OCClassSymbol myClass, final OCClassSymbol myOriginalClass, final boolean isDealloc, @NotNull final IvarsInfo myIvarsInfo) {
        if (myIvarsInfo == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ivarsInfo", "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor", "<init>"));
        }
        this.myContainingFile = myContainingFile;
        this.myOriginalClass = myOriginalClass;
        this.isDealloc = isDealloc;
        this.myClass = myClass;
        this.myIvarsInfo = myIvarsInfo;
    }
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        super.visitSendMessageExpression(ocSendMessageExpression);
        if (OCElementUtil.isReleaseCall((PsiElement)ocSendMessageExpression)) {
            final OCInstanceVariableSymbol receiverIvar = OCNotReleasedIvarInspection.getReceiverIvar(ocSendMessageExpression.getReceiverExpression(), false);
            try {
                if (receiverIvar != null) {
                    this.myIvarsInfo.myReleasedIvars.add(receiverIvar);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return;
        }
        for (final OCMethodSymbol ocMethodSymbol : ocSendMessageExpression.getProbableResponders().getFilteredByStaticnessResponders()) {
            final OCPropertySymbol generatedFromProperty = ocMethodSymbol.getGeneratedFromProperty();
            Label_0112: {
                try {
                    if (generatedFromProperty == null) {
                        break Label_0112;
                    }
                    final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                    final boolean b = ocMethodSymbol2.isSetter();
                    if (b) {
                        break Label_0112;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                    final boolean b = ocMethodSymbol2.isSetter();
                    if (b) {
                        this.a(generatedFromProperty);
                        continue;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            this.a((OCSymbol)ocMethodSymbol);
        }
    }
    
    @Override
    public void visitQualifiedExpression(final OCQualifiedExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/psi/visitors/OCRecursiveVisitor.visitQualifiedExpression:(Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;)V
        //     5: aload_1        
        //     6: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    11: astore_2       
        //    12: aload_1        
        //    13: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    18: astore_3       
        //    19: aload_3        
        //    20: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //    23: ifeq            201
        //    26: aload_3        
        //    27: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //    30: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    35: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    38: astore          4
        //    40: aload           4
        //    42: ifnull          201
        //    45: aload           4
        //    47: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //    52: aload_0        
        //    53: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myClass:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    56: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //    61: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //    64: ifeq            201
        //    67: goto            74
        //    70: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    73: athrow         
        //    74: aload_0        
        //    75: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.isDealloc:Z
        //    78: ifeq            126
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_3        
        //    89: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //    92: ifeq            126
        //    95: goto            102
        //    98: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: aload_0        
        //   103: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myIvarsInfo:Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;
        //   106: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo.access$000:(Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;)Ljava/util/Set;
        //   109: aload_3        
        //   110: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   113: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   118: pop            
        //   119: goto            201
        //   122: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: aload_3        
        //   127: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   130: ifeq            182
        //   133: aload_2        
        //   134: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   137: ifeq            182
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: aload_2        
        //   148: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   151: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   156: aload_1        
        //   157: if_acmpne       182
        //   160: goto            167
        //   163: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: aload_0        
        //   168: aload_3        
        //   169: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   172: invokespecial   com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;)V
        //   175: goto            201
        //   178: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: aload_3        
        //   183: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   186: ifeq            201
        //   189: aload_0        
        //   190: aload_3        
        //   191: invokespecial   com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   194: goto            201
        //   197: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   200: athrow         
        //   201: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  40     67     70     74     Ljava/lang/IllegalArgumentException;
        //  45     81     84     88     Ljava/lang/IllegalArgumentException;
        //  74     95     98     102    Ljava/lang/IllegalArgumentException;
        //  88     122    122    126    Ljava/lang/IllegalArgumentException;
        //  126    140    143    147    Ljava/lang/IllegalArgumentException;
        //  133    160    163    167    Ljava/lang/IllegalArgumentException;
        //  147    178    178    182    Ljava/lang/IllegalArgumentException;
        //  182    194    197    201    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0074:
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
    public void visitReferenceElement(final OCReferenceElement ocReferenceElement) {
        super.visitReferenceElement(ocReferenceElement);
        if (this.isDealloc) {
            final OCSymbol resolveToSymbol = ocReferenceElement.resolveToSymbol();
            try {
                if (resolveToSymbol instanceof OCInstanceVariableSymbol) {
                    this.myIvarsInfo.myReleasedIvars.add(resolveToSymbol);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
    }
    
    @Override
    public void visitCallExpression(final OCCallExpression ocCallExpression) {
        super.visitCallExpression(ocCallExpression);
        final OCExpression functionReferenceExpression = ocCallExpression.getFunctionReferenceExpression();
        final OCGetSymbolVisitor ocGetSymbolVisitor = new OCGetSymbolVisitor();
        functionReferenceExpression.accept((PsiElementVisitor)ocGetSymbolVisitor);
        final OCSymbol symbol = ocGetSymbolVisitor.getSymbol();
        try {
            if (symbol instanceof OCFunctionSymbol) {
                this.a(symbol);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    private void a(final OCPropertySymbol ocPropertySymbol) {
        try {
            if (!ocPropertySymbol.processAccessorMethods((Processor<? super OCMethodSymbol>)(ocMethodSymbol -> {
                try {
                    if (ocMethodSymbol.isSetter()) {
                        this.a((OCSymbol)ocMethodSymbol);
                        return false;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return true;
            }), false) || !ocPropertySymbol.isRetained()) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCInstanceVariableSymbol associatedIvar = ocPropertySymbol.getAssociatedIvar();
        try {
            if (associatedIvar != null) {
                this.myIvarsInfo.myReleasedIvars.add(associatedIvar);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    private void a(final OCSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: astore_2       
        //     2: aload_2        
        //     3: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isDefinition:()Z
        //     8: ifne            18
        //    11: aload_2        
        //    12: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    17: astore_2       
        //    18: aload_1        
        //    19: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    22: ifeq            142
        //    25: aload_1        
        //    26: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    29: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    34: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    37: astore_3       
        //    38: aload_0        
        //    39: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myOriginalClass:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    42: ifnull          142
        //    45: aload_2        
        //    46: ifnull          84
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: aload_3        
        //    57: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //    62: aload_0        
        //    63: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myOriginalClass:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    66: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //    71: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //    74: ifne            142
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: aload_0        
        //    85: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myOriginalClass:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    88: aload_3        
        //    89: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.isSubclass:(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;)Z
        //    94: ifeq            142
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: new             Lcom/intellij/util/CommonProcessors$FindFirstProcessor;
        //   107: dup            
        //   108: invokespecial   com/intellij/util/CommonProcessors$FindFirstProcessor.<init>:()V
        //   111: astore          4
        //   113: aload_0        
        //   114: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myOriginalClass:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   117: aload_1        
        //   118: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   123: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;.class
        //   125: aload           4
        //   127: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.processMembers:(Ljava/lang/String;Ljava/lang/Class;Lcom/intellij/util/Processor;)Z
        //   132: pop            
        //   133: aload           4
        //   135: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.getFoundValue:()Ljava/lang/Object;
        //   138: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   141: astore_2       
        //   142: aload_2        
        //   143: ifnull          258
        //   146: aload_0        
        //   147: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myIvarsInfo:Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;
        //   150: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo.access$100:(Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;)Ljava/util/Set;
        //   153: aload_2        
        //   154: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   159: ifne            258
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aload_0        
        //   170: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myContainingFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   173: aload_2        
        //   174: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   179: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   182: ifeq            258
        //   185: goto            192
        //   188: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   191: athrow         
        //   192: aload_0        
        //   193: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.myIvarsInfo:Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;
        //   196: invokestatic    com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo.access$100:(Lcom/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$IvarsInfo;)Ljava/util/Set;
        //   199: aload_2        
        //   200: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   205: pop            
        //   206: aload_2        
        //   207: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   212: astore_3       
        //   213: aload_3        
        //   214: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   217: ifeq            227
        //   220: aload_3        
        //   221: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   226: astore_3       
        //   227: aload_3        
        //   228: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   231: ifeq            258
        //   234: aload_0        
        //   235: getfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.isDealloc:Z
        //   238: istore          4
        //   240: aload_0        
        //   241: iconst_0       
        //   242: putfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.isDealloc:Z
        //   245: aload_3        
        //   246: aload_0        
        //   247: invokeinterface com/intellij/psi/PsiElement.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //   252: aload_0        
        //   253: iload           4
        //   255: putfield        com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$CallableVisitor.isDealloc:Z
        //   258: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  38     49     52     56     Ljava/lang/IllegalArgumentException;
        //  45     77     80     84     Ljava/lang/IllegalArgumentException;
        //  56     97     100    104    Ljava/lang/IllegalArgumentException;
        //  142    162    165    169    Ljava/lang/IllegalArgumentException;
        //  146    185    188    192    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0056:
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
