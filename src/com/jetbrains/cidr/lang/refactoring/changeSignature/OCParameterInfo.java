// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.jetbrains.cidr.lang.types.OCStructType;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import java.util.Collection;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.refactoring.changeSignature.ParameterInfo;

public class OCParameterInfo implements ParameterInfo, Cloneable
{
    private final int myOldIndex;
    private String mySelector;
    private String myName;
    private OCType myType;
    private String myTypeText;
    private boolean myEllipsisType;
    private boolean mySelfParameter;
    private Collection<PsiReference> myUsages;
    private boolean myReferenceMode;
    private String myArgumentValue;
    private final PsiElement myTypeContext;
    
    public OCParameterInfo(final String s, final String s2, final OCType ocType, final int n, final PsiElement psiElement) {
        this(s, s2, ocType, a(ocType, psiElement), n, psiElement);
    }
    
    public OCParameterInfo(final String s, final String s2, final OCType myType, final String myTypeText, final int myOldIndex, final PsiElement myTypeContext) {
        this.mySelector = "";
        this.myName = "";
        this.myTypeContext = myTypeContext;
        String substring;
        if (s.endsWith(":")) {
            substring = s.substring(0, s.length() - 1);
        }
        else {
            substring = s;
        }
        String myName = null;
        Label_0065: {
            try {
                this.mySelector = substring;
                if (s2 != null) {
                    myName = s2;
                    break Label_0065;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            myName = "";
        }
        this.myName = myName;
        this.myType = myType;
        this.myTypeText = myTypeText;
        this.myOldIndex = myOldIndex;
    }
    
    @NotNull
    private static String a(OCType to, final PsiElement psiElement) {
        Label_0052: {
            String s = null;
            Label_0017: {
                try {
                    if (to != null) {
                        break Label_0052;
                    }
                    s = "";
                    if (s == null) {
                        break Label_0017;
                    }
                    return s;
                }
                catch (UnsupportedOperationException ex) {
                    throw b(ex);
                }
                try {
                    s = "";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo", "getTextFromType"));
                    }
                }
                catch (UnsupportedOperationException ex2) {
                    throw b(ex2);
                }
            }
            return s;
        }
        if (to.getClass().equals(OCPointerType.class)) {
            final OCPointerType ocPointerType = (OCPointerType)to;
            final Integer lengthInBrackets = ocPointerType.getLengthInBrackets();
            if (lengthInBrackets != null) {
                to = OCArrayType.to(ocPointerType.getRefType(), lengthInBrackets, ocPointerType.getARCAttribute());
            }
        }
        String name;
        try {
            name = to.getName(psiElement);
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCParameterInfo", "getTextFromType"));
            }
        }
        catch (UnsupportedOperationException ex3) {
            throw b(ex3);
        }
        return name;
    }
    
    public void setSelfParameter(final boolean mySelfParameter) {
        this.mySelfParameter = mySelfParameter;
    }
    
    public void setEllipsisType(final boolean myEllipsisType) {
        this.myEllipsisType = myEllipsisType;
    }
    
    public void setReferenceMode(final boolean myReferenceMode) {
        this.myReferenceMode = myReferenceMode;
    }
    
    public void setArgumentValue(final String myArgumentValue) {
        this.myArgumentValue = myArgumentValue;
    }
    
    public String getSelector() {
        return this.mySelector;
    }
    
    public String getName() {
        return this.myName;
    }
    
    public int getOldIndex() {
        return this.myOldIndex;
    }
    
    public String getDefaultValue() {
        return null;
    }
    
    public void setName(final String s) {
        String myName = null;
        Label_0015: {
            try {
                if (s != null) {
                    myName = s;
                    break Label_0015;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            myName = "";
        }
        this.myName = myName;
    }
    
    public void setSelector(final String mySelector) {
        this.mySelector = mySelector;
    }
    
    @Nullable
    public OCType getType() {
        Label_0021: {
            try {
                if (this.myType == null) {
                    return null;
                }
                final OCParameterInfo ocParameterInfo = this;
                final boolean b = ocParameterInfo.myReferenceMode;
                if (b) {
                    break Label_0021;
                }
                return this.myType;
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            try {
                final OCParameterInfo ocParameterInfo = this;
                final boolean b = ocParameterInfo.myReferenceMode;
                if (b) {
                    return OCPointerType.to(this.myType);
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
        }
        return this.myType;
        ocType = null;
        return ocType;
    }
    
    public String getTypeText() {
        try {
            if (this.myReferenceMode) {
                return this.myTypeText + "*";
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return this.myTypeText;
    }
    
    public void setType(final OCType myType) {
        this.myType = myType;
        this.myTypeText = a(this.myType, this.myTypeContext);
    }
    
    public void setTypeText(final String myTypeText) {
        this.myTypeText = myTypeText;
    }
    
    public boolean isEllipsisType() {
        return this.myEllipsisType;
    }
    
    public boolean isSelfParameter() {
        return this.mySelfParameter;
    }
    
    public String getParameterStubText(final boolean b, final boolean b2, @Nullable final PsiElement psiElement) {
        final StringBuilder sb = new StringBuilder();
        Label_0058: {
            Label_0040: {
                try {
                    if (!b) {
                        break Label_0058;
                    }
                    final StringBuilder sb2 = sb;
                    final OCParameterInfo ocParameterInfo = this;
                    final String s = ocParameterInfo.mySelector;
                    sb2.append(s);
                    final OCParameterInfo ocParameterInfo2 = this;
                    final String s2 = ocParameterInfo2.myName;
                    final boolean b3 = s2.isEmpty();
                    if (b3) {
                        break Label_0040;
                    }
                    break Label_0040;
                }
                catch (UnsupportedOperationException ex) {
                    throw b(ex);
                }
                try {
                    final StringBuilder sb2 = sb;
                    final OCParameterInfo ocParameterInfo = this;
                    final String s = ocParameterInfo.mySelector;
                    sb2.append(s);
                    final OCParameterInfo ocParameterInfo2 = this;
                    final String s2 = ocParameterInfo2.myName;
                    final boolean b3 = s2.isEmpty();
                    if (b3) {
                        return sb.toString();
                    }
                }
                catch (UnsupportedOperationException ex2) {
                    throw b(ex2);
                }
            }
            sb.append(':');
            try {
                if (b2) {
                    sb.append(this.myName);
                    return sb.toString();
                }
            }
            catch (UnsupportedOperationException ex3) {
                throw b(ex3);
            }
        }
        try {
            if (this.isSelfParameter()) {
                sb.append("self");
                return sb.toString();
            }
        }
        catch (UnsupportedOperationException ex4) {
            throw b(ex4);
        }
        try {
            if (this.myArgumentValue != null) {
                sb.append(this.myArgumentValue);
                return sb.toString();
            }
        }
        catch (UnsupportedOperationException ex5) {
            throw b(ex5);
        }
        Label_0174: {
            StringBuilder sb3 = null;
            OCType ocType = null;
            PsiElement myTypeContext = null;
            Label_0164: {
                Label_0152: {
                    try {
                        if (!(this.myType instanceof OCStructType)) {
                            break Label_0174;
                        }
                        sb3 = sb;
                        final OCParameterInfo ocParameterInfo3 = this;
                        ocType = ocParameterInfo3.myType;
                        final PsiElement psiElement2 = psiElement;
                        if (psiElement2 != null) {
                            break Label_0152;
                        }
                        break Label_0152;
                    }
                    catch (UnsupportedOperationException ex6) {
                        throw b(ex6);
                    }
                    try {
                        sb3 = sb;
                        final OCParameterInfo ocParameterInfo3 = this;
                        ocType = ocParameterInfo3.myType;
                        final PsiElement psiElement2 = psiElement;
                        if (psiElement2 != null) {
                            myTypeContext = psiElement;
                            break Label_0164;
                        }
                    }
                    catch (UnsupportedOperationException ex7) {
                        throw b(ex7);
                    }
                }
                myTypeContext = this.myTypeContext;
            }
            sb3.append(ocType.getDefaultValue(myTypeContext));
            return sb.toString();
            try {
                if (this.myType != null) {
                    sb.append(this.myType.getDefaultValue(this.myTypeContext));
                    return sb.toString();
                }
            }
            catch (UnsupportedOperationException ex8) {
                throw b(ex8);
            }
        }
        sb.append("0");
        return sb.toString();
    }
    
    public boolean isTypeChanged(final String s) {
        try {
            if (!s.replaceAll(" ", "").equals(this.myTypeText.replaceAll(" ", ""))) {
                return true;
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public boolean isUseAnySingleVariable() {
        return false;
    }
    
    public void setUseAnySingleVariable(final boolean b) {
        throw new UnsupportedOperationException();
    }
    
    public void setUsages(final Collection<PsiReference> myUsages) {
        this.myUsages = myUsages;
    }
    
    public Collection<PsiReference> getUsages() {
        return this.myUsages;
    }
    
    public boolean isReferenceMode() {
        return this.myReferenceMode;
    }
    
    public OCParameterInfo clone() {
        try {
            return (OCParameterInfo)super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private static UnsupportedOperationException b(final UnsupportedOperationException ex) {
        return ex;
    }
}
