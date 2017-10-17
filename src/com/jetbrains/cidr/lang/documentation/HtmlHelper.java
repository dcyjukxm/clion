// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.documentation;

import org.jetbrains.annotations.NotNull;

public class HtmlHelper
{
    public static String bold(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper", "bold"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a("b", s);
    }
    
    public static String code(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper", "code"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a("code", s);
    }
    
    public static String pre(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper", "pre"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a("pre", s);
    }
    
    public static String paragraph(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper", "paragraph"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a("p", s);
    }
    
    public static String newLine(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper", "newLine"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return "<br>" + s;
    }
    
    private static String a(@NotNull final String s, @NotNull final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tag", "com/jetbrains/cidr/lang/documentation/HtmlHelper", "wrapWithTag"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper", "wrapWithTag"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final StringBuilder sb = new StringBuilder();
        a(sb, s, s2);
        return sb.toString();
    }
    
    private static void a(@NotNull final StringBuilder sb, @NotNull final String s, @NotNull final String s2) {
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sb", "com/jetbrains/cidr/lang/documentation/HtmlHelper", "wrapWithTag"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tag", "com/jetbrains/cidr/lang/documentation/HtmlHelper", "wrapWithTag"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper", "wrapWithTag"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        sb.append("<").append(s).append(">").append(s2).append("</").append(s).append(">");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class HtmlItem
    {
        protected final StringBuilder sb;
        
        public HtmlItem() {
            this.sb = new StringBuilder();
        }
    }
    
    public static class HtmlDoc extends HtmlItem
    {
        public void addText(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper$HtmlDoc", "addText"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.sb.append(s);
        }
        
        public void addItem(@NotNull final HtmlItem htmlItem) {
            try {
                if (htmlItem == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper$HtmlDoc", "addItem"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.sb.append(htmlItem.toString());
        }
        
        @Override
        public String toString() {
            return this.sb.toString();
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class DL extends HtmlItem
    {
        public void defineTerm(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper$DL", "defineTerm"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            a(this.sb, "dt", s);
        }
        
        public void detailedDescription(@NotNull final HtmlItem htmlItem) {
            try {
                if (htmlItem == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper$DL", "detailedDescription"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.detailedDescription(htmlItem.toString());
        }
        
        public void detailedDescription(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper$DL", "detailedDescription"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            a(this.sb, "dd", s);
        }
        
        @Override
        public String toString() {
            return a("dl", this.sb.toString());
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class Table extends HtmlItem
    {
        public void addRow(@NotNull final TableRow tableRow) {
            try {
                if (tableRow == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/documentation/HtmlHelper$Table", "addRow"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.sb.append(tableRow.toString());
        }
        
        @Override
        public String toString() {
            return a("table", this.sb.toString());
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class TableRow extends HtmlItem
    {
        public static TableRow withData(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper$TableRow", "withData"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final TableRow tableRow = new TableRow();
            tableRow.addData(s);
            return tableRow;
        }
        
        public void addData(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper$TableRow", "addData"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            a(this.sb, "td", s);
        }
        
        @Override
        public String toString() {
            return "<tr valign=\"top\">" + this.sb.toString() + "</tr>";
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
