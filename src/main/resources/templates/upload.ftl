<#include 'base.ftl'>

<#macro head_extra>
    <link rel="stylesheet" href="/static/css/fileinput.css"/>
    <script type="text/javascript" src="/static/js/fileinput.min.js"></script>
    <script type="text/javascript" src="/static/js/upload.js"></script>
</#macro>

<#macro body>
    <div class="row">
        <form method="post" enctype="multipart/form-data" class="form-horizontal col-lg-8">
            <h2>Upload a new file</h2>
            <div class="form-group row">
                <label for="fileInput" class="col-sm-3 col-form-label">File</label>
                <div class="col-sm-9">
                    <input type="file" name="file" id="fileInput" class="form-control-file">
                    <span class="form-text text-muted" id="fileInputErrors">More help text</span>
                    <span class="form-text text-muted">Max file size is <strong>2GB</strong></span>
                </div>
            </div>
            <div class="form-group row">
                <label for="ttlInput" class="col-sm-3 col-form-label">Expires in</label>
                <div class="col-sm-9 input-group">
                    <div class="input-group">
                        <input type="number" name="ttl" id="ttlInput" placeholder="7" class="form-control"
                               min="1" required>
                        <div class="input-group-append">
                            <span class="input-group-text">days</span>
                        </div>
                    </div>
                    <span class="form-text text-muted">The file will be unavailable after expiration</span>
                </div>
            </div>
            <div class="form-group row">
                <div class="offset-sm-3 col-sm-9">
                    <button type="submit" class="btn btn-primary">Upload</button>
                </div>
            </div>
        </form>
    </div>
</#macro>

<@display></@display>