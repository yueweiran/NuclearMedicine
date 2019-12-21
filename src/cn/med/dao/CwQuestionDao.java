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
import cn.med.entity.CwQuestion;
import cn.med.entity.Medicine;

public interface CwQuestionDao {

	
	
	@Select({
        "select",
        "id, department_id,work_code, question ",
        "from cw_question",
        "where  department_id = #{0} and work_code = #{1}"
    })
    @Results({
    	 @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
         @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
         @Result(column="work_code", property="workCode", jdbcType=JdbcType.VARCHAR),
         @Result(column="question", property="question", jdbcType=JdbcType.VARCHAR)
    })
    List<CwQuestion> selQueByDepartId(String departmentId,String workCode);
    
   
    
    /**
     * 查询单个
     * @param mId
     * @return
     */
    @Select({
    	 "select",
         "id, department_id,work_code, question ",
         "from cw_question",
         "where  id = #{questionId,jdbcType=INTEGER}"
    })
    @Results({
    	 @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
         @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
         @Result(column="work_code", property="workCode", jdbcType=JdbcType.VARCHAR),
         @Result(column="question", property="question", jdbcType=JdbcType.VARCHAR)
    })
    CwQuestion selectQuestionById(String questionId);
  
    
    @Update({
        "update cw_question",
        "set question = #{question,jdbcType=VARCHAR} ",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateQuestion(CwQuestion question);



    
    @Insert({
        "insert into cw_question (id, department_id, ",
        "work_code,question)  ",
        "values (#{id,jdbcType=INTEGER}, #{departmentId,jdbcType=INTEGER}, #{workCode,jdbcType=VARCHAR}, ",
        "#{question,jdbcType=VARCHAR})"
    })
	int insert(CwQuestion cq);
    
    
}