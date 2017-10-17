// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import java.util.HashMap;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import java.util.Map;

public class OCTollFreeBridges
{
    private static String[][] ourArray;
    private static Map<String, String> cfToNSMap;
    private static Map<String, String> nsToCFMap;
    
    @Nullable
    public static String getNSCounterpart(final String s) {
        return OCTollFreeBridges.cfToNSMap.get(s);
    }
    
    @Nullable
    public static OCType getResolvedNSCounterpart(final String s, final PsiFile psiFile) {
        return a(OCTollFreeBridges.cfToNSMap.get(s), psiFile);
    }
    
    @Nullable
    private static OCType a(@Nullable String substring, final PsiFile psiFile) {
        if (substring == null) {
            return null;
        }
        if (substring.endsWith(" *")) {
            substring = substring.substring(0, substring.length() - 2);
            return OCPointerType.to(OCReferenceType.resolvedFromText(substring, psiFile));
        }
        return OCReferenceType.resolvedFromText(substring, psiFile);
    }
    
    public static boolean hasCFCounterpart(final String s) {
        return OCTollFreeBridges.nsToCFMap.containsKey(s);
    }
    
    @Nullable
    public static OCType getCounterpart(final OCType ocType, final PsiFile psiFile) {
        final String bestNameInContext = ocType.getBestNameInContext((PsiElement)psiFile);
        if (OCTollFreeBridges.nsToCFMap.containsKey(bestNameInContext)) {
            return a(OCTollFreeBridges.nsToCFMap.get(bestNameInContext), psiFile);
        }
        return a(OCTollFreeBridges.cfToNSMap.get(bestNameInContext), psiFile);
    }
    
    private static boolean a(final OCType ocType, final OCType ocType2) {
        final String nsCounterpart = getNSCounterpart(ocType2.cloneWithoutConstModifier(null).getName());
        final String name = ocType.getName();
        return nsCounterpart != null && (nsCounterpart.equals(name) || ocType.isObjCRootType());
    }
    
    public static boolean isCompatible(final OCType ocType, final OCType ocType2) {
        return ocType != null && ocType2 != null && ((ocType.isPointerToObject() && ocType2 instanceof OCPointerType && a(ocType, ocType2)) || (ocType2.isPointerToObject() && ocType instanceof OCPointerType && a(ocType2, ocType)) || (ocType instanceof OCStructType && ocType2 instanceof OCStructType && (Comparing.equal(((OCStructType)ocType).getSymbol().getName(), getNSCounterpart(((OCStructType)ocType2).getSymbol().getName())) || Comparing.equal(((OCStructType)ocType2).getSymbol().getName(), getNSCounterpart(((OCStructType)ocType).getSymbol().getName())))));
    }
    
    static {
        OCTollFreeBridges.ourArray = new String[][] { { "CFArrayRef", "NSArray *" }, { "CFAttributedStringRef", "NSAttributedString *" }, { "CFCalendarRef", "NSCalendar *" }, { "CFCharacterSetRef", "NSCharacterSet *" }, { "CFDataRef", "NSData *" }, { "CFDateRef", "NSDate *" }, { "CFDictionaryRef", "NSDictionary *" }, { "CFErrorRef", "NSError *" }, { "CFLocaleRef", "NSLocale *" }, { "CFMutableArrayRef", "NSMutableArray *" }, { "CFMutableAttributedStringRef", "NSMutableAttributedString *" }, { "CFMutableCharacterSetRef", "NSMutableCharacterSet *" }, { "CFMutableDataRef", "NSMutableData *" }, { "CFMutableDictionaryRef", "NSMutableDictionary *" }, { "CFMutableSetRef", "NSMutableSet *" }, { "CFMutableStringRef", "NSMutableString *" }, { "CFNumberRef", "NSNumber *" }, { "CFReadStreamRef", "NSInputStream *" }, { "CFRunLoopTimerRef", "NSTimer *" }, { "CFSetRef", "NSSet *" }, { "CFStringRef", "NSString *" }, { "CFTimeZoneRef", "NSTimeZone *" }, { "CFURLRef", "NSURL *" }, { "CFWriteStreamRef", "NSOutputStream *" }, { "CGPoint", "NSPoint" }, { "CGSize", "NSSize" }, { "CGRect", "NSRect" }, { "CFRange", "NSRange" } };
        OCTollFreeBridges.cfToNSMap = new HashMap<String, String>();
        OCTollFreeBridges.nsToCFMap = new HashMap<String, String>();
        for (final String[] array : OCTollFreeBridges.ourArray) {
            OCTollFreeBridges.cfToNSMap.put(array[0], array[1]);
            OCTollFreeBridges.nsToCFMap.put(array[1], array[0]);
        }
    }
}
