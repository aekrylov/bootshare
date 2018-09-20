<!DOCTYPE html>

<#macro head>
    <meta charset="utf-8">
</#macro>

<#macro body>
    Hello world!
</#macro>


<#macro display>
    <html>
    <head>
        <@head></@head>
    </head>

    <body>
        <@body></@body>
    </body>
    </html>
</#macro>