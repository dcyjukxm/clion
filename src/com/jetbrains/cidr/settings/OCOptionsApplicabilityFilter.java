// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.settings;

import com.intellij.application.options.OptionId;
import com.intellij.application.options.OptionsApplicabilityFilter;

public class OCOptionsApplicabilityFilter extends OptionsApplicabilityFilter
{
    @Override
    public boolean isOptionApplicable(final OptionId optionId) {
        return optionId != OptionId.RENAME_IN_PLACE;
    }
}
