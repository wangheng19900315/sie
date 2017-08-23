
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main-menu" role="navigation" style="background-color: #23272d;height: 100%;min-height: 1060px">
    <div id="main-menu-inner">
        <%--<div class="menu-content top" id="menu-content-demo">--%>
            <%--<!-- Menu custom content demo--%>
                 <%--CSS:        styles/pixel-admin-less/demo.less or styles/pixel-admin-scss/_demo.scss--%>
                 <%--Javascript: html//statics/assets/demo/demo.js--%>
             <%---->--%>
            <%--<div>--%>
                <%--<div class="text-bg"><span class="text-slim">Welcome,</span> <span class="text-semibold">John</span></div>--%>

                <%--<img src="/statics/assets/demo/avatars/1.jpg" alt="" class="">--%>
                <%--<div class="btn-group">--%>
                    <%--<a href="#" class="btn btn-xs btn-primary btn-outline dark"><i class="fa fa-envelope"></i></a>--%>
                    <%--<a href="#" class="btn btn-xs btn-primary btn-outline dark"><i class="fa fa-user"></i></a>--%>
                    <%--<a href="#" class="btn btn-xs btn-primary btn-outline dark"><i class="fa fa-cog"></i></a>--%>
                    <%--<a href="#" class="btn btn-xs btn-danger btn-outline dark"><i class="fa fa-power-off"></i></a>--%>
                <%--</div>--%>
                <%--<a href="#" class="close">&times;</a>--%>
            <%--</div>--%>
        <%--</div>--%>
        <ul class="navigation">
            <li>
                <a href="index.html"><i class="menu-icon fa fa-dashboard"></i><span class="mm-text">Dashboard</span></a>
            </li>

            <li class="mm-dropdown">
                <a href="/order/list.html"><i class="menu-icon fa fa-th"></i><span class="mm-text">订单管理</span></a>
                <ul>
                    <li>
                        <a tabindex="-1" href="/order/list.html"><span class="mm-text">订单管理</span></a>
                    </li>

                </ul>
            </li>
            <li class="mm-dropdown">
                <a href="#"><i class="menu-icon fa fa-th"></i><span class="mm-text">学生管理</span></a>
                <ul>

                    <li>  <a tabindex="-1" href="/student/list.html"><span class="mm-text">学生管理</span></a> </li>
                    <li>  <a tabindex="-1" href="/grade/list.html"><span class="mm-text">成绩单管理</span></a> </li>
                    <li>  <a tabindex="-1" href="/send/list.html"><span class="mm-text">寄送管理</span></a> </li>

                </ul>
            </li>

            <li class="mm-dropdown">
                <a href="#"><i class="menu-icon fa fa-th"></i><span class="mm-text">项目管理</span></a>
                <ul>

                    <li>  <a tabindex="-1" href="/project/list.html"><span class="mm-text">项目管理</span></a> </li>
                    <li>  <a tabindex="-1" href="/course/list.html"><span class="mm-text">课程管理</span></a> </li>
                    <li>  <a tabindex="-1" href="/dormitory/list.html"><span class="mm-text">宿舍管理</span></a> </li>


                </ul>
            </li>
            <li class="mm-dropdown">
                <a href="#"><i class="menu-icon fa fa-th"></i><span class="mm-text">优惠管理</span></a>
                <ul>
                    <li>  <a tabindex="-1" href="/cr/list.html"><span class="mm-text">CR管理</span></a> </li>
                    <li>  <a tabindex="-1" href="/coupon/list.html"><span class="mm-text">优惠码管理</span></a> </li>


                </ul>
            </li>
            <li class="mm-dropdown">
                <a href="#"><i class="menu-icon fa fa-table"></i><span class="mm-text">报表管理</span></a>
                <ul>

                    <li>  <a tabindex="-1" href="layouts-grid.html"><span class="mm-text">订单报表</span></a> </li>


                </ul>
            </li>
            <li class="mm-dropdown">
                <a href="#"><i class="menu-icon fa fa-th"></i><span class="mm-text">数据字典</span></a>
                <ul>

                    <li>  <a tabindex="-1" href="/school/list.html"><span class="mm-text">学校管理</span></a> </li>
                    <%--<li>  <a tabindex="-1" href="layouts-grid.html"><span class="mm-text">学校管理</span></a> </li>--%>


                </ul>
            </li>
            <li class="mm-dropdown">
                <a href="#"><i class="menu-icon fa fa-th"></i><span class="mm-text">权限管理</span></a>
                <ul>
                    <li>
                        <a tabindex="-1" href="/user/list.html"><span class="mm-text">用户管理</span></a>
                    </li>
                    <li>
                        <a tabindex="-1" href="/role/list.html"><i class="mm-text"></i>角色管理</a>
                    </li>
                    <%--<li>--%>
                        <%--<a tabindex="-1" href="/menu/list.html"><i class="mm-text"></i>菜单管理</a>--%>
                    <%--</li>--%>
                </ul>
            </li>

        </ul> <!-- / .navigation -->

    </div> <!-- / #main-menu-inner -->
</div>
