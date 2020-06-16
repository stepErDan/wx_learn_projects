package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
<#if swagger2>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
</#if>
import cn.eaglesoft.yjuias.common.annotation.Log;
import cn.eaglesoft.yjuias.common.annotation.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import cn.eaglesoft.yjuias.entity.sys.SysUser;

import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.eaglesoft.yjuias.common.page.export.ExportParam;
import cn.eaglesoft.yjuias.vo.common.CommonId;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import cn.eaglesoft.yjuias.vo.common.Result;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author ${author}
 * @date ${.now}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#if swagger2>
@Api("${table.comment!}接口")
</#if>
@RequestMapping("/rest/<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>
    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    @ApiOperation(value = "查询${table.comment!}列表", notes = "查询${table.comment!}列表")
    @PostMapping("getPage")
    @ResponseBody
    public Result<IPage<${entity}>> getPage(@RequestBody ExportParam exportParam) {
        IPage<${entity}> userList = ${table.serviceName?uncap_first}.getPage(exportParam);
        return getDataTable(userList);
    }

    @ApiOperation(value = "获取${table.comment!}详情", notes = "获取${table.comment!}详情")
    @PostMapping("getDetail")
    @ResponseBody
    public Result getDetails(@RequestBody CommonId commonId) {
        return Result.success( ${table.serviceName?uncap_first}.getDetails(commonId.getId()));
    }

    @ApiOperation(value = "新增${table.comment!}", notes = "新增${table.comment!}")
    @PostMapping("save")
    @ResponseBody
    @Log(value = "${table.comment!}", operationName = "新增${table.comment!}", operationType = 1)
    public Result save${entity}(@RequestBody ${entity} ${entity?uncap_first}, @ApiIgnore @LoginUser SysUser currentUser) {
         return ${table.serviceName?uncap_first}.save${entity}(${entity?uncap_first}, currentUser);
    }

    @ApiOperation(value = "修改${table.comment!}", notes = "修改${table.comment!}")
    @PostMapping("update")
    @ResponseBody
    @Log(value = "${table.comment!}", operationName = "修改${table.comment!}", operationType = 2)
    public Result update${entity}(@RequestBody ${entity} ${entity?uncap_first}, @ApiIgnore @LoginUser SysUser currentUser) {
        return ${table.serviceName?uncap_first}.update${entity}(${entity?uncap_first}, currentUser);
    }

    @ApiOperation(value = "删除${table.comment!}", notes = "删除${table.comment!}")
    @PostMapping("delete")
    @ResponseBody
    @Log(value = "${table.comment!}", operationName = "删除${table.comment!}", operationType = 3)
    public Result delete${entity}(@RequestBody CommonId commonId, @ApiIgnore @LoginUser SysUser currentUser) {
        return ${table.serviceName?uncap_first}.delete${entity}(commonId.getId(), currentUser);
    }
}
</#if>
