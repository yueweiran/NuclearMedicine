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

import cn.med.entity.Medicine;

public interface MedicineDao {


	
    @Delete({
        "delete from medicine",
        "where m_id = #{mId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer mId);


    @Delete({
    	"<script>",
	        "delete from medicine",
	        "where m_id in",
		        "<foreach collection='datas' item='id' open='(' separator=',' close=')'>",
		        "#{id}",
		        "</foreach>",
        "</script>"
    })
    int deleteByAll(@Param("datas")int[] datas);
    

    @Insert({
        "insert into medicine (m_id,med_name, careat_time )",
        "values (#{mId,jdbcType=INTEGER}, #{medName,jdbcType=VARCHAR}, #{careatTime,jdbcType=TIMESTAMP})"
    })
    int insert(Medicine record);

    /**
     * 查询单个
     * @param mId
     * @return
     */
    @Select({
        "select",
        "m_id, med_name,careat_time",
        "from medicine",
        "where m_id = #{mId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="m_id", property="mId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="med_name", property="medName", jdbcType=JdbcType.VARCHAR),
        @Result(column="careat_time", property="careatTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Medicine selectByPrimaryKey(Integer mId);


    /**
     * 查询全部
     * @param mId
     * @return
     */
    @Select({
        "select",
        "m_id,med_name, careat_time ",
        "from medicine"
    })
    @Results({
        @Result(column="m_id", property="mId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="med_name", property="medName", jdbcType=JdbcType.VARCHAR),
        @Result(column="careat_time", property="careatTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Medicine>  selectAllMedicine();


    /**
     * 查询单个手机根据手机号
     * @param mId
     * @return
     */
    @Select({
        "select",
        "m_id,med_name, careat_time ",
        "from medicine ",
        "where  med_name like #{medName,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="m_id", property="mId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="med_name", property="medName", jdbcType=JdbcType.VARCHAR),
        @Result(column="careat_time", property="careatTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Medicine>  selectBymedName(String medName);
    
    
    
    @Update({
        "update medicine",
        "set med_name = #{medName,jdbcType=VARCHAR},",
          "careat_time = #{careatTime,jdbcType=TIMESTAMP} ",
        "where m_id = #{mId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Medicine record);
}