// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public interface OCSendMessageExpression extends OCExpression
{
    @Nullable
    OCExpression getReceiverExpression();
    
    @NotNull
    List<OCMessageArgument> getArguments();
    
    @NotNull
    List<OCArgumentSelector> getArgumentSelectors();
    
    @NotNull
    List<OCExpression> getArgumentExpressions();
    
    @NotNull
    String getMessageSelector();
    
    boolean isVarargCall();
    
    @Nullable
    OCObjectTypeContext getReceiverContext();
    
    @NotNull
    ProbableResponders getProbableResponders();
    
    @Nullable
    String getExpectedMethodSignature();
    
    public static class ProbableResponders
    {
        private OCObjectTypeContext receiverContext;
        private List<OCMethodSymbol> allResponders;
        private List<OCMethodSymbol> filteredByStaticnessResponders;
        private OCMethodSymbol knownResponder;
        
        public ProbableResponders(final List<OCMethodSymbol> allResponders, final List<OCMethodSymbol> filteredByStaticnessResponders, final OCMethodSymbol knownResponder, final OCObjectTypeContext receiverContext) {
            this.allResponders = allResponders;
            this.filteredByStaticnessResponders = filteredByStaticnessResponders;
            this.knownResponder = knownResponder;
            this.receiverContext = receiverContext;
        }
        
        @NotNull
        public List<OCMethodSymbol> getAllResponders() {
            List<OCMethodSymbol> allResponders;
            try {
                allResponders = this.allResponders;
                if (allResponders == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders", "getAllResponders"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return allResponders;
        }
        
        @NotNull
        public List<OCMethodSymbol> getFilteredByStaticnessResponders() {
            List<OCMethodSymbol> filteredByStaticnessResponders;
            try {
                filteredByStaticnessResponders = this.filteredByStaticnessResponders;
                if (filteredByStaticnessResponders == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders", "getFilteredByStaticnessResponders"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return filteredByStaticnessResponders;
        }
        
        @Nullable
        public OCMethodSymbol getKnownResponder() {
            return this.knownResponder;
        }
        
        public boolean isStaticnessMismatch() {
            Label_0031: {
                try {
                    if (this.allResponders.size() <= 0) {
                        return false;
                    }
                    final ProbableResponders probableResponders = this;
                    final List<OCMethodSymbol> list = probableResponders.filteredByStaticnessResponders;
                    final int n = list.size();
                    if (n == 0) {
                        break Label_0031;
                    }
                    return false;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final ProbableResponders probableResponders = this;
                    final List<OCMethodSymbol> list = probableResponders.filteredByStaticnessResponders;
                    final int n = list.size();
                    if (n == 0) {
                        return true;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            return false;
        }
        
        public boolean isStaticContext() {
            Label_0027: {
                try {
                    if (this.receiverContext == null) {
                        return false;
                    }
                    final ProbableResponders probableResponders = this;
                    final OCObjectTypeContext ocObjectTypeContext = probableResponders.receiverContext;
                    final OCObjectTypeContext.StaticMode staticMode = ocObjectTypeContext.getStaticMode();
                    final OCObjectTypeContext.StaticMode staticMode2 = OCObjectTypeContext.StaticMode.STATIC;
                    if (staticMode == staticMode2) {
                        break Label_0027;
                    }
                    return false;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final ProbableResponders probableResponders = this;
                    final OCObjectTypeContext ocObjectTypeContext = probableResponders.receiverContext;
                    final OCObjectTypeContext.StaticMode staticMode = ocObjectTypeContext.getStaticMode();
                    final OCObjectTypeContext.StaticMode staticMode2 = OCObjectTypeContext.StaticMode.STATIC;
                    if (staticMode == staticMode2) {
                        return true;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            return false;
        }
        
        @Nullable
        public OCObjectType getReceiverType() {
            try {
                if (this.receiverContext != null) {
                    return this.receiverContext.getType();
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return null;
        }
        
        @Nullable
        public OCType getReceiverOriginalType() {
            try {
                if (this.receiverContext != null) {
                    return this.receiverContext.getOriginalType();
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return null;
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
}
