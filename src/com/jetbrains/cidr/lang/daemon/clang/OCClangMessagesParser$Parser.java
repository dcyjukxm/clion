// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang;

private static class Parser
{
    private final String myMessage;
    private final int myLength;
    private int myIndex;
    private final StringBuilder myResult;
    
    public Parser(final String myMessage) {
        this.myResult = new StringBuilder();
        this.myMessage = myMessage;
        this.myLength = myMessage.length();
    }
    
    public String parse() {
        while (this.myIndex < this.myLength) {
            final char char1 = this.myMessage.charAt(this.myIndex++);
            if (char1 == '%') {
                this.b();
            }
            else {
                this.a(char1);
            }
        }
        return this.myResult.toString();
    }
    
    private void b() {
        if (this.myIndex < this.myLength && this.myMessage.charAt(this.myIndex) == '%') {
            ++this.myIndex;
            this.myResult.append("%");
            return;
        }
        final StringBuilder sb = new StringBuilder();
        while (this.myIndex < this.myLength) {
            final char char1 = this.myMessage.charAt(this.myIndex);
            if (!Character.isDigit(char1) && !Character.isLetter(char1)) {
                break;
            }
            sb.append(char1);
            ++this.myIndex;
        }
        final String string = sb.toString();
        if (string.equals("select") || string.equals("diff") || string.equals("plural")) {
            assert this.myIndex < this.myLength;
            assert this.myMessage.charAt(this.myIndex) == '{';
            ++this.myIndex;
            this.myResult.append("(");
            this.a(string.equals("plural"));
        }
        else {
            this.myResult.append(".*");
        }
    }
    
    private void a(final boolean b) {
        int n = b ? 1 : 0;
        while (this.myIndex < this.myLength) {
            final char char1 = this.myMessage.charAt(this.myIndex++);
            if (n != 0) {
                if (Character.isDigit(char1)) {
                    continue;
                }
                if (char1 == ':') {
                    continue;
                }
                n = 0;
            }
            if (char1 == '%') {
                this.b();
            }
            else {
                if (char1 == '}') {
                    this.a();
                    return;
                }
                if (char1 == '|') {
                    this.myResult.append("|");
                    n = (b ? 1 : 0);
                }
                else {
                    this.a(char1);
                }
            }
        }
        assert false;
    }
    
    private void a() {
        while (this.myIndex < this.myLength) {
            final char char1 = this.myMessage.charAt(this.myIndex);
            if (!Character.isDigit(char1) && char1 != ',') {
                break;
            }
            ++this.myIndex;
        }
        this.myResult.append(")");
    }
    
    private void a(final char c) {
        if (c == '$') {
            this.myResult.append(".*");
        }
        else if (c == '(' || c == ')' || c == '+' || c == '*' || c == '?' || c == '.' || c == '[' || c == ']' || c == '{' || c == '}' || c == '|' || c == '\\' || c == '\"') {
            this.myResult.append("\\").append(c);
        }
        else {
            this.myResult.append(c);
        }
    }
}
