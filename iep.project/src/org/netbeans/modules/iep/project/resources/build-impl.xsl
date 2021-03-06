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
    xmlns:p="http://www.netbeans.org/ns/project/1"
    xmlns:xalan="http://xml.apache.org/xslt"
    xmlns:ejb="http://www.netbeans.org/ns/j2ee-ejbjarproject/1"
    xmlns:projdeps="http://www.netbeans.org/ns/ant-project-references/1"
    exclude-result-prefixes="xalan p ejb projdeps">
    <xsl:output method="xml" indent="yes" encoding="UTF-8" xalan:indent-amount="4"/>
    <xsl:template match="/">
        <xsl:comment><![CDATA[
        *** GENERATED FROM project.xml - DO NOT EDIT  ***
        ***         EDIT ../build.xml INSTEAD         ***

        For the purpose of easier reading the script
        is divided into following sections:

        - initialization
        - compilation
        - dist
        - execution
        - debugging
        - javadoc
        - cleanup

        ]]>
        </xsl:comment>
        <xsl:variable name="name" select="/p:project/p:configuration/ejb:data/ejb:name"/>
        <project name="{$name}-impl">
            <xsl:attribute name="default">build</xsl:attribute>
            <xsl:attribute name="basedir">..</xsl:attribute>
            <target name="default">
                <xsl:attribute name="depends">dist</xsl:attribute>
                <xsl:attribute name="description">Build whole project.</xsl:attribute>
            </target>
            <xsl:comment> 
                ======================
                INITIALIZATION SECTION 
                ======================
            </xsl:comment>
            <target name="pre-init">
                <xsl:comment> Empty placeholder for easier customization. </xsl:comment>
                <xsl:comment> You can override this target in the ../build.xml file. </xsl:comment>
            </target>
            <target name="init-private">
                <xsl:attribute name="depends">pre-init</xsl:attribute>
                <property file="nbproject/private/private.properties"/>
            </target>
            <target name="init-userdir">
                <xsl:attribute name="depends">pre-init,init-private</xsl:attribute>
                <property name="user.properties.file" location="${{netbeans.user}}/build.properties"/>
            </target>
            <target name="init-user">
                <xsl:attribute name="depends">pre-init,init-private,init-userdir</xsl:attribute>
                <property file="${{user.properties.file}}"/>
            </target>
            <target name="init-project">
                <xsl:attribute name="depends">pre-init,init-private,init-userdir,init-user</xsl:attribute>
                <property file="nbproject/project.properties"/>
            </target>
            <target name="do-init">
                <xsl:attribute name="depends">pre-init,init-private,init-userdir,init-user,init-project</xsl:attribute>
                <xsl:if test="/p:project/p:configuration/ejb:data/ejb:explicit-platform">
                    <!--Setting java and javac default location -->
                    <property name="platforms.${{platform.active}}.javac" value="${{platform.home}}/bin/javac"/>
                    <property name="platforms.${{platform.active}}.java" value="${{platform.home}}/bin/java"/>
                    <!-- XXX Ugly but Ant does not yet support recursive property evaluation: -->
                    <tempfile property="file.tmp" prefix="platform" suffix=".properties"/>
                    <echo file="${{file.tmp}}">
                        platform.home=$${platforms.${platform.active}.home}
                        platform.bootcp=$${platforms.${platform.active}.bootclasspath}                
                        build.compiler=$${platforms.${platform.active}.compiler}
                        platform.java=$${platforms.${platform.active}.java}
                        platform.javac=$${platforms.${platform.active}.javac}
                    </echo>
                    <property file="${{file.tmp}}"/>
                    <delete file="${{file.tmp}}"/>
                    <fail unless="platform.home">Must set platform.home</fail>
                    <fail unless="platform.bootcp">Must set platform.bootcp</fail>
                    <fail unless="platform.java">Must set platform.java</fail>
                    <fail unless="platform.javac">Must set platform.javac</fail>
                </xsl:if>
                <xsl:comment> The two properties below are usually overridden </xsl:comment>
                <xsl:comment> by the active platform. Just a fallback. </xsl:comment>
                <property name="default.javac.source" value="1.4"/>
                <property name="default.javac.target" value="1.4"/>
                <xsl:if test="/p:project/p:configuration/ejb:data/ejb:use-manifest">
                    <fail unless="manifest.file">Must set manifest.file</fail>
                </xsl:if>
            </target>
            <target name="post-init">
                <xsl:comment> Empty placeholder for easier customization. </xsl:comment>
                <xsl:comment> You can override this target in the ../build.xml file. </xsl:comment>
            </target>
            <target name="init-check">
                <xsl:attribute name="depends">pre-init,init-private,init-userdir,init-user,init-project,do-init</xsl:attribute>
                <!-- XXX XSLT 2.0 would make it possible to use a for-each here -->
                <!-- Note that if the properties were defined in project.xml that would be easy -->
                <!-- But required props should be defined by the AntBasedProjectType, not stored in each project -->
                <fail unless="src.dir">Must set src.dir</fail>
                <fail unless="build.dir">Must set build.dir</fail>
                <fail unless="build.generated.dir">Must set build.generated.dir</fail>
                <fail unless="dist.dir">Must set dist.dir</fail>
                <fail unless="build.classes.dir">Must set build.classes.dir</fail>
                <fail unless="dist.jar">Must set dist.jar</fail>
            </target>
            <target name="init-taskdefs"   if="from.commandline">
                <path id="ant.task.classpath.models">
                    <pathelement location="${{esb.netbeans.platform}}/../soa/modules/org-netbeans-modules-iep-editor.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../soa/modules/org-netbeans-modules-iep-project.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../soa/ant/nblib/org-netbeans-modules-iep-project.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../soa/modules/org-netbeans-modules-soa-validation.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../soa/modules/org-netbeans-modules-soa-ui.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../xml/modules/org-netbeans-modules-xml-search.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../platform/lib/org-openide-util.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../platform/lib/org-openide-util-lookup.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../platform/core/org-openide-filesystems.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../platform/modules/org-openide-loaders.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../platform/modules/org-openide-dialogs.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../platform/modules/org-openide-loaders.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../platform/modules/org-openide-nodes.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../platform/modules/org-openide-text.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../platform/modules/org-netbeans-modules-masterfs.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../platform/modules/org-netbeans-modules-queries.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../ide/modules/ext/resolver-1.2.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../ide/modules/org-apache-xml-resolver.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../ide/modules/org-netbeans-modules-project-ant.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../ide/modules/org-netbeans-modules-projectapi.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../ide/modules/org-netbeans-modules-xml-xam.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../ide/modules/org-netbeans-modules-xml-schema-model.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../ide/modules/org-netbeans-modules-xml-wsdl-model.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../ide/modules/org-netbeans-modules-xml-retriever.jar"/>
                    <pathelement location="${{esb.netbeans.platform}}/../xml/modules/org-netbeans-modules-xml-misc.jar"/>
                </path>
                <taskdef name="iep-generate-asa-artifacts" classname="org.netbeans.modules.iep.project.anttasks.cli.GenerateAsaArtifacts">
                    <classpath refid="ant.task.classpath.models"/>
                </taskdef>
            </target>
            <target name="init">
                <xsl:attribute name="depends">pre-init,init-private,init-userdir,init-user,init-project,do-init,post-init,init-check,init-taskdefs</xsl:attribute>
            </target>
            <xsl:comment>
                ===================
                COMPILATION SECTION
                ===================
            </xsl:comment>
            <xsl:call-template name="deps.target">
                <xsl:with-param name="targetname" select="'deps-jar'"/>
                <xsl:with-param name="type" select="'jar'"/>
            </xsl:call-template>
            <xsl:if test="/p:project/p:configuration/ejb:data/ejb:web-services/ejb:web-service|/p:project/p:configuration/ejb:data/ejb:web-service-clients/ejb:web-service-client">
                <target name="wscompile-init">
                    <taskdef name="wscompile" classname="com.sun.xml.rpc.tools.ant.Wscompile">
                        <classpath path="${{wscompile.classpath}}"/>
                    </taskdef>
                    <mkdir dir="${{build.classes.dir}}/META-INF/wsdl"/>
                    <mkdir dir="${{build.generated.dir}}/wssrc"/>
                </target>
            </xsl:if>
            <xsl:for-each select="/p:project/p:configuration/ejb:data/ejb:web-services/ejb:web-service">
                <xsl:variable name="wsname">
                    <xsl:value-of select="ejb:web-service-name"/>
                </xsl:variable>
                <target name="{$wsname}_wscompile" depends="wscompile-init">
                    <wscompile
                        server="true"
                        fork="true"
                        keep="true"
                        base="${{build.generated.dir}}/wssrc"
                        xPrintStackTrace="true"
                        verbose="true"
                        nonClassDir="${{build.classes.dir}}/META-INF/wsdl"
                        classpath="${{wscompile.classpath}}:${{build.classes.dir}}"
                        mapping="${{build.classes.dir}}/META-INF/wsdl/${{{$wsname}.mapping}}"
                        config="${{src.dir}}/${{{$wsname}.config.name}}">
                        <!-- HTTPProxy="${http.proxyHost}:${http.proxyPort}" -->
                    </wscompile>
                </target>
            </xsl:for-each>
            <xsl:for-each select="/p:project/p:configuration/ejb:data/ejb:web-service-clients/ejb:web-service-client">
                <xsl:variable name="wsclientname">
                    <xsl:value-of select="ejb:web-service-client-name"/>
                </xsl:variable>
                <target name="{$wsclientname}_client_wscompile" depends="wscompile-init">
                    <copy file="${{web.docbase.dir}}/WEB-INF/wsdl/{$wsclientname}-config.xml"
                        tofile="${{build.generated.dir}}/wssrc/wsdl/{$wsclientname}-config.xml" filtering="on">
                        <filterset>
                            <!-- replace token with reference to WSDL file in source tree, not build tree, since the
                            the file probably has not have been copied to the build tree yet. -->
                            <filter token="CONFIG_ABSOLUTE_PATH" value="${{basedir}}/${{web.docbase.dir}}/WEB-INF/wsdl"/>
                        </filterset>
                    </copy>
                    <wscompile
                        xPrintStackTrace="true" verbose="true"
                        fork="true" keep="true" import="true" features="norpcstructures"
                        base="${{build.classes.dir}}"
                        sourceBase="${{build.generated.dir}}/wssrc"
                        classpath="${{wscompile.classpath}}"
                        mapping="${{build.web.dir}}/WEB-INF/wsdl/{$wsclientname}-mapping.xml"
                        config="${{build.generated.dir}}/wssrc/wsdl/{$wsclientname}-config.xml">
                    </wscompile>
                </target>
            </xsl:for-each>
            <target name="pre-pre-compile">
                <xsl:attribute name="depends">init,deps-jar</xsl:attribute>
                <mkdir dir="${{build.classes.dir}}"/>
            </target>
            <target name="pre-compile">
                <xsl:comment> Empty placeholder for easier customization. </xsl:comment>
                <xsl:comment> You can override this target in the ../build.xml file. </xsl:comment>
            </target>
            <target name="library-inclusion-in-archive" depends="compile">
                <xsl:for-each select="//ejb:included-library">
                    <xsl:variable name="included.prop.name">
                        <xsl:value-of select="."/>
                    </xsl:variable>
                    <unjar dest="${{build.classes.dir}}">
                        <xsl:attribute name="src">${<xsl:value-of select="$included.prop.name"/>}</xsl:attribute>
                    </unjar>
                </xsl:for-each>
            </target>
            <target name="library-inclusion-in-manifest" depends="compile">
                <xsl:for-each select="//ejb:included-library">
                    <xsl:variable name="included.prop.name">
                        <xsl:value-of select="."/>
                    </xsl:variable>
                    <xsl:variable name="base.prop.name">
                        <xsl:value-of select="concat('included.lib.', $included.prop.name, '')"/>
                    </xsl:variable>
                    <basename>
                        <xsl:attribute name="property"><xsl:value-of select="$base.prop.name"/></xsl:attribute>
                        <xsl:attribute name="file">${<xsl:value-of select="$included.prop.name"/>}</xsl:attribute>
                    </basename>
                    <copy todir="${{build.classes.dir}}">
                        <xsl:attribute name="file">${<xsl:value-of select="$included.prop.name"/>}</xsl:attribute>
                    </copy>
                </xsl:for-each>
                <manifest file="${{build.classes.dir}}/META-INF/MANIFEST.MF" mode="update">
                    <attribute>
                        <xsl:attribute name="name">Class-Path</xsl:attribute>
                        <xsl:attribute name="value"><xsl:for-each select="//ejb:included-library">
                                <xsl:variable name="base.prop.name"><xsl:value-of select="concat('${included.lib.', ., '}')"/></xsl:variable>
                                <xsl:if test="position()>1">,
                                </xsl:if>
                                <xsl:value-of select="$base.prop.name"/>
                            </xsl:for-each></xsl:attribute>
                    </attribute>
                </manifest>
            </target>
            <target name="do-compile">
                <xsl:attribute name="depends">init,deps-jar,pre-pre-compile,pre-compile</xsl:attribute>
                <xsl:if test="/p:project/p:configuration/ejb:data/ejb:web-services/ejb:web-service">
                    <xsl:comment>For web services, refresh the Tie and SerializerRegistry classes</xsl:comment>
                    <delete>
                        <fileset dir="${{build.classes.dir}}" includes="**/*_Tie.* **/*_SerializerRegistry.*"/>
                    </delete>
                </xsl:if>
            </target>
            <target name="post-compile">
                <xsl:if test="/p:project/p:configuration/ejb:data/ejb:web-services/ejb:web-service">
                    <xsl:attribute name="depends"><xsl:for-each select="/p:project/p:configuration/ejb:data/ejb:web-services/ejb:web-service">
                            <xsl:if test="position()!=1"><xsl:text>, </xsl:text></xsl:if>
                            <xsl:variable name="wsname2"><xsl:value-of select="ejb:web-service-name"/></xsl:variable>
                            <xsl:value-of select="ejb:web-service-name"/>
                            <xsl:text>_wscompile</xsl:text>
                        </xsl:for-each></xsl:attribute>
                </xsl:if>
                <xsl:comment> Empty placeholder for easier customization. </xsl:comment>
                <xsl:comment> You can override this target in the ../build.xml file. </xsl:comment>
            </target>
            <target name="compile">
                <xsl:attribute name="depends">init,deps-jar,pre-pre-compile,pre-compile,do-compile,post-compile</xsl:attribute>
                <xsl:attribute name="description">Compile project.</xsl:attribute>
            </target>
            <target name="pre-compile-single">
                <xsl:comment> Empty placeholder for easier customization. </xsl:comment>
                <xsl:comment> You can override this target in the ../build.xml file. </xsl:comment>
            </target>
            <target name="do-compile-single">
                <xsl:attribute name="depends">init,deps-jar,pre-pre-compile</xsl:attribute>
                <fail unless="javac.includes">Must select some files in the IDE or set javac.includes</fail>
                <ejbproject:javac xmlns:ejbproject="http://www.netbeans.org/ns/j2ee-ejbjarproject/1">
                    <customize>
                        <include name="${{javac.includes}}"/>
                    </customize>
                </ejbproject:javac>
            </target>
            <target name="post-compile-single">
                <xsl:comment> Empty placeholder for easier customization. </xsl:comment>
                <xsl:comment> You can override this target in the ../build.xml file. </xsl:comment>
            </target>
            <target name="compile-single">
                <xsl:attribute name="depends">init,deps-jar,pre-pre-compile,pre-compile-single,do-compile-single,post-compile-single</xsl:attribute>
            </target>
            <xsl:comment>
                ====================
                DIST BUILDING SECTION
                ====================
            </xsl:comment>
            <target name="pre-dist">
                <xsl:comment> Empty placeholder for easier customization. </xsl:comment>
                <xsl:comment> You can override this target in the ../build.xml file. </xsl:comment>
                <mkdir dir="${{build.dir}}"/>
                <mkdir dir="${{build.dir}}/META-INF"/>
                <iep-generate-asa-artifacts
                    srcDirectoryLocation="${{basedir}}/${{src.dir}}"
                    buildDirectoryLocation="${{basedir}}/${{build.dir}}"
                    jbiDescriptorFileLocation="${{basedir}}/${{build.dir}}/META-INF/jbi.xml"
                    classpathRef="ant.task.classpath.models"
                    validate="true"
                    allowBuildWithError="${{allow.build.with.error}}"
                    alwaysGenerateAbstractWsdl="${{always.generate.abstract.wsdl}}"
                    >
                </iep-generate-asa-artifacts>
            </target>
            <target name="dist_se">
                <xsl:attribute name="depends">init,pre-dist</xsl:attribute>
                <jar compress="${{jar.compress}}" jarfile="${{build.dir}}/SEDeployment.jar">
                    <fileset includes="**/*.iep,**/*.wsdl,**/*.xsd" dir="${{src.dir}}"/>
                    <fileset includes="**/*.wsdl,**/*.xsd" dir="${{build.dir}}"/>
                    <fileset dir="${{build.dir}}">
                        <include name="META-INF/jbi.xml" />
                    </fileset>
                </jar>
            </target>
            <target name="do-dist">
                <xsl:attribute name="depends">init,pre-dist, dist_se</xsl:attribute>
            </target>
            <target name="post-dist">
                <xsl:comment> Empty placeholder for easier customization. </xsl:comment>
                <xsl:comment> You can override this target in the ../build.xml file. </xsl:comment>
            </target>
            <target name="dist">
                <xsl:attribute name="depends">init,pre-dist,do-dist,post-dist</xsl:attribute>
                <xsl:attribute name="description">Build distribution (JAR).</xsl:attribute>
            </target>
            <xsl:comment>
                =================
                DEBUGGING SECTION
                =================
            </xsl:comment>
            <target name="debug">
                <xsl:attribute name="description">Debug project in IDE.</xsl:attribute>
                <xsl:attribute name ="depends">init,compile</xsl:attribute>
                <xsl:attribute name="if">esb.netbeans.platform</xsl:attribute>
                <nbdeploy debugmode="true" clientUrlPart="${{client.urlPart}}"/>
                <nbjpdaconnect name="${{name}}" host="${{jpda.host}}" address="${{jpda.address}}" transport="${{jpda.transport}}">
                    <classpath>
                        <path path="${{debug.classpath}}"/>
                    </classpath>
                    <sourcepath>
                        <path path="${{web.docbase.dir}}"/>
                    </sourcepath>
                    <xsl:if test="/p:project/p:configuration/ejb:data/ejb:explicit-platform">
                        <bootclasspath>
                            <path path="${{platform.bootcp}}"/>
                        </bootclasspath>
                    </xsl:if>
                </nbjpdaconnect>
            </target>
            <target name="pre-debug-fix">
                <xsl:attribute name="depends">init</xsl:attribute>
                <fail unless="fix.includes">Must set fix.includes</fail>
                <property name="javac.includes" value="${{fix.includes}}.java"/>
            </target>
            <target name="do-debug-fix">
                <xsl:attribute name="if">esb.netbeans.platform</xsl:attribute>
                <xsl:attribute name="depends">init,pre-debug-fix,compile-single</xsl:attribute>
                <j2seproject:nbjpdareload xmlns:j2seproject="http://www.netbeans.org/ns/j2se-project/1"/>
            </target>
            <target name="debug-fix">
                <xsl:attribute name="if">esb.netbeans.platform</xsl:attribute>
                <xsl:attribute name="depends">init,pre-debug-fix,do-debug-fix</xsl:attribute>
            </target>
            <xsl:comment>
                ===============
                CLEANUP SECTION
                ===============
            </xsl:comment>
            <xsl:call-template name="deps.target">
                <xsl:with-param name="targetname" select="'deps-clean'"/>
            </xsl:call-template>
            <target name="do-clean">
                <xsl:attribute name="depends">init</xsl:attribute>
                <delete dir="${{build.dir}}"/>
                <delete dir="${{dist.dir}}"/>
            </target>
            <target name="post-clean">
                <xsl:comment> Empty placeholder for easier customization. </xsl:comment>
                <xsl:comment> You can override this target in the ../build.xml file. </xsl:comment>
            </target>
            <target name="clean">
                <xsl:attribute name="depends">init,deps-clean,do-clean,post-clean</xsl:attribute>
                <xsl:attribute name="description">Clean build products.</xsl:attribute>
            </target>
        </project>

        <!-- TBD items:

        Could pass <propertyset> to run, debug, etc. under Ant 1.6,
        optionally, by doing e.g.

        <propertyset>
        <propertyref prefix="sysprop."/>
        <mapper type="glob" from="sysprop.*" to="*"/>
        </propertyset>

        Now user can add to e.g. project.properties e.g.:
        sysprop.org.netbeans.modules.javahelp=0
        to simulate
        -Dorg.netbeans.modules.javahelp=0

        -->
    </xsl:template>

    <!---
    Generic template to build subdependencies of a certain type.
    Feel free to copy into other modules.
    @param targetname required name of target to generate
    @param type artifact-type from project.xml to filter on; optional, if not specified, uses
    all references, and looks for clean targets rather than build targets
    @return an Ant target which builds (or cleans) all known subprojects
    -->
    <xsl:template name="deps.target">
        <xsl:param name="targetname"/>
        <xsl:param name="type"/>
        <target name="{$targetname}">
            <xsl:attribute name="depends">init</xsl:attribute>
            <xsl:attribute name="unless">${no.dependencies}</xsl:attribute>
            <xsl:variable name="references" select="/p:project/p:configuration/projdeps:references"/>
            <xsl:for-each select="$references/projdeps:reference[not($type) or projdeps:artifact-type = $type]">
                <xsl:variable name="subproj" select="projdeps:foreign-project"/>
                <xsl:variable name="subtarget">
                    <xsl:choose>
                        <xsl:when test="$type"><xsl:value-of select="projdeps:target"/></xsl:when>
                        <xsl:otherwise><xsl:value-of select="projdeps:clean-target"/></xsl:otherwise>
                    </xsl:choose>
                </xsl:variable>
                <xsl:variable name="script" select="projdeps:script"/>
                <xsl:variable name="scriptdir" select="substring-before($script, '/')"/>
                <xsl:variable name="scriptdirslash">
                    <xsl:choose>
                        <xsl:when test="$scriptdir = ''"/>
                        <xsl:otherwise>
                            <xsl:text>/</xsl:text>
                            <xsl:value-of select="$scriptdir"/>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:variable>
                <xsl:variable name="scriptfileorblank" select="substring-after($script, '/')"/>
                <xsl:variable name="scriptfile">
                    <xsl:choose>
                        <xsl:when test="$scriptfileorblank != ''">
                            <xsl:value-of select="$scriptfileorblank"/>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:value-of select="$script"/>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:variable>
                <ant target="{$subtarget}" inheritall="false">
                    <!-- XXX #43624: cannot use inline attr on JDK 1.5 -->
                    <xsl:attribute name="dir">${project.<xsl:value-of select="$subproj"/>}<xsl:value-of select="$scriptdirslash"/></xsl:attribute>
                    <xsl:if test="$scriptfile != 'build.xml'">
                        <xsl:attribute name="antfile"><xsl:value-of select="$scriptfile"/></xsl:attribute>
                    </xsl:if>
                </ant>
            </xsl:for-each>
        </target>
    </xsl:template>
</xsl:stylesheet>
