
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main-navbar" class="navbar navbar-inverse" role="navigation">
    <!-- Main menu toggle -->
    <button type="button" id="main-menu-toggle"><i class="navbar-icon fa fa-bars icon"></i><span class="hide-menu-text">HIDE MENU</span></button>

    <div class="navbar-inner">
        <!-- Main navbar header -->
        <div class="navbar-header">

            <!-- Logo -->
            <a href="index.html" class="navbar-brand">
                <div><img alt="SIE网站管理系统" src="${rootPath}/statics/assets/images/pixel-admin/main-navbar-logo.png"></div>
                SIE网站管理系统
            </a>

            <!-- Main navbar toggle -->
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-navbar-collapse"><i class="navbar-icon fa fa-bars"></i></button>

        </div> <!-- / .navbar-header -->

        <div id="main-navbar-collapse" class="collapse navbar-collapse main-navbar-collapse">
            <div>
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/order/list.html">Home</a>
                    </li>

                </ul> <!-- / .navbar-nav -->

                <div class="right clearfix">
                    <ul class="nav navbar-nav pull-right right-navbar-nav">

                        <!-- 3. $NAVBAR_ICON_BUTTONS =======================================================================

                                                    Navbar Icon Buttons

                                                    NOTE: .nav-icon-btn triggers a dropdown menu on desktop screens only. On small screens .nav-icon-btn acts like a hyperlink.

                                                    Classes:
                                                    * 'nav-icon-btn-info'
                                                    * 'nav-icon-btn-success'
                                                    * 'nav-icon-btn-warning'
                                                    * 'nav-icon-btn-danger'
                        -->
                        <%--<li class="nav-icon-btn nav-icon-btn-danger dropdown">--%>
                            <%--<a href="#notifications" class="dropdown-toggle" data-toggle="dropdown">--%>
                                <%--<span class="label">5</span>--%>
                                <%--<i class="nav-icon fa fa-bullhorn"></i>--%>
                                <%--<span class="small-screen-text">Notifications</span>--%>
                            <%--</a>--%>

                            <%--<!-- NOTIFICATIONS -->--%>

                            <%--<!-- Javascript -->--%>
                            <%--<script>--%>
                                <%--init.push(function () {--%>
                                    <%--$('#main-navbar-notifications').slimScroll({ height: 100 });--%>
                                <%--});--%>
                            <%--</script>--%>
                            <%--<!-- / Javascript -->--%>

                            <%--<div class="dropdown-menu widget-notifications no-padding" style="width: 300px">--%>
                                <%--<div class="notifications-list" id="main-navbar-notifications">--%>

                                    <%--<div class="notification">--%>
                                        <%--<div class="notification-title text-danger">SYSTEM</div>--%>
                                        <%--<div class="notification-description"><strong>Error 500</strong>: Syntax error in index.php at line <strong>461</strong>.</div>--%>
                                        <%--<div class="notification-ago">12h ago</div>--%>
                                        <%--<div class="notification-icon fa fa-hdd-o bg-danger"></div>--%>
                                    <%--</div> <!-- / .notification -->--%>




                                <%--</div> <!-- / .notifications-list -->--%>
                                <%--<a href="#" class="notifications-link">MORE NOTIFICATIONS</a>--%>
                            <%--</div> <!-- / .dropdown-menu -->--%>
                        <%--</li>--%>




                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle user-menu" data-toggle="dropdown">
                                <img src="${rootPath}/statics/assets/demo/avatars/1.jpg" alt="">
                                <span>${USER_NAME}</span>
                            </a>
                            <ul class="dropdown-menu">
                                <%--<li><a href="#"><span class="label label-warning pull-right">New</span>Profile</a></li>--%>
                                <%--<li><a href="#"><span class="badge badge-primary pull-right">New</span>Account</a></li>--%>
                                <%--<li><a href="#"><i class="dropdown-icon fa fa-cog"></i>&nbsp;&nbsp;Settings</a></li>--%>
                                <%--<li class="divider"></li>--%>
                                <li><a href="/user/logout.html"><i class="dropdown-icon fa fa-power-off"></i>&nbsp;&nbsp;退出系统</a></li>
                                <li><a data-toggle="modal" data-target="#modify-password"><i class="dropdown-icon fa fa-user"></i>&nbsp;&nbsp;修改密码</a></li>
                                    <%--<button type="button" class="btn btn-success disabled"  id="refundBtn" data-toggle="modal" data-target="#refund">退课</button> &nbsp;&nbsp;--%>
                            </ul>

                        </li>
                    </ul> <!-- / .navbar-nav -->
                </div> <!-- / .right -->
            </div>
        </div> <!-- / #main-navbar-collapse -->
    </div> <!-- / .navbar-inner -->
</div> <!-- / #main-navbar -->
<!-- /2. $END_MAIN_NAVIGATION -->
<%--修改密码弹出框--%>
<div id="modify-password" class="modal fade" tabindex="-1" role="dialog" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" novalidate="novalidate" id="password-form">
                    <div class="form-group ">
                        <label  class="col-sm-2 control-label">原密码</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="oldPassworld"  name="oldPassworld" placeholder="oldPassworld" required data-msg-required="请输入原密码">
                        </div>
                    </div>
                    <div class="form-group ">
                        <label  class="col-sm-2 control-label">新密码</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="newPassworld"  name="newPassworld" placeholder="newPassworld" required data-msg-required="请输入新密码">
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="modifyPasswordSubmitBtn" class="btn btn-primary" data-dismiss="modal">提交</button>
            </div>
        </div>
    </div>
</div>
