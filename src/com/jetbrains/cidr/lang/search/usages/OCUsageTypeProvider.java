// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages;

import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCProtocolList;
import com.jetbrains.cidr.lang.psi.OCCppBaseClause;
import com.jetbrains.cidr.lang.psi.OCSuperClassRef;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCClassPredeclaration;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.jetbrains.cidr.lang.psi.OCPropertyAttribute;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import com.intellij.usages.impl.rules.UsageType;
import com.intellij.usages.impl.rules.UsageTypeProvider;

public class OCUsageTypeProvider implements UsageTypeProvider
{
    private static final UsageType SUPER_CLASS;
    private static final UsageType PROTOCOL_LIST;
    private static final UsageType MESSAGE_RECEIVER;
    private static final UsageType QUALIFIER;
    private static final UsageType MEMBER_ACCESS;
    private static final UsageType VARIABLE_DECLARATION;
    private static final UsageType PARAMETER_DECLARATION;
    private static final UsageType METHOD_CALL;
    private static final UsageType PROPERTY_ATTRIBUTE;
    private static final UsageType SELECTOR_EXPRESSION;
    private static final UsageType SYNTHESIZE;
    private static final UsageType CLASS_PREDECLARATION;
    private static final UsageType PROTOCOL_PREDECLARATION;
    private static final UsageType NAMESPACE_QUALIFIER;
    private static final UsageType INSIDE_MACRO_SUBSTITUTION;
    
    public UsageType getUsageType(final PsiElement psiElement) {
        final PsiElement parent = psiElement.getParent();
        final PsiElement psiElement2 = (parent != null) ? parent.getParent() : null;
        if (OCElementUtil.isPartOfMacroSubstitution(psiElement)) {
            return OCUsageTypeProvider.INSIDE_MACRO_SUBSTITUTION;
        }
        if (psiElement instanceof OCCppNamespaceQualifier) {
            return OCUsageTypeProvider.NAMESPACE_QUALIFIER;
        }
        if (psiElement instanceof OCSendMessageExpression) {
            return OCUsageTypeProvider.METHOD_CALL;
        }
        if (psiElement instanceof OCPropertyAttribute) {
            return OCUsageTypeProvider.PROPERTY_ATTRIBUTE;
        }
        if (psiElement instanceof OCSelectorExpression) {
            return OCUsageTypeProvider.SELECTOR_EXPRESSION;
        }
        if (psiElement instanceof OCIncludeDirective) {
            return UsageType.CLASS_IMPORT;
        }
        if (psiElement instanceof OCClassPredeclaration) {
            return ((OCClassPredeclaration)psiElement).isProtocol() ? OCUsageTypeProvider.PROTOCOL_PREDECLARATION : OCUsageTypeProvider.CLASS_PREDECLARATION;
        }
        if (psiElement instanceof OCQualifiedExpression) {
            return OCUsageTypeProvider.MEMBER_ACCESS;
        }
        if (parent instanceof OCSuperClassRef || parent instanceof OCCppBaseClause) {
            return OCUsageTypeProvider.SUPER_CLASS;
        }
        if (parent instanceof OCProtocolList || parent instanceof OCReferenceElement) {
            return OCUsageTypeProvider.PROTOCOL_LIST;
        }
        if (psiElement2 instanceof OCCastExpression) {
            return UsageType.CLASS_CAST_TO;
        }
        if (parent instanceof OCSynthesizeProperty) {
            return OCUsageTypeProvider.SYNTHESIZE;
        }
        if (parent instanceof OCReferenceExpression) {
            if (psiElement2 instanceof OCSendMessageExpression) {
                return OCUsageTypeProvider.MESSAGE_RECEIVER;
            }
            if (psiElement2 instanceof OCQualifiedExpression) {
                return OCUsageTypeProvider.QUALIFIER;
            }
        }
        if (parent instanceof OCTypeElement) {
            if (psiElement2 instanceof OCMethod) {
                return UsageType.CLASS_METHOD_RETURN_TYPE;
            }
            if (psiElement2 instanceof OCMethodSelectorPart || psiElement2 instanceof OCParameterDeclaration) {
                return OCUsageTypeProvider.PARAMETER_DECLARATION;
            }
            if (psiElement2 instanceof OCDeclaration) {
                return OCUsageTypeProvider.VARIABLE_DECLARATION;
            }
        }
        return null;
    }
    
    static {
        SUPER_CLASS = new UsageType("Superclass reference");
        PROTOCOL_LIST = new UsageType("Protocol reference");
        MESSAGE_RECEIVER = new UsageType("Message receiver");
        QUALIFIER = new UsageType("Qualifier");
        MEMBER_ACCESS = new UsageType("Member access");
        VARIABLE_DECLARATION = new UsageType("Variable declaration");
        PARAMETER_DECLARATION = new UsageType("Parameter declaration");
        METHOD_CALL = new UsageType("Method call");
        PROPERTY_ATTRIBUTE = new UsageType("Usage in property attribute");
        SELECTOR_EXPRESSION = new UsageType("Selector expression");
        SYNTHESIZE = new UsageType("Usage in synthesize statement");
        CLASS_PREDECLARATION = new UsageType("Class predeclaration");
        PROTOCOL_PREDECLARATION = new UsageType("Protocol predeclaration");
        NAMESPACE_QUALIFIER = new UsageType("Namespace qualifier");
        INSIDE_MACRO_SUBSTITUTION = new UsageType("Inside macro substitution");
    }
}
