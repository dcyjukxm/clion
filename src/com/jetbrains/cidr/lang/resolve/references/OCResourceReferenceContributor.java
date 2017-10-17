// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.util.ProcessingContext;
import java.util.ArrayList;
import java.util.Collection;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.intellij.patterns.PatternCondition;
import com.intellij.patterns.PlatformPatterns;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.patterns.ElementPattern;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiReferenceRegistrar;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.Processor;
import com.intellij.psi.PsiReferenceContributor;

public class OCResourceReferenceContributor extends PsiReferenceContributor
{
    private static final OCResourceCompletionProvider[] PROVIDERS;
    
    public static boolean processReferenceProviders(final Processor<OCResourceCompletionProvider> processor) {
        return ContainerUtil.process((Object[])OCResourceReferenceContributor.PROVIDERS, (Processor)processor);
    }
    
    public void registerReferenceProviders(@NotNull final PsiReferenceRegistrar psiReferenceRegistrar) {
        try {
            if (psiReferenceRegistrar == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "registrar", "com/jetbrains/cidr/lang/resolve/references/OCResourceReferenceContributor", "registerReferenceProviders"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (final OCResourceCompletionProvider ocResourceCompletionProvider : OCResourceReferenceContributor.PROVIDERS) {
            psiReferenceRegistrar.registerReferenceProvider((ElementPattern)a(ocResourceCompletionProvider), (PsiReferenceProvider)ocResourceCompletionProvider);
        }
    }
    
    private static PsiElementPattern.Capture<OCLiteralExpression> a(final OCResourceCompletionProvider ocResourceCompletionProvider) {
        return (PsiElementPattern.Capture<OCLiteralExpression>)((PsiElementPattern.Capture)PlatformPatterns.psiElement((Class)OCLiteralExpression.class).with((PatternCondition)new IsStringLiteral())).inside((ElementPattern)PlatformPatterns.psiElement((Class)OCMessageArgument.class).with((PatternCondition)new IsResourceMethod(ocResourceCompletionProvider)));
    }
    
    static {
        final ArrayList arrayList = ContainerUtil.newArrayList();
        final OCResourceCompletionProviders[] array = (OCResourceCompletionProviders[])Extensions.getExtensions((ExtensionPointName)OCResourceCompletionProviders.EP_NAME);
        for (int length = array.length, i = 0; i < length; ++i) {
            arrayList.addAll(array[i].getProviders());
        }
        PROVIDERS = (OCResourceCompletionProvider[])arrayList.toArray(new OCResourceCompletionProvider[arrayList.size()]);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class IsStringLiteral extends PatternCondition<OCLiteralExpression>
    {
        public IsStringLiteral() {
            super((String)null);
        }
        
        public boolean accepts(@NotNull final OCLiteralExpression ocLiteralExpression, final ProcessingContext processingContext) {
            try {
                if (ocLiteralExpression == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "literal", "com/jetbrains/cidr/lang/resolve/references/OCResourceReferenceContributor$IsStringLiteral", "accepts"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (OCElementUtil.getStringLiteral((PsiElement)ocLiteralExpression) != null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return false;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class IsResourceMethod extends PatternCondition<OCMessageArgument>
    {
        private OCResourceCompletionProvider myProvider;
        
        public IsResourceMethod(final OCResourceCompletionProvider myProvider) {
            super((String)null);
            this.myProvider = myProvider;
        }
        
        public boolean accepts(@NotNull final OCMessageArgument ocMessageArgument, final ProcessingContext processingContext) {
            try {
                if (ocMessageArgument == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argument", "com/jetbrains/cidr/lang/resolve/references/OCResourceReferenceContributor$IsResourceMethod", "accepts"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return ocMessageArgument.getArgumentSelector().getSelectorName().equals(this.myProvider.getArgumentSelector());
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
