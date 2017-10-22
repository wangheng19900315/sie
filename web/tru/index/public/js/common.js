$(function() {
	var events = {
		
		floatAd: function() {
		    $("a.to-a-link").click(function() {
		    	$("html, body").animate({
		    		scrollTop: $($(this).attr("href")).offset().top + "px"
		    	}, {
		    		duration: 500,
		    		easing: "swing"
		    	});
		    	return false;
		    });
			$(window).scroll(function() {
				var s_top = parseInt($(window).scrollTop());
				if (s_top > 100) {
					$(".float-ad").fadeIn("normal");
					$(".float-ad").css("offsetTop", s_top + "px");
				} else {
					$(".float-ad").stop(true, true).fadeOut("normal");
				}
			});
		    $(".ad-box").hover(function() {
		    	$(this).find(".ad-cont").fadeIn(200);
		    },function(){
		    	$(this).find(".ad-cont").fadeOut(200);
		    });
		},
		
		swiper: function() {
		    var swiper = new Swiper('.banner-index', {
		        pagination: '.swiper-pagination',
		        paginationClickable: true,
		        nextButton: '.swiper-button-next',
		        prevButton: '.swiper-button-prev'
		    });
		},
		
		inputDate: function() {
			var inputDate = $('.date').val();
			if (inputDate != null) {
				$('.date-days').datetimepicker({
					format: 'yyyy-mm-dd',
					weekStart: 1,
					autoclose: true,
					todayHighlight: true,
					startView: 2,
					minView: 2,
					pickerPosition: "bottom-left"
				});
				$('.date-months').datetimepicker({
					format: 'yyyy-mm',
					weekStart: 1,
					autoclose: true,
					startView: 3,
					minView: 3,
					pickerPosition: "bottom-left"
				});
			}
		},
		//头像上传
		uploadCard: function() {
			function getObjectURL(file) {
				var url = null;
				if (window.createObjectURL != undefined) {
					url = window.createObjectURL(file);
				} else if (window.URL != undefined) {
					url = window.URL.createObjectURL(file);
				} else if (window.webkitURL != undefined) {
					url = window.webkitURL.createObjectURL(file);
				}
				return url;
			}
			$(".file-input").change(function() {
				var aBox = $(this).parent();
				var imgBox = $(this).next();
				var imgNum = imgBox.find("img");
				aBox.css("min-height","auto");
				imgBox.next().remove();
				var files = this.files;
				for (var i = 0; i < files.length; i++) {
					var objUrl = getObjectURL(files[i]);
					var objName = files[i].name;
					if (objUrl) {
						imgBox.html("<img src='" + objUrl + "' class='img-responsive' alt='" + objName + "' />");
					}
				}
			});
		},
		
		others: function() {
		    $(".nav .dropdown").hover(function() {
		    	$(this).find(".dropdown-menu").show().end().siblings().find(".dropdown-menu").hide();
		    }, function() {
		    	$(this).removeClass("open").find(".dropdown-menu").hide()
		    });
		    $(".dropdown-select li").click(function() {
		    	if ($(this).attr("class") !="disabled") {
			    	var txt = $(this).text();
			    	$(this).parent().prev().val(txt);
			    	var cl = $(this).attr("aria-id");
		    		$("#" + cl).show().siblings().hide();
		    	}
		    });
			$(function(){
				//$('#president').chosen({allow_single_deselect: true});
				//var chosen = $(".chosen-single");
				//var setinput = $(".chosen-search").find("input");
				//chosen.addClass('form-control');
				//setinput.addClass('form-control');
			});
			$(function(){
				var inputTxt = $(".report-address input.form-control");
			    inputTxt.focus(function() {
			    	$(this).attr("placeholder","");
			    	$(this).prev().css("opacity","1");
			    });
			    inputTxt.blur(function() {
			    	if($(this).val() == ""){
			    		$(this).attr("placeholder",$(this).prev().text());
			    		$(this).prev().css("opacity","0");
			    	}
			    });
			});
			//modal
			$(function() {
				$('[data-toggle="modal"]').click(function () {
					var target = $(this).attr("data-target");
					var modal = $(target);
					if(modal.length > 0){
						$("body").addClass("modal-scroll").css({
							"overflow":"hidden",
							"padding-right":0
						});
					}
					setTimeout('if ($(".modal-tips.in").length > 0) {$(".modal-tips").modal("hide");$("body").removeClass("modal-scroll").css({"overflow":"auto"});}',2000);
				});
				$('button[data-dismiss="modal"]').click(function () {
					$("body").removeClass("modal-scroll").css({
						"overflow":"auto"
					});
				});
			});
		}
	}

	events.floatAd();
	events.swiper();
	events.inputDate();
	events.uploadCard();
	events.others();
});