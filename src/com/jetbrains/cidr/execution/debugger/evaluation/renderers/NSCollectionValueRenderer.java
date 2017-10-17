// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import java.util.regex.Matcher;
import java.util.Arrays;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrElementValue;
import com.intellij.openapi.util.Couple;
import java.util.Iterator;
import java.util.Collection;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrValue;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrDictionaryEntryValue;
import java.util.ArrayList;
import com.intellij.xdebugger.frame.XCompositeNode;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrDebuggerTypesHelper;
import com.jetbrains.cidr.execution.debugger.evaluation.ValueRendererFactory;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;
import java.util.regex.Pattern;
import java.util.List;

public class NSCollectionValueRenderer extends NSContainerValueRenderer
{
    private static final List<Pattern> KNOWN_TYPES;
    private static final Pattern BROKEN_SWIFT_CF_TYPES;
    private final Kind myKind;
    
    public NSCollectionValueRenderer(@NotNull final CidrPhysicalValue cidrPhysicalValue, @NotNull final Kind myKind) {
        if (cidrPhysicalValue == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSCollectionValueRenderer", "<init>"));
        }
        if (myKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSCollectionValueRenderer", "<init>"));
        }
        super(cidrPhysicalValue);
        this.myKind = myKind;
    }
    
    @Nullable
    public static Kind getNSCollectionKind(@NotNull final ValueRendererFactory.FactoryContext factoryContext) {
        try {
            if (factoryContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSCollectionValueRenderer", "getNSCollectionKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
        final LLValue llValue = factoryContext.getLLValue();
        final LLValueData llValueData = factoryContext.getLLValueData();
        try {
            if (!llValueData.isValidPointer()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw e(ex2);
        }
        final String unwrapSwiftOptionalTypeIfNecessary = CidrDebuggerTypesHelper.unwrapSwiftOptionalTypeIfNecessary(llValue.getType());
        try {
            if (!NSCollectionValueRenderer.KNOWN_TYPES.stream().anyMatch(pattern -> pattern.matcher(unwrapSwiftOptionalTypeIfNecessary).matches())) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw e(ex3);
        }
        try {
            if (unwrapSwiftOptionalTypeIfNecessary.contains("Dictionary")) {
                return Kind.DICTIONARY;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw e(ex4);
        }
        try {
            if (unwrapSwiftOptionalTypeIfNecessary.contains("Set")) {
                return Kind.SET;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw e(ex5);
        }
        return Kind.ARRAY;
    }
    
    @Override
    protected boolean mayHaveChildrenViaChildrenCount() {
        return true;
    }
    
    @Nullable
    @Override
    protected Integer doComputeChildrenCount(@NotNull final EvaluationContext evaluationContext) throws ExecutionException, DebuggerCommandException {
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSCollectionValueRenderer", "doComputeChildrenCount"));
            }
        }
        catch (ExecutionException ex) {
            throw e((Exception)ex);
        }
        final String trim = StringUtil.notNullize(this.myValue.getVarData(evaluationContext).getDescription()).trim();
        Label_0087: {
            try {
                if ("0 elements".equals(trim)) {
                    break Label_0087;
                }
                final String s = "{\n}";
                final String s2 = trim;
                final boolean b = s.equals(s2);
                if (b) {
                    break Label_0087;
                }
                return super.doComputeChildrenCount(evaluationContext);
            }
            catch (ExecutionException ex2) {
                throw e((Exception)ex2);
            }
            try {
                final String s = "{\n}";
                final String s2 = trim;
                final boolean b = s.equals(s2);
                if (b) {
                    return 0;
                }
            }
            catch (ExecutionException ex3) {
                throw e((Exception)ex3);
            }
        }
        return super.doComputeChildrenCount(evaluationContext);
    }
    
    @Override
    protected void addChildrenTo(@NotNull final List<LLValue> list, @NotNull final EvaluationContext evaluationContext, @NotNull final XCompositeNode xCompositeNode, final boolean b) throws ExecutionException, DebuggerCommandException {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "children", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSCollectionValueRenderer", "addChildrenTo"));
            }
        }
        catch (ExecutionException ex) {
            throw e((Exception)ex);
        }
        try {
            if (evaluationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSCollectionValueRenderer", "addChildrenTo"));
            }
        }
        catch (ExecutionException ex2) {
            throw e((Exception)ex2);
        }
        try {
            if (xCompositeNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "container", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSCollectionValueRenderer", "addChildrenTo"));
            }
        }
        catch (ExecutionException ex3) {
            throw e((Exception)ex3);
        }
        final ArrayList<CidrDictionaryEntryValue> list2 = new ArrayList<CidrDictionaryEntryValue>(list.size());
        for (final LLValue llValue : list) {
            try {
                if (xCompositeNode.isObsolete()) {
                    return;
                }
            }
            catch (ExecutionException ex4) {
                throw e((Exception)ex4);
            }
            Couple dictionaryEntryChildren = null;
            Label_0215: {
                try {
                    if (this.myKind == Kind.DICTIONARY) {
                        dictionaryEntryChildren = NSContainerValueRenderer.getDictionaryEntryChildren(evaluationContext, llValue);
                        break Label_0215;
                    }
                }
                catch (ExecutionException ex5) {
                    throw e((Exception)ex5);
                }
                dictionaryEntryChildren = null;
            }
            final Couple couple = dictionaryEntryChildren;
            try {
                if (couple != null) {
                    list2.add(this.nextMapElementValue((LLValue)couple.first, (LLValue)couple.second));
                    continue;
                }
            }
            catch (ExecutionException ex6) {
                throw e((Exception)ex6);
            }
            list2.add((CidrDictionaryEntryValue)this.nextElementValue(llValue));
        }
        CidrValue.addAllTo((Collection<CidrValue>)list2, xCompositeNode);
    }
    
    @NotNull
    @Override
    public String getChildEvaluationExpression(@NotNull final CidrPhysicalValue cidrPhysicalValue, final boolean b) {
        try {
            if (cidrPhysicalValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSCollectionValueRenderer", "getChildEvaluationExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
        String childEvaluationExpression;
        try {
            childEvaluationExpression = getChildEvaluationExpression(this.myValue, this.myKind, cidrPhysicalValue, b);
            if (childEvaluationExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSCollectionValueRenderer", "getChildEvaluationExpression"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw e(ex2);
        }
        return childEvaluationExpression;
    }
    
    @NotNull
    static String getChildEvaluationExpression(@NotNull final CidrPhysicalValue cidrPhysicalValue, @NotNull final Kind kind, @NotNull final CidrPhysicalValue cidrPhysicalValue2, final boolean b) {
        try {
            if (cidrPhysicalValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "self", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSCollectionValueRenderer", "getChildEvaluationExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
        try {
            if (kind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSCollectionValueRenderer", "getChildEvaluationExpression"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw e(ex2);
        }
        try {
            if (cidrPhysicalValue2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSCollectionValueRenderer", "getChildEvaluationExpression"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw e(ex3);
        }
        final int index = ((CidrElementValue)cidrPhysicalValue2).getIndex();
        final StringBuilder sb = new StringBuilder();
        String s = null;
        Label_0206: {
            if (kind == Kind.SET) {
                s = "allObjects";
            }
            else {
                String s4 = null;
                Label_0204: {
                    Label_0193: {
                        try {
                            if (kind != Kind.DICTIONARY) {
                                break Label_0206;
                            }
                            final String s2 = "key";
                            final CidrPhysicalValue cidrPhysicalValue3 = cidrPhysicalValue2;
                            final String s3 = cidrPhysicalValue3.getName();
                            final boolean b2 = s2.equals(s3);
                            if (b2) {
                                break Label_0193;
                            }
                            break Label_0193;
                        }
                        catch (IllegalArgumentException ex4) {
                            throw e(ex4);
                        }
                        try {
                            final String s2 = "key";
                            final CidrPhysicalValue cidrPhysicalValue3 = cidrPhysicalValue2;
                            final String s3 = cidrPhysicalValue3.getName();
                            final boolean b2 = s2.equals(s3);
                            if (b2) {
                                s4 = "allKeys";
                                break Label_0204;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw e(ex5);
                        }
                    }
                    s4 = "allValues";
                }
                s = s4;
            }
        }
        if (cidrPhysicalValue.isSwiftContext()) {
            final String unwrapSwiftOptionalTypeIfNecessary = CidrDebuggerTypesHelper.unwrapSwiftOptionalTypeIfNecessary(cidrPhysicalValue.getType());
            String replace = null;
            if (unwrapSwiftOptionalTypeIfNecessary.startsWith("CoreFoundation.CF")) {
                replace = unwrapSwiftOptionalTypeIfNecessary.replace("CoreFoundation.CF", "NS").replace("Ref", "");
            }
            try {
                if (replace != null) {
                    sb.append('(');
                }
            }
            catch (IllegalArgumentException ex6) {
                throw e(ex6);
            }
            try {
                sb.append(cidrPhysicalValue.getEvaluationExpression());
                if (cidrPhysicalValue.isSwiftOptional()) {
                    sb.append('!');
                }
            }
            catch (IllegalArgumentException ex7) {
                throw e(ex7);
            }
            try {
                if (replace != null) {
                    sb.append(" as ").append(replace).append(')');
                }
            }
            catch (IllegalArgumentException ex8) {
                throw e(ex8);
            }
            try {
                if (s != null) {
                    sb.append('.').append(s);
                }
            }
            catch (IllegalArgumentException ex9) {
                throw e(ex9);
            }
            String string;
            try {
                string = sb.append("[").append(index).append(']').toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSCollectionValueRenderer", "getChildEvaluationExpression"));
                }
            }
            catch (IllegalArgumentException ex10) {
                throw e(ex10);
            }
            return string;
        }
        sb.append("(id)[");
        final String evaluationExpression = cidrPhysicalValue.getEvaluationExpression();
        String string2 = null;
        Label_0481: {
            try {
                if (s != null) {
                    sb.append('[').append(evaluationExpression).append(' ').append(s).append(']');
                    break Label_0481;
                }
            }
            catch (IllegalArgumentException ex11) {
                throw e(ex11);
            }
            sb.append(evaluationExpression);
            try {
                string2 = sb.append(" objectAtIndex:").append(index).append(']').toString();
                if (string2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSCollectionValueRenderer", "getChildEvaluationExpression"));
                }
            }
            catch (IllegalArgumentException ex12) {
                throw e(ex12);
            }
        }
        return string2;
    }
    
    static {
        KNOWN_TYPES = Arrays.asList(Pattern.compile("(const )?_*NS(CF)?(Mutable)?Array\\w? \\*"), Pattern.compile("(const )?_*NS(CF)?(Mutable)?Set\\w? \\*"), Pattern.compile("(const )?_*NS(CF)?(Mutable)?Dictionary\\w? \\*"), Pattern.compile("Foundation\\.NS(Mutable)?Array"), Pattern.compile("Foundation\\.NS(Mutable)?Set"), Pattern.compile("Foundation\\.NS(Mutable)?Dictionary"), Pattern.compile("(const )?CF(Mutable)?ArrayRef"), Pattern.compile("(const )?CF(Mutable)?SetRef"), Pattern.compile("(const )?CF(Mutable)?DictionaryRef"), Pattern.compile("CoreFoundation\\.CF(Mutable)?Array(Ref)?"), Pattern.compile("CoreFoundation\\.CF(Mutable)?Set(Ref)?"), Pattern.compile("CoreFoundation\\.CF(Mutable)?Dictionary(Ref)?"));
        BROKEN_SWIFT_CF_TYPES = Pattern.compile("(const )?CF(Mutable)?((Array)|(Dictionary))Ref");
    }
    
    private static Exception e(final Exception ex) {
        return ex;
    }
    
    public enum Kind
    {
        ARRAY, 
        SET, 
        DICTIONARY;
    }
    
    public static class Factory implements ValueRendererFactory
    {
        @Nullable
        @Override
        public ValueRenderer createRenderer(@NotNull final FactoryContext factoryContext) throws ExecutionException, DebuggerCommandException {
            try {
                if (factoryContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/evaluation/renderers/NSCollectionValueRenderer$Factory", "createRenderer"));
                }
            }
            catch (ExecutionException ex) {
                throw b(ex);
            }
            try {
                if (!factoryContext.getSettings().COCOA_RENDERERS_ENABLED) {
                    return null;
                }
            }
            catch (ExecutionException ex2) {
                throw b(ex2);
            }
            final Kind nsCollectionKind = NSCollectionValueRenderer.getNSCollectionKind(factoryContext);
            try {
                if (nsCollectionKind == null) {
                    return null;
                }
            }
            catch (ExecutionException ex3) {
                throw b(ex3);
            }
            Label_0111: {
                try {
                    if (!factoryContext.getLLValueData().isSynthetic()) {
                        break Label_0111;
                    }
                    final Pattern pattern = NSCollectionValueRenderer.BROKEN_SWIFT_CF_TYPES;
                    final FactoryContext factoryContext2 = factoryContext;
                    final LLValue llValue = factoryContext2.getLLValue();
                    final String s = llValue.getType();
                    final Matcher matcher = pattern.matcher(s);
                    final boolean b = matcher.matches();
                    if (b) {
                        break Label_0111;
                    }
                    return new NSCollectionValueRenderer(factoryContext.getPhysicalValue(), nsCollectionKind);
                }
                catch (ExecutionException ex4) {
                    throw b(ex4);
                }
                try {
                    final Pattern pattern = NSCollectionValueRenderer.BROKEN_SWIFT_CF_TYPES;
                    final FactoryContext factoryContext2 = factoryContext;
                    final LLValue llValue = factoryContext2.getLLValue();
                    final String s = llValue.getType();
                    final Matcher matcher = pattern.matcher(s);
                    final boolean b = matcher.matches();
                    if (b) {
                        return new NSEnumerableCollectionValueRenderer(factoryContext.getPhysicalValue(), nsCollectionKind);
                    }
                }
                catch (ExecutionException ex5) {
                    throw b(ex5);
                }
            }
            return new NSCollectionValueRenderer(factoryContext.getPhysicalValue(), nsCollectionKind);
        }
        
        private static ExecutionException b(final ExecutionException ex) {
            return ex;
        }
    }
}
