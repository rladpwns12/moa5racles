
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
<script>
    $(document).ready(function() {
        if(window.android){
            console.info("android");
            window.android.callSettingsActivity("이메일 구분가능한 정보");
        }
        else{
            console.info("web");
        }

    });
</script>
<body>

</body>
</html>
