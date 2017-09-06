package com.sie.web.controller;

import com.alibaba.fastjson.JSON;
import com.sie.framework.entity.CouponEntity;
import com.sie.framework.entity.GradeSendEntity;
import com.sie.framework.entity.StudentEntity;
import com.sie.service.GradeSendService;
import com.sie.service.StudentService;
import com.sie.service.bean.GradeSendBean;
import com.sie.service.bean.PageInfo;
import com.sie.service.bean.ResultBean;
import com.sie.util.ImportExcel;
import com.sie.util.NumberUtil;
import com.sie.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/9.
 */
@Controller
@RequestMapping("/send")
public class GradeSendController {

    private static final Logger LOGGER = Logger.getLogger(GradeSendController.class);

    @Autowired
    private GradeSendService gradeSendService;

    @Autowired
    private StudentService studentService;

    @RequestMapping("/addOrUpdate")
    public String showUserInfo(){

        return "/send/showInfo";
    }


    @RequestMapping("/list.html")
    public String list(){
        return "/send/list";
    }

    @RequestMapping("/addOrUpdate.html")
    public String addOrupdate(Model model, Integer id){
        if(NumberUtil.isSignless(id)){
            GradeSendEntity gradeSendEntity = this.gradeSendService.get(id);
            model.addAttribute("entity", JSON.toJSON(gradeSendEntity));
        }
        //添加学生
        Map<Integer,String> students = studentService.getAllStudent();
        model.addAttribute("students", JSON.toJSON(students));
        return "/send/addOrUpdate";
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public PageInfo<GradeSendBean> listJons(Integer page, Integer rows ){

        PageInfo<GradeSendBean> pageInfo = null;
        try{
            pageInfo = this.gradeSendService.getGradeSendList(page,rows, null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return pageInfo;
    }



    @RequestMapping("/addOrupdate.json")
    @ResponseBody
    public ResultBean addOrupdate(GradeSendEntity gradeSendEntity){
        ResultBean resultBean = new ResultBean();


        try{
            Integer id = this.gradeSendService.saveOrUpdate(gradeSendEntity);
            if(NumberUtil.isSignless(id)){
                resultBean.setMessage("保存成功");
                resultBean.setSuccess(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }

    @RequestMapping(value = "/delete.json")
    @ResponseBody
    public ResultBean delete(Integer id){
        ResultBean resultBean = new ResultBean();

        //TODO 判断是否有订单引用有则不能进行删除

        try{
            this.gradeSendService.delete(id);
            if(NumberUtil.isSignless(id)){
                resultBean.setMessage("删除成功");
                resultBean.setSuccess(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultBean;
    }


    /**
     * 导入Excel数据

     */
    @RequestMapping(value = "import.json")
    @ResponseBody
    public ResultBean importFile(@RequestParam("excelFile") MultipartFile file, RedirectAttributes redirectAttributes) {
        ResultBean resultBean = new ResultBean();
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 0, 0);
            List<GradeSendBean> list = ei.getDataList(GradeSendBean.class);
            if(list == null || list.size() == 0){
                resultBean.setMessage("excel数据为空，请检查文件");
                return resultBean;
            }
            for(int i=0; i<list.size(); i++){
                String flag = this.gradeSendService.importBean(list.get(i));
                if(StringUtil.isNotBlank(flag)){
                    failureMsg.append("<p>第"+(i+2)+"行:"+flag+"</p>") ;
                    failureNum ++;
                }else{
                    successNum++;
                }
            }
            resultBean.setSuccess(true);
            resultBean.setMessage("导入完毕，成功导入"+successNum+"条,导入失败"+failureNum+"条;"+failureMsg.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBean;
    }

}