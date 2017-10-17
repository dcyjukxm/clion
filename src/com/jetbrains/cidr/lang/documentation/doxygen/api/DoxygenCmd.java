// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.documentation.doxygen.api;

import com.intellij.util.containers.hash.HashMap;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

public enum DoxygenCmd
{
    BRIEF("brief"), 
    SHORT("short"), 
    FN("fn"), 
    VAR("var"), 
    TYPEDEF("typedef"), 
    PROPERTY("property"), 
    DEF("def"), 
    OVERLOAD("overload"), 
    ENUM("enum"), 
    DEFGROUP("defgroup"), 
    ADDTOGROUP("addtogroup"), 
    WEAKGROUP("weakgroup"), 
    NAMESPACE("namespace"), 
    PACKAGE("package"), 
    CLASS("class"), 
    HEADERFILE("headerfile"), 
    PROTOCOL("protocol"), 
    CATEGORY("category"), 
    UNION("union"), 
    STRUCT("struct"), 
    INTERFACE("interface"), 
    IDLEXCEPT("idlexcept"), 
    PAGE("page"), 
    MAINPAGE("mainpage"), 
    FILE("file"), 
    DIR("dir"), 
    EXAMPLE("example"), 
    DETAILS("details"), 
    NAME("name"), 
    TODO("todo"), 
    TEST("test"), 
    BUG("bug"), 
    DEPRECATED("deprecated"), 
    XREFITEM("xrefitem"), 
    RELATED("related"), 
    RELATES("relates"), 
    RELATEDALSO("relatedalso"), 
    RELATESALSO("relatesalso"), 
    PARBLOCK("parblock"), 
    ENDPARBLOCK("endparblock"), 
    REFITEM("refitem"), 
    CITE("cite"), 
    SUBPAGE("subpage"), 
    SECTION("section"), 
    SUBSECTION("subsection"), 
    SUBSUBSECTION("subsubsection"), 
    PARAGRAPH("paragraph"), 
    ANCHOR("anchor"), 
    VERBATIM("verbatim"), 
    ENDVERBATIM("endverbatim"), 
    LATEXONLY("latexonly"), 
    HTMLONLY("htmlonly"), 
    XMLONLY("xmlonly"), 
    DOCBOOKONLY("docbookonly"), 
    RTFONLY("rtfonly"), 
    MANONLY("manonly"), 
    DOT("dot"), 
    MSC("msc"), 
    STARTUML("startuml"), 
    CODE("code"), 
    ENDCODE("endcode"), 
    ADDINDEX("addindex"), 
    IF("if"), 
    IFNOT("ifnot"), 
    ELSEIF("elseif"), 
    ELSE("else"), 
    ENDIF("endif"), 
    INGROUP("ingroup"), 
    NOSUBGROUPING("nosubgrouping"), 
    SHOWINITIALIZER("showinitializer"), 
    HIDEINITIALIZER("hideinitializer"), 
    CALLGRAPH("callgraph"), 
    HIDECALLGRAPH("hidecallgraph"), 
    CALLERGRAPH("callergraph"), 
    HIDECALLERGRAPH("hidecallergraph"), 
    INTERNAL("internal"), 
    STATIC("static"), 
    PURE("pure"), 
    PRIVATE("private"), 
    PRIVATESECTION("privatesection"), 
    PROTECTED("protected"), 
    PROTECTEDSECTION("protectedsection"), 
    PUBLIC("public"), 
    PUBLICSECTION("publicsection"), 
    TABLEOFCONTENTS("tableofcontents"), 
    INHERIT("inherit"), 
    EXTENDS("extends"), 
    IMPLEMENTS("implements"), 
    MEMBEROF("memberof"), 
    ARG("arg"), 
    ATTENTION("attention"), 
    AUTHOR("author"), 
    AUTHORS("authors"), 
    COPYDOC("copydoc"), 
    COPYBRIEF("copybrief"), 
    COPYDETAILS("copydetails"), 
    COPYRIGHT("copyright"), 
    DATE("date"), 
    DOTFILE("dotfile"), 
    HTMLINCLUDE("htmlinclude"), 
    IMAGE("image"), 
    INCLUDE("include"), 
    INCLUDELINENO("includelineno"), 
    INVARIANT("invariant"), 
    LATEXINCLUDE("latexinclude"), 
    LI("li"), 
    LINE("line"), 
    NOTE("note"), 
    PAR("par"), 
    PARAM("param"), 
    TPARAM("tparam"), 
    POST("post"), 
    PRE("pre"), 
    REMARK("remark"), 
    REMARKS("remarks"), 
    RESULT("result"), 
    RETURN("return"), 
    RETURNS("returns"), 
    RETVAL("retval"), 
    SA("sa"), 
    SEE("see"), 
    SINCE("since"), 
    THROW("throw"), 
    THROWS("throws"), 
    UNTIL("until"), 
    VERBINCLUDE("verbinclude"), 
    VERSION("version"), 
    WARNING("warning");
    
    private final String name;
    public static final String TAG_AT = "@";
    public static final String TAG_BACKSLASH = "\\";
    public static final Map<String, String> SURROUND_TAGS;
    
    private DoxygenCmd(final String name) {
        if (name == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenCmd", "<init>"));
        }
        super(s, n);
        this.name = name;
    }
    
    @NotNull
    @Override
    public String toString() {
        String name;
        try {
            name = this.name;
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenCmd", "toString"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return name;
    }
    
    static {
        (SURROUND_TAGS = (Map)new HashMap()).put(DoxygenCmd.CODE.name, DoxygenCmd.ENDCODE.name);
        DoxygenCmd.SURROUND_TAGS.put(DoxygenCmd.VERBATIM.name, DoxygenCmd.ENDVERBATIM.name);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
