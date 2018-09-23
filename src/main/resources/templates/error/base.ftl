<#include '../base.ftl'>

<#macro jumbo_error title text>
    <div class="jumbotron">
        <h1>${title}</h1>
        <p class="lead">${text}</p>
        <p class="lead">
            <a href="/">Go home</a>
        </p>
    </div>
</#macro>