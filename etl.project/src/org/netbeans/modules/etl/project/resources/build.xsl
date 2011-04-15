<?xml version="1.0" encoding="UTF-8"?>
<!--
  The contents of this file are subject to the terms of the Common Development
  and Distribution License (the License). You may not use this file except in
  compliance with the License.

  You can obtain a copy of the License at http://www.netbeans.org/cddl.html
  or http://www.netbeans.org/cddl.txt.

  When distributing Covered Code, include this CDDL Header Notice in each file
  and include the License file at http://www.netbeans.org/cddl.txt.
  If applicable, add the following below the CDDL Header, with the fields
  enclosed by brackets [] replaced by your own identifying information:
  "Portions Copyrighted [year] [name of copyright owner]"

  The Original Software is NetBeans. The Initial Developer of the Original
  Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
  Microsystems, Inc. All Rights Reserved.
-->
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:project="http://www.netbeans.org/ns/project/1"
                xmlns:web="http://www.netbeans.org/ns/j2ee-ejbjarproject/1"
                xmlns:xalan="http://xml.apache.org/xslt"
                exclude-result-prefixes="xalan project">
    <xsl:output method="xml" indent="yes" encoding="UTF-8" xalan:indent-amount="4"/>
    <xsl:template match="/">
    
        <xsl:variable name="name" select="/project:project/project:configuration/web:data/web:name"/>
        <project name="{$name}">
            <xsl:attribute name="default">default</xsl:attribute>
            <xsl:attribute name="basedir">.</xsl:attribute>
            <import file="nbproject/build-impl.xml"/>

            <target name="init-esb-cli" unless="netbeans.home">
                <property name="from.commandline" value="true"/>
                <property name="netbeans.dir" value="${{esb.netbeans.home}}"/>
                <property name="netbeans.user" value="${{esb.netbeans.user}}"/>
            </target>
            
            <target name="pre-init">
                <xsl:attribute name="depends">init-esb-cli</xsl:attribute>
            </target>
            
        </project>

    </xsl:template>
</xsl:stylesheet> 
