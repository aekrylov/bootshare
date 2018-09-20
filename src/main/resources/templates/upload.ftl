<#include 'base.ftl'>

<#macro body>
    <form method="post" enctype="multipart/form-data">
        <input type="number" name="ttl" placeholder="How many days to keep your file?">
        <input type="file" name="file">
        <input type="submit" value="UPLOAD">
    </form>
</#macro>

<@display></@display>