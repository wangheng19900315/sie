<?php
/**
 *  index.php PHPCMS 入口
 *
 * @copyright			(C) 2005-2010 PHPCMS
 * @license				http://www.phpcms.cn/license/
 * @lastmodify			2010-6-1
 */
 //PHPCMS根目录

define('PHPCMS_PATH', dirname(dirname(dirname(__FILE__))).DIRECTORY_SEPARATOR);
$_GET['m']='user';
$_GET['c']='index';
$_GET['a']='forget';
$_GET['lan']='en';
include PHPCMS_PATH.'/phpcms/base.php';

pc_base::creat_app();

?>