document.writeln("<!-- footer -->");
document.writeln("<div id=\'footer\'>");
document.writeln("	<div class=\'container text-light\'>");
document.writeln("		<ul class=\'row\'>");
document.writeln("			<li class=\'col-xs-3\'>");
document.writeln("				<div class=\'logo pull-right\'>");
document.writeln("					<img class=\'img-responsive\' src=\'../../public/img/logo.png\' alt=\'TRU国际暑期项目\' />");
document.writeln("				</div>");
document.writeln("			</li>");
document.writeln("			<li class=\'col-xs-3\'>");
document.writeln("				<div class=\'about\'>");
document.writeln("					<h2>关注我们</h2>");
document.writeln("					<p class=\'mt20\'>");
document.writeln("						<a href=\'#\'><i class=\'fa fa-facebook-official\'></i></a>");
document.writeln("						<a href=\'#\'><i class=\'fa fa-weibo\'></i></a>");
document.writeln("						<a href=\'#\'><i class=\'fa fa-twitter\'></i></a>");
document.writeln("						<a href=\'#\'><i class=\'fa fa-wechat\'></i></a>");
document.writeln("						<a href=\'#\'><i class=\'fa fa-qq\'></i></a>");
document.writeln("					</p>");
document.writeln("				</div>");
document.writeln("			</li>");
document.writeln("			<li class=\'col-xs-3\'>");
document.writeln("				<div class=\'contact\'>");
document.writeln("					<h2>联系我们</h2>");
document.writeln("					<p class=\'mt10\'>中国：010-69853211</p>");
document.writeln("					<p>加拿大：123456894</p>");
document.writeln("					<p>邮箱：support@TRU.org.cn</p>");
document.writeln("				</div>");
document.writeln("			</li>");
document.writeln("			<li class=\'col-xs-3\'>");
document.writeln("				<div class=\'wechat\'>");
document.writeln("					<div class=\'wechat-cont\'>");
document.writeln("						<img class=\'img-responsive\' src=\'../../public/img/wechat-consult.png\' alt=\'\' />");
document.writeln("						<p>微信咨询号</p>");
document.writeln("					</div>");
document.writeln("					<div class=\'wechat-cont\'>");
document.writeln("						<img class=\'img-responsive\' src=\'../../public/img/wechat-public.png\' alt=\'\' />");
document.writeln("						<p>微信公众号</p>");
document.writeln("					</div>");
document.writeln("				</div>");
document.writeln("			</li>");
document.writeln("		</ul>");
document.writeln("		<p class=\'copyright text-center\'>");
document.writeln("			<span>Copyright © 2009 Sinoway International Education Group Limited</span>");
document.writeln("			<span>京ICP证000007</span>");
document.writeln("		</p>");
document.writeln("	</div>");
document.writeln("</div>");
document.writeln("");
document.writeln("<!-- float-ad -->");
document.writeln("<div class=\'float-ad\'>");
document.writeln("	<div class=\'ad-box\'>");
document.writeln("		<a href=\'javascript:;\'><i class=\'fa fa-qrcode\'></i></a>");
document.writeln("		<div class=\'ad-cont none\'>");
document.writeln("			<em></em>");
document.writeln("			<img class=\'img-responsive\' src=\'../../public/img/wechat-consult.png\' alt=\'\' />");
document.writeln("			<p>微信咨询号</p>");
document.writeln("		</div>");
document.writeln("	</div>");
document.writeln("	<div class=\'ad-box\'>");
document.writeln("		<a href=\'#top-a\' class=\'to-a-link\'><i class=\'fa fa-angle-up\'></i></a>");
document.writeln("	</div>");
document.writeln("</div>");


document.writeln('<!-- modal-box -->'+
    '<div class="modal modal-userBox fade" id="modal_reseat_password" tabindex="-1" role="dialog" data-backdrop="static">'+
      '<div class="modal-dialog">'+
        '<div class="modal-content">'+
            ' <div class="modal-header">'+
                ' <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
                '<h4 class="modal-title text-center">身份认证</h4>'+
            ' </div>'+
            ' <div class="modal-body">'+
                ' <div class="form-list">'+
                    '<div class="form-group">'+
                        //' <label>用户名：</label>'+
    '   <i class="fa fa-envelope-o"></i>'+
                        '<input type="text" class="form-control" id="reset_email" placeholder="请输入您的邮箱">'+
    '   <label class="error"></label>'+
                    '  </div>'+
                    '   <div class="text-right mt30">'+
                        '   <input type="submit" class="btn btn-wd-sm btn-primary" id="resetPasswordBtn" value="确认" />'+
                        '   </div>'+
                 '   </div>'+
            '   </div>'+
         '   </div>'+
        '   </div>'+
    '   </div>'+

'   <div class="modal modal-userBox fade" id="modal-wechat" tabindex="-1" role="dialog" data-backdrop="static">'+
      ' <div class="modal-dialog">'+
        ' <div class="modal-content">'+
        '    <div class="modal-header">'+
            '    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
                '<h4 class="modal-title">注册/登录账户</h4>'+
            '    <p class="text-light">快速安全注册/登录</p>'+
            '   </div>'+
            '   <div class="modal-body">'+
            '    <div class="txt-p text-light text-center">'+
            '    <img class="img-responsive auto" src="../../public/img/wechat-consult.png" alt="" />'+
             '   <p>请打开微信，扫描二维码注册/登录</p>'+
            '</div>'+
            '<div class="txt-p">'+
            '   <p class="text-right mt30">'+
            '   <a class="pull-left" data-dismiss="modal" data-toggle="modal" data-target="#modal-register">其他注册</a>'+
                '   <a data-dismiss="modal" data-toggle="modal" data-target="#modal-login">邮箱登录 &gt;</a>'+
            '   </p>'+
            ' </div>'+
        ' </div>'+
    '   </div>'+
'    </div>'+
'   </div>'+

'  <div class="modal modal-userBox fade" id="modal-register" tabindex="-1" role="dialog" data-backdrop="static">'+
'    <div class="modal-dialog">'+
'       <div class="modal-content">'+
'           <div class="modal-header">'+
        '   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        ' <h4 class="modal-title">注册账户</h4>'+
        '   </div>'+
        '   <div class="modal-body">'+
        '   <div class="form-list">'+
        '   <div class="form-group">'+
        '   <i class="fa fa-envelope-o"></i>'+
        '   <input type="text" class="form-control" id="reggsiter_email" placeholder="请输入邮箱">'+
    '   <label class="error"> </label>'+
        '   </div>'+
        '   <div class="form-group">'+
        '   <i class="ico ico-lock"></i>'+
        '   <input type="password" class="form-control" id="reggsiter_password" placeholder="请输入密码">'+
    '   <label class="error"> </label>'+
        '   </div>'+
        '   <div class="form-group">'+
        '   <i class="ico ico-lock"></i>'+
        '   <input type="password" class="form-control" id="reggsiter_repeat_password"  placeholder="请输入密码">'+
        '   <label class="error"> </label>'+
        ' </div>'+
        ' <div class="mt30">'+
        '   <input type="submit" class="btn btn-block btn-primary" id="registerBtn" value="注册" />'+
        '   </div>'+
        '   <div class="txt-p">'+
        '   <div class="radio-box mt10">'+
        '   <label>'+
        '   <input type="checkbox" name="projectSelectshanghai" checked id="projectSelectshanghai9" value="9" />'+
        '   <i class="fa"></i>'+
        '   <span>同意《服务条款》</span>'+
        ' </label>'+
        ' </div>'+
        ' <p class="text-right mt10">'+
        '    <a data-dismiss="modal" id="register_login_btn" data-toggle="modal" data-target="#modal-login">已有账户？ 登录 &gt;</a>'+
        ' </p>'+
        ' </div>'+
    ' </div>'+
    ' </div>'+
    ' </div>'+
    ' </div>'+
' </div>'+

' <div class="modal modal-userBox fade" id="modal-login" tabindex="-1" role="dialog" data-backdrop="static">'+
'     <div class="modal-dialog">'+
    '   <div class="modal-content">'+
        '   <div class="modal-header">'+
            '   <button type="button" class="close" data-dismiss="modal" id="loginCloseBtn" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
                '<h4 class="modal-title">登录账户</h4>'+
                '   </div>'+
                '   <div class="modal-body">'+
                '   <div class="form-list">'+
                '   <div class="form-group">'+
                '    <i class="fa fa-envelope-o"></i>'+
                '    <input type="text" class="form-control" id="login_emiail" placeholder="请输入邮箱">'+
    '   <label class="error"></label>'+
                '    </div>'+
                '    <div class="form-group">'+
                '   <i class="ico ico-lock"></i>'+
                '   <input type="password" class="form-control" id="login_password" placeholder="请输入密码">'+
    '   <label class="error"></label>'+
                '   </div>'+
                '   <div class="mt30">'+
                '   <input type="submit" class="btn btn-block btn-primary" id="loginBtn" value="登录" />'+
                '   </div>'+
                '   <div class="txt-p">'+
                '   <p class="mt10">'+

                '    <a data-dismiss="modal" data-toggle="modal" data-target="#modal_reseat_password">忘记密码</a>'+
                '</p>'+
                '<p class="text-right mt10">'+
                '    <a data-dismiss="modal" data-toggle="modal" data-target="#modal-register">还未注册账户？ 注册 &gt;</a>'+
                '</p>'+
        '</div>'+
'</div>'+
'</div>'+
'</div>'+
'</div>'+
'</div>');

document.writeln('<!-- modal-box -->'+
'  <div class="modal modal-userBox fade" id="modal-psd" tabindex="-1" role="dialog" data-backdrop="static">'+
'   <div class="modal-dialog">'+
'    <div class="modal-content">'+
'    <div class="modal-header">'+
'   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
'<h4 class="modal-title">密码修改</h4>'+
'    </div>'+
'    <div class="modal-body">'+
'   <div class="form-list">'+
'   <div class="form-group">'+
'   <i class="ico ico-lock"></i>'+
'   <input type="password" id="modifyPassword" class="form-control" placeholder="原密码">'+
    '   <label class="error"></label>'+
'   </div>'+
'   <div class="form-group">'+
'   <i class="ico ico-lock"></i>'+
'   <input type="password" id="modifyNewPassword"  class="form-control" placeholder="新密码">'+
    '   <label class="error"></label>'+
'   </div>'+
'   <div class="form-group">'+
'   <i class="ico ico-lock"></i>'+
'   <input type="password" id="modifyNewPasswordRepeat" class="form-control" placeholder="确认密码">'+
    '   <label class="error"></label>'+
'   <label class="error"></label>'+
'</div>'+
'<div class="mt30">'+
'   <input type="submit" class="btn btn-block btn-primary" id="modifyPasswordBtn" value="确认" />'+
'   </div>'+
'   </div>'+
'   </div>'+
'   </div>'+
'   </div>'+
'   </div>');

document.writeln('<!-- modal-box -->'+
' <div class="modal modal-xs modal-tips fade" id="modal-success" tabindex="-1" role="dialog" data-backdrop="static">'+
' <div class="modal-dialog">'+
'  <div class="modal-content">'+
    '  <div class="modal-body">'+
'  <i class="ico ico-success"></i>'+
'   <h3 class="mt10">保存成功</h3>'+
'   </div>'+
'   </div>'+
'    </div>'+
'   </div>');

document.writeln('<script src="../../public/js/jquery.min.js"></script>'+
'<script src="../../public/js/bootstrap.min.js"></script>'+
'<script src="../../public/js/bootstrap-datetimepicker.js"></script>'+
'<script src="../../public/js/swiper.jquery.min.js"></script>'+
' <script src="../../public/js/chosen.jquery.min.js"></script>'+
' <script src="../../public/js/common.js"></script>'+
' <script src="../../js/util.js"></script>'+
' <script src="../../js/user-operate.js"></script>');