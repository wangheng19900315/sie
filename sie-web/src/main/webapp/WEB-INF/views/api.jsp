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
    <title>商品接口</title>
</head>
<body>
<ul class="list-group fixed opacity-50 col-xs-1 col-lg-1 col-md-1 col-sm-1">
    <li class="list-group-item">
        <span class="badge">4</span>
        <a href="#goods">订单接口</a>
        <a href="#student">学生接口</a>
        <a href="#goods">项目接口</a>
        <a href="#goods">学校接口</a>
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
                        <label>userName</label>
                        <input type="text" class="form-control" name="userName" placeholder="userName" required>
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




        <!-- 商品详情 -->

    </div>

</div>
</body>

</html>
