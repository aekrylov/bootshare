<#include 'base.ftl'>

<#macro head_extra>
    <script type="text/javascript" src="/static/js/jquery.countdown.min.js"></script>
    <script type="text/javascript" src="/static/js/login.js"></script>
</#macro>

<#macro body>
    <div class="row justify-content-center vertical-center">
        <div class="center-block col-lg-6">
            <h2>Sign in to Bootshare</h2>
            <form method="post" action="/auth/login" class="needs-validation" novalidate id="loginForm">
                <#if auth_failed>
                    <div class="alert alert-danger" role="alert">
                        ${message!'Authentication failed, check your details'}
                    </div>
                </#if>
                <div class="form-group row">
                    <label for="phoneNumber" class="col-md-4 col-form-label">Phone number</label>
                    <div class="col-md-8">
                        <input type="text" name="phone" id="phoneNumber" placeholder="+79876543210"
                               class="form-control" pattern="\+7[0-9]{10}" required>
                        <div class="invalid-feedback">
                            Please enter Russian cellphone number (e.g. +79876543210)
                        </div>
                    </div>
                </div>

                <div style="display: none;" id="nextStepForm">
                    <div class="form-group row">
                        <label for="code" class="col-md-4 col-form-label">Code from SMS</label>
                        <div class="col-md-8">
                            <div class="input-group">
                                <input type="text" name="code" id="code" placeholder="123456" class="form-control"
                                       minlength="6" maxlength="6" required disabled>
                                <div class="input-group-append">
                                    <button type="button" id="requestCode" class="btn btn-outline-secondary">Resend code
                                    </button>
                                </div>
                            </div>
                            <span id="codeHelp" class="form-text text-muted"></span>
                        </div>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary" id="submitBtn" data-hasnext="true">Next</button>

            </form>
        </div>
    </div>
</#macro>

<@display "Sign in to Bootshare"></@display>