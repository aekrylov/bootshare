<#include 'base.ftl'>

<#macro body>
    <form method="post">
        <p>
            Message: ${message!'none'}
        </p>
        <input type="text" name="phone" placeholder="+79876543210">
        <br>
        <input type="text" name="code" placeholder="code">
        <button type="submit" name="request_code" value="true">Request code</button>
        <br>
        <input type="submit" value="login">
    </form>
</#macro>

<@display></@display>