package com.kkwo.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kkwo.demo.vo.Reply;

@Mapper
public interface ReplyRepository {

	@Select("""
			<script>
				SELECT R.*, M.nickname AS extra__writer
				FROM reply AS R
				INNER JOIN `member` AS M
				ON R.memberId = M.id
				WHERE R.relId = #{relId}
				AND R.relTypeCode = #{relTypeCode}
			</script>
			""")
	public List<Reply> getForPrintReplies(int actorId, String relTypeCode, int relId);

	@Insert("""
			<script>
				INSERT INTO reply
				SET regDate = NOW(),
				updateDate = NOw(),
				memberId = #{actorId},
				relId = #{relId},
				relTypeCode = #{relTypeCode},
				`body` = #{body}
			</script>
					""")
	public void writeReply(String relTypeCode, int relId, String body, int actorId);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			<script>
				SELECT *
				FROM reply
				WHERE id = #{id}
			</script>
			""")
	public Reply getReply(int id);

	@Delete("""
			<script>
				DELETE FROM reply
				WHERE id = #{id}
			</script>
			""")
	public void deleteReply(int id);

	@Select("""
				SELECT R.*, M.nickname AS extra__writer
				FROM reply AS R
				INNER JOIN `member` AS M
				ON R.memberId = M.id
				WHERE R.id = #{id}
			""")
	Reply getForPrintReply(int id);

	@Update("""
			<script>
				UPDATE reply
				<set>
					<if test="body != null and body != ''">
						`body` = #{body},
					</if>
					updateDate = NOW()
				</set>
				WHERE id = #{id}
			</script>
			""")
	public void modifyReply(int id, String body);

	@Select("""
			SELECT R.*, M.nickname AS extra__writer
			FROM TB_REPLY R
			INNER JOIN TB_MEMBER M
			ON R.memberId = M.id
			""")
	public List<Reply> getReplies();

	@Select("""
			SELECT R.*, M.nickname AS extra__writer
			FROM TB_REPLY R
			INNER JOIN TB_MEMBER M
			ON R.memberId = M.id
			WHERE relTypeCode = #{relTypeCode}
			AND relId = #{relId}
			""")
	public List<Reply> getRepliesByRel(String relTypeCode, int relId);
}
