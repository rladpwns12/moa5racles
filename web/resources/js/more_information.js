function setDefault(e){
    e.src='/resources/image/loading.gif';
    $('.img_btn').remove();
}

$(document).ready(function () {
    $.reviewList(1, 1);

    $('#like_btn').click(function () {
        var like = $('#like_btn > i');

        if( like.attr('class') == 'far fa-heart'){
            like.attr('class','fas fa-heart');
        }
        else{
            like.attr('class','far fa-heart');
        }
    });



    var addrLat = 0;
    var addrLng = 0;
    var addr = $('#baseAddress').val();

    function searchLocation(adr) {
        var geocoder = new kakao.maps.services.Geocoder();
        var callback = function (result, status) {
            if (status === kakao.maps.services.Status.OK) {
                addrLat = result[0].y;
                addrLng = result[0].x;
            }
        };
        geocoder.addressSearch(adr, callback);
    }//-- end of find lat&lng
    searchLocation(addr);
    setTimeout(function(){
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div
            mapOption = {
                center: new kakao.maps.LatLng(addrLat,addrLng), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            };

        var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
        var markerPosition = new kakao.maps.LatLng(addrLat, addrLng);
        var marker = new kakao.maps.Marker({
            position: markerPosition
        });
        marker.setMap(map);
    }, 500)
});


function searchLocation(adr) {
    var geocoder = new kakao.maps.services.Geocoder();
    var callback = function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
            addrLat = result[0].y;
            addrLng = result[0].x;
        }
    };
    geocoder.addressSearch(adr, callback);
}

// 폼 전송
$.replyReview = function () {
    var articleNum = location.href.split('/')[location.href.split('/').length - 1];
    var comment = $('#new-review').val();
    var rating = $('#ratings-hidden').val();

    // 리뷰글이 공백이거나 1000자리를 넘을 때
    if (!isCommentValid(comment)) {
        return;
    }

    // 별점을 입력하지 않았을 때
    if (!isRatingValid(rating)) {
        return;
    }

    var formData = {
        parentNum: 0,
        comment: comment,
        rating: rating
    }

    var jsonData = JSON.stringify(formData);

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: "POST",
        url: "/review/" + articleNum,
        contentType: 'application/json; charset=utf-8',
        data: jsonData,
        cache: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("AJAX", true);
            xhr.setRequestHeader(header, token);
        },
        success: function (check) {
            if (check == "ok") {
                alert("답글을 추가하였습니다.");
                $.reviewList(1, 1);
                $('#new-review').val('');
                $('#new-review').autosize({append: "\n"});

                var reviewBox = $('#post-review-box');
                var newReview = $('#new-review');
                var openReviewBtn = $('#open-review-box');
                var closeReviewBtn = $('#close-review-box');

                reviewBox.slideUp(300, function () {
                    newReview.focus();
                    openReviewBtn.fadeIn(200);
                });
                closeReviewBtn.hide();
            } else if (check == "noAuthority") {
                alert("물건을 맡긴 사람만 답글을 달 수 있습니다.");
            } else if (check == "overFlow") {
                alert("하나의 보관당 하나의 답글을 달 수 있습니다.");
            } else {
                alert("오류가 발생하였습니다. 잠시뒤 시도해 주세요...");
            }
        },
        error:function(request){
            var msg = request.responseText;
            switch (msg) {
                case "noAuthority" :alert("물건을 맡긴 사람만 답글을 달 수 있습니다.");break;
                case "overFlow" : alert("하나의 보관당 하나의 답글을 달 수 있습니다.");break;
                default :  alert("오류가 발생하였습니다. 잠시뒤 시도해 주세요...");
            }
        }
    });
}

function myFunction() {
    var str = '/resources/image/navbar/profile.png';
    $(imgSrc).attr("src", str);
}

// 리뷰 글 양식
$.reviewList = function (section, pageNum) {
    var articleNum = location.href.split('/')[location.href.split('/').length - 1];

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: "GET",
        url: "/review/" + articleNum,
        data: {
            section: section,
            pageNum: pageNum
        },
        dataType: "JSON",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("AJAX", true);
            xhr.setRequestHeader(header, token);
        },
        success: function (data) {
            var thumbnail = 'thumbnail_';
            $('.review_list').remove();

            if (Object.keys(data.reviewList).length == 0) {

            } else {
                var str = '<div class="review_list">'
                    + '<table  class="review_table">';
                for (var i = 0; i < Object.keys(data.reviewList).length; i++) {

                    var profile = data.reviewList[i].profile;
                    var fileCallPath = encodeURIComponent(profile.uploadPath + "/" + thumbnail
                        + profile.uuid + "_" + profile.fileName);

                    var imgSrc = $('<img>', {
                        src: '/display?fileName=/' + fileCallPath,
                        alt: 'profile',
                        onerror: 'this.src="/resources/image/navbar/profile.png"'
                    }).css({
                        borderRadius: '100px',
                        width: '40px',
                        height: '40px'
                    });
                    var path = "/display?fileName=/" + fileCallPath;
                    $(imgSrc).attr("src", path);

                    str += '<tr style="height: 50px">'
                        + '<td class="review_img">';
                    str += imgSrc[0].outerHTML;
                    str += '</td>'
                        + '<td class="review_id">' + data.reviewList[i].nick + '</td>'
                        + '<td class="review_star">';
                    var starPoint = data.reviewList[i].starPoint;
                    for (var j = 0; j < starPoint; j++) {
                        str += '<i class="fas fa-star"></i>';
                    }
                    for (var j = 0; j < 5 - starPoint; j++) {
                        str += '<i class="far fa-star"></i>';
                    }
                    str += '&nbsp;' + starPoint + '.0'
                        + '</td>'
                        + '<td class="review_content" colspan="3" >'
                        + data.reviewList[i].content
                        + '</td>'
                        + '<td class="review_write_date">'
                        + data.reviewList[i].writeDate
                        + '</td>'
                        + '</tr>';
                }
                str += '</table>'
                    + '</div>';
            }
            $('.review_host').after(str);
            $('.review_paging').remove();
            var strf = '<div class="review_paging">';

            if(data.totReviews == 0){

            }
            else if(section == 1 && pageNum == 1) {
                strf += '<i class="fas fa-angle-left no-uline" style="margin-right: 5px;" />';
            }
            else if (section == 1 && pageNum != 1) {
                strf += '<i class="fas fa-angle-left no-uline" onclick="$.reviewList(1,1);">&nbsp;</i>';
            }
            else if (section > 1) {
                strf += '<i class="fas fa-angle-left no-uline" onclick="$.reviewList(section, 1)"/>';
            }
            else{
                strf += '<i class="fas fa-angle-left no-uline" style="margin-right: 5px;" onclick="$.reviewList(' + (section - 1) + ',' + pageNum + ');" />';
            }
            if (parseInt(data.totReviews / 50) > section - 1) {
                for (var i = 1; i <= 10; i++) {
                    if (i == pageNum)
                        strf += '<span class="no-uline sel-page" onclick="$.reviewList(' +
                            section +
                            ',' +
                            i +
                            ');">' + i + '&nbsp;</span>';
                    else
                        strf += '<span class="no-uline" onclick="$.reviewList(' +
                            section +
                            ',' +
                            i +
                            ');">' + i + '&nbsp;</span>';
                }
            } else {
                var lastReview = parseInt((data.totReviews % 50) / 5);
                if (data.totReviews % 5 != 0)
                    lastReview += 1;
                for (var i = 1; i <= lastReview; i++) {
                    if (i == pageNum)
                        strf += '<span class="no-uline sel-page" onclick="$.reviewList(' +
                            section +
                            ',' +
                            i +
                            ');">' + i + '&nbsp;</span>';
                    else
                        strf += '<span class="no-uline" onclick="$.reviewList(' +
                            section +
                            ',' +
                            i +
                            ');">' + i + '&nbsp;</span>';
                }
            }
            var lastPage = parseInt((data.totReviews % 50) / 5) + 1;


            if(data.totReviews == 0){

            }
            else if(((section - 1) * 10 + pageNum) == Math.ceil(data.totReviews / 5)){
                strf += '<i class="fas fa-angle-right no-uline" />';
            }
            else{
                strf += '<i class="fas fa-angle-right no-uline" onclick="$.reviewList(' +section +',' + lastPage +');" />';
            }

            // if (section == parseInt(data.totReviews / 50) + 1 && (section - 1) * 50 + pageNum * 5 < data.totReviews) {
            //     strf += '<i class="fas fa-angle-right no-uline" onclick="$.reviewList(' +
            //         section +
            //         ',' +
            //         lastPage +
            //         ');">&nbsp;</i>';
            // }
            strf += '</div>';
            $('.review_footer').append(strf);
            // $('.review_btn').before(strf);
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

function magnify(imgID, zoom) {
    var img, glass, w, h, bw;
    img = document.getElementById(imgID);
    /*create magnifier glass:*/
    glass = document.createElement("DIV");
    glass.setAttribute("class", "img-magnifier-glass");
    /*insert magnifier glass:*/
    img.parentElement.insertBefore(glass, img);
    /*set background properties for the magnifier glass:*/
    glass.style.backgroundImage = "url('" + img.src + "')";
    glass.style.backgroundRepeat = "no-repeat";
    glass.style.backgroundSize = (img.width * zoom) + "px " + (img.height * zoom) + "px";
    bw = 3;
    w = glass.offsetWidth / 2;
    h = glass.offsetHeight / 2;
    /*execute a function when someone moves the magnifier glass over the image:*/
    glass.addEventListener("mousemove", moveMagnifier);
    img.addEventListener("mousemove", moveMagnifier);
    /*and also for touch screens:*/
    glass.addEventListener("touchmove", moveMagnifier);
    img.addEventListener("touchmove", moveMagnifier);

    function moveMagnifier(e) {
        var pos, x, y;
        /*prevent any other actions that may occur when moving over the image*/
        e.preventDefault();
        /*get the cursor's x and y positions:*/
        pos = getCursorPos(e);
        x = pos.x;
        y = pos.y;
        /*prevent the magnifier glass from being positioned outside the image:*/
        if (x > img.width - (w / zoom)) {
            x = img.width - (w / zoom);
        }
        if (x < w / zoom) {
            x = w / zoom;
        }
        if (y > img.height - (h / zoom)) {
            y = img.height - (h / zoom);
        }
        if (y < h / zoom) {
            y = h / zoom;
        }
        /*set the position of the magnifier glass:*/
        glass.style.left = (x - w) + "px";
        glass.style.top = (y - h) + "px";
        /*display what the magnifier glass "sees":*/
        glass.style.backgroundPosition = "-" + ((x * zoom) - w + bw) + "px -" + ((y * zoom) - h + bw) + "px";
    }

    function getCursorPos(e) {
        var a, x = 0, y = 0;
        e = e || window.event;
        /*get the x and y positions of the image:*/
        a = img.getBoundingClientRect();
        /*calculate the cursor's x and y coordinates, relative to the image:*/
        x = e.pageX - a.left;
        y = e.pageY - a.top;
        /*consider any page scrolling:*/
        x = x - window.pageXOffset;
        y = y - window.pageYOffset;
        return {x: x, y: y};
    }
}

(function (e) {
    var t, o = {className: "autosizejs", append: "", callback: !1, resizeDelay: 10},
        i = '<textarea tabindex="-1" style="position:absolute; top:-999px; left:0; right:auto; bottom:auto; border:0; padding: 0; -moz-box-sizing:content-box; -webkit-box-sizing:content-box; box-sizing:content-box; word-wrap:break-word; height:0 !important; min-height:0 !important; overflow:hidden; transition:none; -webkit-transition:none; -moz-transition:none;"/>',
        n = ["fontFamily", "fontSize", "fontWeight", "fontStyle", "letterSpacing", "textTransform", "wordSpacing", "textIndent"],
        s = e(i).data("autosize", !0)[0];
    s.style.lineHeight = "99px", "99px" === e(s).css("lineHeight") && n.push("lineHeight"), s.style.lineHeight = "", e.fn.autosize = function (i) {
        return this.length ? (i = e.extend({}, o, i || {}), s.parentNode !== document.body && e(document.body).append(s), this.each(function () {
            function o() {
                var t, o;
                "getComputedStyle" in window ? (t = window.getComputedStyle(u, null), o = u.getBoundingClientRect().width, e.each(["paddingLeft", "paddingRight", "borderLeftWidth", "borderRightWidth"], function (e, i) {
                    o -= parseInt(t[i], 10)
                }), s.style.width = o + "px") : s.style.width = Math.max(p.width(), 0) + "px"
            }

            function a() {
                var a = {};
                if (t = u, s.className = i.className, d = parseInt(p.css("maxHeight"), 10), e.each(n, function (e, t) {
                    a[t] = p.css(t)
                }), e(s).css(a), o(), window.chrome) {
                    var r = u.style.width;
                    u.style.width = "0px", u.offsetWidth, u.style.width = r
                }
            }

            function r() {
                var e, n;
                t !== u ? a() : o(), s.value = u.value + i.append, s.style.overflowY = u.style.overflowY, n = parseInt(u.style.height, 10), s.scrollTop = 0, s.scrollTop = 9e4, e = s.scrollTop, d && e > d ? (u.style.overflowY = "scroll", e = d) : (u.style.overflowY = "hidden", c > e && (e = c)), e += w, n !== e && (u.style.height = e + "px", f && i.callback.call(u, u))
            }

            function l() {
                clearTimeout(h), h = setTimeout(function () {
                    var e = p.width();
                    e !== g && (g = e, r())
                }, parseInt(i.resizeDelay, 10))
            }

            var d, c, h, u = this, p = e(u), w = 0, f = e.isFunction(i.callback), z = {
                height: u.style.height,
                overflow: u.style.overflow,
                overflowY: u.style.overflowY,
                wordWrap: u.style.wordWrap,
                resize: u.style.resize
            }, g = p.width();
            p.data("autosize") || (p.data("autosize", !0), ("border-box" === p.css("box-sizing") || "border-box" === p.css("-moz-box-sizing") || "border-box" === p.css("-webkit-box-sizing")) && (w = p.outerHeight() - p.height()), c = Math.max(parseInt(p.css("minHeight"), 10) - w || 0, p.height()), p.css({
                overflow: "hidden",
                overflowY: "hidden",
                wordWrap: "break-word",
                resize: "none" === p.css("resize") || "vertical" === p.css("resize") ? "none" : "horizontal"
            }), "onpropertychange" in u ? "oninput" in u ? p.on("input.autosize keyup.autosize", r) : p.on("propertychange.autosize", function () {
                "value" === event.propertyName && r()
            }) : p.on("input.autosize", r), i.resizeDelay !== !1 && e(window).on("resize.autosize", l), p.on("autosize.resize", r), p.on("autosize.resizeIncludeStyle", function () {
                t = null, r()
            }), p.on("autosize.destroy", function () {
                t = null, clearTimeout(h), e(window).off("resize", l), p.off("autosize").off(".autosize").css(z).removeData("autosize")
            }), r())
        })) : this
    }
})(window.jQuery || window.$);

var __slice = [].slice;
(function (e, t) {
    var n;
    n = function () {
        function t(t, n) {
            var r, i, s, o = this;
            this.options = e.extend({}, this.defaults, n);
            this.$el = t;
            s = this.defaults;
            for (r in s) {
                i = s[r];
                if (this.$el.data(r) != null) {
                    this.options[r] = this.$el.data(r)
                }
            }
            this.createStars();
            this.syncRating();
            this.$el.on("mouseover.starrr", "span", function (e) {
                return o.syncRating(o.$el.find("span").index(e.currentTarget) + 1)
            });
            this.$el.on("mouseout.starrr", function () {
                return o.syncRating()
            });
            this.$el.on("click.starrr", "span", function (e) {
                return o.setRating(o.$el.find("span").index(e.currentTarget) + 1)
            });
            this.$el.on("starrr:change", this.options.change)
        }

        t.prototype.defaults = {
            rating: void 0, numStars: 5, change: function (e, t) {
            }
        };
        t.prototype.createStars = function () {
            var e, t, n;
            n = [];
            for (e = 1, t = this.options.numStars; 1 <= t ? e <= t : e >= t; 1 <= t ? e++ : e--) {
                n.push(this.$el.append("<span class='glyphicon .glyphicon-star-empty'></span>"))
            }
            return n
        };
        t.prototype.setRating = function (e) {
            if (this.options.rating === e) {
                e = void 0
            }
            this.options.rating = e;
            this.syncRating();
            return this.$el.trigger("starrr:change", e)
        };
        t.prototype.syncRating = function (e) {
            var t, n, r, i;
            e || (e = this.options.rating);
            if (e) {
                for (t = n = 0, i = e - 1; 0 <= i ? n <= i : n >= i; t = 0 <= i ? ++n : --n) {
                    this.$el.find("span").eq(t).removeClass("glyphicon-star-empty").addClass("glyphicon-star")
                }
            }
            if (e && e < 5) {
                for (t = r = e; e <= 4 ? r <= 4 : r >= 4; t = e <= 4 ? ++r : --r) {
                    this.$el.find("span").eq(t).removeClass("glyphicon-star").addClass("glyphicon-star-empty")
                }
            }
            if (!e) {
                return this.$el.find("span").removeClass("glyphicon-star").addClass("glyphicon-star-empty")
            }
        };
        return t
    }();
    return e.fn.extend({
        starrr: function () {
            var t, r;
            r = arguments[0], t = 2 <= arguments.length ? __slice.call(arguments, 1) : [];
            return this.each(function () {
                var i;
                i = e(this).data("star-rating");
                if (!i) {
                    e(this).data("star-rating", i = new n(e(this), r))
                }
                if (typeof r === "string") {
                    return i[r].apply(i, t)
                }
            })
        }
    })
})(window.jQuery, window);
$(function () {
    return $(".starrr").starrr()
})

$(function () {

    $('#new-review').autosize({append: "\n"});

    var reviewBox = $('#post-review-box');
    var newReview = $('#new-review');
    var openReviewBtn = $('#open-review-box');
    var closeReviewBtn = $('#close-review-box');


    var ratingsField = $('#ratings-hidden');

    openReviewBtn.click(function (e) {
        reviewBox.slideDown(400, function () {
            $('#new-review').trigger('autosize.resize');
            newReview.focus();
        });
        openReviewBtn.fadeOut(100);
        closeReviewBtn.show();
    });

    closeReviewBtn.click(function (e) {
        e.preventDefault();
        reviewBox.slideUp(300, function () {
            newReview.focus();
            openReviewBtn.fadeIn(200);
        });
        closeReviewBtn.hide();

    });

    $('.starrr').on('starrr:change', function (e, value) {
        ratingsField.val(value);
    });

});

function isCommentValid(comment) {
    if (comment == null || comment.trim() == "") {
        alert("리뷰 내용을 입력해주세요");
        return false;
    }
    if (comment.length > 1000) {
        alert("리뷰 내용은 1000자를 넘을 수 없습니다");
        return false;
    }
    return true;
}

function isRatingValid(rating) {
    /*var ratings = document.getElementsByClassName('glyphicon-star');
    if(ratings.length <= 0) {
      alert("별점을 선택해주세요");
      return false;
    }*/
    if (rating <= 0) {
        alert("별점을 입력해주세요");
        return false;
    }
    return true;
}