// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCReturnStatement;
import com.jetbrains.cidr.lang.psi.OCCastKind;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.util.OCNumber;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import com.jetbrains.cidr.lang.psi.OCCaseStatement;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.util.ArrayUtil;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCRealType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import java.util.Iterator;
import com.jetbrains.cidr.lang.dfa.OCUnreachableCodeFinder;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.sourceglider.utils.PrettyName;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.sourceglider.domains.DomainType;
import com.jetbrains.sourceglider.symtable.KnownDomainTypes;
import java.util.HashSet;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.sourceglider.ui.ThreadCallback;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.dfa.OCControlFlowGraph;
import com.jetbrains.sourceglider.relations.RelationSignature;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.sourceglider.symtable.SymbolTable;
import com.intellij.psi.PsiFile;
import com.jetbrains.sourceglider.atttributes.Attribute;
import com.intellij.psi.PsiElement;
import java.util.Set;
import com.jetbrains.cidr.lang.util.OCElementsRange;
import com.jetbrains.cidr.lang.psi.OCCallable;
import java.util.Map;
import com.jetbrains.cidr.lang.dfa.OCNode;
import com.intellij.util.containers.MultiMap;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.lang.dfa.OCControlFlowBuilder;

public class OCContextSensitiveControlFlowBuilder extends OCControlFlowBuilder
{
    private static final Logger LOG;
    public static final String CONTEXT_SENSITIVITY = "CONTEXT_SENSITIVITY";
    public static final String GLOBAL_ANALYSIS = "GLOBAL_ANALYSIS";
    private static final int MAX_CONTEXTS_CNT = 1000000;
    private final MultiMap<OCNode, Integer> myStatementContexts;
    private final Map<OCCallable, Map<Integer, OCElementsRange>> myRangesMap;
    private Set<PsiElement> myNotUsedWrites;
    private MultiMap<OCNode, Attribute> myVariableWrites;
    private PsiFile myFile;
    private final SymbolTable mySymbolTable;
    private boolean myContextSensitivity;
    private boolean myGlobalAnalysis;
    private int myCurContext;
    private Attribute myCallableAttribute;
    private Set<OCType> myProcessedTypes;
    private List<RelationSignature> mySignatures;
    private final TuplesConsumer myConsumer;
    private Map<OCCallable, OCControlFlowGraph> myGraphs;
    private Set<OCNode> myFakeNodes;
    private Map<Integer, Integer> myFakeContexts;
    private MultiMap<OCNode, Pair<RelationSignature, Attribute[]>> myFakeNodeAttributes;
    private Map<PsiElement, BranchConditions> myBranchConditions;
    private Set<OCSymbol> myPrivateFields;
    private Set<OCSymbol> myPrivateParameters;
    private int myVisitorRecursiveCnt;
    private ThreadCallback myThreadCallback;
    private RelationSignature myAssignmentToVar;
    private RelationSignature myAssignmentToConstant;
    private RelationSignature myAssignmentToExpression;
    private RelationSignature myAssignmentToCall;
    private RelationSignature myNonInitializedDeclarator;
    private RelationSignature myVarArgument;
    private RelationSignature myConstArgument;
    private RelationSignature myParameter;
    private RelationSignature myPrivateMethod;
    private RelationSignature myPrivateField;
    private RelationSignature myEqualityToConstConditionalJump;
    private RelationSignature myNonEqualityToConstConditionalJump;
    private RelationSignature myEqualityToVarConditionalJump;
    private RelationSignature myNonEqualityToVarConditionalJump;
    private RelationSignature myInstanceOfConditionalJump;
    private RelationSignature myNonInstanceOfConditionalJump;
    private RelationSignature myUnconditionalJump;
    private RelationSignature myNodeJump;
    private RelationSignature myStartNode;
    private RelationSignature myConditionThenBranch;
    private RelationSignature myConditionElseBranch;
    private RelationSignature myShortCuttedBranch;
    private RelationSignature myContextInfo;
    private RelationSignature myExpressionType;
    private RelationSignature myConstantType;
    private RelationSignature myVariableType;
    private RelationSignature myMethodReturnType;
    private RelationSignature mySubType;
    private RelationSignature myUnknownSubType;
    private RelationSignature myPODType;
    private RelationSignature myRealTypeVariable;
    private RelationSignature myPointerTypeVariable;
    private RelationSignature myMagicTypeVariable;
    private RelationSignature myStructTypeVariable;
    private RelationSignature myCastExpression;
    private RelationSignature myDynamicCallExpression;
    private RelationSignature myGetReferenceExpression;
    private RelationSignature myDereferenceExpression;
    private RelationSignature myMessageReceiverExpression;
    private RelationSignature myCallStatement;
    private RelationSignature myExternalCallStatement;
    private RelationSignature myReturnVarStatement;
    private RelationSignature myReturnConstStatement;
    private RelationSignature myExitStatement;
    
    public OCContextSensitiveControlFlowBuilder(@NotNull final SymbolTable mySymbolTable, final TuplesConsumer myConsumer) {
        if (mySymbolTable == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolTable", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "<init>"));
        }
        this.myStatementContexts = (MultiMap<OCNode, Integer>)new MultiMap();
        this.myRangesMap = new HashMap<OCCallable, Map<Integer, OCElementsRange>>();
        this.myProcessedTypes = OCTypeUtils.newTypeSet();
        this.mySignatures = new ArrayList<RelationSignature>();
        this.myGraphs = new HashMap<OCCallable, OCControlFlowGraph>();
        this.myFakeNodes = new HashSet<OCNode>();
        this.myFakeContexts = new HashMap<Integer, Integer>();
        this.myFakeNodeAttributes = (MultiMap<OCNode, Pair<RelationSignature, Attribute[]>>)new MultiMap();
        this.myBranchConditions = new HashMap<PsiElement, BranchConditions>();
        this.myPrivateFields = new HashSet<OCSymbol>();
        this.myPrivateParameters = new HashSet<OCSymbol>();
        this.mySymbolTable = mySymbolTable;
        this.myConsumer = myConsumer;
        final DomainType domainType = mySymbolTable.getDomainType(KnownDomainTypes.DOM_OBJECTS);
        final DomainType domainType2 = mySymbolTable.getDomainType(KnownDomainTypes.DOM_CONSTANTS);
        final DomainType domainType3 = mySymbolTable.getDomainType(KnownDomainTypes.DOM_POINTERS);
        final DomainType domainType4 = mySymbolTable.getDomainType(KnownDomainTypes.DOM_BIG_INTEGERS);
        final DomainType domainType5 = mySymbolTable.getDomainType(KnownDomainTypes.DOM_METHODS);
        final DomainType domainType6 = mySymbolTable.getDomainType(KnownDomainTypes.DOM_TYPES);
        final DomainType domainType7 = mySymbolTable.getDomainType(KnownDomainTypes.DOM_INTEGERS);
        this.mySignatures.add(this.myAssignmentToVar = new RelationSignature("AssignmentToVar", new DomainType[] { domainType4, domainType3, domainType3 }));
        this.mySignatures.add(this.myAssignmentToConstant = new RelationSignature("AssignmentToConstant", new DomainType[] { domainType4, domainType3, domainType2 }));
        this.mySignatures.add(this.myAssignmentToExpression = new RelationSignature("AssignmentToExpression", new DomainType[] { domainType4, domainType3, domainType }));
        this.mySignatures.add(this.myAssignmentToCall = new RelationSignature("AssignmentToCall", new DomainType[] { domainType4, domainType3, domainType5 }));
        this.mySignatures.add(this.myNonInitializedDeclarator = new RelationSignature("NonInitializedDeclarator", new DomainType[] { domainType4, domainType3, domainType }));
        this.mySignatures.add(this.myEqualityToConstConditionalJump = new RelationSignature("EqualityToConstConditionalJump", new DomainType[] { domainType4, domainType4, domainType3, domainType2 }));
        this.mySignatures.add(this.myNonEqualityToConstConditionalJump = new RelationSignature("NonEqualityToConstConditionalJump", new DomainType[] { domainType4, domainType4, domainType3, domainType2 }));
        this.mySignatures.add(this.myEqualityToVarConditionalJump = new RelationSignature("EqualityToVarConditionalJump", new DomainType[] { domainType4, domainType4, domainType3, domainType3 }));
        this.mySignatures.add(this.myNonEqualityToVarConditionalJump = new RelationSignature("NonEqualityToVarConditionalJump", new DomainType[] { domainType4, domainType4, domainType3, domainType3 }));
        this.mySignatures.add(this.myInstanceOfConditionalJump = new RelationSignature("InstanceOfConditionalJump", new DomainType[] { domainType4, domainType4, domainType3, domainType6 }));
        this.mySignatures.add(this.myNonInstanceOfConditionalJump = new RelationSignature("NonInstanceOfConditionalJump", new DomainType[] { domainType4, domainType4, domainType3, domainType6 }));
        this.mySignatures.add(this.myUnconditionalJump = new RelationSignature("UnconditionalJump", new DomainType[] { domainType4, domainType4 }));
        this.mySignatures.add(this.myNodeJump = new RelationSignature("NodeJump", new DomainType[] { domainType5, domainType4, domainType4 }));
        this.mySignatures.add(this.myStartNode = new RelationSignature("StartNode", new DomainType[] { domainType5, domainType4 }));
        this.mySignatures.add(this.myConditionThenBranch = new RelationSignature("ConditionThenBranch", new DomainType[] { domainType4, domainType }));
        this.mySignatures.add(this.myConditionElseBranch = new RelationSignature("ConditionElseBranch", new DomainType[] { domainType4, domainType }));
        this.mySignatures.add(this.myShortCuttedBranch = new RelationSignature("ShortCuttedBranch", new DomainType[] { domainType }));
        this.mySignatures.add(this.myContextInfo = new RelationSignature("ContextInfo", new DomainType[] { domainType5, domainType4, domainType4 }));
        this.mySignatures.add(this.myVariableType = new RelationSignature("VariableType", new DomainType[] { domainType3, domainType6 }));
        this.mySignatures.add(this.myExpressionType = new RelationSignature("ExpressionType", new DomainType[] { domainType, domainType6 }));
        this.mySignatures.add(this.myConstantType = new RelationSignature("ConstantType", new DomainType[] { domainType2, domainType, domainType6 }));
        this.mySignatures.add(this.myCastExpression = new RelationSignature("CastExpression", new DomainType[] { domainType4, domainType3, domainType6, domainType }));
        this.mySignatures.add(this.myDynamicCallExpression = new RelationSignature("DynamicCallExpression", new DomainType[] { domainType4, domainType3, domainType6, domainType }));
        this.mySignatures.add(this.myGetReferenceExpression = new RelationSignature("GetReferenceExpression", new DomainType[] { domainType4, domainType3 }));
        this.mySignatures.add(this.myDereferenceExpression = new RelationSignature("DereferenceExpression", new DomainType[] { domainType4, domainType3, domainType }));
        this.mySignatures.add(this.myMessageReceiverExpression = new RelationSignature("MessageReceiverExpression", new DomainType[] { domainType4, domainType3, domainType }));
        this.mySignatures.add(this.mySubType = new RelationSignature("SubType", new DomainType[] { domainType6, domainType6 }));
        this.mySignatures.add(this.myUnknownSubType = new RelationSignature("UnknownSubType", new DomainType[] { domainType6, domainType6 }));
        this.mySignatures.add(this.myPODType = new RelationSignature("PODType", new DomainType[] { domainType6 }));
        this.mySignatures.add(this.myRealTypeVariable = new RelationSignature("RealTypeVariable", new DomainType[] { domainType3 }));
        this.mySignatures.add(this.myPointerTypeVariable = new RelationSignature("PointerTypeVariable", new DomainType[] { domainType3 }));
        this.mySignatures.add(this.myMagicTypeVariable = new RelationSignature("MagicTypeVariable", new DomainType[] { domainType3 }));
        this.mySignatures.add(this.myStructTypeVariable = new RelationSignature("StructTypeVariable", new DomainType[] { domainType3 }));
        this.mySignatures.add(this.myCallStatement = new RelationSignature("CallStatement", new DomainType[] { domainType4, domainType5 }));
        this.mySignatures.add(this.myExternalCallStatement = new RelationSignature("ExternalCallStatement", new DomainType[] { domainType4 }));
        this.mySignatures.add(this.myReturnVarStatement = new RelationSignature("ReturnVarStatement", new DomainType[] { domainType4, domainType3, domainType5 }));
        this.mySignatures.add(this.myReturnConstStatement = new RelationSignature("ReturnConstStatement", new DomainType[] { domainType4, domainType2, domainType5 }));
        this.mySignatures.add(this.myExitStatement = new RelationSignature("ExitStatement", new DomainType[] { domainType4, domainType5 }));
        this.mySignatures.add(this.myVarArgument = new RelationSignature("VarArgument", new DomainType[] { domainType4, domainType5, domainType7, domainType3 }));
        this.mySignatures.add(this.myConstArgument = new RelationSignature("ConstArgument", new DomainType[] { domainType4, domainType5, domainType7, domainType2 }));
        this.mySignatures.add(this.myParameter = new RelationSignature("Parameter", new DomainType[] { domainType5, domainType7, domainType3 }));
        this.mySignatures.add(this.myPrivateMethod = new RelationSignature("PrivateMethod", new DomainType[] { domainType5 }));
        this.mySignatures.add(this.myPrivateField = new RelationSignature("PrivateField", new DomainType[] { domainType3 }));
        this.mySignatures.add(this.myMethodReturnType = new RelationSignature("MethodReturnType", new DomainType[] { domainType5, domainType6 }));
    }
    
    public RelationSignature[] getOutputRelations() {
        return this.mySignatures.toArray(new RelationSignature[this.mySignatures.size()]);
    }
    
    @Nullable
    public OCControlFlowGraph getControlFlowGraph(@NotNull final OCCallable ocCallable) {
        try {
            if (ocCallable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callable", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getControlFlowGraph"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        return this.myGraphs.get(ocCallable);
    }
    
    @NotNull
    public Collection<OCControlFlowGraph> getAllControlFlowGraphs() {
        Collection<OCControlFlowGraph> values;
        try {
            values = this.myGraphs.values();
            if (values == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getAllControlFlowGraphs"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        return values;
    }
    
    @Nullable
    public OCElementsRange getRange(@NotNull final OCCallable ocCallable, final int n) {
        try {
            if (ocCallable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callable", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getRange"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (this.myRangesMap.containsKey(ocCallable)) {
                return this.myRangesMap.get(ocCallable).get(n);
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    @NotNull
    private Attribute a(@NotNull final String s, @Nullable final PsiElement psiElement, @Nullable final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        int startOffset = 0;
        Label_0065: {
            try {
                if (psiElement != null) {
                    startOffset = psiElement.getTextRange().getStartOffset();
                    break Label_0065;
                }
            }
            catch (OCDFAUtils.DFAException ex2) {
                throw b(ex2);
            }
            startOffset = 0;
        }
        final int n = startOffset;
        int textLength = 0;
        Label_0085: {
            try {
                if (psiElement != null) {
                    textLength = psiElement.getTextLength();
                    break Label_0085;
                }
            }
            catch (OCDFAUtils.DFAException ex3) {
                throw b(ex3);
            }
            textLength = 0;
        }
        final int n2 = textLength;
        String path = null;
        Label_0114: {
            try {
                if (psiElement != null) {
                    path = psiElement.getContainingFile().getVirtualFile().getPath();
                    break Label_0114;
                }
            }
            catch (OCDFAUtils.DFAException ex4) {
                throw b(ex4);
            }
            path = "";
        }
        final String s3 = path;
        DomainType domainType = null;
        Label_0136: {
            try {
                if (s2 != null) {
                    domainType = this.mySymbolTable.getDomainType(s2);
                    break Label_0136;
                }
            }
            catch (OCDFAUtils.DFAException ex5) {
                throw b(ex5);
            }
            domainType = null;
        }
        final DomainType domainType2 = domainType;
        Attribute attribute;
        try {
            attribute = new Attribute(s, domainType2, s3, n, n2, new PrettyName(s));
            if (attribute == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex6) {
            throw b(ex6);
        }
        return attribute;
    }
    
    @NotNull
    private Attribute c(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getPsiElementAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        Attribute a;
        try {
            a = this.a(psiElement.getClass() + ":" + psiElement.getTextOffset() + ":" + psiElement.getTextLength(), psiElement, null);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getPsiElementAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        return a;
    }
    
    @NotNull
    private Attribute b(final int n) {
        Attribute a;
        try {
            a = this.a(String.valueOf(n), null, KnownDomainTypes.DOM_INTEGERS);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getIntegerAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        return a;
    }
    
    @NotNull
    private Attribute a(final int n) {
        Attribute a;
        try {
            a = this.a(String.valueOf(n), null, KnownDomainTypes.DOM_BIG_INTEGERS);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getContextAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        return a;
    }
    
    @NotNull
    private Attribute a(@NotNull final OCExpression ocExpression, @NotNull final OCType ocType) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getConstantAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetType", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getConstantAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        Attribute a;
        try {
            a = this.a(OCExpressionEvaluator.evaluate(ocExpression, ocType), (PsiElement)ocExpression);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getConstantAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex3) {
            throw b(ex3);
        }
        return a;
    }
    
    @NotNull
    private Attribute b(@NotNull final OCExpression ocExpression) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getConstantAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        Attribute a;
        try {
            a = this.a(OCExpressionEvaluator.evaluate(ocExpression), (PsiElement)ocExpression);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getConstantAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        return a;
    }
    
    @NotNull
    private Attribute b() {
        Attribute a;
        try {
            a = this.a("unknown const", null, null);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getUnknownConstAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        return a;
    }
    
    @NotNull
    private Attribute a() {
        Attribute a;
        try {
            a = this.a("unknown expression", null, null);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getUnknownExpressionAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        return a;
    }
    
    @NotNull
    private Attribute a(@NotNull final Number n, @Nullable final PsiElement psiElement) {
        try {
            if (n == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "constant", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getConstantAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        Attribute a;
        try {
            a = this.a(String.valueOf(n), psiElement, KnownDomainTypes.DOM_CONSTANTS);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getConstantAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        return a;
    }
    
    @NotNull
    private Attribute b(@NotNull final OCType ocType, @NotNull final PsiElement psiElement) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getTypeAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getTypeAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        Attribute a;
        try {
            a = this.a(ocType.resolve(psiElement.getContainingFile()).getCanonicalName(psiElement), null, KnownDomainTypes.DOM_TYPES);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getTypeAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex3) {
            throw b(ex3);
        }
        return a;
    }
    
    @NotNull
    private Attribute a(@NotNull final OCType ocType, @NotNull final PsiElement psiElement) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getUnknownSubtypeAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getUnknownSubtypeAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        Attribute a;
        try {
            a = this.a("Unknown subtype of " + ocType.resolve(psiElement.getContainingFile()).getCanonicalName(psiElement), null, KnownDomainTypes.DOM_TYPES);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getUnknownSubtypeAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex3) {
            throw b(ex3);
        }
        return a;
    }
    
    @NotNull
    private Attribute a(@NotNull final OCSymbol ocSymbol, @NotNull final String s) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getSymbolAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "domainType", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getSymbolAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        final PsiElement locateDefinition = ocSymbol.locateDefinition();
        PsiElement parent = null;
        Label_0127: {
            Label_0113: {
                try {
                    if (!(ocSymbol instanceof OCFunctionSymbol)) {
                        break Label_0113;
                    }
                    final PsiElement psiElement = locateDefinition;
                    if (psiElement != null) {
                        break Label_0113;
                    }
                    break Label_0113;
                }
                catch (OCDFAUtils.DFAException ex3) {
                    throw b(ex3);
                }
                try {
                    final PsiElement psiElement = locateDefinition;
                    if (psiElement != null) {
                        parent = locateDefinition.getParent();
                        break Label_0127;
                    }
                }
                catch (OCDFAUtils.DFAException ex4) {
                    throw b(ex4);
                }
            }
            parent = locateDefinition;
        }
        final PsiElement psiElement2 = parent;
        Attribute a;
        try {
            a = this.a(ocSymbol.getName() + ":" + ocSymbol.getComplexOffset(), psiElement2, s);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getSymbolAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex5) {
            throw b(ex5);
        }
        return a;
    }
    
    @NotNull
    private Attribute d(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getVariableAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        Attribute a;
        try {
            a = this.a(ocSymbol, KnownDomainTypes.DOM_POINTERS);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getVariableAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        return a;
    }
    
    @NotNull
    private Attribute c(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getCallableAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        Attribute a;
        try {
            a = this.a(ocSymbol, KnownDomainTypes.DOM_METHODS);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getCallableAttribute"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        return a;
    }
    
    @NotNull
    private Attribute a(final PsiElement psiElement) {
        final OCReferenceElement referenceElement = ((OCReferenceExpression)psiElement).getReferenceElement();
        Logger log = null;
        boolean b = false;
        Label_0026: {
            try {
                log = OCContextSensitiveControlFlowBuilder.LOG;
                if (referenceElement != null) {
                    b = true;
                    break Label_0026;
                }
            }
            catch (OCDFAUtils.DFAException ex) {
                throw b(ex);
            }
            b = false;
        }
        log.assertTrue(b);
        final OCSymbol resolveToSymbol = referenceElement.resolveToSymbol();
        Attribute d = null;
        Label_0053: {
            Logger log2;
            try {
                log2 = OCContextSensitiveControlFlowBuilder.LOG;
                if (resolveToSymbol != null) {
                    final boolean b2 = true;
                    break Label_0053;
                }
            }
            catch (OCDFAUtils.DFAException ex2) {
                throw b(ex2);
            }
            final boolean b2 = false;
            try {
                log2.assertTrue(b2);
                d = this.d(resolveToSymbol);
                if (d == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getVariableAttribute"));
                }
            }
            catch (OCDFAUtils.DFAException ex3) {
                throw b(ex3);
            }
        }
        return d;
    }
    
    @Contract("null -> false")
    @Override
    protected boolean isSymbolInScope(@Nullable final OCSymbol ocSymbol) {
        Label_0031: {
            try {
                if (OCDFAUtils.isLocalVariable(ocSymbol, this.myGraph)) {
                    break Label_0031;
                }
                final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder = this;
                final Set<OCSymbol> set = ocContextSensitiveControlFlowBuilder.myPrivateFields;
                final OCSymbol ocSymbol2 = ocSymbol;
                final boolean b = set.contains(ocSymbol2);
                if (b) {
                    break Label_0031;
                }
                return false;
            }
            catch (OCDFAUtils.DFAException ex) {
                throw b(ex);
            }
            try {
                final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder = this;
                final Set<OCSymbol> set = ocContextSensitiveControlFlowBuilder.myPrivateFields;
                final OCSymbol ocSymbol2 = ocSymbol;
                final boolean b = set.contains(ocSymbol2);
                if (b) {
                    return true;
                }
            }
            catch (OCDFAUtils.DFAException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Contract("null -> false")
    protected boolean isSymbolInScope(@Nullable final OCExpression ocExpression) {
        try {
            if (!(ocExpression instanceof OCReferenceExpression) || ((OCReferenceExpression)ocExpression).getSelfSuperToken() != null) {
                return false;
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        final OCReferenceElement referenceElement = ((OCReferenceExpression)ocExpression).getReferenceElement();
        try {
            if (referenceElement != null) {
                final OCSymbol resolveToSymbol = referenceElement.resolveToSymbol();
                return this.isSymbolInScope(resolveToSymbol);
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        final OCSymbol resolveToSymbol = null;
        return this.isSymbolInScope(resolveToSymbol);
    }
    
    public void processCallable(@NotNull final OCCallable p0, @NotNull final Map p1, final ThreadCallback p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "callable"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processCallable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "options"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processCallable"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    87: athrow         
        //    88: aload_1        
        //    89: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    94: astore          4
        //    96: aload_0        
        //    97: aload_3        
        //    98: putfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myThreadCallback:Lcom/jetbrains/sourceglider/ui/ThreadCallback;
        //   101: aload           4
        //   103: ifnull          326
        //   106: aload_1        
        //   107: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   112: ifnull          326
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   121: athrow         
        //   122: aload_0        
        //   123: new             Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //   126: dup            
        //   127: aload_1        
        //   128: aconst_null    
        //   129: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.<init>:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;)V
        //   132: invokevirtual   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.init:(Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;)V
        //   135: aload_0        
        //   136: aload_1        
        //   137: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.hasGotos:(Lcom/jetbrains/cidr/lang/psi/OCCallable;)Z
        //   140: ifne            179
        //   143: goto            150
        //   146: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   149: athrow         
        //   150: aload_2        
        //   151: ldc             "CONTEXT_SENSITIVITY"
        //   153: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   158: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   161: if_acmpeq       179
        //   164: goto            171
        //   167: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   170: athrow         
        //   171: iconst_1       
        //   172: goto            180
        //   175: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   178: athrow         
        //   179: iconst_0       
        //   180: putfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myContextSensitivity:Z
        //   183: aload_0        
        //   184: aload_2        
        //   185: ldc             "GLOBAL_ANALYSIS"
        //   187: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   192: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   195: if_acmpne       206
        //   198: iconst_1       
        //   199: goto            207
        //   202: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   205: athrow         
        //   206: iconst_0       
        //   207: putfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myGlobalAnalysis:Z
        //   210: aload_0        
        //   211: aload_1        
        //   212: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   217: putfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myFile:Lcom/intellij/psi/PsiFile;
        //   220: aload_1        
        //   221: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.getNotUsedWrites:(Lcom/jetbrains/cidr/lang/psi/OCCallable;)Lcom/intellij/openapi/util/Pair;
        //   224: astore          5
        //   226: aload_0        
        //   227: aload           5
        //   229: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //   232: checkcast       Ljava/util/Set;
        //   235: putfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myNotUsedWrites:Ljava/util/Set;
        //   238: aload_0        
        //   239: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myGraphs:Ljava/util/Map;
        //   242: aload_1        
        //   243: aload           5
        //   245: invokevirtual   com/intellij/openapi/util/Pair.getSecond:()Ljava/lang/Object;
        //   248: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   253: pop            
        //   254: aload_0        
        //   255: new             Lcom/intellij/util/containers/MultiMap;
        //   258: dup            
        //   259: invokespecial   com/intellij/util/containers/MultiMap.<init>:()V
        //   262: putfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myVariableWrites:Lcom/intellij/util/containers/MultiMap;
        //   265: aload_0        
        //   266: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myStatementContexts:Lcom/intellij/util/containers/MultiMap;
        //   269: invokevirtual   com/intellij/util/containers/MultiMap.clear:()V
        //   272: aload_0        
        //   273: aload_0        
        //   274: aload           4
        //   276: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.c:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   279: putfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myCallableAttribute:Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   282: aload_0        
        //   283: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myGlobalAnalysis:Z
        //   286: ifeq            313
        //   289: aload_0        
        //   290: aload_1        
        //   291: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/psi/OCCallable;)V
        //   294: aload_0        
        //   295: aload           4
        //   297: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   300: aload_0        
        //   301: aload           4
        //   303: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.e:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   306: goto            313
        //   309: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   312: athrow         
        //   313: aload_0        
        //   314: aload_1        
        //   315: invokevirtual   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.processFirstCodeFragment:(Lcom/intellij/psi/PsiElement;)V
        //   318: aload_0        
        //   319: invokevirtual   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.addContextInfos:()V
        //   322: aload_0        
        //   323: invokevirtual   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.processDeadEnds:()V
        //   326: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                  
        //  -----  -----  -----  -----  ----------------------------------------------------------------------
        //  0      40     40     44     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  44     84     84     88     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  96     115    118    122    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  106    143    146    150    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  122    164    167    171    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  150    175    175    179    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  180    202    202    206    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  226    306    309    313    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0122:
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
    
    private void b(final OCCallable ocCallable) {
        final OCType a = this.a(ocCallable.getReturnType().resolve(this.myFile));
        this.myConsumer.addTuple(this.myMethodReturnType, this.myCallableAttribute, this.b(a, (PsiElement)ocCallable));
        this.b(a);
    }
    
    private void a(final OCSymbol ocSymbol) {
        final boolean privateCallable = OCDFAUtils.isPrivateCallable(ocSymbol);
        try {
            if (privateCallable) {
                this.myConsumer.addTuple(this.myPrivateMethod, this.myCallableAttribute);
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        List<OCDeclaratorSymbol> list = null;
        if (ocSymbol instanceof OCFunctionSymbol) {
            list = ((OCFunctionSymbol)ocSymbol).getParameterSymbols();
        }
        else if (ocSymbol instanceof OCMethodSymbol) {
            list = ((OCMethodSymbol)ocSymbol).getParameterSymbols();
        }
        if (list != null) {
            for (int i = 0; i < list.size(); ++i) {
                final OCDeclaratorSymbol ocDeclaratorSymbol = list.get(i);
                Label_0168: {
                    try {
                        if (ocDeclaratorSymbol == null) {
                            continue;
                        }
                        final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder = this;
                        final TuplesConsumer tuplesConsumer = ocContextSensitiveControlFlowBuilder.myConsumer;
                        final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder2 = this;
                        final RelationSignature relationSignature = ocContextSensitiveControlFlowBuilder2.myParameter;
                        final int n = 3;
                        final Attribute[] array = new Attribute[n];
                        final int n2 = 0;
                        final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder3 = this;
                        final Attribute attribute = ocContextSensitiveControlFlowBuilder3.myCallableAttribute;
                        array[n2] = attribute;
                        final int n3 = 1;
                        final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder4 = this;
                        final int n4 = i;
                        final int n5 = 1;
                        final int n6 = n4 + n5;
                        final Attribute attribute2 = ocContextSensitiveControlFlowBuilder4.b(n6);
                        array[n3] = attribute2;
                        final int n7 = 2;
                        final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder5 = this;
                        final OCDeclaratorSymbol ocDeclaratorSymbol2 = ocDeclaratorSymbol;
                        final Attribute attribute3 = ocContextSensitiveControlFlowBuilder5.d(ocDeclaratorSymbol2);
                        array[n7] = attribute3;
                        tuplesConsumer.addTuple(relationSignature, array);
                        final boolean b = privateCallable;
                        if (b) {
                            break Label_0168;
                        }
                        continue;
                    }
                    catch (OCDFAUtils.DFAException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder = this;
                        final TuplesConsumer tuplesConsumer = ocContextSensitiveControlFlowBuilder.myConsumer;
                        final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder2 = this;
                        final RelationSignature relationSignature = ocContextSensitiveControlFlowBuilder2.myParameter;
                        final int n = 3;
                        final Attribute[] array = new Attribute[n];
                        final int n2 = 0;
                        final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder3 = this;
                        final Attribute attribute = ocContextSensitiveControlFlowBuilder3.myCallableAttribute;
                        array[n2] = attribute;
                        final int n3 = 1;
                        final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder4 = this;
                        final int n4 = i;
                        final int n5 = 1;
                        final int n6 = n4 + n5;
                        final Attribute attribute2 = ocContextSensitiveControlFlowBuilder4.b(n6);
                        array[n3] = attribute2;
                        final int n7 = 2;
                        final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder5 = this;
                        final OCDeclaratorSymbol ocDeclaratorSymbol2 = ocDeclaratorSymbol;
                        final Attribute attribute3 = ocContextSensitiveControlFlowBuilder5.d(ocDeclaratorSymbol2);
                        array[n7] = attribute3;
                        tuplesConsumer.addTuple(relationSignature, array);
                        final boolean b = privateCallable;
                        if (b) {
                            this.myPrivateParameters.add(ocDeclaratorSymbol);
                        }
                    }
                    catch (OCDFAUtils.DFAException ex3) {
                        throw b(ex3);
                    }
                }
            }
        }
    }
    
    protected void processDeadEnds() {
        new OCUnreachableCodeFinder(this.myGraph) {
            @Override
            protected void processDeadEnd(@NotNull final OCNode ocNode) {
                try {
                    if (ocNode == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$1", "processDeadEnd"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                final Iterator<Integer> iterator = OCContextSensitiveControlFlowBuilder.this.myStatementContexts.get((Object)ocNode).iterator();
                while (iterator.hasNext()) {
                    OCContextSensitiveControlFlowBuilder.this.myConsumer.addTuple(OCContextSensitiveControlFlowBuilder.this.myExitStatement, OCContextSensitiveControlFlowBuilder.this.a(iterator.next()), OCContextSensitiveControlFlowBuilder.this.myCallableAttribute);
                }
            }
            
            private static IllegalArgumentException c(final IllegalArgumentException ex) {
                return ex;
            }
        }.process();
    }
    
    private void e(final OCSymbol ocSymbol) {
        Object o = null;
        if (ocSymbol instanceof OCMethodSymbol) {
            o = ((OCSymbolWithParent<T, Object>)ocSymbol).getParent();
        }
        else if (ocSymbol instanceof OCFunctionSymbol) {
            o = ((OCFunctionSymbol)ocSymbol).getResolvedOwner();
        }
        if (o instanceof OCImplementationSymbol) {
            final OCType resolvedType = ((OCSymbol)o).getResolvedType();
            try {
                if (resolvedType instanceof OCObjectType) {
                    ((OCObjectType)resolvedType).processMembers(OCInstanceVariableSymbol.class, (com.intellij.util.Processor<? super OCInstanceVariableSymbol>)(ocInstanceVariableSymbol -> {
                        try {
                            if (ocInstanceVariableSymbol.getVisibility().ordinal() >= OCVisibility.PROTECTED.ordinal()) {
                                this.myConsumer.addTuple(this.myPrivateField, this.d(ocInstanceVariableSymbol));
                                this.myPrivateFields.add(ocInstanceVariableSymbol);
                                this.a(ocInstanceVariableSymbol.getResolvedType(), ocInstanceVariableSymbol, (PsiElement)this.myFile);
                            }
                        }
                        catch (OCDFAUtils.DFAException ex) {
                            throw b(ex);
                        }
                        return true;
                    }));
                }
            }
            catch (OCDFAUtils.DFAException ex) {
                throw b(ex);
            }
        }
        else {
            try {
                if (o instanceof OCStructSymbol) {
                    ((OCStructSymbol)o).processFields((Processor<OCDeclaratorSymbol>)(ocDeclaratorSymbol -> {
                        Label_0024: {
                            try {
                                if (ocDeclaratorSymbol.getVisibility() != OCVisibility.PRIVATE) {
                                    return true;
                                }
                                final OCDeclaratorSymbol ocDeclaratorSymbol2 = ocDeclaratorSymbol;
                                final boolean b = ocDeclaratorSymbol2.isFriendOrStatic();
                                if (!b) {
                                    break Label_0024;
                                }
                                return true;
                            }
                            catch (OCDFAUtils.DFAException ex) {
                                throw b(ex);
                            }
                            try {
                                final OCDeclaratorSymbol ocDeclaratorSymbol2 = ocDeclaratorSymbol;
                                final boolean b = ocDeclaratorSymbol2.isFriendOrStatic();
                                if (!b) {
                                    this.myConsumer.addTuple(this.myPrivateField, this.d(ocDeclaratorSymbol));
                                    this.myPrivateFields.add(ocDeclaratorSymbol);
                                    this.a(ocDeclaratorSymbol.getResolvedType(), ocDeclaratorSymbol, (PsiElement)this.myFile);
                                }
                            }
                            catch (OCDFAUtils.DFAException ex2) {
                                throw b(ex2);
                            }
                        }
                        return true;
                    }));
                }
            }
            catch (OCDFAUtils.DFAException ex2) {
                throw b(ex2);
            }
        }
    }
    
    private void a(@NotNull final OCType ocType, @NotNull final OCSymbol ocSymbol, @NotNull final PsiElement psiElement) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "processDeclarator"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "processDeclarator"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "processDeclarator"));
            }
        }
        catch (OCDFAUtils.DFAException ex3) {
            throw b(ex3);
        }
        final Attribute b = this.b(ocType, psiElement);
        this.b(ocType);
        this.myConsumer.addTuple(this.myVariableType, this.d(ocSymbol), b);
        this.myConsumer.addTuple(this.myExpressionType, this.c(psiElement), b);
        this.a(ocType, ocSymbol);
    }
    
    private void a(@NotNull OCType refType, @NotNull final OCSymbol ocSymbol) {
        try {
            if (refType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "processVariableType"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "processVariableType"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        final Attribute d = this.d(ocSymbol);
        if (refType instanceof OCCppReferenceType) {
            refType = ((OCCppReferenceType)refType).getRefType();
        }
        try {
            if (refType instanceof OCRealType) {
                this.myConsumer.addTuple(this.myRealTypeVariable, d);
                return;
            }
        }
        catch (OCDFAUtils.DFAException ex3) {
            throw b(ex3);
        }
        Label_0193: {
            Label_0165: {
                try {
                    if (!(refType instanceof OCPointerType)) {
                        break Label_0193;
                    }
                    final OCType ocType = refType;
                    final boolean b = ocType instanceof OCArrayType;
                    if (!b) {
                        break Label_0165;
                    }
                    break Label_0193;
                }
                catch (OCDFAUtils.DFAException ex4) {
                    throw b(ex4);
                }
                try {
                    final OCType ocType = refType;
                    final boolean b = ocType instanceof OCArrayType;
                    if (!b) {
                        this.myConsumer.addTuple(this.myPointerTypeVariable, d);
                        return;
                    }
                }
                catch (OCDFAUtils.DFAException ex5) {
                    throw b(ex5);
                }
            }
            try {
                if (refType instanceof OCMagicType) {
                    this.myConsumer.addTuple(this.myMagicTypeVariable, d);
                    return;
                }
            }
            catch (OCDFAUtils.DFAException ex6) {
                throw b(ex6);
            }
        }
        Label_0255: {
            try {
                if (!(refType instanceof OCStructType)) {
                    return;
                }
                final OCType ocType2 = refType;
                final OCStructType ocStructType = (OCStructType)ocType2;
                final OCSymbolKind ocSymbolKind = ocStructType.getKind();
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.STRUCT;
                if (ocSymbolKind == ocSymbolKind2) {
                    break Label_0255;
                }
                return;
            }
            catch (OCDFAUtils.DFAException ex7) {
                throw b(ex7);
            }
            try {
                final OCType ocType2 = refType;
                final OCStructType ocStructType = (OCStructType)ocType2;
                final OCSymbolKind ocSymbolKind = ocStructType.getKind();
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.STRUCT;
                if (ocSymbolKind == ocSymbolKind2) {
                    this.myConsumer.addTuple(this.myStructTypeVariable, d);
                }
            }
            catch (OCDFAUtils.DFAException ex8) {
                throw b(ex8);
            }
        }
    }
    
    @Override
    public void visitDeclarator(final OCDeclarator ocDeclarator) {
        super.visitDeclarator(ocDeclarator);
        final OCSymbol symbol = ocDeclarator.getSymbol();
        final PsiElement nameIdentifier = ocDeclarator.getNameIdentifier();
        Label_0038: {
            try {
                if (!this.isSymbolInScope(symbol)) {
                    return;
                }
                final PsiElement psiElement = nameIdentifier;
                if (psiElement != null) {
                    break Label_0038;
                }
                return;
            }
            catch (OCDFAUtils.DFAException ex) {
                throw b(ex);
            }
            try {
                final PsiElement psiElement = nameIdentifier;
                if (psiElement != null) {
                    this.a(this.a(ocDeclarator.getResolvedType()), symbol, nameIdentifier);
                }
            }
            catch (OCDFAUtils.DFAException ex2) {
                throw b(ex2);
            }
        }
    }
    
    @Override
    public void visitMethodSelectorPart(final OCMethodSelectorPart ocMethodSelectorPart) {
        super.visitMethodSelectorPart(ocMethodSelectorPart);
        final OCDeclaratorSymbol ocDeclaratorSymbol = ocMethodSelectorPart.getSymbol();
        final PsiElement nameIdentifier = ocMethodSelectorPart.getNameIdentifier();
        Label_0041: {
            try {
                if (!this.isSymbolInScope(ocDeclaratorSymbol)) {
                    return;
                }
                final PsiElement psiElement = nameIdentifier;
                if (psiElement != null) {
                    break Label_0041;
                }
                return;
            }
            catch (OCDFAUtils.DFAException ex) {
                throw b(ex);
            }
            try {
                final PsiElement psiElement = nameIdentifier;
                if (psiElement != null) {
                    this.a(this.a(ocMethodSelectorPart.getType().resolve(this.myFile)), ocDeclaratorSymbol, nameIdentifier);
                }
            }
            catch (OCDFAUtils.DFAException ex2) {
                throw b(ex2);
            }
        }
    }
    
    @Override
    protected void processNonInitializedDeclarator(@NotNull final OCSymbol p0, @Nullable final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processNonInitializedDeclarator"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_1        
        //    46: aload_2        
        //    47: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.processNonInitializedDeclarator:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)V
        //    50: aload_0        
        //    51: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myPrivateParameters:Ljava/util/Set;
        //    54: aload_1        
        //    55: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //    60: ifeq            68
        //    63: return         
        //    64: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    67: athrow         
        //    68: aload_0        
        //    69: aload_1        
        //    70: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.d:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //    73: astore_3       
        //    74: aload_0        
        //    75: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myVariableWrites:Lcom/intellij/util/containers/MultiMap;
        //    78: aload_0        
        //    79: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //    82: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getLastAddedNode:()Lcom/jetbrains/cidr/lang/dfa/OCNode;
        //    85: aload_3        
        //    86: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //    89: aload_2        
        //    90: ifnull          256
        //    93: aload_0        
        //    94: lconst_0       
        //    95: invokestatic    com/jetbrains/cidr/lang/util/OCNumber.valueOf:(J)Lcom/jetbrains/cidr/lang/util/OCNumber;
        //    98: aconst_null    
        //    99: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Ljava/lang/Number;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   102: astore          4
        //   104: aload_0        
        //   105: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myConsumer:Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$TuplesConsumer;
        //   108: aload_0        
        //   109: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myConstantType:Lcom/jetbrains/sourceglider/relations/RelationSignature;
        //   112: iconst_3       
        //   113: anewarray       Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   116: dup            
        //   117: iconst_0       
        //   118: aload           4
        //   120: aastore        
        //   121: dup            
        //   122: iconst_1       
        //   123: aload_0        
        //   124: aload           4
        //   126: invokevirtual   com/jetbrains/sourceglider/atttributes/Attribute.getKey:()Ljava/lang/String;
        //   129: aload_2        
        //   130: getstatic       com/jetbrains/sourceglider/symtable/KnownDomainTypes.DOM_OBJECTS:Ljava/lang/String;
        //   133: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   136: aastore        
        //   137: dup            
        //   138: iconst_2       
        //   139: aload_0        
        //   140: aload_1        
        //   141: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   146: aload_2        
        //   147: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   150: aastore        
        //   151: invokeinterface com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$TuplesConsumer.addTuple:(Lcom/jetbrains/sourceglider/relations/RelationSignature;[Lcom/jetbrains/sourceglider/atttributes/Attribute;)V
        //   156: aload_0        
        //   157: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myFile:Lcom/intellij/psi/PsiFile;
        //   160: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //   163: ifeq            232
        //   166: aload_1        
        //   167: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   172: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.LOCAL_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   175: if_acmpne       232
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   184: athrow         
        //   185: aload_1        
        //   186: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   191: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   194: ifeq            232
        //   197: goto            204
        //   200: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   203: athrow         
        //   204: aload_0        
        //   205: aload_0        
        //   206: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myAssignmentToConstant:Lcom/jetbrains/sourceglider/relations/RelationSignature;
        //   209: iconst_2       
        //   210: anewarray       Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   213: dup            
        //   214: iconst_0       
        //   215: aload_3        
        //   216: aastore        
        //   217: dup            
        //   218: iconst_1       
        //   219: aload           4
        //   221: aastore        
        //   222: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Lcom/jetbrains/sourceglider/relations/RelationSignature;[Lcom/jetbrains/sourceglider/atttributes/Attribute;)V
        //   225: goto            256
        //   228: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   231: athrow         
        //   232: aload_0        
        //   233: aload_0        
        //   234: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myNonInitializedDeclarator:Lcom/jetbrains/sourceglider/relations/RelationSignature;
        //   237: iconst_2       
        //   238: anewarray       Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   241: dup            
        //   242: iconst_0       
        //   243: aload_3        
        //   244: aastore        
        //   245: dup            
        //   246: iconst_1       
        //   247: aload_0        
        //   248: aload_2        
        //   249: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.c:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   252: aastore        
        //   253: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Lcom/jetbrains/sourceglider/relations/RelationSignature;[Lcom/jetbrains/sourceglider/atttributes/Attribute;)V
        //   256: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                  
        //  -----  -----  -----  -----  ----------------------------------------------------------------------
        //  0      40     40     44     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  44     64     64     68     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  104    178    181    185    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  166    197    200    204    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  185    228    228    232    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0185:
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
    protected void addStartNode(@NotNull final OCNode ocNode) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addStartNode"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        super.addStartNode(ocNode);
        this.myStatementContexts.putValue((Object)ocNode, (Object)this.myCurContext);
        this.myConsumer.addTuple(this.myStartNode, this.myCallableAttribute, this.a(this.myCurContext));
        ++this.myCurContext;
    }
    
    private void a(@NotNull final RelationSignature relationSignature, @NotNull final Attribute... array) {
        try {
            if (relationSignature == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "relation", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addStatement"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attrs", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addStatement"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        try {
            if (this.myThreadCallback != null) {
                this.myThreadCallback.checkCancelled();
            }
        }
        catch (OCDFAUtils.DFAException ex3) {
            throw b(ex3);
        }
        final OCNode lastAddedNode = this.myGraph.getLastAddedNode();
        try {
            if (this.myFakeNodes.contains(lastAddedNode)) {
                this.myFakeNodeAttributes.putValue((Object)lastAddedNode, (Object)Pair.create((Object)relationSignature, (Object)array));
                return;
            }
        }
        catch (OCDFAUtils.DFAException ex4) {
            throw b(ex4);
        }
        for (final Integer n : this.myStatementContexts.get((Object)lastAddedNode)) {
            try {
                if (this.myThreadCallback != null) {
                    this.myThreadCallback.checkCancelled();
                }
            }
            catch (OCDFAUtils.DFAException ex5) {
                throw b(ex5);
            }
            this.myConsumer.addTuple(relationSignature, (Attribute[])ArrayUtil.mergeArrays((Object[])new Attribute[] { this.a(n) }, (Object[])array));
        }
    }
    
    private boolean a(@Nullable final List<PsiElement> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          36
        //     4: aload_0        
        //     5: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myContextSensitivity:Z
        //     8: ifeq            44
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    17: athrow         
        //    18: aload_1        
        //    19: aload_0        
        //    20: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myNotUsedWrites:Ljava/util/Set;
        //    23: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.areValuesUsed:(Ljava/util/List;Ljava/util/Set;)Z
        //    26: ifeq            44
        //    29: goto            36
        //    32: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    35: athrow         
        //    36: iconst_1       
        //    37: goto            45
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    43: athrow         
        //    44: iconst_0       
        //    45: ireturn        
        //    Signature:
        //  (Ljava/util/List<Lcom/intellij/psi/PsiElement;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                  
        //  -----  -----  -----  -----  ----------------------------------------------------------------------
        //  0      11     14     18     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  4      29     32     36     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  18     40     40     44     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    
    @NotNull
    @Override
    protected MyNodeState addBranch(@NotNull final OCNode ocNode, @NotNull final OCNode ocNode2, @Nullable final List<PsiElement> list, @Nullable final NodeState nodeState) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fromNode", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addBranch"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (ocNode2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toNode", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addBranch"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        MyNodeState myNodeState = null;
        Label_0147: {
            OCConditionsList.Conditions conditions = null;
            Label_0138: {
                try {
                    super.addBranch(ocNode, ocNode2, list, nodeState);
                    conditions = new OCConditionsList.Conditions(null, null, this.myUnconditionalJump, new Attribute[0]);
                    if (!this.a(list)) {
                        break Label_0138;
                    }
                    final NodeState nodeState2 = nodeState;
                    if (nodeState2 == null) {
                        break Label_0138;
                    }
                    break Label_0138;
                }
                catch (OCDFAUtils.DFAException ex3) {
                    throw b(ex3);
                }
                try {
                    final NodeState nodeState2 = nodeState;
                    if (nodeState2 == null) {
                        final boolean b = true;
                        break Label_0147;
                    }
                }
                catch (OCDFAUtils.DFAException ex4) {
                    throw b(ex4);
                }
            }
            final boolean b = false;
            try {
                this.a(conditions, ocNode, ocNode2, b, false, (MyNodeState)nodeState);
                myNodeState = new MyNodeState(ocNode2);
                if (myNodeState == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addBranch"));
                }
            }
            catch (OCDFAUtils.DFAException ex5) {
                throw b(ex5);
            }
        }
        return myNodeState;
    }
    
    @Nullable
    @Override
    protected NodeState addUnstructuralBranch(@NotNull final OCNode ocNode, @NotNull final OCNode ocNode2) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fromNode", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addUnstructuralBranch"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (ocNode2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toNode", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addUnstructuralBranch"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        super.addUnstructuralBranch(ocNode, ocNode2);
        this.a(new OCConditionsList.Conditions(null, null, this.myUnconditionalJump, new Attribute[0]), ocNode, ocNode2, false, true, null);
        return new MyNodeState(ocNode2);
    }
    
    @Override
    protected OCNode getLoopBodyEndNode() {
        final OCNode loopBodyEndNode = super.getLoopBodyEndNode();
        try {
            if (this.myStatementContexts.containsKey((Object)loopBodyEndNode)) {
                return loopBodyEndNode;
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        for (final OCNode ocNode : this.myContinueNodes) {
            try {
                if (this.myStatementContexts.containsKey((Object)ocNode)) {
                    return ocNode;
                }
                continue;
            }
            catch (OCDFAUtils.DFAException ex2) {
                throw b(ex2);
            }
        }
        return loopBodyEndNode;
    }
    
    private void d() {
        if (this.myGraph.isSplitNodesAllowed()) {
            this.addBranch(this.myGraph.getLastAddedNode(), this.myGraph.addNode());
        }
    }
    
    @NotNull
    @Override
    protected MyNodeState addConditionalBranch(@NotNull final OCElement ocElement, final boolean b, @NotNull final OCNode ocNode, @NotNull final OCNode ocNode2, @Nullable final List<PsiElement> list, @Nullable final NodeState nodeState) {
        try {
            if (ocElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "condition", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addConditionalBranch"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fromNode", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addConditionalBranch"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        try {
            if (ocNode2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toNode", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addConditionalBranch"));
            }
        }
        catch (OCDFAUtils.DFAException ex3) {
            throw b(ex3);
        }
        boolean b2 = false;
        Label_0175: {
            Label_0166: {
                try {
                    super.addBranch(ocNode, ocNode2, list, nodeState);
                    if (!this.a(list)) {
                        break Label_0166;
                    }
                    final NodeState nodeState2 = nodeState;
                    if (nodeState2 == null) {
                        break Label_0166;
                    }
                    break Label_0166;
                }
                catch (OCDFAUtils.DFAException ex4) {
                    throw b(ex4);
                }
                try {
                    final NodeState nodeState2 = nodeState;
                    if (nodeState2 == null) {
                        b2 = true;
                        break Label_0175;
                    }
                }
                catch (OCDFAUtils.DFAException ex5) {
                    throw b(ex5);
                }
            }
            b2 = false;
        }
        final boolean b3 = b2;
        OCConditionsList list2 = null;
        Label_0220: {
            try {
                if (b) {
                    list2 = this.myBranchConditions.get(ocElement).getTrueConditions();
                    break Label_0220;
                }
            }
            catch (OCDFAUtils.DFAException ex6) {
                throw b(ex6);
            }
            list2 = this.myBranchConditions.get(ocElement).getFalseConditions();
        }
        final Iterator<OCConditionsList.Conditions> iterator = list2.getConditions().iterator();
        while (iterator.hasNext()) {
            this.a(iterator.next(), ocNode, ocNode2, b3, false, (MyNodeState)nodeState);
        }
        MyNodeState myNodeState;
        try {
            myNodeState = new MyNodeState(ocNode2);
            if (myNodeState == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addConditionalBranch"));
            }
        }
        catch (OCDFAUtils.DFAException ex7) {
            throw b(ex7);
        }
        return myNodeState;
    }
    
    private void b(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "processType"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (this.myThreadCallback != null) {
                this.myThreadCallback.checkCancelled();
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        final Attribute b = this.b(ocType, (PsiElement)this.myFile);
        try {
            this.myConsumer.addTuple(this.myConstantType, this.b(), this.b(), b);
            this.myConsumer.addTuple(this.myExpressionType, this.a(), b);
            if (OCDFAUtils.processTypeHierarchy(ocType, (PsiElement)this.myFile, this.myProcessedTypes, (Processor<Pair<OCType, OCType>>)(pair -> {
                final OCType ocType = (OCType)pair.getSecond();
                try {
                    if (ocType != null) {
                        this.myConsumer.addTuple(this.mySubType, this.b((OCType)pair.getFirst(), (PsiElement)this.myFile), this.b(ocType, (PsiElement)this.myFile));
                    }
                }
                catch (OCDFAUtils.DFAException ex) {
                    throw b(ex);
                }
                return true;
            }))) {
                this.myConsumer.addTuple(this.myUnknownSubType, this.a(ocType, (PsiElement)this.myFile), b);
                return;
            }
        }
        catch (OCDFAUtils.DFAException ex3) {
            throw b(ex3);
        }
        this.myConsumer.addTuple(this.myPODType, b);
    }
    
    @NotNull
    private OCType a(@NotNull OCType refType) {
        try {
            if (refType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "simplifyType"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        while (refType instanceof OCCppReferenceType) {
            refType = ((OCCppReferenceType)refType).getRefType();
        }
        OCType cloneWithoutConstModifier = null;
        Label_0141: {
            if (refType.isPointerToObject()) {
                final OCObjectType ocObjectType = (OCObjectType)refType.getTerminalType();
                OCType ocType2 = null;
                Label_0140: {
                    Label_0103: {
                        try {
                            if (ocObjectType.getAllProtocols().isEmpty()) {
                                break Label_0141;
                            }
                            final OCType ocType = refType;
                            final boolean b = ocType.isPointerToID();
                            if (b) {
                                break Label_0103;
                            }
                            break Label_0103;
                        }
                        catch (OCDFAUtils.DFAException ex2) {
                            throw b(ex2);
                        }
                        try {
                            final OCType ocType = refType;
                            final boolean b = ocType.isPointerToID();
                            if (b) {
                                ocType2 = OCIdType.pointerToID(this.myFile.getProject());
                                break Label_0140;
                            }
                        }
                        catch (OCDFAUtils.DFAException ex3) {
                            throw b(ex3);
                        }
                    }
                    ocType2 = OCPointerType.to(new OCObjectType(ocObjectType.getInterface(), ocObjectType.getSuperType()));
                }
                refType = ocType2;
            }
            try {
                cloneWithoutConstModifier = refType.cloneWithoutConstModifier(this.myFile.getProject());
                if (cloneWithoutConstModifier == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "simplifyType"));
                }
            }
            catch (OCDFAUtils.DFAException ex4) {
                throw b(ex4);
            }
        }
        return cloneWithoutConstModifier;
    }
    
    private void a(@NotNull final OCConditionsList.Conditions conditions, @NotNull final OCNode ocNode, @NotNull final OCNode ocNode2, final boolean b, final boolean b2, @Nullable final MyNodeState myNodeState) {
        try {
            if (conditions == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "conditions", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addJump"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fromNode", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addJump"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        try {
            if (ocNode2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toNode", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addJump"));
            }
        }
        catch (OCDFAUtils.DFAException ex3) {
            throw b(ex3);
        }
        try {
            if (this.myThreadCallback != null) {
                this.myThreadCallback.checkCancelled();
            }
        }
        catch (OCDFAUtils.DFAException ex4) {
            throw b(ex4);
        }
        final List list = (List)this.myStatementContexts.get((Object)ocNode);
        List<Integer> singletonList = (List<Integer>)this.myStatementContexts.get((Object)ocNode2);
        int access$600 = 0;
        Label_0200: {
            try {
                if (myNodeState != null) {
                    access$600 = myNodeState.myContextsCnt;
                    break Label_0200;
                }
            }
            catch (OCDFAUtils.DFAException ex5) {
                throw b(ex5);
            }
            access$600 = 0;
        }
        final int n = access$600;
        Label_0278: {
            Label_0224: {
                try {
                    if (b) {
                        break Label_0278;
                    }
                    final List list2 = list;
                    final boolean b3 = list2.isEmpty();
                    if (!b3) {
                        break Label_0224;
                    }
                    break Label_0278;
                }
                catch (OCDFAUtils.DFAException ex6) {
                    throw b(ex6);
                }
                try {
                    final List list2 = list;
                    final boolean b3 = list2.isEmpty();
                    if (b3) {
                        break Label_0278;
                    }
                    if (!singletonList.isEmpty()) {
                        break Label_0278;
                    }
                }
                catch (OCDFAUtils.DFAException ex7) {
                    throw b(ex7);
                }
            }
            this.myStatementContexts.putValue((Object)ocNode2, (Object)this.myCurContext);
            singletonList = Collections.singletonList(this.myCurContext);
            ++this.myCurContext;
        }
        int n2 = n;
        while (true) {
            Label_0308: {
                try {
                    if (n2 >= list.size()) {
                        break;
                    }
                    final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder = this;
                    final ThreadCallback threadCallback = ocContextSensitiveControlFlowBuilder.myThreadCallback;
                    if (threadCallback != null) {
                        break Label_0308;
                    }
                    break Label_0308;
                }
                catch (OCDFAUtils.DFAException ex8) {
                    throw b(ex8);
                }
                try {
                    final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder = this;
                    final ThreadCallback threadCallback = ocContextSensitiveControlFlowBuilder.myThreadCallback;
                    if (threadCallback != null) {
                        this.myThreadCallback.checkCancelled();
                    }
                }
                catch (OCDFAUtils.DFAException ex9) {
                    throw b(ex9);
                }
            }
            final int intValue = list.get(n2);
            if (b2) {
                final Iterator<Integer> iterator = singletonList.iterator();
                while (iterator.hasNext()) {
                    this.a(conditions, ocNode, ocNode2, intValue, iterator.next());
                }
            }
            else {
                int intValue2 = 0;
                Label_0585: {
                    Label_0484: {
                        try {
                            if (!b || ocNode == ocNode2) {
                                break Label_0484;
                            }
                        }
                        catch (OCDFAUtils.DFAException ex10) {
                            throw b(ex10);
                        }
                        intValue2 = this.myCurContext++;
                        try {
                            this.myStatementContexts.putValue((Object)ocNode2, (Object)intValue2);
                            if (this.myCurContext >= 1000000) {
                                throw new OCDFAUtils.DFAException("Too many DFA contexts used: " + this.myCurContext);
                            }
                            break Label_0585;
                        }
                        catch (OCDFAUtils.DFAException ex11) {
                            throw b(ex11);
                        }
                    }
                    final int n3 = (n2 - n) * singletonList.size() / (list.size() - n);
                    Label_0534: {
                        try {
                            if (n3 < 0) {
                                break Label_0534;
                            }
                            final int n4 = n3;
                            final List<Integer> list3 = singletonList;
                            final int n5 = list3.size();
                            if (n4 >= n5) {
                                break Label_0534;
                            }
                            break Label_0534;
                        }
                        catch (OCDFAUtils.DFAException ex12) {
                            throw b(ex12);
                        }
                        try {
                            final int n4 = n3;
                            final List<Integer> list3 = singletonList;
                            final int n5 = list3.size();
                            if (n4 >= n5) {
                                throw new OCDFAUtils.DFAException("Too many DFA contexts used: " + this.myCurContext);
                            }
                        }
                        catch (OCDFAUtils.DFAException ex13) {
                            throw b(ex13);
                        }
                    }
                    intValue2 = singletonList.get(n3);
                }
                this.a(conditions, ocNode, ocNode2, intValue, intValue2);
            }
            ++n2;
        }
    }
    
    private void a(@NotNull final OCConditionsList.Conditions conditions, @NotNull final OCNode ocNode, @NotNull final OCNode ocNode2, final int n, final int n2) {
        try {
            if (conditions == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "conditions", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addJump"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fromNode", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addJump"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        try {
            if (ocNode2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toNode", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addJump"));
            }
        }
        catch (OCDFAUtils.DFAException ex3) {
            throw b(ex3);
        }
        final List<OCConditionsList.Condition> conditions2 = conditions.getConditions();
        int n3 = n;
        OCNode ocNode3 = ocNode;
        int n4 = 0;
        while (true) {
            Label_0174: {
                try {
                    if (n4 >= conditions2.size()) {
                        break;
                    }
                    final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder = this;
                    final ThreadCallback threadCallback = ocContextSensitiveControlFlowBuilder.myThreadCallback;
                    if (threadCallback != null) {
                        break Label_0174;
                    }
                    break Label_0174;
                }
                catch (OCDFAUtils.DFAException ex4) {
                    throw b(ex4);
                }
                try {
                    final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder = this;
                    final ThreadCallback threadCallback = ocContextSensitiveControlFlowBuilder.myThreadCallback;
                    if (threadCallback != null) {
                        this.myThreadCallback.checkCancelled();
                    }
                }
                catch (OCDFAUtils.DFAException ex5) {
                    throw b(ex5);
                }
            }
            final OCConditionsList.Condition condition = conditions2.get(n4);
            final OCNode fakeNode = condition.getFakeNode();
            int myCurContext = 0;
            Label_0246: {
                try {
                    if (n4 < conditions2.size() - 1) {
                        this.myCurContext = (myCurContext = this.myCurContext) + 1;
                        break Label_0246;
                    }
                }
                catch (OCDFAUtils.DFAException ex6) {
                    throw b(ex6);
                }
                myCurContext = n2;
            }
            final int n5 = myCurContext;
            OCNode ocNode4 = null;
            Label_0272: {
                try {
                    if (n4 < conditions2.size() - 1) {
                        ocNode4 = fakeNode;
                        break Label_0272;
                    }
                }
                catch (OCDFAUtils.DFAException ex7) {
                    throw b(ex7);
                }
                ocNode4 = ocNode2;
            }
            final OCNode ocNode5 = ocNode4;
            try {
                this.a(n3, n5, condition);
                if (ocNode5 != null) {
                    this.myConsumer.addTuple(this.myNodeJump, this.myCallableAttribute, this.a(this.a(ocNode3, n3)), this.a(this.a(ocNode5, n5)));
                }
            }
            catch (OCDFAUtils.DFAException ex8) {
                throw b(ex8);
            }
            n3 = n5;
            ocNode3 = ocNode5;
            Label_0502: {
                Label_0377: {
                    try {
                        if (fakeNode == null) {
                            break Label_0502;
                        }
                        final OCConditionsList.Conditions conditions3 = conditions;
                        final Set<Integer> set = conditions3.getSkipEvaluationIndices();
                        if (set != null) {
                            break Label_0377;
                        }
                        break Label_0377;
                    }
                    catch (OCDFAUtils.DFAException ex9) {
                        throw b(ex9);
                    }
                    try {
                        final OCConditionsList.Conditions conditions3 = conditions;
                        final Set<Integer> set = conditions3.getSkipEvaluationIndices();
                        if (set != null) {
                            if (conditions.getSkipEvaluationIndices().contains(n4 + 1)) {
                                break Label_0502;
                            }
                        }
                    }
                    catch (OCDFAUtils.DFAException ex10) {
                        throw b(ex10);
                    }
                }
                this.myStatementContexts.putValue((Object)fakeNode, (Object)n3);
                for (final Pair pair : this.myFakeNodeAttributes.get((Object)fakeNode)) {
                    this.myConsumer.addTuple((RelationSignature)pair.getFirst(), (Attribute[])ArrayUtil.mergeArrays((Object[])new Attribute[] { this.a(n3) }, (Object[])pair.getSecond()));
                }
            }
            ++n4;
        }
    }
    
    private int a(@NotNull final OCNode ocNode, final int n) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getNodeId"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        Label_0074: {
            try {
                if (!ocNode.isFake()) {
                    return ocNode.getId();
                }
                final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder = this;
                final Map<Integer, Integer> map = ocContextSensitiveControlFlowBuilder.myFakeContexts;
                final int n2 = n;
                final Integer n3 = n2;
                final boolean b = map.containsKey(n3);
                if (b) {
                    break Label_0074;
                }
                break Label_0074;
            }
            catch (OCDFAUtils.DFAException ex2) {
                throw b(ex2);
            }
            try {
                final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder = this;
                final Map<Integer, Integer> map = ocContextSensitiveControlFlowBuilder.myFakeContexts;
                final int n2 = n;
                final Integer n3 = n2;
                final boolean b = map.containsKey(n3);
                if (b) {
                    return this.myFakeContexts.get(n);
                }
            }
            catch (OCDFAUtils.DFAException ex3) {
                throw b(ex3);
            }
        }
        final int nextNodeId = this.myGraph.getNextNodeId();
        this.myFakeContexts.put(n, nextNodeId);
        return nextNodeId;
    }
    
    private void a(final int n, final int n2, @NotNull final OCConditionsList.Condition condition) {
        try {
            if (condition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "condition", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addTuplesForJump"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        this.myConsumer.addTuple(condition.getRelation(), (Attribute[])ArrayUtil.mergeArrays((Object[])new Attribute[] { this.a(n), this.a(n2) }, (Object[])condition.getAttributes()));
        for (final OCConditionsList.BranchInfo branchInfo : condition.getBranches()) {
            RelationSignature relationSignature = null;
            Label_0143: {
                try {
                    if (branchInfo.isTrueBranch()) {
                        relationSignature = this.myConditionThenBranch;
                        break Label_0143;
                    }
                }
                catch (OCDFAUtils.DFAException ex2) {
                    throw b(ex2);
                }
                relationSignature = this.myConditionElseBranch;
            }
            final RelationSignature relationSignature2 = relationSignature;
            Object o = null;
            Label_0170: {
                try {
                    if (branchInfo.getCondition() != null) {
                        o = branchInfo.getCondition();
                        break Label_0170;
                    }
                }
                catch (OCDFAUtils.DFAException ex3) {
                    throw b(ex3);
                }
                o = this.myFile;
            }
            final Attribute c = this.c((PsiElement)o);
            try {
                this.myConsumer.addTuple(relationSignature2, (Attribute[])ArrayUtil.mergeArrays((Object[])new Attribute[] { this.a(n2) }, (Object[])new Attribute[] { c }));
                if (!branchInfo.isCanBeShortCutted()) {
                    continue;
                }
                this.myConsumer.addTuple(this.myShortCuttedBranch, c);
            }
            catch (OCDFAUtils.DFAException ex4) {
                throw b(ex4);
            }
        }
    }
    
    @Override
    protected OCNode acceptCondition(@Nullable final OCElement ocElement, @NotNull final KidIterator kidIterator, final boolean b) {
        try {
            if (kidIterator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "itr", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "acceptCondition"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            kidIterator.skip((PsiElement)ocElement);
            kidIterator.skipLeaves();
            if (!b) {
                this.d();
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        final OCNode lastAddedNode = this.myGraph.getLastAddedNode();
        this.myGraph.forbidSplittingNodes();
        this.myBranchConditions.put((PsiElement)ocElement, this.b(ocElement));
        this.myGraph.allowSplittingNodes();
        this.a(lastAddedNode);
        return lastAddedNode;
    }
    
    @Override
    protected void addCaseStatement(@NotNull final OCCaseStatement ocCaseStatement, @NotNull final OCNode ocNode, @NotNull final SwitchInfo switchInfo) {
        try {
            if (ocCaseStatement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stmt", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addCaseStatement"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caseNode", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addCaseStatement"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        try {
            if (switchInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addCaseStatement"));
            }
        }
        catch (OCDFAUtils.DFAException ex3) {
            throw b(ex3);
        }
        final OCNode lastAddedNode = this.myGraph.getLastAddedNode();
        BranchConditions a = null;
        final OCExpression expression = ocCaseStatement.getExpression();
        switchInfo.addCaseExpression(expression);
        Object o = null;
        if (ocCaseStatement.isDefault()) {
            final OCConditionsList singleton = OCConditionsList.singleton(null, null, this.myUnconditionalJump, new Attribute[0]);
            a = new BranchConditions(singleton, singleton);
            o = ocCaseStatement;
            for (final OCExpression ocExpression : switchInfo.getCaseExpressions()) {
                final BranchConditions a2 = this.a(switchInfo.getExpression(), ocExpression, ocExpression, false, false);
                final OCConditionsList.BranchInfo branchInfo = new OCConditionsList.BranchInfo(null, false, false);
                if (a2 != null) {
                    a = new BranchConditions(a.getTrueConditions().combine(a2.getFalseConditions(), branchInfo), a.getFalseConditions());
                }
            }
            a.getTrueConditions().clearBranches();
        }
        else if (expression != null) {
            a = this.a(switchInfo.getExpression(), expression, expression, false, false);
            o = expression;
        }
        try {
            if (a != null) {
                this.a(lastAddedNode);
                this.myBranchConditions.put((PsiElement)o, a);
                this.addConditionalBranch((OCElement)o, true, switchInfo.getNode(), ocNode);
                return;
            }
        }
        catch (OCDFAUtils.DFAException ex4) {
            throw b(ex4);
        }
        super.addCaseStatement(ocCaseStatement, ocNode, switchInfo);
    }
    
    private void a(final OCNode ocNode) {
        try {
            while (this.myGraph.getLastAddedNode() != ocNode) {
                this.myGraph.removeNode(this.myGraph.getLastAddedNode(), true);
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
    }
    
    @Nullable
    private static BranchConditions a(@Nullable final BranchConditions branchConditions) {
        try {
            if (branchConditions != null) {
                return new BranchConditions(branchConditions.getFalseConditions(), branchConditions.getTrueConditions());
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @NotNull
    private BranchConditions b(@Nullable final OCElement ocElement) {
        BranchConditions a;
        try {
            a = this.a(ocElement, ocElement, false, false);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "doHandleConditionalBranch"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        return a;
    }
    
    @NotNull
    private BranchConditions a(@Nullable OCElement a, @Nullable final OCElement ocElement, final boolean b, final boolean b2) {
        BranchConditions branchConditions = null;
        final OCConditionsList.BranchInfo branchInfo = new OCConditionsList.BranchInfo((PsiElement)ocElement, true, b2);
        final OCConditionsList.BranchInfo branchInfo2 = new OCConditionsList.BranchInfo((PsiElement)ocElement, false, b2);
        a = a(a);
        Label_0869: {
            BranchConditions branchConditions2 = null;
            Label_0834: {
                Label_0816: {
                    if (a instanceof OCDeclarationOrExpression) {
                        branchConditions = this.a(((OCDeclarationOrExpression)a).getExpression(), ocElement, b, b2);
                    }
                    else {
                        Label_0139: {
                            try {
                                if (!(a instanceof OCUnaryExpression) || ((OCUnaryExpression)a).getOperationSign() != OCTokenTypes.EXCL) {
                                    break Label_0139;
                                }
                            }
                            catch (OCDFAUtils.DFAException ex) {
                                throw b(ex);
                            }
                            final OCUnaryExpression ocUnaryExpression = (OCUnaryExpression)a;
                            OCExpression operand = null;
                            boolean b3 = false;
                            Label_0126: {
                                try {
                                    operand = ocUnaryExpression.getOperand();
                                    if (!b) {
                                        b3 = true;
                                        break Label_0126;
                                    }
                                }
                                catch (OCDFAUtils.DFAException ex2) {
                                    throw b(ex2);
                                }
                                b3 = false;
                            }
                            branchConditions = a(this.a(operand, ocElement, b3, b2));
                            break Label_0816;
                        }
                        if (a instanceof OCBinaryExpression) {
                            final OCBinaryExpression ocBinaryExpression = (OCBinaryExpression)a;
                            final OCElementType operationSign = ocBinaryExpression.getOperationSign();
                            Label_0546: {
                                Label_0430: {
                                    Label_0184: {
                                        try {
                                            if (operationSign == OCTokenTypes.ANDAND) {
                                                break Label_0184;
                                            }
                                            final OCPunctuatorElementType ocPunctuatorElementType = (OCPunctuatorElementType)operationSign;
                                            final OCPunctuatorElementType ocPunctuatorElementType2 = OCTokenTypes.OROR;
                                            if (ocPunctuatorElementType != ocPunctuatorElementType2) {
                                                break Label_0184;
                                            }
                                            break Label_0184;
                                        }
                                        catch (OCDFAUtils.DFAException ex3) {
                                            throw b(ex3);
                                        }
                                        try {
                                            final OCPunctuatorElementType ocPunctuatorElementType = (OCPunctuatorElementType)operationSign;
                                            final OCPunctuatorElementType ocPunctuatorElementType2 = OCTokenTypes.OROR;
                                            if (ocPunctuatorElementType != ocPunctuatorElementType2) {
                                                if (operationSign != OCTokenTypes.XOR) {
                                                    break Label_0430;
                                                }
                                            }
                                        }
                                        catch (OCDFAUtils.DFAException ex4) {
                                            throw b(ex4);
                                        }
                                    }
                                    final BranchConditions a2 = this.a(ocBinaryExpression.getLeft(), ocBinaryExpression.getLeft(), false, b2);
                                    final BranchConditions a3 = this.a(ocBinaryExpression.getRight(), ocBinaryExpression.getRight(), false, true);
                                    final OCConditionsList trueConditions = a2.getTrueConditions();
                                    final OCConditionsList falseConditions = a2.getFalseConditions();
                                    final OCConditionsList trueConditions2 = a3.getTrueConditions();
                                    final OCConditionsList falseConditions2 = a3.getFalseConditions();
                                    if (operationSign == OCTokenTypes.ANDAND) {
                                        branchConditions = new BranchConditions(trueConditions.combine(trueConditions2, branchInfo), trueConditions.combine(falseConditions2, branchInfo2).merge(falseConditions.skipEvaluation(branchInfo2)));
                                    }
                                    else if (operationSign == OCTokenTypes.OROR) {
                                        branchConditions = new BranchConditions(falseConditions.combine(trueConditions2, branchInfo).merge(trueConditions.skipEvaluation(branchInfo)), falseConditions.combine(falseConditions2, branchInfo2));
                                    }
                                    else if (operationSign == OCTokenTypes.XOR) {
                                        branchConditions = new BranchConditions(falseConditions.combine(trueConditions2, branchInfo).merge(trueConditions.combine(falseConditions2, branchInfo)), falseConditions.combine(falseConditions2, branchInfo2).merge(trueConditions.combine(trueConditions2, branchInfo2)));
                                    }
                                    break Label_0546;
                                }
                                ++this.myVisitorRecursiveCnt;
                                a.accept((PsiElementVisitor)this);
                                --this.myVisitorRecursiveCnt;
                                if (operationSign == OCTokenTypes.EQEQ) {
                                    branchConditions = this.a(ocBinaryExpression.getLeft(), ocBinaryExpression.getRight(), ocElement, b, b2);
                                }
                                else {
                                    OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder = null;
                                    OCExpression ocExpression = null;
                                    OCExpression ocExpression2 = null;
                                    OCElement ocElement2 = null;
                                    boolean b5 = false;
                                    Label_0536: {
                                        Label_0527: {
                                            try {
                                                if (operationSign != OCTokenTypes.EXCLEQ) {
                                                    break Label_0546;
                                                }
                                                ocContextSensitiveControlFlowBuilder = this;
                                                final OCBinaryExpression ocBinaryExpression2 = ocBinaryExpression;
                                                ocExpression = ocBinaryExpression2.getLeft();
                                                final OCBinaryExpression ocBinaryExpression3 = ocBinaryExpression;
                                                ocExpression2 = ocBinaryExpression3.getRight();
                                                ocElement2 = ocElement;
                                                final boolean b4 = b;
                                                if (!b4) {
                                                    break Label_0527;
                                                }
                                                break Label_0527;
                                            }
                                            catch (OCDFAUtils.DFAException ex5) {
                                                throw b(ex5);
                                            }
                                            try {
                                                ocContextSensitiveControlFlowBuilder = this;
                                                final OCBinaryExpression ocBinaryExpression2 = ocBinaryExpression;
                                                ocExpression = ocBinaryExpression2.getLeft();
                                                final OCBinaryExpression ocBinaryExpression3 = ocBinaryExpression;
                                                ocExpression2 = ocBinaryExpression3.getRight();
                                                ocElement2 = ocElement;
                                                final boolean b4 = b;
                                                if (!b4) {
                                                    b5 = true;
                                                    break Label_0536;
                                                }
                                            }
                                            catch (OCDFAUtils.DFAException ex6) {
                                                throw b(ex6);
                                            }
                                        }
                                        b5 = false;
                                    }
                                    branchConditions = a(ocContextSensitiveControlFlowBuilder.a(ocExpression, ocExpression2, ocElement2, b5, b2));
                                }
                            }
                        }
                        else if (a != null) {
                            ++this.myVisitorRecursiveCnt;
                            a.accept((PsiElementVisitor)this);
                            --this.myVisitorRecursiveCnt;
                            Attribute attribute = null;
                            if (a instanceof OCAssignmentExpression) {
                                final OCAssignmentExpression ocAssignmentExpression = (OCAssignmentExpression)a;
                                if (ocAssignmentExpression.getOperationSign() == OCTokenTypes.EQ) {
                                    attribute = this.b((PsiElement)ocAssignmentExpression.getReceiverExpression());
                                }
                            }
                            if (attribute == null) {
                                attribute = this.b((PsiElement)a);
                            }
                            Label_0689: {
                                OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder2 = null;
                                OCElement ocElement3 = null;
                                Attribute attribute2 = null;
                                Attribute attribute3 = null;
                                boolean b7 = false;
                                Label_0672: {
                                    Label_0663: {
                                        try {
                                            if (attribute == null) {
                                                break Label_0689;
                                            }
                                            ocContextSensitiveControlFlowBuilder2 = this;
                                            ocElement3 = a;
                                            attribute2 = attribute;
                                            final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder3 = this;
                                            final long n = 0L;
                                            final OCNumber ocNumber = OCNumber.valueOf(n);
                                            final PsiElement psiElement = null;
                                            attribute3 = ocContextSensitiveControlFlowBuilder3.a(ocNumber, psiElement);
                                            final boolean b6 = b;
                                            if (!b6) {
                                                break Label_0663;
                                            }
                                            break Label_0663;
                                        }
                                        catch (OCDFAUtils.DFAException ex7) {
                                            throw b(ex7);
                                        }
                                        try {
                                            ocContextSensitiveControlFlowBuilder2 = this;
                                            ocElement3 = a;
                                            attribute2 = attribute;
                                            final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder3 = this;
                                            final long n = 0L;
                                            final OCNumber ocNumber = OCNumber.valueOf(n);
                                            final PsiElement psiElement = null;
                                            attribute3 = ocContextSensitiveControlFlowBuilder3.a(ocNumber, psiElement);
                                            final boolean b6 = b;
                                            if (!b6) {
                                                b7 = true;
                                                break Label_0672;
                                            }
                                        }
                                        catch (OCDFAUtils.DFAException ex8) {
                                            throw b(ex8);
                                        }
                                    }
                                    b7 = false;
                                }
                                branchConditions = a(ocContextSensitiveControlFlowBuilder2.a(ocElement3, attribute2, attribute3, b7, b2, OCIntType.BOOL, ocElement));
                                break Label_0816;
                            }
                            if (a instanceof OCExpression) {
                                final OCDFAUtils.InstanceOf instanceofPair = OCDFAUtils.getInstanceofPair((OCExpression)a);
                                try {
                                    if (instanceofPair == null || !this.isSymbolInScope(instanceofPair.symbol)) {
                                        break Label_0816;
                                    }
                                }
                                catch (OCDFAUtils.DFAException ex9) {
                                    throw b(ex9);
                                }
                                branchConditions = a(ocElement, b, b2, this.c(), this.myInstanceOfConditionalJump, this.myNonInstanceOfConditionalJump, this.d(instanceofPair.symbol), this.b(this.a(instanceofPair.type), (PsiElement)this.myFile));
                                this.addModifiedValue((PsiElement)instanceofPair.variable);
                                this.b(instanceofPair.type);
                            }
                        }
                    }
                    try {
                        if (branchConditions == null) {
                            break Label_0869;
                        }
                        branchConditions2 = branchConditions;
                        if (branchConditions2 == null) {
                            break Label_0834;
                        }
                        return branchConditions2;
                    }
                    catch (OCDFAUtils.DFAException ex10) {
                        throw b(ex10);
                    }
                }
                try {
                    branchConditions2 = branchConditions;
                    if (branchConditions2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "doHandleConditionalBranch"));
                    }
                }
                catch (OCDFAUtils.DFAException ex11) {
                    throw b(ex11);
                }
            }
            return branchConditions2;
        }
        final OCConditionsList singleton = OCConditionsList.singleton(null, null, this.myUnconditionalJump, new Attribute[0]);
        BranchConditions branchConditions3;
        try {
            branchConditions3 = new BranchConditions(singleton, singleton);
            if (branchConditions3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "doHandleConditionalBranch"));
            }
        }
        catch (OCDFAUtils.DFAException ex12) {
            throw b(ex12);
        }
        return branchConditions3;
    }
    
    @Override
    protected boolean doEnlargeNodes() {
        try {
            if (this.myVisitorRecursiveCnt == 0) {
                return true;
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        return false;
    }
    
    @Nullable
    private BranchConditions a(@Nullable OCExpression a, @Nullable OCExpression a2, @Nullable final OCElement ocElement, final boolean b, final boolean b2) {
        a = a(a);
        a2 = a(a2);
        final Attribute b3 = this.b((PsiElement)a);
        final Attribute b4 = this.b((PsiElement)a2);
        Label_0132: {
            Label_0097: {
                Label_0078: {
                    Label_0043: {
                        try {
                            if (!OCDFAUtils.isConstant(a)) {
                                break Label_0078;
                            }
                            final Attribute attribute = b4;
                            if (attribute != null) {
                                break Label_0043;
                            }
                            break Label_0078;
                        }
                        catch (OCDFAUtils.DFAException ex) {
                            throw b(ex);
                        }
                        try {
                            final Attribute attribute = b4;
                            if (attribute != null) {
                                return this.a(a2, b4, this.b(a), b, b2, this.a(a.getType().getGuessedType()), ocElement);
                            }
                        }
                        catch (OCDFAUtils.DFAException ex2) {
                            throw b(ex2);
                        }
                    }
                    try {
                        if (!OCDFAUtils.isConstant(a2)) {
                            break Label_0132;
                        }
                        final Attribute attribute2 = b3;
                        if (attribute2 != null) {
                            break Label_0097;
                        }
                        break Label_0132;
                    }
                    catch (OCDFAUtils.DFAException ex3) {
                        throw b(ex3);
                    }
                }
                try {
                    final Attribute attribute2 = b3;
                    if (attribute2 != null) {
                        return this.a(a, b3, this.b(a2), b, b2, this.a(a2.getType().getGuessedType()), ocElement);
                    }
                }
                catch (OCDFAUtils.DFAException ex4) {
                    throw b(ex4);
                }
            }
            try {
                if (b3 == null || b4 == null) {
                    return null;
                }
            }
            catch (OCDFAUtils.DFAException ex5) {
                throw b(ex5);
            }
        }
        return a(ocElement, b, b2, this.c(), this.myEqualityToVarConditionalJump, this.myNonEqualityToVarConditionalJump, b3, b4);
    }
    
    private BranchConditions a(@NotNull final OCElement ocElement, @NotNull final Attribute attribute, @NotNull final Attribute attribute2, final boolean b, final boolean b2, @NotNull final OCType ocType, @Nullable final OCElement ocElement2) {
        try {
            if (ocElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addEqualityToConst"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (attribute == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "exprAttr", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addEqualityToConst"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        try {
            if (attribute2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "constAttr", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addEqualityToConst"));
            }
        }
        catch (OCDFAUtils.DFAException ex3) {
            throw b(ex3);
        }
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "constType", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addEqualityToConst"));
            }
        }
        catch (OCDFAUtils.DFAException ex4) {
            throw b(ex4);
        }
        Label_0203: {
            try {
                if (!attribute2.getKey().equals("0")) {
                    break Label_0203;
                }
                final OCElement ocElement3 = ocElement;
                final boolean b3 = ocElement3 instanceof OCReferenceExpression;
                if (b3) {
                    break Label_0203;
                }
                break Label_0203;
            }
            catch (OCDFAUtils.DFAException ex5) {
                throw b(ex5);
            }
            try {
                final OCElement ocElement3 = ocElement;
                final boolean b3 = ocElement3 instanceof OCReferenceExpression;
                if (b3) {
                    this.addModifiedValue((PsiElement)ocElement);
                }
            }
            catch (OCDFAUtils.DFAException ex6) {
                throw b(ex6);
            }
        }
        this.myConsumer.addTuple(this.myConstantType, attribute2, this.a(attribute2.getKey(), null, KnownDomainTypes.DOM_OBJECTS), this.b(ocType, (PsiElement)ocElement));
        this.b(ocType);
        return a(ocElement2, b, b2, this.c(), this.myEqualityToConstConditionalJump, this.myNonEqualityToConstConditionalJump, attribute, attribute2);
    }
    
    @Contract("null -> null")
    @Nullable
    private Attribute b(@Nullable final PsiElement psiElement) {
        Label_0025: {
            try {
                if (!(psiElement instanceof OCExpression)) {
                    return null;
                }
                final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder = this;
                final PsiElement psiElement2 = psiElement;
                final OCExpression ocExpression = (OCExpression)psiElement2;
                final boolean b = ocContextSensitiveControlFlowBuilder.isSymbolInScope(ocExpression);
                if (b) {
                    break Label_0025;
                }
                break Label_0025;
            }
            catch (OCDFAUtils.DFAException ex) {
                throw b(ex);
            }
            try {
                final OCContextSensitiveControlFlowBuilder ocContextSensitiveControlFlowBuilder = this;
                final PsiElement psiElement2 = psiElement;
                final OCExpression ocExpression = (OCExpression)psiElement2;
                final boolean b = ocContextSensitiveControlFlowBuilder.isSymbolInScope(ocExpression);
                if (b) {
                    return this.a(psiElement);
                }
            }
            catch (OCDFAUtils.DFAException ex2) {
                throw b(ex2);
            }
        }
        if (this.myGlobalAnalysis) {
            final OCSymbol callSymbol = OCDFAUtils.getCallSymbol(psiElement);
            if (callSymbol != null) {
                final Attribute c = this.c(psiElement);
                this.a(this.myAssignmentToCall, c, this.c(callSymbol));
                this.myConsumer.addTuple(this.myVariableType, c, this.b(this.a(((OCExpression)psiElement).getResolvedType().getGuessedType()), psiElement));
                this.d();
                return c;
            }
        }
        return null;
    }
    
    @NotNull
    private OCNode c() {
        final OCNode addNode = this.myGraph.addNode(true);
        OCNode ocNode;
        try {
            this.myFakeNodes.add(addNode);
            ocNode = addNode;
            if (ocNode == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "addFakeNode"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        return ocNode;
    }
    
    private static BranchConditions a(@Nullable final OCElement ocElement, final boolean b, final boolean b2, @Nullable final OCNode ocNode, @NotNull final RelationSignature relationSignature, @NotNull final RelationSignature relationSignature2, @NotNull final Attribute... array) {
        try {
            if (relationSignature == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "trueRelation", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getBranchConditions"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (relationSignature2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "falseRelation", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getBranchConditions"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "getBranchConditions"));
            }
        }
        catch (OCDFAUtils.DFAException ex3) {
            throw b(ex3);
        }
        try {
            if (!b) {
                final boolean b3 = true;
                return new BranchConditions(OCConditionsList.singleton(new OCConditionsList.BranchInfo((PsiElement)ocElement, b3, b2), ocNode, relationSignature, array), OCConditionsList.singleton(new OCConditionsList.BranchInfo((PsiElement)ocElement, b, b2), ocNode, relationSignature2, array));
            }
        }
        catch (OCDFAUtils.DFAException ex4) {
            throw b(ex4);
        }
        final boolean b3 = false;
        return new BranchConditions(OCConditionsList.singleton(new OCConditionsList.BranchInfo((PsiElement)ocElement, b3, b2), ocNode, relationSignature, array), OCConditionsList.singleton(new OCConditionsList.BranchInfo((PsiElement)ocElement, b, b2), ocNode, relationSignature2, array));
    }
    
    @Override
    protected void processAssignment(@NotNull final OCSymbol p0, @Nullable final PsiElement p1, @Nullable final OCExpression p2, final boolean p3, final boolean p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processAssignment"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_1        
        //    46: aload_2        
        //    47: aload_3        
        //    48: iload           4
        //    50: iload           5
        //    52: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.processAssignment:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/psi/OCExpression;ZZ)V
        //    55: aconst_null    
        //    56: astore          6
        //    58: aload_3        
        //    59: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    62: astore_3       
        //    63: aload_0        
        //    64: aload_1        
        //    65: invokevirtual   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.isSymbolInScope:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    68: ifeq            78
        //    71: aload_0        
        //    72: aload_1        
        //    73: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.d:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //    76: astore          6
        //    78: aload           6
        //    80: ifnull          650
        //    83: aload_0        
        //    84: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myVariableWrites:Lcom/intellij/util/containers/MultiMap;
        //    87: aload_0        
        //    88: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //    91: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getLastAddedNode:()Lcom/jetbrains/cidr/lang/dfa/OCNode;
        //    94: invokevirtual   com/intellij/util/containers/MultiMap.get:(Ljava/lang/Object;)Ljava/util/Collection;
        //    97: astore          7
        //    99: aload           7
        //   101: aload           6
        //   103: invokeinterface java/util/Collection.contains:(Ljava/lang/Object;)Z
        //   108: ifne            148
        //   111: aload_0        
        //   112: aload_3        
        //   113: invokevirtual   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.isSymbolInScope:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   116: ifeq            159
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   125: athrow         
        //   126: aload           7
        //   128: aload_0        
        //   129: aload_3        
        //   130: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   133: invokeinterface java/util/Collection.contains:(Ljava/lang/Object;)Z
        //   138: ifeq            159
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   147: athrow         
        //   148: aload_0        
        //   149: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.d:()V
        //   152: goto            159
        //   155: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   158: athrow         
        //   159: aload_0        
        //   160: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myVariableWrites:Lcom/intellij/util/containers/MultiMap;
        //   163: aload_0        
        //   164: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //   167: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getLastAddedNode:()Lcom/jetbrains/cidr/lang/dfa/OCNode;
        //   170: aload           6
        //   172: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   175: aconst_null    
        //   176: astore          8
        //   178: aconst_null    
        //   179: astore          9
        //   181: aload_3        
        //   182: ifnonnull       216
        //   185: aload_0        
        //   186: aload_0        
        //   187: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myAssignmentToExpression:Lcom/jetbrains/sourceglider/relations/RelationSignature;
        //   190: iconst_2       
        //   191: anewarray       Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   194: dup            
        //   195: iconst_0       
        //   196: aload           6
        //   198: aastore        
        //   199: dup            
        //   200: iconst_1       
        //   201: aload_0        
        //   202: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:()Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   205: aastore        
        //   206: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Lcom/jetbrains/sourceglider/relations/RelationSignature;[Lcom/jetbrains/sourceglider/atttributes/Attribute;)V
        //   209: goto            520
        //   212: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   215: athrow         
        //   216: iload           4
        //   218: ifeq            252
        //   221: aload_0        
        //   222: aload_0        
        //   223: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myAssignmentToExpression:Lcom/jetbrains/sourceglider/relations/RelationSignature;
        //   226: iconst_2       
        //   227: anewarray       Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   230: dup            
        //   231: iconst_0       
        //   232: aload           6
        //   234: aastore        
        //   235: dup            
        //   236: iconst_1       
        //   237: aload_0        
        //   238: aload_3        
        //   239: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.c:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   242: dup            
        //   243: astore          8
        //   245: aastore        
        //   246: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Lcom/jetbrains/sourceglider/relations/RelationSignature;[Lcom/jetbrains/sourceglider/atttributes/Attribute;)V
        //   249: goto            520
        //   252: aload_3        
        //   253: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.isConstant:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   256: ifeq            296
        //   259: aload_0        
        //   260: aload_0        
        //   261: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myAssignmentToConstant:Lcom/jetbrains/sourceglider/relations/RelationSignature;
        //   264: iconst_2       
        //   265: anewarray       Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   268: dup            
        //   269: iconst_0       
        //   270: aload           6
        //   272: aastore        
        //   273: dup            
        //   274: iconst_1       
        //   275: aload_0        
        //   276: aload_3        
        //   277: aload_1        
        //   278: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   283: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   286: dup            
        //   287: astore          9
        //   289: aastore        
        //   290: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Lcom/jetbrains/sourceglider/relations/RelationSignature;[Lcom/jetbrains/sourceglider/atttributes/Attribute;)V
        //   293: goto            520
        //   296: aload_3        
        //   297: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNewExpression;
        //   300: ifeq            353
        //   303: aload_3        
        //   304: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCppNewExpression;
        //   307: invokeinterface com/jetbrains/cidr/lang/psi/OCCppNewExpression.isNoThrow:()Z
        //   312: ifne            353
        //   315: goto            322
        //   318: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   321: athrow         
        //   322: aload_0        
        //   323: aload_0        
        //   324: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myAssignmentToConstant:Lcom/jetbrains/sourceglider/relations/RelationSignature;
        //   327: iconst_2       
        //   328: anewarray       Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   331: dup            
        //   332: iconst_0       
        //   333: aload           6
        //   335: aastore        
        //   336: dup            
        //   337: iconst_1       
        //   338: aload_0        
        //   339: aload_3        
        //   340: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.c:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   343: dup            
        //   344: astore          9
        //   346: aastore        
        //   347: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Lcom/jetbrains/sourceglider/relations/RelationSignature;[Lcom/jetbrains/sourceglider/atttributes/Attribute;)V
        //   350: goto            520
        //   353: aload_0        
        //   354: aload_3        
        //   355: invokevirtual   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.isSymbolInScope:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   358: ifeq            393
        //   361: aload_0        
        //   362: aload_0        
        //   363: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myAssignmentToVar:Lcom/jetbrains/sourceglider/relations/RelationSignature;
        //   366: iconst_2       
        //   367: anewarray       Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   370: dup            
        //   371: iconst_0       
        //   372: aload           6
        //   374: aastore        
        //   375: dup            
        //   376: iconst_1       
        //   377: aload_0        
        //   378: aload_3        
        //   379: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   382: aastore        
        //   383: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Lcom/jetbrains/sourceglider/relations/RelationSignature;[Lcom/jetbrains/sourceglider/atttributes/Attribute;)V
        //   386: goto            520
        //   389: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   392: athrow         
        //   393: aload_0        
        //   394: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myGlobalAnalysis:Z
        //   397: ifeq            492
        //   400: aload_3        
        //   401: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils.getCallSymbol:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   404: astore          10
        //   406: aload           10
        //   408: ifnull          424
        //   411: aload_0        
        //   412: aload           10
        //   414: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.c:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   417: goto            425
        //   420: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   423: athrow         
        //   424: aconst_null    
        //   425: astore          11
        //   427: aload           11
        //   429: ifnull          461
        //   432: aload_0        
        //   433: aload_0        
        //   434: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myAssignmentToCall:Lcom/jetbrains/sourceglider/relations/RelationSignature;
        //   437: iconst_2       
        //   438: anewarray       Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   441: dup            
        //   442: iconst_0       
        //   443: aload           6
        //   445: aastore        
        //   446: dup            
        //   447: iconst_1       
        //   448: aload           11
        //   450: aastore        
        //   451: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Lcom/jetbrains/sourceglider/relations/RelationSignature;[Lcom/jetbrains/sourceglider/atttributes/Attribute;)V
        //   454: goto            489
        //   457: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   460: athrow         
        //   461: aload_0        
        //   462: aload_0        
        //   463: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myAssignmentToExpression:Lcom/jetbrains/sourceglider/relations/RelationSignature;
        //   466: iconst_2       
        //   467: anewarray       Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   470: dup            
        //   471: iconst_0       
        //   472: aload           6
        //   474: aastore        
        //   475: dup            
        //   476: iconst_1       
        //   477: aload_0        
        //   478: aload_3        
        //   479: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.c:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   482: dup            
        //   483: astore          8
        //   485: aastore        
        //   486: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Lcom/jetbrains/sourceglider/relations/RelationSignature;[Lcom/jetbrains/sourceglider/atttributes/Attribute;)V
        //   489: goto            520
        //   492: aload_0        
        //   493: aload_0        
        //   494: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myAssignmentToExpression:Lcom/jetbrains/sourceglider/relations/RelationSignature;
        //   497: iconst_2       
        //   498: anewarray       Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   501: dup            
        //   502: iconst_0       
        //   503: aload           6
        //   505: aastore        
        //   506: dup            
        //   507: iconst_1       
        //   508: aload_0        
        //   509: aload_3        
        //   510: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.c:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   513: dup            
        //   514: astore          8
        //   516: aastore        
        //   517: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Lcom/jetbrains/sourceglider/relations/RelationSignature;[Lcom/jetbrains/sourceglider/atttributes/Attribute;)V
        //   520: aload           8
        //   522: ifnonnull       537
        //   525: aload           9
        //   527: ifnull          650
        //   530: goto            537
        //   533: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   536: athrow         
        //   537: aload_0        
        //   538: aload_3        
        //   539: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   544: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getGuessedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   547: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   550: astore          10
        //   552: aload           8
        //   554: ifnull          596
        //   557: aload_0        
        //   558: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myConsumer:Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$TuplesConsumer;
        //   561: aload_0        
        //   562: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myExpressionType:Lcom/jetbrains/sourceglider/relations/RelationSignature;
        //   565: iconst_2       
        //   566: anewarray       Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   569: dup            
        //   570: iconst_0       
        //   571: aload           8
        //   573: aastore        
        //   574: dup            
        //   575: iconst_1       
        //   576: aload_0        
        //   577: aload           10
        //   579: aload_3        
        //   580: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   583: aastore        
        //   584: invokeinterface com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$TuplesConsumer.addTuple:(Lcom/jetbrains/sourceglider/relations/RelationSignature;[Lcom/jetbrains/sourceglider/atttributes/Attribute;)V
        //   589: goto            644
        //   592: invokestatic    com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;)Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //   595: athrow         
        //   596: aload_0        
        //   597: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myConsumer:Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$TuplesConsumer;
        //   600: aload_0        
        //   601: getfield        com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.myConstantType:Lcom/jetbrains/sourceglider/relations/RelationSignature;
        //   604: iconst_3       
        //   605: anewarray       Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   608: dup            
        //   609: iconst_0       
        //   610: aload           9
        //   612: aastore        
        //   613: dup            
        //   614: iconst_1       
        //   615: aload_0        
        //   616: aload           9
        //   618: invokevirtual   com/jetbrains/sourceglider/atttributes/Attribute.getKey:()Ljava/lang/String;
        //   621: aload_3        
        //   622: getstatic       com/jetbrains/sourceglider/symtable/KnownDomainTypes.DOM_OBJECTS:Ljava/lang/String;
        //   625: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.a:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   628: aastore        
        //   629: dup            
        //   630: iconst_2       
        //   631: aload_0        
        //   632: aload           10
        //   634: aload_3        
        //   635: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/sourceglider/atttributes/Attribute;
        //   638: aastore        
        //   639: invokeinterface com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$TuplesConsumer.addTuple:(Lcom/jetbrains/sourceglider/relations/RelationSignature;[Lcom/jetbrains/sourceglider/atttributes/Attribute;)V
        //   644: aload_0        
        //   645: aload           10
        //   647: invokespecial   com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder.b:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   650: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                  
        //  -----  -----  -----  -----  ----------------------------------------------------------------------
        //  0      40     40     44     Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  99     119    122    126    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  111    141    144    148    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  126    152    155    159    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  181    212    212    216    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  296    315    318    322    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  353    389    389    393    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  406    420    420    424    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  427    457    457    461    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  520    530    533    537    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        //  552    592    592    596    Lcom/jetbrains/cidr/lang/dfa/contextSensitive/OCDFAUtils$DFAException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0126:
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
    public void visitCastExpression(final OCCastExpression ocCastExpression) {
        super.visitCastExpression(ocCastExpression);
        final OCExpression operand = ocCastExpression.getOperand();
        if (this.isSymbolInScope(operand)) {
            final OCType a = this.a(ocCastExpression.getCastType().resolve(this.myFile));
            final Attribute a2 = this.a((PsiElement)operand);
            try {
                if (this.myVariableWrites.get((Object)this.myGraph.getLastAddedNode()).contains(a2)) {
                    this.d();
                }
            }
            catch (OCDFAUtils.DFAException ex) {
                throw b(ex);
            }
            this.a(this.myCastExpression, a2, this.b(a, (PsiElement)ocCastExpression), this.c((PsiElement)ocCastExpression));
            this.b(a);
        }
    }
    
    private void b(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "doProcessReference"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        if (ocSymbol.getKind().isLocal()) {
            final Attribute d = this.d(ocSymbol);
            try {
                if (this.myVariableWrites.get((Object)this.myGraph.getLastAddedNode()).contains(d)) {
                    this.d();
                }
            }
            catch (OCDFAUtils.DFAException ex2) {
                throw b(ex2);
            }
            this.myVariableWrites.putValue((Object)this.myGraph.getLastAddedNode(), (Object)d);
            this.a(this.myGetReferenceExpression, d);
        }
    }
    
    @Override
    protected void processReference(@NotNull final PsiElement psiElement, @NotNull final OCSymbol ocSymbol) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "processReference"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "processReference"));
            }
        }
        catch (OCDFAUtils.DFAException ex2) {
            throw b(ex2);
        }
        super.processReference(psiElement, ocSymbol);
        this.b(ocSymbol);
    }
    
    @Override
    protected void processReferenceFromBlock(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "processReferenceFromBlock"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        super.processReferenceFromBlock(ocSymbol);
        this.b(ocSymbol);
    }
    
    @Override
    protected void processDereference(@NotNull final OCReferenceExpression ocReferenceExpression) {
        try {
            if (ocReferenceExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "processDereference"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        super.processDereference(ocReferenceExpression);
        if (this.isSymbolInScope(ocReferenceExpression)) {
            final Attribute a = this.a((PsiElement)ocReferenceExpression);
            try {
                if (this.myVariableWrites.get((Object)this.myGraph.getLastAddedNode()).contains(a)) {
                    this.d();
                }
            }
            catch (OCDFAUtils.DFAException ex2) {
                throw b(ex2);
            }
            this.a(this.myDereferenceExpression, a, this.c((PsiElement)ocReferenceExpression));
        }
    }
    
    @Override
    public void visitCallExpression(final OCCallExpression ocCallExpression) {
        if (this.myGlobalAnalysis) {
            final OCExpression functionReferenceExpression = ocCallExpression.getFunctionReferenceExpression();
            OCExpression qualifier = null;
            OCSymbol ocSymbol = null;
            if (functionReferenceExpression instanceof OCReferenceExpression) {
                ocSymbol = ((OCReferenceExpression)functionReferenceExpression).resolveToSymbol();
            }
            else if (functionReferenceExpression instanceof OCQualifiedExpression) {
                ocSymbol = ((OCQualifiedExpression)functionReferenceExpression).resolveToSymbol();
                qualifier = ((OCQualifiedExpression)functionReferenceExpression).getQualifier();
            }
            this.a(ocSymbol, qualifier, ocCallExpression.getArguments());
        }
        super.visitCallExpression(ocCallExpression);
    }
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        final OCExpression diveIntoParenthesesAndCasts = OCParenthesesUtils.diveIntoParenthesesAndCasts(ocSendMessageExpression.getReceiverExpression());
        try {
            if (this.myGlobalAnalysis) {
                this.a(ocSendMessageExpression.getProbableResponders().getKnownResponder(), ocSendMessageExpression.getReceiverExpression(), ocSendMessageExpression.getArgumentExpressions());
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        Label_0269: {
            try {
                if (diveIntoParenthesesAndCasts == null || !this.isSymbolInScope(diveIntoParenthesesAndCasts)) {
                    break Label_0269;
                }
            }
            catch (OCDFAUtils.DFAException ex2) {
                throw b(ex2);
            }
            final OCSendMessageExpression.ProbableResponders probableResponders = ocSendMessageExpression.getProbableResponders();
            final Attribute a = this.a((PsiElement)diveIntoParenthesesAndCasts);
            try {
                if (this.myVariableWrites.get((Object)this.myGraph.getLastAddedNode()).contains(a)) {
                    this.d();
                }
            }
            catch (OCDFAUtils.DFAException ex3) {
                throw b(ex3);
            }
            try {
                this.a(this.myMessageReceiverExpression, a, this.c((PsiElement)diveIntoParenthesesAndCasts));
                if (probableResponders.getKnownResponder() != null || probableResponders.getAllResponders().isEmpty()) {
                    break Label_0269;
                }
            }
            catch (OCDFAUtils.DFAException ex4) {
                throw b(ex4);
            }
            final Iterator<OCMethodSymbol> iterator = probableResponders.getAllResponders().iterator();
            while (iterator.hasNext()) {
                final OCType a2 = this.a(OCPointerType.to(((OCSymbolWithParent<T, OCClassSymbol>)iterator.next()).getParent().getResolvedType()));
                this.a(this.myDynamicCallExpression, a, this.b(a2, (PsiElement)ocSendMessageExpression), this.c((PsiElement)ocSendMessageExpression));
                this.b(a2);
            }
        }
        super.visitSendMessageExpression(ocSendMessageExpression);
    }
    
    private void a(@Nullable final OCSymbol ocSymbol, @Nullable final OCExpression ocExpression, @NotNull final List<OCExpression> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder", "processCall"));
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        if (OCDFAUtils.isGoodCallable(this.myFile, ocSymbol)) {
            this.d();
            final Attribute c = this.c(ocSymbol);
            final Attribute b = this.b((PsiElement)ocExpression);
            Label_0179: {
                try {
                    this.a(this.myCallStatement, c);
                    if (b != null) {
                        this.a(this.myVarArgument, c, this.b(0), b);
                        break Label_0179;
                    }
                }
                catch (OCDFAUtils.DFAException ex2) {
                    throw b(ex2);
                }
                try {
                    if (OCDFAUtils.isConstant(ocExpression)) {
                        this.a(this.myConstArgument, c, this.b(0), this.b(ocExpression));
                    }
                }
                catch (OCDFAUtils.DFAException ex3) {
                    throw b(ex3);
                }
            }
            for (int i = 0; i < list.size(); ++i) {
                final OCExpression ocExpression2 = list.get(i);
                final Attribute b2 = this.b((PsiElement)ocExpression2);
                try {
                    if (b2 != null) {
                        this.a(this.myVarArgument, c, this.b(i + 1), b2);
                        continue;
                    }
                }
                catch (OCDFAUtils.DFAException ex4) {
                    throw b(ex4);
                }
                try {
                    if (OCDFAUtils.isConstant(ocExpression2)) {
                        this.a(this.myConstArgument, c, this.b(i + 1), this.b(ocExpression2));
                    }
                }
                catch (OCDFAUtils.DFAException ex5) {
                    throw b(ex5);
                }
            }
        }
        else {
            this.a(this.myExternalCallStatement, new Attribute[0]);
        }
    }
    
    private static OCElement a(@Nullable final OCElement ocElement) {
        try {
            if (ocElement instanceof OCExpression) {
                return a((OCExpression)ocElement);
            }
        }
        catch (OCDFAUtils.DFAException ex) {
            throw b(ex);
        }
        return ocElement;
    }
    
    private static OCExpression a(@Nullable final OCExpression ocExpression) {
        return OCParenthesesUtils.diveIntoParenthesesAndCasts(ocExpression, (OCCastKind[])ArrayUtil.remove((Object[])OCCastKind.values(), (Object)OCCastKind.DYNAMIC_CAST));
    }
    
    @Override
    public void visitReturnStatement(final OCReturnStatement ocReturnStatement) {
        Label_0154: {
            if (this.myGlobalAnalysis) {
                final OCExpression expression = ocReturnStatement.getExpression();
                final Attribute b = this.b((PsiElement)expression);
                final OCCallable ocCallable = (OCCallable)PsiTreeUtil.getParentOfType((PsiElement)ocReturnStatement, (Class)OCCallable.class);
                OCSymbol symbol = null;
                Label_0051: {
                    try {
                        if (ocCallable != null) {
                            symbol = ocCallable.getSymbol();
                            break Label_0051;
                        }
                    }
                    catch (OCDFAUtils.DFAException ex) {
                        throw b(ex);
                    }
                    symbol = null;
                }
                final OCSymbol ocSymbol = symbol;
                if (ocSymbol != null) {
                    final Attribute c = this.c(ocSymbol);
                    try {
                        this.a(this.myExitStatement, c);
                        if (b != null) {
                            this.a(this.myReturnVarStatement, b, c);
                            break Label_0154;
                        }
                    }
                    catch (OCDFAUtils.DFAException ex2) {
                        throw b(ex2);
                    }
                    try {
                        if (OCDFAUtils.isConstant(expression)) {
                            this.a(this.myReturnConstStatement, this.b(expression), c);
                        }
                    }
                    catch (OCDFAUtils.DFAException ex3) {
                        throw b(ex3);
                    }
                }
            }
        }
        super.visitReturnStatement(ocReturnStatement);
    }
    
    public void addContextInfos() {
        final OCCallable ocCallable = (OCCallable)this.myGraph.getCodeFragment();
        final Document document = PsiDocumentManager.getInstance(ocCallable.getProject()).getDocument(ocCallable.getContainingFile());
        final HashMap<Integer, OCElementsRange> hashMap = new HashMap<Integer, OCElementsRange>();
        this.myRangesMap.put(ocCallable, hashMap);
        if (document != null) {
            for (final OCNode ocNode : this.myStatementContexts.keySet()) {
                final OCElementsRange range = ocNode.getRange();
                Label_0117: {
                    try {
                        if (range == null) {
                            break Label_0117;
                        }
                        final OCNode ocNode2 = ocNode;
                        final boolean b = ocNode2.isFake();
                        if (!b) {
                            break Label_0117;
                        }
                        break Label_0117;
                    }
                    catch (OCDFAUtils.DFAException ex) {
                        throw b(ex);
                    }
                    try {
                        final OCNode ocNode2 = ocNode;
                        final boolean b = ocNode2.isFake();
                        if (!b) {
                            hashMap.put(ocNode.getId(), range);
                        }
                    }
                    catch (OCDFAUtils.DFAException ex2) {
                        throw b(ex2);
                    }
                }
                for (final int intValue : this.myStatementContexts.get((Object)ocNode)) {
                    try {
                        if (this.myThreadCallback != null) {
                            this.myThreadCallback.checkCancelled();
                        }
                    }
                    catch (OCDFAUtils.DFAException ex3) {
                        throw b(ex3);
                    }
                    this.myConsumer.addTuple(this.myContextInfo, this.myCallableAttribute, this.a(this.a(ocNode, intValue)), this.a(intValue));
                }
            }
        }
    }
    
    static {
        LOG = Logger.getInstance("com.jetbrains.cidr.lang.dfa.OCContextSensitiveControlFlowBuilder");
    }
    
    private static OCDFAUtils.DFAException b(final OCDFAUtils.DFAException ex) {
        return ex;
    }
    
    private class MyNodeState implements NodeState
    {
        private final int myContextsCnt;
        
        private MyNodeState(final OCNode ocNode) {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$MyNodeState", "<init>"));
            }
            this.myContextsCnt = OCContextSensitiveControlFlowBuilder.this.myStatementContexts.get((Object)ocNode).size();
        }
    }
    
    static class BranchConditions
    {
        OCConditionsList myTrueConditions;
        OCConditionsList myFalseConditions;
        
        BranchConditions(@NotNull final OCConditionsList myTrueConditions, @NotNull final OCConditionsList myFalseConditions) {
            if (myTrueConditions == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "trueConditions", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$BranchConditions", "<init>"));
            }
            if (myFalseConditions == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "falseConditions", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$BranchConditions", "<init>"));
            }
            this.myTrueConditions = myTrueConditions;
            this.myFalseConditions = myFalseConditions;
        }
        
        @NotNull
        OCConditionsList getTrueConditions() {
            OCConditionsList myTrueConditions;
            try {
                myTrueConditions = this.myTrueConditions;
                if (myTrueConditions == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$BranchConditions", "getTrueConditions"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myTrueConditions;
        }
        
        @NotNull
        OCConditionsList getFalseConditions() {
            OCConditionsList myFalseConditions;
            try {
                myFalseConditions = this.myFalseConditions;
                if (myFalseConditions == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/contextSensitive/OCContextSensitiveControlFlowBuilder$BranchConditions", "getFalseConditions"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myFalseConditions;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    @FunctionalInterface
    public interface TuplesConsumer
    {
        void addTuple(final RelationSignature p0, final Attribute... p1);
    }
}
