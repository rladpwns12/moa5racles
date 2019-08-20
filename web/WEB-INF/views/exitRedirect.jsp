<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="/resources/js/jquery-3.4.1.min.js"></script>
<script src="https://sdk.accountkit.com/en_US/sdk.js"></script>
<meta name="viewport" content="width=device-width, user-scalable=no">
<script>

    function fclose(){
        window.close();
        return false;
    }

</script>
<style>
    body{
        margin:0;
    }
    .title{
        font-family: '나눔고딕', sans-serif;
        margin-top: 100px;
        height: 150px;
        /* margin: 0 auto; */
        text-align: center;
        font-size: 25px;
        font-weight: 600;

    }
    .closeBtn{
        width: 430px;
        margin: 0 27;
    }
    .btn{
        display: inline-block;
        line-height: 50px;
        padding: 0 50px;
        -webkit-transition: all 0.4s ease;
        -o-transition: all 0.4s ease;
        -moz-transition: all 0.4s ease;
        transition: all 0.4s ease;
        cursor: pointer;
        font-size: 18px;
        color: #fff;
        width: 430px;
        outline: none;
        background: #999999;
        border: none;
    }
    .btn:hover {
        background: #423257;
    }
    .header{
        display: table;
        height: 42px;
        width: 100%;
    }
    .headerWord{
        display: table-cell;
        vertical-align: middle;
        width: 100%;
        text-align: center;
    }
</style>
<body>
    <div class="header" style="background-color: #8267C2">
        <div class="headerWord" style="color: #FFFFFF">전화번호 확인</div>
    </div>
    <div class="title">핸드폰 인증이 종료됩니다<br><br>
    종료하려면 확인을 눌러주세요</div>
    <div class="closeBtn">
    <button onclick="fclose()" class="btn">확인</button>
    </div>
</body>
</html>
