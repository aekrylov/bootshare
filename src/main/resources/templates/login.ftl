<#include 'base.ftl'>

<#macro head_extra>
    <script type="text/javascript" src="/static/js/login.js"></script>
</#macro>

<#macro body>
    <div class="row justify-content-center">
        <div class="center-block col-lg-5">
            <h1>Sign in to Bootshare</h1>
            <form method="post" action="/auth/login">
                <#if message??>
                    <div class="alert alert-danger" role="alert">
                        ${message!''}
                    </div>
                </#if>
                <div class="form-group row">
                    <label for="phoneNumber" class="col-md-4 col-form-label">Phone number</label>
                    <div class="col-md-8">
                        <input type="text" name="phone" id="phoneNumber" placeholder="+79876543210"
                               class="form-control">
                    </div>
                </div>

                <div style="display: none;" id="nextStepForm">
                    <p>
                        A confirmation code was sent to your phone number
                    </p>
                    <div class="form-group row">
                        <label for="code" class="col-md-4 col-form-label">Code from SMS</label>
                        <div class="col-md-8 input-group">
                            <input type="text" name="code" id="code" placeholder="123456" class="form-control">
                            <div class="input-group-append">
                                <button type="button" id="requestCode" class="btn btn-outline-secondary">Resend code
                                </button>
                            </div>
                        </div>
                        <small id="codeHelp" class="form-text text-muted offset-md-4"></small>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary" id="submitBtn" data-hasnext="true">Next</button>

            </form>
        </div>
    </div>
</#macro>

<@display "Sign in to Bootshare"></@display>