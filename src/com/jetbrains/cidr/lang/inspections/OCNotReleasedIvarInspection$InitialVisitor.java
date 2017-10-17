// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import java.util.HashMap;
import com.jetbrains.cidr.lang.psi.OCArgumentSelector;
import java.util.Map;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.dfa.OCControlFlowGraph;
import com.jetbrains.cidr.lang.dfa.OCNotReleasedVariablesChecker;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.daemon.OCAnnotatorSink;
import com.jetbrains.cidr.lang.dfa.OCDataFlowAnalyzer;
import com.jetbrains.cidr.lang.daemon.OCNullAnnotatorSink;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

private class InitialVisitor extends OCRecursiveVisitor
{
    private IvarsInfo myIvarsInfo;
    private OCMethodSymbol curMethod;
    
    public InitialVisitor(final IvarsInfo myIvarsInfo) {
        if (myIvarsInfo == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ivarsInfo", "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$InitialVisitor", "<init>"));
        }
        this.myIvarsInfo = myIvarsInfo;
    }
    
    public void visitFile(final PsiFile psiFile) {
        for (final PsiElement psiElement : psiFile.getChildren()) {
            try {
                if (psiElement instanceof OCImplementation) {
                    this.myIvarsInfo.myTraversedCallables.clear();
                    this.visitImplementation((OCImplementation)psiElement);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
    }
    
    @Override
    public void visitImplementation(final OCImplementation ocImplementation) {
        super.visitImplementation(ocImplementation);
        final VirtualFile virtualFile = ocImplementation.getContainingFile().getVirtualFile();
        try {
            if (virtualFile == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCObjectType ocObjectType = ocImplementation.getType();
        OCImplementationSymbol implementation = null;
        Label_0049: {
            try {
                if (ocObjectType != null) {
                    implementation = ocObjectType.getImplementation();
                    break Label_0049;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            implementation = null;
        }
        final OCImplementationSymbol ocImplementationSymbol = implementation;
        while (ocObjectType != null) {
            OCImplementationSymbol implementation2 = ocObjectType.getImplementation();
            if (implementation2 == null) {
                final OCInterfaceSymbol interface1 = ocObjectType.getInterface();
                OCImplementationSymbol implementation3 = null;
                Label_0092: {
                    try {
                        if (interface1 != null) {
                            implementation3 = interface1.getImplementation();
                            break Label_0092;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    implementation3 = null;
                }
                implementation2 = implementation3;
                try {
                    if (implementation2 == null) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            final Processor processor = ocMethodSymbol -> {
                final PsiElement locateDefinition = ocMethodSymbol.locateDefinition();
                if (locateDefinition instanceof OCMethod) {
                    final boolean equals = "dealloc".equals(ocMethodSymbol.getName());
                    try {
                        if (!this.myIvarsInfo.myTraversedCallables.contains(ocMethodSymbol)) {
                            this.myIvarsInfo.myTraversedCallables.add(ocMethodSymbol);
                            locateDefinition.accept((PsiElementVisitor)new CallableVisitor(virtualFile, (OCClassSymbol)((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol).getParent(), (OCClassSymbol)ocImplementationSymbol, equals, this.myIvarsInfo));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    if (equals) {
                        final OCBlockStatement body = ((OCMethod)locateDefinition).getBody();
                        if (body != null) {
                            final int size = body.getStatements().size();
                            OCSendMessageExpression callToSuper = null;
                            Label_0169: {
                                try {
                                    if (size > 0) {
                                        callToSuper = OCNotReleasedIvarInspection.getCallToSuper(body.getStatements().get(size - 1));
                                        break Label_0169;
                                    }
                                }
                                catch (IllegalArgumentException ex2) {
                                    throw a(ex2);
                                }
                                callToSuper = null;
                            }
                            if (callToSuper == null) {
                                this.myIvarsInfo.myDeallocs.put(ocMethodSymbol, body.getClosingBrace());
                            }
                            else {
                                this.myIvarsInfo.myDeallocs.put(ocMethodSymbol, null);
                            }
                        }
                    }
                }
                return true;
            };
            if (OCNotReleasedIvarInspection.this.releaseInDealloc) {
                implementation2.processMembers("dealloc", (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)processor);
                final OCType resolvedFromText = OCReferenceType.resolvedFromText("NSObject", "UIApplicationDelegate", ocImplementation.getContainingFile());
                final OCType resolvedFromText2 = OCReferenceType.resolvedFromText("SenTest", ocImplementation.getContainingFile());
                final OCType resolvedFromText3 = OCReferenceType.resolvedFromText("NSManagedObject", ocImplementation.getContainingFile());
                Label_0293: {
                    Label_0268: {
                        Label_0249: {
                            Label_0224: {
                                Label_0205: {
                                    try {
                                        if (!(resolvedFromText instanceof OCObjectType)) {
                                            break Label_0224;
                                        }
                                        final OCType ocType = resolvedFromText;
                                        final OCObjectType ocObjectType2 = ocObjectType;
                                        final OCImplementation ocImplementation2 = ocImplementation;
                                        final boolean b = ocType.isCompatible(ocObjectType2, (PsiElement)ocImplementation2);
                                        if (b) {
                                            break Label_0205;
                                        }
                                        break Label_0224;
                                    }
                                    catch (IllegalArgumentException ex5) {
                                        throw a(ex5);
                                    }
                                    try {
                                        final OCType ocType = resolvedFromText;
                                        final OCObjectType ocObjectType2 = ocObjectType;
                                        final OCImplementation ocImplementation2 = ocImplementation;
                                        final boolean b = ocType.isCompatible(ocObjectType2, (PsiElement)ocImplementation2);
                                        if (b) {
                                            implementation2.processMembers("applicationWillTerminate:", (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)processor);
                                        }
                                    }
                                    catch (IllegalArgumentException ex6) {
                                        throw a(ex6);
                                    }
                                }
                                try {
                                    if (!(resolvedFromText3 instanceof OCObjectType)) {
                                        break Label_0268;
                                    }
                                    final OCType ocType2 = resolvedFromText3;
                                    final OCObjectType ocObjectType3 = ocObjectType;
                                    final OCImplementation ocImplementation3 = ocImplementation;
                                    final boolean b2 = ocType2.isCompatible(ocObjectType3, (PsiElement)ocImplementation3);
                                    if (b2) {
                                        break Label_0249;
                                    }
                                    break Label_0268;
                                }
                                catch (IllegalArgumentException ex7) {
                                    throw a(ex7);
                                }
                            }
                            try {
                                final OCType ocType2 = resolvedFromText3;
                                final OCObjectType ocObjectType3 = ocObjectType;
                                final OCImplementation ocImplementation3 = ocImplementation;
                                final boolean b2 = ocType2.isCompatible(ocObjectType3, (PsiElement)ocImplementation3);
                                if (b2) {
                                    implementation2.processMembers("didTurnIntoFault", (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)processor);
                                }
                            }
                            catch (IllegalArgumentException ex8) {
                                throw a(ex8);
                            }
                        }
                        try {
                            if (!(resolvedFromText2 instanceof OCObjectType)) {
                                break Label_0293;
                            }
                            final OCType ocType3 = resolvedFromText2;
                            final OCObjectType ocObjectType4 = ocObjectType;
                            final OCImplementation ocImplementation4 = ocImplementation;
                            final boolean b3 = ocType3.isCompatible(ocObjectType4, (PsiElement)ocImplementation4);
                            if (b3) {
                                break Label_0293;
                            }
                            break Label_0293;
                        }
                        catch (IllegalArgumentException ex9) {
                            throw a(ex9);
                        }
                    }
                    try {
                        final OCType ocType3 = resolvedFromText2;
                        final OCObjectType ocObjectType4 = ocObjectType;
                        final OCImplementation ocImplementation4 = ocImplementation;
                        final boolean b3 = ocType3.isCompatible(ocObjectType4, (PsiElement)ocImplementation4);
                        if (b3) {
                            implementation2.processMembers("tearDown", (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)processor);
                        }
                    }
                    catch (IllegalArgumentException ex10) {
                        throw a(ex10);
                    }
                }
            }
            else {
                implementation2.processMembers((String)null, (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)processor);
            }
            ocObjectType = ocObjectType.getSuperType();
        }
    }
    
    @Override
    public void visitMethod(final OCMethod ocMethod) {
        this.curMethod = ocMethod.getSymbol();
        super.visitMethod(ocMethod);
        final OCDataFlowAnalyzer ocDataFlowAnalyzer = new OCDataFlowAnalyzer((PsiElement)ocMethod, OCNullAnnotatorSink.INSTANCE, null);
        ocDataFlowAnalyzer.buildControlFlowGraph();
        for (final OCSymbol ocSymbol : ocDataFlowAnalyzer.getGraph().getLocalSymbols()) {
            try {
                if (ocSymbol.isUnnamed()) {
                    continue;
                }
                ocDataFlowAnalyzer.analyzeNotReleased(ocSymbol, new OCNotReleasedVariablesChecker(ocDataFlowAnalyzer.getGraph(), ocSymbol) {
                    @Override
                    protected void handleAssignedIvar(@NotNull final Pair<OCInstanceVariableSymbol, PsiElement> pair) {
                        try {
                            if (pair == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pair", "com/jetbrains/cidr/lang/inspections/OCNotReleasedIvarInspection$InitialVisitor$1", "handleAssignedIvar"));
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw d(ex);
                        }
                        InitialVisitor.this.myIvarsInfo.myLocalRetainedIvars.add(pair);
                    }
                    
                    private static IllegalArgumentException d(final IllegalArgumentException ex) {
                        return ex;
                    }
                }, false);
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        this.curMethod = null;
    }
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        try {
            super.visitSendMessageExpression(ocSendMessageExpression);
            if (this.curMethod == null || !OCElementUtil.isReleaseCall((PsiElement)ocSendMessageExpression)) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCInstanceVariableSymbol receiverIvar = OCNotReleasedIvarInspection.getReceiverIvar(ocSendMessageExpression.getReceiverExpression(), false);
        if (receiverIvar != null) {
            Map<OCMethodSymbol, OCArgumentSelector> map = this.myIvarsInfo.myLocalReleases.get(receiverIvar);
            if (map == null) {
                map = new HashMap<OCMethodSymbol, OCArgumentSelector>();
                this.myIvarsInfo.myLocalReleases.put(receiverIvar, map);
            }
            map.put(this.curMethod, ocSendMessageExpression.getArgumentSelectors().get(0));
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
