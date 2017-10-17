// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.psi.OCExternalReference;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.jetbrains.cidr.lang.search.OCSearchUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import com.intellij.util.containers.MultiMap;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.refactoring.util.OCBindUtil;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbolImpl;
import java.util.Collections;
import java.util.ArrayList;
import com.intellij.usageView.UsageViewDescriptor;
import org.jetbrains.annotations.NotNull;
import com.intellij.usageView.UsageInfo;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import java.util.HashSet;
import java.util.HashMap;
import com.intellij.openapi.util.EmptyRunnable;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.SmartPsiElementPointer;
import com.jetbrains.cidr.lang.quickfixes.OCReleaseVariablesIntentionAction;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.psi.OCSynthesizePropertiesList;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import java.util.Map;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.refactoring.BaseRefactoringProcessor;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;

public class OCConvertMemberRefactoringProcessor<M extends OCMemberSymbol> extends BaseRefactoringProcessor
{
    private OCClassSymbol myParentClass;
    private List<M> myMembers;
    private Map<M, String> myNewMembersNames;
    private Map<M, OCInstanceVariableSymbol> myNewIvars;
    protected Map<M, PsiElement> myNewDeclarations;
    private Map<M, OCMemberSymbol> myExistingDeclarations;
    private List<OCSynthesizePropertiesList> mySynthesizes;
    private OCElementType myNewQualifierType;
    private String myCommandName;
    private OCReleaseVariablesIntentionAction myReleaseAction;
    private final PsiElement myNewQualifyingToken;
    private Map<SmartPsiElementPointer, Pair<OCSymbol, OCVisibility>> myElemsToEscalateVisibility;
    private Set<M> myMembersWithConflicts;
    private boolean myConvertUsages;
    
    private OCConvertMemberRefactoringProcessor(final OCClassSymbol myParentClass, final OCElementType myNewQualifierType) {
        super(myParentClass.getProject(), EmptyRunnable.INSTANCE);
        this.myNewIvars = new HashMap<M, OCInstanceVariableSymbol>();
        this.myElemsToEscalateVisibility = new HashMap<SmartPsiElementPointer, Pair<OCSymbol, OCVisibility>>();
        this.myMembersWithConflicts = new HashSet<M>();
        this.myParentClass = myParentClass;
        this.myNewQualifierType = myNewQualifierType;
        this.myNewQualifyingToken = OCElementFactory.create(this.myNewQualifierType, (PsiElement)this.myParentClass.getContainingOCFile());
    }
    
    public OCConvertMemberRefactoringProcessor(final OCClassSymbol ocClassSymbol, final List<M> myMembers, final Map<M, String> myNewMembersNames, final Map<M, PsiElement> myNewDeclarations, final Map<M, OCMemberSymbol> myExistingDeclarations, final List<OCSynthesizePropertiesList> mySynthesizes, final OCReleaseVariablesIntentionAction myReleaseAction, final OCElementType ocElementType, final String myCommandName, final boolean myConvertUsages) {
        this(ocClassSymbol, ocElementType);
        this.myMembers = myMembers;
        this.myNewMembersNames = myNewMembersNames;
        this.myNewDeclarations = myNewDeclarations;
        this.myExistingDeclarations = myExistingDeclarations;
        this.mySynthesizes = mySynthesizes;
        this.myReleaseAction = myReleaseAction;
        this.myCommandName = myCommandName;
        this.myConvertUsages = myConvertUsages;
    }
    
    @NotNull
    @Override
    protected UsageViewDescriptor createUsageViewDescriptor(@NotNull final UsageInfo[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "usages", "com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor", "createUsageViewDescriptor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCUsageViewDescriptor ocUsageViewDescriptor;
        try {
            ocUsageViewDescriptor = new OCUsageViewDescriptor(((OCSymbol<PsiElement>)this.myParentClass).locateDefinition(), this.getCommandName());
            if (ocUsageViewDescriptor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor", "createUsageViewDescriptor"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (UsageViewDescriptor)ocUsageViewDescriptor;
    }
    
    @NotNull
    @Override
    protected UsageInfo[] findUsages() {
        final ArrayList<ConvertUsage<M>> list = new ArrayList<ConvertUsage<M>>();
        for (final OCMemberSymbol ocMemberSymbol : this.myMembers) {
            final PsiElement locateDefinition = ocMemberSymbol.locateDefinition();
            final OCInstanceVariableSymbolImpl ocInstanceVariableSymbolImpl = new OCInstanceVariableSymbolImpl(this.myProject, this.myParentClass.getContainingFile(), 4294967296L, this.myNewMembersNames.get(ocMemberSymbol), Collections.emptyList(), this.myParentClass, ocMemberSymbol.getType(), OCVisibility.PRIVATE, null);
            try {
                this.myNewIvars.put((M)ocMemberSymbol, ocInstanceVariableSymbolImpl);
                if (locateDefinition == null || !this.a()) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            for (final PsiReference psiReference : ReferencesSearch.search(locateDefinition).findAll()) {
                list.add(new ConvertUsage<M>(psiReference, ocMemberSymbol));
                final PsiElement element = psiReference.getElement();
                try {
                    if (!(ocMemberSymbol instanceof OCPropertySymbol) || element == null) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                ocInstanceVariableSymbolImpl.updateVisibility(OCVisibility.min(ocInstanceVariableSymbolImpl.getVisibility(), OCVisibility.getVisibility(ocInstanceVariableSymbolImpl, element, null)));
            }
        }
        ConvertUsage[] array;
        try {
            array = list.toArray(new ConvertUsage[list.size()]);
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor", "findUsages"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return array;
    }
    
    private boolean a() {
        try {
            if (this.mySynthesizes == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    protected void performRefactoring(@NotNull final UsageInfo[] p0) {
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
        //    18: ldc             "usages"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "performRefactoring"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokedynamic   compare:()Ljava/util/Comparator;
        //    50: invokestatic    java/util/Arrays.sort:([Ljava/lang/Object;Ljava/util/Comparator;)V
        //    53: aload_0        
        //    54: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myParentClass:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    57: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //    62: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclarationBase;
        //    65: astore_2       
        //    66: aload_0        
        //    67: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myParentClass:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    70: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getImplementation:()Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //    75: astore_3       
        //    76: aload_0        
        //    77: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myParentClass:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    80: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //    85: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //    90: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclarationBase;
        //    93: astore          4
        //    95: aload_3        
        //    96: ifnull          113
        //    99: aload_3        
        //   100: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   103: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclarationBase;
        //   106: goto            114
        //   109: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: aconst_null    
        //   114: astore          5
        //   116: new             Lcom/jetbrains/cidr/lang/refactoring/util/OCElementsMover;
        //   119: dup            
        //   120: iconst_1       
        //   121: invokespecial   com/jetbrains/cidr/lang/refactoring/util/OCElementsMover.<init>:(Z)V
        //   124: astore          6
        //   126: new             Ljava/util/ArrayList;
        //   129: dup            
        //   130: invokespecial   java/util/ArrayList.<init>:()V
        //   133: astore          7
        //   135: new             Ljava/util/HashMap;
        //   138: dup            
        //   139: invokespecial   java/util/HashMap.<init>:()V
        //   142: astore          8
        //   144: aload_0        
        //   145: invokespecial   com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:()Z
        //   148: ifeq            253
        //   151: aload_0        
        //   152: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myMembers:Ljava/util/List;
        //   155: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   160: astore          9
        //   162: aload           9
        //   164: invokeinterface java/util/Iterator.hasNext:()Z
        //   169: ifeq            253
        //   172: aload           9
        //   174: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   179: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   182: astore          10
        //   184: aload_0        
        //   185: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myMembersWithConflicts:Ljava/util/Set;
        //   188: aload           10
        //   190: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   195: ifne            250
        //   198: aload           10
        //   200: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   205: astore          11
        //   207: aload           7
        //   209: aload           10
        //   211: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   216: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   221: pop            
        //   222: aload           7
        //   224: aload           11
        //   226: ifnull          243
        //   229: aload           11
        //   231: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   236: goto            244
        //   239: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   242: athrow         
        //   243: aconst_null    
        //   244: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   249: pop            
        //   250: goto            162
        //   253: aload_0        
        //   254: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myMembers:Ljava/util/List;
        //   257: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   262: astore          9
        //   264: aload           9
        //   266: invokeinterface java/util/Iterator.hasNext:()Z
        //   271: ifeq            335
        //   274: aload           9
        //   276: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   281: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   284: astore          10
        //   286: aload_0        
        //   287: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myExistingDeclarations:Ljava/util/Map;
        //   290: aload           10
        //   292: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   297: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   300: astore          11
        //   302: aload           8
        //   304: aload           10
        //   306: aload           11
        //   308: ifnull          325
        //   311: aload           11
        //   313: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   318: goto            326
        //   321: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   324: athrow         
        //   325: aconst_null    
        //   326: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   331: pop            
        //   332: goto            264
        //   335: aload_0        
        //   336: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myMembers:Ljava/util/List;
        //   339: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   344: astore          9
        //   346: aload           9
        //   348: invokeinterface java/util/Iterator.hasNext:()Z
        //   353: ifeq            650
        //   356: aload           9
        //   358: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   363: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   366: astore          10
        //   368: aload_0        
        //   369: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myNewDeclarations:Ljava/util/Map;
        //   372: aload           10
        //   374: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   379: checkcast       Lcom/intellij/psi/PsiElement;
        //   382: astore          11
        //   384: aload           8
        //   386: aload           10
        //   388: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   393: checkcast       Lcom/intellij/psi/PsiElement;
        //   396: astore          12
        //   398: aload_0        
        //   399: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myExistingDeclarations:Ljava/util/Map;
        //   402: aload           10
        //   404: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   409: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   412: astore          13
        //   414: aload           10
        //   416: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   419: ifeq            605
        //   422: aload_0        
        //   423: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myNewIvars:Ljava/util/Map;
        //   426: aload           10
        //   428: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   433: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   436: astore          14
        //   438: aload           12
        //   440: ifnull          521
        //   443: aload           13
        //   445: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   448: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   453: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   456: aload           14
        //   458: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   463: invokevirtual   com/jetbrains/cidr/lang/symbols/OCVisibility.ordinal:()I
        //   466: if_icmple       602
        //   469: goto            476
        //   472: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   475: athrow         
        //   476: aload_0        
        //   477: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myProject:Lcom/intellij/openapi/project/Project;
        //   480: invokestatic    com/intellij/psi/SmartPointerManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/SmartPointerManager;
        //   483: aload           12
        //   485: invokevirtual   com/intellij/psi/SmartPointerManager.createSmartPsiElementPointer:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/SmartPsiElementPointer;
        //   488: astore          15
        //   490: aload_0        
        //   491: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myElemsToEscalateVisibility:Ljava/util/Map;
        //   494: aload           15
        //   496: new             Lcom/intellij/openapi/util/Pair;
        //   499: dup            
        //   500: aload           13
        //   502: aload           14
        //   504: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   509: invokespecial   com/intellij/openapi/util/Pair.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   512: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   517: pop            
        //   518: goto            602
        //   521: aload           11
        //   523: ifnull          602
        //   526: aload           14
        //   528: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   533: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PRIVATE:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //   536: if_acmpeq       558
        //   539: goto            546
        //   542: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   545: athrow         
        //   546: aload           4
        //   548: ifnonnull       566
        //   551: goto            558
        //   554: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   557: athrow         
        //   558: aload_2        
        //   559: goto            568
        //   562: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   565: athrow         
        //   566: aload           4
        //   568: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   571: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   574: astore          15
        //   576: aload_0        
        //   577: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myNewDeclarations:Ljava/util/Map;
        //   580: aload           10
        //   582: aload           6
        //   584: aload           15
        //   586: aload           14
        //   588: aload           11
        //   590: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   593: invokevirtual   com/jetbrains/cidr/lang/refactoring/util/OCElementsMover.addInstanceVariable:(Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;Lcom/jetbrains/cidr/lang/psi/OCDeclaration;)Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   596: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   601: pop            
        //   602: goto            647
        //   605: aload           12
        //   607: ifnonnull       647
        //   610: aload           11
        //   612: ifnull          647
        //   615: goto            622
        //   618: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   621: athrow         
        //   622: aload_0        
        //   623: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myNewDeclarations:Ljava/util/Map;
        //   626: aload           10
        //   628: aload_2        
        //   629: aload           11
        //   631: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.add:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   634: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   639: pop            
        //   640: goto            647
        //   643: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   646: athrow         
        //   647: goto            346
        //   650: aload_1        
        //   651: astore          9
        //   653: aload           9
        //   655: arraylength    
        //   656: istore          10
        //   658: iconst_0       
        //   659: istore          11
        //   661: iload           11
        //   663: iload           10
        //   665: if_icmpge       719
        //   668: aload           9
        //   670: iload           11
        //   672: aaload         
        //   673: astore          12
        //   675: aload           12
        //   677: invokevirtual   com/intellij/usageView/UsageInfo.getElement:()Lcom/intellij/psi/PsiElement;
        //   680: astore          13
        //   682: aload_0        
        //   683: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myNewMembersNames:Ljava/util/Map;
        //   686: aload           12
        //   688: checkcast       Lcom/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor$ConvertUsage;
        //   691: invokevirtual   com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor$ConvertUsage.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   694: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   699: checkcast       Ljava/lang/String;
        //   702: astore          14
        //   704: aload_0        
        //   705: aload           13
        //   707: aload           14
        //   709: invokespecial   com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Lcom/intellij/psi/PsiElement;
        //   712: pop            
        //   713: iinc            11, 1
        //   716: goto            661
        //   719: aload           5
        //   721: ifnull          782
        //   724: aload_0        
        //   725: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.mySynthesizes:Ljava/util/List;
        //   728: ifnull          782
        //   731: goto            738
        //   734: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   737: athrow         
        //   738: aload_0        
        //   739: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.mySynthesizes:Ljava/util/List;
        //   742: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   747: astore          9
        //   749: aload           9
        //   751: invokeinterface java/util/Iterator.hasNext:()Z
        //   756: ifeq            782
        //   759: aload           9
        //   761: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   766: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSynthesizePropertiesList;
        //   769: astore          10
        //   771: aload           5
        //   773: aload           10
        //   775: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.add:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   778: pop            
        //   779: goto            749
        //   782: aload_2        
        //   783: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclarationBase.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   788: astore          9
        //   790: aload           7
        //   792: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   797: astore          10
        //   799: aload           10
        //   801: invokeinterface java/util/Iterator.hasNext:()Z
        //   806: ifeq            858
        //   809: aload           10
        //   811: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   816: checkcast       Lcom/intellij/psi/PsiElement;
        //   819: astore          11
        //   821: aload           11
        //   823: ifnull          855
        //   826: aload           11
        //   828: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //   833: ifeq            855
        //   836: goto            843
        //   839: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   842: athrow         
        //   843: aload           11
        //   845: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   848: goto            855
        //   851: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   854: athrow         
        //   855: goto            799
        //   858: aload_0        
        //   859: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myReleaseAction:Lcom/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction;
        //   862: ifnull          948
        //   865: aload_0        
        //   866: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myProject:Lcom/intellij/openapi/project/Project;
        //   869: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   872: aload           9
        //   874: invokevirtual   com/intellij/psi/PsiDocumentManager.getDocument:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/editor/Document;
        //   877: astore          10
        //   879: aload_0        
        //   880: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myProject:Lcom/intellij/openapi/project/Project;
        //   883: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   886: aload           10
        //   888: invokevirtual   com/intellij/psi/PsiDocumentManager.doPostponedOperationsAndUnblockDocument:(Lcom/intellij/openapi/editor/Document;)V
        //   891: aload           9
        //   893: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   896: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getAssociatedFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   901: astore          11
        //   903: aload           11
        //   905: ifnull          934
        //   908: aload_0        
        //   909: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myProject:Lcom/intellij/openapi/project/Project;
        //   912: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   915: aload           11
        //   917: invokevirtual   com/intellij/psi/PsiDocumentManager.getDocument:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/editor/Document;
        //   920: astore          10
        //   922: aload_0        
        //   923: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myProject:Lcom/intellij/openapi/project/Project;
        //   926: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   929: aload           10
        //   931: invokevirtual   com/intellij/psi/PsiDocumentManager.doPostponedOperationsAndUnblockDocument:(Lcom/intellij/openapi/editor/Document;)V
        //   934: aload_0        
        //   935: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myReleaseAction:Lcom/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction;
        //   938: aload_0        
        //   939: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myProject:Lcom/intellij/openapi/project/Project;
        //   942: aconst_null    
        //   943: aload           9
        //   945: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.invoke:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)V
        //   948: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  95     109    109    113    Ljava/lang/IllegalArgumentException;
        //  207    239    239    243    Ljava/lang/IllegalArgumentException;
        //  302    321    321    325    Ljava/lang/IllegalArgumentException;
        //  438    469    472    476    Ljava/lang/IllegalArgumentException;
        //  521    539    542    546    Ljava/lang/IllegalArgumentException;
        //  526    551    554    558    Ljava/lang/IllegalArgumentException;
        //  546    562    562    566    Ljava/lang/IllegalArgumentException;
        //  605    615    618    622    Ljava/lang/IllegalArgumentException;
        //  610    640    643    647    Ljava/lang/IllegalArgumentException;
        //  719    731    734    738    Ljava/lang/IllegalArgumentException;
        //  821    836    839    843    Ljava/lang/IllegalArgumentException;
        //  826    848    851    855    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0546:
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
    protected void performPsiSpoilingRefactoring() {
        OCBindUtil.escalateVisibilities(this.myProject, this.myElemsToEscalateVisibility, new VirtualFile[0]);
    }
    
    private PsiElement a(final PsiElement p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isPartOfMacroSubstitution:(Lcom/intellij/psi/PsiElement;)Z
        //     4: ifeq            13
        //     7: aload_1        
        //     8: areturn        
        //     9: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    12: athrow         
        //    13: aload_1        
        //    14: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    17: ifeq            160
        //    20: aload_0        
        //    21: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myConvertUsages:Z
        //    24: ifne            51
        //    27: goto            34
        //    30: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    33: athrow         
        //    34: aload_1        
        //    35: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    38: aload_2        
        //    39: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.setName:(Ljava/lang/String;)Lcom/intellij/psi/PsiElement;
        //    44: pop            
        //    45: aload_1        
        //    46: areturn        
        //    47: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: aload_1        
        //    52: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    55: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    60: astore_3       
        //    61: aload_0        
        //    62: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myNewQualifierType:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    65: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    68: if_acmpne       121
        //    71: aload_3        
        //    72: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    75: ifeq            121
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: aload_3        
        //    86: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    89: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getSelfSuperToken:()Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //    94: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken.SELF:Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //    97: if_acmpne       121
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: aload_1        
        //   108: aload_2        
        //   109: aload_1        
        //   110: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   113: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   116: areturn        
        //   117: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   120: athrow         
        //   121: aload_1        
        //   122: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   125: aload_2        
        //   126: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.setName:(Ljava/lang/String;)Lcom/intellij/psi/PsiElement;
        //   131: pop            
        //   132: aload_1        
        //   133: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   136: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifyingToken:()Lcom/intellij/lang/ASTNode;
        //   141: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   146: astore          4
        //   148: aload           4
        //   150: aload_0        
        //   151: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myNewQualifyingToken:Lcom/intellij/psi/PsiElement;
        //   154: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   157: pop            
        //   158: aload_1        
        //   159: areturn        
        //   160: aload_1        
        //   161: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   164: ifeq            288
        //   167: aload_0        
        //   168: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myConvertUsages:Z
        //   171: ifne            198
        //   174: goto            181
        //   177: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   180: athrow         
        //   181: aload_1        
        //   182: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   185: aload_2        
        //   186: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.setNameOfIdentifier:(Ljava/lang/String;)Lcom/intellij/psi/PsiElement;
        //   191: pop            
        //   192: aload_1        
        //   193: areturn        
        //   194: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   197: athrow         
        //   198: aload_1        
        //   199: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   204: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   207: ifeq            225
        //   210: aload_1        
        //   211: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   216: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   219: aconst_null    
        //   220: areturn        
        //   221: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   224: athrow         
        //   225: getstatic       com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.$assertionsDisabled:Z
        //   228: ifne            260
        //   231: aload_0        
        //   232: getfield        com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.myNewQualifierType:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   235: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   238: if_acmpeq       260
        //   241: goto            248
        //   244: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   247: athrow         
        //   248: new             Ljava/lang/AssertionError;
        //   251: dup            
        //   252: invokespecial   java/lang/AssertionError.<init>:()V
        //   255: athrow         
        //   256: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   259: athrow         
        //   260: aload_1        
        //   261: new             Ljava/lang/StringBuilder;
        //   264: dup            
        //   265: invokespecial   java/lang/StringBuilder.<init>:()V
        //   268: ldc             "self."
        //   270: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   273: aload_2        
        //   274: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   277: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   280: aload_1        
        //   281: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   284: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   287: areturn        
        //   288: aload_1        
        //   289: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   292: ifeq            629
        //   295: aload_1        
        //   296: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   299: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArgumentExpressions:()Ljava/util/List;
        //   304: astore_3       
        //   305: aload_1        
        //   306: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   309: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   314: astore          4
        //   316: aload_3        
        //   317: invokeinterface java/util/List.isEmpty:()Z
        //   322: ifeq            425
        //   325: aload           4
        //   327: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   330: ifeq            377
        //   333: goto            340
        //   336: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   339: athrow         
        //   340: aload           4
        //   342: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   345: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getSelfSuperToken:()Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //   350: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken.SELF:Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //   353: if_acmpne       377
        //   356: goto            363
        //   359: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   362: athrow         
        //   363: aload_1        
        //   364: aload_2        
        //   365: aload_1        
        //   366: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   369: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   372: areturn        
        //   373: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   376: athrow         
        //   377: new             Ljava/lang/StringBuilder;
        //   380: dup            
        //   381: invokespecial   java/lang/StringBuilder.<init>:()V
        //   384: ldc             "a->"
        //   386: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   389: aload_2        
        //   390: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   393: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   396: aload_1        
        //   397: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   400: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   403: astore          5
        //   405: aload           5
        //   407: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   412: aload           4
        //   414: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   417: pop            
        //   418: aload_1        
        //   419: aload           5
        //   421: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   424: areturn        
        //   425: getstatic       com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.$assertionsDisabled:Z
        //   428: ifne            460
        //   431: aload_3        
        //   432: invokeinterface java/util/List.size:()I
        //   437: iconst_1       
        //   438: if_icmpeq       460
        //   441: goto            448
        //   444: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   447: athrow         
        //   448: new             Ljava/lang/AssertionError;
        //   451: dup            
        //   452: invokespecial   java/lang/AssertionError.<init>:()V
        //   455: athrow         
        //   456: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   459: athrow         
        //   460: aload           4
        //   462: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   465: ifeq            547
        //   468: aload           4
        //   470: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   473: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getSelfSuperToken:()Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //   478: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken.SELF:Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //   481: if_acmpne       547
        //   484: goto            491
        //   487: invokestatic    com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   490: athrow         
        //   491: new             Ljava/lang/StringBuilder;
        //   494: dup            
        //   495: invokespecial   java/lang/StringBuilder.<init>:()V
        //   498: aload_2        
        //   499: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   502: ldc             "=a"
        //   504: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   507: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   510: aload_1        
        //   511: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   514: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   517: astore          5
        //   519: aload           5
        //   521: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getSourceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   526: aload_3        
        //   527: iconst_0       
        //   528: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   533: checkcast       Lcom/intellij/psi/PsiElement;
        //   536: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   539: pop            
        //   540: aload_1        
        //   541: aload           5
        //   543: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   546: areturn        
        //   547: new             Ljava/lang/StringBuilder;
        //   550: dup            
        //   551: invokespecial   java/lang/StringBuilder.<init>:()V
        //   554: ldc             "a->"
        //   556: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   559: aload_2        
        //   560: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   563: ldc             "=a"
        //   565: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   568: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   571: aload_1        
        //   572: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   575: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   578: astore          5
        //   580: aload           5
        //   582: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getSourceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   587: aload_3        
        //   588: iconst_0       
        //   589: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   594: checkcast       Lcom/intellij/psi/PsiElement;
        //   597: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   600: pop            
        //   601: aload           5
        //   603: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   608: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   611: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   616: aload           4
        //   618: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   621: pop            
        //   622: aload_1        
        //   623: aload           5
        //   625: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   628: areturn        
        //   629: aconst_null    
        //   630: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      9      9      13     Ljava/lang/IllegalArgumentException;
        //  13     27     30     34     Ljava/lang/IllegalArgumentException;
        //  20     47     47     51     Ljava/lang/IllegalArgumentException;
        //  61     78     81     85     Ljava/lang/IllegalArgumentException;
        //  71     100    103    107    Ljava/lang/IllegalArgumentException;
        //  85     117    117    121    Ljava/lang/IllegalArgumentException;
        //  160    174    177    181    Ljava/lang/IllegalArgumentException;
        //  167    194    194    198    Ljava/lang/IllegalArgumentException;
        //  198    221    221    225    Ljava/lang/IllegalArgumentException;
        //  225    241    244    248    Ljava/lang/IllegalArgumentException;
        //  231    256    256    260    Ljava/lang/IllegalArgumentException;
        //  316    333    336    340    Ljava/lang/IllegalArgumentException;
        //  325    356    359    363    Ljava/lang/IllegalArgumentException;
        //  340    373    373    377    Ljava/lang/IllegalArgumentException;
        //  425    441    444    448    Ljava/lang/IllegalArgumentException;
        //  431    456    456    460    Ljava/lang/IllegalArgumentException;
        //  460    484    487    491    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0085:
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
    protected boolean preprocessUsages(@NotNull final Ref<UsageInfo[]> ref) {
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "refUsages", "com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor", "preprocessUsages"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!this.a()) {
                return super.preprocessUsages(ref);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final UsageInfo[] array = (UsageInfo[])ref.get();
        final MultiMap multiMap = new MultiMap();
        final HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        for (final OCMemberSymbol ocMemberSymbol : this.myMembers) {
            final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
            final CommonProcessors.FindFirstProcessor findFirstProcessor2 = new CommonProcessors.FindFirstProcessor();
            Label_0378: {
                Label_0350: {
                    Label_0261: {
                        Label_0233: {
                            Label_0168: {
                                try {
                                    if (!(ocMemberSymbol instanceof OCPropertySymbol)) {
                                        break Label_0233;
                                    }
                                    final OCMemberSymbol ocMemberSymbol2 = ocMemberSymbol;
                                    final OCPropertySymbol ocPropertySymbol = (OCPropertySymbol)ocMemberSymbol2;
                                    final Object o = findFirstProcessor;
                                    final boolean b = false;
                                    final boolean b2 = ocPropertySymbol.processAccessorMethods((Processor<? super OCMethodSymbol>)o, b);
                                    if (!b2) {
                                        break Label_0168;
                                    }
                                    break Label_0233;
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw a(ex3);
                                }
                                try {
                                    final OCMemberSymbol ocMemberSymbol2 = ocMemberSymbol;
                                    final OCPropertySymbol ocPropertySymbol = (OCPropertySymbol)ocMemberSymbol2;
                                    final Object o = findFirstProcessor;
                                    final boolean b = false;
                                    final boolean b2 = ocPropertySymbol.processAccessorMethods((Processor<? super OCMethodSymbol>)o, b);
                                    if (!b2) {
                                        hashMap.put(ocMemberSymbol.getNameWithKindUppercase() + " has custom accessor methods", ((OCMethodSymbol)findFirstProcessor.getFoundValue()).locateDefinition());
                                        this.myMembersWithConflicts.add((M)ocMemberSymbol);
                                    }
                                }
                                catch (IllegalArgumentException ex4) {
                                    throw a(ex4);
                                }
                            }
                            try {
                                if (!(ocMemberSymbol instanceof OCPropertySymbol)) {
                                    break Label_0350;
                                }
                                final OCMemberSymbol ocMemberSymbol3 = ocMemberSymbol;
                                final Object o2 = findFirstProcessor2;
                                final boolean b3 = true;
                                final boolean b4 = false;
                                final boolean b5 = false;
                                final boolean b6 = OCSearchUtil.processMembersHierarchy(ocMemberSymbol3, (com.intellij.util.Processor<? super OCMemberSymbol>)o2, b3, b4, b5);
                                if (!b6) {
                                    break Label_0261;
                                }
                                break Label_0350;
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                        }
                        try {
                            final OCMemberSymbol ocMemberSymbol3 = ocMemberSymbol;
                            final Object o2 = findFirstProcessor2;
                            final boolean b3 = true;
                            final boolean b4 = false;
                            final boolean b5 = false;
                            final boolean b6 = OCSearchUtil.processMembersHierarchy(ocMemberSymbol3, (com.intellij.util.Processor<? super OCMemberSymbol>)o2, b3, b4, b5);
                            if (!b6) {
                                hashMap.put(ocMemberSymbol.getNameWithKindUppercase() + " overrides the property from the " + ((OCMemberSymbol)findFirstProcessor2.getFoundValue()).getParent().getNameWithKindLowercase(), ((OCMemberSymbol)findFirstProcessor2.getFoundValue()).locateDefinition());
                                this.myMembersWithConflicts.add((M)ocMemberSymbol);
                                continue;
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                    }
                    try {
                        if (!(ocMemberSymbol instanceof OCPropertySymbol)) {
                            continue;
                        }
                        final OCMemberSymbol ocMemberSymbol4 = ocMemberSymbol;
                        final Object o3 = findFirstProcessor2;
                        final boolean b7 = false;
                        final boolean b8 = true;
                        final boolean b9 = false;
                        final boolean b10 = OCSearchUtil.processMembersHierarchy(ocMemberSymbol4, (com.intellij.util.Processor<? super OCMemberSymbol>)o3, b7, b8, b9);
                        if (!b10) {
                            break Label_0378;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                }
                try {
                    final OCMemberSymbol ocMemberSymbol4 = ocMemberSymbol;
                    final Object o3 = findFirstProcessor2;
                    final boolean b7 = false;
                    final boolean b8 = true;
                    final boolean b9 = false;
                    final boolean b10 = OCSearchUtil.processMembersHierarchy(ocMemberSymbol4, (com.intellij.util.Processor<? super OCMemberSymbol>)o3, b7, b8, b9);
                    if (b10) {
                        continue;
                    }
                    hashMap.put(ocMemberSymbol.getNameWithKindUppercase() + " is overridden in the " + ((OCMemberSymbol)findFirstProcessor2.getFoundValue()).getParent().getNameWithKindLowercase(), ((OCMemberSymbol)findFirstProcessor2.getFoundValue()).locateDefinition());
                    this.myMembersWithConflicts.add((M)ocMemberSymbol);
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
            }
        }
        for (final UsageInfo usageInfo : array) {
            final PsiElement element = usageInfo.getElement();
            final OCMemberSymbol symbol = ((ConvertUsage<OCMemberSymbol>)usageInfo).getSymbol();
            Label_0717: {
                try {
                    if (element instanceof OCSelectorExpression) {
                        hashMap.put(symbol.getNameWithKindUppercase() + " has @selector references", element);
                        this.myMembersWithConflicts.add((M)symbol);
                        break Label_0717;
                    }
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
                if (usageInfo.getReference() instanceof OCExternalReference) {
                    hashMap.put(symbol.getNameWithKindUppercase() + " has " + ((OCExternalReference)usageInfo.getReference()).getExternalComponentName() + " references", element);
                    this.myMembersWithConflicts.add((M)symbol);
                }
                else {
                    Label_0690: {
                        try {
                            if (element == null) {
                                break Label_0717;
                            }
                            final String s = "Swift";
                            final PsiElement psiElement = element;
                            final Language language = psiElement.getLanguage();
                            final String s2 = language.getDisplayName();
                            final boolean b11 = s.equals(s2);
                            if (b11) {
                                break Label_0690;
                            }
                            break Label_0717;
                        }
                        catch (IllegalArgumentException ex10) {
                            throw a(ex10);
                        }
                        try {
                            final String s = "Swift";
                            final PsiElement psiElement = element;
                            final Language language = psiElement.getLanguage();
                            final String s2 = language.getDisplayName();
                            final boolean b11 = s.equals(s2);
                            if (b11) {
                                multiMap.putValue((Object)element, (Object)"Property is accessed from Swift");
                                this.myMembersWithConflicts.add((M)symbol);
                            }
                        }
                        catch (IllegalArgumentException ex11) {
                            throw a(ex11);
                        }
                    }
                }
            }
        }
        for (final String s3 : hashMap.keySet()) {
            multiMap.putValue(hashMap.get(s3), (Object)s3);
        }
        return this.showConflicts((MultiMap<PsiElement, String>)multiMap, array);
    }
    
    @Override
    protected String getCommandName() {
        return this.myCommandName;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCConvertMemberRefactoringProcessor.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class ConvertUsage<M extends OCMemberSymbol> extends UsageInfo
    {
        private M mySymbol;
        
        public ConvertUsage(@NotNull final PsiReference psiReference, final M mySymbol) {
            if (psiReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor$ConvertUsage", "<init>"));
            }
            super(psiReference);
            this.mySymbol = mySymbol;
        }
        
        public M getSymbol() {
            return this.mySymbol;
        }
    }
}
