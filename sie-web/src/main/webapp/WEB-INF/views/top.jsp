
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main-navbar" class="navbar navbar-inverse" role="navigation">
    <!-- Main menu toggle -->
    <button type="button" id="main-menu-toggle"><i class="navbar-icon fa fa-bars icon"></i><span class="hide-menu-text">HIDE MENU</span></button>

    <div class="navbar-inner">
        <!-- Main navbar header -->
        <div class="navbar-header">

            <!-- Logo -->
            <a href="index.html" class="navbar-brand">
                <div><img alt="Pixel Admin" src="/statics/assets/images/pixel-admin/main-navbar-logo.png"></div>
                PixelAdmin
            </a>

            <!-- Main navbar toggle -->
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-navbar-collapse"><i class="navbar-icon fa fa-bars"></i></button>

        </div> <!-- / .navbar-header -->

        <div id="main-navbar-collapse" class="collapse navbar-collapse main-navbar-collapse">
            <div>
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#">Home</a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown</a>
                        <ul class="dropdown-menu">
                            <li><a href="#">First item</a></li>
                            <li><a href="#">Second item</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Third item</a></li>
                        </ul>
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
                                <img src="/statics/assets/demo/avatars/1.jpg" alt="">
                                <span>John Doe</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#"><span class="label label-warning pull-right">New</span>Profile</a></li>
                                <li><a href="#"><span class="badge badge-primary pull-right">New</span>Account</a></li>
                                <li><a href="#"><i class="dropdown-icon fa fa-cog"></i>&nbsp;&nbsp;Settings</a></li>
                                <li class="divider"></li>
                                <li><a href="pages-signin.html"><i class="dropdown-icon fa fa-power-off"></i>&nbsp;&nbsp;Log Out</a></li>
                            </ul>
                        </li>
                    </ul> <!-- / .navbar-nav -->
                </div> <!-- / .right -->
            </div>
        </div> <!-- / #main-navbar-collapse -->
    </div> <!-- / .navbar-inner -->
</div> <!-- / #main-navbar -->
<!-- /2. $END_MAIN_NAVIGATION -->
