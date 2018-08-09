<#-- @ftlvariable name="" type="me.desair.dropwizard.views.SubmissionCompletedView" -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload Successful!</title>
</head>
<body>

<h1>Upload Successful!</h1>
<p>We successfully received your submission with the following information:</p>
<ul>
    <li><b>Creator:</b> ${creator}</li>
    <li><b>Description:</b> ${description}</li>
    <li><b>Files:</b>
        <ul>
            <#list files as file>
                <li>${file}</li>
            </#list>
        </ul>
    </li>
</ul>
</body>
</html>