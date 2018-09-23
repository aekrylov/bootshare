<#include 'base.ftl'>

<#macro body>
<p>
    You're trying to download <strong>${filename}</strong>.
</p>
<p>
    <a href="/f/${id}/download" target="_blank">Click here</a> to proceed.
</p>
</#macro>

<@display></@display>