package ${package.ServiceImpl};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import cn.eaglesoft.yjuias.constant.UiasConstant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.eaglesoft.yjuias.common.annotation.Log;
import cn.eaglesoft.yjuias.common.utils.SortUtil;
import cn.eaglesoft.yjuias.common.page.export.ExportParam;
import cn.eaglesoft.yjuias.entity.sys.SysUser;
import cn.eaglesoft.yjuias.vo.common.Result;
import java.time.LocalDateTime;

/**
 * @author ${author}
 * @date ${.now}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    private static final Logger logger = LoggerFactory.getLogger(${table.serviceImplName}.class);

    @Override
    public IPage<${entity}> getPage(ExportParam exportParam) {
         try {
             Page<${entity}> page = new Page<>();
             //SortUtil.handlePageSort(exportParam, page, null);
             SortUtil.handlePageSort(exportParam, page, "cjsj", UiasConstant.ORDER_DESC, false);
             QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();
             return  baseMapper.selectPage(page, queryWrapper);
          } catch (Exception e) {
             e.printStackTrace();
             return null;
          }
    }

    @Override
    public ${entity} getDetails(Long id) {
       QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();
       queryWrapper.lambda().eq(${entity}::getId, id);
       return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Result save${entity}(${entity} ${entity?uncap_first}, SysUser activeUser) {
        ${entity?uncap_first}.setCjsj(LocalDateTime.now());
        ${entity?uncap_first}.setScbz(0);
        ${entity?uncap_first}.setCjz(activeUser.getId()+"");
        int i = baseMapper.insert(${entity?uncap_first});
        //if( i > 0){}
        return Result.actionResult(i > 0 ? true : false, Result.ACTION_ADD, ${entity?uncap_first}.getId());
    }


    @Override
    public Result update${entity}(${entity} ${entity?uncap_first}, SysUser activeUser){
        // 更新
        ${entity?uncap_first}.setGxsj(LocalDateTime.now());
        ${entity?uncap_first}.setGxz(activeUser.getId()+"");
        int i = baseMapper.updateById(${entity?uncap_first});
        return Result.actionResult(i > 0 ? true : false, Result.ACTION_UPDATE, ${entity?uncap_first}.getId());
    }


    @Override
    public  Result delete${entity}(Long id, SysUser activeUser){
        boolean result = removeById(id);
        return Result.actionResult(result, Result.ACTION_DELETE, id);
    }


}
</#if>
