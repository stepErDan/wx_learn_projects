package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.eaglesoft.yjuias.common.page.export.ExportParam;
import cn.eaglesoft.yjuias.entity.sys.SysUser;
import cn.eaglesoft.yjuias.vo.common.Result;

/**
 * @author ${author}
 * @date ${.now}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
    * 分页查询
    */
    IPage<${entity}> getPage(ExportParam exportParam);

    /**
    * 获取详情
    */
    ${entity} getDetails(Long id);

    /**
    * 保存
    * @Param [${entity?uncap_first}, activeUser]
    * @return
    */
    Result save${entity}(${entity} ${entity?uncap_first}, SysUser activeUser);

    /**
    * 更新
    * @Param [${entity?uncap_first}, activeUser]
    * @return Result
    **/
    Result update${entity}(${entity} ${entity?uncap_first}, SysUser activeUser);

    /**
    * 删除
    * @Param [id,activeUser]
    **/
    Result delete${entity}(Long id, SysUser activeUser);

}
</#if>
