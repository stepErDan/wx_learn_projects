package com.dingx.personal.web.sys;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.dingx.personal.web.base.BaseController;

/**
 * <p>
 * 单位基础表，存放单位基础信息，即：适用于所有项目的单位表（不适用本项目） 前端控制器
 * </p>
 *
 * @author dingx
 * @since 2020-05-14
 */
@RestController
@RequestMapping("/sys/org-base")
public class SysOrgBaseController extends BaseController {

}
