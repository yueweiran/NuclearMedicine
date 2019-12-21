package cn.med.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import cn.med.entity.CwDepartment;
import cn.med.entity.CwPosition;
import cn.med.entity.Medicine;

public interface CwPositionDao {

	
	
    /**
     * 查询单个
     * @param mId
     * @return
     */
    @Select({
    	 "select",
         "id, cw_departid, work_code,work_name ",
         "from cw_position",
         "where  work_code = #{code,jdbcType=VARCHAR}"
    })
    @Results({
    	  @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
          @Result(column="cw_departid", property="departid", jdbcType=JdbcType.INTEGER),
          @Result(column="work_code", property="work_code", jdbcType=JdbcType.VARCHAR),
          @Result(column="work_name", property="work_name", jdbcType=JdbcType.VARCHAR)
    })
    CwPosition selectPositionByCode(String code);
	
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Thu Nov 15 22:11:44 CST 2018
     */
    @Select({
        "select",
        "id, cw_departid, work_code,work_name ",
        "from cw_position",
        "where  cw_departid = #{departId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="cw_departid", property="departid", jdbcType=JdbcType.INTEGER),
        @Result(column="work_code", property="work_code", jdbcType=JdbcType.INTEGER),
        @Result(column="work_name", property="work_name", jdbcType=JdbcType.VARCHAR)
    })
    List<CwPosition> selectDepartById(String departId);
    
    



    
    
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Thu Nov 15 22:11:44 CST 2018
     */
    @Insert({
        "insert into cw_position (id, cw_departid, ",
        "work_code,work_name)  ",
        "values (#{id,jdbcType=INTEGER}, #{departid,jdbcType=INTEGER}, #{work_code,jdbcType=VARCHAR}, ",
        "#{work_name,jdbcType=VARCHAR})"
    })
    int insert(CwPosition position);
    
    
    
    
    @Update({
        "update cw_position",
        "set work_name = #{work_name,jdbcType=VARCHAR} ",
        "where work_code = #{work_code,jdbcType=VARCHAR}"
    })
    int updatePosition(CwPosition position);
    
    
    /**
     * 删除指定部门
     * @param datas
     * @return
     */
    @Delete({
        "delete from cw_position",
        "where work_code = #{code,jdbcType=VARCHAR}"
    })
    int deletePosition(String code);
    
    @Delete({
    	"<script>",
	        "delete from cw_position",
	        "where work_code in",
		        "<foreach collection='datas' item='work_code' open='(' separator=',' close=')'>",
		        "#{work_code}",
		        "</foreach>",
        "</script>"
    })
    int deletePositions(@Param("datas")int[] datas);
  
}