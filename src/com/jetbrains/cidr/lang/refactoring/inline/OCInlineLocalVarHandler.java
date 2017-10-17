// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.inline;

import com.jetbrains.cidr.lang.parser.OCMacroRange;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.PsiNamedElement;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiReference;
import com.intellij.util.CommonProcessors;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.SmartPsiElementPointer;
import java.util.Map;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.editor.colors.EditorColors;
import java.util.Iterator;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.dfa.OCInstruction;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.dfa.OCDataFlowAnalyzer;
import com.jetbrains.cidr.lang.dfa.OCControlFlowGraph;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.editor.Editor;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

public class OCInlineLocalVarHandler extends OCInlineActionHandlerBase<OCDeclarator>
{
    @Override
    protected String getElementKind(final OCDeclarator ocDeclarator) {
        return "local variable";
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.inlineLocalVar";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public boolean canInlineElement(final PsiElement psiElement) {
        try {
            if (!(psiElement instanceof OCDeclarator)) {
                return false;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCSymbol symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
        Label_0049: {
            try {
                if (!(symbol instanceof OCDeclaratorSymbol)) {
                    return false;
                }
                final OCSymbol ocSymbol = symbol;
                final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.LOCAL_VARIABLE;
                if (ocSymbolKind == ocSymbolKind2) {
                    break Label_0049;
                }
                return false;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final OCSymbol ocSymbol = symbol;
                final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.LOCAL_VARIABLE;
                if (ocSymbolKind == ocSymbolKind2) {
                    return true;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    @Override
    protected boolean allowInlineSingleUsage() {
        return false;
    }
    
    @Override
    protected String checkValidness(final OCDeclarator p0, final List<PsiElement> p1, final PsiElement p2, final String p3, final Editor p4, final Ref<PsiElement> p5, final List<String> p6, final boolean p7) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //     3: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //     6: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //     9: astore          9
        //    11: aload_1        
        //    12: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    17: astore          10
        //    19: aload           9
        //    21: ifnonnull       31
        //    24: ldc             "Can't find the method/function"
        //    26: areturn        
        //    27: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    30: athrow         
        //    31: aload           10
        //    33: ifnonnull       43
        //    36: ldc             "SILENT_EXIT"
        //    38: areturn        
        //    39: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    42: athrow         
        //    43: aload_3        
        //    44: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    47: ifeq            63
        //    50: aload_3        
        //    51: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    56: goto            69
        //    59: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    62: athrow         
        //    63: aload_1        
        //    64: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //    69: astore          11
        //    71: aload           9
        //    73: aload           11
        //    75: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Lcom/jetbrains/cidr/lang/psi/OCCallable;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //    78: astore          12
        //    80: aload           12
        //    82: ifnonnull       92
        //    85: ldc             "SILENT_EXIT"
        //    87: areturn        
        //    88: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    91: athrow         
        //    92: new             Lcom/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher;
        //    95: dup            
        //    96: aload           12
        //    98: aload           10
        //   100: aload           11
        //   102: iconst_0       
        //   103: iconst_1       
        //   104: iconst_1       
        //   105: invokespecial   com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.<init>:(Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;ZZZ)V
        //   108: astore          13
        //   110: aload           13
        //   112: invokevirtual   com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.process:()V
        //   115: aload           13
        //   117: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.WRITE:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   120: invokevirtual   com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.getInstructionsOfKind:(Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;)Ljava/util/Collection;
        //   123: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/util/Collection;)Ljava/util/List;
        //   126: astore          14
        //   128: new             Ljava/util/ArrayList;
        //   131: dup            
        //   132: invokespecial   java/util/ArrayList.<init>:()V
        //   135: astore          15
        //   137: new             Ljava/util/ArrayList;
        //   140: dup            
        //   141: invokespecial   java/util/ArrayList.<init>:()V
        //   144: astore          16
        //   146: new             Ljava/util/ArrayList;
        //   149: dup            
        //   150: invokespecial   java/util/ArrayList.<init>:()V
        //   153: astore          17
        //   155: iconst_0       
        //   156: istore          18
        //   158: aload           14
        //   160: invokeinterface java/util/List.size:()I
        //   165: ifne            464
        //   168: new             Ljava/util/ArrayList;
        //   171: dup            
        //   172: invokespecial   java/util/ArrayList.<init>:()V
        //   175: astore          14
        //   177: aload           12
        //   179: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getParentGraph:()Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //   182: astore          19
        //   184: aload           19
        //   186: ifnull          315
        //   189: aload           19
        //   191: aload           10
        //   193: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getInstructions:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/intellij/util/containers/MultiMap;
        //   196: astore          20
        //   198: aload           20
        //   200: ifnull          305
        //   203: aload           14
        //   205: aload           20
        //   207: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.WRITE:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   210: invokevirtual   com/intellij/util/containers/MultiMap.get:(Ljava/lang/Object;)Ljava/util/Collection;
        //   213: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/util/Collection;)Ljava/util/List;
        //   216: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   221: pop            
        //   222: aload           15
        //   224: aload           20
        //   226: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.READ:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   229: invokevirtual   com/intellij/util/containers/MultiMap.get:(Ljava/lang/Object;)Ljava/util/Collection;
        //   232: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/util/Collection;)Ljava/util/List;
        //   235: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   240: pop            
        //   241: aload           16
        //   243: aload           20
        //   245: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.REFERENCE:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   248: invokevirtual   com/intellij/util/containers/MultiMap.get:(Ljava/lang/Object;)Ljava/util/Collection;
        //   251: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/util/Collection;)Ljava/util/List;
        //   254: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   259: pop            
        //   260: aload           17
        //   262: aload           20
        //   264: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.READ_IN_BLOCK:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   267: invokevirtual   com/intellij/util/containers/MultiMap.get:(Ljava/lang/Object;)Ljava/util/Collection;
        //   270: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/util/Collection;)Ljava/util/List;
        //   273: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   278: pop            
        //   279: aload           17
        //   281: aload           20
        //   283: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.WRITE_IN_BLOCK:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   286: invokevirtual   com/intellij/util/containers/MultiMap.get:(Ljava/lang/Object;)Ljava/util/Collection;
        //   289: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/util/Collection;)Ljava/util/List;
        //   292: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   297: pop            
        //   298: goto            305
        //   301: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   304: athrow         
        //   305: aload           19
        //   307: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getParentGraph:()Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //   310: astore          19
        //   312: goto            184
        //   315: aload           17
        //   317: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   322: astore          20
        //   324: aload           20
        //   326: invokeinterface java/util/Iterator.hasNext:()Z
        //   331: ifeq            377
        //   334: aload           20
        //   336: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   341: checkcast       Lcom/intellij/psi/PsiElement;
        //   344: astore          21
        //   346: aload           12
        //   348: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getCodeFragment:()Lcom/intellij/psi/PsiElement;
        //   351: aload           21
        //   353: iconst_0       
        //   354: invokestatic    com/intellij/psi/util/PsiTreeUtil.isAncestor:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Z)Z
        //   357: ifne            374
        //   360: aload           5
        //   362: ldc             "There are usages in other blocks"
        //   364: aload           17
        //   366: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Lcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;
        //   369: areturn        
        //   370: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   373: athrow         
        //   374: goto            324
        //   377: aload           14
        //   379: invokeinterface java/util/List.size:()I
        //   384: ifne            415
        //   387: new             Ljava/lang/StringBuilder;
        //   390: dup            
        //   391: invokespecial   java/lang/StringBuilder.<init>:()V
        //   394: aload           4
        //   396: invokestatic    com/intellij/openapi/util/text/StringUtil.capitalize:(Ljava/lang/String;)Ljava/lang/String;
        //   399: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   402: ldc             " was not initialized"
        //   404: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   407: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   410: areturn        
        //   411: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   414: athrow         
        //   415: aload           14
        //   417: invokeinterface java/util/List.size:()I
        //   422: iconst_1       
        //   423: if_icmple       458
        //   426: aload           5
        //   428: new             Ljava/lang/StringBuilder;
        //   431: dup            
        //   432: invokespecial   java/lang/StringBuilder.<init>:()V
        //   435: ldc             "There are several definitions of "
        //   437: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   440: aload           4
        //   442: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   445: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   448: aload           14
        //   450: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Lcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;
        //   453: areturn        
        //   454: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   457: athrow         
        //   458: iconst_1       
        //   459: istore          18
        //   461: goto            507
        //   464: aload           14
        //   466: invokeinterface java/util/List.size:()I
        //   471: iconst_1       
        //   472: if_icmple       507
        //   475: aload           5
        //   477: new             Ljava/lang/StringBuilder;
        //   480: dup            
        //   481: invokespecial   java/lang/StringBuilder.<init>:()V
        //   484: ldc             "There are several definitions of "
        //   486: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   489: aload           4
        //   491: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   494: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   497: aload           14
        //   499: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Lcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;
        //   502: areturn        
        //   503: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   506: athrow         
        //   507: aload           14
        //   509: iconst_0       
        //   510: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   515: checkcast       Lcom/intellij/psi/PsiElement;
        //   518: astore          19
        //   520: new             Lcom/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher;
        //   523: dup            
        //   524: aload           12
        //   526: aload           10
        //   528: aload           19
        //   530: iconst_1       
        //   531: iconst_1       
        //   532: iconst_0       
        //   533: invokespecial   com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.<init>:(Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;ZZZ)V
        //   536: astore          13
        //   538: iload           18
        //   540: ifeq            555
        //   543: aload           13
        //   545: invokevirtual   com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.processFromStart:()V
        //   548: goto            560
        //   551: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   554: athrow         
        //   555: aload           13
        //   557: invokevirtual   com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.process:()V
        //   560: aload           15
        //   562: aload           13
        //   564: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.READ:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   567: invokevirtual   com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.getInstructionsOfKind:(Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;)Ljava/util/Collection;
        //   570: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/util/Collection;)Ljava/util/List;
        //   573: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   578: pop            
        //   579: aload           16
        //   581: aload           13
        //   583: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.REFERENCE:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   586: invokevirtual   com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.getInstructionsOfKind:(Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;)Ljava/util/Collection;
        //   589: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/util/Collection;)Ljava/util/List;
        //   592: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   597: pop            
        //   598: aload           13
        //   600: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.READ_IN_BLOCK:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   603: invokevirtual   com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.getInstructionsOfKind:(Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;)Ljava/util/Collection;
        //   606: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/util/Collection;)Ljava/util/List;
        //   609: astore          20
        //   611: aload           13
        //   613: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.WRITE:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   616: invokevirtual   com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.getInstructionsOfKind:(Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;)Ljava/util/Collection;
        //   619: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/util/Collection;)Ljava/util/List;
        //   622: astore          21
        //   624: aload           13
        //   626: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.WRITE_IN_BLOCK:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   629: invokevirtual   com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.getInstructionsOfKind:(Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;)Ljava/util/Collection;
        //   632: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/util/Collection;)Ljava/util/List;
        //   635: astore          22
        //   637: aload           21
        //   639: aload           19
        //   641: invokeinterface java/util/List.remove:(Ljava/lang/Object;)Z
        //   646: pop            
        //   647: aload           16
        //   649: invokeinterface java/util/List.isEmpty:()Z
        //   654: ifne            694
        //   657: aload           5
        //   659: new             Ljava/lang/StringBuilder;
        //   662: dup            
        //   663: invokespecial   java/lang/StringBuilder.<init>:()V
        //   666: ldc             "The address of "
        //   668: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   671: aload           4
        //   673: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   676: ldc             " is taken"
        //   678: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   681: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   684: aload           16
        //   686: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Lcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;
        //   689: areturn        
        //   690: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   693: athrow         
        //   694: aload           22
        //   696: invokeinterface java/util/List.isEmpty:()Z
        //   701: ifne            739
        //   704: aload           5
        //   706: new             Ljava/lang/StringBuilder;
        //   709: dup            
        //   710: invokespecial   java/lang/StringBuilder.<init>:()V
        //   713: aload           4
        //   715: invokestatic    com/intellij/openapi/util/text/StringUtil.capitalize:(Ljava/lang/String;)Ljava/lang/String;
        //   718: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   721: ldc             " is written in the block"
        //   723: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   726: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   729: aload           22
        //   731: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Lcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;
        //   734: areturn        
        //   735: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   738: athrow         
        //   739: aload           21
        //   741: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   746: astore          23
        //   748: aload           23
        //   750: invokeinterface java/util/Iterator.hasNext:()Z
        //   755: ifeq            907
        //   758: aload           23
        //   760: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   765: checkcast       Lcom/intellij/psi/PsiElement;
        //   768: astore          24
        //   770: new             Lcom/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher;
        //   773: dup            
        //   774: aload           12
        //   776: aload           10
        //   778: aload           24
        //   780: iconst_1       
        //   781: iconst_0       
        //   782: iconst_1       
        //   783: invokespecial   com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.<init>:(Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;ZZZ)V
        //   786: astore          13
        //   788: aload           13
        //   790: invokevirtual   com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.process:()V
        //   793: aload           13
        //   795: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.READ:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   798: invokevirtual   com/jetbrains/cidr/lang/dfa/OCLocalDefinitionsSearcher.getInstructionsOfKind:(Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;)Ljava/util/Collection;
        //   801: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/util/Collection;)Ljava/util/List;
        //   804: astore          25
        //   806: aload           25
        //   808: aload           15
        //   810: invokeinterface java/util/List.retainAll:(Ljava/util/Collection;)Z
        //   815: pop            
        //   816: aload           25
        //   818: invokeinterface java/util/List.isEmpty:()Z
        //   823: ifne            904
        //   826: aload           5
        //   828: ifnull          861
        //   831: goto            838
        //   834: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   837: athrow         
        //   838: aload_1        
        //   839: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getProject:()Lcom/intellij/openapi/project/Project;
        //   844: aload           5
        //   846: aload           25
        //   848: getstatic       com/intellij/openapi/editor/colors/EditorColors.SEARCH_RESULT_ATTRIBUTES:Lcom/intellij/openapi/editor/colors/TextAttributesKey;
        //   851: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.highlightUsages:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Ljava/util/List;Lcom/intellij/openapi/editor/colors/TextAttributesKey;)V
        //   854: goto            861
        //   857: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   860: athrow         
        //   861: aload           5
        //   863: new             Ljava/lang/StringBuilder;
        //   866: dup            
        //   867: invokespecial   java/lang/StringBuilder.<init>:()V
        //   870: ldc             "There are several definitions of "
        //   872: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   875: aload           4
        //   877: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   880: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   883: iconst_2       
        //   884: anewarray       Lcom/intellij/psi/PsiElement;
        //   887: dup            
        //   888: iconst_0       
        //   889: aload           19
        //   891: aastore        
        //   892: dup            
        //   893: iconst_1       
        //   894: aload           24
        //   896: aastore        
        //   897: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //   900: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Lcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;
        //   903: areturn        
        //   904: goto            748
        //   907: aload_2        
        //   908: invokeinterface java/util/List.clear:()V
        //   913: aload_2        
        //   914: aload           15
        //   916: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   921: pop            
        //   922: aload_2        
        //   923: aload           20
        //   925: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   930: pop            
        //   931: aload_2        
        //   932: aload           19
        //   934: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   939: pop            
        //   940: aload_2        
        //   941: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   946: astore          23
        //   948: aload           23
        //   950: invokeinterface java/util/Iterator.hasNext:()Z
        //   955: ifeq            1149
        //   958: aload           23
        //   960: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   965: checkcast       Lcom/intellij/psi/PsiElement;
        //   968: astore          24
        //   970: aload           24
        //   972: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   977: astore          25
        //   979: aconst_null    
        //   980: astore          26
        //   982: aload           25
        //   984: instanceof      Lcom/jetbrains/cidr/lang/psi/OCPostfixExpression;
        //   987: ifeq            1005
        //   990: aload           25
        //   992: checkcast       Lcom/jetbrains/cidr/lang/psi/OCPostfixExpression;
        //   995: invokeinterface com/jetbrains/cidr/lang/psi/OCPostfixExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1000: astore          26
        //  1002: goto            1025
        //  1005: aload           25
        //  1007: instanceof      Lcom/jetbrains/cidr/lang/psi/OCPrefixExpression;
        //  1010: ifeq            1025
        //  1013: aload           25
        //  1015: checkcast       Lcom/jetbrains/cidr/lang/psi/OCPrefixExpression;
        //  1018: invokeinterface com/jetbrains/cidr/lang/psi/OCPrefixExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1023: astore          26
        //  1025: aload           26
        //  1027: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUSPLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1030: if_acmpeq       1108
        //  1033: aload           26
        //  1035: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUSMINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1038: if_acmpeq       1108
        //  1041: goto            1048
        //  1044: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1047: athrow         
        //  1048: aload           25
        //  1050: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //  1053: ifeq            1146
        //  1056: goto            1063
        //  1059: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1062: athrow         
        //  1063: aload           25
        //  1065: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //  1068: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //  1073: aload           24
        //  1075: if_acmpne       1146
        //  1078: goto            1085
        //  1081: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1084: athrow         
        //  1085: aload           25
        //  1087: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //  1090: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  1095: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  1098: if_acmpeq       1146
        //  1101: goto            1108
        //  1104: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1107: athrow         
        //  1108: aload           5
        //  1110: new             Ljava/lang/StringBuilder;
        //  1113: dup            
        //  1114: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1117: aload           4
        //  1119: invokestatic    com/intellij/openapi/util/text/StringUtil.capitalize:(Ljava/lang/String;)Ljava/lang/String;
        //  1122: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1125: ldc             " is accessed for writing"
        //  1127: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1130: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1133: aload           24
        //  1135: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //  1138: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Lcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;
        //  1141: areturn        
        //  1142: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1145: athrow         
        //  1146: goto            948
        //  1149: aload_2        
        //  1150: aload_2        
        //  1151: invokeinterface java/util/List.size:()I
        //  1156: iconst_1       
        //  1157: isub           
        //  1158: invokeinterface java/util/List.remove:(I)Ljava/lang/Object;
        //  1163: pop            
        //  1164: new             Ljava/util/ArrayList;
        //  1167: dup            
        //  1168: invokespecial   java/util/ArrayList.<init>:()V
        //  1171: astore          23
        //  1173: aload_2        
        //  1174: aload           23
        //  1176: invokedynamic   fun:(Ljava/util/List;)Lcom/intellij/util/Function;
        //  1181: invokestatic    com/intellij/util/containers/ContainerUtil.mapNotNull:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //  1184: astore          24
        //  1186: aload           23
        //  1188: invokeinterface java/util/List.isEmpty:()Z
        //  1193: ifne            1210
        //  1196: aload           5
        //  1198: ldc             "Can't inline usages inside the macro substitutions"
        //  1200: aload           23
        //  1202: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Lcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;
        //  1205: areturn        
        //  1206: invokestatic    com/jetbrains/cidr/lang/refactoring/inline/OCInlineLocalVarHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1209: athrow         
        //  1210: aload_2        
        //  1211: invokeinterface java/util/List.clear:()V
        //  1216: aload_2        
        //  1217: aload           24
        //  1219: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //  1224: pop            
        //  1225: aload           6
        //  1227: aload           19
        //  1229: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //  1232: aload_0        
        //  1233: aload_1        
        //  1234: aload_2        
        //  1235: aload_3        
        //  1236: aload           4
        //  1238: aload           5
        //  1240: aload           6
        //  1242: aload           7
        //  1244: iload           8
        //  1246: invokespecial   com/jetbrains/cidr/lang/refactoring/inline/OCInlineActionHandlerBase.checkValidness:(Lcom/intellij/psi/PsiNamedElement;Ljava/util/List;Lcom/intellij/psi/PsiElement;Ljava/lang/String;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/openapi/util/Ref;Ljava/util/List;Z)Ljava/lang/String;
        //  1249: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/psi/OCDeclarator;Ljava/util/List<Lcom/intellij/psi/PsiElement;>;Lcom/intellij/psi/PsiElement;Ljava/lang/String;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/openapi/util/Ref<Lcom/intellij/psi/PsiElement;>;Ljava/util/List<Ljava/lang/String;>;Z)Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  19     27     27     31     Ljava/lang/IllegalStateException;
        //  31     39     39     43     Ljava/lang/IllegalStateException;
        //  43     59     59     63     Ljava/lang/IllegalStateException;
        //  80     88     88     92     Ljava/lang/IllegalStateException;
        //  198    298    301    305    Ljava/lang/IllegalStateException;
        //  346    370    370    374    Ljava/lang/IllegalStateException;
        //  377    411    411    415    Ljava/lang/IllegalStateException;
        //  415    454    454    458    Ljava/lang/IllegalStateException;
        //  464    503    503    507    Ljava/lang/IllegalStateException;
        //  538    551    551    555    Ljava/lang/IllegalStateException;
        //  637    690    690    694    Ljava/lang/IllegalStateException;
        //  694    735    735    739    Ljava/lang/IllegalStateException;
        //  806    831    834    838    Ljava/lang/IllegalStateException;
        //  826    854    857    861    Ljava/lang/IllegalStateException;
        //  1025   1041   1044   1048   Ljava/lang/IllegalStateException;
        //  1033   1056   1059   1063   Ljava/lang/IllegalStateException;
        //  1048   1078   1081   1085   Ljava/lang/IllegalStateException;
        //  1063   1101   1104   1108   Ljava/lang/IllegalStateException;
        //  1085   1142   1142   1146   Ljava/lang/IllegalStateException;
        //  1186   1206   1206   1210   Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_1048:
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
    
    @Nullable
    private static OCControlFlowGraph a(final OCCallable ocCallable, final PsiElement psiElement) {
        final OCDataFlowAnalyzer ocDataFlowAnalyzer = new OCDataFlowAnalyzer((PsiElement)ocCallable, null);
        ocDataFlowAnalyzer.buildControlFlowGraph();
        return ocDataFlowAnalyzer.findGraph(psiElement);
    }
    
    private static List<PsiElement> a(final Collection<OCInstruction> collection) {
        final ArrayList<PsiElement> list = new ArrayList<PsiElement>();
        final Iterator<OCInstruction> iterator = collection.iterator();
        while (iterator.hasNext()) {
            final PsiElement rValue = iterator.next().getRValue();
            try {
                if (rValue == null) {
                    continue;
                }
                list.add(rValue);
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
        return list;
    }
    
    private static String a(final Editor editor, final String s, final List<PsiElement> list) {
        try {
            if (editor != null) {
                OCInlineActionHandlerBase.highlightUsages(editor.getProject(), editor, list, EditorColors.WRITE_SEARCH_RESULT_ATTRIBUTES);
                OCInlineActionHandlerBase.showHighlightRemovalStatus(editor.getProject());
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected void inlineUsage(final PsiElement psiElement, final OCDeclarator ocDeclarator, final PsiElement psiElement2, final Project project, final Map<SmartPsiElementPointer, Pair<OCSymbol, OCVisibility>> map) {
        try {
            if (!(psiElement instanceof OCReferenceExpression)) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        OCParenthesesUtils.replaceExpressionAndAppendParentheses((OCExpression)psiElement, (OCExpression)psiElement2);
    }
    
    @Override
    protected void deleteElement(final OCDeclarator ocDeclarator, final PsiElement psiElement) {
        final PsiElement parent = psiElement.getParent();
        Label_0114: {
            Label_0066: {
                Label_0033: {
                    try {
                        if (!(parent instanceof OCAssignmentExpression)) {
                            break Label_0066;
                        }
                        final PsiElement psiElement2 = parent;
                        final PsiElement psiElement3 = psiElement2.getParent();
                        final boolean b = psiElement3 instanceof OCStatement;
                        if (b) {
                            break Label_0033;
                        }
                        break Label_0033;
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    try {
                        final PsiElement psiElement2 = parent;
                        final PsiElement psiElement3 = psiElement2.getParent();
                        final boolean b = psiElement3 instanceof OCStatement;
                        if (b) {
                            OCChangeUtil.delete(parent.getParent());
                            break Label_0114;
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                }
                OCChangeUtil.replaceHandlingMacros(parent, (PsiElement)((OCAssignmentExpression)parent).getReceiverExpression());
                break Label_0114;
                try {
                    if (parent instanceof OCDeclarator) {
                        OCChangeUtil.delete((PsiElement)((OCDeclarator)parent).getInitializer());
                        break Label_0114;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            try {
                assert false : ((OCDeclarator)parent).getClass();
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            try {
                if (ReferencesSearch.search((PsiElement)ocDeclarator, ocDeclarator.getUseScope()).forEach((Processor)new CommonProcessors.FindFirstProcessor<PsiReference>() {
                    protected boolean accept(final PsiReference psiReference) {
                        return !OCElementUtil.isPartOfMacroSubstitution(psiReference.getElement());
                    }
                })) {
                    OCChangeUtil.delete((PsiElement)ocDeclarator);
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
        }
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCInlineLocalVarHandler.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
