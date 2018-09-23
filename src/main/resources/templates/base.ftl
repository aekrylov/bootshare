<!DOCTYPE html>

<#macro head_extra></#macro>

<#macro body>
    Hello world!
</#macro>


<#macro display title=" | Bootshare">
    <html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>${title}</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
              crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
                integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
                integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
                crossorigin="anonymous"></script>

        <link rel="stylesheet" href="/static/css/custom.css">

        <@head_extra></@head_extra>
    </head>

    <body>
        <div class="container-fluid mb-2">
            <header class="navbar navbar-expand navbar-light bg-light">
                <a class="navbar-brand" href="/">BootShare</a>

                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="/upload">Upload</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/cabinet">My files</a>
                        </li>
                    </ul>
                    <#if current_user??>
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item">
                                <span class="navbar-text">${current_user.phoneNumber}</span>
                            </li>
                            <li class="nav-item">
                                <a class="btn btn-outline-secondary mx-2" href="/logout">Logout</a>
                            </li>
                        </ul>
                    </#if>
                </div>
            </header>
        </div>
        <div class="container">
                <@body></@body>
        </div>
    </body>
    </html>
</#macro>