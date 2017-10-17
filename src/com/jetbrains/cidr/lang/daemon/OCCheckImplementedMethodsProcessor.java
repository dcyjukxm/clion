// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCChangeTypeIntentionAction;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.quickfixes.OCRemoveElementsIntentionAction;
import com.jetbrains.cidr.lang.quickfixes.OCMakePropertyDynamicQuickFix;
import com.jetbrains.cidr.lang.quickfixes.OCSynthesizePropertyQuickFix;
import com.jetbrains.cidr.lang.quickfixes.OCImplementPropertyAccessorsQuickFix;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiElement;
import com.intellij.lang.annotation.Annotation;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.quickfixes.OCImplementAllMethodsIntentionAction;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.quickfixes.OCImplementMethodIntentionAction;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.jetbrains.cidr.lang.daemon.clang.OCClangMessageFinder;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.symbols.objc.OCSynthesizeSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.CommonProcessors;
import java.util.HashSet;
import java.util.LinkedList;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCObjectType;
import java.util.Set;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.Processor;

class OCCheckImplementedMethodsProcessor implements Processor<OCMethodSymbol>
{
    private OCAnnotatorSink mySink;
    private OCImplementationSymbol implementationSymbol;
    private List<String> unimplementedMethods;
    private Set<String> declaredInClasses;
    private boolean wasUnimplementedProperty;
    private boolean isImplementationCheckMode;
    @Nullable
    private OCObjectType mySuperType;
    
    OCCheckImplementedMethodsProcessor(final OCAnnotatorSink mySink, final OCImplementationSymbol implementationSymbol, @Nullable final OCObjectType mySuperType, final boolean isImplementationCheckMode) {
        this.unimplementedMethods = new LinkedList<String>();
        this.declaredInClasses = new HashSet<String>();
        this.wasUnimplementedProperty = false;
        this.mySink = mySink;
        this.implementationSymbol = implementationSymbol;
        this.isImplementationCheckMode = isImplementationCheckMode;
        this.mySuperType = mySuperType;
    }
    
    public boolean process(final OCMethodSymbol ocMethodSymbol) {
        if (this.implementationSymbol == null) {
            return true;
        }
        final CommonProcessors.FindFirstProcessor<OCMethodSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCMethodSymbol>() {
            public boolean process(final OCMethodSymbol ocMethodSymbol) {
                return ocMethodSymbol.isStatic() != ocMethodSymbol.isStatic() || super.process((Object)ocMethodSymbol);
            }
        };
        final OCImplementationSymbol ocImplementationSymbol = (this.implementationSymbol.getCategoryName() != null) ? this.implementationSymbol.getMainImplementation() : null;
        this.implementationSymbol.processMembers(ocMethodSymbol.getName(), (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor);
        if (ocImplementationSymbol != null) {
            ocImplementationSymbol.processMembers(ocMethodSymbol.getName(), (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor);
        }
        if (ocMethodSymbol.getParent() instanceof OCProtocolSymbol && this.mySuperType != null) {
            this.mySuperType.processMembers(ocMethodSymbol.getName(), OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)findFirstProcessor, true, false);
        }
        final OCMethodSymbol ocMethodSymbol2 = (OCMethodSymbol)findFirstProcessor.getFoundValue();
        final OCPropertySymbol generatedFromProperty = ocMethodSymbol.getGeneratedFromProperty();
        final String nameWithKindLowercase = ((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol).getParent().getNameWithKindLowercase();
        String string = null;
        if (generatedFromProperty != null) {
            string = generatedFromProperty.getNameWithKindLowercase() + ((generatedFromProperty.getParent() instanceof OCProtocolSymbol) ? (" declared in " + nameWithKindLowercase) : "");
            final CommonProcessors.FindFirstProcessor findFirstProcessor2 = new CommonProcessors.FindFirstProcessor();
            this.implementationSymbol.processMembers(generatedFromProperty.getName(), (Class<? extends OCMemberSymbol>)OCSynthesizeSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor2);
            if (ocImplementationSymbol != null) {
                ocImplementationSymbol.processMembers(generatedFromProperty.getName(), (Class<? extends OCMemberSymbol>)OCSynthesizeSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor2);
            }
            final OCSynthesizeSymbol ocSynthesizeSymbol = (OCSynthesizeSymbol)findFirstProcessor2.getFoundValue();
            if (findFirstProcessor2.isFound() && ocMethodSymbol2 != null && this.isImplementationCheckMode) {
                return this.a(ocMethodSymbol, ocMethodSymbol2, generatedFromProperty, string, ocSynthesizeSymbol);
            }
            if ((!this.wasUnimplementedProperty || !this.isImplementationCheckMode) && !findFirstProcessor2.isFound() && ocMethodSymbol2 == null && !ocMethodSymbol.isOptional() && !OCCompilerFeatures.supportsAutosynthesis((PsiFile)generatedFromProperty.getContainingOCFile())) {
                return this.a(ocMethodSymbol, generatedFromProperty, string);
            }
        }
        else if (ocMethodSymbol2 == null && this.a(ocMethodSymbol, ocImplementationSymbol, nameWithKindLowercase)) {
            return true;
        }
        if (ocMethodSymbol2 == null) {
            if (!ocMethodSymbol.isOptional() && !ocMethodSymbol.isUnavailable() && generatedFromProperty == null) {
                if (!this.isImplementationCheckMode) {
                    final OCMethod ocMethod = ((OCSymbol<OCMethod>)ocMethodSymbol).locateDefinition();
                    if (ocMethod == null) {
                        return true;
                    }
                    final Annotation addWarningAnnotation = this.mySink.addWarningAnnotation(ocMethod.getNameIdentifier(), OCInspections.NotImplementedMethods.class, OCClangMessageFinder.getInstance().getMethodNotImplemented(), ocMethodSymbol.getNameWithKindUppercase() + " is not implemented");
                    this.mySink.registerQuickFix(addWarningAnnotation, (IntentionAction)new OCImplementMethodIntentionAction(ocMethodSymbol));
                    this.mySink.registerQuickFix(addWarningAnnotation, (IntentionAction)new OCImplementAllMethodsIntentionAction(((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol).getParent()));
                }
                else {
                    this.unimplementedMethods.add("'" + ocMethodSymbol.getName() + "'");
                    this.declaredInClasses.add(((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol).getParent().getNameWithKindLowercase());
                }
            }
        }
        else if (this.isImplementationCheckMode) {
            final PsiElement locateDefinition = ocMethodSymbol2.locateDefinition();
            if (!(locateDefinition instanceof OCMethod)) {
                return true;
            }
            this.a(ocMethodSymbol, ocMethodSymbol2, locateDefinition, generatedFromProperty, string, nameWithKindLowercase);
            this.a(ocMethodSymbol, locateDefinition, generatedFromProperty, string, nameWithKindLowercase);
        }
        return true;
    }
    
    private boolean a(final OCMethodSymbol ocMethodSymbol, final OCPropertySymbol ocPropertySymbol, final String s) {
        this.wasUnimplementedProperty = true;
        final PsiNameIdentifierOwner psiNameIdentifierOwner = ((OCSymbol<PsiNameIdentifierOwner>)(this.isImplementationCheckMode ? this.implementationSymbol : ocMethodSymbol)).locateDefinition();
        if (psiNameIdentifierOwner == null) {
            return true;
        }
        final Annotation addWarningAnnotation = this.mySink.addWarningAnnotation(psiNameIdentifierOwner.getNameIdentifier(), OCInspections.NoGetterOrSetter.class, "CIDR", StringUtil.capitalize(s) + " requires " + (ocMethodSymbol.isGetter() ? "getter " : "setter ") + ocMethodSymbol.getNameWithKindLowercase() + " - provide implementation or use '@synthesize' or '@dynamic'");
        if (this.isImplementationCheckMode) {
            this.mySink.registerQuickFix(addWarningAnnotation, (IntentionAction)new OCImplementAllMethodsIntentionAction(this.implementationSymbol));
        }
        else {
            this.mySink.registerQuickFix(addWarningAnnotation, (IntentionAction)new OCImplementPropertyAccessorsQuickFix(ocPropertySymbol));
        }
        this.mySink.registerQuickFix(addWarningAnnotation, (IntentionAction)new OCSynthesizePropertyQuickFix(this.implementationSymbol, ocPropertySymbol));
        this.mySink.registerQuickFix(addWarningAnnotation, (IntentionAction)new OCMakePropertyDynamicQuickFix(this.implementationSymbol, ocPropertySymbol));
        return true;
    }
    
    private boolean a(final OCMethodSymbol ocMethodSymbol, final OCMethodSymbol ocMethodSymbol2, final OCPropertySymbol ocPropertySymbol, final String s, final OCSynthesizeSymbol ocSynthesizeSymbol) {
        final PsiElement locateDefinition = ocMethodSymbol2.locateDefinition();
        if (!(locateDefinition instanceof OCMethod) || !ocSynthesizeSymbol.isSynthesize()) {
            return true;
        }
        final CommonProcessors.FindFirstProcessor<OCMethodSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCMethodSymbol>() {
            public boolean process(final OCMethodSymbol ocMethodSymbol) {
                return ocMethodSymbol == ocMethodSymbol || ocMethodSymbol.getGeneratedFromProperty() != ocPropertySymbol || super.process((Object)ocMethodSymbol);
            }
        };
        ((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol).getParent().processMembers(OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)findFirstProcessor);
        if (findFirstProcessor.isFound() && ((OCMethodSymbol)findFirstProcessor.getFoundValue()).getAssociatedSymbol() == null) {
            return true;
        }
        final Annotation addWarningAnnotation = this.mySink.addWarningAnnotation(((OCMethod)locateDefinition).getNameIdentifier(), OCInspections.AccessorsWereOverridden.class, "CIDR", "Accessor methods of synthesized " + s + " were overridden");
        this.mySink.registerQuickFix(addWarningAnnotation, (IntentionAction)new OCRemoveElementsIntentionAction(locateDefinition, "Remove " + ocMethodSymbol2.getNameWithKindLowercase(), "Remove accessor"));
        this.mySink.registerQuickFix(addWarningAnnotation, (IntentionAction)new OCRemoveElementsIntentionAction(ocSynthesizeSymbol.locateDefinition(), "Remove '@synthesize'/'@dynamic' statement"));
        return true;
    }
    
    private boolean a(final OCMethodSymbol ocMethodSymbol, final OCImplementationSymbol ocImplementationSymbol, final String s) {
        final OCInterfaceSymbol interface1 = this.implementationSymbol.getInterface();
        final CommonProcessors.FindFirstProcessor<OCMethodSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCMethodSymbol>() {
            protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
                return ocMethodSymbol.isStatic() == ocMethodSymbol.isStatic() && ocMethodSymbol.getGeneratedFromProperty() != null;
            }
        };
        if (interface1 != null) {
            interface1.processMembers(ocMethodSymbol.getName(), (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor);
        }
        if (!findFirstProcessor.isFound() && ocImplementationSymbol != null) {
            final OCInterfaceSymbol interface2 = ocImplementationSymbol.getInterface();
            if (interface2 != null) {
                interface2.processMembers(ocMethodSymbol.getName(), (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor);
            }
        }
        if (!findFirstProcessor.isFound()) {
            final OCInterfaceSymbol interface3 = this.implementationSymbol.getInterface(false, "");
            if (interface3 != null && !interface3.equals(interface1)) {
                interface3.processMembers(ocMethodSymbol.getName(), (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor);
            }
        }
        if (findFirstProcessor.isFound()) {
            final OCPropertySymbol generatedFromProperty = ((OCMethodSymbol)findFirstProcessor.getFoundValue()).getGeneratedFromProperty();
            final Ref create = Ref.create((Object)false);
            generatedFromProperty.processSynthesizes((Processor<? super OCSynthesizeSymbol>)(ocSynthesizeSymbol -> {
                final String nameWithKindLowercase = generatedFromProperty.getNameWithKindLowercase();
                if (ocMethodSymbol.isGetter()) {
                    this.a(ocMethodSymbol, null, ocSynthesizeSymbol.locateDefinition(), generatedFromProperty, nameWithKindLowercase, s);
                }
                else {
                    this.a(ocMethodSymbol, ocSynthesizeSymbol.locateDefinition(), generatedFromProperty, nameWithKindLowercase, s);
                }
                create.set((Object)true);
                return true;
            }));
            if ((boolean)create.get() || OCCompilerFeatures.supportsAutosynthesis((PsiFile)this.implementationSymbol.getContainingOCFile())) {
                return true;
            }
        }
        return false;
    }
    
    private void a(final OCMethodSymbol ocMethodSymbol, @Nullable final OCMethodSymbol ocMethodSymbol2, final PsiElement psiElement, final OCPropertySymbol ocPropertySymbol, final String s, final String s2) {
        final OCType ocType = (ocMethodSymbol2 != null) ? ocMethodSymbol2.getReturnType().resolve(psiElement.getContainingFile()) : ocPropertySymbol.getType();
        final OCType resolve = ocMethodSymbol.getReturnType().resolve(psiElement.getContainingFile());
        if (!resolve.isSuperType(ocType, psiElement)) {
            Annotation annotation;
            if (ocPropertySymbol != null) {
                final boolean objCGetter = OCNameSuggester.isObjCGetter(ocMethodSymbol.getName());
                annotation = this.mySink.addWarningAnnotation((psiElement instanceof OCMethod) ? ((OCMethod)psiElement).getNameIdentifier() : psiElement, OCInspections.AssociatedTypeMismatch.class, "warn_non_covariant_overriding_ret_types", (objCGetter ? "Getter" : "Setter") + " method for " + s + " must return '" + resolve.getName((PsiElement)ocMethodSymbol.getContainingOCFile()) + "' rather than '" + ocType.getName((PsiElement)psiElement.getContainingFile()) + "'");
                if (objCGetter && ocMethodSymbol2 != null) {
                    this.mySink.registerQuickFix(annotation, (IntentionAction)new OCChangeTypeIntentionAction(ocPropertySymbol, ocType));
                }
            }
            else {
                annotation = this.mySink.addWarningAnnotation((PsiElement)((OCMethod)psiElement).getReturnTypeElement(), OCInspections.AssociatedTypeMismatch.class, "warn_non_covariant_overriding_ret_types", "Return type '" + ocType.getName((PsiElement)ocMethodSymbol2.getContainingOCFile()) + "' is different from declared in " + s2 + " ('" + resolve.getName((PsiElement)ocMethodSymbol.getContainingOCFile()) + "')");
                this.mySink.registerQuickFix(annotation, (IntentionAction)new OCChangeTypeIntentionAction(ocMethodSymbol, ocType, " in the interface", false));
            }
            if (ocMethodSymbol2 != null) {
                this.mySink.registerQuickFix(annotation, (IntentionAction)new OCChangeTypeIntentionAction(ocMethodSymbol2, resolve, " in the implementation", false));
            }
            else {
                this.mySink.registerQuickFix(annotation, (IntentionAction)new OCChangeTypeIntentionAction(ocMethodSymbol, ocType));
                this.mySink.registerQuickFix(annotation, (IntentionAction)new OCChangeTypeIntentionAction(ocPropertySymbol, resolve));
            }
        }
    }
    
    private void a(final OCMethodSymbol ocMethodSymbol, final PsiElement psiElement, final OCPropertySymbol ocPropertySymbol, final String s, final String s2) {
        final List<OCMethodSymbol.SelectorPartSymbol> selectors = ocMethodSymbol.getSelectors();
        final List<OCMethodSelectorPart> list = (psiElement instanceof OCMethod) ? ((OCMethod)psiElement).getParameters() : Collections.singletonList((OCMethodSelectorPart)null);
        for (int n = 0; n < selectors.size() && n < list.size(); ++n) {
            final OCDeclaratorSymbol parameter = selectors.get(n).getParameter();
            if (parameter != null) {
                final OCType resolve = parameter.getType().resolve(psiElement.getContainingFile());
                final OCMethodSelectorPart ocMethodSelectorPart = list.get(n);
                final OCType resolve2 = ((ocMethodSelectorPart != null) ? ocMethodSelectorPart.getType() : ocPropertySymbol.getType()).resolve(psiElement.getContainingFile());
                final Object o = (ocMethodSelectorPart != null) ? ocMethodSelectorPart.getTypeElement() : psiElement;
                if (!resolve2.isSuperType(resolve, psiElement) && o != null) {
                    Annotation annotation;
                    if (ocPropertySymbol != null) {
                        annotation = this.mySink.addWarningAnnotation((PsiElement)o, OCInspections.AssociatedTypeMismatch.class, "warn_non_contravariant_overriding_param_types", "Setter method for " + s + " must take the parameter of type '" + resolve.getName((PsiElement)ocMethodSymbol.getContainingOCFile()) + "' rather than '" + resolve2.getName((PsiElement)psiElement.getContainingFile()) + "'");
                        if (ocMethodSelectorPart != null) {
                            this.mySink.registerQuickFix(annotation, (IntentionAction)new OCChangeTypeIntentionAction(ocPropertySymbol, resolve2));
                        }
                    }
                    else {
                        annotation = this.mySink.addWarningAnnotation((PsiElement)o, OCInspections.AssociatedTypeMismatch.class, "warn_non_contravariant_overriding_param_types", "Parameter type '" + resolve2.getName((PsiElement)psiElement.getContainingFile()) + "' is different from declared in " + s2 + " ('" + resolve.getName((PsiElement)ocMethodSymbol.getContainingOCFile()) + "')");
                        this.mySink.registerQuickFix(annotation, (IntentionAction)new OCChangeTypeIntentionAction(parameter, resolve2, " in the interface", false));
                    }
                    if (ocMethodSelectorPart != null) {
                        this.mySink.registerQuickFix(annotation, (IntentionAction)new OCChangeTypeIntentionAction(ocMethodSelectorPart.getLocalSymbol(), resolve, " in the implementation", false));
                    }
                    else {
                        this.mySink.registerQuickFix(annotation, (IntentionAction)new OCChangeTypeIntentionAction(parameter, resolve2));
                        this.mySink.registerQuickFix(annotation, (IntentionAction)new OCChangeTypeIntentionAction(ocPropertySymbol, resolve));
                    }
                }
            }
        }
    }
    
    private static String a(final Collection<String> collection, final int n) {
        final StringBuilder sb = new StringBuilder();
        for (final String s : collection) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            if (sb.length() >= n) {
                sb.append("...");
                break;
            }
            sb.append(s);
        }
        return sb.toString();
    }
    
    public String getUnimplementedMethods() {
        final ArrayList<String> list = new ArrayList<String>(this.unimplementedMethods);
        Collections.sort((List<Comparable>)list);
        return a(list, 70);
    }
    
    public String getDeclaredInClasses() {
        final ArrayList<String> list = new ArrayList<String>(this.declaredInClasses);
        Collections.sort((List<Comparable>)list);
        return a(list, 30);
    }
    
    public boolean wasUnimplementedProperty() {
        return this.wasUnimplementedProperty;
    }
}
