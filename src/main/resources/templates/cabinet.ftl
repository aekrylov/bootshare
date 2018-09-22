<#include 'base.ftl'>

<#macro body>
    <p>
        Here's all active files of yours:
    </p>
    <ul>
        <#list files as file>
            <li><a href="/d/${file.id}">${file.filename}</a> - expires at ${file.expiresAt}</li>
        </#list>
    </ul>
</#macro>

<@display "Your files | Bootshare"></@display>