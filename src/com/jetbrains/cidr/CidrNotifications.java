// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr;

import com.intellij.notification.NotificationDisplayType;
import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.notification.NotificationGroup;

public class CidrNotifications
{
    public static final NotificationGroup MESSAGES;
    public static final NotificationGroup IMPORTANT_MESSAGES;
    
    static {
        MESSAGES = new NotificationGroup(ApplicationNamesInfo.getInstance().getProductName() + " Messages", NotificationDisplayType.BALLOON, true);
        IMPORTANT_MESSAGES = new NotificationGroup(ApplicationNamesInfo.getInstance().getProductName() + " Important Messages", NotificationDisplayType.STICKY_BALLOON, true);
    }
}
