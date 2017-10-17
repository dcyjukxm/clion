// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.intellij.refactoring.changeSignature.ParameterInfo;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.lang.Language;
import java.util.Collections;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCCallable;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.changeSignature.ChangeInfo;

public abstract class OCChangeInfo implements ChangeInfo
{
    private PsiElement myContext;
    private OCParameterInfo[] myNewParameters;
    private OCMethodDescriptor myOldMethod;
    private List<OCCallable> myNewMethods;
    private OCCallable myNewMethod;
    private String myNewName;
    private String myNewReturnType;
    private OCClassDeclaration myNewContainerClass;
    private OCCallableKind myNewCallableKind;
    private String myNewSignature;
    private String mySelfParameterName;
    private boolean myChangeUsages;
    private OCGeneratedInfo myGeneratedInfo;
    private boolean myParameterSetOrOrderChanged;
    private boolean myReturnTypeChanged;
    private boolean myChangeAncestors;
    
    public OCChangeInfo(final PsiElement myContext, final OCMethodDescriptor myOldMethod, final String myNewName, final OCParameterInfo[] myNewParameters, final String myNewReturnType, final OCClassDeclaration myNewContainerClass, final OCCallableKind myNewCallableKind, final String myNewSignature, final boolean myChangeUsages, final boolean myChangeAncestors, final OCGeneratedInfo myGeneratedInfo) {
        this.myContext = myContext;
        this.myOldMethod = myOldMethod;
        this.myNewName = myNewName;
        this.myNewReturnType = myNewReturnType;
        this.myNewContainerClass = myNewContainerClass;
        this.myNewSignature = myNewSignature;
        this.myNewParameters = myNewParameters;
        this.myNewCallableKind = myNewCallableKind;
        this.myChangeUsages = myChangeUsages;
        this.myChangeAncestors = myChangeAncestors;
        this.myGeneratedInfo = myGeneratedInfo;
        for (final OCParameterInfo ocParameterInfo : myNewParameters) {
            try {
                if (ocParameterInfo.isSelfParameter()) {
                    this.mySelfParameterName = ocParameterInfo.getName();
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
        this.myParameterSetOrOrderChanged = this.b();
        this.myReturnTypeChanged = this.a();
    }
    
    public boolean isParameterSetOrOrderChanged() {
        return this.myParameterSetOrOrderChanged;
    }
    
    private boolean b() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myNewCallableKind:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //     4: aload_0        
        //     5: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myOldMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //     8: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getCallableKind:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //    11: if_acmpeq       20
        //    14: iconst_1       
        //    15: ireturn        
        //    16: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    19: athrow         
        //    20: aload_0        
        //    21: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myOldMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //    24: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getParametersCount:()I
        //    27: ifeq            76
        //    30: aload_0        
        //    31: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myOldMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //    34: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getMethodSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    37: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    40: ifeq            84
        //    43: goto            50
        //    46: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    49: athrow         
        //    50: aload_0        
        //    51: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myOldMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //    54: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getMethodSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    57: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    60: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    63: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.hasNoParameters:()Z
        //    66: ifeq            84
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    75: athrow         
        //    76: iconst_1       
        //    77: goto            85
        //    80: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    83: athrow         
        //    84: iconst_0       
        //    85: istore_1       
        //    86: aload_0        
        //    87: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myNewParameters:[Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //    90: arraylength    
        //    91: ifeq            132
        //    94: aload_0        
        //    95: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myNewParameters:[Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //    98: arraylength    
        //    99: iconst_1       
        //   100: if_icmpne       140
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   109: athrow         
        //   110: aload_0        
        //   111: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myNewParameters:[Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   114: iconst_0       
        //   115: aaload         
        //   116: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   119: instanceof      Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   122: ifeq            140
        //   125: goto            132
        //   128: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   131: athrow         
        //   132: iconst_1       
        //   133: goto            141
        //   136: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   139: athrow         
        //   140: iconst_0       
        //   141: istore_2       
        //   142: iload_1        
        //   143: iload_2        
        //   144: if_icmpeq       153
        //   147: iconst_1       
        //   148: ireturn        
        //   149: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   152: athrow         
        //   153: aload_0        
        //   154: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myNewParameters:[Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   157: arraylength    
        //   158: aload_0        
        //   159: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myOldMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //   162: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getParametersCount:()I
        //   165: if_icmpeq       174
        //   168: iconst_1       
        //   169: ireturn        
        //   170: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   173: athrow         
        //   174: aload_0        
        //   175: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myOldMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //   178: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getMethod:()Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   181: astore_3       
        //   182: aload_3        
        //   183: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   186: ifeq            228
        //   189: aload_0        
        //   190: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myNewSignature:Ljava/lang/String;
        //   193: ldc             ":"
        //   195: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   198: aload_3        
        //   199: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   202: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getSelector:()Ljava/lang/String;
        //   207: ldc             ":"
        //   209: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   212: if_icmpeq       228
        //   215: goto            222
        //   218: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   221: athrow         
        //   222: iconst_1       
        //   223: ireturn        
        //   224: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   227: athrow         
        //   228: iconst_0       
        //   229: istore          4
        //   231: iload           4
        //   233: aload_0        
        //   234: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myNewParameters:[Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   237: arraylength    
        //   238: if_icmpge       342
        //   241: aload_0        
        //   242: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myNewParameters:[Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   245: iload           4
        //   247: aaload         
        //   248: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getOldIndex:()I
        //   251: iload           4
        //   253: if_icmpeq       269
        //   256: goto            263
        //   259: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   262: athrow         
        //   263: iconst_1       
        //   264: ireturn        
        //   265: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   268: athrow         
        //   269: aload_0        
        //   270: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myNewParameters:[Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   273: iload           4
        //   275: aaload         
        //   276: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getSelector:()Ljava/lang/String;
        //   279: invokevirtual   java/lang/String.isEmpty:()Z
        //   282: ifne            336
        //   285: aload_3        
        //   286: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   289: ifeq            336
        //   292: goto            299
        //   295: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   298: athrow         
        //   299: aload_3        
        //   300: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getParameters:()Ljava/util/List;
        //   305: iload           4
        //   307: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   312: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //   315: invokeinterface com/jetbrains/cidr/lang/psi/OCMethodSelectorPart.getSelectorIdentifier:()Lcom/intellij/psi/PsiElement;
        //   320: ifnonnull       336
        //   323: goto            330
        //   326: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   329: athrow         
        //   330: iconst_1       
        //   331: ireturn        
        //   332: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   335: athrow         
        //   336: iinc            4, 1
        //   339: goto            231
        //   342: iconst_0       
        //   343: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      16     16     20     Ljava/lang/IllegalStateException;
        //  20     43     46     50     Ljava/lang/IllegalStateException;
        //  30     69     72     76     Ljava/lang/IllegalStateException;
        //  50     80     80     84     Ljava/lang/IllegalStateException;
        //  86     103    106    110    Ljava/lang/IllegalStateException;
        //  94     125    128    132    Ljava/lang/IllegalStateException;
        //  110    136    136    140    Ljava/lang/IllegalStateException;
        //  142    149    149    153    Ljava/lang/IllegalStateException;
        //  153    170    170    174    Ljava/lang/IllegalStateException;
        //  182    215    218    222    Ljava/lang/IllegalStateException;
        //  189    224    224    228    Ljava/lang/IllegalStateException;
        //  231    256    259    263    Ljava/lang/IllegalStateException;
        //  241    265    265    269    Ljava/lang/IllegalStateException;
        //  269    292    295    299    Ljava/lang/IllegalStateException;
        //  285    323    326    330    Ljava/lang/IllegalStateException;
        //  299    332    332    336    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0050:
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
    
    public boolean isParameterTypesChanged() {
        return true;
    }
    
    public boolean isParameterNamesChanged() {
        return true;
    }
    
    public boolean isReturnTypeChanged() {
        return this.myReturnTypeChanged;
    }
    
    private boolean a() {
        try {
            if (!this.myNewReturnType.replaceAll(" ", "").equals(this.myOldMethod.getReturnTypeText(this.myContext).replaceAll(" ", ""))) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean isNameChanged() {
        try {
            if (!this.myNewName.equals(this.myOldMethod.getName())) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean isGenerateDelegate() {
        return false;
    }
    
    @NotNull
    public OCParameterInfo[] getNewParameters() {
        OCParameterInfo[] myNewParameters;
        try {
            myNewParameters = this.myNewParameters;
            if (myNewParameters == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo", "getNewParameters"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myNewParameters;
    }
    
    public OCCallableKind getNewCallableKind() {
        return this.myNewCallableKind;
    }
    
    public OCMethodDescriptor getOldMethodDescriptor() {
        return this.myOldMethod;
    }
    
    public OCCallable getMethod() {
        try {
            if (this.myNewMethod != null) {
                return this.myNewMethod;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return this.myOldMethod.getMethod();
    }
    
    public void setNewMethod(final OCCallable myNewMethod) {
        this.myNewMethod = myNewMethod;
    }
    
    public void addNewMethod(final OCCallable ocCallable) {
        try {
            if (this.myNewMethods == null) {
                this.myNewMethods = new ArrayList<OCCallable>();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.myNewMethods.add(ocCallable);
    }
    
    public List<OCCallable> getNewMethods() {
        try {
            if (this.myNewMethods == null) {
                final List<OCCallable> list = Collections.emptyList();
                return (List<OCCallable>)list;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final List<OCCallable> list = this.myNewMethods;
        return (List<OCCallable>)list;
    }
    
    public OCClassDeclaration getNewContainerClass() {
        return this.myNewContainerClass;
    }
    
    public boolean isChangeUsages() {
        return this.myChangeUsages;
    }
    
    public boolean isChangeAncestors() {
        return this.myChangeAncestors;
    }
    
    public OCGeneratedInfo getGenerated() {
        return this.myGeneratedInfo;
    }
    
    public String getNewReturnType() {
        return this.myNewReturnType;
    }
    
    public String getNewName() {
        return this.myNewName;
    }
    
    public String getNewSignature() {
        return this.myNewSignature;
    }
    
    public abstract String getNewInheritedSignature(final OCCallable p0);
    
    public String getNewParameterName(final boolean p0, final OCCallable p1, final OCParameterInfo p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getParameters:()Ljava/util/List;
        //     6: astore          4
        //     8: iload_1        
        //     9: ifeq            97
        //    12: aload_3        
        //    13: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getOldIndex:()I
        //    16: iflt            97
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    25: athrow         
        //    26: aload_3        
        //    27: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getOldIndex:()I
        //    30: aload_0        
        //    31: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myOldMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //    34: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getParametersCount:()I
        //    37: if_icmpge       97
        //    40: goto            47
        //    43: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    46: athrow         
        //    47: aload_3        
        //    48: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    51: instanceof      Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //    54: ifne            97
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    63: athrow         
        //    64: aload           4
        //    66: ifnull          97
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    75: athrow         
        //    76: aload_3        
        //    77: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getOldIndex:()I
        //    80: aload           4
        //    82: invokeinterface java/util/List.size:()I
        //    87: if_icmplt       106
        //    90: goto            97
        //    93: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    96: athrow         
        //    97: aload_3        
        //    98: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getName:()Ljava/lang/String;
        //   101: areturn        
        //   102: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   105: athrow         
        //   106: aload_0        
        //   107: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.myOldMethod:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //   110: aload_3        
        //   111: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getOldIndex:()I
        //   114: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getParameter:(I)Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo;
        //   117: astore          5
        //   119: aload           4
        //   121: aload_3        
        //   122: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getOldIndex:()I
        //   125: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   130: checkcast       Lcom/intellij/psi/PsiNamedElement;
        //   133: astore          6
        //   135: aload           5
        //   137: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getName:()Ljava/lang/String;
        //   140: astore          7
        //   142: aload           6
        //   144: invokeinterface com/intellij/psi/PsiNamedElement.getName:()Ljava/lang/String;
        //   149: astore          8
        //   151: aload           8
        //   153: ifnonnull       165
        //   156: aload_3        
        //   157: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getName:()Ljava/lang/String;
        //   160: areturn        
        //   161: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   164: athrow         
        //   165: aload           7
        //   167: aload           8
        //   169: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   172: ifne            212
        //   175: aload           7
        //   177: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.isParameterNameEmpty:(Ljava/lang/String;)Z
        //   180: ifeq            205
        //   183: goto            190
        //   186: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   189: athrow         
        //   190: aload           8
        //   192: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.isParameterNameEmpty:(Ljava/lang/String;)Z
        //   195: ifne            212
        //   198: goto            205
        //   201: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   204: athrow         
        //   205: aload           8
        //   207: areturn        
        //   208: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   211: athrow         
        //   212: aload_2        
        //   213: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   218: ifnull          268
        //   221: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   224: aload_3        
        //   225: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getName:()Ljava/lang/String;
        //   228: aload_2        
        //   229: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   234: aload_2        
        //   235: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getProject:()Lcom/intellij/openapi/project/Project;
        //   240: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isUniqueInScope:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Ljava/lang/String;Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/project/Project;)Z
        //   243: ifne            268
        //   246: goto            253
        //   249: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   252: athrow         
        //   253: aload           8
        //   255: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.isParameterNameEmpty:(Ljava/lang/String;)Z
        //   258: ifeq            277
        //   261: goto            268
        //   264: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   267: athrow         
        //   268: aload_3        
        //   269: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo.getName:()Ljava/lang/String;
        //   272: areturn        
        //   273: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   276: athrow         
        //   277: aload           8
        //   279: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  8      19     22     26     Ljava/lang/IllegalStateException;
        //  12     40     43     47     Ljava/lang/IllegalStateException;
        //  26     57     60     64     Ljava/lang/IllegalStateException;
        //  47     69     72     76     Ljava/lang/IllegalStateException;
        //  64     90     93     97     Ljava/lang/IllegalStateException;
        //  76     102    102    106    Ljava/lang/IllegalStateException;
        //  151    161    161    165    Ljava/lang/IllegalStateException;
        //  165    183    186    190    Ljava/lang/IllegalStateException;
        //  175    198    201    205    Ljava/lang/IllegalStateException;
        //  190    208    208    212    Ljava/lang/IllegalStateException;
        //  212    246    249    253    Ljava/lang/IllegalStateException;
        //  221    261    264    268    Ljava/lang/IllegalStateException;
        //  253    273    273    277    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
    
    public String getSelfParameterName() {
        return this.mySelfParameterName;
    }
    
    public PsiElement getContext() {
        return this.myContext;
    }
    
    public Language getLanguage() {
        return OCLanguage.getInstance();
    }
    
    public boolean willBeMethod() {
        try {
            if (OCCallableKind.METHOD == this.getNewCallableKind()) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean willBeFunction() {
        try {
            if (OCCallableKind.FUNCTION == this.getNewCallableKind()) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean willBeBlock() {
        try {
            if (OCCallableKind.BLOCK == this.getNewCallableKind()) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
