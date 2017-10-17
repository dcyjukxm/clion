// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.inspections.OCInspection;

public static class TypeCheckResult
{
    private TypeCheckState state;
    private String message;
    private Class<? extends OCInspection> inspectionClass;
    private String clangID;
    private IntentionAction[] quickFixes;
    private PsiElement myAnnotationElement;
    private boolean myIsWithConversion;
    private OCType myTypeAfterConversion;
    private OCType myTypeBeforeConversion;
    private OCFunctionSymbol myImplicitConstructor;
    
    @Override
    public String toString() {
        String s = this.state.toString();
        if (!this.state.isOK()) {
            s = s + "(" + this.clangID + ") " + this.message + " [fixes:" + this.quickFixes.length + "]";
        }
        return s;
    }
    
    public TypeCheckResult(final TypeCheckState typeCheckState, final Class<? extends OCInspection> clazz, final String s, final IntentionAction... array) {
        this(typeCheckState, clazz, s, null, (OCType)null, array);
    }
    
    public TypeCheckResult(final TypeCheckState typeCheckState, final String message, final Class<? extends OCInspection> clazz, final String s, final IntentionAction... array) {
        this(typeCheckState, clazz, s, null, (OCType)null, array);
        this.message = message;
    }
    
    public TypeCheckResult(final TypeCheckState typeCheckState, final Class<? extends OCInspection> clazz, final String s, final PsiElement myAnnotationElement, final IntentionAction... array) {
        this(typeCheckState, clazz, s, null, (OCType)null, array);
        this.myAnnotationElement = myAnnotationElement;
    }
    
    public TypeCheckResult(final TypeCheckState state, final Class<? extends OCInspection> inspectionClass, final String clangID, final OCType ocType, final OCType ocType2, final IntentionAction... quickFixes) {
        this.state = state;
        this.inspectionClass = inspectionClass;
        this.clangID = clangID;
        this.quickFixes = quickFixes;
        this.setConversion(ocType, ocType2);
    }
    
    public TypeCheckResult(final TypeCheckState state) {
        this.state = state;
    }
    
    public TypeCheckState getState() {
        return this.state;
    }
    
    public void setState(final TypeCheckState state) {
        this.state = state;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public Class<? extends OCInspection> getInspectionClass() {
        return this.inspectionClass;
    }
    
    public String getClangID() {
        return this.clangID;
    }
    
    public IntentionAction[] getQuickFixes() {
        return this.quickFixes;
    }
    
    public void setQuickFixes(final IntentionAction[] quickFixes) {
        this.quickFixes = quickFixes;
    }
    
    @Nullable
    public PsiElement getAnnotationElement() {
        return this.myAnnotationElement;
    }
    
    public void setAnnotationElement(final PsiElement myAnnotationElement) {
        this.myAnnotationElement = myAnnotationElement;
    }
    
    public boolean isWithConversion() {
        return this.myIsWithConversion;
    }
    
    public OCType getTypeBeforeConversion() {
        return this.myTypeBeforeConversion;
    }
    
    @Nullable
    public OCType getTypeAfterConversion() {
        return this.myTypeAfterConversion;
    }
    
    public void setConversion(final OCType myTypeBeforeConversion, final OCType myTypeAfterConversion) {
        this.myIsWithConversion = true;
        this.myTypeAfterConversion = myTypeAfterConversion;
        this.myTypeBeforeConversion = myTypeBeforeConversion;
    }
    
    @Nullable
    public OCFunctionSymbol getImplicitConstructor() {
        return this.myImplicitConstructor;
    }
    
    public void setImplicitConstructor(final OCFunctionSymbol myImplicitConstructor) {
        this.myImplicitConstructor = myImplicitConstructor;
    }
    
    public boolean canBeCasted(final OCType ocType, final OCType ocType2, final PsiElement psiElement) {
        return this.state != TypeCheckState.ERROR || (ocType.isPointerCompatible(psiElement) && ocType2.isPointerCompatible(psiElement) && ocType.isPointerToObjectCompatible() == ocType2.isPointerToObjectCompatible()) || (ocType instanceof OCCppReferenceType && ((OCCppReferenceType)ocType).isRvalueRef() && ((OCCppReferenceType)ocType).getRefType().checkCompatible(ocType2, psiElement).getState() == TypeCheckState.OK);
    }
}
