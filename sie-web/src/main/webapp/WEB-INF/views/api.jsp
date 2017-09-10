<%--
  Created by IntelliJ IDEA.
  User: wangheng
  Date: 2017/8/29
  Time: 下午10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/statics/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="/statics/bootstrap/util-aes.js"></script>
    <script src="/statics/bootstrap/jquery-2.0.3.min.js"></script>
    <script src="/statics/bootstrap/dhcc_api.js"></script>
    <script src="/statics/bootstrap/bootstrap.min.js"></script>
    <script>
        var accessToken = "un23n4no2bu4bs34";
    </script>
    <title>网站接口</title>
</head>
<body>
<ul class="list-group fixed opacity-50 col-xs-1 col-lg-1 col-md-1 col-sm-1">
    <li class="list-group-item" style="width:120px">
        <span class="badge">4</span>
        <a href="#order">订单接口</a>
        <span class="badge">4</span>
        <a href="#student">学生接口</a>
        <span class="badge">4</span>
        <a href="#project">项目接口</a>
    </li>
</ul>

<div class="container" style="margin-top: 20px">
    <div id="student">
        <div class="page-header">
            <h1>
                <small>学生</small>
            </h1>
        </div>

        <!-- 商品分类 -->
        <div class="panel panel-default">
            <div class="panel-heading">1.注册(/api/register.json)</div>
            <div class="panel-body">
                <form id="test_register_form" class="form-inline" onsubmit="return false"
                      url="/api/register.json">
                    <div class="form-group">
                        <label>email</label>
                        <input type="text" class="form-control" name="email" placeholder="email"  >
                    </div>
                    <div class="form-group">
                        <label>weixin</label>
                        <input type="text" class="form-control" name="weixin" placeholder="weixin"  >
                    </div>
                    <div class="form-group">
                        <label>password</label>
                        <input type="text" class="form-control" name="password" placeholder="password" required>
                    </div>

                    <button class="btn btn-default"
                            onclick="dhcc.Unit.submit('#test_register_form', '#register_result',  accessToken)">
                        提交
                    </button>
                </form>
                <h5>输出结果</h5>
                <pre id="register_result"></pre>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">2.登录(/api/login.json)</div>
            <div class="panel-body">
                <form id="test_login_form" class="form-inline" onsubmit="return false"
                      url="/api/login.json">
                    <div class="form-group">
                        <label>userName</label>
                        <input type="text" class="form-control" name="userName" placeholder="userName"  required>
                    </div>

                    <div class="form-group">
                        <label>password</label>
                        <input type="text" class="form-control" name="password" placeholder="password" required>
                    </div>

                    <button class="btn btn-default"
                            onclick="dhcc.Unit.submit('#test_login_form', '#login_result',  accessToken)">
                        提交
                    </button>
                </form>
                <h5>输出结果</h5>
                <pre id="login_result"></pre>
            </div>
        </div>

        <!-- 获取学校 -->
        <div class="panel panel-default">
            <div class="panel-heading">3.获取学校(/api/getSchool.json)</div>
            <div class="panel-body">
                <form id="test_getSchool_form" class="form-inline" onsubmit="return false"
                      url="/api/getSchool.json">
                    <button class="btn btn-default"
                            onclick="dhcc.Unit.submit('#test_getSchool_form', '#getSchool_result',  accessToken)">
                        提交
                    </button>
                </form>
                <h5>输出结果</h5>
                <pre id="getSchool_result"></pre>
            </div>
        </div>




        <!-- 商品详情 -->

    </div>

    <div id="project">
        <div class="page-header">
            <h1>
                <small>项目</small>
            </h1>
        </div>

        <!-- 获取项目 -->
        <div class="panel panel-default">
            <div class="panel-heading">1.获取项目(/api/getProjects.json)</div>
            <div class="panel-body">
                <form id="test_getProjects_form" class="form-inline" onsubmit="return false"
                      url="/api/getProjects.json">
                    <div class="form-group">
                        <label>systemType(1:SIE,2:TRU)</label>
                        <input type="number" class="form-control" name="systemType"  required>
                    </div>
                    <button class="btn btn-default"
                            onclick="dhcc.Unit.submit('#test_getProjects_form', '#getProjects_result',  accessToken)">
                        提交
                    </button>
                </form>
                <h5>输出结果</h5>
                <pre id="getProjects_result"></pre>
            </div>
        </div>

        <!-- 获取课程 -->
        <div class="panel panel-default">
            <div class="panel-heading">2.获取课程(/api/getCourses.json)</div>
            <div class="panel-body">
                <form id="test_getCourses_form" class="form-inline" onsubmit="return false"
                      url="/api/getCourses.json">
                    <div class="form-group">
                        <label>systemType(1:SIE,2:TRU)</label>
                        <input type="number" class="form-control" name="systemType"  required>
                    </div>

                    <div class="form-group">
                        <label>projectId</label>
                        <input type="number" class="form-control" name="projectId"  required>
                    </div>
                    <button class="btn btn-default"
                            onclick="dhcc.Unit.submit('#test_getCourses_form', '#getCourses_result',  accessToken)">
                        提交
                    </button>
                </form>
                <h5>输出结果</h5>
                <pre id="getCourses_result"></pre>
            </div>
        </div>


        <!-- 获取价格 -->
        <div class="panel panel-default">
            <div class="panel-heading">3.获取价格(/api/getPrices.json)</div>
            <div class="panel-body">
                <form id="test_getPrices_form" class="form-inline" onsubmit="return false"
                      url="/api/getPrices.json">
                    <div class="form-group">
                        <label>systemType(1:SIE,2:TRU)</label>
                        <input type="number" class="form-control" name="systemType"  required>
                    </div>

                    <button class="btn btn-default"
                            onclick="dhcc.Unit.submit('#test_getPrices_form', '#getPrices_result',  accessToken)">
                        提交
                    </button>
                </form>
                <h5>输出结果</h5>
                <pre id="getPrices_result"></pre>
            </div>
        </div>


    </div>



    <div id="order">
        <div class="page-header">
            <h1>
                <small>订单</small>
            </h1>
        </div>

        <!-- 获取用户订单 -->
        <div class="panel panel-default">
            <div class="panel-heading">1.获取用户订单(/api/getOrderList.json)</div>
            <div class="panel-body">
                <form id="test_getOrderList_form" class="form-inline" onsubmit="return false"
                      url="/api/getOrderList.json">
                    <div class="form-group">
                        <label>systemType(1:SIE,2:TRU)</label>
                        <input type="number" class="form-control" name="systemType"  required>
                    </div>
                    <div class="form-group">
                        <label>studentId</label>
                        <input type="number" class="form-control" name="studentId"  required>
                    </div>
                    <button class="btn btn-default"
                            onclick="dhcc.Unit.submit('#test_getOrderList_form', '#getOrderList_result',  accessToken)">
                        提交
                    </button>
                </form>
                <h5>输出结果</h5>
                <pre id="getOrderList_result"></pre>
            </div>
        </div>

        <!-- 获取用户订单 -->
        <div class="panel panel-default">
            <div class="panel-heading">2.获取用户订单(/api/getOrderDetail.json)</div>
            <div class="panel-body">
                <form id="test_getOrderDetail_form" class="form-inline" onsubmit="return false"
                      url="/api/getOrderDetail.json">
                    <div class="form-group">
                        <label>orderId</label>
                        <input type="number" class="form-control" name="orderId"  required>
                    </div>
                    <button class="btn btn-default"
                            onclick="dhcc.Unit.submit('#test_getOrderDetail_form', '#getOrderDetail_result',  accessToken)">
                        提交
                    </button>
                </form>
                <h5>输出结果</h5>
                <pre id="getOrderDetail_result"></pre>
            </div>
        </div>


        <!-- 提交订单 -->
        <div class="panel panel-default">
            <div class="panel-heading">2.提交订单(/api/createOrder.json)</div>
            <div class="panel-body">
                <form id="test_createOrder_form" class="form-inline" onsubmit="return false"
                      url="/api/getOrderDetail.json">
                    <div class="form-group">
                        <label>orderId</label>
                        <input type="number" class="form-control" name="orderId"  required>
                    </div>
                    <button class="btn btn-default"
                            onclick="dhcc.Unit.submit('#test_createOrder_form', '#createOrder_result',  accessToken)">
                        提交
                    </button>
                </form>
                <h5>输出结果</h5>
                <pre id="createOrder_result"></pre>
            </div>
        </div>


    </div>
</div>
</body>

</html>
