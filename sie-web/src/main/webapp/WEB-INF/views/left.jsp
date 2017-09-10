<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main-menu" role="navigation" style="background-color: #23272d;height: 100%;min-height: 1060px">
    <div id="main-menu-inner">
        <ul class="navigation">
            <li>
                <a href="/index.html"><i class="menu-icon fa fa-dashboard"></i><span class="mm-text">Dashboard</span></a>
            </li>


            <%--<li class="mm-dropdown">--%>
                <%--<a href="#"><i class="menu-icon fa fa-th"></i><span class="mm-text" >订单管理</span></a>--%>
                <%--<ul>--%>
                    <%--<li>--%>
                        <%--<a tabindex="-1" href="/order/list.html"><span class="mm-text">订单管理</span></a>--%>
                    <%--</li>--%>

                <%--</ul>--%>
            <%--</li>--%>
            <%--<li class="mm-dropdown">--%>
                <%--<a href="#"><i class="menu-icon fa fa-th"></i><span class="mm-text">学生管理</span></a>--%>
                <%--<ul>--%>

                    <%--<li>  <a tabindex="-1" href="/student/list.html"><span class="mm-text">学生管理</span></a> </li>--%>
                    <%--<li>  <a tabindex="-1" href="/grade/list.html"><span class="mm-text">成绩单管理</span></a> </li>--%>
                    <%--<li>  <a tabindex="-1" href="/send/list.html"><span class="mm-text">寄送管理</span></a> </li>--%>

                <%--</ul>--%>
            <%--</li>--%>

            <%--<li class="mm-dropdown">--%>
                <%--<a href="#"><i class="menu-icon fa fa-th"></i><span class="mm-text">项目管理</span></a>--%>
                <%--<ul>--%>

                    <%--<li>  <a tabindex="-1" href="/project/list.html"><span class="mm-text">项目管理</span></a> </li>--%>
                    <%--<li>  <a tabindex="-1" href="/course/list.html"><span class="mm-text">课程管理</span></a> </li>--%>
                    <%--<li>  <a tabindex="-1" href="/dormitory/list.html"><span class="mm-text">宿舍管理</span></a> </li>--%>
                    <%--<li>  <a tabindex="-1" href="/packagePrice/list.html"><span class="mm-text">组合价格</span></a> </li>--%>

                <%--</ul>--%>
            <%--</li>--%>
            <%--<li class="mm-dropdown">--%>
                <%--<a href="#"><i class="menu-icon fa fa-th"></i><span class="mm-text">优惠管理</span></a>--%>
                <%--<ul>--%>
                    <%--<li>  <a tabindex="-1" href="/cr/list.html"><span class="mm-text">CR管理</span></a> </li>--%>
                    <%--<li>  <a tabindex="-1" href="/coupon/list.html"><span class="mm-text">优惠码管理</span></a> </li>--%>


                <%--</ul>--%>
            <%--</li>--%>
            <%--<li class="mm-dropdown">--%>
                <%--<a href="#"><i class="menu-icon fa fa-table"></i><span class="mm-text">报表管理</span></a>--%>
                <%--<ul>--%>

                    <%--<li>  <a tabindex="-1" href="/layouts-grid.html"><span class="mm-text">订单报表</span></a> </li>--%>


                <%--</ul>--%>
            <%--</li>--%>
            <%--<li class="mm-dropdown">--%>
                <%--<a href="#"><i class="menu-icon fa fa-th"></i><span class="mm-text">数据字典</span></a>--%>
                <%--<ul>--%>

                    <%--<li>  <a tabindex="-1" href="/school/list.html"><span class="mm-text">学校管理</span></a> </li>--%>
                    <%--&lt;%&ndash;<li>  <a tabindex="-1" href="layouts-grid.html"><span class="mm-text">学校管理</span></a> </li>&ndash;%&gt;--%>


                <%--</ul>--%>
            <%--</li>--%>
            <%--<li class="mm-dropdown">--%>
                <%--<a href="#"><i class="menu-icon fa fa-th"></i><span class="mm-text">权限管理</span></a>--%>
                <%--<ul>--%>
                    <%--<li>--%>
                        <%--<a tabindex="-1" href="/user/list.html"><span class="mm-text">用户管理</span></a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a tabindex="-1" href="/role/list.html"><i class="mm-text"></i>角色管理</a>--%>
                    <%--</li>--%>
                    <%--&lt;%&ndash;<li>&ndash;%&gt;--%>

                        <%--&lt;%&ndash;<a tabindex="-1" href="/menu/list.html"><i class="mm-text"></i>菜单管理</a>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</li>&ndash;%&gt;--%>
                <%--</ul>--%>
            <%--</li>--%>

        </ul> <!-- / .navigation -->

    </div> <!-- / #main-menu-inner -->
</div>


<script type="text/javascript">

    var menuList = '${MENU_LIST}';


</script>
