<%--
 - Copyright (c) 2010, Oracle. All rights reserved.
 -
 - Redistribution and use in source and binary forms, with or without
 - modification, are permitted provided that the following conditions are met:
 -
 - * Redistributions of source code must retain the above copyright notice,
 -   this list of conditions and the following disclaimer.
 -
 - * Redistributions in binary form must reproduce the above copyright notice,
 -   this list of conditions and the following disclaimer in the documentation
 -   and/or other materials provided with the distribution.
 -
 - * Neither the name of Oracle nor the names of its contributors
 -   may be used to endorse or promote products derived from this software without
 -   specific prior written permission.
 -
 - THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 - AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 - IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 - ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 - LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 - CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 - SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 - INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 - CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 - ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 - THE POSSIBILITY OF SUCH DAMAGE.
--%>

<%--
    @author Kirill Sorokin, Kirill.Sorokin@Sun.COM
--%>
<%@page import="com.sun.glassfishesb.wlm.console.*" %>
<%@page import="java.util.*" %>
<%@page import="org.w3c.dom.*" %>
<%@page import="sun.com.jbi.wfse.wsdl.taskcommon.*" %>
<%@page import="static com.sun.glassfishesb.wlm.console.Constants.*" %>
<%@page import="static com.sun.glassfishesb.wlm.console.Utils.*" %>

<%
    final Locale locale = (Locale) request.getAttribute(LOCALE_ATTRIBUTE);
    final long taskId = (Long) request.getAttribute(TASK_ID_ATTRIBUTE);
    final String userId = (String) request.getAttribute(USER_ID_ATTRIBUTE);
    final TaskType task = (TaskType) request.getAttribute(TASK_ATTRIBUTE);

    final Element inputData = (Element) request.getAttribute(TASK_INPUT_DATA_ATTRIBUTE);

    final String data = escapeHtml(marshallElement(inputData, locale));
%>

<div id="defaultInputData">
    <pre id="defaultInputDataField"><%= data %></pre>
</div>
