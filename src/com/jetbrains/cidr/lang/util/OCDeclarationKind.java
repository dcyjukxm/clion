// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import java.util.Collections;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCSynthesizePropertiesList;
import com.jetbrains.cidr.lang.psi.OCProperty;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCClassPredeclarationList;
import com.jetbrains.cidr.lang.psi.OCUnion;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCEnum;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCUndefDirective;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.psi.PsiElement;
import com.intellij.util.PlatformUtils;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.text.StringUtil;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCElement;

public enum OCDeclarationKind
{
    Import(PlatformUtils.isAppCode() ? "Imports" : "Includes", (Class<? extends OCElement>[])new Class[] { OCIncludeDirective.class }), 
    Macro((Class<? extends OCElement>[])new Class[] { OCDefineDirective.class, OCUndefDirective.class }), 
    Typedef(new Class[] { OCDeclaration.class }) {
        @Override
        public boolean isKindOf(final PsiElement psiElement) {
            return psiElement instanceof OCDeclaration && ((OCDeclaration)psiElement).isTypedef();
        }
    }, 
    Enum("Enumerations", new Class[] { OCEnum.class }) {
        @Override
        public boolean isKindOf(final PsiElement psiElement) {
            if (psiElement instanceof OCDeclaration) {
                final OCTypeElement typeElement = ((OCDeclaration)psiElement).getTypeElement();
                if (this.isKindOf((typeElement != null) ? typeElement.getFirstChild() : null)) {
                    return true;
                }
            }
            return super.isKindOf(psiElement);
        }
    }, 
    Constant(new Class[] { OCDeclaration.class }) {
        @Override
        public boolean isKindOf(final PsiElement psiElement) {
            if (!(psiElement instanceof OCDeclaration)) {
                return false;
            }
            final List<OCDeclarator> declarators = ((OCDeclaration)psiElement).getDeclarators();
            final OCSymbol ocSymbol = declarators.isEmpty() ? null : declarators.get(0).getSymbol();
            return ocSymbol instanceof OCDeclaratorSymbol && ((OCDeclaratorSymbol)ocSymbol).isConst();
        }
    }, 
    Global("Global variables", new Class[] { OCDeclaration.class }) {
        @Override
        public boolean isKindOf(final PsiElement psiElement) {
            return psiElement instanceof OCDeclaration && !OCDeclarationKind$4.Typedef.isKindOf(psiElement) && !OCDeclarationKind$4.Constant.isKindOf(psiElement) && !OCDeclarationKind$4.Function.isKindOf(psiElement) && !OCDeclarationKind$4.FunctionPredecl.isKindOf(psiElement);
        }
    }, 
    Struct("Structs, unions and C++ classes", new Class[] { OCStruct.class, OCUnion.class }) {
        @Override
        public boolean isKindOf(final PsiElement psiElement) {
            if (psiElement instanceof OCDeclaration) {
                final OCTypeElement typeElement = ((OCDeclaration)psiElement).getTypeElement();
                if (this.isKindOf((typeElement != null) ? typeElement.getFirstChild() : null)) {
                    return true;
                }
            }
            return super.isKindOf(psiElement);
        }
    }, 
    ClassPredef("Objective-C class predeclarations", true, (Class<? extends OCElement>[])new Class[] { OCClassPredeclarationList.class }), 
    FunctionPredecl("Function predeclarations", new Class[] { OCFunctionDeclaration.class }) {
        @Override
        public boolean isKindOf(final PsiElement psiElement) {
            return psiElement instanceof OCFunctionDeclaration && !OCDeclarationKind$6.Function.isKindOf(psiElement);
        }
    }, 
    Function((Class<? extends OCElement>[])new Class[] { OCFunctionDefinition.class }), 
    Class("Objective-C classes and protocols", true, (Class<? extends OCElement>[])new Class[] { OCClassDeclaration.class }), 
    Property((Class<? extends OCElement>[])new Class[] { OCProperty.class }), 
    Synthesize((Class<? extends OCElement>[])new Class[] { OCSynthesizePropertiesList.class }), 
    InitMethod("Init methods", new Class[] { OCMethod.class }) {
        @Override
        public boolean isKindOf(final PsiElement psiElement) {
            return psiElement instanceof OCMethod && OCElementUtil.startsWithWord(((OCMethod)psiElement).getSelector(), "init");
        }
    }, 
    StaticMethod("Class methods", new Class[] { OCMethod.class }) {
        @Override
        public boolean isKindOf(final PsiElement psiElement) {
            return psiElement instanceof OCMethod && !((OCMethod)psiElement).isInstanceMethod();
        }
    }, 
    InstanceMethod("Instance methods", new Class[] { OCMethod.class }) {
        @Override
        public boolean isKindOf(final PsiElement psiElement) {
            return psiElement instanceof OCMethod && ((OCMethod)psiElement).isInstanceMethod();
        }
    }, 
    DeallocMethod("Dealloc method", new Class[] { OCMethod.class }) {
        @Override
        public boolean isKindOf(final PsiElement psiElement) {
            return psiElement instanceof OCMethod && ((OCMethod)psiElement).getSelector().equals("dealloc");
        }
    };
    
    private String myName;
    private final boolean myObjectiveC;
    private Class<? extends OCElement>[] myClasses;
    public static final List<OCDeclarationKind> ourFileDeclarationKinds;
    public static final List<OCDeclarationKind> ourClassDeclarationKinds;
    public static final List<OCDeclarationKind> ourMethodKinds;
    
    private OCDeclarationKind(final Class<? extends OCElement>[] myClasses) {
        this.myName = StringUtil.pluralize(this.name());
        this.myObjectiveC = false;
        this.myClasses = myClasses;
    }
    
    private OCDeclarationKind(final String s2, final Class<? extends OCElement>[] array) {
        this(s2, false, array);
    }
    
    private OCDeclarationKind(final String myName, final boolean myObjectiveC, final Class<? extends OCElement>[] myClasses) {
        this.myName = myName;
        this.myObjectiveC = myObjectiveC;
        this.myClasses = myClasses;
    }
    
    public String getName() {
        return this.myName;
    }
    
    public boolean isAvailable() {
        return ApplicationManager.getApplication().isUnitTestMode() || PlatformUtils.isAppCode() || !this.myObjectiveC;
    }
    
    public boolean isKindOf(final PsiElement psiElement) {
        final Class<? extends OCElement>[] myClasses = this.myClasses;
        for (int length = myClasses.length, i = 0; i < length; ++i) {
            if (myClasses[i].isInstance(psiElement)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
    
    private static List<OCDeclarationKind> b(final PsiElement psiElement) {
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(psiElement.getProject()).getCustomSettings((Class)OCCodeStyleSettings.class);
        List<OCDeclarationKind> list;
        if (ocCodeStyleSettings != null) {
            list = ((psiElement instanceof OCFile) ? ocCodeStyleSettings.FILE_DECLARATIONS_ORDER : ocCodeStyleSettings.CLASS_DECLARATIONS_ORDER);
        }
        else {
            list = ((psiElement instanceof OCFile) ? OCDeclarationKind.ourFileDeclarationKinds : OCDeclarationKind.ourClassDeclarationKinds);
        }
        return list;
    }
    
    @Nullable
    public static OCDeclarationKind getDeclarationKind(final PsiElement psiElement) {
        for (final OCDeclarationKind ocDeclarationKind : values()) {
            if (ocDeclarationKind.isKindOf(psiElement)) {
                return ocDeclarationKind;
            }
        }
        return null;
    }
    
    private static int a(final PsiElement psiElement) {
        if (psiElement instanceof OCClassDeclaration) {
            Object instanceVariablesList = ((OCClassDeclaration)psiElement).getInstanceVariablesList();
            if (((PsiElement)instanceVariablesList).getTextLength() == 0) {
                final PsiElement previousNonEmptyElement = OCElementUtil.getPreviousNonEmptyElement((PsiElement)instanceVariablesList);
                if (previousNonEmptyElement != null) {
                    instanceVariablesList = previousNonEmptyElement;
                }
            }
            return ((PsiElement)instanceVariablesList).getTextOffset() + ((PsiElement)instanceVariablesList).getTextLength();
        }
        return -1;
    }
    
    public int getChildrenStartOffset(final PsiElement psiElement) {
        return this.getChildrenStartOffset(psiElement, false);
    }
    
    public int getChildrenStartOffset(final PsiElement psiElement, final boolean b) {
        final PsiElement[] children = psiElement.getChildren();
        if (!b) {
            for (final PsiElement psiElement2 : children) {
                if (this.isKindOf(psiElement2)) {
                    return psiElement2.getTextRange().getStartOffset();
                }
            }
        }
        final List<OCDeclarationKind> b2 = b(psiElement);
        final int index = b2.indexOf(this);
        if (index <= 0) {
            return a(psiElement);
        }
        return b2.get(index - 1).getChildrenEndOffset(psiElement);
    }
    
    public int getChildrenEndOffset(final PsiElement psiElement) {
        final List<OCDeclarationKind> b = b(psiElement);
        final int index = b.indexOf(this);
        final PsiElement[] children = psiElement.getChildren();
        if (index == -1) {
            return a(psiElement);
        }
        int n = -1;
        for (int i = index; i >= 0; --i) {
            final OCDeclarationKind ocDeclarationKind = b.get(i);
            for (int j = children.length - 1; j >= 0; --j) {
                if (ocDeclarationKind.isKindOf(children[j])) {
                    final int n2 = children[j].getTextRange().getEndOffset() + ((children[j] instanceof OCIncludeDirective) ? 1 : 0);
                    n = ((n == -1 || n < n2) ? n2 : n);
                }
            }
            if (n != -1) {
                return n;
            }
        }
        for (int k = index + 1; k < b.size(); ++k) {
            final OCDeclarationKind ocDeclarationKind2 = b.get(k);
            for (int l = 0; l < children.length; ++l) {
                if (ocDeclarationKind2.isKindOf(children[l])) {
                    final int n3 = (l > 0) ? children[l - 1].getTextRange().getEndOffset() : children[l].getTextRange().getStartOffset();
                    n = ((n == -1 || n > n3) ? n3 : n);
                }
            }
        }
        return (n != -1) ? n : a(psiElement);
    }
    
    static {
        final ArrayList<OCDeclarationKind> list = new ArrayList<OCDeclarationKind>();
        final ArrayList<OCDeclarationKind> list2 = new ArrayList<OCDeclarationKind>();
        final ArrayList<OCDeclarationKind> list3 = new ArrayList<OCDeclarationKind>();
        for (final OCDeclarationKind ocDeclarationKind : values()) {
            if (ocDeclarationKind.isAvailable()) {
                if (ocDeclarationKind.ordinal() < OCDeclarationKind.Property.ordinal()) {
                    list.add(ocDeclarationKind);
                }
                else {
                    list2.add(ocDeclarationKind);
                }
            }
        }
        list3.add(OCDeclarationKind.InitMethod);
        list3.add(OCDeclarationKind.StaticMethod);
        list3.add(OCDeclarationKind.InstanceMethod);
        list3.add(OCDeclarationKind.DeallocMethod);
        ourFileDeclarationKinds = Collections.unmodifiableList((List<?>)list);
        ourClassDeclarationKinds = Collections.unmodifiableList((List<?>)list2);
        ourMethodKinds = Collections.unmodifiableList((List<?>)list3);
    }
}
