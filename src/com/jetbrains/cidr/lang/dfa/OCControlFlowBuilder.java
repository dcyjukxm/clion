// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.jetbrains.cidr.lang.psi.impl.OCAsmStatementPartImpl;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.jetbrains.cidr.lang.psi.OCParenthesizedExpression;
import com.jetbrains.cidr.lang.psi.OCArraySelectionExpression;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCPrefixExpression;
import com.jetbrains.cidr.lang.psi.OCPostfixExpression;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.daemon.OCAnnotatorSink;
import com.jetbrains.cidr.lang.daemon.OCNullAnnotatorSink;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCCatchSection;
import com.jetbrains.cidr.lang.psi.OCTryStatement;
import com.jetbrains.cidr.lang.psi.OCThrowExpression;
import com.jetbrains.cidr.lang.psi.OCReturnStatement;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.intentions.OCCreateMissingSwitchCasesIntentionAction;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.psi.OCSwitchStatement;
import com.jetbrains.cidr.lang.psi.OCCaseStatement;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCGotoStatement;
import com.jetbrains.cidr.lang.symbols.cpp.OCLabelSymbol;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCLabeledStatement;
import com.jetbrains.cidr.lang.psi.OCContinueStatement;
import com.jetbrains.cidr.lang.psi.OCBreakStatement;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCDoWhileStatement;
import com.jetbrains.cidr.lang.psi.OCForeachStatement;
import com.jetbrains.cidr.lang.psi.OCForStatement;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCWhileStatement;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.psi.OCConditionalExpression;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.lang.ASTNode;
import java.util.ArrayList;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.containers.MostlySingularMultiMap;
import java.util.Map;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Ref;
import java.util.Stack;
import java.util.List;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

public class OCControlFlowBuilder extends OCVisitor
{
    private OCDataFlowAnalyzer myAnalyzer;
    protected OCControlFlowGraph myGraph;
    private TextRange mySelection;
    private boolean myHasCrossSelectionJumps;
    private boolean myHasTopLevelCaseStatements;
    private List<OCNode> myBreakNodes;
    protected List<OCNode> myContinueNodes;
    private Stack<List<OCNode>> myTryThrows;
    private Stack<Ref<OCNode>> myTryFirstCalls;
    private Stack<SwitchInfo> mySwitchStack;
    private Stack<List<PsiElement>> myValuesStack;
    private Map<String, OCNode> myLabeledNodes;
    private MostlySingularMultiMap<String, OCNode> myGotoNodes;
    private int myShortCircuitDepth;
    private static final TokenSet closingTokens;
    
    public OCControlFlowBuilder(@Nullable final OCDataFlowAnalyzer myAnalyzer, @NotNull final OCControlFlowGraph ocControlFlowGraph, @Nullable final TextRange mySelection) {
        if (ocControlFlowGraph == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "graph", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "<init>"));
        }
        this.myAnalyzer = myAnalyzer;
        this.mySelection = mySelection;
        this.init(ocControlFlowGraph);
    }
    
    protected OCControlFlowBuilder() {
    }
    
    protected void init(@NotNull final OCControlFlowGraph myGraph) {
        try {
            if (myGraph == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "graph", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "init"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myGraph = myGraph;
        this.myGotoNodes = (MostlySingularMultiMap<String, OCNode>)new MostlySingularMultiMap();
        this.myLabeledNodes = new HashMap<String, OCNode>();
        this.mySwitchStack = new Stack<SwitchInfo>();
        this.myValuesStack = new Stack<List<PsiElement>>();
        this.myTryThrows = new Stack<List<OCNode>>();
        this.myTryFirstCalls = new Stack<Ref<OCNode>>();
        this.myContinueNodes = new ArrayList<OCNode>();
        this.myBreakNodes = new ArrayList<OCNode>();
    }
    
    public void visitElement(@Nullable final PsiElement psiElement) {
        try {
            if (psiElement != null) {
                this.a(psiElement).acceptAll();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    private KidIterator a(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "getKidIterator"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ASTNode node = psiElement.getNode();
        try {
            if (node != null) {
                final ASTNode firstChildNode = node.getFirstChildNode();
                return new KidIterator() {
                    private ASTNode child;
                    private boolean initialized;
                    final /* synthetic */ ASTNode val$firstChild;
                    
                    @Override
                    public void skipLeaves() {
                        if (!this.initialized) {
                            this.initialized = true;
                            this.child = this.val$firstChild;
                        }
                        while (this.child != null && (this.child.getFirstChildNode() == null || this.child.getElementType() == OCElementTypes.OBJC_KEYWORD || !OCElementUtil.isElementSignificant(this.child.getPsi()))) {
                            final PsiElement psi = this.child.getPsi();
                            if (psi != null && OCControlFlowBuilder.this.doEnlargeNodes()) {
                                OCNode lastAddedNode = OCControlFlowBuilder.this.myGraph.getLastAddedNode();
                                if (lastAddedNode.isEmpty() && OCControlFlowBuilder.closingTokens.contains(this.child.getElementType())) {
                                    for (OCNode ocNode = OCControlFlowBuilder.this.myGraph.getPreviousNonEmptyNode(lastAddedNode); ocNode != null && lastAddedNode.getEndOffset() < ocNode.getEndOffset() && ocNode.getEndOffset() <= psi.getTextOffset(); lastAddedNode = ocNode, ocNode = OCControlFlowBuilder.this.myGraph.getPreviousNonEmptyNode(lastAddedNode)) {}
                                }
                                lastAddedNode.enlarge(psi, psi.getParent());
                            }
                            this.child = this.child.getTreeNext();
                        }
                    }
                    
                    @Override
                    public void accept(@Nullable final PsiElement psiElement) {
                        if (psiElement == null) {
                            return;
                        }
                        this.skipLeaves();
                        if (this.child != null && psiElement == this.child.getPsi()) {
                            psiElement.accept((PsiElementVisitor)OCControlFlowBuilder.this);
                            this.child = this.child.getTreeNext();
                        }
                    }
                    
                    @Override
                    public void skip(@Nullable final PsiElement psiElement) {
                        if (psiElement == null) {
                            return;
                        }
                        this.skipLeaves();
                        if (this.child != null && psiElement == this.child.getPsi()) {
                            this.child = this.child.getTreeNext();
                        }
                    }
                    
                    @Override
                    public void acceptAll() {
                        this.skipLeaves();
                        while (this.child != null) {
                            ProgressManager.checkCanceled();
                            final PsiElement psi = this.child.getPsi();
                            if (psi != null) {
                                psi.accept((PsiElementVisitor)OCControlFlowBuilder.this);
                            }
                            this.child = this.child.getTreeNext();
                            this.skipLeaves();
                        }
                    }
                    
                    @Override
                    public void finish() {
                        this.skipLeaves();
                    }
                };
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final ASTNode firstChildNode = null;
        return new KidIterator() {
            private ASTNode child;
            private boolean initialized;
            final /* synthetic */ ASTNode val$firstChild;
            
            @Override
            public void skipLeaves() {
                if (!this.initialized) {
                    this.initialized = true;
                    this.child = firstChildNode;
                }
                while (this.child != null && (this.child.getFirstChildNode() == null || this.child.getElementType() == OCElementTypes.OBJC_KEYWORD || !OCElementUtil.isElementSignificant(this.child.getPsi()))) {
                    final PsiElement psi = this.child.getPsi();
                    if (psi != null && OCControlFlowBuilder.this.doEnlargeNodes()) {
                        OCNode lastAddedNode = OCControlFlowBuilder.this.myGraph.getLastAddedNode();
                        if (lastAddedNode.isEmpty() && OCControlFlowBuilder.closingTokens.contains(this.child.getElementType())) {
                            for (OCNode ocNode = OCControlFlowBuilder.this.myGraph.getPreviousNonEmptyNode(lastAddedNode); ocNode != null && lastAddedNode.getEndOffset() < ocNode.getEndOffset() && ocNode.getEndOffset() <= psi.getTextOffset(); lastAddedNode = ocNode, ocNode = OCControlFlowBuilder.this.myGraph.getPreviousNonEmptyNode(lastAddedNode)) {}
                        }
                        lastAddedNode.enlarge(psi, psi.getParent());
                    }
                    this.child = this.child.getTreeNext();
                }
            }
            
            @Override
            public void accept(@Nullable final PsiElement psiElement) {
                if (psiElement == null) {
                    return;
                }
                this.skipLeaves();
                if (this.child != null && psiElement == this.child.getPsi()) {
                    psiElement.accept((PsiElementVisitor)OCControlFlowBuilder.this);
                    this.child = this.child.getTreeNext();
                }
            }
            
            @Override
            public void skip(@Nullable final PsiElement psiElement) {
                if (psiElement == null) {
                    return;
                }
                this.skipLeaves();
                if (this.child != null && psiElement == this.child.getPsi()) {
                    this.child = this.child.getTreeNext();
                }
            }
            
            @Override
            public void acceptAll() {
                this.skipLeaves();
                while (this.child != null) {
                    ProgressManager.checkCanceled();
                    final PsiElement psi = this.child.getPsi();
                    if (psi != null) {
                        psi.accept((PsiElementVisitor)OCControlFlowBuilder.this);
                    }
                    this.child = this.child.getTreeNext();
                    this.skipLeaves();
                }
            }
            
            @Override
            public void finish() {
                this.skipLeaves();
            }
        };
    }
    
    protected boolean doEnlargeNodes() {
        return true;
    }
    
    public void processFirstCodeFragment(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "codeFragment", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "processFirstCodeFragment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myTryThrows.push(new ArrayList<OCNode>());
        this.myValuesStack.push(new ArrayList<PsiElement>());
        this.addStartNode(this.myGraph.addNode());
        this.processNextCodeFragment(psiElement);
    }
    
    public void processNextCodeFragment(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "codeFragment", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "processNextCodeFragment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement instanceof OCCallable) {
                this.visitElement(psiElement);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        psiElement.accept((PsiElementVisitor)this);
    }
    
    protected void addStartNode(@NotNull final OCNode startNode) {
        try {
            if (startNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addStartNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myGraph.setStartNode(startNode);
    }
    
    @Nullable
    protected NodeState addBranch(@NotNull final OCNode ocNode, @NotNull final OCNode ocNode2, @Nullable final List<PsiElement> list, @Nullable final NodeState nodeState) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fromNode", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addBranch"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocNode2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toNode", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addBranch"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (this.myGraph.isSplitNodesAllowed()) {
                ocNode.addBranch(ocNode2);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    @Nullable
    protected NodeState addBranch(@NotNull final OCNode ocNode, @NotNull final OCNode ocNode2, @Nullable final List<PsiElement> list) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fromNode", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addBranch"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocNode2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toNode", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addBranch"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.addBranch(ocNode, ocNode2, list, null);
    }
    
    @Nullable
    protected NodeState addUnstructuralBranch(@NotNull final OCNode ocNode, @NotNull final OCNode ocNode2) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fromNode", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addUnstructuralBranch"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocNode2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toNode", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addUnstructuralBranch"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.addBranch(ocNode, ocNode2, null);
    }
    
    @Nullable
    protected NodeState addBranch(@NotNull final OCNode ocNode, @NotNull final OCNode ocNode2) {
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fromNode", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addBranch"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocNode2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toNode", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addBranch"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.addBranch(ocNode, ocNode2, null);
    }
    
    protected OCNode acceptCondition(@Nullable final OCElement ocElement, @NotNull final KidIterator kidIterator, final boolean b) {
        try {
            if (kidIterator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "itr", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "acceptCondition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        kidIterator.accept((PsiElement)ocElement);
        kidIterator.skipLeaves();
        return this.myGraph.getLastAddedNode();
    }
    
    @Nullable
    protected NodeState addConditionalBranch(@NotNull final OCElement ocElement, final boolean b, @NotNull final OCNode ocNode, @NotNull final OCNode ocNode2, @Nullable final List<PsiElement> list, @Nullable final NodeState nodeState) {
        try {
            if (ocElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "condition", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addConditionalBranch"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fromNode", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addConditionalBranch"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocNode2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toNode", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addConditionalBranch"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (this.myGraph.isSplitNodesAllowed()) {
                ocNode.addBranch(ocNode2);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return null;
    }
    
    @Nullable
    protected NodeState addConditionalBranch(@NotNull final OCElement ocElement, final boolean b, @NotNull final OCNode ocNode, @NotNull final OCNode ocNode2) {
        try {
            if (ocElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "condition", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addConditionalBranch"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fromNode", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addConditionalBranch"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocNode2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toNode", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addConditionalBranch"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return this.addConditionalBranch(ocElement, b, ocNode, ocNode2, null, null);
    }
    
    @Override
    public void visitIfStatement(final OCIfStatement ocIfStatement) {
        this.a((PsiElement)ocIfStatement, ocIfStatement.getCondition(), (PsiElement)ocIfStatement.getThenBranch(), (PsiElement)ocIfStatement.getElseBranch());
    }
    
    @Override
    public void visitConditionalExpression(final OCConditionalExpression ocConditionalExpression) {
        this.a((PsiElement)ocConditionalExpression, ocConditionalExpression.getCondition(), (PsiElement)ocConditionalExpression.getPositiveExpression(true), (PsiElement)ocConditionalExpression.getNegativeExpression());
    }
    
    private void a(@NotNull final PsiElement psiElement, @Nullable final OCElement ocElement, @Nullable final PsiElement psiElement2, @Nullable final PsiElement psiElement3) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "visitIfLike"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myValuesStack.push(new ArrayList<PsiElement>());
        final KidIterator a = this.a(psiElement);
        final OCNode acceptCondition = this.acceptCondition(ocElement, a, false);
        final Number a2 = a(ocElement);
        boolean b = false;
        Label_0111: {
            Label_0102: {
                try {
                    if (a2 == null) {
                        break Label_0102;
                    }
                    final Number n = a2;
                    final int n2 = OCExpressionEvaluator.singAsInC(n);
                    if (n2 != 0) {
                        break Label_0102;
                    }
                    break Label_0102;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final Number n = a2;
                    final int n2 = OCExpressionEvaluator.singAsInC(n);
                    if (n2 != 0) {
                        b = true;
                        break Label_0111;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            b = false;
        }
        final boolean b2 = b;
        boolean b3 = false;
        Label_0142: {
            Label_0133: {
                try {
                    if (a2 == null) {
                        break Label_0133;
                    }
                    final Number n3 = a2;
                    final int n4 = OCExpressionEvaluator.singAsInC(n3);
                    if (n4 == 0) {
                        break Label_0133;
                    }
                    break Label_0133;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    final Number n3 = a2;
                    final int n4 = OCExpressionEvaluator.singAsInC(n3);
                    if (n4 == 0) {
                        b3 = true;
                        break Label_0142;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            b3 = false;
        }
        final boolean b4 = b3;
        final OCNode addNode = this.myGraph.addNode();
        Label_0169: {
            try {
                if (!b2) {
                    break Label_0169;
                }
                final OCElement ocElement2 = ocElement;
                if (ocElement2 != null) {
                    break Label_0169;
                }
                break Label_0169;
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            try {
                final OCElement ocElement2 = ocElement;
                if (ocElement2 != null) {
                    this.addConditionalBranch(ocElement, true, acceptCondition, addNode);
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        a.accept(psiElement2);
        final OCNode lastAddedNode = this.myGraph.getLastAddedNode();
        final OCNode addNode2 = this.myGraph.addNode();
        Label_0229: {
            try {
                if (!b4) {
                    break Label_0229;
                }
                final OCElement ocElement3 = ocElement;
                if (ocElement3 != null) {
                    break Label_0229;
                }
                break Label_0229;
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
            try {
                final OCElement ocElement3 = ocElement;
                if (ocElement3 != null) {
                    this.addConditionalBranch(ocElement, false, acceptCondition, addNode2);
                }
            }
            catch (IllegalArgumentException ex9) {
                throw a(ex9);
            }
        }
        a.accept(psiElement3);
        final OCNode lastAddedNode2 = this.myGraph.getLastAddedNode();
        final List<PsiElement> list = this.myValuesStack.pop();
        if (acceptCondition != this.myGraph.getLastAddedNode()) {
            final OCNode addNode3 = this.myGraph.addNode();
            this.addBranch(lastAddedNode, addNode3, list);
            this.addBranch(lastAddedNode2, addNode3, list);
        }
        a.finish();
    }
    
    @Nullable
    private static Number a(@Nullable final OCElement ocElement) {
        OCExpression initializer = null;
        if (ocElement instanceof OCExpression) {
            initializer = (OCExpression)ocElement;
        }
        else if (ocElement instanceof OCDeclarationOrExpression) {
            final OCDeclarationOrExpression ocDeclarationOrExpression = (OCDeclarationOrExpression)ocElement;
            final OCExpression expression = ocDeclarationOrExpression.getExpression();
            if (expression != null) {
                initializer = expression;
            }
            else {
                final OCDeclaration declaration = ocDeclarationOrExpression.getDeclaration();
                try {
                    if (declaration == null || declaration.getDeclarators().size() == 0) {
                        return OCExpressionEvaluator.evaluate(initializer);
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                initializer = declaration.getDeclarators().get(0).getInitializer();
            }
        }
        return OCExpressionEvaluator.evaluate(initializer);
    }
    
    @NotNull
    private int[] a() {
        int[] array;
        try {
            array = new int[] { this.myBreakNodes.size(), this.myContinueNodes.size() };
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "saveLoopState"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return array;
    }
    
    private boolean a(@NotNull final OCNode p0) {
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
        //    18: ldc             "node"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isSelected"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokevirtual   com/jetbrains/cidr/lang/dfa/OCNode.getRange:()Lcom/jetbrains/cidr/lang/util/OCElementsRange;
        //    48: astore_2       
        //    49: aload_0        
        //    50: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.mySelection:Lcom/intellij/openapi/util/TextRange;
        //    53: ifnull          96
        //    56: aload_2        
        //    57: ifnull          96
        //    60: goto            67
        //    63: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: aload_0        
        //    68: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.mySelection:Lcom/intellij/openapi/util/TextRange;
        //    71: aload_2        
        //    72: invokevirtual   com/jetbrains/cidr/lang/util/OCElementsRange.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    75: invokevirtual   com/intellij/openapi/util/TextRange.contains:(Lcom/intellij/openapi/util/TextRange;)Z
        //    78: ifeq            96
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: iconst_1       
        //    89: goto            97
        //    92: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: iconst_0       
        //    97: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  49     60     63     67     Ljava/lang/IllegalArgumentException;
        //  56     81     84     88     Ljava/lang/IllegalArgumentException;
        //  67     92     92     96     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0067:
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
    
    public boolean hasCrossSelectionJumps() {
        return this.myHasCrossSelectionJumps;
    }
    
    public boolean hasDanglingJumps() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.myBreakNodes:Ljava/util/List;
        //     4: invokeinterface java/util/List.isEmpty:()Z
        //     9: ifeq            45
        //    12: aload_0        
        //    13: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.myContinueNodes:Ljava/util/List;
        //    16: invokeinterface java/util/List.isEmpty:()Z
        //    21: ifeq            45
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    30: athrow         
        //    31: aload_0        
        //    32: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.myHasTopLevelCaseStatements:Z
        //    35: ifeq            53
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: iconst_1       
        //    46: goto            54
        //    49: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    52: athrow         
        //    53: iconst_0       
        //    54: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      24     27     31     Ljava/lang/IllegalArgumentException;
        //  12     38     41     45     Ljava/lang/IllegalArgumentException;
        //  31     49     49     53     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0031:
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
    
    private void a(@NotNull final int[] array, @Nullable final OCNode ocNode, @Nullable final OCNode ocNode2, @Nullable final List<PsiElement> list) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "savedState", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "patchLoopJumps"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (ocNode2 != null) {
            int i = this.myBreakNodes.size() - 1;
            try {
                while (i >= array[0]) {
                    this.addUnstructuralBranch(this.myBreakNodes.get(i), ocNode2);
                    this.myBreakNodes.remove(i);
                    --i;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        if (ocNode != null) {
            int j = this.myContinueNodes.size() - 1;
            try {
                while (j >= array[1]) {
                    this.addUnstructuralBranch(this.myContinueNodes.get(j), ocNode);
                    this.myContinueNodes.remove(j);
                    --j;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
    }
    
    @Override
    public void visitWhileStatement(final OCWhileStatement ocWhileStatement) {
        this.a(ocWhileStatement, null, ocWhileStatement.getCondition(), null, ocWhileStatement.getBody());
    }
    
    @Override
    public void visitForStatement(final OCForStatement ocForStatement) {
        this.a(ocForStatement, ocForStatement.getInitializer(), ocForStatement.getCondition(), ocForStatement.getIncrement(), ocForStatement.getBody());
    }
    
    @Override
    public void visitForeachStatement(final OCForeachStatement ocForeachStatement) {
        OCElement ocElement = ocForeachStatement.getVariableDeclaration();
        if (ocElement == null) {
            ocElement = ocForeachStatement.getVariableExpression();
        }
        this.a(ocForeachStatement, ocElement, ocForeachStatement.getCollectionExpression(), null, ocForeachStatement.getBody());
    }
    
    private void a(@NotNull final OCElement p0, @Nullable final OCElement p1, @Nullable final OCElement p2, @Nullable final OCStatement p3, @Nullable final OCStatement p4) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "visitForWhile"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_1        
        //    46: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$KidIterator;
        //    49: astore          6
        //    51: aload           6
        //    53: aload_2        
        //    54: invokeinterface com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$KidIterator.accept:(Lcom/intellij/psi/PsiElement;)V
        //    59: aload_0        
        //    60: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //    63: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getLastAddedNode:()Lcom/jetbrains/cidr/lang/dfa/OCNode;
        //    66: astore          7
        //    68: aload_0        
        //    69: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //    72: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.addNode:()Lcom/jetbrains/cidr/lang/dfa/OCNode;
        //    75: astore          8
        //    77: aload           8
        //    79: astore          9
        //    81: aload_0        
        //    82: aload           7
        //    84: aload           8
        //    86: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.addBranch:(Lcom/jetbrains/cidr/lang/dfa/OCNode;Lcom/jetbrains/cidr/lang/dfa/OCNode;)Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$NodeState;
        //    89: astore          10
        //    91: aload_0        
        //    92: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.myValuesStack:Ljava/util/Stack;
        //    95: new             Ljava/util/ArrayList;
        //    98: dup            
        //    99: invokespecial   java/util/ArrayList.<init>:()V
        //   102: invokevirtual   java/util/Stack.push:(Ljava/lang/Object;)Ljava/lang/Object;
        //   105: pop            
        //   106: aload_0        
        //   107: aload_3        
        //   108: aload           6
        //   110: iconst_1       
        //   111: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.acceptCondition:(Lcom/jetbrains/cidr/lang/psi/OCElement;Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$KidIterator;Z)Lcom/jetbrains/cidr/lang/dfa/OCNode;
        //   114: astore          11
        //   116: aload_1        
        //   117: instanceof      Lcom/jetbrains/cidr/lang/psi/OCForeachStatement;
        //   120: istore          12
        //   122: iload           12
        //   124: ifeq            135
        //   127: aconst_null    
        //   128: goto            139
        //   131: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   134: athrow         
        //   135: aload_3        
        //   136: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Ljava/lang/Number;
        //   139: astore          13
        //   141: aload           13
        //   143: ifnull          161
        //   146: aload           13
        //   148: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //   151: ifeq            169
        //   154: goto            161
        //   157: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   160: athrow         
        //   161: iconst_1       
        //   162: goto            170
        //   165: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: iconst_0       
        //   170: istore          14
        //   172: aload           13
        //   174: ifnull          192
        //   177: aload           13
        //   179: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //   182: ifne            200
        //   185: goto            192
        //   188: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   191: athrow         
        //   192: iconst_1       
        //   193: goto            201
        //   196: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: iconst_0       
        //   201: istore          15
        //   203: aload_0        
        //   204: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //   207: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.addNode:()Lcom/jetbrains/cidr/lang/dfa/OCNode;
        //   210: astore          16
        //   212: iload           14
        //   214: ifeq            267
        //   217: iload           12
        //   219: ifne            240
        //   222: goto            229
        //   225: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   228: athrow         
        //   229: aload_3        
        //   230: ifnonnull       256
        //   233: goto            240
        //   236: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   239: athrow         
        //   240: aload_0        
        //   241: aload           8
        //   243: aload           16
        //   245: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.addBranch:(Lcom/jetbrains/cidr/lang/dfa/OCNode;Lcom/jetbrains/cidr/lang/dfa/OCNode;)Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$NodeState;
        //   248: pop            
        //   249: goto            267
        //   252: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   255: athrow         
        //   256: aload_0        
        //   257: aload_3        
        //   258: iconst_1       
        //   259: aload           11
        //   261: aload           16
        //   263: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.addConditionalBranch:(Lcom/jetbrains/cidr/lang/psi/OCElement;ZLcom/jetbrains/cidr/lang/dfa/OCNode;Lcom/jetbrains/cidr/lang/dfa/OCNode;)Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$NodeState;
        //   266: pop            
        //   267: aload_0        
        //   268: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:()[I
        //   271: astore          17
        //   273: aload           6
        //   275: aload           4
        //   277: invokeinterface com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$KidIterator.skip:(Lcom/intellij/psi/PsiElement;)V
        //   282: aload           6
        //   284: aload           5
        //   286: invokeinterface com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$KidIterator.accept:(Lcom/intellij/psi/PsiElement;)V
        //   291: aload_0        
        //   292: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.getLoopBodyEndNode:()Lcom/jetbrains/cidr/lang/dfa/OCNode;
        //   295: astore          18
        //   297: aload           4
        //   299: ifnull          341
        //   302: aload_0        
        //   303: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //   306: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.addNode:()Lcom/jetbrains/cidr/lang/dfa/OCNode;
        //   309: astore          19
        //   311: aload_0        
        //   312: aload           18
        //   314: aload           19
        //   316: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.addBranch:(Lcom/jetbrains/cidr/lang/dfa/OCNode;Lcom/jetbrains/cidr/lang/dfa/OCNode;)Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$NodeState;
        //   319: pop            
        //   320: aload           4
        //   322: aload_0        
        //   323: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //   328: aload_0        
        //   329: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //   332: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getLastAddedNode:()Lcom/jetbrains/cidr/lang/dfa/OCNode;
        //   335: astore          18
        //   337: aload           19
        //   339: astore          9
        //   341: aload_0        
        //   342: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.myValuesStack:Ljava/util/Stack;
        //   345: invokevirtual   java/util/Stack.pop:()Ljava/lang/Object;
        //   348: checkcast       Ljava/util/List;
        //   351: astore          19
        //   353: aload_0        
        //   354: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //   357: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.addNode:()Lcom/jetbrains/cidr/lang/dfa/OCNode;
        //   360: astore          20
        //   362: aload_0        
        //   363: aload           17
        //   365: aload           9
        //   367: aload           20
        //   369: aload           19
        //   371: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:([ILcom/jetbrains/cidr/lang/dfa/OCNode;Lcom/jetbrains/cidr/lang/dfa/OCNode;Ljava/util/List;)V
        //   374: aload_0        
        //   375: aload           18
        //   377: aload           8
        //   379: aload           19
        //   381: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.addBranch:(Lcom/jetbrains/cidr/lang/dfa/OCNode;Lcom/jetbrains/cidr/lang/dfa/OCNode;Ljava/util/List;)Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$NodeState;
        //   384: astore          21
        //   386: iload           14
        //   388: ifeq            452
        //   391: aload           10
        //   393: aload           21
        //   395: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   398: ifne            440
        //   401: goto            408
        //   404: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   407: athrow         
        //   408: aload_3        
        //   409: ifnull          440
        //   412: goto            419
        //   415: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   418: athrow         
        //   419: aload_0        
        //   420: aload_3        
        //   421: iconst_1       
        //   422: aload           11
        //   424: aload           16
        //   426: aconst_null    
        //   427: aload           10
        //   429: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.addConditionalBranch:(Lcom/jetbrains/cidr/lang/psi/OCElement;ZLcom/jetbrains/cidr/lang/dfa/OCNode;Lcom/jetbrains/cidr/lang/dfa/OCNode;Ljava/util/List;Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$NodeState;)Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$NodeState;
        //   432: pop            
        //   433: goto            452
        //   436: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   439: athrow         
        //   440: aload_0        
        //   441: aload           11
        //   443: aload           16
        //   445: aconst_null    
        //   446: aload           10
        //   448: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.addBranch:(Lcom/jetbrains/cidr/lang/dfa/OCNode;Lcom/jetbrains/cidr/lang/dfa/OCNode;Ljava/util/List;Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$NodeState;)Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$NodeState;
        //   451: pop            
        //   452: iload           15
        //   454: ifeq            507
        //   457: iload           12
        //   459: ifeq            485
        //   462: goto            469
        //   465: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   468: athrow         
        //   469: aload_0        
        //   470: aload           8
        //   472: aload           20
        //   474: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.addBranch:(Lcom/jetbrains/cidr/lang/dfa/OCNode;Lcom/jetbrains/cidr/lang/dfa/OCNode;)Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$NodeState;
        //   477: pop            
        //   478: goto            507
        //   481: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   484: athrow         
        //   485: aload_3        
        //   486: ifnull          507
        //   489: aload_0        
        //   490: aload_3        
        //   491: iconst_0       
        //   492: aload           11
        //   494: aload           20
        //   496: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.addConditionalBranch:(Lcom/jetbrains/cidr/lang/psi/OCElement;ZLcom/jetbrains/cidr/lang/dfa/OCNode;Lcom/jetbrains/cidr/lang/dfa/OCNode;)Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$NodeState;
        //   499: pop            
        //   500: goto            507
        //   503: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   506: athrow         
        //   507: aload           6
        //   509: invokeinterface com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$KidIterator.finish:()V
        //   514: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  122    131    131    135    Ljava/lang/IllegalArgumentException;
        //  141    154    157    161    Ljava/lang/IllegalArgumentException;
        //  146    165    165    169    Ljava/lang/IllegalArgumentException;
        //  172    185    188    192    Ljava/lang/IllegalArgumentException;
        //  177    196    196    200    Ljava/lang/IllegalArgumentException;
        //  212    222    225    229    Ljava/lang/IllegalArgumentException;
        //  217    233    236    240    Ljava/lang/IllegalArgumentException;
        //  229    252    252    256    Ljava/lang/IllegalArgumentException;
        //  386    401    404    408    Ljava/lang/IllegalArgumentException;
        //  391    412    415    419    Ljava/lang/IllegalArgumentException;
        //  408    436    436    440    Ljava/lang/IllegalArgumentException;
        //  452    462    465    469    Ljava/lang/IllegalArgumentException;
        //  457    481    481    485    Ljava/lang/IllegalArgumentException;
        //  485    500    503    507    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0229:
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
    
    protected OCNode getLoopBodyEndNode() {
        return this.myGraph.getLastAddedNode();
    }
    
    @Override
    public void visitDoWhileStatement(final OCDoWhileStatement ocDoWhileStatement) {
        final KidIterator a = this.a((PsiElement)ocDoWhileStatement);
        final OCNode lastAddedNode = this.myGraph.getLastAddedNode();
        final OCNode addNode = this.myGraph.addNode();
        this.addBranch(lastAddedNode, addNode);
        final int[] a2 = this.a();
        this.myValuesStack.push(new ArrayList<PsiElement>());
        a.accept((PsiElement)ocDoWhileStatement.getBody());
        final OCExpression condition = ocDoWhileStatement.getCondition();
        final OCNode acceptCondition = this.acceptCondition(condition, a, false);
        final Number evaluate = OCExpressionEvaluator.evaluate(condition);
        boolean b = false;
        Label_0118: {
            Label_0109: {
                try {
                    if (evaluate == null) {
                        break Label_0109;
                    }
                    final Number n = evaluate;
                    final int n2 = OCExpressionEvaluator.singAsInC(n);
                    if (n2 != 0) {
                        break Label_0109;
                    }
                    break Label_0109;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final Number n = evaluate;
                    final int n2 = OCExpressionEvaluator.singAsInC(n);
                    if (n2 != 0) {
                        b = true;
                        break Label_0118;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            b = false;
        }
        final boolean b2 = b;
        boolean b3 = false;
        Label_0149: {
            Label_0140: {
                try {
                    if (evaluate == null) {
                        break Label_0140;
                    }
                    final Number n3 = evaluate;
                    final int n4 = OCExpressionEvaluator.singAsInC(n3);
                    if (n4 == 0) {
                        break Label_0140;
                    }
                    break Label_0140;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final Number n3 = evaluate;
                    final int n4 = OCExpressionEvaluator.singAsInC(n3);
                    if (n4 == 0) {
                        b3 = true;
                        break Label_0149;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            b3 = false;
        }
        final boolean b4 = b3;
        try {
            if (addNode.isEmpty()) {
                this.myGraph.removeNode(addNode, false);
                return;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        final List<PsiElement> list = this.myValuesStack.pop();
        final OCNode lastAddedNode2 = this.myGraph.getLastAddedNode();
        final OCNode addNode2 = this.myGraph.addNode();
        Label_0244: {
            Label_0221: {
                try {
                    if (condition == null) {
                        break Label_0244;
                    }
                    final boolean b5 = b2;
                    if (b5) {
                        break Label_0221;
                    }
                    break Label_0244;
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                try {
                    final boolean b5 = b2;
                    if (b5) {
                        this.addConditionalBranch(condition, true, lastAddedNode2, addNode, Collections.emptyList(), null);
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            try {
                if (b4) {
                    this.addConditionalBranch(condition, false, lastAddedNode2, addNode2);
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        this.a(a2, acceptCondition, addNode2, list);
        a.finish();
    }
    
    @Override
    public void visitBreakStatement(final OCBreakStatement ocBreakStatement) {
        this.visitElement((PsiElement)ocBreakStatement);
        this.myBreakNodes.add(this.myGraph.getLastAddedNode());
        this.myGraph.addNode();
    }
    
    @Override
    public void visitContinueStatement(final OCContinueStatement ocContinueStatement) {
        this.visitElement((PsiElement)ocContinueStatement);
        this.myContinueNodes.add(this.myGraph.getLastAddedNode());
        this.myGraph.addNode();
    }
    
    @Override
    public void visitLabeledStatement(final OCLabeledStatement ocLabeledStatement) {
        final OCNode lastAddedNode = this.myGraph.getLastAddedNode();
        OCNode addNode;
        if (!lastAddedNode.isEmpty()) {
            addNode = this.myGraph.addNode();
            this.addBranch(lastAddedNode, addNode);
        }
        else {
            addNode = lastAddedNode;
        }
        this.visitElement((PsiElement)ocLabeledStatement);
        this.myLabeledNodes.put(ocLabeledStatement.getLabel(), addNode);
        for (final OCNode ocNode : this.myGotoNodes.get((Object)ocLabeledStatement.getLabel())) {
            try {
                this.addUnstructuralBranch(ocNode, addNode);
                if (!(this.a(ocNode) ^ this.a(addNode))) {
                    continue;
                }
                this.myHasCrossSelectionJumps = true;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        final OCLabelSymbol localSymbol = ocLabeledStatement.getLocalSymbol();
        try {
            if (localSymbol != null) {
                this.myGraph.addInstruction(OCInstruction.InstructionKind.DECLARATOR, ocLabeledStatement.getLabelElement(), localSymbol);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    @Override
    public void visitGotoStatement(final OCGotoStatement ocGotoStatement) {
        this.visitElement((PsiElement)ocGotoStatement);
        final OCNode lastAddedNode = this.myGraph.getLastAddedNode();
        this.myGraph.addNode();
        final OCReferenceElement label = ocGotoStatement.getLabel();
        try {
            if (label == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myGotoNodes.add((Object)label.getCanonicalText(), (Object)lastAddedNode);
        final OCNode ocNode = this.myLabeledNodes.get(label.getCanonicalText());
        Label_0129: {
            try {
                this.myGraph.addInstruction(OCInstruction.InstructionKind.READ, ocGotoStatement.getNavigationElement(), label.resolveToSymbol());
                if (ocNode == null) {
                    return;
                }
                final OCControlFlowBuilder ocControlFlowBuilder = this;
                final OCNode ocNode2 = lastAddedNode;
                final OCNode ocNode3 = ocNode;
                ocControlFlowBuilder.addUnstructuralBranch(ocNode2, ocNode3);
                final OCControlFlowBuilder ocControlFlowBuilder2 = this;
                final OCNode ocNode4 = lastAddedNode;
                final boolean b = ocControlFlowBuilder2.a(ocNode4);
                final OCControlFlowBuilder ocControlFlowBuilder3 = this;
                final OCNode ocNode5 = ocNode;
                final boolean b2 = ocControlFlowBuilder3.a(ocNode5);
                final boolean b3 = b ^ b2;
                if (b3) {
                    break Label_0129;
                }
                return;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCControlFlowBuilder ocControlFlowBuilder = this;
                final OCNode ocNode2 = lastAddedNode;
                final OCNode ocNode3 = ocNode;
                ocControlFlowBuilder.addUnstructuralBranch(ocNode2, ocNode3);
                final OCControlFlowBuilder ocControlFlowBuilder2 = this;
                final OCNode ocNode4 = lastAddedNode;
                final boolean b = ocControlFlowBuilder2.a(ocNode4);
                final OCControlFlowBuilder ocControlFlowBuilder3 = this;
                final OCNode ocNode5 = ocNode;
                final boolean b2 = ocControlFlowBuilder3.a(ocNode5);
                final boolean b3 = b ^ b2;
                if (b3) {
                    this.myHasCrossSelectionJumps = true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
    }
    
    protected void addCaseStatement(@NotNull final OCCaseStatement ocCaseStatement, @NotNull final OCNode ocNode, @NotNull final SwitchInfo switchInfo) {
        try {
            if (ocCaseStatement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stmt", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addCaseStatement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caseNode", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addCaseStatement"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (switchInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "addCaseStatement"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        this.addBranch(switchInfo.getNode(), ocNode);
        switchInfo.addCaseExpression(ocCaseStatement.getExpression());
    }
    
    @Override
    public void visitCaseStatement(final OCCaseStatement ocCaseStatement) {
        final OCNode lastAddedNode = this.myGraph.getLastAddedNode();
        OCNode addNode;
        if (!lastAddedNode.isEmpty()) {
            addNode = this.myGraph.addNode();
            this.addBranch(lastAddedNode, addNode);
        }
        else {
            addNode = lastAddedNode;
        }
        SwitchInfo switchInfo = null;
        Label_0063: {
            try {
                if (this.mySwitchStack.isEmpty()) {
                    switchInfo = null;
                    break Label_0063;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            switchInfo = this.mySwitchStack.peek();
        }
        final SwitchInfo switchInfo2 = switchInfo;
        Label_0112: {
            Label_0094: {
                try {
                    if (switchInfo2 == null) {
                        break Label_0094;
                    }
                    final OCControlFlowBuilder ocControlFlowBuilder = this;
                    final OCCaseStatement ocCaseStatement2 = ocCaseStatement;
                    final OCNode ocNode = addNode;
                    final SwitchInfo switchInfo3 = switchInfo2;
                    ocControlFlowBuilder.addCaseStatement(ocCaseStatement2, ocNode, switchInfo3);
                    final OCCaseStatement ocCaseStatement3 = ocCaseStatement;
                    final boolean b = ocCaseStatement3.isDefault();
                    if (b) {
                        break Label_0094;
                    }
                    break Label_0112;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCControlFlowBuilder ocControlFlowBuilder = this;
                    final OCCaseStatement ocCaseStatement2 = ocCaseStatement;
                    final OCNode ocNode = addNode;
                    final SwitchInfo switchInfo3 = switchInfo2;
                    ocControlFlowBuilder.addCaseStatement(ocCaseStatement2, ocNode, switchInfo3);
                    final OCCaseStatement ocCaseStatement3 = ocCaseStatement;
                    final boolean b = ocCaseStatement3.isDefault();
                    if (b) {
                        switchInfo2.setHasDefault(true);
                    }
                    break Label_0112;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            this.myHasTopLevelCaseStatements = true;
        }
        this.visitElement((PsiElement)ocCaseStatement);
    }
    
    @Override
    public void visitSwitchStatement(final OCSwitchStatement ocSwitchStatement) {
        final KidIterator a = this.a((PsiElement)ocSwitchStatement);
        final OCDeclarationOrExpression expression = ocSwitchStatement.getExpression();
        a.accept((PsiElement)expression);
        a.skipLeaves();
        this.myValuesStack.push(new ArrayList<PsiElement>());
        final OCNode lastAddedNode = this.myGraph.getLastAddedNode();
        this.mySwitchStack.push(new SwitchInfo(expression, lastAddedNode));
        final int[] a2 = this.a();
        this.myGraph.addNode();
        final OCStatement body = ocSwitchStatement.getBody();
        a.accept((PsiElement)body);
        final OCNode lastAddedNode2 = this.myGraph.getLastAddedNode();
        final OCNode addNode = this.myGraph.addNode();
        final List<PsiElement> list = this.myValuesStack.pop();
        this.addBranch(lastAddedNode2, addNode, list);
        boolean hasDefault = this.mySwitchStack.pop().hasDefault();
        OCType terminalType = null;
        Label_0175: {
            try {
                if (expression != null) {
                    terminalType = expression.getResolvedType().getTerminalType();
                    break Label_0175;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            terminalType = null;
        }
        final OCType ocType = terminalType;
        Label_0259: {
            try {
                if (!(ocType instanceof OCStructType) || !((OCStructType)ocType).isEnumClass()) {
                    break Label_0259;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final ArrayList<Pair<Integer, Integer>> list2 = new ArrayList<Pair<Integer, Integer>>();
            final ArrayList<OCCaseStatement> list3 = new ArrayList<OCCaseStatement>();
            if (body != null) {
                OCCreateMissingSwitchCasesIntentionAction.findCaseStatements(body, list2, list3);
                hasDefault |= OCCreateMissingSwitchCasesIntentionAction.getMissingCases(ocSwitchStatement, list2, (Ref<Boolean>)new Ref()).isEmpty();
            }
            try {
                if (!hasDefault) {
                    this.addBranch(lastAddedNode, addNode, list);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        this.a(a2, null, addNode, list);
        a.finish();
    }
    
    @Override
    public void visitReturnStatement(final OCReturnStatement ocReturnStatement) {
        this.visitElement((PsiElement)ocReturnStatement);
        final OCNode lastAddedNode = this.myGraph.getLastAddedNode();
        this.myGraph.addExitNode(lastAddedNode);
        this.myGraph.addNode();
        lastAddedNode.setNodeAfterReturn(this.myGraph.getLastAddedNode());
    }
    
    @Override
    public void visitThrowExpression(final OCThrowExpression ocThrowExpression) {
        this.visitElement((PsiElement)ocThrowExpression);
        this.b();
    }
    
    private void b() {
        final OCNode lastAddedNode = this.myGraph.getLastAddedNode();
        this.myGraph.addExitNode(lastAddedNode);
        this.myGraph.addNode();
        this.myTryThrows.peek().add(lastAddedNode);
    }
    
    @Override
    public void visitTryStatement(final OCTryStatement ocTryStatement) {
        this.myTryFirstCalls.push((Ref<OCNode>)Ref.create());
        final KidIterator a = this.a((PsiElement)ocTryStatement);
        final OCNode lastAddedNode = this.myGraph.getLastAddedNode();
        final OCNode addNode = this.myGraph.addNode();
        this.addBranch(lastAddedNode, addNode);
        this.myTryThrows.push(new ArrayList<OCNode>());
        a.accept((PsiElement)ocTryStatement.getBody());
        addNode.enlarge((PsiElement)ocTryStatement, (PsiElement)ocTryStatement);
        final OCNode lastAddedNode2 = this.myGraph.getLastAddedNode();
        final List<OCNode> list = this.myTryThrows.pop();
        final ArrayList<OCNode> list2 = new ArrayList<OCNode>();
        final OCNode ocNode = (OCNode)this.myTryFirstCalls.pop().get();
        for (final PsiElement psiElement : ocTryStatement.getCatchSections()) {
            final OCNode addNode2 = this.myGraph.addNode();
            try {
                this.addBranch(lastAddedNode2, addNode2);
                if (ocNode != null) {
                    this.addBranch(ocNode, addNode2);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final Iterator<OCNode> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                this.addBranch(iterator2.next(), addNode2);
            }
            this.visitElement(psiElement);
            list2.add(this.myGraph.getLastAddedNode());
        }
        final OCNode addNode3 = this.myGraph.addNode();
        this.addBranch(lastAddedNode2, addNode3);
        final Iterator<Object> iterator3 = list2.iterator();
        while (iterator3.hasNext()) {
            this.addBranch(iterator3.next(), addNode3);
        }
        try {
            if (ocTryStatement.getFinallySection() != null) {
                this.visitElement((PsiElement)ocTryStatement.getFinallySection());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    @Override
    public void visitBlockExpression(final OCBlockExpression ocBlockExpression) {
        this.a(ocBlockExpression);
    }
    
    @Override
    public void visitLambdaExpression(final OCLambdaExpression ocLambdaExpression) {
        this.a(ocLambdaExpression);
    }
    
    private void a(@NotNull final OCCallable ocCallable) {
        try {
            if (ocCallable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callable", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "visitBlockOrLambdaExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCAnnotatorSink ocAnnotatorSink = null;
        Label_0085: {
            try {
                this.myGraph.getLastAddedNode().enlarge((PsiElement)ocCallable, (PsiElement)ocCallable);
                if (this.myAnalyzer != null) {
                    ocAnnotatorSink = this.myAnalyzer.getSink();
                    break Label_0085;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            ocAnnotatorSink = OCNullAnnotatorSink.INSTANCE;
        }
        final OCDataFlowAnalyzer ocDataFlowAnalyzer = new OCDataFlowAnalyzer((PsiElement)ocCallable, ocAnnotatorSink, this.myAnalyzer);
        ocDataFlowAnalyzer.buildControlFlowGraph();
        final OCUnreachableCodeFinder unreachableCodeFinder = ocDataFlowAnalyzer.getUnreachableCodeFinder();
        final OCNode lastAddedNode = this.myGraph.getLastAddedNode();
        this.myGraph.addInstructions(lastAddedNode, OCInstruction.InstructionKind.READ_IN_BLOCK, unreachableCodeFinder.getReachableInstructions(OCInstruction.InstructionKind.READ));
        this.myGraph.addInstructions(lastAddedNode, OCInstruction.InstructionKind.READ_IN_BLOCK, unreachableCodeFinder.getReachableInstructions(OCInstruction.InstructionKind.READ_IN_BLOCK));
        this.myGraph.addInstructions(lastAddedNode, OCInstruction.InstructionKind.WRITE_IN_BLOCK, unreachableCodeFinder.getReachableInstructions(OCInstruction.InstructionKind.WRITE));
        this.myGraph.addInstructions(lastAddedNode, OCInstruction.InstructionKind.WRITE_IN_BLOCK, unreachableCodeFinder.getReachableInstructions(OCInstruction.InstructionKind.WRITE_IN_BLOCK));
        this.myGraph.addInstructions(lastAddedNode, OCInstruction.InstructionKind.REFERENCE, unreachableCodeFinder.getReachableInstructions(OCInstruction.InstructionKind.REFERENCE));
        this.a(unreachableCodeFinder);
        unreachableCodeFinder.clearInstructions();
    }
    
    private void a(final OCUnreachableCodeFinder ocUnreachableCodeFinder) {
        final Iterator<OCInstruction> iterator = ocUnreachableCodeFinder.getReachableInstructions(OCInstruction.InstructionKind.WRITE).iterator();
        while (iterator.hasNext()) {
            this.processReferenceFromBlock(iterator.next().getSymbol());
        }
        final Iterator<OCInstruction> iterator2 = ocUnreachableCodeFinder.getReachableInstructions(OCInstruction.InstructionKind.WRITE_IN_BLOCK).iterator();
        while (iterator2.hasNext()) {
            this.processReferenceFromBlock(iterator2.next().getSymbol());
        }
        final Iterator<OCInstruction> iterator3 = ocUnreachableCodeFinder.getReachableInstructions(OCInstruction.InstructionKind.REFERENCE).iterator();
        while (iterator3.hasNext()) {
            this.processReferenceFromBlock(iterator3.next().getSymbol());
        }
    }
    
    @Override
    public void visitDeclarator(final OCDeclarator p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getLocalSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //     6: astore_2       
        //     7: aload_2        
        //     8: ifnull          50
        //    11: aload_2        
        //    12: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    17: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isLocal:()Z
        //    20: ifeq            50
        //    23: goto            30
        //    26: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    29: athrow         
        //    30: aload_0        
        //    31: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //    34: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.DECLARATOR:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //    37: aload_1        
        //    38: aload_2        
        //    39: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.addInstruction:(Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/dfa/OCInstruction;
        //    42: pop            
        //    43: goto            50
        //    46: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: aload_0        
        //    51: aload_1        
        //    52: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.visitElement:(Lcom/intellij/psi/PsiElement;)V
        //    55: aload_2        
        //    56: ifnull          295
        //    59: aload_2        
        //    60: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    65: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isLocal:()Z
        //    68: ifeq            295
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    77: athrow         
        //    78: aload_1        
        //    79: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    84: ifnull          138
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: aload_2        
        //    95: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   100: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   103: if_acmpeq       138
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: aload_0        
        //   114: aload_2        
        //   115: aload_1        
        //   116: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   121: aload_1        
        //   122: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   127: iconst_0       
        //   128: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.processAssignment:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/psi/OCExpression;Z)V
        //   131: goto            295
        //   134: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: aload_1        
        //   139: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializers:()Ljava/util/List;
        //   144: invokeinterface java/util/List.isEmpty:()Z
        //   149: ifne            171
        //   152: aload_2        
        //   153: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   158: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   161: if_acmpne       192
        //   164: goto            171
        //   167: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   170: athrow         
        //   171: aload_1        
        //   172: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getArrayLengths:()Ljava/util/List;
        //   177: invokeinterface java/util/List.isEmpty:()Z
        //   182: ifne            212
        //   185: goto            192
        //   188: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   191: athrow         
        //   192: aload_0        
        //   193: aload_2        
        //   194: aload_1        
        //   195: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   200: aconst_null    
        //   201: iconst_1       
        //   202: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.processAssignment:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/psi/OCExpression;Z)V
        //   205: goto            295
        //   208: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   211: athrow         
        //   212: aload_1        
        //   213: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   218: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   223: astore_3       
        //   224: aload_3        
        //   225: instanceof      Lcom/jetbrains/cidr/lang/psi/OCForeachStatement;
        //   228: ifne            264
        //   231: aload_3        
        //   232: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
        //   235: ifeq            284
        //   238: goto            245
        //   241: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   244: athrow         
        //   245: aload_3        
        //   246: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   251: instanceof      Lcom/jetbrains/cidr/lang/psi/OCForeachStatement;
        //   254: ifeq            284
        //   257: goto            264
        //   260: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   263: athrow         
        //   264: aload_0        
        //   265: aload_2        
        //   266: aload_1        
        //   267: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   272: aconst_null    
        //   273: iconst_1       
        //   274: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.processAssignment:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/psi/OCExpression;Z)V
        //   277: goto            295
        //   280: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   283: athrow         
        //   284: aload_0        
        //   285: aload_2        
        //   286: aload_1        
        //   287: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   292: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.processNonInitializedDeclarator:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)V
        //   295: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      23     26     30     Ljava/lang/IllegalArgumentException;
        //  11     43     46     50     Ljava/lang/IllegalArgumentException;
        //  50     71     74     78     Ljava/lang/IllegalArgumentException;
        //  59     87     90     94     Ljava/lang/IllegalArgumentException;
        //  78     106    109    113    Ljava/lang/IllegalArgumentException;
        //  94     134    134    138    Ljava/lang/IllegalArgumentException;
        //  138    164    167    171    Ljava/lang/IllegalArgumentException;
        //  152    185    188    192    Ljava/lang/IllegalArgumentException;
        //  171    208    208    212    Ljava/lang/IllegalArgumentException;
        //  224    238    241    245    Ljava/lang/IllegalArgumentException;
        //  231    257    260    264    Ljava/lang/IllegalArgumentException;
        //  245    280    280    284    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0078:
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
    public void visitMethodSelectorPart(final OCMethodSelectorPart ocMethodSelectorPart) {
        this.visitElement((PsiElement)ocMethodSelectorPart);
        final OCDeclaratorSymbol localSymbol = ocMethodSelectorPart.getLocalSymbol();
        Label_0060: {
            Label_0035: {
                try {
                    if (localSymbol == null) {
                        break Label_0060;
                    }
                    final OCDeclaratorSymbol ocDeclaratorSymbol = localSymbol;
                    final OCSymbolKind ocSymbolKind = ocDeclaratorSymbol.getKind();
                    final boolean b = ocSymbolKind.isLocal();
                    if (b) {
                        break Label_0035;
                    }
                    break Label_0060;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCDeclaratorSymbol ocDeclaratorSymbol = localSymbol;
                    final OCSymbolKind ocSymbolKind = ocDeclaratorSymbol.getKind();
                    final boolean b = ocSymbolKind.isLocal();
                    if (b) {
                        this.myGraph.addInstruction(OCInstruction.InstructionKind.DECLARATOR, ocMethodSelectorPart.getParameter(), localSymbol);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            try {
                if (localSymbol != null) {
                    this.processNonInitializedDeclarator(localSymbol, ocMethodSelectorPart.getNameIdentifier());
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
    }
    
    @Override
    public void visitAssignmentExpression(final OCAssignmentExpression ocAssignmentExpression) {
        final OCExpression sourceExpression = ocAssignmentExpression.getSourceExpression();
        final OCExpression receiverExpression = ocAssignmentExpression.getReceiverExpression();
        try {
            if (sourceExpression != null) {
                sourceExpression.accept((PsiElementVisitor)this);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        receiverExpression.accept((PsiElementVisitor)this);
    }
    
    protected void processNonInitializedDeclarator(@NotNull final OCSymbol ocSymbol, @Nullable final PsiElement psiElement) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "processNonInitializedDeclarator"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    protected void processDereference(@NotNull final OCReferenceExpression ocReferenceExpression) {
        try {
            if (ocReferenceExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "processDereference"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    protected void processReference(@NotNull final PsiElement psiElement, @NotNull final OCSymbol ocSymbol) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "processReference"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "processReference"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.myGraph.addInstruction(OCInstruction.InstructionKind.REFERENCE, psiElement, ocSymbol);
    }
    
    protected void processReferenceFromBlock(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "processReferenceFromBlock"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    protected void processAssignment(@NotNull final OCSymbol ocSymbol, @Nullable final PsiElement psiElement, @Nullable final OCExpression ocExpression, final boolean b) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "processAssignment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.processAssignment(ocSymbol, psiElement, ocExpression, b, false);
    }
    
    protected void processAssignment(@NotNull final OCSymbol ocSymbol, @Nullable final PsiElement psiElement, @Nullable final OCExpression ocExpression, final boolean b, final boolean b2) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder", "processAssignment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!b2) {
                this.myGraph.addInstruction(OCInstruction.InstructionKind.KILL, psiElement, null, ocSymbol);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCInstruction addInstruction = this.myGraph.addInstruction(OCInstruction.InstructionKind.WRITE, psiElement, (PsiElement)ocExpression, ocSymbol);
        try {
            if (addInstruction != null) {
                this.addModifiedValue(addInstruction.getRValue());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
    }
    
    protected void addModifiedValue(@Nullable final PsiElement psiElement) {
        if (psiElement != null) {
            final Iterator<List<PsiElement>> iterator = this.myValuesStack.iterator();
            while (iterator.hasNext()) {
                iterator.next().add(psiElement);
            }
        }
    }
    
    @Override
    public void visitBinaryExpression(final OCBinaryExpression ocBinaryExpression) {
        final OCElementType operationSign = ocBinaryExpression.getOperationSign();
        Label_0165: {
            try {
                if (operationSign != OCTokenTypes.ANDAND) {
                    if (operationSign != OCTokenTypes.OROR) {
                        break Label_0165;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final KidIterator a = this.a((PsiElement)ocBinaryExpression);
            a.accept((PsiElement)ocBinaryExpression.getLeft());
            final Number a2 = a(ocBinaryExpression.getLeft());
            Label_0156: {
                try {
                    if (a2 == null) {
                        ++this.myShortCircuitDepth;
                        a.accept((PsiElement)ocBinaryExpression.getRight());
                        --this.myShortCircuitDepth;
                        break Label_0156;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                Label_0134: {
                    boolean b = false;
                    Label_0118: {
                        try {
                            if (OCExpressionEvaluator.singAsInC(a2) == 0) {
                                b = true;
                                break Label_0118;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        b = false;
                        try {
                            if (operationSign == OCTokenTypes.OROR) {
                                final boolean b2 = true;
                                break Label_0134;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    final boolean b2 = false;
                    try {
                        if (b == b2) {
                            a.accept((PsiElement)ocBinaryExpression.getRight());
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
            }
            a.finish();
            return;
        }
        this.visitElement((PsiElement)ocBinaryExpression);
    }
    
    @Override
    public void visitCallExpression(final OCCallExpression ocCallExpression) {
        Label_0070: {
            try {
                if (this.myTryFirstCalls.isEmpty() || !this.myTryFirstCalls.peek().isNull()) {
                    break Label_0070;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final OCNode lastAddedNode = this.myGraph.getLastAddedNode();
            this.addBranch(lastAddedNode, this.myGraph.addNode());
            this.myTryFirstCalls.peek().set((Object)lastAddedNode);
        }
        this.visitElement((PsiElement)ocCallExpression);
        final OCExpression functionReferenceExpression = ocCallExpression.getFunctionReferenceExpression();
        try {
            if (!(functionReferenceExpression instanceof OCReferenceExpression)) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCSymbol resolveToSymbol = ((OCReferenceExpression)functionReferenceExpression).resolveToSymbol();
        try {
            if (resolveToSymbol == null || !a(resolveToSymbol)) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        this.myGraph.addExitNode(this.myGraph.getLastAddedNode());
        this.myGraph.addNode();
    }
    
    private static boolean a(final OCSymbol ocSymbol) {
        try {
            if (ocSymbol.hasAttribute("noreturn")) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String name = ocSymbol.getName();
        Label_0049: {
            try {
                if (name.equals("__builtin_unreachable")) {
                    break Label_0049;
                }
                final String s = name;
                final String s2 = "__builtin_trap";
                final boolean b = s.equals(s2);
                if (b) {
                    break Label_0049;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final String s = name;
                final String s2 = "__builtin_trap";
                final boolean b = s.equals(s2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.visitElement:(Lcom/intellij/psi/PsiElement;)V
        //     5: aload_1        
        //     6: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    11: astore_2       
        //    12: aload_1        
        //    13: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //    18: astore_3       
        //    19: aload_3        
        //    20: ldc             "raise"
        //    22: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    25: ifeq            74
        //    28: aload_2        
        //    29: ifnull          74
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: aload_2        
        //    40: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    45: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //    48: ldc             "NSException"
        //    50: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    53: ifeq            74
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: aload_0        
        //    64: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.b:()V
        //    67: goto            74
        //    70: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    73: athrow         
        //    74: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  19     32     35     39     Ljava/lang/IllegalArgumentException;
        //  28     56     59     63     Ljava/lang/IllegalArgumentException;
        //  39     67     70     74     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0039:
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
    public void visitReferenceExpression(final OCReferenceExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.visitElement:(Lcom/intellij/psi/PsiElement;)V
        //     5: aload_1        
        //     6: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    11: astore_2       
        //    12: aload_0        
        //    13: aload_2        
        //    14: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.isSymbolInScope:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    17: ifne            25
        //    20: return         
        //    21: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    24: athrow         
        //    25: aload_1        
        //    26: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    29: astore_3       
        //    30: iconst_0       
        //    31: istore          4
        //    33: aload_3        
        //    34: ifnonnull       42
        //    37: return         
        //    38: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: aload_2        
        //    43: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    48: aload_1        
        //    49: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    54: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    57: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    60: ifeq            139
        //    63: aload_3        
        //    64: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    69: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    72: ifne            126
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    81: athrow         
        //    82: aload_3        
        //    83: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    88: instanceof      Lcom/jetbrains/cidr/lang/psi/OCArraySelectionExpression;
        //    91: ifeq            139
        //    94: goto            101
        //    97: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   100: athrow         
        //   101: aload_3        
        //   102: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   107: checkcast       Lcom/jetbrains/cidr/lang/psi/OCArraySelectionExpression;
        //   110: invokeinterface com/jetbrains/cidr/lang/psi/OCArraySelectionExpression.getArrayExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   115: aload_3        
        //   116: if_acmpne       139
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: aload_3        
        //   127: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   132: astore_3       
        //   133: iconst_1       
        //   134: istore          4
        //   136: goto            63
        //   139: iload           4
        //   141: ifeq            215
        //   144: aload_0        
        //   145: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //   148: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.READ:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   151: aload_1        
        //   152: aload_2        
        //   153: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.addInstruction:(Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/dfa/OCInstruction;
        //   156: astore          5
        //   158: aload           5
        //   160: ifnull          215
        //   163: aload_3        
        //   164: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   167: ifeq            215
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: aload_3        
        //   178: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   181: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   184: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   189: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //   192: ifne            215
        //   195: goto            202
        //   198: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   201: athrow         
        //   202: aload           5
        //   204: iconst_1       
        //   205: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.setTransparentRead:(Z)V
        //   208: goto            215
        //   211: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   214: athrow         
        //   215: aload_3        
        //   216: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //   221: astore          5
        //   223: aload           5
        //   225: ifnonnull       233
        //   228: return         
        //   229: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   232: athrow         
        //   233: new             Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor;
        //   236: dup            
        //   237: aload_0        
        //   238: aload_3        
        //   239: aload_2        
        //   240: iload           4
        //   242: aconst_null    
        //   243: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.<init>:(Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;ZLcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$1;)V
        //   246: astore          6
        //   248: aload           5
        //   250: aload           6
        //   252: invokeinterface com/intellij/psi/PsiElement.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //   257: iload           4
        //   259: ifne            297
        //   262: aload           6
        //   264: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.isGenerated:()Z
        //   267: ifne            297
        //   270: goto            277
        //   273: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: aload_0        
        //   278: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //   281: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.READ:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   284: aload_1        
        //   285: aload_2        
        //   286: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.addInstruction:(Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/dfa/OCInstruction;
        //   289: pop            
        //   290: goto            297
        //   293: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   296: athrow         
        //   297: aload           6
        //   299: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.access$200:(Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor;)Z
        //   302: ifeq            317
        //   305: aload_0        
        //   306: aload_1        
        //   307: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.processDereference:(Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;)V
        //   310: goto            317
        //   313: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   316: athrow         
        //   317: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  12     21     21     25     Ljava/lang/IllegalArgumentException;
        //  33     38     38     42     Ljava/lang/IllegalArgumentException;
        //  42     75     78     82     Ljava/lang/IllegalArgumentException;
        //  63     94     97     101    Ljava/lang/IllegalArgumentException;
        //  82     119    122    126    Ljava/lang/IllegalArgumentException;
        //  158    170    173    177    Ljava/lang/IllegalArgumentException;
        //  163    195    198    202    Ljava/lang/IllegalArgumentException;
        //  177    208    211    215    Ljava/lang/IllegalArgumentException;
        //  223    229    229    233    Ljava/lang/IllegalArgumentException;
        //  248    270    273    277    Ljava/lang/IllegalArgumentException;
        //  262    290    293    297    Ljava/lang/IllegalArgumentException;
        //  297    310    313    317    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0063:
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
    
    @Contract("null -> false")
    protected boolean isSymbolInScope(@Nullable final OCSymbol ocSymbol) {
        Label_0023: {
            try {
                if (ocSymbol == null) {
                    return false;
                }
                final OCSymbol ocSymbol2 = ocSymbol;
                final OCSymbolKind ocSymbolKind = ocSymbol2.getKind();
                final boolean b = ocSymbolKind.isLocal();
                if (b) {
                    break Label_0023;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCSymbol ocSymbol2 = ocSymbol;
                final OCSymbolKind ocSymbolKind = ocSymbol2.getKind();
                final boolean b = ocSymbolKind.isLocal();
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
    
    @Override
    public void visitBlockStatement(final OCBlockStatement ocBlockStatement) {
        final PsiElement parent = ocBlockStatement.getParent();
        Label_0032: {
            try {
                if (!(parent instanceof OCFunctionDefinition)) {
                    break Label_0032;
                }
                final PsiElement psiElement = parent;
                final OCControlFlowBuilder ocControlFlowBuilder = this;
                final OCControlFlowGraph ocControlFlowGraph = ocControlFlowBuilder.myGraph;
                final PsiElement psiElement2 = ocControlFlowGraph.getCodeFragment();
                if (psiElement == psiElement2) {
                    break Label_0032;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final PsiElement psiElement = parent;
                final OCControlFlowBuilder ocControlFlowBuilder = this;
                final OCControlFlowGraph ocControlFlowGraph = ocControlFlowBuilder.myGraph;
                final PsiElement psiElement2 = ocControlFlowGraph.getCodeFragment();
                if (psiElement == psiElement2) {
                    this.visitElement((PsiElement)ocBlockStatement);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    static {
        closingTokens = TokenSet.orSet(new TokenSet[] { TokenSet.create(new IElementType[] { OCTokenTypes.SEMICOLON, OCTokenTypes.RBRACE }), OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET, OCTokenTypes.DIRECTIVES });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    protected static class SwitchInfo
    {
        OCNode myNode;
        OCExpression myExpression;
        List<OCExpression> myCaseExpressions;
        boolean myHasDefault;
        
        public SwitchInfo(@Nullable final OCDeclarationOrExpression ocDeclarationOrExpression, @NotNull final OCNode myNode) {
            if (myNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$SwitchInfo", "<init>"));
            }
            this.myCaseExpressions = new ArrayList<OCExpression>();
            this.myExpression = ((ocDeclarationOrExpression != null) ? ocDeclarationOrExpression.getExpression() : null);
            this.myNode = myNode;
        }
        
        @NotNull
        public OCNode getNode() {
            OCNode myNode;
            try {
                myNode = this.myNode;
                if (myNode == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$SwitchInfo", "getNode"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myNode;
        }
        
        @Nullable
        public OCExpression getExpression() {
            return this.myExpression;
        }
        
        public boolean hasDefault() {
            return this.myHasDefault;
        }
        
        public void setHasDefault(final boolean myHasDefault) {
            this.myHasDefault = myHasDefault;
        }
        
        public void addCaseExpression(@Nullable final OCExpression ocExpression) {
            try {
                if (ocExpression != null) {
                    this.myCaseExpressions.add(ocExpression);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        
        public List<OCExpression> getCaseExpressions() {
            return this.myCaseExpressions;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private class MyParentVisitor extends OCVisitor
    {
        private PsiElement myElement;
        private OCSymbol mySymbol;
        private boolean myWasQualifierOrIndex;
        private boolean myGenerated;
        private boolean myDereference;
        final /* synthetic */ OCControlFlowBuilder this$0;
        
        private MyParentVisitor(@NotNull final PsiElement myElement, final OCSymbol mySymbol, final boolean myWasQualifierOrIndex) {
            if (myElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor", "<init>"));
            }
            if (mySymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor", "<init>"));
            }
            this.myElement = myElement;
            this.mySymbol = mySymbol;
            this.myWasQualifierOrIndex = myWasQualifierOrIndex;
        }
        
        public boolean isGenerated() {
            return this.myGenerated;
        }
        
        private boolean a() {
            return this.myDereference;
        }
        
        @Override
        public void visitAssignmentExpression(final OCAssignmentExpression ocAssignmentExpression) {
            try {
                if (this.a(ocAssignmentExpression, OCOperatorReference.OperatorPlacement.INFIX)) {
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            boolean b = false;
            Label_0037: {
                try {
                    if (ocAssignmentExpression.getOperationSign() != OCTokenTypes.EQ) {
                        b = true;
                        break Label_0037;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                b = false;
            }
            final boolean b2 = b;
            final OCExpression receiverExpression = ocAssignmentExpression.getReceiverExpression();
            final OCExpression sourceExpression = ocAssignmentExpression.getSourceExpression();
            try {
                if (PsiTreeUtil.isAncestor((PsiElement)receiverExpression, this.myElement, false)) {
                    this.a(receiverExpression, sourceExpression, b2, ocAssignmentExpression.getContainingFile());
                    this.myGenerated = true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        
        private void a(@Nullable final OCExpression ocExpression, @Nullable final OCExpression ocExpression2, final boolean b, @NotNull final PsiFile psiFile) {
            try {
                if (psiFile == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "containingFile", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor", "generateAssignmentInstructions"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (b) {
                    OCControlFlowBuilder.this.myGraph.addInstruction(OCInstruction.InstructionKind.READ, this.myElement, this.mySymbol);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            Label_0128: {
                OCControlFlowBuilder ocControlFlowBuilder = null;
                OCSymbol ocSymbol = null;
                OCExpression ocExpression3 = null;
                OCExpression ocExpression4 = null;
                boolean b2 = false;
                boolean b3 = false;
                Label_0122: {
                    Label_0113: {
                        try {
                            if (this.myWasQualifierOrIndex) {
                                break Label_0128;
                            }
                            final MyParentVisitor myParentVisitor = this;
                            ocControlFlowBuilder = myParentVisitor.this$0;
                            final MyParentVisitor myParentVisitor2 = this;
                            ocSymbol = myParentVisitor2.mySymbol;
                            ocExpression3 = ocExpression;
                            ocExpression4 = ocExpression2;
                            b2 = b;
                            final MyParentVisitor myParentVisitor3 = this;
                            final OCControlFlowBuilder ocControlFlowBuilder2 = myParentVisitor3.this$0;
                            final int n = ocControlFlowBuilder2.myShortCircuitDepth;
                            if (n != 0) {
                                break Label_0113;
                            }
                            break Label_0113;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final MyParentVisitor myParentVisitor = this;
                            ocControlFlowBuilder = myParentVisitor.this$0;
                            final MyParentVisitor myParentVisitor2 = this;
                            ocSymbol = myParentVisitor2.mySymbol;
                            ocExpression3 = ocExpression;
                            ocExpression4 = ocExpression2;
                            b2 = b;
                            final MyParentVisitor myParentVisitor3 = this;
                            final OCControlFlowBuilder ocControlFlowBuilder2 = myParentVisitor3.this$0;
                            final int n = ocControlFlowBuilder2.myShortCircuitDepth;
                            if (n != 0) {
                                b3 = true;
                                break Label_0122;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    b3 = false;
                }
                ocControlFlowBuilder.processAssignment(ocSymbol, (PsiElement)ocExpression3, ocExpression4, b2, b3);
                return;
                try {
                    if (this.mySymbol.getType().resolve(psiFile) instanceof OCStructType) {
                        OCControlFlowBuilder.this.myGraph.addInstruction(OCInstruction.InstructionKind.WRITE, (PsiElement)ocExpression, (PsiElement)ocExpression2, this.mySymbol);
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
        }
        
        private void a(@Nullable final OCFunctionSymbol p0, @Nullable final OCOperatorReference.OperatorPlacement p1, @Nullable final OCFunctionType p2, final List<? extends OCTypeOwner> p3, @NotNull final OCResolveContext p4) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload           5
            //     2: ifnonnull       45
            //     5: new             Ljava/lang/IllegalArgumentException;
            //     8: dup            
            //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    11: ldc             3
            //    13: anewarray       Ljava/lang/Object;
            //    16: dup            
            //    17: ldc             0
            //    19: ldc             "context"
            //    21: aastore        
            //    22: dup            
            //    23: ldc             1
            //    25: ldc             "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor"
            //    27: aastore        
            //    28: dup            
            //    29: ldc             2
            //    31: ldc             "processFunctionCall"
            //    33: aastore        
            //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    40: athrow         
            //    41: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    44: athrow         
            //    45: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
            //    48: aload           4
            //    50: ifnonnull       58
            //    53: return         
            //    54: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    57: athrow         
            //    58: aload_1        
            //    59: instanceof      Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
            //    62: ifeq            117
            //    65: aload_1        
            //    66: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
            //    69: invokevirtual   com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol.getOverloads:()Ljava/util/List;
            //    72: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
            //    77: astore          6
            //    79: aload           6
            //    81: invokeinterface java/util/Iterator.hasNext:()Z
            //    86: ifeq            116
            //    89: aload           6
            //    91: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //    96: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //    99: astore          7
            //   101: aload_0        
            //   102: aload           7
            //   104: aload_2        
            //   105: aload_3        
            //   106: aload           4
            //   108: aload           5
            //   110: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
            //   113: goto            79
            //   116: return         
            //   117: aload_1        
            //   118: ifnull          134
            //   121: aload_1        
            //   122: aload_2        
            //   123: aload           5
            //   125: aconst_null    
            //   126: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.getParameterTypes:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/types/CVQualifiers;)Ljava/util/List;
            //   129: astore          6
            //   131: goto            152
            //   134: aload_3        
            //   135: ifnull          147
            //   138: aload_3        
            //   139: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterTypes:()Ljava/util/List;
            //   142: astore          6
            //   144: goto            152
            //   147: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
            //   150: astore          6
            //   152: iconst_m1      
            //   153: istore          7
            //   155: iconst_0       
            //   156: istore          8
            //   158: iload           8
            //   160: aload           4
            //   162: invokeinterface java/util/List.size:()I
            //   167: if_icmpge       228
            //   170: aload           4
            //   172: iload           8
            //   174: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
            //   179: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
            //   182: astore          9
            //   184: aload           9
            //   186: instanceof      Lcom/intellij/psi/PsiElement;
            //   189: ifeq            222
            //   192: aload           9
            //   194: checkcast       Lcom/intellij/psi/PsiElement;
            //   197: aload_0        
            //   198: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myElement:Lcom/intellij/psi/PsiElement;
            //   201: iconst_0       
            //   202: invokestatic    com/intellij/psi/util/PsiTreeUtil.isAncestor:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Z)Z
            //   205: ifeq            222
            //   208: goto            215
            //   211: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   214: athrow         
            //   215: iload           8
            //   217: istore          7
            //   219: goto            228
            //   222: iinc            8, 1
            //   225: goto            158
            //   228: iload           7
            //   230: iconst_m1      
            //   231: if_icmpeq       253
            //   234: iload           7
            //   236: aload           6
            //   238: invokeinterface java/util/List.size:()I
            //   243: if_icmplt       258
            //   246: goto            253
            //   249: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   252: athrow         
            //   253: return         
            //   254: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   257: athrow         
            //   258: aload           6
            //   260: iload           7
            //   262: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
            //   267: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
            //   270: astore          8
            //   272: aload           8
            //   274: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
            //   277: ifeq            298
            //   280: aload           8
            //   282: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
            //   285: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isReferenceToConst:()Z
            //   288: ifeq            346
            //   291: goto            298
            //   294: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   297: athrow         
            //   298: aload_1        
            //   299: ifnull          373
            //   302: goto            309
            //   305: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   308: athrow         
            //   309: iload           7
            //   311: ifne            373
            //   314: goto            321
            //   317: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   320: athrow         
            //   321: aload_1        
            //   322: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   325: dup            
            //   326: aload_0        
            //   327: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myElement:Lcom/intellij/psi/PsiElement;
            //   330: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
            //   333: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppMemberOperator:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
            //   336: ifeq            373
            //   339: goto            346
            //   342: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   345: athrow         
            //   346: aload_0        
            //   347: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.this$0:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder;
            //   350: aload_0        
            //   351: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myElement:Lcom/intellij/psi/PsiElement;
            //   354: aload_0        
            //   355: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //   358: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.processReference:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
            //   361: aload_0        
            //   362: iconst_1       
            //   363: putfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myGenerated:Z
            //   366: goto            373
            //   369: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   372: athrow         
            //   373: return         
            //    Signature:
            //  (Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/util/List<+Lcom/jetbrains/cidr/lang/types/OCTypeOwner;>;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      41     41     45     Ljava/lang/IllegalArgumentException;
            //  45     54     54     58     Ljava/lang/IllegalArgumentException;
            //  184    208    211    215    Ljava/lang/IllegalArgumentException;
            //  228    246    249    253    Ljava/lang/IllegalArgumentException;
            //  234    254    254    258    Ljava/lang/IllegalArgumentException;
            //  272    291    294    298    Ljava/lang/IllegalArgumentException;
            //  280    302    305    309    Ljava/lang/IllegalArgumentException;
            //  298    314    317    321    Ljava/lang/IllegalArgumentException;
            //  309    339    342    346    Ljava/lang/IllegalArgumentException;
            //  321    366    369    373    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0298:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        private boolean a(final OCExpression ocExpression, @Nullable final OCOperatorReference.OperatorPlacement operatorPlacement) {
            boolean b = false;
            final PsiReference reference = ocExpression.getReference();
            if (reference instanceof OCOperatorReference) {
                for (final OCSymbol ocSymbol : ((OCOperatorReference)reference).resolveToSymbols()) {
                    if (ocSymbol instanceof OCFunctionSymbol) {
                        b = true;
                        this.a((OCFunctionSymbol)ocSymbol, operatorPlacement, null, ((OCOperatorReference)reference).getArgumentExpressions(), new OCResolveContext((PsiElement)ocExpression));
                    }
                }
            }
            return b;
        }
        
        @Override
        public void visitCallExpression(final OCCallExpression ocCallExpression) {
            try {
                if (this.a(ocCallExpression, OCOperatorReference.OperatorPlacement.POSTFIX)) {
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final OCType terminalType = ocCallExpression.getFunctionReferenceExpression().getResolvedType().getTerminalType();
            if (terminalType instanceof OCFunctionType) {
                final OCFunctionType ocFunctionType = (OCFunctionType)terminalType;
                final OCArgumentList argumentList = ocCallExpression.getArgumentList();
                this.a(null, null, ocFunctionType, argumentList.getArguments(), new OCResolveContext((PsiElement)argumentList));
            }
        }
        
        @Override
        public void visitArgumentList(final OCArgumentList list) {
            this.a((PsiElement)list);
        }
        
        @Override
        public void visitMessageArgument(final OCMessageArgument p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getParent:()Lcom/intellij/psi/PsiElement;
            //     6: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
            //     9: astore_2       
            //    10: aload_2        
            //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArguments:()Ljava/util/List;
            //    16: aload_1        
            //    17: invokeinterface java/util/List.indexOf:(Ljava/lang/Object;)I
            //    22: istore_3       
            //    23: aload_2        
            //    24: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getProbableResponders:()Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders;
            //    29: astore          4
            //    31: aload           4
            //    33: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getAllResponders:()Ljava/util/List;
            //    36: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
            //    41: astore          5
            //    43: aload           5
            //    45: invokeinterface java/util/Iterator.hasNext:()Z
            //    50: ifeq            212
            //    53: aload           5
            //    55: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //    60: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
            //    63: astore          6
            //    65: aload           6
            //    67: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getSelectors:()Ljava/util/List;
            //    72: invokeinterface java/util/List.size:()I
            //    77: iload_3        
            //    78: if_icmpgt       88
            //    81: goto            43
            //    84: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    87: athrow         
            //    88: aload           6
            //    90: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getSelectors:()Ljava/util/List;
            //    95: iload_3        
            //    96: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
            //   101: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol;
            //   104: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol.getParameter:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
            //   109: astore          7
            //   111: aload           7
            //   113: ifnull          209
            //   116: aload           7
            //   118: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isPassByReference:()Z
            //   121: ifne            170
            //   124: goto            131
            //   127: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   130: athrow         
            //   131: aload           7
            //   133: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   136: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
            //   139: ifeq            209
            //   142: goto            149
            //   145: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   148: athrow         
            //   149: aload           7
            //   151: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   154: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
            //   157: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isReferenceToConst:()Z
            //   160: ifne            209
            //   163: goto            170
            //   166: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   169: athrow         
            //   170: aload_1        
            //   171: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getArgumentExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
            //   176: astore          8
            //   178: aload           8
            //   180: ifnull          203
            //   183: aload_0        
            //   184: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.this$0:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder;
            //   187: aload           8
            //   189: aload_0        
            //   190: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //   193: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.processReference:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
            //   196: goto            203
            //   199: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   202: athrow         
            //   203: aload_0        
            //   204: iconst_1       
            //   205: putfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myGenerated:Z
            //   208: return         
            //   209: goto            43
            //   212: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  65     84     84     88     Ljava/lang/IllegalArgumentException;
            //  111    124    127    131    Ljava/lang/IllegalArgumentException;
            //  116    142    145    149    Ljava/lang/IllegalArgumentException;
            //  131    163    166    170    Ljava/lang/IllegalArgumentException;
            //  178    196    199    203    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0131:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        public void visitForeachStatement(final OCForeachStatement ocForeachStatement) {
            OCElement ocElement = ocForeachStatement.getVariableExpression();
            if (ocElement instanceof OCExpressionStatement) {
                ocElement = ((OCExpressionStatement)ocElement).getExpression();
            }
            try {
                if (this.myElement == ocElement) {
                    OCControlFlowBuilder.this.processAssignment(this.mySymbol, this.myElement, null, true);
                    this.myGenerated = true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        
        @Override
        public void visitDeclarationStatement(final OCDeclarationStatement ocDeclarationStatement) {
            try {
                if (ocDeclarationStatement.getParent() instanceof OCForeachStatement) {
                    this.visitForeachStatement((OCForeachStatement)ocDeclarationStatement.getParent());
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.myGenerated = true;
        }
        
        @Override
        public void visitDeclarator(final OCDeclarator ocDeclarator) {
            final OCType resolvedType = ocDeclarator.getResolvedType();
            if (resolvedType instanceof OCCppReferenceType) {
                final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)resolvedType;
                try {
                    if (!ocCppReferenceType.isReferenceToConst()) {
                        OCControlFlowBuilder.this.processReference(this.myElement, this.mySymbol);
                        this.myGenerated = true;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
            else if (resolvedType instanceof OCStructType) {
                final OCFunctionSymbol resolveCtorCall = OCResolveUtil.resolveCtorCall(ocDeclarator);
                if (resolveCtorCall != null) {
                    this.a(resolveCtorCall, null, null, ocDeclarator.getInitializers(), new OCResolveContext((PsiElement)ocDeclarator));
                }
            }
        }
        
        @Override
        public void visitExpressionStatement(final OCExpressionStatement ocExpressionStatement) {
            try {
                if (ocExpressionStatement.getParent() instanceof OCForeachStatement) {
                    this.visitForeachStatement((OCForeachStatement)ocExpressionStatement.getParent());
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.myGenerated = true;
        }
        
        @Override
        public void visitPostfixExpression(final OCPostfixExpression p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: aload_1        
            //     2: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement.POSTFIX:Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;
            //     5: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;)Z
            //     8: ifne            107
            //    11: aload_1        
            //    12: invokeinterface com/jetbrains/cidr/lang/psi/OCPostfixExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
            //    17: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUSPLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
            //    20: if_acmpeq       49
            //    23: goto            30
            //    26: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    29: athrow         
            //    30: aload_1        
            //    31: invokeinterface com/jetbrains/cidr/lang/psi/OCPostfixExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
            //    36: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUSMINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
            //    39: if_acmpne       107
            //    42: goto            49
            //    45: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    48: athrow         
            //    49: aload_0        
            //    50: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.this$0:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder;
            //    53: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.myGraph:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
            //    56: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.READ:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
            //    59: aload_0        
            //    60: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myElement:Lcom/intellij/psi/PsiElement;
            //    63: aload_0        
            //    64: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    67: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.addInstruction:(Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/dfa/OCInstruction;
            //    70: pop            
            //    71: aload_0        
            //    72: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.this$0:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder;
            //    75: aload_0        
            //    76: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    79: aload_1        
            //    80: invokeinterface com/jetbrains/cidr/lang/psi/OCPostfixExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
            //    85: aload_1        
            //    86: invokeinterface com/jetbrains/cidr/lang/psi/OCPostfixExpression.getOperand:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
            //    91: iconst_1       
            //    92: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.processAssignment:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/psi/OCExpression;Z)V
            //    95: aload_0        
            //    96: iconst_1       
            //    97: putfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myGenerated:Z
            //   100: goto            107
            //   103: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   106: athrow         
            //   107: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      23     26     30     Ljava/lang/IllegalArgumentException;
            //  11     42     45     49     Ljava/lang/IllegalArgumentException;
            //  30     100    103    107    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0030:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        public void visitPrefixExpression(final OCPrefixExpression ocPrefixExpression) {
            Label_0030: {
                try {
                    if (this.a(ocPrefixExpression, OCOperatorReference.OperatorPlacement.PREFIX)) {
                        return;
                    }
                    final OCPrefixExpression ocPrefixExpression2 = ocPrefixExpression;
                    final OCElementType ocElementType = ocPrefixExpression2.getOperationSign();
                    final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.PLUSPLUS;
                    if (ocElementType != ocPunctuatorElementType) {
                        break Label_0030;
                    }
                    break Label_0030;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCPrefixExpression ocPrefixExpression2 = ocPrefixExpression;
                    final OCElementType ocElementType = ocPrefixExpression2.getOperationSign();
                    final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.PLUSPLUS;
                    if (ocElementType != ocPunctuatorElementType) {
                        if (ocPrefixExpression.getOperationSign() != OCTokenTypes.MINUSMINUS) {
                            return;
                        }
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            OCControlFlowBuilder.this.myGraph.addInstruction(OCInstruction.InstructionKind.READ, this.myElement, this.mySymbol);
            OCControlFlowBuilder.this.processAssignment(this.mySymbol, (PsiElement)ocPrefixExpression.getOperand(), ocPrefixExpression.getOperand(), true);
            final PsiElement parent = OCParenthesesUtils.topmostParenthesized(ocPrefixExpression).getParent();
            try {
                if (parent != null) {
                    parent.accept((PsiElementVisitor)this);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        
        @Override
        public void visitBinaryExpression(final OCBinaryExpression ocBinaryExpression) {
            this.a(ocBinaryExpression, OCOperatorReference.OperatorPlacement.INFIX);
        }
        
        @Override
        public void visitUnaryExpression(final OCUnaryExpression p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: aload_1        
            //     2: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement.PREFIX:Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;
            //     5: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;)Z
            //     8: ifeq            16
            //    11: return         
            //    12: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    15: athrow         
            //    16: aload_1        
            //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCUnaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
            //    22: astore_2       
            //    23: aload_2        
            //    24: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AND:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
            //    27: if_acmpeq       58
            //    30: aload_2        
            //    31: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.__IMAG__KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
            //    34: if_acmpeq       58
            //    37: goto            44
            //    40: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aload_2        
            //    45: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.__REAL__KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
            //    48: if_acmpne       82
            //    51: goto            58
            //    54: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    57: athrow         
            //    58: aload_0        
            //    59: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.this$0:Lcom/jetbrains/cidr/lang/dfa/OCControlFlowBuilder;
            //    62: aload_1        
            //    63: aload_0        
            //    64: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    67: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder.processReference:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
            //    70: aload_0        
            //    71: iconst_1       
            //    72: putfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myGenerated:Z
            //    75: goto            128
            //    78: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    81: athrow         
            //    82: aload_2        
            //    83: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
            //    86: if_acmpne       128
            //    89: aload_1        
            //    90: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
            //    93: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
            //    98: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSizeofExpression;
            //   101: ifeq            123
            //   104: goto            111
            //   107: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   110: athrow         
            //   111: aload_0        
            //   112: iconst_1       
            //   113: putfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myGenerated:Z
            //   116: goto            128
            //   119: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   122: athrow         
            //   123: aload_0        
            //   124: iconst_1       
            //   125: putfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myDereference:Z
            //   128: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      12     12     16     Ljava/lang/IllegalArgumentException;
            //  23     37     40     44     Ljava/lang/IllegalArgumentException;
            //  30     51     54     58     Ljava/lang/IllegalArgumentException;
            //  44     78     78     82     Ljava/lang/IllegalArgumentException;
            //  82     104    107    111    Ljava/lang/IllegalArgumentException;
            //  89     119    119    123    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0044:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        public void visitQualifiedExpression(final OCQualifiedExpression p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: aload_1        
            //     2: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement.INFIX:Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;
            //     5: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;)Z
            //     8: ifne            86
            //    11: aload_1        
            //    12: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
            //    17: aload_0        
            //    18: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myElement:Lcom/intellij/psi/PsiElement;
            //    21: if_acmpne       86
            //    24: goto            31
            //    27: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    30: athrow         
            //    31: aload_1        
            //    32: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifyingTokenKind:()Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
            //    37: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
            //    40: if_acmpne       74
            //    43: goto            50
            //    46: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    49: athrow         
            //    50: aload_1        
            //    51: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
            //    56: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //    61: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
            //    64: ifne            86
            //    67: goto            74
            //    70: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    73: athrow         
            //    74: aload_0        
            //    75: iconst_1       
            //    76: putfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myDereference:Z
            //    79: goto            86
            //    82: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    85: athrow         
            //    86: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      24     27     31     Ljava/lang/IllegalArgumentException;
            //  11     43     46     50     Ljava/lang/IllegalArgumentException;
            //  31     67     70     74     Ljava/lang/IllegalArgumentException;
            //  50     79     82     86     Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0031:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        public void visitArraySelectionExpression(final OCArraySelectionExpression p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: aload_1        
            //     2: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement.POSTFIX:Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;
            //     5: invokespecial   com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;)Z
            //     8: ifne            67
            //    11: aload_1        
            //    12: invokeinterface com/jetbrains/cidr/lang/psi/OCArraySelectionExpression.getArrayExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
            //    17: aload_0        
            //    18: getfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myElement:Lcom/intellij/psi/PsiElement;
            //    21: if_acmpne       67
            //    24: goto            31
            //    27: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    30: athrow         
            //    31: aload_1        
            //    32: invokeinterface com/jetbrains/cidr/lang/psi/OCArraySelectionExpression.getArrayExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
            //    37: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //    42: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
            //    45: ifne            67
            //    48: goto            55
            //    51: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    54: athrow         
            //    55: aload_0        
            //    56: iconst_1       
            //    57: putfield        com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.myDereference:Z
            //    60: goto            67
            //    63: invokestatic    com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    66: athrow         
            //    67: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      24     27     31     Ljava/lang/IllegalArgumentException;
            //  11     48     51     55     Ljava/lang/IllegalArgumentException;
            //  31     60     63     67     Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0031:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        public void visitConditionalExpression(final OCConditionalExpression ocConditionalExpression) {
            try {
                if (!PsiTreeUtil.isAncestor((PsiElement)ocConditionalExpression.getCondition(), this.myElement, false)) {
                    this.a((PsiElement)ocConditionalExpression);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        
        @Override
        public void visitParenthesizedExpression(final OCParenthesizedExpression ocParenthesizedExpression) {
            this.a((PsiElement)ocParenthesizedExpression);
        }
        
        @Override
        public void visitCastExpression(final OCCastExpression ocCastExpression) {
            final OCType castType = ocCastExpression.getCastType();
            Label_0031: {
                try {
                    if (!(castType instanceof OCCppReferenceType)) {
                        return;
                    }
                    final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)castType;
                    final OCCppReferenceType ocCppReferenceType2 = ocCppReferenceType;
                    final boolean b = ocCppReferenceType2.isReferenceToConst();
                    if (!b) {
                        break Label_0031;
                    }
                    return;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)castType;
                    final OCCppReferenceType ocCppReferenceType2 = ocCppReferenceType;
                    final boolean b = ocCppReferenceType2.isReferenceToConst();
                    if (!b) {
                        this.a((PsiElement)ocCastExpression);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
        }
        
        @Override
        public void visitCompoundInitializer(final OCCompoundInitializer ocCompoundInitializer) {
            this.a((PsiElement)ocCompoundInitializer);
        }
        
        @Override
        public void visitAsmStatementPart(final OCAsmStatementPartImpl ocAsmStatementPartImpl) {
            final OCExpression expression = ocAsmStatementPartImpl.getExpression();
            Label_0023: {
                try {
                    if (!ocAsmStatementPartImpl.isOutputParametersPart()) {
                        return;
                    }
                    final OCExpression ocExpression = expression;
                    if (ocExpression != null) {
                        break Label_0023;
                    }
                    return;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCExpression ocExpression = expression;
                    if (ocExpression != null) {
                        this.a(expression, null, false, ocAsmStatementPartImpl.getContainingFile());
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
        }
        
        private void a(@NotNull final PsiElement psiElement) {
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/dfa/OCControlFlowBuilder$MyParentVisitor", "visitParent"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final PsiElement parent = psiElement.getParent();
            try {
                if (parent != null) {
                    parent.accept((PsiElementVisitor)this);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    protected interface NodeState
    {
    }
    
    protected interface KidIterator
    {
        void skipLeaves();
        
        void accept(@Nullable final PsiElement p0);
        
        void skip(@Nullable final PsiElement p0);
        
        void acceptAll();
        
        void finish();
    }
}
