$(document).ready(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
    var maxSize = 5242880; // 5MB
    var cloneObj = $(".uploadDiv").clone(); // input type = file 은 readonly라서 div를 clone
    var uploadResult= $(".uploadResult ul");
    var attachImg = 'resources/image/cookie.png';
    var thumbnail = 'thumbnail_';

    $('#profile').click(function (e) {
        e.preventDefault();
        $('#user').click();
    });

    //파일 유효성 검사
    $.checkExtenstion=function(fileName, fileSize){
        if(fileSize >= maxSize) {
            alert("파일당 5MB 미만의 크기만 가능 합니다.");
            return false;
        }

        if(regex.test(fileName)){
            alert("해당 종류의 파일은 업로드할 수 없습니다.");
            return false;
        }
        return true;
    }

    function myFunction() {
        var str='/resources/image/navbar/profile.png';
        $(imgSrc).attr("src", str);
    }

    //업로드한 완료한 파일 화면에 추가
    $.showUploadedFile = function(uploadedResultArr){
        if(!uploadedResultArr || uploadedResultArr.length == 0)
            return;
        var qwe = "";

        $(uploadedResultArr).each(function(i,obj){
            qwe += "<li data-path='" + obj.uploadPath + "'";
            qwe += "data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'";
            qwe += "><div>";
            qwe += "<span>" + obj.fileName + "</span>";
            if(obj.typeFlag == 'user'){
                var fileCallPath = encodeURIComponent(obj.uploadPath + "/"
                    + obj.uuid +"_"+obj.fileName);
                fileCallPath=replaceAll(fileCallPath,"%0", "%5c");

                var list= document.getElementsByClassName("profile_image");
                for (var i = 0; i < list.length; i++) {
                    var imgSrc=list[i].lastElementChild;
                    var str = "/display?fileName=/" + fileCallPath;
                    $(imgSrc).attr("src", str);
                    imgSrc.addEventListener("error", myFunction);
                }
            }
            else if(obj.fileType) {
                //GET 방식 첨부파일 이름 사용시 공백, 한글이름이 문제 되므로 encodeURIComponent() 이용
                var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + thumbnail
                    + obj.uuid +"_"+obj.fileName);
                fileCallPath=replaceAll(fileCallPath,"%0", "%5c");
                //이미지 파일 원본 보여주기
                qwe += "<button type='button' data-file='"+fileCallPath +"/' data-type='image'";
                qwe += "class='btn btn-warning btn-circle'>" ;
                qwe += "<i class='fa fa-times'></i></button><br>";
                qwe += "<img src='/display?fileName=/" + fileCallPath + "'>";
                qwe += "</div></li>";
            }else{
                var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid +"_"+obj.fileName);
                var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");

                qwe += "<button type='button' class='btn btn-warning btn-circle' " +
                    "data-file='"+fileCallPath +"/' data-type='file'>" ;
                qwe +="<i class='fa fa-times'></i></button><br>";
                qwe += "<img src='/" + attachImg + "'></a>";
                qwe += "</div></li>";
            }
        });
        uploadResult.append(qwe);
    }

    $("input[type='file']").change(function(e){
        var flag = (e.target.id);
        var formData = new FormData();//IE 10.0 이후 적용
        var inputFile = $("input[name = 'uploadFile']");
        var files = inputFile[0].files;


        for(var i=0;i<files.length;i++){
            if(!$.checkExtenstion(files[i].name,files[i].size))
                return false;
            formData.append("uploadFile",files[i]);
        }

        $.ajax({
            url:'/uploadAjax/'+flag,
            processData:false, // 반드시 false
            contentType:false, // 반드시 false
            data:formData,
            type:'POST',
            dataType:'json',
            beforeSend: function(xhr) {
                xhr.setRequestHeader("AJAX", true);
                xhr.setRequestHeader(header, token);
            },
            success:function (result) {
                $.showUploadedFile(result); // 첨부한 파일 목록 ul에 추가
                // $(".uploadDiv").html(cloneObj.html()); // 첨부파일 재 설정
            },
            error:function(request,status,error){
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    });

    //button에 이벤트 위임 'x'아이콘을 클릭하면 서버에서 삭제하도록 이벤트 처리
    $(".uploadResult").on("click","button",function (e) {
        var targetFile = $(this).data("file");
        var type = $(this).data("type");
        var targetLi = $(this).closest("li");


        $.ajax({
            url: '/deleteFile',
            data: {fileName: targetFile, type: type},
            dataType: 'text',
            type: 'POST',
            beforeSend: function(xhr) {
                xhr.setRequestHeader("AJAX", true);
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                alert(result);
                targetLi.remove();
            },
            error: function (request, status, error) {
                console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        });
    });



});