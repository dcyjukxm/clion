// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import java.util.HashMap;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCObjectType;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.Map;

public class OCSelectorAdHocResolver
{
    private static final Map<String, SelectorMethod> ourSelectorMethodsMap;
    private static final SelectorMethod[] ourSelectorMethods;
    
    public static boolean isPerformSelectorMethod(final String s) {
        Label_0028: {
            try {
                if (!OCSelectorAdHocResolver.ourSelectorMethodsMap.containsKey(s)) {
                    return false;
                }
                final String s2 = s;
                final String s3 = "performSelector";
                final boolean b = s2.startsWith(s3);
                if (b) {
                    break Label_0028;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final String s2 = s;
                final String s3 = "performSelector";
                final boolean b = s2.startsWith(s3);
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
    
    @Nullable
    public static OCObjectTypeContext getActionTargetContext(final OCExpression ocExpression) {
        final OCExpression topmostParenthesized = OCParenthesesUtils.topmostParenthesized(ocExpression);
        OCExpression receiverExpression = null;
        if (topmostParenthesized.getParent() instanceof OCMessageArgument) {
            final OCSendMessageExpression ocSendMessageExpression = (OCSendMessageExpression)topmostParenthesized.getParent().getParent();
            final OCMethodSymbol knownResponder = ocSendMessageExpression.getProbableResponders().getKnownResponder();
            if (knownResponder != null) {
                final int a = a(knownResponder, topmostParenthesized.getContainingFile());
                if (a == 0) {
                    receiverExpression = ocSendMessageExpression.getReceiverExpression();
                }
                else if (a != -1) {
                    final List<OCExpression> argumentExpressions = ocSendMessageExpression.getArgumentExpressions();
                    if (a - 1 < argumentExpressions.size()) {
                        receiverExpression = argumentExpressions.get(a - 1);
                    }
                }
            }
        }
        if (receiverExpression != null) {
            final OCObjectTypeContext typeContext = receiverExpression.getTypeContext(true, false);
            try {
                if (typeContext == null) {
                    return null;
                }
                final OCObjectTypeContext ocObjectTypeContext = typeContext;
                final OCObjectType ocObjectType = ocObjectTypeContext.getType();
                final String s = ocObjectType.getName();
                final String s2 = "NSObject";
                final boolean b = s.equals(s2);
                if (!b) {
                    return typeContext;
                }
                return null;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCObjectTypeContext ocObjectTypeContext = typeContext;
                final OCObjectType ocObjectType = ocObjectTypeContext.getType();
                final String s = ocObjectType.getName();
                final String s2 = "NSObject";
                final boolean b = s.equals(s2);
                if (!b) {
                    return typeContext;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    private static int a(@NotNull final OCMethodSymbol ocMethodSymbol, final PsiFile psiFile) {
        try {
            if (ocMethodSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "method", "com/jetbrains/cidr/lang/resolve/OCSelectorAdHocResolver", "getTargetParameterIndex"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final SelectorMethod selectorMethod = OCSelectorAdHocResolver.ourSelectorMethodsMap.get(ocMethodSymbol.getName());
        Label_0158: {
            Label_0085: {
                try {
                    if (selectorMethod == null) {
                        return -1;
                    }
                    final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                    final OCClassSymbol ocClassSymbol = ((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol2).getParent();
                    final boolean b = ocClassSymbol instanceof OCProtocolSymbol;
                    if (b) {
                        break Label_0085;
                    }
                    break Label_0158;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                    final OCClassSymbol ocClassSymbol = ((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol2).getParent();
                    final boolean b = ocClassSymbol instanceof OCProtocolSymbol;
                    if (!b) {
                        break Label_0158;
                    }
                    if (!selectorMethod.mySearchInProtocol) {
                        break Label_0158;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            final OCType resolvedFromText = OCReferenceType.resolvedFromText("id", selectorMethod.myClassName, psiFile);
            Label_0146: {
                try {
                    if (!resolvedFromText.isPointerToObject()) {
                        return -1;
                    }
                    final OCType ocType = resolvedFromText;
                    final OCType ocType2 = ocType.getTerminalType();
                    final OCObjectType ocObjectType = (OCObjectType)ocType2;
                    final OCMethodSymbol ocMethodSymbol3 = ocMethodSymbol;
                    final OCProtocolSymbol ocProtocolSymbol = ((OCSymbolWithParent<T, OCProtocolSymbol>)ocMethodSymbol3).getParent();
                    final OCProtocolSymbol ocProtocolSymbol2 = ocProtocolSymbol;
                    final boolean b2 = ocObjectType.implementsProtocol(ocProtocolSymbol2);
                    if (b2) {
                        break Label_0146;
                    }
                    return -1;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    final OCType ocType = resolvedFromText;
                    final OCType ocType2 = ocType.getTerminalType();
                    final OCObjectType ocObjectType = (OCObjectType)ocType2;
                    final OCMethodSymbol ocMethodSymbol3 = ocMethodSymbol;
                    final OCProtocolSymbol ocProtocolSymbol = ((OCSymbolWithParent<T, OCProtocolSymbol>)ocMethodSymbol3).getParent();
                    final OCProtocolSymbol ocProtocolSymbol2 = ocProtocolSymbol;
                    final boolean b2 = ocObjectType.implementsProtocol(ocProtocolSymbol2);
                    if (b2) {
                        return selectorMethod.myTargetIndex;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            return -1;
        }
        final OCType resolvedFromText2 = OCReferenceType.resolvedFromText(selectorMethod.myClassName, psiFile);
        Label_0205: {
            try {
                if (!(resolvedFromText2 instanceof OCObjectType)) {
                    return -1;
                }
                final OCMethodSymbol ocMethodSymbol4 = ocMethodSymbol;
                final OCClassSymbol ocClassSymbol2 = ((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol4).getParent();
                final OCClassSymbol ocClassSymbol3 = ocClassSymbol2;
                final OCObjectType ocObjectType2 = (OCObjectType)resolvedFromText2;
                final OCObjectType ocObjectType3 = ocObjectType2;
                final OCClassSymbol ocClassSymbol4 = ocObjectType3.getClassSymbol();
                final boolean b3 = ocClassSymbol3.isSubclass(ocClassSymbol4);
                if (b3) {
                    break Label_0205;
                }
                return -1;
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            try {
                final OCMethodSymbol ocMethodSymbol4 = ocMethodSymbol;
                final OCClassSymbol ocClassSymbol2 = ((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol4).getParent();
                final OCClassSymbol ocClassSymbol3 = ocClassSymbol2;
                final OCObjectType ocObjectType2 = (OCObjectType)resolvedFromText2;
                final OCObjectType ocObjectType3 = ocObjectType2;
                final OCClassSymbol ocClassSymbol4 = ocObjectType3.getClassSymbol();
                final boolean b3 = ocClassSymbol3.isSubclass(ocClassSymbol4);
                if (b3) {
                    return selectorMethod.myTargetIndex;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return -1;
    }
    
    static {
        ourSelectorMethodsMap = new HashMap<String, SelectorMethod>();
        ourSelectorMethods = new SelectorMethod[] { new SelectorMethod("CADisplayLink", "displayLinkWithTarget:selector:", 1), new SelectorMethod("NSArray", "makeObjectsPerformSelector:", -1), new SelectorMethod("NSArray", "makeObjectsPerformSelector:withObject:", -1), new SelectorMethod("NSArray", "sortedArrayUsingSelector:", -1), new SelectorMethod("NSAssertionHandler", "handleFailureInMethod:object:file:lineNumber:description:", -1), new SelectorMethod("NSComparisonPredicate", "customSelector", -1), new SelectorMethod("NSComparisonPredicate", "initWithLeftExpression:rightExpression:customSelector:", 1), new SelectorMethod("NSComparisonPredicate", "predicateWithLeftExpression:rightExpression:customSelector:", 1), new SelectorMethod("NSDecimalNumberBehaviors", "exceptionDuringOperation:error:leftOperand:rightOperand:", -1), new SelectorMethod("NSDictionary", "keysSortedByValueUsingSelector:", -1), new SelectorMethod("NSInvocation", "selector", -1), new SelectorMethod("NSInvocation", "setSelector:", -1), new SelectorMethod("NSInvocationOperation", "initWithTarget:selector:object:", 1), new SelectorMethod("NSMutableArray", "sortUsingSelector:", -1), new SelectorMethod("NSNotificationCenter", "addObserver:selector:name:object:", 1), new SelectorMethod("NSObject", "attemptRecoveryFromError:optionIndex:delegate:didRecoverSelector:contextInfo:", -1), new SelectorMethod("NSObject", "cancelPreviousPerformRequestsWithTarget:selector:object:", 1), new SelectorMethod("NSObject", "doesNotRecognizeSelector:", -1), new SelectorMethod("NSObject", "forwardingTargetForSelector:", -1), new SelectorMethod("NSObject", "instanceMethodForSelector:", 0), new SelectorMethod("NSObject", "instanceMethodSignatureForSelector:", 0), new SelectorMethod("NSObject", "instancesRespondToSelector:", 0), new SelectorMethod("NSObject", "methodForSelector:", 0), new SelectorMethod("NSObject", "methodSignatureForSelector:", 0), new SelectorMethod("NSObject", "performSelector:", 0, true), new SelectorMethod("NSObject", "performSelector:onThread:withObject:waitUntilDone:", 0), new SelectorMethod("NSObject", "performSelector:onThread:withObject:waitUntilDone:modes:", 0), new SelectorMethod("NSObject", "performSelector:withObject:", 0, true), new SelectorMethod("NSObject", "performSelector:withObject:afterDelay:", 0), new SelectorMethod("NSObject", "performSelector:withObject:afterDelay:inModes:", 0), new SelectorMethod("NSObject", "performSelector:withObject:withObject:", 0, true), new SelectorMethod("NSObject", "performSelectorInBackground:withObject:", 0), new SelectorMethod("NSObject", "performSelectorOnMainThread:withObject:waitUntilDone:", 0), new SelectorMethod("NSObject", "performSelectorOnMainThread:withObject:waitUntilDone:modes:", 0), new SelectorMethod("NSObject", "resolveClassMethod:", 0), new SelectorMethod("NSObject", "resolveInstanceMethod:", 0), new SelectorMethod("NSObject", "respondsToSelector:", 0, true), new SelectorMethod("NSRunLoop", "cancelPerformSelector:target:argument:", 2), new SelectorMethod("NSRunLoop", "performSelector:target:argument:order:modes:", 2), new SelectorMethod("NSSet", "makeObjectsPerformSelector:", -1), new SelectorMethod("NSSet", "makeObjectsPerformSelector:withObject:", -1), new SelectorMethod("NSSortDescriptor", "initWithKey:ascending:selector:", -1), new SelectorMethod("NSSortDescriptor", "selector", -1), new SelectorMethod("NSSortDescriptor", "sortDescriptorWithKey:ascending:selector:", -1), new SelectorMethod("NSThread", "detachNewThreadSelector:toTarget:withObject:", 2), new SelectorMethod("NSThread", "initWithTarget:selector:object:", 1), new SelectorMethod("NSTimer", "initWithFireDate:interval:target:selector:userInfo:repeats:", 3), new SelectorMethod("NSTimer", "scheduledTimerWithTimeInterval:target:selector:userInfo:repeats:", 2), new SelectorMethod("NSTimer", "timerWithTimeInterval:target:selector:userInfo:repeats:", 2), new SelectorMethod("NSUndoManager", "registerUndoWithTarget:selector:object:", 1), new SelectorMethod("SenTestCase", "afterTestIteration:selector:", 0), new SelectorMethod("SenTestCase", "beforeTestIteration:selector:", 0), new SelectorMethod("SenTestCase", "failureAction", -1), new SelectorMethod("SenTestCase", "initWithSelector:", 0), new SelectorMethod("SenTestCase", "numberOfTestIterationsForTestWithSelector:", -1), new SelectorMethod("SenTestCase", "selector", -1), new SelectorMethod("SenTestCase", "setFailureAction:", 0), new SelectorMethod("SenTestCase", "setUpTestWithSelector:", 0), new SelectorMethod("SenTestCase", "tearDownTestWithSelector:", 0), new SelectorMethod("SenTestCase", "testCaseWithSelector:", 0), new SelectorMethod("UIApplication", "sendAction:to:from:forEvent:", 2), new SelectorMethod("UIBarButtonItem", "initWithBarButtonSystemItem:target:action:", 2), new SelectorMethod("UIBarButtonItem", "initWithImage:landscapeImagePhone:style:target:action:", 3), new SelectorMethod("UIBarButtonItem", "initWithImage:style:target:action:", 3), new SelectorMethod("UIBarButtonItem", "initWithTitle:style:target:action:", 3), new SelectorMethod("UIControl", "addTarget:action:forControlEvents:", 1), new SelectorMethod("UIControl", "removeTarget:action:forControlEvents:", 1), new SelectorMethod("UIControl", "sendAction:to:forEvent:", 2), new SelectorMethod("UIDocumentInteractionControllerDelegate", "documentInteractionController:canPerformAction:", -1), new SelectorMethod("UIDocumentInteractionControllerDelegate", "documentInteractionController:performAction:", -1), new SelectorMethod("UIGestureRecognizer", "addTarget:action:", 1), new SelectorMethod("UIGestureRecognizer", "initWithTarget:action:", 1), new SelectorMethod("UIGestureRecognizer", "removeTarget:action:", 1), new SelectorMethod("UILocalizedIndexedCollation", "sectionForObject:collationStringSelector:", 1), new SelectorMethod("UILocalizedIndexedCollation", "sortedArrayFromArray:collationStringSelector:", -1), new SelectorMethod("UIMenuItem", "initWithTitle:action:", -1), new SelectorMethod("UIResponder", "canPerformAction:withSender:", -1), new SelectorMethod("UIScreen", "displayLinkWithTarget:selector:", 1), new SelectorMethod("UITableViewDelegate", "tableView:canPerformAction:forRowAtIndexPath:withSender:", -1), new SelectorMethod("UITableViewDelegate", "tableView:performAction:forRowAtIndexPath:withSender:", -1), new SelectorMethod("UIView", "setAnimationDidStopSelector:", -1), new SelectorMethod("UIView", "setAnimationWillStartSelector:", -1) };
        for (final SelectorMethod selectorMethod : OCSelectorAdHocResolver.ourSelectorMethods) {
            OCSelectorAdHocResolver.ourSelectorMethodsMap.put(selectorMethod.myMethodSelector, selectorMethod);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class SelectorMethod
    {
        String myClassName;
        String myMethodSelector;
        int myTargetIndex;
        boolean mySearchInProtocol;
        
        private SelectorMethod(final String myClassName, final String myMethodSelector, final int myTargetIndex, final boolean mySearchInProtocol) {
            this.myClassName = myClassName;
            this.myMethodSelector = myMethodSelector;
            this.myTargetIndex = myTargetIndex;
            this.mySearchInProtocol = mySearchInProtocol;
        }
        
        private SelectorMethod(final String s, final String s2, final int n) {
            this(s, s2, n, false);
        }
    }
}
