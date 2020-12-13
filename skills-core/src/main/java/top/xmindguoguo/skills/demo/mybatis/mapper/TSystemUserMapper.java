package top.xmindguoguo.skills.demo.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.xmindguoguo.skills.demo.mybatis.model.TSystemUserModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName: TSystemUserModelMapper
 * @author: 于国帅
 * @Description:
 * @Date: 2020/11/28 16:12
 * @Version: v1.0
 */
@Repository
@Mapper
public interface TSystemUserMapper {
    /**
     * 单条插入
     *
     * @param model
     * @return
     */
    Integer insert(TSystemUserModel model);

    /**
     * 批量插入 (注意:批量超过1m 则用 SqlSession 方式进行提交或者配置数据库的限制大小设置)
     *
     * @param modelList
     * @return
     */
    boolean insertBatch(@Param("modelList") List<TSystemUserModel> modelList);

    /**
     * 根据id 更新实体
     *
     * @param model
     * @return
     */
    Integer updateById(TSystemUserModel model);


    /**
     * 根据id批量更新
     *
     * @param modelList
     * @return
     */
    Integer updateBatchById(@Param("modelList") List<TSystemUserModel> modelList);


    /**
     * 根据id删除多条
     *
     * @param id
     * @return
     */
    Integer deleteById(Serializable id);


    /**
     * 根据id 集合删除
     *
     * @param idCollection
     * @return
     */
    Integer deleteBatchIds(@Param("idCollection") Collection<? extends Serializable> idCollection);


    /**
     * 根据id 查询单个实体
     *
     * @param id
     * @return
     */
    TSystemUserModel selectById(Serializable id);


    /**
     * 根据id列表查询出来实体列表
     *
     * @param idCollection
     * @return
     */
    List<TSystemUserModel> selectBatchIds(@Param("idCollection") Collection<? extends Serializable> idCollection);

    /**
     * @return
     */
    List<TSystemUserModel> selectList();


}


