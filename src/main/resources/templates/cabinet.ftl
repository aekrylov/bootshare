<#include 'base.ftl'>

<#macro head_extra>
    <script type="text/javascript" src="/static/js/cabinet.js"></script>
</#macro>

<#macro body>
    <p>
        Here's all active files of yours:
    </p>
    <ul>
        <#list files as file>
            <li>
                <a href="/f/${file.id}">${file.filename}</a> - expires at ${file.expiresAt}
                <button class="btn btn-danger deleteFile ml-auto" data-id="${file.id}">Delete</button>
            </li>
        </#list>
    </ul>
</#macro>

<@display "Your files | Bootshare"></@display>