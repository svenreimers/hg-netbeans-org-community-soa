/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.2-b01-fcs
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.12.09 at 06:25:52 PM PST 
//


package org.netbeans.modules.compapp.javaee.sunresources.generated.ejb21;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.netbeans.modules.compapp.javaee.sunresources.generated.ejb21 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EjbJar_QNAME = new QName("http://java.sun.com/xml/ns/j2ee", "ejb-jar");
    private final static QName _EjbRelationTypeEjbRelationshipRole_QNAME = new QName("http://java.sun.com/xml/ns/j2ee", "ejb-relationship-role");
    private final static QName _EjbRelationTypeEjbRelationName_QNAME = new QName("http://java.sun.com/xml/ns/j2ee", "ejb-relation-name");
    private final static QName _EjbRelationTypeDescription_QNAME = new QName("http://java.sun.com/xml/ns/j2ee", "description");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.netbeans.modules.compapp.javaee.sunresources.generated.ejb21
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CmpVersionType }
     * 
     */
    public CmpVersionType createCmpVersionType() {
        return new CmpVersionType();
    }

    /**
     * Create an instance of {@link EjbRelationshipRoleType }
     * 
     */
    public EjbRelationshipRoleType createEjbRelationshipRoleType() {
        return new EjbRelationshipRoleType();
    }

    /**
     * Create an instance of {@link XsdIntegerType }
     * 
     */
    public XsdIntegerType createXsdIntegerType() {
        return new XsdIntegerType();
    }

    /**
     * Create an instance of {@link ResSharingScopeType }
     * 
     */
    public ResSharingScopeType createResSharingScopeType() {
        return new ResSharingScopeType();
    }

    /**
     * Create an instance of {@link ActivationConfigType }
     * 
     */
    public ActivationConfigType createActivationConfigType() {
        return new ActivationConfigType();
    }

    /**
     * Create an instance of {@link EjbClassType }
     * 
     */
    public EjbClassType createEjbClassType() {
        return new EjbClassType();
    }

    /**
     * Create an instance of {@link SecurityRoleRefType }
     * 
     */
    public SecurityRoleRefType createSecurityRoleRefType() {
        return new SecurityRoleRefType();
    }

    /**
     * Create an instance of {@link EjbRefTypeType }
     * 
     */
    public EjbRefTypeType createEjbRefTypeType() {
        return new EjbRefTypeType();
    }

    /**
     * Create an instance of {@link EnterpriseBeansType }
     * 
     */
    public EnterpriseBeansType createEnterpriseBeansType() {
        return new EnterpriseBeansType();
    }

    /**
     * Create an instance of {@link SessionBeanType }
     * 
     */
    public SessionBeanType createSessionBeanType() {
        return new SessionBeanType();
    }

    /**
     * Create an instance of {@link RoleNameType }
     * 
     */
    public RoleNameType createRoleNameType() {
        return new RoleNameType();
    }

    /**
     * Create an instance of {@link CmrFieldType }
     * 
     */
    public CmrFieldType createCmrFieldType() {
        return new CmrFieldType();
    }

    /**
     * Create an instance of {@link MethodNameType }
     * 
     */
    public MethodNameType createMethodNameType() {
        return new MethodNameType();
    }

    /**
     * Create an instance of {@link MessageDrivenBeanType }
     * 
     */
    public MessageDrivenBeanType createMessageDrivenBeanType() {
        return new MessageDrivenBeanType();
    }

    /**
     * Create an instance of {@link QueryMethodType }
     * 
     */
    public QueryMethodType createQueryMethodType() {
        return new QueryMethodType();
    }

    /**
     * Create an instance of {@link MessageDestinationType }
     * 
     */
    public MessageDestinationType createMessageDestinationType() {
        return new MessageDestinationType();
    }

    /**
     * Create an instance of {@link EnvEntryTypeValuesType }
     * 
     */
    public EnvEntryTypeValuesType createEnvEntryTypeValuesType() {
        return new EnvEntryTypeValuesType();
    }

    /**
     * Create an instance of {@link MessageDestinationLinkType }
     * 
     */
    public MessageDestinationLinkType createMessageDestinationLinkType() {
        return new MessageDestinationLinkType();
    }

    /**
     * Create an instance of {@link SecurityRoleType }
     * 
     */
    public SecurityRoleType createSecurityRoleType() {
        return new SecurityRoleType();
    }

    /**
     * Create an instance of {@link MessageDestinationRefType }
     * 
     */
    public MessageDestinationRefType createMessageDestinationRefType() {
        return new MessageDestinationRefType();
    }

    /**
     * Create an instance of {@link TrueFalseType }
     * 
     */
    public TrueFalseType createTrueFalseType() {
        return new TrueFalseType();
    }

    /**
     * Create an instance of {@link AssemblyDescriptorType }
     * 
     */
    public AssemblyDescriptorType createAssemblyDescriptorType() {
        return new AssemblyDescriptorType();
    }

    /**
     * Create an instance of {@link QueryType }
     * 
     */
    public QueryType createQueryType() {
        return new QueryType();
    }

    /**
     * Create an instance of {@link ListenerType }
     * 
     */
    public ListenerType createListenerType() {
        return new ListenerType();
    }

    /**
     * Create an instance of {@link EjbNameType }
     * 
     */
    public EjbNameType createEjbNameType() {
        return new EjbNameType();
    }

    /**
     * Create an instance of {@link PathType }
     * 
     */
    public PathType createPathType() {
        return new PathType();
    }

    /**
     * Create an instance of {@link LocalType }
     * 
     */
    public LocalType createLocalType() {
        return new LocalType();
    }

    /**
     * Create an instance of {@link XsdBooleanType }
     * 
     */
    public XsdBooleanType createXsdBooleanType() {
        return new XsdBooleanType();
    }

    /**
     * Create an instance of {@link UrlPatternType }
     * 
     */
    public UrlPatternType createUrlPatternType() {
        return new UrlPatternType();
    }

    /**
     * Create an instance of {@link String }
     * 
     */
    public String createString() {
        return new String();
    }

    /**
     * Create an instance of {@link PortComponentRefType }
     * 
     */
    public PortComponentRefType createPortComponentRefType() {
        return new PortComponentRefType();
    }

    /**
     * Create an instance of {@link JndiNameType }
     * 
     */
    public JndiNameType createJndiNameType() {
        return new JndiNameType();
    }

    /**
     * Create an instance of {@link RelationshipsType }
     * 
     */
    public RelationshipsType createRelationshipsType() {
        return new RelationshipsType();
    }

    /**
     * Create an instance of {@link CmpFieldType }
     * 
     */
    public CmpFieldType createCmpFieldType() {
        return new CmpFieldType();
    }

    /**
     * Create an instance of {@link MethodPermissionType }
     * 
     */
    public MethodPermissionType createMethodPermissionType() {
        return new MethodPermissionType();
    }

    /**
     * Create an instance of {@link FullyQualifiedClassType }
     * 
     */
    public FullyQualifiedClassType createFullyQualifiedClassType() {
        return new FullyQualifiedClassType();
    }

    /**
     * Create an instance of {@link RunAsType }
     * 
     */
    public RunAsType createRunAsType() {
        return new RunAsType();
    }

    /**
     * Create an instance of {@link SecurityIdentityType }
     * 
     */
    public SecurityIdentityType createSecurityIdentityType() {
        return new SecurityIdentityType();
    }

    /**
     * Create an instance of {@link MultiplicityType }
     * 
     */
    public MultiplicityType createMultiplicityType() {
        return new MultiplicityType();
    }

    /**
     * Create an instance of {@link HomeType }
     * 
     */
    public HomeType createHomeType() {
        return new HomeType();
    }

    /**
     * Create an instance of {@link MessageDestinationTypeType }
     * 
     */
    public MessageDestinationTypeType createMessageDestinationTypeType() {
        return new MessageDestinationTypeType();
    }

    /**
     * Create an instance of {@link ExcludeListType }
     * 
     */
    public ExcludeListType createExcludeListType() {
        return new ExcludeListType();
    }

    /**
     * Create an instance of {@link EjbLinkType }
     * 
     */
    public EjbLinkType createEjbLinkType() {
        return new EjbLinkType();
    }

    /**
     * Create an instance of {@link XsdPositiveIntegerType }
     * 
     */
    public XsdPositiveIntegerType createXsdPositiveIntegerType() {
        return new XsdPositiveIntegerType();
    }

    /**
     * Create an instance of {@link XsdStringType }
     * 
     */
    public XsdStringType createXsdStringType() {
        return new XsdStringType();
    }

    /**
     * Create an instance of {@link DescriptionType }
     * 
     */
    public DescriptionType createDescriptionType() {
        return new DescriptionType();
    }

    /**
     * Create an instance of {@link JavaIdentifierType }
     * 
     */
    public JavaIdentifierType createJavaIdentifierType() {
        return new JavaIdentifierType();
    }

    /**
     * Create an instance of {@link TransAttributeType }
     * 
     */
    public TransAttributeType createTransAttributeType() {
        return new TransAttributeType();
    }

    /**
     * Create an instance of {@link ResultTypeMappingType }
     * 
     */
    public ResultTypeMappingType createResultTypeMappingType() {
        return new ResultTypeMappingType();
    }

    /**
     * Create an instance of {@link ResourceEnvRefType }
     * 
     */
    public ResourceEnvRefType createResourceEnvRefType() {
        return new ResourceEnvRefType();
    }

    /**
     * Create an instance of {@link JavaTypeType }
     * 
     */
    public JavaTypeType createJavaTypeType() {
        return new JavaTypeType();
    }

    /**
     * Create an instance of {@link ParamValueType }
     * 
     */
    public ParamValueType createParamValueType() {
        return new ParamValueType();
    }

    /**
     * Create an instance of {@link MethodType }
     * 
     */
    public MethodType createMethodType() {
        return new MethodType();
    }

    /**
     * Create an instance of {@link CmrFieldTypeType }
     * 
     */
    public CmrFieldTypeType createCmrFieldTypeType() {
        return new CmrFieldTypeType();
    }

    /**
     * Create an instance of {@link EjbRefType }
     * 
     */
    public EjbRefType createEjbRefType() {
        return new EjbRefType();
    }

    /**
     * Create an instance of {@link ServiceRefType }
     * 
     */
    public ServiceRefType createServiceRefType() {
        return new ServiceRefType();
    }

    /**
     * Create an instance of {@link ServiceRefHandlerType }
     * 
     */
    public ServiceRefHandlerType createServiceRefHandlerType() {
        return new ServiceRefHandlerType();
    }

    /**
     * Create an instance of {@link EmptyType }
     * 
     */
    public EmptyType createEmptyType() {
        return new EmptyType();
    }

    /**
     * Create an instance of {@link EjbRelationType }
     * 
     */
    public EjbRelationType createEjbRelationType() {
        return new EjbRelationType();
    }

    /**
     * Create an instance of {@link XsdAnyURIType }
     * 
     */
    public XsdAnyURIType createXsdAnyURIType() {
        return new XsdAnyURIType();
    }

    /**
     * Create an instance of {@link LocalHomeType }
     * 
     */
    public LocalHomeType createLocalHomeType() {
        return new LocalHomeType();
    }

    /**
     * Create an instance of {@link EjbJarType }
     * 
     */
    public EjbJarType createEjbJarType() {
        return new EjbJarType();
    }

    /**
     * Create an instance of {@link EnvEntryType }
     * 
     */
    public EnvEntryType createEnvEntryType() {
        return new EnvEntryType();
    }

    /**
     * Create an instance of {@link TransactionTypeType }
     * 
     */
    public TransactionTypeType createTransactionTypeType() {
        return new TransactionTypeType();
    }

    /**
     * Create an instance of {@link MethodIntfType }
     * 
     */
    public MethodIntfType createMethodIntfType() {
        return new MethodIntfType();
    }

    /**
     * Create an instance of {@link MethodParamsType }
     * 
     */
    public MethodParamsType createMethodParamsType() {
        return new MethodParamsType();
    }

    /**
     * Create an instance of {@link EjbLocalRefType }
     * 
     */
    public EjbLocalRefType createEjbLocalRefType() {
        return new EjbLocalRefType();
    }

    /**
     * Create an instance of {@link ContainerTransactionType }
     * 
     */
    public ContainerTransactionType createContainerTransactionType() {
        return new ContainerTransactionType();
    }

    /**
     * Create an instance of {@link PersistenceTypeType }
     * 
     */
    public PersistenceTypeType createPersistenceTypeType() {
        return new PersistenceTypeType();
    }

    /**
     * Create an instance of {@link RemoteType }
     * 
     */
    public RemoteType createRemoteType() {
        return new RemoteType();
    }

    /**
     * Create an instance of {@link SessionTypeType }
     * 
     */
    public SessionTypeType createSessionTypeType() {
        return new SessionTypeType();
    }

    /**
     * Create an instance of {@link RelationshipRoleSourceType }
     * 
     */
    public RelationshipRoleSourceType createRelationshipRoleSourceType() {
        return new RelationshipRoleSourceType();
    }

    /**
     * Create an instance of {@link MessageDestinationUsageType }
     * 
     */
    public MessageDestinationUsageType createMessageDestinationUsageType() {
        return new MessageDestinationUsageType();
    }

    /**
     * Create an instance of {@link XsdQNameType }
     * 
     */
    public XsdQNameType createXsdQNameType() {
        return new XsdQNameType();
    }

    /**
     * Create an instance of {@link GenericBooleanType }
     * 
     */
    public GenericBooleanType createGenericBooleanType() {
        return new GenericBooleanType();
    }

    /**
     * Create an instance of {@link ResAuthType }
     * 
     */
    public ResAuthType createResAuthType() {
        return new ResAuthType();
    }

    /**
     * Create an instance of {@link DisplayNameType }
     * 
     */
    public DisplayNameType createDisplayNameType() {
        return new DisplayNameType();
    }

    /**
     * Create an instance of {@link EjbRefNameType }
     * 
     */
    public EjbRefNameType createEjbRefNameType() {
        return new EjbRefNameType();
    }

    /**
     * Create an instance of {@link XsdNMTOKENType }
     * 
     */
    public XsdNMTOKENType createXsdNMTOKENType() {
        return new XsdNMTOKENType();
    }

    /**
     * Create an instance of {@link ResourceRefType }
     * 
     */
    public ResourceRefType createResourceRefType() {
        return new ResourceRefType();
    }

    /**
     * Create an instance of {@link IconType }
     * 
     */
    public IconType createIconType() {
        return new IconType();
    }

    /**
     * Create an instance of {@link XsdNonNegativeIntegerType }
     * 
     */
    public XsdNonNegativeIntegerType createXsdNonNegativeIntegerType() {
        return new XsdNonNegativeIntegerType();
    }

    /**
     * Create an instance of {@link EntityBeanType }
     * 
     */
    public EntityBeanType createEntityBeanType() {
        return new EntityBeanType();
    }

    /**
     * Create an instance of {@link ActivationConfigPropertyType }
     * 
     */
    public ActivationConfigPropertyType createActivationConfigPropertyType() {
        return new ActivationConfigPropertyType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjbJarType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://java.sun.com/xml/ns/j2ee", name = "ejb-jar")
    public JAXBElement<EjbJarType> createEjbJar(EjbJarType value) {
        return new JAXBElement<EjbJarType>(_EjbJar_QNAME, EjbJarType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjbRelationshipRoleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://java.sun.com/xml/ns/j2ee", name = "ejb-relationship-role", scope = EjbRelationType.class)
    public JAXBElement<EjbRelationshipRoleType> createEjbRelationTypeEjbRelationshipRole(EjbRelationshipRoleType value) {
        return new JAXBElement<EjbRelationshipRoleType>(_EjbRelationTypeEjbRelationshipRole_QNAME, EjbRelationshipRoleType.class, EjbRelationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://java.sun.com/xml/ns/j2ee", name = "ejb-relation-name", scope = EjbRelationType.class)
    public JAXBElement<String> createEjbRelationTypeEjbRelationName(String value) {
        return new JAXBElement<String>(_EjbRelationTypeEjbRelationName_QNAME, String.class, EjbRelationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DescriptionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://java.sun.com/xml/ns/j2ee", name = "description", scope = EjbRelationType.class)
    public JAXBElement<DescriptionType> createEjbRelationTypeDescription(DescriptionType value) {
        return new JAXBElement<DescriptionType>(_EjbRelationTypeDescription_QNAME, DescriptionType.class, EjbRelationType.class, value);
    }

}
